package no.nordicsemi.android.dfu;

import android.app.Activity;
import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.SystemClock;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import androidx.core.internal.view.SupportMenu;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import no.nordicsemi.android.dfu.DfuProgressInfo;
import no.nordicsemi.android.dfu.internal.ArchiveInputStream;
import no.nordicsemi.android.dfu.internal.HexInputStream;
/* loaded from: classes2.dex */
public abstract class DfuBaseService extends IntentService implements DfuProgressInfo.ProgressListener {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int ACTION_ABORT = 2;
    public static final int ACTION_PAUSE = 0;
    public static final int ACTION_RESUME = 1;
    public static final String BROADCAST_ACTION = "no.nordicsemi.android.dfu.broadcast.BROADCAST_ACTION";
    public static final String BROADCAST_ERROR = "no.nordicsemi.android.dfu.broadcast.BROADCAST_ERROR";
    public static final String BROADCAST_LOG = "no.nordicsemi.android.dfu.broadcast.BROADCAST_LOG";
    public static final String BROADCAST_PROGRESS = "no.nordicsemi.android.dfu.broadcast.BROADCAST_PROGRESS";
    static boolean DEBUG = false;
    public static final int ERROR_BLUETOOTH_DISABLED = 4106;
    public static final int ERROR_CONNECTION_MASK = 16384;
    public static final int ERROR_CONNECTION_STATE_MASK = 32768;
    public static final int ERROR_CRC_ERROR = 4109;
    public static final int ERROR_DEVICE_DISCONNECTED = 4096;
    public static final int ERROR_DEVICE_NOT_BONDED = 4110;
    public static final int ERROR_FILE_ERROR = 4098;
    public static final int ERROR_FILE_INVALID = 4099;
    public static final int ERROR_FILE_IO_EXCEPTION = 4100;
    public static final int ERROR_FILE_NOT_FOUND = 4097;
    public static final int ERROR_FILE_SIZE_INVALID = 4108;
    public static final int ERROR_FILE_TYPE_UNSUPPORTED = 4105;
    public static final int ERROR_INIT_PACKET_REQUIRED = 4107;
    public static final int ERROR_INVALID_RESPONSE = 4104;
    public static final int ERROR_MASK = 4096;
    public static final int ERROR_PROGRESS_LOST = 4111;
    public static final int ERROR_REMOTE_MASK = 8192;
    public static final int ERROR_REMOTE_TYPE_LEGACY = 256;
    public static final int ERROR_REMOTE_TYPE_SECURE = 512;
    public static final int ERROR_REMOTE_TYPE_SECURE_BUTTONLESS = 2048;
    public static final int ERROR_REMOTE_TYPE_SECURE_EXTENDED = 1024;
    public static final int ERROR_SERVICE_DISCOVERY_NOT_STARTED = 4101;
    public static final int ERROR_SERVICE_NOT_FOUND = 4102;
    public static final int ERROR_TYPE_COMMUNICATION = 2;
    public static final int ERROR_TYPE_COMMUNICATION_STATE = 1;
    public static final int ERROR_TYPE_DFU_REMOTE = 3;
    public static final int ERROR_TYPE_OTHER = 0;
    public static final String EXTRA_ACTION = "no.nordicsemi.android.dfu.extra.EXTRA_ACTION";
    public static final String EXTRA_AVG_SPEED_B_PER_MS = "no.nordicsemi.android.dfu.extra.EXTRA_AVG_SPEED_B_PER_MS";
    public static final String EXTRA_CURRENT_MTU = "no.nordicsemi.android.dfu.extra.EXTRA_CURRENT_MTU";
    public static final String EXTRA_CUSTOM_UUIDS_FOR_BUTTONLESS_DFU_WITHOUT_BOND_SHARING = "no.nordicsemi.android.dfu.extra.EXTRA_CUSTOM_UUIDS_FOR_BUTTONLESS_DFU_WITHOUT_BOND_SHARING";
    public static final String EXTRA_CUSTOM_UUIDS_FOR_BUTTONLESS_DFU_WITH_BOND_SHARING = "no.nordicsemi.android.dfu.extra.EXTRA_CUSTOM_UUIDS_FOR_BUTTONLESS_DFU_WITH_BOND_SHARING";
    public static final String EXTRA_CUSTOM_UUIDS_FOR_EXPERIMENTAL_BUTTONLESS_DFU = "no.nordicsemi.android.dfu.extra.EXTRA_CUSTOM_UUIDS_FOR_EXPERIMENTAL_BUTTONLESS_DFU";
    public static final String EXTRA_CUSTOM_UUIDS_FOR_LEGACY_DFU = "no.nordicsemi.android.dfu.extra.EXTRA_CUSTOM_UUIDS_FOR_LEGACY_DFU";
    public static final String EXTRA_CUSTOM_UUIDS_FOR_SECURE_DFU = "no.nordicsemi.android.dfu.extra.EXTRA_CUSTOM_UUIDS_FOR_SECURE_DFU";
    public static final String EXTRA_DATA = "no.nordicsemi.android.dfu.extra.EXTRA_DATA";
    public static final String EXTRA_DATA_OBJECT_DELAY = "no.nordicsemi.android.dfu.extra.EXTRA_DATA_OBJECT_DELAY";
    public static final String EXTRA_DEVICE_ADDRESS = "no.nordicsemi.android.dfu.extra.EXTRA_DEVICE_ADDRESS";
    public static final String EXTRA_DEVICE_NAME = "no.nordicsemi.android.dfu.extra.EXTRA_DEVICE_NAME";
    static final String EXTRA_DFU_ATTEMPT = "no.nordicsemi.android.dfu.extra.EXTRA_DFU_ATTEMPT";
    public static final String EXTRA_DISABLE_NOTIFICATION = "no.nordicsemi.android.dfu.extra.EXTRA_DISABLE_NOTIFICATION";
    public static final String EXTRA_DISABLE_RESUME = "no.nordicsemi.android.dfu.extra.EXTRA_DISABLE_RESUME";
    public static final String EXTRA_ERROR_TYPE = "no.nordicsemi.android.dfu.extra.EXTRA_ERROR_TYPE";
    public static final String EXTRA_FILE_MIME_TYPE = "no.nordicsemi.android.dfu.extra.EXTRA_MIME_TYPE";
    public static final String EXTRA_FILE_PATH = "no.nordicsemi.android.dfu.extra.EXTRA_FILE_PATH";
    public static final String EXTRA_FILE_RES_ID = "no.nordicsemi.android.dfu.extra.EXTRA_FILE_RES_ID";
    public static final String EXTRA_FILE_TYPE = "no.nordicsemi.android.dfu.extra.EXTRA_FILE_TYPE";
    public static final String EXTRA_FILE_URI = "no.nordicsemi.android.dfu.extra.EXTRA_FILE_URI";
    public static final String EXTRA_FORCE_DFU = "no.nordicsemi.android.dfu.extra.EXTRA_FORCE_DFU";
    public static final String EXTRA_FORCE_SCANNING_FOR_BOOTLOADER_IN_LEGACY_DFU = "no.nordicsemi.android.dfu.extra.EXTRA_FORCE_SCANNING_FOR_BOOTLOADER_IN_LEGACY_DFU";
    public static final String EXTRA_FOREGROUND_SERVICE = "no.nordicsemi.android.dfu.extra.EXTRA_FOREGROUND_SERVICE";
    public static final String EXTRA_INIT_FILE_PATH = "no.nordicsemi.android.dfu.extra.EXTRA_INIT_FILE_PATH";
    public static final String EXTRA_INIT_FILE_RES_ID = "no.nordicsemi.android.dfu.extra.EXTRA_INIT_FILE_RES_ID";
    public static final String EXTRA_INIT_FILE_URI = "no.nordicsemi.android.dfu.extra.EXTRA_INIT_FILE_URI";
    public static final String EXTRA_KEEP_BOND = "no.nordicsemi.android.dfu.extra.EXTRA_KEEP_BOND";
    public static final String EXTRA_LOG_LEVEL = "no.nordicsemi.android.dfu.extra.EXTRA_LOG_LEVEL";
    public static final String EXTRA_LOG_MESSAGE = "no.nordicsemi.android.dfu.extra.EXTRA_LOG_INFO";
    public static final String EXTRA_MAX_DFU_ATTEMPTS = "no.nordicsemi.android.dfu.extra.EXTRA_MAX_DFU_ATTEMPTS";
    public static final String EXTRA_MBR_SIZE = "no.nordicsemi.android.dfu.extra.EXTRA_MBR_SIZE";
    public static final String EXTRA_MTU = "no.nordicsemi.android.dfu.extra.EXTRA_MTU";
    public static final String EXTRA_PACKET_RECEIPT_NOTIFICATIONS_ENABLED = "no.nordicsemi.android.dfu.extra.EXTRA_PRN_ENABLED";
    public static final String EXTRA_PACKET_RECEIPT_NOTIFICATIONS_VALUE = "no.nordicsemi.android.dfu.extra.EXTRA_PRN_VALUE";
    public static final String EXTRA_PARTS_TOTAL = "no.nordicsemi.android.dfu.extra.EXTRA_PARTS_TOTAL";
    public static final String EXTRA_PART_CURRENT = "no.nordicsemi.android.dfu.extra.EXTRA_PART_CURRENT";
    public static final String EXTRA_PROGRESS = "no.nordicsemi.android.dfu.extra.EXTRA_PROGRESS";
    private static final String EXTRA_RECONNECTION_ATTEMPT = "no.nordicsemi.android.dfu.extra.EXTRA_RECONNECTION_ATTEMPT";
    public static final String EXTRA_RESTORE_BOND = "no.nordicsemi.android.dfu.extra.EXTRA_RESTORE_BOND";
    public static final String EXTRA_SCAN_DELAY = "no.nordicsemi.android.dfu.extra.EXTRA_SCAN_DELAY";
    public static final String EXTRA_SCAN_TIMEOUT = "no.nordicsemi.android.dfu.extra.EXTRA_SCAN_TIMEOUT";
    public static final String EXTRA_SPEED_B_PER_MS = "no.nordicsemi.android.dfu.extra.EXTRA_SPEED_B_PER_MS";
    public static final String EXTRA_UNSAFE_EXPERIMENTAL_BUTTONLESS_DFU = "no.nordicsemi.android.dfu.extra.EXTRA_UNSAFE_EXPERIMENTAL_BUTTONLESS_DFU";
    public static final int LOG_LEVEL_APPLICATION = 10;
    public static final int LOG_LEVEL_DEBUG = 0;
    public static final int LOG_LEVEL_ERROR = 20;
    public static final int LOG_LEVEL_INFO = 5;
    public static final int LOG_LEVEL_VERBOSE = 1;
    public static final int LOG_LEVEL_WARNING = 15;
    public static final String MIME_TYPE_OCTET_STREAM = "application/octet-stream";
    public static final String MIME_TYPE_ZIP = "application/zip";
    public static final String NOTIFICATION_CHANNEL_DFU = "dfu";
    public static final int NOTIFICATION_ID = 283;
    public static final int PROGRESS_ABORTED = -7;
    public static final int PROGRESS_COMPLETED = -6;
    public static final int PROGRESS_CONNECTING = -1;
    public static final int PROGRESS_DISCONNECTING = -5;
    public static final int PROGRESS_ENABLING_DFU_MODE = -3;
    public static final int PROGRESS_STARTING = -2;
    public static final int PROGRESS_VALIDATING = -4;
    protected static final int STATE_CLOSED = -5;
    protected static final int STATE_CONNECTED = -2;
    protected static final int STATE_CONNECTED_AND_READY = -3;
    protected static final int STATE_CONNECTING = -1;
    protected static final int STATE_DISCONNECTED = 0;
    protected static final int STATE_DISCONNECTING = -4;
    private static final String TAG = "DfuBaseService";
    public static final int TYPE_APPLICATION = 4;
    public static final int TYPE_AUTO = 0;
    public static final int TYPE_BOOTLOADER = 2;
    public static final int TYPE_SOFT_DEVICE = 1;
    private boolean mAborted;
    private BluetoothAdapter mBluetoothAdapter;
    private final BroadcastReceiver mBluetoothStateBroadcastReceiver;
    private final BroadcastReceiver mBondStateBroadcastReceiver;
    protected int mConnectionState;
    private final BroadcastReceiver mConnectionStateBroadcastReceiver;
    private String mDeviceAddress;
    private String mDeviceName;
    private final BroadcastReceiver mDfuActionReceiver;
    private DfuCallback mDfuServiceImpl;
    private boolean mDisableNotification;
    private int mError;
    private InputStream mFirmwareInputStream;
    private final BluetoothGattCallback mGattCallback;
    private InputStream mInitFileInputStream;
    private long mLastNotificationTime;
    private int mLastProgress;
    private final Object mLock;
    DfuProgressInfo mProgressInfo;

