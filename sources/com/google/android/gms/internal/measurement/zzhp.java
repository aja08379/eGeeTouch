package com.google.android.gms.internal.measurement;
/* compiled from: com.google.android.gms:play-services-measurement-impl@@21.2.0 */
/* loaded from: classes.dex */
public final class zzhp {
    static volatile zzig zza = zzig.zzc();
    private static final Object zzb = new Object();

    /* JADX WARN: Can't wrap try/catch for region: R(11:18|(1:20)(8:33|(1:35)(1:41)|36|(2:38|(1:40))|27|28|29|30)|21|22|23|24|(1:26)|27|28|29|30) */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean zza(android.content.Context r5, android.net.Uri r6) {
        java.lang.String r6 = r6.getAuthority();
        boolean r1 = false;
        if (!"com.google.android.gms.phenotype".equals(r6)) {
            android.util.Log.e("PhenotypeClientHelper", java.lang.String.valueOf(r6).concat(" is an unsupported authority. Only com.google.android.gms.phenotype authority is supported."));
            return false;
        } else if (com.google.android.gms.internal.measurement.zzhp.zza.zzb()) {
            return ((java.lang.Boolean) com.google.android.gms.internal.measurement.zzhp.zza.zza()).booleanValue();
        } else {
            synchronized (com.google.android.gms.internal.measurement.zzhp.zzb) {
                if (com.google.android.gms.internal.measurement.zzhp.zza.zzb()) {
                    return ((java.lang.Boolean) com.google.android.gms.internal.measurement.zzhp.zza.zza()).booleanValue();
                }
                if (!"com.google.android.gms".equals(r5.getPackageName())) {
                    android.content.pm.ProviderInfo r0 = r5.getPackageManager().resolveContentProvider("com.google.android.gms.phenotype", android.os.Build.VERSION.SDK_INT < 29 ? 0 : 268435456);
                    if (r0 != null) {
                        if (!"com.google.android.gms".equals(r0.packageName)) {
                        }
                    }
                    com.google.android.gms.internal.measurement.zzhp.zza = com.google.android.gms.internal.measurement.zzig.zzd(java.lang.Boolean.valueOf(r1));
                    return ((java.lang.Boolean) com.google.android.gms.internal.measurement.zzhp.zza.zza()).booleanValue();
                }
                if ((r5.getPackageManager().getApplicationInfo("com.google.android.gms", 0).flags & com.egeetouch.egeetouch_manager.TaskManagement.restore_tag2) != 0) {
                    r1 = true;
                }
                com.google.android.gms.internal.measurement.zzhp.zza = com.google.android.gms.internal.measurement.zzig.zzd(java.lang.Boolean.valueOf(r1));
                return ((java.lang.Boolean) com.google.android.gms.internal.measurement.zzhp.zza.zza()).booleanValue();
            }
        }
    }
}
