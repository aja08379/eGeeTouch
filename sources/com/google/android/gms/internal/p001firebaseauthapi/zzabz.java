package com.google.android.gms.internal.p001firebaseauthapi;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Objects;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzabz  reason: invalid package */
/* loaded from: classes.dex */
public class zzabz extends zzaby {
    protected final byte[] zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzabz(byte[] bArr) {
        Objects.requireNonNull(bArr);
        this.zza = bArr;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzacc
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if ((obj instanceof zzacc) && zzd() == ((zzacc) obj).zzd()) {
            if (zzd() == 0) {
                return true;
            }
            if (obj instanceof zzabz) {
                zzabz zzabzVar = (zzabz) obj;
                int zzm = zzm();
                int zzm2 = zzabzVar.zzm();
                if (zzm == 0 || zzm2 == 0 || zzm == zzm2) {
                    int zzd = zzd();
                    if (zzd > zzabzVar.zzd()) {
                        throw new IllegalArgumentException("Length too large: " + zzd + zzd());
                    } else if (zzd > zzabzVar.zzd()) {
                        throw new IllegalArgumentException("Ran off end of other: 0, " + zzd + ", " + zzabzVar.zzd());
                    } else if (zzabzVar instanceof zzabz) {
                        byte[] bArr = this.zza;
                        byte[] bArr2 = zzabzVar.zza;
                        zzabzVar.zzc();
                        int i = 0;
                        int i2 = 0;
                        while (i < zzd) {
                            if (bArr[i] != bArr2[i2]) {
                                return false;
                            }
                            i++;
                            i2++;
                        }
                        return true;
                    } else {
                        return zzabzVar.zzg(0, zzd).equals(zzg(0, zzd));
                    }
                }
                return false;
            }
            return obj.equals(this);
        }
        return false;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzacc
    public byte zza(int i) {
        return this.zza[i];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzacc
    public byte zzb(int i) {
        return this.zza[i];
    }

    protected int zzc() {
        return 0;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzacc
    public int zzd() {
        return this.zza.length;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzacc
    protected void zze(byte[] bArr, int i, int i2, int i3) {
        System.arraycopy(this.zza, 0, bArr, 0, i3);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzacc
    protected final int zzf(int i, int i2, int i3) {
        return zzadl.zzd(i, this.zza, 0, i3);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzacc
    public final zzacc zzg(int i, int i2) {
        int zzl = zzl(0, i2, zzd());
        return zzl == 0 ? zzacc.zzb : new zzabw(this.zza, 0, zzl);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzacc
    public final zzacg zzh() {
        return zzacg.zzu(this.zza, 0, zzd(), true);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzacc
    protected final String zzi(Charset charset) {
        return new String(this.zza, 0, zzd(), charset);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzacc
    public final void zzj(zzabs zzabsVar) throws IOException {
        zzabsVar.zza(this.zza, 0, zzd());
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzacc
    public final boolean zzk() {
        return zzagc.zzf(this.zza, 0, zzd());
    }
}
