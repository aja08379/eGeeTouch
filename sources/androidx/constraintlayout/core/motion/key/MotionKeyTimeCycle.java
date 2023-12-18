package androidx.constraintlayout.core.motion.key;

import androidx.constraintlayout.core.motion.utils.SplineSet;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
/* loaded from: classes.dex */
public class MotionKeyTimeCycle extends MotionKey {
    public static final int KEY_TYPE = 3;
    static final String NAME = "KeyTimeCycle";
    private static final String TAG = "KeyTimeCycle";
    private String mTransitionEasing;
    private int mCurveFit = -1;
    private float mAlpha = Float.NaN;
    private float mElevation = Float.NaN;
    private float mRotation = Float.NaN;
    private float mRotationX = Float.NaN;
    private float mRotationY = Float.NaN;
    private float mTransitionPathRotate = Float.NaN;
    private float mScaleX = Float.NaN;
    private float mScaleY = Float.NaN;
    private float mTranslationX = Float.NaN;
    private float mTranslationY = Float.NaN;
    private float mTranslationZ = Float.NaN;
    private float mProgress = Float.NaN;
    private int mWaveShape = 0;
    private String mCustomWaveShape = null;
    private float mWavePeriod = Float.NaN;
    private float mWaveOffset = 0.0f;

    @Override // androidx.constraintlayout.core.motion.key.MotionKey
    public void addValues(HashMap<String, SplineSet> hashMap) {
    }

