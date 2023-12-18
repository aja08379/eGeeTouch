package com.egeetouch.egeetouch_manager.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import com.egeetouch.egeetouch_manager.R;
/* loaded from: classes.dex */
public final class FragmentChangePasscodeBinding implements ViewBinding {
    public final LinearLayout LinearLayoutAddPasscodeName;
    public final Button btCan;
    public final Button btCancel;
    public final Button btEight;
    public final Button btFive;
    public final Button btFour;
    public final Button btNine;
    public final Button btOne;
    public final Button btSeven;
    public final Button btSix;
    public final Button btThree;
    public final Button btTwo;
    public final Button btZero;
    public final Button btnClear;
    public final Button btnClearAll;
    public final ImageButton btnDownArrow;
    public final ImageButton btnLeftArrow;
    public final ImageButton btnRightArrow;
    public final Button btnSubmitPadPass;
    public final ImageButton btnUpArrow;
    public final EditText editTextPasscodeName;
    public final ImageView imageSwitch;
    public final ConstraintLayout linearLayout4;
    public final LinearLayout linearLayoutOverlay;
    public final LinearLayout llEditPasscode;
    public final GridLayout passGrid;
    public final ImageView passcode1;
    public final ImageView passcode2;
    public final ImageView passcode3;
    public final ImageView passcode4;
    public final ImageView passcode5;
    public final ImageView passcode6;
    private final RelativeLayout rootView;
    public final TextView textPadPass;
    public final TextView textViewAddNameTitle;
    public final TextView textViewCancelName;
    public final TextView textViewPasscodeName;
    public final TextView textViewSubmitName;
    public final TextView tvError;
    public final TextView txCurrentPass;
    public final TextView txOldPass;

