package com.google.android.gms.location;

import android.location.Location;
import android.os.RemoteException;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.internal.location.zzaz;
import com.google.android.gms.tasks.TaskCompletionSource;
/* loaded from: classes2.dex */
final class zzl extends TaskApiCall<zzaz, Location> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzl(FusedLocationProviderClient fusedLocationProviderClient) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.common.api.internal.TaskApiCall
    public final /* synthetic */ void doExecute(zzaz zzazVar, TaskCompletionSource<Location> taskCompletionSource) throws RemoteException {
        taskCompletionSource.setResult(zzazVar.getLastLocation());
    }
}
