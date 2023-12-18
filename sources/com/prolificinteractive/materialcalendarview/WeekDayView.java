package com.prolificinteractive.materialcalendarview;

import android.content.Context;
import android.os.Build;
import androidx.appcompat.widget.AppCompatTextView;
import com.prolificinteractive.materialcalendarview.format.WeekDayFormatter;
import java.util.Calendar;
/* loaded from: classes2.dex */
class WeekDayView extends AppCompatTextView {
    private int dayOfWeek;
    private WeekDayFormatter formatter;

    public WeekDayView(Context context, int i) {
        super(context);
        this.formatter = WeekDayFormatter.DEFAULT;
        setGravity(17);
        if (Build.VERSION.SDK_INT >= 17) {
            setTextAlignment(4);
        }
        setDayOfWeek(i);
    }

    public void setWeekDayFormatter(WeekDayFormatter weekDayFormatter) {
        if (weekDayFormatter == null) {
            weekDayFormatter = WeekDayFormatter.DEFAULT;
        }
        this.formatter = weekDayFormatter;
        setDayOfWeek(this.dayOfWeek);
    }

    public void setDayOfWeek(int i) {
        this.dayOfWeek = i;
        setText(this.formatter.format(i));
    }

    public void setDayOfWeek(Calendar calendar) {
        setDayOfWeek(CalendarUtils.getDayOfWeek(calendar));
    }
}
