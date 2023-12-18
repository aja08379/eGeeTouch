package com.google.android.gms.measurement.internal;

import android.net.Uri;
/* compiled from: com.google.android.gms:play-services-measurement-impl@@21.2.0 */
/* loaded from: classes2.dex */
final class zzhu implements Runnable {
    final /* synthetic */ boolean zza;
    final /* synthetic */ Uri zzb;
    final /* synthetic */ String zzc;
    final /* synthetic */ String zzd;
    final /* synthetic */ zzhw zze;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzhu(zzhw zzhwVar, boolean z, Uri uri, String str, String str2) {
        this.zze = zzhwVar;
        this.zza = z;
        this.zzb = uri;
        this.zzc = str;
        this.zzd = str2;
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x00d1  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00d3 A[Catch: RuntimeException -> 0x0164, TRY_LEAVE, TryCatch #0 {RuntimeException -> 0x0164, blocks: (B:3:0x0011, B:27:0x008a, B:29:0x0098, B:32:0x00a5, B:34:0x00ab, B:35:0x00bf, B:36:0x00cb, B:39:0x00d3, B:43:0x00fa, B:45:0x0118, B:44:0x0107, B:47:0x011f, B:49:0x0125, B:51:0x012b, B:53:0x0131, B:55:0x0137, B:57:0x013f, B:59:0x0147, B:61:0x014d, B:63:0x0154, B:7:0x002e, B:9:0x0034, B:11:0x003a, B:13:0x0040, B:15:0x0046, B:17:0x004e, B:19:0x0056, B:21:0x005e, B:22:0x006c, B:24:0x0080), top: B:68:0x0011 }] */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void run() {
        android.os.Bundle r6;
        android.os.Bundle r0;
        com.google.android.gms.measurement.internal.zzhw r2 = r17.zze;
        boolean r0 = r17.zza;
        android.net.Uri r3 = r17.zzb;
        java.lang.String r4 = r17.zzc;
        java.lang.String r5 = r17.zzd;
        r2.zza.zzg();
        try {
            com.google.android.gms.measurement.internal.zzlb r6 = r2.zza.zzt.zzv();
            if (!android.text.TextUtils.isEmpty(r5)) {
                if (r5.contains("gclid") || r5.contains("utm_campaign") || r5.contains("utm_source") || r5.contains("utm_medium") || r5.contains("utm_id") || r5.contains("dclid") || r5.contains("srsltid")) {
                    r6 = r6.zzs(android.net.Uri.parse("https://google.com/search?".concat(java.lang.String.valueOf(r5))));
                    if (r6 != null) {
                        r6.putString("_cis", "referrer");
                    }
                    if (r0 && (r0 = r2.zza.zzt.zzv().zzs(r3)) != null) {
                        r0.putString("_cis", "intent");
                        if (!r0.containsKey("gclid") && r6 != null && r6.containsKey("gclid")) {
                            r0.putString("_cer", java.lang.String.format("gclid=%s", r6.getString("gclid")));
                        }
                        r2.zza.zzG(r4, "_cmp", r0);
                        r2.zza.zzb.zza(r4, r0);
                    }
                    if (android.text.TextUtils.isEmpty(r5)) {
                        r2.zza.zzt.zzay().zzc().zzb("Activity created with referrer", r5);
                        if (r2.zza.zzt.zzf().zzs(null, com.google.android.gms.measurement.internal.zzdu.zzY)) {
                            if (r6 != null) {
                                r2.zza.zzG(r4, "_cmp", r6);
                                r2.zza.zzb.zza(r4, r6);
                            } else {
                                r2.zza.zzt.zzay().zzc().zzb("Referrer does not contain valid parameters", r5);
                            }
                            r2.zza.zzW("auto", "_ldl", null, true);
                            return;
                        } else if (!r5.contains("gclid") || (!r5.contains("utm_campaign") && !r5.contains("utm_source") && !r5.contains("utm_medium") && !r5.contains("utm_term") && !r5.contains("utm_content"))) {
                            r2.zza.zzt.zzay().zzc().zza("Activity created with data 'referrer' without required params");
                            return;
                        } else if (android.text.TextUtils.isEmpty(r5)) {
                            return;
                        } else {
                            r2.zza.zzW("auto", "_ldl", r5, true);
                            return;
                        }
                    }
                    return;
                }
                r6.zzt.zzay().zzc().zza("Activity created with data 'referrer' without required params");
            }
            r6 = null;
            if (r0) {
                r0.putString("_cis", "intent");
                if (!r0.containsKey("gclid")) {
                    r0.putString("_cer", java.lang.String.format("gclid=%s", r6.getString("gclid")));
                }
                r2.zza.zzG(r4, "_cmp", r0);
                r2.zza.zzb.zza(r4, r0);
            }
            if (android.text.TextUtils.isEmpty(r5)) {
            }
        } catch (java.lang.RuntimeException r0) {
            r2.zza.zzt.zzay().zzd().zzb("Throwable caught in handleReferrerForOnActivityCreated", r0);
        }
    }
}
