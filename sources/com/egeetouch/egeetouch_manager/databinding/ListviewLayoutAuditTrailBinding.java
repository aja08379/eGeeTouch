package com.egeetouch.egeetouch_manager.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import com.egeetouch.egeetouch_manager.R;
/* loaded from: classes.dex */
public final class ListviewLayoutAuditTrailBinding implements ViewBinding {
    public final LinearLayout LinearLayoutDateTime;
    public final TextView TextViewCalendar;
    public final Button btTagName;
    public final ImageView imageView1;
    public final ImageView imageView2;
    public final ImageView imageView3;
    public final ImageView imageView4;
    public final ImageView imageViewRole;
    public final LinearLayout linearLayoutAuditTime;
    public final LinearLayout linearLayoutAuditTrail;
    public final TextView listviewWhen;
    public final TextView listviewWhere;
    public final TextView listviewWho;
    public final TextView lockStatus;
    private final LinearLayout rootView;
    public final TextView textViewAuditTime;
    public final TextView textViewTime;
    public final TextView txClickTag;
    public final TextView txClickTag1;
    public final TextView txViewMap;
    public final TextView txViewTagName;

    private ListviewLayoutAuditTrailBinding(LinearLayout linearLayout, LinearLayout linearLayout2, TextView textView, Button button, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, ImageView imageView5, LinearLayout linearLayout3, LinearLayout linearLayout4, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9, TextView textView10, TextView textView11) {
        this.rootView = linearLayout;
        this.LinearLayoutDateTime = linearLayout2;
        this.TextViewCalendar = textView;
        this.btTagName = button;
        this.imageView1 = imageView;
        this.imageView2 = imageView2;
        this.imageView3 = imageView3;
        this.imageView4 = imageView4;
        this.imageViewRole = imageView5;
        this.linearLayoutAuditTime = linearLayout3;
        this.linearLayoutAuditTrail = linearLayout4;
        this.listviewWhen = textView2;
        this.listviewWhere = textView3;
        this.listviewWho = textView4;
        this.lockStatus = textView5;
        this.textViewAuditTime = textView6;
        this.textViewTime = textView7;
        this.txClickTag = textView8;
        this.txClickTag1 = textView9;
        this.txViewMap = textView10;
        this.txViewTagName = textView11;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ListviewLayoutAuditTrailBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ListviewLayoutAuditTrailBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.listview_layout_audit_trail, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ListviewLayoutAuditTrailBinding bind(View view) {
        int i = R.id.LinearLayoutDateTime;
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.LinearLayoutDateTime);
        if (linearLayout != null) {
            i = R.id.TextView_calendar;
            TextView textView = (TextView) view.findViewById(R.id.TextView_calendar);
            if (textView != null) {
                i = R.id.bt_tag_name;
                Button button = (Button) view.findViewById(R.id.bt_tag_name);
                if (button != null) {
                    i = R.id.imageView1;
                    ImageView imageView = (ImageView) view.findViewById(R.id.imageView1);
                    if (imageView != null) {
                        i = R.id.imageView2;
                        ImageView imageView2 = (ImageView) view.findViewById(R.id.imageView2);
                        if (imageView2 != null) {
                            i = R.id.imageView3;
                            ImageView imageView3 = (ImageView) view.findViewById(R.id.imageView3);
                            if (imageView3 != null) {
                                i = R.id.imageView4;
                                ImageView imageView4 = (ImageView) view.findViewById(R.id.imageView4);
                                if (imageView4 != null) {
                                    i = R.id.imageView_role;
                                    ImageView imageView5 = (ImageView) view.findViewById(R.id.imageView_role);
                                    if (imageView5 != null) {
                                        i = R.id.linearLayoutAuditTime;
                                        LinearLayout linearLayout2 = (LinearLayout) view.findViewById(R.id.linearLayoutAuditTime);
                                        if (linearLayout2 != null) {
                                            LinearLayout linearLayout3 = (LinearLayout) view;
                                            i = R.id.listview_when;
                                            TextView textView2 = (TextView) view.findViewById(R.id.listview_when);
                                            if (textView2 != null) {
                                                i = R.id.listview_where;
                                                TextView textView3 = (TextView) view.findViewById(R.id.listview_where);
                                                if (textView3 != null) {
                                                    i = R.id.listview_who;
                                                    TextView textView4 = (TextView) view.findViewById(R.id.listview_who);
                                                    if (textView4 != null) {
                                                        i = R.id.lock_status;
                                                        TextView textView5 = (TextView) view.findViewById(R.id.lock_status);
                                                        if (textView5 != null) {
                                                            i = R.id.textView_AuditTime;
                                                            TextView textView6 = (TextView) view.findViewById(R.id.textView_AuditTime);
                                                            if (textView6 != null) {
                                                                i = R.id.textView_time;
                                                                TextView textView7 = (TextView) view.findViewById(R.id.textView_time);
                                                                if (textView7 != null) {
                                                                    i = R.id.tx_click_tag;
                                                                    TextView textView8 = (TextView) view.findViewById(R.id.tx_click_tag);
                                                                    if (textView8 != null) {
                                                                        i = R.id.tx_click_tag1;
                                                                        TextView textView9 = (TextView) view.findViewById(R.id.tx_click_tag1);
                                                                        if (textView9 != null) {
                                                                            i = R.id.tx_view_map;
                                                                            TextView textView10 = (TextView) view.findViewById(R.id.tx_view_map);
                                                                            if (textView10 != null) {
                                                                                i = R.id.tx_view_tag_name;
                                                                                TextView textView11 = (TextView) view.findViewById(R.id.tx_view_tag_name);
                                                                                if (textView11 != null) {
                                                                                    return new ListviewLayoutAuditTrailBinding(linearLayout3, linearLayout, textView, button, imageView, imageView2, imageView3, imageView4, imageView5, linearLayout2, linearLayout3, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9, textView10, textView11);
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
