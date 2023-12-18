package com.google.android.gms.internal.p001firebaseauthapi;

import java.security.SecureRandom;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzqq  reason: invalid package */
/* loaded from: classes.dex */
public final class zzqq {
    private static final ThreadLocal zza = new zzqp();

    public static byte[] zza(int i) {
        byte[] bArr = new byte[i];
        ((SecureRandom) zza.get()).nextBytes(bArr);
        return bArr;
    }
}
