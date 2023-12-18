package com.egeetouch.egeetouch_manager.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import com.egeetouch.egeetouch_manager.R;
/* loaded from: classes.dex */
public final class ActivityShareLockBinding implements ViewBinding {
    public final LinearLayout LinearLayoutLock;
    public final LinearLayout LinearLayoutMode;
    public final LinearLayout LinearLayoutUser;
    public final Button btnEndDate;
    public final Button btnManageRecipient;
    public final Button btnStartDate;
    public final Button buttonRetractAccess;
    public final Button buttonShareHistory;
    public final Button buttonShareSubmit;
    public final Button chooseDurationBtn;
    public final RelativeLayout chooseDurationRelativeView;
    public final Switch chooseDurationSwitch;
    public final EditText endDateTextfield;
    public final ImageView imageView5;
    public final Switch retractableSwitch;
    private final LinearLayout rootView;
    public final Spinner spinnerLock;
    public final Spinner spinnerMode;
    public final Spinner spinnerUser;
    public final EditText startDateTextfield;
    public final TextView textViewLock;
    public final TextView textViewMode;
    public final TextView textViewUser;
    public final TextView tvModeInfo;

    private ActivityShareLockBinding(LinearLayout linearLayout, LinearLayout linearLayout2, LinearLayout linearLayout3, LinearLayout linearLayout4, Button button, Button button2, Button button3, Button button4, Button button5, Button button6, Button button7, RelativeLayout relativeLayout, Switch r15, EditText editText, ImageView imageView, Switch r18, Spinner spinner, Spinner spinner2, Spinner spinner3, EditText editText2, TextView textView, TextView textView2, TextView textView3, TextView textView4) {
        this.rootView = linearLayout;
        this.LinearLayoutLock = linearLayout2;
        this.LinearLayoutMode = linearLayout3;
        this.LinearLayoutUser = linearLayout4;
        this.btnEndDate = button;
        this.btnManageRecipient = button2;
        this.btnStartDate = button3;
        this.buttonRetractAccess = button4;
        this.buttonShareHistory = button5;
        this.buttonShareSubmit = button6;
        this.chooseDurationBtn = button7;
        this.chooseDurationRelativeView = relativeLayout;
        this.chooseDurationSwitch = r15;
        this.endDateTextfield = editText;
        this.imageView5 = imageView;
        this.retractableSwitch = r18;
        this.spinnerLock = spinner;
        this.spinnerMode = spinner2;
        this.spinnerUser = spinner3;
        this.startDateTextfield = editText2;
        this.textViewLock = textView;
        this.textViewMode = textView2;
        this.textViewUser = textView3;
        this.tvModeInfo = textView4;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ActivityShareLockBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ActivityShareLockBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_share_lock, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivityShareLockBinding bind(View view) {
        int i = R.id.LinearLayout_lock;
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.LinearLayout_lock);
        if (linearLayout != null) {
            i = R.id.LinearLayout_mode;
            LinearLayout linearLayout2 = (LinearLayout) view.findViewById(R.id.LinearLayout_mode);
            if (linearLayout2 != null) {
                i = R.id.LinearLayout_user;
                LinearLayout linearLayout3 = (LinearLayout) view.findViewById(R.id.LinearLayout_user);
                if (linearLayout3 != null) {
                    i = R.id.btn_end_date;
                    Button button = (Button) view.findViewById(R.id.btn_end_date);
                    if (button != null) {
                        i = R.id.btn_manageRecipient;
                        Button button2 = (Button) view.findViewById(R.id.btn_manageRecipient);
                        if (button2 != null) {
                            i = R.id.btn_start_date;
                            Button button3 = (Button) view.findViewById(R.id.btn_start_date);
                            if (button3 != null) {
                                i = R.id.button_retract_access;
                                Button button4 = (Button) view.findViewById(R.id.button_retract_access);
                                if (button4 != null) {
                                    i = R.id.button_share_history;
                                    Button button5 = (Button) view.findViewById(R.id.button_share_history);
                                    if (button5 != null) {
                                        i = R.id.button_share_submit;
                                        Button button6 = (Button) view.findViewById(R.id.button_share_submit);
                                        if (button6 != null) {
                                            i = R.id.chooseDurationBtn;
                                            Button button7 = (Button) view.findViewById(R.id.chooseDurationBtn);
                                            if (button7 != null) {
                                                i = R.id.choose_duration_relative_view;
                                                RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.choose_duration_relative_view);
                                                if (relativeLayout != null) {
                                                    i = R.id.choose_duration_switch;
                                                    Switch r16 = (Switch) view.findViewById(R.id.choose_duration_switch);
                                                    if (r16 != null) {
                                                        i = R.id.end_date_textfield;
                                                        EditText editText = (EditText) view.findViewById(R.id.end_date_textfield);
                                                        if (editText != null) {
                                                            i = R.id.imageView5;
                                                            ImageView imageView = (ImageView) view.findViewById(R.id.imageView5);
                                                            if (imageView != null) {
                                                                i = R.id.retractable_switch;
                                                                Switch r19 = (Switch) view.findViewById(R.id.retractable_switch);
                                                                if (r19 != null) {
                                                                    i = R.id.spinner_lock;
                                                                    Spinner spinner = (Spinner) view.findViewById(R.id.spinner_lock);
                                                                    if (spinner != null) {
                                                                        i = R.id.spinner_mode;
                                                                        Spinner spinner2 = (Spinner) view.findViewById(R.id.spinner_mode);
                                                                        if (spinner2 != null) {
                                                                            i = R.id.spinner_user;
                                                                            Spinner spinner3 = (Spinner) view.findViewById(R.id.spinner_user);
                                                                            if (spinner3 != null) {
                                                                                i = R.id.start_date_textfield;
                                                                                EditText editText2 = (EditText) view.findViewById(R.id.start_date_textfield);
                                                                                if (editText2 != null) {
                                                                                    i = R.id.textView_lock;
                                                                                    TextView textView = (TextView) view.findViewById(R.id.textView_lock);
                                                                                    if (textView != null) {
                                                                                        i = R.id.textView_mode;
                                                                                        TextView textView2 = (TextView) view.findViewById(R.id.textView_mode);
                                                                                        if (textView2 != null) {
                                                                                            i = R.id.textView_user;
                                                                                            TextView textView3 = (TextView) view.findViewById(R.id.textView_user);
                                                                                            if (textView3 != null) {
                                                                                                i = R.id.tv_mode_info;
                                                                                                TextView textView4 = (TextView) view.findViewById(R.id.tv_mode_info);
                                                                                                if (textView4 != null) {
                                                                                                    return new ActivityShareLockBinding((LinearLayout) view, linearLayout, linearLayout2, linearLayout3, button, button2, button3, button4, button5, button6, button7, relativeLayout, r16, editText, imageView, r19, spinner, spinner2, spinner3, editText2, textView, textView2, textView3, textView4);
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
