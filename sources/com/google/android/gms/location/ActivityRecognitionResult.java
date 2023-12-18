package com.google.android.gms.location;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import java.util.Collections;
import java.util.List;
/* loaded from: classes2.dex */
public class ActivityRecognitionResult extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<ActivityRecognitionResult> CREATOR = new zzb();
    private Bundle extras;
    private List<DetectedActivity> zze;
    private long zzf;
    private long zzg;
    private int zzh;

    public ActivityRecognitionResult(DetectedActivity detectedActivity, long j, long j2) {
        this(detectedActivity, j, j2, 0, (Bundle) null);
    }

    private ActivityRecognitionResult(DetectedActivity detectedActivity, long j, long j2, int i, Bundle bundle) {
        this(Collections.singletonList(detectedActivity), j, j2, 0, (Bundle) null);
    }

    public ActivityRecognitionResult(List<DetectedActivity> list, long j, long j2) {
        this(list, j, j2, 0, (Bundle) null);
    }

    public ActivityRecognitionResult(List<DetectedActivity> list, long j, long j2, int i, Bundle bundle) {
        boolean z = true;
        Preconditions.checkArgument(list != null && list.size() > 0, "Must have at least 1 detected activity");
        Preconditions.checkArgument((j <= 0 || j2 <= 0) ? false : z, "Must set times");
        this.zze = list;
        this.zzf = j;
        this.zzg = j2;
        this.zzh = i;
        this.extras = bundle;
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x0022, code lost:
        if ((r0 instanceof com.google.android.gms.location.ActivityRecognitionResult) != false) goto L6;
     */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0028 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0029  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.google.android.gms.location.ActivityRecognitionResult extractResult(android.content.Intent r3) {
        com.google.android.gms.location.ActivityRecognitionResult r0;
        if (hasResult(r3)) {
            java.lang.Object r0 = r3.getExtras().get("com.google.android.location.internal.EXTRA_ACTIVITY_RESULT");
            if (r0 instanceof byte[]) {
                r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer.deserializeFromBytes((byte[]) r0, com.google.android.gms.location.ActivityRecognitionResult.CREATOR);
            }
            r0 = (com.google.android.gms.location.ActivityRecognitionResult) r0;
            if (r0 == null) {
                return r0;
            }
            java.util.List<com.google.android.gms.location.ActivityRecognitionResult> r3 = zza(r3);
            if (r3 == null || r3.isEmpty()) {
                return null;
            }
            return r3.get(r3.size() - 1);
        }
        r0 = null;
        if (r0 == null) {
        }
    }

    public static boolean hasResult(Intent intent) {
        if (intent == null) {
            return false;
        }
        if (intent == null ? false : intent.hasExtra("com.google.android.location.internal.EXTRA_ACTIVITY_RESULT")) {
            return true;
        }
        List<ActivityRecognitionResult> zza = zza(intent);
        return (zza == null || zza.isEmpty()) ? false : true;
    }

    private static List<ActivityRecognitionResult> zza(Intent intent) {
        if (intent == null ? false : intent.hasExtra("com.google.android.location.internal.EXTRA_ACTIVITY_RESULT_LIST")) {
            return SafeParcelableSerializer.deserializeIterableFromIntentExtra(intent, "com.google.android.location.internal.EXTRA_ACTIVITY_RESULT_LIST", CREATOR);
        }
        return null;
    }

    private static boolean zza(Bundle bundle, Bundle bundle2) {
        if (bundle == null && bundle2 == null) {
            return true;
        }
        if ((bundle != null || bundle2 == null) && ((bundle == null || bundle2 != null) && bundle.size() == bundle2.size())) {
            for (String str : bundle.keySet()) {
                if (!bundle2.containsKey(str)) {
                    return false;
                }
                if (bundle.get(str) == null) {
                    if (bundle2.get(str) != null) {
                        return false;
                    }
                } else if (bundle.get(str) instanceof Bundle) {
                    if (!zza(bundle.getBundle(str), bundle2.getBundle(str))) {
                        return false;
                    }
                } else if (!bundle.get(str).equals(bundle2.get(str))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            ActivityRecognitionResult activityRecognitionResult = (ActivityRecognitionResult) obj;
            if (this.zzf == activityRecognitionResult.zzf && this.zzg == activityRecognitionResult.zzg && this.zzh == activityRecognitionResult.zzh && Objects.equal(this.zze, activityRecognitionResult.zze) && zza(this.extras, activityRecognitionResult.extras)) {
                return true;
            }
        }
        return false;
    }

    public int getActivityConfidence(int i) {
        for (DetectedActivity detectedActivity : this.zze) {
            if (detectedActivity.getType() == i) {
                return detectedActivity.getConfidence();
            }
        }
        return 0;
    }

    public long getElapsedRealtimeMillis() {
        return this.zzg;
    }

    public DetectedActivity getMostProbableActivity() {
        return this.zze.get(0);
    }

    public List<DetectedActivity> getProbableActivities() {
        return this.zze;
    }

    public long getTime() {
        return this.zzf;
    }

    public int hashCode() {
        return Objects.hashCode(Long.valueOf(this.zzf), Long.valueOf(this.zzg), Integer.valueOf(this.zzh), this.zze, this.extras);
    }

    public String toString() {
        String valueOf = String.valueOf(this.zze);
        long j = this.zzf;
        return new StringBuilder(String.valueOf(valueOf).length() + 124).append("ActivityRecognitionResult [probableActivities=").append(valueOf).append(", timeMillis=").append(j).append(", elapsedRealtimeMillis=").append(this.zzg).append("]").toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, this.zze, false);
        SafeParcelWriter.writeLong(parcel, 2, this.zzf);
        SafeParcelWriter.writeLong(parcel, 3, this.zzg);
        SafeParcelWriter.writeInt(parcel, 4, this.zzh);
        SafeParcelWriter.writeBundle(parcel, 5, this.extras, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
