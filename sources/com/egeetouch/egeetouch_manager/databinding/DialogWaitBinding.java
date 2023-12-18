package com.egeetouch.egeetouch_manager.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import com.egeetouch.egeetouch_manager.R;
/* loaded from: classes.dex */
public final class DialogWaitBinding implements ViewBinding {
    public final ProgressBar progressBg;
    private final ConstraintLayout rootView;
    public final Button waitCancel;
    public final TextView waitContent;
    public final TextView waitPercent;
    public final ProgressBar waitProgress;

    private DialogWaitBinding(ConstraintLayout constraintLayout, ProgressBar progressBar, Button button, TextView textView, TextView textView2, ProgressBar progressBar2) {
        this.rootView = constraintLayout;
        this.progressBg = progressBar;
        this.waitCancel = button;
        this.waitContent = textView;
        this.waitPercent = textView2;
        this.waitProgress = progressBar2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static DialogWaitBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static DialogWaitBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.dialog_wait, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static DialogWaitBinding bind(View view) {
        int i = R.id.progress_bg;
        ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.progress_bg);
        if (progressBar != null) {
            i = R.id.wait_cancel;
            Button button = (Button) view.findViewById(R.id.wait_cancel);
            if (button != null) {
                i = R.id.wait_content;
                TextView textView = (TextView) view.findViewById(R.id.wait_content);
                if (textView != null) {
                    i = R.id.wait_percent;
                    TextView textView2 = (TextView) view.findViewById(R.id.wait_percent);
                    if (textView2 != null) {
                        i = R.id.wait_progress;
                        ProgressBar progressBar2 = (ProgressBar) view.findViewById(R.id.wait_progress);
                        if (progressBar2 != null) {
                            return new DialogWaitBinding((ConstraintLayout) view, progressBar, button, textView, textView2, progressBar2);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
