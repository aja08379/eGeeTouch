package com.google.android.gms.maps;

import android.content.Context;
/* compiled from: com.google.android.gms:play-services-maps@@18.1.0 */
/* loaded from: classes2.dex */
public final class MapsInitializer {
    private static final String zza = "MapsInitializer";
    private static boolean zzb = false;
    private static Renderer zzc = Renderer.LEGACY;

    /* compiled from: com.google.android.gms:play-services-maps@@18.1.0 */
    /* loaded from: classes2.dex */
    public enum Renderer {
        LEGACY,
        LATEST
    }

    private MapsInitializer() {
    }

    public static synchronized int initialize(Context context) {
        int initialize;
        synchronized (MapsInitializer.class) {
            initialize = initialize(context, null, null);
        }
        return initialize;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(14:14|15|16|17|(10:19|(1:(1:22))|23|24|(1:26)|27|28|(1:30)|31|32)|36|23|24|(0)|27|28|(0)|31|32) */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x005f, code lost:
        r5 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0060, code lost:
        android.util.Log.e(com.google.android.gms.maps.MapsInitializer.zza, "Failed to retrieve renderer type or log initialization.", r5);
     */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0053 A[Catch: RemoteException -> 0x005f, all -> 0x0091, TryCatch #1 {RemoteException -> 0x005f, blocks: (B:21:0x004d, B:23:0x0053, B:24:0x0057), top: B:44:0x004d, outer: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x007e A[Catch: all -> 0x0091, TRY_LEAVE, TryCatch #0 {, blocks: (B:4:0x0003, B:7:0x0022, B:10:0x0029, B:11:0x002d, B:13:0x003c, B:15:0x0041, B:21:0x004d, B:23:0x0053, B:24:0x0057, B:28:0x0067, B:30:0x007e, B:27:0x0060, B:34:0x0086, B:35:0x008b, B:37:0x008d), top: B:43:0x0003, inners: #1, #2, #3 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static synchronized int initialize(android.content.Context r5, com.google.android.gms.maps.MapsInitializer.Renderer r6, com.google.android.gms.maps.OnMapsSdkInitializedCallback r7) {
        synchronized (com.google.android.gms.maps.MapsInitializer.class) {
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r5, "Context is null");
            android.util.Log.d(com.google.android.gms.maps.MapsInitializer.zza, "preferredRenderer: ".concat(java.lang.String.valueOf(java.lang.String.valueOf(r6))));
            if (com.google.android.gms.maps.MapsInitializer.zzb) {
                if (r7 != null) {
                    r7.onMapsSdkInitialized(com.google.android.gms.maps.MapsInitializer.zzc);
                }
                return 0;
            }
            try {
                com.google.android.gms.maps.internal.zzf r1 = com.google.android.gms.maps.internal.zzcb.zza(r5, r6);
                try {
                    com.google.android.gms.maps.CameraUpdateFactory.zza(r1.zze());
                    com.google.android.gms.maps.model.BitmapDescriptorFactory.zza(r1.zzj());
                    int r3 = 1;
                    com.google.android.gms.maps.MapsInitializer.zzb = true;
                    if (r6 != null) {
                        int r6 = r6.ordinal();
                        if (r6 != 0) {
                            if (r6 == 1) {
                                r3 = 2;
                            }
                        }
                        if (r1.zzd() == 2) {
                            com.google.android.gms.maps.MapsInitializer.zzc = com.google.android.gms.maps.MapsInitializer.Renderer.LATEST;
                        }
                        r1.zzl(com.google.android.gms.dynamic.ObjectWrapper.wrap(r5), r3);
                        android.util.Log.d(com.google.android.gms.maps.MapsInitializer.zza, "loadedRenderer: ".concat(java.lang.String.valueOf(java.lang.String.valueOf(com.google.android.gms.maps.MapsInitializer.zzc))));
                        if (r7 != null) {
                            r7.onMapsSdkInitialized(com.google.android.gms.maps.MapsInitializer.zzc);
                        }
                        return 0;
                    }
                    r3 = 0;
                    if (r1.zzd() == 2) {
                    }
                    r1.zzl(com.google.android.gms.dynamic.ObjectWrapper.wrap(r5), r3);
                    android.util.Log.d(com.google.android.gms.maps.MapsInitializer.zza, "loadedRenderer: ".concat(java.lang.String.valueOf(java.lang.String.valueOf(com.google.android.gms.maps.MapsInitializer.zzc))));
                    if (r7 != null) {
                    }
                    return 0;
                } catch (android.os.RemoteException r5) {
                    throw new com.google.android.gms.maps.model.RuntimeRemoteException(r5);
                }
            } catch (com.google.android.gms.common.GooglePlayServicesNotAvailableException r5) {
                return r5.errorCode;
            }
        }
    }
}
