package com.samsung.android.sdk.accessory;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Process;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.os.TransactionTooLargeException;
import android.util.Log;
import androidx.work.WorkRequest;
import com.samsung.accessory.api.IDeathCallback;
import com.samsung.accessory.api.ISAFrameworkManagerV2;
import com.samsung.accessory.api.ISAMexCallback;
import com.samsung.accessory.api.ISAPeerAgentAuthCallback;
import com.samsung.accessory.api.ISAPeerAgentCallback;
import com.samsung.accessory.api.ISAServiceChannelCallback;
import com.samsung.accessory.api.ISAServiceConnectionCallback;
import com.samsung.accessory.api.ISAServiceConnectionIndicationCallback;
import java.util.HashSet;
import java.util.Set;
/* loaded from: classes2.dex */
public final class SAAdapter {
    private static final String a = "[SA_SDK]" + SAAdapter.class.getSimpleName();
    private static SAAdapter b;
    private Context d;
    private ISAFrameworkManagerV2 g;
    private IDeathCallback i;
    private ResultReceiver j;
    private long e = -1;
    private int f = 0;
    private a h = new a((byte) 0);
    private Set<b> c = new HashSet();
    private ServiceConnectionIndicationCallback k = new ServiceConnectionIndicationCallback();

    /* loaded from: classes2.dex */
    private static final class DeathCallbackStub extends IDeathCallback.Stub {
        private String mPackageName;

        public DeathCallbackStub(String str) {
            if (str == null) {
                throw new IllegalArgumentException("Invalid packageName:null");
            }
            this.mPackageName = str;
        }

        @Override // com.samsung.accessory.api.IDeathCallback
        public final String getAppName() throws RemoteException {
            return this.mPackageName;
        }
    }

    /* loaded from: classes2.dex */
    private final class ServiceConnectionIndicationCallback extends ISAServiceConnectionIndicationCallback.Stub {
        private ServiceConnectionIndicationCallback() {
        }

