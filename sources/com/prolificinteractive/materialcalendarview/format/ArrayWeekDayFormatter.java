package com.prolificinteractive.materialcalendarview.format;
/* loaded from: classes2.dex */
public class ArrayWeekDayFormatter implements WeekDayFormatter {
    private final CharSequence[] weekDayLabels;

    public ArrayWeekDayFormatter(CharSequence[] charSequenceArr) {
        if (charSequenceArr == null) {
            throw new IllegalArgumentException("Cannot be null");
        }
        if (charSequenceArr.length != 7) {
            throw new IllegalArgumentException("Array must contain exactly 7 elements");
        }
        this.weekDayLabels = charSequenceArr;
    }

    @Override // com.prolificinteractive.materialcalendarview.format.WeekDayFormatter
    public CharSequence format(int i) {
        return this.weekDayLabels[i - 1];
    }
}
