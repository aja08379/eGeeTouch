package androidx.work.impl.model;

import androidx.work.Data;
/* loaded from: classes.dex */
public class WorkProgress {
    public final Data mProgress;
    public final String mWorkSpecId;

    public WorkProgress(String workSpecId, Data progress) {
        this.mWorkSpecId = workSpecId;
        this.mProgress = progress;
    }
}
