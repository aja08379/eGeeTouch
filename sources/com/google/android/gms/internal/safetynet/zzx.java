package com.google.android.gms.internal.safetynet;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.GmsClient;
import java.util.List;
/* loaded from: classes.dex */
public final class zzx extends GmsClient<zzi> {
    private final Context zzap;

    public zzx(Context context, Looper looper, ClientSettings clientSettings, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 45, clientSettings, connectionCallbacks, onConnectionFailedListener);
        this.zzap = context;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final /* synthetic */ IInterface createServiceInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.safetynet.internal.ISafetyNetService");
        return queryLocalInterface instanceof zzi ? (zzi) queryLocalInterface : new zzj(iBinder);
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient, com.google.android.gms.common.api.Api.Client
    public final int getMinApkVersion() {
        return 12200000;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final String getServiceDescriptor() {
        return "com.google.android.gms.safetynet.internal.ISafetyNetService";
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    protected final String getStartServiceAction() {
        return "com.google.android.gms.safetynet.service.START";
    }

    public final void zza(zzg zzgVar, List<Integer> list, int i, String str, String str2) throws RemoteException {
        if (str2 == null) {
            str2 = zzb("com.google.android.safetynet.API_KEY");
        }
        String str3 = str2;
        int[] iArr = new int[list.size()];
        for (int i2 = 0; i2 < list.size(); i2++) {
            iArr[i2] = list.get(i2).intValue();
        }
        ((zzi) getService()).zza(zzgVar, str3, iArr, i, str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final String zzb(String str) {
        ApplicationInfo applicationInfo;
        Bundle bundle;
        try {
            PackageManager packageManager = this.zzap.getPackageManager();
            if (packageManager == null || (applicationInfo = packageManager.getApplicationInfo(this.zzap.getPackageName(), 128)) == null || (bundle = applicationInfo.metaData) == null) {
                return null;
            }
            return (String) bundle.get(str);
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }
}
