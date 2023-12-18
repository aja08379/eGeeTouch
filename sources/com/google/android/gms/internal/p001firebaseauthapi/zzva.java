package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.auth.internal.zzai;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzva  reason: invalid package */
/* loaded from: classes.dex */
public final class zzva implements zzyg {
    final /* synthetic */ UserProfileChangeRequest zza;
    final /* synthetic */ zzxa zzb;
    final /* synthetic */ zzvf zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzva(zzvf zzvfVar, UserProfileChangeRequest userProfileChangeRequest, zzxa zzxaVar) {
        this.zzc = zzvfVar;
        this.zza = userProfileChangeRequest;
        this.zzb = zzxaVar;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzyf
    public final void zza(String str) {
        this.zzb.zzh(zzai.zza(str));
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzyg
    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        zzzy zzzyVar = (zzzy) obj;
        zzaao zzaaoVar = new zzaao();
        zzaaoVar.zze(zzzyVar.zze());
        if (this.zza.zzb() || this.zza.getDisplayName() != null) {
            zzaaoVar.zzc(this.zza.getDisplayName());
        }
        if (this.zza.zzc() || this.zza.getPhotoUri() != null) {
            zzaaoVar.zzh(this.zza.zza());
        }
        zzvf.zze(this.zzc, this.zzb, zzzyVar, zzaaoVar, this);
    }
}
