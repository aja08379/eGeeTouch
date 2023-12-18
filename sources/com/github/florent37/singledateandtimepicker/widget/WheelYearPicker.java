package com.github.florent37.singledateandtimepicker.widget;

import android.content.Context;
import android.util.AttributeSet;
import com.github.florent37.singledateandtimepicker.R;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
/* loaded from: classes.dex */
public class WheelYearPicker extends WheelPicker<String> {
    protected int maxYear;
    protected int minYear;
    private OnYearSelectedListener onYearSelectedListener;
    private SimpleDateFormat simpleDateFormat;

    /* loaded from: classes.dex */
    public interface OnYearSelectedListener {
        void onYearSelected(WheelYearPicker wheelYearPicker, int i, int i2);
    }

    public WheelYearPicker(Context context) {
        super(context);
    }

    public WheelYearPicker(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // com.github.florent37.singledateandtimepicker.widget.WheelPicker
    protected void init() {
        this.simpleDateFormat = new SimpleDateFormat("yyyy", getCurrentLocale());
        int i = Calendar.getInstance().get(1);
        this.minYear = i - 100;
        this.maxYear = i + 100;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.github.florent37.singledateandtimepicker.widget.WheelPicker
    public String initDefault() {
        return getTodayText();
    }

    private String getTodayText() {
        return getResources().getString(R.string.picker_today);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.github.florent37.singledateandtimepicker.widget.WheelPicker
    public void onItemSelected(int i, String str) {
        if (this.onYearSelectedListener != null) {
            this.onYearSelectedListener.onYearSelected(this, i, convertItemToYear(i));
        }
    }

    public void setMaxYear(int i) {
        this.maxYear = i;
        notifyDatasetChanged();
    }

    public void setMinYear(int i) {
        this.minYear = i;
        notifyDatasetChanged();
    }

    @Override // com.github.florent37.singledateandtimepicker.widget.WheelPicker
    protected List<String> generateAdapterValues() {
        ArrayList arrayList = new ArrayList();
        Calendar calendar = Calendar.getInstance();
        calendar.set(1, this.minYear - 1);
        for (int i = this.minYear; i <= this.maxYear; i++) {
            calendar.add(1, 1);
            arrayList.add(getFormattedValue(calendar.getTime()));
        }
        return arrayList;
    }

    @Override // com.github.florent37.singledateandtimepicker.widget.WheelPicker
    protected String getFormattedValue(Object obj) {
        return this.simpleDateFormat.format(obj);
    }

    public void setOnYearSelectedListener(OnYearSelectedListener onYearSelectedListener) {
        this.onYearSelectedListener = onYearSelectedListener;
    }

    public int getCurrentYear() {
        return convertItemToYear(super.getCurrentItemPosition());
    }

    private int convertItemToYear(int i) {
        return this.minYear + i;
    }
}
