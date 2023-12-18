package com.google.android.gms.internal.p001firebaseauthapi;

import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzdz  reason: invalid package */
/* loaded from: classes.dex */
public final class zzdz extends zzgc {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzdz() {
        super(zzlg.class, new zzdx(zzat.class));
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzgc
    public final zzgb zza() {
        return new zzdy(this, zzlj.class);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzgc
    public final zznr zzb() {
        return zznr.SYMMETRIC;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzgc
    public final /* synthetic */ zzaek zzc(zzacc zzaccVar) throws zzadn {
        return zzlg.zzd(zzaccVar, zzacs.zza());
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzgc
    public final String zzd() {
        return "type.googleapis.com/google.crypto.tink.AesSivKey";
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzgc
    public final /* bridge */ /* synthetic */ void zze(zzaek zzaekVar) throws GeneralSecurityException {
        zzlg zzlgVar = (zzlg) zzaekVar;
        zzqs.zzc(zzlgVar.zza(), 0);
        if (zzlgVar.zze().zzd() == 64) {
            return;
        }
        int zzd = zzlgVar.zze().zzd();
        throw new InvalidKeyException("invalid key size: " + zzd + ". Valid keys must have 64 bytes.");
    }
}
