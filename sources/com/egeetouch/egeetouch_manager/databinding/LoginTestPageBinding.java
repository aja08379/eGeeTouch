package com.egeetouch.egeetouch_manager.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import androidx.viewbinding.ViewBinding;
import com.egeetouch.egeetouch_manager.R;
/* loaded from: classes.dex */
public final class LoginTestPageBinding implements ViewBinding {
    public final LinearLayout LinearLayoutLoginDefocusEditbox;
    public final RelativeLayout RelativeLayout1;
    public final EditText editTextLoginEmail;
    public final EditText editTextLoginPassword;
    public final LinearLayout linear;
    private final LinearLayout rootView;
    public final Switch switchRmbLogin;

    private LoginTestPageBinding(LinearLayout linearLayout, LinearLayout linearLayout2, RelativeLayout relativeLayout, EditText editText, EditText editText2, LinearLayout linearLayout3, Switch r7) {
        this.rootView = linearLayout;
        this.LinearLayoutLoginDefocusEditbox = linearLayout2;
        this.RelativeLayout1 = relativeLayout;
        this.editTextLoginEmail = editText;
        this.editTextLoginPassword = editText2;
        this.linear = linearLayout3;
        this.switchRmbLogin = r7;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static LoginTestPageBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static LoginTestPageBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.login_test_page, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static LoginTestPageBinding bind(View view) {
        int i = R.id.LinearLayout_login_defocus_editbox;
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.LinearLayout_login_defocus_editbox);
        if (linearLayout != null) {
            i = R.id.RelativeLayout1;
            RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.RelativeLayout1);
            if (relativeLayout != null) {
                i = R.id.editText_login_email;
                EditText editText = (EditText) view.findViewById(R.id.editText_login_email);
                if (editText != null) {
                    i = R.id.editText_login_password;
                    EditText editText2 = (EditText) view.findViewById(R.id.editText_login_password);
                    if (editText2 != null) {
                        i = R.id.linear;
                        LinearLayout linearLayout2 = (LinearLayout) view.findViewById(R.id.linear);
                        if (linearLayout2 != null) {
                            i = R.id.switch_rmb_login;
                            Switch r9 = (Switch) view.findViewById(R.id.switch_rmb_login);
                            if (r9 != null) {
                                return new LoginTestPageBinding((LinearLayout) view, linearLayout, relativeLayout, editText, editText2, linearLayout2, r9);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
