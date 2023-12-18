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
public final class FragmentPairingBinding implements ViewBinding {
    public final Button ButtonRetypePassword;
    public final LinearLayout LinearLayout2;
    public final LinearLayout LinearLayoutEditNameButtons;
    public final LinearLayout LinearLayoutMoreFunction;
    public final LinearLayout LinearLayoutPairAgain;
    public final TextView TextViewUsername;
    public final Button buttonDisconnect;
    public final Button buttonMorefunctions;
    public final Button buttonNewLockNameCancel;
    public final Button buttonNewLockNameSave;
    public final Button buttonPairAgain;
    public final EditText editTextNewLockName;
    public final ImageView imageViewPairing;
    private final LinearLayout rootView;
    public final TextView textViewInstruction;
    public final TextView textViewPairingLockName;

    private FragmentPairingBinding(LinearLayout linearLayout, Button button, LinearLayout linearLayout2, LinearLayout linearLayout3, LinearLayout linearLayout4, LinearLayout linearLayout5, TextView textView, Button button2, Button button3, Button button4, Button button5, Button button6, EditText editText, ImageView imageView, TextView textView2, TextView textView3) {
        this.rootView = linearLayout;
        this.ButtonRetypePassword = button;
        this.LinearLayout2 = linearLayout2;
        this.LinearLayoutEditNameButtons = linearLayout3;
        this.LinearLayoutMoreFunction = linearLayout4;
        this.LinearLayoutPairAgain = linearLayout5;
        this.TextViewUsername = textView;
        this.buttonDisconnect = button2;
        this.buttonMorefunctions = button3;
        this.buttonNewLockNameCancel = button4;
        this.buttonNewLockNameSave = button5;
        this.buttonPairAgain = button6;
        this.editTextNewLockName = editText;
        this.imageViewPairing = imageView;
        this.textViewInstruction = textView2;
        this.textViewPairingLockName = textView3;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static FragmentPairingBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentPairingBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fragment_pairing, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static FragmentPairingBinding bind(View view) {
        int i = R.id.Button_retype_password;
        Button button = (Button) view.findViewById(R.id.Button_retype_password);
        if (button != null) {
            LinearLayout linearLayout = (LinearLayout) view;
            i = R.id.LinearLayout_edit_name_buttons;
            LinearLayout linearLayout2 = (LinearLayout) view.findViewById(R.id.LinearLayout_edit_name_buttons);
            if (linearLayout2 != null) {
                i = R.id.LinearLayout_more_function;
                LinearLayout linearLayout3 = (LinearLayout) view.findViewById(R.id.LinearLayout_more_function);
                if (linearLayout3 != null) {
                    i = R.id.LinearLayout_pair_again;
                    LinearLayout linearLayout4 = (LinearLayout) view.findViewById(R.id.LinearLayout_pair_again);
                    if (linearLayout4 != null) {
                        i = R.id.TextView_username;
                        TextView textView = (TextView) view.findViewById(R.id.TextView_username);
                        if (textView != null) {
                            i = R.id.button_disconnect;
                            Button button2 = (Button) view.findViewById(R.id.button_disconnect);
                            if (button2 != null) {
                                i = R.id.button_morefunctions;
                                Button button3 = (Button) view.findViewById(R.id.button_morefunctions);
                                if (button3 != null) {
                                    i = R.id.button_new_lock_name_cancel;
                                    Button button4 = (Button) view.findViewById(R.id.button_new_lock_name_cancel);
                                    if (button4 != null) {
                                        i = R.id.button_new_lock_name_save;
                                        Button button5 = (Button) view.findViewById(R.id.button_new_lock_name_save);
                                        if (button5 != null) {
                                            i = R.id.button_pair_again;
                                            Button button6 = (Button) view.findViewById(R.id.button_pair_again);
                                            if (button6 != null) {
                                                i = R.id.editText_new_lock_name;
                                                EditText editText = (EditText) view.findViewById(R.id.editText_new_lock_name);
                                                if (editText != null) {
                                                    i = R.id.imageView_pairing;
                                                    ImageView imageView = (ImageView) view.findViewById(R.id.imageView_pairing);
                                                    if (imageView != null) {
                                                        i = R.id.textView_instruction;
                                                        TextView textView2 = (TextView) view.findViewById(R.id.textView_instruction);
                                                        if (textView2 != null) {
                                                            i = R.id.textView_pairing_lock_name;
                                                            TextView textView3 = (TextView) view.findViewById(R.id.textView_pairing_lock_name);
                                                            if (textView3 != null) {
                                                                return new FragmentPairingBinding(linearLayout, button, linearLayout, linearLayout2, linearLayout3, linearLayout4, textView, button2, button3, button4, button5, button6, editText, imageView, textView2, textView3);
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
