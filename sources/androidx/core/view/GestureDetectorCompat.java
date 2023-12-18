package androidx.core.view;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
/* loaded from: classes.dex */
public final class GestureDetectorCompat {
    private final GestureDetectorCompatImpl mImpl;

    /* loaded from: classes.dex */
    interface GestureDetectorCompatImpl {
        boolean isLongpressEnabled();

        boolean onTouchEvent(MotionEvent motionEvent);

        void setIsLongpressEnabled(boolean z);

        void setOnDoubleTapListener(GestureDetector.OnDoubleTapListener onDoubleTapListener);
    }

    /* loaded from: classes.dex */
    static class GestureDetectorCompatImplBase implements GestureDetectorCompatImpl {
        private static final int LONG_PRESS = 2;
        private static final int SHOW_PRESS = 1;
        private static final int TAP = 3;
        private boolean mAlwaysInBiggerTapRegion;
        private boolean mAlwaysInTapRegion;
        MotionEvent mCurrentDownEvent;
        boolean mDeferConfirmSingleTap;
        GestureDetector.OnDoubleTapListener mDoubleTapListener;
        private int mDoubleTapSlopSquare;
        private float mDownFocusX;
        private float mDownFocusY;
        private final Handler mHandler;
        private boolean mInLongPress;
        private boolean mIsDoubleTapping;
        private boolean mIsLongpressEnabled;
        private float mLastFocusX;
        private float mLastFocusY;
        final GestureDetector.OnGestureListener mListener;
        private int mMaximumFlingVelocity;
        private int mMinimumFlingVelocity;
        private MotionEvent mPreviousUpEvent;
        boolean mStillDown;
        private int mTouchSlopSquare;
        private VelocityTracker mVelocityTracker;
        private static final int TAP_TIMEOUT = ViewConfiguration.getTapTimeout();
        private static final int DOUBLE_TAP_TIMEOUT = ViewConfiguration.getDoubleTapTimeout();

        /* loaded from: classes.dex */
        private class GestureHandler extends Handler {
            GestureHandler() {
            }

            GestureHandler(Handler handler) {
                super(handler.getLooper());
            }

            @Override // android.os.Handler
            public void handleMessage(Message message) {
                int i = message.what;
                if (i == 1) {
                    GestureDetectorCompatImplBase.this.mListener.onShowPress(GestureDetectorCompatImplBase.this.mCurrentDownEvent);
                } else if (i == 2) {
                    GestureDetectorCompatImplBase.this.dispatchLongPress();
                } else if (i == 3) {
                    if (GestureDetectorCompatImplBase.this.mDoubleTapListener != null) {
                        if (!GestureDetectorCompatImplBase.this.mStillDown) {
                            GestureDetectorCompatImplBase.this.mDoubleTapListener.onSingleTapConfirmed(GestureDetectorCompatImplBase.this.mCurrentDownEvent);
                        } else {
                            GestureDetectorCompatImplBase.this.mDeferConfirmSingleTap = true;
                        }
                    }
                } else {
                    throw new RuntimeException("Unknown message " + message);
                }
            }
        }

        GestureDetectorCompatImplBase(Context context, GestureDetector.OnGestureListener onGestureListener, Handler handler) {
            if (handler != null) {
                this.mHandler = new GestureHandler(handler);
            } else {
                this.mHandler = new GestureHandler();
            }
            this.mListener = onGestureListener;
            if (onGestureListener instanceof GestureDetector.OnDoubleTapListener) {
                setOnDoubleTapListener((GestureDetector.OnDoubleTapListener) onGestureListener);
            }
            init(context);
        }

        private void init(Context context) {
            if (context == null) {
                throw new IllegalArgumentException("Context must not be null");
            }
            if (this.mListener == null) {
                throw new IllegalArgumentException("OnGestureListener must not be null");
            }
            this.mIsLongpressEnabled = true;
            ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
            int scaledTouchSlop = viewConfiguration.getScaledTouchSlop();
            int scaledDoubleTapSlop = viewConfiguration.getScaledDoubleTapSlop();
            this.mMinimumFlingVelocity = viewConfiguration.getScaledMinimumFlingVelocity();
            this.mMaximumFlingVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
            this.mTouchSlopSquare = scaledTouchSlop * scaledTouchSlop;
            this.mDoubleTapSlopSquare = scaledDoubleTapSlop * scaledDoubleTapSlop;
        }

        @Override // androidx.core.view.GestureDetectorCompat.GestureDetectorCompatImpl
        public void setOnDoubleTapListener(GestureDetector.OnDoubleTapListener onDoubleTapListener) {
            this.mDoubleTapListener = onDoubleTapListener;
        }

