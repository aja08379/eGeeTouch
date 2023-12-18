package com.google.android.gms.internal.p001firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Arrays;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzfi  reason: invalid package */
/* loaded from: classes.dex */
final class zzfi implements zzex {
    private final zzes zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfi(zzes zzesVar) {
        this.zza = zzesVar;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzex
    public final byte[] zza(byte[] bArr, zzey zzeyVar) throws GeneralSecurityException {
        byte[] zza = zzqt.zza(zzeyVar.zza().zzc(), bArr);
        byte[] zzc = zzpp.zzc(bArr, zzeyVar.zzb().zzc());
        byte[] zzd = zzff.zzd(zzff.zzb);
        zzes zzesVar = this.zza;
        return zzesVar.zzb(null, zza, "eae_prk", zzc, "shared_secret", zzd, zzesVar.zza());
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzex
    public final byte[] zzb() throws GeneralSecurityException {
        if (Arrays.equals(this.zza.zzc(), zzff.zzf)) {
            return zzff.zzb;
        }
        throw new GeneralSecurityException("Could not determine HPKE KEM ID");
    }
}
