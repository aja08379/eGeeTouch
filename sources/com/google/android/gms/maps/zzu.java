package com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
/* compiled from: com.google.android.gms:play-services-maps@@18.1.0 */
/* loaded from: classes2.dex */
final class zzu extends com.google.android.gms.maps.internal.zzu {
    final /* synthetic */ GoogleMap.OnCameraMoveStartedListener zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzu(GoogleMap googleMap, GoogleMap.OnCameraMoveStartedListener onCameraMoveStartedListener) {
        this.zza = onCameraMoveStartedListener;
    }

    @Override // com.google.android.gms.maps.internal.zzv
    public final void zzb(int i) {
        this.zza.onCameraMoveStarted(i);
    }
}
