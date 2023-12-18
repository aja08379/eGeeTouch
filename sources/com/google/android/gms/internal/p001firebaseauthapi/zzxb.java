package com.google.android.gms.internal.p001firebaseauthapi;

import android.content.Context;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.logging.Logger;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.PhoneAuthCredential;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzxb  reason: invalid package */
/* loaded from: classes.dex */
public final class zzxb {
    private static final Logger zza = new Logger("FirebaseAuth", "FirebaseAuthFallback:");
    private final zzvf zzb;
    private final zzyv zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzxb(FirebaseApp firebaseApp) {
        Preconditions.checkNotNull(firebaseApp);
        Context applicationContext = firebaseApp.getApplicationContext();
        Preconditions.checkNotNull(applicationContext);
        this.zzb = new zzvf(new zzxp(firebaseApp, zzxo.zza(), null, null, null));
        this.zzc = new zzyv(applicationContext);
    }

    private static boolean zzG(long j, boolean z) {
        if (j <= 0 || !z) {
            zza.w("App hash will not be appended to the request.", new Object[0]);
            return false;
        }
        return true;
    }

    public final void zzA(zzsy zzsyVar, zzwz zzwzVar) {
        Preconditions.checkNotNull(zzsyVar);
        Preconditions.checkNotNull(zzwzVar);
        String phoneNumber = zzsyVar.zzb().getPhoneNumber();
        zzxa zzxaVar = new zzxa(zzwzVar, zza);
        if (this.zzc.zzl(phoneNumber)) {
            if (zzsyVar.zzg()) {
                this.zzc.zzj(phoneNumber);
            } else {
                this.zzc.zzi(zzxaVar, phoneNumber);
                return;
            }
        }
        long zza2 = zzsyVar.zza();
        boolean zzh = zzsyVar.zzh();
        zzaau zzb = zzaau.zzb(zzsyVar.zzd(), zzsyVar.zzb().getUid(), zzsyVar.zzb().getPhoneNumber(), zzsyVar.zzc(), zzsyVar.zze(), zzsyVar.zzf());
        if (zzG(zza2, zzh)) {
            zzb.zzd(new zzza(this.zzc.zzc()));
        }
        this.zzc.zzk(phoneNumber, zzxaVar, zza2, zzh);
        this.zzb.zzG(zzb, new zzys(this.zzc, zzxaVar, phoneNumber));
    }

    public final void zzB(zzta zztaVar, zzwz zzwzVar) {
        Preconditions.checkNotNull(zztaVar);
        Preconditions.checkNotNull(zzwzVar);
        this.zzb.zzH(zztaVar.zza(), zztaVar.zzb(), new zzxa(zzwzVar, zza));
    }

    public final void zzC(zztc zztcVar, zzwz zzwzVar) {
        Preconditions.checkNotNull(zztcVar);
        Preconditions.checkNotEmpty(zztcVar.zza());
        Preconditions.checkNotNull(zzwzVar);
        this.zzb.zzI(zztcVar.zza(), new zzxa(zzwzVar, zza));
    }

    public final void zzD(zzte zzteVar, zzwz zzwzVar) {
        Preconditions.checkNotNull(zzteVar);
        Preconditions.checkNotEmpty(zzteVar.zzb());
        Preconditions.checkNotEmpty(zzteVar.zza());
        Preconditions.checkNotNull(zzwzVar);
        this.zzb.zzJ(zzteVar.zzb(), zzteVar.zza(), new zzxa(zzwzVar, zza));
    }

    public final void zzE(zztg zztgVar, zzwz zzwzVar) {
        Preconditions.checkNotNull(zztgVar);
        Preconditions.checkNotEmpty(zztgVar.zzb());
        Preconditions.checkNotNull(zztgVar.zza());
        Preconditions.checkNotNull(zzwzVar);
        this.zzb.zzK(zztgVar.zzb(), zztgVar.zza(), new zzxa(zzwzVar, zza));
    }

