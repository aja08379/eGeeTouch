package okhttp3;

import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
/* loaded from: classes2.dex */
public final class CacheControl {
    @Nullable
    String headerValue;
    private final boolean immutable;
    private final boolean isPrivate;
    private final boolean isPublic;
    private final int maxAgeSeconds;
    private final int maxStaleSeconds;
    private final int minFreshSeconds;
    private final boolean mustRevalidate;
    private final boolean noCache;
    private final boolean noStore;
    private final boolean noTransform;
    private final boolean onlyIfCached;
    private final int sMaxAgeSeconds;
    public static final CacheControl FORCE_NETWORK = new Builder().noCache().build();
    public static final CacheControl FORCE_CACHE = new Builder().onlyIfCached().maxStale(Integer.MAX_VALUE, TimeUnit.SECONDS).build();

    private CacheControl(boolean z, boolean z2, int i, int i2, boolean z3, boolean z4, boolean z5, int i3, int i4, boolean z6, boolean z7, boolean z8, @Nullable String str) {
        this.noCache = z;
        this.noStore = z2;
        this.maxAgeSeconds = i;
        this.sMaxAgeSeconds = i2;
        this.isPrivate = z3;
        this.isPublic = z4;
        this.mustRevalidate = z5;
        this.maxStaleSeconds = i3;
        this.minFreshSeconds = i4;
        this.onlyIfCached = z6;
        this.noTransform = z7;
        this.immutable = z8;
        this.headerValue = str;
    }

    CacheControl(Builder builder) {
        this.noCache = builder.noCache;
        this.noStore = builder.noStore;
        this.maxAgeSeconds = builder.maxAgeSeconds;
        this.sMaxAgeSeconds = -1;
        this.isPrivate = false;
        this.isPublic = false;
        this.mustRevalidate = false;
        this.maxStaleSeconds = builder.maxStaleSeconds;
        this.minFreshSeconds = builder.minFreshSeconds;
        this.onlyIfCached = builder.onlyIfCached;
        this.noTransform = builder.noTransform;
        this.immutable = builder.immutable;
    }

    public boolean noCache() {
        return this.noCache;
    }

    public boolean noStore() {
        return this.noStore;
    }

    public int maxAgeSeconds() {
        return this.maxAgeSeconds;
    }

    public int sMaxAgeSeconds() {
        return this.sMaxAgeSeconds;
    }

    public boolean isPrivate() {
        return this.isPrivate;
    }

    public boolean isPublic() {
        return this.isPublic;
    }

    public boolean mustRevalidate() {
        return this.mustRevalidate;
    }

    public int maxStaleSeconds() {
        return this.maxStaleSeconds;
    }

    public int minFreshSeconds() {
        return this.minFreshSeconds;
    }

    public boolean onlyIfCached() {
        return this.onlyIfCached;
    }

    public boolean noTransform() {
        return this.noTransform;
    }

