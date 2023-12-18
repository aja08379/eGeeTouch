package com.google.firebase.storage;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.TaskCompletionSource;
/* compiled from: lambda */
/* renamed from: com.google.firebase.storage.-$$Lambda$StorageTask$g715g6W7VdV2JIn7lpH1jtTC4D0  reason: invalid class name */
/* loaded from: classes2.dex */
public final /* synthetic */ class $$Lambda$StorageTask$g715g6W7VdV2JIn7lpH1jtTC4D0 implements OnFailureListener {
    public final /* synthetic */ TaskCompletionSource f$0;

    public /* synthetic */ $$Lambda$StorageTask$g715g6W7VdV2JIn7lpH1jtTC4D0(TaskCompletionSource taskCompletionSource) {
        this.f$0 = taskCompletionSource;
    }

    @Override // com.google.android.gms.tasks.OnFailureListener
    public final void onFailure(Exception exc) {
        this.f$0.setException(exc);
    }
}
