package com.google.firebase.functions;

import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class HttpsCallOptions {
    private static final long DEFAULT_TIMEOUT = 70;
    private static final TimeUnit DEFAULT_TIMEOUT_UNITS = TimeUnit.SECONDS;
    private long timeout = DEFAULT_TIMEOUT;
    private TimeUnit timeoutUnits = DEFAULT_TIMEOUT_UNITS;

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setTimeout(long j, TimeUnit timeUnit) {
        this.timeout = j;
        this.timeoutUnits = timeUnit;
    }

    public long getTimeout() {
        return this.timeoutUnits.toMillis(this.timeout);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public OkHttpClient apply(OkHttpClient okHttpClient) {
        return okHttpClient.newBuilder().callTimeout(this.timeout, this.timeoutUnits).readTimeout(this.timeout, this.timeoutUnits).build();
    }
}
