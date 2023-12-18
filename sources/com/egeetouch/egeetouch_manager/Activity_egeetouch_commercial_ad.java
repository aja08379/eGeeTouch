package com.egeetouch.egeetouch_manager;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.net.MailTo;
/* loaded from: classes.dex */
public class Activity_egeetouch_commercial_ad extends AppCompatActivity {
    Button btn_sendMail;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.egeetouch_commercial_advet_page);
        getSupportActionBar().setTitle("eGeeTouch Commercial");
        getWindow().setFlags(1024, 1024);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        finish();
        return super.onOptionsItemSelected(menuItem);
    }

    public void sendMail(View view) {
        String[] strArr = {getString(R.string.egee_commercial_sales_email)};
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setData(Uri.parse(MailTo.MAILTO_SCHEME));
        intent.setType("text/plain");
        intent.putExtra("android.intent.extra.EMAIL", strArr);
        intent.putExtra("android.intent.extra.SUBJECT", getString(R.string.egee_emailSubject));
        intent.putExtra("android.intent.extra.TEXT", getString(R.string.egee_email_content));
        try {
            startActivity(Intent.createChooser(intent, "Send mail..."));
            finish();
        } catch (ActivityNotFoundException unused) {
            Toast.makeText(this, "There is no email client installed.", 0).show();
        }
    }
}
