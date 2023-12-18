package com.google.android.gms.internal.p001firebaseauthapi;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzlx */
/* loaded from: classes.dex */
public final class zzlx extends zzadf implements zzael {
    private static final zzlx zzb;
    private zzma zzd;

    static {
        zzlx zzlxVar = new zzlx();
        zzb = zzlxVar;
        zzadf.zzG(zzlx.class, zzlxVar);
    }

    private zzlx() {
    }

    public static zzlw zza() {
        return (zzlw) zzb.zzt();
    }

    public static zzlx zzc(zzacc zzaccVar, zzacs zzacsVar) throws zzadn {
        return (zzlx) zzadf.zzx(zzb, zzaccVar, zzacsVar);
    }

    public static /* synthetic */ void zze(zzlx zzlxVar, zzma zzmaVar) {
        zzmaVar.getClass();
        zzlxVar.zzd = zzmaVar;
    }

    public final zzma zzd() {
        zzma zzmaVar = this.zzd;
        return zzmaVar == null ? zzma.zzd() : zzmaVar;
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
                    return new zzlw(null);
                }
                return new zzlx();
            }
            return zzD(zzb, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\t", new Object[]{"zzd"});
        }
        return (byte) 1;
    }
}
