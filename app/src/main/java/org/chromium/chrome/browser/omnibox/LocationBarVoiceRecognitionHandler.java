// Copyright 2018 The Chromium Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

package org.chromium.chrome.browser.omnibox;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.speech.RecognizerIntent;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.text.TextUtils;

import org.chromium.base.metrics.CachedMetrics;
import org.chromium.chrome.R;
import org.chromium.chrome.browser.omnibox.VoiceSuggestionProvider.VoiceResult;
import org.chromium.chrome.browser.search_engines.TemplateUrlService;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.toolbar.ToolbarDataProvider;
import org.chromium.chrome.browser.util.FeatureUtilities;
import org.chromium.content_public.browser.RenderFrameHost;
import org.chromium.content_public.browser.WebContents;
import org.chromium.content_public.browser.WebContentsObserver;
import org.chromium.ui.base.PermissionCallback;
import org.chromium.ui.base.WindowAndroid;

/**
 * Class containing functionality related to voice search in the location bar.
 */
public class LocationBarVoiceRecognitionHandler {
    // The minimum confidence threshold that will result in navigating directly to a voice search
    // response (as opposed to treating it like a typed string in the Omnibox).
    @VisibleForTesting
    public static final float VOICE_SEARCH_CONFIDENCE_NAVIGATE_THRESHOLD = 0.9f;

    private static final CachedMetrics
            .EnumeratedHistogramSample VOICE_INTERACTION_START_SOURCE_METRIC =
            new CachedMetrics.EnumeratedHistogramSample(
                    "VoiceInteraction.StartEventSource", VoiceInteractionSource.HISTOGRAM_BOUNDARY);
    private static final CachedMetrics
            .EnumeratedHistogramSample VOICE_INTERACTION_FINISH_SOURCE_METRIC =
            new CachedMetrics.EnumeratedHistogramSample("VoiceInteraction.FinishEventSource",
                    VoiceInteractionSource.HISTOGRAM_BOUNDARY);
    private static final CachedMetrics
            .EnumeratedHistogramSample VOICE_INTERACTION_DISMISSED_SOURCE_METRIC =
            new CachedMetrics.EnumeratedHistogramSample("VoiceInteraction.DismissedEventSource",
                    VoiceInteractionSource.HISTOGRAM_BOUNDARY);
    private static final CachedMetrics
            .EnumeratedHistogramSample VOICE_INTERACTION_FAILURE_SOURCE_METRIC =
            new CachedMetrics.EnumeratedHistogramSample("VoiceInteraction.FailureEventSource",
                    VoiceInteractionSource.HISTOGRAM_BOUNDARY);
    private static final CachedMetrics.BooleanHistogramSample VOICE_SEARCH_RESULT_METRIC =
            new CachedMetrics.BooleanHistogramSample("VoiceInteraction.VoiceSearchResult");
    // There's no percentage histogram sample in CachedMetrics, so we mimic what that does
    // internally.
    private static final CachedMetrics
            .EnumeratedHistogramSample VOICE_SEARCH_CONFIDENCE_VALUE_METRIC =
            new CachedMetrics.EnumeratedHistogramSample(
                    "VoiceInteraction.VoiceResultConfidenceValue", 101);

    final private Delegate mDelegate;
    private WebContentsObserver mVoiceSearchWebContentsObserver;

    // VoiceInteractionEventSource defined in tools/metrics/histograms/enums.xml.
    // Do not reorder or remove items, only add new items before HISTOGRAM_BOUNDARY.
    @IntDef({VoiceInteractionSource.OMNIBOX, VoiceInteractionSource.NTP,
            VoiceInteractionSource.SEARCH_WIDGET})
    public @interface VoiceInteractionSource {
        int OMNIBOX = 0;
        int NTP = 1;
        int SEARCH_WIDGET = 2;
        int HISTOGRAM_BOUNDARY = 3;
    }

