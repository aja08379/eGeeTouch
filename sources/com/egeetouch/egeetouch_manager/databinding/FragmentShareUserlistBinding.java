package com.egeetouch.egeetouch_manager.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import com.egeetouch.egeetouch_manager.R;
/* loaded from: classes.dex */
public final class FragmentShareUserlistBinding implements ViewBinding {
    public final LinearLayout LinearLayoutAddUser;
    public final EditText editTextUserEmail;
    public final EditText editTextUserName;
    public final ImageView imageViewAddUser;
    public final ListView listviewUserlist;
    private final LinearLayout rootView;
    public final TextView textViewAddUser;
    public final TextView textViewAddUserTitle;
    public final TextView textViewCancelAddUser;
    public final TextView textViewUserName;
    public final TextView textViewUserPassword;

    private FragmentShareUserlistBinding(LinearLayout linearLayout, LinearLayout linearLayout2, EditText editText, EditText editText2, ImageView imageView, ListView listView, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5) {
        this.rootView = linearLayout;
        this.LinearLayoutAddUser = linearLayout2;
        this.editTextUserEmail = editText;
        this.editTextUserName = editText2;
        this.imageViewAddUser = imageView;
        this.listviewUserlist = listView;
        this.textViewAddUser = textView;
        this.textViewAddUserTitle = textView2;
        this.textViewCancelAddUser = textView3;
        this.textViewUserName = textView4;
        this.textViewUserPassword = textView5;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static FragmentShareUserlistBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentShareUserlistBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fragment_share_userlist, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static FragmentShareUserlistBinding bind(View view) {
        int i = R.id.LinearLayout_add_user;
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.LinearLayout_add_user);
        if (linearLayout != null) {
            i = R.id.editText_user_email;
            EditText editText = (EditText) view.findViewById(R.id.editText_user_email);
            if (editText != null) {
                i = R.id.editText_user_name;
                EditText editText2 = (EditText) view.findViewById(R.id.editText_user_name);
                if (editText2 != null) {
                    i = R.id.imageView_add_user;
                    ImageView imageView = (ImageView) view.findViewById(R.id.imageView_add_user);
                    if (imageView != null) {
                        i = R.id.listview_userlist;
                        ListView listView = (ListView) view.findViewById(R.id.listview_userlist);
                        if (listView != null) {
                            i = R.id.textView_add_user;
                            TextView textView = (TextView) view.findViewById(R.id.textView_add_user);
                            if (textView != null) {
                                i = R.id.textView_add_user_title;
                                TextView textView2 = (TextView) view.findViewById(R.id.textView_add_user_title);
                                if (textView2 != null) {
                                    i = R.id.textView_cancel_add_user;
                                    TextView textView3 = (TextView) view.findViewById(R.id.textView_cancel_add_user);
                                    if (textView3 != null) {
                                        i = R.id.textView_user_name;
                                        TextView textView4 = (TextView) view.findViewById(R.id.textView_user_name);
                                        if (textView4 != null) {
                                            i = R.id.textView_user_password;
                                            TextView textView5 = (TextView) view.findViewById(R.id.textView_user_password);
                                            if (textView5 != null) {
                                                return new FragmentShareUserlistBinding((LinearLayout) view, linearLayout, editText, editText2, imageView, listView, textView, textView2, textView3, textView4, textView5);
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
