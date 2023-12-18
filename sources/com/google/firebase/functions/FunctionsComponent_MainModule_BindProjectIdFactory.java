package com.google.firebase.functions;

import com.google.firebase.FirebaseOptions;
import com.google.firebase.functions.FunctionsComponent;
import com.google.firebase.functions.dagger.internal.Factory;
import com.google.firebase.functions.dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes2.dex */
public final class FunctionsComponent_MainModule_BindProjectIdFactory implements Factory<String> {
    private final Provider<FirebaseOptions> optionsProvider;

    public FunctionsComponent_MainModule_BindProjectIdFactory(Provider<FirebaseOptions> provider) {
        this.optionsProvider = provider;
    }

    @Override // javax.inject.Provider
    public String get() {
        return bindProjectId(this.optionsProvider.get());
    }

    public static FunctionsComponent_MainModule_BindProjectIdFactory create(Provider<FirebaseOptions> provider) {
        return new FunctionsComponent_MainModule_BindProjectIdFactory(provider);
    }

    public static String bindProjectId(FirebaseOptions firebaseOptions) {
        return (String) Preconditions.checkNotNullFromProvides(FunctionsComponent.MainModule.bindProjectId(firebaseOptions));
    }
}
