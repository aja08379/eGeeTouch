package com.google.zxing.oned;

import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;
/* loaded from: classes2.dex */
public final class Code128Reader extends OneDReader {
    private static final int CODE_CODE_A = 101;
    private static final int CODE_CODE_B = 100;
    private static final int CODE_CODE_C = 99;
    private static final int CODE_FNC_1 = 102;
    private static final int CODE_FNC_2 = 97;
    private static final int CODE_FNC_3 = 96;
    private static final int CODE_FNC_4_A = 101;
    private static final int CODE_FNC_4_B = 100;
    static final int[][] CODE_PATTERNS = {new int[]{2, 1, 2, 2, 2, 2}, new int[]{2, 2, 2, 1, 2, 2}, new int[]{2, 2, 2, 2, 2, 1}, new int[]{1, 2, 1, 2, 2, 3}, new int[]{1, 2, 1, 3, 2, 2}, new int[]{1, 3, 1, 2, 2, 2}, new int[]{1, 2, 2, 2, 1, 3}, new int[]{1, 2, 2, 3, 1, 2}, new int[]{1, 3, 2, 2, 1, 2}, new int[]{2, 2, 1, 2, 1, 3}, new int[]{2, 2, 1, 3, 1, 2}, new int[]{2, 3, 1, 2, 1, 2}, new int[]{1, 1, 2, 2, 3, 2}, new int[]{1, 2, 2, 1, 3, 2}, new int[]{1, 2, 2, 2, 3, 1}, new int[]{1, 1, 3, 2, 2, 2}, new int[]{1, 2, 3, 1, 2, 2}, new int[]{1, 2, 3, 2, 2, 1}, new int[]{2, 2, 3, 2, 1, 1}, new int[]{2, 2, 1, 1, 3, 2}, new int[]{2, 2, 1, 2, 3, 1}, new int[]{2, 1, 3, 2, 1, 2}, new int[]{2, 2, 3, 1, 1, 2}, new int[]{3, 1, 2, 1, 3, 1}, new int[]{3, 1, 1, 2, 2, 2}, new int[]{3, 2, 1, 1, 2, 2}, new int[]{3, 2, 1, 2, 2, 1}, new int[]{3, 1, 2, 2, 1, 2}, new int[]{3, 2, 2, 1, 1, 2}, new int[]{3, 2, 2, 2, 1, 1}, new int[]{2, 1, 2, 1, 2, 3}, new int[]{2, 1, 2, 3, 2, 1}, new int[]{2, 3, 2, 1, 2, 1}, new int[]{1, 1, 1, 3, 2, 3}, new int[]{1, 3, 1, 1, 2, 3}, new int[]{1, 3, 1, 3, 2, 1}, new int[]{1, 1, 2, 3, 1, 3}, new int[]{1, 3, 2, 1, 1, 3}, new int[]{1, 3, 2, 3, 1, 1}, new int[]{2, 1, 1, 3, 1, 3}, new int[]{2, 3, 1, 1, 1, 3}, new int[]{2, 3, 1, 3, 1, 1}, new int[]{1, 1, 2, 1, 3, 3}, new int[]{1, 1, 2, 3, 3, 1}, new int[]{1, 3, 2, 1, 3, 1}, new int[]{1, 1, 3, 1, 2, 3}, new int[]{1, 1, 3, 3, 2, 1}, new int[]{1, 3, 3, 1, 2, 1}, new int[]{3, 1, 3, 1, 2, 1}, new int[]{2, 1, 1, 3, 3, 1}, new int[]{2, 3, 1, 1, 3, 1}, new int[]{2, 1, 3, 1, 1, 3}, new int[]{2, 1, 3, 3, 1, 1}, new int[]{2, 1, 3, 1, 3, 1}, new int[]{3, 1, 1, 1, 2, 3}, new int[]{3, 1, 1, 3, 2, 1}, new int[]{3, 3, 1, 1, 2, 1}, new int[]{3, 1, 2, 1, 1, 3}, new int[]{3, 1, 2, 3, 1, 1}, new int[]{3, 3, 2, 1, 1, 1}, new int[]{3, 1, 4, 1, 1, 1}, new int[]{2, 2, 1, 4, 1, 1}, new int[]{4, 3, 1, 1, 1, 1}, new int[]{1, 1, 1, 2, 2, 4}, new int[]{1, 1, 1, 4, 2, 2}, new int[]{1, 2, 1, 1, 2, 4}, new int[]{1, 2, 1, 4, 2, 1}, new int[]{1, 4, 1, 1, 2, 2}, new int[]{1, 4, 1, 2, 2, 1}, new int[]{1, 1, 2, 2, 1, 4}, new int[]{1, 1, 2, 4, 1, 2}, new int[]{1, 2, 2, 1, 1, 4}, new int[]{1, 2, 2, 4, 1, 1}, new int[]{1, 4, 2, 1, 1, 2}, new int[]{1, 4, 2, 2, 1, 1}, new int[]{2, 4, 1, 2, 1, 1}, new int[]{2, 2, 1, 1, 1, 4}, new int[]{4, 1, 3, 1, 1, 1}, new int[]{2, 4, 1, 1, 1, 2}, new int[]{1, 3, 4, 1, 1, 1}, new int[]{1, 1, 1, 2, 4, 2}, new int[]{1, 2, 1, 1, 4, 2}, new int[]{1, 2, 1, 2, 4, 1}, new int[]{1, 1, 4, 2, 1, 2}, new int[]{1, 2, 4, 1, 1, 2}, new int[]{1, 2, 4, 2, 1, 1}, new int[]{4, 1, 1, 2, 1, 2}, new int[]{4, 2, 1, 1, 1, 2}, new int[]{4, 2, 1, 2, 1, 1}, new int[]{2, 1, 2, 1, 4, 1}, new int[]{2, 1, 4, 1, 2, 1}, new int[]{4, 1, 2, 1, 2, 1}, new int[]{1, 1, 1, 1, 4, 3}, new int[]{1, 1, 1, 3, 4, 1}, new int[]{1, 3, 1, 1, 4, 1}, new int[]{1, 1, 4, 1, 1, 3}, new int[]{1, 1, 4, 3, 1, 1}, new int[]{4, 1, 1, 1, 1, 3}, new int[]{4, 1, 1, 3, 1, 1}, new int[]{1, 1, 3, 1, 4, 1}, new int[]{1, 1, 4, 1, 3, 1}, new int[]{3, 1, 1, 1, 4, 1}, new int[]{4, 1, 1, 1, 3, 1}, new int[]{2, 1, 1, 4, 1, 2}, new int[]{2, 1, 1, 2, 1, 4}, new int[]{2, 1, 1, 2, 3, 2}, new int[]{2, 3, 3, 1, 1, 1, 2}};
    private static final int CODE_SHIFT = 98;
    private static final int CODE_START_A = 103;
    private static final int CODE_START_B = 104;
    private static final int CODE_START_C = 105;
    private static final int CODE_STOP = 106;
    private static final float MAX_AVG_VARIANCE = 0.25f;
    private static final float MAX_INDIVIDUAL_VARIANCE = 0.7f;

