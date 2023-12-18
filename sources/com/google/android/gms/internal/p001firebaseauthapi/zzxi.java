package com.google.android.gms.internal.p001firebaseauthapi;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzxi  reason: invalid package */
/* loaded from: classes.dex */
public class zzxi {
    final String zza;
    final zzxq zzb;

    public zzxi(String str, zzxq zzxqVar) {
        this.zza = str;
        this.zzb = zzxqVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String zza(String str, String str2) {
        String str3 = this.zza;
        return str3 + str + "?key=" + str2;
    }
}
