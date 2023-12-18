package com.google.android.gms.internal.clearcut;

import com.egeetouch.egeetouch_manager.BLEService;
import java.nio.ByteBuffer;
/* loaded from: classes.dex */
final class zzfj extends zzfg {
    private static int zza(byte[] bArr, int i, long j, int i2) {
        int zzam;
        int zzp;
        int zzd;
        if (i2 == 0) {
            zzam = zzff.zzam(i);
            return zzam;
        } else if (i2 == 1) {
            zzp = zzff.zzp(i, zzfd.zza(bArr, j));
            return zzp;
        } else if (i2 == 2) {
            zzd = zzff.zzd(i, zzfd.zza(bArr, j), zzfd.zza(bArr, j + 1));
            return zzd;
        } else {
            throw new AssertionError();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:33:0x0065, code lost:
        return -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x00ba, code lost:
        return -1;
     */
    @Override // com.google.android.gms.internal.clearcut.zzfg
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    final int zzb(int r18, byte[] r19, int r20, int r21) {
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
                    if (com.google.android.gms.internal.clearcut.zzfd.zza(r19, r11) < 0) {
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
                    byte r7 = com.google.android.gms.internal.clearcut.zzfd.zza(r19, r7);
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
                            if (r1 >= 3) {
                                r1 = r1 - 3;
                                long r13 = r7 + 1;
                                byte r3 = com.google.android.gms.internal.clearcut.zzfd.zza(r19, r7);
                                if (r3 > -65 || (((r2 << 28) + (r3 + 112)) >> 30) != 0) {
                                    break;
                                }
                                long r2 = r13 + 1;
                                if (com.google.android.gms.internal.clearcut.zzfd.zza(r19, r13) > -65) {
                                    break;
                                }
                                r7 = r2 + 1;
                                if (com.google.android.gms.internal.clearcut.zzfd.zza(r19, r2) > -65) {
                                    break;
                                }
                            } else {
                                return zza(r19, r2, r7, r1);
                            }
                        } else if (r1 >= 2) {
                            r1 = r1 - 2;
                            long r13 = r7 + 1;
                            byte r7 = com.google.android.gms.internal.clearcut.zzfd.zza(r19, r7);
                            if (r7 > -65 || ((r2 == -32 && r7 < -96) || (r2 == -19 && r7 >= -96))) {
                                break;
                            }
                            r7 = r13 + 1;
                            if (com.google.android.gms.internal.clearcut.zzfd.zza(r19, r13) > -65) {
                                break;
                            }
                        } else {
                            return zza(r19, r2, r7, r1);
                        }
                    } else if (r1 != 0) {
                        r1 = r1 - 1;
                        if (r2 < -62) {
                            break;
                        }
                        long r2 = r7 + 1;
                        if (com.google.android.gms.internal.clearcut.zzfd.zza(r19, r7) > -65) {
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
    @Override // com.google.android.gms.internal.clearcut.zzfg
    public final int zzb(CharSequence charSequence, byte[] bArr, int i, int i2) {
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
            zzfd.zza(bArr, j4, (byte) charAt);
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
                zzfd.zza(bArr, j4, (byte) charAt2);
                j3 = j;
                j2 = j6;
                c2 = c;
            } else if (charAt2 < 2048 && j4 <= j5 - 2) {
                long j7 = j4 + j;
                zzfd.zza(bArr, j4, (byte) ((charAt2 >>> 6) | 960));
                long j8 = j7 + j;
                zzfd.zza(bArr, j7, (byte) ((charAt2 & '?') | 128));
                long j9 = j;
                c2 = 128;
                j2 = j8;
                j3 = j9;
            } else if ((charAt2 >= 55296 && 57343 >= charAt2) || j4 > j5 - 3) {
                if (j4 > j5 - 4) {
                    if (55296 > charAt2 || charAt2 > 57343 || ((i3 = i4 + 1) != length && Character.isSurrogatePair(charAt2, charSequence.charAt(i3)))) {
                        throw new ArrayIndexOutOfBoundsException(new StringBuilder(46).append("Failed writing ").append(charAt2).append(" at index ").append(j4).toString());
                    }
                    throw new zzfi(i4, length);
                }
                int i5 = i4 + 1;
                if (i5 != length) {
                    char charAt3 = charSequence.charAt(i5);
                    if (Character.isSurrogatePair(charAt2, charAt3)) {
                        int codePoint = Character.toCodePoint(charAt2, charAt3);
                        long j10 = j4 + 1;
                        zzfd.zza(bArr, j4, (byte) ((codePoint >>> 18) | BLEService.ReadResponse_ShackleBypass));
                        long j11 = j10 + 1;
                        c2 = 128;
                        zzfd.zza(bArr, j10, (byte) (((codePoint >>> 12) & 63) | 128));
                        long j12 = j11 + 1;
                        zzfd.zza(bArr, j11, (byte) (((codePoint >>> 6) & 63) | 128));
                        j3 = 1;
                        j2 = j12 + 1;
                        zzfd.zza(bArr, j12, (byte) ((codePoint & 63) | 128));
                        i4 = i5;
                    } else {
                        i4 = i5;
                    }
                }
                throw new zzfi(i4 - 1, length);
            } else {
                long j13 = j4 + j;
                zzfd.zza(bArr, j4, (byte) ((charAt2 >>> '\f') | 480));
                long j14 = j13 + j;
                zzfd.zza(bArr, j13, (byte) (((charAt2 >>> 6) & 63) | 128));
                zzfd.zza(bArr, j14, (byte) ((charAt2 & '?') | 128));
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

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.clearcut.zzfg
    public final void zzb(CharSequence charSequence, ByteBuffer byteBuffer) {
        char c;
        int i;
        long j;
        int i2;
        int i3;
        long j2;
        char c2;
        char charAt;
        ByteBuffer byteBuffer2 = byteBuffer;
        long zzb = zzfd.zzb(byteBuffer);
        long position = byteBuffer.position() + zzb;
        long limit = byteBuffer.limit() + zzb;
        int length = charSequence.length();
        if (length > limit - position) {
            throw new ArrayIndexOutOfBoundsException(new StringBuilder(37).append("Failed writing ").append(charSequence.charAt(length - 1)).append(" at index ").append(byteBuffer.limit()).toString());
        }
        int i4 = 0;
        while (true) {
            c = 128;
            if (i4 >= length || (charAt = charSequence.charAt(i4)) >= 128) {
                break;
            }
            zzfd.zza(position, (byte) charAt);
            i4++;
            position++;
        }
        if (i4 == length) {
            i = (int) (position - zzb);
        } else {
            while (i4 < length) {
                char charAt2 = charSequence.charAt(i4);
                if (charAt2 >= c || position >= limit) {
                    if (charAt2 >= 2048 || position > limit - 2) {
                        j = zzb;
                        if ((charAt2 >= 55296 && 57343 >= charAt2) || position > limit - 3) {
                            if (position > limit - 4) {
                                if (55296 <= charAt2 && charAt2 <= 57343 && ((i2 = i4 + 1) == length || !Character.isSurrogatePair(charAt2, charSequence.charAt(i2)))) {
                                    throw new zzfi(i4, length);
                                }
                                throw new ArrayIndexOutOfBoundsException(new StringBuilder(46).append("Failed writing ").append(charAt2).append(" at index ").append(position).toString());
                            }
                            i3 = i4 + 1;
                            if (i3 != length) {
                                char charAt3 = charSequence.charAt(i3);
                                if (Character.isSurrogatePair(charAt2, charAt3)) {
                                    int codePoint = Character.toCodePoint(charAt2, charAt3);
                                    j2 = limit;
                                    long j3 = position + 1;
                                    zzfd.zza(position, (byte) ((codePoint >>> 18) | BLEService.ReadResponse_ShackleBypass));
                                    long j4 = j3 + 1;
                                    c2 = 128;
                                    zzfd.zza(j3, (byte) (((codePoint >>> 12) & 63) | 128));
                                    long j5 = j4 + 1;
                                    zzfd.zza(j4, (byte) (((codePoint >>> 6) & 63) | 128));
                                    zzfd.zza(j5, (byte) ((codePoint & 63) | 128));
                                    position = j5 + 1;
                                } else {
                                    i4 = i3;
                                }
                            }
                            throw new zzfi(i4 - 1, length);
                        }
                        long j6 = position + 1;
                        zzfd.zza(position, (byte) ((charAt2 >>> '\f') | 480));
                        long j7 = j6 + 1;
                        zzfd.zza(j6, (byte) (((charAt2 >>> 6) & 63) | 128));
                        zzfd.zza(j7, (byte) ((charAt2 & '?') | 128));
                        position = j7 + 1;
                    } else {
                        j = zzb;
                        long j8 = position + 1;
                        zzfd.zza(position, (byte) ((charAt2 >>> 6) | 960));
                        zzfd.zza(j8, (byte) ((charAt2 & '?') | 128));
                        position = j8 + 1;
                    }
                    j2 = limit;
                    i3 = i4;
                    c2 = 128;
                } else {
                    zzfd.zza(position, (byte) charAt2);
                    j2 = limit;
                    i3 = i4;
                    c2 = c;
                    position++;
                    j = zzb;
                }
                c = c2;
                zzb = j;
                limit = j2;
                i4 = i3 + 1;
            }
            i = (int) (position - zzb);
            byteBuffer2 = byteBuffer;
        }
        byteBuffer2.position(i);
    }
}
