package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteFullException;
import android.os.Parcel;
import android.os.SystemClock;
/* compiled from: com.google.android.gms:play-services-measurement-impl@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzea extends zzf {
    private final zzdz zza;
    private boolean zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzea(zzfr zzfrVar) {
        super(zzfrVar);
        Context zzau = this.zzt.zzau();
        this.zzt.zzf();
        this.zza = new zzdz(this, zzau, "google_app_measurement_local.db");
    }

    /* JADX WARN: Removed duplicated region for block: B:77:0x0124  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0129  */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1, types: [int, boolean] */
    /* JADX WARN: Type inference failed for: r2v13 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final boolean zzq(int r17, byte[] r18) {
        android.database.sqlite.SQLiteDatabase r9;
        android.database.Cursor r10;
        zzg();
        ?? r2 = 0;
        if (r16.zzb) {
            return false;
        }
        android.content.ContentValues r3 = new android.content.ContentValues();
        r3.put("type", java.lang.Integer.valueOf(r17));
        r3.put("entry", r18);
        r16.zzt.zzf();
        int r5 = 0;
        int r6 = 5;
        for (int r4 = 5; r5 < r4; r4 = 5) {
            android.database.Cursor r8 = null;
            r8 = null;
            r8 = null;
            r8 = null;
            android.database.sqlite.SQLiteDatabase r8 = null;
            try {
                r9 = zzh();
                try {
                    if (r9 == null) {
                        r16.zzb = true;
                        return r2;
                    }
                    r9.beginTransaction();
                    r10 = r9.rawQuery("select count(1) from messages", null);
                    long r11 = 0;
                    if (r10 != null) {
                        try {
                            if (r10.moveToFirst()) {
                                r11 = r10.getLong(r2);
                            }
                        } catch (android.database.sqlite.SQLiteDatabaseLockedException unused) {
                            r8 = r10;
                            try {
                                android.os.SystemClock.sleep(r6);
                                r6 += 20;
                                if (r8 != null) {
                                    r8.close();
                                }
                                if (r9 != null) {
                                    r9.close();
                                }
                                r5++;
                                r2 = 0;
                            } catch (java.lang.Throwable th) {
                                r0 = th;
                                if (r8 != null) {
                                    r8.close();
                                }
                                if (r9 != null) {
                                    r9.close();
                                }
                                throw r0;
                            }
                        } catch (android.database.sqlite.SQLiteFullException e) {
                            r0 = e;
                            r8 = r9;
                            r16.zzt.zzay().zzd().zzb("Error writing entry; local database full", r0);
                            r16.zzb = true;
                            if (r10 != null) {
                                r10.close();
                            }
                            if (r8 == null) {
                                r5++;
                                r2 = 0;
                            }
                            r8.close();
                            r5++;
                            r2 = 0;
                        } catch (android.database.sqlite.SQLiteException e) {
                            r0 = e;
                            r8 = r9;
                            if (r8 != null) {
                                try {
                                    if (r8.inTransaction()) {
                                        r8.endTransaction();
                                    }
                                } catch (java.lang.Throwable th) {
                                    r0 = th;
                                    r9 = r8;
                                    r8 = r10;
                                    if (r8 != null) {
                                    }
                                    if (r9 != null) {
                                    }
                                    throw r0;
                                }
                            }
                            r16.zzt.zzay().zzd().zzb("Error writing entry to local database", r0);
                            r16.zzb = true;
                            if (r10 != null) {
                                r10.close();
                            }
                            if (r8 == null) {
                                r5++;
                                r2 = 0;
                            }
                            r8.close();
                            r5++;
                            r2 = 0;
                        } catch (java.lang.Throwable th) {
                            r0 = th;
                            r8 = r10;
                            if (r8 != null) {
                            }
                            if (r9 != null) {
                            }
                            throw r0;
                        }
                    }
                    if (r11 >= 100000) {
                        r16.zzt.zzay().zzd().zza("Data loss, local db full");
                        long r13 = (100000 - r11) + 1;
                        java.lang.String[] r0 = new java.lang.String[1];
                        r0[r2] = java.lang.Long.toString(r13);
                        long r11 = r9.delete("messages", "rowid in (select rowid from messages order by rowid asc limit ?)", r0);
                        if (r11 != r13) {
                            r16.zzt.zzay().zzd().zzd("Different delete count than expected in local db. expected, received, difference", java.lang.Long.valueOf(r13), java.lang.Long.valueOf(r11), java.lang.Long.valueOf(r13 - r11));
                        }
                    }
                    r9.insertOrThrow("messages", null, r3);
                    r9.setTransactionSuccessful();
                    r9.endTransaction();
                    if (r10 != 0) {
                        r10.close();
                    }
                    r9.close();
                    return true;
                } catch (android.database.sqlite.SQLiteDatabaseLockedException unused) {
                } catch (android.database.sqlite.SQLiteFullException e) {
                    r0 = e;
                    r10 = null;
                } catch (android.database.sqlite.SQLiteException e) {
                    r0 = e;
                    r10 = null;
                }
            } catch (android.database.sqlite.SQLiteDatabaseLockedException unused) {
                r9 = null;
            } catch (android.database.sqlite.SQLiteFullException e) {
                r0 = e;
                r10 = null;
            } catch (android.database.sqlite.SQLiteException e) {
                r0 = e;
                r10 = null;
            } catch (java.lang.Throwable th) {
                r0 = th;
                r9 = null;
                if (r8 != null) {
                }
                if (r9 != null) {
                }
                throw r0;
            }
        }
        r16.zzt.zzay().zzj().zza("Failed to write entry to local database");
        return false;
    }

    @Override // com.google.android.gms.measurement.internal.zzf
    protected final boolean zzf() {
        return false;
    }

    final SQLiteDatabase zzh() throws SQLiteException {
        if (this.zzb) {
            return null;
        }
        SQLiteDatabase writableDatabase = this.zza.getWritableDatabase();
        if (writableDatabase == null) {
            this.zzb = true;
            return null;
        }
        return writableDatabase;
    }

    /* JADX WARN: Removed duplicated region for block: B:148:0x021f  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x022f  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x024c  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x025a  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x025f  */
    /* JADX WARN: Removed duplicated region for block: B:187:0x0205 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:195:0x01e0 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:208:0x0252 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:209:0x0252 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:211:0x0252 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.util.List zzi(int r23) {
        int r8;
        android.database.sqlite.SQLiteDatabase r15;
        android.database.Cursor r11;
        android.database.sqlite.SQLiteDatabase r14;
        android.database.Cursor r11;
        long r20;
        long r12;
        java.lang.String r14;
        java.lang.String[] r15;
        android.os.Parcel r12;
        com.google.android.gms.measurement.internal.zzkw r0;
        com.google.android.gms.measurement.internal.zzac r0;
        zzg();
        android.database.Cursor r4 = null;
        if (r22.zzb) {
            return null;
        }
        java.util.ArrayList r5 = new java.util.ArrayList();
        if (zzl()) {
            int r9 = 5;
            for (r8 = 0; r8 < 5; r8 = r8 + 1) {
                try {
                    android.database.sqlite.SQLiteDatabase r15 = zzh();
                    if (r15 == null) {
                        r22.zzb = true;
                        return null;
                    }
                    try {
                        r15.beginTransaction();
                        try {
                            try {
                                r11 = r15.query("messages", new java.lang.String[]{"rowid"}, "type=?", new java.lang.String[]{androidx.exifinterface.media.ExifInterface.GPS_MEASUREMENT_3D}, null, null, "rowid desc", com.facebook.appevents.AppEventsConstants.EVENT_PARAM_VALUE_YES);
                                try {
                                    r20 = -1;
                                    try {
                                        if (r11.moveToFirst()) {
                                            r12 = r11.getLong(0);
                                            if (r11 != null) {
                                                r11.close();
                                            }
                                        } else {
                                            if (r11 != null) {
                                                r11.close();
                                            }
                                            r12 = -1;
                                        }
                                        if (r12 != -1) {
                                            r14 = "rowid<?";
                                            r15 = new java.lang.String[]{java.lang.String.valueOf(r12)};
                                        } else {
                                            r14 = null;
                                            r15 = null;
                                        }
                                        r11 = r15.query("messages", new java.lang.String[]{"rowid", "type", "entry"}, r14, r15, null, null, "rowid asc", java.lang.Integer.toString(100));
                                        while (r11.moveToNext()) {
                                            try {
                                                r20 = r11.getLong(0);
                                                int r0 = r11.getInt(1);
                                                byte[] r13 = r11.getBlob(2);
                                                if (r0 == 0) {
                                                    r12 = android.os.Parcel.obtain();
                                                    try {
                                                        try {
                                                            r12.unmarshall(r13, 0, r13.length);
                                                            r12.setDataPosition(0);
                                                            com.google.android.gms.measurement.internal.zzaw r0 = com.google.android.gms.measurement.internal.zzaw.CREATOR.createFromParcel(r12);
                                                            if (r0 != null) {
                                                                r5.add(r0);
                                                            }
                                                        } catch (com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException unused) {
                                                            r22.zzt.zzay().zzd().zza("Failed to load event from local database");
                                                            r12.recycle();
                                                        }
                                                    } finally {
                                                    }
                                                } else if (r0 == 1) {
                                                    r12 = android.os.Parcel.obtain();
                                                    try {
                                                        try {
                                                            r12.unmarshall(r13, 0, r13.length);
                                                            r12.setDataPosition(0);
                                                            r0 = com.google.android.gms.measurement.internal.zzkw.CREATOR.createFromParcel(r12);
                                                        } catch (com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException unused) {
                                                            r22.zzt.zzay().zzd().zza("Failed to load user property from local database");
                                                            r12.recycle();
                                                            r0 = null;
                                                        }
                                                        if (r0 != null) {
                                                            r5.add(r0);
                                                        }
                                                    } finally {
                                                    }
                                                } else if (r0 == 2) {
                                                    r12 = android.os.Parcel.obtain();
                                                    try {
                                                        try {
                                                            r12.unmarshall(r13, 0, r13.length);
                                                            r12.setDataPosition(0);
                                                            r0 = com.google.android.gms.measurement.internal.zzac.CREATOR.createFromParcel(r12);
                                                        } catch (com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException unused) {
                                                            r22.zzt.zzay().zzd().zza("Failed to load conditional user property from local database");
                                                            r12.recycle();
                                                            r0 = null;
                                                        }
                                                        if (r0 != null) {
                                                            r5.add(r0);
                                                        }
                                                    } finally {
                                                    }
                                                } else if (r0 == 3) {
                                                    r22.zzt.zzay().zzk().zza("Skipping app launch break");
                                                } else {
                                                    r22.zzt.zzay().zzd().zza("Unknown record type in local database");
                                                }
                                            } catch (android.database.sqlite.SQLiteDatabaseLockedException unused) {
                                                r14 = r15;
                                            } catch (android.database.sqlite.SQLiteFullException e) {
                                                r0 = e;
                                                r14 = r15;
                                            } catch (android.database.sqlite.SQLiteException e) {
                                                r0 = e;
                                                r14 = r15;
                                            } catch (java.lang.Throwable th) {
                                                r0 = th;
                                                r14 = r15;
                                            }
                                        }
                                        r14 = r15;
                                    } catch (android.database.sqlite.SQLiteDatabaseLockedException unused) {
                                        r14 = r15;
                                        r11 = null;
                                        r15 = r14;
                                        android.os.SystemClock.sleep(r9);
                                        r9 += 20;
                                        if (r11 != null) {
                                            r11.close();
                                        }
                                        r8 = r15 == null ? r8 + 1 : 0;
                                        r15.close();
                                    } catch (android.database.sqlite.SQLiteFullException e) {
                                        r0 = e;
                                        r14 = r15;
                                        r11 = null;
                                        r15 = r14;
                                        r22.zzt.zzay().zzd().zzb("Error reading entries from local database", r0);
                                        r22.zzb = true;
                                        if (r11 != null) {
                                            r11.close();
                                        }
                                        if (r15 == null) {
                                        }
                                        r15.close();
                                    } catch (android.database.sqlite.SQLiteException e) {
                                        r0 = e;
                                        r14 = r15;
                                        r11 = null;
                                        r15 = r14;
                                        if (r15 != null) {
                                            try {
                                                if (r15.inTransaction()) {
                                                    r15.endTransaction();
                                                }
                                            } catch (java.lang.Throwable th) {
                                                r0 = th;
                                                r4 = r11;
                                                if (r4 != null) {
                                                    r4.close();
                                                }
                                                if (r15 != null) {
                                                    r15.close();
                                                }
                                                throw r0;
                                            }
                                        }
                                        r22.zzt.zzay().zzd().zzb("Error reading entries from local database", r0);
                                        r22.zzb = true;
                                        if (r11 != null) {
                                            r11.close();
                                        }
                                        if (r15 == null) {
                                        }
                                        r15.close();
                                    } catch (java.lang.Throwable th) {
                                        r0 = th;
                                        r14 = r15;
                                    }
                                } catch (java.lang.Throwable th) {
                                    r0 = th;
                                    r14 = r15;
                                    if (r11 != null) {
                                        try {
                                            r11.close();
                                        } catch (android.database.sqlite.SQLiteDatabaseLockedException unused) {
                                            r11 = null;
                                            r15 = r14;
                                            android.os.SystemClock.sleep(r9);
                                            r9 += 20;
                                            if (r11 != null) {
                                            }
                                            if (r15 == null) {
                                            }
                                            r15.close();
                                        } catch (android.database.sqlite.SQLiteFullException e) {
                                            r0 = e;
                                            r11 = null;
                                            r15 = r14;
                                            r22.zzt.zzay().zzd().zzb("Error reading entries from local database", r0);
                                            r22.zzb = true;
                                            if (r11 != null) {
                                            }
                                            if (r15 == null) {
                                            }
                                            r15.close();
                                        } catch (android.database.sqlite.SQLiteException e) {
                                            r0 = e;
                                            r11 = null;
                                            r15 = r14;
                                            if (r15 != null) {
                                            }
                                            r22.zzt.zzay().zzd().zzb("Error reading entries from local database", r0);
                                            r22.zzb = true;
                                            if (r11 != null) {
                                            }
                                            if (r15 == null) {
                                            }
                                            r15.close();
                                        } catch (java.lang.Throwable th) {
                                            r0 = th;
                                            r15 = r14;
                                            if (r4 != null) {
                                            }
                                            if (r15 != null) {
                                            }
                                            throw r0;
                                        }
                                    }
                                    throw r0;
                                    break;
                                }
                            } catch (java.lang.Throwable th) {
                                r0 = th;
                                r14 = r15;
                                r11 = null;
                                if (r11 != null) {
                                }
                                throw r0;
                                break;
                                break;
                            }
                        } catch (java.lang.Throwable th) {
                            r0 = th;
                            r14 = r15;
                        }
                    } catch (android.database.sqlite.SQLiteDatabaseLockedException unused) {
                        r14 = r15;
                    } catch (android.database.sqlite.SQLiteFullException e) {
                        r0 = e;
                        r14 = r15;
                    } catch (android.database.sqlite.SQLiteException e) {
                        r0 = e;
                        r14 = r15;
                    } catch (java.lang.Throwable th) {
                        r0 = th;
                        r14 = r15;
                    }
                    try {
                        if (r14.delete("messages", "rowid <= ?", new java.lang.String[]{java.lang.Long.toString(r20)}) < r5.size()) {
                            r22.zzt.zzay().zzd().zza("Fewer entries removed from local database than expected");
                        }
                        r14.setTransactionSuccessful();
                        r14.endTransaction();
                        if (r11 != null) {
                            r11.close();
                        }
                        r14.close();
                        return r5;
                    } catch (android.database.sqlite.SQLiteDatabaseLockedException unused) {
                        r15 = r14;
                        android.os.SystemClock.sleep(r9);
                        r9 += 20;
                        if (r11 != null) {
                        }
                        if (r15 == null) {
                        }
                        r15.close();
                    } catch (android.database.sqlite.SQLiteFullException e) {
                        r0 = e;
                        r15 = r14;
                        r22.zzt.zzay().zzd().zzb("Error reading entries from local database", r0);
                        r22.zzb = true;
                        if (r11 != null) {
                        }
                        if (r15 == null) {
                        }
                        r15.close();
                    } catch (android.database.sqlite.SQLiteException e) {
                        r0 = e;
                        r15 = r14;
                        if (r15 != null) {
                        }
                        r22.zzt.zzay().zzd().zzb("Error reading entries from local database", r0);
                        r22.zzb = true;
                        if (r11 != null) {
                        }
                        if (r15 == null) {
                        }
                        r15.close();
                    } catch (java.lang.Throwable th) {
                        r0 = th;
                        r4 = r11;
                        r15 = r14;
                        if (r4 != null) {
                        }
                        if (r15 != null) {
                        }
                        throw r0;
                    }
                } catch (android.database.sqlite.SQLiteDatabaseLockedException unused) {
                    r11 = null;
                    r15 = null;
                } catch (android.database.sqlite.SQLiteFullException e) {
                    r0 = e;
                    r11 = null;
                    r15 = null;
                } catch (android.database.sqlite.SQLiteException e) {
                    r0 = e;
                    r11 = null;
                    r15 = null;
                } catch (java.lang.Throwable th) {
                    r0 = th;
                    r15 = null;
                }
            }
            r22.zzt.zzay().zzk().zza("Failed to read events from database in reasonable time");
            return null;
        }
        return r5;
    }

    public final void zzj() {
        int delete;
        zzg();
        try {
            SQLiteDatabase zzh = zzh();
            if (zzh == null || (delete = zzh.delete("messages", null, null)) <= 0) {
                return;
            }
            this.zzt.zzay().zzj().zzb("Reset local analytics data. records", Integer.valueOf(delete));
        } catch (SQLiteException e) {
            this.zzt.zzay().zzd().zzb("Error resetting local analytics data. error", e);
        }
    }

    public final boolean zzk() {
        return zzq(3, new byte[0]);
    }

    final boolean zzl() {
        Context zzau = this.zzt.zzau();
        this.zzt.zzf();
        return zzau.getDatabasePath("google_app_measurement_local.db").exists();
    }

    public final boolean zzm() {
        int i;
        zzg();
        if (!this.zzb && zzl()) {
            int i2 = 5;
            for (i = 0; i < 5; i = i + 1) {
                SQLiteDatabase sQLiteDatabase = null;
                try {
                    SQLiteDatabase zzh = zzh();
                    if (zzh == null) {
                        this.zzb = true;
                        return false;
                    }
                    zzh.beginTransaction();
                    zzh.delete("messages", "type == ?", new String[]{Integer.toString(3)});
                    zzh.setTransactionSuccessful();
                    zzh.endTransaction();
                    zzh.close();
                    return true;
                } catch (SQLiteDatabaseLockedException unused) {
                    SystemClock.sleep(i2);
                    i2 += 20;
                    i = 0 == 0 ? i + 1 : 0;
                    sQLiteDatabase.close();
                } catch (SQLiteFullException e) {
                    this.zzt.zzay().zzd().zzb("Error deleting app launch break from local database", e);
                    this.zzb = true;
                    if (0 == 0) {
                    }
                    sQLiteDatabase.close();
                } catch (SQLiteException e2) {
                    if (0 != 0) {
                        try {
                            if (sQLiteDatabase.inTransaction()) {
                                sQLiteDatabase.endTransaction();
                            }
                        } catch (Throwable th) {
                            if (0 != 0) {
                                sQLiteDatabase.close();
                            }
                            throw th;
                        }
                    }
                    this.zzt.zzay().zzd().zzb("Error deleting app launch break from local database", e2);
                    this.zzb = true;
                    if (0 != 0) {
                        sQLiteDatabase.close();
                    }
                }
            }
            this.zzt.zzay().zzk().zza("Error deleting app launch break from local database in reasonable time");
        }
        return false;
    }

    public final boolean zzn(zzac zzacVar) {
        byte[] zzan = this.zzt.zzv().zzan(zzacVar);
        if (zzan.length > 131072) {
            this.zzt.zzay().zzh().zza("Conditional user property too long for local database. Sending directly to service");
            return false;
        }
        return zzq(2, zzan);
    }

    public final boolean zzo(zzaw zzawVar) {
        Parcel obtain = Parcel.obtain();
        zzax.zza(zzawVar, obtain, 0);
        byte[] marshall = obtain.marshall();
        obtain.recycle();
        if (marshall.length > 131072) {
            this.zzt.zzay().zzh().zza("Event is too long for local database. Sending event directly to service");
            return false;
        }
        return zzq(0, marshall);
    }

    public final boolean zzp(zzkw zzkwVar) {
        Parcel obtain = Parcel.obtain();
        zzkx.zza(zzkwVar, obtain, 0);
        byte[] marshall = obtain.marshall();
        obtain.recycle();
        if (marshall.length > 131072) {
            this.zzt.zzay().zzh().zza("User property too long for local database. Sending directly to service");
            return false;
        }
        return zzq(1, marshall);
    }
}
