package com.egeetouch.egeetouch_manager.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import androidx.viewbinding.ViewBinding;
import com.egeetouch.egeetouch_manager.R;
/* loaded from: classes.dex */
public final class EgeetouchCommercialAdvetPageBinding implements ViewBinding {
    public final Button btnSendEmail;
    private final RelativeLayout rootView;
    public final ScrollView scrollView1;

    private EgeetouchCommercialAdvetPageBinding(RelativeLayout relativeLayout, Button button, ScrollView scrollView) {
        this.rootView = relativeLayout;
        this.btnSendEmail = button;
        this.scrollView1 = scrollView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static EgeetouchCommercialAdvetPageBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static EgeetouchCommercialAdvetPageBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.egeetouch_commercial_advet_page, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static EgeetouchCommercialAdvetPageBinding bind(View view) {
        int i = R.id.btn_sendEmail;
        Button button = (Button) view.findViewById(R.id.btn_sendEmail);
        if (button != null) {
            i = R.id.scrollView1;
            ScrollView scrollView = (ScrollView) view.findViewById(R.id.scrollView1);
            if (scrollView != null) {
                return new EgeetouchCommercialAdvetPageBinding((RelativeLayout) view, button, scrollView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
