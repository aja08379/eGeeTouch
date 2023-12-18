package com.prolificinteractive.materialcalendarview;

import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import com.prolificinteractive.materialcalendarview.format.DayFormatter;
import com.prolificinteractive.materialcalendarview.format.WeekDayFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public abstract class CalendarPagerView extends ViewGroup implements View.OnClickListener, View.OnLongClickListener {
    protected static final int DAY_NAMES_ROW = 1;
    protected static final int DEFAULT_DAYS_IN_WEEK = 7;
    protected static final int DEFAULT_MAX_WEEKS = 6;
    private static final Calendar tempWorkingCalendar = CalendarUtils.getInstance();
    private final Collection<DayView> dayViews;
    private final ArrayList<DecoratorResult> decoratorResults;
    private int firstDayOfWeek;
    private CalendarDay firstViewDay;
    private CalendarDay maxDate;
    private MaterialCalendarView mcv;
    private CalendarDay minDate;
    protected int showOtherDates;
    protected boolean showWeekDays;
    private final ArrayList<WeekDayView> weekDayViews;

    protected abstract void buildDayViews(Collection<DayView> collection, Calendar calendar);

    protected abstract int getRows();

    protected abstract boolean isDayEnabled(CalendarDay calendarDay);

    @Override // android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    public CalendarPagerView(MaterialCalendarView materialCalendarView, CalendarDay calendarDay, int i, boolean z) {
        super(materialCalendarView.getContext());
        this.weekDayViews = new ArrayList<>();
        this.decoratorResults = new ArrayList<>();
        this.showOtherDates = 4;
        this.minDate = null;
        this.maxDate = null;
        ArrayList arrayList = new ArrayList();
        this.dayViews = arrayList;
        this.mcv = materialCalendarView;
        this.firstViewDay = calendarDay;
        this.firstDayOfWeek = i;
        this.showWeekDays = z;
        setClipChildren(false);
        setClipToPadding(false);
        if (z) {
            buildWeekDays(resetAndGetWorkingCalendar());
        }
        buildDayViews(arrayList, resetAndGetWorkingCalendar());
    }

    private void buildWeekDays(Calendar calendar) {
        for (int i = 0; i < 7; i++) {
            WeekDayView weekDayView = new WeekDayView(getContext(), CalendarUtils.getDayOfWeek(calendar));
            if (Build.VERSION.SDK_INT >= 16) {
                weekDayView.setImportantForAccessibility(2);
            }
            this.weekDayViews.add(weekDayView);
            addView(weekDayView);
            calendar.add(5, 1);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void addDayView(Collection<DayView> collection, Calendar calendar) {
        DayView dayView = new DayView(getContext(), CalendarDay.from(calendar));
        dayView.setOnClickListener(this);
        dayView.setOnLongClickListener(this);
        collection.add(dayView);
        addView(dayView, new LayoutParams());
        calendar.add(5, 1);
    }

    protected Calendar resetAndGetWorkingCalendar() {
        CalendarDay firstViewDay = getFirstViewDay();
        Calendar calendar = tempWorkingCalendar;
        firstViewDay.copyTo(calendar);
        calendar.setFirstDayOfWeek(getFirstDayOfWeek());
        int firstDayOfWeek = getFirstDayOfWeek() - CalendarUtils.getDayOfWeek(calendar);
        boolean z = true;
        if (!MaterialCalendarView.showOtherMonths(this.showOtherDates) ? firstDayOfWeek <= 0 : firstDayOfWeek < 0) {
            z = false;
        }
        if (z) {
            firstDayOfWeek -= 7;
        }
        calendar.add(5, firstDayOfWeek);
        return calendar;
    }

    protected int getFirstDayOfWeek() {
        return this.firstDayOfWeek;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setDayViewDecorators(List<DecoratorResult> list) {
        this.decoratorResults.clear();
        if (list != null) {
            this.decoratorResults.addAll(list);
        }
        invalidateDecorators();
    }

    public void setWeekDayTextAppearance(int i) {
        Iterator<WeekDayView> it = this.weekDayViews.iterator();
        while (it.hasNext()) {
            it.next().setTextAppearance(getContext(), i);
        }
    }

    public void setDateTextAppearance(int i) {
        for (DayView dayView : this.dayViews) {
            dayView.setTextAppearance(getContext(), i);
        }
    }

    public void setShowOtherDates(int i) {
        this.showOtherDates = i;
        updateUi();
    }

    public void setSelectionEnabled(boolean z) {
        for (DayView dayView : this.dayViews) {
            dayView.setOnClickListener(z ? this : null);
            dayView.setClickable(z);
        }
    }

    public void setSelectionColor(int i) {
        for (DayView dayView : this.dayViews) {
            dayView.setSelectionColor(i);
        }
    }

    public void setWeekDayFormatter(WeekDayFormatter weekDayFormatter) {
        Iterator<WeekDayView> it = this.weekDayViews.iterator();
        while (it.hasNext()) {
            it.next().setWeekDayFormatter(weekDayFormatter);
        }
    }

    public void setDayFormatter(DayFormatter dayFormatter) {
        for (DayView dayView : this.dayViews) {
            dayView.setDayFormatter(dayFormatter);
        }
    }

    public void setDayFormatterContentDescription(DayFormatter dayFormatter) {
        for (DayView dayView : this.dayViews) {
            dayView.setDayFormatterContentDescription(dayFormatter);
        }
    }

    public void setMinimumDate(CalendarDay calendarDay) {
        this.minDate = calendarDay;
        updateUi();
    }

    public void setMaximumDate(CalendarDay calendarDay) {
        this.maxDate = calendarDay;
        updateUi();
    }

    public void setSelectedDates(Collection<CalendarDay> collection) {
        for (DayView dayView : this.dayViews) {
            dayView.setChecked(collection != null && collection.contains(dayView.getDate()));
        }
        postInvalidate();
    }

    protected void updateUi() {
        for (DayView dayView : this.dayViews) {
            CalendarDay date = dayView.getDate();
            dayView.setupSelection(this.showOtherDates, date.isInRange(this.minDate, this.maxDate), isDayEnabled(date));
        }
        postInvalidate();
    }

    protected void invalidateDecorators() {
        DayViewFacade dayViewFacade = new DayViewFacade();
        for (DayView dayView : this.dayViews) {
            dayViewFacade.reset();
            Iterator<DecoratorResult> it = this.decoratorResults.iterator();
            while (it.hasNext()) {
                DecoratorResult next = it.next();
                if (next.decorator.shouldDecorate(dayView.getDate())) {
                    next.result.applyTo(dayViewFacade);
                }
            }
            dayView.applyFacade(dayViewFacade);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view instanceof DayView) {
            this.mcv.onDateClicked((DayView) view);
        }
    }

    @Override // android.view.View.OnLongClickListener
    public boolean onLongClick(View view) {
        if (view instanceof DayView) {
            this.mcv.onDateLongClicked((DayView) view);
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    public LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams();
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int size = View.MeasureSpec.getSize(i);
        int mode = View.MeasureSpec.getMode(i);
        int size2 = View.MeasureSpec.getSize(i2);
        if (View.MeasureSpec.getMode(i2) == 0 || mode == 0) {
            throw new IllegalStateException("CalendarPagerView should never be left to decide it's size");
        }
        int i3 = size / 7;
        int rows = size2 / getRows();
        setMeasuredDimension(size, size2);
        int childCount = getChildCount();
        for (int i4 = 0; i4 < childCount; i4++) {
            getChildAt(i4).measure(View.MeasureSpec.makeMeasureSpec(i3, BasicMeasure.EXACTLY), View.MeasureSpec.makeMeasureSpec(rows, BasicMeasure.EXACTLY));
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        int i5 = 0;
        int i6 = 0;
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = getChildAt(i7);
            int measuredWidth = childAt.getMeasuredWidth() + i5;
            int measuredHeight = childAt.getMeasuredHeight() + i6;
            childAt.layout(i5, i6, measuredWidth, measuredHeight);
            if (i7 % 7 == 6) {
                i5 = 0;
                i6 = measuredHeight;
            } else {
                i5 = measuredWidth;
            }
        }
    }

    @Override // android.view.ViewGroup
    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams();
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new LayoutParams();
    }

    @Override // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(CalendarPagerView.class.getName());
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(CalendarPagerView.class.getName());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public CalendarDay getFirstViewDay() {
        return this.firstViewDay;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes2.dex */
    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        public LayoutParams() {
            super(-2, -2);
        }
    }
}
