package com.google.zxing.datamatrix.encoder;

import com.egeetouch.egeetouch_manager.BLEService;
import com.egeetouch.egeetouch_manager.TaskManagement;
import com.facebook.internal.FacebookRequestErrorClassification;
import es.dmoral.toasty.BuildConfig;
/* loaded from: classes2.dex */
public final class ErrorCorrection {
    private static final int MODULO_VALUE = 301;
    private static final int[] FACTOR_SETS = {5, 7, 10, 11, 12, 14, 18, 20, 24, 28, 36, 42, 48, 56, 62, 68};
    private static final int[][] FACTORS = {new int[]{BLEService.Read_response_timestamp, 48, 15, 111, 62}, new int[]{23, 68, BLEService.Request_old_passcode, TaskManagement.restore_user2, BLEService.ReadResponse_ShackleBypass, 92, BLEService.ReadResponse_clear_passcodeAuditTrail}, new int[]{28, 24, 185, BLEService.Read_Response_enter_low_power_mode, BLEService.Request_tag_list_version2, 248, 116, 255, 110, 61}, new int[]{175, 138, 205, 12, 194, BLEService.Read_Response_read_serial, 39, BLEService.ReadResponse_ChangePasscode, 60, 97, 120}, new int[]{41, BLEService.AutoLocking_Settings, BLEService.Request_tag_AuditTrail_number, 91, 61, 42, BuildConfig.VERSION_CODE, 213, 97, BLEService.Read_response_Admin_Password_new_lock, 100, BLEService.ReadResponse_Master_Tag}, new int[]{156, 97, 192, BLEService.ReadResponse_passcodeAudit, 95, 9, 157, 119, 138, 45, 18, 186, 83, 185}, new int[]{83, 195, 100, 39, 188, 75, 66, 61, BLEService.Request_Master_Tag, 213, 109, TaskManagement.restore_tag2, 94, BLEService.ReadResponse_clear_passcodeAuditTrail, BLEService.Add_Tag_fail, 48, 90, 188}, new int[]{15, 195, BLEService.Read_response_lotoBatteryRecords, 9, BLEService.Read_response_clear_tag_AuditTrail, 71, BLEService.Read_Response_read_serial, 2, 188, BLEService.check_version, BLEService.AutoLocking_Settings, com.egeetouch.egeetouch_manager.BuildConfig.VERSION_CODE, BLEService.Request_clear_passcodeAuditTrail, 79, 108, 82, 27, 174, 186, 172}, new int[]{52, FacebookRequestErrorClassification.EC_INVALID_TOKEN, 88, 205, 109, 39, BLEService.Verify_Admin_Password_new_lock, 21, 155, 197, BLEService.Request_passcodeAudit, BLEService.Request_tag_list_version2, 155, 21, 5, 172, BLEService.ReadResponse_clear_passcodeAuditTrail, 124, 12, 181, 184, 96, 50, 193}, new int[]{211, BLEService.Read_response_tag_AuditTrail, 43, 97, 71, 96, 103, 174, 37, 151, 170, 53, 75, 34, BLEService.Request_passcodeValue, 121, 17, 138, 110, 213, 141, TaskManagement.restore_user4, 120, 151, BLEService.Read_response_clear_tag_AuditTrail, BLEService.Read_Response_read_serial, 93, 255}, new int[]{BLEService.ReadResponse_ChangePasscode, 127, BLEService.ReadResponse_Master_Tag, 218, TaskManagement.restore_admin, 250, 162, 181, 102, 120, 84, 179, 220, BLEService.Request_passcodeAudit, 80, 182, BLEService.Read_response_tag_AuditTrail_number, 18, 2, 4, 68, 33, 101, 137, 95, 119, 115, 44, 175, 184, 59, 25, BLEService.Add_Tag_fail, 98, 81, 112}, new int[]{77, 193, 137, 31, 19, 38, 22, BLEService.AutoLocking_Settings, 247, 105, 122, 2, BLEService.ReadResponse_ChangePasscode, TaskManagement.restore_user1, BLEService.ReadResponse_Master_Tag, 8, 175, 95, 100, 9, BLEService.Read_Response_quit_low_power_mode, 105, 214, 111, 57, 121, 21, 1, BLEService.Request_clear_passcodeAuditTrail, 57, 54, 101, 248, 202, 69, 50, 150, 177, 226, 5, 9, 5}, new int[]{BLEService.ReadResponse_ChangePasscode, TaskManagement.log_extract20, 172, BLEService.Request_tag_list_version2, 96, 32, 117, 22, BLEService.ShackleBypass_settings, TaskManagement.restore_user1, BLEService.ShackleBypass_settings, BLEService.Read_response_tag_AuditTrail, 205, 188, BLEService.ReadResponse_AutoLocking_Settings, 87, 191, 106, 16, 147, 118, 23, 37, 90, 170, 205, TaskManagement.log_extract10, 88, 120, 100, 66, 138, 186, BLEService.ReadResponse_ShackleBypass, 82, 44, BLEService.Verify_Admin_Password_new_lock, 87, BLEService.disconnect, 147, BLEService.check_version, 175, 69, 213, 92, BLEService.Request_clear_passcodeAuditTrail, BLEService.Add_Tag_fail, 19}, new int[]{175, 9, BLEService.Request_tag_list_version2, BLEService.ShackleBypass_settings, 12, 17, 220, 208, 100, 29, 175, 170, BLEService.Request_tag_AuditTrial, 192, 215, BLEService.ScanTagId_FromLock, 150, BLEService.Send_timeStamp, 36, BLEService.Request_tag_list_version2, 38, 200, TaskManagement.log_extract20, 54, BLEService.Read_response_timestamp, 146, 218, 234, 117, 203, 29, BLEService.Request_clear_tag_AuditTrail, BLEService.Request_old_passcode, BLEService.ShackleBypass_settings, 22, 150, 201, 117, 62, 207, 164, 13, 137, BLEService.ReadResponse_ChangePasscode, 127, 67, 247, 28, 155, 43, 203, 107, BLEService.Read_response_clear_tag_AuditTrail, 53, 143, 46}, new int[]{BLEService.ReadResponse_Master_Tag, 93, 169, 50, BLEService.Request_old_passcode, 210, 39, 118, 202, 188, 201, 189, 143, 108, 196, 37, 185, 112, TaskManagement.restore_user2, BLEService.Request_tag_AuditTrial, BLEService.ReadResponse_ChangePasscode, 63, 197, FacebookRequestErrorClassification.EC_INVALID_TOKEN, 250, 106, 185, BLEService.Add_tag_version2, 175, 64, 114, 71, BLEService.check_model, 44, 147, 6, 27, 218, 51, 63, 87, 10, 40, TaskManagement.restore_admin, 188, 17, 163, 31, BLEService.Verify_Admin_Password_new_lock, 170, 4, 107, BLEService.Request_clear_tag_AuditTrail, 7, 94, BLEService.Read_Response_enter_low_power_mode, 224, 124, 86, 47, 11, 204}, new int[]{220, BLEService.Read_response_timestamp, BLEService.Request_ChangePasscode, 89, BLEService.Request_passcodeAudit, 149, BLEService.Send_timeStamp, 56, 89, 33, 147, BLEService.Read_response_lotoBatteryRecords, 154, 36, 73, 127, 213, TaskManagement.restore_user4, 248, 180, 234, 197, BLEService.Request_tag_AuditTrail_number, 177, 68, 122, 93, 213, 15, BLEService.check_version, BLEService.Read_Response_Request_Tag_number_Version2, BLEService.ReadResponse_scanTagId, 66, 139, BLEService.AutoLocking_Settings, 185, 202, BLEService.Read_Response_quit_low_power_mode, 179, 25, 220, BLEService.Request_clear_tag_AuditTrail, 96, 210, BLEService.Read_response_tag_AuditTrail, TaskManagement.restore_user4, BLEService.Request_tag_list_version2, 239, 181, BLEService.Request_Master_Tag, 59, 52, 172, 25, 49, BLEService.Request_clear_tag_AuditTrail, 211, 189, 64, 54, 108, BLEService.AutoLocking_Settings, TaskManagement.log_extract20, 63, 96, 103, 82, 186}};
    private static final int[] LOG = new int[256];
    private static final int[] ALOG = new int[255];

