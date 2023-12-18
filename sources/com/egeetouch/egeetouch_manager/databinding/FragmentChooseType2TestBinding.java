package com.egeetouch.egeetouch_manager.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import com.egeetouch.egeetouch_manager.R;
/* loaded from: classes.dex */
public final class FragmentChooseType2TestBinding implements ViewBinding {
    public final ConstraintLayout LinearLayout1;
    public final ImageView imageViewAddlockImage;
    public final ImageView imageViewAddlockIp45;
    public final LinearLayout ll3rdGen;
    public final LinearLayout llIp45;
    private final ConstraintLayout rootView;
    public final TextView textView2;
    public final TextView textViewSerialNumber;
    public final TextView tvLockGen;
    public final View viewLine;

    private FragmentChooseType2TestBinding(ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ImageView imageView, ImageView imageView2, LinearLayout linearLayout, LinearLayout linearLayout2, TextView textView, TextView textView2, TextView textView3, View view) {
        this.rootView = constraintLayout;
        this.LinearLayout1 = constraintLayout2;
        this.imageViewAddlockImage = imageView;
        this.imageViewAddlockIp45 = imageView2;
        this.ll3rdGen = linearLayout;
        this.llIp45 = linearLayout2;
        this.textView2 = textView;
        this.textViewSerialNumber = textView2;
        this.tvLockGen = textView3;
        this.viewLine = view;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static FragmentChooseType2TestBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentChooseType2TestBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fragment_choose_type2_test, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static FragmentChooseType2TestBinding bind(View view) {
        ConstraintLayout constraintLayout = (ConstraintLayout) view;
        int i = R.id.imageView_addlock_image;
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView_addlock_image);
        if (imageView != null) {
            i = R.id.imageView_addlock_ip45;
            ImageView imageView2 = (ImageView) view.findViewById(R.id.imageView_addlock_ip45);
            if (imageView2 != null) {
                i = R.id.ll_3rd_gen;
                LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.ll_3rd_gen);
                if (linearLayout != null) {
                    i = R.id.ll_ip45;
                    LinearLayout linearLayout2 = (LinearLayout) view.findViewById(R.id.ll_ip45);
                    if (linearLayout2 != null) {
                        i = R.id.textView2;
                        TextView textView = (TextView) view.findViewById(R.id.textView2);
                        if (textView != null) {
                            i = R.id.textView_serialNumber;
                            TextView textView2 = (TextView) view.findViewById(R.id.textView_serialNumber);
                            if (textView2 != null) {
                                i = R.id.tv_lock_gen;
                                TextView textView3 = (TextView) view.findViewById(R.id.tv_lock_gen);
                                if (textView3 != null) {
                                    i = R.id.view_line;
                                    View findViewById = view.findViewById(R.id.view_line);
                                    if (findViewById != null) {
                                        return new FragmentChooseType2TestBinding(constraintLayout, constraintLayout, imageView, imageView2, linearLayout, linearLayout2, textView, textView2, textView3, findViewById);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
