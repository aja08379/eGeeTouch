package com.google.android.gms.internal.vision;
/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.3 */
/* loaded from: classes2.dex */
public final class zzel<K, V> extends zzek<K, V> {
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0046, code lost:
        if (r9.zzf() == false) goto L15;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.google.android.gms.internal.vision.zzem<K, V> zza() {
        com.google.android.gms.internal.vision.zzej r9;
        java.util.Set<java.util.Map.Entry<K, java.util.Collection<V>>> r1 = r19.zza.entrySet();
        if (r1.isEmpty()) {
            return com.google.android.gms.internal.vision.zzdz.zza;
        }
        com.google.android.gms.internal.vision.zzei r2 = new com.google.android.gms.internal.vision.zzei(r1.size());
        java.util.Iterator<java.util.Map.Entry<K, java.util.Collection<V>>> r1 = r1.iterator();
        int r4 = 0;
        while (true) {
            int r7 = 1;
            if (r1.hasNext()) {
                java.util.Map.Entry<K, java.util.Collection<V>> r5 = r1.next();
                K r8 = r5.getKey();
                java.util.Collection<V> r5 = r5.getValue();
                if ((r5 instanceof com.google.android.gms.internal.vision.zzej) && !(r5 instanceof java.util.SortedSet)) {
                    r9 = (com.google.android.gms.internal.vision.zzej) r5;
                }
                java.lang.Object[] r5 = r5.toArray();
                int r9 = r5.length;
                while (true) {
                    if (r9 == 0) {
                        r9 = com.google.android.gms.internal.vision.zzev.zza;
                        break;
                    } else if (r9 == r7) {
                        r9 = new com.google.android.gms.internal.vision.zzex(r5[0]);
                        break;
                    } else {
                        int r10 = com.google.android.gms.internal.vision.zzej.zza(r9);
                        java.lang.Object[] r14 = new java.lang.Object[r10];
                        int r15 = r10 - 1;
                        int r12 = 0;
                        int r13 = 0;
                        for (int r11 = 0; r11 < r9; r11++) {
                            java.lang.Object r3 = com.google.android.gms.internal.vision.zzeq.zza(r5[r11], r11);
                            int r16 = r3.hashCode();
                            int r17 = com.google.android.gms.internal.vision.zzec.zza(r16);
                            while (true) {
                                int r18 = r17 & r15;
                                java.lang.Object r7 = r14[r18];
                                if (r7 == null) {
                                    r5[r12] = r3;
                                    r14[r18] = r3;
                                    r13 += r16;
                                    r12++;
                                    break;
                                } else if (!r7.equals(r3)) {
                                    r17++;
                                }
                            }
                        }
                        java.util.Arrays.fill(r5, r12, r9, (java.lang.Object) null);
                        if (r12 == 1) {
                            r9 = new com.google.android.gms.internal.vision.zzex(r5[0], r13);
                            break;
                        } else if (com.google.android.gms.internal.vision.zzej.zza(r12) < r10 / 2) {
                            r9 = r12;
                            r7 = 1;
                        } else {
                            int r3 = r5.length;
                            if (r12 < (r3 >> 1) + (r3 >> 2)) {
                                r5 = java.util.Arrays.copyOf(r5, r12);
                            }
                            r9 = new com.google.android.gms.internal.vision.zzev(r5, r13, r14, r15, r12);
                        }
                    }
                }
                if (!r9.isEmpty()) {
                    int r3 = (r2.zzb + 1) << 1;
                    if (r3 > r2.zza.length) {
                        java.lang.Object[] r6 = r2.zza;
                        int r7 = r2.zza.length;
                        if (r3 < 0) {
                            throw new java.lang.AssertionError("cannot store more than MAX_VALUE elements");
                        }
                        int r7 = r7 + (r7 >> 1) + 1;
                        if (r7 < r3) {
                            r7 = java.lang.Integer.highestOneBit(r3 - 1) << 1;
                        }
                        if (r7 < 0) {
                            r7 = Integer.MAX_VALUE;
                        }
                        r2.zza = java.util.Arrays.copyOf(r6, r7);
                        r2.zzc = false;
                    }
                    com.google.android.gms.internal.vision.zzdq.zza(r8, r9);
                    r2.zza[r2.zzb * 2] = r8;
                    r2.zza[(r2.zzb * 2) + 1] = r9;
                    r2.zzb++;
                    r4 += r9.size();
                }
            } else {
                r2.zzc = true;
                return new com.google.android.gms.internal.vision.zzem<>(com.google.android.gms.internal.vision.zzes.zza(r2.zzb, r2.zza), r4, null);
            }
        }
    }
}
