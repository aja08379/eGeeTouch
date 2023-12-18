package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.LatLng;
/* compiled from: com.google.android.gms:play-services-maps@@18.1.0 */
/* loaded from: classes.dex */
public final class zzy extends zza implements zzaa {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzy(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.model.internal.IMarkerDelegate");
    }

    @Override // com.google.android.gms.internal.maps.zzaa
    public final void zzA(float f) throws RemoteException {
        Parcel zza = zza();
        zza.writeFloat(f);
        zzc(27, zza);
    }

    @Override // com.google.android.gms.internal.maps.zzaa
    public final void zzB() throws RemoteException {
        zzc(11, zza());
    }

    @Override // com.google.android.gms.internal.maps.zzaa
    public final boolean zzC(zzaa zzaaVar) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, zzaaVar);
        Parcel zzH = zzH(16, zza);
        boolean zzh = zzc.zzh(zzH);
        zzH.recycle();
        return zzh;
    }

    @Override // com.google.android.gms.internal.maps.zzaa
    public final boolean zzD() throws RemoteException {
        Parcel zzH = zzH(10, zza());
        boolean zzh = zzc.zzh(zzH);
        zzH.recycle();
        return zzh;
    }

    @Override // com.google.android.gms.internal.maps.zzaa
    public final boolean zzE() throws RemoteException {
        Parcel zzH = zzH(21, zza());
        boolean zzh = zzc.zzh(zzH);
        zzH.recycle();
        return zzh;
    }

    @Override // com.google.android.gms.internal.maps.zzaa
    public final boolean zzF() throws RemoteException {
        Parcel zzH = zzH(13, zza());
        boolean zzh = zzc.zzh(zzH);
        zzH.recycle();
        return zzh;
    }

    @Override // com.google.android.gms.internal.maps.zzaa
    public final boolean zzG() throws RemoteException {
        Parcel zzH = zzH(15, zza());
        boolean zzh = zzc.zzh(zzH);
        zzH.recycle();
        return zzh;
    }

    @Override // com.google.android.gms.internal.maps.zzaa
    public final float zzd() throws RemoteException {
        Parcel zzH = zzH(26, zza());
        float readFloat = zzH.readFloat();
        zzH.recycle();
        return readFloat;
    }

    @Override // com.google.android.gms.internal.maps.zzaa
    public final float zze() throws RemoteException {
        Parcel zzH = zzH(23, zza());
        float readFloat = zzH.readFloat();
        zzH.recycle();
        return readFloat;
    }

    @Override // com.google.android.gms.internal.maps.zzaa
    public final float zzf() throws RemoteException {
        Parcel zzH = zzH(28, zza());
        float readFloat = zzH.readFloat();
        zzH.recycle();
        return readFloat;
    }

    @Override // com.google.android.gms.internal.maps.zzaa
    public final int zzg() throws RemoteException {
        Parcel zzH = zzH(17, zza());
        int readInt = zzH.readInt();
        zzH.recycle();
        return readInt;
    }

    @Override // com.google.android.gms.internal.maps.zzaa
    public final IObjectWrapper zzh() throws RemoteException {
        Parcel zzH = zzH(30, zza());
        IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(zzH.readStrongBinder());
        zzH.recycle();
        return asInterface;
    }

    @Override // com.google.android.gms.internal.maps.zzaa
    public final LatLng zzi() throws RemoteException {
        Parcel zzH = zzH(4, zza());
        LatLng latLng = (LatLng) zzc.zza(zzH, LatLng.CREATOR);
        zzH.recycle();
        return latLng;
    }

    @Override // com.google.android.gms.internal.maps.zzaa
    public final String zzj() throws RemoteException {
        Parcel zzH = zzH(2, zza());
        String readString = zzH.readString();
        zzH.recycle();
        return readString;
    }

    @Override // com.google.android.gms.internal.maps.zzaa
    public final String zzk() throws RemoteException {
        Parcel zzH = zzH(8, zza());
        String readString = zzH.readString();
        zzH.recycle();
        return readString;
    }

    @Override // com.google.android.gms.internal.maps.zzaa
    public final String zzl() throws RemoteException {
        Parcel zzH = zzH(6, zza());
        String readString = zzH.readString();
        zzH.recycle();
        return readString;
    }

    @Override // com.google.android.gms.internal.maps.zzaa
    public final void zzm() throws RemoteException {
        zzc(12, zza());
    }

    @Override // com.google.android.gms.internal.maps.zzaa
    public final void zzn() throws RemoteException {
        zzc(1, zza());
    }

    @Override // com.google.android.gms.internal.maps.zzaa
    public final void zzo(float f) throws RemoteException {
        Parcel zza = zza();
        zza.writeFloat(f);
        zzc(25, zza);
    }

    @Override // com.google.android.gms.internal.maps.zzaa
    public final void zzp(float f, float f2) throws RemoteException {
        Parcel zza = zza();
        zza.writeFloat(f);
        zza.writeFloat(f2);
        zzc(19, zza);
    }

    @Override // com.google.android.gms.internal.maps.zzaa
    public final void zzq(boolean z) throws RemoteException {
        Parcel zza = zza();
        zzc.zzd(zza, z);
        zzc(9, zza);
    }

    @Override // com.google.android.gms.internal.maps.zzaa
    public final void zzr(boolean z) throws RemoteException {
        Parcel zza = zza();
        zzc.zzd(zza, z);
        zzc(20, zza);
    }

    @Override // com.google.android.gms.internal.maps.zzaa
    public final void zzs(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, iObjectWrapper);
        zzc(18, zza);
    }

    @Override // com.google.android.gms.internal.maps.zzaa
    public final void zzt(float f, float f2) throws RemoteException {
        Parcel zza = zza();
        zza.writeFloat(f);
        zza.writeFloat(f2);
        zzc(24, zza);
    }

    @Override // com.google.android.gms.internal.maps.zzaa
    public final void zzu(LatLng latLng) throws RemoteException {
        Parcel zza = zza();
        zzc.zze(zza, latLng);
        zzc(3, zza);
    }

    @Override // com.google.android.gms.internal.maps.zzaa
    public final void zzv(float f) throws RemoteException {
        Parcel zza = zza();
        zza.writeFloat(f);
        zzc(22, zza);
    }

    @Override // com.google.android.gms.internal.maps.zzaa
    public final void zzw(String str) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zzc(7, zza);
    }

    @Override // com.google.android.gms.internal.maps.zzaa
    public final void zzx(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, iObjectWrapper);
        zzc(29, zza);
    }

    @Override // com.google.android.gms.internal.maps.zzaa
    public final void zzy(String str) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zzc(5, zza);
    }

    @Override // com.google.android.gms.internal.maps.zzaa
    public final void zzz(boolean z) throws RemoteException {
        Parcel zza = zza();
        zzc.zzd(zza, z);
        zzc(14, zza);
    }
}
