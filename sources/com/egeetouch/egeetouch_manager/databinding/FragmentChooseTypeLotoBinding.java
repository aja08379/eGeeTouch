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
public final class FragmentChooseTypeLotoBinding implements ViewBinding {
    public final ConstraintLayout LinearLayout;
    public final ImageView imageViewAddlockImage;
    private final ConstraintLayout rootView;
    public final TextView textView2;
    public final TextView textViewSerialNumberLoto;
    public final TextView tvConnectionState;

    private FragmentChooseTypeLotoBinding(ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ImageView imageView, TextView textView, TextView textView2, TextView textView3) {
        this.rootView = constraintLayout;
        this.LinearLayout = constraintLayout2;
        this.imageViewAddlockImage = imageView;
        this.textView2 = textView;
        this.textViewSerialNumberLoto = textView2;
        this.tvConnectionState = textView3;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static FragmentChooseTypeLotoBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentChooseTypeLotoBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fragment_choose_type_loto, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static FragmentChooseTypeLotoBinding bind(View view) {
        ConstraintLayout constraintLayout = (ConstraintLayout) view;
        int i = R.id.imageView_addlock_image;
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView_addlock_image);
        if (imageView != null) {
            i = R.id.textView2;
            TextView textView = (TextView) view.findViewById(R.id.textView2);
            if (textView != null) {
                i = R.id.textView_serialNumber_loto;
                TextView textView2 = (TextView) view.findViewById(R.id.textView_serialNumber_loto);
                if (textView2 != null) {
                    i = R.id.tv_connectionState;
                    TextView textView3 = (TextView) view.findViewById(R.id.tv_connectionState);
                    if (textView3 != null) {
                        return new FragmentChooseTypeLotoBinding(constraintLayout, constraintLayout, imageView, textView, textView2, textView3);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
