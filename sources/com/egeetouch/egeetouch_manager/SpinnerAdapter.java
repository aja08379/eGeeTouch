package com.egeetouch.egeetouch_manager;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
/* loaded from: classes.dex */
public class SpinnerAdapter extends ArrayAdapter<String> {
    private Context ctx;
    private List<String> data;
    private Integer[] imageArray;
    private String[] nameArray;
    String selected_model;

    public SpinnerAdapter(Context context, List<String> list) {
        super(context, (int) R.layout.spinner_value_layout, (int) R.id.spinnerText, list);
        this.selected_model = "";
        this.ctx = context;
        this.data = list;
        String[] strArr = new String[list.size()];
        this.nameArray = strArr;
        this.nameArray = (String[]) this.data.toArray(strArr);
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        return getCustomView(i, view, viewGroup);
    }

    @Override // android.widget.ArrayAdapter, android.widget.BaseAdapter, android.widget.SpinnerAdapter
    public View getDropDownView(int i, View view, ViewGroup viewGroup) {
        return getCustomView(i, view, viewGroup);
    }

    public View getCustomView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = ((LayoutInflater) this.ctx.getSystemService("layout_inflater")).inflate(R.layout.spinner_value_layout, (ViewGroup) null);
        }
        ((TextView) view.findViewById(R.id.spinnerText)).setText(this.nameArray[i]);
        ImageView imageView = (ImageView) view.findViewById(R.id.spinnerImage);
        try {
            Cursor rawQuery = DatabaseVariable.db_lock.rawQuery(DatabaseVariable.lockdb_Query_model(this.nameArray[i]), null);
            if (rawQuery.moveToNext()) {
                this.selected_model = rawQuery.getString(2);
            }
            rawQuery.close();
            if (this.selected_model.equals("GT1000")) {
                imageView.setImageResource(R.drawable.travel_padlock_gray);
            } else if (this.selected_model.equals("GT2000")) {
                imageView.setImageResource(R.drawable.gt2000);
            } else if (this.selected_model.equals("GT2002")) {
                imageView.setImageResource(R.drawable.indoor_padlock_2nd_gen);
            } else if (this.selected_model.equals("GT2003")) {
                imageView.setImageResource(R.drawable.gt2000_3);
            } else if (this.selected_model.equals("GT3000")) {
                imageView.setImageResource(R.drawable.gt3000);
            } else if (this.selected_model.equals("GT3002")) {
                imageView.setImageResource(R.drawable.luggagelock);
            } else if (this.selected_model.equals("GT2100")) {
                imageView.setImageResource(R.drawable.ip45_4th_gen);
            } else if (this.selected_model.equals("GT5100")) {
                imageView.setImageResource(R.drawable.loto_metalic_lock);
            } else if (this.selected_model.equals("GT5107")) {
                imageView.setImageResource(R.drawable.loto_red_lock);
            } else if (this.selected_model.equals("GT5109")) {
                imageView.setImageResource(R.drawable.loto_black_lock);
            } else if (this.selected_model.equals("GT5300")) {
                imageView.setImageResource(R.drawable.loto_metalic_lock);
            } else if (this.selected_model.equals("GT2500")) {
                imageView.setImageResource(R.drawable.ip66_shortshackle);
            } else if (this.selected_model.equals("GT2550")) {
                imageView.setImageResource(R.drawable.anticut);
            }
        } catch (Exception unused) {
        }
        return view;
    }
}