    /**
     * Delegate interface to provide data to this class from the location bar implementation.
     */
    public interface Delegate {
        /**
         * Loads the provided URL, assumes the PageTransition type is TYPED.
         * @param url The URL to load.
         */
        void loadUrlFromVoice(String url);

        /**
         * Notifies the location bar that the state of the voice search microphone button may need
         * to be updated.
         */
        void updateMicButtonState();

        /**
         * Sets the query string in the omnibox (ensuring the URL bar has focus and triggering
         * autocomplete for the specified query) as if the user typed it.
         * @param query The query to be set in the omnibox.
         */
        void setSearchQuery(final String query);

        /**
         * Grabs a reference to the toolbar data provider from the location bar.
         * @return The {@link ToolbarDataProvider} currently in use by the
         *         {@link LocationBarLayout}.
         */
        ToolbarDataProvider getToolbarDataProvider();

        /**
         * Grabs a reference to the autocomplete coordinator from the location bar.
         * @return The {@link AutocompleteCoordinator} currently in use by the
         *         {@link LocationBarLayout}.
         */
        // TODO(tedchoc): Limit the visibility of what is passed in here.  This does not need the
        //                full coordinator.  It simply needs a way to pass voice suggestions to the
        //                AutocompleteController.
        AutocompleteCoordinator getAutocompleteCoordinator();

        /**
         * @return The current {@link WindowAndroid}.
         */
        WindowAndroid getWindowAndroid();
    }

    public LocationBarVoiceRecognitionHandler(Delegate delegate) {
        mDelegate = delegate;
    }

    /**
     * Instantiated when a voice search is performed to monitor the web contents for a navigation
     * to be started so we can notify the render frame that a user gesture has been performed. This
     * allows autoplay of the voice response for search results.
     */
    private final class VoiceSearchWebContentsObserver extends WebContentsObserver {
        public VoiceSearchWebContentsObserver(WebContents webContents) {
            super(webContents);
        }

        /**
         * Forces the user gesture flag to be set on a render frame if the URL being navigated to
         * is a SRP.
         *
         * @param url The URL for the navigation that started, so we can ensure that what we're
         * navigating to is actually a SRP.
         */
        private void setReceivedUserGesture(String url) {
            WebContents webContents = mWebContents.get();
            if (webContents == null) return;

            RenderFrameHost renderFrameHost = webContents.getMainFrame();
            if (renderFrameHost == null) return;
            if (TemplateUrlService.getInstance().isSearchResultsPageFromDefaultSearchProvider(
                        url)) {
                renderFrameHost.notifyUserActivation();
            }
        }

        @Override
        public void didFinishNavigation(String url, boolean isInMainFrame, boolean isErrorPage,
                boolean hasCommitted, boolean isSameDocument, boolean isFragmentNavigation,
                boolean isRendererInitiated, boolean isDownload, @Nullable Integer pageTransition,
                int errorCode, String errorDescription, int httpStatusCode) {
            if (hasCommitted && isInMainFrame && !isErrorPage) setReceivedUserGesture(url);
            destroy();
        }
    }

    /**
     * Callback for when we receive voice search results after initiating voice recognition.
     */
    class VoiceRecognitionCompleteCallback implements WindowAndroid.IntentCallback {
        @VoiceInteractionSource
        private final int mSource;

        public VoiceRecognitionCompleteCallback(@VoiceInteractionSource int source) {
            mSource = source;
        }

