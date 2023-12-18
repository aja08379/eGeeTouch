package com.google.android.gms.internal.p001firebaseauthapi;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzkl  reason: invalid package */
/* loaded from: classes.dex */
public final class zzkl extends zzadf implements zzael {
    private static final zzkl zzb;
    private int zzd;
    private zzkr zze;
    private zzacc zzf = zzacc.zzb;

    static {
        zzkl zzklVar = new zzkl();
        zzb = zzklVar;
        zzadf.zzG(zzkl.class, zzklVar);
    }

    private zzkl() {
    }

    public static zzkk zzb() {
        return (zzkk) zzb.zzt();
    }

    public static zzkl zzd(zzacc zzaccVar, zzacs zzacsVar) throws zzadn {
        return (zzkl) zzadf.zzx(zzb, zzaccVar, zzacsVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzh(zzkl zzklVar, zzkr zzkrVar) {
        zzkrVar.getClass();
        zzklVar.zze = zzkrVar;
    }

    public final int zza() {
        return this.zzd;
    }

    public final zzkr zze() {
        zzkr zzkrVar = this.zze;
        return zzkrVar == null ? zzkr.zzd() : zzkrVar;
    }

    public final zzacc zzf() {
        return this.zzf;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzadf
    public final Object zzj(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 != 0) {
            if (i2 != 2) {
                if (i2 != 3) {
                    if (i2 != 4) {
                        if (i2 != 5) {
                            return null;
                        }
                        return zzb;
                    }
                    return new zzkk(null);
                }
                return new zzkl();
            }
            return zzD(zzb, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u000b\u0002\t\u0003\n", new Object[]{"zzd", "zze", "zzf"});
        }
        return (byte) 1;
    }
}
