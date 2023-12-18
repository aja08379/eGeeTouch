package com.egeetouch.egeetouch_manager.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import com.egeetouch.egeetouch_manager.R;
/* loaded from: classes.dex */
public final class ActivityLearnMoreBinding implements ViewBinding {
    public final ImageView applicationImageView;
    public final ImageView auditrailImageView;
    public final ImageView bannerImageView;
    public final Button claim2;
    public final ImageView featureImageView;
    public final ImageView freeupgradeImageView;
    public final ImageView multipledeviceImageView;
    public final ImageView revokeImageView;
    private final ScrollView rootView;
    public final TextView text1;
    public final TextView text2;
    public final TextView text3;
    public final TextView text4;
    public final TextView text5;
    public final TextView text6;
    public final ImageView trackingImageView;
    public final ImageView usermanagementImageView;
    public final ImageView youtubeImageView;

    private ActivityLearnMoreBinding(ScrollView scrollView, ImageView imageView, ImageView imageView2, ImageView imageView3, Button button, ImageView imageView4, ImageView imageView5, ImageView imageView6, ImageView imageView7, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, ImageView imageView8, ImageView imageView9, ImageView imageView10) {
        this.rootView = scrollView;
        this.applicationImageView = imageView;
        this.auditrailImageView = imageView2;
        this.bannerImageView = imageView3;
        this.claim2 = button;
        this.featureImageView = imageView4;
        this.freeupgradeImageView = imageView5;
        this.multipledeviceImageView = imageView6;
        this.revokeImageView = imageView7;
        this.text1 = textView;
        this.text2 = textView2;
        this.text3 = textView3;
        this.text4 = textView4;
        this.text5 = textView5;
        this.text6 = textView6;
        this.trackingImageView = imageView8;
        this.usermanagementImageView = imageView9;
        this.youtubeImageView = imageView10;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ScrollView getRoot() {
        return this.rootView;
    }

    public static ActivityLearnMoreBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ActivityLearnMoreBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_learn_more, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivityLearnMoreBinding bind(View view) {
        int i = R.id.applicationImageView;
        ImageView imageView = (ImageView) view.findViewById(R.id.applicationImageView);
        if (imageView != null) {
            i = R.id.auditrailImageView;
            ImageView imageView2 = (ImageView) view.findViewById(R.id.auditrailImageView);
            if (imageView2 != null) {
                i = R.id.bannerImageView;
                ImageView imageView3 = (ImageView) view.findViewById(R.id.bannerImageView);
                if (imageView3 != null) {
                    i = R.id.claim2;
                    Button button = (Button) view.findViewById(R.id.claim2);
                    if (button != null) {
                        i = R.id.featureImageView;
                        ImageView imageView4 = (ImageView) view.findViewById(R.id.featureImageView);
                        if (imageView4 != null) {
                            i = R.id.freeupgradeImageView;
                            ImageView imageView5 = (ImageView) view.findViewById(R.id.freeupgradeImageView);
                            if (imageView5 != null) {
                                i = R.id.multipledeviceImageView;
                                ImageView imageView6 = (ImageView) view.findViewById(R.id.multipledeviceImageView);
                                if (imageView6 != null) {
                                    i = R.id.revokeImageView;
                                    ImageView imageView7 = (ImageView) view.findViewById(R.id.revokeImageView);
                                    if (imageView7 != null) {
                                        i = R.id.text1;
                                        TextView textView = (TextView) view.findViewById(R.id.text1);
                                        if (textView != null) {
                                            i = R.id.text2;
                                            TextView textView2 = (TextView) view.findViewById(R.id.text2);
                                            if (textView2 != null) {
                                                i = R.id.text3;
                                                TextView textView3 = (TextView) view.findViewById(R.id.text3);
                                                if (textView3 != null) {
                                                    i = R.id.text4;
                                                    TextView textView4 = (TextView) view.findViewById(R.id.text4);
                                                    if (textView4 != null) {
                                                        i = R.id.text5;
                                                        TextView textView5 = (TextView) view.findViewById(R.id.text5);
                                                        if (textView5 != null) {
                                                            i = R.id.text6;
                                                            TextView textView6 = (TextView) view.findViewById(R.id.text6);
                                                            if (textView6 != null) {
                                                                i = R.id.trackingImageView;
                                                                ImageView imageView8 = (ImageView) view.findViewById(R.id.trackingImageView);
                                                                if (imageView8 != null) {
                                                                    i = R.id.usermanagementImageView;
                                                                    ImageView imageView9 = (ImageView) view.findViewById(R.id.usermanagementImageView);
                                                                    if (imageView9 != null) {
                                                                        i = R.id.youtubeImageView;
                                                                        ImageView imageView10 = (ImageView) view.findViewById(R.id.youtubeImageView);
                                                                        if (imageView10 != null) {
                                                                            return new ActivityLearnMoreBinding((ScrollView) view, imageView, imageView2, imageView3, button, imageView4, imageView5, imageView6, imageView7, textView, textView2, textView3, textView4, textView5, textView6, imageView8, imageView9, imageView10);
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
