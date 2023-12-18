package com.appeaser.sublimepickerlibrary.datepicker;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextPaint;
import android.text.format.DateFormat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.accessibility.AccessibilityEvent;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.customview.widget.ExploreByTouchHelper;
import androidx.exifinterface.media.ExifInterface;
import com.appeaser.sublimepickerlibrary.R;
import com.appeaser.sublimepickerlibrary.common.DateTimePatternHelper;
import com.appeaser.sublimepickerlibrary.datepicker.SelectedDate;
import com.appeaser.sublimepickerlibrary.utilities.SUtils;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class SimpleMonthView extends View {
    private static final int DAYS_IN_WEEK = 7;
    private static final String DAY_OF_WEEK_FORMAT;
    private static final int DEFAULT_SELECTED_DAY = -1;
    private static final String DEFAULT_TITLE_FORMAT = "MMMMy";
    private static final int DEFAULT_WEEK_START = 1;
    private static final int MAX_WEEKS_IN_MONTH = 6;
    private static final String TAG = "SimpleMonthView";
    private final int DRAW_RECT;
    private final int DRAW_RECT_WITH_CURVE_ON_LEFT;
    private final int DRAW_RECT_WITH_CURVE_ON_RIGHT;
    private final ActivatedDays mActivatedDays;
    private final Calendar mCalendar;
    private int mCellWidth;
    private Context mContext;
    private NumberFormat mDayFormatter;
    private int mDayHeight;
    private final Paint mDayHighlightPaint;
    private SimpleDateFormat mDayOfWeekFormatter;
    private int mDayOfWeekHeight;
    private final Calendar mDayOfWeekLabelCalendar;
    private final TextPaint mDayOfWeekPaint;
    private int mDayOfWeekStart;
    private final TextPaint mDayPaint;
    private final Paint mDayRangeSelectorPaint;
    private final Paint mDaySelectorPaint;
    private int mDaySelectorRadius;
    private ColorStateList mDayTextColor;
    private int mDaysInMonth;
    private int mDesiredCellWidth;
    private int mDesiredDayHeight;
    private int mDesiredDayOfWeekHeight;
    private int mDesiredDaySelectorRadius;
    private int mDesiredMonthHeight;
    private int mDownX;
    private int mDownY;
    private int mEnabledDayEnd;
    private int mEnabledDayStart;
    private int mInitialTarget;
    private int mMonth;
    private int mMonthHeight;
    private final TextPaint mMonthPaint;
    private OnDayClickListener mOnDayClickListener;
    private int mPaddedHeight;
    private int mPaddedWidth;
    private float mPaddingRangeIndicator;
    private CheckForTap mPendingCheckForTap;
    private CharSequence mTitle;
    private SimpleDateFormat mTitleFormatter;
    private int mToday;
    private MonthViewTouchHelper mTouchHelper;
    private int mTouchSlopSquared;
    private int mTouchedItem;
    private int mWeekStart;
    private int mYear;

    /* loaded from: classes.dex */
    public interface OnDayClickListener {
        void onDayClick(SimpleMonthView simpleMonthView, Calendar calendar);
    }

    private static boolean isValidDayOfWeek(int i) {
        return i >= 1 && i <= 7;
    }

    private static boolean isValidMonth(int i) {
        return i >= 0 && i <= 11;
    }

    static {
        if (SUtils.isApi_18_OrHigher()) {
            DAY_OF_WEEK_FORMAT = "EEEEE";
        } else {
            DAY_OF_WEEK_FORMAT = ExifInterface.LONGITUDE_EAST;
        }
    }

    public SimpleMonthView(Context context) {
        this(context, null);
    }

    public SimpleMonthView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.spMonthViewStyle);
    }

    public SimpleMonthView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.DRAW_RECT = 0;
        this.DRAW_RECT_WITH_CURVE_ON_LEFT = 1;
        this.DRAW_RECT_WITH_CURVE_ON_RIGHT = 2;
        this.mMonthPaint = new TextPaint();
        this.mDayOfWeekPaint = new TextPaint();
        this.mDayPaint = new TextPaint();
        this.mDaySelectorPaint = new Paint();
        this.mDayHighlightPaint = new Paint();
        this.mDayRangeSelectorPaint = new Paint();
        this.mCalendar = Calendar.getInstance();
        this.mDayOfWeekLabelCalendar = Calendar.getInstance();
        this.mActivatedDays = new ActivatedDays();
        this.mToday = -1;
        this.mWeekStart = 1;
        this.mEnabledDayStart = 1;
        this.mEnabledDayEnd = 31;
        this.mTouchedItem = -1;
        this.mInitialTarget = -1;
        init();
    }

    public SimpleMonthView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.DRAW_RECT = 0;
        this.DRAW_RECT_WITH_CURVE_ON_LEFT = 1;
        this.DRAW_RECT_WITH_CURVE_ON_RIGHT = 2;
        this.mMonthPaint = new TextPaint();
        this.mDayOfWeekPaint = new TextPaint();
        this.mDayPaint = new TextPaint();
        this.mDaySelectorPaint = new Paint();
        this.mDayHighlightPaint = new Paint();
        this.mDayRangeSelectorPaint = new Paint();
        this.mCalendar = Calendar.getInstance();
        this.mDayOfWeekLabelCalendar = Calendar.getInstance();
        this.mActivatedDays = new ActivatedDays();
        this.mToday = -1;
        this.mWeekStart = 1;
        this.mEnabledDayStart = 1;
        this.mEnabledDayEnd = 31;
        this.mTouchedItem = -1;
        this.mInitialTarget = -1;
        init();
    }

    private void init() {
        String bestDateTimePattern;
        Context context = getContext();
        this.mContext = context;
        this.mTouchSlopSquared = ViewConfiguration.get(context).getScaledTouchSlop() * ViewConfiguration.get(this.mContext).getScaledTouchSlop();
        Resources resources = this.mContext.getResources();
        this.mDesiredMonthHeight = resources.getDimensionPixelSize(R.dimen.sp_date_picker_month_height);
        this.mDesiredDayOfWeekHeight = resources.getDimensionPixelSize(R.dimen.sp_date_picker_day_of_week_height);
        this.mDesiredDayHeight = resources.getDimensionPixelSize(R.dimen.sp_date_picker_day_height);
        this.mDesiredCellWidth = resources.getDimensionPixelSize(R.dimen.sp_date_picker_day_width);
        this.mDesiredDaySelectorRadius = resources.getDimensionPixelSize(R.dimen.sp_date_picker_day_selector_radius);
        this.mPaddingRangeIndicator = resources.getDimensionPixelSize(R.dimen.sp_month_view_range_padding);
        MonthViewTouchHelper monthViewTouchHelper = new MonthViewTouchHelper(this);
        this.mTouchHelper = monthViewTouchHelper;
        ViewCompat.setAccessibilityDelegate(this, monthViewTouchHelper);
        ViewCompat.setImportantForAccessibility(this, 1);
        Locale locale = resources.getConfiguration().locale;
        if (SUtils.isApi_18_OrHigher()) {
            bestDateTimePattern = DateFormat.getBestDateTimePattern(locale, DEFAULT_TITLE_FORMAT);
        } else {
            bestDateTimePattern = DateTimePatternHelper.getBestDateTimePattern(locale, 1);
        }
        this.mTitleFormatter = new SimpleDateFormat(bestDateTimePattern, locale);
        this.mDayOfWeekFormatter = new SimpleDateFormat(DAY_OF_WEEK_FORMAT, locale);
        this.mDayFormatter = NumberFormat.getIntegerInstance(locale);
        initPaints(resources);
    }

    private ColorStateList applyTextAppearance(Paint paint, int i) {
        TextView textView = new TextView(this.mContext);
        if (SUtils.isApi_23_OrHigher()) {
            textView.setTextAppearance(i);
        } else {
            textView.setTextAppearance(this.mContext, i);
        }
        paint.setTypeface(textView.getTypeface());
        paint.setTextSize(textView.getTextSize());
        ColorStateList textColors = textView.getTextColors();
        if (textColors != null) {
            paint.setColor(textColors.getColorForState(ENABLED_STATE_SET, 0));
        }
        return textColors;
    }

    public int getMonthHeight() {
        return this.mMonthHeight;
    }

    public int getCellWidth() {
        return this.mCellWidth;
    }

    public void setMonthTextAppearance(int i) {
        applyTextAppearance(this.mMonthPaint, i);
        invalidate();
    }

    public void setDayOfWeekTextAppearance(int i) {
        applyTextAppearance(this.mDayOfWeekPaint, i);
        invalidate();
    }

    public void setDayTextAppearance(int i) {
        ColorStateList applyTextAppearance = applyTextAppearance(this.mDayPaint, i);
        if (applyTextAppearance != null) {
            this.mDayTextColor = applyTextAppearance;
        }
        invalidate();
    }

    public CharSequence getTitle() {
        if (this.mTitle == null) {
            this.mTitle = this.mTitleFormatter.format(this.mCalendar.getTime());
        }
        return this.mTitle;
    }

    private void initPaints(Resources resources) {
        String string = resources.getString(R.string.sp_date_picker_month_typeface);
        String string2 = resources.getString(R.string.sp_date_picker_day_of_week_typeface);
        String string3 = resources.getString(R.string.sp_date_picker_day_typeface);
        int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.sp_date_picker_month_text_size);
        int dimensionPixelSize2 = resources.getDimensionPixelSize(R.dimen.sp_date_picker_day_of_week_text_size);
        int dimensionPixelSize3 = resources.getDimensionPixelSize(R.dimen.sp_date_picker_day_text_size);
        this.mMonthPaint.setAntiAlias(true);
        this.mMonthPaint.setTextSize(dimensionPixelSize);
        this.mMonthPaint.setTypeface(Typeface.create(string, 0));
        this.mMonthPaint.setTextAlign(Paint.Align.CENTER);
        this.mMonthPaint.setStyle(Paint.Style.FILL);
        this.mDayOfWeekPaint.setAntiAlias(true);
        this.mDayOfWeekPaint.setTextSize(dimensionPixelSize2);
        this.mDayOfWeekPaint.setTypeface(Typeface.create(string2, 0));
        this.mDayOfWeekPaint.setTextAlign(Paint.Align.CENTER);
        this.mDayOfWeekPaint.setStyle(Paint.Style.FILL);
        this.mDaySelectorPaint.setAntiAlias(true);
        this.mDaySelectorPaint.setStyle(Paint.Style.FILL);
        this.mDayHighlightPaint.setAntiAlias(true);
        this.mDayHighlightPaint.setStyle(Paint.Style.FILL);
        this.mDayRangeSelectorPaint.setAntiAlias(true);
        this.mDayRangeSelectorPaint.setStyle(Paint.Style.FILL);
        this.mDayPaint.setAntiAlias(true);
        this.mDayPaint.setTextSize(dimensionPixelSize3);
        this.mDayPaint.setTypeface(Typeface.create(string3, 0));
        this.mDayPaint.setTextAlign(Paint.Align.CENTER);
        this.mDayPaint.setStyle(Paint.Style.FILL);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setMonthTextColor(ColorStateList colorStateList) {
        this.mMonthPaint.setColor(colorStateList.getColorForState(ENABLED_STATE_SET, 0));
        invalidate();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setDayOfWeekTextColor(ColorStateList colorStateList) {
        this.mDayOfWeekPaint.setColor(colorStateList.getColorForState(ENABLED_STATE_SET, 0));
        invalidate();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setDayTextColor(ColorStateList colorStateList) {
        this.mDayTextColor = colorStateList;
        invalidate();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setDaySelectorColor(ColorStateList colorStateList) {
        int colorForState = colorStateList.getColorForState(SUtils.resolveStateSet(3), 0);
        this.mDaySelectorPaint.setColor(colorForState);
        this.mDayRangeSelectorPaint.setColor(colorForState);
        this.mDayRangeSelectorPaint.setAlpha(150);
        invalidate();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setDayHighlightColor(ColorStateList colorStateList) {
        this.mDayHighlightPaint.setColor(colorStateList.getColorForState(SUtils.resolveStateSet(5), 0));
        invalidate();
    }

    public void setOnDayClickListener(OnDayClickListener onDayClickListener) {
        this.mOnDayClickListener = onDayClickListener;
    }

    @Override // android.view.View
    public boolean dispatchHoverEvent(MotionEvent motionEvent) {
        return this.mTouchHelper.dispatchHoverEvent(motionEvent) || super.dispatchHoverEvent(motionEvent);
    }

    private boolean isStillAClick(int i, int i2) {
        int i3 = this.mDownX;
        int i4 = (i - i3) * (i - i3);
        int i5 = this.mDownY;
        return i4 + ((i2 - i5) * (i2 - i5)) <= this.mTouchSlopSquared;
    }

    /* loaded from: classes.dex */
    private final class CheckForTap implements Runnable {
        private CheckForTap() {
        }

        @Override // java.lang.Runnable
        public void run() {
            SimpleMonthView simpleMonthView = SimpleMonthView.this;
            simpleMonthView.mTouchedItem = simpleMonthView.getDayAtLocation(simpleMonthView.mDownX, SimpleMonthView.this.mDownY);
            SimpleMonthView.this.invalidate();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x001c, code lost:
        if (r6 != 3) goto L13;
     */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onTouchEvent(android.view.MotionEvent r6) {
        int r0 = (int) (r6.getX() + 0.5f);
        int r1 = (int) (r6.getY() + 0.5f);
        int r6 = r6.getAction();
        if (r6 == 0) {
            r5.mDownX = r0;
            r5.mDownY = r1;
            int r6 = getDayAtLocation(r0, r1);
            r5.mInitialTarget = r6;
            if (r6 < 0) {
                return false;
            }
            if (r5.mPendingCheckForTap == null) {
                r5.mPendingCheckForTap = new com.appeaser.sublimepickerlibrary.datepicker.SimpleMonthView.CheckForTap();
            }
            postDelayed(r5.mPendingCheckForTap, android.view.ViewConfiguration.getTapTimeout());
        } else {
            if (r6 == 1) {
                onDayClicked(r5.mInitialTarget);
            } else if (r6 == 2) {
                if (!isStillAClick(r0, r1)) {
                    com.appeaser.sublimepickerlibrary.datepicker.SimpleMonthView.CheckForTap r6 = r5.mPendingCheckForTap;
                    if (r6 != null) {
                        removeCallbacks(r6);
                    }
                    r5.mInitialTarget = -1;
                    if (r5.mTouchedItem >= 0) {
                        r5.mTouchedItem = -1;
                        invalidate();
                    }
                }
            }
            com.appeaser.sublimepickerlibrary.datepicker.SimpleMonthView.CheckForTap r6 = r5.mPendingCheckForTap;
            if (r6 != null) {
                removeCallbacks(r6);
            }
            r5.mTouchedItem = -1;
            r5.mInitialTarget = -1;
            invalidate();
        }
        return true;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        canvas.translate(paddingLeft, paddingTop);
        drawMonth(canvas);
        drawDaysOfWeek(canvas);
        drawDays(canvas);
        canvas.translate(-paddingLeft, -paddingTop);
    }

    private void drawMonth(Canvas canvas) {
        float ascent = this.mMonthPaint.ascent() + this.mMonthPaint.descent();
        canvas.drawText(getTitle().toString(), this.mPaddedWidth / 2.0f, (this.mMonthHeight - ascent) / 2.0f, this.mMonthPaint);
    }

    private void drawDaysOfWeek(Canvas canvas) {
        TextPaint textPaint = this.mDayOfWeekPaint;
        int i = this.mMonthHeight;
        int i2 = this.mDayOfWeekHeight;
        int i3 = this.mCellWidth;
        float ascent = (textPaint.ascent() + textPaint.descent()) / 2.0f;
        int i4 = i + (i2 / 2);
        for (int i5 = 0; i5 < 7; i5++) {
            int i6 = (i3 * i5) + (i3 / 2);
            if (SUtils.isLayoutRtlCompat(this)) {
                i6 = this.mPaddedWidth - i6;
            }
            canvas.drawText(getDayOfWeekLabel((this.mWeekStart + i5) % 7), i6, i4 - ascent, textPaint);
        }
    }

    private String getDayOfWeekLabel(int i) {
        this.mDayOfWeekLabelCalendar.set(7, i);
        return this.mDayOfWeekFormatter.format(this.mDayOfWeekLabelCalendar.getTime());
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x0085, code lost:
        if (r29.mActivatedDays.isStartOfMonth() != false) goto L39;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void drawDays(android.graphics.Canvas r30) {
        float r11;
        int r28;
        int r19;
        boolean r1;
        boolean r1;
        int r3;
        int r1;
        android.text.TextPaint r8 = r29.mDayPaint;
        int r1 = r29.mMonthHeight + r29.mDayOfWeekHeight;
        float r9 = r29.mDayHeight;
        float r10 = r29.mCellWidth;
        float r11 = 2.0f;
        float r12 = (r8.ascent() + r8.descent()) / 2.0f;
        float r13 = r9 / 2.0f;
        boolean r14 = true;
        float r15 = r1 + r13;
        int r6 = findDayOffset();
        int r5 = 1;
        while (r5 <= r29.mDaysInMonth) {
            float r16 = r10 / r11;
            float r1 = (r6 * r10) + r16;
            if (com.appeaser.sublimepickerlibrary.utilities.SUtils.isLayoutRtlCompat(r29)) {
                r1 = r29.mPaddedWidth - r1;
            }
            float r4 = r1;
            boolean r17 = isDayEnabled(r5);
            boolean r18 = (r29.mActivatedDays.isValid() && r29.mActivatedDays.isActivated(r5)) ? r14 : false;
            if (r29.mActivatedDays.isSelected(r5)) {
                r30.drawCircle(r4, r15, r29.mDaySelectorRadius, r29.mDaySelectorPaint);
                r11 = r4;
                r28 = r5;
                r19 = r6;
                r1 = r17 | true;
            } else if (r18) {
                boolean r20 = r17 | true;
                if (!r29.mActivatedDays.isSingleDay()) {
                    if (!r29.mActivatedDays.isStartingDayOfRange(r5)) {
                        if (!r29.mActivatedDays.isEndingDayOfRange(r5)) {
                            r1 = false;
                        }
                        r1 = true;
                    }
                    r1 = r14;
                }
                float r3 = r29.mPaddingRangeIndicator;
                float r3 = (r10 > (r9 - (r3 * r11)) ? 1 : (r10 == (r9 - (r3 * r11)) ? 0 : -1)) > 0 ? r14 : false ? r13 - r3 : r16;
                if (r1 == r14) {
                    r28 = r5;
                    r19 = r6;
                    int r1 = (int) (r4 - r3);
                    if (r1 % 2 == 1) {
                        r1++;
                    }
                    int r2 = (int) (r4 + r3);
                    if (r2 % 2 == 1) {
                        r2++;
                    }
                    float r22 = r15 - r13;
                    float r3 = r29.mPaddingRangeIndicator;
                    float r23 = r15 + r13;
                    android.graphics.RectF r14 = new android.graphics.RectF(r1, r22 + r3, r2, r23 - r3);
                    r11 = r4;
                    r30.drawArc(r14, 90.0f, 180.0f, true, r29.mDayRangeSelectorPaint);
                    float r2 = r14.centerX();
                    float r3 = r29.mPaddingRangeIndicator;
                    r30.drawRect(new android.graphics.RectF(r2, r22 + r3, r11 + r16, r23 - r3), r29.mDayRangeSelectorPaint);
                } else if (r1) {
                    int r1 = (int) (r4 - r3);
                    if (r1 % 2 == 1) {
                        r1++;
                    }
                    int r2 = (int) (r3 + r4);
                    if (r2 % 2 == 1) {
                        r2++;
                    }
                    float r14 = r15 - r13;
                    float r3 = r29.mPaddingRangeIndicator;
                    float r24 = r15 + r13;
                    android.graphics.RectF r11 = new android.graphics.RectF(r1, r14 + r3, r2, r24 - r3);
                    r28 = r5;
                    r19 = r6;
                    r30.drawArc(r11, 270.0f, 180.0f, true, r29.mDayRangeSelectorPaint);
                    r30.drawRect(new android.graphics.RectF(r4 - r16, r14 + r29.mPaddingRangeIndicator, r11.centerX(), r24 - r29.mPaddingRangeIndicator), r29.mDayRangeSelectorPaint);
                    r11 = r4;
                } else {
                    float r11 = r29.mPaddingRangeIndicator;
                    r30.drawRect(new android.graphics.RectF(r4 - r16, (r15 - r13) + r11, r4 + r16, (r15 + r13) - r11), r29.mDayRangeSelectorPaint);
                    r11 = r4;
                    r28 = r5;
                    r19 = r6;
                }
                r1 = r20;
            } else {
                r11 = r4;
                r28 = r5;
                r19 = r6;
                r1 = r17;
            }
            int r14 = r28;
            int r1 = r1;
            if (r29.mTouchedItem == r14) {
                boolean r1 = (r1 ? 1 : 0) | true;
                r1 = r1;
                if (r17) {
                    r30.drawCircle(r11, r15, r29.mDaySelectorRadius, r29.mDayHighlightPaint);
                    r1 = r1;
                }
            }
            if ((r29.mToday == r14) && !r18) {
                r1 = r29.mDaySelectorPaint.getColor();
                r3 = 0;
            } else {
                r3 = 0;
                r1 = r29.mDayTextColor.getColorForState(com.appeaser.sublimepickerlibrary.utilities.SUtils.resolveStateSet(r1), 0);
            }
            r8.setColor(r1);
            r30.drawText(r29.mDayFormatter.format(r14), r11, r15 - r12, r8);
            r6 = r19 + 1;
            if (r6 == 7) {
                r15 += r9;
                r6 = r3;
            }
            r5 = r14 + 1;
            r11 = 2.0f;
            r14 = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isDayEnabled(int i) {
        return i >= this.mEnabledDayStart && i <= this.mEnabledDayEnd;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isValidDayOfMonth(int i) {
        return i >= 1 && i <= this.mDaysInMonth;
    }

    public void selectAllDays() {
        setSelectedDays(1, SUtils.getDaysInMonth(this.mMonth, this.mYear), SelectedDate.Type.RANGE);
    }

    public void setSelectedDays(int i, int i2, SelectedDate.Type type) {
        this.mActivatedDays.startingDay = i;
        this.mActivatedDays.endingDay = i2;
        this.mActivatedDays.selectedDateType = type;
        this.mTouchHelper.invalidateRoot();
        invalidate();
    }

    public void setFirstDayOfWeek(int i) {
        if (isValidDayOfWeek(i)) {
            this.mWeekStart = i;
        } else {
            this.mWeekStart = this.mCalendar.getFirstDayOfWeek();
        }
        this.mTouchHelper.invalidateRoot();
        invalidate();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setMonthParams(int i, int i2, int i3, int i4, int i5, int i6, int i7, SelectedDate.Type type) {
        if (isValidMonth(i)) {
            this.mMonth = i;
        }
        this.mYear = i2;
        this.mCalendar.set(2, this.mMonth);
        this.mCalendar.set(1, this.mYear);
        this.mCalendar.set(5, 1);
        this.mDayOfWeekStart = this.mCalendar.get(7);
        if (isValidDayOfWeek(i3)) {
            this.mWeekStart = i3;
        } else {
            this.mWeekStart = this.mCalendar.getFirstDayOfWeek();
        }
        Calendar calendar = Calendar.getInstance();
        this.mToday = -1;
        this.mDaysInMonth = SUtils.getDaysInMonth(this.mMonth, this.mYear);
        int i8 = 0;
        while (true) {
            int i9 = this.mDaysInMonth;
            if (i8 < i9) {
                i8++;
                if (sameDay(i8, calendar)) {
                    this.mToday = i8;
                }
            } else {
                int constrain = SUtils.constrain(i4, 1, i9);
                this.mEnabledDayStart = constrain;
                this.mEnabledDayEnd = SUtils.constrain(i5, constrain, this.mDaysInMonth);
                this.mTitle = null;
                this.mActivatedDays.startingDay = i6;
                this.mActivatedDays.endingDay = i7;
                this.mActivatedDays.selectedDateType = type;
                this.mTouchHelper.invalidateRoot();
                return;
            }
        }
    }

    private boolean sameDay(int i, Calendar calendar) {
        return this.mYear == calendar.get(1) && this.mMonth == calendar.get(2) && i == calendar.get(5);
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        setMeasuredDimension(resolveSize((this.mDesiredCellWidth * 7) + (SUtils.isApi_17_OrHigher() ? getPaddingStart() : getPaddingLeft()) + (SUtils.isApi_17_OrHigher() ? getPaddingEnd() : getPaddingRight()), i), resolveSize((this.mDesiredDayHeight * 6) + this.mDesiredDayOfWeekHeight + this.mDesiredMonthHeight + getPaddingTop() + getPaddingBottom(), i2));
    }

    @Override // android.view.View
    public void onRtlPropertiesChanged(int i) {
        super.onRtlPropertiesChanged(i);
        requestLayout();
    }

    @Override // android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (z) {
            int i5 = i3 - i;
            int i6 = i4 - i2;
            int paddingLeft = getPaddingLeft();
            int paddingTop = getPaddingTop();
            int paddingRight = getPaddingRight();
            int paddingBottom = getPaddingBottom();
            int i7 = (i5 - paddingRight) - paddingLeft;
            int i8 = (i6 - paddingBottom) - paddingTop;
            if (i7 == this.mPaddedWidth || i8 == this.mPaddedHeight) {
                return;
            }
            this.mPaddedWidth = i7;
            this.mPaddedHeight = i8;
            float measuredHeight = i8 / ((getMeasuredHeight() - paddingTop) - paddingBottom);
            int i9 = this.mPaddedWidth / 7;
            this.mMonthHeight = (int) (this.mDesiredMonthHeight * measuredHeight);
            this.mDayOfWeekHeight = (int) (this.mDesiredDayOfWeekHeight * measuredHeight);
            this.mDayHeight = (int) (this.mDesiredDayHeight * measuredHeight);
            this.mCellWidth = i9;
            this.mDaySelectorRadius = Math.min(this.mDesiredDaySelectorRadius, Math.min((i9 / 2) + Math.min(paddingLeft, paddingRight), (this.mDayHeight / 2) + paddingBottom));
            this.mTouchHelper.invalidateRoot();
        }
    }

    private int findDayOffset() {
        int i = this.mDayOfWeekStart;
        int i2 = this.mWeekStart;
        int i3 = i - i2;
        return i < i2 ? i3 + 7 : i3;
    }

    public int getDayAtLocation(int i, int i2) {
        int i3;
        int paddingTop;
        int paddingLeft = i - getPaddingLeft();
        if (paddingLeft < 0 || paddingLeft >= this.mPaddedWidth || (paddingTop = i2 - getPaddingTop()) < (i3 = this.mMonthHeight + this.mDayOfWeekHeight) || paddingTop >= this.mPaddedHeight) {
            return -1;
        }
        if (SUtils.isLayoutRtlCompat(this)) {
            paddingLeft = this.mPaddedWidth - paddingLeft;
        }
        int findDayOffset = ((((paddingLeft * 7) / this.mPaddedWidth) + (((paddingTop - i3) / this.mDayHeight) * 7)) + 1) - findDayOffset();
        if (isValidDayOfMonth(findDayOffset)) {
            return findDayOffset;
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean getBoundsForDay(int i, Rect rect) {
        int paddingLeft;
        if (isValidDayOfMonth(i)) {
            int findDayOffset = (i - 1) + findDayOffset();
            int i2 = findDayOffset % 7;
            int i3 = this.mCellWidth;
            if (SUtils.isLayoutRtlCompat(this)) {
                paddingLeft = (getWidth() - getPaddingRight()) - ((i2 + 1) * i3);
            } else {
                paddingLeft = getPaddingLeft() + (i2 * i3);
            }
            int i4 = this.mDayHeight;
            int paddingTop = getPaddingTop() + this.mMonthHeight + this.mDayOfWeekHeight + ((findDayOffset / 7) * i4);
            rect.set(paddingLeft, paddingTop, i3 + paddingLeft, i4 + paddingTop);
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean onDayClicked(int i) {
        if (isValidDayOfMonth(i) && isDayEnabled(i)) {
            if (this.mOnDayClickListener != null) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(this.mYear, this.mMonth, i);
                this.mOnDayClickListener.onDayClick(this, calendar);
            }
            this.mTouchHelper.sendEventForVirtualView(i, 1);
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class MonthViewTouchHelper extends ExploreByTouchHelper {
        private static final String DATE_FORMAT = "dd MMMM yyyy";
        private final Calendar mTempCalendar;
        private final Rect mTempRect;

        public MonthViewTouchHelper(View view) {
            super(view);
            this.mTempRect = new Rect();
            this.mTempCalendar = Calendar.getInstance();
        }

        @Override // androidx.customview.widget.ExploreByTouchHelper
        protected int getVirtualViewAt(float f, float f2) {
            int dayAtLocation = SimpleMonthView.this.getDayAtLocation((int) (f + 0.5f), (int) (f2 + 0.5f));
            if (dayAtLocation != -1) {
                return dayAtLocation;
            }
            return Integer.MIN_VALUE;
        }

        @Override // androidx.customview.widget.ExploreByTouchHelper
        protected void getVisibleVirtualViews(List<Integer> list) {
            for (int i = 1; i <= SimpleMonthView.this.mDaysInMonth; i++) {
                list.add(Integer.valueOf(i));
            }
        }

        @Override // androidx.customview.widget.ExploreByTouchHelper
        protected void onPopulateEventForVirtualView(int i, AccessibilityEvent accessibilityEvent) {
            accessibilityEvent.setContentDescription(getDayDescription(i));
        }

        @Override // androidx.customview.widget.ExploreByTouchHelper
        protected void onPopulateNodeForVirtualView(int i, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            if (!SimpleMonthView.this.getBoundsForDay(i, this.mTempRect)) {
                this.mTempRect.setEmpty();
                accessibilityNodeInfoCompat.setContentDescription("");
                accessibilityNodeInfoCompat.setBoundsInParent(this.mTempRect);
                accessibilityNodeInfoCompat.setVisibleToUser(false);
                return;
            }
            accessibilityNodeInfoCompat.setText(getDayText(i));
            accessibilityNodeInfoCompat.setContentDescription(getDayDescription(i));
            accessibilityNodeInfoCompat.setBoundsInParent(this.mTempRect);
            boolean isDayEnabled = SimpleMonthView.this.isDayEnabled(i);
            if (isDayEnabled) {
                accessibilityNodeInfoCompat.addAction(16);
            }
            accessibilityNodeInfoCompat.setEnabled(isDayEnabled);
            if (SimpleMonthView.this.mActivatedDays.isValid() && SimpleMonthView.this.mActivatedDays.isActivated(i)) {
                accessibilityNodeInfoCompat.setChecked(true);
            }
        }

        @Override // androidx.customview.widget.ExploreByTouchHelper
        protected boolean onPerformActionForVirtualView(int i, int i2, Bundle bundle) {
            if (i2 != 16) {
                return false;
            }
            return SimpleMonthView.this.onDayClicked(i);
        }

        private CharSequence getDayDescription(int i) {
            if (SimpleMonthView.this.isValidDayOfMonth(i)) {
                this.mTempCalendar.set(SimpleMonthView.this.mYear, SimpleMonthView.this.mMonth, i);
                return DateFormat.format(DATE_FORMAT, this.mTempCalendar.getTimeInMillis());
            }
            return "";
        }

        private CharSequence getDayText(int i) {
            if (SimpleMonthView.this.isValidDayOfMonth(i)) {
                return SimpleMonthView.this.mDayFormatter.format(i);
            }
            return null;
        }
    }

    public Calendar composeDate(int i) {
        if (isValidDayOfMonth(i) && isDayEnabled(i)) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(this.mYear, this.mMonth, i);
            return calendar;
        }
        return null;
    }

    /* loaded from: classes.dex */
    public class ActivatedDays {
        public SelectedDate.Type selectedDateType;
        public int startingDay = -1;
        public int endingDay = -1;

        public ActivatedDays() {
        }

        public void reset() {
            this.endingDay = -1;
            this.startingDay = -1;
        }

        public boolean isValid() {
            return (this.startingDay == -1 || this.endingDay == -1) ? false : true;
        }

        public boolean isActivated(int i) {
            return i >= this.startingDay && i <= this.endingDay;
        }

        public boolean isStartingDayOfRange(int i) {
            return i == this.startingDay;
        }

        public boolean isEndingDayOfRange(int i) {
            return i == this.endingDay;
        }

        public boolean isSingleDay() {
            return this.startingDay == this.endingDay;
        }

        public boolean isSelected(int i) {
            return this.selectedDateType == SelectedDate.Type.SINGLE && this.startingDay == i && this.endingDay == i;
        }

        public int getSelectedDay() {
            int i;
            if (this.selectedDateType == SelectedDate.Type.SINGLE && (i = this.startingDay) == this.endingDay) {
                return i;
            }
            return -1;
        }

        public boolean hasSelectedDay() {
            int i;
            return this.selectedDateType == SelectedDate.Type.SINGLE && (i = this.startingDay) == this.endingDay && i != -1;
        }

        public boolean isStartOfMonth() {
            return this.startingDay == 1;
        }
    }
}
