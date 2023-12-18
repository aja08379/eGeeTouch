package androidx.core.graphics;

import android.graphics.Bitmap;
import android.graphics.BlendMode;
import android.graphics.ColorSpace;
import android.graphics.Paint;
import android.os.Build;
/* loaded from: classes.dex */
public final class BitmapCompat {
    public static int sizeAtStep(int i, int i2, int i3, int i4) {
        return i3 == 0 ? i2 : i3 > 0 ? i * (1 << (i4 - i3)) : i2 << ((-i3) - 1);
    }

    public static boolean hasMipMap(Bitmap bitmap) {
        if (Build.VERSION.SDK_INT >= 17) {
            return Api17Impl.hasMipMap(bitmap);
        }
        return false;
    }

    public static void setHasMipMap(Bitmap bitmap, boolean z) {
        if (Build.VERSION.SDK_INT >= 17) {
            Api17Impl.setHasMipMap(bitmap, z);
        }
    }

    public static int getAllocationByteCount(Bitmap bitmap) {
        if (Build.VERSION.SDK_INT >= 19) {
            return Api19Impl.getAllocationByteCount(bitmap);
        }
        return bitmap.getByteCount();
    }

    /* JADX WARN: Code restructure failed: missing block: B:113:0x01a5, code lost:
        if (androidx.core.graphics.BitmapCompat.Api27Impl.isAlreadyF16AndLinear(r10) == false) goto L96;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static android.graphics.Bitmap createScaledBitmap(android.graphics.Bitmap r22, int r23, int r24, android.graphics.Rect r25, boolean r26) {
        int r18;
        double r10;
        double r9;
        int r11;
        int r14;
        android.graphics.Rect r20;
        android.graphics.Bitmap r3;
        if (r23 <= 0 || r24 <= 0) {
            throw new java.lang.IllegalArgumentException("dstW and dstH must be > 0!");
        }
        if (r25 != null && (r25.isEmpty() || r25.left < 0 || r25.right > r22.getWidth() || r25.top < 0 || r25.bottom > r22.getHeight())) {
            throw new java.lang.IllegalArgumentException("srcRect must be contained by srcBm!");
        }
        android.graphics.Bitmap r4 = android.os.Build.VERSION.SDK_INT >= 27 ? androidx.core.graphics.BitmapCompat.Api27Impl.copyBitmapIfHardware(r22) : r22;
        int r6 = r25 != null ? r25.width() : r22.getWidth();
        int r7 = r25 != null ? r25.height() : r22.getHeight();
        float r8 = r23 / r6;
        float r9 = r24 / r7;
        int r11 = r25 != null ? r25.left : 0;
        int r3 = r25 != null ? r25.top : 0;
        if (r11 == 0 && r3 == 0 && r23 == r22.getWidth() && r24 == r22.getHeight()) {
            return (r22.isMutable() && r22 == r4) ? r22.copy(r22.getConfig(), true) : r4;
        }
        android.graphics.Paint r13 = new android.graphics.Paint(1);
        r13.setFilterBitmap(true);
        if (android.os.Build.VERSION.SDK_INT >= 29) {
            androidx.core.graphics.BitmapCompat.Api29Impl.setPaintBlendMode(r13);
        } else {
            r13.setXfermode(new android.graphics.PorterDuffXfermode(android.graphics.PorterDuff.Mode.SRC));
        }
        if (r6 == r23 && r7 == r24) {
            android.graphics.Bitmap r0 = android.graphics.Bitmap.createBitmap(r23, r24, r4.getConfig());
            new android.graphics.Canvas(r0).drawBitmap(r4, -r11, -r3, r13);
            return r0;
        }
        double r14 = java.lang.Math.log(2.0d);
        if (r8 > 1.0f) {
            r18 = r11;
            r10 = java.lang.Math.ceil(java.lang.Math.log(r8) / r14);
        } else {
            r18 = r11;
            r10 = java.lang.Math.floor(java.lang.Math.log(r8) / r14);
        }
        int r8 = (int) r10;
        if (r9 > 1.0f) {
            r9 = java.lang.Math.ceil(java.lang.Math.log(r9) / r14);
        } else {
            r9 = java.lang.Math.floor(java.lang.Math.log(r9) / r14);
        }
        int r9 = (int) r9;
        android.graphics.Bitmap r10 = null;
        if (!r26 || android.os.Build.VERSION.SDK_INT < 27 || androidx.core.graphics.BitmapCompat.Api27Impl.isAlreadyF16AndLinear(r22)) {
            r11 = r18;
            r14 = 0;
        } else {
            android.graphics.Bitmap r10 = androidx.core.graphics.BitmapCompat.Api27Impl.createBitmapWithSourceColorspace(r8 > 0 ? sizeAtStep(r6, r23, 1, r8) : r6, r9 > 0 ? sizeAtStep(r7, r24, 1, r9) : r7, r22, true);
            new android.graphics.Canvas(r10).drawBitmap(r4, -r18, -r3, r13);
            r14 = 1;
            r3 = 0;
            r11 = 0;
            r10 = r4;
            r4 = r10;
        }
        android.graphics.Rect r15 = new android.graphics.Rect(r11, r3, r6, r7);
        android.graphics.Rect r3 = new android.graphics.Rect();
        int r11 = r8;
        int r16 = r9;
        while (true) {
            if (r11 == 0 && r16 == 0) {
                break;
            }
            if (r11 < 0) {
                r11++;
            } else if (r11 > 0) {
                r11--;
            }
            if (r16 < 0) {
                r16++;
            } else if (r16 > 0) {
                r16--;
            }
            int r12 = r16;
            android.graphics.Paint r18 = r13;
            android.graphics.Rect r19 = r15;
            r3.set(0, 0, sizeAtStep(r6, r23, r11, r8), sizeAtStep(r7, r24, r12, r9));
            boolean r5 = r11 == 0 && r12 == 0;
            boolean r13 = r10 != null && r10.getWidth() == r23 && r10.getHeight() == r24;
            if (r10 == null || r10 == r22) {
                r20 = r3;
            } else {
                if (r26) {
                    r20 = r3;
                    if (android.os.Build.VERSION.SDK_INT >= 27) {
                    }
                } else {
                    r20 = r3;
                }
                if (!r5 || (r13 && r14 == 0)) {
                    r3 = r10;
                    android.graphics.Rect r15 = r20;
                    new android.graphics.Canvas(r3).drawBitmap(r4, r19, r15, r18);
                    r19.set(r15);
                    r16 = r12;
                    android.graphics.Bitmap r21 = r4;
                    r4 = r3;
                    r3 = r15;
                    r15 = r19;
                    r13 = r18;
                    r10 = r21;
                }
            }
            if (r10 != r22 && r10 != null) {
                r10.recycle();
            }
            int r3 = sizeAtStep(r6, r23, r11 > 0 ? r14 : r11, r8);
            int r10 = sizeAtStep(r7, r24, r12 > 0 ? r14 : r12, r9);
            if (android.os.Build.VERSION.SDK_INT >= 27) {
                r3 = androidx.core.graphics.BitmapCompat.Api27Impl.createBitmapWithSourceColorspace(r3, r10, r22, r26 && !r5);
            } else {
                r3 = android.graphics.Bitmap.createBitmap(r3, r10, r4.getConfig());
            }
            android.graphics.Rect r15 = r20;
            new android.graphics.Canvas(r3).drawBitmap(r4, r19, r15, r18);
            r19.set(r15);
            r16 = r12;
            android.graphics.Bitmap r21 = r4;
            r4 = r3;
            r3 = r15;
            r15 = r19;
            r13 = r18;
            r10 = r21;
        }
        if (r10 != r22 && r10 != null) {
            r10.recycle();
        }
        return r4;
    }

    private BitmapCompat() {
    }

    /* loaded from: classes.dex */
    static class Api17Impl {
        private Api17Impl() {
        }

