package com.samsung.android.sdk.accessoryfiletransfer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
/* loaded from: classes2.dex */
public class SAFileTransferIncomingRequestReceiver extends BroadcastReceiver {
    private String a = "[SA_SDK]SAFileTransferIncomingRequestReceiver";

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        Log.d(this.a, "onReceive");
        if (intent == null || intent.getAction() == null || !intent.getAction().equalsIgnoreCase(SAFileTransfer.ACTION_SAP_FILE_TRANSFER_REQUESTED)) {
            return;
        }
        Log.i(this.a, "Intent action is " + intent.getAction());
        intent.setAction("com.samsung.accessory.ftconnection.internal");
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
}
