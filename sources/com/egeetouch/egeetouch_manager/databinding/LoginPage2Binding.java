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
/* loaded from: classes.dex */
public final class LoginPage2Binding implements ViewBinding {
    public final LinearLayout LinearLayoutLoginButton;
    public final RelativeLayout RelativeLayout1;
    public final Button buttonLogin;
    public final Button buttonSignup;
    public final ImageView imageViewBanner;
    public final ViewPager myviewpager;
    private final RelativeLayout rootView;
    public final TextView textViewVersion2;

    private LoginPage2Binding(RelativeLayout relativeLayout, LinearLayout linearLayout, RelativeLayout relativeLayout2, Button button, Button button2, ImageView imageView, ViewPager viewPager, TextView textView) {
        this.rootView = relativeLayout;
        this.LinearLayoutLoginButton = linearLayout;
        this.RelativeLayout1 = relativeLayout2;
        this.buttonLogin = button;
        this.buttonSignup = button2;
        this.imageViewBanner = imageView;
        this.myviewpager = viewPager;
        this.textViewVersion2 = textView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static LoginPage2Binding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static LoginPage2Binding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.login_page2, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static LoginPage2Binding bind(View view) {
        int i = R.id.LinearLayout_login_button;
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.LinearLayout_login_button);
        if (linearLayout != null) {
            RelativeLayout relativeLayout = (RelativeLayout) view;
            i = R.id.button_login;
            Button button = (Button) view.findViewById(R.id.button_login);
            if (button != null) {
                i = R.id.button_signup;
                Button button2 = (Button) view.findViewById(R.id.button_signup);
                if (button2 != null) {
                    i = R.id.imageView_banner;
                    ImageView imageView = (ImageView) view.findViewById(R.id.imageView_banner);
                    if (imageView != null) {
                        i = R.id.myviewpager;
                        ViewPager viewPager = (ViewPager) view.findViewById(R.id.myviewpager);
                        if (viewPager != null) {
                            i = R.id.textView_version2;
                            TextView textView = (TextView) view.findViewById(R.id.textView_version2);
                            if (textView != null) {
                                return new LoginPage2Binding(relativeLayout, linearLayout, relativeLayout, button, button2, imageView, viewPager, textView);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
