package com.google.android.gms.internal.measurement;

import java.util.Comparator;
import kotlin.UByte;
/* compiled from: com.google.android.gms:play-services-measurement-base@@21.2.0 */
/* loaded from: classes.dex */
final class zziw implements Comparator {
    @Override // java.util.Comparator
    public final /* synthetic */ int compare(Object obj, Object obj2) {
        zzje zzjeVar = (zzje) obj;
        zzje zzjeVar2 = (zzje) obj2;
        zziv zzivVar = new zziv(zzjeVar);
        zziv zzivVar2 = new zziv(zzjeVar2);
        while (zzivVar.hasNext() && zzivVar2.hasNext()) {
            int compareTo = Integer.valueOf(zzivVar.zza() & UByte.MAX_VALUE).compareTo(Integer.valueOf(zzivVar2.zza() & UByte.MAX_VALUE));
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return Integer.valueOf(zzjeVar.zzd()).compareTo(Integer.valueOf(zzjeVar2.zzd()));
    }
}
