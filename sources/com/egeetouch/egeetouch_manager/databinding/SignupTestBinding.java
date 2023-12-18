package com.egeetouch.egeetouch_manager.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.viewbinding.ViewBinding;
import com.egeetouch.egeetouch_manager.R;
/* loaded from: classes.dex */
public final class SignupTestBinding implements ViewBinding {
    public final LinearLayout LinearLayoutSignupDefocusEditbox;
    public final ConstraintLayout LinearLayoutSignupMain;
    public final Button button1CreateAcc;
    public final Button button1Skip;
    public final CheckBox checkBoxSubscription;
    public final CoordinatorLayout coordinatorLayout;
    public final EditText editTextEmail;
    public final EditText editTextFirstNameTemp;
    public final EditText editTextLastName;
    public final EditText editTextPassword1;
    public final EditText editTextPassword2;
    public final ImageView imageView1;
    private final CoordinatorLayout rootView;
    public final ScrollView scrollView1;
    public final TextView textView;
    public final TextView textView1;
    public final TextView textView3;
    public final TextView textView4;
    public final TextView textView5;
    public final TextView textView6;
    public final TextView textViewVersion;

    private SignupTestBinding(CoordinatorLayout coordinatorLayout, LinearLayout linearLayout, ConstraintLayout constraintLayout, Button button, Button button2, CheckBox checkBox, CoordinatorLayout coordinatorLayout2, EditText editText, EditText editText2, EditText editText3, EditText editText4, EditText editText5, ImageView imageView, ScrollView scrollView, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7) {
        this.rootView = coordinatorLayout;
        this.LinearLayoutSignupDefocusEditbox = linearLayout;
        this.LinearLayoutSignupMain = constraintLayout;
        this.button1CreateAcc = button;
        this.button1Skip = button2;
        this.checkBoxSubscription = checkBox;
        this.coordinatorLayout = coordinatorLayout2;
        this.editTextEmail = editText;
        this.editTextFirstNameTemp = editText2;
        this.editTextLastName = editText3;
        this.editTextPassword1 = editText4;
        this.editTextPassword2 = editText5;
        this.imageView1 = imageView;
        this.scrollView1 = scrollView;
        this.textView = textView;
        this.textView1 = textView2;
        this.textView3 = textView3;
        this.textView4 = textView4;
        this.textView5 = textView5;
        this.textView6 = textView6;
        this.textViewVersion = textView7;
    }

    @Override // androidx.viewbinding.ViewBinding
    public CoordinatorLayout getRoot() {
        return this.rootView;
    }

    public static SignupTestBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static SignupTestBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.signup_test, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static SignupTestBinding bind(View view) {
        int i = R.id.LinearLayout_signup_defocus_editbox;
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.LinearLayout_signup_defocus_editbox);
        if (linearLayout != null) {
            i = R.id.LinearLayout_signup_main;
            ConstraintLayout constraintLayout = (ConstraintLayout) view.findViewById(R.id.LinearLayout_signup_main);
            if (constraintLayout != null) {
                i = R.id.button1_createAcc;
                Button button = (Button) view.findViewById(R.id.button1_createAcc);
                if (button != null) {
                    i = R.id.button1_skip;
                    Button button2 = (Button) view.findViewById(R.id.button1_skip);
                    if (button2 != null) {
                        i = R.id.checkBox_subscription;
                        CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkBox_subscription);
                        if (checkBox != null) {
                            CoordinatorLayout coordinatorLayout = (CoordinatorLayout) view;
                            i = R.id.editText_email;
                            EditText editText = (EditText) view.findViewById(R.id.editText_email);
                            if (editText != null) {
                                i = R.id.editText_first_name_temp;
                                EditText editText2 = (EditText) view.findViewById(R.id.editText_first_name_temp);
                                if (editText2 != null) {
                                    i = R.id.editText_last_name;
                                    EditText editText3 = (EditText) view.findViewById(R.id.editText_last_name);
                                    if (editText3 != null) {
                                        i = R.id.editText_password1;
                                        EditText editText4 = (EditText) view.findViewById(R.id.editText_password1);
                                        if (editText4 != null) {
                                            i = R.id.editText_password2;
                                            EditText editText5 = (EditText) view.findViewById(R.id.editText_password2);
                                            if (editText5 != null) {
                                                i = R.id.imageView1;
                                                ImageView imageView = (ImageView) view.findViewById(R.id.imageView1);
                                                if (imageView != null) {
                                                    i = R.id.scrollView1;
                                                    ScrollView scrollView = (ScrollView) view.findViewById(R.id.scrollView1);
                                                    if (scrollView != null) {
                                                        i = R.id.textView;
                                                        TextView textView = (TextView) view.findViewById(R.id.textView);
                                                        if (textView != null) {
                                                            i = R.id.textView1;
                                                            TextView textView2 = (TextView) view.findViewById(R.id.textView1);
                                                            if (textView2 != null) {
                                                                i = R.id.textView3;
                                                                TextView textView3 = (TextView) view.findViewById(R.id.textView3);
                                                                if (textView3 != null) {
                                                                    i = R.id.textView4;
                                                                    TextView textView4 = (TextView) view.findViewById(R.id.textView4);
                                                                    if (textView4 != null) {
                                                                        i = R.id.textView5;
                                                                        TextView textView5 = (TextView) view.findViewById(R.id.textView5);
                                                                        if (textView5 != null) {
                                                                            i = R.id.textView6;
                                                                            TextView textView6 = (TextView) view.findViewById(R.id.textView6);
                                                                            if (textView6 != null) {
                                                                                i = R.id.textView_version;
                                                                                TextView textView7 = (TextView) view.findViewById(R.id.textView_version);
                                                                                if (textView7 != null) {
                                                                                    return new SignupTestBinding(coordinatorLayout, linearLayout, constraintLayout, button, button2, checkBox, coordinatorLayout, editText, editText2, editText3, editText4, editText5, imageView, scrollView, textView, textView2, textView3, textView4, textView5, textView6, textView7);
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
