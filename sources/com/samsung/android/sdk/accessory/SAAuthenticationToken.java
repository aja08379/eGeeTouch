package com.samsung.android.sdk.accessory;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
/* loaded from: classes2.dex */
public class SAAuthenticationToken {
    public static final int AUTHENTICATION_TYPE_CERTIFICATE_X509 = 1548;
    public static final int AUTHENTICATION_TYPE_NONE = 1547;
    private byte[] a;
    private int b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SAAuthenticationToken(int i, byte[] bArr) {
        if (bArr != null) {
            this.a = Arrays.copyOf(bArr, bArr.length);
        }
        this.b = i;
    }

    public int getAuthenticationType() {
        return this.b;
    }

    public byte[] getKey() {
        return this.a;
    }

    public String toString() {
        String str;
        try {
            str = this.a != null ? new String(this.a, "ISO-8859-1") : null;
        } catch (UnsupportedEncodingException unused) {
            str = "Failed to encode Key!! charsetISO-8859-1 is not supported";
        }
        return "Type:" + this.b + " Key:" + str;
    }
}
