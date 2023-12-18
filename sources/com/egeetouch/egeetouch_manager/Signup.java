package com.egeetouch.egeetouch_manager;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import cn.pedant.SweetAlert.SweetAlertDialog;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class Signup extends AppCompatActivity {
    public static String email;
    public static String firstname;
    public static String lastname;
    public static String password;
    CheckBox checkbox_agreement;
    CheckBox checkbox_subscription;
    private FirebaseAuth mAuth;
    private ProgressDialog pDialog;
    EditText signup_email;
    EditText signup_first_name;
    EditText signup_last_name;
    EditText signup_name;
    EditText signup_password1;
    EditText signup_password2;
    Boolean subcription = true;
    Boolean agreement = true;
    private FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();

    private void get_value_firebase(View view) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.signup_test);
        getWindow().setFlags(1024, 1024);
        getSupportActionBar().setTitle(R.string.sign_up);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.signup_first_name = (EditText) findViewById(R.id.editText_first_name);
        this.signup_last_name = (EditText) findViewById(R.id.editText_last_name);
        this.signup_email = (EditText) findViewById(R.id.editText_email);
        this.signup_password1 = (EditText) findViewById(R.id.editText_password1);
        this.signup_password2 = (EditText) findViewById(R.id.editText_password2);
        getWindow().setSoftInputMode(3);
        ((LinearLayout) findViewById(R.id.LinearLayout_signup_defocus_editbox)).setOnTouchListener(new View.OnTouchListener() { // from class: com.egeetouch.egeetouch_manager.Signup.1
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 1) {
                    Signup.this.hideKeyboard(view);
                    return true;
                }
                return false;
            }
        });
        CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox_subscription);
        this.checkbox_subscription = checkBox;
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.egeetouch.egeetouch_manager.Signup.2
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    Signup.this.subcription = true;
                } else {
                    Signup.this.subcription = false;
                }
            }
        });
        this.mAuth = FirebaseAuth.getInstance();
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
        try {
            ProgressDialog progressDialog = this.pDialog;
            if (progressDialog != null && progressDialog.isShowing()) {
                this.pDialog.dismiss();
            }
        } catch (Exception e) {
            new SweetAlertDialog(MainActivity.context, 3).setTitleText(MainActivity.context.getResources().getString(R.string.sign_up2)).setContentText(e.toString()).setConfirmText(MainActivity.context.getResources().getString(R.string.ok)).show();
            e.printStackTrace();
        }
        this.pDialog = null;
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
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void btn_haveAccount(View view) {
        setResult(-1, new Intent());
        finish();
    }

    public void btn_forgotpassword(View view) {
        startActivity(new Intent(this, ForgotPassword.class));
    }

    public void btn_createAccount(View view) {
        System.out.println("HEY you are signing up with firebase");
        if (!this.signup_email.getText().toString().equals("") && !this.signup_password1.getText().toString().equals("") && !this.signup_password2.getText().toString().equals("")) {
            System.out.println("HEY");
            System.out.println(this.signup_password1.getText().toString() + this.signup_password2.getText().toString());
            if (this.signup_password1.getText().toString().equals(this.signup_password2.getText().toString())) {
                if (Helper_EmailValidation.isEmailValid(this.signup_email.getText().toString().trim())) {
                    System.out.println("HEY you are ready to sign up");
                    this.mAuth.createUserWithEmailAndPassword(this.signup_email.getText().toString().trim(), this.signup_password1.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() { // from class: com.egeetouch.egeetouch_manager.Signup.3
                        @Override // com.google.android.gms.tasks.OnCompleteListener
                        public void onComplete(Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Signup.this.mAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() { // from class: com.egeetouch.egeetouch_manager.Signup.3.1
                                    @Override // com.google.android.gms.tasks.OnCompleteListener
                                    public void onComplete(Task<Void> task2) {
                                        if (task2.isSuccessful()) {
                                            System.out.println("HEY managed to sign up user");
                                            FirebaseUser currentUser = Signup.this.mAuth.getCurrentUser();
                                            System.out.println("HEY this is new user " + currentUser.getUid());
                                            Signup.this.mDatabase.getReference("registeredUsersOnPlatform").child(currentUser.getUid()).setValue(currentUser.getEmail());
                                            Signup.this.mDatabase.getReference("registeredUsersVerified").child(currentUser.getUid()).child("verified").setValue(false);
                                            Toast.makeText(Signup.this.getApplicationContext(), Signup.this.getResources().getString(R.string.signup_success), 1).show();
                                            Signup.this.finish();
                                            SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(Signup.this.getApplicationContext()).edit();
                                            edit.putString("rmbMe_email", Signup.this.signup_email.getText().toString().trim());
                                            edit.putString("rmbMe_password", Signup.this.signup_password1.getText().toString());
                                            edit.commit();
                                            return;
                                        }
                                        Toast.makeText(Signup.this, task2.getException().getMessage(), 0).show();
                                    }
                                });
                                return;
                            }
                            System.out.println("HEY didn't manage to sign up user");
                            Toast.makeText(Signup.this.getApplicationContext(), task.getException().getLocalizedMessage(), 0).show();
                        }
                    });
                    return;
                }
                this.signup_email.setError(getString(R.string.please_enter_an_valid_email));
                return;
            }
            this.signup_password2.setError(getString(R.string.passwords_dont_match));
            return;
        }
        Toast.makeText(getApplicationContext(), (int) R.string.please_fill_all_fields, 1).show();
    }

    public void btn_policy(View view) {
        String str = Locale.getDefault().getLanguage().equals("ja") ? "https://www.egeetouch.com/jp/privacy-policy" : "https://www.egeetouch.com/privacy-policy";
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(str));
        startActivity(intent);
    }

    public void btn_terms(View view) {
        String str = Locale.getDefault().getLanguage().equals("ja") ? "https://www.egeetouch.com/jp/terms-conditions" : "https://www.egeetouch.com/terms-conditions";
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(str));
        startActivity(intent);
    }

    public void btn_skip(View view) {
        login_page.current_logon_name = "";
        login_page.current_logon_email = "";
        startActivityForResult(new Intent(this, MainActivity.class), 3);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 3 && i2 == -1) {
            finish();
        }
    }

    private void get_value(View view) {
        System.out.println("HEY sign up button pressed");
        hideKeyboard(view);
        if (!this.signup_first_name.getText().toString().equals("")) {
            firstname = this.signup_first_name.getText().toString();
            if (!this.signup_last_name.getText().toString().equals("")) {
                lastname = this.signup_last_name.getText().toString();
                if (!this.signup_email.getText().toString().equals("")) {
                    if (Helper_EmailValidation.isEmailValid(this.signup_email.getText().toString())) {
                        email = this.signup_email.getText().toString();
                        if (!this.signup_password1.getText().toString().equals("")) {
                            if (!this.signup_password2.getText().toString().equals("")) {
                                if (this.signup_password1.getText().toString().equals(this.signup_password2.getText().toString())) {
                                    if (this.signup_password1.getText().toString().length() >= 6) {
                                        if (Helper_EmailValidation.validate_password(this.signup_password1.getText().toString())) {
                                            if (this.agreement.booleanValue()) {
                                                password = this.signup_password1.getText().toString();
                                                if (Helper_Network.haveNetworkConnection(this)) {
                                                    ProgressDialog progressDialog = new ProgressDialog(this);
                                                    this.pDialog = progressDialog;
                                                    progressDialog.setMessage(getString(R.string.registering));
                                                    this.pDialog.setIndeterminate(false);
                                                    this.pDialog.setCancelable(false);
                                                    this.pDialog.setButton(-2, getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() { // from class: com.egeetouch.egeetouch_manager.Signup.4
                                                        @Override // android.content.DialogInterface.OnClickListener
                                                        public void onClick(DialogInterface dialogInterface, int i) {
                                                            if (Signup.this.pDialog == null || !Signup.this.pDialog.isShowing()) {
                                                                return;
                                                            }
                                                            Signup.this.pDialog.dismiss();
                                                        }
                                                    });
                                                    this.pDialog.show();
                                                    Volley.newRequestQueue(this).add(new StringRequest(1, "https://www.egeetouch.com/process.php", new Response.Listener<String>() { // from class: com.egeetouch.egeetouch_manager.Signup.5
                                                        @Override // com.android.volley.Response.Listener
                                                        public void onResponse(String str) {
                                                            try {
                                                                JSONObject jSONObject = new JSONObject(str);
                                                                if (jSONObject.getString("success") != null) {
                                                                    try {
                                                                        if (Signup.this.pDialog != null && Signup.this.pDialog.isShowing()) {
                                                                            Signup.this.pDialog.dismiss();
                                                                        }
                                                                    } catch (Exception e) {
                                                                        Signup.this.show_snackbar(e.toString());
                                                                        e.printStackTrace();
                                                                    }
                                                                    try {
                                                                        if (jSONObject.getString("success") == null) {
                                                                            try {
                                                                                if (Signup.this.pDialog != null && Signup.this.pDialog.isShowing()) {
                                                                                    Signup.this.pDialog.dismiss();
                                                                                }
                                                                            } catch (Exception e2) {
                                                                                Signup.this.show_snackbar(e2.toString());
                                                                                e2.printStackTrace();
                                                                            }
                                                                            Signup signup = Signup.this;
                                                                            signup.show_snackbar(signup.getString(R.string.server_is_unreachable));
                                                                            return;
                                                                        }
                                                                        String string = jSONObject.getString("success");
                                                                        String string2 = jSONObject.getString("error");
                                                                        if (Integer.parseInt(string) == 1) {
                                                                            new SweetAlertDialog(Signup.this).setTitleText(Signup.this.getString(R.string.sign_up)).setContentText(Signup.this.getString(R.string.registration_done_please_login)).setConfirmText(Signup.this.getString(R.string.dismiss)).setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.Signup.5.1
                                                                                @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                                                                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                                                                    sweetAlertDialog.dismissWithAnimation();
                                                                                    Signup.this.setResult(-1, new Intent());
                                                                                    Signup.this.finish();
                                                                                }
                                                                            }).show();
                                                                            try {
                                                                                if (Signup.this.pDialog == null || !Signup.this.pDialog.isShowing()) {
                                                                                    return;
                                                                                }
                                                                                Signup.this.pDialog.dismiss();
                                                                                return;
                                                                            } catch (Exception e3) {
                                                                                Signup.this.show_snackbar(e3.toString());
                                                                                e3.printStackTrace();
                                                                                return;
                                                                            }
                                                                        } else if (Integer.parseInt(string2) == 1) {
                                                                            try {
                                                                                if (Signup.this.pDialog != null && Signup.this.pDialog.isShowing()) {
                                                                                    Signup.this.pDialog.dismiss();
                                                                                }
                                                                            } catch (Exception e4) {
                                                                                Signup.this.show_snackbar(e4.toString());
                                                                                e4.printStackTrace();
                                                                            }
                                                                            Signup signup2 = Signup.this;
                                                                            signup2.show_snackbar(signup2.getString(R.string.registration_failed));
                                                                            return;
                                                                        } else {
                                                                            return;
                                                                        }
                                                                    } catch (JSONException e5) {
                                                                        Signup.this.show_snackbar(e5.toString());
                                                                        e5.printStackTrace();
                                                                        return;
                                                                    }
                                                                }
                                                                try {
                                                                    if (Signup.this.pDialog != null && Signup.this.pDialog.isShowing()) {
                                                                        Signup.this.pDialog.dismiss();
                                                                    }
                                                                } catch (Exception e6) {
                                                                    Signup.this.show_snackbar(e6.toString());
                                                                    e6.printStackTrace();
                                                                }
                                                                Signup signup3 = Signup.this;
                                                                signup3.show_snackbar(signup3.getString(R.string.server_is_unreachable));
                                                                return;
                                                            } catch (Exception e7) {
                                                                Signup.this.show_snackbar(e7.toString());
                                                            }
                                                            Signup.this.show_snackbar(e7.toString());
                                                        }
                                                    }, new Response.ErrorListener() { // from class: com.egeetouch.egeetouch_manager.Signup.6
                                                        @Override // com.android.volley.Response.ErrorListener
                                                        public void onErrorResponse(VolleyError volleyError) {
                                                            Signup.this.show_snackbar(volleyError.toString());
                                                        }
                                                    }) { // from class: com.egeetouch.egeetouch_manager.Signup.7
                                                        @Override // com.android.volley.Request
                                                        protected Map<String, String> getParams() {
                                                            HashMap hashMap = new HashMap();
                                                            hashMap.put("command", "register");
                                                            hashMap.put("fname", Signup.firstname);
                                                            hashMap.put("lname", Signup.lastname);
                                                            hashMap.put("email", Signup.email);
                                                            hashMap.put("password", Signup.password);
                                                            if (Signup.this.subcription.booleanValue()) {
                                                                hashMap.put("newsletter", AppEventsConstants.EVENT_PARAM_VALUE_YES);
                                                            } else {
                                                                hashMap.put("newsletter", AppEventsConstants.EVENT_PARAM_VALUE_NO);
                                                            }
                                                            return hashMap;
                                                        }
                                                    });
                                                    return;
                                                }
                                                show_snackbar(getString(R.string.internet_is_not_available));
                                                finish();
                                                return;
                                            }
                                            this.checkbox_agreement.setError("");
                                            return;
                                        }
                                        this.signup_password1.setError(getString(R.string.please_enter_at_least_numeric));
                                        return;
                                    }
                                    this.signup_password1.setError(getString(R.string.please_enter_at_least_alpha_numeric));
                                    return;
                                }
                                this.signup_password1.setError(getString(R.string.please_enter_identical_password));
                                return;
                            }
                            this.signup_password2.setError(getString(R.string.please_key_in_password));
                            return;
                        }
                        this.signup_password1.setError(getString(R.string.please_key_in_password));
                        return;
                    }
                    this.signup_email.setError(getString(R.string.please_enter_an_valid_email));
                    return;
                }
                this.signup_email.setError(getString(R.string.please_enter_your_email));
                return;
            }
            this.signup_last_name.setError(getString(R.string.please_enter_your_name));
            return;
        }
        this.signup_first_name.setError(getString(R.string.please_enter_your_name));
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
}
