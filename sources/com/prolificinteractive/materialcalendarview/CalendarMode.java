package com.prolificinteractive.materialcalendarview;
/* loaded from: classes2.dex */
public enum CalendarMode {
    MONTHS(6),
    WEEKS(1);
    
    final int visibleWeeksCount;

    CalendarMode(int i) {
        this.visibleWeeksCount = i;
    }
}
