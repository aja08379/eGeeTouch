package com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzbh;
import com.google.android.gms.maps.model.Polyline;
/* compiled from: com.google.android.gms:play-services-maps@@18.1.0 */
/* loaded from: classes2.dex */
final class zzp extends zzbh {
    final /* synthetic */ GoogleMap.OnPolylineClickListener zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzp(GoogleMap googleMap, GoogleMap.OnPolylineClickListener onPolylineClickListener) {
        this.zza = onPolylineClickListener;
    }

    @Override // com.google.android.gms.maps.internal.zzbi
    public final void zzb(com.google.android.gms.internal.maps.zzag zzagVar) {
        this.zza.onPolylineClick(new Polyline(zzagVar));
    }
}
