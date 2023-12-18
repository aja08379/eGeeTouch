package com.google.android.gms.internal.p001firebaseauthapi;

import java.util.Arrays;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzin  reason: invalid package */
/* loaded from: classes.dex */
public final class zzin extends zziv {
    private final int zza;
    private final int zzb;
    private final zzil zzc;
    private final zzik zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzin(int i, int i2, zzil zzilVar, zzik zzikVar, zzim zzimVar) {
        this.zza = i;
        this.zzb = i2;
        this.zzc = zzilVar;
        this.zzd = zzikVar;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof zzin) {
            zzin zzinVar = (zzin) obj;
            return zzinVar.zza == this.zza && zzinVar.zzb() == zzb() && zzinVar.zzc == this.zzc && zzinVar.zzd == this.zzd;
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.zzb), this.zzc, this.zzd});
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zzc);
        String valueOf2 = String.valueOf(this.zzd);
        int i = this.zzb;
        int i2 = this.zza;
        return "HMAC Parameters (variant: " + valueOf + ", hashType: " + valueOf2 + ", " + i + "-byte tags, and " + i2 + "-byte key)";
    }

    public final int zza() {
        return this.zza;
    }

    public final int zzb() {
        zzil zzilVar = this.zzc;
        if (zzilVar == zzil.zzd) {
            return this.zzb;
        }
        if (zzilVar == zzil.zza || zzilVar == zzil.zzb || zzilVar == zzil.zzc) {
            return this.zzb + 5;
        }
        throw new IllegalStateException("Unknown variant");
    }

    public final zzil zzc() {
        return this.zzc;
    }

    public final boolean zzd() {
        return this.zzc != zzil.zzd;
    }
}
