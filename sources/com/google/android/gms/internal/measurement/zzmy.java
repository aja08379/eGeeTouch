package com.google.android.gms.internal.measurement;

import java.lang.reflect.Field;
import java.nio.Buffer;
import java.security.AccessController;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.Unsafe;
/* compiled from: com.google.android.gms:play-services-measurement-base@@21.2.0 */
/* loaded from: classes.dex */
final class zzmy {
    static final long zza;
    static final boolean zzb;
    private static final Unsafe zzc;
    private static final Class zzd;
    private static final boolean zze;
    private static final zzmx zzf;
    private static final boolean zzg;
    private static final boolean zzh;

    /* JADX WARN: Removed duplicated region for block: B:22:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0142  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0152  */
    static {
        boolean r8;
        com.google.android.gms.internal.measurement.zzmx r8;
        boolean r5;
        java.lang.reflect.Field r0;
        com.google.android.gms.internal.measurement.zzmx r1;
        sun.misc.Unsafe r5 = zzg();
        com.google.android.gms.internal.measurement.zzmy.zzc = r5;
        com.google.android.gms.internal.measurement.zzmy.zzd = com.google.android.gms.internal.measurement.zziq.zza();
        boolean r6 = zzv(java.lang.Long.TYPE);
        com.google.android.gms.internal.measurement.zzmy.zze = r6;
        boolean r7 = zzv(java.lang.Integer.TYPE);
        com.google.android.gms.internal.measurement.zzmx r8 = null;
        if (r5 != null) {
            if (r6) {
                r8 = new com.google.android.gms.internal.measurement.zzmw(r5);
            } else if (r7) {
                r8 = new com.google.android.gms.internal.measurement.zzmv(r5);
            }
        }
        com.google.android.gms.internal.measurement.zzmy.zzf = r8;
        if (r8 != null) {
            try {
                java.lang.Class<?> r8 = r8.zza.getClass();
                r8.getMethod("objectFieldOffset", java.lang.reflect.Field.class);
                r8.getMethod("getLong", java.lang.Object.class, java.lang.Long.TYPE);
            } catch (java.lang.Throwable r8) {
                zzh(r8);
            }
            if (zzB() != null) {
                r8 = true;
                com.google.android.gms.internal.measurement.zzmy.zzg = r8;
                r8 = com.google.android.gms.internal.measurement.zzmy.zzf;
                if (r8 != null) {
                    try {
                        java.lang.Class<?> r8 = r8.zza.getClass();
                        r8.getMethod("objectFieldOffset", java.lang.reflect.Field.class);
                        r8.getMethod("arrayBaseOffset", java.lang.Class.class);
                        r8.getMethod("arrayIndexScale", java.lang.Class.class);
                        r8.getMethod("getInt", java.lang.Object.class, java.lang.Long.TYPE);
                        r8.getMethod("putInt", java.lang.Object.class, java.lang.Long.TYPE, java.lang.Integer.TYPE);
                        r8.getMethod("getLong", java.lang.Object.class, java.lang.Long.TYPE);
                        r8.getMethod("putLong", java.lang.Object.class, java.lang.Long.TYPE, java.lang.Long.TYPE);
                        r8.getMethod("getObject", java.lang.Object.class, java.lang.Long.TYPE);
                        r8.getMethod("putObject", java.lang.Object.class, java.lang.Long.TYPE, java.lang.Object.class);
                        r5 = true;
                    } catch (java.lang.Throwable r5) {
                        zzh(r5);
                    }
                    com.google.android.gms.internal.measurement.zzmy.zzh = r5;
                    com.google.android.gms.internal.measurement.zzmy.zza = zzz(byte[].class);
                    zzz(boolean[].class);
                    zzA(boolean[].class);
                    zzz(int[].class);
                    zzA(int[].class);
                    zzz(long[].class);
                    zzA(long[].class);
                    zzz(float[].class);
                    zzA(float[].class);
                    zzz(double[].class);
                    zzA(double[].class);
                    zzz(java.lang.Object[].class);
                    zzA(java.lang.Object[].class);
                    r0 = zzB();
                    if (r0 != null && (r1 = com.google.android.gms.internal.measurement.zzmy.zzf) != null) {
                        r1.zzl(r0);
                    }
                    com.google.android.gms.internal.measurement.zzmy.zzb = java.nio.ByteOrder.nativeOrder() == java.nio.ByteOrder.BIG_ENDIAN;
                }
                r5 = false;
                com.google.android.gms.internal.measurement.zzmy.zzh = r5;
                com.google.android.gms.internal.measurement.zzmy.zza = zzz(byte[].class);
                zzz(boolean[].class);
                zzA(boolean[].class);
                zzz(int[].class);
                zzA(int[].class);
                zzz(long[].class);
                zzA(long[].class);
                zzz(float[].class);
                zzA(float[].class);
                zzz(double[].class);
                zzA(double[].class);
                zzz(java.lang.Object[].class);
                zzA(java.lang.Object[].class);
                r0 = zzB();
                if (r0 != null) {
                    r1.zzl(r0);
                }
                com.google.android.gms.internal.measurement.zzmy.zzb = java.nio.ByteOrder.nativeOrder() == java.nio.ByteOrder.BIG_ENDIAN;
            }
        }
        r8 = false;
        com.google.android.gms.internal.measurement.zzmy.zzg = r8;
        r8 = com.google.android.gms.internal.measurement.zzmy.zzf;
        if (r8 != null) {
        }
        r5 = false;
        com.google.android.gms.internal.measurement.zzmy.zzh = r5;
        com.google.android.gms.internal.measurement.zzmy.zza = zzz(byte[].class);
        zzz(boolean[].class);
        zzA(boolean[].class);
        zzz(int[].class);
        zzA(int[].class);
        zzz(long[].class);
        zzA(long[].class);
        zzz(float[].class);
        zzA(float[].class);
        zzz(double[].class);
        zzA(double[].class);
        zzz(java.lang.Object[].class);
        zzA(java.lang.Object[].class);
        r0 = zzB();
        if (r0 != null) {
        }
        com.google.android.gms.internal.measurement.zzmy.zzb = java.nio.ByteOrder.nativeOrder() == java.nio.ByteOrder.BIG_ENDIAN;
    }

