package com.samsung.android.sdk.accessory;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import androidx.room.RoomDatabase;
import com.samsung.android.sdk.accessory.SAAgentV2;
/* loaded from: classes2.dex */
public class SAService extends Service {
    private static final Object a = new Object();
    private static boolean b = false;
    private static int c;
    private Notification d;
    private b e = new b();

    /* loaded from: classes2.dex */
    static class a implements SAAgentV2.RequestAgentCallback {
        private int a;
        private Intent b;
        private SAService c;

        public a(int i, Intent intent, SAService sAService) {
            this.a = i;
            this.b = intent;
            this.c = sAService;
        }

        @Override // com.samsung.android.sdk.accessory.SAAgentV2.RequestAgentCallback
        public final void onAgentAvailable(SAAgentV2 sAAgentV2) {
            SAService.a(this.c, this.a, sAAgentV2, this.b);
        }

        @Override // com.samsung.android.sdk.accessory.SAAgentV2.RequestAgentCallback
        public final void onError(int i, String str) {
            Log.e("[SA_SDK]SAService", "Agent initialization error: " + i + ". ErrorMsg: " + str);
            this.c.a();
        }
    }

    /* loaded from: classes2.dex */
    class b extends Binder {
        b() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        synchronized (a) {
            StringBuilder sb = new StringBuilder("Finished. Remained request : ");
            int i = c - 1;
            c = i;
            Log.d("[SA_SDK]SAService", sb.append(i).toString());
            if (c <= 0 && b) {
                stopForeground(true);
            }
        }
    }

    static /* synthetic */ void a(SAService sAService, int i, SAAgentV2 sAAgentV2, Intent intent) {
        if (i == 1) {
            sAAgentV2.a(intent);
        } else if (i == 2) {
            sAAgentV2.a();
        }
        sAService.a();
    }

    private void a(String str, a aVar) {
        SAAgentV2.requestAgent(getApplicationContext(), str, aVar);
    }

    private void b() {
        if (this.d == null) {
            NotificationManager notificationManager = (NotificationManager) getSystemService("notification");
            if (notificationManager != null && notificationManager.getNotificationChannel("ACCESSORY_SDK_CHANNEL_ID") == null) {
                notificationManager.createNotificationChannel(new NotificationChannel("ACCESSORY_SDK_CHANNEL_ID", "ACCESSORY_SDK", 2));
            }
            this.d = new Notification.Builder(getBaseContext(), "ACCESSORY_SDK_CHANNEL_ID").setChannelId("ACCESSORY_SDK_CHANNEL_ID").build();
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.e;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        try {
            boolean z = Build.VERSION.SDK_INT >= 26 && getPackageManager().getPackageInfo(getPackageName(), 0).applicationInfo.targetSdkVersion >= 26;
            b = z;
            if (z) {
                b();
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        synchronized (a) {
            c = 0;
        }
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // android.app.Service, android.content.ComponentCallbacks
    public void onLowMemory() {
        SAAgentV2.b();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        String action;
        String stringExtra;
        a aVar;
        if (intent != null && (action = intent.getAction()) != null) {
            if (b) {
                m.a(getApplicationContext()).a();
                b();
                startForeground(RoomDatabase.MAX_BIND_PARAMETER_CNT, this.d);
            }
            if ("com.samsung.accessory.action.SERVICE_CONNECTION_REQUESTED".equalsIgnoreCase(action)) {
                synchronized (a) {
                    StringBuilder sb = new StringBuilder("Received incoming connection indication : ");
                    int i3 = c + 1;
                    c = i3;
                    Log.d("[SA_SDK]SAService", sb.append(i3).toString());
                }
                stringExtra = intent.getStringExtra("agentImplclass");
                aVar = new a(1, intent, this);
            } else if (SAMessage.ACTION_ACCESSORY_MESSAGE_RECEIVED.equalsIgnoreCase(action)) {
                synchronized (a) {
                    StringBuilder sb2 = new StringBuilder("Received message received indication : ");
                    int i4 = c + 1;
                    c = i4;
                    Log.d("[SA_SDK]SAService", sb2.append(i4).toString());
                }
                stringExtra = intent.getStringExtra("agentImplclass");
                aVar = new a(2, intent, this);
            }
            a(stringExtra, aVar);
        }
        return 2;
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        stopSelf();
        return super.onUnbind(intent);
    }
}
