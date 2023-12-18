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
public final class ListviewAdapterAccessLogLockListBinding implements ViewBinding {
    public final ImageView ImageViewLockModel;
    public final TextView TextViewLockName;
    public final TextView TextViewSerial;
    public final LinearLayout lockSerialList;
    private final LinearLayout rootView;

    private ListviewAdapterAccessLogLockListBinding(LinearLayout linearLayout, ImageView imageView, TextView textView, TextView textView2, LinearLayout linearLayout2) {
        this.rootView = linearLayout;
        this.ImageViewLockModel = imageView;
        this.TextViewLockName = textView;
        this.TextViewSerial = textView2;
        this.lockSerialList = linearLayout2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ListviewAdapterAccessLogLockListBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ListviewAdapterAccessLogLockListBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.listview_adapter_access_log_lock_list, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ListviewAdapterAccessLogLockListBinding bind(View view) {
        int i = R.id.ImageView_lock_model;
        ImageView imageView = (ImageView) view.findViewById(R.id.ImageView_lock_model);
        if (imageView != null) {
            i = R.id.TextView_lockName;
            TextView textView = (TextView) view.findViewById(R.id.TextView_lockName);
            if (textView != null) {
                i = R.id.TextView_serial;
                TextView textView2 = (TextView) view.findViewById(R.id.TextView_serial);
                if (textView2 != null) {
                    LinearLayout linearLayout = (LinearLayout) view;
                    return new ListviewAdapterAccessLogLockListBinding(linearLayout, imageView, textView, textView2, linearLayout);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
