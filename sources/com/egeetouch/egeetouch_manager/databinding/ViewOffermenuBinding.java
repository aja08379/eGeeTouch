package com.egeetouch.egeetouch_manager.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import com.egeetouch.egeetouch_manager.R;
/* loaded from: classes.dex */
public final class ViewOffermenuBinding implements ViewBinding {
    public final TextView freeUpgradeTextView;
    private final LinearLayout rootView;

    private ViewOffermenuBinding(LinearLayout linearLayout, TextView textView) {
        this.rootView = linearLayout;
        this.freeUpgradeTextView = textView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ViewOffermenuBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ViewOffermenuBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.view_offermenu, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ViewOffermenuBinding bind(View view) {
        TextView textView = (TextView) view.findViewById(R.id.freeUpgradeTextView);
        if (textView != null) {
            return new ViewOffermenuBinding((LinearLayout) view, textView);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(R.id.freeUpgradeTextView)));
    }
}
