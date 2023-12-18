package com.egeetouch.egeetouch_manager.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import com.egeetouch.egeetouch_manager.R;
/* loaded from: classes.dex */
public final class ActivityPermissionBinding implements ViewBinding {
    public final ImageView bluetoothCheck;
    public final ImageView bluetoothIcon;
    public final Button btnSettings;
    public final ImageView locationCheck;
    public final ImageView locationIcon;
    private final ConstraintLayout rootView;
    public final TextView textView25;
    public final TextView textView26;

    private ActivityPermissionBinding(ConstraintLayout constraintLayout, ImageView imageView, ImageView imageView2, Button button, ImageView imageView3, ImageView imageView4, TextView textView, TextView textView2) {
        this.rootView = constraintLayout;
        this.bluetoothCheck = imageView;
        this.bluetoothIcon = imageView2;
        this.btnSettings = button;
        this.locationCheck = imageView3;
        this.locationIcon = imageView4;
        this.textView25 = textView;
        this.textView26 = textView2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityPermissionBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ActivityPermissionBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_permission, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivityPermissionBinding bind(View view) {
        int i = R.id.bluetooth_check;
        ImageView imageView = (ImageView) view.findViewById(R.id.bluetooth_check);
        if (imageView != null) {
            i = R.id.bluetooth_icon;
            ImageView imageView2 = (ImageView) view.findViewById(R.id.bluetooth_icon);
            if (imageView2 != null) {
                i = R.id.btn_settings;
                Button button = (Button) view.findViewById(R.id.btn_settings);
                if (button != null) {
                    i = R.id.location_check;
                    ImageView imageView3 = (ImageView) view.findViewById(R.id.location_check);
                    if (imageView3 != null) {
                        i = R.id.location_icon;
                        ImageView imageView4 = (ImageView) view.findViewById(R.id.location_icon);
                        if (imageView4 != null) {
                            i = R.id.textView25;
                            TextView textView = (TextView) view.findViewById(R.id.textView25);
                            if (textView != null) {
                                i = R.id.textView26;
                                TextView textView2 = (TextView) view.findViewById(R.id.textView26);
                                if (textView2 != null) {
                                    return new ActivityPermissionBinding((ConstraintLayout) view, imageView, imageView2, button, imageView3, imageView4, textView, textView2);
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