        @Override // androidx.core.view.GestureDetectorCompat.GestureDetectorCompatImpl
        public void setIsLongpressEnabled(boolean z) {
            this.mIsLongpressEnabled = z;
        }

        @Override // androidx.core.view.GestureDetectorCompat.GestureDetectorCompatImpl
        public boolean isLongpressEnabled() {
            return this.mIsLongpressEnabled;
        }

        /* JADX WARN: Removed duplicated region for block: B:107:0x0204  */
        /* JADX WARN: Removed duplicated region for block: B:110:0x021b  */
        @Override // androidx.core.view.GestureDetectorCompat.GestureDetectorCompatImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public boolean onTouchEvent(android.view.MotionEvent r13) {
            boolean r0;
            android.view.MotionEvent r1;
            android.view.MotionEvent r6;
            boolean r13;
            android.view.GestureDetector.OnDoubleTapListener r5;
            int r0 = r13.getAction();
            if (r12.mVelocityTracker == null) {
                r12.mVelocityTracker = android.view.VelocityTracker.obtain();
            }
            r12.mVelocityTracker.addMovement(r13);
            int r0 = r0 & 255;
            boolean r4 = r0 == 6;
            int r5 = r4 ? r13.getActionIndex() : -1;
            int r6 = r13.getPointerCount();
            float r9 = 0.0f;
            float r10 = 0.0f;
            for (int r8 = 0; r8 < r6; r8++) {
                if (r5 != r8) {
                    r9 += r13.getX(r8);
                    r10 += r13.getY(r8);
                }
            }
            float r4 = r4 ? r6 - 1 : r6;
            float r9 = r9 / r4;
            float r10 = r10 / r4;
            if (r0 == 0) {
                if (r12.mDoubleTapListener != null) {
                    boolean r0 = r12.mHandler.hasMessages(3);
                    if (r0) {
                        r12.mHandler.removeMessages(3);
                    }
                    android.view.MotionEvent r1 = r12.mCurrentDownEvent;
                    if (r1 != null && (r6 = r12.mPreviousUpEvent) != null && r0 && isConsideredDoubleTap(r1, r6, r13)) {
                        r12.mIsDoubleTapping = true;
                        r0 = r12.mDoubleTapListener.onDoubleTap(r12.mCurrentDownEvent) | false | r12.mDoubleTapListener.onDoubleTapEvent(r13);
                        r12.mLastFocusX = r9;
                        r12.mDownFocusX = r9;
                        r12.mLastFocusY = r10;
                        r12.mDownFocusY = r10;
                        r1 = r12.mCurrentDownEvent;
                        if (r1 != null) {
                            r1.recycle();
                        }
                        r12.mCurrentDownEvent = android.view.MotionEvent.obtain(r13);
                        r12.mAlwaysInTapRegion = true;
                        r12.mAlwaysInBiggerTapRegion = true;
                        r12.mStillDown = true;
                        r12.mInLongPress = false;
                        r12.mDeferConfirmSingleTap = false;
                        if (r12.mIsLongpressEnabled) {
                            r12.mHandler.removeMessages(2);
                            r12.mHandler.sendEmptyMessageAtTime(2, r12.mCurrentDownEvent.getDownTime() + androidx.core.view.GestureDetectorCompat.GestureDetectorCompatImplBase.TAP_TIMEOUT + android.view.ViewConfiguration.getLongPressTimeout());
                        }
                        r12.mHandler.sendEmptyMessageAtTime(1, r12.mCurrentDownEvent.getDownTime() + androidx.core.view.GestureDetectorCompat.GestureDetectorCompatImplBase.TAP_TIMEOUT);
                        return r0 | r12.mListener.onDown(r13);
                    }
                    r12.mHandler.sendEmptyMessageDelayed(3, androidx.core.view.GestureDetectorCompat.GestureDetectorCompatImplBase.DOUBLE_TAP_TIMEOUT);
                }
                r0 = false;
                r12.mLastFocusX = r9;
                r12.mDownFocusX = r9;
                r12.mLastFocusY = r10;
                r12.mDownFocusY = r10;
                r1 = r12.mCurrentDownEvent;
                if (r1 != null) {
                }
                r12.mCurrentDownEvent = android.view.MotionEvent.obtain(r13);
                r12.mAlwaysInTapRegion = true;
                r12.mAlwaysInBiggerTapRegion = true;
                r12.mStillDown = true;
                r12.mInLongPress = false;
                r12.mDeferConfirmSingleTap = false;
                if (r12.mIsLongpressEnabled) {
                }
                r12.mHandler.sendEmptyMessageAtTime(1, r12.mCurrentDownEvent.getDownTime() + androidx.core.view.GestureDetectorCompat.GestureDetectorCompatImplBase.TAP_TIMEOUT);
                return r0 | r12.mListener.onDown(r13);
            }
            if (r0 == 1) {
                r12.mStillDown = false;
                android.view.MotionEvent r0 = android.view.MotionEvent.obtain(r13);
                if (r12.mIsDoubleTapping) {
                    r13 = r12.mDoubleTapListener.onDoubleTapEvent(r13) | false;
                } else {
                    if (r12.mInLongPress) {
                        r12.mHandler.removeMessages(3);
                        r12.mInLongPress = false;
                    } else if (r12.mAlwaysInTapRegion) {
                        boolean r1 = r12.mListener.onSingleTapUp(r13);
                        if (r12.mDeferConfirmSingleTap && (r5 = r12.mDoubleTapListener) != null) {
                            r5.onSingleTapConfirmed(r13);
                        }
                        r13 = r1;
                    } else {
                        android.view.VelocityTracker r1 = r12.mVelocityTracker;
                        int r5 = r13.getPointerId(0);
                        r1.computeCurrentVelocity(1000, r12.mMaximumFlingVelocity);
                        float r6 = r1.getYVelocity(r5);
                        float r1 = r1.getXVelocity(r5);
                        if (java.lang.Math.abs(r6) > r12.mMinimumFlingVelocity || java.lang.Math.abs(r1) > r12.mMinimumFlingVelocity) {
                            r13 = r12.mListener.onFling(r12.mCurrentDownEvent, r13, r1, r6);
                        }
                    }
                    r13 = false;
                }
                android.view.MotionEvent r1 = r12.mPreviousUpEvent;
                if (r1 != null) {
                    r1.recycle();
                }
                r12.mPreviousUpEvent = r0;
                android.view.VelocityTracker r0 = r12.mVelocityTracker;
                if (r0 != null) {
                    r0.recycle();
                    r12.mVelocityTracker = null;
                }
                r12.mIsDoubleTapping = false;
                r12.mDeferConfirmSingleTap = false;
                r12.mHandler.removeMessages(1);
                r12.mHandler.removeMessages(2);
            } else if (r0 != 2) {
                if (r0 == 3) {
                    cancel();
                    return false;
                } else if (r0 == 5) {
                    r12.mLastFocusX = r9;
                    r12.mDownFocusX = r9;
                    r12.mLastFocusY = r10;
                    r12.mDownFocusY = r10;
                    cancelTaps();
                    return false;
                } else if (r0 != 6) {
                    return false;
                } else {
                    r12.mLastFocusX = r9;
                    r12.mDownFocusX = r9;
                    r12.mLastFocusY = r10;
                    r12.mDownFocusY = r10;
                    r12.mVelocityTracker.computeCurrentVelocity(1000, r12.mMaximumFlingVelocity);
                    int r0 = r13.getActionIndex();
                    int r1 = r13.getPointerId(r0);
                    float r2 = r12.mVelocityTracker.getXVelocity(r1);
                    float r1 = r12.mVelocityTracker.getYVelocity(r1);
                    for (int r4 = 0; r4 < r6; r4++) {
                        if (r4 != r0) {
                            int r5 = r13.getPointerId(r4);
                            if ((r12.mVelocityTracker.getXVelocity(r5) * r2) + (r12.mVelocityTracker.getYVelocity(r5) * r1) < 0.0f) {
                                r12.mVelocityTracker.clear();
                                return false;
                            }
                        }
                    }
                    return false;
                }
            } else if (r12.mInLongPress) {
                return false;
            } else {
                float r0 = r12.mLastFocusX - r9;
                float r1 = r12.mLastFocusY - r10;
                if (r12.mIsDoubleTapping) {
                    return false | r12.mDoubleTapListener.onDoubleTapEvent(r13);
                }
                if (r12.mAlwaysInTapRegion) {
                    int r6 = (int) (r9 - r12.mDownFocusX);
                    int r7 = (int) (r10 - r12.mDownFocusY);
                    int r6 = (r6 * r6) + (r7 * r7);
                    if (r6 > r12.mTouchSlopSquare) {
                        r13 = r12.mListener.onScroll(r12.mCurrentDownEvent, r13, r0, r1);
                        r12.mLastFocusX = r9;
                        r12.mLastFocusY = r10;
                        r12.mAlwaysInTapRegion = false;
                        r12.mHandler.removeMessages(3);
                        r12.mHandler.removeMessages(1);
                        r12.mHandler.removeMessages(2);
                    } else {
                        r13 = false;
                    }
                    if (r6 > r12.mTouchSlopSquare) {
                        r12.mAlwaysInBiggerTapRegion = false;
                    }
                } else if (java.lang.Math.abs(r0) >= 1.0f || java.lang.Math.abs(r1) >= 1.0f) {
                    boolean r3 = r12.mListener.onScroll(r12.mCurrentDownEvent, r13, r0, r1);
                    r12.mLastFocusX = r9;
                    r12.mLastFocusY = r10;
                    return r3;
                } else {
                    return false;
                }
            }
            return r13;
        }