    public MotionKeyTimeCycle() {
        this.mType = 3;
        this.mCustom = new HashMap<>();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x008c, code lost:
        if (r1.equals("scaleX") == false) goto L12;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void addTimeValues(java.util.HashMap<java.lang.String, androidx.constraintlayout.core.motion.utils.TimeCycleSplineSet> r11) {
        java.util.Iterator<java.lang.String> r0 = r11.keySet().iterator();
        while (r0.hasNext()) {
            java.lang.String r1 = r0.next();
            androidx.constraintlayout.core.motion.utils.TimeCycleSplineSet r3 = r11.get(r1);
            if (r3 != null) {
                char r4 = 7;
                if (r1.startsWith("CUSTOM")) {
                    androidx.constraintlayout.core.motion.CustomVariable r6 = r10.mCustom.get(r1.substring(7));
                    if (r6 != null) {
                        ((androidx.constraintlayout.core.motion.utils.TimeCycleSplineSet.CustomVarSet) r3).setPoint(r10.mFramePosition, r6, r10.mWavePeriod, r10.mWaveShape, r10.mWaveOffset);
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
                        case -908189618:
                            break;
                        case -908189617:
                            if (r1.equals("scaleY")) {
                                r4 = '\b';
                                break;
                            }
                            r4 = 65535;
                            break;
                        case -4379043:
                            if (r1.equals("elevation")) {
                                r4 = '\t';
                                break;
                            }
                            r4 = 65535;
                            break;
                        case 92909918:
                            if (r1.equals("alpha")) {
                                r4 = '\n';
                                break;
                            }
                            r4 = 65535;
                            break;
                        case 803192288:
                            if (r1.equals("pathRotate")) {
                                r4 = 11;
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
                            if (!java.lang.Float.isNaN(r10.mRotationX)) {
                                r3.setPoint(r10.mFramePosition, r10.mRotationX, r10.mWavePeriod, r10.mWaveShape, r10.mWaveOffset);
                                break;
                            } else {
                                continue;
                            }
                        case 1:
                            if (!java.lang.Float.isNaN(r10.mRotationY)) {
                                r3.setPoint(r10.mFramePosition, r10.mRotationY, r10.mWavePeriod, r10.mWaveShape, r10.mWaveOffset);
                                break;
                            } else {
                                continue;
                            }
                        case 2:
                            if (!java.lang.Float.isNaN(r10.mRotation)) {
                                r3.setPoint(r10.mFramePosition, r10.mRotation, r10.mWavePeriod, r10.mWaveShape, r10.mWaveOffset);
                                break;
                            } else {
                                continue;
                            }
                        case 3:
                            if (!java.lang.Float.isNaN(r10.mTranslationX)) {
                                r3.setPoint(r10.mFramePosition, r10.mTranslationX, r10.mWavePeriod, r10.mWaveShape, r10.mWaveOffset);
                                break;
                            } else {
                                continue;
                            }
                        case 4:
                            if (!java.lang.Float.isNaN(r10.mTranslationY)) {
                                r3.setPoint(r10.mFramePosition, r10.mTranslationY, r10.mWavePeriod, r10.mWaveShape, r10.mWaveOffset);
                                break;
                            } else {
                                continue;
                            }
                        case 5:
                            if (!java.lang.Float.isNaN(r10.mTranslationZ)) {
                                r3.setPoint(r10.mFramePosition, r10.mTranslationZ, r10.mWavePeriod, r10.mWaveShape, r10.mWaveOffset);
                                break;
                            } else {
                                continue;
                            }
                        case 6:
                            if (!java.lang.Float.isNaN(r10.mProgress)) {
                                r3.setPoint(r10.mFramePosition, r10.mProgress, r10.mWavePeriod, r10.mWaveShape, r10.mWaveOffset);
                                break;
                            } else {
                                continue;
                            }
                        case 7:
                            if (!java.lang.Float.isNaN(r10.mScaleX)) {
                                r3.setPoint(r10.mFramePosition, r10.mScaleX, r10.mWavePeriod, r10.mWaveShape, r10.mWaveOffset);
                                break;
                            } else {
                                continue;
                            }
                        case '\b':
                            if (!java.lang.Float.isNaN(r10.mScaleY)) {
                                r3.setPoint(r10.mFramePosition, r10.mScaleY, r10.mWavePeriod, r10.mWaveShape, r10.mWaveOffset);
                                break;
                            } else {
                                continue;
                            }
                        case '\t':
                            if (!java.lang.Float.isNaN(r10.mTranslationZ)) {
                                r3.setPoint(r10.mFramePosition, r10.mTranslationZ, r10.mWavePeriod, r10.mWaveShape, r10.mWaveOffset);
                                break;
                            } else {
                                continue;
                            }
                        case '\n':
                            if (!java.lang.Float.isNaN(r10.mAlpha)) {
                                r3.setPoint(r10.mFramePosition, r10.mAlpha, r10.mWavePeriod, r10.mWaveShape, r10.mWaveOffset);
                                break;
                            } else {
                                continue;
                            }
                        case 11:
                            if (!java.lang.Float.isNaN(r10.mTransitionPathRotate)) {
                                r3.setPoint(r10.mFramePosition, r10.mTransitionPathRotate, r10.mWavePeriod, r10.mWaveShape, r10.mWaveOffset);
                                break;
                            } else {
                                continue;
                            }
                        default:
                            androidx.constraintlayout.core.motion.utils.Utils.loge("KeyTimeCycles", "UNKNOWN addValues \"" + r1 + "\"");
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
        } else if (i == 421) {
            this.mWaveShape = i2;
            return true;
        } else {
            return super.setValue(i, i2);
        }
    }

    @Override // androidx.constraintlayout.core.motion.key.MotionKey, androidx.constraintlayout.core.motion.utils.TypedValues
    public boolean setValue(int i, float f) {
        if (i == 315) {
            this.mProgress = toFloat(Float.valueOf(f));
            return true;
        } else if (i == 401) {
            this.mCurveFit = toInt(Float.valueOf(f));
            return true;
        } else if (i == 403) {
            this.mAlpha = f;
            return true;
        } else if (i == 416) {
            this.mTransitionPathRotate = toFloat(Float.valueOf(f));
            return true;
        } else if (i == 423) {
            this.mWavePeriod = toFloat(Float.valueOf(f));
            return true;
        } else if (i != 424) {
            switch (i) {
                case 304:
                    this.mTranslationX = toFloat(Float.valueOf(f));
                    return true;
                case 305:
                    this.mTranslationY = toFloat(Float.valueOf(f));
                    return true;
                case 306:
                    this.mTranslationZ = toFloat(Float.valueOf(f));
                    return true;
                case 307:
                    this.mElevation = toFloat(Float.valueOf(f));
                    return true;
                case 308:
                    this.mRotationX = toFloat(Float.valueOf(f));
                    return true;
                case 309:
                    this.mRotationY = toFloat(Float.valueOf(f));
                    return true;
                case 310:
                    this.mRotation = toFloat(Float.valueOf(f));
                    return true;
                case 311:
                    this.mScaleX = toFloat(Float.valueOf(f));
                    return true;
                case 312:
                    this.mScaleY = toFloat(Float.valueOf(f));
                    return true;
                default:
                    return super.setValue(i, f);
            }
        } else {
            this.mWaveOffset = toFloat(Float.valueOf(f));
            return true;
        }
    }

    @Override // androidx.constraintlayout.core.motion.key.MotionKey, androidx.constraintlayout.core.motion.utils.TypedValues
    public boolean setValue(int i, String str) {
        if (i == 420) {
            this.mTransitionEasing = str;
            return true;
        } else if (i == 421) {
            this.mWaveShape = 7;
            this.mCustomWaveShape = str;
            return true;
        } else {
            return super.setValue(i, str);
        }
    }

    @Override // androidx.constraintlayout.core.motion.key.MotionKey, androidx.constraintlayout.core.motion.utils.TypedValues
    public boolean setValue(int i, boolean z) {
        return super.setValue(i, z);
    }

    @Override // androidx.constraintlayout.core.motion.key.MotionKey
    public MotionKeyTimeCycle copy(MotionKey motionKey) {
        super.copy(motionKey);
        MotionKeyTimeCycle motionKeyTimeCycle = (MotionKeyTimeCycle) motionKey;
        this.mTransitionEasing = motionKeyTimeCycle.mTransitionEasing;
        this.mCurveFit = motionKeyTimeCycle.mCurveFit;
        this.mWaveShape = motionKeyTimeCycle.mWaveShape;
        this.mWavePeriod = motionKeyTimeCycle.mWavePeriod;
        this.mWaveOffset = motionKeyTimeCycle.mWaveOffset;
        this.mProgress = motionKeyTimeCycle.mProgress;
        this.mAlpha = motionKeyTimeCycle.mAlpha;
        this.mElevation = motionKeyTimeCycle.mElevation;
        this.mRotation = motionKeyTimeCycle.mRotation;
        this.mTransitionPathRotate = motionKeyTimeCycle.mTransitionPathRotate;
        this.mRotationX = motionKeyTimeCycle.mRotationX;
        this.mRotationY = motionKeyTimeCycle.mRotationY;
        this.mScaleX = motionKeyTimeCycle.mScaleX;
        this.mScaleY = motionKeyTimeCycle.mScaleY;
        this.mTranslationX = motionKeyTimeCycle.mTranslationX;
        this.mTranslationY = motionKeyTimeCycle.mTranslationY;
        this.mTranslationZ = motionKeyTimeCycle.mTranslationZ;
        return this;
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
        if (!Float.isNaN(this.mScaleX)) {
            hashSet.add("scaleX");
        }
        if (!Float.isNaN(this.mScaleY)) {
            hashSet.add("scaleY");
        }
        if (!Float.isNaN(this.mTransitionPathRotate)) {
            hashSet.add("pathRotate");
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
        if (this.mCustom.size() > 0) {
            Iterator<String> it = this.mCustom.keySet().iterator();
            while (it.hasNext()) {
                hashSet.add("CUSTOM," + it.next());
            }
        }
    }

    @Override // androidx.constraintlayout.core.motion.key.MotionKey
    /* renamed from: clone */
    public MotionKey mo2clone() {
        return new MotionKeyTimeCycle().copy((MotionKey) this);
    }

    @Override // androidx.constraintlayout.core.motion.utils.TypedValues
    public int getId(String str) {
        return TypedValues.CycleType.getId(str);
    }
}
