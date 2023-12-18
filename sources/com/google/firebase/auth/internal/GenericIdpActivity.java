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
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import com.google.android.gms.common.util.AndroidUtilsLight;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.common.util.Hex;
import com.google.android.gms.internal.p001firebaseauthapi.zzaay;
import com.google.android.gms.internal.p001firebaseauthapi.zzxe;
import com.google.android.gms.internal.p001firebaseauthapi.zzxf;
import com.google.android.gms.internal.p001firebaseauthapi.zzxg;
import com.google.android.gms.internal.p001firebaseauthapi.zzyz;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.core.ServerValues;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.Executor;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* loaded from: classes2.dex */
public class GenericIdpActivity extends FragmentActivity implements zzxg {
    private static long zzb;
    private static final zzbm zzc = zzbm.zzc();
    private final Executor zzd = com.google.android.gms.internal.p001firebaseauthapi.zzf.zza().zza(1);
    private boolean zze = false;

    private final void zzh() {
        zzb = 0L;
        this.zze = false;
        Intent intent = new Intent();
        intent.putExtra("com.google.firebase.auth.internal.EXTRA_CANCELED", true);
        intent.setAction("com.google.firebase.auth.ACTION_RECEIVE_FIREBASE_AUTH_INTENT");
        if (!LocalBroadcastManager.getInstance(this).sendBroadcast(intent)) {
            zzc.zzf(this, zzai.zza("WEB_CONTEXT_CANCELED"));
        } else {
            zzc.zzd(this);
        }
        finish();
    }

