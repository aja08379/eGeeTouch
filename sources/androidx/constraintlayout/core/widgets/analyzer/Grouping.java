package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.Guideline;
import androidx.constraintlayout.core.widgets.HelperWidget;
import java.util.ArrayList;
/* loaded from: classes.dex */
public class Grouping {
    private static final boolean DEBUG = false;
    private static final boolean DEBUG_GROUPING = false;

    public static boolean validInGroup(ConstraintWidget.DimensionBehaviour dimensionBehaviour, ConstraintWidget.DimensionBehaviour dimensionBehaviour2, ConstraintWidget.DimensionBehaviour dimensionBehaviour3, ConstraintWidget.DimensionBehaviour dimensionBehaviour4) {
        return (dimensionBehaviour3 == ConstraintWidget.DimensionBehaviour.FIXED || dimensionBehaviour3 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT || (dimensionBehaviour3 == ConstraintWidget.DimensionBehaviour.MATCH_PARENT && dimensionBehaviour != ConstraintWidget.DimensionBehaviour.WRAP_CONTENT)) || (dimensionBehaviour4 == ConstraintWidget.DimensionBehaviour.FIXED || dimensionBehaviour4 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT || (dimensionBehaviour4 == ConstraintWidget.DimensionBehaviour.MATCH_PARENT && dimensionBehaviour2 != ConstraintWidget.DimensionBehaviour.WRAP_CONTENT));
    }

