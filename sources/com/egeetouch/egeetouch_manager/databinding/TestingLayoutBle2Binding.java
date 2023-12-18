package com.egeetouch.egeetouch_manager.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import com.egeetouch.egeetouch_manager.R;
/* loaded from: classes.dex */
public final class TestingLayoutBle2Binding implements ViewBinding {
    public final LinearLayout LinearLayoutVicinity;
    public final RelativeLayout RelativeLayoutBar;
    public final RelativeLayout RelativeLayoutButtons;
    public final RelativeLayout RelativeLayoutGraphic;
    public final Button btNfcMode;
    public final Button buttonCountdownTimer;
    public final ImageView imTurnNfc;
    public final ImageView imageViewAudit;
    public final ImageView imageViewBanner;
    public final ImageView imageViewBattery1;
    public final ImageView imageViewBleLockButton;
    public final ImageView imageViewBleUnlockButton;
    public final ImageView imageViewInSync;
    public final ImageView imageViewLocking;
    public final ImageView imageViewLogoDown;
    public final ImageView imageViewProximity;
    public final ImageView imageViewProximityPpl;
    public final ImageView imageViewProximityStand;
    public final ImageView imageViewRoundLock;
    public final ImageView imageViewRoundUnlock;
    public final ImageView imageViewSetting;
    public final ImageView imageViewVibrationIcon;
    public final ImageView imageViewWatch;
    public final LinearLayout llAuditNotif;
    public final LinearLayout llAutoShutdownTimer;
    public final LinearLayout llBottomBar;
    public final LinearLayout llHeader;
    public final ProgressBar progressBarCounter;
    public final RelativeLayout relativeLayoutCounter;
    private final LinearLayout rootView;
    public final SeekBar seekBarRange;
    public final SeekBar seekBarSensitivity;
    public final TextView textView2band;
    public final TextView textViewFar;
    public final TextView textViewLockName;
    public final TextView textViewNear;
    public final TextView textViewRange;
    public final TextView textViewSensitivity;
    public final TextView textViewSensitivityHigh;
    public final TextView textViewSensitivityHigh1;
    public final TextView textViewSensitivityLow;
    public final TextView textViewSensitivityLow1;
    public final TextView textViewSerialNumberMain;
    public final TextView textViewUnmute;
    public final TextView tvAuditNotif;
    public final TextView tvAutoShutDown;
    public final TextView tvDisplayMin;
    public final TextView tvDisplaySec;
    public final TextView tvMins;
    public final TextView tvTextMin;
    public final TextView tvTextSec;
    public final TextView tvTurnToNFC;

