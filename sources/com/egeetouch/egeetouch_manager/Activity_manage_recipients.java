package com.egeetouch.egeetouch_manager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import cn.pedant.SweetAlert.SweetAlertDialog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
/* loaded from: classes.dex */
public class Activity_manage_recipients extends AppCompatActivity {
    ArrayAdapter_addshareUser adapter;
    private FirebaseDatabase database;
    ListView listview;
    private long recordsCounts;
    Toast toaster;
    private long totalNumberOfRecords;
    TextView tv_addFriend;
    String[] user_email_ex;
    String[] user_name_ex;
    View rootView = null;
    List<String> list_user = new ArrayList();
    List<String> list_email = new ArrayList();
    List<Boolean> list_exists = new ArrayList();
    int add_count = 1;
    private boolean recordFound = false;
    boolean hideMenu = false;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_manage_recipients);
        getWindow().setFlags(1024, 1024);
        getSupportActionBar().setTitle(R.string.share_user_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.database = FirebaseDatabase.getInstance();
        this.listview = (ListView) findViewById(R.id.listview_userlist);
        this.tv_addFriend = (TextView) findViewById(R.id.textView_add_user);
        System.out.println("Hello Activity Manage Recipient! onCreate()");
        recipientList();
        this.listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() { // from class: com.egeetouch.egeetouch_manager.Activity_manage_recipients.1
            @Override // android.widget.AdapterView.OnItemLongClickListener
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long j) {
                System.out.println("Hello userFriendListIndustrial array length:" + Firebase_Data_management.listRecipientName.size());
                final String str = Firebase_Data_management.listRecipientName.get(i);
                final String str2 = Firebase_Data_management.listRecipientEmail.get(i);
                final Boolean bool = Firebase_Data_management.listRecipientExists.get(i);
                new SweetAlertDialog(Activity_manage_recipients.this).setTitleText(Activity_manage_recipients.this.getResources().getString(R.string.delete_user)).setContentText(Activity_manage_recipients.this.getResources().getString(R.string.irreversible) + " " + Activity_manage_recipients.this.getResources().getString(R.string.are_you_sure_you_want_to_delete_jp) + " " + Firebase_Data_management.listRecipientEmail.get(i) + "?").setConfirmText(Activity_manage_recipients.this.getResources().getString(R.string.yes)).setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.Activity_manage_recipients.1.2
                    @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismissWithAnimation();
                        Activity_manage_recipients.this.list_user.remove(str);
                        Activity_manage_recipients.this.list_email.remove(str2);
                        Activity_manage_recipients.this.list_exists.remove(bool);
                        Activity_manage_recipients.this.adapter = new ArrayAdapter_addshareUser(Activity_manage_recipients.this, Activity_manage_recipients.this.list_user, Activity_manage_recipients.this.list_email, Activity_manage_recipients.this.list_exists);
                        Activity_manage_recipients.this.listview.setAdapter((ListAdapter) Activity_manage_recipients.this.adapter);
                        Activity_manage_recipients.this.adapter.notifyDataSetChanged();
                        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                        final Query equalTo = firebaseDatabase.getReference("userFriendList").child(MainActivity.user_uid).orderByValue().equalTo(str2);
                        equalTo.addListenerForSingleValueEvent(new ValueEventListener() { // from class: com.egeetouch.egeetouch_manager.Activity_manage_recipients.1.2.1
                            @Override // com.google.firebase.database.ValueEventListener
                            public void onCancelled(DatabaseError databaseError) {
                            }

                            @Override // com.google.firebase.database.ValueEventListener
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists()) {
                                    for (DataSnapshot dataSnapshot2 : dataSnapshot.getChildren()) {
                                        dataSnapshot2.getRef().removeValue();
                                    }
                                    return;
                                }
                                System.out.println("Hello checking the Recipient Deletion userFriendList else :" + equalTo.getRef());
                            }
                        });
                        firebaseDatabase.getReference("retractableSharingRegistry").child(MainActivity.user_uid).orderByChild("sharedToEmail").equalTo(str2).addListenerForSingleValueEvent(new ValueEventListener() { // from class: com.egeetouch.egeetouch_manager.Activity_manage_recipients.1.2.2
                            @Override // com.google.firebase.database.ValueEventListener
                            public void onCancelled(DatabaseError databaseError) {
                            }

                            @Override // com.google.firebase.database.ValueEventListener
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists()) {
                                    for (DataSnapshot dataSnapshot2 : dataSnapshot.getChildren()) {
                                        System.out.println("Hello checking the Recipient Deletion Retract if" + dataSnapshot2.getRef());
                                        dataSnapshot2.getRef().removeValue();
                                    }
                                    return;
                                }
                                System.out.println("Hello checking the Recipient Deletion Retract Els");
                            }
                        });
                        System.out.println("Hello check the posoition:" + i);
                    }
                }).setCancelText(Activity_manage_recipients.this.getResources().getString(R.string.no)).setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.Activity_manage_recipients.1.1
                    @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismissWithAnimation();
                    }
                }).show();
                return false;
            }
        });
        final Button button = (Button) findViewById(R.id.btn_less);
        button.setAlpha(0.5f);
        final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.add_user2);
        final LinearLayout linearLayout2 = (LinearLayout) findViewById(R.id.add_user3);
        final LinearLayout linearLayout3 = (LinearLayout) findViewById(R.id.add_user4);
        final LinearLayout linearLayout4 = (LinearLayout) findViewById(R.id.add_user5);
        ((Button) findViewById(R.id.btn_more)).setOnClickListener(new View.OnClickListener() { // from class: com.egeetouch.egeetouch_manager.Activity_manage_recipients.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (Activity_manage_recipients.this.list_user.size() + Activity_manage_recipients.this.add_count < 5) {
                    int i = Activity_manage_recipients.this.add_count;
                    if (i == 1) {
                        linearLayout.setVisibility(0);
                        Activity_manage_recipients.this.add_count++;
                        button.setAlpha(1.0f);
                        return;
                    } else if (i == 2) {
                        linearLayout2.setVisibility(0);
                        Activity_manage_recipients.this.add_count++;
                        button.setAlpha(1.0f);
                        return;
                    } else if (i == 3) {
                        linearLayout3.setVisibility(0);
                        Activity_manage_recipients.this.add_count++;
                        button.setAlpha(1.0f);
                        return;
                    } else if (i != 4) {
                        return;
                    } else {
                        linearLayout4.setVisibility(0);
                        Activity_manage_recipients.this.add_count++;
                        button.setAlpha(1.0f);
                        return;
                    }
                }
                Activity_manage_recipients activity_manage_recipients = Activity_manage_recipients.this;
                activity_manage_recipients.makeToast(activity_manage_recipients.getResources().getString(R.string.egee_pop_up_recipient_title));
            }
        });
        button.setOnClickListener(new View.OnClickListener() { // from class: com.egeetouch.egeetouch_manager.Activity_manage_recipients.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (Activity_manage_recipients.this.add_count > 1) {
                    int i = Activity_manage_recipients.this.add_count;
                    if (i == 2) {
                        linearLayout.setVisibility(8);
                        Activity_manage_recipients.this.add_count--;
                        button.setAlpha(0.5f);
                    } else if (i == 3) {
                        linearLayout2.setVisibility(8);
                        Activity_manage_recipients.this.add_count--;
                        button.setAlpha(1.0f);
                    } else if (i == 4) {
                        linearLayout3.setVisibility(8);
                        Activity_manage_recipients.this.add_count--;
                        button.setAlpha(1.0f);
                    } else if (i != 5) {
                    } else {
                        linearLayout4.setVisibility(8);
                        Activity_manage_recipients.this.add_count--;
                        button.setAlpha(1.0f);
                    }
                }
            }
        });
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.manage_recipient, menu);
        if (this.hideMenu) {
            menu.findItem(R.id.add_recipient).setVisible(false);
        } else {
            menu.findItem(R.id.add_recipient).setVisible(true);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332) {
            finish();
            return true;
        }
        btn_add_user_icon();
        return true;
    }

    public void recipientList() {
        System.out.println("Hello Activity Manage Recipient! recipientList()");
        this.list_user.clear();
        this.list_email.clear();
        this.list_exists.clear();
        for (int i = 0; i < Firebase_Data_management.listRecipientName.size(); i++) {
            this.list_user.add(Firebase_Data_management.listRecipientName.get(i));
        }
        for (int i2 = 0; i2 < Firebase_Data_management.listRecipientEmail.size(); i2++) {
            this.list_email.add(Firebase_Data_management.listRecipientEmail.get(i2));
        }
        for (int i3 = 0; i3 < Firebase_Data_management.listRecipientExists.size(); i3++) {
            this.list_exists.add(Firebase_Data_management.listRecipientExists.get(i3));
        }
        ArrayAdapter_addshareUser arrayAdapter_addshareUser = new ArrayAdapter_addshareUser(this, this.list_user, this.list_email, this.list_exists);
        this.adapter = arrayAdapter_addshareUser;
        this.listview.setAdapter((ListAdapter) arrayAdapter_addshareUser);
        this.adapter.notifyDataSetChanged();
    }

    public void btn_share_add_new_user(View view) {
        hideKeyboard(view);
        System.out.println("Hello Activity Manage Recipient! btn_add");
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
            if (isUserNameValid(editText.getText().toString())) {
                if (Helper_EmailValidation.isEmailValid(editText2.getText().toString().trim())) {
                    String trim = editText.getText().toString().trim();
                    String trim2 = editText2.getText().toString().trim();
                    this.database = FirebaseDatabase.getInstance();
                    if (!checkOtherUsers()) {
                        return;
                    }
                    try {
                        DatabaseReference child = this.database.getReference("userFriendList").child(MainActivity.user_uid);
                        child.child(trim).setValue(trim2);
                        int i = 0;
                        while (true) {
                            String[] strArr = this.user_name_ex;
                            if (i < strArr.length) {
                                child.child(strArr[i]).setValue(this.user_email_ex[i]);
                                i++;
                            } else {
                                recipientList();
                                finish();
                                ((ConstraintLayout) findViewById(R.id.LinearLayout_add_user)).setVisibility(8);
                                getSupportActionBar().setTitle(R.string.share_user_list);
                                this.hideMenu = false;
                                invalidateOptionsMenu();
                                editText.setText("");
                                editText2.setText("");
                                ((ConstraintLayout) findViewById(R.id.LinearLayout_add_user)).setVisibility(8);
                                getSupportActionBar().setTitle(R.string.share_user_list);
                                this.hideMenu = false;
                                invalidateOptionsMenu();
                                ListView listView = (ListView) findViewById(R.id.listview_userlist);
                                listView.setAlpha(1.0f);
                                listView.setClickable(true);
                                return;
                            }
                        }
                    } catch (Exception unused) {
                        editText.setError("User Name must not contain any special characters");
                    }
                } else {
                    editText2.setError(getString(R.string.please_enter_an_valid_email));
                }
            } else {
                editText.setError("User Name must not contain any special characters");
            }
        }
    }

    public void btn_add_user_icon() {
        if (this.list_user.size() >= 5) {
            CommercialAdvert_dialog.ShowDialog(this, "recipientLimit");
            makeToast(getResources().getString(R.string.egee_pop_up_recipient_title));
            return;
        }
        ((ConstraintLayout) findViewById(R.id.LinearLayout_add_user)).setVisibility(0);
        getSupportActionBar().setTitle(R.string.share_add_friend);
        this.hideMenu = true;
        invalidateOptionsMenu();
        ListView listView = (ListView) findViewById(R.id.listview_userlist);
        listView.setAlpha(0.2f);
        listView.setClickable(false);
        ((ImageView) findViewById(R.id.imageView_add_user)).setVisibility(4);
    }

    public void btn_cancel_add_user(View view) {
        hideKeyboard(view);
        ((ConstraintLayout) findViewById(R.id.LinearLayout_add_user)).setVisibility(8);
        getSupportActionBar().setTitle(R.string.share_user_list);
        this.hideMenu = false;
        invalidateOptionsMenu();
        ListView listView = (ListView) findViewById(R.id.listview_userlist);
        listView.setAlpha(1.0f);
        listView.setClickable(true);
    }

    public void hideKeyboard(View view) {
        ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private boolean isUserNameValid(String str) {
        return !Pattern.compile("[#.,$/\\[\\]]").matcher(str).find();
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

    /* JADX WARN: Removed duplicated region for block: B:28:0x00d1  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0181  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0231  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x02dc  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean checkOtherUsers() {
        boolean r0;
        int r0 = r12.add_count;
        r12.user_name_ex = new java.lang.String[r0 - 1];
        r12.user_email_ex = new java.lang.String[r0 - 1];
        if (r0 >= 2) {
            android.widget.EditText r0 = (android.widget.EditText) findViewById(com.egeetouch.egeetouch_manager.R.id.editText_user_name2);
            android.widget.EditText r9 = (android.widget.EditText) findViewById(com.egeetouch.egeetouch_manager.R.id.editText_user_email2);
            if (r0 != null && r9 != null) {
                if (r0.getText().toString().equals("")) {
                    r0.setError(getString(com.egeetouch.egeetouch_manager.R.string.please_enter_user_name));
                }
                if (r9.getText().toString().equals("")) {
                    r9.setError(getString(com.egeetouch.egeetouch_manager.R.string.please_enter_user_email));
                } else if (!r0.getText().toString().equals("") && !r9.getText().toString().equals("")) {
                    if (isUserNameValid(r0.getText().toString())) {
                        if (com.egeetouch.egeetouch_manager.Helper_EmailValidation.isEmailValid(r9.getText().toString().trim())) {
                            java.lang.String r0 = r0.getText().toString().trim();
                            java.lang.String r9 = r9.getText().toString().trim();
                            r12.user_name_ex[0] = r0;
                            r12.user_email_ex[0] = r9;
                        } else {
                            r9.setError(getString(com.egeetouch.egeetouch_manager.R.string.please_enter_an_valid_email));
                        }
                    } else {
                        r0.setError("User Name must not contain any special characters");
                    }
                }
            }
            r0 = false;
            if (r12.add_count >= 3) {
                android.widget.EditText r0 = (android.widget.EditText) findViewById(com.egeetouch.egeetouch_manager.R.id.editText_user_name3);
                android.widget.EditText r9 = (android.widget.EditText) findViewById(com.egeetouch.egeetouch_manager.R.id.editText_user_email3);
                if (r0 != null && r9 != null) {
                    if (r0.getText().toString().equals("")) {
                        r0.setError(getString(com.egeetouch.egeetouch_manager.R.string.please_enter_user_name));
                    }
                    if (r9.getText().toString().equals("")) {
                        r9.setError(getString(com.egeetouch.egeetouch_manager.R.string.please_enter_user_email));
                    } else if (!r0.getText().toString().equals("") && !r9.getText().toString().equals("")) {
                        if (isUserNameValid(r0.getText().toString())) {
                            if (com.egeetouch.egeetouch_manager.Helper_EmailValidation.isEmailValid(r9.getText().toString().trim())) {
                                java.lang.String r0 = r0.getText().toString().trim();
                                java.lang.String r9 = r9.getText().toString().trim();
                                r12.user_name_ex[1] = r0;
                                r12.user_email_ex[1] = r9;
                                r0 = true;
                            } else {
                                r9.setError(getString(com.egeetouch.egeetouch_manager.R.string.please_enter_an_valid_email));
                            }
                        } else {
                            r0.setError("User Name must not contain any special characters");
                        }
                    }
                }
                r0 = false;
            }
            if (r12.add_count >= 4) {
                android.widget.EditText r0 = (android.widget.EditText) findViewById(com.egeetouch.egeetouch_manager.R.id.editText_user_name4);
                android.widget.EditText r9 = (android.widget.EditText) findViewById(com.egeetouch.egeetouch_manager.R.id.editText_user_email4);
                if (r0 != null && r9 != null) {
                    if (r0.getText().toString().equals("")) {
                        r0.setError(getString(com.egeetouch.egeetouch_manager.R.string.please_enter_user_name));
                    }
                    if (r9.getText().toString().equals("")) {
                        r9.setError(getString(com.egeetouch.egeetouch_manager.R.string.please_enter_user_email));
                    } else if (!r0.getText().toString().equals("") && !r9.getText().toString().equals("")) {
                        if (isUserNameValid(r0.getText().toString())) {
                            if (com.egeetouch.egeetouch_manager.Helper_EmailValidation.isEmailValid(r9.getText().toString().trim())) {
                                java.lang.String r0 = r0.getText().toString().trim();
                                java.lang.String r9 = r9.getText().toString().trim();
                                r12.user_name_ex[2] = r0;
                                r12.user_email_ex[2] = r9;
                                r0 = true;
                            } else {
                                r9.setError(getString(com.egeetouch.egeetouch_manager.R.string.please_enter_an_valid_email));
                            }
                        } else {
                            r0.setError("User Name must not contain any special characters");
                        }
                    }
                }
                r0 = false;
            }
            if (r12.add_count < 5) {
                android.widget.EditText r0 = (android.widget.EditText) findViewById(com.egeetouch.egeetouch_manager.R.id.editText_user_name5);
                android.widget.EditText r1 = (android.widget.EditText) findViewById(com.egeetouch.egeetouch_manager.R.id.editText_user_email5);
                if (r0 == null || r1 == null) {
                    return false;
                }
                if (r0.getText().toString().equals("")) {
                    r0.setError(getString(com.egeetouch.egeetouch_manager.R.string.please_enter_user_name));
                }
                if (r1.getText().toString().equals("")) {
                    r1.setError(getString(com.egeetouch.egeetouch_manager.R.string.please_enter_user_email));
                    return false;
                } else if (r0.getText().toString().equals("") || r1.getText().toString().equals("")) {
                    return false;
                } else {
                    if (isUserNameValid(r0.getText().toString())) {
                        if (com.egeetouch.egeetouch_manager.Helper_EmailValidation.isEmailValid(r1.getText().toString().trim())) {
                            java.lang.String r0 = r0.getText().toString().trim();
                            java.lang.String r1 = r1.getText().toString().trim();
                            r12.user_name_ex[3] = r0;
                            r12.user_email_ex[3] = r1;
                            return true;
                        }
                        r1.setError(getString(com.egeetouch.egeetouch_manager.R.string.please_enter_an_valid_email));
                        return false;
                    }
                    r0.setError("User Name must not contain any special characters");
                    return false;
                }
            }
            return r0;
        }
        r0 = true;
        if (r12.add_count >= 3) {
        }
        if (r12.add_count >= 4) {
        }
        if (r12.add_count < 5) {
        }
    }
}
