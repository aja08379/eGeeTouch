package com.egeetouch.egeetouch_manager.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import com.egeetouch.egeetouch_manager.R;
/* loaded from: classes.dex */
public final class DialogPopUpCommercialBinding implements ViewBinding {
    public final Button button3;
    public final Button claim;
    private final ConstraintLayout rootView;
    public final TextView textView15;
    public final TextView textView16;
    public final TextView textView17;

    private DialogPopUpCommercialBinding(ConstraintLayout constraintLayout, Button button, Button button2, TextView textView, TextView textView2, TextView textView3) {
        this.rootView = constraintLayout;
        this.button3 = button;
        this.claim = button2;
        this.textView15 = textView;
        this.textView16 = textView2;
        this.textView17 = textView3;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static DialogPopUpCommercialBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static DialogPopUpCommercialBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.dialog_pop_up_commercial, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static DialogPopUpCommercialBinding bind(View view) {
        int i = R.id.button3;
        Button button = (Button) view.findViewById(R.id.button3);
        if (button != null) {
            i = R.id.claim;
            Button button2 = (Button) view.findViewById(R.id.claim);
            if (button2 != null) {
                i = R.id.textView15;
                TextView textView = (TextView) view.findViewById(R.id.textView15);
                if (textView != null) {
                    i = R.id.textView16;
                    TextView textView2 = (TextView) view.findViewById(R.id.textView16);
                    if (textView2 != null) {
                        i = R.id.textView17;
                        TextView textView3 = (TextView) view.findViewById(R.id.textView17);
                        if (textView3 != null) {
                            return new DialogPopUpCommercialBinding((ConstraintLayout) view, button, button2, textView, textView2, textView3);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
