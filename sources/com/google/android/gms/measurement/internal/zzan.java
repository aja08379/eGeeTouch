package com.google.android.gms.measurement.internal;

import android.database.sqlite.SQLiteDatabase;
import java.io.File;
/* compiled from: com.google.android.gms:play-services-measurement-impl@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzan {
    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0029, code lost:
        if (r0 == false) goto L11;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00e6  */
    /* JADX WARN: Type inference failed for: r13v0 */
    /* JADX WARN: Type inference failed for: r13v1, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r13v2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void zza(com.google.android.gms.measurement.internal.zzeh r14, android.database.sqlite.SQLiteDatabase r15, java.lang.String r16, java.lang.String r17, java.lang.String r18, java.lang.String[] r19) throws android.database.sqlite.SQLiteException {
        android.database.Cursor r1;
        java.lang.String[] r1;
        if (r14 == null) {
            throw new java.lang.IllegalArgumentException("Monitor must not be null");
        }
        ?? r13 = 0;
        try {
            try {
                r1 = r15.query("SQLITE_MASTER", new java.lang.String[]{"name"}, "name=?", new java.lang.String[]{r16}, null, null, null);
                try {
                    boolean r0 = r1.moveToFirst();
                    if (r1 != null) {
                        r1.close();
                    }
                } catch (android.database.sqlite.SQLiteException e) {
                    r0 = e;
                    r14.zzk().zzc("Error querying for table", r16, r0);
                    if (r1 != null) {
                        r1.close();
                    }
                    r15.execSQL(r17);
                    try {
                        java.util.HashSet r0 = new java.util.HashSet();
                        android.database.Cursor r1 = r15.rawQuery("SELECT * FROM " + r16 + " LIMIT 0", null);
                        java.util.Collections.addAll(r0, r1.getColumnNames());
                        r1.close();
                        for (java.lang.String r4 : r18.split(",")) {
                            if (!r0.remove(r4)) {
                                throw new android.database.sqlite.SQLiteException("Table " + r16 + " is missing required column: " + r4);
                            }
                        }
                        if (r19 != null) {
                            for (int r12 = 0; r12 < r19.length; r12 += 2) {
                                if (!r0.remove(r19[r12])) {
                                    r15.execSQL(r19[r12 + 1]);
                                }
                            }
                        }
                        if (r0.isEmpty()) {
                            return;
                        }
                        r14.zzk().zzc("Table has extra columns. table, columns", r16, android.text.TextUtils.join(", ", r0));
                    } catch (android.database.sqlite.SQLiteException r0) {
                        r14.zzd().zzb("Failed to verify columns on table that was just created", r16);
                        throw r0;
                    }
                }
            } catch (java.lang.Throwable th) {
                r0 = th;
                r13 = r17;
                if (r13 != 0) {
                    r13.close();
                }
                throw r0;
            }
        } catch (android.database.sqlite.SQLiteException e) {
            r0 = e;
            r1 = null;
        } catch (java.lang.Throwable th) {
            r0 = th;
            if (r13 != 0) {
            }
            throw r0;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zzb(zzeh zzehVar, SQLiteDatabase sQLiteDatabase) {
        if (zzehVar != null) {
            File file = new File(sQLiteDatabase.getPath());
            if (!file.setReadable(false, false)) {
                zzehVar.zzk().zza("Failed to turn off database read permission");
            }
            if (!file.setWritable(false, false)) {
                zzehVar.zzk().zza("Failed to turn off database write permission");
            }
            if (!file.setReadable(true, true)) {
                zzehVar.zzk().zza("Failed to turn on database read permission for owner");
            }
            if (file.setWritable(true, true)) {
                return;
            }
            zzehVar.zzk().zza("Failed to turn on database write permission for owner");
            return;
        }
        throw new IllegalArgumentException("Monitor must not be null");
    }
}
