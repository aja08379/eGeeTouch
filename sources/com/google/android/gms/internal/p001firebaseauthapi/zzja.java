package com.google.android.gms.internal.p001firebaseauthapi;

import java.util.Collections;
import java.util.HashMap;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzja  reason: invalid package */
/* loaded from: classes.dex */
public final class zzja {
    private HashMap zza = new HashMap();

    public final zzjc zza() {
        if (this.zza == null) {
            throw new IllegalStateException("cannot call build() twice");
        }
        zzjc zzjcVar = new zzjc(Collections.unmodifiableMap(this.zza), null);
        this.zza = null;
        return zzjcVar;
    }
}
