package com.google.android.gms.internal.p001firebaseauthapi;

import java.security.GeneralSecurityException;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzbx  reason: invalid package */
/* loaded from: classes.dex */
final class zzbx extends zzaz implements zzbw {
    private final zzgx zza;
    private final zzgc zzb;

    public zzbx(zzgx zzgxVar, zzgc zzgcVar, Class cls) {
        super(zzgxVar, cls);
        this.zza = zzgxVar;
        this.zzb = zzgcVar;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzbw
    public final zzns zzf(zzacc zzaccVar) throws GeneralSecurityException {
        try {
            zzaek zzc = this.zza.zzc(zzaccVar);
            this.zza.zze(zzc);
            zzaek zzg = this.zza.zzg(zzc);
            this.zzb.zze(zzg);
            zznp zza = zzns.zza();
            zza.zzb(this.zzb.zzd());
            zza.zzc(zzg.zzo());
            zza.zza(this.zzb.zzb());
            return (zzns) zza.zzi();
        } catch (zzadn e) {
            throw new GeneralSecurityException("expected serialized proto of type ", e);
        }
    }
}