    private TestingLayoutBle2Binding(LinearLayout linearLayout, LinearLayout linearLayout2, RelativeLayout relativeLayout, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3, Button button, Button button2, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, ImageView imageView5, ImageView imageView6, ImageView imageView7, ImageView imageView8, ImageView imageView9, ImageView imageView10, ImageView imageView11, ImageView imageView12, ImageView imageView13, ImageView imageView14, ImageView imageView15, ImageView imageView16, ImageView imageView17, LinearLayout linearLayout3, LinearLayout linearLayout4, LinearLayout linearLayout5, LinearLayout linearLayout6, ProgressBar progressBar, RelativeLayout relativeLayout4, SeekBar seekBar, SeekBar seekBar2, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9, TextView textView10, TextView textView11, TextView textView12, TextView textView13, TextView textView14, TextView textView15, TextView textView16, TextView textView17, TextView textView18, TextView textView19, TextView textView20) {
        this.rootView = linearLayout;
        this.LinearLayoutVicinity = linearLayout2;
        this.RelativeLayoutBar = relativeLayout;
        this.RelativeLayoutButtons = relativeLayout2;
        this.RelativeLayoutGraphic = relativeLayout3;
        this.btNfcMode = button;
        this.buttonCountdownTimer = button2;
        this.imTurnNfc = imageView;
        this.imageViewAudit = imageView2;
        this.imageViewBanner = imageView3;
        this.imageViewBattery1 = imageView4;
        this.imageViewBleLockButton = imageView5;
        this.imageViewBleUnlockButton = imageView6;
        this.imageViewInSync = imageView7;
        this.imageViewLocking = imageView8;
        this.imageViewLogoDown = imageView9;
        this.imageViewProximity = imageView10;
        this.imageViewProximityPpl = imageView11;
        this.imageViewProximityStand = imageView12;
        this.imageViewRoundLock = imageView13;
        this.imageViewRoundUnlock = imageView14;
        this.imageViewSetting = imageView15;
        this.imageViewVibrationIcon = imageView16;
        this.imageViewWatch = imageView17;
        this.llAuditNotif = linearLayout3;
        this.llAutoShutdownTimer = linearLayout4;
        this.llBottomBar = linearLayout5;
        this.llHeader = linearLayout6;
        this.progressBarCounter = progressBar;
        this.relativeLayoutCounter = relativeLayout4;
        this.seekBarRange = seekBar;
        this.seekBarSensitivity = seekBar2;
        this.textView2band = textView;
        this.textViewFar = textView2;
        this.textViewLockName = textView3;
        this.textViewNear = textView4;
        this.textViewRange = textView5;
        this.textViewSensitivity = textView6;
        this.textViewSensitivityHigh = textView7;
        this.textViewSensitivityHigh1 = textView8;
        this.textViewSensitivityLow = textView9;
        this.textViewSensitivityLow1 = textView10;
        this.textViewSerialNumberMain = textView11;
        this.textViewUnmute = textView12;
        this.tvAuditNotif = textView13;
        this.tvAutoShutDown = textView14;
        this.tvDisplayMin = textView15;
        this.tvDisplaySec = textView16;
        this.tvMins = textView17;
        this.tvTextMin = textView18;
        this.tvTextSec = textView19;
        this.tvTurnToNFC = textView20;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static TestingLayoutBle2Binding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static TestingLayoutBle2Binding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.testing_layout_ble2, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static TestingLayoutBle2Binding bind(View view) {
        int i = R.id.LinearLayout_vicinity;
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.LinearLayout_vicinity);
        if (linearLayout != null) {
            i = R.id.RelativeLayout_bar;
            RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.RelativeLayout_bar);
            if (relativeLayout != null) {
                i = R.id.RelativeLayout_buttons;
                RelativeLayout relativeLayout2 = (RelativeLayout) view.findViewById(R.id.RelativeLayout_buttons);
                if (relativeLayout2 != null) {
                    i = R.id.RelativeLayout_graphic;
                    RelativeLayout relativeLayout3 = (RelativeLayout) view.findViewById(R.id.RelativeLayout_graphic);
                    if (relativeLayout3 != null) {
                        i = R.id.bt_nfcMode;
                        Button button = (Button) view.findViewById(R.id.bt_nfcMode);
                        if (button != null) {
                            i = R.id.button_countdown_timer;
                            Button button2 = (Button) view.findViewById(R.id.button_countdown_timer);
                            if (button2 != null) {
                                i = R.id.im_turn_nfc;
                                ImageView imageView = (ImageView) view.findViewById(R.id.im_turn_nfc);
                                if (imageView != null) {
                                    i = R.id.imageView_audit;
                                    ImageView imageView2 = (ImageView) view.findViewById(R.id.imageView_audit);
                                    if (imageView2 != null) {
                                        i = R.id.imageView_banner;
                                        ImageView imageView3 = (ImageView) view.findViewById(R.id.imageView_banner);
                                        if (imageView3 != null) {
                                            i = R.id.imageView_battery1;
                                            ImageView imageView4 = (ImageView) view.findViewById(R.id.imageView_battery1);
                                            if (imageView4 != null) {
                                                i = R.id.imageView_ble_lock_button;
                                                ImageView imageView5 = (ImageView) view.findViewById(R.id.imageView_ble_lock_button);
                                                if (imageView5 != null) {
                                                    i = R.id.imageView_ble_unlock_button;
                                                    ImageView imageView6 = (ImageView) view.findViewById(R.id.imageView_ble_unlock_button);
                                                    if (imageView6 != null) {
                                                        i = R.id.imageView_in_sync;
                                                        ImageView imageView7 = (ImageView) view.findViewById(R.id.imageView_in_sync);
                                                        if (imageView7 != null) {
                                                            i = R.id.imageView_locking;
                                                            ImageView imageView8 = (ImageView) view.findViewById(R.id.imageView_locking);
                                                            if (imageView8 != null) {
                                                                i = R.id.imageView_logo_down;
                                                                ImageView imageView9 = (ImageView) view.findViewById(R.id.imageView_logo_down);
                                                                if (imageView9 != null) {
                                                                    i = R.id.imageView_proximity;
                                                                    ImageView imageView10 = (ImageView) view.findViewById(R.id.imageView_proximity);
                                                                    if (imageView10 != null) {
                                                                        i = R.id.imageView_proximity_ppl;
                                                                        ImageView imageView11 = (ImageView) view.findViewById(R.id.imageView_proximity_ppl);
                                                                        if (imageView11 != null) {
                                                                            i = R.id.imageView_proximity_stand;
                                                                            ImageView imageView12 = (ImageView) view.findViewById(R.id.imageView_proximity_stand);
                                                                            if (imageView12 != null) {
                                                                                i = R.id.imageView_round_lock;
                                                                                ImageView imageView13 = (ImageView) view.findViewById(R.id.imageView_round_lock);
                                                                                if (imageView13 != null) {
                                                                                    i = R.id.imageView_round_unlock;
                                                                                    ImageView imageView14 = (ImageView) view.findViewById(R.id.imageView_round_unlock);
                                                                                    if (imageView14 != null) {
                                                                                        i = R.id.imageView_setting;
                                                                                        ImageView imageView15 = (ImageView) view.findViewById(R.id.imageView_setting);
                                                                                        if (imageView15 != null) {
                                                                                            i = R.id.imageView_vibration_icon;
                                                                                            ImageView imageView16 = (ImageView) view.findViewById(R.id.imageView_vibration_icon);
                                                                                            if (imageView16 != null) {
                                                                                                i = R.id.imageView_watch;
                                                                                                ImageView imageView17 = (ImageView) view.findViewById(R.id.imageView_watch);
                                                                                                if (imageView17 != null) {
                                                                                                    i = R.id.ll_audit_notif;
                                                                                                    LinearLayout linearLayout2 = (LinearLayout) view.findViewById(R.id.ll_audit_notif);
                                                                                                    if (linearLayout2 != null) {
                                                                                                        i = R.id.ll_autoShutdownTimer;
                                                                                                        LinearLayout linearLayout3 = (LinearLayout) view.findViewById(R.id.ll_autoShutdownTimer);
                                                                                                        if (linearLayout3 != null) {
                                                                                                            i = R.id.ll_bottom_bar;
                                                                                                            LinearLayout linearLayout4 = (LinearLayout) view.findViewById(R.id.ll_bottom_bar);
                                                                                                            if (linearLayout4 != null) {
                                                                                                                i = R.id.ll_header;
                                                                                                                LinearLayout linearLayout5 = (LinearLayout) view.findViewById(R.id.ll_header);
                                                                                                                if (linearLayout5 != null) {
                                                                                                                    i = R.id.progressBarCounter;
                                                                                                                    ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.progressBarCounter);
                                                                                                                    if (progressBar != null) {
                                                                                                                        i = R.id.relativeLayout_counter;
                                                                                                                        RelativeLayout relativeLayout4 = (RelativeLayout) view.findViewById(R.id.relativeLayout_counter);
                                                                                                                        if (relativeLayout4 != null) {
                                                                                                                            i = R.id.seekBar_range;
                                                                                                                            SeekBar seekBar = (SeekBar) view.findViewById(R.id.seekBar_range);
                                                                                                                            if (seekBar != null) {
                                                                                                                                i = R.id.seekBar_sensitivity;
                                                                                                                                SeekBar seekBar2 = (SeekBar) view.findViewById(R.id.seekBar_sensitivity);
                                                                                                                                if (seekBar2 != null) {
                                                                                                                                    i = R.id.textView_2band;
                                                                                                                                    TextView textView = (TextView) view.findViewById(R.id.textView_2band);
                                                                                                                                    if (textView != null) {
                                                                                                                                        i = R.id.textView_far;
                                                                                                                                        TextView textView2 = (TextView) view.findViewById(R.id.textView_far);
                                                                                                                                        if (textView2 != null) {
                                                                                                                                            i = R.id.textView_lock_name;
                                                                                                                                            TextView textView3 = (TextView) view.findViewById(R.id.textView_lock_name);
                                                                                                                                            if (textView3 != null) {
                                                                                                                                                i = R.id.textView_near;
                                                                                                                                                TextView textView4 = (TextView) view.findViewById(R.id.textView_near);
                                                                                                                                                if (textView4 != null) {
                                                                                                                                                    i = R.id.textView_range;
                                                                                                                                                    TextView textView5 = (TextView) view.findViewById(R.id.textView_range);
                                                                                                                                                    if (textView5 != null) {
                                                                                                                                                        i = R.id.textView_sensitivity;
                                                                                                                                                        TextView textView6 = (TextView) view.findViewById(R.id.textView_sensitivity);
                                                                                                                                                        if (textView6 != null) {
                                                                                                                                                            i = R.id.textView_sensitivity_high;
                                                                                                                                                            TextView textView7 = (TextView) view.findViewById(R.id.textView_sensitivity_high);
                                                                                                                                                            if (textView7 != null) {
                                                                                                                                                                i = R.id.textView_sensitivity_high1;
                                                                                                                                                                TextView textView8 = (TextView) view.findViewById(R.id.textView_sensitivity_high1);
                                                                                                                                                                if (textView8 != null) {
                                                                                                                                                                    i = R.id.textView_sensitivity_low;
                                                                                                                                                                    TextView textView9 = (TextView) view.findViewById(R.id.textView_sensitivity_low);
                                                                                                                                                                    if (textView9 != null) {
                                                                                                                                                                        i = R.id.textView_sensitivity_low1;
                                                                                                                                                                        TextView textView10 = (TextView) view.findViewById(R.id.textView_sensitivity_low1);
                                                                                                                                                                        if (textView10 != null) {
                                                                                                                                                                            i = R.id.textView_serialNumberMain;
                                                                                                                                                                            TextView textView11 = (TextView) view.findViewById(R.id.textView_serialNumberMain);
                                                                                                                                                                            if (textView11 != null) {
                                                                                                                                                                                i = R.id.textView_Unmute;
                                                                                                                                                                                TextView textView12 = (TextView) view.findViewById(R.id.textView_Unmute);
                                                                                                                                                                                if (textView12 != null) {
                                                                                                                                                                                    i = R.id.tv_audit_notif;
                                                                                                                                                                                    TextView textView13 = (TextView) view.findViewById(R.id.tv_audit_notif);
                                                                                                                                                                                    if (textView13 != null) {
                                                                                                                                                                                        i = R.id.tv_autoShutDown;
                                                                                                                                                                                        TextView textView14 = (TextView) view.findViewById(R.id.tv_autoShutDown);
                                                                                                                                                                                        if (textView14 != null) {
                                                                                                                                                                                            i = R.id.tv_displayMin;
                                                                                                                                                                                            TextView textView15 = (TextView) view.findViewById(R.id.tv_displayMin);
                                                                                                                                                                                            if (textView15 != null) {
                                                                                                                                                                                                i = R.id.tv_displaySec;
                                                                                                                                                                                                TextView textView16 = (TextView) view.findViewById(R.id.tv_displaySec);
                                                                                                                                                                                                if (textView16 != null) {
                                                                                                                                                                                                    i = R.id.tv_mins;
                                                                                                                                                                                                    TextView textView17 = (TextView) view.findViewById(R.id.tv_mins);
                                                                                                                                                                                                    if (textView17 != null) {
                                                                                                                                                                                                        i = R.id.tv_textMin;
                                                                                                                                                                                                        TextView textView18 = (TextView) view.findViewById(R.id.tv_textMin);
                                                                                                                                                                                                        if (textView18 != null) {
                                                                                                                                                                                                            i = R.id.tv_textSec;
                                                                                                                                                                                                            TextView textView19 = (TextView) view.findViewById(R.id.tv_textSec);
                                                                                                                                                                                                            if (textView19 != null) {
                                                                                                                                                                                                                i = R.id.tv_turnToNFC;
                                                                                                                                                                                                                TextView textView20 = (TextView) view.findViewById(R.id.tv_turnToNFC);
                                                                                                                                                                                                                if (textView20 != null) {
                                                                                                                                                                                                                    return new TestingLayoutBle2Binding((LinearLayout) view, linearLayout, relativeLayout, relativeLayout2, relativeLayout3, button, button2, imageView, imageView2, imageView3, imageView4, imageView5, imageView6, imageView7, imageView8, imageView9, imageView10, imageView11, imageView12, imageView13, imageView14, imageView15, imageView16, imageView17, linearLayout2, linearLayout3, linearLayout4, linearLayout5, progressBar, relativeLayout4, seekBar, seekBar2, textView, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9, textView10, textView11, textView12, textView13, textView14, textView15, textView16, textView17, textView18, textView19, textView20);
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
