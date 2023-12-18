package com.google.android.gms.internal.p001firebaseauthapi;

import java.security.GeneralSecurityException;
import java.security.interfaces.ECPrivateKey;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzpt  reason: invalid package */
/* loaded from: classes.dex */
public final class zzpt implements zzau {
    private static final byte[] zza = new byte[0];
    private final ECPrivateKey zzb;
    private final zzpv zzc;
    private final String zzd;
    private final byte[] zze;
    private final zzps zzf;
    private final int zzg;

    public zzpt(ECPrivateKey eCPrivateKey, byte[] bArr, String str, int i, zzps zzpsVar) throws GeneralSecurityException {
        this.zzb = eCPrivateKey;
        this.zzc = new zzpv(eCPrivateKey);
        this.zze = bArr;
        this.zzd = str;
        this.zzg = i;
        this.zzf = zzpsVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0020  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0049  */
    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzau
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final byte[] zza(byte[] r10, byte[] r11) throws java.security.GeneralSecurityException {
        int r11;
        int r0;
        java.security.spec.EllipticCurve r11 = r9.zzb.getParams().getCurve();
        int r0 = r9.zzg;
        int r11 = com.google.android.gms.internal.p001firebaseauthapi.zzpx.zza(r11);
        int r0 = r0 - 1;
        if (r0 == 0) {
            r11 += r11;
        } else if (r0 == 2) {
            r11 = r11 + r11;
            r0 = r10.length;
            if (r0 >= r11) {
                throw new java.security.GeneralSecurityException("ciphertext too short");
            }
            return r9.zzf.zzb(r9.zzc.zza(java.util.Arrays.copyOfRange(r10, 0, r11), r9.zzd, r9.zze, null, r9.zzf.zza(), r9.zzg)).zza(java.util.Arrays.copyOfRange(r10, r11, r0), com.google.android.gms.internal.p001firebaseauthapi.zzpt.zza);
        }
        r11 = r11 + 1;
        r0 = r10.length;
        if (r0 >= r11) {
        }
    }
}
