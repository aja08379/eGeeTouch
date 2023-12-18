package com.egeetouch.egeetouch_manager;

import android.app.PendingIntent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.nfc.NfcAdapter;
import android.text.format.Time;
import android.util.Log;
import com.facebook.appevents.AppEventsConstants;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.nio.ByteBuffer;
import kotlin.jvm.internal.ByteCompanionObject;
/* loaded from: classes.dex */
public class TaskManagement {
    public static boolean admin_restore_done = false;
    public static boolean admin_update2_done = false;
    public static boolean admin_update3_done = false;
    public static boolean admin_update_done = false;
    public static boolean admin_update_done_wrong_lock = false;
    public static boolean admin_varified = false;
    public static boolean admin_varified_correct = false;
    public static boolean admin_varified_first_time = false;
    public static final int battery = 114;
    public static int current_function = 0;
    public static boolean first_time = false;
    public static final int idle = 0;
    public static final int lock_detail = 116;
    public static boolean lock_detail_updated = false;
    public static final int log_extract10 = 131;
    public static boolean log_extract10_done = false;
    public static final int log_extract20 = 132;
    public static boolean log_extract20_done = false;
    public static boolean password_varified = false;
    public static boolean password_varified_correct = false;
    public static boolean password_varified_first_time = false;
    public static final int production = 96;
    public static final int restore_admin = 130;
    public static final int restore_tag = 128;
    public static final int restore_tag1 = 128;
    public static final int restore_tag2 = 129;
    public static final int restore_user1 = 133;
    public static final int restore_user2 = 134;
    public static final int restore_user3 = 135;
    public static final int restore_user4 = 136;
    public static final int setting = 112;
    public static boolean setting_update_done = false;
    public static final int setting_version_battery = 115;
    public static boolean setting_version_battery_done = false;
    public static boolean tag_restore_done = false;
    public static boolean tag_update_done = false;
    public static boolean tag_update_done_wrong_lock = false;
    public static final int update_admin = 81;
    public static final int update_admin2 = 82;
    public static final int update_admin3 = 83;
    public static final int update_tag = 16;
    public static final int update_user = 33;
    public static boolean user_update_done = false;
    public static final int verify_admin = 80;
    public static final int verify_password = 48;
    public static final int verify_user = 32;
    public static final int version = 113;
    public static String version_detected = "0.0";
    public NfcAdapter mAdapter;
    public IntentFilter[] mFilters;
    public PendingIntent mPendingIntent;
    public String[][] mTechLists;
    public static Boolean version_updated = false;
    public static Boolean buzzer_sound = true;
    private static int total_byte = 600;
    public static int number_of_user = 0;
    private static byte[] bufferFile = null;
    public static Boolean is_first_lock = false;
    private static double latitude_previous = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
    private static double longitude_previous = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
    public static int user_num_in_lock = 0;
    public static boolean updated_flag = false;

