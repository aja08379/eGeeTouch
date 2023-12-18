package com.google.android.gms.vision;

import android.util.Log;
import android.util.SparseArray;
import com.google.android.gms.vision.Detector;
/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.3 */
/* loaded from: classes2.dex */
public abstract class FocusingProcessor<T> implements Detector.Processor<T> {
    private Detector<T> zza;
    private Tracker<T> zzb;
    private int zze;
    private int zzc = 3;
    private boolean zzd = false;
    private int zzf = 0;

    public FocusingProcessor(Detector<T> detector, Tracker<T> tracker) {
        this.zza = detector;
        this.zzb = tracker;
    }

    public abstract int selectFocus(Detector.Detections<T> detections);

    @Override // com.google.android.gms.vision.Detector.Processor
    public void release() {
        this.zzb.onDone();
    }

    @Override // com.google.android.gms.vision.Detector.Processor
    public void receiveDetections(Detector.Detections<T> detections) {
        SparseArray<T> detectedItems = detections.getDetectedItems();
        if (detectedItems.size() == 0) {
            if (this.zzf == this.zzc) {
                this.zzb.onDone();
                this.zzd = false;
            } else {
                this.zzb.onMissing(detections);
            }
            this.zzf++;
            return;
        }
        this.zzf = 0;
        if (this.zzd) {
            T t = detectedItems.get(this.zze);
            if (t != null) {
                this.zzb.onUpdate(detections, t);
                return;
            } else {
                this.zzb.onDone();
                this.zzd = false;
            }
        }
        int selectFocus = selectFocus(detections);
        T t2 = detectedItems.get(selectFocus);
        if (t2 == null) {
            Log.w("FocusingProcessor", new StringBuilder(35).append("Invalid focus selected: ").append(selectFocus).toString());
            return;
        }
        this.zzd = true;
        this.zze = selectFocus;
        this.zza.setFocus(selectFocus);
        this.zzb.onNewItem(this.zze, t2);
        this.zzb.onUpdate(detections, t2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void zza(int i) {
        if (i < 0) {
            throw new IllegalArgumentException(new StringBuilder(28).append("Invalid max gap: ").append(i).toString());
        }
        this.zzc = i;
    }
}
