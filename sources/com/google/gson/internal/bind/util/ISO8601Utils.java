package com.google.gson.internal.bind.util;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
/* loaded from: classes2.dex */
public class ISO8601Utils {
    private static final String UTC_ID = "UTC";
    private static final TimeZone TIMEZONE_UTC = TimeZone.getTimeZone(UTC_ID);

    public static String format(Date date) {
        return format(date, false, TIMEZONE_UTC);
    }

    public static String format(Date date, boolean z) {
        return format(date, z, TIMEZONE_UTC);
    }

    public static String format(Date date, boolean z, TimeZone timeZone) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar(timeZone, Locale.US);
        gregorianCalendar.setTime(date);
        StringBuilder sb = new StringBuilder(19 + (z ? 4 : 0) + (timeZone.getRawOffset() == 0 ? 1 : 6));
        padInt(sb, gregorianCalendar.get(1), 4);
        sb.append('-');
        padInt(sb, gregorianCalendar.get(2) + 1, 2);
        sb.append('-');
        padInt(sb, gregorianCalendar.get(5), 2);
        sb.append('T');
        padInt(sb, gregorianCalendar.get(11), 2);
        sb.append(':');
        padInt(sb, gregorianCalendar.get(12), 2);
        sb.append(':');
        padInt(sb, gregorianCalendar.get(13), 2);
        if (z) {
            sb.append('.');
            padInt(sb, gregorianCalendar.get(14), 3);
        }
        int offset = timeZone.getOffset(gregorianCalendar.getTimeInMillis());
        if (offset != 0) {
            int i = offset / 60000;
            int abs = Math.abs(i / 60);
            int abs2 = Math.abs(i % 60);
            sb.append(offset >= 0 ? '+' : '-');
            padInt(sb, abs, 2);
            sb.append(':');
            padInt(sb, abs2, 2);
        } else {
            sb.append('Z');
        }
        return sb.toString();
    }

    /* JADX WARN: Removed duplicated region for block: B:49:0x00d3 A[Catch: IllegalArgumentException | IndexOutOfBoundsException | NumberFormatException -> 0x01cb, NumberFormatException -> 0x01cd, IndexOutOfBoundsException -> 0x01cf, TryCatch #2 {IllegalArgumentException | IndexOutOfBoundsException | NumberFormatException -> 0x01cb, blocks: (B:3:0x0004, B:5:0x0016, B:6:0x0018, B:8:0x0024, B:9:0x0026, B:11:0x0036, B:13:0x003c, B:17:0x0054, B:19:0x0064, B:20:0x0066, B:22:0x0072, B:23:0x0074, B:25:0x007a, B:29:0x0084, B:34:0x0094, B:36:0x009c, B:47:0x00cd, B:49:0x00d3, B:51:0x00da, B:75:0x0192, B:55:0x00e4, B:56:0x0102, B:57:0x0103, B:61:0x0121, B:63:0x012e, B:66:0x0137, B:68:0x0158, B:71:0x0167, B:72:0x018d, B:74:0x0190, B:60:0x010e, B:77:0x01c3, B:78:0x01ca, B:40:0x00b4, B:41:0x00b7), top: B:94:0x0004 }] */
    /* JADX WARN: Removed duplicated region for block: B:77:0x01c3 A[Catch: IllegalArgumentException | IndexOutOfBoundsException | NumberFormatException -> 0x01cb, NumberFormatException -> 0x01cd, IndexOutOfBoundsException -> 0x01cf, TryCatch #2 {IllegalArgumentException | IndexOutOfBoundsException | NumberFormatException -> 0x01cb, blocks: (B:3:0x0004, B:5:0x0016, B:6:0x0018, B:8:0x0024, B:9:0x0026, B:11:0x0036, B:13:0x003c, B:17:0x0054, B:19:0x0064, B:20:0x0066, B:22:0x0072, B:23:0x0074, B:25:0x007a, B:29:0x0084, B:34:0x0094, B:36:0x009c, B:47:0x00cd, B:49:0x00d3, B:51:0x00da, B:75:0x0192, B:55:0x00e4, B:56:0x0102, B:57:0x0103, B:61:0x0121, B:63:0x012e, B:66:0x0137, B:68:0x0158, B:71:0x0167, B:72:0x018d, B:74:0x0190, B:60:0x010e, B:77:0x01c3, B:78:0x01ca, B:40:0x00b4, B:41:0x00b7), top: B:94:0x0004 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.util.Date parse(java.lang.String r17, java.text.ParsePosition r18) throws java.text.ParseException {
        int r7;
        int r8;
        int r13;
        int r14;
        int r6;
        java.util.TimeZone r4;
        char r13;
        try {
            int r0 = r18.getIndex();
            int r3 = r0 + 4;
            int r0 = parseInt(r17, r0, r3);
            if (checkOffset(r17, r3, '-')) {
                r3++;
            }
            int r5 = r3 + 2;
            int r3 = parseInt(r17, r3, r5);
            if (checkOffset(r17, r5, '-')) {
                r5++;
            }
            int r6 = r5 + 2;
            int r5 = parseInt(r17, r5, r6);
            boolean r7 = checkOffset(r17, r6, 'T');
            if (!r7 && r17.length() <= r6) {
                java.util.GregorianCalendar r4 = new java.util.GregorianCalendar(r0, r3 - 1, r5);
                r4.setLenient(false);
                r18.setIndex(r6);
                return r4.getTime();
            }
            if (r7) {
                int r6 = r6 + 1;
                int r7 = r6 + 2;
                int r6 = parseInt(r17, r6, r7);
                if (checkOffset(r17, r7, ':')) {
                    r7++;
                }
                int r14 = r7 + 2;
                int r7 = parseInt(r17, r7, r14);
                if (checkOffset(r17, r14, ':')) {
                    r14++;
                }
                if (r17.length() > r14 && (r13 = r17.charAt(r14)) != 'Z' && r13 != '+' && r13 != '-') {
                    int r13 = r14 + 2;
                    r14 = parseInt(r17, r14, r13);
                    if (r14 > 59 && r14 < 63) {
                        r14 = 59;
                    }
                    if (checkOffset(r17, r13, '.')) {
                        int r13 = r13 + 1;
                        int r15 = indexOfNonDigit(r17, r13 + 1);
                        int r8 = java.lang.Math.min(r15, r13 + 3);
                        int r16 = parseInt(r17, r13, r8);
                        int r8 = r8 - r13;
                        if (r8 == 1) {
                            r16 *= 100;
                        } else if (r8 == 2) {
                            r16 *= 10;
                        }
                        r8 = r7;
                        r13 = r16;
                        r7 = r6;
                        r6 = r15;
                    } else {
                        r8 = r7;
                        r7 = r6;
                        r6 = r13;
                        r13 = 0;
                    }
                    if (r17.length() > r6) {
                        throw new java.lang.IllegalArgumentException("No time zone indicator");
                    }
                    char r15 = r17.charAt(r6);
                    if (r15 == 'Z') {
                        r4 = com.google.gson.internal.bind.util.ISO8601Utils.TIMEZONE_UTC;
                        r6 = r6 + 1;
                    } else {
                        if (r15 != '+' && r15 != '-') {
                            throw new java.lang.IndexOutOfBoundsException("Invalid time zone indicator '" + r15 + "'");
                        }
                        java.lang.String r4 = r17.substring(r6);
                        if (r4.length() < 5) {
                            r4 = r4 + "00";
                        }
                        r6 = r6 + r4.length();
                        if (!"+0000".equals(r4) && !"+00:00".equals(r4)) {
                            java.lang.String r4 = "GMT" + r4;
                            java.util.TimeZone r10 = java.util.TimeZone.getTimeZone(r4);
                            java.lang.String r11 = r10.getID();
                            if (!r11.equals(r4) && !r11.replace(":", "").equals(r4)) {
                                throw new java.lang.IndexOutOfBoundsException("Mismatching time zone indicator: " + r4 + " given, resolves to " + r10.getID());
                            }
                            r4 = r10;
                        }
                        r4 = com.google.gson.internal.bind.util.ISO8601Utils.TIMEZONE_UTC;
                    }
                    java.util.GregorianCalendar r10 = new java.util.GregorianCalendar(r4);
                    r10.setLenient(false);
                    r10.set(1, r0);
                    r10.set(2, r3 - 1);
                    r10.set(5, r5);
                    r10.set(11, r7);
                    r10.set(12, r8);
                    r10.set(13, r14);
                    r10.set(14, r13);
                    r18.setIndex(r6);
                    return r10.getTime();
                }
                r8 = r7;
                r13 = 0;
                r7 = r6;
                r6 = r14;
            } else {
                r7 = 0;
                r8 = 0;
                r13 = 0;
            }
            r14 = 0;
            if (r17.length() > r6) {
            }
        } catch (java.lang.IllegalArgumentException | java.lang.IndexOutOfBoundsException | java.lang.NumberFormatException r0) {
            java.lang.String r1 = r17 == null ? null : kotlin.text.Typography.quote + r17 + kotlin.text.Typography.quote;
            java.lang.String r3 = r0.getMessage();
            if (r3 == null || r3.isEmpty()) {
                r3 = "(" + r0.getClass().getName() + ")";
            }
            java.text.ParseException r4 = new java.text.ParseException("Failed to parse date [" + r1 + "]: " + r3, r18.getIndex());
            r4.initCause(r0);
            throw r4;
        }
    }

    private static boolean checkOffset(String str, int i, char c) {
        return i < str.length() && str.charAt(i) == c;
    }

    private static int parseInt(String str, int i, int i2) throws NumberFormatException {
        int i3;
        int i4;
        if (i < 0 || i2 > str.length() || i > i2) {
            throw new NumberFormatException(str);
        }
        if (i < i2) {
            i4 = i + 1;
            int digit = Character.digit(str.charAt(i), 10);
            if (digit < 0) {
                throw new NumberFormatException("Invalid number: " + str.substring(i, i2));
            }
            i3 = -digit;
        } else {
            i3 = 0;
            i4 = i;
        }
        while (i4 < i2) {
            int i5 = i4 + 1;
            int digit2 = Character.digit(str.charAt(i4), 10);
            if (digit2 < 0) {
                throw new NumberFormatException("Invalid number: " + str.substring(i, i2));
            }
            i3 = (i3 * 10) - digit2;
            i4 = i5;
        }
        return -i3;
    }

    private static void padInt(StringBuilder sb, int i, int i2) {
        String num = Integer.toString(i);
        for (int length = i2 - num.length(); length > 0; length--) {
            sb.append('0');
        }
        sb.append(num);
    }

    private static int indexOfNonDigit(String str, int i) {
        while (i < str.length()) {
            char charAt = str.charAt(i);
            if (charAt < '0' || charAt > '9') {
                return i;
            }
            i++;
        }
        return str.length();
    }
}
