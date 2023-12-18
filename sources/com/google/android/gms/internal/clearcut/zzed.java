package com.google.android.gms.internal.clearcut;

import java.lang.reflect.Field;
import java.util.Arrays;
/* loaded from: classes.dex */
final class zzed {
    private final int flags;
    private final Object[] zzmj;
    private final int zzmk;
    private final int zzml;
    private final int zzmm;
    private final int[] zzms;
    private final zzee zznh;
    private Class<?> zzni;
    private final int zznj;
    private final int zznk;
    private final int zznl;
    private final int zznm;
    private final int zznn;
    private final int zzno;
    private int zznp;
    private int zznq;
    private int zznr = Integer.MAX_VALUE;
    private int zzns = Integer.MIN_VALUE;
    private int zznt = 0;
    private int zznu = 0;
    private int zznv = 0;
    private int zznw = 0;
    private int zznx = 0;
    private int zzny;
    private int zznz;
    private int zzoa;
    private int zzob;
    private int zzoc;
    private Field zzod;
    private Object zzoe;
    private Object zzof;
    private Object zzog;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzed(Class<?> cls, String str, Object[] objArr) {
        this.zzni = cls;
        zzee zzeeVar = new zzee(str);
        this.zznh = zzeeVar;
        this.zzmj = objArr;
        this.flags = zzeeVar.next();
        int next = zzeeVar.next();
        this.zznj = next;
        if (next == 0) {
            this.zznk = 0;
            this.zznl = 0;
            this.zzmk = 0;
            this.zzml = 0;
            this.zznm = 0;
            this.zznn = 0;
            this.zzmm = 0;
            this.zzno = 0;
            this.zzms = null;
            return;
        }
        int next2 = zzeeVar.next();
        this.zznk = next2;
        int next3 = zzeeVar.next();
        this.zznl = next3;
        this.zzmk = zzeeVar.next();
        this.zzml = zzeeVar.next();
        this.zznn = zzeeVar.next();
        this.zzmm = zzeeVar.next();
        this.zznm = zzeeVar.next();
        this.zzno = zzeeVar.next();
        int next4 = zzeeVar.next();
        this.zzms = next4 != 0 ? new int[next4] : null;
        this.zznp = (next2 << 1) + next3;
    }

