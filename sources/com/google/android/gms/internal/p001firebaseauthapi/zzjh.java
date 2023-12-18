package com.google.android.gms.internal.p001firebaseauthapi;

import java.util.Arrays;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzjh  reason: invalid package */
/* loaded from: classes.dex */
public final class zzjh {
    private final zzbe zza;
    private final int zzb;
    private final zzbn zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzjh(zzbe zzbeVar, int i, zzbn zzbnVar, zzjg zzjgVar) {
        this.zza = zzbeVar;
        this.zzb = i;
        this.zzc = zzbnVar;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof zzjh) {
            zzjh zzjhVar = (zzjh) obj;
            return this.zza == zzjhVar.zza && this.zzb == zzjhVar.zzb && this.zzc.equals(zzjhVar.zzc);
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zza, Integer.valueOf(this.zzb), Integer.valueOf(this.zzc.hashCode())});
    }

    public final String toString() {
        return String.format("(status=%s, keyId=%s, parameters='%s')", this.zza, Integer.valueOf(this.zzb), this.zzc);
    }

    public final int zza() {
        return this.zzb;
    }
}
