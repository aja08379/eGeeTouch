package com.google.android.gms.internal.p001firebaseauthapi;

import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzdo  reason: invalid package */
/* loaded from: classes.dex */
public final class zzdo extends zzdp {
    public zzdo(byte[] bArr) throws GeneralSecurityException {
        super(bArr);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzdp
    final zzdn zza(byte[] bArr, int i) throws InvalidKeyException {
        return new zzdm(bArr, i);
    }
}
