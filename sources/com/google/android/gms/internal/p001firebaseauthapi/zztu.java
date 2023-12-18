package com.google.android.gms.internal.p001firebaseauthapi;

import android.text.TextUtils;
import com.google.firebase.auth.zze;
import java.util.List;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zztu  reason: invalid package */
/* loaded from: classes.dex */
public final class zztu implements zzyg {
    final /* synthetic */ zzyf zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ String zzc;
    final /* synthetic */ Boolean zzd;
    final /* synthetic */ zze zze;
    final /* synthetic */ zzxa zzf;
    final /* synthetic */ zzzy zzg;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zztu(zzvf zzvfVar, zzyf zzyfVar, String str, String str2, Boolean bool, zze zzeVar, zzxa zzxaVar, zzzy zzzyVar) {
        this.zza = zzyfVar;
        this.zzb = str;
        this.zzc = str2;
        this.zzd = bool;
        this.zze = zzeVar;
        this.zzf = zzxaVar;
        this.zzg = zzzyVar;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzyf
    public final void zza(String str) {
        this.zza.zza(str);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzyg
    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        List zzb = ((zzzp) obj).zzb();
        if (zzb == null || zzb.isEmpty()) {
            this.zza.zza("No users.");
            return;
        }
        int i = 0;
        zzzr zzzrVar = (zzzr) zzb.get(0);
        zzaag zzl = zzzrVar.zzl();
        List zzc = zzl != null ? zzl.zzc() : null;
        if (zzc != null && !zzc.isEmpty()) {
            if (TextUtils.isEmpty(this.zzb)) {
                ((zzaae) zzc.get(0)).zzh(this.zzc);
            } else {
                while (true) {
                    if (i >= zzc.size()) {
                        break;
                    } else if (((zzaae) zzc.get(i)).zzf().equals(this.zzb)) {
                        ((zzaae) zzc.get(i)).zzh(this.zzc);
                        break;
                    } else {
                        i++;
                    }
                }
            }
        }
        zzzrVar.zzh(this.zzd.booleanValue());
        zzzrVar.zze(this.zze);
        this.zzf.zzi(this.zzg, zzzrVar);
    }
}
