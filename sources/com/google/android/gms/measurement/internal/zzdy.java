package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import java.math.BigInteger;
import java.util.List;
import java.util.Locale;
/* compiled from: com.google.android.gms:play-services-measurement-impl@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzdy extends zzf {
    private String zza;
    private String zzb;
    private int zzc;
    private String zzd;
    private String zze;
    private long zzf;
    private final long zzg;
    private List zzh;
    private String zzi;
    private int zzj;
    private String zzk;
    private String zzl;
    private String zzm;
    private long zzn;
    private String zzo;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzdy(zzfr zzfrVar, long j) {
        super(zzfrVar);
        this.zzn = 0L;
        this.zzo = null;
        this.zzg = j;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(20:1|(1:3)(6:64|65|(1:67)(2:82|(1:84))|68|69|(20:71|(1:73)(1:80)|75|76|5|(1:63)(1:9)|10|11|13|(1:15)|16|17|(1:19)|20|(3:22|(1:24)(1:26)|25)|(3:28|(1:30)(1:33)|31)|34|(3:36|(1:38)(3:45|(3:48|(1:50)|46)|51)|(2:40|41)(2:43|44))|52|(0)(0)))|4|5|(1:7)|63|10|11|13|(0)|16|17|(0)|20|(0)|(0)|34|(0)|52|(0)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x01db, code lost:
        r2 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x01dc, code lost:
        r11.zzt.zzay().zzd().zzc("Fetching Google App Id failed with exception. appId", com.google.android.gms.measurement.internal.zzeh.zzn(r0), r2);
     */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00b6  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00d0  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00e1  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00f1  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0101  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0111  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0121  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0131  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0141  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0151  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x016b  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x018b  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0194 A[Catch: IllegalStateException -> 0x01db, TryCatch #3 {IllegalStateException -> 0x01db, blocks: (B:46:0x0173, B:49:0x018c, B:51:0x0194, B:55:0x01b2, B:54:0x01ae, B:57:0x01bc, B:59:0x01d2, B:61:0x01d7, B:60:0x01d5), top: B:89:0x0173 }] */
    /* JADX WARN: Removed duplicated region for block: B:57:0x01bc A[Catch: IllegalStateException -> 0x01db, TryCatch #3 {IllegalStateException -> 0x01db, blocks: (B:46:0x0173, B:49:0x018c, B:51:0x0194, B:55:0x01b2, B:54:0x01ae, B:57:0x01bc, B:59:0x01d2, B:61:0x01d7, B:60:0x01d5), top: B:89:0x0173 }] */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0206  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x023f  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x024c  */
    @Override // com.google.android.gms.measurement.internal.zzf
    @org.checkerframework.checker.nullness.qual.EnsuresNonNull({com.google.firebase.remoteconfig.RemoteConfigConstants.RequestFieldKey.APP_ID, "appStore", "appName", "gmpAppId", "gaAppId"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected final void zzd() {
        java.lang.String r7;
        java.lang.String r8;
        android.content.pm.PackageInfo r7;
        java.lang.Object[] r2;
        int r6;
        java.util.List<java.lang.String> r0;
        java.lang.String r2;
        java.lang.String r0 = r11.zzt.zzau().getPackageName();
        android.content.pm.PackageManager r1 = r11.zzt.zzau().getPackageManager();
        java.lang.String r2 = com.facebook.internal.AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
        int r3 = Integer.MIN_VALUE;
        java.lang.String r6 = "unknown";
        if (r1 == null) {
            r11.zzt.zzay().zzd().zzb("PackageManager is null, app identity information might be inaccurate. appId", com.google.android.gms.measurement.internal.zzeh.zzn(r0));
        } else {
            try {
                r6 = r1.getInstallerPackageName(r0);
            } catch (java.lang.IllegalArgumentException unused) {
                r11.zzt.zzay().zzd().zzb("Error retrieving app installer package name. appId", com.google.android.gms.measurement.internal.zzeh.zzn(r0));
            }
            if (r6 == null) {
                r6 = "manual_install";
            } else if ("com.android.vending".equals(r6)) {
                r6 = "";
            }
            try {
                r7 = r1.getPackageInfo(r11.zzt.zzau().getPackageName(), 0);
            } catch (android.content.pm.PackageManager.NameNotFoundException unused) {
                r7 = com.facebook.internal.AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
            }
            if (r7 != null) {
                java.lang.CharSequence r8 = r1.getApplicationLabel(r7.applicationInfo);
                r8 = !android.text.TextUtils.isEmpty(r8) ? r8.toString() : com.facebook.internal.AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
                try {
                    r2 = r7.versionName;
                    r3 = r7.versionCode;
                } catch (android.content.pm.PackageManager.NameNotFoundException unused) {
                    r7 = r2;
                    r2 = r8;
                    r11.zzt.zzay().zzd().zzc("Error retrieving package info. appId, appName", com.google.android.gms.measurement.internal.zzeh.zzn(r0), r2);
                    r8 = r2;
                    r2 = r7;
                    r11.zza = r0;
                    r11.zzd = r6;
                    r11.zzb = r2;
                    r11.zzc = r3;
                    r11.zze = r8;
                    r11.zzf = 0L;
                    if (android.text.TextUtils.isEmpty(r11.zzt.zzw())) {
                    }
                    r6 = r11.zzt.zza();
                    switch (r6) {
                    }
                    r11.zzk = "";
                    r11.zzl = "";
                    r11.zzt.zzaw();
                    if (r2 != null) {
                    }
                    r2 = com.google.android.gms.measurement.internal.zzid.zzc(r11.zzt.zzau(), "google_app_id", r11.zzt.zzz());
                    r11.zzk = true != android.text.TextUtils.isEmpty(r2) ? r2 : "";
                    if (!android.text.TextUtils.isEmpty(r2)) {
                    }
                    if (r6 == 0) {
                    }
                    r11.zzh = null;
                    r11.zzt.zzaw();
                    r0 = r11.zzt.zzf().zzp("analytics.safelisted_events");
                    if (r0 != null) {
                    }
                    r11.zzh = r0;
                    if (r1 == null) {
                    }
                }
                r11.zza = r0;
                r11.zzd = r6;
                r11.zzb = r2;
                r11.zzc = r3;
                r11.zze = r8;
                r11.zzf = 0L;
                r2 = (android.text.TextUtils.isEmpty(r11.zzt.zzw()) && "am".equals(r11.zzt.zzx())) ? 1 : null;
                r6 = r11.zzt.zza();
                switch (r6) {
                    case 0:
                        r11.zzt.zzay().zzj().zza("App measurement collection enabled");
                        break;
                    case 1:
                        r11.zzt.zzay().zzi().zza("App measurement deactivated via the manifest");
                        break;
                    case 2:
                        r11.zzt.zzay().zzj().zza("App measurement deactivated via the init parameters");
                        break;
                    case 3:
                        r11.zzt.zzay().zzi().zza("App measurement disabled by setAnalyticsCollectionEnabled(false)");
                        break;
                    case 4:
                        r11.zzt.zzay().zzi().zza("App measurement disabled via the manifest");
                        break;
                    case 5:
                        r11.zzt.zzay().zzj().zza("App measurement disabled via the init parameters");
                        break;
                    case 6:
                        r11.zzt.zzay().zzl().zza("App measurement deactivated via resources. This method is being deprecated. Please refer to https://firebase.google.com/support/guides/disable-analytics");
                        break;
                    case 7:
                        r11.zzt.zzay().zzi().zza("App measurement disabled via the global data collection setting");
                        break;
                    default:
                        r11.zzt.zzay().zzi().zza("App measurement disabled due to denied storage consent");
                        break;
                }
                r11.zzk = "";
                r11.zzl = "";
                r11.zzt.zzaw();
                if (r2 != null) {
                    r11.zzl = r11.zzt.zzw();
                }
                r2 = com.google.android.gms.measurement.internal.zzid.zzc(r11.zzt.zzau(), "google_app_id", r11.zzt.zzz());
                r11.zzk = true != android.text.TextUtils.isEmpty(r2) ? r2 : "";
                if (!android.text.TextUtils.isEmpty(r2)) {
                    android.content.Context r2 = r11.zzt.zzau();
                    java.lang.String r3 = r11.zzt.zzz();
                    com.google.android.gms.common.internal.Preconditions.checkNotNull(r2);
                    android.content.res.Resources r4 = r2.getResources();
                    if (android.text.TextUtils.isEmpty(r3)) {
                        r3 = com.google.android.gms.measurement.internal.zzfj.zza(r2);
                    }
                    r11.zzl = com.google.android.gms.measurement.internal.zzfj.zzb("admob_app_id", r4, r3);
                }
                if (r6 == 0) {
                    r11.zzt.zzay().zzj().zzc("App measurement enabled for app package, google app id", r11.zza, android.text.TextUtils.isEmpty(r11.zzk) ? r11.zzl : r11.zzk);
                }
                r11.zzh = null;
                r11.zzt.zzaw();
                r0 = r11.zzt.zzf().zzp("analytics.safelisted_events");
                if (r0 != null) {
                    if (r0.isEmpty()) {
                        r11.zzt.zzay().zzl().zza("Safelisted event list is empty. Ignoring");
                    } else {
                        for (java.lang.String r3 : r0) {
                            if (!r11.zzt.zzv().zzab("safelisted event", r3)) {
                            }
                        }
                    }
                    if (r1 == null) {
                        r11.zzj = com.google.android.gms.common.wrappers.InstantApps.isInstantApp(r11.zzt.zzau()) ? 1 : 0;
                        return;
                    } else {
                        r11.zzj = 0;
                        return;
                    }
                }
                r11.zzh = r0;
                if (r1 == null) {
                }
            }
        }
        r8 = com.facebook.internal.AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
        r11.zza = r0;
        r11.zzd = r6;
        r11.zzb = r2;
        r11.zzc = r3;
        r11.zze = r8;
        r11.zzf = 0L;
        if (android.text.TextUtils.isEmpty(r11.zzt.zzw())) {
        }
        r6 = r11.zzt.zza();
        switch (r6) {
        }
        r11.zzk = "";
        r11.zzl = "";
        r11.zzt.zzaw();
        if (r2 != null) {
        }
        r2 = com.google.android.gms.measurement.internal.zzid.zzc(r11.zzt.zzau(), "google_app_id", r11.zzt.zzz());
        r11.zzk = true != android.text.TextUtils.isEmpty(r2) ? r2 : "";
        if (!android.text.TextUtils.isEmpty(r2)) {
        }
        if (r6 == 0) {
        }
        r11.zzh = null;
        r11.zzt.zzaw();
        r0 = r11.zzt.zzf().zzp("analytics.safelisted_events");
        if (r0 != null) {
        }
        r11.zzh = r0;
        if (r1 == null) {
        }
    }

    @Override // com.google.android.gms.measurement.internal.zzf
    protected final boolean zzf() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int zzh() {
        zza();
        return this.zzj;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int zzi() {
        zza();
        return this.zzc;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:42:0x018e  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0194  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x01d3  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x01d6  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x01fa  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x021f  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0237  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0272  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.google.android.gms.measurement.internal.zzq zzj(java.lang.String r37) {
        java.lang.String r20;
        java.lang.Class<?> r9;
        java.lang.Object r11;
        long r10;
        java.lang.String r12;
        long r22;
        java.lang.Boolean r2;
        java.lang.String r21;
        long r27;
        java.lang.String r16;
        java.lang.String r32;
        zzg();
        java.lang.String r3 = zzl();
        java.lang.String r4 = zzm();
        zza();
        java.lang.String r5 = r36.zzb;
        zza();
        long r6 = r36.zzc;
        zza();
        com.google.android.gms.common.internal.Preconditions.checkNotNull(r36.zzd);
        java.lang.String r8 = r36.zzd;
        r36.zzt.zzf().zzh();
        zza();
        zzg();
        long r9 = r36.zzf;
        if (r9 == 0) {
            com.google.android.gms.measurement.internal.zzlb r9 = r36.zzt.zzv();
            android.content.Context r0 = r36.zzt.zzau();
            java.lang.String r10 = r36.zzt.zzau().getPackageName();
            r9.zzg();
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r0);
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r10);
            android.content.pm.PackageManager r13 = r0.getPackageManager();
            java.security.MessageDigest r14 = com.google.android.gms.measurement.internal.zzlb.zzF();
            long r15 = -1;
            if (r14 == null) {
                r9.zzt.zzay().zzd().zza("Could not get MD5 instance");
            } else {
                if (r13 != null) {
                    try {
                        if (r9.zzag(r0, r10)) {
                            r15 = 0;
                            r9 = r9;
                        } else {
                            android.content.pm.PackageInfo r0 = com.google.android.gms.common.wrappers.Wrappers.packageManager(r0).getPackageInfo(r9.zzt.zzau().getPackageName(), 64);
                            if (r0.signatures == null || r0.signatures.length <= 0) {
                                r9.zzt.zzay().zzk().zza("Could not get signatures");
                                r9 = r9;
                            } else {
                                long r9 = com.google.android.gms.measurement.internal.zzlb.zzp(r14.digest(r0.signatures[0].toByteArray()));
                                r15 = r9;
                                r9 = r9;
                            }
                        }
                    } catch (android.content.pm.PackageManager.NameNotFoundException r0) {
                        r9.zzt.zzay().zzd().zzb("Package name not found", r0);
                    }
                }
                r9 = 0;
                r36.zzf = r9;
            }
            r9 = r15;
            r36.zzf = r9;
        }
        long r13 = r9;
        boolean r0 = r36.zzt.zzJ();
        boolean r15 = !r36.zzt.zzm().zzl;
        zzg();
        if (r36.zzt.zzJ()) {
            com.google.android.gms.internal.measurement.zzpj.zzc();
            if (!r36.zzt.zzf().zzs(null, com.google.android.gms.measurement.internal.zzdu.zzaa)) {
                try {
                    r9 = r36.zzt.zzau().getClassLoader().loadClass("com.google.firebase.analytics.FirebaseAnalytics");
                } catch (java.lang.ClassNotFoundException unused) {
                }
                if (r9 != null) {
                    try {
                        r11 = r9.getDeclaredMethod("getInstance", android.content.Context.class).invoke(null, r36.zzt.zzau());
                    } catch (java.lang.Exception unused) {
                        r36.zzt.zzay().zzm().zza("Failed to obtain Firebase Analytics instance");
                    }
                    if (r11 != null) {
                        try {
                            r20 = (java.lang.String) r9.getDeclaredMethod("getFirebaseInstanceId", new java.lang.Class[0]).invoke(r11, new java.lang.Object[0]);
                        } catch (java.lang.Exception unused) {
                            r36.zzt.zzay().zzl().zza("Failed to retrieve Firebase Instance Id");
                        }
                        com.google.android.gms.measurement.internal.zzfr r9 = r36.zzt;
                        r10 = r9.zzm().zzc.zza();
                        if (r10 == 0) {
                            r12 = r3;
                            r22 = r9.zzc;
                        } else {
                            r12 = r3;
                            r22 = java.lang.Math.min(r9.zzc, r10);
                        }
                        zza();
                        int r11 = r36.zzj;
                        boolean r24 = r36.zzt.zzf().zzr();
                        com.google.android.gms.measurement.internal.zzew r2 = r36.zzt.zzm();
                        r2.zzg();
                        boolean r25 = r2.zza().getBoolean("deferred_analytics_collection", false);
                        zza();
                        java.lang.String r3 = r36.zzl;
                        java.lang.Boolean r26 = r36.zzt.zzf().zzk("google_analytics_default_allow_ad_personalization_signals") == null ? null : java.lang.Boolean.valueOf(!r2.booleanValue());
                        long r9 = r36.zzg;
                        java.util.List r2 = r36.zzh;
                        java.lang.String r30 = r36.zzt.zzm().zzc().zzh();
                        if (r36.zzi == null) {
                            r21 = r3;
                            r27 = r9;
                            if (r36.zzt.zzf().zzs(null, com.google.android.gms.measurement.internal.zzdu.zzap)) {
                                r36.zzi = r36.zzt.zzv().zzC();
                            } else {
                                r36.zzi = "";
                            }
                        } else {
                            r21 = r3;
                            r27 = r9;
                        }
                        java.lang.String r3 = r36.zzi;
                        com.google.android.gms.internal.measurement.zzpd.zzc();
                        if (r36.zzt.zzf().zzs(null, com.google.android.gms.measurement.internal.zzdu.zzam)) {
                            zzg();
                            if (r36.zzn == 0) {
                                r16 = r3;
                            } else {
                                r16 = r3;
                                long r9 = r36.zzt.zzav().currentTimeMillis() - r36.zzn;
                                if (r36.zzm != null && r9 > 86400000 && r36.zzo == null) {
                                    zzo();
                                }
                            }
                            if (r36.zzm == null) {
                                zzo();
                            }
                            r32 = r36.zzm;
                        } else {
                            r16 = r3;
                            r32 = null;
                        }
                        return new com.google.android.gms.measurement.internal.zzq(r12, r4, r5, r6, r8, 74029L, r13, r37, r0, r15, r20, 0L, r22, r11, r24, r25, r21, r26, r27, r2, (java.lang.String) null, r30, r16, r32);
                    }
                    r20 = null;
                    com.google.android.gms.measurement.internal.zzfr r9 = r36.zzt;
                    r10 = r9.zzm().zzc.zza();
                    if (r10 == 0) {
                    }
                    zza();
                    int r11 = r36.zzj;
                    boolean r24 = r36.zzt.zzf().zzr();
                    com.google.android.gms.measurement.internal.zzew r2 = r36.zzt.zzm();
                    r2.zzg();
                    boolean r25 = r2.zza().getBoolean("deferred_analytics_collection", false);
                    zza();
                    java.lang.String r3 = r36.zzl;
                    if (r36.zzt.zzf().zzk("google_analytics_default_allow_ad_personalization_signals") == null) {
                    }
                    long r9 = r36.zzg;
                    java.util.List r2 = r36.zzh;
                    java.lang.String r30 = r36.zzt.zzm().zzc().zzh();
                    if (r36.zzi == null) {
                    }
                    java.lang.String r3 = r36.zzi;
                    com.google.android.gms.internal.measurement.zzpd.zzc();
                    if (r36.zzt.zzf().zzs(null, com.google.android.gms.measurement.internal.zzdu.zzam)) {
                    }
                    return new com.google.android.gms.measurement.internal.zzq(r12, r4, r5, r6, r8, 74029L, r13, r37, r0, r15, r20, 0L, r22, r11, r24, r25, r21, r26, r27, r2, (java.lang.String) null, r30, r16, r32);
                }
            } else {
                r36.zzt.zzay().zzj().zza("Disabled IID for tests.");
            }
        }
        r20 = null;
        com.google.android.gms.measurement.internal.zzfr r9 = r36.zzt;
        r10 = r9.zzm().zzc.zza();
        if (r10 == 0) {
        }
        zza();
        int r11 = r36.zzj;
        boolean r24 = r36.zzt.zzf().zzr();
        com.google.android.gms.measurement.internal.zzew r2 = r36.zzt.zzm();
        r2.zzg();
        boolean r25 = r2.zza().getBoolean("deferred_analytics_collection", false);
        zza();
        java.lang.String r3 = r36.zzl;
        if (r36.zzt.zzf().zzk("google_analytics_default_allow_ad_personalization_signals") == null) {
        }
        long r9 = r36.zzg;
        java.util.List r2 = r36.zzh;
        java.lang.String r30 = r36.zzt.zzm().zzc().zzh();
        if (r36.zzi == null) {
        }
        java.lang.String r3 = r36.zzi;
        com.google.android.gms.internal.measurement.zzpd.zzc();
        if (r36.zzt.zzf().zzs(null, com.google.android.gms.measurement.internal.zzdu.zzam)) {
        }
        return new com.google.android.gms.measurement.internal.zzq(r12, r4, r5, r6, r8, 74029L, r13, r37, r0, r15, r20, 0L, r22, r11, r24, r25, r21, r26, r27, r2, (java.lang.String) null, r30, r16, r32);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String zzk() {
        zza();
        return this.zzl;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String zzl() {
        zza();
        Preconditions.checkNotNull(this.zza);
        return this.zza;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String zzm() {
        zzg();
        zza();
        Preconditions.checkNotNull(this.zzk);
        return this.zzk;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final List zzn() {
        return this.zzh;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzo() {
        String format;
        zzg();
        if (!this.zzt.zzm().zzc().zzi(zzah.ANALYTICS_STORAGE)) {
            this.zzt.zzay().zzc().zza("Analytics Storage consent is not granted");
            format = null;
        } else {
            byte[] bArr = new byte[16];
            this.zzt.zzv().zzG().nextBytes(bArr);
            format = String.format(Locale.US, "%032x", new BigInteger(1, bArr));
        }
        zzef zzc = this.zzt.zzay().zzc();
        Object[] objArr = new Object[1];
        objArr[0] = format == null ? "null" : "not null";
        zzc.zza(String.format("Resetting session stitching token to %s", objArr));
        this.zzm = format;
        this.zzn = this.zzt.zzav().currentTimeMillis();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean zzp(String str) {
        String str2 = this.zzo;
        boolean z = false;
        if (str2 != null && !str2.equals(str)) {
            z = true;
        }
        this.zzo = str;
        return z;
    }
}
