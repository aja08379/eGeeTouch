package com.google.android.gms.internal.p001firebaseauthapi;

import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.LibraryVersion;
import java.util.List;
import kotlin.time.DurationKt;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzxo  reason: invalid package */
/* loaded from: classes.dex */
public final class zzxo {
    private final int zza;

    public zzxo(String str) {
        int i = -1;
        try {
            List zzd = zzaf.zzc("[.-]").zzd(str);
            if (zzd.size() == 1) {
                i = Integer.parseInt(str);
                str = str;
            } else {
                str = str;
                if (zzd.size() >= 3) {
                    int parseInt = (Integer.parseInt((String) zzd.get(0)) * DurationKt.NANOS_IN_MILLIS) + (Integer.parseInt((String) zzd.get(1)) * 1000);
                    int parseInt2 = Integer.parseInt((String) zzd.get(2));
                    i = parseInt + parseInt2;
                    str = parseInt2;
                }
            }
        } catch (IllegalArgumentException e) {
            if (Log.isLoggable("LibraryVersionContainer", 3)) {
                Log.d("LibraryVersionContainer", String.format("Version code parsing failed for: %s with exception %s.", str, e));
            }
        }
        this.zza = i;
    }

    public static zzxo zza() {
        String version = LibraryVersion.getInstance().getVersion("firebase-auth");
        return new zzxo((TextUtils.isEmpty(version) || version.equals("UNKNOWN")) ? "-1" : "-1");
    }

    public final String zzb() {
        return String.format("X%s", Integer.toString(this.zza));
    }
}
