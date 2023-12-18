package com.google.android.gms.internal.p001firebaseauthapi;

import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.annotation.CheckForNull;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzx  reason: invalid package */
/* loaded from: classes.dex */
final class zzx {
    private static final Logger zza = Logger.getLogger(zzx.class.getName());
    private static final zzw zzb = new zzw(null);

    private zzx() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzq zza(String str) {
        return new zzt(Pattern.compile("[.-]"));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String zzb(@CheckForNull String str) {
        return str == null ? "" : str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean zzc(@CheckForNull String str) {
        return str == null || str.isEmpty();
    }
}
