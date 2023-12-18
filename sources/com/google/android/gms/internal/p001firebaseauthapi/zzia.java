package com.google.android.gms.internal.p001firebaseauthapi;

import java.security.GeneralSecurityException;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzia  reason: invalid package */
/* loaded from: classes.dex */
final class zzia {
    public static final /* synthetic */ int zza = 0;
    private static final zzqv zzb;
    private static final zzgv zzc;
    private static final zzgr zzd;
    private static final zzfz zze;
    private static final zzfv zzf;

    static {
        zzqv zzb2 = zzhj.zzb("type.googleapis.com/google.crypto.tink.AesCmacKey");
        zzb = zzb2;
        zzc = zzgv.zza(new zzgt() { // from class: com.google.android.gms.internal.firebase-auth-api.zzhw
        }, zzhv.class, zzgz.class);
        zzd = zzgr.zza(new zzgp() { // from class: com.google.android.gms.internal.firebase-auth-api.zzhx
        }, zzb2, zzgz.class);
        zze = zzfz.zza(new zzfx() { // from class: com.google.android.gms.internal.firebase-auth-api.zzhy
        }, zzhn.class, zzgy.class);
        zzf = zzfv.zzb(new zzft() { // from class: com.google.android.gms.internal.firebase-auth-api.zzhz
            @Override // com.google.android.gms.internal.p001firebaseauthapi.zzft
            public final zzaw zza(zzha zzhaVar, zzca zzcaVar) {
                zzht zzhtVar;
                int i = zzia.zza;
                if (!((zzgy) zzhaVar).zzg().equals("type.googleapis.com/google.crypto.tink.AesCmacKey")) {
                    throw new IllegalArgumentException("Wrong type URL in call to AesCmacParameters.parseParameters");
                }
                try {
                    zzjn zzd2 = zzjn.zzd(((zzgy) zzhaVar).zze(), zzacs.zza());
                    if (zzd2.zza() == 0) {
                        zzhs zzhsVar = new zzhs(null);
                        zzhsVar.zza(zzd2.zzf().zzd());
                        zzhsVar.zzb(zzd2.zze().zza());
                        zzoy zzc2 = ((zzgy) zzhaVar).zzc();
                        zzoy zzoyVar = zzoy.UNKNOWN_PREFIX;
                        int ordinal = zzc2.ordinal();
                        if (ordinal == 1) {
                            zzhtVar = zzht.zza;
                        } else if (ordinal == 2) {
                            zzhtVar = zzht.zzc;
                        } else if (ordinal == 3) {
                            zzhtVar = zzht.zzd;
                        } else if (ordinal != 4) {
                            int zza2 = zzc2.zza();
                            throw new GeneralSecurityException("Unable to parse OutputPrefixType: " + zza2);
                        } else {
                            zzhtVar = zzht.zzb;
                        }
                        zzhsVar.zzc(zzhtVar);
                        zzhv zzd3 = zzhsVar.zzd();
                        zzhl zzhlVar = new zzhl(null);
                        zzhlVar.zzc(zzd3);
                        zzhlVar.zza(zzqw.zzb(zzd2.zzf().zzt(), zzcaVar));
                        zzhlVar.zzb(((zzgy) zzhaVar).zzf());
                        return zzhlVar.zzd();
                    }
                    throw new GeneralSecurityException("Only version 0 keys are accepted");
                } catch (zzadn | IllegalArgumentException unused) {
                    throw new GeneralSecurityException("Parsing AesCmacKey failed");
                }
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
