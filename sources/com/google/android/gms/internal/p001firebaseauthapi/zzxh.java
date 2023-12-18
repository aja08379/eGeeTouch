package com.google.android.gms.internal.p001firebaseauthapi;

import android.content.Context;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzxh  reason: invalid package */
/* loaded from: classes.dex */
public final class zzxh {
    private static Boolean zza;

    public static boolean zza(Context context) {
        if (zza == null) {
            int isGooglePlayServicesAvailable = GoogleApiAvailabilityLight.getInstance().isGooglePlayServicesAvailable(context, GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE);
            boolean z = true;
            if (isGooglePlayServicesAvailable != 0 && isGooglePlayServicesAvailable != 2) {
                z = false;
            }
            zza = Boolean.valueOf(z);
        }
        return zza.booleanValue();
    }
}
