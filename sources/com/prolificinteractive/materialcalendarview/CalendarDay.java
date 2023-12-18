package com.prolificinteractive.materialcalendarview;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Calendar;
import java.util.Date;
/* loaded from: classes2.dex */
public final class CalendarDay implements Parcelable {
    public static final Parcelable.Creator<CalendarDay> CREATOR = new Parcelable.Creator<CalendarDay>() { // from class: com.prolificinteractive.materialcalendarview.CalendarDay.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CalendarDay createFromParcel(Parcel parcel) {
            return new CalendarDay(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CalendarDay[] newArray(int i) {
            return new CalendarDay[i];
        }
    };
    private transient Calendar _calendar;
    private transient Date _date;
    private final int day;
    private final int month;
    private final int year;

    private static int hashCode(int i, int i2, int i3) {
        return (i * 10000) + (i2 * 100) + i3;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public static CalendarDay today() {
        return from(CalendarUtils.getInstance());
    }

    public static CalendarDay from(int i, int i2, int i3) {
        return new CalendarDay(i, i2, i3);
    }

    public static CalendarDay from(Calendar calendar) {
        if (calendar == null) {
            return null;
        }
        return from(CalendarUtils.getYear(calendar), CalendarUtils.getMonth(calendar), CalendarUtils.getDay(calendar));
    }

    public static CalendarDay from(long j) {
        Calendar calendarUtils = CalendarUtils.getInstance();
        calendarUtils.setTimeInMillis(j);
        return from(calendarUtils);
    }

    @Deprecated
    public CalendarDay() {
        this(CalendarUtils.getInstance());
    }

    @Deprecated
    public CalendarDay(Calendar calendar) {
        this(CalendarUtils.getYear(calendar), CalendarUtils.getMonth(calendar), CalendarUtils.getDay(calendar));
    }

    @Deprecated
    public CalendarDay(int i, int i2, int i3) {
        this.year = i;
        this.month = i2;
        this.day = i3;
    }

    public int getYear() {
        return this.year;
    }

    public int getMonth() {
        return this.month;
    }

    public int getDay() {
        return this.day;
    }

    public Date getDate() {
        if (this._date == null) {
            this._date = getCalendar().getTime();
        }
        return this._date;
    }

    public Calendar getCalendar() {
        if (this._calendar == null) {
            Calendar calendarUtils = CalendarUtils.getInstance();
            this._calendar = calendarUtils;
            copyTo(calendarUtils);
        }
        return this._calendar;
    }

    void copyToMonthOnly(Calendar calendar) {
        calendar.set(this.year, this.month, 1);
    }

    public void copyTo(Calendar calendar) {
        calendar.set(this.year, this.month, this.day);
    }

    public boolean isInRange(CalendarDay calendarDay, CalendarDay calendarDay2) {
        return (calendarDay == null || !calendarDay.isAfter(this)) && (calendarDay2 == null || !calendarDay2.isBefore(this));
    }

    public boolean isBefore(CalendarDay calendarDay) {
        if (calendarDay == null) {
            throw new IllegalArgumentException("other cannot be null");
        }
        int i = this.year;
        int i2 = calendarDay.year;
        if (i != i2) {
            return i < i2;
        }
        int i3 = this.month;
        int i4 = calendarDay.month;
        if (i3 == i4) {
            if (this.day < calendarDay.day) {
                return true;
            }
        } else if (i3 < i4) {
            return true;
        }
        return false;
    }

    public boolean isAfter(CalendarDay calendarDay) {
        if (calendarDay == null) {
            throw new IllegalArgumentException("other cannot be null");
        }
        int i = this.year;
        int i2 = calendarDay.year;
        if (i != i2) {
            return i > i2;
        }
        int i3 = this.month;
        int i4 = calendarDay.month;
        if (i3 == i4) {
            if (this.day > calendarDay.day) {
                return true;
            }
        } else if (i3 > i4) {
            return true;
        }
        return false;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CalendarDay calendarDay = (CalendarDay) obj;
        return this.day == calendarDay.day && this.month == calendarDay.month && this.year == calendarDay.year;
    }

    public int hashCode() {
        return hashCode(this.year, this.month, this.day);
    }

    public String toString() {
        return "CalendarDay{" + this.year + "-" + this.month + "-" + this.day + "}";
    }

    public CalendarDay(Parcel parcel) {
        this(parcel.readInt(), parcel.readInt(), parcel.readInt());
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.year);
        parcel.writeInt(this.month);
        parcel.writeInt(this.day);
    }
}
