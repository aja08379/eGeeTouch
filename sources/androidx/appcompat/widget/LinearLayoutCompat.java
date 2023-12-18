package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.LinearLayout;
import androidx.appcompat.R;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import com.google.android.material.badge.BadgeDrawable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* loaded from: classes.dex */
public class LinearLayoutCompat extends ViewGroup {
    private static final String ACCESSIBILITY_CLASS_NAME = "androidx.appcompat.widget.LinearLayoutCompat";
    public static final int HORIZONTAL = 0;
    private static final int INDEX_BOTTOM = 2;
    private static final int INDEX_CENTER_VERTICAL = 0;
    private static final int INDEX_FILL = 3;
    private static final int INDEX_TOP = 1;
    public static final int SHOW_DIVIDER_BEGINNING = 1;
    public static final int SHOW_DIVIDER_END = 4;
    public static final int SHOW_DIVIDER_MIDDLE = 2;
    public static final int SHOW_DIVIDER_NONE = 0;
    public static final int VERTICAL = 1;
    private static final int VERTICAL_GRAVITY_COUNT = 4;
    private boolean mBaselineAligned;
    private int mBaselineAlignedChildIndex;
    private int mBaselineChildTop;
    private Drawable mDivider;
    private int mDividerHeight;
    private int mDividerPadding;
    private int mDividerWidth;
    private int mGravity;
    private int[] mMaxAscent;
    private int[] mMaxDescent;
    private int mOrientation;
    private int mShowDividers;
    private int mTotalLength;
    private boolean mUseLargestChild;
    private float mWeightSum;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface DividerMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface OrientationMode {
    }

    int getChildrenSkipCount(View view, int i) {
        return 0;
    }

    int getLocationOffset(View view) {
        return 0;
    }

    int getNextLocationOffset(View view) {
        return 0;
    }

    int measureNullChild(int i) {
        return 0;
    }

