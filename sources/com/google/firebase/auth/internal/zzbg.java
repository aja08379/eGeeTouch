package com.google.firebase.auth.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import androidx.exifinterface.media.ExifInterface;
import com.facebook.internal.ServerProtocol;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.logging.Logger;
import com.google.android.gms.internal.p001firebaseauthapi.zzqx;
import com.google.android.gms.internal.p001firebaseauthapi.zzzy;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.MultiFactorInfo;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* loaded from: classes2.dex */
public final class zzbg {
    private final Context zza;
    private final String zzb;
    private final SharedPreferences zzc;
    private final Logger zzd;

    public zzbg(Context context, String str) {
        Preconditions.checkNotNull(context);
        String checkNotEmpty = Preconditions.checkNotEmpty(str);
        this.zzb = checkNotEmpty;
        Context applicationContext = context.getApplicationContext();
        this.zza = applicationContext;
        this.zzc = applicationContext.getSharedPreferences(String.format("com.google.firebase.auth.api.Store.%s", checkNotEmpty), 0);
        this.zzd = new Logger("StorageHelpers", new String[0]);
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x00ce A[Catch: zzqx -> 0x0135, IllegalArgumentException -> 0x0137, ArrayIndexOutOfBoundsException -> 0x0139, JSONException -> 0x013b, TRY_ENTER, TryCatch #2 {JSONException -> 0x013b, blocks: (B:3:0x0008, B:6:0x0025, B:10:0x003b, B:12:0x0079, B:14:0x0080, B:15:0x008c, B:16:0x008d, B:18:0x009c, B:20:0x00a5, B:21:0x00a8, B:23:0x00b1, B:28:0x00ce, B:29:0x00d1, B:31:0x00d7, B:33:0x00dd, B:34:0x00e3, B:36:0x00e9, B:38:0x0100, B:40:0x0108, B:44:0x012b, B:41:0x0122, B:42:0x0129, B:45:0x0131), top: B:59:0x0008 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final com.google.firebase.auth.internal.zzx zzf(org.json.JSONObject r27) {
        org.json.JSONArray r0;
        com.google.firebase.auth.PhoneMultiFactorInfo r3;
        com.google.firebase.auth.internal.zzz r3;
        try {
            try {
                java.lang.String r5 = r27.getString("cachedTokenState");
                java.lang.String r6 = r27.getString("applicationName");
                boolean r7 = r27.getBoolean("anonymous");
                java.lang.String r8 = androidx.exifinterface.media.ExifInterface.GPS_MEASUREMENT_2D;
                java.lang.String r9 = r27.getString(com.facebook.internal.ServerProtocol.FALLBACK_DIALOG_PARAM_VERSION);
                if (r9 != null) {
                    r8 = r9;
                }
                org.json.JSONArray r9 = r27.getJSONArray("userInfos");
                int r10 = r9.length();
                java.util.ArrayList r11 = new java.util.ArrayList(r10);
                for (int r13 = 0; r13 < r10; r13++) {
                    java.lang.String r12 = r9.getString(r13);
                    android.os.Parcelable.Creator<com.google.firebase.auth.internal.zzt> r16 = com.google.firebase.auth.internal.zzt.CREATOR;
                    try {
                        org.json.JSONObject r4 = new org.json.JSONObject(r12);
                        r11.add(new com.google.firebase.auth.internal.zzt(r4.optString("userId"), r4.optString("providerId"), r4.optString("email"), r4.optString("phoneNumber"), r4.optString("displayName"), r4.optString("photoUrl"), r4.optBoolean("isEmailVerified"), r4.optString("rawUserInfo")));
                    } catch (org.json.JSONException r0) {
                        android.util.Log.d("DefaultAuthUserInfo", "Failed to unpack UserInfo from JSON");
                        throw new com.google.android.gms.internal.p001firebaseauthapi.zzqx(r0);
                    }
                }
                com.google.firebase.auth.internal.zzx r6 = new com.google.firebase.auth.internal.zzx(com.google.firebase.FirebaseApp.getInstance(r6), r11);
                if (!android.text.TextUtils.isEmpty(r5)) {
                    r6.zzh(com.google.android.gms.internal.p001firebaseauthapi.zzzy.zzd(r5));
                }
                if (!r7) {
                    r6.zzm();
                }
                r6.zzl(r8);
                if (r27.has("userMetadata")) {
                    org.json.JSONObject r3 = r27.getJSONObject("userMetadata");
                    android.os.Parcelable.Creator<com.google.firebase.auth.internal.zzz> r4 = com.google.firebase.auth.internal.zzz.CREATOR;
                    if (r3 != null) {
                        try {
                            r3 = new com.google.firebase.auth.internal.zzz(r3.getLong("lastSignInTimestamp"), r3.getLong("creationTimestamp"));
                        } catch (org.json.JSONException unused) {
                        }
                        if (r3 != null) {
                            r6.zzr(r3);
                        }
                    }
                    r3 = null;
                    if (r3 != null) {
                    }
                }
                if (r27.has("userMultiFactorInfo") && (r0 = r27.getJSONArray("userMultiFactorInfo")) != null) {
                    java.util.ArrayList r2 = new java.util.ArrayList();
                    for (int r12 = 0; r12 < r0.length(); r12++) {
                        org.json.JSONObject r4 = new org.json.JSONObject(r0.getString(r12));
                        if ("phone".equals(r4.optString(com.google.firebase.auth.MultiFactorInfo.FACTOR_ID_KEY))) {
                            android.os.Parcelable.Creator<com.google.firebase.auth.PhoneMultiFactorInfo> r3 = com.google.firebase.auth.PhoneMultiFactorInfo.CREATOR;
                            if (r4.has("enrollmentTimestamp")) {
                                r3 = new com.google.firebase.auth.PhoneMultiFactorInfo(r4.optString("uid"), r4.optString("displayName"), r4.optLong("enrollmentTimestamp"), r4.optString("phoneNumber"));
                            } else {
                                throw new java.lang.IllegalArgumentException("An enrollment timestamp in seconds of UTC time since Unix epoch is required to build a PhoneMultiFactorInfo instance.");
                            }
                        } else {
                            r3 = null;
                        }
                        r2.add(r3);
                    }
                    r6.zzi(r2);
                }
                return r6;
            } catch (org.json.JSONException e) {
                r0 = e;
                r26.zzd.wtf(r0);
                return null;
            }
        } catch (com.google.android.gms.internal.p001firebaseauthapi.zzqx e) {
            r0 = e;
            r26.zzd.wtf(r0);
            return null;
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            r0 = e;
            r26.zzd.wtf(r0);
            return null;
        } catch (java.lang.IllegalArgumentException e) {
            r0 = e;
            r26.zzd.wtf(r0);
            return null;
        }
    }

    public final FirebaseUser zza() {
        String string = this.zzc.getString("com.google.firebase.auth.FIREBASE_USER", null);
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(string);
            if (jSONObject.has("type") && "com.google.firebase.auth.internal.DefaultFirebaseUser".equalsIgnoreCase(jSONObject.optString("type"))) {
                return zzf(jSONObject);
            }
        } catch (Exception unused) {
        }
        return null;
    }

