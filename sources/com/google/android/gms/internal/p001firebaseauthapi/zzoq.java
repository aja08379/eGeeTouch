package com.google.android.gms.internal.p001firebaseauthapi;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzoq  reason: invalid package */
/* loaded from: classes.dex */
public final class zzoq extends zzadf implements zzael {
    private static final zzoq zzb;
    private String zzd = "";

    static {
        zzoq zzoqVar = new zzoq();
        zzb = zzoqVar;
        zzadf.zzG(zzoq.class, zzoqVar);
    }

    private zzoq() {
    }

    public static zzoq zzb() {
        return zzb;
    }

    public static zzoq zzc(zzacc zzaccVar, zzacs zzacsVar) throws zzadn {
        return (zzoq) zzadf.zzx(zzb, zzaccVar, zzacsVar);
    }

    public final String zzd() {
        return this.zzd;
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
                    return new zzop(null);
                }
                return new zzoq();
            }
            return zzD(zzb, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001Ȉ", new Object[]{"zzd"});
        }
        return (byte) 1;
    }
}
