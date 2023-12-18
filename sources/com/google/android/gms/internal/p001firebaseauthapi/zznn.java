package com.google.android.gms.internal.p001firebaseauthapi;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zznn  reason: invalid package */
/* loaded from: classes.dex */
public final class zznn extends zzadf implements zzael {
    private static final zznn zzb;
    private int zzd;
    private zznh zze;
    private zzacc zzf = zzacc.zzb;

    static {
        zznn zznnVar = new zznn();
        zzb = zznnVar;
        zzadf.zzG(zznn.class, zznnVar);
    }

    private zznn() {
    }

    public static zznm zzc() {
        return (zznm) zzb.zzt();
    }

    public static zznn zze() {
        return zzb;
    }

    public static zznn zzf(zzacc zzaccVar, zzacs zzacsVar) throws zzadn {
        return (zznn) zzadf.zzx(zzb, zzaccVar, zzacsVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzi(zznn zznnVar, zznh zznhVar) {
        zznhVar.getClass();
        zznnVar.zze = zznhVar;
    }

    public final int zza() {
        return this.zzd;
    }

    public final zznh zzb() {
        zznh zznhVar = this.zze;
        return zznhVar == null ? zznh.zzc() : zznhVar;
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
                    return new zznm(null);
                }
                return new zznn();
            }
            return zzD(zzb, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u000b\u0002\t\u0003\n", new Object[]{"zzd", "zze", "zzf"});
        }
        return (byte) 1;
    }

    public final boolean zzl() {
        return this.zze != null;
    }
}
