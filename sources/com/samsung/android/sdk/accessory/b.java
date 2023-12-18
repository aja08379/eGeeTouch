package com.samsung.android.sdk.accessory;

import android.content.Context;
/* loaded from: classes2.dex */
public abstract class b {
    public static b a = null;
    private static /* synthetic */ boolean b = true;

    public static synchronized b a() {
        synchronized (b.class) {
            b bVar = a;
            if (bVar != null) {
                return bVar;
            }
            try {
                Class<?> cls = Class.forName("com.samsung.accessory.a.a.b");
                if (cls != null) {
                    Class.forName("com.samsung.accessory.a.a.b", true, cls.getClassLoader());
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                if (!b) {
                    throw new AssertionError(e);
                }
            }
            if (!b && a == null) {
                throw new AssertionError("The DEFAULT field must be initialized");
            }
            return a;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract void a(int i);

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract void a(Context context);

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract com.samsung.accessory.a.a.a b(int i);
}
