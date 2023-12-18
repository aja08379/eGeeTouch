package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.FrameLayout;
import androidx.core.view.ViewCompat;
/* loaded from: classes.dex */
public class ContentFrameLayout extends FrameLayout {
    private OnAttachListener mAttachListener;
    private final Rect mDecorPadding;
    private TypedValue mFixedHeightMajor;
    private TypedValue mFixedHeightMinor;
    private TypedValue mFixedWidthMajor;
    private TypedValue mFixedWidthMinor;
    private TypedValue mMinWidthMajor;
    private TypedValue mMinWidthMinor;

    /* loaded from: classes.dex */
    public interface OnAttachListener {
        void onAttachedFromWindow();

        void onDetachedFromWindow();
    }

    public ContentFrameLayout(Context context) {
        this(context, null);
    }

    public ContentFrameLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ContentFrameLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mDecorPadding = new Rect();
    }

    public void dispatchFitSystemWindows(Rect rect) {
        fitSystemWindows(rect);
    }

    public void setAttachListener(OnAttachListener onAttachListener) {
        this.mAttachListener = onAttachListener;
    }

    public void setDecorPadding(int i, int i2, int i3, int i4) {
        this.mDecorPadding.set(i, i2, i3, i4);
        if (ViewCompat.isLaidOut(this)) {
            requestLayout();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00be  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00ca  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00d0  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00e2  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00ee  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00f6  */
    /* JADX WARN: Removed duplicated region for block: B:67:? A[RETURN, SYNTHETIC] */
    @Override // android.widget.FrameLayout, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void onMeasure(int r14, int r15) {
        boolean r10;
        int r14;
        android.util.TypedValue r1;
        int r0;
        float r0;
        int r5;
        float r5;
        int r10;
        float r10;
        android.util.DisplayMetrics r0 = getContext().getResources().getDisplayMetrics();
        boolean r3 = true;
        boolean r1 = r0.widthPixels < r0.heightPixels;
        int r2 = android.view.View.MeasureSpec.getMode(r14);
        int r5 = android.view.View.MeasureSpec.getMode(r15);
        if (r2 == Integer.MIN_VALUE) {
            android.util.TypedValue r10 = r1 ? r13.mFixedWidthMinor : r13.mFixedWidthMajor;
            if (r10 != null && r10.type != 0) {
                if (r10.type == 5) {
                    r10 = r10.getDimension(r0);
                } else if (r10.type == 6) {
                    r10 = r10.getFraction(r0.widthPixels, r0.widthPixels);
                } else {
                    r10 = 0;
                    if (r10 > 0) {
                        r14 = android.view.View.MeasureSpec.makeMeasureSpec(java.lang.Math.min(r10 - (r13.mDecorPadding.left + r13.mDecorPadding.right), android.view.View.MeasureSpec.getSize(r14)), androidx.constraintlayout.core.widgets.analyzer.BasicMeasure.EXACTLY);
                        r10 = true;
                        if (r5 == Integer.MIN_VALUE) {
                            android.util.TypedValue r5 = r1 ? r13.mFixedHeightMajor : r13.mFixedHeightMinor;
                            if (r5 != null && r5.type != 0) {
                                if (r5.type == 5) {
                                    r5 = r5.getDimension(r0);
                                } else if (r5.type == 6) {
                                    r5 = r5.getFraction(r0.heightPixels, r0.heightPixels);
                                } else {
                                    r5 = 0;
                                    if (r5 > 0) {
                                        r15 = android.view.View.MeasureSpec.makeMeasureSpec(java.lang.Math.min(r5 - (r13.mDecorPadding.top + r13.mDecorPadding.bottom), android.view.View.MeasureSpec.getSize(r15)), androidx.constraintlayout.core.widgets.analyzer.BasicMeasure.EXACTLY);
                                    }
                                }
                                r5 = (int) r5;
                                if (r5 > 0) {
                                }
                            }
                        }
                        super.onMeasure(r14, r15);
                        r14 = getMeasuredWidth();
                        int r5 = android.view.View.MeasureSpec.makeMeasureSpec(r14, androidx.constraintlayout.core.widgets.analyzer.BasicMeasure.EXACTLY);
                        if (!r10 && r2 == Integer.MIN_VALUE) {
                            r1 = !r1 ? r13.mMinWidthMinor : r13.mMinWidthMajor;
                            if (r1 != null && r1.type != 0) {
                                if (r1.type != 5) {
                                    r0 = r1.getDimension(r0);
                                } else if (r1.type == 6) {
                                    r0 = r1.getFraction(r0.widthPixels, r0.widthPixels);
                                } else {
                                    r0 = 0;
                                    if (r0 > 0) {
                                        r0 -= r13.mDecorPadding.left + r13.mDecorPadding.right;
                                    }
                                    if (r14 < r0) {
                                        r5 = android.view.View.MeasureSpec.makeMeasureSpec(r0, androidx.constraintlayout.core.widgets.analyzer.BasicMeasure.EXACTLY);
                                        if (r3) {
                                            super.onMeasure(r5, r15);
                                            return;
                                        }
                                        return;
                                    }
                                }
                                r0 = (int) r0;
                                if (r0 > 0) {
                                }
                                if (r14 < r0) {
                                }
                            }
                        }
                        r3 = false;
                        if (r3) {
                        }
                    }
                }
                r10 = (int) r10;
                if (r10 > 0) {
                }
            }
        }
        r10 = false;
        if (r5 == Integer.MIN_VALUE) {
        }
        super.onMeasure(r14, r15);
        r14 = getMeasuredWidth();
        int r5 = android.view.View.MeasureSpec.makeMeasureSpec(r14, androidx.constraintlayout.core.widgets.analyzer.BasicMeasure.EXACTLY);
        if (!r10) {
            if (!r1) {
            }
            if (r1 != null) {
                if (r1.type != 5) {
                }
                r0 = (int) r0;
                if (r0 > 0) {
                }
                if (r14 < r0) {
                }
            }
        }
        r3 = false;
        if (r3) {
        }
    }

    public TypedValue getMinWidthMajor() {
        if (this.mMinWidthMajor == null) {
            this.mMinWidthMajor = new TypedValue();
        }
        return this.mMinWidthMajor;
    }

    public TypedValue getMinWidthMinor() {
        if (this.mMinWidthMinor == null) {
            this.mMinWidthMinor = new TypedValue();
        }
        return this.mMinWidthMinor;
    }

    public TypedValue getFixedWidthMajor() {
        if (this.mFixedWidthMajor == null) {
            this.mFixedWidthMajor = new TypedValue();
        }
        return this.mFixedWidthMajor;
    }

    public TypedValue getFixedWidthMinor() {
        if (this.mFixedWidthMinor == null) {
            this.mFixedWidthMinor = new TypedValue();
        }
        return this.mFixedWidthMinor;
    }

    public TypedValue getFixedHeightMajor() {
        if (this.mFixedHeightMajor == null) {
            this.mFixedHeightMajor = new TypedValue();
        }
        return this.mFixedHeightMajor;
    }

    public TypedValue getFixedHeightMinor() {
        if (this.mFixedHeightMinor == null) {
            this.mFixedHeightMinor = new TypedValue();
        }
        return this.mFixedHeightMinor;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        OnAttachListener onAttachListener = this.mAttachListener;
        if (onAttachListener != null) {
            onAttachListener.onAttachedFromWindow();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        OnAttachListener onAttachListener = this.mAttachListener;
        if (onAttachListener != null) {
            onAttachListener.onDetachedFromWindow();
        }
    }
}
