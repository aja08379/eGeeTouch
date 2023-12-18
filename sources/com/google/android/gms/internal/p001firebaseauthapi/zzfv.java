package com.google.android.gms.internal.p001firebaseauthapi;

import java.security.GeneralSecurityException;
import javax.annotation.Nullable;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzfv  reason: invalid package */
/* loaded from: classes.dex */
public abstract class zzfv {
    private final zzqv zza;
    private final Class zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzfv(zzqv zzqvVar, Class cls, zzfu zzfuVar) {
        this.zza = zzqvVar;
        this.zzb = cls;
    }

    public static zzfv zzb(zzft zzftVar, zzqv zzqvVar, Class cls) {
        return new zzfs(zzqvVar, cls, zzftVar);
    }

    public abstract zzaw zza(zzha zzhaVar, @Nullable zzca zzcaVar) throws GeneralSecurityException;

    public final zzqv zzc() {
        return this.zza;
    }

    public final Class zzd() {
        return this.zzb;
    }
}
