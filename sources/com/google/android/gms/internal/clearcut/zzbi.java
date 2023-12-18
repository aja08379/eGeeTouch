package com.google.android.gms.internal.clearcut;

import java.io.IOException;
import java.nio.charset.Charset;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class zzbi extends zzbh {
    protected final byte[] zzfp;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbi(byte[] bArr) {
        this.zzfp = bArr;
    }

    @Override // com.google.android.gms.internal.clearcut.zzbb
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if ((obj instanceof zzbb) && size() == ((zzbb) obj).size()) {
            if (size() == 0) {
                return true;
            }
            if (obj instanceof zzbi) {
                zzbi zzbiVar = (zzbi) obj;
                int zzab = zzab();
                int zzab2 = zzbiVar.zzab();
                if (zzab == 0 || zzab2 == 0 || zzab == zzab2) {
                    return zza(zzbiVar, 0, size());
                }
                return false;
            }
            return obj.equals(this);
        }
        return false;
    }

    @Override // com.google.android.gms.internal.clearcut.zzbb
    public int size() {
        return this.zzfp.length;
    }

    @Override // com.google.android.gms.internal.clearcut.zzbb
    protected final int zza(int i, int i2, int i3) {
        return zzci.zza(i, this.zzfp, zzac(), i3);
    }

    @Override // com.google.android.gms.internal.clearcut.zzbb
    public final zzbb zza(int i, int i2) {
        int zzb = zzb(0, i2, size());
        return zzb == 0 ? zzbb.zzfi : new zzbe(this.zzfp, zzac(), zzb);
    }

    @Override // com.google.android.gms.internal.clearcut.zzbb
    protected final String zza(Charset charset) {
        return new String(this.zzfp, zzac(), size(), charset);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.clearcut.zzbb
    public final void zza(zzba zzbaVar) throws IOException {
        zzbaVar.zza(this.zzfp, zzac(), size());
    }

    @Override // com.google.android.gms.internal.clearcut.zzbh
    final boolean zza(zzbb zzbbVar, int i, int i2) {
        if (i2 > zzbbVar.size()) {
            throw new IllegalArgumentException(new StringBuilder(40).append("Length too large: ").append(i2).append(size()).toString());
        } else if (i2 > zzbbVar.size()) {
            throw new IllegalArgumentException(new StringBuilder(59).append("Ran off end of other: 0, ").append(i2).append(", ").append(zzbbVar.size()).toString());
        } else if (zzbbVar instanceof zzbi) {
            zzbi zzbiVar = (zzbi) zzbbVar;
            byte[] bArr = this.zzfp;
            byte[] bArr2 = zzbiVar.zzfp;
            int zzac = zzac() + i2;
            int zzac2 = zzac();
            int zzac3 = zzbiVar.zzac();
            while (zzac2 < zzac) {
                if (bArr[zzac2] != bArr2[zzac3]) {
                    return false;
                }
                zzac2++;
                zzac3++;
            }
            return true;
        } else {
            return zzbbVar.zza(0, i2).equals(zza(0, i2));
        }
    }

    @Override // com.google.android.gms.internal.clearcut.zzbb
    public final boolean zzaa() {
        int zzac = zzac();
        return zzff.zze(this.zzfp, zzac, size() + zzac);
    }

    protected int zzac() {
        return 0;
    }

    @Override // com.google.android.gms.internal.clearcut.zzbb
    public byte zzj(int i) {
        return this.zzfp[i];
    }
}
