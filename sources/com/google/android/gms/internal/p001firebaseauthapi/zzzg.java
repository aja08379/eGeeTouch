package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.logging.Logger;
import com.google.firebase.auth.ActionCodeUrl;
import com.google.firebase.auth.EmailAuthCredential;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzzg  reason: invalid package */
/* loaded from: classes.dex */
public final class zzzg implements zzxm {
    private static final String zza = "zzzg";
    private static final Logger zzb = new Logger(zzzg.class.getSimpleName(), new String[0]);
    private final String zzc;
    private final String zzd;
    private final String zze;

    public zzzg(EmailAuthCredential emailAuthCredential, String str) {
        this.zzc = Preconditions.checkNotEmpty(emailAuthCredential.zzd());
        this.zzd = Preconditions.checkNotEmpty(emailAuthCredential.zzf());
        this.zze = str;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzxm
    public final String zza() throws JSONException {
        ActionCodeUrl parseLink = ActionCodeUrl.parseLink(this.zzd);
        String code = parseLink != null ? parseLink.getCode() : null;
        String zza2 = parseLink != null ? parseLink.zza() : null;
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("email", this.zzc);
        if (code != null) {
            jSONObject.put("oobCode", code);
        }
        if (zza2 != null) {
            jSONObject.put("tenantId", zza2);
        }
        String str = this.zze;
        if (str != null) {
            jSONObject.put("idToken", str);
        }
        return jSONObject.toString();
    }
}
