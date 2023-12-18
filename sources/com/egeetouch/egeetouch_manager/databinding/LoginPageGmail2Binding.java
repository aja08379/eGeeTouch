package com.egeetouch.egeetouch_manager.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import com.egeetouch.egeetouch_manager.R;
import com.facebook.login.widget.LoginButton;
/* loaded from: classes.dex */
public final class LoginPageGmail2Binding implements ViewBinding {
    public final LinearLayout LinearLayoutLoginButton;
    public final RelativeLayout RelativeLayout1;
    public final Button buttonFb;
    public final Button buttonGmail;
    public final Button buttonLogin;
    public final Button buttonSignup;
    public final ImageView imageView6;
    public final ImageView imageView8;
    public final LoginButton loginButtonFb;
    private final RelativeLayout rootView;
    public final TextView textView9;

    private LoginPageGmail2Binding(RelativeLayout relativeLayout, LinearLayout linearLayout, RelativeLayout relativeLayout2, Button button, Button button2, Button button3, Button button4, ImageView imageView, ImageView imageView2, LoginButton loginButton, TextView textView) {
        this.rootView = relativeLayout;
        this.LinearLayoutLoginButton = linearLayout;
        this.RelativeLayout1 = relativeLayout2;
        this.buttonFb = button;
        this.buttonGmail = button2;
        this.buttonLogin = button3;
        this.buttonSignup = button4;
        this.imageView6 = imageView;
        this.imageView8 = imageView2;
        this.loginButtonFb = loginButton;
        this.textView9 = textView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static LoginPageGmail2Binding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static LoginPageGmail2Binding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.login_page_gmail2, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static LoginPageGmail2Binding bind(View view) {
        int i = R.id.LinearLayout_login_button;
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.LinearLayout_login_button);
        if (linearLayout != null) {
            RelativeLayout relativeLayout = (RelativeLayout) view;
            i = R.id.button_fb;
            Button button = (Button) view.findViewById(R.id.button_fb);
            if (button != null) {
                i = R.id.button_gmail;
                Button button2 = (Button) view.findViewById(R.id.button_gmail);
                if (button2 != null) {
                    i = R.id.button_login;
                    Button button3 = (Button) view.findViewById(R.id.button_login);
                    if (button3 != null) {
                        i = R.id.button_signup;
                        Button button4 = (Button) view.findViewById(R.id.button_signup);
                        if (button4 != null) {
                            i = R.id.imageView6;
                            ImageView imageView = (ImageView) view.findViewById(R.id.imageView6);
                            if (imageView != null) {
                                i = R.id.imageView8;
                                ImageView imageView2 = (ImageView) view.findViewById(R.id.imageView8);
                                if (imageView2 != null) {
                                    i = R.id.login_button_fb;
                                    LoginButton loginButton = (LoginButton) view.findViewById(R.id.login_button_fb);
                                    if (loginButton != null) {
                                        i = R.id.textView9;
                                        TextView textView = (TextView) view.findViewById(R.id.textView9);
                                        if (textView != null) {
                                            return new LoginPageGmail2Binding(relativeLayout, linearLayout, relativeLayout, button, button2, button3, button4, imageView, imageView2, loginButton, textView);
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
