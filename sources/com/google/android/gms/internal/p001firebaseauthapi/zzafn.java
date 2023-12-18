package com.google.android.gms.internal.p001firebaseauthapi;

import java.io.IOException;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzafn  reason: invalid package */
/* loaded from: classes.dex */
abstract class zzafn {
    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract int zza(Object obj);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract int zzb(Object obj);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract Object zzc(Object obj);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract Object zzd(Object obj);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract Object zze(Object obj, Object obj2);

    abstract Object zzf();

    abstract Object zzg(Object obj);

    abstract void zzh(Object obj, int i, int i2);

    abstract void zzi(Object obj, int i, long j);

    abstract void zzj(Object obj, int i, Object obj2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zzk(Object obj, int i, zzacc zzaccVar);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zzl(Object obj, int i, long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zzm(Object obj);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zzn(Object obj, Object obj2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zzo(Object obj, Object obj2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract boolean zzq(zzaev zzaevVar);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zzr(Object obj, zzaco zzacoVar) throws IOException;

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean zzp(Object obj, zzaev zzaevVar) throws IOException {
        int zzd = zzaevVar.zzd();
        int i = zzd >>> 3;
        int i2 = zzd & 7;
        if (i2 == 0) {
            zzl(obj, i, zzaevVar.zzl());
            return true;
        } else if (i2 == 1) {
            zzi(obj, i, zzaevVar.zzk());
            return true;
        } else if (i2 == 2) {
            zzk(obj, i, zzaevVar.zzp());
            return true;
        } else if (i2 != 3) {
            if (i2 != 4) {
                if (i2 == 5) {
                    zzh(obj, i, zzaevVar.zzf());
                    return true;
                }
                throw zzadn.zza();
            }
            return false;
        } else {
            Object zzf = zzf();
            int i3 = 4 | (i << 3);
            while (zzaevVar.zzc() != Integer.MAX_VALUE && zzp(zzf, zzaevVar)) {
            }
            if (i3 != zzaevVar.zzd()) {
                throw zzadn.zzb();
            }
            zzg(zzf);
            zzj(obj, i, zzf);
            return true;
        }
    }
}
