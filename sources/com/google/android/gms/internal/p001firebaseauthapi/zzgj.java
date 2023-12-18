package com.google.android.gms.internal.p001firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.List;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzgj  reason: invalid package */
/* loaded from: classes.dex */
public final class zzgj {
    public static final zzjd zza = new zzgi(null);

    public static zzjj zza(zzbu zzbuVar) {
        zzbe zzbeVar;
        zzjf zzjfVar = new zzjf();
        zzjfVar.zzb(zzbuVar.zzb());
        for (List<zzbq> list : zzbuVar.zzd()) {
            for (zzbq zzbqVar : list) {
                int zzg = zzbqVar.zzg() - 2;
                if (zzg == 1) {
                    zzbeVar = zzbe.zza;
                } else if (zzg == 2) {
                    zzbeVar = zzbe.zzb;
                } else if (zzg != 3) {
                    throw new IllegalStateException("Unknown key status");
                } else {
                    zzbeVar = zzbe.zzc;
                }
                zzjfVar.zza(zzbeVar, zzbqVar.zza(), zzbqVar.zzc());
            }
        }
        if (zzbuVar.zza() != null) {
            zzjfVar.zzc(zzbuVar.zza().zza());
        }
        try {
            return zzjfVar.zzd();
        } catch (GeneralSecurityException e) {
            throw new IllegalStateException(e);
        }
    }
}
