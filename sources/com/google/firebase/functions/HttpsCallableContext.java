package com.google.firebase.functions;
/* loaded from: classes2.dex */
class HttpsCallableContext {
    private final String appCheckToken;
    private final String authToken;
    private final String instanceIdToken;

    /* JADX INFO: Access modifiers changed from: package-private */
    public HttpsCallableContext(String str, String str2, String str3) {
        this.authToken = str;
        this.instanceIdToken = str2;
        this.appCheckToken = str3;
    }

    public String getAuthToken() {
        return this.authToken;
    }

    public String getInstanceIdToken() {
        return this.instanceIdToken;
    }

    public String getAppCheckToken() {
        return this.appCheckToken;
    }
}