        private void cancel() {
            this.mHandler.removeMessages(1);
            this.mHandler.removeMessages(2);
            this.mHandler.removeMessages(3);
            this.mVelocityTracker.recycle();
            this.mVelocityTracker = null;
            this.mIsDoubleTapping = false;
            this.mStillDown = false;
            this.mAlwaysInTapRegion = false;
            this.mAlwaysInBiggerTapRegion = false;
            this.mDeferConfirmSingleTap = false;
            if (this.mInLongPress) {
                this.mInLongPress = false;
            }
        }

        private void cancelTaps() {
            this.mHandler.removeMessages(1);
            this.mHandler.removeMessages(2);
            this.mHandler.removeMessages(3);
            this.mIsDoubleTapping = false;
            this.mAlwaysInTapRegion = false;
            this.mAlwaysInBiggerTapRegion = false;
            this.mDeferConfirmSingleTap = false;
            if (this.mInLongPress) {
                this.mInLongPress = false;
            }
        }

        private boolean isConsideredDoubleTap(MotionEvent motionEvent, MotionEvent motionEvent2, MotionEvent motionEvent3) {
            if (this.mAlwaysInBiggerTapRegion && motionEvent3.getEventTime() - motionEvent2.getEventTime() <= DOUBLE_TAP_TIMEOUT) {
                int x = ((int) motionEvent.getX()) - ((int) motionEvent3.getX());
                int y = ((int) motionEvent.getY()) - ((int) motionEvent3.getY());
                return (x * x) + (y * y) < this.mDoubleTapSlopSquare;
            }
            return false;
        }

