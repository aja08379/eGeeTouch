package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.View;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.motion.utils.ViewSpline;
import androidx.constraintlayout.widget.ConstraintAttribute;
import androidx.constraintlayout.widget.R;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
/* loaded from: classes.dex */
public class KeyTrigger extends Key {
    public static final String CROSS = "CROSS";
    public static final int KEY_TYPE = 5;
    static final String NAME = "KeyTrigger";
    public static final String NEGATIVE_CROSS = "negativeCross";
    public static final String POSITIVE_CROSS = "positiveCross";
    public static final String POST_LAYOUT = "postLayout";
    private static final String TAG = "KeyTrigger";
    public static final String TRIGGER_COLLISION_ID = "triggerCollisionId";
    public static final String TRIGGER_COLLISION_VIEW = "triggerCollisionView";
    public static final String TRIGGER_ID = "triggerID";
    public static final String TRIGGER_RECEIVER = "triggerReceiver";
    public static final String TRIGGER_SLACK = "triggerSlack";
    public static final String VIEW_TRANSITION_ON_CROSS = "viewTransitionOnCross";
    public static final String VIEW_TRANSITION_ON_NEGATIVE_CROSS = "viewTransitionOnNegativeCross";
    public static final String VIEW_TRANSITION_ON_POSITIVE_CROSS = "viewTransitionOnPositiveCross";
    private float mFireLastPos;
    private int mCurveFit = -1;
    private String mCross = null;
    private int mTriggerReceiver = UNSET;
    private String mNegativeCross = null;
    private String mPositiveCross = null;
    private int mTriggerID = UNSET;
    private int mTriggerCollisionId = UNSET;
    private View mTriggerCollisionView = null;
    float mTriggerSlack = 0.1f;
    private boolean mFireCrossReset = true;
    private boolean mFireNegativeReset = true;
    private boolean mFirePositiveReset = true;
    private float mFireThreshold = Float.NaN;
    private boolean mPostLayout = false;
    int mViewTransitionOnNegativeCross = UNSET;
    int mViewTransitionOnPositiveCross = UNSET;
    int mViewTransitionOnCross = UNSET;
    RectF mCollisionRect = new RectF();
    RectF mTargetRect = new RectF();
    HashMap<String, Method> mMethodHashMap = new HashMap<>();

