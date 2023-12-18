package com.google.android.gms.internal.p001firebaseauthapi;

import sun.misc.Unsafe;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzafv  reason: invalid package */
/* loaded from: classes.dex */
final class zzafv extends zzafw {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzafv(Unsafe unsafe) {
        super(unsafe);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzafw
    public final double zza(Object obj, long j) {
        return Double.longBitsToDouble(zzk(obj, j));
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzafw
    public final float zzb(Object obj, long j) {
        return Float.intBitsToFloat(zzj(obj, j));
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzafw
    public final void zzc(Object obj, long j, boolean z) {
        if (zzafx.zzb) {
            zzafx.zzD(obj, j, r3 ? (byte) 1 : (byte) 0);
        } else {
            zzafx.zzE(obj, j, r3 ? (byte) 1 : (byte) 0);
        }
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzafw
    public final void zzd(Object obj, long j, byte b) {
        if (zzafx.zzb) {
            zzafx.zzD(obj, j, b);
        } else {
            zzafx.zzE(obj, j, b);
        }
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzafw
    public final void zze(Object obj, long j, double d) {
        zzo(obj, j, Double.doubleToLongBits(d));
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzafw
    public final void zzf(Object obj, long j, float f) {
        zzn(obj, j, Float.floatToIntBits(f));
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzafw
    public final boolean zzg(Object obj, long j) {
        if (zzafx.zzb) {
            return zzafx.zzt(obj, j);
        }
        return zzafx.zzu(obj, j);
    }
}
