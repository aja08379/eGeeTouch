package com.google.android.gms.internal.p001firebaseauthapi;

import javax.annotation.CheckForNull;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzad  reason: invalid package */
/* loaded from: classes.dex */
abstract class zzad extends zzh {
    final CharSequence zzb;
    final zzn zzc;
    int zzd = 0;
    int zze;

    /* JADX INFO: Access modifiers changed from: protected */
    public zzad(zzaf zzafVar, CharSequence charSequence) {
        zzn zznVar;
        zznVar = zzafVar.zza;
        this.zzc = zznVar;
        this.zze = Integer.MAX_VALUE;
        this.zzb = charSequence;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzh
    @CheckForNull
    protected final /* bridge */ /* synthetic */ Object zza() {
        int zzc;
        int i = this.zzd;
        while (true) {
            int i2 = this.zzd;
            if (i2 == -1) {
                zzb();
                return null;
            }
            int zzd = zzd(i2);
            if (zzd == -1) {
                zzd = this.zzb.length();
                this.zzd = -1;
                zzc = -1;
            } else {
                zzc = zzc(zzd);
                this.zzd = zzc;
            }
            if (zzc == i) {
                int i3 = zzc + 1;
                this.zzd = i3;
                if (i3 > this.zzb.length()) {
                    this.zzd = -1;
                }
            } else {
                if (i < zzd) {
                    this.zzb.charAt(i);
                }
                if (i < zzd) {
                    this.zzb.charAt(zzd - 1);
                }
                int i4 = this.zze;
                if (i4 == 1) {
                    zzd = this.zzb.length();
                    this.zzd = -1;
                    if (zzd > i) {
                        this.zzb.charAt(zzd - 1);
                    }
                } else {
                    this.zze = i4 - 1;
                }
                return this.zzb.subSequence(i, zzd).toString();
            }
        }
    }

    abstract int zzc(int i);

    abstract int zzd(int i);
}
