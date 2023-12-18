package com.egeetouch.egeetouch_manager.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import androidx.viewbinding.ViewBinding;
import com.egeetouch.egeetouch_manager.R;
/* loaded from: classes.dex */
public final class ActivityRetractAccessBinding implements ViewBinding {
    public final ListView listViewRetractAccess;
    private final LinearLayout rootView;

    private ActivityRetractAccessBinding(LinearLayout linearLayout, ListView listView) {
        this.rootView = linearLayout;
        this.listViewRetractAccess = listView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ActivityRetractAccessBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ActivityRetractAccessBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_retract_access, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivityRetractAccessBinding bind(View view) {
        ListView listView = (ListView) view.findViewById(R.id.listView_retract_access);
        if (listView != null) {
            return new ActivityRetractAccessBinding((LinearLayout) view, listView);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(R.id.listView_retract_access)));
    }
}
