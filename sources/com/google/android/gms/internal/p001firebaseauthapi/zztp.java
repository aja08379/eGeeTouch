package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.firebase.auth.internal.zzai;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zztp  reason: invalid package */
/* loaded from: classes.dex */
public final class zztp implements zzyg {
    final /* synthetic */ zzxa zza;
    final /* synthetic */ zzvf zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zztp(zzvf zzvfVar, zzxa zzxaVar) {
        this.zzb = zzvfVar;
        this.zza = zzxaVar;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzyf
    public final void zza(String str) {
        this.zza.zzh(zzai.zza(str));
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzyg
    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        zzabf zzabfVar = (zzabf) obj;
        if (zzabfVar.zzg()) {
            this.zza.zzf(new zztm(zzabfVar.zzd(), zzabfVar.zzf(), null));
            return;
        }
        this.zzb.zzO(new zzzy(zzabfVar.zze(), zzabfVar.zzc(), Long.valueOf(zzabfVar.zzb()), "Bearer"), null, null, false, null, this.zza, this);
    }
}
