package androidx.constraintlayout.core.widgets;

import androidx.constraintlayout.core.LinearSystem;
import java.util.ArrayList;
/* loaded from: classes.dex */
public class Chain {
    private static final boolean DEBUG = false;
    public static final boolean USE_CHAIN_OPTIMIZATION = false;

    public static void applyChainConstraints(ConstraintWidgetContainer constraintWidgetContainer, LinearSystem linearSystem, ArrayList<ConstraintWidget> arrayList, int i) {
        ChainHead[] chainHeadArr;
        int i2;
        int i3;
        if (i == 0) {
            i3 = constraintWidgetContainer.mHorizontalChainsSize;
            chainHeadArr = constraintWidgetContainer.mHorizontalChainsArray;
            i2 = 0;
        } else {
            int i4 = constraintWidgetContainer.mVerticalChainsSize;
            chainHeadArr = constraintWidgetContainer.mVerticalChainsArray;
            i2 = 2;
            i3 = i4;
        }
        for (int i5 = 0; i5 < i3; i5++) {
            ChainHead chainHead = chainHeadArr[i5];
            chainHead.define();
            if (arrayList == null || (arrayList != null && arrayList.contains(chainHead.mFirst))) {
                applyChainConstraints(constraintWidgetContainer, linearSystem, i, i2, chainHead);
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0037, code lost:
        if (r2.mHorizontalChainStyle == 2) goto L325;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x004a, code lost:
        if (r2.mVerticalChainStyle == 2) goto L325;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x004c, code lost:
        r5 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x004e, code lost:
        r5 = false;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:113:0x01bc  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x01f8  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x0386  */
    /* JADX WARN: Removed duplicated region for block: B:226:0x03df  */
    /* JADX WARN: Removed duplicated region for block: B:239:0x03ff  */
    /* JADX WARN: Removed duplicated region for block: B:286:0x04db  */
    /* JADX WARN: Removed duplicated region for block: B:291:0x0510  */
    /* JADX WARN: Removed duplicated region for block: B:301:0x052f  */
    /* JADX WARN: Removed duplicated region for block: B:304:0x053a  */
    /* JADX WARN: Removed duplicated region for block: B:305:0x053f  */
    /* JADX WARN: Removed duplicated region for block: B:308:0x0545  */
    /* JADX WARN: Removed duplicated region for block: B:309:0x054a  */
    /* JADX WARN: Removed duplicated region for block: B:311:0x054e  */
    /* JADX WARN: Removed duplicated region for block: B:315:0x055f  */
    /* JADX WARN: Removed duplicated region for block: B:317:0x0562  */
    /* JADX WARN: Removed duplicated region for block: B:330:0x03e1 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static void applyChainConstraints(androidx.constraintlayout.core.widgets.ConstraintWidgetContainer r38, androidx.constraintlayout.core.LinearSystem r39, int r40, int r41, androidx.constraintlayout.core.widgets.ChainHead r42) {
        boolean r8;
        boolean r15;
        boolean r5;
        java.util.ArrayList<androidx.constraintlayout.core.widgets.ConstraintWidget> r0;
        androidx.constraintlayout.core.widgets.ConstraintWidget r15;
        androidx.constraintlayout.core.widgets.ConstraintAnchor r1;
        androidx.constraintlayout.core.widgets.ConstraintAnchor r10;
        androidx.constraintlayout.core.widgets.ConstraintAnchor r11;
        int r15;
        androidx.constraintlayout.core.widgets.ConstraintWidget r20;
        int r10;
        androidx.constraintlayout.core.widgets.ConstraintWidget r38;
        androidx.constraintlayout.core.widgets.ConstraintAnchor r5;
        androidx.constraintlayout.core.SolverVariable r6;
        androidx.constraintlayout.core.SolverVariable r8;
        androidx.constraintlayout.core.widgets.ConstraintWidget r21;
        androidx.constraintlayout.core.widgets.ConstraintAnchor r5;
        androidx.constraintlayout.core.widgets.ConstraintWidget r38;
        androidx.constraintlayout.core.SolverVariable r6;
        androidx.constraintlayout.core.widgets.ConstraintWidget r20;
        androidx.constraintlayout.core.widgets.ConstraintWidget r16;
        androidx.constraintlayout.core.SolverVariable r3;
        androidx.constraintlayout.core.SolverVariable r5;
        float r2;
        int r2;
        int r7;
        java.util.ArrayList<androidx.constraintlayout.core.widgets.ConstraintWidget> r25;
        int r16;
        boolean r24;
        androidx.constraintlayout.core.widgets.ConstraintWidget r27;
        androidx.constraintlayout.core.widgets.ConstraintWidget r28;
        int r7;
        int r10 = r40;
        androidx.constraintlayout.core.widgets.ConstraintWidget r11 = r42.mFirst;
        androidx.constraintlayout.core.widgets.ConstraintWidget r12 = r42.mLast;
        androidx.constraintlayout.core.widgets.ConstraintWidget r13 = r42.mFirstVisibleWidget;
        androidx.constraintlayout.core.widgets.ConstraintWidget r14 = r42.mLastVisibleWidget;
        androidx.constraintlayout.core.widgets.ConstraintWidget r2 = r42.mHead;
        float r3 = r42.mTotalWeight;
        androidx.constraintlayout.core.widgets.ConstraintWidget r4 = r42.mFirstMatchConstraintWidget;
        androidx.constraintlayout.core.widgets.ConstraintWidget r4 = r42.mLastMatchConstraintWidget;
        boolean r4 = r38.mListDimensionBehaviors[r10] == androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        if (r10 == 0) {
            r8 = r2.mHorizontalChainStyle == 0;
            r15 = r2.mHorizontalChainStyle == 1;
        } else {
            r8 = r2.mVerticalChainStyle == 0;
            r15 = r2.mVerticalChainStyle == 1;
        }
        boolean r17 = r15;
        boolean r6 = false;
        boolean r15 = r8;
        androidx.constraintlayout.core.widgets.ConstraintWidget r8 = r11;
        while (true) {
            if (r6) {
                break;
            }
            androidx.constraintlayout.core.widgets.ConstraintAnchor r7 = r8.mListAnchors[r41];
            int r20 = r5 ? 1 : 4;
            int r24 = r7.getMargin();
            float r25 = r3;
            boolean r26 = r6;
            boolean r3 = r8.mListDimensionBehaviors[r10] == androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && r8.mResolvedMatchConstraintDefault[r10] == 0;
            if (r7.mTarget != null && r8 != r11) {
                r24 += r7.mTarget.getMargin();
            }
            int r6 = r24;
            if (!r5 || r8 == r11 || r8 == r13) {
                r24 = r15;
            } else {
                r24 = r15;
                r20 = 8;
            }
            if (r7.mTarget != null) {
                if (r8 == r13) {
                    r27 = r2;
                    r28 = r11;
                    r39.addGreaterThan(r7.mSolverVariable, r7.mTarget.mSolverVariable, r6, 6);
                } else {
                    r27 = r2;
                    r28 = r11;
                    r39.addGreaterThan(r7.mSolverVariable, r7.mTarget.mSolverVariable, r6, 8);
                }
                if (r3 && !r5) {
                    r20 = 5;
                }
                r39.addEquality(r7.mSolverVariable, r7.mTarget.mSolverVariable, r6, (r8 == r13 && r5 && r8.isInBarrier(r10)) ? 5 : r20);
            } else {
                r27 = r2;
                r28 = r11;
            }
            if (r4) {
                if (r8.getVisibility() == 8 || r8.mListDimensionBehaviors[r10] != androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    r7 = 0;
                } else {
                    r7 = 0;
                    r39.addGreaterThan(r8.mListAnchors[r41 + 1].mSolverVariable, r8.mListAnchors[r41].mSolverVariable, 0, 5);
                }
                r39.addGreaterThan(r8.mListAnchors[r41].mSolverVariable, r38.mListAnchors[r41].mSolverVariable, r7, 8);
            }
            androidx.constraintlayout.core.widgets.ConstraintAnchor r2 = r8.mListAnchors[r41 + 1].mTarget;
            if (r2 != null) {
                androidx.constraintlayout.core.widgets.ConstraintWidget r2 = r2.mOwner;
                if (r2.mListAnchors[r41].mTarget != null && r2.mListAnchors[r41].mTarget.mOwner == r8) {
                    r22 = r2;
                }
            }
            if (r22 != null) {
                r8 = r22;
                r6 = r26;
            } else {
                r6 = true;
            }
            r15 = r24;
            r3 = r25;
            r2 = r27;
            r11 = r28;
        }
        androidx.constraintlayout.core.widgets.ConstraintWidget r27 = r2;
        float r25 = r3;
        androidx.constraintlayout.core.widgets.ConstraintWidget r28 = r11;
        boolean r24 = r15;
        if (r14 != null) {
            int r3 = r41 + 1;
            if (r12.mListAnchors[r3].mTarget != null) {
                androidx.constraintlayout.core.widgets.ConstraintAnchor r2 = r14.mListAnchors[r3];
                if ((r14.mListDimensionBehaviors[r10] == androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && r14.mResolvedMatchConstraintDefault[r10] == 0) && !r5 && r2.mTarget.mOwner == r38) {
                    r39.addEquality(r2.mSolverVariable, r2.mTarget.mSolverVariable, -r2.getMargin(), 5);
                } else if (r5 && r2.mTarget.mOwner == r38) {
                    r39.addEquality(r2.mSolverVariable, r2.mTarget.mSolverVariable, -r2.getMargin(), 4);
                }
                r39.addLowerThan(r2.mSolverVariable, r12.mListAnchors[r3].mTarget.mSolverVariable, -r2.getMargin(), 6);
                if (r4) {
                    int r2 = r41 + 1;
                    r39.addGreaterThan(r38.mListAnchors[r2].mSolverVariable, r12.mListAnchors[r2].mSolverVariable, r12.mListAnchors[r2].getMargin(), 8);
                }
                r0 = r42.mWeightedMatchConstraintsWidgets;
                if (r0 != null && (r2 = r0.size()) > 1) {
                    float r4 = (r42.mHasUndefinedWeights || r42.mHasComplexMatchWeights) ? r25 : r42.mWidgetsMatchCount;
                    float r6 = 0.0f;
                    float r30 = 0.0f;
                    androidx.constraintlayout.core.widgets.ConstraintWidget r8 = null;
                    r7 = 0;
                    while (r7 < r2) {
                        androidx.constraintlayout.core.widgets.ConstraintWidget r15 = r0.get(r7);
                        float r3 = r15.mWeight[r10];
                        if (r3 < r6) {
                            if (r42.mHasComplexMatchWeights) {
                                r39.addEquality(r15.mListAnchors[r41 + 1].mSolverVariable, r15.mListAnchors[r41].mSolverVariable, 0, 4);
                                r25 = r0;
                                r16 = r2;
                                r7++;
                                r2 = r16;
                                r0 = r25;
                                r6 = 0.0f;
                            } else {
                                r3 = 1.0f;
                                r6 = 0.0f;
                            }
                        }
                        if (r3 == r6) {
                            r39.addEquality(r15.mListAnchors[r41 + 1].mSolverVariable, r15.mListAnchors[r41].mSolverVariable, 0, 8);
                            r25 = r0;
                            r16 = r2;
                            r7++;
                            r2 = r16;
                            r0 = r25;
                            r6 = 0.0f;
                        } else {
                            if (r8 != null) {
                                androidx.constraintlayout.core.SolverVariable r6 = r8.mListAnchors[r41].mSolverVariable;
                                int r16 = r41 + 1;
                                androidx.constraintlayout.core.SolverVariable r8 = r8.mListAnchors[r16].mSolverVariable;
                                androidx.constraintlayout.core.SolverVariable r11 = r15.mListAnchors[r41].mSolverVariable;
                                r25 = r0;
                                androidx.constraintlayout.core.SolverVariable r0 = r15.mListAnchors[r16].mSolverVariable;
                                r16 = r2;
                                androidx.constraintlayout.core.ArrayRow r2 = r39.createRow();
                                r2.createRowEqualMatchDimensions(r30, r4, r3, r6, r8, r11, r0);
                                r39.addConstraint(r2);
                            } else {
                                r25 = r0;
                                r16 = r2;
                            }
                            r30 = r3;
                            r8 = r15;
                            r7++;
                            r2 = r16;
                            r0 = r25;
                            r6 = 0.0f;
                        }
                    }
                }
                if (r13 == null && (r13 == r14 || r5)) {
                    androidx.constraintlayout.core.widgets.ConstraintAnchor r0 = r28.mListAnchors[r41];
                    int r2 = r41 + 1;
                    androidx.constraintlayout.core.widgets.ConstraintAnchor r1 = r12.mListAnchors[r2];
                    androidx.constraintlayout.core.SolverVariable r3 = r0.mTarget != null ? r0.mTarget.mSolverVariable : null;
                    androidx.constraintlayout.core.SolverVariable r5 = r1.mTarget != null ? r1.mTarget.mSolverVariable : null;
                    androidx.constraintlayout.core.widgets.ConstraintAnchor r0 = r13.mListAnchors[r41];
                    if (r14 != null) {
                        r1 = r14.mListAnchors[r2];
                    }
                    if (r3 != null && r5 != null) {
                        if (r10 == 0) {
                            r2 = r27.mHorizontalBiasPercent;
                        } else {
                            r2 = r27.mVerticalBiasPercent;
                        }
                        r39.addCentering(r0.mSolverVariable, r3, r0.getMargin(), r2, r5, r1.mSolverVariable, r1.getMargin(), 7);
                    }
                } else if (r24 || r13 == null) {
                    int r8 = 8;
                    if (r17 && r13 != null) {
                        boolean r18 = r42.mWidgetsMatchCount <= 0 && r42.mWidgetsCount == r42.mWidgetsMatchCount;
                        androidx.constraintlayout.core.widgets.ConstraintWidget r7 = r13;
                        r15 = r7;
                        while (r15 != null) {
                            androidx.constraintlayout.core.widgets.ConstraintWidget r0 = r15.mNextChainWidget[r10];
                            while (r0 != null && r0.getVisibility() == r8) {
                                r0 = r0.mNextChainWidget[r10];
                            }
                            if (r15 == r13 || r15 == r14 || r0 == null) {
                                r20 = r7;
                                r10 = r8;
                            } else {
                                androidx.constraintlayout.core.widgets.ConstraintWidget r6 = r0 == r14 ? null : r0;
                                androidx.constraintlayout.core.widgets.ConstraintAnchor r0 = r15.mListAnchors[r41];
                                androidx.constraintlayout.core.SolverVariable r1 = r0.mSolverVariable;
                                if (r0.mTarget != null) {
                                    androidx.constraintlayout.core.SolverVariable r2 = r0.mTarget.mSolverVariable;
                                }
                                int r3 = r41 + 1;
                                androidx.constraintlayout.core.SolverVariable r2 = r7.mListAnchors[r3].mSolverVariable;
                                int r0 = r0.getMargin();
                                int r4 = r15.mListAnchors[r3].getMargin();
                                if (r6 != null) {
                                    r5 = r6.mListAnchors[r41];
                                    androidx.constraintlayout.core.SolverVariable r8 = r5.mSolverVariable;
                                    r38 = r6;
                                    r8 = r5.mTarget != null ? r5.mTarget.mSolverVariable : null;
                                    r6 = r8;
                                } else {
                                    r38 = r6;
                                    r5 = r14.mListAnchors[r41];
                                    r6 = r5 != null ? r5.mSolverVariable : null;
                                    r8 = r15.mListAnchors[r3].mSolverVariable;
                                }
                                if (r5 != null) {
                                    r4 += r5.getMargin();
                                }
                                int r16 = r4;
                                int r3 = r7.mListAnchors[r3].getMargin() + r0;
                                int r19 = r18 ? 8 : 4;
                                if (r1 == null || r2 == null || r6 == null || r8 == null) {
                                    r21 = r38;
                                    r20 = r7;
                                    r10 = 8;
                                } else {
                                    r21 = r38;
                                    r20 = r7;
                                    r10 = 8;
                                    r39.addCentering(r1, r2, r3, 0.5f, r6, r8, r16, r19);
                                }
                                r0 = r21;
                            }
                            r7 = r15.getVisibility() != r10 ? r15 : r20;
                            r15 = r0;
                            r8 = r10;
                            r10 = r40;
                        }
                        androidx.constraintlayout.core.widgets.ConstraintAnchor r0 = r13.mListAnchors[r41];
                        r1 = r28.mListAnchors[r41].mTarget;
                        int r3 = r41 + 1;
                        r10 = r14.mListAnchors[r3];
                        r11 = r12.mListAnchors[r3].mTarget;
                        if (r1 != null) {
                            r15 = 5;
                        } else if (r13 != r14) {
                            r15 = 5;
                            r39.addEquality(r0.mSolverVariable, r1.mSolverVariable, r0.getMargin(), 5);
                        } else {
                            r15 = 5;
                            if (r11 != null) {
                                r39.addCentering(r0.mSolverVariable, r1.mSolverVariable, r0.getMargin(), 0.5f, r10.mSolverVariable, r11.mSolverVariable, r10.getMargin(), 5);
                            }
                        }
                        if (r11 != null && r13 != r14) {
                            r39.addEquality(r10.mSolverVariable, r11.mSolverVariable, -r10.getMargin(), r15);
                        }
                    }
                } else {
                    boolean r18 = r42.mWidgetsMatchCount > 0 && r42.mWidgetsCount == r42.mWidgetsMatchCount;
                    androidx.constraintlayout.core.widgets.ConstraintWidget r8 = r13;
                    androidx.constraintlayout.core.widgets.ConstraintWidget r15 = r8;
                    while (r15 != null) {
                        androidx.constraintlayout.core.widgets.ConstraintWidget r7 = r15.mNextChainWidget[r10];
                        while (r7 != null && r7.getVisibility() == 8) {
                            r7 = r7.mNextChainWidget[r10];
                        }
                        if (r7 != null || r15 == r14) {
                            androidx.constraintlayout.core.widgets.ConstraintAnchor r0 = r15.mListAnchors[r41];
                            androidx.constraintlayout.core.SolverVariable r1 = r0.mSolverVariable;
                            androidx.constraintlayout.core.SolverVariable r2 = r0.mTarget != null ? r0.mTarget.mSolverVariable : null;
                            if (r8 != r15) {
                                r2 = r8.mListAnchors[r41 + 1].mSolverVariable;
                            } else if (r15 == r13) {
                                r2 = r28.mListAnchors[r41].mTarget != null ? r28.mListAnchors[r41].mTarget.mSolverVariable : null;
                            }
                            int r0 = r0.getMargin();
                            int r4 = r41 + 1;
                            int r3 = r15.mListAnchors[r4].getMargin();
                            if (r7 != null) {
                                r5 = r7.mListAnchors[r41];
                                r6 = r5.mSolverVariable;
                            } else {
                                r5 = r12.mListAnchors[r4].mTarget;
                                if (r5 != null) {
                                    r6 = r5.mSolverVariable;
                                } else {
                                    r38 = r7;
                                    r6 = null;
                                    androidx.constraintlayout.core.SolverVariable r7 = r15.mListAnchors[r4].mSolverVariable;
                                    if (r5 != null) {
                                        r3 += r5.getMargin();
                                    }
                                    int r0 = r0 + r8.mListAnchors[r4].getMargin();
                                    if (r1 != null || r2 == null || r6 == null || r7 == null) {
                                        r20 = r38;
                                    } else {
                                        if (r15 == r13) {
                                            r0 = r13.mListAnchors[r41].getMargin();
                                        }
                                        int r5 = r0;
                                        r20 = r38;
                                        r16 = r8;
                                        r39.addCentering(r1, r2, r5, 0.5f, r6, r7, r15 == r14 ? r14.mListAnchors[r4].getMargin() : r3, r18 ? 8 : 5);
                                        if (r15.getVisibility() != 8) {
                                            r15 = r16;
                                        }
                                        r8 = r15;
                                        r15 = r20;
                                    }
                                }
                            }
                            r38 = r7;
                            androidx.constraintlayout.core.SolverVariable r7 = r15.mListAnchors[r4].mSolverVariable;
                            if (r5 != null) {
                            }
                            int r0 = r0 + r8.mListAnchors[r4].getMargin();
                            if (r1 != null) {
                            }
                            r20 = r38;
                        } else {
                            r20 = r7;
                        }
                        r16 = r8;
                        if (r15.getVisibility() != 8) {
                        }
                        r8 = r15;
                        r15 = r20;
                    }
                }
                if ((r24 && !r17) || r13 == null || r13 == r14) {
                    return;
                }
                androidx.constraintlayout.core.widgets.ConstraintAnchor r0 = r13.mListAnchors[r41];
                if (r14 == null) {
                    r14 = r13;
                }
                int r2 = r41 + 1;
                androidx.constraintlayout.core.widgets.ConstraintAnchor r1 = r14.mListAnchors[r2];
                r3 = r0.mTarget == null ? r0.mTarget.mSolverVariable : null;
                androidx.constraintlayout.core.SolverVariable r4 = r1.mTarget == null ? r1.mTarget.mSolverVariable : null;
                if (r12 == r14) {
                    androidx.constraintlayout.core.widgets.ConstraintAnchor r4 = r12.mListAnchors[r2];
                    r5 = r4.mTarget != null ? r4.mTarget.mSolverVariable : null;
                } else {
                    r5 = r4;
                }
                if (r13 == r14) {
                    r0 = r13.mListAnchors[r41];
                    r1 = r13.mListAnchors[r2];
                }
                if (r3 != null || r5 == null) {
                }
                r39.addCentering(r0.mSolverVariable, r3, r0.getMargin(), 0.5f, r5, r1.mSolverVariable, r14.mListAnchors[r2].getMargin(), 5);
                return;
            }
        }
        if (r4) {
        }
        r0 = r42.mWeightedMatchConstraintsWidgets;
        if (r0 != null) {
            if (r42.mHasUndefinedWeights) {
            }
            float r6 = 0.0f;
            float r30 = 0.0f;
            androidx.constraintlayout.core.widgets.ConstraintWidget r8 = null;
            r7 = 0;
            while (r7 < r2) {
            }
        }
        if (r13 == null) {
        }
        if (r24) {
        }
        int r8 = 8;
        if (r17) {
            if (r42.mWidgetsMatchCount <= 0) {
            }
            androidx.constraintlayout.core.widgets.ConstraintWidget r7 = r13;
            r15 = r7;
            while (r15 != null) {
            }
            androidx.constraintlayout.core.widgets.ConstraintAnchor r0 = r13.mListAnchors[r41];
            r1 = r28.mListAnchors[r41].mTarget;
            int r3 = r41 + 1;
            r10 = r14.mListAnchors[r3];
            r11 = r12.mListAnchors[r3].mTarget;
            if (r1 != null) {
            }
            if (r11 != null) {
                r39.addEquality(r10.mSolverVariable, r11.mSolverVariable, -r10.getMargin(), r15);
            }
        }
        if (r24) {
        }
        androidx.constraintlayout.core.widgets.ConstraintAnchor r0 = r13.mListAnchors[r41];
        if (r14 == null) {
        }
        int r2 = r41 + 1;
        androidx.constraintlayout.core.widgets.ConstraintAnchor r1 = r14.mListAnchors[r2];
        if (r0.mTarget == null) {
        }
        if (r1.mTarget == null) {
        }
        if (r12 == r14) {
        }
        if (r13 == r14) {
        }
        if (r3 != null) {
        }
    }
}
