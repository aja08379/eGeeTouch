package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.common.logging.Logger;
import com.google.android.gms.tasks.OnFailureListener;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzyr  reason: invalid package */
/* loaded from: classes.dex */
public final class zzyr implements OnFailureListener {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzyr(zzyv zzyvVar) {
    }

    @Override // com.google.android.gms.tasks.OnFailureListener
    public final void onFailure(Exception exc) {
        Logger logger;
        logger = zzyv.zza;
        logger.e("SmsRetrieverClient failed to start: ".concat(String.valueOf(exc.getMessage())), new Object[0]);
    }
}
