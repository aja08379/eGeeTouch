package com.prolificinteractive.materialcalendarview.format;

import com.prolificinteractive.materialcalendarview.CalendarUtils;
import java.util.Calendar;
import java.util.Locale;
/* loaded from: classes2.dex */
public class CalendarWeekDayFormatter implements WeekDayFormatter {
    private final Calendar calendar;

    public CalendarWeekDayFormatter(Calendar calendar) {
        calendar.get(7);
        this.calendar = calendar;
    }

    public CalendarWeekDayFormatter() {
        this(CalendarUtils.getInstance());
    }

    @Override // com.prolificinteractive.materialcalendarview.format.WeekDayFormatter
    public CharSequence format(int i) {
        this.calendar.set(7, i);
        return this.calendar.getDisplayName(7, 1, Locale.getDefault());
    }
}
