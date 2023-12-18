package com.kunzisoft.switchdatetime.time.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import com.kunzisoft.switchdatetime.R;
/* loaded from: classes2.dex */
public class TimeCircleView extends View {
    private static final String TAG = "CircleView";
    private float mAmPmCircleRadiusMultiplier;
    private int mCenterCircleColor;
    private int mCircleBackgroundColor;
    private int mCircleRadius;
    private float mCircleRadiusMultiplier;
    private boolean mDrawValuesReady;
    private boolean mIs24HourMode;
    private boolean mIsInitialized;
    private final Paint mPaint;
    private int mXCenter;
    private int mYCenter;

    public TimeCircleView(Context context) {
        this(context, null, 0);
    }

    public TimeCircleView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TimeCircleView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Paint paint = new Paint();
        this.mPaint = paint;
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.TimeCircleView);
        setCircleColor(obtainStyledAttributes.getColor(R.styleable.TimeCircleView_timeCircleColor, -1));
        setCenterColor(obtainStyledAttributes.getColor(R.styleable.TimeCircleView_timeCenterColor, -16777216));
        obtainStyledAttributes.recycle();
        paint.setAntiAlias(true);
        this.mIsInitialized = false;
    }

    public void initialize(Context context, boolean z) {
        if (this.mIsInitialized) {
            Log.e(TAG, "CircleView may only be initialized once.");
            return;
        }
        Resources resources = context.getResources();
        this.mIs24HourMode = z;
        if (z) {
            this.mCircleRadiusMultiplier = Float.parseFloat(resources.getString(R.string.circle_radius_multiplier_24HourMode));
        } else {
            this.mCircleRadiusMultiplier = Float.parseFloat(resources.getString(R.string.circle_radius_multiplier));
            this.mAmPmCircleRadiusMultiplier = Float.parseFloat(resources.getString(R.string.ampm_circle_radius_multiplier));
        }
        this.mIsInitialized = true;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        int height;
        if (getWidth() == 0 || !this.mIsInitialized) {
            return;
        }
        if (!this.mDrawValuesReady) {
            this.mXCenter = getWidth() / 2;
            this.mYCenter = getHeight() / 2;
            int min = (int) (Math.min(this.mXCenter, height) * this.mCircleRadiusMultiplier);
            this.mCircleRadius = min;
            if (!this.mIs24HourMode) {
                this.mYCenter -= ((int) (min * this.mAmPmCircleRadiusMultiplier)) / 2;
            }
            this.mDrawValuesReady = true;
        }
        this.mPaint.setColor(this.mCircleBackgroundColor);
        canvas.drawCircle(this.mXCenter, this.mYCenter, this.mCircleRadius, this.mPaint);
        this.mPaint.setColor(this.mCenterCircleColor);
        canvas.drawCircle(this.mXCenter, this.mYCenter, 2.0f, this.mPaint);
    }

    public int getCircleColor() {
        return this.mCircleBackgroundColor;
    }

    public void setCircleColor(int i) {
        this.mCircleBackgroundColor = i;
    }

    public int getCenterColor() {
        return this.mCenterCircleColor;
    }

    public void setCenterColor(int i) {
        this.mCenterCircleColor = i;
    }
}
