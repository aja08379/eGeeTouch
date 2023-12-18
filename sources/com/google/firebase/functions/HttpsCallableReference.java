package com.google.firebase.functions;

import com.google.android.gms.tasks.Task;
import java.net.URL;
import java.util.concurrent.TimeUnit;
/* loaded from: classes2.dex */
public class HttpsCallableReference {
    private final FirebaseFunctions functionsClient;
    private final String name;
    HttpsCallOptions options;
    private final URL url;

    /* JADX INFO: Access modifiers changed from: package-private */
    public HttpsCallableReference(FirebaseFunctions firebaseFunctions, String str) {
        this.options = new HttpsCallOptions();
        this.functionsClient = firebaseFunctions;
        this.name = str;
        this.url = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public HttpsCallableReference(FirebaseFunctions firebaseFunctions, URL url) {
        this.options = new HttpsCallOptions();
        this.functionsClient = firebaseFunctions;
        this.name = null;
        this.url = url;
    }

    public Task<HttpsCallableResult> call(Object obj) {
        String str = this.name;
        if (str != null) {
            return this.functionsClient.call(str, obj, this.options);
        }
        return this.functionsClient.call(this.url, obj, this.options);
    }

    public Task<HttpsCallableResult> call() {
        String str = this.name;
        if (str != null) {
            return this.functionsClient.call(str, (Object) null, this.options);
        }
        return this.functionsClient.call(this.url, (Object) null, this.options);
    }

    public void setTimeout(long j, TimeUnit timeUnit) {
        this.options.setTimeout(j, timeUnit);
    }

    public long getTimeout() {
        return this.options.getTimeout();
    }

    public HttpsCallableReference withTimeout(long j, TimeUnit timeUnit) {
        HttpsCallableReference httpsCallableReference = new HttpsCallableReference(this.functionsClient, this.name);
        httpsCallableReference.setTimeout(j, timeUnit);
        return httpsCallableReference;
    }
}
