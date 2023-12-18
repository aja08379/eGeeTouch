package com.egeetouch.egeetouch_manager.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.viewbinding.ViewBinding;
import com.egeetouch.egeetouch_manager.R;
import java.util.Objects;
/* loaded from: classes.dex */
public final class ShackleOpenedBinding implements ViewBinding {
    private final RelativeLayout rootView;

    private ShackleOpenedBinding(RelativeLayout relativeLayout) {
        this.rootView = relativeLayout;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static ShackleOpenedBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ShackleOpenedBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.shackle_opened, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ShackleOpenedBinding bind(View view) {
        Objects.requireNonNull(view, "rootView");
        return new ShackleOpenedBinding((RelativeLayout) view);
    }
}
