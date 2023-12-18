package com.google.android.gms.internal.p001firebaseauthapi;

import java.security.GeneralSecurityException;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaz  reason: invalid package */
/* loaded from: classes.dex */
class zzaz implements zzax {
    private final zzgc zza;
    private final Class zzb;

    public zzaz(zzgc zzgcVar, Class cls) {
        if (!zzgcVar.zzm().contains(cls) && !Void.class.equals(cls)) {
            throw new IllegalArgumentException(String.format("Given internalKeyMananger %s does not support primitive class %s", zzgcVar.toString(), cls.getName()));
        }
        this.zza = zzgcVar;
        this.zzb = cls;
    }

    private final zzay zzf() {
        return new zzay(this.zza.zza());
    }

    private final Object zzg(zzaek zzaekVar) throws GeneralSecurityException {
        if (Void.class.equals(this.zzb)) {
            throw new GeneralSecurityException("Cannot create a primitive for Void");
        }
        this.zza.zze(zzaekVar);
        return this.zza.zzl(zzaekVar, this.zzb);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzax
    public final zzns zza(zzacc zzaccVar) throws GeneralSecurityException {
        try {
            zzaek zza = zzf().zza(zzaccVar);
            zznp zza2 = zzns.zza();
            zza2.zzb(this.zza.zzd());
            zza2.zzc(zza.zzo());
            zza2.zza(this.zza.zzb());
            return (zzns) zza2.zzi();
        } catch (zzadn e) {
            throw new GeneralSecurityException("Unexpected proto", e);
        }
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzax
    public final zzaek zzb(zzacc zzaccVar) throws GeneralSecurityException {
        try {
            return zzf().zza(zzaccVar);
        } catch (zzadn e) {
            throw new GeneralSecurityException("Failures parsing proto of type ".concat(String.valueOf(this.zza.zza().zzg().getName())), e);
        }
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzax
    public final Object zzc(zzacc zzaccVar) throws GeneralSecurityException {
        try {
            return zzg(this.zza.zzc(zzaccVar));
        } catch (zzadn e) {
            throw new GeneralSecurityException("Failures parsing proto of type ".concat(String.valueOf(this.zza.zzk().getName())), e);
        }
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzax
    public final Object zzd(zzaek zzaekVar) throws GeneralSecurityException {
        String concat = "Expected proto of type ".concat(String.valueOf(this.zza.zzk().getName()));
        if (this.zza.zzk().isInstance(zzaekVar)) {
            return zzg(zzaekVar);
        }
        throw new GeneralSecurityException(concat);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzax
    public final String zze() {
        return this.zza.zzd();
    }
}
