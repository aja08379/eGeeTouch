package com.google.android.gms.internal.p001firebaseauthapi;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.util.Strings;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzzp  reason: invalid package */
/* loaded from: classes.dex */
public final class zzzp extends AbstractSafeParcelable implements zzxn<zzzp> {
    public static final Parcelable.Creator<zzzp> CREATOR = new zzzq();
    private static final String zza = "zzzp";
    private zzzt zzb;

    public zzzp() {
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzb, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzxn
    public final /* bridge */ /* synthetic */ zzxn zza(String str) throws zzvg {
        zzzt zzztVar;
        int i;
        zzzr zzzrVar;
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (!jSONObject.has("users")) {
                this.zzb = new zzzt();
            } else {
                JSONArray optJSONArray = jSONObject.optJSONArray("users");
                Parcelable.Creator<zzzt> creator = zzzt.CREATOR;
                if (optJSONArray != null && optJSONArray.length() != 0) {
                    ArrayList arrayList = new ArrayList(optJSONArray.length());
                    boolean z = false;
                    int i2 = 0;
                    while (i2 < optJSONArray.length()) {
                        JSONObject jSONObject2 = optJSONArray.getJSONObject(i2);
                        if (jSONObject2 != null) {
                            i = i2;
                            zzzrVar = new zzzr(Strings.emptyToNull(jSONObject2.optString("localId", null)), Strings.emptyToNull(jSONObject2.optString("email", null)), jSONObject2.optBoolean("emailVerified", z), Strings.emptyToNull(jSONObject2.optString("displayName", null)), Strings.emptyToNull(jSONObject2.optString("photoUrl", null)), zzaag.zza(jSONObject2.optJSONArray("providerUserInfo")), Strings.emptyToNull(jSONObject2.optString("rawPassword", null)), Strings.emptyToNull(jSONObject2.optString("phoneNumber", null)), jSONObject2.optLong("createdAt", 0L), jSONObject2.optLong("lastLoginAt", 0L), false, null, zzaac.zzf(jSONObject2.optJSONArray("mfaInfo")));
                        } else {
                            zzzrVar = new zzzr();
                            i = i2;
                        }
                        arrayList.add(zzzrVar);
                        i2 = i + 1;
                        z = false;
                    }
                    zzztVar = new zzzt(arrayList);
                    this.zzb = zzztVar;
                }
                zzztVar = new zzzt(new ArrayList());
                this.zzb = zzztVar;
            }
            return this;
        } catch (NullPointerException | JSONException e) {
            throw zzabk.zza(e, zza, str);
        }
    }

    public final List zzb() {
        return this.zzb.zzb();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzzp(zzzt zzztVar) {
        zzzt zza2;
        if (zzztVar != null) {
            zza2 = zzzt.zza(zzztVar);
        } else {
            zza2 = new zzzt();
        }
        this.zzb = zza2;
    }
}
