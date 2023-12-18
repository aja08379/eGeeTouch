package com.samsung.android.sdk.accessory;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.util.Log;
import com.samsung.accessory.api.ISAFrameworkManagerV2;
import java.util.List;
/* loaded from: classes2.dex */
public final class k {
    public static String a = "com.samsung.accessory";
    private static int b = 1;
    private static String c = "";
    private static int d = 1;
    private static int e;
    private static int f;
    private static int g;

    public k(Context context) throws d {
        if (context == null) {
            throw new IllegalArgumentException("Invalid Context");
        }
        PackageManager packageManager = context.getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(a, 0);
            if (packageInfo == null) {
                Log.e("[SA_SDK]SASdkConfig", "Accessory Framework Not installed");
                throw new d(2, "Accessory Framework Not installed");
            }
            b = packageInfo.versionCode;
            c = packageInfo.versionName;
            Log.i("[SA_SDK]SASdkConfig", "Accessory Framework:" + packageInfo.versionName + " Accessory SDK:2.6.4 r4");
            Intent intent = new Intent(ISAFrameworkManagerV2.class.getName());
            intent.setPackage(a);
            List<ResolveInfo> queryIntentServices = packageManager.queryIntentServices(intent, 32);
            if (queryIntentServices == null || queryIntentServices.size() == 0) {
                Log.w("[SA_SDK]SASdkConfig", "Accessory framework does not support thin-sdk");
            }
        } catch (PackageManager.NameNotFoundException unused) {
            Log.e("[SA_SDK]SASdkConfig", "Accessory Framework Not installed");
            throw new d(2, "Accessory Framework Not installed");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(int i) {
        e = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean a(Context context) {
        String packageName = context.getPackageName();
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager == null) {
                Log.w("[SA_SDK]SASdkConfig", "Package Manager is null");
                return false;
            }
            PackageInfo packageInfo = packageManager.getPackageInfo(packageName, 4096);
            if (packageInfo == null) {
                Log.w("[SA_SDK]SASdkConfig", "PackageInfo is null");
                return false;
            }
            String[] strArr = packageInfo.requestedPermissions;
            if (strArr == null) {
                return false;
            }
            int i = 0;
            while (true) {
                if (i >= strArr.length) {
                    i = -1;
                    break;
                } else if ("com.samsung.accessory.permission.ACCESSORY_FRAMEWORK".equals(strArr[i])) {
                    break;
                } else {
                    i++;
                }
            }
            if (i == -1) {
                Log.w("[SA_SDK]SASdkConfig", "Accessory service permission not granted for Package".concat(String.valueOf(packageName)));
                return false;
            }
            Log.i("[SA_SDK]SASdkConfig", "Accessory service permission available for Package".concat(String.valueOf(packageName)));
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            Log.e("[SA_SDK]SASdkConfig", "Admin Permission check failed for Package".concat(String.valueOf(packageName)));
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int b() {
        return e;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void b(int i) {
        f = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int c() {
        return f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void c(int i) {
        g = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int d() {
        return g;
    }

    public static void d(int i) {
        d = i;
    }

    public static int e() {
        return b;
    }

    public static String f() {
        return c;
    }

    public static int g() {
        return d;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String h() {
        return "UTF-8";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean i() {
        return b >= 407;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean j() {
        return d >= 407;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean k() {
        return b >= 205;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean l() {
        return b >= 218;
    }

    public static boolean m() {
        Log.d("[SA_SDK]SASdkConfig", "Active F/W version is " + d);
        return d >= 418;
    }
}
