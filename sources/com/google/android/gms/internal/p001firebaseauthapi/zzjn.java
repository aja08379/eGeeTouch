package com.google.android.gms.internal.p001firebaseauthapi;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzjn  reason: invalid package */
/* loaded from: classes.dex */
public final class zzjn extends zzadf implements zzael {
    private static final zzjn zzb;
    private int zzd;
    private zzacc zze = zzacc.zzb;
    private zzjt zzf;

    static {
        zzjn zzjnVar = new zzjn();
        zzb = zzjnVar;
        zzadf.zzG(zzjn.class, zzjnVar);
    }

    private zzjn() {
    }

    public static zzjm zzb() {
        return (zzjm) zzb.zzt();
    }

    public static zzjn zzd(zzacc zzaccVar, zzacs zzacsVar) throws zzadn {
        return (zzjn) zzadf.zzx(zzb, zzaccVar, zzacsVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzi(zzjn zzjnVar, zzjt zzjtVar) {
        zzjtVar.getClass();
        zzjnVar.zzf = zzjtVar;
    }

    public final int zza() {
        return this.zzd;
    }

    public final zzjt zze() {
        zzjt zzjtVar = this.zzf;
        return zzjtVar == null ? zzjt.zzd() : zzjtVar;
    }

    public final zzacc zzf() {
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
                    return new zzjm(null);
                }
                return new zzjn();
            }
            return zzD(zzb, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u000b\u0002\n\u0003\t", new Object[]{"zzd", "zze", "zzf"});
        }
        return (byte) 1;
    }
}
