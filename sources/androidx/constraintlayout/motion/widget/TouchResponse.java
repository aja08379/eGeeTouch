package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.R;
import androidx.core.widget.NestedScrollView;
import org.xmlpull.v1.XmlPullParser;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class TouchResponse {
    public static final int COMPLETE_MODE_CONTINUOUS_VELOCITY = 0;
    public static final int COMPLETE_MODE_SPRING = 1;
    private static final boolean DEBUG = false;
    private static final float EPSILON = 1.0E-7f;
    static final int FLAG_DISABLE_POST_SCROLL = 1;
    static final int FLAG_DISABLE_SCROLL = 2;
    static final int FLAG_SUPPORT_SCROLL_UP = 4;
    private static final int SEC_TO_MILLISECONDS = 1000;
    private static final int SIDE_BOTTOM = 3;
    private static final int SIDE_END = 6;
    private static final int SIDE_LEFT = 1;
    private static final int SIDE_MIDDLE = 4;
    private static final int SIDE_RIGHT = 2;
    private static final int SIDE_START = 5;
    private static final int SIDE_TOP = 0;
    private static final String TAG = "TouchResponse";
    private static final int TOUCH_DOWN = 1;
    private static final int TOUCH_END = 5;
    private static final int TOUCH_LEFT = 2;
    private static final int TOUCH_RIGHT = 3;
    private static final int TOUCH_START = 4;
    private static final int TOUCH_UP = 0;
    private float[] mAnchorDpDt;
    private int mAutoCompleteMode;
    private float mDragScale;
    private boolean mDragStarted;
    private float mDragThreshold;
    private int mFlags;
    boolean mIsRotateMode;
    private float mLastTouchX;
    private float mLastTouchY;
    private int mLimitBoundsTo;
    private float mMaxAcceleration;
    private float mMaxVelocity;
    private final MotionLayout mMotionLayout;
    private boolean mMoveWhenScrollAtTop;
    private int mOnTouchUp;
    float mRotateCenterX;
    float mRotateCenterY;
    private int mRotationCenterId;
    private int mSpringBoundary;
    private float mSpringDamping;
    private float mSpringMass;
    private float mSpringStiffness;
    private float mSpringStopThreshold;
    private int[] mTempLoc;
    private int mTouchAnchorId;
    private int mTouchAnchorSide;
    private float mTouchAnchorX;
    private float mTouchAnchorY;
    private float mTouchDirectionX;
    private float mTouchDirectionY;
    private int mTouchRegionId;
    private int mTouchSide;
    private static final float[][] TOUCH_SIDES = {new float[]{0.5f, 0.0f}, new float[]{0.0f, 0.5f}, new float[]{1.0f, 0.5f}, new float[]{0.5f, 1.0f}, new float[]{0.5f, 0.5f}, new float[]{0.0f, 0.5f}, new float[]{1.0f, 0.5f}};
    private static final float[][] TOUCH_DIRECTION = {new float[]{0.0f, -1.0f}, new float[]{0.0f, 1.0f}, new float[]{-1.0f, 0.0f}, new float[]{1.0f, 0.0f}, new float[]{-1.0f, 0.0f}, new float[]{1.0f, 0.0f}};

    /* JADX INFO: Access modifiers changed from: package-private */
    public TouchResponse(Context context, MotionLayout layout, XmlPullParser parser) {
        this.mTouchAnchorSide = 0;
        this.mTouchSide = 0;
        this.mOnTouchUp = 0;
        this.mTouchAnchorId = -1;
        this.mTouchRegionId = -1;
        this.mLimitBoundsTo = -1;
        this.mTouchAnchorY = 0.5f;
        this.mTouchAnchorX = 0.5f;
        this.mRotateCenterX = 0.5f;
        this.mRotateCenterY = 0.5f;
        this.mRotationCenterId = -1;
        this.mIsRotateMode = false;
        this.mTouchDirectionX = 0.0f;
        this.mTouchDirectionY = 1.0f;
        this.mDragStarted = false;
        this.mAnchorDpDt = new float[2];
        this.mTempLoc = new int[2];
        this.mMaxVelocity = 4.0f;
        this.mMaxAcceleration = 1.2f;
        this.mMoveWhenScrollAtTop = true;
        this.mDragScale = 1.0f;
        this.mFlags = 0;
        this.mDragThreshold = 10.0f;
        this.mSpringDamping = 10.0f;
        this.mSpringMass = 1.0f;
        this.mSpringStiffness = Float.NaN;
        this.mSpringStopThreshold = Float.NaN;
        this.mSpringBoundary = 0;
        this.mAutoCompleteMode = 0;
        this.mMotionLayout = layout;
        fillFromAttributeList(context, Xml.asAttributeSet(parser));
    }

    public TouchResponse(MotionLayout layout, OnSwipe onSwipe) {
        this.mTouchAnchorSide = 0;
        this.mTouchSide = 0;
        this.mOnTouchUp = 0;
        this.mTouchAnchorId = -1;
        this.mTouchRegionId = -1;
        this.mLimitBoundsTo = -1;
        this.mTouchAnchorY = 0.5f;
        this.mTouchAnchorX = 0.5f;
        this.mRotateCenterX = 0.5f;
        this.mRotateCenterY = 0.5f;
        this.mRotationCenterId = -1;
        this.mIsRotateMode = false;
        this.mTouchDirectionX = 0.0f;
        this.mTouchDirectionY = 1.0f;
        this.mDragStarted = false;
        this.mAnchorDpDt = new float[2];
        this.mTempLoc = new int[2];
        this.mMaxVelocity = 4.0f;
        this.mMaxAcceleration = 1.2f;
        this.mMoveWhenScrollAtTop = true;
        this.mDragScale = 1.0f;
        this.mFlags = 0;
        this.mDragThreshold = 10.0f;
        this.mSpringDamping = 10.0f;
        this.mSpringMass = 1.0f;
        this.mSpringStiffness = Float.NaN;
        this.mSpringStopThreshold = Float.NaN;
        this.mSpringBoundary = 0;
        this.mAutoCompleteMode = 0;
        this.mMotionLayout = layout;
        this.mTouchAnchorId = onSwipe.getTouchAnchorId();
        int touchAnchorSide = onSwipe.getTouchAnchorSide();
        this.mTouchAnchorSide = touchAnchorSide;
        if (touchAnchorSide != -1) {
            float[][] fArr = TOUCH_SIDES;
            this.mTouchAnchorX = fArr[touchAnchorSide][0];
            this.mTouchAnchorY = fArr[touchAnchorSide][1];
        }
        int dragDirection = onSwipe.getDragDirection();
        this.mTouchSide = dragDirection;
        float[][] fArr2 = TOUCH_DIRECTION;
        if (dragDirection < fArr2.length) {
            this.mTouchDirectionX = fArr2[dragDirection][0];
            this.mTouchDirectionY = fArr2[dragDirection][1];
        } else {
            this.mTouchDirectionY = Float.NaN;
            this.mTouchDirectionX = Float.NaN;
            this.mIsRotateMode = true;
        }
        this.mMaxVelocity = onSwipe.getMaxVelocity();
        this.mMaxAcceleration = onSwipe.getMaxAcceleration();
        this.mMoveWhenScrollAtTop = onSwipe.getMoveWhenScrollAtTop();
        this.mDragScale = onSwipe.getDragScale();
        this.mDragThreshold = onSwipe.getDragThreshold();
        this.mTouchRegionId = onSwipe.getTouchRegionId();
        this.mOnTouchUp = onSwipe.getOnTouchUp();
        this.mFlags = onSwipe.getNestedScrollFlags();
        this.mLimitBoundsTo = onSwipe.getLimitBoundsTo();
        this.mRotationCenterId = onSwipe.getRotationCenterId();
        this.mSpringBoundary = onSwipe.getSpringBoundary();
        this.mSpringDamping = onSwipe.getSpringDamping();
        this.mSpringMass = onSwipe.getSpringMass();
        this.mSpringStiffness = onSwipe.getSpringStiffness();
        this.mSpringStopThreshold = onSwipe.getSpringStopThreshold();
        this.mAutoCompleteMode = onSwipe.getAutoCompleteMode();
    }

    public void setRTL(boolean rtl) {
        if (rtl) {
            float[][] fArr = TOUCH_DIRECTION;
            fArr[4] = fArr[3];
            fArr[5] = fArr[2];
            float[][] fArr2 = TOUCH_SIDES;
            fArr2[5] = fArr2[2];
            fArr2[6] = fArr2[1];
        } else {
            float[][] fArr3 = TOUCH_DIRECTION;
            fArr3[4] = fArr3[2];
            fArr3[5] = fArr3[3];
            float[][] fArr4 = TOUCH_SIDES;
            fArr4[5] = fArr4[1];
            fArr4[6] = fArr4[2];
        }
        float[][] fArr5 = TOUCH_SIDES;
        int i = this.mTouchAnchorSide;
        this.mTouchAnchorX = fArr5[i][0];
        this.mTouchAnchorY = fArr5[i][1];
        int i2 = this.mTouchSide;
        float[][] fArr6 = TOUCH_DIRECTION;
        if (i2 >= fArr6.length) {
            return;
        }
        this.mTouchDirectionX = fArr6[i2][0];
        this.mTouchDirectionY = fArr6[i2][1];
    }

    private void fillFromAttributeList(Context context, AttributeSet attrs) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attrs, R.styleable.OnSwipe);
        fill(obtainStyledAttributes);
        obtainStyledAttributes.recycle();
    }

    private void fill(TypedArray a) {
        int indexCount = a.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int index = a.getIndex(i);
            if (index == R.styleable.OnSwipe_touchAnchorId) {
                this.mTouchAnchorId = a.getResourceId(index, this.mTouchAnchorId);
            } else if (index == R.styleable.OnSwipe_touchAnchorSide) {
                int i2 = a.getInt(index, this.mTouchAnchorSide);
                this.mTouchAnchorSide = i2;
                float[][] fArr = TOUCH_SIDES;
                this.mTouchAnchorX = fArr[i2][0];
                this.mTouchAnchorY = fArr[i2][1];
            } else if (index == R.styleable.OnSwipe_dragDirection) {
                int i3 = a.getInt(index, this.mTouchSide);
                this.mTouchSide = i3;
                float[][] fArr2 = TOUCH_DIRECTION;
                if (i3 < fArr2.length) {
                    this.mTouchDirectionX = fArr2[i3][0];
                    this.mTouchDirectionY = fArr2[i3][1];
                } else {
                    this.mTouchDirectionY = Float.NaN;
                    this.mTouchDirectionX = Float.NaN;
                    this.mIsRotateMode = true;
                }
            } else if (index == R.styleable.OnSwipe_maxVelocity) {
                this.mMaxVelocity = a.getFloat(index, this.mMaxVelocity);
            } else if (index == R.styleable.OnSwipe_maxAcceleration) {
                this.mMaxAcceleration = a.getFloat(index, this.mMaxAcceleration);
            } else if (index == R.styleable.OnSwipe_moveWhenScrollAtTop) {
                this.mMoveWhenScrollAtTop = a.getBoolean(index, this.mMoveWhenScrollAtTop);
            } else if (index == R.styleable.OnSwipe_dragScale) {
                this.mDragScale = a.getFloat(index, this.mDragScale);
            } else if (index == R.styleable.OnSwipe_dragThreshold) {
                this.mDragThreshold = a.getFloat(index, this.mDragThreshold);
            } else if (index == R.styleable.OnSwipe_touchRegionId) {
                this.mTouchRegionId = a.getResourceId(index, this.mTouchRegionId);
            } else if (index == R.styleable.OnSwipe_onTouchUp) {
                this.mOnTouchUp = a.getInt(index, this.mOnTouchUp);
            } else if (index == R.styleable.OnSwipe_nestedScrollFlags) {
                this.mFlags = a.getInteger(index, 0);
            } else if (index == R.styleable.OnSwipe_limitBoundsTo) {
                this.mLimitBoundsTo = a.getResourceId(index, 0);
            } else if (index == R.styleable.OnSwipe_rotationCenterId) {
                this.mRotationCenterId = a.getResourceId(index, this.mRotationCenterId);
            } else if (index == R.styleable.OnSwipe_springDamping) {
                this.mSpringDamping = a.getFloat(index, this.mSpringDamping);
            } else if (index == R.styleable.OnSwipe_springMass) {
                this.mSpringMass = a.getFloat(index, this.mSpringMass);
            } else if (index == R.styleable.OnSwipe_springStiffness) {
                this.mSpringStiffness = a.getFloat(index, this.mSpringStiffness);
            } else if (index == R.styleable.OnSwipe_springStopThreshold) {
                this.mSpringStopThreshold = a.getFloat(index, this.mSpringStopThreshold);
            } else if (index == R.styleable.OnSwipe_springBoundary) {
                this.mSpringBoundary = a.getInt(index, this.mSpringBoundary);
            } else if (index == R.styleable.OnSwipe_autoCompleteMode) {
                this.mAutoCompleteMode = a.getInt(index, this.mAutoCompleteMode);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setUpTouchEvent(float lastTouchX, float lastTouchY) {
        this.mLastTouchX = lastTouchX;
        this.mLastTouchY = lastTouchY;
        this.mDragStarted = false;
    }

    /* JADX WARN: Removed duplicated region for block: B:57:0x0270  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0294  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x02b1  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x02bd  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    void processTouchRotateEvent(android.view.MotionEvent r24, androidx.constraintlayout.motion.widget.MotionLayout.MotionTracker r25, int r26, androidx.constraintlayout.motion.widget.MotionScene r27) {
        float r4;
        float r11;
        int r12;
        int r8;
        float r2;
        int r11;
        float r2;
        int r4;
        float[] r3;
        android.view.View r12;
        double r8;
        float[] r3;
        r25.addMovement(r24);
        int r3 = r24.getAction();
        if (r3 == 0) {
            r23.mLastTouchX = r24.getRawX();
            r23.mLastTouchY = r24.getRawY();
            r23.mDragStarted = false;
        } else if (r3 != 1) {
            if (r3 != 2) {
                return;
            }
            r24.getRawY();
            r24.getRawX();
            float r3 = r23.mMotionLayout.getWidth() / 2.0f;
            float r11 = r23.mMotionLayout.getHeight() / 2.0f;
            int r12 = r23.mRotationCenterId;
            if (r12 != -1) {
                android.view.View r3 = r23.mMotionLayout.findViewById(r12);
                r23.mMotionLayout.getLocationOnScreen(r23.mTempLoc);
                r11 = ((r3.getTop() + r3.getBottom()) / 2.0f) + r23.mTempLoc[1];
                r3 = r23.mTempLoc[0] + ((r3.getLeft() + r3.getRight()) / 2.0f);
            } else {
                int r12 = r23.mTouchAnchorId;
                if (r12 != -1) {
                    if (r23.mMotionLayout.findViewById(r23.mMotionLayout.getMotionController(r12).getAnimateRelativeTo()) == null) {
                        android.util.Log.e(androidx.constraintlayout.motion.widget.TouchResponse.TAG, "could not find view to animate to");
                    } else {
                        r23.mMotionLayout.getLocationOnScreen(r23.mTempLoc);
                        r3 = r23.mTempLoc[0] + ((r12.getLeft() + r12.getRight()) / 2.0f);
                        r11 = r23.mTempLoc[1] + ((r12.getTop() + r12.getBottom()) / 2.0f);
                    }
                }
            }
            float r9 = r24.getRawX() - r3;
            float r12 = r24.getRawY() - r11;
            float r6 = (float) (((java.lang.Math.atan2(r24.getRawY() - r11, r24.getRawX() - r3) - java.lang.Math.atan2(r23.mLastTouchY - r11, r23.mLastTouchX - r3)) * 180.0d) / 3.141592653589793d);
            if (r6 > 330.0f) {
                r6 -= 360.0f;
            } else if (r6 < -330.0f) {
                r6 += 360.0f;
            }
            if (java.lang.Math.abs(r6) > 0.01d || r23.mDragStarted) {
                float r7 = r23.mMotionLayout.getProgress();
                if (!r23.mDragStarted) {
                    r23.mDragStarted = true;
                    r23.mMotionLayout.setProgress(r7);
                }
                int r11 = r23.mTouchAnchorId;
                if (r11 != -1) {
                    r23.mMotionLayout.getAnchorDpDt(r11, r7, r23.mTouchAnchorX, r23.mTouchAnchorY, r23.mAnchorDpDt);
                    r23.mAnchorDpDt[1] = (float) java.lang.Math.toDegrees(r3[1]);
                } else {
                    r23.mAnchorDpDt[1] = 360.0f;
                }
                float r5 = java.lang.Math.max(java.lang.Math.min(r7 + ((r6 * r23.mDragScale) / r23.mAnchorDpDt[1]), 1.0f), 0.0f);
                float r7 = r23.mMotionLayout.getProgress();
                if (r5 != r7) {
                    int r6 = (r7 > 0.0f ? 1 : (r7 == 0.0f ? 0 : -1));
                    if (r6 == 0 || r7 == 1.0f) {
                        r23.mMotionLayout.endTrigger(r6 == 0);
                    }
                    r23.mMotionLayout.setProgress(r5);
                    r25.computeCurrentVelocity(1000);
                    float r3 = r25.getXVelocity();
                    double r4 = r25.getYVelocity();
                    double r6 = r3;
                    r23.mMotionLayout.mLastVelocity = (float) java.lang.Math.toDegrees((float) ((java.lang.Math.hypot(r4, r6) * java.lang.Math.sin(java.lang.Math.atan2(r4, r6) - r8)) / java.lang.Math.hypot(r9, r12)));
                } else {
                    r23.mMotionLayout.mLastVelocity = 0.0f;
                }
                r23.mLastTouchX = r24.getRawX();
                r23.mLastTouchY = r24.getRawY();
            }
        } else {
            r23.mDragStarted = false;
            r25.computeCurrentVelocity(16);
            float r6 = r25.getXVelocity();
            float r1 = r25.getYVelocity();
            float r7 = r23.mMotionLayout.getProgress();
            float r8 = r23.mMotionLayout.getWidth() / 2.0f;
            float r11 = r23.mMotionLayout.getHeight() / 2.0f;
            int r12 = r23.mRotationCenterId;
            if (r12 != -1) {
                android.view.View r8 = r23.mMotionLayout.findViewById(r12);
                r23.mMotionLayout.getLocationOnScreen(r23.mTempLoc);
                r4 = r23.mTempLoc[0] + ((r8.getLeft() + r8.getRight()) / 2.0f);
                r11 = r23.mTempLoc[1];
                r12 = r8.getTop();
                r8 = r8.getBottom();
            } else {
                int r12 = r23.mTouchAnchorId;
                if (r12 != -1) {
                    android.view.View r8 = r23.mMotionLayout.findViewById(r23.mMotionLayout.getMotionController(r12).getAnimateRelativeTo());
                    r23.mMotionLayout.getLocationOnScreen(r23.mTempLoc);
                    r4 = r23.mTempLoc[0] + ((r8.getLeft() + r8.getRight()) / 2.0f);
                    r11 = r23.mTempLoc[1];
                    r12 = r8.getTop();
                    r8 = r8.getBottom();
                }
                float r4 = r24.getRawX() - r8;
                double r8 = java.lang.Math.toDegrees(java.lang.Math.atan2(r24.getRawY() - r11, r4));
                r11 = r23.mTouchAnchorId;
                if (r11 == -1) {
                    r23.mMotionLayout.getAnchorDpDt(r11, r7, r23.mTouchAnchorX, r23.mTouchAnchorY, r23.mAnchorDpDt);
                    r23.mAnchorDpDt[1] = (float) java.lang.Math.toDegrees(r3[1]);
                } else {
                    r23.mAnchorDpDt[1] = 360.0f;
                }
                float r1 = ((float) (java.lang.Math.toDegrees(java.lang.Math.atan2(r1 + r2, r6 + r4)) - r8)) * 62.5f;
                r2 = java.lang.Float.isNaN(r1) ? (((r1 * 3.0f) * r23.mDragScale) / r23.mAnchorDpDt[1]) + r7 : r7;
                if (r2 != 0.0f || r2 == 1.0f || (r4 = r23.mOnTouchUp) == 3) {
                    if (0.0f < r2 || 1.0f <= r2) {
                        r23.mMotionLayout.setState(androidx.constraintlayout.motion.widget.MotionLayout.TransitionState.FINISHED);
                    }
                    return;
                }
                float r1 = (r1 * r23.mDragScale) / r23.mAnchorDpDt[1];
                float r2 = ((double) r2) < 0.5d ? 0.0f : 1.0f;
                if (r4 == 6) {
                    if (r7 + r1 < 0.0f) {
                        r1 = java.lang.Math.abs(r1);
                    }
                    r2 = 1.0f;
                }
                if (r23.mOnTouchUp == 7) {
                    if (r7 + r1 > 1.0f) {
                        r1 = -java.lang.Math.abs(r1);
                    }
                    r2 = 0.0f;
                }
                r23.mMotionLayout.touchAnimateTo(r23.mOnTouchUp, r2, r1 * 3.0f);
                if (0.0f >= r7 || 1.0f <= r7) {
                    r23.mMotionLayout.setState(androidx.constraintlayout.motion.widget.MotionLayout.TransitionState.FINISHED);
                    return;
                }
                return;
            }
            r11 = r11 + ((r12 + r8) / 2.0f);
            r8 = r4;
            float r4 = r24.getRawX() - r8;
            double r8 = java.lang.Math.toDegrees(java.lang.Math.atan2(r24.getRawY() - r11, r4));
            r11 = r23.mTouchAnchorId;
            if (r11 == -1) {
            }
            float r1 = ((float) (java.lang.Math.toDegrees(java.lang.Math.atan2(r1 + r2, r6 + r4)) - r8)) * 62.5f;
            if (java.lang.Float.isNaN(r1)) {
            }
            if (r2 != 0.0f) {
            }
            if (0.0f < r2) {
            }
            r23.mMotionLayout.setState(androidx.constraintlayout.motion.widget.MotionLayout.TransitionState.FINISHED);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void processTouchEvent(MotionEvent event, MotionLayout.MotionTracker velocityTracker, int currentState, MotionScene motionScene) {
        float f;
        int i;
        float f2;
        if (this.mIsRotateMode) {
            processTouchRotateEvent(event, velocityTracker, currentState, motionScene);
            return;
        }
        velocityTracker.addMovement(event);
        int action = event.getAction();
        if (action == 0) {
            this.mLastTouchX = event.getRawX();
            this.mLastTouchY = event.getRawY();
            this.mDragStarted = false;
        } else if (action == 1) {
            this.mDragStarted = false;
            velocityTracker.computeCurrentVelocity(1000);
            float xVelocity = velocityTracker.getXVelocity();
            float yVelocity = velocityTracker.getYVelocity();
            float progress = this.mMotionLayout.getProgress();
            int i2 = this.mTouchAnchorId;
            if (i2 != -1) {
                this.mMotionLayout.getAnchorDpDt(i2, progress, this.mTouchAnchorX, this.mTouchAnchorY, this.mAnchorDpDt);
            } else {
                float min = Math.min(this.mMotionLayout.getWidth(), this.mMotionLayout.getHeight());
                float[] fArr = this.mAnchorDpDt;
                fArr[1] = this.mTouchDirectionY * min;
                fArr[0] = min * this.mTouchDirectionX;
            }
            float f3 = this.mTouchDirectionX;
            float[] fArr2 = this.mAnchorDpDt;
            float f4 = fArr2[0];
            float f5 = fArr2[1];
            if (f3 != 0.0f) {
                f = xVelocity / fArr2[0];
            } else {
                f = yVelocity / fArr2[1];
            }
            float f6 = !Float.isNaN(f) ? (f / 3.0f) + progress : progress;
            if (f6 == 0.0f || f6 == 1.0f || (i = this.mOnTouchUp) == 3) {
                if (0.0f >= f6 || 1.0f <= f6) {
                    this.mMotionLayout.setState(MotionLayout.TransitionState.FINISHED);
                    return;
                }
                return;
            }
            float f7 = ((double) f6) < 0.5d ? 0.0f : 1.0f;
            if (i == 6) {
                if (progress + f < 0.0f) {
                    f = Math.abs(f);
                }
                f7 = 1.0f;
            }
            if (this.mOnTouchUp == 7) {
                if (progress + f > 1.0f) {
                    f = -Math.abs(f);
                }
                f7 = 0.0f;
            }
            this.mMotionLayout.touchAnimateTo(this.mOnTouchUp, f7, f);
            if (0.0f >= progress || 1.0f <= progress) {
                this.mMotionLayout.setState(MotionLayout.TransitionState.FINISHED);
            }
        } else if (action != 2) {
        } else {
            float rawY = event.getRawY() - this.mLastTouchY;
            float rawX = event.getRawX() - this.mLastTouchX;
            if (Math.abs((this.mTouchDirectionX * rawX) + (this.mTouchDirectionY * rawY)) > this.mDragThreshold || this.mDragStarted) {
                float progress2 = this.mMotionLayout.getProgress();
                if (!this.mDragStarted) {
                    this.mDragStarted = true;
                    this.mMotionLayout.setProgress(progress2);
                }
                int i3 = this.mTouchAnchorId;
                if (i3 != -1) {
                    this.mMotionLayout.getAnchorDpDt(i3, progress2, this.mTouchAnchorX, this.mTouchAnchorY, this.mAnchorDpDt);
                } else {
                    float min2 = Math.min(this.mMotionLayout.getWidth(), this.mMotionLayout.getHeight());
                    float[] fArr3 = this.mAnchorDpDt;
                    fArr3[1] = this.mTouchDirectionY * min2;
                    fArr3[0] = min2 * this.mTouchDirectionX;
                }
                float f8 = this.mTouchDirectionX;
                float[] fArr4 = this.mAnchorDpDt;
                if (Math.abs(((f8 * fArr4[0]) + (this.mTouchDirectionY * fArr4[1])) * this.mDragScale) < 0.01d) {
                    float[] fArr5 = this.mAnchorDpDt;
                    fArr5[0] = 0.01f;
                    fArr5[1] = 0.01f;
                }
                if (this.mTouchDirectionX != 0.0f) {
                    f2 = rawX / this.mAnchorDpDt[0];
                } else {
                    f2 = rawY / this.mAnchorDpDt[1];
                }
                float max = Math.max(Math.min(progress2 + f2, 1.0f), 0.0f);
                if (this.mOnTouchUp == 6) {
                    max = Math.max(max, 0.01f);
                }
                if (this.mOnTouchUp == 7) {
                    max = Math.min(max, 0.99f);
                }
                float progress3 = this.mMotionLayout.getProgress();
                if (max != progress3) {
                    int i4 = (progress3 > 0.0f ? 1 : (progress3 == 0.0f ? 0 : -1));
                    if (i4 == 0 || progress3 == 1.0f) {
                        this.mMotionLayout.endTrigger(i4 == 0);
                    }
                    this.mMotionLayout.setProgress(max);
                    velocityTracker.computeCurrentVelocity(1000);
                    this.mMotionLayout.mLastVelocity = this.mTouchDirectionX != 0.0f ? velocityTracker.getXVelocity() / this.mAnchorDpDt[0] : velocityTracker.getYVelocity() / this.mAnchorDpDt[1];
                } else {
                    this.mMotionLayout.mLastVelocity = 0.0f;
                }
                this.mLastTouchX = event.getRawX();
                this.mLastTouchY = event.getRawY();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setDown(float lastTouchX, float lastTouchY) {
        this.mLastTouchX = lastTouchX;
        this.mLastTouchY = lastTouchY;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float getProgressDirection(float dx, float dy) {
        this.mMotionLayout.getAnchorDpDt(this.mTouchAnchorId, this.mMotionLayout.getProgress(), this.mTouchAnchorX, this.mTouchAnchorY, this.mAnchorDpDt);
        float f = this.mTouchDirectionX;
        if (f != 0.0f) {
            float[] fArr = this.mAnchorDpDt;
            if (fArr[0] == 0.0f) {
                fArr[0] = 1.0E-7f;
            }
            return (dx * f) / fArr[0];
        }
        float[] fArr2 = this.mAnchorDpDt;
        if (fArr2[1] == 0.0f) {
            fArr2[1] = 1.0E-7f;
        }
        return (dy * this.mTouchDirectionY) / fArr2[1];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void scrollUp(float dx, float dy) {
        float f;
        this.mDragStarted = false;
        float progress = this.mMotionLayout.getProgress();
        this.mMotionLayout.getAnchorDpDt(this.mTouchAnchorId, progress, this.mTouchAnchorX, this.mTouchAnchorY, this.mAnchorDpDt);
        float f2 = this.mTouchDirectionX;
        float[] fArr = this.mAnchorDpDt;
        float f3 = fArr[0];
        float f4 = this.mTouchDirectionY;
        float f5 = fArr[1];
        if (f2 != 0.0f) {
            f = (dx * f2) / fArr[0];
        } else {
            f = (dy * f4) / fArr[1];
        }
        if (!Float.isNaN(f)) {
            progress += f / 3.0f;
        }
        if (progress != 0.0f) {
            boolean z = progress != 1.0f;
            int i = this.mOnTouchUp;
            if ((i != 3) && z) {
                this.mMotionLayout.touchAnimateTo(i, ((double) progress) >= 0.5d ? 1.0f : 0.0f, f);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void scrollMove(float dx, float dy) {
        float f;
        float progress = this.mMotionLayout.getProgress();
        if (!this.mDragStarted) {
            this.mDragStarted = true;
            this.mMotionLayout.setProgress(progress);
        }
        this.mMotionLayout.getAnchorDpDt(this.mTouchAnchorId, progress, this.mTouchAnchorX, this.mTouchAnchorY, this.mAnchorDpDt);
        float f2 = this.mTouchDirectionX;
        float[] fArr = this.mAnchorDpDt;
        if (Math.abs((f2 * fArr[0]) + (this.mTouchDirectionY * fArr[1])) < 0.01d) {
            float[] fArr2 = this.mAnchorDpDt;
            fArr2[0] = 0.01f;
            fArr2[1] = 0.01f;
        }
        float f3 = this.mTouchDirectionX;
        if (f3 != 0.0f) {
            f = (dx * f3) / this.mAnchorDpDt[0];
        } else {
            f = (dy * this.mTouchDirectionY) / this.mAnchorDpDt[1];
        }
        float max = Math.max(Math.min(progress + f, 1.0f), 0.0f);
        if (max != this.mMotionLayout.getProgress()) {
            this.mMotionLayout.setProgress(max);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setupTouch() {
        View view;
        int i = this.mTouchAnchorId;
        if (i != -1) {
            view = this.mMotionLayout.findViewById(i);
            if (view == null) {
                Log.e(TAG, "cannot find TouchAnchorId @id/" + Debug.getName(this.mMotionLayout.getContext(), this.mTouchAnchorId));
            }
        } else {
            view = null;
        }
        if (view instanceof NestedScrollView) {
            NestedScrollView nestedScrollView = (NestedScrollView) view;
            nestedScrollView.setOnTouchListener(new View.OnTouchListener(this) { // from class: androidx.constraintlayout.motion.widget.TouchResponse.1
                @Override // android.view.View.OnTouchListener
                public boolean onTouch(View view2, MotionEvent motionEvent) {
                    return false;
                }
            });
            nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener(this) { // from class: androidx.constraintlayout.motion.widget.TouchResponse.2
                @Override // androidx.core.widget.NestedScrollView.OnScrollChangeListener
                public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                }
            });
        }
    }

    public void setAnchorId(int id) {
        this.mTouchAnchorId = id;
    }

    public int getAnchorId() {
        return this.mTouchAnchorId;
    }

    public void setTouchAnchorLocation(float x, float y) {
        this.mTouchAnchorX = x;
        this.mTouchAnchorY = y;
    }

    public void setMaxVelocity(float velocity) {
        this.mMaxVelocity = velocity;
    }

    public void setMaxAcceleration(float acceleration) {
        this.mMaxAcceleration = acceleration;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float getMaxAcceleration() {
        return this.mMaxAcceleration;
    }

    public float getMaxVelocity() {
        return this.mMaxVelocity;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean getMoveWhenScrollAtTop() {
        return this.mMoveWhenScrollAtTop;
    }

    public int getAutoCompleteMode() {
        return this.mAutoCompleteMode;
    }

    void setAutoCompleteMode(int autoCompleteMode) {
        this.mAutoCompleteMode = autoCompleteMode;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public RectF getTouchRegion(ViewGroup layout, RectF rect) {
        View findViewById;
        int i = this.mTouchRegionId;
        if (i == -1 || (findViewById = layout.findViewById(i)) == null) {
            return null;
        }
        rect.set(findViewById.getLeft(), findViewById.getTop(), findViewById.getRight(), findViewById.getBottom());
        return rect;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getTouchRegionId() {
        return this.mTouchRegionId;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public RectF getLimitBoundsTo(ViewGroup layout, RectF rect) {
        View findViewById;
        int i = this.mLimitBoundsTo;
        if (i == -1 || (findViewById = layout.findViewById(i)) == null) {
            return null;
        }
        rect.set(findViewById.getLeft(), findViewById.getTop(), findViewById.getRight(), findViewById.getBottom());
        return rect;
    }

    int getLimitBoundsToId() {
        return this.mLimitBoundsTo;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float dot(float dx, float dy) {
        return (dx * this.mTouchDirectionX) + (dy * this.mTouchDirectionY);
    }

    public String toString() {
        return Float.isNaN(this.mTouchDirectionX) ? Key.ROTATION : this.mTouchDirectionX + " , " + this.mTouchDirectionY;
    }

    public int getFlags() {
        return this.mFlags;
    }

    public void setTouchUpMode(int touchUpMode) {
        this.mOnTouchUp = touchUpMode;
    }

    public float getSpringStiffness() {
        return this.mSpringStiffness;
    }

    public float getSpringMass() {
        return this.mSpringMass;
    }

    public float getSpringDamping() {
        return this.mSpringDamping;
    }

    public float getSpringStopThreshold() {
        return this.mSpringStopThreshold;
    }

    public int getSpringBoundary() {
        return this.mSpringBoundary;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isDragStarted() {
        return this.mDragStarted;
    }
}
