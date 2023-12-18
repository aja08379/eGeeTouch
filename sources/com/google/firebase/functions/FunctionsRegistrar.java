package com.google.firebase.functions;

import android.content.Context;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.annotations.concurrent.Lightweight;
import com.google.firebase.annotations.concurrent.UiThread;
import com.google.firebase.appcheck.interop.InternalAppCheckTokenProvider;
import com.google.firebase.auth.internal.InternalAuthProvider;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;
import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.components.Dependency;
import com.google.firebase.components.Qualified;
import com.google.firebase.iid.internal.FirebaseInstanceIdInternal;
import com.google.firebase.platforminfo.LibraryVersionComponent;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;
/* loaded from: classes2.dex */
public class FunctionsRegistrar implements ComponentRegistrar {
    private static final String LIBRARY_NAME = "fire-fn";

    @Override // com.google.firebase.components.ComponentRegistrar
    public List<Component<?>> getComponents() {
        final Qualified qualified = Qualified.qualified(Lightweight.class, Executor.class);
        final Qualified qualified2 = Qualified.qualified(UiThread.class, Executor.class);
        return Arrays.asList(Component.builder(FunctionsMultiResourceComponent.class).name(LIBRARY_NAME).add(Dependency.required(Context.class)).add(Dependency.required(FirebaseOptions.class)).add(Dependency.optionalProvider(InternalAuthProvider.class)).add(Dependency.requiredProvider(FirebaseInstanceIdInternal.class)).add(Dependency.deferred(InternalAppCheckTokenProvider.class)).add(Dependency.required(qualified)).add(Dependency.required(qualified2)).factory(new ComponentFactory() { // from class: com.google.firebase.functions.-$$Lambda$FunctionsRegistrar$gwiU4Ub0bON1LcK5HymstFBhi4o
            @Override // com.google.firebase.components.ComponentFactory
            public final Object create(ComponentContainer componentContainer) {
                FunctionsMultiResourceComponent multiResourceComponent;
                multiResourceComponent = DaggerFunctionsComponent.builder().setApplicationContext((Context) componentContainer.get(Context.class)).setFirebaseOptions((FirebaseOptions) componentContainer.get(FirebaseOptions.class)).setLiteExecutor((Executor) componentContainer.get(Qualified.this)).setUiExecutor((Executor) componentContainer.get(qualified2)).setAuth(componentContainer.getProvider(InternalAuthProvider.class)).setIid(componentContainer.getProvider(FirebaseInstanceIdInternal.class)).setAppCheck(componentContainer.getDeferred(InternalAppCheckTokenProvider.class)).build().getMultiResourceComponent();
                return multiResourceComponent;
            }
        }).build(), LibraryVersionComponent.create(LIBRARY_NAME, BuildConfig.VERSION_NAME));
    }
}
