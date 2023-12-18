package com.egeetouch.egeetouch_manager.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import com.egeetouch.egeetouch_manager.R;
/* loaded from: classes.dex */
public final class FragmentRetypePasswordBinding implements ViewBinding {
    public final LinearLayout LinearLayout1;
    public final LinearLayout LinearLayoutLuggagelock;
    public final LinearLayout LinearLayoutPadlock;
    public final LinearLayout LinearLayoutStep4;
    public final Button buttonSavePair;
    public final CheckBox checkBoxGT2000;
    public final CheckBox checkBoxGT3000;
    public final EditText editTextPrimarypassword2;
    public final ImageView imageViewGt2000;
    public final ImageView imageViewGt3000;
    private final LinearLayout rootView;
    public final TextView textViewAddlockStep4;
    public final TextView textViewLuggagelock;
    public final TextView textViewPadlock;
    public final TextView textViewPrimarypassword;

    private FragmentRetypePasswordBinding(LinearLayout linearLayout, LinearLayout linearLayout2, LinearLayout linearLayout3, LinearLayout linearLayout4, LinearLayout linearLayout5, Button button, CheckBox checkBox, CheckBox checkBox2, EditText editText, ImageView imageView, ImageView imageView2, TextView textView, TextView textView2, TextView textView3, TextView textView4) {
        this.rootView = linearLayout;
        this.LinearLayout1 = linearLayout2;
        this.LinearLayoutLuggagelock = linearLayout3;
        this.LinearLayoutPadlock = linearLayout4;
        this.LinearLayoutStep4 = linearLayout5;
        this.buttonSavePair = button;
        this.checkBoxGT2000 = checkBox;
        this.checkBoxGT3000 = checkBox2;
        this.editTextPrimarypassword2 = editText;
        this.imageViewGt2000 = imageView;
        this.imageViewGt3000 = imageView2;
        this.textViewAddlockStep4 = textView;
        this.textViewLuggagelock = textView2;
        this.textViewPadlock = textView3;
        this.textViewPrimarypassword = textView4;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static FragmentRetypePasswordBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentRetypePasswordBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fragment_retype_password, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static FragmentRetypePasswordBinding bind(View view) {
        LinearLayout linearLayout = (LinearLayout) view;
        int i = R.id.LinearLayout_luggagelock;
        LinearLayout linearLayout2 = (LinearLayout) view.findViewById(R.id.LinearLayout_luggagelock);
        if (linearLayout2 != null) {
            i = R.id.LinearLayout_padlock;
            LinearLayout linearLayout3 = (LinearLayout) view.findViewById(R.id.LinearLayout_padlock);
            if (linearLayout3 != null) {
                i = R.id.LinearLayout_step4;
                LinearLayout linearLayout4 = (LinearLayout) view.findViewById(R.id.LinearLayout_step4);
                if (linearLayout4 != null) {
                    i = R.id.button_save_pair;
                    Button button = (Button) view.findViewById(R.id.button_save_pair);
                    if (button != null) {
                        i = R.id.checkBox_GT2000;
                        CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkBox_GT2000);
                        if (checkBox != null) {
                            i = R.id.checkBox_GT3000;
                            CheckBox checkBox2 = (CheckBox) view.findViewById(R.id.checkBox_GT3000);
                            if (checkBox2 != null) {
                                i = R.id.editText_primarypassword2;
                                EditText editText = (EditText) view.findViewById(R.id.editText_primarypassword2);
                                if (editText != null) {
                                    i = R.id.imageView_gt2000;
                                    ImageView imageView = (ImageView) view.findViewById(R.id.imageView_gt2000);
                                    if (imageView != null) {
                                        i = R.id.imageView_gt3000;
                                        ImageView imageView2 = (ImageView) view.findViewById(R.id.imageView_gt3000);
                                        if (imageView2 != null) {
                                            i = R.id.textView_addlock_step4;
                                            TextView textView = (TextView) view.findViewById(R.id.textView_addlock_step4);
                                            if (textView != null) {
                                                i = R.id.textView_luggagelock;
                                                TextView textView2 = (TextView) view.findViewById(R.id.textView_luggagelock);
                                                if (textView2 != null) {
                                                    i = R.id.textView_padlock;
                                                    TextView textView3 = (TextView) view.findViewById(R.id.textView_padlock);
                                                    if (textView3 != null) {
                                                        i = R.id.textView_primarypassword;
                                                        TextView textView4 = (TextView) view.findViewById(R.id.textView_primarypassword);
                                                        if (textView4 != null) {
                                                            return new FragmentRetypePasswordBinding(linearLayout, linearLayout, linearLayout2, linearLayout3, linearLayout4, button, checkBox, checkBox2, editText, imageView, imageView2, textView, textView2, textView3, textView4);
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
