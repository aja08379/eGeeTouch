package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.logging.Logger;
import java.util.HashMap;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzys  reason: invalid package */
/* loaded from: classes.dex */
final class zzys extends zzxa {
    final /* synthetic */ zzyv zza;
    private final String zzb;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzys(zzyv zzyvVar, zzxa zzxaVar, String str) {
        super(zzxaVar);
        this.zza = zzyvVar;
        this.zzb = str;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzxa
    public final void zzb(String str) {
        Logger logger;
        HashMap hashMap;
        logger = zzyv.zza;
        logger.d("onCodeSent", new Object[0]);
        hashMap = this.zza.zzd;
        zzyu zzyuVar = (zzyu) hashMap.get(this.zzb);
        if (zzyuVar == null) {
            return;
        }
        for (zzxa zzxaVar : zzyuVar.zzb) {
            zzxaVar.zzb(str);
        }
        zzyuVar.zzg = true;
        zzyuVar.zzd = str;
        if (zzyuVar.zza <= 0) {
            this.zza.zzh(this.zzb);
        } else if (!zzyuVar.zzc) {
            this.zza.zzn(this.zzb);
        } else if (!zzag.zzd(zzyuVar.zze)) {
            zzyv.zze(this.zza, this.zzb);
        }
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzxa
    public final void zzh(Status status) {
        Logger logger;
        HashMap hashMap;
        logger = zzyv.zza;
        String statusCodeString = CommonStatusCodes.getStatusCodeString(status.getStatusCode());
        String statusMessage = status.getStatusMessage();
        logger.e("SMS verification code request failed: " + statusCodeString + " " + statusMessage, new Object[0]);
        hashMap = this.zza.zzd;
        zzyu zzyuVar = (zzyu) hashMap.get(this.zzb);
        if (zzyuVar == null) {
            return;
        }
        for (zzxa zzxaVar : zzyuVar.zzb) {
            zzxaVar.zzh(status);
        }
        this.zza.zzj(this.zzb);
    }
}
