package com.egeetouch.egeetouch_manager;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.FragmentManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.text.SpannableString;
import android.text.format.DateFormat;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.exifinterface.media.ExifInterface;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;
import cn.pedant.SweetAlert.SweetAlertDialog;
import com.egeetouch.egeetouch_manager.BLEService;
import com.egeetouch.egeetouch_manager.NFCCommandIso14443A;
import com.egeetouch.egeetouch_manager.SwipeDismissListViewTouchListener;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.login.widget.ToolTipPopup;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.ServerValues;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import es.dmoral.toasty.Toasty;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import javax.crypto.NoSuchPaddingException;
import kotlin.jvm.internal.ByteCompanionObject;
import no.nordicsemi.android.dfu.DfuServiceInitiator;
import okhttp3.ResponseBody;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/* loaded from: classes.dex */
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    public static Tag PICCTag = null;
    public static String PICCTag_UID = null;
    private static final int RECOVERY_REQUEST = 3;
    public static int Tag_pageNumber = 0;
    public static boolean allow_lock_access = false;
    static AnimationDrawable animation = null;
    public static String child_index = "";
    public static Context context = null;
    private static int current_lock_number = 0;
    public static ListView dashboard_listview = null;
    public static Button fab = null;
    public static Button fab_admin_access_locks = null;
    public static Button fab_share = null;
    public static FragmentManager fragmentManager = null;
    public static ListView listview = null;
    private static String lock_name_temp = "";
    public static SweetAlertDialog look_for_nfc_tag;
    public static NfcAdapter mNFCAdapter;
    public static double myLocationLatitude;
    public static double myLocationLongitude;
    private static MyPagerAdapter myPagerAdapter;
    public static SweetAlertDialog nfc_mode_dialog;
    public static PackageInfo packageInfo;
    public static Uri photoUrl;
    public static SwipeRefreshLayout pullToRefresh;
    public static SweetAlertDialog retrieving_online_database;
    public static boolean selected_isOfflineLock;
    public static SweetAlertDialog setting_progress;
    public static SweetAlertDialog shutdown_dialog;
    public static SweetAlertDialog unlocking_pd;
    public static EditText user_password1;
    public static EditText user_password2;
    private static ViewPager viewPager;
    private Handler HandlerGpsTime;
    public long RetractAccessChildCount;
    public long RetractAccessTotalChild;
    String[] bannerImg;
    String[] bannerLink;
    int bannerTotal;
    public FirebaseRemoteConfig config;
    boolean cooldown_done;
    private Handler cooldown_handler;
    final Runnable cooldown_run;
    public int count_addtag;
    public long currentTimeStamp;
    public Double currentTimeStampDouble;
    public long currentTimeStampGPS;
    FirebaseDatabase database;
    Dialog egeetouchCommercialDialog;
    Dialog gpsDialog;
    public SweetAlertDialog gps_forScanning;
    private Handler handle_image;
    private Handler handler;
    ImageView imageView;
    ImageView ip45_imageView;
    LinearLayout ll_ip45;
    public SweetAlertDialog loading_dialog;
    String loc;
    LocationListener locationListener;
    DatabaseReference lock;
    DatabaseReference lockDetails;
    DatabaseReference lockRefForUpdating;
    FirebaseAuth mAuth;
    private ServiceConnection mConnection;
    private DatabaseReference mDatabaseBanner;
    DatabaseVariable mDatabaseVariable;
    FirebaseDatabase mFirebaseDatabase;
    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    private PendingIntent mPendingIntent;
    BLEService mService;
    DatabaseReference masterTagUpdating;
    private Menu menu;
    Dialog myDialog;
    public SweetAlertDialog pDialog;
    public SweetAlertDialog password_null_error;
    int photo_position;
    public String popup_image;
    Boolean registered_found;
    Runnable runnableGpsTime;
    private Runnable runnable_slide_photo;
    public Double selected_End_Date;
    public Double selected_Start_Date;
    int send;
    private Handler sliderHandler;
    private Runnable sliderRunnable;
    Dialog successDialog;
    final Runnable task;
    Toast toaster;
    private boolean toggle_image;
    public String token;
    TextView tv_lotoSerial;
    UI_BLE ui_ble;
    private ViewPager2 viewPager2;
    public static final List<String> list = new ArrayList();
    public static final List<String> list_admin_lock = new ArrayList();
    public static long number_of_lock = 0;
    public static String name = "";
    public static String email = "";
    public static String user_uid = "";
    public static String currentTimestamp = "";
    public static double currentTimestampDouble = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
    public static String currentTimeStampFormatted = "";
    public static NFCCommandIso14443A uploaderHandler = null;
    public static StartLoadFromFileTask loaddataTask = null;
    public static boolean transmission_error = false;
    public static boolean ready_2_pair = false;
    public static boolean look_4_tags = false;
    public static boolean incorrect_pssword = false;
    public static String old_lock_admin_password = "";
    public static boolean is_admin = true;
    public static boolean low_batt = false;
    public static Double version_number = Double.valueOf((double) FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
    public static boolean scanning_new_lock = false;
    public static boolean add_old_lock = false;
    public static int addlock_model = 0;
    public static boolean location_permission_granted = false;
    public static boolean lock_dfu_repair = false;
    public static Boolean start_scanning = false;
    public static Boolean stop_scanning = false;
    public static Boolean reconnecting_lock = false;
    public static Boolean no_lock_found = false;
    public static int clickFlag = 0;
    public static int current_icon = 0;
    public static int shutdown_status = 0;
    public static Boolean dashboard_loading_firstTime = false;
    private static int counter_reset = 0;
    public static boolean watch_connected = false;
    public static int tag_num = 0;
    public static int user_num = 0;
    public static int audit_trail_num = 0;
    public static String[] firebase_lock = {""};
    public static String[] firebase_user = {""};
    public static String[] firebase_user_email = {""};
    public static ArrayList<String> shareHistoryLockName = new ArrayList<>();
    public static ArrayList<String> shareHistorySharedOnDate = new ArrayList<>();
    public static ArrayList<String> shareHistorySharedTo = new ArrayList<>();
    public static ArrayList<String> shareHistoryStartDate = new ArrayList<>();
    public static ArrayList<String> shareHistoryEndDate = new ArrayList<>();
    public static ArrayList<String> firebaseLockList = new ArrayList<>();
    public static long numberOfLocksOnFirebase = 0;
    public static long number_of_user = 0;
    public static long numberOfShareHistory = 0;
    private static int current_user_number = 0;
    private static int currentShareHistoryCounterNumber = 0;
    public static String selected_share_lock = "";
    public static String selected_share_email = "";
    public static String selected_share_role = "";
    public static boolean selected_lock_is_shared = false;
    public static String allowAccess_firebase = "";
    public static boolean allowAccess_firebase_boolean = false;
    public static boolean tokenFound = false;
    public static String selectedLockSerial = "";
    public static String selectedLockModel = "";
    public static String selectedLockName = "";
    public static String selectedLockStartTime = "";
    public static String selected_share_lock_model = "";
    public static String selected_share_lock_password = "";
    public static String selected_share_lock_version = "";
    public static String selected_share_lock_lastBatt = "";
    public static String selected_share_lock_serial = "";
    public static String selected_share_lock_MACAddress = "";
    public static boolean selected_share_lock_retractable = false;
    public static String share_access_token_from_admin = "";
    public static long share_friend_number_of_lock = 1;
    public static int selected_share_lock_Type = 0;
    public static double selected_share_lock_startTime = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
    public static double selected_share_lock_endTime = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
    public static String selected_share_lock_ip45SerialNumber = "";
    public static String selected_share_lock_sharedByEmail = "";
    public static String selected_shareAccessToken = "";
    public static boolean showTagPopUp = true;
    public static boolean returnToMainActivityFromElsewhere = false;
    public static ArrayList<String> myadminLockSerialNum = new ArrayList<>();
    public static ArrayList<String> myadminLockModelNum = new ArrayList<>();
    public static ArrayList<String> myadminLockName = new ArrayList<>();
    public static long number_of_locks = 0;
    private static int current_lock_num = 0;
    private static int currentLockCountNumber = 0;
    public static String lockNameForUpdating = "";
    public static boolean slide_enable = true;
    public static boolean lock_settings_status = false;
    public static boolean isAddingLockMode = false;
    private static Boolean first_retrieve = false;
    public static boolean stopBackStack = false;
    public static boolean isPackageInfoFound = false;
    public static String tag_name = "";
    public static String tag_id = "";
    public static long FirebaseTotalTag = 0;
    public static long FirebaseTagCount = 0;
    public static List<String> tag_page_number = new ArrayList();
    public static int vertexCount = 15;
    public static ArrayList<ArrayList<String>> tag_id_graph = new ArrayList<>(vertexCount);
    public static List<Integer> list_empty_pages = new ArrayList();
    public static List<Integer> list_empty_page_size = new ArrayList();
    public static int last_page_number = 0;
    public static boolean Tag_deletion = false;
    public static boolean isNFC_modeOn = false;
    public static boolean isUserClickedShutdown = false;
    public static boolean turnedOnNFCState = false;
    public static boolean isAuditTrailBackup = false;
    public static boolean TagAuditTrailBackup_done = false;
    static boolean audit_first_show = true;
    String[] lock_index_name = new String[100];
    public int textview_tag_limit = 5;
    private long CRC = 0;
    Handler mhandler_task = new Handler();
    public SweetAlertDialog waitForGpsTimeDialog = null;
    private boolean googleapi_connected = false;
    private boolean add_lock_pressed = false;

    public void btn_backup_nfc(View view) {
    }

    public void btn_retrieve_user_list(View view) {
    }

    public void btn_update_auditTrial(View view) {
    }

    public MainActivity() {
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        this.currentTimeStamp = currentTimeMillis;
        this.currentTimeStampDouble = Double.valueOf(currentTimeMillis);
        this.currentTimeStampGPS = 0L;
        Double valueOf = Double.valueOf((double) FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
        this.selected_Start_Date = valueOf;
        this.selected_End_Date = valueOf;
        this.token = "";
        this.RetractAccessChildCount = 0L;
        this.RetractAccessTotalChild = 0L;
        this.photo_position = 0;
        this.handler = new Handler();
        this.HandlerGpsTime = new Handler();
        this.loc = "en";
        this.registered_found = false;
        this.sliderHandler = new Handler();
        this.send = 0;
        this.toggle_image = false;
        this.cooldown_done = true;
        this.cooldown_handler = new Handler();
        this.runnable_slide_photo = new Runnable() { // from class: com.egeetouch.egeetouch_manager.MainActivity.1
            @Override // java.lang.Runnable
            public void run() {
                MainActivity.slide_enable = true;
                if (MainActivity.this.photo_position >= 4) {
                    MainActivity.this.photo_position = 0;
                } else {
                    MainActivity.this.photo_position++;
                }
                MainActivity.viewPager.setCurrentItem(MainActivity.this.photo_position, true);
                MainActivity.this.handler.postDelayed(MainActivity.this.runnable_slide_photo, DfuServiceInitiator.DEFAULT_SCAN_TIMEOUT);
            }
        };
        this.sliderRunnable = new Runnable() { // from class: com.egeetouch.egeetouch_manager.MainActivity.16
            @Override // java.lang.Runnable
            public void run() {
                MainActivity.this.viewPager2.setCurrentItem(MainActivity.this.viewPager2.getCurrentItem() + 1);
            }
        };
        this.mConnection = new ServiceConnection() { // from class: com.egeetouch.egeetouch_manager.MainActivity.18
            @Override // android.content.ServiceConnection
            public void onServiceDisconnected(ComponentName componentName) {
            }

            @Override // android.content.ServiceConnection
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                MainActivity.this.mService = ((BLEService.LocalBinder) iBinder).getService();
            }
        };
        this.cooldown_run = new Runnable() { // from class: com.egeetouch.egeetouch_manager.MainActivity.32
            @Override // java.lang.Runnable
            public void run() {
                MainActivity.this.cooldown_done = true;
            }
        };
        this.task = new Runnable() { // from class: com.egeetouch.egeetouch_manager.MainActivity.50
            @Override // java.lang.Runnable
            public void run() {
                if (MainActivity.this.is_BLE_device().booleanValue()) {
                    if (MainActivity.start_scanning.booleanValue()) {
                        System.out.println("CMDCMD start_scanning!!");
                        if (BLEService.mConnectionState == 0) {
                            MainActivity.no_lock_found = false;
                            MainActivity.start_scanning = false;
                            if (MainActivity.this.mService != null) {
                                System.out.println("HEY i am about to call mService.startscanning()!!");
                                MainActivity.this.mService.startscanning();
                            }
                        } else if ((BLEService.mConnectionState == 1 || BLEService.mConnectionState == 2) && !BLEService.connection_response && MainActivity.this.mService != null) {
                            MainActivity.this.mService.disconnect();
                        }
                    }
                    if (MainActivity.stop_scanning.booleanValue()) {
                        MainActivity.stop_scanning = false;
                        if (MainActivity.this.mService != null) {
                            MainActivity.this.mService.stopScan();
                        }
                        Log.i("TAG", "stop_scanning");
                    }
                    if (MainActivity.no_lock_found.booleanValue()) {
                        MainActivity.no_lock_found = false;
                        UI_BLE.BLE_UI = 15;
                        MainActivity.this.ui_ble.update();
                    }
                    if (BLEService.mConnectionState == 2) {
                        MainActivity.this.ui_ble.update();
                    } else if (BLEService.mConnectionState == 0) {
                        if (BLEService.shutdown_triggered) {
                            BLEService.trigger_low_power_mode = false;
                            BLEService.shutdown_triggered = false;
                            UI_BLE.BLE_UI = 13;
                            BLEService.shutdown_done = true;
                            MainActivity.this.ui_ble.update();
                        } else if (BLEService.disconnect_triggered) {
                            Log.i("TAG", "disconnect_triggered");
                            BLEService.disconnect_triggered = false;
                            if (!MainActivity.isNFC_modeOn) {
                                UI_BLE.BLE_UI = 16;
                            } else {
                                MainActivity.isNFC_modeOn = false;
                                Fragment_BLE.shutdown_trigger = false;
                                BLEService.shutdown_done = true;
                                BLEService.Ble_Mode = 0;
                                BLEService.selectedLockIP45Serial = "";
                            }
                            MainActivity.this.ui_ble.update();
                            BLEService.vicinity_on = false;
                        } else if (BLEService.cancel_scaning_triggered) {
                            MainActivity.current_icon = 0;
                            BLEService.cancel_scaning_triggered = false;
                            BLEService.shutdown_done = true;
                        }
                        if (BLEService.shutdown_done) {
                            BLEService.shutdown_done = false;
                            try {
                                MainActivity.this.read_online_database();
                                MainActivity.this.getFragmentManager().popBackStackImmediate((String) null, 1);
                                MainActivity.fab.setVisibility(0);
                                MainActivity.fab_share.setVisibility(0);
                                MainActivity.fab_admin_access_locks.setVisibility(0);
                                if (MainActivity.dashboard_listview != null) {
                                    MainActivity.dashboard_listview.setVisibility(0);
                                }
                                MainActivity.pullToRefresh.setEnabled(true);
                                MainActivity.current_icon = 0;
                                MainActivity.this.handler.post(MainActivity.this.runnable_slide_photo);
                                if (MainActivity.shutdown_dialog != null && MainActivity.shutdown_dialog.isShowing()) {
                                    MainActivity.shutdown_dialog.dismiss();
                                }
                                if (MainActivity.nfc_mode_dialog != null && MainActivity.nfc_mode_dialog.isShowing()) {
                                    MainActivity.nfc_mode_dialog.dismiss();
                                }
                                if (Helper_Network.haveNetworkConnection(MainActivity.context)) {
                                    System.out.println("HEY you are online after SHUT DOWN pressed");
                                } else {
                                    System.out.println("HEY you are offline after SHUT DOWN pressed, refreshing with  Database locks");
                                    MainActivity.list.clear();
                                    Cursor rawQuery = DatabaseVariable.db_lock.rawQuery(DatabaseVariable.Query_lock_name_db, null);
                                    while (rawQuery.moveToNext()) {
                                        Log.i("Tag", "Lock: " + rawQuery.getString(rawQuery.getColumnIndex(DatabaseVariable.D1_lock)));
                                        MainActivity.list.add(rawQuery.getString(rawQuery.getColumnIndex(DatabaseVariable.D1_lock)));
                                    }
                                    rawQuery.close();
                                    MainActivity.this.show_dashboard_list();
                                }
                                BLEService.verified_password_done = false;
                                MainActivity.this.getSupportActionBar().setTitle(R.string.dashboard);
                            } catch (Exception e) {
                                Toast.makeText(MainActivity.this, e.toString(), 1).show();
                            }
                        }
                    }
                }
                MainActivity.this.mhandler_task.postDelayed(this, 100L);
            }
        };
        this.runnableGpsTime = new Runnable() { // from class: com.egeetouch.egeetouch_manager.MainActivity.58
            @Override // java.lang.Runnable
            public void run() {
                if (MainActivity.this.currentTimeStampGPS <= 0) {
                    MainActivity.this.HandlerGpsTime.postDelayed(this, 100L);
                    return;
                }
                if (MainActivity.this.waitForGpsTimeDialog != null && MainActivity.this.waitForGpsTimeDialog.isShowing()) {
                    MainActivity.this.waitForGpsTimeDialog.cancel();
                }
                MainActivity.this.lock_access();
            }
        };
    }

    static /* synthetic */ int access$1108() {
        int i = current_user_number;
        current_user_number = i + 1;
        return i;
    }

    static /* synthetic */ int access$1208() {
        int i = currentShareHistoryCounterNumber;
        currentShareHistoryCounterNumber = i + 1;
        return i;
    }

    static /* synthetic */ int access$1808() {
        int i = current_lock_number;
        current_lock_number = i + 1;
        return i;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
    }

    private void setup_photo_slider() {
        myPagerAdapter = new MyPagerAdapter();
        ViewPager viewPager2 = (ViewPager) findViewById(R.id.myviewpager);
        viewPager = viewPager2;
        viewPager2.setAdapter(myPagerAdapter);
        this.handler.post(this.runnable_slide_photo);
    }

    private void banner_database() {
        if (Locale.getDefault().getLanguage().equals("ja")) {
            this.loc = "ja";
        }
        this.mDatabaseBanner.child(this.loc).addListenerForSingleValueEvent(new ValueEventListener() { // from class: com.egeetouch.egeetouch_manager.MainActivity.2
            @Override // com.google.firebase.database.ValueEventListener
            public void onCancelled(DatabaseError databaseError) {
            }

            @Override // com.google.firebase.database.ValueEventListener
            public void onDataChange(DataSnapshot dataSnapshot) {
                MainActivity.this.bannerTotal = (int) dataSnapshot.getChildrenCount();
                if (MainActivity.this.bannerTotal > 0) {
                    MainActivity mainActivity = MainActivity.this;
                    mainActivity.bannerImg = new String[mainActivity.bannerTotal];
                    MainActivity mainActivity2 = MainActivity.this;
                    mainActivity2.bannerLink = new String[mainActivity2.bannerTotal];
                } else {
                    MainActivity.this.loc = "en";
                    MainActivity.this.mDatabaseBanner.child(MainActivity.this.loc).addListenerForSingleValueEvent(new ValueEventListener() { // from class: com.egeetouch.egeetouch_manager.MainActivity.2.1
                        @Override // com.google.firebase.database.ValueEventListener
                        public void onCancelled(DatabaseError databaseError) {
                        }

                        @Override // com.google.firebase.database.ValueEventListener
                        public void onDataChange(DataSnapshot dataSnapshot2) {
                            MainActivity.this.bannerTotal = (int) dataSnapshot2.getChildrenCount();
                            MainActivity.this.bannerImg = new String[MainActivity.this.bannerTotal];
                            MainActivity.this.bannerLink = new String[MainActivity.this.bannerTotal];
                        }
                    });
                }
                MainActivity.this.mDatabaseBanner.child(MainActivity.this.loc).addListenerForSingleValueEvent(new ValueEventListener() { // from class: com.egeetouch.egeetouch_manager.MainActivity.2.2
                    @Override // com.google.firebase.database.ValueEventListener
                    public void onCancelled(DatabaseError databaseError) {
                    }

                    @Override // com.google.firebase.database.ValueEventListener
                    public void onDataChange(DataSnapshot dataSnapshot2) {
                        int i = 0;
                        int i2 = 0;
                        for (DataSnapshot dataSnapshot3 : dataSnapshot2.getChildren()) {
                            if (dataSnapshot2.exists()) {
                                MainActivity.this.bannerImg[i] = dataSnapshot3.child("img").getValue().toString();
                                i++;
                                if (i >= MainActivity.this.bannerTotal) {
                                    for (DataSnapshot dataSnapshot4 : dataSnapshot2.getChildren()) {
                                        if (dataSnapshot2.exists()) {
                                            MainActivity.this.bannerLink[i2] = dataSnapshot4.child("url").getValue().toString();
                                            i2++;
                                            if (i2 >= MainActivity.this.bannerTotal) {
                                                MainActivity.this.initViewPager();
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class MyPagerAdapter extends PagerAdapter {
        int NumberOfPages = 5;
        int[] res = {R.drawable.advert1, R.drawable.advert5, R.drawable.advert6, R.drawable.advert7, R.drawable.advert2, R.drawable.advert4};
        int[] backgroundcolor = {-1, -1, -1, -1, -1, -1, -1};

        @Override // androidx.viewpager.widget.PagerAdapter
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        MyPagerAdapter() {
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public int getCount() {
            return this.NumberOfPages;
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public Object instantiateItem(ViewGroup viewGroup, final int i) {
            ImageView imageView = new ImageView(MainActivity.this);
            imageView.setImageResource(this.res[i]);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            ActionBar.LayoutParams layoutParams = new ActionBar.LayoutParams(-2, -2);
            layoutParams.setMargins(0, 0, 0, 0);
            imageView.setLayoutParams(layoutParams);
            imageView.setOnClickListener(new View.OnClickListener() { // from class: com.egeetouch.egeetouch_manager.MainActivity.MyPagerAdapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (MainActivity.slide_enable) {
                        if (i != 1) {
                            Intent intent = new Intent("android.intent.action.VIEW");
                            intent.setData(Uri.parse("https://www.amazon.com/stores/eGeeTouch/eGeeTouch/page/ADA490D1-478F-410B-8C05-98E1BC58E638"));
                            MainActivity.this.startActivity(intent);
                            return;
                        }
                        MainActivity.this.startActivity(new Intent(MainActivity.context, Activity_egeetouch_commercial_ad.class));
                    }
                }
            });
            LinearLayout linearLayout = new LinearLayout(MainActivity.this);
            linearLayout.setOrientation(1);
            ActionBar.LayoutParams layoutParams2 = new ActionBar.LayoutParams(-2, -2);
            linearLayout.setBackgroundColor(this.backgroundcolor[i]);
            linearLayout.setLayoutParams(layoutParams2);
            linearLayout.addView(imageView);
            viewGroup.addView(linearLayout);
            return linearLayout;
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            viewGroup.removeView((LinearLayout) obj);
        }
    }

    public void initViewPager() {
        this.viewPager2 = (ViewPager2) findViewById(R.id.viewPagerImageSlider);
        this.viewPager2.setAdapter(new SliderAdapter(new ArrayList(Arrays.asList(this.bannerImg)), new ArrayList(Arrays.asList(this.bannerLink)), this.viewPager2));
        this.viewPager2.setClipToPadding(false);
        this.viewPager2.setClipChildren(false);
        this.viewPager2.setOffscreenPageLimit(3);
        this.viewPager2.getChildAt(0).setOverScrollMode(0);
        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(30));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() { // from class: com.egeetouch.egeetouch_manager.MainActivity.3
            @Override // androidx.viewpager2.widget.ViewPager2.PageTransformer
            public void transformPage(View view, float f) {
                view.setScaleY(((1.0f - Math.abs(f)) * 0.15f) + 0.85f);
            }
        });
        this.viewPager2.setPageTransformer(compositePageTransformer);
        this.viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() { // from class: com.egeetouch.egeetouch_manager.MainActivity.4
            @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
            public void onPageSelected(int i) {
                super.onPageSelected(i);
                MainActivity.this.sliderHandler.removeCallbacks(MainActivity.this.sliderRunnable);
                MainActivity.this.sliderHandler.postDelayed(MainActivity.this.sliderRunnable, ToolTipPopup.DEFAULT_POPUP_DISPLAY_TIME);
            }
        });
    }

    public String encryptPassword(String str) {
        try {
            try {
                return new CryptLib().encryptPlainTextWithRandomIV(str, "ThisIsMyKey");
            } catch (Exception unused) {
                System.out.println("HEY error in encrypting");
                return "";
            }
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            e.printStackTrace();
            System.out.println("HEY some issue in encrypting/decrypting");
            return "";
        }
    }

    public String decryptPassword(String str) {
        try {
            try {
                return new CryptLib().decryptCipherTextWithRandomIV(str, "ThisIsMyKey");
            } catch (Exception unused) {
                System.out.println("HEY error in decrypting");
                return "";
            }
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            e.printStackTrace();
            System.out.println("HEY some issue in encrypting/decrypting");
            return "";
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        NfcAdapter nfcAdapter;
        System.out.println("HEY mainActivity onCreate called");
        super.onCreate(bundle);
        this.myDialog = new Dialog(this);
        this.successDialog = new Dialog(this);
        this.mAuth = FirebaseAuth.getInstance();
        try {
            if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                if (FirebaseAuth.getInstance().getCurrentUser().getUid() != null) {
                    FirebaseCrashlytics.getInstance().setUserId(FirebaseAuth.getInstance().getCurrentUser().getUid());
                }
            } else {
                FirebaseCrashlytics.getInstance().setUserId("NotLoggedIn");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        setContentView(R.layout.activity_main);
        getWindow().setFlags(1024, 1024);
        context = this;
        try {
            packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            isPackageInfoFound = true;
        } catch (Exception unused) {
            isPackageInfoFound = false;
        }
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor edit = defaultSharedPreferences.edit();
        user_uid = defaultSharedPreferences.getString("userID", "");
        email = defaultSharedPreferences.getString("rmbMe_email", "");
        currentTimestamp = defaultSharedPreferences.getString("currentTimeStamp", "");
        setup_photo_slider();
        initialize_database();
        new Firebase_Data_management();
        Firebase_Data_management.DeclareCommercialDBInstance();
        this.mDatabaseBanner = FirebaseDatabase.getInstance().getReference("advertisementContent");
        banner_database();
        if (Helper_Network.haveNetworkConnection(context)) {
            getNumberOfLocksOnFirebaseAndRefresh();
            Firebase_Data_management.fetch_Recipient(user_uid);
            System.out.println("Hey im at " + user_uid);
        } else {
            list.clear();
            Cursor rawQuery = DatabaseVariable.db_lock.rawQuery(DatabaseVariable.Query_lock_name_db, null);
            while (rawQuery.moveToNext()) {
                Log.i("Tag", "Lock: " + rawQuery.getString(rawQuery.getColumnIndex(DatabaseVariable.D1_lock)));
                list.add(rawQuery.getString(rawQuery.getColumnIndex(DatabaseVariable.D1_lock)));
            }
            rawQuery.close();
            show_dashboard_list();
        }
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.pullToRefresh);
        pullToRefresh = swipeRefreshLayout;
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() { // from class: com.egeetouch.egeetouch_manager.MainActivity.5
            @Override // androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
            public void onRefresh() {
                if (Helper_Network.haveNetworkConnection(MainActivity.context)) {
                    MainActivity.this.getNumberOfLocksOnFirebaseAndRefresh();
                    Firebase_Data_management.fetch_Recipient(MainActivity.user_uid);
                } else {
                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(MainActivity.context, 0);
                    sweetAlertDialog.setTitleText(MainActivity.this.getString(R.string.pls_note));
                    sweetAlertDialog.setContentText(MainActivity.this.getString(R.string.you_are_not_connected));
                    sweetAlertDialog.show();
                }
                MainActivity.this.isLocationPermissionEnabled();
                MainActivity.pullToRefresh.setRefreshing(false);
            }
        });
        isLocationPermissionEnabled();
        bindService(new Intent(this, BLEService.class), this.mConnection, 1);
        this.ui_ble = new UI_BLE(context);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fragmentManager = getFragmentManager();
        Button button = (Button) findViewById(R.id.fab);
        fab = button;
        button.setOnClickListener(new View.OnClickListener() { // from class: com.egeetouch.egeetouch_manager.MainActivity.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                System.out.println("HEY fab tapped");
                MainActivity.lock_settings_status = false;
                if (MainActivity.this.isBluetoothEnabled().booleanValue() && MainActivity.this.isLocationPermissionEnabled()) {
                    if (Helper_Network.haveNetworkConnection(MainActivity.this)) {
                        DatabaseVariable.db_lock.rawQuery(DatabaseVariable.Query_lock_db, null);
                        long j = 0;
                        try {
                            j = MainActivity.list.size();
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                        if (j < 10) {
                            if (MainActivity.this.checkGps()) {
                                MainActivity.this.handler.removeCallbacks(MainActivity.this.runnable_slide_photo);
                                MainActivity.this.btn_addlock(view);
                                MainActivity.slide_enable = false;
                                return;
                            }
                            Log.i("Connection", "Location is disabled");
                            return;
                        }
                        CommercialAdvert_dialog.ShowDialog(MainActivity.context, "lockLimit");
                        return;
                    }
                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(MainActivity.this, 1);
                    sweetAlertDialog.setTitleText(MainActivity.this.getString(R.string.no_connection));
                    sweetAlertDialog.setContentText(MainActivity.this.getString(R.string.no_internet));
                    sweetAlertDialog.setConfirmText(MainActivity.this.getString(R.string.ok));
                    sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.MainActivity.6.1
                        @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                        public void onClick(SweetAlertDialog sweetAlertDialog2) {
                            sweetAlertDialog2.dismissWithAnimation();
                        }
                    });
                    sweetAlertDialog.show();
                    return;
                }
                SweetAlertDialog sweetAlertDialog2 = new SweetAlertDialog(MainActivity.this, 1);
                sweetAlertDialog2.setTitleText(MainActivity.this.getString(R.string.no_connection));
                sweetAlertDialog2.setContentText(MainActivity.this.getString(R.string.no_bluetooth));
                sweetAlertDialog2.setConfirmText(MainActivity.this.getString(R.string.ok));
                sweetAlertDialog2.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.MainActivity.6.2
                    @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                    public void onClick(SweetAlertDialog sweetAlertDialog3) {
                        sweetAlertDialog3.dismissWithAnimation();
                    }
                });
                sweetAlertDialog2.show();
            }
        });
        Button button2 = (Button) findViewById(R.id.fab_share);
        fab_share = button2;
        button2.setOnClickListener(new View.OnClickListener() { // from class: com.egeetouch.egeetouch_manager.MainActivity.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                System.out.println("HEY fab_share tapped");
                MainActivity.this.startActivity(new Intent(MainActivity.this, Activity_share_lock.class));
            }
        });
        Button button3 = (Button) findViewById(R.id.fab_access);
        fab_admin_access_locks = button3;
        button3.setOnClickListener(new View.OnClickListener() { // from class: com.egeetouch.egeetouch_manager.MainActivity.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                System.out.println("HEY fab_admin_access_locks tapped");
                MainActivity.this.btn_admin_access_locks(view);
            }
        });
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) { // from class: com.egeetouch.egeetouch_manager.MainActivity.9
            @Override // androidx.appcompat.app.ActionBarDrawerToggle, androidx.drawerlayout.widget.DrawerLayout.DrawerListener
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
            }

            @Override // androidx.appcompat.app.ActionBarDrawerToggle, androidx.drawerlayout.widget.DrawerLayout.DrawerListener
            public void onDrawerOpened(View view) {
                super.onDrawerOpened(view);
                TextView textView = (TextView) MainActivity.this.findViewById(R.id.TextView_user_name);
                TextView textView2 = (TextView) MainActivity.this.findViewById(R.id.TextView_user_email);
                TextView textView3 = (TextView) MainActivity.this.findViewById(R.id.textView_currentDateTime);
                SharedPreferences defaultSharedPreferences2 = PreferenceManager.getDefaultSharedPreferences(MainActivity.this.getApplicationContext());
                if (textView != null) {
                    textView.setText(defaultSharedPreferences2.getString("username", ""));
                }
                if (textView2 != null) {
                    textView2.setText(defaultSharedPreferences2.getString("rmbMe_email", ""));
                }
                if (!MainActivity.currentTimestamp.equals("")) {
                    long parseLong = Long.parseLong(MainActivity.currentTimestamp);
                    Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
                    calendar.setTimeInMillis(parseLong * 1000);
                    System.out.println("HEY currentTimestamp from onDrawerOpened " + MainActivity.currentTimestamp);
                    MainActivity.currentTimeStampFormatted = DateFormat.format("MMM dd yyyy, HH:mm", calendar).toString();
                    if (textView3 != null) {
                        textView3.setText(MainActivity.this.getResources().getString(R.string.last_sync) + " " + MainActivity.currentTimeStampFormatted);
                    }
                } else if (textView3 != null) {
                    textView3.setText(R.string.last_sync);
                }
                TextView textView4 = (TextView) MainActivity.this.findViewById(R.id.textView_version);
                try {
                    System.out.println("Hello check the version commit:" + defaultSharedPreferences2.getString("currentVersionName", ExifInterface.GPS_MEASUREMENT_2D));
                    textView4.setText(MainActivity.this.getString(R.string.app_version) + " " + MainActivity.this.getPackageManager().getPackageInfo(MainActivity.this.getPackageName(), 0).versionName);
                } catch (Exception unused2) {
                }
            }
        };
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (Locale.getDefault().getLanguage().equals("ja")) {
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.menu_icon_ja);
        } else {
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.menu_icon);
        }
        ((NavigationView) findViewById(R.id.nav_view)).setNavigationItemSelectedListener(this);
        this.mhandler_task.post(this.task);
        setTitle(getResources().getString(R.string.dashboard));
        current_icon = 0;
        mNFCAdapter = NfcAdapter.getDefaultAdapter(this);
        onNewIntent(getIntent());
        if (getPackageManager().hasSystemFeature("android.hardware.nfc") && (nfcAdapter = mNFCAdapter) != null && nfcAdapter.isEnabled()) {
            this.mPendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, getClass()).addFlags(536870912), 33554432);
        }
        if (checkPlayServices()) {
            buildGoogleApiClient();
        }
        try {
            edit.putString("currentVersionName", getPackageManager().getPackageInfo(getPackageName(), 0).versionName.toString());
            edit.commit();
        } catch (Exception e2) {
            System.out.println("Hello checking the app launcher in main ex:" + e2);
        }
        HashMap hashMap = new HashMap();
        this.config = FirebaseRemoteConfig.getInstance();
        this.config.setConfigSettingsAsync(new FirebaseRemoteConfigSettings.Builder().setMinimumFetchIntervalInSeconds(1L).build());
        this.config.setDefaultsAsync(hashMap);
        this.config.fetchAndActivate().addOnCompleteListener(new OnCompleteListener<Boolean>() { // from class: com.egeetouch.egeetouch_manager.MainActivity.10
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public void onComplete(Task<Boolean> task) {
                SharedPreferences sharedPreferences = MainActivity.this.getApplicationContext().getSharedPreferences("MyPref", 0);
                SharedPreferences.Editor edit2 = sharedPreferences.edit();
                if (MainActivity.this.config.getString(RemotePopUp.IMAGEPOPUP).equals(sharedPreferences.getString("IMGPOPUP", ""))) {
                    if (sharedPreferences.getBoolean("dismiss_permission", false)) {
                        System.out.println("dismiss clicked");
                        return;
                    }
                    System.out.println("dismiss not clicked");
                    Long valueOf = Long.valueOf(sharedPreferences.getLong("time", 0L));
                    Long valueOf2 = Long.valueOf(MainActivity.this.config.getLong(RemotePopUp.TIME) * 3600000);
                    Long valueOf3 = Long.valueOf(valueOf.longValue() + valueOf2.longValue());
                    System.out.println("CHECKING TIME FETCHING :" + valueOf2 + "CHECKING IMPORT TIME " + valueOf3);
                    System.out.println("CHECKING CURRENT TIME" + System.currentTimeMillis());
                    if (System.currentTimeMillis() >= valueOf3.longValue()) {
                        edit2.putLong("time", System.currentTimeMillis());
                        edit2.apply();
                        MainActivity.this.displaylog();
                        return;
                    }
                    System.out.println("wait for 24hours need " + (valueOf3.longValue() - System.currentTimeMillis()) + " sec more");
                    return;
                }
                System.out.println("link its different");
                edit2.putLong("time", 0L);
                edit2.apply();
                Long valueOf4 = Long.valueOf(sharedPreferences.getLong("time", 0L));
                Long valueOf5 = Long.valueOf(MainActivity.this.config.getLong(RemotePopUp.TIME) * 120000);
                Long valueOf6 = Long.valueOf(valueOf4.longValue() + valueOf5.longValue());
                edit2.putBoolean("dismiss_permission", false);
                edit2.putString("IMGPOPUP", MainActivity.this.config.getString(RemotePopUp.IMAGEPOPUP));
                edit2.apply();
                System.out.println("CHECKING TIME FETCHING :" + valueOf5 + "CHECKING IMPORT TIME " + valueOf6);
                System.out.println("CHECKING CURRENT TIME" + System.currentTimeMillis());
                if (System.currentTimeMillis() >= valueOf6.longValue()) {
                    edit2.putLong("time", System.currentTimeMillis());
                    edit2.apply();
                    MainActivity.this.displaylog();
                    return;
                }
                System.out.println("wait for 24hours need " + (valueOf6.longValue() - System.currentTimeMillis()) + " sec more");
            }
        });
    }

    public void displaylog() {
        System.out.println("THIS IS DISPLAYLOG");
        System.out.println("PRINT : " + this.config.getBoolean(RemotePopUp.ISDISPLAY));
        if (this.config.getString(RemotePopUp.IMAGEPOPUP) == null || !this.config.getBoolean(RemotePopUp.ISDISPLAY) || this.config.getString(RemotePopUp.IMAGEPOPUP).length() <= 0) {
            return;
        }
        try {
            new Popup_ads(this, this.config).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btn_ads(View view) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse("https://www.egeetouch.com/4thofjuly"));
        startActivity(intent);
        this.myDialog.dismiss();
    }

    public void displayDialog() {
        this.myDialog.setContentView(R.layout.pop_up_ads);
        this.myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor edit = defaultSharedPreferences.edit();
        System.out.println("time :" + System.currentTimeMillis());
        System.out.println("time :" + defaultSharedPreferences.getLong("time", 0L));
        edit.putLong("time", System.currentTimeMillis());
        edit.apply();
        new Handler().post(new Runnable() { // from class: com.egeetouch.egeetouch_manager.MainActivity.11
            @Override // java.lang.Runnable
            public void run() {
                MainActivity.this.myDialog.show();
            }
        });
    }

    private void checkForPromotion() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        this.database = firebaseDatabase;
        firebaseDatabase.getReference("claimedFreeUpgrade").child(user_uid).addListenerForSingleValueEvent(new ValueEventListener() { // from class: com.egeetouch.egeetouch_manager.MainActivity.12
            @Override // com.google.firebase.database.ValueEventListener
            public void onCancelled(DatabaseError databaseError) {
            }

            @Override // com.google.firebase.database.ValueEventListener
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    HashMap hashMap = (HashMap) dataSnapshot.getValue();
                    Boolean valueOf = Boolean.valueOf(hashMap.containsKey("redeemed") ? Boolean.valueOf(hashMap.get("redeemed").toString()).booleanValue() : false);
                    if (hashMap.containsKey("email")) {
                        hashMap.get("email").toString();
                    }
                    if (valueOf.booleanValue()) {
                        return;
                    }
                    MainActivity.this.claimRedeemption();
                    return;
                }
                System.out.println("Hello checking the displayDialog1 values : Doesn't exist ");
                MainActivity.this.claimRedeemption();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void claimRedeemption() {
        this.myDialog.setContentView(R.layout.dialog_pop_up_commercial);
        this.successDialog.setContentView(R.layout.dialog_success_pop_up);
        ((Button) this.myDialog.findViewById(R.id.claim)).setOnClickListener(new View.OnClickListener() { // from class: com.egeetouch.egeetouch_manager.MainActivity.13
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MainActivity.this.myDialog.dismiss();
                if (Helper_Network.haveNetworkConnection(MainActivity.context)) {
                    MainActivity.this.sendViaMailGun(MainActivity.email);
                    Firebase_Data_management.claimRedeemptionUpdateFB(MainActivity.email, MainActivity.user_uid);
                    MainActivity.this.successDialog.show();
                    return;
                }
                SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(MainActivity.this, 0);
                sweetAlertDialog.setTitleText(MainActivity.this.getString(R.string.pls_note));
                sweetAlertDialog.setContentText(MainActivity.this.getString(R.string.you_are_not_connected_access_lock));
                sweetAlertDialog.show();
            }
        });
        ((Button) this.successDialog.findViewById(R.id.done)).setOnClickListener(new View.OnClickListener() { // from class: com.egeetouch.egeetouch_manager.MainActivity.14
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MainActivity.this.successDialog.dismiss();
            }
        });
        this.myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        this.successDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        this.myDialog.show();
    }

    public void btn_learn(View view) {
        startActivityForResult(new Intent(this, LearnMoreActivity.class), 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendViaMailGun(String str) {
        String str2;
        String string = getString(R.string.heres_your_egeetouch_free_redemption_code);
        if (Locale.getDefault().getLanguage().equals("ja")) {
            System.out.println("Hey im at lang " + Locale.getDefault().getLanguage());
            str2 = "email_blast_success_jp";
        } else {
            str2 = "email_blast_success";
        }
        System.out.println("Hello checking the mailGun variables:{}");
        RetrofitClient.getInstance().getApi().sendEmail("The eGeeTouch Team <noreply@egeetouch.com>", str, string, "", str2, "{}").enqueue(new Callback<ResponseBody>() { // from class: com.egeetouch.egeetouch_manager.MainActivity.15
            @Override // retrofit2.Callback
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                System.out.println("Hello checking the mailGun Response:" + response);
                if (response.code() == 200) {
                    try {
                        System.out.println("Hello checking the mailGun Response 1:" + response);
                        new JSONObject(response.body().string());
                        MainActivity.this.send = 1;
                        System.out.println("Redemption Code Sent");
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                        System.out.println("Redemption Code Failed Sent");
                    }
                }
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<ResponseBody> call, Throwable th) {
                Toast.makeText(MainActivity.this, th.getMessage(), 1).show();
                System.out.println("Redemption Code Failed");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        System.out.println("HEY mainActivity onDestroy called");
        System.out.println("Hello service unbinded");
        unbindService(this.mConnection);
    }

    @Override // android.app.Activity
    protected void onRestart() {
        super.onRestart();
        System.out.println("HEY mainActivity onRestart called");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        System.out.println("HEY mainActivity onResume called");
        this.cooldown_done = true;
        if (!is_BLE_device().booleanValue() && getPackageManager().hasSystemFeature("android.hardware.nfc")) {
            NfcAdapter defaultAdapter = NfcAdapter.getDefaultAdapter(this);
            mNFCAdapter = defaultAdapter;
            if (defaultAdapter != null && defaultAdapter.isEnabled()) {
                this.mPendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, getClass()).addFlags(603979776), 33554432);
                enableForegroundMode();
            }
        }
        if (!BLEService.selected_lock_model.equals("GT2000") && !BLEService.selected_lock_model.equals("GT3000")) {
            BLEService.selected_lock_model.equals("GT2003");
        }
        if (BLEService.selected_lock_model.equals("GT2000") || BLEService.selected_lock_model.equals("GT3000") || BLEService.selected_lock_model.equals("GT2003")) {
            checkPlayServices();
        }
        if (Helper_Network.haveNetworkConnection(context) && isPackageInfoFound) {
            try {
                AppRater.app_launched(context, packageInfo.versionCode);
            } catch (Exception e) {
                Log.i("MainActivity", "PackageInfo exception:" + e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        this.cooldown_handler.removeCallbacks(this.cooldown_run);
        System.out.println("HEY mainActivity onPause is called");
        if (is_BLE_device().booleanValue()) {
            return;
        }
        disableForegroundMode();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        System.out.println("HEY mainActivity onStart called");
        GoogleApiClient googleApiClient = this.mGoogleApiClient;
        if (googleApiClient != null) {
            googleApiClient.connect();
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        System.out.println("HEY onBackPressed from MainActivity pressed");
        int i = current_icon;
        if (i == 6 || i == 7) {
            current_icon = 5;
        }
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else if (getFragmentManager().getBackStackEntryCount() == 0) {
            try {
                new SweetAlertDialog(context).setTitleText(context.getResources().getString(R.string.quit)).setContentText(context.getResources().getString(R.string.are_you_sure_you_want_to_quit)).setConfirmText(context.getResources().getString(R.string.yes)).setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.MainActivity.17
                    @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismissWithAnimation();
                        MainActivity.this.setResult(-1, new Intent());
                        MainActivity.this.finish();
                    }
                }).setCancelText(context.getResources().getString(R.string.no)).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            boolean z = stopBackStack;
            if (z) {
                if (z) {
                    Toast.makeText(this, "Please Shutdown or use the icons to navigate!", 1).show();
                    return;
                }
                return;
            }
            try {
                if (getFragmentManager().getBackStackEntryCount() == 1) {
                    Log.i("TAG", "TOP STACK");
                    System.out.println("HEY back to TOP STACK");
                    if (is_BLE_device().booleanValue()) {
                        BLEService.cancel_scaning_triggered = true;
                        BLEService.Ble_Mode = BLEService.disconnect;
                    }
                    show_dashboard_list();
                    this.handler.post(this.runnable_slide_photo);
                    BLEService.verified_password_done = false;
                    fab.setVisibility(0);
                    fab_share.setVisibility(0);
                    fab_admin_access_locks.setVisibility(0);
                    ListView listView = dashboard_listview;
                    if (listView != null) {
                        listView.setVisibility(0);
                    }
                    pullToRefresh.setEnabled(true);
                    this.add_lock_pressed = false;
                    current_icon = 0;
                    fragmentManager.popBackStackImmediate();
                    getSupportActionBar().setTitle(R.string.dashboard);
                    return;
                }
                FragmentManager fragmentManager2 = fragmentManager;
                if (fragmentManager2.getBackStackEntryAt(fragmentManager2.getBackStackEntryCount() - 1).getName().equals("AddLock1")) {
                    this.add_lock_pressed = false;
                    current_icon = 0;
                    if (is_BLE_device().booleanValue()) {
                        BLEService.cancel_scaning_triggered = true;
                        BLEService.Ble_Mode = BLEService.disconnect;
                    }
                    this.handler.post(this.runnable_slide_photo);
                    fab.setVisibility(0);
                    fab_share.setVisibility(0);
                    fab_admin_access_locks.setVisibility(0);
                    ListView listView2 = dashboard_listview;
                    if (listView2 != null) {
                        listView2.setVisibility(0);
                    }
                    pullToRefresh.setEnabled(true);
                    Log.i("TAG", "TOP STACK1");
                    System.out.println("HEY back to TOP STACK1");
                    fragmentManager.popBackStack();
                    getSupportActionBar().setTitle(R.string.dashboard);
                    return;
                }
                FragmentManager fragmentManager3 = fragmentManager;
                if (fragmentManager3.getBackStackEntryAt(fragmentManager3.getBackStackEntryCount() - 1).getName().equals("AddLock2")) {
                    if (is_BLE_device().booleanValue()) {
                        BLEService bLEService = this.mService;
                        if (bLEService != null) {
                            bLEService.stopScan();
                        }
                        try {
                            BLEService.Ble_Mode = BLEService.disconnect;
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                    Log.i("TAG", "TOP STACK2");
                    System.out.println("HEY back to TOPSTACK2");
                    fragmentManager.popBackStack();
                } else if (shutdown_status == 1) {
                    shutdown_status = 0;
                    Log.i("TAG", "TOP STACK");
                    System.out.println("HEllo back navigation to shutdown");
                    if (is_BLE_device().booleanValue()) {
                        BLEService.cancel_scaning_triggered = true;
                        BLEService.Ble_Mode = BLEService.disconnect;
                    }
                    show_dashboard_list();
                    this.handler.post(this.runnable_slide_photo);
                    BLEService.verified_password_done = false;
                    fab.setVisibility(0);
                    fab_share.setVisibility(0);
                    fab_admin_access_locks.setVisibility(0);
                    ListView listView3 = dashboard_listview;
                    if (listView3 != null) {
                        listView3.setVisibility(0);
                    }
                    pullToRefresh.setEnabled(true);
                    this.add_lock_pressed = false;
                    current_icon = 0;
                    fragmentManager.popBackStack();
                    getSupportActionBar().setTitle(R.string.dashboard);
                } else {
                    Log.i("TAG", "TOP STACK3");
                    System.out.println("HEY back to TOPSTACK3");
                    fragmentManager.popBackStack();
                }
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tutorial, menu);
        return true;
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.action_button_tutorial) {
            String str = Helper_PhoneDetails.phonelangauge().equals("ja") ? "http://www.egeetouch.com/jp/support/video" : "http://www.egeetouch.com/support/video";
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse(str));
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Override // com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == R.id.nav_shop) {
            if (Helper_PhoneDetails.phonelangauge().equals("ja")) {
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setData(Uri.parse("https://www.egeetouch.com/jp/sales/retailer"));
                startActivity(intent);
            } else {
                Intent intent2 = new Intent("android.intent.action.VIEW");
                intent2.setData(Uri.parse("https://www.egeetouch.com/sales/"));
                startActivity(intent2);
            }
        } else if (itemId == R.id.nav_tutorial) {
            if (Helper_PhoneDetails.phonelangauge().equals("ja")) {
                Intent intent3 = new Intent("android.intent.action.VIEW");
                intent3.setData(Uri.parse("https://www.egeetouch.com/jp/support/video"));
                startActivity(intent3);
            } else {
                Intent intent4 = new Intent("android.intent.action.VIEW");
                intent4.setData(Uri.parse("https://www.egeetouch.com/support/video"));
                startActivity(intent4);
            }
        } else if (itemId == R.id.nav_manual) {
            if (Helper_PhoneDetails.phonelangauge().equals("ja")) {
                Intent intent5 = new Intent("android.intent.action.VIEW");
                intent5.setData(Uri.parse("http://www.egeetouch.com/jp/support/catalog-manual"));
                startActivity(intent5);
            } else {
                Intent intent6 = new Intent("android.intent.action.VIEW");
                intent6.setData(Uri.parse("http://www.egeetouch.com/support/catalog-manual"));
                startActivity(intent6);
            }
        } else if (itemId == R.id.nav_faq) {
            if (Helper_PhoneDetails.phonelangauge().equals("ja")) {
                Intent intent7 = new Intent("android.intent.action.VIEW");
                intent7.setData(Uri.parse("https://www.egeetouch.com/jp/support/faq"));
                startActivity(intent7);
            } else {
                Intent intent8 = new Intent("android.intent.action.VIEW");
                intent8.setData(Uri.parse("https://www.egeetouch.com/support/"));
                startActivity(intent8);
            }
        } else if (itemId == R.id.nav_contact) {
            if (Helper_PhoneDetails.phonelangauge().equals("ja")) {
                Intent intent9 = new Intent("android.intent.action.VIEW");
                intent9.setData(Uri.parse("https://www.egeetouch.com/jp/contact"));
                startActivity(intent9);
            } else {
                Intent intent10 = new Intent("android.intent.action.VIEW");
                intent10.setData(Uri.parse("https://www.egeetouch.com/contact"));
                startActivity(intent10);
            }
        } else if (itemId == R.id.nav_privacy) {
            if (Helper_PhoneDetails.phonelangauge().equals("ja")) {
                Intent intent11 = new Intent("android.intent.action.VIEW");
                intent11.setData(Uri.parse("https://www.egeetouch.com/jp/privacy-policy"));
                startActivity(intent11);
            } else {
                Intent intent12 = new Intent("android.intent.action.VIEW");
                intent12.setData(Uri.parse("https://www.egeetouch.com/privacy-policy"));
                startActivity(intent12);
            }
        } else if (itemId == R.id.news_dynamic) {
            startActivity(new Intent(this, NewsDynamic.class));
        } else if (itemId == R.id.nav_logout) {
            logout();
        } else if (itemId == R.id.watch_list) {
            startActivity(new Intent(this, WatchListActivity.class));
        } else if (itemId == R.id.nav_dfu_repair) {
            if (getFragmentManager().getBackStackEntryCount() == 0) {
                Intent intent13 = new Intent(this, DfuActivity.class);
                intent13.putExtra("isRepair", true);
                startActivity(intent13);
            } else {
                makeToast("Please disconnect from the lock first");
            }
        }
        ((DrawerLayout) findViewById(R.id.drawer_layout)).closeDrawer(GravityCompat.START);
        return true;
    }

    private void logout() {
        SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit();
        edit.putBoolean("keep_access", false);
        edit.putString("login_email", "");
        edit.putString("login_password", "");
        edit.commit();
        this.mAuth.signOut();
        setResult(30, new Intent());
        startActivity(new Intent(this, login_page_gmail.class));
        finish();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (i != 1) {
            return;
        }
        if (iArr.length > 0 && iArr[0] == 0) {
            location_permission_granted = true;
        } else {
            PermissionUtils.setShouldShowStatus(this, "android.permission.ACCESS_FINE_LOCATION");
        }
    }

    public void enableForegroundMode() {
        mNFCAdapter = NfcAdapter.getDefaultAdapter(this);
        mNFCAdapter.enableForegroundDispatch(this, this.mPendingIntent, new IntentFilter[]{new IntentFilter("android.nfc.action.TAG_DISCOVERED")}, null);
    }

    public void disableForegroundMode() {
        NfcAdapter nfcAdapter = mNFCAdapter;
        if (nfcAdapter != null) {
            nfcAdapter.disableForegroundDispatch(this);
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        String[] split;
        super.onNewIntent(intent);
        Log.i("TAG", "intent.getAction(): " + intent.getAction());
        if ("android.nfc.action.TAG_DISCOVERED".equals(intent.getAction())) {
            Tag tag = (Tag) intent.getParcelableExtra("android.nfc.extra.TAG");
            PICCTag = tag;
            PICCTag_UID = Helper_NFC.ConvertHexByteArrayToString(tag.getId());
            if (look_4_tags) {
                SweetAlertDialog sweetAlertDialog = look_for_nfc_tag;
                if (sweetAlertDialog != null) {
                    sweetAlertDialog.dismissWithAnimation();
                }
                Cursor rawQuery = DatabaseVariable.db_tag.rawQuery(DatabaseVariable.tagdb_number_lock_exist(BLEService.selected_lock_name), null);
                int i = 0;
                boolean z = false;
                String str = "";
                while (rawQuery.moveToNext()) {
                    i++;
                    String[] split2 = PICCTag_UID.split(" ");
                    String[] split3 = rawQuery.getString(1).split(" ");
                    if (split2[1].equals(split3[1]) && split2[2].equals(split3[2])) {
                        str = rawQuery.getString(0);
                        z = true;
                    }
                }
                if (i == 0) {
                    i = 1;
                }
                if (z) {
                    new SweetAlertDialog(context, 3).setTitleText("").setContentText(str + " " + getString(R.string.has_been_added)).setConfirmText(context.getResources().getString(R.string.ok)).show();
                } else {
                    int i2 = 5;
                    if (BLEService.selected_lock_model.equals("GT2000") || BLEService.selected_lock_model.equals("GT3000")) {
                        System.out.println("Hello selected_lock_model1:" + BLEService.selected_lock_model);
                        i2 = 20;
                    } else if (BLEService.selected_lock_model.equals("GT1000") || BLEService.selected_lock_model.equals("GT2002") || BLEService.selected_lock_model.equals("GT2003") || BLEService.selected_lock_model.equals("GT3100")) {
                        System.out.println("Hello selected_lock_model2:" + BLEService.selected_lock_model);
                    }
                    System.out.println("Hello max_tag:" + i2);
                    if (i < i2) {
                        Cursor rawQuery2 = DatabaseVariable.db_tag.rawQuery(DatabaseVariable.tagdb_Query_name_exist("Tag" + String.valueOf(i), BLEService.selected_lock_name), null);
                        while (rawQuery2.moveToNext()) {
                            i++;
                            rawQuery2 = DatabaseVariable.db_tag.rawQuery(DatabaseVariable.tagdb_Query_name_exist("Tag" + String.valueOf(i), BLEService.selected_lock_name), null);
                        }
                        if (PICCTag_UID.split(" ").length < 6) {
                            DatabaseVariable.db_tag.execSQL(DatabaseVariable.tagdb_insert_value("Tag" + String.valueOf(i), PICCTag_UID, BLEService.selected_lock_name));
                        } else {
                            DatabaseVariable.db_tag.execSQL(DatabaseVariable.tagdb_insert_value("Tag" + String.valueOf(i), " " + split[1] + " " + split[2] + " ", BLEService.selected_lock_name));
                        }
                        new SweetAlertDialog(context, 2).setTitleText("").setContentText(str + " " + getString(R.string.new_tag_is_added)).setConfirmText(context.getResources().getString(R.string.ok)).show();
                        ArrayList arrayList = new ArrayList();
                        ArrayList arrayList2 = new ArrayList();
                        ArrayList arrayList3 = new ArrayList();
                        Cursor rawQuery3 = DatabaseVariable.db_tag.rawQuery(DatabaseVariable.tagdb_Query_tag_name_in_lock(BLEService.selected_lock_name), null);
                        while (rawQuery3.moveToNext()) {
                            arrayList.add(rawQuery3.getString(0));
                            arrayList2.add("null");
                            arrayList3.add("cloud");
                        }
                        rawQuery3.close();
                        Fragment_add_tag_nfc.adapter_tag = new ArrayAdapter_ManageTag(this, arrayList, arrayList2, arrayList3);
                        ((ListView) findViewById(R.id.listview_taglist)).setAdapter((ListAdapter) Fragment_add_tag_nfc.adapter_tag);
                    } else {
                        new SweetAlertDialog(context, 3).setTitleText("").setContentText(str + " " + getString(R.string.max_tag_5)).setConfirmText(context.getResources().getString(R.string.ok)).show();
                    }
                }
                look_4_tags = false;
                return;
            }
            byte[] APDUsendSelectAppli = NFCCommandIso14443A.APDUsendSelectAppli(PICCTag);
            if (APDUsendSelectAppli[0] == -112 && APDUsendSelectAppli[1] == 0 && ready_2_pair) {
                transmission_error = false;
                uploaderHandler = new NFCCommandIso14443A();
                StartLoadFromFileTask startLoadFromFileTask = new StartLoadFromFileTask();
                loaddataTask = startLoadFromFileTask;
                startLoadFromFileTask.execute(new Void[0]);
            }
        }
    }

    /* loaded from: classes.dex */
    public class StartLoadFromFileTask extends AsyncTask<Void, Void, Void> {
        private final ProgressDialog dialog;
        private boolean isrunning = false;
        private NFCCommandIso14443A.NFCCommandStatus updateBinStatus;

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Void r1) {
        }

        public StartLoadFromFileTask() {
            this.dialog = new ProgressDialog(MainActivity.this);
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            try {
                MainActivity.this.CRC = crc.CRC(TaskManagement.prepare_send_out_data());
            } catch (IOException e) {
                e.printStackTrace();
            }
            MainActivity.uploaderHandler.init(TaskManagement.prepare_send_out_data());
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(Void... voidArr) {
            this.isrunning = true;
            this.updateBinStatus = NFCCommandIso14443A.NFCCommandStatus.CMD_UPLOADBUFFEREXCEPTIONERR;
            if (MainActivity.this.Verify_RX95_UID(MainActivity.PICCTag_UID)) {
                this.updateBinStatus = MainActivity.uploaderHandler.APDUsendUpdateBinaryNew(false, MainActivity.PICCTag, MainActivity.this.CRC, this);
                return null;
            }
            return null;
        }

        protected boolean getstatus() {
            return this.isrunning;
        }

        @Override // android.os.AsyncTask
        protected void onCancelled() {
            this.isrunning = false;
        }
    }

    public boolean Verify_RX95_UID(String str) {
        return str != null && Helper_NFC.ConvertStringToHexBytesArray(str)[0] == 2;
    }

    private String readText(NdefRecord ndefRecord) throws UnsupportedEncodingException {
        byte[] payload = ndefRecord.getPayload();
        String str = (payload[0] & ByteCompanionObject.MIN_VALUE) == 0 ? "UTF-8" : "UTF-16";
        int i = payload[0] & 51;
        return new String(payload, i + 1, (payload.length - i) - 1, str);
    }

    protected synchronized void buildGoogleApiClient() {
        this.mGoogleApiClient = new GoogleApiClient.Builder(this).addConnectionCallbacks(this).addOnConnectionFailedListener(this).addApi(LocationServices.API).build();
    }

    private boolean checkPlayServices() {
        int isGooglePlayServicesAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (isGooglePlayServicesAvailable != 0) {
            if (GooglePlayServicesUtil.isUserRecoverableError(isGooglePlayServicesAvailable)) {
                GooglePlayServicesUtil.getErrorDialog(isGooglePlayServicesAvailable, this, 9000).show();
                return false;
            } else if (BLEService.selected_lock_model.equals("GT2000") || BLEService.selected_lock_model.equals("GT3000") || BLEService.selected_lock_model.equals("GT2003")) {
                new SweetAlertDialog(context, 3).setTitleText(context.getResources().getString(R.string.setting)).setContentText(context.getResources().getString(R.string.this_device_does_not_support_NFC)).setConfirmText(context.getResources().getString(R.string.ok)).show();
                return false;
            } else {
                return false;
            }
        }
        return true;
    }

    @Override // com.google.android.gms.common.api.internal.OnConnectionFailedListener
    public void onConnectionFailed(ConnectionResult connectionResult) {
        this.googleapi_connected = false;
        Log.i("TAG", "Connection failed: ConnectionResult.getErrorCode() = " + connectionResult.getErrorCode());
    }

    @Override // com.google.android.gms.common.api.internal.ConnectionCallbacks
    public void onConnected(Bundle bundle) {
        this.googleapi_connected = true;
        get_location();
    }

    @Override // com.google.android.gms.common.api.internal.ConnectionCallbacks
    public void onConnectionSuspended(int i) {
        this.mGoogleApiClient.connect();
    }

    public void get_location() {
        if (this.googleapi_connected) {
            if (Build.VERSION.SDK_INT > 21) {
                if (ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") != 0) {
                    ActivityCompat.requestPermissions(this, new String[]{"android.permission.ACCESS_FINE_LOCATION"}, 1);
                    return;
                }
                Location lastLocation = LocationServices.FusedLocationApi.getLastLocation(this.mGoogleApiClient);
                this.mLastLocation = lastLocation;
                if (lastLocation != null) {
                    BLEService.selected_lock_address_latitude = (float) lastLocation.getLatitude();
                    BLEService.selected_lock_address_longitude = (float) this.mLastLocation.getLongitude();
                    return;
                }
                return;
            }
            Location lastLocation2 = LocationServices.FusedLocationApi.getLastLocation(this.mGoogleApiClient);
            this.mLastLocation = lastLocation2;
            if (lastLocation2 != null) {
                BLEService.selected_lock_address_latitude = (float) lastLocation2.getLatitude();
                BLEService.selected_lock_address_longitude = (float) this.mLastLocation.getLongitude();
            }
        }
    }

    public void enable_NFC() {
        if (!getPackageManager().hasSystemFeature("android.hardware.nfc")) {
            new SweetAlertDialog(context, 3).setTitleText(context.getResources().getString(R.string.setting)).setContentText(context.getResources().getString(R.string.this_device_does_not_support_NFC)).setConfirmText(context.getResources().getString(R.string.ok)).show();
            return;
        }
        NfcAdapter nfcAdapter = mNFCAdapter;
        if (nfcAdapter == null || nfcAdapter.isEnabled()) {
            return;
        }
        turn_on_nfc_dialog();
    }

    private void turn_on_nfc_dialog() {
        new SweetAlertDialog(context).setTitleText(context.getResources().getString(R.string.NFC_is_not_turned_on)).setContentText(context.getResources().getString(R.string.click_setting_to_turn_on_NFC)).setConfirmText(context.getResources().getString(R.string.setting)).setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.MainActivity.20
            @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                sweetAlertDialog.dismissWithAnimation();
                MainActivity.this.startActivity(new Intent("android.settings.WIRELESS_SETTINGS"));
            }
        }).setCancelText(context.getResources().getString(R.string.cancel)).setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.MainActivity.19
            @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                sweetAlertDialog.dismissWithAnimation();
            }
        }).show();
    }

    private void show_snackbar(String str) {
        Snackbar make = Snackbar.make(findViewById(R.id.coordinatorLayout), str, 0);
        make.getView().setBackgroundColor(Color.parseColor("#D0389AE0"));
        make.setActionTextColor(Color.parseColor("#FFFFEE19"));
        make.show();
    }

    public void btn_addlock(View view) {
        System.out.println("HEY btn_addlock function run");
        current_icon = 20;
        int i = 1;
        this.add_lock_pressed = true;
        selected_lock_is_shared = false;
        BLEService.skip_verify_password_UI = false;
        Handler handler = new Handler();
        this.mhandler_task = handler;
        handler.post(this.task);
        while (DatabaseVariable.db_lock.rawQuery(DatabaseVariable.Query_lock_db, null).moveToNext()) {
            i++;
        }
        if (i <= 20) {
            fragmentManager.beginTransaction().setCustomAnimations(R.animator.slide_in_right, R.animator.slide_out_left, R.animator.slide_in_left, R.animator.slide_out_right).replace(R.id.container, Fragment_addlock.newInstance()).addToBackStack("AddLock1").commit();
            System.out.println("HEY AddLock1");
        }
    }

    public void btn_admin_access_locks(View view) {
        System.out.println("HEY btn_admin_access_locks pressed!");
        if (Helper_Network.haveNetworkConnection(context)) {
            customProgressBar.ShowProgressBar(context, "pleaseWait");
            SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(this, 5);
            this.pDialog = sweetAlertDialog;
            sweetAlertDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
            this.pDialog.setTitleText(getString(R.string.processing));
            this.pDialog.setCancelable(true);
            myadminLockSerialNum.clear();
            myadminLockModelNum.clear();
            myadminLockName.clear();
            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            this.database = firebaseDatabase;
            firebaseDatabase.getReference("userLocks").child(user_uid).addListenerForSingleValueEvent(new ValueEventListener() { // from class: com.egeetouch.egeetouch_manager.MainActivity.21
                @Override // com.google.firebase.database.ValueEventListener
                public void onCancelled(DatabaseError databaseError) {
                }

                @Override // com.google.firebase.database.ValueEventListener
                public void onDataChange(DataSnapshot dataSnapshot) {
                    MainActivity.number_of_locks = dataSnapshot.getChildrenCount();
                    if (MainActivity.number_of_locks != 0) {
                        int unused = MainActivity.currentLockCountNumber = (int) dataSnapshot.getChildrenCount();
                        for (DataSnapshot dataSnapshot2 : dataSnapshot.getChildren()) {
                            String obj = dataSnapshot2.child("ip45SerialNumber").getValue() != null ? dataSnapshot2.child("ip45SerialNumber").getValue().toString() : "";
                            String obj2 = dataSnapshot2.child("Type").getValue() != null ? dataSnapshot2.child("Type").getValue().toString() : "";
                            String obj3 = dataSnapshot2.child("Name").getValue() != null ? dataSnapshot2.child("Name").getValue().toString() : "";
                            long parseDouble = dataSnapshot2.child("shareStartTime").getValue() != null ? (long) Double.parseDouble(dataSnapshot2.child("shareStartTime").getValue().toString()) : 0L;
                            long parseDouble2 = dataSnapshot2.child("shareEndTime").getValue() != null ? (long) Double.parseDouble(dataSnapshot2.child("shareEndTime").getValue().toString()) : 0L;
                            int i = (parseDouble > 0L ? 1 : (parseDouble == 0L ? 0 : -1));
                            if (i > 0 || parseDouble2 > 0) {
                                if (i == 0 && parseDouble2 < System.currentTimeMillis() / 1000) {
                                    MainActivity.myadminLockSerialNum.add(obj);
                                    MainActivity.myadminLockModelNum.add(obj2);
                                    MainActivity.myadminLockName.add(obj3);
                                } else {
                                    int i2 = (parseDouble2 > 0L ? 1 : (parseDouble2 == 0L ? 0 : -1));
                                    if (i2 == 0 && parseDouble > System.currentTimeMillis() / 100) {
                                        MainActivity.myadminLockSerialNum.add(obj);
                                        MainActivity.myadminLockModelNum.add(obj2);
                                        MainActivity.myadminLockName.add(obj3);
                                    } else if (i > 0 && i2 > 0 && parseDouble < System.currentTimeMillis() / 1000 && parseDouble2 > System.currentTimeMillis() / 1000) {
                                        MainActivity.myadminLockSerialNum.add(obj);
                                        MainActivity.myadminLockModelNum.add(obj2);
                                        MainActivity.myadminLockName.add(obj3);
                                    }
                                }
                            } else {
                                MainActivity.myadminLockSerialNum.add(obj);
                                MainActivity.myadminLockModelNum.add(obj2);
                                MainActivity.myadminLockName.add(obj3);
                            }
                        }
                        MainActivity.this.transfer_data();
                        customProgressBar.closeDialog(0L);
                        return;
                    }
                    customProgressBar.closeDialog(0L);
                    Toast.makeText(MainActivity.context, (int) R.string.no_audittrial_history, 1).show();
                }
            });
            return;
        }
        SweetAlertDialog sweetAlertDialog2 = new SweetAlertDialog(context, 0);
        sweetAlertDialog2.setTitleText(getString(R.string.pls_note));
        sweetAlertDialog2.setContentText(getString(R.string.you_are_not_connected_access_lock));
        sweetAlertDialog2.show();
    }

    public void transfer_data() {
        startActivity(new Intent(this, Activity_access_log_lock_list.class));
    }

    public void btn_sharelock(View view) {
        System.out.println("HEY btn_sharelock function run");
        if (Helper_Network.haveNetworkConnection(context)) {
            shareHistoryLockName.clear();
            shareHistorySharedTo.clear();
            shareHistorySharedOnDate.clear();
            shareHistoryStartDate.clear();
            shareHistoryEndDate.clear();
            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            this.database = firebaseDatabase;
            if (firebase_lock[0] != null) {
                current_icon = 10;
                firebaseDatabase.getReference("userFriendList").child(user_uid).addValueEventListener(new ValueEventListener() { // from class: com.egeetouch.egeetouch_manager.MainActivity.22
                    @Override // com.google.firebase.database.ValueEventListener
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        int unused = MainActivity.current_user_number = 0;
                        MainActivity.number_of_user = 0L;
                        MainActivity.number_of_user = dataSnapshot.getChildrenCount();
                        Log.i("TAG", "number_of_user: " + MainActivity.number_of_user);
                        if (MainActivity.number_of_user == 0) {
                            MainActivity.firebase_user = new String[1];
                            MainActivity.firebase_user_email = new String[1];
                            MainActivity.firebase_user[MainActivity.current_user_number] = MainActivity.this.getResources().getString(R.string.nothing_user);
                            MainActivity.firebase_user_email[MainActivity.current_user_number] = MainActivity.this.getResources().getString(R.string.nothing);
                            MainActivity.fragmentManager.beginTransaction().setCustomAnimations(R.animator.slide_in_right, R.animator.slide_out_left, R.animator.slide_in_left, R.animator.slide_out_right).replace(R.id.container, Fragment_share_lock.newInstance()).addToBackStack("ShareLock").commit();
                            return;
                        }
                        MainActivity.firebase_user = new String[(int) MainActivity.number_of_user];
                        MainActivity.firebase_user_email = new String[(int) MainActivity.number_of_user];
                        for (DataSnapshot dataSnapshot2 : dataSnapshot.getChildren()) {
                            MainActivity.firebase_user[MainActivity.current_user_number] = dataSnapshot2.getKey().toString();
                            MainActivity.firebase_user_email[MainActivity.current_user_number] = dataSnapshot2.getValue().toString();
                            Log.i("TAG", "firebase_user: " + MainActivity.firebase_user[MainActivity.current_user_number]);
                            MainActivity.access$1108();
                            Log.i("Tag", "current_user_number: " + MainActivity.current_user_number);
                            if (MainActivity.number_of_user == MainActivity.current_user_number) {
                                MainActivity.fragmentManager.beginTransaction().setCustomAnimations(R.animator.slide_in_right, R.animator.slide_out_left, R.animator.slide_in_left, R.animator.slide_out_right).replace(R.id.container, Fragment_share_lock.newInstance()).addToBackStack("ShareLock").commit();
                            }
                        }
                    }

                    @Override // com.google.firebase.database.ValueEventListener
                    public void onCancelled(DatabaseError databaseError) {
                        Log.i("TAG", "Firebase error2: " + databaseError.toString());
                    }
                });
                return;
            }
            SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(context, 1);
            sweetAlertDialog.setTitleText(getString(R.string.no_lock_is_added));
            sweetAlertDialog.setContentText(getString(R.string.pls_add_a_lock_first));
            sweetAlertDialog.show();
            return;
        }
        SweetAlertDialog sweetAlertDialog2 = new SweetAlertDialog(context, 0);
        sweetAlertDialog2.setTitleText(getString(R.string.pls_note));
        sweetAlertDialog2.setContentText(getString(R.string.you_are_not_connected_share_lock));
        sweetAlertDialog2.show();
    }

    public void btn_sharelock_submit(View view) {
        System.out.println("HEY you are sending a " + selected_share_lock_model);
        if (selected_share_lock_model.equals("GT1000") || selected_share_lock_model.equals("GT2002") || selected_share_lock_model.equals("GT2100") || selected_share_lock_model.equals("GT3000") || BLEService.selected_lock_model.equals("GT5100") || BLEService.selected_lock_model.equals("GT5107") || BLEService.selected_lock_model.equals("GT5109") || BLEService.selected_lock_model.equals("GT5300")) {
            if (selected_share_email.equals(getResources().getString(R.string.nothing))) {
                SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(context, 1);
                sweetAlertDialog.setTitleText(getString(R.string.no_friend_is_added));
                sweetAlertDialog.setContentText(getString(R.string.pls_add_a_friend));
                sweetAlertDialog.show();
                return;
            }
            Cursor rawQuery = DatabaseVariable.db_lock.rawQuery(DatabaseVariable.lockdb_Query_model(selected_share_lock), null);
            while (rawQuery.moveToNext()) {
                selected_share_lock_password = rawQuery.getString(1);
                selected_share_lock_model = rawQuery.getString(2);
                selected_share_lock_version = rawQuery.getString(3);
                selected_share_lock_lastBatt = rawQuery.getString(4);
                selected_share_lock_serial = rawQuery.getString(5);
                selected_share_lock_MACAddress = rawQuery.getString(6);
                FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                selected_share_lock_sharedByEmail = currentUser != null ? currentUser.getEmail() : "Unknown Email Address";
                selected_share_lock_ip45SerialNumber = rawQuery.getString(5);
                if (selected_share_lock_model.equals("GT2100")) {
                    selected_share_lock_Type = 4;
                } else if (selected_share_lock_model.equals("GT2002")) {
                    selected_share_lock_Type = 3;
                } else if (selected_share_lock_model.equals("GT1000")) {
                    selected_share_lock_Type = 2;
                } else if (selected_share_lock_model.equals("GT3100")) {
                    selected_share_lock_Type = 1;
                } else if (selected_share_lock_model.equals("GT5100")) {
                    selected_share_lock_Type = 7;
                } else if (selected_share_lock_model.equals("GT5107")) {
                    selected_share_lock_Type = 8;
                } else if (selected_share_lock_model.equals("GT5109")) {
                    selected_share_lock_Type = 9;
                } else if (selected_share_lock_model.equals("GT5300")) {
                    selected_share_lock_Type = 7;
                } else {
                    selected_share_lock_Type = 0;
                }
                Log.i("TAG", "selected_share_lock_password: " + selected_share_lock_password + " selected_share_lock_model: " + selected_share_lock_model + " selected_share_lock_version: " + selected_share_lock_version + " selected_share_lock_lastBatt: " + selected_share_lock_lastBatt + " selected_share_lock_MACAddress: " + selected_share_lock_MACAddress + "selected_share_lock_Type: " + selected_share_lock_Type);
            }
            rawQuery.close();
            FirebaseUser currentUser2 = FirebaseAuth.getInstance().getCurrentUser();
            currentUser2.getEmail();
            if (selected_share_email.equals(currentUser2.getEmail())) {
                Toast.makeText(this, getString(R.string.you_already_have_this_lock), 1).show();
                return;
            }
            String string = getString(R.string.once_shared_cannot_retract);
            if (selected_share_lock_retractable) {
                string = getString(R.string.user_has_to_be_online_to_access);
            }
            SweetAlertDialog sweetAlertDialog2 = new SweetAlertDialog(context, 0);
            sweetAlertDialog2.setTitleText(getString(R.string.pls_confirm));
            sweetAlertDialog2.setContentText(getString(R.string.are_you_sure_you_want_to_share) + ":\n" + selected_share_lock + "\n" + getString(R.string.with) + "\n" + selected_share_email + string);
            sweetAlertDialog2.setConfirmText(getString(R.string.yes));
            sweetAlertDialog2.setConfirmClickListener(new AnonymousClass23());
            sweetAlertDialog2.setCancelText(getString(R.string.cancel));
            sweetAlertDialog2.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.MainActivity.24
                @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                public void onClick(SweetAlertDialog sweetAlertDialog3) {
                    sweetAlertDialog3.dismissWithAnimation();
                }
            });
            sweetAlertDialog2.show();
            return;
        }
        SweetAlertDialog sweetAlertDialog3 = new SweetAlertDialog(context, 0);
        sweetAlertDialog3.setTitleText(getString(R.string.pls_note));
        sweetAlertDialog3.setContentText(getString(R.string.lock_model_not_supported));
        sweetAlertDialog3.show();
    }

    /* renamed from: com.egeetouch.egeetouch_manager.MainActivity$23  reason: invalid class name */
    /* loaded from: classes.dex */
    class AnonymousClass23 implements SweetAlertDialog.OnSweetClickListener {
        AnonymousClass23() {
        }

        @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
        public void onClick(SweetAlertDialog sweetAlertDialog) {
            sweetAlertDialog.dismissWithAnimation();
            final SweetAlertDialog sweetAlertDialog2 = new SweetAlertDialog(MainActivity.context, 5);
            sweetAlertDialog2.setTitleText("");
            sweetAlertDialog2.setContentText(MainActivity.this.getString(R.string.pls_wait_sending_data_to_ur_friend));
            sweetAlertDialog2.setCancelText(MainActivity.this.getString(R.string.cancel));
            sweetAlertDialog2.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.MainActivity.23.1
                @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                public void onClick(SweetAlertDialog sweetAlertDialog3) {
                    sweetAlertDialog3.dismissWithAnimation();
                }
            });
            sweetAlertDialog2.setCancelable(false);
            sweetAlertDialog2.show();
            MainActivity.this.registered_found = false;
            MainActivity.currentTimestamp = String.valueOf(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()));
            long parseLong = Long.parseLong(MainActivity.currentTimestamp);
            Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
            calendar.setTimeInMillis(parseLong * 1000);
            MainActivity.currentTimeStampFormatted = DateFormat.format("MMM dd, HH:mm", calendar).toString();
            MainActivity.this.database.getReference("registeredUsersOnPlatform").addListenerForSingleValueEvent(new ValueEventListener() { // from class: com.egeetouch.egeetouch_manager.MainActivity.23.2
                @Override // com.google.firebase.database.ValueEventListener
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Log.i("Tag", "selected_share_email: " + MainActivity.selected_share_email);
                    for (DataSnapshot dataSnapshot2 : dataSnapshot.getChildren()) {
                        if (dataSnapshot2.getValue().toString().equals(Helper_Firebase.DecodeString(MainActivity.selected_share_email))) {
                            MainActivity.this.registered_found = true;
                            final String str = dataSnapshot2.getKey().toString();
                            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                            DatabaseReference push = firebaseDatabase.getReference("MyShareHistory").child(MainActivity.user_uid).push();
                            push.child("lockName").setValue(MainActivity.selected_share_lock);
                            push.child("sharedTo").setValue(MainActivity.selected_share_email);
                            DatabaseReference child = push.child("sharedOn");
                            child.setValue(MainActivity.currentTimeStampFormatted);
                            MainActivity.share_access_token_from_admin = String.valueOf(child.push().getKey());
                            if (MainActivity.selected_share_lock_startTime != FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE || MainActivity.selected_share_lock_endTime != FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                                Calendar calendar2 = Calendar.getInstance(Locale.ENGLISH);
                                calendar2.setTimeInMillis(((long) MainActivity.selected_share_lock_startTime) * 1000);
                                push.child("startDate").setValue(DateFormat.format("MMM dd yyyy, HH:mm", calendar2).toString());
                                Calendar calendar3 = Calendar.getInstance(Locale.ENGLISH);
                                calendar3.setTimeInMillis(((long) MainActivity.selected_share_lock_endTime) * 1000);
                                push.child("endDate").setValue(DateFormat.format("MMM dd yyyy, HH:mm", calendar3).toString());
                            } else {
                                push.child("startDate").setValue("");
                                push.child("endDate").setValue("");
                            }
                            if (MainActivity.selected_share_lock_retractable) {
                                DatabaseReference push2 = firebaseDatabase.getReference("retractableSharingRegistry").child(MainActivity.user_uid).push();
                                push2.child("allowAccess").setValue(true);
                                push2.child("id").setValue(push2.getKey());
                                push2.child("lockSerial").setValue(MainActivity.selected_share_lock_ip45SerialNumber);
                                push2.child("senderUID").setValue(MainActivity.user_uid);
                                push2.child("shareAccessToken").setValue(MainActivity.share_access_token_from_admin);
                                System.out.println("Hello access toke:" + MainActivity.share_access_token_from_admin);
                                push2.child("sharedLockName").setValue(MainActivity.selected_share_lock);
                                push2.child("sharedOn").setValue(MainActivity.currentTimestamp);
                                push2.child("sharedToEmail").setValue(MainActivity.selected_share_email);
                                push2.child("sharedToUID").setValue(str);
                                push2.child("shareEndTime").setValue(Double.valueOf(MainActivity.selected_share_lock_endTime));
                                push2.child("shareStartTime").setValue(Double.valueOf(MainActivity.selected_share_lock_startTime));
                            }
                            firebaseDatabase.getReference("userLocks").child(dataSnapshot2.getKey()).orderByKey().addListenerForSingleValueEvent(new ValueEventListener() { // from class: com.egeetouch.egeetouch_manager.MainActivity.23.2.1
                                @Override // com.google.firebase.database.ValueEventListener
                                public void onDataChange(DataSnapshot dataSnapshot3) {
                                    DatabaseReference push3 = FirebaseDatabase.getInstance().getReference("userLocks").child(str).push();
                                    push3.child("Name").setValue(MainActivity.selected_share_lock);
                                    push3.child("Type").setValue(Integer.valueOf(MainActivity.selected_share_lock_Type));
                                    push3.child("Password").setValue(MainActivity.this.encryptPassword(MainActivity.selected_share_lock_password));
                                    push3.child("ip45SerialNumber").setValue(MainActivity.selected_share_lock_ip45SerialNumber);
                                    push3.child("shareStartTime").setValue(Double.valueOf(MainActivity.selected_share_lock_startTime));
                                    push3.child("shareEndTime").setValue(Double.valueOf(MainActivity.selected_share_lock_endTime));
                                    push3.child("sharedBy").setValue(MainActivity.selected_share_lock_sharedByEmail);
                                    push3.child("shareAccessToken").setValue(MainActivity.share_access_token_from_admin);
                                    DatabaseReference child2 = push3.child("isOfflineLock");
                                    if (MainActivity.selected_share_lock_retractable) {
                                        child2.setValue(false);
                                    } else {
                                        child2.setValue(true);
                                    }
                                    sweetAlertDialog2.dismissWithAnimation();
                                    new SweetAlertDialog(MainActivity.context, 2);
                                }

                                @Override // com.google.firebase.database.ValueEventListener
                                public void onCancelled(DatabaseError databaseError) {
                                    sweetAlertDialog2.dismissWithAnimation();
                                    Log.e("TAG", "onCancelled", databaseError.toException());
                                }
                            });
                        }
                    }
                    if (MainActivity.this.registered_found.equals(true)) {
                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Temp_DB");
                        Log.i("Tag", "selected_share_email: " + Helper_Firebase.EncodeString(MainActivity.selected_share_email));
                        reference.child(Helper_Firebase.EncodeString(MainActivity.selected_share_email)).child(MainActivity.user_uid).orderByKey().addListenerForSingleValueEvent(new ValueEventListener() { // from class: com.egeetouch.egeetouch_manager.MainActivity.23.2.2
                            @Override // com.google.firebase.database.ValueEventListener
                            public void onDataChange(DataSnapshot dataSnapshot3) {
                                int i = 0;
                                if (dataSnapshot3.getChildrenCount() != 0) {
                                    for (DataSnapshot dataSnapshot4 : dataSnapshot3.getChildren()) {
                                        int intValue = Integer.valueOf(dataSnapshot4.getKey()).intValue();
                                        Log.i("Tag", "snap.getKey(): " + dataSnapshot4.getKey());
                                        i = intValue;
                                    }
                                }
                                int i2 = i + 1;
                                Log.i("Tag", "last_lock_num: " + i2);
                                while (dataSnapshot3.hasChild(String.valueOf(i2))) {
                                    i2++;
                                }
                                DatabaseReference child2 = FirebaseDatabase.getInstance().getReference("Temp_DB").child(Helper_Firebase.EncodeString(MainActivity.selected_share_email)).child(String.valueOf(i2)).child("Lock");
                                child2.child("LockName").setValue(MainActivity.selected_share_lock);
                                child2.child("LockModel").setValue(MainActivity.selected_share_lock_model);
                                child2.child("LockPassword").setValue(MainActivity.selected_share_lock_password);
                                child2.child("LockVersion").setValue(MainActivity.selected_share_lock_version);
                                child2.child("LockLastBatt").setValue(MainActivity.selected_share_lock_lastBatt);
                                child2.child("UserRole").setValue(MainActivity.selected_share_role);
                                child2.child("LockIdentifier").setValue("");
                                child2.child("LockMACAddress").setValue(MainActivity.selected_share_lock_MACAddress);
                                sweetAlertDialog2.dismissWithAnimation();
                                SweetAlertDialog sweetAlertDialog3 = new SweetAlertDialog(MainActivity.context, 2);
                                sweetAlertDialog3.setTitleText(MainActivity.this.getString(R.string.done));
                                sweetAlertDialog3.setContentText(MainActivity.this.getString(R.string.successfully_share_to_ur_friend));
                                sweetAlertDialog3.setConfirmText(MainActivity.this.getString(R.string.dismiss));
                                sweetAlertDialog3.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.MainActivity.23.2.2.1
                                    @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                                    public void onClick(SweetAlertDialog sweetAlertDialog4) {
                                        sweetAlertDialog4.dismissWithAnimation();
                                    }
                                });
                                sweetAlertDialog3.show();
                            }

                            @Override // com.google.firebase.database.ValueEventListener
                            public void onCancelled(DatabaseError databaseError) {
                                sweetAlertDialog2.dismissWithAnimation();
                                Log.i("TAG", "Firebase error1: " + databaseError.toString());
                            }
                        });
                        return;
                    }
                    sweetAlertDialog2.dismissWithAnimation();
                    Toast.makeText(MainActivity.this, MainActivity.this.getString(R.string.email_not_found), 1).show();
                }

                @Override // com.google.firebase.database.ValueEventListener
                public void onCancelled(DatabaseError databaseError) {
                    sweetAlertDialog2.dismissWithAnimation();
                    Log.e("TAG", "onCancelled", databaseError.toException());
                }
            });
        }
    }

    public void btn_choose_duration(View view) {
        System.out.println("HEY btn_choose_duration is getting clicked");
        startActivity(new Intent(this, ChooseDurationActivity.class));
    }

    public void btn_share_history(View view) {
        System.out.println("HEY btn_share_history button clicked");
        if (Helper_Network.haveNetworkConnection(context)) {
            shareHistoryLockName.clear();
            shareHistorySharedTo.clear();
            shareHistorySharedOnDate.clear();
            shareHistoryStartDate.clear();
            shareHistoryEndDate.clear();
            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            this.database = firebaseDatabase;
            firebaseDatabase.getReference("MyShareHistory").child(user_uid).addListenerForSingleValueEvent(new ValueEventListener() { // from class: com.egeetouch.egeetouch_manager.MainActivity.25
                @Override // com.google.firebase.database.ValueEventListener
                public void onCancelled(DatabaseError databaseError) {
                }

                @Override // com.google.firebase.database.ValueEventListener
                public void onDataChange(DataSnapshot dataSnapshot) {
                    MainActivity.numberOfShareHistory = dataSnapshot.getChildrenCount();
                    if (MainActivity.numberOfShareHistory != 0) {
                        MainActivity.this.loading_dialog = new SweetAlertDialog(MainActivity.this, 5);
                        MainActivity.this.loading_dialog.setTitleText(MainActivity.this.getString(R.string.loading));
                        MainActivity.this.loading_dialog.show();
                        MainActivity.this.loading_dialog.setCancelable(false);
                        System.out.println("HEY numberOfShareHistory " + MainActivity.numberOfShareHistory);
                        int unused = MainActivity.currentShareHistoryCounterNumber = 0;
                        System.out.println("HEY currentShareHistoryCounterNumber is starting at  " + MainActivity.currentShareHistoryCounterNumber);
                        System.out.println("HEY currentShareHistoryCounterNumber is at  " + MainActivity.currentShareHistoryCounterNumber);
                        for (DataSnapshot dataSnapshot2 : dataSnapshot.getChildren()) {
                            MainActivity.this.database.getReference("MyShareHistory").child(MainActivity.user_uid).child(dataSnapshot2.getKey()).addListenerForSingleValueEvent(new ValueEventListener() { // from class: com.egeetouch.egeetouch_manager.MainActivity.25.1
                                @Override // com.google.firebase.database.ValueEventListener
                                public void onCancelled(DatabaseError databaseError) {
                                }

                                @Override // com.google.firebase.database.ValueEventListener
                                public void onDataChange(DataSnapshot dataSnapshot3) {
                                    for (DataSnapshot dataSnapshot4 : dataSnapshot3.getChildren()) {
                                        if (dataSnapshot4.getKey().equals("lockName")) {
                                            System.out.println("HEY lockName " + dataSnapshot4.getValue().toString());
                                            System.out.println("HEY adding lockName to index: " + MainActivity.currentShareHistoryCounterNumber);
                                            MainActivity.shareHistoryLockName.add(MainActivity.currentShareHistoryCounterNumber, dataSnapshot4.getValue().toString());
                                        } else if (dataSnapshot4.getKey().equals("sharedOn")) {
                                            System.out.println("HEY sharedOn " + dataSnapshot4.getValue().toString());
                                            MainActivity.shareHistorySharedOnDate.add(MainActivity.currentShareHistoryCounterNumber, dataSnapshot4.getValue().toString());
                                            System.out.println("HEY adding sharedOn to index: " + MainActivity.currentShareHistoryCounterNumber);
                                        } else if (dataSnapshot4.getKey().equals("sharedTo")) {
                                            System.out.println("HEY sharedTo " + dataSnapshot4.getValue().toString());
                                            MainActivity.shareHistorySharedTo.add(MainActivity.currentShareHistoryCounterNumber, dataSnapshot4.getValue().toString());
                                            System.out.println("HEY adding sharedTo to index: " + MainActivity.currentShareHistoryCounterNumber);
                                        } else if (dataSnapshot4.getKey().equals("startDate")) {
                                            System.out.println("HEY startDate " + dataSnapshot4.getValue().toString());
                                            MainActivity.shareHistoryStartDate.add(MainActivity.currentShareHistoryCounterNumber, dataSnapshot4.getValue().toString());
                                            System.out.println("HEY adding startDate to index: " + MainActivity.currentShareHistoryCounterNumber);
                                        } else if (dataSnapshot4.getKey().equals("endDate")) {
                                            System.out.println("HEY endDate " + dataSnapshot4.getValue().toString());
                                            MainActivity.shareHistoryEndDate.add(MainActivity.currentShareHistoryCounterNumber, dataSnapshot4.getValue().toString());
                                            System.out.println("HEY adding endDate to index: " + MainActivity.currentShareHistoryCounterNumber);
                                        }
                                        System.out.println("HEY currentShareHistoryCounterNumber " + MainActivity.currentShareHistoryCounterNumber);
                                    }
                                    MainActivity.access$1208();
                                    if (MainActivity.currentShareHistoryCounterNumber == MainActivity.numberOfShareHistory) {
                                        MainActivity.this.loading_dialog.dismissWithAnimation();
                                        MainActivity.fragmentManager.beginTransaction().setCustomAnimations(R.animator.slide_in_right, R.animator.slide_out_left, R.animator.slide_in_left, R.animator.slide_out_right).replace(R.id.container, Fragment_share_history.newInstance()).addToBackStack("ShareHistoryList").commit();
                                    }
                                }
                            });
                        }
                        return;
                    }
                    MainActivity.this.loading_dialog.dismissWithAnimation();
                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(MainActivity.context, 0);
                    sweetAlertDialog.setTitleText(MainActivity.this.getString(R.string.pls_note));
                    sweetAlertDialog.setContentText(MainActivity.this.getString(R.string.no_audittrial_history));
                    sweetAlertDialog.show();
                }
            });
            return;
        }
        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(context, 0);
        sweetAlertDialog.setTitleText(getString(R.string.pls_note));
        sweetAlertDialog.setContentText(getString(R.string.internet_is_not_available));
        sweetAlertDialog.show();
    }

    public void btn_retract_access(View view) {
        if (Helper_Network.haveNetworkConnection(this)) {
            startActivity(new Intent(view.getContext(), Activity_retract_access.class));
            return;
        }
        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(context, 0);
        sweetAlertDialog.setTitleText(getString(R.string.pls_note));
        sweetAlertDialog.setContentText(getString(R.string.you_are_not_connected_access_lock));
        sweetAlertDialog.show();
    }

    public void btn_share_add_user(View view) {
        fragmentManager.beginTransaction().setCustomAnimations(R.animator.slide_in_right, R.animator.slide_out_left, R.animator.slide_in_left, R.animator.slide_out_right).replace(R.id.container, Fragment_share_userList.newInstance()).addToBackStack("ShareUserList").commit();
    }

    public void btn_share_add_new_user(View view) {
        hideKeyboard(view);
        EditText editText = (EditText) findViewById(R.id.editText_user_name);
        EditText editText2 = (EditText) findViewById(R.id.editText_user_email);
        if (editText == null || editText2 == null) {
            return;
        }
        if (editText.getText().toString().equals("")) {
            editText.setError(getString(R.string.please_enter_user_name));
        }
        if (editText2.getText().toString().equals("")) {
            editText2.setError(getString(R.string.please_enter_user_email));
        } else if (editText.getText().toString().equals("") || editText2.getText().toString().equals("")) {
        } else {
            if (Helper_EmailValidation.isEmailValid(editText2.getText().toString())) {
                String obj = editText.getText().toString();
                FirebaseDatabase.getInstance().getReference("userFriendList").child(user_uid).child(obj).setValue(editText2.getText().toString());
                ((LinearLayout) findViewById(R.id.LinearLayout_add_user)).setVisibility(8);
                editText.setText("");
                editText2.setText("");
                ((LinearLayout) findViewById(R.id.LinearLayout_add_user)).setVisibility(4);
                ListView listView = (ListView) findViewById(R.id.listview_userlist);
                listView.setAlpha(1.0f);
                listView.setClickable(true);
                ((ImageView) findViewById(R.id.imageView_add_user)).setVisibility(0);
                return;
            }
            editText2.setError(getString(R.string.please_enter_an_valid_email));
        }
    }

    public void btn_save_pair(View view) {
        EditText editText = (EditText) findViewById(R.id.editText_lockname2);
        EditText editText2 = (EditText) findViewById(R.id.editText_primarypassword2);
        ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 2);
        if (!editText.getText().toString().equals("")) {
            if (!editText2.getText().toString().equals("")) {
                if (editText2.getText().length() >= 6) {
                    int i = 1;
                    while (DatabaseVariable.db_lock.rawQuery(DatabaseVariable.Query_lock_db, null).moveToNext()) {
                        i++;
                    }
                    if (i <= 20) {
                        String obj = editText.getText().toString();
                        String obj2 = editText2.getText().toString();
                        ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 2);
                        if (DatabaseVariable.db_lock.rawQuery(DatabaseVariable.lockdb_Query_name_exist(obj), null).moveToNext()) {
                            Toast.makeText(this, getString(R.string.lock_name_duplicated), 1).show();
                            return;
                        }
                        if (!getPackageManager().hasSystemFeature("android.hardware.nfc")) {
                            Toast.makeText(this, getString(R.string.this_device_does_not_support_NFC), 1).show();
                        } else {
                            NfcAdapter defaultAdapter = NfcAdapter.getDefaultAdapter(this);
                            mNFCAdapter = defaultAdapter;
                            if (defaultAdapter != null && defaultAdapter.isEnabled()) {
                                this.mPendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, getClass()).addFlags(603979776), 33554432);
                            } else {
                                Toast.makeText(this, getString(R.string.NFC_is_not_turned_on), 1).show();
                                turn_on_nfc_dialog();
                            }
                        }
                        System.out.println("checking selected lock " + BLEService.selected_lock_model);
                        DatabaseVariable.db_lock.execSQL(this.mDatabaseVariable.lockdb_insert_value(obj, obj2, "model1", AppEventsConstants.EVENT_PARAM_VALUE_NO, AppEventsConstants.EVENT_PARAM_VALUE_NO, BLEService.selectedLockIP45Serial, AppEventsConstants.EVENT_PARAM_VALUE_NO, AppEventsConstants.EVENT_PARAM_VALUE_NO, AppEventsConstants.EVENT_PARAM_VALUE_NO, AppEventsConstants.EVENT_PARAM_VALUE_NO, "", null, null));
                        System.out.println("HEY adding lock!");
                        BLEService.selected_lock_name = obj;
                        TaskManagement.password_varified = false;
                        TaskManagement.password_varified_correct = false;
                        TaskManagement.current_function = 48;
                        fragmentManager.beginTransaction().replace(R.id.container, Fragment_pairing.newInstance()).addToBackStack("20").commit();
                        this.add_lock_pressed = false;
                        return;
                    }
                    Toast.makeText(this, getString(R.string.max_lock), 1).show();
                    return;
                }
                editText2.setError(view.getResources().getString(R.string.password_length));
                return;
            }
            editText2.setError(view.getResources().getString(R.string.please_key_in_password));
            return;
        }
        editText.setError(view.getResources().getString(R.string.please_enter_lock_name));
    }

    public void btn_lock(View view) {
        Boolean bool = false;
        Helper_PhoneDetails.get_location(context);
        Fragment_BLE.count_down_started = false;
        Fragment_BLE.idle_count_down.cancel();
        if (!BLEService.selected_lock_state && (BLEService.selected_lock_model.equals("GT2002") || BLEService.selected_lock_model.equals("GT2003"))) {
            new SweetAlertDialog(context).setTitleText(context.getResources().getString(R.string.to_lock)).setContentText(context.getResources().getString(R.string.to_lock_press_the_shackle_down_firmly_in_its_lock_position)).setConfirmText(context.getResources().getString(R.string.ok)).show();
        }
        if (bool.booleanValue()) {
            SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(context, 5);
            unlocking_pd = sweetAlertDialog;
            sweetAlertDialog.setTitleText("");
            unlocking_pd.setContentText("");
            unlocking_pd.setCancelText(getString(R.string.cancel));
            unlocking_pd.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.MainActivity.26
                @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                public void onClick(SweetAlertDialog sweetAlertDialog2) {
                    sweetAlertDialog2.dismissWithAnimation();
                    try {
                        if (BLEService.selected_lock_state) {
                            UI_BLE.state = 1;
                            UI_BLE.BLE_UI = 14;
                        } else {
                            UI_BLE.state = 3;
                            UI_BLE.BLE_UI = 14;
                        }
                        BLEService.Ble_Mode = 0;
                    } catch (Exception unused) {
                    }
                }
            });
            unlocking_pd.setCancelable(false);
            unlocking_pd.show();
        }
        ((Vibrator) getSystemService("vibrator")).vibrate(50L);
        if (BLEService.selected_lock_state) {
            MediaPlayer create = MediaPlayer.create(this, (int) R.raw.on);
            create.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: com.egeetouch.egeetouch_manager.MainActivity.27
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
            return;
        }
        MediaPlayer create2 = MediaPlayer.create(this, (int) R.raw.off);
        create2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: com.egeetouch.egeetouch_manager.MainActivity.28
            @Override // android.media.MediaPlayer.OnCompletionListener
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.stop();
                mediaPlayer.reset();
                mediaPlayer.release();
            }
        });
        create2.start();
        if (BLEService.selected_lock_model.equals("GT2002") || BLEService.selected_lock_model.equals("GT2003")) {
            return;
        }
        Log.i("", "Locking the lock...");
        BLEService.selected_lock_state = true;
        UI_BLE.state = 4;
        UI_BLE.BLE_UI = 14;
        BLEService.Ble_Mode = 105;
    }

    public void btn_unlock_ble(View view) {
        Boolean bool = false;
        get_location();
        System.out.println("Hello btn_unlock_ble!!");
        Fragment_BLE.count_down_started = false;
        Fragment_BLE.idle_count_down.cancel();
        if (!BLEService.selected_lock_state && BLEService.selected_lock_model.equals("GT2002")) {
            new SweetAlertDialog(context).setTitleText(context.getResources().getString(R.string.to_lock)).setContentText(context.getResources().getString(R.string.to_lock_press_the_shackle_down_firmly_in_its_lock_position)).setConfirmText(context.getResources().getString(R.string.ok)).show();
        }
        if (bool.booleanValue()) {
            SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(context, 5);
            unlocking_pd = sweetAlertDialog;
            sweetAlertDialog.setTitleText("");
            unlocking_pd.setContentText("");
            unlocking_pd.setCancelText(getString(R.string.cancel));
            unlocking_pd.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.MainActivity.29
                @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                public void onClick(SweetAlertDialog sweetAlertDialog2) {
                    sweetAlertDialog2.dismissWithAnimation();
                    try {
                        if (BLEService.selected_lock_state) {
                            UI_BLE.state = 1;
                            UI_BLE.BLE_UI = 14;
                        } else {
                            UI_BLE.state = 3;
                            UI_BLE.BLE_UI = 14;
                        }
                        BLEService.Ble_Mode = 0;
                    } catch (Exception unused) {
                    }
                }
            });
            unlocking_pd.setCancelable(false);
            unlocking_pd.show();
        }
        ((Vibrator) getSystemService("vibrator")).vibrate(50L);
        if (BLEService.selected_lock_state) {
            MediaPlayer create = MediaPlayer.create(this, (int) R.raw.on);
            create.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: com.egeetouch.egeetouch_manager.MainActivity.30
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
            BLEService.Ble_Mode = 98;
            return;
        }
        MediaPlayer create2 = MediaPlayer.create(this, (int) R.raw.off);
        create2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: com.egeetouch.egeetouch_manager.MainActivity.31
            @Override // android.media.MediaPlayer.OnCompletionListener
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.stop();
                mediaPlayer.reset();
                mediaPlayer.release();
            }
        });
        create2.start();
        if (BLEService.selected_lock_model.equals("GT2002") || BLEService.selected_lock_model.equals("GT2002")) {
            return;
        }
        Log.i("", "Locking the lock...");
        BLEService.selected_lock_state = true;
        if (BLEService.selected_lock_model.equals("GT2100") && BLEService.shackleByPassOnce_isEnabled) {
            System.out.println("Hello checking the Request_Lock_Setting_Param !");
            BLEService.Ble_Mode = 113;
            return;
        }
        BLEService.Ble_Mode = 105;
    }

    public void btn_lock_icon(View view) {
        System.out.println("Hello checking the current_icon : " + current_icon);
        if (Fragment_lock_setting.initial_setup) {
            UI_BLE.BLE_UI = 20;
            this.ui_ble.update();
            return;
        }
        BLEService.vicinity_on = false;
        int i = current_icon;
        if (i == 1 || i == 2) {
            if (i == 2) {
                current_icon = 1;
                getSupportActionBar().setTitle(R.string.lock_access);
                return;
            }
            return;
        }
        current_icon = 1;
        getSupportActionBar().setTitle(R.string.lock_access);
        fragmentManager.beginTransaction().replace(R.id.container, Fragment_BLE.newInstance()).addToBackStack("22").commit();
    }

    public void btn_proximity(View view) {
        shutdown_status = 0;
        if (BLEService.selected_lock_role == 0) {
            if (Fragment_lock_setting.initial_setup) {
                UI_BLE.BLE_UI = 20;
                this.ui_ble.update();
                return;
            }
            BLEService.vicinity_on = true;
            int i = current_icon;
            if (i == 1 || i == 2) {
                if (i == 1) {
                    MediaPlayer create = MediaPlayer.create(this, (int) R.raw.vicinity);
                    create.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: com.egeetouch.egeetouch_manager.MainActivity.34
                        @Override // android.media.MediaPlayer.OnCompletionListener
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            mediaPlayer.stop();
                            mediaPlayer.reset();
                            mediaPlayer.release();
                        }
                    });
                    create.start();
                    current_icon = 2;
                    getSupportActionBar().setTitle(R.string.vicinity_tracking);
                    return;
                }
                return;
            }
            current_icon = 2;
            MediaPlayer create2 = MediaPlayer.create(this, (int) R.raw.vicinity);
            create2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: com.egeetouch.egeetouch_manager.MainActivity.33
                @Override // android.media.MediaPlayer.OnCompletionListener
                public void onCompletion(MediaPlayer mediaPlayer) {
                    mediaPlayer.stop();
                    mediaPlayer.reset();
                    mediaPlayer.release();
                }
            });
            create2.start();
            Fragment_BLE.stop_count_down_timer();
            getSupportActionBar().setTitle(R.string.vicinity_tracking);
            fragmentManager.beginTransaction().replace(R.id.container, Fragment_BLE.newInstance()).addToBackStack("22").commit();
            return;
        }
        new SweetAlertDialog(context, 3).setTitleText(context.getResources().getString(R.string.user_logged_in)).setContentText(context.getResources().getString(R.string.please_login_as_admin)).setConfirmText(context.getResources().getString(R.string.ok)).show();
    }

    public void btn_proximity_sound(View view) {
        shutdown_status = 0;
        if (BLEService.proximity_sound == 1) {
            ((Vibrator) getSystemService("vibrator")).vibrate(100L);
            BLEService.proximity_sound = 2;
        } else if (BLEService.proximity_sound == 2) {
            BLEService.proximity_sound = 3;
        } else if (BLEService.proximity_sound == 3) {
            BLEService.proximity_sound = 1;
        }
    }

    public void btn_watch(View view) {
        shutdown_status = 0;
        if (BLEService.selected_lock_role == 0) {
            if (Fragment_lock_setting.initial_setup) {
                UI_BLE.BLE_UI = 20;
                this.ui_ble.update();
                return;
            } else if (current_icon != 8) {
                if (!watch_connected) {
                    new SweetAlertDialog(context, 0).setTitleText(context.getResources().getString(R.string.smartwatch)).setContentText(context.getResources().getString(R.string.your_smartwatch_is_not_paired)).setConfirmText(context.getResources().getString(R.string.ok)).setCancelText(context.getResources().getString(R.string.learn_more)).setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.MainActivity.35
                        @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            sweetAlertDialog.dismissWithAnimation();
                            new SweetAlertDialog(MainActivity.context, 0).setTitleText(MainActivity.context.getResources().getString(R.string.smartwatch)).setContentText(MainActivity.context.getResources().getString(R.string.Launch)).setConfirmText(MainActivity.context.getResources().getString(R.string.ok)).show();
                        }
                    }).show();
                    return;
                } else {
                    new SweetAlertDialog(context, 4).setCustomImage(R.drawable.gear2_watch).setTitleText(context.getResources().getString(R.string.smartwatch)).setContentText(context.getResources().getString(R.string.your_smartwatch_is_paired)).setConfirmText(context.getResources().getString(R.string.ok)).show();
                    return;
                }
            } else {
                return;
            }
        }
        new SweetAlertDialog(context, 3).setTitleText(context.getResources().getString(R.string.user_logged_in)).setContentText(context.getResources().getString(R.string.please_login_as_admin)).setConfirmText(context.getResources().getString(R.string.ok)).show();
    }

    public void btn_audit_trail(View view) {
        if (!Helper_Network.haveNetworkConnection(this) && !BLEService.selected_lock_model.equals("GT1000")) {
            SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(this, 1);
            sweetAlertDialog.setTitleText(getString(R.string.no_connection));
            sweetAlertDialog.setContentText(getString(R.string.no_internet_bluetooth));
            sweetAlertDialog.setConfirmText(getString(R.string.ok));
            sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.MainActivity.36
                @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                public void onClick(SweetAlertDialog sweetAlertDialog2) {
                    sweetAlertDialog2.dismissWithAnimation();
                }
            });
            sweetAlertDialog.show();
        } else if (BLEService.selected_lock_role == 0) {
            BLEService.vicinity_on = false;
            if (Fragment_lock_setting.initial_setup) {
                UI_BLE.BLE_UI = 20;
                this.ui_ble.update();
                return;
            }
            BLEService.vicinity_on = false;
            if (BLEService.mConnectionState == 0 || current_icon == 4) {
                return;
            }
            if (audit_first_show) {
                audit_first_show = false;
                SweetAlertDialog sweetAlertDialog2 = new SweetAlertDialog(context, 0);
                sweetAlertDialog2.setTitleText(context.getResources().getString(R.string.audit_trail));
                sweetAlertDialog2.setContentText(context.getResources().getString(R.string.audit_msg));
                sweetAlertDialog2.setConfirmText(context.getResources().getString(R.string.ok));
                sweetAlertDialog2.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.MainActivity.37
                    @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                    public void onClick(SweetAlertDialog sweetAlertDialog3) {
                        sweetAlertDialog3.dismissWithAnimation();
                        MainActivity.this.continueToAuditTrail();
                    }
                });
                sweetAlertDialog2.setCanceledOnTouchOutside(false);
                sweetAlertDialog2.show();
                return;
            }
            continueToAuditTrail();
        } else {
            new SweetAlertDialog(context, 3).setTitleText(context.getResources().getString(R.string.user_logged_in)).setContentText(context.getResources().getString(R.string.please_login_as_admin)).setConfirmText(context.getResources().getString(R.string.ok)).show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void continueToAuditTrail() {
        if (BLEService.selected_lock_model.equals("GT5100") || BLEService.selected_lock_model.equals("GT5107") || BLEService.selected_lock_model.equals("GT5109") || BLEService.selected_lock_model.equals("GT5300")) {
            current_icon = 0;
            if (Helper_Network.haveNetworkConnection(this)) {
                current_icon = 4;
                Fragment_BLE.stop_count_down_timer();
                getSupportActionBar().setTitle(R.string.audit_trail);
                fragmentManager.beginTransaction().replace(R.id.container, Fragment_manage_audit_trail.newInstance()).addToBackStack("8").commit();
                if (BLEService.AvailableAuditCount != 0) {
                    new SweetAlertDialog(context).setTitleText(R.string.audit_trail).setContentText(getString(R.string.do_you_want_to_backup_the_directional_passcode)).setConfirmText(context.getResources().getString(R.string.yes)).setCancelText(context.getResources().getString(R.string.no)).setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.MainActivity.39
                        @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            sweetAlertDialog.dismissWithAnimation();
                            MainActivity.current_icon = 4;
                            Fragment_BLE.stop_count_down_timer();
                            MainActivity.isAuditTrailBackup = true;
                            MainActivity.openOnlineAuditTrail();
                            if (BLEService.selected_lock_model.contains("GT5100") || BLEService.selected_lock_model.equals("GT5107") || BLEService.selected_lock_model.equals("GT5109") || BLEService.selected_lock_model.equals("GT5300")) {
                                BLEService.PasscodeIndexCount = 0;
                                BLEService.PasscodeAuditCount = 0;
                                BLEService.listPasscodeIndex.clear();
                                BLEService.listAuditPassCode.clear();
                                BLEService.listAuditTrailTime.clear();
                                BLEService.listAuditDeciValue.clear();
                                BLEService.listAuditLockedbyTime.clear();
                                BLEService.lastUpdatedAuditTimestamp = 0L;
                                BLEService.Ble_Mode = BLEService.Request_passcodeAudit;
                                System.out.println("Hello checking the Passcode Audit trail backup !");
                            } else if (BLEService.selected_lock_model.contains("GT2100")) {
                                BLEService.is_sendTimeStamp = false;
                                BLEService.Total_Tag_Audit = 0L;
                                BLEService.Tag_Audit_count = 0L;
                                BLEService.Tag_AuditList_count = 0;
                                BLEService.Ble_Mode = (BLEService.selected_lock_version.equals("1.80") || ((double) Float.valueOf(BLEService.selected_lock_version).floatValue()) > 1.8d) ? BLEService.Send_timeStamp : 103;
                            }
                            UI_BLE.pls_wait_content = MainActivity.context.getResources().getString(R.string.retrieving_data_from_lock);
                            UI_BLE.BLE_UI = 22;
                            MainActivity.this.ui_ble.update();
                        }
                    }).setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.MainActivity.38
                        @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            sweetAlertDialog.dismissWithAnimation();
                            Fragment_BLE.stop_count_down_timer();
                            MainActivity.isAuditTrailBackup = false;
                            MainActivity.openOnlineAuditTrail();
                        }
                    }).show();
                    return;
                } else if (BLEService.AvailableAuditCount == 0) {
                    isAuditTrailBackup = true;
                    openOnlineAuditTrail();
                    return;
                } else {
                    return;
                }
            }
            SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(context, 0);
            sweetAlertDialog.setTitleText(getString(R.string.pls_note));
            sweetAlertDialog.setContentText(getString(R.string.you_are_not_connected));
            sweetAlertDialog.show();
        } else if (BLEService.selected_lock_model.contains("GT2500") || BLEService.selected_lock_model.equals("GT2550")) {
            current_icon = 0;
            if (Helper_Network.haveNetworkConnection(this)) {
                if (BLEService.AvailableAuditCount != 0) {
                    new SweetAlertDialog(context).setTitleText(R.string.audit_trail).setContentText(getString(R.string.do_you_want_to_back_up_tag_audit)).setConfirmText(context.getResources().getString(R.string.yes)).setCancelText(context.getResources().getString(R.string.no)).setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.MainActivity.41
                        @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                        public void onClick(SweetAlertDialog sweetAlertDialog2) {
                            sweetAlertDialog2.dismissWithAnimation();
                            MainActivity.current_icon = 4;
                            Fragment_BLE.stop_count_down_timer();
                            MainActivity.isAuditTrailBackup = true;
                            MainActivity.openOnlineAuditTrail();
                            BLEService.is_sendTimeStamp = false;
                            BLEService.Total_Tag_Audit = 0L;
                            BLEService.Tag_Audit_count = 0L;
                            BLEService.Tag_AuditList_count = 0;
                            BLEService.listAuditType.clear();
                            BLEService.listAuditTrailTime.clear();
                            BLEService.listAuditTrailTagID.clear();
                            BLEService.audit_current_index_IP66 = BLEService.AvailableAuditCount;
                            BLEService.audittrail_count_IP66 = 0;
                            BLEService.Ble_Mode = 505;
                            UI_BLE.pls_wait_content = MainActivity.context.getResources().getString(R.string.retrieving_data_from_lock);
                            UI_BLE.BLE_UI = 22;
                            MainActivity.this.ui_ble.update();
                        }
                    }).setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.MainActivity.40
                        @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                        public void onClick(SweetAlertDialog sweetAlertDialog2) {
                            sweetAlertDialog2.dismissWithAnimation();
                            Fragment_BLE.stop_count_down_timer();
                            MainActivity.isAuditTrailBackup = false;
                            MainActivity.openOnlineAuditTrail();
                        }
                    }).show();
                    return;
                } else if (BLEService.AvailableAuditCount == 0) {
                    isAuditTrailBackup = true;
                    openOnlineAuditTrail();
                    return;
                } else {
                    return;
                }
            }
            SweetAlertDialog sweetAlertDialog2 = new SweetAlertDialog(context, 0);
            sweetAlertDialog2.setTitleText(getString(R.string.pls_note));
            sweetAlertDialog2.setContentText(getString(R.string.you_are_not_connected));
            sweetAlertDialog2.show();
        } else {
            current_icon = 4;
            Fragment_BLE.stop_count_down_timer();
            if (BLEService.selected_lock_model.equals("GT2100") && (BLEService.selected_lock_version.equals("1.80") || Float.valueOf(BLEService.selected_lock_version).floatValue() > 1.8d)) {
                if (Helper_Network.haveNetworkConnection(this)) {
                    if (BLEService.AvailableAuditCount != 0) {
                        isAuditTrailBackup = true;
                        BLEService.is_sendTimeStamp = false;
                        BLEService.Total_Tag_Audit = 0L;
                        BLEService.Tag_Audit_count = 0L;
                        BLEService.Tag_AuditList_count = 0;
                        BLEService.Ble_Mode = BLEService.Send_timeStamp;
                        UI_BLE.pls_wait_content = context.getResources().getString(R.string.retrieving_data_from_lock);
                        UI_BLE.BLE_UI = 22;
                        this.ui_ble.update();
                    } else {
                        isAuditTrailBackup = false;
                    }
                    getSupportActionBar().setTitle(R.string.audit_trail);
                    fragmentManager.beginTransaction().replace(R.id.container, Fragment_online_logs.newInstance()).addToBackStack("8").commit();
                    return;
                }
                SweetAlertDialog sweetAlertDialog3 = new SweetAlertDialog(context, 0);
                sweetAlertDialog3.setTitleText(getString(R.string.pls_note));
                sweetAlertDialog3.setContentText(getString(R.string.you_are_not_connected));
                sweetAlertDialog3.show();
                return;
            }
            getSupportActionBar().setTitle(R.string.audit_trail);
            fragmentManager.beginTransaction().replace(R.id.container, Fragment_manage_audit_trail.newInstance()).addToBackStack("8").commit();
            BLEService.Ble_Mode = 103;
            UI_BLE.pls_wait_content = context.getResources().getString(R.string.retrieving_data_from_lock);
            UI_BLE.BLE_UI = 22;
            this.ui_ble.update();
        }
    }

    public void btn_audit_notif(View view) {
        btn_audit_trail(view);
    }

    public void btn_setting(View view) {
        shutdown_status = 0;
        BLEService.vicinity_on = false;
        Log.i("TAG", "btn_setting1");
        if (current_icon != 5) {
            current_icon = 5;
            Log.i("TAG", "btn_setting2");
            if (Fragment_lock_setting.initial_setup) {
                UI_BLE.BLE_UI = 20;
                this.ui_ble.update();
                return;
            }
            Fragment_BLE.stop_count_down_timer();
            fragmentManager.beginTransaction().replace(R.id.container, Fragment_lock_setting.newInstance()).addToBackStack("25").commit();
            getSupportActionBar().setTitle(R.string.setting);
        }
    }

    public void standby_time(View view) {
        TextView textView = (TextView) findViewById(R.id.textView_standby);
        if (textView.getText().toString().equals(getResources().getString(R.string.advertising_time_15min))) {
            textView.setText(getResources().getString(R.string.advertising_time_1));
            Fragment_BLE.shutdown_time = 60000;
        } else if (textView.getText().toString().equals(getResources().getString(R.string.advertising_time_1))) {
            textView.setText(getResources().getString(R.string.advertising_time_5));
            Fragment_BLE.shutdown_time = 300000;
        } else if (textView.getText().toString().equals(getResources().getString(R.string.advertising_time_5))) {
            textView.setText(getResources().getString(R.string.advertising_time_15min));
            Fragment_BLE.shutdown_time = 900000;
        } else {
            textView.setText(getResources().getString(R.string.advertising_time_5));
            Fragment_BLE.shutdown_time = 300000;
        }
        SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(getApplication()).edit();
        edit.putInt("shutdown_time", Fragment_BLE.shutdown_time);
        edit.commit();
        new SweetAlertDialog(context, 2).setTitleText(context.getResources().getString(R.string.update_successfully)).show();
    }

    public void btn_update_new_lock_name(View view) {
        if (isConnected()) {
            System.out.println("HEY gonna rename " + BLEService.selected_lock_name);
            EditText editText = (EditText) findViewById(R.id.editText_new_name);
            if (!editText.getText().toString().equals("")) {
                String obj = editText.getText().toString();
                lockNameForUpdating = editText.getText().toString();
                ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 2);
                if (DatabaseVariable.db_lock.rawQuery(DatabaseVariable.lockdb_Query_name_exist(obj), null).moveToNext()) {
                    new SweetAlertDialog(context, 3).setTitleText(context.getResources().getString(R.string.change_lock_name_title)).setContentText(context.getResources().getString(R.string.lock_name_duplicated)).setConfirmText(context.getResources().getString(R.string.ok)).show();
                    return;
                }
                String str = BLEService.selected_lock_name;
                if (obj.contains("'") || obj.contains("\"")) {
                    obj = DatabaseUtils.sqlEscapeString(obj);
                }
                System.out.println("Hello change Name :" + obj);
                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                this.database = firebaseDatabase;
                DatabaseReference child = firebaseDatabase.getReference("userLocks").child(user_uid);
                this.lockRefForUpdating = child;
                child.orderByChild("Name").equalTo(BLEService.selected_lock_name).addListenerForSingleValueEvent(new ValueEventListener() { // from class: com.egeetouch.egeetouch_manager.MainActivity.42
                    @Override // com.google.firebase.database.ValueEventListener
                    public void onCancelled(DatabaseError databaseError) {
                    }

                    @Override // com.google.firebase.database.ValueEventListener
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot dataSnapshot2 : dataSnapshot.getChildren()) {
                            String key = dataSnapshot2.getKey();
                            System.out.println("HEY key of lock whose name you are searching for " + key);
                            HashMap hashMap = new HashMap();
                            hashMap.put("Name", MainActivity.lockNameForUpdating);
                            MainActivity.this.lockRefForUpdating.child(key).updateChildren(hashMap);
                            final DatabaseReference child2 = MainActivity.this.database.getReference("MyAdminLocks").child(MainActivity.user_uid);
                            child2.orderByChild("ip45SerialNumber").equalTo(BLEService.parsedIp45SerialNumber).addListenerForSingleValueEvent(new ValueEventListener() { // from class: com.egeetouch.egeetouch_manager.MainActivity.42.1
                                @Override // com.google.firebase.database.ValueEventListener
                                public void onCancelled(DatabaseError databaseError) {
                                }

                                @Override // com.google.firebase.database.ValueEventListener
                                public void onDataChange(DataSnapshot dataSnapshot3) {
                                    if (dataSnapshot3.exists()) {
                                        for (DataSnapshot dataSnapshot4 : dataSnapshot3.getChildren()) {
                                            String key2 = dataSnapshot4.getKey();
                                            HashMap hashMap2 = new HashMap();
                                            hashMap2.put("lockAlias", MainActivity.lockNameForUpdating);
                                            child2.child(key2).updateChildren(hashMap2);
                                        }
                                    }
                                }
                            });
                        }
                    }
                });
                DatabaseVariable.db_lock.execSQL("UPDATE " + DatabaseVariable.Element_name_lock + " SET " + DatabaseVariable.D1_lock + " = '" + obj.replaceAll("'", "''").replaceAll("\"", "\"\"") + "' WHERE " + DatabaseVariable.D1_lock + "='" + str.replaceAll("'", "''").replaceAll("\"", "\"\"") + "'");
                DatabaseVariable.db_tag.execSQL("UPDATE " + DatabaseVariable.Element_name_tag + " SET " + DatabaseVariable.D3_tag + " = '" + obj.replaceAll("'", "''").replaceAll("\"", "\"\"") + "' WHERE " + DatabaseVariable.D3_tag + "='" + str.replaceAll("'", "''").replaceAll("\"", "\"\"") + "'");
                DatabaseVariable.db_user.execSQL("UPDATE " + DatabaseVariable.Element_name_user + " SET " + DatabaseVariable.D3_user + " = '" + obj.replaceAll("'", "''").replaceAll("\"", "\"\"") + "' WHERE " + DatabaseVariable.D3_user + "='" + str.replaceAll("'", "''").replaceAll("\"", "\"\"") + "'");
                DatabaseVariable.db_audittrail.execSQL("UPDATE " + DatabaseVariable.Element_name_audittrail + " SET " + DatabaseVariable.D7_audittrail + " = '" + obj.replaceAll("'", "''").replaceAll("\"", "\"\"") + "' WHERE " + DatabaseVariable.D7_audittrail + "='" + str.replaceAll("'", "''").replaceAll("\"", "\"\"") + "'");
                DatabaseVariable.db_location.execSQL("UPDATE " + DatabaseVariable.Element_name_location + " SET " + DatabaseVariable.D3_location + " = '" + obj.replaceAll("'", "''").replaceAll("\"", "\"\"") + "' WHERE " + DatabaseVariable.D3_location + "='" + str.replaceAll("'", "''").replaceAll("\"", "\"\"") + "'");
                BLEService.selected_lock_name = obj;
                new SweetAlertDialog(context, 2).setTitleText(context.getResources().getString(R.string.update_successfully)).show();
                return;
            }
            editText.setError(context.getResources().getString(R.string.please_enter_lock_name));
            return;
        }
        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(this, 1);
        sweetAlertDialog.setTitleText(getString(R.string.no_connection));
        sweetAlertDialog.setContentText(getString(R.string.no_internet));
        sweetAlertDialog.setConfirmText(getString(R.string.ok));
        sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.MainActivity.43
            @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
            public void onClick(SweetAlertDialog sweetAlertDialog2) {
                sweetAlertDialog2.dismissWithAnimation();
            }
        });
        sweetAlertDialog.show();
    }

    public void btn_change_pad_password(View view) {
        System.out.println("Hello btn_bhange_pad _password");
    }

    public void btn_update_new_primary_password(View view) {
        if (isConnected() && BluetoothAdapter.getDefaultAdapter().isEnabled()) {
            if (BLEService.selected_lock_role == 1) {
                new SweetAlertDialog(context, 3).setTitleText(context.getResources().getString(R.string.user_logged_in)).setContentText(context.getResources().getString(R.string.please_login_as_admin)).setConfirmText(context.getResources().getString(R.string.ok)).show();
                return;
            }
            SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(context, 5);
            setting_progress = sweetAlertDialog;
            sweetAlertDialog.setTitleText(getString(R.string.loading));
            setting_progress.setCancelable(false);
            EditText editText = (EditText) findViewById(R.id.editText_new_primary_password);
            EditText editText2 = (EditText) findViewById(R.id.editText_new_primary_password2);
            ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 2);
            if (!editText.getText().toString().equals("") && !editText2.getText().toString().equals("")) {
                if (editText.getText().toString().equals(editText2.getText().toString())) {
                    if (editText.getText().length() >= 6) {
                        if (DatabaseVariable.db_user.rawQuery(DatabaseVariable.userdb_Query_password_exist_in_lock(BLEService.selected_lock_name, editText.getText().toString()), null).moveToNext()) {
                            editText.setError(context.getResources().getString(R.string.password_used));
                            return;
                        }
                        BLEService.new_primary_password = editText.getText().toString();
                        if (BLEService.new_primary_password.equals("123456")) {
                            editText.setError(context.getResources().getString(R.string.default_password_is_not_allowed));
                            return;
                        }
                        BLEService.Ble_Mode = 85;
                        backup_lock_password_firebase(BLEService.new_primary_password);
                        setting_progress.show();
                        editText.setText("");
                        editText2.setText("");
                        return;
                    }
                    editText.setError(context.getResources().getString(R.string.password_length));
                    return;
                }
                editText2.setError(context.getResources().getString(R.string.password_is_not_identical));
                return;
            }
            editText.setError(context.getResources().getString(R.string.please_key_in_password));
            return;
        }
        SweetAlertDialog sweetAlertDialog2 = new SweetAlertDialog(this, 1);
        sweetAlertDialog2.setTitleText(getString(R.string.no_connection));
        sweetAlertDialog2.setContentText(getString(R.string.no_internet_bluetooth));
        sweetAlertDialog2.setConfirmText(getString(R.string.ok));
        sweetAlertDialog2.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.MainActivity.44
            @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
            public void onClick(SweetAlertDialog sweetAlertDialog3) {
                sweetAlertDialog3.dismissWithAnimation();
            }
        });
        sweetAlertDialog2.show();
    }

    public void backup_lock_password_firebase(String str) {
        System.out.println("Hello backup_password" + str);
        if (BLEService.parsedIp45SerialNumber.equals("")) {
            return;
        }
        System.out.println("Hello shooting to Firebase LOCKED status with serial of " + BLEService.parsedIp45SerialNumber);
        currentTimestampDouble = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() * 1000);
        DatabaseReference push = this.database.getReference("passwordBackups").child(BLEService.parsedIp45SerialNumber).push();
        push.child("changedOn").setValue(Double.valueOf(currentTimestampDouble));
        push.child("password").setValue(encryptPassword(str));
    }

    public void switch_unlocking(View view) {
        if (((Switch) findViewById(R.id.Switch_unlocking)).isChecked()) {
            SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(this).edit();
            edit.putBoolean("unlocking_msg_show_boolean", true);
            edit.apply();
            return;
        }
        SharedPreferences.Editor edit2 = PreferenceManager.getDefaultSharedPreferences(this).edit();
        edit2.putBoolean("unlocking_msg_show_boolean", false);
        edit2.apply();
    }

    public void switch_proximity(View view) {
        if (((Switch) findViewById(R.id.switch_proximity)).isChecked()) {
            SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(this).edit();
            edit.putBoolean("proximity_msg_box_boolean", true);
            edit.commit();
            return;
        }
        SharedPreferences.Editor edit2 = PreferenceManager.getDefaultSharedPreferences(this).edit();
        edit2.putBoolean("proximity_msg_box_boolean", false);
        edit2.commit();
    }

    public void switch_auto_locking(View view) {
        if (((Switch) findViewById(R.id.Switch_auto_locking)).isChecked()) {
            SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(this).edit();
            edit.putBoolean("auto_locking_boolean", true);
            edit.commit();
            return;
        }
        SharedPreferences.Editor edit2 = PreferenceManager.getDefaultSharedPreferences(this).edit();
        edit2.putBoolean("auto_locking_boolean", false);
        edit2.commit();
    }

    public void switch_auto_unlocking(View view) {
        if (((Switch) findViewById(R.id.Switch_auto_unlocking)).isChecked()) {
            SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(this).edit();
            edit.putBoolean("auto_unlocking_boolean", true);
            edit.commit();
            return;
        }
        SharedPreferences.Editor edit2 = PreferenceManager.getDefaultSharedPreferences(this).edit();
        edit2.putBoolean("auto_unlocking_boolean", false);
        edit2.commit();
    }

    public void adv_time(View view) {
        if (BLEService.selected_lock_role == 1) {
            new SweetAlertDialog(context, 3).setTitleText(context.getResources().getString(R.string.user_logged_in)).setContentText(context.getResources().getString(R.string.please_login_as_admin)).setConfirmText(context.getResources().getString(R.string.ok)).show();
            return;
        }
        TextView textView = (TextView) findViewById(R.id.textView_advertising);
        TextView textView2 = (TextView) findViewById(R.id.tv_lockReconnectionTime);
        Log.i("TAG", "selected_lock_version: " + String.valueOf(BLEService.selected_lock_version));
        double d = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        if (!BLEService.selected_lock_version.equals("")) {
            d = Double.parseDouble(BLEService.selected_lock_version);
        }
        if (BLEService.selected_lock_model.equals("GT2500") || BLEService.selected_lock_model.equals("GT2550") || (d < 3.18d && !BLEService.selected_lock_model.equals("GT3100"))) {
            if (textView.getText().toString().equals(getResources().getString(R.string.advertising_time_1))) {
                BLEService.set_adv_time = 3;
                textView.setText(getResources().getString(R.string.advertising_time_3));
            } else if (textView.getText().toString().equals(getResources().getString(R.string.advertising_time_3))) {
                BLEService.set_adv_time = 5;
                textView.setText(getResources().getString(R.string.advertising_time_5));
            } else if (textView.getText().toString().equals(getResources().getString(R.string.advertising_time_5))) {
                BLEService.set_adv_time = 1;
                textView.setText(getResources().getString(R.string.advertising_time_1));
            }
            if (textView.getText().toString().equals(getResources().getString(R.string.auto_reconnecting_10s))) {
                BLEService.set_adv_time = 20;
                BLEService.LockReconnectingTime = 20;
                textView.setText(getResources().getString(R.string.auto_reconnecting_20s));
                textView2.setText(getResources().getString(R.string.auto_reconnecting_20s));
            } else if (textView.getText().toString().equals(getResources().getString(R.string.auto_reconnecting_20s))) {
                BLEService.set_adv_time = 30;
                BLEService.LockReconnectingTime = 30;
                textView.setText(getResources().getString(R.string.advertising_time_30));
                textView2.setText(getResources().getString(R.string.advertising_time_30));
            } else if (textView.getText().toString().equals(getResources().getString(R.string.advertising_time_30))) {
                BLEService.set_adv_time = 10;
                BLEService.LockReconnectingTime = 10;
                textView.setText(getResources().getString(R.string.auto_reconnecting_10s));
                textView2.setText(getResources().getString(R.string.auto_reconnecting_10s));
            } else {
                BLEService.set_adv_time = 20;
                BLEService.LockReconnectingTime = 20;
                textView.setText(getResources().getString(R.string.auto_reconnecting_20s));
                textView2.setText(getResources().getString(R.string.auto_reconnecting_20s));
            }
            SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(getApplication()).edit();
            edit.putInt("adv_time", BLEService.set_adv_time);
            edit.commit();
        } else if ((d > 3.18d && BLEService.selected_lock_model.equals("GT3100")) || BLEService.selected_lock_model.equals("GT2002")) {
            if (textView.getText().toString().equals(getResources().getString(R.string.advertising_time_3))) {
                BLEService.set_adv_time = 5;
                textView.setText(getResources().getString(R.string.advertising_time_5));
            } else if (textView.getText().toString().equals(getResources().getString(R.string.advertising_time_5))) {
                BLEService.set_adv_time = 10;
                textView.setText(getResources().getString(R.string.advertising_time_10));
            } else if (textView.getText().toString().equals(getResources().getString(R.string.advertising_time_10))) {
                BLEService.set_adv_time = 30;
                textView.setText(getResources().getString(R.string.advertising_time_30min));
            } else if (textView.getText().toString().equals(getResources().getString(R.string.advertising_time_30min))) {
                BLEService.set_adv_time = 3;
                textView.setText(getResources().getString(R.string.advertising_time_3));
            }
            SharedPreferences.Editor edit2 = PreferenceManager.getDefaultSharedPreferences(getApplication()).edit();
            edit2.putInt("adv_time_new", BLEService.set_adv_time);
            edit2.commit();
        }
        if (BLEService.selected_lock_model.equals("GT2500") || BLEService.selected_lock_model.equals("GT2550")) {
            BLEService.Ble_Mode = 509;
        } else {
            BLEService.Ble_Mode = 117;
        }
        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(context, 5);
        setting_progress = sweetAlertDialog;
        sweetAlertDialog.setTitleText(getString(R.string.loading));
        setting_progress.setCancelable(false);
        setting_progress.show();
    }

    public void btn_manage_tag(View view) {
        if (BLEService.selected_lock_role == 0) {
            this.count_addtag = 1;
            if (BLEService.selected_lock_model.equals("GT1000") || BLEService.selected_lock_model.equals("GT2002") || BLEService.selected_lock_model.equals("GT2003") || BLEService.selected_lock_model.equals("GT3100") || BLEService.selected_lock_model.equals("GT3002")) {
                showTagPopUp = false;
            }
            if (BLEService.mConnectionState != 0) {
                if (showTagPopUp) {
                    showTagPopUp = false;
                    ShowCommercialDialog(context, "tagAd");
                    return;
                } else if (current_icon != 6) {
                    Fragment_BLE.stop_count_down_timer();
                    DatabaseVariable.db_tag.execSQL(DatabaseVariable.tagdb_delete_value_bylock(BLEService.selected_lock_name));
                    current_icon = 6;
                    if (BLEService.selected_lock_model.equals("GT2100") && (BLEService.selected_lock_version.equals("1.80") || Float.valueOf(BLEService.selected_lock_version).floatValue() > 1.8d)) {
                        if (Helper_Network.haveNetworkConnection(this)) {
                            UI_BLE.pls_wait_content = context.getResources().getString(R.string.retrieving_data_from_lock);
                            UI_BLE.BLE_UI = 22;
                            this.ui_ble.update();
                            BLEService.Tag_loop_number = 0;
                            BLEService.tag_page_number = 19;
                            DatabaseVariable.db_tag.execSQL(DatabaseVariable.tagdb_delete_value_bylock(BLEService.selected_lock_name));
                            fragmentManager.beginTransaction().replace(R.id.container, Fragment_add_tag.newInstance()).addToBackStack("8").commit();
                            Retrive_Tag_from_Firebase();
                            return;
                        }
                        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(context, 1);
                        sweetAlertDialog.setTitleText(getString(R.string.pls_note));
                        sweetAlertDialog.setContentText(getString(R.string.you_are_not_connected_access_lock));
                        sweetAlertDialog.show();
                        return;
                    } else if (BLEService.selected_lock_model.equals("GT2500") || BLEService.selected_lock_model.equals("GT2550")) {
                        UI_BLE.pls_wait_content = getResources().getString(R.string.retrieving_data_from_lock);
                        UI_BLE.BLE_UI = 22;
                        BLEService.Ble_Mode = 504;
                        this.ui_ble.update();
                        DatabaseVariable.db_tag.execSQL(DatabaseVariable.tagdb_delete_value_bylock(BLEService.selected_lock_name));
                        fragmentManager.beginTransaction().replace(R.id.container, new Fragment_add_tag()).addToBackStack("8").commit();
                        Retrive_Tag_from_FirebaseIP66();
                        return;
                    } else {
                        fragmentManager.beginTransaction().replace(R.id.container, Fragment_add_tag.newInstance()).addToBackStack("8").commit();
                        Fragment_add_tag.msg_update_done = true;
                        BLEService.tag_updateSuccess = true;
                        BLEService.update_progress = 0.0f;
                        BLEService.retrieved_Tag_number = 0;
                        BLEService.retrieve_tag_current_number = 1;
                        BLEService.retrieved_tag_done = false;
                        BLEService.Ble_Mode = 21;
                        UI_BLE.pls_wait_content = context.getResources().getString(R.string.retrieving_data_from_lock);
                        UI_BLE.BLE_UI = 22;
                        this.ui_ble.update();
                        return;
                    }
                } else {
                    return;
                }
            }
            return;
        }
        new SweetAlertDialog(context, 3).setTitleText(context.getResources().getString(R.string.user_logged_in)).setContentText(context.getResources().getString(R.string.please_login_as_admin)).setConfirmText(context.getResources().getString(R.string.ok)).show();
    }

    public void btn_manage_tag2() {
        if (BLEService.selected_lock_role == 0) {
            this.count_addtag = 1;
            if (BLEService.mConnectionState != 0) {
                if (showTagPopUp) {
                    showTagPopUp = false;
                    ShowCommercialDialog(context, "tagAd");
                    return;
                } else if (current_icon != 6) {
                    Fragment_BLE.stop_count_down_timer();
                    DatabaseVariable.db_tag.execSQL(DatabaseVariable.tagdb_delete_value_bylock(BLEService.selected_lock_name));
                    current_icon = 6;
                    if (BLEService.selected_lock_model.equals("GT2100") && (BLEService.selected_lock_version.equals("1.80") || Float.valueOf(BLEService.selected_lock_version).floatValue() > 1.8d)) {
                        if (Helper_Network.haveNetworkConnection(this)) {
                            UI_BLE.pls_wait_content = context.getResources().getString(R.string.retrieving_data_from_lock);
                            UI_BLE.BLE_UI = 22;
                            this.ui_ble.update();
                            BLEService.Tag_loop_number = 0;
                            BLEService.tag_page_number = 19;
                            DatabaseVariable.db_tag.execSQL(DatabaseVariable.tagdb_delete_value_bylock(BLEService.selected_lock_name));
                            fragmentManager.beginTransaction().replace(R.id.container, Fragment_add_tag.newInstance()).addToBackStack("8").commit();
                            Retrive_Tag_from_Firebase();
                            return;
                        }
                        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(context, 0);
                        sweetAlertDialog.setTitleText(getString(R.string.pls_note));
                        sweetAlertDialog.setContentText(getString(R.string.you_are_not_connected_access_lock));
                        sweetAlertDialog.show();
                        return;
                    } else if (BLEService.selected_lock_model.equals("GT2500") || BLEService.selected_lock_model.equals("GT2550")) {
                        UI_BLE.pls_wait_content = getResources().getString(R.string.retrieving_data_from_lock);
                        UI_BLE.BLE_UI = 22;
                        this.ui_ble.update();
                        DatabaseVariable.db_tag.execSQL(DatabaseVariable.tagdb_delete_value_bylock(BLEService.selected_lock_name));
                        fragmentManager.beginTransaction().replace(R.id.container, new Fragment_add_tag()).addToBackStack("8").commit();
                        Retrive_Tag_from_FirebaseIP66();
                        return;
                    } else {
                        fragmentManager.beginTransaction().replace(R.id.container, Fragment_add_tag.newInstance()).addToBackStack("8").commit();
                        Fragment_add_tag.msg_update_done = true;
                        BLEService.tag_updateSuccess = true;
                        BLEService.update_progress = 0.0f;
                        BLEService.retrieved_Tag_number = 0;
                        BLEService.retrieve_tag_current_number = 1;
                        BLEService.retrieved_tag_done = false;
                        BLEService.Ble_Mode = 21;
                        UI_BLE.pls_wait_content = context.getResources().getString(R.string.retrieving_data_from_lock);
                        UI_BLE.BLE_UI = 22;
                        this.ui_ble.update();
                        return;
                    }
                } else {
                    return;
                }
            }
            return;
        }
        new SweetAlertDialog(context, 3).setTitleText(context.getResources().getString(R.string.user_logged_in)).setContentText(context.getResources().getString(R.string.please_login_as_admin)).setConfirmText(context.getResources().getString(R.string.ok)).show();
    }

    public void btn_add_tag_icon(View view) {
        if (usesOnlineTag(BLEService.selected_lock_model, BLEService.selected_lock_version)) {
            if (Helper_Network.haveNetworkConnection(this)) {
                add_tag_func();
                return;
            }
            SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(context, 1);
            sweetAlertDialog.setTitleText(getString(R.string.pls_note));
            sweetAlertDialog.setContentText(getString(R.string.you_are_not_connected_access_lock));
            sweetAlertDialog.show();
            return;
        }
        add_tag_func();
    }

    /* JADX WARN: Removed duplicated region for block: B:49:0x00fb  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x03fb  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void add_tag_func() {
        int r4;
        int r7;
        if (!com.egeetouch.egeetouch_manager.Fragment_add_tag.add_tag_clickable) {
            return;
        }
        int r2 = 0;
        int r3 = 0;
        while (com.egeetouch.egeetouch_manager.DatabaseVariable.db_tag.rawQuery(com.egeetouch.egeetouch_manager.DatabaseVariable.tagdb_number_lock_exist(com.egeetouch.egeetouch_manager.BLEService.selected_lock_name), null).moveToNext()) {
            r3++;
        }
        int r6 = 20;
        if (com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2500") || com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2550")) {
            if (r3 < 20) {
                com.egeetouch.egeetouch_manager.MainActivity.fragmentManager.beginTransaction().replace(com.egeetouch.egeetouch_manager.R.id.container, new com.egeetouch.egeetouch_manager.Fragment_add_multiple_tags()).addToBackStack("add_multiple_tags").commit();
            } else {
                com.egeetouch.egeetouch_manager.CommercialAdvert_dialog.ShowDialog(r18, "tagLimit");
            }
        } else if (!com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2100") || (!com.egeetouch.egeetouch_manager.BLEService.selected_lock_version.equals("1.80") && java.lang.Float.valueOf(com.egeetouch.egeetouch_manager.BLEService.selected_lock_version).floatValue() <= 1.8d)) {
            com.egeetouch.egeetouch_manager.Fragment_add_tag.msg_update_done = true;
            if (com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2000") || com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT3000")) {
                r18.textview_tag_limit = 5 - r3;
            } else if (com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT1000") || com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2002") || com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2003") || com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT3100") || com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT3002")) {
                r18.textview_tag_limit = 5 - r3;
            } else {
                if (com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2100")) {
                    if (r3 > 10) {
                        r18.textview_tag_limit = 20 - r3;
                    } else {
                        r18.textview_tag_limit = 10;
                    }
                    r4 = r3 - 10;
                    r7 = 9;
                } else {
                    r7 = 0;
                    r4 = 5;
                    r6 = 5;
                }
                if (r3 >= r6) {
                    ((android.widget.LinearLayout) findViewById(com.egeetouch.egeetouch_manager.R.id.LinearLayout_add_tag)).setVisibility(0);
                    android.widget.ListView r3 = (android.widget.ListView) findViewById(com.egeetouch.egeetouch_manager.R.id.listview_taglist);
                    r3.setAlpha(0.2f);
                    r3.setClickable(false);
                    ((android.widget.ImageView) findViewById(com.egeetouch.egeetouch_manager.R.id.imageView_addtag)).setVisibility(4);
                    android.widget.TextView[] r3 = {(android.widget.TextView) findViewById(com.egeetouch.egeetouch_manager.R.id.textView_add_tag_title), (android.widget.TextView) findViewById(com.egeetouch.egeetouch_manager.R.id.textView_add_tag_title1), (android.widget.TextView) findViewById(com.egeetouch.egeetouch_manager.R.id.textView_add_tag_title2), (android.widget.TextView) findViewById(com.egeetouch.egeetouch_manager.R.id.textView_add_tag_title3), (android.widget.TextView) findViewById(com.egeetouch.egeetouch_manager.R.id.textView_add_tag_title4), (android.widget.TextView) findViewById(com.egeetouch.egeetouch_manager.R.id.textView_add_tag_title5), (android.widget.TextView) findViewById(com.egeetouch.egeetouch_manager.R.id.textView_add_tag_title6), (android.widget.TextView) findViewById(com.egeetouch.egeetouch_manager.R.id.textView_add_tag_title7), (android.widget.TextView) findViewById(com.egeetouch.egeetouch_manager.R.id.textView_add_tag_title8), (android.widget.TextView) findViewById(com.egeetouch.egeetouch_manager.R.id.textView_add_tag_title9)};
                    android.widget.TextView[] r6 = {(android.widget.TextView) findViewById(com.egeetouch.egeetouch_manager.R.id.textView_tag_name), (android.widget.TextView) findViewById(com.egeetouch.egeetouch_manager.R.id.textView_tag_name1), (android.widget.TextView) findViewById(com.egeetouch.egeetouch_manager.R.id.textView_tag_name2), (android.widget.TextView) findViewById(com.egeetouch.egeetouch_manager.R.id.textView_tag_name3), (android.widget.TextView) findViewById(com.egeetouch.egeetouch_manager.R.id.textView_tag_name4), (android.widget.TextView) findViewById(com.egeetouch.egeetouch_manager.R.id.textView_tag_name5), (android.widget.TextView) findViewById(com.egeetouch.egeetouch_manager.R.id.textView_tag_name6), (android.widget.TextView) findViewById(com.egeetouch.egeetouch_manager.R.id.textView_tag_name7), (android.widget.TextView) findViewById(com.egeetouch.egeetouch_manager.R.id.textView_tag_name8), (android.widget.TextView) findViewById(com.egeetouch.egeetouch_manager.R.id.textView_tag_name9)};
                    android.widget.TextView[] r9 = {(android.widget.TextView) findViewById(com.egeetouch.egeetouch_manager.R.id.textView_tag_id), (android.widget.TextView) findViewById(com.egeetouch.egeetouch_manager.R.id.textView_tag_id1), (android.widget.TextView) findViewById(com.egeetouch.egeetouch_manager.R.id.textView_tag_id2), (android.widget.TextView) findViewById(com.egeetouch.egeetouch_manager.R.id.textView_tag_id3), (android.widget.TextView) findViewById(com.egeetouch.egeetouch_manager.R.id.textView_tag_id4), (android.widget.TextView) findViewById(com.egeetouch.egeetouch_manager.R.id.textView_tag_id5), (android.widget.TextView) findViewById(com.egeetouch.egeetouch_manager.R.id.textView_tag_id6), (android.widget.TextView) findViewById(com.egeetouch.egeetouch_manager.R.id.textView_tag_id7), (android.widget.TextView) findViewById(com.egeetouch.egeetouch_manager.R.id.textView_tag_id8), (android.widget.TextView) findViewById(com.egeetouch.egeetouch_manager.R.id.textView_tag_id9)};
                    android.widget.EditText[] r15 = {(android.widget.EditText) findViewById(com.egeetouch.egeetouch_manager.R.id.editText_tag_name), (android.widget.EditText) findViewById(com.egeetouch.egeetouch_manager.R.id.editText_tag_name1), (android.widget.EditText) findViewById(com.egeetouch.egeetouch_manager.R.id.editText_tag_name2), (android.widget.EditText) findViewById(com.egeetouch.egeetouch_manager.R.id.editText_tag_name3), (android.widget.EditText) findViewById(com.egeetouch.egeetouch_manager.R.id.editText_tag_name4), (android.widget.EditText) findViewById(com.egeetouch.egeetouch_manager.R.id.editText_tag_name5), (android.widget.EditText) findViewById(com.egeetouch.egeetouch_manager.R.id.editText_tag_name6), (android.widget.EditText) findViewById(com.egeetouch.egeetouch_manager.R.id.editText_tag_name7), (android.widget.EditText) findViewById(com.egeetouch.egeetouch_manager.R.id.editText_tag_name8), (android.widget.EditText) findViewById(com.egeetouch.egeetouch_manager.R.id.editText_tag_name9)};
                    android.widget.EditText[] r8 = {(android.widget.EditText) findViewById(com.egeetouch.egeetouch_manager.R.id.editText_tag_id), (android.widget.EditText) findViewById(com.egeetouch.egeetouch_manager.R.id.editText_tag_id1), (android.widget.EditText) findViewById(com.egeetouch.egeetouch_manager.R.id.editText_tag_id2), (android.widget.EditText) findViewById(com.egeetouch.egeetouch_manager.R.id.editText_tag_id3), (android.widget.EditText) findViewById(com.egeetouch.egeetouch_manager.R.id.editText_tag_id4), (android.widget.EditText) findViewById(com.egeetouch.egeetouch_manager.R.id.editText_tag_id5), (android.widget.EditText) findViewById(com.egeetouch.egeetouch_manager.R.id.editText_tag_id6), (android.widget.EditText) findViewById(com.egeetouch.egeetouch_manager.R.id.editText_tag_id7), (android.widget.EditText) findViewById(com.egeetouch.egeetouch_manager.R.id.editText_tag_id8), (android.widget.EditText) findViewById(com.egeetouch.egeetouch_manager.R.id.editText_tag_id9)};
                    for (int r5 = 0; r5 <= 9; r5++) {
                        r3[r5].setVisibility(0);
                        r6[r5].setVisibility(0);
                        r9[r5].setVisibility(0);
                        r15[r5].setVisibility(0);
                        r8[r5].setVisibility(0);
                    }
                    for (int r1 = 1; r1 <= r4; r1++) {
                        r3[r7].setVisibility(8);
                        r6[r7].setVisibility(8);
                        r9[r7].setVisibility(8);
                        r15[r7].setVisibility(8);
                        r8[r7].setVisibility(8);
                        r7--;
                        java.lang.System.out.println("Hello array_index:" + r7 + " i:" + r1);
                    }
                    java.lang.String r1 = getString(com.egeetouch.egeetouch_manager.R.string.add_tag);
                    while (r2 < r18.textview_tag_limit) {
                        int r4 = r2 + 1;
                        r3[r2].setText(r1 + " " + r4);
                        r2 = r4;
                    }
                    return;
                }
                com.egeetouch.egeetouch_manager.CommercialAdvert_dialog.ShowDialog(r18, "tagLimit");
                return;
            }
            r4 = r3 + 5;
            r7 = 9;
            r6 = 5;
            if (r3 >= r6) {
            }
        } else if (r3 < 20) {
            com.egeetouch.egeetouch_manager.MainActivity.fragmentManager.beginTransaction().replace(com.egeetouch.egeetouch_manager.R.id.container, com.egeetouch.egeetouch_manager.Fragment_add_multiple_tags.newInstance()).addToBackStack("9").commit();
        } else {
            com.egeetouch.egeetouch_manager.CommercialAdvert_dialog.ShowDialog(r18, "tagLimit");
        }
    }

    public void btn_cancel_add_tag(View view) {
        hideKeyboard(view);
        ((LinearLayout) findViewById(R.id.LinearLayout_add_tag)).setVisibility(8);
        ListView listView = (ListView) findViewById(R.id.listview_taglist);
        listView.setAlpha(1.0f);
        listView.setClickable(true);
        ((ImageView) findViewById(R.id.imageView_addtag)).setVisibility(0);
    }

    public void btn_add_tag(View view) {
        char c;
        char c2;
        char[] cArr;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        arrayList.clear();
        arrayList2.clear();
        String[] strArr = null;
        boolean z = false;
        int i = 0;
        while (DatabaseVariable.db_tag.rawQuery(DatabaseVariable.tagdb_number_lock_exist(BLEService.selected_lock_name), null).moveToNext()) {
            i++;
        }
        int i2 = 20;
        if (!BLEService.selected_lock_model.equals("GT2000") && !BLEService.selected_lock_model.equals("GT3000") && !BLEService.selected_lock_model.equals("GT1000") && !BLEService.selected_lock_model.equals("GT2002") && !BLEService.selected_lock_model.equals("GT2003") && !BLEService.selected_lock_model.equals("GT3100") && !BLEService.selected_lock_model.equals("GT2100")) {
            i2 = 5;
        }
        if (i <= i2) {
            System.out.println("Hello count_addtag:" + this.count_addtag);
            char c3 = 2;
            EditText[] editTextArr = {(EditText) findViewById(R.id.editText_tag_name), (EditText) findViewById(R.id.editText_tag_name1), (EditText) findViewById(R.id.editText_tag_name2), (EditText) findViewById(R.id.editText_tag_name3), (EditText) findViewById(R.id.editText_tag_name4), (EditText) findViewById(R.id.editText_tag_name5), (EditText) findViewById(R.id.editText_tag_name6), (EditText) findViewById(R.id.editText_tag_name7), (EditText) findViewById(R.id.editText_tag_name8), (EditText) findViewById(R.id.editText_tag_name9)};
            EditText[] editTextArr2 = {(EditText) findViewById(R.id.editText_tag_id), (EditText) findViewById(R.id.editText_tag_id1), (EditText) findViewById(R.id.editText_tag_id2), (EditText) findViewById(R.id.editText_tag_id3), (EditText) findViewById(R.id.editText_tag_id4), (EditText) findViewById(R.id.editText_tag_id5), (EditText) findViewById(R.id.editText_tag_id6), (EditText) findViewById(R.id.editText_tag_id7), (EditText) findViewById(R.id.editText_tag_id8), (EditText) findViewById(R.id.editText_tag_id9)};
            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.LinearLayout_add_tag);
            int i3 = 0;
            boolean z2 = true;
            while (i3 < this.textview_tag_limit) {
                if (editTextArr[i3].getText().toString().equals("") && linearLayout.getVisibility() != 0) {
                    editTextArr[i3].setError(getString(R.string.please_enter_tag_name));
                } else {
                    if (editTextArr2[i3].getText().toString().equals("") && linearLayout.getVisibility() != 0) {
                        editTextArr2[i3].setError(getString(R.string.please_enter_tag_id));
                    }
                    if (!editTextArr[i3].getText().toString().equals("") || editTextArr2[i3].getText().toString().equals("")) {
                        c = c3;
                        c2 = 5;
                    } else {
                        final String obj = editTextArr[i3].getText().toString();
                        String obj2 = editTextArr2[i3].getText().toString();
                        if (obj2.length() != 4) {
                            editTextArr2[i3].setError(getString(R.string.please_enter_correct_tag_id));
                            arrayList3.add(editTextArr[i3].getText().toString());
                            z2 = z;
                            c2 = 5;
                            c = 2;
                        } else {
                            if (i == 0) {
                                i = 1;
                            }
                            if (!checkTagValid(obj2)) {
                                c2 = 5;
                                c = 2;
                                editTextArr2[i3].setError(getString(R.string.please_enter_correct_tag_id));
                                z2 = false;
                            } else if (i < i2) {
                                System.out.println("number_of_tags:" + i + "max_tag:" + i2);
                                Cursor rawQuery = DatabaseVariable.db_tag.rawQuery(DatabaseVariable.tagdb_Query_name_exist(obj, BLEService.selected_lock_name), strArr);
                                while (rawQuery.moveToNext()) {
                                    i++;
                                    rawQuery = DatabaseVariable.db_tag.rawQuery(DatabaseVariable.tagdb_Query_name_exist(obj + String.valueOf(i), BLEService.selected_lock_name), strArr);
                                }
                                obj2.getChars(0, 4, new char[4], 0);
                                c = 2;
                                final String str = " " + cArr[0] + cArr[1] + " " + cArr[2] + cArr[3] + " ";
                                DatabaseVariable.db_tag.execSQL(DatabaseVariable.tagdb_insert_value(obj, str, BLEService.selected_lock_name));
                                final DatabaseReference child = FirebaseDatabase.getInstance().getReference("Registered_user").child(user_uid).child(String.valueOf(child_index)).child("Tag");
                                tag_num = 0;
                                child.addListenerForSingleValueEvent(new ValueEventListener() { // from class: com.egeetouch.egeetouch_manager.MainActivity.45
                                    @Override // com.google.firebase.database.ValueEventListener
                                    public void onCancelled(DatabaseError databaseError) {
                                    }

                                    @Override // com.google.firebase.database.ValueEventListener
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        MainActivity.tag_num = (int) dataSnapshot.getChildrenCount();
                                        MainActivity.tag_num++;
                                        Log.i("Tag", "Tag Child: " + dataSnapshot.getChildrenCount());
                                        DatabaseReference child2 = child.child(String.valueOf(MainActivity.tag_num));
                                        child2.child("TagName").setValue(obj);
                                        child2.child("TagPassword").setValue(str);
                                    }
                                });
                                c2 = 5;
                            } else {
                                c2 = 5;
                                c = 2;
                                if (i2 == 5) {
                                    new SweetAlertDialog(context, 3).setTitleText(context.getResources().getString(R.string.manage_tags)).setContentText(context.getResources().getString(R.string.max_tag_5)).setConfirmText(context.getResources().getString(R.string.ok)).show();
                                } else {
                                    new SweetAlertDialog(context, 3).setTitleText(context.getResources().getString(R.string.manage_tags)).setContentText(context.getResources().getString(R.string.max_tag)).setConfirmText(context.getResources().getString(R.string.ok)).show();
                                }
                            }
                            editTextArr[i3].setText("");
                            editTextArr2[i3].setText("");
                        }
                    }
                    i3++;
                    c3 = c;
                    z = false;
                    strArr = null;
                }
                z2 = z;
                if (editTextArr[i3].getText().toString().equals("")) {
                }
                c = c3;
                c2 = 5;
                i3++;
                c3 = c;
                z = false;
                strArr = null;
            }
            if (z2) {
                BLEService.update_progress = 0.0f;
                BLEService.retrieved_Tag_number = 0;
                BLEService.retrieve_tag_current_number = 1;
                BLEService.retrieved_tag_done = false;
                BLEService.Ble_Mode = 80;
                UI_BLE.pls_wait_content = context.getResources().getString(R.string.updating_data_to_lock);
                UI_BLE.BLE_UI = 22;
                this.ui_ble.update();
                hideKeyboard(view);
                linearLayout.setVisibility(8);
                ListView listView = (ListView) findViewById(R.id.listview_taglist);
                listView.setAlpha(1.0f);
                listView.setClickable(true);
                ((ImageView) findViewById(R.id.imageView_addtag)).setVisibility(0);
                return;
            }
            return;
        }
        new SweetAlertDialog(context, 3).setTitleText(context.getResources().getString(R.string.manage_tags)).setContentText(context.getResources().getString(R.string.max_tag_5_added)).setConfirmText(context.getResources().getString(R.string.ok)).show();
    }

    private boolean checkTagValid(String str) {
        ArrayList arrayList = new ArrayList(Arrays.asList(AppEventsConstants.EVENT_PARAM_VALUE_YES, ExifInterface.GPS_MEASUREMENT_2D, ExifInterface.GPS_MEASUREMENT_3D, "4", "5", "6", "7", "8", "9", AppEventsConstants.EVENT_PARAM_VALUE_NO, "a", "b", "c", "d", "e", "f"));
        boolean z = true;
        for (int i = 0; i < str.length(); i++) {
            if (!arrayList.contains(String.valueOf(str.charAt(i)).toLowerCase()) && str.length() > 0) {
                z = false;
            }
        }
        return z;
    }

    public void btn_manage_user(View view) {
        BLEService.vicinity_on = false;
        if (BLEService.selected_lock_role == 0) {
            if (BLEService.mConnectionState == 0 || current_icon == 7) {
                return;
            }
            current_icon = 7;
            Fragment_BLE.stop_count_down_timer();
            new SweetAlertDialog(context, 0).setTitleText(context.getResources().getString(R.string.manage_users)).setContentText(context.getResources().getString(R.string.free_trail_version)).setConfirmText(context.getResources().getString(R.string.ok)).setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.MainActivity.46
                @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                public void onClick(SweetAlertDialog sweetAlertDialog) {
                    sweetAlertDialog.dismissWithAnimation();
                    DatabaseVariable.db_user.execSQL(DatabaseVariable.userdb_delete_value_bylock(BLEService.selected_lock_name));
                    BLEService.update_progress = 0.0f;
                    BLEService.retrieved_User_number = 0;
                    BLEService.retrieve_user_current_number = 0;
                    BLEService.User_loop_number = 1;
                    BLEService.retrieved_user_done = false;
                    BLEService.Ble_Mode = 34;
                    UI_BLE.pls_wait_content = MainActivity.context.getResources().getString(R.string.retrieving_data_from_lock);
                    UI_BLE.BLE_UI = 22;
                    MainActivity.this.ui_ble.update();
                }
            }).show();
            fragmentManager.beginTransaction().replace(R.id.container, Fragment_manage_user.newInstance()).addToBackStack("8").commit();
            return;
        }
        new SweetAlertDialog(context, 3).setTitleText(context.getResources().getString(R.string.user_logged_in)).setContentText(context.getResources().getString(R.string.please_login_as_admin)).setConfirmText(context.getResources().getString(R.string.ok)).show();
    }

    public void btn_add_user_icon(View view) {
        ((LinearLayout) findViewById(R.id.LinearLayout_add_user)).setVisibility(0);
        ListView listView = (ListView) findViewById(R.id.listview_userlist);
        listView.setAlpha(0.2f);
        listView.setClickable(false);
        ((ImageView) findViewById(R.id.imageView_add_user)).setVisibility(4);
    }

    public void btn_cancel_add_user(View view) {
        hideKeyboard(view);
        ((LinearLayout) findViewById(R.id.LinearLayout_add_user)).setVisibility(4);
        ListView listView = (ListView) findViewById(R.id.listview_userlist);
        listView.setAlpha(1.0f);
        listView.setClickable(true);
        ((ImageView) findViewById(R.id.imageView_add_user)).setVisibility(0);
    }

    public void btn_add_user(View view) {
        int i;
        final EditText editText = (EditText) findViewById(R.id.editText_user_name);
        final EditText editText2 = (EditText) findViewById(R.id.editText_user_password);
        Log.i("TAG", "btn_add_user");
        if (editText == null || editText2 == null) {
            return;
        }
        Log.i("TAG", "btn_add_user2");
        if (editText.getText().toString().equals("")) {
            editText.setError(getString(R.string.please_enter_user_name));
        }
        if (editText2.getText().toString().equals("")) {
            editText2.setError(getString(R.string.please_enter_user_pw));
        } else if (editText.getText().toString().equals("") || editText2.getText().toString().equals("")) {
        } else {
            Log.i("TAG", "btn_add_user3");
            final String obj = editText.getText().toString();
            final String obj2 = editText2.getText().toString();
            if (obj2.length() != 6) {
                editText2.setError(getString(R.string.please_enter_correct_user_pw));
                return;
            }
            Cursor rawQuery = DatabaseVariable.db_user.rawQuery(DatabaseVariable.userdb_Query_user_name_in_lock(BLEService.selected_lock_name), null);
            int i2 = 0;
            while (rawQuery.moveToNext()) {
                i2++;
            }
            rawQuery.close();
            if (BLEService.selected_lock_model.equals("GT2000") || BLEService.selected_lock_model.equals("GT3000")) {
                i = 20;
            } else {
                if (!BLEService.selected_lock_model.equals("GT1000") && !BLEService.selected_lock_model.equals("GT2002")) {
                    BLEService.selected_lock_model.equals("GT3100");
                }
                i = 5;
            }
            if (i2 >= i) {
                if (i == 5) {
                    new SweetAlertDialog(context, 3).setTitleText(context.getResources().getString(R.string.manage_users)).setContentText(context.getResources().getString(R.string.max_user_5)).setConfirmText(context.getResources().getString(R.string.ok)).show();
                } else {
                    new SweetAlertDialog(context, 3).setTitleText(context.getResources().getString(R.string.manage_users)).setContentText(context.getResources().getString(R.string.max_user)).setConfirmText(context.getResources().getString(R.string.ok)).show();
                }
            } else if (!obj.equals("")) {
                if (!obj2.equals("")) {
                    if (obj2.length() >= 6) {
                        ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 2);
                        Cursor rawQuery2 = DatabaseVariable.db_user.rawQuery(DatabaseVariable.userdb_Query_user_name_exist_in_lock(BLEService.selected_lock_name, obj), null);
                        if (rawQuery2.moveToNext()) {
                            editText.setError(getString(R.string.user_name_used));
                        } else {
                            Cursor rawQuery3 = DatabaseVariable.db_lock.rawQuery(DatabaseVariable.lockdb_Query_model(BLEService.selected_lock_name), null);
                            String string = rawQuery3.moveToNext() ? rawQuery3.getString(1) : "      ";
                            rawQuery3.close();
                            Cursor rawQuery4 = DatabaseVariable.db_user.rawQuery(DatabaseVariable.userdb_Query_password_exist_in_lock(BLEService.selected_lock_name, obj2), null);
                            if (string.equals(obj2)) {
                                editText2.setError(getString(R.string.password_used));
                            } else if (rawQuery4.moveToNext()) {
                                editText2.setError(getString(R.string.password_used));
                            } else {
                                final DatabaseReference child = FirebaseDatabase.getInstance().getReference("Registered_user").child(user_uid).child(String.valueOf(child_index)).child("Share_List");
                                user_num = 0;
                                child.addListenerForSingleValueEvent(new ValueEventListener() { // from class: com.egeetouch.egeetouch_manager.MainActivity.47
                                    @Override // com.google.firebase.database.ValueEventListener
                                    public void onCancelled(DatabaseError databaseError) {
                                    }

                                    @Override // com.google.firebase.database.ValueEventListener
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        MainActivity.user_num = (int) dataSnapshot.getChildrenCount();
                                        MainActivity.user_num++;
                                        Log.i("Tag", "User Child: " + MainActivity.user_num);
                                        DatabaseReference child2 = child.child(String.valueOf(MainActivity.user_num));
                                        child2.child("UserName").setValue(obj);
                                        child2.child("UserPassword").setValue(obj2);
                                        child2.child("UserMode").setValue(AppEventsConstants.EVENT_PARAM_VALUE_NO);
                                        child2.child("UserEmail").setValue("koh-waikin@jsbtech.com");
                                        ((LinearLayout) MainActivity.this.findViewById(R.id.LinearLayout_add_user)).setVisibility(8);
                                        DatabaseVariable.db_user.execSQL(DatabaseVariable.userdb_insert_value(obj, obj2, BLEService.selected_lock_name));
                                        BLEService.add_user_done = false;
                                        BLEService.Ble_Mode = 24;
                                        BLEService.User_loop_number = 1;
                                        UI_BLE.pls_wait_content = MainActivity.context.getResources().getString(R.string.updating_data_to_lock);
                                        UI_BLE.BLE_UI = 22;
                                        MainActivity.this.ui_ble.update();
                                        editText.setText("");
                                        editText2.setText("");
                                        ((LinearLayout) MainActivity.this.findViewById(R.id.LinearLayout_add_user)).setVisibility(4);
                                        ListView listView = (ListView) MainActivity.this.findViewById(R.id.listview_userlist);
                                        listView.setAlpha(1.0f);
                                        listView.setClickable(true);
                                        ((ImageView) MainActivity.this.findViewById(R.id.imageView_add_user)).setVisibility(0);
                                    }
                                });
                            }
                        }
                        rawQuery2.close();
                        return;
                    }
                    editText2.setError(getString(R.string.password_length));
                    return;
                }
                editText2.setError(getString(R.string.please_key_in_password));
            } else {
                editText.setError(getString(R.string.please_enter_user_name));
            }
        }
    }

    public void btn_dash_board(View view) {
        if (animation.isRunning()) {
            animation.stop();
        }
        fragmentManager.popBackStack((String) null, 1);
        fab.setVisibility(0);
        fab_share.setVisibility(0);
        fab_admin_access_locks.setVisibility(0);
        ListView listView = dashboard_listview;
        if (listView != null) {
            listView.setVisibility(0);
        }
        pullToRefresh.setEnabled(true);
        current_icon = 0;
        getSupportActionBar().setTitle(R.string.dashboard);
        show_dashboard_list();
    }

    public void btn_more_function(View view) {
        if (animation.isRunning()) {
            animation.stop();
        }
        if (TaskManagement.password_varified && TaskManagement.password_varified_correct && !is_admin) {
            new SweetAlertDialog(context, 3).setTitleText(context.getResources().getString(R.string.user_logged_in)).setContentText(context.getResources().getString(R.string.please_login_as_admin)).setConfirmText(context.getResources().getString(R.string.ok)).show();
        } else if (TaskManagement.password_varified && TaskManagement.password_varified_correct && is_admin) {
            fragmentManager.beginTransaction().replace(R.id.container, Fragment_morefunctions.newInstance()).addToBackStack("8").commit();
        }
    }

    public void btn_change_lock_name(final View view) {
        if (animation.isRunning()) {
            animation.stop();
        }
        ready_2_pair = false;
        final TextView textView = (TextView) findViewById(R.id.textView_pairing_lock_name);
        final EditText editText = (EditText) findViewById(R.id.editText_new_lock_name);
        final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.LinearLayout_edit_name_buttons);
        Button button = (Button) findViewById(R.id.button_new_lock_name_save);
        textView.setVisibility(4);
        editText.setVisibility(0);
        linearLayout.setVisibility(0);
        editText.setText(BLEService.selected_lock_name);
        button.setOnClickListener(new View.OnClickListener() { // from class: com.egeetouch.egeetouch_manager.MainActivity.48
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                MainActivity.this.hideKeyboard(view);
                new SweetAlertDialog(MainActivity.this).setTitleText(view.getResources().getString(R.string.change_lock_name_nfc)).setContentText(view.getResources().getString(R.string.make) + " " + ((Object) editText.getText()) + " " + view.getResources().getString(R.string.as_new_name)).setConfirmText(MainActivity.this.getString(R.string.yes)).setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.MainActivity.48.2
                    @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismissWithAnimation();
                        String obj = editText.getText().toString();
                        if (obj.contains("'")) {
                            obj = DatabaseUtils.sqlEscapeString(obj);
                        }
                        if (DatabaseVariable.db_lock.rawQuery(DatabaseVariable.lockdb_Query_name_exist(obj), null).moveToNext()) {
                            new SweetAlertDialog(MainActivity.this, 1).setTitleText("").setContentText(view.getResources().getString(R.string.lock_name_duplicated)).setConfirmText(MainActivity.this.getString(R.string.ok)).show();
                        } else {
                            String str = BLEService.selected_lock_name;
                            DatabaseVariable.db_lock.execSQL("UPDATE " + DatabaseVariable.Element_name_lock + " SET " + DatabaseVariable.D1_lock + " = '" + obj + "' WHERE " + DatabaseVariable.D1_lock + "='" + str + "'");
                            DatabaseVariable.db_tag.execSQL("UPDATE " + DatabaseVariable.Element_name_tag + " SET " + DatabaseVariable.D3_tag + " = '" + obj + "' WHERE " + DatabaseVariable.D3_tag + "='" + str + "'");
                            DatabaseVariable.db_user.execSQL("UPDATE " + DatabaseVariable.Element_name_user + " SET " + DatabaseVariable.D3_user + " = '" + obj + "' WHERE " + DatabaseVariable.D3_user + "='" + str + "'");
                            DatabaseVariable.db_audittrail.execSQL("UPDATE " + DatabaseVariable.Element_name_audittrail + " SET " + DatabaseVariable.D7_audittrail + " = '" + obj + "' WHERE " + DatabaseVariable.D7_audittrail + "='" + str + "'");
                            DatabaseVariable.db_location.execSQL("UPDATE " + DatabaseVariable.Element_name_location + " SET " + DatabaseVariable.D3_location + " = '" + obj + "' WHERE " + DatabaseVariable.D3_location + "='" + str + "'");
                            textView.setText(obj);
                            BLEService.selected_lock_name = obj;
                            new SweetAlertDialog(MainActivity.this, 2).setTitleText("").setContentText(view.getResources().getString(R.string.update_successfully)).setConfirmText(MainActivity.this.getString(R.string.ok)).show();
                        }
                        textView.setVisibility(0);
                        editText.setVisibility(8);
                        linearLayout.setVisibility(8);
                        if (!MainActivity.animation.isRunning()) {
                            MainActivity.animation.start();
                        }
                        TaskManagement.current_function = 48;
                        MainActivity.ready_2_pair = true;
                    }
                }).setCancelText(MainActivity.this.getString(R.string.no)).setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.MainActivity.48.1
                    @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismissWithAnimation();
                        textView.setVisibility(0);
                        editText.setVisibility(4);
                        linearLayout.setVisibility(4);
                        if (!MainActivity.animation.isRunning()) {
                            MainActivity.animation.start();
                        }
                        MainActivity.ready_2_pair = true;
                    }
                }).show();
            }
        });
        ((Button) findViewById(R.id.button_new_lock_name_cancel)).setOnClickListener(new View.OnClickListener() { // from class: com.egeetouch.egeetouch_manager.MainActivity.49
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                textView.setVisibility(0);
                editText.setVisibility(4);
                linearLayout.setVisibility(4);
                if (!MainActivity.animation.isRunning()) {
                    MainActivity.animation.start();
                }
                MainActivity.ready_2_pair = true;
            }
        });
    }

    public void btn_retype_password(View view) {
        if (animation.isRunning()) {
            animation.stop();
        }
        fragmentManager.beginTransaction().replace(R.id.container, Fragment_retype_password.newInstance()).addToBackStack("17").commit();
    }

    public void btn_resave_pair(View view) {
        EditText editText = (EditText) findViewById(R.id.editText_primarypassword2);
        hideKeyboard(view);
        if (!editText.getText().toString().equals("")) {
            if (editText.getText().length() >= 6) {
                String obj = editText.getText().toString();
                DatabaseVariable.db_lock.execSQL(DatabaseVariable.lockdb_delete_value(BLEService.selected_lock_name));
                DatabaseVariable.db_lock.execSQL(this.mDatabaseVariable.lockdb_insert_value(BLEService.selected_lock_name, obj, "model2", AppEventsConstants.EVENT_PARAM_VALUE_NO, AppEventsConstants.EVENT_PARAM_VALUE_NO, AppEventsConstants.EVENT_PARAM_VALUE_NO, AppEventsConstants.EVENT_PARAM_VALUE_NO, AppEventsConstants.EVENT_PARAM_VALUE_NO, AppEventsConstants.EVENT_PARAM_VALUE_NO, AppEventsConstants.EVENT_PARAM_VALUE_NO, "", null, null));
                TaskManagement.password_varified = false;
                TaskManagement.password_varified_correct = false;
                TaskManagement.current_function = 48;
                fragmentManager.beginTransaction().replace(R.id.container, Fragment_pairing.newInstance()).addToBackStack("20").commit();
                return;
            }
            editText.setError(getString(R.string.password_length));
            return;
        }
        editText.setError(getString(R.string.please_key_in_password));
    }

    public void btn_manage_user_nfc(View view) {
        fragmentManager.beginTransaction().replace(R.id.container, Fragment_manage_user_nfc.newInstance()).addToBackStack("8").commit();
    }

    public void btn_add_user_nfc(View view) {
        if (Helper_PhoneDetails.phonelangauge().equals("ja")) {
            new SweetAlertDialog(this, 1).setTitleText("").setContentText(view.getResources().getString(R.string.only_alphanumeric_characters_are_accepted_for_user_name_and_password)).setConfirmText(getString(R.string.ok)).show();
        }
        fragmentManager.beginTransaction().replace(R.id.container, Fragment_add_user_nfc.newInstance()).addToBackStack("8").commit();
    }

    public void btn_save_newUser(View view) {
        EditText editText = (EditText) findViewById(R.id.editText_newusername);
        user_password1 = (EditText) findViewById(R.id.editText_userpassword);
        user_password2 = (EditText) findViewById(R.id.editText_userpassword2);
        Cursor rawQuery = DatabaseVariable.db_user.rawQuery(DatabaseVariable.userdb_Query_user_name_in_lock(BLEService.selected_lock_name), null);
        int i = 0;
        while (rawQuery.moveToNext()) {
            i++;
        }
        rawQuery.close();
        int i2 = 5;
        if (BLEService.selected_lock_model.equals("GT2000") || BLEService.selected_lock_model.equals("GT3000")) {
            i2 = 20;
        } else if (!BLEService.selected_lock_model.equals("GT1000") && !BLEService.selected_lock_model.equals("GT2002")) {
            BLEService.selected_lock_model.equals("GT3100");
        }
        if (i < i2) {
            if (!editText.getText().toString().equals("")) {
                if (!user_password1.getText().toString().equals("") && !user_password2.getText().toString().equals("")) {
                    if (user_password1.getText().toString().equals(user_password2.getText().toString())) {
                        if (user_password1.getText().length() >= 6) {
                            hideKeyboard(view);
                            Cursor rawQuery2 = DatabaseVariable.db_user.rawQuery(DatabaseVariable.userdb_Query_user_name_exist_in_lock(BLEService.selected_lock_name, editText.getText().toString()), null);
                            if (rawQuery2.moveToNext()) {
                                Toast.makeText(this, view.getResources().getString(R.string.user_name_used), 0).show();
                            } else {
                                Cursor rawQuery3 = DatabaseVariable.db_lock.rawQuery(DatabaseVariable.lockdb_Query_model(BLEService.selected_lock_name), null);
                                String string = rawQuery3.moveToNext() ? rawQuery3.getString(1) : "      ";
                                rawQuery3.close();
                                Cursor rawQuery4 = DatabaseVariable.db_user.rawQuery(DatabaseVariable.userdb_Query_password_exist_in_lock(BLEService.selected_lock_name, user_password1.getText().toString()), null);
                                if (string.equals(user_password1.getText().toString())) {
                                    Toast.makeText(this, view.getResources().getString(R.string.password_used), 0).show();
                                } else if (rawQuery4.moveToNext()) {
                                    Toast.makeText(this, view.getResources().getString(R.string.password_used), 0).show();
                                } else {
                                    DatabaseVariable.db_user.execSQL(DatabaseVariable.userdb_insert_value(editText.getText().toString(), user_password1.getText().toString(), BLEService.selected_lock_name));
                                }
                            }
                            rawQuery2.close();
                            fragmentManager.popBackStack();
                            return;
                        }
                        user_password1.setError(getString(R.string.password_length));
                        return;
                    }
                    user_password1.setError(getString(R.string.password_is_not_identical));
                    return;
                } else if (user_password1.getText().toString().equals("")) {
                    user_password1.setError(getString(R.string.please_key_in_password));
                    return;
                } else {
                    user_password2.setError(getString(R.string.please_key_in_password));
                    return;
                }
            }
            editText.setError(getString(R.string.please_enter_user_name));
            return;
        }
        new SweetAlertDialog(this, 1).setTitleText("").setContentText(view.getResources().getString(R.string.max_user)).setConfirmText(getString(R.string.ok)).show();
    }

    public void btn_update_user_lists(View view) {
        TaskManagement.current_function = 81;
        TaskManagement.admin_update_done = false;
        TaskManagement.user_update_done = false;
        TaskManagement.tag_update_done = false;
        TaskManagement.log_extract10_done = false;
        ready_2_pair = true;
        fragmentManager.beginTransaction().replace(R.id.container, Fragment_update.newInstance()).addToBackStack("8").commit();
    }

    public void btn_manage_tag_nfc(View view) {
        ready_2_pair = true;
        fragmentManager.beginTransaction().replace(R.id.container, Fragment_add_tag_nfc.newInstance()).addToBackStack("8").commit();
    }

    public void btn_add_tag_nfc(View view) {
        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(this);
        look_for_nfc_tag = sweetAlertDialog;
        sweetAlertDialog.setTitleText("").setContentText(view.getResources().getString(R.string.locate_your_tag_to_phone_nfc_antenna)).setConfirmText(getString(R.string.ok)).show();
        look_4_tags = true;
    }

    public void btn_update_tag_lists(View view) {
        TaskManagement.current_function = 16;
        TaskManagement.admin_update_done = false;
        TaskManagement.user_update_done = false;
        TaskManagement.tag_update_done = false;
        TaskManagement.log_extract10_done = false;
        ready_2_pair = true;
        fragmentManager.beginTransaction().replace(R.id.container, Fragment_update.newInstance()).addToBackStack("8").commit();
    }

    public void btn_retrieve_tag_lists(View view) {
        TaskManagement.current_function = 128;
        TaskManagement.admin_update_done = false;
        TaskManagement.user_update_done = false;
        TaskManagement.tag_update_done = false;
        TaskManagement.log_extract10_done = false;
        ready_2_pair = true;
        fragmentManager.beginTransaction().replace(R.id.container, Fragment_update.newInstance()).addToBackStack("8").commit();
    }

    public void btn_audit_trail_nfc(View view) {
        shutdown_status = 0;
        fragmentManager.beginTransaction().replace(R.id.container, Fragment_manage_audit_trail_nfc.newInstance()).addToBackStack("8").commit();
    }

    public void btn_update_auditTrial_nfc(View view) {
        TaskManagement.current_function = TaskManagement.log_extract10;
        TaskManagement.admin_update_done = false;
        TaskManagement.user_update_done = false;
        TaskManagement.tag_update_done = false;
        TaskManagement.log_extract10_done = false;
        ready_2_pair = true;
        fragmentManager.beginTransaction().replace(R.id.container, Fragment_update.newInstance()).addToBackStack("8").commit();
    }

    public void btn_dfu(View view) {
        startActivity(new Intent(this, DfuActivity.class));
    }

    public void hideKeyboard(View view) {
        ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void initialize_database() {
        this.mDatabaseVariable = new DatabaseVariable(this);
        DatabaseVariable.db_lock = openOrCreateDatabase(DatabaseVariable.DB_name_lock, 0, null);
        DatabaseVariable.db_lock.execSQL(DatabaseVariable.Create_lock_db);
        DatabaseVariable.db_user = openOrCreateDatabase(DatabaseVariable.DB_name_user, 0, null);
        DatabaseVariable.db_user.execSQL(DatabaseVariable.Create_user_db);
        DatabaseVariable.db_tag = openOrCreateDatabase(DatabaseVariable.DB_name_tag, 0, null);
        DatabaseVariable.db_tag.execSQL(DatabaseVariable.Create_tag_db);
        DatabaseVariable.db_audittrail = openOrCreateDatabase(DatabaseVariable.DB_name_audittrail, 0, null);
        DatabaseVariable.db_audittrail.execSQL(DatabaseVariable.Create_audittrail_db);
        DatabaseVariable.db_location = openOrCreateDatabase(DatabaseVariable.DB_name_location, 0, null);
        DatabaseVariable.db_location.execSQL(DatabaseVariable.Create_location_db);
    }

    public void getNumberOfLocksOnFirebaseAndRefresh() {
        customProgressBar.ShowProgressBar(context, "syncLock");
        if (Helper_Network.haveNetworkConnection(context)) {
            list.clear();
            DatabaseVariable.db_lock.execSQL(DatabaseVariable.lockdb_delete_everything());
            SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(this, 5);
            this.loading_dialog = sweetAlertDialog;
            sweetAlertDialog.getProgressHelper().setBarColor(Color.parseColor("#389ae0"));
            this.loading_dialog.setTitleText(getString(R.string.dashboard));
            this.loading_dialog.setContentText(getString(R.string.loading));
            dashboard_loading_firstTime = true;
            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            this.database = firebaseDatabase;
            firebaseDatabase.getReference("userLocks").child(user_uid).addListenerForSingleValueEvent(new ValueEventListener() { // from class: com.egeetouch.egeetouch_manager.MainActivity.51
                @Override // com.google.firebase.database.ValueEventListener
                public void onCancelled(DatabaseError databaseError) {
                }

                @Override // com.google.firebase.database.ValueEventListener
                public void onDataChange(DataSnapshot dataSnapshot) {
                    MainActivity.numberOfLocksOnFirebase = (int) dataSnapshot.getChildrenCount();
                    if (MainActivity.numberOfLocksOnFirebase == 0) {
                        System.out.println("HEY NO LOCKS ON CLOUD");
                        DatabaseVariable.db_lock.execSQL(DatabaseVariable.lockdb_delete_everything());
                        MainActivity.this.show_dashboard_list();
                        customProgressBar.closeDialog(0L);
                        MainActivity.dashboard_loading_firstTime = false;
                        SweetAlertDialog sweetAlertDialog2 = new SweetAlertDialog(MainActivity.context, 0);
                        sweetAlertDialog2.setTitleText(MainActivity.this.getString(R.string.pls_note));
                        sweetAlertDialog2.setContentText(MainActivity.this.getString(R.string.no_locks_on_firebase));
                        sweetAlertDialog2.show();
                        return;
                    }
                    MainActivity.currentTimestamp = String.valueOf(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()));
                    SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(MainActivity.this.getApplicationContext()).edit();
                    edit.putString("currentTimeStamp", MainActivity.currentTimestamp);
                    edit.commit();
                    MainActivity.this.read_online_database();
                }
            });
            if (user_uid != null) {
                try {
                    FirebaseDatabase.getInstance().getReference("userLoginDetails").child("MobileAppLogins").child(user_uid).child(RemoteConfigConstants.RequestFieldKey.APP_VERSION).setValue(getPackageManager().getPackageInfo(getPackageName(), 0).versionName);
                    FirebaseDatabase.getInstance().getReference("userLoginDetails").child("MobileAppLogins").child(user_uid).child("lastLoginMobilePlatform").setValue("Android");
                    FirebaseDatabase.getInstance().getReference("userLoginDetails").child("MobileAppLogins").child(user_uid).child(ServerValues.NAME_OP_TIMESTAMP).setValue(Long.valueOf(System.currentTimeMillis() / 1000));
                } catch (PackageManager.NameNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private int getNumberOfLocksOnPhoneDB() {
        return DatabaseVariable.db_lock.rawQuery(DatabaseVariable.lockdb_Query(), null).getCount();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void read_online_database() {
        if (Helper_Network.haveNetworkConnection(context)) {
            list.clear();
            current_lock_number = 0;
            currentTimestamp = String.valueOf(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()));
            SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit();
            edit.putString("currentTimeStamp", currentTimestamp);
            edit.commit();
            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            this.database = firebaseDatabase;
            DatabaseReference reference = firebaseDatabase.getReference("Registered_user");
            this.lock = reference;
            this.lock = reference.child(user_uid);
            DatabaseVariable.db_lock.execSQL(DatabaseVariable.lockdb_delete_everything());
            this.database.getReference("userLocks").child(user_uid).addListenerForSingleValueEvent(new ValueEventListener() { // from class: com.egeetouch.egeetouch_manager.MainActivity.52
                @Override // com.google.firebase.database.ValueEventListener
                public void onCancelled(DatabaseError databaseError) {
                }

                /* JADX WARN: Removed duplicated region for block: B:84:0x01ed A[LOOP:1: B:84:0x01ed->B:86:0x01f5, LOOP_START, PHI: r2 r3 
                  PHI: (r2v37 java.lang.String) = (r2v31 java.lang.String), (r2v43 java.lang.String) binds: [B:83:0x01eb, B:86:0x01f5] A[DONT_GENERATE, DONT_INLINE]
                  PHI: (r3v6 int) = (r3v0 int), (r3v7 int) binds: [B:83:0x01eb, B:86:0x01f5] A[DONT_GENERATE, DONT_INLINE]] */
                /* JADX WARN: Removed duplicated region for block: B:88:0x0223  */
                @Override // com.google.firebase.database.ValueEventListener
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public void onDataChange(com.google.firebase.database.DataSnapshot r27) {
                    java.lang.String r2;
                    java.lang.String r15;
                    com.egeetouch.egeetouch_manager.MainActivity.list.clear();
                    com.egeetouch.egeetouch_manager.MainActivity.firebaseLockList.clear();
                    com.egeetouch.egeetouch_manager.MainActivity.list_admin_lock.clear();
                    com.egeetouch.egeetouch_manager.MainActivity.numberOfLocksOnFirebase = r27.getChildrenCount();
                    java.util.Iterator<com.google.firebase.database.DataSnapshot> r1 = r27.getChildren().iterator();
                    while (true) {
                        int r3 = 0;
                        if (!r1.hasNext()) {
                            break;
                        }
                        com.google.firebase.database.DataSnapshot r2 = r1.next();
                        com.google.firebase.database.DatabaseReference r5 = r2.getRef().child("Name");
                        double r7 = com.google.firebase.remoteconfig.FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
                        java.lang.Double.valueOf((double) com.google.firebase.remoteconfig.FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
                        java.lang.Double.valueOf((double) com.google.firebase.remoteconfig.FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
                        r2.getRef().getKey();
                        java.util.HashMap r2 = (java.util.HashMap) r2.getValue();
                        java.lang.String r0 = com.egeetouch.egeetouch_manager.MainActivity.lock_name_temp = r2.containsKey("Name") ? r2.get("Name").toString() : "";
                        java.lang.String r6 = r2.containsKey("Type") ? r2.get("Type").toString() : "";
                        java.lang.String r9 = r2.containsKey("Password") ? r2.get("Password").toString() : "";
                        java.lang.String r18 = r2.containsKey("ip45SerialNumber") ? r2.get("ip45SerialNumber").toString() : "";
                        java.lang.String r24 = r2.containsKey("isOfflineLock") ? r2.get("isOfflineLock").toString() : "";
                        java.lang.String r11 = r2.containsKey("sharedBy") ? r2.get("sharedBy").toString() : "";
                        if (r2.containsKey("shareStartTime")) {
                            r2.get("shareStartTime").toString();
                        }
                        if (r2.containsKey("shareEndTime")) {
                            r2.get("shareEndTime").toString();
                        }
                        if (r2.containsKey("ownedBy")) {
                            r2.get("ownedBy").toString();
                        }
                        java.lang.String r25 = r2.containsKey("shareAccessToken") ? r2.get("shareAccessToken").toString() : "";
                        if (r2.containsKey("sharingMode")) {
                            r2.get("sharingMode").toString();
                        }
                        if (r2.containsKey("shareCount")) {
                            java.lang.Integer.valueOf(r2.get("shareCount").toString()).intValue();
                        }
                        java.lang.System.out.println("check tenp lock model" + r6);
                        double r14 = r2.containsKey("shareStartTime") ? java.lang.Double.valueOf(r2.get("shareStartTime").toString()).doubleValue() : 0.0d;
                        if (r2.containsKey("shareEndTime")) {
                            r7 = java.lang.Double.valueOf(r2.get("shareEndTime").toString()).doubleValue();
                        }
                        java.lang.String r21 = java.lang.String.valueOf((long) r14);
                        java.lang.String r22 = java.lang.String.valueOf((long) r7);
                        if (r6.equals("4")) {
                            r2 = "GT2100";
                        } else if (r6.equals(androidx.exifinterface.media.ExifInterface.GPS_MEASUREMENT_3D)) {
                            r2 = "GT2002";
                        } else {
                            if (!r6.equals(androidx.exifinterface.media.ExifInterface.GPS_MEASUREMENT_2D)) {
                                if (r6.equals("10")) {
                                    r2 = "GT2500";
                                } else if (r6.equals(com.facebook.appevents.AppEventsConstants.EVENT_PARAM_VALUE_YES)) {
                                    r2 = "GT3002";
                                } else if (r6.equals("7")) {
                                    r2 = "GT5300";
                                } else if (r6.equals("8")) {
                                    r2 = "GT5107";
                                } else if (r6.equals("9")) {
                                    r2 = "GT5109";
                                } else if (r6.equals("11")) {
                                    r2 = "GT2550";
                                }
                            }
                            r15 = "GT1000";
                            java.lang.String r14 = com.egeetouch.egeetouch_manager.MainActivity.this.decryptPassword(r9);
                            java.lang.String r2 = com.egeetouch.egeetouch_manager.MainActivity.lock_name_temp;
                            if (!r11.contains("@")) {
                                while (com.egeetouch.egeetouch_manager.MainActivity.list.contains(r2)) {
                                    r3++;
                                    r2 = com.egeetouch.egeetouch_manager.MainActivity.lock_name_temp + "(" + java.lang.String.valueOf(r3) + ")";
                                }
                                r5.setValue(r2);
                                java.lang.String r0 = com.egeetouch.egeetouch_manager.MainActivity.lock_name_temp = r2;
                            } else if (!r11.contains("@")) {
                                com.egeetouch.egeetouch_manager.MainActivity.list_admin_lock.add(com.egeetouch.egeetouch_manager.MainActivity.lock_name_temp);
                            }
                            com.egeetouch.egeetouch_manager.DatabaseVariable.db_lock.execSQL(com.egeetouch.egeetouch_manager.MainActivity.this.mDatabaseVariable.lockdb_insert_value(com.egeetouch.egeetouch_manager.MainActivity.lock_name_temp, r14, r15, com.facebook.appevents.AppEventsConstants.EVENT_PARAM_VALUE_NO, com.facebook.appevents.AppEventsConstants.EVENT_PARAM_VALUE_NO, r18, com.facebook.appevents.AppEventsConstants.EVENT_PARAM_VALUE_NO, com.facebook.appevents.AppEventsConstants.EVENT_PARAM_VALUE_NO, r21, r22, r11, r24, r25));
                            com.egeetouch.egeetouch_manager.MainActivity.list.add(com.egeetouch.egeetouch_manager.MainActivity.lock_name_temp);
                            com.egeetouch.egeetouch_manager.MainActivity.firebaseLockList.add(com.egeetouch.egeetouch_manager.MainActivity.lock_name_temp);
                            com.egeetouch.egeetouch_manager.MainActivity.access$1808();
                        }
                        r15 = r2;
                        java.lang.String r14 = com.egeetouch.egeetouch_manager.MainActivity.this.decryptPassword(r9);
                        java.lang.String r2 = com.egeetouch.egeetouch_manager.MainActivity.lock_name_temp;
                        if (!r11.contains("@")) {
                        }
                        com.egeetouch.egeetouch_manager.DatabaseVariable.db_lock.execSQL(com.egeetouch.egeetouch_manager.MainActivity.this.mDatabaseVariable.lockdb_insert_value(com.egeetouch.egeetouch_manager.MainActivity.lock_name_temp, r14, r15, com.facebook.appevents.AppEventsConstants.EVENT_PARAM_VALUE_NO, com.facebook.appevents.AppEventsConstants.EVENT_PARAM_VALUE_NO, r18, com.facebook.appevents.AppEventsConstants.EVENT_PARAM_VALUE_NO, com.facebook.appevents.AppEventsConstants.EVENT_PARAM_VALUE_NO, r21, r22, r11, r24, r25));
                        com.egeetouch.egeetouch_manager.MainActivity.list.add(com.egeetouch.egeetouch_manager.MainActivity.lock_name_temp);
                        com.egeetouch.egeetouch_manager.MainActivity.firebaseLockList.add(com.egeetouch.egeetouch_manager.MainActivity.lock_name_temp);
                        com.egeetouch.egeetouch_manager.MainActivity.access$1808();
                    }
                    if (com.egeetouch.egeetouch_manager.MainActivity.numberOfLocksOnFirebase == com.egeetouch.egeetouch_manager.MainActivity.current_lock_number && com.egeetouch.egeetouch_manager.MainActivity.dashboard_loading_firstTime.booleanValue()) {
                        com.egeetouch.egeetouch_manager.customProgressBar.closeDialog(300L);
                        com.egeetouch.egeetouch_manager.MainActivity.dashboard_loading_firstTime = false;
                    }
                    com.egeetouch.egeetouch_manager.MainActivity.this.show_dashboard_list();
                }
            });
            return;
        }
        Log.i("Databse", "HEY from read_online_database got no internet connection");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void show_dashboard_list() {
        dashboard_listview = (ListView) findViewById(R.id.listview_dashboard);
        ArrayAdapter_dashboard arrayAdapter_dashboard = new ArrayAdapter_dashboard(this, list);
        dashboard_listview.setAdapter((ListAdapter) arrayAdapter_dashboard);
        arrayAdapter_dashboard.notifyDataSetChanged();
        lock_settings_status = false;
        UI_BLE.add_lock_mode = false;
        stopBackStack = false;
        SwipeDismissListViewTouchListener swipeDismissListViewTouchListener = new SwipeDismissListViewTouchListener(dashboard_listview, new SwipeDismissListViewTouchListener.DismissCallbacks() { // from class: com.egeetouch.egeetouch_manager.MainActivity.53
            @Override // com.egeetouch.egeetouch_manager.SwipeDismissListViewTouchListener.DismissCallbacks
            public boolean canDismiss(int i) {
                return true;
            }

            @Override // com.egeetouch.egeetouch_manager.SwipeDismissListViewTouchListener.DismissCallbacks
            public void onDismiss(final ListView listView, int[] iArr, final int i) {
                try {
                    MainActivity.clickFlag = 2;
                    MainActivity.lock_settings_status = false;
                    if (MainActivity.current_icon == 0) {
                        BLEService.selected_lock_name = MainActivity.list.get(i);
                        TaskManagement.password_varified = false;
                        TaskManagement.password_varified_correct = false;
                        final String str = MainActivity.list.get(i);
                        Cursor rawQuery = DatabaseVariable.db_lock.rawQuery(DatabaseVariable.lockdb_Query_model(str), null);
                        final String string = rawQuery.moveToNext() ? rawQuery.getString(5) : "";
                        listView.getChildAt(i - listView.getFirstVisiblePosition()).setVisibility(4);
                        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(MainActivity.context);
                        sweetAlertDialog.setTitleText(MainActivity.context.getResources().getString(R.string.delete_lock));
                        sweetAlertDialog.setContentText(MainActivity.context.getResources().getString(R.string.are_you_sure_you_want_to_delete_jp) + "?");
                        sweetAlertDialog.setConfirmText(MainActivity.context.getResources().getString(R.string.yes));
                        sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.MainActivity.53.1
                            @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                            public void onClick(SweetAlertDialog sweetAlertDialog2) {
                                sweetAlertDialog2.dismissWithAnimation();
                                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                                firebaseDatabase.getReference("userLocks").child(MainActivity.user_uid).orderByChild("Name").equalTo(str).addListenerForSingleValueEvent(new ValueEventListener() { // from class: com.egeetouch.egeetouch_manager.MainActivity.53.1.1
                                    @Override // com.google.firebase.database.ValueEventListener
                                    public void onCancelled(DatabaseError databaseError) {
                                    }

                                    @Override // com.google.firebase.database.ValueEventListener
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        if (dataSnapshot.exists()) {
                                            for (DataSnapshot dataSnapshot2 : dataSnapshot.getChildren()) {
                                                Log.i("Tag", "Delete in userLocks: " + dataSnapshot2.getRef());
                                                dataSnapshot2.getRef().removeValue();
                                            }
                                        }
                                    }
                                });
                                firebaseDatabase.getReference("MyAdminLocks").child(MainActivity.user_uid).orderByChild("ip45SerialNumber").equalTo(string).addListenerForSingleValueEvent(new ValueEventListener() { // from class: com.egeetouch.egeetouch_manager.MainActivity.53.1.2
                                    @Override // com.google.firebase.database.ValueEventListener
                                    public void onCancelled(DatabaseError databaseError) {
                                    }

                                    @Override // com.google.firebase.database.ValueEventListener
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        if (dataSnapshot.exists()) {
                                            for (DataSnapshot dataSnapshot2 : dataSnapshot.getChildren()) {
                                                Log.i("Tag", "Delete in MyAdminLocks: " + dataSnapshot2.getRef());
                                                dataSnapshot2.getRef().removeValue();
                                            }
                                        }
                                    }
                                });
                                DatabaseVariable.db_lock.execSQL(DatabaseVariable.lockdb_delete_value(MainActivity.list.get(i)));
                                DatabaseVariable.db_user.execSQL(DatabaseVariable.userdb_delete_value_bylock(MainActivity.list.get(i)));
                                DatabaseVariable.db_tag.execSQL(DatabaseVariable.tagdb_delete_value_bylock(MainActivity.list.get(i)));
                                DatabaseVariable.db_audittrail.execSQL(DatabaseVariable.audittrail_db_delete_value_bylock(MainActivity.list.get(i)));
                                DatabaseVariable.db_location.execSQL(DatabaseVariable.locationdb_delete_value_bylock(MainActivity.list.get(i)));
                                MainActivity.list.remove(i);
                                while (MainActivity.list_admin_lock.contains(BLEService.selected_lock_name)) {
                                    MainActivity.list_admin_lock.remove(BLEService.selected_lock_name);
                                }
                                ArrayAdapter_dashboard arrayAdapter_dashboard2 = new ArrayAdapter_dashboard(MainActivity.this, MainActivity.list);
                                MainActivity.dashboard_listview.setAdapter((ListAdapter) arrayAdapter_dashboard2);
                                arrayAdapter_dashboard2.notifyDataSetChanged();
                                MainActivity.clickFlag = 0;
                                SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                                if (BLEService.selected_lock_name.equals(defaultSharedPreferences.getString("last_selected_lock_name", ""))) {
                                    SharedPreferences.Editor edit = defaultSharedPreferences.edit();
                                    edit.putString("last_selected_lock_name", "");
                                    edit.commit();
                                }
                            }
                        });
                        sweetAlertDialog.setCancelText(MainActivity.context.getResources().getString(R.string.no));
                        sweetAlertDialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.MainActivity.53.2
                            @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                            public void onClick(SweetAlertDialog sweetAlertDialog2) {
                                sweetAlertDialog2.dismissWithAnimation();
                                MainActivity.clickFlag = 0;
                            }
                        });
                        sweetAlertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.egeetouch.egeetouch_manager.MainActivity.53.3
                            @Override // android.content.DialogInterface.OnDismissListener
                            public void onDismiss(DialogInterface dialogInterface) {
                                ListView listView2 = listView;
                                if (listView2.getChildAt(i - listView2.getFirstVisiblePosition()) != null) {
                                    try {
                                        ListView listView3 = listView;
                                        listView3.getChildAt(i - listView3.getFirstVisiblePosition()).setVisibility(0);
                                    } catch (Exception unused) {
                                    }
                                }
                            }
                        });
                        sweetAlertDialog.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.egeetouch.egeetouch_manager.MainActivity.53.4
                            @Override // android.content.DialogInterface.OnCancelListener
                            public void onCancel(DialogInterface dialogInterface) {
                                try {
                                    ListView listView2 = listView;
                                    listView2.getChildAt(i - listView2.getFirstVisiblePosition()).setVisibility(0);
                                } catch (Exception unused) {
                                }
                            }
                        });
                        sweetAlertDialog.show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        dashboard_listview.setOnTouchListener(swipeDismissListViewTouchListener);
        dashboard_listview.setOnScrollListener(swipeDismissListViewTouchListener.makeScrollListener());
        dashboard_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.egeetouch.egeetouch_manager.MainActivity.54
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                UI_BLE.add_lock_mode = false;
                UI_BLE.having_trouble = false;
                if (i < 10) {
                    if (MainActivity.this.isBluetoothEnabled().booleanValue() && MainActivity.this.isLocationPermissionEnabled() && MainActivity.this.checkGps()) {
                        Log.i("Connection", "Location is enabled");
                        MainActivity.lock_settings_status = false;
                        MainActivity.this.currentTimeStamp = System.currentTimeMillis() / 1000;
                        MainActivity mainActivity = MainActivity.this;
                        mainActivity.currentTimeStampDouble = Double.valueOf(mainActivity.currentTimeStamp);
                        MainActivity.lock_settings_status = false;
                        MainActivity.this.mhandler_task = new Handler();
                        MainActivity.this.mhandler_task.post(MainActivity.this.task);
                        MainActivity.slide_enable = false;
                        MainActivity.this.handler.removeCallbacks(MainActivity.this.runnable_slide_photo);
                        BLEService.skip_verify_password_UI = false;
                        BLEService.shackleByPassOnce_isEnabled = false;
                        BLEService.mConnectionState = 0;
                        MainActivity.current_icon = 0;
                        MainActivity.showTagPopUp = true;
                        if (MainActivity.clickFlag != 2) {
                            Log.i("TAG", "current_icon: " + String.valueOf(MainActivity.current_icon));
                            Log.i("TAG", "add_lock_pressed: " + String.valueOf(MainActivity.this.add_lock_pressed));
                            if (MainActivity.current_icon == 0) {
                                MainActivity.scanning_new_lock = false;
                                MainActivity.add_old_lock = false;
                                MainActivity.current_icon = 1;
                                BLEService.selected_lock_name = MainActivity.list.get(i);
                                MainActivity.child_index = MainActivity.this.lock_index_name[i + 1];
                                Log.i("Tag", "Main child_index: " + MainActivity.child_index);
                                Log.i("Tag", "BLEService.selected_lock_name: " + BLEService.selected_lock_name);
                                Cursor rawQuery = DatabaseVariable.db_lock.rawQuery(DatabaseVariable.lockdb_Query_model(BLEService.selected_lock_name), null);
                                if (rawQuery.moveToNext()) {
                                    BLEService.selected_lock_password = rawQuery.getString(1);
                                    BLEService.selected_lock_model = rawQuery.getString(2);
                                    BLEService.selected_lock_version = rawQuery.getString(3);
                                    BLEService.selectedLockIP45Serial = rawQuery.getString(5);
                                    BLEService.selected_lock_address = rawQuery.getString(6);
                                    MainActivity.this.selected_Start_Date = Double.valueOf(rawQuery.getString(8));
                                    MainActivity.this.selected_End_Date = Double.valueOf(rawQuery.getString(9));
                                    MainActivity.selected_share_lock_sharedByEmail = rawQuery.getString(10);
                                    MainActivity.selected_isOfflineLock = true;
                                    MainActivity.selected_shareAccessToken = rawQuery.getString(12);
                                    String string = rawQuery.getString(11);
                                    if (!rawQuery.getString(11).equals("")) {
                                        MainActivity.selected_isOfflineLock = Boolean.valueOf(rawQuery.getString(11)).booleanValue();
                                        MainActivity.selected_shareAccessToken = rawQuery.getString(12);
                                        System.out.println("Hello checking dashboard value from SQLite1:" + rawQuery.getString(11) + " Token:" + string.isEmpty());
                                    }
                                    System.out.println("Hello checking dashboard value from SQLite:" + MainActivity.selected_isOfflineLock + " Token:" + MainActivity.selected_shareAccessToken.length());
                                }
                                rawQuery.close();
                                if (BLEService.selected_lock_password.equals("")) {
                                    MainActivity.current_icon = 0;
                                    MainActivity.this.password_null_error = new SweetAlertDialog(MainActivity.context, 3);
                                    MainActivity.this.password_null_error.setTitleText(MainActivity.this.getString(R.string.error));
                                    MainActivity.this.password_null_error.setContentText(MainActivity.this.getString(R.string.password_null_error));
                                    MainActivity.this.password_null_error.show();
                                    return;
                                }
                                if (MainActivity.selected_share_lock_sharedByEmail.equals("")) {
                                    MainActivity.selected_lock_is_shared = false;
                                } else {
                                    MainActivity.selected_lock_is_shared = true;
                                }
                                Log.i("TAG", BLEService.selected_lock_name + "," + BLEService.selected_lock_password + "," + BLEService.selected_lock_model + "," + BLEService.selected_lock_version + "," + BLEService.selected_lock_address);
                                TaskManagement.current_function = 48;
                                TaskManagement.password_varified = false;
                                TaskManagement.password_varified_correct = false;
                                Log.i("TAG", "selected_lock_model: " + BLEService.selected_lock_model);
                                if (MainActivity.selected_isOfflineLock) {
                                    MainActivity.allow_lock_access = true;
                                    MainActivity.this.lock_access();
                                } else if (!MainActivity.selected_isOfflineLock) {
                                    MainActivity.this.checkingOnlineAccess();
                                } else {
                                    MainActivity.allow_lock_access = true;
                                    MainActivity.this.lock_access();
                                }
                                if (BLEService.selected_lock_model.equals("GT2000") || BLEService.selected_lock_model.equals("GT3000")) {
                                    if (!MainActivity.mNFCAdapter.isEnabled()) {
                                        new SweetAlertDialog(MainActivity.this, 3).setTitleText("").setContentText(MainActivity.this.getString(R.string.NFC_is_not_turned_on)).setConfirmText(MainActivity.this.getString(R.string.ok)).show();
                                    } else {
                                        TaskManagement.current_function = 48;
                                        MainActivity.fragmentManager.beginTransaction().replace(R.id.container, Fragment_pairing.newInstance()).addToBackStack("29").commit();
                                    }
                                }
                                MainActivity.clickFlag = 0;
                                return;
                            }
                            return;
                        }
                        return;
                    }
                    return;
                }
                new SweetAlertDialog(MainActivity.context).setTitleText(MainActivity.context.getResources().getString(R.string.lock_limit)).setContentText("You can access only 10 locks").setConfirmText(MainActivity.context.getResources().getString(R.string.ok)).setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.MainActivity.54.1
                    @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismissWithAnimation();
                    }
                }).show();
            }
        });
    }

    public void retractable_sharing_dashboard() {
        try {
            if (Helper_Network.haveNetworkConnection(context)) {
                customProgressBar.ShowProgressBar(context, "permission");
                SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(this, 5);
                this.pDialog = sweetAlertDialog;
                sweetAlertDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                this.pDialog.setTitleText(getString(R.string.processing));
                this.pDialog.setCancelable(true);
                this.RetractAccessChildCount = 0L;
                tokenFound = false;
                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                this.database = firebaseDatabase;
                firebaseDatabase.getReference("registeredUsersOnPlatform").addListenerForSingleValueEvent(new AnonymousClass55());
            } else {
                clickFlag = 0;
                current_icon = 0;
                SweetAlertDialog sweetAlertDialog2 = new SweetAlertDialog(context, 0);
                sweetAlertDialog2.setTitleText(getString(R.string.pls_note));
                sweetAlertDialog2.setContentText("Sorry you need to be in online to access this lock");
                sweetAlertDialog2.show();
                Toast.makeText(getApplicationContext(), "Sorry you need to be in online to access this lock", 1).show();
            }
        } catch (Exception unused) {
            System.out.println("Hello exception");
        }
    }

    /* renamed from: com.egeetouch.egeetouch_manager.MainActivity$55  reason: invalid class name */
    /* loaded from: classes.dex */
    class AnonymousClass55 implements ValueEventListener {
        @Override // com.google.firebase.database.ValueEventListener
        public void onCancelled(DatabaseError databaseError) {
        }

        AnonymousClass55() {
        }

        @Override // com.google.firebase.database.ValueEventListener
        public void onDataChange(DataSnapshot dataSnapshot) {
            Log.i("Tag", "selected_share_lock_sharedByEmail: " + MainActivity.selected_share_lock_sharedByEmail);
            for (DataSnapshot dataSnapshot2 : dataSnapshot.getChildren()) {
                if (dataSnapshot2.getValue().toString().equals(Helper_Firebase.DecodeString(MainActivity.selected_share_lock_sharedByEmail))) {
                    dataSnapshot2.getKey().toString();
                    FirebaseDatabase.getInstance().getReference("retractableSharingRegistry").child(dataSnapshot2.getKey()).addListenerForSingleValueEvent(new AnonymousClass1());
                }
            }
        }

        /* renamed from: com.egeetouch.egeetouch_manager.MainActivity$55$1  reason: invalid class name */
        /* loaded from: classes.dex */
        class AnonymousClass1 implements ValueEventListener {
            @Override // com.google.firebase.database.ValueEventListener
            public void onCancelled(DatabaseError databaseError) {
            }

            AnonymousClass1() {
            }

            @Override // com.google.firebase.database.ValueEventListener
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot2 : dataSnapshot.getChildren()) {
                    MainActivity.this.RetractAccessTotalChild = dataSnapshot.getChildrenCount();
                    dataSnapshot2.getRef().child("shareAccessToken").addListenerForSingleValueEvent(new ValueEventListener() { // from class: com.egeetouch.egeetouch_manager.MainActivity.55.1.1
                        @Override // com.google.firebase.database.ValueEventListener
                        public void onCancelled(DatabaseError databaseError) {
                        }

                        @Override // com.google.firebase.database.ValueEventListener
                        public void onDataChange(DataSnapshot dataSnapshot3) {
                            if (dataSnapshot3.getValue().toString().equals(MainActivity.selected_shareAccessToken)) {
                                MainActivity.tokenFound = true;
                                dataSnapshot3.getRef().getParent().child("allowAccess").addListenerForSingleValueEvent(new ValueEventListener() { // from class: com.egeetouch.egeetouch_manager.MainActivity.55.1.1.1
                                    @Override // com.google.firebase.database.ValueEventListener
                                    public void onCancelled(DatabaseError databaseError) {
                                    }

                                    @Override // com.google.firebase.database.ValueEventListener
                                    public void onDataChange(DataSnapshot dataSnapshot4) {
                                        MainActivity.allowAccess_firebase = dataSnapshot4.getValue().toString();
                                        MainActivity.allowAccess_firebase_boolean = Boolean.valueOf(MainActivity.allowAccess_firebase).booleanValue();
                                        if (MainActivity.allowAccess_firebase_boolean) {
                                            MainActivity.allow_lock_access = true;
                                            customProgressBar.closeDialog(0L);
                                            MainActivity.this.lock_access();
                                        } else if (MainActivity.allowAccess_firebase_boolean) {
                                        } else {
                                            MainActivity.allow_lock_access = false;
                                            customProgressBar.closeDialog(0L);
                                            MainActivity.this.lock_access();
                                        }
                                    }
                                });
                            }
                            MainActivity.this.RetractAccessChildCount++;
                            if (MainActivity.this.RetractAccessTotalChild != MainActivity.this.RetractAccessChildCount || MainActivity.tokenFound) {
                                return;
                            }
                            MainActivity.allow_lock_access = false;
                            customProgressBar.closeDialog(0L);
                            MainActivity.this.lock_access();
                        }
                    });
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkingOnlineAccess() {
        customProgressBar.ShowProgressBar(context, "permission");
        if (Helper_Network.haveNetworkConnection(context)) {
            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            this.database = firebaseDatabase;
            firebaseDatabase.getReference("registeredUsersOnPlatform").orderByValue().equalTo(selected_share_lock_sharedByEmail).addListenerForSingleValueEvent(new AnonymousClass56());
            return;
        }
        clickFlag = 0;
        current_icon = 0;
        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(context, 0);
        sweetAlertDialog.setTitleText(getString(R.string.pls_note));
        sweetAlertDialog.setContentText("Sorry you need to be in online to access this lock");
        sweetAlertDialog.show();
        Toast.makeText(getApplicationContext(), "Sorry you need to be in online to access this lock", 1).show();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.egeetouch.egeetouch_manager.MainActivity$56  reason: invalid class name */
    /* loaded from: classes.dex */
    public class AnonymousClass56 implements ValueEventListener {
        @Override // com.google.firebase.database.ValueEventListener
        public void onCancelled(DatabaseError databaseError) {
        }

        AnonymousClass56() {
        }

        @Override // com.google.firebase.database.ValueEventListener
        public void onDataChange(DataSnapshot dataSnapshot) {
            if (dataSnapshot.exists()) {
                for (final DataSnapshot dataSnapshot2 : dataSnapshot.getChildren()) {
                    System.out.println("Hello checking the checkingOnline : " + dataSnapshot2.getKey());
                    MainActivity.this.database.getReference("userFriendList").child(dataSnapshot2.getKey()).orderByValue().equalTo(MainActivity.email).addListenerForSingleValueEvent(new ValueEventListener() { // from class: com.egeetouch.egeetouch_manager.MainActivity.56.1
                        @Override // com.google.firebase.database.ValueEventListener
                        public void onCancelled(DatabaseError databaseError) {
                        }

                        @Override // com.google.firebase.database.ValueEventListener
                        public void onDataChange(DataSnapshot dataSnapshot3) {
                            if (dataSnapshot3.exists()) {
                                MainActivity.this.database.getReference("retractableSharingRegistry").child(dataSnapshot2.getKey()).orderByChild("shareAccessToken").equalTo(MainActivity.selected_shareAccessToken).addListenerForSingleValueEvent(new ValueEventListener() { // from class: com.egeetouch.egeetouch_manager.MainActivity.56.1.1
                                    @Override // com.google.firebase.database.ValueEventListener
                                    public void onCancelled(DatabaseError databaseError) {
                                    }

                                    @Override // com.google.firebase.database.ValueEventListener
                                    public void onDataChange(DataSnapshot dataSnapshot4) {
                                        if (dataSnapshot4.exists()) {
                                            for (DataSnapshot dataSnapshot5 : dataSnapshot4.getChildren()) {
                                                System.out.println("Hello checking the checkingOnline  retract user: " + dataSnapshot5);
                                                HashMap hashMap = (HashMap) dataSnapshot5.getValue();
                                                MainActivity.allow_lock_access = hashMap.containsKey("allowAccess") ? Boolean.valueOf(hashMap.get("allowAccess").toString()).booleanValue() : false;
                                                customProgressBar.closeDialog(0L);
                                                MainActivity.this.lock_access();
                                            }
                                            return;
                                        }
                                        MainActivity.allow_lock_access = false;
                                        customProgressBar.closeDialog(0L);
                                        MainActivity.this.lock_access();
                                    }
                                });
                                return;
                            }
                            MainActivity.allow_lock_access = false;
                            customProgressBar.closeDialog(0L);
                            MainActivity.this.lock_access();
                        }
                    });
                }
                return;
            }
            MainActivity.allow_lock_access = false;
            customProgressBar.closeDialog(0L);
            MainActivity.this.lock_access();
        }
    }

    public void lock_access() {
        if (allow_lock_access) {
            System.out.println("Hello:" + this.selected_Start_Date);
            if (this.selected_Start_Date.doubleValue() != FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                if (this.currentTimeStampGPS <= 0) {
                    waitForGpsTime();
                    return;
                } else if (this.selected_Start_Date.doubleValue() < this.currentTimeStampGPS && this.selected_End_Date.doubleValue() > this.currentTimeStampGPS) {
                    System.out.println("HEY YOU CAN OPEN IT, WITHIN TIME");
                    UI_BLE.pls_wait_content = context.getResources().getString(R.string.press_power_on_button_to_begin);
                    UI_BLE.BLE_UI = 18;
                    this.ui_ble.update();
                    BLEService.verified_password_done = false;
                    scanning_new_lock = false;
                    start_scanning = true;
                    BLEService.notification_stay_connected = 0;
                    BLEService.notification_stay_connected_showed1 = false;
                    BLEService.notification_stay_connected_showed2 = false;
                    Fragment_BLE.get_init_battery_level_done = false;
                    Fragment_BLE.get_battery_level_counter = 0;
                    Fragment_BLE.reconnect_msg_show = false;
                    return;
                } else {
                    current_icon = 0;
                    System.out.println("HEY TIMING IS NOT RIGHT");
                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(context, 0);
                    sweetAlertDialog.setTitleText(getString(R.string.pls_note));
                    sweetAlertDialog.setContentText(getString(R.string.sorry_no_lock_permission));
                    sweetAlertDialog.show();
                    Toast.makeText(getApplicationContext(), getString(R.string.sorry_no_lock_permission), 1).show();
                    return;
                }
            }
            System.out.println("HEY THIS IS A PERMANENT ACCESS LOCK");
            if ((BLEService.selected_lock_model.equals("GT1000") | BLEService.selected_lock_model.equals("GT1001")) || BLEService.selected_lock_model.equals("GT2002") || BLEService.selected_lock_model.equals("GT3002") || BLEService.selected_lock_model.equals("GT2003") || BLEService.selected_lock_model.equals("GT2100") || BLEService.selected_lock_model.equals("GT5100") || BLEService.selected_lock_model.equals("GT5107") || BLEService.selected_lock_model.equals("GT5109") || BLEService.selected_lock_model.equals("GT5300") || BLEService.selected_lock_model.equals("GT2500") || BLEService.selected_lock_model.equals("GT2550")) {
                UI_BLE.pls_wait_content = context.getResources().getString(R.string.press_power_on_button_to_begin);
                UI_BLE.BLE_UI = 18;
                this.ui_ble.update();
                BLEService.verified_password_done = false;
                scanning_new_lock = false;
                start_scanning = true;
                BLEService.notification_stay_connected = 0;
                BLEService.notification_stay_connected_showed1 = false;
                BLEService.notification_stay_connected_showed2 = false;
                Fragment_BLE.get_init_battery_level_done = false;
                Fragment_BLE.get_battery_level_counter = 0;
                Fragment_BLE.reconnect_msg_show = false;
                return;
            } else if (BLEService.selected_lock_model.equals("GT2000") || BLEService.selected_lock_model.equals("GT3000")) {
                if (!mNFCAdapter.isEnabled()) {
                    new SweetAlertDialog(this, 3).setTitleText("").setContentText(getString(R.string.NFC_is_not_turned_on)).setConfirmText(getString(R.string.ok)).show();
                    return;
                }
                TaskManagement.current_function = 48;
                fragmentManager.beginTransaction().replace(R.id.container, Fragment_pairing.newInstance()).addToBackStack("29").commit();
                return;
            } else {
                return;
            }
        }
        clickFlag = 0;
        current_icon = 0;
        SweetAlertDialog sweetAlertDialog2 = new SweetAlertDialog(context, 0);
        sweetAlertDialog2.setTitleText(getString(R.string.pls_note));
        sweetAlertDialog2.setContentText(getString(R.string.sorry_lock_access_block));
        sweetAlertDialog2.show();
        Toast.makeText(getApplicationContext(), getString(R.string.sorry_lock_access_block), 1).show();
    }

    public void waitForGpsTime() {
        this.HandlerGpsTime.postDelayed(this.runnableGpsTime, 100L);
        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(context, 5);
        this.waitForGpsTimeDialog = sweetAlertDialog;
        sweetAlertDialog.setTitleText(getString(R.string.initializing));
        this.waitForGpsTimeDialog.setCanceledOnTouchOutside(false);
        this.waitForGpsTimeDialog.setCancelButton(R.string.cancel, new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.MainActivity.57
            @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
            public void onClick(SweetAlertDialog sweetAlertDialog2) {
                MainActivity.this.waitForGpsTimeDialog.cancel();
                MainActivity.this.HandlerGpsTime.removeCallbacks(MainActivity.this.runnableGpsTime);
            }
        });
        this.waitForGpsTimeDialog.showCancelButton(true);
        if (this.waitForGpsTimeDialog.isShowing()) {
            return;
        }
        this.waitForGpsTimeDialog.show();
    }

    public void Retrive_Tag_from_Firebase() {
        DatabaseVariable.db_tag.execSQL(DatabaseVariable.tagdb_delete_value_bylock(BLEService.selected_lock_name));
        tag_id_graph.clear();
        list_empty_pages.clear();
        list_empty_page_size.clear();
        tag_page_number.clear();
        FirebaseTagCount = 0L;
        FirebaseTotalTag = 0L;
        BLEService.highestTagPage = 0;
        UI_BLE.pls_wait_content = context.getResources().getString(R.string.retrieving_data_from_lock);
        UI_BLE.BLE_UI = 24;
        for (int i = 0; i < vertexCount; i++) {
            tag_id_graph.add(new ArrayList<>());
        }
        for (int i2 = 0; i2 < 15; i2++) {
            list_empty_pages.add(Integer.valueOf(i2));
            list_empty_page_size.add(0);
        }
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        this.database = firebaseDatabase;
        DatabaseReference reference = firebaseDatabase.getReference("tagDirectory");
        if (BLEService.selected_lock_model.equals("GT2500") || BLEService.selected_lock_model.equals("GT2550")) {
            reference = FirebaseDatabase.getInstance(FirebaseApp.getInstance("industrial")).getReference("tagDirectory");
        }
        reference.child(BLEService.parsedIp45SerialNumber).addListenerForSingleValueEvent(new ValueEventListener() { // from class: com.egeetouch.egeetouch_manager.MainActivity.59
            @Override // com.google.firebase.database.ValueEventListener
            public void onCancelled(DatabaseError databaseError) {
            }

            @Override // com.google.firebase.database.ValueEventListener
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot2 : dataSnapshot.getChildren()) {
                    MainActivity.FirebaseTotalTag = dataSnapshot.getChildrenCount();
                    DatabaseReference child = MainActivity.this.database.getReference("tagDirectory").child(BLEService.parsedIp45SerialNumber).child(dataSnapshot2.getKey());
                    if (BLEService.selected_lock_model.equals("GT2500") || BLEService.selected_lock_model.equals("GT2550")) {
                        child = FirebaseDatabase.getInstance(FirebaseApp.getInstance("industrial")).getReference("tagDirectory").child(BLEService.parsedIp45SerialNumber).child(dataSnapshot2.getKey());
                    }
                    child.addListenerForSingleValueEvent(new ValueEventListener() { // from class: com.egeetouch.egeetouch_manager.MainActivity.59.1
                        @Override // com.google.firebase.database.ValueEventListener
                        public void onCancelled(DatabaseError databaseError) {
                        }

                        @Override // com.google.firebase.database.ValueEventListener
                        public void onDataChange(DataSnapshot dataSnapshot3) {
                            for (DataSnapshot dataSnapshot4 : dataSnapshot3.getChildren()) {
                                if (dataSnapshot4.getKey().equals("PageNumber")) {
                                    MainActivity.tag_page_number.add(dataSnapshot4.getValue().toString());
                                    MainActivity.Tag_pageNumber = Integer.valueOf(dataSnapshot4.getValue().toString()).intValue();
                                } else if (dataSnapshot4.getKey().equals("tagId")) {
                                    dataSnapshot4.getValue().toString();
                                    MainActivity.tag_id = dataSnapshot4.getValue().toString();
                                    MainActivity.tag_id_graph.get(MainActivity.Tag_pageNumber).add(MainActivity.tag_id);
                                } else if (dataSnapshot4.getKey().equals("tagName")) {
                                    MainActivity.tag_name = dataSnapshot4.getValue().toString();
                                    DatabaseVariable.db_tag.execSQL(DatabaseVariable.tagdb_insert_value(MainActivity.tag_name, MainActivity.tag_id, BLEService.selected_lock_name));
                                }
                            }
                            MainActivity.FirebaseTagCount++;
                            if (MainActivity.FirebaseTotalTag == MainActivity.FirebaseTagCount) {
                                UI_BLE.BLE_UI = 9;
                                Fragment_add_tag.msg_update_done = true;
                                MainActivity.list_empty_pages.clear();
                                MainActivity.list_empty_page_size.clear();
                                for (int i3 = 0; i3 < 15; i3++) {
                                    if (MainActivity.tag_id_graph.get(i3).size() != 15) {
                                        MainActivity.list_empty_pages.add(Integer.valueOf(i3));
                                        MainActivity.list_empty_page_size.add(Integer.valueOf(MainActivity.tag_id_graph.get(i3).size()));
                                    }
                                    if (MainActivity.tag_id_graph.get(i3).size() != 0 && i3 > BLEService.highestTagPage) {
                                        BLEService.highestTagPage = i3;
                                    }
                                }
                            }
                        }
                    });
                }
                if (MainActivity.FirebaseTotalTag == 0) {
                    UI_BLE.BLE_UI = 9;
                }
            }
        });
    }

    public boolean checkGps() {
        boolean z;
        LocationManager locationManager = (LocationManager) getSystemService("location");
        if (locationManager.isProviderEnabled("gps")) {
            z = true;
        } else {
            Dialog dialog = new Dialog(this);
            this.gpsDialog = dialog;
            dialog.requestWindowFeature(1);
            this.gpsDialog.setContentView(R.layout.dialog_location);
            TextView textView = new TextView(context);
            SpannableString spannableString = new SpannableString(context.getText(R.string.link));
            Linkify.addLinks(spannableString, 1);
            textView.setText(spannableString);
            textView.setGravity(3);
            textView.setMovementMethod(LinkMovementMethod.getInstance());
            ((LinearLayout) this.gpsDialog.findViewById(R.id.ll_link)).addView(textView);
            this.gpsDialog.findViewById(R.id.gps_ok).setOnClickListener(new View.OnClickListener() { // from class: com.egeetouch.egeetouch_manager.MainActivity.60
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    MainActivity.this.startActivity(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"));
                }
            });
            this.gpsDialog.findViewById(R.id.gps_cancel).setOnClickListener(new View.OnClickListener() { // from class: com.egeetouch.egeetouch_manager.MainActivity.61
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    MainActivity.clickFlag = 0;
                    MainActivity.this.gpsDialog.dismiss();
                }
            });
            this.gpsDialog.getWindow().setLayout(-2, -2);
            this.gpsDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
            this.gpsDialog.setCancelable(true);
            this.gpsDialog.setCanceledOnTouchOutside(true);
            this.gpsDialog.show();
            z = false;
        }
        if (this.locationListener == null) {
            LocationListener locationListener = new LocationListener() { // from class: com.egeetouch.egeetouch_manager.MainActivity.62
                @Override // android.location.LocationListener
                public void onProviderDisabled(String str) {
                }

                @Override // android.location.LocationListener
                public void onProviderEnabled(String str) {
                }

                @Override // android.location.LocationListener
                public void onStatusChanged(String str, int i, Bundle bundle) {
                }

                @Override // android.location.LocationListener
                public void onLocationChanged(Location location) {
                    if (location.getProvider().equals("gps")) {
                        MainActivity.this.currentTimeStampGPS = location.getTime() / 1000;
                    }
                }
            };
            this.locationListener = locationListener;
            locationManager.requestLocationUpdates("gps", 1000L, 0.0f, locationListener);
        }
        return z;
    }

    public Boolean isBluetoothEnabled() {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (Build.VERSION.SDK_INT >= 31) {
            if (ContextCompat.checkSelfPermission(this, "android.permission.BLUETOOTH_SCAN") != 0 || ContextCompat.checkSelfPermission(this, "android.permission.BLUETOOTH_CONNECT") != 0) {
                ActivityCompat.requestPermissions(this, new String[]{"android.permission.BLUETOOTH_SCAN", "android.permission.BLUETOOTH_CONNECT"}, 1);
                return false;
            } else if (!defaultAdapter.isEnabled()) {
                startActivityForResult(new Intent("android.bluetooth.adapter.action.REQUEST_ENABLE"), 0);
                return false;
            }
        } else if (!defaultAdapter.isEnabled()) {
            startActivityForResult(new Intent("android.bluetooth.adapter.action.REQUEST_ENABLE"), 0);
            return false;
        }
        return true;
    }

    public boolean isLocationPermissionEnabled() {
        Boolean bool;
        Boolean.valueOf(true);
        if (ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") != 0) {
            bool = false;
            if (Build.VERSION.SDK_INT >= 23) {
                if (PermissionUtils.neverAskAgainSelected(this, "android.permission.ACCESS_FINE_LOCATION")) {
                    new AlertDialog.Builder(this).setTitle(getString(R.string.location_permission)).setMessage(getString(R.string.location_permission_enabling)).setPositiveButton("ok", new DialogInterface.OnClickListener() { // from class: com.egeetouch.egeetouch_manager.MainActivity.63
                        @Override // android.content.DialogInterface.OnClickListener
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent();
                            intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                            intent.setData(Uri.fromParts("package", MainActivity.this.getPackageName(), null));
                            MainActivity.this.startActivity(intent);
                        }
                    }).create().show();
                } else {
                    ActivityCompat.requestPermissions(this, new String[]{"android.permission.ACCESS_FINE_LOCATION"}, 1);
                }
            } else {
                ActivityCompat.requestPermissions(this, new String[]{"android.permission.ACCESS_FINE_LOCATION"}, 1);
            }
        } else {
            bool = true;
        }
        return bool.booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Boolean is_BLE_device() {
        if (BLEService.selected_lock_model.equals("GT1000") || BLEService.selected_lock_model.equals("GT1001") || BLEService.selected_lock_model.equals("GT2002") || BLEService.selected_lock_model.equals("GT2003") || BLEService.selected_lock_model.equals("GT2100") || BLEService.selected_lock_model.equals("GT2500") || BLEService.selected_lock_model.equals("GT2550") || BLEService.selected_lock_model.equals("GT3002") || BLEService.selected_lock_model.equals("GT5100") || BLEService.selected_lock_model.equals("GT5107") || BLEService.selected_lock_model.equals("GT5109") || BLEService.selected_lock_model.equals("GT5300")) {
            return true;
        }
        return false;
    }

    public void btn_checkMasterTag(View view) {
        BLEService.Ble_Mode = BLEService.Request_Master_Tag;
        Toasty.normal(context, (int) R.string.check_master_tag, 0).show();
    }

    public void switch_powerSavingMode(View view) {
        BLEService.Ble_Mode = BLEService.AutoLocking_Settings;
    }

    public void switch_shackleByPass(View view) {
        BLEService.Ble_Mode = BLEService.ShackleBypass_settings;
    }

    public static void openOnlineAuditTrail() {
        fragmentManager.beginTransaction().replace(R.id.container, Fragment_online_logs.newInstance()).addToBackStack("8").commit();
    }

    public void btn_managePasscode(View view) {
        fragmentManager.beginTransaction().replace(R.id.container, Fragment_Manage_Passcode.newInstance()).addToBackStack("8").commit();
    }

    public void btn_changePasscode(View view) {
        if (LotoInfo.getInstance().getTotalAvailablePasscode() < 10) {
            fragmentManager.beginTransaction().replace(R.id.container, Fragment_Passcode.newInstance()).addToBackStack("8").commit();
            return;
        }
        new SweetAlertDialog(context, 3).setTitleText("Passcode Limit").setContentText("Already reached maximum limit ").setConfirmText(context.getResources().getString(R.string.ok)).show();
    }

    public void ShowCommercialDialog(final Context context2, final String str) {
        final Dialog dialog = new Dialog(context2);
        dialog.setContentView(R.layout.egeetouch_commercial_adv_pop_up);
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
        Button button = (Button) dialog.findViewById(R.id.button_moreDetails);
        TextView textView = (TextView) dialog.findViewById(R.id.pop_up_titleTV);
        TextView textView2 = (TextView) dialog.findViewById(R.id.pop_up_sub_titleTV);
        ImageView imageView = (ImageView) dialog.findViewById(R.id.imgage_closePopUp);
        TextView textView3 = (TextView) dialog.findViewById(R.id.default2);
        TextView textView4 = (TextView) dialog.findViewById(R.id.defaultTV);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -947840688:
                if (str.equals("lockLimit")) {
                    c = 0;
                    break;
                }
                break;
            case -781693887:
                if (str.equals("tagLimit")) {
                    c = 1;
                    break;
                }
                break;
            case -348171998:
                if (str.equals("recipientLimit")) {
                    c = 2;
                    break;
                }
                break;
            case 110119261:
                if (str.equals("tagAd")) {
                    c = 3;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                textView.setText(R.string.egee_pop_up_lock_title);
                textView2.setText(R.string.egee_pop_up_lock_sub_title);
                break;
            case 1:
                textView.setText(R.string.egee_pop_up_tag_title);
                textView2.setText(R.string.egee_pop_up_tag_sub_title);
                break;
            case 2:
                textView.setText(R.string.egee_pop_up_recipient_title);
                textView2.setText(R.string.egee_pop_up_recipient_sub_title);
                break;
            case 3:
                textView.setVisibility(8);
                textView2.setText(R.string.egee_pop_up_tag_sub_title);
                break;
        }
        if (Locale.getDefault().getLanguage().equals("ja")) {
            textView4.setVisibility(8);
            textView3.setVisibility(8);
        } else {
            textView4.setVisibility(0);
            textView3.setVisibility(0);
        }
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.egeetouch.egeetouch_manager.MainActivity.64
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                dialog.dismiss();
                if (str == "tagAd") {
                    MainActivity.this.btn_manage_tag(null);
                }
            }
        });
        button.setOnClickListener(new View.OnClickListener() { // from class: com.egeetouch.egeetouch_manager.MainActivity.65
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Intent intent = new Intent("android.intent.action.VIEW");
                if (Locale.getDefault().getLanguage().equals("ja")) {
                    intent.setData(Uri.parse("https://www.egeetouch.com/jp/products/IAM-software"));
                } else {
                    intent.setData(Uri.parse("https://iam.egeetouch.com/package"));
                }
                context2.startActivity(intent);
            }
        });
        dialog.setCanceledOnTouchOutside(true);
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.egeetouch.egeetouch_manager.MainActivity.66
            @Override // android.content.DialogInterface.OnCancelListener
            public void onCancel(DialogInterface dialogInterface) {
                if (str == "tagAd") {
                    MainActivity.this.btn_manage_tag(null);
                }
            }
        });
        dialog.show();
    }

    public boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService("connectivity");
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    public void Retrive_Tag_from_FirebaseIP66() {
        DatabaseVariable.db_tag.execSQL(DatabaseVariable.tagdb_delete_value_bylock(BLEService.selected_lock_name));
        FirebaseTagCount = 0L;
        FirebaseTotalTag = 0L;
        UI_BLE.pls_wait_content = context.getResources().getString(R.string.retrieving_data_from_lock);
        UI_BLE.BLE_UI = 24;
        FirebaseDatabase.getInstance(FirebaseApp.getInstance("industrial")).getReference("tagDirectory").child(BLEService.parsedIp45SerialNumber).addListenerForSingleValueEvent(new ValueEventListener() { // from class: com.egeetouch.egeetouch_manager.MainActivity.67
            @Override // com.google.firebase.database.ValueEventListener
            public void onCancelled(DatabaseError databaseError) {
            }

            @Override // com.google.firebase.database.ValueEventListener
            public void onDataChange(DataSnapshot dataSnapshot) {
                MainActivity.FirebaseTotalTag = dataSnapshot.getChildrenCount();
                if (dataSnapshot.exists()) {
                    for (DataSnapshot dataSnapshot2 : dataSnapshot.getChildren()) {
                        System.out.println("Hello checking the TAG DB is FOUND");
                        HashMap hashMap = (HashMap) dataSnapshot2.getValue();
                        String str = " N.A ";
                        String obj = hashMap.containsKey("tagName") ? hashMap.get("tagName").toString() : " N.A ";
                        if (hashMap.containsKey("tagId")) {
                            str = hashMap.get("tagId").toString();
                        }
                        DatabaseVariable.db_tag.execSQL(DatabaseVariable.tagdb_insert_value(obj, str, BLEService.selected_lock_name));
                    }
                    UI_BLE.BLE_UI = 9;
                    Fragment_add_tag.msg_update_done = true;
                    return;
                }
                System.out.println("Hello checking the TAG DB is EMPTY");
                UI_BLE.BLE_UI = 9;
            }
        });
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

    public boolean usesOnlineTag(String str, String str2) {
        return str.equals("GT2500") || str.equals("GT2550") || (str.equals("GT2100") && ((double) Float.parseFloat(str2)) >= 1.8d) || str.equals("GT5100") || str.equals("GT5107") || str.equals("GT5109") || str.equals("GT5300");
    }
}
