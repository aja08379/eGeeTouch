package cn.pedant.SweetAlert;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.Transformation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.pnikosis.materialishprogress.ProgressWheel;
import java.util.List;
/* loaded from: classes.dex */
public class SweetAlertDialog extends Dialog implements View.OnClickListener {
    public static final int BUTTON_CANCEL = -2;
    public static final int BUTTON_CONFIRM = -1;
    public static final int CUSTOM_IMAGE_TYPE = 4;
    public static boolean DARK_STYLE = false;
    public static final int ERROR_TYPE = 1;
    public static final int NORMAL_TYPE = 0;
    public static final int PROGRESS_TYPE = 5;
    public static final int SUCCESS_TYPE = 2;
    public static final int WARNING_TYPE = 3;
    private int contentTextSize;
    private int mAlertType;
    private LinearLayout mButtonsContainer;
    private Button mCancelButton;
    private OnSweetClickListener mCancelClickListener;
    private String mCancelText;
    private boolean mCloseFromCancel;
    private Button mConfirmButton;
    private OnSweetClickListener mConfirmClickListener;
    private String mConfirmText;
    private String mContentText;
    private TextView mContentTextView;
    private ImageView mCustomImage;
    private Drawable mCustomImgDrawable;
    private View mCustomView;
    private FrameLayout mCustomViewContainer;
    private View mDialogView;
    private FrameLayout mErrorFrame;
    private Animation mErrorInAnim;
    private ImageView mErrorX;
    private AnimationSet mErrorXInAnim;
    private boolean mHideConfirmButton;
    private AnimationSet mModalInAnim;
    private AnimationSet mModalOutAnim;
    private Button mNeutralButton;
    private OnSweetClickListener mNeutralClickListener;
    private String mNeutralText;
    private Animation mOverlayOutAnim;
    private FrameLayout mProgressFrame;
    private ProgressHelper mProgressHelper;
    private boolean mShowCancel;
    private boolean mShowContent;
    private Animation mSuccessBowAnim;
    private FrameLayout mSuccessFrame;
    private AnimationSet mSuccessLayoutAnimSet;
    private View mSuccessLeftMask;
    private View mSuccessRightMask;
    private SuccessTickView mSuccessTick;
    private String mTitleText;
    private TextView mTitleTextView;
    private FrameLayout mWarningFrame;

    /* loaded from: classes.dex */
    public interface OnSweetClickListener {
        void onClick(SweetAlertDialog sweetAlertDialog);
    }

    public SweetAlertDialog hideConfirmButton() {
        this.mHideConfirmButton = true;
        return this;
    }

    public SweetAlertDialog(Context context) {
        this(context, 0);
    }

