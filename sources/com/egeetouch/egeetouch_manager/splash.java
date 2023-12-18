package com.egeetouch.egeetouch_manager;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Base64;
import android.util.Log;
import android.widget.ProgressBar;
import androidx.core.content.ContextCompat;
import cn.pedant.SweetAlert.SweetAlertDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/* loaded from: classes.dex */
public class splash extends Activity {
    private static String latest_app_version = "";
    DialogInterface.OnClickListener dialogClickListener;
    int longPopup;
    FirebaseDatabase mDatabase;
    Runnable myRunnable;
    ProgressBar progressbar;
    DatabaseReference versionRef;
    Handler handle = new Handler();
    int dbVersionCode = 0;
    String dbVersionName = "";
    SweetAlertDialog warning_dialog = null;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        Signature[] signatureArr;
        super.onCreate(bundle);
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        setContentView(R.layout.splash);
        final SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        final SharedPreferences.Editor edit = defaultSharedPreferences.edit();
        try {
            if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                if (FirebaseAuth.getInstance().getCurrentUser().getUid() != null) {
                    FirebaseCrashlytics.getInstance().setUserId(FirebaseAuth.getInstance().getCurrentUser().getUid());
                }
            } else {
                FirebaseCrashlytics.getInstance().setUserId("NotLoggedIn");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            for (Signature signature : getPackageManager().getPackageInfo(BuildConfig.APPLICATION_ID, 64).signatures) {
                MessageDigest messageDigest = MessageDigest.getInstance("SHA");
                messageDigest.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(messageDigest.digest(), 0));
            }
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
        } catch (NoSuchAlgorithmException e3) {
            e3.printStackTrace();
        }
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        this.progressbar = progressBar;
        progressBar.getIndeterminateDrawable().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_IN);
        if (Helper_Network.haveNetworkConnection(this)) {
            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            this.mDatabase = firebaseDatabase;
            DatabaseReference child = firebaseDatabase.getReference("latestAppVersion").child("androidApp");
            this.versionRef = child;
            child.addListenerForSingleValueEvent(new ValueEventListener() { // from class: com.egeetouch.egeetouch_manager.splash.1
                @Override // com.google.firebase.database.ValueEventListener
                public void onCancelled(DatabaseError databaseError) {
                }

                @Override // com.google.firebase.database.ValueEventListener
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot dataSnapshot2 : dataSnapshot.getChildren()) {
                        if (dataSnapshot2.getKey().equals("versionCode")) {
                            splash.this.dbVersionCode = Integer.valueOf(dataSnapshot2.getValue().toString()).intValue();
                        } else if (dataSnapshot2.getKey().equals("versionName")) {
                            splash.this.dbVersionName = dataSnapshot2.getValue().toString();
                        } else if (dataSnapshot2.getKey().equals("longPopup")) {
                            splash.this.longPopup = dataSnapshot2.getValue().hashCode();
                        }
                    }
                    try {
                        if (splash.this.dbVersionCode <= splash.this.getPackageManager().getPackageInfo(splash.this.getPackageName(), 0).versionCode) {
                            splash.this.start_activity();
                        } else {
                            Long valueOf = Long.valueOf(Long.valueOf(defaultSharedPreferences.getLong("CurrentPopup", 0L)).longValue() + (splash.this.longPopup * 3600000));
                            if (System.currentTimeMillis() > valueOf.longValue()) {
                                edit.putLong("CurrentPopup", System.currentTimeMillis());
                                edit.apply();
                                try {
                                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(splash.this);
                                    sweetAlertDialog.setTitleText(splash.this.getString(R.string.new_update_available));
                                    sweetAlertDialog.setContentText(splash.this.getString(R.string.download_latest_version_from_playstore));
                                    sweetAlertDialog.setConfirmText(splash.this.getString(R.string.ok));
                                    sweetAlertDialog.setCancelable(false);
                                    sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.splash.1.1
                                        @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                                        public void onClick(SweetAlertDialog sweetAlertDialog2) {
                                            try {
                                                splash.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + BuildConfig.APPLICATION_ID)));
                                            } catch (ActivityNotFoundException unused) {
                                                splash.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID)));
                                            }
                                            splash.this.finish();
                                        }
                                    });
                                    sweetAlertDialog.setCancelText(splash.this.getString(R.string.cancel));
                                    sweetAlertDialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() { // from class: com.egeetouch.egeetouch_manager.splash.1.2
                                        @Override // cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener
                                        public void onClick(SweetAlertDialog sweetAlertDialog2) {
                                            sweetAlertDialog2.dismissWithAnimation();
                                            splash.this.start_activity();
                                        }
                                    }).show();
                                } catch (Exception e4) {
                                    splash.this.start_activity();
                                    new SweetAlertDialog(splash.this, 3).setTitleText(splash.this.getResources().getString(R.string.app_name)).setContentText(e4.toString()).setConfirmText(splash.this.getResources().getString(R.string.ok)).show();
                                }
                            } else {
                                System.out.println("STILL NEED " + (valueOf.longValue() - System.currentTimeMillis()));
                                splash.this.start_activity();
                            }
                        }
                    } catch (Exception unused) {
                        splash.this.start_activity();
                    }
                }
            });
            return;
        }
        Handler handler = this.handle;
        Runnable runnable = new Runnable() { // from class: com.egeetouch.egeetouch_manager.splash.2
            @Override // java.lang.Runnable
            public void run() {
                splash.this.start_activity();
            }
        };
        this.myRunnable = runnable;
        handler.postDelayed(runnable, 1000L);
    }

    @Override // android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        SweetAlertDialog sweetAlertDialog = this.warning_dialog;
        if (sweetAlertDialog != null) {
            sweetAlertDialog.dismiss();
            this.warning_dialog = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void start_activity() {
        Handler handler = this.handle;
        if (handler != null) {
            handler.removeCallbacks(this.myRunnable);
        }
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        if (Build.VERSION.SDK_INT >= 31 && (ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") != 0 || ContextCompat.checkSelfPermission(this, "android.permission.BLUETOOTH_SCAN") != 0 || ContextCompat.checkSelfPermission(this, "android.permission.BLUETOOTH_CONNECT") != 0)) {
            startActivity(new Intent(this, PermissionActivity.class));
        } else if (Build.VERSION.SDK_INT < 31 && ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") != 0) {
            startActivity(new Intent(this, PermissionActivity.class));
        } else if (defaultSharedPreferences.getBoolean("keep_access", false)) {
            startActivityForResult(new Intent(this, MainActivity.class), 3);
        } else {
            startActivity(new Intent(this, login_page_gmail.class));
        }
        finish();
    }
}
