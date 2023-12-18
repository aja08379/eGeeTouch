package com.egeetouch.egeetouch_manager.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import com.egeetouch.egeetouch_manager.R;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.imageview.ShapeableImageView;
/* loaded from: classes.dex */
public final class ListviewNewsBinding implements ViewBinding {
    public final TextView description;
    public final ShimmerFrameLayout imgLoad;
    public final ShapeableImageView imgView;
    public final TextView judul;
    private final LinearLayout rootView;

    private ListviewNewsBinding(LinearLayout linearLayout, TextView textView, ShimmerFrameLayout shimmerFrameLayout, ShapeableImageView shapeableImageView, TextView textView2) {
        this.rootView = linearLayout;
        this.description = textView;
        this.imgLoad = shimmerFrameLayout;
        this.imgView = shapeableImageView;
        this.judul = textView2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ListviewNewsBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ListviewNewsBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.listview_news, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ListviewNewsBinding bind(View view) {
        int i = R.id.description;
        TextView textView = (TextView) view.findViewById(R.id.description);
        if (textView != null) {
            i = R.id.imgLoad;
            ShimmerFrameLayout shimmerFrameLayout = (ShimmerFrameLayout) view.findViewById(R.id.imgLoad);
            if (shimmerFrameLayout != null) {
                i = R.id.imgView;
                ShapeableImageView shapeableImageView = (ShapeableImageView) view.findViewById(R.id.imgView);
                if (shapeableImageView != null) {
                    i = R.id.judul;
                    TextView textView2 = (TextView) view.findViewById(R.id.judul);
                    if (textView2 != null) {
                        return new ListviewNewsBinding((LinearLayout) view, textView, shimmerFrameLayout, shapeableImageView, textView2);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
