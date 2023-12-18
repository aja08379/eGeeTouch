package com.samsung.accessory.api;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.ResultReceiver;
import com.samsung.accessory.api.IDeathCallback;
import com.samsung.accessory.api.ISAMexCallback;
import com.samsung.accessory.api.ISAPeerAgentAuthCallback;
import com.samsung.accessory.api.ISAPeerAgentCallback;
import com.samsung.accessory.api.ISAServiceChannelCallback;
import com.samsung.accessory.api.ISAServiceConnectionCallback;
import com.samsung.accessory.api.ISAServiceConnectionIndicationCallback;
import com.samsung.android.sdk.accessory.SAPeerAgent;
/* loaded from: classes2.dex */
public interface ISAFrameworkManagerV2 extends IInterface {

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements ISAFrameworkManagerV2 {

        /* loaded from: classes2.dex */
        private static class Proxy implements ISAFrameworkManagerV2 {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // com.samsung.accessory.api.ISAFrameworkManagerV2
            public Bundle acceptServiceConnection(long j, String str, SAPeerAgent sAPeerAgent, long j2, ISAServiceConnectionCallback iSAServiceConnectionCallback, ISAServiceChannelCallback iSAServiceChannelCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.samsung.accessory.api.ISAFrameworkManagerV2");
                    obtain.writeLong(j);
                    obtain.writeString(str);
                    if (sAPeerAgent != null) {
                        obtain.writeInt(1);
                        sAPeerAgent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeLong(j2);
                    obtain.writeStrongBinder(iSAServiceConnectionCallback != null ? iSAServiceConnectionCallback.asBinder() : null);
                    obtain.writeStrongBinder(iSAServiceChannelCallback != null ? iSAServiceChannelCallback.asBinder() : null);
                    this.mRemote.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.samsung.accessory.api.ISAFrameworkManagerV2
            public int authenticatePeerAgent(long j, String str, SAPeerAgent sAPeerAgent, ISAPeerAgentAuthCallback iSAPeerAgentAuthCallback, long j2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.samsung.accessory.api.ISAFrameworkManagerV2");
                    obtain.writeLong(j);
                    obtain.writeString(str);
                    if (sAPeerAgent != null) {
                        obtain.writeInt(1);
                        sAPeerAgent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iSAPeerAgentAuthCallback != null ? iSAPeerAgentAuthCallback.asBinder() : null);
                    obtain.writeLong(j2);
                    this.mRemote.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.samsung.accessory.api.ISAFrameworkManagerV2
            public void cleanupAgent(long j, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.samsung.accessory.api.ISAFrameworkManagerV2");
                    obtain.writeLong(j);
                    obtain.writeString(str);
                    this.mRemote.transact(19, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.samsung.accessory.api.ISAFrameworkManagerV2
            public int closeServiceConnection(long j, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.samsung.accessory.api.ISAFrameworkManagerV2");
                    obtain.writeLong(j);
                    obtain.writeString(str);
                    this.mRemote.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.samsung.accessory.api.ISAFrameworkManagerV2
            public int findPeerAgents(long j, long j2, String str, ISAPeerAgentCallback iSAPeerAgentCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.samsung.accessory.api.ISAFrameworkManagerV2");
                    obtain.writeLong(j);
                    obtain.writeLong(j2);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(iSAPeerAgentCallback != null ? iSAPeerAgentCallback.asBinder() : null);
                    this.mRemote.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.samsung.accessory.api.ISAFrameworkManagerV2
            public Bundle getAgentDetails(long j, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.samsung.accessory.api.ISAFrameworkManagerV2");
                    obtain.writeLong(j);
                    obtain.writeString(str);
                    this.mRemote.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.samsung.accessory.api.ISAFrameworkManagerV2
            public String getAgentId(long j, String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.samsung.accessory.api.ISAFrameworkManagerV2");
                    obtain.writeLong(j);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.mRemote.transact(20, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.samsung.accessory.api.ISAFrameworkManagerV2
            public ResultReceiver getClientCallback(long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.samsung.accessory.api.ISAFrameworkManagerV2");
                    obtain.writeLong(j);
                    this.mRemote.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (ResultReceiver) ResultReceiver.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.samsung.accessory.api.ISAFrameworkManagerV2
            public Bundle getLocalAgentId(long j, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.samsung.accessory.api.ISAFrameworkManagerV2");
                    obtain.writeLong(j);
                    obtain.writeString(str);
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.samsung.accessory.api.ISAFrameworkManagerV2
            public boolean isSocketConnected(long j, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.samsung.accessory.api.ISAFrameworkManagerV2");
                    obtain.writeLong(j);
                    obtain.writeString(str);
                    this.mRemote.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.samsung.accessory.api.ISAFrameworkManagerV2
            public Bundle makeFrameworkConnection(int i, String str, IDeathCallback iDeathCallback, int i2, ISAServiceConnectionIndicationCallback iSAServiceConnectionIndicationCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.samsung.accessory.api.ISAFrameworkManagerV2");
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(iDeathCallback != null ? iDeathCallback.asBinder() : null);
                    obtain.writeInt(i2);
                    obtain.writeStrongBinder(iSAServiceConnectionIndicationCallback != null ? iSAServiceConnectionIndicationCallback.asBinder() : null);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.samsung.accessory.api.ISAFrameworkManagerV2
            public void registerComponent(long j, byte[] bArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.samsung.accessory.api.ISAFrameworkManagerV2");
                    obtain.writeLong(j);
                    obtain.writeByteArray(bArr);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.samsung.accessory.api.ISAFrameworkManagerV2
            public void registerMexCallback(long j, String str, ISAMexCallback iSAMexCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.samsung.accessory.api.ISAFrameworkManagerV2");
                    obtain.writeLong(j);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(iSAMexCallback != null ? iSAMexCallback.asBinder() : null);
                    this.mRemote.transact(15, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.samsung.accessory.api.ISAFrameworkManagerV2
            public int rejectServiceConnection(long j, String str, SAPeerAgent sAPeerAgent, long j2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.samsung.accessory.api.ISAFrameworkManagerV2");
                    obtain.writeLong(j);
                    obtain.writeString(str);
                    if (sAPeerAgent != null) {
                        obtain.writeInt(1);
                        sAPeerAgent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeLong(j2);
                    this.mRemote.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.samsung.accessory.api.ISAFrameworkManagerV2
            public int requestServiceConnection(long j, String str, SAPeerAgent sAPeerAgent, ISAServiceConnectionCallback iSAServiceConnectionCallback, ISAServiceChannelCallback iSAServiceChannelCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.samsung.accessory.api.ISAFrameworkManagerV2");
                    obtain.writeLong(j);
                    obtain.writeString(str);
                    if (sAPeerAgent != null) {
                        obtain.writeInt(1);
                        sAPeerAgent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iSAServiceConnectionCallback != null ? iSAServiceConnectionCallback.asBinder() : null);
                    obtain.writeStrongBinder(iSAServiceChannelCallback != null ? iSAServiceChannelCallback.asBinder() : null);
                    this.mRemote.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.samsung.accessory.api.ISAFrameworkManagerV2
            public int send(long j, String str, long j2, byte[] bArr, boolean z, int i, int i2, int i3) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.samsung.accessory.api.ISAFrameworkManagerV2");
                    obtain.writeLong(j);
                    obtain.writeString(str);
                    obtain.writeLong(j2);
                    obtain.writeByteArray(bArr);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    this.mRemote.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.samsung.accessory.api.ISAFrameworkManagerV2
            public int sendCompressed(long j, String str, long j2, byte[] bArr, boolean z, int i, int i2, int i3) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.samsung.accessory.api.ISAFrameworkManagerV2");
                    obtain.writeLong(j);
                    obtain.writeString(str);
                    obtain.writeLong(j2);
                    obtain.writeByteArray(bArr);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    this.mRemote.transact(22, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.samsung.accessory.api.ISAFrameworkManagerV2
            public int sendMessage(long j, String str, String str2, long j2, byte[] bArr, boolean z, int i, int i2, int i3, int i4) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.samsung.accessory.api.ISAFrameworkManagerV2");
                    obtain.writeLong(j);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeLong(j2);
                    obtain.writeByteArray(bArr);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    obtain.writeInt(i4);
                    this.mRemote.transact(17, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.samsung.accessory.api.ISAFrameworkManagerV2
            public void sendMessageDeliveryStatus(long j, long j2, int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.samsung.accessory.api.ISAFrameworkManagerV2");
                    obtain.writeLong(j);
                    obtain.writeLong(j2);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    this.mRemote.transact(18, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.samsung.accessory.api.ISAFrameworkManagerV2
            public int sendUncompressed(long j, String str, long j2, byte[] bArr, boolean z, int i, int i2, int i3) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.samsung.accessory.api.ISAFrameworkManagerV2");
                    obtain.writeLong(j);
                    obtain.writeString(str);
                    obtain.writeLong(j2);
                    obtain.writeByteArray(bArr);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    this.mRemote.transact(21, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.samsung.accessory.api.ISAFrameworkManagerV2
            public int tearFrameworkConnection(long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.samsung.accessory.api.ISAFrameworkManagerV2");
                    obtain.writeLong(j);
                    this.mRemote.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.samsung.accessory.api.ISAFrameworkManagerV2
            public void unregisterMexCallback(long j, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.samsung.accessory.api.ISAFrameworkManagerV2");
                    obtain.writeLong(j);
                    obtain.writeString(str);
                    this.mRemote.transact(16, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static ISAFrameworkManagerV2 asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.samsung.accessory.api.ISAFrameworkManagerV2");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof ISAFrameworkManagerV2)) ? new Proxy(iBinder) : (ISAFrameworkManagerV2) queryLocalInterface;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString("com.samsung.accessory.api.ISAFrameworkManagerV2");
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface("com.samsung.accessory.api.ISAFrameworkManagerV2");
                    Bundle makeFrameworkConnection = makeFrameworkConnection(parcel.readInt(), parcel.readString(), IDeathCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), ISAServiceConnectionIndicationCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    if (makeFrameworkConnection != null) {
                        parcel2.writeInt(1);
                        makeFrameworkConnection.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 2:
                    parcel.enforceInterface("com.samsung.accessory.api.ISAFrameworkManagerV2");
                    registerComponent(parcel.readLong(), parcel.createByteArray());
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface("com.samsung.accessory.api.ISAFrameworkManagerV2");
                    Bundle localAgentId = getLocalAgentId(parcel.readLong(), parcel.readString());
                    parcel2.writeNoException();
                    if (localAgentId != null) {
                        parcel2.writeInt(1);
                        localAgentId.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 4:
                    parcel.enforceInterface("com.samsung.accessory.api.ISAFrameworkManagerV2");
                    int findPeerAgents = findPeerAgents(parcel.readLong(), parcel.readLong(), parcel.readString(), ISAPeerAgentCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(findPeerAgents);
                    return true;
                case 5:
                    parcel.enforceInterface("com.samsung.accessory.api.ISAFrameworkManagerV2");
                    int authenticatePeerAgent = authenticatePeerAgent(parcel.readLong(), parcel.readString(), parcel.readInt() != 0 ? SAPeerAgent.CREATOR.createFromParcel(parcel) : null, ISAPeerAgentAuthCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readLong());
                    parcel2.writeNoException();
                    parcel2.writeInt(authenticatePeerAgent);
                    return true;
                case 6:
                    parcel.enforceInterface("com.samsung.accessory.api.ISAFrameworkManagerV2");
                    int requestServiceConnection = requestServiceConnection(parcel.readLong(), parcel.readString(), parcel.readInt() != 0 ? SAPeerAgent.CREATOR.createFromParcel(parcel) : null, ISAServiceConnectionCallback.Stub.asInterface(parcel.readStrongBinder()), ISAServiceChannelCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(requestServiceConnection);
                    return true;
                case 7:
                    parcel.enforceInterface("com.samsung.accessory.api.ISAFrameworkManagerV2");
                    Bundle acceptServiceConnection = acceptServiceConnection(parcel.readLong(), parcel.readString(), parcel.readInt() != 0 ? SAPeerAgent.CREATOR.createFromParcel(parcel) : null, parcel.readLong(), ISAServiceConnectionCallback.Stub.asInterface(parcel.readStrongBinder()), ISAServiceChannelCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    if (acceptServiceConnection != null) {
                        parcel2.writeInt(1);
                        acceptServiceConnection.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 8:
                    parcel.enforceInterface("com.samsung.accessory.api.ISAFrameworkManagerV2");
                    int rejectServiceConnection = rejectServiceConnection(parcel.readLong(), parcel.readString(), parcel.readInt() != 0 ? SAPeerAgent.CREATOR.createFromParcel(parcel) : null, parcel.readLong());
                    parcel2.writeNoException();
                    parcel2.writeInt(rejectServiceConnection);
                    return true;
                case 9:
                    parcel.enforceInterface("com.samsung.accessory.api.ISAFrameworkManagerV2");
                    int send = send(parcel.readLong(), parcel.readString(), parcel.readLong(), parcel.createByteArray(), parcel.readInt() != 0, parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(send);
                    return true;
                case 10:
                    parcel.enforceInterface("com.samsung.accessory.api.ISAFrameworkManagerV2");
                    int closeServiceConnection = closeServiceConnection(parcel.readLong(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(closeServiceConnection);
                    return true;
                case 11:
                    parcel.enforceInterface("com.samsung.accessory.api.ISAFrameworkManagerV2");
                    Bundle agentDetails = getAgentDetails(parcel.readLong(), parcel.readString());
                    parcel2.writeNoException();
                    if (agentDetails != null) {
                        parcel2.writeInt(1);
                        agentDetails.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 12:
                    parcel.enforceInterface("com.samsung.accessory.api.ISAFrameworkManagerV2");
                    int tearFrameworkConnection = tearFrameworkConnection(parcel.readLong());
                    parcel2.writeNoException();
                    parcel2.writeInt(tearFrameworkConnection);
                    return true;
                case 13:
                    parcel.enforceInterface("com.samsung.accessory.api.ISAFrameworkManagerV2");
                    boolean isSocketConnected = isSocketConnected(parcel.readLong(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(isSocketConnected ? 1 : 0);
                    return true;
                case 14:
                    parcel.enforceInterface("com.samsung.accessory.api.ISAFrameworkManagerV2");
                    ResultReceiver clientCallback = getClientCallback(parcel.readLong());
                    parcel2.writeNoException();
                    if (clientCallback != null) {
                        parcel2.writeInt(1);
                        clientCallback.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 15:
                    parcel.enforceInterface("com.samsung.accessory.api.ISAFrameworkManagerV2");
                    registerMexCallback(parcel.readLong(), parcel.readString(), ISAMexCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 16:
                    parcel.enforceInterface("com.samsung.accessory.api.ISAFrameworkManagerV2");
                    unregisterMexCallback(parcel.readLong(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 17:
                    parcel.enforceInterface("com.samsung.accessory.api.ISAFrameworkManagerV2");
                    int sendMessage = sendMessage(parcel.readLong(), parcel.readString(), parcel.readString(), parcel.readLong(), parcel.createByteArray(), parcel.readInt() != 0, parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(sendMessage);
                    return true;
                case 18:
                    parcel.enforceInterface("com.samsung.accessory.api.ISAFrameworkManagerV2");
                    sendMessageDeliveryStatus(parcel.readLong(), parcel.readLong(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 19:
                    parcel.enforceInterface("com.samsung.accessory.api.ISAFrameworkManagerV2");
                    cleanupAgent(parcel.readLong(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 20:
                    parcel.enforceInterface("com.samsung.accessory.api.ISAFrameworkManagerV2");
                    String agentId = getAgentId(parcel.readLong(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(agentId);
                    return true;
                case 21:
                    parcel.enforceInterface("com.samsung.accessory.api.ISAFrameworkManagerV2");
                    int sendUncompressed = sendUncompressed(parcel.readLong(), parcel.readString(), parcel.readLong(), parcel.createByteArray(), parcel.readInt() != 0, parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(sendUncompressed);
                    return true;
                case 22:
                    parcel.enforceInterface("com.samsung.accessory.api.ISAFrameworkManagerV2");
                    int sendCompressed = sendCompressed(parcel.readLong(), parcel.readString(), parcel.readLong(), parcel.createByteArray(), parcel.readInt() != 0, parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(sendCompressed);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    Bundle acceptServiceConnection(long j, String str, SAPeerAgent sAPeerAgent, long j2, ISAServiceConnectionCallback iSAServiceConnectionCallback, ISAServiceChannelCallback iSAServiceChannelCallback) throws RemoteException;

    int authenticatePeerAgent(long j, String str, SAPeerAgent sAPeerAgent, ISAPeerAgentAuthCallback iSAPeerAgentAuthCallback, long j2) throws RemoteException;

    void cleanupAgent(long j, String str) throws RemoteException;

    int closeServiceConnection(long j, String str) throws RemoteException;

    int findPeerAgents(long j, long j2, String str, ISAPeerAgentCallback iSAPeerAgentCallback) throws RemoteException;

    Bundle getAgentDetails(long j, String str) throws RemoteException;

    String getAgentId(long j, String str, String str2) throws RemoteException;

    ResultReceiver getClientCallback(long j) throws RemoteException;

    Bundle getLocalAgentId(long j, String str) throws RemoteException;

    boolean isSocketConnected(long j, String str) throws RemoteException;

    Bundle makeFrameworkConnection(int i, String str, IDeathCallback iDeathCallback, int i2, ISAServiceConnectionIndicationCallback iSAServiceConnectionIndicationCallback) throws RemoteException;

    void registerComponent(long j, byte[] bArr) throws RemoteException;

    void registerMexCallback(long j, String str, ISAMexCallback iSAMexCallback) throws RemoteException;

    int rejectServiceConnection(long j, String str, SAPeerAgent sAPeerAgent, long j2) throws RemoteException;

    int requestServiceConnection(long j, String str, SAPeerAgent sAPeerAgent, ISAServiceConnectionCallback iSAServiceConnectionCallback, ISAServiceChannelCallback iSAServiceChannelCallback) throws RemoteException;

    int send(long j, String str, long j2, byte[] bArr, boolean z, int i, int i2, int i3) throws RemoteException;

    int sendCompressed(long j, String str, long j2, byte[] bArr, boolean z, int i, int i2, int i3) throws RemoteException;

    int sendMessage(long j, String str, String str2, long j2, byte[] bArr, boolean z, int i, int i2, int i3, int i4) throws RemoteException;

    void sendMessageDeliveryStatus(long j, long j2, int i, int i2) throws RemoteException;

    int sendUncompressed(long j, String str, long j2, byte[] bArr, boolean z, int i, int i2, int i3) throws RemoteException;

    int tearFrameworkConnection(long j) throws RemoteException;

    void unregisterMexCallback(long j, String str) throws RemoteException;
}
