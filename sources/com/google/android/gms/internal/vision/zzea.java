package com.google.android.gms.internal.vision;

import kotlin.UByte;
import kotlin.UShort;
/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.3 */
/* loaded from: classes2.dex */
final class zzea {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zza(int i, int i2, int i3) {
        return (i & (~i3)) | (i2 & i3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzb(int i) {
        return (i < 32 ? 4 : 2) * (i + 1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object zza(int i) {
        if (i < 2 || i > 1073741824 || Integer.highestOneBit(i) != i) {
            throw new IllegalArgumentException(new StringBuilder(52).append("must be power of 2 between 2^1 and 2^30: ").append(i).toString());
        }
        if (i <= 256) {
            return new byte[i];
        }
        if (i <= 65536) {
            return new short[i];
        }
        return new int[i];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zza(Object obj, int i) {
        if (obj instanceof byte[]) {
            return ((byte[]) obj)[i] & UByte.MAX_VALUE;
        }
        if (obj instanceof short[]) {
            return ((short[]) obj)[i] & UShort.MAX_VALUE;
        }
        return ((int[]) obj)[i];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zza(Object obj, int i, int i2) {
        if (obj instanceof byte[]) {
            ((byte[]) obj)[i] = (byte) i2;
        } else if (obj instanceof short[]) {
            ((short[]) obj)[i] = (short) i2;
        } else {
            ((int[]) obj)[i] = i2;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x002b, code lost:
        r9 = r6 & r11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x002d, code lost:
        if (r5 != (-1)) goto L18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x002f, code lost:
        zza(r12, r1, r9);
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0033, code lost:
        r13[r5] = zza(r13[r5], r9, r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x003b, code lost:
        return r2;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int zza(@org.checkerframework.checker.nullness.compatqual.NullableDecl java.lang.Object r9, @org.checkerframework.checker.nullness.compatqual.NullableDecl java.lang.Object r10, int r11, java.lang.Object r12, int[] r13, java.lang.Object[] r14, @org.checkerframework.checker.nullness.compatqual.NullableDecl java.lang.Object[] r15) {
        int r0 = com.google.android.gms.internal.vision.zzec.zza(r9);
        int r1 = r0 & r11;
        int r2 = zza(r12, r1);
        if (r2 == 0) {
            return -1;
        }
        int r4 = ~r11;
        int r0 = r0 & r4;
        int r5 = -1;
        while (true) {
            int r2 = r2 - 1;
            int r6 = r13[r2];
            if ((r6 & r4) != r0 || !com.google.android.gms.internal.vision.zzcz.zza(r9, r14[r2]) || (r15 != null && !com.google.android.gms.internal.vision.zzcz.zza(r10, r15[r2]))) {
                int r5 = r6 & r11;
                if (r5 == 0) {
                    return -1;
                }
                r5 = r2;
                r2 = r5;
            }
        }
    }
}
