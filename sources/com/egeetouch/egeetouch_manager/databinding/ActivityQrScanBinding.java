package com.egeetouch.egeetouch_manager.databinding;

import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import com.egeetouch.egeetouch_manager.R;
/* loaded from: classes.dex */
public final class ActivityQrScanBinding implements ViewBinding {
    private final ConstraintLayout rootView;
    public final SurfaceView surfaceView;

    private ActivityQrScanBinding(ConstraintLayout constraintLayout, SurfaceView surfaceView) {
        this.rootView = constraintLayout;
        this.surfaceView = surfaceView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityQrScanBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ActivityQrScanBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_qr_scan, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivityQrScanBinding bind(View view) {
        SurfaceView surfaceView = (SurfaceView) view.findViewById(R.id.surfaceView);
        if (surfaceView != null) {
            return new ActivityQrScanBinding((ConstraintLayout) view, surfaceView);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(R.id.surfaceView)));
    }
}
