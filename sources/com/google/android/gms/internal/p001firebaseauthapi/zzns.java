package com.google.android.gms.internal.p001firebaseauthapi;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzns  reason: invalid package */
/* loaded from: classes.dex */
public final class zzns extends zzadf implements zzael {
    private static final zzns zzb;
    private String zzd = "";
    private zzacc zze = zzacc.zzb;
    private int zzf;

    static {
        zzns zznsVar = new zzns();
        zzb = zznsVar;
        zzadf.zzG(zzns.class, zznsVar);
    }

    private zzns() {
    }

    public static zznp zza() {
        return (zznp) zzb.zzt();
    }

    public static zzns zzd() {
        return zzb;
    }

    public final zznr zzb() {
        zznr zzb2 = zznr.zzb(this.zzf);
        return zzb2 == null ? zznr.UNRECOGNIZED : zzb2;
    }

    public final zzacc zze() {
        return this.zze;
    }

    public final String zzf() {
        return this.zzd;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzadf
    protected final Object zzj(int i, Object obj, Object obj2) {
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
                    return new zznp(null);
                }
                return new zzns();
            }
            return zzD(zzb, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001Ȉ\u0002\n\u0003\f", new Object[]{"zzd", "zze", "zzf"});
        }
        return (byte) 1;
    }
}
