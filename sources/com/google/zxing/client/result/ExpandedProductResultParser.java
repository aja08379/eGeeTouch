package com.google.zxing.client.result;
/* loaded from: classes2.dex */
public final class ExpandedProductResultParser extends ResultParser {
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:143:0x020e, code lost:
        if (r1.equals("10") == false) goto L12;
     */
    @Override // com.google.zxing.client.result.ResultParser
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.google.zxing.client.result.ExpandedProductParsedResult parse(com.google.zxing.Result r24) {
        com.google.zxing.client.result.ExpandedProductParsedResult r2 = null;
        if (r24.getBarcodeFormat() != com.google.zxing.BarcodeFormat.RSS_EXPANDED) {
            return null;
        }
        java.lang.String r4 = getMassagedText(r24);
        java.util.HashMap r0 = new java.util.HashMap();
        java.lang.String r5 = null;
        java.lang.String r6 = null;
        java.lang.String r7 = null;
        java.lang.String r8 = null;
        java.lang.String r9 = null;
        java.lang.String r10 = null;
        java.lang.String r11 = null;
        java.lang.String r12 = null;
        java.lang.String r13 = null;
        java.lang.String r14 = null;
        java.lang.String r15 = null;
        java.lang.String r16 = null;
        java.lang.String r17 = null;
        int r3 = 0;
        while (r3 < r4.length()) {
            java.lang.String r1 = findAIvalue(r3, r4);
            if (r1 == null) {
                return r2;
            }
            char r19 = 2;
            int r3 = r3 + r1.length() + 2;
            java.lang.String r2 = findValue(r3, r4);
            int r3 = r3 + r2.length();
            r1.hashCode();
            switch (r1.hashCode()) {
                case 1536:
                    if (r1.equals("00")) {
                        r19 = 0;
                        break;
                    }
                    r19 = 65535;
                    break;
                case 1537:
                    if (r1.equals("01")) {
                        r19 = 1;
                        break;
                    }
                    r19 = 65535;
                    break;
                case 1567:
                    break;
                case 1568:
                    if (r1.equals("11")) {
                        r19 = 3;
                        break;
                    }
                    r19 = 65535;
                    break;
                case 1570:
                    if (r1.equals("13")) {
                        r19 = 4;
                        break;
                    }
                    r19 = 65535;
                    break;
                case 1572:
                    if (r1.equals("15")) {
                        r19 = 5;
                        break;
                    }
                    r19 = 65535;
                    break;
                case 1574:
                    if (r1.equals("17")) {
                        r19 = 6;
                        break;
                    }
                    r19 = 65535;
                    break;
                case 1567966:
                    if (r1.equals("3100")) {
                        r19 = 7;
                        break;
                    }
                    r19 = 65535;
                    break;
                case 1567967:
                    if (r1.equals("3101")) {
                        r19 = '\b';
                        break;
                    }
                    r19 = 65535;
                    break;
                case 1567968:
                    if (r1.equals("3102")) {
                        r19 = '\t';
                        break;
                    }
                    r19 = 65535;
                    break;
                case 1567969:
                    if (r1.equals("3103")) {
                        r19 = '\n';
                        break;
                    }
                    r19 = 65535;
                    break;
                case 1567970:
                    if (r1.equals("3104")) {
                        r19 = 11;
                        break;
                    }
                    r19 = 65535;
                    break;
                case 1567971:
                    if (r1.equals("3105")) {
                        r19 = '\f';
                        break;
                    }
                    r19 = 65535;
                    break;
                case 1567972:
                    if (r1.equals("3106")) {
                        r19 = '\r';
                        break;
                    }
                    r19 = 65535;
                    break;
                case 1567973:
                    if (r1.equals("3107")) {
                        r19 = 14;
                        break;
                    }
                    r19 = 65535;
                    break;
                case 1567974:
                    if (r1.equals("3108")) {
                        r19 = 15;
                        break;
                    }
                    r19 = 65535;
                    break;
                case 1567975:
                    if (r1.equals("3109")) {
                        r19 = 16;
                        break;
                    }
                    r19 = 65535;
                    break;
                case 1568927:
                    if (r1.equals("3200")) {
                        r19 = 17;
                        break;
                    }
                    r19 = 65535;
                    break;
                case 1568928:
                    if (r1.equals("3201")) {
                        r19 = 18;
                        break;
                    }
                    r19 = 65535;
                    break;
                case 1568929:
                    if (r1.equals("3202")) {
                        r19 = 19;
                        break;
                    }
                    r19 = 65535;
                    break;
                case 1568930:
                    if (r1.equals("3203")) {
                        r19 = 20;
                        break;
                    }
                    r19 = 65535;
                    break;
                case 1568931:
                    if (r1.equals("3204")) {
                        r19 = 21;
                        break;
                    }
                    r19 = 65535;
                    break;
                case 1568932:
                    if (r1.equals("3205")) {
                        r19 = 22;
                        break;
                    }
                    r19 = 65535;
                    break;
                case 1568933:
                    if (r1.equals("3206")) {
                        r19 = 23;
                        break;
                    }
                    r19 = 65535;
                    break;
                case 1568934:
                    if (r1.equals("3207")) {
                        r19 = 24;
                        break;
                    }
                    r19 = 65535;
                    break;
                case 1568935:
                    if (r1.equals("3208")) {
                        r19 = 25;
                        break;
                    }
                    r19 = 65535;
                    break;
                case 1568936:
                    if (r1.equals("3209")) {
                        r19 = 26;
                        break;
                    }
                    r19 = 65535;
                    break;
                case 1575716:
                    if (r1.equals("3920")) {
                        r19 = 27;
                        break;
                    }
                    r19 = 65535;
                    break;
                case 1575717:
                    if (r1.equals("3921")) {
                        r19 = 28;
                        break;
                    }
                    r19 = 65535;
                    break;
                case 1575718:
                    if (r1.equals("3922")) {
                        r19 = 29;
                        break;
                    }
                    r19 = 65535;
                    break;
                case 1575719:
                    if (r1.equals("3923")) {
                        r19 = 30;
                        break;
                    }
                    r19 = 65535;
                    break;
                case 1575747:
                    if (r1.equals("3930")) {
                        r19 = 31;
                        break;
                    }
                    r19 = 65535;
                    break;
                case 1575748:
                    if (r1.equals("3931")) {
                        r19 = ' ';
                        break;
                    }
                    r19 = 65535;
                    break;
                case 1575749:
                    if (r1.equals("3932")) {
                        r19 = '!';
                        break;
                    }
                    r19 = 65535;
                    break;
                case 1575750:
                    if (r1.equals("3933")) {
                        r19 = kotlin.text.Typography.quote;
                        break;
                    }
                    r19 = 65535;
                    break;
                default:
                    r19 = 65535;
                    break;
            }
            switch (r19) {
                case 0:
                    r6 = r2;
                    break;
                case 1:
                    r5 = r2;
                    break;
                case 2:
                    r7 = r2;
                    break;
                case 3:
                    r8 = r2;
                    break;
                case 4:
                    r9 = r2;
                    break;
                case 5:
                    r10 = r2;
                    break;
                case 6:
                    r11 = r2;
                    break;
                case 7:
                case '\b':
                case '\t':
                case '\n':
                case 11:
                case '\f':
                case '\r':
                case 14:
                case 15:
                case 16:
                    r14 = r1.substring(3);
                    r13 = com.google.zxing.client.result.ExpandedProductParsedResult.KILOGRAM;
                    r12 = r2;
                    break;
                case 17:
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                    r14 = r1.substring(3);
                    r13 = com.google.zxing.client.result.ExpandedProductParsedResult.POUND;
                    r12 = r2;
                    break;
                case 27:
                case 28:
                case 29:
                case 30:
                    r16 = r1.substring(3);
                    r15 = r2;
                    break;
                case 31:
                case ' ':
                case '!':
                case '\"':
                    if (r2.length() < 4) {
                        return null;
                    }
                    java.lang.String r16 = r2.substring(3);
                    r17 = r2.substring(0, 3);
                    r15 = r16;
                    r3 = r3;
                    r2 = null;
                    r16 = r1.substring(3);
                    continue;
                default:
                    r0.put(r1, r2);
                    break;
            }
            r3 = r3;
            r2 = null;
        }
        return new com.google.zxing.client.result.ExpandedProductParsedResult(r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r0);
    }

    private static String findAIvalue(int i, String str) {
        if (str.charAt(i) != '(') {
            return null;
        }
        String substring = str.substring(i + 1);
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < substring.length(); i2++) {
            char charAt = substring.charAt(i2);
            if (charAt == ')') {
                return sb.toString();
            }
            if (charAt < '0' || charAt > '9') {
                return null;
            }
            sb.append(charAt);
        }
        return sb.toString();
    }

    private static String findValue(int i, String str) {
        StringBuilder sb = new StringBuilder();
        String substring = str.substring(i);
        for (int i2 = 0; i2 < substring.length(); i2++) {
            char charAt = substring.charAt(i2);
            if (charAt == '(') {
                if (findAIvalue(i2, substring) != null) {
                    break;
                }
                sb.append('(');
            } else {
                sb.append(charAt);
            }
        }
        return sb.toString();
    }
}
