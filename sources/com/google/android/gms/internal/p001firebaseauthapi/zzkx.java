package com.google.android.gms.internal.p001firebaseauthapi;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzkx */
/* loaded from: classes.dex */
public final class zzkx extends zzadf implements zzael {
    private static final zzkx zzb;
    private int zzd;
    private int zze;

    static {
        zzkx zzkxVar = new zzkx();
        zzb = zzkxVar;
        zzadf.zzG(zzkx.class, zzkxVar);
    }

    private zzkx() {
    }

    public static zzkw zzb() {
        return (zzkw) zzb.zzt();
    }

    public static zzkx zzd(zzacc zzaccVar, zzacs zzacsVar) throws zzadn {
        return (zzkx) zzadf.zzx(zzb, zzaccVar, zzacsVar);
    }

    public final int zza() {
        return this.zzd;
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
                    return new zzkw(null);
                }
                return new zzkx();
            }
            return zzD(zzb, "\u0000\u0002\u0000\u0000\u0002\u0003\u0002\u0000\u0000\u0000\u0002\u000b\u0003\u000b", new Object[]{"zzd", "zze"});
        }
        return (byte) 1;
    }
}
