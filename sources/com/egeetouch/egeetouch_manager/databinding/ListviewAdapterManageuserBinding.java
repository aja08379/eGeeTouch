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
public final class ListviewAdapterManageuserBinding implements ViewBinding {
    public final ImageView ImageViewUserPic;
    public final RelativeLayout RelativeLayout1;
    public final TextView TextViewUsername;
    private final LinearLayout rootView;

    private ListviewAdapterManageuserBinding(LinearLayout linearLayout, ImageView imageView, RelativeLayout relativeLayout, TextView textView) {
        this.rootView = linearLayout;
        this.ImageViewUserPic = imageView;
        this.RelativeLayout1 = relativeLayout;
        this.TextViewUsername = textView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ListviewAdapterManageuserBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ListviewAdapterManageuserBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.listview_adapter_manageuser, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ListviewAdapterManageuserBinding bind(View view) {
        int i = R.id.ImageView_user_pic;
        ImageView imageView = (ImageView) view.findViewById(R.id.ImageView_user_pic);
        if (imageView != null) {
            i = R.id.RelativeLayout1;
            RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.RelativeLayout1);
            if (relativeLayout != null) {
                i = R.id.TextView_username;
                TextView textView = (TextView) view.findViewById(R.id.TextView_username);
                if (textView != null) {
                    return new ListviewAdapterManageuserBinding((LinearLayout) view, imageView, relativeLayout, textView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
