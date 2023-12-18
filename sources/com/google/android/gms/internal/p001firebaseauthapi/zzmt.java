package com.google.android.gms.internal.p001firebaseauthapi;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzmt  reason: invalid package */
/* loaded from: classes.dex */
public final class zzmt extends zzadf implements zzael {
    private static final zzmt zzb;
    private int zzd;
    private zzmz zze;
    private zzacc zzf = zzacc.zzb;

    static {
        zzmt zzmtVar = new zzmt();
        zzb = zzmtVar;
        zzadf.zzG(zzmt.class, zzmtVar);
    }

    private zzmt() {
    }

    public static zzms zzb() {
        return (zzms) zzb.zzt();
    }

    public static zzmt zzd() {
        return zzb;
    }

    public static zzmt zze(zzacc zzaccVar, zzacs zzacsVar) throws zzadn {
        return (zzmt) zzadf.zzx(zzb, zzaccVar, zzacsVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzi(zzmt zzmtVar, zzmz zzmzVar) {
        zzmzVar.getClass();
        zzmtVar.zze = zzmzVar;
    }

    public final int zza() {
        return this.zzd;
    }

    public final zzmz zzf() {
        zzmz zzmzVar = this.zze;
        return zzmzVar == null ? zzmz.zzd() : zzmzVar;
    }

    public final zzacc zzg() {
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
                    return new zzms(null);
                }
                return new zzmt();
            }
            return zzD(zzb, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u000b\u0002\t\u0003\n", new Object[]{"zzd", "zze", "zzf"});
        }
        return (byte) 1;
    }
}
