package com.samsung.accessory.api;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes2.dex */
public interface IDeathCallback extends IInterface {

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IDeathCallback {

        /* loaded from: classes2.dex */
        private static class Proxy implements IDeathCallback {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.samsung.accessory.api.IDeathCallback
            public String getAppName() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.samsung.accessory.api.IDeathCallback");
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, "com.samsung.accessory.api.IDeathCallback");
        }

        public static IDeathCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.samsung.accessory.api.IDeathCallback");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof IDeathCallback)) ? new Proxy(iBinder) : (IDeathCallback) queryLocalInterface;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1) {
                if (i != 1598968902) {
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                parcel2.writeString("com.samsung.accessory.api.IDeathCallback");
                return true;
            }
            parcel.enforceInterface("com.samsung.accessory.api.IDeathCallback");
            String appName = getAppName();
            parcel2.writeNoException();
            parcel2.writeString(appName);
            return true;
        }
    }

    String getAppName() throws RemoteException;
}
