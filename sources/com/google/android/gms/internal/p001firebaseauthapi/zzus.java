package com.google.android.gms.internal.p001firebaseauthapi;

import java.util.List;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzus  reason: invalid package */
/* loaded from: classes.dex */
final class zzus implements zzyg {
    final /* synthetic */ zzyg zza;
    final /* synthetic */ zzzy zzb;
    final /* synthetic */ zzut zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzus(zzut zzutVar, zzyg zzygVar, zzzy zzzyVar) {
        this.zzc = zzutVar;
        this.zza = zzygVar;
        this.zzb = zzzyVar;
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
            this.zzc.zza.zzi(this.zzb, (zzzr) zzb.get(0));
        }
    }
}
