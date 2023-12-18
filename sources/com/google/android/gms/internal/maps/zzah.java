package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
/* compiled from: com.google.android.gms:play-services-maps@@18.1.0 */
/* loaded from: classes.dex */
public final class zzah extends zza implements zzaj {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzah(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.model.internal.ITileOverlayDelegate");
    }

    @Override // com.google.android.gms.internal.maps.zzaj
    public final float zzd() throws RemoteException {
        Parcel zzH = zzH(13, zza());
        float readFloat = zzH.readFloat();
        zzH.recycle();
        return readFloat;
    }

    @Override // com.google.android.gms.internal.maps.zzaj
    public final float zze() throws RemoteException {
        Parcel zzH = zzH(5, zza());
        float readFloat = zzH.readFloat();
        zzH.recycle();
        return readFloat;
    }

    @Override // com.google.android.gms.internal.maps.zzaj
    public final int zzf() throws RemoteException {
        Parcel zzH = zzH(9, zza());
        int readInt = zzH.readInt();
        zzH.recycle();
        return readInt;
    }

    @Override // com.google.android.gms.internal.maps.zzaj
    public final String zzg() throws RemoteException {
        Parcel zzH = zzH(3, zza());
        String readString = zzH.readString();
        zzH.recycle();
        return readString;
    }

    @Override // com.google.android.gms.internal.maps.zzaj
    public final void zzh() throws RemoteException {
        zzc(2, zza());
    }

    @Override // com.google.android.gms.internal.maps.zzaj
    public final void zzi() throws RemoteException {
        zzc(1, zza());
    }

    @Override // com.google.android.gms.internal.maps.zzaj
    public final void zzj(boolean z) throws RemoteException {
        Parcel zza = zza();
        zzc.zzd(zza, z);
        zzc(10, zza);
    }

    @Override // com.google.android.gms.internal.maps.zzaj
    public final void zzk(float f) throws RemoteException {
        Parcel zza = zza();
        zza.writeFloat(f);
        zzc(12, zza);
    }

    @Override // com.google.android.gms.internal.maps.zzaj
    public final void zzl(boolean z) throws RemoteException {
        Parcel zza = zza();
        zzc.zzd(zza, z);
        zzc(6, zza);
    }

    @Override // com.google.android.gms.internal.maps.zzaj
    public final void zzm(float f) throws RemoteException {
        Parcel zza = zza();
        zza.writeFloat(f);
        zzc(4, zza);
    }

    @Override // com.google.android.gms.internal.maps.zzaj
    public final boolean zzn(zzaj zzajVar) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, zzajVar);
        Parcel zzH = zzH(8, zza);
        boolean zzh = zzc.zzh(zzH);
        zzH.recycle();
        return zzh;
    }

    @Override // com.google.android.gms.internal.maps.zzaj
    public final boolean zzo() throws RemoteException {
        Parcel zzH = zzH(11, zza());
        boolean zzh = zzc.zzh(zzH);
        zzH.recycle();
        return zzh;
    }

    @Override // com.google.android.gms.internal.maps.zzaj
    public final boolean zzp() throws RemoteException {
        Parcel zzH = zzH(7, zza());
        boolean zzh = zzc.zzh(zzH);
        zzH.recycle();
        return zzh;
    }
}