    @Override // androidx.constraintlayout.motion.widget.Key
    public void addValues(HashMap<String, ViewSpline> splines) {
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    public void getAttributeNames(HashSet<String> attributes) {
    }

    public KeyTrigger() {
        this.mType = 5;
        this.mCustomConstraints = new HashMap<>();
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    public void load(Context context, AttributeSet attrs) {
        Loader.read(this, context.obtainStyledAttributes(attrs, R.styleable.KeyTrigger), context);
    }

    int getCurveFit() {
        return this.mCurveFit;
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    public void setValue(String tag, Object value) {
        tag.hashCode();
        char c = 65535;
        switch (tag.hashCode()) {
            case -1594793529:
                if (tag.equals("positiveCross")) {
                    c = 0;
                    break;
                }
                break;
            case -966421266:
                if (tag.equals("viewTransitionOnPositiveCross")) {
                    c = 1;
                    break;
                }
                break;
            case -786670827:
                if (tag.equals("triggerCollisionId")) {
                    c = 2;
                    break;
                }
                break;
            case -648752941:
                if (tag.equals("triggerID")) {
                    c = 3;
                    break;
                }
                break;
            case -638126837:
                if (tag.equals("negativeCross")) {
                    c = 4;
                    break;
                }
                break;
            case -76025313:
                if (tag.equals("triggerCollisionView")) {
                    c = 5;
                    break;
                }
                break;
            case -9754574:
                if (tag.equals("viewTransitionOnNegativeCross")) {
                    c = 6;
                    break;
                }
                break;
            case 64397344:
                if (tag.equals("CROSS")) {
                    c = 7;
                    break;
                }
                break;
            case 364489912:
                if (tag.equals("triggerSlack")) {
                    c = '\b';
                    break;
                }
                break;
            case 1301930599:
                if (tag.equals("viewTransitionOnCross")) {
                    c = '\t';
                    break;
                }
                break;
            case 1401391082:
                if (tag.equals("postLayout")) {
                    c = '\n';
                    break;
                }
                break;
            case 1535404999:
                if (tag.equals("triggerReceiver")) {
                    c = 11;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                this.mPositiveCross = value.toString();
                return;
            case 1:
                this.mViewTransitionOnPositiveCross = toInt(value);
                return;
            case 2:
                this.mTriggerCollisionId = toInt(value);
                return;
            case 3:
                this.mTriggerID = toInt(value);
                return;
            case 4:
                this.mNegativeCross = value.toString();
                return;
            case 5:
                this.mTriggerCollisionView = (View) value;
                return;
            case 6:
                this.mViewTransitionOnNegativeCross = toInt(value);
                return;
            case 7:
                this.mCross = value.toString();
                return;
            case '\b':
                this.mTriggerSlack = toFloat(value);
                return;
            case '\t':
                this.mViewTransitionOnCross = toInt(value);
                return;
            case '\n':
                this.mPostLayout = toBoolean(value);
                return;
            case 11:
                this.mTriggerReceiver = toInt(value);
                return;
            default:
                return;
        }
    }

    private void setUpRect(RectF rect, View child, boolean postLayout) {
        rect.top = child.getTop();
        rect.bottom = child.getBottom();
        rect.left = child.getLeft();
        rect.right = child.getRight();
        if (postLayout) {
            child.getMatrix().mapRect(rect);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00a0  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00ce  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void conditionallyFire(float r10, android.view.View r11) {
        boolean r0;
        boolean r4;
        boolean r1;
        boolean r1;
        boolean r4;
        boolean r1;
        if (r9.mTriggerCollisionId != androidx.constraintlayout.motion.widget.KeyTrigger.UNSET) {
            if (r9.mTriggerCollisionView == null) {
                r9.mTriggerCollisionView = ((android.view.ViewGroup) r11.getParent()).findViewById(r9.mTriggerCollisionId);
            }
            setUpRect(r9.mCollisionRect, r9.mTriggerCollisionView, r9.mPostLayout);
            setUpRect(r9.mTargetRect, r11, r9.mPostLayout);
            if (r9.mCollisionRect.intersect(r9.mTargetRect)) {
                if (r9.mFireCrossReset) {
                    r9.mFireCrossReset = false;
                    r0 = true;
                } else {
                    r0 = false;
                }
                if (r9.mFirePositiveReset) {
                    r9.mFirePositiveReset = false;
                    r1 = true;
                } else {
                    r1 = false;
                }
                r9.mFireNegativeReset = true;
                r4 = r1;
                r1 = false;
            } else {
                if (r9.mFireCrossReset) {
                    r0 = false;
                } else {
                    r9.mFireCrossReset = true;
                    r0 = true;
                }
                if (r9.mFireNegativeReset) {
                    r9.mFireNegativeReset = false;
                    r1 = true;
                } else {
                    r1 = false;
                }
                r9.mFirePositiveReset = true;
                r4 = false;
            }
        } else {
            if (r9.mFireCrossReset) {
                float r0 = r9.mFireThreshold;
                if ((r10 - r0) * (r9.mFireLastPos - r0) < 0.0f) {
                    r9.mFireCrossReset = false;
                    r0 = true;
                    if (!r9.mFireNegativeReset) {
                        float r4 = r9.mFireThreshold;
                        float r5 = r10 - r4;
                        if ((r9.mFireLastPos - r4) * r5 < 0.0f && r5 < 0.0f) {
                            r9.mFireNegativeReset = false;
                            r4 = true;
                            if (r9.mFirePositiveReset) {
                                float r5 = r9.mFireThreshold;
                                float r6 = r10 - r5;
                                if ((r9.mFireLastPos - r5) * r6 >= 0.0f || r6 <= 0.0f) {
                                    r1 = false;
                                } else {
                                    r9.mFirePositiveReset = false;
                                    r1 = true;
                                }
                                boolean r8 = r4;
                                r4 = r1;
                                r1 = r8;
                            } else {
                                if (java.lang.Math.abs(r10 - r9.mFireThreshold) > r9.mTriggerSlack) {
                                    r9.mFirePositiveReset = true;
                                }
                                r1 = r4;
                                r4 = false;
                            }
                        }
                    } else if (java.lang.Math.abs(r10 - r9.mFireThreshold) > r9.mTriggerSlack) {
                        r9.mFireNegativeReset = true;
                    }
                    r4 = false;
                    if (r9.mFirePositiveReset) {
                    }
                }
            } else if (java.lang.Math.abs(r10 - r9.mFireThreshold) > r9.mTriggerSlack) {
                r9.mFireCrossReset = true;
            }
            r0 = false;
            if (!r9.mFireNegativeReset) {
            }
            r4 = false;
            if (r9.mFirePositiveReset) {
            }
        }
        r9.mFireLastPos = r10;
        if (r1 || r0 || r4) {
            ((androidx.constraintlayout.motion.widget.MotionLayout) r11.getParent()).fireTrigger(r9.mTriggerID, r4, r10);
        }
        android.view.View r10 = r9.mTriggerReceiver == androidx.constraintlayout.motion.widget.KeyTrigger.UNSET ? r11 : ((androidx.constraintlayout.motion.widget.MotionLayout) r11.getParent()).findViewById(r9.mTriggerReceiver);
        if (r1) {
            java.lang.String r1 = r9.mNegativeCross;
            if (r1 != null) {
                fire(r1, r10);
            }
            if (r9.mViewTransitionOnNegativeCross != androidx.constraintlayout.motion.widget.KeyTrigger.UNSET) {
                ((androidx.constraintlayout.motion.widget.MotionLayout) r11.getParent()).viewTransition(r9.mViewTransitionOnNegativeCross, r10);
            }
        }
        if (r4) {
            java.lang.String r1 = r9.mPositiveCross;
            if (r1 != null) {
                fire(r1, r10);
            }
            if (r9.mViewTransitionOnPositiveCross != androidx.constraintlayout.motion.widget.KeyTrigger.UNSET) {
                ((androidx.constraintlayout.motion.widget.MotionLayout) r11.getParent()).viewTransition(r9.mViewTransitionOnPositiveCross, r10);
            }
        }
        if (r0) {
            java.lang.String r0 = r9.mCross;
            if (r0 != null) {
                fire(r0, r10);
            }
            if (r9.mViewTransitionOnCross != androidx.constraintlayout.motion.widget.KeyTrigger.UNSET) {
                ((androidx.constraintlayout.motion.widget.MotionLayout) r11.getParent()).viewTransition(r9.mViewTransitionOnCross, r10);
            }
        }
    }

    private void fire(String str, View call) {
        Method method;
        if (str == null) {
            return;
        }
        if (str.startsWith(".")) {
            fireCustom(str, call);
            return;
        }
        if (this.mMethodHashMap.containsKey(str)) {
            method = this.mMethodHashMap.get(str);
            if (method == null) {
                return;
            }
        } else {
            method = null;
        }
        if (method == null) {
            try {
                method = call.getClass().getMethod(str, new Class[0]);
                this.mMethodHashMap.put(str, method);
            } catch (NoSuchMethodException unused) {
                this.mMethodHashMap.put(str, null);
                Log.e(TypedValues.TriggerType.NAME, "Could not find method \"" + str + "\"on class " + call.getClass().getSimpleName() + " " + Debug.getName(call));
                return;
            }
        }
        try {
            method.invoke(call, new Object[0]);
        } catch (Exception unused2) {
            Log.e(TypedValues.TriggerType.NAME, "Exception in call \"" + this.mCross + "\"on class " + call.getClass().getSimpleName() + " " + Debug.getName(call));
        }
    }

    private void fireCustom(String str, View view) {
        boolean z = str.length() == 1;
        if (!z) {
            str = str.substring(1).toLowerCase(Locale.ROOT);
        }
        for (String str2 : this.mCustomConstraints.keySet()) {
            String lowerCase = str2.toLowerCase(Locale.ROOT);
            if (z || lowerCase.matches(str)) {
                ConstraintAttribute constraintAttribute = this.mCustomConstraints.get(str2);
                if (constraintAttribute != null) {
                    constraintAttribute.applyCustom(view);
                }
            }
        }
    }

    /* loaded from: classes.dex */
    private static class Loader {
        private static final int COLLISION = 9;
        private static final int CROSS = 4;
        private static final int FRAME_POS = 8;
        private static final int NEGATIVE_CROSS = 1;
        private static final int POSITIVE_CROSS = 2;
        private static final int POST_LAYOUT = 10;
        private static final int TARGET_ID = 7;
        private static final int TRIGGER_ID = 6;
        private static final int TRIGGER_RECEIVER = 11;
        private static final int TRIGGER_SLACK = 5;
        private static final int VT_CROSS = 12;
        private static final int VT_NEGATIVE_CROSS = 13;
        private static final int VT_POSITIVE_CROSS = 14;
        private static SparseIntArray mAttrMap;

        private Loader() {
        }

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            mAttrMap = sparseIntArray;
            sparseIntArray.append(R.styleable.KeyTrigger_framePosition, 8);
            mAttrMap.append(R.styleable.KeyTrigger_onCross, 4);
            mAttrMap.append(R.styleable.KeyTrigger_onNegativeCross, 1);
            mAttrMap.append(R.styleable.KeyTrigger_onPositiveCross, 2);
            mAttrMap.append(R.styleable.KeyTrigger_motionTarget, 7);
            mAttrMap.append(R.styleable.KeyTrigger_triggerId, 6);
            mAttrMap.append(R.styleable.KeyTrigger_triggerSlack, 5);
            mAttrMap.append(R.styleable.KeyTrigger_motion_triggerOnCollision, 9);
            mAttrMap.append(R.styleable.KeyTrigger_motion_postLayoutCollision, 10);
            mAttrMap.append(R.styleable.KeyTrigger_triggerReceiver, 11);
            mAttrMap.append(R.styleable.KeyTrigger_viewTransitionOnCross, 12);
            mAttrMap.append(R.styleable.KeyTrigger_viewTransitionOnNegativeCross, 13);
            mAttrMap.append(R.styleable.KeyTrigger_viewTransitionOnPositiveCross, 14);
        }

        public static void read(KeyTrigger c, TypedArray a, Context context) {
            int indexCount = a.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = a.getIndex(i);
                switch (mAttrMap.get(index)) {
                    case 1:
                        c.mNegativeCross = a.getString(index);
                        break;
                    case 2:
                        c.mPositiveCross = a.getString(index);
                        break;
                    case 3:
                    default:
                        Log.e(TypedValues.TriggerType.NAME, "unused attribute 0x" + Integer.toHexString(index) + "   " + mAttrMap.get(index));
                        break;
                    case 4:
                        c.mCross = a.getString(index);
                        break;
                    case 5:
                        c.mTriggerSlack = a.getFloat(index, c.mTriggerSlack);
                        break;
                    case 6:
                        c.mTriggerID = a.getResourceId(index, c.mTriggerID);
                        break;
                    case 7:
                        if (MotionLayout.IS_IN_EDIT_MODE) {
                            c.mTargetId = a.getResourceId(index, c.mTargetId);
                            if (c.mTargetId == -1) {
                                c.mTargetString = a.getString(index);
                                break;
                            } else {
                                break;
                            }
                        } else if (a.peekValue(index).type == 3) {
                            c.mTargetString = a.getString(index);
                            break;
                        } else {
                            c.mTargetId = a.getResourceId(index, c.mTargetId);
                            break;
                        }
                    case 8:
                        c.mFramePosition = a.getInteger(index, c.mFramePosition);
                        c.mFireThreshold = (c.mFramePosition + 0.5f) / 100.0f;
                        break;
                    case 9:
                        c.mTriggerCollisionId = a.getResourceId(index, c.mTriggerCollisionId);
                        break;
                    case 10:
                        c.mPostLayout = a.getBoolean(index, c.mPostLayout);
                        break;
                    case 11:
                        c.mTriggerReceiver = a.getResourceId(index, c.mTriggerReceiver);
                        break;
                    case 12:
                        c.mViewTransitionOnCross = a.getResourceId(index, c.mViewTransitionOnCross);
                        break;
                    case 13:
                        c.mViewTransitionOnNegativeCross = a.getResourceId(index, c.mViewTransitionOnNegativeCross);
                        break;
                    case 14:
                        c.mViewTransitionOnPositiveCross = a.getResourceId(index, c.mViewTransitionOnPositiveCross);
                        break;
                }
            }
        }
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    public Key copy(Key src) {
        super.copy(src);
        KeyTrigger keyTrigger = (KeyTrigger) src;
        this.mCurveFit = keyTrigger.mCurveFit;
        this.mCross = keyTrigger.mCross;
        this.mTriggerReceiver = keyTrigger.mTriggerReceiver;
        this.mNegativeCross = keyTrigger.mNegativeCross;
        this.mPositiveCross = keyTrigger.mPositiveCross;
        this.mTriggerID = keyTrigger.mTriggerID;
        this.mTriggerCollisionId = keyTrigger.mTriggerCollisionId;
        this.mTriggerCollisionView = keyTrigger.mTriggerCollisionView;
        this.mTriggerSlack = keyTrigger.mTriggerSlack;
        this.mFireCrossReset = keyTrigger.mFireCrossReset;
        this.mFireNegativeReset = keyTrigger.mFireNegativeReset;
        this.mFirePositiveReset = keyTrigger.mFirePositiveReset;
        this.mFireThreshold = keyTrigger.mFireThreshold;
        this.mFireLastPos = keyTrigger.mFireLastPos;
        this.mPostLayout = keyTrigger.mPostLayout;
        this.mCollisionRect = keyTrigger.mCollisionRect;
        this.mTargetRect = keyTrigger.mTargetRect;
        this.mMethodHashMap = keyTrigger.mMethodHashMap;
        return this;
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    /* renamed from: clone */
    public Key mo3clone() {
        return new KeyTrigger().copy(this);
    }
}
