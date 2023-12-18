package no.nordicsemi.android.dfu;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.content.Intent;
import java.util.UUID;
import no.nordicsemi.android.dfu.BaseCustomDfuImpl;
import no.nordicsemi.android.dfu.internal.exception.DeviceDisconnectedException;
import no.nordicsemi.android.dfu.internal.exception.DfuException;
import no.nordicsemi.android.dfu.internal.exception.UnknownResponseException;
import no.nordicsemi.android.dfu.internal.exception.UploadAbortedException;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class LegacyDfuImpl extends BaseCustomDfuImpl {
    static final UUID DEFAULT_DFU_CONTROL_POINT_UUID;
    static final UUID DEFAULT_DFU_PACKET_UUID;
    static final UUID DEFAULT_DFU_SERVICE_UUID;
    static final UUID DEFAULT_DFU_VERSION_UUID;
    static UUID DFU_CONTROL_POINT_UUID = null;
    static UUID DFU_PACKET_UUID = null;
    static UUID DFU_SERVICE_UUID = null;
    private static final int DFU_STATUS_SUCCESS = 1;
    static UUID DFU_VERSION_UUID = null;
    private static final byte[] OP_CODE_ACTIVATE_AND_RESET;
    private static final int OP_CODE_ACTIVATE_AND_RESET_KEY = 5;
    private static final byte[] OP_CODE_INIT_DFU_PARAMS;
    private static final byte[] OP_CODE_INIT_DFU_PARAMS_COMPLETE;
    private static final int OP_CODE_INIT_DFU_PARAMS_KEY = 2;
    private static final byte[] OP_CODE_INIT_DFU_PARAMS_START;
    private static final int OP_CODE_PACKET_RECEIPT_NOTIF_KEY = 17;
    private static final byte[] OP_CODE_PACKET_RECEIPT_NOTIF_REQ;
    private static final int OP_CODE_PACKET_RECEIPT_NOTIF_REQ_KEY = 8;
    private static final byte[] OP_CODE_RECEIVE_FIRMWARE_IMAGE;
    private static final int OP_CODE_RECEIVE_FIRMWARE_IMAGE_KEY = 3;
    private static final byte[] OP_CODE_RESET;
    private static final int OP_CODE_RESET_KEY = 6;
    private static final int OP_CODE_RESPONSE_CODE_KEY = 16;
    private static final byte[] OP_CODE_START_DFU;
    private static final int OP_CODE_START_DFU_KEY = 1;
    private static final byte[] OP_CODE_START_DFU_V1;
    private static final byte[] OP_CODE_VALIDATE;
    private static final int OP_CODE_VALIDATE_KEY = 4;
    private final LegacyBluetoothCallback mBluetoothCallback;
    private BluetoothGattCharacteristic mControlPointCharacteristic;
    private boolean mImageSizeInProgress;
    private BluetoothGattCharacteristic mPacketCharacteristic;

    static {
        UUID uuid = new UUID(23296205844446L, 1523193452336828707L);
        DEFAULT_DFU_SERVICE_UUID = uuid;
        UUID uuid2 = new UUID(23300500811742L, 1523193452336828707L);
        DEFAULT_DFU_CONTROL_POINT_UUID = uuid2;
        UUID uuid3 = new UUID(23304795779038L, 1523193452336828707L);
        DEFAULT_DFU_PACKET_UUID = uuid3;
        UUID uuid4 = new UUID(23313385713630L, 1523193452336828707L);
        DEFAULT_DFU_VERSION_UUID = uuid4;
        DFU_SERVICE_UUID = uuid;
        DFU_CONTROL_POINT_UUID = uuid2;
        DFU_PACKET_UUID = uuid3;
        DFU_VERSION_UUID = uuid4;
        OP_CODE_START_DFU = new byte[]{1, 0};
        OP_CODE_START_DFU_V1 = new byte[]{1};
        OP_CODE_INIT_DFU_PARAMS = new byte[]{2};
        OP_CODE_INIT_DFU_PARAMS_START = new byte[]{2, 0};
        OP_CODE_INIT_DFU_PARAMS_COMPLETE = new byte[]{2, 1};
        OP_CODE_RECEIVE_FIRMWARE_IMAGE = new byte[]{3};
        OP_CODE_VALIDATE = new byte[]{4};
        OP_CODE_ACTIVATE_AND_RESET = new byte[]{5};
        OP_CODE_RESET = new byte[]{6};
        OP_CODE_PACKET_RECEIPT_NOTIF_REQ = new byte[]{8, 0, 0};
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes2.dex */
    public class LegacyBluetoothCallback extends BaseCustomDfuImpl.BaseCustomBluetoothCallback {
        protected LegacyBluetoothCallback() {
            super();
        }

        @Override // no.nordicsemi.android.dfu.BaseCustomDfuImpl.BaseCustomBluetoothCallback
        protected void onPacketCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            if (LegacyDfuImpl.this.mImageSizeInProgress) {
                LegacyDfuImpl.this.mService.sendLogBroadcast(5, "Data written to " + bluetoothGattCharacteristic.getUuid() + ", value (0x): " + parse(bluetoothGattCharacteristic));
                LegacyDfuImpl.this.mImageSizeInProgress = false;
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            if (bluetoothGattCharacteristic.getIntValue(17, 0).intValue() == 17) {
                LegacyDfuImpl.this.mProgressInfo.setBytesReceived(bluetoothGattCharacteristic.getIntValue(20, 1).intValue());
                handlePacketReceiptNotification(bluetoothGatt, bluetoothGattCharacteristic);
            } else if (!LegacyDfuImpl.this.mRemoteErrorOccurred) {
                if (bluetoothGattCharacteristic.getIntValue(17, 2).intValue() != 1) {
                    LegacyDfuImpl.this.mRemoteErrorOccurred = true;
                }
                handleNotification(bluetoothGatt, bluetoothGattCharacteristic);
            }
            LegacyDfuImpl.this.notifyLock();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public LegacyDfuImpl(Intent intent, DfuBaseService dfuBaseService) {
        super(intent, dfuBaseService);
        this.mBluetoothCallback = new LegacyBluetoothCallback();
    }

    @Override // no.nordicsemi.android.dfu.DfuService
    public boolean isClientCompatible(Intent intent, BluetoothGatt bluetoothGatt) {
        BluetoothGattCharacteristic characteristic;
        BluetoothGattService service = bluetoothGatt.getService(DFU_SERVICE_UUID);
        if (service == null || (characteristic = service.getCharacteristic(DFU_CONTROL_POINT_UUID)) == null || characteristic.getDescriptor(CLIENT_CHARACTERISTIC_CONFIG) == null) {
            return false;
        }
        this.mControlPointCharacteristic = characteristic;
        BluetoothGattCharacteristic characteristic2 = service.getCharacteristic(DFU_PACKET_UUID);
        this.mPacketCharacteristic = characteristic2;
        return characteristic2 != null;
    }

    @Override // no.nordicsemi.android.dfu.DfuCallback
    public BaseCustomDfuImpl.BaseCustomBluetoothCallback getGattCallback() {
        return this.mBluetoothCallback;
    }

    @Override // no.nordicsemi.android.dfu.BaseCustomDfuImpl
    protected UUID getControlPointCharacteristicUUID() {
        return DFU_CONTROL_POINT_UUID;
    }

    @Override // no.nordicsemi.android.dfu.BaseCustomDfuImpl
    protected UUID getPacketCharacteristicUUID() {
        return DFU_PACKET_UUID;
    }

    @Override // no.nordicsemi.android.dfu.BaseCustomDfuImpl
    protected UUID getDfuServiceUUID() {
        return DFU_SERVICE_UUID;
    }

    /* JADX WARN: Removed duplicated region for block: B:106:0x0416 A[Catch: UnknownResponseException -> 0x0220, UploadAbortedException -> 0x0228, RemoteDfuException -> 0x06e3, TryCatch #13 {RemoteDfuException -> 0x06e3, blocks: (B:93:0x0359, B:97:0x0363, B:99:0x040a, B:104:0x0412, B:106:0x0416, B:108:0x0421, B:110:0x0497, B:113:0x04cb, B:114:0x04d2, B:109:0x0467, B:116:0x04d5, B:118:0x04d9, B:124:0x04e7, B:125:0x052b, B:126:0x054a, B:127:0x055d, B:129:0x05c7, B:131:0x068f, B:135:0x06be, B:136:0x06c3, B:137:0x06ca, B:138:0x06cb, B:139:0x06d2, B:141:0x06d4, B:142:0x06da, B:122:0x04e3, B:143:0x06db, B:144:0x06e0, B:145:0x06e1, B:146:0x06e2), top: B:181:0x0359 }] */
    /* JADX WARN: Removed duplicated region for block: B:124:0x04e7 A[Catch: UnknownResponseException -> 0x0220, UploadAbortedException -> 0x0228, RemoteDfuException -> 0x06e3, TryCatch #13 {RemoteDfuException -> 0x06e3, blocks: (B:93:0x0359, B:97:0x0363, B:99:0x040a, B:104:0x0412, B:106:0x0416, B:108:0x0421, B:110:0x0497, B:113:0x04cb, B:114:0x04d2, B:109:0x0467, B:116:0x04d5, B:118:0x04d9, B:124:0x04e7, B:125:0x052b, B:126:0x054a, B:127:0x055d, B:129:0x05c7, B:131:0x068f, B:135:0x06be, B:136:0x06c3, B:137:0x06ca, B:138:0x06cb, B:139:0x06d2, B:141:0x06d4, B:142:0x06da, B:122:0x04e3, B:143:0x06db, B:144:0x06e0, B:145:0x06e1, B:146:0x06e2), top: B:181:0x0359 }] */
    /* JADX WARN: Removed duplicated region for block: B:129:0x05c7 A[Catch: UnknownResponseException -> 0x0220, UploadAbortedException -> 0x0228, RemoteDfuException -> 0x06e3, TryCatch #13 {RemoteDfuException -> 0x06e3, blocks: (B:93:0x0359, B:97:0x0363, B:99:0x040a, B:104:0x0412, B:106:0x0416, B:108:0x0421, B:110:0x0497, B:113:0x04cb, B:114:0x04d2, B:109:0x0467, B:116:0x04d5, B:118:0x04d9, B:124:0x04e7, B:125:0x052b, B:126:0x054a, B:127:0x055d, B:129:0x05c7, B:131:0x068f, B:135:0x06be, B:136:0x06c3, B:137:0x06ca, B:138:0x06cb, B:139:0x06d2, B:141:0x06d4, B:142:0x06da, B:122:0x04e3, B:143:0x06db, B:144:0x06e0, B:145:0x06e1, B:146:0x06e2), top: B:181:0x0359 }] */
    /* JADX WARN: Removed duplicated region for block: B:138:0x06cb A[Catch: UnknownResponseException -> 0x0220, UploadAbortedException -> 0x0228, RemoteDfuException -> 0x06e3, TryCatch #13 {RemoteDfuException -> 0x06e3, blocks: (B:93:0x0359, B:97:0x0363, B:99:0x040a, B:104:0x0412, B:106:0x0416, B:108:0x0421, B:110:0x0497, B:113:0x04cb, B:114:0x04d2, B:109:0x0467, B:116:0x04d5, B:118:0x04d9, B:124:0x04e7, B:125:0x052b, B:126:0x054a, B:127:0x055d, B:129:0x05c7, B:131:0x068f, B:135:0x06be, B:136:0x06c3, B:137:0x06ca, B:138:0x06cb, B:139:0x06d2, B:141:0x06d4, B:142:0x06da, B:122:0x04e3, B:143:0x06db, B:144:0x06e0, B:145:0x06e1, B:146:0x06e2), top: B:181:0x0359 }] */
    /* JADX WARN: Removed duplicated region for block: B:77:0x023e  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0356 A[Catch: UnknownResponseException -> 0x0220, UploadAbortedException -> 0x0228, RemoteDfuException -> 0x0357, TRY_LEAVE, TryCatch #0 {RemoteDfuException -> 0x0357, blocks: (B:75:0x0237, B:78:0x0240, B:80:0x0244, B:82:0x0343, B:87:0x034f, B:88:0x0354, B:89:0x0355, B:90:0x0356), top: B:165:0x0237 }] */
    @Override // no.nordicsemi.android.dfu.DfuService
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void performDfu(android.content.Intent r28) throws no.nordicsemi.android.dfu.internal.exception.DfuException, no.nordicsemi.android.dfu.internal.exception.DeviceDisconnectedException, no.nordicsemi.android.dfu.internal.exception.UploadAbortedException {
        java.lang.String r16;
        java.lang.String r4;
        java.lang.String r2;
        no.nordicsemi.android.dfu.internal.exception.UploadAbortedException r3;
        java.lang.String r4;
        java.lang.String r2;
        no.nordicsemi.android.dfu.internal.exception.UnknownResponseException r3;
        java.lang.String r21;
        java.lang.String r22;
        no.nordicsemi.android.dfu.internal.exception.RemoteDfuException r2;
        int r14;
        int r21;
        int r22;
        int r14;
        int r8;
        int r15;
        java.lang.String r23;
        java.lang.String r6;
        java.lang.String r4;
        boolean r3;
        int r3;
        int r9;
        no.nordicsemi.android.dfu.internal.exception.RemoteDfuException r3;
        byte[] r9;
        int r7;
        logw("Legacy DFU bootloader found");
        r27.mProgressInfo.setProgress(-2);
        if (r28.hasExtra(no.nordicsemi.android.dfu.DfuBaseService.EXTRA_MTU)) {
            r16 = ", Status = ";
            if (android.os.Build.VERSION.SDK_INT >= 21) {
                int r10 = r28.getIntExtra(no.nordicsemi.android.dfu.DfuBaseService.EXTRA_MTU, 517);
                logi("Requesting MTU = " + r10);
                requestMtu(r10);
            }
        } else {
            r16 = ", Status = ";
        }
        android.bluetooth.BluetoothGatt r10 = r27.mGatt;
        int r14 = readVersion(r10.getService(no.nordicsemi.android.dfu.LegacyDfuImpl.DFU_SERVICE_UUID).getCharacteristic(no.nordicsemi.android.dfu.LegacyDfuImpl.DFU_VERSION_UUID));
        if (r14 >= 5 && r27.mInitPacketStream == null) {
            logw("Init packet not set for the DFU Bootloader version " + r14);
            r27.mService.sendLogBroadcast(20, "The Init packet is required by this version DFU Bootloader");
            r27.mService.terminateConnection(r10, no.nordicsemi.android.dfu.DfuBaseService.ERROR_INIT_PACKET_REQUIRED);
            return;
        }
        try {
            try {
                try {
                    enableCCCD(r27.mControlPointCharacteristic, 1);
                    r27.mService.sendLogBroadcast(10, "Notifications enabled");
                    int r5 = r27.mFileType;
                    if ((r5 & 1) > 0) {
                        try {
                            r14 = r27.mImageSizeInBytes;
                        } catch (no.nordicsemi.android.dfu.internal.exception.RemoteDfuException r0) {
                            r2 = r0;
                            r21 = "Reset request sent";
                            r22 = "Sending Reset command (Op Code = 6)";
                            int r3 = r2.getErrorNumber() | 256;
                            loge(r2.getMessage() + ": " + no.nordicsemi.android.error.LegacyDfuError.parse(r3));
                            r27.mService.sendLogBroadcast(20, java.lang.String.format(java.util.Locale.US, "Remote DFU error: %s", no.nordicsemi.android.error.LegacyDfuError.parse(r3)));
                            logi(r22);
                            writeOpCode(r27.mControlPointCharacteristic, no.nordicsemi.android.dfu.LegacyDfuImpl.OP_CODE_RESET);
                            r27.mService.sendLogBroadcast(10, r21);
                            r27.mService.terminateConnection(r10, r3 | 8192);
                            return;
                        }
                    } else {
                        r14 = 0;
                    }
                    int r15 = (r5 & 2) > 0 ? r27.mImageSizeInBytes : 0;
                    int r20 = r5 & 4;
                    if (r20 > 0) {
                        r21 = r14;
                        r22 = r27.mImageSizeInBytes;
                    } else {
                        r21 = r14;
                        r22 = 0;
                    }
                    if (r27.mFirmwareStream instanceof no.nordicsemi.android.dfu.internal.ArchiveInputStream) {
                        no.nordicsemi.android.dfu.internal.ArchiveInputStream r14 = (no.nordicsemi.android.dfu.internal.ArchiveInputStream) r27.mFirmwareStream;
                        if (r14.isSecureDfuRequired()) {
                            loge("Secure DFU is required to upload selected firmware");
                            r27.mService.sendLogBroadcast(20, "The device does not support given firmware.");
                            logi("Sending Reset command (Op Code = 6)");
                            writeOpCode(r27.mControlPointCharacteristic, no.nordicsemi.android.dfu.LegacyDfuImpl.OP_CODE_RESET);
                            r27.mService.sendLogBroadcast(10, "Reset request sent");
                            r27.mService.terminateConnection(r10, 4099);
                            return;
                        }
                        int r15 = r14.softDeviceImageSize();
                        r21 = "Reset request sent";
                        r8 = r14.bootloaderImageSize();
                        r15 = r14.applicationImageSize();
                        r14 = r15;
                    } else {
                        r14 = r21;
                        r21 = "Reset request sent";
                        r8 = r15;
                        r15 = r22;
                    }
                    r22 = "Sending Reset command (Op Code = 6)";
                    try {
                        try {
                            r9 = no.nordicsemi.android.dfu.LegacyDfuImpl.OP_CODE_START_DFU;
                            r23 = "DFU target does not support (SD/BL)+App update";
                        } catch (no.nordicsemi.android.dfu.internal.exception.RemoteDfuException e) {
                            r0 = e;
                            r23 = "DFU target does not support (SD/BL)+App update";
                        }
                        try {
                            r9[1] = (byte) r5;
                            try {
                                logi("Sending Start DFU command (Op Code = 1, Upload Mode = " + r5 + ")");
                                writeOpCode(r27.mControlPointCharacteristic, r9);
                                r27.mService.sendLogBroadcast(10, "DFU Start sent (Op Code = 1, Upload Mode = " + r5 + ")");
                                logi("Sending image size array to DFU Packet (" + r14 + "b, " + r8 + "b, " + r15 + "b)");
                                writeImageSize(r27.mPacketCharacteristic, r14, r8, r15);
                                r27.mService.sendLogBroadcast(10, "Firmware image size sent (" + r14 + "b, " + r8 + "b, " + r15 + "b)");
                                byte[] r3 = readNotificationResponse();
                                r7 = getStatusCode(r3, 1);
                                r27.mService.sendLogBroadcast(10, "Response received (Op Code = " + ((int) r3[1]) + " Status = " + r7 + ")");
                            } catch (no.nordicsemi.android.dfu.internal.exception.RemoteDfuException e) {
                                r0 = e;
                                r6 = "Starting DFU failed";
                            }
                        } catch (no.nordicsemi.android.dfu.internal.exception.RemoteDfuException e) {
                            r0 = e;
                            r6 = "Starting DFU failed";
                            r3 = r0;
                            try {
                            } catch (no.nordicsemi.android.dfu.internal.exception.RemoteDfuException r0) {
                                try {
                                    if (r0.getErrorNumber() != 3) {
                                        throw r0;
                                    }
                                    if (r5 == 4) {
                                        r27.mRemoteErrorOccurred = false;
                                        logw("DFU target does not support DFU v.2");
                                        r27.mService.sendLogBroadcast(15, "DFU target does not support DFU v.2");
                                        r27.mService.sendLogBroadcast(1, "Switching to DFU v.1");
                                        logi("Resending Start DFU command (Op Code = 1)");
                                        writeOpCode(r27.mControlPointCharacteristic, no.nordicsemi.android.dfu.LegacyDfuImpl.OP_CODE_START_DFU_V1);
                                        r27.mService.sendLogBroadcast(10, "DFU Start sent (Op Code = 1)");
                                        logi("Sending application image size to DFU Packet: " + r27.mImageSizeInBytes + " bytes");
                                        writeImageSize(r27.mPacketCharacteristic, r27.mImageSizeInBytes);
                                        r27.mService.sendLogBroadcast(10, "Firmware image size sent (" + r27.mImageSizeInBytes + " bytes)");
                                        byte[] r3 = readNotificationResponse();
                                        int r5 = getStatusCode(r3, 1);
                                        r4 = r16;
                                        r27.mService.sendLogBroadcast(10, "Response received (Op Code = " + ((int) r3[1]) + r4 + r5 + ")");
                                        if (r5 == 2) {
                                            resetAndRestart(r10, r28);
                                            return;
                                        } else if (r5 != 1) {
                                            throw new no.nordicsemi.android.dfu.internal.exception.RemoteDfuException(r6, r5);
                                        } else {
                                            r3 = false;
                                        }
                                    } else {
                                        throw r0;
                                    }
                                } catch (no.nordicsemi.android.dfu.internal.exception.RemoteDfuException e) {
                                    r0 = e;
                                    r2 = r0;
                                    int r3 = r2.getErrorNumber() | 256;
                                    loge(r2.getMessage() + ": " + no.nordicsemi.android.error.LegacyDfuError.parse(r3));
                                    r27.mService.sendLogBroadcast(20, java.lang.String.format(java.util.Locale.US, "Remote DFU error: %s", no.nordicsemi.android.error.LegacyDfuError.parse(r3)));
                                    logi(r22);
                                    writeOpCode(r27.mControlPointCharacteristic, no.nordicsemi.android.dfu.LegacyDfuImpl.OP_CODE_RESET);
                                    r27.mService.sendLogBroadcast(10, r21);
                                    r27.mService.terminateConnection(r10, r3 | 8192);
                                    return;
                                }
                            }
                            if (r3.getErrorNumber() != 3) {
                                throw r3;
                            }
                            if (r20 > 0 && (r5 & 3) > 0) {
                                r27.mRemoteErrorOccurred = false;
                                java.lang.String r3 = r23;
                                logw(r3);
                                r27.mService.sendLogBroadcast(15, r3);
                                int r5 = r5 & (-5);
                                r27.mFileType = r5;
                                byte[] r3 = no.nordicsemi.android.dfu.LegacyDfuImpl.OP_CODE_START_DFU;
                                r3[1] = (byte) r5;
                                r27.mProgressInfo.setTotalPart(2);
                                ((no.nordicsemi.android.dfu.internal.ArchiveInputStream) r27.mFirmwareStream).setContentType(r5);
                                r27.mService.sendLogBroadcast(1, "Sending only SD/BL");
                                logi("Resending Start DFU command (Op Code = 1, Upload Mode = " + r5 + ")");
                                writeOpCode(r27.mControlPointCharacteristic, r3);
                                r27.mService.sendLogBroadcast(10, "DFU Start sent (Op Code = 1, Upload Mode = " + r5 + ")");
                                logi("Sending image size array to DFU Packet: [" + r14 + "b, " + r8 + "b, 0b]");
                                writeImageSize(r27.mPacketCharacteristic, r14, r8, 0);
                                r27.mService.sendLogBroadcast(10, "Firmware image size sent [" + r14 + "b, " + r8 + "b, 0b]");
                                byte[] r3 = readNotificationResponse();
                                int r7 = getStatusCode(r3, 1);
                                r27.mService.sendLogBroadcast(10, "Response received (Op Code = " + ((int) r3[1]) + " Status = " + r7 + ")");
                                if (r7 == 2) {
                                    resetAndRestart(r10, r28);
                                    return;
                                }
                                if (r7 != 1) {
                                    throw new no.nordicsemi.android.dfu.internal.exception.RemoteDfuException(r6, r7);
                                }
                                r4 = r16;
                                r3 = true;
                                if (r27.mInitPacketStream != null) {
                                }
                                try {
                                    if (!r3) {
                                        r3 = 10;
                                        if (r3 > 0) {
                                        }
                                        logi("Sending Receive Firmware Image request (Op Code = 3)");
                                        writeOpCode(r27.mControlPointCharacteristic, no.nordicsemi.android.dfu.LegacyDfuImpl.OP_CODE_RECEIVE_FIRMWARE_IMAGE);
                                        r27.mService.sendLogBroadcast(10, "Receive Firmware Image request sent");
                                        long r5 = android.os.SystemClock.elapsedRealtime();
                                        r27.mProgressInfo.setBytesSent(0);
                                        logi("Uploading firmware...");
                                        r27.mService.sendLogBroadcast(10, "Uploading firmware...");
                                        uploadFirmwareImage(r27.mPacketCharacteristic);
                                        long r7 = android.os.SystemClock.elapsedRealtime();
                                        byte[] r3 = readNotificationResponse();
                                        r9 = getStatusCode(r3, 3);
                                        logi("Response received (Op Code = " + ((int) r3[0]) + ", Req Op Code = " + ((int) r3[1]) + r4 + ((int) r3[2]) + ")");
                                        r27.mService.sendLogBroadcast(10, "Response received (Op Code = " + ((int) r3[1]) + r4 + r9 + ")");
                                        if (r9 == 1) {
                                        }
                                    }
                                    logi("Uploading firmware...");
                                    r27.mService.sendLogBroadcast(10, "Uploading firmware...");
                                    uploadFirmwareImage(r27.mPacketCharacteristic);
                                    long r7 = android.os.SystemClock.elapsedRealtime();
                                    byte[] r3 = readNotificationResponse();
                                    r9 = getStatusCode(r3, 3);
                                    logi("Response received (Op Code = " + ((int) r3[0]) + ", Req Op Code = " + ((int) r3[1]) + r4 + ((int) r3[2]) + ")");
                                    r27.mService.sendLogBroadcast(10, "Response received (Op Code = " + ((int) r3[1]) + r4 + r9 + ")");
                                    if (r9 == 1) {
                                    }
                                } catch (no.nordicsemi.android.dfu.internal.exception.DeviceDisconnectedException r0) {
                                    loge("Disconnected while sending data");
                                    throw r0;
                                }
                                r3 = r27.mPacketsBeforeNotification;
                                if (r3 > 0) {
                                }
                                logi("Sending Receive Firmware Image request (Op Code = 3)");
                                writeOpCode(r27.mControlPointCharacteristic, no.nordicsemi.android.dfu.LegacyDfuImpl.OP_CODE_RECEIVE_FIRMWARE_IMAGE);
                                r27.mService.sendLogBroadcast(10, "Receive Firmware Image request sent");
                                long r5 = android.os.SystemClock.elapsedRealtime();
                                r27.mProgressInfo.setBytesSent(0);
                            } else {
                                throw r3;
                            }
                        }
                        if (r7 == 2) {
                            try {
                                resetAndRestart(r10, r28);
                            } catch (no.nordicsemi.android.dfu.internal.exception.RemoteDfuException r0) {
                                r3 = r0;
                                r6 = "Starting DFU failed";
                                if (r3.getErrorNumber() != 3) {
                                }
                            }
                        } else {
                            if (r7 != 1) {
                                r6 = "Starting DFU failed";
                                try {
                                    throw new no.nordicsemi.android.dfu.internal.exception.RemoteDfuException(r6, r7);
                                } catch (no.nordicsemi.android.dfu.internal.exception.RemoteDfuException e) {
                                    r0 = e;
                                    r3 = r0;
                                    if (r3.getErrorNumber() != 3) {
                                    }
                                }
                            }
                            r4 = r16;
                            r3 = true;
                            if (r27.mInitPacketStream != null) {
                                r27.mService.sendLogBroadcast(10, "Writing Initialize DFU Parameters...");
                                if (r3) {
                                    logi("Sending the Initialize DFU Parameters START (Op Code = 2, Value = 0)");
                                    writeOpCode(r27.mControlPointCharacteristic, no.nordicsemi.android.dfu.LegacyDfuImpl.OP_CODE_INIT_DFU_PARAMS_START);
                                    logi("Sending " + r27.mInitPacketSizeInBytes + " bytes of init packet");
                                    writeInitData(r27.mPacketCharacteristic, null);
                                    logi("Sending the Initialize DFU Parameters COMPLETE (Op Code = 2, Value = 1)");
                                    writeOpCode(r27.mControlPointCharacteristic, no.nordicsemi.android.dfu.LegacyDfuImpl.OP_CODE_INIT_DFU_PARAMS_COMPLETE);
                                    r27.mService.sendLogBroadcast(10, "Initialize DFU Parameters completed");
                                } else {
                                    logi("Sending the Initialize DFU Parameters (Op Code = 2)");
                                    writeOpCode(r27.mControlPointCharacteristic, no.nordicsemi.android.dfu.LegacyDfuImpl.OP_CODE_INIT_DFU_PARAMS);
                                    logi("Sending " + r27.mInitPacketSizeInBytes + " bytes of init packet");
                                    writeInitData(r27.mPacketCharacteristic, null);
                                }
                                byte[] r5 = readNotificationResponse();
                                int r7 = getStatusCode(r5, 2);
                                r27.mService.sendLogBroadcast(10, "Response received (Op Code = " + ((int) r5[1]) + r4 + r7 + ")");
                                if (r7 != 1) {
                                    throw new no.nordicsemi.android.dfu.internal.exception.RemoteDfuException("Device returned error after sending init packet", r7);
                                }
                            }
                            if (!r3 && (r27.mPacketsBeforeNotification <= 0 || r27.mPacketsBeforeNotification > 10)) {
                                r3 = 10;
                                if (r3 > 0) {
                                    r27.mPacketsBeforeNotification = r3;
                                    logi("Sending the number of packets before notifications (Op Code = 8, Value = " + r3 + ")");
                                    byte[] r5 = no.nordicsemi.android.dfu.LegacyDfuImpl.OP_CODE_PACKET_RECEIPT_NOTIF_REQ;
                                    setNumberOfPackets(r5, r3);
                                    writeOpCode(r27.mControlPointCharacteristic, r5);
                                    r27.mService.sendLogBroadcast(10, "Packet Receipt Notif Req (Op Code = 8) sent (Value = " + r3 + ")");
                                }
                                logi("Sending Receive Firmware Image request (Op Code = 3)");
                                writeOpCode(r27.mControlPointCharacteristic, no.nordicsemi.android.dfu.LegacyDfuImpl.OP_CODE_RECEIVE_FIRMWARE_IMAGE);
                                r27.mService.sendLogBroadcast(10, "Receive Firmware Image request sent");
                                long r5 = android.os.SystemClock.elapsedRealtime();
                                r27.mProgressInfo.setBytesSent(0);
                                logi("Uploading firmware...");
                                r27.mService.sendLogBroadcast(10, "Uploading firmware...");
                                uploadFirmwareImage(r27.mPacketCharacteristic);
                                long r7 = android.os.SystemClock.elapsedRealtime();
                                byte[] r3 = readNotificationResponse();
                                r9 = getStatusCode(r3, 3);
                                logi("Response received (Op Code = " + ((int) r3[0]) + ", Req Op Code = " + ((int) r3[1]) + r4 + ((int) r3[2]) + ")");
                                r27.mService.sendLogBroadcast(10, "Response received (Op Code = " + ((int) r3[1]) + r4 + r9 + ")");
                                if (r9 == 1) {
                                    throw new no.nordicsemi.android.dfu.internal.exception.RemoteDfuException("Device returned error after sending file", r9);
                                }
                                long r7 = r7 - r5;
                                logi("Transfer of " + r27.mProgressInfo.getBytesSent() + " bytes has taken " + r7 + " ms");
                                r27.mService.sendLogBroadcast(10, "Upload completed in " + r7 + " ms");
                                logi("Sending Validate request (Op Code = 4)");
                                writeOpCode(r27.mControlPointCharacteristic, no.nordicsemi.android.dfu.LegacyDfuImpl.OP_CODE_VALIDATE);
                                r27.mService.sendLogBroadcast(10, "Validate request sent");
                                byte[] r3 = readNotificationResponse();
                                int r5 = getStatusCode(r3, 4);
                                logi("Response received (Op Code = " + ((int) r3[0]) + ", Req Op Code = " + ((int) r3[1]) + r4 + ((int) r3[2]) + ")");
                                r27.mService.sendLogBroadcast(10, "Response received (Op Code = " + ((int) r3[1]) + r4 + r5 + ")");
                                if (r5 != 1) {
                                    throw new no.nordicsemi.android.dfu.internal.exception.RemoteDfuException("Device returned validation error", r5);
                                }
                                r27.mProgressInfo.setProgress(-5);
                                logi("Sending Activate and Reset request (Op Code = 5)");
                                writeOpCode(r27.mControlPointCharacteristic, no.nordicsemi.android.dfu.LegacyDfuImpl.OP_CODE_ACTIVATE_AND_RESET);
                                r27.mService.sendLogBroadcast(10, "Activate and Reset request sent");
                                r27.mService.waitUntilDisconnected();
                                r27.mService.sendLogBroadcast(5, "Disconnected by the remote device");
                                finalize(r28, r14 == 5);
                                return;
                            }
                            r3 = r27.mPacketsBeforeNotification;
                            if (r3 > 0) {
                            }
                            logi("Sending Receive Firmware Image request (Op Code = 3)");
                            writeOpCode(r27.mControlPointCharacteristic, no.nordicsemi.android.dfu.LegacyDfuImpl.OP_CODE_RECEIVE_FIRMWARE_IMAGE);
                            r27.mService.sendLogBroadcast(10, "Receive Firmware Image request sent");
                            long r5 = android.os.SystemClock.elapsedRealtime();
                            r27.mProgressInfo.setBytesSent(0);
                            logi("Uploading firmware...");
                            r27.mService.sendLogBroadcast(10, "Uploading firmware...");
                            uploadFirmwareImage(r27.mPacketCharacteristic);
                            long r7 = android.os.SystemClock.elapsedRealtime();
                            byte[] r3 = readNotificationResponse();
                            r9 = getStatusCode(r3, 3);
                            logi("Response received (Op Code = " + ((int) r3[0]) + ", Req Op Code = " + ((int) r3[1]) + r4 + ((int) r3[2]) + ")");
                            r27.mService.sendLogBroadcast(10, "Response received (Op Code = " + ((int) r3[1]) + r4 + r9 + ")");
                            if (r9 == 1) {
                            }
                        }
                    } catch (no.nordicsemi.android.dfu.internal.exception.UnknownResponseException r0) {
                        r3 = r0;
                        r4 = r21;
                        r2 = r22;
                        loge(r3.getMessage());
                        r27.mService.sendLogBroadcast(20, r3.getMessage());
                        logi(r2);
                        writeOpCode(r27.mControlPointCharacteristic, no.nordicsemi.android.dfu.LegacyDfuImpl.OP_CODE_RESET);
                        r27.mService.sendLogBroadcast(10, r4);
                        r27.mService.terminateConnection(r10, no.nordicsemi.android.dfu.DfuBaseService.ERROR_INVALID_RESPONSE);
                    } catch (no.nordicsemi.android.dfu.internal.exception.UploadAbortedException r0) {
                        r3 = r0;
                        r4 = r21;
                        r2 = r22;
                        logi(r2);
                        r27.mAborted = false;
                        writeOpCode(r27.mControlPointCharacteristic, no.nordicsemi.android.dfu.LegacyDfuImpl.OP_CODE_RESET);
                        r27.mService.sendLogBroadcast(10, r4);
                        throw r3;
                    }
                } catch (no.nordicsemi.android.dfu.internal.exception.UnknownResponseException r0) {
                    r3 = r0;
                    r4 = "Reset request sent";
                    r2 = "Sending Reset command (Op Code = 6)";
                } catch (no.nordicsemi.android.dfu.internal.exception.UploadAbortedException r0) {
                    r3 = r0;
                    r4 = "Reset request sent";
                    r2 = "Sending Reset command (Op Code = 6)";
                }
            } catch (no.nordicsemi.android.dfu.internal.exception.RemoteDfuException e) {
                r0 = e;
                r21 = "Reset request sent";
                r22 = "Sending Reset command (Op Code = 6)";
            }
        } catch (no.nordicsemi.android.dfu.internal.exception.UnknownResponseException r0) {
            r4 = "Reset request sent";
            r2 = "Sending Reset command (Op Code = 6)";
            r3 = r0;
        } catch (no.nordicsemi.android.dfu.internal.exception.UploadAbortedException r0) {
            r4 = "Reset request sent";
            r2 = "Sending Reset command (Op Code = 6)";
            r3 = r0;
        }
    }

    private void setNumberOfPackets(byte[] bArr, int i) {
        bArr[1] = (byte) (i & 255);
        bArr[2] = (byte) ((i >> 8) & 255);
    }

    private int getStatusCode(byte[] bArr, int i) throws UnknownResponseException {
        if (bArr == null || bArr.length != 3 || bArr[0] != 16 || bArr[1] != i || bArr[2] < 1 || bArr[2] > 6) {
            throw new UnknownResponseException("Invalid response received", bArr, 16, i);
        }
        return bArr[2];
    }

    private int readVersion(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (bluetoothGattCharacteristic != null) {
            return bluetoothGattCharacteristic.getIntValue(18, 0).intValue();
        }
        return 0;
    }

    private void writeOpCode(BluetoothGattCharacteristic bluetoothGattCharacteristic, byte[] bArr) throws DeviceDisconnectedException, DfuException, UploadAbortedException {
        boolean z = false;
        writeOpCode(bluetoothGattCharacteristic, bArr, (bArr[0] == 6 || bArr[0] == 5) ? true : true);
    }

    private void writeImageSize(BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) throws DeviceDisconnectedException, DfuException, UploadAbortedException {
        this.mReceivedData = null;
        this.mError = 0;
        this.mImageSizeInProgress = true;
        bluetoothGattCharacteristic.setWriteType(1);
        bluetoothGattCharacteristic.setValue(new byte[4]);
        bluetoothGattCharacteristic.setValue(i, 20, 0);
        this.mService.sendLogBroadcast(1, "Writing to characteristic " + bluetoothGattCharacteristic.getUuid());
        this.mService.sendLogBroadcast(0, "gatt.writeCharacteristic(" + bluetoothGattCharacteristic.getUuid() + ")");
        this.mGatt.writeCharacteristic(bluetoothGattCharacteristic);
        try {
            synchronized (this.mLock) {
                while (true) {
                    if ((!this.mImageSizeInProgress || !this.mConnected || this.mError != 0 || this.mAborted) && !this.mPaused) {
                        break;
                    }
                    this.mLock.wait();
                }
            }
        } catch (InterruptedException e) {
            loge("Sleeping interrupted", e);
        }
        if (this.mAborted) {
            throw new UploadAbortedException();
        }
        if (!this.mConnected) {
            throw new DeviceDisconnectedException("Unable to write Image Size: device disconnected");
        }
        if (this.mError != 0) {
            throw new DfuException("Unable to write Image Size", this.mError);
        }
    }

    private void writeImageSize(BluetoothGattCharacteristic bluetoothGattCharacteristic, int i, int i2, int i3) throws DeviceDisconnectedException, DfuException, UploadAbortedException {
        this.mReceivedData = null;
        this.mError = 0;
        this.mImageSizeInProgress = true;
        bluetoothGattCharacteristic.setWriteType(1);
        bluetoothGattCharacteristic.setValue(new byte[12]);
        bluetoothGattCharacteristic.setValue(i, 20, 0);
        bluetoothGattCharacteristic.setValue(i2, 20, 4);
        bluetoothGattCharacteristic.setValue(i3, 20, 8);
        this.mService.sendLogBroadcast(1, "Writing to characteristic " + bluetoothGattCharacteristic.getUuid());
        this.mService.sendLogBroadcast(0, "gatt.writeCharacteristic(" + bluetoothGattCharacteristic.getUuid() + ")");
        this.mGatt.writeCharacteristic(bluetoothGattCharacteristic);
        try {
            synchronized (this.mLock) {
                while (true) {
                    if ((!this.mImageSizeInProgress || !this.mConnected || this.mError != 0 || this.mAborted) && !this.mPaused) {
                        break;
                    }
                    this.mLock.wait();
                }
            }
        } catch (InterruptedException e) {
            loge("Sleeping interrupted", e);
        }
        if (this.mAborted) {
            throw new UploadAbortedException();
        }
        if (!this.mConnected) {
            throw new DeviceDisconnectedException("Unable to write Image Sizes: device disconnected");
        }
        if (this.mError != 0) {
            throw new DfuException("Unable to write Image Sizes", this.mError);
        }
    }

    private void resetAndRestart(BluetoothGatt bluetoothGatt, Intent intent) throws DfuException, DeviceDisconnectedException, UploadAbortedException {
        this.mService.sendLogBroadcast(15, "Last upload interrupted. Restarting device...");
        this.mProgressInfo.setProgress(-5);
        logi("Sending Reset command (Op Code = 6)");
        writeOpCode(this.mControlPointCharacteristic, OP_CODE_RESET);
        this.mService.sendLogBroadcast(10, "Reset request sent");
        this.mService.waitUntilDisconnected();
        this.mService.sendLogBroadcast(5, "Disconnected by the remote device");
        BluetoothGattService service = bluetoothGatt.getService(GENERIC_ATTRIBUTE_SERVICE_UUID);
        this.mService.refreshDeviceCache(bluetoothGatt, !((service == null || service.getCharacteristic(SERVICE_CHANGED_UUID) == null) ? false : true));
        this.mService.close(bluetoothGatt);
        logi("Restarting the service");
        Intent intent2 = new Intent();
        intent2.fillIn(intent, 24);
        restartService(intent2, false);
    }
}
