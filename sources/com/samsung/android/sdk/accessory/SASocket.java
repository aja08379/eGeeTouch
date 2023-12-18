package com.samsung.android.sdk.accessory;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import com.samsung.accessory.api.ISAServiceChannelCallback;
import com.samsung.accessory.api.ISAServiceConnectionCallback;
import java.io.IOException;
import java.lang.Thread;
/* loaded from: classes2.dex */
public abstract class SASocket {
    public static final int CONNECTION_LOST_DEVICE_DETACHED = 521;
    public static final int CONNECTION_LOST_PEER_DISCONNECTED = 513;
    public static final int CONNECTION_LOST_RECEIVE_LIMIT_VIOLATED = 523;
    public static final int CONNECTION_LOST_RETRANSMISSION_FAILED = 522;
    public static final int CONNECTION_LOST_UNKNOWN_REASON = 512;
    public static final int ERROR_FATAL = 2048;
    private static final String a = "[SA_SDK]" + SASocket.class.getSimpleName();
    private String b;
    private SAPeerAgent c;
    private b d;
    private a e;
    private SAAdapter f;
    private int g = 2;

    /* loaded from: classes2.dex */
    private final class ServiceChannelCallback extends ISAServiceChannelCallback.Stub {
        private ServiceChannelCallback() {
        }

        @Override // com.samsung.accessory.api.ISAServiceChannelCallback
        public final void onError(Bundle bundle) throws RemoteException {
            if (!bundle.containsKey("errorcode")) {
                Log.w(SASocket.a, "onChannelError with no error code!");
                return;
            }
            Message obtainMessage = SASocket.this.d.obtainMessage(3);
            obtainMessage.arg1 = bundle.getInt("errorcode");
            SASocket.this.d.sendMessage(obtainMessage);
        }

        @Override // com.samsung.accessory.api.ISAServiceChannelCallback
        public final void onRead(Bundle bundle) throws RemoteException {
            long j = bundle.getLong("channelId");
            int i = bundle.getInt("fragmentIndex");
            Message obtainMessage = SASocket.this.d.obtainMessage(2);
            obtainMessage.arg1 = (int) j;
            obtainMessage.arg2 = i;
            obtainMessage.obj = bundle;
            SASocket.this.d.sendMessage(obtainMessage);
        }
    }

    /* loaded from: classes2.dex */
    private final class ServiceConnectionCallback extends ISAServiceConnectionCallback.Stub {
        private ServiceConnectionCallback() {
        }

        @Override // com.samsung.accessory.api.ISAServiceConnectionCallback
        public final void onConnectionLost(Bundle bundle) throws RemoteException {
            if (!bundle.containsKey("errorcode")) {
                Log.e(SASocket.a, "onConnectionLost with no error code!");
                return;
            }
            Message obtainMessage = SASocket.this.d.obtainMessage(1);
            obtainMessage.arg1 = bundle.getInt("errorcode");
            SASocket.this.d.sendMessage(obtainMessage);
        }

