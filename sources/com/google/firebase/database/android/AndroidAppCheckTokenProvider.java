package com.google.firebase.database.android;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.appcheck.AppCheckTokenResult;
import com.google.firebase.appcheck.interop.AppCheckTokenListener;
import com.google.firebase.appcheck.interop.InternalAppCheckTokenProvider;
import com.google.firebase.database.core.TokenProvider;
import com.google.firebase.inject.Deferred;
import com.google.firebase.inject.Provider;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes2.dex */
public class AndroidAppCheckTokenProvider implements TokenProvider {
    private final Deferred<InternalAppCheckTokenProvider> deferredAppCheckProvider;
    private final AtomicReference<InternalAppCheckTokenProvider> internalAppCheck = new AtomicReference<>();

    @Override // com.google.firebase.database.core.TokenProvider
    public void removeTokenChangeListener(TokenProvider.TokenChangeListener tokenChangeListener) {
    }

    public AndroidAppCheckTokenProvider(Deferred<InternalAppCheckTokenProvider> deferred) {
        this.deferredAppCheckProvider = deferred;
        deferred.whenAvailable(new Deferred.DeferredHandler() { // from class: com.google.firebase.database.android.-$$Lambda$AndroidAppCheckTokenProvider$f0GiRSsCYRBKXadOYOLSE4tgMLI
            @Override // com.google.firebase.inject.Deferred.DeferredHandler
            public final void handle(Provider provider) {
                AndroidAppCheckTokenProvider.this.lambda$new$0$AndroidAppCheckTokenProvider(provider);
            }
        });
    }

    public /* synthetic */ void lambda$new$0$AndroidAppCheckTokenProvider(Provider provider) {
        this.internalAppCheck.set((InternalAppCheckTokenProvider) provider.get());
    }

    @Override // com.google.firebase.database.core.TokenProvider
    public void getToken(boolean z, final TokenProvider.GetTokenCompletionListener getTokenCompletionListener) {
        InternalAppCheckTokenProvider internalAppCheckTokenProvider = this.internalAppCheck.get();
        if (internalAppCheckTokenProvider != null) {
            internalAppCheckTokenProvider.getToken(z).addOnSuccessListener(new OnSuccessListener() { // from class: com.google.firebase.database.android.-$$Lambda$AndroidAppCheckTokenProvider$feO0JTUBrqZMA0BlrqqLudVZMtE
                @Override // com.google.android.gms.tasks.OnSuccessListener
                public final void onSuccess(Object obj) {
                    TokenProvider.GetTokenCompletionListener.this.onSuccess(((AppCheckTokenResult) obj).getToken());
                }
            }).addOnFailureListener(new OnFailureListener() { // from class: com.google.firebase.database.android.-$$Lambda$AndroidAppCheckTokenProvider$iAOtN9aMfj74tiIrsOjLZoNPn4Y
                @Override // com.google.android.gms.tasks.OnFailureListener
                public final void onFailure(Exception exc) {
                    TokenProvider.GetTokenCompletionListener.this.onError(exc.getMessage());
                }
            });
        } else {
            getTokenCompletionListener.onSuccess(null);
        }
    }

    @Override // com.google.firebase.database.core.TokenProvider
    public void addTokenChangeListener(final ExecutorService executorService, final TokenProvider.TokenChangeListener tokenChangeListener) {
        this.deferredAppCheckProvider.whenAvailable(new Deferred.DeferredHandler() { // from class: com.google.firebase.database.android.-$$Lambda$AndroidAppCheckTokenProvider$JxBAiM_6GBJDGmLThDrcqfNbwXc
            @Override // com.google.firebase.inject.Deferred.DeferredHandler
            public final void handle(Provider provider) {
                ((InternalAppCheckTokenProvider) provider.get()).addAppCheckTokenListener(new AppCheckTokenListener() { // from class: com.google.firebase.database.android.-$$Lambda$AndroidAppCheckTokenProvider$60vFfzkhuN4dN7asQDMFpqCxmE0
                    @Override // com.google.firebase.appcheck.interop.AppCheckTokenListener
                    public final void onAppCheckTokenChanged(AppCheckTokenResult appCheckTokenResult) {
                        r1.execute(new Runnable() { // from class: com.google.firebase.database.android.-$$Lambda$AndroidAppCheckTokenProvider$d5ouCwY_MlmKFbkPIcW9iYuM3gk
                            @Override // java.lang.Runnable
                            public final void run() {
                                TokenProvider.TokenChangeListener.this.onTokenChange(appCheckTokenResult.getToken());
                            }
                        });
                    }
                });
            }
        });
    }
}
