package com.google.firebase.functions;

import android.content.Context;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.appcheck.interop.InternalAppCheckTokenProvider;
import com.google.firebase.auth.internal.InternalAuthProvider;
import com.google.firebase.functions.FunctionsComponent;
import com.google.firebase.functions.FunctionsMultiResourceComponent;
import com.google.firebase.functions.dagger.internal.DoubleCheck;
import com.google.firebase.functions.dagger.internal.Factory;
import com.google.firebase.functions.dagger.internal.InstanceFactory;
import com.google.firebase.functions.dagger.internal.Preconditions;
import com.google.firebase.iid.internal.FirebaseInstanceIdInternal;
import com.google.firebase.inject.Deferred;
import com.google.firebase.inject.Provider;
import java.util.concurrent.Executor;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class DaggerFunctionsComponent {
    private DaggerFunctionsComponent() {
    }

    public static FunctionsComponent.Builder builder() {
        return new Builder();
    }

    /* loaded from: classes2.dex */
    private static final class Builder implements FunctionsComponent.Builder {
        private Deferred<InternalAppCheckTokenProvider> setAppCheck;
        private Context setApplicationContext;
        private Provider<InternalAuthProvider> setAuth;
        private FirebaseOptions setFirebaseOptions;
        private Provider<FirebaseInstanceIdInternal> setIid;
        private Executor setLiteExecutor;
        private Executor setUiExecutor;

        private Builder() {
        }

        @Override // com.google.firebase.functions.FunctionsComponent.Builder
        public /* bridge */ /* synthetic */ FunctionsComponent.Builder setAppCheck(Deferred deferred) {
            return setAppCheck((Deferred<InternalAppCheckTokenProvider>) deferred);
        }

        @Override // com.google.firebase.functions.FunctionsComponent.Builder
        public /* bridge */ /* synthetic */ FunctionsComponent.Builder setAuth(Provider provider) {
            return setAuth((Provider<InternalAuthProvider>) provider);
        }

        @Override // com.google.firebase.functions.FunctionsComponent.Builder
        public /* bridge */ /* synthetic */ FunctionsComponent.Builder setIid(Provider provider) {
            return setIid((Provider<FirebaseInstanceIdInternal>) provider);
        }

        @Override // com.google.firebase.functions.FunctionsComponent.Builder
        public Builder setApplicationContext(Context context) {
            this.setApplicationContext = (Context) Preconditions.checkNotNull(context);
            return this;
        }

        @Override // com.google.firebase.functions.FunctionsComponent.Builder
        public Builder setFirebaseOptions(FirebaseOptions firebaseOptions) {
            this.setFirebaseOptions = (FirebaseOptions) Preconditions.checkNotNull(firebaseOptions);
            return this;
        }

        @Override // com.google.firebase.functions.FunctionsComponent.Builder
        public Builder setLiteExecutor(Executor executor) {
            this.setLiteExecutor = (Executor) Preconditions.checkNotNull(executor);
            return this;
        }

        @Override // com.google.firebase.functions.FunctionsComponent.Builder
        public Builder setUiExecutor(Executor executor) {
            this.setUiExecutor = (Executor) Preconditions.checkNotNull(executor);
            return this;
        }

        @Override // com.google.firebase.functions.FunctionsComponent.Builder
        public Builder setAuth(Provider<InternalAuthProvider> provider) {
            this.setAuth = (Provider) Preconditions.checkNotNull(provider);
            return this;
        }

        @Override // com.google.firebase.functions.FunctionsComponent.Builder
        public Builder setIid(Provider<FirebaseInstanceIdInternal> provider) {
            this.setIid = (Provider) Preconditions.checkNotNull(provider);
            return this;
        }

        @Override // com.google.firebase.functions.FunctionsComponent.Builder
        public Builder setAppCheck(Deferred<InternalAppCheckTokenProvider> deferred) {
            this.setAppCheck = (Deferred) Preconditions.checkNotNull(deferred);
            return this;
        }

        @Override // com.google.firebase.functions.FunctionsComponent.Builder
        public FunctionsComponent build() {
            Preconditions.checkBuilderRequirement(this.setApplicationContext, Context.class);
            Preconditions.checkBuilderRequirement(this.setFirebaseOptions, FirebaseOptions.class);
            Preconditions.checkBuilderRequirement(this.setLiteExecutor, Executor.class);
            Preconditions.checkBuilderRequirement(this.setUiExecutor, Executor.class);
            Preconditions.checkBuilderRequirement(this.setAuth, Provider.class);
            Preconditions.checkBuilderRequirement(this.setIid, Provider.class);
            Preconditions.checkBuilderRequirement(this.setAppCheck, Deferred.class);
            return new FunctionsComponentImpl(this.setApplicationContext, this.setFirebaseOptions, this.setLiteExecutor, this.setUiExecutor, this.setAuth, this.setIid, this.setAppCheck);
        }
    }

    /* loaded from: classes2.dex */
    private static final class FunctionsComponentImpl implements FunctionsComponent {
        private javax.inject.Provider<String> bindProjectIdProvider;
        private javax.inject.Provider<FirebaseContextProvider> firebaseContextProvider;
        private javax.inject.Provider<FunctionsMultiResourceComponent.FirebaseFunctionsFactory> firebaseFunctionsFactoryProvider;
        private FirebaseFunctions_Factory firebaseFunctionsProvider;
        private final FunctionsComponentImpl functionsComponentImpl;
        private javax.inject.Provider<FunctionsMultiResourceComponent> functionsMultiResourceComponentProvider;
        private javax.inject.Provider<Deferred<InternalAppCheckTokenProvider>> setAppCheckProvider;
        private javax.inject.Provider<Context> setApplicationContextProvider;
        private javax.inject.Provider<Provider<InternalAuthProvider>> setAuthProvider;
        private javax.inject.Provider<FirebaseOptions> setFirebaseOptionsProvider;
        private javax.inject.Provider<Provider<FirebaseInstanceIdInternal>> setIidProvider;
        private javax.inject.Provider<Executor> setLiteExecutorProvider;
        private javax.inject.Provider<Executor> setUiExecutorProvider;

        private FunctionsComponentImpl(Context context, FirebaseOptions firebaseOptions, Executor executor, Executor executor2, Provider<InternalAuthProvider> provider, Provider<FirebaseInstanceIdInternal> provider2, Deferred<InternalAppCheckTokenProvider> deferred) {
            this.functionsComponentImpl = this;
            initialize(context, firebaseOptions, executor, executor2, provider, provider2, deferred);
        }

        private void initialize(Context context, FirebaseOptions firebaseOptions, Executor executor, Executor executor2, Provider<InternalAuthProvider> provider, Provider<FirebaseInstanceIdInternal> provider2, Deferred<InternalAppCheckTokenProvider> deferred) {
            this.setApplicationContextProvider = InstanceFactory.create(context);
            Factory create = InstanceFactory.create(firebaseOptions);
            this.setFirebaseOptionsProvider = create;
            this.bindProjectIdProvider = FunctionsComponent_MainModule_BindProjectIdFactory.create(create);
            this.setAuthProvider = InstanceFactory.create(provider);
            this.setIidProvider = InstanceFactory.create(provider2);
            this.setAppCheckProvider = InstanceFactory.create(deferred);
            Factory create2 = InstanceFactory.create(executor);
            this.setLiteExecutorProvider = create2;
            this.firebaseContextProvider = DoubleCheck.provider(FirebaseContextProvider_Factory.create(this.setAuthProvider, this.setIidProvider, this.setAppCheckProvider, create2));
            Factory create3 = InstanceFactory.create(executor2);
            this.setUiExecutorProvider = create3;
            FirebaseFunctions_Factory create4 = FirebaseFunctions_Factory.create(this.setApplicationContextProvider, this.bindProjectIdProvider, this.firebaseContextProvider, this.setLiteExecutorProvider, create3);
            this.firebaseFunctionsProvider = create4;
            javax.inject.Provider<FunctionsMultiResourceComponent.FirebaseFunctionsFactory> create5 = FunctionsMultiResourceComponent_FirebaseFunctionsFactory_Impl.create(create4);
            this.firebaseFunctionsFactoryProvider = create5;
            this.functionsMultiResourceComponentProvider = DoubleCheck.provider(FunctionsMultiResourceComponent_Factory.create(create5));
        }

        @Override // com.google.firebase.functions.FunctionsComponent
        public FunctionsMultiResourceComponent getMultiResourceComponent() {
            return this.functionsMultiResourceComponentProvider.get();
        }
    }
}
