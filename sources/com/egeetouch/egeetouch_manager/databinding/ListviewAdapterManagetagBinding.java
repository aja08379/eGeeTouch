package com.egeetouch.egeetouch_manager.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import com.egeetouch.egeetouch_manager.R;
/* loaded from: classes.dex */
public final class ListviewAdapterManagetagBinding implements ViewBinding {
    public final ImageView imageView1;
    private final LinearLayout rootView;
    public final TextView textViewTagId;
    public final TextView textViewTagIndex;
    public final TextView textViewTagIndex1;
    public final TextView textViewTagUid;
    public final TextView textViewTagnameNew;

    private ListviewAdapterManagetagBinding(LinearLayout linearLayout, ImageView imageView, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5) {
        this.rootView = linearLayout;
        this.imageView1 = imageView;
        this.textViewTagId = textView;
        this.textViewTagIndex = textView2;
        this.textViewTagIndex1 = textView3;
        this.textViewTagUid = textView4;
        this.textViewTagnameNew = textView5;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ListviewAdapterManagetagBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ListviewAdapterManagetagBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.listview_adapter_managetag, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ListviewAdapterManagetagBinding bind(View view) {
        int i = R.id.imageView1;
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView1);
        if (imageView != null) {
            i = R.id.textView_tag_id;
            TextView textView = (TextView) view.findViewById(R.id.textView_tag_id);
            if (textView != null) {
                i = R.id.textView_tag_index;
                TextView textView2 = (TextView) view.findViewById(R.id.textView_tag_index);
                if (textView2 != null) {
                    i = R.id.textView_tag_index1;
                    TextView textView3 = (TextView) view.findViewById(R.id.textView_tag_index1);
                    if (textView3 != null) {
                        i = R.id.textView_tag_uid;
                        TextView textView4 = (TextView) view.findViewById(R.id.textView_tag_uid);
                        if (textView4 != null) {
                            i = R.id.textView_tagname_new;
                            TextView textView5 = (TextView) view.findViewById(R.id.textView_tagname_new);
                            if (textView5 != null) {
                                return new ListviewAdapterManagetagBinding((LinearLayout) view, imageView, textView, textView2, textView3, textView4, textView5);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