    static {
        int i = 1;
        for (int i2 = 0; i2 < 255; i2++) {
            ALOG[i2] = i;
            LOG[i] = i2;
            i <<= 1;
            if (i >= 256) {
                i ^= 301;
            }
        }
    }

    private ErrorCorrection() {
    }

    public static String encodeECC200(String str, SymbolInfo symbolInfo) {
        if (str.length() != symbolInfo.getDataCapacity()) {
            throw new IllegalArgumentException("The number of codewords does not match the selected symbol");
        }
        StringBuilder sb = new StringBuilder(symbolInfo.getDataCapacity() + symbolInfo.getErrorCodewords());
        sb.append(str);
        int interleavedBlockCount = symbolInfo.getInterleavedBlockCount();
        if (interleavedBlockCount == 1) {
            sb.append(createECCBlock(str, symbolInfo.getErrorCodewords()));
        } else {
            sb.setLength(sb.capacity());
            int[] iArr = new int[interleavedBlockCount];
            int[] iArr2 = new int[interleavedBlockCount];
            int[] iArr3 = new int[interleavedBlockCount];
            int i = 0;
            while (i < interleavedBlockCount) {
                int i2 = i + 1;
                iArr[i] = symbolInfo.getDataLengthForInterleavedBlock(i2);
                iArr2[i] = symbolInfo.getErrorLengthForInterleavedBlock(i2);
                iArr3[i] = 0;
                if (i > 0) {
                    iArr3[i] = iArr3[i - 1] + iArr[i];
                }
                i = i2;
            }
            for (int i3 = 0; i3 < interleavedBlockCount; i3++) {
                StringBuilder sb2 = new StringBuilder(iArr[i3]);
                for (int i4 = i3; i4 < symbolInfo.getDataCapacity(); i4 += interleavedBlockCount) {
                    sb2.append(str.charAt(i4));
                }
                String createECCBlock = createECCBlock(sb2.toString(), iArr2[i3]);
                int i5 = i3;
                int i6 = 0;
                while (i5 < iArr2[i3] * interleavedBlockCount) {
                    sb.setCharAt(symbolInfo.getDataCapacity() + i5, createECCBlock.charAt(i6));
                    i5 += interleavedBlockCount;
                    i6++;
                }
            }
        }
        return sb.toString();
    }

