package com.google.android.gms.internal.location;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.GmsClient;
/* loaded from: classes.dex */
public class zzk extends GmsClient<zzao> {
    private final String zzca;
    protected final zzbj<zzao> zzcb;

    public zzk(Context context, Looper looper, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener, String str, ClientSettings clientSettings) {
        super(context, looper, 23, clientSettings, connectionCallbacks, onConnectionFailedListener);
        this.zzcb = new zzl(this);
        this.zzca = str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public /* synthetic */ IInterface createServiceInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
        return queryLocalInterface instanceof zzao ? (zzao) queryLocalInterface : new zzap(iBinder);
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    protected Bundle getGetServiceRequestExtraArgs() {
        Bundle bundle = new Bundle();
        bundle.putString("client_name", this.zzca);
        return bundle;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient, com.google.android.gms.common.api.Api.Client
    public int getMinApkVersion() {
        return 11925000;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public String getServiceDescriptor() {
        return "com.google.android.gms.location.internal.IGoogleLocationManagerService";
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    protected String getStartServiceAction() {
        return "com.google.android.location.internal.GoogleLocationManagerService.START";
    }
}
