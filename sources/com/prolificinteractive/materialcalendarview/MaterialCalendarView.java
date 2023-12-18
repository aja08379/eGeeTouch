package com.prolificinteractive.materialcalendarview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import androidx.viewpager.widget.ViewPager;
import com.prolificinteractive.materialcalendarview.format.ArrayWeekDayFormatter;
import com.prolificinteractive.materialcalendarview.format.DateFormatTitleFormatter;
import com.prolificinteractive.materialcalendarview.format.DayFormatter;
import com.prolificinteractive.materialcalendarview.format.MonthArrayTitleFormatter;
import com.prolificinteractive.materialcalendarview.format.TitleFormatter;
import com.prolificinteractive.materialcalendarview.format.WeekDayFormatter;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
/* loaded from: classes2.dex */
public class MaterialCalendarView extends ViewGroup {
    private static final int DAY_NAMES_ROW = 1;
    private static final int DEFAULT_DAYS_IN_WEEK = 7;
    private static final int DEFAULT_MAX_WEEKS = 6;
    public static final int DEFAULT_TILE_SIZE_DP = 44;
    private static final TitleFormatter DEFAULT_TITLE_FORMATTER = new DateFormatTitleFormatter();
    public static final int HORIZONTAL = 1;
    public static final int INVALID_TILE_DIMENSION = -10;
    public static final int SELECTION_MODE_MULTIPLE = 2;
    public static final int SELECTION_MODE_NONE = 0;
    public static final int SELECTION_MODE_RANGE = 3;
    public static final int SELECTION_MODE_SINGLE = 1;
    public static final int SHOW_ALL = 7;
    public static final int SHOW_DECORATED_DISABLED = 4;
    public static final int SHOW_DEFAULTS = 4;
    public static final int SHOW_NONE = 0;
    public static final int SHOW_OTHER_MONTHS = 1;
    public static final int SHOW_OUT_OF_RANGE = 2;
    public static final int VERTICAL = 0;
    private int accentColor;
    private CalendarPagerAdapter<?> adapter;
    private boolean allowClickDaysOutsideCurrentMonth;
    private int arrowColor;
    private final DirectionButton buttonFuture;
    private final DirectionButton buttonPast;
    CharSequence calendarContentDescription;
    private CalendarMode calendarMode;
    private CalendarDay currentMonth;
    private final ArrayList<DayViewDecorator> dayViewDecorators;
    private int firstDayOfWeek;
    private Drawable leftArrowMask;
    private OnDateSelectedListener listener;
    private OnDateLongClickListener longClickListener;
    private boolean mDynamicHeightEnabled;
    private CalendarDay maxDate;
    private CalendarDay minDate;
    private OnMonthChangedListener monthListener;
    private final View.OnClickListener onClickListener;
    private final ViewPager.OnPageChangeListener pageChangeListener;
    private final CalendarPager pager;
    private OnRangeSelectedListener rangeListener;
    private Drawable rightArrowMask;
    private int selectionMode;
    private boolean showWeekDays;
    private State state;
    private int tileHeight;
    private int tileWidth;
    private final TextView title;
    private final TitleChanger titleChanger;
    private LinearLayout topbar;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface SelectionMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface ShowOtherDates {
    }

    public static boolean showDecoratedDisabled(int i) {
        return (i & 4) != 0;
    }

    public static boolean showOtherMonths(int i) {
        return (i & 1) != 0;
    }

    public static boolean showOutOfRange(int i) {
        return (i & 2) != 0;
    }

