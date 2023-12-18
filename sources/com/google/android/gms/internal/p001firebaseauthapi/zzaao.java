package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.common.internal.Preconditions;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaao  reason: invalid package */
/* loaded from: classes.dex */
public final class zzaao implements zzxm {
    private String zza;
    private String zzb;
    private String zzc;
    private String zzd;
    private String zze;
    private String zzf;
    private final zzaaw zzg = new zzaaw(null);
    private final zzaaw zzh = new zzaaw(null);
    private String zzi;

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzxm
    public final String zza() throws JSONException {
        char c;
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("returnSecureToken", true);
        if (!this.zzh.zzb().isEmpty()) {
            List zzb = this.zzh.zzb();
            JSONArray jSONArray = new JSONArray();
            for (int i = 0; i < zzb.size(); i++) {
                jSONArray.put(zzb.get(i));
            }
            jSONObject.put("deleteProvider", jSONArray);
        }
        List zzb2 = this.zzg.zzb();
        int size = zzb2.size();
        int[] iArr = new int[size];
        for (int i2 = 0; i2 < zzb2.size(); i2++) {
            String str = (String) zzb2.get(i2);
            int i3 = 2;
            switch (str.hashCode()) {
                case -333046776:
                    if (str.equals("DISPLAY_NAME")) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case 66081660:
                    if (str.equals("EMAIL")) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                case 1939891618:
                    if (str.equals("PHOTO_URL")) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                case 1999612571:
                    if (str.equals("PASSWORD")) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    c = 65535;
                    break;
            }
            if (c == 0) {
                i3 = 1;
            } else if (c != 1) {
                i3 = c != 2 ? c != 3 ? 0 : 4 : 5;
            }
            iArr[i2] = i3;
        }
        if (size > 0) {
            JSONArray jSONArray2 = new JSONArray();
            for (int i4 = 0; i4 < size; i4++) {
                jSONArray2.put(iArr[i4]);
            }
            jSONObject.put("deleteAttribute", jSONArray2);
        }
        String str2 = this.zza;
        if (str2 != null) {
            jSONObject.put("idToken", str2);
        }
        String str3 = this.zzc;
        if (str3 != null) {
            jSONObject.put("email", str3);
        }
        String str4 = this.zzd;
        if (str4 != null) {
            jSONObject.put("password", str4);
        }
        String str5 = this.zzb;
        if (str5 != null) {
            jSONObject.put("displayName", str5);
        }
        String str6 = this.zzf;
        if (str6 != null) {
            jSONObject.put("photoUrl", str6);
        }
        String str7 = this.zze;
        if (str7 != null) {
            jSONObject.put("oobCode", str7);
        }
        String str8 = this.zzi;
        if (str8 != null) {
            jSONObject.put("tenantId", str8);
        }
        return jSONObject.toString();
    }

    public final zzaao zzb(String str) {
        Preconditions.checkNotEmpty(str);
        this.zzh.zzb().add(str);
        return this;
    }

    public final zzaao zzc(String str) {
        if (str == null) {
            this.zzg.zzb().add("DISPLAY_NAME");
        } else {
            this.zzb = str;
        }
        return this;
    }

    public final zzaao zzd(String str) {
        if (str == null) {
            this.zzg.zzb().add("EMAIL");
        } else {
            this.zzc = str;
        }
        return this;
    }

    public final zzaao zze(String str) {
        this.zza = Preconditions.checkNotEmpty(str);
        return this;
    }

    public final zzaao zzf(String str) {
        this.zze = Preconditions.checkNotEmpty(str);
        return this;
    }

    public final zzaao zzg(String str) {
        if (str == null) {
            this.zzg.zzb().add("PASSWORD");
        } else {
            this.zzd = str;
        }
        return this;
    }

    public final zzaao zzh(String str) {
        if (str == null) {
            this.zzg.zzb().add("PHOTO_URL");
        } else {
            this.zzf = str;
        }
        return this;
    }

    public final zzaao zzi(String str) {
        this.zzi = str;
        return this;
    }

    public final String zzj() {
        return this.zzb;
    }

    public final String zzk() {
        return this.zzc;
    }

    public final String zzl() {
        return this.zzd;
    }

    public final String zzm() {
        return this.zzf;
    }

    public final boolean zzn(String str) {
        Preconditions.checkNotEmpty(str);
        return this.zzg.zzb().contains(str);
    }
}