        // WindowAndroid.IntentCallback implementation:
        @Override
        public void onIntentCompleted(WindowAndroid window, int resultCode, Intent data) {
            if (resultCode != Activity.RESULT_OK || data.getExtras() == null) {
                if (resultCode == Activity.RESULT_CANCELED) {
                    recordVoiceSearchDismissedEventSource(mSource);
                } else {
                    recordVoiceSearchFailureEventSource(mSource);
                }
                return;
            }

            AutocompleteCoordinator autocompleteCoordinator =
                    mDelegate.getAutocompleteCoordinator();
            assert autocompleteCoordinator != null;

            recordVoiceSearchFinishEventSource(mSource);

            VoiceResult topResult = autocompleteCoordinator.onVoiceResults(data.getExtras());
            if (topResult == null) {
                recordVoiceSearchResult(false);
                return;
            }

            String topResultQuery = topResult.getMatch();
            if (TextUtils.isEmpty(topResultQuery)) {
                recordVoiceSearchResult(false);
                return;
            }

            recordVoiceSearchResult(true);
            recordVoiceSearchConfidenceValue(topResult.getConfidence());

            if (topResult.getConfidence() < VOICE_SEARCH_CONFIDENCE_NAVIGATE_THRESHOLD) {
                mDelegate.setSearchQuery(topResultQuery);
                return;
            }

            String url = AutocompleteController.nativeQualifyPartialURLQuery(topResultQuery);
            if (url == null) {
                url = TemplateUrlService.getInstance().getUrlForVoiceSearchQuery(topResultQuery);
            }

            // Since voice was used, we need to let the frame know that there was a user gesture.
            ToolbarDataProvider toolbarDataProvider = mDelegate.getToolbarDataProvider();
            Tab currentTab = toolbarDataProvider != null ? toolbarDataProvider.getTab() : null;
            if (currentTab != null) {
                if (mVoiceSearchWebContentsObserver != null) {
                    mVoiceSearchWebContentsObserver.destroy();
                    mVoiceSearchWebContentsObserver = null;
                }
                if (currentTab.getWebContents() != null) {
                    mVoiceSearchWebContentsObserver =
                            new VoiceSearchWebContentsObserver(currentTab.getWebContents());
                }
            }
            mDelegate.loadUrlFromVoice(url);
        }
    }

    /**
     * Triggers a voice recognition intent to allow the user to specify a search query.
     * @param source The source of the voice recognition initiation, such as NTP or omnibox.
     */
    public void startVoiceRecognition(@VoiceInteractionSource int source) {
        WindowAndroid windowAndroid = mDelegate.getWindowAndroid();
        if (windowAndroid == null) return;
        Activity activity = windowAndroid.getActivity().get();
        if (activity == null) return;

        if (!windowAndroid.hasPermission(Manifest.permission.RECORD_AUDIO)) {
            if (windowAndroid.canRequestPermission(Manifest.permission.RECORD_AUDIO)) {
                PermissionCallback callback = new PermissionCallback() {
                    @Override
                    public void onRequestPermissionsResult(
                            String[] permissions, int[] grantResults) {
                        if (grantResults.length != 1) return;

                        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                            startVoiceRecognition(source);
                        } else {
                            mDelegate.updateMicButtonState();
                        }
                    }
                };
                windowAndroid.requestPermissions(
                        new String[] {Manifest.permission.RECORD_AUDIO}, callback);
            } else {
                mDelegate.updateMicButtonState();
            }
            return;
        }

