package com.google.android.gms.internal.p001firebaseauthapi;

import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECPoint;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzee  reason: invalid package */
/* loaded from: classes.dex */
final class zzee extends zzgb {
    final /* synthetic */ zzef zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzee(zzef zzefVar, Class cls) {
        super(cls);
        this.zza = zzefVar;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzgb
    public final /* bridge */ /* synthetic */ zzaek zza(zzaek zzaekVar) throws GeneralSecurityException {
        zzlx zzlxVar = (zzlx) zzaekVar;
        KeyPair zzd = zzpx.zzd(zzpx.zzl(zzeo.zzc(zzlxVar.zzd().zze().zzf())));
        ECPoint w = ((ECPublicKey) zzd.getPublic()).getW();
        zzmf zzc = zzmg.zzc();
        zzc.zzb(0);
        zzc.zza(zzlxVar.zzd());
        zzc.zzc(zzacc.zzn(w.getAffineX().toByteArray()));
        zzc.zzd(zzacc.zzn(w.getAffineY().toByteArray()));
        zzmc zzb = zzmd.zzb();
        zzb.zzc(0);
        zzb.zzb((zzmg) zzc.zzi());
        zzb.zza(zzacc.zzn(((ECPrivateKey) zzd.getPrivate()).getS().toByteArray()));
        return (zzmd) zzb.zzi();
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzgb
    public final /* synthetic */ zzaek zzb(zzacc zzaccVar) throws zzadn {
        return zzlx.zzc(zzaccVar, zzacs.zza());
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzgb
    public final Map zzc() throws GeneralSecurityException {
        byte[] bArr;
        byte[] bArr2;
        byte[] bArr3;
        byte[] bArr4;
        byte[] bArr5;
        byte[] bArr6;
        byte[] bArr7;
        byte[] bArr8;
        byte[] bArr9;
        HashMap hashMap = new HashMap();
        zzbf zza = zzbg.zza("AES128_GCM");
        bArr = zzef.zza;
        hashMap.put("ECIES_P256_HKDF_HMAC_SHA256_AES128_GCM", zzef.zzi(4, 5, 3, zza, bArr, 1));
        zzbf zza2 = zzbg.zza("AES128_GCM");
        bArr2 = zzef.zza;
        hashMap.put("ECIES_P256_HKDF_HMAC_SHA256_AES128_GCM_RAW", zzef.zzi(4, 5, 3, zza2, bArr2, 3));
        zzbf zza3 = zzbg.zza("AES128_GCM");
        bArr3 = zzef.zza;
        hashMap.put("ECIES_P256_COMPRESSED_HKDF_HMAC_SHA256_AES128_GCM", zzef.zzi(4, 5, 4, zza3, bArr3, 1));
        zzbf zza4 = zzbg.zza("AES128_GCM");
        bArr4 = zzef.zza;
        hashMap.put("ECIES_P256_COMPRESSED_HKDF_HMAC_SHA256_AES128_GCM_RAW", zzef.zzi(4, 5, 4, zza4, bArr4, 3));
        zzbf zza5 = zzbg.zza("AES128_GCM");
        bArr5 = zzef.zza;
        hashMap.put("ECIES_P256_HKDF_HMAC_SHA256_AES128_GCM_COMPRESSED_WITHOUT_PREFIX", zzef.zzi(4, 5, 4, zza5, bArr5, 3));
        zzbf zza6 = zzbg.zza("AES128_CTR_HMAC_SHA256");
        bArr6 = zzef.zza;
        hashMap.put("ECIES_P256_HKDF_HMAC_SHA256_AES128_CTR_HMAC_SHA256", zzef.zzi(4, 5, 3, zza6, bArr6, 1));
        zzbf zza7 = zzbg.zza("AES128_CTR_HMAC_SHA256");
        bArr7 = zzef.zza;
        hashMap.put("ECIES_P256_HKDF_HMAC_SHA256_AES128_CTR_HMAC_SHA256_RAW", zzef.zzi(4, 5, 3, zza7, bArr7, 3));
        zzbf zza8 = zzbg.zza("AES128_CTR_HMAC_SHA256");
        bArr8 = zzef.zza;
        hashMap.put("ECIES_P256_COMPRESSED_HKDF_HMAC_SHA256_AES128_CTR_HMAC_SHA256", zzef.zzi(4, 5, 4, zza8, bArr8, 1));
        zzbf zza9 = zzbg.zza("AES128_CTR_HMAC_SHA256");
        bArr9 = zzef.zza;
        hashMap.put("ECIES_P256_COMPRESSED_HKDF_HMAC_SHA256_AES128_CTR_HMAC_SHA256_RAW", zzef.zzi(4, 5, 4, zza9, bArr9, 3));
        return Collections.unmodifiableMap(hashMap);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzgb
    public final /* synthetic */ void zzd(zzaek zzaekVar) throws GeneralSecurityException {
        zzeo.zza(((zzlx) zzaekVar).zzd());
    }
}
