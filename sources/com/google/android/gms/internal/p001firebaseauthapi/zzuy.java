package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.firebase.auth.internal.zzai;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzuy  reason: invalid package */
/* loaded from: classes.dex */
public final class zzuy implements zzyg {
    final /* synthetic */ zzxa zza;
    final /* synthetic */ zzvf zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzuy(zzvf zzvfVar, zzxa zzxaVar) {
        this.zzb = zzvfVar;
        this.zza = zzxaVar;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzyf
    public final void zza(String str) {
        this.zza.zzh(zzai.zza(str));
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzyg
    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        zzaba zzabaVar = (zzaba) obj;
        if (!zzabaVar.zzm()) {
            zzvf.zzd(this.zzb, zzabaVar, this.zza, this);
        } else {
            this.zza.zzf(new zztm(zzabaVar.zzg(), zzabaVar.zzl(), zzabaVar.zzc()));
        }
    }
}
