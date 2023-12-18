package com.google.android.gms.internal.vision;

import com.google.android.gms.internal.vision.zzjb;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;
/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.3 */
/* loaded from: classes2.dex */
final class zzip extends zziq<zzjb.zzf> {
    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.vision.zziq
    public final boolean zza(zzkk zzkkVar) {
        return zzkkVar instanceof zzjb.zzc;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.vision.zziq
    public final zziu<zzjb.zzf> zza(Object obj) {
        return ((zzjb.zzc) obj).zzc;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.vision.zziq
    public final zziu<zzjb.zzf> zzb(Object obj) {
        return ((zzjb.zzc) obj).zza();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.vision.zziq
    public final void zzc(Object obj) {
        zza(obj).zzb();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00f8, code lost:
        if (r5 != 18) goto L17;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v0, types: [com.google.android.gms.internal.vision.zzjh] */
    /* JADX WARN: Type inference failed for: r3v18, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r3v22 */
    /* JADX WARN: Type inference failed for: r3v23 */
    /* JADX WARN: Type inference failed for: r3v24 */
    /* JADX WARN: Type inference failed for: r3v25 */
    /* JADX WARN: Type inference failed for: r3v26 */
    /* JADX WARN: Type inference failed for: r3v27 */
    /* JADX WARN: Type inference failed for: r3v28 */
    /* JADX WARN: Type inference failed for: r3v29 */
    /* JADX WARN: Type inference failed for: r3v30 */
    /* JADX WARN: Type inference failed for: r3v31 */
    /* JADX WARN: Type inference failed for: r3v32 */
    /* JADX WARN: Type inference failed for: r3v33 */
    /* JADX WARN: Type inference failed for: r3v34 */
    /* JADX WARN: Type inference failed for: r3v35 */
    /* JADX WARN: Type inference failed for: r3v36 */
    /* JADX WARN: Type inference failed for: r3v37 */
    /* JADX WARN: Type inference failed for: r3v38 */
    /* JADX WARN: Type inference failed for: r3v39 */
    @Override // com.google.android.gms.internal.vision.zziq
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final <UT, UB> UB zza(com.google.android.gms.internal.vision.zzld r5, java.lang.Object r6, com.google.android.gms.internal.vision.zzio r7, com.google.android.gms.internal.vision.zziu<com.google.android.gms.internal.vision.zzjb.zzf> r8, UB r9, com.google.android.gms.internal.vision.zzlu<UT, UB> r10) throws java.io.IOException {
        java.lang.Object r3;
        com.google.android.gms.internal.vision.zzjb.zze r6 = (com.google.android.gms.internal.vision.zzjb.zze) r6;
        int r0 = r6.zzd.zzb;
        boolean r1 = r6.zzd.zzd;
        ?? r3 = 0;
        if (r6.zzd.zzc == com.google.android.gms.internal.vision.zzml.zzn) {
            int r5 = r5.zzh();
            if (r3.zza(r5) == null) {
                return (UB) com.google.android.gms.internal.vision.zzle.zza(r0, r5, r9, r10);
            }
            r3 = java.lang.Integer.valueOf(r5);
        } else {
            switch (com.google.android.gms.internal.vision.zzis.zza[r6.zzd.zzc.ordinal()]) {
                case 1:
                    r3 = java.lang.Double.valueOf(r5.zzd());
                    break;
                case 2:
                    r3 = java.lang.Float.valueOf(r5.zze());
                    break;
                case 3:
                    r3 = java.lang.Long.valueOf(r5.zzg());
                    break;
                case 4:
                    r3 = java.lang.Long.valueOf(r5.zzf());
                    break;
                case 5:
                    r3 = java.lang.Integer.valueOf(r5.zzh());
                    break;
                case 6:
                    r3 = java.lang.Long.valueOf(r5.zzi());
                    break;
                case 7:
                    r3 = java.lang.Integer.valueOf(r5.zzj());
                    break;
                case 8:
                    r3 = java.lang.Boolean.valueOf(r5.zzk());
                    break;
                case 9:
                    r3 = java.lang.Integer.valueOf(r5.zzo());
                    break;
                case 10:
                    r3 = java.lang.Integer.valueOf(r5.zzq());
                    break;
                case 11:
                    r3 = java.lang.Long.valueOf(r5.zzr());
                    break;
                case 12:
                    r3 = java.lang.Integer.valueOf(r5.zzs());
                    break;
                case 13:
                    r3 = java.lang.Long.valueOf(r5.zzt());
                    break;
                case 14:
                    throw new java.lang.IllegalStateException("Shouldn't reach here.");
                case 15:
                    r3 = r5.zzn();
                    break;
                case 16:
                    r3 = r5.zzl();
                    break;
                case 17:
                    r3 = r5.zzb(r6.zzc.getClass(), r7);
                    break;
                case 18:
                    r3 = r5.zza(r6.zzc.getClass(), r7);
                    break;
            }
        }
        if (r6.zzd.zzd) {
            r8.zzb(r6.zzd, r3);
        } else {
            int r5 = com.google.android.gms.internal.vision.zzis.zza[r6.zzd.zzc.ordinal()];
            if (r5 != 17) {
                r3 = r3;
            }
            java.lang.Object r5 = r8.zza((com.google.android.gms.internal.vision.zziu<com.google.android.gms.internal.vision.zzjb.zzf>) r6.zzd);
            r3 = r3;
            if (r5 != null) {
                r3 = com.google.android.gms.internal.vision.zzjf.zza(r5, (java.lang.Object) r3);
            }
            r8.zza((com.google.android.gms.internal.vision.zziu<com.google.android.gms.internal.vision.zzjb.zzf>) r6.zzd, r3);
        }
        return r9;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.vision.zziq
    public final int zza(Map.Entry<?, ?> entry) {
        return ((zzjb.zzf) entry.getKey()).zzb;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.vision.zziq
    public final void zza(zzmr zzmrVar, Map.Entry<?, ?> entry) throws IOException {
        zzjb.zzf zzfVar = (zzjb.zzf) entry.getKey();
        if (zzfVar.zzd) {
            switch (zzis.zza[zzfVar.zzc.ordinal()]) {
                case 1:
                    zzle.zza(zzfVar.zzb, (List<Double>) entry.getValue(), zzmrVar, false);
                    return;
                case 2:
                    zzle.zzb(zzfVar.zzb, (List<Float>) entry.getValue(), zzmrVar, false);
                    return;
                case 3:
                    zzle.zzc(zzfVar.zzb, (List) entry.getValue(), zzmrVar, false);
                    return;
                case 4:
                    zzle.zzd(zzfVar.zzb, (List) entry.getValue(), zzmrVar, false);
                    return;
                case 5:
                    zzle.zzh(zzfVar.zzb, (List) entry.getValue(), zzmrVar, false);
                    return;
                case 6:
                    zzle.zzf(zzfVar.zzb, (List) entry.getValue(), zzmrVar, false);
                    return;
                case 7:
                    zzle.zzk(zzfVar.zzb, (List) entry.getValue(), zzmrVar, false);
                    return;
                case 8:
                    zzle.zzn(zzfVar.zzb, (List) entry.getValue(), zzmrVar, false);
                    return;
                case 9:
                    zzle.zzi(zzfVar.zzb, (List) entry.getValue(), zzmrVar, false);
                    return;
                case 10:
                    zzle.zzl(zzfVar.zzb, (List) entry.getValue(), zzmrVar, false);
                    return;
                case 11:
                    zzle.zzg(zzfVar.zzb, (List) entry.getValue(), zzmrVar, false);
                    return;
                case 12:
                    zzle.zzj(zzfVar.zzb, (List) entry.getValue(), zzmrVar, false);
                    return;
                case 13:
                    zzle.zze(zzfVar.zzb, (List) entry.getValue(), zzmrVar, false);
                    return;
                case 14:
                    zzle.zzh(zzfVar.zzb, (List) entry.getValue(), zzmrVar, false);
                    return;
                case 15:
                    zzle.zzb(zzfVar.zzb, (List) entry.getValue(), zzmrVar);
                    return;
                case 16:
                    zzle.zza(zzfVar.zzb, (List) entry.getValue(), zzmrVar);
                    return;
                case 17:
                    List list = (List) entry.getValue();
                    if (list == null || list.isEmpty()) {
                        return;
                    }
                    zzle.zzb(zzfVar.zzb, (List) entry.getValue(), zzmrVar, zzky.zza().zza((Class) list.get(0).getClass()));
                    return;
                case 18:
                    List list2 = (List) entry.getValue();
                    if (list2 == null || list2.isEmpty()) {
                        return;
                    }
                    zzle.zza(zzfVar.zzb, (List) entry.getValue(), zzmrVar, zzky.zza().zza((Class) list2.get(0).getClass()));
                    return;
                default:
                    return;
            }
        }
        switch (zzis.zza[zzfVar.zzc.ordinal()]) {
            case 1:
                zzmrVar.zza(zzfVar.zzb, ((Double) entry.getValue()).doubleValue());
                return;
            case 2:
                zzmrVar.zza(zzfVar.zzb, ((Float) entry.getValue()).floatValue());
                return;
            case 3:
                zzmrVar.zza(zzfVar.zzb, ((Long) entry.getValue()).longValue());
                return;
            case 4:
                zzmrVar.zzc(zzfVar.zzb, ((Long) entry.getValue()).longValue());
                return;
            case 5:
                zzmrVar.zzc(zzfVar.zzb, ((Integer) entry.getValue()).intValue());
                return;
            case 6:
                zzmrVar.zzd(zzfVar.zzb, ((Long) entry.getValue()).longValue());
                return;
            case 7:
                zzmrVar.zzd(zzfVar.zzb, ((Integer) entry.getValue()).intValue());
                return;
            case 8:
                zzmrVar.zza(zzfVar.zzb, ((Boolean) entry.getValue()).booleanValue());
                return;
            case 9:
                zzmrVar.zze(zzfVar.zzb, ((Integer) entry.getValue()).intValue());
                return;
            case 10:
                zzmrVar.zza(zzfVar.zzb, ((Integer) entry.getValue()).intValue());
                return;
            case 11:
                zzmrVar.zzb(zzfVar.zzb, ((Long) entry.getValue()).longValue());
                return;
            case 12:
                zzmrVar.zzf(zzfVar.zzb, ((Integer) entry.getValue()).intValue());
                return;
            case 13:
                zzmrVar.zze(zzfVar.zzb, ((Long) entry.getValue()).longValue());
                return;
            case 14:
                zzmrVar.zzc(zzfVar.zzb, ((Integer) entry.getValue()).intValue());
                return;
            case 15:
                zzmrVar.zza(zzfVar.zzb, (zzht) entry.getValue());
                return;
            case 16:
                zzmrVar.zza(zzfVar.zzb, (String) entry.getValue());
                return;
            case 17:
                zzmrVar.zzb(zzfVar.zzb, entry.getValue(), zzky.zza().zza((Class) entry.getValue().getClass()));
                return;
            case 18:
                zzmrVar.zza(zzfVar.zzb, entry.getValue(), zzky.zza().zza((Class) entry.getValue().getClass()));
                return;
            default:
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.vision.zziq
    public final Object zza(zzio zzioVar, zzkk zzkkVar, int i) {
        return zzioVar.zza(zzkkVar, i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.vision.zziq
    public final void zza(zzld zzldVar, Object obj, zzio zzioVar, zziu<zzjb.zzf> zziuVar) throws IOException {
        zzjb.zze zzeVar = (zzjb.zze) obj;
        zziuVar.zza((zziu<zzjb.zzf>) zzeVar.zzd, zzldVar.zza(zzeVar.zzc.getClass(), zzioVar));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.vision.zziq
    public final void zza(zzht zzhtVar, Object obj, zzio zzioVar, zziu<zzjb.zzf> zziuVar) throws IOException {
        byte[] bArr;
        zzjb.zze zzeVar = (zzjb.zze) obj;
        zzkk zze = zzeVar.zzc.zzq().zze();
        int zza = zzhtVar.zza();
        if (zza == 0) {
            bArr = zzjf.zzb;
        } else {
            byte[] bArr2 = new byte[zza];
            zzhtVar.zza(bArr2, 0, 0, zza);
            bArr = bArr2;
        }
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        if (wrap.hasArray()) {
            zzho zzhoVar = new zzho(wrap, true);
            zzky.zza().zza((zzky) zze).zza(zze, zzhoVar, zzioVar);
            zziuVar.zza((zziu<zzjb.zzf>) zzeVar.zzd, zze);
            if (zzhoVar.zza() != Integer.MAX_VALUE) {
                throw zzjk.zze();
            }
            return;
        }
        throw new IllegalArgumentException("Direct buffers not yet supported");
    }
}
