package com.google.android.gms.internal.p001firebaseauthapi;

import java.util.List;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzadw  reason: invalid package */
/* loaded from: classes.dex */
final class zzadw extends zzady {
    private zzadw() {
        super(null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzadw(zzadv zzadvVar) {
        super(null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzady
    public final List zza(Object obj, long j) {
        zzadk zzadkVar = (zzadk) zzafx.zzf(obj, j);
        if (zzadkVar.zzc()) {
            return zzadkVar;
        }
        int size = zzadkVar.size();
        zzadk zzd = zzadkVar.zzd(size == 0 ? 10 : size + size);
        zzafx.zzs(obj, j, zzd);
        return zzd;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzady
    public final void zzb(Object obj, long j) {
        ((zzadk) zzafx.zzf(obj, j)).zzb();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzady
    public final void zzc(Object obj, Object obj2, long j) {
        zzadk zzadkVar = (zzadk) zzafx.zzf(obj, j);
        zzadk zzadkVar2 = (zzadk) zzafx.zzf(obj2, j);
        int size = zzadkVar.size();
        int size2 = zzadkVar2.size();
        if (size > 0 && size2 > 0) {
            if (!zzadkVar.zzc()) {
                zzadkVar = zzadkVar.zzd(size2 + size);
            }
            zzadkVar.addAll(zzadkVar2);
        }
        if (size > 0) {
            zzadkVar2 = zzadkVar;
        }
        zzafx.zzs(obj, j, zzadkVar2);
    }
}
