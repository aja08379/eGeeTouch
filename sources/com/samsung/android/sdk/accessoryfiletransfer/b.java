package com.samsung.android.sdk.accessoryfiletransfer;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import androidx.work.WorkRequest;
import com.samsung.accessory.safiletransfer.a.c;
import com.samsung.accessory.safiletransfer.a.h;
import com.samsung.accessory.safiletransfer.a.i;
import com.samsung.accessory.safiletransfer.core.ISAFTManager;
import com.samsung.android.sdk.accessory.d;
import com.samsung.android.sdk.accessoryfiletransfer.SAFileTransfer;
import com.samsung.android.sdk.accessoryfiletransfer.a;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONException;
/* loaded from: classes2.dex */
public final class b {
    private static b a;
    private static List<String> d = new CopyOnWriteArrayList();
    private static ConcurrentHashMap<String, com.samsung.android.sdk.accessoryfiletransfer.a> e = new ConcurrentHashMap<>();
    private static boolean g = false;
    private a b;
    private Context c;
    private HandlerC0084b f;
    private ServiceConnection h = new ServiceConnection() { // from class: com.samsung.android.sdk.accessoryfiletransfer.b.1
        @Override // android.content.ServiceConnection
        public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            if (iBinder == null) {
                Log.e("[SA_SDK]SAFileTransferManager", "onServiceConnected: File Transfer service not created");
                return;
            }
            Log.i("[SA_SDK]SAFileTransferManager", "inside onServiceConnected mFTServiceConn");
            ISAFTManager asInterface = ISAFTManager.Stub.asInterface(iBinder);
            b bVar = b.this;
            Context unused = b.this.c;
            b.this.c.getPackageName();
            bVar.b = new a(asInterface);
            HandlerThread handlerThread = new HandlerThread("FileUpdateReceiverThread");
            handlerThread.start();
            if (handlerThread.getLooper() != null) {
                b.this.f = new HandlerC0084b(handlerThread.getLooper());
            }
            if (b.a != null) {
                synchronized (b.a) {
                    boolean unused2 = b.g = true;
                    b.a.notifyAll();
                    Log.i("[SA_SDK]SAFileTransferManager", "onServiceConnected: File Transfer service connected");
                }
            }
        }

