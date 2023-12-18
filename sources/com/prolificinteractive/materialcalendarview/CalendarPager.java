package com.prolificinteractive.materialcalendarview;

import android.content.Context;
import android.view.MotionEvent;
import androidx.viewpager.widget.ViewPager;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class CalendarPager extends ViewPager {
    private boolean pagingEnabled;

    public CalendarPager(Context context) {
        super(context);
        this.pagingEnabled = true;
    }

    public void setPagingEnabled(boolean z) {
        this.pagingEnabled = z;
    }

    public boolean isPagingEnabled() {
        return this.pagingEnabled;
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.pagingEnabled && super.onInterceptTouchEvent(motionEvent);
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return this.pagingEnabled && super.onTouchEvent(motionEvent);
    }

    @Override // android.view.View
    public boolean canScrollVertically(int i) {
        return this.pagingEnabled && super.canScrollVertically(i);
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.View
    public boolean canScrollHorizontally(int i) {
        return this.pagingEnabled && super.canScrollHorizontally(i);
    }
}
