package com.facebook.internal;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import com.facebook.FacebookSdk;
/* loaded from: classes.dex */
public class CustomTab {
    private Uri uri;

    public CustomTab(String str, Bundle bundle) {
        this.uri = Utility.buildUri(ServerProtocol.getDialogAuthority(), FacebookSdk.getGraphApiVersion() + "/" + ServerProtocol.DIALOG_PATH + str, bundle == null ? new Bundle() : bundle);
    }

    public void openCustomTab(Activity activity, String str) {
        CustomTabsIntent build = new CustomTabsIntent.Builder().build();
        build.intent.setPackage(str);
        build.intent.addFlags(BasicMeasure.EXACTLY);
        build.launchUrl(activity, this.uri);
    }
}
