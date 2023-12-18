package com.google.firebase.auth.internal;

import com.google.android.gms.common.logging.Logger;
import com.google.android.gms.internal.p001firebaseauthapi.zzqx;
import com.google.firebase.auth.GetTokenResult;
import java.util.HashMap;
import java.util.Map;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* loaded from: classes2.dex */
public final class zzay {
    private static final Logger zza = new Logger("GetTokenResultFactory", new String[0]);

    public static GetTokenResult zza(String str) {
        Map hashMap;
        try {
            hashMap = zzaz.zzb(str);
        } catch (zzqx e) {
            zza.e("Error parsing token claims", e, new Object[0]);
            hashMap = new HashMap();
        }
        return new GetTokenResult(str, hashMap);
    }
}
