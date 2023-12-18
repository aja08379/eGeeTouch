package com.google.android.gms.internal.location;

import android.os.Looper;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
/* loaded from: classes.dex */
final class zzx extends zzab {
    private final /* synthetic */ LocationRequest zzck;
    private final /* synthetic */ LocationCallback zzcm;
    private final /* synthetic */ Looper zzcp;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzx(zzq zzqVar, GoogleApiClient googleApiClient, LocationRequest locationRequest, LocationCallback locationCallback, Looper looper) {
        super(googleApiClient);
        this.zzck = locationRequest;
        this.zzcm = locationCallback;
        this.zzcp = looper;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* synthetic */ void doExecute(zzaz zzazVar) throws RemoteException {
        zzazVar.zza(zzbd.zza(this.zzck), ListenerHolders.createListenerHolder(this.zzcm, zzbm.zza(this.zzcp), LocationCallback.class.getSimpleName()), new zzac(this));
    }
}
