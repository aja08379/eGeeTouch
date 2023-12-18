package com.appeaser.sublimepickerlibrary.timepicker;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.customview.widget.ExploreByTouchHelper;
import com.appeaser.sublimepickerlibrary.R;
import com.appeaser.sublimepickerlibrary.utilities.SUtils;
import com.google.android.material.timepicker.TimeModel;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
/* loaded from: classes.dex */
public class RadialTimePickerView extends View {
    private static final int ALPHA_OPAQUE = 255;
    private static final int ALPHA_TRANSPARENT = 0;
    private static final int AM = 0;
    private static final int DEGREES_FOR_ONE_HOUR = 30;
    private static final int DEGREES_FOR_ONE_MINUTE = 6;
    private static final int FADE_IN_DURATION = 500;
    private static final int FADE_OUT_DURATION = 500;
    private static final int HOURS = 0;
    private static final int HOURS_INNER = 2;
    private static final int HOURS_IN_CIRCLE = 12;
    private static final int MINUTES = 1;
    private static final int MINUTES_IN_CIRCLE = 60;
    private static final int NUM_POSITIONS = 12;
    private static final int PM = 1;
    private static final int SELECTOR_CIRCLE = 0;
    private static final int SELECTOR_DOT = 1;
    private static final int SELECTOR_LINE = 2;
    private static final String TAG = "RadialTimePickerView";
    private final IntHolder[] mAlpha;
    private int mAmOrPm;
    private int mCenterDotRadius;
    private boolean mChangedDuringTouch;
    private int mCircleRadius;
    private float mDisabledAlpha;
    private int mHalfwayDist;
    private final String[] mHours12Texts;
    private final ArrayList<Animator> mHoursToMinutesAnims;
    private final String[] mInnerHours24Texts;
    private String[] mInnerTextHours;
    private final float[] mInnerTextX;
    private final float[] mInnerTextY;
    private boolean mInputEnabled;
    private final InvalidateUpdateListener mInvalidateUpdateListener;
    private boolean mIs24HourMode;
    private boolean mIsOnInnerCircle;
    private OnValueSelectedListener mListener;
    private int mMaxDistForOuterNumber;
    private int mMinDistForInnerNumber;
    private final ArrayList<Animator> mMinuteToHoursAnims;
    private String[] mMinutesText;
    private final String[] mMinutesTexts;
    private final String[] mOuterHours24Texts;
    private String[] mOuterTextHours;
    private final float[][] mOuterTextX;
    private final float[][] mOuterTextY;
    private final Paint[] mPaint;
    private final Paint mPaintBackground;
    private final Paint mPaintCenter;
    private final Paint[][] mPaintSelector;
    private final int[] mSelectionDegrees;
    private int mSelectorColor;
    private int mSelectorDotColor;
    private int mSelectorDotRadius;
    private final Path mSelectorPath;
    private int mSelectorRadius;
    private int mSelectorStroke;
    private boolean mShowHours;
    private final ColorStateList[] mTextColor;
    private final int[] mTextInset;
    private final int[] mTextSize;
    private RadialPickerTouchHelper mTouchHelper;
    private AnimatorSet mTransition;
    private Typeface mTypeface;
    private int mXCenter;
    private int mYCenter;
    private static final int[] HOURS_NUMBERS = {12, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
    private static final int[] HOURS_NUMBERS_24 = {0, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23};
    private static final int[] MINUTES_NUMBERS = {0, 5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55};
    private static final int[] SNAP_PREFER_30S_MAP = new int[361];
    private static final float[] COS_30 = new float[12];
    private static final float[] SIN_30 = new float[12];

    /* loaded from: classes.dex */
    public interface OnValueSelectedListener {
        void onValueSelected(int i, int i2, boolean z);
    }

    private int applyAlpha(int i, int i2) {
        return (i & ViewCompat.MEASURED_SIZE_MASK) | (((int) ((((i >> 24) & 255) * (i2 / 255.0d)) + 0.5d)) << 24);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getDegreesForMinute(int i) {
        return i * 6;
    }

    static {
        preparePrefer30sMap();
        double d = 1.5707963267948966d;
        for (int i = 0; i < 12; i++) {
            COS_30[i] = (float) Math.cos(d);
            SIN_30[i] = (float) Math.sin(d);
            d += 0.5235987755982988d;
        }
    }

    private static void preparePrefer30sMap() {
        int i = 1;
        int i2 = 8;
        int i3 = 0;
        for (int i4 = 0; i4 < 361; i4++) {
            SNAP_PREFER_30S_MAP[i4] = i3;
            if (i == i2) {
                i3 += 6;
                if (i3 == 360) {
                    i2 = 7;
                } else {
                    i2 = i3 % 30 == 0 ? 14 : 4;
                }
                i = 1;
            } else {
                i++;
            }
        }
    }

    private static int snapPrefer30s(int i) {
        int[] iArr = SNAP_PREFER_30S_MAP;
        if (iArr == null) {
            return -1;
        }
        return iArr[i];
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int snapOnly30s(int i, int i2) {
        int i3 = (i / 30) * 30;
        int i4 = i3 + 30;
        if (i2 != 1) {
            if (i2 == -1) {
                return i == i3 ? i3 - 30 : i3;
            } else if (i - i3 < i4 - i) {
                return i3;
            }
        }
        return i4;
    }

    public RadialTimePickerView(Context context) {
        this(context, null);
    }

    public RadialTimePickerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.spRadialTimePickerStyle);
    }

    public RadialTimePickerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mInvalidateUpdateListener = new InvalidateUpdateListener();
        this.mHours12Texts = new String[12];
        this.mOuterHours24Texts = new String[12];
        this.mInnerHours24Texts = new String[12];
        this.mMinutesTexts = new String[12];
        this.mPaint = new Paint[2];
        this.mAlpha = new IntHolder[2];
        this.mPaintCenter = new Paint();
        this.mPaintSelector = (Paint[][]) Array.newInstance(Paint.class, 2, 3);
        this.mPaintBackground = new Paint();
        this.mTextColor = new ColorStateList[3];
        this.mTextSize = new int[3];
        this.mTextInset = new int[3];
        this.mOuterTextX = (float[][]) Array.newInstance(float.class, 2, 12);
        this.mOuterTextY = (float[][]) Array.newInstance(float.class, 2, 12);
        this.mInnerTextX = new float[12];
        this.mInnerTextY = new float[12];
        this.mSelectionDegrees = new int[2];
        this.mHoursToMinutesAnims = new ArrayList<>();
        this.mMinuteToHoursAnims = new ArrayList<>();
        this.mSelectorPath = new Path();
        this.mInputEnabled = true;
        this.mChangedDuringTouch = false;
        init(attributeSet, i, R.style.RadialTimePickerViewStyle);
    }