    public final void zzF(zzti zztiVar, zzwz zzwzVar) {
        Preconditions.checkNotNull(zztiVar);
        this.zzb.zzL(zzzv.zzc(zztiVar.zza(), zztiVar.zzb(), zztiVar.zzc()), new zzxa(zzwzVar, zza));
    }

    public final void zza(zzqy zzqyVar, zzwz zzwzVar) {
        Preconditions.checkNotNull(zzqyVar);
        Preconditions.checkNotEmpty(zzqyVar.zza());
        Preconditions.checkNotNull(zzwzVar);
        this.zzb.zzg(zzqyVar.zza(), zzqyVar.zzb(), new zzxa(zzwzVar, zza));
    }

    public final void zzb(zzra zzraVar, zzwz zzwzVar) {
        Preconditions.checkNotNull(zzraVar);
        Preconditions.checkNotEmpty(zzraVar.zza());
        Preconditions.checkNotEmpty(zzraVar.zzb());
        Preconditions.checkNotNull(zzwzVar);
        this.zzb.zzh(zzraVar.zza(), zzraVar.zzb(), new zzxa(zzwzVar, zza));
    }

    public final void zzc(zzrc zzrcVar, zzwz zzwzVar) {
        Preconditions.checkNotNull(zzrcVar);
        Preconditions.checkNotEmpty(zzrcVar.zza());
        Preconditions.checkNotEmpty(zzrcVar.zzb());
        Preconditions.checkNotNull(zzwzVar);
        this.zzb.zzi(zzrcVar.zza(), zzrcVar.zzb(), new zzxa(zzwzVar, zza));
    }

    public final void zzd(zzre zzreVar, zzwz zzwzVar) {
        Preconditions.checkNotNull(zzreVar);
        Preconditions.checkNotEmpty(zzreVar.zza());
        Preconditions.checkNotNull(zzwzVar);
        this.zzb.zzj(zzreVar.zza(), zzreVar.zzb(), new zzxa(zzwzVar, zza));
    }

    public final void zze(zzrg zzrgVar, zzwz zzwzVar) {
        Preconditions.checkNotNull(zzrgVar);
        Preconditions.checkNotEmpty(zzrgVar.zza());
        Preconditions.checkNotEmpty(zzrgVar.zzb());
        Preconditions.checkNotNull(zzwzVar);
        this.zzb.zzk(zzrgVar.zza(), zzrgVar.zzb(), zzrgVar.zzc(), new zzxa(zzwzVar, zza));
    }

    public final void zzf(zzri zzriVar, zzwz zzwzVar) {
        Preconditions.checkNotNull(zzriVar);
        Preconditions.checkNotEmpty(zzriVar.zza());
        Preconditions.checkNotEmpty(zzriVar.zzb());
        Preconditions.checkNotNull(zzwzVar);
        this.zzb.zzl(zzriVar.zza(), zzriVar.zzb(), zzriVar.zzc(), new zzxa(zzwzVar, zza));
    }

    public final void zzg(zzrk zzrkVar, zzwz zzwzVar) {
        Preconditions.checkNotNull(zzrkVar);
        Preconditions.checkNotEmpty(zzrkVar.zza());
        Preconditions.checkNotNull(zzwzVar);
        this.zzb.zzm(zzrkVar.zza(), new zzxa(zzwzVar, zza));
    }

    public final void zzh(zzrm zzrmVar, zzwz zzwzVar) {
        Preconditions.checkNotNull(zzrmVar);
        Preconditions.checkNotNull(zzwzVar);
        this.zzb.zzn(zzzi.zzb(zzrmVar.zzb(), (String) Preconditions.checkNotNull(zzrmVar.zza().zzg()), (String) Preconditions.checkNotNull(zzrmVar.zza().getSmsCode()), zzrmVar.zzc()), zzrmVar.zzb(), new zzxa(zzwzVar, zza));
    }

    public final void zzi(zzro zzroVar, zzwz zzwzVar) {
        Preconditions.checkNotNull(zzroVar);
        Preconditions.checkNotNull(zzwzVar);
        this.zzb.zzo(zzzk.zzb(zzroVar.zzb(), (String) Preconditions.checkNotNull(zzroVar.zza().zzg()), (String) Preconditions.checkNotNull(zzroVar.zza().getSmsCode())), new zzxa(zzwzVar, zza));
    }

