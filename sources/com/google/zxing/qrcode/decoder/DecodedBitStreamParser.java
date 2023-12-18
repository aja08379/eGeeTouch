package com.google.zxing.qrcode.decoder;

import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.common.BitSource;
import com.google.zxing.common.CharacterSetECI;
import com.google.zxing.common.StringUtils;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.Map;
/* loaded from: classes2.dex */
final class DecodedBitStreamParser {
    private static final char[] ALPHANUMERIC_CHARS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ $%*+-./:".toCharArray();
    private static final int GB2312_SUBSET = 1;

    private DecodedBitStreamParser() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00e7 A[LOOP:0: B:64:0x001e->B:61:0x00e7, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00c6 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.google.zxing.common.DecoderResult decode(byte[] r17, com.google.zxing.qrcode.decoder.Version r18, com.google.zxing.qrcode.decoder.ErrorCorrectionLevel r19, java.util.Map<com.google.zxing.DecodeHintType, ?> r20) throws com.google.zxing.FormatException {
        com.google.zxing.qrcode.decoder.Mode r1;
        com.google.zxing.qrcode.decoder.Mode r11;
        com.google.zxing.common.BitSource r7 = new com.google.zxing.common.BitSource(r17);
        java.lang.StringBuilder r9 = new java.lang.StringBuilder(50);
        int r11 = 1;
        java.util.ArrayList r10 = new java.util.ArrayList(1);
        boolean r13 = false;
        int r14 = -1;
        int r15 = -1;
        com.google.zxing.common.CharacterSetECI r16 = null;
        while (true) {
            try {
                if (r7.available() < 4) {
                    r1 = com.google.zxing.qrcode.decoder.Mode.TERMINATOR;
                } else {
                    r1 = com.google.zxing.qrcode.decoder.Mode.forBits(r7.readBits(4));
                }
                com.google.zxing.qrcode.decoder.Mode r6 = r1;
                if (r6 != com.google.zxing.qrcode.decoder.Mode.TERMINATOR) {
                    if (r6 != com.google.zxing.qrcode.decoder.Mode.FNC1_FIRST_POSITION && r6 != com.google.zxing.qrcode.decoder.Mode.FNC1_SECOND_POSITION) {
                        if (r6 == com.google.zxing.qrcode.decoder.Mode.STRUCTURED_APPEND) {
                            if (r7.available() < 16) {
                                throw com.google.zxing.FormatException.getFormatInstance();
                            }
                            int r2 = r7.readBits(8);
                            r15 = r7.readBits(8);
                            r14 = r2;
                        } else if (r6 == com.google.zxing.qrcode.decoder.Mode.ECI) {
                            r16 = com.google.zxing.common.CharacterSetECI.getCharacterSetECIByValue(parseECIValue(r7));
                            if (r16 == null) {
                                throw com.google.zxing.FormatException.getFormatInstance();
                            }
                        } else if (r6 == com.google.zxing.qrcode.decoder.Mode.HANZI) {
                            int r1 = r7.readBits(4);
                            int r2 = r7.readBits(r6.getCharacterCountBits(r18));
                            if (r1 == r11) {
                                decodeHanziSegment(r7, r9, r2);
                            }
                        } else {
                            int r3 = r7.readBits(r6.getCharacterCountBits(r18));
                            if (r6 == com.google.zxing.qrcode.decoder.Mode.NUMERIC) {
                                decodeNumericSegment(r7, r9, r3);
                            } else if (r6 == com.google.zxing.qrcode.decoder.Mode.ALPHANUMERIC) {
                                decodeAlphanumericSegment(r7, r9, r3, r13);
                            } else {
                                if (r6 == com.google.zxing.qrcode.decoder.Mode.BYTE) {
                                    r11 = r6;
                                    decodeByteSegment(r7, r9, r3, r16, r10, r20);
                                } else {
                                    r11 = r6;
                                    if (r11 == com.google.zxing.qrcode.decoder.Mode.KANJI) {
                                        decodeKanjiSegment(r7, r9, r3);
                                    } else {
                                        throw com.google.zxing.FormatException.getFormatInstance();
                                    }
                                }
                                if (r11 == com.google.zxing.qrcode.decoder.Mode.TERMINATOR) {
                                    return new com.google.zxing.common.DecoderResult(r17, r9.toString(), r10.isEmpty() ? null : r10, r19 == null ? null : r19.toString(), r14, r15);
                                }
                                r11 = 1;
                            }
                        }
                    }
                    r11 = r6;
                    r13 = true;
                    if (r11 == com.google.zxing.qrcode.decoder.Mode.TERMINATOR) {
                    }
                }
                r11 = r6;
                if (r11 == com.google.zxing.qrcode.decoder.Mode.TERMINATOR) {
                }
            } catch (java.lang.IllegalArgumentException unused) {
                throw com.google.zxing.FormatException.getFormatInstance();
            }
        }
    }

    private static void decodeHanziSegment(BitSource bitSource, StringBuilder sb, int i) throws FormatException {
        if (i * 13 > bitSource.available()) {
            throw FormatException.getFormatInstance();
        }
        byte[] bArr = new byte[i * 2];
        int i2 = 0;
        while (i > 0) {
            int readBits = bitSource.readBits(13);
            int i3 = (readBits % 96) | ((readBits / 96) << 8);
            int i4 = i3 + (i3 < 959 ? 41377 : 42657);
            bArr[i2] = (byte) (i4 >> 8);
            bArr[i2 + 1] = (byte) i4;
            i2 += 2;
            i--;
        }
        try {
            sb.append(new String(bArr, StringUtils.GB2312));
        } catch (UnsupportedEncodingException unused) {
            throw FormatException.getFormatInstance();
        }
    }

    private static void decodeKanjiSegment(BitSource bitSource, StringBuilder sb, int i) throws FormatException {
        if (i * 13 > bitSource.available()) {
            throw FormatException.getFormatInstance();
        }
        byte[] bArr = new byte[i * 2];
        int i2 = 0;
        while (i > 0) {
            int readBits = bitSource.readBits(13);
            int i3 = (readBits % 192) | ((readBits / 192) << 8);
            int i4 = i3 + (i3 < 7936 ? 33088 : 49472);
            bArr[i2] = (byte) (i4 >> 8);
            bArr[i2 + 1] = (byte) i4;
            i2 += 2;
            i--;
        }
        try {
            sb.append(new String(bArr, StringUtils.SHIFT_JIS));
        } catch (UnsupportedEncodingException unused) {
            throw FormatException.getFormatInstance();
        }
    }

    private static void decodeByteSegment(BitSource bitSource, StringBuilder sb, int i, CharacterSetECI characterSetECI, Collection<byte[]> collection, Map<DecodeHintType, ?> map) throws FormatException {
        String name;
        if ((i << 3) > bitSource.available()) {
            throw FormatException.getFormatInstance();
        }
        byte[] bArr = new byte[i];
        for (int i2 = 0; i2 < i; i2++) {
            bArr[i2] = (byte) bitSource.readBits(8);
        }
        if (characterSetECI == null) {
            name = StringUtils.guessEncoding(bArr, map);
        } else {
            name = characterSetECI.name();
        }
        try {
            sb.append(new String(bArr, name));
            collection.add(bArr);
        } catch (UnsupportedEncodingException unused) {
            throw FormatException.getFormatInstance();
        }
    }

    private static char toAlphaNumericChar(int i) throws FormatException {
        char[] cArr = ALPHANUMERIC_CHARS;
        if (i >= cArr.length) {
            throw FormatException.getFormatInstance();
        }
        return cArr[i];
    }

    private static void decodeAlphanumericSegment(BitSource bitSource, StringBuilder sb, int i, boolean z) throws FormatException {
        while (i > 1) {
            if (bitSource.available() < 11) {
                throw FormatException.getFormatInstance();
            }
            int readBits = bitSource.readBits(11);
            sb.append(toAlphaNumericChar(readBits / 45));
            sb.append(toAlphaNumericChar(readBits % 45));
            i -= 2;
        }
        if (i == 1) {
            if (bitSource.available() < 6) {
                throw FormatException.getFormatInstance();
            }
            sb.append(toAlphaNumericChar(bitSource.readBits(6)));
        }
        if (z) {
            for (int length = sb.length(); length < sb.length(); length++) {
                if (sb.charAt(length) == '%') {
                    if (length < sb.length() - 1) {
                        int i2 = length + 1;
                        if (sb.charAt(i2) == '%') {
                            sb.deleteCharAt(i2);
                        }
                    }
                    sb.setCharAt(length, (char) 29);
                }
            }
        }
    }

    private static void decodeNumericSegment(BitSource bitSource, StringBuilder sb, int i) throws FormatException {
        while (i >= 3) {
            if (bitSource.available() < 10) {
                throw FormatException.getFormatInstance();
            }
            int readBits = bitSource.readBits(10);
            if (readBits >= 1000) {
                throw FormatException.getFormatInstance();
            }
            sb.append(toAlphaNumericChar(readBits / 100));
            sb.append(toAlphaNumericChar((readBits / 10) % 10));
            sb.append(toAlphaNumericChar(readBits % 10));
            i -= 3;
        }
        if (i == 2) {
            if (bitSource.available() < 7) {
                throw FormatException.getFormatInstance();
            }
            int readBits2 = bitSource.readBits(7);
            if (readBits2 >= 100) {
                throw FormatException.getFormatInstance();
            }
            sb.append(toAlphaNumericChar(readBits2 / 10));
            sb.append(toAlphaNumericChar(readBits2 % 10));
        } else if (i == 1) {
            if (bitSource.available() < 4) {
                throw FormatException.getFormatInstance();
            }
            int readBits3 = bitSource.readBits(4);
            if (readBits3 >= 10) {
                throw FormatException.getFormatInstance();
            }
            sb.append(toAlphaNumericChar(readBits3));
        }
    }

    private static int parseECIValue(BitSource bitSource) throws FormatException {
        int readBits = bitSource.readBits(8);
        if ((readBits & 128) == 0) {
            return readBits & 127;
        }
        if ((readBits & 192) == 128) {
            return bitSource.readBits(8) | ((readBits & 63) << 8);
        }
        if ((readBits & 224) == 192) {
            return bitSource.readBits(16) | ((readBits & 31) << 16);
        }
        throw FormatException.getFormatInstance();
    }
}
