package com.egeetouch.egeetouch_manager.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import com.egeetouch.egeetouch_manager.R;
/* loaded from: classes.dex */
public final class AccessLogAudittrailDetailBinding implements ViewBinding {
    public final LinearLayout LinearLayoutDateTime;
    public final TextView TextViewAddress;
    public final TextView TextViewEmail;
    public final TextView TextViewLockStatus;
    public final TextView TextViewTime;
    public final ImageView imageView10;
    public final ImageView imageView2;
    public final ImageView imageView3;
    public final ImageView imageView4;
    public final ImageView imageViewRole;
    public final LinearLayout linearLayoutAuditTime;
    public final LinearLayout linearLayoutAuditTrail;
    private final LinearLayout rootView;
    public final TextView textViewAuditTime;
    public final TextView textViewDate;
    public final TextView textViewTime;
    public final TextView tvIndex;
    public final TextView txClickTag;
    public final TextView txClickTag1;
    public final TextView txViewTagName;

    private AccessLogAudittrailDetailBinding(LinearLayout linearLayout, LinearLayout linearLayout2, TextView textView, TextView textView2, TextView textView3, TextView textView4, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, ImageView imageView5, LinearLayout linearLayout3, LinearLayout linearLayout4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9, TextView textView10, TextView textView11) {
        this.rootView = linearLayout;
        this.LinearLayoutDateTime = linearLayout2;
        this.TextViewAddress = textView;
        this.TextViewEmail = textView2;
        this.TextViewLockStatus = textView3;
        this.TextViewTime = textView4;
        this.imageView10 = imageView;
        this.imageView2 = imageView2;
        this.imageView3 = imageView3;
        this.imageView4 = imageView4;
        this.imageViewRole = imageView5;
        this.linearLayoutAuditTime = linearLayout3;
        this.linearLayoutAuditTrail = linearLayout4;
        this.textViewAuditTime = textView5;
        this.textViewDate = textView6;
        this.textViewTime = textView7;
        this.tvIndex = textView8;
        this.txClickTag = textView9;
        this.txClickTag1 = textView10;
        this.txViewTagName = textView11;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static AccessLogAudittrailDetailBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AccessLogAudittrailDetailBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.access_log_audittrail_detail, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AccessLogAudittrailDetailBinding bind(View view) {
        int i = R.id.LinearLayoutDateTime;
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.LinearLayoutDateTime);
        if (linearLayout != null) {
            i = R.id.TextView_address;
            TextView textView = (TextView) view.findViewById(R.id.TextView_address);
            if (textView != null) {
                i = R.id.TextView_email;
                TextView textView2 = (TextView) view.findViewById(R.id.TextView_email);
                if (textView2 != null) {
                    i = R.id.TextView_lockStatus;
                    TextView textView3 = (TextView) view.findViewById(R.id.TextView_lockStatus);
                    if (textView3 != null) {
                        i = R.id.TextView_time;
                        TextView textView4 = (TextView) view.findViewById(R.id.TextView_time);
                        if (textView4 != null) {
                            i = R.id.imageView10;
                            ImageView imageView = (ImageView) view.findViewById(R.id.imageView10);
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
                                                    i = R.id.textView_AuditTime;
                                                    TextView textView5 = (TextView) view.findViewById(R.id.textView_AuditTime);
                                                    if (textView5 != null) {
                                                        i = R.id.textView_date;
                                                        TextView textView6 = (TextView) view.findViewById(R.id.textView_date);
                                                        if (textView6 != null) {
                                                            i = R.id.textView_time;
                                                            TextView textView7 = (TextView) view.findViewById(R.id.textView_time);
                                                            if (textView7 != null) {
                                                                i = R.id.tv_index;
                                                                TextView textView8 = (TextView) view.findViewById(R.id.tv_index);
                                                                if (textView8 != null) {
                                                                    i = R.id.tx_click_tag;
                                                                    TextView textView9 = (TextView) view.findViewById(R.id.tx_click_tag);
                                                                    if (textView9 != null) {
                                                                        i = R.id.tx_click_tag1;
                                                                        TextView textView10 = (TextView) view.findViewById(R.id.tx_click_tag1);
                                                                        if (textView10 != null) {
                                                                            i = R.id.tx_view_tag_name;
                                                                            TextView textView11 = (TextView) view.findViewById(R.id.tx_view_tag_name);
                                                                            if (textView11 != null) {
                                                                                return new AccessLogAudittrailDetailBinding(linearLayout3, linearLayout, textView, textView2, textView3, textView4, imageView, imageView2, imageView3, imageView4, imageView5, linearLayout2, linearLayout3, textView5, textView6, textView7, textView8, textView9, textView10, textView11);
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
