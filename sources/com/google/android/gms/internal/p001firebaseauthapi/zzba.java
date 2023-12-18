package com.google.android.gms.internal.p001firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Set;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzba  reason: invalid package */
/* loaded from: classes.dex */
public final class zzba implements zzbc {
    final /* synthetic */ zzgc zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzba(zzgc zzgcVar) {
        this.zza = zzgcVar;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzbc
    public final zzax zza(Class cls) throws GeneralSecurityException {
        try {
            return new zzaz(this.zza, cls);
        } catch (IllegalArgumentException e) {
            throw new GeneralSecurityException("Primitive type not supported", e);
        }
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzbc
    public final zzax zzb() {
        zzgc zzgcVar = this.zza;
        return new zzaz(zzgcVar, zzgcVar.zzj());
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzbc
    public final Class zzc() {
        return this.zza.getClass();
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzbc
    public final Class zzd() {
        return null;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzbc
    public final Set zze() {
        return this.zza.zzm();
    }
}
