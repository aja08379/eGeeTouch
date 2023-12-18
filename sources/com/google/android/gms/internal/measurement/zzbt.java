package com.google.android.gms.internal.measurement;

import java.lang.reflect.Method;
/* compiled from: com.google.android.gms:play-services-measurement@@21.2.0 */
/* loaded from: classes.dex */
public final class zzbt {
    private static final Method zza;
    private static final Method zzb;

    /* JADX WARN: Removed duplicated region for block: B:22:0x003c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    static {
        java.lang.reflect.Method r0;
        java.lang.reflect.Method r5 = null;
        if (android.os.Build.VERSION.SDK_INT >= 24) {
            try {
                r0 = android.app.job.JobScheduler.class.getDeclaredMethod("scheduleAsPackage", android.app.job.JobInfo.class, java.lang.String.class, java.lang.Integer.TYPE, java.lang.String.class);
            } catch (java.lang.NoSuchMethodException unused) {
                if (android.util.Log.isLoggable("JobSchedulerCompat", 6)) {
                    android.util.Log.e("JobSchedulerCompat", "No scheduleAsPackage method available, falling back to schedule");
                }
            }
            com.google.android.gms.internal.measurement.zzbt.zza = r0;
            if (android.os.Build.VERSION.SDK_INT >= 24) {
                try {
                    r5 = android.os.UserHandle.class.getDeclaredMethod("myUserId", new java.lang.Class[0]);
                } catch (java.lang.NoSuchMethodException unused) {
                    if (android.util.Log.isLoggable("JobSchedulerCompat", 6)) {
                        android.util.Log.e("JobSchedulerCompat", "No myUserId method available");
                    }
                }
            }
            com.google.android.gms.internal.measurement.zzbt.zzb = r5;
        }
        r0 = null;
        com.google.android.gms.internal.measurement.zzbt.zza = r0;
        if (android.os.Build.VERSION.SDK_INT >= 24) {
        }
        com.google.android.gms.internal.measurement.zzbt.zzb = r5;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0048  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int zza(android.content.Context r5, android.app.job.JobInfo r6, java.lang.String r7, java.lang.String r8) {
        java.lang.Integer r5;
        int r5;
        java.lang.reflect.Method r0;
        android.app.job.JobScheduler r7 = (android.app.job.JobScheduler) r5.getSystemService("jobscheduler");
        java.util.Objects.requireNonNull(r7);
        if (com.google.android.gms.internal.measurement.zzbt.zza == null || r5.checkSelfPermission("android.permission.UPDATE_DEVICE_STATS") != 0) {
            return r7.schedule(r6);
        }
        java.lang.reflect.Method r5 = com.google.android.gms.internal.measurement.zzbt.zzb;
        if (r5 != null) {
            try {
                r5 = (java.lang.Integer) r5.invoke(android.os.UserHandle.class, new java.lang.Object[0]);
            } catch (java.lang.IllegalAccessException | java.lang.reflect.InvocationTargetException r5) {
                if (android.util.Log.isLoggable("JobSchedulerCompat", 6)) {
                    android.util.Log.e("JobSchedulerCompat", "myUserId invocation illegal", r5);
                }
            }
            if (r5 != null) {
                r5 = r5.intValue();
                r0 = com.google.android.gms.internal.measurement.zzbt.zza;
                if (r0 != null) {
                    try {
                        java.lang.Integer r5 = (java.lang.Integer) r0.invoke(r7, r6, "com.google.android.gms", java.lang.Integer.valueOf(r5), "UploadAlarm");
                        if (r5 != null) {
                            return r5.intValue();
                        }
                        return 0;
                    } catch (java.lang.IllegalAccessException | java.lang.reflect.InvocationTargetException r5) {
                        android.util.Log.e("UploadAlarm", "error calling scheduleAsPackage", r5);
                    }
                }
                return r7.schedule(r6);
            }
        }
        r5 = 0;
        r0 = com.google.android.gms.internal.measurement.zzbt.zza;
        if (r0 != null) {
        }
        return r7.schedule(r6);
    }
}
