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
public final class NavHeaderMainBinding implements ViewBinding {
    public final TextView TextViewUserEmail;
    public final TextView TextViewUserName;
    public final ImageView imageView;
    private final LinearLayout rootView;
    public final TextView textViewCurrentDateTime;
    public final TextView textViewVersion;

    private NavHeaderMainBinding(LinearLayout linearLayout, TextView textView, TextView textView2, ImageView imageView, TextView textView3, TextView textView4) {
        this.rootView = linearLayout;
        this.TextViewUserEmail = textView;
        this.TextViewUserName = textView2;
        this.imageView = imageView;
        this.textViewCurrentDateTime = textView3;
        this.textViewVersion = textView4;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static NavHeaderMainBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static NavHeaderMainBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.nav_header_main, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static NavHeaderMainBinding bind(View view) {
        int i = R.id.TextView_user_email;
        TextView textView = (TextView) view.findViewById(R.id.TextView_user_email);
        if (textView != null) {
            i = R.id.TextView_user_name;
            TextView textView2 = (TextView) view.findViewById(R.id.TextView_user_name);
            if (textView2 != null) {
                i = R.id.imageView;
                ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
                if (imageView != null) {
                    i = R.id.textView_currentDateTime;
                    TextView textView3 = (TextView) view.findViewById(R.id.textView_currentDateTime);
                    if (textView3 != null) {
                        i = R.id.textView_version;
                        TextView textView4 = (TextView) view.findViewById(R.id.textView_version);
                        if (textView4 != null) {
                            return new NavHeaderMainBinding((LinearLayout) view, textView, textView2, imageView, textView3, textView4);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
