package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import sun.misc.Unsafe;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement-base@@21.2.0 */
/* loaded from: classes.dex */
public final class zzlp<T> implements zzlx<T> {
    private static final int[] zza = new int[0];
    private static final Unsafe zzb = zzmy.zzg();
    private final int[] zzc;
    private final Object[] zzd;
    private final int zze;
    private final int zzf;
    private final zzlm zzg;
    private final boolean zzh;
    private final boolean zzi;
    private final int[] zzj;
    private final int zzk;
    private final int zzl;
    private final zzla zzm;
    private final zzmo zzn;
    private final zzjs zzo;
    private final zzlr zzp;
    private final zzlh zzq;

    private zzlp(int[] iArr, Object[] objArr, int i, int i2, zzlm zzlmVar, boolean z, boolean z2, int[] iArr2, int i3, int i4, zzlr zzlrVar, zzla zzlaVar, zzmo zzmoVar, zzjs zzjsVar, zzlh zzlhVar, byte[] bArr) {
        this.zzc = iArr;
        this.zzd = objArr;
        this.zze = i;
        this.zzf = i2;
        this.zzi = z;
        boolean z3 = false;
        if (zzjsVar != null && zzjsVar.zzc(zzlmVar)) {
            z3 = true;
        }
        this.zzh = z3;
        this.zzj = iArr2;
        this.zzk = i3;
        this.zzl = i4;
        this.zzp = zzlrVar;
        this.zzm = zzlaVar;
        this.zzn = zzmoVar;
        this.zzo = zzjsVar;
        this.zzg = zzlmVar;
        this.zzq = zzlhVar;
    }

    private static int zzA(int i) {
        return (i >>> 20) & 255;
    }

    private final int zzB(int i) {
        return this.zzc[i + 1];
    }

    private static long zzC(Object obj, long j) {
        return ((Long) zzmy.zzf(obj, j)).longValue();
    }

    private final zzkj zzD(int i) {
        int i2 = i / 3;
        return (zzkj) this.zzd[i2 + i2 + 1];
    }

    private final zzlx zzE(int i) {
        int i2 = i / 3;
        int i3 = i2 + i2;
        zzlx zzlxVar = (zzlx) this.zzd[i3];
        if (zzlxVar != null) {
            return zzlxVar;
        }
        zzlx zzb2 = zzlu.zza().zzb((Class) this.zzd[i3 + 1]);
        this.zzd[i3] = zzb2;
        return zzb2;
    }

    private final Object zzF(int i) {
        int i2 = i / 3;
        return this.zzd[i2 + i2];
    }

    private final Object zzG(Object obj, int i) {
        zzlx zzE = zzE(i);
        long zzB = zzB(i) & 1048575;
        if (!zzT(obj, i)) {
            return zzE.zze();
        }
        Object object = zzb.getObject(obj, zzB);
        if (zzW(object)) {
            return object;
        }
        Object zze = zzE.zze();
        if (object != null) {
            zzE.zzg(zze, object);
        }
        return zze;
    }

    private final Object zzH(Object obj, int i, int i2) {
        zzlx zzE = zzE(i2);
        if (!zzX(obj, i, i2)) {
            return zzE.zze();
        }
        Object object = zzb.getObject(obj, zzB(i2) & 1048575);
        if (zzW(object)) {
            return object;
        }
        Object zze = zzE.zze();
        if (object != null) {
            zzE.zzg(zze, object);
        }
        return zze;
    }

