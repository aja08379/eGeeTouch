package com.kunzisoft.switchdatetime.time;

import android.animation.AnimatorSet;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Handler;
import android.text.format.DateUtils;
import android.text.format.Time;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import androidx.core.internal.view.SupportMenu;
import com.egeetouch.egeetouch_manager.TaskManagement;
import com.google.android.material.timepicker.TimeModel;
import com.kunzisoft.switchdatetime.R;
import com.kunzisoft.switchdatetime.Utils;
import com.kunzisoft.switchdatetime.time.widget.TimeAmPmCirclesView;
import com.kunzisoft.switchdatetime.time.widget.TimeCircleView;
import com.kunzisoft.switchdatetime.time.widget.TimeRadialNumbersView;
import com.kunzisoft.switchdatetime.time.widget.TimeRadialSelectorView;
import java.util.ArrayList;
import java.util.Locale;
/* loaded from: classes2.dex */
public class RadialPickerLayout extends FrameLayout implements View.OnTouchListener {
    private static final int AM = 0;
    private static final int AMPM_INDEX = 2;
    private static final int ENABLE_PICKER_INDEX = 3;
    private static final int HOUR_INDEX = 0;
    private static final int HOUR_VALUE_TO_DEGREES_STEP_SIZE = 30;
    private static final int MINUTE_INDEX = 1;
    private static final int MINUTE_VALUE_TO_DEGREES_STEP_SIZE = 6;
    private static final int PM = 1;
    private static final String TAG = "RadialPickerLayout";
    private static final int VISIBLE_DEGREES_STEP_SIZE = 30;
    private int TAP_TIMEOUT;
    private int TOUCH_SLOP;
    private AccessibilityManager mAccessibilityManager;
    private int mCurrentHoursOfDay;
    private int mCurrentItemShowing;
    private int mCurrentMinutes;
    private boolean mDoingMove;
    private boolean mDoingTouch;
    private int mDownDegrees;
    private float mDownX;
    private float mDownY;
    private View mGrayBox;
    private Handler mHandler;
    private boolean mHideAmPm;
    private TimeRadialNumbersView mHourTimeRadialNumbersView;
    private TimeRadialSelectorView mHourTimeRadialSelectorView;
    private boolean mInputEnabled;
    private boolean mIs24HourMode;
    private int mIsTouchingAmOrPm;
    private int mLastValueSelected;
    private long mLastVibrate;
    private OnValueSelectedListener mListener;
    private TimeRadialNumbersView mMinuteTimeRadialNumbersView;
    private TimeRadialSelectorView mMinuteTimeRadialSelectorView;
    private int[] mSnapPrefer30sMap;
    private TimeAmPmCirclesView mTimeAmPmCirclesView;
    private TimeCircleView mTimeCircleView;
    private boolean mTimeInitialized;
    private AnimatorSet mTransition;
    private boolean mVibrate;

    /* loaded from: classes2.dex */
    public interface OnValueSelectedListener {
        void onValueSelected(int i, int i2, boolean z);
    }

    public void tryVibrate() {
    }

    public RadialPickerLayout(Context context) {
        this(context, null, 0);
    }

