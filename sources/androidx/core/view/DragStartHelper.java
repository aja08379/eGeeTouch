package androidx.core.view;

import android.graphics.Point;
import android.view.MotionEvent;
import android.view.View;
/* loaded from: classes.dex */
public class DragStartHelper {
    private boolean mDragging;
    private int mLastTouchX;
    private int mLastTouchY;
    private final OnDragStartListener mListener;
    private final View.OnLongClickListener mLongClickListener = new View.OnLongClickListener() { // from class: androidx.core.view.-$$Lambda$n1EU8uBmZwEAH7KnaNKI66mZf_U
        @Override // android.view.View.OnLongClickListener
        public final boolean onLongClick(View view) {
            return DragStartHelper.this.onLongClick(view);
        }
    };
    private final View.OnTouchListener mTouchListener = new View.OnTouchListener() { // from class: androidx.core.view.-$$Lambda$HYyW5APu5x4qu1qflbXO0PpqmYE
        @Override // android.view.View.OnTouchListener
        public final boolean onTouch(View view, MotionEvent motionEvent) {
            return DragStartHelper.this.onTouch(view, motionEvent);
        }
    };
    private final View mView;

    /* loaded from: classes.dex */
    public interface OnDragStartListener {
        boolean onDragStart(View view, DragStartHelper dragStartHelper);
    }

    public DragStartHelper(View view, OnDragStartListener onDragStartListener) {
        this.mView = view;
        this.mListener = onDragStartListener;
    }

    public void attach() {
        this.mView.setOnLongClickListener(this.mLongClickListener);
        this.mView.setOnTouchListener(this.mTouchListener);
    }

    public void detach() {
        this.mView.setOnLongClickListener(null);
        this.mView.setOnTouchListener(null);
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x0018, code lost:
        if (r2 != 3) goto L9;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onTouch(android.view.View r7, android.view.MotionEvent r8) {
        int r0 = (int) r8.getX();
        int r1 = (int) r8.getY();
        int r2 = r8.getAction();
        if (r2 == 0) {
            r6.mLastTouchX = r0;
            r6.mLastTouchY = r1;
        } else {
            if (r2 != 1) {
                if (r2 == 2) {
                    if (androidx.core.view.MotionEventCompat.isFromSource(r8, 8194) && (r8.getButtonState() & 1) != 0 && !r6.mDragging && (r6.mLastTouchX != r0 || r6.mLastTouchY != r1)) {
                        r6.mLastTouchX = r0;
                        r6.mLastTouchY = r1;
                        boolean r7 = r6.mListener.onDragStart(r7, r6);
                        r6.mDragging = r7;
                        return r7;
                    }
                }
            }
            r6.mDragging = false;
        }
        return false;
    }

    public boolean onLongClick(View view) {
        return this.mListener.onDragStart(view, this);
    }

    public void getTouchPosition(Point point) {
        point.set(this.mLastTouchX, this.mLastTouchY);
    }
}
