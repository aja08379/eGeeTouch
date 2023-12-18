package com.prolificinteractive.materialcalendarview;

import androidx.collection.SparseArrayCompat;
/* loaded from: classes2.dex */
class MonthPagerAdapter extends CalendarPagerAdapter<MonthView> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public MonthPagerAdapter(MaterialCalendarView materialCalendarView) {
        super(materialCalendarView);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.prolificinteractive.materialcalendarview.CalendarPagerAdapter
    public MonthView createView(int i) {
        return new MonthView(this.mcv, getItem(i), this.mcv.getFirstDayOfWeek(), this.showWeekDays);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.prolificinteractive.materialcalendarview.CalendarPagerAdapter
    public int indexOf(MonthView monthView) {
        return getRangeIndex().indexOf(monthView.getMonth());
    }

    @Override // com.prolificinteractive.materialcalendarview.CalendarPagerAdapter
    protected boolean isInstanceOfView(Object obj) {
        return obj instanceof MonthView;
    }

    @Override // com.prolificinteractive.materialcalendarview.CalendarPagerAdapter
    protected DateRangeIndex createRangeIndex(CalendarDay calendarDay, CalendarDay calendarDay2) {
        return new Monthly(calendarDay, calendarDay2);
    }

    /* loaded from: classes2.dex */
    public static class Monthly implements DateRangeIndex {
        private final int count;
        private SparseArrayCompat<CalendarDay> dayCache = new SparseArrayCompat<>();
        private final CalendarDay min;

        public Monthly(CalendarDay calendarDay, CalendarDay calendarDay2) {
            this.min = CalendarDay.from(calendarDay.getYear(), calendarDay.getMonth(), 1);
            this.count = indexOf(CalendarDay.from(calendarDay2.getYear(), calendarDay2.getMonth(), 1)) + 1;
        }

        @Override // com.prolificinteractive.materialcalendarview.DateRangeIndex
        public int getCount() {
            return this.count;
        }

        @Override // com.prolificinteractive.materialcalendarview.DateRangeIndex
        public int indexOf(CalendarDay calendarDay) {
            return ((calendarDay.getYear() - this.min.getYear()) * 12) + (calendarDay.getMonth() - this.min.getMonth());
        }

        @Override // com.prolificinteractive.materialcalendarview.DateRangeIndex
        public CalendarDay getItem(int i) {
            CalendarDay calendarDay = this.dayCache.get(i);
            if (calendarDay != null) {
                return calendarDay;
            }
            int year = this.min.getYear() + (i / 12);
            int month = this.min.getMonth() + (i % 12);
            if (month >= 12) {
                year++;
                month -= 12;
            }
            CalendarDay from = CalendarDay.from(year, month, 1);
            this.dayCache.put(i, from);
            return from;
        }
    }
}
