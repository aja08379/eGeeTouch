package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.common.internal.Preconditions;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import org.json.JSONException;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzyj  reason: invalid package */
/* loaded from: classes.dex */
public final class zzyj {
    public static void zza(String str, zzxm zzxmVar, zzyg zzygVar, Type type, zzxq zzxqVar) {
        try {
            Preconditions.checkNotNull(zzxmVar);
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setDoOutput(true);
            byte[] bytes = zzxmVar.zza().getBytes(Charset.defaultCharset());
            int length = bytes.length;
            httpURLConnection.setFixedLengthStreamingMode(length);
            httpURLConnection.setRequestProperty("Content-Type", "application/json");
            httpURLConnection.setConnectTimeout(60000);
            zzxqVar.zza(httpURLConnection);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(httpURLConnection.getOutputStream(), length);
            try {
                bufferedOutputStream.write(bytes, 0, length);
                bufferedOutputStream.close();
                zzb(httpURLConnection, zzygVar, type);
            } catch (Throwable th) {
                try {
                    bufferedOutputStream.close();
                } catch (Throwable th2) {
                    Throwable.class.getDeclaredMethod("addSuppressed", Throwable.class).invoke(th, th2);
                }
                throw th;
            }
        } catch (IOException e) {
            e = e;
            zzygVar.zza(e.getMessage());
        } catch (NullPointerException e2) {
            e = e2;
            zzygVar.zza(e.getMessage());
        } catch (SocketTimeoutException unused) {
            zzygVar.zza("TIMEOUT");
        } catch (UnknownHostException unused2) {
            zzygVar.zza("<<Network Error>>");
        } catch (JSONException e3) {
            e = e3;
            zzygVar.zza(e.getMessage());
        }
    }

    private static void zzb(HttpURLConnection httpURLConnection, zzyg zzygVar, Type type) {
        InputStream errorStream;
        try {
            try {
                int responseCode = httpURLConnection.getResponseCode();
                if (zzc(responseCode)) {
                    errorStream = httpURLConnection.getInputStream();
                } else {
                    errorStream = httpURLConnection.getErrorStream();
                }
                StringBuilder sb = new StringBuilder();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(errorStream, "UTF-8"));
                while (true) {
                    try {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        }
                        sb.append(readLine);
                    } catch (Throwable th) {
                        try {
                            bufferedReader.close();
                        } catch (Throwable th2) {
                            Throwable.class.getDeclaredMethod("addSuppressed", Throwable.class).invoke(th, th2);
                        }
                        throw th;
                    }
                }
                bufferedReader.close();
                String sb2 = sb.toString();
                if (!zzc(responseCode)) {
                    zzygVar.zza((String) zzxl.zza(sb2, String.class));
                } else {
                    zzygVar.zzb((zzxn) zzxl.zza(sb2, type));
                }
                httpURLConnection.disconnect();
            } catch (zzvg e) {
                e = e;
                zzygVar.zza(e.getMessage());
                httpURLConnection.disconnect();
            } catch (SocketTimeoutException unused) {
                zzygVar.zza("TIMEOUT");
                httpURLConnection.disconnect();
            } catch (IOException e2) {
                e = e2;
                zzygVar.zza(e.getMessage());
                httpURLConnection.disconnect();
            }
        } catch (Throwable th3) {
            httpURLConnection.disconnect();
            throw th3;
        }
    }

    private static final boolean zzc(int i) {
        return i >= 200 && i < 300;
    }
}
