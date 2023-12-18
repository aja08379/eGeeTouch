package com.google.android.gms.internal.p001firebaseauthapi;

import java.security.GeneralSecurityException;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzih  reason: invalid package */
/* loaded from: classes.dex */
public final class zzih extends zzgc {
    public zzih() {
        super(zzmt.class, new zzif(zzbm.class));
    }

    public static final void zzh(zzmt zzmtVar) throws GeneralSecurityException {
        zzqs.zzc(zzmtVar.zza(), 0);
        if (zzmtVar.zzg().zzd() < 16) {
            throw new GeneralSecurityException("key too short");
        }
        zzn(zzmtVar.zzf());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ zzga zzi(int i, int i2, int i3, int i4) {
        zzmv zzb = zzmw.zzb();
        zzmy zzb2 = zzmz.zzb();
        zzb2.zzb(i3);
        zzb2.zza(i2);
        zzb.zzb((zzmz) zzb2.zzi());
        zzb.zza(i);
        return new zzga((zzmw) zzb.zzi(), i4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void zzn(zzmz zzmzVar) throws GeneralSecurityException {
        if (zzmzVar.zza() < 10) {
            throw new GeneralSecurityException("tag size too small");
        }
        int zzf = zzmzVar.zzf() - 2;
        if (zzf == 1) {
            if (zzmzVar.zza() > 20) {
                throw new GeneralSecurityException("tag size too big");
            }
        } else if (zzf == 2) {
            if (zzmzVar.zza() > 48) {
                throw new GeneralSecurityException("tag size too big");
            }
        } else if (zzf == 3) {
            if (zzmzVar.zza() > 32) {
                throw new GeneralSecurityException("tag size too big");
            }
        } else if (zzf == 4) {
            if (zzmzVar.zza() > 64) {
                throw new GeneralSecurityException("tag size too big");
            }
        } else if (zzf == 5) {
            if (zzmzVar.zza() > 28) {
                throw new GeneralSecurityException("tag size too big");
            }
        } else {
            throw new GeneralSecurityException("unknown hash type");
        }
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzgc
    public final zzgb zza() {
        return new zzig(this, zzmw.class);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzgc
    public final zznr zzb() {
        return zznr.SYMMETRIC;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzgc
    public final /* synthetic */ zzaek zzc(zzacc zzaccVar) throws zzadn {
        return zzmt.zze(zzaccVar, zzacs.zza());
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzgc
    public final String zzd() {
        return "type.googleapis.com/google.crypto.tink.HmacKey";
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzgc
    public final /* bridge */ /* synthetic */ void zze(zzaek zzaekVar) throws GeneralSecurityException {
        zzh((zzmt) zzaekVar);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzgc
    public final int zzf() {
        return 2;
    }
}
