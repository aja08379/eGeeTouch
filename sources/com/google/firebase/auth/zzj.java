package com.google.firebase.auth;

import com.google.firebase.auth.FirebaseAuth;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* loaded from: classes2.dex */
final class zzj implements Runnable {
    final /* synthetic */ FirebaseAuth.IdTokenListener zza;
    final /* synthetic */ FirebaseAuth zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzj(FirebaseAuth firebaseAuth, FirebaseAuth.IdTokenListener idTokenListener) {
        this.zzb = firebaseAuth;
        this.zza = idTokenListener;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zza.onIdTokenChanged(this.zzb);
    }
}
