package com.google.android.gms.internal.p001firebaseauthapi;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzkf */
/* loaded from: classes.dex */
public final class zzkf extends zzadf implements zzael {
    private static final zzkf zzb;
    private zzki zzd;
    private int zze;

    static {
        zzkf zzkfVar = new zzkf();
        zzb = zzkfVar;
        zzadf.zzG(zzkf.class, zzkfVar);
    }

    private zzkf() {
    }

    public static zzke zzb() {
        return (zzke) zzb.zzt();
    }

    public static zzkf zzd() {
        return zzb;
    }

    public static zzkf zze(zzacc zzaccVar, zzacs zzacsVar) throws zzadn {
        return (zzkf) zzadf.zzx(zzb, zzaccVar, zzacsVar);
    }

    public static /* synthetic */ void zzg(zzkf zzkfVar, zzki zzkiVar) {
        zzkiVar.getClass();
        zzkfVar.zzd = zzkiVar;
    }

    public final int zza() {
        return this.zze;
    }

    public final zzki zzf() {
        zzki zzkiVar = this.zzd;
        return zzkiVar == null ? zzki.zzd() : zzkiVar;
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
                    return new zzke(null);
                }
                return new zzkf();
            }
            return zzD(zzb, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\t\u0002\u000b", new Object[]{"zzd", "zze"});
        }
        return (byte) 1;
    }
}
