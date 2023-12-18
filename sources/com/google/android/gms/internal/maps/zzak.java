package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.maps.model.Tile;
/* compiled from: com.google.android.gms:play-services-maps@@18.1.0 */
/* loaded from: classes.dex */
public final class zzak extends zza implements zzam {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzak(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.model.internal.ITileProviderDelegate");
    }

    @Override // com.google.android.gms.internal.maps.zzam
    public final Tile zzb(int i, int i2, int i3) throws RemoteException {
        Parcel zza = zza();
        zza.writeInt(i);
        zza.writeInt(i2);
        zza.writeInt(i3);
        Parcel zzH = zzH(1, zza);
        Tile tile = (Tile) zzc.zza(zzH, Tile.CREATOR);
        zzH.recycle();
        return tile;
    }
}
