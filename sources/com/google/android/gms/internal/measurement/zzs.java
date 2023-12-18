package com.google.android.gms.internal.measurement;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement@@21.2.0 */
/* loaded from: classes.dex */
public final class zzs extends zzai {
    final boolean zza;
    final boolean zzb;
    final /* synthetic */ zzt zzc;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzs(zzt zztVar, boolean z, boolean z2) {
        super("log");
        this.zzc = zztVar;
        this.zza = z;
        this.zzb = z2;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0083  */
    @Override // com.google.android.gms.internal.measurement.zzai
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.google.android.gms.internal.measurement.zzap zza(com.google.android.gms.internal.measurement.zzg r12, java.util.List r13) {
        int r6;
        com.google.android.gms.internal.measurement.zzr r5;
        com.google.android.gms.internal.measurement.zzr r5;
        com.google.android.gms.internal.measurement.zzr r3;
        com.google.android.gms.internal.measurement.zzh.zzi("log", 1, r13);
        if (r13.size() == 1) {
            r3 = r11.zzc.zza;
            r3.zza(3, r12.zzb((com.google.android.gms.internal.measurement.zzap) r13.get(0)).zzi(), java.util.Collections.emptyList(), r11.zza, r11.zzb);
            return com.google.android.gms.internal.measurement.zzs.zzf;
        }
        int r0 = com.google.android.gms.internal.measurement.zzh.zzb(r12.zzb((com.google.android.gms.internal.measurement.zzap) r13.get(0)).zzh().doubleValue());
        int r2 = 3;
        if (r0 != 2) {
            if (r0 == 3) {
                r6 = 1;
            } else if (r0 == 5) {
                r6 = 5;
            } else if (r0 == 6) {
                r6 = 2;
            }
            java.lang.String r7 = r12.zzb((com.google.android.gms.internal.measurement.zzap) r13.get(1)).zzi();
            if (r13.size() != 2) {
                r5 = r11.zzc.zza;
                r5.zza(r6, r7, java.util.Collections.emptyList(), r11.zza, r11.zzb);
                return com.google.android.gms.internal.measurement.zzs.zzf;
            }
            java.util.ArrayList r8 = new java.util.ArrayList();
            for (int r4 = 2; r4 < java.lang.Math.min(r13.size(), 5); r4++) {
                r8.add(r12.zzb((com.google.android.gms.internal.measurement.zzap) r13.get(r4)).zzi());
            }
            r5 = r11.zzc.zza;
            r5.zza(r6, r7, r8, r11.zza, r11.zzb);
            return com.google.android.gms.internal.measurement.zzs.zzf;
        }
        r2 = 4;
        r6 = r2;
        java.lang.String r7 = r12.zzb((com.google.android.gms.internal.measurement.zzap) r13.get(1)).zzi();
        if (r13.size() != 2) {
        }
    }
}
