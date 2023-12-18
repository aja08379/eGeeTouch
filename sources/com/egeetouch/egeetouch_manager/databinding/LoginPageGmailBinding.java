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
import androidx.viewpager.widget.ViewPager;
import com.egeetouch.egeetouch_manager.R;
import com.facebook.login.widget.LoginButton;
/* loaded from: classes.dex */
public final class LoginPageGmailBinding implements ViewBinding {
    public final LinearLayout LinearLayoutLoginButton;
    public final RelativeLayout RelativeLayout1;
    public final Button buttonFb;
    public final Button buttonGmail;
    public final Button buttonLogin;
    public final Button buttonSignup;
    public final ImageView imageViewBanner;
    public final LoginButton loginButtonFb;
    public final ViewPager myviewpager;
    private final RelativeLayout rootView;
    public final TextView textViewVersion2;

    private LoginPageGmailBinding(RelativeLayout relativeLayout, LinearLayout linearLayout, RelativeLayout relativeLayout2, Button button, Button button2, Button button3, Button button4, ImageView imageView, LoginButton loginButton, ViewPager viewPager, TextView textView) {
        this.rootView = relativeLayout;
        this.LinearLayoutLoginButton = linearLayout;
        this.RelativeLayout1 = relativeLayout2;
        this.buttonFb = button;
        this.buttonGmail = button2;
        this.buttonLogin = button3;
        this.buttonSignup = button4;
        this.imageViewBanner = imageView;
        this.loginButtonFb = loginButton;
        this.myviewpager = viewPager;
        this.textViewVersion2 = textView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static LoginPageGmailBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static LoginPageGmailBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.login_page_gmail, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static LoginPageGmailBinding bind(View view) {
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
                            i = R.id.imageView_banner;
                            ImageView imageView = (ImageView) view.findViewById(R.id.imageView_banner);
                            if (imageView != null) {
                                i = R.id.login_button_fb;
                                LoginButton loginButton = (LoginButton) view.findViewById(R.id.login_button_fb);
                                if (loginButton != null) {
                                    i = R.id.myviewpager;
                                    ViewPager viewPager = (ViewPager) view.findViewById(R.id.myviewpager);
                                    if (viewPager != null) {
                                        i = R.id.textView_version2;
                                        TextView textView = (TextView) view.findViewById(R.id.textView_version2);
                                        if (textView != null) {
                                            return new LoginPageGmailBinding(relativeLayout, linearLayout, relativeLayout, button, button2, button3, button4, imageView, loginButton, viewPager, textView);
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
