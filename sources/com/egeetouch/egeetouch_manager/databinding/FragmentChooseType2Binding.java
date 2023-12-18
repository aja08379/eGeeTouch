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
public final class FragmentChooseType2Binding implements ViewBinding {
    public final ConstraintLayout LinearLayout1;
    public final ImageView imageViewAddlockImage;
    private final ConstraintLayout rootView;
    public final TextView textView2;
    public final TextView textViewSerialNumber;

    private FragmentChooseType2Binding(ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ImageView imageView, TextView textView, TextView textView2) {
        this.rootView = constraintLayout;
        this.LinearLayout1 = constraintLayout2;
        this.imageViewAddlockImage = imageView;
        this.textView2 = textView;
        this.textViewSerialNumber = textView2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static FragmentChooseType2Binding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentChooseType2Binding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fragment_choose_type2, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static FragmentChooseType2Binding bind(View view) {
        ConstraintLayout constraintLayout = (ConstraintLayout) view;
        int i = R.id.imageView_addlock_image;
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView_addlock_image);
        if (imageView != null) {
            i = R.id.textView2;
            TextView textView = (TextView) view.findViewById(R.id.textView2);
            if (textView != null) {
                i = R.id.textView_serialNumber;
                TextView textView2 = (TextView) view.findViewById(R.id.textView_serialNumber);
                if (textView2 != null) {
                    return new FragmentChooseType2Binding(constraintLayout, constraintLayout, imageView, textView, textView2);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
