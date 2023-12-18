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
public final class ActivityAccessLogAuditBinding implements ViewBinding {
    public final TextView TextViewLockname;
    public final TextView TextViewSharedByEmail;
    public final ListView accessAuditListView;
    public final LinearLayout linearLayoutMsg;
    private final LinearLayout rootView;
    public final TextView tvPaidVersion;

    private ActivityAccessLogAuditBinding(LinearLayout linearLayout, TextView textView, TextView textView2, ListView listView, LinearLayout linearLayout2, TextView textView3) {
        this.rootView = linearLayout;
        this.TextViewLockname = textView;
        this.TextViewSharedByEmail = textView2;
        this.accessAuditListView = listView;
        this.linearLayoutMsg = linearLayout2;
        this.tvPaidVersion = textView3;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ActivityAccessLogAuditBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ActivityAccessLogAuditBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_access_log_audit, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivityAccessLogAuditBinding bind(View view) {
        int i = R.id.TextView_lockname;
        TextView textView = (TextView) view.findViewById(R.id.TextView_lockname);
        if (textView != null) {
            i = R.id.TextView_sharedByEmail;
            TextView textView2 = (TextView) view.findViewById(R.id.TextView_sharedByEmail);
            if (textView2 != null) {
                i = R.id.access_audit_list_view;
                ListView listView = (ListView) view.findViewById(R.id.access_audit_list_view);
                if (listView != null) {
                    i = R.id.linearLayout_msg;
                    LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.linearLayout_msg);
                    if (linearLayout != null) {
                        i = R.id.tv_paid_version;
                        TextView textView3 = (TextView) view.findViewById(R.id.tv_paid_version);
                        if (textView3 != null) {
                            return new ActivityAccessLogAuditBinding((LinearLayout) view, textView, textView2, listView, linearLayout, textView3);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
