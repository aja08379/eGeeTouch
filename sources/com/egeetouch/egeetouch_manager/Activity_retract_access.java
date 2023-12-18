package com.egeetouch.egeetouch_manager;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import cn.pedant.SweetAlert.SweetAlertDialog;
import com.facebook.internal.ServerProtocol;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/* loaded from: classes.dex */
public class Activity_retract_access extends AppCompatActivity {
    public static boolean change_state;
    public SweetAlertDialog access_change_dialog;
    Context context;
    private FirebaseDatabase database;
    ListView listView;
    public SweetAlertDialog loading_changes_dialog;
    public SweetAlertDialog loading_dialog;
    Arrayadapter_retract_access retract_adapter;
    public static ArrayList<String> lockName = new ArrayList<>();
    public static ArrayList<String> email_Id = new ArrayList<>();
    public static ArrayList<String> startTime = new ArrayList<>();
    public static ArrayList<String> endTime = new ArrayList<>();
    public static ArrayList<String> lockStatus = new ArrayList<>();
    public static String selected = "";
    public static boolean data_found = false;
    public static long number_of_access = 0;
    public static int currentAccessCountNumber = 0;
    View rootView = null;
    List<String> lockName_list = new ArrayList();
    List<String> email_list = new ArrayList();
    List<String> startTime_list = new ArrayList();
    List<String> endTime_list = new ArrayList();
    List<String> lockStatus_list = new ArrayList();
    List<String> shared_access_token_list = new ArrayList();
    List<String> access_token = new ArrayList();
    int br = 0;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_retract_access);
        getWindow().setFlags(1024, 1024);
        getSupportActionBar().setTitle(R.string.retract_access);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.listView = (ListView) findViewById(R.id.listView_retract_access);
        Toast.makeText(this, (int) R.string.retract_access, 0).show();
        if (Helper_Network.haveNetworkConnection(this)) {
            Retract_access_initialization();
        } else {
            Toast.makeText(this, "No internet Connection", 0).show();
        }
        this.listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() { // from class: com.egeetouch.egeetouch_manager.Activity_retract_access.1
            @Override // android.widget.AdapterView.OnItemLongClickListener
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
                System.out.println("Hello position:" + Activity_retract_access.this.access_token.get(i));
                Activity_retract_access.selected = Activity_retract_access.this.access_token.get(i);
                if (Activity_retract_access.this.lockStatus_list.get(i) == ServerProtocol.DIALOG_RETURN_SCOPES_TRUE) {
                    Activity_retract_access.change_state = false;
                } else if (Activity_retract_access.this.lockStatus_list.get(i) == "false") {
                    Activity_retract_access.change_state = true;
                }
                new SweetAlertDialog(Activity_retract_access.this).setTitleText(Activity_retract_access.this.getResources().getString(R.string.pls_confirm)).setContentText(Activity_retract_access.this.getResources().getString(R.string.change_access_permission) + "?").setConfirmText(Activity_retract_access.this.getResources().getString(R.string.yes)).setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.Activity_retract_access.1.2
                    @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismissWithAnimation();
                        Activity_retract_access.this.br = 0;
                        Activity_retract_access.data_found = false;
                        Activity_retract_access.this.loading_changes_dialog = new SweetAlertDialog(Activity_retract_access.this, 5);
                        Activity_retract_access.this.loading_changes_dialog.getProgressHelper().setBarColor(Color.parseColor("#389ae0"));
                        Activity_retract_access.this.loading_changes_dialog.setTitleText(Activity_retract_access.this.getString(R.string.please_wait));
                        customProgressBar.ShowProgressBar(Activity_retract_access.this, "pleaseWait");
                        Activity_retract_access.this.accessChange();
                    }
                }).setCancelText(Activity_retract_access.this.getResources().getString(R.string.no)).setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.Activity_retract_access.1.1
                    @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismissWithAnimation();
                    }
                }).show();
                return false;
            }
        });
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.information_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332) {
            System.out.println("ini back");
            finish();
            return true;
        }
        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(this);
        sweetAlertDialog.setTitleText(getString(R.string.info));
        sweetAlertDialog.setContentText(getString(R.string.retract_info));
        sweetAlertDialog.show();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Retract_access_initialization() {
        lockName.clear();
        email_Id.clear();
        startTime.clear();
        endTime.clear();
        lockStatus.clear();
        this.shared_access_token_list.clear();
        this.lockName_list.clear();
        this.email_list.clear();
        this.startTime_list.clear();
        this.endTime_list.clear();
        this.lockStatus_list.clear();
        this.access_token.clear();
        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(this, 5);
        this.loading_dialog = sweetAlertDialog;
        sweetAlertDialog.getProgressHelper().setBarColor(Color.parseColor("#389ae0"));
        this.loading_dialog.setTitleText(getString(R.string.loading));
        customProgressBar.ShowProgressBar(this, getString(R.string.loading));
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        this.database = firebaseDatabase;
        firebaseDatabase.getReference("retractableSharingRegistry").child(MainActivity.user_uid).addListenerForSingleValueEvent(new ValueEventListener() { // from class: com.egeetouch.egeetouch_manager.Activity_retract_access.2
            @Override // com.google.firebase.database.ValueEventListener
            public void onCancelled(DatabaseError databaseError) {
            }

            @Override // com.google.firebase.database.ValueEventListener
            public void onDataChange(DataSnapshot dataSnapshot) {
                Activity_retract_access.number_of_access = dataSnapshot.getChildrenCount();
                if (Activity_retract_access.number_of_access == 0) {
                    customProgressBar.closeDialog(0L);
                    Toast.makeText(Activity_retract_access.this, (int) R.string.no_audittrial_history, 0).show();
                    return;
                }
                System.out.println("HEY numberOfAccess " + Activity_retract_access.number_of_access);
                Activity_retract_access.currentAccessCountNumber = 0;
                System.out.println("HEY currentAccessCounterNumber is starting at  " + Activity_retract_access.currentAccessCountNumber);
                System.out.println("HEY currentAccessCounterNumber is at  " + Activity_retract_access.currentAccessCountNumber);
                for (DataSnapshot dataSnapshot2 : dataSnapshot.getChildren()) {
                    double d = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
                    Double.valueOf((double) FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
                    Double.valueOf((double) FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
                    HashMap hashMap = (HashMap) dataSnapshot2.getValue();
                    String obj = hashMap.containsKey("sharedLockName") ? hashMap.get("sharedLockName").toString() : " ";
                    String obj2 = hashMap.containsKey("allowAccess") ? hashMap.get("allowAccess").toString() : " ";
                    String obj3 = hashMap.containsKey("sharedToEmail") ? hashMap.get("sharedToEmail").toString() : " ";
                    String obj4 = hashMap.containsKey("shareAccessToken") ? hashMap.get("shareAccessToken").toString() : " ";
                    Double valueOf = Double.valueOf(hashMap.containsKey("shareStartTime") ? Double.valueOf(hashMap.get("shareStartTime").toString()).doubleValue() : 0.0d);
                    if (hashMap.containsKey("shareEndTime")) {
                        d = Double.valueOf(hashMap.get("shareEndTime").toString()).doubleValue();
                    }
                    Double valueOf2 = Double.valueOf(d);
                    String format = String.format("%.0f", valueOf);
                    String format2 = String.format("%.0f", valueOf2);
                    Activity_retract_access.lockName.add(Activity_retract_access.currentAccessCountNumber, obj);
                    Activity_retract_access.lockStatus.add(Activity_retract_access.currentAccessCountNumber, obj2);
                    Activity_retract_access.email_Id.add(Activity_retract_access.currentAccessCountNumber, obj3);
                    Activity_retract_access.endTime.add(Activity_retract_access.currentAccessCountNumber, format2);
                    Activity_retract_access.startTime.add(Activity_retract_access.currentAccessCountNumber, format);
                    Activity_retract_access.this.shared_access_token_list.add(Activity_retract_access.currentAccessCountNumber, obj4);
                    Activity_retract_access.currentAccessCountNumber++;
                }
                if (Activity_retract_access.number_of_access == Activity_retract_access.currentAccessCountNumber) {
                    for (int size = Activity_retract_access.lockName.size() - 1; size >= 0; size--) {
                        Activity_retract_access.this.lockName_list.add(Activity_retract_access.lockName.get(size));
                    }
                    for (int size2 = Activity_retract_access.email_Id.size() - 1; size2 >= 0; size2--) {
                        Activity_retract_access.this.email_list.add(Activity_retract_access.email_Id.get(size2));
                    }
                    for (int size3 = Activity_retract_access.startTime.size() - 1; size3 >= 0; size3--) {
                        Activity_retract_access.this.startTime_list.add(Activity_retract_access.startTime.get(size3));
                    }
                    for (int size4 = Activity_retract_access.endTime.size() - 1; size4 >= 0; size4--) {
                        Activity_retract_access.this.endTime_list.add(Activity_retract_access.endTime.get(size4));
                    }
                    for (int size5 = Activity_retract_access.lockStatus.size() - 1; size5 >= 0; size5--) {
                        Activity_retract_access.this.lockStatus_list.add(Activity_retract_access.lockStatus.get(size5));
                    }
                    for (int size6 = Activity_retract_access.this.shared_access_token_list.size() - 1; size6 >= 0; size6--) {
                        Activity_retract_access.this.access_token.add(Activity_retract_access.this.shared_access_token_list.get(size6));
                    }
                    Activity_retract_access activity_retract_access = Activity_retract_access.this;
                    Activity_retract_access activity_retract_access2 = Activity_retract_access.this;
                    activity_retract_access.retract_adapter = new Arrayadapter_retract_access(activity_retract_access2, activity_retract_access2.lockName_list, Activity_retract_access.this.email_list, Activity_retract_access.this.startTime_list, Activity_retract_access.this.endTime_list, Activity_retract_access.this.lockStatus_list);
                    Activity_retract_access.this.listView.setAdapter((ListAdapter) Activity_retract_access.this.retract_adapter);
                    Activity_retract_access.this.retract_adapter.notifyDataSetChanged();
                    customProgressBar.closeDialog(0L);
                }
            }
        });
    }

    public void accessChange() {
        System.out.println("Hello accessChange():");
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        this.database = firebaseDatabase;
        final DatabaseReference child = firebaseDatabase.getReference("retractableSharingRegistry").child(MainActivity.user_uid);
        child.orderByChild("shareAccessToken").equalTo(selected).addListenerForSingleValueEvent(new ValueEventListener() { // from class: com.egeetouch.egeetouch_manager.Activity_retract_access.3
            @Override // com.google.firebase.database.ValueEventListener
            public void onCancelled(DatabaseError databaseError) {
            }

            @Override // com.google.firebase.database.ValueEventListener
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot dataSnapshot2 : dataSnapshot.getChildren()) {
                        String key = dataSnapshot2.getKey();
                        HashMap hashMap = new HashMap();
                        hashMap.put("allowAccess", Boolean.valueOf(Activity_retract_access.change_state));
                        child.child(key).updateChildren(hashMap);
                        customProgressBar.closeDialog(0L);
                        Activity_retract_access.this.Retract_access_initialization();
                    }
                }
            }
        });
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        finish();
    }
}
