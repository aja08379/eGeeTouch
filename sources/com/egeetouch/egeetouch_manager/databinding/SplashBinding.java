package com.egeetouch.egeetouch_manager.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import com.egeetouch.egeetouch_manager.R;
/* loaded from: classes.dex */
public final class SplashBinding implements ViewBinding {
    public final ConstraintLayout RelativeLayout1;
    public final ImageView imageViewLogoWhite;
    public final ProgressBar progressBar;
    private final ConstraintLayout rootView;
    public final TextView textViewSplash;

    private SplashBinding(ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ImageView imageView, ProgressBar progressBar, TextView textView) {
        this.rootView = constraintLayout;
        this.RelativeLayout1 = constraintLayout2;
        this.imageViewLogoWhite = imageView;
        this.progressBar = progressBar;
        this.textViewSplash = textView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static SplashBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static SplashBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.splash, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static SplashBinding bind(View view) {
        ConstraintLayout constraintLayout = (ConstraintLayout) view;
        int i = R.id.imageView_logo_white;
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView_logo_white);
        if (imageView != null) {
            i = R.id.progressBar;
            ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
            if (progressBar != null) {
                i = R.id.textView_splash;
                TextView textView = (TextView) view.findViewById(R.id.textView_splash);
                if (textView != null) {
                    return new SplashBinding(constraintLayout, constraintLayout, imageView, progressBar, textView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
