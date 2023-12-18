package com.google.android.gms.internal.p001firebaseauthapi;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzjw */
/* loaded from: classes.dex */
public final class zzjw extends zzadf implements zzael {
    private static final zzjw zzb;
    private int zzd;
    private zzkc zze;
    private zzmt zzf;

    static {
        zzjw zzjwVar = new zzjw();
        zzb = zzjwVar;
        zzadf.zzG(zzjw.class, zzjwVar);
    }

    private zzjw() {
    }

    public static zzjv zzb() {
        return (zzjv) zzb.zzt();
    }

    public static zzjw zzd(zzacc zzaccVar, zzacs zzacsVar) throws zzadn {
        return (zzjw) zzadf.zzx(zzb, zzaccVar, zzacsVar);
    }

    public static /* synthetic */ void zzh(zzjw zzjwVar, zzkc zzkcVar) {
        zzkcVar.getClass();
        zzjwVar.zze = zzkcVar;
    }

    public static /* synthetic */ void zzi(zzjw zzjwVar, zzmt zzmtVar) {
        zzmtVar.getClass();
        zzjwVar.zzf = zzmtVar;
    }

    public final int zza() {
        return this.zzd;
    }

    public final zzkc zze() {
        zzkc zzkcVar = this.zze;
        return zzkcVar == null ? zzkc.zzd() : zzkcVar;
    }

    public final zzmt zzf() {
        zzmt zzmtVar = this.zzf;
        return zzmtVar == null ? zzmt.zzd() : zzmtVar;
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
                    return new zzjv(null);
                }
                return new zzjw();
            }
            return zzD(zzb, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u000b\u0002\t\u0003\t", new Object[]{"zzd", "zze", "zzf"});
        }
        return (byte) 1;
    }
}
