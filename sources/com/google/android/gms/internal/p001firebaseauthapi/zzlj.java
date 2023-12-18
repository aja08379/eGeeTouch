package com.google.android.gms.internal.p001firebaseauthapi;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzlj */
/* loaded from: classes.dex */
public final class zzlj extends zzadf implements zzael {
    private static final zzlj zzb;
    private int zzd;
    private int zze;

    static {
        zzlj zzljVar = new zzlj();
        zzb = zzljVar;
        zzadf.zzG(zzlj.class, zzljVar);
    }

    private zzlj() {
    }

    public static zzli zzb() {
        return (zzli) zzb.zzt();
    }

    public static zzlj zzd(zzacc zzaccVar, zzacs zzacsVar) throws zzadn {
        return (zzlj) zzadf.zzx(zzb, zzaccVar, zzacsVar);
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
                    return new zzli(null);
                }
                return new zzlj();
            }
            return zzD(zzb, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u000b\u0002\u000b", new Object[]{"zzd", "zze"});
        }
        return (byte) 1;
    }
}
