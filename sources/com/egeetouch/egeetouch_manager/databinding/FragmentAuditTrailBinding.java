package com.egeetouch.egeetouch_manager.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import com.egeetouch.egeetouch_manager.R;
/* loaded from: classes.dex */
public final class FragmentAuditTrailBinding implements ViewBinding {
    public final Button ButtonShare;
    public final LinearLayout LinearLayoutButtons;
    public final LinearLayout LinearLayoutIcons;
    public final Button buttonExportAccessHistory;
    public final RelativeLayout container;
    public final ImageView imageViewAudit;
    public final ImageView imageViewLocking;
    public final ImageView imageViewProximity;
    public final ImageView imageViewSetting;
    public final ImageView imageViewWatch;
    public final LinearLayout linearLayoutAudit;
    public final LinearLayout linearLayoutMsg;
    public final ListView listViewAccessHistory;
    private final RelativeLayout rootView;
    public final TextView textViewAuditTrailLockName;
    public final TextView textViewDate;
    public final TextView textViewLastUpdatedTime;
    public final TextView textViewTime;
    public final TextView textViewWhere;
    public final TextView textViewWho;
    public final TextView tvAuditTrailBackupMsg;
    public final TextView tvPaidVersion;

    private FragmentAuditTrailBinding(RelativeLayout relativeLayout, Button button, LinearLayout linearLayout, LinearLayout linearLayout2, Button button2, RelativeLayout relativeLayout2, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, ImageView imageView5, LinearLayout linearLayout3, LinearLayout linearLayout4, ListView listView, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8) {
        this.rootView = relativeLayout;
        this.ButtonShare = button;
        this.LinearLayoutButtons = linearLayout;
        this.LinearLayoutIcons = linearLayout2;
        this.buttonExportAccessHistory = button2;
        this.container = relativeLayout2;
        this.imageViewAudit = imageView;
        this.imageViewLocking = imageView2;
        this.imageViewProximity = imageView3;
        this.imageViewSetting = imageView4;
        this.imageViewWatch = imageView5;
        this.linearLayoutAudit = linearLayout3;
        this.linearLayoutMsg = linearLayout4;
        this.listViewAccessHistory = listView;
        this.textViewAuditTrailLockName = textView;
        this.textViewDate = textView2;
        this.textViewLastUpdatedTime = textView3;
        this.textViewTime = textView4;
        this.textViewWhere = textView5;
        this.textViewWho = textView6;
        this.tvAuditTrailBackupMsg = textView7;
        this.tvPaidVersion = textView8;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static FragmentAuditTrailBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentAuditTrailBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fragment_audit_trail, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static FragmentAuditTrailBinding bind(View view) {
        int i = R.id.Button_share;
        Button button = (Button) view.findViewById(R.id.Button_share);
        if (button != null) {
            i = R.id.LinearLayout_buttons;
            LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.LinearLayout_buttons);
            if (linearLayout != null) {
                i = R.id.LinearLayout_icons;
                LinearLayout linearLayout2 = (LinearLayout) view.findViewById(R.id.LinearLayout_icons);
                if (linearLayout2 != null) {
                    i = R.id.button_export_access_history;
                    Button button2 = (Button) view.findViewById(R.id.button_export_access_history);
                    if (button2 != null) {
                        RelativeLayout relativeLayout = (RelativeLayout) view;
                        i = R.id.imageView_audit;
                        ImageView imageView = (ImageView) view.findViewById(R.id.imageView_audit);
                        if (imageView != null) {
                            i = R.id.imageView_locking;
                            ImageView imageView2 = (ImageView) view.findViewById(R.id.imageView_locking);
                            if (imageView2 != null) {
                                i = R.id.imageView_proximity;
                                ImageView imageView3 = (ImageView) view.findViewById(R.id.imageView_proximity);
                                if (imageView3 != null) {
                                    i = R.id.imageView_setting;
                                    ImageView imageView4 = (ImageView) view.findViewById(R.id.imageView_setting);
                                    if (imageView4 != null) {
                                        i = R.id.imageView_watch;
                                        ImageView imageView5 = (ImageView) view.findViewById(R.id.imageView_watch);
                                        if (imageView5 != null) {
                                            i = R.id.linearLayout_audit;
                                            LinearLayout linearLayout3 = (LinearLayout) view.findViewById(R.id.linearLayout_audit);
                                            if (linearLayout3 != null) {
                                                i = R.id.linearLayout_msg;
                                                LinearLayout linearLayout4 = (LinearLayout) view.findViewById(R.id.linearLayout_msg);
                                                if (linearLayout4 != null) {
                                                    i = R.id.listView_access_history;
                                                    ListView listView = (ListView) view.findViewById(R.id.listView_access_history);
                                                    if (listView != null) {
                                                        i = R.id.textView_Audit_trail_lock_name;
                                                        TextView textView = (TextView) view.findViewById(R.id.textView_Audit_trail_lock_name);
                                                        if (textView != null) {
                                                            i = R.id.textView_date;
                                                            TextView textView2 = (TextView) view.findViewById(R.id.textView_date);
                                                            if (textView2 != null) {
                                                                i = R.id.textView_last_updatedTime;
                                                                TextView textView3 = (TextView) view.findViewById(R.id.textView_last_updatedTime);
                                                                if (textView3 != null) {
                                                                    i = R.id.textView_time;
                                                                    TextView textView4 = (TextView) view.findViewById(R.id.textView_time);
                                                                    if (textView4 != null) {
                                                                        i = R.id.textView_where;
                                                                        TextView textView5 = (TextView) view.findViewById(R.id.textView_where);
                                                                        if (textView5 != null) {
                                                                            i = R.id.textView_who;
                                                                            TextView textView6 = (TextView) view.findViewById(R.id.textView_who);
                                                                            if (textView6 != null) {
                                                                                i = R.id.tv_AuditTrailBackup_msg;
                                                                                TextView textView7 = (TextView) view.findViewById(R.id.tv_AuditTrailBackup_msg);
                                                                                if (textView7 != null) {
                                                                                    i = R.id.tv_paid_version;
                                                                                    TextView textView8 = (TextView) view.findViewById(R.id.tv_paid_version);
                                                                                    if (textView8 != null) {
                                                                                        return new FragmentAuditTrailBinding(relativeLayout, button, linearLayout, linearLayout2, button2, relativeLayout, imageView, imageView2, imageView3, imageView4, imageView5, linearLayout3, linearLayout4, listView, textView, textView2, textView3, textView4, textView5, textView6, textView7, textView8);
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
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
