package com.egeetouch.egeetouch_manager;

import com.facebook.appevents.AppEventsConstants;
import kotlin.UByte;
/* loaded from: classes.dex */
public class Helper_NFC {
    public static int Convert1bytesHexaFormatToInt(byte b) {
        int i = b;
        if (b <= -1) {
            i = b + 256;
        }
        return i + 0;
    }

    public static String castHexKeyboard(String str) {
        String upperCase = str.toUpperCase();
        char[] charArray = upperCase.toCharArray();
        String str2 = "";
        for (int i = 0; i < upperCase.length(); i++) {
            if (charArray[i] != '0' && charArray[i] != '1' && charArray[i] != '2' && charArray[i] != '3' && charArray[i] != '4' && charArray[i] != '5' && charArray[i] != '6' && charArray[i] != '7' && charArray[i] != '8' && charArray[i] != '9' && charArray[i] != 'A' && charArray[i] != 'B' && charArray[i] != 'C' && charArray[i] != 'D' && charArray[i] != 'E') {
                charArray[i] = 'F';
            }
            str2 = str2 + charArray[i];
        }
        return str2;
    }

    public static boolean checkDataHexa(String str) {
        String upperCase = str.toUpperCase();
        char[] charArray = upperCase.toCharArray();
        boolean z = true;
        for (int i = 0; i < upperCase.length(); i++) {
            if (charArray[i] != '0' && charArray[i] != '1' && charArray[i] != '2' && charArray[i] != '3' && charArray[i] != '4' && charArray[i] != '5' && charArray[i] != '6' && charArray[i] != '7' && charArray[i] != '8' && charArray[i] != '9' && charArray[i] != 'A' && charArray[i] != 'B' && charArray[i] != 'C' && charArray[i] != 'D' && charArray[i] != 'E' && charArray[i] != 'F') {
                z = false;
            }
        }
        return z;
    }

    public static String checkAndChangeDataHexa(String str) {
        String upperCase = str.toUpperCase();
        char[] charArray = upperCase.toCharArray();
        String str2 = "";
        for (int i = 0; i < upperCase.length(); i++) {
            if (charArray[i] == '0' || charArray[i] == '1' || charArray[i] == '2' || charArray[i] == '3' || charArray[i] == '4' || charArray[i] == '5' || charArray[i] == '6' || charArray[i] == '7' || charArray[i] == '8' || charArray[i] == '9' || charArray[i] == 'A' || charArray[i] == 'B' || charArray[i] == 'C' || charArray[i] == 'D' || charArray[i] == 'E' || charArray[i] == 'F') {
                str2 = str2 + charArray[i];
            }
        }
        return str2;
    }

    public static boolean checkFileName(String str) {
        char[] charArray = str.toCharArray();
        boolean z = true;
        for (int i = 0; i < str.length(); i++) {
            if (charArray[i] != '0' && charArray[i] != '1' && charArray[i] != '2' && charArray[i] != '3' && charArray[i] != '4' && charArray[i] != '5' && charArray[i] != '6' && charArray[i] != '7' && charArray[i] != '8' && charArray[i] != '9' && charArray[i] != 'a' && charArray[i] != 'b' && charArray[i] != 'c' && charArray[i] != 'd' && charArray[i] != 'e' && charArray[i] != 'f' && charArray[i] != 'g' && charArray[i] != 'h' && charArray[i] != 'i' && charArray[i] != 'j' && charArray[i] != 'k' && charArray[i] != 'l' && charArray[i] != 'm' && charArray[i] != 'n' && charArray[i] != 'o' && charArray[i] != 'p' && charArray[i] != 'q' && charArray[i] != 'r' && charArray[i] != 's' && charArray[i] != 't' && charArray[i] != 'u' && charArray[i] != 'v' && charArray[i] != 'w' && charArray[i] != 'x' && charArray[i] != 'y' && charArray[i] != 'z' && charArray[i] != 'A' && charArray[i] != 'B' && charArray[i] != 'C' && charArray[i] != 'D' && charArray[i] != 'E' && charArray[i] != 'F' && charArray[i] != 'G' && charArray[i] != 'H' && charArray[i] != 'I' && charArray[i] != 'J' && charArray[i] != 'K' && charArray[i] != 'L' && charArray[i] != 'M' && charArray[i] != 'N' && charArray[i] != 'O' && charArray[i] != 'P' && charArray[i] != 'Q' && charArray[i] != 'R' && charArray[i] != 'S' && charArray[i] != 'T' && charArray[i] != 'U' && charArray[i] != 'V' && charArray[i] != 'W' && charArray[i] != 'X' && charArray[i] != 'Y' && charArray[i] != 'Z' && charArray[i] != '.' && charArray[i] != '_') {
                z = false;
            }
        }
        return z;
    }

