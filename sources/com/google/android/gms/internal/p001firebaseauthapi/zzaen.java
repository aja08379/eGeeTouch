package com.google.android.gms.internal.p001firebaseauthapi;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import sun.misc.Unsafe;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaen  reason: invalid package */
/* loaded from: classes.dex */
public final class zzaen<T> implements zzaew<T> {
    private static final int[] zza = new int[0];
    private static final Unsafe zzb = zzafx.zzg();
    private final int[] zzc;
    private final Object[] zzd;
    private final int zze;
    private final int zzf;
    private final zzaek zzg;
    private final boolean zzh;
    private final boolean zzi;
    private final boolean zzj;
    private final int[] zzk;
    private final int zzl;
    private final int zzm;
    private final zzady zzn;
    private final zzafn zzo;
    private final zzact zzp;
    private final zzaep zzq;
    private final zzaef zzr;

    private zzaen(int[] iArr, Object[] objArr, int i, int i2, zzaek zzaekVar, boolean z, boolean z2, int[] iArr2, int i3, int i4, zzaep zzaepVar, zzady zzadyVar, zzafn zzafnVar, zzact zzactVar, zzaef zzaefVar, byte[] bArr) {
        this.zzc = iArr;
        this.zzd = objArr;
        this.zze = i;
        this.zzf = i2;
        this.zzi = zzaekVar instanceof zzadf;
        this.zzj = z;
        boolean z3 = false;
        if (zzactVar != null && zzactVar.zzh(zzaekVar)) {
            z3 = true;
        }
        this.zzh = z3;
        this.zzk = iArr2;
        this.zzl = i3;
        this.zzm = i4;
        this.zzq = zzaepVar;
        this.zzn = zzadyVar;
        this.zzo = zzafnVar;
        this.zzp = zzactVar;
        this.zzg = zzaekVar;
        this.zzr = zzaefVar;
    }

