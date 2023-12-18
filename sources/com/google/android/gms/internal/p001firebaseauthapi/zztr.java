package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.firebase.auth.internal.zzai;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zztr  reason: invalid package */
/* loaded from: classes.dex */
public final class zztr implements zzyg {
    final /* synthetic */ zzxa zza;
    final /* synthetic */ zzvf zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zztr(zzvf zzvfVar, zzxa zzxaVar) {
        this.zzb = zzvfVar;
        this.zza = zzxaVar;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzyf
    public final void zza(String str) {
        this.zza.zzh(zzai.zza(str));
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzyg
    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        zzzh zzzhVar = (zzzh) obj;
        if (zzzhVar.zzg()) {
            this.zza.zzf(new zztm(zzzhVar.zzd(), zzzhVar.zzf(), null));
            return;
        }
        this.zzb.zzO(new zzzy(zzzhVar.zze(), zzzhVar.zzc(), Long.valueOf(zzzhVar.zzb()), "Bearer"), null, null, Boolean.valueOf(zzzhVar.zzh()), null, this.zza, this);
    }
}
