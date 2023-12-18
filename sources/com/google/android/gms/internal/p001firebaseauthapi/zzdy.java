package com.google.android.gms.internal.p001firebaseauthapi;

import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzdy  reason: invalid package */
/* loaded from: classes.dex */
final class zzdy extends zzgb {
    final /* synthetic */ zzdz zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzdy(zzdz zzdzVar, Class cls) {
        super(cls);
        this.zza = zzdzVar;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzgb
    public final /* bridge */ /* synthetic */ zzaek zza(zzaek zzaekVar) throws GeneralSecurityException {
        zzlf zzb = zzlg.zzb();
        zzb.zza(zzacc.zzn(zzqq.zza(((zzlj) zzaekVar).zza())));
        zzb.zzb(0);
        return (zzlg) zzb.zzi();
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzgb
    public final /* synthetic */ zzaek zzb(zzacc zzaccVar) throws zzadn {
        return zzlj.zzd(zzaccVar, zzacs.zza());
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzgb
    public final Map zzc() throws GeneralSecurityException {
        HashMap hashMap = new HashMap();
        zzli zzb = zzlj.zzb();
        zzb.zza(64);
        hashMap.put("AES256_SIV", new zzga((zzlj) zzb.zzi(), 1));
        zzli zzb2 = zzlj.zzb();
        zzb2.zza(64);
        hashMap.put("AES256_SIV_RAW", new zzga((zzlj) zzb2.zzi(), 3));
        return Collections.unmodifiableMap(hashMap);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzgb
    public final /* bridge */ /* synthetic */ void zzd(zzaek zzaekVar) throws GeneralSecurityException {
        zzlj zzljVar = (zzlj) zzaekVar;
        if (zzljVar.zza() == 64) {
            return;
        }
        int zza = zzljVar.zza();
        throw new InvalidAlgorithmParameterException("invalid key size: " + zza + ". Valid keys must have 64 bytes.");
    }
}
