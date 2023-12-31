package com.google.zxing.pdf417.encoder;

import com.facebook.appevents.AppEventsConstants;
import com.google.zxing.WriterException;
import com.google.zxing.common.CharacterSetECI;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.Arrays;
import kotlin.UByte;
/* loaded from: classes2.dex */
final class PDF417HighLevelEncoder {
    private static final int BYTE_COMPACTION = 1;
    private static final int ECI_CHARSET = 927;
    private static final int ECI_GENERAL_PURPOSE = 926;
    private static final int ECI_USER_DEFINED = 925;
    private static final int LATCH_TO_BYTE = 924;
    private static final int LATCH_TO_BYTE_PADDED = 901;
    private static final int LATCH_TO_NUMERIC = 902;
    private static final int LATCH_TO_TEXT = 900;
    private static final byte[] MIXED;
    private static final int NUMERIC_COMPACTION = 2;
    private static final int SHIFT_TO_BYTE = 913;
    private static final int SUBMODE_ALPHA = 0;
    private static final int SUBMODE_LOWER = 1;
    private static final int SUBMODE_MIXED = 2;
    private static final int SUBMODE_PUNCTUATION = 3;
    private static final int TEXT_COMPACTION = 0;
    private static final byte[] TEXT_MIXED_RAW = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 38, 13, 9, 44, 58, 35, 45, 46, 36, 47, 43, 37, 42, 61, 94, 0, 32, 0, 0, 0};
    private static final byte[] TEXT_PUNCTUATION_RAW = {59, 60, 62, 64, 91, 92, 93, 95, 96, 126, 33, 13, 9, 44, 58, 10, 45, 46, 36, 47, 34, 124, 42, 40, 41, 63, 123, 125, 39, 0};
    private static final byte[] PUNCTUATION = new byte[128];
    private static final Charset DEFAULT_ENCODING = Charset.forName("ISO-8859-1");

    private static boolean isAlphaLower(char c) {
        if (c != ' ') {
            return c >= 'a' && c <= 'z';
        }
        return true;
    }

    private static boolean isAlphaUpper(char c) {
        if (c != ' ') {
            return c >= 'A' && c <= 'Z';
        }
        return true;
    }

    private static boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    private static boolean isText(char c) {
        if (c == '\t' || c == '\n' || c == '\r') {
            return true;
        }
        return c >= ' ' && c <= '~';
    }

    static {
        byte[] bArr = new byte[128];
        MIXED = bArr;
        Arrays.fill(bArr, (byte) -1);
        int i = 0;
        int i2 = 0;
        while (true) {
            byte[] bArr2 = TEXT_MIXED_RAW;
            if (i2 >= bArr2.length) {
                break;
            }
            byte b = bArr2[i2];
            if (b > 0) {
                MIXED[b] = (byte) i2;
            }
            i2++;
        }
        Arrays.fill(PUNCTUATION, (byte) -1);
        while (true) {
            byte[] bArr3 = TEXT_PUNCTUATION_RAW;
            if (i >= bArr3.length) {
                return;
            }
            byte b2 = bArr3[i];
            if (b2 > 0) {
                PUNCTUATION[b2] = (byte) i;
            }
            i++;
        }
    }

    private PDF417HighLevelEncoder() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String encodeHighLevel(String str, Compaction compaction, Charset charset) throws WriterException {
        CharacterSetECI characterSetECIByName;
        StringBuilder sb = new StringBuilder(str.length());
        if (charset == null) {
            charset = DEFAULT_ENCODING;
        } else if (!DEFAULT_ENCODING.equals(charset) && (characterSetECIByName = CharacterSetECI.getCharacterSetECIByName(charset.name())) != null) {
            encodingECI(characterSetECIByName.getValue(), sb);
        }
        int length = str.length();
        if (compaction == Compaction.TEXT) {
            encodeText(str, 0, length, sb, 0);
        } else if (compaction == Compaction.BYTE) {
            byte[] bytes = str.getBytes(charset);
            encodeBinary(bytes, 0, bytes.length, 1, sb);
        } else if (compaction == Compaction.NUMERIC) {
            sb.append((char) 902);
            encodeNumeric(str, 0, length, sb);
        } else {
            int i = 0;
            int i2 = 0;
            int i3 = 0;
            while (i < length) {
                int determineConsecutiveDigitCount = determineConsecutiveDigitCount(str, i);
                if (determineConsecutiveDigitCount >= 13) {
                    sb.append((char) 902);
                    i3 = 2;
                    encodeNumeric(str, i, determineConsecutiveDigitCount, sb);
                    i += determineConsecutiveDigitCount;
                    i2 = 0;
                } else {
                    int determineConsecutiveTextCount = determineConsecutiveTextCount(str, i);
                    if (determineConsecutiveTextCount >= 5 || determineConsecutiveDigitCount == length) {
                        if (i3 != 0) {
                            sb.append((char) 900);
                            i2 = 0;
                            i3 = 0;
                        }
                        i2 = encodeText(str, i, determineConsecutiveTextCount, sb, i2);
                        i += determineConsecutiveTextCount;
                    } else {
                        int determineConsecutiveBinaryCount = determineConsecutiveBinaryCount(str, i, charset);
                        if (determineConsecutiveBinaryCount == 0) {
                            determineConsecutiveBinaryCount = 1;
                        }
                        int i4 = determineConsecutiveBinaryCount + i;
                        byte[] bytes2 = str.substring(i, i4).getBytes(charset);
                        if (bytes2.length == 1 && i3 == 0) {
                            encodeBinary(bytes2, 0, 1, 0, sb);
                        } else {
                            encodeBinary(bytes2, 0, bytes2.length, i3, sb);
                            i2 = 0;
                            i3 = 1;
                        }
                        i = i4;
                    }
                }
            }
        }
        return sb.toString();
    }

    /* JADX WARN: Removed duplicated region for block: B:76:0x00f6 A[EDGE_INSN: B:76:0x00f6->B:55:0x00f6 ?: BREAK  , SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0011 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static int encodeText(java.lang.CharSequence r16, int r17, int r18, java.lang.StringBuilder r19, int r20) {
        java.lang.StringBuilder r3 = new java.lang.StringBuilder(r18);
        int r7 = r20;
        int r8 = 0;
        while (true) {
            int r9 = r17 + r8;
            char r10 = r16.charAt(r9);
            if (r7 != 0) {
                if (r7 != 1) {
                    if (r7 == 2) {
                        if (isMixed(r10)) {
                            r3.append((char) com.google.zxing.pdf417.encoder.PDF417HighLevelEncoder.MIXED[r10]);
                        } else if (isAlphaUpper(r10)) {
                            r3.append((char) 28);
                            r7 = 0;
                        } else if (isAlphaLower(r10)) {
                            r3.append((char) 27);
                            r7 = 1;
                        } else {
                            int r9 = r9 + 1;
                            if (r9 < r18 && isPunctuation(r16.charAt(r9))) {
                                r7 = 3;
                                r3.append((char) 25);
                            } else {
                                r3.append((char) 29);
                                r3.append((char) com.google.zxing.pdf417.encoder.PDF417HighLevelEncoder.PUNCTUATION[r10]);
                            }
                        }
                    } else if (isPunctuation(r10)) {
                        r3.append((char) com.google.zxing.pdf417.encoder.PDF417HighLevelEncoder.PUNCTUATION[r10]);
                    } else {
                        r3.append((char) 29);
                        r7 = 0;
                    }
                } else if (isAlphaLower(r10)) {
                    if (r10 == ' ') {
                        r3.append((char) 26);
                    } else {
                        r3.append((char) (r10 - 'a'));
                    }
                } else if (isAlphaUpper(r10)) {
                    r3.append((char) 27);
                    r3.append((char) (r10 - 'A'));
                } else if (isMixed(r10)) {
                    r3.append((char) 28);
                    r7 = 2;
                } else {
                    r3.append((char) 29);
                    r3.append((char) com.google.zxing.pdf417.encoder.PDF417HighLevelEncoder.PUNCTUATION[r10]);
                }
                r8++;
                if (r8 < r18) {
                    break;
                }
            } else {
                if (isAlphaUpper(r10)) {
                    if (r10 == ' ') {
                        r3.append((char) 26);
                    } else {
                        r3.append((char) (r10 - 'A'));
                    }
                } else if (isAlphaLower(r10)) {
                    r3.append((char) 27);
                    r7 = 1;
                } else if (isMixed(r10)) {
                    r3.append((char) 28);
                    r7 = 2;
                } else {
                    r3.append((char) 29);
                    r3.append((char) com.google.zxing.pdf417.encoder.PDF417HighLevelEncoder.PUNCTUATION[r10]);
                }
                r8++;
                if (r8 < r18) {
                }
            }
        }
        int r0 = r3.length();
        char r8 = 0;
        for (int r1 = 0; r1 < r0; r1++) {
            if (r1 % 2 != 0) {
                r8 = (char) ((r8 * 30) + r3.charAt(r1));
                r19.append(r8);
            } else {
                r8 = r3.charAt(r1);
            }
        }
        if (r0 % 2 != 0) {
            r19.append((char) ((r8 * 30) + 29));
        }
        return r7;
    }

    private static void encodeBinary(byte[] bArr, int i, int i2, int i3, StringBuilder sb) {
        int i4;
        if (i2 == 1 && i3 == 0) {
            sb.append((char) 913);
        } else if (i2 % 6 == 0) {
            sb.append((char) 924);
        } else {
            sb.append((char) 901);
        }
        if (i2 >= 6) {
            char[] cArr = new char[5];
            i4 = i;
            while ((i + i2) - i4 >= 6) {
                long j = 0;
                for (int i5 = 0; i5 < 6; i5++) {
                    j = (j << 8) + (bArr[i4 + i5] & UByte.MAX_VALUE);
                }
                for (int i6 = 0; i6 < 5; i6++) {
                    cArr[i6] = (char) (j % 900);
                    j /= 900;
                }
                for (int i7 = 4; i7 >= 0; i7--) {
                    sb.append(cArr[i7]);
                }
                i4 += 6;
            }
        } else {
            i4 = i;
        }
        while (i4 < i + i2) {
            sb.append((char) (bArr[i4] & UByte.MAX_VALUE));
            i4++;
        }
    }

    private static void encodeNumeric(String str, int i, int i2, StringBuilder sb) {
        StringBuilder sb2 = new StringBuilder((i2 / 3) + 1);
        BigInteger valueOf = BigInteger.valueOf(900L);
        BigInteger valueOf2 = BigInteger.valueOf(0L);
        int i3 = 0;
        while (i3 < i2) {
            sb2.setLength(0);
            int min = Math.min(44, i2 - i3);
            int i4 = i + i3;
            BigInteger bigInteger = new BigInteger(AppEventsConstants.EVENT_PARAM_VALUE_YES + str.substring(i4, i4 + min));
            do {
                sb2.append((char) bigInteger.mod(valueOf).intValue());
                bigInteger = bigInteger.divide(valueOf);
            } while (!bigInteger.equals(valueOf2));
            for (int length = sb2.length() - 1; length >= 0; length--) {
                sb.append(sb2.charAt(length));
            }
            i3 += min;
        }
    }

    private static boolean isMixed(char c) {
        return MIXED[c] != -1;
    }

    private static boolean isPunctuation(char c) {
        return PUNCTUATION[c] != -1;
    }

    private static int determineConsecutiveDigitCount(CharSequence charSequence, int i) {
        int length = charSequence.length();
        int i2 = 0;
        if (i < length) {
            char charAt = charSequence.charAt(i);
            while (isDigit(charAt) && i < length) {
                i2++;
                i++;
                if (i < length) {
                    charAt = charSequence.charAt(i);
                }
            }
        }
        return i2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0027, code lost:
        return (r1 - r7) - r3;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static int determineConsecutiveTextCount(java.lang.CharSequence r6, int r7) {
        int r0 = r6.length();
        int r1 = r7;
        while (r1 < r0) {
            char r2 = r6.charAt(r1);
            int r3 = 0;
            while (r3 < 13 && isDigit(r2) && r1 < r0) {
                r3++;
                r1++;
                if (r1 < r0) {
                    r2 = r6.charAt(r1);
                }
            }
            if (r3 <= 0) {
                if (!isText(r6.charAt(r1))) {
                    break;
                }
                r1++;
            }
        }
        return r1 - r7;
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0028, code lost:
        return r1 - r6;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static int determineConsecutiveBinaryCount(java.lang.String r5, int r6, java.nio.charset.Charset r7) throws com.google.zxing.WriterException {
        int r2;
        java.nio.charset.CharsetEncoder r7 = r7.newEncoder();
        int r0 = r5.length();
        int r1 = r6;
        while (r1 < r0) {
            char r2 = r5.charAt(r1);
            int r3 = 0;
            while (r3 < 13 && isDigit(r2) && (r2 = r1 + (r3 = r3 + 1)) < r0) {
                r2 = r5.charAt(r2);
            }
            char r2 = r5.charAt(r1);
            if (!r7.canEncode(r2)) {
                throw new com.google.zxing.WriterException("Non-encodable character detected: " + r2 + " (Unicode: " + ((int) r2) + ')');
            }
            r1++;
        }
        return r1 - r6;
    }

    private static void encodingECI(int i, StringBuilder sb) throws WriterException {
        if (i >= 0 && i < 900) {
            sb.append((char) 927);
            sb.append((char) i);
        } else if (i < 810900) {
            sb.append((char) 926);
            sb.append((char) ((i / 900) - 1));
            sb.append((char) (i % 900));
        } else if (i < 811800) {
            sb.append((char) 925);
            sb.append((char) (810900 - i));
        } else {
            throw new WriterException("ECI number not in valid range from 0..811799, but was " + i);
        }
    }
}
