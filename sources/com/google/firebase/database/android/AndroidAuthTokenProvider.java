package com.google.firebase.database.android;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApiNotAvailableException;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.auth.internal.IdTokenListener;
import com.google.firebase.auth.internal.InternalAuthProvider;
import com.google.firebase.database.core.TokenProvider;
import com.google.firebase.inject.Deferred;
import com.google.firebase.inject.Provider;
import com.google.firebase.internal.InternalTokenResult;
import com.google.firebase.internal.api.FirebaseNoSignedInUserException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes2.dex */
public class AndroidAuthTokenProvider implements TokenProvider {
    private final Deferred<InternalAuthProvider> deferredAuthProvider;
    private final AtomicReference<InternalAuthProvider> internalAuth = new AtomicReference<>();

    @Override // com.google.firebase.database.core.TokenProvider
    public void removeTokenChangeListener(TokenProvider.TokenChangeListener tokenChangeListener) {
    }

    public AndroidAuthTokenProvider(Deferred<InternalAuthProvider> deferred) {
        this.deferredAuthProvider = deferred;
        deferred.whenAvailable(new Deferred.DeferredHandler() { // from class: com.google.firebase.database.android.-$$Lambda$AndroidAuthTokenProvider$--R-Ilhra2k6_cGxhH02l4o804c
            @Override // com.google.firebase.inject.Deferred.DeferredHandler
            public final void handle(Provider provider) {
                AndroidAuthTokenProvider.this.lambda$new$0$AndroidAuthTokenProvider(provider);
            }
        });
    }

    public /* synthetic */ void lambda$new$0$AndroidAuthTokenProvider(Provider provider) {
        this.internalAuth.set((InternalAuthProvider) provider.get());
    }

    @Override // com.google.firebase.database.core.TokenProvider
    public void getToken(boolean z, final TokenProvider.GetTokenCompletionListener getTokenCompletionListener) {
        InternalAuthProvider internalAuthProvider = this.internalAuth.get();
        if (internalAuthProvider != null) {
            internalAuthProvider.getAccessToken(z).addOnSuccessListener(new OnSuccessListener() { // from class: com.google.firebase.database.android.-$$Lambda$AndroidAuthTokenProvider$RrOSb5RJWdeYq_-Ke5cq8RhbGFA
                @Override // com.google.android.gms.tasks.OnSuccessListener
                public final void onSuccess(Object obj) {
                    TokenProvider.GetTokenCompletionListener.this.onSuccess(((GetTokenResult) obj).getToken());
                }
            }).addOnFailureListener(new OnFailureListener() { // from class: com.google.firebase.database.android.-$$Lambda$AndroidAuthTokenProvider$pcPyao0pWuVVb2BgLPVQ5kDUhNs
                @Override // com.google.android.gms.tasks.OnFailureListener
                public final void onFailure(Exception exc) {
                    AndroidAuthTokenProvider.lambda$getToken$2(TokenProvider.GetTokenCompletionListener.this, exc);
                }
            });
        } else {
            getTokenCompletionListener.onSuccess(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$getToken$2(TokenProvider.GetTokenCompletionListener getTokenCompletionListener, Exception exc) {
        if (isUnauthenticatedUsage(exc)) {
            getTokenCompletionListener.onSuccess(null);
        } else {
            getTokenCompletionListener.onError(exc.getMessage());
        }
    }

    @Override // com.google.firebase.database.core.TokenProvider
    public void addTokenChangeListener(final ExecutorService executorService, final TokenProvider.TokenChangeListener tokenChangeListener) {
        this.deferredAuthProvider.whenAvailable(new Deferred.DeferredHandler() { // from class: com.google.firebase.database.android.-$$Lambda$AndroidAuthTokenProvider$sdRvRtBwMBY61aPy__XJnUM0XV0
            @Override // com.google.firebase.inject.Deferred.DeferredHandler
            public final void handle(Provider provider) {
                ((InternalAuthProvider) provider.get()).addIdTokenListener(new IdTokenListener() { // from class: com.google.firebase.database.android.-$$Lambda$AndroidAuthTokenProvider$osJYqH9k0s6WcvseFM2DDFHB78I
                    @Override // com.google.firebase.auth.internal.IdTokenListener
                    public final void onIdTokenChanged(InternalTokenResult internalTokenResult) {
                        r1.execute(new Runnable() { // from class: com.google.firebase.database.android.-$$Lambda$AndroidAuthTokenProvider$gJsys6XzUCti6lenAdcfFvM3tKY
                            @Override // java.lang.Runnable
                            public final void run() {
                                TokenProvider.TokenChangeListener.this.onTokenChange(internalTokenResult.getToken());
                            }
                        });
                    }
                });
            }
        });
    }

    private static boolean isUnauthenticatedUsage(Exception exc) {
        return (exc instanceof FirebaseApiNotAvailableException) || (exc instanceof FirebaseNoSignedInUserException);
    }
}
