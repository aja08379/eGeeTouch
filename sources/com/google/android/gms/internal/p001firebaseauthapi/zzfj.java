package com.google.android.gms.internal.p001firebaseauthapi;

import java.security.GeneralSecurityException;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzfj  reason: invalid package */
/* loaded from: classes.dex */
final class zzfj implements zzey {
    private final zzqv zza;
    private final zzqv zzb;

    private zzfj(byte[] bArr, byte[] bArr2) {
        this.zza = zzqv.zzb(bArr);
        this.zzb = zzqv.zzb(bArr2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzfj zzc(byte[] bArr) throws GeneralSecurityException {
        return new zzfj(bArr, zzqt.zzb(bArr));
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
