package com.prolificinteractive.materialcalendarview;

import java.util.Calendar;
import java.util.Locale;
/* loaded from: classes2.dex */
public class CalendarUtils {
    public static Calendar getInstance() {
        return Calendar.getInstance(Locale.getDefault());
    }

    public static int getYear(Calendar calendar) {
        return calendar.get(1);
    }

    public static int getMonth(Calendar calendar) {
        return calendar.get(2);
    }

    public static int getDay(Calendar calendar) {
        return calendar.get(5);
    }

    public static int getDayOfWeek(Calendar calendar) {
        return calendar.get(7);
    }
}
