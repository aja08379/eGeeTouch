package com.google.firebase.concurrent;

import java.util.concurrent.Executor;
/* loaded from: classes2.dex */
public class FirebaseExecutors {
    private FirebaseExecutors() {
    }

    public static Executor newSequentialExecutor(Executor executor) {
        return new SequentialExecutor(executor);
    }

    public static Executor directExecutor() {
        return DirectExecutor.INSTANCE;
    }

    /* loaded from: classes2.dex */
    private enum DirectExecutor implements Executor {
        INSTANCE;

        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            runnable.run();
        }
    }
}
