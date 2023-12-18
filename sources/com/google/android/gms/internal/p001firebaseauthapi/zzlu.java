package com.google.android.gms.internal.p001firebaseauthapi;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzlu */
/* loaded from: classes.dex */
public final class zzlu extends zzadf implements zzael {
    private static final zzlu zzb;
    private zznx zzd;

    static {
        zzlu zzluVar = new zzlu();
        zzb = zzluVar;
        zzadf.zzG(zzlu.class, zzluVar);
    }

    private zzlu() {
    }

    public static zzlt zza() {
        return (zzlt) zzb.zzt();
    }

    public static zzlu zzc() {
        return zzb;
    }

    public static /* synthetic */ void zze(zzlu zzluVar, zznx zznxVar) {
        zznxVar.getClass();
        zzluVar.zzd = zznxVar;
    }

    public final zznx zzd() {
        zznx zznxVar = this.zzd;
        return zznxVar == null ? zznx.zzc() : zznxVar;
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
                    return new zzlt(null);
                }
                return new zzlu();
            }
            return zzD(zzb, "\u0000\u0001\u0000\u0000\u0002\u0002\u0001\u0000\u0000\u0000\u0002\t", new Object[]{"zzd"});
        }
        return (byte) 1;
    }
}
