package com.google.android.gms.internal.p001firebaseauthapi;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.logging.Logger;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.internal.zzai;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzxe  reason: invalid package */
/* loaded from: classes.dex */
public final class zzxe extends AsyncTask {
    private static final Logger zza = new Logger("FirebaseAuth", "GetAuthDomainTask");
    private final String zzb;
    private final String zzc;
    private final WeakReference zzd;
    private final Uri.Builder zze;
    private final String zzf;
    private final FirebaseApp zzg;

    public zzxe(String str, String str2, Intent intent, FirebaseApp firebaseApp, zzxg zzxgVar) {
        this.zzb = Preconditions.checkNotEmpty(str);
        this.zzg = (FirebaseApp) Preconditions.checkNotNull(firebaseApp);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotNull(intent);
        String checkNotEmpty = Preconditions.checkNotEmpty(intent.getStringExtra("com.google.firebase.auth.KEY_API_KEY"));
        Uri.Builder buildUpon = Uri.parse(zzxgVar.zzc(checkNotEmpty)).buildUpon();
        buildUpon.appendPath("getProjectConfig").appendQueryParameter("key", checkNotEmpty).appendQueryParameter("androidPackageName", str).appendQueryParameter("sha1Cert", (String) Preconditions.checkNotNull(str2));
        this.zzc = buildUpon.build().toString();
        this.zzd = new WeakReference(zzxgVar);
        this.zze = zzxgVar.zzb(intent, str, str2);
        this.zzf = intent.getStringExtra("com.google.firebase.auth.KEY_CUSTOM_AUTH_DOMAIN");
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // android.os.AsyncTask
    /* renamed from: zza */
    public final void onPostExecute(zzxd zzxdVar) {
        String str;
        Uri.Builder builder;
        zzxg zzxgVar = (zzxg) this.zzd.get();
        String str2 = null;
        if (zzxdVar != null) {
            str2 = zzxdVar.zzc();
            str = zzxdVar.zzd();
        } else {
            str = null;
        }
        if (zzxgVar == null) {
            zza.e("An error has occurred: the handler reference has returned null.", new Object[0]);
        } else if (TextUtils.isEmpty(str2) || (builder = this.zze) == null) {
            zzxgVar.zze(this.zzb, zzai.zza(str));
        } else {
            builder.authority(str2);
            zzxgVar.zzf(this.zze.build(), this.zzb);
        }
    }

    private static byte[] zzb(InputStream inputStream, int i) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byte[] bArr = new byte[128];
            while (true) {
                int read = inputStream.read(bArr);
                if (read != -1) {
                    byteArrayOutputStream.write(bArr, 0, read);
                } else {
                    return byteArrayOutputStream.toByteArray();
                }
            }
        } finally {
            byteArrayOutputStream.close();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00df A[Catch: zzvg -> 0x00fb, NullPointerException -> 0x0112, IOException -> 0x0129, TryCatch #0 {IOException -> 0x0129, blocks: (B:6:0x0014, B:19:0x009d, B:20:0x00bc, B:21:0x00d9, B:23:0x00df, B:25:0x00ed, B:27:0x00f6, B:18:0x008b), top: B:36:0x0014 }] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x0088 -> B:19:0x009d). Please submit an issue!!! */
    @Override // android.os.AsyncTask
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected final /* bridge */ /* synthetic */ java.lang.Object doInBackground(java.lang.Object[] r7) {
        java.lang.String r2;
        java.lang.String r1;
        int r2;
        java.lang.String r2;
        java.lang.Void[] r7 = (java.lang.Void[]) r7;
        com.google.android.gms.internal.p001firebaseauthapi.zzxd r0 = null;
        if (!android.text.TextUtils.isEmpty(r6.zzf)) {
            return com.google.android.gms.internal.p001firebaseauthapi.zzxd.zza(r6.zzf);
        }
        try {
            try {
                java.net.URL r1 = new java.net.URL(r6.zzc);
                com.google.android.gms.internal.p001firebaseauthapi.zzxg r2 = (com.google.android.gms.internal.p001firebaseauthapi.zzxg) r6.zzd.get();
                java.net.HttpURLConnection r1 = r2.zzd(r1);
                r1.addRequestProperty("Content-Type", "application/json; charset=UTF-8");
                r1.setConnectTimeout(60000);
                new com.google.android.gms.internal.p001firebaseauthapi.zzxq(r2.zza(), r6.zzg, com.google.android.gms.internal.p001firebaseauthapi.zzxo.zza().zzb()).zza(r1);
                int r2 = r1.getResponseCode();
                if (r2 != 200) {
                    try {
                        r2 = r2;
                    } catch (java.io.IOException r1) {
                        com.google.android.gms.internal.p001firebaseauthapi.zzxe.zza.w("Error parsing error message from response body in getErrorMessageFromBody. ".concat(r1.toString()), new java.lang.Object[0]);
                        r2 = r2;
                    }
                    if (r1.getResponseCode() >= 400) {
                        java.io.InputStream r1 = r1.getErrorStream();
                        if (r1 == null) {
                            r1 = "WEB_INTERNAL_ERROR:Could not retrieve the authDomain for this project but did not receive an error response from the network request. Please try again.";
                            r2 = r2;
                        } else {
                            r1 = (java.lang.String) com.google.android.gms.internal.p001firebaseauthapi.zzxl.zza(new java.lang.String(zzb(r1, 128)), java.lang.String.class);
                            r2 = r2;
                        }
                        com.google.android.gms.common.logging.Logger r3 = com.google.android.gms.internal.p001firebaseauthapi.zzxe.zza;
                        r2 = java.lang.String.format("Error getting project config. Failed with %s %s", r1, java.lang.Integer.valueOf(r2));
                        r3.e(r2, new java.lang.Object[0]);
                        r0 = com.google.android.gms.internal.p001firebaseauthapi.zzxd.zzb(r1);
                        return r0;
                    }
                    r1 = r0;
                    r2 = r2;
                    com.google.android.gms.common.logging.Logger r3 = com.google.android.gms.internal.p001firebaseauthapi.zzxe.zza;
                    r2 = java.lang.String.format("Error getting project config. Failed with %s %s", r1, java.lang.Integer.valueOf(r2));
                    r3.e(r2, new java.lang.Object[0]);
                    r0 = com.google.android.gms.internal.p001firebaseauthapi.zzxd.zzb(r1);
                    return r0;
                }
                com.google.android.gms.internal.p001firebaseauthapi.zzzx r2 = new com.google.android.gms.internal.p001firebaseauthapi.zzzx();
                r2.zzb(new java.lang.String(zzb(r1.getInputStream(), 128)));
                for (java.lang.String r2 : r2.zzc()) {
                    if (r2.endsWith("firebaseapp.com") || r2.endsWith("web.app")) {
                        return com.google.android.gms.internal.p001firebaseauthapi.zzxd.zza(r2);
                    }
                    while (r1.hasNext()) {
                    }
                }
                return null;
            } catch (java.io.IOException r1) {
                com.google.android.gms.internal.p001firebaseauthapi.zzxe.zza.e("IOException occurred: ".concat(java.lang.String.valueOf(r1.getMessage())), new java.lang.Object[0]);
                return null;
            }
        } catch (com.google.android.gms.internal.p001firebaseauthapi.zzvg r1) {
            com.google.android.gms.internal.p001firebaseauthapi.zzxe.zza.e("ConversionException encountered: ".concat(java.lang.String.valueOf(r1.getMessage())), new java.lang.Object[0]);
            return null;
        } catch (java.lang.NullPointerException r1) {
            com.google.android.gms.internal.p001firebaseauthapi.zzxe.zza.e("Null pointer encountered: ".concat(java.lang.String.valueOf(r1.getMessage())), new java.lang.Object[0]);
            return null;
        }
    }

    @Override // android.os.AsyncTask
    protected final /* synthetic */ void onCancelled(Object obj) {
        zzxd zzxdVar = (zzxd) obj;
        onPostExecute(null);
    }
}
