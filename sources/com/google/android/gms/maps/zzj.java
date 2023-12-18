package com.google.android.gms.maps;

import android.os.RemoteException;
import com.google.android.gms.maps.GoogleMap;
/* compiled from: com.google.android.gms:play-services-maps@@18.1.0 */
/* loaded from: classes2.dex */
final class zzj extends com.google.android.gms.maps.internal.zzan {
    final /* synthetic */ GoogleMap.OnMapLoadedCallback zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzj(GoogleMap googleMap, GoogleMap.OnMapLoadedCallback onMapLoadedCallback) {
        this.zza = onMapLoadedCallback;
    }

    @Override // com.google.android.gms.maps.internal.zzao
    public final void zzb() throws RemoteException {
        this.zza.onMapLoaded();
    }
}
