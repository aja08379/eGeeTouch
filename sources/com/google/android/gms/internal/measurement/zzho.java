package com.google.android.gms.internal.measurement;
/* compiled from: com.google.android.gms:play-services-measurement-impl@@21.2.0 */
/* loaded from: classes.dex */
public final class zzho {
    private static volatile zzig zza;

    private zzho() {
    }

    /* JADX WARN: Can't wrap try/catch for region: R(16:6|(3:10|11|12)|18|(1:22)|23|24|25|26|27|28|(1:30)(1:80)|31|(10:33|34|35|36|37|38|(2:39|(3:41|(3:56|57|58)(7:43|44|(2:46|(1:49))|50|(1:52)|53|54)|55)(1:59))|60|61|62)(1:79)|63|11|12) */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0068, code lost:
        r3 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0069, code lost:
        android.util.Log.e("HermeticFileOverrides", "no data dir", r3);
        r3 = com.google.android.gms.internal.measurement.zzig.zzc();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.google.android.gms.internal.measurement.zzig zza(android.content.Context r14) {
        com.google.android.gms.internal.measurement.zzig r1;
        com.google.android.gms.internal.measurement.zzig r14;
        synchronized (com.google.android.gms.internal.measurement.zzho.class) {
            r1 = com.google.android.gms.internal.measurement.zzho.zza;
            if (r1 == null) {
                java.lang.String r1 = android.os.Build.TYPE;
                java.lang.String r2 = android.os.Build.TAGS;
                if ((!r1.equals("eng") && !r1.equals("userdebug")) || (!r2.contains("dev-keys") && !r2.contains("test-keys"))) {
                    r14 = com.google.android.gms.internal.measurement.zzig.zzc();
                    r1 = r14;
                    com.google.android.gms.internal.measurement.zzho.zza = r1;
                }
                if (com.google.android.gms.internal.measurement.zzhb.zzb() && !r14.isDeviceProtectedStorage()) {
                    r14 = r14.createDeviceProtectedStorageContext();
                }
                android.os.StrictMode.ThreadPolicy r1 = android.os.StrictMode.allowThreadDiskReads();
                android.os.StrictMode.allowThreadDiskWrites();
                java.io.File r3 = new java.io.File(r14.getDir("phenotype_hermetic", 0), "overrides.txt");
                com.google.android.gms.internal.measurement.zzig r3 = r3.exists() ? com.google.android.gms.internal.measurement.zzig.zzd(r3) : com.google.android.gms.internal.measurement.zzig.zzc();
                if (r3.zzb()) {
                    java.io.File r3 = (java.io.File) r3.zza();
                    try {
                        java.io.BufferedReader r4 = new java.io.BufferedReader(new java.io.InputStreamReader(new java.io.FileInputStream(r3)));
                        try {
                            androidx.collection.SimpleArrayMap r6 = new androidx.collection.SimpleArrayMap();
                            java.util.HashMap r7 = new java.util.HashMap();
                            while (true) {
                                java.lang.String r8 = r4.readLine();
                                if (r8 == null) {
                                    break;
                                }
                                java.lang.String[] r9 = r8.split(" ", 3);
                                if (r9.length != 3) {
                                    android.util.Log.e("HermeticFileOverrides", "Invalid: " + r8);
                                } else {
                                    java.lang.String r10 = new java.lang.String(r9[0]);
                                    java.lang.String r8 = android.net.Uri.decode(new java.lang.String(r9[1]));
                                    java.lang.String r12 = (java.lang.String) r7.get(r9[2]);
                                    if (r12 == null) {
                                        java.lang.String r11 = new java.lang.String(r9[2]);
                                        r12 = android.net.Uri.decode(r11);
                                        if (r12.length() < 1024 || r12 == r11) {
                                            r7.put(r11, r12);
                                        }
                                    }
                                    if (!r6.containsKey(r10)) {
                                        r6.put(r10, new androidx.collection.SimpleArrayMap());
                                    }
                                    ((androidx.collection.SimpleArrayMap) r6.get(r10)).put(r8, r12);
                                }
                            }
                            java.lang.String r3 = r3.toString();
                            java.lang.String r14 = r14.getPackageName();
                            android.util.Log.w("HermeticFileOverrides", "Parsed " + r3 + " for Android package " + r14);
                            com.google.android.gms.internal.measurement.zzhh r14 = new com.google.android.gms.internal.measurement.zzhh(r6);
                            r4.close();
                            r14 = com.google.android.gms.internal.measurement.zzig.zzd(r14);
                        } catch (java.lang.Throwable r14) {
                            try {
                                r4.close();
                            } catch (java.lang.Throwable r3) {
                                try {
                                    java.lang.Throwable.class.getDeclaredMethod("addSuppressed", java.lang.Throwable.class).invoke(r14, r3);
                                } catch (java.lang.Exception unused) {
                                }
                            }
                            throw r14;
                        }
                    } catch (java.io.IOException r14) {
                        throw new java.lang.RuntimeException(r14);
                    }
                } else {
                    r14 = com.google.android.gms.internal.measurement.zzig.zzc();
                }
                android.os.StrictMode.setThreadPolicy(r1);
                r1 = r14;
                com.google.android.gms.internal.measurement.zzho.zza = r1;
            }
        }
        return r1;
    }
}
