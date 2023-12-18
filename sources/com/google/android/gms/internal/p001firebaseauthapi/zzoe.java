package com.google.android.gms.internal.p001firebaseauthapi;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzoe  reason: invalid package */
/* loaded from: classes.dex */
public final class zzoe extends zzadf implements zzael {
    private static final zzoe zzb;
    private zzns zzd;
    private int zze;
    private int zzf;
    private int zzg;

    static {
        zzoe zzoeVar = new zzoe();
        zzb = zzoeVar;
        zzadf.zzG(zzoe.class, zzoeVar);
    }

    private zzoe() {
    }

    public static zzod zzc() {
        return (zzod) zzb.zzt();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzf(zzoe zzoeVar, zzns zznsVar) {
        zznsVar.getClass();
        zzoeVar.zzd = zznsVar;
    }

    public final int zza() {
        return this.zzf;
    }

    public final zzns zzb() {
        zzns zznsVar = this.zzd;
        return zznsVar == null ? zzns.zzd() : zznsVar;
    }

    public final zzoy zze() {
        zzoy zzb2 = zzoy.zzb(this.zzg);
        return zzb2 == null ? zzoy.UNRECOGNIZED : zzb2;
    }

    public final boolean zzi() {
        return this.zzd != null;
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
                    return new zzod(null);
                }
                return new zzoe();
            }
            return zzD(zzb, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0000\u0000\u0001\t\u0002\f\u0003\u000b\u0004\f", new Object[]{"zzd", "zze", "zzf", "zzg"});
        }
        return (byte) 1;
    }

    public final int zzk() {
        int i = this.zze;
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
}
