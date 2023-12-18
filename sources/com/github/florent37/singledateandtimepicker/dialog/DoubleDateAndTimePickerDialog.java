package com.github.florent37.singledateandtimepicker.dialog;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.StateListDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.TextView;
import com.github.florent37.singledateandtimepicker.R;
import com.github.florent37.singledateandtimepicker.SingleDateAndTimePicker;
import com.github.florent37.singledateandtimepicker.dialog.BottomSheetHelper;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
/* loaded from: classes.dex */
public class DoubleDateAndTimePickerDialog extends BaseDialog {
    private Integer bottomSheetHeight;
    private BottomSheetHelper bottomSheetHelper;
    private String buttonOkText;
    private TextView buttonTab0;
    private TextView buttonTab1;
    private Listener listener;
    private SingleDateAndTimePicker pickerTab0;
    private SingleDateAndTimePicker pickerTab1;
    private boolean secondDateAfterFirst;
    private View tab0;
    private Date tab0Date;
    private boolean tab0Days;
    private boolean tab0Hours;
    private boolean tab0Minutes;
    private String tab0Text;
    private View tab1;
    private Date tab1Date;
    private boolean tab1Days;
    private boolean tab1Hours;
    private boolean tab1Minutes;
    private String tab1Text;
    private String title;
    private Integer titleTextSize;
    private String todayText;

    /* loaded from: classes.dex */
    public interface Listener {
        void onDateSelected(List<Date> list);
    }

    private DoubleDateAndTimePickerDialog(Context context) {
        this(context, false);
    }

