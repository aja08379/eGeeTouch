package com.google.android.gms.internal.p001firebaseauthapi;

import java.security.InvalidKeyException;
import java.util.Arrays;
import kotlin.UByte;
import kotlin.jvm.internal.ByteCompanionObject;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzqt  reason: invalid package */
/* loaded from: classes.dex */
public final class zzqt {
    public static byte[] zza(byte[] bArr, byte[] bArr2) throws InvalidKeyException {
        if (bArr.length != 32) {
            throw new InvalidKeyException("Private key must have 32 bytes.");
        }
        long[] jArr = new long[11];
        byte[] copyOf = Arrays.copyOf(bArr, 32);
        copyOf[0] = (byte) (copyOf[0] & 248);
        int i = copyOf[31] & ByteCompanionObject.MAX_VALUE;
        copyOf[31] = (byte) i;
        copyOf[31] = (byte) (i | 64);
        int i2 = zzpr.zzb;
        if (bArr2.length != 32) {
            throw new InvalidKeyException("Public key length is not 32-byte");
        }
        byte[] copyOf2 = Arrays.copyOf(bArr2, 32);
        copyOf2[31] = (byte) (copyOf2[31] & ByteCompanionObject.MAX_VALUE);
        for (int i3 = 0; i3 < 7; i3++) {
            if (zzpp.zzb(zzpr.zza[i3], copyOf2)) {
                throw new InvalidKeyException("Banned public key: ".concat(zzqj.zza(zzpr.zza[i3])));
            }
        }
        long[] zzk = zzqi.zzk(copyOf2);
        long[] jArr2 = new long[19];
        long[] jArr3 = new long[19];
        jArr3[0] = 1;
        long[] jArr4 = new long[19];
        jArr4[0] = 1;
        long[] jArr5 = new long[19];
        long[] jArr6 = new long[19];
        long[] jArr7 = new long[19];
        jArr7[0] = 1;
        long[] jArr8 = new long[19];
        long[] jArr9 = new long[19];
        jArr9[0] = 1;
        int i4 = 10;
        System.arraycopy(zzk, 0, jArr2, 0, 10);
        int i5 = 0;
        int i6 = 32;
        while (i5 < i6) {
            int i7 = copyOf[(32 - i5) - 1] & UByte.MAX_VALUE;
            int i8 = 0;
            while (i8 < 8) {
                int i9 = (i7 >> (7 - i8)) & 1;
                zzpr.zza(jArr4, jArr2, i9);
                zzpr.zza(jArr5, jArr3, i9);
                byte[] bArr3 = copyOf;
                long[] copyOf3 = Arrays.copyOf(jArr4, 10);
                int i10 = i7;
                long[] jArr10 = new long[19];
                long[] jArr11 = jArr;
                long[] jArr12 = new long[19];
                int i11 = i5;
                long[] jArr13 = new long[19];
                int i12 = i8;
                long[] jArr14 = new long[19];
                long[] jArr15 = new long[19];
                long[] jArr16 = jArr9;
                long[] jArr17 = new long[19];
                long[] jArr18 = new long[19];
                zzqi.zzi(jArr4, jArr4, jArr5);
                zzqi.zzh(jArr5, copyOf3, jArr5);
                long[] copyOf4 = Arrays.copyOf(jArr2, 10);
                zzqi.zzi(jArr2, jArr2, jArr3);
                zzqi.zzh(jArr3, copyOf4, jArr3);
                zzqi.zzb(jArr14, jArr2, jArr5);
                zzqi.zzb(jArr15, jArr4, jArr3);
                zzqi.zze(jArr14);
                zzqi.zzd(jArr14);
                zzqi.zze(jArr15);
                zzqi.zzd(jArr15);
                long[] jArr19 = jArr2;
                System.arraycopy(jArr14, 0, copyOf4, 0, 10);
                zzqi.zzi(jArr14, jArr14, jArr15);
                zzqi.zzh(jArr15, copyOf4, jArr15);
                zzqi.zzg(jArr18, jArr14);
                zzqi.zzg(jArr17, jArr15);
                zzqi.zzb(jArr15, jArr17, zzk);
                zzqi.zze(jArr15);
                zzqi.zzd(jArr15);
                System.arraycopy(jArr18, 0, jArr6, 0, 10);
                System.arraycopy(jArr15, 0, jArr7, 0, 10);
                zzqi.zzg(jArr12, jArr4);
                zzqi.zzg(jArr13, jArr5);
                zzqi.zzb(jArr8, jArr12, jArr13);
                zzqi.zze(jArr8);
                zzqi.zzd(jArr8);
                zzqi.zzh(jArr13, jArr12, jArr13);
                Arrays.fill(jArr10, 10, 18, 0L);
                zzqi.zzf(jArr10, jArr13, 121665L);
                zzqi.zzd(jArr10);
                zzqi.zzi(jArr10, jArr10, jArr12);
                zzqi.zzb(jArr16, jArr13, jArr10);
                zzqi.zze(jArr16);
                zzqi.zzd(jArr16);
                zzpr.zza(jArr8, jArr6, i9);
                zzpr.zza(jArr16, jArr7, i9);
                i8 = i12 + 1;
                jArr9 = jArr5;
                jArr2 = jArr6;
                i7 = i10;
                jArr = jArr11;
                i5 = i11;
                jArr6 = jArr19;
                jArr5 = jArr16;
                copyOf = bArr3;
                long[] jArr20 = jArr4;
                jArr4 = jArr8;
                jArr8 = jArr20;
                long[] jArr21 = jArr7;
                jArr7 = jArr3;
                jArr3 = jArr21;
            }
            i5++;
            copyOf = copyOf;
            i6 = 32;
            i4 = 10;
        }
        long[] jArr22 = jArr;
        int i13 = i4;
        long[] jArr23 = new long[i13];
        long[] jArr24 = new long[i13];
        long[] jArr25 = new long[i13];
        long[] jArr26 = new long[i13];
        long[] jArr27 = new long[i13];
        long[] jArr28 = new long[i13];
        long[] jArr29 = new long[i13];
        long[] jArr30 = new long[i13];
        long[] jArr31 = new long[i13];
        long[] jArr32 = new long[i13];
        long[] jArr33 = jArr2;
        long[] jArr34 = new long[i13];
        zzqi.zzg(jArr24, jArr5);
        zzqi.zzg(jArr34, jArr24);
        zzqi.zzg(jArr32, jArr34);
        zzqi.zza(jArr25, jArr32, jArr5);
        zzqi.zza(jArr26, jArr25, jArr24);
        zzqi.zzg(jArr32, jArr26);
        zzqi.zza(jArr27, jArr32, jArr25);
        zzqi.zzg(jArr32, jArr27);
        zzqi.zzg(jArr34, jArr32);
        zzqi.zzg(jArr32, jArr34);
        zzqi.zzg(jArr34, jArr32);
        zzqi.zzg(jArr32, jArr34);
        zzqi.zza(jArr28, jArr32, jArr27);
        zzqi.zzg(jArr32, jArr28);
        zzqi.zzg(jArr34, jArr32);
        for (int i14 = 2; i14 < 10; i14 += 2) {
            zzqi.zzg(jArr32, jArr34);
            zzqi.zzg(jArr34, jArr32);
        }
        zzqi.zza(jArr29, jArr34, jArr28);
        zzqi.zzg(jArr32, jArr29);
        zzqi.zzg(jArr34, jArr32);
        for (int i15 = 2; i15 < 20; i15 += 2) {
            zzqi.zzg(jArr32, jArr34);
            zzqi.zzg(jArr34, jArr32);
        }
        zzqi.zza(jArr32, jArr34, jArr29);
        zzqi.zzg(jArr34, jArr32);
        zzqi.zzg(jArr32, jArr34);
        for (int i16 = 2; i16 < 10; i16 += 2) {
            zzqi.zzg(jArr34, jArr32);
            zzqi.zzg(jArr32, jArr34);
        }
        zzqi.zza(jArr30, jArr32, jArr28);
        zzqi.zzg(jArr32, jArr30);
        zzqi.zzg(jArr34, jArr32);
        for (int i17 = 2; i17 < 50; i17 += 2) {
            zzqi.zzg(jArr32, jArr34);
            zzqi.zzg(jArr34, jArr32);
        }
        zzqi.zza(jArr31, jArr34, jArr30);
        zzqi.zzg(jArr34, jArr31);
        zzqi.zzg(jArr32, jArr34);
        for (int i18 = 2; i18 < 100; i18 += 2) {
            zzqi.zzg(jArr34, jArr32);
            zzqi.zzg(jArr32, jArr34);
        }
        zzqi.zza(jArr34, jArr32, jArr31);
        zzqi.zzg(jArr32, jArr34);
        zzqi.zzg(jArr34, jArr32);
        for (int i19 = 2; i19 < 50; i19 += 2) {
            zzqi.zzg(jArr32, jArr34);
            zzqi.zzg(jArr34, jArr32);
        }
        zzqi.zza(jArr32, jArr34, jArr30);
        zzqi.zzg(jArr34, jArr32);
        zzqi.zzg(jArr32, jArr34);
        zzqi.zzg(jArr34, jArr32);
        zzqi.zzg(jArr32, jArr34);
        zzqi.zzg(jArr34, jArr32);
        zzqi.zza(jArr23, jArr34, jArr26);
        zzqi.zza(jArr22, jArr4, jArr23);
        long[] jArr35 = new long[10];
        long[] jArr36 = new long[10];
        long[] jArr37 = new long[11];
        long[] jArr38 = new long[11];
        long[] jArr39 = new long[11];
        zzqi.zza(jArr35, zzk, jArr22);
        zzqi.zzi(jArr36, zzk, jArr22);
        long[] jArr40 = new long[10];
        jArr40[0] = 486662;
        zzqi.zzi(jArr38, jArr36, jArr40);
        zzqi.zza(jArr38, jArr38, jArr3);
        zzqi.zzi(jArr38, jArr38, jArr33);
        zzqi.zza(jArr38, jArr38, jArr35);
        zzqi.zza(jArr38, jArr38, jArr33);
        zzqi.zzf(jArr37, jArr38, 4L);
        zzqi.zzd(jArr37);
        zzqi.zza(jArr38, jArr35, jArr3);
        zzqi.zzh(jArr38, jArr38, jArr3);
        zzqi.zza(jArr39, jArr36, jArr33);
        zzqi.zzi(jArr38, jArr38, jArr39);
        zzqi.zzg(jArr38, jArr38);
        if (!zzpp.zzb(zzqi.zzj(jArr37), zzqi.zzj(jArr38))) {
            throw new IllegalStateException("Arithmetic error in curve multiplication with the public key: ".concat(zzqj.zza(bArr2)));
        }
        return zzqi.zzj(jArr22);
    }

    public static byte[] zzb(byte[] bArr) throws InvalidKeyException {
        if (bArr.length != 32) {
            throw new InvalidKeyException("Private key must have 32 bytes.");
        }
        byte[] bArr2 = new byte[32];
        bArr2[0] = 9;
        return zza(bArr, bArr2);
    }
}