    public final void zzj(zzrq zzrqVar, zzwz zzwzVar) {
        Preconditions.checkNotNull(zzrqVar);
        Preconditions.checkNotNull(zzwzVar);
        Preconditions.checkNotEmpty(zzrqVar.zza());
        this.zzb.zzp(zzrqVar.zza(), new zzxa(zzwzVar, zza));
    }

    public final void zzk(zzrs zzrsVar, zzwz zzwzVar) {
        Preconditions.checkNotNull(zzrsVar);
        Preconditions.checkNotEmpty(zzrsVar.zza());
        this.zzb.zzq(zzrsVar.zza(), zzrsVar.zzb(), new zzxa(zzwzVar, zza));
    }

    public final void zzl(zzru zzruVar, zzwz zzwzVar) {
        Preconditions.checkNotNull(zzruVar);
        Preconditions.checkNotEmpty(zzruVar.zzb());
        Preconditions.checkNotEmpty(zzruVar.zzc());
        Preconditions.checkNotEmpty(zzruVar.zza());
        Preconditions.checkNotNull(zzwzVar);
        this.zzb.zzr(zzruVar.zzb(), zzruVar.zzc(), zzruVar.zza(), new zzxa(zzwzVar, zza));
    }

    public final void zzm(zzrw zzrwVar, zzwz zzwzVar) {
        Preconditions.checkNotNull(zzrwVar);
        Preconditions.checkNotEmpty(zzrwVar.zzb());
        Preconditions.checkNotNull(zzrwVar.zza());
        Preconditions.checkNotNull(zzwzVar);
        this.zzb.zzs(zzrwVar.zzb(), zzrwVar.zza(), new zzxa(zzwzVar, zza));
    }

    public final void zzn(zzry zzryVar, zzwz zzwzVar) {
        Preconditions.checkNotNull(zzwzVar);
        Preconditions.checkNotNull(zzryVar);
        this.zzb.zzt(Preconditions.checkNotEmpty(zzryVar.zzb()), zzyl.zza((PhoneAuthCredential) Preconditions.checkNotNull(zzryVar.zza())), new zzxa(zzwzVar, zza));
    }

    public final void zzo(zzsa zzsaVar, zzwz zzwzVar) {
        Preconditions.checkNotNull(zzsaVar);
        Preconditions.checkNotEmpty(zzsaVar.zza());
        Preconditions.checkNotNull(zzwzVar);
        this.zzb.zzu(zzsaVar.zza(), new zzxa(zzwzVar, zza));
    }

    public final void zzp(zzsc zzscVar, zzwz zzwzVar) {
        Preconditions.checkNotNull(zzscVar);
        Preconditions.checkNotEmpty(zzscVar.zzb());
        Preconditions.checkNotNull(zzwzVar);
        this.zzb.zzv(zzscVar.zzb(), zzscVar.zza(), new zzxa(zzwzVar, zza));
    }

    public final void zzq(zzse zzseVar, zzwz zzwzVar) {
        Preconditions.checkNotNull(zzseVar);
        Preconditions.checkNotEmpty(zzseVar.zzb());
        Preconditions.checkNotNull(zzwzVar);
        this.zzb.zzw(zzseVar.zzb(), zzseVar.zza(), zzseVar.zzc(), new zzxa(zzwzVar, zza));
    }

    public final void zzr(zzsg zzsgVar, zzwz zzwzVar) {
        Preconditions.checkNotNull(zzwzVar);
        Preconditions.checkNotNull(zzsgVar);
        zzaal zzaalVar = (zzaal) Preconditions.checkNotNull(zzsgVar.zza());
        String zzd = zzaalVar.zzd();
        zzxa zzxaVar = new zzxa(zzwzVar, zza);
        if (this.zzc.zzl(zzd)) {
            if (zzaalVar.zzf()) {
                this.zzc.zzj(zzd);
            } else {
                this.zzc.zzi(zzxaVar, zzd);
                return;
            }
        }
        long zzb = zzaalVar.zzb();
        boolean zzg = zzaalVar.zzg();
        if (zzG(zzb, zzg)) {
            zzaalVar.zze(new zzza(this.zzc.zzc()));
        }
        this.zzc.zzk(zzd, zzxaVar, zzb, zzg);
        this.zzb.zzx(zzaalVar, new zzys(this.zzc, zzxaVar, zzd));
    }

