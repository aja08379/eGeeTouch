package androidx.constraintlayout.core.widgets;

import androidx.constraintlayout.core.Cache;
import androidx.constraintlayout.core.LinearSystem;
import androidx.constraintlayout.core.SolverVariable;
import androidx.constraintlayout.core.state.WidgetFrame;
import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.analyzer.ChainRun;
import androidx.constraintlayout.core.widgets.analyzer.HorizontalWidgetRun;
import androidx.constraintlayout.core.widgets.analyzer.VerticalWidgetRun;
import androidx.constraintlayout.core.widgets.analyzer.WidgetRun;
import androidx.exifinterface.media.ExifInterface;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
/* loaded from: classes.dex */
public class ConstraintWidget {
    public static final int ANCHOR_BASELINE = 4;
    public static final int ANCHOR_BOTTOM = 3;
    public static final int ANCHOR_LEFT = 0;
    public static final int ANCHOR_RIGHT = 1;
    public static final int ANCHOR_TOP = 2;
    private static final boolean AUTOTAG_CENTER = false;
    public static final int BOTH = 2;
    public static final int CHAIN_PACKED = 2;
    public static final int CHAIN_SPREAD = 0;
    public static final int CHAIN_SPREAD_INSIDE = 1;
    public static float DEFAULT_BIAS = 0.5f;
    static final int DIMENSION_HORIZONTAL = 0;
    static final int DIMENSION_VERTICAL = 1;
    protected static final int DIRECT = 2;
    public static final int GONE = 8;
    public static final int HORIZONTAL = 0;
    public static final int INVISIBLE = 4;
    public static final int MATCH_CONSTRAINT_PERCENT = 2;
    public static final int MATCH_CONSTRAINT_RATIO = 3;
    public static final int MATCH_CONSTRAINT_RATIO_RESOLVED = 4;
    public static final int MATCH_CONSTRAINT_SPREAD = 0;
    public static final int MATCH_CONSTRAINT_WRAP = 1;
    protected static final int SOLVER = 1;
    public static final int UNKNOWN = -1;
    private static final boolean USE_WRAP_DIMENSION_FOR_SPREAD = false;
    public static final int VERTICAL = 1;
    public static final int VISIBLE = 0;
    private static final int WRAP = -2;
    public static final int WRAP_BEHAVIOR_HORIZONTAL_ONLY = 1;
    public static final int WRAP_BEHAVIOR_INCLUDED = 0;
    public static final int WRAP_BEHAVIOR_SKIPPED = 3;
    public static final int WRAP_BEHAVIOR_VERTICAL_ONLY = 2;
    private boolean OPTIMIZE_WRAP;
    private boolean OPTIMIZE_WRAP_ON_RESOLVED;
    public WidgetFrame frame;
    private boolean hasBaseline;
    public ChainRun horizontalChainRun;
    public int horizontalGroup;
    public HorizontalWidgetRun horizontalRun;
    private boolean horizontalSolvingPass;
    private boolean inPlaceholder;
    public boolean[] isTerminalWidget;
    protected ArrayList<ConstraintAnchor> mAnchors;
    private boolean mAnimated;
    public ConstraintAnchor mBaseline;
    int mBaselineDistance;
    public ConstraintAnchor mBottom;
    boolean mBottomHasCentered;
    public ConstraintAnchor mCenter;
    ConstraintAnchor mCenterX;
    ConstraintAnchor mCenterY;
    private float mCircleConstraintAngle;
    private Object mCompanionWidget;
    private int mContainerItemSkip;
    private String mDebugName;
    public float mDimensionRatio;
    protected int mDimensionRatioSide;
    int mDistToBottom;
    int mDistToLeft;
    int mDistToRight;
    int mDistToTop;
    boolean mGroupsToSolver;
    int mHeight;
    private int mHeightOverride;
    float mHorizontalBiasPercent;
    boolean mHorizontalChainFixedPosition;
    int mHorizontalChainStyle;
    ConstraintWidget mHorizontalNextWidget;
    public int mHorizontalResolution;
    boolean mHorizontalWrapVisited;
    private boolean mInVirtualLayout;
    public boolean mIsHeightWrapContent;
    private boolean[] mIsInBarrier;
    public boolean mIsWidthWrapContent;
    private int mLastHorizontalMeasureSpec;
    private int mLastVerticalMeasureSpec;
    public ConstraintAnchor mLeft;
    boolean mLeftHasCentered;
    public ConstraintAnchor[] mListAnchors;
    public DimensionBehaviour[] mListDimensionBehaviors;
    protected ConstraintWidget[] mListNextMatchConstraintsWidget;
    public int mMatchConstraintDefaultHeight;
    public int mMatchConstraintDefaultWidth;
    public int mMatchConstraintMaxHeight;
    public int mMatchConstraintMaxWidth;
    public int mMatchConstraintMinHeight;
    public int mMatchConstraintMinWidth;
    public float mMatchConstraintPercentHeight;
    public float mMatchConstraintPercentWidth;
    private int[] mMaxDimension;
    private boolean mMeasureRequested;
    protected int mMinHeight;
    protected int mMinWidth;
    protected ConstraintWidget[] mNextChainWidget;
    protected int mOffsetX;
    protected int mOffsetY;
    public ConstraintWidget mParent;
    int mRelX;
    int mRelY;
    float mResolvedDimensionRatio;
    int mResolvedDimensionRatioSide;
    boolean mResolvedHasRatio;
    public int[] mResolvedMatchConstraintDefault;
    public ConstraintAnchor mRight;
    boolean mRightHasCentered;
    public ConstraintAnchor mTop;
    boolean mTopHasCentered;
    private String mType;
    float mVerticalBiasPercent;
    boolean mVerticalChainFixedPosition;
    int mVerticalChainStyle;
    ConstraintWidget mVerticalNextWidget;
    public int mVerticalResolution;
    boolean mVerticalWrapVisited;
    private int mVisibility;
    public float[] mWeight;
    int mWidth;
    private int mWidthOverride;
    private int mWrapBehaviorInParent;
    protected int mX;
    protected int mY;
    public boolean measured;
    private boolean resolvedHorizontal;
    private boolean resolvedVertical;
    public WidgetRun[] run;
    public String stringId;
    public ChainRun verticalChainRun;
    public int verticalGroup;
    public VerticalWidgetRun verticalRun;
    private boolean verticalSolvingPass;

    /* loaded from: classes.dex */
    public enum DimensionBehaviour {
        FIXED,
        WRAP_CONTENT,
        MATCH_CONSTRAINT,
        MATCH_PARENT
    }

    public WidgetRun getRun(int i) {
        if (i == 0) {
            return this.horizontalRun;
        }
        if (i == 1) {
            return this.verticalRun;
        }
        return null;
    }

    public void setFinalFrame(int i, int i2, int i3, int i4, int i5, int i6) {
        setFrame(i, i2, i3, i4);
        setBaselineDistance(i5);
        if (i6 == 0) {
            this.resolvedHorizontal = true;
            this.resolvedVertical = false;
        } else if (i6 == 1) {
            this.resolvedHorizontal = false;
            this.resolvedVertical = true;
        } else if (i6 == 2) {
            this.resolvedHorizontal = true;
            this.resolvedVertical = true;
        } else {
            this.resolvedHorizontal = false;
            this.resolvedVertical = false;
        }
    }

    public void setFinalLeft(int i) {
        this.mLeft.setFinalValue(i);
        this.mX = i;
    }

    public void setFinalTop(int i) {
        this.mTop.setFinalValue(i);
        this.mY = i;
    }

    public void resetSolvingPassFlag() {
        this.horizontalSolvingPass = false;
        this.verticalSolvingPass = false;
    }

    public boolean isHorizontalSolvingPassDone() {
        return this.horizontalSolvingPass;
    }

    public boolean isVerticalSolvingPassDone() {
        return this.verticalSolvingPass;
    }

    public void markHorizontalSolvingPassDone() {
        this.horizontalSolvingPass = true;
    }

    public void markVerticalSolvingPassDone() {
        this.verticalSolvingPass = true;
    }

    public void setFinalHorizontal(int i, int i2) {
        if (this.resolvedHorizontal) {
            return;
        }
        this.mLeft.setFinalValue(i);
        this.mRight.setFinalValue(i2);
        this.mX = i;
        this.mWidth = i2 - i;
        this.resolvedHorizontal = true;
    }

    public void setFinalVertical(int i, int i2) {
        if (this.resolvedVertical) {
            return;
        }
        this.mTop.setFinalValue(i);
        this.mBottom.setFinalValue(i2);
        this.mY = i;
        this.mHeight = i2 - i;
        if (this.hasBaseline) {
            this.mBaseline.setFinalValue(i + this.mBaselineDistance);
        }
        this.resolvedVertical = true;
    }

    public void setFinalBaseline(int i) {
        if (this.hasBaseline) {
            int i2 = i - this.mBaselineDistance;
            int i3 = this.mHeight + i2;
            this.mY = i2;
            this.mTop.setFinalValue(i2);
            this.mBottom.setFinalValue(i3);
            this.mBaseline.setFinalValue(i);
            this.resolvedVertical = true;
        }
    }

    public boolean isResolvedHorizontally() {
        return this.resolvedHorizontal || (this.mLeft.hasFinalValue() && this.mRight.hasFinalValue());
    }

    public boolean isResolvedVertically() {
        return this.resolvedVertical || (this.mTop.hasFinalValue() && this.mBottom.hasFinalValue());
    }

    public void resetFinalResolution() {
        this.resolvedHorizontal = false;
        this.resolvedVertical = false;
        this.horizontalSolvingPass = false;
        this.verticalSolvingPass = false;
        int size = this.mAnchors.size();
        for (int i = 0; i < size; i++) {
            this.mAnchors.get(i).resetFinalResolution();
        }
    }

    public void ensureMeasureRequested() {
        this.mMeasureRequested = true;
    }

    public boolean hasDependencies() {
        int size = this.mAnchors.size();
        for (int i = 0; i < size; i++) {
            if (this.mAnchors.get(i).hasDependents()) {
                return true;
            }
        }
        return false;
    }

    public boolean hasDanglingDimension(int i) {
        if (i == 0) {
            return (this.mLeft.mTarget != null ? 1 : 0) + (this.mRight.mTarget != null ? 1 : 0) < 2;
        }
        return ((this.mTop.mTarget != null ? 1 : 0) + (this.mBottom.mTarget != null ? 1 : 0)) + (this.mBaseline.mTarget != null ? 1 : 0) < 2;
    }

    public boolean hasResolvedTargets(int i, int i2) {
        return i == 0 ? this.mLeft.mTarget != null && this.mLeft.mTarget.hasFinalValue() && this.mRight.mTarget != null && this.mRight.mTarget.hasFinalValue() && (this.mRight.mTarget.getFinalValue() - this.mRight.getMargin()) - (this.mLeft.mTarget.getFinalValue() + this.mLeft.getMargin()) >= i2 : this.mTop.mTarget != null && this.mTop.mTarget.hasFinalValue() && this.mBottom.mTarget != null && this.mBottom.mTarget.hasFinalValue() && (this.mBottom.mTarget.getFinalValue() - this.mBottom.getMargin()) - (this.mTop.mTarget.getFinalValue() + this.mTop.getMargin()) >= i2;
        return false;
    }

    public boolean isInVirtualLayout() {
        return this.mInVirtualLayout;
    }

    public void setInVirtualLayout(boolean z) {
        this.mInVirtualLayout = z;
    }

    public int getMaxHeight() {
        return this.mMaxDimension[1];
    }

    public int getMaxWidth() {
        return this.mMaxDimension[0];
    }

    public void setMaxWidth(int i) {
        this.mMaxDimension[0] = i;
    }

    public void setMaxHeight(int i) {
        this.mMaxDimension[1] = i;
    }

    public boolean isSpreadWidth() {
        return this.mMatchConstraintDefaultWidth == 0 && this.mDimensionRatio == 0.0f && this.mMatchConstraintMinWidth == 0 && this.mMatchConstraintMaxWidth == 0 && this.mListDimensionBehaviors[0] == DimensionBehaviour.MATCH_CONSTRAINT;
    }

    public boolean isSpreadHeight() {
        return this.mMatchConstraintDefaultHeight == 0 && this.mDimensionRatio == 0.0f && this.mMatchConstraintMinHeight == 0 && this.mMatchConstraintMaxHeight == 0 && this.mListDimensionBehaviors[1] == DimensionBehaviour.MATCH_CONSTRAINT;
    }

    public void setHasBaseline(boolean z) {
        this.hasBaseline = z;
    }

    public boolean getHasBaseline() {
        return this.hasBaseline;
    }

    public boolean isInPlaceholder() {
        return this.inPlaceholder;
    }

