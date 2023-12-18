package kotlin.internal;

import java.lang.reflect.Method;
import java.util.List;
import java.util.regex.MatchResult;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.FallbackThreadLocalRandom;
import kotlin.random.Random;
import kotlin.text.MatchGroup;
/* compiled from: PlatformImplementations.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\b\u0010\u0018\u00002\u00020\u0001:\u0001\u0012B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0016J\b\u0010\b\u001a\u00020\tH\u0016J\u001a\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0016\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00060\u00112\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¨\u0006\u0013"}, d2 = {"Lkotlin/internal/PlatformImplementations;", "", "()V", "addSuppressed", "", "cause", "", "exception", "defaultPlatformRandom", "Lkotlin/random/Random;", "getMatchResultNamedGroup", "Lkotlin/text/MatchGroup;", "matchResult", "Ljava/util/regex/MatchResult;", "name", "", "getSuppressed", "", "ReflectThrowable", "kotlin-stdlib"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public class PlatformImplementations {

    /* compiled from: PlatformImplementations.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÂ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lkotlin/internal/PlatformImplementations$ReflectThrowable;", "", "()V", "addSuppressed", "Ljava/lang/reflect/Method;", "getSuppressed", "kotlin-stdlib"}, k = 1, mv = {1, 7, 1}, xi = 48)
    /* loaded from: classes2.dex */
    private static final class ReflectThrowable {
        public static final ReflectThrowable INSTANCE = new ReflectThrowable();
        public static final Method addSuppressed;
        public static final Method getSuppressed;

        private ReflectThrowable() {
        }

        /* JADX WARN: Removed duplicated region for block: B:13:0x0041 A[LOOP:0: B:3:0x0015->B:13:0x0041, LOOP_END] */
        /* JADX WARN: Removed duplicated region for block: B:24:0x0045 A[EDGE_INSN: B:24:0x0045->B:15:0x0045 ?: BREAK  , SYNTHETIC] */
        static {
            java.lang.reflect.Method r5;
            java.lang.reflect.Method r6;
            boolean r7;
            java.lang.reflect.Method[] r1 = java.lang.Throwable.class.getMethods();
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, "throwableMethods");
            int r2 = r1.length;
            int r3 = 0;
            int r4 = 0;
            while (true) {
                r5 = null;
                if (r4 >= r2) {
                    r6 = null;
                    break;
                }
                r6 = r1[r4];
                if (kotlin.jvm.internal.Intrinsics.areEqual(r6.getName(), "addSuppressed")) {
                    java.lang.Class<?>[] r7 = r6.getParameterTypes();
                    kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, "it.parameterTypes");
                    if (kotlin.jvm.internal.Intrinsics.areEqual(kotlin.collections.ArraysKt.singleOrNull(r7), java.lang.Throwable.class)) {
                        r7 = true;
                        if (!r7) {
                            break;
                        }
                        r4++;
                    }
                }
                r7 = false;
                if (!r7) {
                }
            }
            kotlin.internal.PlatformImplementations.ReflectThrowable.addSuppressed = r6;
            int r0 = r1.length;
            while (true) {
                if (r3 >= r0) {
                    break;
                }
                java.lang.reflect.Method r2 = r1[r3];
                if (kotlin.jvm.internal.Intrinsics.areEqual(r2.getName(), "getSuppressed")) {
                    r5 = r2;
                    break;
                }
                r3++;
            }
            kotlin.internal.PlatformImplementations.ReflectThrowable.getSuppressed = r5;
        }
    }

    public void addSuppressed(Throwable cause, Throwable exception) {
        Intrinsics.checkNotNullParameter(cause, "cause");
        Intrinsics.checkNotNullParameter(exception, "exception");
        Method method = ReflectThrowable.addSuppressed;
        if (method != null) {
            method.invoke(cause, exception);
        }
    }

    public List<Throwable> getSuppressed(Throwable exception) {
        Object invoke;
        List<Throwable> asList;
        Intrinsics.checkNotNullParameter(exception, "exception");
        Method method = ReflectThrowable.getSuppressed;
        return (method == null || (invoke = method.invoke(exception, new Object[0])) == null || (asList = ArraysKt.asList((Throwable[]) invoke)) == null) ? CollectionsKt.emptyList() : asList;
    }

    public MatchGroup getMatchResultNamedGroup(MatchResult matchResult, String name) {
        Intrinsics.checkNotNullParameter(matchResult, "matchResult");
        Intrinsics.checkNotNullParameter(name, "name");
        throw new UnsupportedOperationException("Retrieving groups by name is not supported on this platform.");
    }

    public Random defaultPlatformRandom() {
        return new FallbackThreadLocalRandom();
    }
}