    public static String checkAndChangeFileName(String str) {
        char[] charArray = str.toCharArray();
        String str2 = "";
        for (int i = 0; i < str.length(); i++) {
            if (charArray[i] == '0' || charArray[i] == '1' || charArray[i] == '2' || charArray[i] == '3' || charArray[i] == '4' || charArray[i] == '5' || charArray[i] == '6' || charArray[i] == '7' || charArray[i] == '8' || charArray[i] == '9' || charArray[i] == 'a' || charArray[i] == 'b' || charArray[i] == 'c' || charArray[i] == 'd' || charArray[i] == 'e' || charArray[i] == 'f' || charArray[i] == 'g' || charArray[i] == 'h' || charArray[i] == 'i' || charArray[i] == 'j' || charArray[i] == 'k' || charArray[i] == 'l' || charArray[i] == 'm' || charArray[i] == 'n' || charArray[i] == 'o' || charArray[i] == 'p' || charArray[i] == 'q' || charArray[i] == 'r' || charArray[i] == 's' || charArray[i] == 't' || charArray[i] == 'u' || charArray[i] == 'v' || charArray[i] == 'w' || charArray[i] == 'x' || charArray[i] == 'y' || charArray[i] == 'z' || charArray[i] == 'A' || charArray[i] == 'B' || charArray[i] == 'C' || charArray[i] == 'D' || charArray[i] == 'E' || charArray[i] == 'F' || charArray[i] == 'G' || charArray[i] == 'H' || charArray[i] == 'I' || charArray[i] == 'J' || charArray[i] == 'K' || charArray[i] == 'L' || charArray[i] == 'M' || charArray[i] == 'N' || charArray[i] == 'O' || charArray[i] == 'P' || charArray[i] == 'Q' || charArray[i] == 'R' || charArray[i] == 'S' || charArray[i] == 'T' || charArray[i] == 'U' || charArray[i] == 'V' || charArray[i] == 'W' || charArray[i] == 'X' || charArray[i] == 'Y' || charArray[i] == 'Z' || charArray[i] == '.' || charArray[i] == '_') {
                str2 = str2 + charArray[i];
            }
        }
        return str2;
    }

    public static String StringForceDigit(String str, int i) {
        String replaceAll = str.replaceAll(" ", "");
        if (replaceAll.length() == 4) {
            return replaceAll;
        }
        if (replaceAll.length() < i) {
            while (replaceAll.length() != i) {
                replaceAll = AppEventsConstants.EVENT_PARAM_VALUE_NO.concat(replaceAll);
            }
        }
        return replaceAll;
    }

    public static String ConvertHexByteToString(byte b) {
        if (b < 0) {
            return "" + Integer.toString(b + UByte.MIN_VALUE, 16) + " ";
        }
        if (b <= 15) {
            return "" + AppEventsConstants.EVENT_PARAM_VALUE_NO + Integer.toString(b, 16) + " ";
        }
        return "" + Integer.toString(b, 16) + " ";
    }

    public static String ConvertHexByteArrayToString(byte[] bArr) {
        String str = "";
        for (int i = 0; i < bArr.length; i++) {
            if (bArr[i] < 0) {
                str = str + Integer.toString(bArr[i] + UByte.MIN_VALUE, 16) + " ";
            } else if (bArr[i] <= 15) {
                str = str + AppEventsConstants.EVENT_PARAM_VALUE_NO + Integer.toString(bArr[i], 16) + " ";
            } else {
                str = str + Integer.toString(bArr[i], 16) + " ";
            }
        }
        return str;
    }

