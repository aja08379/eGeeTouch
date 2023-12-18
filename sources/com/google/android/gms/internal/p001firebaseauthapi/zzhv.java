package com.google.android.gms.internal.p001firebaseauthapi;

import java.util.Arrays;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzhv  reason: invalid package */
/* loaded from: classes.dex */
public final class zzhv extends zziv {
    private final int zza;
    private final int zzb;
    private final zzht zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzhv(int i, int i2, zzht zzhtVar, zzhu zzhuVar) {
        this.zza = i;
        this.zzb = i2;
        this.zzc = zzhtVar;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof zzhv) {
            zzhv zzhvVar = (zzhv) obj;
            return zzhvVar.zza == this.zza && zzhvVar.zzb() == zzb() && zzhvVar.zzc == this.zzc;
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.zzb), this.zzc});
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zzc);
        int i = this.zzb;
        int i2 = this.zza;
        return "AES-CMAC Parameters (variant: " + valueOf + ", " + i + "-byte tags, and " + i2 + "-byte key)";
    }

    public final int zza() {
        return this.zza;
    }

    public final int zzb() {
        zzht zzhtVar = this.zzc;
        if (zzhtVar == zzht.zzd) {
            return this.zzb;
        }
        if (zzhtVar == zzht.zza || zzhtVar == zzht.zzb || zzhtVar == zzht.zzc) {
            return this.zzb + 5;
        }
        throw new IllegalStateException("Unknown variant");
    }

    public final zzht zzc() {
        return this.zzc;
    }

    public final boolean zzd() {
        return this.zzc != zzht.zzd;
    }
}
