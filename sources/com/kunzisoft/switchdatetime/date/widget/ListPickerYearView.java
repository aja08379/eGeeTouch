package com.kunzisoft.switchdatetime.date.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.kunzisoft.switchdatetime.R;
import com.kunzisoft.switchdatetime.date.OnYearSelectedListener;
import com.kunzisoft.switchdatetime.date.widget.YearPickerAdapter;
import java.util.ArrayList;
/* loaded from: classes2.dex */
public class ListPickerYearView extends RecyclerView implements YearPickerAdapter.OnClickYearListener {
    private static final String TAG = "ListPickerYearView";
    private int currentYear;
    private YearPickerAdapter mAdapter;
    private int mChildSize;
    private int mViewSize;
    private int maxYear;
    private int minYear;
    private OnYearSelectedListener yearChangeListener;

    public ListPickerYearView(Context context) {
        this(context, null, 0);
    }

    public ListPickerYearView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ListPickerYearView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.minYear = 1970;
        this.maxYear = 2100;
        init(context, attributeSet);
    }

    private void init(Context context, AttributeSet attributeSet) {
        setLayoutManager(new LinearLayoutManager(context));
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.ListPickerYearView);
            setMinYear(obtainStyledAttributes.getInt(R.styleable.ListPickerYearView_minYear, this.minYear));
            setMaxYear(obtainStyledAttributes.getInt(R.styleable.ListPickerYearView_maxYear, this.minYear));
            this.currentYear = obtainStyledAttributes.getInt(R.styleable.ListPickerYearView_defaultYear, -1);
            obtainStyledAttributes.recycle();
        }
        setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
        Resources resources = context.getResources();
        this.mViewSize = resources.getDimensionPixelOffset(R.dimen.date_picker_view_animator_height);
        this.mChildSize = resources.getDimensionPixelOffset(R.dimen.year_label_height);
        setVerticalFadingEdgeEnabled(true);
        setFadingEdgeLength(this.mChildSize / 3);
        YearPickerAdapter yearPickerAdapter = new YearPickerAdapter();
        this.mAdapter = yearPickerAdapter;
        setAdapter(yearPickerAdapter);
        this.mAdapter.setOnClickYearListener(this);
        refreshAndCenter();
    }

    private void injectYearsInAdapter() {
        if (this.mAdapter != null) {
            ArrayList arrayList = new ArrayList();
            for (int i = this.minYear; i <= this.maxYear; i++) {
                arrayList.add(Integer.valueOf(i));
            }
            this.mAdapter.setListYears(arrayList);
            this.mAdapter.notifyDataSetChanged();
        }
    }

    public void refreshAndCenter() {
        this.mAdapter.notifyDataSetChanged();
        centerListOn((this.currentYear - this.minYear) - 1);
    }

    @Override // com.kunzisoft.switchdatetime.date.widget.YearPickerAdapter.OnClickYearListener
    public void onItemYearClick(View view, Integer num, int i) {
        int positionSelectedYear = this.mAdapter.getPositionSelectedYear();
        this.currentYear = num.intValue();
        OnYearSelectedListener onYearSelectedListener = this.yearChangeListener;
        if (onYearSelectedListener != null) {
            onYearSelectedListener.onYearSelected(view, num.intValue());
        }
        try {
            this.mAdapter.setSelectedYear(this.currentYear);
        } catch (YearPickerAdapter.SelectYearException e) {
            Log.e(TAG, e.getMessage());
        }
        this.mAdapter.notifyDataSetChanged();
        this.mAdapter.notifyItemChanged(positionSelectedYear);
        this.mAdapter.notifyItemChanged(i);
    }

    public void centerListOn(int i) {
        getLayoutManager().scrollToPosition(i);
        try {
            getLayoutManager().scrollVerticallyBy((this.mViewSize / 2) - (this.mChildSize / 2), null, null);
        } catch (Exception unused) {
            Log.w(TAG, "Can't scroll more");
        }
    }

    public void setDatePickerListener(OnYearSelectedListener onYearSelectedListener) {
        this.yearChangeListener = onYearSelectedListener;
    }

    public int getMinYear() {
        return this.minYear;
    }

    public void setMinYear(int i) {
        this.minYear = i;
        injectYearsInAdapter();
    }

    public int getMaxYear() {
        return this.maxYear;
    }

    public void setMaxYear(int i) {
        this.maxYear = i;
        injectYearsInAdapter();
    }

    public int getYearSelected() {
        return this.currentYear;
    }

    public void assignCurrentYear(int i) {
        this.currentYear = i;
        YearPickerAdapter yearPickerAdapter = this.mAdapter;
        if (yearPickerAdapter != null) {
            try {
                yearPickerAdapter.setSelectedYear(i);
            } catch (YearPickerAdapter.SelectYearException e) {
                Log.e(TAG, e.getMessage());
            }
        }
        refreshAndCenter();
    }
}
