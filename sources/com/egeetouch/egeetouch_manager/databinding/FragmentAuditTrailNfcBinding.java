package com.egeetouch.egeetouch_manager.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import com.egeetouch.egeetouch_manager.R;
/* loaded from: classes.dex */
public final class FragmentAuditTrailNfcBinding implements ViewBinding {
    public final Button ButtonShare;
    public final LinearLayout LinearLayoutButton;
    public final Button buttonExportAccessHistory;
    public final FrameLayout container;
    public final LinearLayout linearLayoutMsg;
    public final ListView listViewAccessHistory;
    private final FrameLayout rootView;
    public final TextView textViewAuditTrailLockName;
    public final TextView textViewLastUpdatedTime;
    public final TextView tvPaidVersion;

    private FragmentAuditTrailNfcBinding(FrameLayout frameLayout, Button button, LinearLayout linearLayout, Button button2, FrameLayout frameLayout2, LinearLayout linearLayout2, ListView listView, TextView textView, TextView textView2, TextView textView3) {
        this.rootView = frameLayout;
        this.ButtonShare = button;
        this.LinearLayoutButton = linearLayout;
        this.buttonExportAccessHistory = button2;
        this.container = frameLayout2;
        this.linearLayoutMsg = linearLayout2;
        this.listViewAccessHistory = listView;
        this.textViewAuditTrailLockName = textView;
        this.textViewLastUpdatedTime = textView2;
        this.tvPaidVersion = textView3;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static FragmentAuditTrailNfcBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentAuditTrailNfcBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fragment_audit_trail_nfc, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static FragmentAuditTrailNfcBinding bind(View view) {
        int i = R.id.Button_share;
        Button button = (Button) view.findViewById(R.id.Button_share);
        if (button != null) {
            i = R.id.LinearLayout_button;
            LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.LinearLayout_button);
            if (linearLayout != null) {
                i = R.id.button_export_access_history;
                Button button2 = (Button) view.findViewById(R.id.button_export_access_history);
                if (button2 != null) {
                    FrameLayout frameLayout = (FrameLayout) view;
                    i = R.id.linearLayout_msg;
                    LinearLayout linearLayout2 = (LinearLayout) view.findViewById(R.id.linearLayout_msg);
                    if (linearLayout2 != null) {
                        i = R.id.listView_access_history;
                        ListView listView = (ListView) view.findViewById(R.id.listView_access_history);
                        if (listView != null) {
                            i = R.id.textView_Audit_trail_lock_name;
                            TextView textView = (TextView) view.findViewById(R.id.textView_Audit_trail_lock_name);
                            if (textView != null) {
                                i = R.id.textView_last_updatedTime;
                                TextView textView2 = (TextView) view.findViewById(R.id.textView_last_updatedTime);
                                if (textView2 != null) {
                                    i = R.id.tv_paid_version;
                                    TextView textView3 = (TextView) view.findViewById(R.id.tv_paid_version);
                                    if (textView3 != null) {
                                        return new FragmentAuditTrailNfcBinding(frameLayout, button, linearLayout, button2, frameLayout, linearLayout2, listView, textView, textView2, textView3);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
