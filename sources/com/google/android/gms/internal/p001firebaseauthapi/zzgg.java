package com.google.android.gms.internal.p001firebaseauthapi;

import java.security.GeneralSecurityException;
import javax.annotation.Nullable;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzgg  reason: invalid package */
/* loaded from: classes.dex */
public final class zzgg extends zzaw {
    private final zzgy zza;

    public zzgg(zzgy zzgyVar, @Nullable zzca zzcaVar) throws GeneralSecurityException {
        int i = zzgd.zzb[zzgyVar.zzb().ordinal()];
        this.zza = zzgyVar;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaw
    public final zzbn zza() {
        zzgy zzgyVar = this.zza;
        return new zzgf(zzgyVar.zzg(), zzgyVar.zzc(), null);
    }
}
