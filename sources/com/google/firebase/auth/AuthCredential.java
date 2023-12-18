package com.google.firebase.auth;

import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* loaded from: classes2.dex */
public abstract class AuthCredential extends AbstractSafeParcelable {
    public abstract String getProvider();

    public abstract String getSignInMethod();

    public abstract AuthCredential zza();
}
