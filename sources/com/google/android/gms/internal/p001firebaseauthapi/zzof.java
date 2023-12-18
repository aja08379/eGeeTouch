package com.google.android.gms.internal.p001firebaseauthapi;

import java.util.List;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzof  reason: invalid package */
/* loaded from: classes.dex */
public final class zzof extends zzadf implements zzael {
    private static final zzof zzb;
    private int zzd;
    private zzadk zze = zzz();

    static {
        zzof zzofVar = new zzof();
        zzb = zzofVar;
        zzadf.zzG(zzof.class, zzofVar);
    }

    private zzof() {
    }

    public static zzoc zzc() {
        return (zzoc) zzb.zzt();
    }

    public static zzof zzf(byte[] bArr, zzacs zzacsVar) throws zzadn {
        return (zzof) zzadf.zzy(zzb, bArr, zzacsVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzi(zzof zzofVar, zzoe zzoeVar) {
        zzoeVar.getClass();
        zzadk zzadkVar = zzofVar.zze;
        if (!zzadkVar.zzc()) {
            zzofVar.zze = zzadf.zzA(zzadkVar);
        }
        zzofVar.zze.add(zzoeVar);
    }

    public final int zza() {
        return this.zze.size();
    }

    public final int zzb() {
        return this.zzd;
    }

    public final zzoe zzd(int i) {
        return (zzoe) this.zze.get(i);
    }

    public final List zzg() {
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
                    return new zzoc(null);
                }
                return new zzof();
            }
            return zzD(zzb, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0001\u0000\u0001\u000b\u0002\u001b", new Object[]{"zzd", "zze", zzoe.class});
        }
        return (byte) 1;
    }
}
