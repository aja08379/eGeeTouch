package com.egeetouch.egeetouch_manager.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import com.egeetouch.egeetouch_manager.R;
/* loaded from: classes.dex */
public final class BellNotificationBinding implements ViewBinding {
    public final ImageView imageView9;
    public final CardView notificationCardView;
    private final ConstraintLayout rootView;
    public final TextView textView11;

    private BellNotificationBinding(ConstraintLayout constraintLayout, ImageView imageView, CardView cardView, TextView textView) {
        this.rootView = constraintLayout;
        this.imageView9 = imageView;
        this.notificationCardView = cardView;
        this.textView11 = textView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static BellNotificationBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static BellNotificationBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.bell_notification, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static BellNotificationBinding bind(View view) {
        int i = R.id.imageView9;
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView9);
        if (imageView != null) {
            i = R.id.notification_cardView;
            CardView cardView = (CardView) view.findViewById(R.id.notification_cardView);
            if (cardView != null) {
                i = R.id.textView11;
                TextView textView = (TextView) view.findViewById(R.id.textView11);
                if (textView != null) {
                    return new BellNotificationBinding((ConstraintLayout) view, imageView, cardView, textView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
