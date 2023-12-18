package com.samsung.android.sdk.accessory;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
/* loaded from: classes2.dex */
public final class MessageReceiver extends BroadcastReceiver {
    private String a = "[SA_SDK]" + MessageReceiver.class.getSimpleName();

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        if (intent == null || intent.getAction() == null) {
            Log.d(this.a, "received null intent!");
        } else if (SAMessage.ACTION_ACCESSORY_MESSAGE_RECEIVED.equalsIgnoreCase(intent.getAction())) {
            Log.d(this.a, "Incoming Data Received!!!");
            try {
                new k(context);
                String stringExtra = intent.getStringExtra("agentImplclass");
                if (stringExtra == null) {
                    Log.e(this.a, "Impl class not available in intent. ignoring message received");
                } else {
                    m.a(context.getApplicationContext()).a(intent, stringExtra, true);
                }
            } catch (d e) {
                Log.e(this.a, "SDK config initialization failed.".concat(String.valueOf(e)));
            }
        }
    }
}