    private final void zzi(Status status) {
        zzb = 0L;
        this.zze = false;
        Intent intent = new Intent();
        zzbl.zzc(intent, status);
        intent.setAction("com.google.firebase.auth.ACTION_RECEIVE_FIREBASE_AUTH_INTENT");
        if (!LocalBroadcastManager.getInstance(this).sendBroadcast(intent)) {
            zzc.zzf(getApplicationContext(), status);
        } else {
            zzc.zzd(this);
        }
        finish();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        String action = getIntent().getAction();
        if ("com.google.firebase.auth.internal.NONGMSCORE_SIGN_IN".equals(action) || "com.google.firebase.auth.internal.NONGMSCORE_LINK".equals(action) || "com.google.firebase.auth.internal.NONGMSCORE_REAUTHENTICATE".equals(action) || "android.intent.action.VIEW".equals(action)) {
            long currentTimeMillis = DefaultClock.getInstance().currentTimeMillis();
            if (currentTimeMillis - zzb < WorkRequest.DEFAULT_BACKOFF_DELAY_MILLIS) {
                Log.e("GenericIdpActivity", "Could not start operation - already in progress");
                return;
            }
            zzb = currentTimeMillis;
            if (bundle != null) {
                this.zze = bundle.getBoolean("com.google.firebase.auth.internal.KEY_STARTED_SIGN_IN");
                return;
            }
            return;
        }
        Log.e("GenericIdpActivity", "Could not do operation - unknown action: ".concat(String.valueOf(action)));
        zzh();
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
            if (!this.zze) {
                String packageName = getPackageName();
                try {
                    String lowerCase = Hex.bytesToStringUppercase(AndroidUtilsLight.getPackageCertificateHashBytes(this, packageName)).toLowerCase(Locale.US);
                    FirebaseApp firebaseApp = FirebaseApp.getInstance(getIntent().getStringExtra("com.google.firebase.auth.KEY_FIREBASE_APP_NAME"));
                    if (!zzyz.zzg(firebaseApp)) {
                        new zzxe(packageName, lowerCase, getIntent(), firebaseApp, this).executeOnExecutor(this.zzd, new Void[0]);
                    } else {
                        zzf(zzg(Uri.parse(zzyz.zza(firebaseApp.getOptions().getApiKey())).buildUpon(), getIntent(), packageName, lowerCase).build(), packageName);
                    }
                } catch (PackageManager.NameNotFoundException e) {
                    String obj = e.toString();
                    Log.e("GenericIdpActivity", "Could not get package signature: " + packageName + " " + obj);
                    zze(packageName, null);
                }
                this.zze = true;
                return;
            }
            zzh();
            return;
        }
        Intent intent = getIntent();
        if (intent.hasExtra("firebaseError")) {
            zzi(zzbl.zzb(intent.getStringExtra("firebaseError")));
        } else if (!intent.hasExtra("link") || !intent.hasExtra("eventId")) {
            zzh();
        } else {
            String stringExtra = intent.getStringExtra("link");
            String stringExtra2 = intent.getStringExtra("eventId");
            String packageName2 = getPackageName();
            boolean booleanExtra = intent.getBooleanExtra("encryptionEnabled", true);
            zzi zza = zzj.zzb().zza(this, packageName2, stringExtra2);
            if (zza == null) {
                zzh();
            }
            if (booleanExtra) {
                stringExtra = zzk.zza(getApplicationContext(), FirebaseApp.getInstance(zza.zza()).getPersistenceKey()).zzb(stringExtra);
            }
            zzaay zzaayVar = new zzaay(zza, stringExtra);
            String zze = zza.zze();
            String zzb2 = zza.zzb();
            zzaayVar.zzf(zze);
            if ("com.google.firebase.auth.internal.NONGMSCORE_SIGN_IN".equals(zzb2) || "com.google.firebase.auth.internal.NONGMSCORE_LINK".equals(zzb2) || "com.google.firebase.auth.internal.NONGMSCORE_REAUTHENTICATE".equals(zzb2)) {
                zzb = 0L;
                this.zze = false;
                Intent intent2 = new Intent();
                SafeParcelableSerializer.serializeToIntentExtra(zzaayVar, intent2, "com.google.firebase.auth.internal.VERIFY_ASSERTION_REQUEST");
                intent2.putExtra("com.google.firebase.auth.internal.OPERATION", zzb2);
                intent2.setAction("com.google.firebase.auth.ACTION_RECEIVE_FIREBASE_AUTH_INTENT");
                if (!LocalBroadcastManager.getInstance(this).sendBroadcast(intent2)) {
                    SharedPreferences.Editor edit = getApplicationContext().getSharedPreferences("com.google.firebase.auth.internal.ProcessDeathHelper", 0).edit();
                    edit.putString("verifyAssertionRequest", SafeParcelableSerializer.serializeToString(zzaayVar));
                    edit.putString("operation", zzb2);
                    edit.putString("tenantId", zze);
                    edit.putLong(ServerValues.NAME_OP_TIMESTAMP, DefaultClock.getInstance().currentTimeMillis());
                    edit.commit();
                } else {
                    zzc.zzd(this);
                }
                finish();
                return;
            }
            Log.e("GenericIdpActivity", "unsupported operation: ".concat(zzb2));
            zzh();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public final void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("com.google.firebase.auth.internal.KEY_STARTED_SIGN_IN", this.zze);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzxg
    public final Context zza() {
        return getApplicationContext();
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzxg
    public final Uri.Builder zzb(Intent intent, String str, String str2) {
        return zzg(new Uri.Builder().scheme("https").appendPath("__").appendPath("auth").appendPath("handler"), intent, str, str2);
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
            Log.e("GenericIdpActivity", "Error generating URL connection");
            return null;
        }
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzxg
    public final void zze(String str, Status status) {
        if (status == null) {
            zzh();
        } else {
            zzi(status);
        }
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzxg
    public final void zzf(Uri uri, String str) {
        if (getPackageManager().resolveActivity(new Intent("android.intent.action.VIEW"), 0) == null) {
            Log.e("GenericIdpActivity", "Device cannot resolve intent for: android.intent.action.VIEW");
            zze(str, null);
            return;
        }
        List<ResolveInfo> queryIntentServices = getPackageManager().queryIntentServices(new Intent(CustomTabsService.ACTION_CUSTOM_TABS_CONNECTION), 0);
        if (queryIntentServices == null || queryIntentServices.isEmpty()) {
            Intent intent = new Intent("android.intent.action.VIEW", uri);
            intent.putExtra("com.android.browser.application_id", str);
            Log.i("GenericIdpActivity", "Opening IDP Sign In link in a browser window.");
            intent.addFlags(BasicMeasure.EXACTLY);
            intent.addFlags(268435456);
            startActivity(intent);
            return;
        }
        CustomTabsIntent build = new CustomTabsIntent.Builder().build();
        Log.i("GenericIdpActivity", "Opening IDP Sign In link in a custom chrome tab.");
        build.launchUrl(this, uri);
    }

    public final Uri.Builder zzg(Uri.Builder builder, Intent intent, String str, String str2) {
        String jSONObject;
        String stringExtra = intent.getStringExtra("com.google.firebase.auth.KEY_API_KEY");
        String stringExtra2 = intent.getStringExtra("com.google.firebase.auth.KEY_PROVIDER_ID");
        String stringExtra3 = intent.getStringExtra("com.google.firebase.auth.KEY_TENANT_ID");
        String stringExtra4 = intent.getStringExtra("com.google.firebase.auth.KEY_FIREBASE_APP_NAME");
        ArrayList<String> stringArrayListExtra = intent.getStringArrayListExtra("com.google.firebase.auth.KEY_PROVIDER_SCOPES");
        String join = (stringArrayListExtra == null || stringArrayListExtra.isEmpty()) ? null : TextUtils.join(",", stringArrayListExtra);
        Bundle bundleExtra = intent.getBundleExtra("com.google.firebase.auth.KEY_PROVIDER_CUSTOM_PARAMS");
        if (bundleExtra == null) {
            jSONObject = null;
        } else {
            JSONObject jSONObject2 = new JSONObject();
            try {
                for (String str3 : bundleExtra.keySet()) {
                    String string = bundleExtra.getString(str3);
                    if (!TextUtils.isEmpty(string)) {
                        jSONObject2.put(str3, string);
                    }
                }
            } catch (JSONException unused) {
                Log.e("GenericIdpActivity", "Unexpected JSON exception when serializing developer specified custom params");
            }
            jSONObject = jSONObject2.toString();
        }
        String uuid = UUID.randomUUID().toString();
        String zza = zzxf.zza(this, UUID.randomUUID().toString());
        String action = intent.getAction();
        String stringExtra5 = intent.getStringExtra("com.google.firebase.auth.internal.CLIENT_VERSION");
        String str4 = jSONObject;
        String str5 = join;
        zzj.zzb().zzd(getApplicationContext(), str, uuid, zza, action, stringExtra2, stringExtra3, stringExtra4);
        String zzc2 = zzk.zza(getApplicationContext(), FirebaseApp.getInstance(stringExtra4).getPersistenceKey()).zzc();
        if (TextUtils.isEmpty(zzc2)) {
            Log.e("GenericIdpActivity", "Could not generate an encryption key for Generic IDP - cancelling flow.");
            zzi(zzai.zza("Failed to generate/retrieve public encryption key for Generic IDP flow."));
            return null;
        } else if (zza == null) {
            return null;
        } else {
            builder.appendQueryParameter("eid", "p").appendQueryParameter("v", "X".concat(String.valueOf(stringExtra5))).appendQueryParameter("authType", "signInWithRedirect").appendQueryParameter("apiKey", stringExtra).appendQueryParameter("providerId", stringExtra2).appendQueryParameter("sessionId", zza).appendQueryParameter("eventId", uuid).appendQueryParameter("apn", str).appendQueryParameter("sha1Cert", str2).appendQueryParameter("publicKey", zzc2);
            if (!TextUtils.isEmpty(str5)) {
                builder.appendQueryParameter("scopes", str5);
            }
            if (!TextUtils.isEmpty(str4)) {
                builder.appendQueryParameter("customParameters", str4);
            }
            if (!TextUtils.isEmpty(stringExtra3)) {
                builder.appendQueryParameter("tid", stringExtra3);
            }
            return builder;
        }
    }
}
