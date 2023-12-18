package com.egeetouch.egeetouch_manager;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import com.samsung.android.sdk.SsdkUnsupportedException;
import com.samsung.android.sdk.accessory.SA;
import com.samsung.android.sdk.accessory.SAAgent;
import com.samsung.android.sdk.accessory.SAPeerAgent;
import com.samsung.android.sdk.accessory.SASocket;
import java.io.IOException;
/* loaded from: classes.dex */
public class ConsumerService extends SAAgent {
    private static final Class<ServiceConnection> SASOCKET_CLASS = ServiceConnection.class;
    private static final String TAG = "HelloAccessory(C)";
    private final IBinder mBinder;
    private ServiceConnection mConnectionHandler;
    Handler mHandler;

    public ConsumerService() {
        super(TAG, SASOCKET_CLASS);
        this.mBinder = new LocalBinder();
        this.mConnectionHandler = null;
        this.mHandler = new Handler();
    }

    @Override // com.samsung.android.sdk.accessory.SAAgent, android.app.Service
    public void onCreate() {
        super.onCreate();
        SA sa = new SA();
        System.out.println("Hello ConsumerService onCreate!!");
        try {
            sa.initialize(this);
        } catch (SsdkUnsupportedException e) {
            if (processUnsupportedException(e)) {
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            stopSelf();
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.mBinder;
    }

    @Override // com.samsung.android.sdk.accessory.SAAgent
    protected void onFindPeerAgentsResponse(SAPeerAgent[] sAPeerAgentArr, int i) {
        if (i != 0 || sAPeerAgentArr == null) {
            return;
        }
        for (SAPeerAgent sAPeerAgent : sAPeerAgentArr) {
            requestServiceConnection(sAPeerAgent);
        }
    }

    @Override // com.samsung.android.sdk.accessory.SAAgent
    protected void onServiceConnectionRequested(SAPeerAgent sAPeerAgent) {
        if (sAPeerAgent != null) {
            acceptServiceConnectionRequest(sAPeerAgent);
        }
    }

    @Override // com.samsung.android.sdk.accessory.SAAgent
    protected void onServiceConnectionResponse(SAPeerAgent sAPeerAgent, SASocket sASocket, int i) {
        if (i != 0) {
            if (i == 1029) {
                updateTextView("Connected");
                return;
            }
            return;
        }
        MainActivity.watch_connected = true;
        this.mConnectionHandler = (ServiceConnection) sASocket;
        updateTextView("Connected");
        sendData(BLEService.selected_lock_state ? "Locking completed" : "Unlocking completed");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.samsung.android.sdk.accessory.SAAgent
    public void onError(SAPeerAgent sAPeerAgent, String str, int i) {
        super.onError(sAPeerAgent, str, i);
    }

    @Override // com.samsung.android.sdk.accessory.SAAgent
    protected void onPeerAgentsUpdated(final SAPeerAgent[] sAPeerAgentArr, final int i) {
        this.mHandler.post(new Runnable() { // from class: com.egeetouch.egeetouch_manager.ConsumerService.1
            @Override // java.lang.Runnable
            public void run() {
                SAPeerAgent[] sAPeerAgentArr2 = sAPeerAgentArr;
            }
        });
    }

    /* loaded from: classes.dex */
    public class ServiceConnection extends SASocket {
        @Override // com.samsung.android.sdk.accessory.SASocket
        public void onError(int i, String str, int i2) {
        }

        public ServiceConnection() {
            super(ServiceConnection.class.getName());
        }

        @Override // com.samsung.android.sdk.accessory.SASocket
        public void onReceive(int i, byte[] bArr) {
            String str = new String(bArr);
            Log.i("Received: ", str);
            if (str.contains("lock")) {
                if (BLEService.selected_lock_state) {
                    MediaPlayer create = MediaPlayer.create(ConsumerService.this, (int) R.raw.on);
                    create.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: com.egeetouch.egeetouch_manager.ConsumerService.ServiceConnection.1
                        @Override // android.media.MediaPlayer.OnCompletionListener
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            mediaPlayer.stop();
                            mediaPlayer.reset();
                            mediaPlayer.release();
                        }
                    });
                    create.start();
                    Log.i("", "Unlocking the lock...");
                    BLEService.selected_lock_state = false;
                    UI_BLE.state = 2;
                    UI_BLE.BLE_UI = 14;
                    BLEService.Ble_Mode = 98;
                } else {
                    MediaPlayer create2 = MediaPlayer.create(ConsumerService.this, (int) R.raw.off);
                    create2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: com.egeetouch.egeetouch_manager.ConsumerService.ServiceConnection.2
                        @Override // android.media.MediaPlayer.OnCompletionListener
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            mediaPlayer.stop();
                            mediaPlayer.reset();
                            mediaPlayer.release();
                        }
                    });
                    create2.start();
                    if (!BLEService.selected_lock_model.equals("GT2002") && !BLEService.selected_lock_model.equals("GT2003")) {
                        Log.i("", "Locking the lock...");
                        BLEService.selected_lock_state = true;
                        UI_BLE.state = 4;
                        UI_BLE.BLE_UI = 14;
                        BLEService.Ble_Mode = 105;
                    }
                }
            }
            ConsumerService.this.addMessage("Received: ", str);
        }

        @Override // com.samsung.android.sdk.accessory.SASocket
        protected void onServiceConnectionLost(int i) {
            ConsumerService.this.updateTextView("Disconnected");
            ConsumerService.this.closeConnection();
        }
    }

    /* loaded from: classes.dex */
    public class LocalBinder extends Binder {
        public LocalBinder() {
        }

        public ConsumerService getService() {
            return ConsumerService.this;
        }
    }

    public void findPeers() {
        findPeerAgents();
    }

    public boolean sendData(String str) {
        System.out.println("Hello sendData:" + str);
        ServiceConnection serviceConnection = this.mConnectionHandler;
        boolean z = false;
        if (serviceConnection != null) {
            try {
                serviceConnection.send(getServiceChannelId(0), str.getBytes());
                z = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
            addMessage("Sent: ", str);
        }
        return z;
    }

    public boolean closeConnection() {
        ServiceConnection serviceConnection = this.mConnectionHandler;
        if (serviceConnection != null) {
            serviceConnection.close();
            this.mConnectionHandler = null;
            return true;
        }
        return false;
    }

    private boolean processUnsupportedException(SsdkUnsupportedException ssdkUnsupportedException) {
        ssdkUnsupportedException.printStackTrace();
        int type = ssdkUnsupportedException.getType();
        if (type == 0 || type == 1) {
            stopSelf();
        } else if (type == 2) {
            Log.e(TAG, "You need to install Samsung Accessory SDK to use this application.");
        } else if (type == 3) {
            Log.e(TAG, "You need to update Samsung Accessory SDK to use this application.");
        } else if (type == 4) {
            Log.e(TAG, "We recommend that you update your Samsung Accessory SDK before using this application.");
            return false;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateTextView(String str) {
        this.mHandler.post(new Runnable() { // from class: com.egeetouch.egeetouch_manager.ConsumerService.2
            @Override // java.lang.Runnable
            public void run() {
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addMessage(String str, String str2) {
        str.concat(str2);
        this.mHandler.post(new Runnable() { // from class: com.egeetouch.egeetouch_manager.ConsumerService.3
            @Override // java.lang.Runnable
            public void run() {
            }
        });
    }
}
