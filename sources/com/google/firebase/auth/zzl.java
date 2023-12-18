package com.google.firebase.auth;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.internal.IdTokenListener;
import com.google.firebase.internal.InternalTokenResult;
import java.util.List;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* loaded from: classes2.dex */
public final class zzl implements Runnable {
    final /* synthetic */ FirebaseAuth zza;
    final /* synthetic */ InternalTokenResult zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzl(FirebaseAuth firebaseAuth, InternalTokenResult internalTokenResult) {
        this.zza = firebaseAuth;
        this.zzb = internalTokenResult;
    }

    @Override // java.lang.Runnable
    public final void run() {
        List<IdTokenListener> list;
        List<FirebaseAuth.IdTokenListener> list2;
        list = this.zza.zzc;
        for (IdTokenListener idTokenListener : list) {
            idTokenListener.onIdTokenChanged(this.zzb);
        }
        list2 = this.zza.zzb;
        for (FirebaseAuth.IdTokenListener idTokenListener2 : list2) {
            idTokenListener2.onIdTokenChanged(this.zza);
        }
    }
}
