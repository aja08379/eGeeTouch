package com.egeetouch.egeetouch_manager.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import com.egeetouch.egeetouch_manager.R;
/* loaded from: classes.dex */
public final class FragmentChooseType2NfcBinding implements ViewBinding {
    public final LinearLayout LinearLayout01;
    public final LinearLayout LinearLayout02;
    public final LinearLayout LinearLayout1;
    public final LinearLayout LinearLayoutStep4;
    public final TextView TextView01;
    public final TextView TextView02;
    public final Button buttonSavePair;
    public final EditText editTextLockname2;
    public final EditText editTextPrimarypassword2;
    public final ImageView imageView2;
    public final ImageView imageViewPowerOn;
    private final LinearLayout rootView;
    public final TextView textViewAddlockStep4;

    private FragmentChooseType2NfcBinding(LinearLayout linearLayout, LinearLayout linearLayout2, LinearLayout linearLayout3, LinearLayout linearLayout4, LinearLayout linearLayout5, TextView textView, TextView textView2, Button button, EditText editText, EditText editText2, ImageView imageView, ImageView imageView2, TextView textView3) {
        this.rootView = linearLayout;
        this.LinearLayout01 = linearLayout2;
        this.LinearLayout02 = linearLayout3;
        this.LinearLayout1 = linearLayout4;
        this.LinearLayoutStep4 = linearLayout5;
        this.TextView01 = textView;
        this.TextView02 = textView2;
        this.buttonSavePair = button;
        this.editTextLockname2 = editText;
        this.editTextPrimarypassword2 = editText2;
        this.imageView2 = imageView;
        this.imageViewPowerOn = imageView2;
        this.textViewAddlockStep4 = textView3;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static FragmentChooseType2NfcBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentChooseType2NfcBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fragment_choose_type2_nfc, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static FragmentChooseType2NfcBinding bind(View view) {
        int i = R.id.LinearLayout01;
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.LinearLayout01);
        if (linearLayout != null) {
            i = R.id.LinearLayout02;
            LinearLayout linearLayout2 = (LinearLayout) view.findViewById(R.id.LinearLayout02);
            if (linearLayout2 != null) {
                LinearLayout linearLayout3 = (LinearLayout) view;
                i = R.id.LinearLayout_step4;
                LinearLayout linearLayout4 = (LinearLayout) view.findViewById(R.id.LinearLayout_step4);
                if (linearLayout4 != null) {
                    i = R.id.TextView01;
                    TextView textView = (TextView) view.findViewById(R.id.TextView01);
                    if (textView != null) {
                        i = R.id.TextView02;
                        TextView textView2 = (TextView) view.findViewById(R.id.TextView02);
                        if (textView2 != null) {
                            i = R.id.button_save_pair;
                            Button button = (Button) view.findViewById(R.id.button_save_pair);
                            if (button != null) {
                                i = R.id.editText_lockname2;
                                EditText editText = (EditText) view.findViewById(R.id.editText_lockname2);
                                if (editText != null) {
                                    i = R.id.editText_primarypassword2;
                                    EditText editText2 = (EditText) view.findViewById(R.id.editText_primarypassword2);
                                    if (editText2 != null) {
                                        i = R.id.imageView2;
                                        ImageView imageView = (ImageView) view.findViewById(R.id.imageView2);
                                        if (imageView != null) {
                                            i = R.id.imageView_power_on;
                                            ImageView imageView2 = (ImageView) view.findViewById(R.id.imageView_power_on);
                                            if (imageView2 != null) {
                                                i = R.id.textView_addlock_step4;
                                                TextView textView3 = (TextView) view.findViewById(R.id.textView_addlock_step4);
                                                if (textView3 != null) {
                                                    return new FragmentChooseType2NfcBinding(linearLayout3, linearLayout, linearLayout2, linearLayout3, linearLayout4, textView, textView2, button, editText, editText2, imageView, imageView2, textView3);
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
