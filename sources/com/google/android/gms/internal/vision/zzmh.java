package com.google.android.gms.internal.vision;
/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.3 */
/* loaded from: classes2.dex */
final class zzmh extends zzme {
    @Override // com.google.android.gms.internal.vision.zzme
    final int zza(int i, byte[] bArr, int i2, int i3) {
        int zzd;
        int zzd2;
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
                    if (i4 < i3 - 2) {
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
                    }
                    zzd2 = zzmd.zzd(bArr, i4, i3);
                    return zzd2;
                } else if (i4 < i3 - 1) {
                    int i7 = i4 + 1;
                    byte b3 = bArr[i4];
                    if (b3 <= -65 && ((b != -32 || b3 >= -96) && (b != -19 || b3 < -96))) {
                        i2 = i7 + 1;
                        if (bArr[i7] > -65) {
                        }
                    }
                    return -1;
                } else {
                    zzd = zzmd.zzd(bArr, i4, i3);
                    return zzd;
                }
            }
            i2 = i4;
        }
        return 0;
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
            byte b = bArr[i];
            zzd3 = zzmf.zzd(b);
            if (!zzd3) {
                break;
            }
            i++;
            zzmf.zzb(b, cArr, i4);
            i4++;
        }
        int i5 = i4;
        while (i < i3) {
            int i6 = i + 1;
            byte b2 = bArr[i];
            zzd = zzmf.zzd(b2);
            if (zzd) {
                int i7 = i5 + 1;
                zzmf.zzb(b2, cArr, i5);
                while (i6 < i3) {
                    byte b3 = bArr[i6];
                    zzd2 = zzmf.zzd(b3);
                    if (!zzd2) {
                        break;
                    }
                    i6++;
                    zzmf.zzb(b3, cArr, i7);
                    i7++;
                }
                i = i6;
                i5 = i7;
            } else {
                zze = zzmf.zze(b2);
                if (!zze) {
                    zzf = zzmf.zzf(b2);
                    if (zzf) {
                        if (i6 < i3 - 1) {
                            int i8 = i6 + 1;
                            zzmf.zzb(b2, bArr[i6], bArr[i8], cArr, i5);
                            i = i8 + 1;
                            i5++;
                        } else {
                            throw zzjk.zzh();
                        }
                    } else if (i6 >= i3 - 2) {
                        throw zzjk.zzh();
                    } else {
                        int i9 = i6 + 1;
                        byte b4 = bArr[i6];
                        int i10 = i9 + 1;
                        zzmf.zzb(b2, b4, bArr[i9], bArr[i10], cArr, i5);
                        i = i10 + 1;
                        i5 = i5 + 1 + 1;
                    }
                } else if (i6 < i3) {
                    zzmf.zzb(b2, bArr[i6], cArr, i5);
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
    /* JADX WARN: Code restructure failed: missing block: B:12:0x001d, code lost:
        return r10 + r0;
     */
    @Override // com.google.android.gms.internal.vision.zzme
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int zza(java.lang.CharSequence r8, byte[] r9, int r10, int r11) {
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
                    if (r10 <= r11 - 4) {
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
                        throw new com.google.android.gms.internal.vision.zzmg(r1 - 1, r0);
                    } else if (55296 <= r3 && r3 <= 57343 && ((r9 = r1 + 1) == r8.length() || !java.lang.Character.isSurrogatePair(r3, r8.charAt(r9)))) {
                        throw new com.google.android.gms.internal.vision.zzmg(r1, r0);
                    } else {
                        throw new java.lang.ArrayIndexOutOfBoundsException(new java.lang.StringBuilder(37).append("Failed writing ").append(r3).append(" at index ").append(r10).toString());
                    }
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
}
