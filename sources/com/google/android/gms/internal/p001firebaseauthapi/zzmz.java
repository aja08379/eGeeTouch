package com.google.android.gms.internal.p001firebaseauthapi;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzmz */
/* loaded from: classes.dex */
public final class zzmz extends zzadf implements zzael {
    private static final zzmz zzb;
    private int zzd;
    private int zze;

    static {
        zzmz zzmzVar = new zzmz();
        zzb = zzmzVar;
        zzadf.zzG(zzmz.class, zzmzVar);
    }

    private zzmz() {
    }

    public static zzmy zzb() {
        return (zzmy) zzb.zzt();
    }

    public static zzmz zzd() {
        return zzb;
    }

    public final int zza() {
        return this.zze;
    }

    public final int zzf() {
        int zzb2 = zzmq.zzb(this.zzd);
        if (zzb2 == 0) {
            return 1;
        }
        return zzb2;
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
                    return new zzmy(null);
                }
                return new zzmz();
            }
            return zzD(zzb, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\f\u0002\u000b", new Object[]{"zzd", "zze"});
        }
        return (byte) 1;
    }
}
