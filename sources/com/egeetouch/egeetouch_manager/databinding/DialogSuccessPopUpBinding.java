package com.egeetouch.egeetouch_manager.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import com.egeetouch.egeetouch_manager.R;
/* loaded from: classes.dex */
public final class DialogSuccessPopUpBinding implements ViewBinding {
    public final Button done;
    public final ImageView imageView11;
    private final ConstraintLayout rootView;
    public final TextView textView18;
    public final TextView textView19;

    private DialogSuccessPopUpBinding(ConstraintLayout constraintLayout, Button button, ImageView imageView, TextView textView, TextView textView2) {
        this.rootView = constraintLayout;
        this.done = button;
        this.imageView11 = imageView;
        this.textView18 = textView;
        this.textView19 = textView2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static DialogSuccessPopUpBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static DialogSuccessPopUpBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.dialog_success_pop_up, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static DialogSuccessPopUpBinding bind(View view) {
        int i = R.id.done;
        Button button = (Button) view.findViewById(R.id.done);
        if (button != null) {
            i = R.id.imageView11;
            ImageView imageView = (ImageView) view.findViewById(R.id.imageView11);
            if (imageView != null) {
                i = R.id.textView18;
                TextView textView = (TextView) view.findViewById(R.id.textView18);
                if (textView != null) {
                    i = R.id.textView19;
                    TextView textView2 = (TextView) view.findViewById(R.id.textView19);
                    if (textView2 != null) {
                        return new DialogSuccessPopUpBinding((ConstraintLayout) view, button, imageView, textView, textView2);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
