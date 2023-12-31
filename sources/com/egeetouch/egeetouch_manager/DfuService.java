package com.egeetouch.egeetouch_manager;

import android.app.Activity;
import no.nordicsemi.android.dfu.DfuBaseService;
/* loaded from: classes.dex */
public class DfuService extends DfuBaseService {
    @Override // no.nordicsemi.android.dfu.DfuBaseService
    protected boolean isDebug() {
        return false;
    }

    @Override // no.nordicsemi.android.dfu.DfuBaseService
    protected Class<? extends Activity> getNotificationTarget() {
        return DfuNotificationActivity.class;
    }
}
