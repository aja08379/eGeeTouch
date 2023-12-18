package com.google.android.gms.internal.p001firebaseauthapi;

import java.security.GeneralSecurityException;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzcc  reason: invalid package */
/* loaded from: classes.dex */
public final class zzcc {
    public static final String zza;
    public static final String zzb;
    @Deprecated
    public static final zzpb zzc;
    @Deprecated
    public static final zzpb zzd;
    @Deprecated
    public static final zzpb zze;

    static {
        new zzcj();
        zza = "type.googleapis.com/google.crypto.tink.AesCtrHmacAeadKey";
        new zzcs();
        zzb = "type.googleapis.com/google.crypto.tink.AesGcmKey";
        new zzcv();
        new zzcp();
        new zzdb();
        new zzdf();
        new zzcy();
        new zzdi();
        zzpb zzb2 = zzpb.zzb();
        zzc = zzb2;
        zzd = zzb2;
        zze = zzb2;
        try {
            zza();
        } catch (GeneralSecurityException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static void zza() throws GeneralSecurityException {
        zzbz.zzo(new zzcg());
        zzit.zza();
        zzbz.zzn(new zzcj(), true);
        zzbz.zzn(new zzcs(), true);
        if (zzdw.zzb()) {
            return;
        }
        zzbz.zzn(new zzcp(), true);
        zzcv.zzg(true);
        zzbz.zzn(new zzcy(), true);
        zzbz.zzn(new zzdb(), true);
        zzbz.zzn(new zzdf(), true);
        zzbz.zzn(new zzdi(), true);
    }
}
