package com.github.florent37.singledateandtimepicker.dialog;

import java.text.SimpleDateFormat;
import java.util.Date;
/* loaded from: classes.dex */
public abstract class BaseDialog {
    public static final int DEFAULT_ITEM_COUNT_MODE_CURVED = 7;
    public static final int DEFAULT_ITEM_COUNT_MODE_NORMAL = 5;
    protected SimpleDateFormat dayFormatter;
    protected Date defaultDate;
    protected boolean displayDays;
    protected boolean displayDaysOfMonth;
    protected boolean displayHours;
    protected boolean displayMinutes;
    protected boolean displayMonth;
    protected boolean displayMonthNumbers;
    protected boolean displayYears;
    protected Boolean isAmPm;
    private boolean isDisplaying;
    protected Date maxDate;
    protected Date minDate;
    protected Integer backgroundColor = -1;
    protected Integer mainColor = -16776961;
    protected Integer titleTextColor = null;
    protected boolean okClicked = false;
    protected boolean curved = false;
    protected boolean mustBeOnFuture = false;
    protected int minutesStep = 5;

    public void display() {
        this.isDisplaying = true;
    }

    public void close() {
        this.isDisplaying = false;
    }

    public void dismiss() {
        this.isDisplaying = false;
    }

    public boolean isDisplaying() {
        return this.isDisplaying;
    }

    public void setBackgroundColor(Integer num) {
        this.backgroundColor = num;
    }

    public void setMainColor(Integer num) {
        this.mainColor = num;
    }

    public void setTitleTextColor(int i) {
        this.titleTextColor = Integer.valueOf(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onClose() {
        this.isDisplaying = false;
    }
}