    private final int zzA(int i, int i2) {
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

    private static int zzB(int i) {
        return (i >>> 20) & 255;
    }

    private final int zzC(int i) {
        return this.zzc[i + 1];
    }

    private static long zzD(Object obj, long j) {
        return ((Long) zzafx.zzf(obj, j)).longValue();
    }

    private final zzadj zzE(int i) {
        int i2 = i / 3;
        return (zzadj) this.zzd[i2 + i2 + 1];
    }

    private final zzaew zzF(int i) {
        int i2 = i / 3;
        int i3 = i2 + i2;
        zzaew zzaewVar = (zzaew) this.zzd[i3];
        if (zzaewVar != null) {
            return zzaewVar;
        }
        zzaew zzb2 = zzaes.zza().zzb((Class) this.zzd[i3 + 1]);
        this.zzd[i3] = zzb2;
        return zzb2;
    }

    private final Object zzG(Object obj, int i, Object obj2, zzafn zzafnVar, Object obj3) {
        int i2 = this.zzc[i];
        Object zzf = zzafx.zzf(obj, zzC(i) & 1048575);
        if (zzf == null || zzE(i) == null) {
            return obj2;
        }
        zzaee zzaeeVar = (zzaee) zzf;
        zzaed zzaedVar = (zzaed) zzH(i);
        throw null;
    }

    private final Object zzH(int i) {
        int i2 = i / 3;
        return this.zzd[i2 + i2];
    }

    private final Object zzI(Object obj, int i) {
        zzaew zzF = zzF(i);
        long zzC = zzC(i) & 1048575;
        if (!zzV(obj, i)) {
            return zzF.zze();
        }
        Object object = zzb.getObject(obj, zzC);
        if (zzY(object)) {
            return object;
        }
        Object zze = zzF.zze();
        if (object != null) {
            zzF.zzg(zze, object);
        }
        return zze;
    }

    private final Object zzJ(Object obj, int i, int i2) {
        zzaew zzF = zzF(i2);
        if (!zzZ(obj, i, i2)) {
            return zzF.zze();
        }
        Object object = zzb.getObject(obj, zzC(i2) & 1048575);
        if (zzY(object)) {
            return object;
        }
        Object zze = zzF.zze();
        if (object != null) {
            zzF.zzg(zze, object);
        }
        return zze;
    }

    private static Field zzK(Class cls, String str) {
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

    private static void zzL(Object obj) {
        if (!zzY(obj)) {
            throw new IllegalArgumentException("Mutating immutable message: ".concat(String.valueOf(String.valueOf(obj))));
        }
    }

    private final void zzM(Object obj, Object obj2, int i) {
        if (zzV(obj2, i)) {
            long zzC = zzC(i) & 1048575;
            Unsafe unsafe = zzb;
            Object object = unsafe.getObject(obj2, zzC);
            if (object == null) {
                throw new IllegalStateException("Source subfield " + this.zzc[i] + " is present but null: " + obj2.toString());
            }
            zzaew zzF = zzF(i);
            if (!zzV(obj, i)) {
                if (!zzY(object)) {
                    unsafe.putObject(obj, zzC, object);
                } else {
                    Object zze = zzF.zze();
                    zzF.zzg(zze, object);
                    unsafe.putObject(obj, zzC, zze);
                }
                zzP(obj, i);
                return;
            }
            Object object2 = unsafe.getObject(obj, zzC);
            if (!zzY(object2)) {
                Object zze2 = zzF.zze();
                zzF.zzg(zze2, object2);
                unsafe.putObject(obj, zzC, zze2);
                object2 = zze2;
            }
            zzF.zzg(object2, object);
        }
    }

    private final void zzN(Object obj, Object obj2, int i) {
        int i2 = this.zzc[i];
        if (zzZ(obj2, i2, i)) {
            long zzC = zzC(i) & 1048575;
            Unsafe unsafe = zzb;
            Object object = unsafe.getObject(obj2, zzC);
            if (object == null) {
                throw new IllegalStateException("Source subfield " + this.zzc[i] + " is present but null: " + obj2.toString());
            }
            zzaew zzF = zzF(i);
            if (!zzZ(obj, i2, i)) {
                if (!zzY(object)) {
                    unsafe.putObject(obj, zzC, object);
                } else {
                    Object zze = zzF.zze();
                    zzF.zzg(zze, object);
                    unsafe.putObject(obj, zzC, zze);
                }
                zzQ(obj, i2, i);
                return;
            }
            Object object2 = unsafe.getObject(obj, zzC);
            if (!zzY(object2)) {
                Object zze2 = zzF.zze();
                zzF.zzg(zze2, object2);
                unsafe.putObject(obj, zzC, zze2);
                object2 = zze2;
            }
            zzF.zzg(object2, object);
        }
    }

    private final void zzO(Object obj, int i, zzaev zzaevVar) throws IOException {
        if (zzU(i)) {
            zzafx.zzs(obj, i & 1048575, zzaevVar.zzs());
        } else if (!this.zzi) {
            zzafx.zzs(obj, i & 1048575, zzaevVar.zzp());
        } else {
            zzafx.zzs(obj, i & 1048575, zzaevVar.zzr());
        }
    }

    private final void zzP(Object obj, int i) {
        int zzz = zzz(i);
        long j = 1048575 & zzz;
        if (j == 1048575) {
            return;
        }
        zzafx.zzq(obj, j, (1 << (zzz >>> 20)) | zzafx.zzc(obj, j));
    }

    private final void zzQ(Object obj, int i, int i2) {
        zzafx.zzq(obj, zzz(i2) & 1048575, i);
    }

    private final void zzR(Object obj, int i, Object obj2) {
        zzb.putObject(obj, zzC(i) & 1048575, obj2);
        zzP(obj, i);
    }

    private final void zzS(Object obj, int i, int i2, Object obj2) {
        zzb.putObject(obj, zzC(i2) & 1048575, obj2);
        zzQ(obj, i, i2);
    }

    private final boolean zzT(Object obj, Object obj2, int i) {
        return zzV(obj, i) == zzV(obj2, i);
    }

    private static boolean zzU(int i) {
        return (i & 536870912) != 0;
    }

    private final boolean zzV(Object obj, int i) {
        int zzz = zzz(i);
        long j = zzz & 1048575;
        if (j != 1048575) {
            return (zzafx.zzc(obj, j) & (1 << (zzz >>> 20))) != 0;
        }
        int zzC = zzC(i);
        long j2 = zzC & 1048575;
        switch (zzB(zzC)) {
            case 0:
                return Double.doubleToRawLongBits(zzafx.zza(obj, j2)) != 0;
            case 1:
                return Float.floatToRawIntBits(zzafx.zzb(obj, j2)) != 0;
            case 2:
                return zzafx.zzd(obj, j2) != 0;
            case 3:
                return zzafx.zzd(obj, j2) != 0;
            case 4:
                return zzafx.zzc(obj, j2) != 0;
            case 5:
                return zzafx.zzd(obj, j2) != 0;
            case 6:
                return zzafx.zzc(obj, j2) != 0;
            case 7:
                return zzafx.zzw(obj, j2);
            case 8:
                Object zzf = zzafx.zzf(obj, j2);
                if (zzf instanceof String) {
                    return !((String) zzf).isEmpty();
                } else if (zzf instanceof zzacc) {
                    return !zzacc.zzb.equals(zzf);
                } else {
                    throw new IllegalArgumentException();
                }
            case 9:
                return zzafx.zzf(obj, j2) != null;
            case 10:
                return !zzacc.zzb.equals(zzafx.zzf(obj, j2));
            case 11:
                return zzafx.zzc(obj, j2) != 0;
            case 12:
                return zzafx.zzc(obj, j2) != 0;
            case 13:
                return zzafx.zzc(obj, j2) != 0;
            case 14:
                return zzafx.zzd(obj, j2) != 0;
            case 15:
                return zzafx.zzc(obj, j2) != 0;
            case 16:
                return zzafx.zzd(obj, j2) != 0;
            case 17:
                return zzafx.zzf(obj, j2) != null;
            default:
                throw new IllegalArgumentException();
        }
    }

    private final boolean zzW(Object obj, int i, int i2, int i3, int i4) {
        if (i2 == 1048575) {
            return zzV(obj, i);
        }
        return (i3 & i4) != 0;
    }

    private static boolean zzX(Object obj, int i, zzaew zzaewVar) {
        return zzaewVar.zzk(zzafx.zzf(obj, i & 1048575));
    }

    private static boolean zzY(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof zzadf) {
            return ((zzadf) obj).zzK();
        }
        return true;
    }

    private final boolean zzZ(Object obj, int i, int i2) {
        return zzafx.zzc(obj, (long) (zzz(i2) & 1048575)) == i;
    }

    private static boolean zzaa(Object obj, long j) {
        return ((Boolean) zzafx.zzf(obj, j)).booleanValue();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private final void zzab(Object obj, zzaco zzacoVar) throws IOException {
        int i;
        boolean z;
        if (this.zzh) {
            this.zzp.zza(obj);
            throw null;
        }
        int length = this.zzc.length;
        Unsafe unsafe = zzb;
        int i2 = 1048575;
        int i3 = 1048575;
        int i4 = 0;
        int i5 = 0;
        while (i4 < length) {
            int zzC = zzC(i4);
            int[] iArr = this.zzc;
            int i6 = iArr[i4];
            int zzB = zzB(zzC);
            if (zzB <= 17) {
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
            long j = zzC & i2;
            switch (zzB) {
                case 0:
                    if ((i5 & i) == 0) {
                        break;
                    } else {
                        zzacoVar.zzf(i6, zzafx.zza(obj, j));
                        break;
                    }
                case 1:
                    if ((i5 & i) == 0) {
                        break;
                    } else {
                        zzacoVar.zzo(i6, zzafx.zzb(obj, j));
                        break;
                    }
                case 2:
                    if ((i5 & i) == 0) {
                        break;
                    } else {
                        zzacoVar.zzt(i6, unsafe.getLong(obj, j));
                        break;
                    }
                case 3:
                    if ((i5 & i) == 0) {
                        break;
                    } else {
                        zzacoVar.zzJ(i6, unsafe.getLong(obj, j));
                        break;
                    }
                case 4:
                    if ((i5 & i) == 0) {
                        break;
                    } else {
                        zzacoVar.zzr(i6, unsafe.getInt(obj, j));
                        break;
                    }
                case 5:
                    if ((i5 & i) == 0) {
                        break;
                    } else {
                        zzacoVar.zzm(i6, unsafe.getLong(obj, j));
                        break;
                    }
                case 6:
                    if ((i5 & i) == 0) {
                        break;
                    } else {
                        zzacoVar.zzk(i6, unsafe.getInt(obj, j));
                        break;
                    }
                case 7:
                    if ((i5 & i) == 0) {
                        break;
                    } else {
                        zzacoVar.zzb(i6, zzafx.zzw(obj, j));
                        break;
                    }
                case 8:
                    if ((i5 & i) == 0) {
                        break;
                    } else {
                        zzad(i6, unsafe.getObject(obj, j), zzacoVar);
                        break;
                    }
                case 9:
                    if ((i5 & i) == 0) {
                        break;
                    } else {
                        zzacoVar.zzv(i6, unsafe.getObject(obj, j), zzF(i4));
                        break;
                    }
                case 10:
                    if ((i5 & i) == 0) {
                        break;
                    } else {
                        zzacoVar.zzd(i6, (zzacc) unsafe.getObject(obj, j));
                        break;
                    }
                case 11:
                    if ((i5 & i) == 0) {
                        break;
                    } else {
                        zzacoVar.zzH(i6, unsafe.getInt(obj, j));
                        break;
                    }
                case 12:
                    if ((i5 & i) == 0) {
                        break;
                    } else {
                        zzacoVar.zzi(i6, unsafe.getInt(obj, j));
                        break;
                    }
                case 13:
                    if ((i5 & i) == 0) {
                        break;
                    } else {
                        zzacoVar.zzw(i6, unsafe.getInt(obj, j));
                        break;
                    }
                case 14:
                    if ((i5 & i) == 0) {
                        break;
                    } else {
                        zzacoVar.zzy(i6, unsafe.getLong(obj, j));
                        break;
                    }
                case 15:
                    if ((i5 & i) == 0) {
                        break;
                    } else {
                        zzacoVar.zzA(i6, unsafe.getInt(obj, j));
                        break;
                    }
                case 16:
                    if ((i5 & i) == 0) {
                        break;
                    } else {
                        zzacoVar.zzC(i6, unsafe.getLong(obj, j));
                        break;
                    }
                case 17:
                    if ((i5 & i) == 0) {
                        break;
                    } else {
                        zzacoVar.zzq(i6, unsafe.getObject(obj, j), zzF(i4));
                        break;
                    }
                case 18:
                    zzaey.zzL(this.zzc[i4], (List) unsafe.getObject(obj, j), zzacoVar, false);
                    break;
                case 19:
                    zzaey.zzP(this.zzc[i4], (List) unsafe.getObject(obj, j), zzacoVar, false);
                    break;
                case 20:
                    zzaey.zzS(this.zzc[i4], (List) unsafe.getObject(obj, j), zzacoVar, false);
                    break;
                case 21:
                    zzaey.zzaa(this.zzc[i4], (List) unsafe.getObject(obj, j), zzacoVar, false);
                    break;
                case 22:
                    zzaey.zzR(this.zzc[i4], (List) unsafe.getObject(obj, j), zzacoVar, false);
                    break;
                case 23:
                    zzaey.zzO(this.zzc[i4], (List) unsafe.getObject(obj, j), zzacoVar, false);
                    break;
                case 24:
                    zzaey.zzN(this.zzc[i4], (List) unsafe.getObject(obj, j), zzacoVar, false);
                    break;
                case 25:
                    zzaey.zzJ(this.zzc[i4], (List) unsafe.getObject(obj, j), zzacoVar, false);
                    break;
                case 26:
                    zzaey.zzY(this.zzc[i4], (List) unsafe.getObject(obj, j), zzacoVar);
                    break;
                case 27:
                    zzaey.zzT(this.zzc[i4], (List) unsafe.getObject(obj, j), zzacoVar, zzF(i4));
                    break;
                case 28:
                    zzaey.zzK(this.zzc[i4], (List) unsafe.getObject(obj, j), zzacoVar);
                    break;
                case 29:
                    z = false;
                    zzaey.zzZ(this.zzc[i4], (List) unsafe.getObject(obj, j), zzacoVar, false);
                    break;
                case 30:
                    z = false;
                    zzaey.zzM(this.zzc[i4], (List) unsafe.getObject(obj, j), zzacoVar, false);
                    break;
                case 31:
                    z = false;
                    zzaey.zzU(this.zzc[i4], (List) unsafe.getObject(obj, j), zzacoVar, false);
                    break;
                case 32:
                    z = false;
                    zzaey.zzV(this.zzc[i4], (List) unsafe.getObject(obj, j), zzacoVar, false);
                    break;
                case 33:
                    z = false;
                    zzaey.zzW(this.zzc[i4], (List) unsafe.getObject(obj, j), zzacoVar, false);
                    break;
                case 34:
                    z = false;
                    zzaey.zzX(this.zzc[i4], (List) unsafe.getObject(obj, j), zzacoVar, false);
                    break;
                case 35:
                    zzaey.zzL(this.zzc[i4], (List) unsafe.getObject(obj, j), zzacoVar, true);
                    break;
                case 36:
                    zzaey.zzP(this.zzc[i4], (List) unsafe.getObject(obj, j), zzacoVar, true);
                    break;
                case 37:
                    zzaey.zzS(this.zzc[i4], (List) unsafe.getObject(obj, j), zzacoVar, true);
                    break;
                case 38:
                    zzaey.zzaa(this.zzc[i4], (List) unsafe.getObject(obj, j), zzacoVar, true);
                    break;
                case 39:
                    zzaey.zzR(this.zzc[i4], (List) unsafe.getObject(obj, j), zzacoVar, true);
                    break;
                case 40:
                    zzaey.zzO(this.zzc[i4], (List) unsafe.getObject(obj, j), zzacoVar, true);
                    break;
                case 41:
                    zzaey.zzN(this.zzc[i4], (List) unsafe.getObject(obj, j), zzacoVar, true);
                    break;
                case 42:
                    zzaey.zzJ(this.zzc[i4], (List) unsafe.getObject(obj, j), zzacoVar, true);
                    break;
                case 43:
                    zzaey.zzZ(this.zzc[i4], (List) unsafe.getObject(obj, j), zzacoVar, true);
                    break;
                case 44:
                    zzaey.zzM(this.zzc[i4], (List) unsafe.getObject(obj, j), zzacoVar, true);
                    break;
                case 45:
                    zzaey.zzU(this.zzc[i4], (List) unsafe.getObject(obj, j), zzacoVar, true);
                    break;
                case 46:
                    zzaey.zzV(this.zzc[i4], (List) unsafe.getObject(obj, j), zzacoVar, true);
                    break;
                case 47:
                    zzaey.zzW(this.zzc[i4], (List) unsafe.getObject(obj, j), zzacoVar, true);
                    break;
                case 48:
                    zzaey.zzX(this.zzc[i4], (List) unsafe.getObject(obj, j), zzacoVar, true);
                    break;
                case 49:
                    zzaey.zzQ(this.zzc[i4], (List) unsafe.getObject(obj, j), zzacoVar, zzF(i4));
                    break;
                case 50:
                    zzac(zzacoVar, i6, unsafe.getObject(obj, j), i4);
                    break;
                case 51:
                    if (zzZ(obj, i6, i4)) {
                        zzacoVar.zzf(i6, zzo(obj, j));
                    }
                    break;
                case 52:
                    if (zzZ(obj, i6, i4)) {
                        zzacoVar.zzo(i6, zzp(obj, j));
                    }
                    break;
                case 53:
                    if (zzZ(obj, i6, i4)) {
                        zzacoVar.zzt(i6, zzD(obj, j));
                    }
                    break;
                case 54:
                    if (zzZ(obj, i6, i4)) {
                        zzacoVar.zzJ(i6, zzD(obj, j));
                    }
                    break;
                case 55:
                    if (zzZ(obj, i6, i4)) {
                        zzacoVar.zzr(i6, zzs(obj, j));
                    }
                    break;
                case 56:
                    if (zzZ(obj, i6, i4)) {
                        zzacoVar.zzm(i6, zzD(obj, j));
                    }
                    break;
                case 57:
                    if (zzZ(obj, i6, i4)) {
                        zzacoVar.zzk(i6, zzs(obj, j));
                    }
                    break;
                case 58:
                    if (zzZ(obj, i6, i4)) {
                        zzacoVar.zzb(i6, zzaa(obj, j));
                    }
                    break;
                case 59:
                    if (zzZ(obj, i6, i4)) {
                        zzad(i6, unsafe.getObject(obj, j), zzacoVar);
                    }
                    break;
                case 60:
                    if (zzZ(obj, i6, i4)) {
                        zzacoVar.zzv(i6, unsafe.getObject(obj, j), zzF(i4));
                    }
                    break;
                case 61:
                    if (zzZ(obj, i6, i4)) {
                        zzacoVar.zzd(i6, (zzacc) unsafe.getObject(obj, j));
                    }
                    break;
                case 62:
                    if (zzZ(obj, i6, i4)) {
                        zzacoVar.zzH(i6, zzs(obj, j));
                    }
                    break;
                case 63:
                    if (zzZ(obj, i6, i4)) {
                        zzacoVar.zzi(i6, zzs(obj, j));
                    }
                    break;
                case 64:
                    if (zzZ(obj, i6, i4)) {
                        zzacoVar.zzw(i6, zzs(obj, j));
                    }
                    break;
                case 65:
                    if (zzZ(obj, i6, i4)) {
                        zzacoVar.zzy(i6, zzD(obj, j));
                    }
                    break;
                case 66:
                    if (zzZ(obj, i6, i4)) {
                        zzacoVar.zzA(i6, zzs(obj, j));
                    }
                    break;
                case 67:
                    if (zzZ(obj, i6, i4)) {
                        zzacoVar.zzC(i6, zzD(obj, j));
                    }
                    break;
                case 68:
                    if (zzZ(obj, i6, i4)) {
                        zzacoVar.zzq(i6, unsafe.getObject(obj, j), zzF(i4));
                    }
                    break;
            }
            i4 += 3;
            i2 = 1048575;
        }
        zzafn zzafnVar = this.zzo;
        zzafnVar.zzr(zzafnVar.zzd(obj), zzacoVar);
    }

    private final void zzac(zzaco zzacoVar, int i, Object obj, int i2) throws IOException {
        if (obj == null) {
            return;
        }
        zzaed zzaedVar = (zzaed) zzH(i2);
        throw null;
    }

    private static final void zzad(int i, Object obj, zzaco zzacoVar) throws IOException {
        if (obj instanceof String) {
            zzacoVar.zzF(i, (String) obj);
        } else {
            zzacoVar.zzd(i, (zzacc) obj);
        }
    }

    static zzafo zzd(Object obj) {
        zzadf zzadfVar = (zzadf) obj;
        zzafo zzafoVar = zzadfVar.zzc;
        if (zzafoVar == zzafo.zzc()) {
            zzafo zzf = zzafo.zzf();
            zzadfVar.zzc = zzf;
            return zzf;
        }
        return zzafoVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzaen zzl(Class cls, zzaeh zzaehVar, zzaep zzaepVar, zzady zzadyVar, zzafn zzafnVar, zzact zzactVar, zzaef zzaefVar) {
        if (zzaehVar instanceof zzaeu) {
            return zzm((zzaeu) zzaehVar, zzaepVar, zzadyVar, zzafnVar, zzactVar, zzaefVar);
        }
        zzafk zzafkVar = (zzafk) zzaehVar;
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
    static com.google.android.gms.internal.p001firebaseauthapi.zzaen zzm(com.google.android.gms.internal.p001firebaseauthapi.zzaeu r34, com.google.android.gms.internal.p001firebaseauthapi.zzaep r35, com.google.android.gms.internal.p001firebaseauthapi.zzady r36, com.google.android.gms.internal.p001firebaseauthapi.zzafn r37, com.google.android.gms.internal.p001firebaseauthapi.zzact r38, com.google.android.gms.internal.p001firebaseauthapi.zzaef r39) {
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
            r13 = com.google.android.gms.internal.p001firebaseauthapi.zzaen.zza;
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
        sun.misc.Unsafe r15 = com.google.android.gms.internal.p001firebaseauthapi.zzaen.zzb;
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
                        r12 = zzK(r1, (java.lang.String) r12);
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
                        r8 = zzK(r1, (java.lang.String) r8);
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
                java.lang.reflect.Field r8 = zzK(r1, (java.lang.String) r17[r16]);
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
                                    r11 = zzK(r1, (java.lang.String) r11);
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
        return new com.google.android.gms.internal.p001firebaseauthapi.zzaen(r7, r11, r8, r12, r34.zza(), r10, false, r13, r14, r21, r35, r36, r37, r38, r39, null);
    }

    private static double zzo(Object obj, long j) {
        return ((Double) zzafx.zzf(obj, j)).doubleValue();
    }

    private static float zzp(Object obj, long j) {
        return ((Float) zzafx.zzf(obj, j)).floatValue();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private final int zzq(Object obj) {
        int i;
        int zzE;
        int zzE2;
        int zzE3;
        int zzF;
        int zzE4;
        int zzy;
        int zzE5;
        int zzE6;
        int zzd;
        int zzE7;
        int i2;
        int zzu;
        boolean z;
        int zzd2;
        int zzi;
        int zzD;
        int zzE8;
        int i3;
        int zzE9;
        int zzE10;
        int zzE11;
        int zzF2;
        int zzE12;
        int zzd3;
        int zzE13;
        int i4;
        Unsafe unsafe = zzb;
        int i5 = 1048575;
        int i6 = 1048575;
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        while (i7 < this.zzc.length) {
            int zzC = zzC(i7);
            int[] iArr = this.zzc;
            int i10 = iArr[i7];
            int zzB = zzB(zzC);
            if (zzB <= 17) {
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
            long j = zzC & i5;
            switch (zzB) {
                case 0:
                    if ((i9 & i) == 0) {
                        break;
                    } else {
                        zzE = zzacn.zzE(i10 << 3);
                        zzE5 = zzE + 8;
                        i8 += zzE5;
                        break;
                    }
                case 1:
                    if ((i9 & i) == 0) {
                        break;
                    } else {
                        zzE2 = zzacn.zzE(i10 << 3);
                        zzE5 = zzE2 + 4;
                        i8 += zzE5;
                        break;
                    }
                case 2:
                    if ((i9 & i) == 0) {
                        break;
                    } else {
                        long j2 = unsafe.getLong(obj, j);
                        zzE3 = zzacn.zzE(i10 << 3);
                        zzF = zzacn.zzF(j2);
                        i8 += zzE3 + zzF;
                        break;
                    }
                case 3:
                    if ((i9 & i) == 0) {
                        break;
                    } else {
                        long j3 = unsafe.getLong(obj, j);
                        zzE3 = zzacn.zzE(i10 << 3);
                        zzF = zzacn.zzF(j3);
                        i8 += zzE3 + zzF;
                        break;
                    }
                case 4:
                    if ((i9 & i) == 0) {
                        break;
                    } else {
                        int i13 = unsafe.getInt(obj, j);
                        zzE4 = zzacn.zzE(i10 << 3);
                        zzy = zzacn.zzy(i13);
                        i2 = zzE4 + zzy;
                        i8 += i2;
                        break;
                    }
                case 5:
                    if ((i9 & i) == 0) {
                        break;
                    } else {
                        zzE = zzacn.zzE(i10 << 3);
                        zzE5 = zzE + 8;
                        i8 += zzE5;
                        break;
                    }
                case 6:
                    if ((i9 & i) == 0) {
                        break;
                    } else {
                        zzE2 = zzacn.zzE(i10 << 3);
                        zzE5 = zzE2 + 4;
                        i8 += zzE5;
                        break;
                    }
                case 7:
                    if ((i9 & i) == 0) {
                        break;
                    } else {
                        zzE5 = zzacn.zzE(i10 << 3) + 1;
                        i8 += zzE5;
                        break;
                    }
                case 8:
                    if ((i9 & i) == 0) {
                        break;
                    } else {
                        Object object = unsafe.getObject(obj, j);
                        if (object instanceof zzacc) {
                            zzE6 = zzacn.zzE(i10 << 3);
                            zzd = ((zzacc) object).zzd();
                            zzE7 = zzacn.zzE(zzd);
                            i2 = zzE6 + zzE7 + zzd;
                            i8 += i2;
                            break;
                        } else {
                            zzE4 = zzacn.zzE(i10 << 3);
                            zzy = zzacn.zzC((String) object);
                            i2 = zzE4 + zzy;
                            i8 += i2;
                        }
                    }
                case 9:
                    if ((i9 & i) == 0) {
                        break;
                    } else {
                        zzE5 = zzaey.zzo(i10, unsafe.getObject(obj, j), zzF(i7));
                        i8 += zzE5;
                        break;
                    }
                case 10:
                    if ((i9 & i) == 0) {
                        break;
                    } else {
                        zzE6 = zzacn.zzE(i10 << 3);
                        zzd = ((zzacc) unsafe.getObject(obj, j)).zzd();
                        zzE7 = zzacn.zzE(zzd);
                        i2 = zzE6 + zzE7 + zzd;
                        i8 += i2;
                        break;
                    }
                case 11:
                    if ((i9 & i) == 0) {
                        break;
                    } else {
                        int i14 = unsafe.getInt(obj, j);
                        zzE4 = zzacn.zzE(i10 << 3);
                        zzy = zzacn.zzE(i14);
                        i2 = zzE4 + zzy;
                        i8 += i2;
                        break;
                    }
                case 12:
                    if ((i9 & i) == 0) {
                        break;
                    } else {
                        int i15 = unsafe.getInt(obj, j);
                        zzE4 = zzacn.zzE(i10 << 3);
                        zzy = zzacn.zzy(i15);
                        i2 = zzE4 + zzy;
                        i8 += i2;
                        break;
                    }
                case 13:
                    if ((i9 & i) == 0) {
                        break;
                    } else {
                        zzE2 = zzacn.zzE(i10 << 3);
                        zzE5 = zzE2 + 4;
                        i8 += zzE5;
                        break;
                    }
                case 14:
                    if ((i9 & i) == 0) {
                        break;
                    } else {
                        zzE = zzacn.zzE(i10 << 3);
                        zzE5 = zzE + 8;
                        i8 += zzE5;
                        break;
                    }
                case 15:
                    if ((i9 & i) == 0) {
                        break;
                    } else {
                        int i16 = unsafe.getInt(obj, j);
                        zzE4 = zzacn.zzE(i10 << 3);
                        zzy = zzacn.zzE((i16 >> 31) ^ (i16 + i16));
                        i2 = zzE4 + zzy;
                        i8 += i2;
                        break;
                    }
                case 16:
                    if ((i & i9) == 0) {
                        break;
                    } else {
                        long j4 = unsafe.getLong(obj, j);
                        i8 += zzacn.zzE(i10 << 3) + zzacn.zzF((j4 >> 63) ^ (j4 + j4));
                        break;
                    }
                case 17:
                    if ((i9 & i) == 0) {
                        break;
                    } else {
                        zzE5 = zzacn.zzx(i10, (zzaek) unsafe.getObject(obj, j), zzF(i7));
                        i8 += zzE5;
                        break;
                    }
                case 18:
                    zzE5 = zzaey.zzh(i10, (List) unsafe.getObject(obj, j), false);
                    i8 += zzE5;
                    break;
                case 19:
                    zzE5 = zzaey.zzf(i10, (List) unsafe.getObject(obj, j), false);
                    i8 += zzE5;
                    break;
                case 20:
                    zzE5 = zzaey.zzm(i10, (List) unsafe.getObject(obj, j), false);
                    i8 += zzE5;
                    break;
                case 21:
                    zzE5 = zzaey.zzx(i10, (List) unsafe.getObject(obj, j), false);
                    i8 += zzE5;
                    break;
                case 22:
                    zzE5 = zzaey.zzk(i10, (List) unsafe.getObject(obj, j), false);
                    i8 += zzE5;
                    break;
                case 23:
                    zzE5 = zzaey.zzh(i10, (List) unsafe.getObject(obj, j), false);
                    i8 += zzE5;
                    break;
                case 24:
                    zzE5 = zzaey.zzf(i10, (List) unsafe.getObject(obj, j), false);
                    i8 += zzE5;
                    break;
                case 25:
                    zzE5 = zzaey.zza(i10, (List) unsafe.getObject(obj, j), false);
                    i8 += zzE5;
                    break;
                case 26:
                    zzu = zzaey.zzu(i10, (List) unsafe.getObject(obj, j));
                    i8 += zzu;
                    break;
                case 27:
                    zzu = zzaey.zzp(i10, (List) unsafe.getObject(obj, j), zzF(i7));
                    i8 += zzu;
                    break;
                case 28:
                    zzu = zzaey.zzc(i10, (List) unsafe.getObject(obj, j));
                    i8 += zzu;
                    break;
                case 29:
                    zzu = zzaey.zzv(i10, (List) unsafe.getObject(obj, j), false);
                    i8 += zzu;
                    break;
                case 30:
                    z = false;
                    zzd2 = zzaey.zzd(i10, (List) unsafe.getObject(obj, j), false);
                    i8 += zzd2;
                    break;
                case 31:
                    z = false;
                    zzd2 = zzaey.zzf(i10, (List) unsafe.getObject(obj, j), false);
                    i8 += zzd2;
                    break;
                case 32:
                    z = false;
                    zzd2 = zzaey.zzh(i10, (List) unsafe.getObject(obj, j), false);
                    i8 += zzd2;
                    break;
                case 33:
                    z = false;
                    zzd2 = zzaey.zzq(i10, (List) unsafe.getObject(obj, j), false);
                    i8 += zzd2;
                    break;
                case 34:
                    z = false;
                    zzd2 = zzaey.zzs(i10, (List) unsafe.getObject(obj, j), false);
                    i8 += zzd2;
                    break;
                case 35:
                    zzi = zzaey.zzi((List) unsafe.getObject(obj, j));
                    if (zzi > 0) {
                        zzD = zzacn.zzD(i10);
                        zzE8 = zzacn.zzE(zzi);
                        i3 = zzD + zzE8;
                        i4 = i3 + zzi;
                        i8 += i4;
                    }
                    break;
                case 36:
                    zzi = zzaey.zzg((List) unsafe.getObject(obj, j));
                    if (zzi > 0) {
                        zzD = zzacn.zzD(i10);
                        zzE8 = zzacn.zzE(zzi);
                        i3 = zzD + zzE8;
                        i4 = i3 + zzi;
                        i8 += i4;
                    }
                    break;
                case 37:
                    zzi = zzaey.zzn((List) unsafe.getObject(obj, j));
                    if (zzi > 0) {
                        zzD = zzacn.zzD(i10);
                        zzE8 = zzacn.zzE(zzi);
                        i3 = zzD + zzE8;
                        i4 = i3 + zzi;
                        i8 += i4;
                    }
                    break;
                case 38:
                    zzi = zzaey.zzy((List) unsafe.getObject(obj, j));
                    if (zzi > 0) {
                        zzD = zzacn.zzD(i10);
                        zzE8 = zzacn.zzE(zzi);
                        i3 = zzD + zzE8;
                        i4 = i3 + zzi;
                        i8 += i4;
                    }
                    break;
                case 39:
                    zzi = zzaey.zzl((List) unsafe.getObject(obj, j));
                    if (zzi > 0) {
                        zzD = zzacn.zzD(i10);
                        zzE8 = zzacn.zzE(zzi);
                        i3 = zzD + zzE8;
                        i4 = i3 + zzi;
                        i8 += i4;
                    }
                    break;
                case 40:
                    zzi = zzaey.zzi((List) unsafe.getObject(obj, j));
                    if (zzi > 0) {
                        zzD = zzacn.zzD(i10);
                        zzE8 = zzacn.zzE(zzi);
                        i3 = zzD + zzE8;
                        i4 = i3 + zzi;
                        i8 += i4;
                    }
                    break;
                case 41:
                    zzi = zzaey.zzg((List) unsafe.getObject(obj, j));
                    if (zzi > 0) {
                        zzD = zzacn.zzD(i10);
                        zzE8 = zzacn.zzE(zzi);
                        i3 = zzD + zzE8;
                        i4 = i3 + zzi;
                        i8 += i4;
                    }
                    break;
                case 42:
                    zzi = zzaey.zzb((List) unsafe.getObject(obj, j));
                    if (zzi > 0) {
                        zzD = zzacn.zzD(i10);
                        zzE8 = zzacn.zzE(zzi);
                        i3 = zzD + zzE8;
                        i4 = i3 + zzi;
                        i8 += i4;
                    }
                    break;
                case 43:
                    zzi = zzaey.zzw((List) unsafe.getObject(obj, j));
                    if (zzi > 0) {
                        zzD = zzacn.zzD(i10);
                        zzE8 = zzacn.zzE(zzi);
                        i3 = zzD + zzE8;
                        i4 = i3 + zzi;
                        i8 += i4;
                    }
                    break;
                case 44:
                    zzi = zzaey.zze((List) unsafe.getObject(obj, j));
                    if (zzi > 0) {
                        zzD = zzacn.zzD(i10);
                        zzE8 = zzacn.zzE(zzi);
                        i3 = zzD + zzE8;
                        i4 = i3 + zzi;
                        i8 += i4;
                    }
                    break;
                case 45:
                    zzi = zzaey.zzg((List) unsafe.getObject(obj, j));
                    if (zzi > 0) {
                        zzD = zzacn.zzD(i10);
                        zzE8 = zzacn.zzE(zzi);
                        i3 = zzD + zzE8;
                        i4 = i3 + zzi;
                        i8 += i4;
                    }
                    break;
                case 46:
                    zzi = zzaey.zzi((List) unsafe.getObject(obj, j));
                    if (zzi > 0) {
                        zzD = zzacn.zzD(i10);
                        zzE8 = zzacn.zzE(zzi);
                        i3 = zzD + zzE8;
                        i4 = i3 + zzi;
                        i8 += i4;
                    }
                    break;
                case 47:
                    zzi = zzaey.zzr((List) unsafe.getObject(obj, j));
                    if (zzi > 0) {
                        zzD = zzacn.zzD(i10);
                        zzE8 = zzacn.zzE(zzi);
                        i3 = zzD + zzE8;
                        i4 = i3 + zzi;
                        i8 += i4;
                    }
                    break;
                case 48:
                    zzi = zzaey.zzt((List) unsafe.getObject(obj, j));
                    if (zzi > 0) {
                        zzD = zzacn.zzD(i10);
                        zzE8 = zzacn.zzE(zzi);
                        i3 = zzD + zzE8;
                        i4 = i3 + zzi;
                        i8 += i4;
                    }
                    break;
                case 49:
                    zzu = zzaey.zzj(i10, (List) unsafe.getObject(obj, j), zzF(i7));
                    i8 += zzu;
                    break;
                case 50:
                    zzaef.zza(i10, unsafe.getObject(obj, j), zzH(i7));
                    break;
                case 51:
                    if (zzZ(obj, i10, i7)) {
                        zzE9 = zzacn.zzE(i10 << 3);
                        zzu = zzE9 + 8;
                        i8 += zzu;
                    }
                    break;
                case 52:
                    if (zzZ(obj, i10, i7)) {
                        zzE10 = zzacn.zzE(i10 << 3);
                        zzu = zzE10 + 4;
                        i8 += zzu;
                    }
                    break;
                case 53:
                    if (zzZ(obj, i10, i7)) {
                        long zzD2 = zzD(obj, j);
                        zzE11 = zzacn.zzE(i10 << 3);
                        zzF2 = zzacn.zzF(zzD2);
                        i8 += zzE11 + zzF2;
                    }
                    break;
                case 54:
                    if (zzZ(obj, i10, i7)) {
                        long zzD3 = zzD(obj, j);
                        zzE11 = zzacn.zzE(i10 << 3);
                        zzF2 = zzacn.zzF(zzD3);
                        i8 += zzE11 + zzF2;
                    }
                    break;
                case 55:
                    if (zzZ(obj, i10, i7)) {
                        int zzs = zzs(obj, j);
                        i3 = zzacn.zzE(i10 << 3);
                        zzi = zzacn.zzy(zzs);
                        i4 = i3 + zzi;
                        i8 += i4;
                    }
                    break;
                case 56:
                    if (zzZ(obj, i10, i7)) {
                        zzE9 = zzacn.zzE(i10 << 3);
                        zzu = zzE9 + 8;
                        i8 += zzu;
                    }
                    break;
                case 57:
                    if (zzZ(obj, i10, i7)) {
                        zzE10 = zzacn.zzE(i10 << 3);
                        zzu = zzE10 + 4;
                        i8 += zzu;
                    }
                    break;
                case 58:
                    if (zzZ(obj, i10, i7)) {
                        zzu = zzacn.zzE(i10 << 3) + 1;
                        i8 += zzu;
                    }
                    break;
                case 59:
                    if (zzZ(obj, i10, i7)) {
                        Object object2 = unsafe.getObject(obj, j);
                        if (object2 instanceof zzacc) {
                            zzE12 = zzacn.zzE(i10 << 3);
                            zzd3 = ((zzacc) object2).zzd();
                            zzE13 = zzacn.zzE(zzd3);
                            i4 = zzE12 + zzE13 + zzd3;
                            i8 += i4;
                        } else {
                            i3 = zzacn.zzE(i10 << 3);
                            zzi = zzacn.zzC((String) object2);
                            i4 = i3 + zzi;
                            i8 += i4;
                        }
                    }
                    break;
                case 60:
                    if (zzZ(obj, i10, i7)) {
                        zzu = zzaey.zzo(i10, unsafe.getObject(obj, j), zzF(i7));
                        i8 += zzu;
                    }
                    break;
                case 61:
                    if (zzZ(obj, i10, i7)) {
                        zzE12 = zzacn.zzE(i10 << 3);
                        zzd3 = ((zzacc) unsafe.getObject(obj, j)).zzd();
                        zzE13 = zzacn.zzE(zzd3);
                        i4 = zzE12 + zzE13 + zzd3;
                        i8 += i4;
                    }
                    break;
                case 62:
                    if (zzZ(obj, i10, i7)) {
                        int zzs2 = zzs(obj, j);
                        i3 = zzacn.zzE(i10 << 3);
                        zzi = zzacn.zzE(zzs2);
                        i4 = i3 + zzi;
                        i8 += i4;
                    }
                    break;
                case 63:
                    if (zzZ(obj, i10, i7)) {
                        int zzs3 = zzs(obj, j);
                        i3 = zzacn.zzE(i10 << 3);
                        zzi = zzacn.zzy(zzs3);
                        i4 = i3 + zzi;
                        i8 += i4;
                    }
                    break;
                case 64:
                    if (zzZ(obj, i10, i7)) {
                        zzE10 = zzacn.zzE(i10 << 3);
                        zzu = zzE10 + 4;
                        i8 += zzu;
                    }
                    break;
                case 65:
                    if (zzZ(obj, i10, i7)) {
                        zzE9 = zzacn.zzE(i10 << 3);
                        zzu = zzE9 + 8;
                        i8 += zzu;
                    }
                    break;
                case 66:
                    if (zzZ(obj, i10, i7)) {
                        int zzs4 = zzs(obj, j);
                        i3 = zzacn.zzE(i10 << 3);
                        zzi = zzacn.zzE((zzs4 >> 31) ^ (zzs4 + zzs4));
                        i4 = i3 + zzi;
                        i8 += i4;
                    }
                    break;
                case 67:
                    if (zzZ(obj, i10, i7)) {
                        long zzD4 = zzD(obj, j);
                        i8 += zzacn.zzE(i10 << 3) + zzacn.zzF((zzD4 >> 63) ^ (zzD4 + zzD4));
                    }
                    break;
                case 68:
                    if (zzZ(obj, i10, i7)) {
                        zzu = zzacn.zzx(i10, (zzaek) unsafe.getObject(obj, j), zzF(i7));
                        i8 += zzu;
                    }
                    break;
            }
            i7 += 3;
            i5 = 1048575;
        }
        zzafn zzafnVar = this.zzo;
        int zza2 = i8 + zzafnVar.zza(zzafnVar.zzd(obj));
        if (this.zzh) {
            this.zzp.zza(obj);
            throw null;
        }
        return zza2;
    }

    private final int zzr(Object obj) {
        int zzE;
        int zzE2;
        int zzE3;
        int zzF;
        int zzE4;
        int zzy;
        int zzE5;
        int zzE6;
        int zzd;
        int zzE7;
        int zzo;
        int zzD;
        int zzE8;
        int i;
        Unsafe unsafe = zzb;
        int i2 = 0;
        for (int i3 = 0; i3 < this.zzc.length; i3 += 3) {
            int zzC = zzC(i3);
            int zzB = zzB(zzC);
            int i4 = this.zzc[i3];
            long j = zzC & 1048575;
            if (zzB >= zzacy.DOUBLE_LIST_PACKED.zza() && zzB <= zzacy.SINT64_LIST_PACKED.zza()) {
                int i5 = this.zzc[i3 + 2];
            }
            switch (zzB) {
                case 0:
                    if (zzV(obj, i3)) {
                        zzE = zzacn.zzE(i4 << 3);
                        zzo = zzE + 8;
                        i2 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 1:
                    if (zzV(obj, i3)) {
                        zzE2 = zzacn.zzE(i4 << 3);
                        zzo = zzE2 + 4;
                        i2 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 2:
                    if (zzV(obj, i3)) {
                        long zzd2 = zzafx.zzd(obj, j);
                        zzE3 = zzacn.zzE(i4 << 3);
                        zzF = zzacn.zzF(zzd2);
                        i2 += zzE3 + zzF;
                        break;
                    } else {
                        break;
                    }
                case 3:
                    if (zzV(obj, i3)) {
                        long zzd3 = zzafx.zzd(obj, j);
                        zzE3 = zzacn.zzE(i4 << 3);
                        zzF = zzacn.zzF(zzd3);
                        i2 += zzE3 + zzF;
                        break;
                    } else {
                        break;
                    }
                case 4:
                    if (zzV(obj, i3)) {
                        int zzc = zzafx.zzc(obj, j);
                        zzE4 = zzacn.zzE(i4 << 3);
                        zzy = zzacn.zzy(zzc);
                        i = zzE4 + zzy;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 5:
                    if (zzV(obj, i3)) {
                        zzE = zzacn.zzE(i4 << 3);
                        zzo = zzE + 8;
                        i2 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 6:
                    if (zzV(obj, i3)) {
                        zzE2 = zzacn.zzE(i4 << 3);
                        zzo = zzE2 + 4;
                        i2 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 7:
                    if (zzV(obj, i3)) {
                        zzE5 = zzacn.zzE(i4 << 3);
                        zzo = zzE5 + 1;
                        i2 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 8:
                    if (!zzV(obj, i3)) {
                        break;
                    } else {
                        Object zzf = zzafx.zzf(obj, j);
                        if (zzf instanceof zzacc) {
                            zzE6 = zzacn.zzE(i4 << 3);
                            zzd = ((zzacc) zzf).zzd();
                            zzE7 = zzacn.zzE(zzd);
                            i = zzE6 + zzE7 + zzd;
                            i2 += i;
                            break;
                        } else {
                            zzE4 = zzacn.zzE(i4 << 3);
                            zzy = zzacn.zzC((String) zzf);
                            i = zzE4 + zzy;
                            i2 += i;
                        }
                    }
                case 9:
                    if (zzV(obj, i3)) {
                        zzo = zzaey.zzo(i4, zzafx.zzf(obj, j), zzF(i3));
                        i2 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 10:
                    if (zzV(obj, i3)) {
                        zzE6 = zzacn.zzE(i4 << 3);
                        zzd = ((zzacc) zzafx.zzf(obj, j)).zzd();
                        zzE7 = zzacn.zzE(zzd);
                        i = zzE6 + zzE7 + zzd;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 11:
                    if (zzV(obj, i3)) {
                        int zzc2 = zzafx.zzc(obj, j);
                        zzE4 = zzacn.zzE(i4 << 3);
                        zzy = zzacn.zzE(zzc2);
                        i = zzE4 + zzy;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 12:
                    if (zzV(obj, i3)) {
                        int zzc3 = zzafx.zzc(obj, j);
                        zzE4 = zzacn.zzE(i4 << 3);
                        zzy = zzacn.zzy(zzc3);
                        i = zzE4 + zzy;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 13:
                    if (zzV(obj, i3)) {
                        zzE2 = zzacn.zzE(i4 << 3);
                        zzo = zzE2 + 4;
                        i2 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 14:
                    if (zzV(obj, i3)) {
                        zzE = zzacn.zzE(i4 << 3);
                        zzo = zzE + 8;
                        i2 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 15:
                    if (zzV(obj, i3)) {
                        int zzc4 = zzafx.zzc(obj, j);
                        zzE4 = zzacn.zzE(i4 << 3);
                        zzy = zzacn.zzE((zzc4 >> 31) ^ (zzc4 + zzc4));
                        i = zzE4 + zzy;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 16:
                    if (zzV(obj, i3)) {
                        long zzd4 = zzafx.zzd(obj, j);
                        zzE4 = zzacn.zzE(i4 << 3);
                        zzy = zzacn.zzF((zzd4 >> 63) ^ (zzd4 + zzd4));
                        i = zzE4 + zzy;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 17:
                    if (zzV(obj, i3)) {
                        zzo = zzacn.zzx(i4, (zzaek) zzafx.zzf(obj, j), zzF(i3));
                        i2 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 18:
                    zzo = zzaey.zzh(i4, (List) zzafx.zzf(obj, j), false);
                    i2 += zzo;
                    break;
                case 19:
                    zzo = zzaey.zzf(i4, (List) zzafx.zzf(obj, j), false);
                    i2 += zzo;
                    break;
                case 20:
                    zzo = zzaey.zzm(i4, (List) zzafx.zzf(obj, j), false);
                    i2 += zzo;
                    break;
                case 21:
                    zzo = zzaey.zzx(i4, (List) zzafx.zzf(obj, j), false);
                    i2 += zzo;
                    break;
                case 22:
                    zzo = zzaey.zzk(i4, (List) zzafx.zzf(obj, j), false);
                    i2 += zzo;
                    break;
                case 23:
                    zzo = zzaey.zzh(i4, (List) zzafx.zzf(obj, j), false);
                    i2 += zzo;
                    break;
                case 24:
                    zzo = zzaey.zzf(i4, (List) zzafx.zzf(obj, j), false);
                    i2 += zzo;
                    break;
                case 25:
                    zzo = zzaey.zza(i4, (List) zzafx.zzf(obj, j), false);
                    i2 += zzo;
                    break;
                case 26:
                    zzo = zzaey.zzu(i4, (List) zzafx.zzf(obj, j));
                    i2 += zzo;
                    break;
                case 27:
                    zzo = zzaey.zzp(i4, (List) zzafx.zzf(obj, j), zzF(i3));
                    i2 += zzo;
                    break;
                case 28:
                    zzo = zzaey.zzc(i4, (List) zzafx.zzf(obj, j));
                    i2 += zzo;
                    break;
                case 29:
                    zzo = zzaey.zzv(i4, (List) zzafx.zzf(obj, j), false);
                    i2 += zzo;
                    break;
                case 30:
                    zzo = zzaey.zzd(i4, (List) zzafx.zzf(obj, j), false);
                    i2 += zzo;
                    break;
                case 31:
                    zzo = zzaey.zzf(i4, (List) zzafx.zzf(obj, j), false);
                    i2 += zzo;
                    break;
                case 32:
                    zzo = zzaey.zzh(i4, (List) zzafx.zzf(obj, j), false);
                    i2 += zzo;
                    break;
                case 33:
                    zzo = zzaey.zzq(i4, (List) zzafx.zzf(obj, j), false);
                    i2 += zzo;
                    break;
                case 34:
                    zzo = zzaey.zzs(i4, (List) zzafx.zzf(obj, j), false);
                    i2 += zzo;
                    break;
                case 35:
                    zzy = zzaey.zzi((List) unsafe.getObject(obj, j));
                    if (zzy > 0) {
                        zzD = zzacn.zzD(i4);
                        zzE8 = zzacn.zzE(zzy);
                        zzE4 = zzD + zzE8;
                        i = zzE4 + zzy;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 36:
                    zzy = zzaey.zzg((List) unsafe.getObject(obj, j));
                    if (zzy > 0) {
                        zzD = zzacn.zzD(i4);
                        zzE8 = zzacn.zzE(zzy);
                        zzE4 = zzD + zzE8;
                        i = zzE4 + zzy;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 37:
                    zzy = zzaey.zzn((List) unsafe.getObject(obj, j));
                    if (zzy > 0) {
                        zzD = zzacn.zzD(i4);
                        zzE8 = zzacn.zzE(zzy);
                        zzE4 = zzD + zzE8;
                        i = zzE4 + zzy;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 38:
                    zzy = zzaey.zzy((List) unsafe.getObject(obj, j));
                    if (zzy > 0) {
                        zzD = zzacn.zzD(i4);
                        zzE8 = zzacn.zzE(zzy);
                        zzE4 = zzD + zzE8;
                        i = zzE4 + zzy;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 39:
                    zzy = zzaey.zzl((List) unsafe.getObject(obj, j));
                    if (zzy > 0) {
                        zzD = zzacn.zzD(i4);
                        zzE8 = zzacn.zzE(zzy);
                        zzE4 = zzD + zzE8;
                        i = zzE4 + zzy;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 40:
                    zzy = zzaey.zzi((List) unsafe.getObject(obj, j));
                    if (zzy > 0) {
                        zzD = zzacn.zzD(i4);
                        zzE8 = zzacn.zzE(zzy);
                        zzE4 = zzD + zzE8;
                        i = zzE4 + zzy;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 41:
                    zzy = zzaey.zzg((List) unsafe.getObject(obj, j));
                    if (zzy > 0) {
                        zzD = zzacn.zzD(i4);
                        zzE8 = zzacn.zzE(zzy);
                        zzE4 = zzD + zzE8;
                        i = zzE4 + zzy;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 42:
                    zzy = zzaey.zzb((List) unsafe.getObject(obj, j));
                    if (zzy > 0) {
                        zzD = zzacn.zzD(i4);
                        zzE8 = zzacn.zzE(zzy);
                        zzE4 = zzD + zzE8;
                        i = zzE4 + zzy;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 43:
                    zzy = zzaey.zzw((List) unsafe.getObject(obj, j));
                    if (zzy > 0) {
                        zzD = zzacn.zzD(i4);
                        zzE8 = zzacn.zzE(zzy);
                        zzE4 = zzD + zzE8;
                        i = zzE4 + zzy;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 44:
                    zzy = zzaey.zze((List) unsafe.getObject(obj, j));
                    if (zzy > 0) {
                        zzD = zzacn.zzD(i4);
                        zzE8 = zzacn.zzE(zzy);
                        zzE4 = zzD + zzE8;
                        i = zzE4 + zzy;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 45:
                    zzy = zzaey.zzg((List) unsafe.getObject(obj, j));
                    if (zzy > 0) {
                        zzD = zzacn.zzD(i4);
                        zzE8 = zzacn.zzE(zzy);
                        zzE4 = zzD + zzE8;
                        i = zzE4 + zzy;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 46:
                    zzy = zzaey.zzi((List) unsafe.getObject(obj, j));
                    if (zzy > 0) {
                        zzD = zzacn.zzD(i4);
                        zzE8 = zzacn.zzE(zzy);
                        zzE4 = zzD + zzE8;
                        i = zzE4 + zzy;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 47:
                    zzy = zzaey.zzr((List) unsafe.getObject(obj, j));
                    if (zzy > 0) {
                        zzD = zzacn.zzD(i4);
                        zzE8 = zzacn.zzE(zzy);
                        zzE4 = zzD + zzE8;
                        i = zzE4 + zzy;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 48:
                    zzy = zzaey.zzt((List) unsafe.getObject(obj, j));
                    if (zzy > 0) {
                        zzD = zzacn.zzD(i4);
                        zzE8 = zzacn.zzE(zzy);
                        zzE4 = zzD + zzE8;
                        i = zzE4 + zzy;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 49:
                    zzo = zzaey.zzj(i4, (List) zzafx.zzf(obj, j), zzF(i3));
                    i2 += zzo;
                    break;
                case 50:
                    zzaef.zza(i4, zzafx.zzf(obj, j), zzH(i3));
                    break;
                case 51:
                    if (zzZ(obj, i4, i3)) {
                        zzE = zzacn.zzE(i4 << 3);
                        zzo = zzE + 8;
                        i2 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 52:
                    if (zzZ(obj, i4, i3)) {
                        zzE2 = zzacn.zzE(i4 << 3);
                        zzo = zzE2 + 4;
                        i2 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 53:
                    if (zzZ(obj, i4, i3)) {
                        long zzD2 = zzD(obj, j);
                        zzE3 = zzacn.zzE(i4 << 3);
                        zzF = zzacn.zzF(zzD2);
                        i2 += zzE3 + zzF;
                        break;
                    } else {
                        break;
                    }
                case 54:
                    if (zzZ(obj, i4, i3)) {
                        long zzD3 = zzD(obj, j);
                        zzE3 = zzacn.zzE(i4 << 3);
                        zzF = zzacn.zzF(zzD3);
                        i2 += zzE3 + zzF;
                        break;
                    } else {
                        break;
                    }
                case 55:
                    if (zzZ(obj, i4, i3)) {
                        int zzs = zzs(obj, j);
                        zzE4 = zzacn.zzE(i4 << 3);
                        zzy = zzacn.zzy(zzs);
                        i = zzE4 + zzy;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 56:
                    if (zzZ(obj, i4, i3)) {
                        zzE = zzacn.zzE(i4 << 3);
                        zzo = zzE + 8;
                        i2 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 57:
                    if (zzZ(obj, i4, i3)) {
                        zzE2 = zzacn.zzE(i4 << 3);
                        zzo = zzE2 + 4;
                        i2 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 58:
                    if (zzZ(obj, i4, i3)) {
                        zzE5 = zzacn.zzE(i4 << 3);
                        zzo = zzE5 + 1;
                        i2 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 59:
                    if (!zzZ(obj, i4, i3)) {
                        break;
                    } else {
                        Object zzf2 = zzafx.zzf(obj, j);
                        if (zzf2 instanceof zzacc) {
                            zzE6 = zzacn.zzE(i4 << 3);
                            zzd = ((zzacc) zzf2).zzd();
                            zzE7 = zzacn.zzE(zzd);
                            i = zzE6 + zzE7 + zzd;
                            i2 += i;
                            break;
                        } else {
                            zzE4 = zzacn.zzE(i4 << 3);
                            zzy = zzacn.zzC((String) zzf2);
                            i = zzE4 + zzy;
                            i2 += i;
                        }
                    }
                case 60:
                    if (zzZ(obj, i4, i3)) {
                        zzo = zzaey.zzo(i4, zzafx.zzf(obj, j), zzF(i3));
                        i2 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 61:
                    if (zzZ(obj, i4, i3)) {
                        zzE6 = zzacn.zzE(i4 << 3);
                        zzd = ((zzacc) zzafx.zzf(obj, j)).zzd();
                        zzE7 = zzacn.zzE(zzd);
                        i = zzE6 + zzE7 + zzd;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 62:
                    if (zzZ(obj, i4, i3)) {
                        int zzs2 = zzs(obj, j);
                        zzE4 = zzacn.zzE(i4 << 3);
                        zzy = zzacn.zzE(zzs2);
                        i = zzE4 + zzy;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 63:
                    if (zzZ(obj, i4, i3)) {
                        int zzs3 = zzs(obj, j);
                        zzE4 = zzacn.zzE(i4 << 3);
                        zzy = zzacn.zzy(zzs3);
                        i = zzE4 + zzy;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 64:
                    if (zzZ(obj, i4, i3)) {
                        zzE2 = zzacn.zzE(i4 << 3);
                        zzo = zzE2 + 4;
                        i2 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 65:
                    if (zzZ(obj, i4, i3)) {
                        zzE = zzacn.zzE(i4 << 3);
                        zzo = zzE + 8;
                        i2 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 66:
                    if (zzZ(obj, i4, i3)) {
                        int zzs4 = zzs(obj, j);
                        zzE4 = zzacn.zzE(i4 << 3);
                        zzy = zzacn.zzE((zzs4 >> 31) ^ (zzs4 + zzs4));
                        i = zzE4 + zzy;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 67:
                    if (zzZ(obj, i4, i3)) {
                        long zzD4 = zzD(obj, j);
                        zzE4 = zzacn.zzE(i4 << 3);
                        zzy = zzacn.zzF((zzD4 >> 63) ^ (zzD4 + zzD4));
                        i = zzE4 + zzy;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 68:
                    if (zzZ(obj, i4, i3)) {
                        zzo = zzacn.zzx(i4, (zzaek) zzafx.zzf(obj, j), zzF(i3));
                        i2 += zzo;
                        break;
                    } else {
                        break;
                    }
            }
        }
        zzafn zzafnVar = this.zzo;
        return i2 + zzafnVar.zza(zzafnVar.zzd(obj));
    }

    private static int zzs(Object obj, long j) {
        return ((Integer) zzafx.zzf(obj, j)).intValue();
    }

    private final int zzt(Object obj, byte[] bArr, int i, int i2, int i3, long j, zzabp zzabpVar) throws IOException {
        Unsafe unsafe = zzb;
        Object zzH = zzH(i3);
        Object object = unsafe.getObject(obj, j);
        if (zzaef.zzb(object)) {
            zzaee zzb2 = zzaee.zza().zzb();
            zzaef.zzc(zzb2, object);
            unsafe.putObject(obj, j, zzb2);
        }
        zzaed zzaedVar = (zzaed) zzH;
        throw null;
    }

    private final int zzu(Object obj, byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, int i7, long j, int i8, zzabp zzabpVar) throws IOException {
        Unsafe unsafe = zzb;
        long j2 = this.zzc[i8 + 2] & 1048575;
        switch (i7) {
            case 51:
                if (i5 == 1) {
                    unsafe.putObject(obj, j, Double.valueOf(Double.longBitsToDouble(zzabq.zzp(bArr, i))));
                    unsafe.putInt(obj, j2, i4);
                    return i + 8;
                }
                break;
            case 52:
                if (i5 == 5) {
                    unsafe.putObject(obj, j, Float.valueOf(Float.intBitsToFloat(zzabq.zzb(bArr, i))));
                    unsafe.putInt(obj, j2, i4);
                    return i + 4;
                }
                break;
            case 53:
            case 54:
                if (i5 == 0) {
                    int zzm = zzabq.zzm(bArr, i, zzabpVar);
                    unsafe.putObject(obj, j, Long.valueOf(zzabpVar.zzb));
                    unsafe.putInt(obj, j2, i4);
                    return zzm;
                }
                break;
            case 55:
            case 62:
                if (i5 == 0) {
                    int zzj = zzabq.zzj(bArr, i, zzabpVar);
                    unsafe.putObject(obj, j, Integer.valueOf(zzabpVar.zza));
                    unsafe.putInt(obj, j2, i4);
                    return zzj;
                }
                break;
            case 56:
            case 65:
                if (i5 == 1) {
                    unsafe.putObject(obj, j, Long.valueOf(zzabq.zzp(bArr, i)));
                    unsafe.putInt(obj, j2, i4);
                    return i + 8;
                }
                break;
            case 57:
            case 64:
                if (i5 == 5) {
                    unsafe.putObject(obj, j, Integer.valueOf(zzabq.zzb(bArr, i)));
                    unsafe.putInt(obj, j2, i4);
                    return i + 4;
                }
                break;
            case 58:
                if (i5 == 0) {
                    int zzm2 = zzabq.zzm(bArr, i, zzabpVar);
                    unsafe.putObject(obj, j, Boolean.valueOf(zzabpVar.zzb != 0));
                    unsafe.putInt(obj, j2, i4);
                    return zzm2;
                }
                break;
            case 59:
                if (i5 == 2) {
                    int zzj2 = zzabq.zzj(bArr, i, zzabpVar);
                    int i9 = zzabpVar.zza;
                    if (i9 == 0) {
                        unsafe.putObject(obj, j, "");
                    } else if ((i6 & 536870912) == 0 || zzagc.zzf(bArr, zzj2, zzj2 + i9)) {
                        unsafe.putObject(obj, j, new String(bArr, zzj2, i9, zzadl.zzb));
                        zzj2 += i9;
                    } else {
                        throw zzadn.zzd();
                    }
                    unsafe.putInt(obj, j2, i4);
                    return zzj2;
                }
                break;
            case 60:
                if (i5 == 2) {
                    Object zzJ = zzJ(obj, i4, i8);
                    int zzo = zzabq.zzo(zzJ, zzF(i8), bArr, i, i2, zzabpVar);
                    zzS(obj, i4, i8, zzJ);
                    return zzo;
                }
                break;
            case 61:
                if (i5 == 2) {
                    int zza2 = zzabq.zza(bArr, i, zzabpVar);
                    unsafe.putObject(obj, j, zzabpVar.zzc);
                    unsafe.putInt(obj, j2, i4);
                    return zza2;
                }
                break;
            case 63:
                if (i5 == 0) {
                    int zzj3 = zzabq.zzj(bArr, i, zzabpVar);
                    int i10 = zzabpVar.zza;
                    zzadj zzE = zzE(i8);
                    if (zzE == null || zzE.zza()) {
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
                    int zzj4 = zzabq.zzj(bArr, i, zzabpVar);
                    unsafe.putObject(obj, j, Integer.valueOf(zzacg.zzs(zzabpVar.zza)));
                    unsafe.putInt(obj, j2, i4);
                    return zzj4;
                }
                break;
            case 67:
                if (i5 == 0) {
                    int zzm3 = zzabq.zzm(bArr, i, zzabpVar);
                    unsafe.putObject(obj, j, Long.valueOf(zzacg.zzt(zzabpVar.zzb)));
                    unsafe.putInt(obj, j2, i4);
                    return zzm3;
                }
                break;
            case 68:
                if (i5 == 3) {
                    Object zzJ2 = zzJ(obj, i4, i8);
                    int zzn = zzabq.zzn(zzJ2, zzF(i8), bArr, i, i2, (i3 & (-8)) | 4, zzabpVar);
                    zzS(obj, i4, i8, zzJ2);
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
    private final int zzv(java.lang.Object r31, byte[] r32, int r33, int r34, com.google.android.gms.internal.p001firebaseauthapi.zzabp r35) throws java.io.IOException {
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
        com.google.android.gms.internal.p001firebaseauthapi.zzaen<T> r13;
        int r19;
        sun.misc.Unsafe r7;
        int r14;
        int r25;
        int r26;
        com.google.android.gms.internal.p001firebaseauthapi.zzaen<T> r15 = r30;
        java.lang.Object r14 = r31;
        byte[] r12 = r32;
        int r13 = r34;
        com.google.android.gms.internal.p001firebaseauthapi.zzabp r11 = r35;
        zzL(r31);
        sun.misc.Unsafe r9 = com.google.android.gms.internal.p001firebaseauthapi.zzaen.zzb;
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
                r4 = com.google.android.gms.internal.p001firebaseauthapi.zzabq.zzk(r0, r12, r3, r11);
                r17 = r11.zza;
            } else {
                r17 = r0;
                r4 = r3;
            }
            int r5 = r17 >>> 3;
            int r3 = r17 & 7;
            if (r5 > r1) {
                r0 = r15.zzy(r5, r2 / 3);
            } else {
                r0 = r15.zzx(r5);
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
                int r13 = zzB(r1);
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
                                com.google.android.gms.internal.p001firebaseauthapi.zzafx.zzo(r14, r8, java.lang.Double.longBitsToDouble(com.google.android.gms.internal.p001firebaseauthapi.zzabq.zzp(r12, r4)));
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
                                com.google.android.gms.internal.p001firebaseauthapi.zzafx.zzp(r14, r8, java.lang.Float.intBitsToFloat(com.google.android.gms.internal.p001firebaseauthapi.zzabq.zzb(r12, r4)));
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
                                int r17 = com.google.android.gms.internal.p001firebaseauthapi.zzabq.zzm(r12, r4, r11);
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
                                r0 = com.google.android.gms.internal.p001firebaseauthapi.zzabq.zzj(r12, r4, r11);
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
                                r7.putLong(r31, r8, com.google.android.gms.internal.p001firebaseauthapi.zzabq.zzp(r12, r4));
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
                                r7.putInt(r14, r8, com.google.android.gms.internal.p001firebaseauthapi.zzabq.zzb(r12, r4));
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
                                r0 = com.google.android.gms.internal.p001firebaseauthapi.zzabq.zzm(r12, r4, r11);
                                com.google.android.gms.internal.p001firebaseauthapi.zzafx.zzm(r14, r8, r11.zzb != 0);
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
                                    r0 = com.google.android.gms.internal.p001firebaseauthapi.zzabq.zzg(r12, r4, r11);
                                } else {
                                    r0 = com.google.android.gms.internal.p001firebaseauthapi.zzabq.zzh(r12, r4, r11);
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
                                java.lang.Object r8 = r13.zzI(r14, r15);
                                r0 = com.google.android.gms.internal.p001firebaseauthapi.zzabq.zzo(r8, r13.zzF(r15), r32, r4, r34, r35);
                                r13.zzR(r14, r15, r8);
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
                                r0 = com.google.android.gms.internal.p001firebaseauthapi.zzabq.zza(r12, r4, r11);
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
                                r0 = com.google.android.gms.internal.p001firebaseauthapi.zzabq.zzj(r12, r4, r11);
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
                                r0 = com.google.android.gms.internal.p001firebaseauthapi.zzabq.zzj(r12, r4, r11);
                                r7.putInt(r14, r8, com.google.android.gms.internal.p001firebaseauthapi.zzacg.zzs(r11.zza));
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
                                int r13 = com.google.android.gms.internal.p001firebaseauthapi.zzabq.zzm(r12, r4, r11);
                                r2.putLong(r31, r8, com.google.android.gms.internal.p001firebaseauthapi.zzacg.zzt(r11.zzb));
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
                    com.google.android.gms.internal.p001firebaseauthapi.zzaen<T> r10 = r15;
                    r15 = r2;
                    if (r13 != 27) {
                        if (r13 <= 49) {
                            int r24 = r4;
                            r25 = r6;
                            r26 = r20;
                            r29 = r19;
                            r18 = -1;
                            r0 = zzw(r31, r32, r4, r34, r17, r23, r3, r15, r1, r13, r8, r35);
                        } else {
                            r14 = r4;
                            r25 = r6;
                            r29 = r19;
                            r26 = r20;
                            r18 = -1;
                            if (r13 != 50) {
                                r0 = zzu(r31, r32, r14, r34, r17, r23, r3, r1, r13, r8, r15, r35);
                            } else if (r3 == 2) {
                                r0 = zzt(r31, r32, r14, r34, r15, r8, r35);
                            }
                        }
                        r15 = r30;
                    } else if (r3 == 2) {
                        com.google.android.gms.internal.p001firebaseauthapi.zzadk r0 = (com.google.android.gms.internal.p001firebaseauthapi.zzadk) r19.getObject(r14, r8);
                        if (!r0.zzc()) {
                            int r1 = r0.size();
                            r0 = r0.zzd(r1 == 0 ? 10 : r1 + r1);
                            r19.putObject(r14, r8, r0);
                        }
                        r0 = com.google.android.gms.internal.p001firebaseauthapi.zzabq.zze(r10.zzF(r15), r17, r32, r4, r34, r0, r35);
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
            r0 = com.google.android.gms.internal.p001firebaseauthapi.zzabq.zzi(r17, r32, r2, r34, zzd(r31), r35);
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
        throw com.google.android.gms.internal.p001firebaseauthapi.zzadn.zzg();
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
    private final int zzw(java.lang.Object r16, byte[] r17, int r18, int r19, int r20, int r21, int r22, int r23, long r24, int r26, long r27, com.google.android.gms.internal.p001firebaseauthapi.zzabp r29) throws java.io.IOException {
        int r1;
        int r1;
        int r1;
        int r1;
        int r2;
        int r8;
        int r4 = r18;
        sun.misc.Unsafe r11 = com.google.android.gms.internal.p001firebaseauthapi.zzaen.zzb;
        com.google.android.gms.internal.p001firebaseauthapi.zzadk r12 = (com.google.android.gms.internal.p001firebaseauthapi.zzadk) r11.getObject(r16, r27);
        if (!r12.zzc()) {
            int r13 = r12.size();
            r12 = r12.zzd(r13 == 0 ? 10 : r13 + r13);
            r11.putObject(r16, r27, r12);
        }
        switch (r26) {
            case 18:
            case 35:
                if (r22 == 2) {
                    com.google.android.gms.internal.p001firebaseauthapi.zzacp r12 = (com.google.android.gms.internal.p001firebaseauthapi.zzacp) r12;
                    int r1 = com.google.android.gms.internal.p001firebaseauthapi.zzabq.zzj(r17, r4, r29);
                    int r2 = r29.zza + r1;
                    while (r1 < r2) {
                        r12.zze(java.lang.Double.longBitsToDouble(com.google.android.gms.internal.p001firebaseauthapi.zzabq.zzp(r17, r1)));
                        r1 += 8;
                    }
                    if (r1 == r2) {
                        return r1;
                    }
                    throw com.google.android.gms.internal.p001firebaseauthapi.zzadn.zzi();
                }
                if (r22 == 1) {
                    com.google.android.gms.internal.p001firebaseauthapi.zzacp r12 = (com.google.android.gms.internal.p001firebaseauthapi.zzacp) r12;
                    r12.zze(java.lang.Double.longBitsToDouble(com.google.android.gms.internal.p001firebaseauthapi.zzabq.zzp(r17, r18)));
                    while (true) {
                        r1 = r4 + 8;
                        if (r1 < r19) {
                            r4 = com.google.android.gms.internal.p001firebaseauthapi.zzabq.zzj(r17, r1, r29);
                            if (r20 == r29.zza) {
                                r12.zze(java.lang.Double.longBitsToDouble(com.google.android.gms.internal.p001firebaseauthapi.zzabq.zzp(r17, r4)));
                            }
                        }
                    }
                    return r1;
                }
                return r4;
            case 19:
            case 36:
                if (r22 == 2) {
                    com.google.android.gms.internal.p001firebaseauthapi.zzacz r12 = (com.google.android.gms.internal.p001firebaseauthapi.zzacz) r12;
                    int r1 = com.google.android.gms.internal.p001firebaseauthapi.zzabq.zzj(r17, r4, r29);
                    int r2 = r29.zza + r1;
                    while (r1 < r2) {
                        r12.zze(java.lang.Float.intBitsToFloat(com.google.android.gms.internal.p001firebaseauthapi.zzabq.zzb(r17, r1)));
                        r1 += 4;
                    }
                    if (r1 == r2) {
                        return r1;
                    }
                    throw com.google.android.gms.internal.p001firebaseauthapi.zzadn.zzi();
                }
                if (r22 == 5) {
                    com.google.android.gms.internal.p001firebaseauthapi.zzacz r12 = (com.google.android.gms.internal.p001firebaseauthapi.zzacz) r12;
                    r12.zze(java.lang.Float.intBitsToFloat(com.google.android.gms.internal.p001firebaseauthapi.zzabq.zzb(r17, r18)));
                    while (true) {
                        r1 = r4 + 4;
                        if (r1 < r19) {
                            r4 = com.google.android.gms.internal.p001firebaseauthapi.zzabq.zzj(r17, r1, r29);
                            if (r20 == r29.zza) {
                                r12.zze(java.lang.Float.intBitsToFloat(com.google.android.gms.internal.p001firebaseauthapi.zzabq.zzb(r17, r4)));
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
                    com.google.android.gms.internal.p001firebaseauthapi.zzadz r12 = (com.google.android.gms.internal.p001firebaseauthapi.zzadz) r12;
                    int r1 = com.google.android.gms.internal.p001firebaseauthapi.zzabq.zzj(r17, r4, r29);
                    int r2 = r29.zza + r1;
                    while (r1 < r2) {
                        r1 = com.google.android.gms.internal.p001firebaseauthapi.zzabq.zzm(r17, r1, r29);
                        r12.zzf(r29.zzb);
                    }
                    if (r1 == r2) {
                        return r1;
                    }
                    throw com.google.android.gms.internal.p001firebaseauthapi.zzadn.zzi();
                }
                if (r22 == 0) {
                    com.google.android.gms.internal.p001firebaseauthapi.zzadz r12 = (com.google.android.gms.internal.p001firebaseauthapi.zzadz) r12;
                    int r1 = com.google.android.gms.internal.p001firebaseauthapi.zzabq.zzm(r17, r4, r29);
                    r12.zzf(r29.zzb);
                    while (r1 < r19) {
                        int r4 = com.google.android.gms.internal.p001firebaseauthapi.zzabq.zzj(r17, r1, r29);
                        if (r20 != r29.zza) {
                            return r1;
                        }
                        r1 = com.google.android.gms.internal.p001firebaseauthapi.zzabq.zzm(r17, r4, r29);
                        r12.zzf(r29.zzb);
                    }
                    return r1;
                }
                return r4;
            case 22:
            case 29:
            case 39:
            case 43:
                if (r22 == 2) {
                    return com.google.android.gms.internal.p001firebaseauthapi.zzabq.zzf(r17, r4, r12, r29);
                }
                if (r22 == 0) {
                    return com.google.android.gms.internal.p001firebaseauthapi.zzabq.zzl(r20, r17, r18, r19, r12, r29);
                }
                return r4;
            case 23:
            case 32:
            case 40:
            case 46:
                if (r22 == 2) {
                    com.google.android.gms.internal.p001firebaseauthapi.zzadz r12 = (com.google.android.gms.internal.p001firebaseauthapi.zzadz) r12;
                    int r1 = com.google.android.gms.internal.p001firebaseauthapi.zzabq.zzj(r17, r4, r29);
                    int r2 = r29.zza + r1;
                    while (r1 < r2) {
                        r12.zzf(com.google.android.gms.internal.p001firebaseauthapi.zzabq.zzp(r17, r1));
                        r1 += 8;
                    }
                    if (r1 == r2) {
                        return r1;
                    }
                    throw com.google.android.gms.internal.p001firebaseauthapi.zzadn.zzi();
                }
                if (r22 == 1) {
                    com.google.android.gms.internal.p001firebaseauthapi.zzadz r12 = (com.google.android.gms.internal.p001firebaseauthapi.zzadz) r12;
                    r12.zzf(com.google.android.gms.internal.p001firebaseauthapi.zzabq.zzp(r17, r18));
                    while (true) {
                        r1 = r4 + 8;
                        if (r1 < r19) {
                            r4 = com.google.android.gms.internal.p001firebaseauthapi.zzabq.zzj(r17, r1, r29);
                            if (r20 == r29.zza) {
                                r12.zzf(com.google.android.gms.internal.p001firebaseauthapi.zzabq.zzp(r17, r4));
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
                    com.google.android.gms.internal.p001firebaseauthapi.zzadg r12 = (com.google.android.gms.internal.p001firebaseauthapi.zzadg) r12;
                    int r1 = com.google.android.gms.internal.p001firebaseauthapi.zzabq.zzj(r17, r4, r29);
                    int r2 = r29.zza + r1;
                    while (r1 < r2) {
                        r12.zzf(com.google.android.gms.internal.p001firebaseauthapi.zzabq.zzb(r17, r1));
                        r1 += 4;
                    }
                    if (r1 == r2) {
                        return r1;
                    }
                    throw com.google.android.gms.internal.p001firebaseauthapi.zzadn.zzi();
                }
                if (r22 == 5) {
                    com.google.android.gms.internal.p001firebaseauthapi.zzadg r12 = (com.google.android.gms.internal.p001firebaseauthapi.zzadg) r12;
                    r12.zzf(com.google.android.gms.internal.p001firebaseauthapi.zzabq.zzb(r17, r18));
                    while (true) {
                        r1 = r4 + 4;
                        if (r1 < r19) {
                            r4 = com.google.android.gms.internal.p001firebaseauthapi.zzabq.zzj(r17, r1, r29);
                            if (r20 == r29.zza) {
                                r12.zzf(com.google.android.gms.internal.p001firebaseauthapi.zzabq.zzb(r17, r4));
                            }
                        }
                    }
                    return r1;
                }
                return r4;
            case 25:
            case 42:
                if (r22 == 2) {
                    com.google.android.gms.internal.p001firebaseauthapi.zzabr r12 = (com.google.android.gms.internal.p001firebaseauthapi.zzabr) r12;
                    r2 = com.google.android.gms.internal.p001firebaseauthapi.zzabq.zzj(r17, r4, r29);
                    int r4 = r29.zza + r2;
                    while (r2 < r4) {
                        r2 = com.google.android.gms.internal.p001firebaseauthapi.zzabq.zzm(r17, r2, r29);
                        r12.zze(r29.zzb != 0);
                    }
                    if (r2 != r4) {
                        throw com.google.android.gms.internal.p001firebaseauthapi.zzadn.zzi();
                    }
                    return r2;
                }
                if (r22 == 0) {
                    com.google.android.gms.internal.p001firebaseauthapi.zzabr r12 = (com.google.android.gms.internal.p001firebaseauthapi.zzabr) r12;
                    int r4 = com.google.android.gms.internal.p001firebaseauthapi.zzabq.zzm(r17, r4, r29);
                    r12.zze(r29.zzb != 0);
                    while (r4 < r19) {
                        int r6 = com.google.android.gms.internal.p001firebaseauthapi.zzabq.zzj(r17, r4, r29);
                        if (r20 != r29.zza) {
                            return r4;
                        }
                        r4 = com.google.android.gms.internal.p001firebaseauthapi.zzabq.zzm(r17, r6, r29);
                        r12.zze(r29.zzb != 0);
                    }
                    return r4;
                }
                return r4;
            case 26:
                if (r22 == 2) {
                    if ((r24 & 536870912) != 0) {
                        int r1 = com.google.android.gms.internal.p001firebaseauthapi.zzabq.zzj(r17, r4, r29);
                        int r4 = r29.zza;
                        if (r4 >= 0) {
                            if (r4 == 0) {
                                r12.add("");
                                while (r1 < r19) {
                                    int r4 = com.google.android.gms.internal.p001firebaseauthapi.zzabq.zzj(r17, r1, r29);
                                    if (r20 != r29.zza) {
                                        return r1;
                                    }
                                    r1 = com.google.android.gms.internal.p001firebaseauthapi.zzabq.zzj(r17, r4, r29);
                                    int r4 = r29.zza;
                                    if (r4 < 0) {
                                        throw com.google.android.gms.internal.p001firebaseauthapi.zzadn.zzf();
                                    }
                                    if (r4 == 0) {
                                        r12.add("");
                                    } else {
                                        r8 = r1 + r4;
                                        if (com.google.android.gms.internal.p001firebaseauthapi.zzagc.zzf(r17, r1, r8)) {
                                            r12.add(new java.lang.String(r17, r1, r4, com.google.android.gms.internal.p001firebaseauthapi.zzadl.zzb));
                                            r1 = r8;
                                            while (r1 < r19) {
                                            }
                                        } else {
                                            throw com.google.android.gms.internal.p001firebaseauthapi.zzadn.zzd();
                                        }
                                    }
                                }
                                return r1;
                            }
                            r8 = r1 + r4;
                            if (!com.google.android.gms.internal.p001firebaseauthapi.zzagc.zzf(r17, r1, r8)) {
                                throw com.google.android.gms.internal.p001firebaseauthapi.zzadn.zzd();
                            }
                            r12.add(new java.lang.String(r17, r1, r4, com.google.android.gms.internal.p001firebaseauthapi.zzadl.zzb));
                            r1 = r8;
                            while (r1 < r19) {
                            }
                            return r1;
                        }
                        throw com.google.android.gms.internal.p001firebaseauthapi.zzadn.zzf();
                    }
                    int r1 = com.google.android.gms.internal.p001firebaseauthapi.zzabq.zzj(r17, r4, r29);
                    int r4 = r29.zza;
                    if (r4 >= 0) {
                        if (r4 == 0) {
                            r12.add("");
                            while (r1 < r19) {
                                int r4 = com.google.android.gms.internal.p001firebaseauthapi.zzabq.zzj(r17, r1, r29);
                                if (r20 != r29.zza) {
                                    return r1;
                                }
                                r1 = com.google.android.gms.internal.p001firebaseauthapi.zzabq.zzj(r17, r4, r29);
                                r4 = r29.zza;
                                if (r4 < 0) {
                                    throw com.google.android.gms.internal.p001firebaseauthapi.zzadn.zzf();
                                }
                                if (r4 == 0) {
                                    r12.add("");
                                } else {
                                    r12.add(new java.lang.String(r17, r1, r4, com.google.android.gms.internal.p001firebaseauthapi.zzadl.zzb));
                                    r1 += r4;
                                    while (r1 < r19) {
                                    }
                                }
                            }
                            return r1;
                        }
                        r12.add(new java.lang.String(r17, r1, r4, com.google.android.gms.internal.p001firebaseauthapi.zzadl.zzb));
                        r1 += r4;
                        while (r1 < r19) {
                        }
                        return r1;
                    }
                    throw com.google.android.gms.internal.p001firebaseauthapi.zzadn.zzf();
                }
                return r4;
            case 27:
                if (r22 == 2) {
                    return com.google.android.gms.internal.p001firebaseauthapi.zzabq.zze(zzF(r23), r20, r17, r18, r19, r12, r29);
                }
                return r4;
            case 28:
                if (r22 == 2) {
                    int r1 = com.google.android.gms.internal.p001firebaseauthapi.zzabq.zzj(r17, r4, r29);
                    int r4 = r29.zza;
                    if (r4 < 0) {
                        throw com.google.android.gms.internal.p001firebaseauthapi.zzadn.zzf();
                    }
                    if (r4 <= r17.length - r1) {
                        if (r4 == 0) {
                            r12.add(com.google.android.gms.internal.p001firebaseauthapi.zzacc.zzb);
                            while (r1 < r19) {
                                int r4 = com.google.android.gms.internal.p001firebaseauthapi.zzabq.zzj(r17, r1, r29);
                                if (r20 != r29.zza) {
                                    return r1;
                                }
                                r1 = com.google.android.gms.internal.p001firebaseauthapi.zzabq.zzj(r17, r4, r29);
                                r4 = r29.zza;
                                if (r4 >= 0) {
                                    if (r4 > r17.length - r1) {
                                        throw com.google.android.gms.internal.p001firebaseauthapi.zzadn.zzi();
                                    }
                                    if (r4 == 0) {
                                        r12.add(com.google.android.gms.internal.p001firebaseauthapi.zzacc.zzb);
                                    } else {
                                        r12.add(com.google.android.gms.internal.p001firebaseauthapi.zzacc.zzo(r17, r1, r4));
                                        r1 += r4;
                                        while (r1 < r19) {
                                        }
                                    }
                                } else {
                                    throw com.google.android.gms.internal.p001firebaseauthapi.zzadn.zzf();
                                }
                            }
                            return r1;
                        }
                        r12.add(com.google.android.gms.internal.p001firebaseauthapi.zzacc.zzo(r17, r1, r4));
                        r1 += r4;
                        while (r1 < r19) {
                        }
                        return r1;
                    }
                    throw com.google.android.gms.internal.p001firebaseauthapi.zzadn.zzi();
                }
                return r4;
            case 30:
            case 44:
                if (r22 != 2) {
                    if (r22 == 0) {
                        r2 = com.google.android.gms.internal.p001firebaseauthapi.zzabq.zzl(r20, r17, r18, r19, r12, r29);
                    }
                    return r4;
                }
                r2 = com.google.android.gms.internal.p001firebaseauthapi.zzabq.zzf(r17, r4, r12, r29);
                com.google.android.gms.internal.p001firebaseauthapi.zzaey.zzC(r16, r21, r12, zzE(r23), null, r15.zzo);
                return r2;
            case 33:
            case 47:
                if (r22 == 2) {
                    com.google.android.gms.internal.p001firebaseauthapi.zzadg r12 = (com.google.android.gms.internal.p001firebaseauthapi.zzadg) r12;
                    int r1 = com.google.android.gms.internal.p001firebaseauthapi.zzabq.zzj(r17, r4, r29);
                    int r2 = r29.zza + r1;
                    while (r1 < r2) {
                        r1 = com.google.android.gms.internal.p001firebaseauthapi.zzabq.zzj(r17, r1, r29);
                        r12.zzf(com.google.android.gms.internal.p001firebaseauthapi.zzacg.zzs(r29.zza));
                    }
                    if (r1 == r2) {
                        return r1;
                    }
                    throw com.google.android.gms.internal.p001firebaseauthapi.zzadn.zzi();
                }
                if (r22 == 0) {
                    com.google.android.gms.internal.p001firebaseauthapi.zzadg r12 = (com.google.android.gms.internal.p001firebaseauthapi.zzadg) r12;
                    int r1 = com.google.android.gms.internal.p001firebaseauthapi.zzabq.zzj(r17, r4, r29);
                    r12.zzf(com.google.android.gms.internal.p001firebaseauthapi.zzacg.zzs(r29.zza));
                    while (r1 < r19) {
                        int r4 = com.google.android.gms.internal.p001firebaseauthapi.zzabq.zzj(r17, r1, r29);
                        if (r20 != r29.zza) {
                            return r1;
                        }
                        r1 = com.google.android.gms.internal.p001firebaseauthapi.zzabq.zzj(r17, r4, r29);
                        r12.zzf(com.google.android.gms.internal.p001firebaseauthapi.zzacg.zzs(r29.zza));
                    }
                    return r1;
                }
                return r4;
            case 34:
            case 48:
                if (r22 == 2) {
                    com.google.android.gms.internal.p001firebaseauthapi.zzadz r12 = (com.google.android.gms.internal.p001firebaseauthapi.zzadz) r12;
                    int r1 = com.google.android.gms.internal.p001firebaseauthapi.zzabq.zzj(r17, r4, r29);
                    int r2 = r29.zza + r1;
                    while (r1 < r2) {
                        r1 = com.google.android.gms.internal.p001firebaseauthapi.zzabq.zzm(r17, r1, r29);
                        r12.zzf(com.google.android.gms.internal.p001firebaseauthapi.zzacg.zzt(r29.zzb));
                    }
                    if (r1 == r2) {
                        return r1;
                    }
                    throw com.google.android.gms.internal.p001firebaseauthapi.zzadn.zzi();
                }
                if (r22 == 0) {
                    com.google.android.gms.internal.p001firebaseauthapi.zzadz r12 = (com.google.android.gms.internal.p001firebaseauthapi.zzadz) r12;
                    int r1 = com.google.android.gms.internal.p001firebaseauthapi.zzabq.zzm(r17, r4, r29);
                    r12.zzf(com.google.android.gms.internal.p001firebaseauthapi.zzacg.zzt(r29.zzb));
                    while (r1 < r19) {
                        int r4 = com.google.android.gms.internal.p001firebaseauthapi.zzabq.zzj(r17, r1, r29);
                        if (r20 != r29.zza) {
                            return r1;
                        }
                        r1 = com.google.android.gms.internal.p001firebaseauthapi.zzabq.zzm(r17, r4, r29);
                        r12.zzf(com.google.android.gms.internal.p001firebaseauthapi.zzacg.zzt(r29.zzb));
                    }
                    return r1;
                }
                return r4;
            default:
                if (r22 == 3) {
                    com.google.android.gms.internal.p001firebaseauthapi.zzaew r1 = zzF(r23);
                    int r6 = (r20 & (-8)) | 4;
                    int r4 = com.google.android.gms.internal.p001firebaseauthapi.zzabq.zzc(r1, r17, r18, r19, r6, r29);
                    r12.add(r29.zzc);
                    while (r4 < r19) {
                        int r8 = com.google.android.gms.internal.p001firebaseauthapi.zzabq.zzj(r17, r4, r29);
                        if (r20 != r29.zza) {
                            return r4;
                        }
                        r4 = com.google.android.gms.internal.p001firebaseauthapi.zzabq.zzc(r1, r17, r8, r19, r6, r29);
                        r12.add(r29.zzc);
                    }
                    return r4;
                }
                return r4;
        }
    }

    private final int zzx(int i) {
        if (i < this.zze || i > this.zzf) {
            return -1;
        }
        return zzA(i, 0);
    }

    private final int zzy(int i, int i2) {
        if (i < this.zze || i > this.zzf) {
            return -1;
        }
        return zzA(i, i2);
    }

    private final int zzz(int i) {
        return this.zzc[i + 2];
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaew
    public final int zza(Object obj) {
        return this.zzj ? zzr(obj) : zzq(obj);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaew
    public final int zzb(Object obj) {
        int i;
        int zzc;
        int length = this.zzc.length;
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3 += 3) {
            int zzC = zzC(i3);
            int i4 = this.zzc[i3];
            long j = 1048575 & zzC;
            int i5 = 37;
            switch (zzB(zzC)) {
                case 0:
                    i = i2 * 53;
                    zzc = zzadl.zzc(Double.doubleToLongBits(zzafx.zza(obj, j)));
                    i2 = i + zzc;
                    break;
                case 1:
                    i = i2 * 53;
                    zzc = Float.floatToIntBits(zzafx.zzb(obj, j));
                    i2 = i + zzc;
                    break;
                case 2:
                    i = i2 * 53;
                    zzc = zzadl.zzc(zzafx.zzd(obj, j));
                    i2 = i + zzc;
                    break;
                case 3:
                    i = i2 * 53;
                    zzc = zzadl.zzc(zzafx.zzd(obj, j));
                    i2 = i + zzc;
                    break;
                case 4:
                    i = i2 * 53;
                    zzc = zzafx.zzc(obj, j);
                    i2 = i + zzc;
                    break;
                case 5:
                    i = i2 * 53;
                    zzc = zzadl.zzc(zzafx.zzd(obj, j));
                    i2 = i + zzc;
                    break;
                case 6:
                    i = i2 * 53;
                    zzc = zzafx.zzc(obj, j);
                    i2 = i + zzc;
                    break;
                case 7:
                    i = i2 * 53;
                    zzc = zzadl.zza(zzafx.zzw(obj, j));
                    i2 = i + zzc;
                    break;
                case 8:
                    i = i2 * 53;
                    zzc = ((String) zzafx.zzf(obj, j)).hashCode();
                    i2 = i + zzc;
                    break;
                case 9:
                    Object zzf = zzafx.zzf(obj, j);
                    if (zzf != null) {
                        i5 = zzf.hashCode();
                    }
                    i2 = (i2 * 53) + i5;
                    break;
                case 10:
                    i = i2 * 53;
                    zzc = zzafx.zzf(obj, j).hashCode();
                    i2 = i + zzc;
                    break;
                case 11:
                    i = i2 * 53;
                    zzc = zzafx.zzc(obj, j);
                    i2 = i + zzc;
                    break;
                case 12:
                    i = i2 * 53;
                    zzc = zzafx.zzc(obj, j);
                    i2 = i + zzc;
                    break;
                case 13:
                    i = i2 * 53;
                    zzc = zzafx.zzc(obj, j);
                    i2 = i + zzc;
                    break;
                case 14:
                    i = i2 * 53;
                    zzc = zzadl.zzc(zzafx.zzd(obj, j));
                    i2 = i + zzc;
                    break;
                case 15:
                    i = i2 * 53;
                    zzc = zzafx.zzc(obj, j);
                    i2 = i + zzc;
                    break;
                case 16:
                    i = i2 * 53;
                    zzc = zzadl.zzc(zzafx.zzd(obj, j));
                    i2 = i + zzc;
                    break;
                case 17:
                    Object zzf2 = zzafx.zzf(obj, j);
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
                    zzc = zzafx.zzf(obj, j).hashCode();
                    i2 = i + zzc;
                    break;
                case 50:
                    i = i2 * 53;
                    zzc = zzafx.zzf(obj, j).hashCode();
                    i2 = i + zzc;
                    break;
                case 51:
                    if (zzZ(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzadl.zzc(Double.doubleToLongBits(zzo(obj, j)));
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 52:
                    if (zzZ(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = Float.floatToIntBits(zzp(obj, j));
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 53:
                    if (zzZ(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzadl.zzc(zzD(obj, j));
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 54:
                    if (zzZ(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzadl.zzc(zzD(obj, j));
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 55:
                    if (zzZ(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzs(obj, j);
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 56:
                    if (zzZ(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzadl.zzc(zzD(obj, j));
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 57:
                    if (zzZ(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzs(obj, j);
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 58:
                    if (zzZ(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzadl.zza(zzaa(obj, j));
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 59:
                    if (zzZ(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = ((String) zzafx.zzf(obj, j)).hashCode();
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 60:
                    if (zzZ(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzafx.zzf(obj, j).hashCode();
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 61:
                    if (zzZ(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzafx.zzf(obj, j).hashCode();
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 62:
                    if (zzZ(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzs(obj, j);
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 63:
                    if (zzZ(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzs(obj, j);
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 64:
                    if (zzZ(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzs(obj, j);
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 65:
                    if (zzZ(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzadl.zzc(zzD(obj, j));
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 66:
                    if (zzZ(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzs(obj, j);
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 67:
                    if (zzZ(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzadl.zzc(zzD(obj, j));
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 68:
                    if (zzZ(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzafx.zzf(obj, j).hashCode();
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
            }
        }
        int hashCode = (i2 * 53) + this.zzo.zzd(obj).hashCode();
        if (this.zzh) {
            this.zzp.zza(obj);
            throw null;
        }
        return hashCode;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:145:0x0424, code lost:
        if (r0 == r1) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:146:0x0426, code lost:
        r27.putInt(r12, r0, r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:147:0x042c, code lost:
        r10 = r9.zzl;
     */
    /* JADX WARN: Code restructure failed: missing block: B:149:0x0431, code lost:
        if (r10 >= r9.zzm) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:150:0x0433, code lost:
        zzG(r29, r9.zzk[r10], null, r9.zzo, r29);
        r10 = r10 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:151:0x0446, code lost:
        if (r7 != 0) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:153:0x044a, code lost:
        if (r6 != r32) goto L42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:156:0x0451, code lost:
        throw com.google.android.gms.internal.p001firebaseauthapi.zzadn.zzg();
     */
    /* JADX WARN: Code restructure failed: missing block: B:158:0x0454, code lost:
        if (r6 > r32) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:159:0x0456, code lost:
        if (r8 != r7) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:160:0x0458, code lost:
        return r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:162:0x045d, code lost:
        throw com.google.android.gms.internal.p001firebaseauthapi.zzadn.zzg();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int zzc(java.lang.Object r29, byte[] r30, int r31, int r32, int r33, com.google.android.gms.internal.p001firebaseauthapi.zzabp r34) throws java.io.IOException {
        sun.misc.Unsafe r27;
        int r7;
        java.lang.Object r12;
        com.google.android.gms.internal.p001firebaseauthapi.zzaen<T> r9;
        int r6;
        int r8;
        int r0;
        int r1;
        byte r4;
        int r2;
        int r19;
        int r2;
        int r25;
        int r20;
        java.lang.Object r12;
        com.google.android.gms.internal.p001firebaseauthapi.zzabp r10;
        int r11;
        int r17;
        int r26;
        int r13;
        int r11;
        int r6;
        int r6;
        int r5;
        int r1;
        int r17;
        int r15;
        com.google.android.gms.internal.p001firebaseauthapi.zzaen<T> r15 = r28;
        java.lang.Object r14 = r29;
        byte[] r12 = r30;
        int r13 = r32;
        int r11 = r33;
        com.google.android.gms.internal.p001firebaseauthapi.zzabp r9 = r34;
        zzL(r29);
        sun.misc.Unsafe r10 = com.google.android.gms.internal.p001firebaseauthapi.zzaen.zzb;
        int r0 = r31;
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
                    int r0 = com.google.android.gms.internal.p001firebaseauthapi.zzabq.zzk(r0, r12, r1, r9);
                    r4 = r9.zza;
                    r1 = r0;
                } else {
                    r4 = r0;
                }
                int r0 = r4 >>> 3;
                int r7 = r4 & 7;
                if (r0 > r2) {
                    r2 = r15.zzy(r0, r3 / 3);
                } else {
                    r2 = r15.zzx(r0);
                }
                if (r2 == -1) {
                    r19 = r0;
                    r2 = r1;
                    r8 = r4;
                    r25 = r5;
                    r27 = r10;
                    r7 = r11;
                    r20 = 0;
                } else {
                    int[] r3 = r15.zzc;
                    int r8 = r3[r2 + 1];
                    int r11 = zzB(r8);
                    int r20 = r1;
                    long r0 = r8 & 1048575;
                    if (r11 <= 17) {
                        int r0 = r3[r2 + 2];
                        int r23 = 1 << (r0 >>> 20);
                        int r0 = r0 & 1048575;
                        if (r0 != r6) {
                            r17 = r4;
                            if (r6 != 1048575) {
                                r10.putInt(r14, r6, r5);
                            }
                            r26 = r0;
                            r25 = r10.getInt(r14, r0);
                        } else {
                            r17 = r4;
                            r25 = r5;
                            r26 = r6;
                        }
                        switch (r11) {
                            case 0:
                                r13 = r2;
                                r11 = r0;
                                r6 = r20;
                                if (r7 != 1) {
                                    r8 = r17;
                                    r7 = r33;
                                    r2 = r6;
                                    r27 = r10;
                                    r19 = r11;
                                    r20 = r13;
                                    r6 = r26;
                                    break;
                                } else {
                                    com.google.android.gms.internal.p001firebaseauthapi.zzafx.zzo(r14, r0, java.lang.Double.longBitsToDouble(com.google.android.gms.internal.p001firebaseauthapi.zzabq.zzp(r12, r6)));
                                    r0 = r6 + 8;
                                    r5 = r25 | r23;
                                    r2 = r11;
                                    r3 = r13;
                                    r1 = r17;
                                    break;
                                }
                            case 1:
                                r13 = r2;
                                r11 = r0;
                                r6 = r20;
                                if (r7 != 5) {
                                    r8 = r17;
                                    r7 = r33;
                                    r2 = r6;
                                    r27 = r10;
                                    r19 = r11;
                                    r20 = r13;
                                    r6 = r26;
                                    break;
                                } else {
                                    com.google.android.gms.internal.p001firebaseauthapi.zzafx.zzp(r14, r0, java.lang.Float.intBitsToFloat(com.google.android.gms.internal.p001firebaseauthapi.zzabq.zzb(r12, r6)));
                                    r0 = r6 + 4;
                                    r5 = r25 | r23;
                                    r2 = r11;
                                    r3 = r13;
                                    r1 = r17;
                                    break;
                                }
                            case 2:
                            case 3:
                                r13 = r2;
                                r11 = r0;
                                r6 = r20;
                                if (r7 != 0) {
                                    r8 = r17;
                                    r7 = r33;
                                    r2 = r6;
                                    r27 = r10;
                                    r19 = r11;
                                    r20 = r13;
                                    r6 = r26;
                                    break;
                                } else {
                                    int r6 = com.google.android.gms.internal.p001firebaseauthapi.zzabq.zzm(r12, r6, r9);
                                    r10.putLong(r29, r0, r9.zzb);
                                    r5 = r25 | r23;
                                    r0 = r6;
                                    r2 = r11;
                                    r3 = r13;
                                    r1 = r17;
                                    break;
                                }
                            case 4:
                            case 11:
                                r13 = r2;
                                r11 = r0;
                                r6 = r20;
                                if (r7 != 0) {
                                    r8 = r17;
                                    r7 = r33;
                                    r2 = r6;
                                    r27 = r10;
                                    r19 = r11;
                                    r20 = r13;
                                    r6 = r26;
                                    break;
                                } else {
                                    r0 = com.google.android.gms.internal.p001firebaseauthapi.zzabq.zzj(r12, r6, r9);
                                    r10.putInt(r14, r0, r9.zza);
                                    r5 = r25 | r23;
                                    r2 = r11;
                                    r3 = r13;
                                    r1 = r17;
                                    break;
                                }
                            case 5:
                            case 14:
                                r13 = r2;
                                int r6 = r17;
                                r11 = r0;
                                if (r7 != 1) {
                                    r17 = r6;
                                    r6 = r20;
                                    r8 = r17;
                                    r7 = r33;
                                    r2 = r6;
                                    r27 = r10;
                                    r19 = r11;
                                    r20 = r13;
                                    r6 = r26;
                                    break;
                                } else {
                                    r17 = r6;
                                    r6 = r20;
                                    r10.putLong(r29, r0, com.google.android.gms.internal.p001firebaseauthapi.zzabq.zzp(r12, r20));
                                    r0 = r6 + 8;
                                    r5 = r25 | r23;
                                    r2 = r11;
                                    r3 = r13;
                                    r1 = r17;
                                    break;
                                }
                            case 6:
                            case 13:
                                r13 = r2;
                                r6 = r17;
                                r11 = r0;
                                r5 = r20;
                                if (r7 != 5) {
                                    r8 = r6;
                                    r6 = r5;
                                    r7 = r33;
                                    r2 = r6;
                                    r27 = r10;
                                    r19 = r11;
                                    r20 = r13;
                                    r6 = r26;
                                    break;
                                } else {
                                    r10.putInt(r14, r0, com.google.android.gms.internal.p001firebaseauthapi.zzabq.zzb(r12, r5));
                                    r0 = r5 + 4;
                                    r5 = r25 | r23;
                                    r1 = r6;
                                    r2 = r11;
                                    r3 = r13;
                                    break;
                                }
                            case 7:
                                r13 = r2;
                                r6 = r17;
                                r11 = r0;
                                r5 = r20;
                                if (r7 != 0) {
                                    r8 = r6;
                                    r6 = r5;
                                    r7 = r33;
                                    r2 = r6;
                                    r27 = r10;
                                    r19 = r11;
                                    r20 = r13;
                                    r6 = r26;
                                    break;
                                } else {
                                    r0 = com.google.android.gms.internal.p001firebaseauthapi.zzabq.zzm(r12, r5, r9);
                                    com.google.android.gms.internal.p001firebaseauthapi.zzafx.zzm(r14, r0, r9.zzb != 0);
                                    r5 = r25 | r23;
                                    r1 = r6;
                                    r2 = r11;
                                    r3 = r13;
                                    break;
                                }
                            case 8:
                                r13 = r2;
                                r6 = r17;
                                r11 = r0;
                                r5 = r20;
                                if (r7 != 2) {
                                    r8 = r6;
                                    r6 = r5;
                                    r7 = r33;
                                    r2 = r6;
                                    r27 = r10;
                                    r19 = r11;
                                    r20 = r13;
                                    r6 = r26;
                                    break;
                                } else {
                                    if ((536870912 & r8) == 0) {
                                        r0 = com.google.android.gms.internal.p001firebaseauthapi.zzabq.zzg(r12, r5, r9);
                                    } else {
                                        r0 = com.google.android.gms.internal.p001firebaseauthapi.zzabq.zzh(r12, r5, r9);
                                    }
                                    r10.putObject(r14, r0, r9.zzc);
                                    r5 = r25 | r23;
                                    r1 = r6;
                                    r2 = r11;
                                    r3 = r13;
                                    break;
                                }
                            case 9:
                                r13 = r2;
                                r8 = r17;
                                r11 = r0;
                                r1 = r20;
                                if (r7 != 2) {
                                    r6 = r1;
                                    r7 = r33;
                                    r2 = r6;
                                    r27 = r10;
                                    r19 = r11;
                                    r20 = r13;
                                    r6 = r26;
                                    break;
                                } else {
                                    java.lang.Object r6 = r15.zzI(r14, r13);
                                    r0 = com.google.android.gms.internal.p001firebaseauthapi.zzabq.zzo(r6, r15.zzF(r13), r30, r1, r32, r34);
                                    r15.zzR(r14, r13, r6);
                                    r5 = r25 | r23;
                                    r1 = r8;
                                    r2 = r11;
                                    r3 = r13;
                                    break;
                                }
                            case 10:
                                r13 = r2;
                                r8 = r17;
                                r11 = r0;
                                r1 = r20;
                                if (r7 != 2) {
                                    r6 = r1;
                                    r7 = r33;
                                    r2 = r6;
                                    r27 = r10;
                                    r19 = r11;
                                    r20 = r13;
                                    r6 = r26;
                                    break;
                                } else {
                                    r0 = com.google.android.gms.internal.p001firebaseauthapi.zzabq.zza(r12, r1, r9);
                                    r10.putObject(r14, r0, r9.zzc);
                                    r5 = r25 | r23;
                                    r1 = r8;
                                    r2 = r11;
                                    r3 = r13;
                                    break;
                                }
                            case 12:
                                r13 = r2;
                                r8 = r17;
                                r11 = r0;
                                r1 = r20;
                                if (r7 != 0) {
                                    r6 = r1;
                                    r7 = r33;
                                    r2 = r6;
                                    r27 = r10;
                                    r19 = r11;
                                    r20 = r13;
                                    r6 = r26;
                                    break;
                                } else {
                                    r0 = com.google.android.gms.internal.p001firebaseauthapi.zzabq.zzj(r12, r1, r9);
                                    int r1 = r9.zza;
                                    com.google.android.gms.internal.p001firebaseauthapi.zzadj r2 = r15.zzE(r13);
                                    if (r2 == null || r2.zza()) {
                                        r10.putInt(r14, r0, r1);
                                        r5 = r25 | r23;
                                        r1 = r8;
                                        r2 = r11;
                                        r3 = r13;
                                        break;
                                    } else {
                                        zzd(r29).zzj(r8, java.lang.Long.valueOf(r1));
                                        r1 = r8;
                                        r2 = r11;
                                        r3 = r13;
                                        r5 = r25;
                                        break;
                                    }
                                }
                                break;
                            case 15:
                                r13 = r2;
                                r8 = r17;
                                r11 = r0;
                                r1 = r20;
                                if (r7 != 0) {
                                    r6 = r1;
                                    r7 = r33;
                                    r2 = r6;
                                    r27 = r10;
                                    r19 = r11;
                                    r20 = r13;
                                    r6 = r26;
                                    break;
                                } else {
                                    r0 = com.google.android.gms.internal.p001firebaseauthapi.zzabq.zzj(r12, r1, r9);
                                    r10.putInt(r14, r0, com.google.android.gms.internal.p001firebaseauthapi.zzacg.zzs(r9.zza));
                                    r5 = r25 | r23;
                                    r1 = r8;
                                    r2 = r11;
                                    r3 = r13;
                                    break;
                                }
                            case 16:
                                if (r7 != 0) {
                                    r13 = r2;
                                    r11 = r0;
                                    r8 = r17;
                                    r6 = r20;
                                    r7 = r33;
                                    r2 = r6;
                                    r27 = r10;
                                    r19 = r11;
                                    r20 = r13;
                                    r6 = r26;
                                    break;
                                } else {
                                    int r6 = com.google.android.gms.internal.p001firebaseauthapi.zzabq.zzm(r12, r20, r9);
                                    r11 = r0;
                                    r13 = r2;
                                    r8 = r17;
                                    r10.putLong(r29, r0, com.google.android.gms.internal.p001firebaseauthapi.zzacg.zzt(r9.zzb));
                                    r5 = r25 | r23;
                                    r0 = r6;
                                    r1 = r8;
                                    r2 = r11;
                                    r3 = r13;
                                    break;
                                }
                            default:
                                r13 = r2;
                                r11 = r0;
                                r6 = r20;
                                if (r7 != 3) {
                                    r8 = r17;
                                    r7 = r33;
                                    r2 = r6;
                                    r27 = r10;
                                    r19 = r11;
                                    r20 = r13;
                                    r6 = r26;
                                    break;
                                } else {
                                    java.lang.Object r7 = r15.zzI(r14, r13);
                                    r8 = r17;
                                    r0 = com.google.android.gms.internal.p001firebaseauthapi.zzabq.zzn(r7, r15.zzF(r13), r30, r6, r32, (r11 << 3) | 4, r34);
                                    r15.zzR(r14, r13, r7);
                                    r5 = r25 | r23;
                                    r1 = r8;
                                    r2 = r11;
                                    r3 = r13;
                                    break;
                                }
                        }
                        r6 = r26;
                        r13 = r32;
                        r11 = r33;
                    } else {
                        r13 = r2;
                        int r2 = r4;
                        r26 = r6;
                        if (r11 != 27) {
                            r25 = r5;
                            if (r11 <= 49) {
                                r17 = r2;
                                r19 = r0;
                                r27 = r10;
                                r20 = r13;
                                r0 = zzw(r29, r30, r20, r32, r2, r0, r7, r13, r8, r11, r0, r34);
                                if (r0 != r20) {
                                    r15 = r28;
                                    r14 = r29;
                                    r12 = r30;
                                    r13 = r32;
                                    r11 = r33;
                                    r9 = r34;
                                    r1 = r17;
                                    r2 = r19;
                                    r3 = r20;
                                    r5 = r25;
                                    r6 = r26;
                                    r10 = r27;
                                } else {
                                    r7 = r33;
                                    r2 = r0;
                                    r8 = r17;
                                }
                            } else {
                                r19 = r0;
                                r17 = r2;
                                r15 = r20;
                                r27 = r10;
                                r20 = r13;
                                if (r11 != 50) {
                                    r0 = zzu(r29, r30, r15, r32, r17, r19, r7, r8, r11, r0, r20, r34);
                                    if (r0 != r15) {
                                        r15 = r28;
                                        r14 = r29;
                                        r12 = r30;
                                        r13 = r32;
                                        r11 = r33;
                                        r9 = r34;
                                        r1 = r17;
                                        r2 = r19;
                                        r3 = r20;
                                        r5 = r25;
                                        r6 = r26;
                                        r10 = r27;
                                    } else {
                                        r7 = r33;
                                        r2 = r0;
                                        r8 = r17;
                                    }
                                } else if (r7 == 2) {
                                    r0 = zzt(r29, r30, r15, r32, r20, r0, r34);
                                    if (r0 != r15) {
                                        r15 = r28;
                                        r14 = r29;
                                        r12 = r30;
                                        r13 = r32;
                                        r11 = r33;
                                        r9 = r34;
                                        r1 = r17;
                                        r2 = r19;
                                        r3 = r20;
                                        r5 = r25;
                                        r6 = r26;
                                        r10 = r27;
                                    } else {
                                        r7 = r33;
                                        r2 = r0;
                                        r8 = r17;
                                    }
                                }
                            }
                            r6 = r26;
                        } else if (r7 == 2) {
                            com.google.android.gms.internal.p001firebaseauthapi.zzadk r0 = (com.google.android.gms.internal.p001firebaseauthapi.zzadk) r10.getObject(r14, r0);
                            if (!r0.zzc()) {
                                int r7 = r0.size();
                                r0 = r0.zzd(r7 == 0 ? 10 : r7 + r7);
                                r10.putObject(r14, r0, r0);
                            }
                            r25 = r5;
                            r0 = com.google.android.gms.internal.p001firebaseauthapi.zzabq.zze(r15.zzF(r13), r2, r30, r20, r32, r0, r34);
                            r2 = r0;
                            r1 = r2;
                            r3 = r13;
                            r5 = r25;
                            r6 = r26;
                            r13 = r32;
                            r11 = r33;
                        } else {
                            r25 = r5;
                            r19 = r0;
                            r17 = r2;
                            r15 = r20;
                            r27 = r10;
                            r20 = r13;
                        }
                        r7 = r33;
                        r2 = r15;
                        r8 = r17;
                        r6 = r26;
                    }
                }
                if (r8 != r7 || r7 == 0) {
                    if (r28.zzh) {
                        r10 = r34;
                        com.google.android.gms.internal.p001firebaseauthapi.zzacs r0 = r10.zzd;
                        if (r0 != com.google.android.gms.internal.p001firebaseauthapi.zzacs.zza) {
                            r11 = r19;
                            if (r0.zzb(r28.zzg, r11) == null) {
                                r0 = com.google.android.gms.internal.p001firebaseauthapi.zzabq.zzi(r8, r30, r2, r32, zzd(r29), r34);
                                r12 = r29;
                                r13 = r32;
                                r1 = r8;
                                r15 = r28;
                                r9 = r10;
                                r2 = r11;
                                r14 = r12;
                                r3 = r20;
                                r5 = r25;
                                r10 = r27;
                                r12 = r30;
                                r11 = r7;
                            } else {
                                com.google.android.gms.internal.p001firebaseauthapi.zzadc r0 = (com.google.android.gms.internal.p001firebaseauthapi.zzadc) r29;
                                throw null;
                            }
                        } else {
                            r12 = r29;
                        }
                    } else {
                        r12 = r29;
                        r10 = r34;
                    }
                    r11 = r19;
                    r0 = com.google.android.gms.internal.p001firebaseauthapi.zzabq.zzi(r8, r30, r2, r32, zzd(r29), r34);
                    r13 = r32;
                    r1 = r8;
                    r15 = r28;
                    r9 = r10;
                    r2 = r11;
                    r14 = r12;
                    r3 = r20;
                    r5 = r25;
                    r10 = r27;
                    r12 = r30;
                    r11 = r7;
                } else {
                    r9 = r28;
                    r12 = r29;
                    r0 = r6;
                    r5 = r25;
                    r1 = 1048575;
                    r6 = r2;
                }
            } else {
                int r26 = r6;
                r27 = r10;
                r7 = r11;
                r12 = r14;
                r9 = r15;
                r6 = r0;
                r8 = r1;
                r0 = r26;
                r1 = 1048575;
            }
        }
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaew
    public final Object zze() {
        return ((zzadf) this.zzg).zzw();
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaew
    public final void zzf(Object obj) {
        if (zzY(obj)) {
            if (obj instanceof zzadf) {
                zzadf zzadfVar = (zzadf) obj;
                zzadfVar.zzH(Integer.MAX_VALUE);
                zzadfVar.zza = 0;
                zzadfVar.zzF();
            }
            int length = this.zzc.length;
            for (int i = 0; i < length; i += 3) {
                int zzC = zzC(i);
                long j = 1048575 & zzC;
                int zzB = zzB(zzC);
                if (zzB != 9) {
                    switch (zzB) {
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
                            this.zzn.zzb(obj, j);
                            continue;
                        case 50:
                            Unsafe unsafe = zzb;
                            Object object = unsafe.getObject(obj, j);
                            if (object != null) {
                                ((zzaee) object).zzc();
                                unsafe.putObject(obj, j, object);
                            } else {
                                continue;
                            }
                        default:
                    }
                }
                if (zzV(obj, i)) {
                    zzF(i).zzf(zzb.getObject(obj, j));
                }
            }
            this.zzo.zzm(obj);
            if (this.zzh) {
                this.zzp.zze(obj);
            }
        }
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaew
    public final void zzg(Object obj, Object obj2) {
        zzL(obj);
        Objects.requireNonNull(obj2);
        for (int i = 0; i < this.zzc.length; i += 3) {
            int zzC = zzC(i);
            long j = 1048575 & zzC;
            int i2 = this.zzc[i];
            switch (zzB(zzC)) {
                case 0:
                    if (zzV(obj2, i)) {
                        zzafx.zzo(obj, j, zzafx.zza(obj2, j));
                        zzP(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 1:
                    if (zzV(obj2, i)) {
                        zzafx.zzp(obj, j, zzafx.zzb(obj2, j));
                        zzP(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 2:
                    if (zzV(obj2, i)) {
                        zzafx.zzr(obj, j, zzafx.zzd(obj2, j));
                        zzP(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 3:
                    if (zzV(obj2, i)) {
                        zzafx.zzr(obj, j, zzafx.zzd(obj2, j));
                        zzP(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 4:
                    if (zzV(obj2, i)) {
                        zzafx.zzq(obj, j, zzafx.zzc(obj2, j));
                        zzP(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 5:
                    if (zzV(obj2, i)) {
                        zzafx.zzr(obj, j, zzafx.zzd(obj2, j));
                        zzP(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 6:
                    if (zzV(obj2, i)) {
                        zzafx.zzq(obj, j, zzafx.zzc(obj2, j));
                        zzP(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 7:
                    if (zzV(obj2, i)) {
                        zzafx.zzm(obj, j, zzafx.zzw(obj2, j));
                        zzP(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 8:
                    if (zzV(obj2, i)) {
                        zzafx.zzs(obj, j, zzafx.zzf(obj2, j));
                        zzP(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 9:
                    zzM(obj, obj2, i);
                    break;
                case 10:
                    if (zzV(obj2, i)) {
                        zzafx.zzs(obj, j, zzafx.zzf(obj2, j));
                        zzP(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 11:
                    if (zzV(obj2, i)) {
                        zzafx.zzq(obj, j, zzafx.zzc(obj2, j));
                        zzP(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 12:
                    if (zzV(obj2, i)) {
                        zzafx.zzq(obj, j, zzafx.zzc(obj2, j));
                        zzP(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 13:
                    if (zzV(obj2, i)) {
                        zzafx.zzq(obj, j, zzafx.zzc(obj2, j));
                        zzP(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 14:
                    if (zzV(obj2, i)) {
                        zzafx.zzr(obj, j, zzafx.zzd(obj2, j));
                        zzP(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 15:
                    if (zzV(obj2, i)) {
                        zzafx.zzq(obj, j, zzafx.zzc(obj2, j));
                        zzP(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 16:
                    if (zzV(obj2, i)) {
                        zzafx.zzr(obj, j, zzafx.zzd(obj2, j));
                        zzP(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 17:
                    zzM(obj, obj2, i);
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
                    this.zzn.zzc(obj, obj2, j);
                    break;
                case 50:
                    zzaey.zzI(this.zzr, obj, obj2, j);
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
                    if (zzZ(obj2, i2, i)) {
                        zzafx.zzs(obj, j, zzafx.zzf(obj2, j));
                        zzQ(obj, i2, i);
                        break;
                    } else {
                        break;
                    }
                case 60:
                    zzN(obj, obj2, i);
                    break;
                case 61:
                case 62:
                case 63:
                case 64:
                case 65:
                case 66:
                case 67:
                    if (zzZ(obj2, i2, i)) {
                        zzafx.zzs(obj, j, zzafx.zzf(obj2, j));
                        zzQ(obj, i2, i);
                        break;
                    } else {
                        break;
                    }
                case 68:
                    zzN(obj, obj2, i);
                    break;
            }
        }
        zzaey.zzF(this.zzo, obj, obj2);
        if (this.zzh) {
            zzaey.zzE(this.zzp, obj, obj2);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:167:0x061d A[Catch: all -> 0x0612, TryCatch #0 {all -> 0x0612, blocks: (B:151:0x05e8, B:165:0x0618, B:167:0x061d, B:168:0x0622), top: B:191:0x05e8 }] */
    /* JADX WARN: Removed duplicated region for block: B:185:0x0655 A[LOOP:2: B:183:0x0651->B:185:0x0655, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:187:0x0669  */
    /* JADX WARN: Removed duplicated region for block: B:210:0x0628 A[SYNTHETIC] */
    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaew
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void zzh(java.lang.Object r18, com.google.android.gms.internal.p001firebaseauthapi.zzaev r19, com.google.android.gms.internal.p001firebaseauthapi.zzacs r20) throws java.io.IOException {
        java.lang.Object r13;
        com.google.android.gms.internal.p001firebaseauthapi.zzafn r10;
        java.lang.Object r9;
        int r8;
        com.google.android.gms.internal.p001firebaseauthapi.zzafn r3;
        java.lang.Object r2;
        com.google.android.gms.internal.p001firebaseauthapi.zzact r11;
        com.google.android.gms.internal.p001firebaseauthapi.zzacs r14;
        java.lang.Object r13;
        java.lang.Object r15 = r18;
        com.google.android.gms.internal.p001firebaseauthapi.zzacs r6 = r20;
        java.util.Objects.requireNonNull(r20);
        zzL(r18);
        com.google.android.gms.internal.p001firebaseauthapi.zzafn r14 = r17.zzo;
        com.google.android.gms.internal.p001firebaseauthapi.zzact r5 = r17.zzp;
        java.lang.Object r4 = null;
        com.google.android.gms.internal.p001firebaseauthapi.zzacx r8 = null;
        while (true) {
            try {
                int r2 = r19.zzc();
                int r1 = zzx(r2);
                if (r1 >= 0) {
                    r10 = r14;
                    r9 = r15;
                    try {
                        int r3 = zzC(r1);
                        try {
                            switch (zzB(r3)) {
                                case 0:
                                    r13 = r4;
                                    r11 = r5;
                                    r14 = r6;
                                    com.google.android.gms.internal.p001firebaseauthapi.zzafx.zzo(r9, r3 & 1048575, r19.zza());
                                    zzP(r9, r1);
                                    r4 = r13;
                                    break;
                                case 1:
                                    r13 = r4;
                                    r11 = r5;
                                    r14 = r6;
                                    com.google.android.gms.internal.p001firebaseauthapi.zzafx.zzp(r9, r3 & 1048575, r19.zzb());
                                    zzP(r9, r1);
                                    r4 = r13;
                                    break;
                                case 2:
                                    r13 = r4;
                                    r11 = r5;
                                    r14 = r6;
                                    com.google.android.gms.internal.p001firebaseauthapi.zzafx.zzr(r9, r3 & 1048575, r19.zzl());
                                    zzP(r9, r1);
                                    r4 = r13;
                                    break;
                                case 3:
                                    r13 = r4;
                                    r11 = r5;
                                    r14 = r6;
                                    com.google.android.gms.internal.p001firebaseauthapi.zzafx.zzr(r9, r3 & 1048575, r19.zzo());
                                    zzP(r9, r1);
                                    r4 = r13;
                                    break;
                                case 4:
                                    r13 = r4;
                                    r11 = r5;
                                    r14 = r6;
                                    com.google.android.gms.internal.p001firebaseauthapi.zzafx.zzq(r9, r3 & 1048575, r19.zzg());
                                    zzP(r9, r1);
                                    r4 = r13;
                                    break;
                                case 5:
                                    r13 = r4;
                                    r11 = r5;
                                    r14 = r6;
                                    com.google.android.gms.internal.p001firebaseauthapi.zzafx.zzr(r9, r3 & 1048575, r19.zzk());
                                    zzP(r9, r1);
                                    r4 = r13;
                                    break;
                                case 6:
                                    r13 = r4;
                                    r11 = r5;
                                    r14 = r6;
                                    com.google.android.gms.internal.p001firebaseauthapi.zzafx.zzq(r9, r3 & 1048575, r19.zzf());
                                    zzP(r9, r1);
                                    r4 = r13;
                                    break;
                                case 7:
                                    r13 = r4;
                                    r11 = r5;
                                    r14 = r6;
                                    com.google.android.gms.internal.p001firebaseauthapi.zzafx.zzm(r9, r3 & 1048575, r19.zzN());
                                    zzP(r9, r1);
                                    r4 = r13;
                                    break;
                                case 8:
                                    r13 = r4;
                                    r11 = r5;
                                    r14 = r6;
                                    zzO(r9, r3, r19);
                                    zzP(r9, r1);
                                    r4 = r13;
                                    break;
                                case 9:
                                    r13 = r4;
                                    r11 = r5;
                                    r14 = r6;
                                    com.google.android.gms.internal.p001firebaseauthapi.zzaek r2 = (com.google.android.gms.internal.p001firebaseauthapi.zzaek) zzI(r9, r1);
                                    r19.zzu(r2, zzF(r1), r14);
                                    zzR(r9, r1, r2);
                                    r4 = r13;
                                    break;
                                case 10:
                                    r13 = r4;
                                    r11 = r5;
                                    r14 = r6;
                                    com.google.android.gms.internal.p001firebaseauthapi.zzafx.zzs(r9, r3 & 1048575, r19.zzp());
                                    zzP(r9, r1);
                                    r4 = r13;
                                    break;
                                case 11:
                                    r13 = r4;
                                    r11 = r5;
                                    r14 = r6;
                                    com.google.android.gms.internal.p001firebaseauthapi.zzafx.zzq(r9, r3 & 1048575, r19.zzj());
                                    zzP(r9, r1);
                                    r4 = r13;
                                    break;
                                case 12:
                                    r13 = r4;
                                    r11 = r5;
                                    r14 = r6;
                                    int r4 = r19.zze();
                                    com.google.android.gms.internal.p001firebaseauthapi.zzadj r5 = zzE(r1);
                                    if (r5 != null && !r5.zza()) {
                                        r4 = com.google.android.gms.internal.p001firebaseauthapi.zzaey.zzD(r9, r2, r4, r13, r10);
                                        break;
                                    }
                                    com.google.android.gms.internal.p001firebaseauthapi.zzafx.zzq(r9, r3 & 1048575, r4);
                                    zzP(r9, r1);
                                    r4 = r13;
                                    break;
                                case 13:
                                    r13 = r4;
                                    r11 = r5;
                                    r14 = r6;
                                    com.google.android.gms.internal.p001firebaseauthapi.zzafx.zzq(r9, r3 & 1048575, r19.zzh());
                                    zzP(r9, r1);
                                    r4 = r13;
                                    break;
                                case 14:
                                    r13 = r4;
                                    r11 = r5;
                                    r14 = r6;
                                    com.google.android.gms.internal.p001firebaseauthapi.zzafx.zzr(r9, r3 & 1048575, r19.zzm());
                                    zzP(r9, r1);
                                    r4 = r13;
                                    break;
                                case 15:
                                    r13 = r4;
                                    r11 = r5;
                                    r14 = r6;
                                    com.google.android.gms.internal.p001firebaseauthapi.zzafx.zzq(r9, r3 & 1048575, r19.zzi());
                                    zzP(r9, r1);
                                    r4 = r13;
                                    break;
                                case 16:
                                    r13 = r4;
                                    r11 = r5;
                                    r14 = r6;
                                    com.google.android.gms.internal.p001firebaseauthapi.zzafx.zzr(r9, r3 & 1048575, r19.zzn());
                                    zzP(r9, r1);
                                    r4 = r13;
                                    break;
                                case 17:
                                    r13 = r4;
                                    r11 = r5;
                                    r14 = r6;
                                    com.google.android.gms.internal.p001firebaseauthapi.zzaek r2 = (com.google.android.gms.internal.p001firebaseauthapi.zzaek) zzI(r9, r1);
                                    r19.zzt(r2, zzF(r1), r14);
                                    zzR(r9, r1, r2);
                                    r4 = r13;
                                    break;
                                case 18:
                                    r13 = r4;
                                    r11 = r5;
                                    r14 = r6;
                                    r19.zzx(r17.zzn.zza(r9, r3 & 1048575));
                                    r4 = r13;
                                    break;
                                case 19:
                                    r13 = r4;
                                    r11 = r5;
                                    r14 = r6;
                                    r19.zzB(r17.zzn.zza(r9, r3 & 1048575));
                                    r4 = r13;
                                    break;
                                case 20:
                                    r13 = r4;
                                    r11 = r5;
                                    r14 = r6;
                                    r19.zzE(r17.zzn.zza(r9, r3 & 1048575));
                                    r4 = r13;
                                    break;
                                case 21:
                                    r13 = r4;
                                    r11 = r5;
                                    r14 = r6;
                                    r19.zzM(r17.zzn.zza(r9, r3 & 1048575));
                                    r4 = r13;
                                    break;
                                case 22:
                                    r13 = r4;
                                    r11 = r5;
                                    r14 = r6;
                                    r19.zzD(r17.zzn.zza(r9, r3 & 1048575));
                                    r4 = r13;
                                    break;
                                case 23:
                                    r13 = r4;
                                    r11 = r5;
                                    r14 = r6;
                                    r19.zzA(r17.zzn.zza(r9, r3 & 1048575));
                                    r4 = r13;
                                    break;
                                case 24:
                                    r13 = r4;
                                    r11 = r5;
                                    r14 = r6;
                                    r19.zzz(r17.zzn.zza(r9, r3 & 1048575));
                                    r4 = r13;
                                    break;
                                case 25:
                                    r13 = r4;
                                    r11 = r5;
                                    r14 = r6;
                                    r19.zzv(r17.zzn.zza(r9, r3 & 1048575));
                                    r4 = r13;
                                    break;
                                case 26:
                                    r13 = r4;
                                    r11 = r5;
                                    r14 = r6;
                                    if (zzU(r3)) {
                                        ((com.google.android.gms.internal.p001firebaseauthapi.zzach) r19).zzK(r17.zzn.zza(r9, r3 & 1048575), true);
                                    } else {
                                        ((com.google.android.gms.internal.p001firebaseauthapi.zzach) r19).zzK(r17.zzn.zza(r9, r3 & 1048575), false);
                                    }
                                    r4 = r13;
                                    break;
                                case 27:
                                    r13 = r4;
                                    r11 = r5;
                                    r14 = r6;
                                    r19.zzF(r17.zzn.zza(r9, r3 & 1048575), zzF(r1), r14);
                                    r4 = r13;
                                    break;
                                case 28:
                                    r13 = r4;
                                    r11 = r5;
                                    r14 = r6;
                                    r19.zzw(r17.zzn.zza(r9, r3 & 1048575));
                                    r4 = r13;
                                    break;
                                case 29:
                                    r13 = r4;
                                    r11 = r5;
                                    r14 = r6;
                                    r19.zzL(r17.zzn.zza(r9, r3 & 1048575));
                                    r4 = r13;
                                    break;
                                case 30:
                                    r11 = r5;
                                    r14 = r6;
                                    java.util.List r3 = r17.zzn.zza(r9, r3 & 1048575);
                                    r19.zzy(r3);
                                    r4 = com.google.android.gms.internal.p001firebaseauthapi.zzaey.zzC(r18, r2, r3, zzE(r1), r4, r10);
                                    break;
                                case 31:
                                    r13 = r4;
                                    r11 = r5;
                                    r14 = r6;
                                    r19.zzG(r17.zzn.zza(r9, r3 & 1048575));
                                    r4 = r13;
                                    break;
                                case 32:
                                    r13 = r4;
                                    r11 = r5;
                                    r14 = r6;
                                    r19.zzH(r17.zzn.zza(r9, r3 & 1048575));
                                    r4 = r13;
                                    break;
                                case 33:
                                    r13 = r4;
                                    r11 = r5;
                                    r14 = r6;
                                    r19.zzI(r17.zzn.zza(r9, r3 & 1048575));
                                    r4 = r13;
                                    break;
                                case 34:
                                    r13 = r4;
                                    r11 = r5;
                                    r14 = r6;
                                    r19.zzJ(r17.zzn.zza(r9, r3 & 1048575));
                                    r4 = r13;
                                    break;
                                case 35:
                                    r13 = r4;
                                    r11 = r5;
                                    r14 = r6;
                                    r19.zzx(r17.zzn.zza(r9, r3 & 1048575));
                                    r4 = r13;
                                    break;
                                case 36:
                                    r13 = r4;
                                    r11 = r5;
                                    r14 = r6;
                                    r19.zzB(r17.zzn.zza(r9, r3 & 1048575));
                                    r4 = r13;
                                    break;
                                case 37:
                                    r13 = r4;
                                    r11 = r5;
                                    r14 = r6;
                                    r19.zzE(r17.zzn.zza(r9, r3 & 1048575));
                                    r4 = r13;
                                    break;
                                case 38:
                                    r13 = r4;
                                    r11 = r5;
                                    r14 = r6;
                                    r19.zzM(r17.zzn.zza(r9, r3 & 1048575));
                                    r4 = r13;
                                    break;
                                case 39:
                                    r13 = r4;
                                    r11 = r5;
                                    r14 = r6;
                                    r19.zzD(r17.zzn.zza(r9, r3 & 1048575));
                                    r4 = r13;
                                    break;
                                case 40:
                                    r13 = r4;
                                    r11 = r5;
                                    r14 = r6;
                                    r19.zzA(r17.zzn.zza(r9, r3 & 1048575));
                                    r4 = r13;
                                    break;
                                case 41:
                                    r13 = r4;
                                    r11 = r5;
                                    r14 = r6;
                                    r19.zzz(r17.zzn.zza(r9, r3 & 1048575));
                                    r4 = r13;
                                    break;
                                case 42:
                                    r13 = r4;
                                    r11 = r5;
                                    r14 = r6;
                                    r19.zzv(r17.zzn.zza(r9, r3 & 1048575));
                                    r4 = r13;
                                    break;
                                case 43:
                                    r13 = r4;
                                    r11 = r5;
                                    r14 = r6;
                                    r19.zzL(r17.zzn.zza(r9, r3 & 1048575));
                                    r4 = r13;
                                    break;
                                case 44:
                                    java.util.List r3 = r17.zzn.zza(r9, r3 & 1048575);
                                    r19.zzy(r3);
                                    r11 = r5;
                                    r14 = r6;
                                    r4 = com.google.android.gms.internal.p001firebaseauthapi.zzaey.zzC(r18, r2, r3, zzE(r1), r4, r10);
                                    break;
                                case 45:
                                    r19.zzG(r17.zzn.zza(r9, r3 & 1048575));
                                    r13 = r4;
                                    r11 = r5;
                                    r14 = r6;
                                    r4 = r13;
                                    break;
                                case 46:
                                    r19.zzH(r17.zzn.zza(r9, r3 & 1048575));
                                    r13 = r4;
                                    r11 = r5;
                                    r14 = r6;
                                    r4 = r13;
                                    break;
                                case 47:
                                    r19.zzI(r17.zzn.zza(r9, r3 & 1048575));
                                    r13 = r4;
                                    r11 = r5;
                                    r14 = r6;
                                    r4 = r13;
                                    break;
                                case 48:
                                    r19.zzJ(r17.zzn.zza(r9, r3 & 1048575));
                                    r13 = r4;
                                    r11 = r5;
                                    r14 = r6;
                                    r4 = r13;
                                    break;
                                case 49:
                                    r19.zzC(r17.zzn.zza(r9, r3 & 1048575), zzF(r1), r6);
                                    r13 = r4;
                                    r11 = r5;
                                    r14 = r6;
                                    r4 = r13;
                                    break;
                                case 50:
                                    java.lang.Object r2 = zzH(r1);
                                    long r11 = zzC(r1) & 1048575;
                                    java.lang.Object r1 = com.google.android.gms.internal.p001firebaseauthapi.zzafx.zzf(r9, r11);
                                    if (r1 == null) {
                                        r1 = com.google.android.gms.internal.p001firebaseauthapi.zzaee.zza().zzb();
                                        com.google.android.gms.internal.p001firebaseauthapi.zzafx.zzs(r9, r11, r1);
                                    } else if (com.google.android.gms.internal.p001firebaseauthapi.zzaef.zzb(r1)) {
                                        java.lang.Object r3 = com.google.android.gms.internal.p001firebaseauthapi.zzaee.zza().zzb();
                                        com.google.android.gms.internal.p001firebaseauthapi.zzaef.zzc(r3, r1);
                                        com.google.android.gms.internal.p001firebaseauthapi.zzafx.zzs(r9, r11, r3);
                                        r1 = r3;
                                    }
                                    com.google.android.gms.internal.p001firebaseauthapi.zzaee r1 = (com.google.android.gms.internal.p001firebaseauthapi.zzaee) r1;
                                    com.google.android.gms.internal.p001firebaseauthapi.zzaed r2 = (com.google.android.gms.internal.p001firebaseauthapi.zzaed) r2;
                                    throw null;
                                    break;
                                case 51:
                                    com.google.android.gms.internal.p001firebaseauthapi.zzafx.zzs(r9, r3 & 1048575, java.lang.Double.valueOf(r19.zza()));
                                    zzQ(r9, r2, r1);
                                    r13 = r4;
                                    r11 = r5;
                                    r14 = r6;
                                    r4 = r13;
                                    break;
                                case 52:
                                    com.google.android.gms.internal.p001firebaseauthapi.zzafx.zzs(r9, r3 & 1048575, java.lang.Float.valueOf(r19.zzb()));
                                    zzQ(r9, r2, r1);
                                    r13 = r4;
                                    r11 = r5;
                                    r14 = r6;
                                    r4 = r13;
                                    break;
                                case 53:
                                    com.google.android.gms.internal.p001firebaseauthapi.zzafx.zzs(r9, r3 & 1048575, java.lang.Long.valueOf(r19.zzl()));
                                    zzQ(r9, r2, r1);
                                    r13 = r4;
                                    r11 = r5;
                                    r14 = r6;
                                    r4 = r13;
                                    break;
                                case 54:
                                    com.google.android.gms.internal.p001firebaseauthapi.zzafx.zzs(r9, r3 & 1048575, java.lang.Long.valueOf(r19.zzo()));
                                    zzQ(r9, r2, r1);
                                    r13 = r4;
                                    r11 = r5;
                                    r14 = r6;
                                    r4 = r13;
                                    break;
                                case 55:
                                    com.google.android.gms.internal.p001firebaseauthapi.zzafx.zzs(r9, r3 & 1048575, java.lang.Integer.valueOf(r19.zzg()));
                                    zzQ(r9, r2, r1);
                                    r13 = r4;
                                    r11 = r5;
                                    r14 = r6;
                                    r4 = r13;
                                    break;
                                case 56:
                                    com.google.android.gms.internal.p001firebaseauthapi.zzafx.zzs(r9, r3 & 1048575, java.lang.Long.valueOf(r19.zzk()));
                                    zzQ(r9, r2, r1);
                                    r13 = r4;
                                    r11 = r5;
                                    r14 = r6;
                                    r4 = r13;
                                    break;
                                case 57:
                                    com.google.android.gms.internal.p001firebaseauthapi.zzafx.zzs(r9, r3 & 1048575, java.lang.Integer.valueOf(r19.zzf()));
                                    zzQ(r9, r2, r1);
                                    r13 = r4;
                                    r11 = r5;
                                    r14 = r6;
                                    r4 = r13;
                                    break;
                                case 58:
                                    com.google.android.gms.internal.p001firebaseauthapi.zzafx.zzs(r9, r3 & 1048575, java.lang.Boolean.valueOf(r19.zzN()));
                                    zzQ(r9, r2, r1);
                                    r13 = r4;
                                    r11 = r5;
                                    r14 = r6;
                                    r4 = r13;
                                    break;
                                case 59:
                                    zzO(r9, r3, r19);
                                    zzQ(r9, r2, r1);
                                    r13 = r4;
                                    r11 = r5;
                                    r14 = r6;
                                    r4 = r13;
                                    break;
                                case 60:
                                    com.google.android.gms.internal.p001firebaseauthapi.zzaek r3 = (com.google.android.gms.internal.p001firebaseauthapi.zzaek) zzJ(r9, r2, r1);
                                    r19.zzu(r3, zzF(r1), r6);
                                    zzS(r9, r2, r1, r3);
                                    r13 = r4;
                                    r11 = r5;
                                    r14 = r6;
                                    r4 = r13;
                                    break;
                                case 61:
                                    com.google.android.gms.internal.p001firebaseauthapi.zzafx.zzs(r9, r3 & 1048575, r19.zzp());
                                    zzQ(r9, r2, r1);
                                    r13 = r4;
                                    r11 = r5;
                                    r14 = r6;
                                    r4 = r13;
                                    break;
                                case 62:
                                    com.google.android.gms.internal.p001firebaseauthapi.zzafx.zzs(r9, r3 & 1048575, java.lang.Integer.valueOf(r19.zzj()));
                                    zzQ(r9, r2, r1);
                                    r13 = r4;
                                    r11 = r5;
                                    r14 = r6;
                                    r4 = r13;
                                    break;
                                case 63:
                                    int r11 = r19.zze();
                                    com.google.android.gms.internal.p001firebaseauthapi.zzadj r13 = zzE(r1);
                                    if (r13 != null && !r13.zza()) {
                                        r4 = com.google.android.gms.internal.p001firebaseauthapi.zzaey.zzD(r9, r2, r11, r4, r10);
                                        r11 = r5;
                                        r14 = r6;
                                        break;
                                    }
                                    com.google.android.gms.internal.p001firebaseauthapi.zzafx.zzs(r9, r3 & 1048575, java.lang.Integer.valueOf(r11));
                                    zzQ(r9, r2, r1);
                                    r13 = r4;
                                    r11 = r5;
                                    r14 = r6;
                                    r4 = r13;
                                    break;
                                case 64:
                                    com.google.android.gms.internal.p001firebaseauthapi.zzafx.zzs(r9, r3 & 1048575, java.lang.Integer.valueOf(r19.zzh()));
                                    zzQ(r9, r2, r1);
                                    r13 = r4;
                                    r11 = r5;
                                    r14 = r6;
                                    r4 = r13;
                                    break;
                                case 65:
                                    com.google.android.gms.internal.p001firebaseauthapi.zzafx.zzs(r9, r3 & 1048575, java.lang.Long.valueOf(r19.zzm()));
                                    zzQ(r9, r2, r1);
                                    r13 = r4;
                                    r11 = r5;
                                    r14 = r6;
                                    r4 = r13;
                                    break;
                                case 66:
                                    com.google.android.gms.internal.p001firebaseauthapi.zzafx.zzs(r9, r3 & 1048575, java.lang.Integer.valueOf(r19.zzi()));
                                    zzQ(r9, r2, r1);
                                    r13 = r4;
                                    r11 = r5;
                                    r14 = r6;
                                    r4 = r13;
                                    break;
                                case 67:
                                    com.google.android.gms.internal.p001firebaseauthapi.zzafx.zzs(r9, r3 & 1048575, java.lang.Long.valueOf(r19.zzn()));
                                    zzQ(r9, r2, r1);
                                    r13 = r4;
                                    r11 = r5;
                                    r14 = r6;
                                    r4 = r13;
                                    break;
                                case 68:
                                    com.google.android.gms.internal.p001firebaseauthapi.zzaek r3 = (com.google.android.gms.internal.p001firebaseauthapi.zzaek) zzJ(r9, r2, r1);
                                    r19.zzt(r3, zzF(r1), r6);
                                    zzS(r9, r2, r1, r3);
                                    r13 = r4;
                                    r11 = r5;
                                    r14 = r6;
                                    r4 = r13;
                                    break;
                                default:
                                    r13 = r4;
                                    r11 = r5;
                                    r14 = r6;
                                    if (r13 == null) {
                                        try {
                                            r4 = r10.zzc(r9);
                                        } catch (com.google.android.gms.internal.p001firebaseauthapi.zzadm unused) {
                                            r4 = r13;
                                            r10.zzq(r19);
                                            if (r4 == null) {
                                            }
                                            if (!r10.zzp(r4, r19)) {
                                            }
                                            r15 = r9;
                                            r5 = r11;
                                            r6 = r14;
                                            r14 = r10;
                                        } catch (java.lang.Throwable th) {
                                            r0 = th;
                                            r4 = r13;
                                            while (r8 < r17.zzm) {
                                            }
                                            if (r4 != null) {
                                            }
                                            throw r0;
                                        }
                                    } else {
                                        r4 = r13;
                                    }
                                    try {
                                        try {
                                            if (!r10.zzp(r4, r19)) {
                                                for (int r0 = r17.zzl; r0 < r17.zzm; r0++) {
                                                    r4 = zzG(r18, r17.zzk[r0], r4, r10, r18);
                                                }
                                                if (r4 != null) {
                                                    r10.zzn(r9, r4);
                                                    return;
                                                }
                                                return;
                                            }
                                        } catch (java.lang.Throwable th) {
                                            r0 = th;
                                            for (r8 = r17.zzl; r8 < r17.zzm; r8++) {
                                                r4 = zzG(r18, r17.zzk[r8], r4, r10, r18);
                                            }
                                            if (r4 != null) {
                                                r10.zzn(r9, r4);
                                            }
                                            throw r0;
                                        }
                                    } catch (com.google.android.gms.internal.p001firebaseauthapi.zzadm unused) {
                                        r10.zzq(r19);
                                        if (r4 == null) {
                                            r4 = r10.zzc(r9);
                                        }
                                        if (!r10.zzp(r4, r19)) {
                                            for (int r0 = r17.zzl; r0 < r17.zzm; r0++) {
                                                r4 = zzG(r18, r17.zzk[r0], r4, r10, r18);
                                            }
                                            if (r4 != null) {
                                                r10.zzn(r9, r4);
                                                return;
                                            }
                                            return;
                                        }
                                        r15 = r9;
                                        r5 = r11;
                                        r6 = r14;
                                        r14 = r10;
                                    }
                            }
                        } catch (com.google.android.gms.internal.p001firebaseauthapi.zzadm unused) {
                            r13 = r4;
                            r11 = r5;
                            r14 = r6;
                        }
                        r15 = r9;
                        r5 = r11;
                        r6 = r14;
                        r14 = r10;
                    } catch (java.lang.Throwable th) {
                        r0 = th;
                        r13 = r4;
                        r4 = r13;
                        while (r8 < r17.zzm) {
                        }
                        if (r4 != null) {
                        }
                        throw r0;
                    }
                } else if (r2 == Integer.MAX_VALUE) {
                    for (int r0 = r17.zzl; r0 < r17.zzm; r0++) {
                        r4 = zzG(r18, r17.zzk[r0], r4, r14, r18);
                    }
                    if (r4 != null) {
                        r14.zzn(r15, r4);
                        return;
                    }
                    return;
                } else {
                    try {
                        java.lang.Object r11 = !r17.zzh ? null : r5.zzc(r6, r17.zzg, r2);
                        if (r11 != null) {
                            if (r8 == null) {
                                r8 = r5.zzb(r15);
                            }
                            com.google.android.gms.internal.p001firebaseauthapi.zzacx r1 = r8;
                            r3 = r14;
                            r2 = r15;
                            try {
                                r4 = r5.zzd(r18, r19, r11, r20, r1, r4, r3);
                                r8 = r1;
                            } catch (java.lang.Throwable th) {
                                r0 = th;
                                r9 = r2;
                                r10 = r3;
                                r13 = r4;
                                r4 = r13;
                                while (r8 < r17.zzm) {
                                }
                                if (r4 != null) {
                                }
                                throw r0;
                            }
                        } else {
                            r3 = r14;
                            r2 = r15;
                            r3.zzq(r19);
                            if (r4 == null) {
                                r4 = r3.zzc(r2);
                            }
                            try {
                                if (!r3.zzp(r4, r19)) {
                                    int r0 = r17.zzl;
                                    while (r0 < r17.zzm) {
                                        com.google.android.gms.internal.p001firebaseauthapi.zzafn r10 = r3;
                                        r4 = zzG(r18, r17.zzk[r0], r4, r10, r18);
                                        r0++;
                                        r2 = r2;
                                        r3 = r10;
                                    }
                                    java.lang.Object r9 = r2;
                                    com.google.android.gms.internal.p001firebaseauthapi.zzafn r10 = r3;
                                    if (r4 != null) {
                                        r10.zzn(r9, r4);
                                        return;
                                    }
                                    return;
                                }
                            } catch (java.lang.Throwable th) {
                                r0 = th;
                                r9 = r2;
                                r10 = r3;
                                while (r8 < r17.zzm) {
                                }
                                if (r4 != null) {
                                }
                                throw r0;
                            }
                        }
                        r15 = r2;
                        r14 = r3;
                    } catch (java.lang.Throwable th) {
                        r0 = th;
                        r10 = r14;
                        r9 = r15;
                    }
                }
            } catch (java.lang.Throwable th) {
                r0 = th;
                r13 = r4;
                r10 = r14;
                r9 = r15;
            }
        }
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaew
    public final void zzi(Object obj, byte[] bArr, int i, int i2, zzabp zzabpVar) throws IOException {
        if (this.zzj) {
            zzv(obj, bArr, i, i2, zzabpVar);
        } else {
            zzc(obj, bArr, i, i2, 0, zzabpVar);
        }
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaew
    public final boolean zzj(Object obj, Object obj2) {
        boolean zzH;
        int length = this.zzc.length;
        for (int i = 0; i < length; i += 3) {
            int zzC = zzC(i);
            long j = zzC & 1048575;
            switch (zzB(zzC)) {
                case 0:
                    if (zzT(obj, obj2, i) && Double.doubleToLongBits(zzafx.zza(obj, j)) == Double.doubleToLongBits(zzafx.zza(obj2, j))) {
                        continue;
                    }
                    return false;
                case 1:
                    if (zzT(obj, obj2, i) && Float.floatToIntBits(zzafx.zzb(obj, j)) == Float.floatToIntBits(zzafx.zzb(obj2, j))) {
                        continue;
                    }
                    return false;
                case 2:
                    if (zzT(obj, obj2, i) && zzafx.zzd(obj, j) == zzafx.zzd(obj2, j)) {
                        continue;
                    }
                    return false;
                case 3:
                    if (zzT(obj, obj2, i) && zzafx.zzd(obj, j) == zzafx.zzd(obj2, j)) {
                        continue;
                    }
                    return false;
                case 4:
                    if (zzT(obj, obj2, i) && zzafx.zzc(obj, j) == zzafx.zzc(obj2, j)) {
                        continue;
                    }
                    return false;
                case 5:
                    if (zzT(obj, obj2, i) && zzafx.zzd(obj, j) == zzafx.zzd(obj2, j)) {
                        continue;
                    }
                    return false;
                case 6:
                    if (zzT(obj, obj2, i) && zzafx.zzc(obj, j) == zzafx.zzc(obj2, j)) {
                        continue;
                    }
                    return false;
                case 7:
                    if (zzT(obj, obj2, i) && zzafx.zzw(obj, j) == zzafx.zzw(obj2, j)) {
                        continue;
                    }
                    return false;
                case 8:
                    if (zzT(obj, obj2, i) && zzaey.zzH(zzafx.zzf(obj, j), zzafx.zzf(obj2, j))) {
                        continue;
                    }
                    return false;
                case 9:
                    if (zzT(obj, obj2, i) && zzaey.zzH(zzafx.zzf(obj, j), zzafx.zzf(obj2, j))) {
                        continue;
                    }
                    return false;
                case 10:
                    if (zzT(obj, obj2, i) && zzaey.zzH(zzafx.zzf(obj, j), zzafx.zzf(obj2, j))) {
                        continue;
                    }
                    return false;
                case 11:
                    if (zzT(obj, obj2, i) && zzafx.zzc(obj, j) == zzafx.zzc(obj2, j)) {
                        continue;
                    }
                    return false;
                case 12:
                    if (zzT(obj, obj2, i) && zzafx.zzc(obj, j) == zzafx.zzc(obj2, j)) {
                        continue;
                    }
                    return false;
                case 13:
                    if (zzT(obj, obj2, i) && zzafx.zzc(obj, j) == zzafx.zzc(obj2, j)) {
                        continue;
                    }
                    return false;
                case 14:
                    if (zzT(obj, obj2, i) && zzafx.zzd(obj, j) == zzafx.zzd(obj2, j)) {
                        continue;
                    }
                    return false;
                case 15:
                    if (zzT(obj, obj2, i) && zzafx.zzc(obj, j) == zzafx.zzc(obj2, j)) {
                        continue;
                    }
                    return false;
                case 16:
                    if (zzT(obj, obj2, i) && zzafx.zzd(obj, j) == zzafx.zzd(obj2, j)) {
                        continue;
                    }
                    return false;
                case 17:
                    if (zzT(obj, obj2, i) && zzaey.zzH(zzafx.zzf(obj, j), zzafx.zzf(obj2, j))) {
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
                    zzH = zzaey.zzH(zzafx.zzf(obj, j), zzafx.zzf(obj2, j));
                    break;
                case 50:
                    zzH = zzaey.zzH(zzafx.zzf(obj, j), zzafx.zzf(obj2, j));
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
                    long zzz = zzz(i) & 1048575;
                    if (zzafx.zzc(obj, zzz) == zzafx.zzc(obj2, zzz) && zzaey.zzH(zzafx.zzf(obj, j), zzafx.zzf(obj2, j))) {
                        continue;
                    }
                    return false;
                default:
            }
            if (!zzH) {
                return false;
            }
        }
        if (this.zzo.zzd(obj).equals(this.zzo.zzd(obj2))) {
            if (this.zzh) {
                this.zzp.zza(obj);
                this.zzp.zza(obj2);
                throw null;
            }
            return true;
        }
        return false;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaew
    public final boolean zzk(Object obj) {
        int i;
        int i2;
        int i3 = 1048575;
        int i4 = 0;
        int i5 = 0;
        while (i5 < this.zzl) {
            int i6 = this.zzk[i5];
            int i7 = this.zzc[i6];
            int zzC = zzC(i6);
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
            if ((268435456 & zzC) != 0 && !zzW(obj, i6, i, i2, i10)) {
                return false;
            }
            int zzB = zzB(zzC);
            if (zzB != 9 && zzB != 17) {
                if (zzB != 27) {
                    if (zzB == 60 || zzB == 68) {
                        if (zzZ(obj, i7, i6) && !zzX(obj, zzC, zzF(i6))) {
                            return false;
                        }
                    } else if (zzB != 49) {
                        if (zzB == 50 && !((zzaee) zzafx.zzf(obj, zzC & 1048575)).isEmpty()) {
                            zzaed zzaedVar = (zzaed) zzH(i6);
                            throw null;
                        }
                    }
                }
                List list = (List) zzafx.zzf(obj, zzC & 1048575);
                if (list.isEmpty()) {
                    continue;
                } else {
                    zzaew zzF = zzF(i6);
                    for (int i11 = 0; i11 < list.size(); i11++) {
                        if (!zzF.zzk(list.get(i11))) {
                            return false;
                        }
                    }
                    continue;
                }
            } else if (zzW(obj, i6, i, i2, i10) && !zzX(obj, zzC, zzF(i6))) {
                return false;
            }
            i5++;
            i3 = i;
            i4 = i2;
        }
        if (this.zzh) {
            this.zzp.zza(obj);
            throw null;
        }
        return true;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaew
    public final void zzn(Object obj, zzaco zzacoVar) throws IOException {
        if (!this.zzj) {
            zzab(obj, zzacoVar);
        } else if (!this.zzh) {
            int length = this.zzc.length;
            for (int i = 0; i < length; i += 3) {
                int zzC = zzC(i);
                int i2 = this.zzc[i];
                switch (zzB(zzC)) {
                    case 0:
                        if (zzV(obj, i)) {
                            zzacoVar.zzf(i2, zzafx.zza(obj, zzC & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 1:
                        if (zzV(obj, i)) {
                            zzacoVar.zzo(i2, zzafx.zzb(obj, zzC & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 2:
                        if (zzV(obj, i)) {
                            zzacoVar.zzt(i2, zzafx.zzd(obj, zzC & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 3:
                        if (zzV(obj, i)) {
                            zzacoVar.zzJ(i2, zzafx.zzd(obj, zzC & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 4:
                        if (zzV(obj, i)) {
                            zzacoVar.zzr(i2, zzafx.zzc(obj, zzC & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 5:
                        if (zzV(obj, i)) {
                            zzacoVar.zzm(i2, zzafx.zzd(obj, zzC & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 6:
                        if (zzV(obj, i)) {
                            zzacoVar.zzk(i2, zzafx.zzc(obj, zzC & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 7:
                        if (zzV(obj, i)) {
                            zzacoVar.zzb(i2, zzafx.zzw(obj, zzC & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 8:
                        if (zzV(obj, i)) {
                            zzad(i2, zzafx.zzf(obj, zzC & 1048575), zzacoVar);
                            break;
                        } else {
                            break;
                        }
                    case 9:
                        if (zzV(obj, i)) {
                            zzacoVar.zzv(i2, zzafx.zzf(obj, zzC & 1048575), zzF(i));
                            break;
                        } else {
                            break;
                        }
                    case 10:
                        if (zzV(obj, i)) {
                            zzacoVar.zzd(i2, (zzacc) zzafx.zzf(obj, zzC & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 11:
                        if (zzV(obj, i)) {
                            zzacoVar.zzH(i2, zzafx.zzc(obj, zzC & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 12:
                        if (zzV(obj, i)) {
                            zzacoVar.zzi(i2, zzafx.zzc(obj, zzC & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 13:
                        if (zzV(obj, i)) {
                            zzacoVar.zzw(i2, zzafx.zzc(obj, zzC & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 14:
                        if (zzV(obj, i)) {
                            zzacoVar.zzy(i2, zzafx.zzd(obj, zzC & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 15:
                        if (zzV(obj, i)) {
                            zzacoVar.zzA(i2, zzafx.zzc(obj, zzC & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 16:
                        if (zzV(obj, i)) {
                            zzacoVar.zzC(i2, zzafx.zzd(obj, zzC & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 17:
                        if (zzV(obj, i)) {
                            zzacoVar.zzq(i2, zzafx.zzf(obj, zzC & 1048575), zzF(i));
                            break;
                        } else {
                            break;
                        }
                    case 18:
                        zzaey.zzL(i2, (List) zzafx.zzf(obj, zzC & 1048575), zzacoVar, false);
                        break;
                    case 19:
                        zzaey.zzP(i2, (List) zzafx.zzf(obj, zzC & 1048575), zzacoVar, false);
                        break;
                    case 20:
                        zzaey.zzS(i2, (List) zzafx.zzf(obj, zzC & 1048575), zzacoVar, false);
                        break;
                    case 21:
                        zzaey.zzaa(i2, (List) zzafx.zzf(obj, zzC & 1048575), zzacoVar, false);
                        break;
                    case 22:
                        zzaey.zzR(i2, (List) zzafx.zzf(obj, zzC & 1048575), zzacoVar, false);
                        break;
                    case 23:
                        zzaey.zzO(i2, (List) zzafx.zzf(obj, zzC & 1048575), zzacoVar, false);
                        break;
                    case 24:
                        zzaey.zzN(i2, (List) zzafx.zzf(obj, zzC & 1048575), zzacoVar, false);
                        break;
                    case 25:
                        zzaey.zzJ(i2, (List) zzafx.zzf(obj, zzC & 1048575), zzacoVar, false);
                        break;
                    case 26:
                        zzaey.zzY(i2, (List) zzafx.zzf(obj, zzC & 1048575), zzacoVar);
                        break;
                    case 27:
                        zzaey.zzT(i2, (List) zzafx.zzf(obj, zzC & 1048575), zzacoVar, zzF(i));
                        break;
                    case 28:
                        zzaey.zzK(i2, (List) zzafx.zzf(obj, zzC & 1048575), zzacoVar);
                        break;
                    case 29:
                        zzaey.zzZ(i2, (List) zzafx.zzf(obj, zzC & 1048575), zzacoVar, false);
                        break;
                    case 30:
                        zzaey.zzM(i2, (List) zzafx.zzf(obj, zzC & 1048575), zzacoVar, false);
                        break;
                    case 31:
                        zzaey.zzU(i2, (List) zzafx.zzf(obj, zzC & 1048575), zzacoVar, false);
                        break;
                    case 32:
                        zzaey.zzV(i2, (List) zzafx.zzf(obj, zzC & 1048575), zzacoVar, false);
                        break;
                    case 33:
                        zzaey.zzW(i2, (List) zzafx.zzf(obj, zzC & 1048575), zzacoVar, false);
                        break;
                    case 34:
                        zzaey.zzX(i2, (List) zzafx.zzf(obj, zzC & 1048575), zzacoVar, false);
                        break;
                    case 35:
                        zzaey.zzL(i2, (List) zzafx.zzf(obj, zzC & 1048575), zzacoVar, true);
                        break;
                    case 36:
                        zzaey.zzP(i2, (List) zzafx.zzf(obj, zzC & 1048575), zzacoVar, true);
                        break;
                    case 37:
                        zzaey.zzS(i2, (List) zzafx.zzf(obj, zzC & 1048575), zzacoVar, true);
                        break;
                    case 38:
                        zzaey.zzaa(i2, (List) zzafx.zzf(obj, zzC & 1048575), zzacoVar, true);
                        break;
                    case 39:
                        zzaey.zzR(i2, (List) zzafx.zzf(obj, zzC & 1048575), zzacoVar, true);
                        break;
                    case 40:
                        zzaey.zzO(i2, (List) zzafx.zzf(obj, zzC & 1048575), zzacoVar, true);
                        break;
                    case 41:
                        zzaey.zzN(i2, (List) zzafx.zzf(obj, zzC & 1048575), zzacoVar, true);
                        break;
                    case 42:
                        zzaey.zzJ(i2, (List) zzafx.zzf(obj, zzC & 1048575), zzacoVar, true);
                        break;
                    case 43:
                        zzaey.zzZ(i2, (List) zzafx.zzf(obj, zzC & 1048575), zzacoVar, true);
                        break;
                    case 44:
                        zzaey.zzM(i2, (List) zzafx.zzf(obj, zzC & 1048575), zzacoVar, true);
                        break;
                    case 45:
                        zzaey.zzU(i2, (List) zzafx.zzf(obj, zzC & 1048575), zzacoVar, true);
                        break;
                    case 46:
                        zzaey.zzV(i2, (List) zzafx.zzf(obj, zzC & 1048575), zzacoVar, true);
                        break;
                    case 47:
                        zzaey.zzW(i2, (List) zzafx.zzf(obj, zzC & 1048575), zzacoVar, true);
                        break;
                    case 48:
                        zzaey.zzX(i2, (List) zzafx.zzf(obj, zzC & 1048575), zzacoVar, true);
                        break;
                    case 49:
                        zzaey.zzQ(i2, (List) zzafx.zzf(obj, zzC & 1048575), zzacoVar, zzF(i));
                        break;
                    case 50:
                        zzac(zzacoVar, i2, zzafx.zzf(obj, zzC & 1048575), i);
                        break;
                    case 51:
                        if (zzZ(obj, i2, i)) {
                            zzacoVar.zzf(i2, zzo(obj, zzC & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 52:
                        if (zzZ(obj, i2, i)) {
                            zzacoVar.zzo(i2, zzp(obj, zzC & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 53:
                        if (zzZ(obj, i2, i)) {
                            zzacoVar.zzt(i2, zzD(obj, zzC & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 54:
                        if (zzZ(obj, i2, i)) {
                            zzacoVar.zzJ(i2, zzD(obj, zzC & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 55:
                        if (zzZ(obj, i2, i)) {
                            zzacoVar.zzr(i2, zzs(obj, zzC & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 56:
                        if (zzZ(obj, i2, i)) {
                            zzacoVar.zzm(i2, zzD(obj, zzC & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 57:
                        if (zzZ(obj, i2, i)) {
                            zzacoVar.zzk(i2, zzs(obj, zzC & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 58:
                        if (zzZ(obj, i2, i)) {
                            zzacoVar.zzb(i2, zzaa(obj, zzC & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 59:
                        if (zzZ(obj, i2, i)) {
                            zzad(i2, zzafx.zzf(obj, zzC & 1048575), zzacoVar);
                            break;
                        } else {
                            break;
                        }
                    case 60:
                        if (zzZ(obj, i2, i)) {
                            zzacoVar.zzv(i2, zzafx.zzf(obj, zzC & 1048575), zzF(i));
                            break;
                        } else {
                            break;
                        }
                    case 61:
                        if (zzZ(obj, i2, i)) {
                            zzacoVar.zzd(i2, (zzacc) zzafx.zzf(obj, zzC & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 62:
                        if (zzZ(obj, i2, i)) {
                            zzacoVar.zzH(i2, zzs(obj, zzC & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 63:
                        if (zzZ(obj, i2, i)) {
                            zzacoVar.zzi(i2, zzs(obj, zzC & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 64:
                        if (zzZ(obj, i2, i)) {
                            zzacoVar.zzw(i2, zzs(obj, zzC & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 65:
                        if (zzZ(obj, i2, i)) {
                            zzacoVar.zzy(i2, zzD(obj, zzC & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 66:
                        if (zzZ(obj, i2, i)) {
                            zzacoVar.zzA(i2, zzs(obj, zzC & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 67:
                        if (zzZ(obj, i2, i)) {
                            zzacoVar.zzC(i2, zzD(obj, zzC & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 68:
                        if (zzZ(obj, i2, i)) {
                            zzacoVar.zzq(i2, zzafx.zzf(obj, zzC & 1048575), zzF(i));
                            break;
                        } else {
                            break;
                        }
                }
            }
            zzafn zzafnVar = this.zzo;
            zzafnVar.zzr(zzafnVar.zzd(obj), zzacoVar);
        } else {
            this.zzp.zza(obj);
            throw null;
        }
    }
}
