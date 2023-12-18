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
public final class ListviewAdapterAddshareuserBinding implements ViewBinding {
    public final LinearLayout LinearLayout1;
    public final TextView TextViewUser;
    public final TextView TextViewUserEmail;
    public final ImageView doesntExist;
    private final LinearLayout rootView;

    private ListviewAdapterAddshareuserBinding(LinearLayout linearLayout, LinearLayout linearLayout2, TextView textView, TextView textView2, ImageView imageView) {
        this.rootView = linearLayout;
        this.LinearLayout1 = linearLayout2;
        this.TextViewUser = textView;
        this.TextViewUserEmail = textView2;
        this.doesntExist = imageView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ListviewAdapterAddshareuserBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ListviewAdapterAddshareuserBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.listview_adapter_addshareuser, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ListviewAdapterAddshareuserBinding bind(View view) {
        LinearLayout linearLayout = (LinearLayout) view;
        int i = R.id.TextView_user;
        TextView textView = (TextView) view.findViewById(R.id.TextView_user);
        if (textView != null) {
            i = R.id.TextView_user_email;
            TextView textView2 = (TextView) view.findViewById(R.id.TextView_user_email);
            if (textView2 != null) {
                i = R.id.doesnt_exist;
                ImageView imageView = (ImageView) view.findViewById(R.id.doesnt_exist);
                if (imageView != null) {
                    return new ListviewAdapterAddshareuserBinding(linearLayout, linearLayout, textView, textView2, imageView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
