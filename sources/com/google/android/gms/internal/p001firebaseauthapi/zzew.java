package com.google.android.gms.internal.p001firebaseauthapi;

import java.security.GeneralSecurityException;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzew  reason: invalid package */
/* loaded from: classes.dex */
final class zzew implements zzav {
    private final zznn zza;
    private final zzex zzb;
    private final zzet zzc;
    private final zzes zzd;

    private zzew(zznn zznnVar, zzex zzexVar, zzes zzesVar, zzet zzetVar, byte[] bArr) {
        this.zza = zznnVar;
        this.zzb = zzexVar;
        this.zzd = zzesVar;
        this.zzc = zzetVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzew zza(zznn zznnVar) throws GeneralSecurityException {
        if (zznnVar.zzg().zzs()) {
            throw new IllegalArgumentException("HpkePublicKey.public_key is empty.");
        }
        zznh zzb = zznnVar.zzb();
        return new zzew(zznnVar, zzez.zzb(zzb), zzez.zzc(zzb), zzez.zza(zzb), null);
    }
}
