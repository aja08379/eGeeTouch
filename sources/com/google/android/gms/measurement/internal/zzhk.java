package com.google.android.gms.measurement.internal;
/* compiled from: com.google.android.gms:play-services-measurement-impl@@21.2.0 */
/* loaded from: classes2.dex */
final class zzhk implements Runnable {
    final /* synthetic */ com.google.android.gms.internal.measurement.zzcf zza;
    final /* synthetic */ zzhx zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzhk(zzhx zzhxVar, com.google.android.gms.internal.measurement.zzcf zzcfVar) {
        this.zzb = zzhxVar;
        this.zza = zzcfVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x009a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void run() {
        java.lang.Long r0;
        com.google.android.gms.measurement.internal.zzkc r0 = r6.zzb.zzt.zzu();
        com.google.android.gms.internal.measurement.zzpa.zzc();
        if (!r0.zzt.zzf().zzs(null, com.google.android.gms.measurement.internal.zzdu.zzau)) {
            r0.zzt.zzay().zzl().zza("getSessionId has been disabled.");
        } else if (r0.zzt.zzm().zzc().zzi(com.google.android.gms.measurement.internal.zzah.ANALYTICS_STORAGE)) {
            if (!r0.zzt.zzm().zzk(r0.zzt.zzav().currentTimeMillis()) && r0.zzt.zzm().zzk.zza() != 0) {
                r0 = java.lang.Long.valueOf(r0.zzt.zzm().zzk.zza());
                if (r0 == null) {
                    r6.zzb.zzt.zzv().zzU(r6.zza, r0.longValue());
                    return;
                }
                try {
                    r6.zza.zze(null);
                    return;
                } catch (android.os.RemoteException r0) {
                    r6.zzb.zzt.zzay().zzd().zzb("getSessionId failed with exception", r0);
                    return;
                }
            }
        } else {
            r0.zzt.zzay().zzl().zza("Analytics storage consent denied; will not get session id");
        }
        r0 = null;
        if (r0 == null) {
        }
    }
}
