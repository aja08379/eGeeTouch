package com.google.android.gms.internal.clearcut;

import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Iterator;
/* loaded from: classes.dex */
public abstract class zzbb implements Serializable, Iterable<Byte> {
    public static final zzbb zzfi = new zzbi(zzci.zzkt);
    private static final zzbf zzfj;
    private int zzfk = 0;

    static {
        zzfj = zzaw.zzx() ? new zzbj(null) : new zzbd(null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzb(int i, int i2, int i3) {
        int i4 = i2 - i;
        if ((i | i2 | i4 | (i3 - i2)) < 0) {
            if (i >= 0) {
                if (i2 < i) {
                    throw new IndexOutOfBoundsException(new StringBuilder(66).append("Beginning index larger than ending index: ").append(i).append(", ").append(i2).toString());
                }
                throw new IndexOutOfBoundsException(new StringBuilder(37).append("End index: ").append(i2).append(" >= ").append(i3).toString());
            }
            throw new IndexOutOfBoundsException(new StringBuilder(32).append("Beginning index: ").append(i).append(" < 0").toString());
        }
        return i4;
    }

    public static zzbb zzb(byte[] bArr, int i, int i2) {
        return new zzbi(zzfj.zzc(bArr, i, i2));
    }

    public static zzbb zzf(String str) {
        return new zzbi(str.getBytes(zzci.UTF_8));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzbg zzk(int i) {
        return new zzbg(i, null);
    }

    public abstract boolean equals(Object obj);

    public final int hashCode() {
        int i = this.zzfk;
        if (i == 0) {
            int size = size();
            i = zza(size, 0, size);
            if (i == 0) {
                i = 1;
            }
            this.zzfk = i;
        }
        return i;
    }

    @Override // java.lang.Iterable
    public /* synthetic */ Iterator<Byte> iterator() {
        return new zzbc(this);
    }

    public abstract int size();

    public final String toString() {
        return String.format("<ByteString@%s size=%d>", Integer.toHexString(System.identityHashCode(this)), Integer.valueOf(size()));
    }

    protected abstract int zza(int i, int i2, int i3);

    public abstract zzbb zza(int i, int i2);

    protected abstract String zza(Charset charset);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zza(zzba zzbaVar) throws IOException;

    public abstract boolean zzaa();

    /* JADX INFO: Access modifiers changed from: protected */
    public final int zzab() {
        return this.zzfk;
    }

    public abstract byte zzj(int i);

    public final String zzz() {
        return size() == 0 ? "" : zza(zzci.UTF_8);
    }
}
