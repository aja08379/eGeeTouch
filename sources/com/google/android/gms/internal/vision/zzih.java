package com.google.android.gms.internal.vision;

import java.io.IOException;
import java.util.Arrays;
import kotlin.UByte;
import kotlin.jvm.internal.ByteCompanionObject;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.3 */
/* loaded from: classes2.dex */
public final class zzih extends zzif {
    private final byte[] zzd;
    private final boolean zze;
    private int zzf;
    private int zzg;
    private int zzh;
    private int zzi;
    private int zzj;
    private int zzk;

    private zzih(byte[] bArr, int i, int i2, boolean z) {
        super();
        this.zzk = Integer.MAX_VALUE;
        this.zzd = bArr;
        this.zzf = i2 + i;
        this.zzh = i;
        this.zzi = i;
        this.zze = z;
    }

    @Override // com.google.android.gms.internal.vision.zzif
    public final int zza() throws IOException {
        if (zzt()) {
            this.zzj = 0;
            return 0;
        }
        int zzv = zzv();
        this.zzj = zzv;
        if ((zzv >>> 3) != 0) {
            return zzv;
        }
        throw zzjk.zzd();
    }

    @Override // com.google.android.gms.internal.vision.zzif
    public final void zza(int i) throws zzjk {
        if (this.zzj != i) {
            throw zzjk.zze();
        }
    }

    @Override // com.google.android.gms.internal.vision.zzif
    public final boolean zzb(int i) throws IOException {
        int zza;
        int i2 = i & 7;
        int i3 = 0;
        if (i2 == 0) {
            if (this.zzf - this.zzh >= 10) {
                while (i3 < 10) {
                    byte[] bArr = this.zzd;
                    int i4 = this.zzh;
                    this.zzh = i4 + 1;
                    if (bArr[i4] < 0) {
                        i3++;
                    }
                }
                throw zzjk.zzc();
            }
            while (i3 < 10) {
                if (zzaa() < 0) {
                    i3++;
                }
            }
            throw zzjk.zzc();
            return true;
        } else if (i2 == 1) {
            zzf(8);
            return true;
        } else if (i2 == 2) {
            zzf(zzv());
            return true;
        } else if (i2 != 3) {
            if (i2 != 4) {
                if (i2 == 5) {
                    zzf(4);
                    return true;
                }
                throw zzjk.zzf();
            }
            return false;
        } else {
            do {
                zza = zza();
                if (zza == 0) {
                    break;
                }
            } while (zzb(zza));
            zza(((i >>> 3) << 3) | 4);
            return true;
        }
    }

    @Override // com.google.android.gms.internal.vision.zzif
    public final double zzb() throws IOException {
        return Double.longBitsToDouble(zzy());
    }

    @Override // com.google.android.gms.internal.vision.zzif
    public final float zzc() throws IOException {
        return Float.intBitsToFloat(zzx());
    }

    @Override // com.google.android.gms.internal.vision.zzif
    public final long zzd() throws IOException {
        return zzw();
    }

    @Override // com.google.android.gms.internal.vision.zzif
    public final long zze() throws IOException {
        return zzw();
    }

    @Override // com.google.android.gms.internal.vision.zzif
    public final int zzf() throws IOException {
        return zzv();
    }

    @Override // com.google.android.gms.internal.vision.zzif
    public final long zzg() throws IOException {
        return zzy();
    }

    @Override // com.google.android.gms.internal.vision.zzif
    public final int zzh() throws IOException {
        return zzx();
    }

    @Override // com.google.android.gms.internal.vision.zzif
    public final boolean zzi() throws IOException {
        return zzw() != 0;
    }

    @Override // com.google.android.gms.internal.vision.zzif
    public final String zzj() throws IOException {
        int zzv = zzv();
        if (zzv > 0 && zzv <= this.zzf - this.zzh) {
            String str = new String(this.zzd, this.zzh, zzv, zzjf.zza);
            this.zzh += zzv;
            return str;
        } else if (zzv == 0) {
            return "";
        } else {
            if (zzv < 0) {
                throw zzjk.zzb();
            }
            throw zzjk.zza();
        }
    }

    @Override // com.google.android.gms.internal.vision.zzif
    public final String zzk() throws IOException {
        int zzv = zzv();
        if (zzv > 0) {
            int i = this.zzf;
            int i2 = this.zzh;
            if (zzv <= i - i2) {
                String zzb = zzmd.zzb(this.zzd, i2, zzv);
                this.zzh += zzv;
                return zzb;
            }
        }
        if (zzv == 0) {
            return "";
        }
        if (zzv <= 0) {
            throw zzjk.zzb();
        }
        throw zzjk.zza();
    }

