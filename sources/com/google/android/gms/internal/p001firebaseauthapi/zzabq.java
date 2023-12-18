package com.google.android.gms.internal.p001firebaseauthapi;

import java.io.IOException;
import kotlin.UByte;
import kotlin.jvm.internal.ByteCompanionObject;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzabq  reason: invalid package */
/* loaded from: classes.dex */
public final class zzabq {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zza(byte[] bArr, int i, zzabp zzabpVar) throws zzadn {
        int zzj = zzj(bArr, i, zzabpVar);
        int i2 = zzabpVar.zza;
        if (i2 < 0) {
            throw zzadn.zzf();
        }
        if (i2 <= bArr.length - zzj) {
            if (i2 == 0) {
                zzabpVar.zzc = zzacc.zzb;
                return zzj;
            }
            zzabpVar.zzc = zzacc.zzo(bArr, zzj, i2);
            return zzj + i2;
        }
        throw zzadn.zzi();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzb(byte[] bArr, int i) {
        return ((bArr[i + 3] & UByte.MAX_VALUE) << 24) | (bArr[i] & UByte.MAX_VALUE) | ((bArr[i + 1] & UByte.MAX_VALUE) << 8) | ((bArr[i + 2] & UByte.MAX_VALUE) << 16);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzc(zzaew zzaewVar, byte[] bArr, int i, int i2, int i3, zzabp zzabpVar) throws IOException {
        Object zze = zzaewVar.zze();
        int zzn = zzn(zze, zzaewVar, bArr, i, i2, i3, zzabpVar);
        zzaewVar.zzf(zze);
        zzabpVar.zzc = zze;
        return zzn;
    }

    static int zzd(zzaew zzaewVar, byte[] bArr, int i, int i2, zzabp zzabpVar) throws IOException {
        Object zze = zzaewVar.zze();
        int zzo = zzo(zze, zzaewVar, bArr, i, i2, zzabpVar);
        zzaewVar.zzf(zze);
        zzabpVar.zzc = zze;
        return zzo;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zze(zzaew zzaewVar, int i, byte[] bArr, int i2, int i3, zzadk zzadkVar, zzabp zzabpVar) throws IOException {
        int zzd = zzd(zzaewVar, bArr, i2, i3, zzabpVar);
        zzadkVar.add(zzabpVar.zzc);
        while (zzd < i3) {
            int zzj = zzj(bArr, zzd, zzabpVar);
            if (i != zzabpVar.zza) {
                break;
            }
            zzd = zzd(zzaewVar, bArr, zzj, i3, zzabpVar);
            zzadkVar.add(zzabpVar.zzc);
        }
        return zzd;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzf(byte[] bArr, int i, zzadk zzadkVar, zzabp zzabpVar) throws IOException {
        zzadg zzadgVar = (zzadg) zzadkVar;
        int zzj = zzj(bArr, i, zzabpVar);
        int i2 = zzabpVar.zza + zzj;
        while (zzj < i2) {
            zzj = zzj(bArr, zzj, zzabpVar);
            zzadgVar.zzf(zzabpVar.zza);
        }
        if (zzj == i2) {
            return zzj;
        }
        throw zzadn.zzi();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzg(byte[] bArr, int i, zzabp zzabpVar) throws zzadn {
        int zzj = zzj(bArr, i, zzabpVar);
        int i2 = zzabpVar.zza;
        if (i2 >= 0) {
            if (i2 == 0) {
                zzabpVar.zzc = "";
                return zzj;
            }
            zzabpVar.zzc = new String(bArr, zzj, i2, zzadl.zzb);
            return zzj + i2;
        }
        throw zzadn.zzf();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzh(byte[] bArr, int i, zzabp zzabpVar) throws zzadn {
        int zzj = zzj(bArr, i, zzabpVar);
        int i2 = zzabpVar.zza;
        if (i2 >= 0) {
            if (i2 == 0) {
                zzabpVar.zzc = "";
                return zzj;
            }
            zzabpVar.zzc = zzagc.zzd(bArr, zzj, i2);
            return zzj + i2;
        }
        throw zzadn.zzf();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzi(int i, byte[] bArr, int i2, int i3, zzafo zzafoVar, zzabp zzabpVar) throws zzadn {
        if ((i >>> 3) != 0) {
            int i4 = i & 7;
            if (i4 == 0) {
                int zzm = zzm(bArr, i2, zzabpVar);
                zzafoVar.zzj(i, Long.valueOf(zzabpVar.zzb));
                return zzm;
            } else if (i4 == 1) {
                zzafoVar.zzj(i, Long.valueOf(zzp(bArr, i2)));
                return i2 + 8;
            } else if (i4 == 2) {
                int zzj = zzj(bArr, i2, zzabpVar);
                int i5 = zzabpVar.zza;
                if (i5 < 0) {
                    throw zzadn.zzf();
                }
                if (i5 <= bArr.length - zzj) {
                    if (i5 == 0) {
                        zzafoVar.zzj(i, zzacc.zzb);
                    } else {
                        zzafoVar.zzj(i, zzacc.zzo(bArr, zzj, i5));
                    }
                    return zzj + i5;
                }
                throw zzadn.zzi();
            } else if (i4 != 3) {
                if (i4 == 5) {
                    zzafoVar.zzj(i, Integer.valueOf(zzb(bArr, i2)));
                    return i2 + 4;
                }
                throw zzadn.zzc();
            } else {
                int i6 = (i & (-8)) | 4;
                zzafo zzf = zzafo.zzf();
                int i7 = 0;
                while (true) {
                    if (i2 >= i3) {
                        break;
                    }
                    int zzj2 = zzj(bArr, i2, zzabpVar);
                    int i8 = zzabpVar.zza;
                    if (i8 == i6) {
                        i7 = i8;
                        i2 = zzj2;
                        break;
                    }
                    i7 = i8;
                    i2 = zzi(i8, bArr, zzj2, i3, zzf, zzabpVar);
                }
                if (i2 > i3 || i7 != i6) {
                    throw zzadn.zzg();
                }
                zzafoVar.zzj(i, zzf);
                return i2;
            }
        }
        throw zzadn.zzc();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzj(byte[] bArr, int i, zzabp zzabpVar) {
        int i2 = i + 1;
        byte b = bArr[i];
        if (b >= 0) {
            zzabpVar.zza = b;
            return i2;
        }
        return zzk(b, bArr, i2, zzabpVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzk(int i, byte[] bArr, int i2, zzabp zzabpVar) {
        int i3 = i & 127;
        int i4 = i2 + 1;
        byte b = bArr[i2];
        if (b >= 0) {
            zzabpVar.zza = i3 | (b << 7);
            return i4;
        }
        int i5 = i3 | ((b & ByteCompanionObject.MAX_VALUE) << 7);
        int i6 = i4 + 1;
        byte b2 = bArr[i4];
        if (b2 >= 0) {
            zzabpVar.zza = i5 | (b2 << 14);
            return i6;
        }
        int i7 = i5 | ((b2 & ByteCompanionObject.MAX_VALUE) << 14);
        int i8 = i6 + 1;
        byte b3 = bArr[i6];
        if (b3 >= 0) {
            zzabpVar.zza = i7 | (b3 << 21);
            return i8;
        }
        int i9 = i7 | ((b3 & ByteCompanionObject.MAX_VALUE) << 21);
        int i10 = i8 + 1;
        byte b4 = bArr[i8];
        if (b4 >= 0) {
            zzabpVar.zza = i9 | (b4 << 28);
            return i10;
        }
        int i11 = i9 | ((b4 & ByteCompanionObject.MAX_VALUE) << 28);
        while (true) {
            int i12 = i10 + 1;
            if (bArr[i10] >= 0) {
                zzabpVar.zza = i11;
                return i12;
            }
            i10 = i12;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzl(int i, byte[] bArr, int i2, int i3, zzadk zzadkVar, zzabp zzabpVar) {
        zzadg zzadgVar = (zzadg) zzadkVar;
        int zzj = zzj(bArr, i2, zzabpVar);
        zzadgVar.zzf(zzabpVar.zza);
        while (zzj < i3) {
            int zzj2 = zzj(bArr, zzj, zzabpVar);
            if (i != zzabpVar.zza) {
                break;
            }
            zzj = zzj(bArr, zzj2, zzabpVar);
            zzadgVar.zzf(zzabpVar.zza);
        }
        return zzj;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzm(byte[] bArr, int i, zzabp zzabpVar) {
        byte b;
        int i2 = i + 1;
        long j = bArr[i];
        if (j >= 0) {
            zzabpVar.zzb = j;
            return i2;
        }
        int i3 = i2 + 1;
        byte b2 = bArr[i2];
        long j2 = (j & 127) | ((b2 & ByteCompanionObject.MAX_VALUE) << 7);
        int i4 = 7;
        while (b2 < 0) {
            int i5 = i3 + 1;
            i4 += 7;
            j2 |= (b & ByteCompanionObject.MAX_VALUE) << i4;
            b2 = bArr[i3];
            i3 = i5;
        }
        zzabpVar.zzb = j2;
        return i3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzn(Object obj, zzaew zzaewVar, byte[] bArr, int i, int i2, int i3, zzabp zzabpVar) throws IOException {
        int zzc = ((zzaen) zzaewVar).zzc(obj, bArr, i, i2, i3, zzabpVar);
        zzabpVar.zzc = obj;
        return zzc;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzo(Object obj, zzaew zzaewVar, byte[] bArr, int i, int i2, zzabp zzabpVar) throws IOException {
        int i3 = i + 1;
        int i4 = bArr[i];
        if (i4 < 0) {
            i3 = zzk(i4, bArr, i3, zzabpVar);
            i4 = zzabpVar.zza;
        }
        int i5 = i3;
        if (i4 < 0 || i4 > i2 - i5) {
            throw zzadn.zzi();
        }
        int i6 = i4 + i5;
        zzaewVar.zzi(obj, bArr, i5, i6, zzabpVar);
        zzabpVar.zzc = obj;
        return i6;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long zzp(byte[] bArr, int i) {
        return ((bArr[i + 7] & 255) << 56) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16) | ((bArr[i + 3] & 255) << 24) | ((bArr[i + 4] & 255) << 32) | ((bArr[i + 5] & 255) << 40) | ((bArr[i + 6] & 255) << 48);
    }
}
