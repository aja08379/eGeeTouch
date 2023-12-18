package com.samsung.android.sdk.accessory;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
/* loaded from: classes2.dex */
public final class RegisterUponInstallReceiver extends BroadcastReceiver {
    private static String a = "[SA_SDK]RegisterUponInstallReceiver";

    /* JADX WARN: Type inference failed for: r1v3, types: [com.samsung.android.sdk.accessory.RegisterUponInstallReceiver$1] */
    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        if (intent == null || intent.getAction() == null || !intent.getAction().equals("com.samsung.accessory.action.REGISTER_AGENT")) {
            return;
        }
        Log.d(a, "Received register intent:" + context.getPackageName());
        try {
            new k(context);
        } catch (d e) {
            Log.e(a, "SDK config initialization failed.".concat(String.valueOf(e)));
        }
        final BroadcastReceiver.PendingResult goAsync = goAsync();
        j jVar = new j(context.getApplicationContext());
        final Future<Void> a2 = jVar.a();
        new Thread("RegistrationThread") { // from class: com.samsung.android.sdk.accessory.RegisterUponInstallReceiver.1
            @Override // java.lang.Thread, java.lang.Runnable
            public final void run() {
                String str;
                try {
                    try {
                        a2.get();
                    } catch (InterruptedException e2) {
                        e = e2;
                        str = RegisterUponInstallReceiver.a;
                        Log.e(str, "Service Registration has failed!", e);
                    } catch (ExecutionException e3) {
                        e = e3;
                        str = RegisterUponInstallReceiver.a;
                        Log.e(str, "Service Registration has failed!", e);
                    }
                } finally {
                    goAsync.finish();
                }
            }
        }.start();
        jVar.b();
    }
}
