package com.google.android.gms.location;

import android.location.Location;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes2.dex */
public final class zzz extends com.google.android.gms.internal.location.zza implements zzx {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzz(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.location.ILocationListener");
    }

    @Override // com.google.android.gms.location.zzx
    public final void onLocationChanged(Location location) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.location.zzc.zza(obtainAndWriteInterfaceToken, location);
        transactOneway(1, obtainAndWriteInterfaceToken);
    }
}
