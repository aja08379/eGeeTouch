package com.google.android.gms.internal.p001firebaseauthapi;

import java.security.GeneralSecurityException;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzez  reason: invalid package */
/* loaded from: classes.dex */
final class zzez {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzet zza(zznh zznhVar) throws GeneralSecurityException {
        if (zznhVar.zzd() == 3) {
            return new zzeq(16);
        }
        if (zznhVar.zzd() == 4) {
            return new zzeq(32);
        }
        if (zznhVar.zzd() == 5) {
            return new zzer();
        }
        throw new IllegalArgumentException("Unrecognized HPKE AEAD identifier");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzex zzb(zznh zznhVar) throws GeneralSecurityException {
        if (zznhVar.zzf() == 3) {
            return new zzfi(new zzes("HmacSha256"));
        }
        if (zznhVar.zzf() == 4) {
            return zzfg.zzc(1);
        }
        if (zznhVar.zzf() == 5) {
            return zzfg.zzc(2);
        }
        if (zznhVar.zzf() == 6) {
            return zzfg.zzc(3);
        }
        throw new IllegalArgumentException("Unrecognized HPKE KEM identifier");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzes zzc(zznh zznhVar) {
        if (zznhVar.zze() == 3) {
            return new zzes("HmacSha256");
        }
        if (zznhVar.zze() == 4) {
            return new zzes("HmacSha384");
        }
        if (zznhVar.zze() == 5) {
            return new zzes("HmacSha512");
        }
        throw new IllegalArgumentException("Unrecognized HPKE KDF identifier");
    }
}
