package androidx.constraintlayout.helper.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.constraintlayout.motion.widget.MotionHelper;
import androidx.constraintlayout.widget.R;
/* loaded from: classes.dex */
public class MotionEffect extends MotionHelper {
    public static final int AUTO = -1;
    public static final int EAST = 2;
    public static final int NORTH = 0;
    public static final int SOUTH = 1;
    public static final String TAG = "FadeMove";
    private static final int UNSET = -1;
    public static final int WEST = 3;
    private int fadeMove;
    private float motionEffectAlpha;
    private int motionEffectEnd;
    private int motionEffectStart;
    private boolean motionEffectStrictMove;
    private int motionEffectTranslationX;
    private int motionEffectTranslationY;
    private int viewTransitionId;

    @Override // androidx.constraintlayout.motion.widget.MotionHelper, androidx.constraintlayout.motion.widget.MotionHelperInterface
    public boolean isDecorator() {
        return true;
    }

    public MotionEffect(Context context) {
        super(context);
        this.motionEffectAlpha = 0.1f;
        this.motionEffectStart = 49;
        this.motionEffectEnd = 50;
        this.motionEffectTranslationX = 0;
        this.motionEffectTranslationY = 0;
        this.motionEffectStrictMove = true;
        this.viewTransitionId = -1;
        this.fadeMove = -1;
    }

    public MotionEffect(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.motionEffectAlpha = 0.1f;
        this.motionEffectStart = 49;
        this.motionEffectEnd = 50;
        this.motionEffectTranslationX = 0;
        this.motionEffectTranslationY = 0;
        this.motionEffectStrictMove = true;
        this.viewTransitionId = -1;
        this.fadeMove = -1;
        init(context, attrs);
    }

