package com.google.android.gms.internal.p001firebaseauthapi;

import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzeq  reason: invalid package */
/* loaded from: classes.dex */
final class zzeq implements zzet {
    private final int zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzeq(int i) throws InvalidAlgorithmParameterException {
        if (i == 16 || i == 32) {
            this.zza = i;
            return;
        }
        throw new InvalidAlgorithmParameterException("Unsupported key length: " + i);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzet
    public final int zza() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzet
    public final byte[] zzc(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) throws GeneralSecurityException {
        int length = bArr.length;
        if (length != this.zza) {
            throw new InvalidAlgorithmParameterException("Unexpected key length: " + length);
        }
        return new zzdl(bArr, false).zza(bArr2, bArr3, bArr4);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzet
    public final byte[] zzb() throws GeneralSecurityException {
        int i = this.zza;
        if (i != 16) {
            if (i == 32) {
                return zzff.zzj;
            }
            throw new GeneralSecurityException("Could not determine HPKE AEAD ID");
        }
        return zzff.zzi;
    }
}
