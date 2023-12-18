package com.samsung.android.sdk.accessoryfiletransfer;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.util.Log;
import com.facebook.share.internal.ShareConstants;
import com.samsung.android.sdk.SsdkInterface;
import com.samsung.android.sdk.SsdkUnsupportedException;
import com.samsung.android.sdk.SsdkVendorCheck;
import java.util.List;
/* loaded from: classes2.dex */
public class SAft implements SsdkInterface {
    public static final int DEVICE_ACCESSORY = 0;
    public static String FILE_TRANSFER_SERVICE_INTENT = "com.samsung.accessory.ISAFTManager";
    private static int c = 218;
    private boolean a;
    private int b;
    private boolean d = false;

    private static boolean b() {
        try {
            Class<?> cls = Class.forName("com.samsung.android.feature.FloatingFeature");
            boolean booleanValue = ((Boolean) cls.getMethod("getEnableStatus", String.class).invoke(cls.getMethod("getInstance", new Class[0]).invoke(null, new Object[0]), "SEC_FLOATING_FEATURE_CONTEXTSERVICE_ENABLE_SURVEY_MODE")).booleanValue();
            Log.d("SecFloating", "floating feature : ".concat(String.valueOf(booleanValue)));
            return booleanValue;
        } catch (Exception unused) {
            Log.d("SecFloating", "Floating feature is not supported (non-samsung device)");
            try {
                Class<?> cls2 = Class.forName("com.samsung.android.feature.SemFloatingFeature");
                boolean booleanValue2 = ((Boolean) cls2.getMethod("getBoolean", String.class).invoke(cls2.getMethod("getInstance", new Class[0]).invoke(null, new Object[0]), "SEC_FLOATING_FEATURE_CONTEXTSERVICE_ENABLE_SURVEY_MODE")).booleanValue();
                Log.d("SecFloating", "floating feature : ".concat(String.valueOf(booleanValue2)));
                return booleanValue2;
            } catch (Exception unused2) {
                Log.d("SecFloating", "Floating feature is not supported this device (non-samsung device)");
                return false;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean a() {
        Log.d("[SA_SDK]SAft", "Samsung Accessory File Transfer CORE version: " + this.b);
        return this.b > c;
    }

    public String getFileTransferPackageName(Context context) {
        List<ResolveInfo> queryIntentServices = context.getPackageManager().queryIntentServices(new Intent(FILE_TRANSFER_SERVICE_INTENT), 0);
        if (queryIntentServices.size() == 1) {
            return queryIntentServices.get(0).serviceInfo.packageName;
        }
        return null;
    }

    @Override // com.samsung.android.sdk.SsdkInterface
    public int getVersionCode() {
        return 3;
    }

    @Override // com.samsung.android.sdk.SsdkInterface
    public String getVersionName() {
        return "2.6.3";
    }

    @Override // com.samsung.android.sdk.SsdkInterface
    public void initialize(Context context) throws SsdkUnsupportedException {
        String str;
        if (context == null) {
            throw new IllegalArgumentException("Illegal argument input: context");
        }
        if (this.a) {
            return;
        }
        if (!this.d) {
            try {
                if (SsdkVendorCheck.isSamsungDevice()) {
                    int i = -1;
                    try {
                        i = context.getPackageManager().getPackageInfo("com.samsung.android.providers.context", 128).versionCode;
                    } catch (PackageManager.NameNotFoundException unused) {
                        Log.d("SM_SDK", "Could not find ContextProvider");
                    }
                    Log.d("SM_SDK", "versionCode: ".concat(String.valueOf(i)));
                    if (i <= 1) {
                        str = "Add com.samsung.android.providers.context.permission.WRITE_USE_APP_FEATURE_SURVEY permission";
                    } else if (context.checkCallingOrSelfPermission("com.samsung.android.providers.context.permission.WRITE_USE_APP_FEATURE_SURVEY") != 0) {
                        str = "com.samsung.android.providers.context.permission.WRITE_USE_APP_FEATURE_SURVEY is not allowed";
                    } else if (b()) {
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("app_id", getClass().getPackage().getName());
                        contentValues.put("feature", context.getPackageName() + "#" + getVersionCode());
                        Intent intent = new Intent();
                        intent.setAction("com.samsung.android.providers.context.log.action.USE_APP_FEATURE_SURVEY");
                        intent.putExtra(ShareConstants.WEB_DIALOG_PARAM_DATA, contentValues);
                        intent.setPackage("com.samsung.android.providers.context");
                        context.sendBroadcast(intent);
                    }
                    Log.d("SM_SDK", str);
                }
            } catch (SecurityException unused2) {
                Log.e("[SA_SDK]SAft", "SecurityException : com.samsung.android.providers.context.permission.WRITE_USE_APP_FEATURE_SURVEY permission is required.");
            }
            this.d = true;
        }
        PackageManager packageManager = context.getPackageManager();
        try {
            if (packageManager.getPackageInfo("com.samsung.accessory", 0) == null) {
                throw new SsdkUnsupportedException("Device not supported", 1);
            }
            String fileTransferPackageName = getFileTransferPackageName(context);
            if (fileTransferPackageName == null) {
                throw new SsdkUnsupportedException("Samsung Accessory Framework not installed", 2);
            }
            PackageInfo packageInfo = packageManager.getPackageInfo(fileTransferPackageName, 0);
            if (packageInfo == null) {
                throw new SsdkUnsupportedException("Samsung Accessory Framework not installed", 2);
            }
            this.b = packageInfo.versionCode;
            String str2 = packageInfo.versionName;
            Log.d("[SA_SDK]SAft", "Samsung Accessory File Transfer SDK version: 2.6.3");
            this.a = true;
        } catch (PackageManager.NameNotFoundException unused3) {
            Log.e("[SA_SDK]SAft", "Samsung Accessory Framework not installed");
            throw new SsdkUnsupportedException("Samsung Accessory Framework not installed", 2);
        }
    }

    @Override // com.samsung.android.sdk.SsdkInterface
    public boolean isFeatureEnabled(int i) {
        if (i == 0) {
            return true;
        }
        throw new IllegalArgumentException();
    }
}
