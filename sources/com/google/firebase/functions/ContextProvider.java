package com.google.firebase.functions;

import com.google.android.gms.tasks.Task;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public interface ContextProvider {
    Task<HttpsCallableContext> getContext();
}
