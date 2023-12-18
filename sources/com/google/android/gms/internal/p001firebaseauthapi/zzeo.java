package com.google.android.gms.internal.p001firebaseauthapi;

import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzeo  reason: invalid package */
/* loaded from: classes.dex */
final class zzeo {
    public static void zza(zzma zzmaVar) throws GeneralSecurityException {
        zzpx.zzl(zzc(zzmaVar.zze().zzf()));
        zzb(zzmaVar.zze().zzg());
        if (zzmaVar.zzh() == 2) {
            throw new GeneralSecurityException("unknown EC point format");
        }
        zzbz.zzc(zzmaVar.zza().zzd());
    }

    public static String zzb(int i) throws NoSuchAlgorithmException {
        int i2 = i - 2;
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 != 3) {
                    if (i2 != 4) {
                        if (i2 == 5) {
                            return "HmacSha224";
                        }
                        throw new NoSuchAlgorithmException("hash unsupported for HMAC: ".concat(Integer.toString(zzmq.zza(i))));
                    }
                    return "HmacSha512";
                }
                return "HmacSha256";
            }
            return "HmacSha384";
        }
        return "HmacSha1";
    }

    public static int zzc(int i) throws GeneralSecurityException {
        int i2 = i - 2;
        if (i2 != 2) {
            if (i2 != 3) {
                if (i2 == 4) {
                    return 3;
                }
                throw new GeneralSecurityException("unknown curve type: ".concat(Integer.toString(zzml.zza(i))));
            }
            return 2;
        }
        return 1;
    }

    public static int zzd(int i) throws GeneralSecurityException {
        int i2 = i - 2;
        int i3 = 1;
        if (i2 != 1) {
            i3 = 2;
            if (i2 != 2) {
                if (i2 == 3) {
                    return 3;
                }
                throw new GeneralSecurityException("unknown point format: ".concat(Integer.toString(zzlr.zza(i))));
            }
        }
        return i3;
    }
}
