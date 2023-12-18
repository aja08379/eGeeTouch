package com.google.android.gms.internal.p001firebaseauthapi;

import java.security.GeneralSecurityException;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzfh  reason: invalid package */
/* loaded from: classes.dex */
final class zzfh implements zzey {
    private final zzqv zza;
    private final zzqv zzb;

    private zzfh(byte[] bArr, byte[] bArr2) {
        this.zza = zzqv.zzb(bArr);
        this.zzb = zzqv.zzb(bArr2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzfh zzc(byte[] bArr, byte[] bArr2, int i) throws GeneralSecurityException {
        zzpx.zzf(zzpx.zzk(zzpx.zzl(i), 1, bArr2), zzpx.zzi(i, bArr));
        return new zzfh(bArr, bArr2);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzey
    public final zzqv zza() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzey
    public final zzqv zzb() {
        return this.zzb;
    }
}
