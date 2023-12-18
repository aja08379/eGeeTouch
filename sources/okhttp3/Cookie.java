package okhttp3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import okhttp3.internal.Util;
import okhttp3.internal.http.HttpDate;
/* loaded from: classes2.dex */
public final class Cookie {
    private final String domain;
    private final long expiresAt;
    private final boolean hostOnly;
    private final boolean httpOnly;
    private final String name;
    private final String path;
    private final boolean persistent;
    private final boolean secure;
    private final String value;
    private static final Pattern YEAR_PATTERN = Pattern.compile("(\\d{2,4})[^\\d]*");
    private static final Pattern MONTH_PATTERN = Pattern.compile("(?i)(jan|feb|mar|apr|may|jun|jul|aug|sep|oct|nov|dec).*");
    private static final Pattern DAY_OF_MONTH_PATTERN = Pattern.compile("(\\d{1,2})[^\\d]*");
    private static final Pattern TIME_PATTERN = Pattern.compile("(\\d{1,2}):(\\d{1,2}):(\\d{1,2})[^\\d]*");

    private Cookie(String str, String str2, long j, String str3, String str4, boolean z, boolean z2, boolean z3, boolean z4) {
        this.name = str;
        this.value = str2;
        this.expiresAt = j;
        this.domain = str3;
        this.path = str4;
        this.secure = z;
        this.httpOnly = z2;
        this.hostOnly = z3;
        this.persistent = z4;
    }

    Cookie(Builder builder) {
        Objects.requireNonNull(builder.name, "builder.name == null");
        Objects.requireNonNull(builder.value, "builder.value == null");
        Objects.requireNonNull(builder.domain, "builder.domain == null");
        this.name = builder.name;
        this.value = builder.value;
        this.expiresAt = builder.expiresAt;
        this.domain = builder.domain;
        this.path = builder.path;
        this.secure = builder.secure;
        this.httpOnly = builder.httpOnly;
        this.persistent = builder.persistent;
        this.hostOnly = builder.hostOnly;
    }

    public String name() {
        return this.name;
    }

    public String value() {
        return this.value;
    }

    public boolean persistent() {
        return this.persistent;
    }

    public long expiresAt() {
        return this.expiresAt;
    }

    public boolean hostOnly() {
        return this.hostOnly;
    }

    public String domain() {
        return this.domain;
    }

    public String path() {
        return this.path;
    }

    public boolean httpOnly() {
        return this.httpOnly;
    }

    public boolean secure() {
        return this.secure;
    }

    public boolean matches(HttpUrl httpUrl) {
        boolean domainMatch;
        if (this.hostOnly) {
            domainMatch = httpUrl.host().equals(this.domain);
        } else {
            domainMatch = domainMatch(httpUrl.host(), this.domain);
        }
        if (domainMatch && pathMatch(httpUrl, this.path)) {
            return !this.secure || httpUrl.isHttps();
        }
        return false;
    }

    private static boolean domainMatch(String str, String str2) {
        if (str.equals(str2)) {
            return true;
        }
        return str.endsWith(str2) && str.charAt((str.length() - str2.length()) - 1) == '.' && !Util.verifyAsIpAddress(str);
    }

    private static boolean pathMatch(HttpUrl httpUrl, String str) {
        String encodedPath = httpUrl.encodedPath();
        if (encodedPath.equals(str)) {
            return true;
        }
        if (encodedPath.startsWith(str)) {
            return str.endsWith("/") || encodedPath.charAt(str.length()) == '/';
        }
        return false;
    }

    @Nullable
    public static Cookie parse(HttpUrl httpUrl, String str) {
        return parse(System.currentTimeMillis(), httpUrl, str);
    }

