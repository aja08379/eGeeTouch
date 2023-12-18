package com.google.android.gms.internal.p001firebaseauthapi;

import java.io.Serializable;
import java.util.Objects;
import java.util.regex.Pattern;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzt  reason: invalid package */
/* loaded from: classes.dex */
public final class zzt extends zzq implements Serializable {
    private final Pattern zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzt(Pattern pattern) {
        Objects.requireNonNull(pattern);
        this.zza = pattern;
    }

    public final String toString() {
        return this.zza.toString();
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzq
    public final zzp zza(CharSequence charSequence) {
        return new zzs(this.zza.matcher(charSequence));
    }
}
