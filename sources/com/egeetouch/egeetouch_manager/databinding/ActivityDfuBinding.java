package com.egeetouch.egeetouch_manager.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import com.egeetouch.egeetouch_manager.R;
/* loaded from: classes.dex */
public final class ActivityDfuBinding implements ViewBinding {
    public final ScrollView beforeUpdate;
    public final Button buttonScanDfutarg;
    public final Button buttonUpdate;
    public final TextView currentVersion;
    public final TextView databaseVersion;
    public final ProgressBar dbvProgress;
    public final ProgressBar fileProgress;
    public final TextView fileText;
    public final ImageView imageView13;
    public final ImageView imageView131;
    public final ImageView imageView132;
    public final ImageView imageView133;
    public final ImageView imageView134;
    public final ImageView imageView135;
    public final LinearLayout linearLayout5;
    public final LinearLayout llButton;
    public final LinearLayout llRepairMode;
    public final LinearLayout llTips;
    public final LinearLayout llVersion;
    public final LinearLayout llWarning;
    public final ProgressBar overallProgress;
    public final TextView overallText;
    public final ProgressBar progressBar2;
    public final ProgressBar repairProgress;
    public final LinearLayout repairScanProgress;
    private final ConstraintLayout rootView;
    public final TextView scanText;
    public final TextView textView30;
    public final TextView textView31;
    public final TextView textView32;
    public final TextView textView33;
    public final TextView textView34;
    public final TextView textView35;
    public final TextView textView36;
    public final TextView textView37;
    public final TextView tvRepairAdditionalMsg;
    public final TextView tvRepairMode;
    public final TextView tvTips;
    public final TextView tvWarning;
    public final ScrollView uploading;

