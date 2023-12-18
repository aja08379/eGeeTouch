package com.google.android.gms.internal.p001firebaseauthapi;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzkc  reason: invalid package */
/* loaded from: classes.dex */
public final class zzkc extends zzadf implements zzael {
    private static final zzkc zzb;
    private int zzd;
    private zzki zze;
    private zzacc zzf = zzacc.zzb;

    static {
        zzkc zzkcVar = new zzkc();
        zzb = zzkcVar;
        zzadf.zzG(zzkc.class, zzkcVar);
    }

    private zzkc() {
    }

    public static zzkb zzb() {
        return (zzkb) zzb.zzt();
    }

    public static zzkc zzd() {
        return zzb;
    }

    public static zzkc zze(zzacc zzaccVar, zzacs zzacsVar) throws zzadn {
        return (zzkc) zzadf.zzx(zzb, zzaccVar, zzacsVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzi(zzkc zzkcVar, zzki zzkiVar) {
        zzkiVar.getClass();
        zzkcVar.zze = zzkiVar;
    }

    public final int zza() {
        return this.zzd;
    }

    public final zzki zzf() {
        zzki zzkiVar = this.zze;
        return zzkiVar == null ? zzki.zzd() : zzkiVar;
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
                    return new zzkb(null);
                }
                return new zzkc();
            }
            return zzD(zzb, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u000b\u0002\t\u0003\n", new Object[]{"zzd", "zze", "zzf"});
        }
        return (byte) 1;
    }
}
