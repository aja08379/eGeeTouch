package com.google.android.gms.internal.p001firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzcr  reason: invalid package */
/* loaded from: classes.dex */
final class zzcr extends zzgb {
    final /* synthetic */ zzcs zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzcr(zzcs zzcsVar, Class cls) {
        super(cls);
        this.zza = zzcsVar;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzgb
    public final /* bridge */ /* synthetic */ zzaek zza(zzaek zzaekVar) throws GeneralSecurityException {
        zzkt zzb = zzku.zzb();
        zzb.zza(zzacc.zzn(zzqq.zza(((zzkx) zzaekVar).zza())));
        zzb.zzb(0);
        return (zzku) zzb.zzi();
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzgb
    public final /* synthetic */ zzaek zzb(zzacc zzaccVar) throws zzadn {
        return zzkx.zzd(zzaccVar, zzacs.zza());
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzgb
    public final Map zzc() throws GeneralSecurityException {
        HashMap hashMap = new HashMap();
        hashMap.put("AES128_GCM", zzcs.zzg(16, 1));
        hashMap.put("AES128_GCM_RAW", zzcs.zzg(16, 3));
        hashMap.put("AES256_GCM", zzcs.zzg(32, 1));
        hashMap.put("AES256_GCM_RAW", zzcs.zzg(32, 3));
        return Collections.unmodifiableMap(hashMap);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzgb
    public final /* synthetic */ void zzd(zzaek zzaekVar) throws GeneralSecurityException {
        zzqs.zzb(((zzkx) zzaekVar).zza());
    }
}
