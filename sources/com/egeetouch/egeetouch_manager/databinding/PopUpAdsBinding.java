package com.egeetouch.egeetouch_manager.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import com.egeetouch.egeetouch_manager.R;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.imageview.ShapeableImageView;
/* loaded from: classes.dex */
public final class PopUpAdsBinding implements ViewBinding {
    public final ImageView closeBtn;
    public final TextView dismissText;
    public final ShimmerFrameLayout imgLoad;
    public final ShapeableImageView imgpopup;
    public final RelativeLayout linearLayout;
    private final ConstraintLayout rootView;

    private PopUpAdsBinding(ConstraintLayout constraintLayout, ImageView imageView, TextView textView, ShimmerFrameLayout shimmerFrameLayout, ShapeableImageView shapeableImageView, RelativeLayout relativeLayout) {
        this.rootView = constraintLayout;
        this.closeBtn = imageView;
        this.dismissText = textView;
        this.imgLoad = shimmerFrameLayout;
        this.imgpopup = shapeableImageView;
        this.linearLayout = relativeLayout;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static PopUpAdsBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static PopUpAdsBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.pop_up_ads, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static PopUpAdsBinding bind(View view) {
        int i = R.id.close_btn;
        ImageView imageView = (ImageView) view.findViewById(R.id.close_btn);
        if (imageView != null) {
            i = R.id.dismiss_text;
            TextView textView = (TextView) view.findViewById(R.id.dismiss_text);
            if (textView != null) {
                i = R.id.imgLoad;
                ShimmerFrameLayout shimmerFrameLayout = (ShimmerFrameLayout) view.findViewById(R.id.imgLoad);
                if (shimmerFrameLayout != null) {
                    i = R.id.imgpopup;
                    ShapeableImageView shapeableImageView = (ShapeableImageView) view.findViewById(R.id.imgpopup);
                    if (shapeableImageView != null) {
                        i = R.id.linearLayout;
                        RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.linearLayout);
                        if (relativeLayout != null) {
                            return new PopUpAdsBinding((ConstraintLayout) view, imageView, textView, shimmerFrameLayout, shapeableImageView, relativeLayout);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
