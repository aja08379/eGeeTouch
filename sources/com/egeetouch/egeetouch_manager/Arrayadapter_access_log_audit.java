package com.egeetouch.egeetouch_manager;

import android.content.Context;
import android.widget.ArrayAdapter;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public class Arrayadapter_access_log_audit extends ArrayAdapter<String> {
    private final Context context;
    private ArrayList<String> data_address;
    private List<String> data_email;
    private List<Double> data_latitude;
    private ArrayList<String> data_lock_status;
    private List<Double> data_longitude;
    private ArrayList<String> data_time;
    private Double[] mDoubleArray_latitude;
    private Double[] mDoubleArray_longitude;
    private Integer[] mIntegerArray_auditDeciValue;
    private Long[] mLongArray_lockBackTime;
    private String[] mStringArray_address;
    private String[] mStringArray_email;
    private String[] mStringArray_lock_status;
    private Long[] mStringArray_time;
    private int position;

    public Arrayadapter_access_log_audit(Context context, List<String> list, List<Long> list2, List<String> list3, List<String> list4, List<Integer> list5, List<Long> list6, List<Double> list7, List<Double> list8) {
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

    /* JADX WARN: Removed duplicated region for block: B:81:0x042d  */
    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public android.view.View getView(final int r21, android.view.View r22, android.view.ViewGroup r23) {
        java.lang.String r16;
        r20.position = r21;
        int[] r0 = {android.graphics.Color.parseColor("#FFFFFF"), android.graphics.Color.parseColor("#C9E1FC")};
        android.view.View r3 = ((android.view.LayoutInflater) r20.context.getSystemService("layout_inflater")).inflate(com.egeetouch.egeetouch_manager.R.layout.access_log_audittrail_detail, r23, false);
        r3.setBackgroundColor(r0[r21 % 2]);
        java.lang.System.out.println("Array Adapter row");
        android.widget.TextView r6 = (android.widget.TextView) r3.findViewById(com.egeetouch.egeetouch_manager.R.id.TextView_email);
        android.widget.TextView r7 = (android.widget.TextView) r3.findViewById(com.egeetouch.egeetouch_manager.R.id.TextView_time);
        android.widget.TextView r8 = (android.widget.TextView) r3.findViewById(com.egeetouch.egeetouch_manager.R.id.textView_date);
        android.widget.TextView r9 = (android.widget.TextView) r3.findViewById(com.egeetouch.egeetouch_manager.R.id.TextView_lockStatus);
        android.widget.TextView r0 = (android.widget.TextView) r3.findViewById(com.egeetouch.egeetouch_manager.R.id.TextView_address);
        android.widget.ImageView r10 = (android.widget.ImageView) r3.findViewById(com.egeetouch.egeetouch_manager.R.id.imageView_role);
        java.lang.String[] r11 = r20.mStringArray_email;
        java.lang.String r14 = "";
        if (r11[r21] != null) {
            java.lang.String r11 = r11[r21];
            if (!r11.equals("")) {
                if (!r11.equals("Admin(You)")) {
                    if (r11.contains(r20.context.getResources().getString(com.egeetouch.egeetouch_manager.R.string.tag))) {
                        r6.setText(r11);
                        r10.setImageResource(com.egeetouch.egeetouch_manager.R.drawable.tag_icon);
                    } else if (r11.contains(r20.context.getResources().getString(com.egeetouch.egeetouch_manager.R.string.passcode_name))) {
                        r10.setImageResource(com.egeetouch.egeetouch_manager.R.drawable.passcode_icon);
                        r6.setText(r11);
                    } else {
                        r10.setImageResource(com.egeetouch.egeetouch_manager.R.drawable.phone_bluetooth_icon);
                        r6.setText(r11);
                    }
                } else if (r11.equals("Admin(You)")) {
                    r6.setText(r3.getResources().getString(com.egeetouch.egeetouch_manager.R.string.admin_you));
                }
            }
        }
        java.lang.String[] r11 = r20.mStringArray_lock_status;
        if (r11[r21] != null) {
            if (r11[r21].length() == 8 || r20.mStringArray_lock_status[r21] == r20.context.getString(com.egeetouch.egeetouch_manager.R.string.unlocked)) {
                r9.setText(com.egeetouch.egeetouch_manager.R.string.unlocked);
                r9.setTextColor(androidx.core.internal.view.SupportMenu.CATEGORY_MASK);
            } else {
                r9.setText(com.egeetouch.egeetouch_manager.R.string.locked);
                r9.setTextColor(r20.context.getResources().getColor(com.egeetouch.egeetouch_manager.R.color.green_1));
            }
            java.lang.System.out.println("Status color" + r20.mStringArray_lock_status[r21].length());
        }
        java.lang.String[] r5 = r20.mStringArray_address;
        if (r5[r21] != null) {
            r0.setText(r5[r21]);
        }
        try {
            if (r20.mLongArray_lockBackTime[r21].longValue() <= 30) {
                r14 = r20.context.getString(com.egeetouch.egeetouch_manager.R.string.lockback_within) + "5" + r20.context.getString(com.egeetouch.egeetouch_manager.R.string.lockback_mins) + r20.context.getString(com.egeetouch.egeetouch_manager.R.string.estimated_time);
            } else if (r20.mLongArray_lockBackTime[r21].longValue() <= 60) {
                r14 = r20.context.getString(com.egeetouch.egeetouch_manager.R.string.lockback_within) + "10" + r20.context.getString(com.egeetouch.egeetouch_manager.R.string.lockback_mins) + r20.context.getString(com.egeetouch.egeetouch_manager.R.string.estimated_time);
            } else if (r20.mLongArray_lockBackTime[r21].longValue() <= 90) {
                r14 = r20.context.getString(com.egeetouch.egeetouch_manager.R.string.lockback_within) + "15" + r20.context.getString(com.egeetouch.egeetouch_manager.R.string.lockback_mins) + r20.context.getString(com.egeetouch.egeetouch_manager.R.string.estimated_time);
            } else if (r20.mLongArray_lockBackTime[r21].longValue() <= 120) {
                r14 = r20.context.getString(com.egeetouch.egeetouch_manager.R.string.lockback_within) + "20" + r20.context.getString(com.egeetouch.egeetouch_manager.R.string.lockback_mins) + r20.context.getString(com.egeetouch.egeetouch_manager.R.string.estimated_time);
            } else if (r20.mLongArray_lockBackTime[r21].longValue() <= 150) {
                r14 = r20.context.getString(com.egeetouch.egeetouch_manager.R.string.lockback_within) + "25" + r20.context.getString(com.egeetouch.egeetouch_manager.R.string.lockback_mins) + r20.context.getString(com.egeetouch.egeetouch_manager.R.string.estimated_time);
            } else if (r20.mLongArray_lockBackTime[r21].longValue() == 180) {
                r14 = r20.context.getString(com.egeetouch.egeetouch_manager.R.string.lockback_within) + "30" + r20.context.getString(com.egeetouch.egeetouch_manager.R.string.lockback_mins) + r20.context.getString(com.egeetouch.egeetouch_manager.R.string.estimated_time);
            }
        } catch (java.lang.ArrayIndexOutOfBoundsException r0) {
            r0.printStackTrace();
            r14 = com.facebook.internal.AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
        }
        java.lang.System.out.println("Hello checking the DeciValue : " + r20.mIntegerArray_auditDeciValue[r21]);
        r3.setOnClickListener(new android.view.View.OnClickListener() { // from class: com.egeetouch.egeetouch_manager.Arrayadapter_access_log_audit.1
            {
            }

            @Override // android.view.View.OnClickListener
            public void onClick(android.view.View r6) {
                java.lang.Double.valueOf((double) com.google.firebase.remoteconfig.FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
                java.lang.Double.valueOf((double) com.google.firebase.remoteconfig.FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
                java.lang.Double r6 = com.egeetouch.egeetouch_manager.Arrayadapter_access_log_audit.this.mDoubleArray_longitude[r2];
                java.lang.Double r2 = com.egeetouch.egeetouch_manager.Arrayadapter_access_log_audit.this.mDoubleArray_latitude[r2];
                if (r2.doubleValue() == com.google.firebase.remoteconfig.FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE || r6.doubleValue() == com.google.firebase.remoteconfig.FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE || r2.doubleValue() <= -90.0d || r6.doubleValue() <= -180.0d || r2.doubleValue() >= 90.0d || r6.doubleValue() >= 180.0d) {
                    return;
                }
                android.content.Intent r0 = new android.content.Intent(com.egeetouch.egeetouch_manager.Arrayadapter_access_log_audit.this.context, com.egeetouch.egeetouch_manager.MapActivity.class);
                r0.putExtra("latitude", r2);
                r0.putExtra("longitude", r6);
                com.egeetouch.egeetouch_manager.Arrayadapter_access_log_audit.this.context.startActivity(r0);
            }
        });
        try {
            java.lang.Long[] r0 = r20.mStringArray_time;
            if (r0[r21] != null) {
                java.sql.Timestamp r0 = new java.sql.Timestamp(r0[r21].longValue());
                r16 = r14;
                try {
                    java.util.Date r11 = new java.util.Date(r0.getTime());
                    java.text.SimpleDateFormat r13 = new java.text.SimpleDateFormat("MMM dd yyyy   hh:mm a");
                    if (java.util.Locale.getDefault().getLanguage().equals("ja")) {
                        r13 = new java.text.SimpleDateFormat("yyyy年MM月dd日   HH時mm分ss秒");
                    }
                    java.lang.String[] r0 = r13.format(java.lang.Long.valueOf(r0.getTime())).split("   ");
                    java.lang.String r4 = r0[0];
                    java.lang.String r0 = r0[1].trim();
                    r8.setText(r4);
                    r7.setText(r0);
                    java.lang.System.out.println(r11);
                    java.lang.System.out.println(r0);
                    if (!r20.mStringArray_email[r21].contains("Passcode Name") && !r20.mStringArray_email[r21].contains("Tag Name") && !r20.mStringArray_email[r21].contains(r20.context.getString(com.egeetouch.egeetouch_manager.R.string.passcode_name))) {
                        r7.setText(r0);
                        r8.setText(r4);
                    }
                    r7.setText(r0 + r3.getResources().getString(com.egeetouch.egeetouch_manager.R.string.estimated_time));
                    r8.setText(r4);
                    java.lang.System.out.println("Testing here" + r7);
                } catch (java.lang.Exception unused) {
                    r7.setText(r20.context.getResources().getString(com.egeetouch.egeetouch_manager.R.string.error));
                    r8.setText(r20.context.getResources().getString(com.egeetouch.egeetouch_manager.R.string.error));
                    if (r20.mIntegerArray_auditDeciValue[r21].intValue() < 250) {
                    }
                    java.lang.String r14 = r16;
                    if (r20.mIntegerArray_auditDeciValue[r21].intValue() == 249) {
                    }
                    return r3;
                }
            } else {
                r16 = r14;
            }
        } catch (java.lang.Exception unused) {
            r16 = r14;
        }
        if (r20.mIntegerArray_auditDeciValue[r21].intValue() < 250 && r20.mIntegerArray_auditDeciValue[r21].intValue() <= 254) {
            r10.setImageResource(com.egeetouch.egeetouch_manager.R.drawable.lock_icon);
            r7.setText(r16);
            r6.setText(com.egeetouch.egeetouch_manager.R.string.lock_manually);
            r9.setText(com.egeetouch.egeetouch_manager.R.string.locked);
            r9.setTextColor(r20.context.getResources().getColor(com.egeetouch.egeetouch_manager.R.color.green_1));
        } else {
            java.lang.String r14 = r16;
            if (r20.mIntegerArray_auditDeciValue[r21].intValue() == 249) {
                r7.setText(r14);
                r6.setText(com.egeetouch.egeetouch_manager.R.string.lock_failed);
                r9.setText(com.egeetouch.egeetouch_manager.R.string.unlocked);
                r9.setTextColor(r20.context.getResources().getColor(com.egeetouch.egeetouch_manager.R.color.red));
            }
        }
        return r3;
    }
}
