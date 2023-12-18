package com.google.android.gms.internal.clearcut;

import com.google.android.gms.internal.clearcut.zzcg;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import sun.misc.Unsafe;
/* loaded from: classes.dex */
final class zzds<T> implements zzef<T> {
    private static final Unsafe zzmh = zzfd.zzef();
    private final int[] zzmi;
    private final Object[] zzmj;
    private final int zzmk;
    private final int zzml;
    private final int zzmm;
    private final zzdo zzmn;
    private final boolean zzmo;
    private final boolean zzmp;
    private final boolean zzmq;
    private final boolean zzmr;
    private final int[] zzms;
    private final int[] zzmt;
    private final int[] zzmu;
    private final zzdw zzmv;
    private final zzcy zzmw;
    private final zzex<?, ?> zzmx;
    private final zzbu<?> zzmy;
    private final zzdj zzmz;

    private zzds(int[] iArr, Object[] objArr, int i, int i2, int i3, zzdo zzdoVar, boolean z, boolean z2, int[] iArr2, int[] iArr3, int[] iArr4, zzdw zzdwVar, zzcy zzcyVar, zzex<?, ?> zzexVar, zzbu<?> zzbuVar, zzdj zzdjVar) {
        this.zzmi = iArr;
        this.zzmj = objArr;
        this.zzmk = i;
        this.zzml = i2;
        this.zzmm = i3;
        this.zzmp = zzdoVar instanceof zzcg;
        this.zzmq = z;
        this.zzmo = zzbuVar != null && zzbuVar.zze(zzdoVar);
        this.zzmr = false;
        this.zzms = iArr2;
        this.zzmt = iArr3;
        this.zzmu = iArr4;
        this.zzmv = zzdwVar;
        this.zzmw = zzcyVar;
        this.zzmx = zzexVar;
        this.zzmy = zzbuVar;
        this.zzmn = zzdoVar;
        this.zzmz = zzdjVar;
    }

    private static int zza(int i, byte[] bArr, int i2, int i3, Object obj, zzay zzayVar) throws IOException {
        return zzax.zza(i, bArr, i2, i3, zzn(obj), zzayVar);
    }

