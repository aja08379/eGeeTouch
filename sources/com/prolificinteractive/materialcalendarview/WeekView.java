package com.prolificinteractive.materialcalendarview;

import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import com.prolificinteractive.materialcalendarview.CalendarPagerView;
import com.prolificinteractive.materialcalendarview.format.DayFormatter;
import com.prolificinteractive.materialcalendarview.format.WeekDayFormatter;
import java.util.Calendar;
import java.util.Collection;
/* loaded from: classes2.dex */
public class WeekView extends CalendarPagerView {
    @Override // com.prolificinteractive.materialcalendarview.CalendarPagerView
    protected boolean isDayEnabled(CalendarDay calendarDay) {
        return true;
    }

    @Override // com.prolificinteractive.materialcalendarview.CalendarPagerView, android.view.ViewGroup
    public /* bridge */ /* synthetic */ CalendarPagerView.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return super.generateLayoutParams(attributeSet);
    }

    @Override // com.prolificinteractive.materialcalendarview.CalendarPagerView, android.view.View.OnClickListener
    public /* bridge */ /* synthetic */ void onClick(View view) {
        super.onClick(view);
    }

    @Override // com.prolificinteractive.materialcalendarview.CalendarPagerView, android.view.View
    public /* bridge */ /* synthetic */ void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
    }

    @Override // com.prolificinteractive.materialcalendarview.CalendarPagerView, android.view.View
    public /* bridge */ /* synthetic */ void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
    }

    @Override // com.prolificinteractive.materialcalendarview.CalendarPagerView, android.view.View.OnLongClickListener
    public /* bridge */ /* synthetic */ boolean onLongClick(View view) {
        return super.onLongClick(view);
    }

    @Override // com.prolificinteractive.materialcalendarview.CalendarPagerView
    public /* bridge */ /* synthetic */ void setDateTextAppearance(int i) {
        super.setDateTextAppearance(i);
    }

    @Override // com.prolificinteractive.materialcalendarview.CalendarPagerView
    public /* bridge */ /* synthetic */ void setDayFormatter(DayFormatter dayFormatter) {
        super.setDayFormatter(dayFormatter);
    }

    @Override // com.prolificinteractive.materialcalendarview.CalendarPagerView
    public /* bridge */ /* synthetic */ void setDayFormatterContentDescription(DayFormatter dayFormatter) {
        super.setDayFormatterContentDescription(dayFormatter);
    }

    @Override // com.prolificinteractive.materialcalendarview.CalendarPagerView
    public /* bridge */ /* synthetic */ void setMaximumDate(CalendarDay calendarDay) {
        super.setMaximumDate(calendarDay);
    }

    @Override // com.prolificinteractive.materialcalendarview.CalendarPagerView
    public /* bridge */ /* synthetic */ void setMinimumDate(CalendarDay calendarDay) {
        super.setMinimumDate(calendarDay);
    }

    @Override // com.prolificinteractive.materialcalendarview.CalendarPagerView
    public /* bridge */ /* synthetic */ void setSelectedDates(Collection collection) {
        super.setSelectedDates(collection);
    }

    @Override // com.prolificinteractive.materialcalendarview.CalendarPagerView
    public /* bridge */ /* synthetic */ void setSelectionColor(int i) {
        super.setSelectionColor(i);
    }

    @Override // com.prolificinteractive.materialcalendarview.CalendarPagerView
    public /* bridge */ /* synthetic */ void setSelectionEnabled(boolean z) {
        super.setSelectionEnabled(z);
    }

    @Override // com.prolificinteractive.materialcalendarview.CalendarPagerView
    public /* bridge */ /* synthetic */ void setShowOtherDates(int i) {
        super.setShowOtherDates(i);
    }

    @Override // com.prolificinteractive.materialcalendarview.CalendarPagerView
    public /* bridge */ /* synthetic */ void setWeekDayFormatter(WeekDayFormatter weekDayFormatter) {
        super.setWeekDayFormatter(weekDayFormatter);
    }

    @Override // com.prolificinteractive.materialcalendarview.CalendarPagerView
    public /* bridge */ /* synthetic */ void setWeekDayTextAppearance(int i) {
        super.setWeekDayTextAppearance(i);
    }

    @Override // com.prolificinteractive.materialcalendarview.CalendarPagerView, android.view.ViewGroup
    public /* bridge */ /* synthetic */ boolean shouldDelayChildPressedState() {
        return super.shouldDelayChildPressedState();
    }

    public WeekView(MaterialCalendarView materialCalendarView, CalendarDay calendarDay, int i, boolean z) {
        super(materialCalendarView, calendarDay, i, z);
    }

    @Override // com.prolificinteractive.materialcalendarview.CalendarPagerView
    protected void buildDayViews(Collection<DayView> collection, Calendar calendar) {
        for (int i = 0; i < 7; i++) {
            addDayView(collection, calendar);
        }
    }

    @Override // com.prolificinteractive.materialcalendarview.CalendarPagerView
    protected int getRows() {
        return this.showWeekDays ? 2 : 1;
    }
}
