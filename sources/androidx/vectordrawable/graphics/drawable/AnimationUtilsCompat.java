package androidx.vectordrawable.graphics.drawable;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.os.Build;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import androidx.interpolator.view.animation.FastOutLinearInInterpolator;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;
/* loaded from: classes.dex */
public class AnimationUtilsCompat {
    public static Interpolator loadInterpolator(Context context, int i) throws Resources.NotFoundException {
        if (Build.VERSION.SDK_INT >= 21) {
            return AnimationUtils.loadInterpolator(context, i);
        }
        XmlResourceParser xmlResourceParser = null;
        try {
            try {
                if (i == 17563663) {
                    return new FastOutLinearInInterpolator();
                }
                if (i == 17563661) {
                    return new FastOutSlowInInterpolator();
                }
                if (i == 17563662) {
                    return new LinearOutSlowInInterpolator();
                }
                XmlResourceParser animation = context.getResources().getAnimation(i);
                Interpolator createInterpolatorFromXml = createInterpolatorFromXml(context, context.getResources(), context.getTheme(), animation);
                if (animation != null) {
                    animation.close();
                }
                return createInterpolatorFromXml;
            } catch (IOException e) {
                Resources.NotFoundException notFoundException = new Resources.NotFoundException("Can't load animation resource ID #0x" + Integer.toHexString(i));
                notFoundException.initCause(e);
                throw notFoundException;
            } catch (XmlPullParserException e2) {
                Resources.NotFoundException notFoundException2 = new Resources.NotFoundException("Can't load animation resource ID #0x" + Integer.toHexString(i));
                notFoundException2.initCause(e2);
                throw notFoundException2;
            }
        } catch (Throwable th) {
            if (0 != 0) {
                xmlResourceParser.close();
            }
            throw th;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:45:0x00cc, code lost:
        return r4;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static android.view.animation.Interpolator createInterpolatorFromXml(android.content.Context r2, android.content.res.Resources r3, android.content.res.Resources.Theme r4, org.xmlpull.v1.XmlPullParser r5) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        android.view.animation.Interpolator r0;
        int r3 = r5.getDepth();
        android.view.animation.Interpolator r4 = null;
        while (true) {
            int r0 = r5.next();
            if ((r0 != 3 || r5.getDepth() > r3) && r0 != 1) {
                if (r0 == 2) {
                    android.util.AttributeSet r4 = android.util.Xml.asAttributeSet(r5);
                    java.lang.String r0 = r5.getName();
                    if (r0.equals("linearInterpolator")) {
                        r4 = new android.view.animation.LinearInterpolator();
                    } else {
                        if (r0.equals("accelerateInterpolator")) {
                            r0 = new android.view.animation.AccelerateInterpolator(r2, r4);
                        } else if (r0.equals("decelerateInterpolator")) {
                            r0 = new android.view.animation.DecelerateInterpolator(r2, r4);
                        } else if (r0.equals("accelerateDecelerateInterpolator")) {
                            r4 = new android.view.animation.AccelerateDecelerateInterpolator();
                        } else if (r0.equals("cycleInterpolator")) {
                            r0 = new android.view.animation.CycleInterpolator(r2, r4);
                        } else if (r0.equals("anticipateInterpolator")) {
                            r0 = new android.view.animation.AnticipateInterpolator(r2, r4);
                        } else if (r0.equals("overshootInterpolator")) {
                            r0 = new android.view.animation.OvershootInterpolator(r2, r4);
                        } else if (r0.equals("anticipateOvershootInterpolator")) {
                            r0 = new android.view.animation.AnticipateOvershootInterpolator(r2, r4);
                        } else if (r0.equals("bounceInterpolator")) {
                            r4 = new android.view.animation.BounceInterpolator();
                        } else if (r0.equals("pathInterpolator")) {
                            r0 = new androidx.vectordrawable.graphics.drawable.PathInterpolatorCompat(r2, r4, r5);
                        } else {
                            throw new java.lang.RuntimeException("Unknown interpolator name: " + r5.getName());
                        }
                        r4 = r0;
                    }
                }
            }
        }
    }

    private AnimationUtilsCompat() {
    }
}
