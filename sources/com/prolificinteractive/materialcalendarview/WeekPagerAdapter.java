package com.prolificinteractive.materialcalendarview;

import android.view.View;
import android.view.ViewGroup;
import com.prolificinteractive.materialcalendarview.format.DayFormatter;
import com.prolificinteractive.materialcalendarview.format.TitleFormatter;
import com.prolificinteractive.materialcalendarview.format.WeekDayFormatter;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;
/* loaded from: classes2.dex */
public class WeekPagerAdapter extends CalendarPagerAdapter<WeekView> {
    @Override // com.prolificinteractive.materialcalendarview.CalendarPagerAdapter
    public /* bridge */ /* synthetic */ void clearSelections() {
        super.clearSelections();
    }

    @Override // com.prolificinteractive.materialcalendarview.CalendarPagerAdapter, androidx.viewpager.widget.PagerAdapter
    public /* bridge */ /* synthetic */ void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        super.destroyItem(viewGroup, i, obj);
    }

    @Override // com.prolificinteractive.materialcalendarview.CalendarPagerAdapter, androidx.viewpager.widget.PagerAdapter
    public /* bridge */ /* synthetic */ int getCount() {
        return super.getCount();
    }

    @Override // com.prolificinteractive.materialcalendarview.CalendarPagerAdapter
    public /* bridge */ /* synthetic */ int getIndexForDay(CalendarDay calendarDay) {
        return super.getIndexForDay(calendarDay);
    }

    @Override // com.prolificinteractive.materialcalendarview.CalendarPagerAdapter
    public /* bridge */ /* synthetic */ CalendarDay getItem(int i) {
        return super.getItem(i);
    }

    @Override // com.prolificinteractive.materialcalendarview.CalendarPagerAdapter, androidx.viewpager.widget.PagerAdapter
    public /* bridge */ /* synthetic */ int getItemPosition(Object obj) {
        return super.getItemPosition(obj);
    }

    @Override // com.prolificinteractive.materialcalendarview.CalendarPagerAdapter, androidx.viewpager.widget.PagerAdapter
    public /* bridge */ /* synthetic */ CharSequence getPageTitle(int i) {
        return super.getPageTitle(i);
    }

    @Override // com.prolificinteractive.materialcalendarview.CalendarPagerAdapter
    public /* bridge */ /* synthetic */ DateRangeIndex getRangeIndex() {
        return super.getRangeIndex();
    }

    @Override // com.prolificinteractive.materialcalendarview.CalendarPagerAdapter
    public /* bridge */ /* synthetic */ List getSelectedDates() {
        return super.getSelectedDates();
    }

    @Override // com.prolificinteractive.materialcalendarview.CalendarPagerAdapter
    public /* bridge */ /* synthetic */ int getShowOtherDates() {
        return super.getShowOtherDates();
    }

    @Override // com.prolificinteractive.materialcalendarview.CalendarPagerAdapter, androidx.viewpager.widget.PagerAdapter
    public /* bridge */ /* synthetic */ Object instantiateItem(ViewGroup viewGroup, int i) {
        return super.instantiateItem(viewGroup, i);
    }

    @Override // com.prolificinteractive.materialcalendarview.CalendarPagerAdapter
    public /* bridge */ /* synthetic */ void invalidateDecorators() {
        super.invalidateDecorators();
    }

    @Override // com.prolificinteractive.materialcalendarview.CalendarPagerAdapter
    public /* bridge */ /* synthetic */ boolean isShowWeekDays() {
        return super.isShowWeekDays();
    }

    @Override // com.prolificinteractive.materialcalendarview.CalendarPagerAdapter, androidx.viewpager.widget.PagerAdapter
    public /* bridge */ /* synthetic */ boolean isViewFromObject(View view, Object obj) {
        return super.isViewFromObject(view, obj);
    }

    @Override // com.prolificinteractive.materialcalendarview.CalendarPagerAdapter
    public /* bridge */ /* synthetic */ CalendarPagerAdapter migrateStateAndReturn(CalendarPagerAdapter calendarPagerAdapter) {
        return super.migrateStateAndReturn(calendarPagerAdapter);
    }

    @Override // com.prolificinteractive.materialcalendarview.CalendarPagerAdapter
    public /* bridge */ /* synthetic */ void setDateSelected(CalendarDay calendarDay, boolean z) {
        super.setDateSelected(calendarDay, z);
    }

    @Override // com.prolificinteractive.materialcalendarview.CalendarPagerAdapter
    public /* bridge */ /* synthetic */ void setDateTextAppearance(int i) {
        super.setDateTextAppearance(i);
    }

    @Override // com.prolificinteractive.materialcalendarview.CalendarPagerAdapter
    public /* bridge */ /* synthetic */ void setDayFormatter(DayFormatter dayFormatter) {
        super.setDayFormatter(dayFormatter);
    }

    @Override // com.prolificinteractive.materialcalendarview.CalendarPagerAdapter
    public /* bridge */ /* synthetic */ void setDayFormatterContentDescription(DayFormatter dayFormatter) {
        super.setDayFormatterContentDescription(dayFormatter);
    }

    @Override // com.prolificinteractive.materialcalendarview.CalendarPagerAdapter
    public /* bridge */ /* synthetic */ void setDecorators(List list) {
        super.setDecorators(list);
    }

    @Override // com.prolificinteractive.materialcalendarview.CalendarPagerAdapter
    public /* bridge */ /* synthetic */ void setRangeDates(CalendarDay calendarDay, CalendarDay calendarDay2) {
        super.setRangeDates(calendarDay, calendarDay2);
    }

    @Override // com.prolificinteractive.materialcalendarview.CalendarPagerAdapter
    public /* bridge */ /* synthetic */ void setSelectionColor(int i) {
        super.setSelectionColor(i);
    }

    @Override // com.prolificinteractive.materialcalendarview.CalendarPagerAdapter
    public /* bridge */ /* synthetic */ void setSelectionEnabled(boolean z) {
        super.setSelectionEnabled(z);
    }

    @Override // com.prolificinteractive.materialcalendarview.CalendarPagerAdapter
    public /* bridge */ /* synthetic */ void setShowOtherDates(int i) {
        super.setShowOtherDates(i);
    }

    @Override // com.prolificinteractive.materialcalendarview.CalendarPagerAdapter
    public /* bridge */ /* synthetic */ void setShowWeekDays(boolean z) {
        super.setShowWeekDays(z);
    }

    @Override // com.prolificinteractive.materialcalendarview.CalendarPagerAdapter
    public /* bridge */ /* synthetic */ void setTitleFormatter(TitleFormatter titleFormatter) {
        super.setTitleFormatter(titleFormatter);
    }

    @Override // com.prolificinteractive.materialcalendarview.CalendarPagerAdapter
    public /* bridge */ /* synthetic */ void setWeekDayFormatter(WeekDayFormatter weekDayFormatter) {
        super.setWeekDayFormatter(weekDayFormatter);
    }

    @Override // com.prolificinteractive.materialcalendarview.CalendarPagerAdapter
    public /* bridge */ /* synthetic */ void setWeekDayTextAppearance(int i) {
        super.setWeekDayTextAppearance(i);
    }

    public WeekPagerAdapter(MaterialCalendarView materialCalendarView) {
        super(materialCalendarView);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.prolificinteractive.materialcalendarview.CalendarPagerAdapter
    public WeekView createView(int i) {
        return new WeekView(this.mcv, getItem(i), this.mcv.getFirstDayOfWeek(), this.showWeekDays);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.prolificinteractive.materialcalendarview.CalendarPagerAdapter
    public int indexOf(WeekView weekView) {
        return getRangeIndex().indexOf(weekView.getFirstViewDay());
    }

    @Override // com.prolificinteractive.materialcalendarview.CalendarPagerAdapter
    protected boolean isInstanceOfView(Object obj) {
        return obj instanceof WeekView;
    }

    @Override // com.prolificinteractive.materialcalendarview.CalendarPagerAdapter
    protected DateRangeIndex createRangeIndex(CalendarDay calendarDay, CalendarDay calendarDay2) {
        return new Weekly(calendarDay, calendarDay2, this.mcv.getFirstDayOfWeek());
    }

    /* loaded from: classes2.dex */
    public static class Weekly implements DateRangeIndex {
        private static final int DAYS_IN_WEEK = 7;
        private final int count;
        private final CalendarDay min;

        public Weekly(CalendarDay calendarDay, CalendarDay calendarDay2, int i) {
            CalendarDay firstDayOfWeek = getFirstDayOfWeek(calendarDay, i);
            this.min = firstDayOfWeek;
            this.count = weekNumberDifference(firstDayOfWeek, calendarDay2) + 1;
        }

        @Override // com.prolificinteractive.materialcalendarview.DateRangeIndex
        public int getCount() {
            return this.count;
        }

        @Override // com.prolificinteractive.materialcalendarview.DateRangeIndex
        public int indexOf(CalendarDay calendarDay) {
            return weekNumberDifference(this.min, calendarDay);
        }

        @Override // com.prolificinteractive.materialcalendarview.DateRangeIndex
        public CalendarDay getItem(int i) {
            return CalendarDay.from(this.min.getDate().getTime() + TimeUnit.MILLISECONDS.convert(i * 7, TimeUnit.DAYS));
        }

        private int weekNumberDifference(CalendarDay calendarDay, CalendarDay calendarDay2) {
            return (int) (TimeUnit.DAYS.convert(((calendarDay2.getDate().getTime() - calendarDay.getDate().getTime()) + calendarDay2.getCalendar().get(16)) - calendarDay.getCalendar().get(16), TimeUnit.MILLISECONDS) / 7);
        }

        private CalendarDay getFirstDayOfWeek(CalendarDay calendarDay, int i) {
            Calendar calendar = Calendar.getInstance();
            calendarDay.copyTo(calendar);
            while (calendar.get(7) != i) {
                calendar.add(7, -1);
            }
            return CalendarDay.from(calendar);
        }
    }
}
