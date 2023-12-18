package com.google.firebase.auth;

import android.app.Activity;
import android.net.Uri;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.internal.p001firebaseauthapi.zzzy;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import java.util.List;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* loaded from: classes2.dex */
public abstract class FirebaseUser extends AbstractSafeParcelable implements UserInfo {
    public Task<Void> delete() {
        return FirebaseAuth.getInstance(zza()).zza(this);
    }

    @Override // com.google.firebase.auth.UserInfo
    public abstract String getDisplayName();

    @Override // com.google.firebase.auth.UserInfo
    public abstract String getEmail();

    public Task<GetTokenResult> getIdToken(boolean z) {
        return FirebaseAuth.getInstance(zza()).zzc(this, z);
    }

    public abstract FirebaseUserMetadata getMetadata();

    public abstract MultiFactor getMultiFactor();

    @Override // com.google.firebase.auth.UserInfo
    public abstract String getPhoneNumber();

    @Override // com.google.firebase.auth.UserInfo
    public abstract Uri getPhotoUrl();

    public abstract List<? extends UserInfo> getProviderData();

    @Override // com.google.firebase.auth.UserInfo
    public abstract String getProviderId();

    public abstract String getTenantId();

    @Override // com.google.firebase.auth.UserInfo
    public abstract String getUid();

    public abstract boolean isAnonymous();

    public Task<AuthResult> linkWithCredential(AuthCredential authCredential) {
        Preconditions.checkNotNull(authCredential);
        return FirebaseAuth.getInstance(zza()).zzd(this, authCredential);
    }

    public Task<Void> reauthenticate(AuthCredential authCredential) {
        Preconditions.checkNotNull(authCredential);
        return FirebaseAuth.getInstance(zza()).zze(this, authCredential);
    }

    public Task<AuthResult> reauthenticateAndRetrieveData(AuthCredential authCredential) {
        Preconditions.checkNotNull(authCredential);
        return FirebaseAuth.getInstance(zza()).zzf(this, authCredential);
    }

    public Task<Void> reload() {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance(zza());
        return firebaseAuth.zzg(this, new zzt(firebaseAuth));
    }

    public Task<Void> sendEmailVerification() {
        return FirebaseAuth.getInstance(zza()).zzc(this, false).continueWithTask(new zzy(this));
    }

    public Task<AuthResult> startActivityForLinkWithProvider(Activity activity, FederatedAuthProvider federatedAuthProvider) {
        Preconditions.checkNotNull(activity);
        Preconditions.checkNotNull(federatedAuthProvider);
        return FirebaseAuth.getInstance(zza()).zzj(activity, federatedAuthProvider, this);
    }

    public Task<AuthResult> startActivityForReauthenticateWithProvider(Activity activity, FederatedAuthProvider federatedAuthProvider) {
        Preconditions.checkNotNull(activity);
        Preconditions.checkNotNull(federatedAuthProvider);
        return FirebaseAuth.getInstance(zza()).zzk(activity, federatedAuthProvider, this);
    }

    public Task<AuthResult> unlink(String str) {
        Preconditions.checkNotEmpty(str);
        return FirebaseAuth.getInstance(zza()).zzm(this, str);
    }

    public Task<Void> updateEmail(String str) {
        Preconditions.checkNotEmpty(str);
        return FirebaseAuth.getInstance(zza()).zzn(this, str);
    }

    public Task<Void> updatePassword(String str) {
        Preconditions.checkNotEmpty(str);
        return FirebaseAuth.getInstance(zza()).zzo(this, str);
    }

    public Task<Void> updatePhoneNumber(PhoneAuthCredential phoneAuthCredential) {
        return FirebaseAuth.getInstance(zza()).zzp(this, phoneAuthCredential);
    }

    public Task<Void> updateProfile(UserProfileChangeRequest userProfileChangeRequest) {
        Preconditions.checkNotNull(userProfileChangeRequest);
        return FirebaseAuth.getInstance(zza()).zzq(this, userProfileChangeRequest);
    }

    public Task<Void> verifyBeforeUpdateEmail(String str) {
        return verifyBeforeUpdateEmail(str, null);
    }

    public abstract FirebaseApp zza();

    public abstract FirebaseUser zzb();

    public abstract FirebaseUser zzc(List list);

    public abstract zzzy zzd();

    public abstract String zze();

    public abstract String zzf();

    public abstract List zzg();

    public abstract void zzh(zzzy zzzyVar);

    public abstract void zzi(List list);

    public Task<Void> verifyBeforeUpdateEmail(String str, ActionCodeSettings actionCodeSettings) {
        return FirebaseAuth.getInstance(zza()).zzc(this, false).continueWithTask(new zzaa(this, str, actionCodeSettings));
    }

    public Task<Void> sendEmailVerification(ActionCodeSettings actionCodeSettings) {
        return FirebaseAuth.getInstance(zza()).zzc(this, false).continueWithTask(new zzz(this, actionCodeSettings));
    }
}
