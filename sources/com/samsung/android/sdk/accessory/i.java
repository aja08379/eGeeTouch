package com.samsung.android.sdk.accessory;

import android.util.Log;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class i {
    private static final String a = "[SA_SDK]" + i.class.getSimpleName();
    private static Map<Object, a> b = new ConcurrentHashMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static class a {
        com.samsung.accessory.a.a.a a;
        private int b;
        private int c;
        private e d;
        private Object e;

        private a(int i, int i2, Object obj) {
            this.b = i;
            this.c = i2;
            this.e = obj;
            this.d = new e(i);
        }

        /* synthetic */ a(int i, int i2, Object obj, byte b) {
            this(i, i2, obj);
        }

        private int b(int i, byte[] bArr, int i2, int i3) {
            try {
                this.a.a(bArr, i2, i3);
                Log.d(i.a, String.valueOf(this.e) + " : payload received [" + i + "] : " + this.a.d());
                if (i != 0) {
                    return i == this.d.c() ? 1 : 2;
                }
                return 1;
            } catch (com.samsung.accessory.a.a.c e) {
                Log.e(i.a, "BufferException: " + e.getLocalizedMessage());
                return 3;
            }
        }

        public final int a(int i, byte[] bArr, int i2, int i3) throws IOException {
            com.samsung.accessory.a.a.a aVar;
            if (i == 0) {
                com.samsung.accessory.a.a.a aVar2 = this.a;
                if (aVar2 != null && aVar2.d() > 0) {
                    if (this.b != 2) {
                        Log.e(i.a, "Received a non-fragment in <" + this.e + "> while blob receive in progress...!");
                        return 3;
                    }
                    this.a.e();
                    this.a = null;
                    Log.w(i.a, "MsgFragment received out of order! clearing legacy buffer and accepting new...");
                }
                this.a = b.a().b(i3);
                return b(i, bArr, i2, i3);
            } else if (i == this.d.a()) {
                if (this.b == 2 && (aVar = this.a) != null) {
                    aVar.e();
                    this.a = null;
                    Log.w(i.a, "MsgFragment received out of order! Clearing legacy buffer and accepting new...");
                }
                if (this.a == null) {
                    this.a = b.a().b(this.c);
                }
                return b(i, bArr, i2, i3);
            } else if (i != this.d.b() && i != this.d.c()) {
                Log.e(i.a, "invalid fragment index:" + i + " received in <" + this.e + ">! ");
                return 3;
            } else {
                com.samsung.accessory.a.a.a aVar3 = this.a;
                if (aVar3 == null || aVar3.d() == 0) {
                    throw new IOException("Reassembling failed, received invalid fragment!");
                }
                return b(i, bArr, i2, i3);
            }
        }
    }

    i() {
    }

    private static int a(int i, Object obj, int i2, int i3, byte[] bArr, int i4, int i5) throws IOException {
        a aVar = b.get(obj);
        if (aVar == null) {
            aVar = new a(i, i2, obj, (byte) 0);
            b.put(obj, aVar);
        }
        return aVar.a(i3, bArr, i4, i5);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int a(Object obj, int i, int i2, byte[] bArr, int i3, int i4) throws IOException {
        return a(1, obj, i, i2, bArr, i3, i4);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] a(Object obj) throws IOException {
        a aVar = b.get(obj);
        if (aVar == null || aVar.a == null) {
            return null;
        }
        return aVar.a.b();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int b(Object obj, int i, int i2, byte[] bArr, int i3, int i4) throws IOException {
        return a(2, obj, i, i2, bArr, i3, i4);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void b(Object obj) {
        a remove = b.remove(obj);
        if (remove == null || remove.a == null) {
            return;
        }
        remove.a.e();
        remove.a = null;
    }
}
