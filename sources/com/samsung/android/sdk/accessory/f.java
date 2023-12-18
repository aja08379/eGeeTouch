package com.samsung.android.sdk.accessory;

import android.util.Log;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/* loaded from: classes2.dex */
class f {
    private static final String a = "[SA_SDK]" + f.class.getSimpleName();
    private static Map<Object, f> b = new ConcurrentHashMap();
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;
    private byte[] h;
    private e i;
    private Object j;

    /* JADX INFO: Access modifiers changed from: package-private */
    public f(int i, Object obj) {
        this.j = obj;
        this.i = new e(i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final e a() throws IOException {
        e eVar;
        int c;
        byte[] bArr = this.h;
        if (bArr != null) {
            int i = this.c;
            if (i == bArr.length) {
                return null;
            }
            int length = bArr.length;
            int i2 = this.d;
            if (length <= i2) {
                i2 = bArr.length;
                eVar = this.i;
                c = 0;
            } else if (i == 0) {
                eVar = this.i;
                c = eVar.a();
            } else if (bArr.length - i > i2) {
                eVar = this.i;
                c = eVar.b();
            } else {
                i2 = bArr.length - i;
                eVar = this.i;
                c = eVar.c();
            }
            eVar.a(c);
            com.samsung.accessory.a.a.a b2 = b.a().b(this.e + i2 + this.g + this.f);
            b2.a(this.e);
            try {
                b2.a(this.h, this.c, i2);
                this.i.a(b2);
                this.c += i2;
                return this.i;
            } catch (com.samsung.accessory.a.a.c e) {
                Log.e(a, "BufferException: " + e.getLocalizedMessage());
                return null;
            }
        }
        throw new IOException("Send Failed! Fragmenter is already shutdown");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(int i, int i2, int i3, int i4, byte[] bArr) throws IOException {
        if (b.containsKey(this.j)) {
            throw new IOException("Concurrent write detected! Another write active: " + this.j);
        }
        this.e = i;
        this.f = i2;
        this.g = i4;
        this.d = i3 - i4;
        this.h = bArr;
        b.put(this.j, this);
        Log.v(a, "confiure: " + i + " " + i2 + " " + i3 + " " + i4 + " " + bArr.length);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final e b() {
        return this.i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int c() {
        return this.c;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void d() {
        e eVar = this.i;
        if (eVar != null) {
            eVar.h();
        }
        f fVar = b.get(this.j);
        if (fVar != null && fVar.equals(this)) {
            b.remove(this.j);
        }
        this.h = null;
    }
}
