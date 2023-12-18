package com.google.firebase.auth.internal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.browser.customtabs.CustomTabsService;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import androidx.fragment.app.FragmentActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.work.WorkRequest;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.util.AndroidUtilsLight;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.common.util.Hex;
import com.google.android.gms.internal.p001firebaseauthapi.zzxe;
import com.google.android.gms.internal.p001firebaseauthapi.zzxg;
import com.google.android.gms.internal.p001firebaseauthapi.zzxr;
import com.google.android.gms.internal.p001firebaseauthapi.zzyz;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.core.ServerValues;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* loaded from: classes2.dex */
public class RecaptchaActivity extends FragmentActivity implements zzxg {
    private static final String zzb = "RecaptchaActivity";
    private static final ExecutorService zzc = com.google.android.gms.internal.p001firebaseauthapi.zzf.zza().zza(2);
    private static long zzd = 0;
    private static final zzbm zze = zzbm.zzc();
    private boolean zzf = false;

    private final void zzg() {
        zzd = 0L;
        this.zzf = false;
        Intent intent = new Intent();
        intent.putExtra("com.google.firebase.auth.internal.EXTRA_CANCELED", true);
        intent.setAction("com.google.firebase.auth.ACTION_RECEIVE_FIREBASE_AUTH_INTENT");
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
        zze.zzd(this);
        finish();
    }

