package com.google.firebase.auth.internal;

import android.text.TextUtils;
import java.util.Map;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* loaded from: classes2.dex */
public final class zzbe {
    private boolean zza;
    private String zzb;

    private zzbe() {
    }

    public static zzbe zza(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Map zzb = zzaz.zzb(str);
        try {
            zzbe zzbeVar = new zzbe();
            Object obj = zzb.get("basicIntegrity");
            boolean z = false;
            if (obj != null && ((Boolean) obj).booleanValue()) {
                z = true;
            }
            zzbeVar.zza = z;
            String str2 = (String) zzb.get("advice");
            if (str2 == null) {
                str2 = "";
            }
            zzbeVar.zzb = str2;
            return zzbeVar;
        } catch (ClassCastException unused) {
            return null;
        }
    }

    public final String zzb() {
        return this.zzb;
    }

    public final boolean zzc() {
        return this.zza;
    }
}