        @Override // com.samsung.accessory.api.ISAServiceConnectionIndicationCallback
        public final void onServiceConnectionRequested(Bundle bundle) throws RemoteException {
            byte[] byteArray = bundle.getByteArray("peerAgent");
            if (byteArray == null) {
                Log.e(SAAdapter.a, "marshalled accessory byte[] is null!");
                return;
            }
            Parcel obtain = Parcel.obtain();
            if (obtain == null) {
                Log.e(SAAdapter.a, "Failed to obtain parcel");
                return;
            }
            obtain.unmarshall(byteArray, 0, byteArray.length);
            obtain.setDataPosition(0);
            SAPeerAgent createFromParcel = SAPeerAgent.CREATOR.createFromParcel(obtain);
            obtain.recycle();
            long j = bundle.getLong("transactionId", 0L);
            String string = bundle.getString("agentId");
            String string2 = bundle.getString("agentImplclass");
            if (string2 == null) {
                Log.e(SAAdapter.a, "Implementation class not available in intent. Ignoring request");
                return;
            }
            Intent intent = new Intent("com.samsung.accessory.action.SERVICE_CONNECTION_REQUESTED");
            intent.putExtra("transactionId", j);
            intent.putExtra("agentId", string);
            intent.putExtra("peerAgent", createFromParcel);
            intent.putExtra("agentImplclass", string2);
            m.a(SAAdapter.this.d.getApplicationContext()).a(intent, string2, false);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static class a implements ServiceConnection {
        private a() {
        }

        /* synthetic */ a(byte b) {
            this();
        }

        @Override // android.content.ServiceConnection
        public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            synchronized (SAAdapter.b) {
                if (iBinder != null) {
                    Log.d(SAAdapter.a, "Accessory service connected");
                    SAAdapter.b.g = ISAFrameworkManagerV2.Stub.asInterface(iBinder);
                    try {
                        Bundle makeFrameworkConnection = SAAdapter.b.g.makeFrameworkConnection(Process.myPid(), SAAdapter.b.d.getPackageName(), SAAdapter.b.i, 9, SAAdapter.b.k);
                        if (makeFrameworkConnection == null) {
                            Log.e(SAAdapter.a, "Unable to setup client Identity.Invalid response from Framework");
                            return;
                        }
                        k.d(makeFrameworkConnection.getInt("fwk_version", 321));
                        SAAdapter.b.e = makeFrameworkConnection.getLong("clientId", -1L);
                        if (SAAdapter.b.e == -1) {
                            SAAdapter.b.a(-1);
                            Log.e(SAAdapter.a, "Unable to setup client Identity.Error:" + makeFrameworkConnection.getInt("errorcode"));
                            return;
                        }
                        Log.i(SAAdapter.a, "Received Client ID:" + SAAdapter.b.e);
                        SAAdapter.b.a(1);
                        if (makeFrameworkConnection.getInt("com.samsung.accessory.adapter.extra.PROCESS_ID") == Process.myPid() && k.e() >= 79) {
                            SAAdapter.b.j = SAAdapter.b.g.getClientCallback(SAAdapter.b.e);
                            Log.i(SAAdapter.a, "Running in SAP process, Updated my proxy: " + SAAdapter.b.j);
                        }
                        k.a();
                        k.a(makeFrameworkConnection.getInt("com.samsung.accessory.adapter.extra.HEADER_LEN"));
                        k.c(makeFrameworkConnection.getInt("com.samsung.accessory.adapter.extra.FOOTER_LEN"));
                        k.b(makeFrameworkConnection.getInt("com.samsung.accessory.adapter.extra.MSG_HEADER_LEN"));
                    } catch (RemoteException e) {
                        Log.e(SAAdapter.a, "Unable to setup client Identity.", e);
                        SAAdapter.b.a(-1);
                        SAAdapter.b.a(e);
                    }
                }
                SAAdapter.b.notifyAll();
                SAAdapter.b.f();
            }
        }

        @Override // android.content.ServiceConnection
        public final void onServiceDisconnected(ComponentName componentName) {
            synchronized (SAAdapter.b) {
                Log.w(SAAdapter.a, "Accessory service disconnected");
                SAAdapter.b.a(0);
                SAAdapter.b.a(false);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public interface b {
        void a();

        void b();

        void c() throws d;
    }

    private SAAdapter(Context context) {
        this.d = context;
        this.i = new DeathCallbackStub(context.getPackageName());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized SAAdapter a(Context context) {
        SAAdapter sAAdapter;
        synchronized (SAAdapter.class) {
            if (b == null) {
                b = new SAAdapter(context);
            }
            sAAdapter = b;
        }
        return sAAdapter;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void a(RemoteException remoteException) {
        if (!(remoteException instanceof TransactionTooLargeException)) {
            Log.w(a, "Remote call falied", remoteException);
            return;
        }
        Log.w(a, "Remote call falied, binder transaction buffer low", remoteException);
        a(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void a(boolean z) {
        if (z) {
            e();
        }
        if (b.f == 1) {
            this.d.unbindService(this.h);
        }
        b.e = -1L;
        a(0);
        SAAdapter sAAdapter = b;
        sAAdapter.g = null;
        for (b bVar : sAAdapter.c) {
            bVar.a();
        }
    }

    private synchronized int d() {
        return this.f;
    }

    private synchronized void e() {
        ISAFrameworkManagerV2 iSAFrameworkManagerV2 = this.g;
        if (iSAFrameworkManagerV2 == null) {
            Log.i(a, "Binding to framework does not exists");
            return;
        }
        try {
            iSAFrameworkManagerV2.tearFrameworkConnection(this.e);
            a(false);
        } catch (RemoteException e) {
            Log.w(a, "Failed to tear framework connection", e);
            a(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void f() {
        for (b bVar : b.c) {
            bVar.b();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int a(String str, int i, byte[] bArr, boolean z, int i2, int i3, int i4, int i5) throws d {
        if (b.g == null) {
            a();
        }
        try {
            return i5 == 2 ? this.g.sendUncompressed(this.e, str, i, bArr, z, i2, i3, i4) : i5 == 1 ? this.g.sendCompressed(this.e, str, i, bArr, z, i2, i3, i4) : this.g.send(this.e, str, i, bArr, z, i2, i3, i4);
        } catch (RemoteException e) {
            Log.w(a, "Failed send data for connection:".concat(String.valueOf(str)), e);
            a(e);
            throw new d(2048, "send: Remote call failed");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int a(String str, ISAPeerAgentCallback iSAPeerAgentCallback) throws d {
        if (b.g == null) {
            a();
        }
        try {
            ISAFrameworkManagerV2 iSAFrameworkManagerV2 = b.g;
            if (iSAFrameworkManagerV2 != null) {
                return iSAFrameworkManagerV2.findPeerAgents(this.e, -1L, str, iSAPeerAgentCallback);
            }
            throw new d(2048, "findPeerAgents:mServiceProxy is null");
        } catch (RemoteException e) {
            Log.w(a, "Failed to initiate peer discovery", e);
            a(e);
            throw new d(2048, "findPeerAgents:Remote call failed");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int a(String str, SAPeerAgent sAPeerAgent, long j) throws d {
        if (b.g == null) {
            a();
        }
        try {
            return this.g.rejectServiceConnection(this.e, str, sAPeerAgent, j);
        } catch (RemoteException e) {
            Log.w(a, "Failed to reject service connection", e);
            a(e);
            throw new d(2048, "rejectServiceConnection:Remote call failed");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int a(String str, SAPeerAgent sAPeerAgent, ISAPeerAgentAuthCallback iSAPeerAgentAuthCallback, long j) throws d {
        if (b.g == null) {
            a();
        }
        try {
            return this.g.authenticatePeerAgent(this.e, str, sAPeerAgent, iSAPeerAgentAuthCallback, j);
        } catch (RemoteException e) {
            Log.w(a, "Failed to request peer authentication", e);
            a(e);
            throw new d(2048, "authenticatePeeragent:Remote call failed");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int a(String str, SAPeerAgent sAPeerAgent, ISAServiceConnectionCallback iSAServiceConnectionCallback, ISAServiceChannelCallback iSAServiceChannelCallback) throws d {
        if (b.g == null) {
            a();
        }
        try {
            return this.g.requestServiceConnection(this.e, str, sAPeerAgent, iSAServiceConnectionCallback, iSAServiceChannelCallback);
        } catch (RemoteException e) {
            Log.w(a, "Failed to request service connection", e);
            a(e);
            throw new d(2048, "requestServiceConnection:Remote call failed");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int a(String str, String str2, long j, byte[] bArr, boolean z, int i, int i2, int i3, int i4) throws d {
        if (b.g == null) {
            a();
        }
        if (k.i()) {
            try {
                return b.g.sendMessage(this.e, str, str2, j, bArr, z, i2, i3, i4, i);
            } catch (RemoteException e) {
                Log.w(a, "Failed to send messages ".concat(String.valueOf(e)));
                a(e);
                throw new d(2048, "sendMessage: Remote call failed");
            }
        }
        return -1797;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized String a(String str) throws d {
        if (b.g == null) {
            a();
        }
        try {
            ISAFrameworkManagerV2 iSAFrameworkManagerV2 = b.g;
            Bundle localAgentId = iSAFrameworkManagerV2 != null ? iSAFrameworkManagerV2.getLocalAgentId(this.e, str) : null;
            if (localAgentId != null) {
                if (!localAgentId.containsKey("errorcode")) {
                    String string = localAgentId.getString("agentId");
                    if (string != null) {
                        return string;
                    }
                    throw new RuntimeException("Get Local agent ID:Invalid response - localAgentID:null");
                } else if (localAgentId.getInt("errorcode") == 769) {
                    Log.d(a, "Trying again after cleanup");
                    a(false);
                    return a(str);
                } else {
                    throw new d(localAgentId.getInt("errorcode"), "Failed to fetch localAgent ID");
                }
            }
            throw new RuntimeException("Get Local agent ID:Invalid response from accessory framework - null");
        } catch (RemoteException e) {
            Log.w(a, "Failed to fetch localAgent ID", e);
            a(e);
            throw new d(2048, "getLocalAgentId:Remote call failed");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String a(String str, SAPeerAgent sAPeerAgent, long j, ISAServiceConnectionCallback iSAServiceConnectionCallback, ISAServiceChannelCallback iSAServiceChannelCallback) throws d {
        if (b.g == null) {
            a();
        }
        try {
            Bundle acceptServiceConnection = this.g.acceptServiceConnection(this.e, str, sAPeerAgent, j, iSAServiceConnectionCallback, iSAServiceChannelCallback);
            if (acceptServiceConnection == null) {
                Log.e(a, "acceptServiceConnection:Invalid response from Accessory Framework:".concat(String.valueOf(acceptServiceConnection)));
                throw new RuntimeException("acceptServiceConnection:Invalid response from Accessory Framework:".concat(String.valueOf(acceptServiceConnection)));
            } else if (acceptServiceConnection.containsKey("errorcode")) {
                throw new d(acceptServiceConnection.getInt("errorcode"), "Failed to accept connection request!");
            } else {
                String string = acceptServiceConnection.getString("connectionId");
                if (string != null) {
                    return string;
                }
                Log.e(a, "acceptServiceConnection:Invalid response from Accessory Framework- connectionId:".concat(String.valueOf(string)));
                throw new RuntimeException("acceptServiceConnection:Invalid response from Accessory Framework- connectionId:".concat(String.valueOf(string)));
            }
        } catch (RemoteException e) {
            Log.w(a, "Failed to accept service connection", e);
            a(e);
            throw new d(2048, "acceptServiceConnection:Remote call failed");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized void a() throws d {
        if (b.g == null) {
            a(0);
            try {
                Intent intent = new Intent(ISAFrameworkManagerV2.class.getName());
                intent.setPackage(k.a);
                for (int i = 1; b.e == -1 && d() == 0 && i <= 5; i++) {
                    SAAdapter sAAdapter = b;
                    if (!sAAdapter.d.bindService(intent, sAAdapter.h, 1)) {
                        Log.e(a, "getDefaultAdapter: Binding to Accessory service failed!");
                        a(-1);
                        throw new d(2048, "Is the Samsung Accessory Service Framework installed?!");
                    }
                    try {
                        Log.i(a, "getDefaultAdapter: About start waiting");
                        b.wait(WorkRequest.MIN_BACKOFF_MILLIS);
                    } catch (InterruptedException e) {
                        a(-1);
                        throw new d("Failed to Bind to Accessory Framework - Action interrupted!", e);
                    }
                }
                if (b.g != null) {
                    Log.i(a, "Application is now connected to Accessory Framework!");
                    return;
                }
                Log.e(a, "getDefaultAdapter: Service Connection proxy is null!");
                a(-1);
                throw new d(2048, "Unable to bind to Samsung Accessory Service!");
            } catch (SecurityException unused) {
                Log.e(a, "getDefaultAdapter: Permission denied! Binding to Accessory service failed!");
                a(-1);
                if (!k.a(b.d)) {
                    throw new d(2304, "Permission denied to bind to Samsung Accessory Service! Please add permission and try again.");
                }
                throw new d(2305, "Permission validation failed to bind to Samsung Accessory Service! Please re-install the application and try again.");
            }
        }
    }

    final synchronized void a(int i) {
        this.f = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(long j, int i, int i2) throws d {
        if (b.g == null) {
            a();
        }
        try {
            ISAFrameworkManagerV2 iSAFrameworkManagerV2 = b.g;
            if (iSAFrameworkManagerV2 != null) {
                iSAFrameworkManagerV2.sendMessageDeliveryStatus(this.e, j, i, i2);
            }
        } catch (RemoteException e) {
            Log.w(a, "Failed to send message delivery status", e);
            a(e);
            throw new d(2048, "sendMessageDeliveryStatus: Remote call failed");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized void a(b bVar) {
        this.c.add(bVar);
        Log.d(a, "Agent callback added. Current size - " + this.c.size());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(String str, ISAMexCallback iSAMexCallback) throws d {
        if (b.g == null) {
            a();
        }
        try {
            ISAFrameworkManagerV2 iSAFrameworkManagerV2 = b.g;
            if (iSAFrameworkManagerV2 != null) {
                iSAFrameworkManagerV2.registerMexCallback(this.e, str, iSAMexCallback);
            }
        } catch (RemoteException e) {
            Log.w(a, "Failed to register mex callback", e);
            a(e);
            throw new d(2048, "registerMexCallback: Remote call failed");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized void a(byte[] bArr) throws d {
        if (b.g == null) {
            a();
        }
        try {
            ISAFrameworkManagerV2 iSAFrameworkManagerV2 = b.g;
            if (iSAFrameworkManagerV2 != null) {
                iSAFrameworkManagerV2.registerComponent(this.e, bArr);
            }
            for (b bVar : this.c) {
                bVar.c();
            }
        } catch (RemoteException e) {
            Log.w(a, "Service registration call failed", e);
            a(e);
            throw new d(2048, "registerServices:Remote call failed");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int b(String str) throws d {
        if (b.g == null) {
            a();
        }
        try {
            return this.g.closeServiceConnection(this.e, str);
        } catch (RemoteException e) {
            Log.w(a, "Failed to close service connection", e);
            a(e);
            throw new d(2048, "closeServiceConnection:Remote call failed");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized void b(b bVar) {
        this.c.remove(bVar);
        String str = a;
        Log.d(str, "Agent callback removed. Current size - " + this.c.size());
        if (this.c.isEmpty()) {
            Log.i(str, "All clients have unregistered.Disconnection from Accessory Framework.");
            a(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized void b(byte[] bArr) {
        if (b.j != null) {
            Bundle bundle = new Bundle();
            bundle.putByteArray("com.samsung.accessory.adapter.extra.READ_BYTES", bArr);
            b.j.send(0, bundle);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void c(String str) {
        if (b.g == null) {
            Log.w(a, "Binding to framework does not exists");
            return;
        }
        try {
            this.g.cleanupAgent(this.e, str);
        } catch (RemoteException e) {
            Log.w(a, "Failed to cleanup agent details", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void d(String str) throws d {
        ISAFrameworkManagerV2 iSAFrameworkManagerV2 = b.g;
        if (iSAFrameworkManagerV2 != null) {
            try {
                iSAFrameworkManagerV2.unregisterMexCallback(this.e, str);
            } catch (RemoteException e) {
                Log.w(a, "Failed to unregister mex callback", e);
                a(e);
                throw new d(2048, "unregisterMexCallback: Remote call failed");
            }
        }
    }
}
