package com.google.android.gms.location;

import android.location.Location;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes2.dex */
public abstract class zzy extends com.google.android.gms.internal.location.zzb implements zzx {
    public zzy() {
        super("com.google.android.gms.location.ILocationListener");
    }

    public static zzx zzc(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.location.ILocationListener");
        return queryLocalInterface instanceof zzx ? (zzx) queryLocalInterface : new zzz(iBinder);
    }

    @Override // com.google.android.gms.internal.location.zzb
    protected final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i == 1) {
            onLocationChanged((Location) com.google.android.gms.internal.location.zzc.zza(parcel, Location.CREATOR));
            return true;
        }
        return false;
    }
}
