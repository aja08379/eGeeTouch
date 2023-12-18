package com.google.firebase.database.core;

import java.util.concurrent.ExecutorService;
/* loaded from: classes2.dex */
public interface TokenProvider {

    /* loaded from: classes2.dex */
    public interface GetTokenCompletionListener {
        void onError(String str);

        void onSuccess(String str);
    }

    /* loaded from: classes2.dex */
    public interface TokenChangeListener {
        void onTokenChange();

        void onTokenChange(String str);
    }

    void addTokenChangeListener(ExecutorService executorService, TokenChangeListener tokenChangeListener);

    void getToken(boolean z, GetTokenCompletionListener getTokenCompletionListener);

    void removeTokenChangeListener(TokenChangeListener tokenChangeListener);
}