    /* JADX WARN: Removed duplicated region for block: B:58:0x00ed  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00f0  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0128  */
    @javax.annotation.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static okhttp3.Cookie parse(long r23, okhttp3.HttpUrl r25, java.lang.String r26) {
        long r11;
        java.lang.String r0;
        okhttp3.Cookie r1;
        java.lang.String r13;
        java.lang.String r14;
        int r1 = r26.length();
        char r3 = ';';
        int r4 = okhttp3.internal.Util.delimiterOffset(r26, 0, r1, ';');
        char r5 = '=';
        int r6 = okhttp3.internal.Util.delimiterOffset(r26, 0, r4, '=');
        if (r6 == r4) {
            return null;
        }
        java.lang.String r9 = okhttp3.internal.Util.trimSubstring(r26, 0, r6);
        if (r9.isEmpty() || okhttp3.internal.Util.indexOfControlOrNonAscii(r9) != -1) {
            return null;
        }
        java.lang.String r6 = okhttp3.internal.Util.trimSubstring(r26, r6 + 1, r4);
        if (okhttp3.internal.Util.indexOfControlOrNonAscii(r6) != -1) {
            return null;
        }
        int r4 = r4 + 1;
        boolean r15 = false;
        boolean r16 = false;
        boolean r18 = false;
        java.lang.String r14 = null;
        boolean r17 = true;
        long r19 = -1;
        long r21 = 253402300799999L;
        java.lang.String r8 = null;
        while (r4 < r1) {
            int r7 = okhttp3.internal.Util.delimiterOffset(r26, r4, r1, r3);
            int r3 = okhttp3.internal.Util.delimiterOffset(r26, r4, r7, r5);
            java.lang.String r4 = okhttp3.internal.Util.trimSubstring(r26, r4, r3);
            java.lang.String r3 = r3 < r7 ? okhttp3.internal.Util.trimSubstring(r26, r3 + 1, r7) : "";
            if (r4.equalsIgnoreCase("expires")) {
                try {
                    r21 = parseExpires(r3, 0, r3.length());
                } catch (java.lang.NumberFormatException | java.lang.IllegalArgumentException unused) {
                }
            } else if (r4.equalsIgnoreCase("max-age")) {
                r19 = parseMaxAge(r3);
            } else {
                if (r4.equalsIgnoreCase("domain")) {
                    r14 = parseDomain(r3);
                    r17 = false;
                } else if (r4.equalsIgnoreCase("path")) {
                    r8 = r3;
                } else if (r4.equalsIgnoreCase("secure")) {
                    r15 = true;
                } else if (r4.equalsIgnoreCase("httponly")) {
                    r16 = true;
                }
                r4 = r7 + 1;
                r3 = ';';
                r5 = '=';
            }
            r18 = true;
            r4 = r7 + 1;
            r3 = ';';
            r5 = '=';
        }
        long r0 = Long.MIN_VALUE;
        if (r19 != Long.MIN_VALUE) {
            if (r19 != -1) {
                r0 = r23 + (r19 <= 9223372036854775L ? r19 * 1000 : Long.MAX_VALUE);
                if (r0 < r23 || r0 > okhttp3.internal.http.HttpDate.MAX_DATE) {
                    r11 = 253402300799999L;
                }
            } else {
                r11 = r21;
            }
            r0 = r25.host();
            if (r14 != null) {
                r13 = r0;
                r1 = null;
            } else if (!domainMatch(r0, r14)) {
                return null;
            } else {
                r1 = null;
                r13 = r14;
            }
            if (r0.length() == r13.length() && okhttp3.internal.publicsuffix.PublicSuffixDatabase.get().getEffectiveTldPlusOne(r13) == null) {
                return r1;
            }
            if (r8 == null && r8.startsWith("/")) {
                r14 = r8;
            } else {
                java.lang.String r1 = r25.encodedPath();
                int r3 = r1.lastIndexOf(47);
                r14 = r3 != 0 ? r1.substring(0, r3) : "/";
            }
            return new okhttp3.Cookie(r9, r6, r11, r13, r14, r15, r16, r17, r18);
        }
        r11 = r0;
        r0 = r25.host();
        if (r14 != null) {
        }
        if (r0.length() == r13.length()) {
        }
        if (r8 == null) {
        }
        java.lang.String r1 = r25.encodedPath();
        int r3 = r1.lastIndexOf(47);
        r14 = r3 != 0 ? r1.substring(0, r3) : "/";
        return new okhttp3.Cookie(r9, r6, r11, r13, r14, r15, r16, r17, r18);
    }

