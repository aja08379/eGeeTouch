package com.prolificinteractive.materialcalendarview;

import android.view.View;
import android.view.ViewGroup;
import androidx.viewpager.widget.PagerAdapter;
import com.prolificinteractive.materialcalendarview.CalendarPagerView;
import com.prolificinteractive.materialcalendarview.format.DayFormatter;
import com.prolificinteractive.materialcalendarview.format.TitleFormatter;
import com.prolificinteractive.materialcalendarview.format.WeekDayFormatter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public abstract class CalendarPagerAdapter<V extends CalendarPagerView> extends PagerAdapter {
    private final ArrayDeque<V> currentViews;
    private DayFormatter dayFormatter;
    private DayFormatter dayFormatterContentDescription;
    private List<DecoratorResult> decoratorResults;
    private List<DayViewDecorator> decorators;
    protected final MaterialCalendarView mcv;
    private DateRangeIndex rangeIndex;
    private boolean selectionEnabled;
    boolean showWeekDays;
    private final CalendarDay today;
    private TitleFormatter titleFormatter = null;
    private Integer color = null;
    private Integer dateTextAppearance = null;
    private Integer weekDayTextAppearance = null;
    private int showOtherDates = 4;
    private CalendarDay minDate = null;
    private CalendarDay maxDate = null;
    private List<CalendarDay> selectedDates = new ArrayList();
    private WeekDayFormatter weekDayFormatter = WeekDayFormatter.DEFAULT;

    protected abstract DateRangeIndex createRangeIndex(CalendarDay calendarDay, CalendarDay calendarDay2);

    protected abstract V createView(int i);

    protected abstract int indexOf(V v);

    protected abstract boolean isInstanceOfView(Object obj);

    @Override // androidx.viewpager.widget.PagerAdapter
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CalendarPagerAdapter(MaterialCalendarView materialCalendarView) {
        DayFormatter dayFormatter = DayFormatter.DEFAULT;
        this.dayFormatter = dayFormatter;
        this.dayFormatterContentDescription = dayFormatter;
        this.decorators = new ArrayList();
        this.decoratorResults = null;
        this.selectionEnabled = true;
        this.mcv = materialCalendarView;
        this.today = CalendarDay.today();
        ArrayDeque<V> arrayDeque = new ArrayDeque<>();
        this.currentViews = arrayDeque;
        arrayDeque.iterator();
        setRangeDates(null, null);
    }

    public void setDecorators(List<DayViewDecorator> list) {
        this.decorators = list;
        invalidateDecorators();
    }

    public void invalidateDecorators() {
        this.decoratorResults = new ArrayList();
        for (DayViewDecorator dayViewDecorator : this.decorators) {
            DayViewFacade dayViewFacade = new DayViewFacade();
            dayViewDecorator.decorate(dayViewFacade);
            if (dayViewFacade.isDecorated()) {
                this.decoratorResults.add(new DecoratorResult(dayViewDecorator, dayViewFacade));
            }
        }
        Iterator<V> it = this.currentViews.iterator();
        while (it.hasNext()) {
            it.next().setDayViewDecorators(this.decoratorResults);
        }
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        return this.rangeIndex.getCount();
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public CharSequence getPageTitle(int i) {
        TitleFormatter titleFormatter = this.titleFormatter;
        return titleFormatter == null ? "" : titleFormatter.format(getItem(i));
    }

    public CalendarPagerAdapter<?> migrateStateAndReturn(CalendarPagerAdapter<?> calendarPagerAdapter) {
        calendarPagerAdapter.titleFormatter = this.titleFormatter;
        calendarPagerAdapter.color = this.color;
        calendarPagerAdapter.dateTextAppearance = this.dateTextAppearance;
        calendarPagerAdapter.weekDayTextAppearance = this.weekDayTextAppearance;
        calendarPagerAdapter.showOtherDates = this.showOtherDates;
        calendarPagerAdapter.minDate = this.minDate;
        calendarPagerAdapter.maxDate = this.maxDate;
        calendarPagerAdapter.selectedDates = this.selectedDates;
        calendarPagerAdapter.weekDayFormatter = this.weekDayFormatter;
        calendarPagerAdapter.dayFormatter = this.dayFormatter;
        calendarPagerAdapter.dayFormatterContentDescription = this.dayFormatterContentDescription;
        calendarPagerAdapter.decorators = this.decorators;
        calendarPagerAdapter.decoratorResults = this.decoratorResults;
        calendarPagerAdapter.selectionEnabled = this.selectionEnabled;
        return calendarPagerAdapter;
    }

    public int getIndexForDay(CalendarDay calendarDay) {
        if (calendarDay == null) {
            return getCount() / 2;
        }
        CalendarDay calendarDay2 = this.minDate;
        if (calendarDay2 == null || !calendarDay.isBefore(calendarDay2)) {
            CalendarDay calendarDay3 = this.maxDate;
            if (calendarDay3 != null && calendarDay.isAfter(calendarDay3)) {
                return getCount() - 1;
            }
            return this.rangeIndex.indexOf(calendarDay);
        }
        return 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.viewpager.widget.PagerAdapter
    public int getItemPosition(Object obj) {
        int indexOf;
        if (isInstanceOfView(obj)) {
            CalendarPagerView calendarPagerView = (CalendarPagerView) obj;
            if (calendarPagerView.getFirstViewDay() != null && (indexOf = indexOf(calendarPagerView)) >= 0) {
                return indexOf;
            }
            return -2;
        }
        return -2;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, int i) {
        V createView = createView(i);
        createView.setContentDescription(this.mcv.getCalendarContentDescription());
        createView.setAlpha(0.0f);
        createView.setSelectionEnabled(this.selectionEnabled);
        createView.setWeekDayFormatter(this.weekDayFormatter);
        createView.setDayFormatter(this.dayFormatter);
        createView.setDayFormatterContentDescription(this.dayFormatterContentDescription);
        Integer num = this.color;
        if (num != null) {
            createView.setSelectionColor(num.intValue());
        }
        Integer num2 = this.dateTextAppearance;
        if (num2 != null) {
            createView.setDateTextAppearance(num2.intValue());
        }
        Integer num3 = this.weekDayTextAppearance;
        if (num3 != null) {
            createView.setWeekDayTextAppearance(num3.intValue());
        }
        createView.setShowOtherDates(this.showOtherDates);
        createView.setMinimumDate(this.minDate);
        createView.setMaximumDate(this.maxDate);
        createView.setSelectedDates(this.selectedDates);
        viewGroup.addView(createView);
        this.currentViews.add(createView);
        createView.setDayViewDecorators(this.decoratorResults);
        return createView;
    }

    public void setShowWeekDays(boolean z) {
        this.showWeekDays = z;
    }

    public boolean isShowWeekDays() {
        return this.showWeekDays;
    }

    public void setSelectionEnabled(boolean z) {
        this.selectionEnabled = z;
        Iterator<V> it = this.currentViews.iterator();
        while (it.hasNext()) {
            it.next().setSelectionEnabled(this.selectionEnabled);
        }
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        CalendarPagerView calendarPagerView = (CalendarPagerView) obj;
        this.currentViews.remove(calendarPagerView);
        viewGroup.removeView(calendarPagerView);
    }

    public void setTitleFormatter(TitleFormatter titleFormatter) {
        this.titleFormatter = titleFormatter;
    }

    public void setSelectionColor(int i) {
        this.color = Integer.valueOf(i);
        Iterator<V> it = this.currentViews.iterator();
        while (it.hasNext()) {
            it.next().setSelectionColor(i);
        }
    }

    public void setDateTextAppearance(int i) {
        if (i == 0) {
            return;
        }
        this.dateTextAppearance = Integer.valueOf(i);
        Iterator<V> it = this.currentViews.iterator();
        while (it.hasNext()) {
            it.next().setDateTextAppearance(i);
        }
    }

    public void setShowOtherDates(int i) {
        this.showOtherDates = i;
        Iterator<V> it = this.currentViews.iterator();
        while (it.hasNext()) {
            it.next().setShowOtherDates(i);
        }
    }

    public void setWeekDayFormatter(WeekDayFormatter weekDayFormatter) {
        this.weekDayFormatter = weekDayFormatter;
        Iterator<V> it = this.currentViews.iterator();
        while (it.hasNext()) {
            it.next().setWeekDayFormatter(weekDayFormatter);
        }
    }

    public void setDayFormatter(DayFormatter dayFormatter) {
        DayFormatter dayFormatter2 = this.dayFormatterContentDescription;
        if (dayFormatter2 == this.dayFormatter) {
            dayFormatter2 = dayFormatter;
        }
        this.dayFormatterContentDescription = dayFormatter2;
        this.dayFormatter = dayFormatter;
        Iterator<V> it = this.currentViews.iterator();
        while (it.hasNext()) {
            it.next().setDayFormatter(dayFormatter);
        }
    }

    public void setDayFormatterContentDescription(DayFormatter dayFormatter) {
        this.dayFormatterContentDescription = dayFormatter;
        Iterator<V> it = this.currentViews.iterator();
        while (it.hasNext()) {
            it.next().setDayFormatterContentDescription(dayFormatter);
        }
    }

    public int getShowOtherDates() {
        return this.showOtherDates;
    }

    public void setWeekDayTextAppearance(int i) {
        if (i == 0) {
            return;
        }
        this.weekDayTextAppearance = Integer.valueOf(i);
        Iterator<V> it = this.currentViews.iterator();
        while (it.hasNext()) {
            it.next().setWeekDayTextAppearance(i);
        }
    }

    public void setRangeDates(CalendarDay calendarDay, CalendarDay calendarDay2) {
        this.minDate = calendarDay;
        this.maxDate = calendarDay2;
        Iterator<V> it = this.currentViews.iterator();
        while (it.hasNext()) {
            V next = it.next();
            next.setMinimumDate(calendarDay);
            next.setMaximumDate(calendarDay2);
        }
        if (calendarDay == null) {
            calendarDay = CalendarDay.from(this.today.getYear() - 200, this.today.getMonth(), this.today.getDay());
        }
        if (calendarDay2 == null) {
            calendarDay2 = CalendarDay.from(this.today.getYear() + 200, this.today.getMonth(), this.today.getDay());
        }
        this.rangeIndex = createRangeIndex(calendarDay, calendarDay2);
        notifyDataSetChanged();
        invalidateSelectedDates();
    }

    public DateRangeIndex getRangeIndex() {
        return this.rangeIndex;
    }

    public void clearSelections() {
        this.selectedDates.clear();
        invalidateSelectedDates();
    }

    public void setDateSelected(CalendarDay calendarDay, boolean z) {
        if (z) {
            if (this.selectedDates.contains(calendarDay)) {
                return;
            }
            this.selectedDates.add(calendarDay);
            invalidateSelectedDates();
        } else if (this.selectedDates.contains(calendarDay)) {
            this.selectedDates.remove(calendarDay);
            invalidateSelectedDates();
        }
    }

    private void invalidateSelectedDates() {
        validateSelectedDates();
        Iterator<V> it = this.currentViews.iterator();
        while (it.hasNext()) {
            it.next().setSelectedDates(this.selectedDates);
        }
    }

    private void validateSelectedDates() {
        CalendarDay calendarDay;
        int i = 0;
        while (i < this.selectedDates.size()) {
            CalendarDay calendarDay2 = this.selectedDates.get(i);
            CalendarDay calendarDay3 = this.minDate;
            if ((calendarDay3 != null && calendarDay3.isAfter(calendarDay2)) || ((calendarDay = this.maxDate) != null && calendarDay.isBefore(calendarDay2))) {
                this.selectedDates.remove(i);
                this.mcv.onDateUnselected(calendarDay2);
                i--;
            }
            i++;
        }
    }

    public CalendarDay getItem(int i) {
        return this.rangeIndex.getItem(i);
    }

    public List<CalendarDay> getSelectedDates() {
        return Collections.unmodifiableList(this.selectedDates);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int getDateTextAppearance() {
        Integer num = this.dateTextAppearance;
        if (num == null) {
            return 0;
        }
        return num.intValue();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int getWeekDayTextAppearance() {
        Integer num = this.weekDayTextAppearance;
        if (num == null) {
            return 0;
        }
        return num.intValue();
    }
}
