package com.egeetouch.egeetouch_manager.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import com.egeetouch.egeetouch_manager.R;
/* loaded from: classes.dex */
public final class PrimaryPasswordPopUpBinding implements ViewBinding {
    public final Button buttonStartPairing;
    public final TextView default2;
    public final EditText edPrimaryPassword;
    public final TextView fgtPrimaryPw;
    public final ImageView imgClosePopUp;
    public final TextView popUpTitleTV;
    private final RelativeLayout rootView;

    private PrimaryPasswordPopUpBinding(RelativeLayout relativeLayout, Button button, TextView textView, EditText editText, TextView textView2, ImageView imageView, TextView textView3) {
        this.rootView = relativeLayout;
        this.buttonStartPairing = button;
        this.default2 = textView;
        this.edPrimaryPassword = editText;
        this.fgtPrimaryPw = textView2;
        this.imgClosePopUp = imageView;
        this.popUpTitleTV = textView3;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static PrimaryPasswordPopUpBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static PrimaryPasswordPopUpBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.primary_password_pop_up, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static PrimaryPasswordPopUpBinding bind(View view) {
        int i = R.id.button_startPairing;
        Button button = (Button) view.findViewById(R.id.button_startPairing);
        if (button != null) {
            i = R.id.default2;
            TextView textView = (TextView) view.findViewById(R.id.default2);
            if (textView != null) {
                i = R.id.ed_primary_password;
                EditText editText = (EditText) view.findViewById(R.id.ed_primary_password);
                if (editText != null) {
                    i = R.id.fgt_primary_pw;
                    TextView textView2 = (TextView) view.findViewById(R.id.fgt_primary_pw);
                    if (textView2 != null) {
                        i = R.id.img_closePopUp;
                        ImageView imageView = (ImageView) view.findViewById(R.id.img_closePopUp);
                        if (imageView != null) {
                            i = R.id.pop_up_titleTV;
                            TextView textView3 = (TextView) view.findViewById(R.id.pop_up_titleTV);
                            if (textView3 != null) {
                                return new PrimaryPasswordPopUpBinding((RelativeLayout) view, button, textView, editText, textView2, imageView, textView3);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