    private FragmentChangePasscodeBinding(RelativeLayout relativeLayout, LinearLayout linearLayout, Button button, Button button2, Button button3, Button button4, Button button5, Button button6, Button button7, Button button8, Button button9, Button button10, Button button11, Button button12, Button button13, Button button14, ImageButton imageButton, ImageButton imageButton2, ImageButton imageButton3, Button button15, ImageButton imageButton4, EditText editText, ImageView imageView, ConstraintLayout constraintLayout, LinearLayout linearLayout2, LinearLayout linearLayout3, GridLayout gridLayout, ImageView imageView2, ImageView imageView3, ImageView imageView4, ImageView imageView5, ImageView imageView6, ImageView imageView7, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8) {
        this.rootView = relativeLayout;
        this.LinearLayoutAddPasscodeName = linearLayout;
        this.btCan = button;
        this.btCancel = button2;
        this.btEight = button3;
        this.btFive = button4;
        this.btFour = button5;
        this.btNine = button6;
        this.btOne = button7;
        this.btSeven = button8;
        this.btSix = button9;
        this.btThree = button10;
        this.btTwo = button11;
        this.btZero = button12;
        this.btnClear = button13;
        this.btnClearAll = button14;
        this.btnDownArrow = imageButton;
        this.btnLeftArrow = imageButton2;
        this.btnRightArrow = imageButton3;
        this.btnSubmitPadPass = button15;
        this.btnUpArrow = imageButton4;
        this.editTextPasscodeName = editText;
        this.imageSwitch = imageView;
        this.linearLayout4 = constraintLayout;
        this.linearLayoutOverlay = linearLayout2;
        this.llEditPasscode = linearLayout3;
        this.passGrid = gridLayout;
        this.passcode1 = imageView2;
        this.passcode2 = imageView3;
        this.passcode3 = imageView4;
        this.passcode4 = imageView5;
        this.passcode5 = imageView6;
        this.passcode6 = imageView7;
        this.textPadPass = textView;
        this.textViewAddNameTitle = textView2;
        this.textViewCancelName = textView3;
        this.textViewPasscodeName = textView4;
        this.textViewSubmitName = textView5;
        this.tvError = textView6;
        this.txCurrentPass = textView7;
        this.txOldPass = textView8;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static FragmentChangePasscodeBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentChangePasscodeBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fragment_change_passcode, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static FragmentChangePasscodeBinding bind(View view) {
        int i = R.id.LinearLayout_add_passcodeName;
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.LinearLayout_add_passcodeName);
        if (linearLayout != null) {
            i = R.id.bt_can;
            Button button = (Button) view.findViewById(R.id.bt_can);
            if (button != null) {
                i = R.id.bt_cancel;
                Button button2 = (Button) view.findViewById(R.id.bt_cancel);
                if (button2 != null) {
                    i = R.id.bt_eight;
                    Button button3 = (Button) view.findViewById(R.id.bt_eight);
                    if (button3 != null) {
                        i = R.id.bt_five;
                        Button button4 = (Button) view.findViewById(R.id.bt_five);
                        if (button4 != null) {
                            i = R.id.bt_four;
                            Button button5 = (Button) view.findViewById(R.id.bt_four);
                            if (button5 != null) {
                                i = R.id.bt_nine;
                                Button button6 = (Button) view.findViewById(R.id.bt_nine);
                                if (button6 != null) {
                                    i = R.id.bt_one;
                                    Button button7 = (Button) view.findViewById(R.id.bt_one);
                                    if (button7 != null) {
                                        i = R.id.bt_seven;
                                        Button button8 = (Button) view.findViewById(R.id.bt_seven);
                                        if (button8 != null) {
                                            i = R.id.bt_six;
                                            Button button9 = (Button) view.findViewById(R.id.bt_six);
                                            if (button9 != null) {
                                                i = R.id.bt_three;
                                                Button button10 = (Button) view.findViewById(R.id.bt_three);
                                                if (button10 != null) {
                                                    i = R.id.bt_two;
                                                    Button button11 = (Button) view.findViewById(R.id.bt_two);
                                                    if (button11 != null) {
                                                        i = R.id.bt_zero;
                                                        Button button12 = (Button) view.findViewById(R.id.bt_zero);
                                                        if (button12 != null) {
                                                            i = R.id.btn_clear;
                                                            Button button13 = (Button) view.findViewById(R.id.btn_clear);
                                                            if (button13 != null) {
                                                                i = R.id.btn_clear_all;
                                                                Button button14 = (Button) view.findViewById(R.id.btn_clear_all);
                                                                if (button14 != null) {
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
                                                                                Button button15 = (Button) view.findViewById(R.id.btn_submit_pad_pass);
                                                                                if (button15 != null) {
                                                                                    i = R.id.btn_up_arrow;
                                                                                    ImageButton imageButton4 = (ImageButton) view.findViewById(R.id.btn_up_arrow);
                                                                                    if (imageButton4 != null) {
                                                                                        i = R.id.editText_passcode_name;
                                                                                        EditText editText = (EditText) view.findViewById(R.id.editText_passcode_name);
                                                                                        if (editText != null) {
                                                                                            i = R.id.image_switch;
                                                                                            ImageView imageView = (ImageView) view.findViewById(R.id.image_switch);
                                                                                            if (imageView != null) {
                                                                                                i = R.id.linearLayout4;
                                                                                                ConstraintLayout constraintLayout = (ConstraintLayout) view.findViewById(R.id.linearLayout4);
                                                                                                if (constraintLayout != null) {
                                                                                                    i = R.id.linearLayout_overlay;
                                                                                                    LinearLayout linearLayout2 = (LinearLayout) view.findViewById(R.id.linearLayout_overlay);
                                                                                                    if (linearLayout2 != null) {
                                                                                                        i = R.id.ll_editPasscode;
                                                                                                        LinearLayout linearLayout3 = (LinearLayout) view.findViewById(R.id.ll_editPasscode);
                                                                                                        if (linearLayout3 != null) {
                                                                                                            i = R.id.pass_grid;
                                                                                                            GridLayout gridLayout = (GridLayout) view.findViewById(R.id.pass_grid);
                                                                                                            if (gridLayout != null) {
                                                                                                                i = R.id.passcode1;
                                                                                                                ImageView imageView2 = (ImageView) view.findViewById(R.id.passcode1);
                                                                                                                if (imageView2 != null) {
                                                                                                                    i = R.id.passcode2;
                                                                                                                    ImageView imageView3 = (ImageView) view.findViewById(R.id.passcode2);
                                                                                                                    if (imageView3 != null) {
                                                                                                                        i = R.id.passcode3;
                                                                                                                        ImageView imageView4 = (ImageView) view.findViewById(R.id.passcode3);
                                                                                                                        if (imageView4 != null) {
                                                                                                                            i = R.id.passcode4;
                                                                                                                            ImageView imageView5 = (ImageView) view.findViewById(R.id.passcode4);
                                                                                                                            if (imageView5 != null) {
                                                                                                                                i = R.id.passcode5;
                                                                                                                                ImageView imageView6 = (ImageView) view.findViewById(R.id.passcode5);
                                                                                                                                if (imageView6 != null) {
                                                                                                                                    i = R.id.passcode6;
                                                                                                                                    ImageView imageView7 = (ImageView) view.findViewById(R.id.passcode6);
                                                                                                                                    if (imageView7 != null) {
                                                                                                                                        i = R.id.text_pad_pass;
                                                                                                                                        TextView textView = (TextView) view.findViewById(R.id.text_pad_pass);
                                                                                                                                        if (textView != null) {
                                                                                                                                            i = R.id.textView_add_nameTitle;
                                                                                                                                            TextView textView2 = (TextView) view.findViewById(R.id.textView_add_nameTitle);
                                                                                                                                            if (textView2 != null) {
                                                                                                                                                i = R.id.textView_cancel_name;
                                                                                                                                                TextView textView3 = (TextView) view.findViewById(R.id.textView_cancel_name);
                                                                                                                                                if (textView3 != null) {
                                                                                                                                                    i = R.id.textView_passcode_name;
                                                                                                                                                    TextView textView4 = (TextView) view.findViewById(R.id.textView_passcode_name);
                                                                                                                                                    if (textView4 != null) {
                                                                                                                                                        i = R.id.textView_submitName;
                                                                                                                                                        TextView textView5 = (TextView) view.findViewById(R.id.textView_submitName);
                                                                                                                                                        if (textView5 != null) {
                                                                                                                                                            i = R.id.tv_error;
                                                                                                                                                            TextView textView6 = (TextView) view.findViewById(R.id.tv_error);
                                                                                                                                                            if (textView6 != null) {
                                                                                                                                                                i = R.id.tx_current_pass;
                                                                                                                                                                TextView textView7 = (TextView) view.findViewById(R.id.tx_current_pass);
                                                                                                                                                                if (textView7 != null) {
                                                                                                                                                                    i = R.id.tx_old_pass;
                                                                                                                                                                    TextView textView8 = (TextView) view.findViewById(R.id.tx_old_pass);
                                                                                                                                                                    if (textView8 != null) {
                                                                                                                                                                        return new FragmentChangePasscodeBinding((RelativeLayout) view, linearLayout, button, button2, button3, button4, button5, button6, button7, button8, button9, button10, button11, button12, button13, button14, imageButton, imageButton2, imageButton3, button15, imageButton4, editText, imageView, constraintLayout, linearLayout2, linearLayout3, gridLayout, imageView2, imageView3, imageView4, imageView5, imageView6, imageView7, textView, textView2, textView3, textView4, textView5, textView6, textView7, textView8);
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
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