    public RadialPickerLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RadialPickerLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mVibrate = true;
        this.mIsTouchingAmOrPm = -1;
        this.mHandler = new Handler();
        initialize(context, attributeSet, i);
    }

    public RadialPickerLayout(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mVibrate = true;
        this.mIsTouchingAmOrPm = -1;
        this.mHandler = new Handler();
        initialize(context, attributeSet, i);
    }

    private void initialize(Context context, AttributeSet attributeSet, int i) {
        setOnTouchListener(this);
        this.TOUCH_SLOP = ViewConfiguration.get(context).getScaledTouchSlop();
        this.TAP_TIMEOUT = ViewConfiguration.getTapTimeout();
        this.mDoingMove = false;
        this.mTimeCircleView = new TimeCircleView(context);
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.TimeCircleView);
        this.mTimeCircleView.setCircleColor(obtainStyledAttributes.getColor(R.styleable.TimeCircleView_timeCircleColor, SupportMenu.CATEGORY_MASK));
        this.mTimeCircleView.setCenterColor(obtainStyledAttributes.getColor(R.styleable.TimeCircleView_timeCenterColor, -16777216));
        obtainStyledAttributes.recycle();
        addView(this.mTimeCircleView);
        this.mTimeAmPmCirclesView = new TimeAmPmCirclesView(context);
        TypedArray obtainStyledAttributes2 = getContext().obtainStyledAttributes(attributeSet, R.styleable.TimeAmPmCirclesView);
        this.mTimeAmPmCirclesView.setCircleColor(obtainStyledAttributes2.getColor(R.styleable.TimeAmPmCirclesView_timeAmPmBackgroundColor, -1));
        this.mTimeAmPmCirclesView.setSelectCircleColor(obtainStyledAttributes2.getColor(R.styleable.TimeAmPmCirclesView_timeAmPmSelectBackgroundColor, SupportMenu.CATEGORY_MASK));
        this.mTimeAmPmCirclesView.setInverseSelectedColors(obtainStyledAttributes2.getBoolean(R.styleable.TimeAmPmCirclesView_timeAmPmHighlightSelected, false));
        this.mTimeAmPmCirclesView.setAmPmTextColor(obtainStyledAttributes2.getColor(R.styleable.TimeAmPmCirclesView_timeAmPmTextColor, -16777216));
        obtainStyledAttributes2.recycle();
        addView(this.mTimeAmPmCirclesView);
        this.mHourTimeRadialSelectorView = new TimeRadialSelectorView(context);
        this.mMinuteTimeRadialSelectorView = new TimeRadialSelectorView(context);
        TypedArray obtainStyledAttributes3 = getContext().obtainStyledAttributes(attributeSet, R.styleable.TimeRadialSelectorView);
        this.mHourTimeRadialSelectorView.setSelectorColor(obtainStyledAttributes3.getColor(R.styleable.TimeRadialSelectorView_timeSelectorColor, SupportMenu.CATEGORY_MASK));
        this.mMinuteTimeRadialSelectorView.setSelectorColor(obtainStyledAttributes3.getColor(R.styleable.TimeRadialSelectorView_timeSelectorColor, SupportMenu.CATEGORY_MASK));
        obtainStyledAttributes3.recycle();
        addView(this.mHourTimeRadialSelectorView);
        addView(this.mMinuteTimeRadialSelectorView);
        this.mHourTimeRadialNumbersView = new TimeRadialNumbersView(context);
        this.mMinuteTimeRadialNumbersView = new TimeRadialNumbersView(context);
        TypedArray obtainStyledAttributes4 = getContext().obtainStyledAttributes(attributeSet, R.styleable.TimeRadialNumbersView);
        this.mHourTimeRadialNumbersView.setNumbersColor(obtainStyledAttributes4.getColor(R.styleable.TimeRadialNumbersView_timeCircularNumbersColor, -16777216));
        this.mMinuteTimeRadialNumbersView.setNumbersColor(obtainStyledAttributes4.getColor(R.styleable.TimeRadialNumbersView_timeCircularNumbersColor, -16777216));
        obtainStyledAttributes4.recycle();
        addView(this.mHourTimeRadialNumbersView);
        addView(this.mMinuteTimeRadialNumbersView);
        preparePrefer30sMap();
        this.mLastVibrate = 0L;
        this.mLastValueSelected = -1;
        this.mInputEnabled = true;
        View view = new View(context);
        this.mGrayBox = view;
        view.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        this.mGrayBox.setVisibility(4);
        addView(this.mGrayBox);
        this.mAccessibilityManager = (AccessibilityManager) context.getSystemService("accessibility");
        this.mTimeInitialized = false;
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int i, int i2) {
        int size = View.MeasureSpec.getSize(i);
        int mode = View.MeasureSpec.getMode(i);
        int size2 = View.MeasureSpec.getSize(i2);
        int mode2 = View.MeasureSpec.getMode(i2);
        int min = Math.min(size, size2);
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(min, mode), View.MeasureSpec.makeMeasureSpec(min, mode2));
    }

    public void setOnValueSelectedListener(OnValueSelectedListener onValueSelectedListener) {
        this.mListener = onValueSelectedListener;
    }

    public void initialize(Context context, int i, int i2, boolean z, boolean z2, boolean z3) {
        char c;
        String format;
        if (this.mTimeInitialized) {
            Log.e(TAG, "Time has already been initialized.");
            return;
        }
        this.mIs24HourMode = z;
        this.mTimeAmPmCirclesView.setInverseSelectedColors(z2);
        boolean z4 = Utils.isTouchExplorationEnabled(this.mAccessibilityManager) || this.mIs24HourMode;
        this.mHideAmPm = z4;
        this.mVibrate = z3;
        this.mTimeCircleView.initialize(context, z4);
        this.mTimeCircleView.invalidate();
        if (!this.mHideAmPm) {
            this.mTimeAmPmCirclesView.initialize(context, i < 12 ? 0 : 1);
            this.mTimeAmPmCirclesView.invalidate();
        }
        Resources resources = context.getResources();
        int[] iArr = {12, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        int[] iArr2 = {0, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23};
        int[] iArr3 = {0, 5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55};
        String[] strArr = new String[12];
        String[] strArr2 = new String[12];
        String[] strArr3 = new String[12];
        int i3 = 0;
        for (int i4 = 12; i3 < i4; i4 = 12) {
            if (z) {
                c = 0;
                format = String.format(Locale.getDefault(), TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(iArr2[i3]));
            } else {
                c = 0;
                format = String.format(Locale.getDefault(), TimeModel.NUMBER_FORMAT, Integer.valueOf(iArr[i3]));
            }
            strArr[i3] = format;
            Locale locale = Locale.getDefault();
            Object[] objArr = new Object[1];
            objArr[c] = Integer.valueOf(iArr[i3]);
            strArr2[i3] = String.format(locale, TimeModel.NUMBER_FORMAT, objArr);
            Locale locale2 = Locale.getDefault();
            Object[] objArr2 = new Object[1];
            objArr2[c] = Integer.valueOf(iArr3[i3]);
            strArr3[i3] = String.format(locale2, TimeModel.ZERO_LEADING_NUMBER_FORMAT, objArr2);
            i3++;
        }
        this.mHourTimeRadialNumbersView.initialize(resources, strArr, z ? strArr2 : null, this.mHideAmPm, true);
        this.mHourTimeRadialNumbersView.invalidate();
        this.mMinuteTimeRadialNumbersView.initialize(resources, strArr3, null, this.mHideAmPm, false);
        this.mMinuteTimeRadialNumbersView.invalidate();
        setValueForItem(0, i);
        setValueForItem(1, i2);
        this.mHourTimeRadialSelectorView.initialize(context, this.mHideAmPm, z, true, (i % 12) * 30, isHourInnerCircle(i));
        this.mMinuteTimeRadialSelectorView.initialize(context, this.mHideAmPm, false, false, i2 * 6, false);
        this.mTimeInitialized = true;
    }

    public void setTime(int i, int i2) {
        setItem(0, i);
        setItem(1, i2);
    }

    public void setVibrate(boolean z) {
        this.mVibrate = z;
    }

    private void setItem(int i, int i2) {
        if (i == 0) {
            setValueForItem(0, i2);
            this.mHourTimeRadialSelectorView.setSelection((i2 % 12) * 30, isHourInnerCircle(i2), false);
            this.mHourTimeRadialSelectorView.invalidate();
        } else if (i == 1) {
            setValueForItem(1, i2);
            this.mMinuteTimeRadialSelectorView.setSelection(i2 * 6, false, false);
            this.mMinuteTimeRadialSelectorView.invalidate();
        }
    }

    private boolean isHourInnerCircle(int i) {
        return this.mIs24HourMode && i <= 12 && i != 0;
    }

    public int getHours() {
        return this.mCurrentHoursOfDay;
    }

    public int getMinutes() {
        return this.mCurrentMinutes;
    }

    private int getCurrentlyShowingValue() {
        int currentItemShowing = getCurrentItemShowing();
        if (currentItemShowing == 0) {
            return this.mCurrentHoursOfDay;
        }
        if (currentItemShowing == 1) {
            return this.mCurrentMinutes;
        }
        return -1;
    }

    public int getIsCurrentlyAmOrPm() {
        int i = this.mCurrentHoursOfDay;
        if (i < 12) {
            return 0;
        }
        return i < 24 ? 1 : -1;
    }

    private void setValueForItem(int i, int i2) {
        if (i == 0) {
            this.mCurrentHoursOfDay = i2;
        } else if (i == 1) {
            this.mCurrentMinutes = i2;
        } else if (i == 2) {
            if (i2 == 0) {
                this.mCurrentHoursOfDay %= 12;
            } else if (i2 == 1) {
                this.mCurrentHoursOfDay = (this.mCurrentHoursOfDay % 12) + 12;
            }
        }
    }

    public void setAmOrPm(int i) {
        this.mTimeAmPmCirclesView.setAmOrPm(i);
        this.mTimeAmPmCirclesView.invalidate();
        setValueForItem(2, i);
    }

    private void preparePrefer30sMap() {
        this.mSnapPrefer30sMap = new int[361];
        int i = 1;
        int i2 = 8;
        int i3 = 0;
        for (int i4 = 0; i4 < 361; i4++) {
            this.mSnapPrefer30sMap[i4] = i3;
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

    private int snapPrefer30s(int i) {
        int[] iArr = this.mSnapPrefer30sMap;
        if (iArr == null) {
            return -1;
        }
        return iArr[i];
    }

    private int snapOnly30s(int i, int i2) {
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

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x003b, code lost:
        if (r6 == false) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x003e, code lost:
        if (r5 == 0) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0044, code lost:
        if (r0 == 1) goto L20;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int reselectSelector(int r5, boolean r6, boolean r7, boolean r8) {
        int r5;
        com.kunzisoft.switchdatetime.time.widget.TimeRadialSelectorView r7;
        int r3;
        if (r5 == -1) {
            return -1;
        }
        int r0 = getCurrentItemShowing();
        int r2 = 0;
        if (!r7 && r0 == 1) {
            r5 = snapPrefer30s(r5);
        } else {
            r5 = snapOnly30s(r5, 0);
        }
        if (r0 == 0) {
            r7 = r4.mHourTimeRadialSelectorView;
            r3 = 30;
        } else {
            r7 = r4.mMinuteTimeRadialSelectorView;
            r3 = 6;
        }
        r7.setSelection(r5, r6, r8);
        r7.invalidate();
        if (r0 != 0) {
            if (r5 == 360) {
            }
            r2 = r5;
        } else if (r4.mIs24HourMode) {
            if (r5 != 0 || !r6) {
                if (r5 == 360) {
                }
                r2 = r5;
            }
            r2 = 360;
        }
        int r5 = r2 / r3;
        return (r0 != 0 || !r4.mIs24HourMode || r6 || r2 == 0) ? r5 : r5 + 12;
    }

    private int getDegreesFromCoords(float f, float f2, boolean z, Boolean[] boolArr) {
        int currentItemShowing = getCurrentItemShowing();
        if (currentItemShowing == 0) {
            return this.mHourTimeRadialSelectorView.getDegreesFromCoords(f, f2, z, boolArr);
        }
        if (currentItemShowing == 1) {
            return this.mMinuteTimeRadialSelectorView.getDegreesFromCoords(f, f2, z, boolArr);
        }
        return -1;
    }

    public int getCurrentItemShowing() {
        int i = this.mCurrentItemShowing;
        if (i == 0 || i == 1) {
            return i;
        }
        Log.e(TAG, "Current item showing was unfortunately set to " + this.mCurrentItemShowing);
        return -1;
    }

    public void setCurrentItemShowing(int i, boolean z) {
        if (i != 0 && i != 1) {
            Log.e(TAG, "TimePicker does not support view at index " + i);
            return;
        }
        boolean z2 = z && Build.VERSION.SDK_INT >= 14;
        int currentItemShowing = getCurrentItemShowing();
        this.mCurrentItemShowing = i;
        if (z2 && i != currentItemShowing) {
            ArrayList arrayList = new ArrayList();
            if (i == 0) {
                arrayList.add(this.mHourTimeRadialNumbersView.getReappearAnimator());
                arrayList.add(this.mHourTimeRadialSelectorView.getReappearAnimator());
                arrayList.add(this.mMinuteTimeRadialNumbersView.getDisappearAnimator());
                arrayList.add(this.mMinuteTimeRadialSelectorView.getDisappearAnimator());
            } else if (i == 1) {
                arrayList.add(this.mHourTimeRadialNumbersView.getDisappearAnimator());
                arrayList.add(this.mHourTimeRadialSelectorView.getDisappearAnimator());
                arrayList.add(this.mMinuteTimeRadialNumbersView.getReappearAnimator());
                arrayList.add(this.mMinuteTimeRadialSelectorView.getReappearAnimator());
            }
            AnimatorSet animatorSet = this.mTransition;
            if (animatorSet != null && animatorSet.isRunning()) {
                this.mTransition.end();
            }
            AnimatorSet animatorSet2 = new AnimatorSet();
            this.mTransition = animatorSet2;
            animatorSet2.playTogether(arrayList);
            this.mTransition.start();
            return;
        }
        float f = i == 0 ? 1.0f : 0.0f;
        float f2 = i != 1 ? 0.0f : 1.0f;
        this.mHourTimeRadialNumbersView.setAlpha(f);
        this.mHourTimeRadialSelectorView.setAlpha(f);
        this.mMinuteTimeRadialNumbersView.setAlpha(f2);
        this.mMinuteTimeRadialSelectorView.setAlpha(f2);
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x004b, code lost:
        if (r11 <= r7) goto L31;
     */
    @Override // android.view.View.OnTouchListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onTouch(android.view.View r10, android.view.MotionEvent r11) {
        int r10;
        int r10;
        float r10 = r11.getX();
        float r0 = r11.getY();
        final java.lang.Boolean[] r2 = {false};
        int r11 = r11.getAction();
        if (r11 == 0) {
            if (r9.mInputEnabled) {
                r9.mDownX = r10;
                r9.mDownY = r0;
                r9.mLastValueSelected = -1;
                r9.mDoingMove = false;
                r9.mDoingTouch = true;
                if (!r9.mHideAmPm) {
                    r9.mIsTouchingAmOrPm = r9.mTimeAmPmCirclesView.getIsTouchingAmOrPm(r10, r0);
                } else {
                    r9.mIsTouchingAmOrPm = -1;
                }
                int r11 = r9.mIsTouchingAmOrPm;
                if (r11 == 0 || r11 == 1) {
                    tryVibrate();
                    r9.mDownDegrees = -1;
                    r9.mHandler.postDelayed(new java.lang.Runnable() { // from class: com.kunzisoft.switchdatetime.time.RadialPickerLayout.1
                        {
                        }

                        @Override // java.lang.Runnable
                        public void run() {
                            com.kunzisoft.switchdatetime.time.RadialPickerLayout.this.mTimeAmPmCirclesView.setAmOrPmPressed(com.kunzisoft.switchdatetime.time.RadialPickerLayout.this.mIsTouchingAmOrPm);
                            com.kunzisoft.switchdatetime.time.RadialPickerLayout.this.mTimeAmPmCirclesView.invalidate();
                        }
                    }, r9.TAP_TIMEOUT);
                } else {
                    int r10 = getDegreesFromCoords(r10, r0, com.kunzisoft.switchdatetime.Utils.isTouchExplorationEnabled(r9.mAccessibilityManager), r2);
                    r9.mDownDegrees = r10;
                    if (r10 != -1) {
                        tryVibrate();
                        r9.mHandler.postDelayed(new java.lang.Runnable() { // from class: com.kunzisoft.switchdatetime.time.RadialPickerLayout.2
                            {
                            }

                            @Override // java.lang.Runnable
                            public void run() {
                                com.kunzisoft.switchdatetime.time.RadialPickerLayout.this.mDoingMove = true;
                                com.kunzisoft.switchdatetime.time.RadialPickerLayout r0 = com.kunzisoft.switchdatetime.time.RadialPickerLayout.this;
                                int r0 = r0.reselectSelector(r0.mDownDegrees, r2[0].booleanValue(), false, true);
                                com.kunzisoft.switchdatetime.time.RadialPickerLayout.this.mLastValueSelected = r0;
                                com.kunzisoft.switchdatetime.time.RadialPickerLayout.this.mListener.onValueSelected(com.kunzisoft.switchdatetime.time.RadialPickerLayout.this.getCurrentItemShowing(), r0, false);
                            }
                        }, r9.TAP_TIMEOUT);
                    }
                }
                return true;
            }
            return true;
        }
        if (r11 != 1) {
            if (r11 == 2) {
                if (!r9.mInputEnabled) {
                    android.util.Log.e(com.kunzisoft.switchdatetime.time.RadialPickerLayout.TAG, "Input was disabled, but received ACTION_MOVE.");
                    return true;
                }
                float r11 = java.lang.Math.abs(r0 - r9.mDownY);
                float r5 = java.lang.Math.abs(r10 - r9.mDownX);
                if (!r9.mDoingMove) {
                    int r7 = r9.TOUCH_SLOP;
                    if (r5 <= r7) {
                    }
                }
                int r11 = r9.mIsTouchingAmOrPm;
                if (r11 == 0 || r11 == 1) {
                    r9.mHandler.removeCallbacksAndMessages(null);
                    if (r9.mTimeAmPmCirclesView.getIsTouchingAmOrPm(r10, r0) != r9.mIsTouchingAmOrPm) {
                        r9.mTimeAmPmCirclesView.setAmOrPmPressed(-1);
                        r9.mTimeAmPmCirclesView.invalidate();
                        r9.mIsTouchingAmOrPm = -1;
                    }
                } else if (r9.mDownDegrees != -1) {
                    r9.mDoingMove = true;
                    r9.mHandler.removeCallbacksAndMessages(null);
                    int r10 = getDegreesFromCoords(r10, r0, true, r2);
                    if (r10 != -1 && (r10 = reselectSelector(r10, r2[0].booleanValue(), false, true)) != r9.mLastValueSelected) {
                        tryVibrate();
                        r9.mLastValueSelected = r10;
                        r9.mListener.onValueSelected(getCurrentItemShowing(), r10, false);
                    }
                    return true;
                }
            }
        } else if (!r9.mInputEnabled) {
            android.util.Log.d(com.kunzisoft.switchdatetime.time.RadialPickerLayout.TAG, "Input was disabled, but received ACTION_UP.");
            r9.mListener.onValueSelected(3, 1, false);
            return true;
        } else {
            r9.mHandler.removeCallbacksAndMessages(null);
            r9.mDoingTouch = false;
            int r11 = r9.mIsTouchingAmOrPm;
            if (r11 == 0 || r11 == 1) {
                int r10 = r9.mTimeAmPmCirclesView.getIsTouchingAmOrPm(r10, r0);
                r9.mTimeAmPmCirclesView.setAmOrPmPressed(-1);
                r9.mTimeAmPmCirclesView.invalidate();
                if (r10 == r9.mIsTouchingAmOrPm) {
                    r9.mTimeAmPmCirclesView.setAmOrPm(r10);
                    if (getIsCurrentlyAmOrPm() != r10) {
                        r9.mListener.onValueSelected(2, r9.mIsTouchingAmOrPm, false);
                        setValueForItem(2, r10);
                    }
                }
                r9.mIsTouchingAmOrPm = -1;
            } else {
                if (r9.mDownDegrees != -1 && (r10 = getDegreesFromCoords(r10, r0, r9.mDoingMove, r2)) != -1) {
                    int r10 = reselectSelector(r10, r2[0].booleanValue(), !r9.mDoingMove, false);
                    if (getCurrentItemShowing() == 0 && !r9.mIs24HourMode) {
                        int r11 = getIsCurrentlyAmOrPm();
                        if (r11 == 0 && r10 == 12) {
                            r10 = 0;
                        } else if (r11 == 1 && r10 != 12) {
                            r10 += 12;
                        }
                    }
                    setValueForItem(getCurrentItemShowing(), r10);
                    r9.mListener.onValueSelected(getCurrentItemShowing(), r10, true);
                }
                r9.mDoingMove = false;
                return true;
            }
        }
        return false;
    }

    public boolean trySettingInputEnabled(boolean z) {
        if (!this.mDoingTouch || z) {
            this.mInputEnabled = z;
            this.mGrayBox.setVisibility(z ? 4 : 0);
            return true;
        }
        return false;
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        if (Build.VERSION.SDK_INT >= 21) {
            super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
            accessibilityNodeInfo.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_FORWARD);
            accessibilityNodeInfo.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_BACKWARD);
        } else if (Build.VERSION.SDK_INT >= 16) {
            super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
            accessibilityNodeInfo.addAction(4096);
            accessibilityNodeInfo.addAction(8192);
        }
    }

    @Override // android.view.View
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        if (accessibilityEvent.getEventType() == 32) {
            accessibilityEvent.getText().clear();
            Time time = new Time();
            time.hour = getHours();
            time.minute = getMinutes();
            accessibilityEvent.getText().add(DateUtils.formatDateTime(getContext(), time.normalize(true), this.mIs24HourMode ? TaskManagement.restore_tag2 : 1));
            return true;
        }
        return super.dispatchPopulateAccessibilityEvent(accessibilityEvent);
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0046  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean performAccessibilityAction(int r5, android.os.Bundle r6) {
        int r3;
        int r6;
        int r3;
        if (super.performAccessibilityAction(r5, r6)) {
            return true;
        }
        int r5 = r5 == 4096 ? 1 : r5 == 8192 ? -1 : 0;
        if (r5 != 0) {
            int r6 = getCurrentlyShowingValue();
            int r2 = getCurrentItemShowing();
            if (r2 == 0) {
                r3 = 30;
                r6 %= 12;
            } else {
                r3 = r2 == 1 ? 6 : 0;
            }
            int r5 = snapOnly30s(r6 * r3, r5) / r3;
            if (r2 != 0) {
                r6 = 55;
            } else if (!r4.mIs24HourMode) {
                r6 = 12;
                r3 = 1;
                if (r5 <= r6) {
                    r5 = r3;
                } else if (r5 < r3) {
                    r5 = r6;
                }
                setItem(r2, r5);
                r4.mListener.onValueSelected(r2, r5, false);
                return true;
            } else {
                r6 = 23;
            }
            r3 = 0;
            if (r5 <= r6) {
            }
            setItem(r2, r5);
            r4.mListener.onValueSelected(r2, r5, false);
            return true;
        }
        return false;
    }
}
