package com.google.firebase.auth.internal;

import com.google.android.gms.internal.p001firebaseauthapi.zzaaj;
import com.google.firebase.auth.ActionCodeInfo;
import com.google.firebase.auth.ActionCodeResult;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* loaded from: classes2.dex */
public final class zzo implements ActionCodeResult {
    private final int zza;
    private final String zzb;
    private final String zzc;
    private final ActionCodeInfo zzd;

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public zzo(zzaaj zzaajVar) {
        char c;
        this.zzb = zzaajVar.zzh() ? zzaajVar.zzd() : zzaajVar.zzc();
        this.zzc = zzaajVar.zzc();
        ActionCodeInfo actionCodeInfo = null;
        if (!zzaajVar.zzi()) {
            this.zza = 3;
            this.zzd = null;
            return;
        }
        String zze = zzaajVar.zze();
        switch (zze.hashCode()) {
            case -1874510116:
                if (zze.equals("REVERT_SECOND_FACTOR_ADDITION")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case -1452371317:
                if (zze.equals("PASSWORD_RESET")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1341836234:
                if (zze.equals("VERIFY_EMAIL")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -1099157829:
                if (zze.equals("VERIFY_AND_CHANGE_EMAIL")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 870738373:
                if (zze.equals("EMAIL_SIGNIN")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 970484929:
                if (zze.equals("RECOVER_EMAIL")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        int i = c != 0 ? c != 1 ? c != 2 ? c != 3 ? c != 4 ? c != 5 ? 3 : 6 : 2 : 5 : 4 : 1 : 0;
        this.zza = i;
        if (i == 4 || i == 3) {
            this.zzd = null;
            return;
        }
        if (zzaajVar.zzg()) {
            actionCodeInfo = new zzn(zzaajVar.zzc(), zzba.zza(zzaajVar.zzb()));
        } else if (zzaajVar.zzh()) {
            actionCodeInfo = new zzl(zzaajVar.zzd(), zzaajVar.zzc());
        } else if (zzaajVar.zzf()) {
            actionCodeInfo = new zzm(zzaajVar.zzc());
        }
        this.zzd = actionCodeInfo;
    }

    @Override // com.google.firebase.auth.ActionCodeResult
    public final String getData(int i) {
        if (this.zza == 4) {
            return null;
        }
        if (i != 0) {
            if (i != 1) {
                return null;
            }
            return this.zzc;
        }
        return this.zzb;
    }

    @Override // com.google.firebase.auth.ActionCodeResult
    public final ActionCodeInfo getInfo() {
        return this.zzd;
    }

    @Override // com.google.firebase.auth.ActionCodeResult
    public final int getOperation() {
        return this.zza;
    }
}
