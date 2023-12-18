package com.github.florent37.singledateandtimepicker.widget;

import android.content.Context;
import android.util.AttributeSet;
import com.github.florent37.singledateandtimepicker.DateHelper;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
/* loaded from: classes.dex */
public class WheelHourPicker extends WheelPicker<String> {
    private FinishedLoopListener finishedLoopListener;
    private OnHourChangedListener hourChangedListener;
    private int hoursStep;
    protected boolean isAmPm;
    private int maxHour;
    private int minHour;

    /* loaded from: classes.dex */
    public interface FinishedLoopListener {
        void onFinishedLoop(WheelHourPicker wheelHourPicker);
    }

    /* loaded from: classes.dex */
    public interface OnHourChangedListener {
        void onHourChanged(WheelHourPicker wheelHourPicker, int i);
    }

    public WheelHourPicker(Context context) {
        super(context);
    }

    public WheelHourPicker(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // com.github.florent37.singledateandtimepicker.widget.WheelPicker
    protected void init() {
        this.isAmPm = false;
        this.minHour = 0;
        this.maxHour = 23;
        this.hoursStep = 1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.github.florent37.singledateandtimepicker.widget.WheelPicker
    public String initDefault() {
        return String.valueOf(DateHelper.getHour(DateHelper.today(), this.isAmPm));
    }

    @Override // com.github.florent37.singledateandtimepicker.widget.WheelPicker
    protected List<String> generateAdapterValues() {
        ArrayList arrayList = new ArrayList();
        if (this.isAmPm) {
            arrayList.add(getFormattedValue(12));
            int i = this.hoursStep;
            while (i < this.maxHour) {
                arrayList.add(getFormattedValue(Integer.valueOf(i)));
                i += this.hoursStep;
            }
        } else {
            int i2 = this.minHour;
            while (i2 <= this.maxHour) {
                arrayList.add(getFormattedValue(Integer.valueOf(i2)));
                i2 += this.hoursStep;
            }
        }
        return arrayList;
    }

    @Override // com.github.florent37.singledateandtimepicker.widget.WheelPicker
    public int findIndexOfDate(Date date) {
        int hours;
        if (this.isAmPm && (hours = date.getHours()) >= 12) {
            Date date2 = new Date(date.getTime());
            date2.setHours(hours % 12);
            return super.findIndexOfDate(date2);
        }
        return super.findIndexOfDate(date);
    }

    @Override // com.github.florent37.singledateandtimepicker.widget.WheelPicker
    protected String getFormattedValue(Object obj) {
        if (obj instanceof Date) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime((Date) obj);
            obj = Integer.valueOf(calendar.get(11));
        }
        return String.format(getCurrentLocale(), "%1$02d", obj);
    }

    @Override // com.github.florent37.singledateandtimepicker.widget.WheelPicker
    public void setDefault(String str) {
        try {
            int parseInt = Integer.parseInt(str);
            if (this.isAmPm && parseInt >= 12) {
                parseInt -= 12;
            }
            super.setDefault((WheelHourPicker) getFormattedValue(Integer.valueOf(parseInt)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setIsAmPm(boolean z) {
        this.isAmPm = z;
        if (z) {
            setMaxHour(12);
        } else {
            setMaxHour(23);
        }
        updateAdapter();
    }

    public void setMaxHour(int i) {
        if (i >= 0 && i <= 23) {
            this.maxHour = i;
        }
        notifyDatasetChanged();
    }

    public void setMinHour(int i) {
        if (i >= 0 && i <= 23) {
            this.minHour = i;
        }
        notifyDatasetChanged();
    }

    public void setHoursStep(int i) {
        if (i >= 0 && i <= 23) {
            this.hoursStep = i;
        }
        notifyDatasetChanged();
    }

    private int convertItemToHour(Object obj) {
        Integer valueOf = Integer.valueOf(String.valueOf(obj));
        if (!this.isAmPm) {
            return valueOf.intValue();
        }
        if (valueOf.intValue() == 12) {
            valueOf = 0;
        }
        return valueOf.intValue();
    }

    public int getCurrentHour() {
        return convertItemToHour(this.adapter.getItem(getCurrentItemPosition()));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.github.florent37.singledateandtimepicker.widget.WheelPicker
    public void onItemSelected(int i, String str) {
        super.onItemSelected(i, (int) str);
        OnHourChangedListener onHourChangedListener = this.hourChangedListener;
        if (onHourChangedListener != null) {
            onHourChangedListener.onHourChanged(this, convertItemToHour(str));
        }
    }

    public WheelHourPicker setOnFinishedLoopListener(FinishedLoopListener finishedLoopListener) {
        this.finishedLoopListener = finishedLoopListener;
        return this;
    }

    public WheelHourPicker setHourChangedListener(OnHourChangedListener onHourChangedListener) {
        this.hourChangedListener = onHourChangedListener;
        return this;
    }

    @Override // com.github.florent37.singledateandtimepicker.widget.WheelPicker
    protected void onFinishedLoop() {
        super.onFinishedLoop();
        FinishedLoopListener finishedLoopListener = this.finishedLoopListener;
        if (finishedLoopListener != null) {
            finishedLoopListener.onFinishedLoop(this);
        }
    }
}
