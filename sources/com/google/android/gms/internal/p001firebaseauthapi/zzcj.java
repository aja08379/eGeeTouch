package com.google.android.gms.internal.p001firebaseauthapi;

import java.security.GeneralSecurityException;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzcj  reason: invalid package */
/* loaded from: classes.dex */
public final class zzcj extends zzgc {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzcj() {
        super(zzjw.class, new zzch(zzap.class));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ zzga zzg(int i, int i2, int i3, int i4, int i5, int i6) {
        zzke zzb = zzkf.zzb();
        zzkh zzb2 = zzki.zzb();
        zzb2.zza(16);
        zzb.zzb((zzki) zzb2.zzi());
        zzb.zza(i);
        zzmv zzb3 = zzmw.zzb();
        zzmy zzb4 = zzmz.zzb();
        zzb4.zzb(5);
        zzb4.zza(i4);
        zzb3.zzb((zzmz) zzb4.zzi());
        zzb3.zza(32);
        zzjy zza = zzjz.zza();
        zza.zza((zzkf) zzb.zzi());
        zza.zzb((zzmw) zzb3.zzi());
        return new zzga((zzjz) zza.zzi(), i6);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzgc
    public final zzgb zza() {
        return new zzci(this, zzjz.class);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzgc
    public final zznr zzb() {
        return zznr.SYMMETRIC;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzgc
    public final /* synthetic */ zzaek zzc(zzacc zzaccVar) throws zzadn {
        return zzjw.zzd(zzaccVar, zzacs.zza());
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzgc
    public final String zzd() {
        return "type.googleapis.com/google.crypto.tink.AesCtrHmacAeadKey";
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzgc
    public final /* bridge */ /* synthetic */ void zze(zzaek zzaekVar) throws GeneralSecurityException {
        zzjw zzjwVar = (zzjw) zzaekVar;
        zzqs.zzc(zzjwVar.zza(), 0);
        new zzcm();
        zzcm.zzh(zzjwVar.zze());
        new zzih();
        zzih.zzh(zzjwVar.zzf());
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzgc
    public final int zzf() {
        return 2;
    }
}