        void dispatchLongPress() {
            this.mHandler.removeMessages(3);
            this.mDeferConfirmSingleTap = false;
            this.mInLongPress = true;
            this.mListener.onLongPress(this.mCurrentDownEvent);
        }
    }

    /* loaded from: classes.dex */
    static class GestureDetectorCompatImplJellybeanMr2 implements GestureDetectorCompatImpl {
        private final GestureDetector mDetector;

        GestureDetectorCompatImplJellybeanMr2(Context context, GestureDetector.OnGestureListener onGestureListener, Handler handler) {
            this.mDetector = new GestureDetector(context, onGestureListener, handler);
        }

        @Override // androidx.core.view.GestureDetectorCompat.GestureDetectorCompatImpl
        public boolean isLongpressEnabled() {
            return this.mDetector.isLongpressEnabled();
        }

        @Override // androidx.core.view.GestureDetectorCompat.GestureDetectorCompatImpl
        public boolean onTouchEvent(MotionEvent motionEvent) {
            return this.mDetector.onTouchEvent(motionEvent);
        }

        @Override // androidx.core.view.GestureDetectorCompat.GestureDetectorCompatImpl
        public void setIsLongpressEnabled(boolean z) {
            this.mDetector.setIsLongpressEnabled(z);
        }

        @Override // androidx.core.view.GestureDetectorCompat.GestureDetectorCompatImpl
        public void setOnDoubleTapListener(GestureDetector.OnDoubleTapListener onDoubleTapListener) {
            this.mDetector.setOnDoubleTapListener(onDoubleTapListener);
        }
    }

    public GestureDetectorCompat(Context context, GestureDetector.OnGestureListener onGestureListener) {
        this(context, onGestureListener, null);
    }

    public GestureDetectorCompat(Context context, GestureDetector.OnGestureListener onGestureListener, Handler handler) {
        if (Build.VERSION.SDK_INT > 17) {
            this.mImpl = new GestureDetectorCompatImplJellybeanMr2(context, onGestureListener, handler);
        } else {
            this.mImpl = new GestureDetectorCompatImplBase(context, onGestureListener, handler);
        }
    }

    public boolean isLongpressEnabled() {
        return this.mImpl.isLongpressEnabled();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return this.mImpl.onTouchEvent(motionEvent);
    }

    public void setIsLongpressEnabled(boolean z) {
        this.mImpl.setIsLongpressEnabled(z);
    }

    public void setOnDoubleTapListener(GestureDetector.OnDoubleTapListener onDoubleTapListener) {
        this.mImpl.setOnDoubleTapListener(onDoubleTapListener);
    }
}
