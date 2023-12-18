package com.google.android.gms.internal.clearcut;
/* loaded from: classes.dex */
final class zzbe extends zzbi {
    private final int zzfm;
    private final int zzfn;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbe(byte[] bArr, int i, int i2) {
        super(bArr);
        zzb(i, i + i2, bArr.length);
        this.zzfm = i;
        this.zzfn = i2;
    }

    @Override // com.google.android.gms.internal.clearcut.zzbi, com.google.android.gms.internal.clearcut.zzbb
    public final int size() {
        return this.zzfn;
    }

    @Override // com.google.android.gms.internal.clearcut.zzbi
    protected final int zzac() {
        return this.zzfm;
    }

    @Override // com.google.android.gms.internal.clearcut.zzbi, com.google.android.gms.internal.clearcut.zzbb
    public final byte zzj(int i) {
        int size = size();
        if (((size - (i + 1)) | i) < 0) {
            if (i < 0) {
                throw new ArrayIndexOutOfBoundsException(new StringBuilder(22).append("Index < 0: ").append(i).toString());
            }
            throw new ArrayIndexOutOfBoundsException(new StringBuilder(40).append("Index > length: ").append(i).append(", ").append(size).toString());
        }
        return this.zzfp[this.zzfm + i];
    }
}
