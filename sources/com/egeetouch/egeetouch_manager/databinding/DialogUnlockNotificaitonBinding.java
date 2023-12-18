package com.egeetouch.egeetouch_manager.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import com.egeetouch.egeetouch_manager.R;
/* loaded from: classes.dex */
public final class DialogUnlockNotificaitonBinding implements ViewBinding {
    public final Button btnDontRemind;
    public final Button btnOk;
    private final ConstraintLayout rootView;

    private DialogUnlockNotificaitonBinding(ConstraintLayout constraintLayout, Button button, Button button2) {
        this.rootView = constraintLayout;
        this.btnDontRemind = button;
        this.btnOk = button2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static DialogUnlockNotificaitonBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static DialogUnlockNotificaitonBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.dialog_unlock_notificaiton, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static DialogUnlockNotificaitonBinding bind(View view) {
        int i = R.id.btn_dont_remind;
        Button button = (Button) view.findViewById(R.id.btn_dont_remind);
        if (button != null) {
            i = R.id.btn_ok;
            Button button2 = (Button) view.findViewById(R.id.btn_ok);
            if (button2 != null) {
                return new DialogUnlockNotificaitonBinding((ConstraintLayout) view, button, button2);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
