package com.kunzisoft.switchdatetime.date.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatTextView;
import com.kunzisoft.switchdatetime.R;
/* loaded from: classes2.dex */
public class TextCircularIndicatorView extends AppCompatTextView {
    private int mCircleColor;
    private Paint mCirclePaint;

    public TextCircularIndicatorView(Context context) {
        this(context, null, 0);
    }

    public TextCircularIndicatorView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TextCircularIndicatorView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mCircleColor = -16776961;
        this.mCirclePaint = new Paint();
        init(attributeSet);
    }

    private void init(AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.TextCircularIndicatorView);
            setCircleColor(obtainStyledAttributes.getColor(R.styleable.TextCircularIndicatorView_colorCircleIndicator, this.mCircleColor));
            obtainStyledAttributes.recycle();
        }
        this.mCirclePaint.setFakeBoldText(true);
        this.mCirclePaint.setAntiAlias(true);
        this.mCirclePaint.setColor(this.mCircleColor);
        this.mCirclePaint.setTextAlign(Paint.Align.CENTER);
        this.mCirclePaint.setStyle(Paint.Style.FILL);
    }

    @Override // android.view.View
    public CharSequence getContentDescription() {
        return getText();
    }

    @Override // android.widget.TextView, android.view.View
    public void onDraw(Canvas canvas) {
        int width = getWidth();
        int height = getHeight();
        canvas.drawCircle(width / 2, height / 2, Math.min(width, height) / 2, this.mCirclePaint);
        super.onDraw(canvas);
    }

    public int getCircleColor() {
        return this.mCircleColor;
    }

    public void setCircleColor(int i) {
        this.mCircleColor = i;
    }
}
