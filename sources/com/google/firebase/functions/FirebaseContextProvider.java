package com.google.firebase.functions;

import android.util.Log;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.appcheck.AppCheckTokenResult;
import com.google.firebase.appcheck.interop.AppCheckTokenListener;
import com.google.firebase.appcheck.interop.InternalAppCheckTokenProvider;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.auth.internal.InternalAuthProvider;
import com.google.firebase.iid.internal.FirebaseInstanceIdInternal;
import com.google.firebase.inject.Deferred;
import com.google.firebase.inject.Provider;
import com.google.firebase.internal.api.FirebaseNoSignedInUserException;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;
import javax.inject.Inject;
import javax.inject.Singleton;
/* JADX INFO: Access modifiers changed from: package-private */
@Singleton
/* loaded from: classes2.dex */
public class FirebaseContextProvider implements ContextProvider {
    private final String TAG = "FirebaseContextProvider";
    private final AtomicReference<InternalAppCheckTokenProvider> appCheckRef = new AtomicReference<>();
    private final Executor executor;
    private final Provider<FirebaseInstanceIdInternal> instanceId;
    private final Provider<InternalAuthProvider> tokenProvider;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$new$0(AppCheckTokenResult appCheckTokenResult) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    public FirebaseContextProvider(Provider<InternalAuthProvider> provider, Provider<FirebaseInstanceIdInternal> provider2, Deferred<InternalAppCheckTokenProvider> deferred, Executor executor) {
        this.tokenProvider = provider;
        this.instanceId = provider2;
        this.executor = executor;
        deferred.whenAvailable(new Deferred.DeferredHandler() { // from class: com.google.firebase.functions.-$$Lambda$FirebaseContextProvider$evStJctNX3e8OX6GDj_iRv0hh3A
            @Override // com.google.firebase.inject.Deferred.DeferredHandler
            public final void handle(Provider provider3) {
                FirebaseContextProvider.this.lambda$new$1$FirebaseContextProvider(provider3);
            }
        });
    }

    public /* synthetic */ void lambda$new$1$FirebaseContextProvider(Provider provider) {
        InternalAppCheckTokenProvider internalAppCheckTokenProvider = (InternalAppCheckTokenProvider) provider.get();
        this.appCheckRef.set(internalAppCheckTokenProvider);
        internalAppCheckTokenProvider.addAppCheckTokenListener(new AppCheckTokenListener() { // from class: com.google.firebase.functions.-$$Lambda$FirebaseContextProvider$qxwpW2MXdQeNzRquYTOSrwCOtMQ
            @Override // com.google.firebase.appcheck.interop.AppCheckTokenListener
            public final void onAppCheckTokenChanged(AppCheckTokenResult appCheckTokenResult) {
                FirebaseContextProvider.lambda$new$0(appCheckTokenResult);
            }
        });
    }

    @Override // com.google.firebase.functions.ContextProvider
    public Task<HttpsCallableContext> getContext() {
        final Task<String> authToken = getAuthToken();
        final Task<String> appCheckToken = getAppCheckToken();
        return Tasks.whenAll(authToken, appCheckToken).onSuccessTask(this.executor, new SuccessContinuation() { // from class: com.google.firebase.functions.-$$Lambda$FirebaseContextProvider$JpPWfEIwsUrrbB9R-dUWAHi9zJo
            @Override // com.google.android.gms.tasks.SuccessContinuation
            public final Task then(Object obj) {
                return FirebaseContextProvider.this.lambda$getContext$2$FirebaseContextProvider(authToken, appCheckToken, (Void) obj);
            }
        });
    }

    public /* synthetic */ Task lambda$getContext$2$FirebaseContextProvider(Task task, Task task2, Void r4) throws Exception {
        return Tasks.forResult(new HttpsCallableContext((String) task.getResult(), this.instanceId.get().getToken(), (String) task2.getResult()));
    }

    private Task<String> getAuthToken() {
        InternalAuthProvider internalAuthProvider = this.tokenProvider.get();
        if (internalAuthProvider == null) {
            return Tasks.forResult(null);
        }
        return internalAuthProvider.getAccessToken(false).continueWith(this.executor, new Continuation() { // from class: com.google.firebase.functions.-$$Lambda$FirebaseContextProvider$C3-edF7jTQLYkaAoKd_z9Bd3Zpw
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                return FirebaseContextProvider.lambda$getAuthToken$3(task);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ String lambda$getAuthToken$3(Task task) throws Exception {
        if (!task.isSuccessful()) {
            Exception exception = task.getException();
            if (exception instanceof FirebaseNoSignedInUserException) {
                return null;
            }
            throw exception;
        }
        return ((GetTokenResult) task.getResult()).getToken();
    }

    private Task<String> getAppCheckToken() {
        InternalAppCheckTokenProvider internalAppCheckTokenProvider = this.appCheckRef.get();
        if (internalAppCheckTokenProvider == null) {
            return Tasks.forResult(null);
        }
        return internalAppCheckTokenProvider.getToken(false).onSuccessTask(this.executor, new SuccessContinuation() { // from class: com.google.firebase.functions.-$$Lambda$FirebaseContextProvider$gKoozp-YAdV72llAs8IQRMTySus
            @Override // com.google.android.gms.tasks.SuccessContinuation
            public final Task then(Object obj) {
                return FirebaseContextProvider.this.lambda$getAppCheckToken$4$FirebaseContextProvider((AppCheckTokenResult) obj);
            }
        });
    }

    public /* synthetic */ Task lambda$getAppCheckToken$4$FirebaseContextProvider(AppCheckTokenResult appCheckTokenResult) throws Exception {
        if (appCheckTokenResult.getError() != null) {
            Log.w("FirebaseContextProvider", "Error getting App Check token. Error: " + appCheckTokenResult.getError());
            return Tasks.forResult(null);
        }
        return Tasks.forResult(appCheckTokenResult.getToken());
    }
}
