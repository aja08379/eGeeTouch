package com.google.android.gms.internal.p001firebaseauthapi;

import java.util.Collections;
import java.util.List;
import java.util.Map;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaez  reason: invalid package */
/* loaded from: classes.dex */
final class zzaez extends zzafj {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzaez(int i) {
        super(i, null);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzafj
    public final void zza() {
        if (!zzj()) {
            for (int i = 0; i < zzb(); i++) {
                Map.Entry zzg = zzg(i);
                if (((zzacw) zzg.getKey()).zzc()) {
                    zzg.setValue(Collections.unmodifiableList((List) zzg.getValue()));
                }
            }
            for (Map.Entry entry : zzc()) {
                if (((zzacw) entry.getKey()).zzc()) {
                    entry.setValue(Collections.unmodifiableList((List) entry.getValue()));
                }
            }
        }
        super.zza();
    }
}
