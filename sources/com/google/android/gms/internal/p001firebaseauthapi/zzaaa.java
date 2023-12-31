package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Base64Utils;
import java.io.UnsupportedEncodingException;
import java.util.List;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaaa  reason: invalid package */
/* loaded from: classes.dex */
public final class zzaaa {
    public static long zza(String str) {
        Preconditions.checkNotEmpty(str);
        List zzd = zzaf.zzb('.').zzd(str);
        if (zzd.size() < 2) {
            throw new RuntimeException("Invalid idToken ".concat(String.valueOf(str)));
        }
        try {
            zzaab zza = zzaab.zza(new String(Base64Utils.decodeUrlSafeNoPadding((String) zzd.get(1)), "UTF-8"));
            return zza.zzb().longValue() - zza.zzc().longValue();
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Unable to decode token", e);
        }
    }
}
