package com.google.android.gms.internal.p001firebaseauthapi;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzkr */
/* loaded from: classes.dex */
public final class zzkr extends zzadf implements zzael {
    private static final zzkr zzb;
    private int zzd;

    static {
        zzkr zzkrVar = new zzkr();
        zzb = zzkrVar;
        zzadf.zzG(zzkr.class, zzkrVar);
    }

    private zzkr() {
    }

    public static zzkq zzb() {
        return (zzkq) zzb.zzt();
    }

    public static zzkr zzd() {
        return zzb;
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
                    return new zzkq(null);
                }
                return new zzkr();
            }
            return zzD(zzb, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\u000b", new Object[]{"zzd"});
        }
        return (byte) 1;
    }
}
