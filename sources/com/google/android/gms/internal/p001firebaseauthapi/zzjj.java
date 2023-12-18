package com.google.android.gms.internal.p001firebaseauthapi;

import java.util.Arrays;
import java.util.List;
import javax.annotation.Nullable;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzjj  reason: invalid package */
/* loaded from: classes.dex */
public final class zzjj {
    private final zzjc zza;
    private final List zzb;
    @Nullable
    private final Integer zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzjj(zzjc zzjcVar, List list, Integer num, zzji zzjiVar) {
        this.zza = zzjcVar;
        this.zzb = list;
        this.zzc = num;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof zzjj) {
            zzjj zzjjVar = (zzjj) obj;
            if (this.zza.equals(zzjjVar.zza) && this.zzb.equals(zzjjVar.zzb)) {
                Integer num = this.zzc;
                Integer num2 = zzjjVar.zzc;
                if (num == num2) {
                    return true;
                }
                if (num != null && num.equals(num2)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zza, this.zzb});
    }

    public final String toString() {
        return String.format("(annotations=%s, entries=%s, primaryKeyId=%s)", this.zza, this.zzb, this.zzc);
    }
}
