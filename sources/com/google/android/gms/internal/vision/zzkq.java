package com.google.android.gms.internal.vision;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.3 */
/* loaded from: classes2.dex */
final class zzkq<T> implements zzlc<T> {
    private final zzkk zza;
    private final zzlu<?, ?> zzb;
    private final boolean zzc;
    private final zziq<?> zzd;

    private zzkq(zzlu<?, ?> zzluVar, zziq<?> zziqVar, zzkk zzkkVar) {
        this.zzb = zzluVar;
        this.zzc = zziqVar.zza(zzkkVar);
        this.zzd = zziqVar;
        this.zza = zzkkVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> zzkq<T> zza(zzlu<?, ?> zzluVar, zziq<?> zziqVar, zzkk zzkkVar) {
        return new zzkq<>(zzluVar, zziqVar, zzkkVar);
    }

    @Override // com.google.android.gms.internal.vision.zzlc
    public final T zza() {
        return (T) this.zza.zzq().zze();
    }

    @Override // com.google.android.gms.internal.vision.zzlc
    public final boolean zza(T t, T t2) {
        if (this.zzb.zzb(t).equals(this.zzb.zzb(t2))) {
            if (this.zzc) {
                return this.zzd.zza(t).equals(this.zzd.zza(t2));
            }
            return true;
        }
        return false;
    }

    @Override // com.google.android.gms.internal.vision.zzlc
    public final int zza(T t) {
        int hashCode = this.zzb.zzb(t).hashCode();
        return this.zzc ? (hashCode * 53) + this.zzd.zza(t).hashCode() : hashCode;
    }

    @Override // com.google.android.gms.internal.vision.zzlc
    public final void zzb(T t, T t2) {
        zzle.zza(this.zzb, t, t2);
        if (this.zzc) {
            zzle.zza(this.zzd, t, t2);
        }
    }

    @Override // com.google.android.gms.internal.vision.zzlc
    public final void zza(T t, zzmr zzmrVar) throws IOException {
        Iterator<Map.Entry<?, Object>> zzd = this.zzd.zza(t).zzd();
        while (zzd.hasNext()) {
            Map.Entry<?, Object> next = zzd.next();
            zziw zziwVar = (zziw) next.getKey();
            if (zziwVar.zzc() != zzmo.MESSAGE || zziwVar.zzd() || zziwVar.zze()) {
                throw new IllegalStateException("Found invalid MessageSet item.");
            }
            if (next instanceof zzjr) {
                zzmrVar.zza(zziwVar.zza(), (Object) ((zzjr) next).zza().zzc());
            } else {
                zzmrVar.zza(zziwVar.zza(), next.getValue());
            }
        }
        zzlu<?, ?> zzluVar = this.zzb;
        zzluVar.zzb((zzlu<?, ?>) zzluVar.zzb(t), zzmrVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x00b9  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00be A[EDGE_INSN: B:57:0x00be->B:33:0x00be ?: BREAK  , SYNTHETIC] */
    @Override // com.google.android.gms.internal.vision.zzlc
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void zza(T r11, byte[] r12, int r13, int r14, com.google.android.gms.internal.vision.zzhn r15) throws java.io.IOException {
        com.google.android.gms.internal.vision.zzjb r0 = (com.google.android.gms.internal.vision.zzjb) r11;
        com.google.android.gms.internal.vision.zzlx r1 = r0.zzb;
        if (r1 == com.google.android.gms.internal.vision.zzlx.zza()) {
            r1 = com.google.android.gms.internal.vision.zzlx.zzb();
            r0.zzb = r1;
        }
        com.google.android.gms.internal.vision.zziu<com.google.android.gms.internal.vision.zzjb.zzf> r11 = ((com.google.android.gms.internal.vision.zzjb.zzc) r11).zza();
        com.google.android.gms.internal.vision.zzjb.zze r2 = null;
        while (r13 < r14) {
            int r4 = com.google.android.gms.internal.vision.zzhl.zza(r12, r13, r15);
            int r13 = r15.zza;
            if (r13 == 11) {
                int r13 = 0;
                com.google.android.gms.internal.vision.zzht r3 = null;
                while (r4 < r14) {
                    r4 = com.google.android.gms.internal.vision.zzhl.zza(r12, r4, r15);
                    int r6 = r15.zza;
                    int r7 = r6 >>> 3;
                    int r8 = r6 & 7;
                    if (r7 != 2) {
                        if (r7 == 3) {
                            if (r2 != null) {
                                r4 = com.google.android.gms.internal.vision.zzhl.zza(com.google.android.gms.internal.vision.zzky.zza().zza((java.lang.Class) r2.zzc.getClass()), r12, r4, r14, r15);
                                r11.zza((com.google.android.gms.internal.vision.zziu<com.google.android.gms.internal.vision.zzjb.zzf>) r2.zzd, r15.zzc);
                            } else if (r8 == 2) {
                                r4 = com.google.android.gms.internal.vision.zzhl.zze(r12, r4, r15);
                                r3 = (com.google.android.gms.internal.vision.zzht) r15.zzc;
                            }
                        }
                        if (r6 != 12) {
                            break;
                        }
                        r4 = com.google.android.gms.internal.vision.zzhl.zza(r6, r12, r4, r14, r15);
                    } else if (r8 == 0) {
                        r4 = com.google.android.gms.internal.vision.zzhl.zza(r12, r4, r15);
                        r13 = r15.zza;
                        r2 = (com.google.android.gms.internal.vision.zzjb.zze) r10.zzd.zza(r15.zzd, r10.zza, r13);
                    } else if (r6 != 12) {
                    }
                }
                if (r3 != null) {
                    r1.zza((r13 << 3) | 2, r3);
                }
                r13 = r4;
            } else if ((r13 & 7) == 2) {
                com.google.android.gms.internal.vision.zzjb.zze r8 = (com.google.android.gms.internal.vision.zzjb.zze) r10.zzd.zza(r15.zzd, r10.zza, r13 >>> 3);
                if (r8 != null) {
                    r13 = com.google.android.gms.internal.vision.zzhl.zza(com.google.android.gms.internal.vision.zzky.zza().zza((java.lang.Class) r8.zzc.getClass()), r12, r4, r14, r15);
                    r11.zza((com.google.android.gms.internal.vision.zziu<com.google.android.gms.internal.vision.zzjb.zzf>) r8.zzd, r15.zzc);
                } else {
                    r13 = com.google.android.gms.internal.vision.zzhl.zza(r13, r12, r4, r14, r1, r15);
                }
                r2 = r8;
            } else {
                r13 = com.google.android.gms.internal.vision.zzhl.zza(r13, r12, r4, r14, r15);
            }
        }
        if (r13 != r14) {
            throw com.google.android.gms.internal.vision.zzjk.zzg();
        }
    }

    @Override // com.google.android.gms.internal.vision.zzlc
    public final void zza(T t, zzld zzldVar, zzio zzioVar) throws IOException {
        boolean z;
        zzlu<?, ?> zzluVar = this.zzb;
        zziq<?> zziqVar = this.zzd;
        Object zzc = zzluVar.zzc(t);
        zziu<?> zzb = zziqVar.zzb(t);
        do {
            try {
                if (zzldVar.zza() == Integer.MAX_VALUE) {
                    return;
                }
                int zzb2 = zzldVar.zzb();
                if (zzb2 == 11) {
                    int i = 0;
                    Object obj = null;
                    zzht zzhtVar = null;
                    while (zzldVar.zza() != Integer.MAX_VALUE) {
                        int zzb3 = zzldVar.zzb();
                        if (zzb3 == 16) {
                            i = zzldVar.zzo();
                            obj = zziqVar.zza(zzioVar, this.zza, i);
                        } else if (zzb3 == 26) {
                            if (obj != null) {
                                zziqVar.zza(zzldVar, obj, zzioVar, zzb);
                            } else {
                                zzhtVar = zzldVar.zzn();
                            }
                        } else if (!zzldVar.zzc()) {
                            break;
                        }
                    }
                    if (zzldVar.zzb() != 12) {
                        throw zzjk.zze();
                    } else if (zzhtVar != null) {
                        if (obj != null) {
                            zziqVar.zza(zzhtVar, obj, zzioVar, zzb);
                        } else {
                            zzluVar.zza((zzlu<?, ?>) zzc, i, zzhtVar);
                        }
                    }
                } else if ((zzb2 & 7) == 2) {
                    Object zza = zziqVar.zza(zzioVar, this.zza, zzb2 >>> 3);
                    if (zza != null) {
                        zziqVar.zza(zzldVar, zza, zzioVar, zzb);
                    } else {
                        z = zzluVar.zza((zzlu<?, ?>) zzc, zzldVar);
                        continue;
                    }
                } else {
                    z = zzldVar.zzc();
                    continue;
                }
                z = true;
                continue;
            } finally {
                zzluVar.zzb((Object) t, (T) zzc);
            }
        } while (z);
    }

    @Override // com.google.android.gms.internal.vision.zzlc
    public final void zzc(T t) {
        this.zzb.zzd(t);
        this.zzd.zzc(t);
    }

    @Override // com.google.android.gms.internal.vision.zzlc
    public final boolean zzd(T t) {
        return this.zzd.zza(t).zzf();
    }

    @Override // com.google.android.gms.internal.vision.zzlc
    public final int zzb(T t) {
        zzlu<?, ?> zzluVar = this.zzb;
        int zze = zzluVar.zze(zzluVar.zzb(t)) + 0;
        return this.zzc ? zze + this.zzd.zza(t).zzg() : zze;
    }
}