    private zzmy() {
    }

    private static int zzA(Class cls) {
        if (zzh) {
            return zzf.zzi(cls);
        }
        return -1;
    }

    private static Field zzB() {
        int i = zziq.zza;
        Field zzC = zzC(Buffer.class, "effectiveDirectAddress");
        if (zzC == null) {
            Field zzC2 = zzC(Buffer.class, "address");
            if (zzC2 == null || zzC2.getType() != Long.TYPE) {
                return null;
            }
            return zzC2;
        }
        return zzC;
    }

    private static Field zzC(Class cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (Throwable unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void zzD(Object obj, long j, byte b) {
        long j2 = (-4) & j;
        zzmx zzmxVar = zzf;
        int zzj = zzmxVar.zzj(obj, j2);
        int i = ((~((int) j)) & 3) << 3;
        zzmxVar.zzn(obj, j2, ((255 & b) << i) | (zzj & (~(255 << i))));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void zzE(Object obj, long j, byte b) {
        long j2 = (-4) & j;
        zzmx zzmxVar = zzf;
        int i = (((int) j) & 3) << 3;
        zzmxVar.zzn(obj, j2, ((255 & b) << i) | (zzmxVar.zzj(obj, j2) & (~(255 << i))));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static double zza(Object obj, long j) {
        return zzf.zza(obj, j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static float zzb(Object obj, long j) {
        return zzf.zzb(obj, j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzc(Object obj, long j) {
        return zzf.zzj(obj, j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long zzd(Object obj, long j) {
        return zzf.zzk(obj, j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object zze(Class cls) {
        try {
            return zzc.allocateInstance(cls);
        } catch (InstantiationException e) {
            throw new IllegalStateException(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object zzf(Object obj, long j) {
        return zzf.zzm(obj, j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Unsafe zzg() {
        try {
            return (Unsafe) AccessController.doPrivileged(new zzmu());
        } catch (Throwable unused) {
            return null;
        }
    }

    static /* bridge */ /* synthetic */ void zzh(Throwable th) {
        Logger.getLogger(zzmy.class.getName()).logp(Level.WARNING, "com.google.protobuf.UnsafeUtil", "logMissingMethod", "platform method missing - proto runtime falling back to safer methods: ".concat(th.toString()));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zzm(Object obj, long j, boolean z) {
        zzf.zzc(obj, j, z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zzn(byte[] bArr, long j, byte b) {
        zzf.zzd(bArr, zza + j, b);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zzo(Object obj, long j, double d) {
        zzf.zze(obj, j, d);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zzp(Object obj, long j, float f) {
        zzf.zzf(obj, j, f);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zzq(Object obj, long j, int i) {
        zzf.zzn(obj, j, i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zzr(Object obj, long j, long j2) {
        zzf.zzo(obj, j, j2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zzs(Object obj, long j, Object obj2) {
        zzf.zzp(obj, j, obj2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ boolean zzt(Object obj, long j) {
        return ((byte) ((zzf.zzj(obj, (-4) & j) >>> ((int) (((~j) & 3) << 3))) & 255)) != 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ boolean zzu(Object obj, long j) {
        return ((byte) ((zzf.zzj(obj, (-4) & j) >>> ((int) ((j & 3) << 3))) & 255)) != 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    static boolean zzv(Class cls) {
        int i = zziq.zza;
        try {
            Class cls2 = zzd;
            cls2.getMethod("peekLong", cls, Boolean.TYPE);
            cls2.getMethod("pokeLong", cls, Long.TYPE, Boolean.TYPE);
            cls2.getMethod("pokeInt", cls, Integer.TYPE, Boolean.TYPE);
            cls2.getMethod("peekInt", cls, Boolean.TYPE);
            cls2.getMethod("pokeByte", cls, Byte.TYPE);
            cls2.getMethod("peekByte", cls);
            cls2.getMethod("pokeByteArray", cls, byte[].class, Integer.TYPE, Integer.TYPE);
            cls2.getMethod("peekByteArray", cls, byte[].class, Integer.TYPE, Integer.TYPE);
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean zzw(Object obj, long j) {
        return zzf.zzg(obj, j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean zzx() {
        return zzh;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean zzy() {
        return zzg;
    }

    private static int zzz(Class cls) {
        if (zzh) {
            return zzf.zzh(cls);
        }
        return -1;
    }
}
