package com.google.android.gms.internal.p001firebaseauthapi;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzmo  reason: invalid package */
/* loaded from: classes.dex */
public final class zzmo extends zzadf implements zzael {
    private static final zzmo zzb;
    private zzacc zzd = zzacc.zzb;
    private zzok zze;

    static {
        zzmo zzmoVar = new zzmo();
        zzb = zzmoVar;
        zzadf.zzG(zzmo.class, zzmoVar);
    }

    private zzmo() {
    }

    public static zzmn zza() {
        return (zzmn) zzb.zzt();
    }

    public static zzmo zzc(byte[] bArr, zzacs zzacsVar) throws zzadn {
        return (zzmo) zzadf.zzy(zzb, bArr, zzacsVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzf(zzmo zzmoVar, zzok zzokVar) {
        zzokVar.getClass();
        zzmoVar.zze = zzokVar;
    }

    public final zzacc zzd() {
        return this.zzd;
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
                    return new zzmn(null);
                }
                return new zzmo();
            }
            return zzD(zzb, "\u0000\u0002\u0000\u0000\u0002\u0003\u0002\u0000\u0000\u0000\u0002\n\u0003\t", new Object[]{"zzd", "zze"});
        }
        return (byte) 1;
    }
}