    public static byte[] prepare_send_out_data() {
        char c;
        int i;
        int i2;
        int i3;
        String string;
        bufferFile = new byte[total_byte];
        Cursor rawQuery = DatabaseVariable.db_lock.rawQuery(DatabaseVariable.lockdb_Query_model(BLEService.selected_lock_name), null);
        int i4 = 1;
        String string2 = rawQuery.moveToNext() ? rawQuery.getString(1) : "      ";
        rawQuery.close();
        byte[] bytes = string2.getBytes();
        String lockdb_getversion = DatabaseVariable.lockdb_getversion(BLEService.selected_lock_name);
        int i5 = current_function;
        int i6 = 3;
        int i7 = 0;
        if (i5 == 16) {
            bufferFile[0] = 16;
            Cursor rawQuery2 = DatabaseVariable.db_tag.rawQuery("SELECT * FROM Tag WHERE Lock = " + DatabaseUtils.sqlEscapeString(BLEService.selected_lock_name), null);
            if (rawQuery2.getCount() != 0) {
                String[] strArr = new String[35];
                String[] strArr2 = new String[35];
                int i8 = -1;
                while (rawQuery2.moveToNext()) {
                    i8++;
                    strArr[i8] = rawQuery2.getString(1);
                    strArr2[i8] = rawQuery2.getString(0);
                }
                int i9 = 0;
                while (i9 < rawQuery2.getCount()) {
                    byte[] bArr = new byte[7];
                    String[] split = strArr[i9].split(" ");
                    for (int i10 = i4; i10 < i6; i10++) {
                        bArr[i10] = Helper_NFC.ConvertStringToHexByte(split[i10]);
                    }
                    if (lockdb_getversion.equals("3.1") || lockdb_getversion.equals("3.2") || lockdb_getversion.equals("3.3")) {
                        byte[] bArr2 = bufferFile;
                        bArr2[2] = bytes[0];
                        bArr2[3] = bytes[1];
                        bArr2[4] = bytes[2];
                        bArr2[5] = bytes[3];
                        bArr2[6] = bytes[4];
                        bArr2[7] = bytes[5];
                        int i11 = i9 * 2;
                        bArr2[i11 + 8] = bArr[1];
                        bArr2[i11 + 9] = bArr[2];
                        for (int i12 = 1; i12 <= strArr2[i9].length(); i12++) {
                            bufferFile[(rawQuery2.getCount() * 2) + 8 + (i9 * 12) + i12] = (byte) strArr2[i9].charAt(i12 - 1);
                        }
                    } else {
                        byte[] bArr3 = bufferFile;
                        int i13 = i9 * 2;
                        bArr3[i13 + 2] = bArr[i4];
                        bArr3[i13 + i6] = bArr[2];
                        for (int i14 = i4; i14 <= strArr2[i9].length(); i14++) {
                            bufferFile[(rawQuery2.getCount() * 2) + 2 + (i9 * 12) + i14] = (byte) strArr2[i9].charAt(i14 - 1);
                        }
                    }
                    i9++;
                    i4 = 1;
                    i6 = 3;
                }
                bufferFile[1] = (byte) (i8 + 1);
            } else {
                if (lockdb_getversion.equals("3.1") || lockdb_getversion.equals("3.2") || lockdb_getversion.equals("3.3")) {
                    byte[] bArr4 = bufferFile;
                    bArr4[2] = bytes[0];
                    bArr4[3] = bytes[1];
                    bArr4[4] = bytes[2];
                    bArr4[5] = bytes[3];
                    bArr4[6] = bytes[4];
                    bArr4[7] = bytes[5];
                    for (int i15 = 0; i15 < 7; i15++) {
                        byte[] bArr5 = bufferFile;
                        int i16 = i15 * 2;
                        bArr5[i16 + 8] = -1;
                        bArr5[i16 + 9] = -1;
                    }
                    c = 1;
                    bufferFile[1] = 0;
                } else {
                    for (int i17 = 0; i17 < 7; i17++) {
                        byte[] bArr6 = bufferFile;
                        int i18 = i17 * 2;
                        bArr6[i18 + 2] = -1;
                        bArr6[i18 + 3] = -1;
                    }
                    c = 1;
                }
                bufferFile[c] = 0;
            }
        } else if (i5 != 33) {
            int i19 = 14;
            if (i5 == 48) {
                Helper_PhoneDetails.get_location(MainActivity.context);
                Log.i("TAG", "verify_password");
                if (is_first_lock.booleanValue()) {
                    bufferFile[0] = 80;
                } else {
                    bufferFile[0] = 48;
                }
                if (!string2.isEmpty()) {
                    MainActivity.old_lock_admin_password = string2;
                    byte[] bArr7 = bufferFile;
                    bArr7[1] = bytes[0];
                    bArr7[2] = bytes[1];
                    bArr7[3] = bytes[2];
                    bArr7[4] = bytes[3];
                    bArr7[5] = bytes[4];
                    bArr7[6] = bytes[5];
                }
                Time time = new Time();
                time.setToNow();
                bufferFile[9] = (byte) time.hour;
                bufferFile[10] = (byte) time.minute;
                bufferFile[11] = (byte) time.second;
                bufferFile[12] = (byte) time.monthDay;
                bufferFile[13] = (byte) (time.month + 1);
                bufferFile[14] = (byte) (time.year - 2000);
                byte[] bArr8 = new byte[8];
                ByteBuffer.wrap(bArr8).putDouble(Helper_PhoneDetails.myLocationLatitude);
                byte[] bArr9 = bufferFile;
                bArr9[18] = bArr8[0];
                bArr9[19] = bArr8[1];
                bArr9[20] = bArr8[2];
                bArr9[21] = bArr8[3];
                bArr9[22] = bArr8[4];
                bArr9[23] = bArr8[5];
                bArr9[24] = bArr8[6];
                bArr9[25] = bArr8[7];
                byte[] bArr10 = new byte[8];
                ByteBuffer.wrap(bArr10).putDouble(Helper_PhoneDetails.myLocationLongitude);
                byte[] bArr11 = bufferFile;
                bArr11[26] = bArr10[0];
                bArr11[27] = bArr10[1];
                bArr11[28] = bArr10[2];
                bArr11[29] = bArr10[3];
                bArr11[30] = bArr10[4];
                bArr11[31] = bArr10[5];
                bArr11[32] = bArr10[6];
                bArr11[33] = bArr10[7];
                bArr11[34] = 11;
            } else if (i5 == 112) {
                bufferFile[0] = 112;
                if (lockdb_getversion.equals("3.1") || lockdb_getversion.equals("3.2") || lockdb_getversion.equals("3.3")) {
                    byte[] bArr12 = bufferFile;
                    bArr12[1] = bytes[0];
                    bArr12[2] = bytes[1];
                    bArr12[3] = bytes[2];
                    bArr12[4] = bytes[3];
                    bArr12[5] = bytes[4];
                    bArr12[6] = bytes[5];
                    if (buzzer_sound.booleanValue()) {
                        bufferFile[7] = 1;
                    } else {
                        bufferFile[7] = 0;
                    }
                } else if (buzzer_sound.booleanValue()) {
                    bufferFile[1] = 1;
                } else {
                    bufferFile[1] = 0;
                }
            } else if (i5 == 115) {
                bufferFile[0] = 115;
            } else if (i5 != 116) {
                try {
                    switch (i5) {
                        case 81:
                            bufferFile[0] = 81;
                            Cursor rawQuery3 = DatabaseVariable.db_lock.rawQuery(DatabaseVariable.lockdb_Query_model(BLEService.selected_lock_name), null);
                            string = rawQuery3.moveToNext() ? rawQuery3.getString(1) : "      ";
                            rawQuery3.close();
                            byte[] bytes2 = MainActivity.old_lock_admin_password.getBytes();
                            if (lockdb_getversion.equals("3.1") || lockdb_getversion.equals("3.2") || lockdb_getversion.equals("3.3")) {
                                byte[] bArr13 = bufferFile;
                                bArr13[1] = bytes2[0];
                                bArr13[2] = bytes2[1];
                                bArr13[3] = bytes2[2];
                                bArr13[4] = bytes2[3];
                                bArr13[5] = bytes2[4];
                                bArr13[6] = bytes2[5];
                                for (int i20 = 1; i20 <= BLEService.selected_lock_name.length(); i20++) {
                                    bufferFile[i20 + 6] = (byte) BLEService.selected_lock_name.charAt(i20 - 1);
                                }
                                for (int i21 = 1; i21 <= string.length(); i21++) {
                                    bufferFile[i21 + 26] = (byte) string.charAt(i21 - 1);
                                }
                                number_of_user = 0;
                                Cursor rawQuery4 = DatabaseVariable.db_user.rawQuery("SELECT * FROM User WHERE Lock = " + DatabaseUtils.sqlEscapeString(BLEService.selected_lock_name), null);
                                if (rawQuery4.getCount() != 0) {
                                    String[] strArr3 = new String[40];
                                    int i22 = 0;
                                    while (rawQuery4.moveToNext()) {
                                        int i23 = i22 + 1;
                                        strArr3[i22] = rawQuery4.getString(0);
                                        i22 = i23 + 1;
                                        strArr3[i23] = rawQuery4.getString(1);
                                        user_num_in_lock++;
                                    }
                                    bufferFile[33] = (byte) rawQuery4.getCount();
                                    int count = rawQuery4.getCount() > 7 ? 7 : rawQuery4.getCount();
                                    for (int i24 = 0; i24 < count; i24++) {
                                        int i25 = i24 * 2;
                                        byte[] bytes3 = strArr3[i25 + 0].getBytes();
                                        byte[] bytes4 = strArr3[i25 + 1].getBytes();
                                        int length = bytes3.length;
                                        int i26 = 0;
                                        while (i26 < 20) {
                                            if (i26 < length) {
                                                bufferFile[i26 + 34 + (i24 * 28)] = bytes3[i26];
                                            } else {
                                                while (length < 20) {
                                                    bufferFile[length + 35 + (i24 * 28)] = -1;
                                                    length++;
                                                }
                                                i26 = 20;
                                            }
                                            i26++;
                                        }
                                        for (int i27 = 0; i27 < 6; i27++) {
                                            bufferFile[i27 + 52 + (i24 * 28)] = bytes4[i27];
                                        }
                                    }
                                    break;
                                }
                            } else {
                                byte[] bArr14 = bufferFile;
                                bArr14[0] = 33;
                                bArr14[1] = (byte) number_of_user;
                                while (i7 < total_byte - 3) {
                                    bufferFile[i7 + 2] = -1;
                                    i7++;
                                }
                                break;
                            }
                            break;
                        case 82:
                            bufferFile[0] = 82;
                            Cursor rawQuery5 = DatabaseVariable.db_lock.rawQuery(DatabaseVariable.lockdb_Query_model(BLEService.selected_lock_name), null);
                            string = rawQuery5.moveToNext() ? rawQuery5.getString(1) : "      ";
                            rawQuery5.close();
                            byte[] bytes5 = MainActivity.old_lock_admin_password.getBytes();
                            if (lockdb_getversion.equals("3.1") || lockdb_getversion.equals("3.2") || lockdb_getversion.equals("3.3")) {
                                byte[] bArr15 = bufferFile;
                                bArr15[1] = bytes5[0];
                                bArr15[2] = bytes5[1];
                                bArr15[3] = bytes5[2];
                                bArr15[4] = bytes5[3];
                                bArr15[5] = bytes5[4];
                                bArr15[6] = bytes5[5];
                                for (int i28 = 1; i28 <= BLEService.selected_lock_name.length(); i28++) {
                                    bufferFile[i28 + 6] = (byte) BLEService.selected_lock_name.charAt(i28 - 1);
                                }
                                for (int i29 = 1; i29 <= string.length(); i29++) {
                                    bufferFile[i29 + 26] = (byte) string.charAt(i29 - 1);
                                }
                                number_of_user = 0;
                                Cursor rawQuery6 = DatabaseVariable.db_user.rawQuery("SELECT * FROM User WHERE Lock = " + DatabaseUtils.sqlEscapeString(BLEService.selected_lock_name), null);
                                if (rawQuery6.getCount() != 0) {
                                    String[] strArr4 = new String[40];
                                    int i30 = 0;
                                    while (rawQuery6.moveToNext()) {
                                        int i31 = i30 + 1;
                                        strArr4[i30] = rawQuery6.getString(0);
                                        i30 = i31 + 1;
                                        strArr4[i31] = rawQuery6.getString(1);
                                        user_num_in_lock++;
                                    }
                                    bufferFile[33] = (byte) rawQuery6.getCount();
                                    if (rawQuery6.getCount() <= 14) {
                                        i19 = rawQuery6.getCount();
                                    }
                                    for (int i32 = 7; i32 < i19; i32++) {
                                        int i33 = i32 * 2;
                                        byte[] bytes6 = strArr4[i33 + 0].getBytes();
                                        byte[] bytes7 = strArr4[i33 + 1].getBytes();
                                        int length2 = bytes6.length;
                                        int i34 = 0;
                                        while (i34 < 20) {
                                            if (i34 < length2) {
                                                bufferFile[i34 + 34 + ((i32 - 7) * 28)] = bytes6[i34];
                                            } else {
                                                while (length2 < 20) {
                                                    bufferFile[length2 + 35 + ((i32 - 7) * 28)] = -1;
                                                    length2++;
                                                }
                                                i34 = 20;
                                            }
                                            i34++;
                                        }
                                        for (int i35 = 0; i35 < 6; i35++) {
                                            bufferFile[i35 + 52 + ((i32 - 7) * 28)] = bytes7[i35];
                                        }
                                    }
                                    break;
                                }
                            }
                            break;
                        case 83:
                            bufferFile[0] = 83;
                            Cursor rawQuery7 = DatabaseVariable.db_lock.rawQuery(DatabaseVariable.lockdb_Query_model(BLEService.selected_lock_name), null);
                            string = rawQuery7.moveToNext() ? rawQuery7.getString(1) : "      ";
                            rawQuery7.close();
                            byte[] bytes8 = MainActivity.old_lock_admin_password.getBytes();
                            if (lockdb_getversion.equals("3.1") || lockdb_getversion.equals("3.2") || lockdb_getversion.equals("3.3")) {
                                byte[] bArr16 = bufferFile;
                                bArr16[1] = bytes8[0];
                                bArr16[2] = bytes8[1];
                                bArr16[3] = bytes8[2];
                                bArr16[4] = bytes8[3];
                                bArr16[5] = bytes8[4];
                                bArr16[6] = bytes8[5];
                                for (int i36 = 1; i36 <= BLEService.selected_lock_name.length(); i36++) {
                                    bufferFile[i36 + 6] = (byte) BLEService.selected_lock_name.charAt(i36 - 1);
                                }
                                for (int i37 = 1; i37 <= string.length(); i37++) {
                                    bufferFile[i37 + 26] = (byte) string.charAt(i37 - 1);
                                }
                                number_of_user = 0;
                                Cursor rawQuery8 = DatabaseVariable.db_user.rawQuery("SELECT * FROM User WHERE Lock = " + DatabaseUtils.sqlEscapeString(BLEService.selected_lock_name), null);
                                if (rawQuery8.getCount() != 0) {
                                    String[] strArr5 = new String[40];
                                    int i38 = 0;
                                    while (rawQuery8.moveToNext()) {
                                        int i39 = i38 + 1;
                                        strArr5[i38] = rawQuery8.getString(0);
                                        i38 = i39 + 1;
                                        strArr5[i39] = rawQuery8.getString(1);
                                        user_num_in_lock++;
                                    }
                                    bufferFile[33] = (byte) rawQuery8.getCount();
                                    int count2 = rawQuery8.getCount() > 21 ? 20 : rawQuery8.getCount();
                                    while (i19 < count2) {
                                        int i40 = i19 * 2;
                                        byte[] bytes9 = strArr5[i40 + 0].getBytes();
                                        byte[] bytes10 = strArr5[i40 + 1].getBytes();
                                        int length3 = bytes9.length;
                                        int i41 = 0;
                                        while (i41 < 20) {
                                            if (i41 < length3) {
                                                bufferFile[i41 + 34 + ((i19 - 14) * 28)] = bytes9[i41];
                                            } else {
                                                while (length3 < 20) {
                                                    bufferFile[length3 + 35 + ((i19 - 14) * 28)] = -1;
                                                    length3++;
                                                }
                                                i41 = 20;
                                            }
                                            i41++;
                                        }
                                        for (int i42 = 0; i42 < 6; i42++) {
                                            bufferFile[i42 + 52 + ((i19 - 14) * 28)] = bytes10[i42];
                                        }
                                        i19++;
                                    }
                                    break;
                                }
                            }
                            break;
                        default:
                            switch (i5) {
                                case 128:
                                    bufferFile[0] = ByteCompanionObject.MIN_VALUE;
                                    if (lockdb_getversion.equals("3.1") || lockdb_getversion.equals("3.2") || lockdb_getversion.equals("3.3")) {
                                        byte[] bArr17 = bufferFile;
                                        bArr17[1] = bytes[0];
                                        bArr17[2] = bytes[1];
                                        bArr17[3] = bytes[2];
                                        bArr17[4] = bytes[3];
                                        bArr17[5] = bytes[4];
                                        bArr17[6] = bytes[5];
                                        break;
                                    }
                                    break;
                                case restore_tag2 /* 129 */:
                                    bufferFile[0] = -127;
                                    if (lockdb_getversion.equals("3.1") || lockdb_getversion.equals("3.2") || lockdb_getversion.equals("3.3")) {
                                        byte[] bArr18 = bufferFile;
                                        bArr18[1] = bytes[0];
                                        bArr18[2] = bytes[1];
                                        bArr18[3] = bytes[2];
                                        bArr18[4] = bytes[3];
                                        bArr18[5] = bytes[4];
                                        bArr18[6] = bytes[5];
                                        break;
                                    }
                                    break;
                                case restore_admin /* 130 */:
                                    byte[] bArr19 = bufferFile;
                                    bArr19[0] = -126;
                                    bArr19[1] = bytes[0];
                                    bArr19[2] = bytes[1];
                                    bArr19[3] = bytes[2];
                                    bArr19[4] = bytes[3];
                                    bArr19[5] = bytes[4];
                                    bArr19[6] = bytes[5];
                                    break;
                                case log_extract10 /* 131 */:
                                    bufferFile[0] = -125;
                                    if (lockdb_getversion.equals("3.1") || lockdb_getversion.equals("3.2") || lockdb_getversion.equals("3.3")) {
                                        byte[] bArr20 = bufferFile;
                                        bArr20[1] = bytes[0];
                                        bArr20[2] = bytes[1];
                                        bArr20[3] = bytes[2];
                                        bArr20[4] = bytes[3];
                                        bArr20[5] = bytes[4];
                                        bArr20[6] = bytes[5];
                                        break;
                                    }
                                    break;
                                case log_extract20 /* 132 */:
                                    bufferFile[0] = -124;
                                    break;
                                case restore_user1 /* 133 */:
                                    bufferFile[0] = -123;
                                    if (lockdb_getversion.equals("3.1") || lockdb_getversion.equals("3.2") || lockdb_getversion.equals("3.3")) {
                                        byte[] bArr21 = bufferFile;
                                        bArr21[1] = bytes[0];
                                        bArr21[2] = bytes[1];
                                        bArr21[3] = bytes[2];
                                        bArr21[4] = bytes[3];
                                        bArr21[5] = bytes[4];
                                        bArr21[6] = bytes[5];
                                        break;
                                    }
                                    break;
                                case restore_user2 /* 134 */:
                                    bufferFile[0] = -122;
                                    if (lockdb_getversion.equals("3.1") || lockdb_getversion.equals("3.2") || lockdb_getversion.equals("3.3")) {
                                        byte[] bArr22 = bufferFile;
                                        bArr22[1] = bytes[0];
                                        bArr22[2] = bytes[1];
                                        bArr22[3] = bytes[2];
                                        bArr22[4] = bytes[3];
                                        bArr22[5] = bytes[4];
                                        bArr22[6] = bytes[5];
                                        break;
                                    }
                                    break;
                            }
                    }
                } catch (Exception unused) {
                }
            } else {
                bufferFile[0] = 116;
            }
        } else {
            number_of_user = 0;
            Cursor rawQuery9 = DatabaseVariable.db_user.rawQuery("SELECT * FROM User", null);
            if (rawQuery9.getCount() != 0) {
                String[] strArr6 = new String[40];
                int i43 = 0;
                while (rawQuery9.moveToNext()) {
                    int i44 = i43 + 1;
                    strArr6[i43] = rawQuery9.getString(0);
                    i43 = i44 + 1;
                    strArr6[i44] = rawQuery9.getString(1);
                    number_of_user++;
                }
                byte[] bArr23 = bufferFile;
                bArr23[0] = 33;
                bArr23[1] = (byte) number_of_user;
                for (int i45 = 0; i45 < rawQuery9.getCount(); i45++) {
                    int i46 = i45 * 2;
                    byte[] bytes11 = strArr6[i46 + 0].getBytes();
                    byte[] bytes12 = strArr6[i46 + 1].getBytes();
                    int length4 = bytes11.length;
                    if (lockdb_getversion.equals("3.1") || lockdb_getversion.equals("3.2")) {
                        byte[] bArr24 = bufferFile;
                        bArr24[2] = bytes[0];
                        bArr24[3] = bytes[1];
                        bArr24[4] = bytes[2];
                        bArr24[5] = bytes[3];
                        bArr24[6] = bytes[4];
                        bArr24[7] = bytes[5];
                        int i47 = length4;
                        int i48 = 0;
                        int i49 = 20;
                        while (i48 < i49) {
                            if (i48 < i47) {
                                bufferFile[i48 + 8 + (i45 * 28)] = bytes11[i48];
                                i2 = i47;
                                i3 = i48;
                                i = 20;
                            } else {
                                i = 20;
                                while (i47 < 20) {
                                    bufferFile[i47 + 8 + (i45 * 28)] = -1;
                                    i47++;
                                }
                                i2 = i47;
                                i3 = 20;
                            }
                            int i50 = i2;
                            i49 = i;
                            i48 = i3 + 1;
                            i47 = i50;
                        }
                        for (int i51 = 0; i51 < 6; i51++) {
                            bufferFile[i51 + 28 + (i45 * 28)] = bytes12[i51];
                        }
                    } else {
                        int i52 = 0;
                        while (i52 < 20) {
                            if (i52 < length4) {
                                bufferFile[i52 + 2 + (i45 * 28)] = bytes11[i52];
                            } else {
                                while (length4 < 20) {
                                    bufferFile[length4 + 2 + (i45 * 28)] = -1;
                                    length4++;
                                }
                                i52 = 20;
                            }
                            i52++;
                        }
                        for (int i53 = 0; i53 < 6; i53++) {
                            bufferFile[i53 + 22 + (i45 * 28)] = bytes12[i53];
                        }
                    }
                }
            } else {
                byte[] bArr25 = bufferFile;
                bArr25[0] = 33;
                bArr25[1] = (byte) number_of_user;
                while (i7 < total_byte - 3) {
                    bufferFile[i7 + 2] = -1;
                    i7++;
                }
            }
        }
        return bufferFile;
    }