    public SweetAlertDialog(Context context, int i) {
        super(context, DARK_STYLE ? R.style.alert_dialog_dark : R.style.alert_dialog_light);
        int i2 = 0;
        this.mHideConfirmButton = false;
        this.contentTextSize = 0;
        setCancelable(true);
        setCanceledOnTouchOutside(true);
        this.mProgressHelper = new ProgressHelper(context);
        this.mAlertType = i;
        this.mErrorInAnim = OptAnimationLoader.loadAnimation(getContext(), R.anim.error_frame_in);
        this.mErrorXInAnim = (AnimationSet) OptAnimationLoader.loadAnimation(getContext(), R.anim.error_x_in);
        if (Build.VERSION.SDK_INT <= 10) {
            List<Animation> animations = this.mErrorXInAnim.getAnimations();
            while (i2 < animations.size() && !(animations.get(i2) instanceof AlphaAnimation)) {
                i2++;
            }
            if (i2 < animations.size()) {
                animations.remove(i2);
            }
        }
        this.mSuccessBowAnim = OptAnimationLoader.loadAnimation(getContext(), R.anim.success_bow_roate);
        this.mSuccessLayoutAnimSet = (AnimationSet) OptAnimationLoader.loadAnimation(getContext(), R.anim.success_mask_layout);
        this.mModalInAnim = (AnimationSet) OptAnimationLoader.loadAnimation(getContext(), R.anim.modal_in);
        AnimationSet animationSet = (AnimationSet) OptAnimationLoader.loadAnimation(getContext(), R.anim.modal_out);
        this.mModalOutAnim = animationSet;
        animationSet.setAnimationListener(new Animation.AnimationListener() { // from class: cn.pedant.SweetAlert.SweetAlertDialog.1
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                SweetAlertDialog.this.mDialogView.setVisibility(8);
                SweetAlertDialog.this.mDialogView.post(new Runnable() { // from class: cn.pedant.SweetAlert.SweetAlertDialog.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (SweetAlertDialog.this.mCloseFromCancel) {
                            SweetAlertDialog.super.cancel();
                        } else {
                            SweetAlertDialog.super.dismiss();
                        }
                    }
                });
            }
        });
        Animation animation = new Animation() { // from class: cn.pedant.SweetAlert.SweetAlertDialog.2
            @Override // android.view.animation.Animation
            protected void applyTransformation(float f, Transformation transformation) {
                WindowManager.LayoutParams attributes = SweetAlertDialog.this.getWindow().getAttributes();
                attributes.alpha = 1.0f - f;
                SweetAlertDialog.this.getWindow().setAttributes(attributes);
            }
        };
        this.mOverlayOutAnim = animation;
        animation.setDuration(120L);
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.alert_dialog);
        this.mDialogView = getWindow().getDecorView().findViewById(16908290);
        this.mTitleTextView = (TextView) findViewById(R.id.title_text);
        this.mContentTextView = (TextView) findViewById(R.id.content_text);
        this.mCustomViewContainer = (FrameLayout) findViewById(R.id.custom_view_container);
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.error_frame);
        this.mErrorFrame = frameLayout;
        this.mErrorX = (ImageView) frameLayout.findViewById(R.id.error_x);
        this.mSuccessFrame = (FrameLayout) findViewById(R.id.success_frame);
        this.mProgressFrame = (FrameLayout) findViewById(R.id.progress_dialog);
        this.mSuccessTick = (SuccessTickView) this.mSuccessFrame.findViewById(R.id.success_tick);
        this.mSuccessLeftMask = this.mSuccessFrame.findViewById(R.id.mask_left);
        this.mSuccessRightMask = this.mSuccessFrame.findViewById(R.id.mask_right);
        this.mCustomImage = (ImageView) findViewById(R.id.custom_image);
        this.mWarningFrame = (FrameLayout) findViewById(R.id.warning_frame);
        this.mButtonsContainer = (LinearLayout) findViewById(R.id.buttons_container);
        Button button = (Button) findViewById(R.id.confirm_button);
        this.mConfirmButton = button;
        button.setOnClickListener(this);
        this.mConfirmButton.setOnTouchListener(Constants.FOCUS_TOUCH_LISTENER);
        Button button2 = (Button) findViewById(R.id.cancel_button);
        this.mCancelButton = button2;
        button2.setOnClickListener(this);
        this.mCancelButton.setOnTouchListener(Constants.FOCUS_TOUCH_LISTENER);
        Button button3 = (Button) findViewById(R.id.neutral_button);
        this.mNeutralButton = button3;
        button3.setOnClickListener(this);
        this.mNeutralButton.setOnTouchListener(Constants.FOCUS_TOUCH_LISTENER);
        this.mProgressHelper.setProgressWheel((ProgressWheel) findViewById(R.id.progressWheel));
        setTitleText(this.mTitleText);
        setContentText(this.mContentText);
        setCustomView(this.mCustomView);
        setCancelText(this.mCancelText);
        setConfirmText(this.mConfirmText);
        setNeutralText(this.mNeutralText);
        changeAlertType(this.mAlertType, true);
    }

    private void restore() {
        this.mCustomImage.setVisibility(8);
        this.mErrorFrame.setVisibility(8);
        this.mSuccessFrame.setVisibility(8);
        this.mWarningFrame.setVisibility(8);
        this.mProgressFrame.setVisibility(8);
        this.mConfirmButton.setVisibility(this.mHideConfirmButton ? 8 : 0);
        adjustButtonContainerVisibility();
        this.mConfirmButton.setBackgroundResource(R.drawable.green_button_background);
        this.mErrorFrame.clearAnimation();
        this.mErrorX.clearAnimation();
        this.mSuccessTick.clearAnimation();
        this.mSuccessLeftMask.clearAnimation();
        this.mSuccessRightMask.clearAnimation();
    }

    private void adjustButtonContainerVisibility() {
        boolean z;
        int i = 0;
        while (true) {
            if (i >= this.mButtonsContainer.getChildCount()) {
                z = false;
                break;
            }
            View childAt = this.mButtonsContainer.getChildAt(i);
            if ((childAt instanceof Button) && childAt.getVisibility() == 0) {
                z = true;
                break;
            }
            i++;
        }
        this.mButtonsContainer.setVisibility(z ? 0 : 8);
    }

    private void playAnimation() {
        int i = this.mAlertType;
        if (i == 1) {
            this.mErrorFrame.startAnimation(this.mErrorInAnim);
            this.mErrorX.startAnimation(this.mErrorXInAnim);
        } else if (i == 2) {
            this.mSuccessTick.startTickAnim();
            this.mSuccessRightMask.startAnimation(this.mSuccessBowAnim);
        }
    }

    private void changeAlertType(int i, boolean z) {
        this.mAlertType = i;
        if (this.mDialogView != null) {
            if (!z) {
                restore();
            }
            this.mConfirmButton.setVisibility(this.mHideConfirmButton ? 8 : 0);
            int i2 = this.mAlertType;
            if (i2 == 1) {
                this.mErrorFrame.setVisibility(0);
            } else if (i2 == 2) {
                this.mSuccessFrame.setVisibility(0);
                this.mSuccessLeftMask.startAnimation(this.mSuccessLayoutAnimSet.getAnimations().get(0));
                this.mSuccessRightMask.startAnimation(this.mSuccessLayoutAnimSet.getAnimations().get(1));
            } else if (i2 == 3) {
                this.mWarningFrame.setVisibility(0);
            } else if (i2 == 4) {
                setCustomImage(this.mCustomImgDrawable);
            } else if (i2 == 5) {
                this.mProgressFrame.setVisibility(0);
                this.mConfirmButton.setVisibility(8);
            }
            adjustButtonContainerVisibility();
            if (z) {
                return;
            }
            playAnimation();
        }
    }

    public int getAlerType() {
        return this.mAlertType;
    }

    public void changeAlertType(int i) {
        changeAlertType(i, false);
    }

    public String getTitleText() {
        return this.mTitleText;
    }

    public SweetAlertDialog setTitleText(String str) {
        this.mTitleText = str;
        if (this.mTitleTextView != null && str != null) {
            if (str.isEmpty()) {
                this.mTitleTextView.setVisibility(8);
            } else {
                this.mTitleTextView.setVisibility(0);
                this.mTitleTextView.setText(Html.fromHtml(this.mTitleText));
            }
        }
        return this;
    }

    public SweetAlertDialog setTitleText(int i) {
        return setTitleText(getContext().getResources().getString(i));
    }

    public SweetAlertDialog setCustomImage(Drawable drawable) {
        this.mCustomImgDrawable = drawable;
        ImageView imageView = this.mCustomImage;
        if (imageView != null && drawable != null) {
            imageView.setVisibility(0);
            this.mCustomImage.setImageDrawable(this.mCustomImgDrawable);
        }
        return this;
    }

    public SweetAlertDialog setCustomImage(int i) {
        return setCustomImage(getContext().getResources().getDrawable(i));
    }

    public String getContentText() {
        return this.mContentText;
    }

    public SweetAlertDialog setContentText(String str) {
        this.mContentText = str;
        if (this.mContentTextView != null && str != null) {
            showContentText(true);
            int i = this.contentTextSize;
            if (i != 0) {
                this.mContentTextView.setTextSize(0, spToPx(i, getContext()));
            }
            this.mContentTextView.setText(Html.fromHtml(this.mContentText));
            this.mContentTextView.setVisibility(0);
            this.mCustomViewContainer.setVisibility(8);
        }
        return this;
    }

    public static int spToPx(float f, Context context) {
        return (int) TypedValue.applyDimension(2, f, context.getResources().getDisplayMetrics());
    }

    public boolean isShowCancelButton() {
        return this.mShowCancel;
    }

    public SweetAlertDialog showCancelButton(boolean z) {
        this.mShowCancel = z;
        Button button = this.mCancelButton;
        if (button != null) {
            button.setVisibility(z ? 0 : 8);
        }
        return this;
    }

    public boolean isShowContentText() {
        return this.mShowContent;
    }

    public SweetAlertDialog showContentText(boolean z) {
        this.mShowContent = z;
        TextView textView = this.mContentTextView;
        if (textView != null) {
            textView.setVisibility(z ? 0 : 8);
        }
        return this;
    }

    public String getCancelText() {
        return this.mCancelText;
    }

    public SweetAlertDialog setCancelText(String str) {
        this.mCancelText = str;
        if (this.mCancelButton != null && str != null) {
            showCancelButton(true);
            this.mCancelButton.setText(this.mCancelText);
        }
        return this;
    }

    public String getConfirmText() {
        return this.mConfirmText;
    }

    public SweetAlertDialog setConfirmText(String str) {
        this.mConfirmText = str;
        Button button = this.mConfirmButton;
        if (button != null && str != null) {
            button.setText(str);
        }
        return this;
    }

    public SweetAlertDialog setCancelClickListener(OnSweetClickListener onSweetClickListener) {
        this.mCancelClickListener = onSweetClickListener;
        return this;
    }

    public SweetAlertDialog setConfirmClickListener(OnSweetClickListener onSweetClickListener) {
        this.mConfirmClickListener = onSweetClickListener;
        return this;
    }

    public SweetAlertDialog setNeutralText(String str) {
        this.mNeutralText = str;
        if (this.mNeutralButton != null && str != null && !str.isEmpty()) {
            this.mNeutralButton.setVisibility(0);
            this.mNeutralButton.setText(this.mNeutralText);
        }
        return this;
    }

    public SweetAlertDialog setNeutralClickListener(OnSweetClickListener onSweetClickListener) {
        this.mNeutralClickListener = onSweetClickListener;
        return this;
    }

    @Override // android.app.Dialog
    public void setTitle(CharSequence charSequence) {
        setTitleText(charSequence.toString());
    }

    @Override // android.app.Dialog
    public void setTitle(int i) {
        setTitleText(getContext().getResources().getString(i));
    }

    public Button getButton(int i) {
        if (i != -3) {
            if (i != -2) {
                return this.mConfirmButton;
            }
            return this.mCancelButton;
        }
        return this.mNeutralButton;
    }

    public SweetAlertDialog setConfirmButton(String str, OnSweetClickListener onSweetClickListener) {
        setConfirmText(str);
        setConfirmClickListener(onSweetClickListener);
        return this;
    }

    public SweetAlertDialog setConfirmButton(int i, OnSweetClickListener onSweetClickListener) {
        setConfirmButton(getContext().getResources().getString(i), onSweetClickListener);
        return this;
    }

    public SweetAlertDialog setCancelButton(String str, OnSweetClickListener onSweetClickListener) {
        setCancelText(str);
        setCancelClickListener(onSweetClickListener);
        return this;
    }

    public SweetAlertDialog setCancelButton(int i, OnSweetClickListener onSweetClickListener) {
        setCancelButton(getContext().getResources().getString(i), onSweetClickListener);
        return this;
    }

    public SweetAlertDialog setNeutralButton(String str, OnSweetClickListener onSweetClickListener) {
        setNeutralText(str);
        setNeutralClickListener(onSweetClickListener);
        return this;
    }

    public SweetAlertDialog setNeutralButton(int i, OnSweetClickListener onSweetClickListener) {
        setNeutralButton(getContext().getResources().getString(i), onSweetClickListener);
        return this;
    }

    public SweetAlertDialog setContentTextSize(int i) {
        this.contentTextSize = i;
        return this;
    }

    public int getContentTextSize() {
        return this.contentTextSize;
    }

    @Override // android.app.Dialog
    protected void onStart() {
        this.mDialogView.startAnimation(this.mModalInAnim);
        playAnimation();
    }

    public SweetAlertDialog setCustomView(View view) {
        FrameLayout frameLayout;
        this.mCustomView = view;
        if (view != null && (frameLayout = this.mCustomViewContainer) != null) {
            frameLayout.addView(view);
            this.mCustomViewContainer.setVisibility(0);
            this.mContentTextView.setVisibility(8);
        }
        return this;
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void cancel() {
        dismissWithAnimation(true);
    }

    public void dismissWithAnimation() {
        dismissWithAnimation(false);
    }

    private void dismissWithAnimation(boolean z) {
        this.mCloseFromCancel = z;
        ((ViewGroup) this.mDialogView).getChildAt(0).startAnimation(this.mOverlayOutAnim);
        this.mDialogView.startAnimation(this.mModalOutAnim);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.cancel_button) {
            OnSweetClickListener onSweetClickListener = this.mCancelClickListener;
            if (onSweetClickListener != null) {
                onSweetClickListener.onClick(this);
            } else {
                dismissWithAnimation();
            }
        } else if (view.getId() == R.id.confirm_button) {
            OnSweetClickListener onSweetClickListener2 = this.mConfirmClickListener;
            if (onSweetClickListener2 != null) {
                onSweetClickListener2.onClick(this);
            } else {
                dismissWithAnimation();
            }
        } else if (view.getId() == R.id.neutral_button) {
            OnSweetClickListener onSweetClickListener3 = this.mNeutralClickListener;
            if (onSweetClickListener3 != null) {
                onSweetClickListener3.onClick(this);
            } else {
                dismissWithAnimation();
            }
        }
    }

    public ProgressHelper getProgressHelper() {
        return this.mProgressHelper;
    }
}