    private static String createECCBlock(CharSequence charSequence, int i) {
        return createECCBlock(charSequence, 0, charSequence.length(), i);
    }

    private static String createECCBlock(CharSequence charSequence, int i, int i2, int i3) {
        int i4 = 0;
        while (true) {
            int[] iArr = FACTOR_SETS;
            if (i4 >= iArr.length) {
                i4 = -1;
                break;
            } else if (iArr[i4] == i3) {
                break;
            } else {
                i4++;
            }
        }
        if (i4 < 0) {
            throw new IllegalArgumentException("Illegal number of error correction codewords specified: " + i3);
        }
        int[] iArr2 = FACTORS[i4];
        char[] cArr = new char[i3];
        for (int i5 = 0; i5 < i3; i5++) {
            cArr[i5] = 0;
        }
        for (int i6 = i; i6 < i + i2; i6++) {
            int i7 = i3 - 1;
            int charAt = cArr[i7] ^ charSequence.charAt(i6);
            while (i7 > 0) {
                if (charAt != 0 && iArr2[i7] != 0) {
                    char c = cArr[i7 - 1];
                    int[] iArr3 = ALOG;
                    int[] iArr4 = LOG;
                    cArr[i7] = (char) (c ^ iArr3[(iArr4[charAt] + iArr4[iArr2[i7]]) % 255]);
                } else {
                    cArr[i7] = cArr[i7 - 1];
                }
                i7--;
            }
            if (charAt != 0 && iArr2[0] != 0) {
                int[] iArr5 = ALOG;
                int[] iArr6 = LOG;
                cArr[0] = (char) iArr5[(iArr6[charAt] + iArr6[iArr2[0]]) % 255];
            } else {
                cArr[0] = 0;
            }
        }
        char[] cArr2 = new char[i3];
        for (int i8 = 0; i8 < i3; i8++) {
            cArr2[i8] = cArr[(i3 - i8) - 1];
        }
        return String.valueOf(cArr2);
    }
}
