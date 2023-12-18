package com.egeetouch.egeetouch_manager;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.Menu;
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
import es.dmoral.toasty.Toasty;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
/* loaded from: classes.dex */
public class Fragment_watch_audit extends Fragment {
    static Arrayadapter_Online_auditTrail adapter_online_auditTrail;
    private static FirebaseDatabase database;
    static ListView listview;
    static View rootView;
    private static String serial;
    static TextView tv_backup_msg;
    private Menu menu;
    static List<String> addressList = new ArrayList();
    static List<String> lockStatusList = new ArrayList();
    static List<Long> timeList = new ArrayList();
    static List<String> emailList = new ArrayList();
    static List<Integer> auditDeciValueList = new ArrayList();
    static List<Long> lockBackTimeList = new ArrayList();
    static List<Double> longitudeList = new ArrayList();
    static List<Double> latitudeList = new ArrayList();
    public static ArrayList<String> auditEmail = new ArrayList<>();
    public static ArrayList<Long> auditTime = new ArrayList<>();
    public static ArrayList<String> auditlockStatus = new ArrayList<>();
    public static ArrayList<String> auditAddress = new ArrayList<>();
    public static ArrayList<Integer> auditDeci_Value = new ArrayList<>();
    public static ArrayList<Long> auditLockBackTime = new ArrayList<>();
    public static ArrayList<Double> auditLongitude = new ArrayList<>();
    public static ArrayList<Double> auditLatitude = new ArrayList<>();
    public static long number_of_Access = 0;
    private static int currentAccessCountNumber = 0;

    static /* synthetic */ int access$008() {
        int i = currentAccessCountNumber;
        currentAccessCountNumber = i + 1;
        return i;
    }

    public static Fragment_watch_audit newInstance() {
        return new Fragment_watch_audit();
    }

