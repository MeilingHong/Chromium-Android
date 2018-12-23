
// Copyright 2014 The Chromium Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

// This file is autogenerated by:
//     mojo/public/tools/bindings/mojom_bindings_generator.py
// For:
//     third_party/blink/public/mojom/feature_policy/feature_policy.mojom
//

package org.chromium.blink.mojom;

import org.chromium.mojo.bindings.DeserializationException;

public final class FeaturePolicyFeature {


    public static final int NOT_FOUND = (int) (0L);

    public static final int AUTOPLAY = (int) (1L);

    public static final int CAMERA = (int) (2L);

    public static final int ENCRYPTED_MEDIA = (int) (3L);

    public static final int FULLSCREEN = (int) (4L);

    public static final int GEOLOCATION = (int) (5L);

    public static final int MICROPHONE = (int) (6L);

    public static final int MIDI_FEATURE = (int) (7L);

    public static final int PAYMENT = (int) (8L);

    public static final int SPEAKER = (int) (9L);

    public static final int DOCUMENT_COOKIE = (int) (10L);

    public static final int DOCUMENT_DOMAIN = (int) (11L);

    public static final int SYNC_SCRIPT = (int) (12L);

    public static final int SYNC_XHR = (int) (13L);

    public static final int USB = (int) (14L);

    public static final int ACCESSIBILITY_EVENTS = (int) (15L);

    public static final int WEB_VR = (int) (16L);

    public static final int ACCELEROMETER = (int) (17L);

    public static final int AMBIENT_LIGHT_SENSOR = (int) (18L);

    public static final int GYROSCOPE = (int) (19L);

    public static final int MAGNETOMETER = (int) (20L);

    public static final int UNSIZED_MEDIA = (int) (21L);

    public static final int LEGACY_IMAGE_FORMATS = (int) (22L);

    public static final int IMAGE_COMPRESSION = (int) (23L);

    public static final int ANIMATIONS = (int) (24L);

    public static final int MAX_DOWNSCALING_IMAGE = (int) (25L);

    public static final int PICTURE_IN_PICTURE = (int) (26L);

    public static final int VERTICAL_SCROLL = (int) (27L);

    public static final int DOCUMENT_WRITE = (int) (28L);

    public static final int LAZY_LOAD = (int) (29L);


    private static final boolean IS_EXTENSIBLE = false;

    public static boolean isKnownValue(int value) {
        switch (value) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
                return true;
        }
        return false;
    }

    public static void validate(int value) {
        if (IS_EXTENSIBLE || isKnownValue(value))
            return;

        throw new DeserializationException("Invalid enum value.");
    }

    private FeaturePolicyFeature() {}

}