package com.google.android.gms.internal.p001firebaseauthapi;

import java.security.GeneralSecurityException;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzfc  reason: invalid package */
/* loaded from: classes.dex */
public final class zzfc extends zzgx {
    public zzfc() {
        super(zznk.class, zznn.class, new zzfa(zzau.class));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ zzga zzh(int i, int i2, int i3, int i4) {
        zzng zza = zznh.zza();
        zza.zzc(i);
        zza.zzb(i2);
        zza.zza(i3);
        zznd zza2 = zzne.zza();
        zza2.zza((zznh) zza.zzi());
        return new zzga((zzne) zza2.zzi(), i4);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzgc
    public final zzgb zza() {
        return new zzfb(this, zzne.class);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzgc
    public final zznr zzb() {
        return zznr.ASYMMETRIC_PRIVATE;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzgc
    public final /* synthetic */ zzaek zzc(zzacc zzaccVar) throws zzadn {
        return zznk.zzd(zzaccVar, zzacs.zza());
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzgc
    public final String zzd() {
        return "type.googleapis.com/google.crypto.tink.HpkePrivateKey";
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzgc
    public final /* bridge */ /* synthetic */ void zze(zzaek zzaekVar) throws GeneralSecurityException {
        zznk zznkVar = (zznk) zzaekVar;
        if (zznkVar.zzf().zzs()) {
            throw new GeneralSecurityException("Private key is empty.");
        }
        if (!zznkVar.zzk()) {
            throw new GeneralSecurityException("Missing public key.");
        }
        zzqs.zzc(zznkVar.zza(), 0);
        zzff.zza(zznkVar.zze().zzb());
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzgx
    public final /* synthetic */ zzaek zzg(zzaek zzaekVar) throws GeneralSecurityException {
        return ((zznk) zzaekVar).zze();
    }
}
