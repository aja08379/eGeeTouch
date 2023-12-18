package com.samsung.accessory.api;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes2.dex */
public interface ISAServiceConnectionIndicationCallback extends IInterface {

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements ISAServiceConnectionIndicationCallback {

        /* loaded from: classes2.dex */
        private static class Proxy implements ISAServiceConnectionIndicationCallback {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.samsung.accessory.api.ISAServiceConnectionIndicationCallback
            public void onServiceConnectionRequested(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.samsung.accessory.api.ISAServiceConnectionIndicationCallback");
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, "com.samsung.accessory.api.ISAServiceConnectionIndicationCallback");
        }

        public static ISAServiceConnectionIndicationCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.samsung.accessory.api.ISAServiceConnectionIndicationCallback");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof ISAServiceConnectionIndicationCallback)) ? new Proxy(iBinder) : (ISAServiceConnectionIndicationCallback) queryLocalInterface;
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
                parcel2.writeString("com.samsung.accessory.api.ISAServiceConnectionIndicationCallback");
                return true;
            }
            parcel.enforceInterface("com.samsung.accessory.api.ISAServiceConnectionIndicationCallback");
            onServiceConnectionRequested(parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
            parcel2.writeNoException();
            return true;
        }
    }

    void onServiceConnectionRequested(Bundle bundle) throws RemoteException;
}
