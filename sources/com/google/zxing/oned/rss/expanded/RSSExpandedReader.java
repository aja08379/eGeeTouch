package com.google.zxing.oned.rss.expanded;

import com.egeetouch.egeetouch_manager.BLEService;
import com.egeetouch.egeetouch_manager.BuildConfig;
import com.egeetouch.egeetouch_manager.TaskManagement;
import com.facebook.internal.FacebookRequestErrorClassification;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitArray;
import com.google.zxing.common.detector.MathUtils;
import com.google.zxing.oned.rss.AbstractRSSReader;
import com.google.zxing.oned.rss.DataCharacter;
import com.google.zxing.oned.rss.FinderPattern;
import com.google.zxing.oned.rss.RSSUtils;
import com.google.zxing.oned.rss.expanded.decoders.AbstractExpandedDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
/* loaded from: classes2.dex */
public final class RSSExpandedReader extends AbstractRSSReader {
    private static final int FINDER_PAT_A = 0;
    private static final int FINDER_PAT_B = 1;
    private static final int FINDER_PAT_C = 2;
    private static final int FINDER_PAT_D = 3;
    private static final int FINDER_PAT_E = 4;
    private static final int FINDER_PAT_F = 5;
    private static final int MAX_PAIRS = 11;
    private final List<ExpandedPair> pairs = new ArrayList(11);
    private final List<ExpandedRow> rows = new ArrayList();
    private final int[] startEnd = new int[2];
    private boolean startFromEven;
    private static final int[] SYMBOL_WIDEST = {7, 5, 4, 3, 1};
    private static final int[] EVEN_TOTAL_SUBSET = {4, 20, 52, 104, 204};
    private static final int[] GSUM = {0, 348, 1388, 2948, 3988};
    private static final int[][] FINDER_PATTERNS = {new int[]{1, 8, 4, 1}, new int[]{3, 6, 4, 1}, new int[]{3, 4, 6, 1}, new int[]{3, 2, 8, 1}, new int[]{2, 6, 5, 1}, new int[]{2, 2, 9, 1}};
    private static final int[][] WEIGHTS = {new int[]{1, 3, 9, 27, 81, 32, 96, 77}, new int[]{20, 60, 180, 118, 143, 7, 21, 63}, new int[]{189, BuildConfig.VERSION_CODE, 13, 39, 117, 140, 209, 205}, new int[]{193, 157, 49, 147, 19, 57, BLEService.Read_response_Lock_Setting_Param, 91}, new int[]{62, 186, TaskManagement.restore_user4, 197, 169, 85, 44, TaskManagement.log_extract20}, new int[]{185, TaskManagement.restore_user1, 188, es.dmoral.toasty.BuildConfig.VERSION_CODE, 4, 12, 36, 108}, new int[]{113, 128, BLEService.Request_ChangePasscode, 97, 80, 29, 87, 50}, new int[]{150, 28, 84, 41, 123, BLEService.Request_tag_AuditTrail_number, 52, 156}, new int[]{46, 138, 203, BLEService.disconnect, 139, 206, 196, BLEService.Read_Response_enter_low_power_mode}, new int[]{76, 17, 51, BLEService.AutoLocking_Settings, 37, 111, 122, 155}, new int[]{43, TaskManagement.restore_tag2, BLEService.Verify_Admin_Password_new_lock, 106, 107, 110, 119, 146}, new int[]{16, 48, BLEService.Request_old_passcode, 10, 30, 90, 59, 177}, new int[]{109, 116, 137, 200, BLEService.Read_response_Admin_Password_new_lock, 112, 125, 164}, new int[]{70, 210, 208, 202, 184, TaskManagement.restore_admin, 179, 115}, new int[]{TaskManagement.restore_user2, 191, 151, 31, 93, 68, 204, FacebookRequestErrorClassification.EC_INVALID_TOKEN}, new int[]{148, 22, 66, 198, 172, 94, 71, 2}, new int[]{6, 18, 54, 162, 64, 192, 154, 40}, new int[]{120, 149, 25, 75, 14, 42, 126, BLEService.Read_Response_quit_low_power_mode}, new int[]{79, 26, 78, 23, 69, 207, 199, 175}, new int[]{103, 98, 83, 38, 114, TaskManagement.log_extract10, 182, 124}, new int[]{BLEService.check_model, 61, 183, 127, 170, 88, 53, BLEService.Send_timeStamp}, new int[]{55, BLEService.Read_response_update_reconnecting_period, 73, 8, 24, 72, 5, 15}, new int[]{45, TaskManagement.restore_user3, 194, BLEService.check_version, 58, 174, 100, 89}};
    private static final int[][] FINDER_PATTERN_SEQUENCES = {new int[]{0, 0}, new int[]{0, 1, 1}, new int[]{0, 2, 1, 3}, new int[]{0, 4, 1, 3, 2}, new int[]{0, 4, 1, 3, 3, 5}, new int[]{0, 4, 1, 3, 4, 5, 5}, new int[]{0, 0, 1, 1, 2, 2, 3, 3}, new int[]{0, 0, 1, 1, 2, 2, 3, 4, 4}, new int[]{0, 0, 1, 1, 2, 2, 3, 4, 5, 5}, new int[]{0, 0, 1, 1, 2, 3, 3, 4, 4, 5, 5}};

