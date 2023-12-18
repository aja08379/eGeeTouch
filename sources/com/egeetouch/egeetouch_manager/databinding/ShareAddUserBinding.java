package com.egeetouch.egeetouch_manager.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.viewbinding.ViewBinding;
import com.egeetouch.egeetouch_manager.R;
/* loaded from: classes.dex */
public final class ShareAddUserBinding implements ViewBinding {
    public final LinearLayout LinearLayoutSignupDefocusEditbox;
    public final ConstraintLayout LinearLayoutSignupMain;
    public final Button button1CreateAcc;
    public final CoordinatorLayout coordinatorLayout;
    public final EditText editTextEmail;
    public final EditText editTextShareName;
    public final ImageView imageView1;
    private final CoordinatorLayout rootView;
    public final ScrollView scrollView1;

    private ShareAddUserBinding(CoordinatorLayout coordinatorLayout, LinearLayout linearLayout, ConstraintLayout constraintLayout, Button button, CoordinatorLayout coordinatorLayout2, EditText editText, EditText editText2, ImageView imageView, ScrollView scrollView) {
        this.rootView = coordinatorLayout;
        this.LinearLayoutSignupDefocusEditbox = linearLayout;
        this.LinearLayoutSignupMain = constraintLayout;
        this.button1CreateAcc = button;
        this.coordinatorLayout = coordinatorLayout2;
        this.editTextEmail = editText;
        this.editTextShareName = editText2;
        this.imageView1 = imageView;
        this.scrollView1 = scrollView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public CoordinatorLayout getRoot() {
        return this.rootView;
    }

    public static ShareAddUserBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ShareAddUserBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.share_add_user, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ShareAddUserBinding bind(View view) {
        int i = R.id.LinearLayout_signup_defocus_editbox;
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.LinearLayout_signup_defocus_editbox);
        if (linearLayout != null) {
            i = R.id.LinearLayout_signup_main;
            ConstraintLayout constraintLayout = (ConstraintLayout) view.findViewById(R.id.LinearLayout_signup_main);
            if (constraintLayout != null) {
                i = R.id.button1_createAcc;
                Button button = (Button) view.findViewById(R.id.button1_createAcc);
                if (button != null) {
                    CoordinatorLayout coordinatorLayout = (CoordinatorLayout) view;
                    i = R.id.editText_email;
                    EditText editText = (EditText) view.findViewById(R.id.editText_email);
                    if (editText != null) {
                        i = R.id.editText_share_name;
                        EditText editText2 = (EditText) view.findViewById(R.id.editText_share_name);
                        if (editText2 != null) {
                            i = R.id.imageView1;
                            ImageView imageView = (ImageView) view.findViewById(R.id.imageView1);
                            if (imageView != null) {
                                i = R.id.scrollView1;
                                ScrollView scrollView = (ScrollView) view.findViewById(R.id.scrollView1);
                                if (scrollView != null) {
                                    return new ShareAddUserBinding(coordinatorLayout, linearLayout, constraintLayout, button, coordinatorLayout, editText, editText2, imageView, scrollView);
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
