package com.google.android.gms.cloudmessaging;

import android.os.Bundle;
import com.facebook.share.internal.ShareConstants;
/* compiled from: com.google.android.gms:play-services-cloud-messaging@@16.0.0 */
/* loaded from: classes.dex */
final class zzs extends zzq<Bundle> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzs(int i, int i2, Bundle bundle) {
        super(i, 1, bundle);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.cloudmessaging.zzq
    public final boolean zza() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.cloudmessaging.zzq
    public final void zza(Bundle bundle) {
        Bundle bundle2 = bundle.getBundle(ShareConstants.WEB_DIALOG_PARAM_DATA);
        if (bundle2 == null) {
            bundle2 = Bundle.EMPTY;
        }
        zza((zzs) bundle2);
    }
}
