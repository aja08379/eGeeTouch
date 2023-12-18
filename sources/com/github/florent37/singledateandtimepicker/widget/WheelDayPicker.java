package com.github.florent37.singledateandtimepicker.widget;

import android.content.Context;
import android.util.AttributeSet;
import com.github.florent37.singledateandtimepicker.DateHelper;
import com.github.florent37.singledateandtimepicker.R;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
/* loaded from: classes.dex */
public class WheelDayPicker extends WheelPicker<String> {
    private OnDaySelectedListener onDaySelectedListener;
    private SimpleDateFormat simpleDateFormat;

    /* loaded from: classes.dex */
    public interface OnDaySelectedListener {
        void onDaySelected(WheelDayPicker wheelDayPicker, int i, String str, Date date);
    }

    public WheelDayPicker(Context context) {
        super(context);
    }

    public WheelDayPicker(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // com.github.florent37.singledateandtimepicker.widget.WheelPicker
    protected void init() {
        this.simpleDateFormat = new SimpleDateFormat("EEE d MMM", getCurrentLocale());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.github.florent37.singledateandtimepicker.widget.WheelPicker
    public String initDefault() {
        return getTodayText();
    }

    private String getTodayText() {
        return getResources().getString(R.string.picker_today);
    }

    public WheelDayPicker setDayFormatter(SimpleDateFormat simpleDateFormat) {
        this.simpleDateFormat = simpleDateFormat;
        this.adapter.setData(generateAdapterValues());
        notifyDatasetChanged();
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.github.florent37.singledateandtimepicker.widget.WheelPicker
    public void onItemSelected(int i, String str) {
        if (this.onDaySelectedListener != null) {
            this.onDaySelectedListener.onDaySelected(this, i, str, convertItemToDate(i));
        }
    }

    @Override // com.github.florent37.singledateandtimepicker.widget.WheelPicker
    protected List<String> generateAdapterValues() {
        ArrayList arrayList = new ArrayList();
        Calendar calendar = Calendar.getInstance();
        calendar.add(5, -365);
        for (int i = -364; i < 0; i++) {
            calendar.add(5, 1);
            arrayList.add(getFormattedValue(calendar.getTime()));
        }
        arrayList.add(getTodayText());
        Calendar calendar2 = Calendar.getInstance();
        for (int i2 = 0; i2 < 364; i2++) {
            calendar2.add(5, 1);
            arrayList.add(getFormattedValue(calendar2.getTime()));
        }
        return arrayList;
    }

    @Override // com.github.florent37.singledateandtimepicker.widget.WheelPicker
    protected String getFormattedValue(Object obj) {
        return this.simpleDateFormat.format(obj);
    }

    public void setOnDaySelectedListener(OnDaySelectedListener onDaySelectedListener) {
        this.onDaySelectedListener = onDaySelectedListener;
    }

    public Date getCurrentDate() {
        return convertItemToDate(super.getCurrentItemPosition());
    }

    private Date convertItemToDate(int i) {
        Date date;
        String itemText = this.adapter.getItemText(i);
        Calendar calendar = Calendar.getInstance();
        int indexOf = this.adapter.getData().indexOf(getTodayText());
        if (getTodayText().equals(itemText)) {
            date = calendar.getTime();
        } else {
            try {
                date = this.simpleDateFormat.parse(itemText);
            } catch (ParseException e) {
                e.printStackTrace();
                date = null;
            }
        }
        if (date != null) {
            Calendar calendarOfDate = DateHelper.getCalendarOfDate(date);
            calendar.add(5, i - indexOf);
            calendarOfDate.set(1, calendar.get(1));
            return calendarOfDate.getTime();
        }
        return date;
    }

    public void setTodayText(String str) {
        int indexOf = this.adapter.getData().indexOf(getTodayText());
        if (indexOf != -1) {
            this.adapter.getData().set(indexOf, str);
            notifyDatasetChanged();
        }
    }
}