    public void setInPlaceholder(boolean z) {
        this.inPlaceholder = z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setInBarrier(int i, boolean z) {
        this.mIsInBarrier[i] = z;
    }

    public boolean isInBarrier(int i) {
        return this.mIsInBarrier[i];
    }

    public void setMeasureRequested(boolean z) {
        this.mMeasureRequested = z;
    }

    public boolean isMeasureRequested() {
        return this.mMeasureRequested && this.mVisibility != 8;
    }

    public void setWrapBehaviorInParent(int i) {
        if (i < 0 || i > 3) {
            return;
        }
        this.mWrapBehaviorInParent = i;
    }

    public int getWrapBehaviorInParent() {
        return this.mWrapBehaviorInParent;
    }

    public int getLastHorizontalMeasureSpec() {
        return this.mLastHorizontalMeasureSpec;
    }

    public int getLastVerticalMeasureSpec() {
        return this.mLastVerticalMeasureSpec;
    }

    public void setLastMeasureSpec(int i, int i2) {
        this.mLastHorizontalMeasureSpec = i;
        this.mLastVerticalMeasureSpec = i2;
        setMeasureRequested(false);
    }

    public void reset() {
        this.mLeft.reset();
        this.mTop.reset();
        this.mRight.reset();
        this.mBottom.reset();
        this.mBaseline.reset();
        this.mCenterX.reset();
        this.mCenterY.reset();
        this.mCenter.reset();
        this.mParent = null;
        this.mCircleConstraintAngle = 0.0f;
        this.mWidth = 0;
        this.mHeight = 0;
        this.mDimensionRatio = 0.0f;
        this.mDimensionRatioSide = -1;
        this.mX = 0;
        this.mY = 0;
        this.mOffsetX = 0;
        this.mOffsetY = 0;
        this.mBaselineDistance = 0;
        this.mMinWidth = 0;
        this.mMinHeight = 0;
        float f = DEFAULT_BIAS;
        this.mHorizontalBiasPercent = f;
        this.mVerticalBiasPercent = f;
        this.mListDimensionBehaviors[0] = DimensionBehaviour.FIXED;
        this.mListDimensionBehaviors[1] = DimensionBehaviour.FIXED;
        this.mCompanionWidget = null;
        this.mContainerItemSkip = 0;
        this.mVisibility = 0;
        this.mType = null;
        this.mHorizontalWrapVisited = false;
        this.mVerticalWrapVisited = false;
        this.mHorizontalChainStyle = 0;
        this.mVerticalChainStyle = 0;
        this.mHorizontalChainFixedPosition = false;
        this.mVerticalChainFixedPosition = false;
        float[] fArr = this.mWeight;
        fArr[0] = -1.0f;
        fArr[1] = -1.0f;
        this.mHorizontalResolution = -1;
        this.mVerticalResolution = -1;
        int[] iArr = this.mMaxDimension;
        iArr[0] = Integer.MAX_VALUE;
        iArr[1] = Integer.MAX_VALUE;
        this.mMatchConstraintDefaultWidth = 0;
        this.mMatchConstraintDefaultHeight = 0;
        this.mMatchConstraintPercentWidth = 1.0f;
        this.mMatchConstraintPercentHeight = 1.0f;
        this.mMatchConstraintMaxWidth = Integer.MAX_VALUE;
        this.mMatchConstraintMaxHeight = Integer.MAX_VALUE;
        this.mMatchConstraintMinWidth = 0;
        this.mMatchConstraintMinHeight = 0;
        this.mResolvedHasRatio = false;
        this.mResolvedDimensionRatioSide = -1;
        this.mResolvedDimensionRatio = 1.0f;
        this.mGroupsToSolver = false;
        boolean[] zArr = this.isTerminalWidget;
        zArr[0] = true;
        zArr[1] = true;
        this.mInVirtualLayout = false;
        boolean[] zArr2 = this.mIsInBarrier;
        zArr2[0] = false;
        zArr2[1] = false;
        this.mMeasureRequested = true;
        int[] iArr2 = this.mResolvedMatchConstraintDefault;
        iArr2[0] = 0;
        iArr2[1] = 0;
        this.mWidthOverride = -1;
        this.mHeightOverride = -1;
    }

    private void serializeAnchor(StringBuilder sb, String str, ConstraintAnchor constraintAnchor) {
        if (constraintAnchor.mTarget == null) {
            return;
        }
        sb.append(str);
        sb.append(" : [ '");
        sb.append(constraintAnchor.mTarget);
        sb.append("',");
        sb.append(constraintAnchor.mMargin);
        sb.append(",");
        sb.append(constraintAnchor.mGoneMargin);
        sb.append(",");
        sb.append(" ] ,\n");
    }

    private void serializeCircle(StringBuilder sb, ConstraintAnchor constraintAnchor, float f) {
        if (constraintAnchor.mTarget == null) {
            return;
        }
        sb.append("circle : [ '");
        sb.append(constraintAnchor.mTarget);
        sb.append("',");
        sb.append(constraintAnchor.mMargin);
        sb.append(",");
        sb.append(f);
        sb.append(",");
        sb.append(" ] ,\n");
    }

    private void serializeAttribute(StringBuilder sb, String str, float f, float f2) {
        if (f == f2) {
            return;
        }
        sb.append(str);
        sb.append(" :   ");
        sb.append(f);
        sb.append(",\n");
    }

    private void serializeAttribute(StringBuilder sb, String str, int i, int i2) {
        if (i == i2) {
            return;
        }
        sb.append(str);
        sb.append(" :   ");
        sb.append(i);
        sb.append(",\n");
    }

    private void serializeDimensionRatio(StringBuilder sb, String str, float f, int i) {
        if (f == 0.0f) {
            return;
        }
        sb.append(str);
        sb.append(" :  [");
        sb.append(f);
        sb.append(",");
        sb.append(i);
        sb.append("");
        sb.append("],\n");
    }

    private void serializeSize(StringBuilder sb, String str, int i, int i2, int i3, int i4, int i5, int i6, float f, float f2) {
        sb.append(str);
        sb.append(" :  {\n");
        serializeAttribute(sb, "size", i, Integer.MIN_VALUE);
        serializeAttribute(sb, "min", i2, 0);
        serializeAttribute(sb, "max", i3, Integer.MAX_VALUE);
        serializeAttribute(sb, "matchMin", i5, 0);
        serializeAttribute(sb, "matchDef", i6, 0);
        serializeAttribute(sb, "matchPercent", i6, 1);
        sb.append("},\n");
    }

    public StringBuilder serialize(StringBuilder sb) {
        sb.append("{\n");
        serializeAnchor(sb, "left", this.mLeft);
        serializeAnchor(sb, "top", this.mTop);
        serializeAnchor(sb, "right", this.mRight);
        serializeAnchor(sb, "bottom", this.mBottom);
        serializeAnchor(sb, "baseline", this.mBaseline);
        serializeAnchor(sb, "centerX", this.mCenterX);
        serializeAnchor(sb, "centerY", this.mCenterY);
        serializeCircle(sb, this.mCenter, this.mCircleConstraintAngle);
        serializeSize(sb, "width", this.mWidth, this.mMinWidth, this.mMaxDimension[0], this.mWidthOverride, this.mMatchConstraintMinWidth, this.mMatchConstraintDefaultWidth, this.mMatchConstraintPercentWidth, this.mWeight[0]);
        serializeSize(sb, "height", this.mHeight, this.mMinHeight, this.mMaxDimension[1], this.mHeightOverride, this.mMatchConstraintMinHeight, this.mMatchConstraintDefaultHeight, this.mMatchConstraintPercentHeight, this.mWeight[1]);
        serializeDimensionRatio(sb, "dimensionRatio", this.mDimensionRatio, this.mDimensionRatioSide);
        serializeAttribute(sb, "horizontalBias", this.mHorizontalBiasPercent, DEFAULT_BIAS);
        serializeAttribute(sb, "verticalBias", this.mVerticalBiasPercent, DEFAULT_BIAS);
        sb.append("}\n");
        return sb;
    }

    public boolean oppositeDimensionDependsOn(int i) {
        char c = i == 0 ? (char) 1 : (char) 0;
        DimensionBehaviour[] dimensionBehaviourArr = this.mListDimensionBehaviors;
        return dimensionBehaviourArr[i] == DimensionBehaviour.MATCH_CONSTRAINT && dimensionBehaviourArr[c] == DimensionBehaviour.MATCH_CONSTRAINT;
    }

    public boolean oppositeDimensionsTied() {
        return this.mListDimensionBehaviors[0] == DimensionBehaviour.MATCH_CONSTRAINT && this.mListDimensionBehaviors[1] == DimensionBehaviour.MATCH_CONSTRAINT;
    }

    public boolean hasDimensionOverride() {
        return (this.mWidthOverride == -1 && this.mHeightOverride == -1) ? false : true;
    }

    public ConstraintWidget() {
        this.measured = false;
        this.run = new WidgetRun[2];
        this.horizontalRun = null;
        this.verticalRun = null;
        this.isTerminalWidget = new boolean[]{true, true};
        this.mResolvedHasRatio = false;
        this.mMeasureRequested = true;
        this.OPTIMIZE_WRAP = false;
        this.OPTIMIZE_WRAP_ON_RESOLVED = true;
        this.mWidthOverride = -1;
        this.mHeightOverride = -1;
        this.frame = new WidgetFrame(this);
        this.resolvedHorizontal = false;
        this.resolvedVertical = false;
        this.horizontalSolvingPass = false;
        this.verticalSolvingPass = false;
        this.mHorizontalResolution = -1;
        this.mVerticalResolution = -1;
        this.mWrapBehaviorInParent = 0;
        this.mMatchConstraintDefaultWidth = 0;
        this.mMatchConstraintDefaultHeight = 0;
        this.mResolvedMatchConstraintDefault = new int[2];
        this.mMatchConstraintMinWidth = 0;
        this.mMatchConstraintMaxWidth = 0;
        this.mMatchConstraintPercentWidth = 1.0f;
        this.mMatchConstraintMinHeight = 0;
        this.mMatchConstraintMaxHeight = 0;
        this.mMatchConstraintPercentHeight = 1.0f;
        this.mResolvedDimensionRatioSide = -1;
        this.mResolvedDimensionRatio = 1.0f;
        this.mMaxDimension = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE};
        this.mCircleConstraintAngle = 0.0f;
        this.hasBaseline = false;
        this.mInVirtualLayout = false;
        this.mLastHorizontalMeasureSpec = 0;
        this.mLastVerticalMeasureSpec = 0;
        this.mLeft = new ConstraintAnchor(this, ConstraintAnchor.Type.LEFT);
        this.mTop = new ConstraintAnchor(this, ConstraintAnchor.Type.TOP);
        this.mRight = new ConstraintAnchor(this, ConstraintAnchor.Type.RIGHT);
        this.mBottom = new ConstraintAnchor(this, ConstraintAnchor.Type.BOTTOM);
        this.mBaseline = new ConstraintAnchor(this, ConstraintAnchor.Type.BASELINE);
        this.mCenterX = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER_X);
        this.mCenterY = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER_Y);
        ConstraintAnchor constraintAnchor = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER);
        this.mCenter = constraintAnchor;
        this.mListAnchors = new ConstraintAnchor[]{this.mLeft, this.mRight, this.mTop, this.mBottom, this.mBaseline, constraintAnchor};
        this.mAnchors = new ArrayList<>();
        this.mIsInBarrier = new boolean[2];
        this.mListDimensionBehaviors = new DimensionBehaviour[]{DimensionBehaviour.FIXED, DimensionBehaviour.FIXED};
        this.mParent = null;
        this.mWidth = 0;
        this.mHeight = 0;
        this.mDimensionRatio = 0.0f;
        this.mDimensionRatioSide = -1;
        this.mX = 0;
        this.mY = 0;
        this.mRelX = 0;
        this.mRelY = 0;
        this.mOffsetX = 0;
        this.mOffsetY = 0;
        this.mBaselineDistance = 0;
        float f = DEFAULT_BIAS;
        this.mHorizontalBiasPercent = f;
        this.mVerticalBiasPercent = f;
        this.mContainerItemSkip = 0;
        this.mVisibility = 0;
        this.mAnimated = false;
        this.mDebugName = null;
        this.mType = null;
        this.mGroupsToSolver = false;
        this.mHorizontalChainStyle = 0;
        this.mVerticalChainStyle = 0;
        this.mWeight = new float[]{-1.0f, -1.0f};
        this.mListNextMatchConstraintsWidget = new ConstraintWidget[]{null, null};
        this.mNextChainWidget = new ConstraintWidget[]{null, null};
        this.mHorizontalNextWidget = null;
        this.mVerticalNextWidget = null;
        this.horizontalGroup = -1;
        this.verticalGroup = -1;
        addAnchors();
    }

    public ConstraintWidget(String str) {
        this.measured = false;
        this.run = new WidgetRun[2];
        this.horizontalRun = null;
        this.verticalRun = null;
        this.isTerminalWidget = new boolean[]{true, true};
        this.mResolvedHasRatio = false;
        this.mMeasureRequested = true;
        this.OPTIMIZE_WRAP = false;
        this.OPTIMIZE_WRAP_ON_RESOLVED = true;
        this.mWidthOverride = -1;
        this.mHeightOverride = -1;
        this.frame = new WidgetFrame(this);
        this.resolvedHorizontal = false;
        this.resolvedVertical = false;
        this.horizontalSolvingPass = false;
        this.verticalSolvingPass = false;
        this.mHorizontalResolution = -1;
        this.mVerticalResolution = -1;
        this.mWrapBehaviorInParent = 0;
        this.mMatchConstraintDefaultWidth = 0;
        this.mMatchConstraintDefaultHeight = 0;
        this.mResolvedMatchConstraintDefault = new int[2];
        this.mMatchConstraintMinWidth = 0;
        this.mMatchConstraintMaxWidth = 0;
        this.mMatchConstraintPercentWidth = 1.0f;
        this.mMatchConstraintMinHeight = 0;
        this.mMatchConstraintMaxHeight = 0;
        this.mMatchConstraintPercentHeight = 1.0f;
        this.mResolvedDimensionRatioSide = -1;
        this.mResolvedDimensionRatio = 1.0f;
        this.mMaxDimension = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE};
        this.mCircleConstraintAngle = 0.0f;
        this.hasBaseline = false;
        this.mInVirtualLayout = false;
        this.mLastHorizontalMeasureSpec = 0;
        this.mLastVerticalMeasureSpec = 0;
        this.mLeft = new ConstraintAnchor(this, ConstraintAnchor.Type.LEFT);
        this.mTop = new ConstraintAnchor(this, ConstraintAnchor.Type.TOP);
        this.mRight = new ConstraintAnchor(this, ConstraintAnchor.Type.RIGHT);
        this.mBottom = new ConstraintAnchor(this, ConstraintAnchor.Type.BOTTOM);
        this.mBaseline = new ConstraintAnchor(this, ConstraintAnchor.Type.BASELINE);
        this.mCenterX = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER_X);
        this.mCenterY = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER_Y);
        ConstraintAnchor constraintAnchor = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER);
        this.mCenter = constraintAnchor;
        this.mListAnchors = new ConstraintAnchor[]{this.mLeft, this.mRight, this.mTop, this.mBottom, this.mBaseline, constraintAnchor};
        this.mAnchors = new ArrayList<>();
        this.mIsInBarrier = new boolean[2];
        this.mListDimensionBehaviors = new DimensionBehaviour[]{DimensionBehaviour.FIXED, DimensionBehaviour.FIXED};
        this.mParent = null;
        this.mWidth = 0;
        this.mHeight = 0;
        this.mDimensionRatio = 0.0f;
        this.mDimensionRatioSide = -1;
        this.mX = 0;
        this.mY = 0;
        this.mRelX = 0;
        this.mRelY = 0;
        this.mOffsetX = 0;
        this.mOffsetY = 0;
        this.mBaselineDistance = 0;
        float f = DEFAULT_BIAS;
        this.mHorizontalBiasPercent = f;
        this.mVerticalBiasPercent = f;
        this.mContainerItemSkip = 0;
        this.mVisibility = 0;
        this.mAnimated = false;
        this.mDebugName = null;
        this.mType = null;
        this.mGroupsToSolver = false;
        this.mHorizontalChainStyle = 0;
        this.mVerticalChainStyle = 0;
        this.mWeight = new float[]{-1.0f, -1.0f};
        this.mListNextMatchConstraintsWidget = new ConstraintWidget[]{null, null};
        this.mNextChainWidget = new ConstraintWidget[]{null, null};
        this.mHorizontalNextWidget = null;
        this.mVerticalNextWidget = null;
        this.horizontalGroup = -1;
        this.verticalGroup = -1;
        addAnchors();
        setDebugName(str);
    }

    public ConstraintWidget(int i, int i2, int i3, int i4) {
        this.measured = false;
        this.run = new WidgetRun[2];
        this.horizontalRun = null;
        this.verticalRun = null;
        this.isTerminalWidget = new boolean[]{true, true};
        this.mResolvedHasRatio = false;
        this.mMeasureRequested = true;
        this.OPTIMIZE_WRAP = false;
        this.OPTIMIZE_WRAP_ON_RESOLVED = true;
        this.mWidthOverride = -1;
        this.mHeightOverride = -1;
        this.frame = new WidgetFrame(this);
        this.resolvedHorizontal = false;
        this.resolvedVertical = false;
        this.horizontalSolvingPass = false;
        this.verticalSolvingPass = false;
        this.mHorizontalResolution = -1;
        this.mVerticalResolution = -1;
        this.mWrapBehaviorInParent = 0;
        this.mMatchConstraintDefaultWidth = 0;
        this.mMatchConstraintDefaultHeight = 0;
        this.mResolvedMatchConstraintDefault = new int[2];
        this.mMatchConstraintMinWidth = 0;
        this.mMatchConstraintMaxWidth = 0;
        this.mMatchConstraintPercentWidth = 1.0f;
        this.mMatchConstraintMinHeight = 0;
        this.mMatchConstraintMaxHeight = 0;
        this.mMatchConstraintPercentHeight = 1.0f;
        this.mResolvedDimensionRatioSide = -1;
        this.mResolvedDimensionRatio = 1.0f;
        this.mMaxDimension = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE};
        this.mCircleConstraintAngle = 0.0f;
        this.hasBaseline = false;
        this.mInVirtualLayout = false;
        this.mLastHorizontalMeasureSpec = 0;
        this.mLastVerticalMeasureSpec = 0;
        this.mLeft = new ConstraintAnchor(this, ConstraintAnchor.Type.LEFT);
        this.mTop = new ConstraintAnchor(this, ConstraintAnchor.Type.TOP);
        this.mRight = new ConstraintAnchor(this, ConstraintAnchor.Type.RIGHT);
        this.mBottom = new ConstraintAnchor(this, ConstraintAnchor.Type.BOTTOM);
        this.mBaseline = new ConstraintAnchor(this, ConstraintAnchor.Type.BASELINE);
        this.mCenterX = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER_X);
        this.mCenterY = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER_Y);
        ConstraintAnchor constraintAnchor = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER);
        this.mCenter = constraintAnchor;
        this.mListAnchors = new ConstraintAnchor[]{this.mLeft, this.mRight, this.mTop, this.mBottom, this.mBaseline, constraintAnchor};
        this.mAnchors = new ArrayList<>();
        this.mIsInBarrier = new boolean[2];
        this.mListDimensionBehaviors = new DimensionBehaviour[]{DimensionBehaviour.FIXED, DimensionBehaviour.FIXED};
        this.mParent = null;
        this.mWidth = 0;
        this.mHeight = 0;
        this.mDimensionRatio = 0.0f;
        this.mDimensionRatioSide = -1;
        this.mX = 0;
        this.mY = 0;
        this.mRelX = 0;
        this.mRelY = 0;
        this.mOffsetX = 0;
        this.mOffsetY = 0;
        this.mBaselineDistance = 0;
        float f = DEFAULT_BIAS;
        this.mHorizontalBiasPercent = f;
        this.mVerticalBiasPercent = f;
        this.mContainerItemSkip = 0;
        this.mVisibility = 0;
        this.mAnimated = false;
        this.mDebugName = null;
        this.mType = null;
        this.mGroupsToSolver = false;
        this.mHorizontalChainStyle = 0;
        this.mVerticalChainStyle = 0;
        this.mWeight = new float[]{-1.0f, -1.0f};
        this.mListNextMatchConstraintsWidget = new ConstraintWidget[]{null, null};
        this.mNextChainWidget = new ConstraintWidget[]{null, null};
        this.mHorizontalNextWidget = null;
        this.mVerticalNextWidget = null;
        this.horizontalGroup = -1;
        this.verticalGroup = -1;
        this.mX = i;
        this.mY = i2;
        this.mWidth = i3;
        this.mHeight = i4;
        addAnchors();
    }

    public ConstraintWidget(String str, int i, int i2, int i3, int i4) {
        this(i, i2, i3, i4);
        setDebugName(str);
    }

    public ConstraintWidget(int i, int i2) {
        this(0, 0, i, i2);
    }

    public void ensureWidgetRuns() {
        if (this.horizontalRun == null) {
            this.horizontalRun = new HorizontalWidgetRun(this);
        }
        if (this.verticalRun == null) {
            this.verticalRun = new VerticalWidgetRun(this);
        }
    }

    public ConstraintWidget(String str, int i, int i2) {
        this(i, i2);
        setDebugName(str);
    }

    public void resetSolverVariables(Cache cache) {
        this.mLeft.resetSolverVariable(cache);
        this.mTop.resetSolverVariable(cache);
        this.mRight.resetSolverVariable(cache);
        this.mBottom.resetSolverVariable(cache);
        this.mBaseline.resetSolverVariable(cache);
        this.mCenter.resetSolverVariable(cache);
        this.mCenterX.resetSolverVariable(cache);
        this.mCenterY.resetSolverVariable(cache);
    }

    private void addAnchors() {
        this.mAnchors.add(this.mLeft);
        this.mAnchors.add(this.mTop);
        this.mAnchors.add(this.mRight);
        this.mAnchors.add(this.mBottom);
        this.mAnchors.add(this.mCenterX);
        this.mAnchors.add(this.mCenterY);
        this.mAnchors.add(this.mCenter);
        this.mAnchors.add(this.mBaseline);
    }

    public boolean isRoot() {
        return this.mParent == null;
    }

    public ConstraintWidget getParent() {
        return this.mParent;
    }

    public void setParent(ConstraintWidget constraintWidget) {
        this.mParent = constraintWidget;
    }

    public void setWidthWrapContent(boolean z) {
        this.mIsWidthWrapContent = z;
    }

    public boolean isWidthWrapContent() {
        return this.mIsWidthWrapContent;
    }

    public void setHeightWrapContent(boolean z) {
        this.mIsHeightWrapContent = z;
    }

    public boolean isHeightWrapContent() {
        return this.mIsHeightWrapContent;
    }

    public void connectCircularConstraint(ConstraintWidget constraintWidget, float f, int i) {
        immediateConnect(ConstraintAnchor.Type.CENTER, constraintWidget, ConstraintAnchor.Type.CENTER, i, 0);
        this.mCircleConstraintAngle = f;
    }

    public String getType() {
        return this.mType;
    }

    public void setType(String str) {
        this.mType = str;
    }

    public void setVisibility(int i) {
        this.mVisibility = i;
    }

    public int getVisibility() {
        return this.mVisibility;
    }

    public void setAnimated(boolean z) {
        this.mAnimated = z;
    }

    public boolean isAnimated() {
        return this.mAnimated;
    }

    public String getDebugName() {
        return this.mDebugName;
    }

    public void setDebugName(String str) {
        this.mDebugName = str;
    }

    public void setDebugSolverName(LinearSystem linearSystem, String str) {
        this.mDebugName = str;
        SolverVariable createObjectVariable = linearSystem.createObjectVariable(this.mLeft);
        SolverVariable createObjectVariable2 = linearSystem.createObjectVariable(this.mTop);
        SolverVariable createObjectVariable3 = linearSystem.createObjectVariable(this.mRight);
        SolverVariable createObjectVariable4 = linearSystem.createObjectVariable(this.mBottom);
        createObjectVariable.setName(str + ".left");
        createObjectVariable2.setName(str + ".top");
        createObjectVariable3.setName(str + ".right");
        createObjectVariable4.setName(str + ".bottom");
        linearSystem.createObjectVariable(this.mBaseline).setName(str + ".baseline");
    }

    public void createObjectVariables(LinearSystem linearSystem) {
        linearSystem.createObjectVariable(this.mLeft);
        linearSystem.createObjectVariable(this.mTop);
        linearSystem.createObjectVariable(this.mRight);
        linearSystem.createObjectVariable(this.mBottom);
        if (this.mBaselineDistance > 0) {
            linearSystem.createObjectVariable(this.mBaseline);
        }
    }

    public String toString() {
        return (this.mType != null ? "type: " + this.mType + " " : "") + (this.mDebugName != null ? "id: " + this.mDebugName + " " : "") + "(" + this.mX + ", " + this.mY + ") - (" + this.mWidth + " x " + this.mHeight + ")";
    }

    public int getX() {
        ConstraintWidget constraintWidget = this.mParent;
        if (constraintWidget != null && (constraintWidget instanceof ConstraintWidgetContainer)) {
            return ((ConstraintWidgetContainer) constraintWidget).mPaddingLeft + this.mX;
        }
        return this.mX;
    }

    public int getY() {
        ConstraintWidget constraintWidget = this.mParent;
        if (constraintWidget != null && (constraintWidget instanceof ConstraintWidgetContainer)) {
            return ((ConstraintWidgetContainer) constraintWidget).mPaddingTop + this.mY;
        }
        return this.mY;
    }

    public int getWidth() {
        if (this.mVisibility == 8) {
            return 0;
        }
        return this.mWidth;
    }

    public int getOptimizerWrapWidth() {
        int i;
        int i2 = this.mWidth;
        if (this.mListDimensionBehaviors[0] == DimensionBehaviour.MATCH_CONSTRAINT) {
            if (this.mMatchConstraintDefaultWidth == 1) {
                i = Math.max(this.mMatchConstraintMinWidth, i2);
            } else {
                i = this.mMatchConstraintMinWidth;
                if (i > 0) {
                    this.mWidth = i;
                } else {
                    i = 0;
                }
            }
            int i3 = this.mMatchConstraintMaxWidth;
            return (i3 <= 0 || i3 >= i) ? i : i3;
        }
        return i2;
    }

    public int getOptimizerWrapHeight() {
        int i;
        int i2 = this.mHeight;
        if (this.mListDimensionBehaviors[1] == DimensionBehaviour.MATCH_CONSTRAINT) {
            if (this.mMatchConstraintDefaultHeight == 1) {
                i = Math.max(this.mMatchConstraintMinHeight, i2);
            } else {
                i = this.mMatchConstraintMinHeight;
                if (i > 0) {
                    this.mHeight = i;
                } else {
                    i = 0;
                }
            }
            int i3 = this.mMatchConstraintMaxHeight;
            return (i3 <= 0 || i3 >= i) ? i : i3;
        }
        return i2;
    }

    public int getHeight() {
        if (this.mVisibility == 8) {
            return 0;
        }
        return this.mHeight;
    }

    public int getLength(int i) {
        if (i == 0) {
            return getWidth();
        }
        if (i == 1) {
            return getHeight();
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int getRootX() {
        return this.mX + this.mOffsetX;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int getRootY() {
        return this.mY + this.mOffsetY;
    }

    public int getMinWidth() {
        return this.mMinWidth;
    }

    public int getMinHeight() {
        return this.mMinHeight;
    }

    public int getLeft() {
        return getX();
    }

    public int getTop() {
        return getY();
    }

    public int getRight() {
        return getX() + this.mWidth;
    }

    public int getBottom() {
        return getY() + this.mHeight;
    }

    public int getHorizontalMargin() {
        ConstraintAnchor constraintAnchor = this.mLeft;
        int i = constraintAnchor != null ? 0 + constraintAnchor.mMargin : 0;
        ConstraintAnchor constraintAnchor2 = this.mRight;
        return constraintAnchor2 != null ? i + constraintAnchor2.mMargin : i;
    }

    public int getVerticalMargin() {
        int i = this.mLeft != null ? 0 + this.mTop.mMargin : 0;
        return this.mRight != null ? i + this.mBottom.mMargin : i;
    }

    public float getHorizontalBiasPercent() {
        return this.mHorizontalBiasPercent;
    }

    public float getVerticalBiasPercent() {
        return this.mVerticalBiasPercent;
    }

    public float getBiasPercent(int i) {
        if (i == 0) {
            return this.mHorizontalBiasPercent;
        }
        if (i == 1) {
            return this.mVerticalBiasPercent;
        }
        return -1.0f;
    }

    public boolean hasBaseline() {
        return this.hasBaseline;
    }

    public int getBaselineDistance() {
        return this.mBaselineDistance;
    }

    public Object getCompanionWidget() {
        return this.mCompanionWidget;
    }

    public ArrayList<ConstraintAnchor> getAnchors() {
        return this.mAnchors;
    }

    public void setX(int i) {
        this.mX = i;
    }

    public void setY(int i) {
        this.mY = i;
    }

    public void setOrigin(int i, int i2) {
        this.mX = i;
        this.mY = i2;
    }

    public void setOffset(int i, int i2) {
        this.mOffsetX = i;
        this.mOffsetY = i2;
    }

    public void setGoneMargin(ConstraintAnchor.Type type, int i) {
        int i2 = AnonymousClass1.$SwitchMap$androidx$constraintlayout$core$widgets$ConstraintAnchor$Type[type.ordinal()];
        if (i2 == 1) {
            this.mLeft.mGoneMargin = i;
        } else if (i2 == 2) {
            this.mTop.mGoneMargin = i;
        } else if (i2 == 3) {
            this.mRight.mGoneMargin = i;
        } else if (i2 == 4) {
            this.mBottom.mGoneMargin = i;
        } else if (i2 != 5) {
        } else {
            this.mBaseline.mGoneMargin = i;
        }
    }

    public void setWidth(int i) {
        this.mWidth = i;
        int i2 = this.mMinWidth;
        if (i < i2) {
            this.mWidth = i2;
        }
    }

    public void setHeight(int i) {
        this.mHeight = i;
        int i2 = this.mMinHeight;
        if (i < i2) {
            this.mHeight = i2;
        }
    }

    public void setLength(int i, int i2) {
        if (i2 == 0) {
            setWidth(i);
        } else if (i2 == 1) {
            setHeight(i);
        }
    }

    public void setHorizontalMatchStyle(int i, int i2, int i3, float f) {
        this.mMatchConstraintDefaultWidth = i;
        this.mMatchConstraintMinWidth = i2;
        if (i3 == Integer.MAX_VALUE) {
            i3 = 0;
        }
        this.mMatchConstraintMaxWidth = i3;
        this.mMatchConstraintPercentWidth = f;
        if (f <= 0.0f || f >= 1.0f || i != 0) {
            return;
        }
        this.mMatchConstraintDefaultWidth = 2;
    }

    public void setVerticalMatchStyle(int i, int i2, int i3, float f) {
        this.mMatchConstraintDefaultHeight = i;
        this.mMatchConstraintMinHeight = i2;
        if (i3 == Integer.MAX_VALUE) {
            i3 = 0;
        }
        this.mMatchConstraintMaxHeight = i3;
        this.mMatchConstraintPercentHeight = f;
        if (f <= 0.0f || f >= 1.0f || i != 0) {
            return;
        }
        this.mMatchConstraintDefaultHeight = 2;
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:38:0x0084 -> B:39:0x0085). Please submit an issue!!! */
    public void setDimensionRatio(String str) {
        float f;
        int i = 0;
        if (str == null || str.length() == 0) {
            this.mDimensionRatio = 0.0f;
            return;
        }
        int i2 = -1;
        int length = str.length();
        int indexOf = str.indexOf(44);
        int i3 = 0;
        if (indexOf > 0 && indexOf < length - 1) {
            String substring = str.substring(0, indexOf);
            if (substring.equalsIgnoreCase(ExifInterface.LONGITUDE_WEST)) {
                i2 = 0;
            } else if (substring.equalsIgnoreCase("H")) {
                i2 = 1;
            }
            i3 = indexOf + 1;
        }
        int indexOf2 = str.indexOf(58);
        if (indexOf2 >= 0 && indexOf2 < length - 1) {
            String substring2 = str.substring(i3, indexOf2);
            String substring3 = str.substring(indexOf2 + 1);
            if (substring2.length() > 0 && substring3.length() > 0) {
                float parseFloat = Float.parseFloat(substring2);
                float parseFloat2 = Float.parseFloat(substring3);
                if (parseFloat > 0.0f && parseFloat2 > 0.0f) {
                    if (i2 == 1) {
                        f = Math.abs(parseFloat2 / parseFloat);
                    } else {
                        f = Math.abs(parseFloat / parseFloat2);
                    }
                }
            }
            f = i;
        } else {
            String substring4 = str.substring(i3);
            if (substring4.length() > 0) {
                f = Float.parseFloat(substring4);
            }
            f = i;
        }
        i = (f > i ? 1 : (f == i ? 0 : -1));
        if (i > 0) {
            this.mDimensionRatio = f;
            this.mDimensionRatioSide = i2;
        }
    }

    public void setDimensionRatio(float f, int i) {
        this.mDimensionRatio = f;
        this.mDimensionRatioSide = i;
    }

    public float getDimensionRatio() {
        return this.mDimensionRatio;
    }

    public int getDimensionRatioSide() {
        return this.mDimensionRatioSide;
    }

    public void setHorizontalBiasPercent(float f) {
        this.mHorizontalBiasPercent = f;
    }

    public void setVerticalBiasPercent(float f) {
        this.mVerticalBiasPercent = f;
    }

    public void setMinWidth(int i) {
        if (i < 0) {
            this.mMinWidth = 0;
        } else {
            this.mMinWidth = i;
        }
    }

    public void setMinHeight(int i) {
        if (i < 0) {
            this.mMinHeight = 0;
        } else {
            this.mMinHeight = i;
        }
    }

    public void setDimension(int i, int i2) {
        this.mWidth = i;
        int i3 = this.mMinWidth;
        if (i < i3) {
            this.mWidth = i3;
        }
        this.mHeight = i2;
        int i4 = this.mMinHeight;
        if (i2 < i4) {
            this.mHeight = i4;
        }
    }

    public void setFrame(int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        int i7 = i3 - i;
        int i8 = i4 - i2;
        this.mX = i;
        this.mY = i2;
        if (this.mVisibility == 8) {
            this.mWidth = 0;
            this.mHeight = 0;
            return;
        }
        if (this.mListDimensionBehaviors[0] == DimensionBehaviour.FIXED && i7 < (i6 = this.mWidth)) {
            i7 = i6;
        }
        if (this.mListDimensionBehaviors[1] == DimensionBehaviour.FIXED && i8 < (i5 = this.mHeight)) {
            i8 = i5;
        }
        this.mWidth = i7;
        this.mHeight = i8;
        int i9 = this.mMinHeight;
        if (i8 < i9) {
            this.mHeight = i9;
        }
        int i10 = this.mMinWidth;
        if (i7 < i10) {
            this.mWidth = i10;
        }
        if (this.mMatchConstraintMaxWidth > 0 && this.mListDimensionBehaviors[0] == DimensionBehaviour.MATCH_CONSTRAINT) {
            this.mWidth = Math.min(this.mWidth, this.mMatchConstraintMaxWidth);
        }
        if (this.mMatchConstraintMaxHeight > 0 && this.mListDimensionBehaviors[1] == DimensionBehaviour.MATCH_CONSTRAINT) {
            this.mHeight = Math.min(this.mHeight, this.mMatchConstraintMaxHeight);
        }
        int i11 = this.mWidth;
        if (i7 != i11) {
            this.mWidthOverride = i11;
        }
        int i12 = this.mHeight;
        if (i8 != i12) {
            this.mHeightOverride = i12;
        }
    }

    public void setFrame(int i, int i2, int i3) {
        if (i3 == 0) {
            setHorizontalDimension(i, i2);
        } else if (i3 == 1) {
            setVerticalDimension(i, i2);
        }
    }

    public void setHorizontalDimension(int i, int i2) {
        this.mX = i;
        int i3 = i2 - i;
        this.mWidth = i3;
        int i4 = this.mMinWidth;
        if (i3 < i4) {
            this.mWidth = i4;
        }
    }

    public void setVerticalDimension(int i, int i2) {
        this.mY = i;
        int i3 = i2 - i;
        this.mHeight = i3;
        int i4 = this.mMinHeight;
        if (i3 < i4) {
            this.mHeight = i4;
        }
    }

    int getRelativePositioning(int i) {
        if (i == 0) {
            return this.mRelX;
        }
        if (i == 1) {
            return this.mRelY;
        }
        return 0;
    }

    void setRelativePositioning(int i, int i2) {
        if (i2 == 0) {
            this.mRelX = i;
        } else if (i2 == 1) {
            this.mRelY = i;
        }
    }

    public void setBaselineDistance(int i) {
        this.mBaselineDistance = i;
        this.hasBaseline = i > 0;
    }

    public void setCompanionWidget(Object obj) {
        this.mCompanionWidget = obj;
    }

    public void setContainerItemSkip(int i) {
        if (i >= 0) {
            this.mContainerItemSkip = i;
        } else {
            this.mContainerItemSkip = 0;
        }
    }

    public int getContainerItemSkip() {
        return this.mContainerItemSkip;
    }

    public void setHorizontalWeight(float f) {
        this.mWeight[0] = f;
    }

    public void setVerticalWeight(float f) {
        this.mWeight[1] = f;
    }

    public void setHorizontalChainStyle(int i) {
        this.mHorizontalChainStyle = i;
    }

    public int getHorizontalChainStyle() {
        return this.mHorizontalChainStyle;
    }

    public void setVerticalChainStyle(int i) {
        this.mVerticalChainStyle = i;
    }

    public int getVerticalChainStyle() {
        return this.mVerticalChainStyle;
    }

    public boolean allowedInBarrier() {
        return this.mVisibility != 8;
    }

    public void immediateConnect(ConstraintAnchor.Type type, ConstraintWidget constraintWidget, ConstraintAnchor.Type type2, int i, int i2) {
        getAnchor(type).connect(constraintWidget.getAnchor(type2), i, i2, true);
    }

    public void connect(ConstraintAnchor constraintAnchor, ConstraintAnchor constraintAnchor2, int i) {
        if (constraintAnchor.getOwner() == this) {
            connect(constraintAnchor.getType(), constraintAnchor2.getOwner(), constraintAnchor2.getType(), i);
        }
    }

    public void connect(ConstraintAnchor.Type type, ConstraintWidget constraintWidget, ConstraintAnchor.Type type2) {
        connect(type, constraintWidget, type2, 0);
    }

    public void connect(ConstraintAnchor.Type type, ConstraintWidget constraintWidget, ConstraintAnchor.Type type2, int i) {
        boolean z;
        if (type == ConstraintAnchor.Type.CENTER) {
            if (type2 == ConstraintAnchor.Type.CENTER) {
                ConstraintAnchor anchor = getAnchor(ConstraintAnchor.Type.LEFT);
                ConstraintAnchor anchor2 = getAnchor(ConstraintAnchor.Type.RIGHT);
                ConstraintAnchor anchor3 = getAnchor(ConstraintAnchor.Type.TOP);
                ConstraintAnchor anchor4 = getAnchor(ConstraintAnchor.Type.BOTTOM);
                boolean z2 = true;
                if ((anchor == null || !anchor.isConnected()) && (anchor2 == null || !anchor2.isConnected())) {
                    connect(ConstraintAnchor.Type.LEFT, constraintWidget, ConstraintAnchor.Type.LEFT, 0);
                    connect(ConstraintAnchor.Type.RIGHT, constraintWidget, ConstraintAnchor.Type.RIGHT, 0);
                    z = true;
                } else {
                    z = false;
                }
                if ((anchor3 == null || !anchor3.isConnected()) && (anchor4 == null || !anchor4.isConnected())) {
                    connect(ConstraintAnchor.Type.TOP, constraintWidget, ConstraintAnchor.Type.TOP, 0);
                    connect(ConstraintAnchor.Type.BOTTOM, constraintWidget, ConstraintAnchor.Type.BOTTOM, 0);
                } else {
                    z2 = false;
                }
                if (z && z2) {
                    getAnchor(ConstraintAnchor.Type.CENTER).connect(constraintWidget.getAnchor(ConstraintAnchor.Type.CENTER), 0);
                } else if (z) {
                    getAnchor(ConstraintAnchor.Type.CENTER_X).connect(constraintWidget.getAnchor(ConstraintAnchor.Type.CENTER_X), 0);
                } else if (z2) {
                    getAnchor(ConstraintAnchor.Type.CENTER_Y).connect(constraintWidget.getAnchor(ConstraintAnchor.Type.CENTER_Y), 0);
                }
            } else if (type2 == ConstraintAnchor.Type.LEFT || type2 == ConstraintAnchor.Type.RIGHT) {
                connect(ConstraintAnchor.Type.LEFT, constraintWidget, type2, 0);
                connect(ConstraintAnchor.Type.RIGHT, constraintWidget, type2, 0);
                getAnchor(ConstraintAnchor.Type.CENTER).connect(constraintWidget.getAnchor(type2), 0);
            } else if (type2 == ConstraintAnchor.Type.TOP || type2 == ConstraintAnchor.Type.BOTTOM) {
                connect(ConstraintAnchor.Type.TOP, constraintWidget, type2, 0);
                connect(ConstraintAnchor.Type.BOTTOM, constraintWidget, type2, 0);
                getAnchor(ConstraintAnchor.Type.CENTER).connect(constraintWidget.getAnchor(type2), 0);
            }
        } else if (type == ConstraintAnchor.Type.CENTER_X && (type2 == ConstraintAnchor.Type.LEFT || type2 == ConstraintAnchor.Type.RIGHT)) {
            ConstraintAnchor anchor5 = getAnchor(ConstraintAnchor.Type.LEFT);
            ConstraintAnchor anchor6 = constraintWidget.getAnchor(type2);
            ConstraintAnchor anchor7 = getAnchor(ConstraintAnchor.Type.RIGHT);
            anchor5.connect(anchor6, 0);
            anchor7.connect(anchor6, 0);
            getAnchor(ConstraintAnchor.Type.CENTER_X).connect(anchor6, 0);
        } else if (type == ConstraintAnchor.Type.CENTER_Y && (type2 == ConstraintAnchor.Type.TOP || type2 == ConstraintAnchor.Type.BOTTOM)) {
            ConstraintAnchor anchor8 = constraintWidget.getAnchor(type2);
            getAnchor(ConstraintAnchor.Type.TOP).connect(anchor8, 0);
            getAnchor(ConstraintAnchor.Type.BOTTOM).connect(anchor8, 0);
            getAnchor(ConstraintAnchor.Type.CENTER_Y).connect(anchor8, 0);
        } else if (type == ConstraintAnchor.Type.CENTER_X && type2 == ConstraintAnchor.Type.CENTER_X) {
            getAnchor(ConstraintAnchor.Type.LEFT).connect(constraintWidget.getAnchor(ConstraintAnchor.Type.LEFT), 0);
            getAnchor(ConstraintAnchor.Type.RIGHT).connect(constraintWidget.getAnchor(ConstraintAnchor.Type.RIGHT), 0);
            getAnchor(ConstraintAnchor.Type.CENTER_X).connect(constraintWidget.getAnchor(type2), 0);
        } else if (type == ConstraintAnchor.Type.CENTER_Y && type2 == ConstraintAnchor.Type.CENTER_Y) {
            getAnchor(ConstraintAnchor.Type.TOP).connect(constraintWidget.getAnchor(ConstraintAnchor.Type.TOP), 0);
            getAnchor(ConstraintAnchor.Type.BOTTOM).connect(constraintWidget.getAnchor(ConstraintAnchor.Type.BOTTOM), 0);
            getAnchor(ConstraintAnchor.Type.CENTER_Y).connect(constraintWidget.getAnchor(type2), 0);
        } else {
            ConstraintAnchor anchor9 = getAnchor(type);
            ConstraintAnchor anchor10 = constraintWidget.getAnchor(type2);
            if (anchor9.isValidConnection(anchor10)) {
                if (type == ConstraintAnchor.Type.BASELINE) {
                    ConstraintAnchor anchor11 = getAnchor(ConstraintAnchor.Type.TOP);
                    ConstraintAnchor anchor12 = getAnchor(ConstraintAnchor.Type.BOTTOM);
                    if (anchor11 != null) {
                        anchor11.reset();
                    }
                    if (anchor12 != null) {
                        anchor12.reset();
                    }
                } else if (type == ConstraintAnchor.Type.TOP || type == ConstraintAnchor.Type.BOTTOM) {
                    ConstraintAnchor anchor13 = getAnchor(ConstraintAnchor.Type.BASELINE);
                    if (anchor13 != null) {
                        anchor13.reset();
                    }
                    ConstraintAnchor anchor14 = getAnchor(ConstraintAnchor.Type.CENTER);
                    if (anchor14.getTarget() != anchor10) {
                        anchor14.reset();
                    }
                    ConstraintAnchor opposite = getAnchor(type).getOpposite();
                    ConstraintAnchor anchor15 = getAnchor(ConstraintAnchor.Type.CENTER_Y);
                    if (anchor15.isConnected()) {
                        opposite.reset();
                        anchor15.reset();
                    }
                } else if (type == ConstraintAnchor.Type.LEFT || type == ConstraintAnchor.Type.RIGHT) {
                    ConstraintAnchor anchor16 = getAnchor(ConstraintAnchor.Type.CENTER);
                    if (anchor16.getTarget() != anchor10) {
                        anchor16.reset();
                    }
                    ConstraintAnchor opposite2 = getAnchor(type).getOpposite();
                    ConstraintAnchor anchor17 = getAnchor(ConstraintAnchor.Type.CENTER_X);
                    if (anchor17.isConnected()) {
                        opposite2.reset();
                        anchor17.reset();
                    }
                }
                anchor9.connect(anchor10, i);
            }
        }
    }

    public void resetAllConstraints() {
        resetAnchors();
        setVerticalBiasPercent(DEFAULT_BIAS);
        setHorizontalBiasPercent(DEFAULT_BIAS);
    }

    public void resetAnchor(ConstraintAnchor constraintAnchor) {
        if (getParent() != null && (getParent() instanceof ConstraintWidgetContainer) && ((ConstraintWidgetContainer) getParent()).handlesInternalConstraints()) {
            return;
        }
        ConstraintAnchor anchor = getAnchor(ConstraintAnchor.Type.LEFT);
        ConstraintAnchor anchor2 = getAnchor(ConstraintAnchor.Type.RIGHT);
        ConstraintAnchor anchor3 = getAnchor(ConstraintAnchor.Type.TOP);
        ConstraintAnchor anchor4 = getAnchor(ConstraintAnchor.Type.BOTTOM);
        ConstraintAnchor anchor5 = getAnchor(ConstraintAnchor.Type.CENTER);
        ConstraintAnchor anchor6 = getAnchor(ConstraintAnchor.Type.CENTER_X);
        ConstraintAnchor anchor7 = getAnchor(ConstraintAnchor.Type.CENTER_Y);
        if (constraintAnchor == anchor5) {
            if (anchor.isConnected() && anchor2.isConnected() && anchor.getTarget() == anchor2.getTarget()) {
                anchor.reset();
                anchor2.reset();
            }
            if (anchor3.isConnected() && anchor4.isConnected() && anchor3.getTarget() == anchor4.getTarget()) {
                anchor3.reset();
                anchor4.reset();
            }
            this.mHorizontalBiasPercent = 0.5f;
            this.mVerticalBiasPercent = 0.5f;
        } else if (constraintAnchor == anchor6) {
            if (anchor.isConnected() && anchor2.isConnected() && anchor.getTarget().getOwner() == anchor2.getTarget().getOwner()) {
                anchor.reset();
                anchor2.reset();
            }
            this.mHorizontalBiasPercent = 0.5f;
        } else if (constraintAnchor == anchor7) {
            if (anchor3.isConnected() && anchor4.isConnected() && anchor3.getTarget().getOwner() == anchor4.getTarget().getOwner()) {
                anchor3.reset();
                anchor4.reset();
            }
            this.mVerticalBiasPercent = 0.5f;
        } else if (constraintAnchor == anchor || constraintAnchor == anchor2) {
            if (anchor.isConnected() && anchor.getTarget() == anchor2.getTarget()) {
                anchor5.reset();
            }
        } else if ((constraintAnchor == anchor3 || constraintAnchor == anchor4) && anchor3.isConnected() && anchor3.getTarget() == anchor4.getTarget()) {
            anchor5.reset();
        }
        constraintAnchor.reset();
    }

    public void resetAnchors() {
        ConstraintWidget parent = getParent();
        if (parent != null && (parent instanceof ConstraintWidgetContainer) && ((ConstraintWidgetContainer) getParent()).handlesInternalConstraints()) {
            return;
        }
        int size = this.mAnchors.size();
        for (int i = 0; i < size; i++) {
            this.mAnchors.get(i).reset();
        }
    }

    public ConstraintAnchor getAnchor(ConstraintAnchor.Type type) {
        switch (AnonymousClass1.$SwitchMap$androidx$constraintlayout$core$widgets$ConstraintAnchor$Type[type.ordinal()]) {
            case 1:
                return this.mLeft;
            case 2:
                return this.mTop;
            case 3:
                return this.mRight;
            case 4:
                return this.mBottom;
            case 5:
                return this.mBaseline;
            case 6:
                return this.mCenter;
            case 7:
                return this.mCenterX;
            case 8:
                return this.mCenterY;
            case 9:
                return null;
            default:
                throw new AssertionError(type.name());
        }
    }

    public DimensionBehaviour getHorizontalDimensionBehaviour() {
        return this.mListDimensionBehaviors[0];
    }

    public DimensionBehaviour getVerticalDimensionBehaviour() {
        return this.mListDimensionBehaviors[1];
    }

    public DimensionBehaviour getDimensionBehaviour(int i) {
        if (i == 0) {
            return getHorizontalDimensionBehaviour();
        }
        if (i == 1) {
            return getVerticalDimensionBehaviour();
        }
        return null;
    }

    public void setHorizontalDimensionBehaviour(DimensionBehaviour dimensionBehaviour) {
        this.mListDimensionBehaviors[0] = dimensionBehaviour;
    }

    public void setVerticalDimensionBehaviour(DimensionBehaviour dimensionBehaviour) {
        this.mListDimensionBehaviors[1] = dimensionBehaviour;
    }

    public boolean isInHorizontalChain() {
        if (this.mLeft.mTarget == null || this.mLeft.mTarget.mTarget != this.mLeft) {
            return this.mRight.mTarget != null && this.mRight.mTarget.mTarget == this.mRight;
        }
        return true;
    }

    public ConstraintWidget getPreviousChainMember(int i) {
        if (i == 0) {
            if (this.mLeft.mTarget != null) {
                ConstraintAnchor constraintAnchor = this.mLeft.mTarget.mTarget;
                ConstraintAnchor constraintAnchor2 = this.mLeft;
                if (constraintAnchor == constraintAnchor2) {
                    return constraintAnchor2.mTarget.mOwner;
                }
                return null;
            }
            return null;
        } else if (i != 1 || this.mTop.mTarget == null) {
            return null;
        } else {
            ConstraintAnchor constraintAnchor3 = this.mTop.mTarget.mTarget;
            ConstraintAnchor constraintAnchor4 = this.mTop;
            if (constraintAnchor3 == constraintAnchor4) {
                return constraintAnchor4.mTarget.mOwner;
            }
            return null;
        }
    }

    public ConstraintWidget getNextChainMember(int i) {
        if (i == 0) {
            if (this.mRight.mTarget != null) {
                ConstraintAnchor constraintAnchor = this.mRight.mTarget.mTarget;
                ConstraintAnchor constraintAnchor2 = this.mRight;
                if (constraintAnchor == constraintAnchor2) {
                    return constraintAnchor2.mTarget.mOwner;
                }
                return null;
            }
            return null;
        } else if (i != 1 || this.mBottom.mTarget == null) {
            return null;
        } else {
            ConstraintAnchor constraintAnchor3 = this.mBottom.mTarget.mTarget;
            ConstraintAnchor constraintAnchor4 = this.mBottom;
            if (constraintAnchor3 == constraintAnchor4) {
                return constraintAnchor4.mTarget.mOwner;
            }
            return null;
        }
    }

    public ConstraintWidget getHorizontalChainControlWidget() {
        if (isInHorizontalChain()) {
            ConstraintWidget constraintWidget = this;
            ConstraintWidget constraintWidget2 = null;
            while (constraintWidget2 == null && constraintWidget != null) {
                ConstraintAnchor anchor = constraintWidget.getAnchor(ConstraintAnchor.Type.LEFT);
                ConstraintAnchor target = anchor == null ? null : anchor.getTarget();
                ConstraintWidget owner = target == null ? null : target.getOwner();
                if (owner == getParent()) {
                    return constraintWidget;
                }
                ConstraintAnchor target2 = owner == null ? null : owner.getAnchor(ConstraintAnchor.Type.RIGHT).getTarget();
                if (target2 == null || target2.getOwner() == constraintWidget) {
                    constraintWidget = owner;
                } else {
                    constraintWidget2 = constraintWidget;
                }
            }
            return constraintWidget2;
        }
        return null;
    }

    public boolean isInVerticalChain() {
        if (this.mTop.mTarget == null || this.mTop.mTarget.mTarget != this.mTop) {
            return this.mBottom.mTarget != null && this.mBottom.mTarget.mTarget == this.mBottom;
        }
        return true;
    }

    public ConstraintWidget getVerticalChainControlWidget() {
        if (isInVerticalChain()) {
            ConstraintWidget constraintWidget = this;
            ConstraintWidget constraintWidget2 = null;
            while (constraintWidget2 == null && constraintWidget != null) {
                ConstraintAnchor anchor = constraintWidget.getAnchor(ConstraintAnchor.Type.TOP);
                ConstraintAnchor target = anchor == null ? null : anchor.getTarget();
                ConstraintWidget owner = target == null ? null : target.getOwner();
                if (owner == getParent()) {
                    return constraintWidget;
                }
                ConstraintAnchor target2 = owner == null ? null : owner.getAnchor(ConstraintAnchor.Type.BOTTOM).getTarget();
                if (target2 == null || target2.getOwner() == constraintWidget) {
                    constraintWidget = owner;
                } else {
                    constraintWidget2 = constraintWidget;
                }
            }
            return constraintWidget2;
        }
        return null;
    }

    private boolean isChainHead(int i) {
        int i2 = i * 2;
        if (this.mListAnchors[i2].mTarget != null) {
            ConstraintAnchor constraintAnchor = this.mListAnchors[i2].mTarget.mTarget;
            ConstraintAnchor[] constraintAnchorArr = this.mListAnchors;
            if (constraintAnchor != constraintAnchorArr[i2]) {
                int i3 = i2 + 1;
                if (constraintAnchorArr[i3].mTarget != null && this.mListAnchors[i3].mTarget.mTarget == this.mListAnchors[i3]) {
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x01a8  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x01b4  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x0221  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x022b  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x0232  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x023b  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x023d  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x0246  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x0248  */
    /* JADX WARN: Removed duplicated region for block: B:193:0x0311  */
    /* JADX WARN: Removed duplicated region for block: B:197:0x031b  */
    /* JADX WARN: Removed duplicated region for block: B:200:0x0320  */
    /* JADX WARN: Removed duplicated region for block: B:207:0x0335  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x0340  */
    /* JADX WARN: Removed duplicated region for block: B:213:0x0343  */
    /* JADX WARN: Removed duplicated region for block: B:216:0x035c  */
    /* JADX WARN: Removed duplicated region for block: B:237:0x03b1  */
    /* JADX WARN: Removed duplicated region for block: B:238:0x03ba  */
    /* JADX WARN: Removed duplicated region for block: B:241:0x03c0  */
    /* JADX WARN: Removed duplicated region for block: B:242:0x03c8  */
    /* JADX WARN: Removed duplicated region for block: B:245:0x03ee  */
    /* JADX WARN: Removed duplicated region for block: B:246:0x03f1  */
    /* JADX WARN: Removed duplicated region for block: B:250:0x045d  */
    /* JADX WARN: Removed duplicated region for block: B:267:0x04c7  */
    /* JADX WARN: Removed duplicated region for block: B:271:0x04db  */
    /* JADX WARN: Removed duplicated region for block: B:272:0x04dd  */
    /* JADX WARN: Removed duplicated region for block: B:274:0x04e0  */
    /* JADX WARN: Removed duplicated region for block: B:309:0x057d  */
    /* JADX WARN: Removed duplicated region for block: B:310:0x0580  */
    /* JADX WARN: Removed duplicated region for block: B:314:0x05c6  */
    /* JADX WARN: Removed duplicated region for block: B:318:0x05f1  */
    /* JADX WARN: Removed duplicated region for block: B:321:0x05fb  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0100  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void addToSolver(androidx.constraintlayout.core.LinearSystem r51, boolean r52) {
        boolean r4;
        boolean r5;
        boolean r0;
        androidx.constraintlayout.core.widgets.ConstraintWidget r0;
        androidx.constraintlayout.core.widgets.ConstraintWidget r0;
        boolean r28;
        boolean r29;
        int r0;
        int r1;
        int r1;
        int r2;
        float r8;
        int r6;
        int r3;
        int r30;
        int r31;
        int r32;
        int r0;
        boolean r8;
        int r2;
        boolean r17;
        boolean r22;
        int r23;
        boolean r34;
        boolean r27;
        boolean r41;
        boolean r46;
        boolean r43;
        androidx.constraintlayout.core.SolverVariable r47;
        androidx.constraintlayout.core.SolverVariable r48;
        androidx.constraintlayout.core.SolverVariable r49;
        androidx.constraintlayout.core.SolverVariable r37;
        androidx.constraintlayout.core.SolverVariable r38;
        int r2;
        int r10;
        int r11;
        androidx.constraintlayout.core.widgets.ConstraintWidget r15;
        androidx.constraintlayout.core.LinearSystem r14;
        androidx.constraintlayout.core.SolverVariable r1;
        androidx.constraintlayout.core.SolverVariable r12;
        androidx.constraintlayout.core.SolverVariable r13;
        int r7;
        androidx.constraintlayout.core.SolverVariable r34;
        androidx.constraintlayout.core.SolverVariable r36;
        androidx.constraintlayout.core.widgets.ConstraintWidget r7;
        boolean r27;
        androidx.constraintlayout.core.widgets.analyzer.HorizontalWidgetRun r0;
        int r1;
        int r0;
        boolean r0;
        boolean r1;
        androidx.constraintlayout.core.widgets.analyzer.HorizontalWidgetRun r0;
        boolean[] r0;
        androidx.constraintlayout.core.SolverVariable r13 = r51.createObjectVariable(r50.mLeft);
        androidx.constraintlayout.core.SolverVariable r12 = r51.createObjectVariable(r50.mRight);
        androidx.constraintlayout.core.SolverVariable r11 = r51.createObjectVariable(r50.mTop);
        androidx.constraintlayout.core.SolverVariable r10 = r51.createObjectVariable(r50.mBottom);
        androidx.constraintlayout.core.SolverVariable r9 = r51.createObjectVariable(r50.mBaseline);
        androidx.constraintlayout.core.widgets.ConstraintWidget r0 = r50.mParent;
        if (r0 != null) {
            boolean r0 = r0 != null && r0.mListDimensionBehaviors[0] == androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
            androidx.constraintlayout.core.widgets.ConstraintWidget r2 = r50.mParent;
            boolean r2 = r2 != null && r2.mListDimensionBehaviors[1] == androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
            int r3 = r50.mWrapBehaviorInParent;
            if (r3 == 1) {
                r4 = r0;
                r5 = false;
            } else if (r3 == 2) {
                r5 = r2;
                r4 = false;
            } else if (r3 != 3) {
                r4 = r0;
                r5 = r2;
            }
            if (r50.mVisibility == 8 && !r50.mAnimated && !hasDependencies()) {
                r0 = r50.mIsInBarrier;
                if (!r0[0] && !r0[1]) {
                    return;
                }
            }
            r0 = r50.resolvedHorizontal;
            if (!r0 || r50.resolvedVertical) {
                if (r0) {
                    r51.addEquality(r13, r50.mX);
                    r51.addEquality(r12, r50.mX + r50.mWidth);
                    if (r4 && (r0 = r50.mParent) != null) {
                        if (r50.OPTIMIZE_WRAP_ON_RESOLVED) {
                            androidx.constraintlayout.core.widgets.ConstraintWidgetContainer r0 = (androidx.constraintlayout.core.widgets.ConstraintWidgetContainer) r0;
                            r0.addHorizontalWrapMinVariable(r50.mLeft);
                            r0.addHorizontalWrapMaxVariable(r50.mRight);
                        } else {
                            r51.addGreaterThan(r51.createObjectVariable(r0.mRight), r12, 0, 5);
                        }
                    }
                }
                if (r50.resolvedVertical) {
                    r51.addEquality(r11, r50.mY);
                    r51.addEquality(r10, r50.mY + r50.mHeight);
                    if (r50.mBaseline.hasDependents()) {
                        r51.addEquality(r9, r50.mY + r50.mBaselineDistance);
                    }
                    if (r5 && (r0 = r50.mParent) != null) {
                        if (r50.OPTIMIZE_WRAP_ON_RESOLVED) {
                            androidx.constraintlayout.core.widgets.ConstraintWidgetContainer r0 = (androidx.constraintlayout.core.widgets.ConstraintWidgetContainer) r0;
                            r0.addVerticalWrapMinVariable(r50.mTop);
                            r0.addVerticalWrapMaxVariable(r50.mBottom);
                        } else {
                            r51.addGreaterThan(r51.createObjectVariable(r0.mBottom), r10, 0, 5);
                        }
                    }
                }
                if (r50.resolvedHorizontal && r50.resolvedVertical) {
                    r50.resolvedHorizontal = false;
                    r50.resolvedVertical = false;
                    return;
                }
            }
            if (androidx.constraintlayout.core.LinearSystem.sMetrics != null) {
                androidx.constraintlayout.core.LinearSystem.sMetrics.widgets++;
            }
            if (!r52 && (r0 = r50.horizontalRun) != null && r50.verticalRun != null && r0.start.resolved && r50.horizontalRun.end.resolved && r50.verticalRun.start.resolved && r50.verticalRun.end.resolved) {
                if (androidx.constraintlayout.core.LinearSystem.sMetrics != null) {
                    androidx.constraintlayout.core.LinearSystem.sMetrics.graphSolved++;
                }
                r51.addEquality(r13, r50.horizontalRun.start.value);
                r51.addEquality(r12, r50.horizontalRun.end.value);
                r51.addEquality(r11, r50.verticalRun.start.value);
                r51.addEquality(r10, r50.verticalRun.end.value);
                r51.addEquality(r9, r50.verticalRun.baseline.value);
                if (r50.mParent != null) {
                    if (r4 && r50.isTerminalWidget[0] && !isInHorizontalChain()) {
                        r51.addGreaterThan(r51.createObjectVariable(r50.mParent.mRight), r12, 0, 8);
                    }
                    if (r5 && r50.isTerminalWidget[1] && !isInVerticalChain()) {
                        r51.addGreaterThan(r51.createObjectVariable(r50.mParent.mBottom), r10, 0, 8);
                    }
                }
                r50.resolvedHorizontal = false;
                r50.resolvedVertical = false;
                return;
            }
            if (androidx.constraintlayout.core.LinearSystem.sMetrics != null) {
                androidx.constraintlayout.core.LinearSystem.sMetrics.linearSolved++;
            }
            if (r50.mParent == null) {
                if (isChainHead(0)) {
                    ((androidx.constraintlayout.core.widgets.ConstraintWidgetContainer) r50.mParent).addChain(r50, 0);
                    r0 = true;
                } else {
                    r0 = isInHorizontalChain();
                }
                if (isChainHead(1)) {
                    ((androidx.constraintlayout.core.widgets.ConstraintWidgetContainer) r50.mParent).addChain(r50, 1);
                    r1 = true;
                } else {
                    r1 = isInVerticalChain();
                }
                if (!r0 && r4 && r50.mVisibility != 8 && r50.mLeft.mTarget == null && r50.mRight.mTarget == null) {
                    r51.addGreaterThan(r51.createObjectVariable(r50.mParent.mRight), r12, 0, 1);
                }
                if (!r1 && r5 && r50.mVisibility != 8 && r50.mTop.mTarget == null && r50.mBottom.mTarget == null && r50.mBaseline == null) {
                    r51.addGreaterThan(r51.createObjectVariable(r50.mParent.mBottom), r10, 0, 1);
                }
                r29 = r0;
                r28 = r1;
            } else {
                r28 = false;
                r29 = false;
            }
            r0 = r50.mWidth;
            r1 = r50.mMinWidth;
            if (r0 < r1) {
                r0 = r1;
            }
            r1 = r50.mHeight;
            r2 = r50.mMinHeight;
            if (r1 < r2) {
                r1 = r2;
            }
            boolean r2 = r50.mListDimensionBehaviors[0] == androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
            boolean r7 = r50.mListDimensionBehaviors[1] == androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
            r50.mResolvedDimensionRatioSide = r50.mDimensionRatioSide;
            r8 = r50.mDimensionRatio;
            r50.mResolvedDimensionRatio = r8;
            r6 = r50.mMatchConstraintDefaultWidth;
            r3 = r50.mMatchConstraintDefaultHeight;
            int r23 = r0;
            if (r8 <= 0.0f && r50.mVisibility != 8) {
                if (r50.mListDimensionBehaviors[0] == androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && r6 == 0) {
                    r6 = 3;
                }
                if (r50.mListDimensionBehaviors[1] == androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && r3 == 0) {
                    r3 = 3;
                }
                if (r50.mListDimensionBehaviors[0] == androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && r50.mListDimensionBehaviors[1] == androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    r0 = 3;
                    if (r6 == 3 && r3 == 3) {
                        setupDimensionRatio(r4, r5, r2, r7);
                        r30 = r1;
                        r31 = r3;
                        r32 = r6;
                        r0 = r23;
                        r8 = true;
                        int[] r1 = r50.mResolvedMatchConstraintDefault;
                        r1[0] = r32;
                        r1[1] = r31;
                        r50.mResolvedHasRatio = r8;
                        if (r8) {
                            r2 = -1;
                        } else {
                            int r1 = r50.mResolvedDimensionRatioSide;
                            r2 = -1;
                            if (r1 == 0 || r1 == -1) {
                                r17 = true;
                                boolean r33 = !r8 && ((r1 = r50.mResolvedDimensionRatioSide) == 1 || r1 == r2);
                                r22 = r50.mListDimensionBehaviors[0] != androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT && (r50 instanceof androidx.constraintlayout.core.widgets.ConstraintWidgetContainer);
                                r23 = r22 ? 0 : r0;
                                r34 = !r50.mCenter.isConnected();
                                boolean[] r0 = r50.mIsInBarrier;
                                r27 = r0[0];
                                boolean r35 = r0[1];
                                if (r50.mHorizontalResolution != 2 && !r50.resolvedHorizontal) {
                                    if (r52 || (r0 = r50.horizontalRun) == null || !r0.start.resolved || !r50.horizontalRun.end.resolved) {
                                        androidx.constraintlayout.core.widgets.ConstraintWidget r0 = r50.mParent;
                                        androidx.constraintlayout.core.SolverVariable r16 = r0 == null ? r51.createObjectVariable(r0.mRight) : null;
                                        androidx.constraintlayout.core.widgets.ConstraintWidget r0 = r50.mParent;
                                        androidx.constraintlayout.core.SolverVariable r6 = r0 == null ? r51.createObjectVariable(r0.mLeft) : null;
                                        boolean r21 = r50.isTerminalWidget[0];
                                        androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour[] r0 = r50.mListDimensionBehaviors;
                                        r41 = r4;
                                        r46 = r5;
                                        r43 = r8;
                                        r47 = r9;
                                        r48 = r10;
                                        r49 = r11;
                                        r37 = r12;
                                        r38 = r13;
                                        applyConstraints(r51, true, r4, r5, r21, r6, r16, r0[0], r22, r50.mLeft, r50.mRight, r50.mX, r23, r50.mMinWidth, r50.mMaxDimension[0], r50.mHorizontalBiasPercent, r17, r0[1] != androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT, r29, r28, r27, r32, r31, r50.mMatchConstraintMinWidth, r50.mMatchConstraintMaxWidth, r50.mMatchConstraintPercentWidth, r34);
                                        if (r52) {
                                            r15 = r50;
                                            androidx.constraintlayout.core.widgets.analyzer.VerticalWidgetRun r0 = r15.verticalRun;
                                            if (r0 != null && r0.start.resolved && r15.verticalRun.end.resolved) {
                                                r14 = r51;
                                                r13 = r49;
                                                r14.addEquality(r13, r15.verticalRun.start.value);
                                                r12 = r48;
                                                r14.addEquality(r12, r15.verticalRun.end.value);
                                                r1 = r47;
                                                r14.addEquality(r1, r15.verticalRun.baseline.value);
                                                androidx.constraintlayout.core.widgets.ConstraintWidget r0 = r15.mParent;
                                                if (r0 == null || r28 || !r46) {
                                                    r2 = 8;
                                                    r10 = 0;
                                                    r11 = 1;
                                                } else {
                                                    r11 = 1;
                                                    if (r15.isTerminalWidget[1]) {
                                                        r2 = 8;
                                                        r10 = 0;
                                                        r14.addGreaterThan(r14.createObjectVariable(r0.mBottom), r12, 0, 8);
                                                    } else {
                                                        r2 = 8;
                                                        r10 = 0;
                                                    }
                                                }
                                                r7 = r10;
                                                if ((r15.mVerticalResolution != 2 ? r10 : r7) != 0 || r15.resolvedVertical) {
                                                    r34 = r12;
                                                    r36 = r13;
                                                } else {
                                                    boolean r9 = (r15.mListDimensionBehaviors[r11] == androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT && (r15 instanceof androidx.constraintlayout.core.widgets.ConstraintWidgetContainer)) ? r11 : r10;
                                                    if (r9) {
                                                        r30 = r10;
                                                    }
                                                    androidx.constraintlayout.core.widgets.ConstraintWidget r0 = r15.mParent;
                                                    androidx.constraintlayout.core.SolverVariable r7 = r0 != null ? r14.createObjectVariable(r0.mBottom) : null;
                                                    androidx.constraintlayout.core.widgets.ConstraintWidget r0 = r15.mParent;
                                                    androidx.constraintlayout.core.SolverVariable r6 = r0 != null ? r14.createObjectVariable(r0.mTop) : null;
                                                    if (r15.mBaselineDistance > 0 || r15.mVisibility == r2) {
                                                        if (r15.mBaseline.mTarget != null) {
                                                            r14.addEquality(r1, r13, getBaselineDistance(), r2);
                                                            r14.addEquality(r1, r14.createObjectVariable(r15.mBaseline.mTarget), r15.mBaseline.getMargin(), r2);
                                                            if (r46) {
                                                                r14.addGreaterThan(r7, r14.createObjectVariable(r15.mBottom), r10, 5);
                                                            }
                                                            r27 = r10;
                                                            boolean r5 = r15.isTerminalWidget[r11];
                                                            androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour[] r0 = r15.mListDimensionBehaviors;
                                                            r34 = r12;
                                                            r36 = r13;
                                                            applyConstraints(r51, false, r46, r41, r5, r6, r7, r0[r11], r9, r15.mTop, r15.mBottom, r15.mY, r30, r15.mMinHeight, r15.mMaxDimension[r11], r15.mVerticalBiasPercent, r33, r0[0] != androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT, r28, r29, r35, r31, r32, r15.mMatchConstraintMinHeight, r15.mMatchConstraintMaxHeight, r15.mMatchConstraintPercentHeight, r27);
                                                        } else if (r15.mVisibility == r2) {
                                                            r14.addEquality(r1, r13, r15.mBaseline.getMargin(), r2);
                                                        } else {
                                                            r14.addEquality(r1, r13, getBaselineDistance(), r2);
                                                        }
                                                    }
                                                    r27 = r34;
                                                    boolean r5 = r15.isTerminalWidget[r11];
                                                    androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour[] r0 = r15.mListDimensionBehaviors;
                                                    r34 = r12;
                                                    r36 = r13;
                                                    applyConstraints(r51, false, r46, r41, r5, r6, r7, r0[r11], r9, r15.mTop, r15.mBottom, r15.mY, r30, r15.mMinHeight, r15.mMaxDimension[r11], r15.mVerticalBiasPercent, r33, r0[0] != androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT, r28, r29, r35, r31, r32, r15.mMatchConstraintMinHeight, r15.mMatchConstraintMaxHeight, r15.mMatchConstraintPercentHeight, r27);
                                                }
                                                if (r43) {
                                                    r7 = r50;
                                                } else {
                                                    r7 = r50;
                                                    if (r7.mResolvedDimensionRatioSide == 1) {
                                                        r51.addRatio(r34, r36, r37, r38, r7.mResolvedDimensionRatio, 8);
                                                    } else {
                                                        r51.addRatio(r37, r38, r34, r36, r7.mResolvedDimensionRatio, 8);
                                                    }
                                                }
                                                if (r7.mCenter.isConnected()) {
                                                    r51.addCenterPoint(r7, r7.mCenter.getTarget().getOwner(), (float) java.lang.Math.toRadians(r7.mCircleConstraintAngle + 90.0f), r7.mCenter.getMargin());
                                                }
                                                r7.resolvedHorizontal = false;
                                                r7.resolvedVertical = false;
                                            }
                                            r14 = r51;
                                            r1 = r47;
                                            r12 = r48;
                                            r13 = r49;
                                            r2 = 8;
                                            r10 = 0;
                                            r11 = 1;
                                        } else {
                                            r2 = 8;
                                            r10 = 0;
                                            r11 = 1;
                                            r15 = r50;
                                            r14 = r51;
                                            r1 = r47;
                                            r12 = r48;
                                            r13 = r49;
                                        }
                                        r7 = r11;
                                        if ((r15.mVerticalResolution != 2 ? r10 : r7) != 0) {
                                        }
                                        r34 = r12;
                                        r36 = r13;
                                        if (r43) {
                                        }
                                        if (r7.mCenter.isConnected()) {
                                        }
                                        r7.resolvedHorizontal = false;
                                        r7.resolvedVertical = false;
                                    } else if (r52) {
                                        r51.addEquality(r13, r50.horizontalRun.start.value);
                                        r51.addEquality(r12, r50.horizontalRun.end.value);
                                        if (r50.mParent != null && r4 && r50.isTerminalWidget[0] && !isInHorizontalChain()) {
                                            r51.addGreaterThan(r51.createObjectVariable(r50.mParent.mRight), r12, 0, 8);
                                        }
                                    }
                                }
                                r41 = r4;
                                r46 = r5;
                                r43 = r8;
                                r47 = r9;
                                r48 = r10;
                                r49 = r11;
                                r37 = r12;
                                r38 = r13;
                                if (r52) {
                                }
                                r7 = r11;
                                if ((r15.mVerticalResolution != 2 ? r10 : r7) != 0) {
                                }
                                r34 = r12;
                                r36 = r13;
                                if (r43) {
                                }
                                if (r7.mCenter.isConnected()) {
                                }
                                r7.resolvedHorizontal = false;
                                r7.resolvedVertical = false;
                            }
                        }
                        r17 = false;
                        if (r8) {
                        }
                        if (r50.mListDimensionBehaviors[0] != androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                        }
                        if (r22) {
                        }
                        r34 = !r50.mCenter.isConnected();
                        boolean[] r0 = r50.mIsInBarrier;
                        r27 = r0[0];
                        boolean r35 = r0[1];
                        if (r50.mHorizontalResolution != 2) {
                            if (r52) {
                            }
                            androidx.constraintlayout.core.widgets.ConstraintWidget r0 = r50.mParent;
                            if (r0 == null) {
                            }
                            androidx.constraintlayout.core.widgets.ConstraintWidget r0 = r50.mParent;
                            if (r0 == null) {
                            }
                            boolean r21 = r50.isTerminalWidget[0];
                            androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour[] r0 = r50.mListDimensionBehaviors;
                            r41 = r4;
                            r46 = r5;
                            r43 = r8;
                            r47 = r9;
                            r48 = r10;
                            r49 = r11;
                            r37 = r12;
                            r38 = r13;
                            applyConstraints(r51, true, r4, r5, r21, r6, r16, r0[0], r22, r50.mLeft, r50.mRight, r50.mX, r23, r50.mMinWidth, r50.mMaxDimension[0], r50.mHorizontalBiasPercent, r17, r0[1] != androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT, r29, r28, r27, r32, r31, r50.mMatchConstraintMinWidth, r50.mMatchConstraintMaxWidth, r50.mMatchConstraintPercentWidth, r34);
                            if (r52) {
                            }
                            r7 = r11;
                            if ((r15.mVerticalResolution != 2 ? r10 : r7) != 0) {
                            }
                            r34 = r12;
                            r36 = r13;
                            if (r43) {
                            }
                            if (r7.mCenter.isConnected()) {
                            }
                            r7.resolvedHorizontal = false;
                            r7.resolvedVertical = false;
                        }
                        r41 = r4;
                        r46 = r5;
                        r43 = r8;
                        r47 = r9;
                        r48 = r10;
                        r49 = r11;
                        r37 = r12;
                        r38 = r13;
                        if (r52) {
                        }
                        r7 = r11;
                        if ((r15.mVerticalResolution != 2 ? r10 : r7) != 0) {
                        }
                        r34 = r12;
                        r36 = r13;
                        if (r43) {
                        }
                        if (r7.mCenter.isConnected()) {
                        }
                        r7.resolvedHorizontal = false;
                        r7.resolvedVertical = false;
                    }
                } else {
                    r0 = 3;
                }
                if (r50.mListDimensionBehaviors[0] == androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && r6 == r0) {
                    r50.mResolvedDimensionRatioSide = 0;
                    r0 = (int) (r50.mResolvedDimensionRatio * r50.mHeight);
                    r30 = r1;
                    r31 = r3;
                    if (r50.mListDimensionBehaviors[1] != androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                        r32 = 4;
                        r8 = false;
                        int[] r1 = r50.mResolvedMatchConstraintDefault;
                        r1[0] = r32;
                        r1[1] = r31;
                        r50.mResolvedHasRatio = r8;
                        if (r8) {
                        }
                        r17 = false;
                        if (r8) {
                        }
                        if (r50.mListDimensionBehaviors[0] != androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                        }
                        if (r22) {
                        }
                        r34 = !r50.mCenter.isConnected();
                        boolean[] r0 = r50.mIsInBarrier;
                        r27 = r0[0];
                        boolean r35 = r0[1];
                        if (r50.mHorizontalResolution != 2) {
                        }
                        r41 = r4;
                        r46 = r5;
                        r43 = r8;
                        r47 = r9;
                        r48 = r10;
                        r49 = r11;
                        r37 = r12;
                        r38 = r13;
                        if (r52) {
                        }
                        r7 = r11;
                        if ((r15.mVerticalResolution != 2 ? r10 : r7) != 0) {
                        }
                        r34 = r12;
                        r36 = r13;
                        if (r43) {
                        }
                        if (r7.mCenter.isConnected()) {
                        }
                        r7.resolvedHorizontal = false;
                        r7.resolvedVertical = false;
                    }
                    r32 = r6;
                    r8 = true;
                    int[] r1 = r50.mResolvedMatchConstraintDefault;
                    r1[0] = r32;
                    r1[1] = r31;
                    r50.mResolvedHasRatio = r8;
                    if (r8) {
                    }
                    r17 = false;
                    if (r8) {
                    }
                    if (r50.mListDimensionBehaviors[0] != androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                    }
                    if (r22) {
                    }
                    r34 = !r50.mCenter.isConnected();
                    boolean[] r0 = r50.mIsInBarrier;
                    r27 = r0[0];
                    boolean r35 = r0[1];
                    if (r50.mHorizontalResolution != 2) {
                    }
                    r41 = r4;
                    r46 = r5;
                    r43 = r8;
                    r47 = r9;
                    r48 = r10;
                    r49 = r11;
                    r37 = r12;
                    r38 = r13;
                    if (r52) {
                    }
                    r7 = r11;
                    if ((r15.mVerticalResolution != 2 ? r10 : r7) != 0) {
                    }
                    r34 = r12;
                    r36 = r13;
                    if (r43) {
                    }
                    if (r7.mCenter.isConnected()) {
                    }
                    r7.resolvedHorizontal = false;
                    r7.resolvedVertical = false;
                }
                if (r50.mListDimensionBehaviors[1] == androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && r3 == 3) {
                    r50.mResolvedDimensionRatioSide = 1;
                    if (r50.mDimensionRatioSide == -1) {
                        r50.mResolvedDimensionRatio = 1.0f / r50.mResolvedDimensionRatio;
                    }
                    r1 = (int) (r50.mResolvedDimensionRatio * r50.mWidth);
                    if (r50.mListDimensionBehaviors[0] != androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                        r30 = r1;
                        r32 = r6;
                        r31 = 4;
                    }
                }
                r30 = r1;
                r31 = r3;
                r32 = r6;
                r0 = r23;
                r8 = true;
                int[] r1 = r50.mResolvedMatchConstraintDefault;
                r1[0] = r32;
                r1[1] = r31;
                r50.mResolvedHasRatio = r8;
                if (r8) {
                }
                r17 = false;
                if (r8) {
                }
                if (r50.mListDimensionBehaviors[0] != androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                }
                if (r22) {
                }
                r34 = !r50.mCenter.isConnected();
                boolean[] r0 = r50.mIsInBarrier;
                r27 = r0[0];
                boolean r35 = r0[1];
                if (r50.mHorizontalResolution != 2) {
                }
                r41 = r4;
                r46 = r5;
                r43 = r8;
                r47 = r9;
                r48 = r10;
                r49 = r11;
                r37 = r12;
                r38 = r13;
                if (r52) {
                }
                r7 = r11;
                if ((r15.mVerticalResolution != 2 ? r10 : r7) != 0) {
                }
                r34 = r12;
                r36 = r13;
                if (r43) {
                }
                if (r7.mCenter.isConnected()) {
                }
                r7.resolvedHorizontal = false;
                r7.resolvedVertical = false;
            }
            r30 = r1;
            r31 = r3;
            r32 = r6;
            r0 = r23;
            r8 = false;
            int[] r1 = r50.mResolvedMatchConstraintDefault;
            r1[0] = r32;
            r1[1] = r31;
            r50.mResolvedHasRatio = r8;
            if (r8) {
            }
            r17 = false;
            if (r8) {
            }
            if (r50.mListDimensionBehaviors[0] != androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
            }
            if (r22) {
            }
            r34 = !r50.mCenter.isConnected();
            boolean[] r0 = r50.mIsInBarrier;
            r27 = r0[0];
            boolean r35 = r0[1];
            if (r50.mHorizontalResolution != 2) {
            }
            r41 = r4;
            r46 = r5;
            r43 = r8;
            r47 = r9;
            r48 = r10;
            r49 = r11;
            r37 = r12;
            r38 = r13;
            if (r52) {
            }
            r7 = r11;
            if ((r15.mVerticalResolution != 2 ? r10 : r7) != 0) {
            }
            r34 = r12;
            r36 = r13;
            if (r43) {
            }
            if (r7.mCenter.isConnected()) {
            }
            r7.resolvedHorizontal = false;
            r7.resolvedVertical = false;
        }
        r4 = false;
        r5 = false;
        if (r50.mVisibility == 8) {
            r0 = r50.mIsInBarrier;
            if (!r0[0]) {
                return;
            }
        }
        r0 = r50.resolvedHorizontal;
        if (!r0) {
        }
        if (r0) {
        }
        if (r50.resolvedVertical) {
        }
        if (r50.resolvedHorizontal) {
            r50.resolvedHorizontal = false;
            r50.resolvedVertical = false;
            return;
        }
        if (androidx.constraintlayout.core.LinearSystem.sMetrics != null) {
        }
        if (!r52) {
        }
        if (androidx.constraintlayout.core.LinearSystem.sMetrics != null) {
        }
        if (r50.mParent == null) {
        }
        r0 = r50.mWidth;
        r1 = r50.mMinWidth;
        if (r0 < r1) {
        }
        r1 = r50.mHeight;
        r2 = r50.mMinHeight;
        if (r1 < r2) {
        }
        if (r50.mListDimensionBehaviors[0] == androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
        }
        if (r50.mListDimensionBehaviors[1] == androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
        }
        r50.mResolvedDimensionRatioSide = r50.mDimensionRatioSide;
        r8 = r50.mDimensionRatio;
        r50.mResolvedDimensionRatio = r8;
        r6 = r50.mMatchConstraintDefaultWidth;
        r3 = r50.mMatchConstraintDefaultHeight;
        int r23 = r0;
        if (r8 <= 0.0f) {
        }
        r30 = r1;
        r31 = r3;
        r32 = r6;
        r0 = r23;
        r8 = false;
        int[] r1 = r50.mResolvedMatchConstraintDefault;
        r1[0] = r32;
        r1[1] = r31;
        r50.mResolvedHasRatio = r8;
        if (r8) {
        }
        r17 = false;
        if (r8) {
        }
        if (r50.mListDimensionBehaviors[0] != androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
        }
        if (r22) {
        }
        r34 = !r50.mCenter.isConnected();
        boolean[] r0 = r50.mIsInBarrier;
        r27 = r0[0];
        boolean r35 = r0[1];
        if (r50.mHorizontalResolution != 2) {
        }
        r41 = r4;
        r46 = r5;
        r43 = r8;
        r47 = r9;
        r48 = r10;
        r49 = r11;
        r37 = r12;
        r38 = r13;
        if (r52) {
        }
        r7 = r11;
        if ((r15.mVerticalResolution != 2 ? r10 : r7) != 0) {
        }
        r34 = r12;
        r36 = r13;
        if (r43) {
        }
        if (r7.mCenter.isConnected()) {
        }
        r7.resolvedHorizontal = false;
        r7.resolvedVertical = false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean addFirst() {
        return (this instanceof VirtualLayout) || (this instanceof Guideline);
    }

    public void setupDimensionRatio(boolean z, boolean z2, boolean z3, boolean z4) {
        if (this.mResolvedDimensionRatioSide == -1) {
            if (z3 && !z4) {
                this.mResolvedDimensionRatioSide = 0;
            } else if (!z3 && z4) {
                this.mResolvedDimensionRatioSide = 1;
                if (this.mDimensionRatioSide == -1) {
                    this.mResolvedDimensionRatio = 1.0f / this.mResolvedDimensionRatio;
                }
            }
        }
        if (this.mResolvedDimensionRatioSide == 0 && (!this.mTop.isConnected() || !this.mBottom.isConnected())) {
            this.mResolvedDimensionRatioSide = 1;
        } else if (this.mResolvedDimensionRatioSide == 1 && (!this.mLeft.isConnected() || !this.mRight.isConnected())) {
            this.mResolvedDimensionRatioSide = 0;
        }
        if (this.mResolvedDimensionRatioSide == -1 && (!this.mTop.isConnected() || !this.mBottom.isConnected() || !this.mLeft.isConnected() || !this.mRight.isConnected())) {
            if (this.mTop.isConnected() && this.mBottom.isConnected()) {
                this.mResolvedDimensionRatioSide = 0;
            } else if (this.mLeft.isConnected() && this.mRight.isConnected()) {
                this.mResolvedDimensionRatio = 1.0f / this.mResolvedDimensionRatio;
                this.mResolvedDimensionRatioSide = 1;
            }
        }
        if (this.mResolvedDimensionRatioSide == -1) {
            int i = this.mMatchConstraintMinWidth;
            if (i > 0 && this.mMatchConstraintMinHeight == 0) {
                this.mResolvedDimensionRatioSide = 0;
            } else if (i != 0 || this.mMatchConstraintMinHeight <= 0) {
            } else {
                this.mResolvedDimensionRatio = 1.0f / this.mResolvedDimensionRatio;
                this.mResolvedDimensionRatioSide = 1;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:114:0x01f0  */
    /* JADX WARN: Removed duplicated region for block: B:238:0x03b5 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:243:0x03c2  */
    /* JADX WARN: Removed duplicated region for block: B:251:0x0406  */
    /* JADX WARN: Removed duplicated region for block: B:254:0x0416  */
    /* JADX WARN: Removed duplicated region for block: B:258:0x041f  */
    /* JADX WARN: Removed duplicated region for block: B:267:0x0441  */
    /* JADX WARN: Removed duplicated region for block: B:269:0x0445 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:280:0x045f  */
    /* JADX WARN: Removed duplicated region for block: B:311:0x04a9  */
    /* JADX WARN: Removed duplicated region for block: B:318:0x04bb A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:331:0x04e1 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:350:0x0511  */
    /* JADX WARN: Removed duplicated region for block: B:380:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00ea  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void applyConstraints(androidx.constraintlayout.core.LinearSystem r32, boolean r33, boolean r34, boolean r35, boolean r36, androidx.constraintlayout.core.SolverVariable r37, androidx.constraintlayout.core.SolverVariable r38, androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour r39, boolean r40, androidx.constraintlayout.core.widgets.ConstraintAnchor r41, androidx.constraintlayout.core.widgets.ConstraintAnchor r42, int r43, int r44, int r45, int r46, float r47, boolean r48, boolean r49, boolean r50, boolean r51, boolean r52, int r53, int r54, int r55, int r56, float r57, boolean r58) {
        int r2;
        boolean r18;
        int r14;
        androidx.constraintlayout.core.SolverVariable r21;
        int r6;
        int r1;
        int r3;
        int r40;
        androidx.constraintlayout.core.SolverVariable r13;
        androidx.constraintlayout.core.SolverVariable r15;
        androidx.constraintlayout.core.SolverVariable r14;
        int r21;
        boolean r23;
        boolean r18;
        androidx.constraintlayout.core.SolverVariable r3;
        androidx.constraintlayout.core.SolverVariable r4;
        androidx.constraintlayout.core.SolverVariable r1;
        androidx.constraintlayout.core.SolverVariable r4;
        androidx.constraintlayout.core.SolverVariable r2;
        int r3;
        androidx.constraintlayout.core.SolverVariable r15;
        int r5;
        int r6;
        int r14;
        androidx.constraintlayout.core.SolverVariable r8;
        int r13;
        androidx.constraintlayout.core.SolverVariable r8;
        int r3;
        int r1;
        int r19;
        boolean r24;
        boolean r22;
        boolean r25;
        boolean r26;
        int r39;
        androidx.constraintlayout.core.widgets.ConstraintWidget r29;
        androidx.constraintlayout.core.widgets.ConstraintWidget r12;
        androidx.constraintlayout.core.SolverVariable r20;
        androidx.constraintlayout.core.SolverVariable r15;
        boolean r22;
        androidx.constraintlayout.core.SolverVariable r2;
        androidx.constraintlayout.core.widgets.ConstraintWidget r1;
        int r4;
        int r1;
        int r3;
        int r27;
        boolean r28;
        int r26;
        int r1;
        int r3;
        boolean r22;
        int r3;
        boolean r19;
        androidx.constraintlayout.core.widgets.ConstraintWidget r1;
        int r6;
        androidx.constraintlayout.core.SolverVariable r4;
        int r13;
        androidx.constraintlayout.core.widgets.ConstraintWidget r3;
        androidx.constraintlayout.core.SolverVariable r9 = r32.createObjectVariable(r41);
        androidx.constraintlayout.core.SolverVariable r8 = r32.createObjectVariable(r42);
        androidx.constraintlayout.core.SolverVariable r7 = r32.createObjectVariable(r41.getTarget());
        androidx.constraintlayout.core.SolverVariable r6 = r32.createObjectVariable(r42.getTarget());
        if (androidx.constraintlayout.core.LinearSystem.getMetrics() != null) {
            androidx.constraintlayout.core.LinearSystem.getMetrics().nonresolvedWidgets++;
        }
        boolean r11 = r41.isConnected();
        boolean r12 = r42.isConnected();
        boolean r16 = r31.mCenter.isConnected();
        int r5 = r12 ? (r11 ? 1 : 0) + 1 : r11 ? 1 : 0;
        if (r16) {
            r5++;
        }
        int r18 = r48 ? 3 : r53;
        int r2 = androidx.constraintlayout.core.widgets.ConstraintWidget.AnonymousClass1.$SwitchMap$androidx$constraintlayout$core$widgets$ConstraintWidget$DimensionBehaviour[r39.ordinal()];
        if (r2 == 1 || r2 == 2 || r2 == 3 || r2 != 4) {
            r2 = r18;
        } else {
            r2 = r18;
            if (r2 != 4) {
                r18 = true;
                r14 = r31.mWidthOverride;
                if (r14 == -1 && r33) {
                    r31.mWidthOverride = -1;
                    r21 = r6;
                    r18 = false;
                } else {
                    r14 = r44;
                    r21 = r6;
                }
                r6 = r31.mHeightOverride;
                if (r6 != -1 && !r33) {
                    r31.mHeightOverride = -1;
                    r14 = r6;
                    r18 = false;
                }
                if (r31.mVisibility == 8) {
                    r14 = 0;
                    r18 = false;
                }
                if (r58) {
                    if (!r11 && !r12 && !r16) {
                        r32.addEquality(r9, r43);
                    } else if (r11 && !r12) {
                        r32.addEquality(r9, r7, r41.getMargin(), 8);
                    }
                }
                if (r18) {
                    if (r40) {
                        r32.addEquality(r8, r9, 0, 3);
                        if (r45 > 0) {
                            r32.addGreaterThan(r8, r9, r45, 8);
                        }
                        if (r46 < Integer.MAX_VALUE) {
                            r32.addLowerThan(r8, r9, r46, 8);
                        }
                    } else {
                        r32.addEquality(r8, r9, r14, 8);
                    }
                    r1 = r56;
                    r40 = r5;
                    r13 = r7;
                    r15 = r8;
                    r23 = r18;
                    r14 = r21;
                    r18 = r36;
                    r21 = r55;
                } else if (r5 == 2 || r48 || !(r2 == 1 || r2 == 0)) {
                    int r6 = r55 == -2 ? r14 : r55;
                    r1 = r56 == -2 ? r14 : r56;
                    if (r14 > 0 && r2 != 1) {
                        r14 = 0;
                    }
                    if (r6 > 0) {
                        r32.addGreaterThan(r8, r9, r6, 8);
                        r14 = java.lang.Math.max(r14, r6);
                    }
                    if (r1 > 0) {
                        if ((r34 && r2 == 1) ? false : true) {
                            r3 = 8;
                            r32.addLowerThan(r8, r9, r1, 8);
                        } else {
                            r3 = 8;
                        }
                        r14 = java.lang.Math.min(r14, r1);
                    } else {
                        r3 = 8;
                    }
                    if (r2 == 1) {
                        if (r34) {
                            r32.addEquality(r8, r9, r14, r3);
                        } else if (r50) {
                            r32.addEquality(r8, r9, r14, 5);
                            r32.addLowerThan(r8, r9, r14, r3);
                        } else {
                            r32.addEquality(r8, r9, r14, 5);
                            r32.addLowerThan(r8, r9, r14, r3);
                        }
                        r40 = r5;
                        r13 = r7;
                        r15 = r8;
                        r23 = r18;
                        r14 = r21;
                        r18 = r36;
                        r21 = r6;
                    } else if (r2 == 2) {
                        if (r41.getType() == androidx.constraintlayout.core.widgets.ConstraintAnchor.Type.TOP || r41.getType() == androidx.constraintlayout.core.widgets.ConstraintAnchor.Type.BOTTOM) {
                            r3 = r32.createObjectVariable(r31.mParent.getAnchor(androidx.constraintlayout.core.widgets.ConstraintAnchor.Type.TOP));
                            r4 = r32.createObjectVariable(r31.mParent.getAnchor(androidx.constraintlayout.core.widgets.ConstraintAnchor.Type.BOTTOM));
                        } else {
                            r3 = r32.createObjectVariable(r31.mParent.getAnchor(androidx.constraintlayout.core.widgets.ConstraintAnchor.Type.LEFT));
                            r4 = r32.createObjectVariable(r31.mParent.getAnchor(androidx.constraintlayout.core.widgets.ConstraintAnchor.Type.RIGHT));
                        }
                        r40 = r5;
                        r14 = r21;
                        r21 = r6;
                        r13 = r7;
                        r15 = r8;
                        r32.addConstraint(r32.createRow().createRowDimensionRatio(r8, r9, r4, r3, r57));
                        if (r34) {
                            r18 = false;
                        }
                        r23 = r18;
                        r18 = r36;
                    } else {
                        r40 = r5;
                        r13 = r7;
                        r15 = r8;
                        r14 = r21;
                        r21 = r6;
                        r23 = r18;
                        r18 = true;
                    }
                } else {
                    int r1 = java.lang.Math.max(r55, r14);
                    if (r56 > 0) {
                        r1 = java.lang.Math.min(r56, r1);
                    }
                    r32.addEquality(r8, r9, r1, 8);
                    r18 = r36;
                    r1 = r56;
                    r40 = r5;
                    r15 = r8;
                    r23 = false;
                    r14 = r21;
                    r21 = r55;
                    r13 = r7;
                }
                if (r58) {
                    r1 = r37;
                    r4 = r38;
                    r2 = r15;
                    r3 = 0;
                    r15 = r9;
                    r5 = r40;
                    r6 = 2;
                } else if (r50) {
                    r1 = r37;
                    r4 = r38;
                    r5 = r40;
                    r2 = r15;
                    r3 = 0;
                    r6 = 2;
                    r15 = r9;
                } else {
                    if (!r11 && !r12 && !r16) {
                        r2 = r15;
                        r1 = 5;
                        r3 = 0;
                    } else if (!r11 || r12) {
                        if (!r11 && r12) {
                            r32.addEquality(r15, r14, -r42.getMargin(), 8);
                            if (r34) {
                                if (r31.OPTIMIZE_WRAP && r9.isFinalValue && (r1 = r31.mParent) != null) {
                                    androidx.constraintlayout.core.widgets.ConstraintWidgetContainer r1 = (androidx.constraintlayout.core.widgets.ConstraintWidgetContainer) r1;
                                    if (r33) {
                                        r1.addHorizontalWrapMinVariable(r41);
                                    } else {
                                        r1.addVerticalWrapMinVariable(r41);
                                    }
                                } else {
                                    r1 = 5;
                                    r32.addGreaterThan(r9, r37, 0, 5);
                                    r3 = 0;
                                    r2 = r15;
                                }
                            }
                        } else if (r11 && r12) {
                            androidx.constraintlayout.core.widgets.ConstraintWidget r11 = r41.mTarget.mOwner;
                            androidx.constraintlayout.core.widgets.ConstraintWidget r5 = r42.mTarget.mOwner;
                            androidx.constraintlayout.core.widgets.ConstraintWidget r4 = getParent();
                            if (r23) {
                                if (r2 == 0) {
                                    if (r1 != 0 || r21 != 0) {
                                        r22 = false;
                                        r1 = 5;
                                        r3 = 5;
                                        r19 = true;
                                        r24 = true;
                                    } else if (r13.isFinalValue && r14.isFinalValue) {
                                        r32.addEquality(r9, r13, r41.getMargin(), 8);
                                        r32.addEquality(r15, r14, -r42.getMargin(), 8);
                                        return;
                                    } else {
                                        r19 = false;
                                        r24 = false;
                                        r1 = 8;
                                        r3 = 8;
                                        r22 = true;
                                    }
                                    if ((r11 instanceof androidx.constraintlayout.core.widgets.Barrier) || (r5 instanceof androidx.constraintlayout.core.widgets.Barrier)) {
                                        r8 = r38;
                                        r3 = r1;
                                        r1 = 6;
                                        r25 = r22;
                                        r22 = r19;
                                        r19 = 4;
                                        if (r24 || r13 != r14 || r11 == r4) {
                                            r26 = true;
                                        } else {
                                            r24 = false;
                                            r26 = false;
                                        }
                                        if (r22) {
                                            r39 = r2;
                                            r29 = r4;
                                            r12 = r5;
                                            r20 = r15;
                                            r15 = r9;
                                            r22 = r34;
                                        } else {
                                            if (r23 || r49 || r51 || r13 != r37 || r14 != r8) {
                                                r22 = r34;
                                                r27 = r1;
                                                r28 = r26;
                                                r26 = r3;
                                            } else {
                                                r22 = false;
                                                r26 = 8;
                                                r27 = 8;
                                                r28 = false;
                                            }
                                            r39 = r2;
                                            r29 = r4;
                                            r12 = r5;
                                            androidx.constraintlayout.core.SolverVariable r7 = r15;
                                            r20 = r15;
                                            r15 = r9;
                                            r32.addCentering(r9, r13, r41.getMargin(), r47, r14, r7, r42.getMargin(), r27);
                                            r3 = r26;
                                            r26 = r28;
                                        }
                                        if (r31.mVisibility != 8 && !r42.hasDependents()) {
                                            return;
                                        }
                                        if (r24) {
                                            r2 = r20;
                                        } else {
                                            if (r22 && r13 != r14 && !r23 && ((r11 instanceof androidx.constraintlayout.core.widgets.Barrier) || (r12 instanceof androidx.constraintlayout.core.widgets.Barrier))) {
                                                r3 = 6;
                                            }
                                            r32.addGreaterThan(r15, r13, r41.getMargin(), r3);
                                            r2 = r20;
                                            r32.addLowerThan(r2, r14, -r42.getMargin(), r3);
                                        }
                                        if (r22 || !r52 || (r11 instanceof androidx.constraintlayout.core.widgets.Barrier) || (r12 instanceof androidx.constraintlayout.core.widgets.Barrier)) {
                                            r1 = r29;
                                        } else {
                                            r1 = r29;
                                            if (r12 != r1) {
                                                r3 = 6;
                                                r4 = 6;
                                                r26 = true;
                                                if (r26) {
                                                    if (r25 && (!r51 || r35)) {
                                                        int r6 = (r11 == r1 || r12 == r1) ? 6 : r4;
                                                        r6 = ((r11 instanceof androidx.constraintlayout.core.widgets.Guideline) || (r12 instanceof androidx.constraintlayout.core.widgets.Guideline)) ? 5 : 5;
                                                        r6 = ((r11 instanceof androidx.constraintlayout.core.widgets.Barrier) || (r12 instanceof androidx.constraintlayout.core.widgets.Barrier)) ? 5 : 5;
                                                        if (r51) {
                                                            r6 = 5;
                                                        }
                                                        r4 = java.lang.Math.max(r6, r4);
                                                    }
                                                    if (r22) {
                                                        r4 = (r48 && !r51 && (r11 == r1 || r12 == r1)) ? 4 : java.lang.Math.min(r3, r4);
                                                    }
                                                    r32.addEquality(r15, r13, r41.getMargin(), r4);
                                                    r32.addEquality(r2, r14, -r42.getMargin(), r4);
                                                }
                                                if (r22) {
                                                    int r3 = r37 == r13 ? r41.getMargin() : 0;
                                                    if (r13 != r37) {
                                                        r32.addGreaterThan(r15, r37, r3, 5);
                                                    }
                                                }
                                                if (r22 || !r23 || r45 != 0 || r21 != 0) {
                                                    r1 = 5;
                                                    r3 = 0;
                                                } else if (r23 && r39 == 3) {
                                                    r3 = 0;
                                                    r32.addGreaterThan(r2, r15, 0, 8);
                                                    r1 = 5;
                                                } else {
                                                    r3 = 0;
                                                    r1 = 5;
                                                    r32.addGreaterThan(r2, r15, 0, 5);
                                                }
                                            }
                                        }
                                        r4 = r19;
                                        if (r26) {
                                        }
                                        if (r22) {
                                        }
                                        if (r22) {
                                        }
                                        r1 = 5;
                                        r3 = 0;
                                    } else {
                                        r8 = r38;
                                        r25 = r22;
                                        r22 = r19;
                                        r19 = r3;
                                        r3 = r1;
                                        r1 = 6;
                                        if (r24) {
                                        }
                                        r26 = true;
                                        if (r22) {
                                        }
                                        if (r31.mVisibility != 8) {
                                        }
                                        if (r24) {
                                        }
                                        if (r22) {
                                        }
                                        r1 = r29;
                                        r4 = r19;
                                        if (r26) {
                                        }
                                        if (r22) {
                                        }
                                        if (r22) {
                                        }
                                        r1 = 5;
                                        r3 = 0;
                                    }
                                } else if (r2 == 2) {
                                    if (!(r11 instanceof androidx.constraintlayout.core.widgets.Barrier) && !(r5 instanceof androidx.constraintlayout.core.widgets.Barrier)) {
                                        r8 = r38;
                                        r1 = 6;
                                        r3 = 5;
                                        r19 = 5;
                                        r22 = true;
                                        r24 = true;
                                        r25 = false;
                                        if (r24) {
                                        }
                                        r26 = true;
                                        if (r22) {
                                        }
                                        if (r31.mVisibility != 8) {
                                        }
                                        if (r24) {
                                        }
                                        if (r22) {
                                        }
                                        r1 = r29;
                                        r4 = r19;
                                        if (r26) {
                                        }
                                        if (r22) {
                                        }
                                        if (r22) {
                                        }
                                        r1 = 5;
                                        r3 = 0;
                                    }
                                } else if (r2 == 1) {
                                    r8 = r38;
                                    r1 = 6;
                                    r3 = 8;
                                    r19 = 4;
                                    r22 = true;
                                    r24 = true;
                                    r25 = false;
                                    if (r24) {
                                    }
                                    r26 = true;
                                    if (r22) {
                                    }
                                    if (r31.mVisibility != 8) {
                                    }
                                    if (r24) {
                                    }
                                    if (r22) {
                                    }
                                    r1 = r29;
                                    r4 = r19;
                                    if (r26) {
                                    }
                                    if (r22) {
                                    }
                                    if (r22) {
                                    }
                                    r1 = 5;
                                    r3 = 0;
                                } else if (r2 == 3) {
                                    if (r31.mResolvedDimensionRatioSide == -1) {
                                        if (r51) {
                                            r8 = r38;
                                            r1 = r34 ? 5 : 4;
                                        } else {
                                            r8 = r38;
                                            r1 = 8;
                                        }
                                        r3 = 8;
                                    } else if (r48) {
                                        if (r54 == 2 || r54 == 1) {
                                            r1 = 5;
                                            r3 = 4;
                                        } else {
                                            r1 = 8;
                                            r3 = 5;
                                        }
                                        r8 = r38;
                                        r19 = r3;
                                        r22 = true;
                                        r24 = true;
                                        r25 = true;
                                        r3 = r1;
                                        r1 = 6;
                                        if (r24) {
                                        }
                                        r26 = true;
                                        if (r22) {
                                        }
                                        if (r31.mVisibility != 8) {
                                        }
                                        if (r24) {
                                        }
                                        if (r22) {
                                        }
                                        r1 = r29;
                                        r4 = r19;
                                        if (r26) {
                                        }
                                        if (r22) {
                                        }
                                        if (r22) {
                                        }
                                        r1 = 5;
                                        r3 = 0;
                                    } else if (r1 > 0) {
                                        r8 = r38;
                                        r1 = 6;
                                        r3 = 5;
                                    } else {
                                        if (r1 != 0 || r21 != 0) {
                                            r8 = r38;
                                            r1 = 6;
                                            r3 = 5;
                                        } else if (r51) {
                                            r8 = r38;
                                            r3 = (r11 == r4 || r5 == r4) ? 5 : 4;
                                            r1 = 6;
                                        } else {
                                            r8 = r38;
                                            r1 = 6;
                                            r3 = 5;
                                            r19 = 8;
                                            r22 = true;
                                            r24 = true;
                                            r25 = true;
                                            if (r24) {
                                            }
                                            r26 = true;
                                            if (r22) {
                                            }
                                            if (r31.mVisibility != 8) {
                                            }
                                            if (r24) {
                                            }
                                            if (r22) {
                                            }
                                            r1 = r29;
                                            r4 = r19;
                                            if (r26) {
                                            }
                                            if (r22) {
                                            }
                                            if (r22) {
                                            }
                                            r1 = 5;
                                            r3 = 0;
                                        }
                                        r19 = 4;
                                        r22 = true;
                                        r24 = true;
                                        r25 = true;
                                        if (r24) {
                                        }
                                        r26 = true;
                                        if (r22) {
                                        }
                                        if (r31.mVisibility != 8) {
                                        }
                                        if (r24) {
                                        }
                                        if (r22) {
                                        }
                                        r1 = r29;
                                        r4 = r19;
                                        if (r26) {
                                        }
                                        if (r22) {
                                        }
                                        if (r22) {
                                        }
                                        r1 = 5;
                                        r3 = 0;
                                    }
                                    r19 = 5;
                                    r22 = true;
                                    r24 = true;
                                    r25 = true;
                                    if (r24) {
                                    }
                                    r26 = true;
                                    if (r22) {
                                    }
                                    if (r31.mVisibility != 8) {
                                    }
                                    if (r24) {
                                    }
                                    if (r22) {
                                    }
                                    r1 = r29;
                                    r4 = r19;
                                    if (r26) {
                                    }
                                    if (r22) {
                                    }
                                    if (r22) {
                                    }
                                    r1 = 5;
                                    r3 = 0;
                                } else {
                                    r8 = r38;
                                    r1 = 6;
                                    r3 = 5;
                                    r19 = 4;
                                    r22 = false;
                                    r24 = false;
                                    r25 = false;
                                    if (r24) {
                                    }
                                    r26 = true;
                                    if (r22) {
                                    }
                                    if (r31.mVisibility != 8) {
                                    }
                                    if (r24) {
                                    }
                                    if (r22) {
                                    }
                                    r1 = r29;
                                    r4 = r19;
                                    if (r26) {
                                    }
                                    if (r22) {
                                    }
                                    if (r22) {
                                    }
                                    r1 = 5;
                                    r3 = 0;
                                }
                                r6 = r1;
                                if (r22 || !r18) {
                                    return;
                                }
                                if (r42.mTarget != null) {
                                    r13 = r42.getMargin();
                                    r4 = r38;
                                } else {
                                    r4 = r38;
                                    r13 = r3;
                                }
                                if (r14 != r4) {
                                    if (r31.OPTIMIZE_WRAP && r2.isFinalValue && (r3 = r31.mParent) != null) {
                                        androidx.constraintlayout.core.widgets.ConstraintWidgetContainer r3 = (androidx.constraintlayout.core.widgets.ConstraintWidgetContainer) r3;
                                        if (r33) {
                                            r3.addHorizontalWrapMaxVariable(r42);
                                            return;
                                        } else {
                                            r3.addVerticalWrapMaxVariable(r42);
                                            return;
                                        }
                                    }
                                    r32.addGreaterThan(r4, r2, r13, r6);
                                    return;
                                }
                                return;
                            } else if (r13.isFinalValue && r14.isFinalValue) {
                                r32.addCentering(r9, r13, r41.getMargin(), r47, r14, r15, r42.getMargin(), 8);
                                if (r34 && r18) {
                                    if (r42.mTarget != null) {
                                        r13 = r42.getMargin();
                                        r8 = r38;
                                    } else {
                                        r8 = r38;
                                        r13 = 0;
                                    }
                                    if (r14 != r8) {
                                        r32.addGreaterThan(r8, r15, r13, 5);
                                        return;
                                    }
                                    return;
                                }
                                return;
                            }
                            r8 = r38;
                            r1 = 6;
                            r3 = 5;
                            r19 = 4;
                            r22 = true;
                            r24 = true;
                            r25 = false;
                            if (r24) {
                            }
                            r26 = true;
                            if (r22) {
                            }
                            if (r31.mVisibility != 8) {
                            }
                            if (r24) {
                            }
                            if (r22) {
                            }
                            r1 = r29;
                            r4 = r19;
                            if (r26) {
                            }
                            if (r22) {
                            }
                            if (r22) {
                            }
                            r1 = 5;
                            r3 = 0;
                            r6 = r1;
                            if (r22) {
                                return;
                            }
                            return;
                        }
                        r3 = 0;
                        r2 = r15;
                        r1 = 5;
                    } else {
                        r22 = r34;
                        r3 = 0;
                        r6 = (r34 && (r41.mTarget.mOwner instanceof androidx.constraintlayout.core.widgets.Barrier)) ? 8 : 5;
                        r2 = r15;
                        if (r22) {
                        }
                    }
                    r22 = r34;
                    r6 = r1;
                    if (r22) {
                    }
                }
                if (r5 >= r6 && r34 && r18) {
                    r32.addGreaterThan(r15, r1, r3, 8);
                    int r13 = (r33 || r31.mBaseline.mTarget == null) ? 1 : r3;
                    if (r33 || r31.mBaseline.mTarget == null) {
                        r14 = r13;
                    } else {
                        androidx.constraintlayout.core.widgets.ConstraintWidget r1 = r31.mBaseline.mTarget.mOwner;
                        r14 = (r1.mDimensionRatio != 0.0f && r1.mListDimensionBehaviors[r3] == androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && r1.mListDimensionBehaviors[1] == androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) ? 1 : r3;
                    }
                    if (r14 != 0) {
                        r32.addGreaterThan(r4, r2, r3, 8);
                        return;
                    }
                    return;
                }
                return;
            }
        }
        r18 = false;
        r14 = r31.mWidthOverride;
        if (r14 == -1) {
        }
        r14 = r44;
        r21 = r6;
        r6 = r31.mHeightOverride;
        if (r6 != -1) {
            r31.mHeightOverride = -1;
            r14 = r6;
            r18 = false;
        }
        if (r31.mVisibility == 8) {
        }
        if (r58) {
        }
        if (r18) {
        }
        if (r58) {
        }
        if (r5 >= r6) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: androidx.constraintlayout.core.widgets.ConstraintWidget$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$androidx$constraintlayout$core$widgets$ConstraintAnchor$Type;
        static final /* synthetic */ int[] $SwitchMap$androidx$constraintlayout$core$widgets$ConstraintWidget$DimensionBehaviour;

        static {
            int[] iArr = new int[DimensionBehaviour.values().length];
            $SwitchMap$androidx$constraintlayout$core$widgets$ConstraintWidget$DimensionBehaviour = iArr;
            try {
                iArr[DimensionBehaviour.FIXED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$androidx$constraintlayout$core$widgets$ConstraintWidget$DimensionBehaviour[DimensionBehaviour.WRAP_CONTENT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$androidx$constraintlayout$core$widgets$ConstraintWidget$DimensionBehaviour[DimensionBehaviour.MATCH_PARENT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$androidx$constraintlayout$core$widgets$ConstraintWidget$DimensionBehaviour[DimensionBehaviour.MATCH_CONSTRAINT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            int[] iArr2 = new int[ConstraintAnchor.Type.values().length];
            $SwitchMap$androidx$constraintlayout$core$widgets$ConstraintAnchor$Type = iArr2;
            try {
                iArr2[ConstraintAnchor.Type.LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$androidx$constraintlayout$core$widgets$ConstraintAnchor$Type[ConstraintAnchor.Type.TOP.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$androidx$constraintlayout$core$widgets$ConstraintAnchor$Type[ConstraintAnchor.Type.RIGHT.ordinal()] = 3;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$androidx$constraintlayout$core$widgets$ConstraintAnchor$Type[ConstraintAnchor.Type.BOTTOM.ordinal()] = 4;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$androidx$constraintlayout$core$widgets$ConstraintAnchor$Type[ConstraintAnchor.Type.BASELINE.ordinal()] = 5;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$androidx$constraintlayout$core$widgets$ConstraintAnchor$Type[ConstraintAnchor.Type.CENTER.ordinal()] = 6;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$androidx$constraintlayout$core$widgets$ConstraintAnchor$Type[ConstraintAnchor.Type.CENTER_X.ordinal()] = 7;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$androidx$constraintlayout$core$widgets$ConstraintAnchor$Type[ConstraintAnchor.Type.CENTER_Y.ordinal()] = 8;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$androidx$constraintlayout$core$widgets$ConstraintAnchor$Type[ConstraintAnchor.Type.NONE.ordinal()] = 9;
            } catch (NoSuchFieldError unused13) {
            }
        }
    }

    public void updateFromSolver(LinearSystem linearSystem, boolean z) {
        VerticalWidgetRun verticalWidgetRun;
        HorizontalWidgetRun horizontalWidgetRun;
        int objectVariableValue = linearSystem.getObjectVariableValue(this.mLeft);
        int objectVariableValue2 = linearSystem.getObjectVariableValue(this.mTop);
        int objectVariableValue3 = linearSystem.getObjectVariableValue(this.mRight);
        int objectVariableValue4 = linearSystem.getObjectVariableValue(this.mBottom);
        if (z && (horizontalWidgetRun = this.horizontalRun) != null && horizontalWidgetRun.start.resolved && this.horizontalRun.end.resolved) {
            objectVariableValue = this.horizontalRun.start.value;
            objectVariableValue3 = this.horizontalRun.end.value;
        }
        if (z && (verticalWidgetRun = this.verticalRun) != null && verticalWidgetRun.start.resolved && this.verticalRun.end.resolved) {
            objectVariableValue2 = this.verticalRun.start.value;
            objectVariableValue4 = this.verticalRun.end.value;
        }
        int i = objectVariableValue4 - objectVariableValue2;
        if (objectVariableValue3 - objectVariableValue < 0 || i < 0 || objectVariableValue == Integer.MIN_VALUE || objectVariableValue == Integer.MAX_VALUE || objectVariableValue2 == Integer.MIN_VALUE || objectVariableValue2 == Integer.MAX_VALUE || objectVariableValue3 == Integer.MIN_VALUE || objectVariableValue3 == Integer.MAX_VALUE || objectVariableValue4 == Integer.MIN_VALUE || objectVariableValue4 == Integer.MAX_VALUE) {
            objectVariableValue4 = 0;
            objectVariableValue = 0;
            objectVariableValue2 = 0;
            objectVariableValue3 = 0;
        }
        setFrame(objectVariableValue, objectVariableValue2, objectVariableValue3, objectVariableValue4);
    }

    public void copy(ConstraintWidget constraintWidget, HashMap<ConstraintWidget, ConstraintWidget> hashMap) {
        this.mHorizontalResolution = constraintWidget.mHorizontalResolution;
        this.mVerticalResolution = constraintWidget.mVerticalResolution;
        this.mMatchConstraintDefaultWidth = constraintWidget.mMatchConstraintDefaultWidth;
        this.mMatchConstraintDefaultHeight = constraintWidget.mMatchConstraintDefaultHeight;
        int[] iArr = this.mResolvedMatchConstraintDefault;
        int[] iArr2 = constraintWidget.mResolvedMatchConstraintDefault;
        iArr[0] = iArr2[0];
        iArr[1] = iArr2[1];
        this.mMatchConstraintMinWidth = constraintWidget.mMatchConstraintMinWidth;
        this.mMatchConstraintMaxWidth = constraintWidget.mMatchConstraintMaxWidth;
        this.mMatchConstraintMinHeight = constraintWidget.mMatchConstraintMinHeight;
        this.mMatchConstraintMaxHeight = constraintWidget.mMatchConstraintMaxHeight;
        this.mMatchConstraintPercentHeight = constraintWidget.mMatchConstraintPercentHeight;
        this.mIsWidthWrapContent = constraintWidget.mIsWidthWrapContent;
        this.mIsHeightWrapContent = constraintWidget.mIsHeightWrapContent;
        this.mResolvedDimensionRatioSide = constraintWidget.mResolvedDimensionRatioSide;
        this.mResolvedDimensionRatio = constraintWidget.mResolvedDimensionRatio;
        int[] iArr3 = constraintWidget.mMaxDimension;
        this.mMaxDimension = Arrays.copyOf(iArr3, iArr3.length);
        this.mCircleConstraintAngle = constraintWidget.mCircleConstraintAngle;
        this.hasBaseline = constraintWidget.hasBaseline;
        this.inPlaceholder = constraintWidget.inPlaceholder;
        this.mLeft.reset();
        this.mTop.reset();
        this.mRight.reset();
        this.mBottom.reset();
        this.mBaseline.reset();
        this.mCenterX.reset();
        this.mCenterY.reset();
        this.mCenter.reset();
        this.mListDimensionBehaviors = (DimensionBehaviour[]) Arrays.copyOf(this.mListDimensionBehaviors, 2);
        this.mParent = this.mParent == null ? null : hashMap.get(constraintWidget.mParent);
        this.mWidth = constraintWidget.mWidth;
        this.mHeight = constraintWidget.mHeight;
        this.mDimensionRatio = constraintWidget.mDimensionRatio;
        this.mDimensionRatioSide = constraintWidget.mDimensionRatioSide;
        this.mX = constraintWidget.mX;
        this.mY = constraintWidget.mY;
        this.mRelX = constraintWidget.mRelX;
        this.mRelY = constraintWidget.mRelY;
        this.mOffsetX = constraintWidget.mOffsetX;
        this.mOffsetY = constraintWidget.mOffsetY;
        this.mBaselineDistance = constraintWidget.mBaselineDistance;
        this.mMinWidth = constraintWidget.mMinWidth;
        this.mMinHeight = constraintWidget.mMinHeight;
        this.mHorizontalBiasPercent = constraintWidget.mHorizontalBiasPercent;
        this.mVerticalBiasPercent = constraintWidget.mVerticalBiasPercent;
        this.mCompanionWidget = constraintWidget.mCompanionWidget;
        this.mContainerItemSkip = constraintWidget.mContainerItemSkip;
        this.mVisibility = constraintWidget.mVisibility;
        this.mAnimated = constraintWidget.mAnimated;
        this.mDebugName = constraintWidget.mDebugName;
        this.mType = constraintWidget.mType;
        this.mDistToTop = constraintWidget.mDistToTop;
        this.mDistToLeft = constraintWidget.mDistToLeft;
        this.mDistToRight = constraintWidget.mDistToRight;
        this.mDistToBottom = constraintWidget.mDistToBottom;
        this.mLeftHasCentered = constraintWidget.mLeftHasCentered;
        this.mRightHasCentered = constraintWidget.mRightHasCentered;
        this.mTopHasCentered = constraintWidget.mTopHasCentered;
        this.mBottomHasCentered = constraintWidget.mBottomHasCentered;
        this.mHorizontalWrapVisited = constraintWidget.mHorizontalWrapVisited;
        this.mVerticalWrapVisited = constraintWidget.mVerticalWrapVisited;
        this.mHorizontalChainStyle = constraintWidget.mHorizontalChainStyle;
        this.mVerticalChainStyle = constraintWidget.mVerticalChainStyle;
        this.mHorizontalChainFixedPosition = constraintWidget.mHorizontalChainFixedPosition;
        this.mVerticalChainFixedPosition = constraintWidget.mVerticalChainFixedPosition;
        float[] fArr = this.mWeight;
        float[] fArr2 = constraintWidget.mWeight;
        fArr[0] = fArr2[0];
        fArr[1] = fArr2[1];
        ConstraintWidget[] constraintWidgetArr = this.mListNextMatchConstraintsWidget;
        ConstraintWidget[] constraintWidgetArr2 = constraintWidget.mListNextMatchConstraintsWidget;
        constraintWidgetArr[0] = constraintWidgetArr2[0];
        constraintWidgetArr[1] = constraintWidgetArr2[1];
        ConstraintWidget[] constraintWidgetArr3 = this.mNextChainWidget;
        ConstraintWidget[] constraintWidgetArr4 = constraintWidget.mNextChainWidget;
        constraintWidgetArr3[0] = constraintWidgetArr4[0];
        constraintWidgetArr3[1] = constraintWidgetArr4[1];
        ConstraintWidget constraintWidget2 = constraintWidget.mHorizontalNextWidget;
        this.mHorizontalNextWidget = constraintWidget2 == null ? null : hashMap.get(constraintWidget2);
        ConstraintWidget constraintWidget3 = constraintWidget.mVerticalNextWidget;
        this.mVerticalNextWidget = constraintWidget3 != null ? hashMap.get(constraintWidget3) : null;
    }

    public void updateFromRuns(boolean z, boolean z2) {
        int i;
        int i2;
        boolean isResolved = z & this.horizontalRun.isResolved();
        boolean isResolved2 = z2 & this.verticalRun.isResolved();
        int i3 = this.horizontalRun.start.value;
        int i4 = this.verticalRun.start.value;
        int i5 = this.horizontalRun.end.value;
        int i6 = this.verticalRun.end.value;
        int i7 = i6 - i4;
        if (i5 - i3 < 0 || i7 < 0 || i3 == Integer.MIN_VALUE || i3 == Integer.MAX_VALUE || i4 == Integer.MIN_VALUE || i4 == Integer.MAX_VALUE || i5 == Integer.MIN_VALUE || i5 == Integer.MAX_VALUE || i6 == Integer.MIN_VALUE || i6 == Integer.MAX_VALUE) {
            i3 = 0;
            i4 = 0;
            i5 = 0;
            i6 = 0;
        }
        int i8 = i5 - i3;
        int i9 = i6 - i4;
        if (isResolved) {
            this.mX = i3;
        }
        if (isResolved2) {
            this.mY = i4;
        }
        if (this.mVisibility == 8) {
            this.mWidth = 0;
            this.mHeight = 0;
            return;
        }
        if (isResolved) {
            if (this.mListDimensionBehaviors[0] == DimensionBehaviour.FIXED && i8 < (i2 = this.mWidth)) {
                i8 = i2;
            }
            this.mWidth = i8;
            int i10 = this.mMinWidth;
            if (i8 < i10) {
                this.mWidth = i10;
            }
        }
        if (isResolved2) {
            if (this.mListDimensionBehaviors[1] == DimensionBehaviour.FIXED && i9 < (i = this.mHeight)) {
                i9 = i;
            }
            this.mHeight = i9;
            int i11 = this.mMinHeight;
            if (i9 < i11) {
                this.mHeight = i11;
            }
        }
    }

    public void addChildrenToSolverByDependency(ConstraintWidgetContainer constraintWidgetContainer, LinearSystem linearSystem, HashSet<ConstraintWidget> hashSet, int i, boolean z) {
        if (z) {
            if (!hashSet.contains(this)) {
                return;
            }
            Optimizer.checkMatchParent(constraintWidgetContainer, linearSystem, this);
            hashSet.remove(this);
            addToSolver(linearSystem, constraintWidgetContainer.optimizeFor(64));
        }
        if (i == 0) {
            HashSet<ConstraintAnchor> dependents = this.mLeft.getDependents();
            if (dependents != null) {
                Iterator<ConstraintAnchor> it = dependents.iterator();
                while (it.hasNext()) {
                    it.next().mOwner.addChildrenToSolverByDependency(constraintWidgetContainer, linearSystem, hashSet, i, true);
                }
            }
            HashSet<ConstraintAnchor> dependents2 = this.mRight.getDependents();
            if (dependents2 != null) {
                Iterator<ConstraintAnchor> it2 = dependents2.iterator();
                while (it2.hasNext()) {
                    it2.next().mOwner.addChildrenToSolverByDependency(constraintWidgetContainer, linearSystem, hashSet, i, true);
                }
                return;
            }
            return;
        }
        HashSet<ConstraintAnchor> dependents3 = this.mTop.getDependents();
        if (dependents3 != null) {
            Iterator<ConstraintAnchor> it3 = dependents3.iterator();
            while (it3.hasNext()) {
                it3.next().mOwner.addChildrenToSolverByDependency(constraintWidgetContainer, linearSystem, hashSet, i, true);
            }
        }
        HashSet<ConstraintAnchor> dependents4 = this.mBottom.getDependents();
        if (dependents4 != null) {
            Iterator<ConstraintAnchor> it4 = dependents4.iterator();
            while (it4.hasNext()) {
                it4.next().mOwner.addChildrenToSolverByDependency(constraintWidgetContainer, linearSystem, hashSet, i, true);
            }
        }
        HashSet<ConstraintAnchor> dependents5 = this.mBaseline.getDependents();
        if (dependents5 != null) {
            Iterator<ConstraintAnchor> it5 = dependents5.iterator();
            while (it5.hasNext()) {
                it5.next().mOwner.addChildrenToSolverByDependency(constraintWidgetContainer, linearSystem, hashSet, i, true);
            }
        }
    }

    public void getSceneString(StringBuilder sb) {
        sb.append("  " + this.stringId + ":{\n");
        sb.append("    actualWidth:" + this.mWidth);
        sb.append("\n");
        sb.append("    actualHeight:" + this.mHeight);
        sb.append("\n");
        sb.append("    actualLeft:" + this.mX);
        sb.append("\n");
        sb.append("    actualTop:" + this.mY);
        sb.append("\n");
        getSceneString(sb, "left", this.mLeft);
        getSceneString(sb, "top", this.mTop);
        getSceneString(sb, "right", this.mRight);
        getSceneString(sb, "bottom", this.mBottom);
        getSceneString(sb, "baseline", this.mBaseline);
        getSceneString(sb, "centerX", this.mCenterX);
        getSceneString(sb, "centerY", this.mCenterY);
        getSceneString(sb, "    width", this.mWidth, this.mMinWidth, this.mMaxDimension[0], this.mWidthOverride, this.mMatchConstraintMinWidth, this.mMatchConstraintDefaultWidth, this.mMatchConstraintPercentWidth, this.mWeight[0]);
        getSceneString(sb, "    height", this.mHeight, this.mMinHeight, this.mMaxDimension[1], this.mHeightOverride, this.mMatchConstraintMinHeight, this.mMatchConstraintDefaultHeight, this.mMatchConstraintPercentHeight, this.mWeight[1]);
        serializeDimensionRatio(sb, "    dimensionRatio", this.mDimensionRatio, this.mDimensionRatioSide);
        serializeAttribute(sb, "    horizontalBias", this.mHorizontalBiasPercent, DEFAULT_BIAS);
        serializeAttribute(sb, "    verticalBias", this.mVerticalBiasPercent, DEFAULT_BIAS);
        serializeAttribute(sb, "    horizontalChainStyle", this.mHorizontalChainStyle, 0);
        serializeAttribute(sb, "    verticalChainStyle", this.mVerticalChainStyle, 0);
        sb.append("  }");
    }

    private void getSceneString(StringBuilder sb, String str, int i, int i2, int i3, int i4, int i5, int i6, float f, float f2) {
        sb.append(str);
        sb.append(" :  {\n");
        serializeAttribute(sb, "      size", i, 0);
        serializeAttribute(sb, "      min", i2, 0);
        serializeAttribute(sb, "      max", i3, Integer.MAX_VALUE);
        serializeAttribute(sb, "      matchMin", i5, 0);
        serializeAttribute(sb, "      matchDef", i6, 0);
        serializeAttribute(sb, "      matchPercent", f, 1.0f);
        sb.append("    },\n");
    }

    private void getSceneString(StringBuilder sb, String str, ConstraintAnchor constraintAnchor) {
        if (constraintAnchor.mTarget == null) {
            return;
        }
        sb.append("    ");
        sb.append(str);
        sb.append(" : [ '");
        sb.append(constraintAnchor.mTarget);
        sb.append("'");
        if (constraintAnchor.mGoneMargin != Integer.MIN_VALUE || constraintAnchor.mMargin != 0) {
            sb.append(",");
            sb.append(constraintAnchor.mMargin);
            if (constraintAnchor.mGoneMargin != Integer.MIN_VALUE) {
                sb.append(",");
                sb.append(constraintAnchor.mGoneMargin);
                sb.append(",");
            }
        }
        sb.append(" ] ,\n");
    }
}