    @Override // com.google.zxing.oned.OneDReader
    public Result decodeRow(int i, BitArray bitArray, Map<DecodeHintType, ?> map) throws NotFoundException, FormatException {
        this.pairs.clear();
        this.startFromEven = false;
        try {
            return constructResult(decodeRow2pairs(i, bitArray));
        } catch (NotFoundException unused) {
            this.pairs.clear();
            this.startFromEven = true;
            return constructResult(decodeRow2pairs(i, bitArray));
        }
    }

    @Override // com.google.zxing.oned.OneDReader, com.google.zxing.Reader
    public void reset() {
        this.pairs.clear();
        this.rows.clear();
    }

    List<ExpandedPair> decodeRow2pairs(int i, BitArray bitArray) throws NotFoundException {
        while (true) {
            try {
                this.pairs.add(retrieveNextPair(bitArray, this.pairs, i));
            } catch (NotFoundException e) {
                if (this.pairs.isEmpty()) {
                    throw e;
                }
                if (checkChecksum()) {
                    return this.pairs;
                }
                boolean z = !this.rows.isEmpty();
                storeRow(i, false);
                if (z) {
                    List<ExpandedPair> checkRows = checkRows(false);
                    if (checkRows != null) {
                        return checkRows;
                    }
                    List<ExpandedPair> checkRows2 = checkRows(true);
                    if (checkRows2 != null) {
                        return checkRows2;
                    }
                }
                throw NotFoundException.getNotFoundInstance();
            }
        }
    }

    private List<ExpandedPair> checkRows(boolean z) {
        List<ExpandedPair> list = null;
        if (this.rows.size() > 25) {
            this.rows.clear();
            return null;
        }
        this.pairs.clear();
        if (z) {
            Collections.reverse(this.rows);
        }
        try {
            list = checkRows(new ArrayList(), 0);
        } catch (NotFoundException unused) {
        }
        if (z) {
            Collections.reverse(this.rows);
        }
        return list;
    }

