package com.appeaser.sublimepickerlibrary.recurrencepicker;

import android.content.Context;
import android.content.res.Resources;
import android.text.format.DateUtils;
import android.text.format.Time;
import android.util.TimeFormatException;
import com.appeaser.sublimepickerlibrary.R;
/* loaded from: classes.dex */
public class EventRecurrenceFormatter {
    private static int[] mMonthRepeatByDayOfWeekIds;
    private static String[][] mMonthRepeatByDayOfWeekStrs;

    public static String getRepeatString(Context context, Resources resources, EventRecurrence eventRecurrence, boolean z) {
        String str;
        String dayToString;
        String quantityString;
        int i;
        String quantityString2;
        if (z) {
            StringBuilder sb = new StringBuilder();
            if (eventRecurrence.until != null) {
                try {
                    Time time = new Time();
                    time.parse(eventRecurrence.until);
                    sb.append(resources.getString(R.string.endByDate, DateUtils.formatDateTime(context, time.toMillis(false), 131072)));
                } catch (TimeFormatException unused) {
                }
            }
            if (eventRecurrence.count > 0) {
                sb.append(resources.getQuantityString(R.plurals.endByCount, eventRecurrence.count, Integer.valueOf(eventRecurrence.count)));
            }
            str = sb.toString();
        } else {
            str = "";
        }
        int i2 = eventRecurrence.interval <= 1 ? 1 : eventRecurrence.interval;
        int i3 = eventRecurrence.freq;
        if (i3 != 4) {
            if (i3 != 5) {
                if (i3 == 6) {
                    if (i2 == 1) {
                        quantityString = resources.getString(R.string.monthly);
                    } else {
                        quantityString = resources.getQuantityString(R.plurals.recurrence_interval_monthly, i2, Integer.valueOf(i2));
                    }
                    if (eventRecurrence.bydayCount == 1) {
                        cacheMonthRepeatStrings(resources, eventRecurrence.startDate.weekDay);
                        return quantityString + " (" + mMonthRepeatByDayOfWeekStrs[i][(eventRecurrence.startDate.monthDay - 1) / 7] + ")" + str;
                    }
                    return quantityString + str;
                } else {
                    if (i3 != 7) {
                        return null;
                    }
                    if (i2 == 1) {
                        quantityString2 = resources.getString(R.string.yearly_plain);
                    } else {
                        quantityString2 = resources.getQuantityString(R.plurals.recurrence_interval_yearly, i2, Integer.valueOf(i2));
                    }
                    return quantityString2 + str;
                }
            }
            if (eventRecurrence.repeatsOnEveryWeekDay()) {
                return resources.getString(R.string.every_weekday) + str;
            }
            int i4 = eventRecurrence.bydayCount == 1 ? 10 : 20;
            StringBuilder sb2 = new StringBuilder();
            if (eventRecurrence.bydayCount > 0) {
                int i5 = eventRecurrence.bydayCount - 1;
                for (int i6 = 0; i6 < i5; i6++) {
                    sb2.append(dayToString(eventRecurrence.byday[i6], i4));
                    sb2.append(", ");
                }
                sb2.append(dayToString(eventRecurrence.byday[i5], i4));
                dayToString = sb2.toString();
            } else if (eventRecurrence.startDate == null) {
                return null;
            } else {
                dayToString = dayToString(EventRecurrence.timeDay2Day(eventRecurrence.startDate.weekDay), 10);
            }
            return resources.getQuantityString(R.plurals.weekly, i2, Integer.valueOf(i2), dayToString) + str;
        }
        return resources.getQuantityString(R.plurals.daily, i2, Integer.valueOf(i2)) + str;
    }

    private static void cacheMonthRepeatStrings(Resources resources, int i) {
        if (mMonthRepeatByDayOfWeekIds == null) {
            int[] iArr = new int[7];
            mMonthRepeatByDayOfWeekIds = iArr;
            iArr[0] = R.array.repeat_by_nth_sun;
            mMonthRepeatByDayOfWeekIds[1] = R.array.repeat_by_nth_mon;
            mMonthRepeatByDayOfWeekIds[2] = R.array.repeat_by_nth_tues;
            mMonthRepeatByDayOfWeekIds[3] = R.array.repeat_by_nth_wed;
            mMonthRepeatByDayOfWeekIds[4] = R.array.repeat_by_nth_thurs;
            mMonthRepeatByDayOfWeekIds[5] = R.array.repeat_by_nth_fri;
            mMonthRepeatByDayOfWeekIds[6] = R.array.repeat_by_nth_sat;
        }
        if (mMonthRepeatByDayOfWeekStrs == null) {
            mMonthRepeatByDayOfWeekStrs = new String[7];
        }
        String[][] strArr = mMonthRepeatByDayOfWeekStrs;
        if (strArr[i] == null) {
            strArr[i] = resources.getStringArray(mMonthRepeatByDayOfWeekIds[i]);
        }
    }

    private static String dayToString(int i, int i2) {
        return DateUtils.getDayOfWeekString(dayToUtilDay(i), i2);
    }

    private static int dayToUtilDay(int i) {
        if (i != 65536) {
            if (i != 131072) {
                if (i != 262144) {
                    if (i != 524288) {
                        if (i != 1048576) {
                            if (i != 2097152) {
                                if (i == 4194304) {
                                    return 7;
                                }
                                throw new IllegalArgumentException("bad day argument: " + i);
                            }
                            return 6;
                        }
                        return 5;
                    }
                    return 4;
                }
                return 3;
            }
            return 2;
        }
        return 1;
    }
}
