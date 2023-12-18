package com.google.android.gms.internal.p001firebaseauthapi;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zznk  reason: invalid package */
/* loaded from: classes.dex */
public final class zznk extends zzadf implements zzael {
    private static final zznk zzb;
    private int zzd;
    private zznn zze;
    private zzacc zzf = zzacc.zzb;

    static {
        zznk zznkVar = new zznk();
        zzb = zznkVar;
        zzadf.zzG(zznk.class, zznkVar);
    }

    private zznk() {
    }

    public static zznj zzb() {
        return (zznj) zzb.zzt();
    }

    public static zznk zzd(zzacc zzaccVar, zzacs zzacsVar) throws zzadn {
        return (zznk) zzadf.zzx(zzb, zzaccVar, zzacsVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzh(zznk zznkVar, zznn zznnVar) {
        zznnVar.getClass();
        zznkVar.zze = zznnVar;
    }

    public final int zza() {
        return this.zzd;
    }

    public final zznn zze() {
        zznn zznnVar = this.zze;
        return zznnVar == null ? zznn.zze() : zznnVar;
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
                    return new zznj(null);
                }
                return new zznk();
            }
            return zzD(zzb, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u000b\u0002\t\u0003\n", new Object[]{"zzd", "zze", "zzf"});
        }
        return (byte) 1;
    }

    public final boolean zzk() {
        return this.zze != null;
    }
}
