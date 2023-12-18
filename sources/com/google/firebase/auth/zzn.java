package com.google.firebase.auth;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.p001firebaseauthapi.zzzy;
import com.google.firebase.auth.internal.zzbk;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* loaded from: classes2.dex */
public final class zzn implements zzbk {
    final /* synthetic */ FirebaseAuth zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzn(FirebaseAuth firebaseAuth) {
        this.zza = firebaseAuth;
    }

    @Override // com.google.firebase.auth.internal.zzg
    public final void zza(zzzy zzzyVar, FirebaseUser firebaseUser) {
        FirebaseAuth.zzH(this.zza, firebaseUser, zzzyVar, true, true);
    }

    @Override // com.google.firebase.auth.internal.zzao
    public final void zzb(Status status) {
        int statusCode = status.getStatusCode();
        if (statusCode == 17011 || statusCode == 17021 || statusCode == 17005) {
            this.zza.signOut();
        }
    }
}
