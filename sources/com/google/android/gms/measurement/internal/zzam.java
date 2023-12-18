package com.google.android.gms.measurement.internal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import androidx.exifinterface.media.ExifInterface;
import com.facebook.share.internal.ShareConstants;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zznt;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.database.core.ServerValues;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzam extends zzkh {
    private static final String[] zza = {"last_bundled_timestamp", "ALTER TABLE events ADD COLUMN last_bundled_timestamp INTEGER;", "last_bundled_day", "ALTER TABLE events ADD COLUMN last_bundled_day INTEGER;", "last_sampled_complex_event_id", "ALTER TABLE events ADD COLUMN last_sampled_complex_event_id INTEGER;", "last_sampling_rate", "ALTER TABLE events ADD COLUMN last_sampling_rate INTEGER;", "last_exempt_from_sampling", "ALTER TABLE events ADD COLUMN last_exempt_from_sampling INTEGER;", "current_session_count", "ALTER TABLE events ADD COLUMN current_session_count INTEGER;"};
    private static final String[] zzb = {"origin", "ALTER TABLE user_attributes ADD COLUMN origin TEXT;"};
    private static final String[] zzc = {"app_version", "ALTER TABLE apps ADD COLUMN app_version TEXT;", "app_store", "ALTER TABLE apps ADD COLUMN app_store TEXT;", "gmp_version", "ALTER TABLE apps ADD COLUMN gmp_version INTEGER;", "dev_cert_hash", "ALTER TABLE apps ADD COLUMN dev_cert_hash INTEGER;", "measurement_enabled", "ALTER TABLE apps ADD COLUMN measurement_enabled INTEGER;", "last_bundle_start_timestamp", "ALTER TABLE apps ADD COLUMN last_bundle_start_timestamp INTEGER;", "day", "ALTER TABLE apps ADD COLUMN day INTEGER;", "daily_public_events_count", "ALTER TABLE apps ADD COLUMN daily_public_events_count INTEGER;", "daily_events_count", "ALTER TABLE apps ADD COLUMN daily_events_count INTEGER;", "daily_conversions_count", "ALTER TABLE apps ADD COLUMN daily_conversions_count INTEGER;", "remote_config", "ALTER TABLE apps ADD COLUMN remote_config BLOB;", "config_fetched_time", "ALTER TABLE apps ADD COLUMN config_fetched_time INTEGER;", "failed_config_fetch_time", "ALTER TABLE apps ADD COLUMN failed_config_fetch_time INTEGER;", "app_version_int", "ALTER TABLE apps ADD COLUMN app_version_int INTEGER;", "firebase_instance_id", "ALTER TABLE apps ADD COLUMN firebase_instance_id TEXT;", "daily_error_events_count", "ALTER TABLE apps ADD COLUMN daily_error_events_count INTEGER;", "daily_realtime_events_count", "ALTER TABLE apps ADD COLUMN daily_realtime_events_count INTEGER;", "health_monitor_sample", "ALTER TABLE apps ADD COLUMN health_monitor_sample TEXT;", "android_id", "ALTER TABLE apps ADD COLUMN android_id INTEGER;", "adid_reporting_enabled", "ALTER TABLE apps ADD COLUMN adid_reporting_enabled INTEGER;", "ssaid_reporting_enabled", "ALTER TABLE apps ADD COLUMN ssaid_reporting_enabled INTEGER;", "admob_app_id", "ALTER TABLE apps ADD COLUMN admob_app_id TEXT;", "linked_admob_app_id", "ALTER TABLE apps ADD COLUMN linked_admob_app_id TEXT;", "dynamite_version", "ALTER TABLE apps ADD COLUMN dynamite_version INTEGER;", "safelisted_events", "ALTER TABLE apps ADD COLUMN safelisted_events TEXT;", "ga_app_id", "ALTER TABLE apps ADD COLUMN ga_app_id TEXT;", "config_last_modified_time", "ALTER TABLE apps ADD COLUMN config_last_modified_time TEXT;", "e_tag", "ALTER TABLE apps ADD COLUMN e_tag TEXT;", "session_stitching_token", "ALTER TABLE apps ADD COLUMN session_stitching_token TEXT;"};
    private static final String[] zzd = {"realtime", "ALTER TABLE raw_events ADD COLUMN realtime INTEGER;"};
    private static final String[] zze = {"has_realtime", "ALTER TABLE queue ADD COLUMN has_realtime INTEGER;", "retry_count", "ALTER TABLE queue ADD COLUMN retry_count INTEGER;"};
    private static final String[] zzg = {"session_scoped", "ALTER TABLE event_filters ADD COLUMN session_scoped BOOLEAN;"};
    private static final String[] zzh = {"session_scoped", "ALTER TABLE property_filters ADD COLUMN session_scoped BOOLEAN;"};
    private static final String[] zzi = {"previous_install_count", "ALTER TABLE app2 ADD COLUMN previous_install_count INTEGER;"};
    private final zzal zzj;
    private final zzkd zzk;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzam(zzkt zzktVar) {
        super(zzktVar);
        this.zzk = new zzkd(this.zzt.zzav());
        this.zzt.zzf();
        this.zzj = new zzal(this, this.zzt.zzau(), "google_app_measurement.db");
    }

    static final void zzV(ContentValues contentValues, String str, Object obj) {
        Preconditions.checkNotEmpty("value");
        Preconditions.checkNotNull(obj);
        if (obj instanceof String) {
            contentValues.put("value", (String) obj);
        } else if (obj instanceof Long) {
            contentValues.put("value", (Long) obj);
        } else if (obj instanceof Double) {
            contentValues.put("value", (Double) obj);
        } else {
            throw new IllegalArgumentException("Invalid value type");
        }
    }

    private final long zzZ(String str, String[] strArr) {
        Cursor cursor = null;
        try {
            try {
                Cursor rawQuery = zzh().rawQuery(str, strArr);
                if (rawQuery.moveToFirst()) {
                    long j = rawQuery.getLong(0);
                    if (rawQuery != null) {
                        rawQuery.close();
                    }
                    return j;
                }
                throw new SQLiteException("Database returned empty set");
            } catch (SQLiteException e) {
                this.zzt.zzay().zzd().zzc("Database error", str, e);
                throw e;
            }
        } catch (Throwable th) {
            if (0 != 0) {
                cursor.close();
            }
            throw th;
        }
    }

    private final long zzaa(String str, String[] strArr, long j) {
        Cursor cursor = null;
        try {
            try {
                cursor = zzh().rawQuery(str, strArr);
                if (cursor.moveToFirst()) {
                    return cursor.getLong(0);
                }
                if (cursor != null) {
                    cursor.close();
                }
                return j;
            } catch (SQLiteException e) {
                this.zzt.zzay().zzd().zzc("Database error", str, e);
                throw e;
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public final void zzA(String str, String str2) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzg();
        zzW();
        try {
            zzh().delete("user_attributes", "app_id=? and name=?", new String[]{str, str2});
        } catch (SQLiteException e) {
            this.zzt.zzay().zzd().zzd("Error deleting user property. appId", zzeh.zzn(str), this.zzt.zzj().zzf(str2), e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:100:0x0347, code lost:
        if (zzh().insertWithOnConflict("property_filters", null, r11, 5) != (-1)) goto L121;
     */
    /* JADX WARN: Code restructure failed: missing block: B:101:0x0349, code lost:
        r23.zzt.zzay().zzd().zzb("Failed to insert property filter (got -1). appId", com.google.android.gms.measurement.internal.zzeh.zzn(r24));
     */
    /* JADX WARN: Code restructure failed: missing block: B:103:0x035d, code lost:
        r0 = r22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:104:0x0361, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:105:0x0362, code lost:
        r23.zzt.zzay().zzd().zzc("Error storing property filter. appId", com.google.android.gms.measurement.internal.zzeh.zzn(r24), r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:106:0x0375, code lost:
        zzW();
        zzg();
        com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r24);
        r0 = zzh();
        r3 = r17;
        r0.delete("property_filters", r3, new java.lang.String[]{r24, java.lang.String.valueOf(r10)});
        r0.delete("event_filters", r3, new java.lang.String[]{r24, java.lang.String.valueOf(r10)});
        r17 = r3;
        r7 = r21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:107:0x03ac, code lost:
        r7 = r21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x017a, code lost:
        r11 = r0.zzh().iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x0186, code lost:
        if (r11.hasNext() == false) goto L71;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x0192, code lost:
        if (((com.google.android.gms.internal.measurement.zzet) r11.next()).zzj() != false) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x0194, code lost:
        r23.zzt.zzay().zzk().zzc("Property filter with no ID. Audience definition ignored. appId, audienceId", com.google.android.gms.measurement.internal.zzeh.zzn(r24), java.lang.Integer.valueOf(r10));
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x01ad, code lost:
        r11 = r0.zzg().iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x01c3, code lost:
        if (r11.hasNext() == false) goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x01c5, code lost:
        r12 = (com.google.android.gms.internal.measurement.zzek) r11.next();
        zzW();
        zzg();
        com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r24);
        com.google.android.gms.common.internal.Preconditions.checkNotNull(r12);
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x01df, code lost:
        if (r12.zzg().isEmpty() == false) goto L78;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x01e1, code lost:
        r0 = r23.zzt.zzay().zzk();
        r9 = com.google.android.gms.measurement.internal.zzeh.zzn(r24);
        r11 = java.lang.Integer.valueOf(r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x01f9, code lost:
        if (r12.zzp() == false) goto L104;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x01fb, code lost:
        r20 = java.lang.Integer.valueOf(r12.zzb());
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x0206, code lost:
        r20 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x0208, code lost:
        r0.zzd("Event filter had no event name. Audience definition ignored. appId, audienceId, filterId", r9, r11, java.lang.String.valueOf(r20));
        r21 = r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x0213, code lost:
        r3 = r12.zzbu();
        r21 = r7;
        r7 = new android.content.ContentValues();
        r7.put("app_id", r24);
        r7.put("audience_id", java.lang.Integer.valueOf(r10));
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x022c, code lost:
        if (r12.zzp() == false) goto L98;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x022e, code lost:
        r9 = java.lang.Integer.valueOf(r12.zzb());
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x0237, code lost:
        r9 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x0238, code lost:
        r7.put("filter_id", r9);
        r7.put(bolts.MeasurementEvent.MEASUREMENT_EVENT_NAME_KEY, r12.zzg());
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x0248, code lost:
        if (r12.zzq() == false) goto L97;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x024a, code lost:
        r9 = java.lang.Boolean.valueOf(r12.zzn());
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x0253, code lost:
        r9 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x0254, code lost:
        r7.put("session_scoped", r9);
        r7.put(com.facebook.share.internal.ShareConstants.WEB_DIALOG_PARAM_DATA, r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x0268, code lost:
        if (zzh().insertWithOnConflict("event_filters", null, r7, 5) != (-1)) goto L91;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x026a, code lost:
        r23.zzt.zzay().zzd().zzb("Failed to insert event filter (got -1). appId", com.google.android.gms.measurement.internal.zzeh.zzn(r24));
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x027d, code lost:
        r7 = r21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x0283, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x0284, code lost:
        r23.zzt.zzay().zzd().zzc("Error storing event filter. appId", com.google.android.gms.measurement.internal.zzeh.zzn(r24), r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x0299, code lost:
        r21 = r7;
        r0 = r0.zzh().iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x02a7, code lost:
        if (r0.hasNext() == false) goto L135;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x02a9, code lost:
        r3 = (com.google.android.gms.internal.measurement.zzet) r0.next();
        zzW();
        zzg();
        com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r24);
        com.google.android.gms.common.internal.Preconditions.checkNotNull(r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x02c3, code lost:
        if (r3.zze().isEmpty() == false) goto L111;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x02c5, code lost:
        r0 = r23.zzt.zzay().zzk();
        r8 = com.google.android.gms.measurement.internal.zzeh.zzn(r24);
        r9 = java.lang.Integer.valueOf(r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x02dd, code lost:
        if (r3.zzj() == false) goto L134;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x02df, code lost:
        r3 = java.lang.Integer.valueOf(r3.zza());
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x02e8, code lost:
        r3 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x02e9, code lost:
        r0.zzd("Property filter had no property name. Audience definition ignored. appId, audienceId, filterId", r8, r9, java.lang.String.valueOf(r3));
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x02f2, code lost:
        r7 = r3.zzbu();
        r11 = new android.content.ContentValues();
        r11.put("app_id", r24);
        r11.put("audience_id", java.lang.Integer.valueOf(r10));
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x0309, code lost:
        if (r3.zzj() == false) goto L128;
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x030b, code lost:
        r12 = java.lang.Integer.valueOf(r3.zza());
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x0314, code lost:
        r12 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x0315, code lost:
        r11.put("filter_id", r12);
        r22 = r0;
        r11.put("property_name", r3.zze());
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x0327, code lost:
        if (r3.zzk() == false) goto L127;
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x0329, code lost:
        r0 = java.lang.Boolean.valueOf(r3.zzi());
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x0332, code lost:
        r0 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x0333, code lost:
        r11.put("session_scoped", r0);
        r11.put(com.facebook.share.internal.ShareConstants.WEB_DIALOG_PARAM_DATA, r7);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void zzB(java.lang.String r24, java.util.List r25) {
        boolean r14;
        java.lang.String r4 = "app_id=? and audience_id=?";
        com.google.android.gms.common.internal.Preconditions.checkNotNull(r25);
        int r8 = 0;
        while (r8 < r25.size()) {
            com.google.android.gms.internal.measurement.zzeh r9 = (com.google.android.gms.internal.measurement.zzeh) ((com.google.android.gms.internal.measurement.zzei) r25.get(r8)).zzby();
            if (r9.zza() != 0) {
                int r11 = 0;
                while (r11 < r9.zza()) {
                    com.google.android.gms.internal.measurement.zzej r12 = (com.google.android.gms.internal.measurement.zzej) r9.zze(r11).zzby();
                    com.google.android.gms.internal.measurement.zzej r13 = (com.google.android.gms.internal.measurement.zzej) r12.zzau();
                    java.lang.String r14 = com.google.android.gms.measurement.internal.zzgo.zzb(r12.zze());
                    if (r14 != null) {
                        r13.zzb(r14);
                        r14 = true;
                    } else {
                        r14 = false;
                    }
                    int r15 = 0;
                    while (r15 < r12.zza()) {
                        com.google.android.gms.internal.measurement.zzem r7 = r12.zzd(r15);
                        com.google.android.gms.internal.measurement.zzej r16 = r12;
                        java.lang.String r17 = r4;
                        java.lang.String r4 = com.google.android.gms.measurement.internal.zzid.zzb(r7.zze(), com.google.android.gms.measurement.internal.zzgp.zza, com.google.android.gms.measurement.internal.zzgp.zzb);
                        if (r4 != null) {
                            com.google.android.gms.internal.measurement.zzel r7 = (com.google.android.gms.internal.measurement.zzel) r7.zzby();
                            r7.zza(r4);
                            r13.zzc(r15, (com.google.android.gms.internal.measurement.zzem) r7.zzaC());
                            r14 = true;
                        }
                        r15++;
                        r12 = r16;
                        r4 = r17;
                    }
                    java.lang.String r17 = r4;
                    if (r14) {
                        r9.zzc(r11, r13);
                        r25.set(r8, (com.google.android.gms.internal.measurement.zzei) r9.zzaC());
                    }
                    r11++;
                    r4 = r17;
                }
            }
            java.lang.String r17 = r4;
            if (r9.zzb() != 0) {
                for (int r4 = 0; r4 < r9.zzb(); r4++) {
                    com.google.android.gms.internal.measurement.zzet r7 = r9.zzf(r4);
                    java.lang.String r10 = com.google.android.gms.measurement.internal.zzid.zzb(r7.zze(), com.google.android.gms.measurement.internal.zzgq.zza, com.google.android.gms.measurement.internal.zzgq.zzb);
                    if (r10 != null) {
                        com.google.android.gms.internal.measurement.zzes r7 = (com.google.android.gms.internal.measurement.zzes) r7.zzby();
                        r7.zza(r10);
                        r9.zzd(r4, r7);
                        r25.set(r8, (com.google.android.gms.internal.measurement.zzei) r9.zzaC());
                    }
                }
            }
            r8++;
            r4 = r17;
        }
        java.lang.String r17 = r4;
        zzW();
        zzg();
        com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r24);
        com.google.android.gms.common.internal.Preconditions.checkNotNull(r25);
        android.database.sqlite.SQLiteDatabase r4 = zzh();
        r4.beginTransaction();
        try {
            zzW();
            zzg();
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r24);
            android.database.sqlite.SQLiteDatabase r7 = zzh();
            r7.delete("property_filters", "app_id=?", new java.lang.String[]{r24});
            r7.delete("event_filters", "app_id=?", new java.lang.String[]{r24});
            java.util.Iterator r7 = r25.iterator();
            while (r7.hasNext()) {
                com.google.android.gms.internal.measurement.zzei r0 = (com.google.android.gms.internal.measurement.zzei) r7.next();
                zzW();
                zzg();
                com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r24);
                com.google.android.gms.common.internal.Preconditions.checkNotNull(r0);
                if (!r0.zzk()) {
                    r23.zzt.zzay().zzk().zzb("Audience with no ID. appId", com.google.android.gms.measurement.internal.zzeh.zzn(r24));
                } else {
                    int r10 = r0.zza();
                    java.util.Iterator r11 = r0.zzg().iterator();
                    while (true) {
                        if (r11.hasNext()) {
                            if (!((com.google.android.gms.internal.measurement.zzek) r11.next()).zzp()) {
                                r23.zzt.zzay().zzk().zzc("Event filter with no ID. Audience definition ignored. appId, audienceId", com.google.android.gms.measurement.internal.zzeh.zzn(r24), java.lang.Integer.valueOf(r10));
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                }
            }
            java.util.ArrayList r0 = new java.util.ArrayList();
            java.util.Iterator r5 = r25.iterator();
            while (r5.hasNext()) {
                com.google.android.gms.internal.measurement.zzei r6 = (com.google.android.gms.internal.measurement.zzei) r5.next();
                r0.add(r6.zzk() ? java.lang.Integer.valueOf(r6.zza()) : null);
            }
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r24);
            zzW();
            zzg();
            android.database.sqlite.SQLiteDatabase r3 = zzh();
            try {
                long r5 = zzZ("select count(1) from audience_filter_values where app_id=?", new java.lang.String[]{r24});
                int r7 = java.lang.Math.max(0, java.lang.Math.min(2000, r23.zzt.zzf().zze(r24, com.google.android.gms.measurement.internal.zzdu.zzE)));
                if (r5 > r7) {
                    java.util.ArrayList r5 = new java.util.ArrayList();
                    int r10 = 0;
                    while (true) {
                        if (r10 < r0.size()) {
                            java.lang.Integer r6 = (java.lang.Integer) r0.get(r10);
                            if (r6 == null) {
                                break;
                            }
                            r5.add(java.lang.Integer.toString(r6.intValue()));
                            r10++;
                        } else {
                            java.lang.String r0 = android.text.TextUtils.join(",", r5);
                            java.lang.StringBuilder r0 = new java.lang.StringBuilder();
                            r0.append("audience_id in (select audience_id from audience_filter_values where app_id=? and audience_id not in ");
                            r0.append("(" + r0 + ")");
                            r0.append(" order by rowid desc limit -1 offset ?)");
                            r3.delete("audience_filter_values", r0.toString(), new java.lang.String[]{r24, java.lang.Integer.toString(r7)});
                            break;
                        }
                    }
                }
            } catch (android.database.sqlite.SQLiteException r0) {
                r23.zzt.zzay().zzd().zzc("Database error querying filters. appId", com.google.android.gms.measurement.internal.zzeh.zzn(r24), r0);
            }
            r4.setTransactionSuccessful();
        } finally {
            r4.endTransaction();
        }
    }

    public final void zzC() {
        zzW();
        zzh().setTransactionSuccessful();
    }

    public final void zzD(zzh zzhVar) {
        Preconditions.checkNotNull(zzhVar);
        zzg();
        zzW();
        String zzt = zzhVar.zzt();
        Preconditions.checkNotNull(zzt);
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzt);
        contentValues.put("app_instance_id", zzhVar.zzu());
        contentValues.put("gmp_app_id", zzhVar.zzy());
        contentValues.put("resettable_device_id_hash", zzhVar.zzA());
        contentValues.put("last_bundle_index", Long.valueOf(zzhVar.zzo()));
        contentValues.put("last_bundle_start_timestamp", Long.valueOf(zzhVar.zzp()));
        contentValues.put("last_bundle_end_timestamp", Long.valueOf(zzhVar.zzn()));
        contentValues.put("app_version", zzhVar.zzw());
        contentValues.put("app_store", zzhVar.zzv());
        contentValues.put("gmp_version", Long.valueOf(zzhVar.zzm()));
        contentValues.put("dev_cert_hash", Long.valueOf(zzhVar.zzj()));
        contentValues.put("measurement_enabled", Boolean.valueOf(zzhVar.zzai()));
        contentValues.put("day", Long.valueOf(zzhVar.zzi()));
        contentValues.put("daily_public_events_count", Long.valueOf(zzhVar.zzg()));
        contentValues.put("daily_events_count", Long.valueOf(zzhVar.zzf()));
        contentValues.put("daily_conversions_count", Long.valueOf(zzhVar.zzd()));
        contentValues.put("config_fetched_time", Long.valueOf(zzhVar.zzc()));
        contentValues.put("failed_config_fetch_time", Long.valueOf(zzhVar.zzl()));
        contentValues.put("app_version_int", Long.valueOf(zzhVar.zzb()));
        contentValues.put("firebase_instance_id", zzhVar.zzx());
        contentValues.put("daily_error_events_count", Long.valueOf(zzhVar.zze()));
        contentValues.put("daily_realtime_events_count", Long.valueOf(zzhVar.zzh()));
        contentValues.put("health_monitor_sample", zzhVar.zzz());
        zzhVar.zza();
        contentValues.put("android_id", (Long) 0L);
        contentValues.put("adid_reporting_enabled", Boolean.valueOf(zzhVar.zzah()));
        contentValues.put("admob_app_id", zzhVar.zzr());
        contentValues.put("dynamite_version", Long.valueOf(zzhVar.zzk()));
        contentValues.put("session_stitching_token", zzhVar.zzB());
        List zzC = zzhVar.zzC();
        if (zzC != null) {
            if (zzC.isEmpty()) {
                this.zzt.zzay().zzk().zzb("Safelisted events should not be an empty list. appId", zzt);
            } else {
                contentValues.put("safelisted_events", TextUtils.join(",", zzC));
            }
        }
        zznt.zzc();
        if (this.zzt.zzf().zzs(null, zzdu.zzai) && !contentValues.containsKey("safelisted_events")) {
            contentValues.put("safelisted_events", (String) null);
        }
        try {
            SQLiteDatabase zzh2 = zzh();
            if (zzh2.update("apps", contentValues, "app_id = ?", new String[]{zzt}) == 0 && zzh2.insertWithOnConflict("apps", null, contentValues, 5) == -1) {
                this.zzt.zzay().zzd().zzb("Failed to insert/update app (got -1). appId", zzeh.zzn(zzt));
            }
        } catch (SQLiteException e) {
            this.zzt.zzay().zzd().zzc("Error storing app. appId", zzeh.zzn(zzt), e);
        }
    }

    public final void zzE(zzas zzasVar) {
        Preconditions.checkNotNull(zzasVar);
        zzg();
        zzW();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzasVar.zza);
        contentValues.put("name", zzasVar.zzb);
        contentValues.put("lifetime_count", Long.valueOf(zzasVar.zzc));
        contentValues.put("current_bundle_count", Long.valueOf(zzasVar.zzd));
        contentValues.put("last_fire_timestamp", Long.valueOf(zzasVar.zzf));
        contentValues.put("last_bundled_timestamp", Long.valueOf(zzasVar.zzg));
        contentValues.put("last_bundled_day", zzasVar.zzh);
        contentValues.put("last_sampled_complex_event_id", zzasVar.zzi);
        contentValues.put("last_sampling_rate", zzasVar.zzj);
        contentValues.put("current_session_count", Long.valueOf(zzasVar.zze));
        Boolean bool = zzasVar.zzk;
        contentValues.put("last_exempt_from_sampling", (bool == null || !bool.booleanValue()) ? null : 1L);
        try {
            if (zzh().insertWithOnConflict("events", null, contentValues, 5) == -1) {
                this.zzt.zzay().zzd().zzb("Failed to insert/update event aggregates (got -1). appId", zzeh.zzn(zzasVar.zza));
            }
        } catch (SQLiteException e) {
            this.zzt.zzay().zzd().zzc("Error storing event aggregates. appId", zzeh.zzn(zzasVar.zza), e);
        }
    }

    public final boolean zzF() {
        return zzZ("select count(1) > 0 from raw_events", null) != 0;
    }

    public final boolean zzG() {
        return zzZ("select count(1) > 0 from queue where has_realtime = 1", null) != 0;
    }

    public final boolean zzH() {
        return zzZ("select count(1) > 0 from raw_events where realtime = 1", null) != 0;
    }

    protected final boolean zzI() {
        Context zzau = this.zzt.zzau();
        this.zzt.zzf();
        return zzau.getDatabasePath("google_app_measurement.db").exists();
    }

    public final boolean zzJ(String str, Long l, long j, com.google.android.gms.internal.measurement.zzft zzftVar) {
        zzg();
        zzW();
        Preconditions.checkNotNull(zzftVar);
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(l);
        byte[] zzbu = zzftVar.zzbu();
        this.zzt.zzay().zzj().zzc("Saving complex main event, appId, data size", this.zzt.zzj().zzd(str), Integer.valueOf(zzbu.length));
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("event_id", l);
        contentValues.put("children_to_process", Long.valueOf(j));
        contentValues.put("main_event", zzbu);
        try {
            if (zzh().insertWithOnConflict("main_event_params", null, contentValues, 5) == -1) {
                this.zzt.zzay().zzd().zzb("Failed to insert complex main event (got -1). appId", zzeh.zzn(str));
                return false;
            }
            return true;
        } catch (SQLiteException e) {
            this.zzt.zzay().zzd().zzc("Error storing complex main event. appId", zzeh.zzn(str), e);
            return false;
        }
    }

    public final boolean zzK(zzac zzacVar) {
        Preconditions.checkNotNull(zzacVar);
        zzg();
        zzW();
        String str = zzacVar.zza;
        Preconditions.checkNotNull(str);
        if (zzp(str, zzacVar.zzc.zzb) == null) {
            long zzZ = zzZ("SELECT COUNT(1) FROM conditional_properties WHERE app_id=?", new String[]{str});
            this.zzt.zzf();
            if (zzZ >= 1000) {
                return false;
            }
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("origin", zzacVar.zzb);
        contentValues.put("name", zzacVar.zzc.zzb);
        zzV(contentValues, "value", Preconditions.checkNotNull(zzacVar.zzc.zza()));
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.ACTIVE, Boolean.valueOf(zzacVar.zze));
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME, zzacVar.zzf);
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT, Long.valueOf(zzacVar.zzh));
        contentValues.put("timed_out_event", this.zzt.zzv().zzan(zzacVar.zzg));
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP, Long.valueOf(zzacVar.zzd));
        contentValues.put("triggered_event", this.zzt.zzv().zzan(zzacVar.zzi));
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_TIMESTAMP, Long.valueOf(zzacVar.zzc.zzc));
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE, Long.valueOf(zzacVar.zzj));
        contentValues.put("expired_event", this.zzt.zzv().zzan(zzacVar.zzk));
        try {
            if (zzh().insertWithOnConflict("conditional_properties", null, contentValues, 5) == -1) {
                this.zzt.zzay().zzd().zzb("Failed to insert/update conditional user property (got -1)", zzeh.zzn(str));
            }
        } catch (SQLiteException e) {
            this.zzt.zzay().zzd().zzc("Error storing conditional user property", zzeh.zzn(str), e);
        }
        return true;
    }

    public final boolean zzL(zzky zzkyVar) {
        Preconditions.checkNotNull(zzkyVar);
        zzg();
        zzW();
        if (zzp(zzkyVar.zza, zzkyVar.zzc) == null) {
            if (zzlb.zzai(zzkyVar.zzc)) {
                if (zzZ("select count(1) from user_attributes where app_id=? and name not like '!_%' escape '!'", new String[]{zzkyVar.zza}) >= this.zzt.zzf().zzf(zzkyVar.zza, zzdu.zzF, 25, 100)) {
                    return false;
                }
            } else if (!"_npa".equals(zzkyVar.zzc)) {
                long zzZ = zzZ("select count(1) from user_attributes where app_id=? and origin=? AND name like '!_%' escape '!'", new String[]{zzkyVar.zza, zzkyVar.zzb});
                this.zzt.zzf();
                if (zzZ >= 25) {
                    return false;
                }
            }
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzkyVar.zza);
        contentValues.put("origin", zzkyVar.zzb);
        contentValues.put("name", zzkyVar.zzc);
        contentValues.put("set_timestamp", Long.valueOf(zzkyVar.zzd));
        zzV(contentValues, "value", zzkyVar.zze);
        try {
            if (zzh().insertWithOnConflict("user_attributes", null, contentValues, 5) == -1) {
                this.zzt.zzay().zzd().zzb("Failed to insert/update user property (got -1). appId", zzeh.zzn(zzkyVar.zza));
            }
        } catch (SQLiteException e) {
            this.zzt.zzay().zzd().zzc("Error storing user property. appId", zzeh.zzn(zzkyVar.zza), e);
        }
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Not initialized variable reg: 4, insn: 0x0235: MOVE  (r3 I:??[OBJECT, ARRAY]) = (r4 I:??[OBJECT, ARRAY]), block:B:105:0x0235 */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v12 */
    /* JADX WARN: Type inference failed for: r4v2, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r4v3, types: [boolean] */
    /* JADX WARN: Type inference failed for: r4v9 */
    public final void zzU(String str, long j, long j2, zzkq zzkqVar) {
        ?? r4;
        Cursor cursor;
        Cursor rawQuery;
        String string;
        int i;
        String str2;
        String[] strArr;
        Preconditions.checkNotNull(zzkqVar);
        zzg();
        zzW();
        Cursor cursor2 = null;
        r3 = null;
        r3 = null;
        String str3 = null;
        try {
            try {
                SQLiteDatabase zzh2 = zzh();
                r4 = TextUtils.isEmpty(null);
                try {
                    if (r4 != 0) {
                        int i2 = (j2 > (-1L) ? 1 : (j2 == (-1L) ? 0 : -1));
                        rawQuery = zzh2.rawQuery("select app_id, metadata_fingerprint from raw_events where " + (i2 != 0 ? "rowid <= ? and " : "") + "app_id in (select app_id from apps where config_fetched_time >= ?) order by rowid limit 1;", i2 != 0 ? new String[]{String.valueOf(j2), String.valueOf(j)} : new String[]{String.valueOf(j)});
                        if (!rawQuery.moveToFirst()) {
                            if (rawQuery != null) {
                                rawQuery.close();
                                return;
                            }
                            return;
                        }
                        str3 = rawQuery.getString(0);
                        string = rawQuery.getString(1);
                        rawQuery.close();
                    } else {
                        int i3 = (j2 > (-1L) ? 1 : (j2 == (-1L) ? 0 : -1));
                        rawQuery = zzh2.rawQuery("select metadata_fingerprint from raw_events where app_id = ?" + (i3 != 0 ? " and rowid <= ?" : "") + " order by rowid limit 1;", i3 != 0 ? new String[]{null, String.valueOf(j2)} : new String[]{null});
                        if (!rawQuery.moveToFirst()) {
                            if (rawQuery != null) {
                                rawQuery.close();
                                return;
                            }
                            return;
                        }
                        string = rawQuery.getString(0);
                        rawQuery.close();
                    }
                    Cursor cursor3 = rawQuery;
                    String str4 = string;
                    try {
                        Cursor query = zzh2.query("raw_events_metadata", new String[]{"metadata"}, "app_id = ? and metadata_fingerprint = ?", new String[]{str3, str4}, null, null, "rowid", ExifInterface.GPS_MEASUREMENT_2D);
                        try {
                            if (!query.moveToFirst()) {
                                this.zzt.zzay().zzd().zzb("Raw event metadata record is missing. appId", zzeh.zzn(str3));
                                if (query != null) {
                                    query.close();
                                    return;
                                }
                                return;
                            }
                            try {
                                try {
                                    com.google.android.gms.internal.measurement.zzgd zzgdVar = (com.google.android.gms.internal.measurement.zzgd) ((com.google.android.gms.internal.measurement.zzgc) zzkv.zzl(com.google.android.gms.internal.measurement.zzgd.zzt(), query.getBlob(0))).zzaC();
                                    if (query.moveToNext()) {
                                        this.zzt.zzay().zzk().zzb("Get multiple raw event metadata records, expected one. appId", zzeh.zzn(str3));
                                    }
                                    query.close();
                                    Preconditions.checkNotNull(zzgdVar);
                                    zzkqVar.zza = zzgdVar;
                                    if (j2 != -1) {
                                        i = 1;
                                        str2 = "app_id = ? and metadata_fingerprint = ? and rowid <= ?";
                                        strArr = new String[]{str3, str4, String.valueOf(j2)};
                                    } else {
                                        i = 1;
                                        str2 = "app_id = ? and metadata_fingerprint = ?";
                                        strArr = new String[]{str3, str4};
                                    }
                                    Cursor query2 = zzh2.query("raw_events", new String[]{"rowid", "name", ServerValues.NAME_OP_TIMESTAMP, ShareConstants.WEB_DIALOG_PARAM_DATA}, str2, strArr, null, null, "rowid", null);
                                    if (!query2.moveToFirst()) {
                                        this.zzt.zzay().zzk().zzb("Raw event data disappeared while in transaction. appId", zzeh.zzn(str3));
                                        if (query2 != null) {
                                            query2.close();
                                            return;
                                        }
                                        return;
                                    }
                                    do {
                                        long j3 = query2.getLong(0);
                                        try {
                                            com.google.android.gms.internal.measurement.zzfs zzfsVar = (com.google.android.gms.internal.measurement.zzfs) zzkv.zzl(com.google.android.gms.internal.measurement.zzft.zze(), query2.getBlob(3));
                                            zzfsVar.zzi(query2.getString(i));
                                            zzfsVar.zzm(query2.getLong(2));
                                            if (!zzkqVar.zza(j3, (com.google.android.gms.internal.measurement.zzft) zzfsVar.zzaC())) {
                                                if (query2 != null) {
                                                    query2.close();
                                                    return;
                                                }
                                                return;
                                            }
                                        } catch (IOException e) {
                                            this.zzt.zzay().zzd().zzc("Data loss. Failed to merge raw event. appId", zzeh.zzn(str3), e);
                                        }
                                    } while (query2.moveToNext());
                                    if (query2 != null) {
                                        query2.close();
                                    }
                                } catch (IOException e2) {
                                    this.zzt.zzay().zzd().zzc("Data loss. Failed to merge raw event metadata. appId", zzeh.zzn(str3), e2);
                                    if (query != null) {
                                        query.close();
                                    }
                                }
                            } catch (SQLiteException e3) {
                                e = e3;
                                r4 = str4;
                                this.zzt.zzay().zzd().zzc("Data loss. Error selecting raw event. appId", zzeh.zzn(str3), e);
                                if (r4 != 0) {
                                    r4.close();
                                }
                            } catch (Throwable th) {
                                th = th;
                                cursor2 = str4;
                                if (cursor2 != null) {
                                    cursor2.close();
                                }
                                throw th;
                            }
                        } catch (SQLiteException e4) {
                            e = e4;
                            str4 = query;
                        } catch (Throwable th2) {
                            th = th2;
                            str4 = query;
                        }
                    } catch (SQLiteException e5) {
                        e = e5;
                        r4 = cursor3;
                    } catch (Throwable th3) {
                        th = th3;
                        cursor2 = cursor3;
                    }
                } catch (SQLiteException e6) {
                    e = e6;
                }
            } catch (Throwable th4) {
                th = th4;
                cursor2 = cursor;
            }
        } catch (SQLiteException e7) {
            e = e7;
            r4 = 0;
        } catch (Throwable th5) {
            th = th5;
        }
    }

    public final int zza(String str, String str2) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzg();
        zzW();
        try {
            return zzh().delete("conditional_properties", "app_id=? and name=?", new String[]{str, str2});
        } catch (SQLiteException e) {
            this.zzt.zzay().zzd().zzd("Error deleting conditional property", zzeh.zzn(str), this.zzt.zzj().zzf(str2), e);
            return 0;
        }
    }

    @Override // com.google.android.gms.measurement.internal.zzkh
    protected final boolean zzb() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final long zzc(String str, String str2) {
        long j;
        SQLiteException e;
        ContentValues contentValues;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty("first_open_count");
        zzg();
        zzW();
        SQLiteDatabase zzh2 = zzh();
        zzh2.beginTransaction();
        try {
            try {
                j = zzaa("select first_open_count from app2 where app_id=?", new String[]{str}, -1L);
                if (j == -1) {
                    ContentValues contentValues2 = new ContentValues();
                    contentValues2.put("app_id", str);
                    contentValues2.put("first_open_count", (Integer) 0);
                    contentValues2.put("previous_install_count", (Integer) 0);
                    if (zzh2.insertWithOnConflict("app2", null, contentValues2, 5) == -1) {
                        this.zzt.zzay().zzd().zzc("Failed to insert column (got -1). appId", zzeh.zzn(str), "first_open_count");
                        return -1L;
                    }
                    j = 0;
                }
            } catch (SQLiteException e2) {
                j = 0;
                e = e2;
            }
            try {
                contentValues = new ContentValues();
                contentValues.put("app_id", str);
                contentValues.put("first_open_count", Long.valueOf(1 + j));
            } catch (SQLiteException e3) {
                e = e3;
                this.zzt.zzay().zzd().zzd("Error inserting column. appId", zzeh.zzn(str), "first_open_count", e);
                return j;
            }
            if (zzh2.update("app2", contentValues, "app_id = ?", new String[]{str}) == 0) {
                this.zzt.zzay().zzd().zzc("Failed to update column (got 0). appId", zzeh.zzn(str), "first_open_count");
                return -1L;
            }
            zzh2.setTransactionSuccessful();
            return j;
        } finally {
            zzh2.endTransaction();
        }
    }

    public final long zzd() {
        return zzaa("select max(bundle_end_timestamp) from queue", null, 0L);
    }

    public final long zze() {
        return zzaa("select max(timestamp) from raw_events", null, 0L);
    }

    public final long zzf(String str) {
        Preconditions.checkNotEmpty(str);
        return zzaa("select count(1) from events where app_id=? and name not like '!_%' escape '!'", new String[]{str}, 0L);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final SQLiteDatabase zzh() {
        zzg();
        try {
            return this.zzj.getWritableDatabase();
        } catch (SQLiteException e) {
            this.zzt.zzay().zzk().zzb("Error opening database", e);
            throw e;
        }
    }

    /* JADX WARN: Not initialized variable reg: 1, insn: 0x00dc: MOVE  (r0 I:??[OBJECT, ARRAY]) = (r1 I:??[OBJECT, ARRAY]), block:B:47:0x00dc */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00df  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final android.os.Bundle zzi(java.lang.String r8) {
        android.database.Cursor r1;
        android.database.Cursor r1;
        zzg();
        zzW();
        android.database.Cursor r0 = null;
        try {
            try {
                r1 = zzh().rawQuery("select parameters from default_event_params where app_id=?", new java.lang.String[]{r8});
                try {
                    if (!r1.moveToFirst()) {
                        r7.zzt.zzay().zzj().zza("Default event parameters not found");
                        if (r1 != null) {
                            r1.close();
                        }
                        return null;
                    }
                    try {
                        com.google.android.gms.internal.measurement.zzft r2 = (com.google.android.gms.internal.measurement.zzft) ((com.google.android.gms.internal.measurement.zzfs) com.google.android.gms.measurement.internal.zzkv.zzl(com.google.android.gms.internal.measurement.zzft.zze(), r1.getBlob(0))).zzaC();
                        r7.zzf.zzu();
                        java.util.List<com.google.android.gms.internal.measurement.zzfx> r8 = r2.zzi();
                        android.os.Bundle r2 = new android.os.Bundle();
                        for (com.google.android.gms.internal.measurement.zzfx r3 : r8) {
                            java.lang.String r4 = r3.zzg();
                            if (r3.zzu()) {
                                r2.putDouble(r4, r3.zza());
                            } else if (r3.zzv()) {
                                r2.putFloat(r4, r3.zzb());
                            } else if (r3.zzy()) {
                                r2.putString(r4, r3.zzh());
                            } else if (r3.zzw()) {
                                r2.putLong(r4, r3.zzd());
                            }
                        }
                        if (r1 != null) {
                            r1.close();
                        }
                        return r2;
                    } catch (java.io.IOException r2) {
                        r7.zzt.zzay().zzd().zzc("Failed to retrieve default event parameters. appId", com.google.android.gms.measurement.internal.zzeh.zzn(r8), r2);
                        if (r1 != null) {
                            r1.close();
                        }
                        return null;
                    }
                } catch (android.database.sqlite.SQLiteException e) {
                    r8 = e;
                    r7.zzt.zzay().zzd().zzb("Error selecting default event parameters", r8);
                    if (r1 != null) {
                        r1.close();
                    }
                    return null;
                }
            } catch (java.lang.Throwable th) {
                r8 = th;
                r0 = r1;
                if (r0 != null) {
                    r0.close();
                }
                throw r8;
            }
        } catch (android.database.sqlite.SQLiteException e) {
            r8 = e;
            r1 = null;
        } catch (java.lang.Throwable th) {
            r8 = th;
            if (r0 != null) {
            }
            throw r8;
        }
    }

    /* JADX WARN: Not initialized variable reg: 4, insn: 0x01fd: MOVE  (r3 I:??[OBJECT, ARRAY]) = (r4 I:??[OBJECT, ARRAY]), block:B:55:0x01fd */
    /* JADX WARN: Removed duplicated region for block: B:18:0x011b  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x011f A[Catch: SQLiteException -> 0x01dd, all -> 0x01fc, TryCatch #1 {all -> 0x01fc, blocks: (B:4:0x0061, B:9:0x006d, B:11:0x00d0, B:16:0x00da, B:20:0x0124, B:22:0x0153, B:26:0x015b, B:30:0x0176, B:32:0x0181, B:33:0x0193, B:35:0x01a4, B:37:0x01b2, B:38:0x01bb, B:40:0x01c4, B:29:0x0172, B:19:0x011f, B:50:0x01e3), top: B:59:0x000e }] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x016f  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0172 A[Catch: SQLiteException -> 0x01dd, all -> 0x01fc, TryCatch #1 {all -> 0x01fc, blocks: (B:4:0x0061, B:9:0x006d, B:11:0x00d0, B:16:0x00da, B:20:0x0124, B:22:0x0153, B:26:0x015b, B:30:0x0176, B:32:0x0181, B:33:0x0193, B:35:0x01a4, B:37:0x01b2, B:38:0x01bb, B:40:0x01c4, B:29:0x0172, B:19:0x011f, B:50:0x01e3), top: B:59:0x000e }] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0181 A[Catch: SQLiteException -> 0x01dd, all -> 0x01fc, TryCatch #1 {all -> 0x01fc, blocks: (B:4:0x0061, B:9:0x006d, B:11:0x00d0, B:16:0x00da, B:20:0x0124, B:22:0x0153, B:26:0x015b, B:30:0x0176, B:32:0x0181, B:33:0x0193, B:35:0x01a4, B:37:0x01b2, B:38:0x01bb, B:40:0x01c4, B:29:0x0172, B:19:0x011f, B:50:0x01e3), top: B:59:0x000e }] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x01c4 A[Catch: SQLiteException -> 0x01dd, all -> 0x01fc, TRY_LEAVE, TryCatch #1 {all -> 0x01fc, blocks: (B:4:0x0061, B:9:0x006d, B:11:0x00d0, B:16:0x00da, B:20:0x0124, B:22:0x0153, B:26:0x015b, B:30:0x0176, B:32:0x0181, B:33:0x0193, B:35:0x01a4, B:37:0x01b2, B:38:0x01bb, B:40:0x01c4, B:29:0x0172, B:19:0x011f, B:50:0x01e3), top: B:59:0x000e }] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x01d9  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0200  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.google.android.gms.measurement.internal.zzh zzj(java.lang.String r35) {
        android.database.Cursor r4;
        android.database.Cursor r4;
        boolean r6;
        com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r35);
        zzg();
        zzW();
        android.database.Cursor r3 = null;
        try {
            try {
                boolean r0 = true;
                r4 = zzh().query("apps", new java.lang.String[]{"app_instance_id", "gmp_app_id", "resettable_device_id_hash", "last_bundle_index", "last_bundle_start_timestamp", "last_bundle_end_timestamp", "app_version", "app_store", "gmp_version", "dev_cert_hash", "measurement_enabled", "day", "daily_public_events_count", "daily_events_count", "daily_conversions_count", "config_fetched_time", "failed_config_fetch_time", "app_version_int", "firebase_instance_id", "daily_error_events_count", "daily_realtime_events_count", "health_monitor_sample", "android_id", "adid_reporting_enabled", "admob_app_id", "dynamite_version", "safelisted_events", "ga_app_id", "session_stitching_token"}, "app_id=?", new java.lang.String[]{r35}, null, null, null);
                try {
                    if (!r4.moveToFirst()) {
                        if (r4 != null) {
                            r4.close();
                        }
                        return null;
                    }
                    com.google.android.gms.measurement.internal.zzh r5 = new com.google.android.gms.measurement.internal.zzh(r34.zzf.zzq(), r35);
                    r5.zzH(r4.getString(0));
                    r5.zzW(r4.getString(1));
                    r5.zzae(r4.getString(2));
                    r5.zzaa(r4.getLong(3));
                    r5.zzab(r4.getLong(4));
                    r5.zzZ(r4.getLong(5));
                    r5.zzJ(r4.getString(6));
                    r5.zzI(r4.getString(7));
                    r5.zzX(r4.getLong(8));
                    r5.zzS(r4.getLong(9));
                    if (!r4.isNull(10) && r4.getInt(10) == 0) {
                        r6 = false;
                        r5.zzac(r6);
                        r5.zzR(r4.getLong(11));
                        r5.zzP(r4.getLong(12));
                        r5.zzO(r4.getLong(13));
                        r5.zzM(r4.getLong(14));
                        r5.zzL(r4.getLong(15));
                        r5.zzU(r4.getLong(16));
                        r5.zzK(!r4.isNull(17) ? -2147483648L : r4.getInt(17));
                        r5.zzV(r4.getString(18));
                        r5.zzN(r4.getLong(19));
                        r5.zzQ(r4.getLong(20));
                        r5.zzY(r4.getString(21));
                        if (!r4.isNull(23) && r4.getInt(23) == 0) {
                            r0 = false;
                        }
                        r5.zzG(r0);
                        r5.zzF(r4.getString(24));
                        r5.zzT(!r4.isNull(25) ? 0L : r4.getLong(25));
                        if (!r4.isNull(26)) {
                            r5.zzaf(java.util.Arrays.asList(r4.getString(26).split(",", -1)));
                        }
                        com.google.android.gms.internal.measurement.zzpd.zzc();
                        if (r34.zzt.zzf().zzs(null, com.google.android.gms.measurement.internal.zzdu.zzal) && r34.zzt.zzf().zzs(r35, com.google.android.gms.measurement.internal.zzdu.zzan)) {
                            r5.zzag(r4.getString(28));
                        }
                        r5.zzD();
                        if (r4.moveToNext()) {
                            r34.zzt.zzay().zzd().zzb("Got multiple records for app, expected one. appId", com.google.android.gms.measurement.internal.zzeh.zzn(r35));
                        }
                        if (r4 != null) {
                            r4.close();
                        }
                        return r5;
                    }
                    r6 = true;
                    r5.zzac(r6);
                    r5.zzR(r4.getLong(11));
                    r5.zzP(r4.getLong(12));
                    r5.zzO(r4.getLong(13));
                    r5.zzM(r4.getLong(14));
                    r5.zzL(r4.getLong(15));
                    r5.zzU(r4.getLong(16));
                    r5.zzK(!r4.isNull(17) ? -2147483648L : r4.getInt(17));
                    r5.zzV(r4.getString(18));
                    r5.zzN(r4.getLong(19));
                    r5.zzQ(r4.getLong(20));
                    r5.zzY(r4.getString(21));
                    if (!r4.isNull(23)) {
                        r0 = false;
                    }
                    r5.zzG(r0);
                    r5.zzF(r4.getString(24));
                    r5.zzT(!r4.isNull(25) ? 0L : r4.getLong(25));
                    if (!r4.isNull(26)) {
                    }
                    com.google.android.gms.internal.measurement.zzpd.zzc();
                    if (r34.zzt.zzf().zzs(null, com.google.android.gms.measurement.internal.zzdu.zzal)) {
                        r5.zzag(r4.getString(28));
                    }
                    r5.zzD();
                    if (r4.moveToNext()) {
                    }
                    if (r4 != null) {
                    }
                    return r5;
                } catch (android.database.sqlite.SQLiteException e) {
                    r0 = e;
                    r34.zzt.zzay().zzd().zzc("Error querying app. appId", com.google.android.gms.measurement.internal.zzeh.zzn(r35), r0);
                    if (r4 != null) {
                        r4.close();
                    }
                    return null;
                }
            } catch (java.lang.Throwable th) {
                r0 = th;
                r3 = r4;
                if (r3 != null) {
                    r3.close();
                }
                throw r0;
            }
        } catch (android.database.sqlite.SQLiteException e) {
            r0 = e;
            r4 = null;
        } catch (java.lang.Throwable th) {
            r0 = th;
            if (r3 != null) {
            }
            throw r0;
        }
    }

    /* JADX WARN: Not initialized variable reg: 10, insn: 0x012b: MOVE  (r9 I:??[OBJECT, ARRAY]) = (r10 I:??[OBJECT, ARRAY]), block:B:33:0x012b */
    /* JADX WARN: Removed duplicated region for block: B:35:0x012e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.google.android.gms.measurement.internal.zzac zzk(java.lang.String r31, java.lang.String r32) {
        android.database.Cursor r10;
        android.database.Cursor r10;
        com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r31);
        com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r32);
        zzg();
        zzW();
        android.database.Cursor r9 = null;
        try {
            try {
                r10 = zzh().query("conditional_properties", new java.lang.String[]{"origin", "value", com.google.android.gms.measurement.api.AppMeasurementSdk.ConditionalUserProperty.ACTIVE, com.google.android.gms.measurement.api.AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME, com.google.android.gms.measurement.api.AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT, "timed_out_event", com.google.android.gms.measurement.api.AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP, "triggered_event", com.google.android.gms.measurement.api.AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_TIMESTAMP, com.google.android.gms.measurement.api.AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE, "expired_event"}, "app_id=? and name=?", new java.lang.String[]{r31, r32}, null, null, null);
                try {
                    if (!r10.moveToFirst()) {
                        if (r10 != null) {
                            r10.close();
                        }
                        return null;
                    }
                    java.lang.String r4 = r10.getString(0);
                    if (r4 == null) {
                        r4 = "";
                    }
                    java.lang.String r17 = r4;
                    java.lang.Object r6 = zzq(r10, 1);
                    com.google.android.gms.measurement.internal.zzac r0 = new com.google.android.gms.measurement.internal.zzac(r31, r17, new com.google.android.gms.measurement.internal.zzkw(r32, r10.getLong(8), r6, r17), r10.getLong(6), r10.getInt(2) != 0, r10.getString(3), (com.google.android.gms.measurement.internal.zzaw) r30.zzf.zzu().zzh(r10.getBlob(5), com.google.android.gms.measurement.internal.zzaw.CREATOR), r10.getLong(4), (com.google.android.gms.measurement.internal.zzaw) r30.zzf.zzu().zzh(r10.getBlob(7), com.google.android.gms.measurement.internal.zzaw.CREATOR), r10.getLong(9), (com.google.android.gms.measurement.internal.zzaw) r30.zzf.zzu().zzh(r10.getBlob(10), com.google.android.gms.measurement.internal.zzaw.CREATOR));
                    if (r10.moveToNext()) {
                        r30.zzt.zzay().zzd().zzc("Got multiple records for conditional property, expected one", com.google.android.gms.measurement.internal.zzeh.zzn(r31), r30.zzt.zzj().zzf(r32));
                    }
                    if (r10 != null) {
                        r10.close();
                    }
                    return r0;
                } catch (android.database.sqlite.SQLiteException e) {
                    r0 = e;
                    r30.zzt.zzay().zzd().zzd("Error querying conditional property", com.google.android.gms.measurement.internal.zzeh.zzn(r31), r30.zzt.zzj().zzf(r32), r0);
                    if (r10 != null) {
                        r10.close();
                    }
                    return null;
                }
            } catch (java.lang.Throwable th) {
                r0 = th;
                r9 = r10;
                if (r9 != null) {
                    r9.close();
                }
                throw r0;
            }
        } catch (android.database.sqlite.SQLiteException e) {
            r0 = e;
            r10 = null;
        } catch (java.lang.Throwable th) {
            r0 = th;
            if (r9 != null) {
            }
            throw r0;
        }
    }

    public final zzak zzl(long j, String str, boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
        return zzm(j, str, 1L, false, false, z3, false, z5);
    }

    public final zzak zzm(long j, String str, long j2, boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
        Preconditions.checkNotEmpty(str);
        zzg();
        zzW();
        String[] strArr = {str};
        zzak zzakVar = new zzak();
        Cursor cursor = null;
        try {
            try {
                SQLiteDatabase zzh2 = zzh();
                Cursor query = zzh2.query("apps", new String[]{"day", "daily_events_count", "daily_public_events_count", "daily_conversions_count", "daily_error_events_count", "daily_realtime_events_count"}, "app_id=?", new String[]{str}, null, null, null);
                if (!query.moveToFirst()) {
                    this.zzt.zzay().zzk().zzb("Not updating daily counts, app is not known. appId", zzeh.zzn(str));
                    if (query != null) {
                        query.close();
                    }
                    return zzakVar;
                }
                if (query.getLong(0) == j) {
                    zzakVar.zzb = query.getLong(1);
                    zzakVar.zza = query.getLong(2);
                    zzakVar.zzc = query.getLong(3);
                    zzakVar.zzd = query.getLong(4);
                    zzakVar.zze = query.getLong(5);
                }
                if (z) {
                    zzakVar.zzb += j2;
                }
                if (z2) {
                    zzakVar.zza += j2;
                }
                if (z3) {
                    zzakVar.zzc += j2;
                }
                if (z4) {
                    zzakVar.zzd += j2;
                }
                if (z5) {
                    zzakVar.zze += j2;
                }
                ContentValues contentValues = new ContentValues();
                contentValues.put("day", Long.valueOf(j));
                contentValues.put("daily_public_events_count", Long.valueOf(zzakVar.zza));
                contentValues.put("daily_events_count", Long.valueOf(zzakVar.zzb));
                contentValues.put("daily_conversions_count", Long.valueOf(zzakVar.zzc));
                contentValues.put("daily_error_events_count", Long.valueOf(zzakVar.zzd));
                contentValues.put("daily_realtime_events_count", Long.valueOf(zzakVar.zze));
                zzh2.update("apps", contentValues, "app_id=?", strArr);
                if (query != null) {
                    query.close();
                }
                return zzakVar;
            } catch (SQLiteException e) {
                this.zzt.zzay().zzd().zzc("Error updating daily counts. appId", zzeh.zzn(str), e);
                if (0 != 0) {
                    cursor.close();
                }
                return zzakVar;
            }
        } catch (Throwable th) {
            if (0 != 0) {
                cursor.close();
            }
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:65:0x0154  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.google.android.gms.measurement.internal.zzas zzn(java.lang.String r28, java.lang.String r29) {
        android.database.Cursor r13;
        android.database.Cursor r25;
        java.lang.Boolean r23;
        com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r28);
        com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r29);
        zzg();
        zzW();
        android.database.Cursor r19 = null;
        try {
            android.database.Cursor r13 = zzh().query("events", (java.lang.String[]) new java.util.ArrayList(java.util.Arrays.asList("lifetime_count", "current_bundle_count", "last_fire_timestamp", "last_bundled_timestamp", "last_bundled_day", "last_sampled_complex_event_id", "last_sampling_rate", "last_exempt_from_sampling", "current_session_count")).toArray(new java.lang.String[0]), "app_id=? and name=?", new java.lang.String[]{r28, r29}, null, null, null);
            try {
                if (!r13.moveToFirst()) {
                    if (r13 != null) {
                        r13.close();
                    }
                    return null;
                }
                long r5 = r13.getLong(0);
                long r7 = r13.getLong(1);
                long r16 = r13.getLong(2);
                long r20 = r13.isNull(3) ? 0L : r13.getLong(3);
                java.lang.Long r0 = r13.isNull(4) ? null : java.lang.Long.valueOf(r13.getLong(4));
                java.lang.Long r18 = r13.isNull(5) ? null : java.lang.Long.valueOf(r13.getLong(5));
                java.lang.Long r22 = r13.isNull(6) ? null : java.lang.Long.valueOf(r13.getLong(6));
                if (r13.isNull(7)) {
                    r23 = null;
                } else {
                    r23 = java.lang.Boolean.valueOf(r13.getLong(7) == 1);
                }
                r25 = r13;
                try {
                    com.google.android.gms.measurement.internal.zzas r24 = new com.google.android.gms.measurement.internal.zzas(r28, r29, r5, r7, r13.isNull(8) ? 0L : r13.getLong(8), r16, r20, r0, r18, r22, r23);
                    if (r25.moveToNext()) {
                        r27.zzt.zzay().zzd().zzb("Got multiple records for event aggregates, expected one. appId", com.google.android.gms.measurement.internal.zzeh.zzn(r28));
                    }
                    if (r25 != null) {
                        r25.close();
                    }
                    return r24;
                } catch (android.database.sqlite.SQLiteException e) {
                    r0 = e;
                    r13 = r25;
                    try {
                        r27.zzt.zzay().zzd().zzd("Error querying events. appId", com.google.android.gms.measurement.internal.zzeh.zzn(r28), r27.zzt.zzj().zzd(r29), r0);
                        if (r13 != null) {
                            r13.close();
                        }
                        return null;
                    } catch (java.lang.Throwable th) {
                        r0 = th;
                        r19 = r13;
                        if (r19 != null) {
                            r19.close();
                        }
                        throw r0;
                    }
                } catch (java.lang.Throwable th) {
                    r0 = th;
                    r19 = r25;
                    if (r19 != null) {
                    }
                    throw r0;
                }
            } catch (android.database.sqlite.SQLiteException e) {
                r0 = e;
                r25 = r13;
            } catch (java.lang.Throwable th) {
                r0 = th;
                r25 = r13;
            }
        } catch (android.database.sqlite.SQLiteException e) {
            r0 = e;
            r13 = null;
        } catch (java.lang.Throwable th) {
            r0 = th;
        }
    }

    /* JADX WARN: Not initialized variable reg: 11, insn: 0x00a9: MOVE  (r10 I:??[OBJECT, ARRAY]) = (r11 I:??[OBJECT, ARRAY]), block:B:31:0x00a9 */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00ac  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.google.android.gms.measurement.internal.zzky zzp(java.lang.String r20, java.lang.String r21) {
        android.database.Cursor r11;
        android.database.Cursor r11;
        com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r20);
        com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r21);
        zzg();
        zzW();
        android.database.Cursor r10 = null;
        try {
            try {
                r11 = zzh().query("user_attributes", new java.lang.String[]{"set_timestamp", "value", "origin"}, "app_id=? and name=?", new java.lang.String[]{r20, r21}, null, null, null);
                try {
                    if (!r11.moveToFirst()) {
                        if (r11 != null) {
                            r11.close();
                        }
                        return null;
                    }
                    long r6 = r11.getLong(0);
                    java.lang.Object r8 = zzq(r11, 1);
                    if (r8 == null) {
                        if (r11 != null) {
                            r11.close();
                        }
                        return null;
                    }
                    com.google.android.gms.measurement.internal.zzky r0 = new com.google.android.gms.measurement.internal.zzky(r20, r11.getString(2), r21, r6, r8);
                    if (r11.moveToNext()) {
                        r19.zzt.zzay().zzd().zzb("Got multiple records for user property, expected one. appId", com.google.android.gms.measurement.internal.zzeh.zzn(r20));
                    }
                    if (r11 != null) {
                        r11.close();
                    }
                    return r0;
                } catch (android.database.sqlite.SQLiteException e) {
                    r0 = e;
                    r19.zzt.zzay().zzd().zzd("Error querying user property. appId", com.google.android.gms.measurement.internal.zzeh.zzn(r20), r19.zzt.zzj().zzf(r21), r0);
                    if (r11 != null) {
                        r11.close();
                    }
                    return null;
                }
            } catch (java.lang.Throwable th) {
                r0 = th;
                r10 = r11;
                if (r10 != null) {
                    r10.close();
                }
                throw r0;
            }
        } catch (android.database.sqlite.SQLiteException e) {
            r0 = e;
            r11 = null;
        } catch (java.lang.Throwable th) {
            r0 = th;
            if (r10 != null) {
            }
            throw r0;
        }
    }

    final Object zzq(Cursor cursor, int i) {
        int type = cursor.getType(i);
        if (type == 0) {
            this.zzt.zzay().zzd().zza("Loaded invalid null value from database");
            return null;
        } else if (type != 1) {
            if (type != 2) {
                if (type != 3) {
                    if (type != 4) {
                        this.zzt.zzay().zzd().zzb("Loaded invalid unknown value type, ignoring it", Integer.valueOf(type));
                        return null;
                    }
                    this.zzt.zzay().zzd().zza("Loaded invalid blob type value, ignoring it");
                    return null;
                }
                return cursor.getString(i);
            }
            return Double.valueOf(cursor.getDouble(i));
        } else {
            return Long.valueOf(cursor.getLong(i));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0044  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.String zzr() {
        android.database.sqlite.SQLiteException r2;
        android.database.Cursor r0;
        android.database.Cursor r1 = null;
        try {
            r0 = zzh().rawQuery("select app_id from queue order by has_realtime desc, rowid asc limit 1;", null);
            try {
                try {
                    if (!r0.moveToFirst()) {
                        if (r0 != null) {
                            r0.close();
                        }
                        return null;
                    }
                    java.lang.String r1 = r0.getString(0);
                    if (r0 != null) {
                        r0.close();
                    }
                    return r1;
                } catch (android.database.sqlite.SQLiteException e) {
                    r2 = e;
                    r6.zzt.zzay().zzd().zzb("Database error getting next bundle app id", r2);
                    if (r0 != null) {
                        r0.close();
                    }
                    return null;
                }
            } catch (java.lang.Throwable r1) {
                r1 = r0;
                r0 = r1;
                if (r1 != null) {
                    r1.close();
                }
                throw r0;
            }
        } catch (android.database.sqlite.SQLiteException r0) {
            r2 = r0;
            r0 = null;
        } catch (java.lang.Throwable th) {
            r0 = th;
            if (r1 != null) {
            }
            throw r0;
        }
    }

    public final List zzs(String str, String str2, String str3) {
        Preconditions.checkNotEmpty(str);
        zzg();
        zzW();
        ArrayList arrayList = new ArrayList(3);
        arrayList.add(str);
        StringBuilder sb = new StringBuilder("app_id=?");
        if (!TextUtils.isEmpty(str2)) {
            arrayList.add(str2);
            sb.append(" and origin=?");
        }
        if (!TextUtils.isEmpty(str3)) {
            arrayList.add(String.valueOf(str3).concat("*"));
            sb.append(" and name glob ?");
        }
        return zzt(sb.toString(), (String[]) arrayList.toArray(new String[arrayList.size()]));
    }

    /* JADX WARN: Code restructure failed: missing block: B:7:0x0058, code lost:
        r2 = r27.zzt.zzay().zzd();
        r27.zzt.zzf();
        r2.zzb("Read more than the max allowed conditional properties, ignoring extra", 1000);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.util.List zzt(java.lang.String r28, java.lang.String[] r29) {
        zzg();
        zzW();
        java.util.ArrayList r0 = new java.util.ArrayList();
        android.database.Cursor r11 = null;
        try {
            try {
                android.database.sqlite.SQLiteDatabase r2 = zzh();
                java.lang.String[] r4 = {"app_id", "origin", "name", "value", com.google.android.gms.measurement.api.AppMeasurementSdk.ConditionalUserProperty.ACTIVE, com.google.android.gms.measurement.api.AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME, com.google.android.gms.measurement.api.AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT, "timed_out_event", com.google.android.gms.measurement.api.AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP, "triggered_event", com.google.android.gms.measurement.api.AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_TIMESTAMP, com.google.android.gms.measurement.api.AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE, "expired_event"};
                r27.zzt.zzf();
                r11 = r2.query("conditional_properties", r4, r28, r29, null, null, "rowid", "1001");
                if (!r11.moveToFirst()) {
                    if (r11 != null) {
                        r11.close();
                    }
                    return r0;
                }
                while (true) {
                    int r2 = r0.size();
                    r27.zzt.zzf();
                    if (r2 < 1000) {
                        java.lang.String r13 = r11.getString(0);
                        java.lang.String r14 = r11.getString(1);
                        java.lang.String r5 = r11.getString(2);
                        java.lang.Object r8 = zzq(r11, 3);
                        boolean r18 = r11.getInt(4) != 0;
                        java.lang.String r19 = r11.getString(5);
                        long r21 = r11.getLong(6);
                        r0.add(new com.google.android.gms.measurement.internal.zzac(r13, r14, new com.google.android.gms.measurement.internal.zzkw(r5, r11.getLong(10), r8, r14), r11.getLong(8), r18, r19, (com.google.android.gms.measurement.internal.zzaw) r27.zzf.zzu().zzh(r11.getBlob(7), com.google.android.gms.measurement.internal.zzaw.CREATOR), r21, (com.google.android.gms.measurement.internal.zzaw) r27.zzf.zzu().zzh(r11.getBlob(9), com.google.android.gms.measurement.internal.zzaw.CREATOR), r11.getLong(11), (com.google.android.gms.measurement.internal.zzaw) r27.zzf.zzu().zzh(r11.getBlob(12), com.google.android.gms.measurement.internal.zzaw.CREATOR)));
                        if (!r11.moveToNext()) {
                            break;
                        }
                    } else {
                        break;
                    }
                }
                if (r11 != null) {
                    r11.close();
                }
                return r0;
            } catch (android.database.sqlite.SQLiteException r0) {
                r27.zzt.zzay().zzd().zzb("Error querying conditional user property value", r0);
                java.util.List r0 = java.util.Collections.emptyList();
                if (r11 != null) {
                    r11.close();
                }
                return r0;
            }
        } catch (java.lang.Throwable r0) {
            if (r11 != null) {
                r11.close();
            }
            throw r0;
        }
    }

    public final List zzu(String str) {
        Preconditions.checkNotEmpty(str);
        zzg();
        zzW();
        ArrayList arrayList = new ArrayList();
        Cursor cursor = null;
        try {
            try {
                this.zzt.zzf();
                cursor = zzh().query("user_attributes", new String[]{"name", "origin", "set_timestamp", "value"}, "app_id=?", new String[]{str}, null, null, "rowid", "1000");
                if (!cursor.moveToFirst()) {
                    if (cursor != null) {
                        cursor.close();
                    }
                    return arrayList;
                }
                do {
                    String string = cursor.getString(0);
                    String string2 = cursor.getString(1);
                    if (string2 == null) {
                        string2 = "";
                    }
                    String str2 = string2;
                    long j = cursor.getLong(2);
                    Object zzq = zzq(cursor, 3);
                    if (zzq == null) {
                        this.zzt.zzay().zzd().zzb("Read invalid user property value, ignoring it. appId", zzeh.zzn(str));
                    } else {
                        arrayList.add(new zzky(str, str2, string, j, zzq));
                    }
                } while (cursor.moveToNext());
                if (cursor != null) {
                    cursor.close();
                }
                return arrayList;
            } catch (SQLiteException e) {
                this.zzt.zzay().zzd().zzc("Error querying user properties. appId", zzeh.zzn(str), e);
                List emptyList = Collections.emptyList();
                if (cursor != null) {
                    cursor.close();
                }
                return emptyList;
            }
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x00a9, code lost:
        r0 = r17.zzt.zzay().zzd();
        r17.zzt.zzf();
        r0.zzb("Read more than the max allowed user properties, ignoring excess", 1000);
     */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0128  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.util.List zzv(java.lang.String r18, java.lang.String r19, java.lang.String r20) {
        java.lang.String r15;
        com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r18);
        zzg();
        zzW();
        java.util.ArrayList r2 = new java.util.ArrayList();
        android.database.Cursor r12 = null;
        try {
            try {
                java.util.ArrayList r3 = new java.util.ArrayList(3);
                try {
                    r3.add(r18);
                    java.lang.StringBuilder r4 = new java.lang.StringBuilder("app_id=?");
                    if (android.text.TextUtils.isEmpty(r19)) {
                        r15 = r19;
                    } else {
                        r15 = r19;
                        try {
                            r3.add(r15);
                            r4.append(" and origin=?");
                        } catch (android.database.sqlite.SQLiteException e) {
                            r0 = e;
                            r17.zzt.zzay().zzd().zzd("(2)Error querying user properties", com.google.android.gms.measurement.internal.zzeh.zzn(r18), r15, r0);
                            java.util.List r0 = java.util.Collections.emptyList();
                            if (r12 != null) {
                            }
                            return r0;
                        }
                    }
                    if (!android.text.TextUtils.isEmpty(r20)) {
                        r3.add(r20 + "*");
                        r4.append(" and name glob ?");
                    }
                    java.lang.String[] r7 = (java.lang.String[]) r3.toArray(new java.lang.String[r3.size()]);
                    java.lang.String r8 = r4.toString();
                    r17.zzt.zzf();
                    r12 = zzh().query("user_attributes", new java.lang.String[]{"name", "set_timestamp", "value", "origin"}, r8, r7, null, null, "rowid", "1001");
                    if (r12.moveToFirst()) {
                        while (true) {
                            int r3 = r2.size();
                            r17.zzt.zzf();
                            if (r3 < 1000) {
                                java.lang.String r7 = r12.getString(0);
                                long r8 = r12.getLong(1);
                                java.lang.Object r10 = zzq(r12, 2);
                                r15 = r12.getString(3);
                                if (r10 == null) {
                                    r17.zzt.zzay().zzd().zzd("(2)Read invalid user property value, ignoring it", com.google.android.gms.measurement.internal.zzeh.zzn(r18), r15, r20);
                                } else {
                                    r2.add(new com.google.android.gms.measurement.internal.zzky(r18, r15, r7, r8, r10));
                                }
                                if (!r12.moveToNext()) {
                                    break;
                                }
                            } else {
                                break;
                            }
                        }
                        if (r12 != null) {
                            r12.close();
                        }
                        return r2;
                    }
                    return r2;
                } catch (android.database.sqlite.SQLiteException e) {
                    r0 = e;
                    r15 = r19;
                    r17.zzt.zzay().zzd().zzd("(2)Error querying user properties", com.google.android.gms.measurement.internal.zzeh.zzn(r18), r15, r0);
                    java.util.List r0 = java.util.Collections.emptyList();
                    if (r12 != null) {
                        r12.close();
                    }
                    return r0;
                }
            } catch (android.database.sqlite.SQLiteException e) {
                r0 = e;
            }
        } finally {
            if (r12 != null) {
                r12.close();
            }
        }
    }

    public final void zzw() {
        zzW();
        zzh().beginTransaction();
    }

    public final void zzx() {
        zzW();
        zzh().endTransaction();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzy(List list) {
        zzg();
        zzW();
        Preconditions.checkNotNull(list);
        Preconditions.checkNotZero(list.size());
        if (zzI()) {
            String str = "(" + TextUtils.join(",", list) + ")";
            if (zzZ("SELECT COUNT(1) FROM queue WHERE rowid IN " + str + " AND retry_count =  2147483647 LIMIT 1", null) > 0) {
                this.zzt.zzay().zzk().zza("The number of upload retries exceeds the limit. Will remain unchanged.");
            }
            try {
                zzh().execSQL("UPDATE queue SET retry_count = IFNULL(retry_count, 0) + 1 WHERE rowid IN " + str + " AND (retry_count IS NULL OR retry_count < 2147483647)");
            } catch (SQLiteException e) {
                this.zzt.zzay().zzd().zzb("Error incrementing retry count. error", e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzz() {
        zzg();
        zzW();
        if (zzI()) {
            long zza2 = this.zzf.zzs().zza.zza();
            long elapsedRealtime = this.zzt.zzav().elapsedRealtime();
            long abs = Math.abs(elapsedRealtime - zza2);
            this.zzt.zzf();
            if (abs > ((Long) zzdu.zzx.zza(null)).longValue()) {
                this.zzf.zzs().zza.zzb(elapsedRealtime);
                zzg();
                zzW();
                if (zzI()) {
                    SQLiteDatabase zzh2 = zzh();
                    this.zzt.zzf();
                    int delete = zzh2.delete("queue", "abs(bundle_end_timestamp - ?) > cast(? as integer)", new String[]{String.valueOf(this.zzt.zzav().currentTimeMillis()), String.valueOf(zzag.zzA())});
                    if (delete > 0) {
                        this.zzt.zzay().zzj().zzb("Deleted stale rows. rowsDeleted", Integer.valueOf(delete));
                    }
                }
            }
        }
    }
}
