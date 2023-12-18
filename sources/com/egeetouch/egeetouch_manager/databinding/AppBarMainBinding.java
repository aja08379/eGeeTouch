package com.egeetouch.egeetouch_manager.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import com.egeetouch.egeetouch_manager.R;
import com.google.android.material.appbar.AppBarLayout;
/* loaded from: classes.dex */
public final class AppBarMainBinding implements ViewBinding {
    public final AppBarLayout appBarLayout;
    private final ConstraintLayout rootView;
    public final Toolbar toolbar;
    public final TextView tvNavDrawerCount;

    private AppBarMainBinding(ConstraintLayout constraintLayout, AppBarLayout appBarLayout, Toolbar toolbar, TextView textView) {
        this.rootView = constraintLayout;
        this.appBarLayout = appBarLayout;
        this.toolbar = toolbar;
        this.tvNavDrawerCount = textView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static AppBarMainBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AppBarMainBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.app_bar_main, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AppBarMainBinding bind(View view) {
        int i = R.id.appBarLayout;
        AppBarLayout appBarLayout = (AppBarLayout) view.findViewById(R.id.appBarLayout);
        if (appBarLayout != null) {
            i = R.id.toolbar;
            Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
            if (toolbar != null) {
                i = R.id.tv_nav_drawer_count;
                TextView textView = (TextView) view.findViewById(R.id.tv_nav_drawer_count);
                if (textView != null) {
                    return new AppBarMainBinding((ConstraintLayout) view, appBarLayout, toolbar, textView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
