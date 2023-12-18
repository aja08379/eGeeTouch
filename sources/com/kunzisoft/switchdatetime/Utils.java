package com.kunzisoft.switchdatetime;

import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Build;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
/* loaded from: classes2.dex */
public class Utils {
    private static final int ANIMATION_DELAY = 0;
    private static final int PULSE_ANIMATOR_DURATION = 544;

    public static void animLabelElement(View view) {
        ObjectAnimator pulseAnimator = getPulseAnimator(view, 0.9f, 1.05f);
        pulseAnimator.setStartDelay(0L);
        pulseAnimator.start();
    }

    public static ObjectAnimator getPulseAnimator(View view, float f, float f2) {
        Keyframe ofFloat = Keyframe.ofFloat(0.0f, 1.0f);
        Keyframe ofFloat2 = Keyframe.ofFloat(0.275f, f);
        Keyframe ofFloat3 = Keyframe.ofFloat(0.69f, f2);
        Keyframe ofFloat4 = Keyframe.ofFloat(1.0f, 1.0f);
        ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(view, PropertyValuesHolder.ofKeyframe("scaleX", ofFloat, ofFloat2, ofFloat3, ofFloat4), PropertyValuesHolder.ofKeyframe("scaleY", ofFloat, ofFloat2, ofFloat3, ofFloat4));
        ofPropertyValuesHolder.setDuration(544L);
        return ofPropertyValuesHolder;
    }

    public static boolean isTouchExplorationEnabled(AccessibilityManager accessibilityManager) {
        return Build.VERSION.SDK_INT >= 14 && accessibilityManager.isTouchExplorationEnabled();
    }

    public static void tryAccessibilityAnnounce(View view, CharSequence charSequence) {
        if (Build.VERSION.SDK_INT < 16 || view == null || charSequence == null) {
            return;
        }
        view.announceForAccessibility(charSequence);
    }
}
