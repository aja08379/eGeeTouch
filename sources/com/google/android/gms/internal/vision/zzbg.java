package com.google.android.gms.internal.vision;

import android.content.Context;
import android.content.pm.ProviderInfo;
import android.net.Uri;
import android.util.Log;
import com.egeetouch.egeetouch_manager.TaskManagement;
/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.3 */
/* loaded from: classes2.dex */
public final class zzbg {
    private static volatile zzcy<Boolean> zza = zzcy.zzc();
    private static final Object zzb = new Object();

    private static boolean zza(Context context) {
        return (context.getPackageManager().getApplicationInfo("com.google.android.gms", 0).flags & TaskManagement.restore_tag2) != 0;
    }

    public static boolean zza(Context context, Uri uri) {
        ProviderInfo resolveContentProvider;
        boolean z;
        String authority = uri.getAuthority();
        boolean z2 = false;
        if (!"com.google.android.gms.phenotype".equals(authority)) {
            Log.e("PhenotypeClientHelper", new StringBuilder(String.valueOf(authority).length() + 91).append(authority).append(" is an unsupported authority. Only com.google.android.gms.phenotype authority is supported.").toString());
            return false;
        } else if (zza.zza()) {
            return zza.zzb().booleanValue();
        } else {
            synchronized (zzb) {
                if (zza.zza()) {
                    return zza.zzb().booleanValue();
                }
                if (!"com.google.android.gms".equals(context.getPackageName()) && ((resolveContentProvider = context.getPackageManager().resolveContentProvider("com.google.android.gms.phenotype", 0)) == null || !"com.google.android.gms".equals(resolveContentProvider.packageName))) {
                    z = false;
                    if (z && zza(context)) {
                        z2 = true;
                    }
                    zza = zzcy.zza(Boolean.valueOf(z2));
                    return zza.zzb().booleanValue();
                }
                z = true;
                if (z) {
                    z2 = true;
                }
                zza = zzcy.zza(Boolean.valueOf(z2));
                return zza.zzb().booleanValue();
            }
        }
    }
}
