package com.google.android.gms.internal.p001firebaseauthapi;

import android.os.Build;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zza  reason: invalid package */
/* loaded from: classes.dex */
final class zza {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean zza() {
        return Build.VERSION.SDK_INT >= 33 || Build.VERSION.CODENAME.charAt(0) == 'T';
    }
}
