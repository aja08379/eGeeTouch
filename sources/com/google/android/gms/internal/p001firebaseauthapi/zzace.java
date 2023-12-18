package com.google.android.gms.internal.p001firebaseauthapi;

import java.io.IOException;
import java.util.Arrays;
import kotlin.UByte;
import kotlin.jvm.internal.ByteCompanionObject;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzace  reason: invalid package */
/* loaded from: classes.dex */
public final class zzace extends zzacg {
    private final byte[] zze;
    private int zzf;
    private int zzg;
    private int zzh;
    private int zzi;
    private int zzj;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzace(byte[] bArr, int i, int i2, boolean z, zzacd zzacdVar) {
        super(null);
        this.zzj = Integer.MAX_VALUE;
        this.zze = bArr;
        this.zzf = i2;
        this.zzh = 0;
    }

    private final void zzv() {
        int i = this.zzf + this.zzg;
        this.zzf = i;
        int i2 = this.zzj;
        if (i <= i2) {
            this.zzg = 0;
            return;
        }
        int i3 = i - i2;
        this.zzg = i3;
        this.zzf = i - i3;
    }

    public final byte zza() throws IOException {
        int i = this.zzh;
        if (i == this.zzf) {
            throw zzadn.zzi();
        }
        byte[] bArr = this.zze;
        this.zzh = i + 1;
        return bArr[i];
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzacg
    public final int zzb() {
        return this.zzh;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzacg
    public final int zzc(int i) throws zzadn {
        if (i >= 0) {
            int i2 = i + this.zzh;
            if (i2 >= 0) {
                int i3 = this.zzj;
                if (i2 <= i3) {
                    this.zzj = i2;
                    zzv();
                    return i3;
                }
                throw zzadn.zzi();
            }
            throw zzadn.zzg();
        }
        throw zzadn.zzf();
    }

    public final int zzd() throws IOException {
        int i = this.zzh;
        if (this.zzf - i < 4) {
            throw zzadn.zzi();
        }
        byte[] bArr = this.zze;
        this.zzh = i + 4;
        return ((bArr[i + 3] & UByte.MAX_VALUE) << 24) | (bArr[i] & UByte.MAX_VALUE) | ((bArr[i + 1] & UByte.MAX_VALUE) << 8) | ((bArr[i + 2] & UByte.MAX_VALUE) << 16);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzacg
    public final int zzf() throws IOException {
        if (zzp()) {
            this.zzi = 0;
            return 0;
        }
        int zze = zze();
        this.zzi = zze;
        if ((zze >>> 3) != 0) {
            return zze;
        }
        throw zzadn.zzc();
    }

    public final long zzg() throws IOException {
        int i = this.zzh;
        if (this.zzf - i < 8) {
            throw zzadn.zzi();
        }
        byte[] bArr = this.zze;
        this.zzh = i + 8;
        return ((bArr[i + 7] & 255) << 56) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16) | ((bArr[i + 3] & 255) << 24) | ((bArr[i + 4] & 255) << 32) | ((bArr[i + 5] & 255) << 40) | ((bArr[i + 6] & 255) << 48);
    }

    final long zzi() throws IOException {
        long j = 0;
        for (int i = 0; i < 64; i += 7) {
            byte zza = zza();
            j |= (zza & ByteCompanionObject.MAX_VALUE) << i;
            if ((zza & ByteCompanionObject.MIN_VALUE) == 0) {
                return j;
            }
        }
        throw zzadn.zze();
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzacg
    public final zzacc zzj() throws IOException {
        int zze = zze();
        if (zze > 0) {
            int i = this.zzf;
            int i2 = this.zzh;
            if (zze <= i - i2) {
                zzacc zzo = zzacc.zzo(this.zze, i2, zze);
                this.zzh += zze;
                return zzo;
            }
        }
        if (zze != 0) {
            if (zze > 0) {
                int i3 = this.zzf;
                int i4 = this.zzh;
                if (zze <= i3 - i4) {
                    int i5 = zze + i4;
                    this.zzh = i5;
                    return zzacc.zzq(Arrays.copyOfRange(this.zze, i4, i5));
                }
            }
            if (zze <= 0) {
                throw zzadn.zzf();
            }
            throw zzadn.zzi();
        }
        return zzacc.zzb;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzacg
    public final String zzk() throws IOException {
        int zze = zze();
        if (zze > 0) {
            int i = this.zzf;
            int i2 = this.zzh;
            if (zze <= i - i2) {
                String str = new String(this.zze, i2, zze, zzadl.zzb);
                this.zzh += zze;
                return str;
            }
        }
        if (zze == 0) {
            return "";
        }
        if (zze < 0) {
            throw zzadn.zzf();
        }
        throw zzadn.zzi();
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzacg
    public final String zzl() throws IOException {
        int zze = zze();
        if (zze > 0) {
            int i = this.zzf;
            int i2 = this.zzh;
            if (zze <= i - i2) {
                String zzd = zzagc.zzd(this.zze, i2, zze);
                this.zzh += zze;
                return zzd;
            }
        }
        if (zze == 0) {
            return "";
        }
        if (zze <= 0) {
            throw zzadn.zzf();
        }
        throw zzadn.zzi();
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzacg
    public final void zzm(int i) throws zzadn {
        if (this.zzi != i) {
            throw zzadn.zzb();
        }
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzacg
    public final void zzn(int i) {
        this.zzj = i;
        zzv();
    }

    public final void zzo(int i) throws IOException {
        if (i >= 0) {
            int i2 = this.zzf;
            int i3 = this.zzh;
            if (i <= i2 - i3) {
                this.zzh = i3 + i;
                return;
            }
        }
        if (i < 0) {
            throw zzadn.zzf();
        }
        throw zzadn.zzi();
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzacg
    public final boolean zzp() throws IOException {
        return this.zzh == this.zzf;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzacg
    public final boolean zzq() throws IOException {
        return zzh() != 0;
    }

    /* JADX WARN: Code restructure failed: missing block: B:32:0x0067, code lost:
        if (r2[r3] >= 0) goto L13;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int zze() throws java.io.IOException {
        int r0;
        int r0 = r5.zzh;
        int r1 = r5.zzf;
        if (r1 != r0) {
            byte[] r2 = r5.zze;
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
        return (int) zzi();
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzacg
    public final boolean zzr(int i) throws IOException {
        int zzf;
        int i2 = i & 7;
        int i3 = 0;
        if (i2 == 0) {
            if (this.zzf - this.zzh < 10) {
                while (i3 < 10) {
                    if (zza() < 0) {
                        i3++;
                    }
                }
                throw zzadn.zze();
            }
            while (i3 < 10) {
                byte[] bArr = this.zze;
                int i4 = this.zzh;
                this.zzh = i4 + 1;
                if (bArr[i4] < 0) {
                    i3++;
                }
            }
            throw zzadn.zze();
            return true;
        } else if (i2 == 1) {
            zzo(8);
            return true;
        } else if (i2 == 2) {
            zzo(zze());
            return true;
        } else if (i2 != 3) {
            if (i2 != 4) {
                if (i2 == 5) {
                    zzo(4);
                    return true;
                }
                throw zzadn.zza();
            }
            return false;
        } else {
            do {
                zzf = zzf();
                if (zzf == 0) {
                    break;
                }
            } while (zzr(zzf));
            zzm(((i >>> 3) << 3) | 4);
            return true;
        }
    }

    public final long zzh() throws IOException {
        long j;
        long j2;
        long j3;
        long j4;
        int i;
        int i2 = this.zzh;
        int i3 = this.zzf;
        if (i3 != i2) {
            byte[] bArr = this.zze;
            int i4 = i2 + 1;
            byte b = bArr[i2];
            if (b >= 0) {
                this.zzh = i4;
                return b;
            } else if (i3 - i4 >= 9) {
                int i5 = i4 + 1;
                int i6 = b ^ (bArr[i4] << 7);
                if (i6 >= 0) {
                    int i7 = i5 + 1;
                    int i8 = i6 ^ (bArr[i5] << 14);
                    if (i8 >= 0) {
                        j = i8 ^ 16256;
                    } else {
                        i5 = i7 + 1;
                        int i9 = i8 ^ (bArr[i7] << 21);
                        if (i9 < 0) {
                            i = i9 ^ (-2080896);
                        } else {
                            i7 = i5 + 1;
                            long j5 = (bArr[i5] << 28) ^ i9;
                            if (j5 < 0) {
                                int i10 = i7 + 1;
                                long j6 = j5 ^ (bArr[i7] << 35);
                                if (j6 < 0) {
                                    j3 = -34093383808L;
                                } else {
                                    i7 = i10 + 1;
                                    j5 = j6 ^ (bArr[i10] << 42);
                                    if (j5 >= 0) {
                                        j4 = 4363953127296L;
                                    } else {
                                        i10 = i7 + 1;
                                        j6 = j5 ^ (bArr[i7] << 49);
                                        if (j6 < 0) {
                                            j3 = -558586000294016L;
                                        } else {
                                            i7 = i10 + 1;
                                            j = (j6 ^ (bArr[i10] << 56)) ^ 71499008037633920L;
                                            if (j < 0) {
                                                i10 = i7 + 1;
                                                if (bArr[i7] >= 0) {
                                                    j2 = j;
                                                    i5 = i10;
                                                    this.zzh = i5;
                                                    return j2;
                                                }
                                            }
                                        }
                                    }
                                }
                                j2 = j3 ^ j6;
                                i5 = i10;
                                this.zzh = i5;
                                return j2;
                            }
                            j4 = 266354560;
                            j = j5 ^ j4;
                        }
                    }
                    i5 = i7;
                    j2 = j;
                    this.zzh = i5;
                    return j2;
                }
                i = i6 ^ (-128);
                j2 = i;
                this.zzh = i5;
                return j2;
            }
        }
        return zzi();
    }
}
