package com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzbf;
import com.google.android.gms.maps.model.Polygon;
/* compiled from: com.google.android.gms:play-services-maps@@18.1.0 */
/* loaded from: classes2.dex */
final class zzo extends zzbf {
    final /* synthetic */ GoogleMap.OnPolygonClickListener zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzo(GoogleMap googleMap, GoogleMap.OnPolygonClickListener onPolygonClickListener) {
        this.zza = onPolygonClickListener;
    }

    @Override // com.google.android.gms.maps.internal.zzbg
    public final void zzb(com.google.android.gms.internal.maps.zzad zzadVar) {
        this.zza.onPolygonClick(new Polygon(zzadVar));
    }
}
