package com.egeetouch.egeetouch_manager;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.facebook.appevents.AppEventsConstants;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
/* loaded from: classes.dex */
public class ArrayAdapter_dashboard extends ArrayAdapter<String> {
    private final Context context;
    private List<String> data;
    private String[] mStringArray;

    public ArrayAdapter_dashboard(Context context, List<String> list) {
        super(context, (int) R.layout.listview_adapter_dashboard, list);
        this.context = context;
        this.data = list;
        String[] strArr = new String[list.size()];
        this.mStringArray = strArr;
        this.mStringArray = (String[]) this.data.toArray(strArr);
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view2;
        TextView textView;
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String format;
        String format2;
        int[] iArr = {Color.parseColor("#C9E1FC"), Color.parseColor("#FFFFFF")};
        String valueOf = String.valueOf(i + 1);
        View inflate = ((LayoutInflater) this.context.getSystemService("layout_inflater")).inflate(R.layout.listview_adapter_dashboard, viewGroup, false);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.ImageView_lock_model);
        TextView textView2 = (TextView) inflate.findViewById(R.id.TextView_lockname);
        TextView textView3 = (TextView) inflate.findViewById(R.id.TextView_sharedByEmail);
        TextView textView4 = (TextView) inflate.findViewById(R.id.TextView_StartTime);
        TextView textView5 = (TextView) inflate.findViewById(R.id.TextView_EndTime);
        TextView textView6 = (TextView) inflate.findViewById(R.id.TextView_serial);
        ((LinearLayout) inflate.findViewById(R.id.lock_item)).getBackground().setColorFilter(iArr[i % 2], PorterDuff.Mode.SRC_OVER);
        ((TextView) inflate.findViewById(R.id.dashboard_index)).setText(valueOf);
        try {
            Cursor rawQuery = DatabaseVariable.db_lock.rawQuery(DatabaseVariable.lockdb_Query_model(this.mStringArray[i]), null);
            if (rawQuery.moveToNext()) {
                rawQuery.getString(0);
                String string = rawQuery.getString(2);
                rawQuery.getString(3);
                String string2 = rawQuery.getString(5);
                String string3 = rawQuery.getString(8);
                String string4 = rawQuery.getString(9);
                str = rawQuery.getString(10);
                str3 = string2;
                str2 = string;
                textView = textView2;
                str5 = string3;
                view2 = inflate;
                str4 = string4;
            } else {
                view2 = inflate;
                textView = textView2;
                str = "";
                str2 = str;
                str3 = str2;
                str4 = str3;
                str5 = str4;
            }
            try {
                rawQuery.close();
                if (str3.length() != 0) {
                    textView6.setText(str3);
                } else {
                    textView6.setVisibility(8);
                }
                if (!str.equals("")) {
                    textView3.setText(this.context.getString(R.string.shared_by) + str);
                    if (!str5.equals(AppEventsConstants.EVENT_PARAM_VALUE_NO) && !str4.equals(AppEventsConstants.EVENT_PARAM_VALUE_NO)) {
                        long parseLong = Long.parseLong(str5);
                        long parseLong2 = Long.parseLong(str4);
                        if (Locale.getDefault().getLanguage().equals("ja")) {
                            format = new SimpleDateFormat("yyyy年MM月dd日   HH時mm分ss秒").format(Long.valueOf(parseLong * 1000));
                            format2 = new SimpleDateFormat("yyyy年MM月dd日   HH時mm分ss秒").format(Long.valueOf(parseLong2 * 1000));
                        } else {
                            format = new SimpleDateFormat("MMM dd yyyy hh:mm a").format(Long.valueOf(parseLong * 1000));
                            format2 = new SimpleDateFormat("MMM dd yyyy hh:mm a").format(Long.valueOf(parseLong2 * 1000));
                        }
                        textView4.setText(this.context.getString(R.string.starts) + format);
                        textView5.setText(this.context.getString(R.string.ends) + format2);
                    }
                    textView4.setText((CharSequence) null);
                    textView5.setText((CharSequence) null);
                } else {
                    textView3.setText(R.string.permanent_access);
                    textView4.setVisibility(8);
                    textView4.setText("");
                    textView5.setText("");
                }
                System.out.println("Hello Selected_model:" + str2);
                if (str2.equals("GT1000")) {
                    imageView.setImageResource(R.drawable.travel_padlock_gray);
                } else if (str2.equals("GT2000")) {
                    imageView.setImageResource(R.drawable.gt2000);
                } else if (str2.equals("GT2002")) {
                    imageView.setImageResource(R.drawable.indoor_padlock_2nd_gen);
                } else if (str2.equals("GT2003")) {
                    imageView.setImageResource(R.drawable.gt2000_3);
                } else if (str2.equals("GT3000")) {
                    imageView.setImageResource(R.drawable.gt3000);
                } else if (str2.equals("GT3002")) {
                    imageView.setImageResource(R.drawable.luggagelock);
                } else if (str2.equals("GT2100")) {
                    imageView.setImageResource(R.drawable.ip45_4th_gen);
                } else if (str2.equals("GT5100")) {
                    imageView.setImageResource(R.drawable.loto_metalic_lock);
                } else if (str2.equals("GT5107")) {
                    imageView.setImageResource(R.drawable.loto_red_lock);
                } else if (str2.equals("GT5109")) {
                    imageView.setImageResource(R.drawable.loto_black_lock);
                } else if (str2.equals("GT5300")) {
                    imageView.setImageResource(R.drawable.loto_metalic_lock);
                } else if (str2.equals("GT2500")) {
                    imageView.setImageResource(R.drawable.ip66_shortshackle);
                } else if (str2.equals("GT2550")) {
                    imageView.setImageResource(R.drawable.anticut);
                }
                textView.setText(this.mStringArray[i]);
                return view2;
            } catch (Exception unused) {
                return view2;
            }
        } catch (Exception unused2) {
            return inflate;
        }
    }
}
