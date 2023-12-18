package com.egeetouch.egeetouch_manager.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import com.egeetouch.egeetouch_manager.R;
/* loaded from: classes.dex */
public final class FragmentManageUsersBinding implements ViewBinding {
    public final LinearLayout LinearLayoutAddUser;
    public final LinearLayout LinearLayoutIcons;
    public final LinearLayout LinearLayoutUpdateUserlist;
    public final Button buttonRetrieveTaglist;
    public final Button buttonUpdateUserslist;
    public final EditText editTextUserName;
    public final EditText editTextUserPassword;
    public final ImageView imageViewAddUser;
    public final ImageView imageViewAdduser;
    public final ImageView imageViewAudit;
    public final ImageView imageViewLocking;
    public final ImageView imageViewProximity;
    public final ImageView imageViewSetting;
    public final ImageView imageViewWatch;
    public final ListView listviewUserlist;
    private final LinearLayout rootView;
    public final TextView textViewAddUser;
    public final TextView textViewAddUserTitle;
    public final TextView textViewCancelAddUser;
    public final TextView textViewUserName;
    public final TextView textViewUserPassword;

    private FragmentManageUsersBinding(LinearLayout linearLayout, LinearLayout linearLayout2, LinearLayout linearLayout3, LinearLayout linearLayout4, Button button, Button button2, EditText editText, EditText editText2, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, ImageView imageView5, ImageView imageView6, ImageView imageView7, ListView listView, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5) {
        this.rootView = linearLayout;
        this.LinearLayoutAddUser = linearLayout2;
        this.LinearLayoutIcons = linearLayout3;
        this.LinearLayoutUpdateUserlist = linearLayout4;
        this.buttonRetrieveTaglist = button;
        this.buttonUpdateUserslist = button2;
        this.editTextUserName = editText;
        this.editTextUserPassword = editText2;
        this.imageViewAddUser = imageView;
        this.imageViewAdduser = imageView2;
        this.imageViewAudit = imageView3;
        this.imageViewLocking = imageView4;
        this.imageViewProximity = imageView5;
        this.imageViewSetting = imageView6;
        this.imageViewWatch = imageView7;
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

    public static FragmentManageUsersBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentManageUsersBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fragment_manage_users, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static FragmentManageUsersBinding bind(View view) {
        int i = R.id.LinearLayout_add_user;
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.LinearLayout_add_user);
        if (linearLayout != null) {
            i = R.id.LinearLayout_icons;
            LinearLayout linearLayout2 = (LinearLayout) view.findViewById(R.id.LinearLayout_icons);
            if (linearLayout2 != null) {
                i = R.id.LinearLayout_updateUserlist;
                LinearLayout linearLayout3 = (LinearLayout) view.findViewById(R.id.LinearLayout_updateUserlist);
                if (linearLayout3 != null) {
                    i = R.id.button_retrieveTaglist;
                    Button button = (Button) view.findViewById(R.id.button_retrieveTaglist);
                    if (button != null) {
                        i = R.id.button_updateUserslist;
                        Button button2 = (Button) view.findViewById(R.id.button_updateUserslist);
                        if (button2 != null) {
                            i = R.id.editText_user_name;
                            EditText editText = (EditText) view.findViewById(R.id.editText_user_name);
                            if (editText != null) {
                                i = R.id.editText_user_password;
                                EditText editText2 = (EditText) view.findViewById(R.id.editText_user_password);
                                if (editText2 != null) {
                                    i = R.id.imageView_add_user;
                                    ImageView imageView = (ImageView) view.findViewById(R.id.imageView_add_user);
                                    if (imageView != null) {
                                        i = R.id.imageView_adduser;
                                        ImageView imageView2 = (ImageView) view.findViewById(R.id.imageView_adduser);
                                        if (imageView2 != null) {
                                            i = R.id.imageView_audit;
                                            ImageView imageView3 = (ImageView) view.findViewById(R.id.imageView_audit);
                                            if (imageView3 != null) {
                                                i = R.id.imageView_locking;
                                                ImageView imageView4 = (ImageView) view.findViewById(R.id.imageView_locking);
                                                if (imageView4 != null) {
                                                    i = R.id.imageView_proximity;
                                                    ImageView imageView5 = (ImageView) view.findViewById(R.id.imageView_proximity);
                                                    if (imageView5 != null) {
                                                        i = R.id.imageView_setting;
                                                        ImageView imageView6 = (ImageView) view.findViewById(R.id.imageView_setting);
                                                        if (imageView6 != null) {
                                                            i = R.id.imageView_watch;
                                                            ImageView imageView7 = (ImageView) view.findViewById(R.id.imageView_watch);
                                                            if (imageView7 != null) {
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
                                                                                        return new FragmentManageUsersBinding((LinearLayout) view, linearLayout, linearLayout2, linearLayout3, button, button2, editText, editText2, imageView, imageView2, imageView3, imageView4, imageView5, imageView6, imageView7, listView, textView, textView2, textView3, textView4, textView5);
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
