package com.samsung.android.sdk.accessory;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.Log;
import com.facebook.share.internal.ShareConstants;
import com.samsung.android.sdk.SsdkVendorCheck;
/* loaded from: classes2.dex */
public final class g {
    private static boolean a = false;

    static {
        Log.d("[SA_SDK]SAGSIMLog", "init()");
        boolean a2 = a();
        a = a2;
        if (a2) {
            return;
        }
        Log.e("[SA_SDK]SAGSIMLog", "GSIM logging is not enabled.");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void a(Context context, String str, String str2) {
        if (SsdkVendorCheck.isSamsungDevice()) {
            try {
                int i = context.getPackageManager().getPackageInfo("com.samsung.android.providers.context", 128).versionCode;
                Log.d("[SA_SDK]SAGSIMLog", "versionCode: ".concat(String.valueOf(i)));
                if (i <= 1) {
                    Log.d("[SA_SDK]SAGSIMLog", "Add WRITE_USE_APP_FEATURE_SURVEY permission");
                } else if (context.checkCallingOrSelfPermission("com.samsung.android.providers.context.permission.WRITE_USE_APP_FEATURE_SURVEY") != 0) {
                    Log.d("[SA_SDK]SAGSIMLog", "WRITE_USE_APP_FEATURE_SURVEY is not allowed");
                } else if (a) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("app_id", "com.samsung.android.sdk.accessory");
                    contentValues.put("feature", str);
                    contentValues.put("extra", str2);
                    Intent intent = new Intent();
                    intent.setAction("com.samsung.android.providers.context.log.action.USE_APP_FEATURE_SURVEY");
                    intent.setPackage("com.samsung.android.providers.context");
                    intent.putExtra(ShareConstants.WEB_DIALOG_PARAM_DATA, contentValues);
                    context.sendBroadcast(intent);
                }
            } catch (PackageManager.NameNotFoundException unused) {
                Log.d("[SA_SDK]SAGSIMLog", "Could not find ContextProvider");
            } catch (SecurityException unused2) {
                Log.e("[SA_SDK]SAGSIMLog", "SecurityException : WRITE_USE_APP_FEATURE_SURVEY permission is required.");
            }
        }
    }

    private static boolean a() {
        try {
            Class<?> cls = Class.forName("com.samsung.android.feature.FloatingFeature");
            boolean booleanValue = ((Boolean) cls.getMethod("getEnableStatus", String.class).invoke(cls.getMethod("getInstance", new Class[0]).invoke(null, new Object[0]), "SEC_FLOATING_FEATURE_CONTEXTSERVICE_ENABLE_SURVEY_MODE")).booleanValue();
            Log.d("[SA_SDK]SAGSIMLog", "[SecFloating] floating feature : ".concat(String.valueOf(booleanValue)));
            return booleanValue;
        } catch (Exception unused) {
            Log.d("[SA_SDK]SAGSIMLog", "[SecFloating] feature is not supported (non-samsung device)");
            try {
                Class<?> cls2 = Class.forName("com.samsung.android.feature.SemFloatingFeature");
                boolean booleanValue2 = ((Boolean) cls2.getMethod("getBoolean", String.class).invoke(cls2.getMethod("getInstance", new Class[0]).invoke(null, new Object[0]), "SEC_FLOATING_FEATURE_CONTEXTSERVICE_ENABLE_SURVEY_MODE")).booleanValue();
                Log.d("[SA_SDK]SAGSIMLog", "[SecFloating] floating feature : ".concat(String.valueOf(booleanValue2)));
                return booleanValue2;
            } catch (Exception unused2) {
                Log.d("[SA_SDK]SAGSIMLog", "[SecFloating] feature is not supported this device (non-samsung device)");
                return false;
            }
        }
    }
}
