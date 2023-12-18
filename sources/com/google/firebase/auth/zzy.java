package com.google.firebase.auth;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* loaded from: classes2.dex */
public final class zzy implements Continuation {
    final /* synthetic */ FirebaseUser zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzy(FirebaseUser firebaseUser) {
        this.zza = firebaseUser;
    }

    @Override // com.google.android.gms.tasks.Continuation
    public final /* bridge */ /* synthetic */ Object then(Task task) throws Exception {
        return FirebaseAuth.getInstance(this.zza.zza()).zzi(null, (String) Preconditions.checkNotNull(((GetTokenResult) task.getResult()).getToken()));
    }
}