    private static int[] findStartPattern(BitArray bitArray) throws NotFoundException {
        int size = bitArray.getSize();
        int nextSet = bitArray.getNextSet(0);
        int[] iArr = new int[6];
        boolean z = false;
        int i = 0;
        int i2 = nextSet;
        while (nextSet < size) {
            if (bitArray.get(nextSet) ^ z) {
                iArr[i] = iArr[i] + 1;
            } else {
                if (i == 5) {
                    float f = MAX_AVG_VARIANCE;
                    int i3 = -1;
                    for (int i4 = 103; i4 <= 105; i4++) {
                        float patternMatchVariance = patternMatchVariance(iArr, CODE_PATTERNS[i4], MAX_INDIVIDUAL_VARIANCE);
                        if (patternMatchVariance < f) {
                            i3 = i4;
                            f = patternMatchVariance;
                        }
                    }
                    if (i3 >= 0 && bitArray.isRange(Math.max(0, i2 - ((nextSet - i2) / 2)), i2, false)) {
                        return new int[]{i2, nextSet, i3};
                    }
                    i2 += iArr[0] + iArr[1];
                    System.arraycopy(iArr, 2, iArr, 0, 4);
                    iArr[4] = 0;
                    iArr[5] = 0;
                    i--;
                } else {
                    i++;
                }
                iArr[i] = 1;
                z = !z;
            }
            nextSet++;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static int decodeCode(BitArray bitArray, int[] iArr, int i) throws NotFoundException {
        recordPattern(bitArray, i, iArr);
        float f = MAX_AVG_VARIANCE;
        int i2 = -1;
        int i3 = 0;
        while (true) {
            int[][] iArr2 = CODE_PATTERNS;
            if (i3 >= iArr2.length) {
                break;
            }
            float patternMatchVariance = patternMatchVariance(iArr, iArr2[i3], MAX_INDIVIDUAL_VARIANCE);
            if (patternMatchVariance < f) {
                i2 = i3;
                f = patternMatchVariance;
            }
            i3++;
        }
        if (i2 >= 0) {
            return i2;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x00e2, code lost:
        if (r9 != false) goto L62;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x012a, code lost:
        if (r9 != false) goto L62;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x012c, code lost:
        r3 = false;
     */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0131 A[PHI: r17 
      PHI: (r17v10 boolean) = (r17v6 boolean), (r17v16 boolean) binds: [B:67:0x010a, B:43:0x00c2] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:86:0x013d A[PHI: r16 r17 
      PHI: (r16v4 boolean) = (r16v1 boolean), (r16v1 boolean), (r16v1 boolean), (r16v1 boolean), (r16v3 boolean), (r16v1 boolean), (r16v1 boolean), (r16v1 boolean), (r16v1 boolean) binds: [B:67:0x010a, B:69:0x010e, B:73:0x011a, B:72:0x0116, B:85:0x013b, B:43:0x00c2, B:45:0x00c7, B:49:0x00d4, B:48:0x00cf] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r17v9 boolean) = (r17v6 boolean), (r17v6 boolean), (r17v6 boolean), (r17v6 boolean), (r17v8 boolean), (r17v16 boolean), (r17v16 boolean), (r17v16 boolean), (r17v16 boolean) binds: [B:67:0x010a, B:69:0x010e, B:73:0x011a, B:72:0x0116, B:85:0x013b, B:43:0x00c2, B:45:0x00c7, B:49:0x00d4, B:48:0x00cf] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // com.google.zxing.oned.OneDReader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.google.zxing.Result decodeRow(int r25, com.google.zxing.common.BitArray r26, java.util.Map<com.google.zxing.DecodeHintType, ?> r27) throws com.google.zxing.NotFoundException, com.google.zxing.FormatException, com.google.zxing.ChecksumException {
        char r12;
        char r10;
        boolean r5;
        boolean r3 = false;
        boolean r1 = r27 != null && r27.containsKey(com.google.zxing.DecodeHintType.ASSUME_GS1);
        int[] r4 = findStartPattern(r26);
        int r6 = r4[2];
        java.util.ArrayList r7 = new java.util.ArrayList(20);
        r7.add(java.lang.Byte.valueOf((byte) r6));
        switch (r6) {
            case 103:
                r12 = 'e';
                break;
            case 104:
                r12 = 'd';
                break;
            case 105:
                r12 = 'c';
                break;
            default:
                throw com.google.zxing.FormatException.getFormatInstance();
        }
        java.lang.StringBuilder r13 = new java.lang.StringBuilder(20);
        int r15 = 6;
        int[] r2 = new int[6];
        boolean r9 = false;
        boolean r16 = false;
        boolean r18 = false;
        int r19 = 0;
        int r20 = 0;
        int r21 = 0;
        boolean r17 = true;
        char r23 = r12;
        int r12 = r4[0];
        int r8 = r4[1];
        char r14 = r23;
        while (!r16) {
            int r12 = decodeCode(r26, r2, r8);
            r7.add(java.lang.Byte.valueOf((byte) r12));
            if (r12 != 106) {
                r17 = true;
            }
            if (r12 != 106) {
                r20++;
                r6 += r20 * r12;
            }
            int r21 = r8;
            for (int r11 = 0; r11 < r15; r11++) {
                r21 += r2[r11];
            }
            switch (r12) {
                case 103:
                case 104:
                case 105:
                    throw com.google.zxing.FormatException.getFormatInstance();
                default:
                    switch (r14) {
                        case 'c':
                            r10 = 'd';
                            if (r12 >= 100) {
                                if (r12 != 106) {
                                    r17 = false;
                                }
                                if (r12 != 106) {
                                    switch (r12) {
                                        case 100:
                                            r14 = 'd';
                                            break;
                                        case 101:
                                            r5 = false;
                                            r14 = 'e';
                                            break;
                                        case 102:
                                            if (r1) {
                                                if (r13.length() == 0) {
                                                    r13.append("]C1");
                                                    break;
                                                } else {
                                                    r13.append((char) 29);
                                                    break;
                                                }
                                            }
                                            break;
                                    }
                                } else {
                                    r5 = false;
                                    r16 = true;
                                    break;
                                }
                            } else {
                                if (r12 < 10) {
                                    r13.append('0');
                                }
                                r13.append(r12);
                            }
                            r5 = false;
                            break;
                        case 'd':
                            if (r12 < 96) {
                                if (r9 == r3) {
                                    r13.append((char) (r12 + 32));
                                } else {
                                    r13.append((char) (r12 + 32 + 128));
                                }
                                r5 = false;
                                r9 = false;
                                r10 = 'd';
                                break;
                            } else {
                                if (r12 != 106) {
                                    r17 = false;
                                }
                                if (r12 != 106) {
                                    switch (r12) {
                                        case 96:
                                        case 97:
                                        default:
                                            r5 = false;
                                            r10 = 'd';
                                            break;
                                        case 98:
                                            r5 = true;
                                            r10 = 'd';
                                            r14 = 'e';
                                            break;
                                        case 99:
                                            r5 = false;
                                            r10 = 'd';
                                            r14 = 'c';
                                            break;
                                        case 100:
                                            if (r3 || !r9) {
                                                if (r3) {
                                                }
                                                r5 = false;
                                                r9 = true;
                                                r10 = 'd';
                                                break;
                                            }
                                            r3 = true;
                                            r5 = false;
                                            r9 = false;
                                            r10 = 'd';
                                            break;
                                        case 101:
                                            r5 = false;
                                            r10 = 'd';
                                            r14 = 'e';
                                            break;
                                        case 102:
                                            if (r1) {
                                                if (r13.length() == 0) {
                                                    r13.append("]C1");
                                                } else {
                                                    r13.append((char) 29);
                                                }
                                            }
                                            r5 = false;
                                            r10 = 'd';
                                            break;
                                    }
                                }
                                r16 = true;
                                r5 = false;
                                r10 = 'd';
                            }
                            break;
                        case 'e':
                            if (r12 < 64) {
                                if (r9 == r3) {
                                    r13.append((char) (r12 + 32));
                                } else {
                                    r13.append((char) (r12 + 32 + 128));
                                }
                            } else if (r12 >= 96) {
                                if (r12 != 106) {
                                    r17 = false;
                                }
                                if (r12 != 106) {
                                    switch (r12) {
                                        case 98:
                                            r5 = true;
                                            r10 = 'd';
                                            r14 = 'd';
                                            break;
                                        case 100:
                                            r5 = false;
                                            r10 = 'd';
                                            r14 = 'd';
                                            break;
                                        case 101:
                                            if (r3 || !r9) {
                                                if (r3) {
                                                }
                                                r5 = false;
                                                r9 = true;
                                                r10 = 'd';
                                                break;
                                            }
                                            r3 = true;
                                            break;
                                        case 102:
                                            if (r1) {
                                                if (r13.length() == 0) {
                                                    r13.append("]C1");
                                                } else {
                                                    r13.append((char) 29);
                                                }
                                            }
                                            r5 = false;
                                            r10 = 'd';
                                            break;
                                    }
                                }
                                r16 = true;
                                r5 = false;
                                r10 = 'd';
                            } else if (r9 == r3) {
                                r13.append((char) (r12 - 64));
                            } else {
                                r13.append((char) (r12 + 64));
                            }
                            r5 = false;
                            r9 = false;
                            r10 = 'd';
                            break;
                        default:
                            r10 = 'd';
                            r5 = false;
                            break;
                    }
                    if (r18) {
                        r14 = r14 == 'e' ? r10 : 'e';
                    }
                    r18 = r5;
                    r15 = 6;
                    r12 = r8;
                    r8 = r21;
                    r21 = r19;
                    r19 = r12;
                    break;
            }
        }
        int r1 = r8 - r12;
        int r2 = r26.getNextUnset(r8);
        if (!r26.isRange(r2, java.lang.Math.min(r26.getSize(), ((r2 - r12) / 2) + r2), false)) {
            throw com.google.zxing.NotFoundException.getNotFoundInstance();
        }
        int r3 = r21;
        if ((r6 - (r20 * r3)) % 103 != r3) {
            throw com.google.zxing.ChecksumException.getChecksumInstance();
        }
        int r0 = r13.length();
        if (r0 == 0) {
            throw com.google.zxing.NotFoundException.getNotFoundInstance();
        }
        if (r0 > 0 && r17) {
            if (r14 == 'c') {
                r13.delete(r0 - 2, r0);
            } else {
                r13.delete(r0 - 1, r0);
            }
        }
        float r0 = (r4[1] + r4[0]) / 2.0f;
        float r3 = r12 + (r1 / 2.0f);
        int r1 = r7.size();
        byte[] r2 = new byte[r1];
        for (int r5 = 0; r5 < r1; r5++) {
            r2[r5] = ((java.lang.Byte) r7.get(r5)).byteValue();
        }
        float r7 = r25;
        return new com.google.zxing.Result(r13.toString(), r2, new com.google.zxing.ResultPoint[]{new com.google.zxing.ResultPoint(r0, r7), new com.google.zxing.ResultPoint(r3, r7)}, com.google.zxing.BarcodeFormat.CODE_128);
    }
}
