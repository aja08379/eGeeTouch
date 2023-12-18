package com.egeetouch.egeetouch_manager.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.viewbinding.ViewBinding;
import com.egeetouch.egeetouch_manager.R;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.makeramen.roundedimageview.RoundedImageView;
/* loaded from: classes.dex */
public final class SlideItemContainerBinding implements ViewBinding {
    public final RoundedImageView imageSlide;
    public final ShimmerFrameLayout imgLoad;
    private final LinearLayout rootView;

    private SlideItemContainerBinding(LinearLayout linearLayout, RoundedImageView roundedImageView, ShimmerFrameLayout shimmerFrameLayout) {
        this.rootView = linearLayout;
        this.imageSlide = roundedImageView;
        this.imgLoad = shimmerFrameLayout;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static SlideItemContainerBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static SlideItemContainerBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.slide_item_container, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static SlideItemContainerBinding bind(View view) {
        int i = R.id.imageSlide;
        RoundedImageView roundedImageView = (RoundedImageView) view.findViewById(R.id.imageSlide);
        if (roundedImageView != null) {
            i = R.id.imgLoad;
            ShimmerFrameLayout shimmerFrameLayout = (ShimmerFrameLayout) view.findViewById(R.id.imgLoad);
            if (shimmerFrameLayout != null) {
                return new SlideItemContainerBinding((LinearLayout) view, roundedImageView, shimmerFrameLayout);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
