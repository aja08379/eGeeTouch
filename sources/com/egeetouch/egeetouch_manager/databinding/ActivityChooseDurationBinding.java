package com.egeetouch.egeetouch_manager.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import androidx.viewbinding.ViewBinding;
import com.egeetouch.egeetouch_manager.R;
/* loaded from: classes.dex */
public final class ActivityChooseDurationBinding implements ViewBinding {
    public final Button btnStartDate;
    public final Button btnTime;
    public final EditText inTime;
    private final RelativeLayout rootView;
    public final EditText startDateTextfield;

    private ActivityChooseDurationBinding(RelativeLayout relativeLayout, Button button, Button button2, EditText editText, EditText editText2) {
        this.rootView = relativeLayout;
        this.btnStartDate = button;
        this.btnTime = button2;
        this.inTime = editText;
        this.startDateTextfield = editText2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static ActivityChooseDurationBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ActivityChooseDurationBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_choose_duration, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivityChooseDurationBinding bind(View view) {
        int i = R.id.btn_start_date;
        Button button = (Button) view.findViewById(R.id.btn_start_date);
        if (button != null) {
            i = R.id.btn_time;
            Button button2 = (Button) view.findViewById(R.id.btn_time);
            if (button2 != null) {
                i = R.id.in_time;
                EditText editText = (EditText) view.findViewById(R.id.in_time);
                if (editText != null) {
                    i = R.id.start_date_textfield;
                    EditText editText2 = (EditText) view.findViewById(R.id.start_date_textfield);
                    if (editText2 != null) {
                        return new ActivityChooseDurationBinding((RelativeLayout) view, button, button2, editText, editText2);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
