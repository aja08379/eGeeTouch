package com.google.android.gms.internal.p001firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzbl  reason: invalid package */
/* loaded from: classes.dex */
public final class zzbl {
    private static final CopyOnWriteArrayList zza = new CopyOnWriteArrayList();

    public static zzbk zza(String str) throws GeneralSecurityException {
        Iterator it = zza.iterator();
        while (it.hasNext()) {
            zzbk zzbkVar = (zzbk) it.next();
            if (zzbkVar.zzb(str)) {
                return zzbkVar;
            }
        }
        throw new GeneralSecurityException("No KMS client does support: ".concat(String.valueOf(str)));
    }
}
