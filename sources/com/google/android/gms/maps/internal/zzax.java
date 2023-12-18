package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.RemoteException;
/* compiled from: com.google.android.gms:play-services-maps@@18.1.0 */
/* loaded from: classes2.dex */
public abstract class zzax extends com.google.android.gms.internal.maps.zzb implements zzay {
    public zzax() {
        super("com.google.android.gms.maps.internal.IOnMyLocationButtonClickListener");
    }

    @Override // com.google.android.gms.internal.maps.zzb
    protected final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i == 1) {
            boolean zzb = zzb();
            parcel2.writeNoException();
            com.google.android.gms.internal.maps.zzc.zzd(parcel2, zzb);
            return true;
        }
        return false;
    }
}
