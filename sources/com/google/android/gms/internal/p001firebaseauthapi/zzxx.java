package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.firebase.auth.PhoneAuthProvider;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzxx  reason: invalid package */
/* loaded from: classes.dex */
final class zzxx implements Runnable {
    final /* synthetic */ zzxz zza;
    final /* synthetic */ zzxy zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzxx(zzxy zzxyVar, zzxz zzxzVar) {
        this.zzb = zzxyVar;
        this.zza = zzxzVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        synchronized (this.zzb.zza.zzh) {
            if (!this.zzb.zza.zzh.isEmpty()) {
                this.zza.zza((PhoneAuthProvider.OnVerificationStateChangedCallbacks) this.zzb.zza.zzh.get(0), new Object[0]);
            }
        }
    }
}
