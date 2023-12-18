package com.google.android.gms.internal.p001firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzix  reason: invalid package */
/* loaded from: classes.dex */
final class zzix implements zzbm {
    private final zzbu zza;
    private final zzjd zzb;
    private final zzjd zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzix(zzbu zzbuVar, zziw zziwVar) {
        zzjd zzjdVar;
        this.zza = zzbuVar;
        if (zzbuVar.zzf()) {
            zzje zzb = zzgm.zza().zzb();
            zzjj zza = zzgj.zza(zzbuVar);
            this.zzb = zzb.zza(zza, "mac", "compute");
            zzjdVar = zzb.zza(zza, "mac", "verify");
        } else {
            zzjdVar = zzgj.zza;
            this.zzb = zzjdVar;
        }
        this.zzc = zzjdVar;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzbm
    public final void zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        byte[] bArr3;
        Logger logger;
        byte[] bArr4;
        int length = bArr.length;
        if (length <= 5) {
            throw new GeneralSecurityException("tag too short");
        }
        byte[] copyOf = Arrays.copyOf(bArr, 5);
        byte[] copyOfRange = Arrays.copyOfRange(bArr, 5, length);
        for (zzbq zzbqVar : this.zza.zze(copyOf)) {
            if (zzbqVar.zzd().equals(zzoy.LEGACY)) {
                bArr4 = zziy.zzb;
                bArr3 = zzpp.zzc(bArr2, bArr4);
            } else {
                bArr3 = bArr2;
            }
            try {
                ((zzbm) zzbqVar.zze()).zza(copyOfRange, bArr3);
                zzbqVar.zza();
                return;
            } catch (GeneralSecurityException e) {
                logger = zziy.zza;
                logger.logp(Level.INFO, "com.google.crypto.tink.mac.MacWrapper$WrappedMac", "verifyMac", "tag prefix matches a key, but cannot verify: ".concat(e.toString()));
            }
        }
        for (zzbq zzbqVar2 : this.zza.zze(zzas.zza)) {
            try {
                ((zzbm) zzbqVar2.zze()).zza(bArr, bArr2);
                zzbqVar2.zza();
                return;
            } catch (GeneralSecurityException unused) {
            }
        }
        throw new GeneralSecurityException("invalid MAC");
    }
}
