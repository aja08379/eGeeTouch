package com.egeetouch.egeetouch_manager;

import android.content.Context;
import android.widget.ArrayAdapter;
import java.util.ArrayList;
/* loaded from: classes.dex */
public class ArrayAdapter_audittrial extends ArrayAdapter<String> {
    private final Context context;
    private ArrayList<String> data_address;
    private ArrayList<String> data_email;
    private ArrayList<Double> data_latitude;
    private ArrayList<String> data_lock_status;
    private ArrayList<Double> data_longitude;
    private ArrayList<String> data_when;
    private ArrayList<String> data_who;
    private boolean get_location;
    private Double[] mDoubleArray_latitude;
    private Double[] mDoubleArray_longitude;
    private String[] mStringArray_address;
    private String[] mStringArray_email;
    private String[] mStringArray_lock_status;
    private String[] mStringArray_when;
    private String[] mStringArray_who;
    private int position;

    public ArrayAdapter_audittrial(Context context, ArrayList<String> arrayList, ArrayList<String> arrayList2, ArrayList<String> arrayList3, ArrayList<Double> arrayList4, ArrayList<Double> arrayList5, ArrayList<String> arrayList6, ArrayList<String> arrayList7) {
        super(context, (int) R.layout.listview_layout_audit_trail, arrayList);
        this.get_location = false;
        this.context = context;
        this.data_who = arrayList;
        this.data_when = arrayList2;
        this.data_address = arrayList3;
        this.data_longitude = arrayList4;
        this.data_latitude = arrayList5;
        this.data_lock_status = arrayList6;
        this.data_email = arrayList7;
        this.mStringArray_who = new String[arrayList.size()];
        this.mStringArray_when = new String[this.data_when.size()];
        this.mStringArray_address = new String[this.data_address.size()];
        this.mDoubleArray_longitude = new Double[this.data_longitude.size()];
        this.mDoubleArray_latitude = new Double[this.data_latitude.size()];
        this.mStringArray_lock_status = new String[this.data_lock_status.size()];
        this.mStringArray_email = new String[this.data_email.size()];
        this.mStringArray_who = (String[]) this.data_who.toArray(this.mStringArray_who);
        this.mStringArray_when = (String[]) this.data_when.toArray(this.mStringArray_when);
        this.mStringArray_address = (String[]) this.data_address.toArray(this.mStringArray_address);
        this.mDoubleArray_longitude = (Double[]) this.data_longitude.toArray(this.mDoubleArray_longitude);
        this.mDoubleArray_latitude = (Double[]) this.data_latitude.toArray(this.mDoubleArray_latitude);
        this.mStringArray_lock_status = (String[]) this.data_lock_status.toArray(this.mStringArray_lock_status);
        this.mStringArray_email = (String[]) this.data_email.toArray(this.mStringArray_email);
    }

