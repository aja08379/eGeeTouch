package com.kunzisoft.switchdatetime;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ViewAnimator;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import com.kunzisoft.switchdatetime.date.OnYearSelectedListener;
import com.kunzisoft.switchdatetime.date.widget.ListPickerYearView;
import com.kunzisoft.switchdatetime.time.SwitchTimePicker;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Pattern;
/* loaded from: classes2.dex */
public class SwitchDateTimeDialogFragment extends DialogFragment {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String DEFAULT_LOCALE = "en";
    private static final String STATE_CURRENT_POSITION = "STATE_CURRENT_POSITION";
    private static final String STATE_DATETIME = "STATE_DATETIME";
    private static final String TAG = "SwitchDateTimeDialogFrg";
    private static final String TAG_DEFAULT_LOCALE = "DEFAULT_LOCALE";
    private static final String TAG_LABEL = "LABEL";
    private static final String TAG_NEGATIVE_BUTTON = "NEGATIVE_BUTTON";
    private static final String TAG_NEUTRAL_BUTTON = "NEUTRAL_BUTTON";
    private static final String TAG_POSITIVE_BUTTON = "POSITIVE_BUTTON";
    private static final int UNDEFINED_POSITION = -1;
    private int alertStyleId;
    private boolean blockAnimationIn;
    private boolean blockAnimationOut;
    private SimpleDateFormat dayAndMonthSimpleDate;
    private ListPickerYearView listPickerYearView;
    private String mDefaultLocale;
    private String mLabel;
    private OnButtonClickListener mListener;
    private String mNegativeButton;
    private String mNeutralButton;
    private String mPositiveButton;
    private MaterialCalendarView materialCalendarView;
    private TextView monthAndDayHeaderValues;
    private SwitchTimePicker timePicker;
    private ViewAnimator viewSwitcher;
    private TextView yearHeaderValues;
    private SimpleDateFormat yearSimpleDate;
    private Calendar dateTimeCalendar = Calendar.getInstance();
    private Calendar minimumDateTime = new GregorianCalendar(1970, 1, 1);
    private Calendar maximumDateTime = new GregorianCalendar(2200, 1, 1);
    private TimeZone timeZone = TimeZone.getDefault();
    private boolean is24HoursMode = false;
    private boolean highlightAMPMSelection = false;
    private int startAtPosition = -1;
    private int currentPosition = 0;

    /* loaded from: classes2.dex */
    public interface OnButtonClickListener {
        void onNegativeButtonClick(Date date);

        void onPositiveButtonClick(Date date);
    }

    /* loaded from: classes2.dex */
    public interface OnButtonWithNeutralClickListener extends OnButtonClickListener {
        void onNeutralButtonClick(Date date);
    }

    public static SwitchDateTimeDialogFragment newInstance(String str, String str2, String str3) {
        return newInstance(str, str2, str3, null, DEFAULT_LOCALE);
    }

    public static SwitchDateTimeDialogFragment newInstance(String str, String str2, String str3, String str4, String str5) {
        SwitchDateTimeDialogFragment switchDateTimeDialogFragment = new SwitchDateTimeDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString(TAG_LABEL, str);
        bundle.putString(TAG_POSITIVE_BUTTON, str2);
        bundle.putString(TAG_NEGATIVE_BUTTON, str3);
        bundle.putString(TAG_DEFAULT_LOCALE, str5);
        if (str4 != null) {
            bundle.putString(TAG_NEUTRAL_BUTTON, str4);
        }
        switchDateTimeDialogFragment.setArguments(bundle);
        return switchDateTimeDialogFragment;
    }

