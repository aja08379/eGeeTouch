package com.egeetouch.egeetouch_manager.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import com.egeetouch.egeetouch_manager.R;
/* loaded from: classes.dex */
public final class SpinnerValueLayoutBinding implements ViewBinding {
    private final ConstraintLayout rootView;
    public final ImageView spinnerImage;
    public final TextView spinnerText;

    private SpinnerValueLayoutBinding(ConstraintLayout constraintLayout, ImageView imageView, TextView textView) {
        this.rootView = constraintLayout;
        this.spinnerImage = imageView;
        this.spinnerText = textView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static SpinnerValueLayoutBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static SpinnerValueLayoutBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.spinner_value_layout, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static SpinnerValueLayoutBinding bind(View view) {
        int i = R.id.spinnerImage;
        ImageView imageView = (ImageView) view.findViewById(R.id.spinnerImage);
        if (imageView != null) {
            i = R.id.spinnerText;
            TextView textView = (TextView) view.findViewById(R.id.spinnerText);
            if (textView != null) {
                return new SpinnerValueLayoutBinding((ConstraintLayout) view, imageView, textView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