        // Record metrics on the source of a voice search interaction, such as NTP or omnibox.
        recordVoiceSearchStartEventSource(source);

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH);
        intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE,
                activity.getComponentName().flattenToString());
        intent.putExtra(RecognizerIntent.EXTRA_WEB_SEARCH_ONLY, true);

        if (!showSpeechRecognitionIntent(windowAndroid, intent, source)) {
            // Requery whether or not the recognition intent can be handled.
            isRecognitionIntentPresent(activity, false);
            mDelegate.updateMicButtonState();
            recordVoiceSearchFailureEventSource(source);
        }
    }

    /**
     * Shows a cancelable speech recognition intent, returning a boolean that indicates if it was
     * successfully shown.
     *
     * @param windowAndroid The {@link WindowAndroid} associated with the current {@link Tab}.
     * @param intent The speech recognition {@link Intent}.
     * @param source Where the request to launch this @link Intent} originated, such as NTP or
     *        omnibox.
     * @return True if showing the {@link Intent} was successful.
     */
    private boolean showSpeechRecognitionIntent(
            WindowAndroid windowAndroid, Intent intent, @VoiceInteractionSource int source) {
        return windowAndroid.showCancelableIntent(intent,
                       new VoiceRecognitionCompleteCallback(source), R.string.voice_search_error)
                >= 0;
    }

    /**
     * @return Whether or not voice search is enabled.
     */
    public boolean isVoiceSearchEnabled() {
        ToolbarDataProvider toolbarDataProvider = mDelegate.getToolbarDataProvider();
        if (toolbarDataProvider == null) return false;

        boolean isIncognito = toolbarDataProvider.isIncognito();
        WindowAndroid windowAndroid = mDelegate.getWindowAndroid();
        if (windowAndroid == null || isIncognito) return false;

        if (!windowAndroid.hasPermission(Manifest.permission.RECORD_AUDIO)
                && !windowAndroid.canRequestPermission(Manifest.permission.RECORD_AUDIO)) {
            return false;
        }

        Activity activity = windowAndroid.getActivity().get();
        return activity != null && isRecognitionIntentPresent(activity, true);
    }

    /**
     * Records the source of a voice search initiation.
     * @param source The source of the voice search, such as NTP or omnibox. Values taken from the
     *        enum VoiceInteractionEventSource in enums.xml.
     */
    @VisibleForTesting
    protected void recordVoiceSearchStartEventSource(@VoiceInteractionSource int source) {
        VOICE_INTERACTION_START_SOURCE_METRIC.record(source);
    }

    /**
     * Records the source of a successful voice search completion.
     * @param source The source of the voice search, such as NTP or omnibox. Values taken from the
     *        enum VoiceInteractionEventSource in enums.xml.
     */
    @VisibleForTesting
    protected void recordVoiceSearchFinishEventSource(@VoiceInteractionSource int source) {
        VOICE_INTERACTION_FINISH_SOURCE_METRIC.record(source);
    }

    /**
     * Records the source of a dismissed voice search.
     * @param source The source of the voice search, such as NTP or omnibox. Values taken from the
     *        enum VoiceInteractionEventSource in enums.xml.
     */
    @VisibleForTesting
    protected void recordVoiceSearchDismissedEventSource(@VoiceInteractionSource int source) {
        VOICE_INTERACTION_DISMISSED_SOURCE_METRIC.record(source);
    }

    /**
     * Records the source of a failed voice search.
     * @param source The source of the voice search, such as NTP or omnibox. Values taken from the
     *        enum VoiceInteractionEventSource in enums.xml.
     */
    @VisibleForTesting
    protected void recordVoiceSearchFailureEventSource(@VoiceInteractionSource int source) {
        VOICE_INTERACTION_FAILURE_SOURCE_METRIC.record(source);
    }

    /**
     * Records the result of a voice search.
     * @param result The result of a voice search, true if results were successfully returned.
     */
    @VisibleForTesting
    protected void recordVoiceSearchResult(boolean result) {
        VOICE_SEARCH_RESULT_METRIC.record(result);
    }

    /**
     * Records the voice search confidence value as a percentage, instead of the 0.0 to 1.0 range
     * we receive.
     * @param value The voice search confidence value we received from 0.0 to 1.0.
     */
    @VisibleForTesting
    protected void recordVoiceSearchConfidenceValue(float value) {
        int percentage = Math.round(value * 100f);
        VOICE_SEARCH_CONFIDENCE_VALUE_METRIC.record(percentage);
    }

    /**
     * Calls into {@link FeatureUtilities} to determine whether or not the
     * {@link RecognizerIntent#ACTION_WEB_SEARCH} {@link Intent} is handled by any
     * {@link android.app.Activity}s in the system.
     *
     * @param context        The {@link Context} to use to check to see if the {@link Intent} will
     *                       be handled.
     * @param useCachedValue Whether or not to use the cached value from a previous result.
     * @return {@code true} if recognition is supported.  {@code false} otherwise.
     */
    @VisibleForTesting
    protected boolean isRecognitionIntentPresent(Context context, boolean useCachedValue) {
        return FeatureUtilities.isRecognitionIntentPresent(context, useCachedValue);
    }
}
