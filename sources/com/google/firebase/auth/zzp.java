package com.google.firebase.auth;

import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.p001firebaseauthapi.zzwy;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.PhoneAuthProvider;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* loaded from: classes2.dex */
public final class zzp implements OnCompleteListener {
    final /* synthetic */ PhoneAuthOptions zza;
    final /* synthetic */ FirebaseAuth zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzp(FirebaseAuth firebaseAuth, PhoneAuthOptions phoneAuthOptions) {
        this.zzb = firebaseAuth;
        this.zza = phoneAuthOptions;
    }

    @Override // com.google.android.gms.tasks.OnCompleteListener
    public final void onComplete(Task task) {
        String zza;
        String str;
        PhoneAuthProvider.OnVerificationStateChangedCallbacks zzL;
        zzwy zzwyVar;
        String str2;
        zzwy zzwyVar2;
        String str3;
        if (!task.isSuccessful()) {
            Log.e("FirebaseAuth", task.getException() != null ? "Error while validating application identity: ".concat(String.valueOf(task.getException().getMessage())) : "Error while validating application identity: ");
            Log.e("FirebaseAuth", "Proceeding without any application identifier.");
            str = null;
            zza = null;
        } else {
            String zzb = ((com.google.firebase.auth.internal.zze) task.getResult()).zzb();
            zza = ((com.google.firebase.auth.internal.zze) task.getResult()).zza();
            str = zzb;
        }
        long longValue = this.zza.zzg().longValue();
        zzL = this.zzb.zzL(this.zza.zzh(), this.zza.zze());
        com.google.firebase.auth.internal.zzag zzagVar = (com.google.firebase.auth.internal.zzag) Preconditions.checkNotNull(this.zza.zzc());
        if (zzagVar.zze()) {
            zzwyVar2 = this.zzb.zze;
            String str4 = (String) Preconditions.checkNotNull(this.zza.zzh());
            str3 = this.zzb.zzi;
            zzwyVar2.zzD(zzagVar, str4, str3, longValue, this.zza.zzd() != null, this.zza.zzj(), str, zza, this.zzb.zzK(), zzL, this.zza.zzi(), this.zza.zza());
            return;
        }
        zzwyVar = this.zzb.zze;
        PhoneMultiFactorInfo phoneMultiFactorInfo = (PhoneMultiFactorInfo) Preconditions.checkNotNull(this.zza.zzf());
        str2 = this.zzb.zzi;
        zzwyVar.zzE(zzagVar, phoneMultiFactorInfo, str2, longValue, this.zza.zzd() != null, this.zza.zzj(), str, zza, this.zzb.zzK(), zzL, this.zza.zzi(), this.zza.zza());
    }
}
