package com.egeetouch.egeetouch_manager;

import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import cn.pedant.SweetAlert.SweetAlertDialog;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class Fragment_manage_audit_trail_nfc extends Fragment {
    private static String address = "";
    private static boolean address_obtained = false;
    private static boolean address_wait_for_response = false;
    private static int convert_address_count = 0;
    private static boolean disconnected_dialog_show = false;
    public static boolean internet_available = false;
    public static boolean is_new_retrieve_data = false;
    public static boolean is_pd_retrieve_address_show = false;
    public static boolean start_convert_address = false;
    private static boolean start_update_list = false;
    SweetAlertDialog pd_retrieve_address;
    View rootView;
    String[] values_address;
    String[] values_when;
    Double[] values_where_Latitude;
    Double[] values_where_Longitude;
    String[] values_who;
    private boolean no_data = true;
    String last_update = "";
    private Handler manage_audit_handler = new Handler();
    final Runnable r = new Runnable() { // from class: com.egeetouch.egeetouch_manager.Fragment_manage_audit_trail_nfc.1
        @Override // java.lang.Runnable
        public void run() {
            if (Fragment_manage_audit_trail_nfc.is_new_retrieve_data) {
                if (!Fragment_manage_audit_trail_nfc.is_pd_retrieve_address_show) {
                    Fragment_manage_audit_trail_nfc.is_pd_retrieve_address_show = true;
                    Fragment_manage_audit_trail_nfc.this.pd_retrieve_address = new SweetAlertDialog(MainActivity.context, 5);
                    Fragment_manage_audit_trail_nfc.this.pd_retrieve_address.setTitleText(MainActivity.context.getResources().getString(R.string.audit_trail)).setContentText(MainActivity.context.getResources().getString(R.string.please_wait_audit_address)).setCancelText(MainActivity.context.getResources().getString(R.string.cancel)).setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_manage_audit_trail_nfc.1.1
                        @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            sweetAlertDialog.dismissWithAnimation();
                            BLEService.Ble_Mode = 0;
                            MainActivity.fragmentManager.popBackStack();
                        }
                    }).show();
                }
                if (Fragment_manage_audit_trail_nfc.start_convert_address) {
                    Log.i("TAG", "start_convert_address");
                    if (Fragment_manage_audit_trail_nfc.this.values_where_Latitude[Fragment_manage_audit_trail_nfc.convert_address_count].doubleValue() <= -90.0d || Fragment_manage_audit_trail_nfc.this.values_where_Longitude[Fragment_manage_audit_trail_nfc.convert_address_count].doubleValue() <= -180.0d || Fragment_manage_audit_trail_nfc.this.values_where_Latitude[Fragment_manage_audit_trail_nfc.convert_address_count].doubleValue() >= 90.0d || Fragment_manage_audit_trail_nfc.this.values_where_Longitude[Fragment_manage_audit_trail_nfc.convert_address_count].doubleValue() >= 180.0d || Fragment_manage_audit_trail_nfc.this.values_where_Latitude[Fragment_manage_audit_trail_nfc.convert_address_count].doubleValue() == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE || Fragment_manage_audit_trail_nfc.this.values_where_Longitude[Fragment_manage_audit_trail_nfc.convert_address_count].doubleValue() == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                        String unused = Fragment_manage_audit_trail_nfc.address = "N.A.";
                        boolean unused2 = Fragment_manage_audit_trail_nfc.address_obtained = true;
                    } else {
                        Fragment_manage_audit_trail_nfc fragment_manage_audit_trail_nfc = Fragment_manage_audit_trail_nfc.this;
                        fragment_manage_audit_trail_nfc.convert_coordinates_2_address(fragment_manage_audit_trail_nfc.values_where_Longitude[Fragment_manage_audit_trail_nfc.convert_address_count].doubleValue(), Fragment_manage_audit_trail_nfc.this.values_where_Latitude[Fragment_manage_audit_trail_nfc.convert_address_count].doubleValue());
                    }
                    Fragment_manage_audit_trail_nfc.start_convert_address = false;
                }
                if (Fragment_manage_audit_trail_nfc.address_obtained) {
                    Log.i("TAG", "address_obtained");
                    if (Fragment_manage_audit_trail_nfc.convert_address_count == 19) {
                        Fragment_manage_audit_trail_nfc.this.values_address[Fragment_manage_audit_trail_nfc.convert_address_count] = Fragment_manage_audit_trail_nfc.address;
                        DatabaseVariable.db_location.execSQL(DatabaseVariable.locationdb_delete_value(String.valueOf(Fragment_manage_audit_trail_nfc.convert_address_count), BLEService.selected_lock_name));
                        DatabaseVariable.db_location.execSQL(DatabaseVariable.locationdb_insert_value(String.valueOf(Fragment_manage_audit_trail_nfc.convert_address_count), Fragment_manage_audit_trail_nfc.address, BLEService.selected_lock_name));
                        boolean unused3 = Fragment_manage_audit_trail_nfc.start_update_list = true;
                        Fragment_manage_audit_trail_nfc.start_convert_address = false;
                    } else {
                        Fragment_manage_audit_trail_nfc.this.values_address[Fragment_manage_audit_trail_nfc.convert_address_count] = Fragment_manage_audit_trail_nfc.address;
                        DatabaseVariable.db_location.execSQL(DatabaseVariable.locationdb_delete_value(String.valueOf(Fragment_manage_audit_trail_nfc.convert_address_count), BLEService.selected_lock_name));
                        DatabaseVariable.db_location.execSQL(DatabaseVariable.locationdb_insert_value(String.valueOf(Fragment_manage_audit_trail_nfc.convert_address_count), Fragment_manage_audit_trail_nfc.address, BLEService.selected_lock_name));
                        Fragment_manage_audit_trail_nfc.access$008();
                        Fragment_manage_audit_trail_nfc.start_convert_address = true;
                    }
                    boolean unused4 = Fragment_manage_audit_trail_nfc.address_obtained = false;
                }
                if (Fragment_manage_audit_trail_nfc.start_update_list) {
                    Fragment_manage_audit_trail_nfc.this.pd_retrieve_address.dismissWithAnimation();
                    Fragment_manage_audit_trail_nfc.this.update_UI_listview();
                    Fragment_manage_audit_trail_nfc.is_new_retrieve_data = false;
                    Fragment_manage_audit_trail_nfc.is_pd_retrieve_address_show = false;
                }
            }
            Fragment_manage_audit_trail_nfc.this.manage_audit_handler.postDelayed(this, 300L);
        }
    };

    static /* synthetic */ int access$008() {
        int i = convert_address_count;
        convert_address_count = i + 1;
        return i;
    }

    public static Fragment_manage_audit_trail_nfc newInstance() {
        return new Fragment_manage_audit_trail_nfc();
    }

    @Override // android.app.Fragment
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.audit_trail);
        this.manage_audit_handler.postDelayed(this.r, 300L);
    }

    @Override // android.app.Fragment
    public void onPause() {
        super.onPause();
        TaskManagement.password_varified = true;
        this.manage_audit_handler.removeCallbacks(this.r);
    }

    @Override // android.app.Fragment
    public void onStop() {
        super.onStop();
    }

    @Override // android.app.Fragment
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.nfc, menu);
    }

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.rootView = layoutInflater.inflate(R.layout.fragment_audit_trail_nfc, viewGroup, false);
        MainActivity.fab.setVisibility(4);
        MainActivity.fab_share.setVisibility(4);
        MainActivity.fab_admin_access_locks.setVisibility(8);
        if (MainActivity.dashboard_listview != null) {
            MainActivity.dashboard_listview.setVisibility(4);
        }
        MainActivity.pullToRefresh.setEnabled(false);
        Handler handler = new Handler();
        this.manage_audit_handler = handler;
        handler.post(this.r);
        this.values_who = new String[20];
        this.values_when = new String[20];
        this.values_where_Latitude = new Double[20];
        this.values_where_Longitude = new Double[20];
        this.values_address = new String[20];
        boolean haveNetworkConnection = haveNetworkConnection();
        internet_available = haveNetworkConnection;
        if (!haveNetworkConnection) {
            new SweetAlertDialog(MainActivity.context, 3).setTitleText(MainActivity.context.getResources().getString(R.string.audit_trail)).setContentText(MainActivity.context.getResources().getString(R.string.internet_is_not_available)).setConfirmText(MainActivity.context.getResources().getString(R.string.ok)).show();
        }
        retrieve_data_from_database();
        update_UI_listview();
        TextView textView = (TextView) this.rootView.findViewById(R.id.tv_paid_version);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        if (BLEService.selected_lock_model.equals("GT1000") || BLEService.selected_lock_model.equals("GT2000") || BLEService.selected_lock_model.equals("GT2002") || BLEService.selected_lock_model.equals("GT2003") || BLEService.selected_lock_model.equals("GT3100") || BLEService.selected_lock_model.equals("GT3002")) {
            textView.setVisibility(8);
        }
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

    private void retrieve_data_from_database() {
        this.no_data = false;
        Cursor rawQuery = DatabaseVariable.db_audittrail.rawQuery(DatabaseVariable.db_audittrail_lock(BLEService.selected_lock_name), null);
        int i = -1;
        while (rawQuery.moveToNext()) {
            this.no_data = false;
            i++;
            this.values_who[i] = rawQuery.getString(1);
            this.values_when[i] = rawQuery.getString(2);
            this.values_where_Latitude[i] = Double.valueOf(rawQuery.getDouble(3));
            this.values_where_Longitude[i] = Double.valueOf(rawQuery.getDouble(4));
            DecimalFormat decimalFormat = new DecimalFormat("#.######");
            Double[] dArr = this.values_where_Latitude;
            dArr[i] = Double.valueOf(decimalFormat.format(dArr[i]));
            Double[] dArr2 = this.values_where_Longitude;
            dArr2[i] = Double.valueOf(decimalFormat.format(dArr2[i]));
            this.last_update = rawQuery.getString(5);
        }
        rawQuery.close();
        for (int i2 = 0; i2 <= 19; i2++) {
            Log.i("TAG", "retrieve_address_counter: " + String.valueOf(i2));
            Cursor rawQuery2 = DatabaseVariable.db_location.rawQuery(DatabaseVariable.locationdb_Query_addess(String.valueOf(i2), BLEService.selected_lock_name), null);
            if (rawQuery2.moveToNext()) {
                Log.i("TAG", "retrieve_address_counter: " + String.valueOf(i2) + " cursor2.getString(0): " + rawQuery2.getString(0));
                this.values_address[i2] = rawQuery2.getString(0);
            }
            rawQuery2.close();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void update_UI_listview() {
        if (this.no_data) {
            TaskManagement.current_function = TaskManagement.log_extract10;
            TaskManagement.admin_update_done = false;
            TaskManagement.user_update_done = false;
            TaskManagement.tag_update_done = false;
            TaskManagement.log_extract10_done = false;
            MainActivity.fragmentManager.beginTransaction().replace(R.id.container, Fragment_update.newInstance()).addToBackStack("7").commit();
            return;
        }
        ArrayAdapter_audittrial_nfc arrayAdapter_audittrial_nfc = new ArrayAdapter_audittrial_nfc(getActivity(), new ArrayList(Arrays.asList(this.values_who)), new ArrayList(Arrays.asList(this.values_when)), new ArrayList(Arrays.asList(this.values_address)));
        ListView listView = (ListView) this.rootView.findViewById(R.id.listView_access_history);
        listView.setScrollingCacheEnabled(false);
        listView.setAdapter((ListAdapter) arrayAdapter_audittrial_nfc);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_manage_audit_trail_nfc.2
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                Double valueOf = Double.valueOf((double) FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
                Cursor rawQuery = DatabaseVariable.db_audittrail.rawQuery(DatabaseVariable.db_audittrail_lock(BLEService.selected_lock_name), null);
                int i2 = 0;
                Double d = valueOf;
                while (rawQuery.moveToNext()) {
                    if (i2 == i) {
                        i2 = 21;
                        valueOf = Double.valueOf(rawQuery.getDouble(3));
                        d = Double.valueOf(rawQuery.getDouble(4));
                    } else {
                        i2++;
                    }
                }
                Log.i("TAG", "latitude: " + String.valueOf(valueOf) + " longitude: " + String.valueOf(d));
                if (valueOf.doubleValue() == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE || d.doubleValue() == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE || valueOf.doubleValue() <= -90.0d || d.doubleValue() <= -180.0d || valueOf.doubleValue() >= 90.0d || d.doubleValue() >= 180.0d) {
                    return;
                }
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("geo:<" + valueOf + ">,<" + d + ">?q=<" + valueOf + ">,<" + d + ">(" + Fragment_manage_audit_trail_nfc.this.rootView.getResources().getString(R.string.egeetouch_lock_location) + ")"));
                intent.setPackage("com.google.android.apps.maps");
                if (intent.resolveActivity(Fragment_manage_audit_trail_nfc.this.getActivity().getPackageManager()) != null) {
                    Fragment_manage_audit_trail_nfc.this.startActivity(intent);
                }
            }
        });
        ((TextView) this.rootView.findViewById(R.id.textView_Audit_trail_lock_name)).setText(BLEService.selected_lock_name);
        ((TextView) this.rootView.findViewById(R.id.textView_last_updatedTime)).setText(this.rootView.getResources().getString(R.string.last_update) + " " + this.last_update);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void convert_coordinates_2_address(double d, double d2) {
        if (Helper_PhoneDetails.phonelangauge().equals("ja")) {
            new google_geocoder_api_Task("https://maps.googleapis.com/maps/api/geocode/json?latlng=" + d2 + "," + d + "&key=AIzaSyCAqcyJtbuXJ3szf8kLsx06zzKMD2vPy1A&language=zh-TW").execute(new Void[0]);
        } else {
            new google_geocoder_api_Task("https://maps.googleapis.com/maps/api/geocode/json?latlng=" + d2 + "," + d + "&key=AIzaSyCAqcyJtbuXJ3szf8kLsx06zzKMD2vPy1A").execute(new Void[0]);
        }
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
                    boolean unused = Fragment_manage_audit_trail_nfc.address_obtained = true;
                    String unused2 = Fragment_manage_audit_trail_nfc.address = "N.A.";
                    return "N.A.";
                }
                JSONObject jSONObject = new JSONObject(EntityUtils.toString(execute.getEntity())).getJSONArray("results").getJSONObject(0);
                Log.i("", jSONObject.getString("formatted_address"));
                boolean unused3 = Fragment_manage_audit_trail_nfc.address_obtained = true;
                String unused4 = Fragment_manage_audit_trail_nfc.address = jSONObject.getString("formatted_address");
                return jSONObject.getString("formatted_address");
            } catch (IOException e) {
                e.printStackTrace();
                boolean unused5 = Fragment_manage_audit_trail_nfc.address_obtained = true;
                String unused6 = Fragment_manage_audit_trail_nfc.address = "N.A.";
                return "N.A.";
            } catch (JSONException e2) {
                e2.printStackTrace();
                boolean unused7 = Fragment_manage_audit_trail_nfc.address_obtained = true;
                String unused8 = Fragment_manage_audit_trail_nfc.address = "N.A.";
                return "N.A.";
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(String str) {
            super.onPostExecute((google_geocoder_api_Task) str);
        }
    }
}
