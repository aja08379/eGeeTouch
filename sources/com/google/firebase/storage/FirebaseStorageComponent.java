package com.google.firebase.storage;

import com.google.firebase.FirebaseApp;
import com.google.firebase.appcheck.interop.InternalAppCheckTokenProvider;
import com.google.firebase.auth.internal.InternalAuthProvider;
import com.google.firebase.inject.Provider;
import java.util.HashMap;
import java.util.Map;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class FirebaseStorageComponent {
    private final FirebaseApp app;
    private final Provider<InternalAppCheckTokenProvider> appCheckProvider;
    private final Provider<InternalAuthProvider> authProvider;
    private final Map<String, FirebaseStorage> instances = new HashMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    public FirebaseStorageComponent(FirebaseApp firebaseApp, Provider<InternalAuthProvider> provider, Provider<InternalAppCheckTokenProvider> provider2) {
        this.app = firebaseApp;
        this.authProvider = provider;
        this.appCheckProvider = provider2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized FirebaseStorage get(String str) {
        FirebaseStorage firebaseStorage;
        firebaseStorage = this.instances.get(str);
        if (firebaseStorage == null) {
            firebaseStorage = new FirebaseStorage(str, this.app, this.authProvider, this.appCheckProvider);
            this.instances.put(str, firebaseStorage);
        }
        return firebaseStorage;
    }

    synchronized void clearInstancesForTesting() {
        this.instances.clear();
    }
}
