package com.google.firebase.functions;

import com.google.firebase.functions.FunctionsMultiResourceComponent;
import com.google.firebase.functions.dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes2.dex */
public final class FunctionsMultiResourceComponent_Factory implements Factory<FunctionsMultiResourceComponent> {
    private final Provider<FunctionsMultiResourceComponent.FirebaseFunctionsFactory> functionsFactoryProvider;

    public FunctionsMultiResourceComponent_Factory(Provider<FunctionsMultiResourceComponent.FirebaseFunctionsFactory> provider) {
        this.functionsFactoryProvider = provider;
    }

    @Override // javax.inject.Provider
    public FunctionsMultiResourceComponent get() {
        return newInstance(this.functionsFactoryProvider.get());
    }

    public static FunctionsMultiResourceComponent_Factory create(Provider<FunctionsMultiResourceComponent.FirebaseFunctionsFactory> provider) {
        return new FunctionsMultiResourceComponent_Factory(provider);
    }

    public static FunctionsMultiResourceComponent newInstance(Object obj) {
        return new FunctionsMultiResourceComponent((FunctionsMultiResourceComponent.FirebaseFunctionsFactory) obj);
    }
}
