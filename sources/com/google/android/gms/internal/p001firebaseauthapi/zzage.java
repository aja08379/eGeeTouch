package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzage  reason: invalid package */
/* loaded from: classes.dex */
public enum zzage {
    INT(0),
    LONG(0L),
    FLOAT(Float.valueOf(0.0f)),
    DOUBLE(Double.valueOf((double) FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE)),
    BOOLEAN(false),
    STRING(""),
    BYTE_STRING(zzacc.zzb),
    ENUM(null),
    MESSAGE(null);
    
    private final Object zzk;

    zzage(Object obj) {
        this.zzk = obj;
    }
}
