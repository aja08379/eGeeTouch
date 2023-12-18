package com.egeetouch.egeetouch_manager.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewbinding.ViewBinding;
import com.egeetouch.egeetouch_manager.R;
import com.google.android.material.navigation.NavigationView;
/* loaded from: classes.dex */
public final class ActivityMainBinding implements ViewBinding {
    public final CoordinatorLayout coordinatorLayout;
    public final DrawerLayout drawerLayout;
    public final NavigationView navView;
    private final CoordinatorLayout rootView;

    private ActivityMainBinding(CoordinatorLayout coordinatorLayout, CoordinatorLayout coordinatorLayout2, DrawerLayout drawerLayout, NavigationView navigationView) {
        this.rootView = coordinatorLayout;
        this.coordinatorLayout = coordinatorLayout2;
        this.drawerLayout = drawerLayout;
        this.navView = navigationView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public CoordinatorLayout getRoot() {
        return this.rootView;
    }

    public static ActivityMainBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ActivityMainBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_main, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivityMainBinding bind(View view) {
        CoordinatorLayout coordinatorLayout = (CoordinatorLayout) view;
        int i = R.id.drawer_layout;
        DrawerLayout drawerLayout = (DrawerLayout) view.findViewById(R.id.drawer_layout);
        if (drawerLayout != null) {
            i = R.id.nav_view;
            NavigationView navigationView = (NavigationView) view.findViewById(R.id.nav_view);
            if (navigationView != null) {
                return new ActivityMainBinding(coordinatorLayout, coordinatorLayout, drawerLayout, navigationView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
