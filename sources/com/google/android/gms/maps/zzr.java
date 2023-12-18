package com.google.android.gms.maps;

import android.os.RemoteException;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzbd;
import com.google.android.gms.maps.model.PointOfInterest;
/* compiled from: com.google.android.gms:play-services-maps@@18.1.0 */
/* loaded from: classes2.dex */
final class zzr extends zzbd {
    final /* synthetic */ GoogleMap.OnPoiClickListener zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzr(GoogleMap googleMap, GoogleMap.OnPoiClickListener onPoiClickListener) {
        this.zza = onPoiClickListener;
    }

    @Override // com.google.android.gms.maps.internal.zzbe
    public final void zzb(PointOfInterest pointOfInterest) throws RemoteException {
        this.zza.onPoiClick(pointOfInterest);
    }
}