    public void setOnButtonClickListener(OnButtonClickListener onButtonClickListener) {
        this.mListener = onButtonClickListener;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        bundle.putLong(STATE_DATETIME, this.dateTimeCalendar.getTimeInMillis());
        bundle.putInt(STATE_CURRENT_POSITION, this.currentPosition);
        this.timePicker.onSaveInstanceState(bundle);
        super.onSaveInstanceState(bundle);
    }

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        AlertDialog.Builder builder;
        super.onCreateDialog(bundle);
        this.dateTimeCalendar.setTimeZone(this.timeZone);
        if (getArguments() != null) {
            this.mLabel = getArguments().getString(TAG_LABEL);
            this.mPositiveButton = getArguments().getString(TAG_POSITIVE_BUTTON);
            this.mNegativeButton = getArguments().getString(TAG_NEGATIVE_BUTTON);
            this.mNeutralButton = getArguments().getString(TAG_NEUTRAL_BUTTON);
            this.mDefaultLocale = getArguments().getString(TAG_DEFAULT_LOCALE);
        }
        setDefaultLocale(this.mDefaultLocale);
        if (bundle != null) {
            this.currentPosition = bundle.getInt(STATE_CURRENT_POSITION);
            this.dateTimeCalendar.setTime(new Date(bundle.getLong(STATE_DATETIME)));
        }
        if (this.dateTimeCalendar.before(this.minimumDateTime) || this.dateTimeCalendar.after(this.maximumDateTime)) {
            throw new RuntimeException("Default date " + this.dateTimeCalendar.getTime() + " must be between " + this.minimumDateTime.getTime() + " and " + this.maximumDateTime.getTime());
        }
        LayoutInflater from = LayoutInflater.from(getActivity());
        getActivity().getTheme().applyStyle(R.style.Theme_SwitchDateTime, false);
        View inflate = from.inflate(R.layout.dialog_switch_datetime_picker, (ViewGroup) getActivity().findViewById(R.id.datetime_picker));
        TextView textView = (TextView) inflate.findViewById(R.id.label);
        String str = this.mLabel;
        if (str != null) {
            textView.setText(str);
        } else {
            textView.setText(getString(R.string.label_datetime_dialog));
        }
        this.blockAnimationIn = false;
        this.blockAnimationOut = false;
        ViewAnimator viewAnimator = (ViewAnimator) inflate.findViewById(R.id.dateSwitcher);
        this.viewSwitcher = viewAnimator;
        viewAnimator.getInAnimation().setAnimationListener(new Animation.AnimationListener() { // from class: com.kunzisoft.switchdatetime.SwitchDateTimeDialogFragment.1
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
                SwitchDateTimeDialogFragment.this.blockAnimationIn = true;
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                SwitchDateTimeDialogFragment.this.blockAnimationIn = false;
                SwitchDateTimeDialogFragment switchDateTimeDialogFragment = SwitchDateTimeDialogFragment.this;
                switchDateTimeDialogFragment.currentPosition = switchDateTimeDialogFragment.viewSwitcher.getDisplayedChild();
            }
        });
        this.viewSwitcher.getOutAnimation().setAnimationListener(new Animation.AnimationListener() { // from class: com.kunzisoft.switchdatetime.SwitchDateTimeDialogFragment.2
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
                SwitchDateTimeDialogFragment.this.blockAnimationOut = true;
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                SwitchDateTimeDialogFragment.this.blockAnimationOut = false;
            }
        });
        int i = this.startAtPosition;
        if (i != -1) {
            this.currentPosition = i;
        }
        this.viewSwitcher.setDisplayedChild(this.currentPosition);
        ImageButton imageButton = (ImageButton) inflate.findViewById(R.id.button_switch);
        imageButton.setBackgroundColor(0);
        imageButton.setOnClickListener(new View.OnClickListener() { // from class: com.kunzisoft.switchdatetime.SwitchDateTimeDialogFragment.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Utils.animLabelElement(view);
                if (SwitchDateTimeDialogFragment.this.blockAnimationIn && SwitchDateTimeDialogFragment.this.blockAnimationOut) {
                    return;
                }
                SwitchDateTimeDialogFragment.this.viewSwitcher.showNext();
            }
        });
        View findViewById = inflate.findViewById(R.id.time_header_values);
        OnClickHeaderElementListener onClickHeaderElementListener = new OnClickHeaderElementListener(HeaderViewsPosition.VIEW_HOURS_AND_MINUTES.getPosition());
        findViewById.setOnClickListener(onClickHeaderElementListener);
        this.monthAndDayHeaderValues = (TextView) inflate.findViewById(R.id.date_picker_month_and_day);
        this.monthAndDayHeaderValues.setOnClickListener(new OnClickHeaderElementListener(HeaderViewsPosition.VIEW_MONTH_AND_DAY.getPosition()));
        this.yearHeaderValues = (TextView) inflate.findViewById(R.id.date_picker_year);
        this.yearHeaderValues.setOnClickListener(new OnClickHeaderElementListener(HeaderViewsPosition.VIEW_YEAR.getPosition()));
        if (this.dayAndMonthSimpleDate == null) {
            this.dayAndMonthSimpleDate = new SimpleDateFormat("MMMM dd", Locale.getDefault());
        }
        if (this.yearSimpleDate == null) {
            this.yearSimpleDate = new SimpleDateFormat("yyyy", Locale.getDefault());
        }
        this.dayAndMonthSimpleDate.setTimeZone(this.timeZone);
        this.yearSimpleDate.setTimeZone(this.timeZone);
        this.yearHeaderValues.setText(this.yearSimpleDate.format(this.dateTimeCalendar.getTime()));
        this.monthAndDayHeaderValues.setText(this.dayAndMonthSimpleDate.format(this.dateTimeCalendar.getTime()));
        SwitchTimePicker switchTimePicker = new SwitchTimePicker(getContext(), new SwitchTimePicker.OnTimeSelectedListener() { // from class: com.kunzisoft.switchdatetime.SwitchDateTimeDialogFragment.4
            @Override // com.kunzisoft.switchdatetime.time.SwitchTimePicker.OnTimeSelectedListener
            public void onTimeSelected(int i2, int i3) {
                SwitchDateTimeDialogFragment.this.dateTimeCalendar.set(11, i2);
                SwitchDateTimeDialogFragment.this.dateTimeCalendar.set(12, i3);
            }
        }, bundle);
        this.timePicker = switchTimePicker;
        switchTimePicker.setIs24HourMode(this.is24HoursMode);
        this.timePicker.setHighlightAMPMSelection(this.highlightAMPMSelection);
        this.timePicker.setHourOfDay(this.dateTimeCalendar.get(11));
        this.timePicker.setMinute(this.dateTimeCalendar.get(12));
        this.timePicker.onCreateView(inflate, bundle);
        this.timePicker.setOnClickTimeListener(onClickHeaderElementListener);
        MaterialCalendarView materialCalendarView = (MaterialCalendarView) inflate.findViewById(R.id.datePicker);
        this.materialCalendarView = materialCalendarView;
        materialCalendarView.state().edit().setMinimumDate(CalendarDay.from(this.minimumDateTime)).setMaximumDate(CalendarDay.from(this.maximumDateTime)).commit();
        this.materialCalendarView.setCurrentDate(this.dateTimeCalendar);
        this.materialCalendarView.setDateSelected(this.dateTimeCalendar, true);
        this.materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() { // from class: com.kunzisoft.switchdatetime.SwitchDateTimeDialogFragment.5
            @Override // com.prolificinteractive.materialcalendarview.OnDateSelectedListener
            public void onDateSelected(MaterialCalendarView materialCalendarView2, CalendarDay calendarDay, boolean z) {
                SwitchDateTimeDialogFragment.this.dateTimeCalendar.set(1, calendarDay.getYear());
                SwitchDateTimeDialogFragment.this.dateTimeCalendar.set(2, calendarDay.getMonth());
                SwitchDateTimeDialogFragment.this.dateTimeCalendar.set(5, calendarDay.getDay());
                SwitchDateTimeDialogFragment.this.listPickerYearView.assignCurrentYear(calendarDay.getYear());
                SwitchDateTimeDialogFragment.this.yearHeaderValues.setText(SwitchDateTimeDialogFragment.this.yearSimpleDate.format(SwitchDateTimeDialogFragment.this.dateTimeCalendar.getTime()));
                SwitchDateTimeDialogFragment.this.monthAndDayHeaderValues.setText(SwitchDateTimeDialogFragment.this.dayAndMonthSimpleDate.format(SwitchDateTimeDialogFragment.this.dateTimeCalendar.getTime()));
                SwitchDateTimeDialogFragment.this.timePicker.clickHour();
            }
        });
        this.materialCalendarView.invalidate();
        ListPickerYearView listPickerYearView = (ListPickerYearView) inflate.findViewById(R.id.yearPicker);
        this.listPickerYearView = listPickerYearView;
        listPickerYearView.setMinYear(this.minimumDateTime.get(1));
        this.listPickerYearView.setMaxYear(this.maximumDateTime.get(1));
        this.listPickerYearView.assignCurrentYear(this.dateTimeCalendar.get(1));
        this.listPickerYearView.setDatePickerListener(new OnYearSelectedListener() { // from class: com.kunzisoft.switchdatetime.SwitchDateTimeDialogFragment.6
            @Override // com.kunzisoft.switchdatetime.date.OnYearSelectedListener
            public void onYearSelected(View view, int i2) {
                SwitchDateTimeDialogFragment.this.dateTimeCalendar.set(1, i2);
                SwitchDateTimeDialogFragment.this.yearHeaderValues.setText(SwitchDateTimeDialogFragment.this.yearSimpleDate.format(SwitchDateTimeDialogFragment.this.dateTimeCalendar.getTime()));
                SwitchDateTimeDialogFragment.this.materialCalendarView.setCurrentDate(SwitchDateTimeDialogFragment.this.dateTimeCalendar.getTime().getTime());
                SwitchDateTimeDialogFragment.this.materialCalendarView.setDateSelected(SwitchDateTimeDialogFragment.this.dateTimeCalendar, true);
                SwitchDateTimeDialogFragment.this.materialCalendarView.goToNext();
                SwitchDateTimeDialogFragment.this.materialCalendarView.goToPrevious();
            }
        });
        if (this.alertStyleId != 0) {
            builder = new AlertDialog.Builder(getContext(), this.alertStyleId);
        } else {
            builder = new AlertDialog.Builder(getContext());
        }
        builder.setView(inflate);
        if (this.mPositiveButton == null) {
            this.mPositiveButton = getString(17039370);
        }
        builder.setPositiveButton(this.mPositiveButton, new DialogInterface.OnClickListener() { // from class: com.kunzisoft.switchdatetime.SwitchDateTimeDialogFragment.7
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i2) {
                if (SwitchDateTimeDialogFragment.this.mListener != null) {
                    SwitchDateTimeDialogFragment.this.mListener.onPositiveButtonClick(SwitchDateTimeDialogFragment.this.dateTimeCalendar.getTime());
                }
            }
        });
        if (this.mNegativeButton == null) {
            this.mNegativeButton = getString(17039360);
        }
        builder.setNegativeButton(this.mNegativeButton, new DialogInterface.OnClickListener() { // from class: com.kunzisoft.switchdatetime.SwitchDateTimeDialogFragment.8
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i2) {
                if (SwitchDateTimeDialogFragment.this.mListener != null) {
                    SwitchDateTimeDialogFragment.this.mListener.onNegativeButtonClick(SwitchDateTimeDialogFragment.this.dateTimeCalendar.getTime());
                }
            }
        });
        String str2 = this.mNeutralButton;
        if (str2 != null) {
            builder.setNeutralButton(str2, new DialogInterface.OnClickListener() { // from class: com.kunzisoft.switchdatetime.SwitchDateTimeDialogFragment.9
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i2) {
                    if (SwitchDateTimeDialogFragment.this.mListener == null || !(SwitchDateTimeDialogFragment.this.mListener instanceof OnButtonWithNeutralClickListener)) {
                        return;
                    }
                    ((OnButtonWithNeutralClickListener) SwitchDateTimeDialogFragment.this.mListener).onNeutralButtonClick(SwitchDateTimeDialogFragment.this.dateTimeCalendar.getTime());
                }
            });
        }
        return builder.create();
    }

    private void setDefaultLocale(String str) {
        Locale locale = new Locale(str);
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.locale = locale;
        getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        this.startAtPosition = -1;
    }

    public void startAtTimeView() {
        this.startAtPosition = HeaderViewsPosition.VIEW_HOURS_AND_MINUTES.getPosition();
    }

    public void startAtCalendarView() {
        this.startAtPosition = HeaderViewsPosition.VIEW_MONTH_AND_DAY.getPosition();
    }

    public void startAtYearView() {
        this.startAtPosition = HeaderViewsPosition.VIEW_YEAR.getPosition();
    }

    public void setDefaultYear(int i) {
        this.dateTimeCalendar.set(1, i);
    }

    @Deprecated
    public void setYear(int i) {
        setDefaultYear(i);
    }

    public void setDefaultMonth(int i) {
        this.dateTimeCalendar.set(2, i);
    }

    @Deprecated
    public void setMonth(int i) {
        setDefaultMonth(i);
    }

    public void setDefaultDay(int i) {
        this.dateTimeCalendar.set(5, i);
    }

    @Deprecated
    public void setDay(int i) {
        setDefaultDay(i);
    }

    public void setDefaultHourOfDay(int i) {
        this.dateTimeCalendar.set(11, i);
    }

    @Deprecated
    public void setHour(int i) {
        setDefaultHourOfDay(i);
    }

    public void setDefaultMinute(int i) {
        this.dateTimeCalendar.set(12, i);
    }

    @Deprecated
    public void setMinute(int i) {
        setDefaultMinute(i);
    }

    public int getYear() {
        return this.dateTimeCalendar.get(1);
    }

    public int getMonth() {
        return this.dateTimeCalendar.get(2);
    }

    public int getDay() {
        return this.dateTimeCalendar.get(5);
    }

    public int getHourOfDay() {
        return this.dateTimeCalendar.get(11);
    }

    public int getMinute() {
        return this.dateTimeCalendar.get(12);
    }

    public void setDefaultDateTime(Date date) {
        this.dateTimeCalendar.setTime(date);
    }

    public void setMinimumDateTime(Date date) {
        this.minimumDateTime.setTime(date);
    }

    public void setMaximumDateTime(Date date) {
        this.maximumDateTime.setTime(date);
    }

    public Date getMinimumDateTime() {
        return this.minimumDateTime.getTime();
    }

    public Date getMaximumDateTime() {
        return this.maximumDateTime.getTime();
    }

    public SimpleDateFormat getSimpleDateMonthAndDayFormat() {
        return this.dayAndMonthSimpleDate;
    }

    public void setSimpleDateMonthAndDayFormat(SimpleDateFormat simpleDateFormat) throws SimpleDateMonthAndDayFormatException {
        Pattern compile = Pattern.compile("(M|w|W|D|d|F|E|u|\\.|\\s)*");
        if (!compile.matcher(simpleDateFormat.toPattern()).matches()) {
            throw new SimpleDateMonthAndDayFormatException(simpleDateFormat.toPattern() + "isn't allowed for " + compile.pattern());
        }
        this.dayAndMonthSimpleDate = simpleDateFormat;
    }

    public void set24HoursMode(boolean z) {
        this.is24HoursMode = z;
    }

    public void setHighlightAMPMSelection(boolean z) {
        this.highlightAMPMSelection = z;
    }

    public void setTimeZone(TimeZone timeZone) {
        if (timeZone != null) {
            this.timeZone = timeZone;
        }
    }

    public void setAlertStyle(int i) {
        this.alertStyleId = i;
    }

    /* loaded from: classes2.dex */
    public static class SimpleDateMonthAndDayFormatException extends Exception {
        SimpleDateMonthAndDayFormatException(String str) {
            super(str);
        }
    }

    /* loaded from: classes2.dex */
    public enum HeaderViewsPosition {
        VIEW_HOURS_AND_MINUTES(0),
        VIEW_MONTH_AND_DAY(1),
        VIEW_YEAR(2);
        
        private final int positionSwitch;

        HeaderViewsPosition(int i) {
            this.positionSwitch = i;
        }

        public int getPosition() {
            return this.positionSwitch;
        }
    }

    /* loaded from: classes2.dex */
    public class OnClickHeaderElementListener implements View.OnClickListener {
        private final int positionView;

        OnClickHeaderElementListener(int i) {
            this.positionView = i;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Utils.animLabelElement(view);
            if (SwitchDateTimeDialogFragment.this.viewSwitcher.getDisplayedChild() != this.positionView) {
                SwitchDateTimeDialogFragment.this.viewSwitcher.setDisplayedChild(this.positionView);
            }
            SwitchDateTimeDialogFragment.this.startAtPosition = this.positionView;
        }
    }
}
