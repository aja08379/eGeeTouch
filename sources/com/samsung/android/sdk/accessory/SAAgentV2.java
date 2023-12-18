package com.samsung.android.sdk.accessory;

import android.app.job.JobParameters;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.PersistableBundle;
import android.os.RemoteException;
import android.util.Log;
import com.facebook.internal.NativeProtocol;
import com.samsung.accessory.api.ISAPeerAgentAuthCallback;
import com.samsung.accessory.api.ISAPeerAgentCallback;
import com.samsung.android.sdk.SsdkUnsupportedException;
import com.samsung.android.sdk.accessory.SAAdapter;
import com.samsung.android.sdk.accessory.SASocket;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.Thread;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.locks.ReentrantLock;
/* loaded from: classes2.dex */
public abstract class SAAgentV2 {
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
    public static final int ERROR_AGENT_REQUEST_IN_PROGRESS = 2564;
    public static final int ERROR_CLASS_NOT_FOUND = 2561;
    public static final int ERROR_CONNECTION_INVALID_PARAM = 1025;
    public static final int ERROR_CONSTRUCTOR_EXCEPTION = 2563;
    public static final int ERROR_CONSTRUCTOR_NOT_FOUND = 2562;
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
    private SAAdapter d;
    private SA e;
    private PeerAgentCallback f;
    private AuthenticationCallback g;
    private a h;
    private c i;
    private List<SASocket> j;
    private Set<SAPeerAgent> k;
    private String l;
    private String m;
    private SAMessage n;
    private Context o;
    private volatile boolean p;
    private Class<? extends SASocket> s;
    private static final ReentrantLock b = new ReentrantLock();
    private static Map<String, SAAgentV2> t = Collections.synchronizedMap(new HashMap());
    private static e c = new e(Looper.getMainLooper());
    private Object q = new Object();
    private final Object r = new Object();
    private com.samsung.android.sdk.accessory.c u = null;
    private n v = null;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class AuthenticationCallback extends ISAPeerAgentAuthCallback.Stub {
        private AuthenticationCallback() {
        }

        @Override // com.samsung.accessory.api.ISAPeerAgentAuthCallback
        public void onPeerAgentAuthenticated(Bundle bundle) throws RemoteException {
            Log.v("[SA_SDK]SAAgentV2", "Received Authentication response");
            synchronized (SAAgentV2.this.r) {
                if (SAAgentV2.this.a != null) {
                    Message obtainMessage = SAAgentV2.this.a.obtainMessage(11);
                    obtainMessage.setData(bundle);
                    SAAgentV2.this.a.sendMessage(obtainMessage);
                } else {
                    Log.w("[SA_SDK]SAAgentV2", "onPeerAgentAuthenticated: mBackgroundWorker is null!");
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class PeerAgentCallback extends ISAPeerAgentCallback.Stub {
        private PeerAgentCallback() {
        }

        @Override // com.samsung.accessory.api.ISAPeerAgentCallback
        public void onPeerAgentUpdated(Bundle bundle) throws RemoteException {
            Log.v("[SA_SDK]SAAgentV2", "Received peer agent update");
            bundle.setClassLoader(SAPeerAgent.class.getClassLoader());
            if (!bundle.containsKey("peerAgents")) {
                Log.e("[SA_SDK]SAAgentV2", "No peer agents in PeerAgent update callback!");
                return;
            }
            ArrayList<SAPeerAgent> parcelableArrayList = bundle.getParcelableArrayList("peerAgents");
            int i = bundle.getInt("peerAgentStatus");
            if (parcelableArrayList == null) {
                Log.e("[SA_SDK]SAAgentV2", "Peer Update - invalid peer agent list from Accessory Framework");
            } else if (i == 105 || i == 106) {
                Log.i("[SA_SDK]SAAgentV2", parcelableArrayList.size() + " Peer agent(s) updated for:" + getClass().getName());
                for (SAPeerAgent sAPeerAgent : parcelableArrayList) {
                    Log.i("[SA_SDK]SAAgentV2", "Peer ID:" + sAPeerAgent.getPeerId() + "Container Id:" + sAPeerAgent.getContainerId() + " Accessory" + com.samsung.android.sdk.accessory.c.a(sAPeerAgent.getAccessory().getAccessoryId(), sAPeerAgent.getAccessory().getAddress()));
                }
                synchronized (SAAgentV2.this.r) {
                    if (SAAgentV2.this.a != null) {
                        Message obtainMessage = SAAgentV2.this.a.obtainMessage();
                        obtainMessage.what = 4;
                        if (i == 105) {
                            obtainMessage.arg1 = 1;
                        } else {
                            obtainMessage.arg1 = 2;
                        }
                        obtainMessage.obj = parcelableArrayList.toArray(new SAPeerAgent[parcelableArrayList.size()]);
                        SAAgentV2.this.a.sendMessage(obtainMessage);
                    } else {
                        Log.w("[SA_SDK]SAAgentV2", "onPeerAgentUpdated: mBackgroundWorker is null!");
                    }
                }
            } else {
                Log.e("[SA_SDK]SAAgentV2", "Peer Update - invalid peer status from Accessory Framework:".concat(String.valueOf(i)));
            }
        }

        @Override // com.samsung.accessory.api.ISAPeerAgentCallback
        public void onPeerAgentsFound(Bundle bundle) throws RemoteException {
            Log.v("[SA_SDK]SAAgentV2", "FindPeer response received.");
            bundle.setClassLoader(SAPeerAgent.class.getClassLoader());
            if (bundle.containsKey("errorcode")) {
                int i = bundle.getInt("errorcode");
                Log.e("[SA_SDK]SAAgentV2", "Peer Not Found(" + i + ") for: " + getClass().getName());
                synchronized (SAAgentV2.this.r) {
                    if (SAAgentV2.this.a != null) {
                        Message obtainMessage = SAAgentV2.this.a.obtainMessage();
                        obtainMessage.what = 3;
                        obtainMessage.arg1 = i;
                        SAAgentV2.this.a.sendMessage(obtainMessage);
                    } else {
                        Log.w("[SA_SDK]SAAgentV2", "onPeersAgentsFound: mBackgroundWorker is null!");
                    }
                }
                return;
            }
            ArrayList<SAPeerAgent> parcelableArrayList = bundle.getParcelableArrayList("peerAgents");
            if (parcelableArrayList == null) {
                Log.e("[SA_SDK]SAAgentV2", "Find Peer - invalid response from Accessory Framework");
                return;
            }
            Log.i("[SA_SDK]SAAgentV2", parcelableArrayList.size() + " Peer agent(s) found for:" + getClass().getName());
            for (SAPeerAgent sAPeerAgent : parcelableArrayList) {
                Log.i("[SA_SDK]SAAgentV2", "Peer ID:" + sAPeerAgent.getPeerId() + "Container Id:" + sAPeerAgent.getContainerId() + " Accessory" + com.samsung.android.sdk.accessory.c.a(sAPeerAgent.getAccessory().getAccessoryId(), sAPeerAgent.getAccessory().getAddress()) + " Transport:" + sAPeerAgent.getAccessory().getTransportType());
            }
            synchronized (SAAgentV2.this.r) {
                if (SAAgentV2.this.a != null) {
                    Message obtainMessage2 = SAAgentV2.this.a.obtainMessage();
                    obtainMessage2.what = 3;
                    obtainMessage2.arg1 = 0;
                    obtainMessage2.obj = parcelableArrayList.toArray(new SAPeerAgent[parcelableArrayList.size()]);
                    SAAgentV2.this.a.sendMessage(obtainMessage2);
                } else {
                    Log.w("[SA_SDK]SAAgentV2", "onPeerAgentsFound: mBackgroundWorker is null!");
                }
            }
        }
    }

    /* loaded from: classes2.dex */
    public interface RequestAgentCallback {
        void onAgentAvailable(SAAgentV2 sAAgentV2);

        void onError(int i, String str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static class a implements SAAdapter.b {
        private SAAgentV2 a;

        public a(SAAgentV2 sAAgentV2) {
            this.a = sAAgentV2;
        }

        @Override // com.samsung.android.sdk.accessory.SAAdapter.b
        public final void a() {
            if (this.a.a == null) {
                Log.w("[SA_SDK]SAAgentV2", "onFrameworkDisconnected: mBackgroundWorker is null!");
                return;
            }
            synchronized (this.a.r) {
                Message obtainMessage = this.a.a.obtainMessage(12);
                obtainMessage.arg1 = 2048;
                this.a.a.sendMessage(obtainMessage);
            }
        }

        @Override // com.samsung.android.sdk.accessory.SAAdapter.b
        public final void b() {
            if (this.a.a != null) {
                synchronized (this.a.r) {
                    this.a.a.sendEmptyMessage(19);
                }
            }
        }

        @Override // com.samsung.android.sdk.accessory.SAAdapter.b
        public final void c() throws com.samsung.android.sdk.accessory.d {
            if (this.a.a != null) {
                synchronized (this.a.r) {
                    this.a.a.sendEmptyMessage(15);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static class b extends Handler {
        private SAAgentV2 a;

        public b(SAAgentV2 sAAgentV2, Looper looper) {
            super(looper);
            this.a = sAAgentV2;
        }

        public final void a() {
            getLooper().quit();
            this.a = null;
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            SAPeerAgent sAPeerAgent = null;
            switch (message.what) {
                case 0:
                    try {
                        SAAgentV2.a(this.a);
                        return;
                    } catch (com.samsung.android.sdk.accessory.d e) {
                        Log.e("[SA_SDK]SAAgentV2", "Binding to Accessory Framework failed", e);
                        this.a.a(e.a(), (SAPeerAgent) null);
                        return;
                    }
                case 1:
                    this.a.i();
                    return;
                case 2:
                    SAAgentV2.c(this.a);
                    return;
                case 3:
                    if (message.arg1 != 0) {
                        SAAgentV2 sAAgentV2 = this.a;
                        if (sAAgentV2 instanceof h) {
                            new ArrayList();
                            return;
                        }
                        sAAgentV2.onFindPeerAgentsResponse(null, message.arg1);
                        SAAgentV2.d(message.arg1);
                        return;
                    }
                    boolean z = this.a instanceof h;
                    SAPeerAgent[] sAPeerAgentArr = (SAPeerAgent[]) message.obj;
                    if (z) {
                        Arrays.asList(sAPeerAgentArr);
                        return;
                    }
                    this.a.onFindPeerAgentsResponse(sAPeerAgentArr, 0);
                    SAAgentV2.d(0);
                    return;
                case 4:
                    this.a.onPeerAgentsUpdated((SAPeerAgent[]) message.obj, message.arg1);
                    SAAgentV2.b(message.arg1);
                    return;
                case 5:
                    SAAgentV2.a(this.a, (Intent) message.obj);
                    return;
                case 6:
                    if (Build.VERSION.SDK_INT >= 21) {
                        JobParameters jobParameters = (JobParameters) message.getData().get(NativeProtocol.WEB_DIALOG_PARAMS);
                        SAAgentV2.a(this.a, jobParameters.getExtras());
                        ((com.samsung.android.sdk.accessory.a) message.obj).onJobFinished(jobParameters);
                        return;
                    }
                    return;
                case 7:
                    SAAgentV2.a(this.a, (SAPeerAgent) message.obj);
                    return;
                case 8:
                    SAAgentV2.b(this.a, (SAPeerAgent) message.obj);
                    return;
                case 9:
                    this.a.a((SAPeerAgent) message.obj);
                    return;
                case 10:
                    SAAgentV2.d(this.a, (SAPeerAgent) message.obj);
                    return;
                case 11:
                    SAAgentV2.a(this.a, message.getData());
                    return;
                case 12:
                    if (message.obj != null && (message.obj instanceof SAPeerAgent)) {
                        sAPeerAgent = (SAPeerAgent) message.obj;
                    }
                    this.a.a(message.arg1, sAPeerAgent);
                    return;
                case 13:
                    this.a.onServiceConnectionResponse((message.obj == null || !(message.obj instanceof SAPeerAgent)) ? null : (SAPeerAgent) message.obj, null, message.arg1);
                    SAAgentV2.f(message.arg1);
                    return;
                case 14:
                    SAAgentV2.d(this.a);
                    return;
                case 15:
                    try {
                        this.a.g();
                        return;
                    } catch (com.samsung.android.sdk.accessory.d e2) {
                        Log.e("[SA_SDK]SAAgentV2", "Retrieving agent id failed", e2);
                        this.a.a(e2.a(), (SAPeerAgent) null);
                        return;
                    }
                case 16:
                case 17:
                default:
                    Log.w("[SA_SDK]SAAgentV2", "Invalid msg received: " + message.what);
                    return;
                case 18:
                    this.a.onLowMemory();
                    return;
                case 19:
                    try {
                        this.a.h();
                        return;
                    } catch (com.samsung.android.sdk.accessory.d e3) {
                        Log.e("[SA_SDK]SAAgentV2", "handleMessageReceived() - Failed to register agent with message! " + e3.getMessage());
                        this.a.a(e3.a(), (SAPeerAgent) null);
                        return;
                    }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public class c implements SASocket.a {
        private c() {
        }

        /* synthetic */ c(SAAgentV2 sAAgentV2, byte b) {
            this();
        }

        @Override // com.samsung.android.sdk.accessory.SASocket.a
        public final void a(SAPeerAgent sAPeerAgent, int i) {
            if (i == 2048) {
                Log.w("[SA_SDK]SAAgentV2", "Framework disconnected during connection process!");
                SAAgentV2.this.a(i, sAPeerAgent);
                return;
            }
            synchronized (SAAgentV2.this.r) {
                if (SAAgentV2.this.a != null) {
                    if (i == 1034) {
                        i = 1033;
                        SAAgentV2.this.a.sendMessage(SAAgentV2.this.a.obtainMessage(1));
                    }
                    Log.e("[SA_SDK]SAAgentV2", "Connection attempt failed wih peer:" + sAPeerAgent.getPeerId() + " reason:" + i);
                    Message obtainMessage = SAAgentV2.this.a.obtainMessage(13);
                    obtainMessage.arg1 = i;
                    obtainMessage.obj = sAPeerAgent;
                    SAAgentV2.this.a.sendMessage(obtainMessage);
                } else {
                    Log.w("[SA_SDK]SAAgentV2", "onConnectionFailure: mBackgroundWorker is null!");
                }
            }
        }

        @Override // com.samsung.android.sdk.accessory.SASocket.a
        public final void a(SAPeerAgent sAPeerAgent, SASocket sASocket) {
            synchronized (SAAgentV2.this.j) {
                SAAgentV2.this.j.add(sASocket);
            }
            Log.i("[SA_SDK]SAAgentV2", "Connection success with peer:" + sAPeerAgent.getPeerId());
            SAAgentV2.this.onServiceConnectionResponse(sAPeerAgent, sASocket, 0);
            SAAgentV2.f(0);
        }
    }

    /* loaded from: classes2.dex */
    static class d {
        private Context a;
        private String b;
        private RequestAgentCallback c;

        public d(Context context, String str, RequestAgentCallback requestAgentCallback) {
            this.a = context.getApplicationContext();
            this.b = str;
            this.c = requestAgentCallback;
        }
    }

    /* loaded from: classes2.dex */
    static class e extends Handler {
        public e(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            int i = message.what;
            if (i != 1) {
                if (i != 2) {
                    return;
                }
                SAAgentV2 sAAgentV2 = (SAAgentV2) message.obj;
                if (SAAgentV2.t.get(sAAgentV2.getClass().getName()) == sAAgentV2) {
                    SAAgentV2.h(sAAgentV2);
                    return;
                } else {
                    Log.w("[SA_SDK]SAAgentV2", "Stale agent entry. Agent already destroyed. Ignoring...");
                    return;
                }
            }
            d dVar = (d) message.obj;
            Context context = dVar.a;
            String str = dVar.b;
            RequestAgentCallback requestAgentCallback = dVar.c;
            int i2 = message.arg1;
            SAAgentV2 sAAgentV22 = (SAAgentV2) SAAgentV2.t.get(str);
            if (sAAgentV22 != null) {
                if (!sAAgentV22.e()) {
                    if (requestAgentCallback != null) {
                        requestAgentCallback.onAgentAvailable(sAAgentV22);
                        return;
                    }
                    return;
                } else if (i2 == 4) {
                    requestAgentCallback.onError(SAAgentV2.ERROR_AGENT_REQUEST_IN_PROGRESS, "Class could not be initialized: " + str + ". Error occurred while releasing agent.");
                    return;
                } else {
                    Message obtainMessage = obtainMessage(1);
                    obtainMessage.arg1 = i2 + 1;
                    obtainMessage.obj = dVar;
                    sendMessageDelayed(obtainMessage, 500L);
                    return;
                }
            }
            SAAgentV2.b.lock();
            try {
                try {
                    try {
                        try {
                            try {
                                Constructor<?> declaredConstructor = Class.forName(str).getDeclaredConstructor(Context.class);
                                declaredConstructor.setAccessible(true);
                                declaredConstructor.newInstance(context);
                                SAAgentV2.b.unlock();
                                SAAgentV2 sAAgentV23 = (SAAgentV2) SAAgentV2.t.get(str);
                                if (sAAgentV23 == null) {
                                    requestAgentCallback.onError(SAAgentV2.ERROR_CONSTRUCTOR_EXCEPTION, "Class could not be initialized: " + str + ". Call super inside constructor.");
                                } else if (requestAgentCallback != null) {
                                    requestAgentCallback.onAgentAvailable(sAAgentV23);
                                }
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                                requestAgentCallback.onError(SAAgentV2.ERROR_CLASS_NOT_FOUND, "Class not found: ".concat(String.valueOf(str)));
                                SAAgentV2.b.unlock();
                            }
                        } catch (NoSuchMethodException e2) {
                            e2.printStackTrace();
                            requestAgentCallback.onError(SAAgentV2.ERROR_CONSTRUCTOR_NOT_FOUND, "Constructor with Context argument not found: ".concat(String.valueOf(str)));
                            SAAgentV2.b.unlock();
                        }
                    } catch (IllegalAccessException e3) {
                        e3.printStackTrace();
                        requestAgentCallback.onError(SAAgentV2.ERROR_CONSTRUCTOR_EXCEPTION, "Class constructor not accessible: ".concat(String.valueOf(str)));
                        SAAgentV2.b.unlock();
                    } catch (IllegalArgumentException e4) {
                        e4.printStackTrace();
                        requestAgentCallback.onError(SAAgentV2.ERROR_CONSTRUCTOR_EXCEPTION, "Class instantiation error: " + str + ". Invalid context passed.");
                        SAAgentV2.b.unlock();
                    }
                } catch (InstantiationException e5) {
                    e5.printStackTrace();
                    requestAgentCallback.onError(SAAgentV2.ERROR_CONSTRUCTOR_EXCEPTION, "Class instantiation error: ".concat(String.valueOf(str)));
                    SAAgentV2.b.unlock();
                } catch (InvocationTargetException e6) {
                    e6.printStackTrace();
                    requestAgentCallback.onError(SAAgentV2.ERROR_CONSTRUCTOR_EXCEPTION, "Exception occurred while calling constructor of class: ".concat(String.valueOf(str)));
                    SAAgentV2.b.unlock();
                }
            } catch (Throwable th) {
                SAAgentV2.b.unlock();
                throw th;
            }
        }
    }

    protected SAAgentV2(String str, Context context) {
        if (str == null || str.equalsIgnoreCase("")) {
            throw new IllegalArgumentException("Invalid parameter name:".concat(String.valueOf(str)));
        }
        if (!b.isHeldByCurrentThread()) {
            throw new IllegalArgumentException("Constructor should not be called for initializing " + str + ". Call requestAgent API instead");
        }
        this.l = str;
        this.o = context;
        f();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public SAAgentV2(String str, Context context, Class<? extends SASocket> cls) {
        if (str == null || str.equalsIgnoreCase("")) {
            throw new IllegalArgumentException("Invalid parameter name:".concat(String.valueOf(str)));
        }
        if (!b.isHeldByCurrentThread()) {
            throw new IllegalArgumentException("Constructor should not be called for initializing " + str + ". Call requestAgent API instead");
        }
        this.l = str;
        this.o = context;
        a(cls);
        this.s = cls;
        Log.d("[SA_SDK]SAAgentV2", "Thread Name:" + this.l + "SASocket Imple class:" + cls.getName());
        f();
    }

    private void a(long j, String str, SAPeerAgent sAPeerAgent) {
        if (sAPeerAgent == null) {
            Log.e("[SA_SDK]SAAgentV2", "Invalid initiator peer agent:" + sAPeerAgent + ". Ignoring connection request");
        } else if (str == null) {
            Log.e("[SA_SDK]SAAgentV2", "Invalid local agent Id:" + str + ".Ignoring connection request");
        } else {
            sAPeerAgent.a(j);
            Log.i("[SA_SDK]SAAgentV2", "Connection initiated by peer: " + sAPeerAgent.getPeerId() + " on Accessory: " + com.samsung.android.sdk.accessory.c.a(sAPeerAgent.getAccessory().getAccessoryId(), sAPeerAgent.getAccessory().getAddress()) + " Transaction: " + j);
            this.k.add(sAPeerAgent);
            onServiceConnectionRequested(sAPeerAgent);
        }
    }

    private void a(Context context) {
        g.a(context, "SAA2", getClass().getName() + "#2.6.4");
    }

    static /* synthetic */ void a(SAAgentV2 sAAgentV2) throws com.samsung.android.sdk.accessory.d {
        sAAgentV2.d.a(sAAgentV2.h);
        sAAgentV2.d.a();
        sAAgentV2.g();
    }

    static /* synthetic */ void a(SAAgentV2 sAAgentV2, Intent intent) {
        if (intent == null) {
            Log.e("[SA_SDK]SAAgentV2", "Invalid service connection indication.Intent:null.Ignoring reqeuset");
            return;
        }
        sAAgentV2.a(intent.getLongExtra("transactionId", 0L), intent.getStringExtra("agentId"), (SAPeerAgent) intent.getParcelableExtra("peerAgent"));
    }

    static /* synthetic */ void a(SAAgentV2 sAAgentV2, Bundle bundle) {
        bundle.setClassLoader(SAPeerAgent.class.getClassLoader());
        byte[] byteArray = bundle.getByteArray("PEER_AGENT_KEY");
        int i = bundle.getInt("CERT_TYPE");
        SAPeerAgent sAPeerAgent = (SAPeerAgent) bundle.getParcelable("peerAgent");
        long j = bundle.getLong("transactionId");
        if (sAPeerAgent == null) {
            Log.e("[SA_SDK]SAAgentV2", "Invalid response from framework! No peer agent in auth response.Ignoring response");
            return;
        }
        sAPeerAgent.a(j);
        int i2 = 0;
        if (byteArray == null) {
            i2 = 1545;
            Log.e("[SA_SDK]SAAgentV2", "Authentication failed error:1545 Peer Id:" + sAPeerAgent.getPeerId());
        } else {
            Log.i("[SA_SDK]SAAgentV2", "Authentication success status: 0 for peer: " + sAPeerAgent.getPeerId());
        }
        sAAgentV2.onAuthenticationResponse(sAPeerAgent, new SAAuthenticationToken(i, byteArray), i2);
        e(i2);
    }

    static /* synthetic */ void a(SAAgentV2 sAAgentV2, PersistableBundle persistableBundle) {
        if (persistableBundle == null) {
            Log.e("[SA_SDK]SAAgentV2", "Invalid service connection indication.Intent:null.Ignoring reqeuset");
            return;
        }
        long j = persistableBundle.getLong("transactionId", 0L);
        String[] strArr = new String[0];
        String[] stringArray = persistableBundle.getStringArray("peerAgent");
        if (stringArray == null) {
            Log.e("[SA_SDK]SAAgentV2", "Invalid initiator peer agent. Ignoring connection request");
            return;
        }
        sAAgentV2.a(j, persistableBundle.getString("agentId"), new SAPeerAgent(Arrays.asList(stringArray)));
    }

    static /* synthetic */ void a(SAAgentV2 sAAgentV2, SAPeerAgent sAPeerAgent) {
        String j = sAAgentV2.j();
        if (j != null) {
            sAAgentV2.k().a(j, sAPeerAgent, sAAgentV2.d, sAAgentV2.i);
            return;
        }
        Log.e("[SA_SDK]SAAgentV2", "Failed to retrieve service description.Ignoring service connection request");
        sAAgentV2.a(2048, sAPeerAgent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(SAPeerAgent sAPeerAgent) {
        int a2;
        String j = j();
        if (j == null) {
            a2 = 2048;
        } else {
            try {
                this.d.a(j, sAPeerAgent, sAPeerAgent.c());
                return;
            } catch (com.samsung.android.sdk.accessory.d e2) {
                Log.e("[SA_SDK]SAAgentV2", "Failed to reject Service connection!", e2);
                a2 = e2.a();
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
        } catch (NoSuchMethodException e2) {
            Log.e("[SA_SDK]SAAgentV2", "exception: " + e2.getMessage(), e2);
            throw new RuntimeException("Invalid implemetation of SASocket. Provider a public default constructor in the implementation class.");
        }
    }

    private static void a(String str, SAAgentV2 sAAgentV2) {
        if (t.containsKey(str)) {
            return;
        }
        t.put(str, sAAgentV2);
    }

    private void a(boolean z) {
        for (SASocket sASocket : this.j) {
            if (z) {
                sASocket.a();
            } else {
                sASocket.close();
            }
        }
        this.j.clear();
        this.e.clearSdkConfig();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void b() {
        synchronized (t) {
            for (SAAgentV2 sAAgentV2 : t.values()) {
                synchronized (sAAgentV2.r) {
                    b bVar = sAAgentV2.a;
                    if (bVar != null) {
                        bVar.sendEmptyMessage(18);
                    } else {
                        Log.w("[SA_SDK]SAAgentV2", "handleAgentLowMemory : null Agent handler");
                    }
                }
            }
        }
    }

    static /* synthetic */ void b(int i) {
        if (i == 1) {
            Log.i("[SA_SDK]SAAgentV2", "onPeerAgentUpdated() -> PEER_AGENT_AVAILABLE");
        } else if (i != 2) {
            Log.w("[SA_SDK]SAAgentV2", "onPeerAgentUpdated() error_code: ".concat(String.valueOf(i)));
        } else {
            Log.i("[SA_SDK]SAAgentV2", "onPeerAgentUpdated() -> PEER_AGENT_UNAVAILABLE");
        }
    }

    static /* synthetic */ void b(SAAgentV2 sAAgentV2, SAPeerAgent sAPeerAgent) {
        String j = sAAgentV2.j();
        if (j == null) {
            sAAgentV2.a(2048, sAPeerAgent);
        } else {
            sAAgentV2.k().b(j, sAPeerAgent, sAAgentV2.d, sAAgentV2.i);
        }
    }

    private void b(SAPeerAgent sAPeerAgent) {
        synchronized (this.k) {
            for (SAPeerAgent sAPeerAgent2 : this.k) {
                a(sAPeerAgent2);
            }
            synchronized (this.r) {
                b bVar = this.a;
                if (bVar != null) {
                    Message obtainMessage = bVar.obtainMessage(12);
                    obtainMessage.arg1 = 1033;
                    obtainMessage.obj = sAPeerAgent;
                    this.a.sendMessage(obtainMessage);
                } else {
                    Log.w("[SA_SDK]SAAgentV2", "handleInvlaidPeerAction: mBackgroundWorker is null!");
                }
            }
        }
    }

    static /* synthetic */ void c(SAAgentV2 sAAgentV2) {
        int a2;
        String j = sAAgentV2.j();
        if (j == null) {
            a2 = 2048;
        } else {
            try {
                int a3 = sAAgentV2.d.a(j, sAAgentV2.f);
                if (a3 == 0) {
                    Log.d("[SA_SDK]SAAgentV2", "Find peer request successfully enqueued.");
                    return;
                }
                Log.w("[SA_SDK]SAAgentV2", "Find peer request failed:" + a3 + " for service " + sAAgentV2.getClass().getName());
                sAAgentV2.onFindPeerAgentsResponse(null, a3);
                d(a3);
                return;
            } catch (com.samsung.android.sdk.accessory.d e2) {
                Log.e("[SA_SDK]SAAgentV2", "Find Peer request failed!");
                a2 = e2.a();
            }
        }
        sAAgentV2.a(a2, (SAPeerAgent) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void d(int i) {
        if (i == 0) {
            Log.i("[SA_SDK]SAAgentV2", "onFindPeerAgentsResponse() -> PEER_AGENT_FOUND");
        } else if (i == 3085) {
            Log.i("[SA_SDK]SAAgentV2", "onFindPeerAgentsResponse() -> FINDPEER_DUPLICATE_REQUEST");
        } else if (i == 1793) {
            Log.i("[SA_SDK]SAAgentV2", "onFindPeerAgentsResponse() -> FINDPEER_DEVICE_NOT_CONNECTED");
        } else if (i != 1794) {
            Log.w("[SA_SDK]SAAgentV2", "onFindPeerAgentsResponse() error_code: ".concat(String.valueOf(i)));
        } else {
            Log.i("[SA_SDK]SAAgentV2", "onFindPeerAgentsResponse() -> FINDPEER_SERVICE_NOT_FOUND");
        }
    }

    static /* synthetic */ void d(SAAgentV2 sAAgentV2) {
        Log.w("[SA_SDK]SAAgentV2", "Performing agent cleanup");
        sAAgentV2.a(false);
        String j = sAAgentV2.j();
        if (j != null) {
            sAAgentV2.d.c(j);
        }
        sAAgentV2.d.b(sAAgentV2.h);
        SAMessage sAMessage = sAAgentV2.n;
        if (sAMessage != null) {
            sAMessage.a();
        }
        synchronized (sAAgentV2.r) {
            b bVar = sAAgentV2.a;
            if (bVar != null) {
                bVar.a();
                sAAgentV2.a = null;
            }
        }
        String name = sAAgentV2.getClass().getName();
        if (t.containsKey(name)) {
            t.remove(name);
        }
        synchronized (sAAgentV2.q) {
            sAAgentV2.p = false;
        }
    }

    static /* synthetic */ void d(SAAgentV2 sAAgentV2, SAPeerAgent sAPeerAgent) {
        int a2;
        String j = sAAgentV2.j();
        if (j == null) {
            a2 = 2048;
        } else {
            try {
                int a3 = sAAgentV2.d.a(j, sAPeerAgent, sAAgentV2.g, sAPeerAgent.c());
                if (a3 == 0) {
                    Log.i("[SA_SDK]SAAgentV2", "Auth. request for peer: " + sAPeerAgent.getPeerId() + " done successfully");
                    return;
                }
                Log.e("[SA_SDK]SAAgentV2", "Auth. request for peer: " + sAPeerAgent.getPeerId() + " failed as reason: " + a3);
                sAAgentV2.onAuthenticationResponse(sAPeerAgent, null, a3);
                e(a3);
                return;
            } catch (com.samsung.android.sdk.accessory.d e2) {
                Log.e("[SA_SDK]SAAgentV2", "Failed to request peer authentication!", e2);
                a2 = e2.a();
            }
        }
        sAAgentV2.a(a2, sAPeerAgent);
    }

    private static void e(int i) {
        if (i == 0) {
            Log.i("[SA_SDK]SAAgentV2", "onAuthenticationResponse() -> AUTHENTICATION_SUCCESS");
        } else if (i == 1545) {
            Log.i("[SA_SDK]SAAgentV2", "onAuthenticationResponse() -> AUTHENTICATION_FAILURE_TOKEN_NOT_GENERATED");
        } else if (i != 1546) {
            Log.w("[SA_SDK]SAAgentV2", "onAuthenticationResponse() error_code: ".concat(String.valueOf(i)));
        } else {
            Log.i("[SA_SDK]SAAgentV2", "onAuthenticationResponse() -> AUTHENTICATION_FAILURE_PEER_AGENT_NOT_SUPPORTED");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean e() {
        boolean z;
        synchronized (this.q) {
            z = this.p;
        }
        return z;
    }

    private void f() {
        try {
            new k(getApplicationContext());
        } catch (com.samsung.android.sdk.accessory.d e2) {
            e2.printStackTrace();
        }
        Log.d("[SA_SDK]SAAgentV2", "SAAgentV2 - initialize:" + getClass().getSimpleName());
        this.j = Collections.synchronizedList(new ArrayList());
        this.k = Collections.synchronizedSet(new HashSet());
        HandlerThread handlerThread = new HandlerThread(this.l);
        handlerThread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() { // from class: com.samsung.android.sdk.accessory.SAAgentV2.1
            @Override // java.lang.Thread.UncaughtExceptionHandler
            public void uncaughtException(Thread thread, final Throwable th) {
                Log.e("[SA_SDK]SAAgentV2", "Exception in background thread:" + thread.getName(), th);
                new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.samsung.android.sdk.accessory.SAAgentV2.1.1
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
            Log.e("[SA_SDK]SAAgentV2", "Unable to start Agent thread.");
            throw new RuntimeException("Unable to start Agent.Worker thread creation failed");
        }
        this.a = new b(this, looper);
        SA sa = new SA();
        this.e = sa;
        try {
            sa.initialize(getApplicationContext());
        } catch (SsdkUnsupportedException e3) {
            Log.e("[SA_SDK]SAAgentV2", "SDK initialization failed!", e3);
            Message obtainMessage = this.a.obtainMessage(12);
            obtainMessage.arg1 = 2049;
            this.a.sendMessage(obtainMessage);
        }
        a(getClass().getName(), this);
        this.d = SAAdapter.a(getApplicationContext());
        this.g = new AuthenticationCallback();
        this.f = new PeerAgentCallback();
        this.i = new c(this, (byte) 0);
        this.h = new a(this);
        this.a.sendEmptyMessage(0);
        l();
        a(this.o);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void f(int i) {
        if (i == 0) {
            Log.i("[SA_SDK]SAAgentV2", "onServiceConnectionResponse() -> CONNECTION_SUCCESS");
        } else if (i == 1033) {
            Log.i("[SA_SDK]SAAgentV2", "onServiceConnectionResponse() -> CONNECTION_FAILURE_INVALID_PEERAGENT");
        } else if (i == 1037) {
            Log.i("[SA_SDK]SAAgentV2", "onServiceConnectionResponse() -> CONNECTION_FAILURE_SERVICE_LIMIT_REACHED");
        } else if (i == 1040) {
            Log.i("[SA_SDK]SAAgentV2", "onServiceConnectionResponse() -> CONNECTION_DUPLICATE_REQUEST");
        } else if (i == 1280) {
            Log.i("[SA_SDK]SAAgentV2", "onServiceConnectionResponse() -> CONNECTION_FAILURE_NETWORK");
        } else {
            switch (i) {
                case 1028:
                    Log.i("[SA_SDK]SAAgentV2", "onServiceConnectionResponse() -> CONNECTION_FAILURE_DEVICE_UNREACHABLE");
                    return;
                case 1029:
                    Log.i("[SA_SDK]SAAgentV2", "onServiceConnectionResponse() -> CONNECTION_ALREADY_EXIST");
                    return;
                case 1030:
                    Log.i("[SA_SDK]SAAgentV2", "onServiceConnectionResponse() -> CONNECTION_FAILURE_PEERAGENT_NO_RESPONSE");
                    return;
                case 1031:
                    Log.i("[SA_SDK]SAAgentV2", "onServiceConnectionResponse() -> CONNECTION_FAILURE_PEERAGENT_REJECTED");
                    return;
                default:
                    Log.w("[SA_SDK]SAAgentV2", "onServiceConnectionResponse() error_code: ".concat(String.valueOf(i)));
                    return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() throws com.samsung.android.sdk.accessory.d {
        String j = j();
        if (j == null) {
            a(2048, (SAPeerAgent) null);
            return;
        }
        Context applicationContext = getApplicationContext();
        if (Build.VERSION.SDK_INT >= 24) {
            applicationContext = applicationContext.createDeviceProtectedStorageContext();
        }
        SharedPreferences.Editor edit = applicationContext.getSharedPreferences("AccessoryPreferences", 0).edit();
        edit.putString(j, getClass().getName());
        edit.putString(getClass().getName(), j);
        edit.commit();
        this.m = j;
        h();
    }

    private static void g(int i) {
        if (i == 1025) {
            Log.i("[SA_SDK]SAAgentV2", "onError() -> ERROR_CONNECTION_INVALID_PARAM");
        } else if (i == 2048) {
            Log.i("[SA_SDK]SAAgentV2", "onError() -> ERROR_FATAL");
        } else if (i == 2049) {
            Log.i("[SA_SDK]SAAgentV2", "onError() -> ERROR_SDK_NOT_INITIALIZED");
        } else if (i == 2304) {
            Log.i("[SA_SDK]SAAgentV2", "onError() -> ERROR_PERMISSION_DENIED");
        } else if (i != 2305) {
            Log.w("[SA_SDK]SAAgentV2", "onError() error_code: ".concat(String.valueOf(i)));
        } else {
            Log.i("[SA_SDK]SAAgentV2", "onError() -> ERROR_PERMISSION_FAILED");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() throws com.samsung.android.sdk.accessory.d {
        String str;
        SAMessage sAMessage = this.n;
        if (sAMessage == null || (str = this.m) == null) {
            return;
        }
        sAMessage.a(str);
    }

    static /* synthetic */ void h(SAAgentV2 sAAgentV2) {
        Log.d("[SA_SDK]SAAgentV2", "SAAgentV2 - onDestroy:" + sAAgentV2.getClass().getSimpleName());
        synchronized (sAAgentV2.q) {
            sAAgentV2.p = true;
        }
        synchronized (sAAgentV2.r) {
            b bVar = sAAgentV2.a;
            if (bVar != null) {
                bVar.obtainMessage(14).sendToTarget();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        String str;
        j jVar = new j(getApplicationContext());
        Future<Void> a2 = jVar.a();
        jVar.b();
        try {
            a2.get();
        } catch (InterruptedException unused) {
            str = "Regisration failed! : InterruptedException";
            Log.e("[SA_SDK]SAAgentV2", str);
        } catch (ExecutionException unused2) {
            str = "Registration failed! : ExecutionException";
            Log.e("[SA_SDK]SAAgentV2", str);
        }
    }

    private String j() {
        String str;
        try {
            String a2 = this.d.a(getClass().getName());
            Log.i("[SA_SDK]SAAgentV2", "Agent ID retrieved successfully for " + getClass().getName() + " Agent ID:" + a2);
            return a2;
        } catch (com.samsung.android.sdk.accessory.d e2) {
            if (e2.a() == 777 && k.e() >= 298) {
                Log.w("[SA_SDK]SAAgentV2", "Service record was not found in Accessory Framework.Registering service again!");
                i();
                try {
                    Log.i("[SA_SDK]SAAgentV2", "Trying to fetch agent ID after re-registration");
                    return this.d.a(getClass().getName());
                } catch (com.samsung.android.sdk.accessory.d unused) {
                    str = "Failed to retrieve service record after re-registration";
                    Log.e("[SA_SDK]SAAgentV2", str, e2);
                    return null;
                }
            }
            str = "Failed to retrieve service record";
            Log.e("[SA_SDK]SAAgentV2", str, e2);
            return null;
        }
    }

    private SASocket k() {
        a(this.s);
        try {
            Log.d("[SA_SDK]SAAgentV2", "Instantiating SASocket: " + this.s.getName());
            if (this.s.getEnclosingClass() == null || !SAAgentV2.class.isAssignableFrom(this.s.getEnclosingClass())) {
                Constructor<? extends SASocket> declaredConstructor = this.s.getDeclaredConstructor(new Class[0]);
                declaredConstructor.setAccessible(true);
                return declaredConstructor.newInstance(new Object[0]);
            }
            Class<? extends SASocket> cls = this.s;
            Constructor<? extends SASocket> declaredConstructor2 = cls.getDeclaredConstructor(cls.getEnclosingClass());
            declaredConstructor2.setAccessible(true);
            return declaredConstructor2.newInstance(this);
        } catch (IllegalAccessException e2) {
            Log.e("[SA_SDK]SAAgentV2", "Invalid implemetation of SASocket. Provider a public default constructor." + e2.getClass().getSimpleName() + " " + e2.getMessage());
            throw new RuntimeException("Invalid implemetation of SASocket. Provider a public default constructor.");
        } catch (IllegalArgumentException e3) {
            Log.e("[SA_SDK]SAAgentV2", "Invalid implemetation of SASocket. Provider a public default constructor." + e3.getClass().getSimpleName() + " " + e3.getMessage());
            throw new RuntimeException("Invalid implemetation of SASocket. Provider a public default constructor.");
        } catch (InstantiationException e4) {
            Log.e("[SA_SDK]SAAgentV2", "Invalid implemetation of SASocket. Provider a public default constructor." + e4.getClass().getSimpleName() + " " + e4.getMessage());
            throw new RuntimeException("Invalid implemetation of SASocket. Provider a public default constructor.");
        } catch (NoSuchMethodException e5) {
            Log.e("[SA_SDK]SAAgentV2", "Invalid implemetation of SASocket. Provider a public default constructor." + e5.getClass().getSimpleName() + " " + e5.getMessage());
            throw new RuntimeException("Invalid implemetation of SASocket. Provider a public default constructor.");
        } catch (InvocationTargetException e6) {
            Log.e("[SA_SDK]SAAgentV2", "Invalid implemetation of SASocket. Provider a public default constructor." + e6.getClass().getSimpleName() + " " + e6.getMessage());
            throw new RuntimeException("Invalid implemetation of SASocket. Provider a public default constructor.");
        }
    }

    private synchronized void l() {
        com.samsung.android.sdk.accessory.c a2 = com.samsung.android.sdk.accessory.c.a(getApplicationContext());
        this.u = a2;
        if (a2 != null) {
            n a3 = a2.a(getClass().getName());
            this.v = a3;
            if (a3 == null) {
                Log.e("[SA_SDK]SAAgentV2", "fetch service profile description failed !!");
            }
        } else {
            Log.e("[SA_SDK]SAAgentV2", "config  util defualt instance  creation failed !!");
        }
    }

    public static void requestAgent(Context context, String str, RequestAgentCallback requestAgentCallback) {
        d dVar = new d(context, str, requestAgentCallback);
        Message obtainMessage = c.obtainMessage(1);
        obtainMessage.obj = dVar;
        obtainMessage.sendToTarget();
    }

    public static void requestDump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.println("requestDump()");
        synchronized (t) {
            for (SAAgentV2 sAAgentV2 : t.values()) {
                printWriter.println("====[ " + sAAgentV2.getServiceProfileId() + " ]====");
                sAAgentV2.dump(fileDescriptor, printWriter, strArr);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String a(SAMessage sAMessage) {
        this.n = sAMessage;
        return this.m;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a() {
        synchronized (this.r) {
            b bVar = this.a;
            if (bVar != null) {
                bVar.sendEmptyMessage(19);
            } else {
                Log.w("[SA_SDK]SAAgentV2", "handleMessageReceived : null Agent handler");
            }
        }
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
            Log.e("[SA_SDK]SAAgentV2", "Samsung Accessory SDK cannot be initialized");
            onError(null, "Samsung Accessory SDK cannot be initialized. Device or Build not compatible.", i);
            g(i);
        } else if (i != 2304 && i != 2305) {
            Log.w("[SA_SDK]SAAgentV2", "Unknown error: ".concat(String.valueOf(i)));
        } else {
            onError(null, "Permission error!", i);
            g(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(JobParameters jobParameters, com.samsung.android.sdk.accessory.a aVar) {
        synchronized (this.r) {
            b bVar = this.a;
            if (bVar != null) {
                Message obtainMessage = bVar.obtainMessage();
                obtainMessage.what = 6;
                obtainMessage.obj = aVar;
                Bundle bundle = new Bundle();
                bundle.putParcelable(NativeProtocol.WEB_DIALOG_PARAMS, jobParameters);
                obtainMessage.setData(bundle);
                this.a.sendMessage(obtainMessage);
            } else {
                Log.w("[SA_SDK]SAAgentV2", "handleConnectionRequest LOLLIPOP: null Agent handler");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(Intent intent) {
        synchronized (this.r) {
            b bVar = this.a;
            if (bVar != null) {
                Message obtainMessage = bVar.obtainMessage();
                obtainMessage.what = 5;
                obtainMessage.obj = intent;
                this.a.sendMessage(obtainMessage);
            } else {
                Log.w("[SA_SDK]SAAgentV2", "handleConnectionRequest: null Agent handler");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void acceptServiceConnectionRequest(SAPeerAgent sAPeerAgent) {
        if (sAPeerAgent == null) {
            throw new IllegalArgumentException("Illegal argument peerAgent:".concat(String.valueOf(sAPeerAgent)));
        }
        try {
            this.e.initialize(getApplicationContext());
            if (!this.k.remove(sAPeerAgent)) {
                Log.w("[SA_SDK]SAAgentV2", "Accepting service connection with invalid peer agent:" + sAPeerAgent.toString());
                b(sAPeerAgent);
                return;
            }
            Log.i("[SA_SDK]SAAgentV2", "Trying to Accept service connection request from peer:" + sAPeerAgent.getPeerId() + " Transaction:" + sAPeerAgent.c());
            synchronized (this.r) {
                b bVar = this.a;
                if (bVar != null) {
                    Message obtainMessage = bVar.obtainMessage(8);
                    obtainMessage.obj = sAPeerAgent;
                    this.a.sendMessage(obtainMessage);
                } else {
                    Log.w("[SA_SDK]SAAgentV2", "acceptServiceConnection: mBackgroundWorker is null!");
                }
            }
        } catch (SsdkUnsupportedException e2) {
            Log.e("[SA_SDK]SAAgentV2", "exception: " + e2.getMessage());
            a(2049, sAPeerAgent);
        }
    }

    protected void authenticatePeerAgent(SAPeerAgent sAPeerAgent) {
        if (sAPeerAgent == null) {
            throw new IllegalArgumentException("Illegal argument peerAgent:".concat(String.valueOf(sAPeerAgent)));
        }
        try {
            this.e.initialize(getApplicationContext());
            Log.i("[SA_SDK]SAAgentV2", "Authentication requested for peer:" + sAPeerAgent.getPeerId());
            synchronized (this.r) {
                b bVar = this.a;
                if (bVar != null) {
                    Message obtainMessage = bVar.obtainMessage(10);
                    obtainMessage.obj = sAPeerAgent;
                    this.a.sendMessage(obtainMessage);
                } else {
                    Log.w("[SA_SDK]SAAgentV2", "authenticatePeerAgent: mBackgroundWorker is null!");
                }
            }
        } catch (SsdkUnsupportedException e2) {
            Log.e("[SA_SDK]SAAgentV2", "exception: " + e2.getMessage());
            a(2049, sAPeerAgent);
        }
    }

    protected void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.println("SAAgentV2: nothing to dump");
    }

    protected final synchronized void findPeerAgents() {
        Log.d("[SA_SDK]SAAgentV2", "findPeer request received by:" + getClass().getName());
        try {
            this.e.initialize(getApplicationContext());
            synchronized (this.r) {
                b bVar = this.a;
                if (bVar != null) {
                    Message obtainMessage = bVar.obtainMessage();
                    obtainMessage.what = 2;
                    this.a.sendMessage(obtainMessage);
                } else {
                    Log.w("[SA_SDK]SAAgentV2", "findPeerAgents: mBackgroundWorker is null!");
                }
            }
        } catch (SsdkUnsupportedException e2) {
            Log.e("[SA_SDK]SAAgentV2", "exception: " + e2.getMessage());
            a(2049, (SAPeerAgent) null);
        }
    }

    public Context getApplicationContext() {
        return this.o;
    }

    public int getServiceChannelId(int i) {
        String str;
        if (this.v == null) {
            str = "Failed because Service Profile is null";
        } else if (i >= 0 && i < getServiceChannelSize()) {
            return this.v.d().get(i).a();
        } else {
            str = "Failed because of wrong index";
        }
        Log.e("[SA_SDK]SAAgentV2", str);
        return -1;
    }

    public int getServiceChannelSize() {
        n nVar = this.v;
        if (nVar == null) {
            Log.e("[SA_SDK]SAAgentV2", "Failed because Service Profile is null");
            return -1;
        }
        return nVar.d().size();
    }

    public String getServiceProfileId() {
        n nVar = this.v;
        if (nVar == null) {
            Log.e("[SA_SDK]SAAgentV2", "Failed because Service Profile is null");
            return null;
        }
        return nVar.a();
    }

    public String getServiceProfileName() {
        n nVar = this.v;
        if (nVar == null) {
            Log.e("[SA_SDK]SAAgentV2", "Failed because Service Profile is null");
            return null;
        }
        return nVar.b();
    }

    protected void onAuthenticationResponse(SAPeerAgent sAPeerAgent, SAAuthenticationToken sAAuthenticationToken, int i) {
        Log.d("[SA_SDK]SAAgentV2", "Peer authentication response received:".concat(String.valueOf(i)));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onError(SAPeerAgent sAPeerAgent, String str, int i) {
        Log.e("[SA_SDK]SAAgentV2", (sAPeerAgent == null ? new StringBuilder("ACCEPT_STATE_ERROR: ").append(i).append(": ").append(str).append(" PeerAgent: null") : new StringBuilder("ACCEPT_STATE_ERROR: ").append(i).append(": ").append(str).append(" PeerAgent: ").append(sAPeerAgent.getPeerId())).toString());
    }

    protected void onFindPeerAgentsResponse(SAPeerAgent[] sAPeerAgentArr, int i) {
        Log.e("[SA_SDK]SAAgentV2", "Invalid implementation of SAAgentV2.onFindPeerAgentsResponse(SAPeerAgent[], int) should be overrided!");
    }

    protected void onLowMemory() {
        Log.d("[SA_SDK]SAAgentV2", "Service Low Memory");
    }

    protected void onPeerAgentsUpdated(SAPeerAgent[] sAPeerAgentArr, int i) {
        Log.e("[SA_SDK]SAAgentV2", "Invalid implementation of SAAgentV2.onPeerAgentsUpdated(SAPeerAgent[], int) should be overrided!");
    }

    protected void onServiceConnectionRequested(SAPeerAgent sAPeerAgent) {
        if (sAPeerAgent != null) {
            Log.v("[SA_SDK]SAAgentV2", "Accepting connection request by default from Peer:" + sAPeerAgent.getPeerId() + " Transaction:" + sAPeerAgent.c());
        }
        acceptServiceConnectionRequest(sAPeerAgent);
    }

    protected void onServiceConnectionResponse(SAPeerAgent sAPeerAgent, SASocket sASocket, int i) {
        Log.w("[SA_SDK]SAAgentV2", "No Implementaion for onServiceConnectionResponse(SAPeerAgent peerAgent, SASocket socket, int result)!");
    }

    protected void rejectServiceConnectionRequest(SAPeerAgent sAPeerAgent) {
        if (sAPeerAgent == null) {
            throw new IllegalArgumentException("Illegal argument peerAgent:".concat(String.valueOf(sAPeerAgent)));
        }
        try {
            this.e.initialize(getApplicationContext());
            if (!this.k.remove(sAPeerAgent)) {
                Log.w("[SA_SDK]SAAgentV2", "Rejecting service connection with invalid peer agent:" + sAPeerAgent.toString());
                b(sAPeerAgent);
                return;
            }
            Log.i("[SA_SDK]SAAgentV2", "Trying to reject connection request from peer:" + sAPeerAgent.getPeerId() + " Transaction:" + sAPeerAgent.c());
            synchronized (this.r) {
                b bVar = this.a;
                if (bVar != null) {
                    Message obtainMessage = bVar.obtainMessage(9);
                    obtainMessage.obj = sAPeerAgent;
                    this.a.sendMessage(obtainMessage);
                } else {
                    Log.w("[SA_SDK]SAAgentV2", "rejectServiceConnection: mBackgroundWorker is null!");
                }
            }
        } catch (SsdkUnsupportedException e2) {
            Log.e("[SA_SDK]SAAgentV2", "exception: " + e2.getMessage());
            a(2049, sAPeerAgent);
        }
    }

    public void releaseAgent() {
        Message obtainMessage = c.obtainMessage(2);
        obtainMessage.obj = this;
        obtainMessage.sendToTarget();
    }

    protected final void requestServiceConnection(SAPeerAgent sAPeerAgent) {
        if (sAPeerAgent == null) {
            throw new IllegalArgumentException("Illegal argument peerAgent:".concat(String.valueOf(sAPeerAgent)));
        }
        try {
            this.e.initialize(getApplicationContext());
            Log.i("[SA_SDK]SAAgentV2", "Service connection requested for peer:" + sAPeerAgent.getPeerId());
            synchronized (this.r) {
                b bVar = this.a;
                if (bVar != null) {
                    Message obtainMessage = bVar.obtainMessage(7);
                    obtainMessage.obj = sAPeerAgent;
                    this.a.sendMessage(obtainMessage);
                } else {
                    Log.w("[SA_SDK]SAAgentV2", "requestServiceConection: mBackgroundWorker is null!");
                }
            }
        } catch (SsdkUnsupportedException e2) {
            Log.e("[SA_SDK]SAAgentV2", "exception: " + e2.getMessage());
            a(2049, sAPeerAgent);
        }
    }
}
