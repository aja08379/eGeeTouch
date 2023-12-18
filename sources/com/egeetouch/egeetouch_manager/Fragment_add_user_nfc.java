package com.egeetouch.egeetouch_manager;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
/* loaded from: classes.dex */
public class Fragment_add_user_nfc extends Fragment {
    private static boolean disconnected_dialog_show = false;
    public static ListView listview;
    private Handler manage_add_user_handler = new Handler();
    View rootView;

    public static Fragment_add_user_nfc newInstance() {
        return new Fragment_add_user_nfc();
    }

    @Override // android.app.Fragment
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.add_user);
    }

    @Override // android.app.Fragment
    public void onPause() {
        super.onPause();
    }

    @Override // android.app.Fragment
    public void onStop() {
        super.onStop();
    }

    @Override // android.app.Fragment
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.nfc, menu);
    }

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        new ArrayList();
        View inflate = layoutInflater.inflate(R.layout.fragment_add_user_nfc, viewGroup, false);
        this.rootView = inflate;
        EditText editText = (EditText) inflate.findViewById(R.id.editText_newusername);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        ((EditText) this.rootView.findViewById(R.id.editText_newusername)).setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_add_user_nfc.1
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    return;
                }
                Fragment_add_user_nfc.this.hideKeyboard(view);
            }
        });
        ((EditText) this.rootView.findViewById(R.id.editText_userpassword)).setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_add_user_nfc.2
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    return;
                }
                Fragment_add_user_nfc.this.hideKeyboard(view);
            }
        });
        ((EditText) this.rootView.findViewById(R.id.editText_userpassword2)).setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.egeetouch.egeetouch_manager.Fragment_add_user_nfc.3
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    return;
                }
                Fragment_add_user_nfc.this.hideKeyboard(view);
            }
        });
        return this.rootView;
    }

    public void hideKeyboard(View view) {
        ((InputMethodManager) getActivity().getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
