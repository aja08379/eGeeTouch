package com.samsung.accessory.a.a;
/* loaded from: classes2.dex */
public final class a {
    private final byte[] a;
    private final int b;
    private boolean e = false;
    private int d = 0;
    private int c = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public a(byte[] bArr, int i) {
        this.a = bArr;
        this.b = i;
    }

    public final synchronized void a(int i) {
        if (this.e) {
            throw new IllegalStateException("Cannot refer to a recycled buffer!");
        }
        this.c = i;
    }

    public final synchronized void a(byte[] bArr, int i, int i2) throws c {
        if (this.e) {
            throw new IllegalStateException("Failed to extract from a recycled buffer!");
        }
        int i3 = this.c;
        int i4 = this.d;
        if (i3 + i4 + i2 > this.b) {
            throw new c("Cannot extract from byte[]. Buffer length exceeded! [buff offset=" + this.c + "; payload len=" + this.d + "; length to write = " + i2 + "; buff len = " + this.b + "]");
        }
        System.arraycopy(bArr, i, this.a, i3 + i4, i2);
        this.d += i2;
    }

    public final synchronized byte[] a() {
        if (this.e) {
            throw new IllegalStateException("Cannot refer to a recycled buffer!");
        }
        return this.a;
    }

    public final synchronized byte[] b() {
        byte[] bArr;
        if (this.e) {
            throw new IllegalStateException("Cannot refer to a recycled buffer!");
        }
        int i = this.d;
        bArr = new byte[i];
        System.arraycopy(this.a, this.c, bArr, 0, i);
        return bArr;
    }

    public final synchronized int c() {
        if (this.e) {
            throw new IllegalStateException("Cannot refer to a recycled buffer!");
        }
        return this.c;
    }

    public final synchronized int d() {
        if (this.e) {
            throw new IllegalStateException("Cannot refer to a recycled buffer!");
        }
        return this.d;
    }

    public final synchronized boolean e() {
        if (this.e) {
            return false;
        }
        boolean a = d.a(this.a);
        this.e = a;
        return a;
    }
}
