package com.google.android.gms.internal.p001firebaseauthapi;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzlg  reason: invalid package */
/* loaded from: classes.dex */
public final class zzlg extends zzadf implements zzael {
    private static final zzlg zzb;
    private int zzd;
    private zzacc zze = zzacc.zzb;

    static {
        zzlg zzlgVar = new zzlg();
        zzb = zzlgVar;
        zzadf.zzG(zzlg.class, zzlgVar);
    }

    private zzlg() {
    }

    public static zzlf zzb() {
        return (zzlf) zzb.zzt();
    }

    public static zzlg zzd(zzacc zzaccVar, zzacs zzacsVar) throws zzadn {
        return (zzlg) zzadf.zzx(zzb, zzaccVar, zzacsVar);
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
                    return new zzlf(null);
                }
                return new zzlg();
            }
            return zzD(zzb, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u000b\u0002\n", new Object[]{"zzd", "zze"});
        }
        return (byte) 1;
    }
}
