package com.google.firebase.functions;

import com.google.firebase.functions.FunctionsMultiResourceComponent;
import com.google.firebase.functions.dagger.internal.InstanceFactory;
import javax.inject.Provider;
/* loaded from: classes2.dex */
public final class FunctionsMultiResourceComponent_FirebaseFunctionsFactory_Impl implements FunctionsMultiResourceComponent.FirebaseFunctionsFactory {
    private final FirebaseFunctions_Factory delegateFactory;

    FunctionsMultiResourceComponent_FirebaseFunctionsFactory_Impl(FirebaseFunctions_Factory firebaseFunctions_Factory) {
        this.delegateFactory = firebaseFunctions_Factory;
    }

    @Override // com.google.firebase.functions.FunctionsMultiResourceComponent.FirebaseFunctionsFactory
    public FirebaseFunctions create(String str) {
        return this.delegateFactory.get(str);
    }

    public static Provider<FunctionsMultiResourceComponent.FirebaseFunctionsFactory> create(FirebaseFunctions_Factory firebaseFunctions_Factory) {
        return InstanceFactory.create(new FunctionsMultiResourceComponent_FirebaseFunctionsFactory_Impl(firebaseFunctions_Factory));
    }
}
