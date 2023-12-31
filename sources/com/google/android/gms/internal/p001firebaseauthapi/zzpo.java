package com.google.android.gms.internal.p001firebaseauthapi;

import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.util.Arrays;
import java.util.Collection;
import javax.crypto.AEADBadTagException;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import kotlin.jvm.internal.ByteCompanionObject;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzpo  reason: invalid package */
/* loaded from: classes.dex */
public final class zzpo implements zzat {
    private static final Collection zza = Arrays.asList(64);
    private static final byte[] zzb = new byte[16];
    private final zzql zzc;
    private final byte[] zzd;

    public zzpo(byte[] bArr) throws GeneralSecurityException {
        if (zzdv.zza(1)) {
            Collection collection = zza;
            int length = bArr.length;
            if (!collection.contains(Integer.valueOf(length))) {
                throw new InvalidKeyException("invalid key size: " + length + " bytes; key must have 64 bytes");
            }
            int i = length >> 1;
            byte[] copyOfRange = Arrays.copyOfRange(bArr, 0, i);
            this.zzd = Arrays.copyOfRange(bArr, i, length);
            this.zzc = new zzql(copyOfRange);
            return;
        }
        throw new GeneralSecurityException("Can not use AES-SIV in FIPS-mode.");
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzat
    public final byte[] zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        byte[] zzd;
        int length = bArr.length;
        if (length < 16) {
            throw new GeneralSecurityException("Ciphertext too short.");
        }
        Cipher cipher = (Cipher) zzpz.zza.zza("AES/CTR/NoPadding");
        byte[] copyOfRange = Arrays.copyOfRange(bArr, 0, 16);
        byte[] bArr3 = (byte[]) copyOfRange.clone();
        bArr3[8] = (byte) (bArr3[8] & ByteCompanionObject.MAX_VALUE);
        bArr3[12] = (byte) (bArr3[12] & ByteCompanionObject.MAX_VALUE);
        cipher.init(2, new SecretKeySpec(this.zzd, "AES"), new IvParameterSpec(bArr3));
        byte[] copyOfRange2 = Arrays.copyOfRange(bArr, 16, length);
        byte[] doFinal = cipher.doFinal(copyOfRange2);
        if (copyOfRange2.length == 0 && doFinal == null && zzqr.zza()) {
            doFinal = new byte[0];
        }
        byte[][] bArr4 = {bArr2, doFinal};
        byte[] zza2 = this.zzc.zza(zzb, 16);
        for (int i = 0; i <= 0; i++) {
            byte[] bArr5 = bArr4[i];
            if (bArr5 == null) {
                bArr5 = new byte[0];
            }
            zza2 = zzpp.zzd(zziz.zzb(zza2), this.zzc.zza(bArr5, 16));
        }
        byte[] bArr6 = bArr4[1];
        int length2 = bArr6.length;
        if (length2 >= 16) {
            int length3 = zza2.length;
            if (length2 < length3) {
                throw new IllegalArgumentException("xorEnd requires a.length >= b.length");
            }
            int i2 = length2 - length3;
            zzd = Arrays.copyOf(bArr6, length2);
            for (int i3 = 0; i3 < zza2.length; i3++) {
                int i4 = i2 + i3;
                zzd[i4] = (byte) (zzd[i4] ^ zza2[i3]);
            }
        } else {
            zzd = zzpp.zzd(zziz.zza(bArr6), zziz.zzb(zza2));
        }
        if (zzpp.zzb(copyOfRange, this.zzc.zza(zzd, 16))) {
            return doFinal;
        }
        throw new AEADBadTagException("Integrity check failed.");
    }
}
