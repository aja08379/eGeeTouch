package com.egeetouch.egeetouch_manager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
/* loaded from: classes.dex */
public class DfuNotificationActivity extends Activity {
    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (isTaskRoot()) {
            Intent intent = new Intent(this, DfuActivity.class);
            intent.addFlags(268435456);
            startActivity(intent);
        }
        finish();
    }
}
