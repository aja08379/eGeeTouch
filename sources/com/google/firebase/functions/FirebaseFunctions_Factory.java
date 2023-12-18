package com.google.firebase.functions;

import android.content.Context;
import java.util.concurrent.Executor;
import javax.inject.Provider;
/* loaded from: classes2.dex */
public final class FirebaseFunctions_Factory {
    private final Provider<Context> contextProvider;
    private final Provider<ContextProvider> contextProvider2;
    private final Provider<Executor> executorProvider;
    private final Provider<String> projectIdProvider;
    private final Provider<Executor> uiExecutorProvider;

    public FirebaseFunctions_Factory(Provider<Context> provider, Provider<String> provider2, Provider<ContextProvider> provider3, Provider<Executor> provider4, Provider<Executor> provider5) {
        this.contextProvider = provider;
        this.projectIdProvider = provider2;
        this.contextProvider2 = provider3;
        this.executorProvider = provider4;
        this.uiExecutorProvider = provider5;
    }

    public FirebaseFunctions get(String str) {
        return newInstance(this.contextProvider.get(), this.projectIdProvider.get(), str, this.contextProvider2.get(), this.executorProvider.get(), this.uiExecutorProvider.get());
    }

    public static FirebaseFunctions_Factory create(Provider<Context> provider, Provider<String> provider2, Provider<ContextProvider> provider3, Provider<Executor> provider4, Provider<Executor> provider5) {
        return new FirebaseFunctions_Factory(provider, provider2, provider3, provider4, provider5);
    }

    public static FirebaseFunctions newInstance(Context context, String str, String str2, Object obj, Executor executor, Executor executor2) {
        return new FirebaseFunctions(context, str, str2, (ContextProvider) obj, executor, executor2);
    }
}
