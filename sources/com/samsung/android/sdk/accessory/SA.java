package com.samsung.android.sdk.accessory;

import android.content.Context;
import android.util.Log;
import com.samsung.android.sdk.SsdkInterface;
import com.samsung.android.sdk.SsdkUnsupportedException;
/* loaded from: classes2.dex */
public class SA implements SsdkInterface {
    public static final int DEVICE_ACCESSORY = 0;
    public static final int SERVICE_FILETRANSFER = 1;
    public static final int SERVICE_MESSAGE = 2;
    public static final int SERVICE_SOCKET = 3;
    private k a;
    private boolean b = false;

    /* JADX INFO: Access modifiers changed from: protected */
    public void clearSdkConfig() {
        this.a = null;
    }

    @Override // com.samsung.android.sdk.SsdkInterface
    public int getVersionCode() {
        return 9;
    }

    @Override // com.samsung.android.sdk.SsdkInterface
    public String getVersionName() {
        return "2.6.4";
    }

    @Override // com.samsung.android.sdk.SsdkInterface
    public void initialize(Context context) throws SsdkUnsupportedException {
        if (context == null) {
            throw new IllegalArgumentException("Illegal argument: context");
        }
        if (this.a == null) {
            if (!this.b) {
                g.a(context, "SACU", context.getPackageName());
                this.b = true;
            }
            try {
                this.a = new k(context);
                Log.d("[SA_SDK]SA", "Initializing SA");
                b.a().a(context);
            } catch (d e) {
                throw new SsdkUnsupportedException(e.getMessage(), e.a());
            }
        }
    }

    @Override // com.samsung.android.sdk.SsdkInterface
    public boolean isFeatureEnabled(int i) {
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i == 3) {
                        return true;
                    }
                    throw new IllegalArgumentException();
                }
                return k.i();
            }
            return k.l();
        }
        return true;
    }
}
