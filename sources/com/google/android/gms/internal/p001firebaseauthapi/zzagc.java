package com.google.android.gms.internal.p001firebaseauthapi;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzagc  reason: invalid package */
/* loaded from: classes.dex */
final class zzagc {
    private static final zzafz zza;

    static {
        if (zzafx.zzx() && zzafx.zzy()) {
            int i = zzabo.zza;
        }
        zza = new zzaga();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ int zza(byte[] bArr, int i, int i2) {
        byte b = bArr[i - 1];
        int i3 = i2 - i;
        if (i3 != 0) {
            if (i3 == 1) {
                byte b2 = bArr[i];
                if (b <= -12 && b2 <= -65) {
                    return b ^ (b2 << 8);
                }
            } else if (i3 == 2) {
                byte b3 = bArr[i];
                byte b4 = bArr[i + 1];
                if (b <= -12 && b3 <= -65 && b4 <= -65) {
                    return ((b3 << 8) ^ b) ^ (b4 << 16);
                }
            } else {
                throw new AssertionError();
            }
        } else if (b <= -12) {
            return b;
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x00fe, code lost:
        return r9 + r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int zzb(java.lang.CharSequence r7, byte[] r8, int r9, int r10) {
        int r4;
        int r8;
        int r3;
        char r4;
        int r0 = r7.length();
        int r10 = r10 + r9;
        int r1 = 0;
        while (r1 < r0 && (r3 = r1 + r9) < r10 && (r4 = r7.charAt(r1)) < 128) {
            r8[r3] = (byte) r4;
            r1++;
        }
        int r9 = r9 + r1;
        while (r1 < r0) {
            char r3 = r7.charAt(r1);
            if (r3 >= 128 || r9 >= r10) {
                if (r3 < 2048 && r9 <= r10 - 2) {
                    int r4 = r9 + 1;
                    r8[r9] = (byte) ((r3 >>> 6) | 960);
                    r9 = r4 + 1;
                    r8[r4] = (byte) ((r3 & '?') | 128);
                } else if ((r3 >= 55296 && r3 <= 57343) || r9 > r10 - 3) {
                    if (r9 <= r10 - 4) {
                        int r4 = r1 + 1;
                        if (r4 != r7.length()) {
                            char r1 = r7.charAt(r4);
                            if (java.lang.Character.isSurrogatePair(r3, r1)) {
                                int r1 = java.lang.Character.toCodePoint(r3, r1);
                                int r3 = r9 + 1;
                                r8[r9] = (byte) ((r1 >>> 18) | com.egeetouch.egeetouch_manager.BLEService.ReadResponse_ShackleBypass);
                                int r9 = r3 + 1;
                                r8[r3] = (byte) (((r1 >>> 12) & 63) | 128);
                                int r3 = r9 + 1;
                                r8[r9] = (byte) (((r1 >>> 6) & 63) | 128);
                                r9 = r3 + 1;
                                r8[r3] = (byte) ((r1 & 63) | 128);
                                r1 = r4;
                            } else {
                                r1 = r4;
                            }
                        }
                        throw new com.google.android.gms.internal.p001firebaseauthapi.zzagb(r1 - 1, r0);
                    } else if (r3 < 55296 || r3 > 57343 || ((r8 = r1 + 1) != r7.length() && java.lang.Character.isSurrogatePair(r3, r7.charAt(r8)))) {
                        throw new java.lang.ArrayIndexOutOfBoundsException("Failed writing " + r3 + " at index " + r9);
                    } else {
                        throw new com.google.android.gms.internal.p001firebaseauthapi.zzagb(r1, r0);
                    }
                } else {
                    int r4 = r9 + 1;
                    r8[r9] = (byte) ((r3 >>> '\f') | 480);
                    int r9 = r4 + 1;
                    r8[r4] = (byte) (((r3 >>> 6) & 63) | 128);
                    r4 = r9 + 1;
                    r8[r9] = (byte) ((r3 & '?') | 128);
                }
                r1++;
            } else {
                r4 = r9 + 1;
                r8[r9] = (byte) r3;
            }
            r9 = r4;
            r1++;
        }
        return r9;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzc(CharSequence charSequence) {
        int length = charSequence.length();
        int i = 0;
        int i2 = 0;
        while (i2 < length && charSequence.charAt(i2) < 128) {
            i2++;
        }
        int i3 = length;
        while (true) {
            if (i2 >= length) {
                break;
            }
            char charAt = charSequence.charAt(i2);
            if (charAt < 2048) {
                i3 += (127 - charAt) >>> 31;
                i2++;
            } else {
                int length2 = charSequence.length();
                while (i2 < length2) {
                    char charAt2 = charSequence.charAt(i2);
                    if (charAt2 < 2048) {
                        i += (127 - charAt2) >>> 31;
                    } else {
                        i += 2;
                        if (charAt2 >= 55296 && charAt2 <= 57343) {
                            if (Character.codePointAt(charSequence, i2) < 65536) {
                                throw new zzagb(i2, length2);
                            }
                            i2++;
                        }
                    }
                    i2++;
                }
                i3 += i;
            }
        }
        if (i3 >= length) {
            return i3;
        }
        throw new IllegalArgumentException("UTF-8 length does not fit in int: " + (i3 + 4294967296L));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String zzd(byte[] bArr, int i, int i2) throws zzadn {
        int length = bArr.length;
        if ((i | i2 | ((length - i) - i2)) < 0) {
            throw new ArrayIndexOutOfBoundsException(String.format("buffer length=%d, index=%d, size=%d", Integer.valueOf(length), Integer.valueOf(i), Integer.valueOf(i2)));
        }
        int i3 = i + i2;
        char[] cArr = new char[i2];
        int i4 = 0;
        while (i < i3) {
            byte b = bArr[i];
            if (!zzafy.zzd(b)) {
                break;
            }
            i++;
            cArr[i4] = (char) b;
            i4++;
        }
        while (i < i3) {
            int i5 = i + 1;
            byte b2 = bArr[i];
            if (zzafy.zzd(b2)) {
                int i6 = i4 + 1;
                cArr[i4] = (char) b2;
                i = i5;
                while (true) {
                    i4 = i6;
                    if (i < i3) {
                        byte b3 = bArr[i];
                        if (!zzafy.zzd(b3)) {
                            break;
                        }
                        i++;
                        i6 = i4 + 1;
                        cArr[i4] = (char) b3;
                    }
                }
            } else if (b2 < -32) {
                if (i5 < i3) {
                    zzafy.zzc(b2, bArr[i5], cArr, i4);
                    i = i5 + 1;
                    i4++;
                } else {
                    throw zzadn.zzd();
                }
            } else if (b2 < -16) {
                if (i5 < i3 - 1) {
                    int i7 = i5 + 1;
                    zzafy.zzb(b2, bArr[i5], bArr[i7], cArr, i4);
                    i = i7 + 1;
                    i4++;
                } else {
                    throw zzadn.zzd();
                }
            } else if (i5 < i3 - 2) {
                int i8 = i5 + 1;
                int i9 = i8 + 1;
                zzafy.zza(b2, bArr[i5], bArr[i8], bArr[i9], cArr, i4);
                i4 += 2;
                i = i9 + 1;
            } else {
                throw zzadn.zzd();
            }
        }
        return new String(cArr, 0, i4);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean zze(byte[] bArr) {
        return zza.zzb(bArr, 0, bArr.length);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean zzf(byte[] bArr, int i, int i2) {
        return zza.zzb(bArr, i, i2);
    }
}
