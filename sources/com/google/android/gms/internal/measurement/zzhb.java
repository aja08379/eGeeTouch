package com.google.android.gms.internal.measurement;

import android.os.Build;
import android.os.UserManager;
/* compiled from: com.google.android.gms:play-services-measurement-impl@@21.2.0 */
/* loaded from: classes.dex */
public final class zzhb {
    private static UserManager zza;
    private static volatile boolean zzb = !zzb();

    private zzhb() {
    }

    public static boolean zzb() {
        return Build.VERSION.SDK_INT >= 24;
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x003d, code lost:
        if (r4.isUserRunning(android.os.Process.myUserHandle()) == false) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x003f, code lost:
        r8 = true;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean zza(android.content.Context r8) {
        boolean r8;
        if (zzb() && !com.google.android.gms.internal.measurement.zzhb.zzb) {
            synchronized (com.google.android.gms.internal.measurement.zzhb.class) {
                if (!com.google.android.gms.internal.measurement.zzhb.zzb) {
                    int r3 = 1;
                    while (true) {
                        if (r3 > 2) {
                            break;
                        }
                        if (com.google.android.gms.internal.measurement.zzhb.zza == null) {
                            com.google.android.gms.internal.measurement.zzhb.zza = (android.os.UserManager) r8.getSystemService(android.os.UserManager.class);
                        }
                        android.os.UserManager r4 = com.google.android.gms.internal.measurement.zzhb.zza;
                        if (r4 == null) {
                            r8 = true;
                            break;
                        }
                        try {
                            if (r4.isUserUnlocked()) {
                                break;
                            }
                        } catch (java.lang.NullPointerException r4) {
                            android.util.Log.w("DirectBootUtils", "Failed to check if user is unlocked.", r4);
                            com.google.android.gms.internal.measurement.zzhb.zza = null;
                            r3++;
                        }
                    }
                    r8 = false;
                    if (r8) {
                        com.google.android.gms.internal.measurement.zzhb.zza = null;
                    }
                    if (r8) {
                        com.google.android.gms.internal.measurement.zzhb.zzb = true;
                    }
                    if (!r8) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
