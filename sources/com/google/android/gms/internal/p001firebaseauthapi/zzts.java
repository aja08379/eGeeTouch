package com.google.android.gms.internal.p001firebaseauthapi;

import java.util.List;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzts  reason: invalid package */
/* loaded from: classes.dex */
public final class zzts implements zzyg {
    final /* synthetic */ zzyf zza;
    final /* synthetic */ zzxa zzb;
    final /* synthetic */ zzzy zzc;
    final /* synthetic */ zzaao zzd;
    final /* synthetic */ zzvf zze;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzts(zzvf zzvfVar, zzyf zzyfVar, zzxa zzxaVar, zzzy zzzyVar, zzaao zzaaoVar) {
        this.zze = zzvfVar;
        this.zza = zzyfVar;
        this.zzb = zzxaVar;
        this.zzc = zzzyVar;
        this.zzd = zzaaoVar;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzyf
    public final void zza(String str) {
        this.zza.zza(str);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzyg
    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        List zzb = ((zzzp) obj).zzb();
        if (zzb == null || zzb.isEmpty()) {
            this.zza.zza("No users");
        } else {
            zzvf.zzf(this.zze, this.zzb, this.zzc, (zzzr) zzb.get(0), this.zzd, this.zza);
        }
    }
}
