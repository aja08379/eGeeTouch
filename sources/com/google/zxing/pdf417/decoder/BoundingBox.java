package com.google.zxing.pdf417.decoder;

import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;
/* loaded from: classes2.dex */
final class BoundingBox {
    private ResultPoint bottomLeft;
    private ResultPoint bottomRight;
    private BitMatrix image;
    private int maxX;
    private int maxY;
    private int minX;
    private int minY;
    private ResultPoint topLeft;
    private ResultPoint topRight;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BoundingBox(BitMatrix bitMatrix, ResultPoint resultPoint, ResultPoint resultPoint2, ResultPoint resultPoint3, ResultPoint resultPoint4) throws NotFoundException {
        if ((resultPoint == null && resultPoint3 == null) || ((resultPoint2 == null && resultPoint4 == null) || ((resultPoint != null && resultPoint2 == null) || (resultPoint3 != null && resultPoint4 == null)))) {
            throw NotFoundException.getNotFoundInstance();
        }
        init(bitMatrix, resultPoint, resultPoint2, resultPoint3, resultPoint4);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BoundingBox(BoundingBox boundingBox) {
        init(boundingBox.image, boundingBox.topLeft, boundingBox.bottomLeft, boundingBox.topRight, boundingBox.bottomRight);
    }

    private void init(BitMatrix bitMatrix, ResultPoint resultPoint, ResultPoint resultPoint2, ResultPoint resultPoint3, ResultPoint resultPoint4) {
        this.image = bitMatrix;
        this.topLeft = resultPoint;
        this.bottomLeft = resultPoint2;
        this.topRight = resultPoint3;
        this.bottomRight = resultPoint4;
        calculateMinMaxValues();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static BoundingBox merge(BoundingBox boundingBox, BoundingBox boundingBox2) throws NotFoundException {
        return boundingBox == null ? boundingBox2 : boundingBox2 == null ? boundingBox : new BoundingBox(boundingBox.image, boundingBox.topLeft, boundingBox.bottomLeft, boundingBox2.topRight, boundingBox2.bottomRight);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:17:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x005b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.google.zxing.pdf417.decoder.BoundingBox addMissingRows(int r13, int r14, boolean r15) throws com.google.zxing.NotFoundException {
        com.google.zxing.ResultPoint r8;
        com.google.zxing.ResultPoint r10;
        com.google.zxing.ResultPoint r9;
        com.google.zxing.ResultPoint r11;
        com.google.zxing.ResultPoint r0 = r12.topLeft;
        com.google.zxing.ResultPoint r1 = r12.bottomLeft;
        com.google.zxing.ResultPoint r2 = r12.topRight;
        com.google.zxing.ResultPoint r3 = r12.bottomRight;
        if (r13 > 0) {
            com.google.zxing.ResultPoint r4 = r15 ? r0 : r2;
            int r5 = ((int) r4.getY()) - r13;
            if (r5 < 0) {
                r5 = 0;
            }
            com.google.zxing.ResultPoint r13 = new com.google.zxing.ResultPoint(r4.getX(), r5);
            if (!r15) {
                r10 = r13;
                r8 = r0;
                if (r14 <= 0) {
                    com.google.zxing.ResultPoint r13 = r15 ? r12.bottomLeft : r12.bottomRight;
                    int r0 = ((int) r13.getY()) + r14;
                    if (r0 >= r12.image.getHeight()) {
                        r0 = r12.image.getHeight() - 1;
                    }
                    com.google.zxing.ResultPoint r14 = new com.google.zxing.ResultPoint(r13.getX(), r0);
                    if (!r15) {
                        r11 = r14;
                        r9 = r1;
                        calculateMinMaxValues();
                        return new com.google.zxing.pdf417.decoder.BoundingBox(r12.image, r8, r9, r10, r11);
                    }
                    r9 = r14;
                } else {
                    r9 = r1;
                }
                r11 = r3;
                calculateMinMaxValues();
                return new com.google.zxing.pdf417.decoder.BoundingBox(r12.image, r8, r9, r10, r11);
            }
            r8 = r13;
        } else {
            r8 = r0;
        }
        r10 = r2;
        if (r14 <= 0) {
        }
        r11 = r3;
        calculateMinMaxValues();
        return new com.google.zxing.pdf417.decoder.BoundingBox(r12.image, r8, r9, r10, r11);
    }

    private void calculateMinMaxValues() {
        if (this.topLeft == null) {
            this.topLeft = new ResultPoint(0.0f, this.topRight.getY());
            this.bottomLeft = new ResultPoint(0.0f, this.bottomRight.getY());
        } else if (this.topRight == null) {
            this.topRight = new ResultPoint(this.image.getWidth() - 1, this.topLeft.getY());
            this.bottomRight = new ResultPoint(this.image.getWidth() - 1, this.bottomLeft.getY());
        }
        this.minX = (int) Math.min(this.topLeft.getX(), this.bottomLeft.getX());
        this.maxX = (int) Math.max(this.topRight.getX(), this.bottomRight.getX());
        this.minY = (int) Math.min(this.topLeft.getY(), this.topRight.getY());
        this.maxY = (int) Math.max(this.bottomLeft.getY(), this.bottomRight.getY());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getMinX() {
        return this.minX;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getMaxX() {
        return this.maxX;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getMinY() {
        return this.minY;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getMaxY() {
        return this.maxY;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ResultPoint getTopLeft() {
        return this.topLeft;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ResultPoint getTopRight() {
        return this.topRight;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ResultPoint getBottomLeft() {
        return this.bottomLeft;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ResultPoint getBottomRight() {
        return this.bottomRight;
    }
}
