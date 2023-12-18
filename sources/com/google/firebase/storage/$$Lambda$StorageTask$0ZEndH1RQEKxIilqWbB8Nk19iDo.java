package com.google.firebase.storage;

import com.google.android.gms.tasks.CancellationTokenSource;
import com.google.android.gms.tasks.OnCanceledListener;
/* compiled from: lambda */
/* renamed from: com.google.firebase.storage.-$$Lambda$StorageTask$0ZEndH1RQEKxIilqWbB8Nk19iDo  reason: invalid class name */
/* loaded from: classes2.dex */
public final /* synthetic */ class $$Lambda$StorageTask$0ZEndH1RQEKxIilqWbB8Nk19iDo implements OnCanceledListener {
    public final /* synthetic */ CancellationTokenSource f$0;

    public /* synthetic */ $$Lambda$StorageTask$0ZEndH1RQEKxIilqWbB8Nk19iDo(CancellationTokenSource cancellationTokenSource) {
        this.f$0 = cancellationTokenSource;
    }

    @Override // com.google.android.gms.tasks.OnCanceledListener
    public final void onCanceled() {
        StorageTask.lambda$0ZEndH1RQEKxIilqWbB8Nk19iDo(this.f$0);
    }
}
