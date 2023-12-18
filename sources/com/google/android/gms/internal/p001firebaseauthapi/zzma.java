package com.google.android.gms.internal.p001firebaseauthapi;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzma  reason: invalid package */
/* loaded from: classes.dex */
public final class zzma extends zzadf implements zzael {
    private static final zzma zzb;
    private zzmj zzd;
    private zzlu zze;
    private int zzf;

    static {
        zzma zzmaVar = new zzma();
        zzb = zzmaVar;
        zzadf.zzG(zzma.class, zzmaVar);
    }

    private zzma() {
    }

    public static zzlz zzb() {
        return (zzlz) zzb.zzt();
    }

    public static zzma zzd() {
        return zzb;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzf(zzma zzmaVar, zzmj zzmjVar) {
        zzmjVar.getClass();
        zzmaVar.zzd = zzmjVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzg(zzma zzmaVar, zzlu zzluVar) {
        zzluVar.getClass();
        zzmaVar.zze = zzluVar;
    }

    public final zzlu zza() {
        zzlu zzluVar = this.zze;
        return zzluVar == null ? zzlu.zzc() : zzluVar;
    }

    public final zzmj zze() {
        zzmj zzmjVar = this.zzd;
        return zzmjVar == null ? zzmj.zzc() : zzmjVar;
    }

    public final int zzh() {
        int i = this.zzf;
        int i2 = 3;
        if (i == 0) {
            i2 = 2;
        } else if (i != 1) {
            i2 = i != 2 ? i != 3 ? 0 : 5 : 4;
        }
        if (i2 == 0) {
            return 1;
        }
        return i2;
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
                    return new zzlz(null);
                }
                return new zzma();
            }
            return zzD(zzb, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\t\u0002\t\u0003\f", new Object[]{"zzd", "zze", "zzf"});
        }
        return (byte) 1;
    }
}
