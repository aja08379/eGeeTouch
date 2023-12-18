package com.google.android.gms.internal.p001firebaseauthapi;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaes  reason: invalid package */
/* loaded from: classes.dex */
public final class zzaes {
    private static final zzaes zza = new zzaes();
    private final ConcurrentMap zzc = new ConcurrentHashMap();
    private final zzaex zzb = new zzaec();

    private zzaes() {
    }

    public static zzaes zza() {
        return zza;
    }

    public final zzaew zzb(Class cls) {
        zzadl.zzf(cls, "messageType");
        zzaew zzaewVar = (zzaew) this.zzc.get(cls);
        if (zzaewVar == null) {
            zzaewVar = this.zzb.zza(cls);
            zzadl.zzf(cls, "messageType");
            zzadl.zzf(zzaewVar, "schema");
            zzaew zzaewVar2 = (zzaew) this.zzc.putIfAbsent(cls, zzaewVar);
            if (zzaewVar2 != null) {
                return zzaewVar2;
            }
        }
        return zzaewVar;
    }
}
