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
public final class ListviewAdapterManagepasscodeBinding implements ViewBinding {
    public final ImageView imageView1;
    public final ImageView passcode1;
    public final ImageView passcode2;
    public final ImageView passcode3;
    public final ImageView passcode4;
    public final ImageView passcode5;
    public final ImageView passcode6;
    private final LinearLayout rootView;
    public final LinearLayout slots;
    public final TextView textViewPasscode;
    public final TextView textViewPasscodeIndex;
    public final TextView textViewPasscodeName;
    public final TextView textViewTagId;
    public final TextView textViewTagIndex1;

    private ListviewAdapterManagepasscodeBinding(LinearLayout linearLayout, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, ImageView imageView5, ImageView imageView6, ImageView imageView7, LinearLayout linearLayout2, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5) {
        this.rootView = linearLayout;
        this.imageView1 = imageView;
        this.passcode1 = imageView2;
        this.passcode2 = imageView3;
        this.passcode3 = imageView4;
        this.passcode4 = imageView5;
        this.passcode5 = imageView6;
        this.passcode6 = imageView7;
        this.slots = linearLayout2;
        this.textViewPasscode = textView;
        this.textViewPasscodeIndex = textView2;
        this.textViewPasscodeName = textView3;
        this.textViewTagId = textView4;
        this.textViewTagIndex1 = textView5;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ListviewAdapterManagepasscodeBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ListviewAdapterManagepasscodeBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.listview_adapter_managepasscode, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ListviewAdapterManagepasscodeBinding bind(View view) {
        int i = R.id.imageView1;
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView1);
        if (imageView != null) {
            i = R.id.passcode1;
            ImageView imageView2 = (ImageView) view.findViewById(R.id.passcode1);
            if (imageView2 != null) {
                i = R.id.passcode2;
                ImageView imageView3 = (ImageView) view.findViewById(R.id.passcode2);
                if (imageView3 != null) {
                    i = R.id.passcode3;
                    ImageView imageView4 = (ImageView) view.findViewById(R.id.passcode3);
                    if (imageView4 != null) {
                        i = R.id.passcode4;
                        ImageView imageView5 = (ImageView) view.findViewById(R.id.passcode4);
                        if (imageView5 != null) {
                            i = R.id.passcode5;
                            ImageView imageView6 = (ImageView) view.findViewById(R.id.passcode5);
                            if (imageView6 != null) {
                                i = R.id.passcode6;
                                ImageView imageView7 = (ImageView) view.findViewById(R.id.passcode6);
                                if (imageView7 != null) {
                                    i = R.id.slots;
                                    LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.slots);
                                    if (linearLayout != null) {
                                        i = R.id.textView_passcode;
                                        TextView textView = (TextView) view.findViewById(R.id.textView_passcode);
                                        if (textView != null) {
                                            i = R.id.textView_passcode_index;
                                            TextView textView2 = (TextView) view.findViewById(R.id.textView_passcode_index);
                                            if (textView2 != null) {
                                                i = R.id.textView_passcodeName;
                                                TextView textView3 = (TextView) view.findViewById(R.id.textView_passcodeName);
                                                if (textView3 != null) {
                                                    i = R.id.textView_tag_id;
                                                    TextView textView4 = (TextView) view.findViewById(R.id.textView_tag_id);
                                                    if (textView4 != null) {
                                                        i = R.id.textView_tag_index1;
                                                        TextView textView5 = (TextView) view.findViewById(R.id.textView_tag_index1);
                                                        if (textView5 != null) {
                                                            return new ListviewAdapterManagepasscodeBinding((LinearLayout) view, imageView, imageView2, imageView3, imageView4, imageView5, imageView6, imageView7, linearLayout, textView, textView2, textView3, textView4, textView5);
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
