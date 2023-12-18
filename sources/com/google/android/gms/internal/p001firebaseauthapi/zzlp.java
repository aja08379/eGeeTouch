package com.google.android.gms.internal.p001firebaseauthapi;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzlp */
/* loaded from: classes.dex */
public final class zzlp extends zzadf implements zzael {
    private static final zzlp zzb;

    static {
        zzlp zzlpVar = new zzlp();
        zzb = zzlpVar;
        zzadf.zzG(zzlp.class, zzlpVar);
    }

    private zzlp() {
    }

    public static zzlp zzb() {
        return zzb;
    }

    public static zzlp zzc(zzacc zzaccVar, zzacs zzacsVar) throws zzadn {
        return (zzlp) zzadf.zzx(zzb, zzaccVar, zzacsVar);
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
                    return new zzlo(null);
                }
                return new zzlp();
            }
            return zzD(zzb, "\u0000\u0000", null);
        }
        return (byte) 1;
    }
}
