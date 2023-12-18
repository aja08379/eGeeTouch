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
public final class ContactUsBinding implements ViewBinding {
    public final Button btnCancell;
    public final TextView emailCustomerr;
    public final TextView lockseriall;
    public final TextView popUpTitle;
    private final RelativeLayout rootView;
    public final Button sentMailgunn;
    public final EditText tagidd;

    private ContactUsBinding(RelativeLayout relativeLayout, Button button, TextView textView, TextView textView2, TextView textView3, Button button2, EditText editText) {
        this.rootView = relativeLayout;
        this.btnCancell = button;
        this.emailCustomerr = textView;
        this.lockseriall = textView2;
        this.popUpTitle = textView3;
        this.sentMailgunn = button2;
        this.tagidd = editText;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static ContactUsBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ContactUsBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.contact_us, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ContactUsBinding bind(View view) {
        int i = R.id.btn_cancell;
        Button button = (Button) view.findViewById(R.id.btn_cancell);
        if (button != null) {
            i = R.id.email_customerr;
            TextView textView = (TextView) view.findViewById(R.id.email_customerr);
            if (textView != null) {
                i = R.id.lockseriall;
                TextView textView2 = (TextView) view.findViewById(R.id.lockseriall);
                if (textView2 != null) {
                    i = R.id.pop_up_title;
                    TextView textView3 = (TextView) view.findViewById(R.id.pop_up_title);
                    if (textView3 != null) {
                        i = R.id.sent_mailgunn;
                        Button button2 = (Button) view.findViewById(R.id.sent_mailgunn);
                        if (button2 != null) {
                            i = R.id.tagidd;
                            EditText editText = (EditText) view.findViewById(R.id.tagidd);
                            if (editText != null) {
                                return new ContactUsBinding((RelativeLayout) view, button, textView, textView2, textView3, button2, editText);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
