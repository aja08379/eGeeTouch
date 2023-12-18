package com.google.android.gms.internal.p001firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.Map;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzgb  reason: invalid package */
/* loaded from: classes.dex */
public abstract class zzgb {
    private final Class zza;

    public zzgb(Class cls) {
        this.zza = cls;
    }

    public abstract zzaek zza(zzaek zzaekVar) throws GeneralSecurityException;

    public abstract zzaek zzb(zzacc zzaccVar) throws zzadn;

    public Map zzc() throws GeneralSecurityException {
        return Collections.emptyMap();
    }

    public abstract void zzd(zzaek zzaekVar) throws GeneralSecurityException;

    public final Class zzg() {
        return this.zza;
    }
}