    /* JADX WARN: Removed duplicated region for block: B:182:0x035f  */
    /* JADX WARN: Removed duplicated region for block: B:194:0x039b  */
    /* JADX WARN: Removed duplicated region for block: B:197:0x03a0 A[ADDED_TO_REGION] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean simpleSolvingPass(androidx.constraintlayout.core.widgets.ConstraintWidgetContainer r16, androidx.constraintlayout.core.widgets.analyzer.BasicMeasure.Measurer r17) {
        androidx.constraintlayout.core.widgets.analyzer.WidgetGroup r2;
        boolean r5;
        boolean r8;
        androidx.constraintlayout.core.widgets.analyzer.WidgetGroup r4;
        java.util.ArrayList<androidx.constraintlayout.core.widgets.ConstraintWidget> r1 = r16.getChildren();
        int r2 = r1.size();
        int r3 = 0;
        for (int r4 = 0; r4 < r2; r4++) {
            androidx.constraintlayout.core.widgets.ConstraintWidget r5 = r1.get(r4);
            if (!validInGroup(r16.getHorizontalDimensionBehaviour(), r16.getVerticalDimensionBehaviour(), r5.getHorizontalDimensionBehaviour(), r5.getVerticalDimensionBehaviour()) || (r5 instanceof androidx.constraintlayout.core.widgets.Flow)) {
                return false;
            }
        }
        if (r16.mMetrics != null) {
            r16.mMetrics.grouping++;
        }
        int r5 = 0;
        java.util.ArrayList r6 = null;
        java.util.ArrayList r7 = null;
        java.util.ArrayList r8 = null;
        java.util.ArrayList r9 = null;
        java.util.ArrayList r10 = null;
        java.util.ArrayList r11 = null;
        while (r5 < r2) {
            androidx.constraintlayout.core.widgets.ConstraintWidget r13 = r1.get(r5);
            if (!validInGroup(r16.getHorizontalDimensionBehaviour(), r16.getVerticalDimensionBehaviour(), r13.getHorizontalDimensionBehaviour(), r13.getVerticalDimensionBehaviour())) {
                androidx.constraintlayout.core.widgets.ConstraintWidgetContainer.measure(r3, r13, r17, r16.mMeasure, androidx.constraintlayout.core.widgets.analyzer.BasicMeasure.Measure.SELF_DIMENSIONS);
            }
            boolean r4 = r13 instanceof androidx.constraintlayout.core.widgets.Guideline;
            if (r4) {
                androidx.constraintlayout.core.widgets.Guideline r12 = (androidx.constraintlayout.core.widgets.Guideline) r13;
                if (r12.getOrientation() == 0) {
                    if (r8 == null) {
                        r8 = new java.util.ArrayList();
                    }
                    r8.add(r12);
                }
                if (r12.getOrientation() == 1) {
                    if (r6 == null) {
                        r6 = new java.util.ArrayList();
                    }
                    r6.add(r12);
                }
            }
            if (r13 instanceof androidx.constraintlayout.core.widgets.HelperWidget) {
                if (r13 instanceof androidx.constraintlayout.core.widgets.Barrier) {
                    androidx.constraintlayout.core.widgets.Barrier r3 = (androidx.constraintlayout.core.widgets.Barrier) r13;
                    if (r3.getOrientation() == 0) {
                        if (r7 == null) {
                            r7 = new java.util.ArrayList();
                        }
                        r7.add(r3);
                    }
                    if (r3.getOrientation() == 1) {
                        if (r9 == null) {
                            r9 = new java.util.ArrayList();
                        }
                        r9.add(r3);
                    }
                } else {
                    androidx.constraintlayout.core.widgets.HelperWidget r3 = (androidx.constraintlayout.core.widgets.HelperWidget) r13;
                    if (r7 == null) {
                        r7 = new java.util.ArrayList();
                    }
                    r7.add(r3);
                    if (r9 == null) {
                        r9 = new java.util.ArrayList();
                    }
                    r9.add(r3);
                }
            }
            if (r13.mLeft.mTarget == null && r13.mRight.mTarget == null && !r4 && !(r13 instanceof androidx.constraintlayout.core.widgets.Barrier)) {
                if (r10 == null) {
                    r10 = new java.util.ArrayList();
                }
                r10.add(r13);
            }
            if (r13.mTop.mTarget == null && r13.mBottom.mTarget == null && r13.mBaseline.mTarget == null && !r4 && !(r13 instanceof androidx.constraintlayout.core.widgets.Barrier)) {
                if (r11 == null) {
                    r11 = new java.util.ArrayList();
                }
                r11.add(r13);
            }
            r5++;
            r3 = 0;
        }
        java.util.ArrayList<androidx.constraintlayout.core.widgets.analyzer.WidgetGroup> r3 = new java.util.ArrayList<>();
        if (r6 != null) {
            java.util.Iterator r4 = r6.iterator();
            while (r4.hasNext()) {
                findDependents((androidx.constraintlayout.core.widgets.Guideline) r4.next(), 0, r3, null);
            }
        }
        int r6 = 0;
        androidx.constraintlayout.core.widgets.analyzer.WidgetGroup r12 = null;
        if (r7 != null) {
            java.util.Iterator r4 = r7.iterator();
            while (r4.hasNext()) {
                androidx.constraintlayout.core.widgets.HelperWidget r5 = (androidx.constraintlayout.core.widgets.HelperWidget) r4.next();
                androidx.constraintlayout.core.widgets.analyzer.WidgetGroup r7 = findDependents(r5, r6, r3, r12);
                r5.addDependents(r3, r6, r7);
                r7.cleanup(r3);
                r6 = 0;
                r12 = null;
            }
        }
        androidx.constraintlayout.core.widgets.ConstraintAnchor r4 = r16.getAnchor(androidx.constraintlayout.core.widgets.ConstraintAnchor.Type.LEFT);
        if (r4.getDependents() != null) {
            java.util.Iterator<androidx.constraintlayout.core.widgets.ConstraintAnchor> r4 = r4.getDependents().iterator();
            while (r4.hasNext()) {
                findDependents(r4.next().mOwner, 0, r3, null);
            }
        }
        androidx.constraintlayout.core.widgets.ConstraintAnchor r4 = r16.getAnchor(androidx.constraintlayout.core.widgets.ConstraintAnchor.Type.RIGHT);
        if (r4.getDependents() != null) {
            java.util.Iterator<androidx.constraintlayout.core.widgets.ConstraintAnchor> r4 = r4.getDependents().iterator();
            while (r4.hasNext()) {
                findDependents(r4.next().mOwner, 0, r3, null);
            }
        }
        androidx.constraintlayout.core.widgets.ConstraintAnchor r4 = r16.getAnchor(androidx.constraintlayout.core.widgets.ConstraintAnchor.Type.CENTER);
        if (r4.getDependents() != null) {
            java.util.Iterator<androidx.constraintlayout.core.widgets.ConstraintAnchor> r4 = r4.getDependents().iterator();
            while (r4.hasNext()) {
                findDependents(r4.next().mOwner, 0, r3, null);
            }
        }
        androidx.constraintlayout.core.widgets.analyzer.WidgetGroup r7 = null;
        if (r10 != null) {
            java.util.Iterator r4 = r10.iterator();
            while (r4.hasNext()) {
                findDependents((androidx.constraintlayout.core.widgets.ConstraintWidget) r4.next(), 0, r3, null);
            }
        }
        if (r8 != null) {
            java.util.Iterator r4 = r8.iterator();
            while (r4.hasNext()) {
                findDependents((androidx.constraintlayout.core.widgets.Guideline) r4.next(), 1, r3, null);
            }
        }
        int r6 = 1;
        if (r9 != null) {
            java.util.Iterator r4 = r9.iterator();
            while (r4.hasNext()) {
                androidx.constraintlayout.core.widgets.HelperWidget r5 = (androidx.constraintlayout.core.widgets.HelperWidget) r4.next();
                androidx.constraintlayout.core.widgets.analyzer.WidgetGroup r8 = findDependents(r5, r6, r3, r7);
                r5.addDependents(r3, r6, r8);
                r8.cleanup(r3);
                r6 = 1;
                r7 = null;
            }
        }
        androidx.constraintlayout.core.widgets.ConstraintAnchor r4 = r16.getAnchor(androidx.constraintlayout.core.widgets.ConstraintAnchor.Type.TOP);
        if (r4.getDependents() != null) {
            java.util.Iterator<androidx.constraintlayout.core.widgets.ConstraintAnchor> r4 = r4.getDependents().iterator();
            while (r4.hasNext()) {
                findDependents(r4.next().mOwner, 1, r3, null);
            }
        }
        androidx.constraintlayout.core.widgets.ConstraintAnchor r4 = r16.getAnchor(androidx.constraintlayout.core.widgets.ConstraintAnchor.Type.BASELINE);
        if (r4.getDependents() != null) {
            java.util.Iterator<androidx.constraintlayout.core.widgets.ConstraintAnchor> r4 = r4.getDependents().iterator();
            while (r4.hasNext()) {
                findDependents(r4.next().mOwner, 1, r3, null);
            }
        }
        androidx.constraintlayout.core.widgets.ConstraintAnchor r4 = r16.getAnchor(androidx.constraintlayout.core.widgets.ConstraintAnchor.Type.BOTTOM);
        if (r4.getDependents() != null) {
            java.util.Iterator<androidx.constraintlayout.core.widgets.ConstraintAnchor> r4 = r4.getDependents().iterator();
            while (r4.hasNext()) {
                findDependents(r4.next().mOwner, 1, r3, null);
            }
        }
        androidx.constraintlayout.core.widgets.ConstraintAnchor r4 = r16.getAnchor(androidx.constraintlayout.core.widgets.ConstraintAnchor.Type.CENTER);
        if (r4.getDependents() != null) {
            java.util.Iterator<androidx.constraintlayout.core.widgets.ConstraintAnchor> r4 = r4.getDependents().iterator();
            while (r4.hasNext()) {
                findDependents(r4.next().mOwner, 1, r3, null);
            }
        }
        if (r11 != null) {
            java.util.Iterator r4 = r11.iterator();
            while (r4.hasNext()) {
                findDependents((androidx.constraintlayout.core.widgets.ConstraintWidget) r4.next(), 1, r3, null);
            }
        }
        for (int r4 = 0; r4 < r2; r4++) {
            androidx.constraintlayout.core.widgets.ConstraintWidget r5 = r1.get(r4);
            if (r5.oppositeDimensionsTied()) {
                androidx.constraintlayout.core.widgets.analyzer.WidgetGroup r6 = findGroup(r3, r5.horizontalGroup);
                androidx.constraintlayout.core.widgets.analyzer.WidgetGroup r5 = findGroup(r3, r5.verticalGroup);
                if (r6 != null && r5 != null) {
                    r6.moveTo(0, r5);
                    r5.setOrientation(2);
                    r3.remove(r6);
                }
            }
        }
        if (r3.size() <= 1) {
            return false;
        }
        if (r16.getHorizontalDimensionBehaviour() == androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
            java.util.Iterator<androidx.constraintlayout.core.widgets.analyzer.WidgetGroup> r1 = r3.iterator();
            r2 = null;
            int r6 = 0;
            while (r1.hasNext()) {
                androidx.constraintlayout.core.widgets.analyzer.WidgetGroup r4 = r1.next();
                if (r4.getOrientation() != 1) {
                    r4.setAuthoritative(false);
                    int r7 = r4.measureWrap(r16.getSystem(), 0);
                    if (r7 > r6) {
                        r2 = r4;
                        r6 = r7;
                    }
                }
            }
            if (r2 != null) {
                r16.setHorizontalDimensionBehaviour(androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.FIXED);
                r16.setWidth(r6);
                r2.setAuthoritative(true);
                if (r16.getVerticalDimensionBehaviour() != androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                    java.util.Iterator<androidx.constraintlayout.core.widgets.analyzer.WidgetGroup> r1 = r3.iterator();
                    androidx.constraintlayout.core.widgets.analyzer.WidgetGroup r3 = null;
                    int r6 = 0;
                    while (r1.hasNext()) {
                        androidx.constraintlayout.core.widgets.analyzer.WidgetGroup r4 = r1.next();
                        if (r4.getOrientation() != 0) {
                            r4.setAuthoritative(false);
                            int r7 = r4.measureWrap(r16.getSystem(), 1);
                            if (r7 > r6) {
                                r3 = r4;
                                r6 = r7;
                            }
                        }
                    }
                    r5 = false;
                    r8 = true;
                    if (r3 != null) {
                        r16.setVerticalDimensionBehaviour(androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.FIXED);
                        r16.setHeight(r6);
                        r3.setAuthoritative(true);
                        r4 = r3;
                        return (r2 == null || r4 != null) ? r8 : r5;
                    }
                } else {
                    r5 = false;
                    r8 = true;
                }
                r4 = null;
                if (r2 == null) {
                }
            }
        }
        r2 = null;
        if (r16.getVerticalDimensionBehaviour() != androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
        }
        r4 = null;
        if (r2 == null) {
        }
    }

    private static WidgetGroup findGroup(ArrayList<WidgetGroup> arrayList, int i) {
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            WidgetGroup widgetGroup = arrayList.get(i2);
            if (i == widgetGroup.id) {
                return widgetGroup;
            }
        }
        return null;
    }

    public static WidgetGroup findDependents(ConstraintWidget constraintWidget, int i, ArrayList<WidgetGroup> arrayList, WidgetGroup widgetGroup) {
        int i2;
        int findGroupInDependents;
        if (i == 0) {
            i2 = constraintWidget.horizontalGroup;
        } else {
            i2 = constraintWidget.verticalGroup;
        }
        if (i2 != -1 && (widgetGroup == null || i2 != widgetGroup.id)) {
            int i3 = 0;
            while (true) {
                if (i3 >= arrayList.size()) {
                    break;
                }
                WidgetGroup widgetGroup2 = arrayList.get(i3);
                if (widgetGroup2.getId() == i2) {
                    if (widgetGroup != null) {
                        widgetGroup.moveTo(i, widgetGroup2);
                        arrayList.remove(widgetGroup);
                    }
                    widgetGroup = widgetGroup2;
                } else {
                    i3++;
                }
            }
        } else if (i2 != -1) {
            return widgetGroup;
        }
        if (widgetGroup == null) {
            if ((constraintWidget instanceof HelperWidget) && (findGroupInDependents = ((HelperWidget) constraintWidget).findGroupInDependents(i)) != -1) {
                int i4 = 0;
                while (true) {
                    if (i4 >= arrayList.size()) {
                        break;
                    }
                    WidgetGroup widgetGroup3 = arrayList.get(i4);
                    if (widgetGroup3.getId() == findGroupInDependents) {
                        widgetGroup = widgetGroup3;
                        break;
                    }
                    i4++;
                }
            }
            if (widgetGroup == null) {
                widgetGroup = new WidgetGroup(i);
            }
            arrayList.add(widgetGroup);
        }
        if (widgetGroup.add(constraintWidget)) {
            if (constraintWidget instanceof Guideline) {
                Guideline guideline = (Guideline) constraintWidget;
                guideline.getAnchor().findDependents(guideline.getOrientation() == 0 ? 1 : 0, arrayList, widgetGroup);
            }
            if (i == 0) {
                constraintWidget.horizontalGroup = widgetGroup.getId();
                constraintWidget.mLeft.findDependents(i, arrayList, widgetGroup);
                constraintWidget.mRight.findDependents(i, arrayList, widgetGroup);
            } else {
                constraintWidget.verticalGroup = widgetGroup.getId();
                constraintWidget.mTop.findDependents(i, arrayList, widgetGroup);
                constraintWidget.mBaseline.findDependents(i, arrayList, widgetGroup);
                constraintWidget.mBottom.findDependents(i, arrayList, widgetGroup);
            }
            constraintWidget.mCenter.findDependents(i, arrayList, widgetGroup);
        }
        return widgetGroup;
    }
}
