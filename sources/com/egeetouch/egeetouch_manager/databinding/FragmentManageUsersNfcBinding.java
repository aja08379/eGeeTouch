package com.egeetouch.egeetouch_manager.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import com.egeetouch.egeetouch_manager.R;
/* loaded from: classes.dex */
public final class FragmentManageUsersNfcBinding implements ViewBinding {
    public final LinearLayout LinearLayoutAdduser;
    public final LinearLayout LinearLayoutUpdateUserlist;
    public final LinearLayout LinearLayoutUser;
    public final Button buttonRetrieveTaglist;
    public final Button buttonUpdateUserslist;
    public final ImageView imageViewAdduser;
    public final ListView listviewUserlist;
    private final LinearLayout rootView;
    public final TextView textViewUserlist;

    private FragmentManageUsersNfcBinding(LinearLayout linearLayout, LinearLayout linearLayout2, LinearLayout linearLayout3, LinearLayout linearLayout4, Button button, Button button2, ImageView imageView, ListView listView, TextView textView) {
        this.rootView = linearLayout;
        this.LinearLayoutAdduser = linearLayout2;
        this.LinearLayoutUpdateUserlist = linearLayout3;
        this.LinearLayoutUser = linearLayout4;
        this.buttonRetrieveTaglist = button;
        this.buttonUpdateUserslist = button2;
        this.imageViewAdduser = imageView;
        this.listviewUserlist = listView;
        this.textViewUserlist = textView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static FragmentManageUsersNfcBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentManageUsersNfcBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fragment_manage_users_nfc, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static FragmentManageUsersNfcBinding bind(View view) {
        int i = R.id.LinearLayout_adduser;
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.LinearLayout_adduser);
        if (linearLayout != null) {
            i = R.id.LinearLayout_updateUserlist;
            LinearLayout linearLayout2 = (LinearLayout) view.findViewById(R.id.LinearLayout_updateUserlist);
            if (linearLayout2 != null) {
                i = R.id.LinearLayout_user;
                LinearLayout linearLayout3 = (LinearLayout) view.findViewById(R.id.LinearLayout_user);
                if (linearLayout3 != null) {
                    i = R.id.button_retrieveTaglist;
                    Button button = (Button) view.findViewById(R.id.button_retrieveTaglist);
                    if (button != null) {
                        i = R.id.button_updateUserslist;
                        Button button2 = (Button) view.findViewById(R.id.button_updateUserslist);
                        if (button2 != null) {
                            i = R.id.imageView_adduser;
                            ImageView imageView = (ImageView) view.findViewById(R.id.imageView_adduser);
                            if (imageView != null) {
                                i = R.id.listview_userlist;
                                ListView listView = (ListView) view.findViewById(R.id.listview_userlist);
                                if (listView != null) {
                                    i = R.id.textView_userlist;
                                    TextView textView = (TextView) view.findViewById(R.id.textView_userlist);
                                    if (textView != null) {
                                        return new FragmentManageUsersNfcBinding((LinearLayout) view, linearLayout, linearLayout2, linearLayout3, button, button2, imageView, listView, textView);
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
