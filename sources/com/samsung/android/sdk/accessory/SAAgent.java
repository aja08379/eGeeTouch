package com.samsung.android.sdk.accessory;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import com.samsung.accessory.api.ISAPeerAgentAuthCallback;
import com.samsung.accessory.api.ISAPeerAgentCallback;
import com.samsung.android.sdk.SsdkUnsupportedException;
import com.samsung.android.sdk.accessory.SAAdapter;
import com.samsung.android.sdk.accessory.SASocket;
import java.lang.Thread;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
/* loaded from: classes2.dex */
public abstract class SAAgent extends Service {
    public static final String ACTION_REGISTRATION_REQUIRED = "com.samsung.accessory.action.REGISTER_AGENT";
    public static final String ACTION_SERVICE_CONNECTION_REQUESTED = "com.samsung.accessory.action.SERVICE_CONNECTION_REQUESTED";
    public static final int AUTHENTICATION_FAILURE_PEER_AGENT_NOT_SUPPORTED = 1546;
    public static final int AUTHENTICATION_FAILURE_TOKEN_NOT_GENERATED = 1545;
    public static final int AUTHENTICATION_SUCCESS = 0;
    public static final int CONNECTION_ALREADY_EXIST = 1029;
    public static final int CONNECTION_DUPLICATE_REQUEST = 1040;
    public static final int CONNECTION_FAILURE_DEVICE_UNREACHABLE = 1028;
    public static final int CONNECTION_FAILURE_INVALID_PEERAGENT = 1033;
    public static final int CONNECTION_FAILURE_NETWORK = 1280;
    public static final int CONNECTION_FAILURE_PEERAGENT_NO_RESPONSE = 1030;
    public static final int CONNECTION_FAILURE_PEERAGENT_REJECTED = 1031;
    public static final int CONNECTION_FAILURE_SERVICE_LIMIT_REACHED = 1037;
    public static final int CONNECTION_SUCCESS = 0;
    public static final int ERROR_CONNECTION_INVALID_PARAM = 1025;
    public static final int ERROR_FATAL = 2048;
    public static final int ERROR_PERMISSION_DENIED = 2304;
    public static final int ERROR_PERMISSION_FAILED = 2305;
    public static final int ERROR_SDK_NOT_INITIALIZED = 2049;
    public static final int FINDPEER_DEVICE_NOT_CONNECTED = 1793;
    public static final int FINDPEER_DUPLICATE_REQUEST = 3085;
    public static final int FINDPEER_SERVICE_NOT_FOUND = 1794;
    public static final int PEER_AGENT_AVAILABLE = 1;
    public static final int PEER_AGENT_FOUND = 0;
    public static final int PEER_AGENT_UNAVAILABLE = 2;
    b a;
    private SAAdapter b;
    private SA c;
    private PeerAgentCallback d;
    private AuthenticationCallback e;
    private a f;
    private c g;
    private List<SASocket> h;
    private Set<SAPeerAgent> i;
    private String j;
    private String k;
    private SAMessage l;
    private Class<? extends SASocket> m;
    private com.samsung.android.sdk.accessory.c n = null;
    private n o = null;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class AuthenticationCallback extends ISAPeerAgentAuthCallback.Stub {
        private AuthenticationCallback() {
        }

        @Override // com.samsung.accessory.api.ISAPeerAgentAuthCallback
        public void onPeerAgentAuthenticated(Bundle bundle) throws RemoteException {
            Log.v("[SA_SDK]SAAgent", "Received Authentication response");
            if (SAAgent.this.a == null) {
                Log.w("[SA_SDK]SAAgent", "onPeerAgentAuthenticated: mBackgroundWorker is null!");
                return;
            }
            Message obtainMessage = SAAgent.this.a.obtainMessage(10);
            obtainMessage.setData(bundle);
            SAAgent.this.a.sendMessage(obtainMessage);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class PeerAgentCallback extends ISAPeerAgentCallback.Stub {
        private PeerAgentCallback() {
        }

        @Override // com.samsung.accessory.api.ISAPeerAgentCallback
        public void onPeerAgentUpdated(Bundle bundle) throws RemoteException {
            Log.v("[SA_SDK]SAAgent", "Received peer agent update");
            bundle.setClassLoader(SAPeerAgent.class.getClassLoader());
            if (!bundle.containsKey("peerAgents")) {
                Log.e("[SA_SDK]SAAgent", "No peer agents in PeerAgent update callback!");
                return;
            }
            ArrayList<SAPeerAgent> parcelableArrayList = bundle.getParcelableArrayList("peerAgents");
            int i = bundle.getInt("peerAgentStatus");
            if (parcelableArrayList == null) {
                Log.e("[SA_SDK]SAAgent", "Peer Update - invalid peer agent list from Accessory Framework");
            } else if (i == 105 || i == 106) {
                Log.i("[SA_SDK]SAAgent", parcelableArrayList.size() + " Peer agent(s) updated for:" + getClass().getName());
                for (SAPeerAgent sAPeerAgent : parcelableArrayList) {
                    Log.i("[SA_SDK]SAAgent", "Peer ID:" + sAPeerAgent.getPeerId() + "Container Id:" + sAPeerAgent.getContainerId() + " Accessory" + com.samsung.android.sdk.accessory.c.a(sAPeerAgent.getAccessory().getAccessoryId(), sAPeerAgent.getAccessory().getAddress()));
                }
                if (SAAgent.this.a == null) {
                    Log.w("[SA_SDK]SAAgent", "onPeerAgentUpdated: mBackgroundWorker is null!");
                    return;
                }
                Message obtainMessage = SAAgent.this.a.obtainMessage();
                obtainMessage.what = 4;
                obtainMessage.arg1 = i == 105 ? 1 : 2;
                obtainMessage.obj = parcelableArrayList.toArray(new SAPeerAgent[parcelableArrayList.size()]);
                SAAgent.this.a.sendMessage(obtainMessage);
            } else {
                Log.e("[SA_SDK]SAAgent", "Peer Update - invalid peer status from Accessory Framework:".concat(String.valueOf(i)));
            }
        }

        @Override // com.samsung.accessory.api.ISAPeerAgentCallback
        public void onPeerAgentsFound(Bundle bundle) throws RemoteException {
            Log.v("[SA_SDK]SAAgent", "FindPeer response received.");
            bundle.setClassLoader(SAPeerAgent.class.getClassLoader());
            if (bundle.containsKey("errorcode")) {
                int i = bundle.getInt("errorcode");
                Log.e("[SA_SDK]SAAgent", "Peer Not Found(" + i + ") for: " + getClass().getName());
                if (SAAgent.this.a == null) {
                    Log.w("[SA_SDK]SAAgent", "onPeersAgentsFound: mBackgroundWorker is null!");
                    return;
                }
                Message obtainMessage = SAAgent.this.a.obtainMessage();
                obtainMessage.what = 3;
                obtainMessage.arg1 = i;
                SAAgent.this.a.sendMessage(obtainMessage);
                return;
            }
            ArrayList<SAPeerAgent> parcelableArrayList = bundle.getParcelableArrayList("peerAgents");
            if (parcelableArrayList == null) {
                Log.e("[SA_SDK]SAAgent", "Find Peer - invalid response from Accessory Framework");
                return;
            }
            Log.i("[SA_SDK]SAAgent", parcelableArrayList.size() + " Peer agent(s) found for:" + getClass().getName());
            for (SAPeerAgent sAPeerAgent : parcelableArrayList) {
                Log.i("[SA_SDK]SAAgent", "Peer ID:" + sAPeerAgent.getPeerId() + "Container Id:" + sAPeerAgent.getContainerId() + " Accessory" + com.samsung.android.sdk.accessory.c.a(sAPeerAgent.getAccessory().getAccessoryId(), sAPeerAgent.getAccessory().getAddress()) + " Transport:" + sAPeerAgent.getAccessory().getTransportType());
            }
            if (SAAgent.this.a == null) {
                Log.w("[SA_SDK]SAAgent", "onPeerAgentsFound: mBackgroundWorker is null!");
                return;
            }
            Message obtainMessage2 = SAAgent.this.a.obtainMessage();
            obtainMessage2.what = 3;
            obtainMessage2.arg1 = 0;
            obtainMessage2.obj = parcelableArrayList.toArray(new SAPeerAgent[parcelableArrayList.size()]);
            SAAgent.this.a.sendMessage(obtainMessage2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static class a implements SAAdapter.b {
        private SAAgent a;

        public a(SAAgent sAAgent) {
            this.a = sAAgent;
        }

        @Override // com.samsung.android.sdk.accessory.SAAdapter.b
        public final void a() {
            if (this.a.a == null) {
                Log.w("[SA_SDK]SAAgent", "onFrameworkDisconnected: mBackgroundWorker is null!");
                return;
            }
            Message obtainMessage = this.a.a.obtainMessage(11);
            obtainMessage.arg1 = 2048;
            this.a.a.sendMessage(obtainMessage);
        }

        @Override // com.samsung.android.sdk.accessory.SAAdapter.b
        public final void b() {
            this.a.a.sendEmptyMessage(17);
        }

        @Override // com.samsung.android.sdk.accessory.SAAdapter.b
        public final void c() throws d {
            this.a.a.sendEmptyMessage(14);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static class b extends Handler {
        SAAgent a;

        public b(SAAgent sAAgent, Looper looper) {
            super(looper);
            this.a = sAAgent;
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            int i = message.what;
            SAPeerAgent sAPeerAgent = null;
            if (i == 17) {
                try {
                    this.a.b();
                    return;
                } catch (d e) {
                    Log.e("[SA_SDK]SAAgent", "handleMessageReceived() - Failed to register agent with message! " + e.getMessage());
                    this.a.a(e.a(), (SAPeerAgent) null);
                    return;
                }
            }
            switch (i) {
                case 0:
                    try {
                        SAAgent.a(this.a);
                        return;
                    } catch (d e2) {
                        Log.e("[SA_SDK]SAAgent", "Binding to Accessory Framework failed", e2);
                        this.a.a(e2.a(), (SAPeerAgent) null);
                        return;
                    }
                case 1:
                    this.a.c();
                    return;
                case 2:
                    SAAgent.c(this.a);
                    return;
                case 3:
                    if (message.arg1 == 0) {
                        this.a.onFindPeerAgentsResponse((SAPeerAgent[]) message.obj, 0);
                        SAAgent.d(0);
                        return;
                    }
                    this.a.onFindPeerAgentsResponse(null, message.arg1);
                    SAAgent.d(message.arg1);
                    return;
                case 4:
                    this.a.onPeerAgentsUpdated((SAPeerAgent[]) message.obj, message.arg1);
                    SAAgent.b(message.arg1);
                    return;
                case 5:
                    SAAgent.a(this.a, (Intent) message.obj);
                    return;
                case 6:
                    SAAgent.a(this.a, (SAPeerAgent) message.obj);
                    return;
                case 7:
                    SAAgent.b(this.a, (SAPeerAgent) message.obj);
                    return;
                case 8:
                    this.a.a((SAPeerAgent) message.obj);
                    return;
                case 9:
                    SAAgent.d(this.a, (SAPeerAgent) message.obj);
                    return;
                case 10:
                    SAAgent.a(this.a, message.getData());
                    return;
                case 11:
                    if (message.obj != null && (message.obj instanceof SAPeerAgent)) {
                        sAPeerAgent = (SAPeerAgent) message.obj;
                    }
                    this.a.a(message.arg1, sAPeerAgent);
                    return;
                case 12:
                    this.a.onServiceConnectionResponse((message.obj == null || !(message.obj instanceof SAPeerAgent)) ? null : (SAPeerAgent) message.obj, null, message.arg1);
                    SAAgent.f(message.arg1);
                    return;
                case 13:
                    SAAgent.d(this.a);
                    return;
                case 14:
                    try {
                        this.a.a();
                        return;
                    } catch (d e3) {
                        Log.e("[SA_SDK]SAAgent", "Retrieving agent id failed", e3);
                        this.a.a(e3.a(), (SAPeerAgent) null);
                        return;
                    }
                default:
                    Log.w("[SA_SDK]SAAgent", "Invalid msg received: " + message.what);
                    return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public class c implements SASocket.a {
        private c() {
        }

        /* synthetic */ c(SAAgent sAAgent, byte b) {
            this();
        }

        @Override // com.samsung.android.sdk.accessory.SASocket.a
        public final void a(SAPeerAgent sAPeerAgent, int i) {
            if (i == 2048) {
                Log.w("[SA_SDK]SAAgent", "Framework disconnected during connection process!");
                SAAgent.this.a(i, sAPeerAgent);
            } else if (SAAgent.this.a == null) {
                Log.w("[SA_SDK]SAAgent", "onConnectionFailure: mBackgroundWorker is null!");
            } else {
                if (i == 1034) {
                    i = 1033;
                    SAAgent.this.a.sendMessage(SAAgent.this.a.obtainMessage(1));
                }
                Log.e("[SA_SDK]SAAgent", "Connection attempt failed wih peer:" + sAPeerAgent.getPeerId() + " reason:" + i);
                Message obtainMessage = SAAgent.this.a.obtainMessage(12);
                obtainMessage.arg1 = i;
                obtainMessage.obj = sAPeerAgent;
                SAAgent.this.a.sendMessage(obtainMessage);
            }
        }

        @Override // com.samsung.android.sdk.accessory.SASocket.a
        public final void a(SAPeerAgent sAPeerAgent, SASocket sASocket) {
            synchronized (SAAgent.this.h) {
                SAAgent.this.h.add(sASocket);
            }
            Log.i("[SA_SDK]SAAgent", "Connection success with peer:" + sAPeerAgent.getPeerId());
            SAAgent.this.onServiceConnectionResponse(sAPeerAgent, sASocket, 0);
            SAAgent.f(0);
        }
    }

    protected SAAgent(String str) {
        if (str == null || str.equalsIgnoreCase("")) {
            throw new IllegalArgumentException("Invalid parameter name:".concat(String.valueOf(str)));
        }
        this.j = str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public SAAgent(String str, Class<? extends SASocket> cls) {
        if (str == null || str.equalsIgnoreCase("")) {
            throw new IllegalArgumentException("Invalid parameter name:".concat(String.valueOf(str)));
        }
        a(cls);
        this.j = str;
        this.m = cls;
        Log.d("[SA_SDK]SAAgent", "Thread Name:" + this.j + "SASocket Imple class:" + cls.getName());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() throws d {
        String d = d();
        if (d == null) {
            a(2048, (SAPeerAgent) null);
            return;
        }
        Context applicationContext = getApplicationContext();
        if (Build.VERSION.SDK_INT >= 24) {
            applicationContext = applicationContext.createDeviceProtectedStorageContext();
        }
        SharedPreferences.Editor edit = applicationContext.getSharedPreferences("AccessoryPreferences", 0).edit();
        edit.putString(d, getClass().getName());
        edit.putString(getClass().getName(), d);
        edit.commit();
        this.k = d;
        b();
    }

    static /* synthetic */ void a(SAAgent sAAgent) throws d {
        sAAgent.b.a(sAAgent.f);
        sAAgent.b.a();
        sAAgent.a();
    }

    static /* synthetic */ void a(SAAgent sAAgent, Intent intent) {
        if (intent == null) {
            Log.e("[SA_SDK]SAAgent", "Invalid service connection indication.Intent:null.Ignoring reqeuset");
            return;
        }
        long longExtra = intent.getLongExtra("transactionId", 0L);
        SAPeerAgent sAPeerAgent = (SAPeerAgent) intent.getParcelableExtra("peerAgent");
        String stringExtra = intent.getStringExtra("agentId");
        if (sAPeerAgent == null) {
            Log.e("[SA_SDK]SAAgent", "Invalid initiator peer agent:" + sAPeerAgent + ". Ignoring connection request");
        } else if (stringExtra == null) {
            Log.e("[SA_SDK]SAAgent", "Invalid local agent Id:" + stringExtra + ".Ignoring connection request");
        } else {
            sAPeerAgent.a(longExtra);
            Log.i("[SA_SDK]SAAgent", "Connection initiated by peer: " + sAPeerAgent.getPeerId() + " on Accessory: " + com.samsung.android.sdk.accessory.c.a(sAPeerAgent.getAccessory().getAccessoryId(), sAPeerAgent.getAccessory().getAddress()) + " Transaction: " + longExtra);
            sAAgent.i.add(sAPeerAgent);
            sAAgent.onServiceConnectionRequested(sAPeerAgent);
        }
    }

    static /* synthetic */ void a(SAAgent sAAgent, Bundle bundle) {
        bundle.setClassLoader(SAPeerAgent.class.getClassLoader());
        byte[] byteArray = bundle.getByteArray("PEER_AGENT_KEY");
        int i = bundle.getInt("CERT_TYPE");
        SAPeerAgent sAPeerAgent = (SAPeerAgent) bundle.getParcelable("peerAgent");
        long j = bundle.getLong("transactionId");
        if (sAPeerAgent == null) {
            Log.e("[SA_SDK]SAAgent", "Invalid response from framework! No peer agent in auth response.Ignoring response");
            return;
        }
        sAPeerAgent.a(j);
        int i2 = 0;
        if (byteArray == null) {
            i2 = 1545;
            Log.e("[SA_SDK]SAAgent", "Authentication failed error:1545 Peer Id:" + sAPeerAgent.getPeerId());
        } else {
            Log.i("[SA_SDK]SAAgent", "Authentication success status: 0 for peer: " + sAPeerAgent.getPeerId());
        }
        sAAgent.onAuthenticationResponse(sAPeerAgent, new SAAuthenticationToken(i, byteArray), i2);
        e(i2);
    }

    static /* synthetic */ void a(SAAgent sAAgent, SAPeerAgent sAPeerAgent) {
        String d = sAAgent.d();
        if (d != null) {
            sAAgent.e().a(d, sAPeerAgent, sAAgent.b, sAAgent.g);
            return;
        }
        Log.e("[SA_SDK]SAAgent", "Failed to retrieve service description.Ignoring service connection request");
        sAAgent.a(2048, sAPeerAgent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(SAPeerAgent sAPeerAgent) {
        int a2;
        String d = d();
        if (d == null) {
            a2 = 2048;
        } else {
            try {
                this.b.a(d, sAPeerAgent, sAPeerAgent.c());
                return;
            } catch (d e) {
                Log.e("[SA_SDK]SAAgent", "Failed to reject Service connection!", e);
                a2 = e.a();
            }
        }
        a(a2, sAPeerAgent);
    }

    private static void a(Class<? extends SASocket> cls) {
        if (cls == null) {
            throw new IllegalArgumentException("Invalid socketClass param:".concat(String.valueOf(cls)));
        }
        try {
            if (cls.getEnclosingClass() != null) {
                cls.getDeclaredConstructor(cls.getEnclosingClass());
            } else {
                cls.getDeclaredConstructor(new Class[0]);
            }
        } catch (NoSuchMethodException e) {
            Log.e("[SA_SDK]SAAgent", "exception: " + e.getMessage(), e);
            throw new RuntimeException("Invalid implemetation of SASocket. Provider a public default constructor in the implementation class.");
        }
    }

    private void a(boolean z) {
        for (SASocket sASocket : this.h) {
            if (z) {
                sASocket.a();
            } else {
                sASocket.close();
            }
        }
        this.h.clear();
        this.c.clearSdkConfig();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() throws d {
        String str;
        SAMessage sAMessage = this.l;
        if (sAMessage == null || (str = this.k) == null) {
            return;
        }
        sAMessage.a(str);
    }

    static /* synthetic */ void b(int i) {
        if (i == 1) {
            Log.i("[SA_SDK]SAAgent", "onPeerAgentUpdated() -> PEER_AGENT_AVAILABLE");
        } else if (i != 2) {
            Log.w("[SA_SDK]SAAgent", "onPeerAgentUpdated() error_code: ".concat(String.valueOf(i)));
        } else {
            Log.i("[SA_SDK]SAAgent", "onPeerAgentUpdated() -> PEER_AGENT_UNAVAILABLE");
        }
    }

    static /* synthetic */ void b(SAAgent sAAgent, SAPeerAgent sAPeerAgent) {
        String d = sAAgent.d();
        if (d == null) {
            sAAgent.a(2048, sAPeerAgent);
        } else {
            sAAgent.e().b(d, sAPeerAgent, sAAgent.b, sAAgent.g);
        }
    }

    private void b(SAPeerAgent sAPeerAgent) {
        synchronized (this.i) {
            for (SAPeerAgent sAPeerAgent2 : this.i) {
                a(sAPeerAgent2);
            }
            b bVar = this.a;
            if (bVar != null) {
                Message obtainMessage = bVar.obtainMessage(11);
                obtainMessage.arg1 = 1033;
                obtainMessage.obj = sAPeerAgent;
                this.a.sendMessage(obtainMessage);
            } else {
                Log.w("[SA_SDK]SAAgent", "handleInvlaidPeerAction: mBackgroundWorker is null!");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        String str;
        j jVar = new j(getApplicationContext());
        Future<Void> a2 = jVar.a();
        jVar.b();
        try {
            a2.get();
        } catch (InterruptedException unused) {
            str = "Regisration failed! : InterruptedException";
            Log.e("[SA_SDK]SAAgent", str);
        } catch (ExecutionException unused2) {
            str = "Registration failed! : ExecutionException";
            Log.e("[SA_SDK]SAAgent", str);
        }
    }

    static /* synthetic */ void c(SAAgent sAAgent) {
        int a2;
        String d = sAAgent.d();
        if (d == null) {
            a2 = 2048;
        } else {
            try {
                int a3 = sAAgent.b.a(d, sAAgent.d);
                if (a3 == 0) {
                    Log.d("[SA_SDK]SAAgent", "Find peer request successfully enqueued.");
                    return;
                }
                Log.w("[SA_SDK]SAAgent", "Find peer request failed:" + a3 + " for service " + sAAgent.getClass().getName());
                sAAgent.onFindPeerAgentsResponse(null, a3);
                d(a3);
                return;
            } catch (d e) {
                Log.e("[SA_SDK]SAAgent", "Find Peer request failed!");
                a2 = e.a();
            }
        }
        sAAgent.a(a2, (SAPeerAgent) null);
    }

    private String d() {
        String str;
        try {
            String a2 = this.b.a(getClass().getName());
            Log.i("[SA_SDK]SAAgent", "Agent ID retrieved successfully for " + getClass().getName() + " Agent ID:" + a2);
            return a2;
        } catch (d e) {
            if (e.a() == 777 && k.e() >= 298) {
                Log.w("[SA_SDK]SAAgent", "Service record was not found in Accessory Framework.Registering service again!");
                c();
                try {
                    Log.i("[SA_SDK]SAAgent", "Trying to fetch agent ID after re-registration");
                    return this.b.a(getClass().getName());
                } catch (d unused) {
                    str = "Failed to retrieve service record after re-registration";
                    Log.e("[SA_SDK]SAAgent", str, e);
                    return null;
                }
            }
            str = "Failed to retrieve service record";
            Log.e("[SA_SDK]SAAgent", str, e);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void d(int i) {
        if (i == 0) {
            Log.i("[SA_SDK]SAAgent", "onFindPeerAgentsResponse() -> PEER_AGENT_FOUND");
        } else if (i == 3085) {
            Log.i("[SA_SDK]SAAgent", "onFindPeerAgentsResponse() -> FINDPEER_DUPLICATE_REQUEST");
        } else if (i == 1793) {
            Log.i("[SA_SDK]SAAgent", "onFindPeerAgentsResponse() -> FINDPEER_DEVICE_NOT_CONNECTED");
        } else if (i != 1794) {
            Log.w("[SA_SDK]SAAgent", "onFindPeerAgentsResponse() error_code: ".concat(String.valueOf(i)));
        } else {
            Log.i("[SA_SDK]SAAgent", "onFindPeerAgentsResponse() -> FINDPEER_SERVICE_NOT_FOUND");
        }
    }

    static /* synthetic */ void d(SAAgent sAAgent) {
        Log.w("[SA_SDK]SAAgent", "Performing agent cleanup");
        sAAgent.a(false);
        String d = sAAgent.d();
        if (d != null) {
            sAAgent.b.c(d);
        }
        sAAgent.b.b(sAAgent.f);
        SAMessage sAMessage = sAAgent.l;
        if (sAMessage != null) {
            sAMessage.a();
        }
        b bVar = sAAgent.a;
        if (bVar != null) {
            bVar.getLooper().quit();
            bVar.a = null;
            sAAgent.a = null;
        }
    }

    static /* synthetic */ void d(SAAgent sAAgent, SAPeerAgent sAPeerAgent) {
        int a2;
        String d = sAAgent.d();
        if (d == null) {
            a2 = 2048;
        } else {
            try {
                int a3 = sAAgent.b.a(d, sAPeerAgent, sAAgent.e, sAPeerAgent.c());
                if (a3 == 0) {
                    Log.i("[SA_SDK]SAAgent", "Auth. request for peer: " + sAPeerAgent.getPeerId() + " done successfully");
                    return;
                }
                Log.e("[SA_SDK]SAAgent", "Auth. request for peer: " + sAPeerAgent.getPeerId() + " failed as reason: " + a3);
                sAAgent.onAuthenticationResponse(sAPeerAgent, null, a3);
                e(a3);
                return;
            } catch (d e) {
                Log.e("[SA_SDK]SAAgent", "Failed to request peer authentication!", e);
                a2 = e.a();
            }
        }
        sAAgent.a(a2, sAPeerAgent);
    }

    private SASocket e() {
        a(this.m);
        try {
            Log.d("[SA_SDK]SAAgent", "Instantiating SASocket: " + this.m.getName());
            if (this.m.getEnclosingClass() == null || !SAAgent.class.isAssignableFrom(this.m.getEnclosingClass())) {
                Constructor<? extends SASocket> declaredConstructor = this.m.getDeclaredConstructor(new Class[0]);
                declaredConstructor.setAccessible(true);
                return declaredConstructor.newInstance(new Object[0]);
            }
            Class<? extends SASocket> cls = this.m;
            Constructor<? extends SASocket> declaredConstructor2 = cls.getDeclaredConstructor(cls.getEnclosingClass());
            declaredConstructor2.setAccessible(true);
            return declaredConstructor2.newInstance(this);
        } catch (IllegalAccessException e) {
            Log.e("[SA_SDK]SAAgent", "Invalid implemetation of SASocket. Provider a public default constructor." + e.getClass().getSimpleName() + " " + e.getMessage());
            throw new RuntimeException("Invalid implemetation of SASocket. Provider a public default constructor.");
        } catch (IllegalArgumentException e2) {
            Log.e("[SA_SDK]SAAgent", "Invalid implemetation of SASocket. Provider a public default constructor." + e2.getClass().getSimpleName() + " " + e2.getMessage());
            throw new RuntimeException("Invalid implemetation of SASocket. Provider a public default constructor.");
        } catch (InstantiationException e3) {
            Log.e("[SA_SDK]SAAgent", "Invalid implemetation of SASocket. Provider a public default constructor." + e3.getClass().getSimpleName() + " " + e3.getMessage());
            throw new RuntimeException("Invalid implemetation of SASocket. Provider a public default constructor.");
        } catch (NoSuchMethodException e4) {
            Log.e("[SA_SDK]SAAgent", "Invalid implemetation of SASocket. Provider a public default constructor." + e4.getClass().getSimpleName() + " " + e4.getMessage());
            throw new RuntimeException("Invalid implemetation of SASocket. Provider a public default constructor.");
        } catch (InvocationTargetException e5) {
            Log.e("[SA_SDK]SAAgent", "Invalid implemetation of SASocket. Provider a public default constructor." + e5.getClass().getSimpleName() + " " + e5.getMessage());
            throw new RuntimeException("Invalid implemetation of SASocket. Provider a public default constructor.");
        }
    }

    private static void e(int i) {
        if (i == 0) {
            Log.i("[SA_SDK]SAAgent", "onAuthenticationResponse() -> AUTHENTICATION_SUCCESS");
        } else if (i == 1545) {
            Log.i("[SA_SDK]SAAgent", "onAuthenticationResponse() -> AUTHENTICATION_FAILURE_TOKEN_NOT_GENERATED");
        } else if (i != 1546) {
            Log.w("[SA_SDK]SAAgent", "onAuthenticationResponse() error_code: ".concat(String.valueOf(i)));
        } else {
            Log.i("[SA_SDK]SAAgent", "onAuthenticationResponse() -> AUTHENTICATION_FAILURE_PEER_AGENT_NOT_SUPPORTED");
        }
    }

    private synchronized void f() {
        com.samsung.android.sdk.accessory.c a2 = com.samsung.android.sdk.accessory.c.a(getApplicationContext());
        this.n = a2;
        if (a2 != null) {
            n a3 = a2.a(getClass().getName());
            this.o = a3;
            if (a3 == null) {
                Log.e("[SA_SDK]SAAgent", "fetch service profile description failed !!");
            }
        } else {
            Log.e("[SA_SDK]SAAgent", "config  util defualt instance  creation failed !!");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void f(int i) {
        if (i == 0) {
            Log.i("[SA_SDK]SAAgent", "onServiceConnectionResponse() -> CONNECTION_SUCCESS");
        } else if (i == 1033) {
            Log.i("[SA_SDK]SAAgent", "onServiceConnectionResponse() -> CONNECTION_FAILURE_INVALID_PEERAGENT");
        } else if (i == 1037) {
            Log.i("[SA_SDK]SAAgent", "onServiceConnectionResponse() -> CONNECTION_FAILURE_SERVICE_LIMIT_REACHED");
        } else if (i == 1040) {
            Log.i("[SA_SDK]SAAgent", "onServiceConnectionResponse() -> CONNECTION_DUPLICATE_REQUEST");
        } else if (i == 1280) {
            Log.i("[SA_SDK]SAAgent", "onServiceConnectionResponse() -> CONNECTION_FAILURE_NETWORK");
        } else {
            switch (i) {
                case 1028:
                    Log.i("[SA_SDK]SAAgent", "onServiceConnectionResponse() -> CONNECTION_FAILURE_DEVICE_UNREACHABLE");
                    return;
                case 1029:
                    Log.i("[SA_SDK]SAAgent", "onServiceConnectionResponse() -> CONNECTION_ALREADY_EXIST");
                    return;
                case 1030:
                    Log.i("[SA_SDK]SAAgent", "onServiceConnectionResponse() -> CONNECTION_FAILURE_PEERAGENT_NO_RESPONSE");
                    return;
                case 1031:
                    Log.i("[SA_SDK]SAAgent", "onServiceConnectionResponse() -> CONNECTION_FAILURE_PEERAGENT_REJECTED");
                    return;
                default:
                    Log.w("[SA_SDK]SAAgent", "onServiceConnectionResponse() error_code: ".concat(String.valueOf(i)));
                    return;
            }
        }
    }

    private static void g(int i) {
        if (i == 1025) {
            Log.i("[SA_SDK]SAAgent", "onError() -> ERROR_CONNECTION_INVALID_PARAM");
        } else if (i == 2048) {
            Log.i("[SA_SDK]SAAgent", "onError() -> ERROR_FATAL");
        } else if (i == 2049) {
            Log.i("[SA_SDK]SAAgent", "onError() -> ERROR_SDK_NOT_INITIALIZED");
        } else if (i == 2304) {
            Log.i("[SA_SDK]SAAgent", "onError() -> ERROR_PERMISSION_DENIED");
        } else if (i != 2305) {
            Log.w("[SA_SDK]SAAgent", "onError() error_code: ".concat(String.valueOf(i)));
        } else {
            Log.i("[SA_SDK]SAAgent", "onError() -> ERROR_PERMISSION_FAILED");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String a(SAMessage sAMessage) {
        this.l = sAMessage;
        return this.k;
    }

    final void a(int i, SAPeerAgent sAPeerAgent) {
        if (i == 1033) {
            onServiceConnectionResponse(sAPeerAgent, null, 1033);
            f(1033);
        } else if (i == 2048) {
            a(true);
            onError(null, "Samsung Accessory Framework has died!!", i);
            g(i);
        } else if (i == 2049) {
            Log.e("[SA_SDK]SAAgent", "Samsung Accessory SDK cannot be initialized");
            onError(null, "Samsung Accessory SDK cannot be initialized. Device or Build not compatible.", i);
            g(i);
        } else if (i != 2304 && i != 2305) {
            Log.w("[SA_SDK]SAAgent", "Unknown error: ".concat(String.valueOf(i)));
        } else {
            onError(null, "Permission error!", i);
            g(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void acceptServiceConnectionRequest(SAPeerAgent sAPeerAgent) {
        if (sAPeerAgent == null) {
            throw new IllegalArgumentException("Illegal argument peerAgent:".concat(String.valueOf(sAPeerAgent)));
        }
        try {
            this.c.initialize(getApplicationContext());
            if (!this.i.remove(sAPeerAgent)) {
                Log.w("[SA_SDK]SAAgent", "Accepting service connection with invalid peer agent:" + sAPeerAgent.toString());
                b(sAPeerAgent);
                return;
            }
            Log.i("[SA_SDK]SAAgent", "Trying to Accept service connection request from peer:" + sAPeerAgent.getPeerId() + " Transaction:" + sAPeerAgent.c());
            b bVar = this.a;
            if (bVar == null) {
                Log.w("[SA_SDK]SAAgent", "acceptServiceConnection: mBackgroundWorker is null!");
                return;
            }
            Message obtainMessage = bVar.obtainMessage(7);
            obtainMessage.obj = sAPeerAgent;
            this.a.sendMessage(obtainMessage);
        } catch (SsdkUnsupportedException e) {
            Log.e("[SA_SDK]SAAgent", "exception: " + e.getMessage());
            a(2049, sAPeerAgent);
        }
    }

    protected void authenticatePeerAgent(SAPeerAgent sAPeerAgent) {
        if (sAPeerAgent == null) {
            throw new IllegalArgumentException("Illegal argument peerAgent:".concat(String.valueOf(sAPeerAgent)));
        }
        try {
            this.c.initialize(getApplicationContext());
            Log.i("[SA_SDK]SAAgent", "Authentication requested for peer:" + sAPeerAgent.getPeerId());
            b bVar = this.a;
            if (bVar == null) {
                Log.w("[SA_SDK]SAAgent", "authenticatePeerAgent: mBackgroundWorker is null!");
                return;
            }
            Message obtainMessage = bVar.obtainMessage(9);
            obtainMessage.obj = sAPeerAgent;
            this.a.sendMessage(obtainMessage);
        } catch (SsdkUnsupportedException e) {
            Log.e("[SA_SDK]SAAgent", "exception: " + e.getMessage());
            a(2049, sAPeerAgent);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final synchronized void findPeerAgents() {
        Log.d("[SA_SDK]SAAgent", "findPeer request received by:" + getClass().getName());
        try {
            this.c.initialize(getApplicationContext());
            b bVar = this.a;
            if (bVar == null) {
                Log.w("[SA_SDK]SAAgent", "findPeerAgents: mBackgroundWorker is null!");
                return;
            }
            Message obtainMessage = bVar.obtainMessage();
            obtainMessage.what = 2;
            this.a.sendMessage(obtainMessage);
        } catch (SsdkUnsupportedException e) {
            Log.e("[SA_SDK]SAAgent", "exception: " + e.getMessage());
            a(2049, (SAPeerAgent) null);
        }
    }

    public int getServiceChannelId(int i) {
        String str;
        if (this.o == null) {
            str = "Failed because Service Profile is null";
        } else if (i >= 0 && i < getServiceChannelSize()) {
            return this.o.d().get(i).a();
        } else {
            str = "Failed because of wrong index";
        }
        Log.e("[SA_SDK]SAAgent", str);
        return -1;
    }

    public int getServiceChannelSize() {
        n nVar = this.o;
        if (nVar == null) {
            Log.e("[SA_SDK]SAAgent", "Failed because Service Profile is null");
            return -1;
        }
        return nVar.d().size();
    }

    public String getServiceProfileId() {
        n nVar = this.o;
        if (nVar == null) {
            Log.e("[SA_SDK]SAAgent", "Failed because Service Profile is null");
            return null;
        }
        return nVar.a();
    }

    public String getServiceProfileName() {
        n nVar = this.o;
        if (nVar == null) {
            Log.e("[SA_SDK]SAAgent", "Failed because Service Profile is null");
            return null;
        }
        return nVar.b();
    }

    protected void onAuthenticationResponse(SAPeerAgent sAPeerAgent, SAAuthenticationToken sAAuthenticationToken, int i) {
        Log.d("[SA_SDK]SAAgent", "Peer authentication response received:".concat(String.valueOf(i)));
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        try {
            new k(getApplicationContext());
        } catch (d e) {
            e.printStackTrace();
        }
        Log.d("[SA_SDK]SAAgent", "SAAgent - onCreate:" + getClass().getSimpleName());
        this.h = Collections.synchronizedList(new ArrayList());
        this.i = Collections.synchronizedSet(new HashSet());
        HandlerThread handlerThread = new HandlerThread(this.j);
        handlerThread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() { // from class: com.samsung.android.sdk.accessory.SAAgent.1
            @Override // java.lang.Thread.UncaughtExceptionHandler
            public void uncaughtException(Thread thread, final Throwable th) {
                Log.e("[SA_SDK]SAAgent", "Exception in background thread:" + thread.getName(), th);
                new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.samsung.android.sdk.accessory.SAAgent.1.1
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
            Log.e("[SA_SDK]SAAgent", "Unable to start Agent thread.");
            throw new RuntimeException("Unable to start Agent.Worker thread creation failed");
        }
        this.a = new b(this, looper);
        SA sa = new SA();
        this.c = sa;
        try {
            sa.initialize(getApplicationContext());
        } catch (SsdkUnsupportedException e2) {
            Log.e("[SA_SDK]SAAgent", "SDK initialization failed!", e2);
            Message obtainMessage = this.a.obtainMessage(11);
            obtainMessage.arg1 = 2049;
            this.a.sendMessage(obtainMessage);
        }
        this.b = SAAdapter.a(getApplicationContext());
        this.e = new AuthenticationCallback();
        this.d = new PeerAgentCallback();
        this.g = new c(this, (byte) 0);
        this.f = new a(this);
        this.a.sendEmptyMessage(0);
        f();
        g.a(getApplicationContext(), "SAA1", getClass().getName() + "#2.6.4");
    }

    @Override // android.app.Service
    public void onDestroy() {
        Log.d("[SA_SDK]SAAgent", "SAAgent - onDestroy:" + getClass().getSimpleName());
        b bVar = this.a;
        if (bVar != null) {
            bVar.obtainMessage(13).sendToTarget();
        }
        super.onDestroy();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onError(SAPeerAgent sAPeerAgent, String str, int i) {
        Log.e("[SA_SDK]SAAgent", (sAPeerAgent == null ? new StringBuilder("ACCEPT_STATE_ERROR: ").append(i).append(": ").append(str).append(" PeerAgent: null") : new StringBuilder("ACCEPT_STATE_ERROR: ").append(i).append(": ").append(str).append(" PeerAgent: ").append(sAPeerAgent.getPeerId())).toString());
    }

    protected void onFindPeerAgentsResponse(SAPeerAgent[] sAPeerAgentArr, int i) {
        Log.e("[SA_SDK]SAAgent", "Invalid implementation of SAAgent.onFindPeerAgentsResponse(SAPeerAgent[], int) should be overrided!");
    }

    protected void onPeerAgentsUpdated(SAPeerAgent[] sAPeerAgentArr, int i) {
        Log.e("[SA_SDK]SAAgent", "Invalid implementation of SAAgent.onPeerAgentsUpdated(SAPeerAgent[], int) should be overrided!");
    }

    protected void onServiceConnectionRequested(SAPeerAgent sAPeerAgent) {
        if (sAPeerAgent != null) {
            Log.v("[SA_SDK]SAAgent", "Accepting connection request by default from Peer:" + sAPeerAgent.getPeerId() + " Transaction:" + sAPeerAgent.c());
        }
        acceptServiceConnectionRequest(sAPeerAgent);
    }

    protected void onServiceConnectionResponse(SAPeerAgent sAPeerAgent, SASocket sASocket, int i) {
        Log.w("[SA_SDK]SAAgent", "No Implementaion for onServiceConnectionResponse(SAPeerAgent peerAgent, SASocket socket, int result)!");
    }

    @Override // android.app.Service
    public final int onStartCommand(Intent intent, int i, int i2) {
        String action;
        if (intent != null && (action = intent.getAction()) != null) {
            if ("com.samsung.accessory.action.SERVICE_CONNECTION_REQUESTED".equalsIgnoreCase(action)) {
                Log.d("[SA_SDK]SAAgent", "Received incoming connection request");
                Message obtainMessage = this.a.obtainMessage();
                obtainMessage.what = 5;
                obtainMessage.arg1 = i2;
                obtainMessage.obj = intent;
                this.a.sendMessage(obtainMessage);
            } else if (SAMessage.ACTION_ACCESSORY_MESSAGE_RECEIVED.equalsIgnoreCase(action)) {
                Log.d("[SA_SDK]SAAgent", "Received incoming message ind");
                this.a.sendEmptyMessage(17);
            }
        }
        m.a(getApplicationContext()).a();
        return 2;
    }

    @Override // android.app.Service, android.content.ComponentCallbacks2
    public void onTrimMemory(int i) {
        com.samsung.android.sdk.accessory.b.a().a(i);
        super.onTrimMemory(i);
    }

    protected void rejectServiceConnectionRequest(SAPeerAgent sAPeerAgent) {
        if (sAPeerAgent == null) {
            throw new IllegalArgumentException("Illegal argument peerAgent:".concat(String.valueOf(sAPeerAgent)));
        }
        try {
            this.c.initialize(getApplicationContext());
            if (!this.i.remove(sAPeerAgent)) {
                Log.w("[SA_SDK]SAAgent", "Rejecting service connection with invalid peer agent:" + sAPeerAgent.toString());
                b(sAPeerAgent);
                return;
            }
            Log.i("[SA_SDK]SAAgent", "Trying to reject connection request from peer:" + sAPeerAgent.getPeerId() + " Transaction:" + sAPeerAgent.c());
            b bVar = this.a;
            if (bVar == null) {
                Log.w("[SA_SDK]SAAgent", "rejectServiceConnection: mBackgroundWorker is null!");
                return;
            }
            Message obtainMessage = bVar.obtainMessage(8);
            obtainMessage.obj = sAPeerAgent;
            this.a.sendMessage(obtainMessage);
        } catch (SsdkUnsupportedException e) {
            Log.e("[SA_SDK]SAAgent", "exception: " + e.getMessage());
            a(2049, sAPeerAgent);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void requestServiceConnection(SAPeerAgent sAPeerAgent) {
        if (sAPeerAgent == null) {
            throw new IllegalArgumentException("Illegal argument peerAgent:".concat(String.valueOf(sAPeerAgent)));
        }
        try {
            this.c.initialize(getApplicationContext());
            Log.i("[SA_SDK]SAAgent", "Service connection requested for peer:" + sAPeerAgent.getPeerId());
            b bVar = this.a;
            if (bVar == null) {
                Log.w("[SA_SDK]SAAgent", "requestServiceConection: mBackgroundWorker is null!");
                return;
            }
            Message obtainMessage = bVar.obtainMessage(6);
            obtainMessage.obj = sAPeerAgent;
            this.a.sendMessage(obtainMessage);
        } catch (SsdkUnsupportedException e) {
            Log.e("[SA_SDK]SAAgent", "exception: " + e.getMessage());
            a(2049, sAPeerAgent);
        }
    }
}
