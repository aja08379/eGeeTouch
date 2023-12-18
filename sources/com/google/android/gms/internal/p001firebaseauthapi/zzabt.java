package com.google.android.gms.internal.p001firebaseauthapi;

import java.util.NoSuchElementException;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzabt  reason: invalid package */
/* loaded from: classes.dex */
final class zzabt extends zzabv {
    final /* synthetic */ zzacc zza;
    private int zzb = 0;
    private final int zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzabt(zzacc zzaccVar) {
        this.zza = zzaccVar;
        this.zzc = zzaccVar.zzd();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.zzb < this.zzc;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzabx
    public final byte zza() {
        int i = this.zzb;
        if (i >= this.zzc) {
            throw new NoSuchElementException();
        }
        this.zzb = i + 1;
        return this.zza.zzb(i);
    }
}