    private final void zzh(Status status) {
        zzd = 0L;
        this.zzf = false;
        Intent intent = new Intent();
        zzbl.zzc(intent, status);
        intent.setAction("com.google.firebase.auth.ACTION_RECEIVE_FIREBASE_AUTH_INTENT");
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
        zze.zzd(this);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        String action = getIntent().getAction();
        if ("com.google.firebase.auth.internal.ACTION_SHOW_RECAPTCHA".equals(action) || "android.intent.action.VIEW".equals(action)) {
            long currentTimeMillis = DefaultClock.getInstance().currentTimeMillis();
            if (currentTimeMillis - zzd < WorkRequest.DEFAULT_BACKOFF_DELAY_MILLIS) {
                Log.e(zzb, "Could not start operation - already in progress");
                return;
            }
            zzd = currentTimeMillis;
            if (bundle != null) {
                this.zzf = bundle.getBoolean("com.google.firebase.auth.internal.KEY_ALREADY_STARTED_RECAPTCHA_FLOW");
                return;
            }
            return;
        }
        Log.e(zzb, "Could not do operation - unknown action: ".concat(String.valueOf(action)));
        zzg();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public final void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public final void onResume() {
        super.onResume();
        if (!"android.intent.action.VIEW".equals(getIntent().getAction())) {
            if (!this.zzf) {
                Intent intent = getIntent();
                String packageName = getPackageName();
                try {
                    new zzxe(packageName, Hex.bytesToStringUppercase(AndroidUtilsLight.getPackageCertificateHashBytes(this, packageName)).toLowerCase(Locale.US), intent, FirebaseApp.getInstance(intent.getStringExtra("com.google.firebase.auth.internal.FIREBASE_APP_NAME")), this).executeOnExecutor(zzc, new Void[0]);
                } catch (PackageManager.NameNotFoundException e) {
                    String str = zzb;
                    String obj = e.toString();
                    Log.e(str, "Could not get package signature: " + packageName + " " + obj);
                    zze(packageName, null);
                }
                this.zzf = true;
                return;
            }
            zzg();
            return;
        }
        Intent intent2 = getIntent();
        if (intent2.hasExtra("firebaseError")) {
            zzh(zzbl.zzb(intent2.getStringExtra("firebaseError")));
        } else if (!intent2.hasExtra("link") || !intent2.hasExtra("eventId")) {
            zzg();
        } else {
            String stringExtra = intent2.getStringExtra("link");
            String zzc2 = zzj.zzb().zzc(getApplicationContext(), getPackageName(), intent2.getStringExtra("eventId"));
            if (TextUtils.isEmpty(zzc2)) {
                Log.e(zzb, "Failed to find registration for this event - failing to prevent session injection.");
                zzh(zzai.zza("Failed to find registration for this reCAPTCHA event"));
            }
            if (intent2.getBooleanExtra("encryptionEnabled", true)) {
                stringExtra = zzk.zza(getApplicationContext(), FirebaseApp.getInstance(zzc2).getPersistenceKey()).zzb(stringExtra);
            }
            String queryParameter = Uri.parse(stringExtra).getQueryParameter("recaptchaToken");
            zzd = 0L;
            this.zzf = false;
            Intent intent3 = new Intent();
            intent3.putExtra("com.google.firebase.auth.internal.RECAPTCHA_TOKEN", queryParameter);
            intent3.putExtra("com.google.firebase.auth.internal.OPERATION", "com.google.firebase.auth.internal.ACTION_SHOW_RECAPTCHA");
            intent3.setAction("com.google.firebase.auth.ACTION_RECEIVE_FIREBASE_AUTH_INTENT");
            if (!LocalBroadcastManager.getInstance(this).sendBroadcast(intent3)) {
                SharedPreferences.Editor edit = getApplicationContext().getSharedPreferences("com.google.firebase.auth.internal.ProcessDeathHelper", 0).edit();
                edit.putString("recaptchaToken", queryParameter);
                edit.putString("operation", "com.google.firebase.auth.internal.ACTION_SHOW_RECAPTCHA");
                edit.putLong(ServerValues.NAME_OP_TIMESTAMP, DefaultClock.getInstance().currentTimeMillis());
                edit.commit();
            } else {
                zze.zzd(this);
            }
            finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public final void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("com.google.firebase.auth.internal.KEY_ALREADY_STARTED_RECAPTCHA_FLOW", this.zzf);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzxg
    public final Context zza() {
        return getApplicationContext();
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzxg
    public final Uri.Builder zzb(Intent intent, String str, String str2) {
        String zza;
        String stringExtra = intent.getStringExtra("com.google.firebase.auth.KEY_API_KEY");
        String uuid = UUID.randomUUID().toString();
        String stringExtra2 = intent.getStringExtra("com.google.firebase.auth.internal.CLIENT_VERSION");
        String stringExtra3 = intent.getStringExtra("com.google.firebase.auth.internal.FIREBASE_APP_NAME");
        FirebaseApp firebaseApp = FirebaseApp.getInstance(stringExtra3);
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance(firebaseApp);
        zzj.zzb().zze(getApplicationContext(), str, uuid, "com.google.firebase.auth.internal.ACTION_SHOW_RECAPTCHA", stringExtra3);
        String zzc2 = zzk.zza(getApplicationContext(), firebaseApp.getPersistenceKey()).zzc();
        if (TextUtils.isEmpty(zzc2)) {
            Log.e(zzb, "Could not generate an encryption key for reCAPTCHA - cancelling flow.");
            zzh(zzai.zza("Failed to generate/retrieve public encryption key for reCAPTCHA flow."));
            return null;
        }
        if (!TextUtils.isEmpty(firebaseAuth.getLanguageCode())) {
            zza = firebaseAuth.getLanguageCode();
        } else {
            zza = zzxr.zza();
        }
        return new Uri.Builder().scheme("https").appendPath("__").appendPath("auth").appendPath("handler").appendQueryParameter("apiKey", stringExtra).appendQueryParameter("authType", "verifyApp").appendQueryParameter("apn", str).appendQueryParameter("hl", zza).appendQueryParameter("eventId", uuid).appendQueryParameter("v", "X".concat(String.valueOf(stringExtra2))).appendQueryParameter("eid", "p").appendQueryParameter("appName", stringExtra3).appendQueryParameter("sha1Cert", str2).appendQueryParameter("publicKey", zzc2);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzxg
    public final String zzc(String str) {
        return zzyz.zzb(str);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzxg
    public final HttpURLConnection zzd(URL url) {
        try {
            return (HttpURLConnection) url.openConnection();
        } catch (IOException unused) {
            zza.e("Error generating connection", new Object[0]);
            return null;
        }
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzxg
    public final void zze(String str, Status status) {
        if (status == null) {
            zzg();
        } else {
            zzh(status);
        }
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzxg
    public final void zzf(Uri uri, String str) {
        if (getPackageManager().resolveActivity(new Intent("android.intent.action.VIEW"), 0) == null) {
            Log.e(zzb, "Device cannot resolve intent for: android.intent.action.VIEW");
            zze(str, null);
            return;
        }
        List<ResolveInfo> queryIntentServices = getPackageManager().queryIntentServices(new Intent(CustomTabsService.ACTION_CUSTOM_TABS_CONNECTION), 0);
        if (queryIntentServices == null || queryIntentServices.isEmpty()) {
            Intent intent = new Intent("android.intent.action.VIEW", uri);
            intent.putExtra("com.android.browser.application_id", str);
            intent.addFlags(BasicMeasure.EXACTLY);
            intent.addFlags(268435456);
            startActivity(intent);
            return;
        }
        CustomTabsIntent build = new CustomTabsIntent.Builder().build();
        build.intent.addFlags(BasicMeasure.EXACTLY);
        build.intent.addFlags(268435456);
        build.launchUrl(this, uri);
    }
}
