package com.prolificinteractive.materialcalendarview.format;

import com.prolificinteractive.materialcalendarview.CalendarDay;
/* loaded from: classes2.dex */
public interface DayFormatter {
    public static final DayFormatter DEFAULT = new DateFormatDayFormatter();

    String format(CalendarDay calendarDay);
}
