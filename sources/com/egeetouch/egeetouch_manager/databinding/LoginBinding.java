package com.egeetouch.egeetouch_manager.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.viewbinding.ViewBinding;
import com.egeetouch.egeetouch_manager.R;
/* loaded from: classes.dex */
public final class LoginBinding implements ViewBinding {
    public final LinearLayout LinearLayoutLoginDefocusEditbox;
    public final Button buttonLogin;
    public final Button buttonSkip;
    public final CheckBox checkBoxRmbLogin;
    public final CoordinatorLayout coordinatorLayout;
    public final EditText editTextLoginEmail;
    public final EditText editTextLoginPassword;
    public final ImageView imageView1;
    private final CoordinatorLayout rootView;
    public final ScrollView scrollView1;
    public final Switch switchRmbLogin;
    public final TextView textView1;
    public final TextView textViewCreateacc;
    public final TextView textViewForgotPassword;

    private LoginBinding(CoordinatorLayout coordinatorLayout, LinearLayout linearLayout, Button button, Button button2, CheckBox checkBox, CoordinatorLayout coordinatorLayout2, EditText editText, EditText editText2, ImageView imageView, ScrollView scrollView, Switch r11, TextView textView, TextView textView2, TextView textView3) {
        this.rootView = coordinatorLayout;
        this.LinearLayoutLoginDefocusEditbox = linearLayout;
        this.buttonLogin = button;
        this.buttonSkip = button2;
        this.checkBoxRmbLogin = checkBox;
        this.coordinatorLayout = coordinatorLayout2;
        this.editTextLoginEmail = editText;
        this.editTextLoginPassword = editText2;
        this.imageView1 = imageView;
        this.scrollView1 = scrollView;
        this.switchRmbLogin = r11;
        this.textView1 = textView;
        this.textViewCreateacc = textView2;
        this.textViewForgotPassword = textView3;
    }

    @Override // androidx.viewbinding.ViewBinding
    public CoordinatorLayout getRoot() {
        return this.rootView;
    }

    public static LoginBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static LoginBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.login, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static LoginBinding bind(View view) {
        int i = R.id.LinearLayout_login_defocus_editbox;
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.LinearLayout_login_defocus_editbox);
        if (linearLayout != null) {
            i = R.id.button_login;
            Button button = (Button) view.findViewById(R.id.button_login);
            if (button != null) {
                i = R.id.button_skip;
                Button button2 = (Button) view.findViewById(R.id.button_skip);
                if (button2 != null) {
                    i = R.id.checkBox_rmb_login;
                    CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkBox_rmb_login);
                    if (checkBox != null) {
                        CoordinatorLayout coordinatorLayout = (CoordinatorLayout) view;
                        i = R.id.editText_login_email;
                        EditText editText = (EditText) view.findViewById(R.id.editText_login_email);
                        if (editText != null) {
                            i = R.id.editText_login_password;
                            EditText editText2 = (EditText) view.findViewById(R.id.editText_login_password);
                            if (editText2 != null) {
                                i = R.id.imageView1;
                                ImageView imageView = (ImageView) view.findViewById(R.id.imageView1);
                                if (imageView != null) {
                                    i = R.id.scrollView1;
                                    ScrollView scrollView = (ScrollView) view.findViewById(R.id.scrollView1);
                                    if (scrollView != null) {
                                        i = R.id.switch_rmb_login;
                                        Switch r14 = (Switch) view.findViewById(R.id.switch_rmb_login);
                                        if (r14 != null) {
                                            i = R.id.textView1;
                                            TextView textView = (TextView) view.findViewById(R.id.textView1);
                                            if (textView != null) {
                                                i = R.id.textView_createacc;
                                                TextView textView2 = (TextView) view.findViewById(R.id.textView_createacc);
                                                if (textView2 != null) {
                                                    i = R.id.textView_forgot_password;
                                                    TextView textView3 = (TextView) view.findViewById(R.id.textView_forgot_password);
                                                    if (textView3 != null) {
                                                        return new LoginBinding(coordinatorLayout, linearLayout, button, button2, checkBox, coordinatorLayout, editText, editText2, imageView, scrollView, r14, textView, textView2, textView3);
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
