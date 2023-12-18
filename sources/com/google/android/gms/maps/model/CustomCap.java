package com.google.android.gms.maps.model;
/* compiled from: com.google.android.gms:play-services-maps@@18.1.0 */
/* loaded from: classes2.dex */
public final class CustomCap extends Cap {
    public final BitmapDescriptor bitmapDescriptor;
    public final float refWidth;

    public CustomCap(BitmapDescriptor bitmapDescriptor) {
        this(bitmapDescriptor, 10.0f);
    }

    @Override // com.google.android.gms.maps.model.Cap
    public String toString() {
        String valueOf = String.valueOf(this.bitmapDescriptor);
        float f = this.refWidth;
        return "[CustomCap: bitmapDescriptor=" + valueOf + " refWidth=" + f + "]";
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public CustomCap(com.google.android.gms.maps.model.BitmapDescriptor r3, float r4) {
        super(r0, r4);
        com.google.android.gms.maps.model.BitmapDescriptor r0 = (com.google.android.gms.maps.model.BitmapDescriptor) com.google.android.gms.common.internal.Preconditions.checkNotNull(r3, "bitmapDescriptor must not be null");
        if (r4 > 0.0f) {
            r2.bitmapDescriptor = r3;
            r2.refWidth = r4;
            return;
        }
        throw new java.lang.IllegalArgumentException("refWidth must be positive");
    }
}