    private static int zza(zzef<?> zzefVar, int i, byte[] bArr, int i2, int i3, zzcn<?> zzcnVar, zzay zzayVar) throws IOException {
        int zza = zza((zzef) zzefVar, bArr, i2, i3, zzayVar);
        while (true) {
            zzcnVar.add(zzayVar.zzff);
            if (zza >= i3) {
                break;
            }
            int zza2 = zzax.zza(bArr, zza, zzayVar);
            if (i != zzayVar.zzfd) {
                break;
            }
            zza = zza((zzef) zzefVar, bArr, zza2, i3, zzayVar);
        }
        return zza;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static int zza(zzef zzefVar, byte[] bArr, int i, int i2, int i3, zzay zzayVar) throws IOException {
        zzds zzdsVar = (zzds) zzefVar;
        Object newInstance = zzdsVar.newInstance();
        int zza = zzdsVar.zza((zzds) newInstance, bArr, i, i2, i3, zzayVar);
        zzdsVar.zzc(newInstance);
        zzayVar.zzff = newInstance;
        return zza;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static int zza(zzef zzefVar, byte[] bArr, int i, int i2, zzay zzayVar) throws IOException {
        int i3 = i + 1;
        int i4 = bArr[i];
        if (i4 < 0) {
            i3 = zzax.zza(i4, bArr, i3, zzayVar);
            i4 = zzayVar.zzfd;
        }
        int i5 = i3;
        if (i4 < 0 || i4 > i2 - i5) {
            throw zzco.zzbl();
        }
        Object newInstance = zzefVar.newInstance();
        int i6 = i4 + i5;
        zzefVar.zza(newInstance, bArr, i5, i6, zzayVar);
        zzefVar.zzc(newInstance);
        zzayVar.zzff = newInstance;
        return i6;
    }

    private static <UT, UB> int zza(zzex<UT, UB> zzexVar, T t) {
        return zzexVar.zzm(zzexVar.zzq(t));
    }

    private final int zza(T t, byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, int i7, long j, int i8, zzay zzayVar) throws IOException {
        Object valueOf;
        Object valueOf2;
        int zzb;
        long j2;
        int i9;
        Object valueOf3;
        int i10;
        Unsafe unsafe = zzmh;
        long j3 = this.zzmi[i8 + 2] & 1048575;
        switch (i7) {
            case 51:
                if (i5 == 1) {
                    valueOf = Double.valueOf(zzax.zze(bArr, i));
                    unsafe.putObject(t, j, valueOf);
                    zzb = i + 8;
                    unsafe.putInt(t, j3, i4);
                    return zzb;
                }
                return i;
            case 52:
                if (i5 == 5) {
                    valueOf2 = Float.valueOf(zzax.zzf(bArr, i));
                    unsafe.putObject(t, j, valueOf2);
                    zzb = i + 4;
                    unsafe.putInt(t, j3, i4);
                    return zzb;
                }
                return i;
            case 53:
            case 54:
                if (i5 == 0) {
                    zzb = zzax.zzb(bArr, i, zzayVar);
                    j2 = zzayVar.zzfe;
                    valueOf3 = Long.valueOf(j2);
                    unsafe.putObject(t, j, valueOf3);
                    unsafe.putInt(t, j3, i4);
                    return zzb;
                }
                return i;
            case 55:
            case 62:
                if (i5 == 0) {
                    zzb = zzax.zza(bArr, i, zzayVar);
                    i9 = zzayVar.zzfd;
                    valueOf3 = Integer.valueOf(i9);
                    unsafe.putObject(t, j, valueOf3);
                    unsafe.putInt(t, j3, i4);
                    return zzb;
                }
                return i;
            case 56:
            case 65:
                if (i5 == 1) {
                    valueOf = Long.valueOf(zzax.zzd(bArr, i));
                    unsafe.putObject(t, j, valueOf);
                    zzb = i + 8;
                    unsafe.putInt(t, j3, i4);
                    return zzb;
                }
                return i;
            case 57:
            case 64:
                if (i5 == 5) {
                    valueOf2 = Integer.valueOf(zzax.zzc(bArr, i));
                    unsafe.putObject(t, j, valueOf2);
                    zzb = i + 4;
                    unsafe.putInt(t, j3, i4);
                    return zzb;
                }
                return i;
            case 58:
                if (i5 == 0) {
                    zzb = zzax.zzb(bArr, i, zzayVar);
                    valueOf3 = Boolean.valueOf(zzayVar.zzfe != 0);
                    unsafe.putObject(t, j, valueOf3);
                    unsafe.putInt(t, j3, i4);
                    return zzb;
                }
                return i;
            case 59:
                if (i5 == 2) {
                    zzb = zzax.zza(bArr, i, zzayVar);
                    i10 = zzayVar.zzfd;
                    if (i10 == 0) {
                        valueOf3 = "";
                        unsafe.putObject(t, j, valueOf3);
                        unsafe.putInt(t, j3, i4);
                        return zzb;
                    } else if ((i6 & 536870912) == 0 || zzff.zze(bArr, zzb, zzb + i10)) {
                        unsafe.putObject(t, j, new String(bArr, zzb, i10, zzci.UTF_8));
                        zzb += i10;
                        unsafe.putInt(t, j3, i4);
                        return zzb;
                    } else {
                        throw zzco.zzbp();
                    }
                }
                return i;
            case 60:
                if (i5 == 2) {
                    zzb = zza(zzad(i8), bArr, i, i2, zzayVar);
                    Object object = unsafe.getInt(t, j3) == i4 ? unsafe.getObject(t, j) : null;
                    valueOf3 = zzayVar.zzff;
                    if (object != null) {
                        valueOf3 = zzci.zza(object, valueOf3);
                    }
                    unsafe.putObject(t, j, valueOf3);
                    unsafe.putInt(t, j3, i4);
                    return zzb;
                }
                return i;
            case 61:
                if (i5 == 2) {
                    zzb = zzax.zza(bArr, i, zzayVar);
                    i10 = zzayVar.zzfd;
                    if (i10 == 0) {
                        valueOf3 = zzbb.zzfi;
                        unsafe.putObject(t, j, valueOf3);
                        unsafe.putInt(t, j3, i4);
                        return zzb;
                    }
                    unsafe.putObject(t, j, zzbb.zzb(bArr, zzb, i10));
                    zzb += i10;
                    unsafe.putInt(t, j3, i4);
                    return zzb;
                }
                return i;
            case 63:
                if (i5 == 0) {
                    int zza = zzax.zza(bArr, i, zzayVar);
                    int i11 = zzayVar.zzfd;
                    zzck<?> zzaf = zzaf(i8);
                    if (zzaf != null && zzaf.zzb(i11) == null) {
                        zzn(t).zzb(i3, Long.valueOf(i11));
                        return zza;
                    }
                    unsafe.putObject(t, j, Integer.valueOf(i11));
                    zzb = zza;
                    unsafe.putInt(t, j3, i4);
                    return zzb;
                }
                return i;
            case 66:
                if (i5 == 0) {
                    zzb = zzax.zza(bArr, i, zzayVar);
                    i9 = zzbk.zzm(zzayVar.zzfd);
                    valueOf3 = Integer.valueOf(i9);
                    unsafe.putObject(t, j, valueOf3);
                    unsafe.putInt(t, j3, i4);
                    return zzb;
                }
                return i;
            case 67:
                if (i5 == 0) {
                    zzb = zzax.zzb(bArr, i, zzayVar);
                    j2 = zzbk.zza(zzayVar.zzfe);
                    valueOf3 = Long.valueOf(j2);
                    unsafe.putObject(t, j, valueOf3);
                    unsafe.putInt(t, j3, i4);
                    return zzb;
                }
                return i;
            case 68:
                if (i5 == 3) {
                    zzb = zza(zzad(i8), bArr, i, i2, (i3 & (-8)) | 4, zzayVar);
                    Object object2 = unsafe.getInt(t, j3) == i4 ? unsafe.getObject(t, j) : null;
                    valueOf3 = zzayVar.zzff;
                    if (object2 != null) {
                        valueOf3 = zzci.zza(object2, valueOf3);
                    }
                    unsafe.putObject(t, j, valueOf3);
                    unsafe.putInt(t, j3, i4);
                    return zzb;
                }
                return i;
            default:
                return i;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:124:0x0233, code lost:
        if (r29.zzfe != 0) goto L141;
     */
    /* JADX WARN: Code restructure failed: missing block: B:125:0x0235, code lost:
        r6 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:126:0x0237, code lost:
        r6 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:127:0x0238, code lost:
        r12.addBoolean(r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:128:0x023b, code lost:
        if (r4 >= r19) goto L254;
     */
    /* JADX WARN: Code restructure failed: missing block: B:129:0x023d, code lost:
        r6 = com.google.android.gms.internal.clearcut.zzax.zza(r17, r4, r29);
     */
    /* JADX WARN: Code restructure failed: missing block: B:130:0x0243, code lost:
        if (r20 != r29.zzfd) goto L254;
     */
    /* JADX WARN: Code restructure failed: missing block: B:131:0x0245, code lost:
        r4 = com.google.android.gms.internal.clearcut.zzax.zzb(r17, r6, r29);
     */
    /* JADX WARN: Code restructure failed: missing block: B:132:0x024d, code lost:
        if (r29.zzfe == 0) goto L148;
     */
    /* JADX WARN: Code restructure failed: missing block: B:242:?, code lost:
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:243:?, code lost:
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x0137, code lost:
        if (r4 == 0) goto L74;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x0139, code lost:
        r12.add(com.google.android.gms.internal.clearcut.zzbb.zzfi);
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x013f, code lost:
        r12.add(com.google.android.gms.internal.clearcut.zzbb.zzb(r17, r1, r4));
        r1 = r1 + r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x0147, code lost:
        if (r1 >= r19) goto L81;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x0149, code lost:
        r4 = com.google.android.gms.internal.clearcut.zzax.zza(r17, r1, r29);
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x014f, code lost:
        if (r20 != r29.zzfd) goto L80;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x0151, code lost:
        r1 = com.google.android.gms.internal.clearcut.zzax.zza(r17, r4, r29);
        r4 = r29.zzfd;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x0157, code lost:
        if (r4 != 0) goto L82;
     */
    /* JADX WARN: Removed duplicated region for block: B:245:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:247:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:83:0x019a  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x01d4  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:100:0x01e2 -> B:91:0x01bb). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:126:0x0237 -> B:127:0x0238). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:132:0x024d -> B:125:0x0235). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:65:0x013f -> B:66:0x0147). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:70:0x0157 -> B:64:0x0139). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:81:0x0194 -> B:82:0x0198). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:86:0x01a8 -> B:79:0x0189). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:95:0x01ce -> B:96:0x01d2). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final int zza(T r16, byte[] r17, int r18, int r19, int r20, int r21, int r22, int r23, long r24, int r26, long r27, com.google.android.gms.internal.clearcut.zzay r29) throws java.io.IOException {
        int r1;
        int r2;
        int r1;
        int r1;
        int r4 = r18;
        sun.misc.Unsafe r11 = com.google.android.gms.internal.clearcut.zzds.zzmh;
        com.google.android.gms.internal.clearcut.zzcn r12 = (com.google.android.gms.internal.clearcut.zzcn) r11.getObject(r16, r27);
        if (!r12.zzu()) {
            int r13 = r12.size();
            r12 = r12.zzi(r13 == 0 ? 10 : r13 << 1);
            r11.putObject(r16, r27, r12);
        }
        switch (r26) {
            case 18:
            case 35:
                if (r22 == 2) {
                    com.google.android.gms.internal.clearcut.zzbq r12 = (com.google.android.gms.internal.clearcut.zzbq) r12;
                    int r1 = com.google.android.gms.internal.clearcut.zzax.zza(r17, r4, r29);
                    int r2 = r29.zzfd + r1;
                    while (r1 < r2) {
                        r12.zzc(com.google.android.gms.internal.clearcut.zzax.zze(r17, r1));
                        r1 += 8;
                    }
                    if (r1 == r2) {
                        return r1;
                    }
                    throw com.google.android.gms.internal.clearcut.zzco.zzbl();
                }
                if (r22 == 1) {
                    com.google.android.gms.internal.clearcut.zzbq r12 = (com.google.android.gms.internal.clearcut.zzbq) r12;
                    r12.zzc(com.google.android.gms.internal.clearcut.zzax.zze(r17, r18));
                    while (true) {
                        int r1 = r4 + 8;
                        if (r1 >= r19) {
                            return r1;
                        }
                        r4 = com.google.android.gms.internal.clearcut.zzax.zza(r17, r1, r29);
                        if (r20 != r29.zzfd) {
                            return r1;
                        }
                        r12.zzc(com.google.android.gms.internal.clearcut.zzax.zze(r17, r4));
                    }
                }
                return r4;
            case 19:
            case 36:
                if (r22 == 2) {
                    com.google.android.gms.internal.clearcut.zzce r12 = (com.google.android.gms.internal.clearcut.zzce) r12;
                    int r1 = com.google.android.gms.internal.clearcut.zzax.zza(r17, r4, r29);
                    int r2 = r29.zzfd + r1;
                    while (r1 < r2) {
                        r12.zzc(com.google.android.gms.internal.clearcut.zzax.zzf(r17, r1));
                        r1 += 4;
                    }
                    if (r1 == r2) {
                        return r1;
                    }
                    throw com.google.android.gms.internal.clearcut.zzco.zzbl();
                }
                if (r22 == 5) {
                    com.google.android.gms.internal.clearcut.zzce r12 = (com.google.android.gms.internal.clearcut.zzce) r12;
                    r12.zzc(com.google.android.gms.internal.clearcut.zzax.zzf(r17, r18));
                    while (true) {
                        int r1 = r4 + 4;
                        if (r1 >= r19) {
                            return r1;
                        }
                        r4 = com.google.android.gms.internal.clearcut.zzax.zza(r17, r1, r29);
                        if (r20 != r29.zzfd) {
                            return r1;
                        }
                        r12.zzc(com.google.android.gms.internal.clearcut.zzax.zzf(r17, r4));
                    }
                }
                return r4;
            case 20:
            case 21:
            case 37:
            case 38:
                if (r22 == 2) {
                    com.google.android.gms.internal.clearcut.zzdc r12 = (com.google.android.gms.internal.clearcut.zzdc) r12;
                    int r1 = com.google.android.gms.internal.clearcut.zzax.zza(r17, r4, r29);
                    int r2 = r29.zzfd + r1;
                    while (r1 < r2) {
                        r1 = com.google.android.gms.internal.clearcut.zzax.zzb(r17, r1, r29);
                        r12.zzm(r29.zzfe);
                    }
                    if (r1 == r2) {
                        return r1;
                    }
                    throw com.google.android.gms.internal.clearcut.zzco.zzbl();
                }
                if (r22 == 0) {
                    com.google.android.gms.internal.clearcut.zzdc r12 = (com.google.android.gms.internal.clearcut.zzdc) r12;
                    do {
                        r1 = com.google.android.gms.internal.clearcut.zzax.zzb(r17, r4, r29);
                        r12.zzm(r29.zzfe);
                        if (r1 >= r19) {
                            return r1;
                        }
                        r4 = com.google.android.gms.internal.clearcut.zzax.zza(r17, r1, r29);
                    } while (r20 == r29.zzfd);
                    return r1;
                }
                return r4;
            case 22:
            case 29:
            case 39:
            case 43:
                if (r22 == 2) {
                    return com.google.android.gms.internal.clearcut.zzax.zza(r17, r4, r12, r29);
                }
                if (r22 == 0) {
                    return com.google.android.gms.internal.clearcut.zzax.zza(r20, r17, r18, r19, r12, r29);
                }
                return r4;
            case 23:
            case 32:
            case 40:
            case 46:
                if (r22 == 2) {
                    com.google.android.gms.internal.clearcut.zzdc r12 = (com.google.android.gms.internal.clearcut.zzdc) r12;
                    int r1 = com.google.android.gms.internal.clearcut.zzax.zza(r17, r4, r29);
                    int r2 = r29.zzfd + r1;
                    while (r1 < r2) {
                        r12.zzm(com.google.android.gms.internal.clearcut.zzax.zzd(r17, r1));
                        r1 += 8;
                    }
                    if (r1 == r2) {
                        return r1;
                    }
                    throw com.google.android.gms.internal.clearcut.zzco.zzbl();
                }
                if (r22 == 1) {
                    com.google.android.gms.internal.clearcut.zzdc r12 = (com.google.android.gms.internal.clearcut.zzdc) r12;
                    r12.zzm(com.google.android.gms.internal.clearcut.zzax.zzd(r17, r18));
                    while (true) {
                        int r1 = r4 + 8;
                        if (r1 >= r19) {
                            return r1;
                        }
                        r4 = com.google.android.gms.internal.clearcut.zzax.zza(r17, r1, r29);
                        if (r20 != r29.zzfd) {
                            return r1;
                        }
                        r12.zzm(com.google.android.gms.internal.clearcut.zzax.zzd(r17, r4));
                    }
                }
                return r4;
            case 24:
            case 31:
            case 41:
            case 45:
                if (r22 == 2) {
                    com.google.android.gms.internal.clearcut.zzch r12 = (com.google.android.gms.internal.clearcut.zzch) r12;
                    int r1 = com.google.android.gms.internal.clearcut.zzax.zza(r17, r4, r29);
                    int r2 = r29.zzfd + r1;
                    while (r1 < r2) {
                        r12.zzac(com.google.android.gms.internal.clearcut.zzax.zzc(r17, r1));
                        r1 += 4;
                    }
                    if (r1 == r2) {
                        return r1;
                    }
                    throw com.google.android.gms.internal.clearcut.zzco.zzbl();
                }
                if (r22 == 5) {
                    com.google.android.gms.internal.clearcut.zzch r12 = (com.google.android.gms.internal.clearcut.zzch) r12;
                    r12.zzac(com.google.android.gms.internal.clearcut.zzax.zzc(r17, r18));
                    while (true) {
                        int r1 = r4 + 4;
                        if (r1 >= r19) {
                            return r1;
                        }
                        r4 = com.google.android.gms.internal.clearcut.zzax.zza(r17, r1, r29);
                        if (r20 != r29.zzfd) {
                            return r1;
                        }
                        r12.zzac(com.google.android.gms.internal.clearcut.zzax.zzc(r17, r4));
                    }
                }
                return r4;
            case 25:
            case 42:
                if (r22 != 2) {
                    if (r22 == 0) {
                        com.google.android.gms.internal.clearcut.zzaz r12 = (com.google.android.gms.internal.clearcut.zzaz) r12;
                        r4 = com.google.android.gms.internal.clearcut.zzax.zzb(r17, r4, r29);
                        break;
                    }
                    return r4;
                }
                com.google.android.gms.internal.clearcut.zzaz r12 = (com.google.android.gms.internal.clearcut.zzaz) r12;
                r2 = com.google.android.gms.internal.clearcut.zzax.zza(r17, r4, r29);
                int r4 = r29.zzfd + r2;
                while (r2 < r4) {
                    r2 = com.google.android.gms.internal.clearcut.zzax.zzb(r17, r2, r29);
                    r12.addBoolean(r29.zzfe != 0);
                }
                if (r2 != r4) {
                    throw com.google.android.gms.internal.clearcut.zzco.zzbl();
                }
                return r2;
            case 26:
                if (r22 == 2) {
                    if ((r24 & 536870912) == 0) {
                        int r1 = com.google.android.gms.internal.clearcut.zzax.zza(r17, r4, r29);
                        int r4 = r29.zzfd;
                        if (r4 != 0) {
                            java.lang.String r8 = new java.lang.String(r17, r1, r4, com.google.android.gms.internal.clearcut.zzci.UTF_8);
                            r12.add(r8);
                            r1 += r4;
                            if (r1 < r19) {
                                int r4 = com.google.android.gms.internal.clearcut.zzax.zza(r17, r1, r29);
                                if (r20 != r29.zzfd) {
                                    return r1;
                                }
                                r1 = com.google.android.gms.internal.clearcut.zzax.zza(r17, r4, r29);
                                r4 = r29.zzfd;
                                if (r4 != 0) {
                                    r8 = new java.lang.String(r17, r1, r4, com.google.android.gms.internal.clearcut.zzci.UTF_8);
                                    r12.add(r8);
                                    r1 += r4;
                                    if (r1 < r19) {
                                        return r1;
                                    }
                                }
                            }
                        }
                        r12.add("");
                        if (r1 < r19) {
                        }
                    } else {
                        int r1 = com.google.android.gms.internal.clearcut.zzax.zza(r17, r4, r29);
                        int r4 = r29.zzfd;
                        if (r4 != 0) {
                            int r8 = r1 + r4;
                            if (!com.google.android.gms.internal.clearcut.zzff.zze(r17, r1, r8)) {
                                throw com.google.android.gms.internal.clearcut.zzco.zzbp();
                            }
                            java.lang.String r9 = new java.lang.String(r17, r1, r4, com.google.android.gms.internal.clearcut.zzci.UTF_8);
                            r12.add(r9);
                            r1 = r8;
                            if (r1 < r19) {
                                int r4 = com.google.android.gms.internal.clearcut.zzax.zza(r17, r1, r29);
                                if (r20 != r29.zzfd) {
                                    return r1;
                                }
                                r1 = com.google.android.gms.internal.clearcut.zzax.zza(r17, r4, r29);
                                int r4 = r29.zzfd;
                                if (r4 != 0) {
                                    r8 = r1 + r4;
                                    if (!com.google.android.gms.internal.clearcut.zzff.zze(r17, r1, r8)) {
                                        throw com.google.android.gms.internal.clearcut.zzco.zzbp();
                                    }
                                    r9 = new java.lang.String(r17, r1, r4, com.google.android.gms.internal.clearcut.zzci.UTF_8);
                                    r12.add(r9);
                                    r1 = r8;
                                    if (r1 < r19) {
                                        return r1;
                                    }
                                }
                            }
                        }
                        r12.add("");
                        if (r1 < r19) {
                        }
                    }
                }
                return r4;
            case 27:
                if (r22 == 2) {
                    return zza(zzad(r23), r20, r17, r18, r19, r12, r29);
                }
                return r4;
            case 28:
                if (r22 == 2) {
                    int r1 = com.google.android.gms.internal.clearcut.zzax.zza(r17, r4, r29);
                    int r4 = r29.zzfd;
                    break;
                }
                return r4;
            case 30:
            case 44:
                if (r22 != 2) {
                    if (r22 == 0) {
                        r2 = com.google.android.gms.internal.clearcut.zzax.zza(r20, r17, r18, r19, r12, r29);
                    }
                    return r4;
                }
                r2 = com.google.android.gms.internal.clearcut.zzax.zza(r17, r4, r12, r29);
                com.google.android.gms.internal.clearcut.zzcg r1 = (com.google.android.gms.internal.clearcut.zzcg) r16;
                com.google.android.gms.internal.clearcut.zzey r3 = r1.zzjp;
                if (r3 == com.google.android.gms.internal.clearcut.zzey.zzea()) {
                    r3 = null;
                }
                com.google.android.gms.internal.clearcut.zzey r3 = (com.google.android.gms.internal.clearcut.zzey) com.google.android.gms.internal.clearcut.zzeh.zza(r21, r12, zzaf(r23), r3, r15.zzmx);
                if (r3 != null) {
                    r1.zzjp = r3;
                }
                return r2;
            case 33:
            case 47:
                if (r22 == 2) {
                    com.google.android.gms.internal.clearcut.zzch r12 = (com.google.android.gms.internal.clearcut.zzch) r12;
                    int r1 = com.google.android.gms.internal.clearcut.zzax.zza(r17, r4, r29);
                    int r2 = r29.zzfd + r1;
                    while (r1 < r2) {
                        r1 = com.google.android.gms.internal.clearcut.zzax.zza(r17, r1, r29);
                        r12.zzac(com.google.android.gms.internal.clearcut.zzbk.zzm(r29.zzfd));
                    }
                    if (r1 == r2) {
                        return r1;
                    }
                    throw com.google.android.gms.internal.clearcut.zzco.zzbl();
                }
                if (r22 == 0) {
                    com.google.android.gms.internal.clearcut.zzch r12 = (com.google.android.gms.internal.clearcut.zzch) r12;
                    do {
                        r1 = com.google.android.gms.internal.clearcut.zzax.zza(r17, r4, r29);
                        r12.zzac(com.google.android.gms.internal.clearcut.zzbk.zzm(r29.zzfd));
                        if (r1 >= r19) {
                            return r1;
                        }
                        r4 = com.google.android.gms.internal.clearcut.zzax.zza(r17, r1, r29);
                    } while (r20 == r29.zzfd);
                    return r1;
                }
                return r4;
            case 34:
            case 48:
                if (r22 == 2) {
                    com.google.android.gms.internal.clearcut.zzdc r12 = (com.google.android.gms.internal.clearcut.zzdc) r12;
                    int r1 = com.google.android.gms.internal.clearcut.zzax.zza(r17, r4, r29);
                    int r2 = r29.zzfd + r1;
                    while (r1 < r2) {
                        r1 = com.google.android.gms.internal.clearcut.zzax.zzb(r17, r1, r29);
                        r12.zzm(com.google.android.gms.internal.clearcut.zzbk.zza(r29.zzfe));
                    }
                    if (r1 == r2) {
                        return r1;
                    }
                    throw com.google.android.gms.internal.clearcut.zzco.zzbl();
                }
                if (r22 == 0) {
                    com.google.android.gms.internal.clearcut.zzdc r12 = (com.google.android.gms.internal.clearcut.zzdc) r12;
                    do {
                        r1 = com.google.android.gms.internal.clearcut.zzax.zzb(r17, r4, r29);
                        r12.zzm(com.google.android.gms.internal.clearcut.zzbk.zza(r29.zzfe));
                        if (r1 >= r19) {
                            return r1;
                        }
                        r4 = com.google.android.gms.internal.clearcut.zzax.zza(r17, r1, r29);
                    } while (r20 == r29.zzfd);
                    return r1;
                }
                return r4;
            case 49:
                if (r22 == 3) {
                    com.google.android.gms.internal.clearcut.zzef r1 = zzad(r23);
                    int r6 = (r20 & (-8)) | 4;
                    r4 = zza(r1, r17, r18, r19, r6, r29);
                    while (true) {
                        r12.add(r29.zzff);
                        if (r4 < r19) {
                            int r8 = com.google.android.gms.internal.clearcut.zzax.zza(r17, r4, r29);
                            if (r20 == r29.zzfd) {
                                r4 = zza(r1, r17, r8, r19, r6, r29);
                            }
                        }
                    }
                }
                return r4;
            default:
                return r4;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final <K, V> int zza(T t, byte[] bArr, int i, int i2, int i3, int i4, long j, zzay zzayVar) throws IOException {
        Unsafe unsafe = zzmh;
        Object zzae = zzae(i3);
        Object object = unsafe.getObject(t, j);
        if (this.zzmz.zzi(object)) {
            Object zzk = this.zzmz.zzk(zzae);
            this.zzmz.zzb(zzk, object);
            unsafe.putObject(t, j, zzk);
            object = zzk;
        }
        zzdh<?, ?> zzl = this.zzmz.zzl(zzae);
        Map<?, ?> zzg = this.zzmz.zzg(object);
        int zza = zzax.zza(bArr, i, zzayVar);
        int i5 = zzayVar.zzfd;
        if (i5 < 0 || i5 > i2 - zza) {
            throw zzco.zzbl();
        }
        int i6 = i5 + zza;
        Object obj = (K) zzl.zzmc;
        Object obj2 = (V) zzl.zzdu;
        while (zza < i6) {
            int i7 = zza + 1;
            int i8 = bArr[zza];
            if (i8 < 0) {
                i7 = zzax.zza(i8, bArr, i7, zzayVar);
                i8 = zzayVar.zzfd;
            }
            int i9 = i7;
            int i10 = i8 >>> 3;
            int i11 = i8 & 7;
            if (i10 != 1) {
                if (i10 == 2 && i11 == zzl.zzmd.zzel()) {
                    zza = zza(bArr, i9, i2, zzl.zzmd, zzl.zzdu.getClass(), zzayVar);
                    obj2 = zzayVar.zzff;
                }
                zza = zzax.zza(i8, bArr, i9, i2, zzayVar);
            } else if (i11 == zzl.zzmb.zzel()) {
                zza = zza(bArr, i9, i2, zzl.zzmb, (Class<?>) null, zzayVar);
                obj = (K) zzayVar.zzff;
            } else {
                zza = zzax.zza(i8, bArr, i9, i2, zzayVar);
            }
        }
        if (zza == i6) {
            zzg.put(obj, obj2);
            return i6;
        }
        throw zzco.zzbo();
    }

    /* JADX WARN: Removed duplicated region for block: B:133:0x0372 A[ADDED_TO_REGION] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final int zza(T r27, byte[] r28, int r29, int r30, int r31, com.google.android.gms.internal.clearcut.zzay r32) throws java.io.IOException {
        sun.misc.Unsafe r25;
        int r8;
        int r7;
        int r9;
        int r0;
        int r1;
        T r10;
        com.google.android.gms.internal.clearcut.zzck<?> r4;
        byte r5;
        int r4;
        int r15;
        int r29;
        int r20;
        int r19;
        int r2;
        com.google.android.gms.internal.clearcut.zzay r11;
        int r5;
        int r29;
        int r5;
        long r7;
        java.lang.Object r1;
        com.google.android.gms.internal.clearcut.zzay r11;
        int r2;
        com.google.android.gms.internal.clearcut.zzds<T> r15 = r26;
        T r14 = r27;
        byte[] r12 = r28;
        int r13 = r30;
        int r11 = r31;
        com.google.android.gms.internal.clearcut.zzay r9 = r32;
        sun.misc.Unsafe r10 = com.google.android.gms.internal.clearcut.zzds.zzmh;
        int r8 = -1;
        int r0 = r29;
        int r7 = -1;
        int r1 = 0;
        int r6 = 0;
        while (true) {
            if (r0 < r13) {
                int r1 = r0 + 1;
                byte r0 = r12[r0];
                if (r0 < 0) {
                    r4 = com.google.android.gms.internal.clearcut.zzax.zza(r0, r12, r1, r9);
                    r5 = r9.zzfd;
                } else {
                    r5 = r0;
                    r4 = r1;
                }
                int r3 = r5 >>> 3;
                int r2 = r5 & 7;
                int r1 = r15.zzai(r3);
                if (r1 != r8) {
                    int[] r0 = r15.zzmi;
                    int r8 = r0[r1 + 1];
                    int r11 = (r8 & 267386880) >>> 20;
                    int r29 = r5;
                    long r12 = r8 & 1048575;
                    if (r11 <= 17) {
                        int r0 = r0[r1 + 2];
                        int r20 = 1 << (r0 >>> 20);
                        int r0 = r0 & 1048575;
                        if (r0 != r7) {
                            if (r7 != -1) {
                                r10.putInt(r14, r7, r6);
                            }
                            r6 = r10.getInt(r14, r0);
                            r7 = r0;
                        }
                        switch (r11) {
                            case 0:
                                r9 = r29;
                                r11 = r32;
                                r5 = r4;
                                r29 = r7;
                                r12 = r28;
                                if (r2 == 1) {
                                    com.google.android.gms.internal.clearcut.zzfd.zza(r14, r12, com.google.android.gms.internal.clearcut.zzax.zze(r12, r5));
                                    r0 = r5 + 8;
                                    r6 |= r20;
                                    r7 = r29;
                                    r13 = r30;
                                    r1 = r9;
                                    r9 = r11;
                                    r8 = -1;
                                    r11 = r31;
                                    break;
                                } else {
                                    r7 = r29;
                                    r8 = r31;
                                    r2 = r5;
                                    r25 = r10;
                                    if (r9 != r8 && r8 != 0) {
                                        r0 = r7;
                                        r1 = -1;
                                        r7 = r2;
                                        break;
                                    } else {
                                        r0 = zza(r9, r28, r2, r30, r27, r32);
                                        r15 = r26;
                                        r14 = r27;
                                        r12 = r28;
                                        r13 = r30;
                                        r11 = r8;
                                        r1 = r9;
                                        r10 = r25;
                                        r8 = -1;
                                        r9 = r32;
                                        break;
                                    }
                                }
                            case 1:
                                r9 = r29;
                                r11 = r32;
                                r5 = r4;
                                r29 = r7;
                                r12 = r28;
                                if (r2 == 5) {
                                    com.google.android.gms.internal.clearcut.zzfd.zza((java.lang.Object) r14, r12, com.google.android.gms.internal.clearcut.zzax.zzf(r12, r5));
                                    r0 = r5 + 4;
                                    r6 |= r20;
                                    r7 = r29;
                                    r13 = r30;
                                    r1 = r9;
                                    r9 = r11;
                                    r8 = -1;
                                    r11 = r31;
                                    break;
                                } else {
                                    r7 = r29;
                                    r8 = r31;
                                    r2 = r5;
                                    r25 = r10;
                                    if (r9 != r8) {
                                    }
                                    r0 = zza(r9, r28, r2, r30, r27, r32);
                                    r15 = r26;
                                    r14 = r27;
                                    r12 = r28;
                                    r13 = r30;
                                    r11 = r8;
                                    r1 = r9;
                                    r10 = r25;
                                    r8 = -1;
                                    r9 = r32;
                                    break;
                                }
                                break;
                            case 2:
                            case 3:
                                r9 = r29;
                                r5 = r4;
                                r29 = r7;
                                r12 = r28;
                                if (r2 == 0) {
                                    int r17 = com.google.android.gms.internal.clearcut.zzax.zzb(r12, r5, r32);
                                    r10.putLong(r27, r12, r32.zzfe);
                                    r6 |= r20;
                                    r7 = r29;
                                    r13 = r30;
                                    r1 = r9;
                                    r9 = r32;
                                    r0 = r17;
                                    r8 = -1;
                                    r11 = r31;
                                    break;
                                } else {
                                    r7 = r29;
                                    r8 = r31;
                                    r2 = r5;
                                    r25 = r10;
                                    if (r9 != r8) {
                                    }
                                    r0 = zza(r9, r28, r2, r30, r27, r32);
                                    r15 = r26;
                                    r14 = r27;
                                    r12 = r28;
                                    r13 = r30;
                                    r11 = r8;
                                    r1 = r9;
                                    r10 = r25;
                                    r8 = -1;
                                    r9 = r32;
                                    break;
                                }
                                break;
                            case 4:
                            case 11:
                                r9 = r29;
                                r11 = r32;
                                r5 = r4;
                                r29 = r7;
                                r12 = r28;
                                if (r2 == 0) {
                                    r0 = com.google.android.gms.internal.clearcut.zzax.zza(r12, r5, r11);
                                    r10.putInt(r14, r12, r11.zzfd);
                                    r6 |= r20;
                                    r7 = r29;
                                    r13 = r30;
                                    r1 = r9;
                                    r9 = r11;
                                    r8 = -1;
                                    r11 = r31;
                                    break;
                                } else {
                                    r7 = r29;
                                    r8 = r31;
                                    r2 = r5;
                                    r25 = r10;
                                    if (r9 != r8) {
                                    }
                                    r0 = zza(r9, r28, r2, r30, r27, r32);
                                    r15 = r26;
                                    r14 = r27;
                                    r12 = r28;
                                    r13 = r30;
                                    r11 = r8;
                                    r1 = r9;
                                    r10 = r25;
                                    r8 = -1;
                                    r9 = r32;
                                    break;
                                }
                                break;
                            case 5:
                            case 14:
                                r9 = r29;
                                r11 = r32;
                                r29 = r7;
                                r12 = r28;
                                if (r2 == 1) {
                                    r10.putLong(r27, r12, com.google.android.gms.internal.clearcut.zzax.zzd(r12, r4));
                                    r0 = r4 + 8;
                                    r6 |= r20;
                                    r7 = r29;
                                    r13 = r30;
                                    r1 = r9;
                                    r9 = r11;
                                    r8 = -1;
                                    r11 = r31;
                                    break;
                                } else {
                                    r5 = r4;
                                    r7 = r29;
                                    r8 = r31;
                                    r2 = r5;
                                    r25 = r10;
                                    if (r9 != r8) {
                                    }
                                    r0 = zza(r9, r28, r2, r30, r27, r32);
                                    r15 = r26;
                                    r14 = r27;
                                    r12 = r28;
                                    r13 = r30;
                                    r11 = r8;
                                    r1 = r9;
                                    r10 = r25;
                                    r8 = -1;
                                    r9 = r32;
                                    break;
                                }
                                break;
                            case 6:
                            case 13:
                                r9 = r29;
                                r5 = r30;
                                r11 = r32;
                                r29 = r7;
                                r12 = r28;
                                if (r2 == 5) {
                                    r10.putInt(r14, r12, com.google.android.gms.internal.clearcut.zzax.zzc(r12, r4));
                                    r0 = r4 + 4;
                                    r6 |= r20;
                                    r7 = r29;
                                    r13 = r5;
                                    r1 = r9;
                                    r9 = r11;
                                    r8 = -1;
                                    r11 = r31;
                                    break;
                                } else {
                                    r5 = r4;
                                    r7 = r29;
                                    r8 = r31;
                                    r2 = r5;
                                    r25 = r10;
                                    if (r9 != r8) {
                                    }
                                    r0 = zza(r9, r28, r2, r30, r27, r32);
                                    r15 = r26;
                                    r14 = r27;
                                    r12 = r28;
                                    r13 = r30;
                                    r11 = r8;
                                    r1 = r9;
                                    r10 = r25;
                                    r8 = -1;
                                    r9 = r32;
                                    break;
                                }
                                break;
                            case 7:
                                r9 = r29;
                                r5 = r30;
                                r11 = r32;
                                r29 = r7;
                                r12 = r28;
                                if (r2 == 0) {
                                    r0 = com.google.android.gms.internal.clearcut.zzax.zzb(r12, r4, r11);
                                    com.google.android.gms.internal.clearcut.zzfd.zza(r14, r12, r11.zzfe != 0);
                                    r6 |= r20;
                                    r7 = r29;
                                    r13 = r5;
                                    r1 = r9;
                                    r9 = r11;
                                    r8 = -1;
                                    r11 = r31;
                                    break;
                                } else {
                                    r5 = r4;
                                    r7 = r29;
                                    r8 = r31;
                                    r2 = r5;
                                    r25 = r10;
                                    if (r9 != r8) {
                                    }
                                    r0 = zza(r9, r28, r2, r30, r27, r32);
                                    r15 = r26;
                                    r14 = r27;
                                    r12 = r28;
                                    r13 = r30;
                                    r11 = r8;
                                    r1 = r9;
                                    r10 = r25;
                                    r8 = -1;
                                    r9 = r32;
                                    break;
                                }
                                break;
                            case 8:
                                r9 = r29;
                                r5 = r30;
                                r11 = r32;
                                r29 = r7;
                                r7 = r12;
                                r12 = r28;
                                if (r2 == 2) {
                                    r0 = (r8 & 536870912) == 0 ? com.google.android.gms.internal.clearcut.zzax.zzc(r12, r4, r11) : com.google.android.gms.internal.clearcut.zzax.zzd(r12, r4, r11);
                                    r1 = r11.zzff;
                                    r10.putObject(r14, r7, r1);
                                    r6 |= r20;
                                    r7 = r29;
                                    r13 = r5;
                                    r1 = r9;
                                    r9 = r11;
                                    r8 = -1;
                                    r11 = r31;
                                    break;
                                } else {
                                    r5 = r4;
                                    r7 = r29;
                                    r8 = r31;
                                    r2 = r5;
                                    r25 = r10;
                                    if (r9 != r8) {
                                    }
                                    r0 = zza(r9, r28, r2, r30, r27, r32);
                                    r15 = r26;
                                    r14 = r27;
                                    r12 = r28;
                                    r13 = r30;
                                    r11 = r8;
                                    r1 = r9;
                                    r10 = r25;
                                    r8 = -1;
                                    r9 = r32;
                                    break;
                                }
                                break;
                            case 9:
                                r9 = r29;
                                r11 = r32;
                                r29 = r7;
                                r7 = r12;
                                r12 = r28;
                                if (r2 == 2) {
                                    r5 = r30;
                                    r0 = zza(r15.zzad(r1), r12, r4, r5, r11);
                                    r1 = (r6 & r20) == 0 ? r11.zzff : com.google.android.gms.internal.clearcut.zzci.zza(r10.getObject(r14, r7), r11.zzff);
                                    r10.putObject(r14, r7, r1);
                                    r6 |= r20;
                                    r7 = r29;
                                    r13 = r5;
                                    r1 = r9;
                                    r9 = r11;
                                    r8 = -1;
                                    r11 = r31;
                                    break;
                                } else {
                                    r5 = r4;
                                    r7 = r29;
                                    r8 = r31;
                                    r2 = r5;
                                    r25 = r10;
                                    if (r9 != r8) {
                                    }
                                    r0 = zza(r9, r28, r2, r30, r27, r32);
                                    r15 = r26;
                                    r14 = r27;
                                    r12 = r28;
                                    r13 = r30;
                                    r11 = r8;
                                    r1 = r9;
                                    r10 = r25;
                                    r8 = -1;
                                    r9 = r32;
                                    break;
                                }
                                break;
                            case 10:
                                r9 = r29;
                                r11 = r32;
                                r8 = -1;
                                r12 = r28;
                                if (r2 == 2) {
                                    r2 = com.google.android.gms.internal.clearcut.zzax.zze(r12, r4, r11);
                                    r10.putObject(r14, r12, r11.zzff);
                                    r6 |= r20;
                                    r13 = r30;
                                    r0 = r2;
                                    r1 = r9;
                                    r9 = r11;
                                    r11 = r31;
                                    break;
                                } else {
                                    r5 = r4;
                                    r29 = r7;
                                    r7 = r29;
                                    r8 = r31;
                                    r2 = r5;
                                    r25 = r10;
                                    if (r9 != r8) {
                                    }
                                    r0 = zza(r9, r28, r2, r30, r27, r32);
                                    r15 = r26;
                                    r14 = r27;
                                    r12 = r28;
                                    r13 = r30;
                                    r11 = r8;
                                    r1 = r9;
                                    r10 = r25;
                                    r8 = -1;
                                    r9 = r32;
                                    break;
                                }
                                break;
                            case 12:
                                r9 = r29;
                                r11 = r32;
                                r8 = -1;
                                r12 = r28;
                                if (r2 == 0) {
                                    r0 = com.google.android.gms.internal.clearcut.zzax.zza(r12, r4, r11);
                                    int r2 = r11.zzfd;
                                    com.google.android.gms.internal.clearcut.zzck<?> r1 = r15.zzaf(r1);
                                    if (r1 == null || r1.zzb(r2) != null) {
                                        r10.putInt(r14, r12, r2);
                                        r6 |= r20;
                                    } else {
                                        zzn(r27).zzb(r9, java.lang.Long.valueOf(r2));
                                    }
                                    r13 = r30;
                                    r1 = r9;
                                    r9 = r11;
                                    r11 = r31;
                                    break;
                                } else {
                                    r5 = r4;
                                    r29 = r7;
                                    r7 = r29;
                                    r8 = r31;
                                    r2 = r5;
                                    r25 = r10;
                                    if (r9 != r8) {
                                    }
                                    r0 = zza(r9, r28, r2, r30, r27, r32);
                                    r15 = r26;
                                    r14 = r27;
                                    r12 = r28;
                                    r13 = r30;
                                    r11 = r8;
                                    r1 = r9;
                                    r10 = r25;
                                    r8 = -1;
                                    r9 = r32;
                                    break;
                                }
                                break;
                            case 15:
                                r9 = r29;
                                r11 = r32;
                                r8 = -1;
                                r12 = r28;
                                if (r2 == 0) {
                                    r2 = com.google.android.gms.internal.clearcut.zzax.zza(r12, r4, r11);
                                    r10.putInt(r14, r12, com.google.android.gms.internal.clearcut.zzbk.zzm(r11.zzfd));
                                    r6 |= r20;
                                    r13 = r30;
                                    r0 = r2;
                                    r1 = r9;
                                    r9 = r11;
                                    r11 = r31;
                                    break;
                                } else {
                                    r5 = r4;
                                    r29 = r7;
                                    r7 = r29;
                                    r8 = r31;
                                    r2 = r5;
                                    r25 = r10;
                                    if (r9 != r8) {
                                    }
                                    r0 = zza(r9, r28, r2, r30, r27, r32);
                                    r15 = r26;
                                    r14 = r27;
                                    r12 = r28;
                                    r13 = r30;
                                    r11 = r8;
                                    r1 = r9;
                                    r10 = r25;
                                    r8 = -1;
                                    r9 = r32;
                                    break;
                                }
                                break;
                            case 16:
                                r9 = r29;
                                r8 = -1;
                                if (r2 == 0) {
                                    r12 = r28;
                                    int r13 = com.google.android.gms.internal.clearcut.zzax.zzb(r12, r4, r32);
                                    r10.putLong(r27, r12, com.google.android.gms.internal.clearcut.zzbk.zza(r32.zzfe));
                                    r6 |= r20;
                                    r1 = r9;
                                    r9 = r32;
                                    r0 = r13;
                                    r13 = r30;
                                    r11 = r31;
                                    break;
                                } else {
                                    r5 = r4;
                                    r29 = r7;
                                    r7 = r29;
                                    r8 = r31;
                                    r2 = r5;
                                    r25 = r10;
                                    if (r9 != r8) {
                                    }
                                    r0 = zza(r9, r28, r2, r30, r27, r32);
                                    r15 = r26;
                                    r14 = r27;
                                    r12 = r28;
                                    r13 = r30;
                                    r11 = r8;
                                    r1 = r9;
                                    r10 = r25;
                                    r8 = -1;
                                    r9 = r32;
                                    break;
                                }
                                break;
                            case 17:
                                if (r2 == 3) {
                                    r9 = r29;
                                    r8 = -1;
                                    r0 = zza(r15.zzad(r1), r28, r4, r30, (r3 << 3) | 4, r32);
                                    r11 = r32;
                                    r10.putObject(r14, r12, (r6 & r20) == 0 ? r11.zzff : com.google.android.gms.internal.clearcut.zzci.zza(r10.getObject(r14, r12), r11.zzff));
                                    r6 |= r20;
                                    r12 = r28;
                                    r13 = r30;
                                    r1 = r9;
                                    r9 = r11;
                                    r11 = r31;
                                    break;
                                } else {
                                    r9 = r29;
                                    r5 = r4;
                                    r29 = r7;
                                    r7 = r29;
                                    r8 = r31;
                                    r2 = r5;
                                    r25 = r10;
                                    if (r9 != r8) {
                                    }
                                    r0 = zza(r9, r28, r2, r30, r27, r32);
                                    r15 = r26;
                                    r14 = r27;
                                    r12 = r28;
                                    r13 = r30;
                                    r11 = r8;
                                    r1 = r9;
                                    r10 = r25;
                                    r8 = -1;
                                    r9 = r32;
                                    break;
                                }
                                break;
                            default:
                                r9 = r29;
                                r5 = r4;
                                r29 = r7;
                                r7 = r29;
                                r8 = r31;
                                r2 = r5;
                                r25 = r10;
                                if (r9 != r8) {
                                }
                                r0 = zza(r9, r28, r2, r30, r27, r32);
                                r15 = r26;
                                r14 = r27;
                                r12 = r28;
                                r13 = r30;
                                r11 = r8;
                                r1 = r9;
                                r10 = r25;
                                r8 = -1;
                                r9 = r32;
                                break;
                        }
                    } else {
                        int r5 = r4;
                        r19 = r7;
                        r12 = r28;
                        if (r11 != 27) {
                            r20 = r6;
                            if (r11 <= 49) {
                                r29 = r29;
                                r25 = r10;
                                r0 = zza((com.google.android.gms.internal.clearcut.zzds<T>) r27, r28, r5, r30, r29, r3, r2, r1, r8, r11, r12, r32);
                                if (r0 == r5) {
                                    r9 = r29;
                                    r8 = r31;
                                    r2 = r0;
                                    r7 = r19;
                                    r6 = r20;
                                } else {
                                    r15 = r26;
                                    r14 = r27;
                                    r12 = r28;
                                    r1 = r29;
                                    r13 = r30;
                                    r11 = r31;
                                    r9 = r32;
                                    r7 = r19;
                                    r6 = r20;
                                    r10 = r25;
                                    r8 = -1;
                                }
                            } else {
                                r15 = r5;
                                r29 = r29;
                                r25 = r10;
                                if (r11 != 50) {
                                    r0 = zza((com.google.android.gms.internal.clearcut.zzds<T>) r27, r28, r15, r30, r29, r3, r2, r8, r11, r12, r1, r32);
                                    if (r0 == r15) {
                                        r9 = r29;
                                        r8 = r31;
                                        r2 = r0;
                                        r7 = r19;
                                        r6 = r20;
                                    } else {
                                        r15 = r26;
                                        r14 = r27;
                                        r12 = r28;
                                        r1 = r29;
                                        r13 = r30;
                                        r11 = r31;
                                        r9 = r32;
                                        r7 = r19;
                                        r6 = r20;
                                        r10 = r25;
                                        r8 = -1;
                                    }
                                } else if (r2 == 2) {
                                    r0 = zza(r27, r28, r15, r30, r1, r3, r12, r32);
                                    if (r0 == r15) {
                                        r9 = r29;
                                        r8 = r31;
                                        r2 = r0;
                                        r7 = r19;
                                        r6 = r20;
                                    } else {
                                        r15 = r26;
                                        r14 = r27;
                                        r12 = r28;
                                        r1 = r29;
                                        r13 = r30;
                                        r11 = r31;
                                        r9 = r32;
                                        r7 = r19;
                                        r6 = r20;
                                        r10 = r25;
                                        r8 = -1;
                                    }
                                } else {
                                    r9 = r29;
                                    r8 = r31;
                                    r2 = r15;
                                    r7 = r19;
                                    r6 = r20;
                                }
                            }
                            if (r9 != r8) {
                            }
                            r0 = zza(r9, r28, r2, r30, r27, r32);
                            r15 = r26;
                            r14 = r27;
                            r12 = r28;
                            r13 = r30;
                            r11 = r8;
                            r1 = r9;
                            r10 = r25;
                            r8 = -1;
                            r9 = r32;
                        } else if (r2 == 2) {
                            com.google.android.gms.internal.clearcut.zzcn r0 = (com.google.android.gms.internal.clearcut.zzcn) r10.getObject(r14, r12);
                            if (!r0.zzu()) {
                                int r2 = r0.size();
                                r0 = r0.zzi(r2 == 0 ? 10 : r2 << 1);
                                r10.putObject(r14, r12, r0);
                            }
                            com.google.android.gms.internal.clearcut.zzcn r7 = r0;
                            com.google.android.gms.internal.clearcut.zzef r0 = r15.zzad(r1);
                            r1 = r29;
                            r0 = zza(r0, r1, r28, r5, r30, r7, r32);
                            r13 = r30;
                            r11 = r31;
                            r7 = r19;
                            r6 = r6;
                            r8 = -1;
                            r9 = r32;
                        } else {
                            r20 = r6;
                            r15 = r5;
                            r29 = r29;
                        }
                    }
                } else {
                    r15 = r4;
                    r29 = r5;
                    r20 = r6;
                    r19 = r7;
                }
                r25 = r10;
                r9 = r29;
                r8 = r31;
                r2 = r15;
                r7 = r19;
                r6 = r20;
                if (r9 != r8) {
                }
                r0 = zza(r9, r28, r2, r30, r27, r32);
                r15 = r26;
                r14 = r27;
                r12 = r28;
                r13 = r30;
                r11 = r8;
                r1 = r9;
                r10 = r25;
                r8 = -1;
                r9 = r32;
            } else {
                int r19 = r7;
                r25 = r10;
                r8 = r11;
                r7 = r0;
                r9 = r1;
                r0 = r19;
                r1 = -1;
            }
        }
        if (r0 != r1) {
            r10 = r27;
            r25.putInt(r10, r0, r6);
        } else {
            r10 = r27;
        }
        int[] r12 = r26.zzmt;
        if (r12 != null) {
            java.lang.Object r5 = null;
            for (int r1 : r12) {
                com.google.android.gms.internal.clearcut.zzex r6 = r26.zzmx;
                int r2 = r26.zzmi[r1];
                java.lang.Object r0 = com.google.android.gms.internal.clearcut.zzfd.zzo(r10, zzag(r1) & 1048575);
                if (r0 != null && (r4 = zzaf(r1)) != null) {
                    r5 = zza(r1, r2, r26.zzmz.zzg(r0), r4, (com.google.android.gms.internal.clearcut.zzck<?>) r5, (com.google.android.gms.internal.clearcut.zzex<UT, com.google.android.gms.internal.clearcut.zzck<?>>) r6);
                }
                r5 = (com.google.android.gms.internal.clearcut.zzey) r5;
            }
            if (r5 != null) {
                r26.zzmx.zzf(r10, r5);
            }
        }
        if (r8 == 0) {
            if (r7 != r30) {
                throw com.google.android.gms.internal.clearcut.zzco.zzbo();
            }
        } else if (r7 > r30 || r9 != r8) {
            throw com.google.android.gms.internal.clearcut.zzco.zzbo();
        }
        return r7;
    }

    private static int zza(byte[] bArr, int i, int i2, zzfl zzflVar, Class<?> cls, zzay zzayVar) throws IOException {
        int zzb;
        Object valueOf;
        Object valueOf2;
        Object valueOf3;
        int i3;
        long j;
        switch (zzdt.zzgq[zzflVar.ordinal()]) {
            case 1:
                zzb = zzax.zzb(bArr, i, zzayVar);
                valueOf = Boolean.valueOf(zzayVar.zzfe != 0);
                zzayVar.zzff = valueOf;
                return zzb;
            case 2:
                return zzax.zze(bArr, i, zzayVar);
            case 3:
                valueOf2 = Double.valueOf(zzax.zze(bArr, i));
                zzayVar.zzff = valueOf2;
                return i + 8;
            case 4:
            case 5:
                valueOf3 = Integer.valueOf(zzax.zzc(bArr, i));
                zzayVar.zzff = valueOf3;
                return i + 4;
            case 6:
            case 7:
                valueOf2 = Long.valueOf(zzax.zzd(bArr, i));
                zzayVar.zzff = valueOf2;
                return i + 8;
            case 8:
                valueOf3 = Float.valueOf(zzax.zzf(bArr, i));
                zzayVar.zzff = valueOf3;
                return i + 4;
            case 9:
            case 10:
            case 11:
                zzb = zzax.zza(bArr, i, zzayVar);
                i3 = zzayVar.zzfd;
                valueOf = Integer.valueOf(i3);
                zzayVar.zzff = valueOf;
                return zzb;
            case 12:
            case 13:
                zzb = zzax.zzb(bArr, i, zzayVar);
                j = zzayVar.zzfe;
                valueOf = Long.valueOf(j);
                zzayVar.zzff = valueOf;
                return zzb;
            case 14:
                return zza((zzef) zzea.zzcm().zze(cls), bArr, i, i2, zzayVar);
            case 15:
                zzb = zzax.zza(bArr, i, zzayVar);
                i3 = zzbk.zzm(zzayVar.zzfd);
                valueOf = Integer.valueOf(i3);
                zzayVar.zzff = valueOf;
                return zzb;
            case 16:
                zzb = zzax.zzb(bArr, i, zzayVar);
                j = zzbk.zza(zzayVar.zzfe);
                valueOf = Long.valueOf(j);
                zzayVar.zzff = valueOf;
                return zzb;
            case 17:
                return zzax.zzd(bArr, i, zzayVar);
            default:
                throw new RuntimeException("unsupported field type.");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> zzds<T> zza(Class<T> cls, zzdm zzdmVar, zzdw zzdwVar, zzcy zzcyVar, zzex<?, ?> zzexVar, zzbu<?> zzbuVar, zzdj zzdjVar) {
        int zzcu;
        int i;
        int i2;
        int zza;
        int i3;
        int i4;
        if (!(zzdmVar instanceof zzec)) {
            ((zzes) zzdmVar).zzcf();
            throw new NoSuchMethodError();
        }
        zzec zzecVar = (zzec) zzdmVar;
        boolean z = zzecVar.zzcf() == zzcg.zzg.zzkm;
        if (zzecVar.getFieldCount() == 0) {
            zzcu = 0;
            i = 0;
            i2 = 0;
        } else {
            int zzcp = zzecVar.zzcp();
            int zzcq = zzecVar.zzcq();
            zzcu = zzecVar.zzcu();
            i = zzcp;
            i2 = zzcq;
        }
        int[] iArr = new int[zzcu << 2];
        Object[] objArr = new Object[zzcu << 1];
        int[] iArr2 = zzecVar.zzcr() > 0 ? new int[zzecVar.zzcr()] : null;
        int[] iArr3 = zzecVar.zzcs() > 0 ? new int[zzecVar.zzcs()] : null;
        zzed zzco = zzecVar.zzco();
        if (zzco.next()) {
            int zzcx = zzco.zzcx();
            int i5 = 0;
            int i6 = 0;
            int i7 = 0;
            while (true) {
                if (zzcx >= zzecVar.zzcv() || i5 >= ((zzcx - i) << 2)) {
                    if (zzco.zzda()) {
                        zza = (int) zzfd.zza(zzco.zzdb());
                        i3 = (int) zzfd.zza(zzco.zzdc());
                        i4 = 0;
                    } else {
                        zza = (int) zzfd.zza(zzco.zzdd());
                        if (zzco.zzde()) {
                            i3 = (int) zzfd.zza(zzco.zzdf());
                            i4 = zzco.zzdg();
                        } else {
                            i3 = 0;
                            i4 = 0;
                        }
                    }
                    iArr[i5] = zzco.zzcx();
                    int i8 = i5 + 1;
                    iArr[i8] = (zzco.zzdi() ? 536870912 : 0) | (zzco.zzdh() ? 268435456 : 0) | (zzco.zzcy() << 20) | zza;
                    iArr[i5 + 2] = i3 | (i4 << 20);
                    if (zzco.zzdl() != null) {
                        int i9 = (i5 / 4) << 1;
                        objArr[i9] = zzco.zzdl();
                        if (zzco.zzdj() != null) {
                            objArr[i9 + 1] = zzco.zzdj();
                        } else if (zzco.zzdk() != null) {
                            objArr[i9 + 1] = zzco.zzdk();
                        }
                    } else if (zzco.zzdj() != null) {
                        objArr[((i5 / 4) << 1) + 1] = zzco.zzdj();
                    } else if (zzco.zzdk() != null) {
                        objArr[((i5 / 4) << 1) + 1] = zzco.zzdk();
                    }
                    int zzcy = zzco.zzcy();
                    if (zzcy == zzcb.MAP.ordinal()) {
                        iArr2[i6] = i5;
                        i6++;
                    } else if (zzcy >= 18 && zzcy <= 49) {
                        iArr3[i7] = iArr[i8] & 1048575;
                        i7++;
                    }
                    if (!zzco.next()) {
                        break;
                    }
                    zzcx = zzco.zzcx();
                } else {
                    for (int i10 = 0; i10 < 4; i10++) {
                        iArr[i5 + i10] = -1;
                    }
                }
                i5 += 4;
            }
        }
        return new zzds<>(iArr, objArr, i, i2, zzecVar.zzcv(), zzecVar.zzch(), z, false, zzecVar.zzct(), iArr2, iArr3, zzdwVar, zzcyVar, zzexVar, zzbuVar, zzdjVar);
    }

    private final <K, V, UT, UB> UB zza(int i, int i2, Map<K, V> map, zzck<?> zzckVar, UB ub, zzex<UT, UB> zzexVar) {
        zzdh<?, ?> zzl = this.zzmz.zzl(zzae(i));
        Iterator<Map.Entry<K, V>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<K, V> next = it.next();
            if (zzckVar.zzb(((Integer) next.getValue()).intValue()) == null) {
                if (ub == null) {
                    ub = zzexVar.zzdz();
                }
                zzbg zzk = zzbb.zzk(zzdg.zza(zzl, next.getKey(), next.getValue()));
                try {
                    zzdg.zza(zzk.zzae(), zzl, next.getKey(), next.getValue());
                    zzexVar.zza((zzex<UT, UB>) ub, i2, zzk.zzad());
                    it.remove();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return ub;
    }

    private static void zza(int i, Object obj, zzfr zzfrVar) throws IOException {
        if (obj instanceof String) {
            zzfrVar.zza(i, (String) obj);
        } else {
            zzfrVar.zza(i, (zzbb) obj);
        }
    }

    private static <UT, UB> void zza(zzex<UT, UB> zzexVar, T t, zzfr zzfrVar) throws IOException {
        zzexVar.zza(zzexVar.zzq(t), zzfrVar);
    }

    private final <K, V> void zza(zzfr zzfrVar, int i, Object obj, int i2) throws IOException {
        if (obj != null) {
            zzfrVar.zza(i, this.zzmz.zzl(zzae(i2)), this.zzmz.zzh(obj));
        }
    }

    private final void zza(T t, T t2, int i) {
        long zzag = zzag(i) & 1048575;
        if (zza((zzds<T>) t2, i)) {
            Object zzo = zzfd.zzo(t, zzag);
            Object zzo2 = zzfd.zzo(t2, zzag);
            if (zzo != null && zzo2 != null) {
                zzfd.zza(t, zzag, zzci.zza(zzo, zzo2));
                zzb((zzds<T>) t, i);
            } else if (zzo2 != null) {
                zzfd.zza(t, zzag, zzo2);
                zzb((zzds<T>) t, i);
            }
        }
    }

    private final boolean zza(T t, int i) {
        if (!this.zzmq) {
            int zzah = zzah(i);
            return (zzfd.zzj(t, (long) (zzah & 1048575)) & (1 << (zzah >>> 20))) != 0;
        }
        int zzag = zzag(i);
        long j = zzag & 1048575;
        switch ((zzag & 267386880) >>> 20) {
            case 0:
                return zzfd.zzn(t, j) != FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
            case 1:
                return zzfd.zzm(t, j) != 0.0f;
            case 2:
                return zzfd.zzk(t, j) != 0;
            case 3:
                return zzfd.zzk(t, j) != 0;
            case 4:
                return zzfd.zzj(t, j) != 0;
            case 5:
                return zzfd.zzk(t, j) != 0;
            case 6:
                return zzfd.zzj(t, j) != 0;
            case 7:
                return zzfd.zzl(t, j);
            case 8:
                Object zzo = zzfd.zzo(t, j);
                if (zzo instanceof String) {
                    return !((String) zzo).isEmpty();
                } else if (zzo instanceof zzbb) {
                    return !zzbb.zzfi.equals(zzo);
                } else {
                    throw new IllegalArgumentException();
                }
            case 9:
                return zzfd.zzo(t, j) != null;
            case 10:
                return !zzbb.zzfi.equals(zzfd.zzo(t, j));
            case 11:
                return zzfd.zzj(t, j) != 0;
            case 12:
                return zzfd.zzj(t, j) != 0;
            case 13:
                return zzfd.zzj(t, j) != 0;
            case 14:
                return zzfd.zzk(t, j) != 0;
            case 15:
                return zzfd.zzj(t, j) != 0;
            case 16:
                return zzfd.zzk(t, j) != 0;
            case 17:
                return zzfd.zzo(t, j) != null;
            default:
                throw new IllegalArgumentException();
        }
    }

    private final boolean zza(T t, int i, int i2) {
        return zzfd.zzj(t, (long) (zzah(i2) & 1048575)) == i;
    }

    private final boolean zza(T t, int i, int i2, int i3) {
        return this.zzmq ? zza((zzds<T>) t, i) : (i2 & i3) != 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static boolean zza(Object obj, int i, zzef zzefVar) {
        return zzefVar.zzo(zzfd.zzo(obj, i & 1048575));
    }

    private final zzef zzad(int i) {
        int i2 = (i / 4) << 1;
        zzef zzefVar = (zzef) this.zzmj[i2];
        if (zzefVar != null) {
            return zzefVar;
        }
        zzef<T> zze = zzea.zzcm().zze((Class) this.zzmj[i2 + 1]);
        this.zzmj[i2] = zze;
        return zze;
    }

    private final Object zzae(int i) {
        return this.zzmj[(i / 4) << 1];
    }

    private final zzck<?> zzaf(int i) {
        return (zzck) this.zzmj[((i / 4) << 1) + 1];
    }

    private final int zzag(int i) {
        return this.zzmi[i + 1];
    }

    private final int zzah(int i) {
        return this.zzmi[i + 2];
    }

    private final int zzai(int i) {
        int i2 = this.zzmk;
        if (i >= i2) {
            int i3 = this.zzmm;
            if (i < i3) {
                int i4 = (i - i2) << 2;
                if (this.zzmi[i4] == i) {
                    return i4;
                }
                return -1;
            } else if (i <= this.zzml) {
                int i5 = i3 - i2;
                int length = (this.zzmi.length / 4) - 1;
                while (i5 <= length) {
                    int i6 = (length + i5) >>> 1;
                    int i7 = i6 << 2;
                    int i8 = this.zzmi[i7];
                    if (i == i8) {
                        return i7;
                    }
                    if (i < i8) {
                        length = i6 - 1;
                    } else {
                        i5 = i6 + 1;
                    }
                }
            }
        }
        return -1;
    }

    private final void zzb(T t, int i) {
        if (this.zzmq) {
            return;
        }
        int zzah = zzah(i);
        long j = zzah & 1048575;
        zzfd.zza((Object) t, j, zzfd.zzj(t, j) | (1 << (zzah >>> 20)));
    }

    private final void zzb(T t, int i, int i2) {
        zzfd.zza((Object) t, zzah(i2) & 1048575, i);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x0494  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void zzb(T r19, com.google.android.gms.internal.clearcut.zzfr r20) throws java.io.IOException {
        java.util.Iterator<java.util.Map.Entry<?, java.lang.Object>> r3;
        java.util.Map.Entry<?, java.lang.Object> r5;
        int r7;
        int r10;
        int r17;
        int r4;
        if (r18.zzmo) {
            com.google.android.gms.internal.clearcut.zzby<?> r3 = r18.zzmy.zza(r19);
            if (!r3.isEmpty()) {
                r3 = r3.iterator();
                r5 = r3.next();
                int r6 = -1;
                r7 = r18.zzmi.length;
                sun.misc.Unsafe r8 = com.google.android.gms.internal.clearcut.zzds.zzmh;
                r10 = 0;
                int r11 = 0;
                while (r10 < r7) {
                    int r12 = zzag(r10);
                    int[] r13 = r18.zzmi;
                    int r14 = r13[r10];
                    int r15 = (267386880 & r12) >>> 20;
                    if (r18.zzmq || r15 > 17) {
                        r17 = r10;
                        r4 = 0;
                    } else {
                        int r4 = r13[r10 + 2];
                        int r13 = r4 & 1048575;
                        r17 = r10;
                        if (r13 != r6) {
                            r11 = r8.getInt(r19, r13);
                            r6 = r13;
                        }
                        r4 = 1 << (r4 >>> 20);
                    }
                    while (r5 != null && r18.zzmy.zza(r5) <= r14) {
                        r18.zzmy.zza(r20, r5);
                        r5 = r3.hasNext() ? r3.next() : null;
                    }
                    long r9 = r12 & 1048575;
                    int r12 = r17;
                    switch (r15) {
                        case 0:
                            if ((r4 & r11) != 0) {
                                r20.zza(r14, com.google.android.gms.internal.clearcut.zzfd.zzn(r19, r9));
                                continue;
                            }
                            r10 = r12 + 4;
                        case 1:
                            if ((r4 & r11) != 0) {
                                r20.zza(r14, com.google.android.gms.internal.clearcut.zzfd.zzm(r19, r9));
                            } else {
                                continue;
                            }
                            r10 = r12 + 4;
                        case 2:
                            if ((r4 & r11) != 0) {
                                r20.zzi(r14, r8.getLong(r19, r9));
                            } else {
                                continue;
                            }
                            r10 = r12 + 4;
                        case 3:
                            if ((r4 & r11) != 0) {
                                r20.zza(r14, r8.getLong(r19, r9));
                            } else {
                                continue;
                            }
                            r10 = r12 + 4;
                        case 4:
                            if ((r4 & r11) != 0) {
                                r20.zzc(r14, r8.getInt(r19, r9));
                            } else {
                                continue;
                            }
                            r10 = r12 + 4;
                        case 5:
                            if ((r4 & r11) != 0) {
                                r20.zzc(r14, r8.getLong(r19, r9));
                            } else {
                                continue;
                            }
                            r10 = r12 + 4;
                        case 6:
                            if ((r4 & r11) != 0) {
                                r20.zzf(r14, r8.getInt(r19, r9));
                            } else {
                                continue;
                            }
                            r10 = r12 + 4;
                        case 7:
                            if ((r4 & r11) != 0) {
                                r20.zzb(r14, com.google.android.gms.internal.clearcut.zzfd.zzl(r19, r9));
                            } else {
                                continue;
                            }
                            r10 = r12 + 4;
                        case 8:
                            if ((r4 & r11) != 0) {
                                zza(r14, r8.getObject(r19, r9), r20);
                            } else {
                                continue;
                            }
                            r10 = r12 + 4;
                        case 9:
                            if ((r4 & r11) != 0) {
                                r20.zza(r14, r8.getObject(r19, r9), zzad(r12));
                            } else {
                                continue;
                            }
                            r10 = r12 + 4;
                        case 10:
                            if ((r4 & r11) != 0) {
                                r20.zza(r14, (com.google.android.gms.internal.clearcut.zzbb) r8.getObject(r19, r9));
                            } else {
                                continue;
                            }
                            r10 = r12 + 4;
                        case 11:
                            if ((r4 & r11) != 0) {
                                r20.zzd(r14, r8.getInt(r19, r9));
                            } else {
                                continue;
                            }
                            r10 = r12 + 4;
                        case 12:
                            if ((r4 & r11) != 0) {
                                r20.zzn(r14, r8.getInt(r19, r9));
                            } else {
                                continue;
                            }
                            r10 = r12 + 4;
                        case 13:
                            if ((r4 & r11) != 0) {
                                r20.zzm(r14, r8.getInt(r19, r9));
                            } else {
                                continue;
                            }
                            r10 = r12 + 4;
                        case 14:
                            if ((r4 & r11) != 0) {
                                r20.zzj(r14, r8.getLong(r19, r9));
                            } else {
                                continue;
                            }
                            r10 = r12 + 4;
                        case 15:
                            if ((r4 & r11) != 0) {
                                r20.zze(r14, r8.getInt(r19, r9));
                            } else {
                                continue;
                            }
                            r10 = r12 + 4;
                        case 16:
                            if ((r4 & r11) != 0) {
                                r20.zzb(r14, r8.getLong(r19, r9));
                            } else {
                                continue;
                            }
                            r10 = r12 + 4;
                        case 17:
                            if ((r4 & r11) != 0) {
                                r20.zzb(r14, r8.getObject(r19, r9), zzad(r12));
                            } else {
                                continue;
                            }
                            r10 = r12 + 4;
                        case 18:
                            com.google.android.gms.internal.clearcut.zzeh.zza(r18.zzmi[r12], (java.util.List<java.lang.Double>) r8.getObject(r19, r9), r20, false);
                            continue;
                            r10 = r12 + 4;
                        case 19:
                            com.google.android.gms.internal.clearcut.zzeh.zzb(r18.zzmi[r12], (java.util.List<java.lang.Float>) r8.getObject(r19, r9), r20, false);
                            continue;
                            r10 = r12 + 4;
                        case 20:
                            com.google.android.gms.internal.clearcut.zzeh.zzc(r18.zzmi[r12], (java.util.List) r8.getObject(r19, r9), r20, false);
                            continue;
                            r10 = r12 + 4;
                        case 21:
                            com.google.android.gms.internal.clearcut.zzeh.zzd(r18.zzmi[r12], (java.util.List) r8.getObject(r19, r9), r20, false);
                            continue;
                            r10 = r12 + 4;
                        case 22:
                            com.google.android.gms.internal.clearcut.zzeh.zzh(r18.zzmi[r12], (java.util.List) r8.getObject(r19, r9), r20, false);
                            continue;
                            r10 = r12 + 4;
                        case 23:
                            com.google.android.gms.internal.clearcut.zzeh.zzf(r18.zzmi[r12], (java.util.List) r8.getObject(r19, r9), r20, false);
                            continue;
                            r10 = r12 + 4;
                        case 24:
                            com.google.android.gms.internal.clearcut.zzeh.zzk(r18.zzmi[r12], (java.util.List) r8.getObject(r19, r9), r20, false);
                            continue;
                            r10 = r12 + 4;
                        case 25:
                            com.google.android.gms.internal.clearcut.zzeh.zzn(r18.zzmi[r12], (java.util.List) r8.getObject(r19, r9), r20, false);
                            continue;
                            r10 = r12 + 4;
                        case 26:
                            com.google.android.gms.internal.clearcut.zzeh.zza(r18.zzmi[r12], (java.util.List) r8.getObject(r19, r9), r20);
                            break;
                        case 27:
                            com.google.android.gms.internal.clearcut.zzeh.zza(r18.zzmi[r12], (java.util.List) r8.getObject(r19, r9), r20, zzad(r12));
                            break;
                        case 28:
                            com.google.android.gms.internal.clearcut.zzeh.zzb(r18.zzmi[r12], (java.util.List) r8.getObject(r19, r9), r20);
                            break;
                        case 29:
                            com.google.android.gms.internal.clearcut.zzeh.zzi(r18.zzmi[r12], (java.util.List) r8.getObject(r19, r9), r20, false);
                            continue;
                            r10 = r12 + 4;
                        case 30:
                            com.google.android.gms.internal.clearcut.zzeh.zzm(r18.zzmi[r12], (java.util.List) r8.getObject(r19, r9), r20, false);
                            continue;
                            r10 = r12 + 4;
                        case 31:
                            com.google.android.gms.internal.clearcut.zzeh.zzl(r18.zzmi[r12], (java.util.List) r8.getObject(r19, r9), r20, false);
                            continue;
                            r10 = r12 + 4;
                        case 32:
                            com.google.android.gms.internal.clearcut.zzeh.zzg(r18.zzmi[r12], (java.util.List) r8.getObject(r19, r9), r20, false);
                            continue;
                            r10 = r12 + 4;
                        case 33:
                            com.google.android.gms.internal.clearcut.zzeh.zzj(r18.zzmi[r12], (java.util.List) r8.getObject(r19, r9), r20, false);
                            continue;
                            r10 = r12 + 4;
                        case 34:
                            com.google.android.gms.internal.clearcut.zzeh.zze(r18.zzmi[r12], (java.util.List) r8.getObject(r19, r9), r20, false);
                            continue;
                            r10 = r12 + 4;
                        case 35:
                            com.google.android.gms.internal.clearcut.zzeh.zza(r18.zzmi[r12], (java.util.List<java.lang.Double>) r8.getObject(r19, r9), r20, true);
                            break;
                        case 36:
                            com.google.android.gms.internal.clearcut.zzeh.zzb(r18.zzmi[r12], (java.util.List<java.lang.Float>) r8.getObject(r19, r9), r20, true);
                            break;
                        case 37:
                            com.google.android.gms.internal.clearcut.zzeh.zzc(r18.zzmi[r12], (java.util.List) r8.getObject(r19, r9), r20, true);
                            break;
                        case 38:
                            com.google.android.gms.internal.clearcut.zzeh.zzd(r18.zzmi[r12], (java.util.List) r8.getObject(r19, r9), r20, true);
                            break;
                        case 39:
                            com.google.android.gms.internal.clearcut.zzeh.zzh(r18.zzmi[r12], (java.util.List) r8.getObject(r19, r9), r20, true);
                            break;
                        case 40:
                            com.google.android.gms.internal.clearcut.zzeh.zzf(r18.zzmi[r12], (java.util.List) r8.getObject(r19, r9), r20, true);
                            break;
                        case 41:
                            com.google.android.gms.internal.clearcut.zzeh.zzk(r18.zzmi[r12], (java.util.List) r8.getObject(r19, r9), r20, true);
                            break;
                        case 42:
                            com.google.android.gms.internal.clearcut.zzeh.zzn(r18.zzmi[r12], (java.util.List) r8.getObject(r19, r9), r20, true);
                            break;
                        case 43:
                            com.google.android.gms.internal.clearcut.zzeh.zzi(r18.zzmi[r12], (java.util.List) r8.getObject(r19, r9), r20, true);
                            break;
                        case 44:
                            com.google.android.gms.internal.clearcut.zzeh.zzm(r18.zzmi[r12], (java.util.List) r8.getObject(r19, r9), r20, true);
                            break;
                        case 45:
                            com.google.android.gms.internal.clearcut.zzeh.zzl(r18.zzmi[r12], (java.util.List) r8.getObject(r19, r9), r20, true);
                            break;
                        case 46:
                            com.google.android.gms.internal.clearcut.zzeh.zzg(r18.zzmi[r12], (java.util.List) r8.getObject(r19, r9), r20, true);
                            break;
                        case 47:
                            com.google.android.gms.internal.clearcut.zzeh.zzj(r18.zzmi[r12], (java.util.List) r8.getObject(r19, r9), r20, true);
                            break;
                        case 48:
                            com.google.android.gms.internal.clearcut.zzeh.zze(r18.zzmi[r12], (java.util.List) r8.getObject(r19, r9), r20, true);
                            break;
                        case 49:
                            com.google.android.gms.internal.clearcut.zzeh.zzb(r18.zzmi[r12], (java.util.List) r8.getObject(r19, r9), r20, zzad(r12));
                            break;
                        case 50:
                            zza(r20, r14, r8.getObject(r19, r9), r12);
                            break;
                        case 51:
                            if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r19, r14, r12)) {
                                r20.zza(r14, zze(r19, r9));
                                break;
                            }
                            break;
                        case 52:
                            if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r19, r14, r12)) {
                                r20.zza(r14, zzf(r19, r9));
                                break;
                            }
                            break;
                        case 53:
                            if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r19, r14, r12)) {
                                r20.zzi(r14, zzh(r19, r9));
                                break;
                            }
                            break;
                        case 54:
                            if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r19, r14, r12)) {
                                r20.zza(r14, zzh(r19, r9));
                                break;
                            }
                            break;
                        case 55:
                            if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r19, r14, r12)) {
                                r20.zzc(r14, zzg(r19, r9));
                                break;
                            }
                            break;
                        case 56:
                            if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r19, r14, r12)) {
                                r20.zzc(r14, zzh(r19, r9));
                                break;
                            }
                            break;
                        case 57:
                            if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r19, r14, r12)) {
                                r20.zzf(r14, zzg(r19, r9));
                                break;
                            }
                            break;
                        case 58:
                            if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r19, r14, r12)) {
                                r20.zzb(r14, zzi(r19, r9));
                                break;
                            }
                            break;
                        case 59:
                            if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r19, r14, r12)) {
                                zza(r14, r8.getObject(r19, r9), r20);
                                break;
                            }
                            break;
                        case 60:
                            if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r19, r14, r12)) {
                                r20.zza(r14, r8.getObject(r19, r9), zzad(r12));
                                break;
                            }
                            break;
                        case 61:
                            if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r19, r14, r12)) {
                                r20.zza(r14, (com.google.android.gms.internal.clearcut.zzbb) r8.getObject(r19, r9));
                                break;
                            }
                            break;
                        case 62:
                            if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r19, r14, r12)) {
                                r20.zzd(r14, zzg(r19, r9));
                                break;
                            }
                            break;
                        case 63:
                            if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r19, r14, r12)) {
                                r20.zzn(r14, zzg(r19, r9));
                                break;
                            }
                            break;
                        case 64:
                            if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r19, r14, r12)) {
                                r20.zzm(r14, zzg(r19, r9));
                                break;
                            }
                            break;
                        case 65:
                            if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r19, r14, r12)) {
                                r20.zzj(r14, zzh(r19, r9));
                                break;
                            }
                            break;
                        case 66:
                            if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r19, r14, r12)) {
                                r20.zze(r14, zzg(r19, r9));
                                break;
                            }
                            break;
                        case 67:
                            if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r19, r14, r12)) {
                                r20.zzb(r14, zzh(r19, r9));
                                break;
                            }
                            break;
                        case 68:
                            if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r19, r14, r12)) {
                                r20.zzb(r14, r8.getObject(r19, r9), zzad(r12));
                                break;
                            }
                            break;
                    }
                    r10 = r12 + 4;
                }
                while (r5 != null) {
                    r18.zzmy.zza(r20, r5);
                    r5 = r3.hasNext() ? r3.next() : null;
                }
                zza(r18.zzmx, r19, r20);
            }
        }
        r3 = null;
        r5 = null;
        int r6 = -1;
        r7 = r18.zzmi.length;
        sun.misc.Unsafe r8 = com.google.android.gms.internal.clearcut.zzds.zzmh;
        r10 = 0;
        int r11 = 0;
        while (r10 < r7) {
        }
        while (r5 != null) {
        }
        zza(r18.zzmx, r19, r20);
    }

    private final void zzb(T t, T t2, int i) {
        int zzag = zzag(i);
        int i2 = this.zzmi[i];
        long j = zzag & 1048575;
        if (zza((zzds<T>) t2, i2, i)) {
            Object zzo = zzfd.zzo(t, j);
            Object zzo2 = zzfd.zzo(t2, j);
            if (zzo != null && zzo2 != null) {
                zzfd.zza(t, j, zzci.zza(zzo, zzo2));
                zzb((zzds<T>) t, i2, i);
            } else if (zzo2 != null) {
                zzfd.zza(t, j, zzo2);
                zzb((zzds<T>) t, i2, i);
            }
        }
    }

    private final boolean zzc(T t, T t2, int i) {
        return zza((zzds<T>) t, i) == zza((zzds<T>) t2, i);
    }

    private static <E> List<E> zzd(Object obj, long j) {
        return (List) zzfd.zzo(obj, j);
    }

    private static <T> double zze(T t, long j) {
        return ((Double) zzfd.zzo(t, j)).doubleValue();
    }

    private static <T> float zzf(T t, long j) {
        return ((Float) zzfd.zzo(t, j)).floatValue();
    }

    private static <T> int zzg(T t, long j) {
        return ((Integer) zzfd.zzo(t, j)).intValue();
    }

    private static <T> long zzh(T t, long j) {
        return ((Long) zzfd.zzo(t, j)).longValue();
    }

    private static <T> boolean zzi(T t, long j) {
        return ((Boolean) zzfd.zzo(t, j)).booleanValue();
    }

    private static zzey zzn(Object obj) {
        zzcg zzcgVar = (zzcg) obj;
        zzey zzeyVar = zzcgVar.zzjp;
        if (zzeyVar == zzey.zzea()) {
            zzey zzeb = zzey.zzeb();
            zzcgVar.zzjp = zzeb;
            return zzeb;
        }
        return zzeyVar;
    }

    /* JADX WARN: Code restructure failed: missing block: B:102:0x01a0, code lost:
        if (com.google.android.gms.internal.clearcut.zzfd.zzk(r10, r6) == com.google.android.gms.internal.clearcut.zzfd.zzk(r11, r6)) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0038, code lost:
        if (com.google.android.gms.internal.clearcut.zzeh.zzd(com.google.android.gms.internal.clearcut.zzfd.zzo(r10, r6), com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r6)) != false) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x005c, code lost:
        if (com.google.android.gms.internal.clearcut.zzeh.zzd(com.google.android.gms.internal.clearcut.zzfd.zzo(r10, r6), com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r6)) != false) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0070, code lost:
        if (com.google.android.gms.internal.clearcut.zzfd.zzk(r10, r6) == com.google.android.gms.internal.clearcut.zzfd.zzk(r11, r6)) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0082, code lost:
        if (com.google.android.gms.internal.clearcut.zzfd.zzj(r10, r6) == com.google.android.gms.internal.clearcut.zzfd.zzj(r11, r6)) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0096, code lost:
        if (com.google.android.gms.internal.clearcut.zzfd.zzk(r10, r6) == com.google.android.gms.internal.clearcut.zzfd.zzk(r11, r6)) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00a8, code lost:
        if (com.google.android.gms.internal.clearcut.zzfd.zzj(r10, r6) == com.google.android.gms.internal.clearcut.zzfd.zzj(r11, r6)) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00ba, code lost:
        if (com.google.android.gms.internal.clearcut.zzfd.zzj(r10, r6) == com.google.android.gms.internal.clearcut.zzfd.zzj(r11, r6)) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00cc, code lost:
        if (com.google.android.gms.internal.clearcut.zzfd.zzj(r10, r6) == com.google.android.gms.internal.clearcut.zzfd.zzj(r11, r6)) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x00e2, code lost:
        if (com.google.android.gms.internal.clearcut.zzeh.zzd(com.google.android.gms.internal.clearcut.zzfd.zzo(r10, r6), com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r6)) != false) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x00f8, code lost:
        if (com.google.android.gms.internal.clearcut.zzeh.zzd(com.google.android.gms.internal.clearcut.zzfd.zzo(r10, r6), com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r6)) != false) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x010e, code lost:
        if (com.google.android.gms.internal.clearcut.zzeh.zzd(com.google.android.gms.internal.clearcut.zzfd.zzo(r10, r6), com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r6)) != false) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x0120, code lost:
        if (com.google.android.gms.internal.clearcut.zzfd.zzl(r10, r6) == com.google.android.gms.internal.clearcut.zzfd.zzl(r11, r6)) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x0132, code lost:
        if (com.google.android.gms.internal.clearcut.zzfd.zzj(r10, r6) == com.google.android.gms.internal.clearcut.zzfd.zzj(r11, r6)) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x0145, code lost:
        if (com.google.android.gms.internal.clearcut.zzfd.zzk(r10, r6) == com.google.android.gms.internal.clearcut.zzfd.zzk(r11, r6)) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x0156, code lost:
        if (com.google.android.gms.internal.clearcut.zzfd.zzj(r10, r6) == com.google.android.gms.internal.clearcut.zzfd.zzj(r11, r6)) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x0169, code lost:
        if (com.google.android.gms.internal.clearcut.zzfd.zzk(r10, r6) == com.google.android.gms.internal.clearcut.zzfd.zzk(r11, r6)) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x017c, code lost:
        if (com.google.android.gms.internal.clearcut.zzfd.zzk(r10, r6) == com.google.android.gms.internal.clearcut.zzfd.zzk(r11, r6)) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x018d, code lost:
        if (com.google.android.gms.internal.clearcut.zzfd.zzj(r10, r6) == com.google.android.gms.internal.clearcut.zzfd.zzj(r11, r6)) goto L84;
     */
    @Override // com.google.android.gms.internal.clearcut.zzef
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean equals(T r10, T r11) {
        int r0 = r9.zzmi.length;
        int r2 = 0;
        while (true) {
            boolean r3 = true;
            if (r2 >= r0) {
                if (r9.zzmx.zzq(r10).equals(r9.zzmx.zzq(r11))) {
                    if (r9.zzmo) {
                        return r9.zzmy.zza(r10).equals(r9.zzmy.zza(r11));
                    }
                    return true;
                }
                return false;
            }
            int r4 = zzag(r2);
            long r6 = r4 & 1048575;
            switch ((r4 & 267386880) >>> 20) {
                case 0:
                    if (zzc(r10, r11, r2)) {
                        break;
                    }
                    r3 = false;
                    break;
                case 1:
                    if (zzc(r10, r11, r2)) {
                        break;
                    }
                    r3 = false;
                    break;
                case 2:
                    if (zzc(r10, r11, r2)) {
                        break;
                    }
                    r3 = false;
                    break;
                case 3:
                    if (zzc(r10, r11, r2)) {
                        break;
                    }
                    r3 = false;
                    break;
                case 4:
                    if (zzc(r10, r11, r2)) {
                        break;
                    }
                    r3 = false;
                    break;
                case 5:
                    if (zzc(r10, r11, r2)) {
                        break;
                    }
                    r3 = false;
                    break;
                case 6:
                    if (zzc(r10, r11, r2)) {
                        break;
                    }
                    r3 = false;
                    break;
                case 7:
                    if (zzc(r10, r11, r2)) {
                        break;
                    }
                    r3 = false;
                    break;
                case 8:
                    if (zzc(r10, r11, r2)) {
                        break;
                    }
                    r3 = false;
                    break;
                case 9:
                    if (zzc(r10, r11, r2)) {
                        break;
                    }
                    r3 = false;
                    break;
                case 10:
                    if (zzc(r10, r11, r2)) {
                        break;
                    }
                    r3 = false;
                    break;
                case 11:
                    if (zzc(r10, r11, r2)) {
                        break;
                    }
                    r3 = false;
                    break;
                case 12:
                    if (zzc(r10, r11, r2)) {
                        break;
                    }
                    r3 = false;
                    break;
                case 13:
                    if (zzc(r10, r11, r2)) {
                        break;
                    }
                    r3 = false;
                    break;
                case 14:
                    if (zzc(r10, r11, r2)) {
                        break;
                    }
                    r3 = false;
                    break;
                case 15:
                    if (zzc(r10, r11, r2)) {
                        break;
                    }
                    r3 = false;
                    break;
                case 16:
                    if (zzc(r10, r11, r2)) {
                        break;
                    }
                    r3 = false;
                    break;
                case 17:
                    if (zzc(r10, r11, r2)) {
                        break;
                    }
                    r3 = false;
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
                case 50:
                    r3 = com.google.android.gms.internal.clearcut.zzeh.zzd(com.google.android.gms.internal.clearcut.zzfd.zzo(r10, r6), com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r6));
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
                    long r4 = zzah(r2) & 1048575;
                    if (com.google.android.gms.internal.clearcut.zzfd.zzj(r10, r4) == com.google.android.gms.internal.clearcut.zzfd.zzj(r11, r4)) {
                        break;
                    }
                    r3 = false;
                    break;
            }
            if (!r3) {
                return false;
            }
            r2 += 4;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x00ce, code lost:
        if (r3 != null) goto L77;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x00e0, code lost:
        if (r3 != null) goto L77;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x00e2, code lost:
        r7 = r3.hashCode();
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x00e6, code lost:
        r2 = (r2 * 53) + r7;
     */
    @Override // com.google.android.gms.internal.clearcut.zzef
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int hashCode(T r9) {
        int r2;
        double r3;
        float r3;
        long r3;
        int r3;
        boolean r3;
        java.lang.Object r3;
        java.lang.Object r3;
        int r0 = r8.zzmi.length;
        int r2 = 0;
        for (int r1 = 0; r1 < r0; r1 += 4) {
            int r3 = zzag(r1);
            int r4 = r8.zzmi[r1];
            long r5 = 1048575 & r3;
            int r7 = 37;
            switch ((r3 & 267386880) >>> 20) {
                case 0:
                    r2 = r2 * 53;
                    r3 = com.google.android.gms.internal.clearcut.zzfd.zzn(r9, r5);
                    r3 = java.lang.Double.doubleToLongBits(r3);
                    r3 = com.google.android.gms.internal.clearcut.zzci.zzl(r3);
                    r2 = r2 + r3;
                    break;
                case 1:
                    r2 = r2 * 53;
                    r3 = com.google.android.gms.internal.clearcut.zzfd.zzm(r9, r5);
                    r3 = java.lang.Float.floatToIntBits(r3);
                    r2 = r2 + r3;
                    break;
                case 2:
                case 3:
                case 5:
                case 14:
                case 16:
                    r2 = r2 * 53;
                    r3 = com.google.android.gms.internal.clearcut.zzfd.zzk(r9, r5);
                    r3 = com.google.android.gms.internal.clearcut.zzci.zzl(r3);
                    r2 = r2 + r3;
                    break;
                case 4:
                case 6:
                case 11:
                case 12:
                case 13:
                case 15:
                    r2 = r2 * 53;
                    r3 = com.google.android.gms.internal.clearcut.zzfd.zzj(r9, r5);
                    r2 = r2 + r3;
                    break;
                case 7:
                    r2 = r2 * 53;
                    r3 = com.google.android.gms.internal.clearcut.zzfd.zzl(r9, r5);
                    r3 = com.google.android.gms.internal.clearcut.zzci.zzc(r3);
                    r2 = r2 + r3;
                    break;
                case 8:
                    r2 = r2 * 53;
                    r3 = ((java.lang.String) com.google.android.gms.internal.clearcut.zzfd.zzo(r9, r5)).hashCode();
                    r2 = r2 + r3;
                    break;
                case 9:
                    r3 = com.google.android.gms.internal.clearcut.zzfd.zzo(r9, r5);
                    break;
                case 10:
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
                case 50:
                    r2 = r2 * 53;
                    r3 = com.google.android.gms.internal.clearcut.zzfd.zzo(r9, r5);
                    r3 = r3.hashCode();
                    r2 = r2 + r3;
                    break;
                case 17:
                    r3 = com.google.android.gms.internal.clearcut.zzfd.zzo(r9, r5);
                    break;
                case 51:
                    if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r9, r4, r1)) {
                        r2 = r2 * 53;
                        r3 = zze(r9, r5);
                        r3 = java.lang.Double.doubleToLongBits(r3);
                        r3 = com.google.android.gms.internal.clearcut.zzci.zzl(r3);
                        r2 = r2 + r3;
                        break;
                    } else {
                        break;
                    }
                case 52:
                    if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r9, r4, r1)) {
                        r2 = r2 * 53;
                        r3 = zzf(r9, r5);
                        r3 = java.lang.Float.floatToIntBits(r3);
                        r2 = r2 + r3;
                        break;
                    } else {
                        break;
                    }
                case 53:
                    if (!zza((com.google.android.gms.internal.clearcut.zzds<T>) r9, r4, r1)) {
                        break;
                    }
                    r2 = r2 * 53;
                    r3 = zzh(r9, r5);
                    r3 = com.google.android.gms.internal.clearcut.zzci.zzl(r3);
                    r2 = r2 + r3;
                    break;
                case 54:
                    if (!zza((com.google.android.gms.internal.clearcut.zzds<T>) r9, r4, r1)) {
                        break;
                    }
                    r2 = r2 * 53;
                    r3 = zzh(r9, r5);
                    r3 = com.google.android.gms.internal.clearcut.zzci.zzl(r3);
                    r2 = r2 + r3;
                    break;
                case 55:
                    if (!zza((com.google.android.gms.internal.clearcut.zzds<T>) r9, r4, r1)) {
                        break;
                    }
                    r2 = r2 * 53;
                    r3 = zzg(r9, r5);
                    r2 = r2 + r3;
                    break;
                case 56:
                    if (!zza((com.google.android.gms.internal.clearcut.zzds<T>) r9, r4, r1)) {
                        break;
                    }
                    r2 = r2 * 53;
                    r3 = zzh(r9, r5);
                    r3 = com.google.android.gms.internal.clearcut.zzci.zzl(r3);
                    r2 = r2 + r3;
                    break;
                case 57:
                    if (!zza((com.google.android.gms.internal.clearcut.zzds<T>) r9, r4, r1)) {
                        break;
                    }
                    r2 = r2 * 53;
                    r3 = zzg(r9, r5);
                    r2 = r2 + r3;
                    break;
                case 58:
                    if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r9, r4, r1)) {
                        r2 = r2 * 53;
                        r3 = zzi(r9, r5);
                        r3 = com.google.android.gms.internal.clearcut.zzci.zzc(r3);
                        r2 = r2 + r3;
                        break;
                    } else {
                        break;
                    }
                case 59:
                    if (!zza((com.google.android.gms.internal.clearcut.zzds<T>) r9, r4, r1)) {
                        break;
                    }
                    r2 = r2 * 53;
                    r3 = ((java.lang.String) com.google.android.gms.internal.clearcut.zzfd.zzo(r9, r5)).hashCode();
                    r2 = r2 + r3;
                    break;
                case 60:
                    if (!zza((com.google.android.gms.internal.clearcut.zzds<T>) r9, r4, r1)) {
                        break;
                    }
                    r3 = com.google.android.gms.internal.clearcut.zzfd.zzo(r9, r5);
                    r2 = r2 * 53;
                    r3 = r3.hashCode();
                    r2 = r2 + r3;
                    break;
                case 61:
                    if (!zza((com.google.android.gms.internal.clearcut.zzds<T>) r9, r4, r1)) {
                        break;
                    }
                    r2 = r2 * 53;
                    r3 = com.google.android.gms.internal.clearcut.zzfd.zzo(r9, r5);
                    r3 = r3.hashCode();
                    r2 = r2 + r3;
                    break;
                case 62:
                    if (!zza((com.google.android.gms.internal.clearcut.zzds<T>) r9, r4, r1)) {
                        break;
                    }
                    r2 = r2 * 53;
                    r3 = zzg(r9, r5);
                    r2 = r2 + r3;
                    break;
                case 63:
                    if (!zza((com.google.android.gms.internal.clearcut.zzds<T>) r9, r4, r1)) {
                        break;
                    }
                    r2 = r2 * 53;
                    r3 = zzg(r9, r5);
                    r2 = r2 + r3;
                    break;
                case 64:
                    if (!zza((com.google.android.gms.internal.clearcut.zzds<T>) r9, r4, r1)) {
                        break;
                    }
                    r2 = r2 * 53;
                    r3 = zzg(r9, r5);
                    r2 = r2 + r3;
                    break;
                case 65:
                    if (!zza((com.google.android.gms.internal.clearcut.zzds<T>) r9, r4, r1)) {
                        break;
                    }
                    r2 = r2 * 53;
                    r3 = zzh(r9, r5);
                    r3 = com.google.android.gms.internal.clearcut.zzci.zzl(r3);
                    r2 = r2 + r3;
                    break;
                case 66:
                    if (!zza((com.google.android.gms.internal.clearcut.zzds<T>) r9, r4, r1)) {
                        break;
                    }
                    r2 = r2 * 53;
                    r3 = zzg(r9, r5);
                    r2 = r2 + r3;
                    break;
                case 67:
                    if (!zza((com.google.android.gms.internal.clearcut.zzds<T>) r9, r4, r1)) {
                        break;
                    }
                    r2 = r2 * 53;
                    r3 = zzh(r9, r5);
                    r3 = com.google.android.gms.internal.clearcut.zzci.zzl(r3);
                    r2 = r2 + r3;
                    break;
                case 68:
                    if (!zza((com.google.android.gms.internal.clearcut.zzds<T>) r9, r4, r1)) {
                        break;
                    }
                    r3 = com.google.android.gms.internal.clearcut.zzfd.zzo(r9, r5);
                    r2 = r2 * 53;
                    r3 = r3.hashCode();
                    r2 = r2 + r3;
                    break;
            }
        }
        int r2 = (r2 * 53) + r8.zzmx.zzq(r9).hashCode();
        return r8.zzmo ? (r2 * 53) + r8.zzmy.zza(r9).hashCode() : r2;
    }

    @Override // com.google.android.gms.internal.clearcut.zzef
    public final T newInstance() {
        return (T) this.zzmv.newInstance(this.zzmn);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0039  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x04b9  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x04f6  */
    /* JADX WARN: Removed duplicated region for block: B:363:0x0976  */
    @Override // com.google.android.gms.internal.clearcut.zzef
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void zza(T r14, com.google.android.gms.internal.clearcut.zzfr r15) throws java.io.IOException {
        java.util.Iterator<java.util.Map.Entry<?, java.lang.Object>> r0;
        java.util.Map.Entry<?, java.lang.Object> r1;
        int r7;
        int r8;
        double r11;
        float r9;
        long r11;
        long r11;
        int r9;
        long r11;
        int r9;
        boolean r9;
        int r9;
        int r9;
        int r9;
        long r11;
        int r9;
        long r11;
        java.util.Iterator<java.util.Map.Entry<?, java.lang.Object>> r0;
        java.util.Map.Entry<?, java.lang.Object> r1;
        int r7;
        double r10;
        float r8;
        long r10;
        long r10;
        int r8;
        long r10;
        int r8;
        boolean r8;
        int r8;
        int r8;
        int r8;
        long r10;
        int r8;
        long r10;
        if (r15.zzaj() == com.google.android.gms.internal.clearcut.zzcg.zzg.zzkp) {
            zza(r13.zzmx, r14, r15);
            if (r13.zzmo) {
                com.google.android.gms.internal.clearcut.zzby<?> r0 = r13.zzmy.zza(r14);
                if (!r0.isEmpty()) {
                    r0 = r0.descendingIterator();
                    r1 = r0.next();
                    for (r7 = r13.zzmi.length - 4; r7 >= 0; r7 -= 4) {
                        int r8 = zzag(r7);
                        int r9 = r13.zzmi[r7];
                        while (r1 != null && r13.zzmy.zza(r1) > r9) {
                            r13.zzmy.zza(r15, r1);
                            r1 = r0.hasNext() ? r0.next() : null;
                        }
                        switch ((r8 & 267386880) >>> 20) {
                            case 0:
                                if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r14, r7)) {
                                    r10 = com.google.android.gms.internal.clearcut.zzfd.zzn(r14, r8 & 1048575);
                                    r15.zza(r9, r10);
                                    break;
                                } else {
                                    break;
                                }
                            case 1:
                                if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r14, r7)) {
                                    r8 = com.google.android.gms.internal.clearcut.zzfd.zzm(r14, r8 & 1048575);
                                    r15.zza(r9, r8);
                                    break;
                                } else {
                                    break;
                                }
                            case 2:
                                if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r14, r7)) {
                                    r10 = com.google.android.gms.internal.clearcut.zzfd.zzk(r14, r8 & 1048575);
                                    r15.zzi(r9, r10);
                                    break;
                                } else {
                                    break;
                                }
                            case 3:
                                if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r14, r7)) {
                                    r10 = com.google.android.gms.internal.clearcut.zzfd.zzk(r14, r8 & 1048575);
                                    r15.zza(r9, r10);
                                    break;
                                } else {
                                    break;
                                }
                            case 4:
                                if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r14, r7)) {
                                    r8 = com.google.android.gms.internal.clearcut.zzfd.zzj(r14, r8 & 1048575);
                                    r15.zzc(r9, r8);
                                    break;
                                } else {
                                    break;
                                }
                            case 5:
                                if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r14, r7)) {
                                    r10 = com.google.android.gms.internal.clearcut.zzfd.zzk(r14, r8 & 1048575);
                                    r15.zzc(r9, r10);
                                    break;
                                } else {
                                    break;
                                }
                            case 6:
                                if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r14, r7)) {
                                    r8 = com.google.android.gms.internal.clearcut.zzfd.zzj(r14, r8 & 1048575);
                                    r15.zzf(r9, r8);
                                    break;
                                } else {
                                    break;
                                }
                            case 7:
                                if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r14, r7)) {
                                    r8 = com.google.android.gms.internal.clearcut.zzfd.zzl(r14, r8 & 1048575);
                                    r15.zzb(r9, r8);
                                    break;
                                } else {
                                    break;
                                }
                            case 8:
                                if (!zza((com.google.android.gms.internal.clearcut.zzds<T>) r14, r7)) {
                                    break;
                                }
                                zza(r9, com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r8 & 1048575), r15);
                                break;
                            case 9:
                                if (!zza((com.google.android.gms.internal.clearcut.zzds<T>) r14, r7)) {
                                    break;
                                }
                                r15.zza(r9, com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r8 & 1048575), zzad(r7));
                                break;
                            case 10:
                                if (!zza((com.google.android.gms.internal.clearcut.zzds<T>) r14, r7)) {
                                    break;
                                }
                                r15.zza(r9, (com.google.android.gms.internal.clearcut.zzbb) com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r8 & 1048575));
                                break;
                            case 11:
                                if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r14, r7)) {
                                    r8 = com.google.android.gms.internal.clearcut.zzfd.zzj(r14, r8 & 1048575);
                                    r15.zzd(r9, r8);
                                    break;
                                } else {
                                    break;
                                }
                            case 12:
                                if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r14, r7)) {
                                    r8 = com.google.android.gms.internal.clearcut.zzfd.zzj(r14, r8 & 1048575);
                                    r15.zzn(r9, r8);
                                    break;
                                } else {
                                    break;
                                }
                            case 13:
                                if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r14, r7)) {
                                    r8 = com.google.android.gms.internal.clearcut.zzfd.zzj(r14, r8 & 1048575);
                                    r15.zzm(r9, r8);
                                    break;
                                } else {
                                    break;
                                }
                            case 14:
                                if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r14, r7)) {
                                    r10 = com.google.android.gms.internal.clearcut.zzfd.zzk(r14, r8 & 1048575);
                                    r15.zzj(r9, r10);
                                    break;
                                } else {
                                    break;
                                }
                            case 15:
                                if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r14, r7)) {
                                    r8 = com.google.android.gms.internal.clearcut.zzfd.zzj(r14, r8 & 1048575);
                                    r15.zze(r9, r8);
                                    break;
                                } else {
                                    break;
                                }
                            case 16:
                                if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r14, r7)) {
                                    r10 = com.google.android.gms.internal.clearcut.zzfd.zzk(r14, r8 & 1048575);
                                    r15.zzb(r9, r10);
                                    break;
                                } else {
                                    break;
                                }
                            case 17:
                                if (!zza((com.google.android.gms.internal.clearcut.zzds<T>) r14, r7)) {
                                    break;
                                }
                                r15.zzb(r9, com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r8 & 1048575), zzad(r7));
                                break;
                            case 18:
                                com.google.android.gms.internal.clearcut.zzeh.zza(r13.zzmi[r7], (java.util.List<java.lang.Double>) com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r8 & 1048575), r15, false);
                                break;
                            case 19:
                                com.google.android.gms.internal.clearcut.zzeh.zzb(r13.zzmi[r7], (java.util.List<java.lang.Float>) com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r8 & 1048575), r15, false);
                                break;
                            case 20:
                                com.google.android.gms.internal.clearcut.zzeh.zzc(r13.zzmi[r7], (java.util.List) com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r8 & 1048575), r15, false);
                                break;
                            case 21:
                                com.google.android.gms.internal.clearcut.zzeh.zzd(r13.zzmi[r7], (java.util.List) com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r8 & 1048575), r15, false);
                                break;
                            case 22:
                                com.google.android.gms.internal.clearcut.zzeh.zzh(r13.zzmi[r7], (java.util.List) com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r8 & 1048575), r15, false);
                                break;
                            case 23:
                                com.google.android.gms.internal.clearcut.zzeh.zzf(r13.zzmi[r7], (java.util.List) com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r8 & 1048575), r15, false);
                                break;
                            case 24:
                                com.google.android.gms.internal.clearcut.zzeh.zzk(r13.zzmi[r7], (java.util.List) com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r8 & 1048575), r15, false);
                                break;
                            case 25:
                                com.google.android.gms.internal.clearcut.zzeh.zzn(r13.zzmi[r7], (java.util.List) com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r8 & 1048575), r15, false);
                                break;
                            case 26:
                                com.google.android.gms.internal.clearcut.zzeh.zza(r13.zzmi[r7], (java.util.List) com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r8 & 1048575), r15);
                                break;
                            case 27:
                                com.google.android.gms.internal.clearcut.zzeh.zza(r13.zzmi[r7], (java.util.List) com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r8 & 1048575), r15, zzad(r7));
                                break;
                            case 28:
                                com.google.android.gms.internal.clearcut.zzeh.zzb(r13.zzmi[r7], (java.util.List) com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r8 & 1048575), r15);
                                break;
                            case 29:
                                com.google.android.gms.internal.clearcut.zzeh.zzi(r13.zzmi[r7], (java.util.List) com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r8 & 1048575), r15, false);
                                break;
                            case 30:
                                com.google.android.gms.internal.clearcut.zzeh.zzm(r13.zzmi[r7], (java.util.List) com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r8 & 1048575), r15, false);
                                break;
                            case 31:
                                com.google.android.gms.internal.clearcut.zzeh.zzl(r13.zzmi[r7], (java.util.List) com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r8 & 1048575), r15, false);
                                break;
                            case 32:
                                com.google.android.gms.internal.clearcut.zzeh.zzg(r13.zzmi[r7], (java.util.List) com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r8 & 1048575), r15, false);
                                break;
                            case 33:
                                com.google.android.gms.internal.clearcut.zzeh.zzj(r13.zzmi[r7], (java.util.List) com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r8 & 1048575), r15, false);
                                break;
                            case 34:
                                com.google.android.gms.internal.clearcut.zzeh.zze(r13.zzmi[r7], (java.util.List) com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r8 & 1048575), r15, false);
                                break;
                            case 35:
                                com.google.android.gms.internal.clearcut.zzeh.zza(r13.zzmi[r7], (java.util.List<java.lang.Double>) com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r8 & 1048575), r15, true);
                                break;
                            case 36:
                                com.google.android.gms.internal.clearcut.zzeh.zzb(r13.zzmi[r7], (java.util.List<java.lang.Float>) com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r8 & 1048575), r15, true);
                                break;
                            case 37:
                                com.google.android.gms.internal.clearcut.zzeh.zzc(r13.zzmi[r7], (java.util.List) com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r8 & 1048575), r15, true);
                                break;
                            case 38:
                                com.google.android.gms.internal.clearcut.zzeh.zzd(r13.zzmi[r7], (java.util.List) com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r8 & 1048575), r15, true);
                                break;
                            case 39:
                                com.google.android.gms.internal.clearcut.zzeh.zzh(r13.zzmi[r7], (java.util.List) com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r8 & 1048575), r15, true);
                                break;
                            case 40:
                                com.google.android.gms.internal.clearcut.zzeh.zzf(r13.zzmi[r7], (java.util.List) com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r8 & 1048575), r15, true);
                                break;
                            case 41:
                                com.google.android.gms.internal.clearcut.zzeh.zzk(r13.zzmi[r7], (java.util.List) com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r8 & 1048575), r15, true);
                                break;
                            case 42:
                                com.google.android.gms.internal.clearcut.zzeh.zzn(r13.zzmi[r7], (java.util.List) com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r8 & 1048575), r15, true);
                                break;
                            case 43:
                                com.google.android.gms.internal.clearcut.zzeh.zzi(r13.zzmi[r7], (java.util.List) com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r8 & 1048575), r15, true);
                                break;
                            case 44:
                                com.google.android.gms.internal.clearcut.zzeh.zzm(r13.zzmi[r7], (java.util.List) com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r8 & 1048575), r15, true);
                                break;
                            case 45:
                                com.google.android.gms.internal.clearcut.zzeh.zzl(r13.zzmi[r7], (java.util.List) com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r8 & 1048575), r15, true);
                                break;
                            case 46:
                                com.google.android.gms.internal.clearcut.zzeh.zzg(r13.zzmi[r7], (java.util.List) com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r8 & 1048575), r15, true);
                                break;
                            case 47:
                                com.google.android.gms.internal.clearcut.zzeh.zzj(r13.zzmi[r7], (java.util.List) com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r8 & 1048575), r15, true);
                                break;
                            case 48:
                                com.google.android.gms.internal.clearcut.zzeh.zze(r13.zzmi[r7], (java.util.List) com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r8 & 1048575), r15, true);
                                break;
                            case 49:
                                com.google.android.gms.internal.clearcut.zzeh.zzb(r13.zzmi[r7], (java.util.List) com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r8 & 1048575), r15, zzad(r7));
                                break;
                            case 50:
                                zza(r15, r9, com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r8 & 1048575), r7);
                                break;
                            case 51:
                                if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r14, r9, r7)) {
                                    r10 = zze(r14, r8 & 1048575);
                                    r15.zza(r9, r10);
                                    break;
                                } else {
                                    break;
                                }
                            case 52:
                                if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r14, r9, r7)) {
                                    r8 = zzf(r14, r8 & 1048575);
                                    r15.zza(r9, r8);
                                    break;
                                } else {
                                    break;
                                }
                            case 53:
                                if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r14, r9, r7)) {
                                    r10 = zzh(r14, r8 & 1048575);
                                    r15.zzi(r9, r10);
                                    break;
                                } else {
                                    break;
                                }
                            case 54:
                                if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r14, r9, r7)) {
                                    r10 = zzh(r14, r8 & 1048575);
                                    r15.zza(r9, r10);
                                    break;
                                } else {
                                    break;
                                }
                            case 55:
                                if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r14, r9, r7)) {
                                    r8 = zzg(r14, r8 & 1048575);
                                    r15.zzc(r9, r8);
                                    break;
                                } else {
                                    break;
                                }
                            case 56:
                                if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r14, r9, r7)) {
                                    r10 = zzh(r14, r8 & 1048575);
                                    r15.zzc(r9, r10);
                                    break;
                                } else {
                                    break;
                                }
                            case 57:
                                if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r14, r9, r7)) {
                                    r8 = zzg(r14, r8 & 1048575);
                                    r15.zzf(r9, r8);
                                    break;
                                } else {
                                    break;
                                }
                            case 58:
                                if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r14, r9, r7)) {
                                    r8 = zzi(r14, r8 & 1048575);
                                    r15.zzb(r9, r8);
                                    break;
                                } else {
                                    break;
                                }
                            case 59:
                                if (!zza((com.google.android.gms.internal.clearcut.zzds<T>) r14, r9, r7)) {
                                    break;
                                }
                                zza(r9, com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r8 & 1048575), r15);
                                break;
                            case 60:
                                if (!zza((com.google.android.gms.internal.clearcut.zzds<T>) r14, r9, r7)) {
                                    break;
                                }
                                r15.zza(r9, com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r8 & 1048575), zzad(r7));
                                break;
                            case 61:
                                if (!zza((com.google.android.gms.internal.clearcut.zzds<T>) r14, r9, r7)) {
                                    break;
                                }
                                r15.zza(r9, (com.google.android.gms.internal.clearcut.zzbb) com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r8 & 1048575));
                                break;
                            case 62:
                                if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r14, r9, r7)) {
                                    r8 = zzg(r14, r8 & 1048575);
                                    r15.zzd(r9, r8);
                                    break;
                                } else {
                                    break;
                                }
                            case 63:
                                if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r14, r9, r7)) {
                                    r8 = zzg(r14, r8 & 1048575);
                                    r15.zzn(r9, r8);
                                    break;
                                } else {
                                    break;
                                }
                            case 64:
                                if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r14, r9, r7)) {
                                    r8 = zzg(r14, r8 & 1048575);
                                    r15.zzm(r9, r8);
                                    break;
                                } else {
                                    break;
                                }
                            case 65:
                                if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r14, r9, r7)) {
                                    r10 = zzh(r14, r8 & 1048575);
                                    r15.zzj(r9, r10);
                                    break;
                                } else {
                                    break;
                                }
                            case 66:
                                if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r14, r9, r7)) {
                                    r8 = zzg(r14, r8 & 1048575);
                                    r15.zze(r9, r8);
                                    break;
                                } else {
                                    break;
                                }
                            case 67:
                                if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r14, r9, r7)) {
                                    r10 = zzh(r14, r8 & 1048575);
                                    r15.zzb(r9, r10);
                                    break;
                                } else {
                                    break;
                                }
                            case 68:
                                if (!zza((com.google.android.gms.internal.clearcut.zzds<T>) r14, r9, r7)) {
                                    break;
                                }
                                r15.zzb(r9, com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r8 & 1048575), zzad(r7));
                                break;
                        }
                    }
                    while (r1 != null) {
                        r13.zzmy.zza(r15, r1);
                        r1 = r0.hasNext() ? r0.next() : null;
                    }
                }
            }
            r0 = null;
            r1 = null;
            while (r7 >= 0) {
            }
            while (r1 != null) {
            }
        } else if (!r13.zzmq) {
            zzb((com.google.android.gms.internal.clearcut.zzds<T>) r14, r15);
        } else {
            if (r13.zzmo) {
                com.google.android.gms.internal.clearcut.zzby<?> r0 = r13.zzmy.zza(r14);
                if (!r0.isEmpty()) {
                    r0 = r0.iterator();
                    r1 = r0.next();
                    r7 = r13.zzmi.length;
                    for (r8 = 0; r8 < r7; r8 += 4) {
                        int r9 = zzag(r8);
                        int r10 = r13.zzmi[r8];
                        while (r1 != null && r13.zzmy.zza(r1) <= r10) {
                            r13.zzmy.zza(r15, r1);
                            r1 = r0.hasNext() ? r0.next() : null;
                        }
                        switch ((r9 & 267386880) >>> 20) {
                            case 0:
                                if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r14, r8)) {
                                    r11 = com.google.android.gms.internal.clearcut.zzfd.zzn(r14, r9 & 1048575);
                                    r15.zza(r10, r11);
                                    break;
                                } else {
                                    break;
                                }
                            case 1:
                                if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r14, r8)) {
                                    r9 = com.google.android.gms.internal.clearcut.zzfd.zzm(r14, r9 & 1048575);
                                    r15.zza(r10, r9);
                                    break;
                                } else {
                                    break;
                                }
                            case 2:
                                if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r14, r8)) {
                                    r11 = com.google.android.gms.internal.clearcut.zzfd.zzk(r14, r9 & 1048575);
                                    r15.zzi(r10, r11);
                                    break;
                                } else {
                                    break;
                                }
                            case 3:
                                if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r14, r8)) {
                                    r11 = com.google.android.gms.internal.clearcut.zzfd.zzk(r14, r9 & 1048575);
                                    r15.zza(r10, r11);
                                    break;
                                } else {
                                    break;
                                }
                            case 4:
                                if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r14, r8)) {
                                    r9 = com.google.android.gms.internal.clearcut.zzfd.zzj(r14, r9 & 1048575);
                                    r15.zzc(r10, r9);
                                    break;
                                } else {
                                    break;
                                }
                            case 5:
                                if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r14, r8)) {
                                    r11 = com.google.android.gms.internal.clearcut.zzfd.zzk(r14, r9 & 1048575);
                                    r15.zzc(r10, r11);
                                    break;
                                } else {
                                    break;
                                }
                            case 6:
                                if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r14, r8)) {
                                    r9 = com.google.android.gms.internal.clearcut.zzfd.zzj(r14, r9 & 1048575);
                                    r15.zzf(r10, r9);
                                    break;
                                } else {
                                    break;
                                }
                            case 7:
                                if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r14, r8)) {
                                    r9 = com.google.android.gms.internal.clearcut.zzfd.zzl(r14, r9 & 1048575);
                                    r15.zzb(r10, r9);
                                    break;
                                } else {
                                    break;
                                }
                            case 8:
                                if (!zza((com.google.android.gms.internal.clearcut.zzds<T>) r14, r8)) {
                                    break;
                                }
                                zza(r10, com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r9 & 1048575), r15);
                                break;
                            case 9:
                                if (!zza((com.google.android.gms.internal.clearcut.zzds<T>) r14, r8)) {
                                    break;
                                }
                                r15.zza(r10, com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r9 & 1048575), zzad(r8));
                                break;
                            case 10:
                                if (!zza((com.google.android.gms.internal.clearcut.zzds<T>) r14, r8)) {
                                    break;
                                }
                                r15.zza(r10, (com.google.android.gms.internal.clearcut.zzbb) com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r9 & 1048575));
                                break;
                            case 11:
                                if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r14, r8)) {
                                    r9 = com.google.android.gms.internal.clearcut.zzfd.zzj(r14, r9 & 1048575);
                                    r15.zzd(r10, r9);
                                    break;
                                } else {
                                    break;
                                }
                            case 12:
                                if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r14, r8)) {
                                    r9 = com.google.android.gms.internal.clearcut.zzfd.zzj(r14, r9 & 1048575);
                                    r15.zzn(r10, r9);
                                    break;
                                } else {
                                    break;
                                }
                            case 13:
                                if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r14, r8)) {
                                    r9 = com.google.android.gms.internal.clearcut.zzfd.zzj(r14, r9 & 1048575);
                                    r15.zzm(r10, r9);
                                    break;
                                } else {
                                    break;
                                }
                            case 14:
                                if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r14, r8)) {
                                    r11 = com.google.android.gms.internal.clearcut.zzfd.zzk(r14, r9 & 1048575);
                                    r15.zzj(r10, r11);
                                    break;
                                } else {
                                    break;
                                }
                            case 15:
                                if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r14, r8)) {
                                    r9 = com.google.android.gms.internal.clearcut.zzfd.zzj(r14, r9 & 1048575);
                                    r15.zze(r10, r9);
                                    break;
                                } else {
                                    break;
                                }
                            case 16:
                                if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r14, r8)) {
                                    r11 = com.google.android.gms.internal.clearcut.zzfd.zzk(r14, r9 & 1048575);
                                    r15.zzb(r10, r11);
                                    break;
                                } else {
                                    break;
                                }
                            case 17:
                                if (!zza((com.google.android.gms.internal.clearcut.zzds<T>) r14, r8)) {
                                    break;
                                }
                                r15.zzb(r10, com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r9 & 1048575), zzad(r8));
                                break;
                            case 18:
                                com.google.android.gms.internal.clearcut.zzeh.zza(r13.zzmi[r8], (java.util.List<java.lang.Double>) com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r9 & 1048575), r15, false);
                                break;
                            case 19:
                                com.google.android.gms.internal.clearcut.zzeh.zzb(r13.zzmi[r8], (java.util.List<java.lang.Float>) com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r9 & 1048575), r15, false);
                                break;
                            case 20:
                                com.google.android.gms.internal.clearcut.zzeh.zzc(r13.zzmi[r8], (java.util.List) com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r9 & 1048575), r15, false);
                                break;
                            case 21:
                                com.google.android.gms.internal.clearcut.zzeh.zzd(r13.zzmi[r8], (java.util.List) com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r9 & 1048575), r15, false);
                                break;
                            case 22:
                                com.google.android.gms.internal.clearcut.zzeh.zzh(r13.zzmi[r8], (java.util.List) com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r9 & 1048575), r15, false);
                                break;
                            case 23:
                                com.google.android.gms.internal.clearcut.zzeh.zzf(r13.zzmi[r8], (java.util.List) com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r9 & 1048575), r15, false);
                                break;
                            case 24:
                                com.google.android.gms.internal.clearcut.zzeh.zzk(r13.zzmi[r8], (java.util.List) com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r9 & 1048575), r15, false);
                                break;
                            case 25:
                                com.google.android.gms.internal.clearcut.zzeh.zzn(r13.zzmi[r8], (java.util.List) com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r9 & 1048575), r15, false);
                                break;
                            case 26:
                                com.google.android.gms.internal.clearcut.zzeh.zza(r13.zzmi[r8], (java.util.List) com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r9 & 1048575), r15);
                                break;
                            case 27:
                                com.google.android.gms.internal.clearcut.zzeh.zza(r13.zzmi[r8], (java.util.List) com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r9 & 1048575), r15, zzad(r8));
                                break;
                            case 28:
                                com.google.android.gms.internal.clearcut.zzeh.zzb(r13.zzmi[r8], (java.util.List) com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r9 & 1048575), r15);
                                break;
                            case 29:
                                com.google.android.gms.internal.clearcut.zzeh.zzi(r13.zzmi[r8], (java.util.List) com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r9 & 1048575), r15, false);
                                break;
                            case 30:
                                com.google.android.gms.internal.clearcut.zzeh.zzm(r13.zzmi[r8], (java.util.List) com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r9 & 1048575), r15, false);
                                break;
                            case 31:
                                com.google.android.gms.internal.clearcut.zzeh.zzl(r13.zzmi[r8], (java.util.List) com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r9 & 1048575), r15, false);
                                break;
                            case 32:
                                com.google.android.gms.internal.clearcut.zzeh.zzg(r13.zzmi[r8], (java.util.List) com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r9 & 1048575), r15, false);
                                break;
                            case 33:
                                com.google.android.gms.internal.clearcut.zzeh.zzj(r13.zzmi[r8], (java.util.List) com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r9 & 1048575), r15, false);
                                break;
                            case 34:
                                com.google.android.gms.internal.clearcut.zzeh.zze(r13.zzmi[r8], (java.util.List) com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r9 & 1048575), r15, false);
                                break;
                            case 35:
                                com.google.android.gms.internal.clearcut.zzeh.zza(r13.zzmi[r8], (java.util.List<java.lang.Double>) com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r9 & 1048575), r15, true);
                                break;
                            case 36:
                                com.google.android.gms.internal.clearcut.zzeh.zzb(r13.zzmi[r8], (java.util.List<java.lang.Float>) com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r9 & 1048575), r15, true);
                                break;
                            case 37:
                                com.google.android.gms.internal.clearcut.zzeh.zzc(r13.zzmi[r8], (java.util.List) com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r9 & 1048575), r15, true);
                                break;
                            case 38:
                                com.google.android.gms.internal.clearcut.zzeh.zzd(r13.zzmi[r8], (java.util.List) com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r9 & 1048575), r15, true);
                                break;
                            case 39:
                                com.google.android.gms.internal.clearcut.zzeh.zzh(r13.zzmi[r8], (java.util.List) com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r9 & 1048575), r15, true);
                                break;
                            case 40:
                                com.google.android.gms.internal.clearcut.zzeh.zzf(r13.zzmi[r8], (java.util.List) com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r9 & 1048575), r15, true);
                                break;
                            case 41:
                                com.google.android.gms.internal.clearcut.zzeh.zzk(r13.zzmi[r8], (java.util.List) com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r9 & 1048575), r15, true);
                                break;
                            case 42:
                                com.google.android.gms.internal.clearcut.zzeh.zzn(r13.zzmi[r8], (java.util.List) com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r9 & 1048575), r15, true);
                                break;
                            case 43:
                                com.google.android.gms.internal.clearcut.zzeh.zzi(r13.zzmi[r8], (java.util.List) com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r9 & 1048575), r15, true);
                                break;
                            case 44:
                                com.google.android.gms.internal.clearcut.zzeh.zzm(r13.zzmi[r8], (java.util.List) com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r9 & 1048575), r15, true);
                                break;
                            case 45:
                                com.google.android.gms.internal.clearcut.zzeh.zzl(r13.zzmi[r8], (java.util.List) com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r9 & 1048575), r15, true);
                                break;
                            case 46:
                                com.google.android.gms.internal.clearcut.zzeh.zzg(r13.zzmi[r8], (java.util.List) com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r9 & 1048575), r15, true);
                                break;
                            case 47:
                                com.google.android.gms.internal.clearcut.zzeh.zzj(r13.zzmi[r8], (java.util.List) com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r9 & 1048575), r15, true);
                                break;
                            case 48:
                                com.google.android.gms.internal.clearcut.zzeh.zze(r13.zzmi[r8], (java.util.List) com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r9 & 1048575), r15, true);
                                break;
                            case 49:
                                com.google.android.gms.internal.clearcut.zzeh.zzb(r13.zzmi[r8], (java.util.List) com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r9 & 1048575), r15, zzad(r8));
                                break;
                            case 50:
                                zza(r15, r10, com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r9 & 1048575), r8);
                                break;
                            case 51:
                                if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r14, r10, r8)) {
                                    r11 = zze(r14, r9 & 1048575);
                                    r15.zza(r10, r11);
                                    break;
                                } else {
                                    break;
                                }
                            case 52:
                                if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r14, r10, r8)) {
                                    r9 = zzf(r14, r9 & 1048575);
                                    r15.zza(r10, r9);
                                    break;
                                } else {
                                    break;
                                }
                            case 53:
                                if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r14, r10, r8)) {
                                    r11 = zzh(r14, r9 & 1048575);
                                    r15.zzi(r10, r11);
                                    break;
                                } else {
                                    break;
                                }
                            case 54:
                                if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r14, r10, r8)) {
                                    r11 = zzh(r14, r9 & 1048575);
                                    r15.zza(r10, r11);
                                    break;
                                } else {
                                    break;
                                }
                            case 55:
                                if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r14, r10, r8)) {
                                    r9 = zzg(r14, r9 & 1048575);
                                    r15.zzc(r10, r9);
                                    break;
                                } else {
                                    break;
                                }
                            case 56:
                                if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r14, r10, r8)) {
                                    r11 = zzh(r14, r9 & 1048575);
                                    r15.zzc(r10, r11);
                                    break;
                                } else {
                                    break;
                                }
                            case 57:
                                if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r14, r10, r8)) {
                                    r9 = zzg(r14, r9 & 1048575);
                                    r15.zzf(r10, r9);
                                    break;
                                } else {
                                    break;
                                }
                            case 58:
                                if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r14, r10, r8)) {
                                    r9 = zzi(r14, r9 & 1048575);
                                    r15.zzb(r10, r9);
                                    break;
                                } else {
                                    break;
                                }
                            case 59:
                                if (!zza((com.google.android.gms.internal.clearcut.zzds<T>) r14, r10, r8)) {
                                    break;
                                }
                                zza(r10, com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r9 & 1048575), r15);
                                break;
                            case 60:
                                if (!zza((com.google.android.gms.internal.clearcut.zzds<T>) r14, r10, r8)) {
                                    break;
                                }
                                r15.zza(r10, com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r9 & 1048575), zzad(r8));
                                break;
                            case 61:
                                if (!zza((com.google.android.gms.internal.clearcut.zzds<T>) r14, r10, r8)) {
                                    break;
                                }
                                r15.zza(r10, (com.google.android.gms.internal.clearcut.zzbb) com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r9 & 1048575));
                                break;
                            case 62:
                                if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r14, r10, r8)) {
                                    r9 = zzg(r14, r9 & 1048575);
                                    r15.zzd(r10, r9);
                                    break;
                                } else {
                                    break;
                                }
                            case 63:
                                if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r14, r10, r8)) {
                                    r9 = zzg(r14, r9 & 1048575);
                                    r15.zzn(r10, r9);
                                    break;
                                } else {
                                    break;
                                }
                            case 64:
                                if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r14, r10, r8)) {
                                    r9 = zzg(r14, r9 & 1048575);
                                    r15.zzm(r10, r9);
                                    break;
                                } else {
                                    break;
                                }
                            case 65:
                                if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r14, r10, r8)) {
                                    r11 = zzh(r14, r9 & 1048575);
                                    r15.zzj(r10, r11);
                                    break;
                                } else {
                                    break;
                                }
                            case 66:
                                if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r14, r10, r8)) {
                                    r9 = zzg(r14, r9 & 1048575);
                                    r15.zze(r10, r9);
                                    break;
                                } else {
                                    break;
                                }
                            case 67:
                                if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r14, r10, r8)) {
                                    r11 = zzh(r14, r9 & 1048575);
                                    r15.zzb(r10, r11);
                                    break;
                                } else {
                                    break;
                                }
                            case 68:
                                if (!zza((com.google.android.gms.internal.clearcut.zzds<T>) r14, r10, r8)) {
                                    break;
                                }
                                r15.zzb(r10, com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r9 & 1048575), zzad(r8));
                                break;
                        }
                    }
                    while (r1 != null) {
                        r13.zzmy.zza(r15, r1);
                        r1 = r0.hasNext() ? r0.next() : null;
                    }
                    zza(r13.zzmx, r14, r15);
                }
            }
            r0 = null;
            r1 = null;
            r7 = r13.zzmi.length;
            while (r8 < r7) {
            }
            while (r1 != null) {
            }
            zza(r13.zzmx, r14, r15);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:73:0x0164, code lost:
        if (r0 == r15) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x0188, code lost:
        if (r0 == r15) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x01a1, code lost:
        if (r0 == r15) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x01a3, code lost:
        r2 = r0;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v25, types: [int] */
    @Override // com.google.android.gms.internal.clearcut.zzef
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void zza(T r23, byte[] r24, int r25, int r26, com.google.android.gms.internal.clearcut.zzay r27) throws java.io.IOException {
        byte r16;
        int r10;
        sun.misc.Unsafe r21;
        int r15;
        int r6;
        long r4;
        java.lang.Object r1;
        int r1;
        com.google.android.gms.internal.clearcut.zzds<T> r15 = r22;
        T r14 = r23;
        byte[] r12 = r24;
        int r13 = r26;
        com.google.android.gms.internal.clearcut.zzay r11 = r27;
        if (!r15.zzmq) {
            zza((com.google.android.gms.internal.clearcut.zzds<T>) r23, r24, r25, r26, 0, r27);
            return;
        }
        sun.misc.Unsafe r9 = com.google.android.gms.internal.clearcut.zzds.zzmh;
        int r0 = r25;
        while (r0 < r13) {
            int r1 = r0 + 1;
            byte r0 = r12[r0];
            if (r0 < 0) {
                r10 = com.google.android.gms.internal.clearcut.zzax.zza(r0, r12, r1, r11);
                r16 = r11.zzfd;
            } else {
                r16 = r0;
                r10 = r1;
            }
            int r6 = r16 >>> 3;
            int r7 = r16 & 7;
            int r8 = r15.zzai(r6);
            if (r8 >= 0) {
                int r5 = r15.zzmi[r8 + 1];
                int r4 = (267386880 & r5) >>> 20;
                long r2 = 1048575 & r5;
                if (r4 <= 17) {
                    switch (r4) {
                        case 0:
                            if (r7 != 1) {
                                break;
                            } else {
                                com.google.android.gms.internal.clearcut.zzfd.zza(r14, r2, com.google.android.gms.internal.clearcut.zzax.zze(r12, r10));
                                r0 = r10 + 8;
                                break;
                            }
                        case 1:
                            if (r7 != 5) {
                                break;
                            } else {
                                com.google.android.gms.internal.clearcut.zzfd.zza((java.lang.Object) r14, r2, com.google.android.gms.internal.clearcut.zzax.zzf(r12, r10));
                                r0 = r10 + 4;
                                break;
                            }
                        case 2:
                        case 3:
                            if (r7 != 0) {
                                break;
                            } else {
                                r6 = com.google.android.gms.internal.clearcut.zzax.zzb(r12, r10, r11);
                                r4 = r11.zzfe;
                                r9.putLong(r23, r2, r4);
                                r0 = r6;
                                break;
                            }
                        case 4:
                        case 11:
                            if (r7 != 0) {
                                break;
                            } else {
                                r0 = com.google.android.gms.internal.clearcut.zzax.zza(r12, r10, r11);
                                r1 = r11.zzfd;
                                r9.putInt(r14, r2, r1);
                                break;
                            }
                        case 5:
                        case 14:
                            if (r7 != 1) {
                                break;
                            } else {
                                r9.putLong(r23, r2, com.google.android.gms.internal.clearcut.zzax.zzd(r12, r10));
                                r0 = r10 + 8;
                                break;
                            }
                        case 6:
                        case 13:
                            if (r7 != 5) {
                                break;
                            } else {
                                r9.putInt(r14, r2, com.google.android.gms.internal.clearcut.zzax.zzc(r12, r10));
                                r0 = r10 + 4;
                                break;
                            }
                        case 7:
                            if (r7 != 0) {
                                break;
                            } else {
                                r0 = com.google.android.gms.internal.clearcut.zzax.zzb(r12, r10, r11);
                                com.google.android.gms.internal.clearcut.zzfd.zza(r14, r2, r11.zzfe != 0);
                                break;
                            }
                        case 8:
                            if (r7 != 2) {
                                break;
                            } else {
                                r0 = (536870912 & r5) == 0 ? com.google.android.gms.internal.clearcut.zzax.zzc(r12, r10, r11) : com.google.android.gms.internal.clearcut.zzax.zzd(r12, r10, r11);
                                r1 = r11.zzff;
                                r9.putObject(r14, r2, r1);
                                break;
                            }
                        case 9:
                            if (r7 != 2) {
                                break;
                            } else {
                                r0 = zza(r15.zzad(r8), r12, r10, r13, r11);
                                java.lang.Object r1 = r9.getObject(r14, r2);
                                if (r1 != null) {
                                    r1 = com.google.android.gms.internal.clearcut.zzci.zza(r1, r11.zzff);
                                    r9.putObject(r14, r2, r1);
                                    break;
                                }
                                r1 = r11.zzff;
                                r9.putObject(r14, r2, r1);
                            }
                        case 10:
                            if (r7 != 2) {
                                break;
                            } else {
                                r0 = com.google.android.gms.internal.clearcut.zzax.zze(r12, r10, r11);
                                r1 = r11.zzff;
                                r9.putObject(r14, r2, r1);
                                break;
                            }
                        case 12:
                            if (r7 != 0) {
                                break;
                            } else {
                                r0 = com.google.android.gms.internal.clearcut.zzax.zza(r12, r10, r11);
                                r1 = r11.zzfd;
                                r9.putInt(r14, r2, r1);
                                break;
                            }
                        case 15:
                            if (r7 != 0) {
                                break;
                            } else {
                                r0 = com.google.android.gms.internal.clearcut.zzax.zza(r12, r10, r11);
                                r1 = com.google.android.gms.internal.clearcut.zzbk.zzm(r11.zzfd);
                                r9.putInt(r14, r2, r1);
                                break;
                            }
                        case 16:
                            if (r7 != 0) {
                                break;
                            } else {
                                r6 = com.google.android.gms.internal.clearcut.zzax.zzb(r12, r10, r11);
                                r4 = com.google.android.gms.internal.clearcut.zzbk.zza(r11.zzfe);
                                r9.putLong(r23, r2, r4);
                                r0 = r6;
                                break;
                            }
                    }
                } else if (r4 != 27) {
                    if (r4 <= 49) {
                        r21 = r9;
                        int r15 = r10;
                        r0 = zza((com.google.android.gms.internal.clearcut.zzds<T>) r23, r24, r10, r26, r16, r6, r7, r8, r5, r4, r2, r27);
                    } else {
                        r21 = r9;
                        r15 = r10;
                        if (r4 != 50) {
                            r0 = zza((com.google.android.gms.internal.clearcut.zzds<T>) r23, r24, r15, r26, r16, r6, r7, r5, r4, r2, r8, r27);
                        } else if (r7 == 2) {
                            r0 = zza(r23, r24, r15, r26, r8, r6, r2, r27);
                        }
                    }
                    r15 = r22;
                    r14 = r23;
                    r12 = r24;
                    r13 = r26;
                    r11 = r27;
                    r9 = r21;
                } else if (r7 == 2) {
                    com.google.android.gms.internal.clearcut.zzcn r0 = (com.google.android.gms.internal.clearcut.zzcn) r9.getObject(r14, r2);
                    if (!r0.zzu()) {
                        int r1 = r0.size();
                        r0 = r0.zzi(r1 == 0 ? 10 : r1 << 1);
                        r9.putObject(r14, r2, r0);
                    }
                    r0 = zza(r15.zzad(r8), r16, r24, r10, r26, r0, r27);
                }
                int r2 = r15;
                r0 = zza(r16, r24, r2, r26, r23, r27);
                r15 = r22;
                r14 = r23;
                r12 = r24;
                r13 = r26;
                r11 = r27;
                r9 = r21;
            }
            r21 = r9;
            r15 = r10;
            int r2 = r15;
            r0 = zza(r16, r24, r2, r26, r23, r27);
            r15 = r22;
            r14 = r23;
            r12 = r24;
            r13 = r26;
            r11 = r27;
            r9 = r21;
        }
        if (r0 != r13) {
            throw com.google.android.gms.internal.clearcut.zzco.zzbo();
        }
    }

    @Override // com.google.android.gms.internal.clearcut.zzef
    public final void zzc(T t) {
        int[] iArr = this.zzmt;
        if (iArr != null) {
            for (int i : iArr) {
                long zzag = zzag(i) & 1048575;
                Object zzo = zzfd.zzo(t, zzag);
                if (zzo != null) {
                    zzfd.zza(t, zzag, this.zzmz.zzj(zzo));
                }
            }
        }
        int[] iArr2 = this.zzmu;
        if (iArr2 != null) {
            for (int i2 : iArr2) {
                this.zzmw.zza(t, i2);
            }
        }
        this.zzmx.zzc(t);
        if (this.zzmo) {
            this.zzmy.zzc(t);
        }
    }

    @Override // com.google.android.gms.internal.clearcut.zzef
    public final void zzc(T t, T t2) {
        Objects.requireNonNull(t2);
        for (int i = 0; i < this.zzmi.length; i += 4) {
            int zzag = zzag(i);
            long j = 1048575 & zzag;
            int i2 = this.zzmi[i];
            switch ((zzag & 267386880) >>> 20) {
                case 0:
                    if (zza((zzds<T>) t2, i)) {
                        zzfd.zza(t, j, zzfd.zzn(t2, j));
                        zzb((zzds<T>) t, i);
                        break;
                    } else {
                        break;
                    }
                case 1:
                    if (zza((zzds<T>) t2, i)) {
                        zzfd.zza((Object) t, j, zzfd.zzm(t2, j));
                        zzb((zzds<T>) t, i);
                        break;
                    } else {
                        break;
                    }
                case 2:
                    if (!zza((zzds<T>) t2, i)) {
                        break;
                    }
                    zzfd.zza((Object) t, j, zzfd.zzk(t2, j));
                    zzb((zzds<T>) t, i);
                    break;
                case 3:
                    if (!zza((zzds<T>) t2, i)) {
                        break;
                    }
                    zzfd.zza((Object) t, j, zzfd.zzk(t2, j));
                    zzb((zzds<T>) t, i);
                    break;
                case 4:
                    if (!zza((zzds<T>) t2, i)) {
                        break;
                    }
                    zzfd.zza((Object) t, j, zzfd.zzj(t2, j));
                    zzb((zzds<T>) t, i);
                    break;
                case 5:
                    if (!zza((zzds<T>) t2, i)) {
                        break;
                    }
                    zzfd.zza((Object) t, j, zzfd.zzk(t2, j));
                    zzb((zzds<T>) t, i);
                    break;
                case 6:
                    if (!zza((zzds<T>) t2, i)) {
                        break;
                    }
                    zzfd.zza((Object) t, j, zzfd.zzj(t2, j));
                    zzb((zzds<T>) t, i);
                    break;
                case 7:
                    if (zza((zzds<T>) t2, i)) {
                        zzfd.zza(t, j, zzfd.zzl(t2, j));
                        zzb((zzds<T>) t, i);
                        break;
                    } else {
                        break;
                    }
                case 8:
                    if (!zza((zzds<T>) t2, i)) {
                        break;
                    }
                    zzfd.zza(t, j, zzfd.zzo(t2, j));
                    zzb((zzds<T>) t, i);
                    break;
                case 9:
                case 17:
                    zza(t, t2, i);
                    break;
                case 10:
                    if (!zza((zzds<T>) t2, i)) {
                        break;
                    }
                    zzfd.zza(t, j, zzfd.zzo(t2, j));
                    zzb((zzds<T>) t, i);
                    break;
                case 11:
                    if (!zza((zzds<T>) t2, i)) {
                        break;
                    }
                    zzfd.zza((Object) t, j, zzfd.zzj(t2, j));
                    zzb((zzds<T>) t, i);
                    break;
                case 12:
                    if (!zza((zzds<T>) t2, i)) {
                        break;
                    }
                    zzfd.zza((Object) t, j, zzfd.zzj(t2, j));
                    zzb((zzds<T>) t, i);
                    break;
                case 13:
                    if (!zza((zzds<T>) t2, i)) {
                        break;
                    }
                    zzfd.zza((Object) t, j, zzfd.zzj(t2, j));
                    zzb((zzds<T>) t, i);
                    break;
                case 14:
                    if (!zza((zzds<T>) t2, i)) {
                        break;
                    }
                    zzfd.zza((Object) t, j, zzfd.zzk(t2, j));
                    zzb((zzds<T>) t, i);
                    break;
                case 15:
                    if (!zza((zzds<T>) t2, i)) {
                        break;
                    }
                    zzfd.zza((Object) t, j, zzfd.zzj(t2, j));
                    zzb((zzds<T>) t, i);
                    break;
                case 16:
                    if (!zza((zzds<T>) t2, i)) {
                        break;
                    }
                    zzfd.zza((Object) t, j, zzfd.zzk(t2, j));
                    zzb((zzds<T>) t, i);
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
                    this.zzmw.zza(t, t2, j);
                    break;
                case 50:
                    zzeh.zza(this.zzmz, t, t2, j);
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
                    if (!zza((zzds<T>) t2, i2, i)) {
                        break;
                    }
                    zzfd.zza(t, j, zzfd.zzo(t2, j));
                    zzb((zzds<T>) t, i2, i);
                    break;
                case 60:
                case 68:
                    zzb(t, t2, i);
                    break;
                case 61:
                case 62:
                case 63:
                case 64:
                case 65:
                case 66:
                case 67:
                    if (!zza((zzds<T>) t2, i2, i)) {
                        break;
                    }
                    zzfd.zza(t, j, zzfd.zzo(t2, j));
                    zzb((zzds<T>) t, i2, i);
                    break;
            }
        }
        if (this.zzmq) {
            return;
        }
        zzeh.zza(this.zzmx, t, t2);
        if (this.zzmo) {
            zzeh.zza(this.zzmy, t, t2);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:101:0x0181, code lost:
        if (r19.zzmr != false) goto L104;
     */
    /* JADX WARN: Code restructure failed: missing block: B:106:0x0193, code lost:
        if (r19.zzmr != false) goto L104;
     */
    /* JADX WARN: Code restructure failed: missing block: B:111:0x01a5, code lost:
        if (r19.zzmr != false) goto L104;
     */
    /* JADX WARN: Code restructure failed: missing block: B:116:0x01b6, code lost:
        if (r19.zzmr != false) goto L104;
     */
    /* JADX WARN: Code restructure failed: missing block: B:121:0x01c7, code lost:
        if (r19.zzmr != false) goto L104;
     */
    /* JADX WARN: Code restructure failed: missing block: B:126:0x01d8, code lost:
        if (r19.zzmr != false) goto L104;
     */
    /* JADX WARN: Code restructure failed: missing block: B:131:0x01e9, code lost:
        if (r19.zzmr != false) goto L104;
     */
    /* JADX WARN: Code restructure failed: missing block: B:136:0x01fa, code lost:
        if (r19.zzmr != false) goto L104;
     */
    /* JADX WARN: Code restructure failed: missing block: B:141:0x020b, code lost:
        if (r19.zzmr != false) goto L104;
     */
    /* JADX WARN: Code restructure failed: missing block: B:142:0x020d, code lost:
        r2.putInt(r20, r14, r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:143:0x0211, code lost:
        r3 = (com.google.android.gms.internal.clearcut.zzbn.zzr(r3) + com.google.android.gms.internal.clearcut.zzbn.zzt(r5)) + r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:193:0x0331, code lost:
        if ((r5 instanceof com.google.android.gms.internal.clearcut.zzbb) != false) goto L50;
     */
    /* JADX WARN: Code restructure failed: missing block: B:195:0x0334, code lost:
        r3 = com.google.android.gms.internal.clearcut.zzbn.zzb(r3, (java.lang.String) r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:248:0x0414, code lost:
        if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r20, r15, r5) != false) goto L266;
     */
    /* JADX WARN: Code restructure failed: missing block: B:257:0x0434, code lost:
        if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r20, r15, r5) != false) goto L278;
     */
    /* JADX WARN: Code restructure failed: missing block: B:260:0x043c, code lost:
        if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r20, r15, r5) != false) goto L281;
     */
    /* JADX WARN: Code restructure failed: missing block: B:269:0x045c, code lost:
        if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r20, r15, r5) != false) goto L293;
     */
    /* JADX WARN: Code restructure failed: missing block: B:272:0x0464, code lost:
        if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r20, r15, r5) != false) goto L297;
     */
    /* JADX WARN: Code restructure failed: missing block: B:277:0x0474, code lost:
        if ((r4 instanceof com.google.android.gms.internal.clearcut.zzbb) != false) goto L294;
     */
    /* JADX WARN: Code restructure failed: missing block: B:280:0x047c, code lost:
        if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r20, r15, r5) != false) goto L305;
     */
    /* JADX WARN: Code restructure failed: missing block: B:308:0x0514, code lost:
        if (r19.zzmr != false) goto L334;
     */
    /* JADX WARN: Code restructure failed: missing block: B:313:0x0526, code lost:
        if (r19.zzmr != false) goto L334;
     */
    /* JADX WARN: Code restructure failed: missing block: B:318:0x0538, code lost:
        if (r19.zzmr != false) goto L334;
     */
    /* JADX WARN: Code restructure failed: missing block: B:323:0x054a, code lost:
        if (r19.zzmr != false) goto L334;
     */
    /* JADX WARN: Code restructure failed: missing block: B:328:0x055c, code lost:
        if (r19.zzmr != false) goto L334;
     */
    /* JADX WARN: Code restructure failed: missing block: B:333:0x056e, code lost:
        if (r19.zzmr != false) goto L334;
     */
    /* JADX WARN: Code restructure failed: missing block: B:338:0x0580, code lost:
        if (r19.zzmr != false) goto L334;
     */
    /* JADX WARN: Code restructure failed: missing block: B:343:0x0592, code lost:
        if (r19.zzmr != false) goto L334;
     */
    /* JADX WARN: Code restructure failed: missing block: B:348:0x05a3, code lost:
        if (r19.zzmr != false) goto L334;
     */
    /* JADX WARN: Code restructure failed: missing block: B:353:0x05b4, code lost:
        if (r19.zzmr != false) goto L334;
     */
    /* JADX WARN: Code restructure failed: missing block: B:358:0x05c5, code lost:
        if (r19.zzmr != false) goto L334;
     */
    /* JADX WARN: Code restructure failed: missing block: B:363:0x05d6, code lost:
        if (r19.zzmr != false) goto L334;
     */
    /* JADX WARN: Code restructure failed: missing block: B:368:0x05e7, code lost:
        if (r19.zzmr != false) goto L334;
     */
    /* JADX WARN: Code restructure failed: missing block: B:373:0x05f8, code lost:
        if (r19.zzmr != false) goto L334;
     */
    /* JADX WARN: Code restructure failed: missing block: B:374:0x05fa, code lost:
        r2.putInt(r20, r9, r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:375:0x05fe, code lost:
        r9 = (com.google.android.gms.internal.clearcut.zzbn.zzr(r15) + com.google.android.gms.internal.clearcut.zzbn.zzt(r4)) + r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:395:0x06c4, code lost:
        if ((r12 & r18) != 0) goto L266;
     */
    /* JADX WARN: Code restructure failed: missing block: B:396:0x06c6, code lost:
        r4 = com.google.android.gms.internal.clearcut.zzbn.zzc(r15, (com.google.android.gms.internal.clearcut.zzdo) r2.getObject(r20, r10), zzad(r5));
     */
    /* JADX WARN: Code restructure failed: missing block: B:406:0x06f1, code lost:
        if ((r12 & r18) != 0) goto L278;
     */
    /* JADX WARN: Code restructure failed: missing block: B:407:0x06f3, code lost:
        r4 = com.google.android.gms.internal.clearcut.zzbn.zzh(r15, 0L);
     */
    /* JADX WARN: Code restructure failed: missing block: B:409:0x06fc, code lost:
        if ((r12 & r18) != 0) goto L281;
     */
    /* JADX WARN: Code restructure failed: missing block: B:410:0x06fe, code lost:
        r9 = com.google.android.gms.internal.clearcut.zzbn.zzk(r15, 0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:421:0x0721, code lost:
        if ((r12 & r18) != 0) goto L293;
     */
    /* JADX WARN: Code restructure failed: missing block: B:422:0x0723, code lost:
        r4 = r2.getObject(r20, r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:423:0x0727, code lost:
        r4 = com.google.android.gms.internal.clearcut.zzbn.zzc(r15, (com.google.android.gms.internal.clearcut.zzbb) r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:425:0x0730, code lost:
        if ((r12 & r18) != 0) goto L297;
     */
    /* JADX WARN: Code restructure failed: missing block: B:426:0x0732, code lost:
        r4 = com.google.android.gms.internal.clearcut.zzeh.zzc(r15, r2.getObject(r20, r10), zzad(r5));
     */
    /* JADX WARN: Code restructure failed: missing block: B:430:0x074a, code lost:
        if ((r4 instanceof com.google.android.gms.internal.clearcut.zzbb) != false) goto L294;
     */
    /* JADX WARN: Code restructure failed: missing block: B:432:0x074d, code lost:
        r4 = com.google.android.gms.internal.clearcut.zzbn.zzb(r15, (java.lang.String) r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:434:0x0757, code lost:
        if ((r12 & r18) != 0) goto L305;
     */
    /* JADX WARN: Code restructure failed: missing block: B:435:0x0759, code lost:
        r4 = com.google.android.gms.internal.clearcut.zzbn.zzc(r15, true);
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00ab, code lost:
        if ((r5 instanceof com.google.android.gms.internal.clearcut.zzbb) != false) goto L50;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x0127, code lost:
        if (r19.zzmr != false) goto L104;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x0139, code lost:
        if (r19.zzmr != false) goto L104;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x014b, code lost:
        if (r19.zzmr != false) goto L104;
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x015d, code lost:
        if (r19.zzmr != false) goto L104;
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x016f, code lost:
        if (r19.zzmr != false) goto L104;
     */
    @Override // com.google.android.gms.internal.clearcut.zzef
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int zzm(T r20) {
        int r9;
        int r18;
        boolean r4;
        boolean r9;
        long r13;
        int r9;
        java.lang.Object r4;
        int r4;
        int r4;
        int r4;
        long r9;
        int r4;
        boolean r9;
        int r4;
        int r4;
        int r9;
        long r5;
        long r5;
        int r5;
        java.lang.Object r5;
        int r5;
        int r5;
        int r5;
        long r5;
        int r3;
        int r5;
        int r3 = 267386880;
        if (r19.zzmq) {
            sun.misc.Unsafe r2 = com.google.android.gms.internal.clearcut.zzds.zzmh;
            int r12 = 0;
            int r13 = 0;
            while (r12 < r19.zzmi.length) {
                int r14 = zzag(r12);
                int r15 = (r14 & r3) >>> 20;
                int r3 = r19.zzmi[r12];
                long r5 = r14 & 1048575;
                int r14 = (r15 < com.google.android.gms.internal.clearcut.zzcb.DOUBLE_LIST_PACKED.id() || r15 > com.google.android.gms.internal.clearcut.zzcb.SINT64_LIST_PACKED.id()) ? 0 : r19.zzmi[r12 + 2] & 1048575;
                switch (r15) {
                    case 0:
                        if (!zza((com.google.android.gms.internal.clearcut.zzds<T>) r20, r12)) {
                            continue;
                            r12 += 4;
                            r3 = 267386880;
                        }
                        r3 = com.google.android.gms.internal.clearcut.zzbn.zzb(r3, (double) com.google.firebase.remoteconfig.FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
                        break;
                    case 1:
                        if (!zza((com.google.android.gms.internal.clearcut.zzds<T>) r20, r12)) {
                            continue;
                            r12 += 4;
                            r3 = 267386880;
                        }
                        r3 = com.google.android.gms.internal.clearcut.zzbn.zzb(r3, 0.0f);
                        break;
                    case 2:
                        if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r20, r12)) {
                            r5 = com.google.android.gms.internal.clearcut.zzfd.zzk(r20, r5);
                            r3 = com.google.android.gms.internal.clearcut.zzbn.zzd(r3, r5);
                            break;
                        } else {
                            continue;
                            r12 += 4;
                            r3 = 267386880;
                        }
                    case 3:
                        if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r20, r12)) {
                            r5 = com.google.android.gms.internal.clearcut.zzfd.zzk(r20, r5);
                            r3 = com.google.android.gms.internal.clearcut.zzbn.zze(r3, r5);
                            break;
                        } else {
                            continue;
                            r12 += 4;
                            r3 = 267386880;
                        }
                    case 4:
                        if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r20, r12)) {
                            r5 = com.google.android.gms.internal.clearcut.zzfd.zzj(r20, r5);
                            r3 = com.google.android.gms.internal.clearcut.zzbn.zzg(r3, r5);
                            break;
                        } else {
                            continue;
                            r12 += 4;
                            r3 = 267386880;
                        }
                    case 5:
                        if (!zza((com.google.android.gms.internal.clearcut.zzds<T>) r20, r12)) {
                            continue;
                            r12 += 4;
                            r3 = 267386880;
                        }
                        r3 = com.google.android.gms.internal.clearcut.zzbn.zzg(r3, 0L);
                        break;
                    case 6:
                        if (!zza((com.google.android.gms.internal.clearcut.zzds<T>) r20, r12)) {
                            continue;
                            r12 += 4;
                            r3 = 267386880;
                        }
                        r3 = com.google.android.gms.internal.clearcut.zzbn.zzj(r3, 0);
                        break;
                    case 7:
                        if (!zza((com.google.android.gms.internal.clearcut.zzds<T>) r20, r12)) {
                            continue;
                            r12 += 4;
                            r3 = 267386880;
                        }
                        r3 = com.google.android.gms.internal.clearcut.zzbn.zzc(r3, true);
                        break;
                    case 8:
                        if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r20, r12)) {
                            r5 = com.google.android.gms.internal.clearcut.zzfd.zzo(r20, r5);
                            break;
                        } else {
                            continue;
                            r12 += 4;
                            r3 = 267386880;
                        }
                    case 9:
                        if (!zza((com.google.android.gms.internal.clearcut.zzds<T>) r20, r12)) {
                            continue;
                            r12 += 4;
                            r3 = 267386880;
                        }
                        r3 = com.google.android.gms.internal.clearcut.zzeh.zzc(r3, com.google.android.gms.internal.clearcut.zzfd.zzo(r20, r5), zzad(r12));
                        break;
                    case 10:
                        if (!zza((com.google.android.gms.internal.clearcut.zzds<T>) r20, r12)) {
                            continue;
                            r12 += 4;
                            r3 = 267386880;
                        }
                        r5 = com.google.android.gms.internal.clearcut.zzfd.zzo(r20, r5);
                        r3 = com.google.android.gms.internal.clearcut.zzbn.zzc(r3, (com.google.android.gms.internal.clearcut.zzbb) r5);
                        break;
                    case 11:
                        if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r20, r12)) {
                            r5 = com.google.android.gms.internal.clearcut.zzfd.zzj(r20, r5);
                            r3 = com.google.android.gms.internal.clearcut.zzbn.zzh(r3, r5);
                            break;
                        } else {
                            continue;
                            r12 += 4;
                            r3 = 267386880;
                        }
                    case 12:
                        if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r20, r12)) {
                            r5 = com.google.android.gms.internal.clearcut.zzfd.zzj(r20, r5);
                            r3 = com.google.android.gms.internal.clearcut.zzbn.zzl(r3, r5);
                            break;
                        } else {
                            continue;
                            r12 += 4;
                            r3 = 267386880;
                        }
                    case 13:
                        if (!zza((com.google.android.gms.internal.clearcut.zzds<T>) r20, r12)) {
                            continue;
                            r12 += 4;
                            r3 = 267386880;
                        }
                        r3 = com.google.android.gms.internal.clearcut.zzbn.zzk(r3, 0);
                        break;
                    case 14:
                        if (!zza((com.google.android.gms.internal.clearcut.zzds<T>) r20, r12)) {
                            continue;
                            r12 += 4;
                            r3 = 267386880;
                        }
                        r3 = com.google.android.gms.internal.clearcut.zzbn.zzh(r3, 0L);
                        break;
                    case 15:
                        if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r20, r12)) {
                            r5 = com.google.android.gms.internal.clearcut.zzfd.zzj(r20, r5);
                            r3 = com.google.android.gms.internal.clearcut.zzbn.zzi(r3, r5);
                            break;
                        } else {
                            continue;
                            r12 += 4;
                            r3 = 267386880;
                        }
                    case 16:
                        if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r20, r12)) {
                            r5 = com.google.android.gms.internal.clearcut.zzfd.zzk(r20, r5);
                            r3 = com.google.android.gms.internal.clearcut.zzbn.zzf(r3, r5);
                            break;
                        } else {
                            continue;
                            r12 += 4;
                            r3 = 267386880;
                        }
                    case 17:
                        if (!zza((com.google.android.gms.internal.clearcut.zzds<T>) r20, r12)) {
                            continue;
                            r12 += 4;
                            r3 = 267386880;
                        }
                        r3 = com.google.android.gms.internal.clearcut.zzbn.zzc(r3, (com.google.android.gms.internal.clearcut.zzdo) com.google.android.gms.internal.clearcut.zzfd.zzo(r20, r5), zzad(r12));
                        break;
                    case 18:
                    case 23:
                    case 32:
                        r3 = com.google.android.gms.internal.clearcut.zzeh.zzw(r3, zzd(r20, r5), false);
                        break;
                    case 19:
                    case 24:
                    case 31:
                        r3 = com.google.android.gms.internal.clearcut.zzeh.zzv(r3, zzd(r20, r5), false);
                        break;
                    case 20:
                        r3 = com.google.android.gms.internal.clearcut.zzeh.zzo(r3, zzd(r20, r5), false);
                        break;
                    case 21:
                        r3 = com.google.android.gms.internal.clearcut.zzeh.zzp(r3, zzd(r20, r5), false);
                        break;
                    case 22:
                        r3 = com.google.android.gms.internal.clearcut.zzeh.zzs(r3, zzd(r20, r5), false);
                        break;
                    case 25:
                        r3 = com.google.android.gms.internal.clearcut.zzeh.zzx(r3, zzd(r20, r5), false);
                        break;
                    case 26:
                        r3 = com.google.android.gms.internal.clearcut.zzeh.zzc(r3, zzd(r20, r5));
                        break;
                    case 27:
                        r3 = com.google.android.gms.internal.clearcut.zzeh.zzc(r3, (java.util.List<?>) zzd(r20, r5), zzad(r12));
                        break;
                    case 28:
                        r3 = com.google.android.gms.internal.clearcut.zzeh.zzd(r3, zzd(r20, r5));
                        break;
                    case 29:
                        r3 = com.google.android.gms.internal.clearcut.zzeh.zzt(r3, zzd(r20, r5), false);
                        break;
                    case 30:
                        r3 = com.google.android.gms.internal.clearcut.zzeh.zzr(r3, zzd(r20, r5), false);
                        break;
                    case 33:
                        r3 = com.google.android.gms.internal.clearcut.zzeh.zzu(r3, zzd(r20, r5), false);
                        break;
                    case 34:
                        r3 = com.google.android.gms.internal.clearcut.zzeh.zzq(r3, zzd(r20, r5), false);
                        break;
                    case 35:
                        r5 = com.google.android.gms.internal.clearcut.zzeh.zzi((java.util.List) r2.getObject(r20, r5));
                        if (r5 > 0) {
                            break;
                        } else {
                            continue;
                            r12 += 4;
                            r3 = 267386880;
                        }
                    case 36:
                        r5 = com.google.android.gms.internal.clearcut.zzeh.zzh((java.util.List) r2.getObject(r20, r5));
                        if (r5 > 0) {
                            break;
                        } else {
                            continue;
                            r12 += 4;
                            r3 = 267386880;
                        }
                    case 37:
                        r5 = com.google.android.gms.internal.clearcut.zzeh.zza((java.util.List) r2.getObject(r20, r5));
                        if (r5 > 0) {
                            break;
                        } else {
                            continue;
                            r12 += 4;
                            r3 = 267386880;
                        }
                    case 38:
                        r5 = com.google.android.gms.internal.clearcut.zzeh.zzb((java.util.List) r2.getObject(r20, r5));
                        if (r5 > 0) {
                            break;
                        } else {
                            continue;
                            r12 += 4;
                            r3 = 267386880;
                        }
                    case 39:
                        r5 = com.google.android.gms.internal.clearcut.zzeh.zze((java.util.List) r2.getObject(r20, r5));
                        if (r5 > 0) {
                            break;
                        } else {
                            continue;
                            r12 += 4;
                            r3 = 267386880;
                        }
                    case 40:
                        r5 = com.google.android.gms.internal.clearcut.zzeh.zzi((java.util.List) r2.getObject(r20, r5));
                        if (r5 > 0) {
                            break;
                        } else {
                            continue;
                            r12 += 4;
                            r3 = 267386880;
                        }
                    case 41:
                        r5 = com.google.android.gms.internal.clearcut.zzeh.zzh((java.util.List) r2.getObject(r20, r5));
                        if (r5 > 0) {
                            break;
                        } else {
                            continue;
                            r12 += 4;
                            r3 = 267386880;
                        }
                    case 42:
                        r5 = com.google.android.gms.internal.clearcut.zzeh.zzj((java.util.List) r2.getObject(r20, r5));
                        if (r5 > 0) {
                            break;
                        } else {
                            continue;
                            r12 += 4;
                            r3 = 267386880;
                        }
                    case 43:
                        r5 = com.google.android.gms.internal.clearcut.zzeh.zzf((java.util.List) r2.getObject(r20, r5));
                        if (r5 > 0) {
                            break;
                        } else {
                            continue;
                            r12 += 4;
                            r3 = 267386880;
                        }
                    case 44:
                        r5 = com.google.android.gms.internal.clearcut.zzeh.zzd((java.util.List) r2.getObject(r20, r5));
                        if (r5 > 0) {
                            break;
                        } else {
                            continue;
                            r12 += 4;
                            r3 = 267386880;
                        }
                    case 45:
                        r5 = com.google.android.gms.internal.clearcut.zzeh.zzh((java.util.List) r2.getObject(r20, r5));
                        if (r5 > 0) {
                            break;
                        } else {
                            continue;
                            r12 += 4;
                            r3 = 267386880;
                        }
                    case 46:
                        r5 = com.google.android.gms.internal.clearcut.zzeh.zzi((java.util.List) r2.getObject(r20, r5));
                        if (r5 > 0) {
                            break;
                        } else {
                            continue;
                            r12 += 4;
                            r3 = 267386880;
                        }
                    case 47:
                        r5 = com.google.android.gms.internal.clearcut.zzeh.zzg((java.util.List) r2.getObject(r20, r5));
                        if (r5 > 0) {
                            break;
                        } else {
                            continue;
                            r12 += 4;
                            r3 = 267386880;
                        }
                    case 48:
                        r5 = com.google.android.gms.internal.clearcut.zzeh.zzc((java.util.List) r2.getObject(r20, r5));
                        if (r5 > 0) {
                            break;
                        } else {
                            continue;
                            r12 += 4;
                            r3 = 267386880;
                        }
                    case 49:
                        r3 = com.google.android.gms.internal.clearcut.zzeh.zzd(r3, zzd(r20, r5), zzad(r12));
                        break;
                    case 50:
                        r3 = r19.zzmz.zzb(r3, com.google.android.gms.internal.clearcut.zzfd.zzo(r20, r5), zzae(r12));
                        break;
                    case 51:
                        if (!zza((com.google.android.gms.internal.clearcut.zzds<T>) r20, r3, r12)) {
                            continue;
                            r12 += 4;
                            r3 = 267386880;
                        }
                        r3 = com.google.android.gms.internal.clearcut.zzbn.zzb(r3, (double) com.google.firebase.remoteconfig.FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
                        break;
                    case 52:
                        if (!zza((com.google.android.gms.internal.clearcut.zzds<T>) r20, r3, r12)) {
                            continue;
                            r12 += 4;
                            r3 = 267386880;
                        }
                        r3 = com.google.android.gms.internal.clearcut.zzbn.zzb(r3, 0.0f);
                        break;
                    case 53:
                        if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r20, r3, r12)) {
                            r5 = zzh(r20, r5);
                            r3 = com.google.android.gms.internal.clearcut.zzbn.zzd(r3, r5);
                            break;
                        } else {
                            continue;
                            r12 += 4;
                            r3 = 267386880;
                        }
                    case 54:
                        if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r20, r3, r12)) {
                            r5 = zzh(r20, r5);
                            r3 = com.google.android.gms.internal.clearcut.zzbn.zze(r3, r5);
                            break;
                        } else {
                            continue;
                            r12 += 4;
                            r3 = 267386880;
                        }
                    case 55:
                        if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r20, r3, r12)) {
                            r5 = zzg(r20, r5);
                            r3 = com.google.android.gms.internal.clearcut.zzbn.zzg(r3, r5);
                            break;
                        } else {
                            continue;
                            r12 += 4;
                            r3 = 267386880;
                        }
                    case 56:
                        if (!zza((com.google.android.gms.internal.clearcut.zzds<T>) r20, r3, r12)) {
                            continue;
                            r12 += 4;
                            r3 = 267386880;
                        }
                        r3 = com.google.android.gms.internal.clearcut.zzbn.zzg(r3, 0L);
                        break;
                    case 57:
                        if (!zza((com.google.android.gms.internal.clearcut.zzds<T>) r20, r3, r12)) {
                            continue;
                            r12 += 4;
                            r3 = 267386880;
                        }
                        r3 = com.google.android.gms.internal.clearcut.zzbn.zzj(r3, 0);
                        break;
                    case 58:
                        if (!zza((com.google.android.gms.internal.clearcut.zzds<T>) r20, r3, r12)) {
                            continue;
                            r12 += 4;
                            r3 = 267386880;
                        }
                        r3 = com.google.android.gms.internal.clearcut.zzbn.zzc(r3, true);
                        break;
                    case 59:
                        if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r20, r3, r12)) {
                            r5 = com.google.android.gms.internal.clearcut.zzfd.zzo(r20, r5);
                            break;
                        } else {
                            continue;
                            r12 += 4;
                            r3 = 267386880;
                        }
                    case 60:
                        if (!zza((com.google.android.gms.internal.clearcut.zzds<T>) r20, r3, r12)) {
                            continue;
                            r12 += 4;
                            r3 = 267386880;
                        }
                        r3 = com.google.android.gms.internal.clearcut.zzeh.zzc(r3, com.google.android.gms.internal.clearcut.zzfd.zzo(r20, r5), zzad(r12));
                        break;
                    case 61:
                        if (!zza((com.google.android.gms.internal.clearcut.zzds<T>) r20, r3, r12)) {
                            continue;
                            r12 += 4;
                            r3 = 267386880;
                        }
                        r5 = com.google.android.gms.internal.clearcut.zzfd.zzo(r20, r5);
                        r3 = com.google.android.gms.internal.clearcut.zzbn.zzc(r3, (com.google.android.gms.internal.clearcut.zzbb) r5);
                        break;
                    case 62:
                        if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r20, r3, r12)) {
                            r5 = zzg(r20, r5);
                            r3 = com.google.android.gms.internal.clearcut.zzbn.zzh(r3, r5);
                            break;
                        } else {
                            continue;
                            r12 += 4;
                            r3 = 267386880;
                        }
                    case 63:
                        if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r20, r3, r12)) {
                            r5 = zzg(r20, r5);
                            r3 = com.google.android.gms.internal.clearcut.zzbn.zzl(r3, r5);
                            break;
                        } else {
                            continue;
                            r12 += 4;
                            r3 = 267386880;
                        }
                    case 64:
                        if (!zza((com.google.android.gms.internal.clearcut.zzds<T>) r20, r3, r12)) {
                            continue;
                            r12 += 4;
                            r3 = 267386880;
                        }
                        r3 = com.google.android.gms.internal.clearcut.zzbn.zzk(r3, 0);
                        break;
                    case 65:
                        if (!zza((com.google.android.gms.internal.clearcut.zzds<T>) r20, r3, r12)) {
                            continue;
                            r12 += 4;
                            r3 = 267386880;
                        }
                        r3 = com.google.android.gms.internal.clearcut.zzbn.zzh(r3, 0L);
                        break;
                    case 66:
                        if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r20, r3, r12)) {
                            r5 = zzg(r20, r5);
                            r3 = com.google.android.gms.internal.clearcut.zzbn.zzi(r3, r5);
                            break;
                        } else {
                            continue;
                            r12 += 4;
                            r3 = 267386880;
                        }
                    case 67:
                        if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r20, r3, r12)) {
                            r5 = zzh(r20, r5);
                            r3 = com.google.android.gms.internal.clearcut.zzbn.zzf(r3, r5);
                            break;
                        } else {
                            continue;
                            r12 += 4;
                            r3 = 267386880;
                        }
                    case 68:
                        if (!zza((com.google.android.gms.internal.clearcut.zzds<T>) r20, r3, r12)) {
                            continue;
                            r12 += 4;
                            r3 = 267386880;
                        }
                        r3 = com.google.android.gms.internal.clearcut.zzbn.zzc(r3, (com.google.android.gms.internal.clearcut.zzdo) com.google.android.gms.internal.clearcut.zzfd.zzo(r20, r5), zzad(r12));
                        break;
                    default:
                        r12 += 4;
                        r3 = 267386880;
                }
                r13 += r3;
                r12 += 4;
                r3 = 267386880;
            }
            return r13 + zza(r19.zzmx, r20);
        }
        sun.misc.Unsafe r2 = com.google.android.gms.internal.clearcut.zzds.zzmh;
        int r3 = -1;
        int r5 = 0;
        int r6 = 0;
        int r12 = 0;
        while (r5 < r19.zzmi.length) {
            int r13 = zzag(r5);
            int[] r14 = r19.zzmi;
            int r15 = r14[r5];
            int r4 = (r13 & 267386880) >>> 20;
            if (r4 <= 17) {
                int r11 = r14[r5 + 2];
                int r14 = r11 & 1048575;
                r18 = 1 << (r11 >>> 20);
                if (r14 != r3) {
                    r12 = r2.getInt(r20, r14);
                    r3 = r14;
                }
                r9 = r11;
            } else {
                r9 = (!r19.zzmr || r4 < com.google.android.gms.internal.clearcut.zzcb.DOUBLE_LIST_PACKED.id() || r4 > com.google.android.gms.internal.clearcut.zzcb.SINT64_LIST_PACKED.id()) ? 0 : r19.zzmi[r5 + 2] & 1048575;
                r18 = 0;
            }
            long r10 = r13 & 1048575;
            switch (r4) {
                case 0:
                    r4 = false;
                    r9 = false;
                    r13 = 0;
                    if ((r12 & r18) != 0) {
                        r6 += com.google.android.gms.internal.clearcut.zzbn.zzb(r15, (double) com.google.firebase.remoteconfig.FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
                        break;
                    }
                    break;
                case 1:
                    r4 = false;
                    r13 = 0;
                    if ((r12 & r18) != 0) {
                        r9 = false;
                        r6 += com.google.android.gms.internal.clearcut.zzbn.zzb(r15, 0.0f);
                        break;
                    }
                    r9 = false;
                case 2:
                    r4 = false;
                    r13 = 0;
                    if ((r12 & r18) != 0) {
                        r9 = com.google.android.gms.internal.clearcut.zzbn.zzd(r15, r2.getLong(r20, r10));
                        r6 += r9;
                    }
                    r9 = false;
                    break;
                case 3:
                    r4 = false;
                    r13 = 0;
                    if ((r12 & r18) != 0) {
                        r9 = com.google.android.gms.internal.clearcut.zzbn.zze(r15, r2.getLong(r20, r10));
                        r6 += r9;
                    }
                    r9 = false;
                    break;
                case 4:
                    r4 = false;
                    r13 = 0;
                    if ((r12 & r18) != 0) {
                        r9 = com.google.android.gms.internal.clearcut.zzbn.zzg(r15, r2.getInt(r20, r10));
                        r6 += r9;
                    }
                    r9 = false;
                    break;
                case 5:
                    r4 = false;
                    r13 = 0;
                    if ((r12 & r18) != 0) {
                        r9 = com.google.android.gms.internal.clearcut.zzbn.zzg(r15, 0L);
                        r6 += r9;
                    }
                    r9 = false;
                    break;
                case 6:
                    if ((r12 & r18) != 0) {
                        r4 = false;
                        r6 += com.google.android.gms.internal.clearcut.zzbn.zzj(r15, 0);
                        r9 = false;
                        r13 = 0;
                        break;
                    }
                    r4 = false;
                    r9 = false;
                    r13 = 0;
                case 7:
                    break;
                case 8:
                    if ((r12 & r18) != 0) {
                        r4 = r2.getObject(r20, r10);
                        break;
                    }
                    r4 = false;
                    r9 = false;
                    r13 = 0;
                    break;
                case 9:
                    break;
                case 10:
                    break;
                case 11:
                    if ((r12 & r18) != 0) {
                        r4 = r2.getInt(r20, r10);
                        r4 = com.google.android.gms.internal.clearcut.zzbn.zzh(r15, r4);
                        r6 += r4;
                    }
                    r4 = false;
                    r9 = false;
                    r13 = 0;
                    break;
                case 12:
                    if ((r12 & r18) != 0) {
                        r4 = r2.getInt(r20, r10);
                        r4 = com.google.android.gms.internal.clearcut.zzbn.zzl(r15, r4);
                        r6 += r4;
                    }
                    r4 = false;
                    r9 = false;
                    r13 = 0;
                    break;
                case 13:
                    break;
                case 14:
                    break;
                case 15:
                    if ((r12 & r18) != 0) {
                        r4 = r2.getInt(r20, r10);
                        r4 = com.google.android.gms.internal.clearcut.zzbn.zzi(r15, r4);
                        r6 += r4;
                    }
                    r4 = false;
                    r9 = false;
                    r13 = 0;
                    break;
                case 16:
                    if ((r12 & r18) != 0) {
                        r9 = r2.getLong(r20, r10);
                        r4 = com.google.android.gms.internal.clearcut.zzbn.zzf(r15, r9);
                        r6 += r4;
                    }
                    r4 = false;
                    r9 = false;
                    r13 = 0;
                    break;
                case 17:
                    break;
                case 18:
                    r4 = com.google.android.gms.internal.clearcut.zzeh.zzw(r15, (java.util.List) r2.getObject(r20, r10), false);
                    r6 += r4;
                    r4 = false;
                    r9 = false;
                    r13 = 0;
                    break;
                case 19:
                case 24:
                case 31:
                    r9 = false;
                    r4 = com.google.android.gms.internal.clearcut.zzeh.zzv(r15, (java.util.List) r2.getObject(r20, r10), false);
                    r6 += r4;
                    r4 = r9;
                    r9 = false;
                    r13 = 0;
                    break;
                case 20:
                    r9 = false;
                    r4 = com.google.android.gms.internal.clearcut.zzeh.zzo(r15, (java.util.List) r2.getObject(r20, r10), false);
                    r6 += r4;
                    r4 = r9;
                    r9 = false;
                    r13 = 0;
                    break;
                case 21:
                    r9 = false;
                    r4 = com.google.android.gms.internal.clearcut.zzeh.zzp(r15, (java.util.List) r2.getObject(r20, r10), false);
                    r6 += r4;
                    r4 = r9;
                    r9 = false;
                    r13 = 0;
                    break;
                case 22:
                    r9 = false;
                    r4 = com.google.android.gms.internal.clearcut.zzeh.zzs(r15, (java.util.List) r2.getObject(r20, r10), false);
                    r6 += r4;
                    r4 = r9;
                    r9 = false;
                    r13 = 0;
                    break;
                case 23:
                case 32:
                    r9 = false;
                    r4 = com.google.android.gms.internal.clearcut.zzeh.zzw(r15, (java.util.List) r2.getObject(r20, r10), false);
                    r6 += r4;
                    r4 = r9;
                    r9 = false;
                    r13 = 0;
                    break;
                case 25:
                    r9 = false;
                    r4 = com.google.android.gms.internal.clearcut.zzeh.zzx(r15, (java.util.List) r2.getObject(r20, r10), false);
                    r6 += r4;
                    r4 = r9;
                    r9 = false;
                    r13 = 0;
                    break;
                case 26:
                    r4 = com.google.android.gms.internal.clearcut.zzeh.zzc(r15, (java.util.List) r2.getObject(r20, r10));
                    r6 += r4;
                    r4 = false;
                    r9 = false;
                    r13 = 0;
                    break;
                case 27:
                    r4 = com.google.android.gms.internal.clearcut.zzeh.zzc(r15, (java.util.List<?>) r2.getObject(r20, r10), zzad(r5));
                    r6 += r4;
                    r4 = false;
                    r9 = false;
                    r13 = 0;
                    break;
                case 28:
                    r4 = com.google.android.gms.internal.clearcut.zzeh.zzd(r15, (java.util.List) r2.getObject(r20, r10));
                    r6 += r4;
                    r4 = false;
                    r9 = false;
                    r13 = 0;
                    break;
                case 29:
                    r4 = com.google.android.gms.internal.clearcut.zzeh.zzt(r15, (java.util.List) r2.getObject(r20, r10), false);
                    r6 += r4;
                    r4 = false;
                    r9 = false;
                    r13 = 0;
                    break;
                case 30:
                    r9 = false;
                    r4 = com.google.android.gms.internal.clearcut.zzeh.zzr(r15, (java.util.List) r2.getObject(r20, r10), false);
                    r6 += r4;
                    r4 = r9;
                    r9 = false;
                    r13 = 0;
                    break;
                case 33:
                    r9 = false;
                    r4 = com.google.android.gms.internal.clearcut.zzeh.zzu(r15, (java.util.List) r2.getObject(r20, r10), false);
                    r6 += r4;
                    r4 = r9;
                    r9 = false;
                    r13 = 0;
                    break;
                case 34:
                    r9 = false;
                    r4 = com.google.android.gms.internal.clearcut.zzeh.zzq(r15, (java.util.List) r2.getObject(r20, r10), false);
                    r6 += r4;
                    r4 = r9;
                    r9 = false;
                    r13 = 0;
                    break;
                case 35:
                    r4 = com.google.android.gms.internal.clearcut.zzeh.zzi((java.util.List) r2.getObject(r20, r10));
                    if (r4 > 0) {
                        break;
                    }
                    r4 = false;
                    r9 = false;
                    r13 = 0;
                    break;
                case 36:
                    r4 = com.google.android.gms.internal.clearcut.zzeh.zzh((java.util.List) r2.getObject(r20, r10));
                    if (r4 > 0) {
                        break;
                    }
                    r4 = false;
                    r9 = false;
                    r13 = 0;
                    break;
                case 37:
                    r4 = com.google.android.gms.internal.clearcut.zzeh.zza((java.util.List) r2.getObject(r20, r10));
                    if (r4 > 0) {
                        break;
                    }
                    r4 = false;
                    r9 = false;
                    r13 = 0;
                    break;
                case 38:
                    r4 = com.google.android.gms.internal.clearcut.zzeh.zzb((java.util.List) r2.getObject(r20, r10));
                    if (r4 > 0) {
                        break;
                    }
                    r4 = false;
                    r9 = false;
                    r13 = 0;
                    break;
                case 39:
                    r4 = com.google.android.gms.internal.clearcut.zzeh.zze((java.util.List) r2.getObject(r20, r10));
                    if (r4 > 0) {
                        break;
                    }
                    r4 = false;
                    r9 = false;
                    r13 = 0;
                    break;
                case 40:
                    r4 = com.google.android.gms.internal.clearcut.zzeh.zzi((java.util.List) r2.getObject(r20, r10));
                    if (r4 > 0) {
                        break;
                    }
                    r4 = false;
                    r9 = false;
                    r13 = 0;
                    break;
                case 41:
                    r4 = com.google.android.gms.internal.clearcut.zzeh.zzh((java.util.List) r2.getObject(r20, r10));
                    if (r4 > 0) {
                        break;
                    }
                    r4 = false;
                    r9 = false;
                    r13 = 0;
                    break;
                case 42:
                    r4 = com.google.android.gms.internal.clearcut.zzeh.zzj((java.util.List) r2.getObject(r20, r10));
                    if (r4 > 0) {
                        break;
                    }
                    r4 = false;
                    r9 = false;
                    r13 = 0;
                    break;
                case 43:
                    r4 = com.google.android.gms.internal.clearcut.zzeh.zzf((java.util.List) r2.getObject(r20, r10));
                    if (r4 > 0) {
                        break;
                    }
                    r4 = false;
                    r9 = false;
                    r13 = 0;
                    break;
                case 44:
                    r4 = com.google.android.gms.internal.clearcut.zzeh.zzd((java.util.List) r2.getObject(r20, r10));
                    if (r4 > 0) {
                        break;
                    }
                    r4 = false;
                    r9 = false;
                    r13 = 0;
                    break;
                case 45:
                    r4 = com.google.android.gms.internal.clearcut.zzeh.zzh((java.util.List) r2.getObject(r20, r10));
                    if (r4 > 0) {
                        break;
                    }
                    r4 = false;
                    r9 = false;
                    r13 = 0;
                    break;
                case 46:
                    r4 = com.google.android.gms.internal.clearcut.zzeh.zzi((java.util.List) r2.getObject(r20, r10));
                    if (r4 > 0) {
                        break;
                    }
                    r4 = false;
                    r9 = false;
                    r13 = 0;
                    break;
                case 47:
                    r4 = com.google.android.gms.internal.clearcut.zzeh.zzg((java.util.List) r2.getObject(r20, r10));
                    if (r4 > 0) {
                        break;
                    }
                    r4 = false;
                    r9 = false;
                    r13 = 0;
                    break;
                case 48:
                    r4 = com.google.android.gms.internal.clearcut.zzeh.zzc((java.util.List) r2.getObject(r20, r10));
                    if (r4 > 0) {
                        break;
                    }
                    r4 = false;
                    r9 = false;
                    r13 = 0;
                    break;
                case 49:
                    r4 = com.google.android.gms.internal.clearcut.zzeh.zzd(r15, (java.util.List) r2.getObject(r20, r10), zzad(r5));
                    r6 += r4;
                    r4 = false;
                    r9 = false;
                    r13 = 0;
                    break;
                case 50:
                    r4 = r19.zzmz.zzb(r15, r2.getObject(r20, r10), zzae(r5));
                    r6 += r4;
                    r4 = false;
                    r9 = false;
                    r13 = 0;
                    break;
                case 51:
                    if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r20, r15, r5)) {
                        r4 = com.google.android.gms.internal.clearcut.zzbn.zzb(r15, (double) com.google.firebase.remoteconfig.FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
                        r6 += r4;
                    }
                    r4 = false;
                    r9 = false;
                    r13 = 0;
                    break;
                case 52:
                    if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r20, r15, r5)) {
                        r9 = com.google.android.gms.internal.clearcut.zzbn.zzb(r15, 0.0f);
                        r6 += r9;
                    }
                    r4 = false;
                    r9 = false;
                    r13 = 0;
                    break;
                case 53:
                    if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r20, r15, r5)) {
                        r4 = com.google.android.gms.internal.clearcut.zzbn.zzd(r15, zzh(r20, r10));
                        r6 += r4;
                    }
                    r4 = false;
                    r9 = false;
                    r13 = 0;
                    break;
                case 54:
                    if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r20, r15, r5)) {
                        r4 = com.google.android.gms.internal.clearcut.zzbn.zze(r15, zzh(r20, r10));
                        r6 += r4;
                    }
                    r4 = false;
                    r9 = false;
                    r13 = 0;
                    break;
                case 55:
                    if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r20, r15, r5)) {
                        r4 = com.google.android.gms.internal.clearcut.zzbn.zzg(r15, zzg(r20, r10));
                        r6 += r4;
                    }
                    r4 = false;
                    r9 = false;
                    r13 = 0;
                    break;
                case 56:
                    if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r20, r15, r5)) {
                        r4 = com.google.android.gms.internal.clearcut.zzbn.zzg(r15, 0L);
                        r6 += r4;
                    }
                    r4 = false;
                    r9 = false;
                    r13 = 0;
                    break;
                case 57:
                    if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r20, r15, r5)) {
                        r9 = com.google.android.gms.internal.clearcut.zzbn.zzj(r15, 0);
                        r6 += r9;
                    }
                    r4 = false;
                    r9 = false;
                    r13 = 0;
                    break;
                case 58:
                    break;
                case 59:
                    if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r20, r15, r5)) {
                        r4 = r2.getObject(r20, r10);
                        break;
                    }
                    r4 = false;
                    r9 = false;
                    r13 = 0;
                    break;
                case 60:
                    break;
                case 61:
                    break;
                case 62:
                    if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r20, r15, r5)) {
                        r4 = zzg(r20, r10);
                        r4 = com.google.android.gms.internal.clearcut.zzbn.zzh(r15, r4);
                        r6 += r4;
                    }
                    r4 = false;
                    r9 = false;
                    r13 = 0;
                    break;
                case 63:
                    if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r20, r15, r5)) {
                        r4 = zzg(r20, r10);
                        r4 = com.google.android.gms.internal.clearcut.zzbn.zzl(r15, r4);
                        r6 += r4;
                    }
                    r4 = false;
                    r9 = false;
                    r13 = 0;
                    break;
                case 64:
                    break;
                case 65:
                    break;
                case 66:
                    if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r20, r15, r5)) {
                        r4 = zzg(r20, r10);
                        r4 = com.google.android.gms.internal.clearcut.zzbn.zzi(r15, r4);
                        r6 += r4;
                    }
                    r4 = false;
                    r9 = false;
                    r13 = 0;
                    break;
                case 67:
                    if (zza((com.google.android.gms.internal.clearcut.zzds<T>) r20, r15, r5)) {
                        r9 = zzh(r20, r10);
                        r4 = com.google.android.gms.internal.clearcut.zzbn.zzf(r15, r9);
                        r6 += r4;
                    }
                    r4 = false;
                    r9 = false;
                    r13 = 0;
                    break;
                case 68:
                    break;
                default:
                    r4 = false;
                    r9 = false;
                    r13 = 0;
                    break;
            }
            r5 += 4;
        }
        int r6 = r6 + zza(r19.zzmx, r20);
        return r19.zzmo ? r6 + r19.zzmy.zza(r20).zzas() : r6;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v10, types: [com.google.android.gms.internal.clearcut.zzef] */
    /* JADX WARN: Type inference failed for: r7v25 */
    /* JADX WARN: Type inference failed for: r7v27, types: [com.google.android.gms.internal.clearcut.zzef] */
    /* JADX WARN: Type inference failed for: r7v30 */
    @Override // com.google.android.gms.internal.clearcut.zzef
    public final boolean zzo(T t) {
        int i;
        int i2;
        boolean z;
        boolean z2;
        int[] iArr = this.zzms;
        if (iArr != null && iArr.length != 0) {
            int i3 = -1;
            int length = iArr.length;
            int i4 = 0;
            for (int i5 = 0; i5 < length; i5 = i + 1) {
                int i6 = iArr[i5];
                int zzai = zzai(i6);
                int zzag = zzag(zzai);
                if (this.zzmq) {
                    i = i5;
                    i2 = 0;
                } else {
                    int i7 = this.zzmi[zzai + 2];
                    int i8 = i7 & 1048575;
                    i2 = 1 << (i7 >>> 20);
                    if (i8 != i3) {
                        i = i5;
                        i4 = zzmh.getInt(t, i8);
                        i3 = i8;
                    } else {
                        i = i5;
                    }
                }
                if (((268435456 & zzag) != 0) && !zza((zzds<T>) t, zzai, i4, i2)) {
                    return false;
                }
                int i9 = (267386880 & zzag) >>> 20;
                if (i9 != 9 && i9 != 17) {
                    if (i9 != 27) {
                        if (i9 == 60 || i9 == 68) {
                            if (zza((zzds<T>) t, i6, zzai) && !zza(t, zzag, zzad(zzai))) {
                                return false;
                            }
                        } else if (i9 != 49) {
                            if (i9 != 50) {
                                continue;
                            } else {
                                Map<?, ?> zzh = this.zzmz.zzh(zzfd.zzo(t, zzag & 1048575));
                                if (!zzh.isEmpty()) {
                                    if (this.zzmz.zzl(zzae(zzai)).zzmd.zzek() == zzfq.MESSAGE) {
                                        zzef<T> zzefVar = 0;
                                        for (Object obj : zzh.values()) {
                                            if (zzefVar == null) {
                                                zzefVar = zzea.zzcm().zze(obj.getClass());
                                            }
                                            boolean zzo = zzefVar.zzo(obj);
                                            zzefVar = zzefVar;
                                            if (!zzo) {
                                                z2 = false;
                                                break;
                                            }
                                        }
                                    }
                                }
                                z2 = true;
                                if (!z2) {
                                    return false;
                                }
                            }
                        }
                    }
                    List list = (List) zzfd.zzo(t, zzag & 1048575);
                    if (!list.isEmpty()) {
                        ?? zzad = zzad(zzai);
                        for (int i10 = 0; i10 < list.size(); i10++) {
                            if (!zzad.zzo(list.get(i10))) {
                                z = false;
                                break;
                            }
                        }
                    }
                    z = true;
                    if (!z) {
                        return false;
                    }
                } else if (zza((zzds<T>) t, zzai, i4, i2) && !zza(t, zzag, zzad(zzai))) {
                    return false;
                }
            }
            if (this.zzmo && !this.zzmy.zza(t).isInitialized()) {
                return false;
            }
        }
        return true;
    }
}
