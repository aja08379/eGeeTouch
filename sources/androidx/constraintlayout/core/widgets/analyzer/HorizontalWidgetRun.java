package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.Helper;
import androidx.constraintlayout.core.widgets.analyzer.DependencyNode;
import androidx.constraintlayout.core.widgets.analyzer.WidgetRun;
/* loaded from: classes.dex */
public class HorizontalWidgetRun extends WidgetRun {
    private static int[] tempDimensions = new int[2];

    public HorizontalWidgetRun(ConstraintWidget constraintWidget) {
        super(constraintWidget);
        this.start.type = DependencyNode.Type.LEFT;
        this.end.type = DependencyNode.Type.RIGHT;
        this.orientation = 0;
    }

    public String toString() {
        return "HorizontalRun " + this.widget.getDebugName();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public void clear() {
        this.runGroup = null;
        this.start.clear();
        this.end.clear();
        this.dimension.clear();
        this.resolved = false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public void reset() {
        this.resolved = false;
        this.start.clear();
        this.start.resolved = false;
        this.end.clear();
        this.end.resolved = false;
        this.dimension.resolved = false;
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    boolean supportsWrapComputation() {
        return this.dimensionBehavior != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT || this.widget.mMatchConstraintDefaultWidth == 0;
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    void apply() {
        ConstraintWidget parent;
        ConstraintWidget parent2;
        if (this.widget.measured) {
            this.dimension.resolve(this.widget.getWidth());
        }
        if (!this.dimension.resolved) {
            this.dimensionBehavior = this.widget.getHorizontalDimensionBehaviour();
            if (this.dimensionBehavior != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                if (this.dimensionBehavior == ConstraintWidget.DimensionBehaviour.MATCH_PARENT && (parent2 = this.widget.getParent()) != null && (parent2.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.FIXED || parent2.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_PARENT)) {
                    int width = (parent2.getWidth() - this.widget.mLeft.getMargin()) - this.widget.mRight.getMargin();
                    addTarget(this.start, parent2.horizontalRun.start, this.widget.mLeft.getMargin());
                    addTarget(this.end, parent2.horizontalRun.end, -this.widget.mRight.getMargin());
                    this.dimension.resolve(width);
                    return;
                } else if (this.dimensionBehavior == ConstraintWidget.DimensionBehaviour.FIXED) {
                    this.dimension.resolve(this.widget.getWidth());
                }
            }
        } else if (this.dimensionBehavior == ConstraintWidget.DimensionBehaviour.MATCH_PARENT && (parent = this.widget.getParent()) != null && (parent.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.FIXED || parent.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_PARENT)) {
            addTarget(this.start, parent.horizontalRun.start, this.widget.mLeft.getMargin());
            addTarget(this.end, parent.horizontalRun.end, -this.widget.mRight.getMargin());
            return;
        }
        if (this.dimension.resolved && this.widget.measured) {
            if (this.widget.mListAnchors[0].mTarget != null && this.widget.mListAnchors[1].mTarget != null) {
                if (this.widget.isInHorizontalChain()) {
                    this.start.margin = this.widget.mListAnchors[0].getMargin();
                    this.end.margin = -this.widget.mListAnchors[1].getMargin();
                    return;
                }
                DependencyNode target = getTarget(this.widget.mListAnchors[0]);
                if (target != null) {
                    addTarget(this.start, target, this.widget.mListAnchors[0].getMargin());
                }
                DependencyNode target2 = getTarget(this.widget.mListAnchors[1]);
                if (target2 != null) {
                    addTarget(this.end, target2, -this.widget.mListAnchors[1].getMargin());
                }
                this.start.delegateToWidgetRun = true;
                this.end.delegateToWidgetRun = true;
                return;
            } else if (this.widget.mListAnchors[0].mTarget != null) {
                DependencyNode target3 = getTarget(this.widget.mListAnchors[0]);
                if (target3 != null) {
                    addTarget(this.start, target3, this.widget.mListAnchors[0].getMargin());
                    addTarget(this.end, this.start, this.dimension.value);
                    return;
                }
                return;
            } else if (this.widget.mListAnchors[1].mTarget != null) {
                DependencyNode target4 = getTarget(this.widget.mListAnchors[1]);
                if (target4 != null) {
                    addTarget(this.end, target4, -this.widget.mListAnchors[1].getMargin());
                    addTarget(this.start, this.end, -this.dimension.value);
                    return;
                }
                return;
            } else if ((this.widget instanceof Helper) || this.widget.getParent() == null || this.widget.getAnchor(ConstraintAnchor.Type.CENTER).mTarget != null) {
                return;
            } else {
                addTarget(this.start, this.widget.getParent().horizontalRun.start, this.widget.getX());
                addTarget(this.end, this.start, this.dimension.value);
                return;
            }
        }
        if (this.dimensionBehavior == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
            int i = this.widget.mMatchConstraintDefaultWidth;
            if (i == 2) {
                ConstraintWidget parent3 = this.widget.getParent();
                if (parent3 != null) {
                    DimensionDependency dimensionDependency = parent3.verticalRun.dimension;
                    this.dimension.targets.add(dimensionDependency);
                    dimensionDependency.dependencies.add(this.dimension);
                    this.dimension.delegateToWidgetRun = true;
                    this.dimension.dependencies.add(this.start);
                    this.dimension.dependencies.add(this.end);
                }
            } else if (i == 3) {
                if (this.widget.mMatchConstraintDefaultHeight == 3) {
                    this.start.updateDelegate = this;
                    this.end.updateDelegate = this;
                    this.widget.verticalRun.start.updateDelegate = this;
                    this.widget.verticalRun.end.updateDelegate = this;
                    this.dimension.updateDelegate = this;
                    if (this.widget.isInVerticalChain()) {
                        this.dimension.targets.add(this.widget.verticalRun.dimension);
                        this.widget.verticalRun.dimension.dependencies.add(this.dimension);
                        this.widget.verticalRun.dimension.updateDelegate = this;
                        this.dimension.targets.add(this.widget.verticalRun.start);
                        this.dimension.targets.add(this.widget.verticalRun.end);
                        this.widget.verticalRun.start.dependencies.add(this.dimension);
                        this.widget.verticalRun.end.dependencies.add(this.dimension);
                    } else if (this.widget.isInHorizontalChain()) {
                        this.widget.verticalRun.dimension.targets.add(this.dimension);
                        this.dimension.dependencies.add(this.widget.verticalRun.dimension);
                    } else {
                        this.widget.verticalRun.dimension.targets.add(this.dimension);
                    }
                } else {
                    DimensionDependency dimensionDependency2 = this.widget.verticalRun.dimension;
                    this.dimension.targets.add(dimensionDependency2);
                    dimensionDependency2.dependencies.add(this.dimension);
                    this.widget.verticalRun.start.dependencies.add(this.dimension);
                    this.widget.verticalRun.end.dependencies.add(this.dimension);
                    this.dimension.delegateToWidgetRun = true;
                    this.dimension.dependencies.add(this.start);
                    this.dimension.dependencies.add(this.end);
                    this.start.targets.add(this.dimension);
                    this.end.targets.add(this.dimension);
                }
            }
        }
        if (this.widget.mListAnchors[0].mTarget != null && this.widget.mListAnchors[1].mTarget != null) {
            if (this.widget.isInHorizontalChain()) {
                this.start.margin = this.widget.mListAnchors[0].getMargin();
                this.end.margin = -this.widget.mListAnchors[1].getMargin();
                return;
            }
            DependencyNode target5 = getTarget(this.widget.mListAnchors[0]);
            DependencyNode target6 = getTarget(this.widget.mListAnchors[1]);
            if (target5 != null) {
                target5.addDependency(this);
            }
            if (target6 != null) {
                target6.addDependency(this);
            }
            this.mRunType = WidgetRun.RunType.CENTER;
        } else if (this.widget.mListAnchors[0].mTarget != null) {
            DependencyNode target7 = getTarget(this.widget.mListAnchors[0]);
            if (target7 != null) {
                addTarget(this.start, target7, this.widget.mListAnchors[0].getMargin());
                addTarget(this.end, this.start, 1, this.dimension);
            }
        } else if (this.widget.mListAnchors[1].mTarget != null) {
            DependencyNode target8 = getTarget(this.widget.mListAnchors[1]);
            if (target8 != null) {
                addTarget(this.end, target8, -this.widget.mListAnchors[1].getMargin());
                addTarget(this.start, this.end, -1, this.dimension);
            }
        } else if ((this.widget instanceof Helper) || this.widget.getParent() == null) {
        } else {
            addTarget(this.start, this.widget.getParent().horizontalRun.start, this.widget.getX());
            addTarget(this.end, this.start, 1, this.dimension);
        }
    }

    private void computeInsetRatio(int[] iArr, int i, int i2, int i3, int i4, float f, int i5) {
        int i6 = i2 - i;
        int i7 = i4 - i3;
        if (i5 != -1) {
            if (i5 == 0) {
                iArr[0] = (int) ((i7 * f) + 0.5f);
                iArr[1] = i7;
                return;
            } else if (i5 != 1) {
                return;
            } else {
                iArr[0] = i6;
                iArr[1] = (int) ((i6 * f) + 0.5f);
                return;
            }
        }
        int i8 = (int) ((i7 * f) + 0.5f);
        int i9 = (int) ((i6 / f) + 0.5f);
        if (i8 <= i6 && i7 <= i7) {
            iArr[0] = i8;
            iArr[1] = i7;
        } else if (i6 > i6 || i9 > i7) {
        } else {
            iArr[0] = i6;
            iArr[1] = i9;
        }
    }

    /* renamed from: androidx.constraintlayout.core.widgets.analyzer.HorizontalWidgetRun$1  reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$androidx$constraintlayout$core$widgets$analyzer$WidgetRun$RunType;

        static {
            int[] iArr = new int[WidgetRun.RunType.values().length];
            $SwitchMap$androidx$constraintlayout$core$widgets$analyzer$WidgetRun$RunType = iArr;
            try {
                iArr[WidgetRun.RunType.START.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$androidx$constraintlayout$core$widgets$analyzer$WidgetRun$RunType[WidgetRun.RunType.END.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$androidx$constraintlayout$core$widgets$analyzer$WidgetRun$RunType[WidgetRun.RunType.CENTER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:120:0x02e6, code lost:
        if (r14 != 1) goto L132;
     */
    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun, androidx.constraintlayout.core.widgets.analyzer.Dependency
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void update(androidx.constraintlayout.core.widgets.analyzer.Dependency r17) {
        float r0;
        float r1;
        float r0;
        int r0;
        int r0 = androidx.constraintlayout.core.widgets.analyzer.HorizontalWidgetRun.AnonymousClass1.$SwitchMap$androidx$constraintlayout$core$widgets$analyzer$WidgetRun$RunType[r16.mRunType.ordinal()];
        if (r0 == 1) {
            updateRunStart(r17);
        } else if (r0 == 2) {
            updateRunEnd(r17);
        } else if (r0 == 3) {
            updateRunCenter(r17, r16.widget.mLeft, r16.widget.mRight, 0);
            return;
        }
        if (!r16.dimension.resolved && r16.dimensionBehavior == androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
            int r0 = r16.widget.mMatchConstraintDefaultWidth;
            if (r0 == 2) {
                androidx.constraintlayout.core.widgets.ConstraintWidget r0 = r16.widget.getParent();
                if (r0 != null && r0.horizontalRun.dimension.resolved) {
                    r16.dimension.resolve((int) ((r0.horizontalRun.dimension.value * r16.widget.mMatchConstraintPercentWidth) + 0.5f));
                }
            } else if (r0 == 3) {
                if (r16.widget.mMatchConstraintDefaultHeight == 0 || r16.widget.mMatchConstraintDefaultHeight == 3) {
                    androidx.constraintlayout.core.widgets.analyzer.DependencyNode r12 = r16.widget.verticalRun.start;
                    androidx.constraintlayout.core.widgets.analyzer.DependencyNode r13 = r16.widget.verticalRun.end;
                    boolean r0 = r16.widget.mLeft.mTarget != null;
                    boolean r2 = r16.widget.mTop.mTarget != null;
                    boolean r3 = r16.widget.mRight.mTarget != null;
                    boolean r4 = r16.widget.mBottom.mTarget != null;
                    int r14 = r16.widget.getDimensionRatioSide();
                    if (r0 && r2 && r3 && r4) {
                        float r15 = r16.widget.getDimensionRatio();
                        if (r12.resolved && r13.resolved) {
                            if (r16.start.readyToSolve && r16.end.readyToSolve) {
                                computeInsetRatio(androidx.constraintlayout.core.widgets.analyzer.HorizontalWidgetRun.tempDimensions, r16.start.targets.get(0).value + r16.start.margin, r16.end.targets.get(0).value - r16.end.margin, r12.value + r12.margin, r13.value - r13.margin, r15, r14);
                                r16.dimension.resolve(androidx.constraintlayout.core.widgets.analyzer.HorizontalWidgetRun.tempDimensions[0]);
                                r16.widget.verticalRun.dimension.resolve(androidx.constraintlayout.core.widgets.analyzer.HorizontalWidgetRun.tempDimensions[1]);
                                return;
                            }
                            return;
                        }
                        if (r16.start.resolved && r16.end.resolved) {
                            if (!r12.readyToSolve || !r13.readyToSolve) {
                                return;
                            }
                            computeInsetRatio(androidx.constraintlayout.core.widgets.analyzer.HorizontalWidgetRun.tempDimensions, r16.start.value + r16.start.margin, r16.end.value - r16.end.margin, r12.targets.get(0).value + r12.margin, r13.targets.get(0).value - r13.margin, r15, r14);
                            r16.dimension.resolve(androidx.constraintlayout.core.widgets.analyzer.HorizontalWidgetRun.tempDimensions[0]);
                            r16.widget.verticalRun.dimension.resolve(androidx.constraintlayout.core.widgets.analyzer.HorizontalWidgetRun.tempDimensions[1]);
                        }
                        if (!r16.start.readyToSolve || !r16.end.readyToSolve || !r12.readyToSolve || !r13.readyToSolve) {
                            return;
                        }
                        computeInsetRatio(androidx.constraintlayout.core.widgets.analyzer.HorizontalWidgetRun.tempDimensions, r16.start.targets.get(0).value + r16.start.margin, r16.end.targets.get(0).value - r16.end.margin, r12.targets.get(0).value + r12.margin, r13.targets.get(0).value - r13.margin, r15, r14);
                        r16.dimension.resolve(androidx.constraintlayout.core.widgets.analyzer.HorizontalWidgetRun.tempDimensions[0]);
                        r16.widget.verticalRun.dimension.resolve(androidx.constraintlayout.core.widgets.analyzer.HorizontalWidgetRun.tempDimensions[1]);
                    } else if (r0 && r3) {
                        if (!r16.start.readyToSolve || !r16.end.readyToSolve) {
                            return;
                        }
                        float r0 = r16.widget.getDimensionRatio();
                        int r2 = r16.start.targets.get(0).value + r16.start.margin;
                        int r3 = r16.end.targets.get(0).value - r16.end.margin;
                        if (r14 == -1 || r14 == 0) {
                            int r1 = getLimitedDimension(r3 - r2, 0);
                            int r2 = (int) ((r1 * r0) + 0.5f);
                            int r3 = getLimitedDimension(r2, 1);
                            if (r2 != r3) {
                                r1 = (int) ((r3 / r0) + 0.5f);
                            }
                            r16.dimension.resolve(r1);
                            r16.widget.verticalRun.dimension.resolve(r3);
                        } else if (r14 == 1) {
                            int r1 = getLimitedDimension(r3 - r2, 0);
                            int r2 = (int) ((r1 / r0) + 0.5f);
                            int r3 = getLimitedDimension(r2, 1);
                            if (r2 != r3) {
                                r1 = (int) ((r3 * r0) + 0.5f);
                            }
                            r16.dimension.resolve(r1);
                            r16.widget.verticalRun.dimension.resolve(r3);
                        }
                    } else if (r2 && r4) {
                        if (!r12.readyToSolve || !r13.readyToSolve) {
                            return;
                        }
                        float r0 = r16.widget.getDimensionRatio();
                        int r2 = r12.targets.get(0).value + r12.margin;
                        int r3 = r13.targets.get(0).value - r13.margin;
                        if (r14 != -1) {
                            if (r14 == 0) {
                                int r1 = getLimitedDimension(r3 - r2, 1);
                                int r2 = (int) ((r1 * r0) + 0.5f);
                                int r3 = getLimitedDimension(r2, 0);
                                if (r2 != r3) {
                                    r1 = (int) ((r3 / r0) + 0.5f);
                                }
                                r16.dimension.resolve(r3);
                                r16.widget.verticalRun.dimension.resolve(r1);
                            }
                        }
                        int r1 = getLimitedDimension(r3 - r2, 1);
                        int r2 = (int) ((r1 / r0) + 0.5f);
                        int r3 = getLimitedDimension(r2, 0);
                        if (r2 != r3) {
                            r1 = (int) ((r3 * r0) + 0.5f);
                        }
                        r16.dimension.resolve(r3);
                        r16.widget.verticalRun.dimension.resolve(r1);
                    }
                } else {
                    int r0 = r16.widget.getDimensionRatioSide();
                    if (r0 == -1) {
                        r0 = r16.widget.verticalRun.dimension.value;
                        r1 = r16.widget.getDimensionRatio();
                    } else if (r0 == 0) {
                        r0 = r16.widget.verticalRun.dimension.value / r16.widget.getDimensionRatio();
                        r0 = (int) (r0 + 0.5f);
                        r16.dimension.resolve(r0);
                    } else if (r0 == 1) {
                        r0 = r16.widget.verticalRun.dimension.value;
                        r1 = r16.widget.getDimensionRatio();
                    } else {
                        r0 = 0;
                        r16.dimension.resolve(r0);
                    }
                    r0 = r0 * r1;
                    r0 = (int) (r0 + 0.5f);
                    r16.dimension.resolve(r0);
                }
            }
        }
        if (r16.start.readyToSolve && r16.end.readyToSolve) {
            if (r16.start.resolved && r16.end.resolved && r16.dimension.resolved) {
                return;
            }
            if (!r16.dimension.resolved && r16.dimensionBehavior == androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && r16.widget.mMatchConstraintDefaultWidth == 0 && !r16.widget.isInHorizontalChain()) {
                int r0 = r16.start.targets.get(0).value + r16.start.margin;
                int r1 = r16.end.targets.get(0).value + r16.end.margin;
                r16.start.resolve(r0);
                r16.end.resolve(r1);
                r16.dimension.resolve(r1 - r0);
                return;
            }
            if (!r16.dimension.resolved && r16.dimensionBehavior == androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && r16.matchConstraintsType == 1 && r16.start.targets.size() > 0 && r16.end.targets.size() > 0) {
                int r0 = java.lang.Math.min((r16.end.targets.get(0).value + r16.end.margin) - (r16.start.targets.get(0).value + r16.start.margin), r16.dimension.wrapValue);
                int r1 = r16.widget.mMatchConstraintMaxWidth;
                int r0 = java.lang.Math.max(r16.widget.mMatchConstraintMinWidth, r0);
                if (r1 > 0) {
                    r0 = java.lang.Math.min(r1, r0);
                }
                r16.dimension.resolve(r0);
            }
            if (r16.dimension.resolved) {
                androidx.constraintlayout.core.widgets.analyzer.DependencyNode r0 = r16.start.targets.get(0);
                androidx.constraintlayout.core.widgets.analyzer.DependencyNode r1 = r16.end.targets.get(0);
                int r2 = r0.value + r16.start.margin;
                int r3 = r1.value + r16.end.margin;
                float r4 = r16.widget.getHorizontalBiasPercent();
                if (r0 == r1) {
                    r2 = r0.value;
                    r3 = r1.value;
                    r4 = 0.5f;
                }
                r16.start.resolve((int) (r2 + 0.5f + (((r3 - r2) - r16.dimension.value) * r4)));
                r16.end.resolve(r16.start.value + r16.dimension.value);
            }
        }
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public void applyToWidget() {
        if (this.start.resolved) {
            this.widget.setX(this.start.value);
        }
    }
}
