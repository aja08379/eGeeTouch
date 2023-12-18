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
public final class FragmentManageTagsNfcBinding implements ViewBinding {
    public final LinearLayout LinearLayoutAdduser;
    public final LinearLayout LinearLayoutTag;
    public final LinearLayout LinearLayoutUpdateTaglist;
    public final Button buttonRetrieveTaglist;
    public final Button buttonUpdateTagslist;
    public final ImageView imageViewAddtag;
    public final ListView listviewTaglist;
    private final LinearLayout rootView;
    public final TextView textViewUserlist;

    private FragmentManageTagsNfcBinding(LinearLayout linearLayout, LinearLayout linearLayout2, LinearLayout linearLayout3, LinearLayout linearLayout4, Button button, Button button2, ImageView imageView, ListView listView, TextView textView) {
        this.rootView = linearLayout;
        this.LinearLayoutAdduser = linearLayout2;
        this.LinearLayoutTag = linearLayout3;
        this.LinearLayoutUpdateTaglist = linearLayout4;
        this.buttonRetrieveTaglist = button;
        this.buttonUpdateTagslist = button2;
        this.imageViewAddtag = imageView;
        this.listviewTaglist = listView;
        this.textViewUserlist = textView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static FragmentManageTagsNfcBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentManageTagsNfcBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fragment_manage_tags_nfc, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static FragmentManageTagsNfcBinding bind(View view) {
        int i = R.id.LinearLayout_adduser;
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.LinearLayout_adduser);
        if (linearLayout != null) {
            i = R.id.LinearLayout_tag;
            LinearLayout linearLayout2 = (LinearLayout) view.findViewById(R.id.LinearLayout_tag);
            if (linearLayout2 != null) {
                i = R.id.LinearLayout_updateTaglist;
                LinearLayout linearLayout3 = (LinearLayout) view.findViewById(R.id.LinearLayout_updateTaglist);
                if (linearLayout3 != null) {
                    i = R.id.button_retrieveTaglist;
                    Button button = (Button) view.findViewById(R.id.button_retrieveTaglist);
                    if (button != null) {
                        i = R.id.button_updateTagslist;
                        Button button2 = (Button) view.findViewById(R.id.button_updateTagslist);
                        if (button2 != null) {
                            i = R.id.imageView_addtag;
                            ImageView imageView = (ImageView) view.findViewById(R.id.imageView_addtag);
                            if (imageView != null) {
                                i = R.id.listview_taglist;
                                ListView listView = (ListView) view.findViewById(R.id.listview_taglist);
                                if (listView != null) {
                                    i = R.id.textView_userlist;
                                    TextView textView = (TextView) view.findViewById(R.id.textView_userlist);
                                    if (textView != null) {
                                        return new FragmentManageTagsNfcBinding((LinearLayout) view, linearLayout, linearLayout2, linearLayout3, button, button2, imageView, listView, textView);
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
