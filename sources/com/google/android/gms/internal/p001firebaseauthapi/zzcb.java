package com.google.android.gms.internal.p001firebaseauthapi;

import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzcb  reason: invalid package */
/* loaded from: classes.dex */
final class zzcb {
    public static final Charset zza = Charset.forName("UTF-8");

    public static zzok zza(zzof zzofVar) {
        zzoh zza2 = zzok.zza();
        zza2.zzb(zzofVar.zzb());
        for (zzoe zzoeVar : zzofVar.zzg()) {
            zzoi zzb = zzoj.zzb();
            zzb.zzc(zzoeVar.zzb().zzf());
            zzb.zzd(zzoeVar.zzk());
            zzb.zzb(zzoeVar.zze());
            zzb.zza(zzoeVar.zza());
            zza2.zza((zzoj) zzb.zzi());
        }
        return (zzok) zza2.zzi();
    }

    public static void zzb(zzof zzofVar) throws GeneralSecurityException {
        int zzb = zzofVar.zzb();
        int i = 0;
        boolean z = false;
        boolean z2 = true;
        for (zzoe zzoeVar : zzofVar.zzg()) {
            if (zzoeVar.zzk() == 3) {
                if (zzoeVar.zzi()) {
                    if (zzoeVar.zze() != zzoy.UNKNOWN_PREFIX) {
                        if (zzoeVar.zzk() != 2) {
                            if (zzoeVar.zza() == zzb) {
                                if (z) {
                                    throw new GeneralSecurityException("keyset contains multiple primary keys");
                                }
                                z = true;
                            }
                            z2 &= zzoeVar.zzb().zzb() == zznr.ASYMMETRIC_PUBLIC;
                            i++;
                        } else {
                            throw new GeneralSecurityException(String.format("key %d has unknown status", Integer.valueOf(zzoeVar.zza())));
                        }
                    } else {
                        throw new GeneralSecurityException(String.format("key %d has unknown prefix", Integer.valueOf(zzoeVar.zza())));
                    }
                } else {
                    throw new GeneralSecurityException(String.format("key %d has no key data", Integer.valueOf(zzoeVar.zza())));
                }
            }
        }
        if (i == 0) {
            throw new GeneralSecurityException("keyset must contain at least one ENABLED key");
        }
        if (!z && !z2) {
            throw new GeneralSecurityException("keyset doesn't contain a valid primary key");
        }
    }
}
