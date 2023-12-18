package androidx.core.util;

import java.util.Objects;
/* loaded from: classes.dex */
public interface Predicate<T> {
    boolean test(T t);

    default Predicate<T> and(final Predicate<? super T> predicate) {
        Objects.requireNonNull(predicate);
        return new Predicate() { // from class: androidx.core.util.-$$Lambda$Predicate$Z_mQR2qf4h-U7w6X77mkLCF8SWU
            @Override // androidx.core.util.Predicate
            public final boolean test(Object obj) {
                return Predicate.lambda$and$0(Predicate.this, predicate, obj);
            }
        };
    }

    static /* synthetic */ boolean lambda$and$0(Predicate _this, Predicate predicate, Object obj) {
        return _this.test(obj) && predicate.test(obj);
    }

    static /* synthetic */ boolean lambda$negate$1(Predicate _this, Object obj) {
        return !_this.test(obj);
    }

    default Predicate<T> negate() {
        return new Predicate() { // from class: androidx.core.util.-$$Lambda$Predicate$W9Q6-JQjW4dIqgo2g8GAADcVFiE
            @Override // androidx.core.util.Predicate
            public final boolean test(Object obj) {
                return Predicate.lambda$negate$1(Predicate.this, obj);
            }
        };
    }

    default Predicate<T> or(final Predicate<? super T> predicate) {
        Objects.requireNonNull(predicate);
        return new Predicate() { // from class: androidx.core.util.-$$Lambda$Predicate$twsP9GttRLcEwZFiG7gzeeDgsmI
            @Override // androidx.core.util.Predicate
            public final boolean test(Object obj) {
                return Predicate.lambda$or$2(Predicate.this, predicate, obj);
            }
        };
    }

    static /* synthetic */ boolean lambda$or$2(Predicate _this, Predicate predicate, Object obj) {
        return _this.test(obj) || predicate.test(obj);
    }

    static <T> Predicate<T> isEqual(final Object obj) {
        if (obj == null) {
            return new Predicate() { // from class: androidx.core.util.-$$Lambda$Predicate$c1xpOSQv97DVgcNkcl2YqANiPCE
                @Override // androidx.core.util.Predicate
                public final boolean test(Object obj2) {
                    boolean isNull;
                    isNull = Objects.isNull(obj2);
                    return isNull;
                }
            };
        }
        return new Predicate() { // from class: androidx.core.util.-$$Lambda$Predicate$-58553dH4VPuMtoknK4XN56zTEI
            @Override // androidx.core.util.Predicate
            public final boolean test(Object obj2) {
                boolean equals;
                equals = obj.equals(obj2);
                return equals;
            }
        };
    }

    static <T> Predicate<T> not(Predicate<? super T> predicate) {
        Objects.requireNonNull(predicate);
        return (Predicate<? super T>) predicate.negate();
    }
}
