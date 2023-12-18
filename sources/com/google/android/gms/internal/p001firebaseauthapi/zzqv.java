package com.google.android.gms.internal.p001firebaseauthapi;

import java.util.Arrays;
import java.util.Objects;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzqv  reason: invalid package */
/* loaded from: classes.dex */
public final class zzqv {
    private final byte[] zza;

    private zzqv(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[i2];
        this.zza = bArr2;
        System.arraycopy(bArr, 0, bArr2, 0, i2);
    }

    public static zzqv zzb(byte[] bArr) {
        Objects.requireNonNull(bArr, "data must be non-null");
        return new zzqv(bArr, 0, bArr.length);
    }

    public final boolean equals(Object obj) {
        if (obj instanceof zzqv) {
            return Arrays.equals(((zzqv) obj).zza, this.zza);
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(this.zza);
    }

    public final String toString() {
        String zza = zzqj.zza(this.zza);
        return "Bytes(" + zza + ")";
    }

    public final int zza() {
        return this.zza.length;
    }

    public final byte[] zzc() {
        byte[] bArr = this.zza;
        int length = bArr.length;
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, 0, bArr2, 0, length);
        return bArr2;
    }
}
