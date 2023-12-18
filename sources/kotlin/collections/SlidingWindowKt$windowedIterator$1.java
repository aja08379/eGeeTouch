package kotlin.collections;

import androidx.exifinterface.media.ExifInterface;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.sequences.SequenceScope;
/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: Add missing generic type declarations: [T] */
/* compiled from: SlidingWindow.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00040\u0003H\u008a@"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlin/sequences/SequenceScope;", ""}, k = 3, mv = {1, 7, 1}, xi = 48)
@DebugMetadata(c = "kotlin.collections.SlidingWindowKt$windowedIterator$1", f = "SlidingWindow.kt", i = {0, 0, 0, 2, 2, 3, 3}, l = {34, 40, 49, 55, 58}, m = "invokeSuspend", n = {"$this$iterator", "buffer", "gap", "$this$iterator", "buffer", "$this$iterator", "buffer"}, s = {"L$0", "L$1", "I$0", "L$0", "L$1", "L$0", "L$1"})
/* loaded from: classes2.dex */
public final class SlidingWindowKt$windowedIterator$1<T> extends RestrictedSuspendLambda implements Function2<SequenceScope<? super List<? extends T>>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Iterator<T> $iterator;
    final /* synthetic */ boolean $partialWindows;
    final /* synthetic */ boolean $reuseBuffer;
    final /* synthetic */ int $size;
    final /* synthetic */ int $step;
    int I$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public SlidingWindowKt$windowedIterator$1(int i, int i2, Iterator<? extends T> it, boolean z, boolean z2, Continuation<? super SlidingWindowKt$windowedIterator$1> continuation) {
        super(2, continuation);
        this.$size = i;
        this.$step = i2;
        this.$iterator = it;
        this.$reuseBuffer = z;
        this.$partialWindows = z2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        SlidingWindowKt$windowedIterator$1 slidingWindowKt$windowedIterator$1 = new SlidingWindowKt$windowedIterator$1(this.$size, this.$step, this.$iterator, this.$reuseBuffer, this.$partialWindows, continuation);
        slidingWindowKt$windowedIterator$1.L$0 = obj;
        return slidingWindowKt$windowedIterator$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Continuation<? super Unit> continuation) {
        return invoke((SequenceScope) ((SequenceScope) obj), continuation);
    }

    public final Object invoke(SequenceScope<? super List<? extends T>> sequenceScope, Continuation<? super Unit> continuation) {
        return ((SlidingWindowKt$windowedIterator$1) create(sequenceScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00e4 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00f4  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x013c  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0146  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0170  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:27:0x00a8 -> B:29:0x00ab). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:56:0x012f -> B:58:0x0132). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:69:0x0167 -> B:71:0x016a). Please submit an issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r13) {
        int r8;
        kotlin.collections.RingBuffer r5;
        java.util.Iterator<T> r1;
        kotlin.sequences.SequenceScope r8;
        kotlin.collections.SlidingWindowKt$windowedIterator$1<T> r13;
        int r1;
        kotlin.sequences.SequenceScope r4;
        kotlin.collections.SlidingWindowKt$windowedIterator$1<T> r13;
        java.util.ArrayList r3;
        java.util.Iterator<T> r2;
        kotlin.collections.RingBuffer r1;
        kotlin.sequences.SequenceScope r4;
        java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = r12.label;
        if (r1 == 0) {
            kotlin.ResultKt.throwOnFailure(r13);
            kotlin.sequences.SequenceScope r13 = (kotlin.sequences.SequenceScope) r12.L$0;
            int r1 = kotlin.ranges.RangesKt.coerceAtMost(r12.$size, 1024);
            r8 = r12.$step - r12.$size;
            if (r8 >= 0) {
                java.util.ArrayList r2 = new java.util.ArrayList(r1);
                r1 = 0;
                r4 = r13;
                r13 = r12;
                r3 = r2;
                r2 = r12.$iterator;
                while (r2.hasNext()) {
                }
                if (!r3.isEmpty()) {
                    r13.L$0 = null;
                    r13.L$1 = null;
                    r13.L$2 = null;
                    r13.label = 2;
                    if (r4.yield(r3, r13) == r0) {
                    }
                }
                return kotlin.Unit.INSTANCE;
            }
            r5 = new kotlin.collections.RingBuffer(r1);
            r1 = r12.$iterator;
            r8 = r13;
            r13 = r12;
            while (r1.hasNext()) {
            }
            if (r13.$partialWindows) {
            }
            return kotlin.Unit.INSTANCE;
        } else if (r1 == 1) {
            int r1 = r12.I$0;
            r2 = (java.util.Iterator) r12.L$2;
            r3 = (java.util.ArrayList) r12.L$1;
            r4 = (kotlin.sequences.SequenceScope) r12.L$0;
            kotlin.ResultKt.throwOnFailure(r13);
            r13 = r12;
            r8 = r1;
            if (r13.$reuseBuffer) {
                r3 = new java.util.ArrayList(r13.$size);
            } else {
                r3.clear();
            }
            r1 = r8;
            while (r2.hasNext()) {
                T r9 = r2.next();
                if (r1 > 0) {
                    r1--;
                } else {
                    r3.add(r9);
                    if (r3.size() == r13.$size) {
                        r13.L$0 = r4;
                        r13.L$1 = r3;
                        r13.L$2 = r2;
                        r13.I$0 = r8;
                        r13.label = 1;
                        if (r4.yield(r3, r13) == r0) {
                            return r0;
                        }
                        if (r13.$reuseBuffer) {
                        }
                        r1 = r8;
                        while (r2.hasNext()) {
                        }
                    }
                }
            }
            if ((!r3.isEmpty()) && (r13.$partialWindows || r3.size() == r13.$size)) {
                r13.L$0 = null;
                r13.L$1 = null;
                r13.L$2 = null;
                r13.label = 2;
                if (r4.yield(r3, r13) == r0) {
                    return r0;
                }
            }
            return kotlin.Unit.INSTANCE;
        } else {
            if (r1 != 2) {
                if (r1 == 3) {
                    r1 = (java.util.Iterator) r12.L$2;
                    r5 = (kotlin.collections.RingBuffer) r12.L$1;
                    r8 = (kotlin.sequences.SequenceScope) r12.L$0;
                    kotlin.ResultKt.throwOnFailure(r13);
                    r13 = r12;
                    r5.removeFirst(r13.$step);
                    while (r1.hasNext()) {
                        r5.add((kotlin.collections.RingBuffer) r1.next());
                        if (r5.isFull()) {
                            int r9 = r5.size();
                            int r10 = r13.$size;
                            if (r9 >= r10) {
                                java.util.List r9 = r13.$reuseBuffer ? r5 : new java.util.ArrayList(r5);
                                r13.L$0 = r8;
                                r13.L$1 = r5;
                                r13.L$2 = r1;
                                r13.label = 3;
                                if (r8.yield(r9, r13) == r0) {
                                    return r0;
                                }
                                r5.removeFirst(r13.$step);
                                while (r1.hasNext()) {
                                }
                            } else {
                                r5 = r5.expanded(r10);
                            }
                        }
                    }
                    if (r13.$partialWindows) {
                        r1 = r5;
                        r4 = r8;
                        if (r1.size() <= r13.$step) {
                        }
                    }
                    return kotlin.Unit.INSTANCE;
                } else if (r1 == 4) {
                    r1 = (kotlin.collections.RingBuffer) r12.L$1;
                    r4 = (kotlin.sequences.SequenceScope) r12.L$0;
                    kotlin.ResultKt.throwOnFailure(r13);
                    r13 = r12;
                    r1.removeFirst(r13.$step);
                    if (r1.size() <= r13.$step) {
                        java.util.List r5 = r13.$reuseBuffer ? r1 : new java.util.ArrayList(r1);
                        r13.L$0 = r4;
                        r13.L$1 = r1;
                        r13.L$2 = null;
                        r13.label = 4;
                        if (r4.yield(r5, r13) == r0) {
                            return r0;
                        }
                        r1.removeFirst(r13.$step);
                        if (r1.size() <= r13.$step) {
                            if (!r1.isEmpty()) {
                                r13.L$0 = null;
                                r13.L$1 = null;
                                r13.L$2 = null;
                                r13.label = 5;
                                if (r4.yield(r1, r13) == r0) {
                                    return r0;
                                }
                            }
                            return kotlin.Unit.INSTANCE;
                        }
                    }
                } else if (r1 != 5) {
                    throw new java.lang.IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
            kotlin.ResultKt.throwOnFailure(r13);
            return kotlin.Unit.INSTANCE;
        }
    }
}
