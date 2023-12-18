package com.google.android.gms.internal.p001firebaseauthapi;

import java.security.GeneralSecurityException;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzef  reason: invalid package */
/* loaded from: classes.dex */
public final class zzef extends zzgx {
    private static final byte[] zza = new byte[0];

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzef() {
        super(zzmd.class, zzmg.class, new zzed(zzau.class));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ zzga zzi(int i, int i2, int i3, zzbf zzbfVar, byte[] bArr, int i4) {
        zzoy zzoyVar;
        zzlw zza2 = zzlx.zza();
        zzmi zza3 = zzmj.zza();
        zza3.zzb(4);
        zza3.zzc(5);
        zza3.zza(zzacc.zzn(bArr));
        zzmj zzmjVar = (zzmj) zza3.zzi();
        zznw zza4 = zznx.zza();
        zza4.zzb(zzbfVar.zzb());
        zza4.zzc(zzacc.zzn(zzbfVar.zzc()));
        int zzd = zzbfVar.zzd() - 1;
        if (zzd == 0) {
            zzoyVar = zzoy.TINK;
        } else if (zzd == 1) {
            zzoyVar = zzoy.LEGACY;
        } else if (zzd != 2) {
            zzoyVar = zzoy.CRUNCHY;
        } else {
            zzoyVar = zzoy.RAW;
        }
        zza4.zza(zzoyVar);
        zzlt zza5 = zzlu.zza();
        zza5.zza((zznx) zza4.zzi());
        zzlz zzb = zzma.zzb();
        zzb.zzb(zzmjVar);
        zzb.zza((zzlu) zza5.zzi());
        zzb.zzc(i3);
        zza2.zza((zzma) zzb.zzi());
        return new zzga((zzlx) zza2.zzi(), i4);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzgc
    public final zzgb zza() {
        return new zzee(this, zzlx.class);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzgc
    public final zznr zzb() {
        return zznr.ASYMMETRIC_PRIVATE;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzgc
    public final /* synthetic */ zzaek zzc(zzacc zzaccVar) throws zzadn {
        return zzmd.zzd(zzaccVar, zzacs.zza());
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzgc
    public final String zzd() {
        return "type.googleapis.com/google.crypto.tink.EciesAeadHkdfPrivateKey";
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzgc
    public final /* bridge */ /* synthetic */ void zze(zzaek zzaekVar) throws GeneralSecurityException {
        zzmd zzmdVar = (zzmd) zzaekVar;
        if (zzmdVar.zzf().zzs()) {
            throw new GeneralSecurityException("invalid ECIES private key");
        }
        zzqs.zzc(zzmdVar.zza(), 0);
        zzeo.zza(zzmdVar.zze().zzb());
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzgx
    public final /* synthetic */ zzaek zzg(zzaek zzaekVar) throws GeneralSecurityException {
        return ((zzmd) zzaekVar).zze();
    }
}
