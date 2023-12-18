package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* loaded from: classes2.dex */
public class ActivityTransition extends AbstractSafeParcelable {
    public static final int ACTIVITY_TRANSITION_ENTER = 0;
    public static final int ACTIVITY_TRANSITION_EXIT = 1;
    public static final Parcelable.Creator<ActivityTransition> CREATOR = new zzc();
    private final int zzi;
    private final int zzj;

    /* loaded from: classes2.dex */
    public static class Builder {
        private int zzi = -1;
        private int zzj = -1;

        public ActivityTransition build() {
            Preconditions.checkState(this.zzi != -1, "Activity type not set.");
            Preconditions.checkState(this.zzj != -1, "Activity transition type not set.");
            return new ActivityTransition(this.zzi, this.zzj);
        }

        public Builder setActivityTransition(int i) {
            ActivityTransition.zza(i);
            this.zzj = i;
            return this;
        }

        public Builder setActivityType(int i) {
            DetectedActivity.zzb(i);
            this.zzi = i;
            return this;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface SupportedActivityTransition {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ActivityTransition(int i, int i2) {
        this.zzi = i;
        this.zzj = i2;
    }

    public static void zza(int i) {
        boolean z = true;
        Preconditions.checkArgument((i < 0 || i > 1) ? false : false, new StringBuilder(41).append("Transition type ").append(i).append(" is not valid.").toString());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ActivityTransition) {
            ActivityTransition activityTransition = (ActivityTransition) obj;
            return this.zzi == activityTransition.zzi && this.zzj == activityTransition.zzj;
        }
        return false;
    }

    public int getActivityType() {
        return this.zzi;
    }

    public int getTransitionType() {
        return this.zzj;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.zzi), Integer.valueOf(this.zzj));
    }

    public String toString() {
        int i = this.zzi;
        return new StringBuilder(75).append("ActivityTransition [mActivityType=").append(i).append(", mTransitionType=").append(this.zzj).append(']').toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, getActivityType());
        SafeParcelWriter.writeInt(parcel, 2, getTransitionType());
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
