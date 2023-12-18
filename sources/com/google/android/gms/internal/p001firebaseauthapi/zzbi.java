package com.google.android.gms.internal.p001firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Iterator;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzbi  reason: invalid package */
/* loaded from: classes.dex */
public final class zzbi {
    private final zzoc zza;

    private zzbi(zzoc zzocVar) {
        this.zza = zzocVar;
    }

    public static zzbi zze() {
        return new zzbi(zzof.zzc());
    }

    public static zzbi zzf(zzbh zzbhVar) {
        return new zzbi((zzoc) zzbhVar.zzc().zzu());
    }

    private final synchronized int zzg() {
        int zza;
        zza = zzhj.zza();
        while (zzj(zza)) {
            zza = zzhj.zza();
        }
        return zza;
    }

    private final synchronized zzoe zzh(zzns zznsVar, zzoy zzoyVar) throws GeneralSecurityException {
        zzod zzc;
        int zzg = zzg();
        if (zzoyVar == zzoy.UNKNOWN_PREFIX) {
            throw new GeneralSecurityException("unknown output prefix type");
        }
        zzc = zzoe.zzc();
        zzc.zza(zznsVar);
        zzc.zzb(zzg);
        zzc.zzd(3);
        zzc.zzc(zzoyVar);
        return (zzoe) zzc.zzi();
    }

    private final synchronized zzoe zzi(zznx zznxVar) throws GeneralSecurityException {
        return zzh(zzbz.zzc(zznxVar), zznxVar.zzd());
    }

    private final synchronized boolean zzj(int i) {
        boolean z;
        Iterator it = this.zza.zze().iterator();
        while (true) {
            if (!it.hasNext()) {
                z = false;
                break;
            } else if (((zzoe) it.next()).zza() == i) {
                z = true;
                break;
            }
        }
        return z;
    }

    @Deprecated
    public final synchronized int zza(zznx zznxVar, boolean z) throws GeneralSecurityException {
        zzoe zzi;
        zzi = zzi(zznxVar);
        this.zza.zzb(zzi);
        return zzi.zza();
    }

    public final synchronized zzbh zzb() throws GeneralSecurityException {
        return zzbh.zza((zzof) this.zza.zzi());
    }

    public final synchronized zzbi zzc(zzbf zzbfVar) throws GeneralSecurityException {
        zza(zzbfVar.zza(), false);
        return this;
    }

    public final synchronized zzbi zzd(int i) throws GeneralSecurityException {
        for (int i2 = 0; i2 < this.zza.zza(); i2++) {
            zzoe zzd = this.zza.zzd(i2);
            if (zzd.zza() == i) {
                if (zzd.zzk() != 3) {
                    throw new GeneralSecurityException("cannot set key as primary because it's not enabled: " + i);
                } else {
                    this.zza.zzc(i);
                }
            }
        }
        throw new GeneralSecurityException("key not found: " + i);
        return this;
    }
}
