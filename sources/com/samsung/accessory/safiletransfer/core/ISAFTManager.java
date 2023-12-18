package com.samsung.accessory.safiletransfer.core;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.ResultReceiver;
/* loaded from: classes2.dex */
public interface ISAFTManager extends IInterface {

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements ISAFTManager {

        /* loaded from: classes2.dex */
        private static class Proxy implements ISAFTManager {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.samsung.accessory.safiletransfer.core.ISAFTManager
            public boolean registerCallbackFacilitator(int i, ResultReceiver resultReceiver) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.samsung.accessory.safiletransfer.core.ISAFTManager");
                    obtain.writeInt(i);
                    if (resultReceiver != null) {
                        obtain.writeInt(1);
                        resultReceiver.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.samsung.accessory.safiletransfer.core.ISAFTManager
            public Bundle sendCommand(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.samsung.accessory.safiletransfer.core.ISAFTManager");
                    obtain.writeString(str);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.samsung.accessory.safiletransfer.core.ISAFTManager
            public Bundle sendCommandV2(String str, ResultReceiver resultReceiver) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.samsung.accessory.safiletransfer.core.ISAFTManager");
                    obtain.writeString(str);
                    if (resultReceiver != null) {
                        obtain.writeInt(1);
                        resultReceiver.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static ISAFTManager asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.samsung.accessory.safiletransfer.core.ISAFTManager");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof ISAFTManager)) ? new Proxy(iBinder) : (ISAFTManager) queryLocalInterface;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface("com.samsung.accessory.safiletransfer.core.ISAFTManager");
                boolean registerCallbackFacilitator = registerCallbackFacilitator(parcel.readInt(), parcel.readInt() != 0 ? (ResultReceiver) ResultReceiver.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                parcel2.writeInt(registerCallbackFacilitator ? 1 : 0);
                return true;
            } else if (i == 2) {
                parcel.enforceInterface("com.samsung.accessory.safiletransfer.core.ISAFTManager");
                Bundle sendCommand = sendCommand(parcel.readString());
                parcel2.writeNoException();
                if (sendCommand != null) {
                    parcel2.writeInt(1);
                    sendCommand.writeToParcel(parcel2, 1);
                } else {
                    parcel2.writeInt(0);
                }
                return true;
            } else if (i != 3) {
                if (i != 1598968902) {
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                parcel2.writeString("com.samsung.accessory.safiletransfer.core.ISAFTManager");
                return true;
            } else {
                parcel.enforceInterface("com.samsung.accessory.safiletransfer.core.ISAFTManager");
                Bundle sendCommandV2 = sendCommandV2(parcel.readString(), parcel.readInt() != 0 ? (ResultReceiver) ResultReceiver.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                if (sendCommandV2 != null) {
                    parcel2.writeInt(1);
                    sendCommandV2.writeToParcel(parcel2, 1);
                } else {
                    parcel2.writeInt(0);
                }
                return true;
            }
        }
    }

    boolean registerCallbackFacilitator(int i, ResultReceiver resultReceiver) throws RemoteException;

    Bundle sendCommand(String str) throws RemoteException;

    Bundle sendCommandV2(String str, ResultReceiver resultReceiver) throws RemoteException;
}
