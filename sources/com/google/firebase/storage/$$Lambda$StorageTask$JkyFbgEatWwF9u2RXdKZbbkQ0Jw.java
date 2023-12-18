package com.google.firebase.storage;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.TaskCompletionSource;
/* compiled from: lambda */
/* renamed from: com.google.firebase.storage.-$$Lambda$StorageTask$JkyFbgEatWwF9u2RXdKZbbkQ0Jw  reason: invalid class name */
/* loaded from: classes2.dex */
public final /* synthetic */ class $$Lambda$StorageTask$JkyFbgEatWwF9u2RXdKZbbkQ0Jw implements OnSuccessListener {
    public final /* synthetic */ TaskCompletionSource f$0;

    public /* synthetic */ $$Lambda$StorageTask$JkyFbgEatWwF9u2RXdKZbbkQ0Jw(TaskCompletionSource taskCompletionSource) {
        this.f$0 = taskCompletionSource;
    }

    @Override // com.google.android.gms.tasks.OnSuccessListener
    public final void onSuccess(Object obj) {
        this.f$0.setResult(obj);
    }
}
