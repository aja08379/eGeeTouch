package com.google.android.gms.internal.p001firebaseauthapi;

import java.util.Arrays;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzhd  reason: invalid package */
/* loaded from: classes.dex */
final class zzhd {
    private final Class zza;
    private final zzqv zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzhd(Class cls, zzqv zzqvVar, zzhc zzhcVar) {
        this.zza = cls;
        this.zzb = zzqvVar;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof zzhd) {
            zzhd zzhdVar = (zzhd) obj;
            return zzhdVar.zza.equals(this.zza) && zzhdVar.zzb.equals(this.zzb);
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zza, this.zzb});
    }

    public final String toString() {
        String simpleName = this.zza.getSimpleName();
        String valueOf = String.valueOf(this.zzb);
        return simpleName + ", object identifier: " + valueOf;
    }
}
