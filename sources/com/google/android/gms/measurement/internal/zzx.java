package com.google.android.gms.measurement.internal;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzx extends zzy {
    final /* synthetic */ zzaa zza;
    private final com.google.android.gms.internal.measurement.zzek zzh;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzx(zzaa zzaaVar, String str, int i, com.google.android.gms.internal.measurement.zzek zzekVar) {
        super(str, i);
        this.zza = zzaaVar;
        this.zzh = zzekVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.measurement.internal.zzy
    public final int zza() {
        return this.zzh.zzb();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.measurement.internal.zzy
    public final boolean zzb() {
        return this.zzh.zzo();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.measurement.internal.zzy
    public final boolean zzc() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:127:0x03fb  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x03fe  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x0406 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:132:0x0407  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean zzd(java.lang.Long r16, java.lang.Long r17, com.google.android.gms.internal.measurement.zzft r18, long r19, com.google.android.gms.measurement.internal.zzas r21, boolean r22) {
        java.lang.Boolean r7;
        com.google.android.gms.internal.measurement.zznz.zzc();
        boolean r1 = r15.zza.zzt.zzf().zzs(r15.zzb, com.google.android.gms.measurement.internal.zzdu.zzW);
        long r2 = r15.zzh.zzn() ? r21.zze : r19;
        r5 = null;
        r5 = null;
        r5 = null;
        r5 = null;
        r5 = null;
        r5 = null;
        r5 = null;
        r5 = null;
        r5 = null;
        r5 = null;
        r5 = null;
        java.lang.Boolean r5 = null;
        if (android.util.Log.isLoggable(r15.zza.zzt.zzay().zzq(), 2)) {
            r15.zza.zzt.zzay().zzj().zzd("Evaluating filter. audience, filter, event", java.lang.Integer.valueOf(r15.zzc), r15.zzh.zzp() ? java.lang.Integer.valueOf(r15.zzh.zzb()) : null, r15.zza.zzt.zzj().zzd(r15.zzh.zzg()));
            r15.zza.zzt.zzay().zzj().zzb("Filter definition", r15.zza.zzf.zzu().zzo(r15.zzh));
        }
        if (!r15.zzh.zzp() || r15.zzh.zzb() > 256) {
            r15.zza.zzt.zzay().zzk().zzc("Invalid event filter ID. appId, id", com.google.android.gms.measurement.internal.zzeh.zzn(r15.zzb), java.lang.String.valueOf(r15.zzh.zzp() ? java.lang.Integer.valueOf(r15.zzh.zzb()) : null));
            return false;
        }
        java.lang.Object[] r4 = (r15.zzh.zzk() || r15.zzh.zzm() || r15.zzh.zzn()) ? 1 : null;
        if (!r22 || r4 != null) {
            com.google.android.gms.internal.measurement.zzek r7 = r15.zzh;
            java.lang.String r8 = r18.zzh();
            if (r7.zzo()) {
                java.lang.Boolean r2 = zzh(r2, r7.zzf());
                if (r2 != null) {
                    if (!r2.booleanValue()) {
                        r5 = false;
                    }
                }
                r15.zza.zzt.zzay().zzj().zzb("Event filter result", r5 != null ? "null" : r5);
                if (r5 != null) {
                    return false;
                }
                r15.zzd = true;
                if (r5.booleanValue()) {
                    r15.zze = true;
                    if (r4 != null && r18.zzu()) {
                        java.lang.Long r2 = java.lang.Long.valueOf(r18.zzd());
                        if (r15.zzh.zzm()) {
                            if (r1 && r15.zzh.zzo()) {
                                r2 = r16;
                            }
                            r15.zzg = r2;
                        } else {
                            if (r1 && r15.zzh.zzo()) {
                                r2 = r17;
                            }
                            r15.zzf = r2;
                        }
                    }
                    return true;
                }
                return true;
            }
            java.util.HashSet r2 = new java.util.HashSet();
            java.util.Iterator r3 = r7.zzh().iterator();
            while (true) {
                if (r3.hasNext()) {
                    com.google.android.gms.internal.measurement.zzem r10 = (com.google.android.gms.internal.measurement.zzem) r3.next();
                    if (!r10.zze().isEmpty()) {
                        r2.add(r10.zze());
                    } else {
                        r15.zza.zzt.zzay().zzk().zzb("null or empty param name in filter. event", r15.zza.zzt.zzj().zzd(r8));
                        break;
                    }
                } else {
                    androidx.collection.ArrayMap r3 = new androidx.collection.ArrayMap();
                    java.util.Iterator r10 = r18.zzi().iterator();
                    while (true) {
                        if (r10.hasNext()) {
                            com.google.android.gms.internal.measurement.zzfx r11 = (com.google.android.gms.internal.measurement.zzfx) r10.next();
                            if (r2.contains(r11.zzg())) {
                                if (r11.zzw()) {
                                    r3.put(r11.zzg(), r11.zzw() ? java.lang.Long.valueOf(r11.zzd()) : null);
                                } else if (r11.zzu()) {
                                    r3.put(r11.zzg(), r11.zzu() ? java.lang.Double.valueOf(r11.zza()) : null);
                                } else if (r11.zzy()) {
                                    r3.put(r11.zzg(), r11.zzh());
                                } else {
                                    r15.zza.zzt.zzay().zzk().zzc("Unknown value for param. event, param", r15.zza.zzt.zzj().zzd(r8), r15.zza.zzt.zzj().zze(r11.zzg()));
                                    break;
                                }
                            }
                        } else {
                            java.util.Iterator r2 = r7.zzh().iterator();
                            while (true) {
                                if (r2.hasNext()) {
                                    com.google.android.gms.internal.measurement.zzem r7 = (com.google.android.gms.internal.measurement.zzem) r2.next();
                                    boolean r10 = r7.zzh() && r7.zzg();
                                    java.lang.String r11 = r7.zze();
                                    if (!r11.isEmpty()) {
                                        java.lang.Object r12 = r3.get(r11);
                                        if (r12 instanceof java.lang.Long) {
                                            if (r7.zzi()) {
                                                java.lang.Boolean r7 = zzh(((java.lang.Long) r12).longValue(), r7.zzc());
                                                if (r7 == null) {
                                                    break;
                                                } else if (r7.booleanValue() == r10) {
                                                    r5 = false;
                                                    break;
                                                }
                                            } else {
                                                r15.zza.zzt.zzay().zzk().zzc("No number filter for long param. event, param", r15.zza.zzt.zzj().zzd(r8), r15.zza.zzt.zzj().zze(r11));
                                                break;
                                            }
                                        } else if (r12 instanceof java.lang.Double) {
                                            if (r7.zzi()) {
                                                java.lang.Boolean r7 = zzg(((java.lang.Double) r12).doubleValue(), r7.zzc());
                                                if (r7 == null) {
                                                    break;
                                                } else if (r7.booleanValue() == r10) {
                                                    r5 = false;
                                                    break;
                                                }
                                            } else {
                                                r15.zza.zzt.zzay().zzk().zzc("No number filter for double param. event, param", r15.zza.zzt.zzj().zzd(r8), r15.zza.zzt.zzj().zze(r11));
                                                break;
                                            }
                                        } else if (r12 instanceof java.lang.String) {
                                            if (r7.zzk()) {
                                                r7 = zzf((java.lang.String) r12, r7.zzd(), r15.zza.zzt.zzay());
                                            } else if (r7.zzi()) {
                                                java.lang.String r12 = (java.lang.String) r12;
                                                if (com.google.android.gms.measurement.internal.zzkv.zzx(r12)) {
                                                    r7 = zzi(r12, r7.zzc());
                                                } else {
                                                    r15.zza.zzt.zzay().zzk().zzc("Invalid param value for number filter. event, param", r15.zza.zzt.zzj().zzd(r8), r15.zza.zzt.zzj().zze(r11));
                                                    break;
                                                }
                                            } else {
                                                r15.zza.zzt.zzay().zzk().zzc("No filter for String param. event, param", r15.zza.zzt.zzj().zzd(r8), r15.zza.zzt.zzj().zze(r11));
                                                break;
                                            }
                                            if (r7 == null) {
                                                break;
                                            } else if (r7.booleanValue() == r10) {
                                                r5 = false;
                                                break;
                                            }
                                        } else if (r12 == null) {
                                            r15.zza.zzt.zzay().zzj().zzc("Missing param for filter. event, param", r15.zza.zzt.zzj().zzd(r8), r15.zza.zzt.zzj().zze(r11));
                                            r5 = false;
                                        } else {
                                            r15.zza.zzt.zzay().zzk().zzc("Unknown param type. event, param", r15.zza.zzt.zzj().zzd(r8), r15.zza.zzt.zzj().zze(r11));
                                        }
                                    } else {
                                        r15.zza.zzt.zzay().zzk().zzb("Event has empty param name. event", r15.zza.zzt.zzj().zzd(r8));
                                        break;
                                    }
                                } else {
                                    r5 = true;
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            r15.zza.zzt.zzay().zzj().zzb("Event filter result", r5 != null ? "null" : r5);
            if (r5 != null) {
            }
        } else {
            r15.zza.zzt.zzay().zzj().zzc("Event filter already evaluated true and it is not associated with an enhanced audience. audience ID, filter ID", java.lang.Integer.valueOf(r15.zzc), r15.zzh.zzp() ? java.lang.Integer.valueOf(r15.zzh.zzb()) : null);
            return true;
        }
    }
}
