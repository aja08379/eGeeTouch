package com.egeetouch.egeetouch_manager.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import com.egeetouch.egeetouch_manager.R;
/* loaded from: classes.dex */
public final class ListviewAdapterDashboardBinding implements ViewBinding {
    public final ImageView ImageViewLockModel;
    public final TextView TextViewEndTime;
    public final TextView TextViewLockname;
    public final TextView TextViewSerial;
    public final TextView TextViewSharedByEmail;
    public final TextView TextViewStartTime;
    public final TextView dashboardIndex;
    public final LinearLayout lockItem;
    private final RelativeLayout rootView;

    private ListviewAdapterDashboardBinding(RelativeLayout relativeLayout, ImageView imageView, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, LinearLayout linearLayout) {
        this.rootView = relativeLayout;
        this.ImageViewLockModel = imageView;
        this.TextViewEndTime = textView;
        this.TextViewLockname = textView2;
        this.TextViewSerial = textView3;
        this.TextViewSharedByEmail = textView4;
        this.TextViewStartTime = textView5;
        this.dashboardIndex = textView6;
        this.lockItem = linearLayout;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static ListviewAdapterDashboardBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ListviewAdapterDashboardBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.listview_adapter_dashboard, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ListviewAdapterDashboardBinding bind(View view) {
        int i = R.id.ImageView_lock_model;
        ImageView imageView = (ImageView) view.findViewById(R.id.ImageView_lock_model);
        if (imageView != null) {
            i = R.id.TextView_EndTime;
            TextView textView = (TextView) view.findViewById(R.id.TextView_EndTime);
            if (textView != null) {
                i = R.id.TextView_lockname;
                TextView textView2 = (TextView) view.findViewById(R.id.TextView_lockname);
                if (textView2 != null) {
                    i = R.id.TextView_serial;
                    TextView textView3 = (TextView) view.findViewById(R.id.TextView_serial);
                    if (textView3 != null) {
                        i = R.id.TextView_sharedByEmail;
                        TextView textView4 = (TextView) view.findViewById(R.id.TextView_sharedByEmail);
                        if (textView4 != null) {
                            i = R.id.TextView_StartTime;
                            TextView textView5 = (TextView) view.findViewById(R.id.TextView_StartTime);
                            if (textView5 != null) {
                                i = R.id.dashboard_index;
                                TextView textView6 = (TextView) view.findViewById(R.id.dashboard_index);
                                if (textView6 != null) {
                                    i = R.id.lock_item;
                                    LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.lock_item);
                                    if (linearLayout != null) {
                                        return new ListviewAdapterDashboardBinding((RelativeLayout) view, imageView, textView, textView2, textView3, textView4, textView5, textView6, linearLayout);
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
