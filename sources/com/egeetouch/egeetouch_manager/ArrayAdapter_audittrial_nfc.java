package com.egeetouch.egeetouch_manager;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
/* loaded from: classes.dex */
public class ArrayAdapter_audittrial_nfc extends ArrayAdapter<String> {
    private final Context context;
    private ArrayList<String> data_address;
    private ArrayList<String> data_when;
    private ArrayList<String> data_who;
    private boolean get_location;
    private String[] mStringArray_address;
    private String[] mStringArray_when;
    private String[] mStringArray_who;
    private int position;

    public ArrayAdapter_audittrial_nfc(Context context, ArrayList<String> arrayList, ArrayList<String> arrayList2, ArrayList<String> arrayList3) {
        super(context, (int) R.layout.listview_layout_audit_trail, arrayList);
        this.get_location = false;
        this.context = context;
        this.data_who = arrayList;
        this.data_when = arrayList2;
        this.data_address = arrayList3;
        this.mStringArray_who = new String[arrayList.size()];
        this.mStringArray_when = new String[this.data_when.size()];
        this.mStringArray_address = new String[this.data_address.size()];
        this.mStringArray_who = (String[]) this.data_who.toArray(this.mStringArray_who);
        this.mStringArray_when = (String[]) this.data_when.toArray(this.mStringArray_when);
        this.mStringArray_address = (String[]) this.data_address.toArray(this.mStringArray_address);
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        this.position = i;
        Color.parseColor("#FFFFFF");
        Color.parseColor("#C9E1FC");
        View inflate = ((LayoutInflater) this.context.getSystemService("layout_inflater")).inflate(R.layout.listview_layout_audit_trail, viewGroup, false);
        TextView textView = (TextView) inflate.findViewById(R.id.listview_who);
        TextView textView2 = (TextView) inflate.findViewById(R.id.TextView_calendar);
        TextView textView3 = (TextView) inflate.findViewById(R.id.listview_when);
        TextView textView4 = (TextView) inflate.findViewById(R.id.listview_where);
        TextView textView5 = (TextView) inflate.findViewById(R.id.lock_status);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.imageView1);
        TextView textView6 = (TextView) inflate.findViewById(R.id.tx_view_map);
        textView6.setTextColor(-16776961);
        textView5.setText(this.context.getString(R.string.unlocked));
        textView5.setTextColor(this.context.getColor(R.color.blossomred));
        String[] strArr = this.mStringArray_when;
        if (strArr[i] != null) {
            try {
                String[] split = strArr[i].split("   ");
                textView2.setText(split[0]);
                textView3.setText(split[1].trim());
            } catch (Exception unused) {
                textView2.setText(inflate.getResources().getString(R.string.N_A));
                textView3.setText(inflate.getResources().getString(R.string.N_A));
            }
        }
        String[] strArr2 = this.mStringArray_who;
        if (strArr2[i] != null && strArr2[i] != "") {
            String str = strArr2[i];
            if (!str.equals("")) {
                if (!str.equals("Admin(You)")) {
                    if (str.contains("Tag")) {
                        ((ImageView) inflate.findViewById(R.id.imageView_role)).setImageResource(R.drawable.tag_icon);
                        textView.setText(str);
                        textView2.setText(inflate.getResources().getString(R.string.N_A));
                        textView3.setText(inflate.getResources().getString(R.string.N_A));
                        imageView.setVisibility(8);
                        textView6.setVisibility(8);
                    } else {
                        ((ImageView) inflate.findViewById(R.id.imageView_role)).setImageResource(R.drawable.phone_bluetooth_icon);
                        textView.setText(str);
                        this.get_location = true;
                        imageView.setVisibility(8);
                        textView6.setVisibility(8);
                    }
                } else if (str.equals("Admin(You)")) {
                    textView.setText(inflate.getResources().getString(R.string.admin_you));
                    this.get_location = true;
                    textView6.setVisibility(0);
                    imageView.setVisibility(0);
                }
            }
        }
        if (!this.get_location) {
            textView4.setText(inflate.getResources().getString(R.string.N_A));
        } else {
            textView4.setText(this.mStringArray_address[i]);
        }
        this.get_location = false;
        return inflate;
    }
}
