package com.egeetouch.egeetouch_manager;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import cn.pedant.SweetAlert.SweetAlertDialog;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
/* loaded from: classes.dex */
public class ForgotPassword extends AppCompatActivity {
    private ProgressDialog pDialog;
    String recovery_email = "";
    private SweetAlertDialog swDialog;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.forgot_password_test);
        getWindow().setFlags(1024, 1024);
        getSupportActionBar().setTitle(getString(R.string.forgot_your_password));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332) {
            finish();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void btn_send(View view) {
        hideKeyboard(view);
        EditText editText = (EditText) findViewById(R.id.editText_recovery_email);
        if (!editText.getText().toString().equals("")) {
            if (Helper_EmailValidation.isEmailValid(editText.getText().toString())) {
                this.recovery_email = editText.getText().toString();
                if (Helper_Network.haveNetworkConnection(this)) {
                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(this, 5);
                    this.swDialog = sweetAlertDialog;
                    sweetAlertDialog.setTitleText(getString(R.string.forgot_your_password));
                    this.swDialog.setContentText(getString(R.string.processing));
                    this.swDialog.setCancelable(false);
                    this.swDialog.setCancelText(getString(R.string.cancel));
                    this.swDialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.ForgotPassword.1
                        @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                        public void onClick(SweetAlertDialog sweetAlertDialog2) {
                            sweetAlertDialog2.dismissWithAnimation();
                        }
                    });
                    this.swDialog.show();
                    try {
                        login_page_gmail.mAuth.sendPasswordResetEmail(this.recovery_email).addOnSuccessListener(new OnSuccessListener<Void>() { // from class: com.egeetouch.egeetouch_manager.ForgotPassword.3
                            @Override // com.google.android.gms.tasks.OnSuccessListener
                            public void onSuccess(Void r3) {
                                if (ForgotPassword.this.swDialog != null && ForgotPassword.this.swDialog.isShowing()) {
                                    ForgotPassword.this.swDialog.dismissWithAnimation();
                                }
                                new SweetAlertDialog(ForgotPassword.this).setTitleText(ForgotPassword.this.getString(R.string.email_sent)).setContentText(ForgotPassword.this.getString(R.string.check_your_email_and_follow_the_instruction)).setConfirmText(ForgotPassword.this.getString(R.string.dismiss)).setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.ForgotPassword.3.1
                                    @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                                    public void onClick(SweetAlertDialog sweetAlertDialog2) {
                                        sweetAlertDialog2.dismissWithAnimation();
                                        ForgotPassword.this.finish();
                                    }
                                }).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() { // from class: com.egeetouch.egeetouch_manager.ForgotPassword.2
                            @Override // com.google.android.gms.tasks.OnFailureListener
                            public void onFailure(Exception exc) {
                                if (ForgotPassword.this.swDialog != null && ForgotPassword.this.swDialog.isShowing()) {
                                    ForgotPassword.this.swDialog.dismissWithAnimation();
                                }
                                new SweetAlertDialog(ForgotPassword.this).setTitleText(ForgotPassword.this.getString(R.string.error)).setContentText(ForgotPassword.this.getString(R.string.recovery_failed)).setConfirmText(ForgotPassword.this.getString(R.string.ok)).setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.ForgotPassword.2.1
                                    @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                                    public void onClick(SweetAlertDialog sweetAlertDialog2) {
                                        sweetAlertDialog2.dismissWithAnimation();
                                        ForgotPassword.this.finish();
                                    }
                                }).show();
                            }
                        });
                        return;
                    } catch (Exception unused) {
                        new SweetAlertDialog(this).setTitleText(getString(R.string.error)).setContentText(getString(R.string.recovery_failed)).setConfirmText(getString(R.string.ok)).setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.ForgotPassword.4
                            @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                            public void onClick(SweetAlertDialog sweetAlertDialog2) {
                                sweetAlertDialog2.dismissWithAnimation();
                                ForgotPassword.this.finish();
                            }
                        }).show();
                        return;
                    }
                }
                show_snackbar(getString(R.string.internet_is_not_available));
                finish();
                return;
            }
            editText.setError(getString(R.string.please_enter_an_valid_email));
            return;
        }
        editText.setError(getString(R.string.please_enter_your_email));
    }

    private void show_snackbar(String str) {
        Snackbar make = Snackbar.make(findViewById(R.id.coordinatorLayout), str, 0);
        make.getView().setBackgroundColor(Color.parseColor("#D0389AE0"));
        make.show();
    }

    public void hideKeyboard(View view) {
        ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
