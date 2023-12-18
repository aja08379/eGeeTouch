package com.google.android.gms.internal.common;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-basement@@18.1.0 */
/* loaded from: classes.dex */
public abstract class zzw extends zzj {
    final CharSequence zzb;
    final zzo zzc;
    final boolean zzd;
    int zze = 0;
    int zzf;

    /* JADX INFO: Access modifiers changed from: protected */
    public zzw(zzx zzxVar, CharSequence charSequence) {
        zzo zzoVar;
        boolean z;
        zzoVar = zzxVar.zza;
        this.zzc = zzoVar;
        z = zzxVar.zzb;
        this.zzd = z;
        this.zzf = Integer.MAX_VALUE;
        this.zzb = charSequence;
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x0047, code lost:
        r3 = r5.zzf;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x004a, code lost:
        if (r3 != 1) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x004c, code lost:
        r1 = r5.zzb.length();
        r5.zze = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0054, code lost:
        if (r1 <= r0) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0056, code lost:
        r5.zzb.charAt(r1 - 1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x005e, code lost:
        r5.zzf = r3 - 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:?, code lost:
        return r5.zzb.subSequence(r0, r1).toString();
     */
    @Override // com.google.android.gms.internal.common.zzj
    @javax.annotation.CheckForNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected final /* bridge */ /* synthetic */ java.lang.Object zza() {
        int r3;
        int r0 = r5.zze;
        while (true) {
            int r1 = r5.zze;
            if (r1 == -1) {
                zzb();
                return null;
            }
            int r1 = zzd(r1);
            if (r1 == -1) {
                r1 = r5.zzb.length();
                r5.zze = -1;
                r3 = -1;
            } else {
                r3 = zzc(r1);
                r5.zze = r3;
            }
            if (r3 == r0) {
                int r3 = r3 + 1;
                r5.zze = r3;
                if (r3 > r5.zzb.length()) {
                    r5.zze = -1;
                }
            } else {
                if (r0 < r1) {
                    r5.zzb.charAt(r0);
                }
                if (r0 < r1) {
                    r5.zzb.charAt(r1 - 1);
                }
                if (!r5.zzd || r0 != r1) {
                    break;
                }
                r0 = r5.zze;
            }
        }
    }

    abstract int zzc(int i);

    abstract int zzd(int i);
}
