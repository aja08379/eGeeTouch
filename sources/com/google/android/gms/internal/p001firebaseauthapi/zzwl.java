package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.internal.zzg;
import com.google.firebase.auth.internal.zzr;
import com.google.firebase.auth.internal.zzx;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzwl  reason: invalid package */
/* loaded from: classes.dex */
public final class zzwl extends zzyb {
    private final zzsu zza;

    public zzwl(PhoneAuthCredential phoneAuthCredential, String str) {
        super(2);
        Preconditions.checkNotNull(phoneAuthCredential);
        this.zza = new zzsu(phoneAuthCredential, str);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzyd
    public final String zza() {
        return "signInWithPhoneNumber";
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzyb
    public final void zzb() {
        zzx zzN = zzwy.zzN(this.zzd, this.zzk);
        ((zzg) this.zzf).zza(this.zzj, zzN);
        zzm(new zzr(zzN));
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzyd
    public final void zzc(TaskCompletionSource taskCompletionSource, zzxb zzxbVar) {
        this.zzv = new zzya(this, taskCompletionSource);
        zzxbVar.zzy(this.zza, this.zzc);
    }
}
