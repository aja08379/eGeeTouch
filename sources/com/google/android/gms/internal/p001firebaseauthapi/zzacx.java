package com.google.android.gms.internal.p001firebaseauthapi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzacx  reason: invalid package */
/* loaded from: classes.dex */
final class zzacx {
    private static final zzacx zzb = new zzacx(true);
    final zzafj zza = new zzaez(16);
    private boolean zzc;
    private boolean zzd;

    private zzacx() {
    }

    public static zzacx zza() {
        throw null;
    }

    /* JADX WARN: Removed duplicated region for block: B:32:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final void zzd(com.google.android.gms.internal.p001firebaseauthapi.zzacw r4, java.lang.Object r5) {
        boolean r0;
        com.google.android.gms.internal.p001firebaseauthapi.zzagd r0 = r4.zzb();
        com.google.android.gms.internal.p001firebaseauthapi.zzadl.zze(r5);
        com.google.android.gms.internal.p001firebaseauthapi.zzagd r1 = com.google.android.gms.internal.p001firebaseauthapi.zzagd.DOUBLE;
        com.google.android.gms.internal.p001firebaseauthapi.zzage r1 = com.google.android.gms.internal.p001firebaseauthapi.zzage.INT;
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
                if ((r5 instanceof com.google.android.gms.internal.p001firebaseauthapi.zzacc) || (r5 instanceof byte[])) {
                    return;
                }
                throw new java.lang.IllegalArgumentException(java.lang.String.format("Wrong object type used with protocol message reflection.\nField number: %d, field java type: %s, value type: %s\n", java.lang.Integer.valueOf(r4.zza()), r4.zzb().zza(), r5.getClass().getName()));
            case 7:
                if ((r5 instanceof java.lang.Integer) || (r5 instanceof com.google.android.gms.internal.p001firebaseauthapi.zzadh)) {
                    return;
                }
                throw new java.lang.IllegalArgumentException(java.lang.String.format("Wrong object type used with protocol message reflection.\nField number: %d, field java type: %s, value type: %s\n", java.lang.Integer.valueOf(r4.zza()), r4.zzb().zza(), r5.getClass().getName()));
            case 8:
                if ((r5 instanceof com.google.android.gms.internal.p001firebaseauthapi.zzaek) || (r5 instanceof com.google.android.gms.internal.p001firebaseauthapi.zzadp)) {
                    return;
                }
                throw new java.lang.IllegalArgumentException(java.lang.String.format("Wrong object type used with protocol message reflection.\nField number: %d, field java type: %s, value type: %s\n", java.lang.Integer.valueOf(r4.zza()), r4.zzb().zza(), r5.getClass().getName()));
            default:
                throw new java.lang.IllegalArgumentException(java.lang.String.format("Wrong object type used with protocol message reflection.\nField number: %d, field java type: %s, value type: %s\n", java.lang.Integer.valueOf(r4.zza()), r4.zzb().zza(), r5.getClass().getName()));
        }
    }

    public final /* bridge */ /* synthetic */ Object clone() throws CloneNotSupportedException {
        zzacx zzacxVar = new zzacx();
        for (int i = 0; i < this.zza.zzb(); i++) {
            Map.Entry zzg = this.zza.zzg(i);
            zzacxVar.zzc((zzacw) zzg.getKey(), zzg.getValue());
        }
        for (Map.Entry entry : this.zza.zzc()) {
            zzacxVar.zzc((zzacw) entry.getKey(), entry.getValue());
        }
        zzacxVar.zzd = this.zzd;
        return zzacxVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzacx) {
            return this.zza.equals(((zzacx) obj).zza);
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
            if (zzg.getValue() instanceof zzadf) {
                ((zzadf) zzg.getValue()).zzE();
            }
        }
        this.zza.zza();
        this.zzc = true;
    }

    public final void zzc(zzacw zzacwVar, Object obj) {
        if (zzacwVar.zzc()) {
            if (!(obj instanceof List)) {
                throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
            }
            ArrayList arrayList = new ArrayList();
            arrayList.addAll((List) obj);
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                zzd(zzacwVar, arrayList.get(i));
            }
            obj = arrayList;
        } else {
            zzd(zzacwVar, obj);
        }
        if (obj instanceof zzadp) {
            this.zzd = true;
        }
        this.zza.put(zzacwVar, obj);
    }

    private zzacx(boolean z) {
        zzb();
        zzb();
    }
}
