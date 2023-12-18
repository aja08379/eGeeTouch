package com.github.florent37.singledateandtimepicker;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.text.format.DateFormat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.core.content.ContextCompat;
import com.github.florent37.singledateandtimepicker.widget.WheelAmPmPicker;
import com.github.florent37.singledateandtimepicker.widget.WheelDayOfMonthPicker;
import com.github.florent37.singledateandtimepicker.widget.WheelDayPicker;
import com.github.florent37.singledateandtimepicker.widget.WheelHourPicker;
import com.github.florent37.singledateandtimepicker.widget.WheelMinutePicker;
import com.github.florent37.singledateandtimepicker.widget.WheelMonthPicker;
import com.github.florent37.singledateandtimepicker.widget.WheelPicker;
import com.github.florent37.singledateandtimepicker.widget.WheelYearPicker;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
/* loaded from: classes.dex */
public class SingleDateAndTimePicker extends LinearLayout {
    public static final int DELAY_BEFORE_CHECK_PAST = 200;
    public static final boolean IS_CURVED_DEFAULT = false;
    public static final boolean IS_CYCLIC_DEFAULT = true;
    public static final boolean MUST_BE_ON_FUTUR_DEFAULT = false;
    private static final int PM_HOUR_ADDITION = 12;
    private static final int VISIBLE_ITEM_COUNT_DEFAULT = 7;
    private final WheelAmPmPicker amPmPicker;
    private final WheelDayOfMonthPicker daysOfMonthPicker;
    private final WheelDayPicker daysPicker;
    private Date defaultDate;
    private boolean displayDays;
    private boolean displayDaysOfMonth;
    private boolean displayHours;
    private boolean displayMinutes;
    private boolean displayMonth;
    private boolean displayYears;
    private View dtSelector;
    private final WheelHourPicker hoursPicker;
    private boolean isAmPm;
    private List<OnDateChangedListener> listeners;
    private Date maxDate;
    private Date minDate;
    private final WheelMinutePicker minutesPicker;
    private final WheelMonthPicker monthPicker;
    private boolean mustBeOnFuture;
    private List<WheelPicker> pickers;
    private final WheelYearPicker yearsPicker;
    private static final CharSequence FORMAT_24_HOUR = "EEE d MMM H:mm";
    private static final CharSequence FORMAT_12_HOUR = "EEE d MMM h:mm a";

    /* loaded from: classes.dex */
    public interface OnDateChangedListener {
        void onDateChanged(String str, Date date);
    }

    public SingleDateAndTimePicker(Context context) {
        this(context, null);
    }