        @Override // android.content.ServiceConnection
        public final void onServiceDisconnected(ComponentName componentName) {
            Log.d("[SA_SDK]SAFileTransferManager", "onServiceDisconnected: File Transfer service disconnected");
            if (b.a != null) {
                b.a.c.unbindService(this);
                b.a.b = null;
                b.c();
            }
            boolean unused = b.g = false;
            if (b.this.f != null) {
                b.this.f.getLooper().quit();
                b.this.f = null;
            }
            for (Map.Entry entry : b.e.entrySet()) {
                com.samsung.android.sdk.accessoryfiletransfer.a aVar = (com.samsung.android.sdk.accessoryfiletransfer.a) entry.getValue();
                if (aVar.e() != null && !aVar.e().isEmpty()) {
                    ArrayList<a.C0083a> arrayList = new ArrayList();
                    for (Map.Entry<Integer, a.C0083a> entry2 : aVar.e().entrySet()) {
                        arrayList.add(entry2.getValue());
                    }
                    for (a.C0083a c0083a : arrayList) {
                        ((com.samsung.android.sdk.accessoryfiletransfer.a) entry.getValue()).b().a(c0083a.a, c0083a.c, 2048);
                    }
                    arrayList.clear();
                }
            }
        }
    };

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static class a {
        private ISAFTManager a;

        a(ISAFTManager iSAFTManager) {
            this.a = iSAFTManager;
        }

        final ISAFTManager a() {
            return this.a;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.samsung.android.sdk.accessoryfiletransfer.b$b  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    public static class HandlerC0084b extends Handler {
        public HandlerC0084b(Looper looper) {
            super(looper);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized b a(Context context, String str) throws IllegalAccessException, d {
        b bVar;
        synchronized (b.class) {
            b bVar2 = a;
            if (bVar2 == null || bVar2.b == null) {
                b bVar3 = new b();
                a = bVar3;
                bVar3.c = context;
                synchronized (bVar3) {
                    Intent intent = new Intent(SAft.FILE_TRANSFER_SERVICE_INTENT);
                    String fileTransferPackageName = new SAft().getFileTransferPackageName(a.c);
                    if (fileTransferPackageName == null) {
                        throw new d(2048, "Package name is null!");
                    }
                    intent.setPackage(fileTransferPackageName);
                    b bVar4 = a;
                    if (bVar4.c.bindService(intent, bVar4.h, 1)) {
                        try {
                            Log.i("[SA_SDK]SAFileTransferManager", "SAFTAdapter: About start waiting");
                            for (int i = 0; i <= 0; i++) {
                                a.wait(WorkRequest.MIN_BACKOFF_MILLIS);
                            }
                        } catch (InterruptedException e2) {
                            e2.printStackTrace();
                        }
                        if (!g) {
                            throw new d(2048, "Timed out trying to bind to FT Service!");
                        }
                        Log.i("[SA_SDK]SAFileTransferManager", "getInstance: Woken up , FTService Connected");
                    } else {
                        Log.e("[SA_SDK]SAFileTransferManager", "getInstance: FTService Connection Failed");
                    }
                }
            }
            if (str == null) {
                throw new IllegalAccessException("Calling agent was cleared from record. Please re-register your service.");
            }
            Log.d("[SA_SDK]SAFileTransferManager", str + " is using FTService");
            bVar = a;
        }
        return bVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(String str, com.samsung.android.sdk.accessoryfiletransfer.a aVar) {
        e.put(str, aVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean a() {
        return g;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static com.samsung.android.sdk.accessoryfiletransfer.a b(String str) {
        return e.get(str);
    }

    static /* synthetic */ b c() {
        a = null;
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean c(String str) {
        if (d.contains(str)) {
            return false;
        }
        d.add(str);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void d(String str) {
        if (a == null) {
            Log.e("[SA_SDK]SAFileTransferManager", "FT already unbound for this package. Please check whether the calling agent was registered");
            return;
        }
        d.remove(str);
        if (!d.isEmpty()) {
            Log.e("[SA_SDK]SAFileTransferManager", "Other applications are still using this FT binding");
            return;
        }
        b bVar = a;
        bVar.c.unbindService(bVar.h);
        a.b = null;
        g = false;
        Log.d("[SA_SDK]SAFileTransferManager", "File transfer service disconnected");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00d9  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00e6  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00e9  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00f8 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int a(android.content.Context r20, java.lang.String r21, com.samsung.android.sdk.accessoryfiletransfer.SAFileTransfer.c r22, com.samsung.android.sdk.accessory.SAPeerAgent r23, java.lang.String r24, java.lang.String r25) {
        com.samsung.accessory.safiletransfer.a.c r0;
        android.os.Bundle r0;
        int r0;
        boolean r4 = false;
        java.lang.String r3 = null;
        java.lang.String r0 = (android.os.Build.VERSION.SDK_INT >= 24 ? r20.createDeviceProtectedStorageContext() : r20).getSharedPreferences("AccessoryPreferences", 0).getString(r21, null);
        if (r0 == null) {
            android.util.Log.w("[SA_SDK]SAFileTransferManager", "Agent id was not found in prefs! Fetching from framework..");
            r0 = "";
        }
        java.lang.String r9 = r0;
        try {
            if (r24 != null) {
                java.io.File r0 = new java.io.File(r25);
                r3 = "[SA_SDK]SAFileTransferManager";
                r0 = new com.samsung.accessory.safiletransfer.a.c(4, new com.samsung.accessory.safiletransfer.a.k(r25, "", r23.getPeerId(), r9, r23.getAccessoryId(), r0.length(), r0.getName(), r24, r20.getPackageName(), r21).a());
            } else {
                r3 = "[SA_SDK]SAFileTransferManager";
                r0 = new com.samsung.accessory.safiletransfer.a.c(1, new com.samsung.accessory.safiletransfer.a.j(r25, "", r23.getPeerId(), r9, r23.getAccessoryId(), r20.getPackageName(), r21).a());
            }
        } catch (org.json.JSONException r0) {
            r0.printStackTrace();
            r0 = null;
        }
        try {
        } catch (android.os.RemoteException r0) {
            r0.printStackTrace();
        } catch (org.json.JSONException r0) {
            r0.printStackTrace();
        }
        if (r19.b != null) {
            if (com.samsung.android.sdk.accessory.k.m()) {
                android.util.Log.d(r3, "new ft version is supported");
                r0 = r19.b.a().sendCommandV2(r0.a().toString(), new com.samsung.android.sdk.accessoryfiletransfer.SAFileTransferCallbackReceiver(r19.f, r22));
            } else {
                android.util.Log.d(r3, "new ft version is not supported");
                r0 = r19.b.a().sendCommand(r0.a().toString());
            }
            if (r0 == null) {
                r4 = r0.getBoolean("STATUS");
                r0 = r0.getInt("ID");
            } else {
                r0 = 0;
            }
            if (r4) {
                return -1;
            }
            if (!com.samsung.android.sdk.accessory.k.m()) {
                a(r22, r0);
            }
            android.util.Log.d(r3, "File Pushed and Callback registered");
            return r0;
        }
        r0 = null;
        if (r0 == null) {
        }
        if (r4) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int a(String str) {
        try {
            com.samsung.accessory.safiletransfer.a.a aVar = new com.samsung.accessory.safiletransfer.a.a(str);
            Bundle bundle = null;
            try {
                c cVar = new c(6, aVar.a());
                a aVar2 = this.b;
                if (aVar2 != null) {
                    bundle = aVar2.a().sendCommand(cVar.a().toString());
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            if (bundle != null) {
                return bundle.getInt("receiveStatus");
            }
            Log.i("[SA_SDK]SAFileTransferManager", "File Transfer Daemon could not queue request");
            return 1;
        } catch (RemoteException e3) {
            e3.printStackTrace();
            return 1;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(int i) {
        try {
            try {
                c cVar = new c(3, new com.samsung.accessory.safiletransfer.a.b(i).a());
                a aVar = this.b;
                if (aVar != null) {
                    aVar.a().sendCommand(cVar.a().toString());
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        } catch (RemoteException e3) {
            e3.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(SAFileTransfer.c cVar, int i, String str, String str2, boolean z) {
        if (z) {
            try {
                if (!a(cVar, i)) {
                    Log.d("[SA_SDK]SAFileTransferManager", "Could not register file event callback. Declining transfer.");
                    cVar.a(i, str, 3);
                    return;
                }
            } catch (RemoteException e2) {
                e2.printStackTrace();
                return;
            }
        }
        try {
            c cVar2 = str2 != null ? new c(5, new i(i, str, str2, z).a()) : new c(2, new h(i, str, z).a());
            a aVar = this.b;
            Bundle sendCommand = aVar != null ? aVar.a().sendCommand(cVar2.a().toString()) : null;
            if (sendCommand != null) {
                Log.i("[SA_SDK]SAFileTransferManager", "receiveStatus:".concat(String.valueOf(sendCommand.getInt("receiveStatus"))));
            } else {
                Log.i("[SA_SDK]SAFileTransferManager", "File Transfer Daemon could not queue request");
            }
        } catch (JSONException e3) {
            e3.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean a(SAFileTransfer.c cVar, int i) {
        if (cVar == null) {
            return false;
        }
        try {
            a aVar = this.b;
            if (aVar != null) {
                return aVar.a().registerCallbackFacilitator(i, new SAFileTransferCallbackReceiver(this.f, cVar));
            }
        } catch (RemoteException e2) {
            e2.printStackTrace();
        }
        return false;
    }
}