    public MotionEffect(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.motionEffectAlpha = 0.1f;
        this.motionEffectStart = 49;
        this.motionEffectEnd = 50;
        this.motionEffectTranslationX = 0;
        this.motionEffectTranslationY = 0;
        this.motionEffectStrictMove = true;
        this.viewTransitionId = -1;
        this.fadeMove = -1;
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attrs, R.styleable.MotionEffect);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = obtainStyledAttributes.getIndex(i);
                if (index == R.styleable.MotionEffect_motionEffect_start) {
                    int i2 = obtainStyledAttributes.getInt(index, this.motionEffectStart);
                    this.motionEffectStart = i2;
                    this.motionEffectStart = Math.max(Math.min(i2, 99), 0);
                } else if (index == R.styleable.MotionEffect_motionEffect_end) {
                    int i3 = obtainStyledAttributes.getInt(index, this.motionEffectEnd);
                    this.motionEffectEnd = i3;
                    this.motionEffectEnd = Math.max(Math.min(i3, 99), 0);
                } else if (index == R.styleable.MotionEffect_motionEffect_translationX) {
                    this.motionEffectTranslationX = obtainStyledAttributes.getDimensionPixelOffset(index, this.motionEffectTranslationX);
                } else if (index == R.styleable.MotionEffect_motionEffect_translationY) {
                    this.motionEffectTranslationY = obtainStyledAttributes.getDimensionPixelOffset(index, this.motionEffectTranslationY);
                } else if (index == R.styleable.MotionEffect_motionEffect_alpha) {
                    this.motionEffectAlpha = obtainStyledAttributes.getFloat(index, this.motionEffectAlpha);
                } else if (index == R.styleable.MotionEffect_motionEffect_move) {
                    this.fadeMove = obtainStyledAttributes.getInt(index, this.fadeMove);
                } else if (index == R.styleable.MotionEffect_motionEffect_strict) {
                    this.motionEffectStrictMove = obtainStyledAttributes.getBoolean(index, this.motionEffectStrictMove);
                } else if (index == R.styleable.MotionEffect_motionEffect_viewTransition) {
                    this.viewTransitionId = obtainStyledAttributes.getResourceId(index, this.viewTransitionId);
                }
            }
            int i4 = this.motionEffectStart;
            int i5 = this.motionEffectEnd;
            if (i4 == i5) {
                if (i4 > 0) {
                    this.motionEffectStart = i4 - 1;
                } else {
                    this.motionEffectEnd = i5 + 1;
                }
            }
            obtainStyledAttributes.recycle();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:55:0x018a, code lost:
        if (r14 == 0.0f) goto L62;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x019e, code lost:
        if (r14 == 0.0f) goto L62;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x01ae, code lost:
        if (r15 == 0.0f) goto L62;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x01be, code lost:
        if (r15 == 0.0f) goto L63;
     */
    @Override // androidx.constraintlayout.motion.widget.MotionHelper, androidx.constraintlayout.motion.widget.MotionHelperInterface
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onPreSetup(androidx.constraintlayout.motion.widget.MotionLayout r22, java.util.HashMap<android.view.View, androidx.constraintlayout.motion.widget.MotionController> r23) {
        androidx.constraintlayout.motion.widget.Key r8;
        androidx.constraintlayout.motion.widget.Key r11;
        androidx.constraintlayout.motion.widget.Key r12;
        boolean r14;
        java.util.HashMap<android.view.View, androidx.constraintlayout.motion.widget.MotionController> r1 = r23;
        android.view.View[] r2 = getViews((androidx.constraintlayout.widget.ConstraintLayout) getParent());
        if (r2 == null) {
            android.util.Log.v(androidx.constraintlayout.helper.widget.MotionEffect.TAG, androidx.constraintlayout.motion.widget.Debug.getLoc() + " views = null");
            return;
        }
        androidx.constraintlayout.motion.widget.Key r3 = new androidx.constraintlayout.motion.widget.KeyAttributes();
        androidx.constraintlayout.motion.widget.Key r4 = new androidx.constraintlayout.motion.widget.KeyAttributes();
        r3.setValue("alpha", java.lang.Float.valueOf(r21.motionEffectAlpha));
        r4.setValue("alpha", java.lang.Float.valueOf(r21.motionEffectAlpha));
        r3.setFramePosition(r21.motionEffectStart);
        r4.setFramePosition(r21.motionEffectEnd);
        androidx.constraintlayout.motion.widget.KeyPosition r5 = new androidx.constraintlayout.motion.widget.KeyPosition();
        r5.setFramePosition(r21.motionEffectStart);
        r5.setType(0);
        r5.setValue("percentX", 0);
        r5.setValue("percentY", 0);
        androidx.constraintlayout.motion.widget.KeyPosition r7 = new androidx.constraintlayout.motion.widget.KeyPosition();
        r7.setFramePosition(r21.motionEffectEnd);
        r7.setType(0);
        r7.setValue("percentX", 1);
        r7.setValue("percentY", 1);
        androidx.constraintlayout.motion.widget.Key r9 = null;
        if (r21.motionEffectTranslationX > 0) {
            r8 = new androidx.constraintlayout.motion.widget.KeyAttributes();
            r11 = new androidx.constraintlayout.motion.widget.KeyAttributes();
            r8.setValue("translationX", java.lang.Integer.valueOf(r21.motionEffectTranslationX));
            r8.setFramePosition(r21.motionEffectEnd);
            r11.setValue("translationX", 0);
            r11.setFramePosition(r21.motionEffectEnd - 1);
        } else {
            r8 = null;
            r11 = null;
        }
        if (r21.motionEffectTranslationY > 0) {
            r9 = new androidx.constraintlayout.motion.widget.KeyAttributes();
            r12 = new androidx.constraintlayout.motion.widget.KeyAttributes();
            r9.setValue("translationY", java.lang.Integer.valueOf(r21.motionEffectTranslationY));
            r9.setFramePosition(r21.motionEffectEnd);
            r12.setValue("translationY", 0);
            r12.setFramePosition(r21.motionEffectEnd - 1);
        } else {
            r12 = null;
        }
        int r13 = r21.fadeMove;
        if (r13 == -1) {
            int[] r15 = new int[4];
            for (android.view.View r14 : r2) {
                androidx.constraintlayout.motion.widget.MotionController r14 = r1.get(r14);
                if (r14 != null) {
                    float r19 = r14.getFinalX() - r14.getStartX();
                    float r20 = r14.getFinalY() - r14.getStartY();
                    if (r20 < 0.0f) {
                        r15[1] = r15[1] + 1;
                    }
                    if (r20 > 0.0f) {
                        r15[0] = r15[0] + 1;
                    }
                    if (r19 > 0.0f) {
                        r15[3] = r15[3] + 1;
                    }
                    if (r19 < 0.0f) {
                        r15[2] = r15[2] + 1;
                    }
                }
            }
            int r14 = r15[0];
            r13 = 0;
            for (int r6 = 1; r6 < 4; r6++) {
                if (r14 < r15[r6]) {
                    r13 = r6;
                    r14 = r15[r6];
                }
            }
        }
        int r6 = 0;
        while (r6 < r2.length) {
            androidx.constraintlayout.motion.widget.MotionController r10 = r1.get(r2[r6]);
            if (r10 != null) {
                float r14 = r10.getFinalX() - r10.getStartX();
                float r15 = r10.getFinalY() - r10.getStartY();
                if (r13 == 0) {
                    if (r15 > 0.0f) {
                        if (r21.motionEffectStrictMove) {
                        }
                        r14 = false;
                    }
                    r14 = true;
                } else if (r13 == 1) {
                    if (r15 < 0.0f) {
                        if (r21.motionEffectStrictMove) {
                        }
                        r14 = false;
                    }
                    r14 = true;
                } else if (r13 == 2) {
                    if (r14 < 0.0f) {
                        if (r21.motionEffectStrictMove) {
                        }
                        r14 = false;
                    }
                    r14 = true;
                } else {
                    if (r13 == 3) {
                        if (r14 > 0.0f) {
                            if (r21.motionEffectStrictMove) {
                            }
                            r14 = false;
                        }
                    }
                    r14 = true;
                }
                if (r14) {
                    int r14 = r21.viewTransitionId;
                    if (r14 == -1) {
                        r10.addKey(r3);
                        r10.addKey(r4);
                        r10.addKey(r5);
                        r10.addKey(r7);
                        if (r21.motionEffectTranslationX > 0) {
                            r10.addKey(r8);
                            r10.addKey(r11);
                        }
                        if (r21.motionEffectTranslationY > 0) {
                            r10.addKey(r9);
                            r10.addKey(r12);
                        }
                    } else {
                        r22.applyViewTransition(r14, r10);
                    }
                    r6++;
                    r1 = r23;
                }
            }
            r6++;
            r1 = r23;
        }
    }
}
