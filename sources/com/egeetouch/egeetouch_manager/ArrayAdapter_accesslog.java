package com.egeetouch.egeetouch_manager;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
/* loaded from: classes.dex */
public class ArrayAdapter_accesslog extends ArrayAdapter<String> {
    private String[] LockNameArray;
    private String[] LockSerialStringArray;
    private String[] LockmodelNumArray;
    private final Context context;
    private List<String> data;

    public ArrayAdapter_accesslog(Context context, List<String> list, List<String> list2, List<String> list3) {
        super(context, (int) R.layout.listview_adapter_access_log_lock_list, list);
        this.context = context;
        this.data = list;
        Log.i("Tag", "data.size(): " + this.data.size());
        System.out.println("hey array adapter access_log");
        String[] strArr = new String[list.size()];
        this.LockSerialStringArray = strArr;
        this.LockSerialStringArray = (String[]) list.toArray(strArr);
        String[] strArr2 = new String[list2.size()];
        this.LockmodelNumArray = strArr2;
        this.LockmodelNumArray = (String[]) list2.toArray(strArr2);
        String[] strArr3 = new String[list3.size()];
        this.LockNameArray = strArr3;
        this.LockNameArray = (String[]) list3.toArray(strArr3);
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        int[] iArr = {Color.parseColor("#FFFFFF"), Color.parseColor("#C9E1FC")};
        View inflate = ((LayoutInflater) this.context.getSystemService("layout_inflater")).inflate(R.layout.listview_adapter_access_log_lock_list, viewGroup, false);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.ImageView_lock_model);
        TextView textView = (TextView) inflate.findViewById(R.id.TextView_serial);
        TextView textView2 = (TextView) inflate.findViewById(R.id.TextView_lockName);
        inflate.setBackgroundColor(iArr[i % 2]);
        String[] strArr = this.LockSerialStringArray;
        if (strArr[i] != null) {
            textView.setText(strArr[i]);
            textView2.setText(this.LockNameArray[i]);
        }
        if (this.LockmodelNumArray[i].equals("4")) {
            this.LockmodelNumArray[i] = "GT2100";
        } else if (this.LockmodelNumArray[i].equals("7")) {
            this.LockmodelNumArray[i] = "GT5300";
        } else if (this.LockmodelNumArray[i].equals("8")) {
            this.LockmodelNumArray[i] = "GT5107";
        } else if (this.LockmodelNumArray[i].equals("9")) {
            this.LockmodelNumArray[i] = "GT5109";
        } else if (this.LockmodelNumArray[i].equals("10")) {
            this.LockmodelNumArray[i] = "GT2500";
        } else if (this.LockmodelNumArray[i].equals("11")) {
            this.LockmodelNumArray[i] = "GT2550";
        }
        if (this.LockmodelNumArray[i].equals("GT1000")) {
            imageView.setImageResource(R.drawable.gt1000);
        } else if (this.LockmodelNumArray[i].equals("GT2000")) {
            imageView.setImageResource(R.drawable.gt2000);
        } else if (this.LockmodelNumArray[i].equals("GT2002")) {
            imageView.setImageResource(R.drawable.gt2000_2);
        } else if (this.LockmodelNumArray[i].equals("GT2003")) {
            imageView.setImageResource(R.drawable.gt2000_3);
        } else if (this.LockmodelNumArray[i].equals("GT3000")) {
            imageView.setImageResource(R.drawable.gt3000);
        } else if (this.LockmodelNumArray[i].equals("GT3100") || this.LockmodelNumArray[i].equals("GT3002")) {
            imageView.setImageResource(R.drawable.gt3000_2);
        } else if (this.LockmodelNumArray[i].equals("GT2100")) {
            imageView.setImageResource(R.drawable.ip45_4th_gen);
        } else if (this.LockmodelNumArray[i].equals("GT5300")) {
            imageView.setImageResource(R.drawable.loto_metalic_lock);
        } else if (this.LockmodelNumArray[i].equals("GT5107")) {
            imageView.setImageResource(R.drawable.loto_red_lock);
        } else if (this.LockmodelNumArray[i].equals("GT5109")) {
            imageView.setImageResource(R.drawable.loto_black_lock);
        } else if (this.LockmodelNumArray[i].equals("GT2500")) {
            imageView.setImageResource(R.drawable.ip66_shortshackle);
        } else if (this.LockmodelNumArray[i].equals("GT2550")) {
            imageView.setImageResource(R.drawable.anticut);
        }
        return inflate;
    }
}
