package com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
/* compiled from: com.google.android.gms:play-services-maps@@18.1.0 */
/* loaded from: classes2.dex */
final class zzx extends com.google.android.gms.maps.internal.zzo {
    final /* synthetic */ GoogleMap.OnCameraIdleListener zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzx(GoogleMap googleMap, GoogleMap.OnCameraIdleListener onCameraIdleListener) {
        this.zza = onCameraIdleListener;
    }

    @Override // com.google.android.gms.maps.internal.zzp
    public final void zzb() {
        this.zza.onCameraIdle();
    }
}