    private List<ExpandedPair> checkRows(List<ExpandedRow> list, int i) throws NotFoundException {
        while (i < this.rows.size()) {
            ExpandedRow expandedRow = this.rows.get(i);
            this.pairs.clear();
            for (ExpandedRow expandedRow2 : list) {
                this.pairs.addAll(expandedRow2.getPairs());
            }
            this.pairs.addAll(expandedRow.getPairs());
            if (isValidSequence(this.pairs)) {
                if (checkChecksum()) {
                    return this.pairs;
                }
                ArrayList arrayList = new ArrayList();
                arrayList.addAll(list);
                arrayList.add(expandedRow);
                try {
                    return checkRows(arrayList, i + 1);
                } catch (NotFoundException unused) {
                    continue;
                }
            }
            i++;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static boolean isValidSequence(List<ExpandedPair> list) {
        int[][] iArr;
        boolean z;
        for (int[] iArr2 : FINDER_PATTERN_SEQUENCES) {
            if (list.size() <= iArr2.length) {
                int i = 0;
                while (true) {
                    if (i >= list.size()) {
                        z = true;
                        break;
                    } else if (list.get(i).getFinderPattern().getValue() != iArr2[i]) {
                        z = false;
                        break;
                    } else {
                        i++;
                    }
                }
                if (z) {
                    return true;
                }
            }
        }
        return false;
    }

    private void storeRow(int i, boolean z) {
        boolean z2 = false;
        int i2 = 0;
        boolean z3 = false;
        while (true) {
            if (i2 >= this.rows.size()) {
                break;
            }
            ExpandedRow expandedRow = this.rows.get(i2);
            if (expandedRow.getRowNumber() > i) {
                z2 = expandedRow.isEquivalent(this.pairs);
                break;
            } else {
                z3 = expandedRow.isEquivalent(this.pairs);
                i2++;
            }
        }
        if (z2 || z3 || isPartialRow(this.pairs, this.rows)) {
            return;
        }
        this.rows.add(i2, new ExpandedRow(this.pairs, i, z));
        removePartialRows(this.pairs, this.rows);
    }

    /* JADX WARN: Code restructure failed: missing block: B:33:0x004c, code lost:
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static void removePartialRows(java.util.List<com.google.zxing.oned.rss.expanded.ExpandedPair> r6, java.util.List<com.google.zxing.oned.rss.expanded.ExpandedRow> r7) {
        boolean r2;
        java.util.Iterator<com.google.zxing.oned.rss.expanded.ExpandedRow> r7 = r7.iterator();
        while (r7.hasNext()) {
            com.google.zxing.oned.rss.expanded.ExpandedRow r0 = r7.next();
            if (r0.getPairs().size() != r6.size()) {
                java.util.Iterator<com.google.zxing.oned.rss.expanded.ExpandedPair> r0 = r0.getPairs().iterator();
                while (true) {
                    r2 = false;
                    boolean r3 = true;
                    if (!r0.hasNext()) {
                        r2 = true;
                        break;
                    }
                    com.google.zxing.oned.rss.expanded.ExpandedPair r1 = r0.next();
                    java.util.Iterator<com.google.zxing.oned.rss.expanded.ExpandedPair> r4 = r6.iterator();
                    while (true) {
                        if (!r4.hasNext()) {
                            r3 = false;
                            continue;
                            break;
                        } else if (r1.equals(r4.next())) {
                            break;
                        }
                    }
                    if (!r3) {
                        break;
                    }
                }
                if (r2) {
                    r7.remove();
                }
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x0043, code lost:
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static boolean isPartialRow(java.lang.Iterable<com.google.zxing.oned.rss.expanded.ExpandedPair> r7, java.lang.Iterable<com.google.zxing.oned.rss.expanded.ExpandedRow> r8) {
        boolean r1;
        boolean r3;
        java.util.Iterator<com.google.zxing.oned.rss.expanded.ExpandedRow> r8 = r8.iterator();
        do {
            r1 = false;
            if (!r8.hasNext()) {
                return false;
            }
            com.google.zxing.oned.rss.expanded.ExpandedRow r0 = r8.next();
            java.util.Iterator<com.google.zxing.oned.rss.expanded.ExpandedPair> r2 = r7.iterator();
            while (true) {
                if (!r2.hasNext()) {
                    r1 = true;
                    continue;
                    break;
                }
                com.google.zxing.oned.rss.expanded.ExpandedPair r3 = r2.next();
                java.util.Iterator<com.google.zxing.oned.rss.expanded.ExpandedPair> r5 = r0.getPairs().iterator();
                while (true) {
                    if (!r5.hasNext()) {
                        r3 = false;
                        continue;
                        break;
                    } else if (r3.equals(r5.next())) {
                        r3 = true;
                        continue;
                        break;
                    }
                }
                if (!r3) {
                    break;
                }
            }
        } while (!r1);
        return true;
    }

    List<ExpandedRow> getRows() {
        return this.rows;
    }

    static Result constructResult(List<ExpandedPair> list) throws NotFoundException, FormatException {
        String parseInformation = AbstractExpandedDecoder.createDecoder(BitArrayBuilder.buildBitArray(list)).parseInformation();
        ResultPoint[] resultPoints = list.get(0).getFinderPattern().getResultPoints();
        ResultPoint[] resultPoints2 = list.get(list.size() - 1).getFinderPattern().getResultPoints();
        return new Result(parseInformation, null, new ResultPoint[]{resultPoints[0], resultPoints[1], resultPoints2[0], resultPoints2[1]}, BarcodeFormat.RSS_EXPANDED);
    }

    private boolean checkChecksum() {
        ExpandedPair expandedPair = this.pairs.get(0);
        DataCharacter leftChar = expandedPair.getLeftChar();
        DataCharacter rightChar = expandedPair.getRightChar();
        if (rightChar == null) {
            return false;
        }
        int checksumPortion = rightChar.getChecksumPortion();
        int i = 2;
        for (int i2 = 1; i2 < this.pairs.size(); i2++) {
            ExpandedPair expandedPair2 = this.pairs.get(i2);
            checksumPortion += expandedPair2.getLeftChar().getChecksumPortion();
            i++;
            DataCharacter rightChar2 = expandedPair2.getRightChar();
            if (rightChar2 != null) {
                checksumPortion += rightChar2.getChecksumPortion();
                i++;
            }
        }
        return ((i + (-4)) * 211) + (checksumPortion % 211) == leftChar.getValue();
    }

    private static int getNextSecondBar(BitArray bitArray, int i) {
        if (bitArray.get(i)) {
            return bitArray.getNextSet(bitArray.getNextUnset(i));
        }
        return bitArray.getNextUnset(bitArray.getNextSet(i));
    }

    ExpandedPair retrieveNextPair(BitArray bitArray, List<ExpandedPair> list, int i) throws NotFoundException {
        FinderPattern parseFoundFinderPattern;
        DataCharacter dataCharacter;
        boolean z = list.size() % 2 == 0;
        if (this.startFromEven) {
            z = !z;
        }
        int i2 = -1;
        boolean z2 = true;
        do {
            findNextPair(bitArray, list, i2);
            parseFoundFinderPattern = parseFoundFinderPattern(bitArray, i, z);
            if (parseFoundFinderPattern == null) {
                i2 = getNextSecondBar(bitArray, this.startEnd[0]);
                continue;
            } else {
                z2 = false;
                continue;
            }
        } while (z2);
        DataCharacter decodeDataCharacter = decodeDataCharacter(bitArray, parseFoundFinderPattern, z, true);
        if (!list.isEmpty() && list.get(list.size() - 1).mustBeLast()) {
            throw NotFoundException.getNotFoundInstance();
        }
        try {
            dataCharacter = decodeDataCharacter(bitArray, parseFoundFinderPattern, z, false);
        } catch (NotFoundException unused) {
            dataCharacter = null;
        }
        return new ExpandedPair(decodeDataCharacter, dataCharacter, parseFoundFinderPattern, true);
    }

    private void findNextPair(BitArray bitArray, List<ExpandedPair> list, int i) throws NotFoundException {
        int[] decodeFinderCounters = getDecodeFinderCounters();
        decodeFinderCounters[0] = 0;
        decodeFinderCounters[1] = 0;
        decodeFinderCounters[2] = 0;
        decodeFinderCounters[3] = 0;
        int size = bitArray.getSize();
        if (i < 0) {
            i = list.isEmpty() ? 0 : list.get(list.size() - 1).getFinderPattern().getStartEnd()[1];
        }
        boolean z = list.size() % 2 != 0;
        if (this.startFromEven) {
            z = !z;
        }
        boolean z2 = false;
        while (i < size) {
            z2 = !bitArray.get(i);
            if (!z2) {
                break;
            }
            i++;
        }
        int i2 = 0;
        boolean z3 = z2;
        int i3 = i;
        while (i < size) {
            if (bitArray.get(i) ^ z3) {
                decodeFinderCounters[i2] = decodeFinderCounters[i2] + 1;
            } else {
                if (i2 == 3) {
                    if (z) {
                        reverseCounters(decodeFinderCounters);
                    }
                    if (isFinderPattern(decodeFinderCounters)) {
                        int[] iArr = this.startEnd;
                        iArr[0] = i3;
                        iArr[1] = i;
                        return;
                    }
                    if (z) {
                        reverseCounters(decodeFinderCounters);
                    }
                    i3 += decodeFinderCounters[0] + decodeFinderCounters[1];
                    decodeFinderCounters[0] = decodeFinderCounters[2];
                    decodeFinderCounters[1] = decodeFinderCounters[3];
                    decodeFinderCounters[2] = 0;
                    decodeFinderCounters[3] = 0;
                    i2--;
                } else {
                    i2++;
                }
                decodeFinderCounters[i2] = 1;
                z3 = !z3;
            }
            i++;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static void reverseCounters(int[] iArr) {
        int length = iArr.length;
        for (int i = 0; i < length / 2; i++) {
            int i2 = iArr[i];
            int i3 = (length - i) - 1;
            iArr[i] = iArr[i3];
            iArr[i3] = i2;
        }
    }

    private FinderPattern parseFoundFinderPattern(BitArray bitArray, int i, boolean z) {
        int i2;
        int i3;
        int i4;
        if (z) {
            int i5 = this.startEnd[0] - 1;
            while (i5 >= 0 && !bitArray.get(i5)) {
                i5--;
            }
            int i6 = i5 + 1;
            int[] iArr = this.startEnd;
            i4 = iArr[0] - i6;
            i2 = iArr[1];
            i3 = i6;
        } else {
            int[] iArr2 = this.startEnd;
            int i7 = iArr2[0];
            int nextUnset = bitArray.getNextUnset(iArr2[1] + 1);
            i2 = nextUnset;
            i3 = i7;
            i4 = nextUnset - this.startEnd[1];
        }
        int[] decodeFinderCounters = getDecodeFinderCounters();
        System.arraycopy(decodeFinderCounters, 0, decodeFinderCounters, 1, decodeFinderCounters.length - 1);
        decodeFinderCounters[0] = i4;
        try {
            return new FinderPattern(parseFinderValue(decodeFinderCounters, FINDER_PATTERNS), new int[]{i3, i2}, i3, i2, i);
        } catch (NotFoundException unused) {
            return null;
        }
    }

    DataCharacter decodeDataCharacter(BitArray bitArray, FinderPattern finderPattern, boolean z, boolean z2) throws NotFoundException {
        int[] dataCharacterCounters = getDataCharacterCounters();
        dataCharacterCounters[0] = 0;
        dataCharacterCounters[1] = 0;
        dataCharacterCounters[2] = 0;
        dataCharacterCounters[3] = 0;
        dataCharacterCounters[4] = 0;
        dataCharacterCounters[5] = 0;
        dataCharacterCounters[6] = 0;
        dataCharacterCounters[7] = 0;
        if (z2) {
            recordPatternInReverse(bitArray, finderPattern.getStartEnd()[0], dataCharacterCounters);
        } else {
            recordPattern(bitArray, finderPattern.getStartEnd()[1], dataCharacterCounters);
            int i = 0;
            for (int length = dataCharacterCounters.length - 1; i < length; length--) {
                int i2 = dataCharacterCounters[i];
                dataCharacterCounters[i] = dataCharacterCounters[length];
                dataCharacterCounters[length] = i2;
                i++;
            }
        }
        float sum = MathUtils.sum(dataCharacterCounters) / 17.0f;
        float f = (finderPattern.getStartEnd()[1] - finderPattern.getStartEnd()[0]) / 15.0f;
        if (Math.abs(sum - f) / f > 0.3f) {
            throw NotFoundException.getNotFoundInstance();
        }
        int[] oddCounts = getOddCounts();
        int[] evenCounts = getEvenCounts();
        float[] oddRoundingErrors = getOddRoundingErrors();
        float[] evenRoundingErrors = getEvenRoundingErrors();
        for (int i3 = 0; i3 < dataCharacterCounters.length; i3++) {
            float f2 = (dataCharacterCounters[i3] * 1.0f) / sum;
            int i4 = (int) (0.5f + f2);
            if (i4 <= 0) {
                if (f2 < 0.3f) {
                    throw NotFoundException.getNotFoundInstance();
                }
                i4 = 1;
            } else if (i4 > 8) {
                if (f2 > 8.7f) {
                    throw NotFoundException.getNotFoundInstance();
                }
                i4 = 8;
            }
            int i5 = i3 / 2;
            if ((i3 & 1) == 0) {
                oddCounts[i5] = i4;
                oddRoundingErrors[i5] = f2 - i4;
            } else {
                evenCounts[i5] = i4;
                evenRoundingErrors[i5] = f2 - i4;
            }
        }
        adjustOddEvenCounts(17);
        int value = (((finderPattern.getValue() * 4) + (z ? 0 : 2)) + (!z2 ? 1 : 0)) - 1;
        int i6 = 0;
        int i7 = 0;
        for (int length2 = oddCounts.length - 1; length2 >= 0; length2--) {
            if (isNotA1left(finderPattern, z, z2)) {
                i6 += oddCounts[length2] * WEIGHTS[value][length2 * 2];
            }
            i7 += oddCounts[length2];
        }
        int i8 = 0;
        for (int length3 = evenCounts.length - 1; length3 >= 0; length3--) {
            if (isNotA1left(finderPattern, z, z2)) {
                i8 += evenCounts[length3] * WEIGHTS[value][(length3 * 2) + 1];
            }
        }
        int i9 = i6 + i8;
        if ((i7 & 1) != 0 || i7 > 13 || i7 < 4) {
            throw NotFoundException.getNotFoundInstance();
        }
        int i10 = (13 - i7) / 2;
        int i11 = SYMBOL_WIDEST[i10];
        return new DataCharacter((RSSUtils.getRSSvalue(oddCounts, i11, true) * EVEN_TOTAL_SUBSET[i10]) + RSSUtils.getRSSvalue(evenCounts, 9 - i11, false) + GSUM[i10], i9);
    }

    private static boolean isNotA1left(FinderPattern finderPattern, boolean z, boolean z2) {
        return (finderPattern.getValue() == 0 && z && z2) ? false : true;
    }

    /* JADX WARN: Removed duplicated region for block: B:53:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00b7  */
    /* JADX WARN: Removed duplicated region for block: B:71:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void adjustOddEvenCounts(int r11) throws com.google.zxing.NotFoundException {
        boolean r6;
        boolean r7;
        boolean r2;
        boolean r3;
        int r0 = com.google.zxing.common.detector.MathUtils.sum(getOddCounts());
        int r1 = com.google.zxing.common.detector.MathUtils.sum(getEvenCounts());
        boolean r5 = true;
        if (r0 > 13) {
            r6 = false;
            r7 = true;
        } else if (r0 < 4) {
            r7 = false;
            r6 = true;
        } else {
            r6 = false;
            r7 = false;
        }
        if (r1 > 13) {
            r2 = false;
            r3 = true;
        } else if (r1 < 4) {
            r3 = false;
            r2 = true;
        } else {
            r2 = false;
            r3 = false;
        }
        int r8 = (r0 + r1) - r11;
        boolean r11 = (r0 & 1) == 1;
        boolean r4 = (r1 & 1) == 0;
        if (r8 == 1) {
            if (r11) {
                if (r4) {
                    throw com.google.zxing.NotFoundException.getNotFoundInstance();
                }
                r7 = true;
            } else if (!r4) {
                throw com.google.zxing.NotFoundException.getNotFoundInstance();
            } else {
                r3 = true;
            }
        } else if (r8 == -1) {
            if (r11) {
                if (r4) {
                    throw com.google.zxing.NotFoundException.getNotFoundInstance();
                }
                if (r5) {
                    if (r7) {
                        throw com.google.zxing.NotFoundException.getNotFoundInstance();
                    }
                    increment(getOddCounts(), getOddRoundingErrors());
                }
                if (r7) {
                    decrement(getOddCounts(), getOddRoundingErrors());
                }
                if (r2) {
                    if (r3) {
                        throw com.google.zxing.NotFoundException.getNotFoundInstance();
                    }
                    increment(getEvenCounts(), getOddRoundingErrors());
                }
                if (r3) {
                    decrement(getEvenCounts(), getEvenRoundingErrors());
                    return;
                }
                return;
            } else if (!r4) {
                throw com.google.zxing.NotFoundException.getNotFoundInstance();
            } else {
                r2 = true;
            }
        } else if (r8 != 0) {
            throw com.google.zxing.NotFoundException.getNotFoundInstance();
        } else {
            if (r11) {
                if (!r4) {
                    throw com.google.zxing.NotFoundException.getNotFoundInstance();
                }
                if (r0 < r1) {
                    r3 = true;
                    if (r5) {
                    }
                    if (r7) {
                    }
                    if (r2) {
                    }
                    if (r3) {
                    }
                } else {
                    r2 = true;
                    r7 = true;
                }
            } else if (r4) {
                throw com.google.zxing.NotFoundException.getNotFoundInstance();
            }
        }
        r5 = r6;
        if (r5) {
        }
        if (r7) {
        }
        if (r2) {
        }
        if (r3) {
        }
    }
}
