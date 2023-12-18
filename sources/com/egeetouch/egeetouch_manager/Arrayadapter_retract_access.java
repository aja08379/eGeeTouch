package com.egeetouch.egeetouch_manager;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.core.internal.view.SupportMenu;
import com.facebook.internal.ServerProtocol;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
/* loaded from: classes.dex */
public class Arrayadapter_retract_access extends ArrayAdapter<String> {
    private final Context context;
    private List<String> data_email;
    private String[] mStringArray_email;
    private String[] mStringArray_endTime;
    private String[] mStringArray_lockName;
    private String[] mStringArray_locksatus;
    private String[] mStringArray_startTime;
    private int position;

    public Arrayadapter_retract_access(Context context, List<String> list, List<String> list2, List<String> list3, List<String> list4, List<String> list5) {
        super(context, (int) R.layout.listview_arrayadapter_retract_access, list2);
        this.context = context;
        this.data_email = list2;
        System.out.println("Hello data_email:" + this.data_email.size());
        this.mStringArray_email = new String[list2.size()];
        this.mStringArray_lockName = new String[list.size()];
        this.mStringArray_startTime = new String[list3.size()];
        this.mStringArray_endTime = new String[list4.size()];
        this.mStringArray_locksatus = new String[list5.size()];
        this.mStringArray_email = (String[]) list2.toArray(this.mStringArray_email);
        this.mStringArray_lockName = (String[]) list.toArray(this.mStringArray_lockName);
        this.mStringArray_startTime = (String[]) list3.toArray(this.mStringArray_startTime);
        this.mStringArray_endTime = (String[]) list4.toArray(this.mStringArray_endTime);
        this.mStringArray_locksatus = (String[]) list5.toArray(this.mStringArray_locksatus);
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        String string;
        this.position = i;
        int[] iArr = {Color.parseColor("#FFFFFF"), Color.parseColor("#C9E1FC")};
        View inflate = ((LayoutInflater) this.context.getSystemService("layout_inflater")).inflate(R.layout.listview_arrayadapter_retract_access, viewGroup, false);
        inflate.setBackgroundColor(iArr[i % 2]);
        TextView textView = (TextView) inflate.findViewById(R.id.email_id);
        TextView textView2 = (TextView) inflate.findViewById(R.id.start_time);
        TextView textView3 = (TextView) inflate.findViewById(R.id.End_time);
        TextView textView4 = (TextView) inflate.findViewById(R.id.lock_status);
        TextView textView5 = (TextView) inflate.findViewById(R.id.lock_name);
        String[] strArr = this.mStringArray_email;
        if (strArr[i] != null) {
            textView.setText(strArr[i]);
        }
        String[] strArr2 = this.mStringArray_lockName;
        if (strArr2[i] != null) {
            textView5.setText(strArr2[i]);
        }
        String[] strArr3 = this.mStringArray_startTime;
        if (strArr3[i] != null) {
            long parseLong = Long.parseLong(strArr3[i]);
            if (parseLong != 0) {
                long j = parseLong * 1000;
                String format = new SimpleDateFormat("MMM dd yyyy hh:mm a").format(Long.valueOf(j));
                if (Locale.getDefault().getLanguage().equals("ja")) {
                    format = new SimpleDateFormat("yyyy年MM月dd日   HH時mm分ss秒").format(Long.valueOf(j));
                }
                textView2.setText(this.context.getResources().getString(R.string.starts) + format);
            } else {
                textView2.setText(R.string.permanent_access);
            }
        }
        String[] strArr4 = this.mStringArray_endTime;
        if (strArr4[i] != null) {
            long parseLong2 = Long.parseLong(strArr4[i]);
            if (parseLong2 != 0) {
                long j2 = parseLong2 * 1000;
                String format2 = new SimpleDateFormat("MMM dd yyyy hh:mm a").format(Long.valueOf(j2));
                if (Locale.getDefault().getLanguage().equals("ja")) {
                    format2 = new SimpleDateFormat("yyyy年MM月dd日   HH時mm分ss秒").format(Long.valueOf(j2));
                }
                textView3.setText(this.context.getResources().getString(R.string.ends) + format2);
            } else {
                textView3.setText("");
            }
        }
        String[] strArr5 = this.mStringArray_locksatus;
        if (strArr5[i] != null) {
            if (strArr5[i] == ServerProtocol.DIALOG_RETURN_SCOPES_TRUE) {
                string = this.context.getString(R.string.allowed);
                textView4.setTextColor(this.context.getResources().getColor(R.color.green_1));
            } else {
                textView4.setTextColor(SupportMenu.CATEGORY_MASK);
                string = this.context.getString(R.string.blocked);
            }
            textView4.setText(string);
        }
        return inflate;
    }
}
