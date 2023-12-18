package kotlin.text;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.sequences.SequenceScope;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Regex.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlin/sequences/SequenceScope;", ""}, k = 3, mv = {1, 7, 1}, xi = 48)
@DebugMetadata(c = "kotlin.text.Regex$splitToSequence$1", f = "Regex.kt", i = {1, 1, 1}, l = {276, 284, 288}, m = "invokeSuspend", n = {"$this$sequence", "matcher", "splitCount"}, s = {"L$0", "L$1", "I$0"})
/* loaded from: classes2.dex */
public final class Regex$splitToSequence$1 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super String>, Continuation<? super Unit>, Object> {
    final /* synthetic */ CharSequence $input;
    final /* synthetic */ int $limit;
    int I$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ Regex this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Regex$splitToSequence$1(Regex regex, CharSequence charSequence, int i, Continuation<? super Regex$splitToSequence$1> continuation) {
        super(2, continuation);
        this.this$0 = regex;
        this.$input = charSequence;
        this.$limit = i;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        Regex$splitToSequence$1 regex$splitToSequence$1 = new Regex$splitToSequence$1(this.this$0, this.$input, this.$limit, continuation);
        regex$splitToSequence$1.L$0 = obj;
        return regex$splitToSequence$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(SequenceScope<? super String> sequenceScope, Continuation<? super Unit> continuation) {
        return ((Regex$splitToSequence$1) create(sequenceScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0075 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00a4 A[RETURN] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:19:0x0073 -> B:21:0x0076). Please submit an issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r11) {
        java.util.regex.Pattern r1;
        java.util.regex.Matcher r1;
        kotlin.text.Regex$splitToSequence$1 r7;
        kotlin.sequences.SequenceScope r6;
        int r11;
        java.lang.CharSequence r11;
        java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = r10.label;
        int r2 = 0;
        if (r1 == 0) {
            kotlin.ResultKt.throwOnFailure(r11);
            kotlin.sequences.SequenceScope r11 = (kotlin.sequences.SequenceScope) r10.L$0;
            r1 = r10.this$0.nativePattern;
            r1 = r1.matcher(r10.$input);
            if (r10.$limit == 1 || !r1.find()) {
                r10.label = 1;
                if (r11.yield(r10.$input.toString(), r10) == r0) {
                    return r0;
                }
            } else {
                r7 = r10;
                r6 = r11;
                r11 = 0;
                r7.L$0 = r6;
                r7.L$1 = r1;
                r7.I$0 = r11;
                r7.label = 2;
                if (r6.yield(r7.$input.subSequence(r2, r1.start()).toString(), r7) == r0) {
                }
                r2 = r1.end();
                r11++;
                if (r11 != r7.$limit - 1) {
                }
                r11 = r7.$input;
                r7.L$0 = null;
                r7.L$1 = null;
                r7.label = 3;
                if (r6.yield(r11.subSequence(r2, r11.length()).toString(), r7) == r0) {
                }
                return kotlin.Unit.INSTANCE;
            }
        } else if (r1 != 1) {
            if (r1 != 2) {
                if (r1 == 3) {
                    kotlin.ResultKt.throwOnFailure(r11);
                    return kotlin.Unit.INSTANCE;
                }
                throw new java.lang.IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            int r1 = r10.I$0;
            r6 = (kotlin.sequences.SequenceScope) r10.L$0;
            kotlin.ResultKt.throwOnFailure(r11);
            r7 = r10;
            r11 = r1;
            r1 = (java.util.regex.Matcher) r10.L$1;
            r2 = r1.end();
            r11++;
            if (r11 != r7.$limit - 1 || !r1.find()) {
                r11 = r7.$input;
                r7.L$0 = null;
                r7.L$1 = null;
                r7.label = 3;
                if (r6.yield(r11.subSequence(r2, r11.length()).toString(), r7) == r0) {
                    return r0;
                }
                return kotlin.Unit.INSTANCE;
            }
            r7.L$0 = r6;
            r7.L$1 = r1;
            r7.I$0 = r11;
            r7.label = 2;
            if (r6.yield(r7.$input.subSequence(r2, r1.start()).toString(), r7) == r0) {
                return r0;
            }
            r2 = r1.end();
            r11++;
            if (r11 != r7.$limit - 1) {
            }
            r11 = r7.$input;
            r7.L$0 = null;
            r7.L$1 = null;
            r7.label = 3;
            if (r6.yield(r11.subSequence(r2, r11.length()).toString(), r7) == r0) {
            }
            return kotlin.Unit.INSTANCE;
        } else {
            kotlin.ResultKt.throwOnFailure(r11);
        }
        return kotlin.Unit.INSTANCE;
    }
}
