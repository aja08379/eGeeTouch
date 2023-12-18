package com.google.android.gms.internal.location;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zzah extends zzai {
    private final /* synthetic */ com.google.android.gms.location.zzal zzct;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzah(zzaf zzafVar, GoogleApiClient googleApiClient, com.google.android.gms.location.zzal zzalVar) {
        super(googleApiClient);
        this.zzct = zzalVar;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* synthetic */ void doExecute(zzaz zzazVar) throws RemoteException {
        zzazVar.zza(this.zzct, this);
    }
}
