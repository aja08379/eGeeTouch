package com.egeetouch.egeetouch_manager;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
/* loaded from: classes.dex */
public class Fragment_retype_password extends Fragment {
    public static Fragment_retype_password newInstance() {
        return new Fragment_retype_password();
    }

    @Override // android.app.Fragment
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.re_type_password);
    }

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_retype_password, viewGroup, false);
        CheckBox checkBox = (CheckBox) inflate.findViewById(R.id.checkBox_GT2000);
        CheckBox checkBox2 = (CheckBox) inflate.findViewById(R.id.checkBox_GT3000);
        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.LinearLayout_padlock);
        LinearLayout linearLayout2 = (LinearLayout) inflate.findViewById(R.id.LinearLayout_luggagelock);
        if (BLEService.selected_lock_model.equals("GT2000")) {
            checkBox.setChecked(true);
            checkBox2.setChecked(false);
            linearLayout.setBackground(getResources().getDrawable(R.drawable.border_filled));
            linearLayout2.setBackground(getResources().getDrawable(R.drawable.border));
        } else if (BLEService.selected_lock_model.equals("GT3000")) {
            checkBox2.setChecked(true);
            checkBox.setChecked(false);
            linearLayout2.setBackground(getResources().getDrawable(R.drawable.border_filled));
            linearLayout.setBackground(getResources().getDrawable(R.drawable.border));
        }
        return inflate;
    }
}
