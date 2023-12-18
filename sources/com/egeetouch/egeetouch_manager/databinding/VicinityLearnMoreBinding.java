package com.egeetouch.egeetouch_manager.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.viewbinding.ViewBinding;
import com.egeetouch.egeetouch_manager.R;
/* loaded from: classes.dex */
public final class VicinityLearnMoreBinding implements ViewBinding {
    public final ImageView dialogImageview;
    private final LinearLayout rootView;

    private VicinityLearnMoreBinding(LinearLayout linearLayout, ImageView imageView) {
        this.rootView = linearLayout;
        this.dialogImageview = imageView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static VicinityLearnMoreBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static VicinityLearnMoreBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.vicinity_learn_more, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static VicinityLearnMoreBinding bind(View view) {
        ImageView imageView = (ImageView) view.findViewById(R.id.dialog_imageview);
        if (imageView != null) {
            return new VicinityLearnMoreBinding((LinearLayout) view, imageView);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(R.id.dialog_imageview)));
    }
}
