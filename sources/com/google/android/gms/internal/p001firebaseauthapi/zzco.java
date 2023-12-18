package com.google.android.gms.internal.p001firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzco  reason: invalid package */
/* loaded from: classes.dex */
final class zzco extends zzgb {
    final /* synthetic */ zzcp zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzco(zzcp zzcpVar, Class cls) {
        super(cls);
        this.zza = zzcpVar;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzgb
    public final /* bridge */ /* synthetic */ zzaek zza(zzaek zzaekVar) throws GeneralSecurityException {
        zzko zzkoVar = (zzko) zzaekVar;
        zzkk zzb = zzkl.zzb();
        zzb.zza(zzacc.zzn(zzqq.zza(zzkoVar.zza())));
        zzb.zzb(zzkoVar.zze());
        zzb.zzc(0);
        return (zzkl) zzb.zzi();
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzgb
    public final /* synthetic */ zzaek zzb(zzacc zzaccVar) throws zzadn {
        return zzko.zzd(zzaccVar, zzacs.zza());
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzgb
    public final Map zzc() throws GeneralSecurityException {
        HashMap hashMap = new HashMap();
        hashMap.put("AES128_EAX", zzcp.zzg(16, 16, 1));
        hashMap.put("AES128_EAX_RAW", zzcp.zzg(16, 16, 3));
        hashMap.put("AES256_EAX", zzcp.zzg(32, 16, 1));
        hashMap.put("AES256_EAX_RAW", zzcp.zzg(32, 16, 3));
        return Collections.unmodifiableMap(hashMap);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzgb
    public final /* bridge */ /* synthetic */ void zzd(zzaek zzaekVar) throws GeneralSecurityException {
        zzko zzkoVar = (zzko) zzaekVar;
        zzqs.zzb(zzkoVar.zza());
        if (zzkoVar.zze().zza() != 12 && zzkoVar.zze().zza() != 16) {
            throw new GeneralSecurityException("invalid IV size; acceptable values have 12 or 16 bytes");
        }
    }
}
