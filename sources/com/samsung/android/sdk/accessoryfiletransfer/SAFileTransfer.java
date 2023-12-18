package com.samsung.android.sdk.accessoryfiletransfer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ProviderInfo;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Process;
import android.util.Log;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.samsung.android.sdk.SsdkUnsupportedException;
import com.samsung.android.sdk.accessory.SAAgent;
import com.samsung.android.sdk.accessory.SAAgentV2;
import com.samsung.android.sdk.accessory.d;
import com.samsung.android.sdk.accessoryfiletransfer.a;
import java.io.File;
import java.lang.Thread;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
/* loaded from: classes2.dex */
public class SAFileTransfer {
    public static final String ACTION_SAP_FILE_TRANSFER_REQUESTED = "com.samsung.accessory.ftconnection";
    public static final int ERROR_CHANNEL_IO = 1;
    public static final int ERROR_COMMAND_DROPPED = 3;
    public static final int ERROR_CONNECTION_LOST = 5;
    public static final int ERROR_FATAL = 2048;
    public static final int ERROR_FILE_IO = 2;
    public static final int ERROR_NONE = 0;
    public static final int ERROR_NOT_SUPPORTED = 12;
    public static final int ERROR_PEER_AGENT_BUSY = 8;
    public static final int ERROR_PEER_AGENT_NO_RESPONSE = 4;
    public static final int ERROR_PEER_AGENT_REJECTED = 9;
    public static final int ERROR_REQUEST_NOT_QUEUED = -1;
    public static final int ERROR_SPACE_NOT_AVAILABLE = 11;
    public static final int ERROR_TRANSACTION_NOT_FOUND = 13;
    private static int c;
    private HandlerThread e;
    private b f;
    private Object g;
    private Context h;
    private String i;
    private EventListener j;
    private com.samsung.android.sdk.accessoryfiletransfer.a l;
    private ConcurrentHashMap<Integer, a.C0083a> n;
    private SAft o;
    private static final Object d = new Object();
    private static Random b = new Random(System.currentTimeMillis());
    private long k = 0;
    private boolean m = false;
    private BroadcastReceiver p = new BroadcastReceiver() { // from class: com.samsung.android.sdk.accessoryfiletransfer.SAFileTransfer.7
        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            SAFileTransfer.a(SAFileTransfer.this, context, intent);
        }
    };
    c a = new c() { // from class: com.samsung.android.sdk.accessoryfiletransfer.SAFileTransfer.9
        @Override // com.samsung.android.sdk.accessoryfiletransfer.SAFileTransfer.c
        public final void a(int i, int i2) {
            for (Map.Entry entry : SAFileTransfer.this.n.entrySet()) {
                if (((a.C0083a) entry.getValue()).a == i && SAFileTransfer.this.j != null) {
                    SAFileTransfer.this.j.onProgressChanged(((Integer) entry.getKey()).intValue(), i2);
                    return;
                }
            }
        }

        @Override // com.samsung.android.sdk.accessoryfiletransfer.SAFileTransfer.c
        public final void a(int i, String str, int i2) {
            StringBuilder sb;
            for (Map.Entry entry : SAFileTransfer.this.n.entrySet()) {
                a.C0083a c0083a = (a.C0083a) entry.getValue();
                if (c0083a.a == i && SAFileTransfer.this.j != null) {
                    if (c0083a.b != null && i2 != 0) {
                        File file = new File(c0083a.b + "_temp_" + i);
                        if (!file.isFile() || !file.exists()) {
                            sb = new StringBuilder("temp file could not be deleted - ");
                        } else if (file.delete()) {
                            Log.v("[SA_SDK]SAFileTransfer", "temp file deleted successfully - " + c0083a.b);
                            c0083a.b = null;
                        } else {
                            sb = new StringBuilder("temp file could not be deleted - ");
                        }
                        Log.e("[SA_SDK]SAFileTransfer", sb.append(c0083a.b).toString());
                        c0083a.b = null;
                    } else if (c0083a.b != null && i2 == 0) {
                        boolean a2 = SAFileTransfer.a(c0083a.b + "_temp_" + i, c0083a.b);
                        c0083a.b = null;
                        if (!a2) {
                            i2 = 2;
                        }
                    }
                    SAFileTransfer.a(i2);
                    SAFileTransfer.this.j.onTransferCompleted(((Integer) entry.getKey()).intValue(), str, i2);
                    SAFileTransfer.this.n.remove(entry.getKey());
                    if (i == SAFileTransfer.c) {
                        SAFileTransfer.b();
                        return;
                    }
                    return;
                }
            }
            if (SAFileTransfer.this.m && i2 == 9) {
                Log.d("[SA_SDK]SAFileTransfer", "Ignoring onTransferCompleted because setup in progress");
                return;
            }
            SAFileTransfer.this.m = false;
            if (i == SAFileTransfer.c) {
                SAFileTransfer.b();
                if (SAFileTransfer.this.n.containsKey(Integer.valueOf(i)) || SAFileTransfer.this.j == null) {
                    return;
                }
                SAFileTransfer.a(i2);
                SAFileTransfer.this.j.onTransferCompleted(i, str, i2);
            }
        }

        @Override // com.samsung.android.sdk.accessoryfiletransfer.SAFileTransfer.c
        public final void a(int[] iArr, int i) {
            if (iArr == null) {
                SAFileTransfer.b(13);
                SAFileTransfer.this.j.onCancelAllCompleted(13);
                return;
            }
            int[] iArr2 = new int[iArr.length];
            int i2 = 0;
            for (int i3 : iArr) {
                for (Map.Entry entry : SAFileTransfer.this.n.entrySet()) {
                    if (((a.C0083a) entry.getValue()).a == i3 && SAFileTransfer.this.j != null) {
                        iArr2[i2] = ((Integer) entry.getKey()).intValue();
                        i2++;
                        SAFileTransfer.this.n.remove(entry.getKey());
                    }
                }
            }
            if (SAFileTransfer.this.j != null) {
                SAFileTransfer.b(i);
                SAFileTransfer.this.j.onCancelAllCompleted(i);
            }
        }
    };

    /* loaded from: classes2.dex */
    public interface EventListener {
        void onCancelAllCompleted(int i);

        void onProgressChanged(int i, int i2);

        void onTransferCompleted(int i, String str, int i2);

        void onTransferRequested(int i, String str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static class a implements Thread.UncaughtExceptionHandler {
        private a() {
        }

        /* synthetic */ a(byte b) {
            this();
        }

        @Override // java.lang.Thread.UncaughtExceptionHandler
        public final void uncaughtException(final Thread thread, final Throwable th) {
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.samsung.android.sdk.accessoryfiletransfer.SAFileTransfer.a.1
                @Override // java.lang.Runnable
                public final void run() {
                    Log.e("[SA_SDK]SAFileTransfer", "Exception in SAFileTransfer Handler thread :" + thread.getName());
                    throw new RuntimeException(th);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static class b extends Handler {
        public b(Looper looper) {
            super(looper);
        }
    }

    /* loaded from: classes2.dex */
    public interface c {
        void a(int i, int i2);

        void a(int i, String str, int i2);

        void a(int[] iArr, int i);
    }

    public SAFileTransfer(SAAgent sAAgent, EventListener eventListener) {
        if (sAAgent == null || eventListener == null) {
            throw new IllegalArgumentException("FileEventCallback parameter cannot be null");
        }
        this.g = sAAgent;
        this.h = sAAgent.getApplicationContext();
        this.i = sAAgent.getClass().getName();
        this.j = eventListener;
        if (this.o == null) {
            SAft sAft = new SAft();
            this.o = sAft;
            try {
                sAft.initialize(this.h);
            } catch (SsdkUnsupportedException e) {
                e.printStackTrace();
            }
        }
        if (c()) {
            return;
        }
        Log.d("[SA_SDK]SAFileTransfer", "Agent already registered");
        com.samsung.android.sdk.accessoryfiletransfer.a b2 = com.samsung.android.sdk.accessoryfiletransfer.b.b(this.i);
        this.l = b2;
        if (b2 != null) {
            this.e = b2.c();
            this.f = (b) this.l.d();
            this.n = this.l.e();
            this.l.a(this.j);
            this.l.a(this.a);
        }
    }

    public SAFileTransfer(SAAgentV2 sAAgentV2, EventListener eventListener) {
        if (sAAgentV2 == null || eventListener == null) {
            throw new IllegalArgumentException("FileEventCallback parameter cannot be null");
        }
        this.g = sAAgentV2;
        this.h = sAAgentV2.getApplicationContext();
        this.i = sAAgentV2.getClass().getName();
        this.j = eventListener;
        if (this.o == null) {
            SAft sAft = new SAft();
            this.o = sAft;
            try {
                sAft.initialize(this.h);
            } catch (SsdkUnsupportedException e) {
                e.printStackTrace();
            }
        }
        if (c()) {
            return;
        }
        Log.d("[SA_SDK]SAFileTransfer", "Agent already registered");
        com.samsung.android.sdk.accessoryfiletransfer.a b2 = com.samsung.android.sdk.accessoryfiletransfer.b.b(this.i);
        this.l = b2;
        if (b2 == null) {
            Log.w("[SA_SDK]SAFileTransfer", "CallingAgentInfo is null!");
            return;
        }
        this.e = b2.c();
        this.f = (b) this.l.d();
        this.n = this.l.e();
        this.l.a(this.j);
        this.l.a(this.a);
    }

    private static Context a(Context context) {
        return Build.VERSION.SDK_INT >= 24 ? context.createDeviceProtectedStorageContext() : context;
    }

    static /* synthetic */ void a(int i) {
        if (i == 8) {
            Log.i("[SA_SDK]SAFileTransfer", "onTransferCompleted() -> ERROR_PEER_AGENT_BUSY");
        } else if (i == 9) {
            Log.i("[SA_SDK]SAFileTransfer", "onTransferCompleted() -> ERROR_PEER_AGENT_REJECTED");
        } else if (i == 11) {
            Log.i("[SA_SDK]SAFileTransfer", "onTransferCompleted() -> ERROR_SPACE_NOT_AVAILABLE");
        } else if (i == 2048) {
            Log.i("[SA_SDK]SAFileTransfer", "onTransferCompleted() -> ERROR_FATAL");
        } else {
            switch (i) {
                case -1:
                    Log.i("[SA_SDK]SAFileTransfer", "onTransferCompleted() -> ERROR_REQUEST_NOT_QUEUED");
                    return;
                case 0:
                    Log.i("[SA_SDK]SAFileTransfer", "onTransferCompleted() -> ERROR_NONE");
                    return;
                case 1:
                    Log.i("[SA_SDK]SAFileTransfer", "onTransferCompleted() -> ERROR_CHANNEL_IO");
                    return;
                case 2:
                    Log.i("[SA_SDK]SAFileTransfer", "onTransferCompleted() -> ERROR_FILE_IO");
                    return;
                case 3:
                    Log.i("[SA_SDK]SAFileTransfer", "onTransferCompleted() -> ERROR_COMMAND_DROPPED");
                    return;
                case 4:
                    Log.i("[SA_SDK]SAFileTransfer", "onTransferCompleted() -> ERROR_PEER_AGENT_NO_RESPONSE");
                    return;
                case 5:
                    Log.i("[SA_SDK]SAFileTransfer", "onTransferCompleted() -> ERROR_CONNECTION_LOST");
                    return;
                default:
                    Log.w("[SA_SDK]SAFileTransfer", "onTransferCompleted() error_code: ".concat(String.valueOf(i)));
                    return;
            }
        }
    }

    static /* synthetic */ void a(SAFileTransfer sAFileTransfer, Context context, Intent intent) {
        while (true) {
            c = intent.getIntExtra("transId", -1);
            String stringExtra = intent.getStringExtra("agentClass");
            if (stringExtra == null) {
                stringExtra = a(context).getSharedPreferences("AccessoryPreferences", 0).getString(intent.getStringExtra("peerId"), null);
            }
            Log.d("[SA_SDK]SAFileTransfer", "class now:".concat(String.valueOf(stringExtra)));
            if (stringExtra == null) {
                Log.e("[SA_SDK]SAFileTransfer", "Target agent was cleared. Re-registering");
                Intent intent2 = new Intent();
                intent2.setAction("com.samsung.accessory.action.REGISTER_AGENT");
                intent2.setPackage(context.getPackageName());
                context.sendBroadcast(intent2);
                return;
            } else if (sAFileTransfer.g == null) {
                Log.e("[SA_SDK]SAFileTransfer", "Calling agent was cleared");
                return;
            } else if (!stringExtra.equalsIgnoreCase(sAFileTransfer.i)) {
                Log.e("[SA_SDK]SAFileTransfer", "Class name not matched with " + sAFileTransfer.i);
                return;
            } else {
                final com.samsung.android.sdk.accessoryfiletransfer.a b2 = com.samsung.android.sdk.accessoryfiletransfer.b.b(stringExtra);
                if (b2 != null) {
                    if (b2.a() == null) {
                        Log.e("[SA_SDK]SAFileTransfer", "callback is not registered for ".concat(String.valueOf(stringExtra)));
                        return;
                    }
                    final String stringExtra2 = intent.getStringExtra("filePath");
                    Log.d("[SA_SDK]SAFileTransfer", "Informing app of incoming file transfer request on registered callback-tid: " + c);
                    sAFileTransfer.f.post(new Runnable() { // from class: com.samsung.android.sdk.accessoryfiletransfer.SAFileTransfer.8
                        @Override // java.lang.Runnable
                        public final void run() {
                            try {
                                com.samsung.android.sdk.accessoryfiletransfer.b.a(SAFileTransfer.this.h, SAFileTransfer.this.i).a(SAFileTransfer.this.a, SAFileTransfer.c);
                                SAFileTransfer.this.m = true;
                                b2.a().onTransferRequested(SAFileTransfer.c, stringExtra2);
                            } catch (d e) {
                                e.printStackTrace();
                            } catch (IllegalAccessException e2) {
                                e2.printStackTrace();
                            }
                        }
                    });
                    return;
                }
                Log.e("[SA_SDK]SAFileTransfer", "AgentInfo is NULL! Re-Registering");
                sAFileTransfer.c();
            }
        }
    }

    private boolean a(String str) {
        Log.d("[SA_SDK]SAFileTransfer", "checkPathPermission calling pkg: " + this.h.getPackageName() + " file Path:" + str);
        if (str == null) {
            return false;
        }
        return !str.startsWith("/data/data") || str.contains(this.h.getPackageName());
    }

    private boolean a(String str, int i) {
        String str2;
        String str3;
        boolean z = true;
        if (str != null) {
            if (str.length() != 0) {
                if (a(str)) {
                    File file = new File(str);
                    if (file.isDirectory()) {
                        str2 = "given path is a directory";
                    } else {
                        String substring = str.substring(str.lastIndexOf("/") + 1, str.length());
                        if (!substring.contains(".")) {
                            str3 = "No extension provided";
                        } else if (substring.charAt(substring.length() - 1) == '.') {
                            str3 = "extension length is 0";
                        } else {
                            File parentFile = file.getParentFile();
                            if (parentFile != null) {
                                z = parentFile.exists();
                                if (!z) {
                                    Log.d("[SA_SDK]SAFileTransfer", "Directory does not exist!");
                                }
                            } else {
                                str2 = "getParentFile() is null ";
                            }
                        }
                    }
                    Log.d("[SA_SDK]SAFileTransfer", str2);
                } else {
                    str3 = "checkReceiveParams return false, internal path";
                }
                Log.d("[SA_SDK]SAFileTransfer", str3);
                return false;
            }
            if (z || !this.n.containsKey(Integer.valueOf(i))) {
                return z;
            }
            Log.d("[SA_SDK]SAFileTransfer", "transactionId already exist");
            return false;
        }
        z = false;
        if (z) {
        }
        return z;
    }

    static /* synthetic */ boolean a(String str, String str2) {
        String valueOf;
        String str3;
        File file = new File(str2);
        if (file.isFile() && file.exists()) {
            String str4 = str2.substring(0, str2.lastIndexOf("/") + 1) + str2.substring(str.lastIndexOf("/") + 1, str2.lastIndexOf(".")) + System.currentTimeMillis() + str2.substring(str2.lastIndexOf("."), str2.length());
            if (!new File(str).renameTo(new File(str4))) {
                Log.e("[SA_SDK]SAFileTransfer", "File rename failed");
                return false;
            }
            valueOf = String.valueOf(str4);
            str3 = "File successfully renamed ";
        } else if (!new File(str).renameTo(new File(str2))) {
            Log.e("[SA_SDK]SAFileTransfer", "File rename failed");
            return false;
        } else {
            valueOf = String.valueOf(str2);
            str3 = "File successfully renamed: ";
        }
        Log.v("[SA_SDK]SAFileTransfer", str3.concat(valueOf));
        return true;
    }

    static /* synthetic */ int b() {
        c = 0;
        return 0;
    }

    static /* synthetic */ void b(int i) {
        if (i == 12) {
            Log.i("[SA_SDK]SAFileTransfer", "onCancelAllCompleted() -> ERROR_NOT_SUPPORTED");
        } else if (i != 13) {
            Log.w("[SA_SDK]SAFileTransfer", "onCancelAllCompleted() error_code: ".concat(String.valueOf(i)));
        } else {
            Log.i("[SA_SDK]SAFileTransfer", "onCancelAllCompleted() -> ERROR_TRANSACTION_NOT_FOUND");
        }
    }

    private static boolean b(String str) {
        return str.startsWith("/data/data");
    }

    private boolean c() {
        synchronized (d) {
            if (com.samsung.android.sdk.accessoryfiletransfer.b.c(this.i)) {
                HandlerThread handlerThread = new HandlerThread("FileTransferHandlerThread");
                this.e = handlerThread;
                handlerThread.setUncaughtExceptionHandler(new a((byte) 0));
                this.e.start();
                Log.d("[SA_SDK]SAFileTransfer", "FileTransferHandlerThread started");
                Looper looper = this.e.getLooper();
                if (looper != null) {
                    this.f = new b(looper);
                }
                if (this.f != null) {
                    this.n = new ConcurrentHashMap<>();
                    this.l = new com.samsung.android.sdk.accessoryfiletransfer.a(this.j, this.e, this.f, this.a, this.n);
                    LocalBroadcastManager.getInstance(this.h).registerReceiver(this.p, new IntentFilter("com.samsung.accessory.ftconnection.internal"));
                    com.samsung.android.sdk.accessoryfiletransfer.b.a(this.i, this.l);
                    this.f.post(new Runnable() { // from class: com.samsung.android.sdk.accessoryfiletransfer.SAFileTransfer.6
                        @Override // java.lang.Runnable
                        public final void run() {
                            try {
                                com.samsung.android.sdk.accessoryfiletransfer.b.a(SAFileTransfer.this.h, SAFileTransfer.this.i);
                            } catch (d e) {
                                e.printStackTrace();
                            } catch (IllegalAccessException e2) {
                                e2.printStackTrace();
                            }
                        }
                    });
                    return true;
                }
            }
            return false;
        }
    }

    private String d() {
        List<ProviderInfo> list;
        try {
            list = this.h.getPackageManager().queryContentProviders(this.h.getPackageName(), Process.myUid(), 0);
        } catch (RuntimeException e) {
            e.printStackTrace();
            list = null;
        }
        if (list != null) {
            for (ProviderInfo providerInfo : list) {
                if (providerInfo.name.equalsIgnoreCase("androidx.core.content.FileProvider")) {
                    Log.d("[SA_SDK]SAFileTransfer", "Authority:" + providerInfo.authority);
                    return providerInfo.authority;
                }
            }
            return null;
        }
        return null;
    }

    private int e() {
        long currentTimeMillis;
        do {
            currentTimeMillis = System.currentTimeMillis();
        } while (currentTimeMillis == this.k);
        this.k = currentTimeMillis;
        b.setSeed(currentTimeMillis);
        return b.nextInt();
    }

    public void cancel(final int i) {
        if (this.g == null || this.j == null) {
            Log.d("[SA_SDK]SAFileTransfer", "Using invalid instance of SAFileTransfer(). Please re-register.");
        } else if (!this.n.containsKey(Integer.valueOf(i))) {
            throw new IllegalArgumentException("Wrong transaction id used for cancel");
        } else {
            this.f.post(new Runnable() { // from class: com.samsung.android.sdk.accessoryfiletransfer.SAFileTransfer.4
                @Override // java.lang.Runnable
                public final void run() {
                    try {
                        a.C0083a c0083a = (a.C0083a) SAFileTransfer.this.n.get(Integer.valueOf(i));
                        if (c0083a == null) {
                            Log.d("[SA_SDK]SAFileTransfer", "cancelFile aborted because service connection or transaction already closed.");
                        } else if (c0083a.a == 0) {
                            c0083a.a = -1;
                            Log.d("[SA_SDK]SAFileTransfer", "Cancel called before transaction id is genereated" + i);
                        } else if (c0083a.a == -1) {
                            Log.d("[SA_SDK]SAFileTransfer", "Cancel called again before transaction id is genereated" + i);
                        } else {
                            com.samsung.android.sdk.accessoryfiletransfer.b.a(SAFileTransfer.this.h, SAFileTransfer.this.i).a(c0083a.a);
                        }
                    } catch (d e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e2) {
                        e2.printStackTrace();
                    }
                }
            });
        }
    }

    public void cancelAll() {
        if (this.g == null || this.j == null) {
            Log.d("[SA_SDK]SAFileTransfer", "Using invalid instance of SAFileTransfer. Please re-register.");
            return;
        }
        final String string = a(this.h).getSharedPreferences("AccessoryPreferences", 0).getString(this.i, null);
        if (string == null) {
            Log.e("[SA_SDK]SAFileTransfer", "Your service was not found. Please re-register");
        } else {
            this.f.post(new Runnable() { // from class: com.samsung.android.sdk.accessoryfiletransfer.SAFileTransfer.5
                @Override // java.lang.Runnable
                public final void run() {
                    try {
                        int a2 = com.samsung.android.sdk.accessoryfiletransfer.b.a(SAFileTransfer.this.h, SAFileTransfer.this.i).a(string);
                        if (a2 == 0) {
                            SAFileTransfer.b(12);
                            SAFileTransfer.this.j.onCancelAllCompleted(12);
                        } else if (a2 == 13) {
                            SAFileTransfer.b(13);
                            SAFileTransfer.this.j.onCancelAllCompleted(13);
                        }
                    } catch (d e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e2) {
                        e2.printStackTrace();
                    }
                }
            });
        }
    }

    public void close() {
        ConcurrentHashMap<Integer, a.C0083a> concurrentHashMap = this.n;
        if (concurrentHashMap != null && concurrentHashMap.size() != 0) {
            throw new RuntimeException("Cannot close as File Transfer is in progress!");
        }
        if (this.g == null || this.j == null) {
            Log.d("[SA_SDK]SAFileTransfer", "Using invalid instance of SAFileTransfer(). Please re-register.");
            return;
        }
        Log.d("[SA_SDK]SAFileTransfer", "stopFileTransferService() called by : " + this.i);
        Context context = this.h;
        if (context == null || LocalBroadcastManager.getInstance(context) == null) {
            Log.d("[SA_SDK]SAFileTransfer", "Could not unregister receiver. Calling context is null");
        } else {
            LocalBroadcastManager.getInstance(context).unregisterReceiver(this.p);
        }
        com.samsung.android.sdk.accessoryfiletransfer.b.d(this.i);
        ConcurrentHashMap<Integer, a.C0083a> concurrentHashMap2 = this.n;
        if (concurrentHashMap2 != null) {
            concurrentHashMap2.clear();
        }
        this.g = null;
        this.j = null;
    }

    /* JADX WARN: Removed duplicated region for block: B:53:0x00ea A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00fe  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0102  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0107  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x011e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void receive(final int r12, final java.lang.String r13) {
        android.net.Uri r3;
        java.io.File r8;
        java.lang.String r0;
        if (r11.g == null || r11.j == null) {
            android.util.Log.d("[SA_SDK]SAFileTransfer", "Using invalid instance of SAFileTransfer(). Please re-register.");
            r11.m = false;
        } else if (!a(r13, r12) || r12 != com.samsung.android.sdk.accessoryfiletransfer.SAFileTransfer.c) {
            android.util.Log.d("[SA_SDK]SAFileTransfer", "TransactionId: Given[" + r12 + "] Current [" + com.samsung.android.sdk.accessoryfiletransfer.SAFileTransfer.c + "]");
            r11.m = false;
            throw new java.lang.IllegalArgumentException("Wrong filepath or transaction id used");
        } else {
            com.samsung.android.sdk.accessoryfiletransfer.a.C0083a r0 = new com.samsung.android.sdk.accessoryfiletransfer.a.C0083a();
            r0.a = r12;
            r0.c = r13;
            r11.n.put(java.lang.Integer.valueOf(r12), r0);
            if (r11.o == null) {
                com.samsung.android.sdk.accessoryfiletransfer.SAft r1 = new com.samsung.android.sdk.accessoryfiletransfer.SAft();
                r11.o = r1;
                try {
                    r1.initialize(r11.h);
                } catch (com.samsung.android.sdk.SsdkUnsupportedException r1) {
                    r1.printStackTrace();
                }
            }
            java.lang.String r1 = r11.o.getFileTransferPackageName(r11.h);
            java.lang.String r3 = d();
            if (!r11.o.a() || r1 == null || r3 == null) {
                android.util.Log.v("[SA_SDK]SAFileTransfer", "Accessory Framework doesn't support content URI !!");
                r3 = null;
            } else {
                try {
                } catch (java.io.IOException e) {
                    r0 = e;
                    r8 = null;
                } catch (java.lang.IllegalArgumentException e) {
                    r0 = e;
                    r8 = null;
                } catch (java.lang.NullPointerException e) {
                    r0 = e;
                    r8 = null;
                }
                if (r13 == null) {
                    android.util.Log.e("[SA_SDK]SAFileTransfer", "File path is wrong!!");
                    return;
                }
                android.util.Log.v("[SA_SDK]SAFileTransfer", "File :".concat(java.lang.String.valueOf(r13)));
                r8 = new java.io.File(r13 + "_temp_" + r12);
                try {
                    android.util.Log.e("[SA_SDK]SAFileTransfer", "Temporary File Created for content URI : " + r8.createNewFile());
                    r3 = androidx.core.content.FileProvider.getUriForFile(r11.h, r3, r8);
                    if (r3 == null) {
                        android.util.Log.e("[SA_SDK]SAFileTransfer", "Cannot create the content URI !");
                        if (r8.delete()) {
                            android.util.Log.v("[SA_SDK]SAFileTransfer", "temp file deleted successfully ");
                        } else {
                            android.util.Log.e("[SA_SDK]SAFileTransfer", "temp file could not be deleted ");
                        }
                    } else {
                        r0.b = r13;
                        r11.h.grantUriPermission(r1, r3, 2);
                    }
                } catch (java.io.IOException e) {
                    r0 = e;
                    r0.printStackTrace();
                    r0 = "Cannot create the File !";
                    android.util.Log.e("[SA_SDK]SAFileTransfer", r0);
                    r3 = null;
                    if (r3 == null) {
                        if (r8.delete()) {
                        }
                    }
                    if (r3 == null) {
                        throw new java.lang.IllegalArgumentException("Content URI needs to be implemented for receiving to internal folders. Please refer to sdk documentation for more details");
                    }
                    if (r3 != null) {
                    }
                    r11.f.post(new java.lang.Runnable() { // from class: com.samsung.android.sdk.accessoryfiletransfer.SAFileTransfer.2
                        {
                        }

                        @Override // java.lang.Runnable
                        public final void run() {
                            try {
                                try {
                                    try {
                                        com.samsung.android.sdk.accessoryfiletransfer.b.a(com.samsung.android.sdk.accessoryfiletransfer.SAFileTransfer.this.h, com.samsung.android.sdk.accessoryfiletransfer.SAFileTransfer.this.i).a(com.samsung.android.sdk.accessoryfiletransfer.SAFileTransfer.this.a, r2, r3, r4, true);
                                    } catch (java.lang.IllegalAccessException r1) {
                                        r1.printStackTrace();
                                    }
                                } catch (com.samsung.android.sdk.accessory.d r1) {
                                    r1.printStackTrace();
                                }
                            } finally {
                                com.samsung.android.sdk.accessoryfiletransfer.SAFileTransfer.this.m = false;
                            }
                        }
                    });
                } catch (java.lang.IllegalArgumentException e) {
                    r0 = e;
                    r0.printStackTrace();
                    android.util.Log.e("[SA_SDK]SAFileTransfer", "Cannot create the content URI !");
                    r3 = null;
                    if (r3 == null) {
                    }
                    if (r3 == null) {
                    }
                    if (r3 != null) {
                    }
                    r11.f.post(new java.lang.Runnable() { // from class: com.samsung.android.sdk.accessoryfiletransfer.SAFileTransfer.2
                        {
                        }

                        @Override // java.lang.Runnable
                        public final void run() {
                            try {
                                try {
                                    try {
                                        com.samsung.android.sdk.accessoryfiletransfer.b.a(com.samsung.android.sdk.accessoryfiletransfer.SAFileTransfer.this.h, com.samsung.android.sdk.accessoryfiletransfer.SAFileTransfer.this.i).a(com.samsung.android.sdk.accessoryfiletransfer.SAFileTransfer.this.a, r2, r3, r4, true);
                                    } catch (java.lang.IllegalAccessException r1) {
                                        r1.printStackTrace();
                                    }
                                } catch (com.samsung.android.sdk.accessory.d r1) {
                                    r1.printStackTrace();
                                }
                            } finally {
                                com.samsung.android.sdk.accessoryfiletransfer.SAFileTransfer.this.m = false;
                            }
                        }
                    });
                } catch (java.lang.NullPointerException e) {
                    r0 = e;
                    r0.printStackTrace();
                    r0 = "Cannot create the content URI !!";
                    android.util.Log.e("[SA_SDK]SAFileTransfer", r0);
                    r3 = null;
                    if (r3 == null) {
                    }
                    if (r3 == null) {
                    }
                    if (r3 != null) {
                    }
                    r11.f.post(new java.lang.Runnable() { // from class: com.samsung.android.sdk.accessoryfiletransfer.SAFileTransfer.2
                        {
                        }

                        @Override // java.lang.Runnable
                        public final void run() {
                            try {
                                try {
                                    try {
                                        com.samsung.android.sdk.accessoryfiletransfer.b.a(com.samsung.android.sdk.accessoryfiletransfer.SAFileTransfer.this.h, com.samsung.android.sdk.accessoryfiletransfer.SAFileTransfer.this.i).a(com.samsung.android.sdk.accessoryfiletransfer.SAFileTransfer.this.a, r2, r3, r4, true);
                                    } catch (java.lang.IllegalAccessException r1) {
                                        r1.printStackTrace();
                                    }
                                } catch (com.samsung.android.sdk.accessory.d r1) {
                                    r1.printStackTrace();
                                }
                            } finally {
                                com.samsung.android.sdk.accessoryfiletransfer.SAFileTransfer.this.m = false;
                            }
                        }
                    });
                }
                if (r3 == null && r8 != null && r8.isFile() && r8.exists()) {
                    if (r8.delete()) {
                        android.util.Log.e("[SA_SDK]SAFileTransfer", "temp file could not be deleted ");
                    } else {
                        android.util.Log.v("[SA_SDK]SAFileTransfer", "temp file deleted successfully ");
                    }
                }
                if (r3 == null && b(r13)) {
                    throw new java.lang.IllegalArgumentException("Content URI needs to be implemented for receiving to internal folders. Please refer to sdk documentation for more details");
                }
            }
            final java.lang.String r5 = r3 != null ? r3.toString() : null;
            r11.f.post(new java.lang.Runnable() { // from class: com.samsung.android.sdk.accessoryfiletransfer.SAFileTransfer.2
                {
                }

                @Override // java.lang.Runnable
                public final void run() {
                    try {
                        try {
                            try {
                                com.samsung.android.sdk.accessoryfiletransfer.b.a(com.samsung.android.sdk.accessoryfiletransfer.SAFileTransfer.this.h, com.samsung.android.sdk.accessoryfiletransfer.SAFileTransfer.this.i).a(com.samsung.android.sdk.accessoryfiletransfer.SAFileTransfer.this.a, r2, r3, r4, true);
                            } catch (java.lang.IllegalAccessException r1) {
                                r1.printStackTrace();
                            }
                        } catch (com.samsung.android.sdk.accessory.d r1) {
                            r1.printStackTrace();
                        }
                    } finally {
                        com.samsung.android.sdk.accessoryfiletransfer.SAFileTransfer.this.m = false;
                    }
                }
            });
        }
    }

    public void reject(final int i) {
        if (this.g == null || this.j == null) {
            Log.d("[SA_SDK]SAFileTransfer", "Using invalid instance of SAFileTransfer(). Please re-register.");
        } else if (!a("", i) || c != i) {
            throw new IllegalArgumentException("Wrong transaction id used in reject()");
        } else {
            a.C0083a c0083a = new a.C0083a();
            c0083a.a = i;
            c0083a.c = "";
            this.n.put(Integer.valueOf(i), c0083a);
            this.f.post(new Runnable() { // from class: com.samsung.android.sdk.accessoryfiletransfer.SAFileTransfer.3
                @Override // java.lang.Runnable
                public final void run() {
                    try {
                        com.samsung.android.sdk.accessoryfiletransfer.b.a(SAFileTransfer.this.h, SAFileTransfer.this.i).a(null, i, "", null, false);
                    } catch (d e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e2) {
                        e2.printStackTrace();
                    }
                }
            });
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:121:0x016b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0142  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x015e  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0164  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x01bc  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int send(final com.samsung.android.sdk.accessory.SAPeerAgent r17, final java.lang.String r18) {
        java.lang.String r0;
        android.net.Uri r6;
        com.samsung.android.sdk.accessoryfiletransfer.a.C0083a r4;
        int r1 = -1;
        if (r16.g == null || r16.j == null) {
            android.util.Log.d("[SA_SDK]SAFileTransfer", "Using invalid instance of SAFileTransfer(). Please re-register.");
            return -1;
        } else if (r17 != null) {
            if (r18 == null || r18.length() == 0 || !a(r18)) {
                throw new java.lang.IllegalArgumentException("Wrong file path");
            }
            try {
                android.util.Log.v("[SA_SDK]SAFileTransfer", "File has a valid extentsion: ".concat(java.lang.String.valueOf(r18.substring(r18.lastIndexOf("."), r18.length()))));
                android.net.Uri r9 = android.net.Uri.parse(r18);
                if ("file".equalsIgnoreCase(r9.getScheme())) {
                    r0 = r9.getPath();
                    if (r0 != null) {
                        android.util.Log.v("[SA_SDK]SAFileTransfer", "URI scheme is SCHEME_FILE  File Path : ".concat(java.lang.String.valueOf(r0)));
                    }
                } else if (com.google.firebase.analytics.FirebaseAnalytics.Param.CONTENT.equalsIgnoreCase(r9.getScheme())) {
                    android.database.Cursor r6 = r16.h.getContentResolver().query(r9, new java.lang.String[]{"_data"}, null, null, null);
                    if (r6 == null || !r6.moveToFirst()) {
                        r0 = r18;
                    } else {
                        try {
                            r0 = r6.getString(0);
                            if (r0 != null) {
                                android.util.Log.v("[SA_SDK]SAFileTransfer", "URI ContentResolver is SCHEME_CONTENT File Path : ".concat(java.lang.String.valueOf(r0)));
                            }
                            r6.close();
                            r6 = null;
                        } finally {
                            r6.close();
                        }
                    }
                    if (r6 != null) {
                    }
                } else {
                    r0 = r18;
                }
                java.io.File r6 = new java.io.File(r0);
                if (r6.exists()) {
                    if (r6.isDirectory()) {
                        throw new java.lang.IllegalArgumentException("File is a directory");
                    }
                    if (r6.length() != 0) {
                        android.util.Log.v("[SA_SDK]SAFileTransfer", "File is valid !!");
                        final int r15 = e();
                        if (r16.o == null) {
                            com.samsung.android.sdk.accessoryfiletransfer.SAft r0 = new com.samsung.android.sdk.accessoryfiletransfer.SAft();
                            r16.o = r0;
                            try {
                                r0.initialize(r16.h);
                            } catch (com.samsung.android.sdk.SsdkUnsupportedException r0) {
                                r0.printStackTrace();
                            }
                        }
                        java.lang.String r0 = r16.o.getFileTransferPackageName(r16.h);
                        java.lang.String r6 = d();
                        if (!r16.o.a() || r0 == null || r6 == null) {
                            android.util.Log.v("[SA_SDK]SAFileTransfer", "FTCore version doesnot support content uri");
                            r6 = null;
                        } else {
                            try {
                            } catch (java.lang.IllegalArgumentException r0) {
                                r0.printStackTrace();
                                android.util.Log.e("[SA_SDK]SAFileTransfer", "Cannot create the content URI !");
                                r6 = null;
                                if (r6 == null) {
                                    throw new java.lang.IllegalArgumentException("content uri needs to be implemented for sending from internal folders.Please check file-transfer sdk documentation for more details");
                                }
                                com.samsung.android.sdk.accessoryfiletransfer.a.C0083a r14 = new com.samsung.android.sdk.accessoryfiletransfer.a.C0083a();
                                if (r6 != null) {
                                }
                                if (com.samsung.android.sdk.accessoryfiletransfer.b.a()) {
                                }
                                return r15;
                            } catch (java.lang.NullPointerException r0) {
                                r0.printStackTrace();
                                android.util.Log.e("[SA_SDK]SAFileTransfer", "Cannot create the content URI !! ");
                                r6 = null;
                                if (r6 == null) {
                                }
                                com.samsung.android.sdk.accessoryfiletransfer.a.C0083a r14 = new com.samsung.android.sdk.accessoryfiletransfer.a.C0083a();
                                if (r6 != null) {
                                }
                                if (com.samsung.android.sdk.accessoryfiletransfer.b.a()) {
                                }
                                return r15;
                            }
                            if (r18 == null) {
                                android.util.Log.e("[SA_SDK]SAFileTransfer", "File path is wrong!!");
                                return -1;
                            }
                            android.util.Log.v("[SA_SDK]SAFileTransfer", "File :".concat(java.lang.String.valueOf(r18)));
                            r6 = androidx.core.content.FileProvider.getUriForFile(r16.h, r6, new java.io.File(r18));
                            if (r6 == null) {
                                android.util.Log.e("[SA_SDK]SAFileTransfer", "Cannot create the content URI !");
                            } else {
                                r16.h.grantUriPermission(r0, r6, 1);
                            }
                            if (r6 == null && b(r18)) {
                                throw new java.lang.IllegalArgumentException("content uri needs to be implemented for sending from internal folders.Please check file-transfer sdk documentation for more details");
                            }
                        }
                        com.samsung.android.sdk.accessoryfiletransfer.a.C0083a r14 = new com.samsung.android.sdk.accessoryfiletransfer.a.C0083a();
                        java.lang.String r13 = r6 != null ? r6.toString() : null;
                        if (com.samsung.android.sdk.accessoryfiletransfer.b.a()) {
                            try {
                                r4 = r14;
                                try {
                                    r1 = com.samsung.android.sdk.accessoryfiletransfer.b.a(r16.h, r16.i).a(r16.h, r16.i, r16.a, r17, r13, r18);
                                } catch (com.samsung.android.sdk.accessory.d e) {
                                    r0 = e;
                                    r0.printStackTrace();
                                    android.util.Log.d("[SA_SDK]SAFileTransfer", "received tx from FTCore" + r15 + " " + r1);
                                    r4.a = r1;
                                    r4.c = r18;
                                    r16.n.put(java.lang.Integer.valueOf(r15), r4);
                                    return r15;
                                } catch (java.lang.IllegalAccessException e) {
                                    r0 = e;
                                    r0.printStackTrace();
                                    android.util.Log.d("[SA_SDK]SAFileTransfer", "received tx from FTCore" + r15 + " " + r1);
                                    r4.a = r1;
                                    r4.c = r18;
                                    r16.n.put(java.lang.Integer.valueOf(r15), r4);
                                    return r15;
                                }
                            } catch (com.samsung.android.sdk.accessory.d e) {
                                r0 = e;
                                r4 = r14;
                            } catch (java.lang.IllegalAccessException e) {
                                r0 = e;
                                r4 = r14;
                            }
                            android.util.Log.d("[SA_SDK]SAFileTransfer", "received tx from FTCore" + r15 + " " + r1);
                            r4.a = r1;
                            r4.c = r18;
                            r16.n.put(java.lang.Integer.valueOf(r15), r4);
                        } else {
                            r14.a = 0;
                            r14.c = r18;
                            r16.n.put(java.lang.Integer.valueOf(r15), r14);
                            android.util.Log.d("[SA_SDK]SAFileTransfer", "returning temporary transaction id".concat(java.lang.String.valueOf(r15)));
                            final java.lang.String r4 = r13;
                            r16.f.post(new java.lang.Runnable() { // from class: com.samsung.android.sdk.accessoryfiletransfer.SAFileTransfer.1
                                {
                                }

                                @Override // java.lang.Runnable
                                public final void run() {
                                    try {
                                        int r3 = com.samsung.android.sdk.accessoryfiletransfer.b.a(com.samsung.android.sdk.accessoryfiletransfer.SAFileTransfer.this.h, com.samsung.android.sdk.accessoryfiletransfer.SAFileTransfer.this.i).a(com.samsung.android.sdk.accessoryfiletransfer.SAFileTransfer.this.h, com.samsung.android.sdk.accessoryfiletransfer.SAFileTransfer.this.i, com.samsung.android.sdk.accessoryfiletransfer.SAFileTransfer.this.a, r2, r3, r4);
                                        android.util.Log.d("[SA_SDK]SAFileTransfer", "received tx from FTCore" + r5 + " " + r3);
                                        com.samsung.android.sdk.accessoryfiletransfer.a.C0083a r4 = (com.samsung.android.sdk.accessoryfiletransfer.a.C0083a) com.samsung.android.sdk.accessoryfiletransfer.SAFileTransfer.this.n.get(java.lang.Integer.valueOf(r5));
                                        if (r4 != null && r4.a == -1) {
                                            android.util.Log.d("[SA_SDK]SAFileTransfer", "Cancel aready requested for " + r5 + " " + r3);
                                            r4.a = r3;
                                            com.samsung.android.sdk.accessoryfiletransfer.SAFileTransfer.this.cancel(r5);
                                        }
                                        com.samsung.android.sdk.accessoryfiletransfer.a.C0083a r0 = new com.samsung.android.sdk.accessoryfiletransfer.a.C0083a();
                                        r0.a = r3;
                                        r0.c = r4;
                                        com.samsung.android.sdk.accessoryfiletransfer.SAFileTransfer.this.n.put(java.lang.Integer.valueOf(r5), r0);
                                    } catch (com.samsung.android.sdk.accessory.d r0) {
                                        r0.printStackTrace();
                                        com.samsung.android.sdk.accessoryfiletransfer.SAFileTransfer.a(2048);
                                        com.samsung.android.sdk.accessoryfiletransfer.SAFileTransfer.this.j.onTransferCompleted(-1, r4, 2048);
                                    } catch (java.lang.IllegalAccessException r0) {
                                        r0.printStackTrace();
                                    }
                                }
                            });
                        }
                        return r15;
                    }
                    throw new java.lang.IllegalArgumentException("File length is 0");
                }
                throw new java.lang.IllegalArgumentException("File doesnot exist");
            } catch (java.lang.StringIndexOutOfBoundsException r0) {
                r0.printStackTrace();
                throw new java.lang.IllegalArgumentException("Wrong file..does not have extension");
            }
        } else {
            throw new java.lang.IllegalArgumentException("PeerAgent cannot be null");
        }
    }
}
