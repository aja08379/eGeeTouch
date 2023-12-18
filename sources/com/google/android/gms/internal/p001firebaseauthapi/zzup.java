package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.firebase.auth.internal.zzai;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzup  reason: invalid package */
/* loaded from: classes.dex */
public final class zzup implements zzyg {
    final /* synthetic */ zzzi zza;
    final /* synthetic */ zzxa zzb;
    final /* synthetic */ zzvf zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzup(zzvf zzvfVar, zzzi zzziVar, zzxa zzxaVar) {
        this.zzc = zzvfVar;
        this.zza = zzziVar;
        this.zzb = zzxaVar;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzyf
    public final void zza(String str) {
        this.zzb.zzh(zzai.zza(str));
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzyg
    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        zzyh zzyhVar;
        this.zza.zzc(((zzzy) obj).zze());
        zzyhVar = this.zzc.zza;
        zzyhVar.zzd(this.zza, new zzuo(this));
    }
}
