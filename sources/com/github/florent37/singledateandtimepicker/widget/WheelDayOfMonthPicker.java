package com.github.florent37.singledateandtimepicker.widget;

import android.content.Context;
import android.util.AttributeSet;
import com.github.florent37.singledateandtimepicker.DateHelper;
import com.google.android.material.timepicker.TimeModel;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public class WheelDayOfMonthPicker extends WheelPicker<String> {
    private int daysInMonth;
    private FinishedLoopListener finishedLoopListener;
    private DayOfMonthSelectedListener listener;

    /* loaded from: classes.dex */
    public interface DayOfMonthSelectedListener {
        void onDayOfMonthSelected(WheelDayOfMonthPicker wheelDayOfMonthPicker, int i);
    }

    /* loaded from: classes.dex */
    public interface FinishedLoopListener {
        void onFinishedLoop(WheelDayOfMonthPicker wheelDayOfMonthPicker);
    }

    @Override // com.github.florent37.singledateandtimepicker.widget.WheelPicker
    protected void init() {
    }

    public WheelDayOfMonthPicker(Context context) {
        this(context, null);
    }

    public WheelDayOfMonthPicker(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // com.github.florent37.singledateandtimepicker.widget.WheelPicker
    protected List<String> generateAdapterValues() {
        ArrayList arrayList = new ArrayList();
        for (int i = 1; i <= this.daysInMonth; i++) {
            arrayList.add(String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(i)));
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.github.florent37.singledateandtimepicker.widget.WheelPicker
    public String initDefault() {
        return String.valueOf(DateHelper.getDay(DateHelper.today()));
    }

    public void setOnFinishedLoopListener(FinishedLoopListener finishedLoopListener) {
        this.finishedLoopListener = finishedLoopListener;
    }

    @Override // com.github.florent37.singledateandtimepicker.widget.WheelPicker
    protected void onFinishedLoop() {
        super.onFinishedLoop();
        FinishedLoopListener finishedLoopListener = this.finishedLoopListener;
        if (finishedLoopListener != null) {
            finishedLoopListener.onFinishedLoop(this);
        }
    }

    public void setDayOfMonthSelectedListener(DayOfMonthSelectedListener dayOfMonthSelectedListener) {
        this.listener = dayOfMonthSelectedListener;
    }

    public int getDaysInMonth() {
        return this.daysInMonth;
    }

    public void setDaysInMonth(int i) {
        this.daysInMonth = i;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.github.florent37.singledateandtimepicker.widget.WheelPicker
    public void onItemSelected(int i, String str) {
        DayOfMonthSelectedListener dayOfMonthSelectedListener = this.listener;
        if (dayOfMonthSelectedListener != null) {
            dayOfMonthSelectedListener.onDayOfMonthSelected(this, i);
        }
    }

    public int getCurrentDay() {
        return getCurrentItemPosition();
    }
}
