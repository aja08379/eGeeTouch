package com.facebook.appevents.codeless;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.codeless.CodelessLoggingEventListener;
import com.facebook.appevents.codeless.RCTCodelessLoggingEventListener;
import com.facebook.appevents.codeless.internal.Constants;
import com.facebook.appevents.codeless.internal.EventBinding;
import com.facebook.appevents.codeless.internal.ParameterComponent;
import com.facebook.appevents.codeless.internal.PathComponent;
import com.facebook.appevents.codeless.internal.ViewHierarchy;
import com.facebook.internal.FetchedAppSettings;
import com.facebook.internal.FetchedAppSettingsManager;
import com.facebook.internal.InternalSettings;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
/* loaded from: classes.dex */
public class CodelessMatcher {
    private static final String CURRENT_CLASS_NAME = ".";
    private static final String PARENT_CLASS_NAME = "..";
    private static final String TAG = "com.facebook.appevents.codeless.CodelessMatcher";
    private final Handler uiThreadHandler = new Handler(Looper.getMainLooper());
    private Set<Activity> activitiesSet = new HashSet();
    private Set<ViewMatcher> viewMatchers = new HashSet();
    private HashMap<String, String> delegateMap = new HashMap<>();

    public void add(Activity activity) {
        if (InternalSettings.isUnityApp()) {
            return;
        }
        if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
            throw new FacebookException("Can't add activity to CodelessMatcher on non-UI thread");
        }
        this.activitiesSet.add(activity);
        this.delegateMap.clear();
        startTracking();
    }

    public void remove(Activity activity) {
        if (InternalSettings.isUnityApp()) {
            return;
        }
        if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
            throw new FacebookException("Can't remove activity from CodelessMatcher on non-UI thread");
        }
        this.activitiesSet.remove(activity);
        this.viewMatchers.clear();
        this.delegateMap.clear();
    }

    public static Bundle getParameters(EventBinding eventBinding, View view, View view2) {
        List<ParameterComponent> viewParameters;
        List<MatchedView> findViewByPath;
        Bundle bundle = new Bundle();
        if (eventBinding != null && (viewParameters = eventBinding.getViewParameters()) != null) {
            for (ParameterComponent parameterComponent : viewParameters) {
                if (parameterComponent.value != null && parameterComponent.value.length() > 0) {
                    bundle.putString(parameterComponent.name, parameterComponent.value);
                } else if (parameterComponent.path.size() > 0) {
                    if (parameterComponent.pathType.equals(Constants.PATH_TYPE_RELATIVE)) {
                        findViewByPath = ViewMatcher.findViewByPath(eventBinding, view2, parameterComponent.path, 0, -1, view2.getClass().getSimpleName());
                    } else {
                        findViewByPath = ViewMatcher.findViewByPath(eventBinding, view, parameterComponent.path, 0, -1, view.getClass().getSimpleName());
                    }
                    Iterator<MatchedView> it = findViewByPath.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            MatchedView next = it.next();
                            if (next.getView() != null) {
                                String textOfView = ViewHierarchy.getTextOfView(next.getView());
                                if (textOfView.length() > 0) {
                                    bundle.putString(parameterComponent.name, textOfView);
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        return bundle;
    }

    private void startTracking() {
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            matchViews();
        } else {
            this.uiThreadHandler.post(new Runnable() { // from class: com.facebook.appevents.codeless.CodelessMatcher.1
                @Override // java.lang.Runnable
                public void run() {
                    CodelessMatcher.this.matchViews();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void matchViews() {
        for (Activity activity : this.activitiesSet) {
            this.viewMatchers.add(new ViewMatcher(activity.getWindow().getDecorView().getRootView(), this.uiThreadHandler, this.delegateMap, activity.getClass().getSimpleName()));
        }
    }

    /* loaded from: classes.dex */
    public static class MatchedView {
        private WeakReference<View> view;
        private String viewMapKey;

        public MatchedView(View view, String str) {
            this.view = new WeakReference<>(view);
            this.viewMapKey = str;
        }

        public View getView() {
            WeakReference<View> weakReference = this.view;
            if (weakReference == null) {
                return null;
            }
            return weakReference.get();
        }

        public String getViewMapKey() {
            return this.viewMapKey;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes.dex */
    public static class ViewMatcher implements ViewTreeObserver.OnGlobalLayoutListener, ViewTreeObserver.OnScrollChangedListener, Runnable {
        private final String activityName;
        private HashMap<String, String> delegateMap;
        private List<EventBinding> eventBindings;
        private final Handler handler;
        private WeakReference<View> rootView;

        public ViewMatcher(View view, Handler handler, HashMap<String, String> hashMap, String str) {
            this.rootView = new WeakReference<>(view);
            this.handler = handler;
            this.delegateMap = hashMap;
            this.activityName = str;
            handler.postDelayed(this, 200L);
        }

        @Override // java.lang.Runnable
        public void run() {
            View view;
            FetchedAppSettings appSettingsWithoutQuery = FetchedAppSettingsManager.getAppSettingsWithoutQuery(FacebookSdk.getApplicationId());
            if (appSettingsWithoutQuery == null || !appSettingsWithoutQuery.getCodelessEventsEnabled()) {
                return;
            }
            List<EventBinding> parseArray = EventBinding.parseArray(appSettingsWithoutQuery.getEventBindings());
            this.eventBindings = parseArray;
            if (parseArray == null || (view = this.rootView.get()) == null) {
                return;
            }
            ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.addOnGlobalLayoutListener(this);
                viewTreeObserver.addOnScrollChangedListener(this);
            }
            startMatch();
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            startMatch();
        }

        @Override // android.view.ViewTreeObserver.OnScrollChangedListener
        public void onScrollChanged() {
            startMatch();
        }

        private void startMatch() {
            if (this.eventBindings == null || this.rootView.get() == null) {
                return;
            }
            for (int i = 0; i < this.eventBindings.size(); i++) {
                findView(this.eventBindings.get(i), this.rootView.get());
            }
        }

        public void findView(EventBinding eventBinding, View view) {
            if (eventBinding == null || view == null) {
                return;
            }
            if (TextUtils.isEmpty(eventBinding.getActivityName()) || eventBinding.getActivityName().equals(this.activityName)) {
                List<PathComponent> viewPath = eventBinding.getViewPath();
                if (viewPath.size() > 25) {
                    return;
                }
                for (MatchedView matchedView : findViewByPath(eventBinding, view, viewPath, 0, -1, this.activityName)) {
                    attachListener(matchedView, view, eventBinding);
                }
            }
        }

        public static List<MatchedView> findViewByPath(EventBinding eventBinding, View view, List<PathComponent> list, int i, int i2, String str) {
            String str2 = str + CodelessMatcher.CURRENT_CLASS_NAME + String.valueOf(i2);
            ArrayList arrayList = new ArrayList();
            if (view == null) {
                return arrayList;
            }
            if (i >= list.size()) {
                arrayList.add(new MatchedView(view, str2));
            } else {
                PathComponent pathComponent = list.get(i);
                if (pathComponent.className.equals(CodelessMatcher.PARENT_CLASS_NAME)) {
                    ViewParent parent = view.getParent();
                    if (parent instanceof ViewGroup) {
                        List<View> findVisibleChildren = findVisibleChildren((ViewGroup) parent);
                        int size = findVisibleChildren.size();
                        for (int i3 = 0; i3 < size; i3++) {
                            arrayList.addAll(findViewByPath(eventBinding, findVisibleChildren.get(i3), list, i + 1, i3, str2));
                        }
                    }
                    return arrayList;
                } else if (pathComponent.className.equals(CodelessMatcher.CURRENT_CLASS_NAME)) {
                    arrayList.add(new MatchedView(view, str2));
                    return arrayList;
                } else if (!isTheSameView(view, pathComponent, i2)) {
                    return arrayList;
                } else {
                    if (i == list.size() - 1) {
                        arrayList.add(new MatchedView(view, str2));
                    }
                }
            }
            if (view instanceof ViewGroup) {
                List<View> findVisibleChildren2 = findVisibleChildren((ViewGroup) view);
                int size2 = findVisibleChildren2.size();
                for (int i4 = 0; i4 < size2; i4++) {
                    arrayList.addAll(findViewByPath(eventBinding, findVisibleChildren2.get(i4), list, i + 1, i4, str2));
                }
            }
            return arrayList;
        }

        /* JADX WARN: Code restructure failed: missing block: B:14:0x0041, code lost:
            if (r4.getClass().getSimpleName().equals(r6[r6.length - 1]) == false) goto L15;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private static boolean isTheSameView(android.view.View r4, com.facebook.appevents.codeless.internal.PathComponent r5, int r6) {
            if (r5.index == -1 || r6 == r5.index) {
                if (!r4.getClass().getCanonicalName().equals(r5.className)) {
                    if (r5.className.matches(".*android\\..*")) {
                        java.lang.String[] r6 = r5.className.split("\\.");
                        if (r6.length > 0) {
                        }
                    }
                    return false;
                }
                if ((r5.matchBitmask & com.facebook.appevents.codeless.internal.PathComponent.MatchBitmaskType.ID.getValue()) <= 0 || r5.id == r4.getId()) {
                    if ((r5.matchBitmask & com.facebook.appevents.codeless.internal.PathComponent.MatchBitmaskType.TEXT.getValue()) <= 0 || r5.text.equals(com.facebook.appevents.codeless.internal.ViewHierarchy.getTextOfView(r4))) {
                        if ((r5.matchBitmask & com.facebook.appevents.codeless.internal.PathComponent.MatchBitmaskType.DESCRIPTION.getValue()) > 0) {
                            if (!r5.description.equals(r4.getContentDescription() == null ? "" : java.lang.String.valueOf(r4.getContentDescription()))) {
                                return false;
                            }
                        }
                        if ((r5.matchBitmask & com.facebook.appevents.codeless.internal.PathComponent.MatchBitmaskType.HINT.getValue()) <= 0 || r5.hint.equals(com.facebook.appevents.codeless.internal.ViewHierarchy.getHintOfView(r4))) {
                            if ((r5.matchBitmask & com.facebook.appevents.codeless.internal.PathComponent.MatchBitmaskType.TAG.getValue()) > 0) {
                                if (!r5.tag.equals(r4.getTag() != null ? java.lang.String.valueOf(r4.getTag()) : "")) {
                                    return false;
                                }
                            }
                            return true;
                        }
                        return false;
                    }
                    return false;
                }
                return false;
            }
            return false;
        }

        private static List<View> findVisibleChildren(ViewGroup viewGroup) {
            ArrayList arrayList = new ArrayList();
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = viewGroup.getChildAt(i);
                if (childAt.getVisibility() == 0) {
                    arrayList.add(childAt);
                }
            }
            return arrayList;
        }

        private void attachListener(MatchedView matchedView, View view, EventBinding eventBinding) {
            if (eventBinding == null) {
                return;
            }
            try {
                View view2 = matchedView.getView();
                if (view2 == null) {
                    return;
                }
                View findRCTRootView = ViewHierarchy.findRCTRootView(view2);
                if (findRCTRootView != null && ViewHierarchy.isRCTButton(view2, findRCTRootView)) {
                    attachRCTListener(matchedView, view, findRCTRootView, eventBinding);
                } else if (view2.getClass().getName().startsWith("com.facebook.react")) {
                } else {
                    String viewMapKey = matchedView.getViewMapKey();
                    View.AccessibilityDelegate existingDelegate = ViewHierarchy.getExistingDelegate(view2);
                    boolean z = true;
                    boolean z2 = existingDelegate != null;
                    boolean z3 = z2 && (existingDelegate instanceof CodelessLoggingEventListener.AutoLoggingAccessibilityDelegate);
                    if (!z3 || !((CodelessLoggingEventListener.AutoLoggingAccessibilityDelegate) existingDelegate).getSupportCodelessLogging()) {
                        z = false;
                    }
                    if (this.delegateMap.containsKey(viewMapKey)) {
                        return;
                    }
                    if (z2 && z3 && z) {
                        return;
                    }
                    view2.setAccessibilityDelegate(CodelessLoggingEventListener.getAccessibilityDelegate(eventBinding, view, view2));
                    this.delegateMap.put(viewMapKey, eventBinding.getEventName());
                }
            } catch (FacebookException e) {
                Log.e(CodelessMatcher.TAG, "Failed to attach auto logging event listener.", e);
            }
        }

        private void attachRCTListener(MatchedView matchedView, View view, View view2, EventBinding eventBinding) {
            View view3;
            if (eventBinding == null || (view3 = matchedView.getView()) == null || !ViewHierarchy.isRCTButton(view3, view2)) {
                return;
            }
            String viewMapKey = matchedView.getViewMapKey();
            View.OnTouchListener existingOnTouchListener = ViewHierarchy.getExistingOnTouchListener(view3);
            boolean z = true;
            boolean z2 = existingOnTouchListener != null;
            boolean z3 = z2 && (existingOnTouchListener instanceof RCTCodelessLoggingEventListener.AutoLoggingOnTouchListener);
            if (!z3 || !((RCTCodelessLoggingEventListener.AutoLoggingOnTouchListener) existingOnTouchListener).getSupportCodelessLogging()) {
                z = false;
            }
            if (this.delegateMap.containsKey(viewMapKey)) {
                return;
            }
            if (z2 && z3 && z) {
                return;
            }
            view3.setOnTouchListener(RCTCodelessLoggingEventListener.getOnTouchListener(eventBinding, view, view3));
            this.delegateMap.put(viewMapKey, eventBinding.getEventName());
        }
    }
}
