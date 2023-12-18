package com.google.zxing.common;

import com.google.zxing.NotFoundException;
/* loaded from: classes2.dex */
public abstract class GridSampler {
    private static GridSampler gridSampler = new DefaultGridSampler();

    public abstract BitMatrix sampleGrid(BitMatrix bitMatrix, int i, int i2, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13, float f14, float f15, float f16) throws NotFoundException;

    public abstract BitMatrix sampleGrid(BitMatrix bitMatrix, int i, int i2, PerspectiveTransform perspectiveTransform) throws NotFoundException;

    public static void setGridSampler(GridSampler gridSampler2) {
        gridSampler = gridSampler2;
    }

    public static GridSampler getInstance() {
        return gridSampler;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0075  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void checkAndNudgePoints(com.google.zxing.common.BitMatrix r9, float[] r10) throws com.google.zxing.NotFoundException {
        int r0 = r9.getWidth();
        int r9 = r9.getHeight();
        boolean r4 = true;
        for (int r3 = 0; r3 < r10.length && r4; r3 += 2) {
            int r4 = (int) r10[r3];
            int r5 = r3 + 1;
            int r8 = (int) r10[r5];
            if (r4 < -1 || r4 > r0 || r8 < -1 || r8 > r9) {
                throw com.google.zxing.NotFoundException.getNotFoundInstance();
            }
            if (r4 == -1) {
                r10[r3] = 0.0f;
            } else if (r4 == r0) {
                r10[r3] = r0 - 1;
            } else {
                r4 = false;
                if (r8 != -1) {
                    r10[r5] = 0.0f;
                } else if (r8 == r9) {
                    r10[r5] = r9 - 1;
                }
                r4 = true;
            }
            r4 = true;
            if (r8 != -1) {
            }
            r4 = true;
        }
        boolean r4 = true;
        for (int r3 = r10.length - 2; r3 >= 0 && r4; r3 -= 2) {
            int r4 = (int) r10[r3];
            int r5 = r3 + 1;
            int r8 = (int) r10[r5];
            if (r4 < -1 || r4 > r0 || r8 < -1 || r8 > r9) {
                throw com.google.zxing.NotFoundException.getNotFoundInstance();
            }
            if (r4 == -1) {
                r10[r3] = 0.0f;
            } else if (r4 == r0) {
                r10[r3] = r0 - 1;
            } else {
                r4 = false;
                if (r8 != -1) {
                    r10[r5] = 0.0f;
                } else if (r8 == r9) {
                    r10[r5] = r9 - 1;
                }
                r4 = true;
            }
            r4 = true;
            if (r8 != -1) {
            }
            r4 = true;
        }
    }
}
