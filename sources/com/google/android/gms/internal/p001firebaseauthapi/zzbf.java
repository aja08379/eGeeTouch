package com.google.android.gms.internal.p001firebaseauthapi;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzbf  reason: invalid package */
/* loaded from: classes.dex */
public final class zzbf {
    private final zznx zza;

    private zzbf(zznx zznxVar) {
        this.zza = zznxVar;
    }

    public static zzbf zze(String str, byte[] bArr, int i) {
        zzoy zzoyVar;
        zznw zza = zznx.zza();
        zza.zzb(str);
        zza.zzc(zzacc.zzn(bArr));
        int i2 = i - 1;
        if (i2 == 0) {
            zzoyVar = zzoy.TINK;
        } else if (i2 != 1) {
            zzoyVar = i2 != 2 ? zzoy.CRUNCHY : zzoy.RAW;
        } else {
            zzoyVar = zzoy.LEGACY;
        }
        zza.zza(zzoyVar);
        return new zzbf((zznx) zza.zzi());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zznx zza() {
        return this.zza;
    }

    public final String zzb() {
        return this.zza.zzf();
    }

    public final byte[] zzc() {
        return this.zza.zze().zzt();
    }

    public final int zzd() {
        zzoy zzd = this.zza.zzd();
        zzoy zzoyVar = zzoy.UNKNOWN_PREFIX;
        int ordinal = zzd.ordinal();
        int i = 1;
        if (ordinal != 1) {
            i = 2;
            if (ordinal != 2) {
                i = 3;
                if (ordinal != 3) {
                    if (ordinal == 4) {
                        return 4;
                    }
                    throw new IllegalArgumentException("Unknown output prefix type");
                }
            }
        }
        return i;
    }
}
