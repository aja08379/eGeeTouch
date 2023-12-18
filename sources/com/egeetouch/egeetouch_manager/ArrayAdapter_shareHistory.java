package com.egeetouch.egeetouch_manager;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
/* loaded from: classes.dex */
public class ArrayAdapter_shareHistory extends ArrayAdapter<String> {
    private List<String> User_email;
    private List<String> User_name;
    private final Context context;
    private String[] mStringArray_endTime;
    private String[] mStringArray_lockName;
    private String[] mStringArray_sharedOnDate;
    private String[] mStringArray_sharedToEmail;
    private String[] mStringArray_startTime;

    public ArrayAdapter_shareHistory(Context context, List<String> list, List<String> list2, List<String> list3, List<String> list4, List<String> list5) {
        super(context, (int) R.layout.listview_adapter_share_history, list);
        this.context = context;
        this.User_name = list;
        this.User_email = list2;
        this.mStringArray_lockName = new String[list.size()];
        this.mStringArray_sharedOnDate = new String[list2.size()];
        this.mStringArray_sharedToEmail = new String[list3.size()];
        this.mStringArray_startTime = new String[list4.size()];
        this.mStringArray_endTime = new String[list5.size()];
        this.mStringArray_lockName = (String[]) list.toArray(this.mStringArray_lockName);
        this.mStringArray_sharedOnDate = (String[]) list2.toArray(this.mStringArray_sharedOnDate);
        this.mStringArray_sharedToEmail = (String[]) list3.toArray(this.mStringArray_sharedToEmail);
        this.mStringArray_startTime = (String[]) list4.toArray(this.mStringArray_startTime);
        this.mStringArray_endTime = (String[]) list5.toArray(this.mStringArray_endTime);
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        int[] iArr = {Color.parseColor("#FFFFFF"), Color.parseColor("#C9E1FC")};
        View inflate = ((LayoutInflater) this.context.getSystemService("layout_inflater")).inflate(R.layout.listview_adapter_share_history, viewGroup, false);
        TextView textView = (TextView) inflate.findViewById(R.id.TextView_lockname);
        TextView textView2 = (TextView) inflate.findViewById(R.id.TextView_share_on_date);
        TextView textView3 = (TextView) inflate.findViewById(R.id.textView_shared_to_email);
        TextView textView4 = (TextView) inflate.findViewById(R.id.textView_start_date);
        TextView textView5 = (TextView) inflate.findViewById(R.id.textView_end_date);
        inflate.setBackgroundColor(iArr[i % 2]);
        String[] strArr = this.mStringArray_lockName;
        if (strArr[i] != null) {
            textView.setText(strArr[i]);
        }
        if (this.mStringArray_sharedOnDate[i] != null) {
            try {
                String format = new SimpleDateFormat("MMM dd yyyy hh:mm a").format(Long.valueOf(Long.parseLong(this.mStringArray_sharedOnDate[i]) * 1000));
                if (Locale.getDefault().getLanguage().equals("ja")) {
                    format = new SimpleDateFormat("yyyy年MM月dd日   HH時mm分ss秒").format(Long.valueOf(Long.parseLong(this.mStringArray_sharedOnDate[i]) * 1000));
                }
                textView2.setText(this.context.getString(R.string.shared_on) + format);
            } catch (Exception unused) {
                textView2.setText(this.context.getString(R.string.shared_on) + this.mStringArray_sharedOnDate[i]);
            }
        }
        if (this.mStringArray_sharedToEmail[i] != null) {
            textView3.setText(this.context.getResources().getString(R.string.shared_to) + this.mStringArray_sharedToEmail[i]);
        }
        String[] strArr2 = this.mStringArray_startTime;
        if (strArr2[i] != null) {
            if (strArr2[i].equals("")) {
                textView4.setText(R.string.permanent_access);
            } else {
                try {
                    long parseLong = Long.parseLong(this.mStringArray_startTime[i]);
                    if (parseLong != 0) {
                        long j = parseLong * 1000;
                        String format2 = new SimpleDateFormat("MMM dd yyyy hh:mm a").format(Long.valueOf(j));
                        if (Locale.getDefault().getLanguage().equals("ja")) {
                            format2 = new SimpleDateFormat("yyyy年MM月dd日   HH時mm分ss秒").format(Long.valueOf(j));
                        }
                        textView4.setText(this.context.getString(R.string.starts) + format2);
                    } else {
                        textView4.setText(R.string.permanent_access);
                    }
                } catch (Exception unused2) {
                    textView4.setText(this.context.getString(R.string.starts) + this.mStringArray_startTime[i]);
                }
            }
        }
        String[] strArr3 = this.mStringArray_endTime;
        if (strArr3[i] != null) {
            if (strArr3[i].equals("")) {
                textView4.setText(R.string.permanent_access);
                textView5.setText("");
            } else {
                try {
                    long parseLong2 = Long.parseLong(this.mStringArray_endTime[i]);
                    if (parseLong2 != 0) {
                        long j2 = parseLong2 * 1000;
                        String format3 = new SimpleDateFormat("MMM dd yyyy hh:mm a").format(Long.valueOf(j2));
                        if (Locale.getDefault().getLanguage().equals("ja")) {
                            format3 = new SimpleDateFormat("yyyy年MM月dd日   HH時mm分ss秒").format(Long.valueOf(j2));
                        }
                        textView5.setText(this.context.getString(R.string.ends) + format3);
                    } else {
                        textView5.setText("");
                    }
                } catch (Exception unused3) {
                    textView5.setText(this.context.getString(R.string.ends) + this.mStringArray_endTime[i]);
                }
            }
        }
        return inflate;
    }
}
