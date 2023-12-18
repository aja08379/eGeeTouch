package com.github.florent37.singledateandtimepicker.dialog;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import com.github.florent37.singledateandtimepicker.R;
/* loaded from: classes.dex */
public class BottomSheetHelper {
    private Context context;
    private Handler handler = new Handler(Looper.getMainLooper());
    private int layoutId;
    private Listener listener;
    private View view;
    private WindowManager windowManager;

    /* loaded from: classes.dex */
    public interface Listener {
        void onClose();

        void onLoaded(View view);

        void onOpen();
    }

    public BottomSheetHelper(Context context, int i) {
        this.context = context;
        this.layoutId = i;
    }

    private void init() {
        this.handler.postDelayed(new Runnable() { // from class: com.github.florent37.singledateandtimepicker.dialog.BottomSheetHelper.1
            @Override // java.lang.Runnable
            public void run() {
                if (BottomSheetHelper.this.context instanceof Activity) {
                    BottomSheetHelper bottomSheetHelper = BottomSheetHelper.this;
                    bottomSheetHelper.windowManager = (WindowManager) bottomSheetHelper.context.getSystemService("window");
                    BottomSheetHelper bottomSheetHelper2 = BottomSheetHelper.this;
                    bottomSheetHelper2.view = LayoutInflater.from(bottomSheetHelper2.context).inflate(BottomSheetHelper.this.layoutId, (ViewGroup) null, true);
                    WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(-1, -1, 1000, 8, -3);
                    if ((layoutParams.softInputMode & 256) == 0) {
                        WindowManager.LayoutParams layoutParams2 = new WindowManager.LayoutParams();
                        layoutParams2.copyFrom(layoutParams);
                        layoutParams2.softInputMode |= 256;
                        layoutParams = layoutParams2;
                    }
                    BottomSheetHelper.this.windowManager.addView(BottomSheetHelper.this.view, layoutParams);
                    BottomSheetHelper.this.view.findViewById(R.id.bottom_sheet_background).setOnClickListener(new View.OnClickListener() { // from class: com.github.florent37.singledateandtimepicker.dialog.BottomSheetHelper.1.1
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            BottomSheetHelper.this.hide();
                        }
                    });
                    BottomSheetHelper.this.view.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() { // from class: com.github.florent37.singledateandtimepicker.dialog.BottomSheetHelper.1.2
                        @Override // android.view.ViewTreeObserver.OnPreDrawListener
                        public boolean onPreDraw() {
                            BottomSheetHelper.this.view.getViewTreeObserver().removeOnPreDrawListener(this);
                            if (BottomSheetHelper.this.listener != null) {
                                BottomSheetHelper.this.listener.onLoaded(BottomSheetHelper.this.view);
                                return true;
                            }
                            return true;
                        }
                    });
                }
            }
        }, 100L);
    }

    public BottomSheetHelper setListener(Listener listener) {
        this.listener = listener;
        return this;
    }

    public void display() {
        init();
        this.handler.postDelayed(new Runnable() { // from class: com.github.florent37.singledateandtimepicker.dialog.BottomSheetHelper.2
            @Override // java.lang.Runnable
            public void run() {
                ObjectAnimator ofFloat = ObjectAnimator.ofFloat(BottomSheetHelper.this.view, View.TRANSLATION_Y, BottomSheetHelper.this.view.getHeight(), 0.0f);
                ofFloat.addListener(new AnimatorListenerAdapter() { // from class: com.github.florent37.singledateandtimepicker.dialog.BottomSheetHelper.2.1
                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                    public void onAnimationEnd(Animator animator) {
                        if (BottomSheetHelper.this.listener != null) {
                            BottomSheetHelper.this.listener.onOpen();
                        }
                    }
                });
                ofFloat.start();
            }
        }, 200L);
    }

    public void hide() {
        this.handler.postDelayed(new Runnable() { // from class: com.github.florent37.singledateandtimepicker.dialog.BottomSheetHelper.3
            @Override // java.lang.Runnable
            public void run() {
                ObjectAnimator ofFloat = ObjectAnimator.ofFloat(BottomSheetHelper.this.view, View.TRANSLATION_Y, 0.0f, BottomSheetHelper.this.view.getHeight());
                ofFloat.addListener(new AnimatorListenerAdapter() { // from class: com.github.florent37.singledateandtimepicker.dialog.BottomSheetHelper.3.1
                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                    public void onAnimationEnd(Animator animator) {
                        BottomSheetHelper.this.view.setVisibility(8);
                        if (BottomSheetHelper.this.listener != null) {
                            BottomSheetHelper.this.listener.onClose();
                        }
                        BottomSheetHelper.this.remove();
                    }
                });
                ofFloat.start();
            }
        }, 200L);
    }

    public void dismiss() {
        remove();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void remove() {
        if (this.view.getWindowToken() != null) {
            this.windowManager.removeView(this.view);
        }
    }
}
