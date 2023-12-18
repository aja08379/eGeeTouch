package androidx.transition;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.AttributeSet;
import android.util.Xml;
import android.view.InflateException;
import android.view.ViewGroup;
import androidx.collection.ArrayMap;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.content.res.TypedArrayUtils;
import java.io.IOException;
import java.lang.reflect.Constructor;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
/* loaded from: classes.dex */
public class TransitionInflater {
    private final Context mContext;
    private static final Class<?>[] CONSTRUCTOR_SIGNATURE = {Context.class, AttributeSet.class};
    private static final ArrayMap<String, Constructor<?>> CONSTRUCTORS = new ArrayMap<>();

    private TransitionInflater(Context context) {
        this.mContext = context;
    }

    public static TransitionInflater from(Context context) {
        return new TransitionInflater(context);
    }

    public Transition inflateTransition(int i) {
        XmlResourceParser xml = this.mContext.getResources().getXml(i);
        try {
            try {
                return createTransitionFromXml(xml, Xml.asAttributeSet(xml), null);
            } catch (IOException e) {
                throw new InflateException(xml.getPositionDescription() + ": " + e.getMessage(), e);
            } catch (XmlPullParserException e2) {
                throw new InflateException(e2.getMessage(), e2);
            }
        } finally {
            xml.close();
        }
    }

