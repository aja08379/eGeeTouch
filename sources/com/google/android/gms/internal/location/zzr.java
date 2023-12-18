package com.google.android.gms.internal.location;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
/* loaded from: classes.dex */
final class zzr extends zzab {
    private final /* synthetic */ LocationRequest zzck;
    private final /* synthetic */ LocationListener zzcl;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzr(zzq zzqVar, GoogleApiClient googleApiClient, LocationRequest locationRequest, LocationListener locationListener) {
        super(googleApiClient);
        this.zzck = locationRequest;
        this.zzcl = locationListener;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* synthetic */ void doExecute(zzaz zzazVar) throws RemoteException {
        zzazVar.zza(this.zzck, ListenerHolders.createListenerHolder(this.zzcl, zzbm.zzc(), LocationListener.class.getSimpleName()), new zzac(this));
    }
}
