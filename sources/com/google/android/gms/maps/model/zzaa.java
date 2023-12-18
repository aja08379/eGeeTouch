package com.google.android.gms.maps.model;

import android.os.RemoteException;
import com.google.android.gms.internal.maps.zzam;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-maps@@18.1.0 */
/* loaded from: classes2.dex */
public final class zzaa implements TileProvider {
    final /* synthetic */ TileOverlayOptions zza;
    private final zzam zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzaa(TileOverlayOptions tileOverlayOptions) {
        zzam zzamVar;
        this.zza = tileOverlayOptions;
        zzamVar = tileOverlayOptions.zza;
        this.zzb = zzamVar;
    }

    @Override // com.google.android.gms.maps.model.TileProvider
    public final Tile getTile(int i, int i2, int i3) {
        try {
            return this.zzb.zzb(i, i2, i3);
        } catch (RemoteException unused) {
            return null;
        }
    }
}
