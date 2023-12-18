package com.prolificinteractive.materialcalendarview.spans;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.style.LineBackgroundSpan;
/* loaded from: classes2.dex */
public class DotSpan implements LineBackgroundSpan {
    public static final float DEFAULT_RADIUS = 3.0f;
    private final int color;
    private final float radius;

    public DotSpan() {
        this.radius = 3.0f;
        this.color = 0;
    }

    public DotSpan(int i) {
        this.radius = 3.0f;
        this.color = i;
    }

    public DotSpan(float f) {
        this.radius = f;
        this.color = 0;
    }

    public DotSpan(float f, int i) {
        this.radius = f;
        this.color = i;
    }

    @Override // android.text.style.LineBackgroundSpan
    public void drawBackground(Canvas canvas, Paint paint, int i, int i2, int i3, int i4, int i5, CharSequence charSequence, int i6, int i7, int i8) {
        int color = paint.getColor();
        int i9 = this.color;
        if (i9 != 0) {
            paint.setColor(i9);
        }
        float f = this.radius;
        canvas.drawCircle((i + i2) / 2, i5 + f, f, paint);
        paint.setColor(color);
    }
}