    public boolean immutable() {
        return this.immutable;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0041  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static okhttp3.CacheControl parse(okhttp3.Headers r22) {
        int r2;
        int r3;
        java.lang.String r0;
        okhttp3.Headers r0 = r22;
        int r1 = r22.size();
        int r6 = 0;
        boolean r7 = true;
        java.lang.String r8 = null;
        boolean r9 = false;
        boolean r10 = false;
        int r11 = -1;
        int r12 = -1;
        boolean r13 = false;
        boolean r14 = false;
        boolean r15 = false;
        int r16 = -1;
        int r17 = -1;
        boolean r18 = false;
        boolean r19 = false;
        boolean r20 = false;
        while (r6 < r1) {
            java.lang.String r2 = r0.name(r6);
            java.lang.String r4 = r0.value(r6);
            if (r2.equalsIgnoreCase("Cache-Control")) {
                if (r8 == null) {
                    r8 = r4;
                    for (r2 = 0; r2 < r4.length(); r2 = r3) {
                        int r3 = okhttp3.internal.http.HttpHeaders.skipUntil(r4, r2, "=,;");
                        java.lang.String r2 = r4.substring(r2, r3).trim();
                        if (r3 == r4.length() || r4.charAt(r3) == ',' || r4.charAt(r3) == ';') {
                            r3 = r3 + 1;
                            r0 = null;
                        } else {
                            int r0 = okhttp3.internal.http.HttpHeaders.skipWhitespace(r4, r3 + 1);
                            if (r0 < r4.length() && r4.charAt(r0) == '\"') {
                                int r0 = r0 + 1;
                                int r3 = okhttp3.internal.http.HttpHeaders.skipUntil(r4, r0, "\"");
                                r0 = r4.substring(r0, r3);
                                r3 = r3 + 1;
                            } else {
                                r3 = okhttp3.internal.http.HttpHeaders.skipUntil(r4, r0, ",;");
                                r0 = r4.substring(r0, r3).trim();
                            }
                        }
                        if ("no-cache".equalsIgnoreCase(r2)) {
                            r9 = true;
                        } else if ("no-store".equalsIgnoreCase(r2)) {
                            r10 = true;
                        } else if ("max-age".equalsIgnoreCase(r2)) {
                            r11 = okhttp3.internal.http.HttpHeaders.parseSeconds(r0, -1);
                        } else if ("s-maxage".equalsIgnoreCase(r2)) {
                            r12 = okhttp3.internal.http.HttpHeaders.parseSeconds(r0, -1);
                        } else if ("private".equalsIgnoreCase(r2)) {
                            r13 = true;
                        } else if ("public".equalsIgnoreCase(r2)) {
                            r14 = true;
                        } else if ("must-revalidate".equalsIgnoreCase(r2)) {
                            r15 = true;
                        } else if ("max-stale".equalsIgnoreCase(r2)) {
                            r16 = okhttp3.internal.http.HttpHeaders.parseSeconds(r0, Integer.MAX_VALUE);
                        } else if ("min-fresh".equalsIgnoreCase(r2)) {
                            r17 = okhttp3.internal.http.HttpHeaders.parseSeconds(r0, -1);
                        } else if ("only-if-cached".equalsIgnoreCase(r2)) {
                            r18 = true;
                        } else if ("no-transform".equalsIgnoreCase(r2)) {
                            r19 = true;
                        } else if ("immutable".equalsIgnoreCase(r2)) {
                            r20 = true;
                        }
                    }
                    r6++;
                    r0 = r22;
                }
            } else if (!r2.equalsIgnoreCase("Pragma")) {
                r6++;
                r0 = r22;
            }
            r7 = false;
            while (r2 < r4.length()) {
            }
            r6++;
            r0 = r22;
        }
        return new okhttp3.CacheControl(r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, !r7 ? null : r8);
    }

    public String toString() {
        String str = this.headerValue;
        if (str != null) {
            return str;
        }
        String headerValue = headerValue();
        this.headerValue = headerValue;
        return headerValue;
    }

    private String headerValue() {
        StringBuilder sb = new StringBuilder();
        if (this.noCache) {
            sb.append("no-cache, ");
        }
        if (this.noStore) {
            sb.append("no-store, ");
        }
        if (this.maxAgeSeconds != -1) {
            sb.append("max-age=").append(this.maxAgeSeconds).append(", ");
        }
        if (this.sMaxAgeSeconds != -1) {
            sb.append("s-maxage=").append(this.sMaxAgeSeconds).append(", ");
        }
        if (this.isPrivate) {
            sb.append("private, ");
        }
        if (this.isPublic) {
            sb.append("public, ");
        }
        if (this.mustRevalidate) {
            sb.append("must-revalidate, ");
        }
        if (this.maxStaleSeconds != -1) {
            sb.append("max-stale=").append(this.maxStaleSeconds).append(", ");
        }
        if (this.minFreshSeconds != -1) {
            sb.append("min-fresh=").append(this.minFreshSeconds).append(", ");
        }
        if (this.onlyIfCached) {
            sb.append("only-if-cached, ");
        }
        if (this.noTransform) {
            sb.append("no-transform, ");
        }
        if (this.immutable) {
            sb.append("immutable, ");
        }
        if (sb.length() == 0) {
            return "";
        }
        sb.delete(sb.length() - 2, sb.length());
        return sb.toString();
    }

    /* loaded from: classes2.dex */
    public static final class Builder {
        boolean immutable;
        int maxAgeSeconds = -1;
        int maxStaleSeconds = -1;
        int minFreshSeconds = -1;
        boolean noCache;
        boolean noStore;
        boolean noTransform;
        boolean onlyIfCached;

        public Builder noCache() {
            this.noCache = true;
            return this;
        }

        public Builder noStore() {
            this.noStore = true;
            return this;
        }

        public Builder maxAge(int i, TimeUnit timeUnit) {
            if (i < 0) {
                throw new IllegalArgumentException("maxAge < 0: " + i);
            }
            long seconds = timeUnit.toSeconds(i);
            this.maxAgeSeconds = seconds > 2147483647L ? Integer.MAX_VALUE : (int) seconds;
            return this;
        }

        public Builder maxStale(int i, TimeUnit timeUnit) {
            if (i < 0) {
                throw new IllegalArgumentException("maxStale < 0: " + i);
            }
            long seconds = timeUnit.toSeconds(i);
            this.maxStaleSeconds = seconds > 2147483647L ? Integer.MAX_VALUE : (int) seconds;
            return this;
        }

        public Builder minFresh(int i, TimeUnit timeUnit) {
            if (i < 0) {
                throw new IllegalArgumentException("minFresh < 0: " + i);
            }
            long seconds = timeUnit.toSeconds(i);
            this.minFreshSeconds = seconds > 2147483647L ? Integer.MAX_VALUE : (int) seconds;
            return this;
        }

        public Builder onlyIfCached() {
            this.onlyIfCached = true;
            return this;
        }

        public Builder noTransform() {
            this.noTransform = true;
            return this;
        }

        public Builder immutable() {
            this.immutable = true;
            return this;
        }

        public CacheControl build() {
            return new CacheControl(this);
        }
    }
}
