package com.egeetouch.egeetouch_manager.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import com.egeetouch.egeetouch_manager.R;
/* loaded from: classes.dex */
public final class ResetPrimaryPasswordBinding implements ViewBinding {
    public final Button buttonCancel;
    public final Button buttonReset;
    public final TextView popUpTitle;
    public final EditText resetPPassword;
    public final EditText resetPPasswordConfirm;
    private final RelativeLayout rootView;

    private ResetPrimaryPasswordBinding(RelativeLayout relativeLayout, Button button, Button button2, TextView textView, EditText editText, EditText editText2) {
        this.rootView = relativeLayout;
        this.buttonCancel = button;
        this.buttonReset = button2;
        this.popUpTitle = textView;
        this.resetPPassword = editText;
        this.resetPPasswordConfirm = editText2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static ResetPrimaryPasswordBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ResetPrimaryPasswordBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.reset_primary_password, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ResetPrimaryPasswordBinding bind(View view) {
        int i = R.id.button_cancel;
        Button button = (Button) view.findViewById(R.id.button_cancel);
        if (button != null) {
            i = R.id.button_reset;
            Button button2 = (Button) view.findViewById(R.id.button_reset);
            if (button2 != null) {
                i = R.id.pop_up_title;
                TextView textView = (TextView) view.findViewById(R.id.pop_up_title);
                if (textView != null) {
                    i = R.id.reset_p_password;
                    EditText editText = (EditText) view.findViewById(R.id.reset_p_password);
                    if (editText != null) {
                        i = R.id.reset_p_password_confirm;
                        EditText editText2 = (EditText) view.findViewById(R.id.reset_p_password_confirm);
                        if (editText2 != null) {
                            return new ResetPrimaryPasswordBinding((RelativeLayout) view, button, button2, textView, editText, editText2);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
