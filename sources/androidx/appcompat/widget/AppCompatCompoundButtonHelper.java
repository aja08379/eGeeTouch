package androidx.appcompat.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.widget.CompoundButton;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.widget.CompoundButtonCompat;
/* loaded from: classes.dex */
class AppCompatCompoundButtonHelper {
    private ColorStateList mButtonTintList = null;
    private PorterDuff.Mode mButtonTintMode = null;
    private boolean mHasButtonTint = false;
    private boolean mHasButtonTintMode = false;
    private boolean mSkipNextApply;
    private final CompoundButton mView;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AppCompatCompoundButtonHelper(CompoundButton compoundButton) {
        this.mView = compoundButton;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0066 A[Catch: all -> 0x008e, TryCatch #1 {all -> 0x008e, blocks: (B:3:0x001f, B:5:0x0027, B:7:0x002f, B:11:0x0041, B:13:0x0049, B:15:0x0051, B:16:0x005e, B:18:0x0066, B:19:0x0071, B:21:0x0079), top: B:30:0x001f }] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0079 A[Catch: all -> 0x008e, TRY_LEAVE, TryCatch #1 {all -> 0x008e, blocks: (B:3:0x001f, B:5:0x0027, B:7:0x002f, B:11:0x0041, B:13:0x0049, B:15:0x0051, B:16:0x005e, B:18:0x0066, B:19:0x0071, B:21:0x0079), top: B:30:0x001f }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void loadFromAttributes(android.util.AttributeSet r11, int r12) {
        boolean r11;
        int r11;
        int r11;
        androidx.appcompat.widget.TintTypedArray r0 = androidx.appcompat.widget.TintTypedArray.obtainStyledAttributes(r10.mView.getContext(), r11, androidx.appcompat.R.styleable.CompoundButton, r12, 0);
        android.widget.CompoundButton r3 = r10.mView;
        androidx.core.view.ViewCompat.saveAttributeDataForStyleable(r3, r3.getContext(), androidx.appcompat.R.styleable.CompoundButton, r11, r0.getWrappedTypeArray(), r12, 0);
        try {
            if (r0.hasValue(androidx.appcompat.R.styleable.CompoundButton_buttonCompat) && (r11 = r0.getResourceId(androidx.appcompat.R.styleable.CompoundButton_buttonCompat, 0)) != 0) {
                try {
                    android.widget.CompoundButton r12 = r10.mView;
                    r12.setButtonDrawable(androidx.appcompat.content.res.AppCompatResources.getDrawable(r12.getContext(), r11));
                    r11 = true;
                } catch (android.content.res.Resources.NotFoundException unused) {
                }
                if (!r11 && r0.hasValue(androidx.appcompat.R.styleable.CompoundButton_android_button) && (r11 = r0.getResourceId(androidx.appcompat.R.styleable.CompoundButton_android_button, 0)) != 0) {
                    android.widget.CompoundButton r12 = r10.mView;
                    r12.setButtonDrawable(androidx.appcompat.content.res.AppCompatResources.getDrawable(r12.getContext(), r11));
                }
                if (r0.hasValue(androidx.appcompat.R.styleable.CompoundButton_buttonTint)) {
                    androidx.core.widget.CompoundButtonCompat.setButtonTintList(r10.mView, r0.getColorStateList(androidx.appcompat.R.styleable.CompoundButton_buttonTint));
                }
                if (r0.hasValue(androidx.appcompat.R.styleable.CompoundButton_buttonTintMode)) {
                    androidx.core.widget.CompoundButtonCompat.setButtonTintMode(r10.mView, androidx.appcompat.widget.DrawableUtils.parseTintMode(r0.getInt(androidx.appcompat.R.styleable.CompoundButton_buttonTintMode, -1), null));
                }
            }
            r11 = false;
            if (!r11) {
                android.widget.CompoundButton r12 = r10.mView;
                r12.setButtonDrawable(androidx.appcompat.content.res.AppCompatResources.getDrawable(r12.getContext(), r11));
            }
            if (r0.hasValue(androidx.appcompat.R.styleable.CompoundButton_buttonTint)) {
            }
            if (r0.hasValue(androidx.appcompat.R.styleable.CompoundButton_buttonTintMode)) {
            }
        } finally {
            r0.recycle();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setSupportButtonTintList(ColorStateList colorStateList) {
        this.mButtonTintList = colorStateList;
        this.mHasButtonTint = true;
        applyButtonTint();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ColorStateList getSupportButtonTintList() {
        return this.mButtonTintList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setSupportButtonTintMode(PorterDuff.Mode mode) {
        this.mButtonTintMode = mode;
        this.mHasButtonTintMode = true;
        applyButtonTint();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PorterDuff.Mode getSupportButtonTintMode() {
        return this.mButtonTintMode;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onSetButtonDrawable() {
        if (this.mSkipNextApply) {
            this.mSkipNextApply = false;
            return;
        }
        this.mSkipNextApply = true;
        applyButtonTint();
    }

    void applyButtonTint() {
        Drawable buttonDrawable = CompoundButtonCompat.getButtonDrawable(this.mView);
        if (buttonDrawable != null) {
            if (this.mHasButtonTint || this.mHasButtonTintMode) {
                Drawable mutate = DrawableCompat.wrap(buttonDrawable).mutate();
                if (this.mHasButtonTint) {
                    DrawableCompat.setTintList(mutate, this.mButtonTintList);
                }
                if (this.mHasButtonTintMode) {
                    DrawableCompat.setTintMode(mutate, this.mButtonTintMode);
                }
                if (mutate.isStateful()) {
                    mutate.setState(this.mView.getDrawableState());
                }
                this.mView.setButtonDrawable(mutate);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getCompoundPaddingLeft(int i) {
        Drawable buttonDrawable;
        return (Build.VERSION.SDK_INT >= 17 || (buttonDrawable = CompoundButtonCompat.getButtonDrawable(this.mView)) == null) ? i : i + buttonDrawable.getIntrinsicWidth();
    }
}
