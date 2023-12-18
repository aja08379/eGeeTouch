package kotlin.collections;

import kotlin.Metadata;
import kotlin.UByte;
import kotlin.UByteArray;
import kotlin.UIntArray;
import kotlin.ULongArray;
import kotlin.UShort;
import kotlin.UShortArray;
import kotlin.UnsignedKt;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: UArraySorting.kt */
@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0010\u001a*\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0007\u001a*\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\t\u0010\n\u001a*\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\f\u0010\r\u001a*\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u000e2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0010\u001a*\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\u0014\u001a*\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016\u001a*\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018\u001a*\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u000e2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u001a\u001a*\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u0001H\u0001ø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u0014\u001a*\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\b2\u0006\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u0001H\u0001ø\u0001\u0000¢\u0006\u0004\b\u001f\u0010\u0016\u001a*\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u000b2\u0006\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u0001H\u0001ø\u0001\u0000¢\u0006\u0004\b \u0010\u0018\u001a*\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u0001H\u0001ø\u0001\u0000¢\u0006\u0004\b!\u0010\u001a\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\""}, d2 = {"partition", "", "array", "Lkotlin/UByteArray;", "left", "right", "partition-4UcCI2c", "([BII)I", "Lkotlin/UIntArray;", "partition-oBK06Vg", "([III)I", "Lkotlin/ULongArray;", "partition--nroSd4", "([JII)I", "Lkotlin/UShortArray;", "partition-Aa5vz7o", "([SII)I", "quickSort", "", "quickSort-4UcCI2c", "([BII)V", "quickSort-oBK06Vg", "([III)V", "quickSort--nroSd4", "([JII)V", "quickSort-Aa5vz7o", "([SII)V", "sortArray", "fromIndex", "toIndex", "sortArray-4UcCI2c", "sortArray-oBK06Vg", "sortArray--nroSd4", "sortArray-Aa5vz7o", "kotlin-stdlib"}, k = 2, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class UArraySortingKt {
    /* renamed from: partition-4UcCI2c  reason: not valid java name */
    private static final int m475partition4UcCI2c(byte[] bArr, int i, int i2) {
        int i3;
        byte m98getw2LRezQ = UByteArray.m98getw2LRezQ(bArr, (i + i2) / 2);
        while (i <= i2) {
            while (true) {
                int m98getw2LRezQ2 = UByteArray.m98getw2LRezQ(bArr, i) & UByte.MAX_VALUE;
                i3 = m98getw2LRezQ & UByte.MAX_VALUE;
                if (Intrinsics.compare(m98getw2LRezQ2, i3) >= 0) {
                    break;
                }
                i++;
            }
            while (Intrinsics.compare(UByteArray.m98getw2LRezQ(bArr, i2) & UByte.MAX_VALUE, i3) > 0) {
                i2--;
            }
            if (i <= i2) {
                byte m98getw2LRezQ3 = UByteArray.m98getw2LRezQ(bArr, i);
                UByteArray.m103setVurrAj0(bArr, i, UByteArray.m98getw2LRezQ(bArr, i2));
                UByteArray.m103setVurrAj0(bArr, i2, m98getw2LRezQ3);
                i++;
                i2--;
            }
        }
        return i;
    }

    /* renamed from: quickSort-4UcCI2c  reason: not valid java name */
    private static final void m479quickSort4UcCI2c(byte[] bArr, int i, int i2) {
        int m475partition4UcCI2c = m475partition4UcCI2c(bArr, i, i2);
        int i3 = m475partition4UcCI2c - 1;
        if (i < i3) {
            m479quickSort4UcCI2c(bArr, i, i3);
        }
        if (m475partition4UcCI2c < i2) {
            m479quickSort4UcCI2c(bArr, m475partition4UcCI2c, i2);
        }
    }

    /* renamed from: partition-Aa5vz7o  reason: not valid java name */
    private static final int m476partitionAa5vz7o(short[] sArr, int i, int i2) {
        int i3;
        short m358getMh2AYeg = UShortArray.m358getMh2AYeg(sArr, (i + i2) / 2);
        while (i <= i2) {
            while (true) {
                int m358getMh2AYeg2 = UShortArray.m358getMh2AYeg(sArr, i) & UShort.MAX_VALUE;
                i3 = m358getMh2AYeg & UShort.MAX_VALUE;
                if (Intrinsics.compare(m358getMh2AYeg2, i3) >= 0) {
                    break;
                }
                i++;
            }
            while (Intrinsics.compare(UShortArray.m358getMh2AYeg(sArr, i2) & UShort.MAX_VALUE, i3) > 0) {
                i2--;
            }
            if (i <= i2) {
                short m358getMh2AYeg3 = UShortArray.m358getMh2AYeg(sArr, i);
                UShortArray.m363set01HTLdE(sArr, i, UShortArray.m358getMh2AYeg(sArr, i2));
                UShortArray.m363set01HTLdE(sArr, i2, m358getMh2AYeg3);
                i++;
                i2--;
            }
        }
        return i;
    }

    /* renamed from: quickSort-Aa5vz7o  reason: not valid java name */
    private static final void m480quickSortAa5vz7o(short[] sArr, int i, int i2) {
        int m476partitionAa5vz7o = m476partitionAa5vz7o(sArr, i, i2);
        int i3 = m476partitionAa5vz7o - 1;
        if (i < i3) {
            m480quickSortAa5vz7o(sArr, i, i3);
        }
        if (m476partitionAa5vz7o < i2) {
            m480quickSortAa5vz7o(sArr, m476partitionAa5vz7o, i2);
        }
    }

    /* renamed from: partition-oBK06Vg  reason: not valid java name */
    private static final int m477partitionoBK06Vg(int[] iArr, int i, int i2) {
        int m176getpVg5ArA = UIntArray.m176getpVg5ArA(iArr, (i + i2) / 2);
        while (i <= i2) {
            while (UnsignedKt.uintCompare(UIntArray.m176getpVg5ArA(iArr, i), m176getpVg5ArA) < 0) {
                i++;
            }
            while (UnsignedKt.uintCompare(UIntArray.m176getpVg5ArA(iArr, i2), m176getpVg5ArA) > 0) {
                i2--;
            }
            if (i <= i2) {
                int m176getpVg5ArA2 = UIntArray.m176getpVg5ArA(iArr, i);
                UIntArray.m181setVXSXFK8(iArr, i, UIntArray.m176getpVg5ArA(iArr, i2));
                UIntArray.m181setVXSXFK8(iArr, i2, m176getpVg5ArA2);
                i++;
                i2--;
            }
        }
        return i;
    }

    /* renamed from: quickSort-oBK06Vg  reason: not valid java name */
    private static final void m481quickSortoBK06Vg(int[] iArr, int i, int i2) {
        int m477partitionoBK06Vg = m477partitionoBK06Vg(iArr, i, i2);
        int i3 = m477partitionoBK06Vg - 1;
        if (i < i3) {
            m481quickSortoBK06Vg(iArr, i, i3);
        }
        if (m477partitionoBK06Vg < i2) {
            m481quickSortoBK06Vg(iArr, m477partitionoBK06Vg, i2);
        }
    }

    /* renamed from: partition--nroSd4  reason: not valid java name */
    private static final int m474partitionnroSd4(long[] jArr, int i, int i2) {
        long m254getsVKNKU = ULongArray.m254getsVKNKU(jArr, (i + i2) / 2);
        while (i <= i2) {
            while (UnsignedKt.ulongCompare(ULongArray.m254getsVKNKU(jArr, i), m254getsVKNKU) < 0) {
                i++;
            }
            while (UnsignedKt.ulongCompare(ULongArray.m254getsVKNKU(jArr, i2), m254getsVKNKU) > 0) {
                i2--;
            }
            if (i <= i2) {
                long m254getsVKNKU2 = ULongArray.m254getsVKNKU(jArr, i);
                ULongArray.m259setk8EXiF4(jArr, i, ULongArray.m254getsVKNKU(jArr, i2));
                ULongArray.m259setk8EXiF4(jArr, i2, m254getsVKNKU2);
                i++;
                i2--;
            }
        }
        return i;
    }

    /* renamed from: quickSort--nroSd4  reason: not valid java name */
    private static final void m478quickSortnroSd4(long[] jArr, int i, int i2) {
        int m474partitionnroSd4 = m474partitionnroSd4(jArr, i, i2);
        int i3 = m474partitionnroSd4 - 1;
        if (i < i3) {
            m478quickSortnroSd4(jArr, i, i3);
        }
        if (m474partitionnroSd4 < i2) {
            m478quickSortnroSd4(jArr, m474partitionnroSd4, i2);
        }
    }

    /* renamed from: sortArray-4UcCI2c  reason: not valid java name */
    public static final void m483sortArray4UcCI2c(byte[] array, int i, int i2) {
        Intrinsics.checkNotNullParameter(array, "array");
        m479quickSort4UcCI2c(array, i, i2 - 1);
    }

    /* renamed from: sortArray-Aa5vz7o  reason: not valid java name */
    public static final void m484sortArrayAa5vz7o(short[] array, int i, int i2) {
        Intrinsics.checkNotNullParameter(array, "array");
        m480quickSortAa5vz7o(array, i, i2 - 1);
    }

    /* renamed from: sortArray-oBK06Vg  reason: not valid java name */
    public static final void m485sortArrayoBK06Vg(int[] array, int i, int i2) {
        Intrinsics.checkNotNullParameter(array, "array");
        m481quickSortoBK06Vg(array, i, i2 - 1);
    }

    /* renamed from: sortArray--nroSd4  reason: not valid java name */
    public static final void m482sortArraynroSd4(long[] array, int i, int i2) {
        Intrinsics.checkNotNullParameter(array, "array");
        m478quickSortnroSd4(array, i, i2 - 1);
    }
}
