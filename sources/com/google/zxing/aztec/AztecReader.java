package com.google.zxing.aztec;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.Result;
/* loaded from: classes2.dex */
public final class AztecReader implements Reader {
    @Override // com.google.zxing.Reader
    public void reset() {
    }

    @Override // com.google.zxing.Reader
    public Result decode(BinaryBitmap binaryBitmap) throws NotFoundException, FormatException {
        return decode(binaryBitmap, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x005e A[LOOP:0: B:33:0x005c->B:34:0x005e, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x008f  */
    @Override // com.google.zxing.Reader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.google.zxing.Result decode(com.google.zxing.BinaryBitmap r11, java.util.Map<com.google.zxing.DecodeHintType, ?> r12) throws com.google.zxing.NotFoundException, com.google.zxing.FormatException {
        com.google.zxing.ResultPoint[] r3;
        com.google.zxing.ResultPoint[] r4;
        com.google.zxing.FormatException r3;
        com.google.zxing.ResultPoint[] r6;
        java.util.List<byte[]> r12;
        java.lang.String r12;
        com.google.zxing.ResultPointCallback r12;
        com.google.zxing.aztec.detector.Detector r0 = new com.google.zxing.aztec.detector.Detector(r11.getBlackMatrix());
        com.google.zxing.common.DecoderResult r1 = null;
        try {
            com.google.zxing.aztec.AztecDetectorResult r2 = r0.detect(false);
            r3 = r2.getPoints();
            try {
                r4 = r3;
                r3 = null;
                r1 = new com.google.zxing.aztec.decoder.Decoder().decode(r2);
                r2 = null;
            } catch (com.google.zxing.FormatException e) {
                r2 = e;
                r4 = r3;
                r3 = r2;
                r2 = null;
                if (r1 == null) {
                }
                r6 = r4;
                if (r12 != null) {
                    while (r11 < r0) {
                    }
                }
                com.google.zxing.Result r11 = new com.google.zxing.Result(r1.getText(), r1.getRawBytes(), r1.getNumBits(), r6, com.google.zxing.BarcodeFormat.AZTEC, java.lang.System.currentTimeMillis());
                r12 = r1.getByteSegments();
                if (r12 != null) {
                }
                r12 = r1.getECLevel();
                if (r12 != null) {
                }
                return r11;
            } catch (com.google.zxing.NotFoundException e) {
                r2 = e;
                r4 = r3;
                r3 = null;
                if (r1 == null) {
                }
                r6 = r4;
                if (r12 != null) {
                }
                com.google.zxing.Result r11 = new com.google.zxing.Result(r1.getText(), r1.getRawBytes(), r1.getNumBits(), r6, com.google.zxing.BarcodeFormat.AZTEC, java.lang.System.currentTimeMillis());
                r12 = r1.getByteSegments();
                if (r12 != null) {
                }
                r12 = r1.getECLevel();
                if (r12 != null) {
                }
                return r11;
            }
        } catch (com.google.zxing.FormatException e) {
            r2 = e;
            r3 = null;
        } catch (com.google.zxing.NotFoundException e) {
            r2 = e;
            r3 = null;
        }
        if (r1 == null) {
            try {
                com.google.zxing.aztec.AztecDetectorResult r0 = r0.detect(true);
                r4 = r0.getPoints();
                r1 = new com.google.zxing.aztec.decoder.Decoder().decode(r0);
            } catch (com.google.zxing.FormatException | com.google.zxing.NotFoundException r11) {
                if (r2 == null) {
                    if (r3 != null) {
                        throw r3;
                    }
                    throw r11;
                }
                throw r2;
            }
        }
        r6 = r4;
        if (r12 != null && (r12 = (com.google.zxing.ResultPointCallback) r12.get(com.google.zxing.DecodeHintType.NEED_RESULT_POINT_CALLBACK)) != null) {
            for (com.google.zxing.ResultPoint r2 : r6) {
                r12.foundPossibleResultPoint(r2);
            }
        }
        com.google.zxing.Result r11 = new com.google.zxing.Result(r1.getText(), r1.getRawBytes(), r1.getNumBits(), r6, com.google.zxing.BarcodeFormat.AZTEC, java.lang.System.currentTimeMillis());
        r12 = r1.getByteSegments();
        if (r12 != null) {
            r11.putMetadata(com.google.zxing.ResultMetadataType.BYTE_SEGMENTS, r12);
        }
        r12 = r1.getECLevel();
        if (r12 != null) {
            r11.putMetadata(com.google.zxing.ResultMetadataType.ERROR_CORRECTION_LEVEL, r12);
        }
        return r11;
    }
}
