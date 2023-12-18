package com.egeetouch.egeetouch_manager;

import android.app.ActivityManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.hardware.display.DisplayManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.PowerManager;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.text.format.Time;
import android.util.Log;
import android.view.Display;
import android.widget.Toast;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.work.WorkRequest;
import cn.pedant.SweetAlert.SweetAlertDialog;
import com.egeetouch.egeetouch_manager.ConsumerService;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.internal.ServerProtocol;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.ServerValues;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import kotlin.UByte;
import no.nordicsemi.android.dfu.DfuServiceInitiator;
/* loaded from: classes.dex */
public class BLEService extends Service implements BluetoothAdapter.LeScanCallback {
    public static final String ACTION_DATA_AVAILABLE = "ACTION_DATA_AVAILABLE";
    public static final int Add_Audit_Trail_1 = 40;
    public static final int Add_Audit_Trail_2 = 41;
    public static final int Add_Tag = 80;
    public static final int Add_Tag_fail = 225;
    public static final int Add_Tag_number = 19;
    public static final int Add_User_Name = 24;
    public static final int Add_User_number = 36;
    public static final int Add_User_password = 25;
    public static final int Add_tag_version2 = 221;
    public static final int AutoLocking_Settings = 153;
    public static final int Change_passcode = 89;
    public static final int Locking_By_App = 105;
    private static final int REQUEST_ENABLE_BT = 0;
    public static final int ReadResponse_AutoLocking_Settings = 237;
    public static final int ReadResponse_ChangePasscode = 245;
    public static final int ReadResponse_Master_Tag = 242;
    public static final int ReadResponse_ShackleBypass = 240;
    public static final int ReadResponse_changePasscode_Count = 260;
    public static final int ReadResponse_clear_passcodeAuditTrail = 254;
    public static final int ReadResponse_passcodeAudit = 252;
    public static final int ReadResponse_passcodeValue = 250;
    public static final int ReadResponse_scanTagId = 236;
    public static final int ReadResponse_syncPasscode = 258;
    public static final int Read_Audit_Data_1 = 54;
    public static final int Read_Audit_Data_2 = 55;
    public static final int Read_Response_Add_Audit_Trail_1 = 48;
    public static final int Read_Response_Add_Audit_Trail_2 = 49;
    public static final int Read_Response_Add_Tag = 17;
    public static final int Read_Response_Add_Tag_number = 20;
    public static final int Read_Response_Add_User_Name = 32;
    public static final int Read_Response_Add_User_number = 37;
    public static final int Read_Response_Add_User_password = 33;
    public static final int Read_Response_Add_tag_IP66 = 600;
    public static final int Read_Response_AuditTrail_delete_IP66 = 606;
    public static final int Read_Response_Audit_trail_IP66 = 605;
    public static final int Read_Response_Check_tag_IP66 = 602;
    public static final int Read_Response_Delete_tag_IP66 = 601;
    public static final int Read_Response_Master_Tag_IP66 = 608;
    public static final int Read_Response_Request_Audit_Data_1 = 52;
    public static final int Read_Response_Request_Audit_Data_2 = 53;
    public static final int Read_Response_Request_Audit_Index = 57;
    public static final int Read_Response_Request_Tag_number = 22;
    public static final int Read_Response_Request_Tag_number_Version2 = 227;
    public static final int Read_Response_Request_User_Password = 39;
    public static final int Read_Response_Request_User_number = 35;
    public static final int Read_Response_auditCount_IP66 = 607;
    public static final int Read_Response_enter_low_power_mode = 166;
    public static final int Read_Response_lotoEnable_auditTrail = 266;
    public static final int Read_Response_quit_low_power_mode = 167;
    public static final int Read_Response_read_serial = 168;
    public static final int Read_Response_reconnecting_period = 610;
    public static final int Read_Response_request_Tag = 18;
    public static final int Read_Response_request_User_name = 38;
    public static final int Read_Response_status_IP66 = 604;
    public static final int Read_Response_update_reconnecting_period = 609;
    public static final int Read_masterTag_only = 409;
    public static final int Read_response_Admin_Password = 16;
    public static final int Read_response_Admin_Password_new_lock = 178;
    public static final int Read_response_Lock_Setting_Param = 171;
    public static final int Read_response_Locking_By_App = 68;
    public static final int Read_response_Unlocking_RSSI = 66;
    public static final int Read_response_clear_tag_AuditTrail = 233;
    public static final int Read_response_disable_shut_down = 71;
    public static final int Read_response_input_new_primary_password = 88;
    public static final int Read_response_lotoBatteryRecords = 244;
    public static final int Read_response_reset_master_password = 87;
    public static final int Read_response_tag_AuditTrail = 231;
    public static final int Read_response_tag_AuditTrail_number = 229;
    public static final int Read_response_timestamp = 228;
    public static final int Read_response_update_reconnecting_period = 165;
    public static final int Request_Add_tag_IP66 = 500;
    public static final int Request_AuditTrail_delete_IP66 = 506;
    public static final int Request_Audit_Data_1 = 50;
    public static final int Request_Audit_Data_2 = 51;
    public static final int Request_Audit_Index = 103;
    public static final int Request_Audit_trail_IP66 = 505;
    public static final int Request_ChangePasscode = 173;
    public static final int Request_Check_tag_IP66 = 502;
    public static final int Request_Delete_tag_IP66 = 501;
    public static final int Request_Lock_Setting_Param = 113;
    public static final int Request_Master_Tag = 241;
    public static final int Request_Master_Tag_IP66 = 508;
    public static final int Request_Read_tag_IP66 = 503;
    public static final int Request_Response_Read_tag_IP66 = 603;
    public static final int Request_Tag_list = 100;
    public static final int Request_Tag_number = 21;
    public static final int Request_User_Name = 101;
    public static final int Request_User_Password = 102;
    public static final int Request_User_number = 34;
    public static final int Request_auditCount_IP66 = 507;
    public static final int Request_changePasscode_Count = 259;
    public static final int Request_clear_passcodeAuditTrail = 253;
    public static final int Request_clear_tag_AuditTrail = 232;
    public static final int Request_lotoBatteryRecords = 243;
    public static final int Request_lotoEnable_auditTrail = 265;
    public static final int Request_old_passcode = 144;
    public static final int Request_passcodeAudit = 251;
    public static final int Request_passcodeValue = 249;
    public static final int Request_reconnecting_period = 510;
    public static final int Request_status_IP66 = 504;
    public static final int Request_syncPasscode = 257;
    public static final int Request_tag_AuditTrail_number = 158;
    public static final int Request_tag_AuditTrial = 230;
    public static final int Request_tag_list_version2 = 223;
    public static final int Request_tag_number_version2 = 222;
    public static final int Request_update_reconnecting_period = 509;
    public static final int Response_old_passcode = 90;
    private static final int STATE_CONNECTED = 2;
    private static final int STATE_CONNECTING = 1;
    private static final int STATE_DISCONNECTED = 0;
    public static final int ScanTagId_FromLock = 235;
    public static final int Send_timeStamp = 159;
    public static final int ShackleBypass_settings = 238;
    public static final int Unlocking_RSSI = 98;
    public static final int Verify_Admin_Password = 97;
    public static final int Verify_Admin_Password_new_lock = 176;
    static AddLockListener addLockListener = null;
    public static final int admin = 0;
    static BatteryLowListener batteryLowListener = null;
    public static final int check_battery_level = 67;
    public static final int check_model = 161;
    public static final int check_version = 160;
    public static FirebaseDatabase commercialDatabase = null;
    public static final int coresponse_MasterTag = 665;
    static BluetoothGattDescriptor descriptor = null;
    public static final int disable_shut_down = 99;
    public static final int disconnect = 187;
    public static final int enter_low_power_mode = 118;
    public static final int input_new_primary_password = 86;
    private static String mBluetoothDeviceAddress = null;
    private static BluetoothManager mBluetoothManager = null;
    public static int mConnectionState = 0;
    public static MainActivity mainActivity = null;
    public static Time now_admin = null;
    public static final int quit_low_power_mode = 119;
    public static final int read_serial = 83;
    public static final int reset_master_password = 85;
    public static final int shut_down_lock = 112;
    public static final int update_reconnecting_period = 117;
    public static final int user = 1;
    public static final int user_one_time_access = 2;
    BluetoothGattService batteryService;
    BluetoothGattCharacteristic characteristic_1;
    BluetoothGattCharacteristic characteristic_2;
    BluetoothGattCharacteristic characteristic_3;
    BluetoothGattCharacteristic characteristic_4;
    BluetoothGattCharacteristic characteristic_5;
    BluetoothGattCharacteristic characteristic_battery;
    BluetoothGattCharacteristic characteristic_model;
    BluetoothGattCharacteristic characteristic_serial;
    BluetoothGattCharacteristic characteristic_version;
    BluetoothGattService deviceService;
    private List<ScanFilter> filters;
    CurrentSelectedLockInfo lockInfo;
    BluetoothGattService lockService;
    LotoInfo lotoInfo;
    private BluetoothAdapter mBluetoothAdapter;
    private BluetoothGatt mBluetoothGatt;
    private BluetoothGatt mConnectedGatt;
    private BluetoothLeScanner mLEScanner;
    private ScanSettings settings;
    Toast toaster;
    BluetoothGattCharacteristic unlocking_characteristic;
    static final UUID lock_Service_UUID_luggage_padlock = UUID.fromString("0000fff7-0000-1000-8000-00805f9b34fb");
    static final UUID lock_Service_UUID_tvl_padlock = UUID.fromString("0000fff8-0000-1000-8000-00805f9b34fb");
    static final UUID lock_Service_UUID_bt_padlock = UUID.fromString("0000fff9-0000-1000-8000-00805f9b34fb");
    static final UUID lock_Service_UUID_bt_ip45 = UUID.fromString("0000fffa-0000-1000-8000-00805f9b34fb");
    static final UUID lock_Service_UUID_bt_loto = UUID.fromString("0000fffc-0000-1000-8000-00805f9b34fb");
    static final UUID lock_Service_UUID_bt_ip66 = UUID.fromString("0000f00d-0000-1000-8000-00805f9b34fb");
    static final UUID Device_Service_UUID = UUID.fromString("0000180A-0000-1000-8000-00805f9b34fb");
    static final UUID Battery_Service_UUID = UUID.fromString("0000180F-0000-1000-8000-00805f9b34fb");
    static final UUID UUID_model = UUID.fromString("00002a24-0000-1000-8000-00805f9b34fb");
    static final UUID UUID_serial = UUID.fromString("00002a25-0000-1000-8000-00805f9b34fb");
    static final UUID UUID_version = UUID.fromString("00002a26-0000-1000-8000-00805f9b34fb");
    static final UUID UUID_battery = UUID.fromString("00002A19-0000-1000-8000-00805f9b34fb");
    static final UUID UUID_1 = UUID.fromString("0000fff1-0000-1000-8000-00805f9b34fb");
    static final UUID UUID_2 = UUID.fromString("0000fff2-0000-1000-8000-00805f9b34fb");
    static final UUID UUID_3 = UUID.fromString("0000fff3-0000-1000-8000-00805f9b34fb");
    static final UUID UUID_4 = UUID.fromString("0000fff4-0000-1000-8000-00805f9b34fb");
    static final UUID UUID_5 = UUID.fromString("0000fff5-0000-1000-8000-00805f9b34fb");
    static final UUID UUID_descriptor = UUID.fromString("00002902-0000-1000-8000-00805f9b34fb");
    public static Boolean device_is_connected = false;
    public static int current_user_index = 1;
    public static float update_progress = 0.0f;
    public static boolean add_tag_done = false;
    public static int Tag_current_number = 0;
    public static int Tag_number = 0;
    public static int Tag_loop_number = 0;
    public static int retrieve_tag_current_number = 0;
    public static boolean retrieved_tag_done = false;
    public static int retrieved_Tag_number = 0;
    public static String selected_lock_name = "";
    public static String selected_lock_password = "";
    public static boolean selected_lock_state = true;
    public static String selectedLockip45SerialNumber = "";
    public static int typeOfLockInIOSFormat = 0;
    public static String fullNameOfLock = "";
    public static String parsedIp45SerialNumber = "";
    public static Boolean isIp45Lock = false;
    public static byte[] old_pass_code = new byte[16];
    public static byte[] new_pass_code_byte = new byte[16];
    public static int selected_lock_role = 0;
    public static String selected_lock_serial = "";
    public static String selectedLockIP45Serial = "";
    public static String selected_lock_version = "";
    public static String selected_lock_model = "";
    public static String selected_lock_address = "";
    public static String selected_lock_sharedByEmail = "";
    public static String selected_lock_shareStartTime = "";
    public static String selected_lock_shareEndTime = "";
    public static float selected_lock_address_latitude = 0.0f;
    public static float selected_lock_address_longitude = 0.0f;
    public static boolean selected_lock_is_one_time_access = false;
    public static int set_adv_time = 0;
    public static int audit_trail_index_number = 0;
    public static int audit_trail_loop_number = 0;
    public static int temp_audit_count1 = 1;
    public static int temp_audit_count = 1;
    public static boolean new_password_verification = false;
    public static boolean verified_password_done = false;
    public static boolean retrieved_user_done = false;
    public static boolean wrong_password = false;
    public static boolean add_user_done = false;
    public static int User_number = 0;
    public static int User_current_number = 0;
    public static int User_loop_number = 1;
    public static int retrieved_User_number = 0;
    public static int retrieve_user_current_number = 0;
    public static boolean audit_pass_verification = false;
    public static boolean shutdown_done = false;
    public static boolean pending_shut_down = false;
    public static boolean pending_disconnect = false;
    public static boolean pending_vicinity_tracking = false;
    public static boolean shutdown_triggered = false;
    public static boolean disconnect_triggered = false;
    public static boolean cancel_scaning_triggered = false;
    public static boolean force_disconnect = false;
    public static boolean add_new_password_done = false;
    public static boolean locking_by_lock = false;
    public static boolean change_passcode = false;
    public static int Ble_Mode = 0;
    public static int Ble_Mode_before_disconnect = 0;
    public static boolean Response_old_passcode_boolean = false;
    public static byte[] Add_tag_fail = new byte[16];
    public static int audittrail_count_IP66 = 0;
    public static int audittrail_max_count = 0;
    public static int index_tag_IP66 = 0;
    public static int add_tag_count_IP66 = 0;
    public static int audit_current_index_IP66 = 0;
    public static List<String> list_Add_tag_id_IP66 = new ArrayList();
    public static List<String> list_Add_tag_name_IP66 = new ArrayList();
    public static String deleteTagID_IP66 = "";
    public static int PasscodeIndexCount = 0;
    public static int PasscodeAuditCount = 0;
    public static int PasscodeAuditIndex = 0;
    public static String new_passcode = "";
    public static String new_passcodeName = "";
    public static byte[] new_passcodeBytes = new byte[6];
    public static String StrPasscodeEmoji = "";
    public static String Detected_tag_id = "";
    public static int previous_lockStatus_notification = 0;
    public static boolean is_sendTimeStamp = false;
    public static boolean iS_sendConnectionTimeStamp = false;
    public static long Total_Tag_Audit = 0;
    public static long Tag_Audit_count = 0;
    public static int Tag_AuditList_count = 0;
    public static int Tag_Audit_status = 0;
    public static int AuditTrail_Capacity = 100;
    public static long AuditTrail_Percentage = 1;
    public static int tag_batch_number = 0;
    public static int tag_page_number = 0;
    public static int Request_tag_batch = 0;
    public static int Request_tag_page = 0;
    public static int name_count = 0;
    public static int page_count = 0;
    public static int tag_list_count = 0;
    public static int AddTagCloud_count = 0;
    public static int new_tag_name_count = 0;
    public static int numberOfPageToUpdate = 0;
    public static int currentPagePosition = 0;
    public static int totalNewTagsAdded = 0;
    public static long totalTagsInLock = 0;
    public static boolean add_tag_boolean = false;
    public static boolean tag_updateSuccess = true;
    public static int Request_list_page = 0;
    public static int Request_list_batch = 0;
    public static int vertex = 15;
    public static ArrayList<ArrayList<String>> check_page_graph = new ArrayList<>(vertex);
    public static List<Integer> rewrite_page = new ArrayList();
    public static boolean rewrite_page_boolean = false;
    public static int highestTagPage = 0;
    protected static final char[] hexArray = "0123456789ABCDEF".toCharArray();
    public static boolean connection_response = true;
    public static boolean vicinity_on = false;
    public static int proximity_sound = 1;
    private static int transmission_error_counter = 0;
    public static boolean range_far = true;
    private static int check_battery_counter = 0;
    public static boolean check_battery_routine_on = false;
    public static boolean battery_value_obtained = false;
    public static boolean get_battery_level_done = false;
    public static int battery_level = 0;
    public static boolean get_version_done = false;
    public static String new_primary_password = "";
    public static boolean trigger_low_power_mode = false;
    public static int notification_stay_connected = 0;
    public static boolean notification_stay_connected_showed1 = false;
    public static boolean notification_stay_connected_showed2 = false;
    private static int delay_for_4_sec_counter = 0;
    private static boolean delay_for_4_sec = false;
    public static int var_zone_1 = 0;
    public static int var_zone_2 = 0;
    public static int zone_1_min = -85;
    public static int zone_1_max = -88;
    public static int zone_2_min = -93;
    public static int zone_2_max = -95;
    private static boolean got_new_rssi = false;
    private static int read_rssi_counter = 0;
    public static int rssi_value = 0;
    private static int[] average_rssi = new int[10];
    private static int min_rssi = -57;
    private static int max_rssi = -100;
    private static int past_rssi = -57;
    public static int avg_rssi = -57;
    private static boolean notify_left_ur_luggage = false;
    private static boolean notify_disconnected = false;
    public static boolean update_UI_new_lock_status = false;
    public static boolean grace_period_for_LP_mode_is_over = true;
    public static int grace_period_for_LP_mode_counter = 0;
    private static boolean handler_LP_started = false;
    public static boolean mIsBound = false;
    public static ConsumerService mConsumerService = null;
    public static boolean skip_verify_password_UI = false;
    public static boolean ready_update_parameter = false;
    public static int loading_percentage = 20;
    public static boolean egeetouchDeviceFound = false;
    public static boolean isShackleOpened = false;
    public static boolean isInitialStateOpened = false;
    public static boolean isLockInChargingState = false;
    public static int AvailableAuditCount = 0;
    public static int AvailableTagCount = 0;
    public static int LockReconnectingTime = 0;
    public static boolean AutoLockingMode = false;
    public static boolean isShackleBypass_turnedOn = false;
    public static boolean isTagAuditTrail_updateOnly = false;
    public static boolean shackleByPassOnce_isEnabled = false;
    public static List<Integer> listPasscodeIndex = new ArrayList();
    public static List<String> listAuditPassCode = new ArrayList();
    public static List<Long> listAuditTrailTime = new ArrayList();
    public static List<Long> listAuditLockedbyTime = new ArrayList();
    public static List<Integer> listAuditDeciValue = new ArrayList();
    public static List<String> listAuditTrailTagID = new ArrayList();
    public static List<Integer> listAuditType = new ArrayList();
    public static List<String> listAuditTrailTagIDIP45 = new ArrayList();
    public static List<Long> listAuditTrailTagTimeIP45 = new ArrayList();
    public static List<Integer> listAuditTagTypeIP45 = new ArrayList();
    public static long lastUpdatedAuditTimestamp = 0;
    public static String tagID = "";
    public static String TagtagID = "";
    public static ArrayList<String> address_blacklist = new ArrayList<>();
    static boolean service_disconnect = false;
    public static Handler addTimeoutHandler = new Handler();
    public static boolean first_battery_check = true;
    public static ArrayList<String> sync_list = new ArrayList<>();
    public static ArrayList<Integer> sync_list_page = new ArrayList<>();
    private ArrayList<BluetoothDevice> mDevices = new ArrayList<>();
    Handler mhandler = new Handler();
    Handler mhandler_task = new Handler();
    Handler mhandler_task_low_power = null;
    Handler mhandler_battery = new Handler();
    private final IBinder mBinder = new LocalBinder();
    private int detected_max_newDevice_rssi = -100;
    private BluetoothDevice detected_new_BluetoothDevices = null;
    Boolean service_obtained_correct = true;
    Boolean service_obtained_done = false;
    final int refresh_interval = 500;
    String user_name_temp = "";
    String user_password_temp = "";
    private int totalsyncPasscode = 0;
    private int syncPasscodeCount = 0;
    private List<Integer> syncPassCodeIndexList = new ArrayList();
    private List<Integer> syncPassCodeValueList = new ArrayList();
    List<String> list_tag_ids = new ArrayList();
    private int current_rssi = min_rssi;
    private int vicinity_count = 0;
    private final int SHACKLE_LOCKED = 1;
    private final int USB_DETECTED = 2;
    private final int FOREVER_WAITING_TO_LOCK_BACK = 16;
    private final int LOCKBYPASS = 8;
    private final int TIMESTAMP_ERROR = 4;
    public int autoReconnectionTime = 15;
    MainActivity mMainActivity = new MainActivity();
    Firebase_Data_management firebaseDataManaggement = new Firebase_Data_management();
    boolean sending = false;
    String auditKey = "WATCH_AUDIT_TRAIL";
    SharedPreferences sharedPreferences = MainActivity.context.getSharedPreferences("WATCH_AUDIT", 0);
    Handler watchAuditHandler = new Handler();
    ArrayList<Integer> battery_list = new ArrayList<>();
    private final ServiceConnection mConnection = new ServiceConnection() { // from class: com.egeetouch.egeetouch_manager.BLEService.2
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            BLEService.mConsumerService = ((ConsumerService.LocalBinder) iBinder).getService();
            if (!BLEService.mIsBound || BLEService.mConsumerService == null) {
                return;
            }
            BLEService.mConsumerService.findPeers();
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            BLEService.mConsumerService = null;
            BLEService.mIsBound = false;
        }
    };
    final BluetoothGattCallback mGattCallback = new BluetoothGattCallback() { // from class: com.egeetouch.egeetouch_manager.BLEService.3
        @Override // android.bluetooth.BluetoothGattCallback
        public void onConnectionStateChange(BluetoothGatt bluetoothGatt, int i, int i2) {
            super.onConnectionStateChange(bluetoothGatt, i, i2);
            System.out.println("Hello OnConnectionStateChange: newState:" + i2);
            if (i2 != 0) {
                if (i2 == 2) {
                    Log.i("watch_connected", "watch_connected: " + String.valueOf(MainActivity.watch_connected));
                    if (MainActivity.watch_connected) {
                        Log.i("watch_connected", "watch_connected CONNECTED");
                        String str = BLEService.selected_lock_state ? "Locking completed" : "Unlocking completed";
                        System.out.println("Hello sendData:Initial connection");
                        Log.i("mainGattCallback", "watch_connected CONNECTED " + str);
                        BLEService.mConsumerService.sendData(str);
                    } else {
                        BLEService.mIsBound = BLEService.this.bindService(new Intent(BLEService.this, ConsumerService.class), BLEService.this.mConnection, 1);
                    }
                    if (MainActivity.scanning_new_lock) {
                        BLEService.addTimeoutHandler.postDelayed(BLEService.this.runAddTimeout, 60000L);
                    }
                    Log.i("mainGattCallback", "CONNECTED");
                    Log.i("TAG", "result: " + String.valueOf(bluetoothGatt.discoverServices()));
                    return;
                }
                Log.i("mainGattCallback", "STATE_OTHER");
                return;
            }
            BLEService.this.mBluetoothGatt.close();
            BLEService.this.mBluetoothGatt = null;
            BLEService.egeetouchDeviceFound = false;
            if (MainActivity.watch_connected) {
                BLEService.mConsumerService.sendData("Disconnected");
            }
            if (BLEService.Ble_Mode != 0) {
                BLEService.Ble_Mode_before_disconnect = BLEService.Ble_Mode;
            }
            BLEService.mConnectionState = 0;
            BLEService.this.lockInfo.setConnectionState(0);
            BLEService.Ble_Mode = 0;
            Log.i("mainGattCallback", "DISCONNECTED: " + String.valueOf(MainActivity.watch_connected));
            Log.i("TAG", "shutdown_triggered: " + BLEService.shutdown_triggered);
            if (MainActivity.scanning_new_lock) {
                if (BLEService.service_disconnect) {
                    BLEService.address_blacklist.add(BLEService.selected_lock_address);
                    BLEService.service_disconnect = false;
                    System.out.println("CMDCMD blacklisted address: " + BLEService.selected_lock_address + ", model: " + BLEService.selected_lock_model);
                }
                BLEService.this.startScan();
            } else if ((!BLEService.disconnect_triggered && !BLEService.shutdown_triggered && !BLEService.cancel_scaning_triggered) || (BLEService.trigger_low_power_mode && !BLEService.shutdown_triggered)) {
                new Timer().schedule(new TimerTask() { // from class: com.egeetouch.egeetouch_manager.BLEService.3.1
                    @Override // java.util.TimerTask, java.lang.Runnable
                    public void run() {
                        BLEService.connection_response = BLEService.this.connect(BLEService.selected_lock_address);
                        Log.i("TAG", "Disconnected connection_response: " + BLEService.connection_response);
                        BLEService.mConnectionState = 0;
                        BLEService.this.lockInfo.setConnectionState(0);
                    }
                }, 1000L);
            } else {
                BLEService.battery_value_obtained = false;
            }
            BLEService.mConnectionState = 0;
            BLEService.this.lockInfo.setConnectionState(0);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i) {
            super.onServicesDiscovered(bluetoothGatt, i);
            BLEService.this.service_obtained_correct = true;
            System.out.println("HEY BLEService.parsedIp45SerialNumber connecting lock onServicesDiscovered " + BLEService.parsedIp45SerialNumber);
            System.out.println("HEY BLEService.selectedLockIP45Serial database lock onServicesDiscovered " + BLEService.selectedLockIP45Serial);
            if (!MainActivity.isAddingLockMode) {
                System.out.println("HEY you are NOT in adding lock mode");
                if (BLEService.parsedIp45SerialNumber.equals(BLEService.selectedLockIP45Serial)) {
                    System.out.println("HEY ITS THE SAME YOU CAN PROCEED from onServicesDiscovered");
                    System.out.println("HEY parsedIp45SerialNumber is " + BLEService.parsedIp45SerialNumber);
                    System.out.println("HEY selectedLockIP45Serial is " + BLEService.selectedLockIP45Serial);
                    BLEService.this.link_service_up();
                } else {
                    System.out.println("HEY ITS NOT THE SAME SO CANNOT PROCEED from onServicesDiscovered");
                    System.out.println("HEY parsedIp45SerialNumber is " + BLEService.parsedIp45SerialNumber);
                    System.out.println("HEY selectedLockIP45Serial is " + BLEService.selectedLockIP45Serial);
                }
            } else {
                System.out.println("HEY you are in adding lock mode");
                BLEService.this.link_service_up();
            }
            if (MainActivity.watch_connected) {
                String str = BLEService.selected_lock_state ? "Locking completed" : "Unlocking completed";
                System.out.println("Hello sendData:Connection 2");
                BLEService.mConsumerService.sendData(str);
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicRead(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            super.onCharacteristicRead(bluetoothGatt, bluetoothGattCharacteristic, i);
            if (i == 0) {
                BLEService.this.broadcastUpdate(BLEService.ACTION_DATA_AVAILABLE, bluetoothGattCharacteristic);
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            super.onCharacteristicWrite(bluetoothGatt, bluetoothGattCharacteristic, i);
            BLEService bLEService = BLEService.this;
            bLEService.setCharacteristicNotification(bLEService.characteristic_2, true);
            BLEService bLEService2 = BLEService.this;
            bLEService2.setCharacteristicNotification(bLEService2.characteristic_battery, true);
            if (BLEService.selected_lock_model.equals("GT5100") || BLEService.selected_lock_model.equals("GT5107") || BLEService.selected_lock_model.equals("GT5109") || BLEService.selected_lock_model.equals("GT5300")) {
                BLEService bLEService3 = BLEService.this;
                bLEService3.setCharacteristicNotification(bLEService3.characteristic_5, true);
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            SharedPreferences defaultSharedPreferences;
            super.onCharacteristicChanged(bluetoothGatt, bluetoothGattCharacteristic);
            byte[] value = bluetoothGattCharacteristic.getValue();
            Log.i("BLEService", "Hello characteristic:" + String.valueOf(bluetoothGattCharacteristic.getUuid()));
            Log.i("TAG", "characteristic: check" + String.valueOf(bluetoothGattCharacteristic) + "  data[0]: " + ((int) value[0]) + "  length: " + value.length);
            if (value.length == 3) {
                System.out.println("Hello checking the data length : " + value.length + "  data[0]: " + ((int) value[0]) + "  data[1]: " + ((int) value[1]) + " data[2]:" + ((int) value[2]) + " char :" + String.valueOf(bluetoothGattCharacteristic.getUuid()));
            } else {
                System.out.println("Hello checking the data length : " + value.length + "  data[0]: " + ((int) value[0]) + " char :" + String.valueOf(bluetoothGattCharacteristic.getUuid()));
            }
            if ((BLEService.selected_lock_version.contains("1.8") && bluetoothGattCharacteristic.getUuid().equals(BLEService.UUID_2) && value[0] == -81) || BLEService.selected_lock_model.equals("GT5100") || BLEService.selected_lock_model.equals("GT5109") || BLEService.selected_lock_model.equals("GT5107") || BLEService.selected_lock_model.equals("GT5300")) {
                BLEService bLEService = BLEService.this;
                bLEService.setCharacteristicNotification(bLEService.characteristic_5, true);
            }
            if (bluetoothGattCharacteristic.getUuid().equals(BLEService.UUID_5)) {
                String replace = String.format("%8s", Integer.toBinaryString(value[0] & UByte.MAX_VALUE)).replace(' ', '0');
                if ((value[0] & 1) == 1) {
                    BLEService.shackleByPassOnce_isEnabled = false;
                    BLEService.isShackleOpened = false;
                    if (UI_BLE.shackle_dialog != null && UI_BLE.shackle_dialog.isShowing()) {
                        UI_BLE.shackle_dialog.dismiss();
                        UI_BLE.unlocking_msg_show = false;
                    }
                } else if (!BLEService.isShackleOpened) {
                    BLEService.isShackleOpened = true;
                    UI_BLE.BLE_UI = 2;
                }
                if (value.length > 1 && value[1] == 48) {
                    MediaPlayer create = MediaPlayer.create(BLEService.this.getApplicationContext(), (int) R.raw.on);
                    create.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: com.egeetouch.egeetouch_manager.BLEService.3.2
                        @Override // android.media.MediaPlayer.OnCompletionListener
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            mediaPlayer.stop();
                            mediaPlayer.reset();
                            mediaPlayer.release();
                        }
                    });
                    create.start();
                    ((Vibrator) BLEService.this.getApplicationContext().getSystemService("vibrator")).vibrate(500L);
                }
                BLEService.isLockInChargingState = (value[0] & 2) == 2;
                Log.i("BLEService", "Hello characteristic:5: byte value:" + Arrays.toString(value) + " bit values:" + replace);
            } else if (bluetoothGattCharacteristic.getUuid().equals(BLEService.UUID_2)) {
                if (BLEService.selected_lock_model.equals("GT2500") || BLEService.selected_lock_model.equals("GT2550")) {
                    System.out.println("Hello checking the notification TYPE:" + (value[0] & UByte.MAX_VALUE) + " RESULT:" + (value[3] & UByte.MAX_VALUE));
                    BLEService.isLockInChargingState = (value[2] & 2) == 2;
                    if ((value[2] & 1) == 1) {
                        BLEService.shackleByPassOnce_isEnabled = false;
                        if (UI_BLE.shackle_dialog != null && UI_BLE.shackle_dialog.isShowing()) {
                            UI_BLE.shackle_dialog.dismiss();
                            UI_BLE.unlocking_msg_show = false;
                            BLEService.isShackleOpened = false;
                            BLEService.shackleByPassOnce_isEnabled = false;
                        }
                        if (UI_BLE.shackle_dialog != null && UI_BLE.shackle_dialog.isShowing()) {
                            UI_BLE.shackle_dialog.dismiss();
                            UI_BLE.unlocking_msg_show = false;
                            BLEService.isShackleOpened = false;
                        }
                    } else {
                        if (!BLEService.isShackleOpened) {
                            BLEService.isShackleOpened = true;
                            UI_BLE.BLE_UI = 2;
                        }
                        BLEService.selected_lock_state = false;
                    }
                    if ((value[0] & UByte.MAX_VALUE) == 98) {
                        BLEService.selected_lock_state = false;
                        BLEService.selected_lock_role = 0;
                        BLEService.this.lockInfo.setLockRole(0);
                        BLEService.Ble_Mode = 0;
                        UI_BLE.state = 4;
                        UI_BLE.BLE_UI = 2;
                    }
                    if ((value[0] & UByte.MAX_VALUE) == 105) {
                        BLEService.Ble_Mode = 0;
                        UI_BLE.BLE_UI = 7;
                    }
                    if ((value[0] & UByte.MAX_VALUE) == 198) {
                        BLEService.this.updateMasterTag_toFB(BLEService.convertTagID_toHexa(value[5], value[4]));
                        BLEService.Ble_Mode = 0;
                    }
                    if ((value[0] & UByte.MAX_VALUE) == 117) {
                        System.out.println("Hello checking the IP66 ADV_Time : RESULT :" + (value[3] & UByte.MAX_VALUE));
                        int i = 0;
                        for (int i2 = 13; i2 > 11; i2--) {
                            i = (i << 8) + (value[i2] & UByte.MAX_VALUE);
                        }
                        BLEService.AvailableTagCount = i;
                        int i3 = 0;
                        for (int i4 = 15; i4 > 13; i4--) {
                            i3 = (i3 << 8) + (value[i4] & UByte.MAX_VALUE);
                        }
                        BLEService.AvailableAuditCount = i3;
                        BLEService.audittrail_max_count = BLEService.AvailableAuditCount > 200 ? 200 : BLEService.AvailableAuditCount;
                        long j = 0;
                        for (int i5 = 19; i5 > 15; i5--) {
                            j = (j << 8) + (value[i5] & UByte.MAX_VALUE);
                        }
                        String format = new SimpleDateFormat("dd/MM/yyyy hh:mm a").format(Long.valueOf(1000 * j));
                        int i6 = 0;
                        for (int i7 = 9; i7 > 7; i7--) {
                            i6 = (i6 << 8) + (value[i7] & UByte.MAX_VALUE);
                        }
                        System.out.println("Hello checking the IP66 ADV_Time :  " + i6);
                        System.out.println("Hello checking the STATUS Details Audit.C : " + BLEService.AvailableAuditCount + " Tag.C : " + BLEService.AvailableTagCount + " TimeS: " + j + " date: " + format);
                        BLEService.this.lockInfo.setTotalTagAuditTrailCount(BLEService.AvailableAuditCount);
                        BLEService.this.lockInfo.setTotalTagCount(BLEService.AvailableTagCount);
                        BLEService.Ble_Mode = 0;
                    }
                    int i8 = value[3] & UByte.MAX_VALUE;
                    if ((value[0] & UByte.MAX_VALUE) == 160) {
                        if (i8 == 161) {
                            long j2 = 0;
                            for (int i9 = 11; i9 > 7; i9--) {
                                j2 = (j2 << 8) + (value[i9] & UByte.MAX_VALUE);
                            }
                            String convertTagID_toHexa = BLEService.convertTagID_toHexa(value[7], value[6]);
                            System.out.println("Hello checking the Audit READ Audit_Type : " + (value[4] & UByte.MAX_VALUE));
                            if ((value[4] & UByte.MAX_VALUE) == 17) {
                                BLEService.Ble_Mode = 0;
                                System.out.println("Hello checking the Audit trail READ Notify : timestamp:" + j2 + " tag:N.A  Audit deci:" + (value[6] & UByte.MAX_VALUE) + " INDEX:" + BLEService.audit_current_index_IP66 + " count:" + BLEService.audittrail_count_IP66);
                                System.out.println("Hello checking AUDIT INDEX if:" + BLEService.audit_current_index_IP66 + " count:" + BLEService.audittrail_count_IP66 + " MAXCount:" + BLEService.audittrail_max_count);
                                if (BLEService.audittrail_count_IP66 < BLEService.audittrail_max_count) {
                                    BLEService.this.updateIP66_auditTrail(j2, "N.A", value[6] & UByte.MAX_VALUE);
                                } else {
                                    BLEService.audit_current_index_IP66 = 0;
                                    BLEService.Ble_Mode = 506;
                                }
                            } else {
                                System.out.println("Hello checking the Audit trail READ : timestamp:" + j2 + " tag:" + convertTagID_toHexa + " Audit deci:0  INDEX:" + BLEService.audit_current_index_IP66 + " count:" + BLEService.audittrail_count_IP66);
                                System.out.println("Hello checking AUDIT INDEX else:" + BLEService.audit_current_index_IP66 + " count:" + BLEService.audittrail_count_IP66 + " MAXCount:" + BLEService.audittrail_max_count);
                                if (BLEService.audittrail_count_IP66 < BLEService.audittrail_max_count) {
                                    BLEService.Ble_Mode = 0;
                                    BLEService.this.updateIP66_auditTrail(j2, convertTagID_toHexa, 0);
                                } else {
                                    BLEService.audit_current_index_IP66 = 0;
                                    BLEService.Ble_Mode = 506;
                                }
                            }
                        } else if (i8 == 164) {
                            BLEService.AvailableAuditCount = 0;
                            if (!BLEService.isTagAuditTrail_updateOnly) {
                                UI_BLE.BLE_UI = 30;
                            } else {
                                BLEService.isTagAuditTrail_updateOnly = false;
                                UI_BLE.BLE_UI = 32;
                            }
                            BLEService.Ble_Mode = 0;
                        }
                    }
                    int i10 = value[3] & UByte.MAX_VALUE;
                    if ((value[0] & UByte.MAX_VALUE) == 80) {
                        if (i10 == 81) {
                            BLEService.this.addTag_IP66(i10);
                        } else if (i10 == 93) {
                            BLEService.this.addTag_IP66(i10);
                        } else if (i10 == 82) {
                            BLEService.this.deleteTag_IP66();
                        } else if (i10 == 91) {
                            BLEService.this.deleteTag_IP66();
                        }
                    }
                } else {
                    if (value[0] == -81 || value[0] == -49) {
                        BLEService.previous_lockStatus_notification = value[0];
                    }
                    if (BLEService.previous_lockStatus_notification == -49 && value[0] == 25 && !BLEService.isShackleOpened) {
                        BLEService.isShackleOpened = true;
                        UI_BLE.BLE_UI = 2;
                    }
                    if (value[0] == 64) {
                        if (!BLEService.isShackleOpened) {
                            BLEService.isShackleOpened = true;
                            UI_BLE.BLE_UI = 2;
                        }
                    } else if (value[0] == -52) {
                        BLEService.isLockInChargingState = false;
                    } else if (value[0] == -69) {
                        BLEService.isLockInChargingState = true;
                    }
                }
            }
            if (bluetoothGattCharacteristic.getUuid().equals(BLEService.UUID_2) && value[0] == 64 && !BLEService.isShackleOpened) {
                BLEService.isShackleOpened = true;
                UI_BLE.BLE_UI = 2;
            }
            if (bluetoothGattCharacteristic.getUuid().equals(BLEService.UUID_2) && value[0] == 89) {
                activity_change_pad_password.Pass_code_mode = 1;
            }
            if (value[0] == -49 || value[0] == -21) {
                BLEService.selected_lock_state = true;
                UI_BLE.state = 1;
                UI_BLE.BLE_UI = 14;
            }
            if (value[0] != 120 || MainActivity.scanning_new_lock) {
                return;
            }
            if (PreferenceManager.getDefaultSharedPreferences(BLEService.this.getApplication()).getBoolean("auto_unlocking_boolean", false)) {
                if (BLEService.selected_lock_state) {
                    MediaPlayer create2 = MediaPlayer.create(BLEService.this.getApplicationContext(), (int) R.raw.on);
                    create2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: com.egeetouch.egeetouch_manager.BLEService.3.3
                        @Override // android.media.MediaPlayer.OnCompletionListener
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            mediaPlayer.stop();
                            mediaPlayer.reset();
                            mediaPlayer.release();
                        }
                    });
                    create2.start();
                    Log.i("", "Unlocking the lock...");
                    BLEService.selected_lock_state = false;
                    UI_BLE.state = 2;
                    UI_BLE.BLE_UI = 14;
                    BLEService.Ble_Mode = 98;
                }
            } else {
                BLEService.Ble_Mode_before_disconnect = BLEService.Ble_Mode;
                BLEService.Ble_Mode = 0;
                UI_BLE.BLE_UI = 17;
            }
            System.out.println("Hello test auto-locking!!" + defaultSharedPreferences.getBoolean("auto_unlocking_boolean", false));
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onDescriptorRead(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
            super.onDescriptorRead(bluetoothGatt, bluetoothGattDescriptor, i);
            Log.i("", "onDescriptorRead");
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onDescriptorWrite(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
            super.onDescriptorWrite(bluetoothGatt, bluetoothGattDescriptor, i);
            Log.i("", "onDescriptorWrite");
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onReliableWriteCompleted(BluetoothGatt bluetoothGatt, int i) {
            super.onReliableWriteCompleted(bluetoothGatt, i);
            Log.i("", "onReliableWriteCompleted");
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onReadRemoteRssi(BluetoothGatt bluetoothGatt, int i, int i2) {
            super.onReadRemoteRssi(bluetoothGatt, i, i2);
            Log.i("", "onReadRemoteRssi");
            boolean unused = BLEService.got_new_rssi = true;
            BLEService.rssi_value = i;
        }
    };
    private Runnable mStopRunnable = new Runnable() { // from class: com.egeetouch.egeetouch_manager.BLEService.4
        @Override // java.lang.Runnable
        public void run() {
            BLEService.this.stopScan();
            MainActivity.no_lock_found = Boolean.valueOf(!BLEService.egeetouchDeviceFound);
            if (MainActivity.no_lock_found.booleanValue()) {
                BLEService.this.mhandler_task.removeCallbacks(BLEService.this.task);
            }
            System.out.println("HEY stop scan runnable is called");
        }
    };
    private Runnable mStartRunnable = new Runnable() { // from class: com.egeetouch.egeetouch_manager.BLEService.5
        @Override // java.lang.Runnable
        public void run() {
            BLEService.this.enable_BLE();
            BLEService.egeetouchDeviceFound = false;
            BLEService.this.startScan();
            System.out.println("HEY start scan runnable is called");
        }
    };
    public final Runnable runAddTimeout = new Runnable() { // from class: com.egeetouch.egeetouch_manager.BLEService.6
        @Override // java.lang.Runnable
        public void run() {
            BLEService.this.stopScan();
            BLEService.Ble_Mode = BLEService.disconnect;
            try {
                UI_BLE.pw_dialog.dismissWithAnimation();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (MainActivity.scanning_new_lock) {
                MainActivity.fragmentManager.popBackStack();
            }
        }
    };
    private ScanCallback mScanCallback = new ScanCallback() { // from class: com.egeetouch.egeetouch_manager.BLEService.7
        @Override // android.bluetooth.le.ScanCallback
        public void onScanResult(int i, ScanResult scanResult) {
            BluetoothDevice device = scanResult.getDevice();
            System.out.println("Hello ScanCallback!!");
            if (MainActivity.scanning_new_lock) {
                System.out.println("Hello Scanning new lock!! " + device.getName());
                if (device.getName() != null && device.getName().contains("eGeeTouch") && !BLEService.address_blacklist.contains(device.getAddress())) {
                    System.out.println("HEY we found " + device.getName());
                    BLEService.fullNameOfLock = device.getName();
                    System.out.println("HEY fullNameOfLock is " + BLEService.fullNameOfLock);
                    if (BLEService.fullNameOfLock.contains(" ")) {
                        StringTokenizer stringTokenizer = new StringTokenizer(BLEService.fullNameOfLock, " ");
                        try {
                            System.out.println("HEY firstNameOfLock is: " + stringTokenizer.nextToken());
                            String nextToken = stringTokenizer.nextToken();
                            System.out.println("HEY serial of lock is: " + nextToken);
                            BLEService.parsedIp45SerialNumber = nextToken;
                            System.out.println("HEY parsedIp45SerialNumber assigned the serial");
                            BLEService.isIp45Lock = true;
                        } catch (NoSuchElementException unused) {
                            System.out.println("HEY this is an IP45 without a serial number");
                        }
                    } else {
                        System.out.println("HEY this is not an IP45 with serial number, probably some other lock");
                        BLEService.isIp45Lock = false;
                    }
                    BLEService.this.stopScan();
                    BLEService.selected_lock_address = device.getAddress();
                    BLEService.connection_response = BLEService.this.connect(device.getAddress());
                    System.out.println("HEY connection_response says: " + BLEService.connection_response);
                } else if (device.getName() == null || !device.getName().contains("EGT")) {
                } else {
                    new StringTokenizer(BLEService.fullNameOfLock, " ");
                    device.getName();
                    BLEService.parsedIp45SerialNumber = "IP0066";
                    System.out.println("Hello checking the ip66 serial : IP0066");
                    BLEService.isIp45Lock = true;
                    if (BLEService.selectedLockIP45Serial.equals(BLEService.parsedIp45SerialNumber)) {
                        BLEService.selected_lock_address = device.getAddress();
                        BLEService.connection_response = BLEService.this.connect(device.getAddress());
                        System.out.println("Hello checking the ip66 connection problem! 1selectedLockIP45Serial:" + BLEService.selectedLockIP45Serial + " parsedIp45SerialNumber:" + BLEService.parsedIp45SerialNumber);
                        BLEService.this.stopScan();
                    } else if (UI_BLE.add_lock_mode) {
                        BLEService.selected_lock_address = device.getAddress();
                        BLEService.connection_response = BLEService.this.connect(device.getAddress());
                        BLEService.this.stopScan();
                        System.out.println("Hello checking the ip66 connection problem! 2 selectedLockIP45Serial:" + BLEService.selectedLockIP45Serial + " parsedIp45SerialNumber:" + BLEService.parsedIp45SerialNumber);
                    }
                }
            } else if (BLEService.egeetouchDeviceFound) {
            } else {
                System.out.println("Hello existing new lock!!");
                if (device.getName() != null && device.getName().contains("eGeeTouch")) {
                    System.out.println("HEY we found this " + device.getName());
                    BLEService.fullNameOfLock = device.getName();
                    System.out.println("HEY fullNameOfLock is called" + BLEService.fullNameOfLock);
                    if (BLEService.fullNameOfLock.contains(" ")) {
                        StringTokenizer stringTokenizer2 = new StringTokenizer(BLEService.fullNameOfLock, " ");
                        try {
                            System.out.println("HEY firstNameOfLock is: " + stringTokenizer2.nextToken());
                            String nextToken2 = stringTokenizer2.nextToken();
                            System.out.println("HEY serial of lock is: " + nextToken2);
                            BLEService.parsedIp45SerialNumber = nextToken2;
                            System.out.println("HEY parsedIp45SerialNumber assigned the serial");
                            BLEService.isIp45Lock = true;
                        } catch (NoSuchElementException unused2) {
                            System.out.println("HEY this is an IP45 without a serial number");
                        }
                    } else {
                        System.out.println("HEY this is not an IP45 with serial number, probably some other lock");
                        BLEService.isIp45Lock = false;
                    }
                    BLEService.this.stopScan();
                    BLEService.selected_lock_address = device.getAddress();
                    BLEService.connection_response = BLEService.this.connect(device.getAddress());
                    System.out.println("HEY connection_response says: " + BLEService.connection_response);
                } else if (device.getName() != null && device.getName().contains("EGT")) {
                    new StringTokenizer(BLEService.fullNameOfLock, " ");
                    device.getName();
                    BLEService.parsedIp45SerialNumber = "IP0066";
                    System.out.println("Hello checking the ip66 serial : IP0066");
                    BLEService.isIp45Lock = true;
                    if (BLEService.selectedLockIP45Serial.equals(BLEService.parsedIp45SerialNumber)) {
                        if (Build.VERSION.SDK_INT >= 21) {
                            BLEService.this.mLEScanner.stopScan(BLEService.this.mScanCallback);
                        } else {
                            BLEService.this.mBluetoothAdapter.stopLeScan(BLEService.this);
                        }
                        BLEService.this.stopScan();
                        BLEService.selected_lock_address = device.getAddress();
                        BLEService.connection_response = BLEService.this.connect(device.getAddress());
                        System.out.println("Hello checking the bluetooth connection problem! 3 connection_response : " + BLEService.connection_response + " selectedLockIP45Serial:" + BLEService.selectedLockIP45Serial + " parsedIp45SerialNumber:" + BLEService.parsedIp45SerialNumber);
                    } else if (UI_BLE.add_lock_mode) {
                        BLEService.this.stopScan();
                        BLEService.selected_lock_address = device.getAddress();
                        BLEService.connection_response = BLEService.this.connect(device.getAddress());
                        System.out.println("Hello checking the bluetooth connection problem! 4 connection_response : " + BLEService.connection_response + "selectedLockIP45Serial:" + BLEService.selectedLockIP45Serial + "parsedIp45SerialNumber:" + BLEService.parsedIp45SerialNumber + " selectedLockIP45Serial == parsedIp45SerialNumber:" + BLEService.selectedLockIP45Serial.equals(BLEService.parsedIp45SerialNumber));
                    }
                    System.out.println("HEY connection_response says:2 " + BLEService.connection_response);
                }
                if (device.getName() == null || !device.getName().contains("eGeeTouch")) {
                    return;
                }
                if (BLEService.parsedIp45SerialNumber.equals(BLEService.selectedLockIP45Serial)) {
                    System.out.println("HEY ITS THE SAME YOU CAN PROCEED");
                    System.out.println("HEY parsedIp45SerialNumber is " + BLEService.parsedIp45SerialNumber);
                    System.out.println("HEY selectedLockIP45Serial is " + BLEService.selectedLockIP45Serial);
                    System.out.println("HEY selected_lock_address is " + BLEService.selected_lock_address);
                    if (BLEService.selected_lock_address.equals(device.getAddress().toString())) {
                        BLEService.this.stopScan();
                        BLEService.connection_response = BLEService.this.connect(device.getAddress());
                        System.out.println("HEY we are connecting to " + device.getAddress() + " and connection response is " + BLEService.connection_response);
                    }
                    BLEService.egeetouchDeviceFound = true;
                    Toast.makeText(MainActivity.context, BLEService.parsedIp45SerialNumber + " Lock Serial detected\nPlease wait...", 1).show();
                    return;
                }
                if (UI_BLE.pd_pls_wait != null && UI_BLE.pd_pls_wait.isShowing()) {
                    BLEService.this.stopScan();
                    BLEService.Ble_Mode = BLEService.disconnect;
                    BLEService.this.disconnect();
                    UI_BLE.pd_pls_wait.dismiss();
                    new SweetAlertDialog(MainActivity.context, 0).setTitleText(BLEService.this.getString(R.string.oops)).setContentText(BLEService.this.getString(R.string.you_must_have_connected_to_the_wrong_lock, new Object[]{BLEService.selectedLockIP45Serial, BLEService.parsedIp45SerialNumber})).setConfirmText("OK").show();
                }
                System.out.println("HEY ITS NOT THE SAME SO CANNOT PROCEED");
                System.out.println("HEY parsedIp45SerialNumber is " + BLEService.parsedIp45SerialNumber);
                System.out.println("HEY selectedLockIP45Serial is " + BLEService.selectedLockIP45Serial);
            }
        }
    };
    final Runnable task = new Runnable() { // from class: com.egeetouch.egeetouch_manager.BLEService.8
        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Code restructure failed: missing block: B:949:0x21ac, code lost:
            if (r0.isScreenOn(r0.getApplicationContext()) == false) goto L107;
         */
        /* JADX WARN: Removed duplicated region for block: B:153:0x0389  */
        /* JADX WARN: Removed duplicated region for block: B:557:0x13ff  */
        /* JADX WARN: Removed duplicated region for block: B:790:0x1c9c  */
        /* JADX WARN: Removed duplicated region for block: B:800:0x1cdc  */
        /* JADX WARN: Removed duplicated region for block: B:981:0x22d0  */
        /* JADX WARN: Removed duplicated region for block: B:985:0x22e3  */
        /* JADX WARN: Type inference failed for: r2v78 */
        /* JADX WARN: Type inference failed for: r2v79, types: [int, boolean] */
        /* JADX WARN: Type inference failed for: r2v83 */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void run() {
            boolean r2;
            ?? r2;
            int r0;
            byte[] r0;
            int r6;
            byte[] r0;
            char r2;
            if (com.egeetouch.egeetouch_manager.BLEService.mConnectionState == 2) {
                java.lang.System.out.println("Hello Ble_Mode:" + com.egeetouch.egeetouch_manager.BLEService.Ble_Mode);
                int r0 = com.egeetouch.egeetouch_manager.BLEService.Ble_Mode;
                byte r10 = 0;
                if (r0 != 24) {
                    if (r0 != 25) {
                        if (r0 != 112) {
                            if (r0 == 113) {
                                if (com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1 != null && com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt != null) {
                                    byte[] r0 = new byte[7];
                                    r0[0] = 113;
                                    if ((com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2100") && java.lang.Float.valueOf(com.egeetouch.egeetouch_manager.BLEService.selected_lock_version).floatValue() > 1.8d) || com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT5100") || com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT5107") || com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT5109") || com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT5300")) {
                                        byte[] r3 = new byte[4];
                                        long r4 = (java.lang.System.currentTimeMillis() / 1000) + 3;
                                        for (int r2 = 3; r2 >= 0; r2--) {
                                            r3[r2] = (byte) (r4 & 255);
                                            r4 >>= 8;
                                        }
                                        r0[1] = r3[0];
                                        r0[2] = r3[1];
                                        r0[3] = r3[2];
                                        r0[4] = r3[3];
                                        r0[5] = 0;
                                        r0[6] = 0;
                                    } else if (com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2500") || com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2550")) {
                                        byte r20 = 0;
                                        r0[0] = 48;
                                        r0[1] = 6;
                                        int r2 = 0;
                                        while (r2 < 7) {
                                            r0[r2] = r20;
                                            r2++;
                                            r20 = 0;
                                        }
                                    } else {
                                        byte[] r2 = com.egeetouch.egeetouch_manager.BLEService.selected_lock_password.getBytes();
                                        r0[1] = r2[0];
                                        r0[2] = r2[1];
                                        r0[3] = r2[2];
                                        r0[4] = r2[3];
                                        r0[5] = r2[4];
                                        r0[6] = r2[5];
                                    }
                                    com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1.setValue(r0);
                                    java.lang.System.out.println("Hello checking the lock Param value sent : " + java.util.Arrays.toString(r0));
                                    if (com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt.writeCharacteristic(com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1)) {
                                        java.lang.System.out.println("Hello checking the lock Param sent Succecc!!");
                                        com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = com.egeetouch.egeetouch_manager.BLEService.Read_response_Lock_Setting_Param;
                                    }
                                }
                            } else {
                                switch (r0) {
                                    case 16:
                                    case 17:
                                    case 20:
                                        if (com.egeetouch.egeetouch_manager.BLEService.this.characteristic_2 != null && com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt != null) {
                                            com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt.readCharacteristic(com.egeetouch.egeetouch_manager.BLEService.this.characteristic_2);
                                            break;
                                        }
                                        break;
                                    case 18:
                                    case 22:
                                        if (com.egeetouch.egeetouch_manager.BLEService.this.characteristic_3 != null && com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt != null) {
                                            try {
                                                if (com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt.readCharacteristic(com.egeetouch.egeetouch_manager.BLEService.this.characteristic_3)) {
                                                    java.lang.System.out.println("Hello checking the lock Param char3");
                                                    break;
                                                }
                                            } catch (java.lang.Exception r0) {
                                                android.util.Log.i("TAG", "Exception :" + r0);
                                                break;
                                            }
                                        }
                                        break;
                                    case 19:
                                        java.lang.System.out.println("HEY from BLEService case Add_Tag_number is getting called");
                                        if (com.egeetouch.egeetouch_manager.BLEService.this.characteristic_4 != null && com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt != null) {
                                            byte[] r2 = new byte[16];
                                            if (com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2100") && (com.egeetouch.egeetouch_manager.BLEService.selected_lock_version.equals("1.80") || java.lang.Float.valueOf(com.egeetouch.egeetouch_manager.BLEService.selected_lock_version).floatValue() > 1.8d)) {
                                                byte r5 = 0;
                                                r2[0] = 80;
                                                r2[1] = -92;
                                                r2[2] = (byte) ((com.egeetouch.egeetouch_manager.BLEService.totalTagsInLock >> 8) & 255);
                                                r2[3] = (byte) (com.egeetouch.egeetouch_manager.BLEService.totalTagsInLock & 255);
                                                r2[4] = (byte) com.egeetouch.egeetouch_manager.BLEService.highestTagPage;
                                                int r0 = 5;
                                                int r3 = 16;
                                                while (r0 < r3) {
                                                    r2[r0] = r5;
                                                    r0++;
                                                    r3 = 16;
                                                    r5 = 0;
                                                }
                                            } else {
                                                if (com.egeetouch.egeetouch_manager.BLEService.Tag_number >= 20) {
                                                    com.egeetouch.egeetouch_manager.BLEService.Tag_number = 20;
                                                }
                                                byte r4 = 0;
                                                r2[0] = 80;
                                                r2[1] = -92;
                                                r2[2] = (byte) com.egeetouch.egeetouch_manager.BLEService.Tag_number;
                                                int r0 = 3;
                                                int r3 = 16;
                                                while (r0 < r3) {
                                                    r2[r0] = r4;
                                                    r0++;
                                                    r3 = 16;
                                                    r4 = 0;
                                                }
                                            }
                                            com.egeetouch.egeetouch_manager.BLEService.this.characteristic_4.setValue(r2);
                                            if (com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt.writeCharacteristic(com.egeetouch.egeetouch_manager.BLEService.this.characteristic_4)) {
                                                com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 20;
                                                break;
                                            }
                                        }
                                        break;
                                    case 21:
                                        if (com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1 != null && com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt != null) {
                                            byte[] r0 = new byte[7];
                                            byte r3 = 0;
                                            r0[0] = 100;
                                            r0[1] = -92;
                                            int r2 = 2;
                                            while (r2 < 7) {
                                                r0[r2] = r3;
                                                r2++;
                                                r3 = 0;
                                            }
                                            com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1.setValue(r0);
                                            if (com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt.writeCharacteristic(com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1)) {
                                                com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 22;
                                                break;
                                            }
                                        }
                                        break;
                                    default:
                                        switch (r0) {
                                            case 32:
                                            case 33:
                                            case 37:
                                            case 57:
                                            case 71:
                                            case com.egeetouch.egeetouch_manager.BLEService.Read_response_update_reconnecting_period /* 165 */:
                                            case com.egeetouch.egeetouch_manager.BLEService.Read_Response_enter_low_power_mode /* 166 */:
                                            case com.egeetouch.egeetouch_manager.BLEService.Read_Response_quit_low_power_mode /* 167 */:
                                            case com.egeetouch.egeetouch_manager.BLEService.Read_response_Admin_Password_new_lock /* 178 */:
                                            case com.egeetouch.egeetouch_manager.BLEService.Read_response_timestamp /* 228 */:
                                            case com.egeetouch.egeetouch_manager.BLEService.Read_response_clear_tag_AuditTrail /* 233 */:
                                            case com.egeetouch.egeetouch_manager.BLEService.ReadResponse_AutoLocking_Settings /* 237 */:
                                            case com.egeetouch.egeetouch_manager.BLEService.ReadResponse_ShackleBypass /* 240 */:
                                            case com.egeetouch.egeetouch_manager.BLEService.ReadResponse_ChangePasscode /* 245 */:
                                            case com.egeetouch.egeetouch_manager.BLEService.ReadResponse_syncPasscode /* 258 */:
                                            case 600:
                                            case 601:
                                            case 602:
                                            case 603:
                                            case 604:
                                            case 605:
                                            case 606:
                                            case 607:
                                            case 608:
                                            case 609:
                                            case 610:
                                                break;
                                            case 34:
                                                if (com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1 != null && com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt != null) {
                                                    byte[] r0 = new byte[7];
                                                    byte r3 = 0;
                                                    r0[0] = 102;
                                                    r0[1] = -90;
                                                    int r2 = 2;
                                                    while (r2 < 7) {
                                                        r0[r2] = r3;
                                                        r2++;
                                                        r3 = 0;
                                                    }
                                                    com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1.setValue(r0);
                                                    if (com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt.writeCharacteristic(com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1)) {
                                                        com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 35;
                                                        break;
                                                    }
                                                }
                                                break;
                                            case 35:
                                            case 38:
                                            case 39:
                                            case com.egeetouch.egeetouch_manager.BLEService.Read_Response_read_serial /* 168 */:
                                            case com.egeetouch.egeetouch_manager.BLEService.Read_response_Lock_Setting_Param /* 171 */:
                                            case com.egeetouch.egeetouch_manager.BLEService.Read_response_tag_AuditTrail_number /* 229 */:
                                            case com.egeetouch.egeetouch_manager.BLEService.Read_response_tag_AuditTrail /* 231 */:
                                            case com.egeetouch.egeetouch_manager.BLEService.ReadResponse_scanTagId /* 236 */:
                                            case com.egeetouch.egeetouch_manager.BLEService.ReadResponse_Master_Tag /* 242 */:
                                            case com.egeetouch.egeetouch_manager.BLEService.Read_response_lotoBatteryRecords /* 244 */:
                                            case 250:
                                            case com.egeetouch.egeetouch_manager.BLEService.ReadResponse_passcodeAudit /* 252 */:
                                            case com.egeetouch.egeetouch_manager.BLEService.ReadResponse_clear_passcodeAuditTrail /* 254 */:
                                            case com.egeetouch.egeetouch_manager.BLEService.Read_Response_lotoEnable_auditTrail /* 266 */:
                                            case com.egeetouch.egeetouch_manager.BLEService.coresponse_MasterTag /* 665 */:
                                                break;
                                            case 36:
                                                if (com.egeetouch.egeetouch_manager.BLEService.this.characteristic_4 != null && com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt != null) {
                                                    int r0 = 16;
                                                    byte[] r2 = new byte[16];
                                                    byte r4 = 0;
                                                    r2[0] = 81;
                                                    r2[1] = -90;
                                                    r2[2] = (byte) com.egeetouch.egeetouch_manager.BLEService.User_number;
                                                    int r3 = 3;
                                                    while (r3 < r0) {
                                                        r2[r3] = r4;
                                                        r3++;
                                                        r0 = 16;
                                                        r4 = 0;
                                                    }
                                                    com.egeetouch.egeetouch_manager.BLEService.this.characteristic_4.setValue(r2);
                                                    if (com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt.writeCharacteristic(com.egeetouch.egeetouch_manager.BLEService.this.characteristic_4)) {
                                                        com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 37;
                                                        break;
                                                    }
                                                }
                                                break;
                                            case 40:
                                                if (com.egeetouch.egeetouch_manager.BLEService.this.characteristic_4 != null && com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt != null) {
                                                    byte[] r0 = new byte[16];
                                                    r0[0] = 82;
                                                    r0[1] = -83;
                                                    if (com.egeetouch.egeetouch_manager.BLEService.now_admin == null) {
                                                        com.egeetouch.egeetouch_manager.BLEService.now_admin = new android.text.format.Time();
                                                    }
                                                    com.egeetouch.egeetouch_manager.BLEService.now_admin.setToNow();
                                                    r0[2] = (byte) com.egeetouch.egeetouch_manager.BLEService.now_admin.hour;
                                                    r0[3] = (byte) com.egeetouch.egeetouch_manager.BLEService.now_admin.minute;
                                                    r0[4] = (byte) com.egeetouch.egeetouch_manager.BLEService.now_admin.second;
                                                    r0[5] = (byte) com.egeetouch.egeetouch_manager.BLEService.now_admin.monthDay;
                                                    r0[6] = (byte) (com.egeetouch.egeetouch_manager.BLEService.now_admin.month + 1);
                                                    r0[7] = (byte) (com.egeetouch.egeetouch_manager.BLEService.now_admin.year - 2000);
                                                    byte[] r2 = new byte[8];
                                                    java.nio.ByteBuffer.wrap(r2).putDouble(com.egeetouch.egeetouch_manager.BLEService.selected_lock_address_latitude);
                                                    r0[8] = r2[7];
                                                    r0[9] = r2[6];
                                                    r0[10] = r2[5];
                                                    r0[11] = r2[4];
                                                    r0[12] = r2[3];
                                                    r0[13] = r2[2];
                                                    r0[14] = r2[1];
                                                    r0[15] = r2[0];
                                                    com.egeetouch.egeetouch_manager.BLEService.this.characteristic_4.setValue(r0);
                                                    if (com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt.writeCharacteristic(com.egeetouch.egeetouch_manager.BLEService.this.characteristic_4)) {
                                                        com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 48;
                                                        break;
                                                    }
                                                }
                                                break;
                                            case 41:
                                                if (com.egeetouch.egeetouch_manager.BLEService.this.characteristic_4 != null && com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt != null) {
                                                    byte[] r0 = new byte[16];
                                                    r0[0] = 82;
                                                    r0[1] = -54;
                                                    byte[] r2 = new byte[8];
                                                    java.nio.ByteBuffer.wrap(r2).putDouble(com.egeetouch.egeetouch_manager.BLEService.selected_lock_address_longitude);
                                                    r0[2] = r2[7];
                                                    r0[3] = r2[6];
                                                    r0[4] = r2[5];
                                                    r0[5] = r2[4];
                                                    r0[6] = r2[3];
                                                    r0[7] = r2[2];
                                                    r0[8] = r2[1];
                                                    r0[9] = r2[0];
                                                    if (com.egeetouch.egeetouch_manager.BLEService.selected_lock_role == 0) {
                                                        r0[10] = 0;
                                                    } else if (com.egeetouch.egeetouch_manager.BLEService.selected_lock_role == 1) {
                                                        r0[10] = (byte) (com.egeetouch.egeetouch_manager.BLEService.current_user_index & 255);
                                                    }
                                                    com.egeetouch.egeetouch_manager.BLEService.this.characteristic_4.setValue(r0);
                                                    if (com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt.writeCharacteristic(com.egeetouch.egeetouch_manager.BLEService.this.characteristic_4)) {
                                                        com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 49;
                                                        android.util.Log.i("TAG", "Read_Response_Add_Audit_Trail_2");
                                                        break;
                                                    }
                                                }
                                                break;
                                            case 80:
                                                java.lang.System.out.println("HEY Add_Tag case getting called from BLEService");
                                                byte[] r2 = new byte[16];
                                                android.database.Cursor r0 = com.egeetouch.egeetouch_manager.DatabaseVariable.db_tag.rawQuery("SELECT * FROM Tag WHERE Lock = " + android.database.DatabaseUtils.sqlEscapeString(com.egeetouch.egeetouch_manager.BLEService.selected_lock_name), null);
                                                com.egeetouch.egeetouch_manager.BLEService.Tag_number = r0.getCount();
                                                com.egeetouch.egeetouch_manager.BLEService.Tag_loop_number++;
                                                if (com.egeetouch.egeetouch_manager.BLEService.Tag_loop_number <= com.egeetouch.egeetouch_manager.BLEService.Tag_number) {
                                                    java.lang.String[] r3 = new java.lang.String[35];
                                                    java.lang.String[] r4 = new java.lang.String[35];
                                                    int r5 = -1;
                                                    while (r0.moveToNext()) {
                                                        r5++;
                                                        r3[r5] = r0.getString(1);
                                                        r4[r5] = r0.getString(0);
                                                    }
                                                    java.lang.String[] r0 = r3[com.egeetouch.egeetouch_manager.BLEService.Tag_loop_number - 1].split(" ");
                                                    for (int r3 = 1; r3 < 3; r3++) {
                                                        r2[r3] = com.egeetouch.egeetouch_manager.Helper_Math.ConvertStringToHexByte(r0[r3]);
                                                    }
                                                    if (com.egeetouch.egeetouch_manager.BLEService.this.characteristic_4 != null && com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt != null) {
                                                        byte[] r3 = new byte[16];
                                                        r3[0] = 80;
                                                        r3[1] = (byte) com.egeetouch.egeetouch_manager.BLEService.Tag_loop_number;
                                                        r3[2] = r2[1];
                                                        r3[3] = r2[2];
                                                        for (int r0 = 0; r0 < r4[com.egeetouch.egeetouch_manager.BLEService.Tag_loop_number - 1].length(); r0++) {
                                                            r3[r0 + 4] = (byte) r4[com.egeetouch.egeetouch_manager.BLEService.Tag_loop_number - 1].charAt(r0);
                                                        }
                                                        for (int r0 = r4[com.egeetouch.egeetouch_manager.BLEService.Tag_loop_number - 1].length() + 4; r0 < 16; r0++) {
                                                            r3[r0] = 0;
                                                        }
                                                        com.egeetouch.egeetouch_manager.BLEService.Add_tag_fail = r3;
                                                        com.egeetouch.egeetouch_manager.BLEService.this.characteristic_4.setValue(r3);
                                                        if (com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt.writeCharacteristic(com.egeetouch.egeetouch_manager.BLEService.this.characteristic_4)) {
                                                            com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 17;
                                                            break;
                                                        }
                                                    }
                                                } else if (com.egeetouch.egeetouch_manager.BLEService.this.characteristic_4 != null && com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt != null) {
                                                    int r0 = 16;
                                                    byte[] r2 = new byte[16];
                                                    byte r4 = 0;
                                                    r2[0] = 80;
                                                    r2[1] = (byte) com.egeetouch.egeetouch_manager.BLEService.Tag_loop_number;
                                                    int r3 = 2;
                                                    while (r3 < r0) {
                                                        r2[r3] = r4;
                                                        r3++;
                                                        r0 = 16;
                                                        r4 = 0;
                                                    }
                                                    com.egeetouch.egeetouch_manager.BLEService.Add_tag_fail = r2;
                                                    com.egeetouch.egeetouch_manager.BLEService.this.characteristic_4.setValue(r2);
                                                    if (com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt.writeCharacteristic(com.egeetouch.egeetouch_manager.BLEService.this.characteristic_4)) {
                                                        com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 17;
                                                        break;
                                                    }
                                                }
                                                break;
                                            case 83:
                                                if (com.egeetouch.egeetouch_manager.BLEService.this.characteristic_4 != null && com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt != null) {
                                                    int r0 = 16;
                                                    byte[] r2 = new byte[16];
                                                    byte r4 = 0;
                                                    r2[0] = 82;
                                                    int r3 = 1;
                                                    while (r3 < r0) {
                                                        r2[r3] = r4;
                                                        r3++;
                                                        r0 = 16;
                                                        r4 = 0;
                                                    }
                                                    com.egeetouch.egeetouch_manager.BLEService.this.characteristic_4.setValue(r2);
                                                    if (com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt.writeCharacteristic(com.egeetouch.egeetouch_manager.BLEService.this.characteristic_4)) {
                                                        com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = com.egeetouch.egeetouch_manager.BLEService.Read_Response_read_serial;
                                                        break;
                                                    }
                                                }
                                                break;
                                            case 105:
                                                if (com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1 != null && com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt != null) {
                                                    if (com.egeetouch.egeetouch_manager.BLEService.now_admin == null) {
                                                        com.egeetouch.egeetouch_manager.BLEService.now_admin = new android.text.format.Time();
                                                    }
                                                    com.egeetouch.egeetouch_manager.BLEService.now_admin.setToNow();
                                                    java.lang.System.out.println("Hello checking the locking -----------");
                                                    byte[] r0 = new byte[7];
                                                    r0[0] = 105;
                                                    byte[] r3 = new byte[4];
                                                    if ((com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2100") && java.lang.Float.valueOf(com.egeetouch.egeetouch_manager.BLEService.selected_lock_version).floatValue() > 1.8d) || com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT5100") || com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT5107") || com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT5109") || com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT5300")) {
                                                        long r4 = (java.lang.System.currentTimeMillis() / 1000) + 3;
                                                        long r11 = r4;
                                                        int r2 = 3;
                                                        while (r2 >= 0) {
                                                            r3[r2] = (byte) (r11 & 255);
                                                            r11 >>= 8;
                                                            r2--;
                                                            r10 = 0;
                                                        }
                                                        byte r2 = r10;
                                                        r0[1] = r3[r2];
                                                        r0[2] = r3[1];
                                                        r0[3] = r3[2];
                                                        r0[4] = r3[3];
                                                        r0[5] = r2;
                                                        r0[6] = r2;
                                                        java.lang.System.out.println("Hello checking the time stamp raw value locking:" + r4);
                                                    } else if (com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2500") || com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2550")) {
                                                        r0[0] = 105;
                                                        r0[1] = 4;
                                                        r0[2] = r3[0];
                                                        r0[3] = r3[1];
                                                        r0[4] = r3[2];
                                                        r0[5] = r3[3];
                                                    } else {
                                                        r0[1] = 0;
                                                        r0[2] = 0;
                                                        r0[3] = 0;
                                                        r0[4] = 0;
                                                        r0[5] = 0;
                                                        r0[6] = 0;
                                                    }
                                                    java.lang.System.out.println("CMDCMD byte array ref: " + java.util.Arrays.toString(r0));
                                                    com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1.setValue(r0);
                                                    if (com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt != null && com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt.writeCharacteristic(com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1)) {
                                                        com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 68;
                                                        break;
                                                    }
                                                }
                                                break;
                                            case com.egeetouch.egeetouch_manager.BLEService.Request_old_passcode /* 144 */:
                                                if (com.egeetouch.egeetouch_manager.BLEService.this.characteristic_4 != null && com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothAdapter != null) {
                                                    byte[] r2 = new byte[16];
                                                    r2[0] = 90;
                                                    r2[1] = 0;
                                                    int r4 = 2;
                                                    for (int r0 = 16; r4 < r0; r0 = 16) {
                                                        r2[r4] = 0;
                                                        r4++;
                                                    }
                                                    com.egeetouch.egeetouch_manager.BLEService.Response_old_passcode_boolean = true;
                                                    com.egeetouch.egeetouch_manager.BLEService.this.characteristic_4.setValue(r2);
                                                    if (com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt.writeCharacteristic(com.egeetouch.egeetouch_manager.BLEService.this.characteristic_4)) {
                                                        com.egeetouch.egeetouch_manager.BLEService.Response_old_passcode_boolean = true;
                                                        com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = com.egeetouch.egeetouch_manager.BLEService.Read_Response_read_serial;
                                                        android.util.Log.i("TAG", "Request_old_passcode!!");
                                                        break;
                                                    }
                                                }
                                                break;
                                            case com.egeetouch.egeetouch_manager.BLEService.AutoLocking_Settings /* 153 */:
                                                if (com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1 != null && com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt != null) {
                                                    byte[] r0 = new byte[7];
                                                    r0[0] = -103;
                                                    r0[1] = 0;
                                                    r0[2] = com.egeetouch.egeetouch_manager.BLEService.AutoLockingMode ? (byte) -52 : (byte) 17;
                                                    r0[3] = 0;
                                                    r0[4] = 0;
                                                    r0[5] = 0;
                                                    r0[6] = 0;
                                                    com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1.setValue(r0);
                                                    java.lang.System.out.println("Hello checking AutoLocking_Settings  data sent from char1:" + java.util.Arrays.toString(r0));
                                                    if (com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt.writeCharacteristic(com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1)) {
                                                        com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = com.egeetouch.egeetouch_manager.BLEService.ReadResponse_AutoLocking_Settings;
                                                        break;
                                                    }
                                                }
                                                break;
                                            case com.egeetouch.egeetouch_manager.BLEService.Request_ChangePasscode /* 173 */:
                                                if (com.egeetouch.egeetouch_manager.BLEService.this.characteristic_4 != null && com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt != null) {
                                                    int r0 = com.egeetouch.egeetouch_manager.BLEService.this.lotoInfo.getUpdateMode();
                                                    byte[] r3 = new byte[16];
                                                    r3[0] = -51;
                                                    r3[1] = (byte) r0;
                                                    if (r0 == 222) {
                                                        int r0 = com.egeetouch.egeetouch_manager.BLEService.this.lotoInfo.deleteIndex;
                                                        int r2 = com.egeetouch.egeetouch_manager.BLEService.this.convertPasscodeToDecimal("111111");
                                                        java.lang.System.out.println("Hello checking the Passcode Delete deci:" + r2 + " passcode:" + com.egeetouch.egeetouch_manager.BLEService.new_passcode);
                                                        byte[] r5 = new byte[2];
                                                        for (int r4 = 0; r4 <= 1; r4++) {
                                                            r5[r4] = (byte) (r2 & 255);
                                                            r2 >>= 8;
                                                        }
                                                        r3[2] = (byte) r0;
                                                        r3[3] = 6;
                                                        r3[4] = r5[0];
                                                        r3[5] = r5[1];
                                                        for (int r0 = 6; r0 < 16; r0++) {
                                                            r3[r0] = 0;
                                                        }
                                                        java.lang.System.out.println("Hello checking the Passcode Delete Sent !" + java.util.Arrays.toString(r3));
                                                    } else {
                                                        int r0 = com.egeetouch.egeetouch_manager.BLEService.this.lotoInfo.nextAvailabeIndex;
                                                        int r2 = com.egeetouch.egeetouch_manager.BLEService.this.convertPasscodeToDecimal(com.egeetouch.egeetouch_manager.BLEService.new_passcode);
                                                        java.lang.System.out.println("Hello checking the Passcode Add deci:" + r2 + " passcode:" + com.egeetouch.egeetouch_manager.BLEService.new_passcode);
                                                        byte[] r5 = new byte[2];
                                                        for (int r4 = 0; r4 <= 1; r4++) {
                                                            r5[r4] = (byte) (r2 & 255);
                                                            r2 >>= 8;
                                                        }
                                                        r3[2] = (byte) r0;
                                                        r3[3] = 6;
                                                        r3[4] = r5[0];
                                                        r3[5] = r5[1];
                                                        for (int r0 = 6; r0 < 16; r0++) {
                                                            r3[r0] = 0;
                                                        }
                                                    }
                                                    com.egeetouch.egeetouch_manager.BLEService.this.characteristic_4.setValue(r3);
                                                    java.lang.System.out.println("Hello checking the Passcode change Sent !" + java.util.Arrays.toString(r3));
                                                    if (com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt.writeCharacteristic(com.egeetouch.egeetouch_manager.BLEService.this.characteristic_4)) {
                                                        java.lang.System.out.print("Hello checking the Passcode change sent to char4");
                                                        com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = com.egeetouch.egeetouch_manager.BLEService.ReadResponse_ChangePasscode;
                                                        break;
                                                    }
                                                }
                                                break;
                                            case com.egeetouch.egeetouch_manager.BLEService.Verify_Admin_Password_new_lock /* 176 */:
                                                java.lang.System.out.println("Hello checking the lock ver 7");
                                                java.lang.System.out.println("HEY Verify_Admin_Password getting called");
                                                java.lang.System.out.println("HEY from Verify_Admin_Password or Verify_Admin_Password_new_lock new_password_verification is " + com.egeetouch.egeetouch_manager.BLEService.new_password_verification);
                                                if (com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1 != null && com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt != null) {
                                                    byte[] r0 = new byte[7];
                                                    r0[0] = 97;
                                                    if (com.egeetouch.egeetouch_manager.BLEService.Ble_Mode == 176) {
                                                        com.egeetouch.egeetouch_manager.BLEService.new_password_verification = true;
                                                        java.lang.System.out.println("HEY new_password_verification getting set to TRUE");
                                                        if (com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2500") || com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2550")) {
                                                            r0 = new byte[]{97, 6, 49, 50, 51, 52, 53, 54};
                                                        } else {
                                                            r0[1] = 49;
                                                            r0[2] = 50;
                                                            r0[3] = 51;
                                                            r0[4] = 52;
                                                            r0[5] = 53;
                                                            r0[6] = 54;
                                                        }
                                                    } else {
                                                        com.egeetouch.egeetouch_manager.BLEService.new_password_verification = false;
                                                        java.lang.System.out.println("HEY new_password_verification getting set to FALSE");
                                                        byte[] r2 = com.egeetouch.egeetouch_manager.BLEService.selected_lock_password.getBytes();
                                                        if (com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2500") || com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2550")) {
                                                            r0 = new byte[]{97, 6, r2[0], r2[1], r2[2], r2[3], r2[4], r2[5]};
                                                        } else {
                                                            r0[1] = r2[0];
                                                            r0[2] = r2[1];
                                                            r0[3] = r2[2];
                                                            r0[4] = r2[3];
                                                            r0[5] = r2[4];
                                                            r0[6] = r2[5];
                                                        }
                                                    }
                                                    com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1.setValue(r0);
                                                    if (com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt.writeCharacteristic(com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1)) {
                                                        java.lang.System.out.println("Hello BLe mode:" + com.egeetouch.egeetouch_manager.BLEService.Ble_Mode);
                                                        if (com.egeetouch.egeetouch_manager.BLEService.Ble_Mode == 176) {
                                                            com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = com.egeetouch.egeetouch_manager.BLEService.Read_response_Admin_Password_new_lock;
                                                            com.egeetouch.egeetouch_manager.BLEService.addTimeoutHandler.removeCallbacks(com.egeetouch.egeetouch_manager.BLEService.this.runAddTimeout);
                                                            com.egeetouch.egeetouch_manager.BLEService.addTimeoutHandler.postDelayed(com.egeetouch.egeetouch_manager.BLEService.this.runAddTimeout, 60000L);
                                                            break;
                                                        } else if (com.egeetouch.egeetouch_manager.BLEService.Ble_Mode == 97 && com.egeetouch.egeetouch_manager.BLEService.change_passcode) {
                                                            com.egeetouch.egeetouch_manager.BLEService.change_passcode = false;
                                                            com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 89;
                                                            break;
                                                        } else {
                                                            com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 16;
                                                            break;
                                                        }
                                                    }
                                                }
                                                break;
                                            case com.egeetouch.egeetouch_manager.BLEService.disconnect /* 187 */:
                                                android.content.SharedPreferences r0 = android.preference.PreferenceManager.getDefaultSharedPreferences(com.egeetouch.egeetouch_manager.BLEService.this.getApplication());
                                                if (!com.egeetouch.egeetouch_manager.BLEService.selected_lock_state && r0.getBoolean("auto_locking_boolean", false)) {
                                                    if (!com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2002") && !com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2003")) {
                                                        com.egeetouch.egeetouch_manager.BLEService.pending_disconnect = true;
                                                        com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 105;
                                                        break;
                                                    }
                                                } else {
                                                    com.egeetouch.egeetouch_manager.BLEService.this.disconnect();
                                                    com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 0;
                                                    break;
                                                }
                                                break;
                                            case com.egeetouch.egeetouch_manager.BLEService.Add_tag_version2 /* 221 */:
                                                java.lang.System.out.println("Hello Add_tag_version2 from BLEService !!");
                                                if (com.egeetouch.egeetouch_manager.BLEService.this.characteristic_4 != null && com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt != null) {
                                                    java.lang.System.out.println("Hello Add tag version2 listempty:" + com.egeetouch.egeetouch_manager.MainActivity.list_empty_pages + " last_page:" + com.egeetouch.egeetouch_manager.MainActivity.last_page_number + " position:" + com.egeetouch.egeetouch_manager.MainActivity.list_empty_pages.indexOf(java.lang.Integer.valueOf(com.egeetouch.egeetouch_manager.MainActivity.last_page_number)));
                                                    if ((com.egeetouch.egeetouch_manager.BLEService.rewrite_page_boolean && com.egeetouch.egeetouch_manager.BLEService.rewrite_page.get(com.egeetouch.egeetouch_manager.BLEService.page_count).intValue() <= com.egeetouch.egeetouch_manager.MainActivity.last_page_number) || com.egeetouch.egeetouch_manager.MainActivity.list_empty_pages.get(com.egeetouch.egeetouch_manager.BLEService.page_count).intValue() <= com.egeetouch.egeetouch_manager.MainActivity.last_page_number) {
                                                        if (com.egeetouch.egeetouch_manager.BLEService.rewrite_page_boolean) {
                                                            r0 = com.egeetouch.egeetouch_manager.BLEService.rewrite_page.get(com.egeetouch.egeetouch_manager.BLEService.page_count).intValue();
                                                        } else {
                                                            r0 = com.egeetouch.egeetouch_manager.MainActivity.list_empty_pages.get(com.egeetouch.egeetouch_manager.BLEService.page_count).intValue();
                                                        }
                                                        com.egeetouch.egeetouch_manager.BLEService.this.list_tag_ids = com.egeetouch.egeetouch_manager.MainActivity.tag_id_graph.get(r0);
                                                        java.lang.System.out.println("Hello page_count1:" + com.egeetouch.egeetouch_manager.BLEService.page_count + " list value:" + com.egeetouch.egeetouch_manager.BLEService.this.list_tag_ids);
                                                        byte[] r3 = new byte[16];
                                                        byte[] r2 = new byte[16];
                                                        r2[0] = 80;
                                                        r2[1] = -91;
                                                        r2[2] = (byte) r0;
                                                        r2[3] = (byte) com.egeetouch.egeetouch_manager.BLEService.tag_batch_number;
                                                        com.egeetouch.egeetouch_manager.BLEService.tag_page_number = r0;
                                                        for (int r4 = 4; r4 < 14; r4 += 2) {
                                                            if (com.egeetouch.egeetouch_manager.BLEService.tag_list_count < com.egeetouch.egeetouch_manager.BLEService.this.list_tag_ids.size()) {
                                                                java.lang.String[] r5 = com.egeetouch.egeetouch_manager.BLEService.this.list_tag_ids.get(com.egeetouch.egeetouch_manager.BLEService.tag_list_count).split(" ");
                                                                for (int r6 = 1; r6 < 3; r6++) {
                                                                    r3[r6] = com.egeetouch.egeetouch_manager.Helper_Math.ConvertStringToHexByte(r5[r6]);
                                                                }
                                                                r2[r4] = r3[1];
                                                                r2[r4 + 1] = r3[2];
                                                                com.egeetouch.egeetouch_manager.BLEService.tag_list_count++;
                                                            } else {
                                                                r2[r4] = 0;
                                                                r2[r4 + 2] = 0;
                                                                java.lang.System.out.println("Hello Reply Else");
                                                            }
                                                        }
                                                        java.lang.System.out.println("Hello page_count2:" + com.egeetouch.egeetouch_manager.BLEService.page_count + "Page number:" + r0 + " list value:" + com.egeetouch.egeetouch_manager.BLEService.this.list_tag_ids);
                                                        java.lang.System.out.println("Hello Reply Add1:" + java.util.Arrays.toString(r2) + " Tag_number:" + com.egeetouch.egeetouch_manager.BLEService.tag_page_number);
                                                        com.egeetouch.egeetouch_manager.BLEService.Add_tag_fail = r2;
                                                        android.util.Log.i("TAG", "Add_Tag_Version2::");
                                                        java.lang.System.out.println("Hello Reply Add:" + java.util.Arrays.toString(r2) + " Tag_number:" + com.egeetouch.egeetouch_manager.BLEService.Tag_number);
                                                        com.egeetouch.egeetouch_manager.BLEService.this.characteristic_4.setValue(r2);
                                                        if (com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt.writeCharacteristic(com.egeetouch.egeetouch_manager.BLEService.this.characteristic_4)) {
                                                            android.util.Log.i("TAG", "Hello Reply Add_Tag_Version2!!");
                                                            com.egeetouch.egeetouch_manager.BLEService.Request_tag_page = com.egeetouch.egeetouch_manager.BLEService.tag_page_number;
                                                            com.egeetouch.egeetouch_manager.BLEService.Request_tag_batch = com.egeetouch.egeetouch_manager.BLEService.tag_batch_number;
                                                            com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 17;
                                                        } else {
                                                            com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = com.egeetouch.egeetouch_manager.BLEService.Add_Tag_fail;
                                                        }
                                                    }
                                                    com.egeetouch.egeetouch_manager.BLEService.add_tag_boolean = false;
                                                    com.egeetouch.egeetouch_manager.BLEService.Tag_number = com.egeetouch.egeetouch_manager.DatabaseVariable.db_tag.rawQuery("SELECT * FROM Tag WHERE Lock = " + android.database.DatabaseUtils.sqlEscapeString(com.egeetouch.egeetouch_manager.BLEService.selected_lock_name), null).getCount();
                                                    break;
                                                }
                                                break;
                                            case com.egeetouch.egeetouch_manager.BLEService.Add_Tag_fail /* 225 */:
                                                if (com.egeetouch.egeetouch_manager.BLEService.this.characteristic_4 != null && com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt != null) {
                                                    java.lang.System.out.println("Hello Reply Add fail:" + java.util.Arrays.toString(com.egeetouch.egeetouch_manager.BLEService.Add_tag_fail) + " Tag_number:" + com.egeetouch.egeetouch_manager.BLEService.Tag_number);
                                                    com.egeetouch.egeetouch_manager.BLEService.this.characteristic_4.setValue(com.egeetouch.egeetouch_manager.BLEService.Add_tag_fail);
                                                    if (com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt.writeCharacteristic(com.egeetouch.egeetouch_manager.BLEService.this.characteristic_4)) {
                                                        android.util.Log.i("TAG", "Hello Reply Add_tag_version2_fail!!");
                                                        com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 17;
                                                        break;
                                                    }
                                                }
                                                break;
                                            case com.egeetouch.egeetouch_manager.BLEService.Request_tag_AuditTrial /* 230 */:
                                                if (com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1 != null && com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt != null) {
                                                    byte[] r0 = new byte[2];
                                                    long r2 = com.egeetouch.egeetouch_manager.BLEService.Tag_Audit_count;
                                                    for (int r4 = 1; r4 >= 0; r4--) {
                                                        r0[r4] = (byte) (r2 & 255);
                                                        r2 >>= 8;
                                                    }
                                                    com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1.setValue(new byte[]{-95, r0[0], r0[1], 0, 0, 0, 0});
                                                    if (com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt.writeCharacteristic(com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1)) {
                                                        com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = com.egeetouch.egeetouch_manager.BLEService.Read_response_tag_AuditTrail;
                                                        break;
                                                    }
                                                }
                                                break;
                                            case com.egeetouch.egeetouch_manager.BLEService.Request_clear_tag_AuditTrail /* 232 */:
                                                if (com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1 != null && com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt != null) {
                                                    com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1.setValue(new byte[]{-96, -86, 0, 0, 0, 0, 0});
                                                    if (com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt.writeCharacteristic(com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1)) {
                                                        com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = com.egeetouch.egeetouch_manager.BLEService.Read_response_clear_tag_AuditTrail;
                                                        break;
                                                    }
                                                }
                                                break;
                                            case com.egeetouch.egeetouch_manager.BLEService.ScanTagId_FromLock /* 235 */:
                                                if (com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1 != null && com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt != null) {
                                                    com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1.setValue(new byte[]{79, 0, 0, 0, 0, 0, 0});
                                                    java.lang.System.out.println("Hello checking the ScanTagId  data sent from char1:" + java.util.Arrays.toString(r0));
                                                    if (com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt.writeCharacteristic(com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1)) {
                                                        com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = com.egeetouch.egeetouch_manager.BLEService.ReadResponse_scanTagId;
                                                        break;
                                                    }
                                                }
                                                break;
                                            case com.egeetouch.egeetouch_manager.BLEService.ShackleBypass_settings /* 238 */:
                                                if (com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1 != null && com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt != null) {
                                                    byte[] r0 = new byte[7];
                                                    r0[0] = -103;
                                                    r0[1] = com.egeetouch.egeetouch_manager.BLEService.isShackleBypass_turnedOn ? (byte) -52 : (byte) 17;
                                                    r0[2] = com.egeetouch.egeetouch_manager.BLEService.isShackleBypass_turnedOn ? (byte) 0 : (byte) -52;
                                                    r0[3] = 0;
                                                    r0[4] = 0;
                                                    r0[5] = 0;
                                                    r0[6] = 0;
                                                    com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1.setValue(r0);
                                                    java.lang.System.out.println("Hello checking ShackleBypass_settings  data sent from char1:" + java.util.Arrays.toString(r0));
                                                    if (com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt.writeCharacteristic(com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1)) {
                                                        com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = com.egeetouch.egeetouch_manager.BLEService.ReadResponse_ShackleBypass;
                                                        break;
                                                    }
                                                }
                                                break;
                                            case com.egeetouch.egeetouch_manager.BLEService.Request_Master_Tag /* 241 */:
                                                java.lang.System.out.println("requesting master tag");
                                                if (com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1 != null && com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt != null) {
                                                    byte[] r0 = new byte[7];
                                                    r0[0] = 100;
                                                    r0[1] = -88;
                                                    for (int r2 = 2; r2 < 7; r2++) {
                                                        r0[r2] = 0;
                                                    }
                                                    java.lang.System.out.println("Hello checking Master Tag  data sent from char1:" + java.util.Arrays.toString(r0));
                                                    com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1.setValue(r0);
                                                    if (com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt.writeCharacteristic(com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1)) {
                                                        java.lang.System.out.println("writing master tag");
                                                        com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = com.egeetouch.egeetouch_manager.BLEService.ReadResponse_Master_Tag;
                                                        break;
                                                    }
                                                }
                                                break;
                                            case com.egeetouch.egeetouch_manager.BLEService.Request_lotoBatteryRecords /* 243 */:
                                                if (com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1 != null && com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt != null) {
                                                    byte[] r0 = new byte[7];
                                                    r0[0] = 103;
                                                    for (int r2 = 1; r2 < 7; r2++) {
                                                        r0[r2] = 0;
                                                    }
                                                    com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1.setValue(r0);
                                                    if (com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt.writeCharacteristic(com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1)) {
                                                        com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = com.egeetouch.egeetouch_manager.BLEService.Read_response_lotoBatteryRecords;
                                                        break;
                                                    }
                                                }
                                                break;
                                            case com.egeetouch.egeetouch_manager.BLEService.Request_passcodeValue /* 249 */:
                                                if (com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1 != null && com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt != null) {
                                                    byte[] r0 = new byte[7];
                                                    r0[0] = 100;
                                                    r0[1] = -64;
                                                    r0[2] = (byte) com.egeetouch.egeetouch_manager.BLEService.PasscodeIndexCount;
                                                    for (int r2 = 3; r2 < 7; r2++) {
                                                        r0[3] = 0;
                                                    }
                                                    com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1.setValue(r0);
                                                    java.lang.System.out.println("Hello checking the Passcode value sent :" + java.util.Arrays.toString(r0));
                                                    if (com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt.writeCharacteristic(com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1)) {
                                                        com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 250;
                                                        break;
                                                    }
                                                }
                                                break;
                                            case com.egeetouch.egeetouch_manager.BLEService.Request_passcodeAudit /* 251 */:
                                                if (com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1 != null && com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt != null) {
                                                    byte[] r0 = new byte[7];
                                                    byte[] r3 = new byte[2];
                                                    int r2 = com.egeetouch.egeetouch_manager.BLEService.PasscodeAuditIndex;
                                                    for (int r4 = 1; r4 >= 0; r4--) {
                                                        r3[r4] = (byte) (r2 & 255);
                                                        r2 >>= 8;
                                                    }
                                                    r0[0] = 101;
                                                    r0[1] = -83;
                                                    r0[2] = r3[1];
                                                    r0[3] = r3[0];
                                                    r0[4] = 0;
                                                    r0[5] = 0;
                                                    r0[6] = 0;
                                                    com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1.setValue(r0);
                                                    java.lang.System.out.println("Hello checking the Passcode Audit sent :" + java.util.Arrays.toString(r0) + " data[0]:" + (r0[0] & kotlin.UByte.MAX_VALUE));
                                                    com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = com.egeetouch.egeetouch_manager.BLEService.ReadResponse_passcodeAudit;
                                                    if (com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt.writeCharacteristic(com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1)) {
                                                        com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = com.egeetouch.egeetouch_manager.BLEService.ReadResponse_passcodeAudit;
                                                        break;
                                                    }
                                                }
                                                break;
                                            case com.egeetouch.egeetouch_manager.BLEService.Request_clear_passcodeAuditTrail /* 253 */:
                                                if (com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1 != null && com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt != null) {
                                                    byte[] r0 = new byte[7];
                                                    r0[0] = 101;
                                                    r0[1] = -34;
                                                    for (int r2 = 2; r2 < 7; r2++) {
                                                        r0[r2] = 0;
                                                    }
                                                    com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1.setValue(r0);
                                                    if (com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt.writeCharacteristic(com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1)) {
                                                        com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = com.egeetouch.egeetouch_manager.BLEService.ReadResponse_clear_passcodeAuditTrail;
                                                        break;
                                                    }
                                                }
                                                break;
                                            case 257:
                                                if (com.egeetouch.egeetouch_manager.BLEService.this.characteristic_4 != null && com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt != null) {
                                                    int r0 = com.egeetouch.egeetouch_manager.BLEService.this.lotoInfo.getUpdateMode();
                                                    int r2 = com.egeetouch.egeetouch_manager.BLEService.this.lotoInfo.syncPasscodeIndexList.get(com.egeetouch.egeetouch_manager.BLEService.this.syncPasscodeCount).intValue();
                                                    byte[] r4 = new byte[2];
                                                    byte[] r5 = new byte[16];
                                                    r5[0] = -51;
                                                    r5[1] = (byte) r0;
                                                    if (r0 == 222) {
                                                        int r0 = com.egeetouch.egeetouch_manager.BLEService.this.convertPasscodeToDecimal("111111");
                                                        int r3 = 0;
                                                        for (int r6 = 1; r3 <= r6; r6 = 1) {
                                                            r4[r3] = (byte) (r0 & 255);
                                                            r0 >>= 8;
                                                            r3++;
                                                        }
                                                        r5[2] = (byte) r2;
                                                        r5[3] = 6;
                                                        r5[4] = r4[0];
                                                        r5[5] = r4[1];
                                                        for (int r0 = 6; r0 < 16; r0++) {
                                                            r5[r0] = 0;
                                                        }
                                                    } else {
                                                        int r0 = com.egeetouch.egeetouch_manager.BLEService.this.lotoInfo.syncPasscodeList.get(com.egeetouch.egeetouch_manager.BLEService.this.syncPasscodeCount).intValue();
                                                        if (r0 == 0) {
                                                            r0 = com.egeetouch.egeetouch_manager.BLEService.this.convertPasscodeToDecimal("111111");
                                                            r6 = 1;
                                                            r5[1] = (byte) com.egeetouch.egeetouch_manager.BLEService.this.lotoInfo.DELETE_PASSCODE;
                                                        } else {
                                                            r6 = 1;
                                                        }
                                                        int r3 = 0;
                                                        while (r3 <= r6) {
                                                            r4[r3] = (byte) (r0 & 255);
                                                            r0 >>= 8;
                                                            r3++;
                                                            r6 = 1;
                                                        }
                                                        r5[2] = (byte) r2;
                                                        r5[3] = 6;
                                                        r5[4] = r4[0];
                                                        r5[5] = r4[1];
                                                        for (int r0 = 6; r0 < 16; r0++) {
                                                            r5[r0] = 0;
                                                        }
                                                    }
                                                    com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 0;
                                                    java.lang.System.out.println("Hello checking the Passcode Sync Sent !" + java.util.Arrays.toString(r5));
                                                    com.egeetouch.egeetouch_manager.BLEService.this.characteristic_4.setValue(r5);
                                                    if (com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt.writeCharacteristic(com.egeetouch.egeetouch_manager.BLEService.this.characteristic_4)) {
                                                        com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = com.egeetouch.egeetouch_manager.BLEService.ReadResponse_syncPasscode;
                                                        break;
                                                    }
                                                }
                                                break;
                                            case com.egeetouch.egeetouch_manager.BLEService.Request_lotoEnable_auditTrail /* 265 */:
                                                byte[] r0 = new byte[7];
                                                r0[0] = 102;
                                                r0[1] = 5;
                                                r0[2] = 1;
                                                for (int r2 = 3; r2 < 7; r2++) {
                                                    r0[r2] = 0;
                                                }
                                                com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1.setValue(r0);
                                                if (com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt.writeCharacteristic(com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1)) {
                                                    com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = com.egeetouch.egeetouch_manager.BLEService.Read_Response_lotoEnable_auditTrail;
                                                    break;
                                                }
                                                break;
                                            case com.egeetouch.egeetouch_manager.BLEService.Read_masterTag_only /* 409 */:
                                                java.lang.System.out.println("this is mastertag only" + com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1);
                                                if (com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1 != null && com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt != null) {
                                                    byte[] r0 = new byte[7];
                                                    r0[0] = 100;
                                                    r0[1] = -88;
                                                    for (int r2 = 2; r2 < 7; r2++) {
                                                        r0[r2] = 0;
                                                    }
                                                    java.lang.System.out.println("Hello checking Master Tag data sent from char1:" + java.util.Arrays.toString(r0));
                                                    com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1.setValue(r0);
                                                    if (com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt.writeCharacteristic(com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1)) {
                                                        com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = com.egeetouch.egeetouch_manager.BLEService.coresponse_MasterTag;
                                                        break;
                                                    }
                                                }
                                                break;
                                            case 500:
                                                if (com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1 != null && com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt != null) {
                                                    byte[] r0 = new byte[7];
                                                    byte[] r3 = new byte[2];
                                                    int r2 = com.egeetouch.egeetouch_manager.BLEService.add_tag_count_IP66;
                                                    for (int r4 = 0; r4 <= 1; r4++) {
                                                        r3[r4] = (byte) (r2 & 255);
                                                        r2 >>= 8;
                                                    }
                                                    byte[] r2 = new byte[16];
                                                    java.lang.String[] r4 = com.egeetouch.egeetouch_manager.BLEService.list_Add_tag_id_IP66.get(com.egeetouch.egeetouch_manager.BLEService.add_tag_count_IP66).split(" ");
                                                    for (int r5 = 1; r5 < 3; r5++) {
                                                        r2[r5] = com.egeetouch.egeetouch_manager.Helper_Math.ConvertStringToHexByte(r4[r5]);
                                                    }
                                                    r0[0] = 80;
                                                    r0[1] = 5;
                                                    r0[2] = 1;
                                                    r0[3] = r3[0];
                                                    r0[4] = r3[1];
                                                    r0[5] = r2[2];
                                                    r0[6] = r2[1];
                                                    com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1.setValue(r0);
                                                    java.lang.System.out.println("Hello checking the Tag ADD SENT data : " + java.util.Arrays.toString(r0));
                                                    if (com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt.writeCharacteristic(com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1)) {
                                                        com.egeetouch.egeetouch_manager.UI_BLE.progress_val = (com.egeetouch.egeetouch_manager.BLEService.add_tag_count_IP66 * 100) / com.egeetouch.egeetouch_manager.BLEService.list_Add_tag_id_IP66.size();
                                                        com.egeetouch.egeetouch_manager.UI_BLE.BLE_UI = 24;
                                                        break;
                                                    }
                                                }
                                                break;
                                            case 501:
                                                if (com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1 != null && com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt != null) {
                                                    byte[] r0 = new byte[10];
                                                    byte[] r3 = new byte[2];
                                                    int r2 = 1;
                                                    for (int r4 = 0; r4 <= 1; r4++) {
                                                        r3[r4] = (byte) (r2 & 255);
                                                        r2 >>= 8;
                                                    }
                                                    byte[] r2 = new byte[16];
                                                    java.lang.String[] r4 = com.egeetouch.egeetouch_manager.BLEService.deleteTagID_IP66.split(" ");
                                                    for (int r5 = 1; r5 < 3; r5++) {
                                                        r2[r5] = com.egeetouch.egeetouch_manager.Helper_Math.ConvertStringToHexByte(r4[r5]);
                                                    }
                                                    r0[0] = 80;
                                                    r0[1] = 6;
                                                    r0[2] = 2;
                                                    r0[3] = r3[0];
                                                    r0[4] = r3[1];
                                                    r0[5] = r2[2];
                                                    r0[6] = r2[1];
                                                    com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1.setValue(r0);
                                                    java.lang.System.out.println("Hello checking the Tag DELETE SENT data : " + java.util.Arrays.toString(r0) + " TAG ID:" + com.egeetouch.egeetouch_manager.BLEService.deleteTagID_IP66);
                                                    com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt.writeCharacteristic(com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1);
                                                    break;
                                                }
                                                break;
                                            case 502:
                                                if (com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1 != null && com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt != null) {
                                                    byte[] r0 = new byte[10];
                                                    byte[] r3 = new byte[2];
                                                    int r2 = 1;
                                                    for (int r4 = 0; r4 <= 1; r4++) {
                                                        r3[r4] = (byte) (r2 & 255);
                                                        r2 >>= 8;
                                                    }
                                                    r0[0] = 80;
                                                    r0[1] = 6;
                                                    r0[2] = 3;
                                                    r0[3] = r3[0];
                                                    r0[4] = r3[1];
                                                    r0[5] = 119;
                                                    r0[6] = -121;
                                                    com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1.setValue(r0);
                                                    java.lang.System.out.println("Hello checking the Tag CHECK SENT data : " + java.util.Arrays.toString(r0));
                                                    if (com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt.writeCharacteristic(com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1)) {
                                                        com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 602;
                                                        break;
                                                    }
                                                }
                                                break;
                                            case 503:
                                                if (com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1 != null && com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt != null) {
                                                    byte[] r0 = new byte[10];
                                                    byte[] r3 = new byte[2];
                                                    int r2 = com.egeetouch.egeetouch_manager.BLEService.index_tag_IP66;
                                                    for (int r4 = 0; r4 <= 1; r4++) {
                                                        r3[r4] = (byte) (r2 & 255);
                                                        r2 >>= 8;
                                                    }
                                                    r0[0] = 80;
                                                    r0[1] = 6;
                                                    r0[2] = 4;
                                                    r0[3] = r3[0];
                                                    r0[4] = r3[1];
                                                    com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1.setValue(r0);
                                                    java.lang.System.out.println("Hello checking the Tag READ SENT data : " + java.util.Arrays.toString(r0));
                                                    if (com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt.writeCharacteristic(com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1)) {
                                                        com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 603;
                                                        break;
                                                    }
                                                }
                                                break;
                                            case 504:
                                                if (com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1 != null && com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt != null) {
                                                    byte[] r0 = new byte[10];
                                                    r0[0] = 48;
                                                    r0[1] = 2;
                                                    com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1.setValue(r0);
                                                    java.lang.System.out.println("Hello checking the STATUS SENT data : " + java.util.Arrays.toString(r0));
                                                    if (com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt.writeCharacteristic(com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1)) {
                                                        com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 604;
                                                        break;
                                                    }
                                                }
                                                break;
                                            case 505:
                                                if (com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1 != null && com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt != null) {
                                                    java.lang.System.out.println("Hello checking the AUDIT TRAIl :");
                                                    byte[] r0 = new byte[10];
                                                    byte[] r3 = new byte[2];
                                                    int r2 = com.egeetouch.egeetouch_manager.BLEService.audit_current_index_IP66;
                                                    for (int r4 = 1; r4 >= 0; r4--) {
                                                        r3[r4] = (byte) (r2 & 255);
                                                        r2 >>= 8;
                                                    }
                                                    r0[0] = -96;
                                                    r0[1] = 5;
                                                    r0[2] = 1;
                                                    r0[3] = r3[1];
                                                    r0[4] = r3[0];
                                                    com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1.setValue(r0);
                                                    java.lang.System.out.println("Hello checking the Audit STATUS SENT data : " + java.util.Arrays.toString(r0) + "Count: " + com.egeetouch.egeetouch_manager.BLEService.audit_current_index_IP66 + " [l]: " + ((int) r3[1]) + " [h]: " + ((int) r3[0]));
                                                    if (com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt.writeCharacteristic(com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1)) {
                                                        try {
                                                            com.egeetouch.egeetouch_manager.UI_BLE.progress_val = ((com.egeetouch.egeetouch_manager.BLEService.audittrail_max_count - com.egeetouch.egeetouch_manager.BLEService.audit_current_index_IP66) * 100) / com.egeetouch.egeetouch_manager.BLEService.audittrail_max_count;
                                                        } catch (java.lang.Exception unused) {
                                                            com.egeetouch.egeetouch_manager.UI_BLE.progress_val = 0;
                                                        }
                                                        com.egeetouch.egeetouch_manager.UI_BLE.BLE_UI = 24;
                                                        break;
                                                    }
                                                }
                                                break;
                                            case 506:
                                                if (com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1 != null && com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt != null) {
                                                    byte[] r0 = new byte[10];
                                                    int r2 = com.egeetouch.egeetouch_manager.BLEService.audit_current_index_IP66;
                                                    r0[0] = -96;
                                                    r0[1] = 5;
                                                    r0[2] = 4;
                                                    com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1.setValue(r0);
                                                    com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt.writeCharacteristic(com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1);
                                                    break;
                                                }
                                                break;
                                            case 507:
                                                if (com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1 != null && com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt != null) {
                                                    byte[] r0 = new byte[10];
                                                    r0[0] = -96;
                                                    r0[1] = 5;
                                                    r0[2] = 3;
                                                    com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1.setValue(r0);
                                                    java.lang.System.out.println("Hello checking the Audit STATUS CHECK AUDIT COUNT sent : " + java.util.Arrays.toString(r0) + "Count: " + com.egeetouch.egeetouch_manager.BLEService.audit_current_index_IP66);
                                                    if (com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt.writeCharacteristic(com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1)) {
                                                        com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 607;
                                                        break;
                                                    }
                                                }
                                                break;
                                            case 508:
                                                if (com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1 != null && com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt != null) {
                                                    com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1.setValue(new byte[]{-58, 3, 4});
                                                    java.lang.System.out.println("Hello checking the Tag MASTER READ SENT data : " + java.util.Arrays.toString(r0));
                                                    if (com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt.writeCharacteristic(com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1)) {
                                                        com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 608;
                                                        break;
                                                    }
                                                }
                                                break;
                                            case 509:
                                                if (com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1 != null && com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt != null) {
                                                    byte[] r0 = new byte[5];
                                                    byte[] r3 = new byte[2];
                                                    int r2 = com.egeetouch.egeetouch_manager.BLEService.LockReconnectingTime;
                                                    for (int r4 = 0; r4 <= 1; r4++) {
                                                        r3[r4] = (byte) (r2 & 255);
                                                        r2 >>= 8;
                                                    }
                                                    r0[0] = -57;
                                                    r0[1] = 3;
                                                    r0[2] = 1;
                                                    r0[3] = r3[0];
                                                    r0[4] = r3[1];
                                                    com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1.setValue(r0);
                                                    java.lang.System.out.println("Hello checking the IP66 ADV data : " + java.util.Arrays.toString(r0) + " SEC:" + com.egeetouch.egeetouch_manager.BLEService.LockReconnectingTime);
                                                    if (com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt.writeCharacteristic(com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1)) {
                                                        com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 609;
                                                        break;
                                                    }
                                                }
                                                break;
                                            case 510:
                                                if (com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1 != null && com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt != null) {
                                                    com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1.setValue(new byte[]{-57, 3, 4});
                                                    if (com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt.writeCharacteristic(com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1)) {
                                                        com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 610;
                                                        break;
                                                    }
                                                }
                                                break;
                                            default:
                                                switch (r0) {
                                                    case 48:
                                                    case 49:
                                                    case 52:
                                                    case 53:
                                                        break;
                                                    case 50:
                                                        if (com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1 != null && com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt != null) {
                                                            byte[] r0 = new byte[7];
                                                            r0[0] = 104;
                                                            r0[1] = (byte) com.egeetouch.egeetouch_manager.BLEService.audit_trail_loop_number;
                                                            for (int r2 = 2; r2 < 7; r2++) {
                                                                r0[r2] = 0;
                                                            }
                                                            com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1.setValue(r0);
                                                            if (com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt.writeCharacteristic(com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1)) {
                                                                com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 54;
                                                                android.util.Log.i("TAG", "Trigger Read_Audit_Data_1");
                                                                break;
                                                            }
                                                        }
                                                        break;
                                                    case 51:
                                                        if (com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1 != null && com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt != null) {
                                                            byte[] r0 = new byte[7];
                                                            r0[0] = 104;
                                                            r0[1] = (byte) (com.egeetouch.egeetouch_manager.BLEService.audit_trail_loop_number + 32);
                                                            for (int r2 = 2; r2 < 7; r2++) {
                                                                r0[r2] = 0;
                                                            }
                                                            com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1.setValue(r0);
                                                            if (com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt.writeCharacteristic(com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1)) {
                                                                com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 55;
                                                                break;
                                                            }
                                                        }
                                                        break;
                                                    case 54:
                                                    case 55:
                                                        break;
                                                    default:
                                                        switch (r0) {
                                                            case 66:
                                                            case 68:
                                                                break;
                                                            case 67:
                                                                if (com.egeetouch.egeetouch_manager.BLEService.this.characteristic_battery != null && com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt != null) {
                                                                    com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt.readCharacteristic(com.egeetouch.egeetouch_manager.BLEService.this.characteristic_battery);
                                                                    break;
                                                                }
                                                                break;
                                                            default:
                                                                switch (r0) {
                                                                    case 85:
                                                                        if (com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1 != null && com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt != null) {
                                                                            com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1.setValue(new byte[]{97, -81, 105, -44, -125, 21, -22});
                                                                            if (com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt.writeCharacteristic(com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1)) {
                                                                                com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 87;
                                                                                break;
                                                                            }
                                                                        }
                                                                        break;
                                                                    case 86:
                                                                        if (com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1 != null && com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt != null) {
                                                                            byte[] r0 = new byte[7];
                                                                            r0[0] = 96;
                                                                            byte[] r2 = com.egeetouch.egeetouch_manager.BLEService.new_primary_password.getBytes();
                                                                            if (com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2500") || com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2550")) {
                                                                                r0 = new byte[]{96, 6, r2[0], r2[1], r2[2], r2[3], r2[4], r2[5]};
                                                                            } else {
                                                                                r0[1] = r2[0];
                                                                                r0[2] = r2[1];
                                                                                r0[3] = r2[2];
                                                                                r0[4] = r2[3];
                                                                                r0[5] = r2[4];
                                                                                r0[6] = r2[5];
                                                                            }
                                                                            com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1.setValue(r0);
                                                                            if (com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt.writeCharacteristic(com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1)) {
                                                                                com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 88;
                                                                                break;
                                                                            }
                                                                        }
                                                                        break;
                                                                    case 87:
                                                                    case 88:
                                                                        break;
                                                                    case 89:
                                                                        if (com.egeetouch.egeetouch_manager.BLEService.this.characteristic_4 != null && com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothAdapter != null) {
                                                                            com.egeetouch.egeetouch_manager.BLEService.this.characteristic_4.setValue(com.egeetouch.egeetouch_manager.BLEService.new_pass_code_byte);
                                                                            if (com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt.writeCharacteristic(com.egeetouch.egeetouch_manager.BLEService.this.characteristic_4)) {
                                                                                android.util.Log.i("TAG", "Change_passcode!!");
                                                                                com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 0;
                                                                                break;
                                                                            } else {
                                                                                com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 89;
                                                                                break;
                                                                            }
                                                                        }
                                                                        break;
                                                                    default:
                                                                        switch (r0) {
                                                                            case 97:
                                                                                break;
                                                                            case 98:
                                                                                if (com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1 != null && com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt != null) {
                                                                                    byte[] r0 = new byte[7];
                                                                                    r0[0] = 98;
                                                                                    byte[] r3 = new byte[4];
                                                                                    if (com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.contains("GT2500") || com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2550")) {
                                                                                        r0[0] = 98;
                                                                                        r0[1] = 4;
                                                                                        r0[2] = r3[0];
                                                                                        r0[3] = r3[1];
                                                                                        r0[4] = r3[2];
                                                                                        r0[5] = r3[3];
                                                                                        r0[6] = 0;
                                                                                    } else if ((com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2100") && java.lang.Float.valueOf(com.egeetouch.egeetouch_manager.BLEService.selected_lock_version).floatValue() > 1.8d) || com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT5100") || com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT5107") || com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT5109") || com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT5300")) {
                                                                                        long r5 = (java.lang.System.currentTimeMillis() / 1000) + 3;
                                                                                        long r11 = r5;
                                                                                        int r2 = 3;
                                                                                        while (r2 >= 0) {
                                                                                            r3[r2] = (byte) (r11 & 255);
                                                                                            r11 >>= 8;
                                                                                            r2--;
                                                                                            r5 = r5;
                                                                                        }
                                                                                        r0[1] = r3[0];
                                                                                        r0[2] = r3[1];
                                                                                        r0[3] = r3[2];
                                                                                        r0[4] = r3[3];
                                                                                        r0[5] = 0;
                                                                                        r0[6] = 0;
                                                                                        java.lang.System.out.println("Hello checking the time stamp raw value UNlocking:" + r5 + " Byte:" + java.util.Arrays.toString(r3));
                                                                                    } else {
                                                                                        byte[] r2 = com.egeetouch.egeetouch_manager.BLEService.selected_lock_password.getBytes();
                                                                                        r0[1] = r2[0];
                                                                                        r0[2] = r2[1];
                                                                                        r0[3] = r2[2];
                                                                                        r0[4] = r2[3];
                                                                                        r0[5] = r2[4];
                                                                                        r0[6] = r2[5];
                                                                                    }
                                                                                    java.lang.System.out.println("CMDCMD byte array ref: " + java.util.Arrays.toString(r0));
                                                                                    com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1.setValue(r0);
                                                                                    if (com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt.writeCharacteristic(com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1)) {
                                                                                        com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 66;
                                                                                        break;
                                                                                    }
                                                                                }
                                                                                break;
                                                                            case 99:
                                                                                if (com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1 != null && com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt != null) {
                                                                                    java.lang.System.out.println("Hello checkin UUID_2 diasble shutdown!");
                                                                                    byte[] r2 = com.egeetouch.egeetouch_manager.BLEService.selected_lock_password.getBytes();
                                                                                    com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1.setValue(new byte[]{99, r2[0], r2[1], r2[2], r2[3], r2[4], r2[5]});
                                                                                    if (com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt.writeCharacteristic(com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1)) {
                                                                                        android.util.Log.i("TAG", "Shutdown command sent");
                                                                                        com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 71;
                                                                                        break;
                                                                                    }
                                                                                }
                                                                                break;
                                                                            case 100:
                                                                                java.lang.System.out.println("HEY from BLEService Request_Tag_list getting called");
                                                                                if (com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1 != null && com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt != null) {
                                                                                    com.egeetouch.egeetouch_manager.BLEService.update_progress = com.egeetouch.egeetouch_manager.BLEService.retrieve_tag_current_number / com.egeetouch.egeetouch_manager.BLEService.retrieved_Tag_number;
                                                                                    byte[] r0 = {100, (byte) com.egeetouch.egeetouch_manager.BLEService.retrieve_tag_current_number, 0, 0, 0, 0, 0};
                                                                                    java.lang.System.out.println("HEY HEY from BLEService Request_Tag_list value_tag " + java.util.Arrays.toString(r0));
                                                                                    com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1.setValue(r0);
                                                                                    if (com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt.writeCharacteristic(com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1)) {
                                                                                        com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 18;
                                                                                        break;
                                                                                    }
                                                                                }
                                                                                break;
                                                                            case 101:
                                                                                if (com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1 != null && com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt != null) {
                                                                                    com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1.setValue(new byte[]{101, (byte) com.egeetouch.egeetouch_manager.BLEService.User_loop_number, 0, 0, 0, 0, 0});
                                                                                    if (com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt.writeCharacteristic(com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1)) {
                                                                                        android.util.Log.i("TAG", "Request_User_Name");
                                                                                        com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 38;
                                                                                        break;
                                                                                    }
                                                                                }
                                                                                break;
                                                                            case 102:
                                                                                if (com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1 != null && com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt != null) {
                                                                                    com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1.setValue(new byte[]{102, (byte) com.egeetouch.egeetouch_manager.BLEService.User_loop_number, 0, 0, 0, 0, 0});
                                                                                    if (com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt.writeCharacteristic(com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1)) {
                                                                                        com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 39;
                                                                                        break;
                                                                                    }
                                                                                }
                                                                                break;
                                                                            case 103:
                                                                                if (com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1 != null && com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt != null) {
                                                                                    byte[] r0 = new byte[7];
                                                                                    r0[0] = 103;
                                                                                    for (int r2 = 1; r2 < 7; r2++) {
                                                                                        r0[r2] = 0;
                                                                                    }
                                                                                    com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1.setValue(r0);
                                                                                    if (com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt.writeCharacteristic(com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1)) {
                                                                                        android.util.Log.i("Tag", "Request_Audit_Index2");
                                                                                        com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 57;
                                                                                        break;
                                                                                    }
                                                                                }
                                                                                break;
                                                                            default:
                                                                                switch (r0) {
                                                                                    case 117:
                                                                                        if (com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1 != null && com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt != null) {
                                                                                            byte[] r0 = new byte[7];
                                                                                            r0[0] = 117;
                                                                                            double r2 = com.google.firebase.remoteconfig.FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
                                                                                            if (!com.egeetouch.egeetouch_manager.BLEService.selected_lock_version.equals("")) {
                                                                                                r2 = java.lang.Double.parseDouble(com.egeetouch.egeetouch_manager.BLEService.selected_lock_version);
                                                                                            }
                                                                                            if (r2 < 3.18d && !com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT3100") && !com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2002")) {
                                                                                                if (com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2100") && java.lang.Float.valueOf(com.egeetouch.egeetouch_manager.BLEService.selected_lock_version).floatValue() > 1.8d) {
                                                                                                    if (com.egeetouch.egeetouch_manager.BLEService.set_adv_time == 20) {
                                                                                                        r0[1] = 2;
                                                                                                        com.egeetouch.egeetouch_manager.BLEService.this.autoReconnectionTime = 20;
                                                                                                    } else if (com.egeetouch.egeetouch_manager.BLEService.set_adv_time == 30) {
                                                                                                        r0[1] = 3;
                                                                                                        com.egeetouch.egeetouch_manager.BLEService.this.autoReconnectionTime = 30;
                                                                                                    } else if (com.egeetouch.egeetouch_manager.BLEService.set_adv_time == 60) {
                                                                                                        r0[1] = 6;
                                                                                                        com.egeetouch.egeetouch_manager.BLEService.this.autoReconnectionTime = 60;
                                                                                                    } else if (com.egeetouch.egeetouch_manager.BLEService.set_adv_time == 10) {
                                                                                                        r0[1] = 1;
                                                                                                        com.egeetouch.egeetouch_manager.BLEService.this.autoReconnectionTime = 10;
                                                                                                    } else {
                                                                                                        r2 = 2;
                                                                                                        r0[1] = 2;
                                                                                                        com.egeetouch.egeetouch_manager.BLEService.this.autoReconnectionTime = 2;
                                                                                                        r0[r2] = 0;
                                                                                                        r0[3] = 0;
                                                                                                        r0[4] = 0;
                                                                                                        r0[5] = 0;
                                                                                                        r0[6] = 0;
                                                                                                        com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1.setValue(r0);
                                                                                                        if (com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt.writeCharacteristic(com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1)) {
                                                                                                            com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = com.egeetouch.egeetouch_manager.BLEService.Read_response_update_reconnecting_period;
                                                                                                            break;
                                                                                                        }
                                                                                                    }
                                                                                                } else if (com.egeetouch.egeetouch_manager.BLEService.set_adv_time == 1) {
                                                                                                    r0[1] = 30;
                                                                                                } else if (com.egeetouch.egeetouch_manager.BLEService.set_adv_time == 3) {
                                                                                                    r0[1] = 90;
                                                                                                } else if (com.egeetouch.egeetouch_manager.BLEService.set_adv_time == 5) {
                                                                                                    r0[1] = -106;
                                                                                                }
                                                                                            } else if (r2 > 3.18d || com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT3100") || com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2002")) {
                                                                                                if (com.egeetouch.egeetouch_manager.BLEService.set_adv_time == 3) {
                                                                                                    r0[1] = 18;
                                                                                                } else if (com.egeetouch.egeetouch_manager.BLEService.set_adv_time == 5) {
                                                                                                    r0[1] = 30;
                                                                                                } else if (com.egeetouch.egeetouch_manager.BLEService.set_adv_time == 10) {
                                                                                                    r0[1] = 60;
                                                                                                } else if (com.egeetouch.egeetouch_manager.BLEService.set_adv_time == 30) {
                                                                                                    r0[1] = -76;
                                                                                                }
                                                                                            }
                                                                                            r2 = 2;
                                                                                            r0[r2] = 0;
                                                                                            r0[3] = 0;
                                                                                            r0[4] = 0;
                                                                                            r0[5] = 0;
                                                                                            r0[6] = 0;
                                                                                            com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1.setValue(r0);
                                                                                            if (com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt.writeCharacteristic(com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1)) {
                                                                                            }
                                                                                        }
                                                                                        break;
                                                                                    case 118:
                                                                                        if (com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1 != null && com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt != null) {
                                                                                            byte[] r0 = new byte[7];
                                                                                            r0[0] = 118;
                                                                                            r0[1] = 0;
                                                                                            for (int r2 = 2; r2 < 7; r2++) {
                                                                                                r0[r2] = 0;
                                                                                            }
                                                                                            com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1.setValue(r0);
                                                                                            if (com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt.writeCharacteristic(com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1)) {
                                                                                                com.egeetouch.egeetouch_manager.BLEService.trigger_low_power_mode = true;
                                                                                                com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = com.egeetouch.egeetouch_manager.BLEService.Read_Response_enter_low_power_mode;
                                                                                                break;
                                                                                            }
                                                                                        }
                                                                                        break;
                                                                                    case 119:
                                                                                        android.util.Log.i("TAG", "quit_low_power_mode");
                                                                                        if (com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1 != null && com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt != null) {
                                                                                            byte[] r0 = new byte[7];
                                                                                            r0[0] = 119;
                                                                                            r0[1] = 0;
                                                                                            for (int r2 = 2; r2 < 7; r2++) {
                                                                                                r0[r2] = 0;
                                                                                            }
                                                                                            com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1.setValue(r0);
                                                                                            if (com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt.writeCharacteristic(com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1)) {
                                                                                                com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = com.egeetouch.egeetouch_manager.BLEService.Read_Response_quit_low_power_mode;
                                                                                                break;
                                                                                            }
                                                                                        }
                                                                                        break;
                                                                                    default:
                                                                                        switch (r0) {
                                                                                            case com.egeetouch.egeetouch_manager.BLEService.Request_tag_AuditTrail_number /* 158 */:
                                                                                                if (com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1 != null && com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt != null) {
                                                                                                    com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1.setValue(new byte[]{-98, 0, 0, 0, 0, 0, 0});
                                                                                                    if (com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt.writeCharacteristic(com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1)) {
                                                                                                        com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = com.egeetouch.egeetouch_manager.BLEService.Read_response_tag_AuditTrail_number;
                                                                                                        break;
                                                                                                    }
                                                                                                }
                                                                                                break;
                                                                                            case com.egeetouch.egeetouch_manager.BLEService.Send_timeStamp /* 159 */:
                                                                                                if (com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1 != null && com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt != null) {
                                                                                                    byte[] r0 = new byte[7];
                                                                                                    r0[0] = -97;
                                                                                                    byte[] r5 = new byte[4];
                                                                                                    long r11 = java.lang.System.currentTimeMillis() / 1000;
                                                                                                    long r18 = r11;
                                                                                                    for (int r16 = 3; r16 >= 0; r16--) {
                                                                                                        r5[r16] = (byte) (r18 & 255);
                                                                                                        r18 >>= 8;
                                                                                                    }
                                                                                                    if (com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2500") || com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2550")) {
                                                                                                        r0[0] = -61;
                                                                                                        r0[1] = 4;
                                                                                                        r0[2] = r5[0];
                                                                                                        r0[3] = r5[1];
                                                                                                        r0[4] = r5[2];
                                                                                                        r0[5] = r5[3];
                                                                                                        r0[6] = 0;
                                                                                                    } else {
                                                                                                        r0[1] = r5[0];
                                                                                                        r0[2] = r5[1];
                                                                                                        r0[3] = r5[2];
                                                                                                        r0[4] = r5[3];
                                                                                                        r0[5] = 0;
                                                                                                        r0[6] = 0;
                                                                                                    }
                                                                                                    java.lang.System.out.println("Hello check time bytes:" + java.util.Arrays.toString(r0) + " TimeStamp:" + r11);
                                                                                                    com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1.setValue(r0);
                                                                                                    if (com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt.writeCharacteristic(com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1)) {
                                                                                                        com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = com.egeetouch.egeetouch_manager.BLEService.Read_response_timestamp;
                                                                                                        break;
                                                                                                    }
                                                                                                }
                                                                                                break;
                                                                                            case com.egeetouch.egeetouch_manager.BLEService.check_version /* 160 */:
                                                                                                if (com.egeetouch.egeetouch_manager.BLEService.this.characteristic_version != null && com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt != null) {
                                                                                                    com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt.readCharacteristic(com.egeetouch.egeetouch_manager.BLEService.this.characteristic_version);
                                                                                                    break;
                                                                                                }
                                                                                                break;
                                                                                            case com.egeetouch.egeetouch_manager.BLEService.check_model /* 161 */:
                                                                                                java.lang.System.out.println("Hello checking the model !!! characteristic_model:" + (com.egeetouch.egeetouch_manager.BLEService.this.characteristic_model != null) + " BLU:" + (com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt != null));
                                                                                                if (com.egeetouch.egeetouch_manager.BLEService.this.characteristic_model != null && com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt != null && !com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt.readCharacteristic(com.egeetouch.egeetouch_manager.BLEService.this.characteristic_model)) {
                                                                                                    java.lang.System.out.println("Hello checking the model !!!!!!!");
                                                                                                    break;
                                                                                                }
                                                                                                break;
                                                                                            default:
                                                                                                if (com.egeetouch.egeetouch_manager.BLEService.Ble_Mode == 0 && com.egeetouch.egeetouch_manager.BLEService.mConnectionState != 0) {
                                                                                                    com.egeetouch.egeetouch_manager.BLEService.access$1308();
                                                                                                    if (com.egeetouch.egeetouch_manager.BLEService.check_battery_counter >= 600) {
                                                                                                        int r0 = com.egeetouch.egeetouch_manager.BLEService.check_battery_counter = 0;
                                                                                                        com.egeetouch.egeetouch_manager.BLEService.check_battery_routine_on = true;
                                                                                                        com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 67;
                                                                                                        break;
                                                                                                    } else {
                                                                                                        com.egeetouch.egeetouch_manager.BLEService.check_battery_routine_on = false;
                                                                                                        break;
                                                                                                    }
                                                                                                }
                                                                                                break;
                                                                                        }
                                                                                }
                                                                        }
                                                                }
                                                        }
                                                }
                                        }
                                }
                            }
                        } else {
                            java.lang.System.out.println("THIS IS SHUTDOWN LOCK");
                            if (com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1 != null && com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt != null) {
                                android.content.SharedPreferences r0 = android.preference.PreferenceManager.getDefaultSharedPreferences(com.egeetouch.egeetouch_manager.BLEService.this.getApplication());
                                if (!com.egeetouch.egeetouch_manager.BLEService.selected_lock_state && r0.getBoolean("auto_locking_boolean", false) && !com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2002") && !com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2003")) {
                                    com.egeetouch.egeetouch_manager.BLEService.pending_shut_down = true;
                                    com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 105;
                                } else {
                                    com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1.setValue(new byte[]{112, 0, 0, 0, 0, 0, 0});
                                    if (com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt.writeCharacteristic(com.egeetouch.egeetouch_manager.BLEService.this.characteristic_1)) {
                                        android.util.Log.i("TAG", "shut_down_lock");
                                        com.egeetouch.egeetouch_manager.BLEService.this.mhandler_task.removeCallbacks(r21);
                                        com.egeetouch.egeetouch_manager.BLEService.this.disconnect();
                                        com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 0;
                                    }
                                }
                            }
                        }
                    } else if (com.egeetouch.egeetouch_manager.BLEService.this.characteristic_4 != null && com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt != null) {
                        byte[] r2 = new byte[16];
                        android.database.Cursor r0 = com.egeetouch.egeetouch_manager.DatabaseVariable.db_user.rawQuery("SELECT * FROM User WHERE Lock = " + android.database.DatabaseUtils.sqlEscapeString(com.egeetouch.egeetouch_manager.BLEService.selected_lock_name), null);
                        com.egeetouch.egeetouch_manager.BLEService.User_number = r0.getCount();
                        java.lang.String[] r3 = new java.lang.String[40];
                        int r4 = 0;
                        while (r0.moveToNext()) {
                            r3[r4] = r0.getString(1);
                            com.egeetouch.egeetouch_manager.BLEService.User_current_number++;
                            r4++;
                        }
                        if (com.egeetouch.egeetouch_manager.BLEService.User_loop_number <= com.egeetouch.egeetouch_manager.BLEService.User_number) {
                            int r0 = com.egeetouch.egeetouch_manager.BLEService.User_loop_number + 32;
                            android.util.Log.i("", "user: " + java.lang.String.valueOf(r0));
                            r2[0] = 81;
                            r2[1] = (byte) r0;
                            android.util.Log.i("User", "User" + java.lang.String.valueOf(r0) + "User_loop_number: " + java.lang.String.valueOf(com.egeetouch.egeetouch_manager.BLEService.User_loop_number) + "Total Users: " + java.lang.String.valueOf(com.egeetouch.egeetouch_manager.BLEService.User_number));
                            for (int r0 = 0; r0 < r3[com.egeetouch.egeetouch_manager.BLEService.User_loop_number - 1].length(); r0++) {
                                android.util.Log.i("User", java.lang.String.valueOf(r3[com.egeetouch.egeetouch_manager.BLEService.User_loop_number - 1].length()) + ": " + java.lang.String.valueOf(r3[com.egeetouch.egeetouch_manager.BLEService.User_loop_number - 1].charAt(r0)));
                                r2[r0 + 2] = (byte) r3[com.egeetouch.egeetouch_manager.BLEService.User_loop_number - 1].charAt(r0);
                            }
                            for (int r0 = r3[com.egeetouch.egeetouch_manager.BLEService.User_loop_number - 1].length() + 2; r0 < 16; r0++) {
                                r2[r0] = 0;
                            }
                        } else {
                            byte r3 = 0;
                            r2[0] = 81;
                            r2[1] = (byte) (com.egeetouch.egeetouch_manager.BLEService.User_loop_number + 32);
                            int r0 = 2;
                            while (r0 < 16) {
                                r2[2] = r3;
                                r0++;
                                r3 = 0;
                            }
                        }
                        com.egeetouch.egeetouch_manager.BLEService.this.characteristic_4.setValue(r2);
                        if (com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt.writeCharacteristic(com.egeetouch.egeetouch_manager.BLEService.this.characteristic_4)) {
                            com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 33;
                        }
                    }
                } else if (com.egeetouch.egeetouch_manager.BLEService.this.characteristic_4 != null && com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt != null) {
                    byte[] r2 = new byte[16];
                    android.database.Cursor r0 = com.egeetouch.egeetouch_manager.DatabaseVariable.db_user.rawQuery("SELECT * FROM User WHERE Lock = " + android.database.DatabaseUtils.sqlEscapeString(com.egeetouch.egeetouch_manager.BLEService.selected_lock_name), null);
                    com.egeetouch.egeetouch_manager.BLEService.User_number = r0.getCount();
                    java.lang.String[] r3 = new java.lang.String[40];
                    int r4 = 0;
                    while (r0.moveToNext()) {
                        r3[r4] = r0.getString(0);
                        com.egeetouch.egeetouch_manager.BLEService.User_current_number++;
                        r4++;
                    }
                    if (com.egeetouch.egeetouch_manager.BLEService.User_loop_number <= com.egeetouch.egeetouch_manager.BLEService.User_number) {
                        r2[0] = 81;
                        r2[1] = (byte) com.egeetouch.egeetouch_manager.BLEService.User_loop_number;
                        for (int r0 = 0; r0 < r3[com.egeetouch.egeetouch_manager.BLEService.User_loop_number - 1].length(); r0++) {
                            r2[r0 + 2] = (byte) r3[com.egeetouch.egeetouch_manager.BLEService.User_loop_number - 1].charAt(r0);
                        }
                        for (int r0 = r3[com.egeetouch.egeetouch_manager.BLEService.User_loop_number - 1].length() + 2; r0 < 16; r0++) {
                            r2[r0] = 0;
                        }
                    } else {
                        byte r3 = 0;
                        r2[0] = 81;
                        r2[1] = (byte) com.egeetouch.egeetouch_manager.BLEService.User_loop_number;
                        int r0 = 2;
                        while (r0 < 16) {
                            r2[2] = r3;
                            r0++;
                            r3 = 0;
                        }
                    }
                    com.egeetouch.egeetouch_manager.BLEService.this.characteristic_4.setValue(r2);
                    if (com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt.writeCharacteristic(com.egeetouch.egeetouch_manager.BLEService.this.characteristic_4)) {
                        com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 32;
                    }
                }
                if (com.egeetouch.egeetouch_manager.BLEService.vicinity_on) {
                    try {
                        if (com.egeetouch.egeetouch_manager.BLEService.read_rssi_counter > 1) {
                            int r0 = com.egeetouch.egeetouch_manager.BLEService.read_rssi_counter = 0;
                            com.egeetouch.egeetouch_manager.BLEService.this.mBluetoothGatt.readRemoteRssi();
                            boolean r0 = com.egeetouch.egeetouch_manager.BLEService.got_new_rssi = false;
                        } else {
                            com.egeetouch.egeetouch_manager.BLEService.access$1408();
                        }
                    } catch (java.lang.Exception unused) {
                    }
                    if (com.egeetouch.egeetouch_manager.BLEService.got_new_rssi) {
                        com.egeetouch.egeetouch_manager.BLEService.this.current_rssi = com.egeetouch.egeetouch_manager.BLEService.rssi_value;
                        if (com.egeetouch.egeetouch_manager.BLEService.this.current_rssi > com.egeetouch.egeetouch_manager.BLEService.min_rssi || com.egeetouch.egeetouch_manager.BLEService.this.current_rssi < com.egeetouch.egeetouch_manager.BLEService.max_rssi) {
                            if (com.egeetouch.egeetouch_manager.BLEService.this.current_rssi > com.egeetouch.egeetouch_manager.BLEService.min_rssi) {
                                int r0 = com.egeetouch.egeetouch_manager.BLEService.past_rssi = com.egeetouch.egeetouch_manager.BLEService.min_rssi;
                            } else if (com.egeetouch.egeetouch_manager.BLEService.this.current_rssi < com.egeetouch.egeetouch_manager.BLEService.max_rssi) {
                                int r0 = com.egeetouch.egeetouch_manager.BLEService.past_rssi = com.egeetouch.egeetouch_manager.BLEService.max_rssi;
                            }
                        } else {
                            if (java.lang.Math.abs(com.egeetouch.egeetouch_manager.BLEService.this.current_rssi - com.egeetouch.egeetouch_manager.BLEService.past_rssi) >= 3) {
                                com.egeetouch.egeetouch_manager.BLEService.avg_rssi = com.egeetouch.egeetouch_manager.BLEService.averaging_rssi_value(com.egeetouch.egeetouch_manager.BLEService.this.current_rssi);
                            }
                            int r0 = com.egeetouch.egeetouch_manager.BLEService.past_rssi = com.egeetouch.egeetouch_manager.BLEService.this.current_rssi;
                        }
                        if ((com.egeetouch.egeetouch_manager.Fragment_BLE.current_zone == 2 && !com.egeetouch.egeetouch_manager.BLEService.range_far) || com.egeetouch.egeetouch_manager.Fragment_BLE.current_zone == 3) {
                            android.content.SharedPreferences r0 = android.preference.PreferenceManager.getDefaultSharedPreferences(com.egeetouch.egeetouch_manager.BLEService.this.getApplicationContext());
                            if (!com.egeetouch.egeetouch_manager.BLEService.selected_lock_state && r0.getBoolean("auto_locking_boolean", false) && !com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2002") && !com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2003")) {
                                com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 105;
                            }
                            if (!com.egeetouch.egeetouch_manager.BLEService.notify_left_ur_luggage && com.egeetouch.egeetouch_manager.Fragment_BLE.current_zone == 2 && !com.egeetouch.egeetouch_manager.BLEService.range_far) {
                                boolean r0 = com.egeetouch.egeetouch_manager.BLEService.notify_left_ur_luggage = true;
                                com.egeetouch.egeetouch_manager.BLEService r0 = com.egeetouch.egeetouch_manager.BLEService.this;
                                if (!r0.isAppIsInBackground(r0.getApplicationContext())) {
                                    com.egeetouch.egeetouch_manager.BLEService r0 = com.egeetouch.egeetouch_manager.BLEService.this;
                                }
                                com.egeetouch.egeetouch_manager.BLEService.generateNotification(com.egeetouch.egeetouch_manager.BLEService.this.getApplicationContext(), com.egeetouch.egeetouch_manager.BLEService.this.getResources().getString(com.egeetouch.egeetouch_manager.R.string.have_you_left_your_belonging_behind), android.net.Uri.parse("android.resource://" + com.egeetouch.egeetouch_manager.BLEService.this.getPackageName() + "/" + com.egeetouch.egeetouch_manager.R.raw.proximity_sound));
                            }
                            if (com.egeetouch.egeetouch_manager.BLEService.this.vicinity_count > 40) {
                                com.egeetouch.egeetouch_manager.BLEService.this.vicinity_count = 0;
                                if (com.egeetouch.egeetouch_manager.BLEService.proximity_sound == 1) {
                                    android.media.MediaPlayer r0 = android.media.MediaPlayer.create(com.egeetouch.egeetouch_manager.BLEService.this.getApplicationContext(), (int) com.egeetouch.egeetouch_manager.R.raw.proximity_sound);
                                    r0.setOnCompletionListener(new android.media.MediaPlayer.OnCompletionListener() { // from class: com.egeetouch.egeetouch_manager.BLEService.8.1
                                        {
                                        }

                                        @Override // android.media.MediaPlayer.OnCompletionListener
                                        public void onCompletion(android.media.MediaPlayer r1) {
                                            r1.stop();
                                            r1.reset();
                                            r1.release();
                                        }
                                    });
                                    r0.start();
                                    ((android.os.Vibrator) com.egeetouch.egeetouch_manager.BLEService.this.getApplicationContext().getSystemService("vibrator")).vibrate(500L);
                                } else if (com.egeetouch.egeetouch_manager.BLEService.proximity_sound == 2) {
                                    ((android.os.Vibrator) com.egeetouch.egeetouch_manager.BLEService.this.getApplicationContext().getSystemService("vibrator")).vibrate(500L);
                                }
                            } else {
                                com.egeetouch.egeetouch_manager.BLEService.access$2308(com.egeetouch.egeetouch_manager.BLEService.this);
                            }
                        }
                    }
                }
                com.egeetouch.egeetouch_manager.BLEService r0 = com.egeetouch.egeetouch_manager.BLEService.this;
                if (!r0.isAppIsInBackground(r0.getApplicationContext())) {
                    com.egeetouch.egeetouch_manager.BLEService r0 = com.egeetouch.egeetouch_manager.BLEService.this;
                    if (r0.isScreenOn(r0.getApplicationContext())) {
                        if (com.egeetouch.egeetouch_manager.BLEService.this.mhandler_task_low_power == null || !com.egeetouch.egeetouch_manager.BLEService.handler_LP_started) {
                            r2 = 0;
                        } else {
                            com.egeetouch.egeetouch_manager.BLEService.this.mhandler_task_low_power.removeCallbacks(com.egeetouch.egeetouch_manager.BLEService.this.mStopLowPower);
                            r2 = 0;
                            boolean r0 = com.egeetouch.egeetouch_manager.BLEService.handler_LP_started = false;
                            com.egeetouch.egeetouch_manager.BLEService.this.mhandler_task_low_power = null;
                        }
                        if (com.egeetouch.egeetouch_manager.BLEService.trigger_low_power_mode) {
                            com.egeetouch.egeetouch_manager.BLEService.trigger_low_power_mode = r2;
                            android.util.Log.i("TAG", "quit_low_power_mode");
                            com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 119;
                            com.egeetouch.egeetouch_manager.BLEService.grace_period_for_LP_mode_counter = r2;
                            com.egeetouch.egeetouch_manager.BLEService.grace_period_for_LP_mode_is_over = true;
                        }
                        if (!com.egeetouch.egeetouch_manager.BLEService.trigger_low_power_mode) {
                            r2 = true;
                            com.egeetouch.egeetouch_manager.BLEService.grace_period_for_LP_mode_counter++;
                            if (com.egeetouch.egeetouch_manager.BLEService.grace_period_for_LP_mode_counter > 80) {
                                com.egeetouch.egeetouch_manager.BLEService.grace_period_for_LP_mode_is_over = true;
                            } else {
                                com.egeetouch.egeetouch_manager.BLEService.grace_period_for_LP_mode_is_over = false;
                            }
                        } else {
                            r2 = true;
                            com.egeetouch.egeetouch_manager.BLEService.grace_period_for_LP_mode_is_over = true;
                        }
                        if (com.egeetouch.egeetouch_manager.BLEService.notification_stay_connected <= 6000 && !com.egeetouch.egeetouch_manager.BLEService.notification_stay_connected_showed1) {
                            com.egeetouch.egeetouch_manager.BLEService.notification_stay_connected_showed1 = r2;
                            com.egeetouch.egeetouch_manager.BLEService.generateNotification(com.egeetouch.egeetouch_manager.BLEService.this.getApplicationContext(), com.egeetouch.egeetouch_manager.BLEService.this.getResources().getString(com.egeetouch.egeetouch_manager.R.string.lock_stay_connected), android.net.Uri.parse("android.resource://" + com.egeetouch.egeetouch_manager.BLEService.this.getPackageName() + "/" + com.egeetouch.egeetouch_manager.R.raw.vicinity));
                        } else if (com.egeetouch.egeetouch_manager.BLEService.notification_stay_connected <= 12000 && !com.egeetouch.egeetouch_manager.BLEService.notification_stay_connected_showed2) {
                            com.egeetouch.egeetouch_manager.BLEService.notification_stay_connected_showed2 = true;
                            com.egeetouch.egeetouch_manager.BLEService.generateNotification(com.egeetouch.egeetouch_manager.BLEService.this.getApplicationContext(), com.egeetouch.egeetouch_manager.BLEService.this.getResources().getString(com.egeetouch.egeetouch_manager.R.string.lock_stay_connected), android.net.Uri.parse("android.resource://" + com.egeetouch.egeetouch_manager.BLEService.this.getPackageName() + "/" + com.egeetouch.egeetouch_manager.R.raw.vicinity));
                        } else {
                            com.egeetouch.egeetouch_manager.BLEService.notification_stay_connected++;
                        }
                    }
                }
                if (!com.egeetouch.egeetouch_manager.BLEService.trigger_low_power_mode && com.egeetouch.egeetouch_manager.BLEService.this.mhandler_task_low_power == null) {
                    android.util.Log.i("TAG", "postDelayed(mStopLowPower)");
                    com.egeetouch.egeetouch_manager.BLEService.this.mhandler_task_low_power = new android.os.Handler();
                    boolean r0 = com.egeetouch.egeetouch_manager.BLEService.handler_LP_started = com.egeetouch.egeetouch_manager.BLEService.this.mhandler_task_low_power.postDelayed(com.egeetouch.egeetouch_manager.BLEService.this.mStopLowPower, 15000L);
                }
                if (!com.egeetouch.egeetouch_manager.BLEService.trigger_low_power_mode) {
                }
                if (com.egeetouch.egeetouch_manager.BLEService.notification_stay_connected <= 6000) {
                }
                if (com.egeetouch.egeetouch_manager.BLEService.notification_stay_connected <= 12000) {
                }
                com.egeetouch.egeetouch_manager.BLEService.notification_stay_connected++;
            } else if (com.egeetouch.egeetouch_manager.BLEService.mConnectionState == 0) {
                if (com.egeetouch.egeetouch_manager.BLEService.delay_for_4_sec_counter > 80) {
                    boolean r0 = com.egeetouch.egeetouch_manager.BLEService.delay_for_4_sec = true;
                } else {
                    com.egeetouch.egeetouch_manager.BLEService.access$2608();
                }
                if (!com.egeetouch.egeetouch_manager.BLEService.notify_disconnected && com.egeetouch.egeetouch_manager.BLEService.vicinity_on && com.egeetouch.egeetouch_manager.BLEService.delay_for_4_sec && com.egeetouch.egeetouch_manager.BLEService.grace_period_for_LP_mode_is_over) {
                    boolean r0 = com.egeetouch.egeetouch_manager.BLEService.notify_disconnected = true;
                    com.egeetouch.egeetouch_manager.BLEService r0 = com.egeetouch.egeetouch_manager.BLEService.this;
                    if (!r0.isAppIsInBackground(r0.getApplicationContext())) {
                        com.egeetouch.egeetouch_manager.BLEService r0 = com.egeetouch.egeetouch_manager.BLEService.this;
                        if (r0.isScreenOn(r0.getApplicationContext())) {
                            if (com.egeetouch.egeetouch_manager.BLEService.proximity_sound == 1) {
                                android.media.MediaPlayer r0 = android.media.MediaPlayer.create(com.egeetouch.egeetouch_manager.BLEService.this.getApplicationContext(), (int) com.egeetouch.egeetouch_manager.R.raw.tiny_bell_unstop);
                                r0.setOnCompletionListener(new android.media.MediaPlayer.OnCompletionListener() { // from class: com.egeetouch.egeetouch_manager.BLEService.8.2
                                    {
                                    }

                                    @Override // android.media.MediaPlayer.OnCompletionListener
                                    public void onCompletion(android.media.MediaPlayer r1) {
                                        r1.stop();
                                        r1.reset();
                                        r1.release();
                                    }
                                });
                                r0.start();
                            } else if (com.egeetouch.egeetouch_manager.BLEService.proximity_sound == 2) {
                                ((android.os.Vibrator) com.egeetouch.egeetouch_manager.BLEService.this.getApplicationContext().getSystemService("vibrator")).vibrate(500L);
                            }
                        }
                    }
                    com.egeetouch.egeetouch_manager.BLEService.generateNotification(com.egeetouch.egeetouch_manager.BLEService.this.getApplicationContext(), com.egeetouch.egeetouch_manager.BLEService.this.getResources().getString(com.egeetouch.egeetouch_manager.R.string.lost_connection_to_your_lock), android.net.Uri.parse("android.resource://" + com.egeetouch.egeetouch_manager.BLEService.this.getPackageName() + "/" + com.egeetouch.egeetouch_manager.R.raw.tiny_bell_unstop));
                }
            }
            com.egeetouch.egeetouch_manager.BLEService.this.mhandler_task.postDelayed(r21, 500L);
        }
    };
    private Runnable mStopLowPower = new Runnable() { // from class: com.egeetouch.egeetouch_manager.BLEService.15
        @Override // java.lang.Runnable
        public void run() {
            BLEService.Ble_Mode = 118;
        }
    };
    Runnable taskBattery = new Runnable() { // from class: com.egeetouch.egeetouch_manager.BLEService.17
        @Override // java.lang.Runnable
        public void run() {
            if (BLEService.Ble_Mode == 0) {
                BLEService.check_battery_routine_on = true;
                if (BLEService.selected_lock_model.equals("GT2100") || BLEService.selected_lock_model.equals("GT2500") || BLEService.selected_lock_model.equals("GT2550")) {
                    BLEService.Ble_Mode = 67;
                } else if (BLEService.selected_lock_model.equals("GT5100") || BLEService.selected_lock_model.equals("GT5107") || BLEService.selected_lock_model.equals("GT5109") || BLEService.selected_lock_model.equals("GT5300")) {
                    BLEService.Ble_Mode = BLEService.Request_lotoBatteryRecords;
                }
            }
            BLEService.this.mhandler_battery.postDelayed(this, WorkRequest.DEFAULT_BACKOFF_DELAY_MILLIS);
        }
    };
    Runnable watchAuditRun = new Runnable() { // from class: com.egeetouch.egeetouch_manager.BLEService.21
        @Override // java.lang.Runnable
        public void run() {
            if (BLEService.this.sharedPreferences.getString(BLEService.this.auditKey, "").length() > 0 && !BLEService.this.sending && Helper_Network.haveNetworkConnection(MainActivity.context)) {
                BLEService.this.sending = true;
                Firebase_Data_management.database = FirebaseDatabase.getInstance();
                final String string = BLEService.this.sharedPreferences.getString(BLEService.this.auditKey, "");
                String[] split = string.split("`");
                int length = split.length / 5;
                final int i = length * 4;
                final int[] iArr = {0};
                String[] strArr = new String[length];
                String[] strArr2 = new String[length];
                String[] strArr3 = new String[length];
                String[] strArr4 = new String[length];
                String[] strArr5 = new String[length];
                for (int i2 = 0; i2 < length; i2++) {
                    int i3 = 5 * i2;
                    strArr[i2] = split[i3];
                    strArr2[i2] = split[i3 + 1];
                    strArr3[i2] = split[i3 + 2];
                    strArr4[i2] = split[i3 + 3];
                    strArr5[i2] = split[i3 + 4];
                }
                for (int i4 = 0; i4 < length; i4++) {
                    DatabaseReference push = Firebase_Data_management.database.getReference("watchAuditTrail").child(strArr[i4]).push();
                    push.child("time").setValue((Object) strArr2[i4], new DatabaseReference.CompletionListener() { // from class: com.egeetouch.egeetouch_manager.BLEService.21.1
                        @Override // com.google.firebase.database.DatabaseReference.CompletionListener
                        public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                            if (databaseError != null) {
                                System.out.println("Data could not be saved " + databaseError.getMessage());
                                return;
                            }
                            int[] iArr2 = iArr;
                            iArr2[0] = iArr2[0] + 1;
                            if (iArr2[0] >= i) {
                                SharedPreferences.Editor edit = BLEService.this.sharedPreferences.edit();
                                String replace = BLEService.this.sharedPreferences.getString(BLEService.this.auditKey, "").replace(string, "");
                                if (replace.length() > 0 && String.valueOf(replace.charAt(0)).equals("`")) {
                                    replace = replace.substring(1, replace.length() - 1);
                                }
                                edit.putString(BLEService.this.auditKey, replace);
                                edit.apply();
                                BLEService.this.sending = false;
                            }
                        }
                    });
                    push.child("lockedStatus").setValue((Object) strArr3[i4], new DatabaseReference.CompletionListener() { // from class: com.egeetouch.egeetouch_manager.BLEService.21.2
                        @Override // com.google.firebase.database.DatabaseReference.CompletionListener
                        public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                            if (databaseError != null) {
                                System.out.println("Data could not be saved " + databaseError.getMessage());
                                return;
                            }
                            int[] iArr2 = iArr;
                            iArr2[0] = iArr2[0] + 1;
                            if (iArr2[0] >= i) {
                                SharedPreferences.Editor edit = BLEService.this.sharedPreferences.edit();
                                String replace = BLEService.this.sharedPreferences.getString(BLEService.this.auditKey, "").replace(string, "");
                                if (replace.length() > 0 && String.valueOf(replace.charAt(0)).equals("`")) {
                                    replace = replace.substring(1, replace.length() - 1);
                                }
                                edit.putString(BLEService.this.auditKey, replace);
                                edit.apply();
                                BLEService.this.sending = false;
                            }
                        }
                    });
                    push.child("name").setValue((Object) strArr4[i4], new DatabaseReference.CompletionListener() { // from class: com.egeetouch.egeetouch_manager.BLEService.21.3
                        @Override // com.google.firebase.database.DatabaseReference.CompletionListener
                        public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                            if (databaseError != null) {
                                System.out.println("Data could not be saved " + databaseError.getMessage());
                                return;
                            }
                            int[] iArr2 = iArr;
                            iArr2[0] = iArr2[0] + 1;
                            if (iArr2[0] >= i) {
                                SharedPreferences.Editor edit = BLEService.this.sharedPreferences.edit();
                                String replace = BLEService.this.sharedPreferences.getString(BLEService.this.auditKey, "").replace(string, "");
                                if (replace.length() > 0 && String.valueOf(replace.charAt(0)).equals("`")) {
                                    replace = replace.substring(1, replace.length() - 1);
                                }
                                edit.putString(BLEService.this.auditKey, replace);
                                edit.apply();
                                BLEService.this.sending = false;
                            }
                        }
                    });
                    push.child("uuid").setValue((Object) strArr5[i4], new DatabaseReference.CompletionListener() { // from class: com.egeetouch.egeetouch_manager.BLEService.21.4
                        @Override // com.google.firebase.database.DatabaseReference.CompletionListener
                        public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                            if (databaseError != null) {
                                System.out.println("Data could not be saved " + databaseError.getMessage());
                                return;
                            }
                            int[] iArr2 = iArr;
                            iArr2[0] = iArr2[0] + 1;
                            if (iArr2[0] >= i) {
                                SharedPreferences.Editor edit = BLEService.this.sharedPreferences.edit();
                                String replace = BLEService.this.sharedPreferences.getString(BLEService.this.auditKey, "").replace(string, "");
                                if (replace.length() > 0 && String.valueOf(replace.charAt(0)).equals("`")) {
                                    replace = replace.substring(1, replace.length() - 1);
                                }
                                edit.putString(BLEService.this.auditKey, replace);
                                edit.apply();
                                BLEService.this.sending = false;
                            }
                        }
                    });
                }
            }
            BLEService.this.watchAuditHandler.postDelayed(this, DfuServiceInitiator.DEFAULT_SCAN_TIMEOUT);
        }
    };
    private ScanCallback mScanCallbackRepair = new ScanCallback() { // from class: com.egeetouch.egeetouch_manager.BLEService.29
        @Override // android.bluetooth.le.ScanCallback
        public void onScanResult(int i, ScanResult scanResult) {
            super.onScanResult(i, scanResult);
            BluetoothDevice device = scanResult.getDevice();
            if (device.getName() == null || !device.getName().startsWith("Dfu")) {
                return;
            }
            DfuActivity.foundAffected = true;
            DfuActivity.affectedLockName = device.getName();
            DfuActivity.affectedLockAddress = device.getAddress();
            BLEService.this.connectRepair(device.getAddress());
            BLEService.this.stopScan();
        }
    };
    final BluetoothGattCallback mGattCallbackRepair = new BluetoothGattCallback() { // from class: com.egeetouch.egeetouch_manager.BLEService.30
        @Override // android.bluetooth.BluetoothGattCallback
        public void onConnectionStateChange(BluetoothGatt bluetoothGatt, int i, int i2) {
            super.onConnectionStateChange(bluetoothGatt, i, i2);
            if (i2 != 0) {
                if (i2 == 2) {
                    Log.i("mGattCallbackRepair", "CONNECTED");
                    DfuActivity.connectAffected = true;
                    return;
                }
                Log.i("mGattCallbackRepair", "STATE_OTHER");
                return;
            }
            BLEService.this.mBluetoothGatt.close();
            BLEService.this.mBluetoothGatt = null;
            BLEService.egeetouchDeviceFound = false;
            DfuActivity.connectAffected = false;
            DfuActivity.foundAffected = false;
            Log.i("mGattCallbackRepair", "DISCONNECTED");
        }
    };

    static /* synthetic */ int access$1308() {
        int i = check_battery_counter;
        check_battery_counter = i + 1;
        return i;
    }

    static /* synthetic */ int access$1408() {
        int i = read_rssi_counter;
        read_rssi_counter = i + 1;
        return i;
    }

    static /* synthetic */ int access$2308(BLEService bLEService) {
        int i = bLEService.vicinity_count;
        bLEService.vicinity_count = i + 1;
        return i;
    }

    static /* synthetic */ int access$2608() {
        int i = delay_for_4_sec_counter;
        delay_for_4_sec_counter = i + 1;
        return i;
    }

    public static void setAddLockListener(AddLockListener addLockListener2) {
        addLockListener = addLockListener2;
    }

    public void startscanning() {
        System.out.println("HEY startscanning is getting called");
        System.out.println("HEY clearing parsedIp45SerialNumber");
        parsedIp45SerialNumber = "";
        if (Build.VERSION.SDK_INT >= 21) {
            System.out.println("HEY android version is > 21");
            this.mLEScanner = this.mBluetoothAdapter.getBluetoothLeScanner();
            this.settings = new ScanSettings.Builder().setScanMode(2).build();
            this.filters = new ArrayList();
        }
        Handler handler = new Handler();
        this.mhandler = handler;
        handler.post(this.mStartRunnable);
        this.mhandler.postDelayed(this.mStopRunnable, 15000L);
        Handler handler2 = new Handler();
        this.mhandler_task = handler2;
        handler2.post(this.task);
    }

    /* loaded from: classes.dex */
    public class LocalBinder extends Binder {
        public LocalBinder() {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public BLEService getService() {
            return BLEService.this;
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.mBinder;
    }

    @Override // android.app.Service
    public void onCreate() {
        this.lotoInfo = LotoInfo.getInstance();
        mainActivity = new MainActivity();
        this.lockInfo = CurrentSelectedLockInfo.getInstance();
        BluetoothManager bluetoothManager = (BluetoothManager) getSystemService("bluetooth");
        mBluetoothManager = bluetoothManager;
        this.mBluetoothAdapter = bluetoothManager.getAdapter();
        isIp45Lock = false;
        BluetoothAdapter bluetoothAdapter = this.mBluetoothAdapter;
        if (bluetoothAdapter != null && !bluetoothAdapter.isEnabled() && Build.VERSION.SDK_INT < 31) {
            this.mBluetoothAdapter.enable();
        }
        super.onCreate();
        System.out.println("Hello BLEService onCreate()!!");
        final Handler handler = new Handler();
        Runnable runnable = new Runnable() { // from class: com.egeetouch.egeetouch_manager.BLEService.1
            @Override // java.lang.Runnable
            public void run() {
                System.out.println("CHECKING BLE MODE :" + BLEService.Ble_Mode + "Checking when tag id come :" + BLEService.tagID);
                handler.postDelayed(this, 1000L);
            }
        };
        this.mhandler_battery.postDelayed(this.taskBattery, WorkRequest.DEFAULT_BACKOFF_DELAY_MILLIS);
        try {
            FirebaseApp.initializeApp(MainActivity.context, new FirebaseOptions.Builder().setApplicationId("1:793477089265:android:2c6bbd1d22610f6144e650").setDatabaseUrl("https://egeetouchindustrial.firebaseio.com/").setApiKey("AIzaSyACfHrrc0ARWI_iyx9XRcFE-1Gk6AHtmc8").build(), "industrial");
        } catch (Exception e) {
            e.printStackTrace();
        }
        commercialDatabase = FirebaseDatabase.getInstance(FirebaseApp.getInstance("industrial"));
        handler.postDelayed(runnable, 1000L);
        this.watchAuditHandler.post(this.watchAuditRun);
    }

    @Override // android.app.Service
    public void onRebind(Intent intent) {
        super.onRebind(intent);
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        return super.onStartCommand(intent, i, i2);
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        if (mIsBound) {
            unbindService(this.mConnection);
            mIsBound = false;
        }
        return super.onUnbind(intent);
    }

    private void broadcastUpdate(String str) {
        sendBroadcast(new Intent(str));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void broadcastUpdate(String str, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        new Intent(str);
        System.out.println("Hello broadcastUpdate():" + bluetoothGattCharacteristic.getUuid());
        process_incoming_data(bluetoothGattCharacteristic);
    }

    private boolean isBluetoothEnabled() {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (Build.VERSION.SDK_INT >= 31) {
            if (ContextCompat.checkSelfPermission(MainActivity.context, "android.permission.BLUETOOTH_SCAN") != 0 || ContextCompat.checkSelfPermission(MainActivity.context, "android.permission.BLUETOOTH_CONNECT") != 0) {
                makeToast(getResources().getString(R.string.bluetooth_permission_revoked));
            } else if (defaultAdapter.isEnabled()) {
                return true;
            } else {
                makeToast(getResources().getString(R.string.no_bluetooth));
            }
        } else if (defaultAdapter.isEnabled()) {
            return true;
        } else {
            defaultAdapter.enable();
        }
        return false;
    }

    public void startScan() {
        if (isBluetoothEnabled()) {
            if (Build.VERSION.SDK_INT >= 21) {
                System.out.println("Hello StartScan() >=21!");
                this.mLEScanner.startScan(this.filters, this.settings, this.mScanCallback);
                return;
            }
            this.mBluetoothAdapter.startLeScan(this);
            System.out.println("Hello StartScan() <=21!");
        }
    }

    public void stopScan() {
        if (isBluetoothEnabled()) {
            System.out.println("HEY stopScan getting called, runnables stopping! ");
            if (Build.VERSION.SDK_INT >= 21) {
                BluetoothLeScanner bluetoothLeScanner = this.mLEScanner;
                if (bluetoothLeScanner != null) {
                    bluetoothLeScanner.stopScan(this.mScanCallback);
                }
            } else {
                this.mBluetoothAdapter.stopLeScan(this);
            }
            this.mhandler.removeCallbacks(this.mStopRunnable);
            this.mhandler.removeCallbacksAndMessages(null);
            addTimeoutHandler.removeCallbacks(this.runAddTimeout);
        }
    }

    public void enable_BLE() {
        System.out.println("Hello enable_BLE()");
        BluetoothAdapter bluetoothAdapter = this.mBluetoothAdapter;
        if (bluetoothAdapter == null || bluetoothAdapter.isEnabled()) {
            return;
        }
        this.mBluetoothAdapter.enable();
        System.out.println("Hello enabled BLE");
    }

    @Override // android.bluetooth.BluetoothAdapter.LeScanCallback
    public void onLeScan(BluetoothDevice bluetoothDevice, int i, byte[] bArr) {
        if (MainActivity.scanning_new_lock) {
            if (bluetoothDevice.getName() == null || !bluetoothDevice.getName().contains("eGeeTouch")) {
                return;
            }
            stopScan();
            selected_lock_address = bluetoothDevice.getAddress();
            connection_response = connect(bluetoothDevice.getAddress());
            Log.i("TAG", "connection_response: scanning_new_lock " + connection_response);
        } else if (MainActivity.lock_dfu_repair) {
            if (bluetoothDevice.getName().equalsIgnoreCase("dfutarg")) {
                stopScan();
                connectRepair(bluetoothDevice.getAddress());
            }
        } else {
            stopScan();
            selected_lock_address = bluetoothDevice.getAddress();
            connection_response = connect(bluetoothDevice.getAddress());
            Log.i("TAG", "connection_response: " + connection_response);
        }
    }

    public boolean connect(String str) {
        BluetoothDevice remoteDevice;
        if (this.mBluetoothAdapter == null || str == null) {
            Log.w("connecting situation", "BluetoothAdapter not initialized or unspecified address.");
            return false;
        }
        String str2 = mBluetoothDeviceAddress;
        if (str2 != null && str.equals(str2) && this.mBluetoothGatt != null) {
            Log.d("connecting situation", "Trying to use an existing mBluetoothGatt for connection.");
            if (this.mBluetoothGatt.connect()) {
                mConnectionState = 1;
                this.lockInfo.setConnectionState(1);
                return true;
            }
            return false;
        }
        try {
            remoteDevice = this.mBluetoothAdapter.getRemoteDevice(str);
        } catch (Exception e) {
            System.out.println("Hello Bluetooth Exception e" + e);
        }
        if (remoteDevice == null) {
            Log.w("connecting situation", "Device not found.  Unable to connect.");
            return false;
        }
        this.mBluetoothGatt = remoteDevice.connectGatt(this, false, this.mGattCallback);
        Log.d("connecting situation", "Trying to create a new connection.");
        mBluetoothDeviceAddress = str;
        mConnectionState = 1;
        this.lockInfo.setConnectionState(1);
        return true;
    }

    public void disconnect() {
        BluetoothGatt bluetoothGatt;
        System.out.println("Hello checking the loto BLEService disconnection()!!!");
        if (this.mBluetoothAdapter == null || (bluetoothGatt = this.mBluetoothGatt) == null) {
            Log.w("ContentValues", "BluetoothAdapter not initialized");
            return;
        }
        bluetoothGatt.disconnect();
        Log.i("TAG", "disconnect()");
    }

    public void link_service_up() {
        System.out.println("HEY link_service_up getting called!");
        BluetoothGattService service = this.mBluetoothGatt.getService(Battery_Service_UUID);
        this.batteryService = service;
        if (service != null) {
            this.characteristic_battery = service.getCharacteristic(UUID_battery);
        } else {
            System.out.println("Hello checking the loto BLEService serviceObtained false !! 1");
            this.service_obtained_correct = false;
        }
        BluetoothGattService service2 = this.mBluetoothGatt.getService(Device_Service_UUID);
        this.deviceService = service2;
        if (service2 != null) {
            this.characteristic_model = service2.getCharacteristic(UUID_model);
            this.characteristic_version = this.deviceService.getCharacteristic(UUID_version);
        } else {
            System.out.println("Hello checking the loto BLEService serviceObtained false !! 2");
            this.service_obtained_correct = false;
        }
        BluetoothGattService service3 = this.mBluetoothGatt.getService(lock_Service_UUID_bt_ip45);
        this.lockService = service3;
        if (service3 != null) {
            if (selected_lock_model.equals("GT2100")) {
                link_characteristic_up(this.lockService);
            } else {
                service_disconnect = true;
                disconnect();
            }
        } else if (selected_lock_model.equals("GT2500") || selected_lock_model.equals("GT2550")) {
            BluetoothGattService service4 = this.mBluetoothGatt.getService(lock_Service_UUID_bt_ip66);
            this.lockService = service4;
            if (service4 != null) {
                System.out.println("Hello checking the ip66 lock Service " + parsedIp45SerialNumber);
                if ((selected_lock_model.equals("GT2500") && parsedIp45SerialNumber.startsWith("F")) || (selected_lock_model.equals("GT2550") && parsedIp45SerialNumber.startsWith("G"))) {
                    link_characteristic_up(this.lockService);
                } else {
                    service_disconnect = true;
                    disconnect();
                }
            }
        } else {
            BluetoothGattService service5 = this.mBluetoothGatt.getService(lock_Service_UUID_luggage_padlock);
            this.lockService = service5;
            if (service5 != null) {
                if (selected_lock_model.equals("GT3002")) {
                    link_characteristic_up(this.lockService);
                } else {
                    service_disconnect = true;
                    disconnect();
                }
            } else {
                BluetoothGattService service6 = this.mBluetoothGatt.getService(lock_Service_UUID_bt_padlock);
                this.lockService = service6;
                if (service6 != null) {
                    if (selected_lock_model.equals("GT2002")) {
                        link_characteristic_up(this.lockService);
                    } else {
                        service_disconnect = true;
                        disconnect();
                    }
                } else {
                    BluetoothGattService service7 = this.mBluetoothGatt.getService(lock_Service_UUID_tvl_padlock);
                    this.lockService = service7;
                    if (service7 != null) {
                        if (selected_lock_model.equals("GT1000")) {
                            link_characteristic_up(this.lockService);
                        } else {
                            service_disconnect = true;
                            disconnect();
                        }
                    } else {
                        this.lockService = this.mBluetoothGatt.getService(lock_Service_UUID_bt_loto);
                        System.out.println("Hello checking the loto BLEService !" + selected_lock_model.equals("GT5100") + " lockService :" + (this.lockService != null));
                        if (this.lockService != null) {
                            if (selected_lock_model.equals("GT5100") || selected_lock_model.equals("GT5107") || selected_lock_model.equals("GT5109") || selected_lock_model.equals("GT5300")) {
                                link_characteristic_up(this.lockService);
                            } else {
                                service_disconnect = true;
                                disconnect();
                            }
                        } else {
                            System.out.println("Hello checking the loto BLEService serviceObtained false !!");
                            this.service_obtained_correct = false;
                        }
                    }
                }
            }
        }
        if (this.service_obtained_correct.booleanValue()) {
            if (MainActivity.scanning_new_lock) {
                Ble_Mode = check_model;
                System.out.println("HEY service_obtained_correct calling Verify_Admin_Password_new_lock -> NEW TO PHONE not necessarily 123456");
            } else {
                if (selected_lock_model.equals("GTIP661")) {
                    this.mhandler_task.post(this.task);
                    Ble_Mode = 0;
                    verified_password_done = true;
                    Ble_Mode = 67;
                } else {
                    Ble_Mode = 97;
                }
                System.out.println("HEY service_obtained_correct calling Verify_Admin_Password -> NEW TO PHONE not necessarily 123456");
            }
            if (MainActivity.watch_connected) {
                boolean z = selected_lock_state;
                System.out.println("Hello sendData:Connection1");
            }
            Log.i("TAG", "service_obtained_correct: true");
            mConnectionState = 2;
            this.lockInfo.setConnectionState(2);
            device_is_connected = true;
            notify_disconnected = false;
            notify_left_ur_luggage = false;
            delay_for_4_sec_counter = 0;
            delay_for_4_sec = false;
            if (!MainActivity.scanning_new_lock && verified_password_done) {
                System.out.println("Hello Fragment Ble check 3!!");
            }
            Log.i("TAG", "scanning_new_lock: " + MainActivity.scanning_new_lock);
            return;
        }
        Log.i("TAG", "service_obtained_correct: false");
        mConnectionState = 0;
        this.lockInfo.setConnectionState(0);
        System.out.println("Hello checking the loto BLEService disconnection()!!! serviceObtained False");
        service_disconnect = true;
        disconnect();
    }

    public void link_characteristic_up(BluetoothGattService bluetoothGattService) {
        UUID uuid = UUID_1;
        this.unlocking_characteristic = bluetoothGattService.getCharacteristic(uuid);
        this.characteristic_1 = bluetoothGattService.getCharacteristic(uuid);
        this.characteristic_2 = bluetoothGattService.getCharacteristic(UUID_2);
        this.characteristic_3 = bluetoothGattService.getCharacteristic(UUID_3);
        this.characteristic_4 = bluetoothGattService.getCharacteristic(UUID_4);
        this.characteristic_5 = bluetoothGattService.getCharacteristic(UUID_5);
        if (selected_lock_model.equals("GT2500") || selected_lock_model.equals("GT2550")) {
            mConnectionState = 4;
            MainActivity.no_lock_found = false;
            this.mhandler_task.removeCallbacks(this.task);
            System.out.println("Hello checking the ip66 Runnable Remove 2");
            egeetouchDeviceFound = true;
            this.mhandler_task.post(this.task);
            System.out.println("Hello checking the ip66 connection testing 8 State: " + mConnectionState);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void generateNotification(Context context, String str, Uri uri) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(603979776);
        ((NotificationManager) context.getSystemService("notification")).notify(0, new NotificationCompat.Builder(context).setSmallIcon(R.drawable.ic_launcher).setContentTitle(context.getString(R.string.app_name)).setContentIntent(PendingIntent.getActivity(context, 0, intent, 67108864)).setPriority(1).setContentText(str).setAutoCancel(true).setSound(uri).setVibrate(new long[]{0, 200, 50, 200}).build());
    }

    /* JADX WARN: Removed duplicated region for block: B:1179:0x2b56  */
    /* JADX WARN: Removed duplicated region for block: B:1182:0x2b7b  */
    /* JADX WARN: Removed duplicated region for block: B:1183:0x2b84  */
    /* JADX WARN: Removed duplicated region for block: B:1340:0x3248  */
    /* JADX WARN: Removed duplicated region for block: B:1372:0x3349  */
    /* JADX WARN: Removed duplicated region for block: B:1375:0x3372  */
    /* JADX WARN: Removed duplicated region for block: B:1376:0x3386  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void process_incoming_data(android.bluetooth.BluetoothGattCharacteristic r21) {
        java.lang.String r4;
        java.lang.String r7;
        java.lang.String r9;
        java.lang.String r6;
        int r2;
        int r1;
        java.lang.String r2;
        java.lang.String r1;
        int r2;
        int r3;
        boolean r5;
        int r2;
        double r10;
        java.lang.String r1;
        int r6;
        boolean r1;
        boolean r2;
        char r4;
        int r4;
        boolean r3;
        boolean r4;
        java.lang.System.out.println("Hello checking the UUID:" + r21.getUuid());
        if (com.egeetouch.egeetouch_manager.BLEService.UUID_battery.equals(r21.getUuid())) {
            byte[] r1 = r21.getValue();
            com.egeetouch.egeetouch_manager.BLEService.get_battery_level_done = true;
            if (com.egeetouch.egeetouch_manager.BLEService.first_battery_check) {
                com.egeetouch.egeetouch_manager.BLEService.battery_level = r1[0] & kotlin.UByte.MAX_VALUE;
                com.egeetouch.egeetouch_manager.BLEService.first_battery_check = false;
                com.egeetouch.egeetouch_manager.BLEService.get_battery_level_done = true;
            } else {
                if (r20.battery_list.size() < 5) {
                    r20.battery_list.add(java.lang.Integer.valueOf(r1[0] & kotlin.UByte.MAX_VALUE));
                }
                if (r20.battery_list.size() >= 5) {
                    java.util.ArrayList r1 = new java.util.ArrayList();
                    for (int r5 = 0; r5 < r20.battery_list.size(); r5++) {
                        if (!r1.contains(r20.battery_list.get(r5))) {
                            r1.add(r20.battery_list.get(r5));
                        }
                    }
                    java.util.ArrayList r5 = new java.util.ArrayList();
                    for (int r9 = 0; r9 < r1.size(); r9++) {
                        r5.add(r9, 0);
                    }
                    for (int r8 = 0; r8 < r20.battery_list.size(); r8++) {
                        r5.set(r1.indexOf(r20.battery_list.get(r8)), java.lang.Integer.valueOf(((java.lang.Integer) r5.get(r1.indexOf(r20.battery_list.get(r8)))).intValue() + 1));
                    }
                    boolean r9 = false;
                    int r10 = 0;
                    int r4 = -1;
                    for (int r8 = 0; r8 < r5.size(); r8++) {
                        if (((java.lang.Integer) r5.get(r8)).intValue() > r4) {
                            r4 = ((java.lang.Integer) r5.get(r8)).intValue();
                            r9 = false;
                            r10 = r8;
                        } else if (((java.lang.Integer) r5.get(r8)).intValue() == r4) {
                            r9 = true;
                        }
                    }
                    if (!r9) {
                        com.egeetouch.egeetouch_manager.BLEService.battery_level = ((java.lang.Integer) r1.get(r10)).intValue();
                    }
                    r20.battery_list.clear();
                    r20.battery_list = new java.util.ArrayList<>();
                }
                com.egeetouch.egeetouch_manager.BLEService.get_battery_level_done = true;
            }
            java.lang.Integer.valueOf(com.egeetouch.egeetouch_manager.BLEService.battery_level).intValue();
            if (!com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2500")) {
                com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2550");
            }
            java.lang.System.out.println("Hello checking the battery level : " + com.egeetouch.egeetouch_manager.BLEService.battery_level);
            android.util.Log.i("TAG", "Original Battery Level: " + java.lang.String.valueOf(com.egeetouch.egeetouch_manager.BLEService.battery_level));
            if (com.egeetouch.egeetouch_manager.BLEService.egeetouchDeviceFound) {
                com.egeetouch.egeetouch_manager.BLEService.egeetouchDeviceFound = false;
                com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = com.egeetouch.egeetouch_manager.BLEService.check_model;
                com.egeetouch.egeetouch_manager.UI_BLE.BLE_UI = 19;
            }
            if (com.egeetouch.egeetouch_manager.UI_BLE.add_lock_mode) {
                com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = com.egeetouch.egeetouch_manager.BLEService.check_model;
                return;
            }
            return;
        }
        java.lang.String r10 = "";
        if (com.egeetouch.egeetouch_manager.BLEService.UUID_model.equals(r21.getUuid())) {
            byte[] r1 = r21.getValue();
            try {
                byte[] r4 = r1.length == 21 ? new byte[11] : null;
                for (int r5 = 10; r5 < r1.length; r5++) {
                    r4[r5 - 10] = r1[r5];
                }
                r10 = r4 != null ? new java.lang.String(r4) : "N.A";
                if (r10.contains("5109")) {
                    com.egeetouch.egeetouch_manager.BLEService.selected_lock_model = "GT5109";
                } else if (r10.contains("5107")) {
                    com.egeetouch.egeetouch_manager.BLEService.selected_lock_model = "GT5107";
                } else if (r10.contains("5300")) {
                    com.egeetouch.egeetouch_manager.BLEService.selected_lock_model = "GT5300";
                }
            } catch (java.lang.Exception unused) {
            }
            java.lang.System.out.println("Hello checking the model from loto :" + r10);
            com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = com.egeetouch.egeetouch_manager.BLEService.check_version;
            if (com.egeetouch.egeetouch_manager.BLEService.verified_password_done) {
                return;
            }
            if (com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2500") || com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2550")) {
                com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = com.egeetouch.egeetouch_manager.BLEService.Verify_Admin_Password_new_lock;
                return;
            }
            java.lang.System.out.println("Hello checking the model : **************");
            com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = com.egeetouch.egeetouch_manager.BLEService.Verify_Admin_Password_new_lock;
            return;
        }
        int r4 = 6;
        if (com.egeetouch.egeetouch_manager.BLEService.UUID_version.equals(r21.getUuid())) {
            if (!com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2500") && !com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2550")) {
                byte[] r1 = r21.getValue();
                byte[] r2 = null;
                java.lang.System.out.println("Hello checking the version length of the loto : " + r1.length);
                if (r1.length == 24) {
                    r2 = new byte[6];
                } else if (r1.length == 23) {
                    r2 = new byte[5];
                }
                if (r1.length == 22) {
                    r2 = new byte[4];
                } else if (r1.length == 21) {
                    r2 = new byte[3];
                }
                if (r1.length == 11) {
                    r2 = new byte[4];
                    for (int r4 = 7; r4 < r1.length; r4++) {
                        r2[r4 - 7] = r1[r4];
                    }
                } else {
                    for (int r3 = 18; r3 < r1.length; r3++) {
                        r2[r3 - 18] = r1[r3];
                    }
                }
                java.lang.String r1 = r2 != null ? new java.lang.String(r2) : com.google.firebase.crashlytics.internal.common.IdManager.DEFAULT_VERSION_NAME;
                com.egeetouch.egeetouch_manager.BLEService.selected_lock_version = r1;
                java.lang.System.out.println("Hello checking the lock current version from firmware:" + com.egeetouch.egeetouch_manager.BLEService.selected_lock_version + " scanning_new_lock:" + com.egeetouch.egeetouch_manager.MainActivity.scanning_new_lock);
                if (com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2100")) {
                    if (com.egeetouch.egeetouch_manager.BLEService.selected_lock_version.equals("1.80")) {
                        com.egeetouch.egeetouch_manager.BLEService.AuditTrail_Capacity = 100;
                    } else if (java.lang.Float.valueOf(com.egeetouch.egeetouch_manager.BLEService.selected_lock_version).floatValue() > 1.8d) {
                        com.egeetouch.egeetouch_manager.BLEService.AuditTrail_Capacity = com.egeetouch.egeetouch_manager.BLEService.check_version;
                    }
                }
                com.egeetouch.egeetouch_manager.DatabaseVariable.db_lock.execSQL(com.egeetouch.egeetouch_manager.DatabaseVariable.lockdb_update_version(r1, com.egeetouch.egeetouch_manager.BLEService.selected_lock_name));
                com.egeetouch.egeetouch_manager.BLEService.get_version_done = true;
                if (!com.egeetouch.egeetouch_manager.MainActivity.scanning_new_lock) {
                    com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 113;
                }
                java.lang.System.out.println("Hello Fragment Ble check 1");
                if (com.egeetouch.egeetouch_manager.MainActivity.scanning_new_lock) {
                    try {
                        android.content.pm.PackageInfo r1 = getPackageManager().getPackageInfo(getPackageName(), 0);
                        if (java.lang.Float.valueOf(com.egeetouch.egeetouch_manager.BLEService.selected_lock_version).floatValue() > 1.8d && r1.versionCode <= 97 && com.egeetouch.egeetouch_manager.BLEService.selected_lock_model == "GT2100" && !com.egeetouch.egeetouch_manager.BLEService.verified_password_done) {
                            com.egeetouch.egeetouch_manager.UI_BLE.BLE_UI = 27;
                            com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 0;
                            java.lang.System.out.println("Hello checking the lock ver 1");
                        } else {
                            boolean r1 = com.egeetouch.egeetouch_manager.BLEService.verified_password_done;
                            if (!r1) {
                                java.lang.System.out.println("Hello checking the lock ver 2");
                                if (com.egeetouch.egeetouch_manager.MainActivity.scanning_new_lock) {
                                    com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = com.egeetouch.egeetouch_manager.BLEService.Verify_Admin_Password_new_lock;
                                    java.lang.System.out.println("Hello checking the lock ver 3");
                                }
                            } else if (r1) {
                                com.egeetouch.egeetouch_manager.UI_BLE.BLE_UI = 3;
                                com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 0;
                                java.lang.System.out.println("Hello checking the lock ver 4");
                            }
                        }
                        return;
                    } catch (java.lang.Exception unused) {
                        if (com.egeetouch.egeetouch_manager.MainActivity.scanning_new_lock && !com.egeetouch.egeetouch_manager.BLEService.verified_password_done) {
                            com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = com.egeetouch.egeetouch_manager.BLEService.Verify_Admin_Password_new_lock;
                        }
                        java.lang.System.out.println("Hello checking the lock ver 5");
                        return;
                    }
                } else if (com.egeetouch.egeetouch_manager.BLEService.skip_verify_password_UI) {
                    return;
                } else {
                    com.egeetouch.egeetouch_manager.UI_BLE.BLE_UI = 3;
                    com.egeetouch.egeetouch_manager.BLEService.skip_verify_password_UI = true;
                    java.lang.System.out.println("Hello Fragment Ble check 2");
                    java.lang.System.out.println("Hello checking the lock ver 6");
                    return;
                }
            }
            if (com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2500") || com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2550")) {
                com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 504;
                if (com.egeetouch.egeetouch_manager.BLEService.verified_password_done) {
                    com.egeetouch.egeetouch_manager.UI_BLE.BLE_UI = 3;
                    java.lang.System.out.println("Hello checking the lock ver 4");
                }
            }
            com.egeetouch.egeetouch_manager.BLEService.selected_lock_version = com.github.florent37.singledateandtimepicker.BuildConfig.VERSION_NAME;
            byte[] r1 = r21.getValue();
            byte[] r2 = null;
            if (r1.length == 10) {
                r2 = new byte[4];
                while (r4 < r1.length) {
                    r2[r4 - 6] = r1[r4];
                    r4++;
                }
            }
            java.lang.String r1 = new java.lang.String(r2);
            com.egeetouch.egeetouch_manager.BLEService.selected_lock_version = r1;
            r20.lockInfo.setLockVersion(r1);
            com.egeetouch.egeetouch_manager.DatabaseVariable.db_lock.execSQL(com.egeetouch.egeetouch_manager.DatabaseVariable.lockdb_update_version(com.egeetouch.egeetouch_manager.BLEService.selected_lock_version, com.egeetouch.egeetouch_manager.BLEService.selected_lock_name));
        } else if (com.egeetouch.egeetouch_manager.BLEService.UUID_2.equals(r21.getUuid())) {
            byte[] r1 = r21.getValue();
            java.lang.System.out.println("Hello checkin UUID_2 data is.. data[0]:" + ((int) r1[0]) + " Int val :" + (r1[0] & kotlin.UByte.MAX_VALUE) + "BLE:" + com.egeetouch.egeetouch_manager.BLEService.Ble_Mode);
            int r11 = com.egeetouch.egeetouch_manager.BLEService.Ble_Mode;
            if (r11 == 16) {
                java.lang.System.out.println("HEY Read_response_Admin_Password getting called:16 CMDCMD data[0]: " + ((int) r1[0]));
                if (r1[0] == 97 || r1[0] == -52) {
                    com.egeetouch.egeetouch_manager.BLEService.selected_lock_role = 0;
                    r20.lockInfo.setLockRole(0);
                    if (r1[0] == 97) {
                        if (com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2500") || com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2550")) {
                            if (r1[3] == 0) {
                                com.egeetouch.egeetouch_manager.BLEService.wrong_password = true;
                                com.egeetouch.egeetouch_manager.BLEService.verified_password_done = false;
                                com.egeetouch.egeetouch_manager.BLEService.selected_lock_role = 0;
                                r20.lockInfo.setLockRole(0);
                                com.egeetouch.egeetouch_manager.UI_BLE.BLE_UI = 3;
                                com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 0;
                                com.egeetouch.egeetouch_manager.AddLockListener r1 = com.egeetouch.egeetouch_manager.BLEService.addLockListener;
                                if (r1 != null) {
                                    r1.onAddLockResponse(false, false);
                                    return;
                                }
                                return;
                            } else if (r1[3] == 1 || r1[3] == 2) {
                                com.egeetouch.egeetouch_manager.BLEService.verified_password_done = true;
                                int r1 = com.egeetouch.egeetouch_manager.BLEService.Ble_Mode_before_disconnect;
                                if (r1 == 0) {
                                    r1 = false;
                                    com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 67;
                                } else if (r1 == 17 || r1 == 221) {
                                    r1 = false;
                                    com.egeetouch.egeetouch_manager.BLEService.Ble_Mode_before_disconnect = 0;
                                    com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = com.egeetouch.egeetouch_manager.BLEService.Add_Tag_fail;
                                } else {
                                    com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 67;
                                    r1 = false;
                                    com.egeetouch.egeetouch_manager.BLEService.Ble_Mode_before_disconnect = 0;
                                }
                                if (com.egeetouch.egeetouch_manager.BLEService.addLockListener != null) {
                                    com.egeetouch.egeetouch_manager.UI_BLE.BLE_UI = 3;
                                    com.egeetouch.egeetouch_manager.BLEService.addLockListener.onAddLockResponse(true, r1);
                                    return;
                                }
                                return;
                            } else {
                                return;
                            }
                        }
                        com.egeetouch.egeetouch_manager.BLEService.verified_password_done = true;
                        int r1 = com.egeetouch.egeetouch_manager.BLEService.Ble_Mode_before_disconnect;
                        if (r1 == 0) {
                            r2 = false;
                            com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 67;
                        } else if (r1 == 17) {
                            r2 = false;
                            com.egeetouch.egeetouch_manager.BLEService.Ble_Mode_before_disconnect = 0;
                            com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = com.egeetouch.egeetouch_manager.BLEService.Add_Tag_fail;
                        } else {
                            r2 = false;
                            com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = r1;
                            com.egeetouch.egeetouch_manager.BLEService.Ble_Mode_before_disconnect = 0;
                        }
                        if (com.egeetouch.egeetouch_manager.BLEService.addLockListener != null) {
                            com.egeetouch.egeetouch_manager.UI_BLE.BLE_UI = 3;
                            com.egeetouch.egeetouch_manager.BLEService.addLockListener.onAddLockResponse(true, r2);
                            return;
                        }
                        return;
                    }
                    com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 97;
                } else if (r1[0] == -113 || r1[0] == 143) {
                    android.util.Log.i("TAG", "Hello passsword_verification user1!!");
                    com.egeetouch.egeetouch_manager.BLEService.selected_lock_role = 1;
                    com.egeetouch.egeetouch_manager.BLEService.verified_password_done = true;
                    com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 67;
                } else if (r1[0] == -98) {
                    com.egeetouch.egeetouch_manager.BLEService.wrong_password = true;
                    com.egeetouch.egeetouch_manager.BLEService.verified_password_done = false;
                    com.egeetouch.egeetouch_manager.BLEService.selected_lock_role = 0;
                    com.egeetouch.egeetouch_manager.UI_BLE.BLE_UI = 3;
                    com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 0;
                    java.lang.System.out.println("Hello checking the lock ver 14 ");
                    com.egeetouch.egeetouch_manager.AddLockListener r1 = com.egeetouch.egeetouch_manager.BLEService.addLockListener;
                    if (r1 != null) {
                        r1.onAddLockResponse(false, false);
                    }
                } else {
                    com.egeetouch.egeetouch_manager.BLEService.verified_password_done = false;
                    com.egeetouch.egeetouch_manager.BLEService.selected_lock_role = 0;
                    com.egeetouch.egeetouch_manager.UI_BLE.BLE_UI = 3;
                    com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 0;
                    java.lang.System.out.println("Hello checking the lock ver 15 ");
                }
            } else if (r11 == 17) {
                com.egeetouch.egeetouch_manager.BLEService.tag_updateSuccess = true;
                java.lang.System.out.println("HEY from BLEService case Read_Response_Add_Tag getting called");
                if (com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2100") && (com.egeetouch.egeetouch_manager.BLEService.selected_lock_version.equals("1.80") || java.lang.Float.valueOf(com.egeetouch.egeetouch_manager.BLEService.selected_lock_version).floatValue() > 1.8d)) {
                    java.lang.System.out.println("Hello Reply Add_tag data:" + ((int) r1[0]) + "tag_batch_number:" + com.egeetouch.egeetouch_manager.BLEService.tag_batch_number + " Page_number:" + com.egeetouch.egeetouch_manager.BLEService.tag_page_number);
                    com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 0;
                    com.egeetouch.egeetouch_manager.BLEService.numberOfPageToUpdate = com.egeetouch.egeetouch_manager.MainActivity.list_empty_pages.indexOf(java.lang.Integer.valueOf(com.egeetouch.egeetouch_manager.MainActivity.last_page_number)) + 1;
                    com.egeetouch.egeetouch_manager.BLEService.currentPagePosition = com.egeetouch.egeetouch_manager.MainActivity.list_empty_pages.indexOf(java.lang.Integer.valueOf(com.egeetouch.egeetouch_manager.BLEService.tag_page_number)) + 1;
                    com.google.firebase.database.FirebaseDatabase r2 = com.google.firebase.database.FirebaseDatabase.getInstance();
                    com.egeetouch.egeetouch_manager.BLEService.totalTagsInLock = com.egeetouch.egeetouch_manager.MainActivity.FirebaseTotalTag;
                    if (r1[0] == 17) {
                        com.egeetouch.egeetouch_manager.BLEService.totalTagsInLock = com.egeetouch.egeetouch_manager.MainActivity.Tag_deletion ? com.egeetouch.egeetouch_manager.MainActivity.FirebaseTotalTag - 1 : com.egeetouch.egeetouch_manager.BLEService.totalTagsInLock;
                        int r1 = com.egeetouch.egeetouch_manager.BLEService.tag_page_number;
                        int r3 = com.egeetouch.egeetouch_manager.BLEService.highestTagPage;
                        if (r1 <= r3) {
                            r1 = r3;
                        }
                        com.egeetouch.egeetouch_manager.BLEService.highestTagPage = r1;
                        if (!com.egeetouch.egeetouch_manager.MainActivity.Tag_deletion) {
                            if (!com.egeetouch.egeetouch_manager.BLEService.rewrite_page_boolean) {
                                com.google.firebase.database.DatabaseReference r1 = r2.getReference("tagDirectory").child(com.egeetouch.egeetouch_manager.BLEService.parsedIp45SerialNumber);
                                int r2 = com.egeetouch.egeetouch_manager.MainActivity.tag_id_graph.get(com.egeetouch.egeetouch_manager.BLEService.tag_page_number).size();
                                com.egeetouch.egeetouch_manager.BLEService.totalTagsInLock = com.egeetouch.egeetouch_manager.MainActivity.FirebaseTotalTag + com.egeetouch.egeetouch_manager.BLEService.totalNewTagsAdded;
                                for (int r3 = com.egeetouch.egeetouch_manager.MainActivity.list_empty_page_size.get(com.egeetouch.egeetouch_manager.BLEService.name_count).intValue(); r3 < r2; r3++) {
                                    com.google.firebase.database.DatabaseReference r7 = r1.push();
                                    r7.child("PageNumber").setValue(java.lang.Integer.valueOf(com.egeetouch.egeetouch_manager.BLEService.tag_page_number));
                                    r7.child("tagId").setValue(r20.list_tag_ids.get(r3));
                                    r7.child("tagName").setValue(com.egeetouch.egeetouch_manager.Fragment_add_multiple_tags.new_tag_name.get(com.egeetouch.egeetouch_manager.BLEService.new_tag_name_count));
                                    r7.child("timeAdded").setValue(java.lang.Double.valueOf(com.egeetouch.egeetouch_manager.MainActivity.currentTimestampDouble));
                                    com.egeetouch.egeetouch_manager.BLEService.new_tag_name_count++;
                                }
                                com.egeetouch.egeetouch_manager.BLEService.name_count++;
                                com.egeetouch.egeetouch_manager.UI_BLE.pls_wait_content = com.egeetouch.egeetouch_manager.MainActivity.context.getResources().getString(com.egeetouch.egeetouch_manager.R.string.updating_data_to_lock);
                                com.egeetouch.egeetouch_manager.UI_BLE.progress_val = (com.egeetouch.egeetouch_manager.BLEService.currentPagePosition * 100) / com.egeetouch.egeetouch_manager.BLEService.numberOfPageToUpdate;
                                com.egeetouch.egeetouch_manager.UI_BLE.BLE_UI = 24;
                            }
                        } else {
                            com.egeetouch.egeetouch_manager.MainActivity.Tag_deletion = false;
                            r2.getReference("tagDirectory").child(com.egeetouch.egeetouch_manager.BLEService.parsedIp45SerialNumber).addListenerForSingleValueEvent(new com.google.firebase.database.ValueEventListener() { // from class: com.egeetouch.egeetouch_manager.BLEService.11
                                @Override // com.google.firebase.database.ValueEventListener
                                public void onCancelled(com.google.firebase.database.DatabaseError r1) {
                                }

                                {
                                }


                                @Override // com.google.firebase.database.ValueEventListener
                                public void onDataChange(com.google.firebase.database.DataSnapshot r3) {
                                    for (com.google.firebase.database.DataSnapshot r0 : r3.getChildren()) {
                                        r0.getRef().child("tagId").addListenerForSingleValueEvent(new com.google.firebase.database.ValueEventListener() { // from class: com.egeetouch.egeetouch_manager.BLEService.11.1
                                            @Override // com.google.firebase.database.ValueEventListener
                                            public void onCancelled(com.google.firebase.database.DatabaseError r1) {
                                            }

                                            {
                                            }

                                            @Override // com.google.firebase.database.ValueEventListener
                                            public void onDataChange(com.google.firebase.database.DataSnapshot r3) {
                                                if (r3.getValue().toString().equals(com.egeetouch.egeetouch_manager.Fragment_add_tag.tag_id_remove)) {
                                                    android.util.Log.i("Tag", "Delete Tag from Cloud : " + r3.getRef().getKey().toString());
                                                    r3.getRef().getParent().removeValue();
                                                }
                                            }
                                        });
                                    }
                                }
                            });
                            com.egeetouch.egeetouch_manager.BLEService.commercialDatabase.getReference("tagDirectory").child(com.egeetouch.egeetouch_manager.BLEService.parsedIp45SerialNumber).addListenerForSingleValueEvent(new com.google.firebase.database.ValueEventListener() { // from class: com.egeetouch.egeetouch_manager.BLEService.12
                                @Override // com.google.firebase.database.ValueEventListener
                                public void onCancelled(com.google.firebase.database.DatabaseError r1) {
                                }

                                {
                                }


                                @Override // com.google.firebase.database.ValueEventListener
                                public void onDataChange(com.google.firebase.database.DataSnapshot r3) {
                                    for (com.google.firebase.database.DataSnapshot r0 : r3.getChildren()) {
                                        r0.getRef().child("tagId").addListenerForSingleValueEvent(new com.google.firebase.database.ValueEventListener() { // from class: com.egeetouch.egeetouch_manager.BLEService.12.1
                                            @Override // com.google.firebase.database.ValueEventListener
                                            public void onCancelled(com.google.firebase.database.DatabaseError r1) {
                                            }

                                            {
                                            }

                                            @Override // com.google.firebase.database.ValueEventListener
                                            public void onDataChange(com.google.firebase.database.DataSnapshot r3) {
                                                if (r3.getValue().toString().equals(com.egeetouch.egeetouch_manager.Fragment_add_tag.tag_id_remove)) {
                                                    android.util.Log.i("Tag", "Delete Tag from Cloud : " + r3.getRef().getKey().toString());
                                                    r3.getRef().getParent().removeValue();
                                                }
                                            }
                                        });
                                    }
                                }
                            });
                        }
                    } else if (r1[0] != 0 && r1[0] != 1) {
                        com.google.firebase.database.DatabaseReference r2 = r2.getReference("tagUpdateFailure").child(com.egeetouch.egeetouch_manager.BLEService.parsedIp45SerialNumber).push();
                        r2.child("pageNumber").setValue(java.lang.Integer.valueOf(com.egeetouch.egeetouch_manager.BLEService.tag_page_number));
                        r2.child("isAddTagFailed").setValue(java.lang.Boolean.valueOf(!com.egeetouch.egeetouch_manager.MainActivity.Tag_deletion));
                        r2.child("isTagSynchronizeFailed").setValue(java.lang.Boolean.valueOf(com.egeetouch.egeetouch_manager.BLEService.rewrite_page_boolean));
                        r2.child("isDeleteTagFailed").setValue(java.lang.Boolean.valueOf(com.egeetouch.egeetouch_manager.MainActivity.Tag_deletion));
                        r2.child("lockErrorCode").setValue(java.lang.Integer.valueOf(r1[0]));
                        r2.child("timeStamp").setValue(java.lang.Double.valueOf(com.egeetouch.egeetouch_manager.MainActivity.currentTimestampDouble));
                        r2.child("updatePlatform").setValue("AndroidApp");
                        com.egeetouch.egeetouch_manager.BLEService.tag_updateSuccess = false;
                        com.egeetouch.egeetouch_manager.UI_BLE.pls_wait_content = com.egeetouch.egeetouch_manager.MainActivity.context.getResources().getString(com.egeetouch.egeetouch_manager.R.string.update_failed);
                        com.egeetouch.egeetouch_manager.UI_BLE.BLE_UI = 24;
                    }
                    if (com.egeetouch.egeetouch_manager.BLEService.tag_page_number <= com.egeetouch.egeetouch_manager.MainActivity.last_page_number) {
                        int r1 = com.egeetouch.egeetouch_manager.BLEService.tag_batch_number + 1;
                        com.egeetouch.egeetouch_manager.BLEService.tag_batch_number = r1;
                        if (r1 < 3) {
                            com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = com.egeetouch.egeetouch_manager.BLEService.Add_tag_version2;
                            return;
                        }
                        com.egeetouch.egeetouch_manager.BLEService.tag_batch_number = 0;
                        com.egeetouch.egeetouch_manager.BLEService.tag_list_count = 0;
                        if (com.egeetouch.egeetouch_manager.BLEService.tag_page_number == com.egeetouch.egeetouch_manager.MainActivity.last_page_number) {
                            com.egeetouch.egeetouch_manager.BLEService.rewrite_page_boolean = false;
                            com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 19;
                            return;
                        }
                        com.egeetouch.egeetouch_manager.BLEService.page_count++;
                        com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = com.egeetouch.egeetouch_manager.BLEService.Add_tag_version2;
                        return;
                    }
                    com.egeetouch.egeetouch_manager.BLEService.rewrite_page_boolean = false;
                    com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 19;
                    return;
                }
                com.egeetouch.egeetouch_manager.BLEService.Tag_current_number = java.lang.Integer.valueOf(r1[0]).intValue();
                if (com.egeetouch.egeetouch_manager.BLEService.Tag_loop_number >= 20) {
                    com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 19;
                    return;
                }
                com.egeetouch.egeetouch_manager.UI_BLE.pls_wait_content = com.egeetouch.egeetouch_manager.MainActivity.context.getResources().getString(com.egeetouch.egeetouch_manager.R.string.updating_data_to_lock);
                com.egeetouch.egeetouch_manager.UI_BLE.progress_val = com.egeetouch.egeetouch_manager.BLEService.Tag_loop_number * 5;
                com.egeetouch.egeetouch_manager.UI_BLE.BLE_UI = 24;
                com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 80;
            } else if (r11 == 32) {
                com.egeetouch.egeetouch_manager.BLEService.User_current_number = java.lang.Integer.valueOf(r1[0]).intValue();
                android.util.Log.i("TAG", "User_current_number: " + java.lang.String.valueOf(com.egeetouch.egeetouch_manager.BLEService.User_current_number));
                if (com.egeetouch.egeetouch_manager.BLEService.User_current_number == com.egeetouch.egeetouch_manager.BLEService.User_loop_number) {
                    com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 25;
                } else {
                    com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 24;
                }
            } else if (r11 == 33) {
                if (com.egeetouch.egeetouch_manager.BLEService.User_loop_number > 4) {
                    com.egeetouch.egeetouch_manager.BLEService.User_current_number = 0;
                    com.egeetouch.egeetouch_manager.BLEService.User_loop_number = 0;
                    com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 36;
                    return;
                }
                com.egeetouch.egeetouch_manager.UI_BLE.pls_wait_content = com.egeetouch.egeetouch_manager.MainActivity.context.getResources().getString(com.egeetouch.egeetouch_manager.R.string.updating_data_to_lock);
                com.egeetouch.egeetouch_manager.UI_BLE.progress_val = com.egeetouch.egeetouch_manager.BLEService.User_loop_number * 20;
                com.egeetouch.egeetouch_manager.UI_BLE.BLE_UI = 24;
                com.egeetouch.egeetouch_manager.BLEService.User_loop_number++;
                com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 24;
            } else if (r11 == 48) {
                if (java.lang.String.valueOf((int) r1[0]).equals("-83")) {
                    com.google.firebase.database.DatabaseReference r1 = com.google.firebase.database.FirebaseDatabase.getInstance().getReference("Registered_user").child(com.egeetouch.egeetouch_manager.MainActivity.user_uid).child(java.lang.String.valueOf(com.egeetouch.egeetouch_manager.MainActivity.child_index)).child("Audit_Trail");
                    com.egeetouch.egeetouch_manager.MainActivity.audit_trail_num = 0;
                    r1.addListenerForSingleValueEvent(new com.google.firebase.database.ValueEventListener() { // from class: com.egeetouch.egeetouch_manager.BLEService.9
                        @Override // com.google.firebase.database.ValueEventListener
                        public void onCancelled(com.google.firebase.database.DatabaseError r1) {
                        }

                        @Override // com.google.firebase.database.ValueEventListener
                        public void onDataChange(com.google.firebase.database.DataSnapshot r1) {
                        }

                        {
                        }
                    });
                    com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 41;
                    return;
                }
                com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 41;
            } else if (r11 == 49) {
                if (java.lang.String.valueOf((int) r1[0]).equals("-54")) {
                    com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 0;
                    com.egeetouch.egeetouch_manager.UI_BLE.BLE_UI = 2;
                    return;
                }
                com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 49;
            } else if (r11 == 52) {
                android.util.Log.i("Tag", "Read_Response_Request_Audit_Data_1: " + java.lang.String.valueOf((int) r1[0]));
                if (java.lang.String.valueOf((int) r1[0]).equals(com.facebook.appevents.AppEventsConstants.EVENT_PARAM_VALUE_YES)) {
                    com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 54;
                    android.util.Log.i("Tag", "Read_Response_Request_Audit_Data_1");
                    return;
                }
                com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 50;
            } else if (r11 == 53) {
                if (java.lang.String.valueOf((int) r1[0]).equals("33")) {
                    com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 55;
                } else {
                    com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 51;
                }
            } else if (r11 == 87) {
                if (r1[0] == 97) {
                    com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 86;
                } else {
                    com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 86;
                }
            } else if (r11 == 88) {
                if (r1[0] == 96) {
                    com.egeetouch.egeetouch_manager.BLEService.add_new_password_done = true;
                } else {
                    com.egeetouch.egeetouch_manager.BLEService.add_new_password_done = false;
                }
                if (r1[0] == -52) {
                    java.lang.System.out.println("HEY UUID_2 -52 input_new_password");
                    com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 86;
                    return;
                }
                com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 0;
                if (com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT5109") || com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT5107") || com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT5300")) {
                    com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = com.egeetouch.egeetouch_manager.BLEService.Request_lotoEnable_auditTrail;
                }
                if (com.egeetouch.egeetouch_manager.Fragment_lock_setting.initial_setup) {
                    if (com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2100") && java.lang.Float.valueOf(com.egeetouch.egeetouch_manager.BLEService.selected_lock_version).floatValue() > 1.8d) {
                        com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = com.egeetouch.egeetouch_manager.BLEService.Request_Master_Tag;
                        return;
                    } else if (com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2500") || com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2550")) {
                        com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 508;
                        return;
                    } else {
                        com.egeetouch.egeetouch_manager.UI_BLE.BLE_UI = 5;
                        return;
                    }
                }
                com.egeetouch.egeetouch_manager.UI_BLE.BLE_UI = 5;
            } else {
                switch (r11) {
                    case 20:
                        java.lang.System.out.println("HEY from BLEService case Read_Response_Add_Tag_number getting called");
                        if (com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2100") && (com.egeetouch.egeetouch_manager.BLEService.selected_lock_version.equals("1.80") || java.lang.Float.valueOf(com.egeetouch.egeetouch_manager.BLEService.selected_lock_version).floatValue() > 1.8d)) {
                            com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 0;
                            com.egeetouch.egeetouch_manager.BLEService.mainActivity.Retrive_Tag_from_Firebase();
                            return;
                        } else if (java.lang.String.valueOf((int) r1[0]).equals("-92")) {
                            com.egeetouch.egeetouch_manager.BLEService.add_tag_done = true;
                            com.egeetouch.egeetouch_manager.BLEService.Tag_loop_number = 0;
                            com.egeetouch.egeetouch_manager.BLEService.Tag_current_number = 0;
                            com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 0;
                            com.egeetouch.egeetouch_manager.UI_BLE.BLE_UI = 9;
                            return;
                        } else {
                            com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 19;
                            return;
                        }
                    case 37:
                        if (java.lang.String.valueOf((int) r1[0]).equals("-90")) {
                            com.egeetouch.egeetouch_manager.BLEService.add_user_done = true;
                            com.egeetouch.egeetouch_manager.BLEService.User_loop_number = 0;
                            com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 0;
                            com.egeetouch.egeetouch_manager.UI_BLE.BLE_UI = 11;
                            com.egeetouch.egeetouch_manager.BLEService.User_current_number = 0;
                            return;
                        }
                        com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 36;
                        return;
                    case 57:
                        com.egeetouch.egeetouch_manager.BLEService.audit_trail_index_number = r1[0];
                        android.util.Log.i("TAG", "audit_trail_index_number: " + java.lang.String.valueOf(com.egeetouch.egeetouch_manager.BLEService.audit_trail_index_number));
                        int r1 = com.egeetouch.egeetouch_manager.BLEService.audit_trail_index_number;
                        if (r1 == -52) {
                            com.egeetouch.egeetouch_manager.BLEService.audit_trail_loop_number = 1;
                            com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 50;
                            com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 97;
                            com.egeetouch.egeetouch_manager.BLEService.audit_pass_verification = true;
                            return;
                        } else if (r1 >= 0 && r1 <= 21) {
                            com.egeetouch.egeetouch_manager.BLEService.audit_trail_loop_number = r1;
                            if (r1 == 21) {
                                com.egeetouch.egeetouch_manager.BLEService.audit_trail_loop_number = r1 - 1;
                            }
                            com.egeetouch.egeetouch_manager.BLEService.temp_audit_count1 = 1;
                            com.egeetouch.egeetouch_manager.BLEService.temp_audit_count = 1;
                            if (r1 == 0) {
                                com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 0;
                                com.egeetouch.egeetouch_manager.UI_BLE.BLE_UI = 21;
                                java.lang.String r1 = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(java.util.Calendar.getInstance().getTime());
                                java.lang.String r2 = com.egeetouch.egeetouch_manager.BLEService.selected_lock_name;
                                java.lang.Double r3 = java.lang.Double.valueOf((double) com.google.firebase.remoteconfig.FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
                                java.lang.Double r4 = java.lang.Double.valueOf((double) com.google.firebase.remoteconfig.FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
                                for (int r6 = 1; r6 <= 20; r6++) {
                                    com.egeetouch.egeetouch_manager.DatabaseVariable.db_audittrail.execSQL(com.egeetouch.egeetouch_manager.DatabaseVariable.db_audittrail_update_value_bt_1(r6, "", r3, r1, r2));
                                    com.egeetouch.egeetouch_manager.DatabaseVariable.db_audittrail.execSQL(com.egeetouch.egeetouch_manager.DatabaseVariable.db_audittrail_update_value_bt_2(r6, "", r4, r2));
                                }
                                com.egeetouch.egeetouch_manager.UI_BLE.BLE_UI = 21;
                            }
                            android.util.Log.i("Tag", "audit_trail_loop_number: " + java.lang.String.valueOf(com.egeetouch.egeetouch_manager.BLEService.audit_trail_loop_number));
                            com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 50;
                            return;
                        } else {
                            com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 103;
                            android.util.Log.i("Tag", "Request_Audit_Index");
                            return;
                        }
                    case 66:
                        java.lang.System.out.println("Hello checkin UUID_2 reply from UNLOCKING! " + ((int) r1[0]) + " int Val:" + (r1[0] & kotlin.UByte.MAX_VALUE));
                        if (com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2500") || com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2550")) {
                            if ((r1[0] & kotlin.UByte.MAX_VALUE) == 98) {
                                com.egeetouch.egeetouch_manager.UI_BLE.BLE_UI = 2;
                            }
                        } else {
                            int r4 = 0;
                            if (r1[0] < 20) {
                                if (r1[0] > 0) {
                                    com.egeetouch.egeetouch_manager.BLEService.selected_lock_role = (!com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2100") || (!com.egeetouch.egeetouch_manager.BLEService.selected_lock_version.equals("1.80") && ((double) java.lang.Float.valueOf(com.egeetouch.egeetouch_manager.BLEService.selected_lock_version).floatValue()) <= 1.8d)) ? 1 : 0;
                                    com.egeetouch.egeetouch_manager.BLEService.selected_lock_state = false;
                                    com.egeetouch.egeetouch_manager.UI_BLE.BLE_UI = 2;
                                    com.egeetouch.egeetouch_manager.BLEService.selected_lock_role = (!com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2100") || (!com.egeetouch.egeetouch_manager.BLEService.selected_lock_version.equals("1.80") && ((double) java.lang.Float.valueOf(com.egeetouch.egeetouch_manager.BLEService.selected_lock_version).floatValue()) <= 1.8d)) ? 1 : 0;
                                    com.egeetouch.egeetouch_manager.BLEService.is_sendTimeStamp = true;
                                    if ((com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2100") && (com.egeetouch.egeetouch_manager.BLEService.selected_lock_version.equals("1.80") || java.lang.Float.valueOf(com.egeetouch.egeetouch_manager.BLEService.selected_lock_version).floatValue() > 1.8d)) || com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT5100") || com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT5107") || com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT5109") || com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT5300")) {
                                        r4 = 0;
                                        com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 0;
                                    } else {
                                        com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 40;
                                        r4 = 0;
                                    }
                                    com.egeetouch.egeetouch_manager.BLEService.current_user_index = r1[r4];
                                    com.egeetouch.egeetouch_manager.UI_BLE.BLE_UI = 8;
                                } else {
                                    r4 = 0;
                                }
                            }
                            if (r1[r4] == -81 || r1[r4] == 97) {
                                com.egeetouch.egeetouch_manager.BLEService.selected_lock_role = r4;
                                if ((com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2100") && (com.egeetouch.egeetouch_manager.BLEService.selected_lock_version.equals("1.80") || java.lang.Float.valueOf(com.egeetouch.egeetouch_manager.BLEService.selected_lock_version).floatValue() > 1.8d)) || com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT5100") || com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT5107") || com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT5109") || com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT5300")) {
                                    com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 0;
                                } else {
                                    com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 40;
                                }
                                com.egeetouch.egeetouch_manager.BLEService.is_sendTimeStamp = true;
                                com.egeetouch.egeetouch_manager.UI_BLE.BLE_UI = 8;
                                com.egeetouch.egeetouch_manager.UI_BLE.BLE_UI = 2;
                            } else if (r1[r4] == -52) {
                                com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 66;
                            }
                        }
                        if (!com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2100") || java.lang.Float.valueOf(com.egeetouch.egeetouch_manager.BLEService.selected_lock_version).floatValue() <= 1.8d) {
                            return;
                        }
                        java.lang.System.out.println("Hello checking the characteristic_5  send ");
                        if (com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2500") || com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2550")) {
                            setCharacteristicNotification(r20.characteristic_2, true);
                            return;
                        } else if (java.lang.Float.valueOf(com.egeetouch.egeetouch_manager.BLEService.selected_lock_version).floatValue() > 1.8d) {
                            java.lang.System.out.println("Hello checking the characteristic_5  send ");
                            setCharacteristicNotification(r20.characteristic_5, true);
                            return;
                        } else {
                            return;
                        }
                    case 68:
                        com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 0;
                        java.lang.System.out.println("Hello checkin UUID_2 reply from LOCKING! " + ((int) r1[0]) + " int Val:" + (r1[0] & kotlin.UByte.MAX_VALUE));
                        android.util.Log.i("", "mConsumerService.sendData(\"Locking completed\");");
                        com.google.firebase.database.FirebaseDatabase r4 = com.google.firebase.database.FirebaseDatabase.getInstance();
                        if (com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2500") || com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2550")) {
                            return;
                        }
                        if (java.lang.String.valueOf((int) r1[0]).equals("-49") || java.lang.String.valueOf((int) r1[0]).equals("99")) {
                            java.lang.System.out.println("masuk ke old code");
                            if (com.egeetouch.egeetouch_manager.BLEService.selected_lock_is_one_time_access) {
                                r4 = 0;
                            } else {
                                final com.google.firebase.database.DatabaseReference r1 = r4.getReference("Registered_user").child(com.egeetouch.egeetouch_manager.MainActivity.user_uid).child(java.lang.String.valueOf(com.egeetouch.egeetouch_manager.MainActivity.child_index)).child("Audit_Trail");
                                r4 = 0;
                                com.egeetouch.egeetouch_manager.MainActivity.audit_trail_num = 0;
                                r1.addListenerForSingleValueEvent(new com.google.firebase.database.ValueEventListener() { // from class: com.egeetouch.egeetouch_manager.BLEService.10
                                    @Override // com.google.firebase.database.ValueEventListener
                                    public void onCancelled(com.google.firebase.database.DatabaseError r1) {
                                    }

                                    {
                                    }

                                    @Override // com.google.firebase.database.ValueEventListener
                                    public void onDataChange(com.google.firebase.database.DataSnapshot r4) {
                                        com.egeetouch.egeetouch_manager.MainActivity.audit_trail_num = (int) r4.getChildrenCount();
                                        com.egeetouch.egeetouch_manager.MainActivity.audit_trail_num++;
                                        com.google.firebase.database.DatabaseReference r4 = r2.child(java.lang.String.valueOf(com.egeetouch.egeetouch_manager.MainActivity.audit_trail_num));
                                        r4.child("ATLatitude").setValue(java.lang.Float.valueOf(com.egeetouch.egeetouch_manager.BLEService.selected_lock_address_latitude));
                                        r4.child("ATLongitude").setValue(java.lang.Float.valueOf(com.egeetouch.egeetouch_manager.BLEService.selected_lock_address_longitude));
                                        r4.child("ATUser").setValue(com.egeetouch.egeetouch_manager.MainActivity.email);
                                        r4.child("ATAddress").setValue("");
                                        r4.child("ATStatus").setValue("lock");
                                        r4.child("ATDateTime").setValue(com.egeetouch.egeetouch_manager.BLEService.now_admin.monthDay + "-" + (com.egeetouch.egeetouch_manager.BLEService.now_admin.month + 1) + "-" + (com.egeetouch.egeetouch_manager.BLEService.now_admin.year - 2000) + "  " + com.egeetouch.egeetouch_manager.BLEService.now_admin.hour + ":" + com.egeetouch.egeetouch_manager.BLEService.now_admin.minute + ":" + com.egeetouch.egeetouch_manager.BLEService.now_admin.second);
                                    }
                                });
                            }
                            com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = r4;
                            com.egeetouch.egeetouch_manager.UI_BLE.BLE_UI = 7;
                            return;
                        }
                        java.lang.System.out.println("masuk ke new code");
                        if (java.lang.String.valueOf((int) r1[0]).equals("-50")) {
                            com.egeetouch.egeetouch_manager.UI_BLE.BLE_UI = 7;
                            return;
                        }
                        int r3 = com.egeetouch.egeetouch_manager.BLEService.transmission_error_counter;
                        if (r3 >= 2) {
                            com.egeetouch.egeetouch_manager.BLEService.transmission_error_counter = 0;
                            com.egeetouch.egeetouch_manager.UI_BLE.BLE_UI = 7;
                            return;
                        }
                        com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 105;
                        com.egeetouch.egeetouch_manager.BLEService.transmission_error_counter = r3 + 1;
                        return;
                    case 71:
                        if (java.lang.String.valueOf((int) r1[0]).equals("99")) {
                            com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 0;
                            return;
                        } else {
                            com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 99;
                            return;
                        }
                    case com.egeetouch.egeetouch_manager.BLEService.Read_response_Admin_Password_new_lock /* 178 */:
                        java.lang.System.out.println("HEY Read_response_Admin_Password_new_lock getting called 0xB2");
                        if (r1[0] == 97) {
                            if (com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2500") || com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2550")) {
                                if (r1[3] == 0) {
                                    if (!com.egeetouch.egeetouch_manager.UI_BLE.add_lock_mode) {
                                        com.egeetouch.egeetouch_manager.BLEService.verified_password_done = true;
                                    }
                                    com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 0;
                                    com.egeetouch.egeetouch_manager.UI_BLE.BLE_UI = 1;
                                    java.lang.System.out.println("HEY you have found an old lock (NOT 123456) IP66");
                                    return;
                                } else if (r1[3] == 1 || r1[3] == 2) {
                                    if (com.egeetouch.egeetouch_manager.BLEService.new_password_verification) {
                                        com.egeetouch.egeetouch_manager.BLEService.new_password_verification = false;
                                        java.lang.System.out.println("HEY assigning selected_lock_password to 123456");
                                        com.egeetouch.egeetouch_manager.BLEService.selected_lock_password = "123456";
                                        com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 0;
                                        com.egeetouch.egeetouch_manager.AddLockListener r1 = com.egeetouch.egeetouch_manager.BLEService.addLockListener;
                                        r3 = true;
                                        if (r1 != null) {
                                            r1.onAddLockResponse(true, true);
                                        }
                                    } else {
                                        r3 = true;
                                    }
                                    com.egeetouch.egeetouch_manager.BLEService.iS_sendConnectionTimeStamp = r3;
                                    com.egeetouch.egeetouch_manager.BLEService.selected_lock_role = 0;
                                    r20.lockInfo.setLockRole(0);
                                    com.egeetouch.egeetouch_manager.BLEService.verified_password_done = r3;
                                    if (!com.egeetouch.egeetouch_manager.UI_BLE.add_lock_mode) {
                                        com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 67;
                                        return;
                                    } else {
                                        com.egeetouch.egeetouch_manager.UI_BLE.BLE_UI = 3;
                                        return;
                                    }
                                } else {
                                    return;
                                }
                            }
                            java.lang.System.out.println("HEY new_password_verification is: " + com.egeetouch.egeetouch_manager.BLEService.new_password_verification);
                            if (com.egeetouch.egeetouch_manager.BLEService.new_password_verification) {
                                com.egeetouch.egeetouch_manager.BLEService.new_password_verification = false;
                                java.lang.System.out.println("HEY assigning selected_lock_password to 123456");
                                com.egeetouch.egeetouch_manager.BLEService.selected_lock_password = "123456";
                                com.egeetouch.egeetouch_manager.BLEService.selected_lock_role = 0;
                                com.egeetouch.egeetouch_manager.BLEService.verified_password_done = true;
                                com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 67;
                                java.lang.System.out.println("Hello checking the lock ver 8");
                                return;
                            }
                            return;
                        } else if (r1[0] == -52) {
                            if (com.egeetouch.egeetouch_manager.BLEService.new_password_verification) {
                                com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = com.egeetouch.egeetouch_manager.BLEService.Verify_Admin_Password_new_lock;
                            } else {
                                com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 97;
                            }
                            java.lang.System.out.println("Hello checking the lock ver 9");
                            return;
                        } else {
                            if (!com.egeetouch.egeetouch_manager.UI_BLE.add_lock_mode) {
                                com.egeetouch.egeetouch_manager.BLEService.verified_password_done = true;
                                java.lang.System.out.println("Hello checking the lock ver 10");
                            }
                            com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 0;
                            com.egeetouch.egeetouch_manager.UI_BLE.BLE_UI = 1;
                            java.lang.System.out.println("Hello checking the lock ver 12 : " + com.egeetouch.egeetouch_manager.BLEService.selected_lock_model);
                            java.lang.System.out.println("HEY you have found an old lock (NOT 123456)");
                            return;
                        }
                    case com.egeetouch.egeetouch_manager.BLEService.Read_response_timestamp /* 228 */:
                        if (com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2500") || com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2550")) {
                            java.lang.System.out.println("Hello checking the TimeStamp receive : " + java.util.Arrays.toString(r1));
                            r4 = false;
                            com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 0;
                        } else {
                            if (java.lang.Integer.valueOf(r1[0]).intValue() >= 0 || java.lang.Integer.valueOf(r1[0]).intValue() <= 10) {
                                r4 = false;
                                java.lang.System.out.println("Hello UUID2 timeStamp response:" + java.lang.Integer.valueOf(r1[0]));
                                com.egeetouch.egeetouch_manager.BLEService.Tag_Audit_status = java.lang.Integer.valueOf(r1[0]).intValue();
                                if (com.egeetouch.egeetouch_manager.BLEService.is_sendTimeStamp) {
                                    com.egeetouch.egeetouch_manager.BLEService.is_sendTimeStamp = false;
                                    com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 0;
                                } else {
                                    com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = com.egeetouch.egeetouch_manager.BLEService.Request_tag_AuditTrail_number;
                                }
                            }
                            r4 = false;
                        }
                        if (com.egeetouch.egeetouch_manager.MainActivity.isUserClickedShutdown) {
                            com.egeetouch.egeetouch_manager.MainActivity.isUserClickedShutdown = r4;
                            com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 112;
                            com.egeetouch.egeetouch_manager.BLEService.shutdown_done = true;
                            return;
                        } else if (com.egeetouch.egeetouch_manager.MainActivity.turnedOnNFCState) {
                            com.egeetouch.egeetouch_manager.MainActivity.turnedOnNFCState = r4;
                            com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = com.egeetouch.egeetouch_manager.BLEService.disconnect;
                            com.egeetouch.egeetouch_manager.BLEService.shutdown_done = true;
                            return;
                        } else {
                            return;
                        }
                    case com.egeetouch.egeetouch_manager.BLEService.Read_response_clear_tag_AuditTrail /* 233 */:
                        if (r1[0] == 17) {
                            java.lang.System.out.println("Hello checking Audit Trail Backup and  Cleared!");
                            com.egeetouch.egeetouch_manager.BLEService.AvailableAuditCount = 0;
                            com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 0;
                            return;
                        }
                        return;
                    case com.egeetouch.egeetouch_manager.BLEService.ReadResponse_AutoLocking_Settings /* 237 */:
                        if (r1[0] == -103) {
                            com.egeetouch.egeetouch_manager.BLEService.AutoLockingMode = !com.egeetouch.egeetouch_manager.BLEService.AutoLockingMode;
                        }
                        java.lang.System.out.println("Hello checking AutoLocking_settings Response:" + java.util.Arrays.toString(r1));
                        com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 0;
                        return;
                    case com.egeetouch.egeetouch_manager.BLEService.ReadResponse_ShackleBypass /* 240 */:
                        if (r1[0] == -103) {
                            com.egeetouch.egeetouch_manager.BLEService.AutoLockingMode = com.egeetouch.egeetouch_manager.BLEService.isShackleBypass_turnedOn ? com.egeetouch.egeetouch_manager.BLEService.AutoLockingMode : false;
                        }
                        com.egeetouch.egeetouch_manager.BLEService.isShackleBypass_turnedOn = !com.egeetouch.egeetouch_manager.BLEService.isShackleBypass_turnedOn;
                        java.lang.System.out.println("Hello checking ReadResponse_ShackleBypass Response:" + java.util.Arrays.toString(r1));
                        com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 113;
                        return;
                    case com.egeetouch.egeetouch_manager.BLEService.ReadResponse_ChangePasscode /* 245 */:
                        java.lang.System.out.println("Hello checking the Passcode Add Reply !" + (r1[0] & kotlin.UByte.MAX_VALUE));
                        if ((r1[0] & kotlin.UByte.MAX_VALUE) == 173) {
                            com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 0;
                            com.egeetouch.egeetouch_manager.Firebase_Data_management.sendDirectionCodeToServer(com.egeetouch.egeetouch_manager.BLEService.parsedIp45SerialNumber, com.egeetouch.egeetouch_manager.BLEService.StrPasscodeEmoji, com.egeetouch.egeetouch_manager.BLEService.new_passcode, com.egeetouch.egeetouch_manager.BLEService.new_passcodeName, r20.lotoInfo.nextAvailabeIndex);
                            com.egeetouch.egeetouch_manager.UI_BLE.BLE_UI = 28;
                            java.lang.System.out.println("Hello checking the Passcode Success1 !!");
                            return;
                        } else if ((r1[0] & kotlin.UByte.MAX_VALUE) == 222) {
                            if (r20.lotoInfo.isDeleteAdd_mode()) {
                                com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = com.egeetouch.egeetouch_manager.BLEService.Request_ChangePasscode;
                                com.egeetouch.egeetouch_manager.LotoInfo r1 = r20.lotoInfo;
                                r1.setUpdateMode(r1.ADD_PASSCODE);
                                return;
                            }
                            com.egeetouch.egeetouch_manager.Firebase_Data_management.deletePasscode(com.egeetouch.egeetouch_manager.BLEService.parsedIp45SerialNumber, r20.lotoInfo.deleteIndex);
                            com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 0;
                            return;
                        } else if ((r1[0] & kotlin.UByte.MAX_VALUE) == 15) {
                            com.egeetouch.egeetouch_manager.UI_BLE.BLE_UI = 29;
                            com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 0;
                            return;
                        } else if (r20.lotoInfo.getUpdateMode() == r20.lotoInfo.ADD_PASSCODE) {
                            r20.lotoInfo.setDeleteAdd_mode(true);
                            com.egeetouch.egeetouch_manager.LotoInfo r1 = r20.lotoInfo;
                            r1.setUpdateMode(r1.DELETE_PASSCODE);
                            com.egeetouch.egeetouch_manager.LotoInfo r1 = r20.lotoInfo;
                            r1.deleteIndex = r1.nextAvailabeIndex;
                            com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = com.egeetouch.egeetouch_manager.BLEService.Request_ChangePasscode;
                            return;
                        } else if (r20.lotoInfo.getUpdateMode() != r20.lotoInfo.DELETE_PASSCODE || r20.lotoInfo.isDeleteAdd_mode()) {
                            return;
                        } else {
                            com.egeetouch.egeetouch_manager.Firebase_Data_management.deletePasscode(com.egeetouch.egeetouch_manager.BLEService.parsedIp45SerialNumber, r20.lotoInfo.deleteIndex);
                            com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 0;
                            return;
                        }
                    case com.egeetouch.egeetouch_manager.BLEService.ReadResponse_syncPasscode /* 258 */:
                        java.lang.System.out.println("Hello checking the Passcode Sync Reply !" + (r1[0] & kotlin.UByte.MAX_VALUE) + " syncCount:" + r20.syncPasscodeCount + "  total:" + r20.totalsyncPasscode);
                        if ((r1[0] & kotlin.UByte.MAX_VALUE) == 173) {
                            int r1 = r20.syncPasscodeCount;
                            if (r1 < r20.totalsyncPasscode - 1) {
                                r20.syncPasscodeCount = r1 + 1;
                                com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 257;
                                return;
                            }
                            com.egeetouch.egeetouch_manager.UI_BLE.BLE_UI = 34;
                            com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 0;
                            r20.syncPasscodeCount = 0;
                            r20.totalsyncPasscode = 0;
                            return;
                        } else if ((r1[0] & kotlin.UByte.MAX_VALUE) == 222) {
                            if (r20.lotoInfo.isDeleteAdd_mode()) {
                                com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 257;
                                com.egeetouch.egeetouch_manager.LotoInfo r1 = r20.lotoInfo;
                                r1.setUpdateMode(r1.ADD_PASSCODE);
                                return;
                            }
                            int r1 = r20.syncPasscodeCount;
                            if (r1 < r20.totalsyncPasscode - 1) {
                                r20.syncPasscodeCount = r1 + 1;
                                com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 257;
                                return;
                            }
                            com.egeetouch.egeetouch_manager.UI_BLE.BLE_UI = 34;
                            com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 0;
                            r20.syncPasscodeCount = 0;
                            r20.totalsyncPasscode = 0;
                            return;
                        } else if ((r1[0] & kotlin.UByte.MAX_VALUE) == 15) {
                            if (r20.lotoInfo.getUpdateMode() == r20.lotoInfo.ADD_PASSCODE) {
                                r20.lotoInfo.setDeleteAdd_mode(true);
                                com.egeetouch.egeetouch_manager.LotoInfo r1 = r20.lotoInfo;
                                r1.setUpdateMode(r1.DELETE_PASSCODE);
                                com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 257;
                                return;
                            }
                            com.egeetouch.egeetouch_manager.UI_BLE.BLE_UI = 34;
                            com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 0;
                            return;
                        } else if (r20.lotoInfo.getUpdateMode() == r20.lotoInfo.ADD_PASSCODE) {
                            r20.lotoInfo.setDeleteAdd_mode(true);
                            com.egeetouch.egeetouch_manager.LotoInfo r1 = r20.lotoInfo;
                            r1.setUpdateMode(r1.DELETE_PASSCODE);
                            com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 257;
                            return;
                        } else {
                            com.egeetouch.egeetouch_manager.UI_BLE.BLE_UI = 34;
                            com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 0;
                            return;
                        }
                    default:
                        switch (r11) {
                            case com.egeetouch.egeetouch_manager.BLEService.Read_response_update_reconnecting_period /* 165 */:
                                android.util.Log.i("TAG", java.lang.String.valueOf((int) r1[0]));
                                if (r1[0] == 90 || r1[0] == -106 || r1[0] == 30 || r1[0] == 60 || r1[0] == 6 || r1[0] == 18 || r1[0] == -76) {
                                    com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 0;
                                    com.egeetouch.egeetouch_manager.UI_BLE.BLE_UI = 6;
                                    return;
                                } else if (r1[0] == r20.autoReconnectionTime) {
                                    com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 0;
                                    com.egeetouch.egeetouch_manager.UI_BLE.BLE_UI = 6;
                                    return;
                                } else {
                                    com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 117;
                                    return;
                                }
                            case com.egeetouch.egeetouch_manager.BLEService.Read_Response_enter_low_power_mode /* 166 */:
                                android.util.Log.i("Tag", "Read_Response_enter_low_power_mode: " + java.lang.String.valueOf((int) r1[0]));
                                if (java.lang.String.valueOf((int) r1[0]).equals("118")) {
                                    android.util.Log.i("TAG", "entered_low_power_mode");
                                    com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 0;
                                    return;
                                }
                                com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 118;
                                return;
                            case com.egeetouch.egeetouch_manager.BLEService.Read_Response_quit_low_power_mode /* 167 */:
                                android.util.Log.i("Tag", "Read_Response_quit_low_power_mode: " + java.lang.String.valueOf((int) r1[0]));
                                if (java.lang.String.valueOf((int) r1[0]).equals("119")) {
                                    com.egeetouch.egeetouch_manager.BLEService.trigger_low_power_mode = false;
                                    com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 0;
                                    return;
                                }
                                int r1 = com.egeetouch.egeetouch_manager.BLEService.transmission_error_counter;
                                if (r1 >= 2) {
                                    com.egeetouch.egeetouch_manager.BLEService.transmission_error_counter = 0;
                                    com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 0;
                                    return;
                                }
                                com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 119;
                                com.egeetouch.egeetouch_manager.BLEService.transmission_error_counter = r1 + 1;
                                return;
                            default:
                                switch (r11) {
                                    case 600:
                                        java.lang.System.out.println("Hello checking the Tag ADD receive : " + java.util.Arrays.toString(r1) + " RESULT: " + (r1[3] & kotlin.UByte.MAX_VALUE));
                                        if ((r1[0] & kotlin.UByte.MAX_VALUE) == 80) {
                                            if (r1[3] == 81) {
                                                java.lang.System.out.println("Hello checking the Tag ADD Database ID: " + com.egeetouch.egeetouch_manager.BLEService.list_Add_tag_id_IP66.get(com.egeetouch.egeetouch_manager.BLEService.add_tag_count_IP66) + " NAME: " + com.egeetouch.egeetouch_manager.BLEService.list_Add_tag_name_IP66.get(com.egeetouch.egeetouch_manager.BLEService.add_tag_count_IP66));
                                                com.google.firebase.database.DatabaseReference r4 = com.google.firebase.database.FirebaseDatabase.getInstance().getReference("tagDirectory").child(com.egeetouch.egeetouch_manager.BLEService.parsedIp45SerialNumber);
                                                if (com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2500") || com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2550")) {
                                                    com.google.firebase.database.FirebaseDatabase r2 = com.google.firebase.database.FirebaseDatabase.getInstance(com.google.firebase.FirebaseApp.getInstance("industrial"));
                                                    com.egeetouch.egeetouch_manager.BLEService.commercialDatabase = r2;
                                                    r4 = r2.getReference("tagDirectory").child(com.egeetouch.egeetouch_manager.BLEService.parsedIp45SerialNumber);
                                                }
                                                com.google.firebase.database.DatabaseReference r2 = r4.push();
                                                r2.child("PageNumber").setValue(java.lang.Integer.valueOf(com.egeetouch.egeetouch_manager.BLEService.tag_page_number));
                                                r2.child("tagId").setValue(com.egeetouch.egeetouch_manager.BLEService.list_Add_tag_id_IP66.get(com.egeetouch.egeetouch_manager.BLEService.add_tag_count_IP66));
                                                r2.child("tagName").setValue(com.egeetouch.egeetouch_manager.BLEService.list_Add_tag_name_IP66.get(com.egeetouch.egeetouch_manager.BLEService.add_tag_count_IP66));
                                                r2.child("timeAdded").setValue(java.lang.Double.valueOf(com.egeetouch.egeetouch_manager.MainActivity.currentTimestampDouble));
                                                r2.child("reply").setValue(java.lang.Integer.valueOf(r1[3] & kotlin.UByte.MAX_VALUE));
                                                if (com.egeetouch.egeetouch_manager.BLEService.add_tag_count_IP66 + 1 < com.egeetouch.egeetouch_manager.BLEService.list_Add_tag_id_IP66.size()) {
                                                    com.egeetouch.egeetouch_manager.BLEService.add_tag_count_IP66++;
                                                    com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 500;
                                                    return;
                                                }
                                                r20.mMainActivity.Retrive_Tag_from_FirebaseIP66();
                                                com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 0;
                                                return;
                                            } else if (r1[3] == 93) {
                                                com.google.firebase.database.DatabaseReference r4 = com.google.firebase.database.FirebaseDatabase.getInstance().getReference("tagDirectory").child(com.egeetouch.egeetouch_manager.BLEService.parsedIp45SerialNumber);
                                                if (com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2500") || com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2550")) {
                                                    com.google.firebase.database.FirebaseDatabase r2 = com.google.firebase.database.FirebaseDatabase.getInstance(com.google.firebase.FirebaseApp.getInstance("industrial"));
                                                    com.egeetouch.egeetouch_manager.BLEService.commercialDatabase = r2;
                                                    r4 = r2.getReference("tagDirectory").child(com.egeetouch.egeetouch_manager.BLEService.parsedIp45SerialNumber);
                                                }
                                                com.google.firebase.database.DatabaseReference r2 = r4.push();
                                                r2.child("PageNumber").setValue(java.lang.Integer.valueOf(com.egeetouch.egeetouch_manager.BLEService.tag_page_number));
                                                r2.child("tagId").setValue(com.egeetouch.egeetouch_manager.BLEService.list_Add_tag_id_IP66.get(com.egeetouch.egeetouch_manager.BLEService.add_tag_count_IP66));
                                                r2.child("tagName").setValue(com.egeetouch.egeetouch_manager.BLEService.list_Add_tag_name_IP66.get(com.egeetouch.egeetouch_manager.BLEService.add_tag_count_IP66));
                                                r2.child("timeAdded").setValue(java.lang.Double.valueOf(com.egeetouch.egeetouch_manager.MainActivity.currentTimestampDouble));
                                                r2.child("reply").setValue(java.lang.Integer.valueOf(r1[3] & kotlin.UByte.MAX_VALUE));
                                                if (com.egeetouch.egeetouch_manager.BLEService.add_tag_count_IP66 + 1 < com.egeetouch.egeetouch_manager.BLEService.list_Add_tag_id_IP66.size()) {
                                                    com.egeetouch.egeetouch_manager.BLEService.add_tag_count_IP66++;
                                                    com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 500;
                                                    return;
                                                }
                                                com.egeetouch.egeetouch_manager.UI_BLE.BLE_UI = 9;
                                                r20.mMainActivity.Retrive_Tag_from_FirebaseIP66();
                                                com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 504;
                                                return;
                                            } else if ((r1[3] & kotlin.UByte.MAX_VALUE) == 225) {
                                                com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 500;
                                                return;
                                            } else {
                                                com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 0;
                                                return;
                                            }
                                        }
                                        com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 500;
                                        return;
                                    case 601:
                                        java.lang.System.out.println("Hello checking the Tag DELETE receive : " + java.util.Arrays.toString(r1) + " RESULT: " + (r1[3] & kotlin.UByte.MAX_VALUE) + " TAG: " + com.egeetouch.egeetouch_manager.BLEService.deleteTagID_IP66);
                                        if ((r1[0] & kotlin.UByte.MAX_VALUE) == 80) {
                                            if (r1[3] == 82 || r1[3] == 92) {
                                                com.google.firebase.database.DatabaseReference r1 = com.google.firebase.database.FirebaseDatabase.getInstance().getReference("tagDirectory").child(com.egeetouch.egeetouch_manager.BLEService.parsedIp45SerialNumber);
                                                if (com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2500") || com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2550")) {
                                                    com.google.firebase.database.FirebaseDatabase r1 = com.google.firebase.database.FirebaseDatabase.getInstance(com.google.firebase.FirebaseApp.getInstance("industrial"));
                                                    com.egeetouch.egeetouch_manager.BLEService.commercialDatabase = r1;
                                                    r1 = r1.getReference("tagDirectory").child(com.egeetouch.egeetouch_manager.BLEService.parsedIp45SerialNumber);
                                                }
                                                r1.addListenerForSingleValueEvent(new com.google.firebase.database.ValueEventListener() { // from class: com.egeetouch.egeetouch_manager.BLEService.13
                                                    @Override // com.google.firebase.database.ValueEventListener
                                                    public void onCancelled(com.google.firebase.database.DatabaseError r1) {
                                                    }

                                                    {
                                                    }


                                                    @Override // com.google.firebase.database.ValueEventListener
                                                    public void onDataChange(com.google.firebase.database.DataSnapshot r3) {
                                                        for (com.google.firebase.database.DataSnapshot r0 : r3.getChildren()) {
                                                            r0.getRef().child("tagId").addListenerForSingleValueEvent(new com.google.firebase.database.ValueEventListener() { // from class: com.egeetouch.egeetouch_manager.BLEService.13.1
                                                                @Override // com.google.firebase.database.ValueEventListener
                                                                public void onCancelled(com.google.firebase.database.DatabaseError r1) {
                                                                }

                                                                {
                                                                }

                                                                @Override // com.google.firebase.database.ValueEventListener
                                                                public void onDataChange(com.google.firebase.database.DataSnapshot r3) {
                                                                    if (r3.getValue().toString().equals(com.egeetouch.egeetouch_manager.BLEService.deleteTagID_IP66)) {
                                                                        android.util.Log.i("Tag", "Delete Tag from Cloud : " + r3.getRef().getKey().toString());
                                                                        r3.getRef().getParent().removeValue();
                                                                        com.egeetouch.egeetouch_manager.UI_BLE.BLE_UI = 9;
                                                                    }
                                                                }
                                                            });
                                                        }
                                                    }
                                                });
                                                java.lang.System.out.println("Hello checking the Tag DELETE is SUCCESS");
                                                com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 0;
                                                return;
                                            }
                                            return;
                                        }
                                        com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 501;
                                        return;
                                    case 602:
                                        java.lang.System.out.println("Hello checking the Tag CHECK receive : " + java.util.Arrays.toString(r1) + " RESULT: " + (r1[3] & kotlin.UByte.MAX_VALUE));
                                        if ((r1[0] & kotlin.UByte.MAX_VALUE) == 80) {
                                            if (r1[3] == 83) {
                                                com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 503;
                                                return;
                                            } else {
                                                com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 0;
                                                return;
                                            }
                                        }
                                        return;
                                    case 603:
                                        if ((r1[0] & kotlin.UByte.MAX_VALUE) == 80 && (r1[3] & kotlin.UByte.MAX_VALUE) == 84) {
                                            java.lang.System.out.println("CMDCMD found tag at index: " + com.egeetouch.egeetouch_manager.BLEService.index_tag_IP66);
                                            int r2 = com.egeetouch.egeetouch_manager.BLEService.index_tag_IP66;
                                            if (r2 < 501) {
                                                com.egeetouch.egeetouch_manager.BLEService.index_tag_IP66 = r2 + 1;
                                                com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 503;
                                                com.egeetouch.egeetouch_manager.UI_BLE.pls_wait_content = com.egeetouch.egeetouch_manager.MainActivity.context.getResources().getString(com.egeetouch.egeetouch_manager.R.string.checking_tag);
                                                if (r20.lockInfo.getTotalTagCount() <= 0) {
                                                    com.egeetouch.egeetouch_manager.UI_BLE.progress_val = (com.egeetouch.egeetouch_manager.BLEService.index_tag_IP66 * 100) / 1;
                                                } else {
                                                    com.egeetouch.egeetouch_manager.UI_BLE.progress_val = (com.egeetouch.egeetouch_manager.BLEService.index_tag_IP66 * 100) / r20.lockInfo.getTotalTagCount();
                                                }
                                                com.egeetouch.egeetouch_manager.UI_BLE.BLE_UI = 24;
                                                com.egeetouch.egeetouch_manager.BLEService.sync_list.add(convertTagID_toHexa(r1[7], r1[6]));
                                                com.egeetouch.egeetouch_manager.BLEService.sync_list_page.add(0);
                                                return;
                                            }
                                            com.egeetouch.egeetouch_manager.BLEService.index_tag_IP66 = 0;
                                            com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 0;
                                            syncTagProcessing();
                                            return;
                                        }
                                        java.lang.System.out.println("CMDCMD NO tag at index: " + com.egeetouch.egeetouch_manager.BLEService.index_tag_IP66);
                                        com.egeetouch.egeetouch_manager.BLEService.index_tag_IP66 = 0;
                                        com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 0;
                                        syncTagProcessing();
                                        return;
                                    case 604:
                                        java.lang.System.out.println("Hello checking the STATUS receive : " + java.util.Arrays.toString(r1) + " RESULT: " + (r1[3] & kotlin.UByte.MAX_VALUE));
                                        long r2 = 0;
                                        if ((r1[0] & kotlin.UByte.MAX_VALUE) == 48) {
                                            int r5 = 0;
                                            for (int r4 = 13; r4 > 11; r4--) {
                                                r5 = (r5 << 8) + (r1[r4] & kotlin.UByte.MAX_VALUE);
                                            }
                                            com.egeetouch.egeetouch_manager.BLEService.AvailableTagCount = r5;
                                            int r7 = 0;
                                            for (int r4 = 15; r4 > 13; r4--) {
                                                r7 = (r7 << 8) + (r1[r4] & kotlin.UByte.MAX_VALUE);
                                            }
                                            com.egeetouch.egeetouch_manager.BLEService.AvailableAuditCount = r7;
                                            if (r7 > 500) {
                                                r7 = 500;
                                            }
                                            com.egeetouch.egeetouch_manager.BLEService.audittrail_max_count = r7;
                                            for (int r4 = 19; r4 > 15; r4--) {
                                                r2 = (r2 << 8) + (r1[r4] & kotlin.UByte.MAX_VALUE);
                                            }
                                            java.lang.System.out.println("Hello checking the STATUS Details Audit.C : " + com.egeetouch.egeetouch_manager.BLEService.AvailableAuditCount + " Tag.C : " + com.egeetouch.egeetouch_manager.BLEService.AvailableTagCount + " TimeS: " + r2 + " date: " + new java.text.SimpleDateFormat("dd/MM/yyyy hh:mm a").format(java.lang.Long.valueOf(1000 * r2)));
                                            r20.lockInfo.setTotalTagAuditTrailCount(com.egeetouch.egeetouch_manager.BLEService.AvailableAuditCount);
                                            r20.lockInfo.setTotalTagCount(com.egeetouch.egeetouch_manager.BLEService.AvailableTagCount);
                                        }
                                        com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 510;
                                        return;
                                    case 605:
                                        java.lang.System.out.println("Hello checking the Audit STATUS receive : " + java.util.Arrays.toString(r1) + " RESULT: " + (r1[3] & kotlin.UByte.MAX_VALUE) + " [0]: " + (r1[0] & kotlin.UByte.MAX_VALUE) + " [4]: " + (r1[4] & kotlin.UByte.MAX_VALUE) + " [5]: " + (r1[5] & kotlin.UByte.MAX_VALUE) + " [6]: " + (r1[6] & kotlin.UByte.MAX_VALUE) + " [7]: " + (r1[6] & kotlin.UByte.MAX_VALUE));
                                        long r2 = 0;
                                        for (int r5 = 11; r5 > 7; r5--) {
                                            r2 = (r2 << 8) + (r1[r5] & kotlin.UByte.MAX_VALUE);
                                        }
                                        new java.text.SimpleDateFormat("dd/MM/yyyy hh:mm:ss a").format(java.lang.Long.valueOf(1000 * r2));
                                        java.lang.String r5 = convertTagID_toHexa(r1[7], r1[6]);
                                        if ((r1[0] & kotlin.UByte.MAX_VALUE) == 160) {
                                            if ((r1[4] & kotlin.UByte.MAX_VALUE) == 17) {
                                                com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 0;
                                                updateIP66_auditTrail(r2, "N.A", r1[6] & kotlin.UByte.MAX_VALUE);
                                                return;
                                            } else if ((r1[0] & kotlin.UByte.MAX_VALUE) == 160) {
                                                if (com.egeetouch.egeetouch_manager.BLEService.audittrail_count_IP66 < com.egeetouch.egeetouch_manager.BLEService.audittrail_max_count) {
                                                    com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 0;
                                                    updateIP66_auditTrail(r2, r5, 0);
                                                    return;
                                                }
                                                com.egeetouch.egeetouch_manager.BLEService.audit_current_index_IP66 = 0;
                                                com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 0;
                                                return;
                                            } else {
                                                com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 0;
                                                return;
                                            }
                                        }
                                        com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 505;
                                        return;
                                    case 606:
                                        if ((r1[0] & kotlin.UByte.MAX_VALUE) == 160) {
                                            if ((r1[3] & kotlin.UByte.MAX_VALUE) == 164) {
                                                com.egeetouch.egeetouch_manager.BLEService.AvailableAuditCount = 0;
                                                if (!com.egeetouch.egeetouch_manager.BLEService.isTagAuditTrail_updateOnly) {
                                                    com.egeetouch.egeetouch_manager.UI_BLE.BLE_UI = 30;
                                                } else {
                                                    com.egeetouch.egeetouch_manager.BLEService.isTagAuditTrail_updateOnly = false;
                                                    com.egeetouch.egeetouch_manager.UI_BLE.BLE_UI = 32;
                                                }
                                                com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 0;
                                                return;
                                            }
                                            return;
                                        }
                                        com.egeetouch.egeetouch_manager.BLEService.AvailableAuditCount = 0;
                                        if (!com.egeetouch.egeetouch_manager.BLEService.isTagAuditTrail_updateOnly) {
                                            com.egeetouch.egeetouch_manager.UI_BLE.BLE_UI = 30;
                                        } else {
                                            com.egeetouch.egeetouch_manager.BLEService.isTagAuditTrail_updateOnly = false;
                                            com.egeetouch.egeetouch_manager.UI_BLE.BLE_UI = 32;
                                        }
                                        com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 0;
                                        return;
                                    case 607:
                                        java.lang.System.out.println("Hello checking the Audit STATUS CHECK AUDIT COUNT  receive : " + java.util.Arrays.toString(r1) + " RESULT: " + (r1[3] & kotlin.UByte.MAX_VALUE) + "data[0]: " + (r1[0] & kotlin.UByte.MAX_VALUE) + "data[6]: " + (r1[6] & kotlin.UByte.MAX_VALUE));
                                        int r3 = 0;
                                        for (int r2 = 15; r2 > 13; r2--) {
                                            r3 = (r3 << 8) + (r1[r2] & kotlin.UByte.MAX_VALUE);
                                        }
                                        com.egeetouch.egeetouch_manager.BLEService.AvailableAuditCount = r3;
                                        java.lang.System.out.println("Hello checking the Audit STATUS CHECK AUDIT COUNT val : " + com.egeetouch.egeetouch_manager.BLEService.AvailableAuditCount);
                                        if (com.egeetouch.egeetouch_manager.BLEService.AvailableAuditCount == 0) {
                                            com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 0;
                                            return;
                                        } else {
                                            com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 505;
                                            return;
                                        }
                                    case 608:
                                        com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 0;
                                        return;
                                    case 609:
                                        if (r1[3] == 49) {
                                            com.egeetouch.egeetouch_manager.UI_BLE.BLE_UI = 6;
                                        }
                                        com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 0;
                                        return;
                                    case 610:
                                        if (r1[3] == 4) {
                                            java.lang.Integer.valueOf(r1[4]).intValue();
                                            java.lang.Integer.valueOf(r1[5]).intValue();
                                            com.egeetouch.egeetouch_manager.BLEService.LockReconnectingTime = java.lang.Integer.valueOf(r1[4]).intValue();
                                        }
                                        com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = com.egeetouch.egeetouch_manager.BLEService.Send_timeStamp;
                                        return;
                                    default:
                                        return;
                                }
                        }
                }
            }
        } else if (com.egeetouch.egeetouch_manager.BLEService.UUID_3.equals(r21.getUuid())) {
            byte[] r1 = r21.getValue();
            java.lang.System.out.println("Hello UUID_3 data!! " + java.util.Arrays.toString(r1));
            switch (com.egeetouch.egeetouch_manager.BLEService.Ble_Mode) {
                case 18:
                    int r2 = -1;
                    java.lang.System.out.println("HEY from BLEService case Read_Response_request_Tag getting called");
                    com.egeetouch.egeetouch_manager.BLEService.retrieve_tag_current_number = java.lang.Integer.valueOf(r1[1]).intValue();
                    android.util.Log.i("TAG", "retrieve_tag_current_number: " + java.lang.String.valueOf(com.egeetouch.egeetouch_manager.BLEService.retrieve_tag_current_number));
                    if (com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2100") && (com.egeetouch.egeetouch_manager.BLEService.selected_lock_version.equals("1.80") || java.lang.Float.valueOf(com.egeetouch.egeetouch_manager.BLEService.selected_lock_version).floatValue() > 1.8d)) {
                        java.lang.System.out.println("Hello Reply data request_tag list array:" + java.util.Arrays.toString(r1));
                        if (r1[0] == com.egeetouch.egeetouch_manager.BLEService.Request_list_page || r1[1] == com.egeetouch.egeetouch_manager.BLEService.Request_list_batch) {
                            for (int r2 = 2; r2 < 12; r2 += 2) {
                                if (r1[r2] != 0 || r1[r2 + 1] != 0) {
                                    java.lang.String r3 = convertTagID_toHexa(r1[r2], r1[r2 + 1]);
                                    com.egeetouch.egeetouch_manager.BLEService.check_page_graph.get(com.egeetouch.egeetouch_manager.BLEService.Request_list_page).add(r3);
                                    java.lang.System.out.println("CMDCMD UID: " + r3);
                                    if (!com.egeetouch.egeetouch_manager.BLEService.sync_list.contains(r3)) {
                                        com.egeetouch.egeetouch_manager.BLEService.sync_list.add(r3);
                                        com.egeetouch.egeetouch_manager.BLEService.sync_list_page.add(java.lang.Integer.valueOf(r1[0]));
                                    }
                                }
                            }
                            com.egeetouch.egeetouch_manager.UI_BLE.pls_wait_content = com.egeetouch.egeetouch_manager.MainActivity.context.getResources().getString(com.egeetouch.egeetouch_manager.R.string.checking_tag);
                            com.egeetouch.egeetouch_manager.UI_BLE.progress_val = (int) (com.egeetouch.egeetouch_manager.BLEService.Request_list_page * 8.33d);
                            com.egeetouch.egeetouch_manager.UI_BLE.BLE_UI = 24;
                        }
                        if (com.egeetouch.egeetouch_manager.BLEService.Request_list_page < 13) {
                            if (r1[0] == 15 && r1[0] == 15) {
                                com.egeetouch.egeetouch_manager.UI_BLE.pls_wait_content = com.egeetouch.egeetouch_manager.MainActivity.context.getResources().getString(com.egeetouch.egeetouch_manager.R.string.checking_tag);
                                com.egeetouch.egeetouch_manager.UI_BLE.progress_val = (int) (com.egeetouch.egeetouch_manager.BLEService.Request_list_page * 8.33d);
                                com.egeetouch.egeetouch_manager.UI_BLE.BLE_UI = 24;
                            }
                            int r1 = com.egeetouch.egeetouch_manager.BLEService.Request_list_batch;
                            if (r1 < 2) {
                                com.egeetouch.egeetouch_manager.BLEService.Request_list_batch = r1 + 1;
                                com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 100;
                                return;
                            }
                            int r3 = com.egeetouch.egeetouch_manager.BLEService.Request_list_page + 1;
                            com.egeetouch.egeetouch_manager.BLEService.Request_list_page = r3;
                            if (r3 < 13) {
                                com.egeetouch.egeetouch_manager.BLEService.Request_list_batch = 0;
                                com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 100;
                                return;
                            }
                            syncTagProcessing();
                            return;
                        }
                        com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 0;
                        for (int r7 = 0; r7 < 13; r7++) {
                            com.egeetouch.egeetouch_manager.MainActivity.tag_id_graph.get(r7).size();
                            com.egeetouch.egeetouch_manager.BLEService.check_page_graph.get(r7).size();
                        }
                        return;
                    } else if (com.egeetouch.egeetouch_manager.BLEService.retrieve_tag_current_number > com.egeetouch.egeetouch_manager.BLEService.retrieved_Tag_number) {
                        return;
                    } else {
                        byte r7 = r1[2];
                        if (r7 < 0) {
                            r4 = java.lang.Integer.toHexString(r7 + kotlin.UByte.MIN_VALUE);
                        } else {
                            r4 = java.lang.Integer.toHexString(r7);
                        }
                        byte r7 = r1[3];
                        if (r7 < 0) {
                            r7 = java.lang.Integer.toHexString(r7 + kotlin.UByte.MIN_VALUE);
                        } else {
                            r7 = java.lang.Integer.toHexString(r7);
                        }
                        if (r4.length() == 1) {
                            r4 = com.facebook.appevents.AppEventsConstants.EVENT_PARAM_VALUE_NO + r4;
                        }
                        if (r7.length() == 1) {
                            r7 = com.facebook.appevents.AppEventsConstants.EVENT_PARAM_VALUE_NO + r7;
                        }
                        java.lang.String r8 = " " + r4 + " " + r7 + " ";
                        java.lang.String r11 = "";
                        for (int r9 = 0; r9 < 12; r9++) {
                            int r12 = r9 + 4;
                            if (r1[r12] >= 32 && r1[r12] < Byte.MAX_VALUE) {
                                r11 = r11 + java.lang.Character.toString((char) r1[r12]);
                            }
                            if (!com.egeetouch.egeetouch_manager.DatabaseVariable.db_tag.rawQuery(com.egeetouch.egeetouch_manager.DatabaseVariable.tagdb_Query_UID_exist2(r11, com.egeetouch.egeetouch_manager.BLEService.selected_lock_name), null).moveToNext()) {
                                android.database.Cursor r1 = com.egeetouch.egeetouch_manager.DatabaseVariable.db_tag.rawQuery("SELECT * FROM Tag WHERE Lock = " + android.database.DatabaseUtils.sqlEscapeString(com.egeetouch.egeetouch_manager.BLEService.selected_lock_name) + " AND TagName =" + android.database.DatabaseUtils.sqlEscapeString(r11), null);
                                if (r1.getCount() != 0) {
                                    java.lang.String[] r9 = new java.lang.String[35];
                                    while (r1.moveToNext()) {
                                        r2++;
                                        r9[r2] = r1.getString(1);
                                    }
                                    byte[] r2 = new byte[7];
                                    for (int r12 = 0; r12 < r1.getCount(); r12++) {
                                        java.lang.String[] r13 = r9[r12].split(" ");
                                        for (int r14 = 1; r14 < 3; r14++) {
                                            r2[r14] = com.egeetouch.egeetouch_manager.Helper_Math.ConvertStringToHexByte(r13[r14]);
                                        }
                                    }
                                    int r6 = r2[1];
                                    if (r6 < 0) {
                                        r6 += 256;
                                        r9 = java.lang.Integer.toHexString(r6);
                                    } else {
                                        r9 = java.lang.Integer.toHexString(r6);
                                    }
                                    if (r6 < 16) {
                                        r9 = com.facebook.appevents.AppEventsConstants.EVENT_PARAM_VALUE_NO + r9;
                                    }
                                    int r2 = r2[2];
                                    if (r2 < 0) {
                                        r2 += 256;
                                        r6 = java.lang.Integer.toHexString(r2);
                                    } else {
                                        r6 = java.lang.Integer.toHexString(r2);
                                    }
                                    if (r2 < 16) {
                                        r6 = com.facebook.appevents.AppEventsConstants.EVENT_PARAM_VALUE_NO + r6;
                                    }
                                    if (!r9.equals(r4) || !r6.equals(r7)) {
                                        android.util.Log.i("", r11 + " " + r8);
                                        com.egeetouch.egeetouch_manager.DatabaseVariable.db_tag.execSQL(com.egeetouch.egeetouch_manager.DatabaseVariable.tagdb_insert_value(r11 + java.lang.String.valueOf(r1.getCount() + 1), r8, com.egeetouch.egeetouch_manager.BLEService.selected_lock_name));
                                    }
                                }
                            } else {
                                android.util.Log.i("", r11 + " " + r8);
                                com.egeetouch.egeetouch_manager.DatabaseVariable.db_tag.execSQL(com.egeetouch.egeetouch_manager.DatabaseVariable.tagdb_insert_value(r11, r8, com.egeetouch.egeetouch_manager.BLEService.selected_lock_name));
                            }
                            if (com.egeetouch.egeetouch_manager.BLEService.retrieve_tag_current_number != com.egeetouch.egeetouch_manager.BLEService.retrieved_Tag_number) {
                                android.util.Log.i("", "Retrieved Tags Done");
                                com.egeetouch.egeetouch_manager.BLEService.retrieved_tag_done = true;
                                com.egeetouch.egeetouch_manager.BLEService.retrieve_tag_current_number = 1;
                                com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 0;
                                com.egeetouch.egeetouch_manager.UI_BLE.BLE_UI = 10;
                                com.egeetouch.egeetouch_manager.BLEService.tag_updateSuccess = true;
                                return;
                            }
                            if (com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2100")) {
                                com.egeetouch.egeetouch_manager.BLEService.loading_percentage = 5;
                            } else {
                                com.egeetouch.egeetouch_manager.BLEService.loading_percentage = 20;
                            }
                            com.egeetouch.egeetouch_manager.UI_BLE.progress_val = com.egeetouch.egeetouch_manager.BLEService.retrieve_tag_current_number * com.egeetouch.egeetouch_manager.BLEService.loading_percentage;
                            com.egeetouch.egeetouch_manager.UI_BLE.BLE_UI = 24;
                            android.util.Log.i("TAG", "Request_Tag_list222");
                            com.egeetouch.egeetouch_manager.BLEService.retrieve_tag_current_number++;
                            com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 100;
                            return;
                        }
                        if (!com.egeetouch.egeetouch_manager.DatabaseVariable.db_tag.rawQuery(com.egeetouch.egeetouch_manager.DatabaseVariable.tagdb_Query_UID_exist2(r11, com.egeetouch.egeetouch_manager.BLEService.selected_lock_name), null).moveToNext()) {
                        }
                        if (com.egeetouch.egeetouch_manager.BLEService.retrieve_tag_current_number != com.egeetouch.egeetouch_manager.BLEService.retrieved_Tag_number) {
                        }
                    }
                    break;
                case 22:
                    java.lang.System.out.println("HEY from BLEService case Read_Response_Request_Tag_number getting called");
                    com.egeetouch.egeetouch_manager.BLEService.retrieved_Tag_number = java.lang.Integer.valueOf(r1[2]).intValue();
                    java.lang.System.out.println("HEY from BLEService case Read_Response_Request_Tag_number :retrieved_Tag_number " + com.egeetouch.egeetouch_manager.BLEService.retrieved_Tag_number);
                    android.util.Log.i("TAG", "Tag Number: " + java.lang.String.valueOf(com.egeetouch.egeetouch_manager.BLEService.retrieved_Tag_number));
                    if (com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2100") && (com.egeetouch.egeetouch_manager.BLEService.selected_lock_version.equals("1.80") || java.lang.Float.valueOf(com.egeetouch.egeetouch_manager.BLEService.selected_lock_version).floatValue() > 1.8d)) {
                        byte[] r2 = new byte[4];
                        for (int r3 = 0; r3 < 4; r3++) {
                            int r4 = 0;
                            while (r4 < 2) {
                                r4++;
                                r2[3 - r3] = r1[r4];
                            }
                        }
                        java.nio.ByteBuffer.wrap(r2).getInt();
                        com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 100;
                        return;
                    }
                    int r2 = com.egeetouch.egeetouch_manager.BLEService.retrieved_Tag_number;
                    if (r2 > 0 && r2 < 21) {
                        android.util.Log.i("TAG", "Request_Tag_list");
                        com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 100;
                        return;
                    }
                    com.egeetouch.egeetouch_manager.BLEService.retrieved_tag_done = true;
                    com.egeetouch.egeetouch_manager.BLEService.retrieve_tag_current_number = 0;
                    com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 0;
                    com.egeetouch.egeetouch_manager.UI_BLE.BLE_UI = 10;
                    android.util.Log.i("TAG", "update_UI_extract_Tag");
                    return;
                case 35:
                    com.egeetouch.egeetouch_manager.BLEService.retrieved_User_number = java.lang.Integer.valueOf(r1[2]).intValue();
                    android.util.Log.i("Tag", "retrieved_User_number: " + java.lang.String.valueOf(com.egeetouch.egeetouch_manager.BLEService.retrieved_User_number));
                    int r1 = com.egeetouch.egeetouch_manager.BLEService.retrieved_User_number;
                    if (r1 > 0 && r1 < 6) {
                        com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 101;
                        return;
                    }
                    com.egeetouch.egeetouch_manager.BLEService.retrieved_user_done = true;
                    com.egeetouch.egeetouch_manager.BLEService.User_current_number = 0;
                    com.egeetouch.egeetouch_manager.BLEService.User_loop_number = 0;
                    com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 0;
                    com.egeetouch.egeetouch_manager.UI_BLE.BLE_UI = 12;
                    android.util.Log.i("Tag", "update_UI_extract_user");
                    return;
                case 38:
                    com.egeetouch.egeetouch_manager.BLEService.User_current_number = java.lang.Integer.valueOf(r1[1]).intValue();
                    android.util.Log.i("", "User_current_number: " + java.lang.String.valueOf(com.egeetouch.egeetouch_manager.BLEService.User_current_number));
                    android.util.Log.i("", "User_loop_number: " + java.lang.String.valueOf(com.egeetouch.egeetouch_manager.BLEService.User_loop_number));
                    if (com.egeetouch.egeetouch_manager.BLEService.User_current_number == com.egeetouch.egeetouch_manager.BLEService.User_loop_number) {
                        r20.user_name_temp = "";
                        for (int r13 = 2; r13 < 16; r13++) {
                            if (r1[r13] >= 32 && r1[r13] <= Byte.MAX_VALUE) {
                                r20.user_name_temp += java.lang.Character.toString((char) r1[r13]);
                            }
                        }
                        android.util.Log.i("", "Read_Response_request_User_name: " + r20.user_name_temp);
                        android.database.Cursor r1 = com.egeetouch.egeetouch_manager.DatabaseVariable.db_user.rawQuery(com.egeetouch.egeetouch_manager.DatabaseVariable.userdb_Query_user_name_exist_in_lock(com.egeetouch.egeetouch_manager.BLEService.selected_lock_name, r20.user_name_temp), null);
                        if (!r1.moveToNext()) {
                            com.egeetouch.egeetouch_manager.DatabaseVariable.db_user.execSQL(com.egeetouch.egeetouch_manager.DatabaseVariable.userdb_insert_value(r20.user_name_temp, "", com.egeetouch.egeetouch_manager.BLEService.selected_lock_name));
                        }
                        r1.close();
                        com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 102;
                        return;
                    }
                    com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 101;
                    return;
                case 39:
                    com.egeetouch.egeetouch_manager.BLEService.User_current_number = java.lang.Integer.valueOf(r1[1]).intValue();
                    r20.user_password_temp = "";
                    android.util.Log.i("", "User_current_number: " + java.lang.String.valueOf(com.egeetouch.egeetouch_manager.BLEService.User_current_number));
                    for (int r2 = 5; r2 < 11; r2++) {
                        if (r1[r2] >= 32 && r1[r2] <= Byte.MAX_VALUE) {
                            r20.user_password_temp += java.lang.Character.toString((char) r1[r2]);
                        }
                    }
                    com.egeetouch.egeetouch_manager.DatabaseVariable.db_user.execSQL("UPDATE " + com.egeetouch.egeetouch_manager.DatabaseVariable.Element_name_user + " SET " + com.egeetouch.egeetouch_manager.DatabaseVariable.D2_user + " = '" + r20.user_password_temp + "' WHERE " + com.egeetouch.egeetouch_manager.DatabaseVariable.D1_user + "='" + r20.user_name_temp + "' AND " + com.egeetouch.egeetouch_manager.DatabaseVariable.D3_user + "='" + com.egeetouch.egeetouch_manager.BLEService.selected_lock_name + "'");
                    android.util.Log.i("", "user_password_temp: " + r20.user_password_temp);
                    android.util.Log.i("", "Read_Response_Request_User_Password");
                    if (com.egeetouch.egeetouch_manager.BLEService.User_current_number >= com.egeetouch.egeetouch_manager.BLEService.retrieved_User_number) {
                        com.egeetouch.egeetouch_manager.BLEService.retrieved_user_done = true;
                        com.egeetouch.egeetouch_manager.BLEService.User_current_number = 0;
                        com.egeetouch.egeetouch_manager.BLEService.User_loop_number = 0;
                        com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 0;
                        com.egeetouch.egeetouch_manager.UI_BLE.BLE_UI = 12;
                        return;
                    }
                    if (com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2100")) {
                        com.egeetouch.egeetouch_manager.BLEService.loading_percentage = 5;
                    } else {
                        com.egeetouch.egeetouch_manager.BLEService.loading_percentage = 20;
                    }
                    com.egeetouch.egeetouch_manager.UI_BLE.pls_wait_content = com.egeetouch.egeetouch_manager.MainActivity.context.getResources().getString(com.egeetouch.egeetouch_manager.R.string.retrieving_data_from_lock);
                    com.egeetouch.egeetouch_manager.UI_BLE.progress_val = com.egeetouch.egeetouch_manager.BLEService.User_loop_number * com.egeetouch.egeetouch_manager.BLEService.loading_percentage;
                    com.egeetouch.egeetouch_manager.UI_BLE.BLE_UI = 24;
                    com.egeetouch.egeetouch_manager.BLEService.User_loop_number++;
                    com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 101;
                    return;
                case 54:
                    android.util.Log.i("Tag", "Request_Audit_Data_1 audit_trail_loop_number: " + java.lang.String.valueOf(com.egeetouch.egeetouch_manager.BLEService.audit_trail_loop_number));
                    java.lang.Double.valueOf((double) com.google.firebase.remoteconfig.FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
                    java.lang.String r2 = (r1[2] == -1 || r1[3] == -1 || r1[4] == -1 || r1[5] == -1 || r1[6] == -1 || r1[7] == -1) ? "" : "20" + round_2_digit(java.lang.Byte.valueOf(r1[7])) + "/" + round_2_digit(java.lang.Byte.valueOf(r1[6])) + "/" + round_2_digit(java.lang.Byte.valueOf(r1[5])) + "   " + round_2_digit(java.lang.Byte.valueOf(r1[2])) + ":" + round_2_digit(java.lang.Byte.valueOf(r1[3])) + ":" + round_2_digit(java.lang.Byte.valueOf(r1[4]));
                    byte[] r4 = new byte[8];
                    for (int r7 = 0; r7 < 8; r7++) {
                        r4[7 - r7] = r1[r7 + 8];
                    }
                    java.lang.Double r1 = java.lang.Double.valueOf(java.nio.ByteBuffer.wrap(r4).getDouble());
                    if ((r1.doubleValue() > com.google.firebase.remoteconfig.FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE && r1.doubleValue() < 1.0E-6d) || (r1.doubleValue() < com.google.firebase.remoteconfig.FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE && r1.doubleValue() > -1.0E-6d)) {
                        r1 = java.lang.Double.valueOf((double) com.google.firebase.remoteconfig.FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
                    }
                    java.lang.Double r1 = java.lang.Double.valueOf(new java.text.DecimalFormat("#.######").format(r1));
                    android.util.Log.i("", "Location_Latitude: " + java.lang.String.valueOf(r1));
                    com.egeetouch.egeetouch_manager.DatabaseVariable.db_audittrail.execSQL(com.egeetouch.egeetouch_manager.DatabaseVariable.db_audittrail_update_value_bt_1(com.egeetouch.egeetouch_manager.BLEService.temp_audit_count1, r2, r1, new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(java.util.Calendar.getInstance().getTime()), com.egeetouch.egeetouch_manager.BLEService.selected_lock_name));
                    int r1 = com.egeetouch.egeetouch_manager.BLEService.temp_audit_count1 + 1;
                    com.egeetouch.egeetouch_manager.BLEService.temp_audit_count1 = r1;
                    if (r1 >= 21) {
                        com.egeetouch.egeetouch_manager.BLEService.temp_audit_count1 = 1;
                    }
                    android.util.Log.i("Tag", "Request_Audit_Data_1 Date_Time: " + r2);
                    com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 51;
                    return;
                case 55:
                    java.lang.Double r4 = java.lang.Double.valueOf((double) com.google.firebase.remoteconfig.FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
                    if (r1[0] != 0 || r1[1] != 0 || r1[2] != 0 || r1[3] != 0 || r1[4] != 0 || r1[7] == -1 || r1[7] == 0) {
                        r2 = 8;
                    } else if (r1[8] != -1 && r1[8] != 0) {
                        if ((" " + ((int) r1[7]) + " " + ((int) r1[8]) + " ") != "") {
                            android.util.Log.i("Tag", "I found UID and Tag");
                            byte r2 = r1[7];
                            if (r2 < 0) {
                                r2 = java.lang.Integer.toHexString(r2 + kotlin.UByte.MIN_VALUE);
                            } else {
                                r2 = java.lang.Integer.toHexString(r2);
                            }
                            byte r1 = r1[8];
                            if (r1 < 0) {
                                r1 = java.lang.Integer.toHexString(r1 + kotlin.UByte.MIN_VALUE);
                            } else {
                                r1 = java.lang.Integer.toHexString(r1);
                            }
                            java.lang.System.out.println("Hello hex1:" + r2 + " and hexa2:" + r1);
                            r10 = "Tag ID: " + r1 + " " + r2;
                        }
                        com.egeetouch.egeetouch_manager.DatabaseVariable.db_audittrail.execSQL(com.egeetouch.egeetouch_manager.DatabaseVariable.db_audittrail_update_value_bt_2(com.egeetouch.egeetouch_manager.BLEService.temp_audit_count, r10, r4, com.egeetouch.egeetouch_manager.BLEService.selected_lock_name));
                        r1 = com.egeetouch.egeetouch_manager.BLEService.audit_trail_loop_number - 1;
                        com.egeetouch.egeetouch_manager.BLEService.audit_trail_loop_number = r1;
                        if (r1 <= 0) {
                            com.egeetouch.egeetouch_manager.BLEService.audit_trail_loop_number = 20 - r1;
                        }
                        com.egeetouch.egeetouch_manager.BLEService.temp_audit_count++;
                        android.util.Log.i("Tag", "Request_Audit_Data_2 User: " + r10);
                        if (com.egeetouch.egeetouch_manager.BLEService.temp_audit_count < 21) {
                            com.egeetouch.egeetouch_manager.BLEService.temp_audit_count = 1;
                            com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 0;
                            com.egeetouch.egeetouch_manager.UI_BLE.BLE_UI = 21;
                            return;
                        }
                        com.egeetouch.egeetouch_manager.UI_BLE.pls_wait_content = com.egeetouch.egeetouch_manager.MainActivity.context.getResources().getString(com.egeetouch.egeetouch_manager.R.string.retrieving_data_from_lock);
                        com.egeetouch.egeetouch_manager.UI_BLE.progress_val = (com.egeetouch.egeetouch_manager.BLEService.temp_audit_count - 1) * 5;
                        com.egeetouch.egeetouch_manager.UI_BLE.BLE_UI = 24;
                        com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 50;
                        return;
                    } else {
                        r2 = 8;
                    }
                    if (r1[r2] != -1 && r1[r2] == 0) {
                        byte[] r3 = new byte[r2];
                        int r4 = 0;
                        while (r4 < r2) {
                            r3[7 - r4] = r1[r4 + 0];
                            r4++;
                            r2 = 8;
                        }
                        java.lang.Double r1 = java.lang.Double.valueOf(java.nio.ByteBuffer.wrap(r3).getDouble());
                        if ((r1.doubleValue() > com.google.firebase.remoteconfig.FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE && r1.doubleValue() < 1.0E-6d) || (r1.doubleValue() < com.google.firebase.remoteconfig.FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE && r1.doubleValue() > -1.0E-6d)) {
                            r1 = java.lang.Double.valueOf((double) com.google.firebase.remoteconfig.FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
                        }
                        r4 = r1;
                        r10 = "Admin(You)";
                    } else if (r1[r2] == -1 || r1[r2] <= 0 || r1[r2] > 20) {
                        r10 = "N.A.";
                    } else {
                        r10 = "User" + java.lang.String.valueOf((int) r1[r2]);
                        byte[] r3 = new byte[r2];
                        int r4 = 0;
                        while (r4 < r2) {
                            r3[7 - r4] = r1[r4 + 0];
                            r4++;
                            r2 = 8;
                        }
                        java.lang.Double r1 = java.lang.Double.valueOf(java.nio.ByteBuffer.wrap(r3).getDouble());
                        if ((r1.doubleValue() > com.google.firebase.remoteconfig.FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE && r1.doubleValue() < 1.0E-6d) || (r1.doubleValue() < com.google.firebase.remoteconfig.FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE && r1.doubleValue() > -1.0E-6d)) {
                            r1 = java.lang.Double.valueOf((double) com.google.firebase.remoteconfig.FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
                        }
                        r4 = r1;
                    }
                    com.egeetouch.egeetouch_manager.DatabaseVariable.db_audittrail.execSQL(com.egeetouch.egeetouch_manager.DatabaseVariable.db_audittrail_update_value_bt_2(com.egeetouch.egeetouch_manager.BLEService.temp_audit_count, r10, r4, com.egeetouch.egeetouch_manager.BLEService.selected_lock_name));
                    r1 = com.egeetouch.egeetouch_manager.BLEService.audit_trail_loop_number - 1;
                    com.egeetouch.egeetouch_manager.BLEService.audit_trail_loop_number = r1;
                    if (r1 <= 0) {
                    }
                    com.egeetouch.egeetouch_manager.BLEService.temp_audit_count++;
                    android.util.Log.i("Tag", "Request_Audit_Data_2 User: " + r10);
                    if (com.egeetouch.egeetouch_manager.BLEService.temp_audit_count < 21) {
                    }
                    break;
                case 90:
                    java.lang.System.out.println("Hello data[0]" + ((int) r1[0]));
                    com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 0;
                    return;
                case com.egeetouch.egeetouch_manager.BLEService.Read_Response_read_serial /* 168 */:
                    if (!com.egeetouch.egeetouch_manager.BLEService.Response_old_passcode_boolean) {
                        com.egeetouch.egeetouch_manager.BLEService.selected_lock_serial = bytesToHex(r1);
                        com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 0;
                        return;
                    }
                    byte r17 = 0;
                    java.lang.System.out.println("Hello Response_old_passcode_boolean");
                    com.egeetouch.egeetouch_manager.BLEService.Response_old_passcode_boolean = false;
                    int r2 = 0;
                    while (r2 < 16) {
                        com.egeetouch.egeetouch_manager.BLEService.old_pass_code[r2] = r17;
                        r2++;
                        r17 = 0;
                    }
                    int r2 = 0;
                    for (int r13 = 2; r13 <= r1[1] + 1; r13++) {
                        com.egeetouch.egeetouch_manager.BLEService.old_pass_code[r2] = r1[r13];
                        r2++;
                    }
                    com.egeetouch.egeetouch_manager.activity_change_pad_password.old_pass_code_mode = 1;
                    com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 0;
                    return;
                case com.egeetouch.egeetouch_manager.BLEService.Read_response_Lock_Setting_Param /* 171 */:
                    java.lang.System.out.println("Hello checking the lock Param value received : " + java.util.Arrays.toString(r1));
                    java.lang.System.out.println("Hello checking the lock Read_response_Lock_Setting_Param!");
                    android.util.Log.i("Tag", java.lang.Integer.valueOf(r1[1]) + " " + java.lang.Integer.valueOf(r1[2]) + " " + java.lang.Integer.valueOf(r1[3]) + " " + java.lang.Integer.valueOf(r1[4]) + " " + java.lang.Integer.valueOf(r1[5]) + " " + java.lang.Integer.valueOf(r1[6]) + " " + java.lang.Integer.valueOf(r1[7]) + " " + java.lang.Integer.valueOf(r1[8]) + " " + java.lang.Integer.valueOf(r1[9]) + " " + java.lang.Integer.valueOf(r1[10]) + " " + java.lang.Integer.valueOf(r1[11]) + " " + java.lang.Integer.valueOf(r1[12]) + " " + java.lang.Integer.valueOf(r1[13]) + " " + java.lang.Integer.valueOf(r1[14]) + " " + java.lang.Integer.valueOf(r1[15]));
                    if (com.egeetouch.egeetouch_manager.BLEService.ready_update_parameter) {
                        if ((r1[1] & 1) == 1) {
                            com.egeetouch.egeetouch_manager.BLEService.selected_lock_state = true;
                        } else {
                            com.egeetouch.egeetouch_manager.BLEService.selected_lock_state = false;
                        }
                        if ((com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2100") && java.lang.Float.valueOf(com.egeetouch.egeetouch_manager.BLEService.selected_lock_version).floatValue() > 1.8d) || com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT5100") || com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT5107") || com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT5109") || com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT5300")) {
                            if (com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT5100") || com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT5107") || com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT5109") || com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT5300")) {
                                r2 = 0;
                                for (int r3 = 5; r3 > 3; r3--) {
                                    r2 = (r2 << 8) + (r1[r3] & kotlin.UByte.MAX_VALUE);
                                }
                                r3 = 0;
                                for (int r4 = 7; r4 > 5; r4--) {
                                    r3 = (r3 << 8) + (r1[r4] & kotlin.UByte.MAX_VALUE);
                                }
                                int r5 = 0;
                                for (int r4 = 13; r4 > 11; r4--) {
                                    r5 = (r5 << 8) + (r1[r4] & kotlin.UByte.MAX_VALUE);
                                }
                                int r4 = r2 % 120;
                                com.egeetouch.egeetouch_manager.BLEService.PasscodeAuditIndex = r4 == 0 ? 119 : r4 - 1;
                                java.lang.System.out.println("Hello checking the PasscodeAuditIndex: " + com.egeetouch.egeetouch_manager.BLEService.PasscodeAuditIndex);
                                com.egeetouch.egeetouch_manager.BLEService.LockReconnectingTime = r5;
                            } else {
                                r2 = 0;
                                for (int r3 = 4; r3 < 6; r3++) {
                                    r2 = (r2 << 8) + (r1[r3] & kotlin.UByte.MAX_VALUE);
                                }
                                r3 = 0;
                                while (r4 < 8) {
                                    r3 = (r3 << 8) + (r1[r4] & kotlin.UByte.MAX_VALUE);
                                    r4++;
                                }
                                com.egeetouch.egeetouch_manager.BLEService.LockReconnectingTime = java.lang.Integer.valueOf(r1[3]).intValue();
                            }
                            com.egeetouch.egeetouch_manager.BLEService.AvailableTagCount = r3;
                            com.egeetouch.egeetouch_manager.BLEService.AvailableAuditCount = r2;
                            if ((r1[1] & 16) == 16) {
                                com.egeetouch.egeetouch_manager.BLEService.AutoLockingMode = true;
                                r5 = false;
                            } else {
                                r5 = false;
                                com.egeetouch.egeetouch_manager.BLEService.AutoLockingMode = false;
                            }
                            if ((r1[1] & 8) == 8) {
                                com.egeetouch.egeetouch_manager.BLEService.isShackleBypass_turnedOn = true;
                            } else {
                                com.egeetouch.egeetouch_manager.BLEService.isShackleBypass_turnedOn = r5;
                                if ((r1[2] & 1) == 1) {
                                    com.egeetouch.egeetouch_manager.BLEService.isShackleOpened = r5;
                                } else {
                                    com.egeetouch.egeetouch_manager.BLEService.isShackleOpened = true;
                                }
                            }
                            java.lang.System.out.println("Hello checking the Lock_Setting_Param arrayString :" + java.util.Arrays.toString(r1) + " auditCount:" + r2 + " tagCount:" + r3);
                            setCharacteristicNotification(r20.characteristic_5, true);
                        }
                        if (com.egeetouch.egeetouch_manager.MainActivity.watch_connected) {
                            java.lang.System.out.println("Hello watch connected!!");
                            java.lang.System.out.println("Hello watch lock status change1:" + (com.egeetouch.egeetouch_manager.BLEService.selected_lock_state ? "Locking completed" : "Unlocking completed"));
                        }
                        if (com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2100") && java.lang.Float.valueOf(com.egeetouch.egeetouch_manager.BLEService.selected_lock_version).floatValue() > 1.8d) {
                            setCharacteristicNotification(r20.characteristic_5, true);
                        }
                        if (com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2100") && com.egeetouch.egeetouch_manager.BLEService.isShackleBypass_turnedOn && java.lang.Float.valueOf(com.egeetouch.egeetouch_manager.BLEService.selected_lock_version).floatValue() > 1.8d) {
                            com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = com.egeetouch.egeetouch_manager.BLEService.ShackleBypass_settings;
                        } else {
                            com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 0;
                        }
                        if (com.egeetouch.egeetouch_manager.MainActivity.unlocking_pd != null && com.egeetouch.egeetouch_manager.MainActivity.unlocking_pd.isShowing()) {
                            com.egeetouch.egeetouch_manager.MainActivity.unlocking_pd.dismiss();
                        }
                        com.egeetouch.egeetouch_manager.UI_BLE.BLE_UI = 4;
                        com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = com.egeetouch.egeetouch_manager.BLEService.Read_masterTag_only;
                        return;
                    }
                    return;
                case com.egeetouch.egeetouch_manager.BLEService.Read_response_tag_AuditTrail_number /* 229 */:
                    if (r1[0] == -98) {
                        long r2 = 0;
                        for (int r4 = 1; r4 < 3; r4++) {
                            r2 = (r2 << 8) + (r1[r4] & kotlin.UByte.MAX_VALUE);
                        }
                        com.egeetouch.egeetouch_manager.BLEService.Total_Tag_Audit = r2;
                        int r1 = com.egeetouch.egeetouch_manager.BLEService.AuditTrail_Capacity;
                        com.egeetouch.egeetouch_manager.BLEService.Tag_Audit_count = r2 % ((long) r1) == 0 ? r1 - 1 : (r2 % r1) - 1;
                        if (r2 > r1) {
                            r2 = r1;
                        }
                        com.egeetouch.egeetouch_manager.BLEService.Total_Tag_Audit = r2;
                        java.lang.System.out.println("Hello checking the percentage Tag_Audit_count:" + com.egeetouch.egeetouch_manager.BLEService.Tag_Audit_count + " Total_Tag_Audit:" + com.egeetouch.egeetouch_manager.BLEService.Total_Tag_Audit);
                        com.egeetouch.egeetouch_manager.BLEService.AuditTrail_Percentage = com.egeetouch.egeetouch_manager.BLEService.Tag_Audit_count;
                        com.egeetouch.egeetouch_manager.DatabaseVariable.db_audittrail.execSQL(com.egeetouch.egeetouch_manager.DatabaseVariable.audittrail_db_delete_value_bylock(com.egeetouch.egeetouch_manager.BLEService.selected_lock_name));
                        com.egeetouch.egeetouch_manager.BLEService.listAuditTrailTagIDIP45.clear();
                        com.egeetouch.egeetouch_manager.BLEService.listAuditTrailTagTimeIP45.clear();
                        com.egeetouch.egeetouch_manager.BLEService.listAuditTagTypeIP45.clear();
                        com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = com.egeetouch.egeetouch_manager.BLEService.Request_tag_AuditTrial;
                        return;
                    }
                    return;
                case com.egeetouch.egeetouch_manager.BLEService.Read_response_tag_AuditTrail /* 231 */:
                    if (r1[0] == -95) {
                        if (com.egeetouch.egeetouch_manager.BLEService.Tag_AuditList_count < com.egeetouch.egeetouch_manager.BLEService.Total_Tag_Audit && r1[1] != 15) {
                            long r2 = 0;
                            if (r1[5] != 0) {
                                byte[] r2 = new byte[4];
                                for (int r3 = 0; r3 < 4; r3++) {
                                    r2[r3] = r1[r3 + 5];
                                }
                                long r3 = 0;
                                for (int r5 = 0; r5 < 4; r5++) {
                                    r3 = (r3 << 8) + (r2[r5] & kotlin.UByte.MAX_VALUE);
                                }
                                new java.text.SimpleDateFormat("dd/MM/yyyy hh:mm a").format(java.lang.Long.valueOf(1000 * r3));
                                r2 = r3;
                            }
                            java.lang.String r4 = convertTagID_toHexa(r1[3], r1[4]);
                            java.lang.String r0 = "Tag ID: " + r4.toUpperCase();
                            new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(java.util.Calendar.getInstance().getTime());
                            com.egeetouch.egeetouch_manager.BLEService.listAuditTrailTagIDIP45.add(r4.toUpperCase());
                            com.egeetouch.egeetouch_manager.BLEService.listAuditTrailTagTimeIP45.add(java.lang.Long.valueOf(r2));
                            com.egeetouch.egeetouch_manager.BLEService.listAuditTagTypeIP45.add(0);
                            long r3 = com.egeetouch.egeetouch_manager.BLEService.Tag_Audit_count;
                            if ((r1[2] & kotlin.UByte.MAX_VALUE) == r3) {
                                if (r3 > 0) {
                                    com.egeetouch.egeetouch_manager.BLEService.Tag_Audit_count = r3 - 1;
                                } else if (r3 == 0) {
                                    r2 = 1;
                                    com.egeetouch.egeetouch_manager.BLEService.Tag_Audit_count = com.egeetouch.egeetouch_manager.BLEService.AuditTrail_Capacity - 1;
                                    com.egeetouch.egeetouch_manager.BLEService.Tag_AuditList_count += r2;
                                    com.egeetouch.egeetouch_manager.UI_BLE.pls_wait_content = com.egeetouch.egeetouch_manager.MainActivity.context.getResources().getString(com.egeetouch.egeetouch_manager.R.string.retrieving_data_from_lock);
                                    com.egeetouch.egeetouch_manager.UI_BLE.progress_val = (int) ((com.egeetouch.egeetouch_manager.BLEService.Tag_AuditList_count * 100) / com.egeetouch.egeetouch_manager.BLEService.Total_Tag_Audit);
                                    com.egeetouch.egeetouch_manager.UI_BLE.BLE_UI = 24;
                                }
                                r2 = 1;
                                com.egeetouch.egeetouch_manager.BLEService.Tag_AuditList_count += r2;
                                com.egeetouch.egeetouch_manager.UI_BLE.pls_wait_content = com.egeetouch.egeetouch_manager.MainActivity.context.getResources().getString(com.egeetouch.egeetouch_manager.R.string.retrieving_data_from_lock);
                                com.egeetouch.egeetouch_manager.UI_BLE.progress_val = (int) ((com.egeetouch.egeetouch_manager.BLEService.Tag_AuditList_count * 100) / com.egeetouch.egeetouch_manager.BLEService.Total_Tag_Audit);
                                com.egeetouch.egeetouch_manager.UI_BLE.BLE_UI = 24;
                            }
                            com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = com.egeetouch.egeetouch_manager.BLEService.Request_tag_AuditTrial;
                            return;
                        } else if (com.egeetouch.egeetouch_manager.Helper_Network.haveNetworkConnection(com.egeetouch.egeetouch_manager.MainActivity.context)) {
                            com.egeetouch.egeetouch_manager.UI_BLE.pls_wait_content = "Backing up Audit Trail...";
                            com.egeetouch.egeetouch_manager.Firebase_Data_management.sendingTagAuditTrailToFirebase(com.egeetouch.egeetouch_manager.BLEService.listAuditTrailTagIDIP45, com.egeetouch.egeetouch_manager.BLEService.listAuditTrailTagTimeIP45, com.egeetouch.egeetouch_manager.MainActivity.user_uid, com.egeetouch.egeetouch_manager.MainActivity.email, com.egeetouch.egeetouch_manager.BLEService.parsedIp45SerialNumber, com.egeetouch.egeetouch_manager.BLEService.listAuditTagTypeIP45);
                            com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = com.egeetouch.egeetouch_manager.BLEService.Request_clear_tag_AuditTrail;
                            if (!com.egeetouch.egeetouch_manager.BLEService.isTagAuditTrail_updateOnly) {
                                com.egeetouch.egeetouch_manager.UI_BLE.BLE_UI = 30;
                                return;
                            }
                            com.egeetouch.egeetouch_manager.BLEService.isTagAuditTrail_updateOnly = false;
                            com.egeetouch.egeetouch_manager.UI_BLE.BLE_UI = 32;
                            return;
                        } else {
                            com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 0;
                            com.egeetouch.egeetouch_manager.UI_BLE.BLE_UI = 30;
                            return;
                        }
                    }
                    com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 0;
                    com.egeetouch.egeetouch_manager.UI_BLE.BLE_UI = 21;
                    return;
                case com.egeetouch.egeetouch_manager.BLEService.ReadResponse_scanTagId /* 236 */:
                    java.lang.System.out.println("Hello checking the ScanTagId  Response:" + java.util.Arrays.toString(r1));
                    java.lang.String r2 = " " + ((int) r1[2]) + " " + ((int) r1[3]) + " ";
                    if (r1[1] != 1) {
                        com.egeetouch.egeetouch_manager.BLEService.Detected_tag_id = "Tag is not detected.";
                    } else if (r2 != "") {
                        com.egeetouch.egeetouch_manager.BLEService.Detected_tag_id = "Detected tag id is" + convertTagID_toHexa(r1[2], r1[3]);
                    }
                    com.egeetouch.egeetouch_manager.UI_BLE.BLE_UI = 26;
                    com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 0;
                    return;
                case com.egeetouch.egeetouch_manager.BLEService.ReadResponse_Master_Tag /* 242 */:
                    com.egeetouch.egeetouch_manager.BLEService.Detected_tag_id = "Your Master Tag Id for this lock is " + convertTagID_toHexa(r1[1], r1[2]);
                    com.egeetouch.egeetouch_manager.BLEService.tagID = convertTagID_toHexa(r1[1], r1[2]);
                    java.lang.System.out.println("checking parsed" + com.egeetouch.egeetouch_manager.BLEService.parsedIp45SerialNumber);
                    final long r1 = java.util.concurrent.TimeUnit.MILLISECONDS.toSeconds(java.lang.System.currentTimeMillis() * 1000);
                    if (com.egeetouch.egeetouch_manager.Fragment_lock_setting.initial_setup) {
                        final com.google.firebase.database.DatabaseReference r3 = com.google.firebase.database.FirebaseDatabase.getInstance().getReference("tagDirectory").child(com.egeetouch.egeetouch_manager.BLEService.parsedIp45SerialNumber);
                        r3.addListenerForSingleValueEvent(new com.google.firebase.database.ValueEventListener() { // from class: com.egeetouch.egeetouch_manager.BLEService.14
                            @Override // com.google.firebase.database.ValueEventListener
                            public void onCancelled(com.google.firebase.database.DatabaseError r1) {
                            }

                            {
                            }

                            @Override // com.google.firebase.database.ValueEventListener
                            public void onDataChange(com.google.firebase.database.DataSnapshot r4) {
                                if (r4.exists()) {
                                    return;
                                }
                                com.google.firebase.database.DatabaseReference r4 = r2.push();
                                java.util.HashMap r0 = new java.util.HashMap();
                                r0.put("PageNumber", 0);
                                r0.put("tagId", com.egeetouch.egeetouch_manager.BLEService.tagID);
                                r0.put("tagName", "Tag1");
                                r0.put("timeAdded", java.lang.Long.valueOf(r3));
                                r2.child(r4.getKey()).setValue(r0);
                            }
                        });
                        com.egeetouch.egeetouch_manager.UI_BLE.BLE_UI = 5;
                    } else {
                        com.egeetouch.egeetouch_manager.UI_BLE.BLE_UI = 26;
                    }
                    com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 0;
                    return;
                case com.egeetouch.egeetouch_manager.BLEService.Read_response_lotoBatteryRecords /* 244 */:
                    java.lang.System.out.println("Hello checking the battery Records " + (r1[0] & kotlin.UByte.MAX_VALUE) + " data: " + java.util.Arrays.toString(r1));
                    int r2 = r1[1] & kotlin.UByte.MAX_VALUE;
                    int r8 = r1[2] & kotlin.UByte.MAX_VALUE;
                    int r10 = 4;
                    int r9 = 0;
                    for (int r3 = 2; r10 > r3; r3 = 2) {
                        r9 = (r1[r10] & kotlin.UByte.MAX_VALUE) + (r9 << 8);
                        r10--;
                    }
                    int r3 = 0;
                    while (r4 > 4) {
                        r3 = (r3 << 8) + (r1[r4] & kotlin.UByte.MAX_VALUE);
                        r4--;
                    }
                    if (r2 == 5) {
                        com.egeetouch.egeetouch_manager.BLEService.battery_level = 100;
                    } else if (r2 == 4) {
                        com.egeetouch.egeetouch_manager.BLEService.battery_level = 75;
                    } else if (r2 == 3) {
                        com.egeetouch.egeetouch_manager.BLEService.battery_level = 50;
                    } else if (r2 == 2) {
                        com.egeetouch.egeetouch_manager.BLEService.battery_level = 25;
                    } else if (r2 == 1) {
                        com.egeetouch.egeetouch_manager.BLEService.battery_level = 10;
                    } else if (r2 == 0) {
                        com.egeetouch.egeetouch_manager.BLEService.battery_level = 0;
                    }
                    java.lang.System.out.println("Hello checking the battery Rec : battery : " + r2 + " chargeTime: " + r8 + " on time: " + r9 + " motor count: " + r3);
                    com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 0;
                    return;
                case com.egeetouch.egeetouch_manager.BLEService.ReadResponse_ChangePasscode /* 245 */:
                    java.lang.System.out.println("Hello checking the Passcode Add Reply !" + (r1[0] & kotlin.UByte.MAX_VALUE) + " data: " + java.util.Arrays.toString(r1));
                    if ((r1[0] & kotlin.UByte.MAX_VALUE) == 173) {
                        com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 0;
                        com.egeetouch.egeetouch_manager.Firebase_Data_management.sendDirectionCodeToServer(com.egeetouch.egeetouch_manager.BLEService.parsedIp45SerialNumber, com.egeetouch.egeetouch_manager.BLEService.StrPasscodeEmoji, com.egeetouch.egeetouch_manager.BLEService.new_passcode, com.egeetouch.egeetouch_manager.BLEService.new_passcodeName, r20.lotoInfo.nextAvailabeIndex);
                        com.egeetouch.egeetouch_manager.UI_BLE.BLE_UI = 28;
                        java.lang.System.out.println("Hello checking the Passcode Success1 !!");
                        return;
                    } else if ((r1[0] & kotlin.UByte.MAX_VALUE) == 222) {
                        if (r20.lotoInfo.isDeleteAdd_mode()) {
                            com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = com.egeetouch.egeetouch_manager.BLEService.Request_ChangePasscode;
                            com.egeetouch.egeetouch_manager.LotoInfo r1 = r20.lotoInfo;
                            r1.setUpdateMode(r1.ADD_PASSCODE);
                            return;
                        }
                        com.egeetouch.egeetouch_manager.Firebase_Data_management.deletePasscode(com.egeetouch.egeetouch_manager.BLEService.parsedIp45SerialNumber, r20.lotoInfo.deleteIndex);
                        com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 0;
                        return;
                    } else if ((r1[0] & kotlin.UByte.MAX_VALUE) == 15) {
                        com.egeetouch.egeetouch_manager.UI_BLE.BLE_UI = 29;
                        com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 0;
                        return;
                    } else if (r20.lotoInfo.getUpdateMode() == r20.lotoInfo.ADD_PASSCODE) {
                        r20.lotoInfo.setDeleteAdd_mode(true);
                        com.egeetouch.egeetouch_manager.LotoInfo r1 = r20.lotoInfo;
                        r1.setUpdateMode(r1.DELETE_PASSCODE);
                        com.egeetouch.egeetouch_manager.LotoInfo r1 = r20.lotoInfo;
                        r1.deleteIndex = r1.nextAvailabeIndex;
                        com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = com.egeetouch.egeetouch_manager.BLEService.Request_ChangePasscode;
                        return;
                    } else if (r20.lotoInfo.getUpdateMode() != r20.lotoInfo.DELETE_PASSCODE || r20.lotoInfo.isDeleteAdd_mode()) {
                        return;
                    } else {
                        com.egeetouch.egeetouch_manager.Firebase_Data_management.deletePasscode(com.egeetouch.egeetouch_manager.BLEService.parsedIp45SerialNumber, r20.lotoInfo.deleteIndex);
                        com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 0;
                        return;
                    }
                case 250:
                    java.lang.System.out.println("Hello checking the Passcode value Index: " + com.egeetouch.egeetouch_manager.BLEService.PasscodeIndexCount + " array:" + java.util.Arrays.toString(r1));
                    if ((r1[0] & kotlin.UByte.MAX_VALUE) == 100 && (r1[1] & kotlin.UByte.MAX_VALUE) == 192) {
                        byte[] r9 = {r1[3], r1[4]};
                        byte[] r10 = {r1[5], r1[6]};
                        byte[] r4 = {r1[7], r1[8]};
                        byte[] r11 = {r1[9], r1[10]};
                        byte[] r12 = {r1[11], r1[12]};
                        int[] r2 = {0, 0, 0, 0, 0};
                        for (int r3 = 1; r3 >= 0; r3--) {
                            byte r8 = r9[r3];
                            byte r8 = r10[r3];
                            byte r8 = r4[r3];
                            byte r8 = r11[r3];
                            byte r8 = r12[r3];
                            r2[0] = (r2[0] << 8) + (r9[r3] & kotlin.UByte.MAX_VALUE);
                            r2[1] = (r2[1] << 8) + (r10[r3] & kotlin.UByte.MAX_VALUE);
                            r2[2] = (r2[2] << 8) + (r4[r3] & kotlin.UByte.MAX_VALUE);
                            r2[3] = (r2[3] << 8) + (r11[r3] & kotlin.UByte.MAX_VALUE);
                            r2[4] = (r2[4] << 8) + (r12[r3] & kotlin.UByte.MAX_VALUE);
                        }
                        byte r1 = r1[2];
                        for (int r3 = 0; r3 < 5; r3++) {
                            r20.lotoInfo.addLockPasscodeToList(r1 + r3, r2[r3]);
                        }
                        int r4 = r1 + 4;
                        float r8 = r4 * 1.25f;
                        java.lang.System.out.println("Hello checking the percentage : " + java.lang.Math.floor(r10) + "  " + r8);
                        com.egeetouch.egeetouch_manager.UI_BLE.pls_wait_content = com.egeetouch.egeetouch_manager.MainActivity.context.getResources().getString(com.egeetouch.egeetouch_manager.R.string.sync_passcode);
                        com.egeetouch.egeetouch_manager.UI_BLE.progress_val = (int) java.lang.Math.floor(r8);
                        com.egeetouch.egeetouch_manager.UI_BLE.BLE_UI = 24;
                        java.lang.System.out.println("Hello checking the Passcode value decimal : " + ((int) r1) + ":" + r2[0] + " ," + (r1 + 1) + ":" + r2[1] + " , " + (r1 + 2) + ":" + r2[2] + " , " + (r1 + 3) + ":" + r2[3] + " , " + r4 + ":" + r2[4]);
                    }
                    int r1 = com.egeetouch.egeetouch_manager.BLEService.PasscodeIndexCount;
                    if (r1 <= 94) {
                        com.egeetouch.egeetouch_manager.BLEService.PasscodeIndexCount = r1 + 5;
                        com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = com.egeetouch.egeetouch_manager.BLEService.Request_passcodeValue;
                        return;
                    }
                    com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 0;
                    com.egeetouch.egeetouch_manager.BLEService.PasscodeIndexCount = 0;
                    java.lang.System.out.println("Passcode value--: " + r20.lotoInfo.getBackupPasscodeList());
                    java.lang.System.out.println("Passcode value-:" + r20.lotoInfo.getLockPasscodeList());
                    checkSyncPasscode();
                    return;
                case com.egeetouch.egeetouch_manager.BLEService.ReadResponse_passcodeAudit /* 252 */:
                    java.lang.System.out.println("Hello checking the Passcode Audit Index: " + com.egeetouch.egeetouch_manager.BLEService.PasscodeAuditCount + " array:" + java.util.Arrays.toString(r1));
                    int r2 = com.egeetouch.egeetouch_manager.BLEService.AvailableAuditCount;
                    java.lang.System.out.println("Hello checking the Passcode count:" + com.egeetouch.egeetouch_manager.BLEService.PasscodeAuditCount + " total:" + r2 + " data[1]:" + (r1[1] & kotlin.UByte.MAX_VALUE) + " index:" + com.egeetouch.egeetouch_manager.BLEService.PasscodeAuditIndex);
                    if (r1[0] == 101) {
                        if (com.egeetouch.egeetouch_manager.BLEService.PasscodeAuditCount < r2 && r1[1] == -83) {
                            byte r3 = r1[4];
                            byte r3 = r1[5];
                            int r3 = r1[5] & kotlin.UByte.MAX_VALUE;
                            byte[] r4 = new byte[4];
                            for (int r6 = 0; r6 < 4; r6++) {
                                r4[r6] = r1[r6 + 6];
                            }
                            long r8 = r1[4] & kotlin.UByte.MAX_VALUE;
                            if (r8 > 1000) {
                                com.egeetouch.egeetouch_manager.BLEService.lastUpdatedAuditTimestamp = r8;
                            }
                            java.lang.String r4 = "Passcode : ";
                            byte[] r6 = new byte[4];
                            for (int r11 = 0; r11 < 4; r11++) {
                                r6[r11] = r1[r11 + 6];
                            }
                            long r11 = 0;
                            for (int r13 = 0; r13 < 4; r13++) {
                                r11 = (r11 << 8) + (r6[r13] & kotlin.UByte.MAX_VALUE);
                            }
                            int r6 = 0;
                            for (int r5 = 5; r5 >= 4; r5--) {
                                r6 = (r6 << 8) + (r1[r5] & kotlin.UByte.MAX_VALUE);
                            }
                            if (r3 == 254) {
                                java.lang.System.out.println("Hello checking the Audit Update (lock by shackle) auditType: " + r3 + " time : " + r8 + " TS: " + r11 + " count:" + com.egeetouch.egeetouch_manager.BLEService.PasscodeAuditCount + " F.ind:" + (r1[2] & kotlin.UByte.MAX_VALUE) + " A.ind:" + com.egeetouch.egeetouch_manager.BLEService.PasscodeAuditIndex + " Deci:" + r6);
                            } else if (r3 == 253) {
                                java.lang.System.out.println("Hello checking the Audit Update (lock by button) auditType" + r3 + " time : " + r8 + " TS: " + r11 + " count:" + com.egeetouch.egeetouch_manager.BLEService.PasscodeAuditCount + " F.ind:" + (r1[2] & kotlin.UByte.MAX_VALUE) + " A.ind:" + com.egeetouch.egeetouch_manager.BLEService.PasscodeAuditIndex + " Deci:" + r6);
                            } else if (r3 == 252) {
                                java.lang.System.out.println("Hello checking the Audit Update (lock by power up) auditType" + r3 + " time : " + r8 + " TS: " + r11 + " count:" + com.egeetouch.egeetouch_manager.BLEService.PasscodeAuditCount + " F.ind:" + (r1[2] & kotlin.UByte.MAX_VALUE) + " A.ind:" + com.egeetouch.egeetouch_manager.BLEService.PasscodeAuditIndex + " Deci:" + r6);
                            } else if (r3 == 251) {
                                java.lang.System.out.println("Hello checking the Audit Update (lock by power off) auditType" + r3 + " time : " + r8 + " TS: " + r11 + " count:" + com.egeetouch.egeetouch_manager.BLEService.PasscodeAuditCount + " F.ind:" + (r1[2] & kotlin.UByte.MAX_VALUE) + " A.ind:" + com.egeetouch.egeetouch_manager.BLEService.PasscodeAuditIndex + " Deci:" + r6);
                            } else if (r3 == 250) {
                                java.lang.System.out.println("Hello checking the Audit Update (lock by phone) auditType: " + r3 + " time : " + r8 + " TS: " + r11 + " count:" + com.egeetouch.egeetouch_manager.BLEService.PasscodeAuditCount + " F.ind:" + (r1[2] & kotlin.UByte.MAX_VALUE) + " A.ind:" + com.egeetouch.egeetouch_manager.BLEService.PasscodeAuditIndex + " Deci:" + r6);
                            } else if (r3 == 249) {
                                java.lang.System.out.println("Hello checking the Audit Update (locked fail before power off) auditType: " + r3 + " time : " + r8 + " TS: " + r11 + " count:" + com.egeetouch.egeetouch_manager.BLEService.PasscodeAuditCount + " F.ind:" + (r1[2] & kotlin.UByte.MAX_VALUE) + " A.ind:" + com.egeetouch.egeetouch_manager.BLEService.PasscodeAuditIndex + " Deci:" + r6);
                            } else {
                                java.lang.System.out.println("Hello checking the Audit Update (lock by NONE) auditType: " + r3 + " time : " + r8 + " TS: " + r11 + " count:" + com.egeetouch.egeetouch_manager.BLEService.PasscodeAuditCount + " F.ind:" + (r1[2] & kotlin.UByte.MAX_VALUE) + " A.ind:" + com.egeetouch.egeetouch_manager.BLEService.PasscodeAuditIndex + " Deci:" + r6);
                            }
                            java.lang.String r5 = new java.text.SimpleDateFormat("dd/MM/yyyy hh:mm a").format(java.lang.Long.valueOf(1000 * r11));
                            java.lang.System.out.println("Hello checking the time stamp :" + r11);
                            java.lang.System.out.println("Hello checking the pass value count :" + com.egeetouch.egeetouch_manager.BLEService.PasscodeAuditIndex + " deci: " + r6 + " pas:" + convertDeciToPasscode(r6));
                            java.lang.String r8 = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(java.util.Calendar.getInstance().getTime());
                            int r9 = com.egeetouch.egeetouch_manager.BLEService.PasscodeAuditCount + 1;
                            java.lang.System.out.println("Hello checking the Audit passcode DECI :" + r6 + " index: " + com.egeetouch.egeetouch_manager.BLEService.PasscodeAuditIndex);
                            if ((r1[2] & kotlin.UByte.MAX_VALUE) == com.egeetouch.egeetouch_manager.BLEService.PasscodeAuditIndex) {
                                if ((r6 > 0 && r6 <= 4096) || r3 == 254 || r3 == 253 || r3 == 252 || r3 == 251 || r3 == 250 || r3 == 249) {
                                    com.egeetouch.egeetouch_manager.BLEService.listPasscodeIndex.add(java.lang.Integer.valueOf(r1[4] & kotlin.UByte.MAX_VALUE));
                                    java.lang.System.out.println("Hello checking the pass value in if ");
                                    if (r6 > 0 && r6 <= 4096) {
                                        com.egeetouch.egeetouch_manager.BLEService.listAuditPassCode.add(convertDeciToPasscode(r6));
                                        r1 = convertDeciToPasscode(r6);
                                        r4 = "Passcode : " + convertToEmoji(r1);
                                        com.egeetouch.egeetouch_manager.BLEService.listAuditTrailTime.add(java.lang.Long.valueOf(r11));
                                        com.egeetouch.egeetouch_manager.BLEService.listAuditLockedbyTime.add(0L);
                                        com.egeetouch.egeetouch_manager.BLEService.listAuditDeciValue.add(java.lang.Integer.valueOf(r6));
                                    } else {
                                        long r6 = r1[4] & kotlin.UByte.MAX_VALUE;
                                        if (r3 == 254) {
                                            r10 = " Locked by Shackle";
                                        } else if (r3 == 253) {
                                            r10 = " Locked by button";
                                        } else if (r3 == 252) {
                                            r10 = " Locked by power up";
                                        } else if (r3 == 251) {
                                            r10 = " Locked by power off";
                                        } else if (r3 == 250) {
                                            r10 = " Locked by phone";
                                        } else if (r3 == 249) {
                                            r10 = " Locked fail ";
                                        }
                                        com.egeetouch.egeetouch_manager.BLEService.listAuditPassCode.add(r10);
                                        com.egeetouch.egeetouch_manager.BLEService.listAuditTrailTime.add(java.lang.Long.valueOf(r11));
                                        com.egeetouch.egeetouch_manager.BLEService.listAuditLockedbyTime.add(java.lang.Long.valueOf(r6));
                                        com.egeetouch.egeetouch_manager.BLEService.listAuditDeciValue.add(java.lang.Integer.valueOf(r3));
                                        r1 = com.facebook.appevents.AppEventsConstants.EVENT_PARAM_VALUE_NO;
                                    }
                                    matchPasscodeName(r1, r11, com.egeetouch.egeetouch_manager.BLEService.selectedLockIP45Serial);
                                    int r3 = com.egeetouch.egeetouch_manager.BLEService.PasscodeAuditIndex;
                                    if (r3 > 0) {
                                        r6 = 1;
                                        com.egeetouch.egeetouch_manager.BLEService.PasscodeAuditIndex = r3 - 1;
                                    } else {
                                        r6 = 1;
                                        if (r3 == 0) {
                                            com.egeetouch.egeetouch_manager.BLEService.PasscodeAuditIndex = 119;
                                        }
                                    }
                                    com.egeetouch.egeetouch_manager.BLEService.PasscodeAuditCount += r6;
                                    java.lang.System.out.println("Hello checking the Passcode Na value count passcode: " + r1);
                                    com.egeetouch.egeetouch_manager.DatabaseVariable.db_audittrail.execSQL(com.egeetouch.egeetouch_manager.DatabaseVariable.db_audittrail_update_value_bt_1(r9, r5, java.lang.Double.valueOf((double) com.google.firebase.remoteconfig.FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE), r8, com.egeetouch.egeetouch_manager.BLEService.selected_lock_name));
                                    com.egeetouch.egeetouch_manager.DatabaseVariable.db_audittrail.execSQL(com.egeetouch.egeetouch_manager.DatabaseVariable.db_audittrail_update_value_bt_2(r9, r4, java.lang.Double.valueOf((double) com.google.firebase.remoteconfig.FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE), com.egeetouch.egeetouch_manager.BLEService.selected_lock_name));
                                } else {
                                    java.lang.System.out.println("Hello checking the pass value count else :" + com.egeetouch.egeetouch_manager.BLEService.PasscodeAuditIndex + " deci: " + r6 + " pas:" + convertDeciToPasscode(r6));
                                    java.lang.System.out.println("Hello checking the Passcode Audit Emoji : Passcode : ");
                                    int r1 = com.egeetouch.egeetouch_manager.BLEService.PasscodeAuditIndex;
                                    if (r1 > 0) {
                                        com.egeetouch.egeetouch_manager.BLEService.PasscodeAuditIndex = r1 - 1;
                                    } else if (r1 == 0) {
                                        com.egeetouch.egeetouch_manager.BLEService.PasscodeAuditIndex = 119;
                                    }
                                }
                                java.lang.System.out.println("Hello checking the index : " + com.egeetouch.egeetouch_manager.BLEService.PasscodeAuditIndex + " count : " + com.egeetouch.egeetouch_manager.BLEService.PasscodeAuditCount);
                                com.egeetouch.egeetouch_manager.UI_BLE.pls_wait_content = com.egeetouch.egeetouch_manager.MainActivity.context.getResources().getString(com.egeetouch.egeetouch_manager.R.string.retrieving_data_from_lock);
                                com.egeetouch.egeetouch_manager.UI_BLE.progress_val = (com.egeetouch.egeetouch_manager.BLEService.PasscodeAuditCount * 100) / r2;
                                com.egeetouch.egeetouch_manager.UI_BLE.BLE_UI = 24;
                                com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = com.egeetouch.egeetouch_manager.BLEService.Request_passcodeAudit;
                                return;
                            }
                            return;
                        }
                        if (com.egeetouch.egeetouch_manager.Helper_Network.haveNetworkConnection(com.egeetouch.egeetouch_manager.MainActivity.context)) {
                            com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 0;
                            com.egeetouch.egeetouch_manager.Firebase_Data_management.sendingPasscodeAuditTrailToFirebase(com.egeetouch.egeetouch_manager.BLEService.listPasscodeIndex, com.egeetouch.egeetouch_manager.BLEService.listAuditPassCode, com.egeetouch.egeetouch_manager.BLEService.listAuditTrailTime, com.egeetouch.egeetouch_manager.BLEService.listAuditDeciValue, com.egeetouch.egeetouch_manager.BLEService.listAuditLockedbyTime, com.egeetouch.egeetouch_manager.MainActivity.user_uid, com.egeetouch.egeetouch_manager.MainActivity.email, com.egeetouch.egeetouch_manager.BLEService.parsedIp45SerialNumber);
                            com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = com.egeetouch.egeetouch_manager.BLEService.Request_clear_passcodeAuditTrail;
                        } else {
                            com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 0;
                        }
                        com.egeetouch.egeetouch_manager.UI_BLE.BLE_UI = 30;
                        return;
                    }
                    return;
                case com.egeetouch.egeetouch_manager.BLEService.ReadResponse_clear_passcodeAuditTrail /* 254 */:
                    java.lang.System.out.println("Hello checking the Passcode clear Audit Reply array:" + java.util.Arrays.toString(r1));
                    com.egeetouch.egeetouch_manager.BLEService.AvailableAuditCount = 0;
                    com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 0;
                    return;
                case com.egeetouch.egeetouch_manager.BLEService.Read_Response_lotoEnable_auditTrail /* 266 */:
                    if (r20.lotoInfo.isNewLock()) {
                        com.egeetouch.egeetouch_manager.Firebase_Data_management.sendDirectionCodeToServer(com.egeetouch.egeetouch_manager.BLEService.parsedIp45SerialNumber, convertToEmoji("111111"), "111111", "Passcode 1", 0);
                    }
                    r20.lotoInfo.setNewLock(false);
                    com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 0;
                    return;
                case com.egeetouch.egeetouch_manager.BLEService.coresponse_MasterTag /* 665 */:
                    java.lang.System.out.println("CHECKING BLE MODE DATA 1 DATA 2 " + ((int) r1[1]) + "  " + ((int) r1[2]));
                    java.lang.String r1 = convertTagID_toHexa(r1[1], r1[2]);
                    com.egeetouch.egeetouch_manager.BLEService.tagID = r1;
                    java.lang.String r1 = r1.trim();
                    com.egeetouch.egeetouch_manager.BLEService.tagID = r1;
                    com.egeetouch.egeetouch_manager.BLEService.tagID = r1.replace(" ", "");
                    java.lang.String r1 = com.egeetouch.egeetouch_manager.BLEService.parsedIp45SerialNumber;
                    if (r1 == null || r1 == "") {
                        java.lang.System.out.println("printkan 2");
                    } else {
                        com.egeetouch.egeetouch_manager.Firebase_Data_management.database = com.google.firebase.database.FirebaseDatabase.getInstance();
                        com.google.firebase.database.DatabaseReference r1 = com.egeetouch.egeetouch_manager.Firebase_Data_management.database.getReference("masterTagID");
                        java.lang.System.out.println("CHECKING BLE MODE" + r1);
                        java.util.HashMap r2 = new java.util.HashMap();
                        r2.put("tagName", "Tag1");
                        r2.put("tagID", com.egeetouch.egeetouch_manager.BLEService.tagID);
                        r2.put("timeStamps", java.lang.Long.valueOf(java.lang.System.currentTimeMillis() / 1000));
                        java.lang.System.out.println("Checking time stamps : " + com.egeetouch.egeetouch_manager.MainActivity.currentTimestampDouble);
                        r1.child(com.egeetouch.egeetouch_manager.BLEService.parsedIp45SerialNumber).child(com.egeetouch.egeetouch_manager.MainActivity.user_uid).updateChildren(r2);
                    }
                    com.egeetouch.egeetouch_manager.BLEService.Ble_Mode = 0;
                    com.egeetouch.egeetouch_manager.BLEService.tagID = "";
                    return;
                default:
                    return;
            }
        }
    }

    public void readCharacteristic(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        BluetoothGatt bluetoothGatt;
        if (this.mBluetoothAdapter == null || (bluetoothGatt = this.mBluetoothGatt) == null) {
            Log.w("ContentValues", "BluetoothAdapter not initialized");
        } else {
            bluetoothGatt.readCharacteristic(bluetoothGattCharacteristic);
        }
    }

    public void setCharacteristicNotification(BluetoothGattCharacteristic bluetoothGattCharacteristic, boolean z) {
        BluetoothGatt bluetoothGatt;
        if (this.mBluetoothAdapter == null || (bluetoothGatt = this.mBluetoothGatt) == null) {
            Log.w("ContentValues", "BluetoothAdapter not initialized");
            return;
        }
        bluetoothGatt.setCharacteristicNotification(bluetoothGattCharacteristic, z);
        if (bluetoothGattCharacteristic.equals(this.characteristic_2)) {
            BluetoothGattDescriptor descriptor2 = bluetoothGattCharacteristic.getDescriptor(UUID_descriptor);
            descriptor = descriptor2;
            descriptor2.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
        } else if (bluetoothGattCharacteristic.equals(this.characteristic_battery)) {
            BluetoothGattDescriptor descriptor3 = bluetoothGattCharacteristic.getDescriptor(UUID_descriptor);
            descriptor = descriptor3;
            descriptor3.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
        } else if (bluetoothGattCharacteristic.equals(this.characteristic_5)) {
            BluetoothGattDescriptor descriptor4 = bluetoothGattCharacteristic.getDescriptor(UUID_descriptor);
            descriptor = descriptor4;
            descriptor4.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
        }
        this.mBluetoothGatt.writeDescriptor(descriptor);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int averaging_rssi_value(int i) {
        if (Fragment_BLE.sensitivity_low) {
            int i2 = 0;
            while (i2 < 8) {
                int[] iArr = average_rssi;
                int i3 = i2 + 1;
                iArr[i2] = iArr[i3];
                i2 = i3;
            }
            int[] iArr2 = average_rssi;
            iArr2[8] = i;
            return ((((((((iArr2[0] + iArr2[1]) + iArr2[2]) + iArr2[3]) + iArr2[4]) + iArr2[5]) + iArr2[6]) + iArr2[7]) + iArr2[8]) / 9;
        }
        int i4 = 0;
        while (i4 < 3) {
            int[] iArr3 = average_rssi;
            int i5 = i4 + 1;
            iArr3[i4] = iArr3[i5];
            i4 = i5;
        }
        int[] iArr4 = average_rssi;
        iArr4[3] = i;
        return (((iArr4[0] + iArr4[1]) + iArr4[2]) + iArr4[3]) / 4;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isAppIsInBackground(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        boolean z = true;
        if (Build.VERSION.SDK_INT <= 20) {
            return !activityManager.getRunningTasks(1).get(0).topActivity.getPackageName().equals(context.getPackageName());
        }
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : activityManager.getRunningAppProcesses()) {
            if (runningAppProcessInfo.importance == 100) {
                for (String str : runningAppProcessInfo.pkgList) {
                    if (str.equals(context.getPackageName())) {
                        z = false;
                    }
                }
            }
        }
        return z;
    }

    public boolean isScreenOn(Context context) {
        if (Build.VERSION.SDK_INT >= 20) {
            boolean z = false;
            for (Display display : ((DisplayManager) context.getSystemService(ServerProtocol.DIALOG_PARAM_DISPLAY)).getDisplays()) {
                if (display.getState() != 1) {
                    z = true;
                }
            }
            return z;
        }
        return ((PowerManager) context.getSystemService("power")).isScreenOn();
    }

    private static String round_2_digit(Byte b) {
        if (b.byteValue() < 10) {
            return AppEventsConstants.EVENT_PARAM_VALUE_NO + String.valueOf(b);
        }
        return String.valueOf(b);
    }

    public static String bytesToHex(byte[] bArr) {
        char[] cArr = new char[bArr.length * 2];
        for (int i = 0; i < bArr.length; i++) {
            int i2 = bArr[i] & UByte.MAX_VALUE;
            int i3 = i * 2;
            char[] cArr2 = hexArray;
            cArr[i3] = cArr2[i2 >>> 4];
            cArr[i3 + 1] = cArr2[i2 & 15];
        }
        return new String(cArr);
    }

    public static String convertTagID_toHexa(byte b, byte b2) {
        String hexString;
        String hexString2;
        String str;
        if (b == 0 && b2 == 0) {
            str = " 00 00 ";
        } else {
            if (b < 0) {
                hexString = Integer.toHexString(b + UByte.MIN_VALUE);
            } else {
                hexString = Integer.toHexString(b);
            }
            if (b2 < 0) {
                hexString2 = Integer.toHexString(b2 + UByte.MIN_VALUE);
            } else {
                hexString2 = Integer.toHexString(b2);
            }
            if (hexString.length() == 1) {
                hexString = AppEventsConstants.EVENT_PARAM_VALUE_NO + hexString;
            }
            if (hexString2.length() == 1) {
                hexString2 = AppEventsConstants.EVENT_PARAM_VALUE_NO + hexString2;
            }
            str = " " + hexString + " " + hexString2 + " ";
        }
        return str.toUpperCase();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int convertPasscodeToDecimal(String str) {
        String str2 = "";
        for (int length = str.length() - 1; length >= 0; length--) {
            str2 = str2 + "" + (Integer.parseInt("" + str.charAt(length)) - 1);
        }
        String str3 = "";
        for (int i = 0; i < str2.length(); i++) {
            str3 = str3 + String.format("%2s", Integer.toBinaryString(Integer.parseInt("" + str2.charAt(i)))).replace(' ', '0');
        }
        return Integer.parseInt(str3, 2) + 1;
    }

    private String convertDeciToPasscode(int i) {
        String replace;
        String str = "";
        for (int length = String.format("%12s", Integer.toBinaryString(i - 1)).replace(' ', '0').length() - 1; length >= 0; length -= 2) {
            str = str + "" + Integer.parseInt(replace.charAt(length - 1) + "" + replace.charAt(length), 2);
        }
        String str2 = "";
        for (int i2 = 0; i2 < str.length(); i2++) {
            str2 = str2 + "" + (Integer.parseInt("" + str.charAt(i2)) + 1);
        }
        return str2;
    }

    private String convertToEmoji(String str) {
        String str2 = "";
        String str3 = "";
        for (int intValue = Integer.valueOf(str).intValue(); intValue != 0; intValue /= 10) {
            str3 = str3 + getEmojiByUnicode(intValue % 10);
        }
        for (int length = str3.length() - 1; length >= 0; length--) {
            str2 = str2 + str3.charAt(length);
        }
        return str2;
    }

    private String getEmojiByUnicode(int i) {
        return new String(Character.toChars(i != 1 ? i != 2 ? i != 3 ? i != 4 ? 0 : 10145 : 11015 : 11013 : 11014));
    }

    private void checkSyncPasscode() {
        new ArrayList();
        new ArrayList();
        this.syncPassCodeIndexList.clear();
        this.syncPassCodeValueList.clear();
        List<Integer> lockPasscodeList = this.lotoInfo.getLockPasscodeList();
        List<Integer> backupPasscodeList = this.lotoInfo.getBackupPasscodeList();
        this.lotoInfo.clearSyncPasscode();
        boolean z = false;
        for (int i = 0; i < 80; i++) {
            if (lockPasscodeList.get(i).intValue() != backupPasscodeList.get(i).intValue()) {
                System.out.println("Hello check comp lock:" + lockPasscodeList.get(i) + " backup:" + backupPasscodeList.get(i));
                this.lotoInfo.addSyncPasscode(i, backupPasscodeList.get(i).intValue());
                z = true;
            }
        }
        this.totalsyncPasscode = this.lotoInfo.syncPasscodeIndexList.size();
        this.syncPasscodeCount = 0;
        if (z) {
            LotoInfo lotoInfo = this.lotoInfo;
            lotoInfo.setUpdateMode(lotoInfo.ADD_PASSCODE);
            this.lotoInfo.setDeleteAdd_mode(false);
            Ble_Mode = 257;
        } else {
            UI_BLE.BLE_UI = 34;
        }
        System.out.println("Passcode value---:" + this.lotoInfo.getSyncPasscodeList());
    }

    private void matchPasscodeName(final String str, final long j, String str2) {
        System.out.println("Hello checking the data from fun pass: " + str + " time:" + j + " lock: " + str2);
        FirebaseDatabase commercialDBInstance = Firebase_Data_management.getCommercialDBInstance();
        if (!str.equals(AppEventsConstants.EVENT_PARAM_VALUE_NO)) {
            commercialDBInstance.getReference("directionalPassCodeDirectory").child(str2).orderByChild("directionalPasscode").equalTo(str).addListenerForSingleValueEvent(new ValueEventListener() { // from class: com.egeetouch.egeetouch_manager.BLEService.16
                @Override // com.google.firebase.database.ValueEventListener
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        Iterator<DataSnapshot> it = dataSnapshot.getChildren().iterator();
                        if (it.hasNext()) {
                            HashMap hashMap = (HashMap) it.next().getValue();
                            String obj = hashMap.containsKey("name") ? hashMap.get("name").toString() : "N.A";
                            if (((long) (hashMap.containsKey(ServerValues.NAME_OP_TIMESTAMP) ? Double.valueOf(hashMap.get(ServerValues.NAME_OP_TIMESTAMP).toString()).doubleValue() : FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE)) < j * 1000) {
                                System.out.println("Hello checking the Passcode Name :" + obj + " passcode:" + str);
                                BLEService.this.requestPasscodeAudit(obj, j);
                                return;
                            }
                            System.out.println("Hello checking the Passcode Name :" + obj + " passcode:" + str);
                            BLEService.this.requestPasscodeAudit(obj, j);
                            return;
                        }
                        return;
                    }
                    System.out.println("Hello checking the Passcode Name does't exist :" + str + "Name : N.A");
                    BLEService.this.requestPasscodeAudit("N.A", j);
                }

                @Override // com.google.firebase.database.ValueEventListener
                public void onCancelled(DatabaseError databaseError) {
                    BLEService.this.requestPasscodeAudit("N.A", j);
                }
            });
        } else {
            requestPasscodeAudit("N.A", j);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestPasscodeAudit(String str, long j) {
        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
        String str2 = "Passcode : " + str;
        new SimpleDateFormat("dd/MM/yyyy hh:mm a").format(Long.valueOf(j * 1000));
        UI_BLE.pls_wait_content = MainActivity.context.getResources().getString(R.string.retrieving_data_from_lock) + " " + PasscodeAuditCount + "/" + AvailableAuditCount;
        UI_BLE.pls_wait_content = MainActivity.context.getResources().getString(R.string.retrieving_data_from_lock);
        int i = AvailableAuditCount;
        if (i > 0) {
            UI_BLE.progress_val = (PasscodeAuditCount * 100) / i;
        } else {
            UI_BLE.progress_val = 0;
        }
        UI_BLE.BLE_UI = 24;
        Ble_Mode = Request_passcodeAudit;
    }

    private void send_IP66_audittrailToCloud(String str, int i, final long j, String str2) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseDatabase.getReference("tagAuditTrail").child(str2);
        firebaseDatabase.getReference("tagDirectory").child(str2).orderByChild("tagId").equalTo(str).addListenerForSingleValueEvent(new ValueEventListener() { // from class: com.egeetouch.egeetouch_manager.BLEService.18
            @Override // com.google.firebase.database.ValueEventListener
            public void onCancelled(DatabaseError databaseError) {
            }

            @Override // com.google.firebase.database.ValueEventListener
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot dataSnapshot2 : dataSnapshot.getChildren()) {
                        HashMap hashMap = (HashMap) dataSnapshot2.getValue();
                        if (hashMap.containsKey("tagName")) {
                            hashMap.get("tagName").toString();
                        }
                        if (hashMap.containsKey("timeAdded")) {
                            Long.parseLong(hashMap.get("timeAdded").toString());
                        }
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateIP66_auditTrail(long j, String str, int i) {
        listAuditTrailTagID.add(str);
        listAuditTrailTime.add(Long.valueOf(j));
        listAuditType.add(Integer.valueOf(i));
        int i2 = audittrail_count_IP66 + 1;
        audittrail_count_IP66 = i2;
        audit_current_index_IP66--;
        if (i2 < audittrail_max_count) {
            Ble_Mode = 505;
            return;
        }
        Firebase_Data_management.sendingTagAuditTrailToFirebase(listAuditTrailTagID, listAuditTrailTime, MainActivity.user_uid, MainActivity.email, parsedIp45SerialNumber, listAuditType);
        Ble_Mode = 506;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addTag_IP66(int i) {
        DatabaseReference child = FirebaseDatabase.getInstance().getReference("tagDirectory").child(parsedIp45SerialNumber);
        if (selected_lock_model.equals("GT2500") || selected_lock_model.equals("GT2550")) {
            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance(FirebaseApp.getInstance("industrial"));
            commercialDatabase = firebaseDatabase;
            child = firebaseDatabase.getReference("tagDirectory").child(parsedIp45SerialNumber);
        }
        DatabaseReference push = child.push();
        push.child("PageNumber").setValue(Integer.valueOf(tag_page_number));
        push.child("tagId").setValue(list_Add_tag_id_IP66.get(add_tag_count_IP66));
        push.child("tagName").setValue(list_Add_tag_name_IP66.get(add_tag_count_IP66));
        push.child("timeAdded").setValue(Double.valueOf(MainActivity.currentTimestampDouble));
        push.child("reply").setValue(Integer.valueOf(i));
        if (add_tag_count_IP66 + 1 < list_Add_tag_id_IP66.size()) {
            add_tag_count_IP66++;
            Ble_Mode = 500;
            return;
        }
        UI_BLE.BLE_UI = 9;
        this.mMainActivity.Retrive_Tag_from_FirebaseIP66();
        Ble_Mode = 504;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void deleteTag_IP66() {
        DatabaseReference child = FirebaseDatabase.getInstance().getReference("tagDirectory").child(parsedIp45SerialNumber);
        if (selected_lock_model.equals("GT2500") || selected_lock_model.equals("GT2550")) {
            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance(FirebaseApp.getInstance("industrial"));
            commercialDatabase = firebaseDatabase;
            child = firebaseDatabase.getReference("tagDirectory").child(parsedIp45SerialNumber);
        }
        child.addListenerForSingleValueEvent(new ValueEventListener() { // from class: com.egeetouch.egeetouch_manager.BLEService.19
            @Override // com.google.firebase.database.ValueEventListener
            public void onCancelled(DatabaseError databaseError) {
            }

            @Override // com.google.firebase.database.ValueEventListener
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot2 : dataSnapshot.getChildren()) {
                    dataSnapshot2.getRef().child("tagId").addListenerForSingleValueEvent(new ValueEventListener() { // from class: com.egeetouch.egeetouch_manager.BLEService.19.1
                        @Override // com.google.firebase.database.ValueEventListener
                        public void onCancelled(DatabaseError databaseError) {
                        }

                        @Override // com.google.firebase.database.ValueEventListener
                        public void onDataChange(DataSnapshot dataSnapshot3) {
                            if (dataSnapshot3.getValue().toString().equals(BLEService.deleteTagID_IP66)) {
                                Log.i("Tag", "Delete Tag from Cloud : " + dataSnapshot3.getRef().getKey().toString());
                                dataSnapshot3.getRef().getParent().removeValue();
                                UI_BLE.BLE_UI = 9;
                            }
                        }
                    });
                }
            }
        });
        System.out.println("Hello checking the Tag DELETE is SUCCESS");
        Ble_Mode = 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateMasterTag_toFB(final String str) {
        final long seconds = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() * 1000);
        if (Fragment_lock_setting.initial_setup) {
            DatabaseReference child = FirebaseDatabase.getInstance().getReference("tagDirectory").child(parsedIp45SerialNumber);
            if (selected_lock_model.equals("GT2500") || selected_lock_model.equals("GT2550")) {
                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance(FirebaseApp.getInstance("industrial"));
                commercialDatabase = firebaseDatabase;
                child = firebaseDatabase.getReference("tagDirectory").child(parsedIp45SerialNumber);
            }
            final DatabaseReference databaseReference = child;
            child.addListenerForSingleValueEvent(new ValueEventListener() { // from class: com.egeetouch.egeetouch_manager.BLEService.20
                @Override // com.google.firebase.database.ValueEventListener
                public void onCancelled(DatabaseError databaseError) {
                }

                @Override // com.google.firebase.database.ValueEventListener
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        return;
                    }
                    DatabaseReference push = databaseReference.push();
                    HashMap hashMap = new HashMap();
                    hashMap.put("PageNumber", 0);
                    hashMap.put("tagId", str);
                    hashMap.put("tagName", "Tag1");
                    hashMap.put("timeAdded", Long.valueOf(seconds));
                    databaseReference.child(push.getKey()).setValue(hashMap);
                }
            });
            UI_BLE.BLE_UI = 5;
        } else {
            UI_BLE.BLE_UI = 26;
        }
        Ble_Mode = 0;
    }

    public void makeToast(String str) {
        Toast toast = this.toaster;
        if (toast != null) {
            toast.cancel();
        }
        Toast makeText = Toast.makeText(this, str, 0);
        this.toaster = makeText;
        makeText.show();
    }

    public static void syncTagProcessing() {
        String str;
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        if (selected_lock_model.equals("GT2500") || selected_lock_model.equals("GT2550")) {
            firebaseDatabase = commercialDatabase;
        }
        DatabaseReference child = firebaseDatabase.getReference("tagDirectory").child(parsedIp45SerialNumber);
        final int[] iArr = {0};
        final int[] iArr2 = {0};
        for (int i = 0; i < sync_list.size(); i++) {
            if (!Fragment_add_tag.list_UID.contains(sync_list.get(i))) {
                iArr[0] = iArr[0] + 4;
                if (Fragment_add_tag.list_id.contains(sync_list.get(i)) && selected_lock_model.equals("GT2100")) {
                    str = Fragment_add_tag.list.get(Fragment_add_tag.list_id.indexOf(sync_list.get(i)));
                    System.out.println("CMDCMD found one from blue app named: " + str);
                } else {
                    str = "";
                }
                DatabaseReference push = child.push();
                push.child("PageNumber").setValue(sync_list_page.get(i)).addOnSuccessListener(new OnSuccessListener<Void>() { // from class: com.egeetouch.egeetouch_manager.BLEService.22
                    @Override // com.google.android.gms.tasks.OnSuccessListener
                    public void onSuccess(Void r3) {
                        int[] iArr3 = iArr2;
                        iArr3[0] = iArr3[0] + 1;
                        if (iArr3[0] == iArr[0]) {
                            BLEService.syncTagFinishing();
                        }
                    }
                });
                push.child("tagId").setValue(sync_list.get(i)).addOnSuccessListener(new OnSuccessListener<Void>() { // from class: com.egeetouch.egeetouch_manager.BLEService.23
                    @Override // com.google.android.gms.tasks.OnSuccessListener
                    public void onSuccess(Void r3) {
                        int[] iArr3 = iArr2;
                        iArr3[0] = iArr3[0] + 1;
                        if (iArr3[0] == iArr[0]) {
                            BLEService.syncTagFinishing();
                        }
                    }
                });
                if (str.length() > 0) {
                    push.child("tagName").setValue(str).addOnSuccessListener(new OnSuccessListener<Void>() { // from class: com.egeetouch.egeetouch_manager.BLEService.24
                        @Override // com.google.android.gms.tasks.OnSuccessListener
                        public void onSuccess(Void r3) {
                            int[] iArr3 = iArr2;
                            iArr3[0] = iArr3[0] + 1;
                            if (iArr3[0] == iArr[0]) {
                                BLEService.syncTagFinishing();
                            }
                        }
                    });
                } else {
                    push.child("tagName").setValue(sync_list.get(i)).addOnSuccessListener(new OnSuccessListener<Void>() { // from class: com.egeetouch.egeetouch_manager.BLEService.25
                        @Override // com.google.android.gms.tasks.OnSuccessListener
                        public void onSuccess(Void r3) {
                            int[] iArr3 = iArr2;
                            iArr3[0] = iArr3[0] + 1;
                            if (iArr3[0] == iArr[0]) {
                                BLEService.syncTagFinishing();
                            }
                        }
                    });
                }
                push.child("timeAdded").setValue(Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() * 1000))).addOnSuccessListener(new OnSuccessListener<Void>() { // from class: com.egeetouch.egeetouch_manager.BLEService.26
                    @Override // com.google.android.gms.tasks.OnSuccessListener
                    public void onSuccess(Void r3) {
                        int[] iArr3 = iArr2;
                        iArr3[0] = iArr3[0] + 1;
                        if (iArr3[0] == iArr[0]) {
                            BLEService.syncTagFinishing();
                        }
                    }
                });
                totalTagsInLock++;
            } else if (!Fragment_add_tag.list_UID.contains(sync_list.get(i)) && Fragment_add_tag.list_id.contains(sync_list.get(i))) {
                Fragment_add_tag.list_id.indexOf(sync_list.get(i));
            }
        }
        if (iArr[0] <= 0) {
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.egeetouch.egeetouch_manager.BLEService.27
                @Override // java.lang.Runnable
                public void run() {
                    BLEService.syncTagFinishing();
                }
            });
        }
    }

    public static void syncTagFinishing() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        if (selected_lock_model.equals("GT2500") || selected_lock_model.equals("GT2550")) {
            firebaseDatabase = commercialDatabase;
        }
        final DatabaseReference child = firebaseDatabase.getReference("tagDirectory").child(parsedIp45SerialNumber);
        final int[] iArr = {0};
        final int[] iArr2 = {0};
        for (final int i = 0; i < Fragment_add_tag.list_UID.size(); i++) {
            if (!sync_list.contains(Fragment_add_tag.list_UID.get(i))) {
                iArr[0] = iArr[0] + 1;
                child.addListenerForSingleValueEvent(new ValueEventListener() { // from class: com.egeetouch.egeetouch_manager.BLEService.28
                    @Override // com.google.firebase.database.ValueEventListener
                    public void onCancelled(DatabaseError databaseError) {
                    }

                    @Override // com.google.firebase.database.ValueEventListener
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot dataSnapshot2 : dataSnapshot.getChildren()) {
                            if (dataSnapshot2.child("tagId").getValue().toString().equals(Fragment_add_tag.list_UID.get(i))) {
                                child.child(dataSnapshot2.getKey()).removeValue(new DatabaseReference.CompletionListener() { // from class: com.egeetouch.egeetouch_manager.BLEService.28.1
                                    @Override // com.google.firebase.database.DatabaseReference.CompletionListener
                                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                                        int[] iArr3 = iArr2;
                                        iArr3[0] = iArr3[0] + 1;
                                        if (iArr2[0] == iArr[0]) {
                                            if (BLEService.selected_lock_model.equals("GT2100")) {
                                                BLEService.mainActivity.Retrive_Tag_from_Firebase();
                                            } else if (BLEService.selected_lock_model.equals("GT2500") || BLEService.selected_lock_model.equals("GT2550")) {
                                                BLEService.mainActivity.Retrive_Tag_from_FirebaseIP66();
                                            }
                                        }
                                    }
                                });
                            }
                        }
                    }
                });
            }
        }
        if (iArr[0] <= 0) {
            if (selected_lock_model.equals("GT2100")) {
                mainActivity.Retrive_Tag_from_Firebase();
            } else if (selected_lock_model.equals("GT2500") || selected_lock_model.equals("GT2550")) {
                mainActivity.Retrive_Tag_from_FirebaseIP66();
            }
        }
    }

    public void startScanDfuRepair() {
        if (isBluetoothEnabled()) {
            if (Build.VERSION.SDK_INT >= 21) {
                System.out.println("Hello StartScan() >=21!");
                BluetoothAdapter adapter = mBluetoothManager.getAdapter();
                this.mBluetoothAdapter = adapter;
                this.mLEScanner = adapter.getBluetoothLeScanner();
                this.settings = new ScanSettings.Builder().setScanMode(2).build();
                ArrayList arrayList = new ArrayList();
                this.filters = arrayList;
                this.mLEScanner.startScan(arrayList, this.settings, this.mScanCallbackRepair);
                return;
            }
            this.mBluetoothAdapter.startLeScan(this);
            System.out.println("Hello StartScan() <=21!");
        }
    }

    public void connectRepair(String str) {
        BluetoothAdapter bluetoothAdapter = this.mBluetoothAdapter;
        if (bluetoothAdapter == null || str == null) {
            return;
        }
        try {
            BluetoothDevice remoteDevice = bluetoothAdapter.getRemoteDevice(str);
            if (remoteDevice == null) {
                Log.w("ContentValues", "Device not found.  Unable to connect.");
                return;
            }
            this.mBluetoothGatt = remoteDevice.connectGatt(this, false, this.mGattCallbackRepair);
            Log.d("ContentValues", "Trying to create a new connection.");
        } catch (Exception e) {
            System.out.println("Hello Bluetooth Exception e" + e);
        }
    }

    public void disconnectRepair() {
        BluetoothGatt bluetoothGatt;
        if (this.mBluetoothAdapter == null || (bluetoothGatt = this.mBluetoothGatt) == null) {
            return;
        }
        bluetoothGatt.disconnect();
        Log.i("TAG", "disconnectRepair()");
    }
}
