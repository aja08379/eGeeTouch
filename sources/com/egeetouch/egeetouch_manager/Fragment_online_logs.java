package com.egeetouch.egeetouch_manager;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import cn.pedant.SweetAlert.SweetAlertDialog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import es.dmoral.toasty.Toasty;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
/* loaded from: classes.dex */
public class Fragment_online_logs extends Fragment {
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

    static /* synthetic */ int access$208() {
        int i = currentAccessCountNumber;
        currentAccessCountNumber = i + 1;
        return i;
    }

    public static Fragment_online_logs newInstance() {
        return new Fragment_online_logs();
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
            if (BLEService.AvailableAuditCount == 0) {
                retriveAuditTrail();
                tv_backup_msg.setVisibility(8);
                return;
            } else if (MainActivity.isAuditTrailBackup) {
                return;
            } else {
                retriveAuditTrail();
                tv_backup_msg.setVisibility(0);
                return;
            }
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
        firebaseDatabase.getReference("AuditTrail").child(serial).orderByChild("time").limitToLast(100).addListenerForSingleValueEvent(new ValueEventListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_online_logs.1
            @Override // com.google.firebase.database.ValueEventListener
            public void onCancelled(DatabaseError databaseError) {
            }

            @Override // com.google.firebase.database.ValueEventListener
            public void onDataChange(DataSnapshot dataSnapshot) {
                Fragment_online_logs.number_of_Access = dataSnapshot.getChildrenCount();
                long j = 0;
                if (Fragment_online_logs.number_of_Access != 0) {
                    int unused = Fragment_online_logs.currentAccessCountNumber = 0;
                    Iterator<DataSnapshot> it = dataSnapshot.getChildren().iterator();
                    while (it.hasNext()) {
                        Double.valueOf((double) FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
                        Double.valueOf((double) FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
                        HashMap hashMap = (HashMap) it.next().getValue();
                        String obj = hashMap.containsKey("userEmail") ? hashMap.get("userEmail").toString() : " N.A ";
                        String obj2 = hashMap.containsKey("lockedStatus") ? hashMap.get("lockedStatus").toString() : " N.A ";
                        String obj3 = hashMap.containsKey("Placemark") ? hashMap.get("Placemark").toString() : "Address not found";
                        long parseLong = hashMap.containsKey("time") ? Long.parseLong(hashMap.get("time").toString()) : j;
                        int parseInt = hashMap.containsKey("auditDeciValue") ? Integer.parseInt(hashMap.get("auditDeciValue").toString()) : 0;
                        long parseInt2 = hashMap.containsKey("lockBackTime") ? Integer.parseInt(hashMap.get("lockBackTime").toString()) : 0L;
                        Double valueOf = Double.valueOf(hashMap.containsKey("Longitude") ? Double.parseDouble(hashMap.get("Longitude").toString()) : FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
                        Double valueOf2 = Double.valueOf(hashMap.containsKey("Latitude") ? Double.parseDouble(hashMap.get("Latitude").toString()) : FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
                        Iterator<DataSnapshot> it2 = it;
                        Fragment_online_logs.auditEmail.add(Fragment_online_logs.currentAccessCountNumber, obj);
                        Fragment_online_logs.auditTime.add(Fragment_online_logs.currentAccessCountNumber, Long.valueOf(parseLong));
                        Fragment_online_logs.auditlockStatus.add(Fragment_online_logs.currentAccessCountNumber, obj2);
                        Fragment_online_logs.auditAddress.add(Fragment_online_logs.currentAccessCountNumber, obj3);
                        Fragment_online_logs.auditDeci_Value.add(Fragment_online_logs.currentAccessCountNumber, Integer.valueOf(parseInt));
                        Fragment_online_logs.auditLockBackTime.add(Fragment_online_logs.currentAccessCountNumber, Long.valueOf(parseInt2));
                        Fragment_online_logs.auditLongitude.add(Fragment_online_logs.currentAccessCountNumber, valueOf);
                        Fragment_online_logs.auditLatitude.add(Fragment_online_logs.currentAccessCountNumber, valueOf2);
                        Fragment_online_logs.access$208();
                        if (Fragment_online_logs.number_of_Access == Fragment_online_logs.currentAccessCountNumber) {
                            if (BLEService.selected_lock_model.contains("GT5100") || BLEService.selected_lock_model.equals("GT5107") || BLEService.selected_lock_model.equals("GT5109") || BLEService.selected_lock_model.equals("GT5300")) {
                                Fragment_online_logs.passcodeAudit();
                                System.out.println("Hello checking the Passcode Model GT5100 selected !");
                            } else {
                                Fragment_online_logs.tagAduit();
                                System.out.println("Hello checking the Passcode Model GT5100 is NOT selected !");
                            }
                        }
                        it = it2;
                        j = 0;
                    }
                } else if (BLEService.selected_lock_model.contains("GT5100") || BLEService.selected_lock_model.equals("GT5107") || BLEService.selected_lock_model.equals("GT5109") || BLEService.selected_lock_model.equals("GT5300")) {
                    Fragment_online_logs.passcodeAudit();
                    System.out.println("Hello checking the Passcode Model GT5100 selected !");
                } else {
                    Fragment_online_logs.tagAduit();
                    System.out.println("Hello checking the Passcode Model GT5100 is NOT selected !");
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void tagAduit() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        database = firebaseDatabase;
        firebaseDatabase.getReference("tagAuditTrail").child(serial).orderByChild("updateTime").limitToLast(100).addListenerForSingleValueEvent(new ValueEventListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_online_logs.2
            @Override // com.google.firebase.database.ValueEventListener
            public void onCancelled(DatabaseError databaseError) {
            }

            @Override // com.google.firebase.database.ValueEventListener
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Fragment_online_logs.number_of_Access = dataSnapshot.getChildrenCount();
                    int unused = Fragment_online_logs.currentAccessCountNumber = 0;
                    for (DataSnapshot dataSnapshot2 : dataSnapshot.getChildren()) {
                        HashMap hashMap = (HashMap) dataSnapshot2.getValue();
                        String obj = hashMap.containsKey("tagId") ? hashMap.get("tagId").toString() : " N.A ";
                        String obj2 = hashMap.containsKey("tagName") ? hashMap.get("tagName").toString() : " N.A ";
                        long parseLong = hashMap.containsKey("auditTrailTime") ? Long.parseLong(hashMap.get("auditTrailTime").toString()) : 0L;
                        int parseInt = hashMap.containsKey("auditDeciValue") ? Integer.parseInt(hashMap.get("auditDeciValue").toString()) : 0;
                        long parseInt2 = hashMap.containsKey("lockBackTime") ? Integer.parseInt(hashMap.get("lockBackTime").toString()) : 0L;
                        Fragment_online_logs.auditTime.add(Long.valueOf(parseLong + Fragment_online_logs.currentAccessCountNumber));
                        Fragment_online_logs.auditEmail.add(MainActivity.context.getString(R.string.tag_id_audit) + obj + "  " + MainActivity.context.getString(R.string.tag_name_audit) + obj2);
                        Fragment_online_logs.auditlockStatus.add(MainActivity.context.getResources().getString(R.string.unlocked));
                        Fragment_online_logs.auditAddress.add(MainActivity.context.getResources().getString(R.string.no_address_found));
                        Fragment_online_logs.auditDeci_Value.add(Integer.valueOf(parseInt));
                        Fragment_online_logs.auditLockBackTime.add(Long.valueOf(parseInt2));
                        Fragment_online_logs.auditLongitude.add(Double.valueOf((double) FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE));
                        Fragment_online_logs.auditLatitude.add(Double.valueOf((double) FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE));
                        Fragment_online_logs.currentAccessCountNumber++;
                        if (Fragment_online_logs.currentAccessCountNumber == Fragment_online_logs.number_of_Access) {
                            Fragment_online_logs.updateArrayAdapterView();
                        }
                    }
                } else if (Fragment_online_logs.auditEmail.size() != 0) {
                    Fragment_online_logs.updateArrayAdapterView();
                } else {
                    customProgressBar.closeDialog(10L);
                    Toast.makeText(MainActivity.context, (int) R.string.no_audittrial_history, 0).show();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void passcodeAudit() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        database = firebaseDatabase;
        Query limitToLast = firebaseDatabase.getReference("directionalPassCodeAuditTrail").child(serial).orderByChild("updateTime").limitToLast(100);
        System.out.println("Hello checking the Passcode Audit 1");
        limitToLast.addListenerForSingleValueEvent(new ValueEventListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_online_logs.3
            @Override // com.google.firebase.database.ValueEventListener
            public void onCancelled(DatabaseError databaseError) {
            }

            @Override // com.google.firebase.database.ValueEventListener
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Fragment_online_logs.number_of_Access = dataSnapshot.getChildrenCount();
                    int unused = Fragment_online_logs.currentAccessCountNumber = 0;
                    for (DataSnapshot dataSnapshot2 : dataSnapshot.getChildren()) {
                        HashMap hashMap = (HashMap) dataSnapshot2.getValue();
                        if (hashMap.containsKey("directionalPasscode")) {
                            hashMap.get("directionalPasscode").toString();
                        }
                        String obj = hashMap.containsKey("passcodeName") ? hashMap.get("passcodeName").toString() : " N.A ";
                        long parseLong = hashMap.containsKey("auditTrailTime") ? Long.parseLong(hashMap.get("auditTrailTime").toString()) : 0L;
                        int parseInt = hashMap.containsKey("auditDeciValue") ? Integer.parseInt(hashMap.get("auditDeciValue").toString()) : 0;
                        long parseInt2 = hashMap.containsKey("lockBackTime") ? Integer.parseInt(hashMap.get("lockBackTime").toString()) : 0L;
                        System.out.println("Hello checking the lockBackTime : " + parseInt2);
                        Fragment_online_logs.auditTime.add(Long.valueOf(parseLong + Fragment_online_logs.currentAccessCountNumber));
                        Fragment_online_logs.auditEmail.add(Fragment_online_logs.rootView.getResources().getString(R.string.passcode_name) + obj);
                        Fragment_online_logs.auditlockStatus.add(Fragment_online_logs.rootView.getResources().getString(R.string.unlocked));
                        Fragment_online_logs.auditAddress.add(Fragment_online_logs.rootView.getResources().getString(R.string.no_address_found));
                        Fragment_online_logs.auditDeci_Value.add(Integer.valueOf(parseInt));
                        Fragment_online_logs.auditLockBackTime.add(Long.valueOf(parseInt2));
                        Fragment_online_logs.auditLongitude.add(Double.valueOf((double) FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE));
                        Fragment_online_logs.auditLatitude.add(Double.valueOf((double) FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE));
                        Fragment_online_logs.currentAccessCountNumber++;
                        System.out.println("Hello checking the Passcode Audit 2");
                        if (Fragment_online_logs.currentAccessCountNumber == Fragment_online_logs.number_of_Access) {
                            Fragment_online_logs.updateArrayAdapterView();
                        }
                    }
                } else if (Fragment_online_logs.auditEmail.size() != 0) {
                    Fragment_online_logs.updateArrayAdapterView();
                } else {
                    customProgressBar.closeDialog(10L);
                    Toast.makeText(MainActivity.context, (int) R.string.no_audittrial_history, 0).show();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void updateArrayAdapterView() {
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
        customProgressBar.closeDialog(10L);
        long parseLong = Long.parseLong(String.valueOf(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis())));
        Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
        calendar.setTimeInMillis(parseLong * 1000);
        String charSequence = DateFormat.format("MMM dd yyyy, HH:mm", calendar).toString();
        TextView textView = (TextView) rootView.findViewById(R.id.textView_last_updatedTime);
        textView.setText(rootView.getResources().getString(R.string.last_update) + " " + charSequence);
        if (MainActivity.isAuditTrailBackup) {
            tv_backup_msg.setVisibility(8);
        } else {
            textView.setVisibility(8);
        }
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
        rootView = layoutInflater.inflate(R.layout.fragment_audit_trail, viewGroup, false);
        setHasOptionsMenu(true);
        ListView listView = (ListView) rootView.findViewById(R.id.listView_access_history);
        listview = listView;
        listView.setScrollingCacheEnabled(false);
        TextView textView = (TextView) rootView.findViewById(R.id.tv_paid_version);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        if (BLEService.selected_lock_model.equals("GT1000") || BLEService.selected_lock_model.equals("GT2002") || BLEService.selected_lock_model.equals("GT2003") || BLEService.selected_lock_model.equals("GT3100") || BLEService.selected_lock_model.equals("GT3002")) {
            textView.setVisibility(8);
        }
        return rootView;
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
}
