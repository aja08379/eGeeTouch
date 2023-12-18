package com.google.android.gms.internal.p001firebaseauthapi;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaey  reason: invalid package */
/* loaded from: classes.dex */
public final class zzaey {
    private static final Class zza;
    private static final zzafn zzb;
    private static final zzafn zzc;
    private static final zzafn zzd;

    static {
        Class<?> cls;
        try {
            cls = Class.forName("com.google.protobuf.GeneratedMessage");
        } catch (Throwable unused) {
            cls = null;
        }
        zza = cls;
        zzb = zzab(false);
        zzc = zzab(true);
        zzd = new zzafp();
    }

    public static zzafn zzA() {
        return zzc;
    }

    public static zzafn zzB() {
        return zzd;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object zzC(Object obj, int i, List list, zzadj zzadjVar, Object obj2, zzafn zzafnVar) {
        if (zzadjVar == null) {
            return obj2;
        }
        if (list instanceof RandomAccess) {
            int size = list.size();
            int i2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                int intValue = ((Integer) list.get(i3)).intValue();
                if (zzadjVar.zza()) {
                    if (i3 != i2) {
                        list.set(i2, Integer.valueOf(intValue));
                    }
                    i2++;
                } else {
                    obj2 = zzD(obj, i, intValue, obj2, zzafnVar);
                }
            }
            if (i2 != size) {
                list.subList(i2, size).clear();
                return obj2;
            }
        } else {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                int intValue2 = ((Integer) it.next()).intValue();
                if (!zzadjVar.zza()) {
                    obj2 = zzD(obj, i, intValue2, obj2, zzafnVar);
                    it.remove();
                }
            }
        }
        return obj2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object zzD(Object obj, int i, int i2, Object obj2, zzafn zzafnVar) {
        if (obj2 == null) {
            obj2 = zzafnVar.zzc(obj);
        }
        zzafnVar.zzl(obj2, i, i2);
        return obj2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zzE(zzact zzactVar, Object obj, Object obj2) {
        zzactVar.zza(obj2);
        throw null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zzF(zzafn zzafnVar, Object obj, Object obj2) {
        zzafnVar.zzo(obj, zzafnVar.zze(zzafnVar.zzd(obj), zzafnVar.zzd(obj2)));
    }

    public static void zzG(Class cls) {
        Class cls2;
        if (!zzadf.class.isAssignableFrom(cls) && (cls2 = zza) != null && !cls2.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean zzH(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zzI(zzaef zzaefVar, Object obj, Object obj2, long j) {
        zzafx.zzs(obj, j, zzaef.zzc(zzafx.zzf(obj, j), zzafx.zzf(obj2, j)));
    }

    public static void zzJ(int i, List list, zzaco zzacoVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzacoVar.zzc(i, list, z);
    }

    public static void zzK(int i, List list, zzaco zzacoVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzacoVar.zze(i, list);
    }

    public static void zzL(int i, List list, zzaco zzacoVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzacoVar.zzg(i, list, z);
    }

    public static void zzM(int i, List list, zzaco zzacoVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzacoVar.zzj(i, list, z);
    }

    public static void zzN(int i, List list, zzaco zzacoVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzacoVar.zzl(i, list, z);
    }

    public static void zzO(int i, List list, zzaco zzacoVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzacoVar.zzn(i, list, z);
    }

    public static void zzP(int i, List list, zzaco zzacoVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzacoVar.zzp(i, list, z);
    }

    public static void zzQ(int i, List list, zzaco zzacoVar, zzaew zzaewVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            zzacoVar.zzq(i, list.get(i2), zzaewVar);
        }
    }

    public static void zzR(int i, List list, zzaco zzacoVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzacoVar.zzs(i, list, z);
    }

    public static void zzS(int i, List list, zzaco zzacoVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzacoVar.zzu(i, list, z);
    }

    public static void zzT(int i, List list, zzaco zzacoVar, zzaew zzaewVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            zzacoVar.zzv(i, list.get(i2), zzaewVar);
        }
    }

    public static void zzU(int i, List list, zzaco zzacoVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzacoVar.zzx(i, list, z);
    }

    public static void zzV(int i, List list, zzaco zzacoVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzacoVar.zzz(i, list, z);
    }

    public static void zzW(int i, List list, zzaco zzacoVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzacoVar.zzB(i, list, z);
    }

    public static void zzX(int i, List list, zzaco zzacoVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzacoVar.zzD(i, list, z);
    }

    public static void zzY(int i, List list, zzaco zzacoVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzacoVar.zzG(i, list);
    }

    public static void zzZ(int i, List list, zzaco zzacoVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzacoVar.zzI(i, list, z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zza(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * (zzacn.zzE(i << 3) + 1);
    }

    public static void zzaa(int i, List list, zzaco zzacoVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzacoVar.zzK(i, list, z);
    }

    private static zzafn zzab(boolean z) {
        Class<?> cls;
        try {
            cls = Class.forName("com.google.protobuf.UnknownFieldSetSchema");
        } catch (Throwable unused) {
            cls = null;
        }
        if (cls == null) {
            return null;
        }
        try {
            return (zzafn) cls.getConstructor(Boolean.TYPE).newInstance(Boolean.valueOf(z));
        } catch (Throwable unused2) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzb(List list) {
        return list.size();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzc(int i, List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzD = size * zzacn.zzD(i);
        for (int i2 = 0; i2 < list.size(); i2++) {
            zzD += zzacn.zzw((zzacc) list.get(i2));
        }
        return zzD;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzd(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zze(list) + (size * zzacn.zzD(i));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zze(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzadg) {
            zzadg zzadgVar = (zzadg) list;
            i = 0;
            while (i2 < size) {
                i += zzacn.zzy(zzadgVar.zze(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzacn.zzy(((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzf(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * (zzacn.zzE(i << 3) + 4);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzg(List list) {
        return list.size() * 4;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzh(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * (zzacn.zzE(i << 3) + 8);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzi(List list) {
        return list.size() * 8;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzj(int i, List list, zzaew zzaewVar) {
        int size = list.size();
        if (size != 0) {
            int i2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                i2 += zzacn.zzx(i, (zzaek) list.get(i3), zzaewVar);
            }
            return i2;
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzk(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzl(list) + (size * zzacn.zzD(i));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzl(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzadg) {
            zzadg zzadgVar = (zzadg) list;
            i = 0;
            while (i2 < size) {
                i += zzacn.zzy(zzadgVar.zze(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzacn.zzy(((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzm(int i, List list, boolean z) {
        if (list.size() == 0) {
            return 0;
        }
        return zzn(list) + (list.size() * zzacn.zzD(i));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzn(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzadz) {
            zzadz zzadzVar = (zzadz) list;
            i = 0;
            while (i2 < size) {
                i += zzacn.zzF(zzadzVar.zze(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzacn.zzF(((Long) list.get(i2)).longValue());
                i2++;
            }
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzo(int i, Object obj, zzaew zzaewVar) {
        if (obj instanceof zzadq) {
            int zzE = zzacn.zzE(i << 3);
            int zza2 = ((zzadq) obj).zza();
            return zzE + zzacn.zzE(zza2) + zza2;
        }
        return zzacn.zzE(i << 3) + zzacn.zzA((zzaek) obj, zzaewVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzp(int i, List list, zzaew zzaewVar) {
        int zzA;
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzD = zzacn.zzD(i) * size;
        for (int i2 = 0; i2 < size; i2++) {
            Object obj = list.get(i2);
            if (obj instanceof zzadq) {
                zzA = zzacn.zzz((zzadq) obj);
            } else {
                zzA = zzacn.zzA((zzaek) obj, zzaewVar);
            }
            zzD += zzA;
        }
        return zzD;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzq(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzr(list) + (size * zzacn.zzD(i));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzr(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzadg) {
            zzadg zzadgVar = (zzadg) list;
            i = 0;
            while (i2 < size) {
                int zze = zzadgVar.zze(i2);
                i += zzacn.zzE((zze >> 31) ^ (zze + zze));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                int intValue = ((Integer) list.get(i2)).intValue();
                i += zzacn.zzE((intValue >> 31) ^ (intValue + intValue));
                i2++;
            }
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzs(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzt(list) + (size * zzacn.zzD(i));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzt(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzadz) {
            zzadz zzadzVar = (zzadz) list;
            i = 0;
            while (i2 < size) {
                long zze = zzadzVar.zze(i2);
                i += zzacn.zzF((zze >> 63) ^ (zze + zze));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                long longValue = ((Long) list.get(i2)).longValue();
                i += zzacn.zzF((longValue >> 63) ^ (longValue + longValue));
                i2++;
            }
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzu(int i, List list) {
        int zzC;
        int zzC2;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        int zzD = zzacn.zzD(i) * size;
        if (list instanceof zzads) {
            zzads zzadsVar = (zzads) list;
            while (i2 < size) {
                Object zzf = zzadsVar.zzf(i2);
                if (zzf instanceof zzacc) {
                    zzC2 = zzacn.zzw((zzacc) zzf);
                } else {
                    zzC2 = zzacn.zzC((String) zzf);
                }
                zzD += zzC2;
                i2++;
            }
        } else {
            while (i2 < size) {
                Object obj = list.get(i2);
                if (obj instanceof zzacc) {
                    zzC = zzacn.zzw((zzacc) obj);
                } else {
                    zzC = zzacn.zzC((String) obj);
                }
                zzD += zzC;
                i2++;
            }
        }
        return zzD;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzv(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzw(list) + (size * zzacn.zzD(i));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzw(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzadg) {
            zzadg zzadgVar = (zzadg) list;
            i = 0;
            while (i2 < size) {
                i += zzacn.zzE(zzadgVar.zze(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzacn.zzE(((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzx(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzy(list) + (size * zzacn.zzD(i));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzy(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzadz) {
            zzadz zzadzVar = (zzadz) list;
            i = 0;
            while (i2 < size) {
                i += zzacn.zzF(zzadzVar.zze(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzacn.zzF(((Long) list.get(i2)).longValue());
                i2++;
            }
        }
        return i;
    }

    public static zzafn zzz() {
        return zzb;
    }
}
