package com.google.android.gms.internal.p001firebaseauthapi;

import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Base64Utils;
import java.util.ArrayList;
import java.util.List;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zztt  reason: invalid package */
/* loaded from: classes.dex */
public final class zztt implements zzyg {
    final /* synthetic */ zzaao zza;
    final /* synthetic */ zzzr zzb;
    final /* synthetic */ zzxa zzc;
    final /* synthetic */ zzzy zzd;
    final /* synthetic */ zzyf zze;
    final /* synthetic */ zzvf zzf;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zztt(zzvf zzvfVar, zzaao zzaaoVar, zzzr zzzrVar, zzxa zzxaVar, zzzy zzzyVar, zzyf zzyfVar) {
        this.zzf = zzvfVar;
        this.zza = zzaaoVar;
        this.zzb = zzzrVar;
        this.zzc = zzxaVar;
        this.zzd = zzzyVar;
        this.zze = zzyfVar;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzyf
    public final void zza(String str) {
        this.zze.zza(str);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzyg
    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        zzaap zzaapVar = (zzaap) obj;
        if (this.zza.zzn("EMAIL")) {
            this.zzb.zzg(null);
        } else {
            zzaao zzaaoVar = this.zza;
            if (zzaaoVar.zzk() != null) {
                this.zzb.zzg(zzaaoVar.zzk());
            }
        }
        if (this.zza.zzn("DISPLAY_NAME")) {
            this.zzb.zzf(null);
        } else {
            zzaao zzaaoVar2 = this.zza;
            if (zzaaoVar2.zzj() != null) {
                this.zzb.zzf(zzaaoVar2.zzj());
            }
        }
        if (this.zza.zzn("PHOTO_URL")) {
            this.zzb.zzj(null);
        } else {
            zzaao zzaaoVar3 = this.zza;
            if (zzaaoVar3.zzm() != null) {
                this.zzb.zzj(zzaaoVar3.zzm());
            }
        }
        if (!TextUtils.isEmpty(this.zza.zzl())) {
            this.zzb.zzi(Base64Utils.encode("redacted".getBytes()));
        }
        List zzf = zzaapVar.zzf();
        if (zzf == null) {
            zzf = new ArrayList();
        }
        this.zzb.zzk(zzf);
        zzxa zzxaVar = this.zzc;
        zzzy zzzyVar = this.zzd;
        Preconditions.checkNotNull(zzzyVar);
        Preconditions.checkNotNull(zzaapVar);
        String zzd = zzaapVar.zzd();
        String zze = zzaapVar.zze();
        if (!TextUtils.isEmpty(zzd) && !TextUtils.isEmpty(zze)) {
            zzzyVar = new zzzy(zze, zzd, Long.valueOf(zzaapVar.zzb()), zzzyVar.zzg());
        }
        zzxaVar.zzi(zzzyVar, this.zzb);
    }
}
