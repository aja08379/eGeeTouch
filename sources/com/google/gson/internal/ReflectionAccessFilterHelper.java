package com.google.gson.internal;

import com.google.gson.ReflectionAccessFilter;
import java.lang.reflect.AccessibleObject;
import java.util.List;
/* loaded from: classes2.dex */
public class ReflectionAccessFilterHelper {
    private ReflectionAccessFilterHelper() {
    }

    public static boolean isJavaType(Class<?> cls) {
        return isJavaType(cls.getName());
    }

    private static boolean isJavaType(String str) {
        return str.startsWith("java.") || str.startsWith("javax.");
    }

    public static boolean isAndroidType(Class<?> cls) {
        return isAndroidType(cls.getName());
    }

    private static boolean isAndroidType(String str) {
        return str.startsWith("android.") || str.startsWith("androidx.") || isJavaType(str);
    }

    public static boolean isAnyPlatformType(Class<?> cls) {
        String name = cls.getName();
        return isAndroidType(name) || name.startsWith("kotlin.") || name.startsWith("kotlinx.") || name.startsWith("scala.");
    }

    public static ReflectionAccessFilter.FilterResult getFilterResult(List<ReflectionAccessFilter> list, Class<?> cls) {
        for (ReflectionAccessFilter reflectionAccessFilter : list) {
            ReflectionAccessFilter.FilterResult check = reflectionAccessFilter.check(cls);
            if (check != ReflectionAccessFilter.FilterResult.INDECISIVE) {
                return check;
            }
        }
        return ReflectionAccessFilter.FilterResult.ALLOW;
    }

    public static boolean canAccess(AccessibleObject accessibleObject, Object obj) {
        return AccessChecker.INSTANCE.canAccess(accessibleObject, obj);
    }

    /* loaded from: classes2.dex */
    private static abstract class AccessChecker {
        public static final AccessChecker INSTANCE;

        public abstract boolean canAccess(AccessibleObject accessibleObject, Object obj);

        private AccessChecker() {
        }

        /* JADX WARN: Removed duplicated region for block: B:8:0x001f  */
        static {
            com.google.gson.internal.ReflectionAccessFilterHelper.AccessChecker r1;
            if (com.google.gson.internal.JavaVersion.isJava9OrLater()) {
                try {
                    final java.lang.reflect.Method r0 = java.lang.reflect.AccessibleObject.class.getDeclaredMethod("canAccess", java.lang.Object.class);
                    r1 = new com.google.gson.internal.ReflectionAccessFilterHelper.AccessChecker() { // from class: com.google.gson.internal.ReflectionAccessFilterHelper.AccessChecker.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super();
                        }

                        @Override // com.google.gson.internal.ReflectionAccessFilterHelper.AccessChecker
                        public boolean canAccess(java.lang.reflect.AccessibleObject r4, java.lang.Object r5) {
                            try {
                                return ((java.lang.Boolean) r1.invoke(r4, r5)).booleanValue();
                            } catch (java.lang.Exception r4) {
                                throw new java.lang.RuntimeException("Failed invoking canAccess", r4);
                            }
                        }
                    };
                } catch (java.lang.NoSuchMethodException unused) {
                }
                if (r1 == null) {
                    r1 = new com.google.gson.internal.ReflectionAccessFilterHelper.AccessChecker() { // from class: com.google.gson.internal.ReflectionAccessFilterHelper.AccessChecker.2
                        @Override // com.google.gson.internal.ReflectionAccessFilterHelper.AccessChecker
                        public boolean canAccess(java.lang.reflect.AccessibleObject r1, java.lang.Object r2) {
                            return true;
                        }

                        {
                        }
                    };
                }
                com.google.gson.internal.ReflectionAccessFilterHelper.AccessChecker.INSTANCE = r1;
            }
            r1 = null;
            if (r1 == null) {
            }
            com.google.gson.internal.ReflectionAccessFilterHelper.AccessChecker.INSTANCE = r1;
        }
    }
}