    public SingleDateAndTimePicker(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SingleDateAndTimePicker(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.pickers = new ArrayList();
        this.listeners = new ArrayList();
        this.displayYears = false;
        this.displayMonth = false;
        this.displayDaysOfMonth = false;
        this.displayDays = true;
        this.displayMinutes = true;
        this.displayHours = true;
        this.defaultDate = new Date();
        this.isAmPm = !DateFormat.is24HourFormat(context);
        inflate(context, R.layout.single_day_picker, this);
        WheelYearPicker wheelYearPicker = (WheelYearPicker) findViewById(R.id.yearPicker);
        this.yearsPicker = wheelYearPicker;
        WheelMonthPicker wheelMonthPicker = (WheelMonthPicker) findViewById(R.id.monthPicker);
        this.monthPicker = wheelMonthPicker;
        WheelDayOfMonthPicker wheelDayOfMonthPicker = (WheelDayOfMonthPicker) findViewById(R.id.daysOfMonthPicker);
        this.daysOfMonthPicker = wheelDayOfMonthPicker;
        WheelDayPicker wheelDayPicker = (WheelDayPicker) findViewById(R.id.daysPicker);
        this.daysPicker = wheelDayPicker;
        WheelMinutePicker wheelMinutePicker = (WheelMinutePicker) findViewById(R.id.minutesPicker);
        this.minutesPicker = wheelMinutePicker;
        WheelHourPicker wheelHourPicker = (WheelHourPicker) findViewById(R.id.hoursPicker);
        this.hoursPicker = wheelHourPicker;
        WheelAmPmPicker wheelAmPmPicker = (WheelAmPmPicker) findViewById(R.id.amPmPicker);
        this.amPmPicker = wheelAmPmPicker;
        this.dtSelector = findViewById(R.id.dtSelector);
        this.pickers.addAll(Arrays.asList(wheelDayPicker, wheelMinutePicker, wheelHourPicker, wheelAmPmPicker, wheelDayOfMonthPicker, wheelMonthPicker, wheelYearPicker));
        init(context, attributeSet);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.yearsPicker.setOnYearSelectedListener(new WheelYearPicker.OnYearSelectedListener() { // from class: com.github.florent37.singledateandtimepicker.SingleDateAndTimePicker.1
            @Override // com.github.florent37.singledateandtimepicker.widget.WheelYearPicker.OnYearSelectedListener
            public void onYearSelected(WheelYearPicker wheelYearPicker, int i, int i2) {
                SingleDateAndTimePicker.this.updateListener();
                SingleDateAndTimePicker.this.checkMinMaxDate(wheelYearPicker);
                if (SingleDateAndTimePicker.this.displayDaysOfMonth) {
                    SingleDateAndTimePicker.this.updateDaysOfMonth();
                }
            }
        });
        this.monthPicker.setOnMonthSelectedListener(new WheelMonthPicker.MonthSelectedListener() { // from class: com.github.florent37.singledateandtimepicker.SingleDateAndTimePicker.2
            @Override // com.github.florent37.singledateandtimepicker.widget.WheelMonthPicker.MonthSelectedListener
            public void onMonthSelected(WheelMonthPicker wheelMonthPicker, int i, String str) {
                SingleDateAndTimePicker.this.updateListener();
                SingleDateAndTimePicker.this.checkMinMaxDate(wheelMonthPicker);
                if (SingleDateAndTimePicker.this.displayDaysOfMonth) {
                    SingleDateAndTimePicker.this.updateDaysOfMonth();
                }
            }
        });
        this.daysOfMonthPicker.setDayOfMonthSelectedListener(new WheelDayOfMonthPicker.DayOfMonthSelectedListener() { // from class: com.github.florent37.singledateandtimepicker.SingleDateAndTimePicker.3
            @Override // com.github.florent37.singledateandtimepicker.widget.WheelDayOfMonthPicker.DayOfMonthSelectedListener
            public void onDayOfMonthSelected(WheelDayOfMonthPicker wheelDayOfMonthPicker, int i) {
                SingleDateAndTimePicker.this.updateListener();
                SingleDateAndTimePicker.this.checkMinMaxDate(wheelDayOfMonthPicker);
            }
        });
        this.daysOfMonthPicker.setOnFinishedLoopListener(new WheelDayOfMonthPicker.FinishedLoopListener() { // from class: com.github.florent37.singledateandtimepicker.SingleDateAndTimePicker.4
            @Override // com.github.florent37.singledateandtimepicker.widget.WheelDayOfMonthPicker.FinishedLoopListener
            public void onFinishedLoop(WheelDayOfMonthPicker wheelDayOfMonthPicker) {
                if (SingleDateAndTimePicker.this.displayMonth) {
                    SingleDateAndTimePicker.this.monthPicker.scrollTo(SingleDateAndTimePicker.this.monthPicker.getCurrentItemPosition() + 1);
                    SingleDateAndTimePicker.this.updateDaysOfMonth();
                }
            }
        });
        this.daysPicker.setOnDaySelectedListener(new WheelDayPicker.OnDaySelectedListener() { // from class: com.github.florent37.singledateandtimepicker.SingleDateAndTimePicker.5
            @Override // com.github.florent37.singledateandtimepicker.widget.WheelDayPicker.OnDaySelectedListener
            public void onDaySelected(WheelDayPicker wheelDayPicker, int i, String str, Date date) {
                SingleDateAndTimePicker.this.updateListener();
                SingleDateAndTimePicker.this.checkMinMaxDate(wheelDayPicker);
            }
        });
        this.minutesPicker.setOnMinuteChangedListener(new WheelMinutePicker.OnMinuteChangedListener() { // from class: com.github.florent37.singledateandtimepicker.SingleDateAndTimePicker.7
            @Override // com.github.florent37.singledateandtimepicker.widget.WheelMinutePicker.OnMinuteChangedListener
            public void onMinuteChanged(WheelMinutePicker wheelMinutePicker, int i) {
                SingleDateAndTimePicker.this.updateListener();
                SingleDateAndTimePicker.this.checkMinMaxDate(wheelMinutePicker);
            }
        }).setOnFinishedLoopListener(new WheelMinutePicker.OnFinishedLoopListener() { // from class: com.github.florent37.singledateandtimepicker.SingleDateAndTimePicker.6
            @Override // com.github.florent37.singledateandtimepicker.widget.WheelMinutePicker.OnFinishedLoopListener
            public void onFinishedLoop(WheelMinutePicker wheelMinutePicker) {
                SingleDateAndTimePicker.this.hoursPicker.scrollTo(SingleDateAndTimePicker.this.hoursPicker.getCurrentItemPosition() + 1);
            }
        });
        this.hoursPicker.setOnFinishedLoopListener(new WheelHourPicker.FinishedLoopListener() { // from class: com.github.florent37.singledateandtimepicker.SingleDateAndTimePicker.9
            @Override // com.github.florent37.singledateandtimepicker.widget.WheelHourPicker.FinishedLoopListener
            public void onFinishedLoop(WheelHourPicker wheelHourPicker) {
                SingleDateAndTimePicker.this.daysPicker.scrollTo(SingleDateAndTimePicker.this.daysPicker.getCurrentItemPosition() + 1);
            }
        }).setHourChangedListener(new WheelHourPicker.OnHourChangedListener() { // from class: com.github.florent37.singledateandtimepicker.SingleDateAndTimePicker.8
            @Override // com.github.florent37.singledateandtimepicker.widget.WheelHourPicker.OnHourChangedListener
            public void onHourChanged(WheelHourPicker wheelHourPicker, int i) {
                SingleDateAndTimePicker.this.updateListener();
                SingleDateAndTimePicker.this.checkMinMaxDate(wheelHourPicker);
            }
        });
        this.amPmPicker.setAmPmListener(new WheelAmPmPicker.AmPmListener() { // from class: com.github.florent37.singledateandtimepicker.SingleDateAndTimePicker.10
            @Override // com.github.florent37.singledateandtimepicker.widget.WheelAmPmPicker.AmPmListener
            public void onAmPmChanged(WheelAmPmPicker wheelAmPmPicker, boolean z) {
                SingleDateAndTimePicker.this.updateListener();
                SingleDateAndTimePicker.this.checkMinMaxDate(wheelAmPmPicker);
            }
        });
        setDefaultDate(this.defaultDate);
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        for (WheelPicker wheelPicker : this.pickers) {
            wheelPicker.setEnabled(z);
        }
    }

    public void setDisplayYears(boolean z) {
        this.displayYears = z;
        this.yearsPicker.setVisibility(z ? 0 : 8);
    }

    public void setDisplayMonths(boolean z) {
        this.displayMonth = z;
        this.monthPicker.setVisibility(z ? 0 : 8);
        checkSettings();
    }

    public void setDisplayDaysOfMonth(boolean z) {
        this.displayDaysOfMonth = z;
        this.daysOfMonthPicker.setVisibility(z ? 0 : 8);
        if (z) {
            updateDaysOfMonth();
        }
        checkSettings();
    }

    public void setDisplayDays(boolean z) {
        this.displayDays = z;
        this.daysPicker.setVisibility(z ? 0 : 8);
        checkSettings();
    }

    public void setDisplayMinutes(boolean z) {
        this.displayMinutes = z;
        this.minutesPicker.setVisibility(z ? 0 : 8);
    }

    public void setDisplayHours(boolean z) {
        this.displayHours = z;
        this.hoursPicker.setVisibility(z ? 0 : 8);
        setIsAmPm(this.isAmPm);
        this.hoursPicker.setIsAmPm(this.isAmPm);
    }

    public void setDisplayMonthNumbers(boolean z) {
        this.monthPicker.setDisplayMonthNumbers(z);
        this.monthPicker.updateAdapter();
    }

    public void setTodayText(String str) {
        if (str == null || str.isEmpty()) {
            return;
        }
        this.daysPicker.setTodayText(str);
    }

    public void setCurved(boolean z) {
        for (WheelPicker wheelPicker : this.pickers) {
            wheelPicker.setCurved(z);
        }
    }

    public void setCyclic(boolean z) {
        for (WheelPicker wheelPicker : this.pickers) {
            wheelPicker.setCyclic(z);
        }
    }

    public void setTextSize(int i) {
        for (WheelPicker wheelPicker : this.pickers) {
            wheelPicker.setItemTextSize(i);
        }
    }

    public void setSelectedTextColor(int i) {
        for (WheelPicker wheelPicker : this.pickers) {
            wheelPicker.setSelectedItemTextColor(i);
        }
    }

    public void setTextColor(int i) {
        for (WheelPicker wheelPicker : this.pickers) {
            wheelPicker.setItemTextColor(i);
        }
    }

    public void setSelectorColor(int i) {
        this.dtSelector.setBackgroundColor(i);
    }

    public void setSelectorHeight(int i) {
        ViewGroup.LayoutParams layoutParams = this.dtSelector.getLayoutParams();
        layoutParams.height = i;
        this.dtSelector.setLayoutParams(layoutParams);
    }

    public void setVisibleItemCount(int i) {
        for (WheelPicker wheelPicker : this.pickers) {
            wheelPicker.setVisibleItemCount(i);
        }
    }

    public void setIsAmPm(boolean z) {
        this.isAmPm = z;
        this.amPmPicker.setVisibility((z && this.displayHours) ? 0 : 8);
        this.hoursPicker.setIsAmPm(z);
    }

    public void setDayFormatter(SimpleDateFormat simpleDateFormat) {
        if (simpleDateFormat != null) {
            this.daysPicker.setDayFormatter(simpleDateFormat);
        }
    }

    public boolean isAmPm() {
        return this.isAmPm;
    }

    public Date getMinDate() {
        return this.minDate;
    }

    public void setMinDate(Date date) {
        this.minDate = date;
        setMinYear();
    }

    public Date getMaxDate() {
        return this.maxDate;
    }

    public void setMaxDate(Date date) {
        this.maxDate = date;
        setMinYear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkMinMaxDate(WheelPicker wheelPicker) {
        checkBeforeMinDate(wheelPicker);
        checkAfterMaxDate(wheelPicker);
    }

    private void checkBeforeMinDate(WheelPicker wheelPicker) {
        wheelPicker.postDelayed(new Runnable() { // from class: com.github.florent37.singledateandtimepicker.SingleDateAndTimePicker.11
            @Override // java.lang.Runnable
            public void run() {
                if (SingleDateAndTimePicker.this.minDate != null) {
                    SingleDateAndTimePicker singleDateAndTimePicker = SingleDateAndTimePicker.this;
                    if (singleDateAndTimePicker.isBeforeMinDate(singleDateAndTimePicker.getDate())) {
                        for (WheelPicker wheelPicker2 : SingleDateAndTimePicker.this.pickers) {
                            wheelPicker2.scrollTo(wheelPicker2.findIndexOfDate(SingleDateAndTimePicker.this.minDate));
                        }
                    }
                }
            }
        }, 200L);
    }

    private void checkAfterMaxDate(WheelPicker wheelPicker) {
        wheelPicker.postDelayed(new Runnable() { // from class: com.github.florent37.singledateandtimepicker.SingleDateAndTimePicker.12
            @Override // java.lang.Runnable
            public void run() {
                if (SingleDateAndTimePicker.this.maxDate != null) {
                    SingleDateAndTimePicker singleDateAndTimePicker = SingleDateAndTimePicker.this;
                    if (singleDateAndTimePicker.isAfterMaxDate(singleDateAndTimePicker.getDate())) {
                        for (WheelPicker wheelPicker2 : SingleDateAndTimePicker.this.pickers) {
                            wheelPicker2.scrollTo(wheelPicker2.findIndexOfDate(SingleDateAndTimePicker.this.maxDate));
                        }
                    }
                }
            }
        }, 200L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isBeforeMinDate(Date date) {
        return DateHelper.getCalendarOfDate(date).before(DateHelper.getCalendarOfDate(this.minDate));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isAfterMaxDate(Date date) {
        return DateHelper.getCalendarOfDate(date).after(DateHelper.getCalendarOfDate(this.maxDate));
    }

    public void addOnDateChangedListener(OnDateChangedListener onDateChangedListener) {
        this.listeners.add(onDateChangedListener);
    }

    public void removeOnDateChangedListener(OnDateChangedListener onDateChangedListener) {
        this.listeners.remove(onDateChangedListener);
    }

    public void checkPickersMinMax() {
        for (WheelPicker wheelPicker : this.pickers) {
            checkMinMaxDate(wheelPicker);
        }
    }

    public Date getDate() {
        int currentHour = this.hoursPicker.getCurrentHour();
        if (this.isAmPm && this.amPmPicker.isPm()) {
            currentHour += 12;
        }
        int currentMinute = this.minutesPicker.getCurrentMinute();
        Calendar calendar = Calendar.getInstance();
        if (this.displayDays) {
            calendar.setTime(this.daysPicker.getCurrentDate());
        } else {
            if (this.displayMonth) {
                calendar.set(2, this.monthPicker.getCurrentMonth());
            }
            if (this.displayYears) {
                calendar.set(1, this.yearsPicker.getCurrentYear());
            }
            if (this.displayDaysOfMonth) {
                int actualMaximum = calendar.getActualMaximum(5);
                if (this.daysOfMonthPicker.getCurrentDay() >= actualMaximum) {
                    calendar.set(5, actualMaximum);
                } else {
                    calendar.set(5, this.daysOfMonthPicker.getCurrentDay() + 1);
                }
            }
        }
        calendar.set(11, currentHour);
        calendar.set(12, currentMinute);
        return calendar.getTime();
    }

    public void setStepMinutes(int i) {
        this.minutesPicker.setStepMinutes(i);
    }

    public void setHoursStep(int i) {
        this.hoursPicker.setHoursStep(i);
    }

    public void setDefaultDate(Date date) {
        if (date != null) {
            this.defaultDate = date;
            updateDaysOfMonth();
            for (WheelPicker wheelPicker : this.pickers) {
                wheelPicker.setDefaultDate(this.defaultDate);
            }
        }
    }

    public void selectDate(Calendar calendar) {
        if (calendar == null) {
            return;
        }
        Date time = calendar.getTime();
        for (WheelPicker wheelPicker : this.pickers) {
            wheelPicker.selectDate(time);
        }
        if (this.displayDaysOfMonth) {
            updateDaysOfMonth();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateListener() {
        Date date = getDate();
        String charSequence = DateFormat.format(this.isAmPm ? FORMAT_12_HOUR : FORMAT_24_HOUR, date).toString();
        for (OnDateChangedListener onDateChangedListener : this.listeners) {
            onDateChangedListener.onDateChanged(charSequence, date);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateDaysOfMonth() {
        Date date = getDate();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        updateDaysOfMonth(calendar);
    }

    private void updateDaysOfMonth(Calendar calendar) {
        this.daysOfMonthPicker.setDaysInMonth(calendar.getActualMaximum(5));
        this.daysOfMonthPicker.updateAdapter();
    }

    public void setMustBeOnFuture(boolean z) {
        this.mustBeOnFuture = z;
        if (z) {
            this.minDate = Calendar.getInstance().getTime();
        }
    }

    public boolean mustBeOnFuture() {
        return this.mustBeOnFuture;
    }

    private void setMinYear() {
        if (!this.displayYears || this.minDate == null || this.maxDate == null) {
            return;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.minDate);
        this.yearsPicker.setMinYear(calendar.get(1));
        calendar.setTime(this.maxDate);
        this.yearsPicker.setMaxYear(calendar.get(1));
    }

    private void checkSettings() {
        if (this.displayDays) {
            if (this.displayDaysOfMonth || this.displayMonth) {
                throw new IllegalArgumentException("You can either display days with months or days and months separately");
            }
        }
    }

    private void init(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.SingleDateAndTimePicker);
        Resources resources = getResources();
        setTodayText(obtainStyledAttributes.getString(R.styleable.SingleDateAndTimePicker_picker_todayText));
        setTextColor(obtainStyledAttributes.getColor(R.styleable.SingleDateAndTimePicker_picker_textColor, ContextCompat.getColor(context, R.color.picker_default_text_color)));
        setSelectedTextColor(obtainStyledAttributes.getColor(R.styleable.SingleDateAndTimePicker_picker_selectedTextColor, ContextCompat.getColor(context, R.color.picker_default_selected_text_color)));
        setSelectorColor(obtainStyledAttributes.getColor(R.styleable.SingleDateAndTimePicker_picker_selectorColor, ContextCompat.getColor(context, R.color.picker_default_selector_color)));
        setSelectorHeight(obtainStyledAttributes.getDimensionPixelSize(R.styleable.SingleDateAndTimePicker_picker_selectorHeight, resources.getDimensionPixelSize(R.dimen.wheelSelectorHeight)));
        setTextSize(obtainStyledAttributes.getDimensionPixelSize(R.styleable.SingleDateAndTimePicker_picker_textSize, resources.getDimensionPixelSize(R.dimen.WheelItemTextSize)));
        setCurved(obtainStyledAttributes.getBoolean(R.styleable.SingleDateAndTimePicker_picker_curved, false));
        setCyclic(obtainStyledAttributes.getBoolean(R.styleable.SingleDateAndTimePicker_picker_cyclic, true));
        setMustBeOnFuture(obtainStyledAttributes.getBoolean(R.styleable.SingleDateAndTimePicker_picker_mustBeOnFuture, false));
        setVisibleItemCount(obtainStyledAttributes.getInt(R.styleable.SingleDateAndTimePicker_picker_visibleItemCount, 7));
        setDisplayDays(obtainStyledAttributes.getBoolean(R.styleable.SingleDateAndTimePicker_picker_displayDays, this.displayDays));
        setDisplayMinutes(obtainStyledAttributes.getBoolean(R.styleable.SingleDateAndTimePicker_picker_displayMinutes, this.displayMinutes));
        setDisplayHours(obtainStyledAttributes.getBoolean(R.styleable.SingleDateAndTimePicker_picker_displayHours, this.displayHours));
        setDisplayMonths(obtainStyledAttributes.getBoolean(R.styleable.SingleDateAndTimePicker_picker_displayMonth, this.displayMonth));
        setDisplayYears(obtainStyledAttributes.getBoolean(R.styleable.SingleDateAndTimePicker_picker_displayYears, this.displayYears));
        setDisplayDaysOfMonth(obtainStyledAttributes.getBoolean(R.styleable.SingleDateAndTimePicker_picker_displayDaysOfMonth, this.displayDaysOfMonth));
        setDisplayMonthNumbers(obtainStyledAttributes.getBoolean(R.styleable.SingleDateAndTimePicker_picker_displayMonthNumbers, this.monthPicker.displayMonthNumbers()));
        checkSettings();
        setMinYear();
        obtainStyledAttributes.recycle();
        if (this.displayDaysOfMonth) {
            updateDaysOfMonth(Calendar.getInstance());
        }
    }
}
