package com.prolificinteractive.materialcalendarview;

import android.graphics.drawable.Drawable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
/* loaded from: classes2.dex */
public class DayViewFacade {
    private Drawable backgroundDrawable = null;
    private Drawable selectionDrawable = null;
    private final LinkedList<Span> spans = new LinkedList<>();
    private boolean daysDisabled = false;
    private boolean isDecorated = false;

    public void setBackgroundDrawable(Drawable drawable) {
        if (drawable == null) {
            throw new IllegalArgumentException("Cannot be null");
        }
        this.backgroundDrawable = drawable;
        this.isDecorated = true;
    }

    public void setSelectionDrawable(Drawable drawable) {
        if (drawable == null) {
            throw new IllegalArgumentException("Cannot be null");
        }
        this.selectionDrawable = drawable;
        this.isDecorated = true;
    }

    public void addSpan(Object obj) {
        LinkedList<Span> linkedList = this.spans;
        if (linkedList != null) {
            linkedList.add(new Span(obj));
            this.isDecorated = true;
        }
    }

    public void setDaysDisabled(boolean z) {
        this.daysDisabled = z;
        this.isDecorated = true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void reset() {
        this.backgroundDrawable = null;
        this.selectionDrawable = null;
        this.spans.clear();
        this.isDecorated = false;
        this.daysDisabled = false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void applyTo(DayViewFacade dayViewFacade) {
        Drawable drawable = this.selectionDrawable;
        if (drawable != null) {
            dayViewFacade.setSelectionDrawable(drawable);
        }
        Drawable drawable2 = this.backgroundDrawable;
        if (drawable2 != null) {
            dayViewFacade.setBackgroundDrawable(drawable2);
        }
        dayViewFacade.spans.addAll(this.spans);
        dayViewFacade.isDecorated |= this.isDecorated;
        dayViewFacade.daysDisabled = this.daysDisabled;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isDecorated() {
        return this.isDecorated;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Drawable getSelectionDrawable() {
        return this.selectionDrawable;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Drawable getBackgroundDrawable() {
        return this.backgroundDrawable;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<Span> getSpans() {
        return Collections.unmodifiableList(this.spans);
    }

    public boolean areDaysDisabled() {
        return this.daysDisabled;
    }

    /* loaded from: classes2.dex */
    static class Span {
        final Object span;

        public Span(Object obj) {
            this.span = obj;
        }
    }
}