        static boolean hasMipMap(Bitmap bitmap) {
            return bitmap.hasMipMap();
        }

        static void setHasMipMap(Bitmap bitmap, boolean z) {
            bitmap.setHasMipMap(z);
        }
    }

    /* loaded from: classes.dex */
    static class Api19Impl {
        private Api19Impl() {
        }

        static int getAllocationByteCount(Bitmap bitmap) {
            return bitmap.getAllocationByteCount();
        }
    }

    /* loaded from: classes.dex */
    static class Api27Impl {
        private Api27Impl() {
        }

        static Bitmap createBitmapWithSourceColorspace(int i, int i2, Bitmap bitmap, boolean z) {
            Bitmap.Config config = bitmap.getConfig();
            ColorSpace colorSpace = bitmap.getColorSpace();
            ColorSpace colorSpace2 = ColorSpace.get(ColorSpace.Named.LINEAR_EXTENDED_SRGB);
            if (z && !bitmap.getColorSpace().equals(colorSpace2)) {
                config = Bitmap.Config.RGBA_F16;
                colorSpace = colorSpace2;
            } else if (bitmap.getConfig() == Bitmap.Config.HARDWARE) {
                config = Bitmap.Config.ARGB_8888;
                if (Build.VERSION.SDK_INT >= 31) {
                    config = Api31Impl.getHardwareBitmapConfig(bitmap);
                }
            }
            return Bitmap.createBitmap(i, i2, config, bitmap.hasAlpha(), colorSpace);
        }

        static boolean isAlreadyF16AndLinear(Bitmap bitmap) {
            return bitmap.getConfig() == Bitmap.Config.RGBA_F16 && bitmap.getColorSpace().equals(ColorSpace.get(ColorSpace.Named.LINEAR_EXTENDED_SRGB));
        }

        static Bitmap copyBitmapIfHardware(Bitmap bitmap) {
            if (bitmap.getConfig() == Bitmap.Config.HARDWARE) {
                Bitmap.Config config = Bitmap.Config.ARGB_8888;
                if (Build.VERSION.SDK_INT >= 31) {
                    config = Api31Impl.getHardwareBitmapConfig(bitmap);
                }
                return bitmap.copy(config, true);
            }
            return bitmap;
        }
    }

    /* loaded from: classes.dex */
    static class Api29Impl {
        private Api29Impl() {
        }

        static void setPaintBlendMode(Paint paint) {
            paint.setBlendMode(BlendMode.SRC);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class Api31Impl {
        private Api31Impl() {
        }

        static Bitmap.Config getHardwareBitmapConfig(Bitmap bitmap) {
            if (bitmap.getHardwareBuffer().getFormat() == 22) {
                return Bitmap.Config.RGBA_F16;
            }
            return Bitmap.Config.ARGB_8888;
        }
    }
}
