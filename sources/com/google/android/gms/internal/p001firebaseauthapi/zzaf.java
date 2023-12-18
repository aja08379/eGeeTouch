package com.google.android.gms.internal.p001firebaseauthapi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaf  reason: invalid package */
/* loaded from: classes.dex */
public final class zzaf {
    private final zzn zza;
    private final zzae zzb;

    private zzaf(zzae zzaeVar) {
        zzm zzmVar = zzm.zza;
        this.zzb = zzaeVar;
        this.zza = zzmVar;
    }

    public static zzaf zzb(char c) {
        return new zzaf(new zzaa(new zzk('.')));
    }

    public static zzaf zzc(String str) {
        zzq zza = zzx.zza("[.-]");
        if (!(!((zzs) zza.zza("")).zza.matches())) {
            throw new IllegalArgumentException(zzag.zzb("The pattern may not match the empty string: %s", zza));
        }
        return new zzaf(new zzac(zza));
    }

    public final List zzd(CharSequence charSequence) {
        Objects.requireNonNull(charSequence);
        Iterator zza = this.zzb.zza(this, charSequence);
        ArrayList arrayList = new ArrayList();
        while (zza.hasNext()) {
            arrayList.add((String) zza.next());
        }
        return Collections.unmodifiableList(arrayList);
    }
}
