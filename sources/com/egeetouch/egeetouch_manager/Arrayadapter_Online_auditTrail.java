package com.egeetouch.egeetouch_manager;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.internal.view.SupportMenu;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
/* loaded from: classes.dex */
public class Arrayadapter_Online_auditTrail extends ArrayAdapter<String> {
    private final Context context;
    private ArrayList<String> data_address;
    private List<String> data_email;
    private List<Double> data_latitude;
    private ArrayList<String> data_lock_status;
    private List<Double> data_longitude;
    private ArrayList<Long> data_time;
    private Double[] mDoubleArray_latitude;
    private Double[] mDoubleArray_longitude;
    private Integer[] mIntegerArray_auditDeciValue;
    private Long[] mLongArray_lockBackTime;
    private String[] mStringArray_address;
    private String[] mStringArray_email;
    private String[] mStringArray_lock_status;
    private Long[] mStringArray_time;
    private int position;

    public Arrayadapter_Online_auditTrail(Context context, List<String> list, List<Long> list2, List<String> list3, List<String> list4, List<Integer> list5, List<Long> list6, List<Double> list7, List<Double> list8) {
        super(context, (int) R.layout.access_log_audittrail_detail, list);
        this.context = context;
        this.data_email = list;
        this.data_longitude = list7;
        this.data_latitude = list8;
        System.out.println("data_email" + this.data_email.size());
        this.mStringArray_email = new String[list.size()];
        this.mStringArray_time = new Long[list2.size()];
        this.mStringArray_address = new String[list3.size()];
        this.mStringArray_lock_status = new String[list4.size()];
        this.mIntegerArray_auditDeciValue = new Integer[list5.size()];
        this.mLongArray_lockBackTime = new Long[list6.size()];
        this.mDoubleArray_longitude = new Double[list7.size()];
        this.mDoubleArray_latitude = new Double[list8.size()];
        this.mStringArray_email = (String[]) list.toArray(this.mStringArray_email);
        this.mStringArray_time = (Long[]) list2.toArray(this.mStringArray_time);
        this.mStringArray_address = (String[]) list3.toArray(this.mStringArray_address);
        this.mStringArray_lock_status = (String[]) list4.toArray(this.mStringArray_lock_status);
        this.mIntegerArray_auditDeciValue = (Integer[]) list5.toArray(this.mIntegerArray_auditDeciValue);
        this.mLongArray_lockBackTime = (Long[]) list6.toArray(this.mLongArray_lockBackTime);
        this.mDoubleArray_longitude = (Double[]) list7.toArray(this.mDoubleArray_longitude);
        this.mDoubleArray_latitude = (Double[]) list8.toArray(this.mDoubleArray_latitude);
        System.out.println("Hello array ");
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(final int i, View view, ViewGroup viewGroup) {
        String str;
        this.position = i;
        int[] iArr = {Color.parseColor("#FFFFFF"), Color.parseColor("#C9E1FC")};
        View inflate = ((LayoutInflater) this.context.getSystemService("layout_inflater")).inflate(R.layout.listview_layout_audit_trail_with_icon, viewGroup, false);
        inflate.setBackgroundColor(iArr[i % 2]);
        System.out.println("Array Adapter row");
        TextView textView = (TextView) inflate.findViewById(R.id.TextView_email);
        TextView textView2 = (TextView) inflate.findViewById(R.id.TextView_time);
        TextView textView3 = (TextView) inflate.findViewById(R.id.textView_date);
        TextView textView4 = (TextView) inflate.findViewById(R.id.TextView_lockStatus);
        TextView textView5 = (TextView) inflate.findViewById(R.id.TextView_address);
        TextView textView6 = (TextView) inflate.findViewById(R.id.textView_time);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.imageView_role);
        String[] strArr = this.mStringArray_email;
        if (strArr[i] != null) {
            textView.setText(strArr[i]);
        }
        String[] strArr2 = this.mStringArray_lock_status;
        if (strArr2[i] != null) {
            if (strArr2[i].length() == 8 || this.mStringArray_lock_status[i] == this.context.getString(R.string.unlocked)) {
                textView4.setText(R.string.unlocked);
                textView4.setTextColor(SupportMenu.CATEGORY_MASK);
            } else {
                textView4.setText(R.string.locked);
                textView4.setTextColor(this.context.getResources().getColor(R.color.green_1));
            }
            System.out.println("Status" + this.mStringArray_lock_status[i].length());
        }
        String[] strArr3 = this.mStringArray_address;
        if (strArr3[i] != null) {
            textView5.setText(strArr3[i]);
        }
        if (this.mStringArray_email[i].contains(this.context.getResources().getString(R.string.tag))) {
            imageView.setImageResource(R.drawable.tag_icon);
            textView6.setVisibility(0);
        } else if (this.mStringArray_email[i].contains(this.context.getResources().getString(R.string.passcode_name))) {
            imageView.setImageResource(R.drawable.passcode_icon);
            textView6.setVisibility(0);
        } else if (this.mStringArray_email[i].contains("Smart Watch") || this.mStringArray_email[i].contains("Smartwatch")) {
            imageView.setImageResource(R.drawable.function_watch_icon);
        } else {
            imageView.setImageResource(R.drawable.phone_bluetooth_icon);
        }
        if (this.mLongArray_lockBackTime[i].longValue() <= 30) {
            str = this.context.getString(R.string.lockback_within) + "5" + this.context.getString(R.string.lockback_mins);
        } else if (this.mLongArray_lockBackTime[i].longValue() <= 60) {
            str = this.context.getString(R.string.lockback_within) + "10" + this.context.getString(R.string.lockback_mins);
        } else if (this.mLongArray_lockBackTime[i].longValue() <= 90) {
            str = this.context.getString(R.string.lockback_within) + "15" + this.context.getString(R.string.lockback_mins);
        } else if (this.mLongArray_lockBackTime[i].longValue() <= 120) {
            str = this.context.getString(R.string.lockback_within) + "20" + this.context.getString(R.string.lockback_mins);
        } else if (this.mLongArray_lockBackTime[i].longValue() <= 150) {
            str = this.context.getString(R.string.lockback_within) + "25" + this.context.getString(R.string.lockback_mins);
        } else {
            str = this.mLongArray_lockBackTime[i].longValue() == 180 ? this.context.getString(R.string.lockback_within) + "30" + this.context.getString(R.string.lockback_mins) : "";
        }
        inflate.setOnClickListener(new View.OnClickListener() { // from class: com.egeetouch.egeetouch_manager.Arrayadapter_Online_auditTrail.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (Arrayadapter_Online_auditTrail.this.mStringArray_email[i].contains("Tag id")) {
                    ((MainActivity) Arrayadapter_Online_auditTrail.this.context).btn_manage_tag(null);
                    return;
                }
                Double.valueOf((double) FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
                Double.valueOf((double) FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
                Double d = Arrayadapter_Online_auditTrail.this.mDoubleArray_longitude[i];
                Double d2 = Arrayadapter_Online_auditTrail.this.mDoubleArray_latitude[i];
                if (d2.doubleValue() == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE || d.doubleValue() == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE || d2.doubleValue() <= -90.0d || d.doubleValue() <= -180.0d || d2.doubleValue() >= 90.0d || d.doubleValue() >= 180.0d) {
                    return;
                }
                Intent intent = new Intent(Arrayadapter_Online_auditTrail.this.context, MapActivity.class);
                intent.putExtra("latitude", d2);
                intent.putExtra("longitude", d);
                Arrayadapter_Online_auditTrail.this.context.startActivity(intent);
            }
        });
        System.out.println("Hello checking the auditTime Sec : " + this.mLongArray_lockBackTime[i] + " " + this.mIntegerArray_auditDeciValue[i]);
        Long[] lArr = this.mStringArray_time;
        if (lArr[i] != null) {
            Timestamp timestamp = new Timestamp(lArr[i].longValue());
            Date date = new Date(timestamp.getTime());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM dd yyyy   hh:mm a");
            if (Locale.getDefault().getLanguage().equals("ja")) {
                simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日   HH時mm分ss秒");
            }
            String[] split = simpleDateFormat.format(Long.valueOf(timestamp.getTime())).split("   ");
            String str2 = split[0];
            String trim = split[1].trim();
            System.out.println(date);
            System.out.println(trim);
            textView2.setText(trim);
            textView3.setText(str2);
        }
        if (this.mIntegerArray_auditDeciValue[i].intValue() >= 250 && this.mIntegerArray_auditDeciValue[i].intValue() <= 254) {
            imageView.setImageResource(R.drawable.lock_icon);
            textView6.setVisibility(0);
            textView2.setText(str);
            textView.setText(R.string.lock_manually);
            textView4.setText(R.string.locked);
            textView4.setTextColor(this.context.getResources().getColor(R.color.green_1));
        } else if (this.mIntegerArray_auditDeciValue[i].intValue() == 249) {
            imageView.setImageResource(R.drawable.passcode_button_with_border);
            textView6.setVisibility(0);
            textView2.setText(str);
            textView.setText(R.string.lock_failed);
            textView4.setText(R.string.unlocked);
            textView4.setTextColor(this.context.getResources().getColor(R.color.red));
        }
        return inflate;
    }
}
