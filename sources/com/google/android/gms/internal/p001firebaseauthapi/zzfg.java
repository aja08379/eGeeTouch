package com.google.android.gms.internal.p001firebaseauthapi;

import java.security.GeneralSecurityException;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzfg  reason: invalid package */
/* loaded from: classes.dex */
final class zzfg implements zzex {
    private final zzes zza;
    private final int zzb;

    private zzfg(zzes zzesVar, int i) {
        this.zza = zzesVar;
        this.zzb = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzfg zzc(int i) throws GeneralSecurityException {
        int i2 = i - 1;
        if (i2 != 0) {
            if (i2 != 1) {
                return new zzfg(new zzes("HmacSha512"), 3);
            }
            return new zzfg(new zzes("HmacSha384"), 2);
        }
        return new zzfg(new zzes("HmacSha256"), 1);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzex
    public final byte[] zza(byte[] bArr, zzey zzeyVar) throws GeneralSecurityException {
        byte[] zzh = zzpx.zzh(zzpx.zzi(this.zzb, zzeyVar.zza().zzc()), zzpx.zzk(zzpx.zzl(this.zzb), 1, bArr));
        byte[] zzc = zzpp.zzc(bArr, zzeyVar.zzb().zzc());
        byte[] zzd = zzff.zzd(zzb());
        zzes zzesVar = this.zza;
        return zzesVar.zzb(null, zzh, "eae_prk", zzc, "shared_secret", zzd, zzesVar.zza());
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzex
    public final byte[] zzb() throws GeneralSecurityException {
        int i = this.zzb - 1;
        if (i != 0) {
            return i != 1 ? zzff.zze : zzff.zzd;
        }
        return zzff.zzc;
    }
}
