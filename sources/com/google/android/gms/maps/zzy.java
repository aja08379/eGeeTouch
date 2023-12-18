package com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
/* compiled from: com.google.android.gms:play-services-maps@@18.1.0 */
/* loaded from: classes2.dex */
final class zzy extends com.google.android.gms.maps.internal.zzal {
    final /* synthetic */ GoogleMap.OnMapClickListener zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzy(GoogleMap googleMap, GoogleMap.OnMapClickListener onMapClickListener) {
        this.zza = onMapClickListener;
    }

    @Override // com.google.android.gms.maps.internal.zzam
    public final void zzb(LatLng latLng) {
        this.zza.onMapClick(latLng);
    }
}
