package com.google.android.gms.internal.measurement;

import android.content.Context;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.Nullable;
/* compiled from: com.google.android.gms:play-services-measurement-impl@@21.2.0 */
/* loaded from: classes.dex */
public abstract class zzib {
    public static final /* synthetic */ int zzc = 0;
    @Nullable
    private static volatile zzhz zze = null;
    private static volatile boolean zzf = false;
    final zzhy zza;
    final String zzb;
    private final Object zzj;
    private volatile int zzk = -1;
    private volatile Object zzl;
    private final boolean zzm;
    private static final Object zzd = new Object();
    private static final AtomicReference zzg = new AtomicReference();
    private static final zzid zzh = new zzid(new Object() { // from class: com.google.android.gms.internal.measurement.zzht
    }, null);
    private static final AtomicInteger zzi = new AtomicInteger();

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzib(zzhy zzhyVar, String str, Object obj, boolean z, zzia zziaVar) {
        if (zzhyVar.zzb == null) {
            throw new IllegalArgumentException("Must pass a valid SharedPreferences file name or ContentProvider URI");
        }
        this.zza = zzhyVar;
        this.zzb = str;
        this.zzj = obj;
        this.zzm = true;
    }

    public static void zzd() {
        zzi.incrementAndGet();
    }

    public static void zze(final Context context) {
        if (zze == null) {
            Object obj = zzd;
            synchronized (obj) {
                if (zze == null) {
                    synchronized (obj) {
                        zzhz zzhzVar = zze;
                        Context applicationContext = context.getApplicationContext();
                        if (applicationContext != null) {
                            context = applicationContext;
                        }
                        if (zzhzVar == null || zzhzVar.zza() != context) {
                            zzhf.zze();
                            zzic.zzc();
                            zzhn.zze();
                            zze = new zzhc(context, zzim.zza(new zzii() { // from class: com.google.android.gms.internal.measurement.zzhs
                                @Override // com.google.android.gms.internal.measurement.zzii
                                public final Object zza() {
                                    Context context2 = context;
                                    int i = zzib.zzc;
                                    return zzho.zza(context2);
                                }
                            }));
                            zzi.incrementAndGet();
                        }
                    }
                }
            }
        }
    }

    abstract Object zza(Object obj);

    /* JADX WARN: Removed duplicated region for block: B:37:0x009d A[Catch: all -> 0x00d9, TryCatch #0 {, blocks: (B:8:0x0016, B:10:0x001a, B:12:0x0020, B:14:0x0029, B:16:0x0037, B:20:0x0060, B:22:0x006a, B:38:0x009f, B:40:0x00af, B:42:0x00c5, B:43:0x00c8, B:44:0x00cc, B:26:0x0073, B:28:0x0079, B:32:0x008f, B:34:0x0095, B:37:0x009d, B:31:0x008b, B:18:0x0050, B:45:0x00d1, B:46:0x00d6, B:47:0x00d7), top: B:54:0x0016 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object zzb() {
        com.google.android.gms.internal.measurement.zzhk r2;
        java.lang.String r4;
        java.lang.Object r2;
        if (!r6.zzm) {
            java.util.Objects.requireNonNull(r6.zzb, "flagName must not be null");
        }
        int r0 = com.google.android.gms.internal.measurement.zzib.zzi.get();
        if (r6.zzk < r0) {
            synchronized (r6) {
                if (r6.zzk < r0) {
                    com.google.android.gms.internal.measurement.zzhz r1 = com.google.android.gms.internal.measurement.zzib.zze;
                    if (r1 != null) {
                        com.google.android.gms.internal.measurement.zzhy r2 = r6.zza;
                        boolean r3 = r2.zzf;
                        if (r2.zzb != null) {
                            if (com.google.android.gms.internal.measurement.zzhp.zza(r1.zza(), r6.zza.zzb)) {
                                boolean r2 = r6.zza.zzh;
                                r2 = com.google.android.gms.internal.measurement.zzhf.zza(r1.zza().getContentResolver(), r6.zza.zzb, new java.lang.Runnable() { // from class: com.google.android.gms.internal.measurement.zzhr
                                    static {
                                    }

                                    {
                                    }

                                    @Override // java.lang.Runnable
                                    public final void run() {
                                        com.google.android.gms.internal.measurement.zzib.zzd();
                                    }
                                });
                            } else {
                                r2 = null;
                            }
                        } else {
                            android.content.Context r2 = r1.zza();
                            java.lang.String r4 = r6.zza.zza;
                            r2 = com.google.android.gms.internal.measurement.zzic.zza(r2, null, new java.lang.Runnable() { // from class: com.google.android.gms.internal.measurement.zzhr
                                static {
                                }

                                {
                                }

                                @Override // java.lang.Runnable
                                public final void run() {
                                    com.google.android.gms.internal.measurement.zzib.zzd();
                                }
                            });
                        }
                        java.lang.Object r2 = (r2 == null || (r2 = r2.zzb(zzc())) == null) ? null : zza(r2);
                        if (r2 == null) {
                            com.google.android.gms.internal.measurement.zzhy r2 = r6.zza;
                            if (!r2.zze) {
                                com.google.android.gms.internal.measurement.zzif r2 = r2.zzi;
                                com.google.android.gms.internal.measurement.zzhn r2 = com.google.android.gms.internal.measurement.zzhn.zza(r1.zza());
                                com.google.android.gms.internal.measurement.zzhy r4 = r6.zza;
                                if (r4.zze) {
                                    r4 = null;
                                } else {
                                    java.lang.String r4 = r4.zzc;
                                    r4 = r6.zzb;
                                }
                                java.lang.String r2 = r2.zzb(r4);
                                if (r2 != null) {
                                    r2 = zza(r2);
                                    if (r2 == null) {
                                        r2 = r6.zzj;
                                    }
                                }
                            }
                            r2 = null;
                            if (r2 == null) {
                            }
                        }
                        com.google.android.gms.internal.measurement.zzig r1 = (com.google.android.gms.internal.measurement.zzig) r1.zzb().zza();
                        if (r1.zzb()) {
                            com.google.android.gms.internal.measurement.zzhy r2 = r6.zza;
                            android.net.Uri r4 = r2.zzb;
                            java.lang.String r5 = r2.zza;
                            java.lang.String r1 = ((com.google.android.gms.internal.measurement.zzhh) r1.zza()).zza(r4, null, r2.zzd, r6.zzb);
                            r2 = r1 == null ? r6.zzj : zza(r1);
                        }
                        r6.zzl = r2;
                        r6.zzk = r0;
                    } else {
                        throw new java.lang.IllegalStateException("Must call PhenotypeFlag.init() first");
                    }
                }
            }
        }
        return r6.zzl;
    }

    public final String zzc() {
        String str = this.zza.zzd;
        return this.zzb;
    }
}
