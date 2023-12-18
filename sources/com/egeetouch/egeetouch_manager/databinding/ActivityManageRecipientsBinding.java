package com.egeetouch.egeetouch_manager.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import com.egeetouch.egeetouch_manager.R;
/* loaded from: classes.dex */
public final class ActivityManageRecipientsBinding implements ViewBinding {
    public final ConstraintLayout LinearLayoutAddUser;
    public final LinearLayout addUser1;
    public final LinearLayout addUser2;
    public final LinearLayout addUser3;
    public final LinearLayout addUser4;
    public final LinearLayout addUser5;
    public final Button btnLess;
    public final Button btnMore;
    public final EditText editTextUserEmail;
    public final EditText editTextUserEmail2;
    public final EditText editTextUserEmail3;
    public final EditText editTextUserEmail4;
    public final EditText editTextUserEmail5;
    public final EditText editTextUserName;
    public final EditText editTextUserName2;
    public final EditText editTextUserName3;
    public final EditText editTextUserName4;
    public final EditText editTextUserName5;
    public final ImageView imageViewAddUser;
    public final LinearLayout linearLayout2;
    public final ListView listviewUserlist;
    private final LinearLayout rootView;
    public final ScrollView scrollView2;
    public final TextView textView20;
    public final TextView textView21;
    public final TextView textView22;
    public final TextView textView23;
    public final TextView textView24;
    public final TextView textViewAddUser;
    public final TextView textViewCancelAddUser;
    public final TextView textViewUserName;
    public final TextView textViewUserName2;
    public final TextView textViewUserName3;
    public final TextView textViewUserName4;
    public final TextView textViewUserName5;
    public final TextView textViewUserPassword;
    public final TextView textViewUserPassword2;
    public final TextView textViewUserPassword3;
    public final TextView textViewUserPassword4;
    public final TextView textViewUserPassword5;

