package com.egeetouch.egeetouch_manager.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import com.egeetouch.egeetouch_manager.R;
/* loaded from: classes.dex */
public final class WatchListItemBinding implements ViewBinding {
    private final ConstraintLayout rootView;
    public final ImageView statusCircle;
    public final ImageView watch;
    public final TextView watchName;
    public final TextView watchUuid;

    private WatchListItemBinding(ConstraintLayout constraintLayout, ImageView imageView, ImageView imageView2, TextView textView, TextView textView2) {
        this.rootView = constraintLayout;
        this.statusCircle = imageView;
        this.watch = imageView2;
        this.watchName = textView;
        this.watchUuid = textView2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static WatchListItemBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static WatchListItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.watch_list_item, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static WatchListItemBinding bind(View view) {
        int i = R.id.status_circle;
        ImageView imageView = (ImageView) view.findViewById(R.id.status_circle);
        if (imageView != null) {
            i = R.id.watch;
            ImageView imageView2 = (ImageView) view.findViewById(R.id.watch);
            if (imageView2 != null) {
                i = R.id.watch_name;
                TextView textView = (TextView) view.findViewById(R.id.watch_name);
                if (textView != null) {
                    i = R.id.watch_uuid;
                    TextView textView2 = (TextView) view.findViewById(R.id.watch_uuid);
                    if (textView2 != null) {
                        return new WatchListItemBinding((ConstraintLayout) view, imageView, imageView2, textView, textView2);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
