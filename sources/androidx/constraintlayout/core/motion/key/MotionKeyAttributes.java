package androidx.constraintlayout.core.motion.key;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
/* loaded from: classes.dex */
public class MotionKeyAttributes extends MotionKey {
    private static final boolean DEBUG = false;
    public static final int KEY_TYPE = 1;
    static final String NAME = "KeyAttribute";
    private static final String TAG = "KeyAttributes";
    private String mTransitionEasing;
    private int mCurveFit = -1;
    private int mVisibility = 0;
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

    @Override // androidx.constraintlayout.core.motion.key.MotionKey
    /* renamed from: clone */
    public MotionKey mo2clone() {
        return null;
    }

    public MotionKeyAttributes() {
        this.mType = 1;
        this.mCustom = new HashMap<>();
    }

    @Override // androidx.constraintlayout.core.motion.key.MotionKey
    public void getAttributeNames(HashSet<String> hashSet) {
        if (!Float.isNaN(this.mAlpha)) {
            hashSet.add("alpha");
        }
        if (!Float.isNaN(this.mElevation)) {
            hashSet.add("elevation");
        }
        if (!Float.isNaN(this.mRotation)) {
            hashSet.add("rotationZ");
        }
        if (!Float.isNaN(this.mRotationX)) {
            hashSet.add("rotationX");
        }
        if (!Float.isNaN(this.mRotationY)) {
            hashSet.add("rotationY");
        }
        if (!Float.isNaN(this.mPivotX)) {
            hashSet.add("pivotX");
        }
        if (!Float.isNaN(this.mPivotY)) {
            hashSet.add("pivotY");
        }
        if (!Float.isNaN(this.mTranslationX)) {
            hashSet.add("translationX");
        }
        if (!Float.isNaN(this.mTranslationY)) {
            hashSet.add("translationY");
        }
        if (!Float.isNaN(this.mTranslationZ)) {
            hashSet.add("translationZ");
        }
        if (!Float.isNaN(this.mTransitionPathRotate)) {
            hashSet.add("pathRotate");
        }
        if (!Float.isNaN(this.mScaleX)) {
            hashSet.add("scaleX");
        }
        if (!Float.isNaN(this.mScaleY)) {
            hashSet.add("scaleY");
        }
        if (!Float.isNaN(this.mProgress)) {
            hashSet.add("progress");
        }
        if (this.mCustom.size() > 0) {
            Iterator<String> it = this.mCustom.keySet().iterator();
            while (it.hasNext()) {
                hashSet.add("CUSTOM," + it.next());
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x009d, code lost:
        if (r1.equals("pivotX") == false) goto L12;
     */
    @Override // androidx.constraintlayout.core.motion.key.MotionKey
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void addValues(java.util.HashMap<java.lang.String, androidx.constraintlayout.core.motion.utils.SplineSet> r7) {
        java.util.Iterator<java.lang.String> r0 = r7.keySet().iterator();
        while (r0.hasNext()) {
            java.lang.String r1 = r0.next();
            androidx.constraintlayout.core.motion.utils.SplineSet r2 = r7.get(r1);
            if (r2 != null) {
                char r4 = 7;
                if (r1.startsWith("CUSTOM")) {
                    androidx.constraintlayout.core.motion.CustomVariable r1 = r6.mCustom.get(r1.substring(7));
                    if (r1 != null) {
                        ((androidx.constraintlayout.core.motion.utils.SplineSet.CustomSpline) r2).setPoint(r6.mFramePosition, r1);
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
                        case -1249320804:
                            if (r1.equals("rotationZ")) {
                                r4 = 2;
                                break;
                            }
                            r4 = 65535;
                            break;
                        case -1225497657:
                            if (r1.equals("translationX")) {
                                r4 = 3;
                                break;
                            }
                            r4 = 65535;
                            break;
                        case -1225497656:
                            if (r1.equals("translationY")) {
                                r4 = 4;
                                break;
                            }
                            r4 = 65535;
                            break;
                        case -1225497655:
                            if (r1.equals("translationZ")) {
                                r4 = 5;
                                break;
                            }
                            r4 = 65535;
                            break;
                        case -1001078227:
                            if (r1.equals("progress")) {
                                r4 = 6;
                                break;
                            }
                            r4 = 65535;
                            break;
                        case -987906986:
                            break;
                        case -987906985:
                            if (r1.equals("pivotY")) {
                                r4 = '\b';
                                break;
                            }
                            r4 = 65535;
                            break;
                        case -908189618:
                            if (r1.equals("scaleX")) {
                                r4 = '\t';
                                break;
                            }
                            r4 = 65535;
                            break;
                        case -908189617:
                            if (r1.equals("scaleY")) {
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
                        case 92909918:
                            if (r1.equals("alpha")) {
                                r4 = '\f';
                                break;
                            }
                            r4 = 65535;
                            break;
                        case 803192288:
                            if (r1.equals("pathRotate")) {
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
                            if (!java.lang.Float.isNaN(r6.mRotation)) {
                                r2.setPoint(r6.mFramePosition, r6.mRotation);
                                break;
                            } else {
                                continue;
                            }
                        case 3:
                            if (!java.lang.Float.isNaN(r6.mTranslationX)) {
                                r2.setPoint(r6.mFramePosition, r6.mTranslationX);
                                break;
                            } else {
                                continue;
                            }
                        case 4:
                            if (!java.lang.Float.isNaN(r6.mTranslationY)) {
                                r2.setPoint(r6.mFramePosition, r6.mTranslationY);
                                break;
                            } else {
                                continue;
                            }
                        case 5:
                            if (!java.lang.Float.isNaN(r6.mTranslationZ)) {
                                r2.setPoint(r6.mFramePosition, r6.mTranslationZ);
                                break;
                            } else {
                                continue;
                            }
                        case 6:
                            if (!java.lang.Float.isNaN(r6.mProgress)) {
                                r2.setPoint(r6.mFramePosition, r6.mProgress);
                                break;
                            } else {
                                continue;
                            }
                        case 7:
                            if (!java.lang.Float.isNaN(r6.mRotationX)) {
                                r2.setPoint(r6.mFramePosition, r6.mPivotX);
                                break;
                            } else {
                                continue;
                            }
                        case '\b':
                            if (!java.lang.Float.isNaN(r6.mRotationY)) {
                                r2.setPoint(r6.mFramePosition, r6.mPivotY);
                                break;
                            } else {
                                continue;
                            }
                        case '\t':
                            if (!java.lang.Float.isNaN(r6.mScaleX)) {
                                r2.setPoint(r6.mFramePosition, r6.mScaleX);
                                break;
                            } else {
                                continue;
                            }
                        case '\n':
                            if (!java.lang.Float.isNaN(r6.mScaleY)) {
                                r2.setPoint(r6.mFramePosition, r6.mScaleY);
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
                            if (!java.lang.Float.isNaN(r6.mAlpha)) {
                                r2.setPoint(r6.mFramePosition, r6.mAlpha);
                                break;
                            } else {
                                continue;
                            }
                        case '\r':
                            if (!java.lang.Float.isNaN(r6.mTransitionPathRotate)) {
                                r2.setPoint(r6.mFramePosition, r6.mTransitionPathRotate);
                                break;
                            } else {
                                continue;
                            }
                        default:
                            java.lang.System.err.println("not supported by KeyAttributes " + r1);
                            continue;
                    }
                }
            }
        }
    }

    @Override // androidx.constraintlayout.core.motion.key.MotionKey, androidx.constraintlayout.core.motion.utils.TypedValues
    public boolean setValue(int i, int i2) {
        if (i == 100) {
            this.mFramePosition = i2;
            return true;
        } else if (i == 301) {
            this.mCurveFit = i2;
            return true;
        } else if (i == 302) {
            this.mVisibility = i2;
            return true;
        } else if (setValue(i, i2)) {
            return true;
        } else {
            return super.setValue(i, i2);
        }
    }

    @Override // androidx.constraintlayout.core.motion.key.MotionKey, androidx.constraintlayout.core.motion.utils.TypedValues
    public boolean setValue(int i, float f) {
        if (i != 100) {
            switch (i) {
                case 303:
                    this.mAlpha = f;
                    return true;
                case 304:
                    this.mTranslationX = f;
                    return true;
                case 305:
                    this.mTranslationY = f;
                    return true;
                case 306:
                    this.mTranslationZ = f;
                    return true;
                case 307:
                    this.mElevation = f;
                    return true;
                case 308:
                    this.mRotationX = f;
                    return true;
                case 309:
                    this.mRotationY = f;
                    return true;
                case 310:
                    this.mRotation = f;
                    return true;
                case 311:
                    this.mScaleX = f;
                    return true;
                case 312:
                    this.mScaleY = f;
                    return true;
                case 313:
                    this.mPivotX = f;
                    return true;
                case 314:
                    this.mPivotY = f;
                    return true;
                case 315:
                    this.mProgress = f;
                    return true;
                case TypedValues.AttributesType.TYPE_PATH_ROTATE /* 316 */:
                    this.mTransitionPathRotate = f;
                    return true;
                default:
                    return super.setValue(i, f);
            }
        }
        this.mTransitionPathRotate = f;
        return true;
    }

    @Override // androidx.constraintlayout.core.motion.key.MotionKey
    public void setInterpolation(HashMap<String, Integer> hashMap) {
        if (!Float.isNaN(this.mAlpha)) {
            hashMap.put("alpha", Integer.valueOf(this.mCurveFit));
        }
        if (!Float.isNaN(this.mElevation)) {
            hashMap.put("elevation", Integer.valueOf(this.mCurveFit));
        }
        if (!Float.isNaN(this.mRotation)) {
            hashMap.put("rotationZ", Integer.valueOf(this.mCurveFit));
        }
        if (!Float.isNaN(this.mRotationX)) {
            hashMap.put("rotationX", Integer.valueOf(this.mCurveFit));
        }
        if (!Float.isNaN(this.mRotationY)) {
            hashMap.put("rotationY", Integer.valueOf(this.mCurveFit));
        }
        if (!Float.isNaN(this.mPivotX)) {
            hashMap.put("pivotX", Integer.valueOf(this.mCurveFit));
        }
        if (!Float.isNaN(this.mPivotY)) {
            hashMap.put("pivotY", Integer.valueOf(this.mCurveFit));
        }
        if (!Float.isNaN(this.mTranslationX)) {
            hashMap.put("translationX", Integer.valueOf(this.mCurveFit));
        }
        if (!Float.isNaN(this.mTranslationY)) {
            hashMap.put("translationY", Integer.valueOf(this.mCurveFit));
        }
        if (!Float.isNaN(this.mTranslationZ)) {
            hashMap.put("translationZ", Integer.valueOf(this.mCurveFit));
        }
        if (!Float.isNaN(this.mTransitionPathRotate)) {
            hashMap.put("pathRotate", Integer.valueOf(this.mCurveFit));
        }
        if (!Float.isNaN(this.mScaleX)) {
            hashMap.put("scaleX", Integer.valueOf(this.mCurveFit));
        }
        if (!Float.isNaN(this.mScaleY)) {
            hashMap.put("scaleY", Integer.valueOf(this.mCurveFit));
        }
        if (!Float.isNaN(this.mProgress)) {
            hashMap.put("progress", Integer.valueOf(this.mCurveFit));
        }
        if (this.mCustom.size() > 0) {
            Iterator<String> it = this.mCustom.keySet().iterator();
            while (it.hasNext()) {
                hashMap.put("CUSTOM," + it.next(), Integer.valueOf(this.mCurveFit));
            }
        }
    }

    @Override // androidx.constraintlayout.core.motion.key.MotionKey, androidx.constraintlayout.core.motion.utils.TypedValues
    public boolean setValue(int i, String str) {
        if (i == 101) {
            this.mTargetString = str;
            return true;
        } else if (i == 317) {
            this.mTransitionEasing = str;
            return true;
        } else {
            return super.setValue(i, str);
        }
    }

    @Override // androidx.constraintlayout.core.motion.utils.TypedValues
    public int getId(String str) {
        return TypedValues.AttributesType.getId(str);
    }

    public int getCurveFit() {
        return this.mCurveFit;
    }

    public void printAttributes() {
        HashSet<String> hashSet = new HashSet<>();
        getAttributeNames(hashSet);
        System.out.println(" ------------- " + this.mFramePosition + " -------------");
        String[] strArr = (String[]) hashSet.toArray(new String[0]);
        for (int i = 0; i < strArr.length; i++) {
            System.out.println(strArr[i] + ":" + getFloatValue(TypedValues.AttributesType.getId(strArr[i])));
        }
    }

    private float getFloatValue(int i) {
        if (i != 100) {
            switch (i) {
                case 303:
                    return this.mAlpha;
                case 304:
                    return this.mTranslationX;
                case 305:
                    return this.mTranslationY;
                case 306:
                    return this.mTranslationZ;
                case 307:
                    return this.mElevation;
                case 308:
                    return this.mRotationX;
                case 309:
                    return this.mRotationY;
                case 310:
                    return this.mRotation;
                case 311:
                    return this.mScaleX;
                case 312:
                    return this.mScaleY;
                case 313:
                    return this.mPivotX;
                case 314:
                    return this.mPivotY;
                case 315:
                    return this.mProgress;
                case TypedValues.AttributesType.TYPE_PATH_ROTATE /* 316 */:
                    return this.mTransitionPathRotate;
                default:
                    return Float.NaN;
            }
        }
        return this.mFramePosition;
    }
}
