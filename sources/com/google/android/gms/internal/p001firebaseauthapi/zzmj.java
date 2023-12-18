package com.google.android.gms.internal.p001firebaseauthapi;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzmj  reason: invalid package */
/* loaded from: classes.dex */
public final class zzmj extends zzadf implements zzael {
    private static final zzmj zzb;
    private int zzd;
    private int zze;
    private zzacc zzf = zzacc.zzb;

    static {
        zzmj zzmjVar = new zzmj();
        zzb = zzmjVar;
        zzadf.zzG(zzmj.class, zzmjVar);
    }

    private zzmj() {
    }

    public static zzmi zza() {
        return (zzmi) zzb.zzt();
    }

    public static zzmj zzc() {
        return zzb;
    }

    public final zzacc zzd() {
        return this.zzf;
    }

    public final int zzf() {
        int i = this.zzd;
        int i2 = 5;
        if (i == 0) {
            i2 = 2;
        } else if (i == 2) {
            i2 = 4;
        } else if (i != 3) {
            i2 = i != 4 ? i != 5 ? 0 : 7 : 6;
        }
        if (i2 == 0) {
            return 1;
        }
        return i2;
    }

    public final int zzg() {
        int zzb2 = zzmq.zzb(this.zze);
        if (zzb2 == 0) {
            return 1;
        }
        return zzb2;
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
                    return new zzmi(null);
                }
                return new zzmj();
            }
            return zzD(zzb, "\u0000\u0003\u0000\u0000\u0001\u000b\u0003\u0000\u0000\u0000\u0001\f\u0002\f\u000b\n", new Object[]{"zzd", "zze", "zzf"});
        }
        return (byte) 1;
    }
}
