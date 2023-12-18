package com.google.firebase.auth;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* loaded from: classes2.dex */
public class FacebookAuthProvider {
    public static final String FACEBOOK_SIGN_IN_METHOD = "facebook.com";
    public static final String PROVIDER_ID = "facebook.com";

    private FacebookAuthProvider() {
    }

    public static AuthCredential getCredential(String str) {
        return new FacebookAuthCredential(str);
    }
}
