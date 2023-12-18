package com.egeetouch.egeetouch_manager.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import androidx.viewbinding.ViewBinding;
import com.egeetouch.egeetouch_manager.R;
/* loaded from: classes.dex */
public final class ActivityShareHistoryBinding implements ViewBinding {
    public final ListView listviewSharehistory;
    private final LinearLayout rootView;

    private ActivityShareHistoryBinding(LinearLayout linearLayout, ListView listView) {
        this.rootView = linearLayout;
        this.listviewSharehistory = listView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ActivityShareHistoryBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ActivityShareHistoryBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_share_history, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivityShareHistoryBinding bind(View view) {
        ListView listView = (ListView) view.findViewById(R.id.listview_sharehistory);
        if (listView != null) {
            return new ActivityShareHistoryBinding((LinearLayout) view, listView);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(R.id.listview_sharehistory)));
    }
}
