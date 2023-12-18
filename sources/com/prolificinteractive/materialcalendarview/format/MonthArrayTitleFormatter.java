package com.prolificinteractive.materialcalendarview.format;

import android.text.SpannableStringBuilder;
import com.prolificinteractive.materialcalendarview.CalendarDay;
/* loaded from: classes2.dex */
public class MonthArrayTitleFormatter implements TitleFormatter {
    private final CharSequence[] monthLabels;

    public MonthArrayTitleFormatter(CharSequence[] charSequenceArr) {
        if (charSequenceArr == null) {
            throw new IllegalArgumentException("Label array cannot be null");
        }
        if (charSequenceArr.length < 12) {
            throw new IllegalArgumentException("Label array is too short");
        }
        this.monthLabels = charSequenceArr;
    }

    @Override // com.prolificinteractive.materialcalendarview.format.TitleFormatter
    public CharSequence format(CalendarDay calendarDay) {
        return new SpannableStringBuilder().append(this.monthLabels[calendarDay.getMonth()]).append((CharSequence) " ").append((CharSequence) String.valueOf(calendarDay.getYear()));
    }
}
