package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement-impl@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzho implements Runnable {
    final /* synthetic */ AtomicReference zza;
    final /* synthetic */ zzhx zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzho(zzhx zzhxVar, AtomicReference atomicReference) {
        this.zzb = zzhxVar;
        this.zza = atomicReference;
    }

    @Override // java.lang.Runnable
    public final void run() {
        synchronized (this.zza) {
            this.zza.set(Integer.valueOf(this.zzb.zzt.zzf().zze(this.zzb.zzt.zzh().zzl(), zzdu.zzM)));
            this.zza.notify();
        }
    }
}
