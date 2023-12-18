package com.prolificinteractive.materialcalendarview;
/* loaded from: classes2.dex */
public interface DayViewDecorator {
    void decorate(DayViewFacade dayViewFacade);

    boolean shouldDecorate(CalendarDay calendarDay);
}
