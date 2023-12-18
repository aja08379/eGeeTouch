package com.egeetouch.egeetouch_manager.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import com.egeetouch.egeetouch_manager.R;
/* loaded from: classes.dex */
public final class FragmentBle2Binding implements ViewBinding {
    public final LinearLayout LinearLayoutIcons;
    public final RelativeLayout RelativeLayoutButtons;
    public final TextView buttonCountdownTimer;
    public final HorizontalScrollView horizontalScrollView1;
    public final ImageView imageViewAudit;
    public final ImageView imageViewBackup;
    public final ImageView imageViewBattery1;
    public final ImageView imageViewBleLockButton;
    public final ImageView imageViewBleUnlockButton;
    public final ImageView imageViewInSync;
    public final ImageView imageViewLocking;
    public final ImageView imageViewLogoDown;
    public final ImageView imageViewProximity;
    public final ImageView imageViewRoundLock;
    public final ImageView imageViewRoundUnlock;
    public final ImageView imageViewSetting;
    public final ImageView imageViewTag;
    public final ImageView imageViewUser;
    public final RelativeLayout relativeLayout;
    private final ConstraintLayout rootView;
    public final TextView textViewBatteryLevel;
    public final TextView textViewLockName;

    private FragmentBle2Binding(ConstraintLayout constraintLayout, LinearLayout linearLayout, RelativeLayout relativeLayout, TextView textView, HorizontalScrollView horizontalScrollView, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, ImageView imageView5, ImageView imageView6, ImageView imageView7, ImageView imageView8, ImageView imageView9, ImageView imageView10, ImageView imageView11, ImageView imageView12, ImageView imageView13, ImageView imageView14, RelativeLayout relativeLayout2, TextView textView2, TextView textView3) {
        this.rootView = constraintLayout;
        this.LinearLayoutIcons = linearLayout;
        this.RelativeLayoutButtons = relativeLayout;
        this.buttonCountdownTimer = textView;
        this.horizontalScrollView1 = horizontalScrollView;
        this.imageViewAudit = imageView;
        this.imageViewBackup = imageView2;
        this.imageViewBattery1 = imageView3;
        this.imageViewBleLockButton = imageView4;
        this.imageViewBleUnlockButton = imageView5;
        this.imageViewInSync = imageView6;
        this.imageViewLocking = imageView7;
        this.imageViewLogoDown = imageView8;
        this.imageViewProximity = imageView9;
        this.imageViewRoundLock = imageView10;
        this.imageViewRoundUnlock = imageView11;
        this.imageViewSetting = imageView12;
        this.imageViewTag = imageView13;
        this.imageViewUser = imageView14;
        this.relativeLayout = relativeLayout2;
        this.textViewBatteryLevel = textView2;
        this.textViewLockName = textView3;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static FragmentBle2Binding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentBle2Binding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fragment_ble2, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static FragmentBle2Binding bind(View view) {
        int i = R.id.LinearLayout_icons;
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.LinearLayout_icons);
        if (linearLayout != null) {
            i = R.id.RelativeLayout_buttons;
            RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.RelativeLayout_buttons);
            if (relativeLayout != null) {
                i = R.id.button_countdown_timer;
                TextView textView = (TextView) view.findViewById(R.id.button_countdown_timer);
                if (textView != null) {
                    i = R.id.horizontalScrollView1;
                    HorizontalScrollView horizontalScrollView = (HorizontalScrollView) view.findViewById(R.id.horizontalScrollView1);
                    if (horizontalScrollView != null) {
                        i = R.id.imageView_audit;
                        ImageView imageView = (ImageView) view.findViewById(R.id.imageView_audit);
                        if (imageView != null) {
                            i = R.id.imageView_backup;
                            ImageView imageView2 = (ImageView) view.findViewById(R.id.imageView_backup);
                            if (imageView2 != null) {
                                i = R.id.imageView_battery1;
                                ImageView imageView3 = (ImageView) view.findViewById(R.id.imageView_battery1);
                                if (imageView3 != null) {
                                    i = R.id.imageView_ble_lock_button;
                                    ImageView imageView4 = (ImageView) view.findViewById(R.id.imageView_ble_lock_button);
                                    if (imageView4 != null) {
                                        i = R.id.imageView_ble_unlock_button;
                                        ImageView imageView5 = (ImageView) view.findViewById(R.id.imageView_ble_unlock_button);
                                        if (imageView5 != null) {
                                            i = R.id.imageView_in_sync;
                                            ImageView imageView6 = (ImageView) view.findViewById(R.id.imageView_in_sync);
                                            if (imageView6 != null) {
                                                i = R.id.imageView_locking;
                                                ImageView imageView7 = (ImageView) view.findViewById(R.id.imageView_locking);
                                                if (imageView7 != null) {
                                                    i = R.id.imageView_logo_down;
                                                    ImageView imageView8 = (ImageView) view.findViewById(R.id.imageView_logo_down);
                                                    if (imageView8 != null) {
                                                        i = R.id.imageView_proximity;
                                                        ImageView imageView9 = (ImageView) view.findViewById(R.id.imageView_proximity);
                                                        if (imageView9 != null) {
                                                            i = R.id.imageView_round_lock;
                                                            ImageView imageView10 = (ImageView) view.findViewById(R.id.imageView_round_lock);
                                                            if (imageView10 != null) {
                                                                i = R.id.imageView_round_unlock;
                                                                ImageView imageView11 = (ImageView) view.findViewById(R.id.imageView_round_unlock);
                                                                if (imageView11 != null) {
                                                                    i = R.id.imageView_setting;
                                                                    ImageView imageView12 = (ImageView) view.findViewById(R.id.imageView_setting);
                                                                    if (imageView12 != null) {
                                                                        i = R.id.imageView_tag;
                                                                        ImageView imageView13 = (ImageView) view.findViewById(R.id.imageView_tag);
                                                                        if (imageView13 != null) {
                                                                            i = R.id.imageView_user;
                                                                            ImageView imageView14 = (ImageView) view.findViewById(R.id.imageView_user);
                                                                            if (imageView14 != null) {
                                                                                i = R.id.relativeLayout;
                                                                                RelativeLayout relativeLayout2 = (RelativeLayout) view.findViewById(R.id.relativeLayout);
                                                                                if (relativeLayout2 != null) {
                                                                                    i = R.id.textView_battery_level;
                                                                                    TextView textView2 = (TextView) view.findViewById(R.id.textView_battery_level);
                                                                                    if (textView2 != null) {
                                                                                        i = R.id.textView_lock_name;
                                                                                        TextView textView3 = (TextView) view.findViewById(R.id.textView_lock_name);
                                                                                        if (textView3 != null) {
                                                                                            return new FragmentBle2Binding((ConstraintLayout) view, linearLayout, relativeLayout, textView, horizontalScrollView, imageView, imageView2, imageView3, imageView4, imageView5, imageView6, imageView7, imageView8, imageView9, imageView10, imageView11, imageView12, imageView13, imageView14, relativeLayout2, textView2, textView3);
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
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
