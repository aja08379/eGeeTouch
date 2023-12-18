package com.egeetouch.egeetouch_manager.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import com.egeetouch.egeetouch_manager.R;
/* loaded from: classes.dex */
public final class FragmentAddUserNfcBinding implements ViewBinding {
    public final Button ButtonSaveNewuser;
    public final EditText editTextNewusername;
    public final EditText editTextUserpassword;
    public final EditText editTextUserpassword2;
    private final LinearLayout rootView;
    public final TextView textViewUsername;
    public final TextView textViewUserpassword;

    private FragmentAddUserNfcBinding(LinearLayout linearLayout, Button button, EditText editText, EditText editText2, EditText editText3, TextView textView, TextView textView2) {
        this.rootView = linearLayout;
        this.ButtonSaveNewuser = button;
        this.editTextNewusername = editText;
        this.editTextUserpassword = editText2;
        this.editTextUserpassword2 = editText3;
        this.textViewUsername = textView;
        this.textViewUserpassword = textView2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static FragmentAddUserNfcBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentAddUserNfcBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fragment_add_user_nfc, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static FragmentAddUserNfcBinding bind(View view) {
        int i = R.id.Button_save_newuser;
        Button button = (Button) view.findViewById(R.id.Button_save_newuser);
        if (button != null) {
            i = R.id.editText_newusername;
            EditText editText = (EditText) view.findViewById(R.id.editText_newusername);
            if (editText != null) {
                i = R.id.editText_userpassword;
                EditText editText2 = (EditText) view.findViewById(R.id.editText_userpassword);
                if (editText2 != null) {
                    i = R.id.editText_userpassword2;
                    EditText editText3 = (EditText) view.findViewById(R.id.editText_userpassword2);
                    if (editText3 != null) {
                        i = R.id.textView_username;
                        TextView textView = (TextView) view.findViewById(R.id.textView_username);
                        if (textView != null) {
                            i = R.id.textView_userpassword;
                            TextView textView2 = (TextView) view.findViewById(R.id.textView_userpassword);
                            if (textView2 != null) {
                                return new FragmentAddUserNfcBinding((LinearLayout) view, button, editText, editText2, editText3, textView, textView2);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
