package com.github.florent37.singledateandtimepicker.widget;

import android.content.Context;
import android.util.AttributeSet;
import com.github.florent37.singledateandtimepicker.DateHelper;
import com.google.android.material.timepicker.TimeModel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
/* loaded from: classes.dex */
public class WheelMonthPicker extends WheelPicker<String> {
    private boolean displayMonthNumbers;
    private int lastScrollPosition;
    private MonthSelectedListener listener;

    /* loaded from: classes.dex */
    public interface MonthSelectedListener {
        void onMonthSelected(WheelMonthPicker wheelMonthPicker, int i, String str);
    }

    @Override // com.github.florent37.singledateandtimepicker.widget.WheelPicker
    protected void init() {
    }

    public WheelMonthPicker(Context context) {
        this(context, null);
    }

    public WheelMonthPicker(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.displayMonthNumbers = false;
    }

    @Override // com.github.florent37.singledateandtimepicker.widget.WheelPicker
    protected List<String> generateAdapterValues() {
        ArrayList arrayList = new ArrayList();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMMM", Locale.getDefault());
        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        calendar.set(5, 1);
        for (int i = 0; i < 12; i++) {
            calendar.set(2, i);
            if (this.displayMonthNumbers) {
                arrayList.add(String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(i + 1)));
            } else {
                arrayList.add(simpleDateFormat.format(calendar.getTime()));
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.github.florent37.singledateandtimepicker.widget.WheelPicker
    public String initDefault() {
        return String.valueOf(DateHelper.getMonth(DateHelper.today()));
    }

    public void setOnMonthSelectedListener(MonthSelectedListener monthSelectedListener) {
        this.listener = monthSelectedListener;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.github.florent37.singledateandtimepicker.widget.WheelPicker
    public void onItemSelected(int i, String str) {
        MonthSelectedListener monthSelectedListener = this.listener;
        if (monthSelectedListener != null) {
            monthSelectedListener.onMonthSelected(this, i, str);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.github.florent37.singledateandtimepicker.widget.WheelPicker
    public void onItemCurrentScroll(int i, String str) {
        if (this.lastScrollPosition != i) {
            onItemSelected(i, str);
            this.lastScrollPosition = i;
        }
    }

    public boolean displayMonthNumbers() {
        return this.displayMonthNumbers;
    }

    public void setDisplayMonthNumbers(boolean z) {
        this.displayMonthNumbers = z;
    }

    public int getCurrentMonth() {
        return getCurrentItemPosition();
    }
}