    private static Field zzI(Class cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException unused) {
            Field[] declaredFields = cls.getDeclaredFields();
            for (Field field : declaredFields) {
                if (str.equals(field.getName())) {
                    return field;
                }
            }
            throw new RuntimeException("Field " + str + " for " + cls.getName() + " not found. Known fields are " + Arrays.toString(declaredFields));
        }
    }

    private static void zzJ(Object obj) {
        if (!zzW(obj)) {
            throw new IllegalArgumentException("Mutating immutable message: ".concat(String.valueOf(String.valueOf(obj))));
        }
    }

    private final void zzK(Object obj, Object obj2, int i) {
        if (zzT(obj2, i)) {
            long zzB = zzB(i) & 1048575;
            Unsafe unsafe = zzb;
            Object object = unsafe.getObject(obj2, zzB);
            if (object == null) {
                throw new IllegalStateException("Source subfield " + this.zzc[i] + " is present but null: " + obj2.toString());
            }
            zzlx zzE = zzE(i);
            if (!zzT(obj, i)) {
                if (!zzW(object)) {
                    unsafe.putObject(obj, zzB, object);
                } else {
                    Object zze = zzE.zze();
                    zzE.zzg(zze, object);
                    unsafe.putObject(obj, zzB, zze);
                }
                zzM(obj, i);
                return;
            }
            Object object2 = unsafe.getObject(obj, zzB);
            if (!zzW(object2)) {
                Object zze2 = zzE.zze();
                zzE.zzg(zze2, object2);
                unsafe.putObject(obj, zzB, zze2);
                object2 = zze2;
            }
            zzE.zzg(object2, object);
        }
    }

    private final void zzL(Object obj, Object obj2, int i) {
        int i2 = this.zzc[i];
        if (zzX(obj2, i2, i)) {
            long zzB = zzB(i) & 1048575;
            Unsafe unsafe = zzb;
            Object object = unsafe.getObject(obj2, zzB);
            if (object == null) {
                throw new IllegalStateException("Source subfield " + this.zzc[i] + " is present but null: " + obj2.toString());
            }
            zzlx zzE = zzE(i);
            if (!zzX(obj, i2, i)) {
                if (!zzW(object)) {
                    unsafe.putObject(obj, zzB, object);
                } else {
                    Object zze = zzE.zze();
                    zzE.zzg(zze, object);
                    unsafe.putObject(obj, zzB, zze);
                }
                zzN(obj, i2, i);
                return;
            }
            Object object2 = unsafe.getObject(obj, zzB);
            if (!zzW(object2)) {
                Object zze2 = zzE.zze();
                zzE.zzg(zze2, object2);
                unsafe.putObject(obj, zzB, zze2);
                object2 = zze2;
            }
            zzE.zzg(object2, object);
        }
    }

    private final void zzM(Object obj, int i) {
        int zzy = zzy(i);
        long j = 1048575 & zzy;
        if (j == 1048575) {
            return;
        }
        zzmy.zzq(obj, j, (1 << (zzy >>> 20)) | zzmy.zzc(obj, j));
    }

    private final void zzN(Object obj, int i, int i2) {
        zzmy.zzq(obj, zzy(i2) & 1048575, i);
    }

    private final void zzO(Object obj, int i, Object obj2) {
        zzb.putObject(obj, zzB(i) & 1048575, obj2);
        zzM(obj, i);
    }

    private final void zzP(Object obj, int i, int i2, Object obj2) {
        zzb.putObject(obj, zzB(i2) & 1048575, obj2);
        zzN(obj, i, i2);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private final void zzQ(Object obj, zzng zzngVar) throws IOException {
        int i;
        boolean z;
        if (this.zzh) {
            this.zzo.zza(obj);
            throw null;
        }
        int length = this.zzc.length;
        Unsafe unsafe = zzb;
        int i2 = 1048575;
        int i3 = 1048575;
        int i4 = 0;
        int i5 = 0;
        while (i4 < length) {
            int zzB = zzB(i4);
            int[] iArr = this.zzc;
            int i6 = iArr[i4];
            int zzA = zzA(zzB);
            if (zzA <= 17) {
                int i7 = iArr[i4 + 2];
                int i8 = i7 & i2;
                if (i8 != i3) {
                    i5 = unsafe.getInt(obj, i8);
                    i3 = i8;
                }
                i = 1 << (i7 >>> 20);
            } else {
                i = 0;
            }
            long j = zzB & i2;
            switch (zzA) {
                case 0:
                    if ((i5 & i) == 0) {
                        break;
                    } else {
                        zzngVar.zzf(i6, zzmy.zza(obj, j));
                        break;
                    }
                case 1:
                    if ((i5 & i) == 0) {
                        break;
                    } else {
                        zzngVar.zzo(i6, zzmy.zzb(obj, j));
                        break;
                    }
                case 2:
                    if ((i5 & i) == 0) {
                        break;
                    } else {
                        zzngVar.zzt(i6, unsafe.getLong(obj, j));
                        break;
                    }
                case 3:
                    if ((i5 & i) == 0) {
                        break;
                    } else {
                        zzngVar.zzJ(i6, unsafe.getLong(obj, j));
                        break;
                    }
                case 4:
                    if ((i5 & i) == 0) {
                        break;
                    } else {
                        zzngVar.zzr(i6, unsafe.getInt(obj, j));
                        break;
                    }
                case 5:
                    if ((i5 & i) == 0) {
                        break;
                    } else {
                        zzngVar.zzm(i6, unsafe.getLong(obj, j));
                        break;
                    }
                case 6:
                    if ((i5 & i) == 0) {
                        break;
                    } else {
                        zzngVar.zzk(i6, unsafe.getInt(obj, j));
                        break;
                    }
                case 7:
                    if ((i5 & i) == 0) {
                        break;
                    } else {
                        zzngVar.zzb(i6, zzmy.zzw(obj, j));
                        break;
                    }
                case 8:
                    if ((i5 & i) == 0) {
                        break;
                    } else {
                        zzZ(i6, unsafe.getObject(obj, j), zzngVar);
                        break;
                    }
                case 9:
                    if ((i5 & i) == 0) {
                        break;
                    } else {
                        zzngVar.zzv(i6, unsafe.getObject(obj, j), zzE(i4));
                        break;
                    }
                case 10:
                    if ((i5 & i) == 0) {
                        break;
                    } else {
                        zzngVar.zzd(i6, (zzje) unsafe.getObject(obj, j));
                        break;
                    }
                case 11:
                    if ((i5 & i) == 0) {
                        break;
                    } else {
                        zzngVar.zzH(i6, unsafe.getInt(obj, j));
                        break;
                    }
                case 12:
                    if ((i5 & i) == 0) {
                        break;
                    } else {
                        zzngVar.zzi(i6, unsafe.getInt(obj, j));
                        break;
                    }
                case 13:
                    if ((i5 & i) == 0) {
                        break;
                    } else {
                        zzngVar.zzw(i6, unsafe.getInt(obj, j));
                        break;
                    }
                case 14:
                    if ((i5 & i) == 0) {
                        break;
                    } else {
                        zzngVar.zzy(i6, unsafe.getLong(obj, j));
                        break;
                    }
                case 15:
                    if ((i5 & i) == 0) {
                        break;
                    } else {
                        zzngVar.zzA(i6, unsafe.getInt(obj, j));
                        break;
                    }
                case 16:
                    if ((i5 & i) == 0) {
                        break;
                    } else {
                        zzngVar.zzC(i6, unsafe.getLong(obj, j));
                        break;
                    }
                case 17:
                    if ((i5 & i) == 0) {
                        break;
                    } else {
                        zzngVar.zzq(i6, unsafe.getObject(obj, j), zzE(i4));
                        break;
                    }
                case 18:
                    zzlz.zzJ(this.zzc[i4], (List) unsafe.getObject(obj, j), zzngVar, false);
                    break;
                case 19:
                    zzlz.zzN(this.zzc[i4], (List) unsafe.getObject(obj, j), zzngVar, false);
                    break;
                case 20:
                    zzlz.zzQ(this.zzc[i4], (List) unsafe.getObject(obj, j), zzngVar, false);
                    break;
                case 21:
                    zzlz.zzY(this.zzc[i4], (List) unsafe.getObject(obj, j), zzngVar, false);
                    break;
                case 22:
                    zzlz.zzP(this.zzc[i4], (List) unsafe.getObject(obj, j), zzngVar, false);
                    break;
                case 23:
                    zzlz.zzM(this.zzc[i4], (List) unsafe.getObject(obj, j), zzngVar, false);
                    break;
                case 24:
                    zzlz.zzL(this.zzc[i4], (List) unsafe.getObject(obj, j), zzngVar, false);
                    break;
                case 25:
                    zzlz.zzH(this.zzc[i4], (List) unsafe.getObject(obj, j), zzngVar, false);
                    break;
                case 26:
                    zzlz.zzW(this.zzc[i4], (List) unsafe.getObject(obj, j), zzngVar);
                    break;
                case 27:
                    zzlz.zzR(this.zzc[i4], (List) unsafe.getObject(obj, j), zzngVar, zzE(i4));
                    break;
                case 28:
                    zzlz.zzI(this.zzc[i4], (List) unsafe.getObject(obj, j), zzngVar);
                    break;
                case 29:
                    z = false;
                    zzlz.zzX(this.zzc[i4], (List) unsafe.getObject(obj, j), zzngVar, false);
                    break;
                case 30:
                    z = false;
                    zzlz.zzK(this.zzc[i4], (List) unsafe.getObject(obj, j), zzngVar, false);
                    break;
                case 31:
                    z = false;
                    zzlz.zzS(this.zzc[i4], (List) unsafe.getObject(obj, j), zzngVar, false);
                    break;
                case 32:
                    z = false;
                    zzlz.zzT(this.zzc[i4], (List) unsafe.getObject(obj, j), zzngVar, false);
                    break;
                case 33:
                    z = false;
                    zzlz.zzU(this.zzc[i4], (List) unsafe.getObject(obj, j), zzngVar, false);
                    break;
                case 34:
                    z = false;
                    zzlz.zzV(this.zzc[i4], (List) unsafe.getObject(obj, j), zzngVar, false);
                    break;
                case 35:
                    zzlz.zzJ(this.zzc[i4], (List) unsafe.getObject(obj, j), zzngVar, true);
                    break;
                case 36:
                    zzlz.zzN(this.zzc[i4], (List) unsafe.getObject(obj, j), zzngVar, true);
                    break;
                case 37:
                    zzlz.zzQ(this.zzc[i4], (List) unsafe.getObject(obj, j), zzngVar, true);
                    break;
                case 38:
                    zzlz.zzY(this.zzc[i4], (List) unsafe.getObject(obj, j), zzngVar, true);
                    break;
                case 39:
                    zzlz.zzP(this.zzc[i4], (List) unsafe.getObject(obj, j), zzngVar, true);
                    break;
                case 40:
                    zzlz.zzM(this.zzc[i4], (List) unsafe.getObject(obj, j), zzngVar, true);
                    break;
                case 41:
                    zzlz.zzL(this.zzc[i4], (List) unsafe.getObject(obj, j), zzngVar, true);
                    break;
                case 42:
                    zzlz.zzH(this.zzc[i4], (List) unsafe.getObject(obj, j), zzngVar, true);
                    break;
                case 43:
                    zzlz.zzX(this.zzc[i4], (List) unsafe.getObject(obj, j), zzngVar, true);
                    break;
                case 44:
                    zzlz.zzK(this.zzc[i4], (List) unsafe.getObject(obj, j), zzngVar, true);
                    break;
                case 45:
                    zzlz.zzS(this.zzc[i4], (List) unsafe.getObject(obj, j), zzngVar, true);
                    break;
                case 46:
                    zzlz.zzT(this.zzc[i4], (List) unsafe.getObject(obj, j), zzngVar, true);
                    break;
                case 47:
                    zzlz.zzU(this.zzc[i4], (List) unsafe.getObject(obj, j), zzngVar, true);
                    break;
                case 48:
                    zzlz.zzV(this.zzc[i4], (List) unsafe.getObject(obj, j), zzngVar, true);
                    break;
                case 49:
                    zzlz.zzO(this.zzc[i4], (List) unsafe.getObject(obj, j), zzngVar, zzE(i4));
                    break;
                case 50:
                    zzR(zzngVar, i6, unsafe.getObject(obj, j), i4);
                    break;
                case 51:
                    if (zzX(obj, i6, i4)) {
                        zzngVar.zzf(i6, zzn(obj, j));
                    }
                    break;
                case 52:
                    if (zzX(obj, i6, i4)) {
                        zzngVar.zzo(i6, zzo(obj, j));
                    }
                    break;
                case 53:
                    if (zzX(obj, i6, i4)) {
                        zzngVar.zzt(i6, zzC(obj, j));
                    }
                    break;
                case 54:
                    if (zzX(obj, i6, i4)) {
                        zzngVar.zzJ(i6, zzC(obj, j));
                    }
                    break;
                case 55:
                    if (zzX(obj, i6, i4)) {
                        zzngVar.zzr(i6, zzr(obj, j));
                    }
                    break;
                case 56:
                    if (zzX(obj, i6, i4)) {
                        zzngVar.zzm(i6, zzC(obj, j));
                    }
                    break;
                case 57:
                    if (zzX(obj, i6, i4)) {
                        zzngVar.zzk(i6, zzr(obj, j));
                    }
                    break;
                case 58:
                    if (zzX(obj, i6, i4)) {
                        zzngVar.zzb(i6, zzY(obj, j));
                    }
                    break;
                case 59:
                    if (zzX(obj, i6, i4)) {
                        zzZ(i6, unsafe.getObject(obj, j), zzngVar);
                    }
                    break;
                case 60:
                    if (zzX(obj, i6, i4)) {
                        zzngVar.zzv(i6, unsafe.getObject(obj, j), zzE(i4));
                    }
                    break;
                case 61:
                    if (zzX(obj, i6, i4)) {
                        zzngVar.zzd(i6, (zzje) unsafe.getObject(obj, j));
                    }
                    break;
                case 62:
                    if (zzX(obj, i6, i4)) {
                        zzngVar.zzH(i6, zzr(obj, j));
                    }
                    break;
                case 63:
                    if (zzX(obj, i6, i4)) {
                        zzngVar.zzi(i6, zzr(obj, j));
                    }
                    break;
                case 64:
                    if (zzX(obj, i6, i4)) {
                        zzngVar.zzw(i6, zzr(obj, j));
                    }
                    break;
                case 65:
                    if (zzX(obj, i6, i4)) {
                        zzngVar.zzy(i6, zzC(obj, j));
                    }
                    break;
                case 66:
                    if (zzX(obj, i6, i4)) {
                        zzngVar.zzA(i6, zzr(obj, j));
                    }
                    break;
                case 67:
                    if (zzX(obj, i6, i4)) {
                        zzngVar.zzC(i6, zzC(obj, j));
                    }
                    break;
                case 68:
                    if (zzX(obj, i6, i4)) {
                        zzngVar.zzq(i6, unsafe.getObject(obj, j), zzE(i4));
                    }
                    break;
            }
            i4 += 3;
            i2 = 1048575;
        }
        zzmo zzmoVar = this.zzn;
        zzmoVar.zzi(zzmoVar.zzd(obj), zzngVar);
    }

    private final void zzR(zzng zzngVar, int i, Object obj, int i2) throws IOException {
        if (obj == null) {
            return;
        }
        zzlf zzlfVar = (zzlf) zzF(i2);
        throw null;
    }

    private final boolean zzS(Object obj, Object obj2, int i) {
        return zzT(obj, i) == zzT(obj2, i);
    }

    private final boolean zzT(Object obj, int i) {
        int zzy = zzy(i);
        long j = zzy & 1048575;
        if (j != 1048575) {
            return (zzmy.zzc(obj, j) & (1 << (zzy >>> 20))) != 0;
        }
        int zzB = zzB(i);
        long j2 = zzB & 1048575;
        switch (zzA(zzB)) {
            case 0:
                return Double.doubleToRawLongBits(zzmy.zza(obj, j2)) != 0;
            case 1:
                return Float.floatToRawIntBits(zzmy.zzb(obj, j2)) != 0;
            case 2:
                return zzmy.zzd(obj, j2) != 0;
            case 3:
                return zzmy.zzd(obj, j2) != 0;
            case 4:
                return zzmy.zzc(obj, j2) != 0;
            case 5:
                return zzmy.zzd(obj, j2) != 0;
            case 6:
                return zzmy.zzc(obj, j2) != 0;
            case 7:
                return zzmy.zzw(obj, j2);
            case 8:
                Object zzf = zzmy.zzf(obj, j2);
                if (zzf instanceof String) {
                    return !((String) zzf).isEmpty();
                } else if (zzf instanceof zzje) {
                    return !zzje.zzb.equals(zzf);
                } else {
                    throw new IllegalArgumentException();
                }
            case 9:
                return zzmy.zzf(obj, j2) != null;
            case 10:
                return !zzje.zzb.equals(zzmy.zzf(obj, j2));
            case 11:
                return zzmy.zzc(obj, j2) != 0;
            case 12:
                return zzmy.zzc(obj, j2) != 0;
            case 13:
                return zzmy.zzc(obj, j2) != 0;
            case 14:
                return zzmy.zzd(obj, j2) != 0;
            case 15:
                return zzmy.zzc(obj, j2) != 0;
            case 16:
                return zzmy.zzd(obj, j2) != 0;
            case 17:
                return zzmy.zzf(obj, j2) != null;
            default:
                throw new IllegalArgumentException();
        }
    }

    private final boolean zzU(Object obj, int i, int i2, int i3, int i4) {
        if (i2 == 1048575) {
            return zzT(obj, i);
        }
        return (i3 & i4) != 0;
    }

    private static boolean zzV(Object obj, int i, zzlx zzlxVar) {
        return zzlxVar.zzk(zzmy.zzf(obj, i & 1048575));
    }

    private static boolean zzW(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof zzkf) {
            return ((zzkf) obj).zzbO();
        }
        return true;
    }

    private final boolean zzX(Object obj, int i, int i2) {
        return zzmy.zzc(obj, (long) (zzy(i2) & 1048575)) == i;
    }

    private static boolean zzY(Object obj, long j) {
        return ((Boolean) zzmy.zzf(obj, j)).booleanValue();
    }

    private static final void zzZ(int i, Object obj, zzng zzngVar) throws IOException {
        if (obj instanceof String) {
            zzngVar.zzF(i, (String) obj);
        } else {
            zzngVar.zzd(i, (zzje) obj);
        }
    }

    static zzmp zzd(Object obj) {
        zzkf zzkfVar = (zzkf) obj;
        zzmp zzmpVar = zzkfVar.zzc;
        if (zzmpVar == zzmp.zzc()) {
            zzmp zzf = zzmp.zzf();
            zzkfVar.zzc = zzf;
            return zzf;
        }
        return zzmpVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzlp zzl(Class cls, zzlj zzljVar, zzlr zzlrVar, zzla zzlaVar, zzmo zzmoVar, zzjs zzjsVar, zzlh zzlhVar) {
        if (zzljVar instanceof zzlw) {
            return zzm((zzlw) zzljVar, zzlrVar, zzlaVar, zzmoVar, zzjsVar, zzlhVar);
        }
        zzml zzmlVar = (zzml) zzljVar;
        throw null;
    }

    /* JADX WARN: Removed duplicated region for block: B:123:0x025e  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x0261  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x0279  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x027c  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x032c  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x0385  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static com.google.android.gms.internal.measurement.zzlp zzm(com.google.android.gms.internal.measurement.zzlw r34, com.google.android.gms.internal.measurement.zzlr r35, com.google.android.gms.internal.measurement.zzla r36, com.google.android.gms.internal.measurement.zzmo r37, com.google.android.gms.internal.measurement.zzjs r38, com.google.android.gms.internal.measurement.zzlh r39) {
        int r6;
        int r8;
        int r11;
        int r14;
        int[] r13;
        int r16;
        int r6;
        int r12;
        int r9;
        int r17;
        char r15;
        int r16;
        char r14;
        int r15;
        char r13;
        int r14;
        char r12;
        int r13;
        char r11;
        int r12;
        char r9;
        int r11;
        char r8;
        int r9;
        char r6;
        int r2;
        int r28;
        int r3;
        int[] r31;
        int r14;
        int r32;
        int r12;
        int r7;
        java.lang.Object[] r29;
        java.lang.String r30;
        java.lang.Class<?> r8;
        int r0;
        int r27;
        int r3;
        java.lang.reflect.Field r11;
        char r8;
        int r27;
        int r14;
        int r14;
        java.lang.Object r12;
        java.lang.reflect.Field r12;
        java.lang.Object r8;
        java.lang.reflect.Field r8;
        int r31;
        char r14;
        int r27;
        char r5;
        int r26;
        char r2;
        int r9;
        char r4;
        boolean r10 = r34.zzc() == 2;
        java.lang.String r0 = r34.zzd();
        int r3 = r0.length();
        char r5 = 55296;
        if (r0.charAt(0) >= 55296) {
            int r4 = 1;
            while (true) {
                r6 = r4 + 1;
                if (r0.charAt(r4) < 55296) {
                    break;
                }
                r4 = r6;
            }
        } else {
            r6 = 1;
        }
        int r4 = r6 + 1;
        int r6 = r0.charAt(r6);
        if (r6 >= 55296) {
            int r6 = r6 & 8191;
            int r8 = 13;
            while (true) {
                r9 = r4 + 1;
                r4 = r0.charAt(r4);
                if (r4 < 55296) {
                    break;
                }
                r6 |= (r4 & 8191) << r8;
                r8 += 13;
                r4 = r9;
            }
            r6 = r6 | (r4 << r8);
            r4 = r9;
        }
        if (r6 == 0) {
            r8 = 0;
            r9 = 0;
            r11 = 0;
            r12 = 0;
            r14 = 0;
            r16 = 0;
            r13 = com.google.android.gms.internal.measurement.zzlp.zza;
            r6 = 0;
        } else {
            int r6 = r4 + 1;
            int r4 = r0.charAt(r4);
            if (r4 >= 55296) {
                int r4 = r4 & 8191;
                int r8 = 13;
                while (true) {
                    r9 = r6 + 1;
                    r6 = r0.charAt(r6);
                    if (r6 < 55296) {
                        break;
                    }
                    r4 |= (r6 & 8191) << r8;
                    r8 += 13;
                    r6 = r9;
                }
                r4 = r4 | (r6 << r8);
                r6 = r9;
            }
            int r8 = r6 + 1;
            int r6 = r0.charAt(r6);
            if (r6 >= 55296) {
                int r6 = r6 & 8191;
                int r9 = 13;
                while (true) {
                    r11 = r8 + 1;
                    r8 = r0.charAt(r8);
                    if (r8 < 55296) {
                        break;
                    }
                    r6 |= (r8 & 8191) << r9;
                    r9 += 13;
                    r8 = r11;
                }
                r6 = r6 | (r8 << r9);
                r8 = r11;
            }
            int r9 = r8 + 1;
            r8 = r0.charAt(r8);
            if (r8 >= 55296) {
                int r8 = r8 & 8191;
                int r11 = 13;
                while (true) {
                    r12 = r9 + 1;
                    r9 = r0.charAt(r9);
                    if (r9 < 55296) {
                        break;
                    }
                    r8 |= (r9 & 8191) << r11;
                    r11 += 13;
                    r9 = r12;
                }
                r8 = r8 | (r9 << r11);
                r9 = r12;
            }
            int r11 = r9 + 1;
            int r9 = r0.charAt(r9);
            if (r9 >= 55296) {
                int r9 = r9 & 8191;
                int r12 = 13;
                while (true) {
                    r13 = r11 + 1;
                    r11 = r0.charAt(r11);
                    if (r11 < 55296) {
                        break;
                    }
                    r9 |= (r11 & 8191) << r12;
                    r12 += 13;
                    r11 = r13;
                }
                r9 = r9 | (r11 << r12);
                r11 = r13;
            }
            int r12 = r11 + 1;
            r11 = r0.charAt(r11);
            if (r11 >= 55296) {
                int r11 = r11 & 8191;
                int r13 = 13;
                while (true) {
                    r14 = r12 + 1;
                    r12 = r0.charAt(r12);
                    if (r12 < 55296) {
                        break;
                    }
                    r11 |= (r12 & 8191) << r13;
                    r13 += 13;
                    r12 = r14;
                }
                r11 = r11 | (r12 << r13);
                r12 = r14;
            }
            int r13 = r12 + 1;
            int r12 = r0.charAt(r12);
            if (r12 >= 55296) {
                int r12 = r12 & 8191;
                int r14 = 13;
                while (true) {
                    r15 = r13 + 1;
                    r13 = r0.charAt(r13);
                    if (r13 < 55296) {
                        break;
                    }
                    r12 |= (r13 & 8191) << r14;
                    r14 += 13;
                    r13 = r15;
                }
                r12 = r12 | (r13 << r14);
                r13 = r15;
            }
            int r14 = r13 + 1;
            int r13 = r0.charAt(r13);
            if (r13 >= 55296) {
                int r13 = r13 & 8191;
                int r15 = 13;
                while (true) {
                    r16 = r14 + 1;
                    r14 = r0.charAt(r14);
                    if (r14 < 55296) {
                        break;
                    }
                    r13 |= (r14 & 8191) << r15;
                    r15 += 13;
                    r14 = r16;
                }
                r13 = r13 | (r14 << r15);
                r14 = r16;
            }
            int r15 = r14 + 1;
            r14 = r0.charAt(r14);
            if (r14 >= 55296) {
                int r14 = r14 & 8191;
                int r16 = 13;
                while (true) {
                    r17 = r15 + 1;
                    r15 = r0.charAt(r15);
                    if (r15 < 55296) {
                        break;
                    }
                    r14 |= (r15 & 8191) << r16;
                    r16 += 13;
                    r15 = r17;
                }
                r14 = r14 | (r15 << r16);
                r15 = r17;
            }
            r13 = new int[r14 + r12 + r13];
            r16 = r4 + r4 + r6;
            r6 = r4;
            r4 = r15;
            int r33 = r12;
            r12 = r9;
            r9 = r33;
        }
        sun.misc.Unsafe r15 = com.google.android.gms.internal.measurement.zzlp.zzb;
        java.lang.Object[] r17 = r34.zze();
        java.lang.Class<?> r1 = r34.zza().getClass();
        int[] r7 = new int[r11 * 3];
        java.lang.Object[] r11 = new java.lang.Object[r11 + r11];
        int r21 = r14 + r9;
        int r22 = r14;
        int r23 = r21;
        int r9 = 0;
        int r20 = 0;
        while (r4 < r3) {
            int r24 = r4 + 1;
            int r4 = r0.charAt(r4);
            if (r4 >= r5) {
                int r4 = r4 & 8191;
                int r2 = r24;
                int r24 = 13;
                while (true) {
                    r26 = r2 + 1;
                    r2 = r0.charAt(r2);
                    if (r2 < r5) {
                        break;
                    }
                    r4 |= (r2 & 8191) << r24;
                    r24 += 13;
                    r2 = r26;
                }
                r4 = r4 | (r2 << r24);
                r2 = r26;
            } else {
                r2 = r24;
            }
            int r24 = r2 + 1;
            int r2 = r0.charAt(r2);
            if (r2 >= r5) {
                int r2 = r2 & 8191;
                int r5 = r24;
                int r24 = 13;
                while (true) {
                    r27 = r5 + 1;
                    r5 = r0.charAt(r5);
                    r28 = r3;
                    if (r5 < 55296) {
                        break;
                    }
                    r2 |= (r5 & 8191) << r24;
                    r24 += 13;
                    r5 = r27;
                    r3 = r28;
                }
                r2 = r2 | (r5 << r24);
                r3 = r27;
            } else {
                r28 = r3;
                r3 = r24;
            }
            int r5 = r2 & 255;
            int r24 = r14;
            if ((r2 & 1024) != 0) {
                r13[r20] = r9;
                r20++;
            }
            if (r5 >= 51) {
                int r14 = r3 + 1;
                int r3 = r0.charAt(r3);
                if (r3 >= 55296) {
                    int r3 = r3 & 8191;
                    int r14 = r14;
                    int r27 = 13;
                    while (true) {
                        r31 = r14 + 1;
                        r14 = r0.charAt(r14);
                        r32 = r12;
                        if (r14 < 55296) {
                            break;
                        }
                        r3 |= (r14 & 8191) << r27;
                        r27 += 13;
                        r14 = r31;
                        r12 = r32;
                    }
                    r3 = r3 | (r14 << r27);
                    r14 = r31;
                } else {
                    r32 = r12;
                    r14 = r14;
                }
                int r12 = r5 - 51;
                r27 = r14;
                if (r12 == 9 || r12 == 17) {
                    int r12 = r9 / 3;
                    r14 = r16 + 1;
                    r11[r12 + r12 + 1] = r17[r16];
                } else {
                    if (r12 == 12 && !r10) {
                        int r12 = r9 / 3;
                        r14 = r16 + 1;
                        r11[r12 + r12 + 1] = r17[r16];
                    }
                    int r3 = r3 + r3;
                    r12 = r17[r3];
                    if (!(r12 instanceof java.lang.reflect.Field)) {
                        r12 = (java.lang.reflect.Field) r12;
                    } else {
                        r12 = zzI(r1, (java.lang.String) r12);
                        r17[r3] = r12;
                    }
                    r31 = r7;
                    r14 = r8;
                    int r7 = (int) r15.objectFieldOffset(r12);
                    int r3 = r3 + 1;
                    r8 = r17[r3];
                    if (!(r8 instanceof java.lang.reflect.Field)) {
                        r8 = (java.lang.reflect.Field) r8;
                    } else {
                        r8 = zzI(r1, (java.lang.String) r8);
                        r17[r3] = r8;
                    }
                    r30 = r0;
                    r8 = r1;
                    r0 = (int) r15.objectFieldOffset(r8);
                    r29 = r11;
                    r7 = r7;
                    r3 = 0;
                }
                r16 = r14;
                int r3 = r3 + r3;
                r12 = r17[r3];
                if (!(r12 instanceof java.lang.reflect.Field)) {
                }
                r31 = r7;
                r14 = r8;
                int r7 = (int) r15.objectFieldOffset(r12);
                int r3 = r3 + 1;
                r8 = r17[r3];
                if (!(r8 instanceof java.lang.reflect.Field)) {
                }
                r30 = r0;
                r8 = r1;
                r0 = (int) r15.objectFieldOffset(r8);
                r29 = r11;
                r7 = r7;
                r3 = 0;
            } else {
                r31 = r7;
                r14 = r8;
                r32 = r12;
                int r7 = r16 + 1;
                java.lang.reflect.Field r8 = zzI(r1, (java.lang.String) r17[r16]);
                if (r5 == 9 || r5 == 17) {
                    int r12 = r9 / 3;
                    r11[r12 + r12 + 1] = r8.getType();
                } else {
                    if (r5 == 27 || r5 == 49) {
                        int r12 = r9 / 3;
                        r27 = r7 + 1;
                        r11[r12 + r12 + 1] = r17[r7];
                    } else if (r5 == 12 || r5 == 30 || r5 == 44) {
                        if (!r10) {
                            int r12 = r9 / 3;
                            r27 = r7 + 1;
                            r11[r12 + r12 + 1] = r17[r7];
                        }
                    } else if (r5 == 50) {
                        int r12 = r22 + 1;
                        r13[r22] = r9;
                        int r22 = r9 / 3;
                        int r22 = r22 + r22;
                        int r27 = r7 + 1;
                        r11[r22] = r17[r7];
                        if ((r2 & 2048) != 0) {
                            r7 = r27 + 1;
                            r11[r22 + 1] = r17[r27];
                            r22 = r12;
                        } else {
                            r22 = r12;
                            r12 = r27;
                            r7 = (int) r15.objectFieldOffset(r8);
                            r29 = r11;
                            if ((r2 & 4096) == 4096 || r5 > 17) {
                                r30 = r0;
                                r8 = r1;
                                r0 = 1048575;
                                r27 = r3;
                                r3 = 0;
                            } else {
                                int r8 = r3 + 1;
                                int r3 = r0.charAt(r3);
                                if (r3 >= 55296) {
                                    int r3 = r3 & 8191;
                                    int r26 = 13;
                                    while (true) {
                                        r27 = r8 + 1;
                                        r8 = r0.charAt(r8);
                                        if (r8 < 55296) {
                                            break;
                                        }
                                        r3 |= (r8 & 8191) << r26;
                                        r26 += 13;
                                        r8 = r27;
                                    }
                                    r3 = r3 | (r8 << r26);
                                } else {
                                    r27 = r8;
                                }
                                int r8 = r6 + r6 + (r3 / 32);
                                java.lang.Object r11 = r17[r8];
                                r30 = r0;
                                if (r11 instanceof java.lang.reflect.Field) {
                                    r11 = (java.lang.reflect.Field) r11;
                                } else {
                                    r11 = zzI(r1, (java.lang.String) r11);
                                    r17[r8] = r11;
                                }
                                r8 = r1;
                                r0 = (int) r15.objectFieldOffset(r11);
                                r3 = r3 % 32;
                            }
                            if (r5 >= 18 && r5 <= 49) {
                                r13[r23] = r7;
                                r23++;
                            }
                            r16 = r12;
                        }
                    }
                    r12 = r27;
                    r7 = (int) r15.objectFieldOffset(r8);
                    r29 = r11;
                    if ((r2 & 4096) == 4096) {
                    }
                    r30 = r0;
                    r8 = r1;
                    r0 = 1048575;
                    r27 = r3;
                    r3 = 0;
                    if (r5 >= 18) {
                        r13[r23] = r7;
                        r23++;
                    }
                    r16 = r12;
                }
                r12 = r7;
                r7 = (int) r15.objectFieldOffset(r8);
                r29 = r11;
                if ((r2 & 4096) == 4096) {
                }
                r30 = r0;
                r8 = r1;
                r0 = 1048575;
                r27 = r3;
                r3 = 0;
                if (r5 >= 18) {
                }
                r16 = r12;
            }
            int r1 = r9 + 1;
            r31[r9] = r4;
            int r4 = r1 + 1;
            r31[r1] = ((r2 & 256) != 0 ? 268435456 : 0) | ((r2 & 512) != 0 ? 536870912 : 0) | (r5 << 20) | r7;
            r9 = r4 + 1;
            r31[r4] = r0 | (r3 << 20);
            r1 = r8;
            r8 = r14;
            r14 = r24;
            r4 = r27;
            r3 = r28;
            r11 = r29;
            r0 = r30;
            r7 = r31;
            r12 = r32;
            r5 = 55296;
        }
        return new com.google.android.gms.internal.measurement.zzlp(r7, r11, r8, r12, r34.zza(), r10, false, r13, r14, r21, r35, r36, r37, r38, r39, null);
    }

    private static double zzn(Object obj, long j) {
        return ((Double) zzmy.zzf(obj, j)).doubleValue();
    }

    private static float zzo(Object obj, long j) {
        return ((Float) zzmy.zzf(obj, j)).floatValue();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private final int zzp(Object obj) {
        int i;
        int zzA;
        int zzA2;
        int zzA3;
        int zzB;
        int zzA4;
        int zzv;
        int zzA5;
        int zzA6;
        int zzd;
        int zzA7;
        int i2;
        int zzu;
        boolean z;
        int zzd2;
        int zzi;
        int zzz;
        int zzA8;
        int i3;
        int zzA9;
        int zzA10;
        int zzA11;
        int zzB2;
        int zzA12;
        int zzd3;
        int zzA13;
        int i4;
        Unsafe unsafe = zzb;
        int i5 = 1048575;
        int i6 = 1048575;
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        while (i7 < this.zzc.length) {
            int zzB3 = zzB(i7);
            int[] iArr = this.zzc;
            int i10 = iArr[i7];
            int zzA14 = zzA(zzB3);
            if (zzA14 <= 17) {
                int i11 = iArr[i7 + 2];
                int i12 = i11 & i5;
                i = 1 << (i11 >>> 20);
                if (i12 != i6) {
                    i9 = unsafe.getInt(obj, i12);
                    i6 = i12;
                }
            } else {
                i = 0;
            }
            long j = zzB3 & i5;
            switch (zzA14) {
                case 0:
                    if ((i9 & i) == 0) {
                        break;
                    } else {
                        zzA = zzjm.zzA(i10 << 3);
                        zzA5 = zzA + 8;
                        i8 += zzA5;
                        break;
                    }
                case 1:
                    if ((i9 & i) == 0) {
                        break;
                    } else {
                        zzA2 = zzjm.zzA(i10 << 3);
                        zzA5 = zzA2 + 4;
                        i8 += zzA5;
                        break;
                    }
                case 2:
                    if ((i9 & i) == 0) {
                        break;
                    } else {
                        long j2 = unsafe.getLong(obj, j);
                        zzA3 = zzjm.zzA(i10 << 3);
                        zzB = zzjm.zzB(j2);
                        i8 += zzA3 + zzB;
                        break;
                    }
                case 3:
                    if ((i9 & i) == 0) {
                        break;
                    } else {
                        long j3 = unsafe.getLong(obj, j);
                        zzA3 = zzjm.zzA(i10 << 3);
                        zzB = zzjm.zzB(j3);
                        i8 += zzA3 + zzB;
                        break;
                    }
                case 4:
                    if ((i9 & i) == 0) {
                        break;
                    } else {
                        int i13 = unsafe.getInt(obj, j);
                        zzA4 = zzjm.zzA(i10 << 3);
                        zzv = zzjm.zzv(i13);
                        i2 = zzA4 + zzv;
                        i8 += i2;
                        break;
                    }
                case 5:
                    if ((i9 & i) == 0) {
                        break;
                    } else {
                        zzA = zzjm.zzA(i10 << 3);
                        zzA5 = zzA + 8;
                        i8 += zzA5;
                        break;
                    }
                case 6:
                    if ((i9 & i) == 0) {
                        break;
                    } else {
                        zzA2 = zzjm.zzA(i10 << 3);
                        zzA5 = zzA2 + 4;
                        i8 += zzA5;
                        break;
                    }
                case 7:
                    if ((i9 & i) == 0) {
                        break;
                    } else {
                        zzA5 = zzjm.zzA(i10 << 3) + 1;
                        i8 += zzA5;
                        break;
                    }
                case 8:
                    if ((i9 & i) == 0) {
                        break;
                    } else {
                        Object object = unsafe.getObject(obj, j);
                        if (object instanceof zzje) {
                            zzA6 = zzjm.zzA(i10 << 3);
                            zzd = ((zzje) object).zzd();
                            zzA7 = zzjm.zzA(zzd);
                            i2 = zzA6 + zzA7 + zzd;
                            i8 += i2;
                            break;
                        } else {
                            zzA4 = zzjm.zzA(i10 << 3);
                            zzv = zzjm.zzy((String) object);
                            i2 = zzA4 + zzv;
                            i8 += i2;
                        }
                    }
                case 9:
                    if ((i9 & i) == 0) {
                        break;
                    } else {
                        zzA5 = zzlz.zzo(i10, unsafe.getObject(obj, j), zzE(i7));
                        i8 += zzA5;
                        break;
                    }
                case 10:
                    if ((i9 & i) == 0) {
                        break;
                    } else {
                        zzA6 = zzjm.zzA(i10 << 3);
                        zzd = ((zzje) unsafe.getObject(obj, j)).zzd();
                        zzA7 = zzjm.zzA(zzd);
                        i2 = zzA6 + zzA7 + zzd;
                        i8 += i2;
                        break;
                    }
                case 11:
                    if ((i9 & i) == 0) {
                        break;
                    } else {
                        int i14 = unsafe.getInt(obj, j);
                        zzA4 = zzjm.zzA(i10 << 3);
                        zzv = zzjm.zzA(i14);
                        i2 = zzA4 + zzv;
                        i8 += i2;
                        break;
                    }
                case 12:
                    if ((i9 & i) == 0) {
                        break;
                    } else {
                        int i15 = unsafe.getInt(obj, j);
                        zzA4 = zzjm.zzA(i10 << 3);
                        zzv = zzjm.zzv(i15);
                        i2 = zzA4 + zzv;
                        i8 += i2;
                        break;
                    }
                case 13:
                    if ((i9 & i) == 0) {
                        break;
                    } else {
                        zzA2 = zzjm.zzA(i10 << 3);
                        zzA5 = zzA2 + 4;
                        i8 += zzA5;
                        break;
                    }
                case 14:
                    if ((i9 & i) == 0) {
                        break;
                    } else {
                        zzA = zzjm.zzA(i10 << 3);
                        zzA5 = zzA + 8;
                        i8 += zzA5;
                        break;
                    }
                case 15:
                    if ((i9 & i) == 0) {
                        break;
                    } else {
                        int i16 = unsafe.getInt(obj, j);
                        zzA4 = zzjm.zzA(i10 << 3);
                        zzv = zzjm.zzA((i16 >> 31) ^ (i16 + i16));
                        i2 = zzA4 + zzv;
                        i8 += i2;
                        break;
                    }
                case 16:
                    if ((i & i9) == 0) {
                        break;
                    } else {
                        long j4 = unsafe.getLong(obj, j);
                        i8 += zzjm.zzA(i10 << 3) + zzjm.zzB((j4 >> 63) ^ (j4 + j4));
                        break;
                    }
                case 17:
                    if ((i9 & i) == 0) {
                        break;
                    } else {
                        zzA5 = zzjm.zzu(i10, (zzlm) unsafe.getObject(obj, j), zzE(i7));
                        i8 += zzA5;
                        break;
                    }
                case 18:
                    zzA5 = zzlz.zzh(i10, (List) unsafe.getObject(obj, j), false);
                    i8 += zzA5;
                    break;
                case 19:
                    zzA5 = zzlz.zzf(i10, (List) unsafe.getObject(obj, j), false);
                    i8 += zzA5;
                    break;
                case 20:
                    zzA5 = zzlz.zzm(i10, (List) unsafe.getObject(obj, j), false);
                    i8 += zzA5;
                    break;
                case 21:
                    zzA5 = zzlz.zzx(i10, (List) unsafe.getObject(obj, j), false);
                    i8 += zzA5;
                    break;
                case 22:
                    zzA5 = zzlz.zzk(i10, (List) unsafe.getObject(obj, j), false);
                    i8 += zzA5;
                    break;
                case 23:
                    zzA5 = zzlz.zzh(i10, (List) unsafe.getObject(obj, j), false);
                    i8 += zzA5;
                    break;
                case 24:
                    zzA5 = zzlz.zzf(i10, (List) unsafe.getObject(obj, j), false);
                    i8 += zzA5;
                    break;
                case 25:
                    zzA5 = zzlz.zza(i10, (List) unsafe.getObject(obj, j), false);
                    i8 += zzA5;
                    break;
                case 26:
                    zzu = zzlz.zzu(i10, (List) unsafe.getObject(obj, j));
                    i8 += zzu;
                    break;
                case 27:
                    zzu = zzlz.zzp(i10, (List) unsafe.getObject(obj, j), zzE(i7));
                    i8 += zzu;
                    break;
                case 28:
                    zzu = zzlz.zzc(i10, (List) unsafe.getObject(obj, j));
                    i8 += zzu;
                    break;
                case 29:
                    zzu = zzlz.zzv(i10, (List) unsafe.getObject(obj, j), false);
                    i8 += zzu;
                    break;
                case 30:
                    z = false;
                    zzd2 = zzlz.zzd(i10, (List) unsafe.getObject(obj, j), false);
                    i8 += zzd2;
                    break;
                case 31:
                    z = false;
                    zzd2 = zzlz.zzf(i10, (List) unsafe.getObject(obj, j), false);
                    i8 += zzd2;
                    break;
                case 32:
                    z = false;
                    zzd2 = zzlz.zzh(i10, (List) unsafe.getObject(obj, j), false);
                    i8 += zzd2;
                    break;
                case 33:
                    z = false;
                    zzd2 = zzlz.zzq(i10, (List) unsafe.getObject(obj, j), false);
                    i8 += zzd2;
                    break;
                case 34:
                    z = false;
                    zzd2 = zzlz.zzs(i10, (List) unsafe.getObject(obj, j), false);
                    i8 += zzd2;
                    break;
                case 35:
                    zzi = zzlz.zzi((List) unsafe.getObject(obj, j));
                    if (zzi > 0) {
                        zzz = zzjm.zzz(i10);
                        zzA8 = zzjm.zzA(zzi);
                        i3 = zzz + zzA8;
                        i4 = i3 + zzi;
                        i8 += i4;
                    }
                    break;
                case 36:
                    zzi = zzlz.zzg((List) unsafe.getObject(obj, j));
                    if (zzi > 0) {
                        zzz = zzjm.zzz(i10);
                        zzA8 = zzjm.zzA(zzi);
                        i3 = zzz + zzA8;
                        i4 = i3 + zzi;
                        i8 += i4;
                    }
                    break;
                case 37:
                    zzi = zzlz.zzn((List) unsafe.getObject(obj, j));
                    if (zzi > 0) {
                        zzz = zzjm.zzz(i10);
                        zzA8 = zzjm.zzA(zzi);
                        i3 = zzz + zzA8;
                        i4 = i3 + zzi;
                        i8 += i4;
                    }
                    break;
                case 38:
                    zzi = zzlz.zzy((List) unsafe.getObject(obj, j));
                    if (zzi > 0) {
                        zzz = zzjm.zzz(i10);
                        zzA8 = zzjm.zzA(zzi);
                        i3 = zzz + zzA8;
                        i4 = i3 + zzi;
                        i8 += i4;
                    }
                    break;
                case 39:
                    zzi = zzlz.zzl((List) unsafe.getObject(obj, j));
                    if (zzi > 0) {
                        zzz = zzjm.zzz(i10);
                        zzA8 = zzjm.zzA(zzi);
                        i3 = zzz + zzA8;
                        i4 = i3 + zzi;
                        i8 += i4;
                    }
                    break;
                case 40:
                    zzi = zzlz.zzi((List) unsafe.getObject(obj, j));
                    if (zzi > 0) {
                        zzz = zzjm.zzz(i10);
                        zzA8 = zzjm.zzA(zzi);
                        i3 = zzz + zzA8;
                        i4 = i3 + zzi;
                        i8 += i4;
                    }
                    break;
                case 41:
                    zzi = zzlz.zzg((List) unsafe.getObject(obj, j));
                    if (zzi > 0) {
                        zzz = zzjm.zzz(i10);
                        zzA8 = zzjm.zzA(zzi);
                        i3 = zzz + zzA8;
                        i4 = i3 + zzi;
                        i8 += i4;
                    }
                    break;
                case 42:
                    zzi = zzlz.zzb((List) unsafe.getObject(obj, j));
                    if (zzi > 0) {
                        zzz = zzjm.zzz(i10);
                        zzA8 = zzjm.zzA(zzi);
                        i3 = zzz + zzA8;
                        i4 = i3 + zzi;
                        i8 += i4;
                    }
                    break;
                case 43:
                    zzi = zzlz.zzw((List) unsafe.getObject(obj, j));
                    if (zzi > 0) {
                        zzz = zzjm.zzz(i10);
                        zzA8 = zzjm.zzA(zzi);
                        i3 = zzz + zzA8;
                        i4 = i3 + zzi;
                        i8 += i4;
                    }
                    break;
                case 44:
                    zzi = zzlz.zze((List) unsafe.getObject(obj, j));
                    if (zzi > 0) {
                        zzz = zzjm.zzz(i10);
                        zzA8 = zzjm.zzA(zzi);
                        i3 = zzz + zzA8;
                        i4 = i3 + zzi;
                        i8 += i4;
                    }
                    break;
                case 45:
                    zzi = zzlz.zzg((List) unsafe.getObject(obj, j));
                    if (zzi > 0) {
                        zzz = zzjm.zzz(i10);
                        zzA8 = zzjm.zzA(zzi);
                        i3 = zzz + zzA8;
                        i4 = i3 + zzi;
                        i8 += i4;
                    }
                    break;
                case 46:
                    zzi = zzlz.zzi((List) unsafe.getObject(obj, j));
                    if (zzi > 0) {
                        zzz = zzjm.zzz(i10);
                        zzA8 = zzjm.zzA(zzi);
                        i3 = zzz + zzA8;
                        i4 = i3 + zzi;
                        i8 += i4;
                    }
                    break;
                case 47:
                    zzi = zzlz.zzr((List) unsafe.getObject(obj, j));
                    if (zzi > 0) {
                        zzz = zzjm.zzz(i10);
                        zzA8 = zzjm.zzA(zzi);
                        i3 = zzz + zzA8;
                        i4 = i3 + zzi;
                        i8 += i4;
                    }
                    break;
                case 48:
                    zzi = zzlz.zzt((List) unsafe.getObject(obj, j));
                    if (zzi > 0) {
                        zzz = zzjm.zzz(i10);
                        zzA8 = zzjm.zzA(zzi);
                        i3 = zzz + zzA8;
                        i4 = i3 + zzi;
                        i8 += i4;
                    }
                    break;
                case 49:
                    zzu = zzlz.zzj(i10, (List) unsafe.getObject(obj, j), zzE(i7));
                    i8 += zzu;
                    break;
                case 50:
                    zzlh.zza(i10, unsafe.getObject(obj, j), zzF(i7));
                    break;
                case 51:
                    if (zzX(obj, i10, i7)) {
                        zzA9 = zzjm.zzA(i10 << 3);
                        zzu = zzA9 + 8;
                        i8 += zzu;
                    }
                    break;
                case 52:
                    if (zzX(obj, i10, i7)) {
                        zzA10 = zzjm.zzA(i10 << 3);
                        zzu = zzA10 + 4;
                        i8 += zzu;
                    }
                    break;
                case 53:
                    if (zzX(obj, i10, i7)) {
                        long zzC = zzC(obj, j);
                        zzA11 = zzjm.zzA(i10 << 3);
                        zzB2 = zzjm.zzB(zzC);
                        i8 += zzA11 + zzB2;
                    }
                    break;
                case 54:
                    if (zzX(obj, i10, i7)) {
                        long zzC2 = zzC(obj, j);
                        zzA11 = zzjm.zzA(i10 << 3);
                        zzB2 = zzjm.zzB(zzC2);
                        i8 += zzA11 + zzB2;
                    }
                    break;
                case 55:
                    if (zzX(obj, i10, i7)) {
                        int zzr = zzr(obj, j);
                        i3 = zzjm.zzA(i10 << 3);
                        zzi = zzjm.zzv(zzr);
                        i4 = i3 + zzi;
                        i8 += i4;
                    }
                    break;
                case 56:
                    if (zzX(obj, i10, i7)) {
                        zzA9 = zzjm.zzA(i10 << 3);
                        zzu = zzA9 + 8;
                        i8 += zzu;
                    }
                    break;
                case 57:
                    if (zzX(obj, i10, i7)) {
                        zzA10 = zzjm.zzA(i10 << 3);
                        zzu = zzA10 + 4;
                        i8 += zzu;
                    }
                    break;
                case 58:
                    if (zzX(obj, i10, i7)) {
                        zzu = zzjm.zzA(i10 << 3) + 1;
                        i8 += zzu;
                    }
                    break;
                case 59:
                    if (zzX(obj, i10, i7)) {
                        Object object2 = unsafe.getObject(obj, j);
                        if (object2 instanceof zzje) {
                            zzA12 = zzjm.zzA(i10 << 3);
                            zzd3 = ((zzje) object2).zzd();
                            zzA13 = zzjm.zzA(zzd3);
                            i4 = zzA12 + zzA13 + zzd3;
                            i8 += i4;
                        } else {
                            i3 = zzjm.zzA(i10 << 3);
                            zzi = zzjm.zzy((String) object2);
                            i4 = i3 + zzi;
                            i8 += i4;
                        }
                    }
                    break;
                case 60:
                    if (zzX(obj, i10, i7)) {
                        zzu = zzlz.zzo(i10, unsafe.getObject(obj, j), zzE(i7));
                        i8 += zzu;
                    }
                    break;
                case 61:
                    if (zzX(obj, i10, i7)) {
                        zzA12 = zzjm.zzA(i10 << 3);
                        zzd3 = ((zzje) unsafe.getObject(obj, j)).zzd();
                        zzA13 = zzjm.zzA(zzd3);
                        i4 = zzA12 + zzA13 + zzd3;
                        i8 += i4;
                    }
                    break;
                case 62:
                    if (zzX(obj, i10, i7)) {
                        int zzr2 = zzr(obj, j);
                        i3 = zzjm.zzA(i10 << 3);
                        zzi = zzjm.zzA(zzr2);
                        i4 = i3 + zzi;
                        i8 += i4;
                    }
                    break;
                case 63:
                    if (zzX(obj, i10, i7)) {
                        int zzr3 = zzr(obj, j);
                        i3 = zzjm.zzA(i10 << 3);
                        zzi = zzjm.zzv(zzr3);
                        i4 = i3 + zzi;
                        i8 += i4;
                    }
                    break;
                case 64:
                    if (zzX(obj, i10, i7)) {
                        zzA10 = zzjm.zzA(i10 << 3);
                        zzu = zzA10 + 4;
                        i8 += zzu;
                    }
                    break;
                case 65:
                    if (zzX(obj, i10, i7)) {
                        zzA9 = zzjm.zzA(i10 << 3);
                        zzu = zzA9 + 8;
                        i8 += zzu;
                    }
                    break;
                case 66:
                    if (zzX(obj, i10, i7)) {
                        int zzr4 = zzr(obj, j);
                        i3 = zzjm.zzA(i10 << 3);
                        zzi = zzjm.zzA((zzr4 >> 31) ^ (zzr4 + zzr4));
                        i4 = i3 + zzi;
                        i8 += i4;
                    }
                    break;
                case 67:
                    if (zzX(obj, i10, i7)) {
                        long zzC3 = zzC(obj, j);
                        i8 += zzjm.zzA(i10 << 3) + zzjm.zzB((zzC3 >> 63) ^ (zzC3 + zzC3));
                    }
                    break;
                case 68:
                    if (zzX(obj, i10, i7)) {
                        zzu = zzjm.zzu(i10, (zzlm) unsafe.getObject(obj, j), zzE(i7));
                        i8 += zzu;
                    }
                    break;
            }
            i7 += 3;
            i5 = 1048575;
        }
        zzmo zzmoVar = this.zzn;
        int zza2 = i8 + zzmoVar.zza(zzmoVar.zzd(obj));
        if (this.zzh) {
            this.zzo.zza(obj);
            throw null;
        }
        return zza2;
    }

    private final int zzq(Object obj) {
        int zzA;
        int zzA2;
        int zzA3;
        int zzB;
        int zzA4;
        int zzv;
        int zzA5;
        int zzA6;
        int zzd;
        int zzA7;
        int zzo;
        int zzz;
        int zzA8;
        int i;
        Unsafe unsafe = zzb;
        int i2 = 0;
        for (int i3 = 0; i3 < this.zzc.length; i3 += 3) {
            int zzB2 = zzB(i3);
            int zzA9 = zzA(zzB2);
            int i4 = this.zzc[i3];
            long j = zzB2 & 1048575;
            if (zzA9 >= zzjx.DOUBLE_LIST_PACKED.zza() && zzA9 <= zzjx.SINT64_LIST_PACKED.zza()) {
                int i5 = this.zzc[i3 + 2];
            }
            switch (zzA9) {
                case 0:
                    if (zzT(obj, i3)) {
                        zzA = zzjm.zzA(i4 << 3);
                        zzo = zzA + 8;
                        i2 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 1:
                    if (zzT(obj, i3)) {
                        zzA2 = zzjm.zzA(i4 << 3);
                        zzo = zzA2 + 4;
                        i2 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 2:
                    if (zzT(obj, i3)) {
                        long zzd2 = zzmy.zzd(obj, j);
                        zzA3 = zzjm.zzA(i4 << 3);
                        zzB = zzjm.zzB(zzd2);
                        i2 += zzA3 + zzB;
                        break;
                    } else {
                        break;
                    }
                case 3:
                    if (zzT(obj, i3)) {
                        long zzd3 = zzmy.zzd(obj, j);
                        zzA3 = zzjm.zzA(i4 << 3);
                        zzB = zzjm.zzB(zzd3);
                        i2 += zzA3 + zzB;
                        break;
                    } else {
                        break;
                    }
                case 4:
                    if (zzT(obj, i3)) {
                        int zzc = zzmy.zzc(obj, j);
                        zzA4 = zzjm.zzA(i4 << 3);
                        zzv = zzjm.zzv(zzc);
                        i = zzA4 + zzv;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 5:
                    if (zzT(obj, i3)) {
                        zzA = zzjm.zzA(i4 << 3);
                        zzo = zzA + 8;
                        i2 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 6:
                    if (zzT(obj, i3)) {
                        zzA2 = zzjm.zzA(i4 << 3);
                        zzo = zzA2 + 4;
                        i2 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 7:
                    if (zzT(obj, i3)) {
                        zzA5 = zzjm.zzA(i4 << 3);
                        zzo = zzA5 + 1;
                        i2 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 8:
                    if (!zzT(obj, i3)) {
                        break;
                    } else {
                        Object zzf = zzmy.zzf(obj, j);
                        if (zzf instanceof zzje) {
                            zzA6 = zzjm.zzA(i4 << 3);
                            zzd = ((zzje) zzf).zzd();
                            zzA7 = zzjm.zzA(zzd);
                            i = zzA6 + zzA7 + zzd;
                            i2 += i;
                            break;
                        } else {
                            zzA4 = zzjm.zzA(i4 << 3);
                            zzv = zzjm.zzy((String) zzf);
                            i = zzA4 + zzv;
                            i2 += i;
                        }
                    }
                case 9:
                    if (zzT(obj, i3)) {
                        zzo = zzlz.zzo(i4, zzmy.zzf(obj, j), zzE(i3));
                        i2 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 10:
                    if (zzT(obj, i3)) {
                        zzA6 = zzjm.zzA(i4 << 3);
                        zzd = ((zzje) zzmy.zzf(obj, j)).zzd();
                        zzA7 = zzjm.zzA(zzd);
                        i = zzA6 + zzA7 + zzd;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 11:
                    if (zzT(obj, i3)) {
                        int zzc2 = zzmy.zzc(obj, j);
                        zzA4 = zzjm.zzA(i4 << 3);
                        zzv = zzjm.zzA(zzc2);
                        i = zzA4 + zzv;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 12:
                    if (zzT(obj, i3)) {
                        int zzc3 = zzmy.zzc(obj, j);
                        zzA4 = zzjm.zzA(i4 << 3);
                        zzv = zzjm.zzv(zzc3);
                        i = zzA4 + zzv;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 13:
                    if (zzT(obj, i3)) {
                        zzA2 = zzjm.zzA(i4 << 3);
                        zzo = zzA2 + 4;
                        i2 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 14:
                    if (zzT(obj, i3)) {
                        zzA = zzjm.zzA(i4 << 3);
                        zzo = zzA + 8;
                        i2 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 15:
                    if (zzT(obj, i3)) {
                        int zzc4 = zzmy.zzc(obj, j);
                        zzA4 = zzjm.zzA(i4 << 3);
                        zzv = zzjm.zzA((zzc4 >> 31) ^ (zzc4 + zzc4));
                        i = zzA4 + zzv;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 16:
                    if (zzT(obj, i3)) {
                        long zzd4 = zzmy.zzd(obj, j);
                        zzA4 = zzjm.zzA(i4 << 3);
                        zzv = zzjm.zzB((zzd4 >> 63) ^ (zzd4 + zzd4));
                        i = zzA4 + zzv;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 17:
                    if (zzT(obj, i3)) {
                        zzo = zzjm.zzu(i4, (zzlm) zzmy.zzf(obj, j), zzE(i3));
                        i2 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 18:
                    zzo = zzlz.zzh(i4, (List) zzmy.zzf(obj, j), false);
                    i2 += zzo;
                    break;
                case 19:
                    zzo = zzlz.zzf(i4, (List) zzmy.zzf(obj, j), false);
                    i2 += zzo;
                    break;
                case 20:
                    zzo = zzlz.zzm(i4, (List) zzmy.zzf(obj, j), false);
                    i2 += zzo;
                    break;
                case 21:
                    zzo = zzlz.zzx(i4, (List) zzmy.zzf(obj, j), false);
                    i2 += zzo;
                    break;
                case 22:
                    zzo = zzlz.zzk(i4, (List) zzmy.zzf(obj, j), false);
                    i2 += zzo;
                    break;
                case 23:
                    zzo = zzlz.zzh(i4, (List) zzmy.zzf(obj, j), false);
                    i2 += zzo;
                    break;
                case 24:
                    zzo = zzlz.zzf(i4, (List) zzmy.zzf(obj, j), false);
                    i2 += zzo;
                    break;
                case 25:
                    zzo = zzlz.zza(i4, (List) zzmy.zzf(obj, j), false);
                    i2 += zzo;
                    break;
                case 26:
                    zzo = zzlz.zzu(i4, (List) zzmy.zzf(obj, j));
                    i2 += zzo;
                    break;
                case 27:
                    zzo = zzlz.zzp(i4, (List) zzmy.zzf(obj, j), zzE(i3));
                    i2 += zzo;
                    break;
                case 28:
                    zzo = zzlz.zzc(i4, (List) zzmy.zzf(obj, j));
                    i2 += zzo;
                    break;
                case 29:
                    zzo = zzlz.zzv(i4, (List) zzmy.zzf(obj, j), false);
                    i2 += zzo;
                    break;
                case 30:
                    zzo = zzlz.zzd(i4, (List) zzmy.zzf(obj, j), false);
                    i2 += zzo;
                    break;
                case 31:
                    zzo = zzlz.zzf(i4, (List) zzmy.zzf(obj, j), false);
                    i2 += zzo;
                    break;
                case 32:
                    zzo = zzlz.zzh(i4, (List) zzmy.zzf(obj, j), false);
                    i2 += zzo;
                    break;
                case 33:
                    zzo = zzlz.zzq(i4, (List) zzmy.zzf(obj, j), false);
                    i2 += zzo;
                    break;
                case 34:
                    zzo = zzlz.zzs(i4, (List) zzmy.zzf(obj, j), false);
                    i2 += zzo;
                    break;
                case 35:
                    zzv = zzlz.zzi((List) unsafe.getObject(obj, j));
                    if (zzv > 0) {
                        zzz = zzjm.zzz(i4);
                        zzA8 = zzjm.zzA(zzv);
                        zzA4 = zzz + zzA8;
                        i = zzA4 + zzv;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 36:
                    zzv = zzlz.zzg((List) unsafe.getObject(obj, j));
                    if (zzv > 0) {
                        zzz = zzjm.zzz(i4);
                        zzA8 = zzjm.zzA(zzv);
                        zzA4 = zzz + zzA8;
                        i = zzA4 + zzv;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 37:
                    zzv = zzlz.zzn((List) unsafe.getObject(obj, j));
                    if (zzv > 0) {
                        zzz = zzjm.zzz(i4);
                        zzA8 = zzjm.zzA(zzv);
                        zzA4 = zzz + zzA8;
                        i = zzA4 + zzv;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 38:
                    zzv = zzlz.zzy((List) unsafe.getObject(obj, j));
                    if (zzv > 0) {
                        zzz = zzjm.zzz(i4);
                        zzA8 = zzjm.zzA(zzv);
                        zzA4 = zzz + zzA8;
                        i = zzA4 + zzv;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 39:
                    zzv = zzlz.zzl((List) unsafe.getObject(obj, j));
                    if (zzv > 0) {
                        zzz = zzjm.zzz(i4);
                        zzA8 = zzjm.zzA(zzv);
                        zzA4 = zzz + zzA8;
                        i = zzA4 + zzv;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 40:
                    zzv = zzlz.zzi((List) unsafe.getObject(obj, j));
                    if (zzv > 0) {
                        zzz = zzjm.zzz(i4);
                        zzA8 = zzjm.zzA(zzv);
                        zzA4 = zzz + zzA8;
                        i = zzA4 + zzv;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 41:
                    zzv = zzlz.zzg((List) unsafe.getObject(obj, j));
                    if (zzv > 0) {
                        zzz = zzjm.zzz(i4);
                        zzA8 = zzjm.zzA(zzv);
                        zzA4 = zzz + zzA8;
                        i = zzA4 + zzv;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 42:
                    zzv = zzlz.zzb((List) unsafe.getObject(obj, j));
                    if (zzv > 0) {
                        zzz = zzjm.zzz(i4);
                        zzA8 = zzjm.zzA(zzv);
                        zzA4 = zzz + zzA8;
                        i = zzA4 + zzv;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 43:
                    zzv = zzlz.zzw((List) unsafe.getObject(obj, j));
                    if (zzv > 0) {
                        zzz = zzjm.zzz(i4);
                        zzA8 = zzjm.zzA(zzv);
                        zzA4 = zzz + zzA8;
                        i = zzA4 + zzv;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 44:
                    zzv = zzlz.zze((List) unsafe.getObject(obj, j));
                    if (zzv > 0) {
                        zzz = zzjm.zzz(i4);
                        zzA8 = zzjm.zzA(zzv);
                        zzA4 = zzz + zzA8;
                        i = zzA4 + zzv;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 45:
                    zzv = zzlz.zzg((List) unsafe.getObject(obj, j));
                    if (zzv > 0) {
                        zzz = zzjm.zzz(i4);
                        zzA8 = zzjm.zzA(zzv);
                        zzA4 = zzz + zzA8;
                        i = zzA4 + zzv;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 46:
                    zzv = zzlz.zzi((List) unsafe.getObject(obj, j));
                    if (zzv > 0) {
                        zzz = zzjm.zzz(i4);
                        zzA8 = zzjm.zzA(zzv);
                        zzA4 = zzz + zzA8;
                        i = zzA4 + zzv;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 47:
                    zzv = zzlz.zzr((List) unsafe.getObject(obj, j));
                    if (zzv > 0) {
                        zzz = zzjm.zzz(i4);
                        zzA8 = zzjm.zzA(zzv);
                        zzA4 = zzz + zzA8;
                        i = zzA4 + zzv;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 48:
                    zzv = zzlz.zzt((List) unsafe.getObject(obj, j));
                    if (zzv > 0) {
                        zzz = zzjm.zzz(i4);
                        zzA8 = zzjm.zzA(zzv);
                        zzA4 = zzz + zzA8;
                        i = zzA4 + zzv;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 49:
                    zzo = zzlz.zzj(i4, (List) zzmy.zzf(obj, j), zzE(i3));
                    i2 += zzo;
                    break;
                case 50:
                    zzlh.zza(i4, zzmy.zzf(obj, j), zzF(i3));
                    break;
                case 51:
                    if (zzX(obj, i4, i3)) {
                        zzA = zzjm.zzA(i4 << 3);
                        zzo = zzA + 8;
                        i2 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 52:
                    if (zzX(obj, i4, i3)) {
                        zzA2 = zzjm.zzA(i4 << 3);
                        zzo = zzA2 + 4;
                        i2 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 53:
                    if (zzX(obj, i4, i3)) {
                        long zzC = zzC(obj, j);
                        zzA3 = zzjm.zzA(i4 << 3);
                        zzB = zzjm.zzB(zzC);
                        i2 += zzA3 + zzB;
                        break;
                    } else {
                        break;
                    }
                case 54:
                    if (zzX(obj, i4, i3)) {
                        long zzC2 = zzC(obj, j);
                        zzA3 = zzjm.zzA(i4 << 3);
                        zzB = zzjm.zzB(zzC2);
                        i2 += zzA3 + zzB;
                        break;
                    } else {
                        break;
                    }
                case 55:
                    if (zzX(obj, i4, i3)) {
                        int zzr = zzr(obj, j);
                        zzA4 = zzjm.zzA(i4 << 3);
                        zzv = zzjm.zzv(zzr);
                        i = zzA4 + zzv;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 56:
                    if (zzX(obj, i4, i3)) {
                        zzA = zzjm.zzA(i4 << 3);
                        zzo = zzA + 8;
                        i2 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 57:
                    if (zzX(obj, i4, i3)) {
                        zzA2 = zzjm.zzA(i4 << 3);
                        zzo = zzA2 + 4;
                        i2 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 58:
                    if (zzX(obj, i4, i3)) {
                        zzA5 = zzjm.zzA(i4 << 3);
                        zzo = zzA5 + 1;
                        i2 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 59:
                    if (!zzX(obj, i4, i3)) {
                        break;
                    } else {
                        Object zzf2 = zzmy.zzf(obj, j);
                        if (zzf2 instanceof zzje) {
                            zzA6 = zzjm.zzA(i4 << 3);
                            zzd = ((zzje) zzf2).zzd();
                            zzA7 = zzjm.zzA(zzd);
                            i = zzA6 + zzA7 + zzd;
                            i2 += i;
                            break;
                        } else {
                            zzA4 = zzjm.zzA(i4 << 3);
                            zzv = zzjm.zzy((String) zzf2);
                            i = zzA4 + zzv;
                            i2 += i;
                        }
                    }
                case 60:
                    if (zzX(obj, i4, i3)) {
                        zzo = zzlz.zzo(i4, zzmy.zzf(obj, j), zzE(i3));
                        i2 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 61:
                    if (zzX(obj, i4, i3)) {
                        zzA6 = zzjm.zzA(i4 << 3);
                        zzd = ((zzje) zzmy.zzf(obj, j)).zzd();
                        zzA7 = zzjm.zzA(zzd);
                        i = zzA6 + zzA7 + zzd;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 62:
                    if (zzX(obj, i4, i3)) {
                        int zzr2 = zzr(obj, j);
                        zzA4 = zzjm.zzA(i4 << 3);
                        zzv = zzjm.zzA(zzr2);
                        i = zzA4 + zzv;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 63:
                    if (zzX(obj, i4, i3)) {
                        int zzr3 = zzr(obj, j);
                        zzA4 = zzjm.zzA(i4 << 3);
                        zzv = zzjm.zzv(zzr3);
                        i = zzA4 + zzv;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 64:
                    if (zzX(obj, i4, i3)) {
                        zzA2 = zzjm.zzA(i4 << 3);
                        zzo = zzA2 + 4;
                        i2 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 65:
                    if (zzX(obj, i4, i3)) {
                        zzA = zzjm.zzA(i4 << 3);
                        zzo = zzA + 8;
                        i2 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 66:
                    if (zzX(obj, i4, i3)) {
                        int zzr4 = zzr(obj, j);
                        zzA4 = zzjm.zzA(i4 << 3);
                        zzv = zzjm.zzA((zzr4 >> 31) ^ (zzr4 + zzr4));
                        i = zzA4 + zzv;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 67:
                    if (zzX(obj, i4, i3)) {
                        long zzC3 = zzC(obj, j);
                        zzA4 = zzjm.zzA(i4 << 3);
                        zzv = zzjm.zzB((zzC3 >> 63) ^ (zzC3 + zzC3));
                        i = zzA4 + zzv;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 68:
                    if (zzX(obj, i4, i3)) {
                        zzo = zzjm.zzu(i4, (zzlm) zzmy.zzf(obj, j), zzE(i3));
                        i2 += zzo;
                        break;
                    } else {
                        break;
                    }
            }
        }
        zzmo zzmoVar = this.zzn;
        return i2 + zzmoVar.zza(zzmoVar.zzd(obj));
    }

    private static int zzr(Object obj, long j) {
        return ((Integer) zzmy.zzf(obj, j)).intValue();
    }

    private final int zzs(Object obj, byte[] bArr, int i, int i2, int i3, long j, zzir zzirVar) throws IOException {
        Unsafe unsafe = zzb;
        Object zzF = zzF(i3);
        Object object = unsafe.getObject(obj, j);
        if (!((zzlg) object).zze()) {
            zzlg zzb2 = zzlg.zza().zzb();
            zzlh.zzb(zzb2, object);
            unsafe.putObject(obj, j, zzb2);
        }
        zzlf zzlfVar = (zzlf) zzF;
        throw null;
    }

    private final int zzt(Object obj, byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, int i7, long j, int i8, zzir zzirVar) throws IOException {
        Unsafe unsafe = zzb;
        long j2 = this.zzc[i8 + 2] & 1048575;
        switch (i7) {
            case 51:
                if (i5 == 1) {
                    unsafe.putObject(obj, j, Double.valueOf(Double.longBitsToDouble(zzis.zzp(bArr, i))));
                    unsafe.putInt(obj, j2, i4);
                    return i + 8;
                }
                break;
            case 52:
                if (i5 == 5) {
                    unsafe.putObject(obj, j, Float.valueOf(Float.intBitsToFloat(zzis.zzb(bArr, i))));
                    unsafe.putInt(obj, j2, i4);
                    return i + 4;
                }
                break;
            case 53:
            case 54:
                if (i5 == 0) {
                    int zzm = zzis.zzm(bArr, i, zzirVar);
                    unsafe.putObject(obj, j, Long.valueOf(zzirVar.zzb));
                    unsafe.putInt(obj, j2, i4);
                    return zzm;
                }
                break;
            case 55:
            case 62:
                if (i5 == 0) {
                    int zzj = zzis.zzj(bArr, i, zzirVar);
                    unsafe.putObject(obj, j, Integer.valueOf(zzirVar.zza));
                    unsafe.putInt(obj, j2, i4);
                    return zzj;
                }
                break;
            case 56:
            case 65:
                if (i5 == 1) {
                    unsafe.putObject(obj, j, Long.valueOf(zzis.zzp(bArr, i)));
                    unsafe.putInt(obj, j2, i4);
                    return i + 8;
                }
                break;
            case 57:
            case 64:
                if (i5 == 5) {
                    unsafe.putObject(obj, j, Integer.valueOf(zzis.zzb(bArr, i)));
                    unsafe.putInt(obj, j2, i4);
                    return i + 4;
                }
                break;
            case 58:
                if (i5 == 0) {
                    int zzm2 = zzis.zzm(bArr, i, zzirVar);
                    unsafe.putObject(obj, j, Boolean.valueOf(zzirVar.zzb != 0));
                    unsafe.putInt(obj, j2, i4);
                    return zzm2;
                }
                break;
            case 59:
                if (i5 == 2) {
                    int zzj2 = zzis.zzj(bArr, i, zzirVar);
                    int i9 = zzirVar.zza;
                    if (i9 == 0) {
                        unsafe.putObject(obj, j, "");
                    } else if ((i6 & 536870912) == 0 || zznd.zzf(bArr, zzj2, zzj2 + i9)) {
                        unsafe.putObject(obj, j, new String(bArr, zzj2, i9, zzkn.zzb));
                        zzj2 += i9;
                    } else {
                        throw zzkp.zzc();
                    }
                    unsafe.putInt(obj, j2, i4);
                    return zzj2;
                }
                break;
            case 60:
                if (i5 == 2) {
                    Object zzH = zzH(obj, i4, i8);
                    int zzo = zzis.zzo(zzH, zzE(i8), bArr, i, i2, zzirVar);
                    zzP(obj, i4, i8, zzH);
                    return zzo;
                }
                break;
            case 61:
                if (i5 == 2) {
                    int zza2 = zzis.zza(bArr, i, zzirVar);
                    unsafe.putObject(obj, j, zzirVar.zzc);
                    unsafe.putInt(obj, j2, i4);
                    return zza2;
                }
                break;
            case 63:
                if (i5 == 0) {
                    int zzj3 = zzis.zzj(bArr, i, zzirVar);
                    int i10 = zzirVar.zza;
                    zzkj zzD = zzD(i8);
                    if (zzD == null || zzD.zza(i10)) {
                        unsafe.putObject(obj, j, Integer.valueOf(i10));
                        unsafe.putInt(obj, j2, i4);
                    } else {
                        zzd(obj).zzj(i3, Long.valueOf(i10));
                    }
                    return zzj3;
                }
                break;
            case 66:
                if (i5 == 0) {
                    int zzj4 = zzis.zzj(bArr, i, zzirVar);
                    unsafe.putObject(obj, j, Integer.valueOf(zzji.zzb(zzirVar.zza)));
                    unsafe.putInt(obj, j2, i4);
                    return zzj4;
                }
                break;
            case 67:
                if (i5 == 0) {
                    int zzm3 = zzis.zzm(bArr, i, zzirVar);
                    unsafe.putObject(obj, j, Long.valueOf(zzji.zzc(zzirVar.zzb)));
                    unsafe.putInt(obj, j2, i4);
                    return zzm3;
                }
                break;
            case 68:
                if (i5 == 3) {
                    Object zzH2 = zzH(obj, i4, i8);
                    int zzn = zzis.zzn(zzH2, zzE(i8), bArr, i, i2, (i3 & (-8)) | 4, zzirVar);
                    zzP(obj, i4, i8, zzH2);
                    return zzn;
                }
                break;
        }
        return i;
    }

    /* JADX WARN: Code restructure failed: missing block: B:100:0x02e7, code lost:
        if (r0 != r24) goto L42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:101:0x02e9, code lost:
        r14 = r31;
        r12 = r32;
        r13 = r34;
        r11 = r35;
        r2 = r15;
        r10 = r18;
        r1 = r23;
        r6 = r25;
        r7 = r26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:102:0x02fc, code lost:
        r2 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:108:0x0328, code lost:
        if (r0 != r14) goto L42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:113:0x034b, code lost:
        if (r0 != r14) goto L42;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v10, types: [int] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final int zzu(java.lang.Object r31, byte[] r32, int r33, int r34, com.google.android.gms.internal.measurement.zzir r35) throws java.io.IOException {
        byte r17;
        int r4;
        int r0;
        int r2;
        int r23;
        sun.misc.Unsafe r29;
        int r18;
        int r15;
        int r22;
        int r20;
        sun.misc.Unsafe r2;
        int r20;
        sun.misc.Unsafe r7;
        com.google.android.gms.internal.measurement.zzlp<T> r13;
        int r19;
        sun.misc.Unsafe r7;
        int r14;
        int r25;
        int r26;
        com.google.android.gms.internal.measurement.zzlp<T> r15 = r30;
        java.lang.Object r14 = r31;
        byte[] r12 = r32;
        int r13 = r34;
        com.google.android.gms.internal.measurement.zzir r11 = r35;
        zzJ(r31);
        sun.misc.Unsafe r9 = com.google.android.gms.internal.measurement.zzlp.zzb;
        int r10 = -1;
        int r8 = 1048575;
        int r0 = r33;
        int r7 = 1048575;
        int r1 = -1;
        int r2 = 0;
        int r6 = 0;
        while (r0 < r13) {
            int r3 = r0 + 1;
            byte r0 = r12[r0];
            if (r0 < 0) {
                r4 = com.google.android.gms.internal.measurement.zzis.zzk(r0, r12, r3, r11);
                r17 = r11.zza;
            } else {
                r17 = r0;
                r4 = r3;
            }
            int r5 = r17 >>> 3;
            int r3 = r17 & 7;
            if (r5 > r1) {
                r0 = r15.zzx(r5, r2 / 3);
            } else {
                r0 = r15.zzw(r5);
            }
            int r2 = r0;
            if (r2 == r10) {
                r2 = r4;
                r23 = r5;
                r29 = r9;
                r18 = r10;
                r15 = 0;
            } else {
                int[] r0 = r15.zzc;
                int r1 = r0[r2 + 1];
                int r13 = zzA(r1);
                sun.misc.Unsafe r19 = r9;
                long r8 = r1 & r8;
                if (r13 <= 17) {
                    int r0 = r0[r2 + 2];
                    int r10 = 1 << (r0 >>> 20);
                    int r0 = r0 & 1048575;
                    if (r0 != r7) {
                        r22 = r1;
                        r20 = r2;
                        if (r7 != 1048575) {
                            long r1 = r7;
                            r7 = r19;
                            r7.putInt(r14, r1, r6);
                        } else {
                            r7 = r19;
                        }
                        if (r0 != 1048575) {
                            r6 = r7.getInt(r14, r0);
                        }
                        r2 = r7;
                        r7 = r0;
                    } else {
                        r22 = r1;
                        r20 = r2;
                        r2 = r19;
                    }
                    switch (r13) {
                        case 0:
                            r13 = r30;
                            r23 = r5;
                            r19 = 1048575;
                            r15 = r20;
                            r20 = r7;
                            r7 = r2;
                            if (r3 != 1) {
                                r2 = r4;
                                r29 = r7;
                                r7 = r20;
                                r18 = -1;
                                break;
                            } else {
                                com.google.android.gms.internal.measurement.zzmy.zzo(r14, r8, java.lang.Double.longBitsToDouble(com.google.android.gms.internal.measurement.zzis.zzp(r12, r4)));
                                r0 = r4 + 8;
                                r6 |= r10;
                                r9 = r7;
                                r2 = r15;
                                r8 = r19;
                                r7 = r20;
                                r1 = r23;
                                r10 = -1;
                                r15 = r13;
                                r13 = r34;
                                break;
                            }
                        case 1:
                            r13 = r30;
                            r23 = r5;
                            r19 = 1048575;
                            r15 = r20;
                            r20 = r7;
                            r7 = r2;
                            if (r3 != 5) {
                                r2 = r4;
                                r29 = r7;
                                r7 = r20;
                                r18 = -1;
                                break;
                            } else {
                                com.google.android.gms.internal.measurement.zzmy.zzp(r14, r8, java.lang.Float.intBitsToFloat(com.google.android.gms.internal.measurement.zzis.zzb(r12, r4)));
                                r0 = r4 + 4;
                                r6 |= r10;
                                r9 = r7;
                                r2 = r15;
                                r8 = r19;
                                r7 = r20;
                                r1 = r23;
                                r10 = -1;
                                r15 = r13;
                                r13 = r34;
                                break;
                            }
                        case 2:
                        case 3:
                            r13 = r30;
                            r23 = r5;
                            r19 = 1048575;
                            r15 = r20;
                            r20 = r7;
                            r7 = r2;
                            if (r3 != 0) {
                                r2 = r4;
                                r29 = r7;
                                r7 = r20;
                                r18 = -1;
                                break;
                            } else {
                                int r17 = com.google.android.gms.internal.measurement.zzis.zzm(r12, r4, r11);
                                r7.putLong(r31, r8, r11.zzb);
                                r6 |= r10;
                                r9 = r7;
                                r2 = r15;
                                r0 = r17;
                                r8 = r19;
                                r7 = r20;
                                r1 = r23;
                                r10 = -1;
                                r15 = r13;
                                r13 = r34;
                                break;
                            }
                        case 4:
                        case 11:
                            r13 = r30;
                            r23 = r5;
                            r19 = 1048575;
                            r15 = r20;
                            r20 = r7;
                            r7 = r2;
                            if (r3 != 0) {
                                r2 = r4;
                                r29 = r7;
                                r7 = r20;
                                r18 = -1;
                                break;
                            } else {
                                r0 = com.google.android.gms.internal.measurement.zzis.zzj(r12, r4, r11);
                                r7.putInt(r14, r8, r11.zza);
                                r6 |= r10;
                                r9 = r7;
                                r2 = r15;
                                r8 = r19;
                                r7 = r20;
                                r1 = r23;
                                r10 = -1;
                                r15 = r13;
                                r13 = r34;
                                break;
                            }
                        case 5:
                        case 14:
                            r13 = r30;
                            r23 = r5;
                            r19 = 1048575;
                            r15 = r20;
                            r20 = r7;
                            r7 = r2;
                            if (r3 != 1) {
                                r2 = r4;
                                r29 = r7;
                                r7 = r20;
                                r18 = -1;
                                break;
                            } else {
                                r7.putLong(r31, r8, com.google.android.gms.internal.measurement.zzis.zzp(r12, r4));
                                r0 = r4 + 8;
                                r6 |= r10;
                                r9 = r7;
                                r2 = r15;
                                r8 = r19;
                                r7 = r20;
                                r1 = r23;
                                r10 = -1;
                                r15 = r13;
                                r13 = r34;
                                break;
                            }
                        case 6:
                        case 13:
                            r13 = r30;
                            r23 = r5;
                            r19 = 1048575;
                            r15 = r20;
                            r20 = r7;
                            r7 = r2;
                            if (r3 != 5) {
                                r2 = r4;
                                r29 = r7;
                                r7 = r20;
                                r18 = -1;
                                break;
                            } else {
                                r7.putInt(r14, r8, com.google.android.gms.internal.measurement.zzis.zzb(r12, r4));
                                r0 = r4 + 4;
                                r6 |= r10;
                                r9 = r7;
                                r2 = r15;
                                r8 = r19;
                                r7 = r20;
                                r1 = r23;
                                r10 = -1;
                                r15 = r13;
                                r13 = r34;
                                break;
                            }
                        case 7:
                            r13 = r30;
                            r23 = r5;
                            r19 = 1048575;
                            r15 = r20;
                            r20 = r7;
                            r7 = r2;
                            if (r3 != 0) {
                                r2 = r4;
                                r29 = r7;
                                r7 = r20;
                                r18 = -1;
                                break;
                            } else {
                                r0 = com.google.android.gms.internal.measurement.zzis.zzm(r12, r4, r11);
                                com.google.android.gms.internal.measurement.zzmy.zzm(r14, r8, r11.zzb != 0);
                                r6 |= r10;
                                r9 = r7;
                                r2 = r15;
                                r8 = r19;
                                r7 = r20;
                                r1 = r23;
                                r10 = -1;
                                r15 = r13;
                                r13 = r34;
                                break;
                            }
                        case 8:
                            r13 = r30;
                            r23 = r5;
                            r19 = 1048575;
                            r15 = r20;
                            r20 = r7;
                            r7 = r2;
                            if (r3 != 2) {
                                r2 = r4;
                                r29 = r7;
                                r7 = r20;
                                r18 = -1;
                                break;
                            } else {
                                if ((r22 & 536870912) == 0) {
                                    r0 = com.google.android.gms.internal.measurement.zzis.zzg(r12, r4, r11);
                                } else {
                                    r0 = com.google.android.gms.internal.measurement.zzis.zzh(r12, r4, r11);
                                }
                                r7.putObject(r14, r8, r11.zzc);
                                r6 |= r10;
                                r9 = r7;
                                r2 = r15;
                                r8 = r19;
                                r7 = r20;
                                r1 = r23;
                                r10 = -1;
                                r15 = r13;
                                r13 = r34;
                                break;
                            }
                        case 9:
                            r23 = r5;
                            r15 = r20;
                            r20 = r7;
                            r7 = r2;
                            if (r3 != 2) {
                                r2 = r4;
                                r29 = r7;
                                r7 = r20;
                                r18 = -1;
                                break;
                            } else {
                                r13 = r30;
                                r19 = 1048575;
                                java.lang.Object r8 = r13.zzG(r14, r15);
                                r0 = com.google.android.gms.internal.measurement.zzis.zzo(r8, r13.zzE(r15), r32, r4, r34, r35);
                                r13.zzO(r14, r15, r8);
                                r6 |= r10;
                                r9 = r7;
                                r2 = r15;
                                r8 = r19;
                                r7 = r20;
                                r1 = r23;
                                r10 = -1;
                                r15 = r13;
                                r13 = r34;
                                break;
                            }
                        case 10:
                            r23 = r5;
                            r15 = r20;
                            r20 = r7;
                            r7 = r2;
                            if (r3 == 2) {
                                r0 = com.google.android.gms.internal.measurement.zzis.zza(r12, r4, r11);
                                r7.putObject(r14, r8, r11.zzc);
                                r6 |= r10;
                                r13 = r34;
                                r9 = r7;
                                r2 = r15;
                                r7 = r20;
                                r1 = r23;
                                r8 = 1048575;
                                r10 = -1;
                                r15 = r30;
                                break;
                            }
                            r2 = r4;
                            r29 = r7;
                            r7 = r20;
                            r18 = -1;
                            break;
                        case 12:
                            r23 = r5;
                            r15 = r20;
                            r20 = r7;
                            r7 = r2;
                            if (r3 == 0) {
                                r0 = com.google.android.gms.internal.measurement.zzis.zzj(r12, r4, r11);
                                r7.putInt(r14, r8, r11.zza);
                                r6 |= r10;
                                r13 = r34;
                                r9 = r7;
                                r2 = r15;
                                r7 = r20;
                                r1 = r23;
                                r8 = 1048575;
                                r10 = -1;
                                r15 = r30;
                                break;
                            }
                            r2 = r4;
                            r29 = r7;
                            r7 = r20;
                            r18 = -1;
                            break;
                        case 15:
                            r23 = r5;
                            r15 = r20;
                            r20 = r7;
                            r7 = r2;
                            if (r3 == 0) {
                                r0 = com.google.android.gms.internal.measurement.zzis.zzj(r12, r4, r11);
                                r7.putInt(r14, r8, com.google.android.gms.internal.measurement.zzji.zzb(r11.zza));
                                r6 |= r10;
                                r13 = r34;
                                r9 = r7;
                                r2 = r15;
                                r7 = r20;
                                r1 = r23;
                                r8 = 1048575;
                                r10 = -1;
                                r15 = r30;
                                break;
                            }
                            r2 = r4;
                            r29 = r7;
                            r7 = r20;
                            r18 = -1;
                            break;
                        case 16:
                            if (r3 != 0) {
                                r23 = r5;
                                r15 = r20;
                                r20 = r7;
                                r7 = r2;
                                r2 = r4;
                                r29 = r7;
                                r7 = r20;
                                r18 = -1;
                                break;
                            } else {
                                int r13 = com.google.android.gms.internal.measurement.zzis.zzm(r12, r4, r11);
                                r2.putLong(r31, r8, com.google.android.gms.internal.measurement.zzji.zzc(r11.zzb));
                                r6 |= r10;
                                r9 = r2;
                                r0 = r13;
                                r2 = r20;
                                r7 = r7;
                                r1 = r5;
                                r8 = 1048575;
                                r10 = -1;
                                r15 = r30;
                                r13 = r34;
                                break;
                            }
                        default:
                            r23 = r5;
                            r15 = r20;
                            r20 = r7;
                            r7 = r2;
                            r2 = r4;
                            r29 = r7;
                            r7 = r20;
                            r18 = -1;
                            break;
                    }
                } else {
                    r23 = r5;
                    int r20 = r7;
                    com.google.android.gms.internal.measurement.zzlp<T> r10 = r15;
                    r15 = r2;
                    if (r13 != 27) {
                        if (r13 <= 49) {
                            int r24 = r4;
                            r25 = r6;
                            r26 = r20;
                            r29 = r19;
                            r18 = -1;
                            r0 = zzv(r31, r32, r4, r34, r17, r23, r3, r15, r1, r13, r8, r35);
                        } else {
                            r14 = r4;
                            r25 = r6;
                            r29 = r19;
                            r26 = r20;
                            r18 = -1;
                            if (r13 != 50) {
                                r0 = zzt(r31, r32, r14, r34, r17, r23, r3, r1, r13, r8, r15, r35);
                            } else if (r3 == 2) {
                                r0 = zzs(r31, r32, r14, r34, r15, r8, r35);
                            }
                        }
                        r15 = r30;
                    } else if (r3 == 2) {
                        com.google.android.gms.internal.measurement.zzkm r0 = (com.google.android.gms.internal.measurement.zzkm) r19.getObject(r14, r8);
                        if (!r0.zzc()) {
                            int r1 = r0.size();
                            r0 = r0.zzd(r1 == 0 ? 10 : r1 + r1);
                            r19.putObject(r14, r8, r0);
                        }
                        r0 = com.google.android.gms.internal.measurement.zzis.zze(r10.zzE(r15), r17, r32, r4, r34, r0, r35);
                        r13 = r34;
                        r9 = r19;
                        r6 = r6;
                        r2 = r15;
                        r8 = 1048575;
                        r7 = r20;
                        r1 = r23;
                        r15 = r10;
                        r10 = -1;
                    } else {
                        r14 = r4;
                        r25 = r6;
                        r29 = r19;
                        r26 = r20;
                        r18 = -1;
                    }
                    r2 = r14;
                    r6 = r25;
                    r7 = r26;
                }
                r9 = r29;
                r8 = 1048575;
                r15 = r30;
            }
            r0 = com.google.android.gms.internal.measurement.zzis.zzi(r17, r32, r2, r34, zzd(r31), r35);
            r14 = r31;
            r12 = r32;
            r13 = r34;
            r11 = r35;
            r2 = r15;
            r10 = r18;
            r1 = r23;
            r9 = r29;
            r8 = 1048575;
            r15 = r30;
        }
        int r25 = r6;
        sun.misc.Unsafe r29 = r9;
        if (r7 != r8) {
            r29.putInt(r31, r7, r25);
        }
        if (r0 == r34) {
            return r0;
        }
        throw com.google.android.gms.internal.measurement.zzkp.zze();
    }

    /* JADX WARN: Removed duplicated region for block: B:115:0x0216  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x014b  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x01c8  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:113:0x0213 -> B:114:0x0214). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:60:0x0148 -> B:61:0x0149). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:93:0x01c5 -> B:94:0x01c6). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final int zzv(java.lang.Object r16, byte[] r17, int r18, int r19, int r20, int r21, int r22, int r23, long r24, int r26, long r27, com.google.android.gms.internal.measurement.zzir r29) throws java.io.IOException {
        int r1;
        int r1;
        int r1;
        int r1;
        int r2;
        int r8;
        int r4 = r18;
        sun.misc.Unsafe r11 = com.google.android.gms.internal.measurement.zzlp.zzb;
        com.google.android.gms.internal.measurement.zzkm r12 = (com.google.android.gms.internal.measurement.zzkm) r11.getObject(r16, r27);
        if (!r12.zzc()) {
            int r13 = r12.size();
            r12 = r12.zzd(r13 == 0 ? 10 : r13 + r13);
            r11.putObject(r16, r27, r12);
        }
        switch (r26) {
            case 18:
            case 35:
                if (r22 == 2) {
                    com.google.android.gms.internal.measurement.zzjo r12 = (com.google.android.gms.internal.measurement.zzjo) r12;
                    int r1 = com.google.android.gms.internal.measurement.zzis.zzj(r17, r4, r29);
                    int r2 = r29.zza + r1;
                    while (r1 < r2) {
                        r12.zze(java.lang.Double.longBitsToDouble(com.google.android.gms.internal.measurement.zzis.zzp(r17, r1)));
                        r1 += 8;
                    }
                    if (r1 == r2) {
                        return r1;
                    }
                    throw com.google.android.gms.internal.measurement.zzkp.zzf();
                }
                if (r22 == 1) {
                    com.google.android.gms.internal.measurement.zzjo r12 = (com.google.android.gms.internal.measurement.zzjo) r12;
                    r12.zze(java.lang.Double.longBitsToDouble(com.google.android.gms.internal.measurement.zzis.zzp(r17, r18)));
                    while (true) {
                        r1 = r4 + 8;
                        if (r1 < r19) {
                            r4 = com.google.android.gms.internal.measurement.zzis.zzj(r17, r1, r29);
                            if (r20 == r29.zza) {
                                r12.zze(java.lang.Double.longBitsToDouble(com.google.android.gms.internal.measurement.zzis.zzp(r17, r4)));
                            }
                        }
                    }
                    return r1;
                }
                return r4;
            case 19:
            case 36:
                if (r22 == 2) {
                    com.google.android.gms.internal.measurement.zzjy r12 = (com.google.android.gms.internal.measurement.zzjy) r12;
                    int r1 = com.google.android.gms.internal.measurement.zzis.zzj(r17, r4, r29);
                    int r2 = r29.zza + r1;
                    while (r1 < r2) {
                        r12.zze(java.lang.Float.intBitsToFloat(com.google.android.gms.internal.measurement.zzis.zzb(r17, r1)));
                        r1 += 4;
                    }
                    if (r1 == r2) {
                        return r1;
                    }
                    throw com.google.android.gms.internal.measurement.zzkp.zzf();
                }
                if (r22 == 5) {
                    com.google.android.gms.internal.measurement.zzjy r12 = (com.google.android.gms.internal.measurement.zzjy) r12;
                    r12.zze(java.lang.Float.intBitsToFloat(com.google.android.gms.internal.measurement.zzis.zzb(r17, r18)));
                    while (true) {
                        r1 = r4 + 4;
                        if (r1 < r19) {
                            r4 = com.google.android.gms.internal.measurement.zzis.zzj(r17, r1, r29);
                            if (r20 == r29.zza) {
                                r12.zze(java.lang.Float.intBitsToFloat(com.google.android.gms.internal.measurement.zzis.zzb(r17, r4)));
                            }
                        }
                    }
                    return r1;
                }
                return r4;
            case 20:
            case 21:
            case 37:
            case 38:
                if (r22 == 2) {
                    com.google.android.gms.internal.measurement.zzlb r12 = (com.google.android.gms.internal.measurement.zzlb) r12;
                    int r1 = com.google.android.gms.internal.measurement.zzis.zzj(r17, r4, r29);
                    int r2 = r29.zza + r1;
                    while (r1 < r2) {
                        r1 = com.google.android.gms.internal.measurement.zzis.zzm(r17, r1, r29);
                        r12.zzg(r29.zzb);
                    }
                    if (r1 == r2) {
                        return r1;
                    }
                    throw com.google.android.gms.internal.measurement.zzkp.zzf();
                }
                if (r22 == 0) {
                    com.google.android.gms.internal.measurement.zzlb r12 = (com.google.android.gms.internal.measurement.zzlb) r12;
                    int r1 = com.google.android.gms.internal.measurement.zzis.zzm(r17, r4, r29);
                    r12.zzg(r29.zzb);
                    while (r1 < r19) {
                        int r4 = com.google.android.gms.internal.measurement.zzis.zzj(r17, r1, r29);
                        if (r20 != r29.zza) {
                            return r1;
                        }
                        r1 = com.google.android.gms.internal.measurement.zzis.zzm(r17, r4, r29);
                        r12.zzg(r29.zzb);
                    }
                    return r1;
                }
                return r4;
            case 22:
            case 29:
            case 39:
            case 43:
                if (r22 == 2) {
                    return com.google.android.gms.internal.measurement.zzis.zzf(r17, r4, r12, r29);
                }
                if (r22 == 0) {
                    return com.google.android.gms.internal.measurement.zzis.zzl(r20, r17, r18, r19, r12, r29);
                }
                return r4;
            case 23:
            case 32:
            case 40:
            case 46:
                if (r22 == 2) {
                    com.google.android.gms.internal.measurement.zzlb r12 = (com.google.android.gms.internal.measurement.zzlb) r12;
                    int r1 = com.google.android.gms.internal.measurement.zzis.zzj(r17, r4, r29);
                    int r2 = r29.zza + r1;
                    while (r1 < r2) {
                        r12.zzg(com.google.android.gms.internal.measurement.zzis.zzp(r17, r1));
                        r1 += 8;
                    }
                    if (r1 == r2) {
                        return r1;
                    }
                    throw com.google.android.gms.internal.measurement.zzkp.zzf();
                }
                if (r22 == 1) {
                    com.google.android.gms.internal.measurement.zzlb r12 = (com.google.android.gms.internal.measurement.zzlb) r12;
                    r12.zzg(com.google.android.gms.internal.measurement.zzis.zzp(r17, r18));
                    while (true) {
                        r1 = r4 + 8;
                        if (r1 < r19) {
                            r4 = com.google.android.gms.internal.measurement.zzis.zzj(r17, r1, r29);
                            if (r20 == r29.zza) {
                                r12.zzg(com.google.android.gms.internal.measurement.zzis.zzp(r17, r4));
                            }
                        }
                    }
                    return r1;
                }
                return r4;
            case 24:
            case 31:
            case 41:
            case 45:
                if (r22 == 2) {
                    com.google.android.gms.internal.measurement.zzkg r12 = (com.google.android.gms.internal.measurement.zzkg) r12;
                    int r1 = com.google.android.gms.internal.measurement.zzis.zzj(r17, r4, r29);
                    int r2 = r29.zza + r1;
                    while (r1 < r2) {
                        r12.zzh(com.google.android.gms.internal.measurement.zzis.zzb(r17, r1));
                        r1 += 4;
                    }
                    if (r1 == r2) {
                        return r1;
                    }
                    throw com.google.android.gms.internal.measurement.zzkp.zzf();
                }
                if (r22 == 5) {
                    com.google.android.gms.internal.measurement.zzkg r12 = (com.google.android.gms.internal.measurement.zzkg) r12;
                    r12.zzh(com.google.android.gms.internal.measurement.zzis.zzb(r17, r18));
                    while (true) {
                        r1 = r4 + 4;
                        if (r1 < r19) {
                            r4 = com.google.android.gms.internal.measurement.zzis.zzj(r17, r1, r29);
                            if (r20 == r29.zza) {
                                r12.zzh(com.google.android.gms.internal.measurement.zzis.zzb(r17, r4));
                            }
                        }
                    }
                    return r1;
                }
                return r4;
            case 25:
            case 42:
                if (r22 == 2) {
                    com.google.android.gms.internal.measurement.zzit r12 = (com.google.android.gms.internal.measurement.zzit) r12;
                    r2 = com.google.android.gms.internal.measurement.zzis.zzj(r17, r4, r29);
                    int r4 = r29.zza + r2;
                    while (r2 < r4) {
                        r2 = com.google.android.gms.internal.measurement.zzis.zzm(r17, r2, r29);
                        r12.zze(r29.zzb != 0);
                    }
                    if (r2 != r4) {
                        throw com.google.android.gms.internal.measurement.zzkp.zzf();
                    }
                    return r2;
                }
                if (r22 == 0) {
                    com.google.android.gms.internal.measurement.zzit r12 = (com.google.android.gms.internal.measurement.zzit) r12;
                    int r4 = com.google.android.gms.internal.measurement.zzis.zzm(r17, r4, r29);
                    r12.zze(r29.zzb != 0);
                    while (r4 < r19) {
                        int r6 = com.google.android.gms.internal.measurement.zzis.zzj(r17, r4, r29);
                        if (r20 != r29.zza) {
                            return r4;
                        }
                        r4 = com.google.android.gms.internal.measurement.zzis.zzm(r17, r6, r29);
                        r12.zze(r29.zzb != 0);
                    }
                    return r4;
                }
                return r4;
            case 26:
                if (r22 == 2) {
                    if ((r24 & 536870912) != 0) {
                        int r1 = com.google.android.gms.internal.measurement.zzis.zzj(r17, r4, r29);
                        int r4 = r29.zza;
                        if (r4 >= 0) {
                            if (r4 == 0) {
                                r12.add("");
                                while (r1 < r19) {
                                    int r4 = com.google.android.gms.internal.measurement.zzis.zzj(r17, r1, r29);
                                    if (r20 != r29.zza) {
                                        return r1;
                                    }
                                    r1 = com.google.android.gms.internal.measurement.zzis.zzj(r17, r4, r29);
                                    int r4 = r29.zza;
                                    if (r4 < 0) {
                                        throw com.google.android.gms.internal.measurement.zzkp.zzd();
                                    }
                                    if (r4 == 0) {
                                        r12.add("");
                                    } else {
                                        r8 = r1 + r4;
                                        if (com.google.android.gms.internal.measurement.zznd.zzf(r17, r1, r8)) {
                                            r12.add(new java.lang.String(r17, r1, r4, com.google.android.gms.internal.measurement.zzkn.zzb));
                                            r1 = r8;
                                            while (r1 < r19) {
                                            }
                                        } else {
                                            throw com.google.android.gms.internal.measurement.zzkp.zzc();
                                        }
                                    }
                                }
                                return r1;
                            }
                            r8 = r1 + r4;
                            if (!com.google.android.gms.internal.measurement.zznd.zzf(r17, r1, r8)) {
                                throw com.google.android.gms.internal.measurement.zzkp.zzc();
                            }
                            r12.add(new java.lang.String(r17, r1, r4, com.google.android.gms.internal.measurement.zzkn.zzb));
                            r1 = r8;
                            while (r1 < r19) {
                            }
                            return r1;
                        }
                        throw com.google.android.gms.internal.measurement.zzkp.zzd();
                    }
                    int r1 = com.google.android.gms.internal.measurement.zzis.zzj(r17, r4, r29);
                    int r4 = r29.zza;
                    if (r4 >= 0) {
                        if (r4 == 0) {
                            r12.add("");
                            while (r1 < r19) {
                                int r4 = com.google.android.gms.internal.measurement.zzis.zzj(r17, r1, r29);
                                if (r20 != r29.zza) {
                                    return r1;
                                }
                                r1 = com.google.android.gms.internal.measurement.zzis.zzj(r17, r4, r29);
                                r4 = r29.zza;
                                if (r4 < 0) {
                                    throw com.google.android.gms.internal.measurement.zzkp.zzd();
                                }
                                if (r4 == 0) {
                                    r12.add("");
                                } else {
                                    r12.add(new java.lang.String(r17, r1, r4, com.google.android.gms.internal.measurement.zzkn.zzb));
                                    r1 += r4;
                                    while (r1 < r19) {
                                    }
                                }
                            }
                            return r1;
                        }
                        r12.add(new java.lang.String(r17, r1, r4, com.google.android.gms.internal.measurement.zzkn.zzb));
                        r1 += r4;
                        while (r1 < r19) {
                        }
                        return r1;
                    }
                    throw com.google.android.gms.internal.measurement.zzkp.zzd();
                }
                return r4;
            case 27:
                if (r22 == 2) {
                    return com.google.android.gms.internal.measurement.zzis.zze(zzE(r23), r20, r17, r18, r19, r12, r29);
                }
                return r4;
            case 28:
                if (r22 == 2) {
                    int r1 = com.google.android.gms.internal.measurement.zzis.zzj(r17, r4, r29);
                    int r4 = r29.zza;
                    if (r4 < 0) {
                        throw com.google.android.gms.internal.measurement.zzkp.zzd();
                    }
                    if (r4 <= r17.length - r1) {
                        if (r4 == 0) {
                            r12.add(com.google.android.gms.internal.measurement.zzje.zzb);
                            while (r1 < r19) {
                                int r4 = com.google.android.gms.internal.measurement.zzis.zzj(r17, r1, r29);
                                if (r20 != r29.zza) {
                                    return r1;
                                }
                                r1 = com.google.android.gms.internal.measurement.zzis.zzj(r17, r4, r29);
                                r4 = r29.zza;
                                if (r4 >= 0) {
                                    if (r4 > r17.length - r1) {
                                        throw com.google.android.gms.internal.measurement.zzkp.zzf();
                                    }
                                    if (r4 == 0) {
                                        r12.add(com.google.android.gms.internal.measurement.zzje.zzb);
                                    } else {
                                        r12.add(com.google.android.gms.internal.measurement.zzje.zzl(r17, r1, r4));
                                        r1 += r4;
                                        while (r1 < r19) {
                                        }
                                    }
                                } else {
                                    throw com.google.android.gms.internal.measurement.zzkp.zzd();
                                }
                            }
                            return r1;
                        }
                        r12.add(com.google.android.gms.internal.measurement.zzje.zzl(r17, r1, r4));
                        r1 += r4;
                        while (r1 < r19) {
                        }
                        return r1;
                    }
                    throw com.google.android.gms.internal.measurement.zzkp.zzf();
                }
                return r4;
            case 30:
            case 44:
                if (r22 != 2) {
                    if (r22 == 0) {
                        r2 = com.google.android.gms.internal.measurement.zzis.zzl(r20, r17, r18, r19, r12, r29);
                    }
                    return r4;
                }
                r2 = com.google.android.gms.internal.measurement.zzis.zzf(r17, r4, r12, r29);
                com.google.android.gms.internal.measurement.zzlz.zzC(r16, r21, r12, zzD(r23), null, r15.zzn);
                return r2;
            case 33:
            case 47:
                if (r22 == 2) {
                    com.google.android.gms.internal.measurement.zzkg r12 = (com.google.android.gms.internal.measurement.zzkg) r12;
                    int r1 = com.google.android.gms.internal.measurement.zzis.zzj(r17, r4, r29);
                    int r2 = r29.zza + r1;
                    while (r1 < r2) {
                        r1 = com.google.android.gms.internal.measurement.zzis.zzj(r17, r1, r29);
                        r12.zzh(com.google.android.gms.internal.measurement.zzji.zzb(r29.zza));
                    }
                    if (r1 == r2) {
                        return r1;
                    }
                    throw com.google.android.gms.internal.measurement.zzkp.zzf();
                }
                if (r22 == 0) {
                    com.google.android.gms.internal.measurement.zzkg r12 = (com.google.android.gms.internal.measurement.zzkg) r12;
                    int r1 = com.google.android.gms.internal.measurement.zzis.zzj(r17, r4, r29);
                    r12.zzh(com.google.android.gms.internal.measurement.zzji.zzb(r29.zza));
                    while (r1 < r19) {
                        int r4 = com.google.android.gms.internal.measurement.zzis.zzj(r17, r1, r29);
                        if (r20 != r29.zza) {
                            return r1;
                        }
                        r1 = com.google.android.gms.internal.measurement.zzis.zzj(r17, r4, r29);
                        r12.zzh(com.google.android.gms.internal.measurement.zzji.zzb(r29.zza));
                    }
                    return r1;
                }
                return r4;
            case 34:
            case 48:
                if (r22 == 2) {
                    com.google.android.gms.internal.measurement.zzlb r12 = (com.google.android.gms.internal.measurement.zzlb) r12;
                    int r1 = com.google.android.gms.internal.measurement.zzis.zzj(r17, r4, r29);
                    int r2 = r29.zza + r1;
                    while (r1 < r2) {
                        r1 = com.google.android.gms.internal.measurement.zzis.zzm(r17, r1, r29);
                        r12.zzg(com.google.android.gms.internal.measurement.zzji.zzc(r29.zzb));
                    }
                    if (r1 == r2) {
                        return r1;
                    }
                    throw com.google.android.gms.internal.measurement.zzkp.zzf();
                }
                if (r22 == 0) {
                    com.google.android.gms.internal.measurement.zzlb r12 = (com.google.android.gms.internal.measurement.zzlb) r12;
                    int r1 = com.google.android.gms.internal.measurement.zzis.zzm(r17, r4, r29);
                    r12.zzg(com.google.android.gms.internal.measurement.zzji.zzc(r29.zzb));
                    while (r1 < r19) {
                        int r4 = com.google.android.gms.internal.measurement.zzis.zzj(r17, r1, r29);
                        if (r20 != r29.zza) {
                            return r1;
                        }
                        r1 = com.google.android.gms.internal.measurement.zzis.zzm(r17, r4, r29);
                        r12.zzg(com.google.android.gms.internal.measurement.zzji.zzc(r29.zzb));
                    }
                    return r1;
                }
                return r4;
            default:
                if (r22 == 3) {
                    com.google.android.gms.internal.measurement.zzlx r1 = zzE(r23);
                    int r6 = (r20 & (-8)) | 4;
                    int r4 = com.google.android.gms.internal.measurement.zzis.zzc(r1, r17, r18, r19, r6, r29);
                    r12.add(r29.zzc);
                    while (r4 < r19) {
                        int r8 = com.google.android.gms.internal.measurement.zzis.zzj(r17, r4, r29);
                        if (r20 != r29.zza) {
                            return r4;
                        }
                        r4 = com.google.android.gms.internal.measurement.zzis.zzc(r1, r17, r8, r19, r6, r29);
                        r12.add(r29.zzc);
                    }
                    return r4;
                }
                return r4;
        }
    }

    private final int zzw(int i) {
        if (i < this.zze || i > this.zzf) {
            return -1;
        }
        return zzz(i, 0);
    }

    private final int zzx(int i, int i2) {
        if (i < this.zze || i > this.zzf) {
            return -1;
        }
        return zzz(i, i2);
    }

    private final int zzy(int i) {
        return this.zzc[i + 2];
    }

    private final int zzz(int i, int i2) {
        int length = (this.zzc.length / 3) - 1;
        while (i2 <= length) {
            int i3 = (length + i2) >>> 1;
            int i4 = i3 * 3;
            int i5 = this.zzc[i4];
            if (i == i5) {
                return i4;
            }
            if (i < i5) {
                length = i3 - 1;
            } else {
                i2 = i3 + 1;
            }
        }
        return -1;
    }

    @Override // com.google.android.gms.internal.measurement.zzlx
    public final int zza(Object obj) {
        return this.zzi ? zzq(obj) : zzp(obj);
    }

    @Override // com.google.android.gms.internal.measurement.zzlx
    public final int zzb(Object obj) {
        int i;
        int zzc;
        int length = this.zzc.length;
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3 += 3) {
            int zzB = zzB(i3);
            int i4 = this.zzc[i3];
            long j = 1048575 & zzB;
            int i5 = 37;
            switch (zzA(zzB)) {
                case 0:
                    i = i2 * 53;
                    zzc = zzkn.zzc(Double.doubleToLongBits(zzmy.zza(obj, j)));
                    i2 = i + zzc;
                    break;
                case 1:
                    i = i2 * 53;
                    zzc = Float.floatToIntBits(zzmy.zzb(obj, j));
                    i2 = i + zzc;
                    break;
                case 2:
                    i = i2 * 53;
                    zzc = zzkn.zzc(zzmy.zzd(obj, j));
                    i2 = i + zzc;
                    break;
                case 3:
                    i = i2 * 53;
                    zzc = zzkn.zzc(zzmy.zzd(obj, j));
                    i2 = i + zzc;
                    break;
                case 4:
                    i = i2 * 53;
                    zzc = zzmy.zzc(obj, j);
                    i2 = i + zzc;
                    break;
                case 5:
                    i = i2 * 53;
                    zzc = zzkn.zzc(zzmy.zzd(obj, j));
                    i2 = i + zzc;
                    break;
                case 6:
                    i = i2 * 53;
                    zzc = zzmy.zzc(obj, j);
                    i2 = i + zzc;
                    break;
                case 7:
                    i = i2 * 53;
                    zzc = zzkn.zza(zzmy.zzw(obj, j));
                    i2 = i + zzc;
                    break;
                case 8:
                    i = i2 * 53;
                    zzc = ((String) zzmy.zzf(obj, j)).hashCode();
                    i2 = i + zzc;
                    break;
                case 9:
                    Object zzf = zzmy.zzf(obj, j);
                    if (zzf != null) {
                        i5 = zzf.hashCode();
                    }
                    i2 = (i2 * 53) + i5;
                    break;
                case 10:
                    i = i2 * 53;
                    zzc = zzmy.zzf(obj, j).hashCode();
                    i2 = i + zzc;
                    break;
                case 11:
                    i = i2 * 53;
                    zzc = zzmy.zzc(obj, j);
                    i2 = i + zzc;
                    break;
                case 12:
                    i = i2 * 53;
                    zzc = zzmy.zzc(obj, j);
                    i2 = i + zzc;
                    break;
                case 13:
                    i = i2 * 53;
                    zzc = zzmy.zzc(obj, j);
                    i2 = i + zzc;
                    break;
                case 14:
                    i = i2 * 53;
                    zzc = zzkn.zzc(zzmy.zzd(obj, j));
                    i2 = i + zzc;
                    break;
                case 15:
                    i = i2 * 53;
                    zzc = zzmy.zzc(obj, j);
                    i2 = i + zzc;
                    break;
                case 16:
                    i = i2 * 53;
                    zzc = zzkn.zzc(zzmy.zzd(obj, j));
                    i2 = i + zzc;
                    break;
                case 17:
                    Object zzf2 = zzmy.zzf(obj, j);
                    if (zzf2 != null) {
                        i5 = zzf2.hashCode();
                    }
                    i2 = (i2 * 53) + i5;
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    i = i2 * 53;
                    zzc = zzmy.zzf(obj, j).hashCode();
                    i2 = i + zzc;
                    break;
                case 50:
                    i = i2 * 53;
                    zzc = zzmy.zzf(obj, j).hashCode();
                    i2 = i + zzc;
                    break;
                case 51:
                    if (zzX(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzkn.zzc(Double.doubleToLongBits(zzn(obj, j)));
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 52:
                    if (zzX(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = Float.floatToIntBits(zzo(obj, j));
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 53:
                    if (zzX(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzkn.zzc(zzC(obj, j));
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 54:
                    if (zzX(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzkn.zzc(zzC(obj, j));
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 55:
                    if (zzX(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzr(obj, j);
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 56:
                    if (zzX(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzkn.zzc(zzC(obj, j));
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 57:
                    if (zzX(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzr(obj, j);
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 58:
                    if (zzX(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzkn.zza(zzY(obj, j));
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 59:
                    if (zzX(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = ((String) zzmy.zzf(obj, j)).hashCode();
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 60:
                    if (zzX(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzmy.zzf(obj, j).hashCode();
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 61:
                    if (zzX(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzmy.zzf(obj, j).hashCode();
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 62:
                    if (zzX(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzr(obj, j);
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 63:
                    if (zzX(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzr(obj, j);
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 64:
                    if (zzX(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzr(obj, j);
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 65:
                    if (zzX(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzkn.zzc(zzC(obj, j));
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 66:
                    if (zzX(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzr(obj, j);
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 67:
                    if (zzX(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzkn.zzc(zzC(obj, j));
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 68:
                    if (zzX(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzmy.zzf(obj, j).hashCode();
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
            }
        }
        int hashCode = (i2 * 53) + this.zzn.zzd(obj).hashCode();
        if (this.zzh) {
            this.zzo.zza(obj);
            throw null;
        }
        return hashCode;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:147:0x041e, code lost:
        if (r6 == 1048575) goto L36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:148:0x0420, code lost:
        r28.putInt(r12, r6, r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:149:0x0426, code lost:
        r3 = r9.zzk;
     */
    /* JADX WARN: Code restructure failed: missing block: B:151:0x042a, code lost:
        if (r3 >= r9.zzl) goto L50;
     */
    /* JADX WARN: Code restructure failed: missing block: B:152:0x042c, code lost:
        r4 = r9.zzj[r3];
        r5 = r9.zzc[r4];
        r5 = com.google.android.gms.internal.measurement.zzmy.zzf(r12, r9.zzB(r4) & 1048575);
     */
    /* JADX WARN: Code restructure failed: missing block: B:153:0x043e, code lost:
        if (r5 != null) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:156:0x0445, code lost:
        if (r9.zzD(r4) != null) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:157:0x0447, code lost:
        r3 = r3 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:158:0x044a, code lost:
        r5 = (com.google.android.gms.internal.measurement.zzlg) r5;
        r0 = (com.google.android.gms.internal.measurement.zzlf) r9.zzF(r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:159:0x0452, code lost:
        throw null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:160:0x0453, code lost:
        if (r7 != 0) goto L57;
     */
    /* JADX WARN: Code restructure failed: missing block: B:162:0x0457, code lost:
        if (r0 != r33) goto L54;
     */
    /* JADX WARN: Code restructure failed: missing block: B:165:0x045e, code lost:
        throw com.google.android.gms.internal.measurement.zzkp.zze();
     */
    /* JADX WARN: Code restructure failed: missing block: B:167:0x0461, code lost:
        if (r0 > r33) goto L60;
     */
    /* JADX WARN: Code restructure failed: missing block: B:168:0x0463, code lost:
        if (r1 != r7) goto L60;
     */
    /* JADX WARN: Code restructure failed: missing block: B:169:0x0465, code lost:
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:171:0x046a, code lost:
        throw com.google.android.gms.internal.measurement.zzkp.zze();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int zzc(java.lang.Object r30, byte[] r31, int r32, int r33, int r34, com.google.android.gms.internal.measurement.zzir r35) throws java.io.IOException {
        sun.misc.Unsafe r28;
        int r7;
        java.lang.Object r12;
        com.google.android.gms.internal.measurement.zzlp<T> r9;
        byte r4;
        int r2;
        int r20;
        int r2;
        int r8;
        int r26;
        int r21;
        java.lang.Object r12;
        com.google.android.gms.internal.measurement.zzir r10;
        int r11;
        int r18;
        int r27;
        int r13;
        int r11;
        int r6;
        int r6;
        int r5;
        int r1;
        int r18;
        int r15;
        com.google.android.gms.internal.measurement.zzlp<T> r15 = r29;
        java.lang.Object r14 = r30;
        byte[] r12 = r31;
        int r13 = r33;
        int r11 = r34;
        com.google.android.gms.internal.measurement.zzir r9 = r35;
        zzJ(r30);
        sun.misc.Unsafe r10 = com.google.android.gms.internal.measurement.zzlp.zzb;
        int r0 = r32;
        int r1 = 0;
        int r3 = 0;
        int r5 = 0;
        int r2 = -1;
        int r6 = 1048575;
        while (true) {
            if (r0 < r13) {
                int r1 = r0 + 1;
                byte r0 = r12[r0];
                if (r0 < 0) {
                    int r0 = com.google.android.gms.internal.measurement.zzis.zzk(r0, r12, r1, r9);
                    r4 = r9.zza;
                    r1 = r0;
                } else {
                    r4 = r0;
                }
                int r0 = r4 >>> 3;
                int r7 = r4 & 7;
                if (r0 > r2) {
                    r2 = r15.zzx(r0, r3 / 3);
                } else {
                    r2 = r15.zzw(r0);
                }
                if (r2 == -1) {
                    r20 = r0;
                    r2 = r1;
                    r8 = r4;
                    r26 = r5;
                    r28 = r10;
                    r7 = r11;
                    r21 = 0;
                } else {
                    int[] r3 = r15.zzc;
                    int r8 = r3[r2 + 1];
                    int r11 = zzA(r8);
                    int r21 = r1;
                    long r0 = r8 & 1048575;
                    if (r11 <= 17) {
                        int r0 = r3[r2 + 2];
                        int r24 = 1 << (r0 >>> 20);
                        int r0 = r0 & 1048575;
                        if (r0 != r6) {
                            r18 = r4;
                            if (r6 != 1048575) {
                                r10.putInt(r14, r6, r5);
                            }
                            r27 = r0;
                            r26 = r10.getInt(r14, r0);
                        } else {
                            r18 = r4;
                            r26 = r5;
                            r27 = r6;
                        }
                        switch (r11) {
                            case 0:
                                r13 = r2;
                                r11 = r0;
                                r6 = r21;
                                if (r7 != 1) {
                                    r8 = r18;
                                    r7 = r34;
                                    r2 = r6;
                                    r28 = r10;
                                    r20 = r11;
                                    r21 = r13;
                                    r6 = r27;
                                    break;
                                } else {
                                    com.google.android.gms.internal.measurement.zzmy.zzo(r14, r0, java.lang.Double.longBitsToDouble(com.google.android.gms.internal.measurement.zzis.zzp(r12, r6)));
                                    r0 = r6 + 8;
                                    r5 = r26 | r24;
                                    r2 = r11;
                                    r3 = r13;
                                    r1 = r18;
                                    break;
                                }
                            case 1:
                                r13 = r2;
                                r11 = r0;
                                r6 = r21;
                                if (r7 != 5) {
                                    r8 = r18;
                                    r7 = r34;
                                    r2 = r6;
                                    r28 = r10;
                                    r20 = r11;
                                    r21 = r13;
                                    r6 = r27;
                                    break;
                                } else {
                                    com.google.android.gms.internal.measurement.zzmy.zzp(r14, r0, java.lang.Float.intBitsToFloat(com.google.android.gms.internal.measurement.zzis.zzb(r12, r6)));
                                    r0 = r6 + 4;
                                    r5 = r26 | r24;
                                    r2 = r11;
                                    r3 = r13;
                                    r1 = r18;
                                    break;
                                }
                            case 2:
                            case 3:
                                r13 = r2;
                                r11 = r0;
                                r6 = r21;
                                if (r7 != 0) {
                                    r8 = r18;
                                    r7 = r34;
                                    r2 = r6;
                                    r28 = r10;
                                    r20 = r11;
                                    r21 = r13;
                                    r6 = r27;
                                    break;
                                } else {
                                    int r6 = com.google.android.gms.internal.measurement.zzis.zzm(r12, r6, r9);
                                    r10.putLong(r30, r0, r9.zzb);
                                    r5 = r26 | r24;
                                    r0 = r6;
                                    r2 = r11;
                                    r3 = r13;
                                    r1 = r18;
                                    break;
                                }
                            case 4:
                            case 11:
                                r13 = r2;
                                r11 = r0;
                                r6 = r21;
                                if (r7 != 0) {
                                    r8 = r18;
                                    r7 = r34;
                                    r2 = r6;
                                    r28 = r10;
                                    r20 = r11;
                                    r21 = r13;
                                    r6 = r27;
                                    break;
                                } else {
                                    r0 = com.google.android.gms.internal.measurement.zzis.zzj(r12, r6, r9);
                                    r10.putInt(r14, r0, r9.zza);
                                    r5 = r26 | r24;
                                    r2 = r11;
                                    r3 = r13;
                                    r1 = r18;
                                    break;
                                }
                            case 5:
                            case 14:
                                r13 = r2;
                                int r6 = r18;
                                r11 = r0;
                                if (r7 != 1) {
                                    r18 = r6;
                                    r6 = r21;
                                    r8 = r18;
                                    r7 = r34;
                                    r2 = r6;
                                    r28 = r10;
                                    r20 = r11;
                                    r21 = r13;
                                    r6 = r27;
                                    break;
                                } else {
                                    r18 = r6;
                                    r6 = r21;
                                    r10.putLong(r30, r0, com.google.android.gms.internal.measurement.zzis.zzp(r12, r21));
                                    r0 = r6 + 8;
                                    r5 = r26 | r24;
                                    r2 = r11;
                                    r3 = r13;
                                    r1 = r18;
                                    break;
                                }
                            case 6:
                            case 13:
                                r13 = r2;
                                r6 = r18;
                                r11 = r0;
                                r5 = r21;
                                if (r7 != 5) {
                                    r8 = r6;
                                    r6 = r5;
                                    r7 = r34;
                                    r2 = r6;
                                    r28 = r10;
                                    r20 = r11;
                                    r21 = r13;
                                    r6 = r27;
                                    break;
                                } else {
                                    r10.putInt(r14, r0, com.google.android.gms.internal.measurement.zzis.zzb(r12, r5));
                                    r0 = r5 + 4;
                                    r5 = r26 | r24;
                                    r1 = r6;
                                    r2 = r11;
                                    r3 = r13;
                                    break;
                                }
                            case 7:
                                r13 = r2;
                                r6 = r18;
                                r11 = r0;
                                r5 = r21;
                                if (r7 != 0) {
                                    r8 = r6;
                                    r6 = r5;
                                    r7 = r34;
                                    r2 = r6;
                                    r28 = r10;
                                    r20 = r11;
                                    r21 = r13;
                                    r6 = r27;
                                    break;
                                } else {
                                    r0 = com.google.android.gms.internal.measurement.zzis.zzm(r12, r5, r9);
                                    com.google.android.gms.internal.measurement.zzmy.zzm(r14, r0, r9.zzb != 0);
                                    r5 = r26 | r24;
                                    r1 = r6;
                                    r2 = r11;
                                    r3 = r13;
                                    break;
                                }
                            case 8:
                                r13 = r2;
                                r6 = r18;
                                r11 = r0;
                                r5 = r21;
                                if (r7 != 2) {
                                    r8 = r6;
                                    r6 = r5;
                                    r7 = r34;
                                    r2 = r6;
                                    r28 = r10;
                                    r20 = r11;
                                    r21 = r13;
                                    r6 = r27;
                                    break;
                                } else {
                                    if ((536870912 & r8) == 0) {
                                        r0 = com.google.android.gms.internal.measurement.zzis.zzg(r12, r5, r9);
                                    } else {
                                        r0 = com.google.android.gms.internal.measurement.zzis.zzh(r12, r5, r9);
                                    }
                                    r10.putObject(r14, r0, r9.zzc);
                                    r5 = r26 | r24;
                                    r1 = r6;
                                    r2 = r11;
                                    r3 = r13;
                                    break;
                                }
                            case 9:
                                r13 = r2;
                                r8 = r18;
                                r11 = r0;
                                r1 = r21;
                                if (r7 != 2) {
                                    r6 = r1;
                                    r7 = r34;
                                    r2 = r6;
                                    r28 = r10;
                                    r20 = r11;
                                    r21 = r13;
                                    r6 = r27;
                                    break;
                                } else {
                                    java.lang.Object r6 = r15.zzG(r14, r13);
                                    r0 = com.google.android.gms.internal.measurement.zzis.zzo(r6, r15.zzE(r13), r31, r1, r33, r35);
                                    r15.zzO(r14, r13, r6);
                                    r5 = r26 | r24;
                                    r1 = r8;
                                    r2 = r11;
                                    r3 = r13;
                                    break;
                                }
                            case 10:
                                r13 = r2;
                                r8 = r18;
                                r11 = r0;
                                r1 = r21;
                                if (r7 != 2) {
                                    r6 = r1;
                                    r7 = r34;
                                    r2 = r6;
                                    r28 = r10;
                                    r20 = r11;
                                    r21 = r13;
                                    r6 = r27;
                                    break;
                                } else {
                                    r0 = com.google.android.gms.internal.measurement.zzis.zza(r12, r1, r9);
                                    r10.putObject(r14, r0, r9.zzc);
                                    r5 = r26 | r24;
                                    r1 = r8;
                                    r2 = r11;
                                    r3 = r13;
                                    break;
                                }
                            case 12:
                                r13 = r2;
                                r8 = r18;
                                r11 = r0;
                                r1 = r21;
                                if (r7 != 0) {
                                    r6 = r1;
                                    r7 = r34;
                                    r2 = r6;
                                    r28 = r10;
                                    r20 = r11;
                                    r21 = r13;
                                    r6 = r27;
                                    break;
                                } else {
                                    r0 = com.google.android.gms.internal.measurement.zzis.zzj(r12, r1, r9);
                                    int r1 = r9.zza;
                                    com.google.android.gms.internal.measurement.zzkj r2 = r15.zzD(r13);
                                    if (r2 == null || r2.zza(r1)) {
                                        r10.putInt(r14, r0, r1);
                                        r5 = r26 | r24;
                                        r1 = r8;
                                        r2 = r11;
                                        r3 = r13;
                                        break;
                                    } else {
                                        zzd(r30).zzj(r8, java.lang.Long.valueOf(r1));
                                        r1 = r8;
                                        r2 = r11;
                                        r3 = r13;
                                        r5 = r26;
                                        break;
                                    }
                                }
                                break;
                            case 15:
                                r13 = r2;
                                r8 = r18;
                                r11 = r0;
                                r1 = r21;
                                if (r7 != 0) {
                                    r6 = r1;
                                    r7 = r34;
                                    r2 = r6;
                                    r28 = r10;
                                    r20 = r11;
                                    r21 = r13;
                                    r6 = r27;
                                    break;
                                } else {
                                    r0 = com.google.android.gms.internal.measurement.zzis.zzj(r12, r1, r9);
                                    r10.putInt(r14, r0, com.google.android.gms.internal.measurement.zzji.zzb(r9.zza));
                                    r5 = r26 | r24;
                                    r1 = r8;
                                    r2 = r11;
                                    r3 = r13;
                                    break;
                                }
                            case 16:
                                if (r7 != 0) {
                                    r13 = r2;
                                    r11 = r0;
                                    r8 = r18;
                                    r6 = r21;
                                    r7 = r34;
                                    r2 = r6;
                                    r28 = r10;
                                    r20 = r11;
                                    r21 = r13;
                                    r6 = r27;
                                    break;
                                } else {
                                    int r6 = com.google.android.gms.internal.measurement.zzis.zzm(r12, r21, r9);
                                    r11 = r0;
                                    r13 = r2;
                                    r8 = r18;
                                    r10.putLong(r30, r0, com.google.android.gms.internal.measurement.zzji.zzc(r9.zzb));
                                    r5 = r26 | r24;
                                    r0 = r6;
                                    r1 = r8;
                                    r2 = r11;
                                    r3 = r13;
                                    break;
                                }
                            default:
                                r13 = r2;
                                r11 = r0;
                                r6 = r21;
                                if (r7 != 3) {
                                    r8 = r18;
                                    r7 = r34;
                                    r2 = r6;
                                    r28 = r10;
                                    r20 = r11;
                                    r21 = r13;
                                    r6 = r27;
                                    break;
                                } else {
                                    java.lang.Object r7 = r15.zzG(r14, r13);
                                    r8 = r18;
                                    r0 = com.google.android.gms.internal.measurement.zzis.zzn(r7, r15.zzE(r13), r31, r6, r33, (r11 << 3) | 4, r35);
                                    r15.zzO(r14, r13, r7);
                                    r5 = r26 | r24;
                                    r1 = r8;
                                    r2 = r11;
                                    r3 = r13;
                                    break;
                                }
                        }
                        r6 = r27;
                        r13 = r33;
                        r11 = r34;
                    } else {
                        r13 = r2;
                        int r2 = r4;
                        r27 = r6;
                        if (r11 != 27) {
                            r26 = r5;
                            if (r11 <= 49) {
                                r18 = r2;
                                r20 = r0;
                                r28 = r10;
                                r21 = r13;
                                r0 = zzv(r30, r31, r21, r33, r2, r0, r7, r13, r8, r11, r0, r35);
                                if (r0 != r21) {
                                    r15 = r29;
                                    r14 = r30;
                                    r12 = r31;
                                    r13 = r33;
                                    r11 = r34;
                                    r9 = r35;
                                    r1 = r18;
                                    r2 = r20;
                                    r3 = r21;
                                    r5 = r26;
                                    r6 = r27;
                                    r10 = r28;
                                } else {
                                    r7 = r34;
                                    r2 = r0;
                                    r8 = r18;
                                }
                            } else {
                                r20 = r0;
                                r18 = r2;
                                r15 = r21;
                                r28 = r10;
                                r21 = r13;
                                if (r11 != 50) {
                                    r0 = zzt(r30, r31, r15, r33, r18, r20, r7, r8, r11, r0, r21, r35);
                                    if (r0 != r15) {
                                        r15 = r29;
                                        r14 = r30;
                                        r12 = r31;
                                        r13 = r33;
                                        r11 = r34;
                                        r9 = r35;
                                        r1 = r18;
                                        r2 = r20;
                                        r3 = r21;
                                        r5 = r26;
                                        r6 = r27;
                                        r10 = r28;
                                    } else {
                                        r7 = r34;
                                        r2 = r0;
                                        r8 = r18;
                                    }
                                } else if (r7 == 2) {
                                    r0 = zzs(r30, r31, r15, r33, r21, r0, r35);
                                    if (r0 != r15) {
                                        r15 = r29;
                                        r14 = r30;
                                        r12 = r31;
                                        r13 = r33;
                                        r11 = r34;
                                        r9 = r35;
                                        r1 = r18;
                                        r2 = r20;
                                        r3 = r21;
                                        r5 = r26;
                                        r6 = r27;
                                        r10 = r28;
                                    } else {
                                        r7 = r34;
                                        r2 = r0;
                                        r8 = r18;
                                    }
                                }
                            }
                            r6 = r27;
                        } else if (r7 == 2) {
                            com.google.android.gms.internal.measurement.zzkm r0 = (com.google.android.gms.internal.measurement.zzkm) r10.getObject(r14, r0);
                            if (!r0.zzc()) {
                                int r7 = r0.size();
                                r0 = r0.zzd(r7 == 0 ? 10 : r7 + r7);
                                r10.putObject(r14, r0, r0);
                            }
                            r26 = r5;
                            r0 = com.google.android.gms.internal.measurement.zzis.zze(r15.zzE(r13), r2, r31, r21, r33, r0, r35);
                            r2 = r0;
                            r1 = r2;
                            r3 = r13;
                            r5 = r26;
                            r6 = r27;
                            r13 = r33;
                            r11 = r34;
                        } else {
                            r26 = r5;
                            r20 = r0;
                            r18 = r2;
                            r15 = r21;
                            r28 = r10;
                            r21 = r13;
                        }
                        r7 = r34;
                        r2 = r15;
                        r8 = r18;
                        r6 = r27;
                    }
                }
                if (r8 != r7 || r7 == 0) {
                    if (r29.zzh) {
                        r10 = r35;
                        com.google.android.gms.internal.measurement.zzjr r0 = r10.zzd;
                        if (r0 != com.google.android.gms.internal.measurement.zzjr.zza) {
                            r11 = r20;
                            if (r0.zzb(r29.zzg, r11) == null) {
                                r0 = com.google.android.gms.internal.measurement.zzis.zzi(r8, r31, r2, r33, zzd(r30), r35);
                                r12 = r30;
                                r13 = r33;
                                r1 = r8;
                                r15 = r29;
                                r9 = r10;
                                r2 = r11;
                                r14 = r12;
                                r3 = r21;
                                r5 = r26;
                                r10 = r28;
                                r12 = r31;
                                r11 = r7;
                            } else {
                                com.google.android.gms.internal.measurement.zzkc r0 = (com.google.android.gms.internal.measurement.zzkc) r30;
                                throw null;
                            }
                        } else {
                            r12 = r30;
                        }
                    } else {
                        r12 = r30;
                        r10 = r35;
                    }
                    r11 = r20;
                    r0 = com.google.android.gms.internal.measurement.zzis.zzi(r8, r31, r2, r33, zzd(r30), r35);
                    r13 = r33;
                    r1 = r8;
                    r15 = r29;
                    r9 = r10;
                    r2 = r11;
                    r14 = r12;
                    r3 = r21;
                    r5 = r26;
                    r10 = r28;
                    r12 = r31;
                    r11 = r7;
                } else {
                    r9 = r29;
                    r12 = r30;
                    r0 = r2;
                    r1 = r8;
                    r5 = r26;
                }
            } else {
                r28 = r10;
                r7 = r11;
                r12 = r14;
                r9 = r15;
            }
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzlx
    public final Object zze() {
        return ((zzkf) this.zzg).zzbA();
    }

    @Override // com.google.android.gms.internal.measurement.zzlx
    public final void zzf(Object obj) {
        if (zzW(obj)) {
            if (obj instanceof zzkf) {
                zzkf zzkfVar = (zzkf) obj;
                zzkfVar.zzbM(Integer.MAX_VALUE);
                zzkfVar.zzb = 0;
                zzkfVar.zzbK();
            }
            int length = this.zzc.length;
            for (int i = 0; i < length; i += 3) {
                int zzB = zzB(i);
                long j = 1048575 & zzB;
                int zzA = zzA(zzB);
                if (zzA != 9) {
                    switch (zzA) {
                        case 17:
                            break;
                        case 18:
                        case 19:
                        case 20:
                        case 21:
                        case 22:
                        case 23:
                        case 24:
                        case 25:
                        case 26:
                        case 27:
                        case 28:
                        case 29:
                        case 30:
                        case 31:
                        case 32:
                        case 33:
                        case 34:
                        case 35:
                        case 36:
                        case 37:
                        case 38:
                        case 39:
                        case 40:
                        case 41:
                        case 42:
                        case 43:
                        case 44:
                        case 45:
                        case 46:
                        case 47:
                        case 48:
                        case 49:
                            this.zzm.zza(obj, j);
                            continue;
                        case 50:
                            Unsafe unsafe = zzb;
                            Object object = unsafe.getObject(obj, j);
                            if (object != null) {
                                ((zzlg) object).zzc();
                                unsafe.putObject(obj, j, object);
                            } else {
                                continue;
                            }
                        default:
                    }
                }
                if (zzT(obj, i)) {
                    zzE(i).zzf(zzb.getObject(obj, j));
                }
            }
            this.zzn.zzg(obj);
            if (this.zzh) {
                this.zzo.zzb(obj);
            }
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzlx
    public final void zzg(Object obj, Object obj2) {
        zzJ(obj);
        Objects.requireNonNull(obj2);
        for (int i = 0; i < this.zzc.length; i += 3) {
            int zzB = zzB(i);
            long j = 1048575 & zzB;
            int i2 = this.zzc[i];
            switch (zzA(zzB)) {
                case 0:
                    if (zzT(obj2, i)) {
                        zzmy.zzo(obj, j, zzmy.zza(obj2, j));
                        zzM(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 1:
                    if (zzT(obj2, i)) {
                        zzmy.zzp(obj, j, zzmy.zzb(obj2, j));
                        zzM(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 2:
                    if (zzT(obj2, i)) {
                        zzmy.zzr(obj, j, zzmy.zzd(obj2, j));
                        zzM(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 3:
                    if (zzT(obj2, i)) {
                        zzmy.zzr(obj, j, zzmy.zzd(obj2, j));
                        zzM(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 4:
                    if (zzT(obj2, i)) {
                        zzmy.zzq(obj, j, zzmy.zzc(obj2, j));
                        zzM(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 5:
                    if (zzT(obj2, i)) {
                        zzmy.zzr(obj, j, zzmy.zzd(obj2, j));
                        zzM(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 6:
                    if (zzT(obj2, i)) {
                        zzmy.zzq(obj, j, zzmy.zzc(obj2, j));
                        zzM(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 7:
                    if (zzT(obj2, i)) {
                        zzmy.zzm(obj, j, zzmy.zzw(obj2, j));
                        zzM(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 8:
                    if (zzT(obj2, i)) {
                        zzmy.zzs(obj, j, zzmy.zzf(obj2, j));
                        zzM(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 9:
                    zzK(obj, obj2, i);
                    break;
                case 10:
                    if (zzT(obj2, i)) {
                        zzmy.zzs(obj, j, zzmy.zzf(obj2, j));
                        zzM(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 11:
                    if (zzT(obj2, i)) {
                        zzmy.zzq(obj, j, zzmy.zzc(obj2, j));
                        zzM(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 12:
                    if (zzT(obj2, i)) {
                        zzmy.zzq(obj, j, zzmy.zzc(obj2, j));
                        zzM(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 13:
                    if (zzT(obj2, i)) {
                        zzmy.zzq(obj, j, zzmy.zzc(obj2, j));
                        zzM(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 14:
                    if (zzT(obj2, i)) {
                        zzmy.zzr(obj, j, zzmy.zzd(obj2, j));
                        zzM(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 15:
                    if (zzT(obj2, i)) {
                        zzmy.zzq(obj, j, zzmy.zzc(obj2, j));
                        zzM(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 16:
                    if (zzT(obj2, i)) {
                        zzmy.zzr(obj, j, zzmy.zzd(obj2, j));
                        zzM(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 17:
                    zzK(obj, obj2, i);
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    this.zzm.zzb(obj, obj2, j);
                    break;
                case 50:
                    zzlz.zzaa(this.zzq, obj, obj2, j);
                    break;
                case 51:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                case 58:
                case 59:
                    if (zzX(obj2, i2, i)) {
                        zzmy.zzs(obj, j, zzmy.zzf(obj2, j));
                        zzN(obj, i2, i);
                        break;
                    } else {
                        break;
                    }
                case 60:
                    zzL(obj, obj2, i);
                    break;
                case 61:
                case 62:
                case 63:
                case 64:
                case 65:
                case 66:
                case 67:
                    if (zzX(obj2, i2, i)) {
                        zzmy.zzs(obj, j, zzmy.zzf(obj2, j));
                        zzN(obj, i2, i);
                        break;
                    } else {
                        break;
                    }
                case 68:
                    zzL(obj, obj2, i);
                    break;
            }
        }
        zzlz.zzF(this.zzn, obj, obj2);
        if (this.zzh) {
            zzlz.zzE(this.zzo, obj, obj2);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzlx
    public final void zzh(Object obj, byte[] bArr, int i, int i2, zzir zzirVar) throws IOException {
        if (this.zzi) {
            zzu(obj, bArr, i, i2, zzirVar);
        } else {
            zzc(obj, bArr, i, i2, 0, zzirVar);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzlx
    public final void zzi(Object obj, zzng zzngVar) throws IOException {
        if (!this.zzi) {
            zzQ(obj, zzngVar);
        } else if (!this.zzh) {
            int length = this.zzc.length;
            for (int i = 0; i < length; i += 3) {
                int zzB = zzB(i);
                int i2 = this.zzc[i];
                switch (zzA(zzB)) {
                    case 0:
                        if (zzT(obj, i)) {
                            zzngVar.zzf(i2, zzmy.zza(obj, zzB & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 1:
                        if (zzT(obj, i)) {
                            zzngVar.zzo(i2, zzmy.zzb(obj, zzB & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 2:
                        if (zzT(obj, i)) {
                            zzngVar.zzt(i2, zzmy.zzd(obj, zzB & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 3:
                        if (zzT(obj, i)) {
                            zzngVar.zzJ(i2, zzmy.zzd(obj, zzB & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 4:
                        if (zzT(obj, i)) {
                            zzngVar.zzr(i2, zzmy.zzc(obj, zzB & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 5:
                        if (zzT(obj, i)) {
                            zzngVar.zzm(i2, zzmy.zzd(obj, zzB & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 6:
                        if (zzT(obj, i)) {
                            zzngVar.zzk(i2, zzmy.zzc(obj, zzB & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 7:
                        if (zzT(obj, i)) {
                            zzngVar.zzb(i2, zzmy.zzw(obj, zzB & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 8:
                        if (zzT(obj, i)) {
                            zzZ(i2, zzmy.zzf(obj, zzB & 1048575), zzngVar);
                            break;
                        } else {
                            break;
                        }
                    case 9:
                        if (zzT(obj, i)) {
                            zzngVar.zzv(i2, zzmy.zzf(obj, zzB & 1048575), zzE(i));
                            break;
                        } else {
                            break;
                        }
                    case 10:
                        if (zzT(obj, i)) {
                            zzngVar.zzd(i2, (zzje) zzmy.zzf(obj, zzB & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 11:
                        if (zzT(obj, i)) {
                            zzngVar.zzH(i2, zzmy.zzc(obj, zzB & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 12:
                        if (zzT(obj, i)) {
                            zzngVar.zzi(i2, zzmy.zzc(obj, zzB & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 13:
                        if (zzT(obj, i)) {
                            zzngVar.zzw(i2, zzmy.zzc(obj, zzB & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 14:
                        if (zzT(obj, i)) {
                            zzngVar.zzy(i2, zzmy.zzd(obj, zzB & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 15:
                        if (zzT(obj, i)) {
                            zzngVar.zzA(i2, zzmy.zzc(obj, zzB & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 16:
                        if (zzT(obj, i)) {
                            zzngVar.zzC(i2, zzmy.zzd(obj, zzB & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 17:
                        if (zzT(obj, i)) {
                            zzngVar.zzq(i2, zzmy.zzf(obj, zzB & 1048575), zzE(i));
                            break;
                        } else {
                            break;
                        }
                    case 18:
                        zzlz.zzJ(i2, (List) zzmy.zzf(obj, zzB & 1048575), zzngVar, false);
                        break;
                    case 19:
                        zzlz.zzN(i2, (List) zzmy.zzf(obj, zzB & 1048575), zzngVar, false);
                        break;
                    case 20:
                        zzlz.zzQ(i2, (List) zzmy.zzf(obj, zzB & 1048575), zzngVar, false);
                        break;
                    case 21:
                        zzlz.zzY(i2, (List) zzmy.zzf(obj, zzB & 1048575), zzngVar, false);
                        break;
                    case 22:
                        zzlz.zzP(i2, (List) zzmy.zzf(obj, zzB & 1048575), zzngVar, false);
                        break;
                    case 23:
                        zzlz.zzM(i2, (List) zzmy.zzf(obj, zzB & 1048575), zzngVar, false);
                        break;
                    case 24:
                        zzlz.zzL(i2, (List) zzmy.zzf(obj, zzB & 1048575), zzngVar, false);
                        break;
                    case 25:
                        zzlz.zzH(i2, (List) zzmy.zzf(obj, zzB & 1048575), zzngVar, false);
                        break;
                    case 26:
                        zzlz.zzW(i2, (List) zzmy.zzf(obj, zzB & 1048575), zzngVar);
                        break;
                    case 27:
                        zzlz.zzR(i2, (List) zzmy.zzf(obj, zzB & 1048575), zzngVar, zzE(i));
                        break;
                    case 28:
                        zzlz.zzI(i2, (List) zzmy.zzf(obj, zzB & 1048575), zzngVar);
                        break;
                    case 29:
                        zzlz.zzX(i2, (List) zzmy.zzf(obj, zzB & 1048575), zzngVar, false);
                        break;
                    case 30:
                        zzlz.zzK(i2, (List) zzmy.zzf(obj, zzB & 1048575), zzngVar, false);
                        break;
                    case 31:
                        zzlz.zzS(i2, (List) zzmy.zzf(obj, zzB & 1048575), zzngVar, false);
                        break;
                    case 32:
                        zzlz.zzT(i2, (List) zzmy.zzf(obj, zzB & 1048575), zzngVar, false);
                        break;
                    case 33:
                        zzlz.zzU(i2, (List) zzmy.zzf(obj, zzB & 1048575), zzngVar, false);
                        break;
                    case 34:
                        zzlz.zzV(i2, (List) zzmy.zzf(obj, zzB & 1048575), zzngVar, false);
                        break;
                    case 35:
                        zzlz.zzJ(i2, (List) zzmy.zzf(obj, zzB & 1048575), zzngVar, true);
                        break;
                    case 36:
                        zzlz.zzN(i2, (List) zzmy.zzf(obj, zzB & 1048575), zzngVar, true);
                        break;
                    case 37:
                        zzlz.zzQ(i2, (List) zzmy.zzf(obj, zzB & 1048575), zzngVar, true);
                        break;
                    case 38:
                        zzlz.zzY(i2, (List) zzmy.zzf(obj, zzB & 1048575), zzngVar, true);
                        break;
                    case 39:
                        zzlz.zzP(i2, (List) zzmy.zzf(obj, zzB & 1048575), zzngVar, true);
                        break;
                    case 40:
                        zzlz.zzM(i2, (List) zzmy.zzf(obj, zzB & 1048575), zzngVar, true);
                        break;
                    case 41:
                        zzlz.zzL(i2, (List) zzmy.zzf(obj, zzB & 1048575), zzngVar, true);
                        break;
                    case 42:
                        zzlz.zzH(i2, (List) zzmy.zzf(obj, zzB & 1048575), zzngVar, true);
                        break;
                    case 43:
                        zzlz.zzX(i2, (List) zzmy.zzf(obj, zzB & 1048575), zzngVar, true);
                        break;
                    case 44:
                        zzlz.zzK(i2, (List) zzmy.zzf(obj, zzB & 1048575), zzngVar, true);
                        break;
                    case 45:
                        zzlz.zzS(i2, (List) zzmy.zzf(obj, zzB & 1048575), zzngVar, true);
                        break;
                    case 46:
                        zzlz.zzT(i2, (List) zzmy.zzf(obj, zzB & 1048575), zzngVar, true);
                        break;
                    case 47:
                        zzlz.zzU(i2, (List) zzmy.zzf(obj, zzB & 1048575), zzngVar, true);
                        break;
                    case 48:
                        zzlz.zzV(i2, (List) zzmy.zzf(obj, zzB & 1048575), zzngVar, true);
                        break;
                    case 49:
                        zzlz.zzO(i2, (List) zzmy.zzf(obj, zzB & 1048575), zzngVar, zzE(i));
                        break;
                    case 50:
                        zzR(zzngVar, i2, zzmy.zzf(obj, zzB & 1048575), i);
                        break;
                    case 51:
                        if (zzX(obj, i2, i)) {
                            zzngVar.zzf(i2, zzn(obj, zzB & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 52:
                        if (zzX(obj, i2, i)) {
                            zzngVar.zzo(i2, zzo(obj, zzB & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 53:
                        if (zzX(obj, i2, i)) {
                            zzngVar.zzt(i2, zzC(obj, zzB & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 54:
                        if (zzX(obj, i2, i)) {
                            zzngVar.zzJ(i2, zzC(obj, zzB & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 55:
                        if (zzX(obj, i2, i)) {
                            zzngVar.zzr(i2, zzr(obj, zzB & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 56:
                        if (zzX(obj, i2, i)) {
                            zzngVar.zzm(i2, zzC(obj, zzB & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 57:
                        if (zzX(obj, i2, i)) {
                            zzngVar.zzk(i2, zzr(obj, zzB & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 58:
                        if (zzX(obj, i2, i)) {
                            zzngVar.zzb(i2, zzY(obj, zzB & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 59:
                        if (zzX(obj, i2, i)) {
                            zzZ(i2, zzmy.zzf(obj, zzB & 1048575), zzngVar);
                            break;
                        } else {
                            break;
                        }
                    case 60:
                        if (zzX(obj, i2, i)) {
                            zzngVar.zzv(i2, zzmy.zzf(obj, zzB & 1048575), zzE(i));
                            break;
                        } else {
                            break;
                        }
                    case 61:
                        if (zzX(obj, i2, i)) {
                            zzngVar.zzd(i2, (zzje) zzmy.zzf(obj, zzB & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 62:
                        if (zzX(obj, i2, i)) {
                            zzngVar.zzH(i2, zzr(obj, zzB & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 63:
                        if (zzX(obj, i2, i)) {
                            zzngVar.zzi(i2, zzr(obj, zzB & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 64:
                        if (zzX(obj, i2, i)) {
                            zzngVar.zzw(i2, zzr(obj, zzB & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 65:
                        if (zzX(obj, i2, i)) {
                            zzngVar.zzy(i2, zzC(obj, zzB & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 66:
                        if (zzX(obj, i2, i)) {
                            zzngVar.zzA(i2, zzr(obj, zzB & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 67:
                        if (zzX(obj, i2, i)) {
                            zzngVar.zzC(i2, zzC(obj, zzB & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 68:
                        if (zzX(obj, i2, i)) {
                            zzngVar.zzq(i2, zzmy.zzf(obj, zzB & 1048575), zzE(i));
                            break;
                        } else {
                            break;
                        }
                }
            }
            zzmo zzmoVar = this.zzn;
            zzmoVar.zzi(zzmoVar.zzd(obj), zzngVar);
        } else {
            this.zzo.zza(obj);
            throw null;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzlx
    public final boolean zzj(Object obj, Object obj2) {
        boolean zzZ;
        int length = this.zzc.length;
        for (int i = 0; i < length; i += 3) {
            int zzB = zzB(i);
            long j = zzB & 1048575;
            switch (zzA(zzB)) {
                case 0:
                    if (zzS(obj, obj2, i) && Double.doubleToLongBits(zzmy.zza(obj, j)) == Double.doubleToLongBits(zzmy.zza(obj2, j))) {
                        continue;
                    }
                    return false;
                case 1:
                    if (zzS(obj, obj2, i) && Float.floatToIntBits(zzmy.zzb(obj, j)) == Float.floatToIntBits(zzmy.zzb(obj2, j))) {
                        continue;
                    }
                    return false;
                case 2:
                    if (zzS(obj, obj2, i) && zzmy.zzd(obj, j) == zzmy.zzd(obj2, j)) {
                        continue;
                    }
                    return false;
                case 3:
                    if (zzS(obj, obj2, i) && zzmy.zzd(obj, j) == zzmy.zzd(obj2, j)) {
                        continue;
                    }
                    return false;
                case 4:
                    if (zzS(obj, obj2, i) && zzmy.zzc(obj, j) == zzmy.zzc(obj2, j)) {
                        continue;
                    }
                    return false;
                case 5:
                    if (zzS(obj, obj2, i) && zzmy.zzd(obj, j) == zzmy.zzd(obj2, j)) {
                        continue;
                    }
                    return false;
                case 6:
                    if (zzS(obj, obj2, i) && zzmy.zzc(obj, j) == zzmy.zzc(obj2, j)) {
                        continue;
                    }
                    return false;
                case 7:
                    if (zzS(obj, obj2, i) && zzmy.zzw(obj, j) == zzmy.zzw(obj2, j)) {
                        continue;
                    }
                    return false;
                case 8:
                    if (zzS(obj, obj2, i) && zzlz.zzZ(zzmy.zzf(obj, j), zzmy.zzf(obj2, j))) {
                        continue;
                    }
                    return false;
                case 9:
                    if (zzS(obj, obj2, i) && zzlz.zzZ(zzmy.zzf(obj, j), zzmy.zzf(obj2, j))) {
                        continue;
                    }
                    return false;
                case 10:
                    if (zzS(obj, obj2, i) && zzlz.zzZ(zzmy.zzf(obj, j), zzmy.zzf(obj2, j))) {
                        continue;
                    }
                    return false;
                case 11:
                    if (zzS(obj, obj2, i) && zzmy.zzc(obj, j) == zzmy.zzc(obj2, j)) {
                        continue;
                    }
                    return false;
                case 12:
                    if (zzS(obj, obj2, i) && zzmy.zzc(obj, j) == zzmy.zzc(obj2, j)) {
                        continue;
                    }
                    return false;
                case 13:
                    if (zzS(obj, obj2, i) && zzmy.zzc(obj, j) == zzmy.zzc(obj2, j)) {
                        continue;
                    }
                    return false;
                case 14:
                    if (zzS(obj, obj2, i) && zzmy.zzd(obj, j) == zzmy.zzd(obj2, j)) {
                        continue;
                    }
                    return false;
                case 15:
                    if (zzS(obj, obj2, i) && zzmy.zzc(obj, j) == zzmy.zzc(obj2, j)) {
                        continue;
                    }
                    return false;
                case 16:
                    if (zzS(obj, obj2, i) && zzmy.zzd(obj, j) == zzmy.zzd(obj2, j)) {
                        continue;
                    }
                    return false;
                case 17:
                    if (zzS(obj, obj2, i) && zzlz.zzZ(zzmy.zzf(obj, j), zzmy.zzf(obj2, j))) {
                        continue;
                    }
                    return false;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    zzZ = zzlz.zzZ(zzmy.zzf(obj, j), zzmy.zzf(obj2, j));
                    break;
                case 50:
                    zzZ = zzlz.zzZ(zzmy.zzf(obj, j), zzmy.zzf(obj2, j));
                    break;
                case 51:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                case 58:
                case 59:
                case 60:
                case 61:
                case 62:
                case 63:
                case 64:
                case 65:
                case 66:
                case 67:
                case 68:
                    long zzy = zzy(i) & 1048575;
                    if (zzmy.zzc(obj, zzy) == zzmy.zzc(obj2, zzy) && zzlz.zzZ(zzmy.zzf(obj, j), zzmy.zzf(obj2, j))) {
                        continue;
                    }
                    return false;
                default:
            }
            if (!zzZ) {
                return false;
            }
        }
        if (this.zzn.zzd(obj).equals(this.zzn.zzd(obj2))) {
            if (this.zzh) {
                this.zzo.zza(obj);
                this.zzo.zza(obj2);
                throw null;
            }
            return true;
        }
        return false;
    }

    @Override // com.google.android.gms.internal.measurement.zzlx
    public final boolean zzk(Object obj) {
        int i;
        int i2;
        int i3 = 1048575;
        int i4 = 0;
        int i5 = 0;
        while (i5 < this.zzk) {
            int i6 = this.zzj[i5];
            int i7 = this.zzc[i6];
            int zzB = zzB(i6);
            int i8 = this.zzc[i6 + 2];
            int i9 = i8 & 1048575;
            int i10 = 1 << (i8 >>> 20);
            if (i9 != i3) {
                if (i9 != 1048575) {
                    i4 = zzb.getInt(obj, i9);
                }
                i2 = i4;
                i = i9;
            } else {
                i = i3;
                i2 = i4;
            }
            if ((268435456 & zzB) != 0 && !zzU(obj, i6, i, i2, i10)) {
                return false;
            }
            int zzA = zzA(zzB);
            if (zzA != 9 && zzA != 17) {
                if (zzA != 27) {
                    if (zzA == 60 || zzA == 68) {
                        if (zzX(obj, i7, i6) && !zzV(obj, zzB, zzE(i6))) {
                            return false;
                        }
                    } else if (zzA != 49) {
                        if (zzA == 50 && !((zzlg) zzmy.zzf(obj, zzB & 1048575)).isEmpty()) {
                            zzlf zzlfVar = (zzlf) zzF(i6);
                            throw null;
                        }
                    }
                }
                List list = (List) zzmy.zzf(obj, zzB & 1048575);
                if (list.isEmpty()) {
                    continue;
                } else {
                    zzlx zzE = zzE(i6);
                    for (int i11 = 0; i11 < list.size(); i11++) {
                        if (!zzE.zzk(list.get(i11))) {
                            return false;
                        }
                    }
                    continue;
                }
            } else if (zzU(obj, i6, i, i2, i10) && !zzV(obj, zzB, zzE(i6))) {
                return false;
            }
            i5++;
            i3 = i;
            i4 = i2;
        }
        if (this.zzh) {
            this.zzo.zza(obj);
            throw null;
        }
        return true;
    }
}