    private static long parseExpires(String str, int i, int i2) {
        int dateCharacterOffset = dateCharacterOffset(str, i, i2, false);
        Matcher matcher = TIME_PATTERN.matcher(str);
        int i3 = -1;
        int i4 = -1;
        int i5 = -1;
        int i6 = -1;
        int i7 = -1;
        int i8 = -1;
        while (dateCharacterOffset < i2) {
            int dateCharacterOffset2 = dateCharacterOffset(str, dateCharacterOffset + 1, i2, true);
            matcher.region(dateCharacterOffset, dateCharacterOffset2);
            if (i4 == -1 && matcher.usePattern(TIME_PATTERN).matches()) {
                i4 = Integer.parseInt(matcher.group(1));
                i7 = Integer.parseInt(matcher.group(2));
                i8 = Integer.parseInt(matcher.group(3));
            } else if (i5 == -1 && matcher.usePattern(DAY_OF_MONTH_PATTERN).matches()) {
                i5 = Integer.parseInt(matcher.group(1));
            } else {
                if (i6 == -1) {
                    Pattern pattern = MONTH_PATTERN;
                    if (matcher.usePattern(pattern).matches()) {
                        i6 = pattern.pattern().indexOf(matcher.group(1).toLowerCase(Locale.US)) / 4;
                    }
                }
                if (i3 == -1 && matcher.usePattern(YEAR_PATTERN).matches()) {
                    i3 = Integer.parseInt(matcher.group(1));
                }
            }
            dateCharacterOffset = dateCharacterOffset(str, dateCharacterOffset2 + 1, i2, false);
        }
        if (i3 >= 70 && i3 <= 99) {
            i3 += 1900;
        }
        if (i3 >= 0 && i3 <= 69) {
            i3 += 2000;
        }
        if (i3 >= 1601) {
            if (i6 != -1) {
                if (i5 < 1 || i5 > 31) {
                    throw new IllegalArgumentException();
                }
                if (i4 < 0 || i4 > 23) {
                    throw new IllegalArgumentException();
                }
                if (i7 < 0 || i7 > 59) {
                    throw new IllegalArgumentException();
                }
                if (i8 < 0 || i8 > 59) {
                    throw new IllegalArgumentException();
                }
                GregorianCalendar gregorianCalendar = new GregorianCalendar(Util.UTC);
                gregorianCalendar.setLenient(false);
                gregorianCalendar.set(1, i3);
                gregorianCalendar.set(2, i6 - 1);
                gregorianCalendar.set(5, i5);
                gregorianCalendar.set(11, i4);
                gregorianCalendar.set(12, i7);
                gregorianCalendar.set(13, i8);
                gregorianCalendar.set(14, 0);
                return gregorianCalendar.getTimeInMillis();
            }
            throw new IllegalArgumentException();
        }
        throw new IllegalArgumentException();
    }

    private static int dateCharacterOffset(String str, int i, int i2, boolean z) {
        while (i < i2) {
            char charAt = str.charAt(i);
            if (((charAt < ' ' && charAt != '\t') || charAt >= 127 || (charAt >= '0' && charAt <= '9') || ((charAt >= 'a' && charAt <= 'z') || ((charAt >= 'A' && charAt <= 'Z') || charAt == ':'))) == (!z)) {
                return i;
            }
            i++;
        }
        return i2;
    }

    private static long parseMaxAge(String str) {
        try {
            long parseLong = Long.parseLong(str);
            if (parseLong <= 0) {
                return Long.MIN_VALUE;
            }
            return parseLong;
        } catch (NumberFormatException e) {
            if (str.matches("-?\\d+")) {
                return str.startsWith("-") ? Long.MIN_VALUE : Long.MAX_VALUE;
            }
            throw e;
        }
    }

    private static String parseDomain(String str) {
        if (str.endsWith(".")) {
            throw new IllegalArgumentException();
        }
        if (str.startsWith(".")) {
            str = str.substring(1);
        }
        String canonicalizeHost = Util.canonicalizeHost(str);
        if (canonicalizeHost != null) {
            return canonicalizeHost;
        }
        throw new IllegalArgumentException();
    }

