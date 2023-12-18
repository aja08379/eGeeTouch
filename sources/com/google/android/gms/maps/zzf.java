package com.google.android.gms.maps;

import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
/* compiled from: com.google.android.gms:play-services-maps@@18.1.0 */
/* loaded from: classes2.dex */
final class zzf extends com.google.android.gms.maps.internal.zzh {
    final /* synthetic */ GoogleMap.InfoWindowAdapter zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzf(GoogleMap googleMap, GoogleMap.InfoWindowAdapter infoWindowAdapter) {
        this.zza = infoWindowAdapter;
    }

    @Override // com.google.android.gms.maps.internal.zzi
    public final IObjectWrapper zzb(com.google.android.gms.internal.maps.zzaa zzaaVar) {
        return ObjectWrapper.wrap(this.zza.getInfoContents(new Marker(zzaaVar)));
    }

    @Override // com.google.android.gms.maps.internal.zzi
    public final IObjectWrapper zzc(com.google.android.gms.internal.maps.zzaa zzaaVar) {
        return ObjectWrapper.wrap(this.zza.getInfoWindow(new Marker(zzaaVar)));
    }
}
