package com.google.android.gms.internal.p001firebaseauthapi;

import java.security.GeneralSecurityException;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzed  reason: invalid package */
/* loaded from: classes.dex */
public final class zzed extends zzgw {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzed(Class cls) {
        super(cls);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzgw
    public final /* bridge */ /* synthetic */ Object zza(zzaek zzaekVar) throws GeneralSecurityException {
        zzmd zzmdVar = (zzmd) zzaekVar;
        zzma zzb = zzmdVar.zze().zzb();
        zzmj zze = zzb.zze();
        return new zzpt(zzpx.zzi(zzeo.zzc(zze.zzf()), zzmdVar.zzf().zzt()), zze.zzd().zzt(), zzeo.zzb(zze.zzg()), zzeo.zzd(zzb.zzh()), new zzep(zzb.zza().zzd()));
    }
}
