package com.google.android.gms.internal.p001firebaseauthapi;

import java.io.IOException;
import java.util.Arrays;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzafo  reason: invalid package */
/* loaded from: classes.dex */
public final class zzafo {
    private static final zzafo zza = new zzafo(0, new int[0], new Object[0], false);
    private int zzb;
    private int[] zzc;
    private Object[] zzd;
    private int zze;
    private boolean zzf;

    private zzafo() {
        this(0, new int[8], new Object[8], true);
    }

    private zzafo(int i, int[] iArr, Object[] objArr, boolean z) {
        this.zze = -1;
        this.zzb = i;
        this.zzc = iArr;
        this.zzd = objArr;
        this.zzf = z;
    }

    public static zzafo zzc() {
        return zza;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzafo zze(zzafo zzafoVar, zzafo zzafoVar2) {
        int i = zzafoVar.zzb + zzafoVar2.zzb;
        int[] copyOf = Arrays.copyOf(zzafoVar.zzc, i);
        System.arraycopy(zzafoVar2.zzc, 0, copyOf, zzafoVar.zzb, zzafoVar2.zzb);
        Object[] copyOf2 = Arrays.copyOf(zzafoVar.zzd, i);
        System.arraycopy(zzafoVar2.zzd, 0, copyOf2, zzafoVar.zzb, zzafoVar2.zzb);
        return new zzafo(i, copyOf, copyOf2, true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzafo zzf() {
        return new zzafo(0, new int[8], new Object[8], true);
    }

    private final void zzl(int i) {
        int[] iArr = this.zzc;
        if (i > iArr.length) {
            int i2 = this.zzb;
            int i3 = i2 + (i2 / 2);
            if (i3 >= i) {
                i = i3;
            }
            if (i < 8) {
                i = 8;
            }
            this.zzc = Arrays.copyOf(iArr, i);
            this.zzd = Arrays.copyOf(this.zzd, i);
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && (obj instanceof zzafo)) {
            zzafo zzafoVar = (zzafo) obj;
            int i = this.zzb;
            if (i == zzafoVar.zzb) {
                int[] iArr = this.zzc;
                int[] iArr2 = zzafoVar.zzc;
                int i2 = 0;
                while (true) {
                    if (i2 >= i) {
                        Object[] objArr = this.zzd;
                        Object[] objArr2 = zzafoVar.zzd;
                        int i3 = this.zzb;
                        for (int i4 = 0; i4 < i3; i4++) {
                            if (objArr[i4].equals(objArr2[i4])) {
                            }
                        }
                        return true;
                    } else if (iArr[i2] != iArr2[i2]) {
                        break;
                    } else {
                        i2++;
                    }
                }
            }
            return false;
        }
        return false;
    }

    public final int hashCode() {
        int i = this.zzb;
        int i2 = (i + 527) * 31;
        int[] iArr = this.zzc;
        int i3 = 17;
        int i4 = 17;
        for (int i5 = 0; i5 < i; i5++) {
            i4 = (i4 * 31) + iArr[i5];
        }
        int i6 = (i2 + i4) * 31;
        Object[] objArr = this.zzd;
        int i7 = this.zzb;
        for (int i8 = 0; i8 < i7; i8++) {
            i3 = (i3 * 31) + objArr[i8].hashCode();
        }
        return i6 + i3;
    }

    public final int zza() {
        int zzE;
        int zzF;
        int i;
        int i2 = this.zze;
        if (i2 == -1) {
            int i3 = 0;
            for (int i4 = 0; i4 < this.zzb; i4++) {
                int i5 = this.zzc[i4];
                int i6 = i5 >>> 3;
                int i7 = i5 & 7;
                if (i7 != 0) {
                    if (i7 == 1) {
                        ((Long) this.zzd[i4]).longValue();
                        i = zzacn.zzE(i6 << 3) + 8;
                    } else if (i7 == 2) {
                        int zzE2 = zzacn.zzE(i6 << 3);
                        int zzd = ((zzacc) this.zzd[i4]).zzd();
                        i3 += zzE2 + zzacn.zzE(zzd) + zzd;
                    } else if (i7 == 3) {
                        int zzD = zzacn.zzD(i6);
                        zzE = zzD + zzD;
                        zzF = ((zzafo) this.zzd[i4]).zza();
                    } else if (i7 == 5) {
                        ((Integer) this.zzd[i4]).intValue();
                        i = zzacn.zzE(i6 << 3) + 4;
                    } else {
                        throw new IllegalStateException(zzadn.zza());
                    }
                    i3 += i;
                } else {
                    long longValue = ((Long) this.zzd[i4]).longValue();
                    zzE = zzacn.zzE(i6 << 3);
                    zzF = zzacn.zzF(longValue);
                }
                i = zzE + zzF;
                i3 += i;
            }
            this.zze = i3;
            return i3;
        }
        return i2;
    }

    public final int zzb() {
        int i = this.zze;
        if (i == -1) {
            int i2 = 0;
            for (int i3 = 0; i3 < this.zzb; i3++) {
                int i4 = this.zzc[i3];
                int zzE = zzacn.zzE(8);
                int zzd = ((zzacc) this.zzd[i3]).zzd();
                i2 += zzE + zzE + zzacn.zzE(16) + zzacn.zzE(i4 >>> 3) + zzacn.zzE(24) + zzacn.zzE(zzd) + zzd;
            }
            this.zze = i2;
            return i2;
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzafo zzd(zzafo zzafoVar) {
        if (zzafoVar.equals(zza)) {
            return this;
        }
        zzg();
        int i = this.zzb + zzafoVar.zzb;
        zzl(i);
        System.arraycopy(zzafoVar.zzc, 0, this.zzc, this.zzb, zzafoVar.zzb);
        System.arraycopy(zzafoVar.zzd, 0, this.zzd, this.zzb, zzafoVar.zzb);
        this.zzb = i;
        return this;
    }

    final void zzg() {
        if (!this.zzf) {
            throw new UnsupportedOperationException();
        }
    }

    public final void zzh() {
        this.zzf = false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzi(StringBuilder sb, int i) {
        for (int i2 = 0; i2 < this.zzb; i2++) {
            zzaem.zzb(sb, i, String.valueOf(this.zzc[i2] >>> 3), this.zzd[i2]);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzj(int i, Object obj) {
        zzg();
        zzl(this.zzb + 1);
        int[] iArr = this.zzc;
        int i2 = this.zzb;
        iArr[i2] = i;
        this.zzd[i2] = obj;
        this.zzb = i2 + 1;
    }

    public final void zzk(zzaco zzacoVar) throws IOException {
        if (this.zzb != 0) {
            for (int i = 0; i < this.zzb; i++) {
                int i2 = this.zzc[i];
                Object obj = this.zzd[i];
                int i3 = i2 >>> 3;
                int i4 = i2 & 7;
                if (i4 == 0) {
                    zzacoVar.zzt(i3, ((Long) obj).longValue());
                } else if (i4 == 1) {
                    zzacoVar.zzm(i3, ((Long) obj).longValue());
                } else if (i4 == 2) {
                    zzacoVar.zzd(i3, (zzacc) obj);
                } else if (i4 == 3) {
                    zzacoVar.zzE(i3);
                    ((zzafo) obj).zzk(zzacoVar);
                    zzacoVar.zzh(i3);
                } else if (i4 == 5) {
                    zzacoVar.zzk(i3, ((Integer) obj).intValue());
                } else {
                    throw new RuntimeException(zzadn.zza());
                }
            }
        }
    }
}
