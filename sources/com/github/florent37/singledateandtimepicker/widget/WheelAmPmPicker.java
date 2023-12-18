package com.github.florent37.singledateandtimepicker.widget;

import android.content.Context;
import android.util.AttributeSet;
import com.github.florent37.singledateandtimepicker.DateHelper;
import com.github.florent37.singledateandtimepicker.R;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
/* loaded from: classes.dex */
public class WheelAmPmPicker extends WheelPicker<String> {
    public static final int INDEX_AM = 0;
    public static final int INDEX_PM = 1;
    private AmPmListener amPmListener;

    /* loaded from: classes.dex */
    public interface AmPmListener {
        void onAmPmChanged(WheelAmPmPicker wheelAmPmPicker, boolean z);
    }

    @Override // com.github.florent37.singledateandtimepicker.widget.WheelPicker
    protected void init() {
    }

    public boolean isAmPosition(int i) {
        return i == 0;
    }

    public WheelAmPmPicker(Context context) {
        super(context);
    }

    public WheelAmPmPicker(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.github.florent37.singledateandtimepicker.widget.WheelPicker
    public String initDefault() {
        if (DateHelper.getHour(DateHelper.today(), true) >= 12) {
            return getContext().getString(R.string.picker_pm);
        }
        return getContext().getString(R.string.picker_am);
    }

    @Override // com.github.florent37.singledateandtimepicker.widget.WheelPicker
    protected List<String> generateAdapterValues() {
        return Arrays.asList(getContext().getString(R.string.picker_am), getContext().getString(R.string.picker_pm));
    }

    @Override // com.github.florent37.singledateandtimepicker.widget.WheelPicker
    public int findIndexOfDate(Date date) {
        return date.getHours() >= 12 ? 1 : 0;
    }

    public void setAmPmListener(AmPmListener amPmListener) {
        this.amPmListener = amPmListener;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.github.florent37.singledateandtimepicker.widget.WheelPicker
    public void onItemSelected(int i, String str) {
        super.onItemSelected(i, (int) str);
        AmPmListener amPmListener = this.amPmListener;
        if (amPmListener != null) {
            amPmListener.onAmPmChanged(this, isAm());
        }
    }

    @Override // com.github.florent37.singledateandtimepicker.widget.WheelPicker
    public void setCyclic(boolean z) {
        super.setCyclic(false);
    }

    @Override // com.github.florent37.singledateandtimepicker.widget.WheelPicker
    protected String getFormattedValue(Object obj) {
        if (obj instanceof Date) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime((Date) obj);
            return getResources().getString(calendar.get(9) == 1 ? R.string.picker_pm : R.string.picker_am);
        }
        return String.valueOf(obj);
    }

    public boolean isAm() {
        return getCurrentItemPosition() == 0;
    }

    public boolean isPm() {
        return getCurrentItemPosition() == 1;
    }
}
