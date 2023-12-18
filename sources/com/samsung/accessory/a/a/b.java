package com.samsung.accessory.a.a;

import android.content.Context;
/* loaded from: classes2.dex */
class b extends com.samsung.android.sdk.accessory.b {
    static {
        com.samsung.android.sdk.accessory.b.a = new b();
    }

    b() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.samsung.android.sdk.accessory.b
    public final void a(int i) {
        d.b(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.samsung.android.sdk.accessory.b
    public final void a(Context context) {
        if (d.a()) {
            return;
        }
        d.a(new e(context, "SaBufferPool-SDK", d.a(context) ? 2621440 : 7340032));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.samsung.android.sdk.accessory.b
    public final a b(int i) {
        return d.a(i);
    }
}
