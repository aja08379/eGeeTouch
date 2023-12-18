package com.prolificinteractive.materialcalendarview;

import java.util.Calendar;
import java.util.Collection;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class MonthView extends CalendarPagerView {
    public MonthView(MaterialCalendarView materialCalendarView, CalendarDay calendarDay, int i, boolean z) {
        super(materialCalendarView, calendarDay, i, z);
    }

    @Override // com.prolificinteractive.materialcalendarview.CalendarPagerView
    protected void buildDayViews(Collection<DayView> collection, Calendar calendar) {
        for (int i = 0; i < 6; i++) {
            for (int i2 = 0; i2 < 7; i2++) {
                addDayView(collection, calendar);
            }
        }
    }

    public CalendarDay getMonth() {
        return getFirstViewDay();
    }

    @Override // com.prolificinteractive.materialcalendarview.CalendarPagerView
    protected boolean isDayEnabled(CalendarDay calendarDay) {
        return calendarDay.getMonth() == getFirstViewDay().getMonth();
    }

    @Override // com.prolificinteractive.materialcalendarview.CalendarPagerView
    protected int getRows() {
        return this.showWeekDays ? 7 : 6;
    }
}
