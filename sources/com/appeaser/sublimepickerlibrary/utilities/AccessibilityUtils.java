package com.appeaser.sublimepickerlibrary.utilities;

import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import androidx.core.view.accessibility.AccessibilityEventCompat;
/* loaded from: classes.dex */
public class AccessibilityUtils {
    public static void makeAnnouncement(View view, CharSequence charSequence) {
        if (view == null) {
            return;
        }
        if (SUtils.isApi_16_OrHigher()) {
            view.announceForAccessibility(charSequence);
            return;
        }
        AccessibilityManager accessibilityManager = (AccessibilityManager) view.getContext().getSystemService("accessibility");
        if (accessibilityManager.isEnabled()) {
            AccessibilityEvent obtain = AccessibilityEvent.obtain(64);
            AccessibilityEventCompat.asRecord(obtain).setSource(view);
            obtain.setClassName(view.getClass().getName());
            obtain.setPackageName(view.getContext().getPackageName());
            obtain.setEnabled(view.isEnabled());
            obtain.getText().add(charSequence);
            accessibilityManager.sendAccessibilityEvent(obtain);
        }
    }
}
