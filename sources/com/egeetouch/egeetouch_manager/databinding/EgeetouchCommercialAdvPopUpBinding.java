package com.egeetouch.egeetouch_manager.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import com.egeetouch.egeetouch_manager.R;
/* loaded from: classes.dex */
public final class EgeetouchCommercialAdvPopUpBinding implements ViewBinding {
    public final Button buttonMoreDetails;
    public final TextView default2;
    public final TextView defaultTV;
    public final ImageView imgageClosePopUp;
    public final TextView popUpSubTitleTV;
    public final TextView popUpTitleTV;
    private final RelativeLayout rootView;

    private EgeetouchCommercialAdvPopUpBinding(RelativeLayout relativeLayout, Button button, TextView textView, TextView textView2, ImageView imageView, TextView textView3, TextView textView4) {
        this.rootView = relativeLayout;
        this.buttonMoreDetails = button;
        this.default2 = textView;
        this.defaultTV = textView2;
        this.imgageClosePopUp = imageView;
        this.popUpSubTitleTV = textView3;
        this.popUpTitleTV = textView4;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static EgeetouchCommercialAdvPopUpBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static EgeetouchCommercialAdvPopUpBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.egeetouch_commercial_adv_pop_up, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static EgeetouchCommercialAdvPopUpBinding bind(View view) {
        int i = R.id.button_moreDetails;
        Button button = (Button) view.findViewById(R.id.button_moreDetails);
        if (button != null) {
            i = R.id.default2;
            TextView textView = (TextView) view.findViewById(R.id.default2);
            if (textView != null) {
                i = R.id.defaultTV;
                TextView textView2 = (TextView) view.findViewById(R.id.defaultTV);
                if (textView2 != null) {
                    i = R.id.imgage_closePopUp;
                    ImageView imageView = (ImageView) view.findViewById(R.id.imgage_closePopUp);
                    if (imageView != null) {
                        i = R.id.pop_up_sub_titleTV;
                        TextView textView3 = (TextView) view.findViewById(R.id.pop_up_sub_titleTV);
                        if (textView3 != null) {
                            i = R.id.pop_up_titleTV;
                            TextView textView4 = (TextView) view.findViewById(R.id.pop_up_titleTV);
                            if (textView4 != null) {
                                return new EgeetouchCommercialAdvPopUpBinding((RelativeLayout) view, button, textView, textView2, imageView, textView3, textView4);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
