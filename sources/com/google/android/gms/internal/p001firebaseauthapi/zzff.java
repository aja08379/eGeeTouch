package com.google.android.gms.internal.p001firebaseauthapi;

import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzff  reason: invalid package */
/* loaded from: classes.dex */
public final class zzff {
    public static final byte[] zza = zzc(1, 0);
    public static final byte[] zzb = zzc(2, 32);
    public static final byte[] zzc = zzc(2, 16);
    public static final byte[] zzd = zzc(2, 17);
    public static final byte[] zze = zzc(2, 18);
    public static final byte[] zzf = zzc(2, 1);
    public static final byte[] zzg = zzc(2, 2);
    public static final byte[] zzh = zzc(2, 3);
    public static final byte[] zzi = zzc(2, 1);
    public static final byte[] zzj = zzc(2, 2);
    public static final byte[] zzk = zzc(2, 3);
    public static final byte[] zzl = new byte[0];
    private static final byte[] zzm = "KEM".getBytes(StandardCharsets.UTF_8);
    private static final byte[] zzn = "HPKE".getBytes(StandardCharsets.UTF_8);
    private static final byte[] zzo = "HPKE-v1".getBytes(StandardCharsets.UTF_8);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zza(zznh zznhVar) throws GeneralSecurityException {
        if (zznhVar.zzf() == 2 || zznhVar.zzf() == 1) {
            throw new GeneralSecurityException("Invalid KEM param: ".concat(zznb.zza(zznhVar.zzf())));
        }
        String str = "UNRECOGNIZED";
        if (zznhVar.zze() == 2 || zznhVar.zze() == 1) {
            int zze2 = zznhVar.zze();
            if (zze2 == 2) {
                str = "KDF_UNKNOWN";
            } else if (zze2 == 3) {
                str = "HKDF_SHA256";
            } else if (zze2 == 4) {
                str = "HKDF_SHA384";
            } else if (zze2 == 5) {
                str = "HKDF_SHA512";
            }
            throw new GeneralSecurityException("Invalid KDF param: ".concat(str));
        } else if (zznhVar.zzd() == 2 || zznhVar.zzd() == 1) {
            int zzd2 = zznhVar.zzd();
            if (zzd2 == 2) {
                str = "AEAD_UNKNOWN";
            } else if (zzd2 == 3) {
                str = "AES_128_GCM";
            } else if (zzd2 == 4) {
                str = "AES_256_GCM";
            } else if (zzd2 == 5) {
                str = "CHACHA20_POLY1305";
            }
            throw new GeneralSecurityException("Invalid AEAD param: ".concat(str));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] zzb(byte[] bArr, byte[] bArr2, byte[] bArr3) throws GeneralSecurityException {
        return zzpp.zzc(zzn, bArr, bArr2, bArr3);
    }

    public static byte[] zzc(int i, int i2) {
        byte[] bArr = new byte[i];
        for (int i3 = 0; i3 < i; i3++) {
            bArr[i3] = (byte) ((i2 >> (((i - i3) - 1) * 8)) & 255);
        }
        return bArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] zzd(byte[] bArr) throws GeneralSecurityException {
        return zzpp.zzc(zzm, bArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] zze(String str, byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        return zzpp.zzc(zzo, bArr2, str.getBytes(StandardCharsets.UTF_8), bArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] zzf(String str, byte[] bArr, byte[] bArr2, int i) throws GeneralSecurityException {
        return zzpp.zzc(zzc(2, i), zzo, bArr2, str.getBytes(StandardCharsets.UTF_8), bArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzg(int i) throws GeneralSecurityException {
        int i2 = i - 2;
        if (i2 != 2) {
            if (i2 != 3) {
                if (i2 == 4) {
                    return 3;
                }
                throw new GeneralSecurityException("Unrecognized NIST HPKE KEM identifier");
            }
            return 2;
        }
        return 1;
    }
}
