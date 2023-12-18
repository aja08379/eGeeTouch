package com.google.android.gms.measurement.internal;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement-impl@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzij implements Runnable {
    final /* synthetic */ long zza;
    final /* synthetic */ zzim zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzij(zzim zzimVar, long j) {
        this.zzb = zzimVar;
        this.zza = j;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzb.zzt.zzd().zzf(this.zza);
        this.zzb.zza = null;
    }
}
