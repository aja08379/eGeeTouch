package com.samsung.android.sdk.accessory;
/* loaded from: classes2.dex */
public final class d extends Exception {
    private int a;

    public d() {
    }

    public d(int i, String str) {
        super(str);
        this.a = i;
    }

    public d(String str, Throwable th) {
        super(str, th);
        this.a = 2048;
    }

    public final int a() {
        return this.a;
    }
}
