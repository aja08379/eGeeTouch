package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzado  reason: invalid package */
/* loaded from: classes.dex */
public enum zzado {
    VOID(Void.class, Void.class, null),
    INT(Integer.TYPE, Integer.class, 0),
    LONG(Long.TYPE, Long.class, 0L),
    FLOAT(Float.TYPE, Float.class, Float.valueOf(0.0f)),
    DOUBLE(Double.TYPE, Double.class, Double.valueOf((double) FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE)),
    BOOLEAN(Boolean.TYPE, Boolean.class, false),
    STRING(String.class, String.class, ""),
    BYTE_STRING(zzacc.class, zzacc.class, zzacc.zzb),
    ENUM(Integer.TYPE, Integer.class, null),
    MESSAGE(Object.class, Object.class, null);
    
    private final Class zzl;
    private final Class zzm;
    private final Object zzn;

    zzado(Class cls, Class cls2, Object obj) {
        this.zzl = cls;
        this.zzm = cls2;
        this.zzn = obj;
    }

    public final Class zza() {
        return this.zzm;
    }
}
