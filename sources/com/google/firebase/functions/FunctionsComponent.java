package com.google.firebase.functions;

import android.content.Context;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.appcheck.interop.InternalAppCheckTokenProvider;
import com.google.firebase.auth.internal.InternalAuthProvider;
import com.google.firebase.functions.dagger.Binds;
import com.google.firebase.functions.dagger.BindsInstance;
import com.google.firebase.functions.dagger.Component;
import com.google.firebase.functions.dagger.Module;
import com.google.firebase.functions.dagger.Provides;
import com.google.firebase.iid.internal.FirebaseInstanceIdInternal;
import com.google.firebase.inject.Deferred;
import com.google.firebase.inject.Provider;
import java.util.concurrent.Executor;
import javax.inject.Named;
import javax.inject.Singleton;
/* JADX INFO: Access modifiers changed from: package-private */
@Component(modules = {MainModule.class})
@Singleton
/* loaded from: classes2.dex */
public interface FunctionsComponent {

    @Component.Builder
    /* loaded from: classes2.dex */
    public interface Builder {
        FunctionsComponent build();

        @BindsInstance
        Builder setAppCheck(Deferred<InternalAppCheckTokenProvider> deferred);

        @BindsInstance
        Builder setApplicationContext(Context context);

        @BindsInstance
        Builder setAuth(Provider<InternalAuthProvider> provider);

        @BindsInstance
        Builder setFirebaseOptions(FirebaseOptions firebaseOptions);

        @BindsInstance
        Builder setIid(Provider<FirebaseInstanceIdInternal> provider);

        @BindsInstance
        Builder setLiteExecutor(Executor executor);

        @BindsInstance
        Builder setUiExecutor(Executor executor);
    }

    FunctionsMultiResourceComponent getMultiResourceComponent();

    @Module
    /* loaded from: classes2.dex */
    public interface MainModule {
        @Binds
        ContextProvider contextProvider(FirebaseContextProvider firebaseContextProvider);

        @Provides
        @Named("projectId")
        static String bindProjectId(FirebaseOptions firebaseOptions) {
            return firebaseOptions.getProjectId();
        }
    }
}