    private static Field zza(Class<?> cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException unused) {
            Field[] declaredFields = cls.getDeclaredFields();
            for (Field field : declaredFields) {
                if (str.equals(field.getName())) {
                    return field;
                }
            }
            String name = cls.getName();
            String arrays = Arrays.toString(declaredFields);
            throw new RuntimeException(new StringBuilder(String.valueOf(str).length() + 40 + String.valueOf(name).length() + String.valueOf(arrays).length()).append("Field ").append(str).append(" for ").append(name).append(" not found. Known fields are ").append(arrays).toString());
        }
    }

    private final Object zzcw() {
        Object[] objArr = this.zzmj;
        int i = this.zznp;
        this.zznp = i + 1;
        return objArr[i];
    }

    private final boolean zzcz() {
        return (this.flags & 1) == 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00c6, code lost:
        if (zzcz() != false) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00c8, code lost:
        r5.zzof = zzcw();
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x0154, code lost:
        if (((r5.zznz & 2048) != 0) != false) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x015b, code lost:
        if (zzcz() != false) goto L32;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean next() {
        int r0;
        java.lang.Object r0;
        if (r5.zznh.hasNext()) {
            r5.zzny = r5.zznh.next();
            int r0 = r5.zznh.next();
            r5.zznz = r0;
            int r0 = r0 & 255;
            r5.zzoa = r0;
            int r2 = r5.zzny;
            if (r2 < r5.zznr) {
                r5.zznr = r2;
            }
            if (r2 > r5.zzns) {
                r5.zzns = r2;
            }
            if (r0 == com.google.android.gms.internal.clearcut.zzcb.MAP.id()) {
                r5.zznt++;
            } else if (r5.zzoa >= com.google.android.gms.internal.clearcut.zzcb.DOUBLE_LIST.id() && r5.zzoa <= com.google.android.gms.internal.clearcut.zzcb.GROUP_LIST.id()) {
                r5.zznu++;
            }
            int r0 = r5.zznx + 1;
            r5.zznx = r0;
            if (com.google.android.gms.internal.clearcut.zzeh.zzc(r5.zznr, r5.zzny, r0)) {
                int r0 = r5.zzny + 1;
                r5.zznw = r0;
                r0 = r0 - r5.zznr;
            } else {
                r0 = r5.zznv + 1;
            }
            r5.zznv = r0;
            if ((r5.zznz & 1024) != 0) {
                int[] r0 = r5.zzms;
                int r2 = r5.zznq;
                r5.zznq = r2 + 1;
                r0[r2] = r5.zzny;
            }
            r5.zzoe = null;
            r5.zzof = null;
            r5.zzog = null;
            if (zzda()) {
                r5.zzob = r5.zznh.next();
                if (r5.zzoa != com.google.android.gms.internal.clearcut.zzcb.MESSAGE.id() + 51 && r5.zzoa != com.google.android.gms.internal.clearcut.zzcb.GROUP.id() + 51) {
                    if (r5.zzoa == com.google.android.gms.internal.clearcut.zzcb.ENUM.id() + 51) {
                    }
                    return true;
                }
                r0 = zzcw();
            } else {
                r5.zzod = zza(r5.zzni, (java.lang.String) zzcw());
                if (zzde()) {
                    r5.zzoc = r5.zznh.next();
                }
                if (r5.zzoa == com.google.android.gms.internal.clearcut.zzcb.MESSAGE.id() || r5.zzoa == com.google.android.gms.internal.clearcut.zzcb.GROUP.id()) {
                    r0 = r5.zzod.getType();
                } else {
                    if (r5.zzoa != com.google.android.gms.internal.clearcut.zzcb.MESSAGE_LIST.id() && r5.zzoa != com.google.android.gms.internal.clearcut.zzcb.GROUP_LIST.id()) {
                        if (r5.zzoa != com.google.android.gms.internal.clearcut.zzcb.ENUM.id() && r5.zzoa != com.google.android.gms.internal.clearcut.zzcb.ENUM_LIST.id() && r5.zzoa != com.google.android.gms.internal.clearcut.zzcb.ENUM_LIST_PACKED.id()) {
                            if (r5.zzoa == com.google.android.gms.internal.clearcut.zzcb.MAP.id()) {
                                r5.zzog = zzcw();
                            }
                            return true;
                        }
                    }
                    r0 = zzcw();
                }
            }
            r5.zzoe = r0;
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int zzcx() {
        return this.zzny;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int zzcy() {
        return this.zzoa;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean zzda() {
        return this.zzoa > zzcb.MAP.id();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Field zzdb() {
        int i = this.zzob << 1;
        Object obj = this.zzmj[i];
        if (obj instanceof Field) {
            return (Field) obj;
        }
        Field zza = zza(this.zzni, (String) obj);
        this.zzmj[i] = zza;
        return zza;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Field zzdc() {
        int i = (this.zzob << 1) + 1;
        Object obj = this.zzmj[i];
        if (obj instanceof Field) {
            return (Field) obj;
        }
        Field zza = zza(this.zzni, (String) obj);
        this.zzmj[i] = zza;
        return zza;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Field zzdd() {
        return this.zzod;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean zzde() {
        return zzcz() && this.zzoa <= zzcb.GROUP.id();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Field zzdf() {
        int i = (this.zznk << 1) + (this.zzoc / 32);
        Object obj = this.zzmj[i];
        if (obj instanceof Field) {
            return (Field) obj;
        }
        Field zza = zza(this.zzni, (String) obj);
        this.zzmj[i] = zza;
        return zza;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int zzdg() {
        return this.zzoc % 32;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean zzdh() {
        return (this.zznz & 256) != 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean zzdi() {
        return (this.zznz & 512) != 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Object zzdj() {
        return this.zzoe;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Object zzdk() {
        return this.zzof;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Object zzdl() {
        return this.zzog;
    }
}