    public static String ConvertHexByteArrayToString2(byte[] bArr) {
        String str = "";
        for (int i = 0; i < bArr.length; i++) {
            if (bArr[i] < 0) {
                str = str + Integer.toString(bArr[i] + UByte.MIN_VALUE, 16) + ":";
            } else if (bArr[i] <= 15) {
                str = str + AppEventsConstants.EVENT_PARAM_VALUE_NO + Integer.toString(bArr[i], 16) + ":";
            } else {
                str = str + Integer.toString(bArr[i], 16) + ":";
            }
        }
        return str + 15;
    }

    public static String FormatStringAddressStart(String str, DataDevice dataDevice) {
        String StringForceDigit = StringForceDigit(str, 4);
        if (StringForceDigit.length() > 4) {
            StringForceDigit = "11";
        }
        int ConvertStringToInt = ConvertStringToInt(StringForceDigit);
        int ConvertStringToInt2 = ConvertStringToInt("11");
        if (ConvertStringToInt > ConvertStringToInt2) {
            ConvertStringToInt = ConvertStringToInt2;
        }
        return ConvertIntToHexFormatString(ConvertStringToInt).toUpperCase();
    }

    public static String FormatValueByteWrite(String str) {
        return castHexKeyboard(StringForceDigit(str, 2)).toUpperCase();
    }

    public static String ConvertIntToHexFormatString(int i) {
        return ConvertHexByteArrayToString(ConvertIntTo2bytesHexaFormat(i)).replaceAll(" ", "");
    }

    public static String FormatStringNbBlock(String str, String str2, DataDevice dataDevice) {
        String StringForceDigit = StringForceDigit(str, 4);
        if (StringForceDigit.length() > 4) {
            StringForceDigit = "11";
        }
        int ConvertStringToInt = ConvertStringToInt(StringForceDigit);
        int ConvertStringToInt2 = ConvertStringToInt(str2);
        if (ConvertStringToInt2 + ConvertStringToInt > 11) {
            ConvertStringToInt = (11 - ConvertStringToInt2) + 1;
        }
        return StringForceDigit(ConvertIntToHexFormatString(ConvertStringToInt), 4);
    }

    public static String FormatStringNbBlockInteger(String str, String str2, DataDevice dataDevice) {
        String StringForceDigit = StringForceDigit(str, 4);
        if (StringForceDigit.length() > 4) {
            StringForceDigit = "11";
        }
        int parseInt = Integer.parseInt(StringForceDigit);
        int ConvertStringToInt = ConvertStringToInt(str2);
        int ConvertStringToInt2 = ConvertStringToInt("11");
        if (ConvertStringToInt + parseInt > ConvertStringToInt2 + 1) {
            parseInt = (ConvertStringToInt2 - ConvertStringToInt) + 1;
        }
        return StringForceDigit(Integer.toString(parseInt, 10), 4);
    }

    public static byte[] ConvertStringToHexBytes(String str) {
        char[] charArray = str.toUpperCase().replaceAll(" ", "").toCharArray();
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        byte[] bArr = {0, 0};
        int i = 0;
        for (int i2 = 0; i2 <= 1; i2++) {
            int i3 = 0;
            while (i3 <= 15) {
                if (charArray[i2] == cArr[i3]) {
                    if (i2 != 1) {
                        if (i2 == 0) {
                            i3 *= 16;
                        }
                    }
                    i += i3;
                    i3 = 15;
                }
                i3++;
            }
        }
        bArr[0] = (byte) i;
        int i4 = 0;
        for (int i5 = 2; i5 <= 3; i5++) {
            int i6 = 0;
            while (i6 <= 15) {
                if (charArray[i5] == cArr[i6]) {
                    if (i5 != 3) {
                        if (i5 == 2) {
                            i6 *= 16;
                        }
                    }
                    i4 += i6;
                    i6 = 15;
                }
                i6++;
            }
        }
        bArr[1] = (byte) i4;
        return bArr;
    }

