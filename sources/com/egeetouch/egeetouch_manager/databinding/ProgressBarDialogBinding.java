package com.egeetouch.egeetouch_manager.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import com.egeetouch.egeetouch_manager.R;
/* loaded from: classes.dex */
public final class ProgressBarDialogBinding implements ViewBinding {
    private final RelativeLayout rootView;
    public final TextView titleDotsTv;
    public final TextView titleTv;
    public final TextView tvContent;

    private ProgressBarDialogBinding(RelativeLayout relativeLayout, TextView textView, TextView textView2, TextView textView3) {
        this.rootView = relativeLayout;
        this.titleDotsTv = textView;
        this.titleTv = textView2;
        this.tvContent = textView3;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static ProgressBarDialogBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ProgressBarDialogBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.progress_bar_dialog, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ProgressBarDialogBinding bind(View view) {
        int i = R.id.title_dots_tv;
        TextView textView = (TextView) view.findViewById(R.id.title_dots_tv);
        if (textView != null) {
            i = R.id.title_tv;
            TextView textView2 = (TextView) view.findViewById(R.id.title_tv);
            if (textView2 != null) {
                i = R.id.tv_content;
                TextView textView3 = (TextView) view.findViewById(R.id.tv_content);
                if (textView3 != null) {
                    return new ProgressBarDialogBinding((RelativeLayout) view, textView, textView2, textView3);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
