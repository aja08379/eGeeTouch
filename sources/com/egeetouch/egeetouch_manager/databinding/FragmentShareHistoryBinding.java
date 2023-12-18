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
public final class FragmentShareHistoryBinding implements ViewBinding {
    public final TextView TextViewLockname;
    public final TextView TextViewSharedByEmail;
    public final ListView listviewSharehistory;
    private final LinearLayout rootView;

    private FragmentShareHistoryBinding(LinearLayout linearLayout, TextView textView, TextView textView2, ListView listView) {
        this.rootView = linearLayout;
        this.TextViewLockname = textView;
        this.TextViewSharedByEmail = textView2;
        this.listviewSharehistory = listView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static FragmentShareHistoryBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentShareHistoryBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fragment_share_history, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static FragmentShareHistoryBinding bind(View view) {
        int i = R.id.TextView_lockname;
        TextView textView = (TextView) view.findViewById(R.id.TextView_lockname);
        if (textView != null) {
            i = R.id.TextView_sharedByEmail;
            TextView textView2 = (TextView) view.findViewById(R.id.TextView_sharedByEmail);
            if (textView2 != null) {
                i = R.id.listview_sharehistory;
                ListView listView = (ListView) view.findViewById(R.id.listview_sharehistory);
                if (listView != null) {
                    return new FragmentShareHistoryBinding((LinearLayout) view, textView, textView2, listView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
