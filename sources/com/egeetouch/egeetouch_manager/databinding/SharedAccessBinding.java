package com.egeetouch.egeetouch_manager.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import com.egeetouch.egeetouch_manager.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
/* loaded from: classes.dex */
public final class SharedAccessBinding implements ViewBinding {
    public final FloatingActionButton floatingActionButton;
    public final ListView listviewSharelist;
    private final ConstraintLayout rootView;

    private SharedAccessBinding(ConstraintLayout constraintLayout, FloatingActionButton floatingActionButton, ListView listView) {
        this.rootView = constraintLayout;
        this.floatingActionButton = floatingActionButton;
        this.listviewSharelist = listView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static SharedAccessBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static SharedAccessBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.shared_access, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static SharedAccessBinding bind(View view) {
        int i = R.id.floatingActionButton;
        FloatingActionButton floatingActionButton = (FloatingActionButton) view.findViewById(R.id.floatingActionButton);
        if (floatingActionButton != null) {
            i = R.id.listview_sharelist;
            ListView listView = (ListView) view.findViewById(R.id.listview_sharelist);
            if (listView != null) {
                return new SharedAccessBinding((ConstraintLayout) view, floatingActionButton, listView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
