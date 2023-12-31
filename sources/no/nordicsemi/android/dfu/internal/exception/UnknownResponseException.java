package no.nordicsemi.android.dfu.internal.exception;

import java.util.Locale;
import kotlin.UByte;
/* loaded from: classes2.dex */
public class UnknownResponseException extends Exception {
    private static final char[] HEX_ARRAY = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private static final long serialVersionUID = -8716125467309979289L;
    private final int mExpectedOpCode;
    private final int mExpectedReturnCode;
    private final byte[] mResponse;

    public UnknownResponseException(String str, byte[] bArr, int i, int i2) {
        super(str);
        this.mResponse = bArr == null ? new byte[0] : bArr;
        this.mExpectedReturnCode = i;
        this.mExpectedOpCode = i2;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        Locale locale = Locale.US;
        byte[] bArr = this.mResponse;
        return String.format(locale, "%s (response: %s, expected: 0x%02X%02X..)", super.getMessage(), bytesToHex(bArr, 0, bArr.length), Integer.valueOf(this.mExpectedReturnCode), Integer.valueOf(this.mExpectedOpCode));
    }

    public static String bytesToHex(byte[] bArr, int i, int i2) {
        if (bArr == null || bArr.length <= i || i2 <= 0) {
            return "";
        }
        int min = Math.min(i2, bArr.length - i);
        char[] cArr = new char[min * 2];
        for (int i3 = 0; i3 < min; i3++) {
            int i4 = bArr[i + i3] & UByte.MAX_VALUE;
            int i5 = i3 * 2;
            char[] cArr2 = HEX_ARRAY;
            cArr[i5] = cArr2[i4 >>> 4];
            cArr[i5 + 1] = cArr2[i4 & 15];
        }
        return "0x" + new String(cArr);
    }
}
