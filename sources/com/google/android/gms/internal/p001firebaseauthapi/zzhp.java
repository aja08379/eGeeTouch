package com.google.android.gms.internal.p001firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzhp  reason: invalid package */
/* loaded from: classes.dex */
final class zzhp extends zzgb {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzhp(zzhq zzhqVar, Class cls) {
        super(cls);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzgb
    public final /* bridge */ /* synthetic */ zzaek zza(zzaek zzaekVar) throws GeneralSecurityException {
        zzjq zzjqVar = (zzjq) zzaekVar;
        zzjm zzb = zzjn.zzb();
        zzb.zzc(0);
        zzb.zza(zzacc.zzn(zzqq.zza(zzjqVar.zza())));
        zzb.zzb(zzjqVar.zze());
        return (zzjn) zzb.zzi();
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzgb
    public final /* synthetic */ zzaek zzb(zzacc zzaccVar) throws zzadn {
        return zzjq.zzd(zzaccVar, zzacs.zza());
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzgb
    public final Map zzc() throws GeneralSecurityException {
        HashMap hashMap = new HashMap();
        zzjp zzb = zzjq.zzb();
        zzb.zza(32);
        zzjs zzb2 = zzjt.zzb();
        zzb2.zza(16);
        zzb.zzb((zzjt) zzb2.zzi());
        hashMap.put("AES_CMAC", new zzga((zzjq) zzb.zzi(), 1));
        zzjp zzb3 = zzjq.zzb();
        zzb3.zza(32);
        zzjs zzb4 = zzjt.zzb();
        zzb4.zza(16);
        zzb3.zzb((zzjt) zzb4.zzi());
        hashMap.put("AES256_CMAC", new zzga((zzjq) zzb3.zzi(), 1));
        zzjp zzb5 = zzjq.zzb();
        zzb5.zza(32);
        zzjs zzb6 = zzjt.zzb();
        zzb6.zza(16);
        zzb5.zzb((zzjt) zzb6.zzi());
        hashMap.put("AES256_CMAC_RAW", new zzga((zzjq) zzb5.zzi(), 3));
        return Collections.unmodifiableMap(hashMap);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzgb
    public final /* bridge */ /* synthetic */ void zzd(zzaek zzaekVar) throws GeneralSecurityException {
        zzjq zzjqVar = (zzjq) zzaekVar;
        zzhq.zzi(zzjqVar.zze());
        zzhq.zzn(zzjqVar.zza());
    }
}
