package com.egeetouch.egeetouch_manager.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import com.egeetouch.egeetouch_manager.R;
/* loaded from: classes.dex */
public final class TestingLayoutForLoginBinding implements ViewBinding {
    public final LinearLayout LinearLayoutLoginDefocusEditbox;
    public final TextView btnFgt;
    public final Button btnLogin;
    public final TextView btnSignup;
    public final EditText editTextLoginEmail;
    public final EditText editTextLoginPassword;
    private final LinearLayout rootView;
    public final Switch switchRmbLogin;

    private TestingLayoutForLoginBinding(LinearLayout linearLayout, LinearLayout linearLayout2, TextView textView, Button button, TextView textView2, EditText editText, EditText editText2, Switch r8) {
        this.rootView = linearLayout;
        this.LinearLayoutLoginDefocusEditbox = linearLayout2;
        this.btnFgt = textView;
        this.btnLogin = button;
        this.btnSignup = textView2;
        this.editTextLoginEmail = editText;
        this.editTextLoginPassword = editText2;
        this.switchRmbLogin = r8;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static TestingLayoutForLoginBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static TestingLayoutForLoginBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.testing_layout_for_login, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static TestingLayoutForLoginBinding bind(View view) {
        int i = R.id.LinearLayout_login_defocus_editbox;
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.LinearLayout_login_defocus_editbox);
        if (linearLayout != null) {
            i = R.id.btn_fgt;
            TextView textView = (TextView) view.findViewById(R.id.btn_fgt);
            if (textView != null) {
                i = R.id.btn_login;
                Button button = (Button) view.findViewById(R.id.btn_login);
                if (button != null) {
                    i = R.id.btn_signup;
                    TextView textView2 = (TextView) view.findViewById(R.id.btn_signup);
                    if (textView2 != null) {
                        i = R.id.editText_login_email;
                        EditText editText = (EditText) view.findViewById(R.id.editText_login_email);
                        if (editText != null) {
                            i = R.id.editText_login_password;
                            EditText editText2 = (EditText) view.findViewById(R.id.editText_login_password);
                            if (editText2 != null) {
                                i = R.id.switch_rmb_login;
                                Switch r10 = (Switch) view.findViewById(R.id.switch_rmb_login);
                                if (r10 != null) {
                                    return new TestingLayoutForLoginBinding((LinearLayout) view, linearLayout, textView, button, textView2, editText, editText2, r10);
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
