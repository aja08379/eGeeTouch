package com.egeetouch.egeetouch_manager.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import com.egeetouch.egeetouch_manager.R;
/* loaded from: classes.dex */
public final class ResetpasswordfailedBinding implements ViewBinding {
    public final Button buttonCancell;
    public final Button buttonContactUs;
    public final TextView popUpContent;
    public final TextView popUpTitle;
    private final RelativeLayout rootView;

    private ResetpasswordfailedBinding(RelativeLayout relativeLayout, Button button, Button button2, TextView textView, TextView textView2) {
        this.rootView = relativeLayout;
        this.buttonCancell = button;
        this.buttonContactUs = button2;
        this.popUpContent = textView;
        this.popUpTitle = textView2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static ResetpasswordfailedBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ResetpasswordfailedBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.resetpasswordfailed, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ResetpasswordfailedBinding bind(View view) {
        int i = R.id.button_cancell;
        Button button = (Button) view.findViewById(R.id.button_cancell);
        if (button != null) {
            i = R.id.button_contactUs;
            Button button2 = (Button) view.findViewById(R.id.button_contactUs);
            if (button2 != null) {
                i = R.id.pop_up_content;
                TextView textView = (TextView) view.findViewById(R.id.pop_up_content);
                if (textView != null) {
                    i = R.id.pop_up_title;
                    TextView textView2 = (TextView) view.findViewById(R.id.pop_up_title);
                    if (textView2 != null) {
                        return new ResetpasswordfailedBinding((RelativeLayout) view, button, button2, textView, textView2);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
