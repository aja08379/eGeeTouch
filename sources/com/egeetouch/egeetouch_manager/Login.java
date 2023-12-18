package com.egeetouch.egeetouch_manager;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import cn.pedant.SweetAlert.SweetAlertDialog;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class Login extends AppCompatActivity {
    private static String email;
    private static String password;
    CheckBox CB_rmbMe;
    TextView btn_fgt;
    Button btn_login;
    TextView btn_signup;
    Dialog dialog;
    JSONObject json;
    EditText login_email;
    EditText login_password;
    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    private SweetAlertDialog pleaseWaitDialog;
    boolean resend;
    Switch s;
    private SweetAlertDialog swDialog;

    private void login_firebase() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        System.out.println("HEY login page onCreate called");
        super.onCreate(bundle);
        setContentView(R.layout.testing_layout_for_login);
        getWindow().setFlags(1024, 1024);
        getSupportActionBar().setTitle(R.string.login);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.mAuth = FirebaseAuth.getInstance();
        getWindow().setSoftInputMode(3);
        ((LinearLayout) findViewById(R.id.LinearLayout_login_defocus_editbox)).setOnTouchListener(new View.OnTouchListener() { // from class: com.egeetouch.egeetouch_manager.Login.1
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 1) {
                    Login.this.hideKeyboard(view);
                    return true;
                }
                return false;
            }
        });
        this.login_email = (EditText) findViewById(R.id.editText_login_email);
        this.login_password = (EditText) findViewById(R.id.editText_login_password);
        this.btn_fgt = (TextView) findViewById(R.id.btn_fgt);
        this.btn_signup = (TextView) findViewById(R.id.btn_signup);
        this.btn_login = (Button) findViewById(R.id.btn_login);
        this.pleaseWaitDialog = new SweetAlertDialog(this, 5);
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        if (defaultSharedPreferences.getBoolean("rmbMe_login", false)) {
            this.login_email.setText(defaultSharedPreferences.getString("rmbMe_email", ""));
            this.login_password.setText(defaultSharedPreferences.getString("rmbMe_password", ""));
            ((Switch) findViewById(R.id.switch_rmb_login)).setChecked(true);
            return;
        }
        this.login_email.setText("");
        this.login_password.setText("");
        ((Switch) findViewById(R.id.switch_rmb_login)).setChecked(false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        getWindow().setSoftInputMode(3);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332) {
            finish();
            startActivity(new Intent(this, login_page_gmail.class));
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void btn_forgotpassword(View view) {
        startActivity(new Intent(this, ForgotPassword.class));
    }

    public void btn_CreateAccount(View view) {
        startActivity(new Intent(this, Signup.class));
        System.out.println("HEY login button pressed");
    }

    public void btn_login(View view) {
        this.pleaseWaitDialog = new SweetAlertDialog(this, 5);
        get_value_firebase(view);
    }

    public void btn_skip(View view) {
        login_page.current_logon_name = "";
        login_page.current_logon_email = "";
        startActivityForResult(new Intent(this, MainActivity.class), 3);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 3 && i2 == -1) {
            finish();
        }
    }

    private void get_value_firebase(View view) {
        hideKeyboard(view);
        if (!this.login_email.getText().toString().equals("")) {
            if (!this.login_password.getText().toString().equals("")) {
                password = this.login_password.getText().toString();
                email = this.login_email.getText().toString().trim();
                SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit();
                if (((Switch) findViewById(R.id.switch_rmb_login)).isChecked()) {
                    edit.putBoolean("rmbMe_login", true);
                } else {
                    edit.putBoolean("rmbMe_login", false);
                }
                edit.putString("rmbMe_email", email);
                edit.putString("rmbMe_password", password);
                edit.commit();
                if (Helper_Network.haveNetworkConnection(this)) {
                    SweetAlertDialog sweetAlertDialog = this.pleaseWaitDialog;
                    if (sweetAlertDialog != null && sweetAlertDialog.isShowing()) {
                        this.pleaseWaitDialog.dismissWithAnimation();
                    } else {
                        this.pleaseWaitDialog.setContentText(getString(R.string.pls_wait));
                        this.pleaseWaitDialog.getProgressHelper().setBarColor(Color.parseColor("#389ae0"));
                        customProgressBar.ShowProgressBar(this, FirebaseAnalytics.Event.LOGIN);
                    }
                    disabled_button();
                    this.mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new AnonymousClass2());
                    return;
                }
                SweetAlertDialog sweetAlertDialog2 = new SweetAlertDialog(this, 0);
                sweetAlertDialog2.setTitleText(getString(R.string.pls_note));
                sweetAlertDialog2.setContentText(getString(R.string.you_are_not_connected_access_lock));
                sweetAlertDialog2.show();
                enabled_button();
                return;
            }
            this.login_password.setError("Please enter the password !");
            enabled_button();
            return;
        }
        this.login_email.setError("Please enter the email !");
        enabled_button();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.egeetouch.egeetouch_manager.Login$2  reason: invalid class name */
    /* loaded from: classes.dex */
    public class AnonymousClass2 implements OnCompleteListener<AuthResult> {
        AnonymousClass2() {
        }

        @Override // com.google.android.gms.tasks.OnCompleteListener
        public void onComplete(Task<AuthResult> task) {
            if (Login.this.swDialog != null && Login.this.swDialog.isShowing()) {
                Login.this.swDialog.dismissWithAnimation();
            }
            Log.d("TAG", "signInWithEmail:onComplete:" + task.isSuccessful());
            if (task.isSuccessful()) {
                System.out.println("ID PASS CORRECT");
                FirebaseDatabase.getInstance().getReference("registeredUsersVerified").addValueEventListener(new AnonymousClass1());
                return;
            }
            Login.this.enabled_button();
            if (Login.this.pleaseWaitDialog != null && Login.this.pleaseWaitDialog.isShowing()) {
                Login.this.pleaseWaitDialog.dismissWithAnimation();
            }
            customProgressBar.closeDialog(0L);
            new SweetAlertDialog(Login.this).setTitleText(Login.this.getString(R.string.login)).setContentText(Login.this.getString(R.string.login_failed)).setConfirmText(Login.this.getString(R.string.dismiss)).setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.Login.2.2
                @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                public void onClick(SweetAlertDialog sweetAlertDialog) {
                    sweetAlertDialog.dismissWithAnimation();
                }
            }).show();
        }

        /* renamed from: com.egeetouch.egeetouch_manager.Login$2$1  reason: invalid class name */
        /* loaded from: classes.dex */
        class AnonymousClass1 implements ValueEventListener {
            @Override // com.google.firebase.database.ValueEventListener
            public void onCancelled(DatabaseError databaseError) {
            }

            AnonymousClass1() {
            }

            @Override // com.google.firebase.database.ValueEventListener
            public void onDataChange(DataSnapshot dataSnapshot) {
                System.out.println("GET DATE");
                if (dataSnapshot.child(Login.this.mAuth.getUid()).hasChild("verified")) {
                    System.out.println("NEW USER");
                    if (Login.this.mAuth.getCurrentUser().isEmailVerified()) {
                        System.out.println("IS VERIFIED");
                        FirebaseUser currentUser = Login.this.mAuth.getCurrentUser();
                        Login.this.mDatabase.getReference("registeredUsersOnPlatform").child(currentUser.getUid()).setValue(currentUser.getEmail());
                        if (Login.this.swDialog != null && Login.this.swDialog.isShowing()) {
                            Login.this.swDialog.dismissWithAnimation();
                        }
                        if (Login.this.pleaseWaitDialog != null && Login.this.pleaseWaitDialog.isShowing()) {
                            Login.this.pleaseWaitDialog.dismiss();
                        }
                        customProgressBar.closeDialog(0L);
                        if (currentUser != null) {
                            SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(Login.this.getApplicationContext()).edit();
                            edit.putBoolean("keep_access", true);
                            edit.putString("userID", currentUser.getUid());
                            edit.putString("rmbMe_email", currentUser.getEmail());
                            edit.putString("rmbMe_password", Login.password);
                            edit.putString("username", currentUser.getDisplayName());
                            edit.commit();
                            FirebaseDatabase.getInstance().getReference("userID").child(currentUser.getUid()).setValue(currentUser.getEmail());
                            Log.i("Tag", "user.getUid(): " + currentUser.getUid());
                            Log.i("Tag", "user.getEmail(): " + currentUser.getEmail());
                            System.out.println("HEY adding user to roster");
                            Login.this.mDatabase.getReference("registeredUsersVerified").child(currentUser.getUid()).child("verified").setValue(true);
                            SharedPreferences.Editor edit2 = Login.this.getApplicationContext().getSharedPreferences("RATER", 0).edit();
                            edit2.putBoolean("NO THANKS", false);
                            edit2.apply();
                            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                            Login.this.movefromTempDB(firebaseDatabase.getReference("Temp_DB").child(Helper_Firebase.EncodeString(currentUser.getEmail())), firebaseDatabase.getReference("Registered_user").child(currentUser.getUid()));
                        }
                        Login.this.startActivity(new Intent(Login.this, MainActivity.class));
                        Login.this.finish();
                        return;
                    }
                    System.out.println("PLEASE VERIFIED EMAIL");
                    customProgressBar.closeDialog(0L);
                    final SharedPreferences sharedPreferences = Login.this.getApplicationContext().getSharedPreferences("MyPref", 0);
                    final SharedPreferences.Editor edit3 = sharedPreferences.edit();
                    System.out.println("HEY ITS RESEND" + Login.this.resend);
                    try {
                        Login.this.dialog = new AlertDialog.Builder(Login.this).setTitle("Verify Your Email").setMessage("Please check your email and follow the instructions to verify your account, if you didn't receive an email for it, you can resend one.").setCancelable(false).setPositiveButton("Resend", (DialogInterface.OnClickListener) null).setNegativeButton("Close", new DialogInterface.OnClickListener() { // from class: com.egeetouch.egeetouch_manager.Login.2.1.1
                            @Override // android.content.DialogInterface.OnClickListener
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                                Login.this.enabled_button();
                            }
                        }).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    final Button button = ((AlertDialog) Login.this.dialog).getButton(-1);
                    button.setOnClickListener(new View.OnClickListener() { // from class: com.egeetouch.egeetouch_manager.Login.2.1.2
                        /* JADX WARN: Type inference failed for: r7v6, types: [com.egeetouch.egeetouch_manager.Login$2$1$2$1] */
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            Login.this.mAuth.getCurrentUser().sendEmailVerification();
                            new CountDownTimer(60000L, 100L) { // from class: com.egeetouch.egeetouch_manager.Login.2.1.2.1
                                @Override // android.os.CountDownTimer
                                public void onTick(long j) {
                                    button.setText(String.format(Locale.getDefault(), "( %d )", Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(j) + 1)));
                                    button.setAlpha(0.5f);
                                    button.setClickable(false);
                                    edit3.putBoolean("resend", true);
                                    edit3.commit();
                                    System.out.println("IS IS RESEND" + sharedPreferences.getBoolean("resend", false));
                                }

                                @Override // android.os.CountDownTimer
                                public void onFinish() {
                                    if (((AlertDialog) Login.this.dialog).isShowing()) {
                                        button.setText("Resend");
                                        button.setClickable(true);
                                        button.setAlpha(1.0f);
                                        edit3.putBoolean("resend", false);
                                        edit3.commit();
                                    }
                                }
                            }.start();
                            Toast.makeText(Login.this, "Resend, Please check your email!", 0).show();
                        }
                    });
                    return;
                }
                System.out.println("THIS IS OLD USER");
                FirebaseUser currentUser2 = Login.this.mAuth.getCurrentUser();
                Login.this.mDatabase.getReference("registeredUsersOnPlatform").child(currentUser2.getUid()).setValue(currentUser2.getEmail());
                if (Login.this.swDialog != null && Login.this.swDialog.isShowing()) {
                    Login.this.swDialog.dismissWithAnimation();
                }
                if (Login.this.pleaseWaitDialog != null && Login.this.pleaseWaitDialog.isShowing()) {
                    Login.this.pleaseWaitDialog.dismiss();
                }
                customProgressBar.closeDialog(0L);
                if (currentUser2 != null) {
                    SharedPreferences.Editor edit4 = PreferenceManager.getDefaultSharedPreferences(Login.this.getApplicationContext()).edit();
                    edit4.putBoolean("keep_access", true);
                    edit4.putString("userID", currentUser2.getUid());
                    edit4.putString("rmbMe_email", currentUser2.getEmail());
                    edit4.putString("rmbMe_password", Login.password);
                    edit4.putString("username", currentUser2.getDisplayName());
                    edit4.commit();
                    FirebaseDatabase.getInstance().getReference("userID").child(currentUser2.getUid()).setValue(currentUser2.getEmail());
                    Log.i("Tag", "user.getUid(): " + currentUser2.getUid());
                    Log.i("Tag", "user.getEmail(): " + currentUser2.getEmail());
                    System.out.println("HEY adding user to roster");
                    SharedPreferences.Editor edit5 = Login.this.getApplicationContext().getSharedPreferences("RATER", 0).edit();
                    edit5.putBoolean("NO THANKS", false);
                    edit5.apply();
                    FirebaseDatabase firebaseDatabase2 = FirebaseDatabase.getInstance();
                    Login.this.movefromTempDB(firebaseDatabase2.getReference("Temp_DB").child(Helper_Firebase.EncodeString(currentUser2.getEmail())), firebaseDatabase2.getReference("Registered_user").child(currentUser2.getUid()));
                }
                Login.this.startActivity(new Intent(Login.this, MainActivity.class));
                Login.this.finish();
            }
        }
    }

    private void get_value(View view) {
        hideKeyboard(view);
        if (!this.login_email.getText().toString().equals("")) {
            if (Helper_EmailValidation.isEmailValid(this.login_email.getText().toString())) {
                email = this.login_email.getText().toString();
                if (!this.login_password.getText().toString().equals("")) {
                    password = this.login_password.getText().toString();
                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(this, 5);
                    this.swDialog = sweetAlertDialog;
                    sweetAlertDialog.setTitleText(getString(R.string.logging_in));
                    this.swDialog.setCancelable(false);
                    this.swDialog.setCancelText(getString(R.string.cancel));
                    this.swDialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.Login.3
                        @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                        public void onClick(SweetAlertDialog sweetAlertDialog2) {
                            sweetAlertDialog2.dismissWithAnimation();
                        }
                    });
                    customProgressBar.ShowProgressBar(this, FirebaseAnalytics.Event.LOGIN);
                    SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit();
                    if (((Switch) findViewById(R.id.switch_rmb_login)).isChecked()) {
                        edit.putBoolean("rmbMe_login", true);
                    } else {
                        edit.putBoolean("rmbMe_login", false);
                    }
                    edit.putString("rmbMe_email", email);
                    edit.putString("rmbMe_password", password);
                    edit.commit();
                    if (Helper_Network.haveNetworkConnection(this)) {
                        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                        this.mAuth = firebaseAuth;
                        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new AnonymousClass4());
                        return;
                    }
                    show_snackbar(getString(R.string.internet_is_not_available));
                    finish();
                    return;
                }
                this.login_password.setError(getString(R.string.please_enter_password));
                return;
            }
            this.login_email.setError(getString(R.string.please_enter_an_valid_email));
            return;
        }
        this.login_email.setError(getString(R.string.please_enter_your_email));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.egeetouch.egeetouch_manager.Login$4  reason: invalid class name */
    /* loaded from: classes.dex */
    public class AnonymousClass4 implements OnCompleteListener<AuthResult> {
        AnonymousClass4() {
        }

        @Override // com.google.android.gms.tasks.OnCompleteListener
        public void onComplete(Task<AuthResult> task) {
            if (task.isSuccessful()) {
                if (Login.this.swDialog != null && Login.this.swDialog.isShowing()) {
                    Login.this.swDialog.dismissWithAnimation();
                }
                customProgressBar.closeDialog(0L);
                FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                if (currentUser != null) {
                    SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(Login.this.getApplicationContext()).edit();
                    edit.putBoolean("keep_access", true);
                    edit.putString("userID", currentUser.getUid());
                    edit.putString("rmbMe_email", currentUser.getEmail());
                    edit.putString("rmbMe_password", Login.password);
                    edit.putString("username", currentUser.getDisplayName());
                    edit.commit();
                }
                Login.this.setResult(32, new Intent());
                Login.this.finish();
                return;
            }
            Volley.newRequestQueue(Login.this).add(new StringRequest(1, "https://www.egeetouch.com/process.php", new AnonymousClass1(), new Response.ErrorListener() { // from class: com.egeetouch.egeetouch_manager.Login.4.2
                @Override // com.android.volley.Response.ErrorListener
                public void onErrorResponse(VolleyError volleyError) {
                    if (Login.this.swDialog != null && Login.this.swDialog.isShowing()) {
                        Login.this.swDialog.dismissWithAnimation();
                    }
                    customProgressBar.closeDialog(0L);
                    Login.this.show_snackbar(volleyError.toString());
                }
            }) { // from class: com.egeetouch.egeetouch_manager.Login.4.3
                @Override // com.android.volley.Request
                protected Map<String, String> getParams() {
                    HashMap hashMap = new HashMap();
                    hashMap.put("command", FirebaseAnalytics.Event.LOGIN);
                    hashMap.put("email", Login.email);
                    hashMap.put("password", Login.password);
                    return hashMap;
                }
            });
        }

        /* renamed from: com.egeetouch.egeetouch_manager.Login$4$1  reason: invalid class name */
        /* loaded from: classes.dex */
        class AnonymousClass1 implements Response.Listener<String> {
            AnonymousClass1() {
            }

            @Override // com.android.volley.Response.Listener
            public void onResponse(String str) {
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    if (jSONObject.getString("success") == null) {
                        if (Login.this.swDialog != null && Login.this.swDialog.isShowing()) {
                            Login.this.swDialog.dismissWithAnimation();
                        }
                        customProgressBar.closeDialog(0L);
                        Login.this.show_snackbar(Login.this.getString(R.string.server_is_unreachable));
                    } else if (Integer.parseInt(jSONObject.getString("success")) != 1) {
                        if (Login.this.swDialog != null && Login.this.swDialog.isShowing()) {
                            Login.this.swDialog.dismissWithAnimation();
                        }
                        customProgressBar.closeDialog(0L);
                        new SweetAlertDialog(Login.this).setTitleText(Login.this.getString(R.string.login)).setContentText(Login.this.getString(R.string.login_failed)).setConfirmText(Login.this.getString(R.string.dismiss)).setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.Login.4.1.2
                            @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                sweetAlertDialog.dismissWithAnimation();
                                Login.this.setResult(-1, new Intent());
                                Login.this.finish();
                            }
                        }).show();
                    } else {
                        Login.this.mAuth.createUserWithEmailAndPassword(Login.email, Login.password).addOnCompleteListener(Login.this, new C00431());
                    }
                } catch (Exception e) {
                    if (Login.this.swDialog != null && Login.this.swDialog.isShowing()) {
                        Login.this.swDialog.dismissWithAnimation();
                    }
                    customProgressBar.closeDialog(0L);
                    Login.this.show_snackbar(e.toString());
                }
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            /* renamed from: com.egeetouch.egeetouch_manager.Login$4$1$1  reason: invalid class name and collision with other inner class name */
            /* loaded from: classes.dex */
            public class C00431 implements OnCompleteListener<AuthResult> {
                C00431() {
                }

                @Override // com.google.android.gms.tasks.OnCompleteListener
                public void onComplete(Task<AuthResult> task) {
                    if (Login.this.swDialog != null && Login.this.swDialog.isShowing()) {
                        Login.this.swDialog.dismissWithAnimation();
                    }
                    if (!task.isSuccessful()) {
                        if (Login.this.swDialog != null && Login.this.swDialog.isShowing()) {
                            Login.this.swDialog.dismissWithAnimation();
                        }
                        new SweetAlertDialog(Login.this).setTitleText(Login.this.getString(R.string.login)).setContentText(Login.this.getString(R.string.login_failed)).setConfirmText(Login.this.getString(R.string.dismiss)).setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.Login.4.1.1.1
                            @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                sweetAlertDialog.dismissWithAnimation();
                                Login.this.setResult(-1, new Intent());
                                Login.this.finish();
                            }
                        }).show();
                        return;
                    }
                    Login.this.mAuth.signInWithEmailAndPassword(Login.email, Login.password).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() { // from class: com.egeetouch.egeetouch_manager.Login.4.1.1.2
                        @Override // com.google.android.gms.tasks.OnCompleteListener
                        public void onComplete(Task<AuthResult> task2) {
                            if (Login.this.swDialog != null && Login.this.swDialog.isShowing()) {
                                Login.this.swDialog.dismissWithAnimation();
                            }
                            Log.d("TAG", "signInWithEmail:onComplete:" + task2.isSuccessful());
                            if (task2.isSuccessful()) {
                                if (Login.this.swDialog != null && Login.this.swDialog.isShowing()) {
                                    Login.this.swDialog.dismissWithAnimation();
                                }
                                customProgressBar.closeDialog(0L);
                                FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                                if (currentUser != null) {
                                    SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(Login.this.getApplicationContext()).edit();
                                    edit.putBoolean("keep_access", true);
                                    edit.putString("userID", currentUser.getUid());
                                    edit.putString("rmbMe_email", currentUser.getEmail());
                                    edit.putString("rmbMe_password", Login.password);
                                    edit.putString("username", currentUser.getDisplayName());
                                    edit.commit();
                                    FirebaseDatabase.getInstance().getReference("userID").child(currentUser.getUid()).setValue(currentUser.getEmail());
                                    Log.i("Tag", "user.getUid(): " + currentUser.getUid());
                                    Log.i("Tag", "user.getEmail(): " + currentUser.getEmail());
                                    System.out.println("HEY adding user to roster");
                                    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                                    Login.this.movefromTempDB(firebaseDatabase.getReference("Temp_DB").child(Helper_Firebase.EncodeString(currentUser.getEmail())), firebaseDatabase.getReference("Registered_user").child(currentUser.getUid()));
                                }
                                Login.this.setResult(32, new Intent());
                                Login.this.finish();
                                return;
                            }
                            new SweetAlertDialog(Login.this).setTitleText(Login.this.getString(R.string.login)).setContentText(Login.this.getString(R.string.login_failed)).setConfirmText(Login.this.getString(R.string.dismiss)).setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.Login.4.1.1.2.1
                                @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    sweetAlertDialog.dismissWithAnimation();
                                    Login.this.setResult(-1, new Intent());
                                    Login.this.finish();
                                }
                            }).show();
                        }
                    });
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void show_snackbar(String str) {
        Snackbar make = Snackbar.make(findViewById(R.id.coordinatorLayout), str, 0);
        make.getView().setBackgroundColor(Color.parseColor("#D0389AE0"));
        make.setActionTextColor(Color.parseColor("#FFFFEE19"));
        make.show();
    }

    public void hideKeyboard(View view) {
        ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void movefromTempDB(final DatabaseReference databaseReference, final DatabaseReference databaseReference2) {
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() { // from class: com.egeetouch.egeetouch_manager.Login.5
            @Override // com.google.firebase.database.ValueEventListener
            public void onCancelled(DatabaseError databaseError) {
            }

            @Override // com.google.firebase.database.ValueEventListener
            public void onDataChange(DataSnapshot dataSnapshot) {
                databaseReference2.setValue(dataSnapshot.getValue(), new DatabaseReference.CompletionListener() { // from class: com.egeetouch.egeetouch_manager.Login.5.1
                    @Override // com.google.firebase.database.DatabaseReference.CompletionListener
                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference3) {
                        if (databaseError != null) {
                            Log.i("Tag", "Copy failed");
                            return;
                        }
                        Log.i("Tag", "Copy Done");
                        databaseReference.setValue(null);
                    }
                });
            }
        });
    }

    public void enabled_button() {
        this.login_email.setEnabled(true);
        this.login_password.setEnabled(true);
        this.btn_fgt.setEnabled(true);
        this.btn_login.setEnabled(true);
        this.btn_signup.setEnabled(true);
    }

    public void disabled_button() {
        this.login_email.setEnabled(false);
        this.login_password.setEnabled(false);
        this.btn_fgt.setEnabled(false);
        this.btn_login.setEnabled(false);
        this.btn_signup.setEnabled(false);
    }
}
