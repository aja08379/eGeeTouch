package com.google.zxing.client.result;

import com.facebook.share.internal.ShareConstants;
import com.google.zxing.Result;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/* loaded from: classes2.dex */
public final class VCardResultParser extends ResultParser {
    private static final Pattern BEGIN_VCARD = Pattern.compile("BEGIN:VCARD", 2);
    private static final Pattern VCARD_LIKE_DATE = Pattern.compile("\\d{4}-?\\d{2}-?\\d{2}");
    private static final Pattern CR_LF_SPACE_TAB = Pattern.compile("\r\n[ \t]");
    private static final Pattern NEWLINE_ESCAPE = Pattern.compile("\\\\[nN]");
    private static final Pattern VCARD_ESCAPES = Pattern.compile("\\\\([,;\\\\])");
    private static final Pattern EQUALS = Pattern.compile("=");
    private static final Pattern SEMICOLON = Pattern.compile(";");
    private static final Pattern UNESCAPED_SEMICOLONS = Pattern.compile("(?<!\\\\);+");
    private static final Pattern COMMA = Pattern.compile(",");
    private static final Pattern SEMICOLON_OR_COMMA = Pattern.compile("[;,]");

    @Override // com.google.zxing.client.result.ResultParser
    public AddressBookParsedResult parse(Result result) {
        String massagedText = getMassagedText(result);
        Matcher matcher = BEGIN_VCARD.matcher(massagedText);
        if (matcher.find() && matcher.start() == 0) {
            List<List<String>> matchVCardPrefixedField = matchVCardPrefixedField("FN", massagedText, true, false);
            if (matchVCardPrefixedField == null) {
                matchVCardPrefixedField = matchVCardPrefixedField("N", massagedText, true, false);
                formatNames(matchVCardPrefixedField);
            }
            List<String> matchSingleVCardPrefixedField = matchSingleVCardPrefixedField("NICKNAME", massagedText, true, false);
            String[] split = matchSingleVCardPrefixedField == null ? null : COMMA.split(matchSingleVCardPrefixedField.get(0));
            List<List<String>> matchVCardPrefixedField2 = matchVCardPrefixedField("TEL", massagedText, true, false);
            List<List<String>> matchVCardPrefixedField3 = matchVCardPrefixedField("EMAIL", massagedText, true, false);
            List<String> matchSingleVCardPrefixedField2 = matchSingleVCardPrefixedField("NOTE", massagedText, false, false);
            List<List<String>> matchVCardPrefixedField4 = matchVCardPrefixedField("ADR", massagedText, true, true);
            List<String> matchSingleVCardPrefixedField3 = matchSingleVCardPrefixedField("ORG", massagedText, true, true);
            List<String> matchSingleVCardPrefixedField4 = matchSingleVCardPrefixedField("BDAY", massagedText, true, false);
            List<String> list = (matchSingleVCardPrefixedField4 == null || isLikeVCardDate(matchSingleVCardPrefixedField4.get(0))) ? matchSingleVCardPrefixedField4 : null;
            List<String> matchSingleVCardPrefixedField5 = matchSingleVCardPrefixedField(ShareConstants.TITLE, massagedText, true, false);
            List<List<String>> matchVCardPrefixedField5 = matchVCardPrefixedField("URL", massagedText, true, false);
            List<String> matchSingleVCardPrefixedField6 = matchSingleVCardPrefixedField("IMPP", massagedText, true, false);
            List<String> matchSingleVCardPrefixedField7 = matchSingleVCardPrefixedField("GEO", massagedText, true, false);
            String[] split2 = matchSingleVCardPrefixedField7 == null ? null : SEMICOLON_OR_COMMA.split(matchSingleVCardPrefixedField7.get(0));
            return new AddressBookParsedResult(toPrimaryValues(matchVCardPrefixedField), split, null, toPrimaryValues(matchVCardPrefixedField2), toTypes(matchVCardPrefixedField2), toPrimaryValues(matchVCardPrefixedField3), toTypes(matchVCardPrefixedField3), toPrimaryValue(matchSingleVCardPrefixedField6), toPrimaryValue(matchSingleVCardPrefixedField2), toPrimaryValues(matchVCardPrefixedField4), toTypes(matchVCardPrefixedField4), toPrimaryValue(matchSingleVCardPrefixedField3), toPrimaryValue(list), toPrimaryValue(matchSingleVCardPrefixedField5), toPrimaryValues(matchVCardPrefixedField5), (split2 == null || split2.length == 2) ? split2 : null);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00ca, code lost:
        r4 = r1;
        r3 = 0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.util.List<java.util.List<java.lang.String>> matchVCardPrefixedField(java.lang.CharSequence r16, java.lang.String r17, boolean r18, boolean r19) {
        java.util.ArrayList r12;
        int r13;
        java.lang.String r14;
        int r2;
        int r4;
        java.lang.String r3;
        int r1 = r17.length();
        int r3 = 0;
        int r4 = 0;
        java.util.ArrayList r5 = null;
        while (r4 < r1) {
            java.util.regex.Matcher r6 = java.util.regex.Pattern.compile("(?:^|\n)" + ((java.lang.Object) r16) + "(?:;([^:]*))?:", 2).matcher(r17);
            if (r4 > 0) {
                r4--;
            }
            if (!r6.find(r4)) {
                break;
            }
            int r4 = r6.end(r3);
            java.lang.String r6 = r6.group(1);
            if (r6 != null) {
                java.lang.String[] r6 = com.google.zxing.client.result.VCardResultParser.SEMICOLON.split(r6);
                int r10 = r6.length;
                int r11 = r3;
                r13 = r11;
                r12 = null;
                r14 = null;
                while (r11 < r10) {
                    java.lang.String r15 = r6[r11];
                    if (r12 == null) {
                        r12 = new java.util.ArrayList(1);
                    }
                    r12.add(r15);
                    java.lang.String[] r2 = com.google.zxing.client.result.VCardResultParser.EQUALS.split(r15, 2);
                    if (r2.length > 1) {
                        java.lang.String r15 = r2[r3];
                        java.lang.String r2 = r2[1];
                        if ("ENCODING".equalsIgnoreCase(r15) && "QUOTED-PRINTABLE".equalsIgnoreCase(r2)) {
                            r13 = 1;
                        } else if ("CHARSET".equalsIgnoreCase(r15)) {
                            r14 = r2;
                        }
                    }
                    r11++;
                    r3 = 0;
                }
            } else {
                r12 = null;
                r13 = 0;
                r14 = null;
            }
            int r2 = r4;
            while (true) {
                r2 = r17.indexOf(10, r2);
                if (r2 >= 0) {
                    if (r2 < r17.length() - 1) {
                        int r3 = r2 + 1;
                        if (r17.charAt(r3) == ' ' || r17.charAt(r3) == '\t') {
                            r2 = r2 + 2;
                        }
                    }
                    if (r13 == 0 || ((r2 <= 0 || r17.charAt(r2 - 1) != '=') && (r2 < 2 || r17.charAt(r2 - 2) != '='))) {
                        break;
                    }
                    r2 = r2 + 1;
                } else {
                    break;
                }
            }
            if (r2 > r4) {
                if (r5 == null) {
                    r5 = new java.util.ArrayList(1);
                }
                if (r2 > 0 && r17.charAt(r2 - 1) == '\r') {
                    r2--;
                }
                java.lang.String r3 = r17.substring(r4, r2);
                if (r18) {
                    r3 = r3.trim();
                }
                if (r13 != 0) {
                    r3 = decodeQuotedPrintable(r3, r14);
                    if (r19) {
                        r3 = com.google.zxing.client.result.VCardResultParser.UNESCAPED_SEMICOLONS.matcher(r3).replaceAll("\n").trim();
                    }
                } else {
                    if (r19) {
                        r3 = com.google.zxing.client.result.VCardResultParser.UNESCAPED_SEMICOLONS.matcher(r3).replaceAll("\n").trim();
                    }
                    r3 = com.google.zxing.client.result.VCardResultParser.VCARD_ESCAPES.matcher(com.google.zxing.client.result.VCardResultParser.NEWLINE_ESCAPE.matcher(com.google.zxing.client.result.VCardResultParser.CR_LF_SPACE_TAB.matcher(r3).replaceAll("")).replaceAll("\n")).replaceAll("$1");
                }
                if (r12 == null) {
                    java.util.ArrayList r4 = new java.util.ArrayList(1);
                    r4.add(r3);
                    r5.add(r4);
                } else {
                    r4 = 0;
                    r12.add(0, r3);
                    r5.add(r12);
                    r3 = r4;
                    r4 = r2 + 1;
                }
            }
            r4 = 0;
            r3 = r4;
            r4 = r2 + 1;
        }
        return r5;
    }

    private static String decodeQuotedPrintable(CharSequence charSequence, String str) {
        char charAt;
        int length = charSequence.length();
        StringBuilder sb = new StringBuilder(length);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i = 0;
        while (i < length) {
            char charAt2 = charSequence.charAt(i);
            if (charAt2 != '\n' && charAt2 != '\r') {
                if (charAt2 != '=') {
                    maybeAppendFragment(byteArrayOutputStream, str, sb);
                    sb.append(charAt2);
                } else if (i < length - 2 && (charAt = charSequence.charAt(i + 1)) != '\r' && charAt != '\n') {
                    i += 2;
                    char charAt3 = charSequence.charAt(i);
                    int parseHexDigit = parseHexDigit(charAt);
                    int parseHexDigit2 = parseHexDigit(charAt3);
                    if (parseHexDigit >= 0 && parseHexDigit2 >= 0) {
                        byteArrayOutputStream.write((parseHexDigit << 4) + parseHexDigit2);
                    }
                }
            }
            i++;
        }
        maybeAppendFragment(byteArrayOutputStream, str, sb);
        return sb.toString();
    }

    private static void maybeAppendFragment(ByteArrayOutputStream byteArrayOutputStream, String str, StringBuilder sb) {
        String str2;
        if (byteArrayOutputStream.size() > 0) {
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            if (str == null) {
                str2 = new String(byteArray, Charset.forName("UTF-8"));
            } else {
                try {
                    str2 = new String(byteArray, str);
                } catch (UnsupportedEncodingException unused) {
                    str2 = new String(byteArray, Charset.forName("UTF-8"));
                }
            }
            byteArrayOutputStream.reset();
            sb.append(str2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static List<String> matchSingleVCardPrefixedField(CharSequence charSequence, String str, boolean z, boolean z2) {
        List<List<String>> matchVCardPrefixedField = matchVCardPrefixedField(charSequence, str, z, z2);
        if (matchVCardPrefixedField == null || matchVCardPrefixedField.isEmpty()) {
            return null;
        }
        return matchVCardPrefixedField.get(0);
    }

    private static String toPrimaryValue(List<String> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    private static String[] toPrimaryValues(Collection<List<String>> collection) {
        if (collection == null || collection.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList(collection.size());
        for (List<String> list : collection) {
            String str = list.get(0);
            if (str != null && !str.isEmpty()) {
                arrayList.add(str);
            }
        }
        return (String[]) arrayList.toArray(new String[collection.size()]);
    }

    private static String[] toTypes(Collection<List<String>> collection) {
        String str;
        if (collection == null || collection.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList(collection.size());
        for (List<String> list : collection) {
            int i = 1;
            while (true) {
                if (i >= list.size()) {
                    str = null;
                    break;
                }
                str = list.get(i);
                int indexOf = str.indexOf(61);
                if (indexOf >= 0) {
                    if ("TYPE".equalsIgnoreCase(str.substring(0, indexOf))) {
                        str = str.substring(indexOf + 1);
                        break;
                    }
                    i++;
                }
            }
            arrayList.add(str);
        }
        return (String[]) arrayList.toArray(new String[collection.size()]);
    }

    private static boolean isLikeVCardDate(CharSequence charSequence) {
        return charSequence == null || VCARD_LIKE_DATE.matcher(charSequence).matches();
    }

    private static void formatNames(Iterable<List<String>> iterable) {
        int indexOf;
        if (iterable != null) {
            for (List<String> list : iterable) {
                String str = list.get(0);
                String[] strArr = new String[5];
                int i = 0;
                int i2 = 0;
                while (i < 4 && (indexOf = str.indexOf(59, i2)) >= 0) {
                    strArr[i] = str.substring(i2, indexOf);
                    i++;
                    i2 = indexOf + 1;
                }
                strArr[i] = str.substring(i2);
                StringBuilder sb = new StringBuilder(100);
                maybeAppendComponent(strArr, 3, sb);
                maybeAppendComponent(strArr, 1, sb);
                maybeAppendComponent(strArr, 2, sb);
                maybeAppendComponent(strArr, 0, sb);
                maybeAppendComponent(strArr, 4, sb);
                list.set(0, sb.toString().trim());
            }
        }
    }

    private static void maybeAppendComponent(String[] strArr, int i, StringBuilder sb) {
        if (strArr[i] == null || strArr[i].isEmpty()) {
            return;
        }
        if (sb.length() > 0) {
            sb.append(' ');
        }
        sb.append(strArr[i]);
    }
}