    public final void zzs(zzsi zzsiVar, zzwz zzwzVar) {
        Preconditions.checkNotNull(zzsiVar);
        Preconditions.checkNotNull(zzwzVar);
        this.zzb.zzy(zzsiVar.zza(), new zzxa(zzwzVar, zza));
    }

    public final void zzt(zzsk zzskVar, zzwz zzwzVar) {
        Preconditions.checkNotNull(zzskVar);
        Preconditions.checkNotNull(zzwzVar);
        this.zzb.zzz(zzskVar.zza(), new zzxa(zzwzVar, zza));
    }

    public final void zzu(zzsm zzsmVar, zzwz zzwzVar) {
        Preconditions.checkNotNull(zzsmVar);
        Preconditions.checkNotNull(zzsmVar.zza());
        Preconditions.checkNotNull(zzwzVar);
        this.zzb.zzA(zzsmVar.zza(), new zzxa(zzwzVar, zza));
    }

    public final void zzv(zzso zzsoVar, zzwz zzwzVar) {
        Preconditions.checkNotNull(zzsoVar);
        Preconditions.checkNotEmpty(zzsoVar.zzb());
        Preconditions.checkNotNull(zzwzVar);
        this.zzb.zzB(new zzabb(zzsoVar.zzb(), zzsoVar.zza()), new zzxa(zzwzVar, zza));
    }

    public final void zzw(zzsq zzsqVar, zzwz zzwzVar) {
        Preconditions.checkNotNull(zzsqVar);
        Preconditions.checkNotEmpty(zzsqVar.zza());
        Preconditions.checkNotEmpty(zzsqVar.zzb());
        Preconditions.checkNotNull(zzwzVar);
        this.zzb.zzC(zzsqVar.zza(), zzsqVar.zzb(), zzsqVar.zzc(), new zzxa(zzwzVar, zza));
    }

    public final void zzx(zzss zzssVar, zzwz zzwzVar) {
        Preconditions.checkNotNull(zzssVar);
        Preconditions.checkNotNull(zzssVar.zza());
        Preconditions.checkNotNull(zzwzVar);
        this.zzb.zzD(zzssVar.zza(), new zzxa(zzwzVar, zza));
    }

    public final void zzy(zzsu zzsuVar, zzwz zzwzVar) {
        Preconditions.checkNotNull(zzwzVar);
        Preconditions.checkNotNull(zzsuVar);
        this.zzb.zzE(zzyl.zza((PhoneAuthCredential) Preconditions.checkNotNull(zzsuVar.zza())), new zzxa(zzwzVar, zza));
    }

    public final void zzz(zzsw zzswVar, zzwz zzwzVar) {
        Preconditions.checkNotNull(zzswVar);
        Preconditions.checkNotNull(zzwzVar);
        String zzd = zzswVar.zzd();
        zzxa zzxaVar = new zzxa(zzwzVar, zza);
        if (this.zzc.zzl(zzd)) {
            if (zzswVar.zzg()) {
                this.zzc.zzj(zzd);
            } else {
                this.zzc.zzi(zzxaVar, zzd);
                return;
            }
        }
        long zza2 = zzswVar.zza();
        boolean zzh = zzswVar.zzh();
        zzaas zzb = zzaas.zzb(zzswVar.zzb(), zzswVar.zzd(), zzswVar.zzc(), zzswVar.zze(), zzswVar.zzf());
        if (zzG(zza2, zzh)) {
            zzb.zzd(new zzza(this.zzc.zzc()));
        }
        this.zzc.zzk(zzd, zzxaVar, zza2, zzh);
        this.zzb.zzF(zzb, new zzys(this.zzc, zzxaVar, zzd));
    }
}
