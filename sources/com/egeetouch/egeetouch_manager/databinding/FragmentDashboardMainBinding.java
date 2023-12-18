package com.egeetouch.egeetouch_manager.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.VideoView;
import androidx.viewbinding.ViewBinding;
import androidx.viewpager.widget.ViewPager;
import com.egeetouch.egeetouch_manager.R;
/* loaded from: classes.dex */
public final class FragmentDashboardMainBinding implements ViewBinding {
    public final LinearLayout LinearLayout1;
    public final LinearLayout LinearLayoutDashboardContent;
    public final RelativeLayout RelativeLayout1;
    public final Button button1;
    public final Button button2;
    public final ImageView imageView1;
    public final ImageView imageViewAddlock;
    public final ListView listviewDashboard;
    public final ViewPager myviewpager;
    private final LinearLayout rootView;
    public final VideoView videoView1;

    private FragmentDashboardMainBinding(LinearLayout linearLayout, LinearLayout linearLayout2, LinearLayout linearLayout3, RelativeLayout relativeLayout, Button button, Button button2, ImageView imageView, ImageView imageView2, ListView listView, ViewPager viewPager, VideoView videoView) {
        this.rootView = linearLayout;
        this.LinearLayout1 = linearLayout2;
        this.LinearLayoutDashboardContent = linearLayout3;
        this.RelativeLayout1 = relativeLayout;
        this.button1 = button;
        this.button2 = button2;
        this.imageView1 = imageView;
        this.imageViewAddlock = imageView2;
        this.listviewDashboard = listView;
        this.myviewpager = viewPager;
        this.videoView1 = videoView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static FragmentDashboardMainBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentDashboardMainBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fragment_dashboard_main, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static FragmentDashboardMainBinding bind(View view) {
        LinearLayout linearLayout = (LinearLayout) view;
        int i = R.id.LinearLayout_dashboard_content;
        LinearLayout linearLayout2 = (LinearLayout) view.findViewById(R.id.LinearLayout_dashboard_content);
        if (linearLayout2 != null) {
            i = R.id.RelativeLayout1;
            RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.RelativeLayout1);
            if (relativeLayout != null) {
                i = R.id.button1;
                Button button = (Button) view.findViewById(R.id.button1);
                if (button != null) {
                    i = R.id.button2;
                    Button button2 = (Button) view.findViewById(R.id.button2);
                    if (button2 != null) {
                        i = R.id.imageView1;
                        ImageView imageView = (ImageView) view.findViewById(R.id.imageView1);
                        if (imageView != null) {
                            i = R.id.imageView_addlock;
                            ImageView imageView2 = (ImageView) view.findViewById(R.id.imageView_addlock);
                            if (imageView2 != null) {
                                i = R.id.listview_dashboard;
                                ListView listView = (ListView) view.findViewById(R.id.listview_dashboard);
                                if (listView != null) {
                                    i = R.id.myviewpager;
                                    ViewPager viewPager = (ViewPager) view.findViewById(R.id.myviewpager);
                                    if (viewPager != null) {
                                        i = R.id.videoView1;
                                        VideoView videoView = (VideoView) view.findViewById(R.id.videoView1);
                                        if (videoView != null) {
                                            return new FragmentDashboardMainBinding(linearLayout, linearLayout, linearLayout2, relativeLayout, button, button2, imageView, imageView2, listView, viewPager, videoView);
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
