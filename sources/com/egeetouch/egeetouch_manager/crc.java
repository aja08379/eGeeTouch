package com.egeetouch.egeetouch_manager;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
/* loaded from: classes.dex */
public class crc {
    static long BITMASK(int i) {
        return 1 << i;
    }

    static long reflect(long j, int i) {
        long j2 = j;
        for (int i2 = 0; i2 < i; i2++) {
            if ((1 & j2) != 0) {
                j |= BITMASK((i - 1) - i2);
            } else {
                j &= ~BITMASK((i - 1) - i2);
            }
            j2 >>= 1;
        }
        return j;
    }

    static long widmask(CrcModel crcModel) {
        return (((1 << (crcModel.width - 1)) - 1) << 1) | 1;
    }

    static void cm_ini(CrcModel crcModel) {
        crcModel.reg = crcModel.init;
    }

    static void cm_nxt(CrcModel crcModel, int i) {
        long j = i;
        long BITMASK = BITMASK(crcModel.width - 1);
        if (crcModel.refin) {
            j = reflect(j, 8);
        }
        crcModel.reg = (j << (crcModel.width - 8)) ^ crcModel.reg;
        for (int i2 = 0; i2 < 8; i2++) {
            if ((crcModel.reg & BITMASK) != 0) {
                crcModel.reg = (crcModel.reg << 1) ^ crcModel.poly;
            } else {
                crcModel.reg <<= 1;
            }
            crcModel.reg &= widmask(crcModel);
        }
    }

    static long cm_crc(CrcModel crcModel) {
        long j;
        long j2;
        if (crcModel.refot) {
            j = crcModel.xorot;
            j2 = reflect(crcModel.reg, crcModel.width);
        } else {
            j = crcModel.xorot;
            j2 = crcModel.reg;
        }
        return j ^ j2;
    }

    static long cm_tab(CrcModel crcModel, int i) {
        long BITMASK = BITMASK(crcModel.width - 1);
        long j = i;
        if (crcModel.refin) {
            j = reflect(j, 8);
        }
        long j2 = j << (crcModel.width - 8);
        for (int i2 = 0; i2 < 8; i2++) {
            j2 = (j2 & BITMASK) != 0 ? (j2 << 1) ^ crcModel.poly : j2 << 1;
        }
        if (crcModel.refin) {
            j2 = reflect(j2, crcModel.width);
        }
        return widmask(crcModel) & j2;
    }

    static long CRC(File file) throws IOException {
        char c;
        CrcModel crcModel = new CrcModel();
        byte[] bArr = new byte[4];
        crcModel.width = 32;
        crcModel.poly = 79764919L;
        crcModel.init = -1L;
        crcModel.refin = false;
        crcModel.refot = false;
        crcModel.xorot = 0L;
        cm_ini(crcModel);
        FileInputStream fileInputStream = new FileInputStream(file);
        int i = 0;
        while (true) {
            int read = fileInputStream.read(bArr, 0, 4);
            if (read == -1) {
                fileInputStream.close();
                return cm_crc(crcModel);
            }
            for (int i2 = 0; i2 < 4; i2++) {
                i = (int) (i + ((bArr[i2] & 255) << (i2 * 8)));
            }
            if (read != 0) {
                for (int i3 = 0; i3 < 4; i3++) {
                    if (crcModel.refin) {
                        c = (char) (i & 255);
                        i >>= 8;
                    } else {
                        c = (char) (((-16777216) & i) >>> 24);
                        i <<= 8;
                    }
                    cm_nxt(crcModel, c);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long CRC(byte[] bArr) throws IOException {
        char c;
        CrcModel crcModel = new CrcModel();
        byte[] bArr2 = new byte[4];
        crcModel.width = 32;
        crcModel.poly = 79764919L;
        crcModel.init = -1L;
        crcModel.refin = false;
        crcModel.refot = false;
        crcModel.xorot = 0L;
        cm_ini(crcModel);
        int i = 0;
        for (int i2 = 0; i2 < bArr.length; i2 += 4) {
            Arrays.fill(bArr2, (byte) 0);
            for (int i3 = i2; i3 < bArr.length && i3 < i2 + 4; i3++) {
                bArr2[i3 - i2] = bArr[i3];
            }
            for (int i4 = 0; i4 < 4; i4++) {
                i = (int) (i + ((bArr2[i4] & 255) << (i4 * 8)));
            }
            for (int i5 = 0; i5 < 4 && i5 + i2 < bArr.length; i5++) {
                if (crcModel.refin) {
                    c = (char) (i & 255);
                    i >>= 8;
                } else {
                    c = (char) (((-16777216) & i) >>> 24);
                    i <<= 8;
                }
                cm_nxt(crcModel, c);
            }
        }
        return cm_crc(crcModel);
    }
}