    protected abstract Class<? extends Activity> getNotificationTarget();

    protected boolean isDebug() {
        return false;
    }

    protected void updateErrorNotification(NotificationCompat.Builder builder) {
    }

    protected void updateForegroundNotification(NotificationCompat.Builder builder) {
    }

    public DfuBaseService() {
        super(TAG);
        this.mLock = new Object();
        this.mLastProgress = -1;
        this.mDfuActionReceiver = new BroadcastReceiver() { // from class: no.nordicsemi.android.dfu.DfuBaseService.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                int intExtra = intent.getIntExtra(DfuBaseService.EXTRA_ACTION, 0);
                DfuBaseService.this.logi("User action received: " + intExtra);
                if (intExtra == 0) {
                    DfuBaseService.this.sendLogBroadcast(15, "[Broadcast] Pause action received");
                    if (DfuBaseService.this.mDfuServiceImpl != null) {
                        DfuBaseService.this.mDfuServiceImpl.pause();
                    }
                } else if (intExtra == 1) {
                    DfuBaseService.this.sendLogBroadcast(15, "[Broadcast] Resume action received");
                    if (DfuBaseService.this.mDfuServiceImpl != null) {
                        DfuBaseService.this.mDfuServiceImpl.resume();
                    }
                } else if (intExtra != 2) {
                } else {
                    DfuBaseService.this.sendLogBroadcast(15, "[Broadcast] Abort action received");
                    DfuBaseService.this.mAborted = true;
                    if (DfuBaseService.this.mDfuServiceImpl != null) {
                        DfuBaseService.this.mDfuServiceImpl.abort();
                    }
                }
            }
        };
        this.mBluetoothStateBroadcastReceiver = new BroadcastReceiver() { // from class: no.nordicsemi.android.dfu.DfuBaseService.2
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", 10);
                int intExtra2 = intent.getIntExtra("android.bluetooth.adapter.extra.PREVIOUS_STATE", 12);
                DfuBaseService.this.logw("Action received: android.bluetooth.adapter.action.STATE_CHANGED [state: " + intExtra + ", previous state: " + intExtra2 + "]");
                if (intExtra2 == 12) {
                    if (intExtra == 13 || intExtra == 10) {
                        DfuBaseService.this.sendLogBroadcast(15, "Bluetooth adapter disabled");
                        DfuBaseService.this.mConnectionState = 0;
                        if (DfuBaseService.this.mDfuServiceImpl != null) {
                            DfuBaseService.this.mDfuServiceImpl.getGattCallback().onDisconnected();
                        }
                        synchronized (DfuBaseService.this.mLock) {
                            DfuBaseService.this.mLock.notifyAll();
                        }
                    }
                }
            }
        };
        this.mBondStateBroadcastReceiver = new BroadcastReceiver() { // from class: no.nordicsemi.android.dfu.DfuBaseService.3
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                int intExtra;
                BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                if (bluetoothDevice == null || !bluetoothDevice.getAddress().equals(DfuBaseService.this.mDeviceAddress) || (intExtra = intent.getIntExtra("android.bluetooth.device.extra.BOND_STATE", -1)) == 11 || DfuBaseService.this.mDfuServiceImpl == null) {
                    return;
                }
                DfuBaseService.this.mDfuServiceImpl.onBondStateChanged(intExtra);
            }
        };
        this.mConnectionStateBroadcastReceiver = new BroadcastReceiver() { // from class: no.nordicsemi.android.dfu.DfuBaseService.4
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                if (bluetoothDevice == null || !bluetoothDevice.getAddress().equals(DfuBaseService.this.mDeviceAddress)) {
                    return;
                }
                String action = intent.getAction();
                DfuBaseService.this.logi("Action received: " + action);
                DfuBaseService.this.sendLogBroadcast(0, "[Broadcast] Action received: " + action);
            }
        };
        this.mGattCallback = new BluetoothGattCallback() { // from class: no.nordicsemi.android.dfu.DfuBaseService.5
            @Override // android.bluetooth.BluetoothGattCallback
            public void onConnectionStateChange(BluetoothGatt bluetoothGatt, int i, int i2) {
                if (i != 0) {
                    if (i == 8 || i == 19) {
                        DfuBaseService.this.logw("Target device disconnected with status: " + i);
                    } else {
                        DfuBaseService.this.loge("Connection state change error: " + i + " newState: " + i2);
                    }
                    DfuBaseService.this.mError = i | 32768;
                    if (i2 == 0) {
                        DfuBaseService.this.mConnectionState = 0;
                        if (DfuBaseService.this.mDfuServiceImpl != null) {
                            DfuBaseService.this.mDfuServiceImpl.getGattCallback().onDisconnected();
                        }
                    }
                } else if (i2 == 2) {
                    DfuBaseService.this.logi("Connected to GATT server");
                    DfuBaseService.this.sendLogBroadcast(5, "Connected to " + DfuBaseService.this.mDeviceAddress);
                    DfuBaseService.this.mConnectionState = -2;
                    if (bluetoothGatt.getDevice().getBondState() == 12) {
                        DfuBaseService.this.logi("Waiting 1600 ms for a possible Service Changed indication...");
                        DfuBaseService.this.waitFor(1600L);
                    }
                    DfuBaseService.this.sendLogBroadcast(1, "Discovering services...");
                    DfuBaseService.this.sendLogBroadcast(0, "gatt.discoverServices()");
                    boolean discoverServices = bluetoothGatt.discoverServices();
                    DfuBaseService.this.logi("Attempting to start service discovery... " + (discoverServices ? "succeed" : "failed"));
                    if (discoverServices) {
                        return;
                    }
                    DfuBaseService.this.mError = DfuBaseService.ERROR_SERVICE_DISCOVERY_NOT_STARTED;
                } else if (i2 == 0) {
                    DfuBaseService.this.logi("Disconnected from GATT server");
                    DfuBaseService.this.mConnectionState = 0;
                    if (DfuBaseService.this.mDfuServiceImpl != null) {
                        DfuBaseService.this.mDfuServiceImpl.getGattCallback().onDisconnected();
                    }
                }
                synchronized (DfuBaseService.this.mLock) {
                    DfuBaseService.this.mLock.notifyAll();
                }
            }

            @Override // android.bluetooth.BluetoothGattCallback
            public void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i) {
                if (i == 0) {
                    DfuBaseService.this.logi("Services discovered");
                    DfuBaseService.this.mConnectionState = -3;
                } else {
                    DfuBaseService.this.loge("Service discovery error: " + i);
                    DfuBaseService.this.mError = i | 16384;
                }
                synchronized (DfuBaseService.this.mLock) {
                    DfuBaseService.this.mLock.notifyAll();
                }
            }

            @Override // android.bluetooth.BluetoothGattCallback
            public void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
                if (DfuBaseService.this.mDfuServiceImpl != null) {
                    DfuBaseService.this.mDfuServiceImpl.getGattCallback().onCharacteristicWrite(bluetoothGatt, bluetoothGattCharacteristic, i);
                }
            }

            @Override // android.bluetooth.BluetoothGattCallback
            public void onCharacteristicRead(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
                if (DfuBaseService.this.mDfuServiceImpl != null) {
                    DfuBaseService.this.mDfuServiceImpl.getGattCallback().onCharacteristicRead(bluetoothGatt, bluetoothGattCharacteristic, i);
                }
            }

            @Override // android.bluetooth.BluetoothGattCallback
            public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
                if (DfuBaseService.this.mDfuServiceImpl != null) {
                    DfuBaseService.this.mDfuServiceImpl.getGattCallback().onCharacteristicChanged(bluetoothGatt, bluetoothGattCharacteristic);
                }
            }

            @Override // android.bluetooth.BluetoothGattCallback
            public void onDescriptorWrite(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
                if (DfuBaseService.this.mDfuServiceImpl != null) {
                    DfuBaseService.this.mDfuServiceImpl.getGattCallback().onDescriptorWrite(bluetoothGatt, bluetoothGattDescriptor, i);
                }
            }

            @Override // android.bluetooth.BluetoothGattCallback
            public void onDescriptorRead(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
                if (DfuBaseService.this.mDfuServiceImpl != null) {
                    DfuBaseService.this.mDfuServiceImpl.getGattCallback().onDescriptorRead(bluetoothGatt, bluetoothGattDescriptor, i);
                }
            }

            @Override // android.bluetooth.BluetoothGattCallback
            public void onMtuChanged(BluetoothGatt bluetoothGatt, int i, int i2) {
                if (DfuBaseService.this.mDfuServiceImpl != null) {
                    DfuBaseService.this.mDfuServiceImpl.getGattCallback().onMtuChanged(bluetoothGatt, i, i2);
                }
            }

            @Override // android.bluetooth.BluetoothGattCallback
            public void onPhyUpdate(BluetoothGatt bluetoothGatt, int i, int i2, int i3) {
                if (DfuBaseService.this.mDfuServiceImpl != null) {
                    DfuBaseService.this.mDfuServiceImpl.getGattCallback().onPhyUpdate(bluetoothGatt, i, i2, i3);
                }
            }
        };
    }

    private static IntentFilter makeDfuActionIntentFilter() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BROADCAST_ACTION);
        return intentFilter;
    }

    @Override // android.app.IntentService, android.app.Service
    public void onCreate() {
        super.onCreate();
        DEBUG = isDebug();
        logi("DFU service created. Version: 2.3.0");
        initialize();
        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(this);
        IntentFilter makeDfuActionIntentFilter = makeDfuActionIntentFilter();
        localBroadcastManager.registerReceiver(this.mDfuActionReceiver, makeDfuActionIntentFilter);
        registerReceiver(this.mDfuActionReceiver, makeDfuActionIntentFilter);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.bluetooth.device.action.ACL_CONNECTED");
        intentFilter.addAction("android.bluetooth.device.action.ACL_DISCONNECT_REQUESTED");
        intentFilter.addAction("android.bluetooth.device.action.ACL_DISCONNECTED");
        registerReceiver(this.mConnectionStateBroadcastReceiver, intentFilter);
        registerReceiver(this.mBondStateBroadcastReceiver, new IntentFilter("android.bluetooth.device.action.BOND_STATE_CHANGED"));
        registerReceiver(this.mBluetoothStateBroadcastReceiver, new IntentFilter("android.bluetooth.adapter.action.STATE_CHANGED"));
    }

    @Override // android.app.Service
    public void onTaskRemoved(Intent intent) {
        super.onTaskRemoved(intent);
        NotificationManager notificationManager = (NotificationManager) getSystemService("notification");
        if (notificationManager != null) {
            notificationManager.cancel(NOTIFICATION_ID);
        }
        stopSelf();
    }

    @Override // android.app.IntentService, android.app.Service
    public void onDestroy() {
        super.onDestroy();
        DfuCallback dfuCallback = this.mDfuServiceImpl;
        if (dfuCallback != null) {
            dfuCallback.abort();
        }
        LocalBroadcastManager.getInstance(this).unregisterReceiver(this.mDfuActionReceiver);
        unregisterReceiver(this.mDfuActionReceiver);
        unregisterReceiver(this.mConnectionStateBroadcastReceiver);
        unregisterReceiver(this.mBondStateBroadcastReceiver);
        unregisterReceiver(this.mBluetoothStateBroadcastReceiver);
        try {
            InputStream inputStream = this.mFirmwareInputStream;
            if (inputStream != null) {
                inputStream.close();
            }
            InputStream inputStream2 = this.mInitFileInputStream;
            if (inputStream2 != null) {
                inputStream2.close();
            }
        } catch (IOException unused) {
        } catch (Throwable th) {
            this.mFirmwareInputStream = null;
            this.mInitFileInputStream = null;
            throw th;
        }
        this.mFirmwareInputStream = null;
        this.mInitFileInputStream = null;
        logi("DFU service destroyed");
    }

    /* JADX WARN: Code restructure failed: missing block: B:245:0x04b9, code lost:
        if (r2 == null) goto L188;
     */
    /* JADX WARN: Code restructure failed: missing block: B:263:0x053f, code lost:
        if (r2 == null) goto L188;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x0105, code lost:
        if (r4 < 0) goto L311;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x0110, code lost:
        if (r4 < 0) goto L311;
     */
    /* JADX WARN: Removed duplicated region for block: B:137:0x0219 A[Catch: all -> 0x017f, Exception -> 0x0183, IOException -> 0x0187, SizeValidationException -> 0x018b, FileNotFoundException -> 0x018f, SecurityException -> 0x0193, TryCatch #15 {FileNotFoundException -> 0x018f, SizeValidationException -> 0x018b, blocks: (B:64:0x012a, B:66:0x0134, B:73:0x014b, B:79:0x016d, B:97:0x019b, B:99:0x01a1, B:101:0x01a6, B:103:0x01af, B:105:0x01b3, B:108:0x01bc, B:109:0x01c3, B:110:0x01c4, B:112:0x01c8, B:115:0x01d1, B:116:0x01d8, B:117:0x01d9, B:119:0x01dd, B:122:0x01e6, B:123:0x01ed, B:126:0x01f1, B:128:0x01f7, B:137:0x0219, B:139:0x0222, B:140:0x0229, B:129:0x0201, B:131:0x0207, B:102:0x01ab, B:82:0x0177, B:83:0x017e, B:75:0x0156, B:77:0x0160, B:68:0x013b, B:70:0x0142), top: B:313:0x012a, outer: #18 }] */
    /* JADX WARN: Removed duplicated region for block: B:144:0x0241 A[Catch: all -> 0x017f, TRY_ENTER, TRY_LEAVE, TryCatch #18 {all -> 0x017f, blocks: (B:64:0x012a, B:66:0x0134, B:73:0x014b, B:79:0x016d, B:97:0x019b, B:99:0x01a1, B:101:0x01a6, B:103:0x01af, B:105:0x01b3, B:108:0x01bc, B:109:0x01c3, B:110:0x01c4, B:112:0x01c8, B:115:0x01d1, B:116:0x01d8, B:117:0x01d9, B:119:0x01dd, B:122:0x01e6, B:123:0x01ed, B:126:0x01f1, B:128:0x01f7, B:137:0x0219, B:139:0x0222, B:140:0x0229, B:141:0x0233, B:144:0x0241, B:148:0x0254, B:150:0x0270, B:154:0x0285, B:160:0x0296, B:187:0x03b1, B:193:0x03c1, B:195:0x03c5, B:200:0x03d9, B:202:0x03dd, B:206:0x03f4, B:213:0x0423, B:224:0x0441, B:255:0x051f, B:246:0x04bb, B:269:0x054b, B:270:0x054e, B:129:0x0201, B:131:0x0207, B:102:0x01ab, B:82:0x0177, B:83:0x017e, B:75:0x0156, B:77:0x0160, B:68:0x013b, B:70:0x0142, B:271:0x054f, B:275:0x0579, B:279:0x05a3, B:283:0x05ba, B:287:0x05d1), top: B:312:0x0128, inners: #15, #13 }] */
    /* JADX WARN: Removed duplicated region for block: B:148:0x0254 A[Catch: all -> 0x017f, TRY_ENTER, TryCatch #18 {all -> 0x017f, blocks: (B:64:0x012a, B:66:0x0134, B:73:0x014b, B:79:0x016d, B:97:0x019b, B:99:0x01a1, B:101:0x01a6, B:103:0x01af, B:105:0x01b3, B:108:0x01bc, B:109:0x01c3, B:110:0x01c4, B:112:0x01c8, B:115:0x01d1, B:116:0x01d8, B:117:0x01d9, B:119:0x01dd, B:122:0x01e6, B:123:0x01ed, B:126:0x01f1, B:128:0x01f7, B:137:0x0219, B:139:0x0222, B:140:0x0229, B:141:0x0233, B:144:0x0241, B:148:0x0254, B:150:0x0270, B:154:0x0285, B:160:0x0296, B:187:0x03b1, B:193:0x03c1, B:195:0x03c5, B:200:0x03d9, B:202:0x03dd, B:206:0x03f4, B:213:0x0423, B:224:0x0441, B:255:0x051f, B:246:0x04bb, B:269:0x054b, B:270:0x054e, B:129:0x0201, B:131:0x0207, B:102:0x01ab, B:82:0x0177, B:83:0x017e, B:75:0x0156, B:77:0x0160, B:68:0x013b, B:70:0x0142, B:271:0x054f, B:275:0x0579, B:279:0x05a3, B:283:0x05ba, B:287:0x05d1), top: B:312:0x0128, inners: #15, #13 }] */
    /* JADX WARN: Removed duplicated region for block: B:253:0x04e3 A[Catch: all -> 0x045a, TRY_LEAVE, TryCatch #13 {all -> 0x045a, blocks: (B:208:0x03ff, B:251:0x04c3, B:253:0x04e3, B:259:0x0528, B:240:0x0460, B:242:0x0468, B:244:0x04ab, B:243:0x048b, B:262:0x052e), top: B:307:0x03ff }] */
    /* JADX WARN: Removed duplicated region for block: B:259:0x0528 A[Catch: all -> 0x045a, TRY_ENTER, TryCatch #13 {all -> 0x045a, blocks: (B:208:0x03ff, B:251:0x04c3, B:253:0x04e3, B:259:0x0528, B:240:0x0460, B:242:0x0468, B:244:0x04ab, B:243:0x048b, B:262:0x052e), top: B:307:0x03ff }] */
    /* JADX WARN: Removed duplicated region for block: B:266:0x0545  */
    /* JADX WARN: Removed duplicated region for block: B:292:0x05ea  */
    /* JADX WARN: Removed duplicated region for block: B:323:? A[RETURN, SYNTHETIC] */
    @Override // android.app.IntentService
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void onHandleIntent(android.content.Intent r26) {
        int r4;
        java.lang.Throwable r2;
        java.io.InputStream r6;
        int r5;
        java.io.InputStream r7;
        no.nordicsemi.android.dfu.internal.exception.DfuException r3;
        int r15;
        java.lang.Throwable r3;
        no.nordicsemi.android.dfu.internal.exception.DeviceDisconnectedException r3;
        int r3;
        int r4;
        no.nordicsemi.android.dfu.DfuService r13;
        boolean r18;
        java.io.InputStream r5;
        if (r26 == null) {
            return;
        }
        java.lang.String r3 = r26.getStringExtra(no.nordicsemi.android.dfu.DfuBaseService.EXTRA_DEVICE_ADDRESS);
        java.lang.String r4 = r26.getStringExtra(no.nordicsemi.android.dfu.DfuBaseService.EXTRA_DEVICE_NAME);
        boolean r10 = r26.getBooleanExtra(no.nordicsemi.android.dfu.DfuBaseService.EXTRA_DISABLE_NOTIFICATION, false);
        boolean r12 = r26.getBooleanExtra(no.nordicsemi.android.dfu.DfuBaseService.EXTRA_FOREGROUND_SERVICE, true);
        java.lang.String r5 = r26.getStringExtra(no.nordicsemi.android.dfu.DfuBaseService.EXTRA_FILE_PATH);
        android.net.Uri r6 = (android.net.Uri) r26.getParcelableExtra(no.nordicsemi.android.dfu.DfuBaseService.EXTRA_FILE_URI);
        int r7 = r26.getIntExtra(no.nordicsemi.android.dfu.DfuBaseService.EXTRA_FILE_RES_ID, 0);
        java.lang.String r13 = r26.getStringExtra(no.nordicsemi.android.dfu.DfuBaseService.EXTRA_INIT_FILE_PATH);
        android.net.Uri r14 = (android.net.Uri) r26.getParcelableExtra(no.nordicsemi.android.dfu.DfuBaseService.EXTRA_INIT_FILE_URI);
        int r15 = r26.getIntExtra(no.nordicsemi.android.dfu.DfuBaseService.EXTRA_INIT_FILE_RES_ID, 0);
        int r11 = r26.getIntExtra(no.nordicsemi.android.dfu.DfuBaseService.EXTRA_FILE_TYPE, 0);
        if (r5 != null && r11 == 0) {
            r11 = r5.toLowerCase(java.util.Locale.US).endsWith("zip") ? 0 : 4;
        }
        java.lang.String r9 = r26.getStringExtra(no.nordicsemi.android.dfu.DfuBaseService.EXTRA_FILE_MIME_TYPE);
        if (r9 == null) {
            r9 = r11 == 0 ? no.nordicsemi.android.dfu.DfuBaseService.MIME_TYPE_ZIP : no.nordicsemi.android.dfu.DfuBaseService.MIME_TYPE_OCTET_STREAM;
        }
        if (r3 == null || (r5 == null && r6 == null && r7 == 0)) {
            loge("Device Address of firmware location are empty. Hint: use DfuServiceInitiator to start DFU");
        } else if ((r11 & (-8)) > 0 || !(no.nordicsemi.android.dfu.DfuBaseService.MIME_TYPE_ZIP.equals(r9) || no.nordicsemi.android.dfu.DfuBaseService.MIME_TYPE_OCTET_STREAM.equals(r9))) {
            logw("File type or file mime-type not supported");
            sendLogBroadcast(15, "File type or file mime-type not supported");
            report(no.nordicsemi.android.dfu.DfuBaseService.ERROR_FILE_TYPE_UNSUPPORTED);
        } else if (no.nordicsemi.android.dfu.DfuBaseService.MIME_TYPE_OCTET_STREAM.equals(r9) && r11 != 1 && r11 != 2 && r11 != 4) {
            logw("Unable to determine file type");
            sendLogBroadcast(15, "Unable to determine file type");
            report(no.nordicsemi.android.dfu.DfuBaseService.ERROR_FILE_TYPE_UNSUPPORTED);
        } else {
            if (!r10) {
                java.util.Objects.requireNonNull(getNotificationTarget(), "getNotificationTarget() must not return null if notifications are enabled");
            }
            if (!r12 && android.os.Build.VERSION.SDK_INT >= 26) {
                logw("Foreground service disabled. Android Oreo or newer may kill a background service few moments after user closes the application.\nConsider enabling foreground service using DfuServiceInitiator#setForeground(boolean)");
            }
            no.nordicsemi.android.dfu.UuidHelper.assignCustomUuids(r26);
            if (r12) {
                logi("Starting DFU service in foreground");
                startForeground();
            }
            r25.mDeviceAddress = r3;
            r25.mDeviceName = r4;
            r25.mDisableNotification = r10;
            r25.mConnectionState = 0;
            r25.mError = 0;
            android.content.SharedPreferences r4 = android.preference.PreferenceManager.getDefaultSharedPreferences(r25);
            if (r4.contains(no.nordicsemi.android.dfu.DfuSettingsConstants.SETTINGS_MBR_SIZE)) {
                try {
                    r4 = java.lang.Integer.parseInt(r4.getString(no.nordicsemi.android.dfu.DfuSettingsConstants.SETTINGS_MBR_SIZE, java.lang.String.valueOf(4096)));
                } catch (java.lang.NumberFormatException unused) {
                    r4 = 4096;
                }
            } else {
                r4 = r26.getIntExtra(no.nordicsemi.android.dfu.DfuBaseService.EXTRA_MBR_SIZE, 4096);
            }
            r4 = 0;
            sendLogBroadcast(1, "DFU service started");
            java.io.InputStream r13 = r25.mFirmwareInputStream;
            java.io.InputStream r15 = r25.mInitFileInputStream;
            boolean r22 = r13 == null;
            try {
                if (r22) {
                    try {
                        try {
                            sendLogBroadcast(1, "Opening file...");
                            java.io.InputStream r13 = r6 != null ? openInputStream(r6, r9, r4, r11) : r5 != null ? openInputStream(r5, r9, r4, r11) : r7 > 0 ? openInputStream(r7, r9, r4, r11) : r13;
                            r15 = r14 != null ? getContentResolver().openInputStream(r14) : r13 != null ? new java.io.FileInputStream(r13) : r15 > 0 ? getResources().openRawResource(r15) : r15;
                            if (r13.available() % 4 != 0) {
                                throw new no.nordicsemi.android.dfu.internal.exception.SizeValidationException("The new firmware is not word-aligned.");
                            }
                            r6 = r13;
                        } catch (java.io.FileNotFoundException r0) {
                            loge("An exception occurred while opening file", r0);
                            sendLogBroadcast(20, "Opening file failed: File not found");
                            report(4097);
                            if (r12) {
                                stopForeground(r10);
                                return;
                            }
                            return;
                        } catch (no.nordicsemi.android.dfu.internal.exception.SizeValidationException r0) {
                            loge("Firmware not word-aligned", r0);
                            sendLogBroadcast(20, "Opening file failed: Firmware size must be word-aligned");
                            report(no.nordicsemi.android.dfu.DfuBaseService.ERROR_FILE_SIZE_INVALID);
                            if (r12) {
                                stopForeground(r10);
                                return;
                            }
                            return;
                        }
                    } catch (java.io.IOException r0) {
                        loge("An exception occurred while calculating file size", r0);
                        sendLogBroadcast(20, "Opening file failed: " + r0.getLocalizedMessage());
                        report(4098);
                        if (r12) {
                            stopForeground(r10);
                            return;
                        }
                        return;
                    } catch (java.lang.SecurityException r0) {
                        loge("A security exception occurred while opening file", r0);
                        sendLogBroadcast(20, "Opening file failed: Permission required");
                        report(4097);
                        if (r12) {
                            stopForeground(r10);
                            return;
                        }
                        return;
                    } catch (java.lang.Exception r0) {
                        loge("An exception occurred while opening files. Did you set the firmware file?", r0);
                        sendLogBroadcast(20, "Opening file failed: " + r0.getLocalizedMessage());
                        report(4098);
                        if (r12) {
                            stopForeground(r10);
                            return;
                        }
                        return;
                    }
                } else {
                    r6 = r13;
                }
                if (no.nordicsemi.android.dfu.DfuBaseService.MIME_TYPE_ZIP.equals(r9)) {
                    no.nordicsemi.android.dfu.internal.ArchiveInputStream r2 = (no.nordicsemi.android.dfu.internal.ArchiveInputStream) r6;
                    int r4 = r11 == 0 ? r2.getContentType() : r2.setContentType(r11);
                    if ((r4 & 4) > 0 && r2.applicationImageSize() % 4 != 0) {
                        throw new no.nordicsemi.android.dfu.internal.exception.SizeValidationException("Application firmware is not word-aligned.");
                    }
                    if ((r4 & 2) > 0 && r2.bootloaderImageSize() % 4 != 0) {
                        throw new no.nordicsemi.android.dfu.internal.exception.SizeValidationException("Bootloader firmware is not word-aligned.");
                    }
                    if ((r4 & 1) > 0 && r2.softDeviceImageSize() % 4 != 0) {
                        throw new no.nordicsemi.android.dfu.internal.exception.SizeValidationException("Soft Device firmware is not word-aligned.");
                    }
                    if (r4 == 4) {
                        if (r2.getApplicationInit() != null) {
                            r5 = new java.io.ByteArrayInputStream(r2.getApplicationInit());
                            r7 = r5;
                            r5 = r4;
                        }
                        r5 = r4;
                    } else {
                        if (r2.getSystemInit() != null) {
                            r5 = new java.io.ByteArrayInputStream(r2.getSystemInit());
                            r7 = r5;
                            r5 = r4;
                        }
                        r5 = r4;
                    }
                    if (r22) {
                        r6.mark(r6.available());
                        if (r7 != null) {
                            r7.mark(r7.available());
                        }
                    }
                    r25.mFirmwareInputStream = r6;
                    r25.mInitFileInputStream = r7;
                    sendLogBroadcast(5, "Firmware file opened successfully");
                    r25.mProgressInfo = new no.nordicsemi.android.dfu.DfuProgressInfo(r25);
                    if (!r25.mAborted) {
                        logw("Upload aborted");
                        sendLogBroadcast(15, "Upload aborted");
                        r25.mProgressInfo.setProgress(-7);
                        if (r12) {
                            stopForeground(r10);
                            return;
                        }
                        return;
                    }
                    sendLogBroadcast(1, "Connecting to DFU target...");
                    r25.mProgressInfo.setProgress(-1);
                    long r2 = android.os.SystemClock.elapsedRealtime();
                    android.bluetooth.BluetoothGatt r14 = connect(r3);
                    long r21 = android.os.SystemClock.elapsedRealtime();
                    if (r14 == null) {
                        loge("Bluetooth adapter disabled");
                        sendLogBroadcast(20, "Bluetooth adapter disabled");
                        report(no.nordicsemi.android.dfu.DfuBaseService.ERROR_BLUETOOTH_DISABLED);
                        if (r12) {
                            stopForeground(r10);
                            return;
                        }
                        return;
                    }
                    int r15 = r25.mError;
                    if (r15 > 0) {
                        if ((r15 & 32768) > 0) {
                            int r5 = (-32769) & r15;
                            r18 = r10;
                            try {
                                logi("Connection error after: " + (r21 - r2) + " ms");
                                if (r5 == 133 && r21 > r2 + 25000) {
                                    loge("Device not reachable. Check if the device with address " + r3 + " is in range, is advertising and is connectable");
                                    sendLogBroadcast(20, "Error 133: Connection timeout");
                                } else {
                                    loge("An error occurred while connecting to the device: " + r5);
                                    sendLogBroadcast(20, java.lang.String.format(java.util.Locale.US, "Connection failed (0x%02X): %s", java.lang.Integer.valueOf(r5), no.nordicsemi.android.error.GattError.parseConnectionError(r5)));
                                }
                            } catch (java.lang.Throwable r0) {
                                r2 = r0;
                                r10 = r18;
                                if (r12) {
                                }
                                throw r2;
                            }
                        } else {
                            r18 = r10;
                            int r2 = r15 & (-16385);
                            try {
                                loge("An error occurred during discovering services:" + r2);
                                sendLogBroadcast(20, java.lang.String.format(java.util.Locale.US, "Connection failed (0x%02X): %s", java.lang.Integer.valueOf(r2), no.nordicsemi.android.error.GattError.parse(r2)));
                            } catch (java.lang.Throwable th) {
                                r0 = th;
                                r10 = r18;
                                r2 = r0;
                                if (r12) {
                                    stopForeground(r10);
                                }
                                throw r2;
                            }
                        }
                        int r2 = r26.getIntExtra(no.nordicsemi.android.dfu.DfuBaseService.EXTRA_RECONNECTION_ATTEMPT, 0);
                        int r5 = r2 + 1;
                        logi("Attempt: " + r5);
                        if (r2 >= 2) {
                            boolean r10 = r18;
                            terminateConnection(r14, r25.mError);
                            if (r12) {
                                stopForeground(r10);
                                return;
                            }
                            return;
                        }
                        sendLogBroadcast(15, "Retrying...");
                        if (r25.mConnectionState != 0) {
                            disconnect(r14);
                        }
                        refreshDeviceCache(r14, true);
                        close(r14);
                        logi("Restarting the service");
                        android.content.Intent r2 = new android.content.Intent();
                        r2.fillIn(r26, 24);
                        r2.putExtra(no.nordicsemi.android.dfu.DfuBaseService.EXTRA_RECONNECTION_ATTEMPT, r5);
                        startService(r2);
                        if (r12) {
                            stopForeground(r18);
                            return;
                        }
                        return;
                    } else if (r25.mConnectionState == 0) {
                        sendLogBroadcast(20, "Disconnected");
                        terminateConnection(r14, 4096);
                        if (r12) {
                            stopForeground(r10);
                            return;
                        }
                        return;
                    } else if (r25.mAborted) {
                        logw("Upload aborted");
                        sendLogBroadcast(15, "Upload aborted");
                        terminateConnection(r14, 0);
                        r25.mProgressInfo.setProgress(-7);
                        if (r12) {
                            stopForeground(r10);
                            return;
                        }
                        return;
                    } else {
                        sendLogBroadcast(5, "Services discovered");
                        r26.putExtra(no.nordicsemi.android.dfu.DfuBaseService.EXTRA_RECONNECTION_ATTEMPT, 0);
                        no.nordicsemi.android.dfu.DfuService r2 = null;
                        try {
                            try {
                                no.nordicsemi.android.dfu.DfuServiceProvider r4 = new no.nordicsemi.android.dfu.DfuServiceProvider();
                                r25.mDfuServiceImpl = r4;
                                r13 = r4.getServiceImpl(r26, r25, r14);
                            } catch (java.lang.Throwable r0) {
                                r3 = r0;
                            }
                            try {
                                try {
                                    r25.mDfuServiceImpl = r13;
                                } catch (no.nordicsemi.android.dfu.internal.exception.DeviceDisconnectedException e) {
                                    r0 = e;
                                    r15 = 4096;
                                }
                            } catch (no.nordicsemi.android.dfu.internal.exception.DfuException r0) {
                                r3 = r0;
                                r2 = r13;
                                int r4 = r3.getErrorNumber();
                                if ((r4 & 32768) > 0) {
                                    int r4 = r4 & (-32769);
                                    sendLogBroadcast(20, java.lang.String.format(java.util.Locale.US, "Error (0x%02X): %s", java.lang.Integer.valueOf(r4), no.nordicsemi.android.error.GattError.parseConnectionError(r4)));
                                } else {
                                    int r4 = r4 & (-16385);
                                    sendLogBroadcast(20, java.lang.String.format(java.util.Locale.US, "Error (0x%02X): %s", java.lang.Integer.valueOf(r4), no.nordicsemi.android.error.GattError.parse(r4)));
                                }
                                loge(r3.getMessage());
                                terminateConnection(r14, r3.getErrorNumber());
                            } catch (no.nordicsemi.android.dfu.internal.exception.UploadAbortedException unused) {
                                r2 = r13;
                                logw("Upload aborted");
                                sendLogBroadcast(15, "Upload aborted");
                                terminateConnection(r14, 0);
                                r25.mProgressInfo.setProgress(-7);
                            } catch (java.lang.Throwable r0) {
                                r3 = r0;
                                r2 = r13;
                                if (r2 != null) {
                                    r2.release();
                                }
                                throw r3;
                            }
                        } catch (no.nordicsemi.android.dfu.internal.exception.DeviceDisconnectedException e) {
                            r0 = e;
                            r15 = 4096;
                        } catch (no.nordicsemi.android.dfu.internal.exception.DfuException r0) {
                            r3 = r0;
                        } catch (no.nordicsemi.android.dfu.internal.exception.UploadAbortedException unused) {
                        }
                        if (r13 != null) {
                            r15 = 4096;
                            try {
                                if (r13.initialize(r26, r14, r5, r6, r7)) {
                                    r13.performDfu(r26);
                                }
                                if (r13 != null) {
                                    r13.release();
                                }
                            } catch (no.nordicsemi.android.dfu.internal.exception.DeviceDisconnectedException e) {
                                r0 = e;
                                r3 = r0;
                                r2 = r13;
                                sendLogBroadcast(20, "Device has disconnected");
                                loge(r3.getMessage());
                                close(r14);
                                r3 = r26.getIntExtra(no.nordicsemi.android.dfu.DfuBaseService.EXTRA_DFU_ATTEMPT, 0);
                                r4 = r26.getIntExtra(no.nordicsemi.android.dfu.DfuBaseService.EXTRA_MAX_DFU_ATTEMPTS, 0);
                                if (r3 >= r4) {
                                }
                            }
                            if (r12) {
                                return;
                            }
                            stopForeground(r10);
                            return;
                        }
                        try {
                            android.util.Log.w(no.nordicsemi.android.dfu.DfuBaseService.TAG, "DFU Service not found.");
                            sendLogBroadcast(15, "DFU Service not found");
                            terminateConnection(r14, no.nordicsemi.android.dfu.DfuBaseService.ERROR_SERVICE_NOT_FOUND);
                            if (r13 != null) {
                                r13.release();
                            }
                            if (r12) {
                                stopForeground(r10);
                                return;
                            }
                            return;
                        } catch (no.nordicsemi.android.dfu.internal.exception.DeviceDisconnectedException e) {
                            r0 = e;
                            r15 = 4096;
                            r2 = r13;
                            r3 = r0;
                            sendLogBroadcast(20, "Device has disconnected");
                            loge(r3.getMessage());
                            close(r14);
                            r3 = r26.getIntExtra(no.nordicsemi.android.dfu.DfuBaseService.EXTRA_DFU_ATTEMPT, 0);
                            r4 = r26.getIntExtra(no.nordicsemi.android.dfu.DfuBaseService.EXTRA_MAX_DFU_ATTEMPTS, 0);
                            if (r3 >= r4) {
                                int r3 = r3 + 1;
                                logi("Restarting the service (" + r3 + " /" + r4 + ")");
                                android.content.Intent r4 = new android.content.Intent();
                                r4.fillIn(r26, 24);
                                r4.putExtra(no.nordicsemi.android.dfu.DfuBaseService.EXTRA_DFU_ATTEMPT, r3);
                                startService(r4);
                                if (r2 != null) {
                                    r2.release();
                                }
                                if (r12) {
                                    stopForeground(r10);
                                    return;
                                }
                                return;
                            }
                            report(r15);
                            if (r2 != null) {
                                r2.release();
                            }
                            if (r12) {
                            }
                        }
                    }
                } else {
                    r5 = r11;
                }
                r7 = r15;
                if (r22) {
                }
                r25.mFirmwareInputStream = r6;
                r25.mInitFileInputStream = r7;
                sendLogBroadcast(5, "Firmware file opened successfully");
                r25.mProgressInfo = new no.nordicsemi.android.dfu.DfuProgressInfo(r25);
                if (!r25.mAborted) {
                }
            } catch (java.lang.Throwable th) {
                r0 = th;
            }
        }
    }

    private InputStream openInputStream(String str, String str2, int i, int i2) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(str);
        if (MIME_TYPE_ZIP.equals(str2)) {
            return new ArchiveInputStream(fileInputStream, i, i2);
        }
        return str.toLowerCase(Locale.US).endsWith("hex") ? new HexInputStream(fileInputStream, i) : fileInputStream;
    }

    private InputStream openInputStream(Uri uri, String str, int i, int i2) throws IOException {
        InputStream openInputStream;
        if (uri.toString().startsWith("file:///android_asset/")) {
            openInputStream = getAssets().open(uri.getPath().substring(15));
        } else {
            openInputStream = getContentResolver().openInputStream(uri);
        }
        if (MIME_TYPE_ZIP.equals(str)) {
            return new ArchiveInputStream(openInputStream, i, i2);
        }
        Cursor query = getContentResolver().query(uri, new String[]{"_display_name"}, null, null, null);
        if (query != null) {
            try {
                if (query.moveToNext() && query.getString(0).toLowerCase(Locale.US).endsWith("hex")) {
                    HexInputStream hexInputStream = new HexInputStream(openInputStream, i);
                    if (query != null) {
                        query.close();
                    }
                    return hexInputStream;
                }
            } catch (Throwable th) {
                if (query != null) {
                    try {
                        query.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        }
        if (query != null) {
            query.close();
        }
        return openInputStream;
    }

    private InputStream openInputStream(int i, String str, int i2, int i3) throws IOException {
        InputStream openRawResource = getResources().openRawResource(i);
        if (MIME_TYPE_ZIP.equals(str)) {
            return new ArchiveInputStream(openRawResource, i2, i3);
        }
        openRawResource.mark(2);
        int read = openRawResource.read();
        openRawResource.reset();
        return read == 58 ? new HexInputStream(openRawResource, i2) : openRawResource;
    }

    protected BluetoothGatt connect(String str) {
        BluetoothGatt connectGatt;
        if (this.mBluetoothAdapter.isEnabled()) {
            this.mConnectionState = -1;
            logi("Connecting to the device...");
            BluetoothDevice remoteDevice = this.mBluetoothAdapter.getRemoteDevice(str);
            if (Build.VERSION.SDK_INT >= 26) {
                sendLogBroadcast(0, "gatt = device.connectGatt(autoConnect = false, TRANSPORT_LE, preferredPhy = LE_1M | LE_2M)");
                connectGatt = remoteDevice.connectGatt(this, false, this.mGattCallback, 2, 3);
            } else if (Build.VERSION.SDK_INT >= 23) {
                sendLogBroadcast(0, "gatt = device.connectGatt(autoConnect = false, TRANSPORT_LE)");
                connectGatt = remoteDevice.connectGatt(this, false, this.mGattCallback, 2);
            } else {
                sendLogBroadcast(0, "gatt = device.connectGatt(autoConnect = false)");
                connectGatt = remoteDevice.connectGatt(this, false, this.mGattCallback);
            }
            try {
                synchronized (this.mLock) {
                    while (true) {
                        int i = this.mConnectionState;
                        if ((i == -1 || i == -2) && this.mError == 0 && !this.mAborted) {
                            this.mLock.wait();
                        }
                    }
                }
            } catch (InterruptedException e) {
                loge("Sleeping interrupted", e);
            }
            return connectGatt;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void terminateConnection(BluetoothGatt bluetoothGatt, int i) {
        if (this.mConnectionState != 0) {
            disconnect(bluetoothGatt);
        }
        refreshDeviceCache(bluetoothGatt, false);
        close(bluetoothGatt);
        waitFor(600L);
        if (i != 0) {
            report(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void disconnect(BluetoothGatt bluetoothGatt) {
        if (this.mConnectionState == 0) {
            return;
        }
        sendLogBroadcast(1, "Disconnecting...");
        this.mProgressInfo.setProgress(-5);
        this.mConnectionState = -4;
        logi("Disconnecting from the device...");
        sendLogBroadcast(0, "gatt.disconnect()");
        bluetoothGatt.disconnect();
        waitUntilDisconnected();
        sendLogBroadcast(5, "Disconnected");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void waitUntilDisconnected() {
        try {
            synchronized (this.mLock) {
                while (this.mConnectionState != 0 && this.mError == 0) {
                    this.mLock.wait();
                }
            }
        } catch (InterruptedException e) {
            loge("Sleeping interrupted", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void waitFor(long j) {
        synchronized (this.mLock) {
            try {
                sendLogBroadcast(0, "wait(" + j + ")");
                this.mLock.wait(j);
            } catch (InterruptedException e) {
                loge("Sleeping interrupted", e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void close(BluetoothGatt bluetoothGatt) {
        logi("Cleaning up...");
        sendLogBroadcast(0, "gatt.disconnect()");
        bluetoothGatt.disconnect();
        sendLogBroadcast(0, "gatt.close()");
        bluetoothGatt.close();
        this.mConnectionState = -5;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void refreshDeviceCache(BluetoothGatt bluetoothGatt, boolean z) {
        if (z || bluetoothGatt.getDevice().getBondState() == 10) {
            sendLogBroadcast(0, "gatt.refresh() (hidden)");
            try {
                logi("Refreshing result: " + ((Boolean) bluetoothGatt.getClass().getMethod("refresh", new Class[0]).invoke(bluetoothGatt, new Object[0])).booleanValue());
            } catch (Exception e) {
                loge("An exception occurred while refreshing device", e);
                sendLogBroadcast(15, "Refreshing failed");
            }
        }
    }

    @Override // no.nordicsemi.android.dfu.DfuProgressInfo.ProgressListener
    public void updateProgressNotification() {
        DfuProgressInfo dfuProgressInfo = this.mProgressInfo;
        int progress = dfuProgressInfo.getProgress();
        if (this.mLastProgress == progress) {
            return;
        }
        this.mLastProgress = progress;
        sendProgressBroadcast(dfuProgressInfo);
        if (this.mDisableNotification) {
            return;
        }
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (elapsedRealtime - this.mLastNotificationTime >= 250 || -6 == progress || -7 == progress) {
            this.mLastNotificationTime = elapsedRealtime;
            String str = this.mDeviceAddress;
            String str2 = this.mDeviceName;
            if (str2 == null) {
                str2 = getString(R.string.dfu_unknown_name);
            }
            NotificationCompat.Builder onlyAlertOnce = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_DFU).setSmallIcon(17301640).setOnlyAlertOnce(true);
            onlyAlertOnce.setColor(-7829368);
            switch (progress) {
                case -7:
                    onlyAlertOnce.setOngoing(false).setContentTitle(getString(R.string.dfu_status_aborted)).setSmallIcon(17301641).setContentText(getString(R.string.dfu_status_aborted_msg)).setAutoCancel(true);
                    break;
                case -6:
                    onlyAlertOnce.setOngoing(false).setContentTitle(getString(R.string.dfu_status_completed)).setSmallIcon(17301641).setContentText(getString(R.string.dfu_status_completed_msg)).setAutoCancel(true).setColor(-16730086);
                    break;
                case -5:
                    onlyAlertOnce.setOngoing(true).setContentTitle(getString(R.string.dfu_status_disconnecting)).setContentText(getString(R.string.dfu_status_disconnecting_msg, new Object[]{str2})).setProgress(100, 0, true);
                    break;
                case -4:
                    onlyAlertOnce.setOngoing(true).setContentTitle(getString(R.string.dfu_status_validating)).setContentText(getString(R.string.dfu_status_validating_msg)).setProgress(100, 0, true);
                    break;
                case -3:
                    onlyAlertOnce.setOngoing(true).setContentTitle(getString(R.string.dfu_status_switching_to_dfu)).setContentText(getString(R.string.dfu_status_switching_to_dfu_msg)).setProgress(100, 0, true);
                    break;
                case -2:
                    onlyAlertOnce.setOngoing(true).setContentTitle(getString(R.string.dfu_status_starting)).setContentText(getString(R.string.dfu_status_starting_msg)).setProgress(100, 0, true);
                    break;
                case -1:
                    onlyAlertOnce.setOngoing(true).setContentTitle(getString(R.string.dfu_status_connecting)).setContentText(getString(R.string.dfu_status_connecting_msg, new Object[]{str2})).setProgress(100, 0, true);
                    break;
                default:
                    onlyAlertOnce.setOngoing(true).setContentTitle(dfuProgressInfo.getTotalParts() == 1 ? getString(R.string.dfu_status_uploading) : getString(R.string.dfu_status_uploading_part, new Object[]{Integer.valueOf(dfuProgressInfo.getCurrentPart()), Integer.valueOf(dfuProgressInfo.getTotalParts())})).setContentText(getString(R.string.dfu_status_uploading_msg, new Object[]{str2})).setProgress(100, progress, false);
                    break;
            }
            Intent intent = new Intent(this, getNotificationTarget());
            intent.addFlags(268435456);
            intent.putExtra(EXTRA_DEVICE_ADDRESS, str);
            intent.putExtra(EXTRA_DEVICE_NAME, str2);
            intent.putExtra(EXTRA_PROGRESS, progress);
            onlyAlertOnce.setContentIntent(PendingIntent.getActivity(this, 0, intent, Build.VERSION.SDK_INT >= 23 ? 201326592 : 134217728));
            updateProgressNotification(onlyAlertOnce, progress);
            NotificationManager notificationManager = (NotificationManager) getSystemService("notification");
            if (notificationManager != null) {
                notificationManager.notify(NOTIFICATION_ID, onlyAlertOnce.build());
            }
        }
    }

    protected void updateProgressNotification(NotificationCompat.Builder builder, int i) {
        if (i == -7 || i == -6) {
            return;
        }
        Intent intent = new Intent(BROADCAST_ACTION);
        intent.putExtra(EXTRA_ACTION, 2);
        builder.addAction(R.drawable.ic_action_notify_cancel, getString(R.string.dfu_action_abort), PendingIntent.getBroadcast(this, 1, intent, Build.VERSION.SDK_INT >= 23 ? 201326592 : 134217728));
    }

    private void report(int i) {
        sendErrorBroadcast(i);
        if (this.mDisableNotification) {
            return;
        }
        String str = this.mDeviceAddress;
        String str2 = this.mDeviceName;
        if (str2 == null) {
            str2 = getString(R.string.dfu_unknown_name);
        }
        NotificationCompat.Builder autoCancel = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_DFU).setSmallIcon(17301640).setOnlyAlertOnce(true).setColor(SupportMenu.CATEGORY_MASK).setOngoing(false).setContentTitle(getString(R.string.dfu_status_error)).setSmallIcon(17301641).setContentText(getString(R.string.dfu_status_error_msg)).setAutoCancel(true);
        Intent intent = new Intent(this, getNotificationTarget());
        intent.addFlags(268435456);
        intent.putExtra(EXTRA_DEVICE_ADDRESS, str);
        intent.putExtra(EXTRA_DEVICE_NAME, str2);
        intent.putExtra(EXTRA_PROGRESS, i);
        autoCancel.setContentIntent(PendingIntent.getActivity(this, 0, intent, Build.VERSION.SDK_INT >= 23 ? 201326592 : 134217728));
        updateErrorNotification(autoCancel);
        NotificationManager notificationManager = (NotificationManager) getSystemService("notification");
        if (notificationManager != null) {
            notificationManager.notify(NOTIFICATION_ID, autoCancel.build());
        }
    }

    private void startForeground() {
        NotificationCompat.Builder ongoing = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_DFU).setSmallIcon(17301640).setContentTitle(getString(R.string.dfu_status_foreground_title)).setContentText(getString(R.string.dfu_status_foreground_content)).setColor(-7829368).setPriority(-1).setOngoing(true);
        Class<? extends Activity> notificationTarget = getNotificationTarget();
        if (notificationTarget != null) {
            Intent intent = new Intent(this, notificationTarget);
            intent.addFlags(268435456);
            intent.putExtra(EXTRA_DEVICE_ADDRESS, this.mDeviceAddress);
            intent.putExtra(EXTRA_DEVICE_NAME, this.mDeviceName);
            ongoing.setContentIntent(PendingIntent.getActivity(this, 0, intent, Build.VERSION.SDK_INT >= 23 ? 201326592 : 134217728));
        } else {
            logw("getNotificationTarget() should not return null if the service is to be started as a foreground service");
        }
        updateForegroundNotification(ongoing);
        startForeground(NOTIFICATION_ID, ongoing.build());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public DfuDeviceSelector getDeviceSelector() {
        return new DfuDefaultDeviceSelector();
    }

    private void sendProgressBroadcast(DfuProgressInfo dfuProgressInfo) {
        Intent intent = new Intent(BROADCAST_PROGRESS);
        intent.putExtra(EXTRA_DATA, dfuProgressInfo.getProgress());
        intent.putExtra(EXTRA_DEVICE_ADDRESS, this.mDeviceAddress);
        intent.putExtra(EXTRA_PART_CURRENT, dfuProgressInfo.getCurrentPart());
        intent.putExtra(EXTRA_PARTS_TOTAL, dfuProgressInfo.getTotalParts());
        intent.putExtra(EXTRA_SPEED_B_PER_MS, dfuProgressInfo.getSpeed());
        intent.putExtra(EXTRA_AVG_SPEED_B_PER_MS, dfuProgressInfo.getAverageSpeed());
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    private void sendErrorBroadcast(int i) {
        Intent intent = new Intent(BROADCAST_ERROR);
        if ((i & 16384) > 0) {
            intent.putExtra(EXTRA_DATA, i & (-16385));
            intent.putExtra(EXTRA_ERROR_TYPE, 2);
        } else if ((32768 & i) > 0) {
            intent.putExtra(EXTRA_DATA, i & (-32769));
            intent.putExtra(EXTRA_ERROR_TYPE, 1);
        } else if ((i & 8192) > 0) {
            intent.putExtra(EXTRA_DATA, i & (-8193));
            intent.putExtra(EXTRA_ERROR_TYPE, 3);
        } else {
            intent.putExtra(EXTRA_DATA, i);
            intent.putExtra(EXTRA_ERROR_TYPE, 0);
        }
        intent.putExtra(EXTRA_DEVICE_ADDRESS, this.mDeviceAddress);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void sendLogBroadcast(int i, String str) {
        Intent intent = new Intent(BROADCAST_LOG);
        intent.putExtra(EXTRA_LOG_MESSAGE, "[DFU] " + str);
        intent.putExtra(EXTRA_LOG_LEVEL, i);
        intent.putExtra(EXTRA_DEVICE_ADDRESS, this.mDeviceAddress);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    private boolean initialize() {
        BluetoothManager bluetoothManager = (BluetoothManager) getSystemService("bluetooth");
        if (bluetoothManager == null) {
            loge("Unable to initialize BluetoothManager.");
            return false;
        }
        BluetoothAdapter adapter = bluetoothManager.getAdapter();
        this.mBluetoothAdapter = adapter;
        if (adapter == null) {
            loge("Unable to obtain a BluetoothAdapter.");
            return false;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loge(String str) {
        Log.e(TAG, str);
    }

    private void loge(String str, Throwable th) {
        Log.e(TAG, str, th);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void logw(String str) {
        if (DEBUG) {
            Log.w(TAG, str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void logi(String str) {
        if (DEBUG) {
            Log.i(TAG, str);
        }
    }
}
