package com.egeetouch.egeetouch_manager;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.facebook.AccessToken;
import com.facebook.Profile;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
/* loaded from: classes.dex */
public class login_page_gmail extends AppCompatActivity {
    public static String current_logon_email = "";
    public static String current_logon_name = "";
    public static FirebaseAuth mAuth;
    public FirebaseAuth.AuthStateListener mAuthListener;
    private GoogleApiClient mGoogleApiClient;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getBoolean("keep_access", false)) {
            startActivityForResult(new Intent(this, MainActivity.class), 3);
        }
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        setContentView(R.layout.login_page_gmail2);
        mAuth = FirebaseAuth.getInstance();
        this.mAuthListener = new FirebaseAuth.AuthStateListener() { // from class: com.egeetouch.egeetouch_manager.login_page_gmail.1
            @Override // com.google.firebase.auth.FirebaseAuth.AuthStateListener
            public void onAuthStateChanged(FirebaseAuth firebaseAuth) {
                FirebaseUser currentUser = firebaseAuth.getCurrentUser();
                if (currentUser != null) {
                    Log.i("ContentValues", "onAuthStateChanged:signed_in:" + currentUser.getUid());
                } else {
                    Log.i("ContentValues", "onAuthStateChanged:signed_out");
                }
            }
        };
        mAuth = FirebaseAuth.getInstance();
        if (Profile.getCurrentProfile() == null || AccessToken.getCurrentAccessToken() == null) {
            return;
        }
        startActivityForResult(new Intent(this, MainActivity.class), 3);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        FirebaseAuth.AuthStateListener authStateListener = this.mAuthListener;
        if (authStateListener != null) {
            mAuth.removeAuthStateListener(authStateListener);
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(this.mAuthListener);
        Log.i("Tag", "onStart currentUser: " + String.valueOf(mAuth.getCurrentUser()));
    }

    public void btn_login(View view) {
        startActivityForResult(new Intent(this, Login.class), 1);
    }

    public void btn_signup(View view) {
        startActivityForResult(new Intent(this, Signup.class), 2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1) {
            if (i2 == 16) {
                startActivityForResult(new Intent(this, Signup.class), 2);
            } else if (i2 == 32) {
                startActivityForResult(new Intent(this, MainActivity.class), 3);
            }
        } else if (i == 2) {
            if (i2 == -1) {
                startActivityForResult(new Intent(this, Login.class), 1);
            }
        } else if (i == 3 && i2 == -1) {
            finish();
        }
    }
}
