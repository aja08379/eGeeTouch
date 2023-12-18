package com.google.android.gms.internal.p001firebaseauthapi;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzku  reason: invalid package */
/* loaded from: classes.dex */
public final class zzku extends zzadf implements zzael {
    private static final zzku zzb;
    private int zzd;
    private zzacc zze = zzacc.zzb;

    static {
        zzku zzkuVar = new zzku();
        zzb = zzkuVar;
        zzadf.zzG(zzku.class, zzkuVar);
    }

    private zzku() {
    }

    public static zzkt zzb() {
        return (zzkt) zzb.zzt();
    }

    public static zzku zzd(zzacc zzaccVar, zzacs zzacsVar) throws zzadn {
        return (zzku) zzadf.zzx(zzb, zzaccVar, zzacsVar);
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
                    return new zzkt(null);
                }
                return new zzku();
            }
            return zzD(zzb, "\u0000\u0002\u0000\u0000\u0001\u0003\u0002\u0000\u0000\u0000\u0001\u000b\u0003\n", new Object[]{"zzd", "zze"});
        }
        return (byte) 1;
    }
}
