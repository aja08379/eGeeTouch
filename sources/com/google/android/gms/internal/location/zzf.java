package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
/* loaded from: classes.dex */
final class zzf extends zzj {
    private final /* synthetic */ long zzbw;
    private final /* synthetic */ PendingIntent zzbx;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzf(zze zzeVar, GoogleApiClient googleApiClient, long j, PendingIntent pendingIntent) {
        super(googleApiClient);
        this.zzbw = j;
        this.zzbx = pendingIntent;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* synthetic */ void doExecute(zzaz zzazVar) throws RemoteException {
        zzazVar.zza(this.zzbw, this.zzbx);
        setResult((zzf) Status.RESULT_SUCCESS);
    }
}
