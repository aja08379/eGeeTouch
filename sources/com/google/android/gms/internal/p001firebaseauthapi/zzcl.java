package com.google.android.gms.internal.p001firebaseauthapi;

import java.security.GeneralSecurityException;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzcl  reason: invalid package */
/* loaded from: classes.dex */
final class zzcl extends zzgb {
    final /* synthetic */ zzcm zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzcl(zzcm zzcmVar, Class cls) {
        super(cls);
        this.zza = zzcmVar;
    }

    public static final zzkc zzf(zzkf zzkfVar) throws GeneralSecurityException {
        zzkb zzb = zzkc.zzb();
        zzb.zzb(zzkfVar.zzf());
        zzb.zza(zzacc.zzn(zzqq.zza(zzkfVar.zza())));
        zzb.zzc(0);
        return (zzkc) zzb.zzi();
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzgb
    public final /* bridge */ /* synthetic */ zzaek zza(zzaek zzaekVar) throws GeneralSecurityException {
        return zzf((zzkf) zzaekVar);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzgb
    public final /* synthetic */ zzaek zzb(zzacc zzaccVar) throws zzadn {
        return zzkf.zze(zzaccVar, zzacs.zza());
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzgb
    /* renamed from: zze */
    public final void zzd(zzkf zzkfVar) throws GeneralSecurityException {
        zzqs.zzb(zzkfVar.zza());
        zzcm zzcmVar = this.zza;
        zzcm.zzi(zzkfVar.zzf());
    }
}
