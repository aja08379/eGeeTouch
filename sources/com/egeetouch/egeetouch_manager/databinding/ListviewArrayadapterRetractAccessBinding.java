package com.egeetouch.egeetouch_manager.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import com.egeetouch.egeetouch_manager.R;
/* loaded from: classes.dex */
public final class ListviewArrayadapterRetractAccessBinding implements ViewBinding {
    public final TextView EndTime;
    public final TextView emailId;
    public final TextView lockName;
    public final TextView lockStatus;
    private final LinearLayout rootView;
    public final TextView startTime;

    private ListviewArrayadapterRetractAccessBinding(LinearLayout linearLayout, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5) {
        this.rootView = linearLayout;
        this.EndTime = textView;
        this.emailId = textView2;
        this.lockName = textView3;
        this.lockStatus = textView4;
        this.startTime = textView5;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ListviewArrayadapterRetractAccessBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ListviewArrayadapterRetractAccessBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.listview_arrayadapter_retract_access, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ListviewArrayadapterRetractAccessBinding bind(View view) {
        int i = R.id.End_time;
        TextView textView = (TextView) view.findViewById(R.id.End_time);
        if (textView != null) {
            i = R.id.email_id;
            TextView textView2 = (TextView) view.findViewById(R.id.email_id);
            if (textView2 != null) {
                i = R.id.lock_name;
                TextView textView3 = (TextView) view.findViewById(R.id.lock_name);
                if (textView3 != null) {
                    i = R.id.lock_status;
                    TextView textView4 = (TextView) view.findViewById(R.id.lock_status);
                    if (textView4 != null) {
                        i = R.id.start_time;
                        TextView textView5 = (TextView) view.findViewById(R.id.start_time);
                        if (textView5 != null) {
                            return new ListviewArrayadapterRetractAccessBinding((LinearLayout) view, textView, textView2, textView3, textView4, textView5);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
