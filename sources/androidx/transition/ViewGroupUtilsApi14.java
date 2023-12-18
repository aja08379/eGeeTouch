package androidx.transition;

import android.animation.LayoutTransition;
import android.util.Log;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
/* loaded from: classes.dex */
class ViewGroupUtilsApi14 {
    private static final int LAYOUT_TRANSITION_CHANGING = 4;
    private static final String TAG = "ViewGroupUtilsApi14";
    private static Method sCancelMethod;
    private static boolean sCancelMethodFetched;
    private static LayoutTransition sEmptyLayoutTransition;
    private static Field sLayoutSuppressedField;
    private static boolean sLayoutSuppressedFieldFetched;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:44:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void suppressLayout(android.view.ViewGroup r5, boolean r6) {
        android.animation.LayoutTransition r6;
        boolean r2 = false;
        if (androidx.transition.ViewGroupUtilsApi14.sEmptyLayoutTransition == null) {
            android.animation.LayoutTransition r0 = new android.animation.LayoutTransition() { // from class: androidx.transition.ViewGroupUtilsApi14.1
                @Override // android.animation.LayoutTransition
                public boolean isChangingLayout() {
                    return true;
                }

                {
                }
            };
            androidx.transition.ViewGroupUtilsApi14.sEmptyLayoutTransition = r0;
            r0.setAnimator(2, null);
            androidx.transition.ViewGroupUtilsApi14.sEmptyLayoutTransition.setAnimator(0, null);
            androidx.transition.ViewGroupUtilsApi14.sEmptyLayoutTransition.setAnimator(1, null);
            androidx.transition.ViewGroupUtilsApi14.sEmptyLayoutTransition.setAnimator(3, null);
            androidx.transition.ViewGroupUtilsApi14.sEmptyLayoutTransition.setAnimator(4, null);
        }
        if (r6) {
            android.animation.LayoutTransition r6 = r5.getLayoutTransition();
            if (r6 != null) {
                if (r6.isRunning()) {
                    cancelLayoutTransition(r6);
                }
                if (r6 != androidx.transition.ViewGroupUtilsApi14.sEmptyLayoutTransition) {
                    r5.setTag(androidx.transition.R.id.transition_layout_save, r6);
                }
            }
            r5.setLayoutTransition(androidx.transition.ViewGroupUtilsApi14.sEmptyLayoutTransition);
            return;
        }
        r5.setLayoutTransition(null);
        if (!androidx.transition.ViewGroupUtilsApi14.sLayoutSuppressedFieldFetched) {
            try {
                java.lang.reflect.Field r6 = android.view.ViewGroup.class.getDeclaredField("mLayoutSuppressed");
                androidx.transition.ViewGroupUtilsApi14.sLayoutSuppressedField = r6;
                r6.setAccessible(true);
            } catch (java.lang.NoSuchFieldException unused) {
                android.util.Log.i(androidx.transition.ViewGroupUtilsApi14.TAG, "Failed to access mLayoutSuppressed field by reflection");
            }
            androidx.transition.ViewGroupUtilsApi14.sLayoutSuppressedFieldFetched = true;
        }
        java.lang.reflect.Field r6 = androidx.transition.ViewGroupUtilsApi14.sLayoutSuppressedField;
        if (r6 != null) {
            try {
                boolean r6 = r6.getBoolean(r5);
                if (r6) {
                    try {
                        androidx.transition.ViewGroupUtilsApi14.sLayoutSuppressedField.setBoolean(r5, false);
                    } catch (java.lang.IllegalAccessException unused) {
                        r2 = r6;
                        android.util.Log.i(androidx.transition.ViewGroupUtilsApi14.TAG, "Failed to get mLayoutSuppressed field by reflection");
                        if (r2) {
                        }
                        r6 = (android.animation.LayoutTransition) r5.getTag(androidx.transition.R.id.transition_layout_save);
                        if (r6 == null) {
                        }
                    }
                }
                r2 = r6;
            } catch (java.lang.IllegalAccessException unused) {
            }
        }
        if (r2) {
            r5.requestLayout();
        }
        r6 = (android.animation.LayoutTransition) r5.getTag(androidx.transition.R.id.transition_layout_save);
        if (r6 == null) {
            r5.setTag(androidx.transition.R.id.transition_layout_save, null);
            r5.setLayoutTransition(r6);
        }
    }

    private static void cancelLayoutTransition(LayoutTransition layoutTransition) {
        if (!sCancelMethodFetched) {
            try {
                Method declaredMethod = LayoutTransition.class.getDeclaredMethod("cancel", new Class[0]);
                sCancelMethod = declaredMethod;
                declaredMethod.setAccessible(true);
            } catch (NoSuchMethodException unused) {
                Log.i(TAG, "Failed to access cancel method by reflection");
            }
            sCancelMethodFetched = true;
        }
        Method method = sCancelMethod;
        if (method != null) {
            try {
                method.invoke(layoutTransition, new Object[0]);
            } catch (IllegalAccessException unused2) {
                Log.i(TAG, "Failed to access cancel method by reflection");
            } catch (InvocationTargetException unused3) {
                Log.i(TAG, "Failed to invoke cancel method by reflection");
            }
        }
    }

    private ViewGroupUtilsApi14() {
    }
}