    @Override // com.google.android.gms.internal.vision.zzif
    public final zzht zzl() throws IOException {
        byte[] bArr;
        int zzv = zzv();
        if (zzv > 0) {
            int i = this.zzf;
            int i2 = this.zzh;
            if (zzv <= i - i2) {
                zzht zza = zzht.zza(this.zzd, i2, zzv);
                this.zzh += zzv;
                return zza;
            }
        }
        if (zzv == 0) {
            return zzht.zza;
        }
        if (zzv > 0) {
            int i3 = this.zzf;
            int i4 = this.zzh;
            if (zzv <= i3 - i4) {
                int i5 = zzv + i4;
                this.zzh = i5;
                bArr = Arrays.copyOfRange(this.zzd, i4, i5);
                return zzht.zza(bArr);
            }
        }
        if (zzv <= 0) {
            if (zzv == 0) {
                bArr = zzjf.zzb;
                return zzht.zza(bArr);
            }
            throw zzjk.zzb();
        }
        throw zzjk.zza();
    }

    @Override // com.google.android.gms.internal.vision.zzif
    public final int zzm() throws IOException {
        return zzv();
    }

    @Override // com.google.android.gms.internal.vision.zzif
    public final int zzn() throws IOException {
        return zzv();
    }

    @Override // com.google.android.gms.internal.vision.zzif
    public final int zzo() throws IOException {
        return zzx();
    }

    @Override // com.google.android.gms.internal.vision.zzif
    public final long zzp() throws IOException {
        return zzy();
    }

    @Override // com.google.android.gms.internal.vision.zzif
    public final int zzq() throws IOException {
        return zze(zzv());
    }

