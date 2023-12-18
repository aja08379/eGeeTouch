package com.google.android.gms.internal.p001firebaseauthapi;

import java.util.Iterator;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzafr  reason: invalid package */
/* loaded from: classes.dex */
final class zzafr implements Iterator {
    final Iterator zza;
    final /* synthetic */ zzafs zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzafr(zzafs zzafsVar) {
        zzads zzadsVar;
        this.zzb = zzafsVar;
        zzadsVar = zzafsVar.zza;
        this.zza = zzadsVar.iterator();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.zza.hasNext();
    }

    @Override // java.util.Iterator
    public final /* bridge */ /* synthetic */ Object next() {
        return (String) this.zza.next();
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