    public static List<Cookie> parseAll(HttpUrl httpUrl, Headers headers) {
        List<String> values = headers.values("Set-Cookie");
        int size = values.size();
        ArrayList arrayList = null;
        for (int i = 0; i < size; i++) {
            Cookie parse = parse(httpUrl, values.get(i));
            if (parse != null) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(parse);
            }
        }
        if (arrayList != null) {
            return Collections.unmodifiableList(arrayList);
        }
        return Collections.emptyList();
    }

    /* loaded from: classes2.dex */
    public static final class Builder {
        @Nullable
        String domain;
        boolean hostOnly;
        boolean httpOnly;
        @Nullable
        String name;
        boolean persistent;
        boolean secure;
        @Nullable
        String value;
        long expiresAt = HttpDate.MAX_DATE;
        String path = "/";

        public Builder name(String str) {
            Objects.requireNonNull(str, "name == null");
            if (!str.trim().equals(str)) {
                throw new IllegalArgumentException("name is not trimmed");
            }
            this.name = str;
            return this;
        }

        public Builder value(String str) {
            Objects.requireNonNull(str, "value == null");
            if (!str.trim().equals(str)) {
                throw new IllegalArgumentException("value is not trimmed");
            }
            this.value = str;
            return this;
        }

        public Builder expiresAt(long j) {
            if (j <= 0) {
                j = Long.MIN_VALUE;
            }
            if (j > HttpDate.MAX_DATE) {
                j = 253402300799999L;
            }
            this.expiresAt = j;
            this.persistent = true;
            return this;
        }

        public Builder domain(String str) {
            return domain(str, false);
        }

        public Builder hostOnlyDomain(String str) {
            return domain(str, true);
        }

        private Builder domain(String str, boolean z) {
            Objects.requireNonNull(str, "domain == null");
            String canonicalizeHost = Util.canonicalizeHost(str);
            if (canonicalizeHost == null) {
                throw new IllegalArgumentException("unexpected domain: " + str);
            }
            this.domain = canonicalizeHost;
            this.hostOnly = z;
            return this;
        }

        public Builder path(String str) {
            if (!str.startsWith("/")) {
                throw new IllegalArgumentException("path must start with '/'");
            }
            this.path = str;
            return this;
        }

        public Builder secure() {
            this.secure = true;
            return this;
        }

        public Builder httpOnly() {
            this.httpOnly = true;
            return this;
        }

        public Cookie build() {
            return new Cookie(this);
        }
    }

    public String toString() {
        return toString(false);
    }

    String toString(boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.name);
        sb.append('=');
        sb.append(this.value);
        if (this.persistent) {
            if (this.expiresAt == Long.MIN_VALUE) {
                sb.append("; max-age=0");
            } else {
                sb.append("; expires=").append(HttpDate.format(new Date(this.expiresAt)));
            }
        }
        if (!this.hostOnly) {
            sb.append("; domain=");
            if (z) {
                sb.append(".");
            }
            sb.append(this.domain);
        }
        sb.append("; path=").append(this.path);
        if (this.secure) {
            sb.append("; secure");
        }
        if (this.httpOnly) {
            sb.append("; httponly");
        }
        return sb.toString();
    }

    public boolean equals(@Nullable Object obj) {
        if (obj instanceof Cookie) {
            Cookie cookie = (Cookie) obj;
            return cookie.name.equals(this.name) && cookie.value.equals(this.value) && cookie.domain.equals(this.domain) && cookie.path.equals(this.path) && cookie.expiresAt == this.expiresAt && cookie.secure == this.secure && cookie.httpOnly == this.httpOnly && cookie.persistent == this.persistent && cookie.hostOnly == this.hostOnly;
        }
        return false;
    }

    public int hashCode() {
        long j = this.expiresAt;
        return ((((((((((((((((527 + this.name.hashCode()) * 31) + this.value.hashCode()) * 31) + this.domain.hashCode()) * 31) + this.path.hashCode()) * 31) + ((int) (j ^ (j >>> 32)))) * 31) + (!this.secure ? 1 : 0)) * 31) + (!this.httpOnly ? 1 : 0)) * 31) + (!this.persistent ? 1 : 0)) * 31) + (!this.hostOnly ? 1 : 0);
    }
}
