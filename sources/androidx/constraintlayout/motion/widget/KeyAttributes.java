package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import androidx.constraintlayout.widget.R;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
/* loaded from: classes.dex */
public class KeyAttributes extends Key {
    private static final boolean DEBUG = false;
    public static final int KEY_TYPE = 1;
    static final String NAME = "KeyAttribute";
    private static final String TAG = "KeyAttributes";
    private String mTransitionEasing;
    private int mCurveFit = -1;
    private boolean mVisibility = false;
    private float mAlpha = Float.NaN;
    private float mElevation = Float.NaN;
    private float mRotation = Float.NaN;
    private float mRotationX = Float.NaN;
    private float mRotationY = Float.NaN;
    private float mPivotX = Float.NaN;
    private float mPivotY = Float.NaN;
    private float mTransitionPathRotate = Float.NaN;
    private float mScaleX = Float.NaN;
    private float mScaleY = Float.NaN;
    private float mTranslationX = Float.NaN;
    private float mTranslationY = Float.NaN;
    private float mTranslationZ = Float.NaN;
    private float mProgress = Float.NaN;

    public KeyAttributes() {
        this.mType = 1;
        this.mCustomConstraints = new HashMap<>();
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    public void load(Context context, AttributeSet attrs) {
        Loader.read(this, context.obtainStyledAttributes(attrs, R.styleable.KeyAttribute));
    }

    int getCurveFit() {
        return this.mCurveFit;
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    public void getAttributeNames(HashSet<String> attributes) {
        if (!Float.isNaN(this.mAlpha)) {
            attributes.add("alpha");
        }
        if (!Float.isNaN(this.mElevation)) {
            attributes.add("elevation");
        }
        if (!Float.isNaN(this.mRotation)) {
            attributes.add(Key.ROTATION);
        }
        if (!Float.isNaN(this.mRotationX)) {
            attributes.add("rotationX");
        }
        if (!Float.isNaN(this.mRotationY)) {
            attributes.add("rotationY");
        }
        if (!Float.isNaN(this.mPivotX)) {
            attributes.add(Key.PIVOT_X);
        }
        if (!Float.isNaN(this.mPivotY)) {
            attributes.add(Key.PIVOT_Y);
        }
        if (!Float.isNaN(this.mTranslationX)) {
            attributes.add("translationX");
        }
        if (!Float.isNaN(this.mTranslationY)) {
            attributes.add("translationY");
        }
        if (!Float.isNaN(this.mTranslationZ)) {
            attributes.add("translationZ");
        }
        if (!Float.isNaN(this.mTransitionPathRotate)) {
            attributes.add("transitionPathRotate");
        }
        if (!Float.isNaN(this.mScaleX)) {
            attributes.add("scaleX");
        }
        if (!Float.isNaN(this.mScaleY)) {
            attributes.add("scaleY");
        }
        if (!Float.isNaN(this.mProgress)) {
            attributes.add("progress");
        }
        if (this.mCustomConstraints.size() > 0) {
            Iterator<String> it = this.mCustomConstraints.keySet().iterator();
            while (it.hasNext()) {
                attributes.add("CUSTOM," + it.next());
            }
        }
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    public void setInterpolation(HashMap<String, Integer> interpolation) {
        if (this.mCurveFit == -1) {
            return;
        }
        if (!Float.isNaN(this.mAlpha)) {
            interpolation.put("alpha", Integer.valueOf(this.mCurveFit));
        }
        if (!Float.isNaN(this.mElevation)) {
            interpolation.put("elevation", Integer.valueOf(this.mCurveFit));
        }
        if (!Float.isNaN(this.mRotation)) {
            interpolation.put(Key.ROTATION, Integer.valueOf(this.mCurveFit));
        }
        if (!Float.isNaN(this.mRotationX)) {
            interpolation.put("rotationX", Integer.valueOf(this.mCurveFit));
        }
        if (!Float.isNaN(this.mRotationY)) {
            interpolation.put("rotationY", Integer.valueOf(this.mCurveFit));
        }
        if (!Float.isNaN(this.mPivotX)) {
            interpolation.put(Key.PIVOT_X, Integer.valueOf(this.mCurveFit));
        }
        if (!Float.isNaN(this.mPivotY)) {
            interpolation.put(Key.PIVOT_Y, Integer.valueOf(this.mCurveFit));
        }
        if (!Float.isNaN(this.mTranslationX)) {
            interpolation.put("translationX", Integer.valueOf(this.mCurveFit));
        }
        if (!Float.isNaN(this.mTranslationY)) {
            interpolation.put("translationY", Integer.valueOf(this.mCurveFit));
        }
        if (!Float.isNaN(this.mTranslationZ)) {
            interpolation.put("translationZ", Integer.valueOf(this.mCurveFit));
        }
        if (!Float.isNaN(this.mTransitionPathRotate)) {
            interpolation.put("transitionPathRotate", Integer.valueOf(this.mCurveFit));
        }
        if (!Float.isNaN(this.mScaleX)) {
            interpolation.put("scaleX", Integer.valueOf(this.mCurveFit));
        }
        if (!Float.isNaN(this.mScaleY)) {
            interpolation.put("scaleY", Integer.valueOf(this.mCurveFit));
        }
        if (!Float.isNaN(this.mProgress)) {
            interpolation.put("progress", Integer.valueOf(this.mCurveFit));
        }
        if (this.mCustomConstraints.size() > 0) {
            Iterator<String> it = this.mCustomConstraints.keySet().iterator();
            while (it.hasNext()) {
                interpolation.put("CUSTOM," + it.next(), Integer.valueOf(this.mCurveFit));
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x009d, code lost:
        if (r1.equals("scaleY") == false) goto L12;
     */
    @Override // androidx.constraintlayout.motion.widget.Key
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void addValues(java.util.HashMap<java.lang.String, androidx.constraintlayout.motion.utils.ViewSpline> r7) {
        java.util.Iterator<java.lang.String> r0 = r7.keySet().iterator();
        while (r0.hasNext()) {
            java.lang.String r1 = r0.next();
            androidx.constraintlayout.motion.utils.ViewSpline r2 = r7.get(r1);
            if (r2 != null) {
                char r4 = 7;
                if (r1.startsWith("CUSTOM")) {
                    androidx.constraintlayout.widget.ConstraintAttribute r1 = r6.mCustomConstraints.get(r1.substring(7));
                    if (r1 != null) {
                        ((androidx.constraintlayout.motion.utils.ViewSpline.CustomSet) r2).setPoint(r6.mFramePosition, r1);
                    }
                } else {
                    r1.hashCode();
                    switch (r1.hashCode()) {
                        case -1249320806:
                            if (r1.equals("rotationX")) {
                                r4 = 0;
                                break;
                            }
                            r4 = 65535;
                            break;
                        case -1249320805:
                            if (r1.equals("rotationY")) {
                                r4 = 1;
                                break;
                            }
                            r4 = 65535;
                            break;
                        case -1225497657:
                            if (r1.equals("translationX")) {
                                r4 = 2;
                                break;
                            }
                            r4 = 65535;
                            break;
                        case -1225497656:
                            if (r1.equals("translationY")) {
                                r4 = 3;
                                break;
                            }
                            r4 = 65535;
                            break;
                        case -1225497655:
                            if (r1.equals("translationZ")) {
                                r4 = 4;
                                break;
                            }
                            r4 = 65535;
                            break;
                        case -1001078227:
                            if (r1.equals("progress")) {
                                r4 = 5;
                                break;
                            }
                            r4 = 65535;
                            break;
                        case -908189618:
                            if (r1.equals("scaleX")) {
                                r4 = 6;
                                break;
                            }
                            r4 = 65535;
                            break;
                        case -908189617:
                            break;
                        case -760884510:
                            if (r1.equals(androidx.constraintlayout.motion.widget.Key.PIVOT_X)) {
                                r4 = '\b';
                                break;
                            }
                            r4 = 65535;
                            break;
                        case -760884509:
                            if (r1.equals(androidx.constraintlayout.motion.widget.Key.PIVOT_Y)) {
                                r4 = '\t';
                                break;
                            }
                            r4 = 65535;
                            break;
                        case -40300674:
                            if (r1.equals(androidx.constraintlayout.motion.widget.Key.ROTATION)) {
                                r4 = '\n';
                                break;
                            }
                            r4 = 65535;
                            break;
                        case -4379043:
                            if (r1.equals("elevation")) {
                                r4 = 11;
                                break;
                            }
                            r4 = 65535;
                            break;
                        case 37232917:
                            if (r1.equals("transitionPathRotate")) {
                                r4 = '\f';
                                break;
                            }
                            r4 = 65535;
                            break;
                        case 92909918:
                            if (r1.equals("alpha")) {
                                r4 = '\r';
                                break;
                            }
                            r4 = 65535;
                            break;
                        default:
                            r4 = 65535;
                            break;
                    }
                    switch (r4) {
                        case 0:
                            if (!java.lang.Float.isNaN(r6.mRotationX)) {
                                r2.setPoint(r6.mFramePosition, r6.mRotationX);
                                break;
                            } else {
                                continue;
                            }
                        case 1:
                            if (!java.lang.Float.isNaN(r6.mRotationY)) {
                                r2.setPoint(r6.mFramePosition, r6.mRotationY);
                                break;
                            } else {
                                continue;
                            }
                        case 2:
                            if (!java.lang.Float.isNaN(r6.mTranslationX)) {
                                r2.setPoint(r6.mFramePosition, r6.mTranslationX);
                                break;
                            } else {
                                continue;
                            }
                        case 3:
                            if (!java.lang.Float.isNaN(r6.mTranslationY)) {
                                r2.setPoint(r6.mFramePosition, r6.mTranslationY);
                                break;
                            } else {
                                continue;
                            }
                        case 4:
                            if (!java.lang.Float.isNaN(r6.mTranslationZ)) {
                                r2.setPoint(r6.mFramePosition, r6.mTranslationZ);
                                break;
                            } else {
                                continue;
                            }
                        case 5:
                            if (!java.lang.Float.isNaN(r6.mProgress)) {
                                r2.setPoint(r6.mFramePosition, r6.mProgress);
                                break;
                            } else {
                                continue;
                            }
                        case 6:
                            if (!java.lang.Float.isNaN(r6.mScaleX)) {
                                r2.setPoint(r6.mFramePosition, r6.mScaleX);
                                break;
                            } else {
                                continue;
                            }
                        case 7:
                            if (!java.lang.Float.isNaN(r6.mScaleY)) {
                                r2.setPoint(r6.mFramePosition, r6.mScaleY);
                                break;
                            } else {
                                continue;
                            }
                        case '\b':
                            if (!java.lang.Float.isNaN(r6.mRotationX)) {
                                r2.setPoint(r6.mFramePosition, r6.mPivotX);
                                break;
                            } else {
                                continue;
                            }
                        case '\t':
                            if (!java.lang.Float.isNaN(r6.mRotationY)) {
                                r2.setPoint(r6.mFramePosition, r6.mPivotY);
                                break;
                            } else {
                                continue;
                            }
                        case '\n':
                            if (!java.lang.Float.isNaN(r6.mRotation)) {
                                r2.setPoint(r6.mFramePosition, r6.mRotation);
                                break;
                            } else {
                                continue;
                            }
                        case 11:
                            if (!java.lang.Float.isNaN(r6.mElevation)) {
                                r2.setPoint(r6.mFramePosition, r6.mElevation);
                                break;
                            } else {
                                continue;
                            }
                        case '\f':
                            if (!java.lang.Float.isNaN(r6.mTransitionPathRotate)) {
                                r2.setPoint(r6.mFramePosition, r6.mTransitionPathRotate);
                                break;
                            } else {
                                continue;
                            }
                        case '\r':
                            if (!java.lang.Float.isNaN(r6.mAlpha)) {
                                r2.setPoint(r6.mFramePosition, r6.mAlpha);
                                break;
                            } else {
                                continue;
                            }
                    }
                }
            }
        }
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    public void setValue(String tag, Object value) {
        tag.hashCode();
        char c = 65535;
        switch (tag.hashCode()) {
            case -1913008125:
                if (tag.equals(Key.MOTIONPROGRESS)) {
                    c = 0;
                    break;
                }
                break;
            case -1812823328:
                if (tag.equals("transitionEasing")) {
                    c = 1;
                    break;
                }
                break;
            case -1249320806:
                if (tag.equals("rotationX")) {
                    c = 2;
                    break;
                }
                break;
            case -1249320805:
                if (tag.equals("rotationY")) {
                    c = 3;
                    break;
                }
                break;
            case -1225497657:
                if (tag.equals("translationX")) {
                    c = 4;
                    break;
                }
                break;
            case -1225497656:
                if (tag.equals("translationY")) {
                    c = 5;
                    break;
                }
                break;
            case -1225497655:
                if (tag.equals("translationZ")) {
                    c = 6;
                    break;
                }
                break;
            case -908189618:
                if (tag.equals("scaleX")) {
                    c = 7;
                    break;
                }
                break;
            case -908189617:
                if (tag.equals("scaleY")) {
                    c = '\b';
                    break;
                }
                break;
            case -760884510:
                if (tag.equals(Key.PIVOT_X)) {
                    c = '\t';
                    break;
                }
                break;
            case -760884509:
                if (tag.equals(Key.PIVOT_Y)) {
                    c = '\n';
                    break;
                }
                break;
            case -40300674:
                if (tag.equals(Key.ROTATION)) {
                    c = 11;
                    break;
                }
                break;
            case -4379043:
                if (tag.equals("elevation")) {
                    c = '\f';
                    break;
                }
                break;
            case 37232917:
                if (tag.equals("transitionPathRotate")) {
                    c = '\r';
                    break;
                }
                break;
            case 92909918:
                if (tag.equals("alpha")) {
                    c = 14;
                    break;
                }
                break;
            case 579057826:
                if (tag.equals("curveFit")) {
                    c = 15;
                    break;
                }
                break;
            case 1941332754:
                if (tag.equals("visibility")) {
                    c = 16;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                this.mProgress = toFloat(value);
                return;
            case 1:
                this.mTransitionEasing = value.toString();
                return;
            case 2:
                this.mRotationX = toFloat(value);
                return;
            case 3:
                this.mRotationY = toFloat(value);
                return;
            case 4:
                this.mTranslationX = toFloat(value);
                return;
            case 5:
                this.mTranslationY = toFloat(value);
                return;
            case 6:
                this.mTranslationZ = toFloat(value);
                return;
            case 7:
                this.mScaleX = toFloat(value);
                return;
            case '\b':
                this.mScaleY = toFloat(value);
                return;
            case '\t':
                this.mPivotX = toFloat(value);
                return;
            case '\n':
                this.mPivotY = toFloat(value);
                return;
            case 11:
                this.mRotation = toFloat(value);
                return;
            case '\f':
                this.mElevation = toFloat(value);
                return;
            case '\r':
                this.mTransitionPathRotate = toFloat(value);
                return;
            case 14:
                this.mAlpha = toFloat(value);
                return;
            case 15:
                this.mCurveFit = toInt(value);
                return;
            case 16:
                this.mVisibility = toBoolean(value);
                return;
            default:
                return;
        }
    }

    /* loaded from: classes.dex */
    private static class Loader {
        private static final int ANDROID_ALPHA = 1;
        private static final int ANDROID_ELEVATION = 2;
        private static final int ANDROID_PIVOT_X = 19;
        private static final int ANDROID_PIVOT_Y = 20;
        private static final int ANDROID_ROTATION = 4;
        private static final int ANDROID_ROTATION_X = 5;
        private static final int ANDROID_ROTATION_Y = 6;
        private static final int ANDROID_SCALE_X = 7;
        private static final int ANDROID_SCALE_Y = 14;
        private static final int ANDROID_TRANSLATION_X = 15;
        private static final int ANDROID_TRANSLATION_Y = 16;
        private static final int ANDROID_TRANSLATION_Z = 17;
        private static final int CURVE_FIT = 13;
        private static final int FRAME_POSITION = 12;
        private static final int PROGRESS = 18;
        private static final int TARGET_ID = 10;
        private static final int TRANSITION_EASING = 9;
        private static final int TRANSITION_PATH_ROTATE = 8;
        private static SparseIntArray mAttrMap;

        private Loader() {
        }

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            mAttrMap = sparseIntArray;
            sparseIntArray.append(R.styleable.KeyAttribute_android_alpha, 1);
            mAttrMap.append(R.styleable.KeyAttribute_android_elevation, 2);
            mAttrMap.append(R.styleable.KeyAttribute_android_rotation, 4);
            mAttrMap.append(R.styleable.KeyAttribute_android_rotationX, 5);
            mAttrMap.append(R.styleable.KeyAttribute_android_rotationY, 6);
            mAttrMap.append(R.styleable.KeyAttribute_android_transformPivotX, 19);
            mAttrMap.append(R.styleable.KeyAttribute_android_transformPivotY, 20);
            mAttrMap.append(R.styleable.KeyAttribute_android_scaleX, 7);
            mAttrMap.append(R.styleable.KeyAttribute_transitionPathRotate, 8);
            mAttrMap.append(R.styleable.KeyAttribute_transitionEasing, 9);
            mAttrMap.append(R.styleable.KeyAttribute_motionTarget, 10);
            mAttrMap.append(R.styleable.KeyAttribute_framePosition, 12);
            mAttrMap.append(R.styleable.KeyAttribute_curveFit, 13);
            mAttrMap.append(R.styleable.KeyAttribute_android_scaleY, 14);
            mAttrMap.append(R.styleable.KeyAttribute_android_translationX, 15);
            mAttrMap.append(R.styleable.KeyAttribute_android_translationY, 16);
            mAttrMap.append(R.styleable.KeyAttribute_android_translationZ, 17);
            mAttrMap.append(R.styleable.KeyAttribute_motionProgress, 18);
        }

        public static void read(KeyAttributes c, TypedArray a) {
            int indexCount = a.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = a.getIndex(i);
                switch (mAttrMap.get(index)) {
                    case 1:
                        c.mAlpha = a.getFloat(index, c.mAlpha);
                        break;
                    case 2:
                        c.mElevation = a.getDimension(index, c.mElevation);
                        break;
                    case 3:
                    case 11:
                    default:
                        Log.e(KeyAttributes.NAME, "unused attribute 0x" + Integer.toHexString(index) + "   " + mAttrMap.get(index));
                        break;
                    case 4:
                        c.mRotation = a.getFloat(index, c.mRotation);
                        break;
                    case 5:
                        c.mRotationX = a.getFloat(index, c.mRotationX);
                        break;
                    case 6:
                        c.mRotationY = a.getFloat(index, c.mRotationY);
                        break;
                    case 7:
                        c.mScaleX = a.getFloat(index, c.mScaleX);
                        break;
                    case 8:
                        c.mTransitionPathRotate = a.getFloat(index, c.mTransitionPathRotate);
                        break;
                    case 9:
                        c.mTransitionEasing = a.getString(index);
                        break;
                    case 10:
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
                    case 12:
                        c.mFramePosition = a.getInt(index, c.mFramePosition);
                        break;
                    case 13:
                        c.mCurveFit = a.getInteger(index, c.mCurveFit);
                        break;
                    case 14:
                        c.mScaleY = a.getFloat(index, c.mScaleY);
                        break;
                    case 15:
                        c.mTranslationX = a.getDimension(index, c.mTranslationX);
                        break;
                    case 16:
                        c.mTranslationY = a.getDimension(index, c.mTranslationY);
                        break;
                    case 17:
                        if (Build.VERSION.SDK_INT >= 21) {
                            c.mTranslationZ = a.getDimension(index, c.mTranslationZ);
                            break;
                        } else {
                            break;
                        }
                    case 18:
                        c.mProgress = a.getFloat(index, c.mProgress);
                        break;
                    case 19:
                        c.mPivotX = a.getDimension(index, c.mPivotX);
                        break;
                    case 20:
                        c.mPivotY = a.getDimension(index, c.mPivotY);
                        break;
                }
            }
        }
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    public Key copy(Key src) {
        super.copy(src);
        KeyAttributes keyAttributes = (KeyAttributes) src;
        this.mCurveFit = keyAttributes.mCurveFit;
        this.mVisibility = keyAttributes.mVisibility;
        this.mAlpha = keyAttributes.mAlpha;
        this.mElevation = keyAttributes.mElevation;
        this.mRotation = keyAttributes.mRotation;
        this.mRotationX = keyAttributes.mRotationX;
        this.mRotationY = keyAttributes.mRotationY;
        this.mPivotX = keyAttributes.mPivotX;
        this.mPivotY = keyAttributes.mPivotY;
        this.mTransitionPathRotate = keyAttributes.mTransitionPathRotate;
        this.mScaleX = keyAttributes.mScaleX;
        this.mScaleY = keyAttributes.mScaleY;
        this.mTranslationX = keyAttributes.mTranslationX;
        this.mTranslationY = keyAttributes.mTranslationY;
        this.mTranslationZ = keyAttributes.mTranslationZ;
        this.mProgress = keyAttributes.mProgress;
        return this;
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    /* renamed from: clone */
    public Key mo3clone() {
        return new KeyAttributes().copy(this);
    }
}