package com.google.android.gms.internal.p001firebaseauthapi;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzla  reason: invalid package */
/* loaded from: classes.dex */
public final class zzla extends zzadf implements zzael {
    private static final zzla zzb;
    private int zzd;
    private zzacc zze = zzacc.zzb;

    static {
        zzla zzlaVar = new zzla();
        zzb = zzlaVar;
        zzadf.zzG(zzla.class, zzlaVar);
    }

    private zzla() {
    }

    public static zzkz zzb() {
        return (zzkz) zzb.zzt();
    }

    public static zzla zzd(zzacc zzaccVar, zzacs zzacsVar) throws zzadn {
        return (zzla) zzadf.zzx(zzb, zzaccVar, zzacsVar);
    }

    public final int zza() {
        return this.zzd;
    }

    public final zzacc zze() {
        return this.zze;
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
                    return new zzkz(null);
                }
                return new zzla();
            }
            return zzD(zzb, "\u0000\u0002\u0000\u0000\u0001\u0003\u0002\u0000\u0000\u0000\u0001\u000b\u0003\n", new Object[]{"zzd", "zze"});
        }
        return (byte) 1;
    }
}
