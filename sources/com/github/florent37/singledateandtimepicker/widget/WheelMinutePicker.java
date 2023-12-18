package com.github.florent37.singledateandtimepicker.widget;

import android.content.Context;
import android.util.AttributeSet;
import com.github.florent37.singledateandtimepicker.DateHelper;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
/* loaded from: classes.dex */
public class WheelMinutePicker extends WheelPicker<String> {
    private OnFinishedLoopListener onFinishedLoopListener;
    private OnMinuteChangedListener onMinuteChangedListener;
    private int stepMinutes;

    /* loaded from: classes.dex */
    public interface OnFinishedLoopListener {
        void onFinishedLoop(WheelMinutePicker wheelMinutePicker);
    }

    /* loaded from: classes.dex */
    public interface OnMinuteChangedListener {
        void onMinuteChanged(WheelMinutePicker wheelMinutePicker, int i);
    }

    public WheelMinutePicker(Context context) {
        super(context);
    }

    public WheelMinutePicker(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // com.github.florent37.singledateandtimepicker.widget.WheelPicker
    protected void init() {
        this.stepMinutes = 5;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.github.florent37.singledateandtimepicker.widget.WheelPicker
    public String initDefault() {
        return getFormattedValue(Integer.valueOf(Calendar.getInstance().get(12)));
    }

    @Override // com.github.florent37.singledateandtimepicker.widget.WheelPicker
    protected List<String> generateAdapterValues() {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (i <= 59) {
            arrayList.add(getFormattedValue(Integer.valueOf(i)));
            i += this.stepMinutes;
        }
        return arrayList;
    }

    private int findIndexOfMinute(int i) {
        int itemCount = this.adapter.getItemCount();
        for (int i2 = 0; i2 < itemCount; i2++) {
            Integer valueOf = Integer.valueOf(this.adapter.getItemText(i2));
            if (i == valueOf.intValue()) {
                return i2;
            }
            if (i < valueOf.intValue()) {
                return i2 - 1;
            }
        }
        return itemCount - 1;
    }

    @Override // com.github.florent37.singledateandtimepicker.widget.WheelPicker
    public int findIndexOfDate(Date date) {
        return findIndexOfMinute(DateHelper.getMinuteOf(date));
    }

    @Override // com.github.florent37.singledateandtimepicker.widget.WheelPicker
    protected String getFormattedValue(Object obj) {
        if (obj instanceof Date) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime((Date) obj);
            obj = Integer.valueOf(calendar.get(12));
        }
        return String.format(getCurrentLocale(), "%1$02d", obj);
    }

    public void setStepMinutes(int i) {
        if (i >= 60 || i <= 0) {
            return;
        }
        this.stepMinutes = i;
        updateAdapter();
    }

    private int convertItemToMinute(Object obj) {
        return Integer.valueOf(String.valueOf(obj)).intValue();
    }

    public int getCurrentMinute() {
        return convertItemToMinute(this.adapter.getItem(getCurrentItemPosition()));
    }

    public WheelMinutePicker setOnMinuteChangedListener(OnMinuteChangedListener onMinuteChangedListener) {
        this.onMinuteChangedListener = onMinuteChangedListener;
        return this;
    }

    public WheelMinutePicker setOnFinishedLoopListener(OnFinishedLoopListener onFinishedLoopListener) {
        this.onFinishedLoopListener = onFinishedLoopListener;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.github.florent37.singledateandtimepicker.widget.WheelPicker
    public void onItemSelected(int i, String str) {
        super.onItemSelected(i, (int) str);
        OnMinuteChangedListener onMinuteChangedListener = this.onMinuteChangedListener;
        if (onMinuteChangedListener != null) {
            onMinuteChangedListener.onMinuteChanged(this, convertItemToMinute(str));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.github.florent37.singledateandtimepicker.widget.WheelPicker
    public void onFinishedLoop() {
        super.onFinishedLoop();
        OnFinishedLoopListener onFinishedLoopListener = this.onFinishedLoopListener;
        if (onFinishedLoopListener != null) {
            onFinishedLoopListener.onFinishedLoop(this);
        }
    }
}
