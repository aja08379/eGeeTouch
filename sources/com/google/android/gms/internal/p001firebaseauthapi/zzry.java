package com.google.android.gms.internal.p001firebaseauthapi;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.firebase.auth.PhoneAuthCredential;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzry  reason: invalid package */
/* loaded from: classes.dex */
public final class zzry extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzry> CREATOR = new zzrz();
    private final String zza;
    private final PhoneAuthCredential zzb;

    public zzry(String str, PhoneAuthCredential phoneAuthCredential) {
        this.zza = str;
        this.zzb = phoneAuthCredential;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zza, false);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzb, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final PhoneAuthCredential zza() {
        return this.zzb;
    }

    public final String zzb() {
        return this.zza;
    }
}
