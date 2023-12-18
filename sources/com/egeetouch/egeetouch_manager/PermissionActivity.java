package com.egeetouch.egeetouch_manager;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
/* loaded from: classes.dex */
public class PermissionActivity extends AppCompatActivity {
    ImageView bluetooth_check;
    ImageView bluetooth_ic;
    Button btn_settings;
    ImageView location_check;
    ImageView location_ic;
    boolean locationPermit = false;
    boolean bluetoothPermit = false;
    boolean askOnce = false;
    boolean askTwice = false;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_permission);
        getWindow().setFlags(1024, 1024);
        this.bluetooth_ic = (ImageView) findViewById(R.id.bluetooth_icon);
        this.bluetooth_check = (ImageView) findViewById(R.id.bluetooth_check);
        this.location_ic = (ImageView) findViewById(R.id.location_icon);
        this.location_check = (ImageView) findViewById(R.id.location_check);
        Button button = (Button) findViewById(R.id.btn_settings);
        this.btn_settings = button;
        button.setOnClickListener(new View.OnClickListener() { // from class: com.egeetouch.egeetouch_manager.PermissionActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PermissionActivity.this.settings();
            }
        });
        checkPermit();
    }

    private void ask() {
        ActivityCompat.requestPermissions(this, new String[]{"android.permission.ACCESS_FINE_LOCATION", "android.permission.BLUETOOTH_SCAN", "android.permission.BLUETOOTH_CONNECT"}, 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void settings() {
        if (!this.askOnce) {
            ask();
            this.askOnce = true;
        } else if (!this.askTwice) {
            ask();
            this.askTwice = true;
        } else if (!this.bluetoothPermit) {
            Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", Uri.parse("package:" + getPackageName()));
            intent.addCategory("android.intent.category.DEFAULT");
            intent.setFlags(268435456);
            startActivity(intent);
        } else if (this.locationPermit) {
        } else {
            Intent intent2 = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", Uri.parse("package:" + getPackageName()));
            intent2.addCategory("android.intent.category.DEFAULT");
            intent2.setFlags(268435456);
            startActivity(intent2);
        }
    }

    private void checkPermit() {
        if (Build.VERSION.SDK_INT >= 31) {
            if (ContextCompat.checkSelfPermission(this, "android.permission.BLUETOOTH_SCAN") == 0 || ContextCompat.checkSelfPermission(this, "android.permission.BLUETOOTH_CONNECT") == 0) {
                this.bluetoothPermit = true;
            } else {
                this.bluetoothPermit = false;
            }
        } else {
            this.bluetoothPermit = true;
        }
        if (ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") == 0) {
            this.locationPermit = true;
        } else {
            this.locationPermit = false;
        }
        if (this.locationPermit) {
            this.location_check.setImageResource(R.drawable.ic_check_green);
            this.location_ic.setImageResource(R.drawable.ic_location_on);
        } else {
            this.location_check.setImageResource(R.drawable.ic_close_red);
            this.location_ic.setImageResource(R.drawable.ic_location);
        }
        if (this.bluetoothPermit) {
            this.bluetooth_check.setImageResource(R.drawable.ic_check_green);
            this.bluetooth_ic.setImageResource(R.drawable.ic_bluetooth_on);
        } else {
            this.bluetooth_check.setImageResource(R.drawable.ic_close_red);
            this.bluetooth_ic.setImageResource(R.drawable.ic_bluetooth);
        }
        if (this.locationPermit && this.bluetoothPermit) {
            this.btn_settings.setText(R.string.done);
            this.btn_settings.setOnClickListener(new View.OnClickListener() { // from class: com.egeetouch.egeetouch_manager.PermissionActivity.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (PreferenceManager.getDefaultSharedPreferences(PermissionActivity.this.getApplicationContext()).getBoolean("keep_access", false)) {
                        PermissionActivity.this.startActivityForResult(new Intent(PermissionActivity.this, MainActivity.class), 3);
                    } else {
                        PermissionActivity.this.startActivity(new Intent(PermissionActivity.this, login_page_gmail.class));
                    }
                    PermissionActivity.this.finish();
                }
            });
        }
        if (this.askTwice) {
            if (this.bluetoothPermit && this.locationPermit) {
                return;
            }
            this.btn_settings.setText(R.string.setting);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        checkPermit();
    }
}