    @Override // com.google.android.gms.internal.vision.zzif
    public final long zzr() throws IOException {
        return zza(zzw());
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x0066, code lost:
        if (r2[r3] >= 0) goto L13;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final int zzv() throws java.io.IOException {
        int r0;
        int r0 = r5.zzh;
        int r1 = r5.zzf;
        if (r1 != r0) {
            byte[] r2 = r5.zzd;
            int r3 = r0 + 1;
            byte r0 = r2[r0];
            if (r0 >= 0) {
                r5.zzh = r3;
                return r0;
            } else if (r1 - r3 >= 9) {
                int r1 = r3 + 1;
                int r0 = r0 ^ (r2[r3] << 7);
                if (r0 < 0) {
                    r0 = r0 ^ (-128);
                } else {
                    int r3 = r1 + 1;
                    int r0 = r0 ^ (r2[r1] << 14);
                    if (r0 >= 0) {
                        r0 = r0 ^ 16256;
                    } else {
                        r1 = r3 + 1;
                        int r0 = r0 ^ (r2[r3] << 21);
                        if (r0 < 0) {
                            r0 = r0 ^ (-2080896);
                        } else {
                            r3 = r1 + 1;
                            byte r1 = r2[r1];
                            r0 = (r0 ^ (r1 << 28)) ^ 266354560;
                            if (r1 < 0) {
                                r1 = r3 + 1;
                                if (r2[r3] < 0) {
                                    r3 = r1 + 1;
                                    if (r2[r1] < 0) {
                                        r1 = r3 + 1;
                                        if (r2[r3] < 0) {
                                            r3 = r1 + 1;
                                            if (r2[r1] < 0) {
                                                r1 = r3 + 1;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    r1 = r3;
                }
                r5.zzh = r1;
                return r0;
            }
        }
        return (int) zzs();
    }

    /* JADX WARN: Code restructure failed: missing block: B:37:0x00b0, code lost:
        if (r2[r0] >= 0) goto L40;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final long zzw() throws java.io.IOException {
        long r2;
        long r5;
        long r1;
        int r0;
        int r0 = r11.zzh;
        int r1 = r11.zzf;
        if (r1 != r0) {
            byte[] r2 = r11.zzd;
            int r3 = r0 + 1;
            byte r0 = r2[r0];
            if (r0 >= 0) {
                r11.zzh = r3;
                return r0;
            } else if (r1 - r3 >= 9) {
                int r1 = r3 + 1;
                int r0 = r0 ^ (r2[r3] << 7);
                if (r0 >= 0) {
                    int r3 = r1 + 1;
                    int r0 = r0 ^ (r2[r1] << 14);
                    if (r0 >= 0) {
                        r1 = r3;
                        r2 = r0 ^ 16256;
                    } else {
                        r1 = r3 + 1;
                        int r0 = r0 ^ (r2[r3] << 21);
                        if (r0 < 0) {
                            r0 = r0 ^ (-2080896);
                        } else {
                            long r3 = r0;
                            int r0 = r1 + 1;
                            long r3 = r3 ^ (r2[r1] << 28);
                            if (r3 >= 0) {
                                r1 = 266354560;
                            } else {
                                r1 = r0 + 1;
                                long r3 = r3 ^ (r2[r0] << 35);
                                if (r3 < 0) {
                                    r5 = -34093383808L;
                                } else {
                                    r0 = r1 + 1;
                                    r3 = r3 ^ (r2[r1] << 42);
                                    if (r3 >= 0) {
                                        r1 = 4363953127296L;
                                    } else {
                                        r1 = r0 + 1;
                                        r3 = r3 ^ (r2[r0] << 49);
                                        if (r3 < 0) {
                                            r5 = -558586000294016L;
                                        } else {
                                            int r0 = r1 + 1;
                                            long r3 = (r3 ^ (r2[r1] << 56)) ^ 71499008037633920L;
                                            r1 = r3 < 0 ? r0 + 1 : r0;
                                            r2 = r3;
                                        }
                                    }
                                }
                                r2 = r3 ^ r5;
                            }
                            r2 = r3 ^ r1;
                            r1 = r0;
                        }
                    }
                    r11.zzh = r1;
                    return r2;
                }
                r0 = r0 ^ (-128);
                r2 = r0;
                r11.zzh = r1;
                return r2;
            }
        }
        return zzs();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.vision.zzif
    public final long zzs() throws IOException {
        long j = 0;
        for (int i = 0; i < 64; i += 7) {
            byte zzaa = zzaa();
            j |= (zzaa & ByteCompanionObject.MAX_VALUE) << i;
            if ((zzaa & ByteCompanionObject.MIN_VALUE) == 0) {
                return j;
            }
        }
        throw zzjk.zzc();
    }

    private final int zzx() throws IOException {
        int i = this.zzh;
        if (this.zzf - i < 4) {
            throw zzjk.zza();
        }
        byte[] bArr = this.zzd;
        this.zzh = i + 4;
        return ((bArr[i + 3] & UByte.MAX_VALUE) << 24) | (bArr[i] & UByte.MAX_VALUE) | ((bArr[i + 1] & UByte.MAX_VALUE) << 8) | ((bArr[i + 2] & UByte.MAX_VALUE) << 16);
    }

    private final long zzy() throws IOException {
        int i = this.zzh;
        if (this.zzf - i < 8) {
            throw zzjk.zza();
        }
        byte[] bArr = this.zzd;
        this.zzh = i + 8;
        return ((bArr[i + 7] & 255) << 56) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16) | ((bArr[i + 3] & 255) << 24) | ((bArr[i + 4] & 255) << 32) | ((bArr[i + 5] & 255) << 40) | ((bArr[i + 6] & 255) << 48);
    }

    @Override // com.google.android.gms.internal.vision.zzif
    public final int zzc(int i) throws zzjk {
        if (i < 0) {
            throw zzjk.zzb();
        }
        int zzu = i + zzu();
        int i2 = this.zzk;
        if (zzu > i2) {
            throw zzjk.zza();
        }
        this.zzk = zzu;
        zzz();
        return i2;
    }

    private final void zzz() {
        int i = this.zzf + this.zzg;
        this.zzf = i;
        int i2 = i - this.zzi;
        int i3 = this.zzk;
        if (i2 > i3) {
            int i4 = i2 - i3;
            this.zzg = i4;
            this.zzf = i - i4;
            return;
        }
        this.zzg = 0;
    }

    @Override // com.google.android.gms.internal.vision.zzif
    public final void zzd(int i) {
        this.zzk = i;
        zzz();
    }

    @Override // com.google.android.gms.internal.vision.zzif
    public final boolean zzt() throws IOException {
        return this.zzh == this.zzf;
    }

    @Override // com.google.android.gms.internal.vision.zzif
    public final int zzu() {
        return this.zzh - this.zzi;
    }

    private final byte zzaa() throws IOException {
        int i = this.zzh;
        if (i == this.zzf) {
            throw zzjk.zza();
        }
        byte[] bArr = this.zzd;
        this.zzh = i + 1;
        return bArr[i];
    }

    private final void zzf(int i) throws IOException {
        if (i >= 0) {
            int i2 = this.zzf;
            int i3 = this.zzh;
            if (i <= i2 - i3) {
                this.zzh = i3 + i;
                return;
            }
        }
        if (i < 0) {
            throw zzjk.zzb();
        }
        throw zzjk.zza();
    }
}
