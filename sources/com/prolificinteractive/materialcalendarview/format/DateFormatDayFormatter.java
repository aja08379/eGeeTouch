package com.prolificinteractive.materialcalendarview.format;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
/* loaded from: classes2.dex */
public class DateFormatDayFormatter implements DayFormatter {
    private final DateFormat dateFormat;

    public DateFormatDayFormatter() {
        this.dateFormat = new SimpleDateFormat("d", Locale.getDefault());
    }

    public DateFormatDayFormatter(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

    @Override // com.prolificinteractive.materialcalendarview.format.DayFormatter
    public String format(CalendarDay calendarDay) {
        return this.dateFormat.format(calendarDay.getDate());
    }
}
