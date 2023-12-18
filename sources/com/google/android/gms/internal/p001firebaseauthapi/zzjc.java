package com.google.android.gms.internal.p001firebaseauthapi;

import java.util.Map;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzjc  reason: invalid package */
/* loaded from: classes.dex */
public final class zzjc {
    public static final zzjc zza = new zzja().zza();
    private final Map zzb;

    public final boolean equals(Object obj) {
        if (obj instanceof zzjc) {
            return this.zzb.equals(((zzjc) obj).zzb);
        }
        return false;
    }

    public final int hashCode() {
        return this.zzb.hashCode();
    }

    public final String toString() {
        return this.zzb.toString();
    }

    public final Map zza() {
        return this.zzb;
    }
}
