package com.google.android.gms.measurement.internal;

import android.app.Activity;
import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/* compiled from: com.google.android.gms:play-services-measurement-impl@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzim extends zzf {
    protected zzie zza;
    private volatile zzie zzb;
    private volatile zzie zzc;
    private final Map zzd;
    private Activity zze;
    private volatile boolean zzf;
    private volatile zzie zzg;
    private zzie zzh;
    private boolean zzi;
    private final Object zzj;

    public zzim(zzfr zzfrVar) {
        super(zzfrVar);
        this.zzj = new Object();
        this.zzd = new ConcurrentHashMap();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzA(zzie zzieVar, zzie zzieVar2, long j, boolean z, Bundle bundle) {
        long j2;
        long j3;
        zzg();
        boolean z2 = false;
        boolean z3 = (zzieVar2 != null && zzieVar2.zzc == zzieVar.zzc && zzif.zza(zzieVar2.zzb, zzieVar.zzb) && zzif.zza(zzieVar2.zza, zzieVar.zza)) ? false : true;
        if (z && this.zza != null) {
            z2 = true;
        }
        if (z3) {
            Bundle bundle2 = bundle != null ? new Bundle(bundle) : new Bundle();
            zzlb.zzK(zzieVar, bundle2, true);
            if (zzieVar2 != null) {
                String str = zzieVar2.zza;
                if (str != null) {
                    bundle2.putString("_pn", str);
                }
                String str2 = zzieVar2.zzb;
                if (str2 != null) {
                    bundle2.putString("_pc", str2);
                }
                bundle2.putLong("_pi", zzieVar2.zzc);
            }
            if (z2) {
                zzka zzkaVar = this.zzt.zzu().zzb;
                long j4 = j - zzkaVar.zzb;
                zzkaVar.zzb = j;
                if (j4 > 0) {
                    this.zzt.zzv().zzI(bundle2, j4);
                }
            }
            if (!this.zzt.zzf().zzu()) {
                bundle2.putLong("_mst", 1L);
            }
            String str3 = true != zzieVar.zze ? "auto" : "app";
            long currentTimeMillis = this.zzt.zzav().currentTimeMillis();
            if (zzieVar.zze) {
                j2 = currentTimeMillis;
                long j5 = zzieVar.zzf;
                if (j5 != 0) {
                    j3 = j5;
                    this.zzt.zzq().zzH(str3, "_vs", j3, bundle2);
                }
            } else {
                j2 = currentTimeMillis;
            }
            j3 = j2;
            this.zzt.zzq().zzH(str3, "_vs", j3, bundle2);
        }
        if (z2) {
            zzB(this.zza, true, j);
        }
        this.zza = zzieVar;
        if (zzieVar.zze) {
            this.zzh = zzieVar;
        }
        this.zzt.zzt().zzG(zzieVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzB(zzie zzieVar, boolean z, long j) {
        this.zzt.zzd().zzf(this.zzt.zzav().elapsedRealtime());
        if (!this.zzt.zzu().zzb.zzd(zzieVar != null && zzieVar.zzd, z, j) || zzieVar == null) {
            return;
        }
        zzieVar.zzd = false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ void zzp(zzim zzimVar, Bundle bundle, zzie zzieVar, zzie zzieVar2, long j) {
        bundle.remove(FirebaseAnalytics.Param.SCREEN_NAME);
        bundle.remove(FirebaseAnalytics.Param.SCREEN_CLASS);
        zzimVar.zzA(zzieVar, zzieVar2, j, true, zzimVar.zzt.zzv().zzy(null, FirebaseAnalytics.Event.SCREEN_VIEW, bundle, null, false));
    }

    private final zzie zzy(Activity activity) {
        Preconditions.checkNotNull(activity);
        zzie zzieVar = (zzie) this.zzd.get(activity);
        if (zzieVar == null) {
            zzie zzieVar2 = new zzie(null, zzl(activity.getClass(), "Activity"), this.zzt.zzv().zzq());
            this.zzd.put(activity, zzieVar2);
            zzieVar = zzieVar2;
        }
        return this.zzg != null ? this.zzg : zzieVar;
    }

    private final void zzz(Activity activity, zzie zzieVar, boolean z) {
        zzie zzieVar2;
        zzie zzieVar3 = this.zzb == null ? this.zzc : this.zzb;
        if (zzieVar.zzb == null) {
            zzieVar2 = new zzie(zzieVar.zza, activity != null ? zzl(activity.getClass(), "Activity") : null, zzieVar.zzc, zzieVar.zze, zzieVar.zzf);
        } else {
            zzieVar2 = zzieVar;
        }
        this.zzc = this.zzb;
        this.zzb = zzieVar2;
        this.zzt.zzaz().zzp(new zzih(this, zzieVar2, zzieVar3, this.zzt.zzav().elapsedRealtime(), z));
    }

    @Override // com.google.android.gms.measurement.internal.zzf
    protected final boolean zzf() {
        return false;
    }

    public final zzie zzi() {
        return this.zzb;
    }

    public final zzie zzj(boolean z) {
        zza();
        zzg();
        if (z) {
            zzie zzieVar = this.zza;
            return zzieVar != null ? zzieVar : this.zzh;
        }
        return this.zza;
    }

    final String zzl(Class cls, String str) {
        String canonicalName = cls.getCanonicalName();
        if (canonicalName == null) {
            return "Activity";
        }
        String[] split = canonicalName.split("\\.");
        int length = split.length;
        String str2 = length > 0 ? split[length - 1] : "";
        int length2 = str2.length();
        this.zzt.zzf();
        if (length2 > 100) {
            this.zzt.zzf();
            return str2.substring(0, 100);
        }
        return str2;
    }

    public final void zzr(Activity activity, Bundle bundle) {
        Bundle bundle2;
        if (!this.zzt.zzf().zzu() || bundle == null || (bundle2 = bundle.getBundle("com.google.app_measurement.screen_service")) == null) {
            return;
        }
        this.zzd.put(activity, new zzie(bundle2.getString("name"), bundle2.getString("referrer_name"), bundle2.getLong("id")));
    }

    public final void zzs(Activity activity) {
        synchronized (this.zzj) {
            if (activity == this.zze) {
                this.zze = null;
            }
        }
        if (this.zzt.zzf().zzu()) {
            this.zzd.remove(activity);
        }
    }

    public final void zzt(Activity activity) {
        synchronized (this.zzj) {
            this.zzi = false;
            this.zzf = true;
        }
        long elapsedRealtime = this.zzt.zzav().elapsedRealtime();
        if (!this.zzt.zzf().zzu()) {
            this.zzb = null;
            this.zzt.zzaz().zzp(new zzij(this, elapsedRealtime));
            return;
        }
        zzie zzy = zzy(activity);
        this.zzc = this.zzb;
        this.zzb = null;
        this.zzt.zzaz().zzp(new zzik(this, zzy, elapsedRealtime));
    }

    public final void zzu(Activity activity) {
        synchronized (this.zzj) {
            this.zzi = true;
            if (activity != this.zze) {
                synchronized (this.zzj) {
                    this.zze = activity;
                    this.zzf = false;
                }
                if (this.zzt.zzf().zzu()) {
                    this.zzg = null;
                    this.zzt.zzaz().zzp(new zzil(this));
                }
            }
        }
        if (!this.zzt.zzf().zzu()) {
            this.zzb = this.zzg;
            this.zzt.zzaz().zzp(new zzii(this));
            return;
        }
        zzz(activity, zzy(activity), false);
        zzd zzd = this.zzt.zzd();
        zzd.zzt.zzaz().zzp(new zzc(zzd, zzd.zzt.zzav().elapsedRealtime()));
    }

    public final void zzv(Activity activity, Bundle bundle) {
        zzie zzieVar;
        if (!this.zzt.zzf().zzu() || bundle == null || (zzieVar = (zzie) this.zzd.get(activity)) == null) {
            return;
        }
        Bundle bundle2 = new Bundle();
        bundle2.putLong("id", zzieVar.zzc);
        bundle2.putString("name", zzieVar.zza);
        bundle2.putString("referrer_name", zzieVar.zzb);
        bundle.putBundle("com.google.app_measurement.screen_service", bundle2);
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x0088, code lost:
        if (r5.length() <= 100) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00b4, code lost:
        if (r6.length() <= 100) goto L36;
     */
    @java.lang.Deprecated
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void zzw(android.app.Activity r4, java.lang.String r5, java.lang.String r6) {
        if (!r3.zzt.zzf().zzu()) {
            r3.zzt.zzay().zzl().zza("setCurrentScreen cannot be called while screen reporting is disabled.");
            return;
        }
        com.google.android.gms.measurement.internal.zzie r0 = r3.zzb;
        if (r0 == null) {
            r3.zzt.zzay().zzl().zza("setCurrentScreen cannot be called while no activity active");
        } else if (r3.zzd.get(r4) == null) {
            r3.zzt.zzay().zzl().zza("setCurrentScreen must be called with an activity in the activity lifecycle");
        } else {
            if (r6 == null) {
                r6 = zzl(r4.getClass(), "Activity");
            }
            boolean r1 = com.google.android.gms.measurement.internal.zzif.zza(r0.zzb, r6);
            boolean r0 = com.google.android.gms.measurement.internal.zzif.zza(r0.zza, r5);
            if (!r1 || !r0) {
                if (r5 != null) {
                    if (r5.length() > 0) {
                        r3.zzt.zzf();
                    }
                    r3.zzt.zzay().zzl().zzb("Invalid screen name length in setCurrentScreen. Length", java.lang.Integer.valueOf(r5.length()));
                    return;
                }
                if (r6 != null) {
                    if (r6.length() > 0) {
                        r3.zzt.zzf();
                    }
                    r3.zzt.zzay().zzl().zzb("Invalid class name length in setCurrentScreen. Length", java.lang.Integer.valueOf(r6.length()));
                    return;
                }
                r3.zzt.zzay().zzj().zzc("Setting current screen to name, class", r5 == null ? "null" : r5, r6);
                com.google.android.gms.measurement.internal.zzie r0 = new com.google.android.gms.measurement.internal.zzie(r5, r6, r3.zzt.zzv().zzq());
                r3.zzd.put(r4, r0);
                zzz(r4, r0, true);
                return;
            }
            r3.zzt.zzay().zzl().zza("setCurrentScreen cannot be called with the same class and name");
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0031, code lost:
        if (r2 > 100) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0063, code lost:
        if (r4 > 100) goto L25;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void zzx(android.os.Bundle r13, long r14) {
        java.lang.String r4;
        synchronized (r12.zzj) {
            if (!r12.zzi) {
                r12.zzt.zzay().zzl().zza("Cannot log screen view event when the app is in the background.");
                return;
            }
            java.lang.String r3 = r13.getString(com.google.firebase.analytics.FirebaseAnalytics.Param.SCREEN_NAME);
            if (r3 != null) {
                if (r3.length() > 0) {
                    int r2 = r3.length();
                    r12.zzt.zzf();
                }
                r12.zzt.zzay().zzl().zzb("Invalid screen name length for screen view. Length", java.lang.Integer.valueOf(r3.length()));
                return;
            }
            java.lang.String r2 = r13.getString(com.google.firebase.analytics.FirebaseAnalytics.Param.SCREEN_CLASS);
            if (r2 != null) {
                if (r2.length() > 0) {
                    int r4 = r2.length();
                    r12.zzt.zzf();
                }
                r12.zzt.zzay().zzl().zzb("Invalid screen class length for screen view. Length", java.lang.Integer.valueOf(r2.length()));
                return;
            }
            if (r2 == null) {
                android.app.Activity r1 = r12.zze;
                r4 = r1 != null ? zzl(r1.getClass(), "Activity") : "Activity";
            } else {
                r4 = r2;
            }
            com.google.android.gms.measurement.internal.zzie r1 = r12.zzb;
            if (r12.zzf && r1 != null) {
                r12.zzf = false;
                boolean r2 = com.google.android.gms.measurement.internal.zzif.zza(r1.zzb, r4);
                boolean r1 = com.google.android.gms.measurement.internal.zzif.zza(r1.zza, r3);
                if (r2 && r1) {
                    r12.zzt.zzay().zzl().zza("Ignoring call to log screen view event with duplicate parameters.");
                    return;
                }
            }
            r12.zzt.zzay().zzj().zzc("Logging screen view with name, class", r3 == null ? "null" : r3, r4 == null ? "null" : r4);
            com.google.android.gms.measurement.internal.zzie r0 = r12.zzb == null ? r12.zzc : r12.zzb;
            com.google.android.gms.measurement.internal.zzie r1 = new com.google.android.gms.measurement.internal.zzie(r3, r4, r12.zzt.zzv().zzq(), true, r14);
            r12.zzb = r1;
            r12.zzc = r0;
            r12.zzg = r1;
            r12.zzt.zzaz().zzp(new com.google.android.gms.measurement.internal.zzig(r12, r13, r1, r0, r12.zzt.zzav().elapsedRealtime()));
        }
    }
}
