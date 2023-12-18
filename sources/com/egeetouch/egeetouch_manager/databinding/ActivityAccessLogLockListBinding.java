package com.egeetouch.egeetouch_manager.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import androidx.viewbinding.ViewBinding;
import com.egeetouch.egeetouch_manager.R;
/* loaded from: classes.dex */
public final class ActivityAccessLogLockListBinding implements ViewBinding {
    public final ListView accessLogLockSerialListview;
    private final LinearLayout rootView;

    private ActivityAccessLogLockListBinding(LinearLayout linearLayout, ListView listView) {
        this.rootView = linearLayout;
        this.accessLogLockSerialListview = listView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ActivityAccessLogLockListBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ActivityAccessLogLockListBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_access_log_lock_list, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivityAccessLogLockListBinding bind(View view) {
        ListView listView = (ListView) view.findViewById(R.id.access_log_lock_serial_listview);
        if (listView != null) {
            return new ActivityAccessLogLockListBinding((LinearLayout) view, listView);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(R.id.access_log_lock_serial_listview)));
    }
}
