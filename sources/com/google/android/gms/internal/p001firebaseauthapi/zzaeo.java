package com.google.android.gms.internal.p001firebaseauthapi;

import java.io.IOException;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaeo  reason: invalid package */
/* loaded from: classes.dex */
final class zzaeo implements zzaew {
    private final zzaek zza;
    private final zzafn zzb;
    private final boolean zzc;
    private final zzact zzd;

    private zzaeo(zzafn zzafnVar, zzact zzactVar, zzaek zzaekVar) {
        this.zzb = zzafnVar;
        this.zzc = zzactVar.zzh(zzaekVar);
        this.zzd = zzactVar;
        this.zza = zzaekVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzaeo zzc(zzafn zzafnVar, zzact zzactVar, zzaek zzaekVar) {
        return new zzaeo(zzafnVar, zzactVar, zzaekVar);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaew
    public final int zza(Object obj) {
        zzafn zzafnVar = this.zzb;
        int zzb = zzafnVar.zzb(zzafnVar.zzd(obj));
        if (this.zzc) {
            this.zzd.zza(obj);
            throw null;
        }
        return zzb;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaew
    public final int zzb(Object obj) {
        int hashCode = this.zzb.zzd(obj).hashCode();
        if (this.zzc) {
            this.zzd.zza(obj);
            throw null;
        }
        return hashCode;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaew
    public final Object zze() {
        zzaek zzaekVar = this.zza;
        if (zzaekVar instanceof zzadf) {
            return ((zzadf) zzaekVar).zzw();
        }
        return zzaekVar.zzB().zzk();
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaew
    public final void zzf(Object obj) {
        this.zzb.zzm(obj);
        this.zzd.zze(obj);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaew
    public final void zzg(Object obj, Object obj2) {
        zzaey.zzF(this.zzb, obj, obj2);
        if (this.zzc) {
            zzaey.zzE(this.zzd, obj, obj2);
        }
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaew
    public final void zzh(Object obj, zzaev zzaevVar, zzacs zzacsVar) throws IOException {
        boolean zzO;
        zzafn zzafnVar = this.zzb;
        zzact zzactVar = this.zzd;
        Object zzc = zzafnVar.zzc(obj);
        zzacx zzb = zzactVar.zzb(obj);
        while (zzaevVar.zzc() != Integer.MAX_VALUE) {
            try {
                int zzd = zzaevVar.zzd();
                if (zzd != 11) {
                    if ((zzd & 7) == 2) {
                        Object zzc2 = zzactVar.zzc(zzacsVar, this.zza, zzd >>> 3);
                        if (zzc2 != null) {
                            zzactVar.zzf(zzaevVar, zzc2, zzacsVar, zzb);
                        } else {
                            zzO = zzafnVar.zzp(zzc, zzaevVar);
                        }
                    } else {
                        zzO = zzaevVar.zzO();
                    }
                    if (!zzO) {
                        return;
                    }
                } else {
                    int i = 0;
                    Object obj2 = null;
                    zzacc zzaccVar = null;
                    while (zzaevVar.zzc() != Integer.MAX_VALUE) {
                        int zzd2 = zzaevVar.zzd();
                        if (zzd2 == 16) {
                            i = zzaevVar.zzj();
                            obj2 = zzactVar.zzc(zzacsVar, this.zza, i);
                        } else if (zzd2 == 26) {
                            if (obj2 != null) {
                                zzactVar.zzf(zzaevVar, obj2, zzacsVar, zzb);
                            } else {
                                zzaccVar = zzaevVar.zzp();
                            }
                        } else if (!zzaevVar.zzO()) {
                            break;
                        }
                    }
                    if (zzaevVar.zzd() != 12) {
                        throw zzadn.zzb();
                    } else if (zzaccVar != null) {
                        if (obj2 != null) {
                            zzactVar.zzg(zzaccVar, obj2, zzacsVar, zzb);
                        } else {
                            zzafnVar.zzk(zzc, i, zzaccVar);
                        }
                    }
                }
            } finally {
                zzafnVar.zzn(obj, zzc);
            }
        }
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaew
    public final void zzi(Object obj, byte[] bArr, int i, int i2, zzabp zzabpVar) throws IOException {
        zzadf zzadfVar = (zzadf) obj;
        if (zzadfVar.zzc == zzafo.zzc()) {
            zzadfVar.zzc = zzafo.zzf();
        }
        zzadc zzadcVar = (zzadc) obj;
        throw null;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaew
    public final boolean zzj(Object obj, Object obj2) {
        if (this.zzb.zzd(obj).equals(this.zzb.zzd(obj2))) {
            if (this.zzc) {
                this.zzd.zza(obj);
                this.zzd.zza(obj2);
                throw null;
            }
            return true;
        }
        return false;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaew
    public final boolean zzk(Object obj) {
        this.zzd.zza(obj);
        throw null;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaew
    public final void zzn(Object obj, zzaco zzacoVar) throws IOException {
        this.zzd.zza(obj);
        throw null;
    }
}
