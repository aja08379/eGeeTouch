package com.google.android.gms.maps.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.RuntimeRemoteException;
/* compiled from: com.google.android.gms:play-services-maps@@18.1.0 */
/* loaded from: classes2.dex */
public final class zzcb {
    private static final String zza = "zzcb";
    private static Context zzb;
    private static zzf zzc;

    public static zzf zza(Context context, MapsInitializer.Renderer renderer) throws GooglePlayServicesNotAvailableException {
        zzf zzeVar;
        Preconditions.checkNotNull(context);
        String str = zza;
        Log.d(str, "preferredRenderer: ".concat(String.valueOf(String.valueOf(renderer))));
        zzf zzfVar = zzc;
        if (zzfVar == null) {
            int isGooglePlayServicesAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(context, 13400000);
            if (isGooglePlayServicesAvailable == 0) {
                Log.i(str, "Making Creator dynamically");
                try {
                    IBinder iBinder = (IBinder) zzd(((ClassLoader) Preconditions.checkNotNull(zzc(context, renderer).getClassLoader())).loadClass("com.google.android.gms.maps.internal.CreatorImpl"));
                    if (iBinder == null) {
                        zzeVar = null;
                    } else {
                        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.internal.ICreator");
                        zzeVar = queryLocalInterface instanceof zzf ? (zzf) queryLocalInterface : new zze(iBinder);
                    }
                    zzc = zzeVar;
                    try {
                        Context zzc2 = zzc(context, renderer);
                        zzc2.getClass();
                        zzeVar.zzk(ObjectWrapper.wrap(zzc2.getResources()), GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_VERSION_CODE);
                        return zzc;
                    } catch (RemoteException e) {
                        throw new RuntimeRemoteException(e);
                    }
                } catch (ClassNotFoundException unused) {
                    throw new IllegalStateException("Unable to find dynamic class com.google.android.gms.maps.internal.CreatorImpl");
                }
            }
            throw new GooglePlayServicesNotAvailableException(isGooglePlayServicesAvailable);
        }
        return zzfVar;
    }

    private static Context zzb(Exception exc, Context context) {
        Log.e(zza, "Failed to load maps module, use pre-Chimera", exc);
        return GooglePlayServicesUtil.getRemoteContext(context);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(7:3|(6:5|(2:7|(1:9))(1:25)|10|11|12|13)|26|10|11|12|13) */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0027, code lost:
        r1 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x002c, code lost:
        if (r3.equals("com.google.android.gms.maps_dynamite") == false) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x002e, code lost:
        android.util.Log.d(com.google.android.gms.maps.internal.zzcb.zza, "Attempting to load maps_dynamite again.");
        r2 = com.google.android.gms.dynamite.DynamiteModule.load(r2, com.google.android.gms.dynamite.DynamiteModule.PREFER_REMOTE, "com.google.android.gms.maps_dynamite").getModuleContext();
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0040, code lost:
        r3 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0041, code lost:
        r2 = zzb(r3, r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0046, code lost:
        r2 = zzb(r1, r2);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static android.content.Context zzc(android.content.Context r2, com.google.android.gms.maps.MapsInitializer.Renderer r3) {
        java.lang.String r3;
        android.content.Context r0 = com.google.android.gms.maps.internal.zzcb.zzb;
        if (r0 == null) {
            r2.getApplicationContext();
            if (r3 != null) {
                int r3 = r3.ordinal();
                if (r3 == 0) {
                    r3 = "com.google.android.gms.maps_legacy_dynamite";
                } else if (r3 == 1) {
                    r3 = "com.google.android.gms.maps_core_dynamite";
                }
                android.content.Context r2 = com.google.android.gms.dynamite.DynamiteModule.load(r2, com.google.android.gms.dynamite.DynamiteModule.PREFER_REMOTE, r3).getModuleContext();
                com.google.android.gms.maps.internal.zzcb.zzb = r2;
                return r2;
            }
            r3 = "com.google.android.gms.maps_dynamite";
            android.content.Context r2 = com.google.android.gms.dynamite.DynamiteModule.load(r2, com.google.android.gms.dynamite.DynamiteModule.PREFER_REMOTE, r3).getModuleContext();
            com.google.android.gms.maps.internal.zzcb.zzb = r2;
            return r2;
        }
        return r0;
    }

    private static Object zzd(Class cls) {
        try {
            return cls.newInstance();
        } catch (IllegalAccessException unused) {
            throw new IllegalStateException("Unable to call the default constructor of ".concat(String.valueOf(cls.getName())));
        } catch (InstantiationException unused2) {
            throw new IllegalStateException("Unable to instantiate the dynamic class ".concat(String.valueOf(cls.getName())));
        }
    }
}
