package com.google.firebase.internal;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.GetTokenResult;
/* compiled from: com.google.firebase:firebase-auth-interop@@20.0.0 */
/* loaded from: classes2.dex */
public interface InternalTokenProvider {
    Task<GetTokenResult> getAccessToken(boolean z);

    String getUid();
}
