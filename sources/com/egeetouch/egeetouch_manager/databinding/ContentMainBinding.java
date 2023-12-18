package com.egeetouch.egeetouch_manager.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;
import com.egeetouch.egeetouch_manager.R;
/* loaded from: classes.dex */
public final class ContentMainBinding implements ViewBinding {
    public final ConstraintLayout container;
    public final Button fab;
    public final Button fabAccess;
    public final Button fabAdminAccessLocks;
    public final Button fabShare;
    public final ListView listviewDashboard;
    public final ViewPager myviewpager;
    public final SwipeRefreshLayout pullToRefresh;
    private final ConstraintLayout rootView;
    public final TextView textView12;
    public final ViewPager2 viewPagerImageSlider;

    private ContentMainBinding(ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, Button button, Button button2, Button button3, Button button4, ListView listView, ViewPager viewPager, SwipeRefreshLayout swipeRefreshLayout, TextView textView, ViewPager2 viewPager2) {
        this.rootView = constraintLayout;
        this.container = constraintLayout2;
        this.fab = button;
        this.fabAccess = button2;
        this.fabAdminAccessLocks = button3;
        this.fabShare = button4;
        this.listviewDashboard = listView;
        this.myviewpager = viewPager;
        this.pullToRefresh = swipeRefreshLayout;
        this.textView12 = textView;
        this.viewPagerImageSlider = viewPager2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ContentMainBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ContentMainBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.content_main, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ContentMainBinding bind(View view) {
        ConstraintLayout constraintLayout = (ConstraintLayout) view;
        int i = R.id.fab;
        Button button = (Button) view.findViewById(R.id.fab);
        if (button != null) {
            i = R.id.fab_access;
            Button button2 = (Button) view.findViewById(R.id.fab_access);
            if (button2 != null) {
                i = R.id.fab_admin_access_locks;
                Button button3 = (Button) view.findViewById(R.id.fab_admin_access_locks);
                if (button3 != null) {
                    i = R.id.fab_share;
                    Button button4 = (Button) view.findViewById(R.id.fab_share);
                    if (button4 != null) {
                        i = R.id.listview_dashboard;
                        ListView listView = (ListView) view.findViewById(R.id.listview_dashboard);
                        if (listView != null) {
                            i = R.id.myviewpager;
                            ViewPager viewPager = (ViewPager) view.findViewById(R.id.myviewpager);
                            if (viewPager != null) {
                                i = R.id.pullToRefresh;
                                SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.pullToRefresh);
                                if (swipeRefreshLayout != null) {
                                    i = R.id.textView12;
                                    TextView textView = (TextView) view.findViewById(R.id.textView12);
                                    if (textView != null) {
                                        i = R.id.viewPagerImageSlider;
                                        ViewPager2 viewPager2 = (ViewPager2) view.findViewById(R.id.viewPagerImageSlider);
                                        if (viewPager2 != null) {
                                            return new ContentMainBinding(constraintLayout, constraintLayout, button, button2, button3, button4, listView, viewPager, swipeRefreshLayout, textView, viewPager2);
                                        }
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
