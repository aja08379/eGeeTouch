package com.google.firebase.auth.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.firebase.auth.AdditionalUserInfo;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import java.util.List;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* loaded from: classes2.dex */
public final class zzr implements AuthResult {
    public static final Parcelable.Creator<zzr> CREATOR = new zzs();
    private zzx zza;
    private zzp zzb;
    private com.google.firebase.auth.zze zzc;

    public zzr(zzx zzxVar) {
        zzx zzxVar2 = (zzx) Preconditions.checkNotNull(zzxVar);
        this.zza = zzxVar2;
        List zzo = zzxVar2.zzo();
        this.zzb = null;
        for (int i = 0; i < zzo.size(); i++) {
            if (!TextUtils.isEmpty(((zzt) zzo.get(i)).zza())) {
                this.zzb = new zzp(((zzt) zzo.get(i)).getProviderId(), ((zzt) zzo.get(i)).zza(), zzxVar.zzs());
            }
        }
        if (this.zzb == null) {
            this.zzb = new zzp(zzxVar.zzs());
        }
        this.zzc = zzxVar.zzj();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzr(zzx zzxVar, zzp zzpVar, com.google.firebase.auth.zze zzeVar) {
        this.zza = zzxVar;
        this.zzb = zzpVar;
        this.zzc = zzeVar;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // com.google.firebase.auth.AuthResult
    public final AdditionalUserInfo getAdditionalUserInfo() {
        return this.zzb;
    }

    @Override // com.google.firebase.auth.AuthResult
    public final AuthCredential getCredential() {
        return this.zzc;
    }

    @Override // com.google.firebase.auth.AuthResult
    public final FirebaseUser getUser() {
        return this.zza;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zza, i, false);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzb, i, false);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zzc, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
