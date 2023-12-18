package com.egeetouch.egeetouch_manager.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import com.egeetouch.egeetouch_manager.R;
/* loaded from: classes.dex */
public final class ActivityChangePadPasswordBinding implements ViewBinding {
    public final Button btnClearAll;
    public final ImageButton btnDownArrow;
    public final ImageButton btnLeftArrow;
    public final ImageButton btnRightArrow;
    public final Button btnSubmitPadPass;
    public final ImageButton btnUpArrow;
    public final ImageView imageSwitch;
    private final ConstraintLayout rootView;
    public final TextView textPadPass;
    public final TextView txCurrentPass;
    public final TextView txOldPass;

    private ActivityChangePadPasswordBinding(ConstraintLayout constraintLayout, Button button, ImageButton imageButton, ImageButton imageButton2, ImageButton imageButton3, Button button2, ImageButton imageButton4, ImageView imageView, TextView textView, TextView textView2, TextView textView3) {
        this.rootView = constraintLayout;
        this.btnClearAll = button;
        this.btnDownArrow = imageButton;
        this.btnLeftArrow = imageButton2;
        this.btnRightArrow = imageButton3;
        this.btnSubmitPadPass = button2;
        this.btnUpArrow = imageButton4;
        this.imageSwitch = imageView;
        this.textPadPass = textView;
        this.txCurrentPass = textView2;
        this.txOldPass = textView3;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityChangePadPasswordBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ActivityChangePadPasswordBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_change_pad_password, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivityChangePadPasswordBinding bind(View view) {
        int i = R.id.btn_clear_all;
        Button button = (Button) view.findViewById(R.id.btn_clear_all);
        if (button != null) {
            i = R.id.btn_down_arrow;
            ImageButton imageButton = (ImageButton) view.findViewById(R.id.btn_down_arrow);
            if (imageButton != null) {
                i = R.id.btn_left_arrow;
                ImageButton imageButton2 = (ImageButton) view.findViewById(R.id.btn_left_arrow);
                if (imageButton2 != null) {
                    i = R.id.btn_right_arrow;
                    ImageButton imageButton3 = (ImageButton) view.findViewById(R.id.btn_right_arrow);
                    if (imageButton3 != null) {
                        i = R.id.btn_submit_pad_pass;
                        Button button2 = (Button) view.findViewById(R.id.btn_submit_pad_pass);
                        if (button2 != null) {
                            i = R.id.btn_up_arrow;
                            ImageButton imageButton4 = (ImageButton) view.findViewById(R.id.btn_up_arrow);
                            if (imageButton4 != null) {
                                i = R.id.image_switch;
                                ImageView imageView = (ImageView) view.findViewById(R.id.image_switch);
                                if (imageView != null) {
                                    i = R.id.text_pad_pass;
                                    TextView textView = (TextView) view.findViewById(R.id.text_pad_pass);
                                    if (textView != null) {
                                        i = R.id.tx_current_pass;
                                        TextView textView2 = (TextView) view.findViewById(R.id.tx_current_pass);
                                        if (textView2 != null) {
                                            i = R.id.tx_old_pass;
                                            TextView textView3 = (TextView) view.findViewById(R.id.tx_old_pass);
                                            if (textView3 != null) {
                                                return new ActivityChangePadPasswordBinding((ConstraintLayout) view, button, imageButton, imageButton2, imageButton3, button2, imageButton4, imageView, textView, textView2, textView3);
                                            }
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
