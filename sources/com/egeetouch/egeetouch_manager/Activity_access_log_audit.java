package com.egeetouch.egeetouch_manager;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import cn.pedant.SweetAlert.SweetAlertDialog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
/* loaded from: classes.dex */
public class Activity_access_log_audit extends AppCompatActivity {
    Arrayadapter_access_log_audit accesslogAuditAdapter;
    Context context;
    private FirebaseDatabase database;
    ListView listview;
    public SweetAlertDialog loading_dialog;
    private String lockModel;
    private String serial;
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
    View rootView = null;
    List<String> addressList = new ArrayList();
    List<String> lockStatusList = new ArrayList();
    List<Long> timeList = new ArrayList();
    List<String> emailList = new ArrayList();
    List<Integer> auditDeciValueList = new ArrayList();
    List<Long> lockBackTimeList = new ArrayList();
    List<Double> longitudeList = new ArrayList();
    List<Double> latitudeList = new ArrayList();

    static /* synthetic */ int access$208() {
        int i = currentAccessCountNumber;
        currentAccessCountNumber = i + 1;
        return i;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_access_log_audit);
        getWindow().setFlags(1024, 1024);
        System.out.println("Hello checking the Activity : 2");
        this.serial = getIntent().getExtras().get("lockserial").toString();
        this.lockModel = getIntent().getExtras().get("lockModel").toString();
        getSupportActionBar().setTitle(this.serial);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.listview = (ListView) findViewById(R.id.access_audit_list_view);
        if (Helper_Network.haveNetworkConnection(this)) {
            InitializerFields();
        } else {
            SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(this, 0);
            sweetAlertDialog.setTitleText(getString(R.string.pls_note));
            sweetAlertDialog.setContentText(getString(R.string.you_are_not_connected_access_lock));
            sweetAlertDialog.show();
            Toast.makeText(this, (int) R.string.you_are_not_connected_access_lock, 1).show();
        }
        TextView textView = (TextView) findViewById(R.id.tv_paid_version);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        if (BLEService.selected_lock_model.equals("GT1000") || BLEService.selected_lock_model.equals("GT2002") || BLEService.selected_lock_model.equals("GT2003") || BLEService.selected_lock_model.equals("GT3100") || BLEService.selected_lock_model.equals("GT3002")) {
            textView.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkingForLimitedAuditTrail() {
        currentAccessCountNumber = 0;
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        this.database = firebaseDatabase;
        firebaseDatabase.getReference("AuditTrail").child(this.serial).orderByChild("time").limitToLast(10).addListenerForSingleValueEvent(new ValueEventListener() { // from class: com.egeetouch.egeetouch_manager.Activity_access_log_audit.1
            @Override // com.google.firebase.database.ValueEventListener
            public void onCancelled(DatabaseError databaseError) {
            }

            @Override // com.google.firebase.database.ValueEventListener
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Activity_access_log_audit.number_of_Access = dataSnapshot.getChildrenCount();
                    for (DataSnapshot dataSnapshot2 : dataSnapshot.getChildren()) {
                        Activity_access_log_audit.this.database.getReference("AuditTrail").child(Activity_access_log_audit.this.serial).child(dataSnapshot2.getKey()).addValueEventListener(new ValueEventListener() { // from class: com.egeetouch.egeetouch_manager.Activity_access_log_audit.1.1
                            @Override // com.google.firebase.database.ValueEventListener
                            public void onCancelled(DatabaseError databaseError) {
                            }

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
                                    Activity_access_log_audit.auditEmail.add(Activity_access_log_audit.currentAccessCountNumber, obj);
                                    Activity_access_log_audit.auditTime.add(Activity_access_log_audit.currentAccessCountNumber, Long.valueOf(parseLong));
                                    Activity_access_log_audit.auditlockStatus.add(Activity_access_log_audit.currentAccessCountNumber, obj2);
                                    Activity_access_log_audit.auditAddress.add(Activity_access_log_audit.currentAccessCountNumber, obj3);
                                    Activity_access_log_audit.auditDeci_Value.add(Activity_access_log_audit.currentAccessCountNumber, Integer.valueOf(parseInt));
                                    Activity_access_log_audit.auditLockBackTime.add(Activity_access_log_audit.currentAccessCountNumber, Long.valueOf(parseInt2));
                                    Activity_access_log_audit.auditLongitude.add(Activity_access_log_audit.currentAccessCountNumber, Double.valueOf(parseDouble));
                                    Activity_access_log_audit.auditLatitude.add(Activity_access_log_audit.currentAccessCountNumber, Double.valueOf(d));
                                    Activity_access_log_audit.access$208();
                                    if (Activity_access_log_audit.number_of_Access == Activity_access_log_audit.currentAccessCountNumber) {
                                        if (Activity_access_log_audit.this.lockModel.equals("4") || Activity_access_log_audit.this.lockModel.equals("10")) {
                                            Activity_access_log_audit.this.tagAudit();
                                        } else if (Activity_access_log_audit.this.lockModel.equals("7") || Activity_access_log_audit.this.lockModel.contains("8") || Activity_access_log_audit.this.lockModel.contains("9")) {
                                            Activity_access_log_audit.this.passcodeAudit();
                                        }
                                    }
                                }
                            }
                        });
                    }
                }
            }
        });
    }

    private void InitializerFields() {
        auditEmail.clear();
        auditTime.clear();
        auditlockStatus.clear();
        auditAddress.clear();
        auditDeci_Value.clear();
        auditLockBackTime.clear();
        auditLongitude.clear();
        auditLatitude.clear();
        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(this, 5);
        this.loading_dialog = sweetAlertDialog;
        sweetAlertDialog.setTitleText(getString(R.string.loading));
        customProgressBar.ShowProgressBar(this, getString(R.string.loading));
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        this.database = firebaseDatabase;
        firebaseDatabase.getReference("AuditTrail").child(this.serial).addListenerForSingleValueEvent(new ValueEventListener() { // from class: com.egeetouch.egeetouch_manager.Activity_access_log_audit.2
            @Override // com.google.firebase.database.ValueEventListener
            public void onCancelled(DatabaseError databaseError) {
            }

            @Override // com.google.firebase.database.ValueEventListener
            public void onDataChange(DataSnapshot dataSnapshot) {
                Activity_access_log_audit.number_of_Access = dataSnapshot.getChildrenCount();
                if (Activity_access_log_audit.number_of_Access == 0) {
                    if (Activity_access_log_audit.this.lockModel.equals("4")) {
                        Activity_access_log_audit.this.tagAudit();
                    } else if (Activity_access_log_audit.this.lockModel.equals("7") || Activity_access_log_audit.this.lockModel.contains("8") || Activity_access_log_audit.this.lockModel.contains("9")) {
                        Activity_access_log_audit.this.passcodeAudit();
                    }
                } else if (Activity_access_log_audit.number_of_Access <= 10) {
                    Activity_access_log_audit.this.checkingForLimitedAuditTrail();
                } else {
                    Activity_access_log_audit.this.checkingForLimitedAuditTrail();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void tagAudit() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        this.database = firebaseDatabase;
        firebaseDatabase.getReference("tagAuditTrail").child(this.serial).orderByChild("updateTime").limitToLast(100).addListenerForSingleValueEvent(new ValueEventListener() { // from class: com.egeetouch.egeetouch_manager.Activity_access_log_audit.3
            @Override // com.google.firebase.database.ValueEventListener
            public void onCancelled(DatabaseError databaseError) {
            }

            @Override // com.google.firebase.database.ValueEventListener
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists()) {
                    Activity_access_log_audit.this.retrieve_data_from_database();
                    if (Activity_access_log_audit.auditEmail.size() != 0) {
                        Activity_access_log_audit.this.updateArrayAdapterView();
                        return;
                    }
                    customProgressBar.closeDialog(10L);
                    Toast.makeText(MainActivity.context, (int) R.string.no_audittrial_history, 0).show();
                    return;
                }
                Activity_access_log_audit.number_of_Access = dataSnapshot.getChildrenCount();
                int unused = Activity_access_log_audit.currentAccessCountNumber = 0;
                for (DataSnapshot dataSnapshot2 : dataSnapshot.getChildren()) {
                    HashMap hashMap = (HashMap) dataSnapshot2.getValue();
                    String obj = hashMap.containsKey("tagId") ? hashMap.get("tagId").toString() : " N.A ";
                    String obj2 = hashMap.containsKey("tagName") ? hashMap.get("tagName").toString() : " N.A ";
                    long parseLong = hashMap.containsKey("auditTrailTime") ? Long.parseLong(hashMap.get("auditTrailTime").toString()) : 0L;
                    int parseInt = hashMap.containsKey("auditDeciValue") ? Integer.parseInt(hashMap.get("auditDeciValue").toString()) : 0;
                    long parseInt2 = hashMap.containsKey("lockBackTime") ? Integer.parseInt(hashMap.get("lockBackTime").toString()) : 0L;
                    Activity_access_log_audit.auditTime.add(Long.valueOf(parseLong + Activity_access_log_audit.currentAccessCountNumber));
                    Activity_access_log_audit.auditEmail.add(Activity_access_log_audit.this.getString(R.string.tag_id_audit) + obj + "  " + Activity_access_log_audit.this.getString(R.string.tag_name_audit) + obj2);
                    Activity_access_log_audit.auditlockStatus.add(Activity_access_log_audit.this.getString(R.string.unlocked));
                    Activity_access_log_audit.auditAddress.add(Activity_access_log_audit.this.getString(R.string.no_address_found));
                    Activity_access_log_audit.auditDeci_Value.add(Integer.valueOf(parseInt));
                    Activity_access_log_audit.auditLockBackTime.add(Long.valueOf(parseInt2));
                    Activity_access_log_audit.auditLongitude.add(Double.valueOf((double) FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE));
                    Activity_access_log_audit.auditLatitude.add(Double.valueOf((double) FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE));
                    Activity_access_log_audit.currentAccessCountNumber++;
                    if (Activity_access_log_audit.currentAccessCountNumber == Activity_access_log_audit.number_of_Access) {
                        Activity_access_log_audit.this.updateArrayAdapterView();
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void retrieve_data_from_database() {
        int i;
        long j = (!BLEService.selected_lock_model.equals("GT2100") || (!BLEService.selected_lock_version.equals("1.80") && ((double) Float.valueOf(BLEService.selected_lock_version).floatValue()) <= 1.8d)) ? 20L : BLEService.Total_Tag_Audit;
        Cursor rawQuery = DatabaseVariable.db_audittrail.rawQuery(DatabaseVariable.db_audittrail_lock2(BLEService.selected_lock_name), null);
        int i2 = -1;
        while (rawQuery.moveToNext()) {
            i2++;
            if (i2 <= j && (i = rawQuery.getInt(0)) > 0 && i <= j) {
                Log.i("TAG", "who: " + String.valueOf(rawQuery.getString(1)));
                if (rawQuery.getString(1) != null) {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm a", Locale.ENGLISH);
                    new Date();
                    try {
                        Date parse = simpleDateFormat.parse(rawQuery.getString(2));
                        auditTime.add(Long.valueOf(parse.getTime()));
                        auditLockBackTime.add(Long.valueOf(parse.getTime()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    auditEmail.add(rawQuery.getString(1));
                    auditlockStatus.add(getString(R.string.unlocked));
                    auditAddress.add(getString(R.string.no_address_found));
                    auditDeci_Value.add(0);
                    DecimalFormat decimalFormat = new DecimalFormat("#.######");
                    auditLongitude.add(Double.valueOf(decimalFormat.format(rawQuery.getDouble(3))));
                    auditLatitude.add(Double.valueOf(decimalFormat.format(rawQuery.getDouble(4))));
                }
            }
        }
        rawQuery.close();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void passcodeAudit() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        this.database = firebaseDatabase;
        firebaseDatabase.getReference("directionalPassCodeAuditTrail").child(this.serial).orderByChild("updateTime").limitToLast(100).addListenerForSingleValueEvent(new ValueEventListener() { // from class: com.egeetouch.egeetouch_manager.Activity_access_log_audit.4
            @Override // com.google.firebase.database.ValueEventListener
            public void onCancelled(DatabaseError databaseError) {
            }

            @Override // com.google.firebase.database.ValueEventListener
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Activity_access_log_audit.number_of_Access = dataSnapshot.getChildrenCount();
                    int unused = Activity_access_log_audit.currentAccessCountNumber = 0;
                    for (DataSnapshot dataSnapshot2 : dataSnapshot.getChildren()) {
                        HashMap hashMap = (HashMap) dataSnapshot2.getValue();
                        if (hashMap.containsKey("directionalPasscode")) {
                            hashMap.get("directionalPasscode").toString();
                        }
                        String obj = hashMap.containsKey("passcodeName") ? hashMap.get("passcodeName").toString() : " N.A ";
                        long parseLong = hashMap.containsKey("auditTrailTime") ? Long.parseLong(hashMap.get("auditTrailTime").toString()) : 0L;
                        int parseInt = hashMap.containsKey("auditDeciValue") ? Integer.parseInt(hashMap.get("auditDeciValue").toString()) : 0;
                        long parseInt2 = hashMap.containsKey("lockBackTime") ? Integer.parseInt(hashMap.get("lockBackTime").toString()) : 0L;
                        Activity_access_log_audit.auditTime.add(Long.valueOf(parseLong + Activity_access_log_audit.currentAccessCountNumber));
                        Activity_access_log_audit.auditEmail.add(Activity_access_log_audit.this.getString(R.string.passcode_name) + obj);
                        Activity_access_log_audit.auditlockStatus.add(Activity_access_log_audit.this.getString(R.string.unlocked));
                        Activity_access_log_audit.auditAddress.add(Activity_access_log_audit.this.getString(R.string.no_address_found));
                        Activity_access_log_audit.auditDeci_Value.add(Integer.valueOf(parseInt));
                        Activity_access_log_audit.auditLockBackTime.add(Long.valueOf(parseInt2));
                        Activity_access_log_audit.auditLongitude.add(Double.valueOf((double) FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE));
                        Activity_access_log_audit.auditLatitude.add(Double.valueOf((double) FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE));
                        Activity_access_log_audit.currentAccessCountNumber++;
                        System.out.println("Hello checking the Passcode Audit 2 ACTIVITY");
                        if (Activity_access_log_audit.currentAccessCountNumber == Activity_access_log_audit.number_of_Access) {
                            Activity_access_log_audit.this.updateArrayAdapterView();
                        }
                    }
                } else if (Activity_access_log_audit.auditEmail.size() != 0) {
                    Activity_access_log_audit.this.updateArrayAdapterView();
                } else {
                    customProgressBar.closeDialog(10L);
                    Toast.makeText(MainActivity.context, (int) R.string.no_audittrial_history, 0).show();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateArrayAdapterView() {
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
            this.emailList.add(auditEmail.get(size));
            size--;
        }
        int size2 = auditTime.size() - 1;
        for (int i2 = 1; i2 <= 20 && size2 >= 0; i2++) {
            this.timeList.add(auditTime.get(size2));
            size2--;
        }
        int size3 = auditAddress.size() - 1;
        for (int i3 = 1; i3 <= 20 && size3 >= 0; i3++) {
            this.addressList.add(auditAddress.get(size3));
            size3--;
        }
        int size4 = auditlockStatus.size() - 1;
        for (int i4 = 1; i4 <= 20 && size4 >= 0; i4++) {
            this.lockStatusList.add(auditlockStatus.get(size4));
            size4--;
        }
        int size5 = auditDeci_Value.size() - 1;
        for (int i5 = 1; i5 <= 20 && size5 >= 0; i5++) {
            this.auditDeciValueList.add(auditDeci_Value.get(size5));
            size5--;
        }
        int size6 = auditLockBackTime.size() - 1;
        for (int i6 = 1; i6 <= 20 && size6 >= 0; i6++) {
            this.lockBackTimeList.add(auditLockBackTime.get(size6));
            size6--;
        }
        int size7 = auditLongitude.size() - 1;
        for (int i7 = 1; i7 <= 20 && size7 >= 0; i7++) {
            this.longitudeList.add(auditLongitude.get(size7));
            size7--;
        }
        int size8 = auditLatitude.size() - 1;
        for (int i8 = 1; i8 <= 20 && size8 >= 0; i8++) {
            this.latitudeList.add(auditLatitude.get(size8));
            size8--;
        }
        Arrayadapter_access_log_audit arrayadapter_access_log_audit = new Arrayadapter_access_log_audit(this, this.emailList, this.timeList, this.addressList, this.lockStatusList, this.auditDeciValueList, this.lockBackTimeList, this.longitudeList, this.latitudeList);
        this.accesslogAuditAdapter = arrayadapter_access_log_audit;
        this.listview.setAdapter((ListAdapter) arrayadapter_access_log_audit);
        this.accesslogAuditAdapter.notifyDataSetChanged();
        customProgressBar.closeDialog(10L);
    }

    private void sendDatabaseToArrayAdapter() {
        int size = auditEmail.size() - 1;
        for (int i = 0; size >= 0 && i < 10; i++) {
            this.emailList.add(auditEmail.get(size));
            size--;
        }
        int size2 = auditTime.size() - 1;
        for (int i2 = 0; size2 >= 0 && i2 < 10; i2++) {
            this.timeList.add(auditTime.get(size2));
            size2--;
        }
        int size3 = auditAddress.size() - 1;
        for (int i3 = 0; size3 >= 0 && i3 < 10; i3++) {
            this.addressList.add(auditAddress.get(size3));
            size3--;
        }
        int size4 = auditlockStatus.size() - 1;
        for (int i4 = 0; size4 >= 0 && i4 < 10; i4++) {
            this.lockStatusList.add(auditlockStatus.get(size4));
            size4--;
        }
        auditlockStatus.clear();
        Arrayadapter_access_log_audit arrayadapter_access_log_audit = new Arrayadapter_access_log_audit(this, this.emailList, this.timeList, this.addressList, this.lockStatusList, this.auditDeciValueList, this.lockBackTimeList, this.longitudeList, this.latitudeList);
        this.accesslogAuditAdapter = arrayadapter_access_log_audit;
        this.listview.setAdapter((ListAdapter) arrayadapter_access_log_audit);
        this.accesslogAuditAdapter.notifyDataSetChanged();
        customProgressBar.closeDialog(100L);
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

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332) {
            finish();
            return true;
        }
        return true;
    }
}
