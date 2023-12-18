package com.google.android.gms.internal.p001firebaseauthapi;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzzt  reason: invalid package */
/* loaded from: classes.dex */
public final class zzzt extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzzt> CREATOR = new zzzu();
    private final List zza;

    public zzzt() {
        this.zza = new ArrayList();
    }

    public static zzzt zza(zzzt zzztVar) {
        Preconditions.checkNotNull(zzztVar);
        List list = zzztVar.zza;
        zzzt zzztVar2 = new zzzt();
        if (list != null && !list.isEmpty()) {
            zzztVar2.zza.addAll(list);
        }
        return zzztVar2;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 2, this.zza, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final List zzb() {
        return this.zza;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzzt(List list) {
        List unmodifiableList;
        if (list == null) {
            unmodifiableList = Collections.emptyList();
        } else {
            unmodifiableList = Collections.unmodifiableList(list);
        }
        this.zza = unmodifiableList;
    }
}
