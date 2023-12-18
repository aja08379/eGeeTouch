package com.google.android.gms.internal.vision;

import com.egeetouch.egeetouch_manager.BLEService;
/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.3 */
/* loaded from: classes2.dex */
final class zzmj extends zzme {
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0065, code lost:
        return -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x00ba, code lost:
        return -1;
     */
    @Override // com.google.android.gms.internal.vision.zzme
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    final int zza(int r18, byte[] r19, int r20, int r21) {
        int r2;
        if ((r20 | r21 | (r19.length - r21)) >= 0) {
            long r7 = r20;
            int r1 = (int) (r21 - r7);
            if (r1 >= 16) {
                r2 = 0;
                long r11 = r7;
                while (true) {
                    if (r2 >= r1) {
                        r2 = r1;
                        break;
                    }
                    long r13 = r11 + 1;
                    if (com.google.android.gms.internal.vision.zzma.zza(r19, r11) < 0) {
                        break;
                    }
                    r2++;
                    r11 = r13;
                }
            } else {
                r2 = 0;
            }
            int r1 = r1 - r2;
            long r7 = r7 + r2;
            while (true) {
                byte r2 = 0;
                while (true) {
                    if (r1 <= 0) {
                        break;
                    }
                    long r2 = r7 + 1;
                    byte r7 = com.google.android.gms.internal.vision.zzma.zza(r19, r7);
                    if (r7 < 0) {
                        r2 = r7;
                        r7 = r2;
                        break;
                    }
                    r1--;
                    r2 = r7;
                    r7 = r2;
                }
                if (r1 != 0) {
                    int r1 = r1 - 1;
                    if (r2 >= -32) {
                        if (r2 >= -16) {
                            if (r1 < 3) {
                                return zza(r19, r2, r7, r1);
                            }
                            r1 = r1 - 3;
                            long r13 = r7 + 1;
                            byte r3 = com.google.android.gms.internal.vision.zzma.zza(r19, r7);
                            if (r3 > -65 || (((r2 << 28) + (r3 + 112)) >> 30) != 0) {
                                break;
                            }
                            long r2 = r13 + 1;
                            if (com.google.android.gms.internal.vision.zzma.zza(r19, r13) > -65) {
                                break;
                            }
                            r7 = r2 + 1;
                            if (com.google.android.gms.internal.vision.zzma.zza(r19, r2) > -65) {
                                break;
                            }
                        } else if (r1 < 2) {
                            return zza(r19, r2, r7, r1);
                        } else {
                            r1 = r1 - 2;
                            long r13 = r7 + 1;
                            byte r7 = com.google.android.gms.internal.vision.zzma.zza(r19, r7);
                            if (r7 > -65 || ((r2 == -32 && r7 < -96) || (r2 == -19 && r7 >= -96))) {
                                break;
                            }
                            r7 = r13 + 1;
                            if (com.google.android.gms.internal.vision.zzma.zza(r19, r13) > -65) {
                                break;
                            }
                        }
                    } else if (r1 != 0) {
                        r1 = r1 - 1;
                        if (r2 < -62) {
                            break;
                        }
                        long r2 = r7 + 1;
                        if (com.google.android.gms.internal.vision.zzma.zza(r19, r7) > -65) {
                            break;
                        }
                        r7 = r2;
                    } else {
                        return r2;
                    }
                } else {
                    return 0;
                }
            }
            return -1;
        }
        throw new java.lang.ArrayIndexOutOfBoundsException(java.lang.String.format("Array length=%d, index=%d, limit=%d", java.lang.Integer.valueOf(r19.length), java.lang.Integer.valueOf(r20), java.lang.Integer.valueOf(r21)));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.vision.zzme
    public final String zzb(byte[] bArr, int i, int i2) throws zzjk {
        boolean zzd;
        boolean zzd2;
        boolean zze;
        boolean zzf;
        boolean zzd3;
        if ((i | i2 | ((bArr.length - i) - i2)) < 0) {
            throw new ArrayIndexOutOfBoundsException(String.format("buffer length=%d, index=%d, size=%d", Integer.valueOf(bArr.length), Integer.valueOf(i), Integer.valueOf(i2)));
        }
        int i3 = i + i2;
        char[] cArr = new char[i2];
        int i4 = 0;
        while (i < i3) {
            byte zza = zzma.zza(bArr, i);
            zzd3 = zzmf.zzd(zza);
            if (!zzd3) {
                break;
            }
            i++;
            zzmf.zzb(zza, cArr, i4);
            i4++;
        }
        int i5 = i4;
        while (i < i3) {
            int i6 = i + 1;
            byte zza2 = zzma.zza(bArr, i);
            zzd = zzmf.zzd(zza2);
            if (zzd) {
                int i7 = i5 + 1;
                zzmf.zzb(zza2, cArr, i5);
                while (i6 < i3) {
                    byte zza3 = zzma.zza(bArr, i6);
                    zzd2 = zzmf.zzd(zza3);
                    if (!zzd2) {
                        break;
                    }
                    i6++;
                    zzmf.zzb(zza3, cArr, i7);
                    i7++;
                }
                i = i6;
                i5 = i7;
            } else {
                zze = zzmf.zze(zza2);
                if (!zze) {
                    zzf = zzmf.zzf(zza2);
                    if (zzf) {
                        if (i6 < i3 - 1) {
                            int i8 = i6 + 1;
                            zzmf.zzb(zza2, zzma.zza(bArr, i6), zzma.zza(bArr, i8), cArr, i5);
                            i = i8 + 1;
                            i5++;
                        } else {
                            throw zzjk.zzh();
                        }
                    } else if (i6 < i3 - 2) {
                        int i9 = i6 + 1;
                        int i10 = i9 + 1;
                        zzmf.zzb(zza2, zzma.zza(bArr, i6), zzma.zza(bArr, i9), zzma.zza(bArr, i10), cArr, i5);
                        i = i10 + 1;
                        i5 = i5 + 1 + 1;
                    } else {
                        throw zzjk.zzh();
                    }
                } else if (i6 < i3) {
                    zzmf.zzb(zza2, zzma.zza(bArr, i6), cArr, i5);
                    i = i6 + 1;
                    i5++;
                } else {
                    throw zzjk.zzh();
                }
            }
        }
        return new String(cArr, 0, i5);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.vision.zzme
    public final int zza(CharSequence charSequence, byte[] bArr, int i, int i2) {
        char c;
        long j;
        long j2;
        long j3;
        char c2;
        int i3;
        char charAt;
        long j4 = i;
        long j5 = i2 + j4;
        int length = charSequence.length();
        if (length > i2 || bArr.length - i2 < i) {
            throw new ArrayIndexOutOfBoundsException(new StringBuilder(37).append("Failed writing ").append(charSequence.charAt(length - 1)).append(" at index ").append(i + i2).toString());
        }
        int i4 = 0;
        while (true) {
            c = 128;
            j = 1;
            if (i4 >= length || (charAt = charSequence.charAt(i4)) >= 128) {
                break;
            }
            zzma.zza(bArr, j4, (byte) charAt);
            i4++;
            j4 = 1 + j4;
        }
        if (i4 == length) {
            return (int) j4;
        }
        while (i4 < length) {
            char charAt2 = charSequence.charAt(i4);
            if (charAt2 < c && j4 < j5) {
                long j6 = j4 + j;
                zzma.zza(bArr, j4, (byte) charAt2);
                j3 = j;
                j2 = j6;
                c2 = c;
            } else if (charAt2 < 2048 && j4 <= j5 - 2) {
                long j7 = j4 + j;
                zzma.zza(bArr, j4, (byte) ((charAt2 >>> 6) | 960));
                long j8 = j7 + j;
                zzma.zza(bArr, j7, (byte) ((charAt2 & '?') | 128));
                long j9 = j;
                c2 = 128;
                j2 = j8;
                j3 = j9;
            } else if ((charAt2 >= 55296 && 57343 >= charAt2) || j4 > j5 - 3) {
                if (j4 <= j5 - 4) {
                    int i5 = i4 + 1;
                    if (i5 != length) {
                        char charAt3 = charSequence.charAt(i5);
                        if (Character.isSurrogatePair(charAt2, charAt3)) {
                            int codePoint = Character.toCodePoint(charAt2, charAt3);
                            long j10 = j4 + 1;
                            zzma.zza(bArr, j4, (byte) ((codePoint >>> 18) | BLEService.ReadResponse_ShackleBypass));
                            long j11 = j10 + 1;
                            c2 = 128;
                            zzma.zza(bArr, j10, (byte) (((codePoint >>> 12) & 63) | 128));
                            long j12 = j11 + 1;
                            zzma.zza(bArr, j11, (byte) (((codePoint >>> 6) & 63) | 128));
                            j3 = 1;
                            j2 = j12 + 1;
                            zzma.zza(bArr, j12, (byte) ((codePoint & 63) | 128));
                            i4 = i5;
                        } else {
                            i4 = i5;
                        }
                    }
                    throw new zzmg(i4 - 1, length);
                } else if (55296 <= charAt2 && charAt2 <= 57343 && ((i3 = i4 + 1) == length || !Character.isSurrogatePair(charAt2, charSequence.charAt(i3)))) {
                    throw new zzmg(i4, length);
                } else {
                    throw new ArrayIndexOutOfBoundsException(new StringBuilder(46).append("Failed writing ").append(charAt2).append(" at index ").append(j4).toString());
                }
            } else {
                long j13 = j4 + j;
                zzma.zza(bArr, j4, (byte) ((charAt2 >>> '\f') | 480));
                long j14 = j13 + j;
                zzma.zza(bArr, j13, (byte) (((charAt2 >>> 6) & 63) | 128));
                zzma.zza(bArr, j14, (byte) ((charAt2 & '?') | 128));
                j2 = j14 + 1;
                j3 = 1;
                c2 = 128;
            }
            i4++;
            c = c2;
            long j15 = j3;
            j4 = j2;
            j = j15;
        }
        return (int) j4;
    }

    private static int zza(byte[] bArr, int i, long j, int i2) {
        int zzb;
        int zzb2;
        int zzb3;
        if (i2 == 0) {
            zzb = zzmd.zzb(i);
            return zzb;
        } else if (i2 == 1) {
            zzb2 = zzmd.zzb(i, zzma.zza(bArr, j));
            return zzb2;
        } else if (i2 == 2) {
            zzb3 = zzmd.zzb(i, zzma.zza(bArr, j), zzma.zza(bArr, j + 1));
            return zzb3;
        } else {
            throw new AssertionError();
        }
    }
}
