package com.google.android.gms.internal.p001firebaseauthapi;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzzd  reason: invalid package */
/* loaded from: classes.dex */
public final class zzzd extends AbstractSafeParcelable implements zzxn<zzzd> {
    public static final Parcelable.Creator<zzzd> CREATOR = new zzze();
    private static final String zza = "zzzd";
    private String zzb;
    private boolean zzc;
    private String zzd;
    private boolean zze;
    private zzaaw zzf;
    private List zzg;

    public zzzd() {
        this.zzf = new zzaaw(null);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzb, false);
        SafeParcelWriter.writeBoolean(parcel, 3, this.zzc);
        SafeParcelWriter.writeString(parcel, 4, this.zzd, false);
        SafeParcelWriter.writeBoolean(parcel, 5, this.zze);
        SafeParcelWriter.writeParcelable(parcel, 6, this.zzf, i, false);
        SafeParcelWriter.writeStringList(parcel, 7, this.zzg, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzxn
    public final /* bridge */ /* synthetic */ zzxn zza(String str) throws zzvg {
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.zzb = jSONObject.optString("authUri", null);
            this.zzc = jSONObject.optBoolean("registered", false);
            this.zzd = jSONObject.optString("providerId", null);
            this.zze = jSONObject.optBoolean("forExistingProvider", false);
            if (!jSONObject.has("allProviders")) {
                this.zzf = new zzaaw(null);
            } else {
                this.zzf = new zzaaw(1, zzabk.zzb(jSONObject.optJSONArray("allProviders")));
            }
            this.zzg = zzabk.zzb(jSONObject.optJSONArray("signinMethods"));
            return this;
        } catch (NullPointerException | JSONException e) {
            throw zzabk.zza(e, zza, str);
        }
    }

    public final List zzb() {
        return this.zzg;
    }

    public zzzd(String str, boolean z, String str2, boolean z2, zzaaw zzaawVar, List list) {
        zzaaw zza2;
        this.zzb = str;
        this.zzc = z;
        this.zzd = str2;
        this.zze = z2;
        if (zzaawVar != null) {
            zza2 = zzaaw.zza(zzaawVar);
        } else {
            zza2 = new zzaaw(null);
        }
        this.zzf = zza2;
        this.zzg = list;
    }
}
