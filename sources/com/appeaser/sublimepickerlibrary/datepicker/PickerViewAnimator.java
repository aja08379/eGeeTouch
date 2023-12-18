package com.appeaser.sublimepickerlibrary.datepicker;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ViewAnimator;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import androidx.core.view.InputDeviceCompat;
import java.util.ArrayList;
/* loaded from: classes.dex */
public class PickerViewAnimator extends ViewAnimator {
    private final ArrayList<View> mMatchParentChildren;

    public PickerViewAnimator(Context context) {
        super(context);
        this.mMatchParentChildren = new ArrayList<>(1);
    }

    public PickerViewAnimator(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mMatchParentChildren = new ArrayList<>(1);
    }

    @Override // android.widget.FrameLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        int childMeasureSpec;
        int childMeasureSpec2;
        int i3;
        boolean z = (View.MeasureSpec.getMode(i) == 1073741824 && View.MeasureSpec.getMode(i2) == 1073741824) ? false : true;
        int childCount = getChildCount();
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = getChildAt(i7);
            if (getMeasureAllChildren() || childAt.getVisibility() != 8) {
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
                boolean z2 = layoutParams.width == -1;
                boolean z3 = layoutParams.height == -1;
                if (z && (z2 || z3)) {
                    this.mMatchParentChildren.add(childAt);
                }
                int i8 = i4;
                int i9 = i5;
                measureChildWithMargins(childAt, i, 0, i2, 0);
                if (!z || z2) {
                    i3 = 0;
                } else {
                    int max = Math.max(i6, childAt.getMeasuredWidth() + layoutParams.leftMargin + layoutParams.rightMargin);
                    i3 = (childAt.getMeasuredWidthAndState() & (-16777216)) | 0;
                    i6 = max;
                }
                if (!z || z3) {
                    i5 = i9;
                } else {
                    int max2 = Math.max(i9, childAt.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin);
                    i3 |= (childAt.getMeasuredHeightAndState() >> 16) & InputDeviceCompat.SOURCE_ANY;
                    i5 = max2;
                }
                i4 = combineMeasuredStates(i8, i3);
            }
        }
        int i10 = i4;
        int paddingLeft = i6 + getPaddingLeft() + getPaddingRight();
        int max3 = Math.max(i5 + getPaddingTop() + getPaddingBottom(), getSuggestedMinimumHeight());
        int max4 = Math.max(paddingLeft, getSuggestedMinimumWidth());
        Drawable foreground = getForeground();
        if (foreground != null) {
            max3 = Math.max(max3, foreground.getMinimumHeight());
            max4 = Math.max(max4, foreground.getMinimumWidth());
        }
        setMeasuredDimension(resolveSizeAndState(max4, i, i10), resolveSizeAndState(max3, i2, i10 << 16));
        int size = this.mMatchParentChildren.size();
        for (int i11 = 0; i11 < size; i11++) {
            View view = this.mMatchParentChildren.get(i11);
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            if (marginLayoutParams.width == -1) {
                childMeasureSpec = View.MeasureSpec.makeMeasureSpec((((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight()) - marginLayoutParams.leftMargin) - marginLayoutParams.rightMargin, BasicMeasure.EXACTLY);
            } else {
                childMeasureSpec = getChildMeasureSpec(i, getPaddingLeft() + getPaddingRight() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin, marginLayoutParams.width);
            }
            if (marginLayoutParams.height == -1) {
                childMeasureSpec2 = View.MeasureSpec.makeMeasureSpec((((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom()) - marginLayoutParams.topMargin) - marginLayoutParams.bottomMargin, BasicMeasure.EXACTLY);
            } else {
                childMeasureSpec2 = getChildMeasureSpec(i2, getPaddingTop() + getPaddingBottom() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin, marginLayoutParams.height);
            }
            view.measure(childMeasureSpec, childMeasureSpec2);
        }
        this.mMatchParentChildren.clear();
    }
}