    @Override // android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    public MaterialCalendarView(Context context) {
        this(context, null);
    }

    public MaterialCalendarView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.dayViewDecorators = new ArrayList<>();
        View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.prolificinteractive.materialcalendarview.MaterialCalendarView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (view == MaterialCalendarView.this.buttonFuture) {
                    MaterialCalendarView.this.pager.setCurrentItem(MaterialCalendarView.this.pager.getCurrentItem() + 1, true);
                } else if (view == MaterialCalendarView.this.buttonPast) {
                    MaterialCalendarView.this.pager.setCurrentItem(MaterialCalendarView.this.pager.getCurrentItem() - 1, true);
                }
            }
        };
        this.onClickListener = onClickListener;
        ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() { // from class: com.prolificinteractive.materialcalendarview.MaterialCalendarView.2
            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrolled(int i, float f, int i2) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageSelected(int i) {
                MaterialCalendarView.this.titleChanger.setPreviousMonth(MaterialCalendarView.this.currentMonth);
                MaterialCalendarView materialCalendarView = MaterialCalendarView.this;
                materialCalendarView.currentMonth = materialCalendarView.adapter.getItem(i);
                MaterialCalendarView.this.updateUi();
                MaterialCalendarView materialCalendarView2 = MaterialCalendarView.this;
                materialCalendarView2.dispatchOnMonthChanged(materialCalendarView2.currentMonth);
            }
        };
        this.pageChangeListener = onPageChangeListener;
        this.minDate = null;
        this.maxDate = null;
        this.accentColor = 0;
        this.arrowColor = -16777216;
        this.tileHeight = -10;
        this.tileWidth = -10;
        this.selectionMode = 1;
        this.allowClickDaysOutsideCurrentMonth = true;
        if (Build.VERSION.SDK_INT >= 19) {
            setClipToPadding(false);
            setClipChildren(false);
        } else {
            setClipChildren(true);
            setClipToPadding(true);
        }
        DirectionButton directionButton = new DirectionButton(getContext());
        this.buttonPast = directionButton;
        directionButton.setContentDescription(getContext().getString(R.string.previous));
        AppCompatTextView appCompatTextView = new AppCompatTextView(getContext());
        this.title = appCompatTextView;
        DirectionButton directionButton2 = new DirectionButton(getContext());
        this.buttonFuture = directionButton2;
        directionButton2.setContentDescription(getContext().getString(R.string.next));
        CalendarPager calendarPager = new CalendarPager(getContext());
        this.pager = calendarPager;
        directionButton.setOnClickListener(onClickListener);
        directionButton2.setOnClickListener(onClickListener);
        TitleChanger titleChanger = new TitleChanger(appCompatTextView);
        this.titleChanger = titleChanger;
        titleChanger.setTitleFormatter(DEFAULT_TITLE_FORMATTER);
        calendarPager.setOnPageChangeListener(onPageChangeListener);
        calendarPager.setPageTransformer(false, new ViewPager.PageTransformer() { // from class: com.prolificinteractive.materialcalendarview.MaterialCalendarView.3
            @Override // androidx.viewpager.widget.ViewPager.PageTransformer
            public void transformPage(View view, float f) {
                view.setAlpha((float) Math.sqrt(1.0f - Math.abs(f)));
            }
        });
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.MaterialCalendarView, 0, 0);
        try {
            try {
                int integer = obtainStyledAttributes.getInteger(R.styleable.MaterialCalendarView_mcv_calendarMode, 0);
                this.firstDayOfWeek = obtainStyledAttributes.getInteger(R.styleable.MaterialCalendarView_mcv_firstDayOfWeek, -1);
                titleChanger.setOrientation(obtainStyledAttributes.getInteger(R.styleable.MaterialCalendarView_mcv_titleAnimationOrientation, 0));
                if (this.firstDayOfWeek < 0) {
                    this.firstDayOfWeek = CalendarUtils.getInstance().getFirstDayOfWeek();
                }
                this.showWeekDays = obtainStyledAttributes.getBoolean(R.styleable.MaterialCalendarView_mcv_showWeekDays, true);
                newState().setFirstDayOfWeek(this.firstDayOfWeek).setCalendarDisplayMode(CalendarMode.values()[integer]).setShowWeekDays(this.showWeekDays).commit();
                setSelectionMode(obtainStyledAttributes.getInteger(R.styleable.MaterialCalendarView_mcv_selectionMode, 1));
                int layoutDimension = obtainStyledAttributes.getLayoutDimension(R.styleable.MaterialCalendarView_mcv_tileSize, -10);
                if (layoutDimension > -10) {
                    setTileSize(layoutDimension);
                }
                int layoutDimension2 = obtainStyledAttributes.getLayoutDimension(R.styleable.MaterialCalendarView_mcv_tileWidth, -10);
                if (layoutDimension2 > -10) {
                    setTileWidth(layoutDimension2);
                }
                int layoutDimension3 = obtainStyledAttributes.getLayoutDimension(R.styleable.MaterialCalendarView_mcv_tileHeight, -10);
                if (layoutDimension3 > -10) {
                    setTileHeight(layoutDimension3);
                }
                setArrowColor(obtainStyledAttributes.getColor(R.styleable.MaterialCalendarView_mcv_arrowColor, -16777216));
                Drawable drawable = obtainStyledAttributes.getDrawable(R.styleable.MaterialCalendarView_mcv_leftArrowMask);
                setLeftArrowMask(drawable == null ? getResources().getDrawable(R.drawable.mcv_action_previous) : drawable);
                Drawable drawable2 = obtainStyledAttributes.getDrawable(R.styleable.MaterialCalendarView_mcv_rightArrowMask);
                setRightArrowMask(drawable2 == null ? getResources().getDrawable(R.drawable.mcv_action_next) : drawable2);
                setSelectionColor(obtainStyledAttributes.getColor(R.styleable.MaterialCalendarView_mcv_selectionColor, getThemeAccentColor(context)));
                CharSequence[] textArray = obtainStyledAttributes.getTextArray(R.styleable.MaterialCalendarView_mcv_weekDayLabels);
                if (textArray != null) {
                    setWeekDayFormatter(new ArrayWeekDayFormatter(textArray));
                }
                CharSequence[] textArray2 = obtainStyledAttributes.getTextArray(R.styleable.MaterialCalendarView_mcv_monthLabels);
                if (textArray2 != null) {
                    setTitleFormatter(new MonthArrayTitleFormatter(textArray2));
                }
                setHeaderTextAppearance(obtainStyledAttributes.getResourceId(R.styleable.MaterialCalendarView_mcv_headerTextAppearance, R.style.TextAppearance_MaterialCalendarWidget_Header));
                setWeekDayTextAppearance(obtainStyledAttributes.getResourceId(R.styleable.MaterialCalendarView_mcv_weekDayTextAppearance, R.style.TextAppearance_MaterialCalendarWidget_WeekDay));
                setDateTextAppearance(obtainStyledAttributes.getResourceId(R.styleable.MaterialCalendarView_mcv_dateTextAppearance, R.style.TextAppearance_MaterialCalendarWidget_Date));
                setShowOtherDates(obtainStyledAttributes.getInteger(R.styleable.MaterialCalendarView_mcv_showOtherDates, 4));
                setAllowClickDaysOutsideCurrentMonth(obtainStyledAttributes.getBoolean(R.styleable.MaterialCalendarView_mcv_allowClickDaysOutsideCurrentMonth, true));
            } catch (Exception e) {
                e.printStackTrace();
            }
            obtainStyledAttributes.recycle();
            this.adapter.setTitleFormatter(DEFAULT_TITLE_FORMATTER);
            setupChildren();
            CalendarDay calendarDay = CalendarDay.today();
            this.currentMonth = calendarDay;
            setCurrentDate(calendarDay);
            if (isInEditMode()) {
                removeView(this.pager);
                MonthView monthView = new MonthView(this, this.currentMonth, getFirstDayOfWeek(), true);
                monthView.setSelectionColor(getSelectionColor());
                monthView.setDateTextAppearance(this.adapter.getDateTextAppearance());
                monthView.setWeekDayTextAppearance(this.adapter.getWeekDayTextAppearance());
                monthView.setShowOtherDates(getShowOtherDates());
                addView(monthView, new LayoutParams(this.calendarMode.visibleWeeksCount + 1));
            }
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
    }

    private void setupChildren() {
        LinearLayout linearLayout = new LinearLayout(getContext());
        this.topbar = linearLayout;
        linearLayout.setOrientation(0);
        this.topbar.setClipChildren(false);
        this.topbar.setClipToPadding(false);
        addView(this.topbar, new LayoutParams(1));
        this.buttonPast.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        this.topbar.addView(this.buttonPast, new LinearLayout.LayoutParams(0, -1, 1.0f));
        this.title.setGravity(17);
        this.topbar.addView(this.title, new LinearLayout.LayoutParams(0, -1, 5.0f));
        this.buttonFuture.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        this.topbar.addView(this.buttonFuture, new LinearLayout.LayoutParams(0, -1, 1.0f));
        this.pager.setId(R.id.mcv_pager);
        this.pager.setOffscreenPageLimit(1);
        addView(this.pager, new LayoutParams(this.showWeekDays ? this.calendarMode.visibleWeeksCount + 1 : this.calendarMode.visibleWeeksCount));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateUi() {
        this.titleChanger.change(this.currentMonth);
        this.buttonPast.setEnabled(canGoBack());
        this.buttonFuture.setEnabled(canGoForward());
    }

    public void setSelectionMode(int i) {
        int i2 = this.selectionMode;
        this.selectionMode = i;
        if (i != 1) {
            if (i != 2) {
                if (i == 3) {
                    clearSelection();
                } else {
                    this.selectionMode = 0;
                    if (i2 != 0) {
                        clearSelection();
                    }
                }
            }
        } else if ((i2 == 2 || i2 == 3) && !getSelectedDates().isEmpty()) {
            setSelectedDate(getSelectedDate());
        }
        this.adapter.setSelectionEnabled(this.selectionMode != 0);
    }

    public void goToPrevious() {
        if (canGoBack()) {
            CalendarPager calendarPager = this.pager;
            calendarPager.setCurrentItem(calendarPager.getCurrentItem() - 1, true);
        }
    }

    public void goToNext() {
        if (canGoForward()) {
            CalendarPager calendarPager = this.pager;
            calendarPager.setCurrentItem(calendarPager.getCurrentItem() + 1, true);
        }
    }

    public int getSelectionMode() {
        return this.selectionMode;
    }

    @Deprecated
    public int getTileSize() {
        return Math.max(this.tileHeight, this.tileWidth);
    }

    public void setTileSize(int i) {
        this.tileWidth = i;
        this.tileHeight = i;
        requestLayout();
    }

    public void setTileSizeDp(int i) {
        setTileSize(dpToPx(i));
    }

    public int getTileHeight() {
        return this.tileHeight;
    }

    public void setTileHeight(int i) {
        this.tileHeight = i;
        requestLayout();
    }

    public void setTileHeightDp(int i) {
        setTileHeight(dpToPx(i));
    }

    public int getTileWidth() {
        return this.tileWidth;
    }

    public void setTileWidth(int i) {
        this.tileWidth = i;
        requestLayout();
    }

    public void setTileWidthDp(int i) {
        setTileWidth(dpToPx(i));
    }

    private int dpToPx(int i) {
        return (int) TypedValue.applyDimension(1, i, getResources().getDisplayMetrics());
    }

    public boolean canGoForward() {
        return this.pager.getCurrentItem() < this.adapter.getCount() - 1;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return this.pager.dispatchTouchEvent(motionEvent);
    }

    public boolean canGoBack() {
        return this.pager.getCurrentItem() > 0;
    }

    public int getSelectionColor() {
        return this.accentColor;
    }

    public void setSelectionColor(int i) {
        if (i == 0) {
            if (!isInEditMode()) {
                return;
            }
            i = -7829368;
        }
        this.accentColor = i;
        this.adapter.setSelectionColor(i);
        invalidate();
    }

    public int getArrowColor() {
        return this.arrowColor;
    }

    public void setArrowColor(int i) {
        if (i == 0) {
            return;
        }
        this.arrowColor = i;
        this.buttonPast.setColor(i);
        this.buttonFuture.setColor(i);
        invalidate();
    }

    public void setContentDescriptionArrowPast(CharSequence charSequence) {
        this.buttonPast.setContentDescription(charSequence);
    }

    public void setContentDescriptionArrowFuture(CharSequence charSequence) {
        this.buttonFuture.setContentDescription(charSequence);
    }

    public void setContentDescriptionCalendar(CharSequence charSequence) {
        this.calendarContentDescription = charSequence;
    }

    public CharSequence getCalendarContentDescription() {
        CharSequence charSequence = this.calendarContentDescription;
        return charSequence != null ? charSequence : getContext().getString(R.string.calendar);
    }

    public void setDayFormatterContentDescription(DayFormatter dayFormatter) {
        this.adapter.setDayFormatterContentDescription(dayFormatter);
    }

    public Drawable getLeftArrowMask() {
        return this.leftArrowMask;
    }

    public void setLeftArrowMask(Drawable drawable) {
        this.leftArrowMask = drawable;
        this.buttonPast.setImageDrawable(drawable);
    }

    public Drawable getRightArrowMask() {
        return this.rightArrowMask;
    }

    public void setRightArrowMask(Drawable drawable) {
        this.rightArrowMask = drawable;
        this.buttonFuture.setImageDrawable(drawable);
    }

    public void setHeaderTextAppearance(int i) {
        this.title.setTextAppearance(getContext(), i);
    }

    public void setDateTextAppearance(int i) {
        this.adapter.setDateTextAppearance(i);
    }

    public void setWeekDayTextAppearance(int i) {
        this.adapter.setWeekDayTextAppearance(i);
    }

    public CalendarDay getSelectedDate() {
        List<CalendarDay> selectedDates = this.adapter.getSelectedDates();
        if (selectedDates.isEmpty()) {
            return null;
        }
        return selectedDates.get(selectedDates.size() - 1);
    }

    public List<CalendarDay> getSelectedDates() {
        return this.adapter.getSelectedDates();
    }

    public void clearSelection() {
        List<CalendarDay> selectedDates = getSelectedDates();
        this.adapter.clearSelections();
        for (CalendarDay calendarDay : selectedDates) {
            dispatchOnDateSelected(calendarDay, false);
        }
    }

    public void setSelectedDate(Calendar calendar) {
        setSelectedDate(CalendarDay.from(calendar));
    }

    public void setSelectedDate(long j) {
        setSelectedDate(CalendarDay.from(j));
    }

    public void setSelectedDate(CalendarDay calendarDay) {
        clearSelection();
        if (calendarDay != null) {
            setDateSelected(calendarDay, true);
        }
    }

    public void setDateSelected(Calendar calendar, boolean z) {
        setDateSelected(CalendarDay.from(calendar), z);
    }

    public void setDateSelected(long j, boolean z) {
        setDateSelected(CalendarDay.from(j), z);
    }

    public void setDateSelected(CalendarDay calendarDay, boolean z) {
        if (calendarDay == null) {
            return;
        }
        this.adapter.setDateSelected(calendarDay, z);
    }

    public void setCurrentDate(Calendar calendar) {
        setCurrentDate(CalendarDay.from(calendar));
    }

    public void setCurrentDate(long j) {
        setCurrentDate(CalendarDay.from(j));
    }

    public CalendarDay getCurrentDate() {
        return this.adapter.getItem(this.pager.getCurrentItem());
    }

    public void setCurrentDate(CalendarDay calendarDay) {
        setCurrentDate(calendarDay, true);
    }

    public void setCurrentDate(CalendarDay calendarDay, boolean z) {
        if (calendarDay == null) {
            return;
        }
        this.pager.setCurrentItem(this.adapter.getIndexForDay(calendarDay), z);
        updateUi();
    }

    public CalendarDay getMinimumDate() {
        return this.minDate;
    }

    public CalendarDay getMaximumDate() {
        return this.maxDate;
    }

    public void setShowOtherDates(int i) {
        this.adapter.setShowOtherDates(i);
    }

    public void setAllowClickDaysOutsideCurrentMonth(boolean z) {
        this.allowClickDaysOutsideCurrentMonth = z;
    }

    public void setWeekDayFormatter(WeekDayFormatter weekDayFormatter) {
        CalendarPagerAdapter<?> calendarPagerAdapter = this.adapter;
        if (weekDayFormatter == null) {
            weekDayFormatter = WeekDayFormatter.DEFAULT;
        }
        calendarPagerAdapter.setWeekDayFormatter(weekDayFormatter);
    }

    public void setDayFormatter(DayFormatter dayFormatter) {
        CalendarPagerAdapter<?> calendarPagerAdapter = this.adapter;
        if (dayFormatter == null) {
            dayFormatter = DayFormatter.DEFAULT;
        }
        calendarPagerAdapter.setDayFormatter(dayFormatter);
    }

    public void setWeekDayLabels(CharSequence[] charSequenceArr) {
        setWeekDayFormatter(new ArrayWeekDayFormatter(charSequenceArr));
    }

    public void setWeekDayLabels(int i) {
        setWeekDayLabels(getResources().getTextArray(i));
    }

    public int getShowOtherDates() {
        return this.adapter.getShowOtherDates();
    }

    public boolean allowClickDaysOutsideCurrentMonth() {
        return this.allowClickDaysOutsideCurrentMonth;
    }

    public boolean isShowWeekDays() {
        return this.showWeekDays;
    }

    public void setTitleFormatter(TitleFormatter titleFormatter) {
        if (titleFormatter == null) {
            titleFormatter = DEFAULT_TITLE_FORMATTER;
        }
        this.titleChanger.setTitleFormatter(titleFormatter);
        this.adapter.setTitleFormatter(titleFormatter);
        updateUi();
    }

    public void setTitleMonths(CharSequence[] charSequenceArr) {
        setTitleFormatter(new MonthArrayTitleFormatter(charSequenceArr));
    }

    public void setTitleMonths(int i) {
        setTitleMonths(getResources().getTextArray(i));
    }

    public void setTitleAnimationOrientation(int i) {
        this.titleChanger.setOrientation(i);
    }

    public int getTitleAnimationOrientation() {
        return this.titleChanger.getOrientation();
    }

    public void setTopbarVisible(boolean z) {
        this.topbar.setVisibility(z ? 0 : 8);
        requestLayout();
    }

    public boolean getTopbarVisible() {
        return this.topbar.getVisibility() == 0;
    }

    public CalendarMode getCalendarMode() {
        return this.calendarMode;
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.color = getSelectionColor();
        savedState.dateTextAppearance = this.adapter.getDateTextAppearance();
        savedState.weekDayTextAppearance = this.adapter.getWeekDayTextAppearance();
        savedState.showOtherDates = getShowOtherDates();
        savedState.allowClickDaysOutsideCurrentMonth = allowClickDaysOutsideCurrentMonth();
        savedState.minDate = getMinimumDate();
        savedState.maxDate = getMaximumDate();
        savedState.selectedDates = getSelectedDates();
        savedState.firstDayOfWeek = getFirstDayOfWeek();
        savedState.orientation = getTitleAnimationOrientation();
        savedState.selectionMode = getSelectionMode();
        savedState.tileWidthPx = getTileWidth();
        savedState.tileHeightPx = getTileHeight();
        savedState.topbarVisible = getTopbarVisible();
        savedState.calendarMode = this.calendarMode;
        savedState.dynamicHeightEnabled = this.mDynamicHeightEnabled;
        savedState.currentMonth = this.currentMonth;
        savedState.cacheCurrentPosition = this.state.cacheCurrentPosition;
        savedState.showWeekDays = this.showWeekDays;
        return savedState;
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        newState().setFirstDayOfWeek(savedState.firstDayOfWeek).setCalendarDisplayMode(savedState.calendarMode).setMinimumDate(savedState.minDate).setMaximumDate(savedState.maxDate).isCacheCalendarPositionEnabled(savedState.cacheCurrentPosition).setShowWeekDays(savedState.showWeekDays).commit();
        setSelectionColor(savedState.color);
        setDateTextAppearance(savedState.dateTextAppearance);
        setWeekDayTextAppearance(savedState.weekDayTextAppearance);
        setShowOtherDates(savedState.showOtherDates);
        setAllowClickDaysOutsideCurrentMonth(savedState.allowClickDaysOutsideCurrentMonth);
        clearSelection();
        for (CalendarDay calendarDay : savedState.selectedDates) {
            setDateSelected(calendarDay, true);
        }
        setTitleAnimationOrientation(savedState.orientation);
        setTileWidth(savedState.tileWidthPx);
        setTileHeight(savedState.tileHeightPx);
        setTopbarVisible(savedState.topbarVisible);
        setSelectionMode(savedState.selectionMode);
        setDynamicHeightEnabled(savedState.dynamicHeightEnabled);
        setCurrentDate(savedState.currentMonth);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchSaveInstanceState(SparseArray<Parcelable> sparseArray) {
        dispatchFreezeSelfOnly(sparseArray);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchRestoreInstanceState(SparseArray<Parcelable> sparseArray) {
        dispatchThawSelfOnly(sparseArray);
    }

    private void setRangeDates(CalendarDay calendarDay, CalendarDay calendarDay2) {
        CalendarDay calendarDay3 = this.currentMonth;
        this.adapter.setRangeDates(calendarDay, calendarDay2);
        this.currentMonth = calendarDay3;
        if (calendarDay != null) {
            if (!calendarDay.isAfter(calendarDay3)) {
                calendarDay = this.currentMonth;
            }
            this.currentMonth = calendarDay;
        }
        this.pager.setCurrentItem(this.adapter.getIndexForDay(calendarDay3), false);
        updateUi();
    }

    /* loaded from: classes2.dex */
    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: com.prolificinteractive.materialcalendarview.MaterialCalendarView.SavedState.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        boolean allowClickDaysOutsideCurrentMonth;
        boolean cacheCurrentPosition;
        CalendarMode calendarMode;
        int color;
        CalendarDay currentMonth;
        int dateTextAppearance;
        boolean dynamicHeightEnabled;
        int firstDayOfWeek;
        CalendarDay maxDate;
        CalendarDay minDate;
        int orientation;
        List<CalendarDay> selectedDates;
        int selectionMode;
        int showOtherDates;
        boolean showWeekDays;
        int tileHeightPx;
        int tileWidthPx;
        boolean topbarVisible;
        int weekDayTextAppearance;

        SavedState(Parcelable parcelable) {
            super(parcelable);
            this.color = 0;
            this.dateTextAppearance = 0;
            this.weekDayTextAppearance = 0;
            this.showOtherDates = 4;
            this.allowClickDaysOutsideCurrentMonth = true;
            this.minDate = null;
            this.maxDate = null;
            this.selectedDates = new ArrayList();
            this.firstDayOfWeek = 1;
            this.orientation = 0;
            this.tileWidthPx = -1;
            this.tileHeightPx = -1;
            this.topbarVisible = true;
            this.selectionMode = 1;
            this.dynamicHeightEnabled = false;
            this.calendarMode = CalendarMode.MONTHS;
            this.currentMonth = null;
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.color);
            parcel.writeInt(this.dateTextAppearance);
            parcel.writeInt(this.weekDayTextAppearance);
            parcel.writeInt(this.showOtherDates);
            parcel.writeByte(this.allowClickDaysOutsideCurrentMonth ? (byte) 1 : (byte) 0);
            parcel.writeParcelable(this.minDate, 0);
            parcel.writeParcelable(this.maxDate, 0);
            parcel.writeTypedList(this.selectedDates);
            parcel.writeInt(this.firstDayOfWeek);
            parcel.writeInt(this.orientation);
            parcel.writeInt(this.tileWidthPx);
            parcel.writeInt(this.tileHeightPx);
            parcel.writeInt(this.topbarVisible ? 1 : 0);
            parcel.writeInt(this.selectionMode);
            parcel.writeInt(this.dynamicHeightEnabled ? 1 : 0);
            parcel.writeInt(this.calendarMode == CalendarMode.WEEKS ? 1 : 0);
            parcel.writeParcelable(this.currentMonth, 0);
            parcel.writeByte(this.cacheCurrentPosition ? (byte) 1 : (byte) 0);
            parcel.writeByte(this.showWeekDays ? (byte) 1 : (byte) 0);
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.color = 0;
            this.dateTextAppearance = 0;
            this.weekDayTextAppearance = 0;
            this.showOtherDates = 4;
            this.allowClickDaysOutsideCurrentMonth = true;
            this.minDate = null;
            this.maxDate = null;
            this.selectedDates = new ArrayList();
            this.firstDayOfWeek = 1;
            this.orientation = 0;
            this.tileWidthPx = -1;
            this.tileHeightPx = -1;
            this.topbarVisible = true;
            this.selectionMode = 1;
            this.dynamicHeightEnabled = false;
            this.calendarMode = CalendarMode.MONTHS;
            this.currentMonth = null;
            this.color = parcel.readInt();
            this.dateTextAppearance = parcel.readInt();
            this.weekDayTextAppearance = parcel.readInt();
            this.showOtherDates = parcel.readInt();
            this.allowClickDaysOutsideCurrentMonth = parcel.readByte() != 0;
            ClassLoader classLoader = CalendarDay.class.getClassLoader();
            this.minDate = (CalendarDay) parcel.readParcelable(classLoader);
            this.maxDate = (CalendarDay) parcel.readParcelable(classLoader);
            parcel.readTypedList(this.selectedDates, CalendarDay.CREATOR);
            this.firstDayOfWeek = parcel.readInt();
            this.orientation = parcel.readInt();
            this.tileWidthPx = parcel.readInt();
            this.tileHeightPx = parcel.readInt();
            this.topbarVisible = parcel.readInt() == 1;
            this.selectionMode = parcel.readInt();
            this.dynamicHeightEnabled = parcel.readInt() == 1;
            this.calendarMode = parcel.readInt() == 1 ? CalendarMode.WEEKS : CalendarMode.MONTHS;
            this.currentMonth = (CalendarDay) parcel.readParcelable(classLoader);
            this.cacheCurrentPosition = parcel.readByte() != 0;
            this.showWeekDays = parcel.readByte() != 0;
        }
    }

    private static int getThemeAccentColor(Context context) {
        int identifier = Build.VERSION.SDK_INT >= 21 ? 16843829 : context.getResources().getIdentifier("colorAccent", "attr", context.getPackageName());
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(identifier, typedValue, true);
        return typedValue.data;
    }

    public int getFirstDayOfWeek() {
        return this.firstDayOfWeek;
    }

    public void setDynamicHeightEnabled(boolean z) {
        this.mDynamicHeightEnabled = z;
    }

    public boolean isDynamicHeightEnabled() {
        return this.mDynamicHeightEnabled;
    }

    public void addDecorators(Collection<? extends DayViewDecorator> collection) {
        if (collection == null) {
            return;
        }
        this.dayViewDecorators.addAll(collection);
        this.adapter.setDecorators(this.dayViewDecorators);
    }

    public void addDecorators(DayViewDecorator... dayViewDecoratorArr) {
        addDecorators(Arrays.asList(dayViewDecoratorArr));
    }

    public void addDecorator(DayViewDecorator dayViewDecorator) {
        if (dayViewDecorator == null) {
            return;
        }
        this.dayViewDecorators.add(dayViewDecorator);
        this.adapter.setDecorators(this.dayViewDecorators);
    }

    public void removeDecorators() {
        this.dayViewDecorators.clear();
        this.adapter.setDecorators(this.dayViewDecorators);
    }

    public void removeDecorator(DayViewDecorator dayViewDecorator) {
        this.dayViewDecorators.remove(dayViewDecorator);
        this.adapter.setDecorators(this.dayViewDecorators);
    }

    public void invalidateDecorators() {
        this.adapter.invalidateDecorators();
    }

    public void setOnDateChangedListener(OnDateSelectedListener onDateSelectedListener) {
        this.listener = onDateSelectedListener;
    }

    public void setOnDateLongClickListener(OnDateLongClickListener onDateLongClickListener) {
        this.longClickListener = onDateLongClickListener;
    }

    public void setOnMonthChangedListener(OnMonthChangedListener onMonthChangedListener) {
        this.monthListener = onMonthChangedListener;
    }

    public void setOnRangeSelectedListener(OnRangeSelectedListener onRangeSelectedListener) {
        this.rangeListener = onRangeSelectedListener;
    }

    public void setOnTitleClickListener(View.OnClickListener onClickListener) {
        this.title.setOnClickListener(onClickListener);
    }

    protected void dispatchOnDateSelected(CalendarDay calendarDay, boolean z) {
        OnDateSelectedListener onDateSelectedListener = this.listener;
        if (onDateSelectedListener != null) {
            onDateSelectedListener.onDateSelected(this, calendarDay, z);
        }
    }

    protected void dispatchOnRangeSelected(CalendarDay calendarDay, CalendarDay calendarDay2) {
        OnRangeSelectedListener onRangeSelectedListener = this.rangeListener;
        ArrayList arrayList = new ArrayList();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(calendarDay.getDate());
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(calendarDay2.getDate());
        while (true) {
            if (!calendar.before(calendar2) && !calendar.equals(calendar2)) {
                break;
            }
            CalendarDay from = CalendarDay.from(calendar);
            this.adapter.setDateSelected(from, true);
            arrayList.add(from);
            calendar.add(5, 1);
        }
        if (onRangeSelectedListener != null) {
            onRangeSelectedListener.onRangeSelected(this, arrayList);
        }
    }

    protected void dispatchOnMonthChanged(CalendarDay calendarDay) {
        OnMonthChangedListener onMonthChangedListener = this.monthListener;
        if (onMonthChangedListener != null) {
            onMonthChangedListener.onMonthChanged(this, calendarDay);
        }
    }

    protected void onDateClicked(CalendarDay calendarDay, boolean z) {
        int i = this.selectionMode;
        if (i == 2) {
            this.adapter.setDateSelected(calendarDay, z);
            dispatchOnDateSelected(calendarDay, z);
        } else if (i == 3) {
            List<CalendarDay> selectedDates = this.adapter.getSelectedDates();
            if (selectedDates.size() == 0) {
                this.adapter.setDateSelected(calendarDay, z);
                dispatchOnDateSelected(calendarDay, z);
            } else if (selectedDates.size() == 1) {
                CalendarDay calendarDay2 = selectedDates.get(0);
                this.adapter.setDateSelected(calendarDay, z);
                if (calendarDay2.equals(calendarDay)) {
                    dispatchOnDateSelected(calendarDay, z);
                } else if (calendarDay2.isAfter(calendarDay)) {
                    dispatchOnRangeSelected(calendarDay, calendarDay2);
                } else {
                    dispatchOnRangeSelected(calendarDay2, calendarDay);
                }
            } else {
                this.adapter.clearSelections();
                this.adapter.setDateSelected(calendarDay, z);
                dispatchOnDateSelected(calendarDay, z);
            }
        } else {
            this.adapter.clearSelections();
            this.adapter.setDateSelected(calendarDay, true);
            dispatchOnDateSelected(calendarDay, true);
        }
    }

    public void selectRange(CalendarDay calendarDay, CalendarDay calendarDay2) {
        clearSelection();
        if (calendarDay == null || calendarDay2 == null) {
            return;
        }
        if (calendarDay.isAfter(calendarDay2)) {
            dispatchOnRangeSelected(calendarDay2, calendarDay);
        } else {
            dispatchOnRangeSelected(calendarDay, calendarDay2);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onDateClicked(DayView dayView) {
        CalendarDay currentDate = getCurrentDate();
        CalendarDay date = dayView.getDate();
        int month = currentDate.getMonth();
        int month2 = date.getMonth();
        if (this.calendarMode == CalendarMode.MONTHS && this.allowClickDaysOutsideCurrentMonth && month != month2) {
            if (currentDate.isAfter(date)) {
                goToPrevious();
            } else if (currentDate.isBefore(date)) {
                goToNext();
            }
        }
        onDateClicked(dayView.getDate(), !dayView.isChecked());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onDateLongClicked(DayView dayView) {
        OnDateLongClickListener onDateLongClickListener = this.longClickListener;
        if (onDateLongClickListener != null) {
            onDateLongClickListener.onDateLongClick(this, dayView.getDate());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onDateUnselected(CalendarDay calendarDay) {
        dispatchOnDateSelected(calendarDay, false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    public LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(1);
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int size = View.MeasureSpec.getSize(i);
        int mode = View.MeasureSpec.getMode(i);
        int size2 = View.MeasureSpec.getSize(i2);
        int mode2 = View.MeasureSpec.getMode(i2);
        int paddingLeft = (size - getPaddingLeft()) - getPaddingRight();
        int paddingTop = (size2 - getPaddingTop()) - getPaddingBottom();
        int weekCountBasedOnMode = getWeekCountBasedOnMode();
        if (getTopbarVisible()) {
            weekCountBasedOnMode++;
        }
        int i3 = paddingLeft / 7;
        int i4 = paddingTop / weekCountBasedOnMode;
        int i5 = this.tileWidth;
        int i6 = -1;
        if (i5 == -10 && this.tileHeight == -10) {
            if (mode == 1073741824 || mode == Integer.MIN_VALUE) {
                if (mode2 == 1073741824) {
                    i3 = Math.min(i3, i4);
                }
            } else if (mode2 == 1073741824 || mode2 == Integer.MIN_VALUE) {
                i3 = i4;
            } else {
                i3 = -1;
                i4 = -1;
            }
            i4 = -1;
        } else {
            if (i5 > 0) {
                i3 = i5;
            }
            int i7 = this.tileHeight;
            if (i7 > 0) {
                i4 = i7;
            }
            i6 = i3;
            i3 = -1;
        }
        if (i3 > 0) {
            i4 = i3;
        } else if (i3 <= 0) {
            int dpToPx = i6 <= 0 ? dpToPx(44) : i6;
            if (i4 <= 0) {
                i4 = dpToPx(44);
            }
            i3 = dpToPx;
        } else {
            i3 = i6;
        }
        int i8 = i3 * 7;
        setMeasuredDimension(clampSize(getPaddingLeft() + getPaddingRight() + i8, i), clampSize((weekCountBasedOnMode * i4) + getPaddingTop() + getPaddingBottom(), i2));
        int childCount = getChildCount();
        for (int i9 = 0; i9 < childCount; i9++) {
            View childAt = getChildAt(i9);
            childAt.measure(View.MeasureSpec.makeMeasureSpec(i8, BasicMeasure.EXACTLY), View.MeasureSpec.makeMeasureSpec(((LayoutParams) childAt.getLayoutParams()).height * i4, BasicMeasure.EXACTLY));
        }
    }

    private int getWeekCountBasedOnMode() {
        CalendarPagerAdapter<?> calendarPagerAdapter;
        CalendarPager calendarPager;
        int i = this.calendarMode.visibleWeeksCount;
        if (this.calendarMode.equals(CalendarMode.MONTHS) && this.mDynamicHeightEnabled && (calendarPagerAdapter = this.adapter) != null && (calendarPager = this.pager) != null) {
            Calendar calendar = (Calendar) calendarPagerAdapter.getItem(calendarPager.getCurrentItem()).getCalendar().clone();
            calendar.set(5, calendar.getActualMaximum(5));
            calendar.setFirstDayOfWeek(getFirstDayOfWeek());
            calendar.setMinimalDaysInFirstWeek(1);
            i = calendar.get(4);
        }
        return this.showWeekDays ? i + 1 : i;
    }

    private static int clampSize(int i, int i2) {
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        if (mode != Integer.MIN_VALUE) {
            return mode != 1073741824 ? i : size;
        }
        return Math.min(i, size);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        int paddingLeft = getPaddingLeft();
        int paddingRight = ((i3 - i) - paddingLeft) - getPaddingRight();
        int paddingTop = getPaddingTop();
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            if (childAt.getVisibility() != 8) {
                int measuredWidth = childAt.getMeasuredWidth();
                int i6 = ((paddingRight - measuredWidth) / 2) + paddingLeft;
                int measuredHeight = childAt.getMeasuredHeight() + paddingTop;
                childAt.layout(i6, paddingTop, measuredWidth + i6, measuredHeight);
                paddingTop = measuredHeight;
            }
        }
    }

    @Override // android.view.ViewGroup
    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(1);
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new LayoutParams(1);
    }

    @Override // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(MaterialCalendarView.class.getName());
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(MaterialCalendarView.class.getName());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes2.dex */
    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        public LayoutParams(int i) {
            super(-1, i);
        }
    }

    public void setPagingEnabled(boolean z) {
        this.pager.setPagingEnabled(z);
        updateUi();
    }

    public boolean isPagingEnabled() {
        return this.pager.isPagingEnabled();
    }

    public State state() {
        return this.state;
    }

    public StateBuilder newState() {
        return new StateBuilder();
    }

    /* loaded from: classes2.dex */
    public class State {
        private final boolean cacheCurrentPosition;
        private final CalendarMode calendarMode;
        private final int firstDayOfWeek;
        private final CalendarDay maxDate;
        private final CalendarDay minDate;
        private final boolean showWeekDays;

        private State(StateBuilder stateBuilder) {
            this.calendarMode = stateBuilder.calendarMode;
            this.firstDayOfWeek = stateBuilder.firstDayOfWeek;
            this.minDate = stateBuilder.minDate;
            this.maxDate = stateBuilder.maxDate;
            this.cacheCurrentPosition = stateBuilder.cacheCurrentPosition;
            this.showWeekDays = stateBuilder.showWeekDays;
        }

        public StateBuilder edit() {
            return new StateBuilder(this);
        }
    }

    /* loaded from: classes2.dex */
    public class StateBuilder {
        private boolean cacheCurrentPosition;
        private CalendarMode calendarMode;
        private int firstDayOfWeek;
        private CalendarDay maxDate;
        private CalendarDay minDate;
        private boolean showWeekDays;

        public StateBuilder() {
            this.calendarMode = CalendarMode.MONTHS;
            this.firstDayOfWeek = CalendarUtils.getInstance().getFirstDayOfWeek();
            this.cacheCurrentPosition = false;
            this.minDate = null;
            this.maxDate = null;
        }

        private StateBuilder(State state) {
            this.calendarMode = CalendarMode.MONTHS;
            this.firstDayOfWeek = CalendarUtils.getInstance().getFirstDayOfWeek();
            this.cacheCurrentPosition = false;
            this.minDate = null;
            this.maxDate = null;
            this.calendarMode = state.calendarMode;
            this.firstDayOfWeek = state.firstDayOfWeek;
            this.minDate = state.minDate;
            this.maxDate = state.maxDate;
            this.cacheCurrentPosition = state.cacheCurrentPosition;
            this.showWeekDays = state.showWeekDays;
        }

        public StateBuilder setFirstDayOfWeek(int i) {
            this.firstDayOfWeek = i;
            return this;
        }

        public StateBuilder setCalendarDisplayMode(CalendarMode calendarMode) {
            this.calendarMode = calendarMode;
            return this;
        }

        public StateBuilder setMinimumDate(Calendar calendar) {
            setMinimumDate(CalendarDay.from(calendar));
            return this;
        }

        public StateBuilder setMinimumDate(long j) {
            setMinimumDate(CalendarDay.from(j));
            return this;
        }

        public StateBuilder setMinimumDate(CalendarDay calendarDay) {
            this.minDate = calendarDay;
            return this;
        }

        public StateBuilder setMaximumDate(Calendar calendar) {
            setMaximumDate(CalendarDay.from(calendar));
            return this;
        }

        public StateBuilder setMaximumDate(long j) {
            setMaximumDate(CalendarDay.from(j));
            return this;
        }

        public StateBuilder setMaximumDate(CalendarDay calendarDay) {
            this.maxDate = calendarDay;
            return this;
        }

        public StateBuilder setShowWeekDays(boolean z) {
            this.showWeekDays = z;
            return this;
        }

        public StateBuilder isCacheCalendarPositionEnabled(boolean z) {
            this.cacheCurrentPosition = z;
            return this;
        }

        public void commit() {
            MaterialCalendarView.this.commit(new State(this));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0047, code lost:
        if (r3.isBefore(r4) == false) goto L30;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void commit(com.prolificinteractive.materialcalendarview.MaterialCalendarView.State r8) {
        com.prolificinteractive.materialcalendarview.CalendarDay r0;
        com.prolificinteractive.materialcalendarview.CalendarPagerAdapter<?> r8;
        com.prolificinteractive.materialcalendarview.CalendarDay r8;
        if (r7.adapter == null || !r8.cacheCurrentPosition) {
            r0 = null;
        } else {
            r0 = r7.adapter.getItem(r7.pager.getCurrentItem());
            if (r7.calendarMode != r8.calendarMode) {
                com.prolificinteractive.materialcalendarview.CalendarDay r3 = getSelectedDate();
                if (r7.calendarMode == com.prolificinteractive.materialcalendarview.CalendarMode.MONTHS && r3 != 0) {
                    java.util.Calendar r4 = r0.getCalendar();
                    r4.add(2, 1);
                    com.prolificinteractive.materialcalendarview.CalendarDay r4 = com.prolificinteractive.materialcalendarview.CalendarDay.from(r4);
                    if (!r3.equals(r0)) {
                        if (r3.isAfter(r0)) {
                        }
                    }
                    r0 = r3;
                } else if (r7.calendarMode == com.prolificinteractive.materialcalendarview.CalendarMode.WEEKS) {
                    java.util.Calendar r4 = r0.getCalendar();
                    r4.add(7, 6);
                    com.prolificinteractive.materialcalendarview.CalendarDay r4 = com.prolificinteractive.materialcalendarview.CalendarDay.from(r4);
                    if (r3 == null || (!r3.equals(r0) && !r3.equals(r4) && (!r3.isAfter(r0) || !r3.isBefore(r4)))) {
                        r0 = r4;
                    }
                    r0 = r3;
                }
            }
        }
        r7.state = r8;
        r7.calendarMode = r8.calendarMode;
        r7.firstDayOfWeek = r8.firstDayOfWeek;
        r7.minDate = r8.minDate;
        r7.maxDate = r8.maxDate;
        r7.showWeekDays = r8.showWeekDays;
        int r8 = com.prolificinteractive.materialcalendarview.MaterialCalendarView.AnonymousClass4.$SwitchMap$com$prolificinteractive$materialcalendarview$CalendarMode[r7.calendarMode.ordinal()];
        if (r8 == 1) {
            r8 = new com.prolificinteractive.materialcalendarview.MonthPagerAdapter(r7);
        } else if (r8 == 2) {
            r8 = new com.prolificinteractive.materialcalendarview.WeekPagerAdapter(r7);
        } else {
            throw new java.lang.IllegalArgumentException("Provided display mode which is not yet implemented");
        }
        com.prolificinteractive.materialcalendarview.CalendarPagerAdapter<?> r1 = r7.adapter;
        if (r1 == null) {
            r7.adapter = r8;
        } else {
            r7.adapter = r1.migrateStateAndReturn(r8);
        }
        r7.adapter.setShowWeekDays(r7.showWeekDays);
        r7.pager.setAdapter(r7.adapter);
        setRangeDates(r7.minDate, r7.maxDate);
        r7.pager.setLayoutParams(new com.prolificinteractive.materialcalendarview.MaterialCalendarView.LayoutParams(r7.showWeekDays ? r7.calendarMode.visibleWeeksCount + 1 : r7.calendarMode.visibleWeeksCount));
        if (r7.selectionMode == 1 && !r7.adapter.getSelectedDates().isEmpty()) {
            r8 = r7.adapter.getSelectedDates().get(0);
        } else {
            r8 = com.prolificinteractive.materialcalendarview.CalendarDay.today();
        }
        setCurrentDate(r8);
        if (r0 != null) {
            r7.pager.setCurrentItem(r7.adapter.getIndexForDay(r0));
        }
        invalidateDecorators();
        updateUi();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.prolificinteractive.materialcalendarview.MaterialCalendarView$4  reason: invalid class name */
    /* loaded from: classes2.dex */
    public static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$com$prolificinteractive$materialcalendarview$CalendarMode;

        static {
            int[] iArr = new int[CalendarMode.values().length];
            $SwitchMap$com$prolificinteractive$materialcalendarview$CalendarMode = iArr;
            try {
                iArr[CalendarMode.MONTHS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$prolificinteractive$materialcalendarview$CalendarMode[CalendarMode.WEEKS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }
}
