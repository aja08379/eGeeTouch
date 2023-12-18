package com.google.firebase.auth;

import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.internal.zzan;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* loaded from: classes2.dex */
public final class zzi implements zzan {
    final /* synthetic */ FirebaseUser zza;
    final /* synthetic */ FirebaseAuth zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzi(FirebaseAuth firebaseAuth, FirebaseUser firebaseUser) {
        this.zzb = firebaseAuth;
        this.zza = firebaseUser;
    }

    @Override // com.google.firebase.auth.internal.zzan
    public final void zza() {
        FirebaseUser firebaseUser;
        FirebaseUser firebaseUser2;
        FirebaseAuth firebaseAuth = this.zzb;
        firebaseUser = firebaseAuth.zzf;
        if (firebaseUser != null) {
            firebaseUser2 = firebaseAuth.zzf;
            if (firebaseUser2.getUid().equalsIgnoreCase(this.zza.getUid())) {
                this.zzb.zzD();
            }
        }
    }

    @Override // com.google.firebase.auth.internal.zzao
    public final void zzb(Status status) {
        if (status.getStatusCode() == 17011 || status.getStatusCode() == 17021 || status.getStatusCode() == 17005) {
            this.zzb.signOut();
        }
    }
}
