package com.google.android.gms.internal.p001firebaseauthapi;

import java.util.Iterator;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaa  reason: invalid package */
/* loaded from: classes.dex */
public final class zzaa implements zzae {
    final /* synthetic */ zzn zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzaa(zzn zznVar) {
        this.zza = zznVar;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzae
    public final /* synthetic */ Iterator zza(zzaf zzafVar, CharSequence charSequence) {
        return new zzz(this, zzafVar, charSequence);
    }
}
