package com.google.android.gms.internal.vision;
/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.3 */
/* loaded from: classes2.dex */
public final class zzfe {
    private static final zzfd zza;
    private static final int zzb;

    public static void zza(Throwable th, Throwable th2) {
        zza.zza(th, th2);
    }

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.1.3 */
    /* loaded from: classes2.dex */
    static final class zza extends zzfd {
        zza() {
        }

        @Override // com.google.android.gms.internal.vision.zzfd
        public final void zza(Throwable th, Throwable th2) {
        }

        @Override // com.google.android.gms.internal.vision.zzfd
        public final void zza(Throwable th) {
            th.printStackTrace();
        }
    }

    public static void zza(Throwable th) {
        zza.zza(th);
    }

    private static Integer zza() {
        try {
            return (Integer) Class.forName("android.os.Build$VERSION").getField("SDK_INT").get(null);
        } catch (Exception e) {
            System.err.println("Failed to retrieve value from android.os.Build$VERSION.SDK_INT due to the following exception.");
            e.printStackTrace(System.err);
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x006b  */
    static {
        java.lang.Integer r1;
        com.google.android.gms.internal.vision.zzfd r2;
        try {
            r1 = zza();
        } catch (java.lang.Throwable th) {
            r2 = th;
            r1 = null;
        }
        if (r1 != null) {
            try {
            } catch (java.lang.Throwable th) {
                r2 = th;
                java.io.PrintStream r3 = java.lang.System.err;
                java.lang.String r4 = com.google.android.gms.internal.vision.zzfe.zza.class.getName();
                r3.println(new java.lang.StringBuilder(java.lang.String.valueOf(r4).length() + com.egeetouch.egeetouch_manager.TaskManagement.restore_user1).append("An error has occurred when initializing the try-with-resources desuguring strategy. The default strategy ").append(r4).append("will be used. The error is: ").toString());
                r2.printStackTrace(java.lang.System.err);
                r2 = new com.google.android.gms.internal.vision.zzfe.zza();
                com.google.android.gms.internal.vision.zzfe.zza = r2;
                com.google.android.gms.internal.vision.zzfe.zzb = r1 != null ? r1.intValue() : 1;
            }
            if (r1.intValue() >= 19) {
                r2 = new com.google.android.gms.internal.vision.zzfj();
                com.google.android.gms.internal.vision.zzfe.zza = r2;
                com.google.android.gms.internal.vision.zzfe.zzb = r1 != null ? r1.intValue() : 1;
            }
        }
        if (!java.lang.Boolean.getBoolean("com.google.devtools.build.android.desugar.runtime.twr_disable_mimic")) {
            r2 = new com.google.android.gms.internal.vision.zzfh();
        } else {
            r2 = new com.google.android.gms.internal.vision.zzfe.zza();
        }
        com.google.android.gms.internal.vision.zzfe.zza = r2;
        com.google.android.gms.internal.vision.zzfe.zzb = r1 != null ? r1.intValue() : 1;
    }
}
