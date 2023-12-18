package com.google.android.gms.internal.p001firebaseauthapi;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzjz */
/* loaded from: classes.dex */
public final class zzjz extends zzadf implements zzael {
    private static final zzjz zzb;
    private zzkf zzd;
    private zzmw zze;

    static {
        zzjz zzjzVar = new zzjz();
        zzb = zzjzVar;
        zzadf.zzG(zzjz.class, zzjzVar);
    }

    private zzjz() {
    }

    public static zzjy zza() {
        return (zzjy) zzb.zzt();
    }

    public static zzjz zzc(zzacc zzaccVar, zzacs zzacsVar) throws zzadn {
        return (zzjz) zzadf.zzx(zzb, zzaccVar, zzacsVar);
    }

    public static /* synthetic */ void zzf(zzjz zzjzVar, zzkf zzkfVar) {
        zzkfVar.getClass();
        zzjzVar.zzd = zzkfVar;
    }

    public static /* synthetic */ void zzg(zzjz zzjzVar, zzmw zzmwVar) {
        zzmwVar.getClass();
        zzjzVar.zze = zzmwVar;
    }

    public final zzkf zzd() {
        zzkf zzkfVar = this.zzd;
        return zzkfVar == null ? zzkf.zzd() : zzkfVar;
    }

    public final zzmw zze() {
        zzmw zzmwVar = this.zze;
        return zzmwVar == null ? zzmw.zzd() : zzmwVar;
    }

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
                    return new zzjy(null);
                }
                return new zzjz();
            }
            return zzD(zzb, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\t\u0002\t", new Object[]{"zzd", "zze"});
        }
        return (byte) 1;
    }
}