    public RadialTimePickerView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet);
        this.mInvalidateUpdateListener = new InvalidateUpdateListener();
        this.mHours12Texts = new String[12];
        this.mOuterHours24Texts = new String[12];
        this.mInnerHours24Texts = new String[12];
        this.mMinutesTexts = new String[12];
        this.mPaint = new Paint[2];
        this.mAlpha = new IntHolder[2];
        this.mPaintCenter = new Paint();
        this.mPaintSelector = (Paint[][]) Array.newInstance(Paint.class, 2, 3);
        this.mPaintBackground = new Paint();
        this.mTextColor = new ColorStateList[3];
        this.mTextSize = new int[3];
        this.mTextInset = new int[3];
        this.mOuterTextX = (float[][]) Array.newInstance(float.class, 2, 12);
        this.mOuterTextY = (float[][]) Array.newInstance(float.class, 2, 12);
        this.mInnerTextX = new float[12];
        this.mInnerTextY = new float[12];
        this.mSelectionDegrees = new int[2];
        this.mHoursToMinutesAnims = new ArrayList<>();
        this.mMinuteToHoursAnims = new ArrayList<>();
        this.mSelectorPath = new Path();
        this.mInputEnabled = true;
        this.mChangedDuringTouch = false;
        init(attributeSet, i, i2);
    }

    private void init(AttributeSet attributeSet, int i, int i2) {
        Context context = getContext();
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(16842803, typedValue, true);
        this.mDisabledAlpha = typedValue.getFloat();
        Resources resources = getResources();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.RadialTimePickerView, i, i2);
        this.mTypeface = Typeface.create("sans-serif", 0);
        int i3 = 0;
        while (true) {
            IntHolder[] intHolderArr = this.mAlpha;
            if (i3 >= intHolderArr.length) {
                break;
            }
            intHolderArr[i3] = new IntHolder(255);
            i3++;
        }
        this.mTextColor[0] = obtainStyledAttributes.getColorStateList(R.styleable.RadialTimePickerView_spNumbersTextColor);
        this.mTextColor[2] = obtainStyledAttributes.getColorStateList(R.styleable.RadialTimePickerView_spNumbersInnerTextColor);
        ColorStateList[] colorStateListArr = this.mTextColor;
        colorStateListArr[1] = colorStateListArr[0];
        this.mPaint[0] = new Paint();
        this.mPaint[0].setAntiAlias(true);
        this.mPaint[0].setTextAlign(Paint.Align.CENTER);
        this.mPaint[1] = new Paint();
        this.mPaint[1].setAntiAlias(true);
        this.mPaint[1].setTextAlign(Paint.Align.CENTER);
        ColorStateList colorStateList = obtainStyledAttributes.getColorStateList(R.styleable.RadialTimePickerView_spNumbersSelectorColor);
        int colorForState = colorStateList != null ? colorStateList.getColorForState(SUtils.resolveStateSet(3), 0) : -16777216;
        this.mPaintCenter.setColor(colorForState);
        this.mPaintCenter.setAntiAlias(true);
        int[] resolveStateSet = SUtils.resolveStateSet(3);
        this.mSelectorColor = colorForState;
        this.mSelectorDotColor = this.mTextColor[0].getColorForState(resolveStateSet, 0);
        this.mPaintSelector[0][0] = new Paint();
        this.mPaintSelector[0][0].setAntiAlias(true);
        this.mPaintSelector[0][1] = new Paint();
        this.mPaintSelector[0][1].setAntiAlias(true);
        this.mPaintSelector[0][2] = new Paint();
        this.mPaintSelector[0][2].setAntiAlias(true);
        this.mPaintSelector[0][2].setStrokeWidth(2.0f);
        this.mPaintSelector[1][0] = new Paint();
        this.mPaintSelector[1][0].setAntiAlias(true);
        this.mPaintSelector[1][1] = new Paint();
        this.mPaintSelector[1][1].setAntiAlias(true);
        this.mPaintSelector[1][2] = new Paint();
        this.mPaintSelector[1][2].setAntiAlias(true);
        this.mPaintSelector[1][2].setStrokeWidth(2.0f);
        this.mPaintBackground.setColor(obtainStyledAttributes.getColor(R.styleable.RadialTimePickerView_spNumbersBackgroundColor, ContextCompat.getColor(context, R.color.timepicker_default_numbers_background_color_material)));
        this.mPaintBackground.setAntiAlias(true);
        this.mSelectorRadius = resources.getDimensionPixelSize(R.dimen.sp_timepicker_selector_radius);
        this.mSelectorStroke = resources.getDimensionPixelSize(R.dimen.sp_timepicker_selector_stroke);
        this.mSelectorDotRadius = resources.getDimensionPixelSize(R.dimen.sp_timepicker_selector_dot_radius);
        this.mCenterDotRadius = resources.getDimensionPixelSize(R.dimen.sp_timepicker_center_dot_radius);
        this.mTextSize[0] = resources.getDimensionPixelSize(R.dimen.sp_timepicker_text_size_normal);
        this.mTextSize[1] = resources.getDimensionPixelSize(R.dimen.sp_timepicker_text_size_normal);
        this.mTextSize[2] = resources.getDimensionPixelSize(R.dimen.sp_timepicker_text_size_inner);
        this.mTextInset[0] = resources.getDimensionPixelSize(R.dimen.sp_timepicker_text_inset_normal);
        this.mTextInset[1] = resources.getDimensionPixelSize(R.dimen.sp_timepicker_text_inset_normal);
        this.mTextInset[2] = resources.getDimensionPixelSize(R.dimen.sp_timepicker_text_inset_inner);
        this.mShowHours = true;
        this.mIs24HourMode = false;
        this.mAmOrPm = 0;
        RadialPickerTouchHelper radialPickerTouchHelper = new RadialPickerTouchHelper();
        this.mTouchHelper = radialPickerTouchHelper;
        ViewCompat.setAccessibilityDelegate(this, radialPickerTouchHelper);
        if (ViewCompat.getImportantForAccessibility(this) == 0) {
            ViewCompat.setImportantForAccessibility(this, 1);
        }
        initHoursAndMinutesText();
        initData();
        obtainStyledAttributes.recycle();
        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        int i4 = calendar.get(11);
        int i5 = calendar.get(12);
        setCurrentHourInternal(i4, false, false);
        setCurrentMinuteInternal(i5, false);
        setHapticFeedbackEnabled(true);
    }

    public void initialize(int i, int i2, boolean z) {
        if (this.mIs24HourMode != z) {
            this.mIs24HourMode = z;
            initData();
        }
        setCurrentHourInternal(i, false, false);
        setCurrentMinuteInternal(i2, false);
    }

    public void setCurrentItemShowing(int i, boolean z) {
        if (i == 0) {
            showHours(z);
        } else if (i == 1) {
            showMinutes(z);
        } else {
            Log.e(TAG, "ClockView does not support showing item " + i);
        }
    }

    public int getCurrentItemShowing() {
        return !this.mShowHours ? 1 : 0;
    }

    public void setOnValueSelectedListener(OnValueSelectedListener onValueSelectedListener) {
        this.mListener = onValueSelectedListener;
    }

    public void setCurrentHour(int i) {
        setCurrentHourInternal(i, true, false);
    }

    private void setCurrentHourInternal(int i, boolean z, boolean z2) {
        OnValueSelectedListener onValueSelectedListener;
        this.mSelectionDegrees[0] = (i % 12) * 30;
        int i2 = (i == 0 || i % 24 < 12) ? 0 : 1;
        boolean innerCircleForHour = getInnerCircleForHour(i);
        if (this.mAmOrPm != i2 || this.mIsOnInnerCircle != innerCircleForHour) {
            this.mAmOrPm = i2;
            this.mIsOnInnerCircle = innerCircleForHour;
            initData();
            this.mTouchHelper.invalidateRoot();
        }
        invalidate();
        if (!z || (onValueSelectedListener = this.mListener) == null) {
            return;
        }
        onValueSelectedListener.onValueSelected(0, i, z2);
    }

    public int getCurrentHour() {
        return getHourForDegrees(this.mSelectionDegrees[0], this.mIsOnInnerCircle);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0016, code lost:
        if (r2.mAmOrPm == 1) goto L9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:?, code lost:
        return r3 + 12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0010, code lost:
        if (r3 != 0) goto L9;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int getHourForDegrees(int r3, boolean r4) {
        int r3 = (r3 / 30) % 12;
        if (r2.mIs24HourMode) {
            if (r4 || r3 != 0) {
                if (r4) {
                }
                return r3;
            }
            return 12;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getDegreesForHour(int i) {
        if (this.mIs24HourMode) {
            if (i >= 12) {
                i -= 12;
            }
        } else if (i == 12) {
            i = 0;
        }
        return i * 30;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean getInnerCircleForHour(int i) {
        return this.mIs24HourMode && (i == 0 || i > 12);
    }

    public void setCurrentMinute(int i) {
        setCurrentMinuteInternal(i, true);
    }

    private void setCurrentMinuteInternal(int i, boolean z) {
        OnValueSelectedListener onValueSelectedListener;
        this.mSelectionDegrees[1] = (i % 60) * 6;
        invalidate();
        if (!z || (onValueSelectedListener = this.mListener) == null) {
            return;
        }
        onValueSelectedListener.onValueSelected(1, i, false);
    }

    public int getCurrentMinute() {
        return getMinuteForDegrees(this.mSelectionDegrees[1]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getMinuteForDegrees(int i) {
        return i / 6;
    }

    public void setAmOrPm(int i) {
        this.mAmOrPm = i % 2;
        invalidate();
        this.mTouchHelper.invalidateRoot();
    }

    public int getAmOrPm() {
        return this.mAmOrPm;
    }

    private void showHours(boolean z) {
        if (this.mShowHours) {
            return;
        }
        this.mShowHours = true;
        if (z) {
            startMinutesToHoursAnimation();
        }
        initData();
        invalidate();
        this.mTouchHelper.invalidateRoot();
    }

    private void showMinutes(boolean z) {
        if (this.mShowHours) {
            this.mShowHours = false;
            if (z) {
                startHoursToMinutesAnimation();
            }
            initData();
            invalidate();
            this.mTouchHelper.invalidateRoot();
        }
    }

    private void initHoursAndMinutesText() {
        for (int i = 0; i < 12; i++) {
            String[] strArr = this.mHours12Texts;
            int[] iArr = HOURS_NUMBERS;
            strArr[i] = String.format(TimeModel.NUMBER_FORMAT, Integer.valueOf(iArr[i]));
            this.mInnerHours24Texts[i] = String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(HOURS_NUMBERS_24[i]));
            this.mOuterHours24Texts[i] = String.format(TimeModel.NUMBER_FORMAT, Integer.valueOf(iArr[i]));
            this.mMinutesTexts[i] = String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(MINUTES_NUMBERS[i]));
        }
    }

    private void initData() {
        if (this.mIs24HourMode) {
            this.mOuterTextHours = this.mOuterHours24Texts;
            this.mInnerTextHours = this.mInnerHours24Texts;
        } else {
            String[] strArr = this.mHours12Texts;
            this.mOuterTextHours = strArr;
            this.mInnerTextHours = strArr;
        }
        this.mMinutesText = this.mMinutesTexts;
        this.mAlpha[0].setValue(this.mShowHours ? 255 : 0);
        this.mAlpha[1].setValue(this.mShowHours ? 0 : 255);
    }

    @Override // android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (z) {
            this.mXCenter = getWidth() / 2;
            int height = getHeight() / 2;
            this.mYCenter = height;
            int min = Math.min(this.mXCenter, height);
            this.mCircleRadius = min;
            int[] iArr = this.mTextInset;
            int i5 = this.mSelectorRadius;
            this.mMinDistForInnerNumber = (min - iArr[2]) - i5;
            this.mMaxDistForOuterNumber = (min - iArr[0]) + i5;
            this.mHalfwayDist = min - ((iArr[0] + iArr[2]) / 2);
            calculatePositionsHours();
            calculatePositionsMinutes();
            this.mTouchHelper.invalidateRoot();
        }
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        float f = this.mInputEnabled ? 1.0f : this.mDisabledAlpha;
        drawCircleBackground(canvas);
        drawHours(canvas, f);
        drawMinutes(canvas, f);
        drawCenter(canvas, f);
    }

    private void drawCircleBackground(Canvas canvas) {
        canvas.drawCircle(this.mXCenter, this.mYCenter, this.mCircleRadius, this.mPaintBackground);
    }

    private void drawHours(Canvas canvas, float f) {
        String[] strArr;
        int value = (int) ((this.mAlpha[0].getValue() * f) + 0.5f);
        if (value > 0) {
            drawSelector(canvas, this.mIsOnInnerCircle ? 2 : 0, null, f);
            drawTextElements(canvas, this.mTextSize[0], this.mTypeface, this.mTextColor[0], this.mOuterTextHours, this.mOuterTextX[0], this.mOuterTextY[0], this.mPaint[0], value, !this.mIsOnInnerCircle, this.mSelectionDegrees[0], false);
            if (!this.mIs24HourMode || (strArr = this.mInnerTextHours) == null) {
                return;
            }
            drawTextElements(canvas, this.mTextSize[2], this.mTypeface, this.mTextColor[2], strArr, this.mInnerTextX, this.mInnerTextY, this.mPaint[0], value, this.mIsOnInnerCircle, this.mSelectionDegrees[0], false);
        }
    }

    private void drawMinutes(Canvas canvas, float f) {
        int value = (int) ((this.mAlpha[1].getValue() * f) + 0.5f);
        if (value > 0) {
            drawSelector(canvas, 1, this.mSelectorPath, f);
            canvas.save(2);
            canvas.clipPath(this.mSelectorPath, Region.Op.DIFFERENCE);
            drawTextElements(canvas, this.mTextSize[1], this.mTypeface, this.mTextColor[1], this.mMinutesText, this.mOuterTextX[1], this.mOuterTextY[1], this.mPaint[1], value, false, 0, false);
            canvas.restore();
            canvas.save(2);
            canvas.clipPath(this.mSelectorPath, Region.Op.INTERSECT);
            drawTextElements(canvas, this.mTextSize[1], this.mTypeface, this.mTextColor[1], this.mMinutesText, this.mOuterTextX[1], this.mOuterTextY[1], this.mPaint[1], value, true, this.mSelectionDegrees[1], true);
            canvas.restore();
        }
    }

    private void drawCenter(Canvas canvas, float f) {
        this.mPaintCenter.setAlpha((int) ((f * 255.0f) + 0.5f));
        canvas.drawCircle(this.mXCenter, this.mYCenter, this.mCenterDotRadius, this.mPaintCenter);
    }

    private int getMultipliedAlpha(int i, int i2) {
        return (int) ((Color.alpha(i) * (i2 / 255.0d)) + 0.5d);
    }

    private void drawSelector(Canvas canvas, int i, Path path, float f) {
        int i2 = i % 2;
        int applyAlpha = applyAlpha(this.mSelectorColor, (int) ((this.mAlpha[i2].getValue() * f) + 0.5f));
        int i3 = this.mSelectorRadius;
        int i4 = this.mCircleRadius - this.mTextInset[i];
        double radians = Math.toRadians(this.mSelectionDegrees[i2]);
        float f2 = i4;
        float sin = this.mXCenter + (((float) Math.sin(radians)) * f2);
        float cos = this.mYCenter - (f2 * ((float) Math.cos(radians)));
        Paint paint = this.mPaintSelector[i2][0];
        paint.setColor(applyAlpha);
        float f3 = i3;
        canvas.drawCircle(sin, cos, f3, paint);
        if (path != null) {
            path.reset();
            path.addCircle(sin, cos, f3, Path.Direction.CCW);
        }
        if (this.mSelectionDegrees[i2] % 30 != 0) {
            Paint paint2 = this.mPaintSelector[i2][1];
            paint2.setColor(this.mSelectorDotColor);
            canvas.drawCircle(sin, cos, this.mSelectorDotRadius, paint2);
        }
        double sin2 = Math.sin(radians);
        double cos2 = Math.cos(radians);
        int i5 = i4 - i3;
        int i6 = this.mXCenter;
        int i7 = this.mCenterDotRadius;
        int i8 = i6 + ((int) (i7 * sin2));
        int i9 = this.mYCenter - ((int) (i7 * cos2));
        double d = i5;
        Paint paint3 = this.mPaintSelector[i2][2];
        paint3.setColor(applyAlpha);
        paint3.setStrokeWidth(this.mSelectorStroke);
        canvas.drawLine(this.mXCenter, this.mYCenter, i8 + ((int) (sin2 * d)), i9 - ((int) (d * cos2)), paint3);
    }

    private void calculatePositionsHours() {
        calculatePositions(this.mPaint[0], this.mCircleRadius - this.mTextInset[0], this.mXCenter, this.mYCenter, this.mTextSize[0], this.mOuterTextX[0], this.mOuterTextY[0]);
        if (this.mIs24HourMode) {
            calculatePositions(this.mPaint[0], this.mCircleRadius - this.mTextInset[2], this.mXCenter, this.mYCenter, this.mTextSize[2], this.mInnerTextX, this.mInnerTextY);
        }
    }

    private void calculatePositionsMinutes() {
        calculatePositions(this.mPaint[1], this.mCircleRadius - this.mTextInset[1], this.mXCenter, this.mYCenter, this.mTextSize[1], this.mOuterTextX[1], this.mOuterTextY[1]);
    }

    private static void calculatePositions(Paint paint, float f, float f2, float f3, float f4, float[] fArr, float[] fArr2) {
        paint.setTextSize(f4);
        float descent = f3 - ((paint.descent() + paint.ascent()) / 2.0f);
        for (int i = 0; i < 12; i++) {
            fArr[i] = f2 - (COS_30[i] * f);
            fArr2[i] = descent - (SIN_30[i] * f);
        }
    }

    private void drawTextElements(Canvas canvas, float f, Typeface typeface, ColorStateList colorStateList, String[] strArr, float[] fArr, float[] fArr2, Paint paint, int i, boolean z, int i2, boolean z2) {
        paint.setTextSize(f);
        paint.setTypeface(typeface);
        float f2 = i2 / 30.0f;
        int i3 = (int) f2;
        int ceil = ((int) Math.ceil(f2)) % 12;
        int i4 = 0;
        while (i4 < 12) {
            boolean z3 = i3 == i4 || ceil == i4;
            if (!z2 || z3) {
                int colorForState = colorStateList.getColorForState(SUtils.resolveStateSet(1 | ((z && z3) ? 2 : 0)), 0);
                paint.setColor(colorForState);
                paint.setAlpha(getMultipliedAlpha(colorForState, i));
                canvas.drawText(strArr[i4], fArr[i4], fArr2[i4], paint);
            }
            i4++;
        }
    }

    private static ObjectAnimator getFadeOutAnimator(IntHolder intHolder, int i, int i2, InvalidateUpdateListener invalidateUpdateListener) {
        ObjectAnimator ofInt = ObjectAnimator.ofInt(intHolder, "value", i, i2);
        ofInt.setDuration(500L);
        ofInt.addUpdateListener(invalidateUpdateListener);
        return ofInt;
    }

    private static ObjectAnimator getFadeInAnimator(IntHolder intHolder, int i, int i2, InvalidateUpdateListener invalidateUpdateListener) {
        ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(intHolder, PropertyValuesHolder.ofKeyframe("value", Keyframe.ofInt(0.0f, i), Keyframe.ofInt(0.2f, i), Keyframe.ofInt(1.0f, i2)));
        ofPropertyValuesHolder.setDuration(625L);
        ofPropertyValuesHolder.addUpdateListener(invalidateUpdateListener);
        return ofPropertyValuesHolder;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class InvalidateUpdateListener implements ValueAnimator.AnimatorUpdateListener {
        private InvalidateUpdateListener() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            RadialTimePickerView.this.invalidate();
        }
    }

    private void startHoursToMinutesAnimation() {
        if (this.mHoursToMinutesAnims.size() == 0) {
            this.mHoursToMinutesAnims.add(getFadeOutAnimator(this.mAlpha[0], 255, 0, this.mInvalidateUpdateListener));
            this.mHoursToMinutesAnims.add(getFadeInAnimator(this.mAlpha[1], 0, 255, this.mInvalidateUpdateListener));
        }
        AnimatorSet animatorSet = this.mTransition;
        if (animatorSet != null && animatorSet.isRunning()) {
            this.mTransition.end();
        }
        AnimatorSet animatorSet2 = new AnimatorSet();
        this.mTransition = animatorSet2;
        animatorSet2.playTogether(this.mHoursToMinutesAnims);
        this.mTransition.start();
    }

    private void startMinutesToHoursAnimation() {
        if (this.mMinuteToHoursAnims.size() == 0) {
            this.mMinuteToHoursAnims.add(getFadeOutAnimator(this.mAlpha[1], 255, 0, this.mInvalidateUpdateListener));
            this.mMinuteToHoursAnims.add(getFadeInAnimator(this.mAlpha[0], 0, 255, this.mInvalidateUpdateListener));
        }
        AnimatorSet animatorSet = this.mTransition;
        if (animatorSet != null && animatorSet.isRunning()) {
            this.mTransition.end();
        }
        AnimatorSet animatorSet2 = new AnimatorSet();
        this.mTransition = animatorSet2;
        animatorSet2.playTogether(this.mMinuteToHoursAnims);
        this.mTransition.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getDegreesFromXY(float f, float f2, boolean z) {
        int i;
        int i2;
        if (this.mIs24HourMode && this.mShowHours) {
            i2 = this.mMinDistForInnerNumber;
            i = this.mMaxDistForOuterNumber;
        } else {
            int i3 = this.mCircleRadius - this.mTextInset[!this.mShowHours ? 1 : 0];
            int i4 = this.mSelectorRadius;
            int i5 = i3 - i4;
            i = i3 + i4;
            i2 = i5;
        }
        double d = f - this.mXCenter;
        double d2 = f2 - this.mYCenter;
        double sqrt = Math.sqrt((d * d) + (d2 * d2));
        if (sqrt >= i2) {
            if (!z || sqrt <= i) {
                int degrees = (int) (Math.toDegrees(Math.atan2(d2, d) + 1.5707963267948966d) + 0.5d);
                return degrees < 0 ? degrees + 360 : degrees;
            }
            return -1;
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean getInnerCircleFromXY(float f, float f2) {
        if (this.mIs24HourMode && this.mShowHours) {
            double d = f - this.mXCenter;
            double d2 = f2 - this.mYCenter;
            return Math.sqrt((d * d) + (d2 * d2)) <= ((double) this.mHalfwayDist);
        }
        return false;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z;
        if (this.mInputEnabled) {
            int actionMasked = motionEvent.getActionMasked();
            if (actionMasked == 2 || actionMasked == 1 || actionMasked == 0) {
                boolean z2 = false;
                if (actionMasked == 0) {
                    this.mChangedDuringTouch = false;
                } else if (actionMasked == 1) {
                    if (this.mChangedDuringTouch) {
                        z = true;
                    } else {
                        z = true;
                        z2 = true;
                    }
                    this.mChangedDuringTouch = handleTouchInput(motionEvent.getX(), motionEvent.getY(), z2, z) | this.mChangedDuringTouch;
                }
                z = false;
                this.mChangedDuringTouch = handleTouchInput(motionEvent.getX(), motionEvent.getY(), z2, z) | this.mChangedDuringTouch;
            }
            return true;
        }
        return true;
    }

    private boolean handleTouchInput(float f, float f2, boolean z, boolean z2) {
        int currentMinute;
        boolean z3;
        int i;
        boolean innerCircleFromXY = getInnerCircleFromXY(f, f2);
        int degreesFromXY = getDegreesFromXY(f, f2, false);
        if (degreesFromXY == -1) {
            return false;
        }
        if (this.mShowHours) {
            int snapOnly30s = snapOnly30s(degreesFromXY, 0) % 360;
            z3 = (this.mIsOnInnerCircle == innerCircleFromXY && this.mSelectionDegrees[0] == snapOnly30s) ? false : true;
            this.mIsOnInnerCircle = innerCircleFromXY;
            this.mSelectionDegrees[0] = snapOnly30s;
            currentMinute = getCurrentHour();
            i = 0;
        } else {
            int snapPrefer30s = snapPrefer30s(degreesFromXY) % 360;
            int[] iArr = this.mSelectionDegrees;
            boolean z4 = iArr[1] != snapPrefer30s;
            iArr[1] = snapPrefer30s;
            currentMinute = getCurrentMinute();
            z3 = z4;
            i = 1;
        }
        if (z3 || z || z2) {
            OnValueSelectedListener onValueSelectedListener = this.mListener;
            if (onValueSelectedListener != null) {
                onValueSelectedListener.onValueSelected(i, currentMinute, z2);
            }
            if (z3 || z) {
                SUtils.vibrateForTimePicker(this);
                invalidate();
            }
            return true;
        }
        return false;
    }

    @Override // android.view.View
    public boolean dispatchHoverEvent(MotionEvent motionEvent) {
        return this.mTouchHelper.dispatchHoverEvent(motionEvent) || super.dispatchHoverEvent(motionEvent);
    }

    public void setInputEnabled(boolean z) {
        this.mInputEnabled = z;
        invalidate();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class RadialPickerTouchHelper extends ExploreByTouchHelper {
        private final int MASK_TYPE;
        private final int MASK_VALUE;
        private final int MINUTE_INCREMENT;
        private final int SHIFT_TYPE;
        private final int SHIFT_VALUE;
        private final int TYPE_HOUR;
        private final int TYPE_MINUTE;
        private final Rect mTempRect;

        private int getTypeFromId(int i) {
            return (i >>> 0) & 15;
        }

        private int getValueFromId(int i) {
            return (i >>> 8) & 255;
        }

        private int hour12To24(int i, int i2) {
            if (i != 12) {
                return i2 == 1 ? i + 12 : i;
            } else if (i2 == 0) {
                return 0;
            } else {
                return i;
            }
        }

        private int hour24To12(int i) {
            if (i == 0) {
                return 12;
            }
            return i > 12 ? i - 12 : i;
        }

        private int makeId(int i, int i2) {
            return (i << 0) | (i2 << 8);
        }

        public RadialPickerTouchHelper() {
            super(RadialTimePickerView.this);
            this.mTempRect = new Rect();
            this.TYPE_HOUR = 1;
            this.TYPE_MINUTE = 2;
            this.SHIFT_TYPE = 0;
            this.MASK_TYPE = 15;
            this.SHIFT_VALUE = 8;
            this.MASK_VALUE = 255;
            this.MINUTE_INCREMENT = 5;
        }

        @Override // androidx.customview.widget.ExploreByTouchHelper
        protected void getVisibleVirtualViews(List<Integer> list) {
            if (RadialTimePickerView.this.mShowHours) {
                int i = RadialTimePickerView.this.mIs24HourMode ? 23 : 12;
                for (int i2 = !RadialTimePickerView.this.mIs24HourMode ? 1 : 0; i2 <= i; i2++) {
                    list.add(Integer.valueOf(makeId(1, i2)));
                }
                return;
            }
            int currentMinute = RadialTimePickerView.this.getCurrentMinute();
            for (int i3 = 0; i3 < 60; i3 += 5) {
                list.add(Integer.valueOf(makeId(2, i3)));
                if (currentMinute > i3 && currentMinute < i3 + 5) {
                    list.add(Integer.valueOf(makeId(2, currentMinute)));
                }
            }
        }

        @Override // androidx.customview.widget.ExploreByTouchHelper
        protected void onPopulateEventForVirtualView(int i, AccessibilityEvent accessibilityEvent) {
            accessibilityEvent.setClassName(getClass().getName());
            accessibilityEvent.setContentDescription(getVirtualViewDescription(getTypeFromId(i), getValueFromId(i)));
        }

        @Override // androidx.customview.widget.ExploreByTouchHelper
        protected void onPopulateNodeForVirtualView(int i, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            accessibilityNodeInfoCompat.setClassName(getClass().getName());
            accessibilityNodeInfoCompat.addAction(16);
            int typeFromId = getTypeFromId(i);
            int valueFromId = getValueFromId(i);
            accessibilityNodeInfoCompat.setContentDescription(getVirtualViewDescription(typeFromId, valueFromId));
            getBoundsForVirtualView(i, this.mTempRect);
            accessibilityNodeInfoCompat.setBoundsInParent(this.mTempRect);
            accessibilityNodeInfoCompat.setSelected(isVirtualViewSelected(typeFromId, valueFromId));
            int virtualViewIdAfter = getVirtualViewIdAfter(typeFromId, valueFromId);
            if (virtualViewIdAfter != Integer.MIN_VALUE) {
                accessibilityNodeInfoCompat.setTraversalBefore(RadialTimePickerView.this, virtualViewIdAfter);
            }
        }

        @Override // androidx.customview.widget.ExploreByTouchHelper
        protected boolean onPerformActionForVirtualView(int i, int i2, Bundle bundle) {
            if (i2 == 16) {
                int typeFromId = getTypeFromId(i);
                int valueFromId = getValueFromId(i);
                if (typeFromId == 1) {
                    if (!RadialTimePickerView.this.mIs24HourMode) {
                        valueFromId = hour12To24(valueFromId, RadialTimePickerView.this.mAmOrPm);
                    }
                    RadialTimePickerView.this.setCurrentHour(valueFromId);
                    return true;
                } else if (typeFromId == 2) {
                    RadialTimePickerView.this.setCurrentMinute(valueFromId);
                    return true;
                } else {
                    return false;
                }
            }
            return false;
        }

        @Override // androidx.customview.widget.ExploreByTouchHelper, androidx.core.view.AccessibilityDelegateCompat
        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
            accessibilityNodeInfoCompat.addAction(4096);
            accessibilityNodeInfoCompat.addAction(8192);
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public boolean performAccessibilityAction(View view, int i, Bundle bundle) {
            if (super.performAccessibilityAction(view, i, bundle)) {
                return true;
            }
            if (i == 4096) {
                adjustPicker(1);
                return true;
            } else if (i != 8192) {
                return false;
            } else {
                adjustPicker(-1);
                return true;
            }
        }

        private void adjustPicker(int i) {
            int currentMinute;
            int i2;
            int i3 = 1;
            int i4 = 0;
            if (RadialTimePickerView.this.mShowHours) {
                currentMinute = RadialTimePickerView.this.getCurrentHour();
                if (RadialTimePickerView.this.mIs24HourMode) {
                    i2 = 23;
                } else {
                    currentMinute = hour24To12(currentMinute);
                    i2 = 12;
                    i4 = 1;
                }
            } else {
                i3 = 5;
                currentMinute = RadialTimePickerView.this.getCurrentMinute() / 5;
                i2 = 55;
            }
            int constrain = SUtils.constrain((currentMinute + i) * i3, i4, i2);
            if (RadialTimePickerView.this.mShowHours) {
                RadialTimePickerView.this.setCurrentHour(constrain);
            } else {
                RadialTimePickerView.this.setCurrentMinute(constrain);
            }
        }

        @Override // androidx.customview.widget.ExploreByTouchHelper
        protected int getVirtualViewAt(float f, float f2) {
            int degreesFromXY = RadialTimePickerView.this.getDegreesFromXY(f, f2, true);
            if (degreesFromXY != -1) {
                int snapOnly30s = RadialTimePickerView.snapOnly30s(degreesFromXY, 0) % 360;
                if (RadialTimePickerView.this.mShowHours) {
                    int hourForDegrees = RadialTimePickerView.this.getHourForDegrees(snapOnly30s, RadialTimePickerView.this.getInnerCircleFromXY(f, f2));
                    if (!RadialTimePickerView.this.mIs24HourMode) {
                        hourForDegrees = hour24To12(hourForDegrees);
                    }
                    return makeId(1, hourForDegrees);
                }
                int currentMinute = RadialTimePickerView.this.getCurrentMinute();
                int minuteForDegrees = RadialTimePickerView.this.getMinuteForDegrees(degreesFromXY);
                int minuteForDegrees2 = RadialTimePickerView.this.getMinuteForDegrees(snapOnly30s);
                if (getCircularDiff(currentMinute, minuteForDegrees, 60) >= getCircularDiff(minuteForDegrees2, minuteForDegrees, 60)) {
                    currentMinute = minuteForDegrees2;
                }
                return makeId(2, currentMinute);
            }
            return Integer.MIN_VALUE;
        }

        private int getCircularDiff(int i, int i2, int i3) {
            int abs = Math.abs(i - i2);
            return abs > i3 / 2 ? i3 - abs : abs;
        }

        private int getVirtualViewIdAfter(int i, int i2) {
            if (i == 1) {
                int i3 = i2 + 1;
                if (i3 <= (RadialTimePickerView.this.mIs24HourMode ? 23 : 12)) {
                    return makeId(i, i3);
                }
                return Integer.MIN_VALUE;
            } else if (i == 2) {
                int currentMinute = RadialTimePickerView.this.getCurrentMinute();
                int i4 = (i2 - (i2 % 5)) + 5;
                if (i2 >= currentMinute || i4 <= currentMinute) {
                    if (i4 < 60) {
                        return makeId(i, i4);
                    }
                    return Integer.MIN_VALUE;
                }
                return makeId(i, currentMinute);
            } else {
                return Integer.MIN_VALUE;
            }
        }

        private void getBoundsForVirtualView(int i, Rect rect) {
            float f;
            float f2;
            int i2;
            int typeFromId = getTypeFromId(i);
            int valueFromId = getValueFromId(i);
            float f3 = 0.0f;
            if (typeFromId == 1) {
                if (RadialTimePickerView.this.getInnerCircleForHour(valueFromId)) {
                    f2 = RadialTimePickerView.this.mCircleRadius - RadialTimePickerView.this.mTextInset[2];
                    i2 = RadialTimePickerView.this.mSelectorRadius;
                } else {
                    f2 = RadialTimePickerView.this.mCircleRadius - RadialTimePickerView.this.mTextInset[0];
                    i2 = RadialTimePickerView.this.mSelectorRadius;
                }
                f3 = RadialTimePickerView.this.getDegreesForHour(valueFromId);
                f = i2;
            } else if (typeFromId == 2) {
                float f4 = RadialTimePickerView.this.mCircleRadius - RadialTimePickerView.this.mTextInset[1];
                f3 = RadialTimePickerView.this.getDegreesForMinute(valueFromId);
                f = RadialTimePickerView.this.mSelectorRadius;
                f2 = f4;
            } else {
                f = 0.0f;
                f2 = 0.0f;
            }
            double radians = Math.toRadians(f3);
            float sin = RadialTimePickerView.this.mXCenter + (((float) Math.sin(radians)) * f2);
            float cos = RadialTimePickerView.this.mYCenter - (f2 * ((float) Math.cos(radians)));
            rect.set((int) (sin - f), (int) (cos - f), (int) (sin + f), (int) (cos + f));
        }

        private CharSequence getVirtualViewDescription(int i, int i2) {
            if (i == 1 || i == 2) {
                return Integer.toString(i2);
            }
            return null;
        }

        private boolean isVirtualViewSelected(int i, int i2) {
            if (i == 1) {
                if (RadialTimePickerView.this.getCurrentHour() != i2) {
                    return false;
                }
            } else if (i != 2 || RadialTimePickerView.this.getCurrentMinute() != i2) {
                return false;
            }
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class IntHolder {
        private int mValue;

        public IntHolder(int i) {
            this.mValue = i;
        }

        public void setValue(int i) {
            this.mValue = i;
        }

        public int getValue() {
            return this.mValue;
        }
    }
}
