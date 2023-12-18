package com.github.florent37.singledateandtimepicker;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
/* loaded from: classes.dex */
public class DateHelper {
    public static Calendar getCalendarOfDate(Date date) {
        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        calendar.setTime(date);
        calendar.set(14, 0);
        calendar.set(13, 0);
        return calendar;
    }

    public static int getHour(Date date) {
        return getCalendarOfDate(date).get(10);
    }

    public static int getHourOfDay(Date date) {
        return getCalendarOfDate(date).get(10);
    }

    public static int getHour(Date date, boolean z) {
        if (z) {
            return getHourOfDay(date);
        }
        return getHour(date);
    }

    public static int getMinuteOf(Date date) {
        return getCalendarOfDate(date).get(12);
    }

    public static Date today() {
        return Calendar.getInstance(Locale.getDefault()).getTime();
    }

    public static int getMonth(Date date) {
        return getCalendarOfDate(date).get(2);
    }

    public static int getDay(Date date) {
        return getCalendarOfDate(date).get(5);
    }
}
