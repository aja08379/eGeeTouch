package com.egeetouch.egeetouch_manager.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import com.egeetouch.egeetouch_manager.R;
/* loaded from: classes.dex */
public final class DialogLocationBinding implements ViewBinding {
    public final Button gpsCancel;
    public final Button gpsOk;
    public final LinearLayout llLink;
    private final ConstraintLayout rootView;
    public final TextView textView13;
    public final TextView textView14;

    private DialogLocationBinding(ConstraintLayout constraintLayout, Button button, Button button2, LinearLayout linearLayout, TextView textView, TextView textView2) {
        this.rootView = constraintLayout;
        this.gpsCancel = button;
        this.gpsOk = button2;
        this.llLink = linearLayout;
        this.textView13 = textView;
        this.textView14 = textView2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static DialogLocationBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static DialogLocationBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.dialog_location, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static DialogLocationBinding bind(View view) {
        int i = R.id.gps_cancel;
        Button button = (Button) view.findViewById(R.id.gps_cancel);
        if (button != null) {
            i = R.id.gps_ok;
            Button button2 = (Button) view.findViewById(R.id.gps_ok);
            if (button2 != null) {
                i = R.id.ll_link;
                LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.ll_link);
                if (linearLayout != null) {
                    i = R.id.textView13;
                    TextView textView = (TextView) view.findViewById(R.id.textView13);
                    if (textView != null) {
                        i = R.id.textView14;
                        TextView textView2 = (TextView) view.findViewById(R.id.textView14);
                        if (textView2 != null) {
                            return new DialogLocationBinding((ConstraintLayout) view, button, button2, linearLayout, textView, textView2);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
