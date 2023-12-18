package com.google.zxing.common.detector;

import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;
@Deprecated
/* loaded from: classes2.dex */
public final class MonochromeRectangleDetector {
    private static final int MAX_MODULES = 32;
    private final BitMatrix image;

    public MonochromeRectangleDetector(BitMatrix bitMatrix) {
        this.image = bitMatrix;
    }

    public ResultPoint[] detect() throws NotFoundException {
        int height = this.image.getHeight();
        int width = this.image.getWidth();
        int i = height / 2;
        int i2 = width / 2;
        int max = Math.max(1, height / 256);
        int max2 = Math.max(1, width / 256);
        int i3 = -max;
        int i4 = i2 / 2;
        int y = ((int) findCornerFromCenter(i2, 0, 0, width, i, i3, 0, height, i4).getY()) - 1;
        int i5 = i / 2;
        ResultPoint findCornerFromCenter = findCornerFromCenter(i2, -max2, 0, width, i, 0, y, height, i5);
        int x = ((int) findCornerFromCenter.getX()) - 1;
        ResultPoint findCornerFromCenter2 = findCornerFromCenter(i2, max2, x, width, i, 0, y, height, i5);
        int x2 = ((int) findCornerFromCenter2.getX()) + 1;
        ResultPoint findCornerFromCenter3 = findCornerFromCenter(i2, 0, x, x2, i, max, y, height, i4);
        return new ResultPoint[]{findCornerFromCenter(i2, 0, x, x2, i, i3, y, ((int) findCornerFromCenter3.getY()) + 1, i2 / 4), findCornerFromCenter, findCornerFromCenter2, findCornerFromCenter3};
    }

    private ResultPoint findCornerFromCenter(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) throws NotFoundException {
        int[] blackWhiteRange;
        int[] iArr = null;
        int i10 = i;
        int i11 = i5;
        while (i11 < i8 && i11 >= i7 && i10 < i4 && i10 >= i3) {
            if (i2 == 0) {
                blackWhiteRange = blackWhiteRange(i11, i9, i3, i4, true);
            } else {
                blackWhiteRange = blackWhiteRange(i10, i9, i7, i8, false);
            }
            if (blackWhiteRange == null) {
                if (iArr != null) {
                    if (i2 == 0) {
                        int i12 = i11 - i6;
                        if (iArr[0] < i) {
                            if (iArr[1] > i) {
                                return new ResultPoint(iArr[i6 > 0 ? (char) 0 : (char) 1], i12);
                            }
                            return new ResultPoint(iArr[0], i12);
                        }
                        return new ResultPoint(iArr[1], i12);
                    }
                    int i13 = i10 - i2;
                    if (iArr[0] < i5) {
                        if (iArr[1] > i5) {
                            return new ResultPoint(i13, iArr[i2 < 0 ? (char) 0 : (char) 1]);
                        }
                        return new ResultPoint(i13, iArr[0]);
                    }
                    return new ResultPoint(i13, iArr[1]);
                }
                throw NotFoundException.getNotFoundInstance();
            }
            i11 += i6;
            i10 += i2;
            iArr = blackWhiteRange;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0020  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0031 A[EDGE_INSN: B:70:0x0031->B:22:0x0031 ?: BREAK  , SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0067 A[EDGE_INSN: B:86:0x0067->B:47:0x0067 ?: BREAK  , SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private int[] blackWhiteRange(int r6, int r7, int r8, int r9, boolean r10) {
        int r3;
        int r3;
        int r0 = (r8 + r9) / 2;
        int r2 = r0;
        while (r2 >= r8) {
            com.google.zxing.common.BitMatrix r3 = r5.image;
            if (r10) {
                if (!r3.get(r2, r6)) {
                    r3 = r2;
                    while (true) {
                        r3--;
                        if (r3 >= r8) {
                            break;
                        }
                        com.google.zxing.common.BitMatrix r4 = r5.image;
                        if (r10) {
                            if (r4.get(r3, r6)) {
                                break;
                            }
                        } else if (r4.get(r6, r3)) {
                            break;
                        }
                    }
                    int r4 = r2 - r3;
                    if (r3 >= r8 || r4 > r7) {
                        break;
                        break;
                    }
                    r2 = r3;
                } else {
                    r2--;
                }
            } else if (r3.get(r6, r2)) {
                r2--;
            } else {
                r3 = r2;
                while (true) {
                    r3--;
                    if (r3 >= r8) {
                    }
                }
                int r4 = r2 - r3;
                if (r3 >= r8) {
                    break;
                }
                r2 = r3;
            }
        }
        int r2 = r2 + 1;
        while (r0 < r9) {
            com.google.zxing.common.BitMatrix r3 = r5.image;
            if (r10) {
                if (!r3.get(r0, r6)) {
                    r3 = r0;
                    while (true) {
                        r3++;
                        if (r3 < r9) {
                            break;
                        }
                        com.google.zxing.common.BitMatrix r4 = r5.image;
                        if (r10) {
                            if (r4.get(r3, r6)) {
                                break;
                            }
                        } else if (r4.get(r6, r3)) {
                            break;
                        }
                    }
                    int r4 = r3 - r0;
                    if (r3 < r9 || r4 > r7) {
                        break;
                        break;
                    }
                    r0 = r3;
                } else {
                    r0++;
                }
            } else if (r3.get(r6, r0)) {
                r0++;
            } else {
                r3 = r0;
                while (true) {
                    r3++;
                    if (r3 < r9) {
                    }
                }
                int r4 = r3 - r0;
                if (r3 < r9) {
                    break;
                }
                r0 = r3;
            }
        }
        int r0 = r0 - 1;
        if (r0 > r2) {
            return new int[]{r2, r0};
        }
        return null;
    }
}
