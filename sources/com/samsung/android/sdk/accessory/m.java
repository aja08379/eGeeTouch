package com.samsung.android.sdk.accessory;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import android.util.Log;
import no.nordicsemi.android.dfu.DfuServiceInitiator;
/* loaded from: classes2.dex */
class m {
    private static m a;
    private static boolean b;
    private PowerManager.WakeLock c;
    private Context d;
    private Handler e;
    private int f;

    private m(Context context) {
        this.d = context;
        PowerManager powerManager = (PowerManager) context.getSystemService("power");
        if (powerManager == null) {
            Log.w("[SA_SDK]SAServiceHelper", "Failed to get the POWER_SERVICE!");
            return;
        }
        this.c = powerManager.newWakeLock(1, m.class.getSimpleName());
        this.e = new Handler(Looper.getMainLooper());
        this.f = 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized m a(Context context) {
        m mVar;
        synchronized (m.class) {
            if (a == null) {
                a = new m(context);
                try {
                    boolean z = false;
                    int i = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).applicationInfo.targetSdkVersion;
                    if (Build.VERSION.SDK_INT >= 26 && i >= 26) {
                        z = true;
                    }
                    b = z;
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
            }
            mVar = a;
        }
        return mVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Intent intent, String str) {
        ComponentName startService;
        try {
            Class<?> cls = Class.forName(str);
            if (a(this.d, cls.getName())) {
                if (SAAgentV2.class.isAssignableFrom(cls)) {
                    intent.setClassName(this.d, SAService.class.getName());
                } else {
                    intent.setClassName(this.d, str);
                }
                if (b) {
                    b();
                    startService = this.d.startForegroundService(intent);
                } else {
                    startService = this.d.startService(intent);
                }
                if (startService == null) {
                    if (b(this.d)) {
                        Log.e("[SA_SDK]SAServiceHelper", "App is restricted in background. Cannot start a service for connection request.");
                    } else {
                        Log.e("[SA_SDK]SAServiceHelper", "Agent " + intent.getComponent().getClassName() + " not found. Check Accessory Service XML for serviceImpl attribute");
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private synchronized boolean a(Context context, String str) {
        boolean z;
        String str2;
        String str3;
        z = false;
        c a2 = c.a(context);
        if (a2 != null) {
            n a3 = a2.a(str);
            if (a3 == null) {
                str2 = "[SA_SDK]SAServiceHelper";
                str3 = "fetch service profile description failed !!";
            } else if (str.equalsIgnoreCase(a3.c())) {
                z = true;
            }
        } else {
            str2 = "[SA_SDK]SAServiceHelper";
            str3 = "config  util default instance  creation failed !!";
        }
        Log.e(str2, str3);
        return z;
    }

    private synchronized void b() {
        c();
        this.c.acquire(DfuServiceInitiator.DEFAULT_SCAN_TIMEOUT);
        this.f++;
    }

    private static boolean b(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        return Build.VERSION.SDK_INT >= 28 && activityManager != null && activityManager.isBackgroundRestricted();
    }

    private void c() {
        if (this.c.isHeld()) {
            this.c.release();
        }
    }

    public final synchronized void a() {
        if (b) {
            int i = this.f - 1;
            this.f = i;
            if (i <= 0) {
                c();
            }
        }
    }

    public final void a(final Intent intent, final String str, boolean z) {
        if (z) {
            a(intent, str);
            return;
        }
        this.e.post(new Runnable() { // from class: com.samsung.android.sdk.accessory.m.1
            @Override // java.lang.Runnable
            public final void run() {
                m.this.a(intent, str);
            }
        });
    }
}
