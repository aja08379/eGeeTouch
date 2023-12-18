package com.google.android.gms.internal.vision;
/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.3 */
/* loaded from: classes2.dex */
final class zzdq {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zza(Object obj, Object obj2) {
        if (obj == null) {
            String valueOf = String.valueOf(obj2);
            throw new NullPointerException(new StringBuilder(String.valueOf(valueOf).length() + 24).append("null key in entry: null=").append(valueOf).toString());
        } else if (obj2 != null) {
        } else {
            String valueOf2 = String.valueOf(obj);
            throw new NullPointerException(new StringBuilder(String.valueOf(valueOf2).length() + 26).append("null value in entry: ").append(valueOf2).append("=null").toString());
        }
    }
}
