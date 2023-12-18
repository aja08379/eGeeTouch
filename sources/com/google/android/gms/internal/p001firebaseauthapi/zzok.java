package com.google.android.gms.internal.p001firebaseauthapi;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzok  reason: invalid package */
/* loaded from: classes.dex */
public final class zzok extends zzadf implements zzael {
    private static final zzok zzb;
    private int zzd;
    private zzadk zze = zzz();

    static {
        zzok zzokVar = new zzok();
        zzb = zzokVar;
        zzadf.zzG(zzok.class, zzokVar);
    }

    private zzok() {
    }

    public static zzoh zza() {
        return (zzoh) zzb.zzt();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zze(zzok zzokVar, zzoj zzojVar) {
        zzojVar.getClass();
        zzadk zzadkVar = zzokVar.zze;
        if (!zzadkVar.zzc()) {
            zzokVar.zze = zzadf.zzA(zzadkVar);
        }
        zzokVar.zze.add(zzojVar);
    }

    public final zzoj zzb(int i) {
        return (zzoj) this.zze.get(0);
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
                    return new zzoh(null);
                }
                return new zzok();
            }
            return zzD(zzb, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0001\u0000\u0001\u000b\u0002\u001b", new Object[]{"zzd", "zze", zzoj.class});
        }
        return (byte) 1;
    }
}
