package com.egeetouch.egeetouch_manager.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.viewbinding.ViewBinding;
import com.egeetouch.egeetouch_manager.R;
/* loaded from: classes.dex */
public final class ForgotPasswordTestBinding implements ViewBinding {
    public final Button buttonSendRecoveryEmail;
    public final CoordinatorLayout coordinatorLayout;
    public final EditText editTextRecoveryEmail;
    public final ImageView imageView1;
    private final CoordinatorLayout rootView;

    private ForgotPasswordTestBinding(CoordinatorLayout coordinatorLayout, Button button, CoordinatorLayout coordinatorLayout2, EditText editText, ImageView imageView) {
        this.rootView = coordinatorLayout;
        this.buttonSendRecoveryEmail = button;
        this.coordinatorLayout = coordinatorLayout2;
        this.editTextRecoveryEmail = editText;
        this.imageView1 = imageView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public CoordinatorLayout getRoot() {
        return this.rootView;
    }

    public static ForgotPasswordTestBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ForgotPasswordTestBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.forgot_password_test, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ForgotPasswordTestBinding bind(View view) {
        int i = R.id.button_send_recovery_email;
        Button button = (Button) view.findViewById(R.id.button_send_recovery_email);
        if (button != null) {
            CoordinatorLayout coordinatorLayout = (CoordinatorLayout) view;
            i = R.id.editText_recovery_email;
            EditText editText = (EditText) view.findViewById(R.id.editText_recovery_email);
            if (editText != null) {
                i = R.id.imageView1;
                ImageView imageView = (ImageView) view.findViewById(R.id.imageView1);
                if (imageView != null) {
                    return new ForgotPasswordTestBinding(coordinatorLayout, button, coordinatorLayout, editText, imageView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
