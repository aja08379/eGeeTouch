package com.egeetouch.egeetouch_manager.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import com.egeetouch.egeetouch_manager.R;
/* loaded from: classes.dex */
public final class TagidAuthenticationBinding implements ViewBinding {
    public final Button btnCancel;
    public final TextView popUpTitle;
    private final RelativeLayout rootView;
    public final Button sentMailgun;
    public final EditText tagid;

    private TagidAuthenticationBinding(RelativeLayout relativeLayout, Button button, TextView textView, Button button2, EditText editText) {
        this.rootView = relativeLayout;
        this.btnCancel = button;
        this.popUpTitle = textView;
        this.sentMailgun = button2;
        this.tagid = editText;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static TagidAuthenticationBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static TagidAuthenticationBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.tagid_authentication, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static TagidAuthenticationBinding bind(View view) {
        int i = R.id.btn_cancel;
        Button button = (Button) view.findViewById(R.id.btn_cancel);
        if (button != null) {
            i = R.id.pop_up_title;
            TextView textView = (TextView) view.findViewById(R.id.pop_up_title);
            if (textView != null) {
                i = R.id.sent_mailgun;
                Button button2 = (Button) view.findViewById(R.id.sent_mailgun);
                if (button2 != null) {
                    i = R.id.tagid;
                    EditText editText = (EditText) view.findViewById(R.id.tagid);
                    if (editText != null) {
                        return new TagidAuthenticationBinding((RelativeLayout) view, button, textView, button2, editText);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