    /* JADX WARN: Code restructure failed: missing block: B:30:0x0185, code lost:
        if (java.lang.Float.valueOf(com.egeetouch.egeetouch_manager.BLEService.selected_lock_version).floatValue() > 1.8d) goto L27;
     */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0248  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0259  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0271  */
    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public android.view.View getView(final int r24, android.view.View r25, android.view.ViewGroup r26) {
        android.widget.Button r19;
        java.lang.String[] r0;
        android.widget.TextView r20;
        java.lang.String[] r0;
        r23.position = r24;
        int[] r0 = {android.graphics.Color.parseColor("#FFFFFF"), android.graphics.Color.parseColor("#C9E1FC")};
        android.view.View r3 = ((android.view.LayoutInflater) r23.context.getSystemService("layout_inflater")).inflate(com.egeetouch.egeetouch_manager.R.layout.listview_layout_audit_trail, r26, false);
        r3.setBackgroundColor(r0[r24 % 2]);
        android.widget.TextView r6 = (android.widget.TextView) r3.findViewById(com.egeetouch.egeetouch_manager.R.id.listview_who);
        android.widget.TextView r7 = (android.widget.TextView) r3.findViewById(com.egeetouch.egeetouch_manager.R.id.TextView_calendar);
        android.widget.TextView r8 = (android.widget.TextView) r3.findViewById(com.egeetouch.egeetouch_manager.R.id.listview_when);
        android.widget.TextView r9 = (android.widget.TextView) r3.findViewById(com.egeetouch.egeetouch_manager.R.id.listview_where);
        android.widget.ImageView r10 = (android.widget.ImageView) r3.findViewById(com.egeetouch.egeetouch_manager.R.id.imageView1);
        android.widget.TextView r0 = (android.widget.TextView) r3.findViewById(com.egeetouch.egeetouch_manager.R.id.tx_view_tag_name);
        android.widget.TextView r11 = (android.widget.TextView) r3.findViewById(com.egeetouch.egeetouch_manager.R.id.tx_click_tag);
        android.widget.Button r12 = (android.widget.Button) r3.findViewById(com.egeetouch.egeetouch_manager.R.id.bt_tag_name);
        android.widget.TextView r13 = (android.widget.TextView) r3.findViewById(com.egeetouch.egeetouch_manager.R.id.tx_view_map);
        android.widget.ImageView r14 = (android.widget.ImageView) r3.findViewById(com.egeetouch.egeetouch_manager.R.id.imageView_role);
        android.widget.TextView r15 = (android.widget.TextView) r3.findViewById(com.egeetouch.egeetouch_manager.R.id.lock_status);
        android.widget.TextView r5 = (android.widget.TextView) r3.findViewById(com.egeetouch.egeetouch_manager.R.id.textView_AuditTime);
        android.widget.LinearLayout r4 = (android.widget.LinearLayout) r3.findViewById(com.egeetouch.egeetouch_manager.R.id.linearLayoutAuditTime);
        android.widget.LinearLayout r15 = (android.widget.LinearLayout) r3.findViewById(com.egeetouch.egeetouch_manager.R.id.LinearLayoutDateTime);
        android.widget.LinearLayout r9 = (android.widget.LinearLayout) r3.findViewById(com.egeetouch.egeetouch_manager.R.id.linearLayout_auditTrail);
        r13.setTextColor(-16776961);
        r11.setTextColor(-16776961);
        r11.setPaintFlags(r11.getPaintFlags() | 8);
        r3.setOnClickListener(new android.view.View.OnClickListener() { // from class: com.egeetouch.egeetouch_manager.ArrayAdapter_audittrial.1
            {
            }

            @Override // android.view.View.OnClickListener
            public void onClick(android.view.View r6) {
                if (com.egeetouch.egeetouch_manager.ArrayAdapter_audittrial.this.mStringArray_who[r2].contains("Tag")) {
                    ((com.egeetouch.egeetouch_manager.MainActivity) com.egeetouch.egeetouch_manager.ArrayAdapter_audittrial.this.context).btn_manage_tag(null);
                    return;
                }
                java.lang.Double.valueOf((double) com.google.firebase.remoteconfig.FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
                java.lang.Double.valueOf((double) com.google.firebase.remoteconfig.FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
                java.lang.Double r6 = com.egeetouch.egeetouch_manager.ArrayAdapter_audittrial.this.mDoubleArray_longitude[r2];
                java.lang.Double r2 = com.egeetouch.egeetouch_manager.ArrayAdapter_audittrial.this.mDoubleArray_latitude[r2];
                if (r2.doubleValue() == com.google.firebase.remoteconfig.FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE || r6.doubleValue() == com.google.firebase.remoteconfig.FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE || r2.doubleValue() <= -90.0d || r6.doubleValue() <= -180.0d || r2.doubleValue() >= 90.0d || r6.doubleValue() >= 180.0d) {
                    return;
                }
                android.content.Intent r0 = new android.content.Intent(com.egeetouch.egeetouch_manager.ArrayAdapter_audittrial.this.context, com.egeetouch.egeetouch_manager.MapActivity.class);
                r0.putExtra("latitude", r2);
                r0.putExtra("longitude", r6);
                com.egeetouch.egeetouch_manager.ArrayAdapter_audittrial.this.context.startActivity(r0);
            }
        });
        java.lang.String[] r0 = r23.mStringArray_when;
        if (r0[r24] != null) {
            try {
                r0 = r0[r24].split("   ");
                r19 = r12;
            } catch (java.lang.Exception e) {
                r0 = e;
                r19 = r12;
            }
            try {
                r7.setText(r0[0]);
                r8.setText(r0[1].trim());
            } catch (java.lang.Exception e) {
                r0 = e;
                r7.setText(r3.getResources().getString(com.egeetouch.egeetouch_manager.R.string.N_A));
                r8.setText(r3.getResources().getString(com.egeetouch.egeetouch_manager.R.string.N_A));
                r0.printStackTrace();
                r0 = r23.mStringArray_who;
                if (r0[r24] == null) {
                }
                r9.setVisibility(8);
                if (r23.get_location) {
                }
                r23.get_location = false;
                if (r23.mStringArray_lock_status[r24].contains("UN")) {
                }
                return r3;
            }
        } else {
            r19 = r12;
        }
        r0 = r23.mStringArray_who;
        if (r0[r24] == null && r0[r24] != "") {
            java.lang.String r0 = r0[r24];
            if (!r0.equals("")) {
                if (!r0.equals("Admin(You)")) {
                    if (r0.contains("Tag")) {
                        r6.setText(r0);
                        r14.setImageResource(com.egeetouch.egeetouch_manager.R.drawable.tag_icon);
                        if (com.egeetouch.egeetouch_manager.BLEService.selected_lock_model.equals("GT2100")) {
                            if (com.egeetouch.egeetouch_manager.BLEService.selected_lock_version.equals("1.80")) {
                                r20 = r11;
                            } else {
                                r20 = r11;
                            }
                            r15.setVisibility(0);
                            r4.setVisibility(8);
                            r5.setText(r23.mStringArray_when[r24]);
                            r10.setVisibility(8);
                            r13.setVisibility(8);
                            r20.setVisibility(0);
                        } else {
                            r20 = r11;
                        }
                        r7.setText(r3.getResources().getString(com.egeetouch.egeetouch_manager.R.string.N_A));
                        r8.setText(r3.getResources().getString(com.egeetouch.egeetouch_manager.R.string.N_A));
                        r10.setVisibility(8);
                        r13.setVisibility(8);
                        r20.setVisibility(0);
                    } else if (r0.contains("Passcode")) {
                        r14.setImageResource(com.egeetouch.egeetouch_manager.R.drawable.passcode_button_with_border);
                        r15.setVisibility(8);
                        r4.setVisibility(0);
                        r5.setText(r23.mStringArray_when[r24]);
                        r6.setText(r0);
                        r10.setVisibility(8);
                        r13.setVisibility(8);
                        r11.setVisibility(4);
                    } else {
                        r14.setImageResource(com.egeetouch.egeetouch_manager.R.drawable.phone_bluetooth_icon);
                        r6.setText(r23.mStringArray_email[r24]);
                        r23.get_location = true;
                        r11.setVisibility(8);
                        r19.setVisibility(8);
                        r13.setVisibility(0);
                        r10.setVisibility(0);
                    }
                } else {
                    android.widget.Button r4 = r19;
                    if (r0.equals("Admin(You)")) {
                        r6.setText(r3.getResources().getString(com.egeetouch.egeetouch_manager.R.string.admin_you));
                        r23.get_location = true;
                        r11.setVisibility(8);
                        r4.setVisibility(8);
                        r13.setVisibility(0);
                        r10.setVisibility(0);
                    }
                }
            }
        } else {
            r9.setVisibility(8);
        }
        if (r23.get_location) {
            r9.setText(r3.getResources().getString(com.egeetouch.egeetouch_manager.R.string.N_A));
        } else {
            r9.setText(r23.mStringArray_address[r24]);
        }
        r23.get_location = false;
        if (r23.mStringArray_lock_status[r24].contains("UN")) {
            r15.setTextColor(r23.context.getResources().getColor(com.egeetouch.egeetouch_manager.R.color.red));
            r15.setText(com.egeetouch.egeetouch_manager.R.string.unlocked);
        }
        return r3;
    }
}