        @Override // com.samsung.accessory.api.ISAServiceConnectionCallback
        public final void onConnectionResponse(Bundle bundle) throws RemoteException {
            Message obtainMessage = SASocket.this.d.obtainMessage(4);
            obtainMessage.arg1 = bundle.getInt("errorcode", 1280);
            obtainMessage.obj = bundle.getString("connectionId", null);
            SASocket.this.d.sendMessage(obtainMessage);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public interface a {
        void a(SAPeerAgent sAPeerAgent, int i);

        void a(SAPeerAgent sAPeerAgent, SASocket sASocket);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static final class b extends Handler {
        private SASocket a;

        public b(SASocket sASocket, Looper looper) {
            super(looper);
            this.a = sASocket;
        }

        public final synchronized void a() {
            super.getLooper().quit();
            this.a = null;
        }

        @Override // android.os.Handler
        public final synchronized void handleMessage(Message message) {
            int i = message.what;
            if (i == 1) {
                SASocket.a(this.a, message.arg1);
            } else if (i == 2) {
                SASocket.a(this.a, message.arg1, message.arg2, (Bundle) message.obj);
            } else if (i != 4) {
                Log.e(SASocket.a, "Invalid message: " + message.what);
            } else {
                SASocket.a(this.a, (String) message.obj, message.arg1);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public SASocket(String str) {
    }

    private static String a(String str, int i) {
        return new StringBuilder(30).append(str).append("_").append(i).toString();
    }

    private void a(int i, f fVar, boolean z, int i2) throws IOException {
        e b2 = fVar.b();
        try {
            try {
                if (this.g != 1) {
                    Log.w(a, "Data send failed, connection closed!");
                    throw new IOException("Failed to send, connection closed!");
                }
                int a2 = this.f.a(this.b, i, b2.d(), z, b2.e(), b2.f(), b2.g(), i2);
                if (a2 == 0) {
                    Log.d(a, "Data sent [" + b2.e() + "] : " + b2.f() + " : " + fVar.c());
                } else if (a2 == 2561) {
                    this.g = 2;
                    Log.e(a, "Write failed: Connection closed");
                    throw new IOException("Write failed:Connection already closed");
                } else if (a2 == 2566) {
                    Log.e(a, "Write failed. Attempt to write on invalid channel:".concat(String.valueOf(i)));
                    throw new IllegalArgumentException("Write failed. Attempt to write on invalid channel:".concat(String.valueOf(i)));
                } else if (a2 != 2567) {
                } else {
                    Log.e(a, "Write failed: Timed out!");
                    close();
                    throw new IOException("Write failed: Timed out!");
                }
            } catch (d e) {
                Log.e(a, "Send failed!", e);
                throw new IOException("Send Failed", e);
            }
        } finally {
            b2.h();
        }
    }

    private synchronized void a(int i, byte[] bArr, int i2) throws IOException {
        if (i < 0) {
            Log.e(a, "Send Failed : there is no service channel at the index");
        } else if (this.g != 1) {
            throw new IOException("Send failed. Socket already closed");
        } else {
            if (bArr == null) {
                Log.e(a, "sendData: data is null");
                throw new IllegalArgumentException("Invalid data to send:NULL");
            } else if (bArr.length == 0) {
                Log.e(a, "sendData: data length is 0");
                throw new IllegalArgumentException("Invalaid data length 0");
            } else if (bArr.length > this.c.getMaxAllowedDataSize()) {
                Log.e(a, "Data too long:" + bArr.length);
                throw new IllegalArgumentException("Data Too long! size:" + bArr.length + " Max allowed Size:" + this.c.getMaxAllowedDataSize() + ". check SAPeerAgent.getMaxAllowedDataSize()");
            } else {
                Log.d(a, "Sending data: " + bArr.length + " bytes");
                f fVar = new f(1, a(this.b, i));
                try {
                    fVar.a(k.b(), k.d(), getConnectedPeerAgent().b(), 0, bArr);
                    while (fVar.a() != null) {
                        a(i, fVar, false, i2);
                    }
                    fVar.d();
                } catch (IOException e) {
                    Log.e(a, "Send Blob failed", e);
                    throw e;
                }
            }
        }
    }

    static /* synthetic */ void a(SASocket sASocket, int i) {
        String str;
        String str2;
        sASocket.g = i == 2048 ? 3 : 2;
        sASocket.onServiceConnectionLost(i);
        if (i == 512) {
            str = a;
            str2 = "onServiceConnectionLost() -> CONNECTION_LOST_UNKNOWN_REASON";
        } else if (i == 513) {
            str = a;
            str2 = "onServiceConnectionLost() -> CONNECTION_LOST_PEER_DISCONNECTED";
        } else if (i == 521) {
            str = a;
            str2 = "onServiceConnectionLost() -> CONNECTION_LOST_DEVICE_DETACHED";
        } else if (i == 522) {
            str = a;
            str2 = "onServiceConnectionLost() -> CONNECTION_LOST_RETRANSMISSION_FAILED";
        } else if (i != 2048) {
            Log.w(a, "onServiceConnectionLost() error_code: ".concat(String.valueOf(i)));
            sASocket.c();
        } else {
            str = a;
            str2 = "onServiceConnectionLost() -> ERROR_FATAL";
        }
        Log.i(str, str2);
        sASocket.c();
    }

    static /* synthetic */ void a(SASocket sASocket, int i, int i2, Bundle bundle) {
        if (sASocket.g != 1) {
            Log.w(a, "Ignoring data, socket is not yet established");
            return;
        }
        byte[] byteArray = bundle.getByteArray("com.samsung.accessory.adapter.extra.READ_BYTES");
        if (byteArray == null) {
            Log.e(a, "Failed to reassemble! - null data received!");
            return;
        }
        int i3 = bundle.getInt("com.samsung.accessory.adapter.extra.READ_LENGHT");
        int i4 = bundle.getInt("com.samsung.accessory.adapter.extra.READ_OFFSET");
        String a2 = a(sASocket.b, i);
        String str = a;
        Log.d(str, "handleIncomingData: " + byteArray.length + " [" + i4 + ", " + i3 + "]");
        try {
            try {
                int a3 = i.a(a2, sASocket.c.getAccessory().a(), i2, byteArray, i4, i3);
                if (a3 == 1) {
                    sASocket.onReceive(i, i.a(a2));
                } else if (a3 == 3) {
                    Log.e(str, "Failed to reassemble!: closing down the connection");
                    sASocket.close();
                }
                sASocket.f.b(byteArray);
                if (a3 != 2) {
                    i.b(a2);
                }
            } catch (IOException e) {
                Log.e(a, "Error in onRead!", e);
                sASocket.f.b(byteArray);
                if (-1 != 2) {
                    i.b(a2);
                }
            }
        } catch (Throwable th) {
            sASocket.f.b(byteArray);
            if (-1 != 2) {
                i.b(a2);
            }
            throw th;
        }
    }

    static /* synthetic */ void a(SASocket sASocket, String str, int i) {
        a aVar = sASocket.e;
        if (aVar == null) {
            Log.w(a, "Connection status callback not found! Ignoring response");
        } else if (str == null) {
            Log.w(a, "connectionId is null so cleaning up");
            sASocket.e.a(sASocket.c, i);
            sASocket.c();
        } else {
            sASocket.b = str;
            sASocket.g = 1;
            aVar.a(sASocket.c, sASocket);
        }
    }

    private boolean a(String str, String str2) {
        HandlerThread handlerThread = new HandlerThread("Socket:" + str + "_" + str2);
        handlerThread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() { // from class: com.samsung.android.sdk.accessory.SASocket.3
            @Override // java.lang.Thread.UncaughtExceptionHandler
            public final void uncaughtException(Thread thread, final Throwable th) {
                Log.e(SASocket.a, "Exception in Socket background thread:" + thread.getName() + "exception: " + th.getMessage());
                thread.interrupt();
                new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.samsung.android.sdk.accessory.SASocket.3.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        throw new RuntimeException(th);
                    }
                });
            }
        });
        handlerThread.start();
        Looper looper = handlerThread.getLooper();
        if (looper == null) {
            Log.e(a, "Failed get Looper for Socket: initiator:" + str + " Peer Id:" + str2);
            return false;
        }
        this.d = new b(this, looper);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        this.d.removeCallbacksAndMessages(null);
        this.d.a();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a() {
        if (this.g == 1) {
            this.g = 3;
            Message obtainMessage = this.d.obtainMessage(1);
            obtainMessage.arg1 = 2048;
            this.d.sendMessage(obtainMessage);
            Log.i(a, "Socket:" + this.b + " has been force closed!");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(final String str, final SAPeerAgent sAPeerAgent, SAAdapter sAAdapter, a aVar) {
        this.c = sAPeerAgent;
        this.e = aVar;
        this.f = sAAdapter;
        a(str, sAPeerAgent.getPeerId());
        this.d.post(new Runnable() { // from class: com.samsung.android.sdk.accessory.SASocket.1
            @Override // java.lang.Runnable
            public void run() {
                int a2;
                if (!sAPeerAgent.isFeatureEnabled(3)) {
                    Log.i(SASocket.a, "Connection request failed for peer:" + sAPeerAgent.getPeerId() + " Reason: PeerAgent does not support Socket communication");
                    if (SASocket.this.e != null) {
                        SASocket.this.e.a(sAPeerAgent, 1025);
                    }
                    SASocket.this.c();
                    return;
                }
                try {
                    a2 = SASocket.this.f.a(str, sAPeerAgent, new ServiceConnectionCallback(), new ServiceChannelCallback());
                } catch (d e) {
                    Log.e(SASocket.a, "Failed to initiate connection!", e);
                    a2 = e.a();
                }
                if (a2 == 0) {
                    Log.i(SASocket.a, "Connection request enqued successfully for peer:" + sAPeerAgent.getPeerId());
                    return;
                }
                Log.i(SASocket.a, "Connection request failed for peer:" + sAPeerAgent.getPeerId() + " Reason:" + a2 + " Cleaning up now");
                if (SASocket.this.e != null) {
                    SASocket.this.e.a(sAPeerAgent, a2);
                }
                SASocket.this.c();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void b(final String str, final SAPeerAgent sAPeerAgent, final SAAdapter sAAdapter, a aVar) {
        this.c = sAPeerAgent;
        this.f = sAAdapter;
        this.e = aVar;
        a(str, sAPeerAgent.getPeerId());
        this.d.post(new Runnable() { // from class: com.samsung.android.sdk.accessory.SASocket.2
            @Override // java.lang.Runnable
            public final void run() {
                try {
                    SAAdapter sAAdapter2 = sAAdapter;
                    String str2 = str;
                    SAPeerAgent sAPeerAgent2 = sAPeerAgent;
                    String a2 = sAAdapter2.a(str2, sAPeerAgent2, sAPeerAgent2.c(), new ServiceConnectionCallback(), new ServiceChannelCallback());
                    Log.d(SASocket.a, "Connection accepted successfully. connection Id:".concat(String.valueOf(a2)));
                    SASocket.a(SASocket.this, a2, 0);
                } catch (d e) {
                    Log.e(SASocket.a, "Failed to accept service connection: " + e.getMessage());
                    SASocket.a(SASocket.this, (String) null, e.a());
                }
            }
        });
    }

    public void close() {
        if (this.g != 1) {
            Log.i(a, "Connection is already closed");
            return;
        }
        this.g = 2;
        String str = a;
        Log.i(str, "Application requested to close socket for Peer:" + this.c.getPeerId());
        try {
            int b2 = this.f.b(this.b);
            if (b2 == 2561) {
                Log.i(str, "Connection is already closed");
            } else if (b2 == 0) {
                Log.i(str, "Connection " + this.b + " close requested successfully");
            }
        } catch (d e) {
            Log.e(a, "Failed to close connection!", e);
        }
    }

    public SAPeerAgent getConnectedPeerAgent() {
        return this.c;
    }

    public boolean isConnected() {
        return this.g == 1;
    }

    public abstract void onError(int i, String str, int i2);

    public abstract void onReceive(int i, byte[] bArr);

    protected abstract void onServiceConnectionLost(int i);

    public synchronized void secureSend(int i, byte[] bArr) throws IOException {
        if (i < 0) {
            Log.e(a, "Send Failed : there is no service channel at the index");
        } else if (this.g != 1) {
            throw new IOException("Secure Send failed. Socket already closed");
        } else {
            if (bArr == null) {
                Log.e(a, "secureSend: data is null");
                throw new IllegalArgumentException("Invalid data to send:NULL");
            } else if (bArr.length == 0) {
                Log.e(a, "SecureSend: data length is 0");
                throw new IllegalArgumentException("Invalaid data length 0");
            } else if (bArr.length > this.c.getMaxAllowedDataSize()) {
                Log.e(a, "SecureSend:Data too long:" + bArr.length);
                throw new IllegalArgumentException("Secure send:Data Too long! size:" + bArr.length + " Max allowed Size:" + this.c.getMaxAllowedDataSize() + ". check SAPeerAgent.getMaxAllowedDataSize()");
            } else {
                Log.d(a, "Sending data:" + bArr.length + " bytes");
                f fVar = new f(1, a(this.b, i));
                try {
                    fVar.a(k.b(), k.d(), getConnectedPeerAgent().b(), getConnectedPeerAgent().getAccessory().d(), bArr);
                    while (fVar.a() != null) {
                        a(i, fVar, true, 3);
                    }
                    fVar.d();
                } catch (IOException e) {
                    Log.e(a, "Send Blob failed", e);
                    throw e;
                }
            }
        }
    }

    public void send(int i, byte[] bArr) throws IOException {
        a(i, bArr, 3);
    }

    public void sendCompressed(int i, byte[] bArr) throws IOException {
        a(i, bArr, 1);
    }

    public void sendUncompressed(int i, byte[] bArr) throws IOException {
        a(i, bArr, 2);
    }
}
