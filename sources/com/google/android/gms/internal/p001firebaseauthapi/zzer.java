package com.google.android.gms.internal.p001firebaseauthapi;

import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzer  reason: invalid package */
/* loaded from: classes.dex */
final class zzer implements zzet {
    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzet
    public final int zza() {
        return 32;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzet
    public final byte[] zzb() {
        return zzff.zzk;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzet
    public final byte[] zzc(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) throws GeneralSecurityException {
        if (bArr.length != 32) {
            throw new InvalidAlgorithmParameterException("Unexpected key length: 32");
        }
        return new zzdo(bArr).zzc(bArr2, bArr3, bArr4);
    }
}
