package com.egeetouch.egeetouch_manager.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import com.egeetouch.egeetouch_manager.R;
/* loaded from: classes.dex */
public final class ListviewLayoutAuditTrailNfcBinding implements ViewBinding {
    public final TextView listviewWhen;
    public final TextView listviewWhere;
    public final TextView listviewWho;
    private final TableLayout rootView;

    private ListviewLayoutAuditTrailNfcBinding(TableLayout tableLayout, TextView textView, TextView textView2, TextView textView3) {
        this.rootView = tableLayout;
        this.listviewWhen = textView;
        this.listviewWhere = textView2;
        this.listviewWho = textView3;
    }

    @Override // androidx.viewbinding.ViewBinding
    public TableLayout getRoot() {
        return this.rootView;
    }

    public static ListviewLayoutAuditTrailNfcBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ListviewLayoutAuditTrailNfcBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.listview_layout_audit_trail_nfc, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ListviewLayoutAuditTrailNfcBinding bind(View view) {
        int i = R.id.listview_when;
        TextView textView = (TextView) view.findViewById(R.id.listview_when);
        if (textView != null) {
            i = R.id.listview_where;
            TextView textView2 = (TextView) view.findViewById(R.id.listview_where);
            if (textView2 != null) {
                i = R.id.listview_who;
                TextView textView3 = (TextView) view.findViewById(R.id.listview_who);
                if (textView3 != null) {
                    return new ListviewLayoutAuditTrailNfcBinding((TableLayout) view, textView, textView2, textView3);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
