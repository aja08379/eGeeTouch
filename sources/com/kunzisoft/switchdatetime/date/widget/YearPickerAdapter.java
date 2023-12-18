package com.kunzisoft.switchdatetime.date.widget;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.kunzisoft.switchdatetime.R;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
/* loaded from: classes2.dex */
class YearPickerAdapter extends RecyclerView.Adapter<TextIndicatorViewHolder> {
    private static final int LIST_ITEM_TYPE_INDICATOR = 1;
    private static final int LIST_ITEM_TYPE_STANDARD = 0;
    public static final int UNDEFINED = -1;
    private OnClickYearListener onClickYearListener;
    private int positionSelectedYear;
    private List<Integer> listYears = new ArrayList();
    private Integer selectedYear = -1;
    private SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy", Locale.getDefault());
    private Calendar calendar = Calendar.getInstance();

    /* loaded from: classes2.dex */
    public interface OnClickYearListener {
        void onItemYearClick(View view, Integer num, int i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int i) {
        return this.listYears.get(i).intValue();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.listYears.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public TextIndicatorViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i != 1) {
            return new TextIndicatorViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.year_text, viewGroup, false));
        }
        return new TextIndicatorViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.year_text_indicator, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(TextIndicatorViewHolder textIndicatorViewHolder, int i) {
        Integer num = this.listYears.get(i);
        this.calendar.set(1, num.intValue());
        textIndicatorViewHolder.textView.setText(this.yearFormat.format(this.calendar.getTime()));
        if (this.onClickYearListener != null) {
            textIndicatorViewHolder.container.setOnClickListener(new BufferYearClickListener(num, i));
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return this.listYears.get(i).equals(this.selectedYear) ? 1 : 0;
    }

    public List<Integer> getListYears() {
        return this.listYears;
    }

    public void setListYears(List<Integer> list) {
        this.listYears = list;
    }

    public int getSelectedYear() {
        return this.selectedYear.intValue();
    }

    public void setSelectedYear(int i) throws SelectYearException {
        if (!this.listYears.contains(Integer.valueOf(i))) {
            throw new SelectYearException(Integer.valueOf(i), this.listYears);
        }
        this.selectedYear = Integer.valueOf(i);
        this.positionSelectedYear = this.listYears.indexOf(Integer.valueOf(i));
    }

    public int getPositionSelectedYear() {
        return this.positionSelectedYear;
    }

    public OnClickYearListener getOnClickYearListener() {
        return this.onClickYearListener;
    }

    public void setOnClickYearListener(OnClickYearListener onClickYearListener) {
        this.onClickYearListener = onClickYearListener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class BufferYearClickListener implements View.OnClickListener {
        private int position;
        private Integer year;

        BufferYearClickListener(Integer num, int i) {
            this.year = num;
            this.position = i;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            YearPickerAdapter.this.onClickYearListener.onItemYearClick(view, this.year, this.position);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public class TextIndicatorViewHolder extends RecyclerView.ViewHolder {
        private ViewGroup container;
        private TextView textView;

        TextIndicatorViewHolder(View view) {
            super(view);
            this.container = (ViewGroup) view.findViewById(R.id.year_element_container);
            this.textView = (TextView) view.findViewById(R.id.year_textView);
        }
    }

    /* loaded from: classes2.dex */
    class SelectYearException extends Exception {
        SelectYearException(Integer num, List<Integer> list) {
            super("Year selected " + num + " must be in list of years : " + list);
        }
    }
}
