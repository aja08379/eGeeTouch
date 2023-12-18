package com.google.android.gms.internal.p001firebaseauthapi;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzmw */
/* loaded from: classes.dex */
public final class zzmw extends zzadf implements zzael {
    private static final zzmw zzb;
    private zzmz zzd;
    private int zze;
    private int zzf;

    static {
        zzmw zzmwVar = new zzmw();
        zzb = zzmwVar;
        zzadf.zzG(zzmw.class, zzmwVar);
    }

    private zzmw() {
    }

    public static zzmv zzb() {
        return (zzmv) zzb.zzt();
    }

    public static zzmw zzd() {
        return zzb;
    }

    public static zzmw zze(zzacc zzaccVar, zzacs zzacsVar) throws zzadn {
        return (zzmw) zzadf.zzx(zzb, zzaccVar, zzacsVar);
    }

    public static /* synthetic */ void zzg(zzmw zzmwVar, zzmz zzmzVar) {
        zzmzVar.getClass();
        zzmwVar.zzd = zzmzVar;
    }

    public final int zza() {
        return this.zze;
    }

    public final zzmz zzf() {
        zzmz zzmzVar = this.zzd;
        return zzmzVar == null ? zzmz.zzd() : zzmzVar;
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
                    return new zzmv(null);
                }
                return new zzmw();
            }
            return zzD(zzb, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\t\u0002\u000b\u0003\u000b", new Object[]{"zzd", "zze", "zzf"});
        }
        return (byte) 1;
    }
}
