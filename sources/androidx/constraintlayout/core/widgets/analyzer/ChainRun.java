package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.ConstraintWidgetContainer;
import java.util.ArrayList;
import java.util.Iterator;
/* loaded from: classes.dex */
public class ChainRun extends WidgetRun {
    private int chainStyle;
    ArrayList<WidgetRun> widgets;

    public ChainRun(ConstraintWidget constraintWidget, int i) {
        super(constraintWidget);
        this.widgets = new ArrayList<>();
        this.orientation = i;
        build();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("ChainRun ");
        sb.append(this.orientation == 0 ? "horizontal : " : "vertical : ");
        Iterator<WidgetRun> it = this.widgets.iterator();
        while (it.hasNext()) {
            sb.append("<");
            sb.append(it.next());
            sb.append("> ");
        }
        return sb.toString();
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    boolean supportsWrapComputation() {
        int size = this.widgets.size();
        for (int i = 0; i < size; i++) {
            if (!this.widgets.get(i).supportsWrapComputation()) {
                return false;
            }
        }
        return true;
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public long getWrapDimension() {
        int size = this.widgets.size();
        long j = 0;
        for (int i = 0; i < size; i++) {
            WidgetRun widgetRun = this.widgets.get(i);
            j = j + widgetRun.start.margin + widgetRun.getWrapDimension() + widgetRun.end.margin;
        }
        return j;
    }

    private void build() {
        ConstraintWidget constraintWidget;
        ConstraintWidget constraintWidget2 = this.widget;
        ConstraintWidget previousChainMember = constraintWidget2.getPreviousChainMember(this.orientation);
        while (true) {
            ConstraintWidget constraintWidget3 = previousChainMember;
            constraintWidget = constraintWidget2;
            constraintWidget2 = constraintWidget3;
            if (constraintWidget2 == null) {
                break;
            }
            previousChainMember = constraintWidget2.getPreviousChainMember(this.orientation);
        }
        this.widget = constraintWidget;
        this.widgets.add(constraintWidget.getRun(this.orientation));
        ConstraintWidget nextChainMember = constraintWidget.getNextChainMember(this.orientation);
        while (nextChainMember != null) {
            this.widgets.add(nextChainMember.getRun(this.orientation));
            nextChainMember = nextChainMember.getNextChainMember(this.orientation);
        }
        Iterator<WidgetRun> it = this.widgets.iterator();
        while (it.hasNext()) {
            WidgetRun next = it.next();
            if (this.orientation == 0) {
                next.widget.horizontalChainRun = this;
            } else if (this.orientation == 1) {
                next.widget.verticalChainRun = this;
            }
        }
        if ((this.orientation == 0 && ((ConstraintWidgetContainer) this.widget.getParent()).isRtl()) && this.widgets.size() > 1) {
            ArrayList<WidgetRun> arrayList = this.widgets;
            this.widget = arrayList.get(arrayList.size() - 1).widget;
        }
        this.chainStyle = this.orientation == 0 ? this.widget.getHorizontalChainStyle() : this.widget.getVerticalChainStyle();
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    void clear() {
        this.runGroup = null;
        Iterator<WidgetRun> it = this.widgets.iterator();
        while (it.hasNext()) {
            it.next().clear();
        }
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    void reset() {
        this.start.resolved = false;
        this.end.resolved = false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:266:0x03e8, code lost:
        r2 = r2 - r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x00cd, code lost:
        if (r3.dimension.resolved != false) goto L66;
     */
    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun, androidx.constraintlayout.core.widgets.analyzer.Dependency
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void update(androidx.constraintlayout.core.widgets.analyzer.Dependency r23) {
        int r6;
        int r7;
        int r3;
        int r14;
        int r15;
        float r17;
        int r21;
        int r7;
        int r9;
        int r2;
        int r21;
        float r19;
        int r20;
        int r11;
        int r10;
        if (r22.start.resolved && r22.end.resolved) {
            androidx.constraintlayout.core.widgets.ConstraintWidget r1 = r22.widget.getParent();
            boolean r1 = r1 instanceof androidx.constraintlayout.core.widgets.ConstraintWidgetContainer ? ((androidx.constraintlayout.core.widgets.ConstraintWidgetContainer) r1).isRtl() : false;
            int r2 = r22.end.value - r22.start.value;
            int r4 = r22.widgets.size();
            int r5 = 0;
            while (true) {
                r6 = -1;
                r7 = 8;
                if (r5 >= r4) {
                    r5 = -1;
                    break;
                } else if (r22.widgets.get(r5).widget.getVisibility() != 8) {
                    break;
                } else {
                    r5++;
                }
            }
            int r8 = r4 - 1;
            int r9 = r8;
            while (true) {
                if (r9 < 0) {
                    break;
                }
                if (r22.widgets.get(r9).widget.getVisibility() != 8) {
                    r6 = r9;
                    break;
                }
                r9--;
            }
            int r9 = 0;
            while (r9 < 2) {
                int r13 = 0;
                r14 = 0;
                r15 = 0;
                int r16 = 0;
                r17 = 0.0f;
                while (r13 < r4) {
                    androidx.constraintlayout.core.widgets.analyzer.WidgetRun r3 = r22.widgets.get(r13);
                    if (r3.widget.getVisibility() != r7) {
                        r16++;
                        if (r13 > 0 && r13 >= r5) {
                            r14 += r3.start.margin;
                        }
                        int r11 = r3.dimension.value;
                        boolean r7 = r3.dimensionBehavior != androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                        if (r7) {
                            if (r22.orientation == 0 && !r3.widget.horizontalRun.dimension.resolved) {
                                return;
                            }
                            if (r22.orientation == 1 && !r3.widget.verticalRun.dimension.resolved) {
                                return;
                            }
                        } else {
                            if (r3.matchConstraintsType == 1 && r9 == 0) {
                                r11 = r3.dimension.wrapValue;
                                r15++;
                            }
                            r7 = true;
                        }
                        if (r7) {
                            r14 += r11;
                        } else {
                            r15++;
                            float r7 = r3.widget.mWeight[r22.orientation];
                            if (r7 >= 0.0f) {
                                r17 += r7;
                            }
                        }
                        if (r13 < r8 && r13 < r6) {
                            r14 += -r3.end.margin;
                        }
                    }
                    r13++;
                    r7 = 8;
                }
                if (r14 < r2 || r15 == 0) {
                    r3 = r16;
                    break;
                } else {
                    r9++;
                    r7 = 8;
                }
            }
            r3 = 0;
            r14 = 0;
            r15 = 0;
            r17 = 0.0f;
            int r7 = r22.start.value;
            if (r1) {
                r7 = r22.end.value;
            }
            if (r14 > r2) {
                r7 = r1 ? r7 + ((int) (((r14 - r2) / 2.0f) + 0.5f)) : r7 - ((int) (((r14 - r2) / 2.0f) + 0.5f));
            }
            if (r15 > 0) {
                float r10 = r2 - r14;
                int r11 = (int) ((r10 / r15) + 0.5f);
                int r13 = 0;
                int r16 = 0;
                while (r13 < r4) {
                    androidx.constraintlayout.core.widgets.analyzer.WidgetRun r12 = r22.widgets.get(r13);
                    int r18 = r11;
                    if (r12.widget.getVisibility() == 8 || r12.dimensionBehavior != androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT || r12.dimension.resolved) {
                        r21 = r7;
                        r19 = r10;
                        r20 = r14;
                    } else {
                        int r9 = r17 > 0.0f ? (int) (((r12.widget.mWeight[r22.orientation] * r10) / r17) + 0.5f) : r18;
                        if (r22.orientation == 0) {
                            r11 = r12.widget.mMatchConstraintMaxWidth;
                            r19 = r10;
                            r10 = r12.widget.mMatchConstraintMinWidth;
                        } else {
                            r19 = r10;
                            r11 = r12.widget.mMatchConstraintMaxHeight;
                            r10 = r12.widget.mMatchConstraintMinHeight;
                        }
                        r20 = r14;
                        r21 = r7;
                        int r7 = java.lang.Math.max(r10, r12.matchConstraintsType == 1 ? java.lang.Math.min(r9, r12.dimension.wrapValue) : r9);
                        if (r11 > 0) {
                            r7 = java.lang.Math.min(r11, r7);
                        }
                        if (r7 != r9) {
                            r16++;
                            r9 = r7;
                        }
                        r12.dimension.resolve(r9);
                    }
                    r13++;
                    r11 = r18;
                    r10 = r19;
                    r14 = r20;
                    r7 = r21;
                }
                r21 = r7;
                int r20 = r14;
                if (r16 > 0) {
                    r15 -= r16;
                    int r9 = 0;
                    for (int r7 = 0; r7 < r4; r7++) {
                        androidx.constraintlayout.core.widgets.analyzer.WidgetRun r10 = r22.widgets.get(r7);
                        if (r10.widget.getVisibility() != 8) {
                            if (r7 > 0 && r7 >= r5) {
                                r9 += r10.start.margin;
                            }
                            r9 += r10.dimension.value;
                            if (r7 < r8 && r7 < r6) {
                                r9 += -r10.end.margin;
                            }
                        }
                    }
                    r14 = r9;
                } else {
                    r14 = r20;
                }
                r9 = 2;
                if (r22.chainStyle == 2 && r16 == 0) {
                    r7 = 0;
                    r22.chainStyle = 0;
                } else {
                    r7 = 0;
                }
            } else {
                r21 = r7;
                r7 = 0;
                r9 = 2;
            }
            if (r14 > r2) {
                r22.chainStyle = r9;
            }
            if (r3 > 0 && r15 == 0 && r5 == r6) {
                r22.chainStyle = r9;
            }
            int r9 = r22.chainStyle;
            if (r9 == 1) {
                if (r3 > 1) {
                    r2 = (r2 - r14) / (r3 - 1);
                } else {
                    r2 = r3 == 1 ? (r2 - r14) / 2 : r7;
                }
                if (r15 > 0) {
                    r2 = r7;
                }
                int r7 = r21;
                for (int r3 = r7; r3 < r4; r3++) {
                    androidx.constraintlayout.core.widgets.analyzer.WidgetRun r9 = r22.widgets.get(r1 ? r4 - (r3 + 1) : r3);
                    if (r9.widget.getVisibility() == 8) {
                        r9.start.resolve(r7);
                        r9.end.resolve(r7);
                    } else {
                        if (r3 > 0) {
                            r7 = r1 ? r7 - r2 : r7 + r2;
                        }
                        if (r3 > 0 && r3 >= r5) {
                            if (r1) {
                                r7 -= r9.start.margin;
                            } else {
                                r7 += r9.start.margin;
                            }
                        }
                        if (r1) {
                            r9.end.resolve(r7);
                        } else {
                            r9.start.resolve(r7);
                        }
                        int r10 = r9.dimension.value;
                        if (r9.dimensionBehavior == androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && r9.matchConstraintsType == 1) {
                            r10 = r9.dimension.wrapValue;
                        }
                        r7 = r1 ? r7 - r10 : r7 + r10;
                        if (r1) {
                            r9.start.resolve(r7);
                        } else {
                            r9.end.resolve(r7);
                        }
                        r9.resolved = true;
                        if (r3 < r8 && r3 < r6) {
                            if (r1) {
                                r7 -= -r9.end.margin;
                            } else {
                                r7 += -r9.end.margin;
                            }
                        }
                    }
                }
            } else if (r9 == 0) {
                int r2 = (r2 - r14) / (r3 + 1);
                if (r15 > 0) {
                    r2 = r7;
                }
                int r7 = r21;
                for (int r3 = r7; r3 < r4; r3++) {
                    androidx.constraintlayout.core.widgets.analyzer.WidgetRun r9 = r22.widgets.get(r1 ? r4 - (r3 + 1) : r3);
                    if (r9.widget.getVisibility() == 8) {
                        r9.start.resolve(r7);
                        r9.end.resolve(r7);
                    } else {
                        int r7 = r1 ? r7 - r2 : r7 + r2;
                        if (r3 > 0 && r3 >= r5) {
                            if (r1) {
                                r7 -= r9.start.margin;
                            } else {
                                r7 += r9.start.margin;
                            }
                        }
                        if (r1) {
                            r9.end.resolve(r7);
                        } else {
                            r9.start.resolve(r7);
                        }
                        int r10 = r9.dimension.value;
                        if (r9.dimensionBehavior == androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && r9.matchConstraintsType == 1) {
                            r10 = java.lang.Math.min(r10, r9.dimension.wrapValue);
                        }
                        r7 = r1 ? r7 - r10 : r7 + r10;
                        if (r1) {
                            r9.start.resolve(r7);
                        } else {
                            r9.end.resolve(r7);
                        }
                        if (r3 < r8 && r3 < r6) {
                            if (r1) {
                                r7 -= -r9.end.margin;
                            } else {
                                r7 += -r9.end.margin;
                            }
                        }
                    }
                }
            } else if (r9 == 2) {
                float r3 = r22.orientation == 0 ? r22.widget.getHorizontalBiasPercent() : r22.widget.getVerticalBiasPercent();
                if (r1) {
                    r3 = 1.0f - r3;
                }
                int r2 = (int) (((r2 - r14) * r3) + 0.5f);
                if (r2 < 0 || r15 > 0) {
                    r2 = r7;
                }
                int r2 = r1 ? r21 - r2 : r21 + r2;
                for (int r3 = r7; r3 < r4; r3++) {
                    androidx.constraintlayout.core.widgets.analyzer.WidgetRun r7 = r22.widgets.get(r1 ? r4 - (r3 + 1) : r3);
                    if (r7.widget.getVisibility() == 8) {
                        r7.start.resolve(r2);
                        r7.end.resolve(r2);
                    } else {
                        if (r3 > 0 && r3 >= r5) {
                            if (r1) {
                                r2 -= r7.start.margin;
                            } else {
                                r2 += r7.start.margin;
                            }
                        }
                        if (r1) {
                            r7.end.resolve(r2);
                        } else {
                            r7.start.resolve(r2);
                        }
                        int r9 = r7.dimension.value;
                        if (r7.dimensionBehavior == androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && r7.matchConstraintsType == 1) {
                            r9 = r7.dimension.wrapValue;
                        }
                        r2 += r9;
                        if (r1) {
                            r7.start.resolve(r2);
                        } else {
                            r7.end.resolve(r2);
                        }
                        if (r3 < r8 && r3 < r6) {
                            if (r1) {
                                r2 -= -r7.end.margin;
                            } else {
                                r2 += -r7.end.margin;
                            }
                        }
                    }
                }
            }
        }
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public void applyToWidget() {
        for (int i = 0; i < this.widgets.size(); i++) {
            this.widgets.get(i).applyToWidget();
        }
    }

    private ConstraintWidget getFirstVisibleWidget() {
        for (int i = 0; i < this.widgets.size(); i++) {
            WidgetRun widgetRun = this.widgets.get(i);
            if (widgetRun.widget.getVisibility() != 8) {
                return widgetRun.widget;
            }
        }
        return null;
    }

    private ConstraintWidget getLastVisibleWidget() {
        for (int size = this.widgets.size() - 1; size >= 0; size--) {
            WidgetRun widgetRun = this.widgets.get(size);
            if (widgetRun.widget.getVisibility() != 8) {
                return widgetRun.widget;
            }
        }
        return null;
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    void apply() {
        Iterator<WidgetRun> it = this.widgets.iterator();
        while (it.hasNext()) {
            it.next().apply();
        }
        int size = this.widgets.size();
        if (size < 1) {
            return;
        }
        ConstraintWidget constraintWidget = this.widgets.get(0).widget;
        ConstraintWidget constraintWidget2 = this.widgets.get(size - 1).widget;
        if (this.orientation == 0) {
            ConstraintAnchor constraintAnchor = constraintWidget.mLeft;
            ConstraintAnchor constraintAnchor2 = constraintWidget2.mRight;
            DependencyNode target = getTarget(constraintAnchor, 0);
            int margin = constraintAnchor.getMargin();
            ConstraintWidget firstVisibleWidget = getFirstVisibleWidget();
            if (firstVisibleWidget != null) {
                margin = firstVisibleWidget.mLeft.getMargin();
            }
            if (target != null) {
                addTarget(this.start, target, margin);
            }
            DependencyNode target2 = getTarget(constraintAnchor2, 0);
            int margin2 = constraintAnchor2.getMargin();
            ConstraintWidget lastVisibleWidget = getLastVisibleWidget();
            if (lastVisibleWidget != null) {
                margin2 = lastVisibleWidget.mRight.getMargin();
            }
            if (target2 != null) {
                addTarget(this.end, target2, -margin2);
            }
        } else {
            ConstraintAnchor constraintAnchor3 = constraintWidget.mTop;
            ConstraintAnchor constraintAnchor4 = constraintWidget2.mBottom;
            DependencyNode target3 = getTarget(constraintAnchor3, 1);
            int margin3 = constraintAnchor3.getMargin();
            ConstraintWidget firstVisibleWidget2 = getFirstVisibleWidget();
            if (firstVisibleWidget2 != null) {
                margin3 = firstVisibleWidget2.mTop.getMargin();
            }
            if (target3 != null) {
                addTarget(this.start, target3, margin3);
            }
            DependencyNode target4 = getTarget(constraintAnchor4, 1);
            int margin4 = constraintAnchor4.getMargin();
            ConstraintWidget lastVisibleWidget2 = getLastVisibleWidget();
            if (lastVisibleWidget2 != null) {
                margin4 = lastVisibleWidget2.mBottom.getMargin();
            }
            if (target4 != null) {
                addTarget(this.end, target4, -margin4);
            }
        }
        this.start.updateDelegate = this;
        this.end.updateDelegate = this;
    }
}
