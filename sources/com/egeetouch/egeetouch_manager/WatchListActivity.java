package com.egeetouch.egeetouch_manager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import no.nordicsemi.android.dfu.DfuServiceInitiator;
/* loaded from: classes.dex */
public class WatchListActivity extends AppCompatActivity {
    private static final int REQUEST_CAMERA_PERMISSION = 201;
    ListView listView;
    SharedPreferences sharedPreferences;
    ArrayAdapter_watch_list watchAdapter;
    String[] watch_name;
    long[] watch_time;
    String[] watch_uuid;
    String listKey = "WATCH_LIST";
    Handler handler = new Handler();
    boolean askOnce = false;
    boolean askTwice = false;
    Runnable runRefresh = new Runnable() { // from class: com.egeetouch.egeetouch_manager.WatchListActivity.1
        @Override // java.lang.Runnable
        public void run() {
            WatchListActivity.this.refreshList();
            WatchListActivity.this.handler.postDelayed(this, DfuServiceInitiator.DEFAULT_SCAN_TIMEOUT);
        }
    };

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_watch_list);
        getWindow().setFlags(1024, 1024);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.sharedPreferences = getSharedPreferences("WATCH_AUDIT", 0);
        this.listView = (ListView) findViewById(R.id.watch_list);
        this.handler.post(this.runRefresh);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshList() {
        String string = this.sharedPreferences.getString(this.listKey, "");
        if (string.length() > 0) {
            String[] split = string.split("`");
            int length = split.length / 3;
            this.watch_name = new String[length];
            this.watch_uuid = new String[length];
            this.watch_time = new long[length];
            for (int i = 0; i < length; i++) {
                int i2 = i * 3;
                this.watch_name[i] = split[i2];
                this.watch_uuid[i] = split[i2 + 1];
                this.watch_time[i] = Long.parseLong(split[i2 + 2]);
            }
            ArrayAdapter_watch_list arrayAdapter_watch_list = new ArrayAdapter_watch_list(this, this.watch_name, this.watch_uuid, this.watch_time);
            this.watchAdapter = arrayAdapter_watch_list;
            this.listView.setAdapter((ListAdapter) arrayAdapter_watch_list);
            this.watchAdapter.notifyDataSetChanged();
        }
    }

    public void btn_scan_qr(View view) {
        if (ActivityCompat.checkSelfPermission(this, "android.permission.CAMERA") == 0) {
            startActivity(new Intent(this, QrScanActivity.class));
        } else if (!this.askOnce) {
            this.askOnce = true;
            askCameraPermission();
        } else if (!this.askTwice) {
            this.askTwice = true;
            askCameraPermission();
        } else {
            Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", Uri.parse("package:" + getPackageName()));
            intent.addCategory("android.intent.category.DEFAULT");
            intent.setFlags(268435456);
            startActivity(intent);
        }
    }

    private void askCameraPermission() {
        if (ActivityCompat.checkSelfPermission(this, "android.permission.CAMERA") == 0) {
            startActivity(new Intent(this, QrScanActivity.class));
        } else {
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.CAMERA"}, REQUEST_CAMERA_PERMISSION);
        }
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332) {
            finish();
            return true;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        this.handler.removeCallbacks(this.runRefresh);
        super.onDestroy();
    }
}
