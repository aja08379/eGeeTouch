package com.egeetouch.egeetouch_manager;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
/* loaded from: classes.dex */
public class PermissionUtils {
    public static boolean neverAskAgainSelected(Activity activity, String str) {
        return getRatinaleDisplayStatus(activity, str) != activity.shouldShowRequestPermissionRationale(str);
    }

    public static void setShouldShowStatus(Context context, String str) {
        SharedPreferences.Editor edit = context.getSharedPreferences("GENERIC_PREFERENCES", 0).edit();
        edit.putBoolean(str, true);
        edit.commit();
    }

    public static boolean getRatinaleDisplayStatus(Context context, String str) {
        return context.getSharedPreferences("GENERIC_PREFERENCES", 0).getBoolean(str, false);
    }
}