    public TransitionManager inflateTransitionManager(int i, ViewGroup viewGroup) {
        XmlResourceParser xml = this.mContext.getResources().getXml(i);
        try {
            try {
                return createTransitionManagerFromXml(xml, Xml.asAttributeSet(xml), viewGroup);
            } catch (IOException e) {
                InflateException inflateException = new InflateException(xml.getPositionDescription() + ": " + e.getMessage());
                inflateException.initCause(e);
                throw inflateException;
            } catch (XmlPullParserException e2) {
                InflateException inflateException2 = new InflateException(e2.getMessage());
                inflateException2.initCause(e2);
                throw inflateException2;
            }
        } finally {
            xml.close();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:82:0x017f, code lost:
        return r3;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private androidx.transition.Transition createTransitionFromXml(org.xmlpull.v1.XmlPullParser r8, android.util.AttributeSet r9, androidx.transition.Transition r10) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        int r0 = r8.getDepth();
        androidx.transition.TransitionSet r1 = r10 instanceof androidx.transition.TransitionSet ? (androidx.transition.TransitionSet) r10 : null;
        loop0: while (true) {
            androidx.transition.Transition r3 = null;
            while (true) {
                int r4 = r8.next();
                if ((r4 != 3 || r8.getDepth() > r0) && r4 != 1) {
                    if (r4 == 2) {
                        java.lang.String r4 = r8.getName();
                        if ("fade".equals(r4)) {
                            r3 = new androidx.transition.Fade(r7.mContext, r9);
                        } else if ("changeBounds".equals(r4)) {
                            r3 = new androidx.transition.ChangeBounds(r7.mContext, r9);
                        } else if ("slide".equals(r4)) {
                            r3 = new androidx.transition.Slide(r7.mContext, r9);
                        } else if ("explode".equals(r4)) {
                            r3 = new androidx.transition.Explode(r7.mContext, r9);
                        } else if ("changeImageTransform".equals(r4)) {
                            r3 = new androidx.transition.ChangeImageTransform(r7.mContext, r9);
                        } else if ("changeTransform".equals(r4)) {
                            r3 = new androidx.transition.ChangeTransform(r7.mContext, r9);
                        } else if ("changeClipBounds".equals(r4)) {
                            r3 = new androidx.transition.ChangeClipBounds(r7.mContext, r9);
                        } else if (androidx.constraintlayout.core.motion.utils.TypedValues.TransitionType.S_AUTO_TRANSITION.equals(r4)) {
                            r3 = new androidx.transition.AutoTransition(r7.mContext, r9);
                        } else if ("changeScroll".equals(r4)) {
                            r3 = new androidx.transition.ChangeScroll(r7.mContext, r9);
                        } else if ("transitionSet".equals(r4)) {
                            r3 = new androidx.transition.TransitionSet(r7.mContext, r9);
                        } else if ("transition".equals(r4)) {
                            r3 = (androidx.transition.Transition) createCustom(r9, androidx.transition.Transition.class, "transition");
                        } else if ("targets".equals(r4)) {
                            getTargetIds(r8, r9, r10);
                        } else if ("arcMotion".equals(r4)) {
                            if (r10 == null) {
                                throw new java.lang.RuntimeException("Invalid use of arcMotion element");
                            }
                            r10.setPathMotion(new androidx.transition.ArcMotion(r7.mContext, r9));
                        } else if ("pathMotion".equals(r4)) {
                            if (r10 == null) {
                                throw new java.lang.RuntimeException("Invalid use of pathMotion element");
                            }
                            r10.setPathMotion((androidx.transition.PathMotion) createCustom(r9, androidx.transition.PathMotion.class, "pathMotion"));
                        } else if (!"patternPathMotion".equals(r4)) {
                            throw new java.lang.RuntimeException("Unknown scene name: " + r8.getName());
                        } else {
                            if (r10 == null) {
                                throw new java.lang.RuntimeException("Invalid use of patternPathMotion element");
                            }
                            r10.setPathMotion(new androidx.transition.PatternPathMotion(r7.mContext, r9));
                        }
                        if (r3 == null) {
                            continue;
                        } else {
                            if (!r8.isEmptyElementTag()) {
                                createTransitionFromXml(r8, r9, r3);
                            }
                            if (r1 != null) {
                                break;
                            } else if (r10 != null) {
                                throw new android.view.InflateException("Could not add transition to another transition.");
                            }
                        }
                    }
                }
            }
            r1.addTransition(r3);
        }
    }

    private Object createCustom(AttributeSet attributeSet, Class<?> cls, String str) {
        Object newInstance;
        Class<? extends U> asSubclass;
        String attributeValue = attributeSet.getAttributeValue(null, "class");
        if (attributeValue == null) {
            throw new InflateException(str + " tag must have a 'class' attribute");
        }
        try {
            ArrayMap<String, Constructor<?>> arrayMap = CONSTRUCTORS;
            synchronized (arrayMap) {
                Constructor<?> constructor = arrayMap.get(attributeValue);
                if (constructor == null && (asSubclass = Class.forName(attributeValue, false, this.mContext.getClassLoader()).asSubclass(cls)) != 0) {
                    constructor = asSubclass.getConstructor(CONSTRUCTOR_SIGNATURE);
                    constructor.setAccessible(true);
                    arrayMap.put(attributeValue, constructor);
                }
                newInstance = constructor.newInstance(this.mContext, attributeSet);
            }
            return newInstance;
        } catch (Exception e) {
            throw new InflateException("Could not instantiate " + cls + " class " + attributeValue, e);
        }
    }

    private void getTargetIds(XmlPullParser xmlPullParser, AttributeSet attributeSet, Transition transition) throws XmlPullParserException, IOException {
        int depth = xmlPullParser.getDepth();
        while (true) {
            int next = xmlPullParser.next();
            if ((next == 3 && xmlPullParser.getDepth() <= depth) || next == 1) {
                return;
            }
            if (next == 2) {
                if (xmlPullParser.getName().equals(TypedValues.AttributesType.S_TARGET)) {
                    TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(attributeSet, Styleable.TRANSITION_TARGET);
                    int namedResourceId = TypedArrayUtils.getNamedResourceId(obtainStyledAttributes, xmlPullParser, "targetId", 1, 0);
                    if (namedResourceId != 0) {
                        transition.addTarget(namedResourceId);
                    } else {
                        int namedResourceId2 = TypedArrayUtils.getNamedResourceId(obtainStyledAttributes, xmlPullParser, "excludeId", 2, 0);
                        if (namedResourceId2 != 0) {
                            transition.excludeTarget(namedResourceId2, true);
                        } else {
                            String namedString = TypedArrayUtils.getNamedString(obtainStyledAttributes, xmlPullParser, "targetName", 4);
                            if (namedString != null) {
                                transition.addTarget(namedString);
                            } else {
                                String namedString2 = TypedArrayUtils.getNamedString(obtainStyledAttributes, xmlPullParser, "excludeName", 5);
                                if (namedString2 != null) {
                                    transition.excludeTarget(namedString2, true);
                                } else {
                                    String namedString3 = TypedArrayUtils.getNamedString(obtainStyledAttributes, xmlPullParser, "excludeClass", 3);
                                    if (namedString3 != null) {
                                        try {
                                            transition.excludeTarget(Class.forName(namedString3), true);
                                        } catch (ClassNotFoundException e) {
                                            obtainStyledAttributes.recycle();
                                            throw new RuntimeException("Could not create " + namedString3, e);
                                        }
                                    } else {
                                        String namedString4 = TypedArrayUtils.getNamedString(obtainStyledAttributes, xmlPullParser, "targetClass", 0);
                                        if (namedString4 != null) {
                                            transition.addTarget(Class.forName(namedString4));
                                        }
                                    }
                                }
                            }
                        }
                    }
                    obtainStyledAttributes.recycle();
                } else {
                    throw new RuntimeException("Unknown scene name: " + xmlPullParser.getName());
                }
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x0056, code lost:
        return r1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private androidx.transition.TransitionManager createTransitionManagerFromXml(org.xmlpull.v1.XmlPullParser r5, android.util.AttributeSet r6, android.view.ViewGroup r7) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        int r0 = r5.getDepth();
        androidx.transition.TransitionManager r1 = null;
        while (true) {
            int r2 = r5.next();
            if ((r2 != 3 || r5.getDepth() > r0) && r2 != 1) {
                if (r2 == 2) {
                    java.lang.String r2 = r5.getName();
                    if (r2.equals("transitionManager")) {
                        r1 = new androidx.transition.TransitionManager();
                    } else if (!r2.equals("transition") || r1 == null) {
                        break;
                    } else {
                        loadTransition(r6, r5, r7, r1);
                    }
                }
            }
        }
        throw new java.lang.RuntimeException("Unknown scene name: " + r5.getName());
    }

    private void loadTransition(AttributeSet attributeSet, XmlPullParser xmlPullParser, ViewGroup viewGroup, TransitionManager transitionManager) throws Resources.NotFoundException {
        Transition inflateTransition;
        TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(attributeSet, Styleable.TRANSITION_MANAGER);
        int namedResourceId = TypedArrayUtils.getNamedResourceId(obtainStyledAttributes, xmlPullParser, "transition", 2, -1);
        int namedResourceId2 = TypedArrayUtils.getNamedResourceId(obtainStyledAttributes, xmlPullParser, "fromScene", 0, -1);
        Scene sceneForLayout = namedResourceId2 < 0 ? null : Scene.getSceneForLayout(viewGroup, namedResourceId2, this.mContext);
        int namedResourceId3 = TypedArrayUtils.getNamedResourceId(obtainStyledAttributes, xmlPullParser, "toScene", 1, -1);
        Scene sceneForLayout2 = namedResourceId3 >= 0 ? Scene.getSceneForLayout(viewGroup, namedResourceId3, this.mContext) : null;
        if (namedResourceId >= 0 && (inflateTransition = inflateTransition(namedResourceId)) != null) {
            if (sceneForLayout2 == null) {
                throw new RuntimeException("No toScene for transition ID " + namedResourceId);
            }
            if (sceneForLayout == null) {
                transitionManager.setTransition(sceneForLayout2, inflateTransition);
            } else {
                transitionManager.setTransition(sceneForLayout, sceneForLayout2, inflateTransition);
            }
        }
        obtainStyledAttributes.recycle();
    }
}
