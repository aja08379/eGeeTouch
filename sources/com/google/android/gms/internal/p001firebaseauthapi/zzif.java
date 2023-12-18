package com.google.android.gms.internal.p001firebaseauthapi;

import java.security.GeneralSecurityException;
import javax.crypto.spec.SecretKeySpec;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzif  reason: invalid package */
/* loaded from: classes.dex */
final class zzif extends zzgw {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzif(Class cls) {
        super(cls);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzgw
    public final /* bridge */ /* synthetic */ Object zza(zzaek zzaekVar) throws GeneralSecurityException {
        zzmt zzmtVar = (zzmt) zzaekVar;
        int zzf = zzmtVar.zzf().zzf();
        SecretKeySpec secretKeySpec = new SecretKeySpec(zzmtVar.zzg().zzt(), "HMAC");
        int zza = zzmtVar.zzf().zza();
        int i = zzf - 2;
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    if (i != 4) {
                        if (i == 5) {
                            return new zzqo(new zzqn("HMACSHA224", secretKeySpec), zza);
                        }
                        throw new GeneralSecurityException("unknown hash");
                    }
                    return new zzqo(new zzqn("HMACSHA512", secretKeySpec), zza);
                }
                return new zzqo(new zzqn("HMACSHA256", secretKeySpec), zza);
            }
            return new zzqo(new zzqn("HMACSHA384", secretKeySpec), zza);
        }
        return new zzqo(new zzqn("HMACSHA1", secretKeySpec), zza);
    }
}
