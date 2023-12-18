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
public final class FragmentChooseTypeBinding implements ViewBinding {
    public final LinearLayout LinearLayout1;
    public final View head;
    public final ListView listviewLocktype;
    private final LinearLayout rootView;
    public final TextView textViewAddlockStep4;

    private FragmentChooseTypeBinding(LinearLayout linearLayout, LinearLayout linearLayout2, View view, ListView listView, TextView textView) {
        this.rootView = linearLayout;
        this.LinearLayout1 = linearLayout2;
        this.head = view;
        this.listviewLocktype = listView;
        this.textViewAddlockStep4 = textView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static FragmentChooseTypeBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentChooseTypeBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fragment_choose_type, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static FragmentChooseTypeBinding bind(View view) {
        LinearLayout linearLayout = (LinearLayout) view;
        int i = R.id.head;
        View findViewById = view.findViewById(R.id.head);
        if (findViewById != null) {
            i = R.id.listview_locktype;
            ListView listView = (ListView) view.findViewById(R.id.listview_locktype);
            if (listView != null) {
                i = R.id.textView_addlock_step4;
                TextView textView = (TextView) view.findViewById(R.id.textView_addlock_step4);
                if (textView != null) {
                    return new FragmentChooseTypeBinding(linearLayout, linearLayout, findViewById, listView, textView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
