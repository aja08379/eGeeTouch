package com.samsung.android.sdk.accessory;

import android.util.Log;
/* loaded from: classes2.dex */
final class e {
    private int a;
    private int b;
    private int c;
    private int d;
    private com.samsung.accessory.a.a.a e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public e(int i) {
        if (i == 1) {
            this.a = 1;
            this.b = 1;
            this.c = 2;
        } else if (i != 2) {
            Log.e("[SA_SDK]", "invalid fragment mode!");
        } else {
            this.a = 1;
            this.b = 2;
            this.c = 3;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int a() {
        return this.a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(int i) {
        this.d = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(com.samsung.accessory.a.a.a aVar) {
        this.e = aVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int b() {
        return this.b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int c() {
        return this.c;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final byte[] d() {
        return this.e.a();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int e() {
        return this.d;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int f() {
        return this.e.d();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int g() {
        return this.e.c();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void h() {
        com.samsung.accessory.a.a.a aVar = this.e;
        if (aVar != null) {
            aVar.e();
        }
    }
}
