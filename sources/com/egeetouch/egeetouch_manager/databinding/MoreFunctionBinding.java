package com.egeetouch.egeetouch_manager.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.viewbinding.ViewBinding;
import com.egeetouch.egeetouch_manager.R;
/* loaded from: classes.dex */
public final class MoreFunctionBinding implements ViewBinding {
    public final ImageView imageViewAudittrail;
    public final ImageView imageViewManagetag;
    public final ImageView imageViewManageuser;
    public final ImageView imageViewUsFlag;
    private final LinearLayout rootView;

    private MoreFunctionBinding(LinearLayout linearLayout, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4) {
        this.rootView = linearLayout;
        this.imageViewAudittrail = imageView;
        this.imageViewManagetag = imageView2;
        this.imageViewManageuser = imageView3;
        this.imageViewUsFlag = imageView4;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static MoreFunctionBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static MoreFunctionBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.more_function, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static MoreFunctionBinding bind(View view) {
        int i = R.id.imageView_audittrail;
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView_audittrail);
        if (imageView != null) {
            i = R.id.imageView_managetag;
            ImageView imageView2 = (ImageView) view.findViewById(R.id.imageView_managetag);
            if (imageView2 != null) {
                i = R.id.imageView_manageuser;
                ImageView imageView3 = (ImageView) view.findViewById(R.id.imageView_manageuser);
                if (imageView3 != null) {
                    i = R.id.imageView_us_flag;
                    ImageView imageView4 = (ImageView) view.findViewById(R.id.imageView_us_flag);
                    if (imageView4 != null) {
                        return new MoreFunctionBinding((LinearLayout) view, imageView, imageView2, imageView3, imageView4);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
