package com.google.firebase.functions;

import com.google.firebase.functions.dagger.assisted.Assisted;
import com.google.firebase.functions.dagger.assisted.AssistedFactory;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Singleton;
/* JADX INFO: Access modifiers changed from: package-private */
@Singleton
/* loaded from: classes2.dex */
public class FunctionsMultiResourceComponent {
    private final FirebaseFunctionsFactory functionsFactory;
    private final Map<String, FirebaseFunctions> instances = new HashMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    @AssistedFactory
    /* loaded from: classes2.dex */
    public interface FirebaseFunctionsFactory {
        FirebaseFunctions create(@Assisted String str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    public FunctionsMultiResourceComponent(FirebaseFunctionsFactory firebaseFunctionsFactory) {
        this.functionsFactory = firebaseFunctionsFactory;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized FirebaseFunctions get(String str) {
        FirebaseFunctions firebaseFunctions;
        firebaseFunctions = this.instances.get(str);
        if (firebaseFunctions == null) {
            firebaseFunctions = this.functionsFactory.create(str);
            this.instances.put(str, firebaseFunctions);
        }
        return firebaseFunctions;
    }
}
