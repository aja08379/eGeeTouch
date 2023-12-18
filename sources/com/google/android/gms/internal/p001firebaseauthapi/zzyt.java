package com.google.android.gms.internal.p001firebaseauthapi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.logging.Logger;
import java.util.HashMap;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzyt  reason: invalid package */
/* loaded from: classes.dex */
public final class zzyt extends BroadcastReceiver {
    final /* synthetic */ zzyv zza;
    private final String zzb;

    public zzyt(zzyv zzyvVar, String str) {
        this.zza = zzyvVar;
        this.zzb = str;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        HashMap hashMap;
        Logger logger;
        Logger logger2;
        if (SmsRetriever.SMS_RETRIEVED_ACTION.equals(intent.getAction())) {
            Bundle extras = intent.getExtras();
            if (((Status) extras.get("com.google.android.gms.auth.api.phone.EXTRA_STATUS")).getStatusCode() == 0) {
                String str = (String) extras.get(SmsRetriever.EXTRA_SMS_MESSAGE);
                hashMap = this.zza.zzd;
                zzyu zzyuVar = (zzyu) hashMap.get(this.zzb);
                if (zzyuVar == null) {
                    logger2 = zzyv.zza;
                    logger2.e("Verification code received with no active retrieval session.", new Object[0]);
                } else {
                    zzyuVar.zze = zzyv.zzb(str);
                    if (zzyuVar.zze == null) {
                        logger = zzyv.zza;
                        logger.e("Unable to extract verification code.", new Object[0]);
                    } else if (!zzag.zzd(zzyuVar.zzd)) {
                        zzyv.zze(this.zza, this.zzb);
                    }
                }
            }
            context.getApplicationContext().unregisterReceiver(this);
        }
    }
}