    /* JADX WARN: Removed duplicated region for block: B:139:0x02f8  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x0309  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x03b5 A[LOOP:1: B:156:0x03b3->B:157:0x03b5, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:193:0x04f1  */
    /* JADX WARN: Removed duplicated region for block: B:194:0x0503  */
    /* JADX WARN: Removed duplicated region for block: B:211:0x05b0 A[LOOP:3: B:210:0x05ae->B:211:0x05b0, LOOP_END] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean process_incoming_data(byte[] r27) {
        java.lang.String r3;
        java.lang.String r4;
        java.lang.String r7;
        java.lang.String r10;
        byte r5;
        java.lang.String r20;
        java.lang.String r4;
        int r3;
        java.lang.String r3;
        int r7;
        java.lang.Double r22;
        java.lang.String r21;
        java.lang.Double r23;
        int r4;
        int r7;
        int r8;
        int r9;
        int r10;
        java.lang.String r21;
        java.lang.String r5;
        int r4;
        java.lang.String r1;
        int r6;
        java.lang.Double r23;
        java.lang.Double r24;
        int r5;
        int r6;
        int r7;
        int r9;
        int r13;
        int r0 = com.egeetouch.egeetouch_manager.TaskManagement.current_function;
        if (r0 == 48) {
            com.egeetouch.egeetouch_manager.Fragment_pairing.has_shown = false;
            com.egeetouch.egeetouch_manager.TaskManagement.password_varified = true;
            com.egeetouch.egeetouch_manager.TaskManagement.updated_flag = false;
            if ((r27[0] & 240) == -80) {
                com.egeetouch.egeetouch_manager.MainActivity.low_batt = true;
            } else {
                com.egeetouch.egeetouch_manager.MainActivity.low_batt = false;
            }
            if (r27[0] == -96 || r27[0] == -80) {
                com.egeetouch.egeetouch_manager.TaskManagement.password_varified_first_time = false;
                com.egeetouch.egeetouch_manager.TaskManagement.password_varified_correct = true;
                com.egeetouch.egeetouch_manager.MainActivity.is_admin = true;
                java.lang.String r0 = java.lang.String.valueOf((int) r27[1]);
                com.egeetouch.egeetouch_manager.TaskManagement.version_detected = r0;
                com.egeetouch.egeetouch_manager.MainActivity.version_number = java.lang.Double.valueOf(java.lang.Double.parseDouble(r0));
                com.egeetouch.egeetouch_manager.MainActivity.version_number = java.lang.Double.valueOf(com.egeetouch.egeetouch_manager.MainActivity.version_number.doubleValue() / 10.0d);
                com.egeetouch.egeetouch_manager.DatabaseVariable.db_lock.execSQL("UPDATE " + com.egeetouch.egeetouch_manager.DatabaseVariable.Element_name_lock + " SET " + com.egeetouch.egeetouch_manager.DatabaseVariable.D4_lock + " = '" + java.lang.String.valueOf(com.egeetouch.egeetouch_manager.MainActivity.version_number) + "' WHERE " + com.egeetouch.egeetouch_manager.DatabaseVariable.D1_lock + "=" + android.database.DatabaseUtils.sqlEscapeString(com.egeetouch.egeetouch_manager.BLEService.selected_lock_name));
                return false;
            } else if (r27[0] == -95 || r27[0] == -79) {
                com.egeetouch.egeetouch_manager.TaskManagement.password_varified_first_time = true;
                com.egeetouch.egeetouch_manager.TaskManagement.password_varified_correct = true;
                com.egeetouch.egeetouch_manager.MainActivity.is_admin = true;
                java.lang.String r0 = java.lang.String.valueOf((int) r27[1]);
                com.egeetouch.egeetouch_manager.TaskManagement.version_detected = r0;
                com.egeetouch.egeetouch_manager.MainActivity.version_number = java.lang.Double.valueOf(java.lang.Double.parseDouble(r0));
                com.egeetouch.egeetouch_manager.MainActivity.version_number = java.lang.Double.valueOf(com.egeetouch.egeetouch_manager.MainActivity.version_number.doubleValue() / 10.0d);
                com.egeetouch.egeetouch_manager.DatabaseVariable.db_lock.execSQL("UPDATE " + com.egeetouch.egeetouch_manager.DatabaseVariable.Element_name_lock + " SET " + com.egeetouch.egeetouch_manager.DatabaseVariable.D4_lock + " = '" + java.lang.String.valueOf(com.egeetouch.egeetouch_manager.MainActivity.version_number) + "' WHERE " + com.egeetouch.egeetouch_manager.DatabaseVariable.D1_lock + "=" + android.database.DatabaseUtils.sqlEscapeString(com.egeetouch.egeetouch_manager.BLEService.selected_lock_name));
                return false;
            } else if (r27[0] == -94 || r27[0] == -78) {
                com.egeetouch.egeetouch_manager.TaskManagement.password_varified_first_time = false;
                com.egeetouch.egeetouch_manager.TaskManagement.password_varified_correct = true;
                com.egeetouch.egeetouch_manager.MainActivity.is_admin = false;
                java.lang.String r0 = java.lang.String.valueOf((int) r27[1]);
                com.egeetouch.egeetouch_manager.TaskManagement.version_detected = r0;
                com.egeetouch.egeetouch_manager.MainActivity.version_number = java.lang.Double.valueOf(java.lang.Double.parseDouble(r0));
                com.egeetouch.egeetouch_manager.MainActivity.version_number = java.lang.Double.valueOf(com.egeetouch.egeetouch_manager.MainActivity.version_number.doubleValue() / 10.0d);
                com.egeetouch.egeetouch_manager.DatabaseVariable.db_lock.execSQL("UPDATE " + com.egeetouch.egeetouch_manager.DatabaseVariable.Element_name_lock + " SET " + com.egeetouch.egeetouch_manager.DatabaseVariable.D4_lock + " = '" + java.lang.String.valueOf(com.egeetouch.egeetouch_manager.MainActivity.version_number) + "' WHERE " + com.egeetouch.egeetouch_manager.DatabaseVariable.D1_lock + "=" + android.database.DatabaseUtils.sqlEscapeString(com.egeetouch.egeetouch_manager.BLEService.selected_lock_name));
                return false;
            } else if (r27[0] == -93 || r27[0] == -77) {
                com.egeetouch.egeetouch_manager.TaskManagement.password_varified_first_time = false;
                com.egeetouch.egeetouch_manager.TaskManagement.password_varified_correct = false;
                com.egeetouch.egeetouch_manager.MainActivity.incorrect_pssword = true;
                return false;
            }
        } else if (r0 == 81) {
            if (r27[0] == -96 && r27[1] == 0) {
                if (com.egeetouch.egeetouch_manager.DatabaseVariable.lockdb_getversion(com.egeetouch.egeetouch_manager.BLEService.selected_lock_name).equals("3.3")) {
                    com.egeetouch.egeetouch_manager.TaskManagement.current_function = 82;
                    com.egeetouch.egeetouch_manager.TaskManagement.admin_update_done = true;
                    com.egeetouch.egeetouch_manager.TaskManagement.admin_update_done_wrong_lock = false;
                } else {
                    com.egeetouch.egeetouch_manager.TaskManagement.admin_update_done = true;
                    com.egeetouch.egeetouch_manager.TaskManagement.admin_update_done_wrong_lock = false;
                }
                return false;
            } else if (r27[0] == -93 || r27[0] == -77) {
                com.egeetouch.egeetouch_manager.TaskManagement.admin_update_done = true;
                com.egeetouch.egeetouch_manager.TaskManagement.admin_update_done_wrong_lock = true;
                return false;
            }
        } else if (r0 == 82) {
            if (r27[0] == -96 && r27[1] == 0) {
                if (com.egeetouch.egeetouch_manager.DatabaseVariable.lockdb_getversion(com.egeetouch.egeetouch_manager.BLEService.selected_lock_name).equals("3.3")) {
                    com.egeetouch.egeetouch_manager.TaskManagement.current_function = 83;
                    com.egeetouch.egeetouch_manager.TaskManagement.admin_update2_done = true;
                    com.egeetouch.egeetouch_manager.TaskManagement.admin_update_done_wrong_lock = false;
                } else {
                    com.egeetouch.egeetouch_manager.TaskManagement.admin_update_done = true;
                    com.egeetouch.egeetouch_manager.TaskManagement.admin_update_done_wrong_lock = false;
                }
                return false;
            } else if (r27[0] == -93 || r27[0] == -77) {
                com.egeetouch.egeetouch_manager.TaskManagement.admin_update_done = true;
                com.egeetouch.egeetouch_manager.TaskManagement.admin_update_done_wrong_lock = true;
                return false;
            }
        } else if (r0 == 83) {
            if (r27[0] == -96 && r27[1] == 0) {
                com.egeetouch.egeetouch_manager.TaskManagement.admin_update_done = true;
                com.egeetouch.egeetouch_manager.TaskManagement.admin_update2_done = true;
                com.egeetouch.egeetouch_manager.TaskManagement.admin_update3_done = true;
                com.egeetouch.egeetouch_manager.TaskManagement.admin_update_done_wrong_lock = false;
                com.egeetouch.egeetouch_manager.TaskManagement.updated_flag = true;
                return false;
            } else if (r27[0] == -93 || r27[0] == -77) {
                com.egeetouch.egeetouch_manager.TaskManagement.admin_update_done = true;
                com.egeetouch.egeetouch_manager.TaskManagement.admin_update_done_wrong_lock = true;
                com.egeetouch.egeetouch_manager.TaskManagement.updated_flag = true;
                return false;
            }
        } else if (r0 == 130) {
            if (r27[0] == -96 && r27[1] == 0) {
                com.egeetouch.egeetouch_manager.TaskManagement.admin_restore_done = true;
                return false;
            }
        } else if (r0 == 33) {
            android.util.Log.i("", "Update_user");
            if (r27[0] == -96 && r27[1] == 0) {
                android.util.Log.i("", "Update_user_correct");
                com.egeetouch.egeetouch_manager.TaskManagement.user_update_done = true;
                com.egeetouch.egeetouch_manager.TaskManagement.updated_flag = true;
                return false;
            }
        } else if (r0 != 16) {
            byte r10 = 40;
            byte r1 = -1;
            if (r0 == 131) {
                com.egeetouch.egeetouch_manager.TaskManagement.password_varified = true;
                java.lang.Double.valueOf((double) com.google.firebase.remoteconfig.FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
                java.lang.Double.valueOf((double) com.google.firebase.remoteconfig.FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
                int r0 = 0;
                while (r0 < 10) {
                    int r3 = r0 * 23;
                    int r4 = r3 + 23;
                    if (r27[r4] != r1 && r27[r4] > r10) {
                        r5 = "Tag" + java.lang.String.valueOf(r27[r4] - r10) + " ";
                    } else if (r27[r4] != r1 && r27[r4] == 0) {
                        r5 = "Admin(You)";
                    } else if (r27[r4] != r1 && r27[r4] > 0 && r27[r4] <= r10) {
                        r5 = java.lang.String.valueOf((int) r27[r4]);
                    } else {
                        r21 = "";
                        if (r27[r4] <= r10) {
                            r23 = java.lang.Double.valueOf((double) com.google.firebase.remoteconfig.FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
                            r24 = java.lang.Double.valueOf((double) com.google.firebase.remoteconfig.FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
                            r1 = "            ------             ------";
                        } else {
                            if (r27[r3 + 1] != r1) {
                                if (r27[r3 + 2] != r1) {
                                    if (r27[r3 + 3] != r1) {
                                        if (r27[r3 + 4] != r1) {
                                            if (r27[r3 + 5] != r1) {
                                                if (r27[r3 + 6] != r1) {
                                                    r1 = "20" + round_2_digit(java.lang.Byte.valueOf(r27[r13])) + "/" + round_2_digit(java.lang.Byte.valueOf(r27[r9])) + "/" + round_2_digit(java.lang.Byte.valueOf(r27[r7])) + "   " + round_2_digit(java.lang.Byte.valueOf(r27[r4])) + ":" + round_2_digit(java.lang.Byte.valueOf(r27[r5])) + ":" + round_2_digit(java.lang.Byte.valueOf(r27[r6]));
                                                    byte[] r4 = new byte[8];
                                                    byte[] r5 = new byte[8];
                                                    for (r6 = 0; r6 < 8; r6++) {
                                                        r4[r6] = r27[r6 + 7 + r3];
                                                        r5[r6] = r27[r6 + 15 + r3];
                                                        android.util.Log.i("abc", r6 + ":" + java.lang.String.valueOf((int) r4[r6]) + " " + java.lang.String.valueOf((int) r5[r6]));
                                                    }
                                                    java.lang.Double r3 = java.lang.Double.valueOf(java.nio.ByteBuffer.wrap(r4).getDouble());
                                                    java.lang.Double r6 = java.lang.Double.valueOf(java.nio.ByteBuffer.wrap(r5).getDouble());
                                                    android.util.Log.i("abc2", java.nio.ByteBuffer.wrap(r4) + " " + java.nio.ByteBuffer.wrap(r5));
                                                    android.util.Log.i("abc", r3 + " " + r6);
                                                    r23 = r3;
                                                    r24 = r6;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            r1 = "";
                            byte[] r4 = new byte[8];
                            byte[] r5 = new byte[8];
                            while (r6 < 8) {
                            }
                            java.lang.Double r3 = java.lang.Double.valueOf(java.nio.ByteBuffer.wrap(r4).getDouble());
                            java.lang.Double r6 = java.lang.Double.valueOf(java.nio.ByteBuffer.wrap(r5).getDouble());
                            android.util.Log.i("abc2", java.nio.ByteBuffer.wrap(r4) + " " + java.nio.ByteBuffer.wrap(r5));
                            android.util.Log.i("abc", r3 + " " + r6);
                            r23 = r3;
                            r24 = r6;
                        }
                        java.lang.String r25 = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(java.util.Calendar.getInstance().getTime());
                        android.util.Log.i("", "Date_Time: " + r1);
                        com.egeetouch.egeetouch_manager.DatabaseVariable.db_audittrail.execSQL(com.egeetouch.egeetouch_manager.DatabaseVariable.db_audittrail_update_value(r0, r21, r1, r23, r24, r25, com.egeetouch.egeetouch_manager.BLEService.selected_lock_name));
                        r0++;
                        r1 = -1;
                        r10 = 40;
                    }
                    r21 = r5;
                    if (r27[r4] <= r10) {
                    }
                    java.lang.String r25 = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(java.util.Calendar.getInstance().getTime());
                    android.util.Log.i("", "Date_Time: " + r1);
                    com.egeetouch.egeetouch_manager.DatabaseVariable.db_audittrail.execSQL(com.egeetouch.egeetouch_manager.DatabaseVariable.db_audittrail_update_value(r0, r21, r1, r23, r24, r25, com.egeetouch.egeetouch_manager.BLEService.selected_lock_name));
                    r0++;
                    r1 = -1;
                    r10 = 40;
                }
                if (r27[232] == -15) {
                    try {
                        com.egeetouch.egeetouch_manager.TaskManagement.log_extract10_done = true;
                        return false;
                    } catch (java.lang.Exception r0) {
                        r0.printStackTrace();
                    }
                }
            } else if (r0 == 132) {
                java.lang.Double.valueOf((double) com.google.firebase.remoteconfig.FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
                java.lang.Double.valueOf((double) com.google.firebase.remoteconfig.FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
                for (int r0 = 0; r0 < 10; r0++) {
                    int r1 = r0 * 23;
                    int r3 = r1 + 23;
                    if (r27[r3] != -1 && r27[r3] > 40) {
                        r4 = java.lang.String.valueOf((int) r27[r3]);
                    } else if (r27[r3] == -1 || r27[r3] != 0) {
                        if (r27[r3] == -1 || r27[r3] <= 0) {
                            r5 = 40;
                        } else {
                            r5 = 40;
                            if (r27[r3] <= 40) {
                                r20 = java.lang.String.valueOf((int) r27[r3]);
                                if (r27[r3] > r5) {
                                    r22 = java.lang.Double.valueOf((double) com.google.firebase.remoteconfig.FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
                                    r23 = java.lang.Double.valueOf((double) com.google.firebase.remoteconfig.FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
                                    r21 = "            ------             ------";
                                } else {
                                    if (r27[r1 + 1] != -1) {
                                        if (r27[r1 + 2] != -1) {
                                            if (r27[r1 + 3] != -1) {
                                                if (r27[r1 + 4] != -1) {
                                                    if (r27[r1 + 5] != -1) {
                                                        if (r27[r1 + 6] != -1) {
                                                            r3 = "20" + round_2_digit(java.lang.Byte.valueOf(r27[r10])) + "/" + round_2_digit(java.lang.Byte.valueOf(r27[r9])) + "/" + round_2_digit(java.lang.Byte.valueOf(r27[r8])) + "   " + round_2_digit(java.lang.Byte.valueOf(r27[r3])) + ":" + round_2_digit(java.lang.Byte.valueOf(r27[r4])) + ":" + round_2_digit(java.lang.Byte.valueOf(r27[r7]));
                                                            byte[] r4 = new byte[8];
                                                            byte[] r6 = new byte[8];
                                                            for (r7 = 0; r7 < 8; r7++) {
                                                                r4[r7] = r27[r7 + 7 + r1];
                                                                r6[r7] = r27[r7 + 15 + r1];
                                                            }
                                                            r22 = java.lang.Double.valueOf(java.nio.ByteBuffer.wrap(r4).getDouble());
                                                            r21 = r3;
                                                            r23 = java.lang.Double.valueOf(java.nio.ByteBuffer.wrap(r6).getDouble());
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    r3 = "";
                                    byte[] r4 = new byte[8];
                                    byte[] r6 = new byte[8];
                                    while (r7 < 8) {
                                    }
                                    r22 = java.lang.Double.valueOf(java.nio.ByteBuffer.wrap(r4).getDouble());
                                    r21 = r3;
                                    r23 = java.lang.Double.valueOf(java.nio.ByteBuffer.wrap(r6).getDouble());
                                }
                                com.egeetouch.egeetouch_manager.DatabaseVariable.db_audittrail.execSQL(com.egeetouch.egeetouch_manager.DatabaseVariable.db_audittrail_update_value(r0 + 10, r20, r21, r22, r23, new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(java.util.Calendar.getInstance().getTime()), com.egeetouch.egeetouch_manager.BLEService.selected_lock_name));
                            }
                        }
                        r20 = "";
                        if (r27[r3] > r5) {
                        }
                        com.egeetouch.egeetouch_manager.DatabaseVariable.db_audittrail.execSQL(com.egeetouch.egeetouch_manager.DatabaseVariable.db_audittrail_update_value(r0 + 10, r20, r21, r22, r23, new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(java.util.Calendar.getInstance().getTime()), com.egeetouch.egeetouch_manager.BLEService.selected_lock_name));
                    } else {
                        r4 = "Admin(You)";
                    }
                    r20 = r4;
                    r5 = 40;
                    if (r27[r3] > r5) {
                    }
                    com.egeetouch.egeetouch_manager.DatabaseVariable.db_audittrail.execSQL(com.egeetouch.egeetouch_manager.DatabaseVariable.db_audittrail_update_value(r0 + 10, r20, r21, r22, r23, new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(java.util.Calendar.getInstance().getTime()), com.egeetouch.egeetouch_manager.BLEService.selected_lock_name));
                }
                if (r27[232] == -14) {
                    try {
                        com.egeetouch.egeetouch_manager.TaskManagement.log_extract20_done = true;
                        com.egeetouch.egeetouch_manager.TaskManagement.updated_flag = true;
                        return false;
                    } catch (java.lang.Exception r0) {
                        r0.printStackTrace();
                    }
                }
            } else if (r0 == 80) {
                if ((r27[0] == -93 && r27[1] == -93) || ((r27[0] == -95 && r27[1] == -93) || (r27[0] == -77 && r27[1] == -77))) {
                    com.egeetouch.egeetouch_manager.MainActivity.low_batt = true;
                } else {
                    com.egeetouch.egeetouch_manager.MainActivity.low_batt = false;
                }
                if (r27[0] == -96 && r27[1] == -96) {
                    com.egeetouch.egeetouch_manager.TaskManagement.admin_varified = true;
                    com.egeetouch.egeetouch_manager.TaskManagement.admin_varified_first_time = false;
                    com.egeetouch.egeetouch_manager.TaskManagement.admin_varified_correct = true;
                    com.egeetouch.egeetouch_manager.TaskManagement.updated_flag = false;
                    com.egeetouch.egeetouch_manager.MainActivity.version_number = java.lang.Double.valueOf(2.0d);
                    com.egeetouch.egeetouch_manager.DatabaseVariable.db_lock.execSQL("UPDATE " + com.egeetouch.egeetouch_manager.DatabaseVariable.Element_name_lock + " SET " + com.egeetouch.egeetouch_manager.DatabaseVariable.D4_lock + " = '" + java.lang.String.valueOf(com.egeetouch.egeetouch_manager.MainActivity.version_number) + "' WHERE " + com.egeetouch.egeetouch_manager.DatabaseVariable.D1_lock + "=" + android.database.DatabaseUtils.sqlEscapeString(com.egeetouch.egeetouch_manager.BLEService.selected_lock_name));
                    return false;
                } else if ((r27[0] == -93 && r27[1] == -93) || (r27[0] == -95 && r27[1] == -95)) {
                    com.egeetouch.egeetouch_manager.TaskManagement.admin_varified = true;
                    com.egeetouch.egeetouch_manager.TaskManagement.admin_varified_first_time = true;
                    com.egeetouch.egeetouch_manager.TaskManagement.admin_varified_correct = true;
                    com.egeetouch.egeetouch_manager.TaskManagement.updated_flag = false;
                    com.egeetouch.egeetouch_manager.MainActivity.version_number = java.lang.Double.valueOf(2.0d);
                    com.egeetouch.egeetouch_manager.DatabaseVariable.db_lock.execSQL("UPDATE " + com.egeetouch.egeetouch_manager.DatabaseVariable.Element_name_lock + " SET " + com.egeetouch.egeetouch_manager.DatabaseVariable.D4_lock + " = '" + java.lang.String.valueOf(com.egeetouch.egeetouch_manager.MainActivity.version_number) + "' WHERE " + com.egeetouch.egeetouch_manager.DatabaseVariable.D1_lock + "=" + android.database.DatabaseUtils.sqlEscapeString(com.egeetouch.egeetouch_manager.BLEService.selected_lock_name));
                    return false;
                } else if ((r27[0] == -80 && r27[1] == -80) || (r27[0] == -77 && r27[1] == -77)) {
                    com.egeetouch.egeetouch_manager.TaskManagement.admin_varified = true;
                    com.egeetouch.egeetouch_manager.TaskManagement.admin_varified_first_time = false;
                    com.egeetouch.egeetouch_manager.TaskManagement.admin_varified_correct = false;
                    com.egeetouch.egeetouch_manager.TaskManagement.updated_flag = false;
                    com.egeetouch.egeetouch_manager.MainActivity.version_number = java.lang.Double.valueOf(2.0d);
                    com.egeetouch.egeetouch_manager.DatabaseVariable.db_lock.execSQL("UPDATE " + com.egeetouch.egeetouch_manager.DatabaseVariable.Element_name_lock + " SET " + com.egeetouch.egeetouch_manager.DatabaseVariable.D4_lock + " = '" + java.lang.String.valueOf(com.egeetouch.egeetouch_manager.MainActivity.version_number) + "' WHERE " + com.egeetouch.egeetouch_manager.DatabaseVariable.D1_lock + "=" + android.database.DatabaseUtils.sqlEscapeString(com.egeetouch.egeetouch_manager.BLEService.selected_lock_name));
                    return false;
                }
            } else if (r0 == 116) {
                if (r27[61] == -96 && r27[62] == 0) {
                    com.egeetouch.egeetouch_manager.TaskManagement.lock_detail_updated = true;
                }
            } else if (r0 == 112) {
                if (r27[0] == -96 && r27[1] == 0) {
                    com.egeetouch.egeetouch_manager.TaskManagement.setting_update_done = true;
                }
            } else if (r0 == 115) {
                if (r27[0] == -96 && r27[1] == 0) {
                    com.egeetouch.egeetouch_manager.TaskManagement.setting_version_battery_done = true;
                }
            } else if (r0 != 133 && r0 != 134 && r0 == 128) {
                if (r27[0] == -1) {
                    r27[0] = 3;
                }
                if (r27[0] > 0 && r27[0] < 20) {
                    for (int r0 = 0; r0 < r27[0]; r0++) {
                        int r2 = r0 * 14;
                        int r3 = r2 + 1;
                        if (r27[r3] != -1) {
                            int r4 = r2 + 2;
                            if (r27[r4] != -1) {
                                int r2 = r2 + 3;
                                if (r27[r2] != -1) {
                                    byte r3 = r27[r3];
                                    if (r3 < 0) {
                                        r3 = java.lang.Integer.toHexString(r3 + kotlin.UByte.MIN_VALUE);
                                    } else {
                                        r3 = java.lang.Integer.toHexString(r3);
                                    }
                                    byte r4 = r27[r4];
                                    if (r4 < 0) {
                                        r4 = java.lang.Integer.toHexString(r4 + kotlin.UByte.MIN_VALUE);
                                    } else {
                                        r4 = java.lang.Integer.toHexString(r4);
                                    }
                                    if (r3.length() == 1) {
                                        r3 = com.facebook.appevents.AppEventsConstants.EVENT_PARAM_VALUE_NO + r3;
                                    }
                                    if (r4.length() == 1) {
                                        r4 = com.facebook.appevents.AppEventsConstants.EVENT_PARAM_VALUE_NO + r4;
                                    }
                                    java.lang.String r6 = " " + r3 + " " + r4 + " ";
                                    java.lang.String r9 = "";
                                    for (int r7 = 0; r7 < 12; r7++) {
                                        int r10 = r2 + r7;
                                        if (r27[r10] > 32 && r27[r10] < Byte.MAX_VALUE) {
                                            r9 = r9 + java.lang.Character.toString((char) r27[r10]);
                                        }
                                    }
                                    try {
                                        if (com.egeetouch.egeetouch_manager.DatabaseVariable.db_tag.rawQuery(com.egeetouch.egeetouch_manager.DatabaseVariable.tagdb_Query_UID_exist2(r9, com.egeetouch.egeetouch_manager.BLEService.selected_lock_name), null).moveToNext()) {
                                            android.database.Cursor r2 = com.egeetouch.egeetouch_manager.DatabaseVariable.db_tag.rawQuery("SELECT * FROM Tag WHERE Lock = " + android.database.DatabaseUtils.sqlEscapeString(com.egeetouch.egeetouch_manager.BLEService.selected_lock_name) + " AND TagName =" + android.database.DatabaseUtils.sqlEscapeString(r9), null);
                                            if (r2.getCount() != 0) {
                                                java.lang.String[] r7 = new java.lang.String[35];
                                                int r10 = -1;
                                                while (r2.moveToNext()) {
                                                    r10++;
                                                    r7[r10] = r2.getString(1);
                                                }
                                                byte[] r10 = new byte[7];
                                                for (int r13 = 0; r13 < r2.getCount(); r13++) {
                                                    java.lang.String[] r15 = r7[r13].split(" ");
                                                    for (int r5 = 1; r5 < 3; r5++) {
                                                        r10[r5] = com.egeetouch.egeetouch_manager.Helper_NFC.ConvertStringToHexByte(r15[r5]);
                                                    }
                                                }
                                                int r5 = r10[1];
                                                if (r5 < 0) {
                                                    r5 += 256;
                                                    r7 = java.lang.Integer.toHexString(r5);
                                                } else {
                                                    r7 = java.lang.Integer.toHexString(r5);
                                                }
                                                if (r5 < 16) {
                                                    r7 = com.facebook.appevents.AppEventsConstants.EVENT_PARAM_VALUE_NO + r7;
                                                }
                                                int r5 = r10[2];
                                                if (r5 < 0) {
                                                    r5 += 256;
                                                    r10 = java.lang.Integer.toHexString(r5);
                                                } else {
                                                    r10 = java.lang.Integer.toHexString(r5);
                                                }
                                                if (r5 < 16) {
                                                    r10 = com.facebook.appevents.AppEventsConstants.EVENT_PARAM_VALUE_NO + r10;
                                                }
                                                if (!r7.equals(r3) || !r10.equals(r4)) {
                                                    com.egeetouch.egeetouch_manager.DatabaseVariable.db_tag.execSQL(com.egeetouch.egeetouch_manager.DatabaseVariable.tagdb_insert_value(r9 + java.lang.String.valueOf(r2.getCount() + 1), r6, com.egeetouch.egeetouch_manager.BLEService.selected_lock_name));
                                                }
                                            }
                                        } else {
                                            com.egeetouch.egeetouch_manager.DatabaseVariable.db_tag.execSQL(com.egeetouch.egeetouch_manager.DatabaseVariable.tagdb_insert_value(r9, r6, com.egeetouch.egeetouch_manager.BLEService.selected_lock_name));
                                        }
                                    } catch (java.lang.Exception unused) {
                                    }
                                }
                            }
                        }
                        return false;
                    }
                }
                com.egeetouch.egeetouch_manager.TaskManagement.tag_restore_done = true;
            }
        } else if (r27[0] == -96 && r27[1] == 0) {
            com.egeetouch.egeetouch_manager.TaskManagement.tag_update_done = true;
            com.egeetouch.egeetouch_manager.TaskManagement.updated_flag = true;
            return false;
        } else if (r27[0] == -93 || r27[0] == -77) {
            com.egeetouch.egeetouch_manager.TaskManagement.tag_update_done = true;
            com.egeetouch.egeetouch_manager.TaskManagement.updated_flag = true;
            com.egeetouch.egeetouch_manager.TaskManagement.tag_update_done_wrong_lock = true;
            return false;
        }
        return true;
    }

    private static String round_2_digit(Byte b) {
        if (b.byteValue() < 10) {
            return AppEventsConstants.EVENT_PARAM_VALUE_NO + String.valueOf(b);
        }
        return String.valueOf(b);
    }
}
