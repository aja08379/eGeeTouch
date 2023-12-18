package com.google.firebase.functions;

import com.google.firebase.appcheck.interop.InternalAppCheckTokenProvider;
import com.google.firebase.auth.internal.InternalAuthProvider;
import com.google.firebase.functions.dagger.internal.Factory;
import com.google.firebase.iid.internal.FirebaseInstanceIdInternal;
import com.google.firebase.inject.Deferred;
import java.util.concurrent.Executor;
import javax.inject.Provider;
/* loaded from: classes2.dex */
public final class FirebaseContextProvider_Factory implements Factory<FirebaseContextProvider> {
    private final Provider<Deferred<InternalAppCheckTokenProvider>> appCheckDeferredProvider;
    private final Provider<Executor> executorProvider;
    private final Provider<com.google.firebase.inject.Provider<FirebaseInstanceIdInternal>> instanceIdProvider;
    private final Provider<com.google.firebase.inject.Provider<InternalAuthProvider>> tokenProvider;

    public FirebaseContextProvider_Factory(Provider<com.google.firebase.inject.Provider<InternalAuthProvider>> provider, Provider<com.google.firebase.inject.Provider<FirebaseInstanceIdInternal>> provider2, Provider<Deferred<InternalAppCheckTokenProvider>> provider3, Provider<Executor> provider4) {
        this.tokenProvider = provider;
        this.instanceIdProvider = provider2;
        this.appCheckDeferredProvider = provider3;
        this.executorProvider = provider4;
    }

    @Override // javax.inject.Provider
    public FirebaseContextProvider get() {
        return newInstance(this.tokenProvider.get(), this.instanceIdProvider.get(), this.appCheckDeferredProvider.get(), this.executorProvider.get());
    }

    public static FirebaseContextProvider_Factory create(Provider<com.google.firebase.inject.Provider<InternalAuthProvider>> provider, Provider<com.google.firebase.inject.Provider<FirebaseInstanceIdInternal>> provider2, Provider<Deferred<InternalAppCheckTokenProvider>> provider3, Provider<Executor> provider4) {
        return new FirebaseContextProvider_Factory(provider, provider2, provider3, provider4);
    }

    public static FirebaseContextProvider newInstance(com.google.firebase.inject.Provider<InternalAuthProvider> provider, com.google.firebase.inject.Provider<FirebaseInstanceIdInternal> provider2, Deferred<InternalAppCheckTokenProvider> deferred, Executor executor) {
        return new FirebaseContextProvider(provider, provider2, deferred, executor);
    }
}
