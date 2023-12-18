package com.google.android.gms.internal.p001firebaseauthapi;

import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzql  reason: invalid package */
/* loaded from: classes.dex */
public final class zzql implements zzjk {
    private final SecretKey zza;
    private final byte[] zzb;
    private final byte[] zzc;

    public zzql(byte[] bArr) throws GeneralSecurityException {
        zzqs.zzb(bArr.length);
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, "AES");
        this.zza = secretKeySpec;
        Cipher zzb = zzb();
        zzb.init(1, secretKeySpec);
        byte[] zzb2 = zziz.zzb(zzb.doFinal(new byte[16]));
        this.zzb = zzb2;
        this.zzc = zziz.zzb(zzb2);
    }

    private static Cipher zzb() throws GeneralSecurityException {
        if (!zzdv.zza(1)) {
            throw new GeneralSecurityException("Can not use AES-CMAC in FIPS-mode.");
        }
        return (Cipher) zzpz.zza.zza("AES/ECB/NoPadding");
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzjk
    public final byte[] zza(byte[] bArr, int i) throws GeneralSecurityException {
        byte[] zzd;
        if (i > 16) {
            throw new InvalidAlgorithmParameterException("outputLength too large, max is 16 bytes");
        }
        Cipher zzb = zzb();
        zzb.init(1, this.zza);
        int length = bArr.length;
        int max = Math.max(1, (int) Math.ceil(length / 16.0d));
        if (max * 16 == length) {
            zzd = zzpp.zze(bArr, (max - 1) * 16, this.zzb, 0, 16);
        } else {
            zzd = zzpp.zzd(zziz.zza(Arrays.copyOfRange(bArr, (max - 1) * 16, length)), this.zzc);
        }
        byte[] bArr2 = new byte[16];
        for (int i2 = 0; i2 < max - 1; i2++) {
            bArr2 = zzb.doFinal(zzpp.zze(bArr2, 0, bArr, i2 * 16, 16));
        }
        return Arrays.copyOf(zzb.doFinal(zzpp.zzd(zzd, bArr2)), i);
    }
}
