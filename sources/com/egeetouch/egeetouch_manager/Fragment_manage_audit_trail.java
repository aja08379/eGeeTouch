package com.egeetouch.egeetouch_manager;

import android.app.Fragment;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import cn.pedant.SweetAlert.SweetAlertDialog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class Fragment_manage_audit_trail extends Fragment {
    private static String address = "";
    private static boolean address_obtained = false;
    private static boolean address_wait_for_response = false;
    private static int convert_address_count = 0;
    private static boolean disconnected_dialog_show = false;
    private static boolean disconnected_trigger = false;
    public static boolean internet_available = false;
    private static boolean reconnect_msg_show = false;
    public static boolean refresh_listview_audit = false;
    private static boolean start_convert_address = false;
    private static boolean start_update_list = false;
    ArrayAdapter_audittrial adapter;
    ListView all_history;
    private FirebaseDatabase database;
    ArrayList<Double> doubleList_latitude;
    ArrayList<Double> doubleList_longitude;
    private Menu menu;
    SweetAlertDialog pd_retrieve_address;
    SweetAlertDialog reconnect_msg;
    View rootView;
    ArrayList<String> stringList_address;
    ArrayList<String> stringList_email;
    ArrayList<String> stringList_lock_status;
    ArrayList<String> stringList_when;
    ArrayList<String> stringList_who;
    String[] values_address;
    String[] values_email;
    String[] values_lock_status;
    String[] values_when;
    Double[] values_where_Latitude;
    Double[] values_where_Longitude;
    String[] values_who;
    public static ArrayList<String> auditEmail = new ArrayList<>();
    public static ArrayList<Long> auditTime = new ArrayList<>();
    public static ArrayList<String> auditlockStatus = new ArrayList<>();
    public static ArrayList<String> auditAddress = new ArrayList<>();
    public static ArrayList<Integer> auditDeci_Value = new ArrayList<>();
    public static ArrayList<Long> auditLockBackTime = new ArrayList<>();
    public static ArrayList<Double> auditLockLongitude = new ArrayList<>();
    public static ArrayList<Double> auditLockLatitude = new ArrayList<>();
    public static ArrayList<String> auditUser = new ArrayList<>();
    public static ArrayList<String> auditDate = new ArrayList<>();
    public static long number_of_Access = 0;
    private static int currentAccessCountNumber = 0;
    private boolean no_data = true;
    private Handler manage_audit_handler = new Handler();
    String last_update = "";
    int max_logged_number = 20;
    int max_address_convert = 20;
    boolean firebaseDone = false;
    final Runnable r = new Runnable() { // from class: com.egeetouch.egeetouch_manager.Fragment_manage_audit_trail.2
        @Override // java.lang.Runnable
        public void run() {
            if (BLEService.mConnectionState == 0) {
                Fragment_manage_audit_trail.this.icon_display(false);
                if (!Fragment_manage_audit_trail.reconnect_msg_show && !Fragment_manage_audit_trail.disconnected_trigger) {
                    boolean unused = Fragment_manage_audit_trail.reconnect_msg_show = true;
                    Fragment_manage_audit_trail.this.reconnect_msg = new SweetAlertDialog(MainActivity.context, 5);
                    Fragment_manage_audit_trail.this.reconnect_msg.setTitleText(MainActivity.context.getResources().getString(R.string.lost_connection));
                    Fragment_manage_audit_trail.this.reconnect_msg.setContentText(MainActivity.context.getResources().getString(R.string.move_nearer));
                    Fragment_manage_audit_trail.this.reconnect_msg.setCancelText(MainActivity.context.getResources().getString(R.string.cancel));
                    Fragment_manage_audit_trail.this.reconnect_msg.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_manage_audit_trail.2.1
                        @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            sweetAlertDialog.dismissWithAnimation();
                            BLEService.cancel_scaning_triggered = true;
                            BLEService.Ble_Mode = BLEService.disconnect;
                        }
                    });
                    Fragment_manage_audit_trail.this.reconnect_msg.setCancelable(false);
                    Fragment_manage_audit_trail.this.reconnect_msg.show();
                }
            } else {
                Fragment_manage_audit_trail.this.icon_display(true);
                if (Fragment_manage_audit_trail.reconnect_msg_show && Fragment_manage_audit_trail.this.reconnect_msg != null && Fragment_manage_audit_trail.this.reconnect_msg.isShowing()) {
                    Fragment_manage_audit_trail.this.reconnect_msg.dismiss();
                    boolean unused2 = Fragment_manage_audit_trail.reconnect_msg_show = false;
                }
            }
            if (Fragment_manage_audit_trail.refresh_listview_audit) {
                Fragment_manage_audit_trail.this.pd_retrieve_address = new SweetAlertDialog(MainActivity.context, 5);
                Fragment_manage_audit_trail.this.pd_retrieve_address.setTitleText(MainActivity.context.getResources().getString(R.string.audit_trail)).setContentText(MainActivity.context.getResources().getString(R.string.please_wait_audit_address)).setCancelText(MainActivity.context.getResources().getString(R.string.cancel)).setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_manage_audit_trail.2.2
                    @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismissWithAnimation();
                        BLEService.Ble_Mode = 0;
                        MainActivity.fragmentManager.popBackStack();
                        boolean unused3 = Fragment_manage_audit_trail.start_update_list = false;
                    }
                });
                Fragment_manage_audit_trail.this.pd_retrieve_address.setCanceledOnTouchOutside(false);
                Fragment_manage_audit_trail.this.pd_retrieve_address.show();
                Fragment_manage_audit_trail.this.retrieve_data_from_database();
                if (!BLEService.selected_lock_model.equals("GT1000") && !BLEService.selected_lock_model.equals("GT3100")) {
                    Fragment_manage_audit_trail.this.checkingForLimitedAuditTrail();
                } else {
                    Fragment_manage_audit_trail.this.firebaseDone = true;
                }
                Fragment_manage_audit_trail.refresh_listview_audit = false;
                boolean unused3 = Fragment_manage_audit_trail.start_convert_address = true;
                boolean unused4 = Fragment_manage_audit_trail.start_update_list = false;
            }
            if (Fragment_manage_audit_trail.start_convert_address) {
                Log.i("TAG", "start_convert_address" + String.valueOf(Fragment_manage_audit_trail.convert_address_count) + " " + String.valueOf(Fragment_manage_audit_trail.this.max_address_convert) + " " + String.valueOf(BLEService.Total_Tag_Audit));
                if (Fragment_manage_audit_trail.this.values_where_Latitude[Fragment_manage_audit_trail.convert_address_count] == null || Fragment_manage_audit_trail.this.values_where_Latitude[Fragment_manage_audit_trail.convert_address_count].doubleValue() <= -90.0d || Fragment_manage_audit_trail.this.values_where_Longitude[Fragment_manage_audit_trail.convert_address_count].doubleValue() <= -180.0d || Fragment_manage_audit_trail.this.values_where_Latitude[Fragment_manage_audit_trail.convert_address_count].doubleValue() >= 90.0d || Fragment_manage_audit_trail.this.values_where_Longitude[Fragment_manage_audit_trail.convert_address_count].doubleValue() >= 180.0d || Fragment_manage_audit_trail.this.values_where_Latitude[Fragment_manage_audit_trail.convert_address_count].doubleValue() == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE || Fragment_manage_audit_trail.this.values_where_Longitude[Fragment_manage_audit_trail.convert_address_count].doubleValue() == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                    String unused5 = Fragment_manage_audit_trail.address = Fragment_manage_audit_trail.this.getString(R.string.N_A);
                    boolean unused6 = Fragment_manage_audit_trail.address_obtained = true;
                } else {
                    Fragment_manage_audit_trail fragment_manage_audit_trail = Fragment_manage_audit_trail.this;
                    fragment_manage_audit_trail.convert_coordinates_2_address(fragment_manage_audit_trail.values_where_Longitude[Fragment_manage_audit_trail.convert_address_count].doubleValue(), Fragment_manage_audit_trail.this.values_where_Latitude[Fragment_manage_audit_trail.convert_address_count].doubleValue());
                }
                boolean unused7 = Fragment_manage_audit_trail.start_convert_address = false;
            }
            if (Fragment_manage_audit_trail.address_obtained) {
                Log.i("TAG", "address_obtained");
                if (Fragment_manage_audit_trail.convert_address_count >= Fragment_manage_audit_trail.this.max_address_convert - 1) {
                    Fragment_manage_audit_trail.this.values_address[Fragment_manage_audit_trail.convert_address_count] = Fragment_manage_audit_trail.address;
                    boolean unused8 = Fragment_manage_audit_trail.start_update_list = true;
                    boolean unused9 = Fragment_manage_audit_trail.start_convert_address = false;
                } else {
                    Fragment_manage_audit_trail.this.values_address[Fragment_manage_audit_trail.convert_address_count] = Fragment_manage_audit_trail.address;
                    Fragment_manage_audit_trail.access$708();
                    boolean unused10 = Fragment_manage_audit_trail.start_convert_address = true;
                }
                boolean unused11 = Fragment_manage_audit_trail.address_obtained = false;
            }
            if (Fragment_manage_audit_trail.start_update_list) {
                try {
                    if (Fragment_manage_audit_trail.this.firebaseDone) {
                        Fragment_manage_audit_trail.this.pd_retrieve_address.dismissWithAnimation();
                        Fragment_manage_audit_trail.this.update_UI_listview();
                        boolean unused12 = Fragment_manage_audit_trail.start_update_list = false;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Fragment_manage_audit_trail.this.pd_retrieve_address.dismissWithAnimation();
                    boolean unused13 = Fragment_manage_audit_trail.start_update_list = false;
                }
            }
            Fragment_manage_audit_trail.this.manage_audit_handler.postDelayed(this, 30L);
        }
    };

    static /* synthetic */ int access$1408() {
        int i = currentAccessCountNumber;
        currentAccessCountNumber = i + 1;
        return i;
    }

    static /* synthetic */ int access$708() {
        int i = convert_address_count;
        convert_address_count = i + 1;
        return i;
    }

    public static Fragment_manage_audit_trail newInstance() {
        return new Fragment_manage_audit_trail();
    }

    @Override // android.app.Fragment
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.audit_trail);
        try {
            auditEmail.clear();
            auditTime.clear();
            auditlockStatus.clear();
            auditAddress.clear();
            auditDeci_Value.clear();
            auditLockBackTime.clear();
            auditLockLongitude.clear();
            auditLockLatitude.clear();
            auditUser.clear();
            auditDate.clear();
        } catch (Exception unused) {
        }
        disconnected_dialog_show = false;
        this.manage_audit_handler.postDelayed(this.r, 10L);
    }

    @Override // android.app.Fragment
    public void onPause() {
        super.onPause();
        this.manage_audit_handler.removeCallbacks(this.r);
    }

    @Override // android.app.Fragment
    public void onStop() {
        super.onStop();
    }

    @Override // android.app.Fragment
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.global, menu);
        MainActivity.shutdown_status = 0;
    }

    @Override // android.app.Fragment
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.action_button_disconnect) {
            new SweetAlertDialog(getActivity()).setTitleText(getString(R.string.power_off)).setContentText(getString(R.string.are_you_sure_power_off_lock)).setConfirmText(getString(R.string.yes)).setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_manage_audit_trail.1
                @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                public void onClick(SweetAlertDialog sweetAlertDialog) {
                    sweetAlertDialog.dismissWithAnimation();
                    boolean unused = Fragment_manage_audit_trail.disconnected_trigger = true;
                    BLEService.cancel_scaning_triggered = true;
                    BLEService.Ble_Mode = BLEService.Send_timeStamp;
                    BLEService.disconnect_triggered = true;
                    MainActivity.isUserClickedShutdown = true;
                    MainActivity.shutdown_dialog = new SweetAlertDialog(MainActivity.context, 5);
                    MainActivity.shutdown_dialog.setTitleText(Fragment_manage_audit_trail.this.getString(R.string.power_off));
                    MainActivity.shutdown_dialog.setCancelable(false);
                    MainActivity.shutdown_dialog.setCanceledOnTouchOutside(false);
                    MainActivity.shutdown_dialog.show();
                    MainActivity.stopBackStack = false;
                    ((AppCompatActivity) Fragment_manage_audit_trail.this.getActivity()).getSupportActionBar().setTitle(R.string.dashboard);
                    MediaPlayer create = MediaPlayer.create(Fragment_manage_audit_trail.this.getActivity(), (int) R.raw.disconnectinapp);
                    create.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_manage_audit_trail.1.1
                        @Override // android.media.MediaPlayer.OnCompletionListener
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            mediaPlayer.stop();
                            mediaPlayer.reset();
                            mediaPlayer.release();
                        }
                    });
                    create.start();
                }
            }).setCancelText(getString(R.string.no)).show();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.rootView = layoutInflater.inflate(R.layout.fragment_audit_trail, viewGroup, false);
        setHasOptionsMenu(true);
        disconnected_trigger = false;
        Handler handler = new Handler();
        this.manage_audit_handler = handler;
        handler.post(this.r);
        this.max_logged_number = 20;
        if (BLEService.selected_lock_model.equals("GT2100") && (BLEService.selected_lock_version.equals("1.80") || Float.valueOf(BLEService.selected_lock_version).floatValue() > 1.8d)) {
            this.max_logged_number = BLEService.selected_lock_version.equals("1.80") ? 100 : BLEService.check_version;
        }
        MainActivity.lock_settings_status = true;
        convert_address_count = 0;
        MainActivity.current_icon = 4;
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.audit_trail);
        int i = this.max_logged_number;
        this.values_who = new String[i];
        this.values_when = new String[i];
        this.values_where_Latitude = new Double[i];
        this.values_where_Longitude = new Double[i];
        this.values_address = new String[i];
        this.values_lock_status = new String[i];
        this.values_email = new String[i];
        if (BLEService.selected_lock_model.equals("GT2002") || BLEService.selected_lock_model.equals("GT2003") || BLEService.selected_lock_model.equals("GT2100") || BLEService.selected_lock_model.equals("GT5100") || BLEService.selected_lock_model.equals("GT5107") || BLEService.selected_lock_model.equals("GT5109") || BLEService.selected_lock_model.equals("GT5300")) {
            ((ImageView) this.rootView.findViewById(R.id.imageView_proximity)).setVisibility(8);
        } else {
            if (BLEService.selected_lock_model.equals("GT1000") || BLEService.selected_lock_model.equals("GT3100")) {
                ((ImageView) this.rootView.findViewById(R.id.imageView_proximity)).setVisibility(0);
            }
            TextView textView = (TextView) this.rootView.findViewById(R.id.tv_paid_version);
            textView.setMovementMethod(LinkMovementMethod.getInstance());
            if (!BLEService.selected_lock_model.equals("GT1000") || BLEService.selected_lock_model.equals("GT2002") || BLEService.selected_lock_model.equals("GT2003") || BLEService.selected_lock_model.equals("GT3100") || BLEService.selected_lock_model.equals("GT3002")) {
                textView.setVisibility(8);
            }
            return this.rootView;
        }
        TextView textView2 = (TextView) this.rootView.findViewById(R.id.tv_paid_version);
        textView2.setMovementMethod(LinkMovementMethod.getInstance());
        if (!BLEService.selected_lock_model.equals("GT1000")) {
        }
        textView2.setVisibility(8);
        return this.rootView;
    }

    private boolean haveNetworkConnection() {
        NetworkInfo[] allNetworkInfo;
        boolean z = false;
        boolean z2 = false;
        for (NetworkInfo networkInfo : ((ConnectivityManager) getActivity().getSystemService("connectivity")).getAllNetworkInfo()) {
            if (networkInfo.getTypeName().equalsIgnoreCase("WIFI") && networkInfo.isConnected()) {
                z = true;
            }
            if (networkInfo.getTypeName().equalsIgnoreCase("MOBILE") && networkInfo.isConnected()) {
                z2 = true;
            }
        }
        return z || z2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void convert_coordinates_2_address(double d, double d2) {
        if (Helper_PhoneDetails.phonelangauge().equals("ja")) {
            new google_geocoder_api_Task("https://maps.googleapis.com/maps/api/geocode/json?latlng=" + d2 + "," + d + "&key=AIzaSyCAqcyJtbuXJ3szf8kLsx06zzKMD2vPy1A&language=zh-TW").execute(new Void[0]);
        } else {
            new google_geocoder_api_Task("https://maps.googleapis.com/maps/api/geocode/json?latlng=" + d2 + "," + d + "&key=AIzaSyCAqcyJtbuXJ3szf8kLsx06zzKMD2vPy1A").execute(new Void[0]);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void retrieve_data_from_database() {
        long j;
        int i;
        this.no_data = false;
        if (!BLEService.selected_lock_model.equals("GT2100") || (!BLEService.selected_lock_version.equals("1.80") && Float.valueOf(BLEService.selected_lock_version).floatValue() <= 1.8d)) {
            j = 20;
        } else {
            j = BLEService.Total_Tag_Audit;
            this.max_address_convert = (int) j;
        }
        Cursor rawQuery = DatabaseVariable.db_audittrail.rawQuery(DatabaseVariable.db_audittrail_lock2(BLEService.selected_lock_name), null);
        int i2 = -1;
        int i3 = 0;
        while (rawQuery.moveToNext()) {
            this.no_data = false;
            boolean z = true;
            i2++;
            if (i2 <= j && (i = rawQuery.getInt(0)) > 0 && i <= j) {
                Log.i("TAG", "who: " + String.valueOf(rawQuery.getString(1)));
                Log.i("TAG", "when: " + String.valueOf(rawQuery.getString(2)));
                if (rawQuery.getString(1) != null) {
                    this.values_who[i3] = rawQuery.getString(1);
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
                    Date date = new Date();
                    try {
                        date = simpleDateFormat.parse(rawQuery.getString(2));
                    } catch (ParseException e) {
                        e.printStackTrace();
                        z = false;
                    }
                    String string = rawQuery.getString(2);
                    SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy/MM/dd   HH:mm:ss");
                    if (z) {
                        try {
                            string = simpleDateFormat2.format(Long.valueOf(date.getTime()));
                        } catch (Exception unused) {
                        }
                    }
                    this.values_when[i3] = string;
                    this.values_where_Latitude[i3] = Double.valueOf(rawQuery.getDouble(3));
                    this.values_where_Longitude[i3] = Double.valueOf(rawQuery.getDouble(4));
                    DecimalFormat decimalFormat = new DecimalFormat("#.######");
                    Double[] dArr = this.values_where_Latitude;
                    dArr[i3] = Double.valueOf(decimalFormat.format(dArr[i3]));
                    Double[] dArr2 = this.values_where_Longitude;
                    dArr2[i3] = Double.valueOf(decimalFormat.format(dArr2[i3]));
                    this.values_lock_status[i3] = "UNLOCKED";
                    this.values_email[i3] = getString(R.string.N_A);
                    this.values_address[i3] = getString(R.string.N_A);
                    i3++;
                }
                this.last_update = rawQuery.getString(5);
            }
        }
        rawQuery.close();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void update_UI_listview() {
        boolean haveNetworkConnection = haveNetworkConnection();
        internet_available = haveNetworkConnection;
        if (!haveNetworkConnection) {
            new SweetAlertDialog(MainActivity.context, 3).setTitleText(MainActivity.context.getResources().getString(R.string.audit_trail)).setContentText(MainActivity.context.getResources().getString(R.string.internet_is_not_available)).setConfirmText(MainActivity.context.getResources().getString(R.string.ok)).show();
        }
        this.stringList_who = new ArrayList<>(Arrays.asList(this.values_who));
        this.stringList_when = new ArrayList<>(Arrays.asList(this.values_when));
        this.stringList_address = new ArrayList<>(Arrays.asList(this.values_address));
        this.doubleList_longitude = new ArrayList<>(Arrays.asList(this.values_where_Longitude));
        this.doubleList_latitude = new ArrayList<>(Arrays.asList(this.values_where_Latitude));
        this.stringList_lock_status = new ArrayList<>(Arrays.asList(this.values_lock_status));
        this.stringList_email = new ArrayList<>(Arrays.asList(this.values_email));
        this.stringList_who.removeAll(Arrays.asList("", null));
        this.stringList_when.removeAll(Arrays.asList("", null));
        this.stringList_address.removeAll(Arrays.asList("", null));
        this.doubleList_longitude.removeAll(Arrays.asList("", null));
        this.doubleList_latitude.removeAll(Arrays.asList("", null));
        this.stringList_lock_status.removeAll(Arrays.asList("", null));
        this.stringList_email.removeAll(Arrays.asList("", null));
        this.stringList_who.addAll(auditUser);
        this.stringList_when.addAll(auditDate);
        this.stringList_address.addAll(auditAddress);
        this.doubleList_longitude.addAll(auditLockLongitude);
        this.doubleList_latitude.addAll(auditLockLatitude);
        this.stringList_lock_status.addAll(auditlockStatus);
        this.stringList_email.addAll(auditEmail);
        try {
            sortAudit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        int size = this.stringList_when.size();
        if (size > 20) {
            while (true) {
                size--;
                if (size <= 19) {
                    break;
                }
                this.stringList_who.remove(size);
                this.stringList_when.remove(size);
                this.stringList_address.remove(size);
                this.doubleList_longitude.remove(size);
                this.doubleList_latitude.remove(size);
                this.stringList_lock_status.remove(size);
                this.stringList_email.remove(size);
            }
        }
        System.out.println("Hello checking the passcode Audit Trail : " + this.values_who);
        this.adapter = new ArrayAdapter_audittrial(getActivity(), this.stringList_who, this.stringList_when, this.stringList_address, this.doubleList_longitude, this.doubleList_latitude, this.stringList_lock_status, this.stringList_email);
        ListView listView = (ListView) this.rootView.findViewById(R.id.listView_access_history);
        this.all_history = listView;
        listView.setScrollingCacheEnabled(false);
        this.all_history.setAdapter((ListAdapter) this.adapter);
        ((TextView) this.rootView.findViewById(R.id.textView_Audit_trail_lock_name)).setText(BLEService.selected_lock_name);
        ((TextView) this.rootView.findViewById(R.id.textView_last_updatedTime)).setText(this.rootView.getResources().getString(R.string.last_update) + " " + this.last_update);
    }

    /* loaded from: classes.dex */
    public class google_geocoder_api_Task extends AsyncTask<Void, Void, String> {
        private String mUrl;

        private String getJSON(String str) {
            return str;
        }

        public google_geocoder_api_Task(String str) {
            this.mUrl = str;
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            super.onPreExecute();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public String doInBackground(Void... voidArr) {
            try {
                HttpResponse execute = new DefaultHttpClient().execute(new HttpGet(this.mUrl));
                if (execute.getStatusLine().getStatusCode() != 200) {
                    boolean unused = Fragment_manage_audit_trail.address_obtained = true;
                    String unused2 = Fragment_manage_audit_trail.address = "N.A.";
                    return "N.A.";
                }
                JSONObject jSONObject = new JSONObject(EntityUtils.toString(execute.getEntity())).getJSONArray("results").getJSONObject(0);
                Log.i("", jSONObject.getString("formatted_address"));
                boolean unused3 = Fragment_manage_audit_trail.address_obtained = true;
                String unused4 = Fragment_manage_audit_trail.address = jSONObject.getString("formatted_address");
                return jSONObject.getString("formatted_address");
            } catch (IOException e) {
                e.printStackTrace();
                boolean unused5 = Fragment_manage_audit_trail.address_obtained = true;
                String unused6 = Fragment_manage_audit_trail.address = "N.A.";
                return "N.A.";
            } catch (JSONException e2) {
                e2.printStackTrace();
                boolean unused7 = Fragment_manage_audit_trail.address_obtained = true;
                String unused8 = Fragment_manage_audit_trail.address = "N.A.";
                return "N.A.";
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(String str) {
            super.onPostExecute((google_geocoder_api_Task) str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void icon_display(Boolean bool) {
        ImageView imageView = (ImageView) this.rootView.findViewById(R.id.imageView_locking);
        ImageView imageView2 = (ImageView) this.rootView.findViewById(R.id.imageView_proximity);
        ImageView imageView3 = (ImageView) this.rootView.findViewById(R.id.imageView_audit);
        ImageView imageView4 = (ImageView) this.rootView.findViewById(R.id.imageView_watch);
        ImageView imageView5 = (ImageView) this.rootView.findViewById(R.id.imageView_setting);
        if (!bool.booleanValue()) {
            if (imageView != null) {
                imageView.setAlpha(75);
                imageView.setClickable(false);
            }
            if (imageView2 != null) {
                imageView2.setAlpha(75);
                imageView2.setClickable(false);
            }
            if (imageView4 != null) {
                imageView4.setAlpha(75);
                imageView4.setClickable(false);
            }
            if (imageView3 != null) {
                imageView3.setAlpha(75);
                imageView3.setClickable(false);
            }
            if (imageView5 != null) {
                imageView5.setAlpha(75);
                imageView5.setClickable(false);
                return;
            }
            return;
        }
        if (imageView != null) {
            imageView.setAlpha(255);
            imageView.setClickable(true);
        }
        if (imageView2 != null) {
            if (BLEService.selected_lock_role == 1) {
                imageView2.setAlpha(75);
            } else {
                imageView2.setAlpha(255);
            }
            imageView2.setClickable(true);
        }
        if (imageView4 != null) {
            if (BLEService.selected_lock_role == 1) {
                imageView4.setAlpha(75);
            } else {
                imageView4.setAlpha(255);
            }
            imageView4.setClickable(true);
        }
        if (imageView3 != null) {
            if (BLEService.selected_lock_role == 1) {
                imageView3.setAlpha(75);
            } else {
                imageView3.setAlpha(255);
            }
            imageView3.setClickable(true);
        }
        if (imageView5 != null) {
            imageView5.setAlpha(255);
            imageView5.setClickable(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkingForLimitedAuditTrail() {
        currentAccessCountNumber = 0;
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        this.database = firebaseDatabase;
        firebaseDatabase.getReference("AuditTrail").child(BLEService.parsedIp45SerialNumber).orderByChild("time").limitToLast(20).addListenerForSingleValueEvent(new ValueEventListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_manage_audit_trail.3
            @Override // com.google.firebase.database.ValueEventListener
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Fragment_manage_audit_trail.number_of_Access = dataSnapshot.getChildrenCount();
                    for (DataSnapshot dataSnapshot2 : dataSnapshot.getChildren()) {
                        Fragment_manage_audit_trail.this.database.getReference("AuditTrail").child(BLEService.parsedIp45SerialNumber).child(dataSnapshot2.getKey()).addValueEventListener(new ValueEventListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_manage_audit_trail.3.1
                            @Override // com.google.firebase.database.ValueEventListener
                            public void onDataChange(DataSnapshot dataSnapshot3) {
                                if (dataSnapshot3.exists()) {
                                    System.out.println("Hello checking the Audit Snap 1 : " + dataSnapshot3.getValue());
                                    System.out.println("Hello checking the Audit Snap : " + dataSnapshot3.getValue());
                                    HashMap hashMap = (HashMap) dataSnapshot3.getValue();
                                    String obj = hashMap.containsKey("userEmail") ? hashMap.get("userEmail").toString() : " N.A ";
                                    String obj2 = hashMap.containsKey("lockedStatus") ? hashMap.get("lockedStatus").toString() : " N.A ";
                                    String obj3 = hashMap.containsKey("Placemark") ? hashMap.get("Placemark").toString() : "Address not found";
                                    long parseLong = hashMap.containsKey("time") ? Long.parseLong(hashMap.get("time").toString()) : 0L;
                                    int parseInt = hashMap.containsValue("auditDeciValue") ? Integer.parseInt(hashMap.get("auditDeciValue").toString()) : 0;
                                    long parseInt2 = hashMap.containsValue("lockBackTime") ? Integer.parseInt(hashMap.get("lockBackTime").toString()) : 0L;
                                    boolean containsKey = hashMap.containsKey("Longitude");
                                    double d = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
                                    double parseDouble = containsKey ? Double.parseDouble(hashMap.get("Longitude").toString()) : 0.0d;
                                    if (hashMap.containsKey("Latitude")) {
                                        d = Double.parseDouble(hashMap.get("Latitude").toString());
                                    }
                                    String format = new SimpleDateFormat("yyyy/MM/dd   HH:mm:ss").format(Long.valueOf(parseLong));
                                    Fragment_manage_audit_trail.auditEmail.add(Fragment_manage_audit_trail.currentAccessCountNumber, obj);
                                    Fragment_manage_audit_trail.auditTime.add(Fragment_manage_audit_trail.currentAccessCountNumber, Long.valueOf(parseLong));
                                    Fragment_manage_audit_trail.auditlockStatus.add(Fragment_manage_audit_trail.currentAccessCountNumber, obj2);
                                    Fragment_manage_audit_trail.auditAddress.add(Fragment_manage_audit_trail.currentAccessCountNumber, obj3);
                                    Fragment_manage_audit_trail.auditDeci_Value.add(Fragment_manage_audit_trail.currentAccessCountNumber, Integer.valueOf(parseInt));
                                    Fragment_manage_audit_trail.auditLockBackTime.add(Fragment_manage_audit_trail.currentAccessCountNumber, Long.valueOf(parseInt2));
                                    Fragment_manage_audit_trail.auditLockLongitude.add(Fragment_manage_audit_trail.currentAccessCountNumber, Double.valueOf(parseDouble));
                                    Fragment_manage_audit_trail.auditLockLatitude.add(Fragment_manage_audit_trail.currentAccessCountNumber, Double.valueOf(d));
                                    Fragment_manage_audit_trail.auditUser.add(Fragment_manage_audit_trail.currentAccessCountNumber, "user");
                                    Fragment_manage_audit_trail.auditDate.add(Fragment_manage_audit_trail.currentAccessCountNumber, format);
                                    Fragment_manage_audit_trail.access$1408();
                                    if (Fragment_manage_audit_trail.number_of_Access == Fragment_manage_audit_trail.currentAccessCountNumber) {
                                        Fragment_manage_audit_trail.this.firebaseDone = true;
                                        return;
                                    }
                                    return;
                                }
                                Fragment_manage_audit_trail.this.firebaseDone = true;
                            }

                            @Override // com.google.firebase.database.ValueEventListener
                            public void onCancelled(DatabaseError databaseError) {
                                Fragment_manage_audit_trail.this.firebaseDone = true;
                            }
                        });
                    }
                    return;
                }
                Fragment_manage_audit_trail.this.firebaseDone = true;
            }

            @Override // com.google.firebase.database.ValueEventListener
            public void onCancelled(DatabaseError databaseError) {
                Fragment_manage_audit_trail.this.firebaseDone = true;
            }
        });
    }

    public void sortAudit() {
        boolean z;
        int i;
        boolean z2;
        Date date;
        boolean z3;
        String[] strArr = (String[]) this.stringList_who.toArray(new String[this.stringList_who.size()]);
        String[] strArr2 = (String[]) this.stringList_when.toArray(new String[this.stringList_when.size()]);
        String[] strArr3 = (String[]) this.stringList_address.toArray(new String[this.stringList_address.size()]);
        Double[] dArr = (Double[]) this.doubleList_longitude.toArray(new Double[this.doubleList_longitude.size()]);
        Double[] dArr2 = (Double[]) this.doubleList_latitude.toArray(new Double[this.doubleList_latitude.size()]);
        String[] strArr4 = (String[]) this.stringList_lock_status.toArray(new String[this.stringList_lock_status.size()]);
        String[] strArr5 = (String[]) this.stringList_email.toArray(new String[this.stringList_email.size()]);
        int i2 = 0;
        while (i2 < strArr2.length) {
            int i3 = 1;
            while (i3 < strArr2.length - i2) {
                int i4 = i3 - 1;
                if (strArr[i4].contains("Tag")) {
                    if (strArr2[i4].contains("null")) {
                        strArr2[i4] = "1970/01/01   00:00:01";
                    }
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm a", Locale.ENGLISH);
                    Date date2 = new Date();
                    try {
                        date = simpleDateFormat.parse(strArr2[i4]);
                        i = i2;
                        z3 = true;
                    } catch (ParseException e) {
                        e.printStackTrace();
                        date = date2;
                        z3 = false;
                        i = i2;
                    }
                    SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy/MM/dd   HH:mm:ss");
                    if (z3) {
                        try {
                            strArr2[i4] = simpleDateFormat2.format(Long.valueOf(date.getTime()));
                        } catch (Exception unused) {
                        }
                    }
                } else {
                    i = i2;
                }
                if (strArr[i3].contains("Tag")) {
                    if (strArr2[i3].contains("null")) {
                        strArr2[i3] = "1970/01/01   00:00:01";
                    }
                    SimpleDateFormat simpleDateFormat3 = new SimpleDateFormat("dd/MM/yyyy hh:mm a", Locale.ENGLISH);
                    Date date3 = new Date();
                    try {
                        date3 = simpleDateFormat3.parse(strArr2[i3]);
                        z2 = true;
                    } catch (ParseException unused2) {
                        z2 = false;
                    }
                    SimpleDateFormat simpleDateFormat4 = new SimpleDateFormat("yyyy/MM/dd   HH:mm:ss");
                    if (z2) {
                        try {
                            strArr2[i3] = simpleDateFormat4.format(Long.valueOf(date3.getTime()));
                        } catch (Exception unused3) {
                        }
                    }
                }
                if (Long.parseLong(strArr2[i4].replaceAll("[^0-9]", "")) < Long.parseLong(strArr2[i3].replaceAll("[^0-9]", ""))) {
                    String str = strArr[i4];
                    strArr[i4] = strArr[i3];
                    strArr[i3] = str;
                    String str2 = strArr2[i4];
                    strArr2[i4] = strArr2[i3];
                    strArr2[i3] = str2;
                    String str3 = strArr3[i4];
                    strArr3[i4] = strArr3[i3];
                    strArr3[i3] = str3;
                    Double d = dArr[i4];
                    dArr[i4] = dArr[i3];
                    dArr[i3] = d;
                    Double d2 = dArr2[i4];
                    dArr2[i4] = dArr2[i3];
                    dArr2[i3] = d2;
                    String str4 = strArr4[i4];
                    strArr4[i4] = strArr4[i3];
                    strArr4[i3] = str4;
                    String str5 = strArr5[i4];
                    strArr5[i4] = strArr5[i3];
                    strArr5[i3] = str5;
                }
                i3++;
                i2 = i;
            }
            i2++;
        }
        for (int i5 = 0; i5 < strArr2.length; i5++) {
            SimpleDateFormat simpleDateFormat5 = new SimpleDateFormat("yyyy/MM/dd   HH:mm:ss");
            Date date4 = new Date();
            try {
                date4 = simpleDateFormat5.parse(strArr2[i5]);
                z = true;
            } catch (ParseException e2) {
                e2.printStackTrace();
                z = false;
            }
            SimpleDateFormat simpleDateFormat6 = new SimpleDateFormat("MMM dd yyyy   hh:mm a");
            if (Locale.getDefault().getLanguage().equals("ja")) {
                simpleDateFormat6 = new SimpleDateFormat("yyyy年MM月dd日   HH時mm分ss秒");
            }
            if (z) {
                try {
                    strArr2[i5] = simpleDateFormat6.format(Long.valueOf(date4.getTime()));
                } catch (Exception unused4) {
                }
            }
        }
        this.stringList_who = new ArrayList<>(Arrays.asList(strArr));
        this.stringList_when = new ArrayList<>(Arrays.asList(strArr2));
        this.stringList_address = new ArrayList<>(Arrays.asList(strArr3));
        this.doubleList_longitude = new ArrayList<>(Arrays.asList(dArr));
        this.doubleList_latitude = new ArrayList<>(Arrays.asList(dArr2));
        this.stringList_lock_status = new ArrayList<>(Arrays.asList(strArr4));
        this.stringList_email = new ArrayList<>(Arrays.asList(strArr5));
    }
}
