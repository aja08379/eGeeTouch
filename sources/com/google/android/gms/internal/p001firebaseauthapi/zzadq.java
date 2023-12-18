package com.google.android.gms.internal.p001firebaseauthapi;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzadq  reason: invalid package */
/* loaded from: classes.dex */
public class zzadq {
    private static final zzacs zzb = zzacs.zza;
    protected volatile zzaek zza;
    private volatile zzacc zzc;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzadq) {
            zzadq zzadqVar = (zzadq) obj;
            zzaek zzaekVar = this.zza;
            zzaek zzaekVar2 = zzadqVar.zza;
            if (zzaekVar == null && zzaekVar2 == null) {
                return zzb().equals(zzadqVar.zzb());
            }
            if (zzaekVar == null || zzaekVar2 == null) {
                if (zzaekVar != null) {
                    zzadqVar.zzc(zzaekVar.zzL());
                    return zzaekVar.equals(zzadqVar.zza);
                }
                zzc(zzaekVar2.zzL());
                return this.zza.equals(zzaekVar2);
            }
            return zzaekVar.equals(zzaekVar2);
        }
        return false;
    }

    public int hashCode() {
        return 1;
    }

    public final int zza() {
        if (this.zzc != null) {
            return ((zzabz) this.zzc).zza.length;
        }
        if (this.zza != null) {
            return this.zza.zzs();
        }
        return 0;
    }

    public final zzacc zzb() {
        if (this.zzc != null) {
            return this.zzc;
        }
        synchronized (this) {
            if (this.zzc != null) {
                return this.zzc;
            }
            if (this.zza == null) {
                this.zzc = zzacc.zzb;
            } else {
                this.zzc = this.zza.zzo();
            }
            return this.zzc;
        }
    }

    protected final void zzc(zzaek zzaekVar) {
        if (this.zza != null) {
            return;
        }
        synchronized (this) {
            if (this.zza == null) {
                try {
                    this.zza = zzaekVar;
                    this.zzc = zzacc.zzb;
                } catch (zzadn unused) {
                    this.zza = zzaekVar;
                    this.zzc = zzacc.zzb;
                }
            }
        }
    }
}
