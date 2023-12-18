package com.egeetouch.egeetouch_manager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import cn.pedant.SweetAlert.SweetAlertDialog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public class Activity_access_log_lock_list extends AppCompatActivity {
    Context accessLogContext;
    ArrayAdapter_accesslog accesslogAdapter;
    ListView listview;
    public SweetAlertDialog loading_dialog;
    View rootView = null;
    List<String> accessLockserialList = new ArrayList();
    List<String> accessLockModelNum = new ArrayList();
    List<String> accessLockName = new ArrayList();

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_access_log_lock_list);
        getWindow().setFlags(1024, 1024);
        getSupportActionBar().setTitle(R.string.access_locks);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.accessLogContext = this;
        this.listview = (ListView) findViewById(R.id.access_log_lock_serial_listview);
        if (Helper_Network.haveNetworkConnection(this)) {
            if (MainActivity.myadminLockSerialNum.size() > 0) {
                display_serial_list();
                return;
            }
            SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(this, 0);
            sweetAlertDialog.setTitleText(getString(R.string.pls_note));
            sweetAlertDialog.setContentText(getString(R.string.no_locks_on_firebase));
            sweetAlertDialog.show();
            return;
        }
        Toast.makeText(this, (int) R.string.you_are_not_connected_access_lock, 1).show();
    }

    private void display_serial_list() {
        for (int i = 0; i < MainActivity.myadminLockSerialNum.size(); i++) {
            if (!this.accessLockserialList.contains(MainActivity.myadminLockSerialNum.get(i))) {
                this.accessLockserialList.add(MainActivity.myadminLockSerialNum.get(i));
                this.accessLockModelNum.add(MainActivity.myadminLockModelNum.get(i));
                this.accessLockName.add(MainActivity.myadminLockName.get(i));
            }
        }
        ArrayAdapter_accesslog arrayAdapter_accesslog = new ArrayAdapter_accesslog(this, this.accessLockserialList, this.accessLockModelNum, this.accessLockName);
        this.accesslogAdapter = arrayAdapter_accesslog;
        this.listview.setAdapter((ListAdapter) arrayAdapter_accesslog);
        this.accesslogAdapter.notifyDataSetChanged();
        this.listview.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.egeetouch.egeetouch_manager.Activity_access_log_lock_list.1
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i2, long j) {
                Activity_access_log_lock_list.this.loading_dialog = new SweetAlertDialog(Activity_access_log_lock_list.this, 5);
                Activity_access_log_lock_list.this.loading_dialog.setTitleText(Activity_access_log_lock_list.this.getString(R.string.loading));
                Activity_access_log_lock_list activity_access_log_lock_list = Activity_access_log_lock_list.this;
                customProgressBar.ShowProgressBar(activity_access_log_lock_list, activity_access_log_lock_list.getString(R.string.loading));
                String str = Activity_access_log_lock_list.this.accessLockModelNum.get(i2);
                System.out.println("Hello checking the lockModel number :" + str);
                Intent intent = new Intent(Activity_access_log_lock_list.this, Activity_access_log_audit.class);
                intent.putExtra("lockserial", Activity_access_log_lock_list.this.accessLockserialList.get(i2));
                intent.putExtra("lockModel", str);
                Activity_access_log_lock_list.this.startActivity(intent);
                customProgressBar.closeDialog(0L);
            }
        });
        this.listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() { // from class: com.egeetouch.egeetouch_manager.Activity_access_log_lock_list.2
            @Override // android.widget.AdapterView.OnItemLongClickListener
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i2, long j) {
                final String str = Activity_access_log_lock_list.this.accessLockserialList.get(i2);
                new SweetAlertDialog(Activity_access_log_lock_list.this.accessLogContext).setTitleText(Activity_access_log_lock_list.this.accessLogContext.getResources().getString(R.string.delete_lock)).setContentText(Activity_access_log_lock_list.this.accessLogContext.getResources().getString(R.string.are_you_sure_you_want_to_delete_jp) + "?").setConfirmText(Activity_access_log_lock_list.this.accessLogContext.getResources().getString(R.string.yes)).setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.Activity_access_log_lock_list.2.2
                    @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismissWithAnimation();
                        FirebaseDatabase.getInstance().getReference("MyAdminLocks").child(MainActivity.user_uid).orderByChild("ip45SerialNumber").equalTo(str).addListenerForSingleValueEvent(new ValueEventListener() { // from class: com.egeetouch.egeetouch_manager.Activity_access_log_lock_list.2.2.1
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
                        Activity_access_log_lock_list.this.accessLockserialList.remove(i2);
                        Activity_access_log_lock_list.this.accesslogAdapter.notifyDataSetChanged();
                    }
                }).setCancelText(Activity_access_log_lock_list.this.accessLogContext.getResources().getString(R.string.no)).setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.Activity_access_log_lock_list.2.1
                    @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismissWithAnimation();
                    }
                }).show();
                return true;
            }
        });
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
