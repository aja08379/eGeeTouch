package com.google.android.gms.internal.p001firebaseauthapi;

import java.security.GeneralSecurityException;
import java.security.Key;
import javax.crypto.Mac;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzqm  reason: invalid package */
/* loaded from: classes.dex */
public final class zzqm extends ThreadLocal {
    final /* synthetic */ zzqn zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzqm(zzqn zzqnVar) {
        this.zza = zzqnVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // java.lang.ThreadLocal
    /* renamed from: zza */
    public final Mac initialValue() {
        String str;
        Key key;
        try {
            zzpz zzpzVar = zzpz.zzb;
            str = this.zza.zzb;
            Mac mac = (Mac) zzpzVar.zza(str);
            key = this.zza.zzc;
            mac.init(key);
            return mac;
        } catch (GeneralSecurityException e) {
            throw new IllegalStateException(e);
        }
    }
}
