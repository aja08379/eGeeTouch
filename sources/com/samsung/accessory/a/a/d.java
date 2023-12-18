package com.samsung.accessory.a.a;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.util.SparseArray;
import com.facebook.internal.NativeProtocol;
import java.util.LinkedList;
import java.util.TreeMap;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class d {
    private static int a;
    private static int b;
    private static int c;
    private static int d;
    private static int e;
    private static boolean f;
    private static String g;
    private static final TreeMap<Integer, Object> h = new TreeMap<>();
    private static final SparseArray<a> i = new SparseArray<>();
    private static final Object j = new Object();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static class a {
        private static int a;
        private LinkedList<byte[]> b;
        private int c;
        private int d;
        private int e;
        private final int f;

        private a(int i) {
            this.f = i;
            this.c = 0;
            this.d = 0;
            this.b = null;
            this.e = 1;
        }

        /* synthetic */ a(int i, byte b) {
            this(i);
        }

        public static void a(a aVar) {
            aVar.f();
            a++;
        }

        private synchronized void f() {
            this.c++;
        }

        final synchronized LinkedList<byte[]> a() {
            if (this.b == null) {
                this.b = new LinkedList<>();
            }
            return this.b;
        }

        final synchronized LinkedList<byte[]> b() {
            return this.b;
        }

        final synchronized int c() {
            return this.f;
        }

        final float d() {
            float f;
            synchronized (d.j) {
                f = d.a * (this.c / a);
            }
            return f;
        }

        final synchronized boolean e() {
            this.d++;
            int size = this.b.size();
            int i = this.e;
            if (size == i) {
                int i2 = this.d;
                if ((((float) i2) == 0.0f ? this.c : this.c / i2) < 0.9f) {
                    return false;
                }
                this.e = ((i * 3) / 2) + 1;
            }
            return true;
        }
    }

    public static com.samsung.accessory.a.a.a a(int i2) {
        if (a()) {
            return c(i2);
        }
        throw new RuntimeException("Bufferpool not initialised!");
    }

    private static com.samsung.accessory.a.a.a a(int i2, int i3) {
        synchronized (j) {
            a d2 = d(i2);
            com.samsung.accessory.a.a.a aVar = null;
            if (d2 == null) {
                return null;
            }
            LinkedList<byte[]> b2 = d2.b();
            if (b2 != null && !b2.isEmpty()) {
                byte[] removeLast = b2.removeLast();
                if (removeLast != null) {
                    e -= removeLast.length;
                    a.a(d2);
                    aVar = new com.samsung.accessory.a.a.a(removeLast, i3);
                }
                return aVar;
            }
            return null;
        }
    }

    private static void a(int i2, String str) {
        if (i2 == 2) {
            Log.v(g, str);
        } else if (i2 == 3) {
            Log.d(g, str);
        } else if (i2 == 4) {
            Log.i(g, str);
        } else if (i2 == 5) {
            Log.w(g, str);
        } else if (i2 != 6) {
        } else {
            Log.e(g, str);
        }
    }

    public static void a(e eVar) {
        if (a()) {
            a(5, "BufferPool already initialised!");
            return;
        }
        synchronized (j) {
            e = 0;
            if (eVar.c < 24 || eVar.b < eVar.c) {
                throw new RuntimeException("Failed to initialise the Bufferpool! [Cache size=" + eVar.b + "; Max chunk size=" + eVar.c + "]");
            }
            g = eVar.a;
            a = eVar.b;
            int i2 = eVar.c;
            b = i2;
            if (i2 > 66560) {
                i2 = 66560;
            }
            int i3 = a;
            c = i3 / 4;
            d = i3 / 2;
            int i4 = 36;
            int i5 = 24;
            while (i5 <= i2) {
                if (i5 <= i2) {
                    e(i5);
                }
                if (i5 != 24 && i4 <= i2) {
                    e(i4);
                }
                i5 <<= 1;
                i4 <<= 1;
            }
            e();
            int i6 = b;
            if (i6 > 66560) {
                e(i6);
            } else {
                e(i2);
            }
            f = true;
            a(4, "BufferPool[v1.0.2] initialised with capacity " + (a / 1048576) + "MB");
        }
    }

    public static boolean a() {
        boolean z;
        synchronized (j) {
            z = f;
        }
        return z;
    }

    public static boolean a(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        if (activityManager != null) {
            return Build.VERSION.SDK_INT >= 19 ? activityManager.isLowRamDevice() : activityManager.getMemoryClass() < 128;
        }
        a(5, "isLowMemoryDevice(): ActivityManager is null!");
        return true;
    }

    public static boolean a(byte[] bArr) {
        String str;
        if (!a()) {
            str = "Failed to recycle buffer - Bufferpool not initialised!";
        } else if (bArr != null) {
            int length = bArr.length;
            Object obj = j;
            synchronized (obj) {
                if (length >= 24) {
                    if (length <= b) {
                        Integer floorKey = h.floorKey(Integer.valueOf(length));
                        int intValue = floorKey == null ? length : floorKey.intValue();
                        synchronized (obj) {
                            a d2 = d(intValue);
                            if (d2 != null) {
                                LinkedList<byte[]> a2 = d2.a();
                                if (d2.e()) {
                                    if (e + length > a) {
                                        int d3 = ((int) d2.d()) / intValue;
                                        if (a2.size() >= d3) {
                                            a(5, "Cannot recycle buffer '" + intValue + "', Buffer chunk count(" + a2.size() + ") exceeded the limit" + d3 + "!");
                                            return false;
                                        }
                                        d();
                                        if (e + length > a) {
                                            a(5, "Cannot recycle buffer '" + intValue + "', Buffer cache limit exceeded!!!");
                                            return false;
                                        }
                                    }
                                    if (intValue == length) {
                                        a2.addLast(bArr);
                                    } else {
                                        a2.addFirst(bArr);
                                    }
                                    e += length;
                                    return true;
                                }
                                return false;
                            }
                            return false;
                        }
                    }
                }
                a(5, "Cannot recycle buffer '" + length + "', Non-matcing size!");
                return false;
            }
        } else {
            str = "Cannot recycle null buffer!";
        }
        a(5, str);
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0053 A[Catch: all -> 0x00ae, TryCatch #0 {, blocks: (B:19:0x0027, B:24:0x0030, B:26:0x0053, B:27:0x0060, B:29:0x0062, B:31:0x006a, B:33:0x0078, B:35:0x007e, B:37:0x0082, B:38:0x008f, B:40:0x0093, B:41:0x0096, B:42:0x00ab, B:22:0x002b, B:23:0x002e), top: B:48:0x0011 }] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0062 A[Catch: all -> 0x00ae, TryCatch #0 {, blocks: (B:19:0x0027, B:24:0x0030, B:26:0x0053, B:27:0x0060, B:29:0x0062, B:31:0x006a, B:33:0x0078, B:35:0x007e, B:37:0x0082, B:38:0x008f, B:40:0x0093, B:41:0x0096, B:42:0x00ab, B:22:0x002b, B:23:0x002e), top: B:48:0x0011 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean b(int r7) {
        int r3;
        if (!a()) {
            a(5, "Failed to clear cache - Bufferpool not initialised!");
            return false;
        }
        synchronized (com.samsung.accessory.a.a.d.j) {
            if (r7 != 5) {
                if (r7 != 10) {
                    if (r7 != 15) {
                        if (r7 != 40) {
                            if (r7 != 60) {
                                if (r7 != 80) {
                                    return false;
                                }
                            }
                        }
                    }
                    r3 = 0;
                    a(2, "ClearCache[" + r7 + "] : Cache Size BEFORE = " + com.samsung.accessory.a.a.d.e);
                    if (com.samsung.accessory.a.a.d.e <= r3) {
                        a(5, "ClearCache : Current cache size is lesser than the threshold of ".concat(java.lang.String.valueOf(r3)));
                        return false;
                    }
                    int r7 = com.samsung.accessory.a.a.d.i.size();
                    for (int r2 = 0; r2 < r7; r2++) {
                        java.util.LinkedList<byte[]> r1 = com.samsung.accessory.a.a.d.i.valueAt(r2).b();
                        if (r1 != null) {
                            while (!r1.isEmpty() && com.samsung.accessory.a.a.d.e > r3) {
                                com.samsung.accessory.a.a.d.e -= r1.removeLast().length;
                            }
                        }
                        if (com.samsung.accessory.a.a.d.e <= r3) {
                            break;
                        }
                    }
                    a(3, "ClearCache : Cache Size AFTER = " + com.samsung.accessory.a.a.d.e);
                    return true;
                }
                r3 = com.samsung.accessory.a.a.d.d;
                a(2, "ClearCache[" + r7 + "] : Cache Size BEFORE = " + com.samsung.accessory.a.a.d.e);
                if (com.samsung.accessory.a.a.d.e <= r3) {
                }
            }
            r3 = com.samsung.accessory.a.a.d.c;
            a(2, "ClearCache[" + r7 + "] : Cache Size BEFORE = " + com.samsung.accessory.a.a.d.e);
            if (com.samsung.accessory.a.a.d.e <= r3) {
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x0076, code lost:
        r2 = new com.samsung.accessory.a.a.a(new byte[r1], r6);
        r6 = d(r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0081, code lost:
        if (r6 == null) goto L39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0083, code lost:
        com.samsung.accessory.a.a.d.a.a(r6);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static com.samsung.accessory.a.a.a c(int r6) {
        synchronized (com.samsung.accessory.a.a.d.j) {
            if (r6 > com.samsung.accessory.a.a.d.b) {
                a(5, "Buffer '" + r6 + "' is not matching with the pool sizes! creating new...");
                return new com.samsung.accessory.a.a.a(new byte[r6], r6);
            }
            java.lang.Integer r1 = com.samsung.accessory.a.a.d.h.ceilingKey(java.lang.Integer.valueOf(r6));
            int r1 = r1 == null ? r6 : r1.intValue();
            com.samsung.accessory.a.a.a r2 = a(r1, r6);
            if (r2 == null) {
                int r4 = r1;
                for (int r3 = 1; r2 == null && r3 <= 3; r3++) {
                    java.lang.Integer r4 = com.samsung.accessory.a.a.d.h.higherKey(java.lang.Integer.valueOf(r4));
                    r4 = r4 == null ? Integer.MAX_VALUE : r4.intValue();
                    if (r4 < 24 || r4 > 66560 || r4 > com.samsung.accessory.a.a.d.b) {
                        break;
                    }
                    r2 = a(r4, r6);
                }
            }
            return r2;
        }
    }

    private static int d() {
        int i2;
        synchronized (j) {
            int i3 = e;
            int size = i.size();
            for (int i4 = 0; i4 < size; i4++) {
                a valueAt = i.valueAt(i4);
                if (valueAt != null) {
                    int d2 = (int) (valueAt.d() / valueAt.c());
                    LinkedList<byte[]> b2 = valueAt.b();
                    int size2 = b2 == null ? 0 : b2.size();
                    while (size2 > d2) {
                        if (b2 != null) {
                            e -= b2.removeLast().length;
                            size2--;
                        }
                    }
                }
            }
            a(5, "Pool Stabilized; Cache size reduced from  " + i3 + " -> " + e);
            i2 = i3 - e;
        }
        return i2;
    }

    private static a d(int i2) {
        a aVar;
        synchronized (j) {
            aVar = i.get(i2);
        }
        return aVar;
    }

    private static void e() {
        int[] iArr = {30731, 32779, 61451, NativeProtocol.MESSAGE_GET_INSTALL_DATA_REPLY};
        synchronized (j) {
            for (int i2 = 0; i2 < 4; i2++) {
                e(iArr[i2]);
            }
        }
    }

    private static boolean e(int i2) {
        synchronized (j) {
            if (i2 <= b) {
                SparseArray<a> sparseArray = i;
                if (sparseArray.indexOfKey(i2) < 0) {
                    h.put(Integer.valueOf(i2), null);
                    sparseArray.put(i2, new a(i2, (byte) 0));
                    return true;
                }
            }
            return false;
        }
    }
}
