package com.google.firebase.functions.dagger.internal;

import java.util.Objects;
/* loaded from: classes2.dex */
public final class Preconditions {
    public static <T> T checkNotNull(T t) {
        Objects.requireNonNull(t);
        return t;
    }

    public static <T> T checkNotNull(T t, String str) {
        Objects.requireNonNull(t, str);
        return t;
    }

    public static <T> T checkNotNullFromProvides(T t) {
        Objects.requireNonNull(t, "Cannot return null from a non-@Nullable @Provides method");
        return t;
    }

    public static <T> T checkNotNullFromComponent(T t) {
        Objects.requireNonNull(t, "Cannot return null from a non-@Nullable component method");
        return t;
    }

    public static <T> T checkNotNull(T t, String str, Object obj) {
        String valueOf;
        if (t == null) {
            if (!str.contains("%s")) {
                throw new IllegalArgumentException("errorMessageTemplate has no format specifiers");
            }
            if (str.indexOf("%s") != str.lastIndexOf("%s")) {
                throw new IllegalArgumentException("errorMessageTemplate has more than one format specifier");
            }
            if (obj instanceof Class) {
                valueOf = ((Class) obj).getCanonicalName();
            } else {
                valueOf = String.valueOf(obj);
            }
            throw new NullPointerException(str.replace("%s", valueOf));
        }
        return t;
    }

    public static <T> void checkBuilderRequirement(T t, Class<T> cls) {
        if (t == null) {
            throw new IllegalStateException(cls.getCanonicalName() + " must be set");
        }
    }

    private Preconditions() {
    }
}
