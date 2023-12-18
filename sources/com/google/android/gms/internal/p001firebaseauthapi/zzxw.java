package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.PhoneAuthProvider;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzxw  reason: invalid package */
/* loaded from: classes.dex */
final class zzxw implements zzxz {
    final /* synthetic */ Status zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzxw(zzxy zzxyVar, Status status) {
        this.zza = status;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzxz
    public final void zza(PhoneAuthProvider.OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks, Object... objArr) {
        onVerificationStateChangedCallbacks.onVerificationFailed(zzxc.zza(this.zza));
    }
}
