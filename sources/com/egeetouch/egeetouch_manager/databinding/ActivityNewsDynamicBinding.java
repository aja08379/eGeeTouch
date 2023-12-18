package com.egeetouch.egeetouch_manager.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import com.egeetouch.egeetouch_manager.R;
/* loaded from: classes.dex */
public final class ActivityNewsDynamicBinding implements ViewBinding {
    public final ListView listview1;
    public final TextView noConnection;
    private final LinearLayout rootView;

    private ActivityNewsDynamicBinding(LinearLayout linearLayout, ListView listView, TextView textView) {
        this.rootView = linearLayout;
        this.listview1 = listView;
        this.noConnection = textView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ActivityNewsDynamicBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ActivityNewsDynamicBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_news_dynamic, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivityNewsDynamicBinding bind(View view) {
        int i = R.id.listview1;
        ListView listView = (ListView) view.findViewById(R.id.listview1);
        if (listView != null) {
            i = R.id.no_connection;
            TextView textView = (TextView) view.findViewById(R.id.no_connection);
            if (textView != null) {
                return new ActivityNewsDynamicBinding((LinearLayout) view, listView, textView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