    public final zzzy zzb(FirebaseUser firebaseUser) {
        Preconditions.checkNotNull(firebaseUser);
        String string = this.zzc.getString(String.format("com.google.firebase.auth.GET_TOKEN_RESPONSE.%s", firebaseUser.getUid()), null);
        if (string != null) {
            return zzzy.zzd(string);
        }
        return null;
    }

    public final void zzc(String str) {
        this.zzc.edit().remove(str).apply();
    }

    public final void zzd(FirebaseUser firebaseUser) {
        String str;
        Preconditions.checkNotNull(firebaseUser);
        JSONObject jSONObject = new JSONObject();
        if (zzx.class.isAssignableFrom(firebaseUser.getClass())) {
            zzx zzxVar = (zzx) firebaseUser;
            try {
                jSONObject.put("cachedTokenState", zzxVar.zzf());
                jSONObject.put("applicationName", zzxVar.zza().getName());
                jSONObject.put("type", "com.google.firebase.auth.internal.DefaultFirebaseUser");
                if (zzxVar.zzo() != null) {
                    JSONArray jSONArray = new JSONArray();
                    List zzo = zzxVar.zzo();
                    int size = zzo.size();
                    if (zzo.size() > 30) {
                        this.zzd.w("Provider user info list size larger than max size, truncating list to %d. Actual list size: %d", 30, Integer.valueOf(zzo.size()));
                        size = 30;
                    }
                    for (int i = 0; i < size; i++) {
                        jSONArray.put(((zzt) zzo.get(i)).zzb());
                    }
                    jSONObject.put("userInfos", jSONArray);
                }
                jSONObject.put("anonymous", zzxVar.isAnonymous());
                jSONObject.put(ServerProtocol.FALLBACK_DIALOG_PARAM_VERSION, ExifInterface.GPS_MEASUREMENT_2D);
                if (zzxVar.getMetadata() != null) {
                    jSONObject.put("userMetadata", ((zzz) zzxVar.getMetadata()).zza());
                }
                List<MultiFactorInfo> enrolledFactors = new zzac(zzxVar).getEnrolledFactors();
                if (!enrolledFactors.isEmpty()) {
                    JSONArray jSONArray2 = new JSONArray();
                    for (int i2 = 0; i2 < enrolledFactors.size(); i2++) {
                        jSONArray2.put(enrolledFactors.get(i2).toJson());
                    }
                    jSONObject.put("userMultiFactorInfo", jSONArray2);
                }
                str = jSONObject.toString();
            } catch (Exception e) {
                this.zzd.wtf("Failed to turn object into JSON", e, new Object[0]);
                throw new zzqx(e);
            }
        } else {
            str = null;
        }
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.zzc.edit().putString("com.google.firebase.auth.FIREBASE_USER", str).apply();
    }

    public final void zze(FirebaseUser firebaseUser, zzzy zzzyVar) {
        Preconditions.checkNotNull(firebaseUser);
        Preconditions.checkNotNull(zzzyVar);
        this.zzc.edit().putString(String.format("com.google.firebase.auth.GET_TOKEN_RESPONSE.%s", firebaseUser.getUid()), zzzyVar.zzh()).apply();
    }
}
