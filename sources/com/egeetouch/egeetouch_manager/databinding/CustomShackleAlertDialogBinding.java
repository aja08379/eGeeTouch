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
/* loaded from: classes.dex */
public final class CustomShackleAlertDialogBinding implements ViewBinding {
    public final Button btnCloseShackle;
    public final ImageView imageClosePopUp;
    public final ImageView imgShackle;
    public final LinearLayout llInnerLayout;
    public final TextView messageTV;
    public final RelativeLayout rlCustomDialog;
    private final RelativeLayout rootView;
    public final TextView titleTv;

    private CustomShackleAlertDialogBinding(RelativeLayout relativeLayout, Button button, ImageView imageView, ImageView imageView2, LinearLayout linearLayout, TextView textView, RelativeLayout relativeLayout2, TextView textView2) {
        this.rootView = relativeLayout;
        this.btnCloseShackle = button;
        this.imageClosePopUp = imageView;
        this.imgShackle = imageView2;
        this.llInnerLayout = linearLayout;
        this.messageTV = textView;
        this.rlCustomDialog = relativeLayout2;
        this.titleTv = textView2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static CustomShackleAlertDialogBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static CustomShackleAlertDialogBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.custom_shackle_alert_dialog, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static CustomShackleAlertDialogBinding bind(View view) {
        int i = R.id.btn_closeShackle;
        Button button = (Button) view.findViewById(R.id.btn_closeShackle);
        if (button != null) {
            i = R.id.image_closePopUp;
            ImageView imageView = (ImageView) view.findViewById(R.id.image_closePopUp);
            if (imageView != null) {
                i = R.id.img_shackle;
                ImageView imageView2 = (ImageView) view.findViewById(R.id.img_shackle);
                if (imageView2 != null) {
                    i = R.id.ll_inner_layout;
                    LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.ll_inner_layout);
                    if (linearLayout != null) {
                        i = R.id.messageTV;
                        TextView textView = (TextView) view.findViewById(R.id.messageTV);
                        if (textView != null) {
                            RelativeLayout relativeLayout = (RelativeLayout) view;
                            i = R.id.titleTv;
                            TextView textView2 = (TextView) view.findViewById(R.id.titleTv);
                            if (textView2 != null) {
                                return new CustomShackleAlertDialogBinding(relativeLayout, button, imageView, imageView2, linearLayout, textView, relativeLayout, textView2);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
