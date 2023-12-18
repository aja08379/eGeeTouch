package com.google.android.gms.internal.p001firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzej  reason: invalid package */
/* loaded from: classes.dex */
final class zzej implements zzau {
    private final zzbu zza;
    private final zzjd zzb;

    public zzej(zzbu zzbuVar) {
        this.zza = zzbuVar;
        this.zzb = zzbuVar.zzf() ? zzgm.zza().zzb().zza(zzgj.zza(zzbuVar), "hybrid_decrypt", "decrypt") : zzgj.zza;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzau
    public final byte[] zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        Logger logger;
        int length = bArr.length;
        if (length > 5) {
            byte[] copyOfRange = Arrays.copyOfRange(bArr, 0, 5);
            byte[] copyOfRange2 = Arrays.copyOfRange(bArr, 5, length);
            for (zzbq zzbqVar : this.zza.zze(copyOfRange)) {
                try {
                    byte[] zza = ((zzau) zzbqVar.zze()).zza(copyOfRange2, null);
                    zzbqVar.zza();
                    int length2 = copyOfRange2.length;
                    return zza;
                } catch (GeneralSecurityException e) {
                    logger = zzek.zza;
                    logger.logp(Level.INFO, "com.google.crypto.tink.hybrid.HybridDecryptWrapper$WrappedHybridDecrypt", "decrypt", "ciphertext prefix matches a key, but cannot decrypt: ".concat(String.valueOf(e.toString())));
                }
            }
        }
        for (zzbq zzbqVar2 : this.zza.zze(zzas.zza)) {
            try {
                byte[] zza2 = ((zzau) zzbqVar2.zze()).zza(bArr, null);
                zzbqVar2.zza();
                int length3 = bArr.length;
                return zza2;
            } catch (GeneralSecurityException unused) {
            }
        }
        throw new GeneralSecurityException("decryption failed");
    }
}
