package com.google.android.gms.vision;

import android.util.Log;
/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.3 */
/* loaded from: classes2.dex */
public class L {
    public static final String TAG = "Vision";

    public static int v(String str, Object... objArr) {
        if (Log.isLoggable(TAG, 2)) {
            return Log.v(TAG, String.format(str, objArr));
        }
        return 0;
    }

    public static int d(String str, Object... objArr) {
        if (Log.isLoggable(TAG, 3)) {
            return Log.d(TAG, String.format(str, objArr));
        }
        return 0;
    }

    public static int d(Throwable th, String str, Object... objArr) {
        if (Log.isLoggable(TAG, 3)) {
            return Log.d(TAG, String.format(str, objArr), th);
        }
        return 0;
    }

    public static int i(String str, Object... objArr) {
        if (Log.isLoggable(TAG, 4)) {
            return Log.i(TAG, String.format(str, objArr));
        }
        return 0;
    }

    public static int e(String str, Object... objArr) {
        if (Log.isLoggable(TAG, 6)) {
            return Log.e(TAG, String.format(str, objArr));
        }
        return 0;
    }

    public static int e(Throwable th, String str, Object... objArr) {
        if (Log.isLoggable(TAG, 6)) {
            if (Log.isLoggable(TAG, 3)) {
                return Log.e(TAG, String.format(str, objArr), th);
            }
            String format = String.format(str, objArr);
            String valueOf = String.valueOf(th);
            return Log.e(TAG, new StringBuilder(String.valueOf(format).length() + 2 + String.valueOf(valueOf).length()).append(format).append(": ").append(valueOf).toString());
        }
        return 0;
    }

    public static int w(String str, Object... objArr) {
        if (Log.isLoggable(TAG, 5)) {
            return Log.w(TAG, String.format(str, objArr));
        }
        return 0;
    }

    public static int w(Throwable th, String str, Object... objArr) {
        if (Log.isLoggable(TAG, 5)) {
            if (Log.isLoggable(TAG, 3)) {
                return Log.w(TAG, String.format(str, objArr), th);
            }
            String format = String.format(str, objArr);
            String valueOf = String.valueOf(th);
            return Log.w(TAG, new StringBuilder(String.valueOf(format).length() + 2 + String.valueOf(valueOf).length()).append(format).append(": ").append(valueOf).toString());
        }
        return 0;
    }
}