    private ActivityDfuBinding(ConstraintLayout constraintLayout, ScrollView scrollView, Button button, Button button2, TextView textView, TextView textView2, ProgressBar progressBar, ProgressBar progressBar2, TextView textView3, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, ImageView imageView5, ImageView imageView6, LinearLayout linearLayout, LinearLayout linearLayout2, LinearLayout linearLayout3, LinearLayout linearLayout4, LinearLayout linearLayout5, LinearLayout linearLayout6, ProgressBar progressBar3, TextView textView4, ProgressBar progressBar4, ProgressBar progressBar5, LinearLayout linearLayout7, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9, TextView textView10, TextView textView11, TextView textView12, TextView textView13, TextView textView14, TextView textView15, TextView textView16, TextView textView17, ScrollView scrollView2) {
        this.rootView = constraintLayout;
        this.beforeUpdate = scrollView;
        this.buttonScanDfutarg = button;
        this.buttonUpdate = button2;
        this.currentVersion = textView;
        this.databaseVersion = textView2;
        this.dbvProgress = progressBar;
        this.fileProgress = progressBar2;
        this.fileText = textView3;
        this.imageView13 = imageView;
        this.imageView131 = imageView2;
        this.imageView132 = imageView3;
        this.imageView133 = imageView4;
        this.imageView134 = imageView5;
        this.imageView135 = imageView6;
        this.linearLayout5 = linearLayout;
        this.llButton = linearLayout2;
        this.llRepairMode = linearLayout3;
        this.llTips = linearLayout4;
        this.llVersion = linearLayout5;
        this.llWarning = linearLayout6;
        this.overallProgress = progressBar3;
        this.overallText = textView4;
        this.progressBar2 = progressBar4;
        this.repairProgress = progressBar5;
        this.repairScanProgress = linearLayout7;
        this.scanText = textView5;
        this.textView30 = textView6;
        this.textView31 = textView7;
        this.textView32 = textView8;
        this.textView33 = textView9;
        this.textView34 = textView10;
        this.textView35 = textView11;
        this.textView36 = textView12;
        this.textView37 = textView13;
        this.tvRepairAdditionalMsg = textView14;
        this.tvRepairMode = textView15;
        this.tvTips = textView16;
        this.tvWarning = textView17;
        this.uploading = scrollView2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityDfuBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ActivityDfuBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_dfu, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivityDfuBinding bind(View view) {
        int i = R.id.before_update;
        ScrollView scrollView = (ScrollView) view.findViewById(R.id.before_update);
        if (scrollView != null) {
            i = R.id.button_scan_dfutarg;
            Button button = (Button) view.findViewById(R.id.button_scan_dfutarg);
            if (button != null) {
                i = R.id.button_update;
                Button button2 = (Button) view.findViewById(R.id.button_update);
                if (button2 != null) {
                    i = R.id.current_version;
                    TextView textView = (TextView) view.findViewById(R.id.current_version);
                    if (textView != null) {
                        i = R.id.database_version;
                        TextView textView2 = (TextView) view.findViewById(R.id.database_version);
                        if (textView2 != null) {
                            i = R.id.dbv_progress;
                            ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.dbv_progress);
                            if (progressBar != null) {
                                i = R.id.file_progress;
                                ProgressBar progressBar2 = (ProgressBar) view.findViewById(R.id.file_progress);
                                if (progressBar2 != null) {
                                    i = R.id.file_text;
                                    TextView textView3 = (TextView) view.findViewById(R.id.file_text);
                                    if (textView3 != null) {
                                        i = R.id.imageView13;
                                        ImageView imageView = (ImageView) view.findViewById(R.id.imageView13);
                                        if (imageView != null) {
                                            i = R.id.imageView13_1;
                                            ImageView imageView2 = (ImageView) view.findViewById(R.id.imageView13_1);
                                            if (imageView2 != null) {
                                                i = R.id.imageView13_2;
                                                ImageView imageView3 = (ImageView) view.findViewById(R.id.imageView13_2);
                                                if (imageView3 != null) {
                                                    i = R.id.imageView13_3;
                                                    ImageView imageView4 = (ImageView) view.findViewById(R.id.imageView13_3);
                                                    if (imageView4 != null) {
                                                        i = R.id.imageView13_4;
                                                        ImageView imageView5 = (ImageView) view.findViewById(R.id.imageView13_4);
                                                        if (imageView5 != null) {
                                                            i = R.id.imageView13_5;
                                                            ImageView imageView6 = (ImageView) view.findViewById(R.id.imageView13_5);
                                                            if (imageView6 != null) {
                                                                i = R.id.linearLayout5;
                                                                LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.linearLayout5);
                                                                if (linearLayout != null) {
                                                                    i = R.id.ll_button;
                                                                    LinearLayout linearLayout2 = (LinearLayout) view.findViewById(R.id.ll_button);
                                                                    if (linearLayout2 != null) {
                                                                        i = R.id.ll_repair_mode;
                                                                        LinearLayout linearLayout3 = (LinearLayout) view.findViewById(R.id.ll_repair_mode);
                                                                        if (linearLayout3 != null) {
                                                                            i = R.id.ll_tips;
                                                                            LinearLayout linearLayout4 = (LinearLayout) view.findViewById(R.id.ll_tips);
                                                                            if (linearLayout4 != null) {
                                                                                i = R.id.ll_version;
                                                                                LinearLayout linearLayout5 = (LinearLayout) view.findViewById(R.id.ll_version);
                                                                                if (linearLayout5 != null) {
                                                                                    i = R.id.ll_warning;
                                                                                    LinearLayout linearLayout6 = (LinearLayout) view.findViewById(R.id.ll_warning);
                                                                                    if (linearLayout6 != null) {
                                                                                        i = R.id.overall_progress;
                                                                                        ProgressBar progressBar3 = (ProgressBar) view.findViewById(R.id.overall_progress);
                                                                                        if (progressBar3 != null) {
                                                                                            i = R.id.overall_text;
                                                                                            TextView textView4 = (TextView) view.findViewById(R.id.overall_text);
                                                                                            if (textView4 != null) {
                                                                                                i = R.id.progressBar2;
                                                                                                ProgressBar progressBar4 = (ProgressBar) view.findViewById(R.id.progressBar2);
                                                                                                if (progressBar4 != null) {
                                                                                                    i = R.id.repair_progress;
                                                                                                    ProgressBar progressBar5 = (ProgressBar) view.findViewById(R.id.repair_progress);
                                                                                                    if (progressBar5 != null) {
                                                                                                        i = R.id.repair_scan_progress;
                                                                                                        LinearLayout linearLayout7 = (LinearLayout) view.findViewById(R.id.repair_scan_progress);
                                                                                                        if (linearLayout7 != null) {
                                                                                                            i = R.id.scan_text;
                                                                                                            TextView textView5 = (TextView) view.findViewById(R.id.scan_text);
                                                                                                            if (textView5 != null) {
                                                                                                                i = R.id.textView30;
                                                                                                                TextView textView6 = (TextView) view.findViewById(R.id.textView30);
                                                                                                                if (textView6 != null) {
                                                                                                                    i = R.id.textView31;
                                                                                                                    TextView textView7 = (TextView) view.findViewById(R.id.textView31);
                                                                                                                    if (textView7 != null) {
                                                                                                                        i = R.id.textView32;
                                                                                                                        TextView textView8 = (TextView) view.findViewById(R.id.textView32);
                                                                                                                        if (textView8 != null) {
                                                                                                                            i = R.id.textView33;
                                                                                                                            TextView textView9 = (TextView) view.findViewById(R.id.textView33);
                                                                                                                            if (textView9 != null) {
                                                                                                                                i = R.id.textView34;
                                                                                                                                TextView textView10 = (TextView) view.findViewById(R.id.textView34);
                                                                                                                                if (textView10 != null) {
                                                                                                                                    i = R.id.textView35;
                                                                                                                                    TextView textView11 = (TextView) view.findViewById(R.id.textView35);
                                                                                                                                    if (textView11 != null) {
                                                                                                                                        i = R.id.textView36;
                                                                                                                                        TextView textView12 = (TextView) view.findViewById(R.id.textView36);
                                                                                                                                        if (textView12 != null) {
                                                                                                                                            i = R.id.textView37;
                                                                                                                                            TextView textView13 = (TextView) view.findViewById(R.id.textView37);
                                                                                                                                            if (textView13 != null) {
                                                                                                                                                i = R.id.tv_repair_additional_msg;
                                                                                                                                                TextView textView14 = (TextView) view.findViewById(R.id.tv_repair_additional_msg);
                                                                                                                                                if (textView14 != null) {
                                                                                                                                                    i = R.id.tv_repair_mode;
                                                                                                                                                    TextView textView15 = (TextView) view.findViewById(R.id.tv_repair_mode);
                                                                                                                                                    if (textView15 != null) {
                                                                                                                                                        i = R.id.tv_tips;
                                                                                                                                                        TextView textView16 = (TextView) view.findViewById(R.id.tv_tips);
                                                                                                                                                        if (textView16 != null) {
                                                                                                                                                            i = R.id.tv_warning;
                                                                                                                                                            TextView textView17 = (TextView) view.findViewById(R.id.tv_warning);
                                                                                                                                                            if (textView17 != null) {
                                                                                                                                                                i = R.id.uploading;
                                                                                                                                                                ScrollView scrollView2 = (ScrollView) view.findViewById(R.id.uploading);
                                                                                                                                                                if (scrollView2 != null) {
                                                                                                                                                                    return new ActivityDfuBinding((ConstraintLayout) view, scrollView, button, button2, textView, textView2, progressBar, progressBar2, textView3, imageView, imageView2, imageView3, imageView4, imageView5, imageView6, linearLayout, linearLayout2, linearLayout3, linearLayout4, linearLayout5, linearLayout6, progressBar3, textView4, progressBar4, progressBar5, linearLayout7, textView5, textView6, textView7, textView8, textView9, textView10, textView11, textView12, textView13, textView14, textView15, textView16, textView17, scrollView2);
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
