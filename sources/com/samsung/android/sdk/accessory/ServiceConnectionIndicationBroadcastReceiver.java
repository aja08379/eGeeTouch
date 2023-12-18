package com.samsung.android.sdk.accessory;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
/* loaded from: classes2.dex */
public final class ServiceConnectionIndicationBroadcastReceiver extends BroadcastReceiver {
    private String a = "[SA_SDK]ServiceConnectionIndicationReceiver";

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        if (intent == null || intent.getAction() == null || !intent.getAction().equalsIgnoreCase("com.samsung.accessory.action.SERVICE_CONNECTION_REQUESTED")) {
            return;
        }
        Log.i(this.a, "Incoming service connection request received.");
        try {
            new k(context);
            String stringExtra = intent.getStringExtra("agentImplclass");
            if (stringExtra == null) {
                Log.e(this.a, "Impl class not availabel in intent. Ignoring request");
                return;
            }
            Log.v(this.a, "Connection request will be handled by :".concat(String.valueOf(stringExtra)));
            m.a(context.getApplicationContext()).a(intent, stringExtra, true);
        } catch (d e) {
            Log.e(this.a, "SDK config init failed.".concat(String.valueOf(e)));
        }
    }
}
