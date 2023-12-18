package com.egeetouch.egeetouch_manager.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import com.egeetouch.egeetouch_manager.R;
/* loaded from: classes.dex */
public final class AppRatingPopUpBinding implements ViewBinding {
    public final Button buttonCancel;
    public final Button buttonNotNow;
    public final Button buttonReview;
    public final Button buttonSubmit;
    public final EditText edReview;
    public final LinearLayout llReview;
    public final TextView messageTV;
    public final RatingBar ratingBar;
    private final RelativeLayout rootView;
    public final TextView titleTv;

    private AppRatingPopUpBinding(RelativeLayout relativeLayout, Button button, Button button2, Button button3, Button button4, EditText editText, LinearLayout linearLayout, TextView textView, RatingBar ratingBar, TextView textView2) {
        this.rootView = relativeLayout;
        this.buttonCancel = button;
        this.buttonNotNow = button2;
        this.buttonReview = button3;
        this.buttonSubmit = button4;
        this.edReview = editText;
        this.llReview = linearLayout;
        this.messageTV = textView;
        this.ratingBar = ratingBar;
        this.titleTv = textView2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static AppRatingPopUpBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AppRatingPopUpBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.app_rating_pop_up, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AppRatingPopUpBinding bind(View view) {
        int i = R.id.button_cancel;
        Button button = (Button) view.findViewById(R.id.button_cancel);
        if (button != null) {
            i = R.id.button_notNow;
            Button button2 = (Button) view.findViewById(R.id.button_notNow);
            if (button2 != null) {
                i = R.id.button_review;
                Button button3 = (Button) view.findViewById(R.id.button_review);
                if (button3 != null) {
                    i = R.id.button_submit;
                    Button button4 = (Button) view.findViewById(R.id.button_submit);
                    if (button4 != null) {
                        i = R.id.ed_review;
                        EditText editText = (EditText) view.findViewById(R.id.ed_review);
                        if (editText != null) {
                            i = R.id.ll_review;
                            LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.ll_review);
                            if (linearLayout != null) {
                                i = R.id.messageTV;
                                TextView textView = (TextView) view.findViewById(R.id.messageTV);
                                if (textView != null) {
                                    i = R.id.ratingBar;
                                    RatingBar ratingBar = (RatingBar) view.findViewById(R.id.ratingBar);
                                    if (ratingBar != null) {
                                        i = R.id.titleTv;
                                        TextView textView2 = (TextView) view.findViewById(R.id.titleTv);
                                        if (textView2 != null) {
                                            return new AppRatingPopUpBinding((RelativeLayout) view, button, button2, button3, button4, editText, linearLayout, textView, ratingBar, textView2);
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
