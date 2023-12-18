package com.google.android.gms.internal.p001firebaseauthapi;

import java.security.GeneralSecurityException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzes  reason: invalid package */
/* loaded from: classes.dex */
final class zzes {
    private final String zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzes(String str) {
        this.zza = str;
    }

    private final byte[] zzf(byte[] bArr, byte[] bArr2, int i) throws GeneralSecurityException {
        Mac mac = (Mac) zzpz.zzb.zza(this.zza);
        if (i > mac.getMacLength() * 255) {
            throw new GeneralSecurityException("size too large");
        }
        byte[] bArr3 = new byte[i];
        mac.init(new SecretKeySpec(bArr, this.zza));
        byte[] bArr4 = new byte[0];
        int i2 = 1;
        int i3 = 0;
        while (true) {
            mac.update(bArr4);
            mac.update(bArr2);
            mac.update((byte) i2);
            bArr4 = mac.doFinal();
            int length = bArr4.length;
            int i4 = i3 + length;
            if (i4 < i) {
                System.arraycopy(bArr4, 0, bArr3, i3, length);
                i2++;
                i3 = i4;
            } else {
                System.arraycopy(bArr4, 0, bArr3, i3, i - i3);
                return bArr3;
            }
        }
    }

    private final byte[] zzg(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        Mac mac = (Mac) zzpz.zzb.zza(this.zza);
        if (bArr2 == null || bArr2.length == 0) {
            mac.init(new SecretKeySpec(new byte[mac.getMacLength()], this.zza));
        } else {
            mac.init(new SecretKeySpec(bArr2, this.zza));
        }
        return mac.doFinal(bArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int zza() throws GeneralSecurityException {
        return Mac.getInstance(this.zza).getMacLength();
    }

    public final byte[] zzb(byte[] bArr, byte[] bArr2, String str, byte[] bArr3, String str2, byte[] bArr4, int i) throws GeneralSecurityException {
        return zzf(zzg(zzff.zze("eae_prk", bArr2, bArr4), null), zzff.zzf("shared_secret", bArr3, bArr4, i), i);
    }

    public final byte[] zzd(byte[] bArr, byte[] bArr2, String str, byte[] bArr3, int i) throws GeneralSecurityException {
        return zzf(bArr, zzff.zzf(str, bArr2, bArr3, i), i);
    }

    public final byte[] zze(byte[] bArr, byte[] bArr2, String str, byte[] bArr3) throws GeneralSecurityException {
        return zzg(zzff.zze(str, bArr2, bArr3), bArr);
    }

    public final byte[] zzc() throws GeneralSecurityException {
        char c;
        String str = this.zza;
        int hashCode = str.hashCode();
        if (hashCode == 984523022) {
            if (str.equals("HmacSha256")) {
                c = 0;
            }
            c = 65535;
        } else if (hashCode != 984524074) {
            if (hashCode == 984525777 && str.equals("HmacSha512")) {
                c = 2;
            }
            c = 65535;
        } else {
            if (str.equals("HmacSha384")) {
                c = 1;
            }
            c = 65535;
        }
        if (c != 0) {
            if (c != 1) {
                if (c == 2) {
                    return zzff.zzh;
                }
                throw new GeneralSecurityException("Could not determine HPKE KDF ID");
            }
            return zzff.zzg;
        }
        return zzff.zzf;
    }
}
