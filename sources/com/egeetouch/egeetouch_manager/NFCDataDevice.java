package com.egeetouch.egeetouch_manager;

import android.app.Application;
import android.nfc.Tag;
/* loaded from: classes.dex */
public class NFCDataDevice extends Application {
    private Tag currentTag;
    private String uid;

    public void setCurrentTag(Tag tag) {
        this.currentTag = tag;
    }

    public Tag getCurrentTag() {
        return this.currentTag;
    }

    public void setUid(String str) {
        this.uid = str;
    }

    public String getUid() {
        return this.uid;
    }
}
