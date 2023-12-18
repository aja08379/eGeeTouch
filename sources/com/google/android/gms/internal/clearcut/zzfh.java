package com.google.android.gms.internal.clearcut;

import java.nio.ByteBuffer;
/* loaded from: classes.dex */
final class zzfh extends zzfg {
    @Override // com.google.android.gms.internal.clearcut.zzfg
    final int zzb(int i, byte[] bArr, int i2, int i3) {
        int zzf;
        int zzf2;
        while (i2 < i3 && bArr[i2] >= 0) {
            i2++;
        }
        if (i2 >= i3) {
            return 0;
        }
        while (i2 < i3) {
            int i4 = i2 + 1;
            byte b = bArr[i2];
            if (b < 0) {
                if (b < -32) {
                    if (i4 >= i3) {
                        return b;
                    }
                    if (b >= -62) {
                        i2 = i4 + 1;
                        if (bArr[i4] > -65) {
                        }
                    }
                    return -1;
                } else if (b >= -16) {
                    if (i4 >= i3 - 2) {
                        zzf2 = zzff.zzf(bArr, i4, i3);
                        return zzf2;
                    }
                    int i5 = i4 + 1;
                    byte b2 = bArr[i4];
                    if (b2 <= -65 && (((b << 28) + (b2 + 112)) >> 30) == 0) {
                        int i6 = i5 + 1;
                        if (bArr[i5] <= -65) {
                            i4 = i6 + 1;
                            if (bArr[i6] > -65) {
                            }
                        }
                    }
                    return -1;
                } else if (i4 >= i3 - 1) {
                    zzf = zzff.zzf(bArr, i4, i3);
                    return zzf;
                } else {
                    int i7 = i4 + 1;
                    byte b3 = bArr[i4];
                    if (b3 <= -65 && ((b != -32 || b3 >= -96) && (b != -19 || b3 < -96))) {
                        i2 = i7 + 1;
                        if (bArr[i7] > -65) {
                        }
                    }
                    return -1;
                }
            }
            i2 = i4;
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x001d, code lost:
        return r10 + r0;
     */
    @Override // com.google.android.gms.internal.clearcut.zzfg
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int zzb(java.lang.CharSequence r8, byte[] r9, int r10, int r11) {
        int r4;
        int r9;
        int r3;
        char r4;
        int r0 = r8.length();
        int r11 = r11 + r10;
        int r1 = 0;
        while (r1 < r0 && (r3 = r1 + r10) < r11 && (r4 = r8.charAt(r1)) < 128) {
            r9[r3] = (byte) r4;
            r1++;
        }
        int r10 = r10 + r1;
        while (r1 < r0) {
            char r3 = r8.charAt(r1);
            if (r3 >= 128 || r10 >= r11) {
                if (r3 < 2048 && r10 <= r11 - 2) {
                    int r4 = r10 + 1;
                    r9[r10] = (byte) ((r3 >>> 6) | 960);
                    r10 = r4 + 1;
                    r9[r4] = (byte) ((r3 & '?') | 128);
                } else if ((r3 >= 55296 && 57343 >= r3) || r10 > r11 - 3) {
                    if (r10 > r11 - 4) {
                        if (55296 > r3 || r3 > 57343 || ((r9 = r1 + 1) != r8.length() && java.lang.Character.isSurrogatePair(r3, r8.charAt(r9)))) {
                            throw new java.lang.ArrayIndexOutOfBoundsException(new java.lang.StringBuilder(37).append("Failed writing ").append(r3).append(" at index ").append(r10).toString());
                        }
                        throw new com.google.android.gms.internal.clearcut.zzfi(r1, r0);
                    }
                    int r4 = r1 + 1;
                    if (r4 != r8.length()) {
                        char r1 = r8.charAt(r4);
                        if (java.lang.Character.isSurrogatePair(r3, r1)) {
                            int r1 = java.lang.Character.toCodePoint(r3, r1);
                            int r3 = r10 + 1;
                            r9[r10] = (byte) ((r1 >>> 18) | com.egeetouch.egeetouch_manager.BLEService.ReadResponse_ShackleBypass);
                            int r10 = r3 + 1;
                            r9[r3] = (byte) (((r1 >>> 12) & 63) | 128);
                            int r3 = r10 + 1;
                            r9[r10] = (byte) (((r1 >>> 6) & 63) | 128);
                            r10 = r3 + 1;
                            r9[r3] = (byte) ((r1 & 63) | 128);
                            r1 = r4;
                        } else {
                            r1 = r4;
                        }
                    }
                    throw new com.google.android.gms.internal.clearcut.zzfi(r1 - 1, r0);
                } else {
                    int r4 = r10 + 1;
                    r9[r10] = (byte) ((r3 >>> '\f') | 480);
                    int r10 = r4 + 1;
                    r9[r4] = (byte) (((r3 >>> 6) & 63) | 128);
                    r4 = r10 + 1;
                    r9[r10] = (byte) ((r3 & '?') | 128);
                }
                r1++;
            } else {
                r4 = r10 + 1;
                r9[r10] = (byte) r3;
            }
            r10 = r4;
            r1++;
        }
        return r10;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.clearcut.zzfg
    public final void zzb(CharSequence charSequence, ByteBuffer byteBuffer) {
        zzc(charSequence, byteBuffer);
    }
}
