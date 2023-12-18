package com.google.android.gms.internal.p001firebaseauthapi;

import java.security.GeneralSecurityException;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzis  reason: invalid package */
/* loaded from: classes.dex */
final class zzis {
    public static final /* synthetic */ int zza = 0;
    private static final zzqv zzb;
    private static final zzgv zzc;
    private static final zzgr zzd;
    private static final zzfz zze;
    private static final zzfv zzf;

    static {
        zzqv zzb2 = zzhj.zzb("type.googleapis.com/google.crypto.tink.HmacKey");
        zzb = zzb2;
        zzc = zzgv.zza(new zzgt() { // from class: com.google.android.gms.internal.firebase-auth-api.zzio
        }, zzin.class, zzgz.class);
        zzd = zzgr.zza(new zzgp() { // from class: com.google.android.gms.internal.firebase-auth-api.zzip
        }, zzb2, zzgz.class);
        zze = zzfz.zza(new zzfx() { // from class: com.google.android.gms.internal.firebase-auth-api.zziq
        }, zzie.class, zzgy.class);
        zzf = zzfv.zzb(new zzft() { // from class: com.google.android.gms.internal.firebase-auth-api.zzir
            @Override // com.google.android.gms.internal.p001firebaseauthapi.zzft
            public final zzaw zza(zzha zzhaVar, zzca zzcaVar) {
                zzik zzikVar;
                zzil zzilVar;
                int i = zzis.zza;
                if (((zzgy) zzhaVar).zzg().equals("type.googleapis.com/google.crypto.tink.HmacKey")) {
                    try {
                        zzmt zze2 = zzmt.zze(((zzgy) zzhaVar).zze(), zzacs.zza());
                        if (zze2.zza() == 0) {
                            zzij zzijVar = new zzij(null);
                            zzijVar.zzb(zze2.zzg().zzd());
                            zzijVar.zzc(zze2.zzf().zza());
                            int zzf2 = zze2.zzf().zzf();
                            zzoy zzoyVar = zzoy.UNKNOWN_PREFIX;
                            int i2 = zzf2 - 2;
                            if (i2 == 1) {
                                zzikVar = zzik.zza;
                            } else if (i2 == 2) {
                                zzikVar = zzik.zzd;
                            } else if (i2 == 3) {
                                zzikVar = zzik.zzc;
                            } else if (i2 == 4) {
                                zzikVar = zzik.zze;
                            } else if (i2 != 5) {
                                throw new GeneralSecurityException("Unable to parse HashType: " + zzmq.zza(zzf2));
                            } else {
                                zzikVar = zzik.zzb;
                            }
                            zzijVar.zza(zzikVar);
                            zzoy zzc2 = ((zzgy) zzhaVar).zzc();
                            int ordinal = zzc2.ordinal();
                            if (ordinal == 1) {
                                zzilVar = zzil.zza;
                            } else if (ordinal == 2) {
                                zzilVar = zzil.zzc;
                            } else if (ordinal == 3) {
                                zzilVar = zzil.zzd;
                            } else if (ordinal != 4) {
                                throw new GeneralSecurityException("Unable to parse OutputPrefixType: " + zzc2.zza());
                            } else {
                                zzilVar = zzil.zzb;
                            }
                            zzijVar.zzd(zzilVar);
                            zzin zze3 = zzijVar.zze();
                            zzic zzicVar = new zzic(null);
                            zzicVar.zzc(zze3);
                            zzicVar.zzb(zzqw.zzb(zze2.zzg().zzt(), zzcaVar));
                            zzicVar.zza(((zzgy) zzhaVar).zzf());
                            return zzicVar.zzd();
                        }
                        throw new GeneralSecurityException("Only version 0 keys are accepted");
                    } catch (zzadn | IllegalArgumentException unused) {
                        throw new GeneralSecurityException("Parsing HmacKey failed");
                    }
                }
                throw new IllegalArgumentException("Wrong type URL in call to HmacParameters.parseParameters");
            }
        }, zzb2, zzgy.class);
    }

    public static void zza() throws GeneralSecurityException {
        zzgn zzb2 = zzgn.zzb();
        zzb2.zzf(zzc);
        zzb2.zze(zzd);
        zzb2.zzd(zze);
        zzb2.zzc(zzf);
    }
}
