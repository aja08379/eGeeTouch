package com.egeetouch.egeetouch_manager.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import com.egeetouch.egeetouch_manager.R;
/* loaded from: classes.dex */
public final class ListviewAdapterShareHistoryBinding implements ViewBinding {
    public final LinearLayout LinearLayout1;
    public final TextView TextViewLockname;
    public final TextView TextViewShareOnDate;
    private final LinearLayout rootView;
    public final TextView textViewEndDate;
    public final TextView textViewSharedToEmail;
    public final TextView textViewStartDate;

    private ListviewAdapterShareHistoryBinding(LinearLayout linearLayout, LinearLayout linearLayout2, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5) {
        this.rootView = linearLayout;
        this.LinearLayout1 = linearLayout2;
        this.TextViewLockname = textView;
        this.TextViewShareOnDate = textView2;
        this.textViewEndDate = textView3;
        this.textViewSharedToEmail = textView4;
        this.textViewStartDate = textView5;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ListviewAdapterShareHistoryBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ListviewAdapterShareHistoryBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.listview_adapter_share_history, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ListviewAdapterShareHistoryBinding bind(View view) {
        LinearLayout linearLayout = (LinearLayout) view;
        int i = R.id.TextView_lockname;
        TextView textView = (TextView) view.findViewById(R.id.TextView_lockname);
        if (textView != null) {
            i = R.id.TextView_share_on_date;
            TextView textView2 = (TextView) view.findViewById(R.id.TextView_share_on_date);
            if (textView2 != null) {
                i = R.id.textView_end_date;
                TextView textView3 = (TextView) view.findViewById(R.id.textView_end_date);
                if (textView3 != null) {
                    i = R.id.textView_shared_to_email;
                    TextView textView4 = (TextView) view.findViewById(R.id.textView_shared_to_email);
                    if (textView4 != null) {
                        i = R.id.textView_start_date;
                        TextView textView5 = (TextView) view.findViewById(R.id.textView_start_date);
                        if (textView5 != null) {
                            return new ListviewAdapterShareHistoryBinding(linearLayout, linearLayout, textView, textView2, textView3, textView4, textView5);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
