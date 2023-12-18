package com.google.firebase.auth;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.internal.InternalAuthProvider;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;
import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.components.Dependency;
import com.google.firebase.heartbeatinfo.HeartBeatConsumerComponent;
import com.google.firebase.heartbeatinfo.HeartBeatController;
import com.google.firebase.platforminfo.LibraryVersionComponent;
import java.util.Arrays;
import java.util.List;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* loaded from: classes2.dex */
public class FirebaseAuthRegistrar implements ComponentRegistrar {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ FirebaseAuth lambda$getComponents$0(ComponentContainer componentContainer) {
        return new com.google.firebase.auth.internal.zzv((FirebaseApp) componentContainer.get(FirebaseApp.class), componentContainer.getProvider(HeartBeatController.class));
    }

    @Override // com.google.firebase.components.ComponentRegistrar
    public List<Component<?>> getComponents() {
        return Arrays.asList(Component.builder(FirebaseAuth.class, InternalAuthProvider.class).add(Dependency.required(FirebaseApp.class)).add(Dependency.requiredProvider(HeartBeatController.class)).factory(new ComponentFactory() { // from class: com.google.firebase.auth.zzx
            @Override // com.google.firebase.components.ComponentFactory
            public final Object create(ComponentContainer componentContainer) {
                return FirebaseAuthRegistrar.lambda$getComponents$0(componentContainer);
            }
        }).eagerInDefaultApp().build(), HeartBeatConsumerComponent.create(), LibraryVersionComponent.create("fire-auth", "21.1.0"));
    }
}
