package com.kunzisoft.switchdatetime.time.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import com.kunzisoft.switchdatetime.R;
import java.text.DateFormatSymbols;
/* loaded from: classes2.dex */
public class TimeAmPmCirclesView extends View {
    private static final int AM = 0;
    private static final int PM = 1;
    private static final int PRESSED_ALPHA = 175;
    private static final int SELECTED_ALPHA = 51;
    private static final String TAG = "AmPmCirclesView";
    private int mAmOrPm;
    private int mAmOrPmPressed;
    private int mAmPmCircleRadius;
    private float mAmPmCircleRadiusMultiplier;
    private int mAmPmTextColor;
    private int mAmPmYCenter;
    private String mAmText;
    private int mAmXCenter;
    private int mBackgroundColor;
    private float mCircleRadiusMultiplier;
    private boolean mDrawValuesReady;
    private boolean mInverseSelectedColors;
    private boolean mIsInitialized;
    private final Paint mPaint;
    private String mPmText;
    private int mPmXCenter;
    private int mSelectBackgroundColor;

    public TimeAmPmCirclesView(Context context) {
        this(context, null, 0);
    }

    public TimeAmPmCirclesView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TimeAmPmCirclesView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mPaint = new Paint();
        this.mBackgroundColor = -1;
        this.mAmPmTextColor = -16777216;
        this.mSelectBackgroundColor = -16776961;
        this.mInverseSelectedColors = false;
        this.mIsInitialized = false;
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.TimeAmPmCirclesView);
        setCircleColor(obtainStyledAttributes.getColor(R.styleable.TimeAmPmCirclesView_timeAmPmBackgroundColor, this.mBackgroundColor));
        setSelectCircleColor(obtainStyledAttributes.getColor(R.styleable.TimeAmPmCirclesView_timeAmPmSelectBackgroundColor, this.mSelectBackgroundColor));
        setAmPmTextColor(obtainStyledAttributes.getColor(R.styleable.TimeAmPmCirclesView_timeAmPmTextColor, this.mAmPmTextColor));
        setInverseSelectedColors(obtainStyledAttributes.getBoolean(R.styleable.TimeAmPmCirclesView_timeAmPmHighlightSelected, this.mInverseSelectedColors));
        obtainStyledAttributes.recycle();
    }

    public void initialize(Context context, int i) {
        if (this.mIsInitialized) {
            Log.e(TAG, "AmPmCirclesView may only be initialized once.");
            return;
        }
        Resources resources = context.getResources();
        this.mPaint.setTypeface(Typeface.create(resources.getString(R.string.sans_serif), 0));
        this.mPaint.setAntiAlias(true);
        this.mPaint.setTextAlign(Paint.Align.CENTER);
        this.mCircleRadiusMultiplier = Float.parseFloat(resources.getString(R.string.circle_radius_multiplier));
        this.mAmPmCircleRadiusMultiplier = Float.parseFloat(resources.getString(R.string.ampm_circle_radius_multiplier));
        String[] amPmStrings = new DateFormatSymbols().getAmPmStrings();
        this.mAmText = amPmStrings[0];
        this.mPmText = amPmStrings[1];
        setAmOrPm(i);
        this.mAmOrPmPressed = -1;
        this.mIsInitialized = true;
    }

    public void setAmOrPm(int i) {
        this.mAmOrPm = i;
    }

    public void setAmOrPmPressed(int i) {
        this.mAmOrPmPressed = i;
    }

    public int getIsTouchingAmOrPm(float f, float f2) {
        if (this.mDrawValuesReady) {
            int i = this.mAmPmYCenter;
            int i2 = (int) ((f2 - i) * (f2 - i));
            int i3 = this.mAmXCenter;
            float f3 = i2;
            if (((int) Math.sqrt(((f - i3) * (f - i3)) + f3)) <= this.mAmPmCircleRadius) {
                return 0;
            }
            int i4 = this.mPmXCenter;
            return ((int) Math.sqrt((double) (((f - ((float) i4)) * (f - ((float) i4))) + f3))) <= this.mAmPmCircleRadius ? 1 : -1;
        }
        return -1;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        int i;
        int i2;
        if (getWidth() == 0 || !this.mIsInitialized) {
            return;
        }
        if (!this.mDrawValuesReady) {
            int width = getWidth() / 2;
            int height = getHeight() / 2;
            int min = (int) (Math.min(width, height) * this.mCircleRadiusMultiplier);
            this.mAmPmCircleRadius = (int) (min * this.mAmPmCircleRadiusMultiplier);
            this.mPaint.setTextSize((i2 * 3) / 4);
            int i3 = this.mAmPmCircleRadius;
            this.mAmPmYCenter = (height - (i3 / 2)) + min;
            this.mAmXCenter = (width - min) + i3;
            this.mPmXCenter = (width + min) - i3;
            this.mDrawValuesReady = true;
        }
        int i4 = this.mBackgroundColor;
        boolean z = this.mInverseSelectedColors;
        int i5 = 255;
        int i6 = PRESSED_ALPHA;
        int i7 = 51;
        if (z) {
            int i8 = this.mAmOrPm;
            if (i8 == 0) {
                i = this.mSelectBackgroundColor;
            } else if (i8 == 1) {
                i = i4;
                i4 = this.mSelectBackgroundColor;
                i7 = 255;
                i5 = 51;
            } else {
                i = i4;
                i7 = 255;
            }
            int i9 = this.mAmOrPmPressed;
            if (i9 == 0) {
                i = this.mSelectBackgroundColor;
                i7 = PRESSED_ALPHA;
                i6 = i5;
                this.mPaint.setColor(i4);
                this.mPaint.setAlpha(i6);
                canvas.drawCircle(this.mAmXCenter, this.mAmPmYCenter, this.mAmPmCircleRadius, this.mPaint);
                this.mPaint.setColor(i);
                this.mPaint.setAlpha(i7);
                canvas.drawCircle(this.mPmXCenter, this.mAmPmYCenter, this.mAmPmCircleRadius, this.mPaint);
                this.mPaint.setColor(this.mAmPmTextColor);
                float descent = this.mAmPmYCenter - (((int) (this.mPaint.descent() + this.mPaint.ascent())) / 2);
                canvas.drawText(this.mAmText, this.mAmXCenter, descent, this.mPaint);
                canvas.drawText(this.mPmText, this.mPmXCenter, descent, this.mPaint);
            }
            if (i9 == 1) {
                i4 = this.mSelectBackgroundColor;
                this.mPaint.setColor(i4);
                this.mPaint.setAlpha(i6);
                canvas.drawCircle(this.mAmXCenter, this.mAmPmYCenter, this.mAmPmCircleRadius, this.mPaint);
                this.mPaint.setColor(i);
                this.mPaint.setAlpha(i7);
                canvas.drawCircle(this.mPmXCenter, this.mAmPmYCenter, this.mAmPmCircleRadius, this.mPaint);
                this.mPaint.setColor(this.mAmPmTextColor);
                float descent2 = this.mAmPmYCenter - (((int) (this.mPaint.descent() + this.mPaint.ascent())) / 2);
                canvas.drawText(this.mAmText, this.mAmXCenter, descent2, this.mPaint);
                canvas.drawText(this.mPmText, this.mPmXCenter, descent2, this.mPaint);
            }
            i6 = i5;
            this.mPaint.setColor(i4);
            this.mPaint.setAlpha(i6);
            canvas.drawCircle(this.mAmXCenter, this.mAmPmYCenter, this.mAmPmCircleRadius, this.mPaint);
            this.mPaint.setColor(i);
            this.mPaint.setAlpha(i7);
            canvas.drawCircle(this.mPmXCenter, this.mAmPmYCenter, this.mAmPmCircleRadius, this.mPaint);
            this.mPaint.setColor(this.mAmPmTextColor);
            float descent22 = this.mAmPmYCenter - (((int) (this.mPaint.descent() + this.mPaint.ascent())) / 2);
            canvas.drawText(this.mAmText, this.mAmXCenter, descent22, this.mPaint);
            canvas.drawText(this.mPmText, this.mPmXCenter, descent22, this.mPaint);
        }
        int i10 = this.mAmOrPm;
        if (i10 == 0) {
            i = i4;
            i4 = this.mSelectBackgroundColor;
            i7 = 255;
            i5 = 51;
        } else if (i10 == 1) {
            i = this.mSelectBackgroundColor;
        } else {
            i = i4;
            i7 = 255;
        }
        int i11 = this.mAmOrPmPressed;
        if (i11 == 0) {
            i4 = this.mSelectBackgroundColor;
            this.mPaint.setColor(i4);
            this.mPaint.setAlpha(i6);
            canvas.drawCircle(this.mAmXCenter, this.mAmPmYCenter, this.mAmPmCircleRadius, this.mPaint);
            this.mPaint.setColor(i);
            this.mPaint.setAlpha(i7);
            canvas.drawCircle(this.mPmXCenter, this.mAmPmYCenter, this.mAmPmCircleRadius, this.mPaint);
            this.mPaint.setColor(this.mAmPmTextColor);
            float descent222 = this.mAmPmYCenter - (((int) (this.mPaint.descent() + this.mPaint.ascent())) / 2);
            canvas.drawText(this.mAmText, this.mAmXCenter, descent222, this.mPaint);
            canvas.drawText(this.mPmText, this.mPmXCenter, descent222, this.mPaint);
        }
        if (i11 == 1) {
            i = this.mSelectBackgroundColor;
            i7 = PRESSED_ALPHA;
        }
        i6 = i5;
        this.mPaint.setColor(i4);
        this.mPaint.setAlpha(i6);
        canvas.drawCircle(this.mAmXCenter, this.mAmPmYCenter, this.mAmPmCircleRadius, this.mPaint);
        this.mPaint.setColor(i);
        this.mPaint.setAlpha(i7);
        canvas.drawCircle(this.mPmXCenter, this.mAmPmYCenter, this.mAmPmCircleRadius, this.mPaint);
        this.mPaint.setColor(this.mAmPmTextColor);
        float descent2222 = this.mAmPmYCenter - (((int) (this.mPaint.descent() + this.mPaint.ascent())) / 2);
        canvas.drawText(this.mAmText, this.mAmXCenter, descent2222, this.mPaint);
        canvas.drawText(this.mPmText, this.mPmXCenter, descent2222, this.mPaint);
    }

    public int getCircleColor() {
        return this.mBackgroundColor;
    }

    public void setCircleColor(int i) {
        this.mBackgroundColor = i;
    }

    public int getAmPmTextColor() {
        return this.mAmPmTextColor;
    }

    public void setAmPmTextColor(int i) {
        this.mAmPmTextColor = i;
    }

    public int getSelectCircleColor() {
        return this.mSelectBackgroundColor;
    }

    public void setSelectCircleColor(int i) {
        this.mSelectBackgroundColor = i;
    }

    public void setInverseSelectedColors(boolean z) {
        this.mInverseSelectedColors = z;
    }
}
