package com.google.android.gms.internal.p001firebaseauthapi;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzjq */
/* loaded from: classes.dex */
public final class zzjq extends zzadf implements zzael {
    private static final zzjq zzb;
    private int zzd;
    private zzjt zze;

    static {
        zzjq zzjqVar = new zzjq();
        zzb = zzjqVar;
        zzadf.zzG(zzjq.class, zzjqVar);
    }

    private zzjq() {
    }

    public static zzjp zzb() {
        return (zzjp) zzb.zzt();
    }

    public static zzjq zzd(zzacc zzaccVar, zzacs zzacsVar) throws zzadn {
        return (zzjq) zzadf.zzx(zzb, zzaccVar, zzacsVar);
    }

    public static /* synthetic */ void zzg(zzjq zzjqVar, zzjt zzjtVar) {
        zzjtVar.getClass();
        zzjqVar.zze = zzjtVar;
    }

    public final int zza() {
        return this.zzd;
    }

    public final zzjt zze() {
        zzjt zzjtVar = this.zze;
        return zzjtVar == null ? zzjt.zzd() : zzjtVar;
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
                    return new zzjp(null);
                }
                return new zzjq();
            }
            return zzD(zzb, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u000b\u0002\t", new Object[]{"zzd", "zze"});
        }
        return (byte) 1;
    }
}
