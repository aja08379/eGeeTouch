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
public final class ListviewAdapterAddlockBinding implements ViewBinding {
    public final ImageView ImageViewLockModel;
    public final LinearLayout LinearLayout1;
    public final TextView TextViewLocktype;
    private final LinearLayout rootView;

    private ListviewAdapterAddlockBinding(LinearLayout linearLayout, ImageView imageView, LinearLayout linearLayout2, TextView textView) {
        this.rootView = linearLayout;
        this.ImageViewLockModel = imageView;
        this.LinearLayout1 = linearLayout2;
        this.TextViewLocktype = textView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ListviewAdapterAddlockBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ListviewAdapterAddlockBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.listview_adapter_addlock, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ListviewAdapterAddlockBinding bind(View view) {
        int i = R.id.ImageView_lock_model;
        ImageView imageView = (ImageView) view.findViewById(R.id.ImageView_lock_model);
        if (imageView != null) {
            LinearLayout linearLayout = (LinearLayout) view;
            TextView textView = (TextView) view.findViewById(R.id.TextView_locktype);
            if (textView != null) {
                return new ListviewAdapterAddlockBinding(linearLayout, imageView, linearLayout, textView);
            }
            i = R.id.TextView_locktype;
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
