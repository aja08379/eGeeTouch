package com.prolificinteractive.materialcalendarview.format;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
/* loaded from: classes2.dex */
public class DateFormatTitleFormatter implements TitleFormatter {
    private final DateFormat dateFormat;

    public DateFormatTitleFormatter() {
        this.dateFormat = new SimpleDateFormat("LLLL yyyy", Locale.getDefault());
    }

    public DateFormatTitleFormatter(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

    @Override // com.prolificinteractive.materialcalendarview.format.TitleFormatter
    public CharSequence format(CalendarDay calendarDay) {
        return this.dateFormat.format(calendarDay.getDate());
    }
}
