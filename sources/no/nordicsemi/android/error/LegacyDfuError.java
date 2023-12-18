package no.nordicsemi.android.error;

import com.egeetouch.egeetouch_manager.BLEService;
/* loaded from: classes2.dex */
public final class LegacyDfuError {
    public static final int CRC_ERROR = 5;
    public static final int DATA_SIZE_EXCEEDS_LIMIT = 4;
    public static final int INVALID_STATE = 2;
    public static final int NOT_SUPPORTED = 3;
    public static final int OPERATION_FAILED = 6;

    public static String parse(int i) {
        switch (i) {
            case BLEService.ReadResponse_syncPasscode /* 258 */:
                return "INVALID STATE";
            case BLEService.Request_changePasscode_Count /* 259 */:
                return "NOT SUPPORTED";
            case BLEService.ReadResponse_changePasscode_Count /* 260 */:
                return "DATA SIZE EXCEEDS LIMIT";
            case 261:
                return "INVALID CRC ERROR";
            case 262:
                return "OPERATION FAILED";
            default:
                return "UNKNOWN (" + i + ")";
        }
    }
}