    @Override // android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    public LinearLayoutCompat(Context context) {
        this(context, null);
    }

    public LinearLayoutCompat(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LinearLayoutCompat(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mBaselineAligned = true;
        this.mBaselineAlignedChildIndex = -1;
        this.mBaselineChildTop = 0;
        this.mGravity = BadgeDrawable.TOP_START;
        TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(context, attributeSet, R.styleable.LinearLayoutCompat, i, 0);
        ViewCompat.saveAttributeDataForStyleable(this, context, R.styleable.LinearLayoutCompat, attributeSet, obtainStyledAttributes.getWrappedTypeArray(), i, 0);
        int i2 = obtainStyledAttributes.getInt(R.styleable.LinearLayoutCompat_android_orientation, -1);
        if (i2 >= 0) {
            setOrientation(i2);
        }
        int i3 = obtainStyledAttributes.getInt(R.styleable.LinearLayoutCompat_android_gravity, -1);
        if (i3 >= 0) {
            setGravity(i3);
        }
        boolean z = obtainStyledAttributes.getBoolean(R.styleable.LinearLayoutCompat_android_baselineAligned, true);
        if (!z) {
            setBaselineAligned(z);
        }
        this.mWeightSum = obtainStyledAttributes.getFloat(R.styleable.LinearLayoutCompat_android_weightSum, -1.0f);
        this.mBaselineAlignedChildIndex = obtainStyledAttributes.getInt(R.styleable.LinearLayoutCompat_android_baselineAlignedChildIndex, -1);
        this.mUseLargestChild = obtainStyledAttributes.getBoolean(R.styleable.LinearLayoutCompat_measureWithLargestChild, false);
        setDividerDrawable(obtainStyledAttributes.getDrawable(R.styleable.LinearLayoutCompat_divider));
        this.mShowDividers = obtainStyledAttributes.getInt(R.styleable.LinearLayoutCompat_showDividers, 0);
        this.mDividerPadding = obtainStyledAttributes.getDimensionPixelSize(R.styleable.LinearLayoutCompat_dividerPadding, 0);
        obtainStyledAttributes.recycle();
    }

    public void setShowDividers(int i) {
        if (i != this.mShowDividers) {
            requestLayout();
        }
        this.mShowDividers = i;
    }

    public int getShowDividers() {
        return this.mShowDividers;
    }

    public Drawable getDividerDrawable() {
        return this.mDivider;
    }

    public void setDividerDrawable(Drawable drawable) {
        if (drawable == this.mDivider) {
            return;
        }
        this.mDivider = drawable;
        if (drawable != null) {
            this.mDividerWidth = drawable.getIntrinsicWidth();
            this.mDividerHeight = drawable.getIntrinsicHeight();
        } else {
            this.mDividerWidth = 0;
            this.mDividerHeight = 0;
        }
        setWillNotDraw(drawable == null);
        requestLayout();
    }

    public void setDividerPadding(int i) {
        this.mDividerPadding = i;
    }

    public int getDividerPadding() {
        return this.mDividerPadding;
    }

    public int getDividerWidth() {
        return this.mDividerWidth;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        if (this.mDivider == null) {
            return;
        }
        if (this.mOrientation == 1) {
            drawDividersVertical(canvas);
        } else {
            drawDividersHorizontal(canvas);
        }
    }

    void drawDividersVertical(Canvas canvas) {
        int bottom;
        int virtualChildCount = getVirtualChildCount();
        for (int i = 0; i < virtualChildCount; i++) {
            View virtualChildAt = getVirtualChildAt(i);
            if (virtualChildAt != null && virtualChildAt.getVisibility() != 8 && hasDividerBeforeChildAt(i)) {
                drawHorizontalDivider(canvas, (virtualChildAt.getTop() - ((LayoutParams) virtualChildAt.getLayoutParams()).topMargin) - this.mDividerHeight);
            }
        }
        if (hasDividerBeforeChildAt(virtualChildCount)) {
            View virtualChildAt2 = getVirtualChildAt(virtualChildCount - 1);
            if (virtualChildAt2 == null) {
                bottom = (getHeight() - getPaddingBottom()) - this.mDividerHeight;
            } else {
                bottom = virtualChildAt2.getBottom() + ((LayoutParams) virtualChildAt2.getLayoutParams()).bottomMargin;
            }
            drawHorizontalDivider(canvas, bottom);
        }
    }

    void drawDividersHorizontal(Canvas canvas) {
        int right;
        int left;
        int i;
        int left2;
        int virtualChildCount = getVirtualChildCount();
        boolean isLayoutRtl = ViewUtils.isLayoutRtl(this);
        for (int i2 = 0; i2 < virtualChildCount; i2++) {
            View virtualChildAt = getVirtualChildAt(i2);
            if (virtualChildAt != null && virtualChildAt.getVisibility() != 8 && hasDividerBeforeChildAt(i2)) {
                LayoutParams layoutParams = (LayoutParams) virtualChildAt.getLayoutParams();
                if (isLayoutRtl) {
                    left2 = virtualChildAt.getRight() + layoutParams.rightMargin;
                } else {
                    left2 = (virtualChildAt.getLeft() - layoutParams.leftMargin) - this.mDividerWidth;
                }
                drawVerticalDivider(canvas, left2);
            }
        }
        if (hasDividerBeforeChildAt(virtualChildCount)) {
            View virtualChildAt2 = getVirtualChildAt(virtualChildCount - 1);
            if (virtualChildAt2 != null) {
                LayoutParams layoutParams2 = (LayoutParams) virtualChildAt2.getLayoutParams();
                if (isLayoutRtl) {
                    left = virtualChildAt2.getLeft() - layoutParams2.leftMargin;
                    i = this.mDividerWidth;
                    right = left - i;
                } else {
                    right = virtualChildAt2.getRight() + layoutParams2.rightMargin;
                }
            } else if (isLayoutRtl) {
                right = getPaddingLeft();
            } else {
                left = getWidth() - getPaddingRight();
                i = this.mDividerWidth;
                right = left - i;
            }
            drawVerticalDivider(canvas, right);
        }
    }

    void drawHorizontalDivider(Canvas canvas, int i) {
        this.mDivider.setBounds(getPaddingLeft() + this.mDividerPadding, i, (getWidth() - getPaddingRight()) - this.mDividerPadding, this.mDividerHeight + i);
        this.mDivider.draw(canvas);
    }

    void drawVerticalDivider(Canvas canvas, int i) {
        this.mDivider.setBounds(i, getPaddingTop() + this.mDividerPadding, this.mDividerWidth + i, (getHeight() - getPaddingBottom()) - this.mDividerPadding);
        this.mDivider.draw(canvas);
    }

    public boolean isBaselineAligned() {
        return this.mBaselineAligned;
    }

    public void setBaselineAligned(boolean z) {
        this.mBaselineAligned = z;
    }

    public boolean isMeasureWithLargestChildEnabled() {
        return this.mUseLargestChild;
    }

    public void setMeasureWithLargestChildEnabled(boolean z) {
        this.mUseLargestChild = z;
    }

    @Override // android.view.View
    public int getBaseline() {
        int i;
        if (this.mBaselineAlignedChildIndex < 0) {
            return super.getBaseline();
        }
        int childCount = getChildCount();
        int i2 = this.mBaselineAlignedChildIndex;
        if (childCount <= i2) {
            throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout set to an index that is out of bounds.");
        }
        View childAt = getChildAt(i2);
        int baseline = childAt.getBaseline();
        if (baseline == -1) {
            if (this.mBaselineAlignedChildIndex == 0) {
                return -1;
            }
            throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout points to a View that doesn't know how to get its baseline.");
        }
        int i3 = this.mBaselineChildTop;
        if (this.mOrientation == 1 && (i = this.mGravity & 112) != 48) {
            if (i == 16) {
                i3 += ((((getBottom() - getTop()) - getPaddingTop()) - getPaddingBottom()) - this.mTotalLength) / 2;
            } else if (i == 80) {
                i3 = ((getBottom() - getTop()) - getPaddingBottom()) - this.mTotalLength;
            }
        }
        return i3 + ((LayoutParams) childAt.getLayoutParams()).topMargin + baseline;
    }

    public int getBaselineAlignedChildIndex() {
        return this.mBaselineAlignedChildIndex;
    }

    public void setBaselineAlignedChildIndex(int i) {
        if (i < 0 || i >= getChildCount()) {
            throw new IllegalArgumentException("base aligned child index out of range (0, " + getChildCount() + ")");
        }
        this.mBaselineAlignedChildIndex = i;
    }

    View getVirtualChildAt(int i) {
        return getChildAt(i);
    }

    int getVirtualChildCount() {
        return getChildCount();
    }

    public float getWeightSum() {
        return this.mWeightSum;
    }

    public void setWeightSum(float f) {
        this.mWeightSum = Math.max(0.0f, f);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        if (this.mOrientation == 1) {
            measureVertical(i, i2);
        } else {
            measureHorizontal(i, i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean hasDividerBeforeChildAt(int i) {
        if (i == 0) {
            return (this.mShowDividers & 1) != 0;
        } else if (i == getChildCount()) {
            return (this.mShowDividers & 4) != 0;
        } else if ((this.mShowDividers & 2) != 0) {
            for (int i2 = i - 1; i2 >= 0; i2--) {
                if (getChildAt(i2).getVisibility() != 8) {
                    return true;
                }
            }
            return false;
        } else {
            return false;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:150:0x032b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    void measureVertical(int r34, int r35) {
        int r2;
        int r1;
        int r11;
        int r0;
        int r21;
        int r23;
        int r1;
        boolean r0;
        int r10;
        int r2;
        int r8;
        int r29;
        int r24;
        int r13;
        int r23;
        int r31;
        int r11;
        android.view.View r3;
        int r0;
        boolean r1;
        int r1;
        r33.mTotalLength = 0;
        int r11 = getVirtualChildCount();
        int r12 = android.view.View.MeasureSpec.getMode(r34);
        int r13 = android.view.View.MeasureSpec.getMode(r35);
        int r14 = r33.mBaselineAlignedChildIndex;
        boolean r15 = r33.mUseLargestChild;
        int r1 = 0;
        int r2 = 0;
        int r3 = 0;
        int r4 = 0;
        int r5 = 0;
        int r6 = 0;
        boolean r18 = false;
        boolean r20 = false;
        float r0 = 0.0f;
        boolean r19 = true;
        while (true) {
            int r10 = 8;
            int r22 = r4;
            if (r6 < r11) {
                android.view.View r4 = getVirtualChildAt(r6);
                if (r4 == null) {
                    r33.mTotalLength += measureNullChild(r6);
                    r23 = r11;
                    r24 = r13;
                    r4 = r22;
                } else {
                    int r24 = r1;
                    if (r4.getVisibility() == 8) {
                        r6 += getChildrenSkipCount(r4, r6);
                        r23 = r11;
                        r4 = r22;
                        r1 = r24;
                        r24 = r13;
                    } else {
                        if (hasDividerBeforeChildAt(r6)) {
                            r33.mTotalLength += r33.mDividerHeight;
                        }
                        androidx.appcompat.widget.LinearLayoutCompat.LayoutParams r10 = (androidx.appcompat.widget.LinearLayoutCompat.LayoutParams) r4.getLayoutParams();
                        float r25 = r0 + r10.weight;
                        if (r13 == 1073741824 && r10.height == 0 && r10.weight > 0.0f) {
                            int r0 = r33.mTotalLength;
                            r33.mTotalLength = java.lang.Math.max(r0, r10.topMargin + r0 + r10.bottomMargin);
                            r0 = r3;
                            r3 = r4;
                            r31 = r5;
                            r23 = r11;
                            r18 = true;
                            r8 = r24;
                            r29 = r2;
                            r11 = r6;
                            r24 = r13;
                            r13 = r22;
                        } else {
                            int r26 = r2;
                            if (r10.height != 0 || r10.weight <= 0.0f) {
                                r2 = Integer.MIN_VALUE;
                            } else {
                                r10.height = -2;
                                r2 = 0;
                            }
                            r8 = r24;
                            int r30 = r2;
                            r29 = r26;
                            int r9 = r3;
                            r24 = r13;
                            r13 = r22;
                            r23 = r11;
                            r31 = r5;
                            r11 = r6;
                            measureChildBeforeLayout(r4, r6, r34, 0, r35, r25 == 0.0f ? r33.mTotalLength : 0);
                            if (r30 != Integer.MIN_VALUE) {
                                r10.height = r30;
                            }
                            int r0 = r4.getMeasuredHeight();
                            int r1 = r33.mTotalLength;
                            r3 = r4;
                            r33.mTotalLength = java.lang.Math.max(r1, r1 + r0 + r10.topMargin + r10.bottomMargin + getNextLocationOffset(r3));
                            r0 = r15 ? java.lang.Math.max(r0, r9) : r9;
                        }
                        if (r14 >= 0 && r14 == r11 + 1) {
                            r33.mBaselineChildTop = r33.mTotalLength;
                        }
                        if (r11 < r14 && r10.weight > 0.0f) {
                            throw new java.lang.RuntimeException("A child of LinearLayout with index less than mBaselineAlignedChildIndex has weight > 0, which won't work.  Either remove the weight, or don't set mBaselineAlignedChildIndex.");
                        }
                        if (r12 == 1073741824 || r10.width != -1) {
                            r1 = false;
                        } else {
                            r1 = true;
                            r20 = true;
                        }
                        int r2 = r10.leftMargin + r10.rightMargin;
                        int r4 = r3.getMeasuredWidth() + r2;
                        int r5 = java.lang.Math.max(r29, r4);
                        int r6 = android.view.View.combineMeasuredStates(r8, r3.getMeasuredState());
                        r19 = r19 && r10.width == -1;
                        if (r10.weight > 0.0f) {
                            if (!r1) {
                                r2 = r4;
                            }
                            r4 = java.lang.Math.max(r13, r2);
                            r1 = r31;
                        } else {
                            if (!r1) {
                                r2 = r4;
                            }
                            r1 = java.lang.Math.max(r31, r2);
                            r4 = r13;
                        }
                        r3 = r0;
                        r0 = r25;
                        r5 = r1;
                        r1 = r6;
                        r6 = getChildrenSkipCount(r3, r11) + r11;
                        r2 = r5;
                    }
                }
                r6++;
                r11 = r23;
                r13 = r24;
            } else {
                int r8 = r1;
                int r9 = r3;
                int r1 = r5;
                int r23 = r11;
                int r24 = r13;
                int r5 = r2;
                if (r33.mTotalLength > 0) {
                    r2 = r23;
                    if (hasDividerBeforeChildAt(r2)) {
                        r33.mTotalLength += r33.mDividerHeight;
                    }
                } else {
                    r2 = r23;
                }
                if (r15 && (r24 == Integer.MIN_VALUE || r24 == 0)) {
                    r33.mTotalLength = 0;
                    int r4 = 0;
                    while (r4 < r2) {
                        android.view.View r6 = getVirtualChildAt(r4);
                        if (r6 == null) {
                            r33.mTotalLength += measureNullChild(r4);
                        } else if (r6.getVisibility() == r10) {
                            r4 += getChildrenSkipCount(r6, r4);
                        } else {
                            androidx.appcompat.widget.LinearLayoutCompat.LayoutParams r11 = (androidx.appcompat.widget.LinearLayoutCompat.LayoutParams) r6.getLayoutParams();
                            int r14 = r33.mTotalLength;
                            r33.mTotalLength = java.lang.Math.max(r14, r14 + r9 + r11.topMargin + r11.bottomMargin + getNextLocationOffset(r6));
                        }
                        r4++;
                        r10 = 8;
                    }
                }
                int r4 = r33.mTotalLength + getPaddingTop() + getPaddingBottom();
                r33.mTotalLength = r4;
                int r4 = android.view.View.resolveSizeAndState(java.lang.Math.max(r4, getSuggestedMinimumHeight()), r35, 0);
                int r9 = (16777215 & r4) - r33.mTotalLength;
                if (r18 || (r9 != 0 && r0 > 0.0f)) {
                    float r10 = r33.mWeightSum;
                    if (r10 > 0.0f) {
                        r0 = r10;
                    }
                    r33.mTotalLength = 0;
                    int r11 = r9;
                    int r9 = r1;
                    r1 = r8;
                    int r8 = 0;
                    while (r8 < r2) {
                        android.view.View r13 = getVirtualChildAt(r8);
                        if (r13.getVisibility() == 8) {
                            r21 = r11;
                        } else {
                            androidx.appcompat.widget.LinearLayoutCompat.LayoutParams r14 = (androidx.appcompat.widget.LinearLayoutCompat.LayoutParams) r13.getLayoutParams();
                            float r10 = r14.weight;
                            if (r10 > 0.0f) {
                                int r15 = (int) ((r11 * r10) / r0);
                                float r0 = r0 - r10;
                                r21 = r11 - r15;
                                int r0 = getChildMeasureSpec(r34, getPaddingLeft() + getPaddingRight() + r14.leftMargin + r14.rightMargin, r14.width);
                                if (r14.height == 0) {
                                    r10 = androidx.constraintlayout.core.widgets.analyzer.BasicMeasure.EXACTLY;
                                    if (r24 == 1073741824) {
                                        if (r15 <= 0) {
                                            r15 = 0;
                                        }
                                        r13.measure(r0, android.view.View.MeasureSpec.makeMeasureSpec(r15, androidx.constraintlayout.core.widgets.analyzer.BasicMeasure.EXACTLY));
                                        r1 = android.view.View.combineMeasuredStates(r1, r13.getMeasuredState() & androidx.core.view.InputDeviceCompat.SOURCE_ANY);
                                        r0 = r0;
                                    }
                                } else {
                                    r10 = androidx.constraintlayout.core.widgets.analyzer.BasicMeasure.EXACTLY;
                                }
                                int r15 = r13.getMeasuredHeight() + r15;
                                if (r15 < 0) {
                                    r15 = 0;
                                }
                                r13.measure(r0, android.view.View.MeasureSpec.makeMeasureSpec(r15, r10));
                                r1 = android.view.View.combineMeasuredStates(r1, r13.getMeasuredState() & androidx.core.view.InputDeviceCompat.SOURCE_ANY);
                                r0 = r0;
                            } else {
                                r21 = r11;
                            }
                            int r10 = r14.leftMargin + r14.rightMargin;
                            int r15 = r13.getMeasuredWidth() + r10;
                            r5 = java.lang.Math.max(r5, r15);
                            float r18 = r0;
                            if (r12 != 1073741824) {
                                r23 = r1;
                                r1 = -1;
                                if (r14.width == -1) {
                                    r0 = true;
                                    if (!r0) {
                                        r10 = r15;
                                    }
                                    int r0 = java.lang.Math.max(r9, r10);
                                    boolean r9 = !r19 && r14.width == r1;
                                    int r10 = r33.mTotalLength;
                                    r33.mTotalLength = java.lang.Math.max(r10, r13.getMeasuredHeight() + r10 + r14.topMargin + r14.bottomMargin + getNextLocationOffset(r13));
                                    r19 = r9;
                                    r1 = r23;
                                    r9 = r0;
                                    r0 = r18;
                                }
                            } else {
                                r23 = r1;
                                r1 = -1;
                            }
                            r0 = false;
                            if (!r0) {
                            }
                            int r0 = java.lang.Math.max(r9, r10);
                            if (r19) {
                            }
                            int r10 = r33.mTotalLength;
                            r33.mTotalLength = java.lang.Math.max(r10, r13.getMeasuredHeight() + r10 + r14.topMargin + r14.bottomMargin + getNextLocationOffset(r13));
                            r19 = r9;
                            r1 = r23;
                            r9 = r0;
                            r0 = r18;
                        }
                        r8++;
                        r11 = r21;
                    }
                    r11 = r34;
                    r33.mTotalLength += getPaddingTop() + getPaddingBottom();
                    r0 = r9;
                } else {
                    r0 = java.lang.Math.max(r1, r22);
                    if (r15 && r24 != 1073741824) {
                        for (int r1 = 0; r1 < r2; r1++) {
                            android.view.View r3 = getVirtualChildAt(r1);
                            if (r3 != null && r3.getVisibility() != 8 && ((androidx.appcompat.widget.LinearLayoutCompat.LayoutParams) r3.getLayoutParams()).weight > 0.0f) {
                                r3.measure(android.view.View.MeasureSpec.makeMeasureSpec(r3.getMeasuredWidth(), androidx.constraintlayout.core.widgets.analyzer.BasicMeasure.EXACTLY), android.view.View.MeasureSpec.makeMeasureSpec(r9, androidx.constraintlayout.core.widgets.analyzer.BasicMeasure.EXACTLY));
                            }
                        }
                    }
                    r11 = r34;
                    r1 = r8;
                }
                if (r19 || r12 == 1073741824) {
                    r0 = r5;
                }
                setMeasuredDimension(android.view.View.resolveSizeAndState(java.lang.Math.max(r0 + getPaddingLeft() + getPaddingRight(), getSuggestedMinimumWidth()), r11, r1), r4);
                if (r20) {
                    forceUniformWidth(r2, r35);
                    return;
                }
                return;
            }
        }
    }

    private void forceUniformWidth(int i, int i2) {
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), BasicMeasure.EXACTLY);
        for (int i3 = 0; i3 < i; i3++) {
            View virtualChildAt = getVirtualChildAt(i3);
            if (virtualChildAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) virtualChildAt.getLayoutParams();
                if (layoutParams.width == -1) {
                    int i4 = layoutParams.height;
                    layoutParams.height = virtualChildAt.getMeasuredHeight();
                    measureChildWithMargins(virtualChildAt, makeMeasureSpec, 0, i2, 0);
                    layoutParams.height = i4;
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:198:0x045a  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x045d  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x019d  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x01a0  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x01d4  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x01df  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    void measureHorizontal(int r39, int r40) {
        int[] r28;
        int r23;
        int r1;
        int r2;
        int r25;
        int r3;
        int r0;
        int r3;
        int r25;
        float r23;
        int r8;
        boolean r6;
        int r14;
        int r3;
        int r22;
        char r5;
        int r2;
        int r34;
        boolean r32;
        boolean r36;
        android.view.View r3;
        int r1;
        boolean r0;
        int r4;
        int r2;
        int r5;
        r38.mTotalLength = 0;
        int r11 = getVirtualChildCount();
        int r12 = android.view.View.MeasureSpec.getMode(r39);
        int r13 = android.view.View.MeasureSpec.getMode(r40);
        if (r38.mMaxAscent == null || r38.mMaxDescent == null) {
            r38.mMaxAscent = new int[4];
            r38.mMaxDescent = new int[4];
        }
        int[] r15 = r38.mMaxAscent;
        int[] r6 = r38.mMaxDescent;
        r15[3] = -1;
        r15[2] = -1;
        r15[1] = -1;
        r15[0] = -1;
        r6[3] = -1;
        r6[2] = -1;
        r6[1] = -1;
        r6[0] = -1;
        boolean r4 = r38.mBaselineAligned;
        boolean r3 = r38.mUseLargestChild;
        int r2 = androidx.constraintlayout.core.widgets.analyzer.BasicMeasure.EXACTLY;
        boolean r19 = r12 == 1073741824;
        int r1 = 0;
        int r14 = 0;
        int r21 = 0;
        int r22 = 0;
        int r23 = 0;
        boolean r24 = false;
        int r25 = 0;
        boolean r27 = false;
        boolean r26 = true;
        float r0 = 0.0f;
        while (true) {
            r28 = r6;
            if (r1 >= r11) {
                break;
            }
            android.view.View r6 = getVirtualChildAt(r1);
            if (r6 == null) {
                r38.mTotalLength += measureNullChild(r1);
            } else if (r6.getVisibility() == 8) {
                r1 += getChildrenSkipCount(r6, r1);
            } else {
                if (hasDividerBeforeChildAt(r1)) {
                    r38.mTotalLength += r38.mDividerWidth;
                }
                androidx.appcompat.widget.LinearLayoutCompat.LayoutParams r10 = (androidx.appcompat.widget.LinearLayoutCompat.LayoutParams) r6.getLayoutParams();
                float r31 = r0 + r10.weight;
                if (r12 == r2 && r10.width == 0 && r10.weight > 0.0f) {
                    if (r19) {
                        r38.mTotalLength += r10.leftMargin + r10.rightMargin;
                    } else {
                        int r0 = r38.mTotalLength;
                        r38.mTotalLength = java.lang.Math.max(r0, r10.leftMargin + r0 + r10.rightMargin);
                    }
                    if (r4) {
                        int r2 = android.view.View.MeasureSpec.makeMeasureSpec(0, 0);
                        r6.measure(r2, r2);
                        r34 = r1;
                        r32 = r3;
                        r36 = r4;
                        r3 = r6;
                    } else {
                        r34 = r1;
                        r32 = r3;
                        r36 = r4;
                        r3 = r6;
                        r24 = true;
                        r1 = androidx.constraintlayout.core.widgets.analyzer.BasicMeasure.EXACTLY;
                        if (r13 == r1 && r10.height == -1) {
                            r0 = true;
                            r27 = true;
                        } else {
                            r0 = false;
                        }
                        int r2 = r10.topMargin + r10.bottomMargin;
                        r4 = r3.getMeasuredHeight() + r2;
                        r25 = android.view.View.combineMeasuredStates(r25, r3.getMeasuredState());
                        if (r36 && (r5 = r3.getBaseline()) != -1) {
                            int r6 = ((((r10.gravity >= 0 ? r38.mGravity : r10.gravity) & 112) >> 4) & (-2)) >> 1;
                            r15[r6] = java.lang.Math.max(r15[r6], r5);
                            r28[r6] = java.lang.Math.max(r28[r6], r4 - r5);
                        }
                        r21 = java.lang.Math.max(r21, r4);
                        r26 = !r26 && r10.height == -1;
                        if (r10.weight <= 0.0f) {
                            if (!r0) {
                                r2 = r4;
                            }
                            r23 = java.lang.Math.max(r23, r2);
                        } else {
                            int r10 = r23;
                            if (!r0) {
                                r2 = r4;
                            }
                            r22 = java.lang.Math.max(r22, r2);
                            r23 = r10;
                        }
                        int r10 = r34;
                        r2 = getChildrenSkipCount(r3, r10) + r10;
                        r0 = r31;
                        int r2 = r2 + 1;
                        r6 = r28;
                        r3 = r32;
                        r4 = r36;
                        r2 = r1;
                        r1 = r2;
                    }
                } else {
                    if (r10.width != 0 || r10.weight <= 0.0f) {
                        r5 = 65534;
                        r2 = Integer.MIN_VALUE;
                    } else {
                        r5 = 65534;
                        r10.width = -2;
                        r2 = 0;
                    }
                    r34 = r1;
                    int r35 = r2;
                    r32 = r3;
                    r36 = r4;
                    measureChildBeforeLayout(r6, r34, r39, r31 == 0.0f ? r38.mTotalLength : 0, r40, 0);
                    if (r35 != Integer.MIN_VALUE) {
                        r10.width = r35;
                    }
                    int r0 = r6.getMeasuredWidth();
                    if (r19) {
                        r3 = r6;
                        r38.mTotalLength += r10.leftMargin + r0 + r10.rightMargin + getNextLocationOffset(r3);
                    } else {
                        r3 = r6;
                        int r1 = r38.mTotalLength;
                        r38.mTotalLength = java.lang.Math.max(r1, r1 + r0 + r10.leftMargin + r10.rightMargin + getNextLocationOffset(r3));
                    }
                    if (r32) {
                        r14 = java.lang.Math.max(r0, r14);
                    }
                }
                r1 = androidx.constraintlayout.core.widgets.analyzer.BasicMeasure.EXACTLY;
                if (r13 == r1) {
                }
                r0 = false;
                int r2 = r10.topMargin + r10.bottomMargin;
                r4 = r3.getMeasuredHeight() + r2;
                r25 = android.view.View.combineMeasuredStates(r25, r3.getMeasuredState());
                if (r36) {
                    int r6 = ((((r10.gravity >= 0 ? r38.mGravity : r10.gravity) & 112) >> 4) & (-2)) >> 1;
                    r15[r6] = java.lang.Math.max(r15[r6], r5);
                    r28[r6] = java.lang.Math.max(r28[r6], r4 - r5);
                }
                r21 = java.lang.Math.max(r21, r4);
                if (r26) {
                }
                if (r10.weight <= 0.0f) {
                }
                int r10 = r34;
                r2 = getChildrenSkipCount(r3, r10) + r10;
                r0 = r31;
                int r2 = r2 + 1;
                r6 = r28;
                r3 = r32;
                r4 = r36;
                r2 = r1;
                r1 = r2;
            }
            r32 = r3;
            r36 = r4;
            int r37 = r2;
            r2 = r1;
            r1 = r37;
            int r2 = r2 + 1;
            r6 = r28;
            r3 = r32;
            r4 = r36;
            r2 = r1;
            r1 = r2;
        }
        boolean r32 = r3;
        boolean r36 = r4;
        int r2 = r21;
        int r4 = r22;
        int r10 = r23;
        int r6 = r25;
        if (r38.mTotalLength > 0 && hasDividerBeforeChildAt(r11)) {
            r38.mTotalLength += r38.mDividerWidth;
        }
        if (r15[1] == -1 && r15[0] == -1 && r15[2] == -1 && r15[3] == -1) {
            r1 = r2;
            r23 = r6;
        } else {
            r23 = r6;
            r1 = java.lang.Math.max(r2, java.lang.Math.max(r15[3], java.lang.Math.max(r15[0], java.lang.Math.max(r15[1], r15[2]))) + java.lang.Math.max(r28[3], java.lang.Math.max(r28[0], java.lang.Math.max(r28[1], r28[2]))));
        }
        if (r32 && (r12 == Integer.MIN_VALUE || r12 == 0)) {
            r38.mTotalLength = 0;
            int r2 = 0;
            while (r2 < r11) {
                android.view.View r3 = getVirtualChildAt(r2);
                if (r3 == null) {
                    r38.mTotalLength += measureNullChild(r2);
                } else if (r3.getVisibility() == 8) {
                    r2 += getChildrenSkipCount(r3, r2);
                } else {
                    androidx.appcompat.widget.LinearLayoutCompat.LayoutParams r5 = (androidx.appcompat.widget.LinearLayoutCompat.LayoutParams) r3.getLayoutParams();
                    if (r19) {
                        r38.mTotalLength += r5.leftMargin + r14 + r5.rightMargin + getNextLocationOffset(r3);
                    } else {
                        int r6 = r38.mTotalLength;
                        r22 = r1;
                        r38.mTotalLength = java.lang.Math.max(r6, r6 + r14 + r5.leftMargin + r5.rightMargin + getNextLocationOffset(r3));
                        r2++;
                        r1 = r22;
                    }
                }
                r22 = r1;
                r2++;
                r1 = r22;
            }
        }
        int r22 = r1;
        int r1 = r38.mTotalLength + getPaddingLeft() + getPaddingRight();
        r38.mTotalLength = r1;
        int r1 = android.view.View.resolveSizeAndState(java.lang.Math.max(r1, getSuggestedMinimumWidth()), r39, 0);
        int r2 = (16777215 & r1) - r38.mTotalLength;
        if (r24 || (r2 != 0 && r0 > 0.0f)) {
            float r5 = r38.mWeightSum;
            if (r5 > 0.0f) {
                r0 = r5;
            }
            r15[3] = -1;
            r15[2] = -1;
            r15[1] = -1;
            r15[0] = -1;
            r28[3] = -1;
            r28[2] = -1;
            r28[1] = -1;
            r28[0] = -1;
            r38.mTotalLength = 0;
            int r6 = r4;
            int r4 = -1;
            int r9 = r23;
            int r10 = 0;
            while (r10 < r11) {
                android.view.View r14 = getVirtualChildAt(r10);
                if (r14 == null || r14.getVisibility() == 8) {
                    r3 = r2;
                    r25 = r11;
                } else {
                    androidx.appcompat.widget.LinearLayoutCompat.LayoutParams r5 = (androidx.appcompat.widget.LinearLayoutCompat.LayoutParams) r14.getLayoutParams();
                    float r3 = r5.weight;
                    if (r3 > 0.0f) {
                        int r8 = (int) ((r2 * r3) / r0);
                        float r0 = r0 - r3;
                        int r2 = r2 - r8;
                        r25 = r11;
                        int r0 = getChildMeasureSpec(r40, getPaddingTop() + getPaddingBottom() + r5.topMargin + r5.bottomMargin, r5.height);
                        if (r5.width == 0) {
                            r3 = androidx.constraintlayout.core.widgets.analyzer.BasicMeasure.EXACTLY;
                            if (r12 == 1073741824) {
                                if (r8 <= 0) {
                                    r8 = 0;
                                }
                                r14.measure(android.view.View.MeasureSpec.makeMeasureSpec(r8, androidx.constraintlayout.core.widgets.analyzer.BasicMeasure.EXACTLY), r0);
                                r9 = android.view.View.combineMeasuredStates(r9, r14.getMeasuredState() & (-16777216));
                                r0 = r0;
                                r3 = r2;
                            }
                        } else {
                            r3 = androidx.constraintlayout.core.widgets.analyzer.BasicMeasure.EXACTLY;
                        }
                        int r8 = r14.getMeasuredWidth() + r8;
                        if (r8 < 0) {
                            r8 = 0;
                        }
                        r14.measure(android.view.View.MeasureSpec.makeMeasureSpec(r8, r3), r0);
                        r9 = android.view.View.combineMeasuredStates(r9, r14.getMeasuredState() & (-16777216));
                        r0 = r0;
                        r3 = r2;
                    } else {
                        r3 = r2;
                        r25 = r11;
                    }
                    if (r19) {
                        r38.mTotalLength += r14.getMeasuredWidth() + r5.leftMargin + r5.rightMargin + getNextLocationOffset(r14);
                        r23 = r0;
                    } else {
                        int r8 = r38.mTotalLength;
                        r23 = r0;
                        r38.mTotalLength = java.lang.Math.max(r8, r14.getMeasuredWidth() + r8 + r5.leftMargin + r5.rightMargin + getNextLocationOffset(r14));
                    }
                    boolean r0 = r13 != 1073741824 && r5.height == -1;
                    int r8 = r5.topMargin + r5.bottomMargin;
                    int r11 = r14.getMeasuredHeight() + r8;
                    r4 = java.lang.Math.max(r4, r11);
                    if (!r0) {
                        r8 = r11;
                    }
                    int r0 = java.lang.Math.max(r6, r8);
                    if (r26) {
                        r8 = -1;
                        if (r5.height == -1) {
                            r6 = true;
                            if (r36 && (r14 = r14.getBaseline()) != r8) {
                                int r5 = ((((r5.gravity >= 0 ? r38.mGravity : r5.gravity) & 112) >> 4) & (-2)) >> 1;
                                r15[r5] = java.lang.Math.max(r15[r5], r14);
                                r28[r5] = java.lang.Math.max(r28[r5], r11 - r14);
                            }
                            r26 = r6;
                            r6 = r0;
                            r0 = r23;
                        }
                    } else {
                        r8 = -1;
                    }
                    r6 = false;
                    if (r36) {
                        int r5 = ((((r5.gravity >= 0 ? r38.mGravity : r5.gravity) & 112) >> 4) & (-2)) >> 1;
                        r15[r5] = java.lang.Math.max(r15[r5], r14);
                        r28[r5] = java.lang.Math.max(r28[r5], r11 - r14);
                    }
                    r26 = r6;
                    r6 = r0;
                    r0 = r23;
                }
                r10++;
                r2 = r3;
                r11 = r25;
            }
            r2 = r40;
            r25 = r11;
            r38.mTotalLength += getPaddingLeft() + getPaddingRight();
            r3 = (r15[1] == -1 && r15[0] == -1 && r15[2] == -1 && r15[3] == -1) ? r4 : java.lang.Math.max(r4, java.lang.Math.max(r15[3], java.lang.Math.max(r15[0], java.lang.Math.max(r15[1], r15[2]))) + java.lang.Math.max(r28[3], java.lang.Math.max(r28[0], java.lang.Math.max(r28[1], r28[2]))));
            r0 = r6;
            r23 = r9;
        } else {
            r0 = java.lang.Math.max(r4, r10);
            if (r32 && r12 != 1073741824) {
                for (int r10 = 0; r10 < r11; r10++) {
                    android.view.View r2 = getVirtualChildAt(r10);
                    if (r2 != null && r2.getVisibility() != 8 && ((androidx.appcompat.widget.LinearLayoutCompat.LayoutParams) r2.getLayoutParams()).weight > 0.0f) {
                        r2.measure(android.view.View.MeasureSpec.makeMeasureSpec(r14, androidx.constraintlayout.core.widgets.analyzer.BasicMeasure.EXACTLY), android.view.View.MeasureSpec.makeMeasureSpec(r2.getMeasuredHeight(), androidx.constraintlayout.core.widgets.analyzer.BasicMeasure.EXACTLY));
                    }
                }
            }
            r2 = r40;
            r25 = r11;
            r3 = r22;
        }
        if (r26 || r13 == 1073741824) {
            r0 = r3;
        }
        setMeasuredDimension(r1 | (r23 & (-16777216)), android.view.View.resolveSizeAndState(java.lang.Math.max(r0 + getPaddingTop() + getPaddingBottom(), getSuggestedMinimumHeight()), r2, r23 << 16));
        if (r27) {
            forceUniformHeight(r25, r39);
        }
    }

    private void forceUniformHeight(int i, int i2) {
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), BasicMeasure.EXACTLY);
        for (int i3 = 0; i3 < i; i3++) {
            View virtualChildAt = getVirtualChildAt(i3);
            if (virtualChildAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) virtualChildAt.getLayoutParams();
                if (layoutParams.height == -1) {
                    int i4 = layoutParams.width;
                    layoutParams.width = virtualChildAt.getMeasuredWidth();
                    measureChildWithMargins(virtualChildAt, i2, 0, makeMeasureSpec, 0);
                    layoutParams.width = i4;
                }
            }
        }
    }

    void measureChildBeforeLayout(View view, int i, int i2, int i3, int i4, int i5) {
        measureChildWithMargins(view, i2, i3, i4, i5);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (this.mOrientation == 1) {
            layoutVertical(i, i2, i3, i4);
        } else {
            layoutHorizontal(i, i2, i3, i4);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x009f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    void layoutVertical(int r18, int r19, int r20, int r21) {
        int r0;
        int r1;
        int r2;
        int r1;
        int r1;
        int r7 = getPaddingLeft();
        int r0 = r20 - r18;
        int r8 = r0 - getPaddingRight();
        int r9 = (r0 - r7) - getPaddingRight();
        int r10 = getVirtualChildCount();
        int r0 = r17.mGravity;
        int r1 = r0 & 112;
        int r11 = r0 & androidx.core.view.GravityCompat.RELATIVE_HORIZONTAL_GRAVITY_MASK;
        if (r1 == 16) {
            r0 = getPaddingTop() + (((r21 - r19) - r17.mTotalLength) / 2);
        } else if (r1 == 80) {
            r0 = ((getPaddingTop() + r21) - r19) - r17.mTotalLength;
        } else {
            r0 = getPaddingTop();
        }
        int r12 = 0;
        while (r12 < r10) {
            android.view.View r13 = getVirtualChildAt(r12);
            if (r13 == null) {
                r0 += measureNullChild(r12);
            } else if (r13.getVisibility() != 8) {
                int r4 = r13.getMeasuredWidth();
                int r15 = r13.getMeasuredHeight();
                androidx.appcompat.widget.LinearLayoutCompat.LayoutParams r5 = (androidx.appcompat.widget.LinearLayoutCompat.LayoutParams) r13.getLayoutParams();
                int r1 = r5.gravity;
                if (r1 < 0) {
                    r1 = r11;
                }
                int r1 = androidx.core.view.GravityCompat.getAbsoluteGravity(r1, androidx.core.view.ViewCompat.getLayoutDirection(r17)) & 7;
                if (r1 == 1) {
                    r1 = ((r9 - r4) / 2) + r7 + r5.leftMargin;
                    r2 = r5.rightMargin;
                } else if (r1 == 5) {
                    r1 = r8 - r4;
                    r2 = r5.rightMargin;
                } else {
                    r1 = r5.leftMargin + r7;
                    int r2 = r1;
                    if (hasDividerBeforeChildAt(r12)) {
                        r0 += r17.mDividerHeight;
                    }
                    int r16 = r0 + r5.topMargin;
                    setChildFrame(r13, r2, r16 + getLocationOffset(r13), r4, r15);
                    r12 += getChildrenSkipCount(r13, r12);
                    r0 = r16 + r15 + r5.bottomMargin + getNextLocationOffset(r13);
                    r1 = 1;
                    r12 += r1;
                }
                r1 = r1 - r2;
                int r2 = r1;
                if (hasDividerBeforeChildAt(r12)) {
                }
                int r16 = r0 + r5.topMargin;
                setChildFrame(r13, r2, r16 + getLocationOffset(r13), r4, r15);
                r12 += getChildrenSkipCount(r13, r12);
                r0 = r16 + r15 + r5.bottomMargin + getNextLocationOffset(r13);
                r1 = 1;
                r12 += r1;
            }
            r1 = 1;
            r12 += r1;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00ff  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    void layoutHorizontal(int r25, int r26, int r27, int r28) {
        int r1;
        int r16;
        int r17;
        int r22;
        int r19;
        int r20;
        boolean r21;
        int r10;
        int r3;
        int r3;
        int r3;
        boolean r0 = androidx.appcompat.widget.ViewUtils.isLayoutRtl(r24);
        int r7 = getPaddingTop();
        int r1 = r28 - r26;
        int r8 = r1 - getPaddingBottom();
        int r9 = (r1 - r7) - getPaddingBottom();
        int r10 = getVirtualChildCount();
        int r1 = r24.mGravity;
        int r11 = r1 & 112;
        boolean r12 = r24.mBaselineAligned;
        int[] r13 = r24.mMaxAscent;
        int[] r14 = r24.mMaxDescent;
        int r1 = androidx.core.view.GravityCompat.getAbsoluteGravity(8388615 & r1, androidx.core.view.ViewCompat.getLayoutDirection(r24));
        boolean r5 = true;
        if (r1 == 1) {
            r1 = getPaddingLeft() + (((r27 - r25) - r24.mTotalLength) / 2);
        } else if (r1 == 5) {
            r1 = ((getPaddingLeft() + r27) - r25) - r24.mTotalLength;
        } else {
            r1 = getPaddingLeft();
        }
        if (r0) {
            r16 = r10 - 1;
            r17 = -1;
        } else {
            r16 = 0;
            r17 = 1;
        }
        int r3 = 0;
        while (r3 < r10) {
            int r2 = r16 + (r17 * r3);
            android.view.View r0 = getVirtualChildAt(r2);
            if (r0 == null) {
                r1 += measureNullChild(r2);
                r21 = r5;
                r22 = r7;
                r19 = r10;
                r20 = r11;
            } else if (r0.getVisibility() != 8) {
                int r15 = r0.getMeasuredWidth();
                int r5 = r0.getMeasuredHeight();
                androidx.appcompat.widget.LinearLayoutCompat.LayoutParams r4 = (androidx.appcompat.widget.LinearLayoutCompat.LayoutParams) r0.getLayoutParams();
                int r18 = r3;
                if (r12) {
                    r19 = r10;
                    if (r4.height != -1) {
                        r10 = r0.getBaseline();
                        r3 = r4.gravity;
                        if (r3 < 0) {
                            r3 = r11;
                        }
                        r3 = r3 & 112;
                        r20 = r11;
                        if (r3 == 16) {
                            if (r3 == 48) {
                                r3 = r4.topMargin + r7;
                                if (r10 != -1) {
                                    r21 = true;
                                    r3 += r13[1] - r10;
                                }
                            } else if (r3 != 80) {
                                r3 = r7;
                            } else {
                                r3 = (r8 - r5) - r4.bottomMargin;
                                if (r10 != -1) {
                                    r3 -= r14[2] - (r0.getMeasuredHeight() - r10);
                                }
                            }
                            r21 = true;
                        } else {
                            r21 = true;
                            r3 = ((((r9 - r5) / 2) + r7) + r4.topMargin) - r4.bottomMargin;
                        }
                        if (hasDividerBeforeChildAt(r2)) {
                            r1 += r24.mDividerWidth;
                        }
                        int r10 = r4.leftMargin + r1;
                        r22 = r7;
                        setChildFrame(r0, r10 + getLocationOffset(r0), r3, r15, r5);
                        r3 = r18 + getChildrenSkipCount(r0, r2);
                        r1 = r10 + r15 + r4.rightMargin + getNextLocationOffset(r0);
                        r3++;
                        r10 = r19;
                        r11 = r20;
                        r5 = r21;
                        r7 = r22;
                    }
                } else {
                    r19 = r10;
                }
                r10 = -1;
                r3 = r4.gravity;
                if (r3 < 0) {
                }
                r3 = r3 & 112;
                r20 = r11;
                if (r3 == 16) {
                }
                if (hasDividerBeforeChildAt(r2)) {
                }
                int r10 = r4.leftMargin + r1;
                r22 = r7;
                setChildFrame(r0, r10 + getLocationOffset(r0), r3, r15, r5);
                r3 = r18 + getChildrenSkipCount(r0, r2);
                r1 = r10 + r15 + r4.rightMargin + getNextLocationOffset(r0);
                r3++;
                r10 = r19;
                r11 = r20;
                r5 = r21;
                r7 = r22;
            } else {
                r22 = r7;
                r19 = r10;
                r20 = r11;
                r21 = true;
            }
            r3++;
            r10 = r19;
            r11 = r20;
            r5 = r21;
            r7 = r22;
        }
    }

    private void setChildFrame(View view, int i, int i2, int i3, int i4) {
        view.layout(i, i2, i3 + i, i4 + i2);
    }

    public void setOrientation(int i) {
        if (this.mOrientation != i) {
            this.mOrientation = i;
            requestLayout();
        }
    }

    public int getOrientation() {
        return this.mOrientation;
    }

    public void setGravity(int i) {
        if (this.mGravity != i) {
            if ((8388615 & i) == 0) {
                i |= GravityCompat.START;
            }
            if ((i & 112) == 0) {
                i |= 48;
            }
            this.mGravity = i;
            requestLayout();
        }
    }

    public int getGravity() {
        return this.mGravity;
    }

    public void setHorizontalGravity(int i) {
        int i2 = i & GravityCompat.RELATIVE_HORIZONTAL_GRAVITY_MASK;
        int i3 = this.mGravity;
        if ((8388615 & i3) != i2) {
            this.mGravity = i2 | ((-8388616) & i3);
            requestLayout();
        }
    }

    public void setVerticalGravity(int i) {
        int i2 = i & 112;
        int i3 = this.mGravity;
        if ((i3 & 112) != i2) {
            this.mGravity = i2 | (i3 & (-113));
            requestLayout();
        }
    }

    @Override // android.view.ViewGroup
    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    public LayoutParams generateDefaultLayoutParams() {
        int i = this.mOrientation;
        if (i == 0) {
            return new LayoutParams(-2, -2);
        }
        if (i == 1) {
            return new LayoutParams(-1, -2);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    public LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new LayoutParams(layoutParams);
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    @Override // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(ACCESSIBILITY_CLASS_NAME);
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(ACCESSIBILITY_CLASS_NAME);
    }

    /* loaded from: classes.dex */
    public static class LayoutParams extends LinearLayout.LayoutParams {
        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
        }

        public LayoutParams(int i, int i2, float f) {
            super(i, i2, f);
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
        }
    }
}
