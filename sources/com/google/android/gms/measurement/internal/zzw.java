package com.google.android.gms.measurement.internal;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzw {
    final /* synthetic */ zzaa zza;
    private com.google.android.gms.internal.measurement.zzft zzb;
    private Long zzc;
    private long zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzw(zzaa zzaaVar, zzv zzvVar) {
        this.zza = zzaaVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00e6, code lost:
        if (r14 == null) goto L67;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0100, code lost:
        if (r14 == null) goto L67;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0102, code lost:
        r14.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0105, code lost:
        r0 = null;
     */
    /* JADX WARN: Not initialized variable reg: 14, insn: 0x01e7: MOVE  (r5 I:??[OBJECT, ARRAY]) = (r14 I:??[OBJECT, ARRAY]), block:B:70:0x01e7 */
    /* JADX WARN: Removed duplicated region for block: B:72:0x01ea  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.google.android.gms.internal.measurement.zzft zza(java.lang.String r18, com.google.android.gms.internal.measurement.zzft r19) {
        android.database.Cursor r5;
        android.database.Cursor r14;
        android.database.Cursor r14;
        android.util.Pair r0;
        java.lang.String r0 = r19.zzh();
        java.util.List r9 = r19.zzi();
        r17.zza.zzf.zzu();
        java.lang.Long r4 = (java.lang.Long) com.google.android.gms.measurement.internal.zzkv.zzC(r19, "_eid");
        if (r4 != null) {
            if (r0.equals("_ep")) {
                com.google.android.gms.common.internal.Preconditions.checkNotNull(r4);
                r17.zza.zzf.zzu();
                java.lang.String r10 = (java.lang.String) com.google.android.gms.measurement.internal.zzkv.zzC(r19, "_en");
                if (android.text.TextUtils.isEmpty(r10)) {
                    r17.zza.zzt.zzay().zzh().zzb("Extra parameter without an event name. eventId", r4);
                    return null;
                }
                if (r17.zzb == null || r17.zzc == null || r4.longValue() != r17.zzc.longValue()) {
                    com.google.android.gms.measurement.internal.zzam r13 = r17.zza.zzf.zzi();
                    r13.zzg();
                    r13.zzW();
                    try {
                        try {
                            r14 = r13.zzh().rawQuery("select main_event, children_to_process from main_event_params where app_id=? and event_id=?", new java.lang.String[]{r18, r4.toString()});
                            try {
                                if (r14.moveToFirst()) {
                                    try {
                                        r0 = android.util.Pair.create((com.google.android.gms.internal.measurement.zzft) ((com.google.android.gms.internal.measurement.zzfs) com.google.android.gms.measurement.internal.zzkv.zzl(com.google.android.gms.internal.measurement.zzft.zze(), r14.getBlob(0))).zzaC(), java.lang.Long.valueOf(r14.getLong(1)));
                                        if (r14 != null) {
                                            r14.close();
                                        }
                                    } catch (java.io.IOException r0) {
                                        r13.zzt.zzay().zzd().zzd("Failed to merge main event. appId, eventId", com.google.android.gms.measurement.internal.zzeh.zzn(r18), r4, r0);
                                    }
                                } else {
                                    r13.zzt.zzay().zzj().zza("Main event not found");
                                    if (r14 != null) {
                                        r14.close();
                                    }
                                    r0 = null;
                                }
                            } catch (android.database.sqlite.SQLiteException e) {
                                r0 = e;
                                r13.zzt.zzay().zzd().zzb("Error selecting main event", r0);
                            }
                        } catch (java.lang.Throwable th) {
                            r0 = th;
                            r5 = r14;
                            if (r5 != null) {
                                r5.close();
                            }
                            throw r0;
                        }
                    } catch (android.database.sqlite.SQLiteException e) {
                        r0 = e;
                        r14 = null;
                    } catch (java.lang.Throwable th) {
                        r0 = th;
                        r5 = null;
                        if (r5 != null) {
                        }
                        throw r0;
                    }
                    if (r0 == null || r0.first == null) {
                        r17.zza.zzt.zzay().zzh().zzc("Extra parameter without existing main event. eventName, eventId", r10, r4);
                        return null;
                    }
                    r17.zzb = (com.google.android.gms.internal.measurement.zzft) r0.first;
                    r17.zzd = ((java.lang.Long) r0.second).longValue();
                    r17.zza.zzf.zzu();
                    r17.zzc = (java.lang.Long) com.google.android.gms.measurement.internal.zzkv.zzC(r17.zzb, "_eid");
                }
                long r12 = r17.zzd - 1;
                r17.zzd = r12;
                if (r12 <= 0) {
                    com.google.android.gms.measurement.internal.zzam r2 = r17.zza.zzf.zzi();
                    r2.zzg();
                    r2.zzt.zzay().zzj().zzb("Clearing complex main event info. appId", r18);
                    try {
                        r2.zzh().execSQL("delete from main_event_params where app_id=?", new java.lang.String[]{r18});
                    } catch (android.database.sqlite.SQLiteException r0) {
                        r2.zzt.zzay().zzd().zzb("Error clearing complex main event", r0);
                    }
                } else {
                    r17.zza.zzf.zzi().zzJ(r18, r4, r17.zzd, r17.zzb);
                }
                java.util.ArrayList r0 = new java.util.ArrayList();
                for (com.google.android.gms.internal.measurement.zzfx r3 : r17.zzb.zzi()) {
                    r17.zza.zzf.zzu();
                    if (com.google.android.gms.measurement.internal.zzkv.zzB(r19, r3.zzg()) == null) {
                        r0.add(r3);
                    }
                }
                if (r0.isEmpty()) {
                    r17.zza.zzt.zzay().zzh().zzb("No unique parameters in main event. eventName", r10);
                } else {
                    r0.addAll(r9);
                    r9 = r0;
                }
                r0 = r10;
            } else {
                r17.zzc = r4;
                r17.zzb = r19;
                r17.zza.zzf.zzu();
                java.lang.Object r5 = com.google.android.gms.measurement.internal.zzkv.zzC(r19, "_epc");
                long r10 = ((java.lang.Long) (r5 != null ? r5 : 0L)).longValue();
                r17.zzd = r10;
                if (r10 <= 0) {
                    r17.zza.zzt.zzay().zzh().zzb("Complex event with zero extra param count. eventName", r0);
                } else {
                    r17.zza.zzf.zzi().zzJ(r18, (java.lang.Long) com.google.android.gms.common.internal.Preconditions.checkNotNull(r4), r17.zzd, r19);
                }
            }
        }
        com.google.android.gms.internal.measurement.zzfs r2 = (com.google.android.gms.internal.measurement.zzfs) r19.zzby();
        r2.zzi(r0);
        r2.zzg();
        r2.zzd(r9);
        return (com.google.android.gms.internal.measurement.zzft) r2.zzaC();
    }
}
