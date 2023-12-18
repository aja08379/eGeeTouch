package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.firebase.auth.internal.zzai;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzuc  reason: invalid package */
/* loaded from: classes.dex */
public final class zzuc implements zzyg {
    final /* synthetic */ String zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ zzxa zzc;
    final /* synthetic */ zzvf zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzuc(zzvf zzvfVar, String str, String str2, zzxa zzxaVar) {
        this.zzd = zzvfVar;
        this.zza = str;
        this.zzb = str2;
        this.zzc = zzxaVar;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzyf
    public final void zza(String str) {
        this.zzc.zzh(zzai.zza(str));
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzyg
    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        zzzy zzzyVar = (zzzy) obj;
        zzaao zzaaoVar = new zzaao();
        zzaaoVar.zze(zzzyVar.zze());
        zzaaoVar.zzd(this.zza);
        zzaaoVar.zzg(this.zzb);
        zzvf.zze(this.zzd, this.zzc, zzzyVar, zzaaoVar, this);
    }
}