    public static byte[] ConvertStringToHexBytesArray(String str) {
        String replaceAll = str.toUpperCase().replaceAll(" ", "");
        char[] charArray = replaceAll.toCharArray();
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        byte[] bArr = new byte[replaceAll.length() / 2];
        int length = replaceAll.length();
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = 0;
            while (i3 <= 15) {
                if (charArray[i2] == cArr[i3]) {
                    int i4 = i2 % 2;
                    if (i4 != 1) {
                        if (i4 == 0) {
                            i3 *= 16;
                        }
                    }
                    i += i3;
                    i3 = 15;
                }
                i3++;
            }
            if (i2 % 2 == 1) {
                bArr[i2 / 2] = (byte) i;
                i = 0;
            }
        }
        return bArr;
    }

    public static byte ConvertStringToHexByte(String str) {
        char[] charArray = str.toUpperCase().toCharArray();
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        int i = 0;
        for (int i2 = 0; i2 <= 1; i2++) {
            int i3 = 0;
            while (i3 <= 15) {
                if (charArray[i2] == cArr[i3]) {
                    if (i2 != 1) {
                        if (i2 == 0) {
                            i3 *= 16;
                        }
                    }
                    i += i3;
                    i3 = 15;
                }
                i3++;
            }
        }
        return (byte) i;
    }

    public static byte[] ConvertIntTo2bytesHexaFormat(int i) {
        int i2 = i / 256;
        return new byte[]{(byte) i2, (byte) (i - (i2 * 256))};
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static int Convert2bytesHexaFormatToInt(byte[] bArr) {
        int i;
        int i2;
        if (bArr[1] <= -1) {
            i = bArr[1] + 256;
        } else {
            i = bArr[1];
        }
        int i3 = i + 0;
        if (bArr[0] <= -1) {
            i2 = bArr[0] + 256;
        } else {
            i2 = bArr[0];
        }
        return i3 + (i2 * 256);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static int Convert4bytesHexaFormatToInt(byte[] bArr) {
        int i;
        int i2;
        int i3;
        int i4;
        if (bArr[3] <= -1) {
            i = bArr[3] + 256;
        } else {
            i = bArr[3];
        }
        int i5 = i + 0;
        if (bArr[2] <= -1) {
            i2 = bArr[2] + 256;
        } else {
            i2 = bArr[2];
        }
        int i6 = i5 + (i2 * 256);
        if (bArr[1] <= -1) {
            i3 = bArr[1] + 256;
        } else {
            i3 = bArr[1];
        }
        int i7 = i6 + (i3 * 256 * 256);
        if (bArr[0] <= -1) {
            i4 = bArr[0] + 256;
        } else {
            i4 = bArr[0];
        }
        return i7 + (i4 * 256 * 256 * 256);
    }

    public static int ConvertStringToInt(String str) {
        if (str.length() > 2) {
            return Integer.parseInt(str.substring(2, 4), 16) + (Integer.parseInt(str.substring(0, 2), 16) * 256);
        }
        return Integer.parseInt(str.substring(0, 2), 16);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static String[] buildArrayBlocks(byte[] bArr, int i) {
        int i2;
        String[] strArr = new String[i];
        int i3 = bArr[1];
        if (bArr[1] < 0) {
            i3 = bArr[1] + 256;
        }
        int i4 = 0;
        if (bArr[0] < 0) {
            i2 = bArr[0] + 256;
        } else {
            i2 = bArr[0];
        }
        int i5 = i3 + (i2 * 256);
        while (i4 < i) {
            if (i4 == 14) {
                i4 = 14;
            }
            strArr[i4] = "Block  " + ConvertIntToHexFormatString(i4 + i5).toUpperCase();
            i4++;
        }
        return strArr;
    }

    public static String[] buildArrayValueBlocks(byte[] bArr, int i) {
        String[] strArr = new String[i];
        int i2 = 1;
        for (int i3 = 0; i3 < i; i3++) {
            strArr[i3] = "";
            strArr[i3] = strArr[i3] + ConvertHexByteToString(bArr[i2]).toUpperCase();
            strArr[i3] = strArr[i3] + " ";
            strArr[i3] = strArr[i3] + ConvertHexByteToString(bArr[i2 + 1]).toUpperCase();
            strArr[i3] = strArr[i3] + " ";
            strArr[i3] = strArr[i3] + ConvertHexByteToString(bArr[i2 + 2]).toUpperCase();
            strArr[i3] = strArr[i3] + " ";
            strArr[i3] = strArr[i3] + ConvertHexByteToString(bArr[i2 + 3]).toUpperCase();
            i2 += 4;
        }
        return strArr;
    }
}
