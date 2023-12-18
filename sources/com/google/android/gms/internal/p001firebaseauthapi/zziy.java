package com.google.android.gms.internal.p001firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.List;
import java.util.logging.Logger;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zziy  reason: invalid package */
/* loaded from: classes.dex */
final class zziy implements zzbv {
    private static final Logger zza = Logger.getLogger(zziy.class.getName());
    private static final byte[] zzb = {0};

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzbv
    public final Class zza() {
        return zzbm.class;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzbv
    public final Class zzb() {
        return zzbm.class;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzbv
    public final /* bridge */ /* synthetic */ Object zzc(zzbu zzbuVar) throws GeneralSecurityException {
        for (List<zzbq> list : zzbuVar.zzd()) {
            for (zzbq zzbqVar : list) {
                if (zzbqVar.zzb() instanceof zziu) {
                    zziu zziuVar = (zziu) zzbqVar.zzb();
                    zzqv zzb2 = zzqv.zzb(zzbqVar.zzf());
                    if (!zzb2.equals(zziuVar.zzc())) {
                        String valueOf = String.valueOf(zziuVar.zzb());
                        String obj = zziuVar.zzc().toString();
                        String obj2 = zzb2.toString();
                        throw new GeneralSecurityException("Mac Key with parameters " + valueOf + " has wrong output prefix (" + obj + ") instead of (" + obj2 + ")");
                    }
                }
            }
        }
        return new zzix(zzbuVar, null);
    }
}