    @Override // android.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // android.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        serial = BLEService.parsedIp45SerialNumber;
        ListView listView = (ListView) rootView.findViewById(R.id.listView_access_history);
        listview = listView;
        listView.setScrollingCacheEnabled(false);
        tv_backup_msg = (TextView) rootView.findViewById(R.id.tv_AuditTrailBackup_msg);
        ((ImageView) rootView.findViewById(R.id.imageView_proximity)).setVisibility(8);
        if (Helper_Network.haveNetworkConnection(MainActivity.context)) {
            retriveAuditTrail();
            tv_backup_msg.setVisibility(8);
            return;
        }
        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(MainActivity.context, 0);
        sweetAlertDialog.setTitleText(getString(R.string.pls_note));
        sweetAlertDialog.setContentText(getString(R.string.you_are_not_connected_access_lock));
        sweetAlertDialog.show();
        Toasty.normal(MainActivity.context, (int) R.string.you_are_not_connected_access_lock, getResources().getDrawable(R.drawable.ic_cloud_off_black_24dp)).show();
    }

    public static void showAuditTrail() {
        retriveAuditTrail();
    }

    private static void retriveAuditTrail() {
        serial = BLEService.parsedIp45SerialNumber;
        auditEmail.clear();
        auditTime.clear();
        auditlockStatus.clear();
        auditAddress.clear();
        auditDeci_Value.clear();
        auditLockBackTime.clear();
        auditLongitude.clear();
        auditLatitude.clear();
        customProgressBar.ShowProgressBar(MainActivity.context, MainActivity.context.getString(R.string.loading));
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        database = firebaseDatabase;
        firebaseDatabase.getReference("watchAuditTrail").child(serial).orderByChild("time").limitToLast(100).addListenerForSingleValueEvent(new ValueEventListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_watch_audit.1
            @Override // com.google.firebase.database.ValueEventListener
            public void onCancelled(DatabaseError databaseError) {
            }

            @Override // com.google.firebase.database.ValueEventListener
            public void onDataChange(DataSnapshot dataSnapshot) {
                Fragment_watch_audit.number_of_Access = dataSnapshot.getChildrenCount();
                if (Fragment_watch_audit.number_of_Access == 0) {
                    return;
                }
                int unused = Fragment_watch_audit.currentAccessCountNumber = 0;
                for (DataSnapshot dataSnapshot2 : dataSnapshot.getChildren()) {
                    double d = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
                    Double.valueOf((double) FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
                    Double.valueOf((double) FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
                    HashMap hashMap = (HashMap) dataSnapshot2.getValue();
                    String obj = hashMap.containsKey("name") ? hashMap.get("name").toString() : " N.A ";
                    String obj2 = hashMap.containsKey("lockedStatus") ? hashMap.get("lockedStatus").toString() : " N.A ";
                    String obj3 = hashMap.containsKey("Placemark") ? hashMap.get("Placemark").toString() : "Address not found";
                    long parseLong = hashMap.containsKey("time") ? Long.parseLong(hashMap.get("time").toString()) * 1000 : 0L;
                    Double valueOf = Double.valueOf(hashMap.containsKey("Longitude") ? Double.parseDouble(hashMap.get("Longitude").toString()) : 0.0d);
                    if (hashMap.containsKey("Latitude")) {
                        d = Double.parseDouble(hashMap.get("Latitude").toString());
                    }
                    Double valueOf2 = Double.valueOf(d);
                    Fragment_watch_audit.auditEmail.add(Fragment_watch_audit.currentAccessCountNumber, obj);
                    Fragment_watch_audit.auditTime.add(Fragment_watch_audit.currentAccessCountNumber, Long.valueOf(parseLong));
                    Fragment_watch_audit.auditlockStatus.add(Fragment_watch_audit.currentAccessCountNumber, obj2);
                    Fragment_watch_audit.auditAddress.add(Fragment_watch_audit.currentAccessCountNumber, obj3);
                    Fragment_watch_audit.auditDeci_Value.add(Fragment_watch_audit.currentAccessCountNumber, 0);
                    Fragment_watch_audit.auditLockBackTime.add(Fragment_watch_audit.currentAccessCountNumber, 0L);
                    Fragment_watch_audit.auditLongitude.add(Fragment_watch_audit.currentAccessCountNumber, valueOf);
                    Fragment_watch_audit.auditLatitude.add(Fragment_watch_audit.currentAccessCountNumber, valueOf2);
                    Fragment_watch_audit.access$008();
                    if (Fragment_watch_audit.number_of_Access == Fragment_watch_audit.currentAccessCountNumber) {
                        Fragment_watch_audit.updateArrayAdapterView();
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void updateArrayAdapterView() {
        Calendar calendar;
        emailList.clear();
        timeList.clear();
        addressList.clear();
        lockStatusList.clear();
        auditDeciValueList.clear();
        lockBackTimeList.clear();
        longitudeList.clear();
        latitudeList.clear();
        SortAuditLogs2 sortAuditLogs2 = new SortAuditLogs2(auditTime, auditAddress, auditEmail, auditlockStatus, auditDeci_Value, auditLockBackTime, auditLongitude, auditLatitude);
        auditTime = sortAuditLogs2.getTime();
        auditAddress = sortAuditLogs2.getAddress();
        auditEmail = sortAuditLogs2.getEmail();
        auditlockStatus = sortAuditLogs2.getLockStatus();
        auditDeci_Value = sortAuditLogs2.getAuditDeciValue();
        auditLockBackTime = sortAuditLogs2.getLockBacktime();
        auditLongitude = sortAuditLogs2.getLongitude();
        auditLatitude = sortAuditLogs2.getLatitude();
        int size = auditEmail.size() - 1;
        for (int i = 1; i <= 20 && size >= 0; i++) {
            emailList.add(auditEmail.get(size));
            size--;
        }
        int size2 = auditTime.size() - 1;
        for (int i2 = 1; i2 <= 20 && size2 >= 0; i2++) {
            timeList.add(auditTime.get(size2));
            size2--;
        }
        int size3 = auditAddress.size() - 1;
        for (int i3 = 1; i3 <= 20 && size3 >= 0; i3++) {
            addressList.add(auditAddress.get(size3));
            size3--;
        }
        int size4 = auditlockStatus.size() - 1;
        for (int i4 = 1; i4 <= 20 && size4 >= 0; i4++) {
            lockStatusList.add(auditlockStatus.get(size4));
            size4--;
        }
        int size5 = auditDeci_Value.size() - 1;
        for (int i5 = 1; i5 <= 20 && size5 >= 0; i5++) {
            auditDeciValueList.add(auditDeci_Value.get(size5));
            size5--;
        }
        int size6 = auditLockBackTime.size() - 1;
        for (int i6 = 1; i6 <= 20 && size6 >= 0; i6++) {
            lockBackTimeList.add(auditLockBackTime.get(size6));
            size6--;
        }
        int size7 = auditLongitude.size() - 1;
        for (int i7 = 1; i7 <= 20 && size7 >= 0; i7++) {
            longitudeList.add(auditLongitude.get(size7));
            size7--;
        }
        int size8 = auditLatitude.size() - 1;
        for (int i8 = 1; i8 <= 20 && size8 >= 0; i8++) {
            latitudeList.add(auditLatitude.get(size8));
            size8--;
        }
        Arrayadapter_Online_auditTrail arrayadapter_Online_auditTrail = new Arrayadapter_Online_auditTrail(MainActivity.context, emailList, timeList, addressList, lockStatusList, auditDeciValueList, lockBackTimeList, longitudeList, latitudeList);
        adapter_online_auditTrail = arrayadapter_Online_auditTrail;
        listview.setAdapter((ListAdapter) arrayadapter_Online_auditTrail);
        adapter_online_auditTrail.notifyDataSetChanged();
        long parseLong = Long.parseLong(String.valueOf(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis())));
        Calendar.getInstance(Locale.ENGLISH).setTimeInMillis(parseLong * 1000);
        ((TextView) rootView.findViewById(R.id.textView_last_updatedTime)).setText(rootView.getResources().getString(R.string.last_update) + " " + DateFormat.format("MMM dd yyyy, HH:mm", calendar).toString());
        customProgressBar.closeDialog(0L);
    }

    @Override // android.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        serial = BLEService.parsedIp45SerialNumber;
        super.onViewCreated(view, bundle);
    }

    @Override // android.app.Fragment
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(getString(R.string.audit_trail));
    }

    @Override // android.app.Fragment
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

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        rootView = layoutInflater.inflate(R.layout.fragment_watch_audit, viewGroup, false);
        setHasOptionsMenu(true);
        ListView listView = (ListView) rootView.findViewById(R.id.listView_access_history);
        listview = listView;
        listView.setScrollingCacheEnabled(false);
        return rootView;
    }
}
