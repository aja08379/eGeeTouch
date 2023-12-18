package com.egeetouch.egeetouch_manager.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import com.egeetouch.egeetouch_manager.R;
/* loaded from: classes.dex */
public final class ActivityWatchListBinding implements ViewBinding {
    public final ImageView imageView12;
    public final LinearLayout linearLayout3;
    private final ConstraintLayout rootView;
    public final TextView textView28;
    public final ListView watchList;

    private ActivityWatchListBinding(ConstraintLayout constraintLayout, ImageView imageView, LinearLayout linearLayout, TextView textView, ListView listView) {
        this.rootView = constraintLayout;
        this.imageView12 = imageView;
        this.linearLayout3 = linearLayout;
        this.textView28 = textView;
        this.watchList = listView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityWatchListBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ActivityWatchListBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_watch_list, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivityWatchListBinding bind(View view) {
        int i = R.id.imageView12;
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView12);
        if (imageView != null) {
            i = R.id.linearLayout3;
            LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.linearLayout3);
            if (linearLayout != null) {
                i = R.id.textView28;
                TextView textView = (TextView) view.findViewById(R.id.textView28);
                if (textView != null) {
                    i = R.id.watch_list;
                    ListView listView = (ListView) view.findViewById(R.id.watch_list);
                    if (listView != null) {
                        return new ActivityWatchListBinding((ConstraintLayout) view, imageView, linearLayout, textView, listView);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
