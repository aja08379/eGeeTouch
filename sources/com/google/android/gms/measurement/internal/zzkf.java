package com.google.android.gms.measurement.internal;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.PersistableBundle;
import androidx.core.app.NotificationCompat;
import com.facebook.internal.NativeProtocol;
/* compiled from: com.google.android.gms:play-services-measurement@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzkf extends zzkh {
    private final AlarmManager zza;
    private zzap zzb;
    private Integer zzc;

    /* JADX INFO: Access modifiers changed from: protected */
    public zzkf(zzkt zzktVar) {
        super(zzktVar);
        this.zza = (AlarmManager) this.zzt.zzau().getSystemService(NotificationCompat.CATEGORY_ALARM);
    }

    private final int zzf() {
        if (this.zzc == null) {
            this.zzc = Integer.valueOf("measurement".concat(String.valueOf(this.zzt.zzau().getPackageName())).hashCode());
        }
        return this.zzc.intValue();
    }

    private final PendingIntent zzh() {
        Context zzau = this.zzt.zzau();
        return PendingIntent.getBroadcast(zzau, 0, new Intent().setClassName(zzau, "com.google.android.gms.measurement.AppMeasurementReceiver").setAction("com.google.android.gms.measurement.UPLOAD"), com.google.android.gms.internal.measurement.zzbs.zza);
    }

    private final zzap zzi() {
        if (this.zzb == null) {
            this.zzb = new zzke(this, this.zzf.zzq());
        }
        return this.zzb;
    }

    private final void zzj() {
        JobScheduler jobScheduler = (JobScheduler) this.zzt.zzau().getSystemService("jobscheduler");
        if (jobScheduler != null) {
            jobScheduler.cancel(zzf());
        }
    }

    public final void zza() {
        zzW();
        this.zzt.zzay().zzj().zza("Unscheduling upload");
        AlarmManager alarmManager = this.zza;
        if (alarmManager != null) {
            alarmManager.cancel(zzh());
        }
        zzi().zzb();
        if (Build.VERSION.SDK_INT >= 24) {
            zzj();
        }
    }

    @Override // com.google.android.gms.measurement.internal.zzkh
    protected final boolean zzb() {
        AlarmManager alarmManager = this.zza;
        if (alarmManager != null) {
            alarmManager.cancel(zzh());
        }
        if (Build.VERSION.SDK_INT >= 24) {
            zzj();
            return false;
        }
        return false;
    }

    public final void zzd(long j) {
        zzW();
        this.zzt.zzaw();
        Context zzau = this.zzt.zzau();
        if (!zzlb.zzaj(zzau)) {
            this.zzt.zzay().zzc().zza("Receiver not registered/enabled");
        }
        if (!zzlb.zzak(zzau, false)) {
            this.zzt.zzay().zzc().zza("Service not registered/enabled");
        }
        zza();
        this.zzt.zzay().zzj().zzb("Scheduling upload, millis", Long.valueOf(j));
        long elapsedRealtime = this.zzt.zzav().elapsedRealtime() + j;
        this.zzt.zzf();
        if (j < Math.max(0L, ((Long) zzdu.zzw.zza(null)).longValue()) && !zzi().zze()) {
            zzi().zzd(j);
        }
        this.zzt.zzaw();
        if (Build.VERSION.SDK_INT < 24) {
            AlarmManager alarmManager = this.zza;
            if (alarmManager != null) {
                this.zzt.zzf();
                alarmManager.setInexactRepeating(2, elapsedRealtime, Math.max(((Long) zzdu.zzr.zza(null)).longValue(), j), zzh());
                return;
            }
            return;
        }
        Context zzau2 = this.zzt.zzau();
        ComponentName componentName = new ComponentName(zzau2, "com.google.android.gms.measurement.AppMeasurementJobService");
        int zzf = zzf();
        PersistableBundle persistableBundle = new PersistableBundle();
        persistableBundle.putString(NativeProtocol.WEB_DIALOG_ACTION, "com.google.android.gms.measurement.UPLOAD");
        com.google.android.gms.internal.measurement.zzbt.zza(zzau2, new JobInfo.Builder(zzf, componentName).setMinimumLatency(j).setOverrideDeadline(j + j).setExtras(persistableBundle).build(), "com.google.android.gms", "UploadAlarm");
    }
}
