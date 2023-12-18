package com.github.florent37.singledateandtimepicker.dialog;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.github.florent37.singledateandtimepicker.R;
import com.github.florent37.singledateandtimepicker.SingleDateAndTimePicker;
import com.github.florent37.singledateandtimepicker.dialog.BottomSheetHelper;
import java.text.SimpleDateFormat;
import java.util.Date;
/* loaded from: classes.dex */
public class SingleDateAndTimePickerDialog extends BaseDialog {
    private Integer bottomSheetHeight;
    private BottomSheetHelper bottomSheetHelper;
    private DisplayListener displayListener;
    private Listener listener;
    private SingleDateAndTimePicker picker;
    private String title;
    private Integer titleTextSize;
    private String todayText;

    /* loaded from: classes.dex */
    public interface DisplayListener {
        void onDisplayed(SingleDateAndTimePicker singleDateAndTimePicker);
    }

    /* loaded from: classes.dex */
    public interface Listener {
        void onDateSelected(Date date);
    }

    private SingleDateAndTimePickerDialog(Context context) {
        this(context, false);
    }

    private SingleDateAndTimePickerDialog(Context context, boolean z) {
        BottomSheetHelper bottomSheetHelper = new BottomSheetHelper(context, z ? R.layout.bottom_sheet_picker_bottom_sheet : R.layout.bottom_sheet_picker);
        this.bottomSheetHelper = bottomSheetHelper;
        bottomSheetHelper.setListener(new BottomSheetHelper.Listener() { // from class: com.github.florent37.singledateandtimepicker.dialog.SingleDateAndTimePickerDialog.1
            @Override // com.github.florent37.singledateandtimepicker.dialog.BottomSheetHelper.Listener
            public void onOpen() {
            }

            @Override // com.github.florent37.singledateandtimepicker.dialog.BottomSheetHelper.Listener
            public void onLoaded(View view) {
                SingleDateAndTimePickerDialog.this.init(view);
                if (SingleDateAndTimePickerDialog.this.displayListener != null) {
                    SingleDateAndTimePickerDialog.this.displayListener.onDisplayed(SingleDateAndTimePickerDialog.this.picker);
                }
            }

            @Override // com.github.florent37.singledateandtimepicker.dialog.BottomSheetHelper.Listener
            public void onClose() {
                SingleDateAndTimePickerDialog.this.onClose();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void init(View view) {
        SingleDateAndTimePicker singleDateAndTimePicker = (SingleDateAndTimePicker) view.findViewById(R.id.picker);
        this.picker = singleDateAndTimePicker;
        if (singleDateAndTimePicker != null && this.bottomSheetHeight != null) {
            ViewGroup.LayoutParams layoutParams = singleDateAndTimePicker.getLayoutParams();
            layoutParams.height = this.bottomSheetHeight.intValue();
            this.picker.setLayoutParams(layoutParams);
        }
        TextView textView = (TextView) view.findViewById(R.id.buttonOk);
        if (textView != null) {
            textView.setOnClickListener(new View.OnClickListener() { // from class: com.github.florent37.singledateandtimepicker.dialog.SingleDateAndTimePickerDialog.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    SingleDateAndTimePickerDialog.this.okClicked = true;
                    SingleDateAndTimePickerDialog.this.close();
                }
            });
            if (this.mainColor != null) {
                textView.setTextColor(this.mainColor.intValue());
            }
            Integer num = this.titleTextSize;
            if (num != null) {
                textView.setTextSize(num.intValue());
            }
        }
        View findViewById = view.findViewById(R.id.sheetContentLayout);
        if (findViewById != null) {
            findViewById.setOnClickListener(new View.OnClickListener() { // from class: com.github.florent37.singledateandtimepicker.dialog.SingleDateAndTimePickerDialog.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                }
            });
            if (this.backgroundColor != null) {
                findViewById.setBackgroundColor(this.backgroundColor.intValue());
            }
        }
        TextView textView2 = (TextView) view.findViewById(R.id.sheetTitle);
        if (textView2 != null) {
            textView2.setText(this.title);
            if (this.titleTextColor != null) {
                textView2.setTextColor(this.titleTextColor.intValue());
            }
            Integer num2 = this.titleTextSize;
            if (num2 != null) {
                textView2.setTextSize(num2.intValue());
            }
        }
        this.picker.setTodayText(this.todayText);
        View findViewById2 = view.findViewById(R.id.pickerTitleHeader);
        if (this.mainColor != null && findViewById2 != null) {
            findViewById2.setBackgroundColor(this.mainColor.intValue());
        }
        if (this.curved) {
            this.picker.setCurved(true);
            this.picker.setVisibleItemCount(7);
        } else {
            this.picker.setCurved(false);
            this.picker.setVisibleItemCount(5);
        }
        this.picker.setMustBeOnFuture(this.mustBeOnFuture);
        this.picker.setStepMinutes(this.minutesStep);
        if (this.dayFormatter != null) {
            this.picker.setDayFormatter(this.dayFormatter);
        }
        if (this.mainColor != null) {
            this.picker.setSelectedTextColor(this.mainColor.intValue());
        }
        if (this.minDate != null) {
            this.picker.setMinDate(this.minDate);
        }
        if (this.maxDate != null) {
            this.picker.setMaxDate(this.maxDate);
        }
        if (this.defaultDate != null) {
            this.picker.setDefaultDate(this.defaultDate);
        }
        if (this.isAmPm != null) {
            this.picker.setIsAmPm(this.isAmPm.booleanValue());
        }
        this.picker.setDisplayDays(this.displayDays);
        this.picker.setDisplayYears(this.displayYears);
        this.picker.setDisplayMonths(this.displayMonth);
        this.picker.setDisplayDaysOfMonth(this.displayDaysOfMonth);
        this.picker.setDisplayMinutes(this.displayMinutes);
        this.picker.setDisplayHours(this.displayHours);
        this.picker.setDisplayMonthNumbers(this.displayMonthNumbers);
    }

    public SingleDateAndTimePickerDialog setListener(Listener listener) {
        this.listener = listener;
        return this;
    }

    public SingleDateAndTimePickerDialog setCurved(boolean z) {
        this.curved = z;
        return this;
    }

    public SingleDateAndTimePickerDialog setMinutesStep(int i) {
        this.minutesStep = i;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDisplayListener(DisplayListener displayListener) {
        this.displayListener = displayListener;
    }

    public SingleDateAndTimePickerDialog setTitle(String str) {
        this.title = str;
        return this;
    }

    public SingleDateAndTimePickerDialog setTitleTextSize(Integer num) {
        this.titleTextSize = num;
        return this;
    }

    public SingleDateAndTimePickerDialog setBottomSheetHeight(Integer num) {
        this.bottomSheetHeight = num;
        return this;
    }

    public SingleDateAndTimePickerDialog setTodayText(String str) {
        this.todayText = str;
        return this;
    }

    public SingleDateAndTimePickerDialog setMustBeOnFuture(boolean z) {
        this.mustBeOnFuture = z;
        return this;
    }

    public SingleDateAndTimePickerDialog setMinDateRange(Date date) {
        this.minDate = date;
        return this;
    }

    public SingleDateAndTimePickerDialog setMaxDateRange(Date date) {
        this.maxDate = date;
        return this;
    }

    public SingleDateAndTimePickerDialog setDefaultDate(Date date) {
        this.defaultDate = date;
        return this;
    }

    public SingleDateAndTimePickerDialog setDisplayDays(boolean z) {
        this.displayDays = z;
        return this;
    }

    public SingleDateAndTimePickerDialog setDisplayMinutes(boolean z) {
        this.displayMinutes = z;
        return this;
    }

    public SingleDateAndTimePickerDialog setDisplayMonthNumbers(boolean z) {
        this.displayMonthNumbers = z;
        return this;
    }

    public SingleDateAndTimePickerDialog setDisplayHours(boolean z) {
        this.displayHours = z;
        return this;
    }

    public SingleDateAndTimePickerDialog setDisplayDaysOfMonth(boolean z) {
        this.displayDaysOfMonth = z;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public SingleDateAndTimePickerDialog setDisplayMonth(boolean z) {
        this.displayMonth = z;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public SingleDateAndTimePickerDialog setDisplayYears(boolean z) {
        this.displayYears = z;
        return this;
    }

    public SingleDateAndTimePickerDialog setDayFormatter(SimpleDateFormat simpleDateFormat) {
        this.dayFormatter = simpleDateFormat;
        return this;
    }

    public SingleDateAndTimePickerDialog setIsAmPm(boolean z) {
        this.isAmPm = Boolean.valueOf(z);
        return this;
    }

    @Override // com.github.florent37.singledateandtimepicker.dialog.BaseDialog
    public void display() {
        super.display();
        this.bottomSheetHelper.display();
    }

    @Override // com.github.florent37.singledateandtimepicker.dialog.BaseDialog
    public void close() {
        super.close();
        this.bottomSheetHelper.hide();
        if (this.listener == null || !this.okClicked) {
            return;
        }
        this.listener.onDateSelected(this.picker.getDate());
    }

    @Override // com.github.florent37.singledateandtimepicker.dialog.BaseDialog
    public void dismiss() {
        super.dismiss();
        this.bottomSheetHelper.dismiss();
    }

    /* loaded from: classes.dex */
    public static class Builder {
        private boolean bottomSheet;
        private Integer bottomSheetHeight;
        private final Context context;
        private boolean curved;
        private SimpleDateFormat dayFormatter;
        private Date defaultDate;
        private SingleDateAndTimePickerDialog dialog;
        private DisplayListener displayListener;
        private Boolean isAmPm;
        private Listener listener;
        private Date maxDate;
        private Date minDate;
        private boolean mustBeOnFuture;
        private String title;
        private Integer titleTextSize;
        private String todayText;
        private int minutesStep = 5;
        private boolean displayDays = true;
        private boolean displayMinutes = true;
        private boolean displayHours = true;
        private boolean displayMonth = false;
        private boolean displayDaysOfMonth = false;
        private boolean displayYears = false;
        private boolean displayMonthNumbers = false;
        private Integer backgroundColor = null;
        private Integer mainColor = null;
        private Integer titleTextColor = null;

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

        public Builder minutesStep(int i) {
            this.minutesStep = i;
            return this;
        }

        public Builder displayDays(boolean z) {
            this.displayDays = z;
            return this;
        }

        public Builder displayAmPm(boolean z) {
            this.isAmPm = Boolean.valueOf(z);
            return this;
        }

        public Builder displayMinutes(boolean z) {
            this.displayMinutes = z;
            return this;
        }

        public Builder displayHours(boolean z) {
            this.displayHours = z;
            return this;
        }

        public Builder displayDaysOfMonth(boolean z) {
            this.displayDaysOfMonth = z;
            return this;
        }

        public Builder displayMonth(boolean z) {
            this.displayMonth = z;
            return this;
        }

        public Builder displayYears(boolean z) {
            this.displayYears = z;
            return this;
        }

        public Builder listener(Listener listener) {
            this.listener = listener;
            return this;
        }

        public Builder displayListener(DisplayListener displayListener) {
            this.displayListener = displayListener;
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

        public Builder displayMonthNumbers(boolean z) {
            this.displayMonthNumbers = z;
            return this;
        }

        public Builder defaultDate(Date date) {
            this.defaultDate = date;
            return this;
        }

        public Builder setDayFormatter(SimpleDateFormat simpleDateFormat) {
            this.dayFormatter = simpleDateFormat;
            return this;
        }

        public SingleDateAndTimePickerDialog build() {
            SingleDateAndTimePickerDialog mustBeOnFuture = new SingleDateAndTimePickerDialog(this.context, this.bottomSheet).setTitle(this.title).setTitleTextSize(this.titleTextSize).setBottomSheetHeight(this.bottomSheetHeight).setTodayText(this.todayText).setListener(this.listener).setCurved(this.curved).setMinutesStep(this.minutesStep).setMaxDateRange(this.maxDate).setMinDateRange(this.minDate).setDefaultDate(this.defaultDate).setDisplayHours(this.displayHours).setDisplayMonth(this.displayMonth).setDisplayYears(this.displayYears).setDisplayDaysOfMonth(this.displayDaysOfMonth).setDisplayMinutes(this.displayMinutes).setDisplayMonthNumbers(this.displayMonthNumbers).setDisplayDays(this.displayDays).setDayFormatter(this.dayFormatter).setMustBeOnFuture(this.mustBeOnFuture);
            Integer num = this.mainColor;
            if (num != null) {
                mustBeOnFuture.setMainColor(num);
            }
            Integer num2 = this.backgroundColor;
            if (num2 != null) {
                mustBeOnFuture.setBackgroundColor(num2);
            }
            Integer num3 = this.titleTextColor;
            if (num3 != null) {
                mustBeOnFuture.setTitleTextColor(num3.intValue());
            }
            DisplayListener displayListener = this.displayListener;
            if (displayListener != null) {
                mustBeOnFuture.setDisplayListener(displayListener);
            }
            Boolean bool = this.isAmPm;
            if (bool != null) {
                mustBeOnFuture.setIsAmPm(bool.booleanValue());
            }
            return mustBeOnFuture;
        }

        public void display() {
            SingleDateAndTimePickerDialog build = build();
            this.dialog = build;
            build.display();
        }

        public void close() {
            SingleDateAndTimePickerDialog singleDateAndTimePickerDialog = this.dialog;
            if (singleDateAndTimePickerDialog != null) {
                singleDateAndTimePickerDialog.close();
            }
        }

        public void dismiss() {
            SingleDateAndTimePickerDialog singleDateAndTimePickerDialog = this.dialog;
            if (singleDateAndTimePickerDialog != null) {
                singleDateAndTimePickerDialog.dismiss();
            }
        }
    }
}
