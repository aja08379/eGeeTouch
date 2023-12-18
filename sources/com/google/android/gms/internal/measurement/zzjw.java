package com.google.android.gms.internal.measurement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/* compiled from: com.google.android.gms:play-services-measurement-base@@21.2.0 */
/* loaded from: classes.dex */
final class zzjw {
    private static final zzjw zzb = new zzjw(true);
    final zzmk zza = new zzma(16);
    private boolean zzc;
    private boolean zzd;

    private zzjw() {
    }

    public static zzjw zza() {
        throw null;
    }

    /* JADX WARN: Removed duplicated region for block: B:32:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final void zzd(com.google.android.gms.internal.measurement.zzjv r4, java.lang.Object r5) {
        boolean r0;
        com.google.android.gms.internal.measurement.zzne r0 = r4.zzb();
        com.google.android.gms.internal.measurement.zzkn.zze(r5);
        com.google.android.gms.internal.measurement.zzne r1 = com.google.android.gms.internal.measurement.zzne.DOUBLE;
        com.google.android.gms.internal.measurement.zznf r1 = com.google.android.gms.internal.measurement.zznf.INT;
        switch (r0.zza().ordinal()) {
            case 0:
                r0 = r5 instanceof java.lang.Integer;
                if (r0) {
                    return;
                }
                throw new java.lang.IllegalArgumentException(java.lang.String.format("Wrong object type used with protocol message reflection.\nField number: %d, field java type: %s, value type: %s\n", java.lang.Integer.valueOf(r4.zza()), r4.zzb().zza(), r5.getClass().getName()));
            case 1:
                r0 = r5 instanceof java.lang.Long;
                if (r0) {
                }
                throw new java.lang.IllegalArgumentException(java.lang.String.format("Wrong object type used with protocol message reflection.\nField number: %d, field java type: %s, value type: %s\n", java.lang.Integer.valueOf(r4.zza()), r4.zzb().zza(), r5.getClass().getName()));
            case 2:
                r0 = r5 instanceof java.lang.Float;
                if (r0) {
                }
                throw new java.lang.IllegalArgumentException(java.lang.String.format("Wrong object type used with protocol message reflection.\nField number: %d, field java type: %s, value type: %s\n", java.lang.Integer.valueOf(r4.zza()), r4.zzb().zza(), r5.getClass().getName()));
            case 3:
                r0 = r5 instanceof java.lang.Double;
                if (r0) {
                }
                throw new java.lang.IllegalArgumentException(java.lang.String.format("Wrong object type used with protocol message reflection.\nField number: %d, field java type: %s, value type: %s\n", java.lang.Integer.valueOf(r4.zza()), r4.zzb().zza(), r5.getClass().getName()));
            case 4:
                r0 = r5 instanceof java.lang.Boolean;
                if (r0) {
                }
                throw new java.lang.IllegalArgumentException(java.lang.String.format("Wrong object type used with protocol message reflection.\nField number: %d, field java type: %s, value type: %s\n", java.lang.Integer.valueOf(r4.zza()), r4.zzb().zza(), r5.getClass().getName()));
            case 5:
                r0 = r5 instanceof java.lang.String;
                if (r0) {
                }
                throw new java.lang.IllegalArgumentException(java.lang.String.format("Wrong object type used with protocol message reflection.\nField number: %d, field java type: %s, value type: %s\n", java.lang.Integer.valueOf(r4.zza()), r4.zzb().zza(), r5.getClass().getName()));
            case 6:
                if ((r5 instanceof com.google.android.gms.internal.measurement.zzje) || (r5 instanceof byte[])) {
                    return;
                }
                throw new java.lang.IllegalArgumentException(java.lang.String.format("Wrong object type used with protocol message reflection.\nField number: %d, field java type: %s, value type: %s\n", java.lang.Integer.valueOf(r4.zza()), r4.zzb().zza(), r5.getClass().getName()));
            case 7:
                if ((r5 instanceof java.lang.Integer) || (r5 instanceof com.google.android.gms.internal.measurement.zzkh)) {
                    return;
                }
                throw new java.lang.IllegalArgumentException(java.lang.String.format("Wrong object type used with protocol message reflection.\nField number: %d, field java type: %s, value type: %s\n", java.lang.Integer.valueOf(r4.zza()), r4.zzb().zza(), r5.getClass().getName()));
            case 8:
                if ((r5 instanceof com.google.android.gms.internal.measurement.zzlm) || (r5 instanceof com.google.android.gms.internal.measurement.zzkr)) {
                    return;
                }
                throw new java.lang.IllegalArgumentException(java.lang.String.format("Wrong object type used with protocol message reflection.\nField number: %d, field java type: %s, value type: %s\n", java.lang.Integer.valueOf(r4.zza()), r4.zzb().zza(), r5.getClass().getName()));
            default:
                throw new java.lang.IllegalArgumentException(java.lang.String.format("Wrong object type used with protocol message reflection.\nField number: %d, field java type: %s, value type: %s\n", java.lang.Integer.valueOf(r4.zza()), r4.zzb().zza(), r5.getClass().getName()));
        }
    }

    public final /* bridge */ /* synthetic */ Object clone() throws CloneNotSupportedException {
        zzjw zzjwVar = new zzjw();
        for (int i = 0; i < this.zza.zzb(); i++) {
            Map.Entry zzg = this.zza.zzg(i);
            zzjwVar.zzc((zzjv) zzg.getKey(), zzg.getValue());
        }
        for (Map.Entry entry : this.zza.zzc()) {
            zzjwVar.zzc((zzjv) entry.getKey(), entry.getValue());
        }
        zzjwVar.zzd = this.zzd;
        return zzjwVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzjw) {
            return this.zza.equals(((zzjw) obj).zza);
        }
        return false;
    }

    public final int hashCode() {
        return this.zza.hashCode();
    }

    public final void zzb() {
        if (this.zzc) {
            return;
        }
        for (int i = 0; i < this.zza.zzb(); i++) {
            Map.Entry zzg = this.zza.zzg(i);
            if (zzg.getValue() instanceof zzkf) {
                ((zzkf) zzg.getValue()).zzbJ();
            }
        }
        this.zza.zza();
        this.zzc = true;
    }

    public final void zzc(zzjv zzjvVar, Object obj) {
        if (zzjvVar.zzc()) {
            if (!(obj instanceof List)) {
                throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
            }
            ArrayList arrayList = new ArrayList();
            arrayList.addAll((List) obj);
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                zzd(zzjvVar, arrayList.get(i));
            }
            obj = arrayList;
        } else {
            zzd(zzjvVar, obj);
        }
        if (obj instanceof zzkr) {
            this.zzd = true;
        }
        this.zza.put(zzjvVar, obj);
    }

    private zzjw(boolean z) {
        zzb();
        zzb();
    }
}