    private ActivityManageRecipientsBinding(LinearLayout linearLayout, ConstraintLayout constraintLayout, LinearLayout linearLayout2, LinearLayout linearLayout3, LinearLayout linearLayout4, LinearLayout linearLayout5, LinearLayout linearLayout6, Button button, Button button2, EditText editText, EditText editText2, EditText editText3, EditText editText4, EditText editText5, EditText editText6, EditText editText7, EditText editText8, EditText editText9, EditText editText10, ImageView imageView, LinearLayout linearLayout7, ListView listView, ScrollView scrollView, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9, TextView textView10, TextView textView11, TextView textView12, TextView textView13, TextView textView14, TextView textView15, TextView textView16, TextView textView17) {
        this.rootView = linearLayout;
        this.LinearLayoutAddUser = constraintLayout;
        this.addUser1 = linearLayout2;
        this.addUser2 = linearLayout3;
        this.addUser3 = linearLayout4;
        this.addUser4 = linearLayout5;
        this.addUser5 = linearLayout6;
        this.btnLess = button;
        this.btnMore = button2;
        this.editTextUserEmail = editText;
        this.editTextUserEmail2 = editText2;
        this.editTextUserEmail3 = editText3;
        this.editTextUserEmail4 = editText4;
        this.editTextUserEmail5 = editText5;
        this.editTextUserName = editText6;
        this.editTextUserName2 = editText7;
        this.editTextUserName3 = editText8;
        this.editTextUserName4 = editText9;
        this.editTextUserName5 = editText10;
        this.imageViewAddUser = imageView;
        this.linearLayout2 = linearLayout7;
        this.listviewUserlist = listView;
        this.scrollView2 = scrollView;
        this.textView20 = textView;
        this.textView21 = textView2;
        this.textView22 = textView3;
        this.textView23 = textView4;
        this.textView24 = textView5;
        this.textViewAddUser = textView6;
        this.textViewCancelAddUser = textView7;
        this.textViewUserName = textView8;
        this.textViewUserName2 = textView9;
        this.textViewUserName3 = textView10;
        this.textViewUserName4 = textView11;
        this.textViewUserName5 = textView12;
        this.textViewUserPassword = textView13;
        this.textViewUserPassword2 = textView14;
        this.textViewUserPassword3 = textView15;
        this.textViewUserPassword4 = textView16;
        this.textViewUserPassword5 = textView17;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ActivityManageRecipientsBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ActivityManageRecipientsBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_manage_recipients, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivityManageRecipientsBinding bind(View view) {
        int i = R.id.LinearLayout_add_user;
        ConstraintLayout constraintLayout = (ConstraintLayout) view.findViewById(R.id.LinearLayout_add_user);
        if (constraintLayout != null) {
            i = R.id.add_user1;
            LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.add_user1);
            if (linearLayout != null) {
                i = R.id.add_user2;
                LinearLayout linearLayout2 = (LinearLayout) view.findViewById(R.id.add_user2);
                if (linearLayout2 != null) {
                    i = R.id.add_user3;
                    LinearLayout linearLayout3 = (LinearLayout) view.findViewById(R.id.add_user3);
                    if (linearLayout3 != null) {
                        i = R.id.add_user4;
                        LinearLayout linearLayout4 = (LinearLayout) view.findViewById(R.id.add_user4);
                        if (linearLayout4 != null) {
                            i = R.id.add_user5;
                            LinearLayout linearLayout5 = (LinearLayout) view.findViewById(R.id.add_user5);
                            if (linearLayout5 != null) {
                                i = R.id.btn_less;
                                Button button = (Button) view.findViewById(R.id.btn_less);
                                if (button != null) {
                                    i = R.id.btn_more;
                                    Button button2 = (Button) view.findViewById(R.id.btn_more);
                                    if (button2 != null) {
                                        i = R.id.editText_user_email;
                                        EditText editText = (EditText) view.findViewById(R.id.editText_user_email);
                                        if (editText != null) {
                                            i = R.id.editText_user_email2;
                                            EditText editText2 = (EditText) view.findViewById(R.id.editText_user_email2);
                                            if (editText2 != null) {
                                                i = R.id.editText_user_email3;
                                                EditText editText3 = (EditText) view.findViewById(R.id.editText_user_email3);
                                                if (editText3 != null) {
                                                    i = R.id.editText_user_email4;
                                                    EditText editText4 = (EditText) view.findViewById(R.id.editText_user_email4);
                                                    if (editText4 != null) {
                                                        i = R.id.editText_user_email5;
                                                        EditText editText5 = (EditText) view.findViewById(R.id.editText_user_email5);
                                                        if (editText5 != null) {
                                                            i = R.id.editText_user_name;
                                                            EditText editText6 = (EditText) view.findViewById(R.id.editText_user_name);
                                                            if (editText6 != null) {
                                                                i = R.id.editText_user_name2;
                                                                EditText editText7 = (EditText) view.findViewById(R.id.editText_user_name2);
                                                                if (editText7 != null) {
                                                                    i = R.id.editText_user_name3;
                                                                    EditText editText8 = (EditText) view.findViewById(R.id.editText_user_name3);
                                                                    if (editText8 != null) {
                                                                        i = R.id.editText_user_name4;
                                                                        EditText editText9 = (EditText) view.findViewById(R.id.editText_user_name4);
                                                                        if (editText9 != null) {
                                                                            i = R.id.editText_user_name5;
                                                                            EditText editText10 = (EditText) view.findViewById(R.id.editText_user_name5);
                                                                            if (editText10 != null) {
                                                                                i = R.id.imageView_add_user;
                                                                                ImageView imageView = (ImageView) view.findViewById(R.id.imageView_add_user);
                                                                                if (imageView != null) {
                                                                                    i = R.id.linearLayout2;
                                                                                    LinearLayout linearLayout6 = (LinearLayout) view.findViewById(R.id.linearLayout2);
                                                                                    if (linearLayout6 != null) {
                                                                                        i = R.id.listview_userlist;
                                                                                        ListView listView = (ListView) view.findViewById(R.id.listview_userlist);
                                                                                        if (listView != null) {
                                                                                            i = R.id.scrollView2;
                                                                                            ScrollView scrollView = (ScrollView) view.findViewById(R.id.scrollView2);
                                                                                            if (scrollView != null) {
                                                                                                i = R.id.textView20;
                                                                                                TextView textView = (TextView) view.findViewById(R.id.textView20);
                                                                                                if (textView != null) {
                                                                                                    i = R.id.textView21;
                                                                                                    TextView textView2 = (TextView) view.findViewById(R.id.textView21);
                                                                                                    if (textView2 != null) {
                                                                                                        i = R.id.textView22;
                                                                                                        TextView textView3 = (TextView) view.findViewById(R.id.textView22);
                                                                                                        if (textView3 != null) {
                                                                                                            i = R.id.textView23;
                                                                                                            TextView textView4 = (TextView) view.findViewById(R.id.textView23);
                                                                                                            if (textView4 != null) {
                                                                                                                i = R.id.textView24;
                                                                                                                TextView textView5 = (TextView) view.findViewById(R.id.textView24);
                                                                                                                if (textView5 != null) {
                                                                                                                    i = R.id.textView_add_user;
                                                                                                                    TextView textView6 = (TextView) view.findViewById(R.id.textView_add_user);
                                                                                                                    if (textView6 != null) {
                                                                                                                        i = R.id.textView_cancel_add_user;
                                                                                                                        TextView textView7 = (TextView) view.findViewById(R.id.textView_cancel_add_user);
                                                                                                                        if (textView7 != null) {
                                                                                                                            i = R.id.textView_user_name;
                                                                                                                            TextView textView8 = (TextView) view.findViewById(R.id.textView_user_name);
                                                                                                                            if (textView8 != null) {
                                                                                                                                i = R.id.textView_user_name2;
                                                                                                                                TextView textView9 = (TextView) view.findViewById(R.id.textView_user_name2);
                                                                                                                                if (textView9 != null) {
                                                                                                                                    i = R.id.textView_user_name3;
                                                                                                                                    TextView textView10 = (TextView) view.findViewById(R.id.textView_user_name3);
                                                                                                                                    if (textView10 != null) {
                                                                                                                                        i = R.id.textView_user_name4;
                                                                                                                                        TextView textView11 = (TextView) view.findViewById(R.id.textView_user_name4);
                                                                                                                                        if (textView11 != null) {
                                                                                                                                            i = R.id.textView_user_name5;
                                                                                                                                            TextView textView12 = (TextView) view.findViewById(R.id.textView_user_name5);
                                                                                                                                            if (textView12 != null) {
                                                                                                                                                i = R.id.textView_user_password;
                                                                                                                                                TextView textView13 = (TextView) view.findViewById(R.id.textView_user_password);
                                                                                                                                                if (textView13 != null) {
                                                                                                                                                    i = R.id.textView_user_password2;
                                                                                                                                                    TextView textView14 = (TextView) view.findViewById(R.id.textView_user_password2);
                                                                                                                                                    if (textView14 != null) {
                                                                                                                                                        i = R.id.textView_user_password3;
                                                                                                                                                        TextView textView15 = (TextView) view.findViewById(R.id.textView_user_password3);
                                                                                                                                                        if (textView15 != null) {
                                                                                                                                                            i = R.id.textView_user_password4;
                                                                                                                                                            TextView textView16 = (TextView) view.findViewById(R.id.textView_user_password4);
                                                                                                                                                            if (textView16 != null) {
                                                                                                                                                                i = R.id.textView_user_password5;
                                                                                                                                                                TextView textView17 = (TextView) view.findViewById(R.id.textView_user_password5);
                                                                                                                                                                if (textView17 != null) {
                                                                                                                                                                    return new ActivityManageRecipientsBinding((LinearLayout) view, constraintLayout, linearLayout, linearLayout2, linearLayout3, linearLayout4, linearLayout5, button, button2, editText, editText2, editText3, editText4, editText5, editText6, editText7, editText8, editText9, editText10, imageView, linearLayout6, listView, scrollView, textView, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9, textView10, textView11, textView12, textView13, textView14, textView15, textView16, textView17);
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