    private DoubleDateAndTimePickerDialog(Context context, boolean z) {
        BottomSheetHelper bottomSheetHelper = new BottomSheetHelper(context, z ? R.layout.bottom_sheet_double_picker_bottom_sheet : R.layout.bottom_sheet_double_picker);
        this.bottomSheetHelper = bottomSheetHelper;
        bottomSheetHelper.setListener(new BottomSheetHelper.Listener() { // from class: com.github.florent37.singledateandtimepicker.dialog.DoubleDateAndTimePickerDialog.1
            @Override // com.github.florent37.singledateandtimepicker.dialog.BottomSheetHelper.Listener
            public void onOpen() {
            }

            @Override // com.github.florent37.singledateandtimepicker.dialog.BottomSheetHelper.Listener
            public void onLoaded(View view) {
                DoubleDateAndTimePickerDialog.this.init(view);
            }

            @Override // com.github.florent37.singledateandtimepicker.dialog.BottomSheetHelper.Listener
            public void onClose() {
                DoubleDateAndTimePickerDialog.this.onClose();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void init(View view) {
        this.buttonTab0 = (TextView) view.findViewById(R.id.buttonTab0);
        this.buttonTab1 = (TextView) view.findViewById(R.id.buttonTab1);
        this.pickerTab0 = (SingleDateAndTimePicker) view.findViewById(R.id.picker_tab_0);
        this.pickerTab1 = (SingleDateAndTimePicker) view.findViewById(R.id.picker_tab_1);
        this.tab0 = view.findViewById(R.id.tab0);
        this.tab1 = view.findViewById(R.id.tab1);
        SingleDateAndTimePicker singleDateAndTimePicker = this.pickerTab0;
        if (singleDateAndTimePicker != null && this.bottomSheetHeight != null) {
            ViewGroup.LayoutParams layoutParams = singleDateAndTimePicker.getLayoutParams();
            layoutParams.height = this.bottomSheetHeight.intValue();
            this.pickerTab0.setLayoutParams(layoutParams);
        }
        SingleDateAndTimePicker singleDateAndTimePicker2 = this.pickerTab1;
        if (singleDateAndTimePicker2 != null && this.bottomSheetHeight != null) {
            ViewGroup.LayoutParams layoutParams2 = singleDateAndTimePicker2.getLayoutParams();
            layoutParams2.height = this.bottomSheetHeight.intValue();
            this.pickerTab1.setLayoutParams(layoutParams2);
        }
        View findViewById = view.findViewById(R.id.sheetTitleLayout);
        TextView textView = (TextView) view.findViewById(R.id.sheetTitle);
        String str = this.title;
        if (str != null) {
            if (textView != null) {
                textView.setText(str);
                if (this.titleTextColor != null) {
                    textView.setTextColor(this.titleTextColor.intValue());
                }
                Integer num = this.titleTextSize;
                if (num != null) {
                    textView.setTextSize(num.intValue());
                }
            }
            if (this.mainColor != null && findViewById != null) {
                findViewById.setBackgroundColor(this.mainColor.intValue());
            }
        } else {
            findViewById.setVisibility(8);
        }
        this.pickerTab0.setTodayText(this.todayText);
        this.pickerTab1.setTodayText(this.todayText);
        View findViewById2 = view.findViewById(R.id.sheetContentLayout);
        if (findViewById2 != null) {
            findViewById2.setOnClickListener(new View.OnClickListener() { // from class: com.github.florent37.singledateandtimepicker.dialog.DoubleDateAndTimePickerDialog.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                }
            });
            if (this.backgroundColor != null) {
                findViewById2.setBackgroundColor(this.backgroundColor.intValue());
            }
        }
        this.tab1.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() { // from class: com.github.florent37.singledateandtimepicker.dialog.DoubleDateAndTimePickerDialog.3
            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public boolean onPreDraw() {
                DoubleDateAndTimePickerDialog.this.tab1.getViewTreeObserver().removeOnPreDrawListener(this);
                DoubleDateAndTimePickerDialog.this.tab1.setTranslationX(DoubleDateAndTimePickerDialog.this.tab1.getWidth());
                return false;
            }
        });
        this.buttonTab0.setSelected(true);
        String str2 = this.tab0Text;
        if (str2 != null) {
            this.buttonTab0.setText(str2);
        }
        this.buttonTab0.setOnClickListener(new View.OnClickListener() { // from class: com.github.florent37.singledateandtimepicker.dialog.DoubleDateAndTimePickerDialog.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                DoubleDateAndTimePickerDialog.this.displayTab0();
            }
        });
        String str3 = this.tab1Text;
        if (str3 != null) {
            this.buttonTab1.setText(str3);
        }
        this.buttonTab1.setOnClickListener(new View.OnClickListener() { // from class: com.github.florent37.singledateandtimepicker.dialog.DoubleDateAndTimePickerDialog.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                DoubleDateAndTimePickerDialog.this.displayTab1();
            }
        });
        this.buttonTab0.setBackgroundDrawable(getTabsListDrawable());
        this.buttonTab1.setBackgroundDrawable(getTabsListDrawable());
        TextView textView2 = (TextView) view.findViewById(R.id.buttonOk);
        if (textView2 != null) {
            String str4 = this.buttonOkText;
            if (str4 != null) {
                textView2.setText(str4);
            }
            if (this.mainColor != null) {
                textView2.setTextColor(this.mainColor.intValue());
            }
            Integer num2 = this.titleTextSize;
            if (num2 != null) {
                textView2.setTextSize(num2.intValue());
            }
        }
        textView2.setOnClickListener(new View.OnClickListener() { // from class: com.github.florent37.singledateandtimepicker.dialog.DoubleDateAndTimePickerDialog.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (DoubleDateAndTimePickerDialog.this.isTab0Visible()) {
                    DoubleDateAndTimePickerDialog.this.displayTab1();
                    return;
                }
                DoubleDateAndTimePickerDialog.this.okClicked = true;
                DoubleDateAndTimePickerDialog.this.close();
            }
        });
        if (this.curved) {
            this.pickerTab0.setCurved(true);
            this.pickerTab1.setCurved(true);
            this.pickerTab0.setVisibleItemCount(7);
            this.pickerTab1.setVisibleItemCount(7);
        } else {
            this.pickerTab0.setCurved(false);
            this.pickerTab1.setCurved(false);
            this.pickerTab0.setVisibleItemCount(5);
            this.pickerTab1.setVisibleItemCount(5);
        }
        this.pickerTab0.setDisplayDays(this.tab0Days);
        this.pickerTab0.setDisplayHours(this.tab0Hours);
        this.pickerTab0.setDisplayMinutes(this.tab0Minutes);
        this.pickerTab1.setDisplayDays(this.tab1Days);
        this.pickerTab1.setDisplayHours(this.tab1Hours);
        this.pickerTab1.setDisplayMinutes(this.tab1Minutes);
        this.pickerTab0.setMustBeOnFuture(this.mustBeOnFuture);
        this.pickerTab1.setMustBeOnFuture(this.mustBeOnFuture);
        this.pickerTab0.setStepMinutes(this.minutesStep);
        this.pickerTab1.setStepMinutes(this.minutesStep);
        if (this.mainColor != null) {
            this.pickerTab0.setSelectedTextColor(this.mainColor.intValue());
            this.pickerTab1.setSelectedTextColor(this.mainColor.intValue());
        }
        if (this.minDate != null) {
            this.pickerTab0.setMinDate(this.minDate);
            this.pickerTab1.setMinDate(this.minDate);
        }
        if (this.maxDate != null) {
            this.pickerTab0.setMaxDate(this.maxDate);
            this.pickerTab1.setMaxDate(this.maxDate);
        }
        if (this.defaultDate != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(this.defaultDate);
            this.pickerTab0.selectDate(calendar);
            this.pickerTab1.selectDate(calendar);
        }
        if (this.tab0Date != null) {
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(this.tab0Date);
            this.pickerTab0.selectDate(calendar2);
        }
        if (this.tab1Date != null) {
            Calendar calendar3 = Calendar.getInstance();
            calendar3.setTime(this.tab1Date);
            this.pickerTab1.selectDate(calendar3);
        }
        if (this.dayFormatter != null) {
            this.pickerTab0.setDayFormatter(this.dayFormatter);
            this.pickerTab1.setDayFormatter(this.dayFormatter);
        }
        if (this.secondDateAfterFirst) {
            this.pickerTab0.addOnDateChangedListener(new SingleDateAndTimePicker.OnDateChangedListener() { // from class: com.github.florent37.singledateandtimepicker.dialog.DoubleDateAndTimePickerDialog.7
                @Override // com.github.florent37.singledateandtimepicker.SingleDateAndTimePicker.OnDateChangedListener
                public void onDateChanged(String str5, Date date) {
                    DoubleDateAndTimePickerDialog.this.pickerTab1.setMinDate(date);
                    DoubleDateAndTimePickerDialog.this.pickerTab1.checkPickersMinMax();
                }
            });
        }
    }

    private StateListDrawable getTabsListDrawable() {
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{16842913}, new ColorDrawable(this.mainColor.intValue()));
        stateListDrawable.addState(new int[]{-16842913}, new ColorDrawable(this.backgroundColor.intValue()));
        return stateListDrawable;
    }

    public DoubleDateAndTimePickerDialog setTab0Text(String str) {
        this.tab0Text = str;
        return this;
    }

    public DoubleDateAndTimePickerDialog setTab1Text(String str) {
        this.tab1Text = str;
        return this;
    }

    public DoubleDateAndTimePickerDialog setButtonOkText(String str) {
        this.buttonOkText = str;
        return this;
    }

    public DoubleDateAndTimePickerDialog setTitle(String str) {
        this.title = str;
        return this;
    }

    public DoubleDateAndTimePickerDialog setTitleTextSize(Integer num) {
        this.titleTextSize = num;
        return this;
    }

    public DoubleDateAndTimePickerDialog setBottomSheetHeight(Integer num) {
        this.bottomSheetHeight = num;
        return this;
    }

    public DoubleDateAndTimePickerDialog setTodayText(String str) {
        this.todayText = str;
        return this;
    }

    public DoubleDateAndTimePickerDialog setListener(Listener listener) {
        this.listener = listener;
        return this;
    }

    public DoubleDateAndTimePickerDialog setCurved(boolean z) {
        this.curved = z;
        return this;
    }

    public DoubleDateAndTimePickerDialog setMinutesStep(int i) {
        this.minutesStep = i;
        return this;
    }

    public DoubleDateAndTimePickerDialog setMustBeOnFuture(boolean z) {
        this.mustBeOnFuture = z;
        return this;
    }

    public DoubleDateAndTimePickerDialog setMinDateRange(Date date) {
        this.minDate = date;
        return this;
    }

    public DoubleDateAndTimePickerDialog setMaxDateRange(Date date) {
        this.maxDate = date;
        return this;
    }

    public DoubleDateAndTimePickerDialog setDefaultDate(Date date) {
        this.defaultDate = date;
        return this;
    }

    public DoubleDateAndTimePickerDialog setDayFormatter(SimpleDateFormat simpleDateFormat) {
        this.dayFormatter = simpleDateFormat;
        return this;
    }

    public DoubleDateAndTimePickerDialog setTab0Date(Date date) {
        this.tab0Date = date;
        return this;
    }

    public DoubleDateAndTimePickerDialog setTab1Date(Date date) {
        this.tab1Date = date;
        return this;
    }

    public DoubleDateAndTimePickerDialog setSecondDateAfterFirst(boolean z) {
        this.secondDateAfterFirst = z;
        return this;
    }

    public DoubleDateAndTimePickerDialog setTab0DisplayDays(boolean z) {
        this.tab0Days = z;
        return this;
    }

    public DoubleDateAndTimePickerDialog setTab0DisplayHours(boolean z) {
        this.tab0Hours = z;
        return this;
    }

    public DoubleDateAndTimePickerDialog setTab0DisplayMinutes(boolean z) {
        this.tab0Minutes = z;
        return this;
    }

    public DoubleDateAndTimePickerDialog setTab1DisplayDays(boolean z) {
        this.tab1Days = z;
        return this;
    }

    public DoubleDateAndTimePickerDialog setTab1DisplayHours(boolean z) {
        this.tab1Hours = z;
        return this;
    }

    public DoubleDateAndTimePickerDialog setTab1DisplayMinutes(boolean z) {
        this.tab1Minutes = z;
        return this;
    }

    @Override // com.github.florent37.singledateandtimepicker.dialog.BaseDialog
    public void display() {
        super.display();
        this.bottomSheetHelper.display();
    }

    @Override // com.github.florent37.singledateandtimepicker.dialog.BaseDialog
    public void dismiss() {
        super.dismiss();
        this.bottomSheetHelper.dismiss();
    }

    @Override // com.github.florent37.singledateandtimepicker.dialog.BaseDialog
    public void close() {
        super.close();
        this.bottomSheetHelper.hide();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.github.florent37.singledateandtimepicker.dialog.BaseDialog
    public void onClose() {
        super.onClose();
        if (this.listener == null || !this.okClicked) {
            return;
        }
        this.listener.onDateSelected(Arrays.asList(this.pickerTab0.getDate(), this.pickerTab1.getDate()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void displayTab0() {
        if (isTab0Visible()) {
            return;
        }
        this.buttonTab0.setSelected(true);
        this.buttonTab1.setSelected(false);
        this.tab0.animate().translationX(0.0f);
        this.tab1.animate().translationX(this.tab1.getWidth());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void displayTab1() {
        if (isTab0Visible()) {
            this.buttonTab0.setSelected(false);
            this.buttonTab1.setSelected(true);
            this.tab0.animate().translationX(-this.tab0.getWidth());
            this.tab1.animate().translationX(0.0f);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isTab0Visible() {
        return this.tab0.getTranslationX() == 0.0f;
    }

    /* loaded from: classes.dex */
    public static class Builder {
        private boolean bottomSheet;
        private Integer bottomSheetHeight;
        private String buttonOkText;
        private final Context context;
        private boolean curved;
        private SimpleDateFormat dayFormatter;
        private Date defaultDate;
        private DoubleDateAndTimePickerDialog dialog;
        private Listener listener;
        private Date maxDate;
        private Date minDate;
        private boolean mustBeOnFuture;
        private boolean secondDateAfterFirst;
        private Date tab0Date;
        private String tab0Text;
        private Date tab1Date;
        private String tab1Text;
        private String title;
        private Integer titleTextSize;
        private String todayText;
        private int minutesStep = 5;
        private Integer backgroundColor = null;
        private Integer mainColor = null;
        private Integer titleTextColor = null;
        private boolean tab0Days = true;
        private boolean tab0Hours = true;
        private boolean tab0Minutes = true;
        private boolean tab1Days = true;
        private boolean tab1Hours = true;
        private boolean tab1Minutes = true;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder title(String str) {
            this.title = str;
            return this;
        }

        public Builder titleTextSize(Integer num) {
            this.titleTextSize = num;
            return this;
        }

        public Builder bottomSheetHeight(Integer num) {
            this.bottomSheetHeight = num;
            return this;
        }

        public Builder todayText(String str) {
            this.todayText = str;
            return this;
        }

        public Builder bottomSheet() {
            this.bottomSheet = true;
            return this;
        }

        public Builder curved() {
            this.curved = true;
            return this;
        }

        public Builder mustBeOnFuture() {
            this.mustBeOnFuture = true;
            return this;
        }

        public Builder dayFormatter(SimpleDateFormat simpleDateFormat) {
            this.dayFormatter = simpleDateFormat;
            return this;
        }

        public Builder minutesStep(int i) {
            this.minutesStep = i;
            return this;
        }

        public Builder titleTextColor(int i) {
            this.titleTextColor = Integer.valueOf(i);
            return this;
        }

        public Builder backgroundColor(int i) {
            this.backgroundColor = Integer.valueOf(i);
            return this;
        }

        public Builder mainColor(int i) {
            this.mainColor = Integer.valueOf(i);
            return this;
        }

        public Builder minDateRange(Date date) {
            this.minDate = date;
            return this;
        }

        public Builder maxDateRange(Date date) {
            this.maxDate = date;
            return this;
        }

        public Builder defaultDate(Date date) {
            this.defaultDate = date;
            return this;
        }

        public Builder tab0Date(Date date) {
            this.tab0Date = date;
            return this;
        }

        public Builder tab1Date(Date date) {
            this.tab1Date = date;
            return this;
        }

        public Builder listener(Listener listener) {
            this.listener = listener;
            return this;
        }

        public Builder tab1Text(String str) {
            this.tab1Text = str;
            return this;
        }

        public Builder tab0Text(String str) {
            this.tab0Text = str;
            return this;
        }

        public Builder buttonOkText(String str) {
            this.buttonOkText = str;
            return this;
        }

        public Builder secondDateAfterFirst(boolean z) {
            this.secondDateAfterFirst = z;
            return this;
        }

        public Builder setTab0DisplayDays(boolean z) {
            this.tab0Days = z;
            return this;
        }

        public Builder setTab0DisplayHours(boolean z) {
            this.tab0Hours = z;
            return this;
        }

        public Builder setTab0DisplayMinutes(boolean z) {
            this.tab0Minutes = z;
            return this;
        }

        public Builder setTab1DisplayDays(boolean z) {
            this.tab1Days = z;
            return this;
        }

        public Builder setTab1DisplayHours(boolean z) {
            this.tab1Hours = z;
            return this;
        }

        public Builder setTab1DisplayMinutes(boolean z) {
            this.tab1Minutes = z;
            return this;
        }

        public DoubleDateAndTimePickerDialog build() {
            DoubleDateAndTimePickerDialog secondDateAfterFirst = new DoubleDateAndTimePickerDialog(this.context, this.bottomSheet).setTitle(this.title).setTitleTextSize(this.titleTextSize).setBottomSheetHeight(this.bottomSheetHeight).setTodayText(this.todayText).setListener(this.listener).setCurved(this.curved).setButtonOkText(this.buttonOkText).setTab0Text(this.tab0Text).setTab1Text(this.tab1Text).setMinutesStep(this.minutesStep).setMaxDateRange(this.maxDate).setMinDateRange(this.minDate).setDefaultDate(this.defaultDate).setTab0DisplayDays(this.tab0Days).setTab0DisplayHours(this.tab0Hours).setTab0DisplayMinutes(this.tab0Minutes).setTab1DisplayDays(this.tab1Days).setTab1DisplayHours(this.tab1Hours).setTab1DisplayMinutes(this.tab1Minutes).setTab0Date(this.tab0Date).setTab1Date(this.tab1Date).setDayFormatter(this.dayFormatter).setMustBeOnFuture(this.mustBeOnFuture).setSecondDateAfterFirst(this.secondDateAfterFirst);
            Integer num = this.mainColor;
            if (num != null) {
                secondDateAfterFirst.setMainColor(num);
            }
            Integer num2 = this.backgroundColor;
            if (num2 != null) {
                secondDateAfterFirst.setBackgroundColor(num2);
            }
            Integer num3 = this.titleTextColor;
            if (num3 != null) {
                secondDateAfterFirst.setTitleTextColor(num3.intValue());
            }
            return secondDateAfterFirst;
        }

        public void display() {
            DoubleDateAndTimePickerDialog build = build();
            this.dialog = build;
            build.display();
        }

        public void close() {
            DoubleDateAndTimePickerDialog doubleDateAndTimePickerDialog = this.dialog;
            if (doubleDateAndTimePickerDialog != null) {
                doubleDateAndTimePickerDialog.close();
            }
        }

        public void dismiss() {
            DoubleDateAndTimePickerDialog doubleDateAndTimePickerDialog = this.dialog;
            if (doubleDateAndTimePickerDialog != null) {
                doubleDateAndTimePickerDialog.dismiss();
            }
        }
    }
}
