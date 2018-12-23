
// Copyright 2018 The Chromium Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

// This file is autogenerated by
//     java_cpp_enum.py
// From
//     ../../chrome/browser/vr/ui_test_input.h

package org.chromium.chrome.browser.vr;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@IntDef({
    UserFriendlyElementName.URL, UserFriendlyElementName.BACK_BUTTON,
    UserFriendlyElementName.FORWARD_BUTTON, UserFriendlyElementName.RELOAD_BUTTON,
    UserFriendlyElementName.OVERFLOW_MENU, UserFriendlyElementName.PAGE_INFO_BUTTON,
    UserFriendlyElementName.BROWSING_DIALOG, UserFriendlyElementName.CONTENT_QUAD,
    UserFriendlyElementName.NEW_INCOGNITO_TAB, UserFriendlyElementName.CLOSE_INCOGNITO_TABS,
    UserFriendlyElementName.EXIT_PROMPT, UserFriendlyElementName.SUGGESTION_BOX,
    UserFriendlyElementName.OMNIBOX_TEXT_FIELD, UserFriendlyElementName.OMNIBOX_CLOSE_BUTTON
})
@Retention(RetentionPolicy.SOURCE)
public @interface UserFriendlyElementName {
  int URL = 0;
  int BACK_BUTTON = 1;
  int FORWARD_BUTTON = 2;
  int RELOAD_BUTTON = 3;
  int OVERFLOW_MENU = 4;
  int PAGE_INFO_BUTTON = 5;
  int BROWSING_DIALOG = 6;
  int CONTENT_QUAD = 7;
  int NEW_INCOGNITO_TAB = 8;
  int CLOSE_INCOGNITO_TABS = 9;
  /**
   * menu
   */
  int EXIT_PROMPT = 10;
  int SUGGESTION_BOX = 11;
  int OMNIBOX_TEXT_FIELD = 12;
  /**
   * URL bar is clicked.
   */
  int OMNIBOX_CLOSE_BUTTON = 13;
}
