package no.nordicsemi.android.dfu;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.content.Intent;
import java.io.InputStream;
import java.util.Locale;
import java.util.UUID;
import kotlin.UByte;
import no.nordicsemi.android.dfu.BaseCustomDfuImpl;
import no.nordicsemi.android.dfu.BaseDfuImpl;
import no.nordicsemi.android.dfu.internal.ArchiveInputStream;
import no.nordicsemi.android.dfu.internal.exception.DeviceDisconnectedException;
import no.nordicsemi.android.dfu.internal.exception.DfuException;
import no.nordicsemi.android.dfu.internal.exception.RemoteDfuException;
import no.nordicsemi.android.dfu.internal.exception.RemoteDfuExtendedErrorException;
import no.nordicsemi.android.dfu.internal.exception.UnknownResponseException;
import no.nordicsemi.android.dfu.internal.exception.UploadAbortedException;
import no.nordicsemi.android.error.SecureDfuError;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class SecureDfuImpl extends BaseCustomDfuImpl {
    static final UUID DEFAULT_DFU_CONTROL_POINT_UUID;
    static final UUID DEFAULT_DFU_PACKET_UUID;
    static final UUID DEFAULT_DFU_SERVICE_UUID;
    static UUID DFU_CONTROL_POINT_UUID = null;
    static UUID DFU_PACKET_UUID = null;
    static UUID DFU_SERVICE_UUID = null;
    private static final int DFU_STATUS_SUCCESS = 1;
    private static final int MAX_ATTEMPTS = 3;
    private static final int OBJECT_COMMAND = 1;
    private static final int OBJECT_DATA = 2;
    private static final byte[] OP_CODE_CALCULATE_CHECKSUM;
    private static final int OP_CODE_CALCULATE_CHECKSUM_KEY = 3;
    private static final byte[] OP_CODE_CREATE_COMMAND;
    private static final byte[] OP_CODE_CREATE_DATA;
    private static final int OP_CODE_CREATE_KEY = 1;
    private static final byte[] OP_CODE_EXECUTE;
    private static final int OP_CODE_EXECUTE_KEY = 4;
    private static final byte[] OP_CODE_PACKET_RECEIPT_NOTIF_REQ;
    private static final int OP_CODE_PACKET_RECEIPT_NOTIF_REQ_KEY = 2;
    private static final int OP_CODE_RESPONSE_CODE_KEY = 96;
    private static final byte[] OP_CODE_SELECT_OBJECT;
    private static final int OP_CODE_SELECT_OBJECT_KEY = 6;
    private final SecureBluetoothCallback mBluetoothCallback;
    private BluetoothGattCharacteristic mControlPointCharacteristic;
    private BluetoothGattCharacteristic mPacketCharacteristic;
    private long prepareObjectDelay;

    static {
        UUID uuid = new UUID(279658205548544L, -9223371485494954757L);
        DEFAULT_DFU_SERVICE_UUID = uuid;
        UUID uuid2 = new UUID(-8157989241631715488L, -6937650605005804976L);
        DEFAULT_DFU_CONTROL_POINT_UUID = uuid2;
        UUID uuid3 = new UUID(-8157989237336748192L, -6937650605005804976L);
        DEFAULT_DFU_PACKET_UUID = uuid3;
        DFU_SERVICE_UUID = uuid;
        DFU_CONTROL_POINT_UUID = uuid2;
        DFU_PACKET_UUID = uuid3;
        OP_CODE_CREATE_COMMAND = new byte[]{1, 1, 0, 0, 0, 0};
        OP_CODE_CREATE_DATA = new byte[]{1, 2, 0, 0, 0, 0};
        OP_CODE_PACKET_RECEIPT_NOTIF_REQ = new byte[]{2, 0, 0};
        OP_CODE_CALCULATE_CHECKSUM = new byte[]{3};
        OP_CODE_EXECUTE = new byte[]{4};
        OP_CODE_SELECT_OBJECT = new byte[]{6, 0};
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes2.dex */
    public class SecureBluetoothCallback extends BaseCustomDfuImpl.BaseCustomBluetoothCallback {
        protected SecureBluetoothCallback() {
            super();
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            if (bluetoothGattCharacteristic.getValue() == null || bluetoothGattCharacteristic.getValue().length < 3) {
                SecureDfuImpl.this.loge("Empty response: " + parse(bluetoothGattCharacteristic));
                SecureDfuImpl.this.mError = DfuBaseService.ERROR_INVALID_RESPONSE;
                SecureDfuImpl.this.notifyLock();
                return;
            }
            if (bluetoothGattCharacteristic.getIntValue(17, 0).intValue() == 96) {
                if (bluetoothGattCharacteristic.getIntValue(17, 1).intValue() == 3) {
                    int intValue = bluetoothGattCharacteristic.getIntValue(20, 3).intValue();
                    if (((int) (((ArchiveInputStream) SecureDfuImpl.this.mFirmwareStream).getCrc32() & 4294967295L)) == bluetoothGattCharacteristic.getIntValue(20, 7).intValue()) {
                        SecureDfuImpl.this.mProgressInfo.setBytesReceived(intValue);
                    } else if (SecureDfuImpl.this.mFirmwareUploadInProgress) {
                        SecureDfuImpl.this.mFirmwareUploadInProgress = false;
                        SecureDfuImpl.this.notifyLock();
                        return;
                    }
                    handlePacketReceiptNotification(bluetoothGatt, bluetoothGattCharacteristic);
                } else if (!SecureDfuImpl.this.mRemoteErrorOccurred) {
                    if (bluetoothGattCharacteristic.getIntValue(17, 2).intValue() != 1) {
                        SecureDfuImpl.this.mRemoteErrorOccurred = true;
                    }
                    handleNotification(bluetoothGatt, bluetoothGattCharacteristic);
                }
            } else {
                SecureDfuImpl.this.loge("Invalid response: " + parse(bluetoothGattCharacteristic));
                SecureDfuImpl.this.mError = DfuBaseService.ERROR_INVALID_RESPONSE;
            }
            SecureDfuImpl.this.notifyLock();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SecureDfuImpl(Intent intent, DfuBaseService dfuBaseService) {
        super(intent, dfuBaseService);
        this.mBluetoothCallback = new SecureBluetoothCallback();
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

    @Override // no.nordicsemi.android.dfu.BaseDfuImpl, no.nordicsemi.android.dfu.DfuService
    public boolean initialize(Intent intent, BluetoothGatt bluetoothGatt, int i, InputStream inputStream, InputStream inputStream2) throws DfuException, DeviceDisconnectedException, UploadAbortedException {
        if (inputStream2 == null) {
            this.mService.sendLogBroadcast(20, "The Init packet is required by this version DFU Bootloader");
            this.mService.terminateConnection(bluetoothGatt, DfuBaseService.ERROR_INIT_PACKET_REQUIRED);
            return false;
        }
        return super.initialize(intent, bluetoothGatt, i, inputStream, inputStream2);
    }

    @Override // no.nordicsemi.android.dfu.DfuCallback
    public BaseDfuImpl.BaseBluetoothGattCallback getGattCallback() {
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

    /* JADX WARN: Can't wrap try/catch for region: R(9:7|8|(6:13|(1:15)|16|17|18|19)|27|(0)|16|17|18|19) */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0073, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x007a, code lost:
        if (r8.mProgressInfo.isLastPart() == false) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x007c, code lost:
        r8.mRemoteErrorOccurred = false;
        logw("Sending SD+BL failed. Trying to send App only");
        r8.mService.sendLogBroadcast(15, "Invalid system components. Trying to send application");
        r8.mFileType = 4;
        r0 = (no.nordicsemi.android.dfu.internal.ArchiveInputStream) r8.mFirmwareStream;
        r0.setContentType(r8.mFileType);
        r5 = r0.getApplicationInit();
        r8.mInitPacketStream = new java.io.ByteArrayInputStream(r5);
        r8.mInitPacketSizeInBytes = r5.length;
        r8.mImageSizeInBytes = r0.applicationImageSize();
        r8.mProgressInfo.init(r8.mImageSizeInBytes, 2, 2);
        sendInitPacket(r1, false);
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x00d2, code lost:
        throw r0;
     */
    /* JADX WARN: Removed duplicated region for block: B:16:0x006a A[Catch: RemoteDfuException -> 0x00d3, UnknownResponseException -> 0x0177, UploadAbortedException -> 0x0190, TRY_LEAVE, TryCatch #1 {RemoteDfuException -> 0x00d3, blocks: (B:8:0x004a, B:10:0x005e, B:16:0x006a, B:20:0x0074, B:22:0x007c, B:24:0x00d2, B:23:0x00b7), top: B:37:0x004a }] */
    @Override // no.nordicsemi.android.dfu.DfuService
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void performDfu(android.content.Intent r9) throws no.nordicsemi.android.dfu.internal.exception.DfuException, no.nordicsemi.android.dfu.internal.exception.DeviceDisconnectedException, no.nordicsemi.android.dfu.internal.exception.UploadAbortedException {
        no.nordicsemi.android.dfu.internal.exception.RemoteDfuExtendedErrorException r9;
        boolean r0;
        logw("Secure DFU bootloader found");
        r8.mProgressInfo.setProgress(-2);
        android.bluetooth.BluetoothGatt r1 = r8.mGatt;
        if (r9.hasExtra(no.nordicsemi.android.dfu.DfuBaseService.EXTRA_MTU) && android.os.Build.VERSION.SDK_INT >= 21) {
            int r2 = r9.getIntExtra(no.nordicsemi.android.dfu.DfuBaseService.EXTRA_MTU, 517);
            logi("Requesting MTU = " + r2);
            requestMtu(r2);
        }
        r8.prepareObjectDelay = r9.getLongExtra(no.nordicsemi.android.dfu.DfuBaseService.EXTRA_DATA_OBJECT_DELAY, 0L);
        try {
            try {
                enableCCCD(r8.mControlPointCharacteristic, 1);
                r8.mService.sendLogBroadcast(10, "Notifications enabled");
                if (r9.hasExtra(no.nordicsemi.android.dfu.DfuBaseService.EXTRA_DISABLE_RESUME) && r9.getBooleanExtra(no.nordicsemi.android.dfu.DfuBaseService.EXTRA_DISABLE_RESUME, false)) {
                    r0 = false;
                    if (!r0) {
                        logi("Resume feature disabled. Performing fresh DFU");
                    }
                    sendInitPacket(r1, r0);
                    sendFirmware(r1);
                    r8.mProgressInfo.setProgress(-5);
                    r8.mService.waitUntilDisconnected();
                    r8.mService.sendLogBroadcast(5, "Disconnected by the remote device");
                    finalize(r9, false);
                }
                r0 = true;
                if (!r0) {
                }
                sendInitPacket(r1, r0);
                sendFirmware(r1);
                r8.mProgressInfo.setProgress(-5);
                r8.mService.waitUntilDisconnected();
                r8.mService.sendLogBroadcast(5, "Disconnected by the remote device");
                finalize(r9, false);
            } catch (no.nordicsemi.android.dfu.internal.exception.RemoteDfuException r9) {
                int r0 = r9.getErrorNumber() | 512;
                loge(r9.getMessage() + ": " + no.nordicsemi.android.error.SecureDfuError.parse(r0));
                r8.mService.sendLogBroadcast(20, java.lang.String.format(java.util.Locale.US, "Remote DFU error: %s", no.nordicsemi.android.error.SecureDfuError.parse(r0)));
                if (r9 instanceof no.nordicsemi.android.dfu.internal.exception.RemoteDfuExtendedErrorException) {
                    int r0 = ((no.nordicsemi.android.dfu.internal.exception.RemoteDfuExtendedErrorException) r9).getExtendedErrorNumber() | 1024;
                    loge("Extended Error details: " + no.nordicsemi.android.error.SecureDfuError.parseExtendedError(r0));
                    r8.mService.sendLogBroadcast(20, "Details: " + no.nordicsemi.android.error.SecureDfuError.parseExtendedError(r0) + " (Code = " + r9.getExtendedErrorNumber() + ")");
                    r8.mService.terminateConnection(r1, r0 | 8192);
                    return;
                }
                r8.mService.terminateConnection(r1, r0 | 8192);
            }
        } catch (no.nordicsemi.android.dfu.internal.exception.UnknownResponseException r9) {
            loge(r9.getMessage());
            r8.mService.sendLogBroadcast(20, r9.getMessage());
            r8.mService.terminateConnection(r1, no.nordicsemi.android.dfu.DfuBaseService.ERROR_INVALID_RESPONSE);
        } catch (no.nordicsemi.android.dfu.internal.exception.UploadAbortedException r9) {
            throw r9;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x0124  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void sendInitPacket(android.bluetooth.BluetoothGatt r18, boolean r19) throws no.nordicsemi.android.dfu.internal.exception.RemoteDfuException, no.nordicsemi.android.dfu.internal.exception.DeviceDisconnectedException, no.nordicsemi.android.dfu.internal.exception.DfuException, no.nordicsemi.android.dfu.internal.exception.UploadAbortedException, no.nordicsemi.android.dfu.internal.exception.UnknownResponseException {
        boolean r0;
        boolean r14;
        java.io.IOException r15;
        java.util.zip.CRC32 r3 = new java.util.zip.CRC32();
        logi("Setting object to Command (Op Code = 6, Type = 1)");
        no.nordicsemi.android.dfu.SecureDfuImpl.ObjectInfo r5 = selectObject(1);
        int r6 = 3;
        logi(java.lang.String.format(java.util.Locale.US, "Command object info received (Max size = %d, Offset = %d, CRC = %08X)", java.lang.Integer.valueOf(r5.maxSize), java.lang.Integer.valueOf(r5.offset), java.lang.Integer.valueOf(r5.CRC32)));
        r17.mService.sendLogBroadcast(10, java.lang.String.format(java.util.Locale.US, "Command object info received (Max size = %d, Offset = %d, CRC = %08X)", java.lang.Integer.valueOf(r5.maxSize), java.lang.Integer.valueOf(r5.offset), java.lang.Integer.valueOf(r5.CRC32)));
        int r0 = r17.mInitPacketSizeInBytes;
        int r0 = r5.maxSize;
        if (r19 && r5.offset > 0 && r5.offset <= r17.mInitPacketSizeInBytes) {
            try {
                byte[] r0 = new byte[r5.offset];
                r17.mInitPacketStream.read(r0);
                r3.update(r0);
            } catch (java.io.IOException r0) {
                r15 = r0;
                r0 = false;
                r14 = false;
            }
            if (r5.CRC32 == ((int) (r3.getValue() & 4294967295L))) {
                logi("Init packet CRC is the same");
                if (r5.offset == r17.mInitPacketSizeInBytes) {
                    logi("-> Whole Init packet was sent before");
                    try {
                        r17.mService.sendLogBroadcast(10, "Received CRC match Init packet");
                        r0 = true;
                        r14 = false;
                    } catch (java.io.IOException r0) {
                        r15 = r0;
                        r0 = true;
                        r14 = false;
                        loge("Error while reading " + r5.offset + " bytes from the init packet stream", r15);
                        try {
                            r17.mInitPacketStream.reset();
                            r3.reset();
                            r5.offset = 0;
                            if (!r0) {
                            }
                            logi("Executing init packet (Op Code = 4)");
                            writeExecute();
                            r17.mService.sendLogBroadcast(10, "Command object executed");
                        } catch (java.io.IOException r0) {
                            loge("Error while resetting the init packet stream", r0);
                            r17.mService.terminateConnection(r18, no.nordicsemi.android.dfu.DfuBaseService.ERROR_FILE_IO_EXCEPTION);
                            return;
                        }
                    }
                } else {
                    logi("-> " + r5.offset + " bytes of Init packet were sent before");
                    try {
                        r17.mService.sendLogBroadcast(10, "Resuming sending Init packet...");
                        r14 = true;
                        r0 = false;
                    } catch (java.io.IOException r0) {
                        r15 = r0;
                        r14 = true;
                        r0 = false;
                        loge("Error while reading " + r5.offset + " bytes from the init packet stream", r15);
                        r17.mInitPacketStream.reset();
                        r3.reset();
                        r5.offset = 0;
                        if (!r0) {
                        }
                        logi("Executing init packet (Op Code = 4)");
                        writeExecute();
                        r17.mService.sendLogBroadcast(10, "Command object executed");
                    }
                }
                if (!r0) {
                    setPacketReceiptNotifications(0);
                    r17.mService.sendLogBroadcast(10, "Packet Receipt Notif disabled (Op Code = 2, Value = 0)");
                    int r0 = 1;
                    while (r0 <= r6) {
                        if (!r14) {
                            logi("Creating Init packet object (Op Code = 1, Type = 1, Size = " + r17.mInitPacketSizeInBytes + ")");
                            writeCreateRequest(1, r17.mInitPacketSizeInBytes);
                            r17.mService.sendLogBroadcast(10, "Command object created");
                        }
                        try {
                            logi("Sending " + (r17.mInitPacketSizeInBytes - r5.offset) + " bytes of init packet...");
                            writeInitData(r17.mPacketCharacteristic, r3);
                            int r11 = (int) (r3.getValue() & 4294967295L);
                            r17.mService.sendLogBroadcast(10, java.lang.String.format(java.util.Locale.US, "Command object sent (CRC = %08X)", java.lang.Integer.valueOf(r11)));
                            logi("Sending Calculate Checksum command (Op Code = 3)");
                            no.nordicsemi.android.dfu.SecureDfuImpl.ObjectChecksum r7 = readChecksum();
                            r17.mService.sendLogBroadcast(10, java.lang.String.format(java.util.Locale.US, "Checksum received (Offset = %d, CRC = %08X)", java.lang.Integer.valueOf(r7.offset), java.lang.Integer.valueOf(r7.CRC32)));
                            logi(java.lang.String.format(java.util.Locale.US, "Checksum received (Offset = %d, CRC = %08X)", java.lang.Integer.valueOf(r7.offset), java.lang.Integer.valueOf(r7.CRC32)));
                            if (r11 == r7.CRC32) {
                                break;
                            }
                            r6 = 3;
                            if (r0 < 3) {
                                r0++;
                                logi("CRC does not match! Retrying...(" + r0 + "/3)");
                                r17.mService.sendLogBroadcast(15, "CRC does not match! Retrying...(" + r0 + "/3)");
                                try {
                                    r5.offset = 0;
                                    r5.CRC32 = 0;
                                    r17.mInitPacketStream.reset();
                                    r3.reset();
                                    r14 = false;
                                } catch (java.io.IOException r0) {
                                    loge("Error while resetting the init packet stream", r0);
                                    r17.mService.terminateConnection(r18, no.nordicsemi.android.dfu.DfuBaseService.ERROR_FILE_IO_EXCEPTION);
                                    return;
                                }
                            } else {
                                loge("CRC does not match!");
                                r17.mService.sendLogBroadcast(20, "CRC does not match!");
                                r17.mService.terminateConnection(r18, no.nordicsemi.android.dfu.DfuBaseService.ERROR_CRC_ERROR);
                                return;
                            }
                        } catch (no.nordicsemi.android.dfu.internal.exception.DeviceDisconnectedException r0) {
                            loge("Disconnected while sending init packet");
                            throw r0;
                        }
                    }
                }
                logi("Executing init packet (Op Code = 4)");
                writeExecute();
                r17.mService.sendLogBroadcast(10, "Command object executed");
            }
            r17.mInitPacketStream.reset();
            r3.reset();
            r5.offset = 0;
        }
        r0 = false;
        r14 = false;
        if (!r0) {
        }
        logi("Executing init packet (Op Code = 4)");
        writeExecute();
        r17.mService.sendLogBroadcast(10, "Command object executed");
    }

    /* JADX WARN: Code restructure failed: missing block: B:59:0x034e, code lost:
        if (r26.mPacketsBeforeNotification > 1) goto L29;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void sendFirmware(android.bluetooth.BluetoothGatt r27) throws no.nordicsemi.android.dfu.internal.exception.RemoteDfuException, no.nordicsemi.android.dfu.internal.exception.DeviceDisconnectedException, no.nordicsemi.android.dfu.internal.exception.DfuException, no.nordicsemi.android.dfu.internal.exception.UploadAbortedException, no.nordicsemi.android.dfu.internal.exception.UnknownResponseException {
        int r21;
        java.lang.String r10;
        int r12;
        boolean r0;
        java.lang.String r23;
        boolean r0;
        int r6 = r26.mPacketsBeforeNotification;
        if (r6 > 0) {
            setPacketReceiptNotifications(r6);
            r26.mService.sendLogBroadcast(10, "Packet Receipt Notif Req (Op Code = 2) sent (Value = " + r6 + ")");
        }
        logi("Setting object to Data (Op Code = 6, Type = 2)");
        no.nordicsemi.android.dfu.SecureDfuImpl.ObjectInfo r9 = selectObject(2);
        logi(java.lang.String.format(java.util.Locale.US, "Data object info received (Max size = %d, Offset = %d, CRC = %08X)", java.lang.Integer.valueOf(r9.maxSize), java.lang.Integer.valueOf(r9.offset), java.lang.Integer.valueOf(r9.CRC32)));
        r26.mService.sendLogBroadcast(10, java.lang.String.format(java.util.Locale.US, "Data object info received (Max size = %d, Offset = %d, CRC = %08X)", java.lang.Integer.valueOf(r9.maxSize), java.lang.Integer.valueOf(r9.offset), java.lang.Integer.valueOf(r9.CRC32)));
        r26.mProgressInfo.setMaxObjectSizeInBytes(r9.maxSize);
        int r8 = ((r26.mImageSizeInBytes + r9.maxSize) - 1) / r9.maxSize;
        if (r9.offset > 0) {
            try {
                int r10 = r9.offset / r9.maxSize;
                int r14 = r9.maxSize * r10;
                int r11 = r9.offset - r14;
                if (r11 == 0) {
                    r14 -= r9.maxSize;
                    r11 = r9.maxSize;
                }
                if (r14 > 0) {
                    r21 = r10;
                    r26.mFirmwareStream.read(new byte[r14]);
                    r26.mFirmwareStream.mark(r9.maxSize);
                } else {
                    r21 = r10;
                }
                r26.mFirmwareStream.read(new byte[r11]);
                r10 = ")";
                r12 = r8;
                if (((int) (((no.nordicsemi.android.dfu.internal.ArchiveInputStream) r26.mFirmwareStream).getCrc32() & 4294967295L)) == r9.CRC32) {
                    logi(r9.offset + " bytes of data sent before, CRC match");
                    r26.mService.sendLogBroadcast(10, r9.offset + " bytes of data sent before, CRC match");
                    r26.mProgressInfo.setBytesSent(r9.offset);
                    r26.mProgressInfo.setBytesReceived(r9.offset);
                    if (r11 != r9.maxSize || r9.offset >= r26.mImageSizeInBytes) {
                        r0 = true;
                    } else {
                        logi("Executing data object (Op Code = 4)");
                        try {
                            writeExecute();
                            r26.mService.sendLogBroadcast(10, "Data object executed");
                        } catch (no.nordicsemi.android.dfu.internal.exception.RemoteDfuException r0) {
                            if (r0.getErrorNumber() != 8) {
                                throw r0;
                            }
                            r26.mService.sendLogBroadcast(10, "Data object already executed");
                            r26.mRemoteErrorOccurred = false;
                        }
                    }
                } else {
                    logi(r9.offset + " bytes sent before, CRC does not match");
                    r26.mService.sendLogBroadcast(15, r9.offset + " bytes sent before, CRC does not match");
                    r26.mProgressInfo.setBytesSent(r14);
                    r26.mProgressInfo.setBytesReceived(r14);
                    r9.offset -= r11;
                    r9.CRC32 = 0;
                    r26.mFirmwareStream.reset();
                    logi("Resuming from byte " + r9.offset + "...");
                    r26.mService.sendLogBroadcast(10, "Resuming from byte " + r9.offset + "...");
                }
                r0 = false;
            } catch (java.io.IOException r0) {
                loge("Error while reading firmware stream", r0);
                r26.mService.terminateConnection(r27, no.nordicsemi.android.dfu.DfuBaseService.ERROR_FILE_IO_EXCEPTION);
                return;
            }
        } else {
            r10 = ")";
            r12 = r8;
            r26.mProgressInfo.setBytesSent(0);
            r0 = false;
            r21 = 0;
        }
        long r3 = android.os.SystemClock.elapsedRealtime();
        if (r9.offset < r26.mImageSizeInBytes) {
            int r5 = 1;
            while (r26.mProgressInfo.getAvailableObjectSizeIsBytes() > 0) {
                if (!r0) {
                    int r8 = r26.mProgressInfo.getAvailableObjectSizeIsBytes();
                    int r14 = r21 + 1;
                    boolean r22 = r0;
                    logi("Creating Data object (Op Code = 1, Type = 2, Size = " + r8 + ") (" + r14 + "/" + r12 + r10);
                    writeCreateRequest(2, r8);
                    r23 = r10;
                    r26.mService.sendLogBroadcast(10, "Data object (" + r14 + "/" + r12 + ") created");
                    if (r26.prepareObjectDelay > 0 || r12 == 0) {
                        no.nordicsemi.android.dfu.DfuBaseService r0 = r26.mService;
                        long r10 = r26.prepareObjectDelay;
                        if (r10 <= 0) {
                            r10 = 400;
                        }
                        r0.waitFor(r10);
                    }
                    r26.mService.sendLogBroadcast(10, "Uploading firmware...");
                    r0 = r22;
                } else {
                    r23 = r10;
                    r26.mService.sendLogBroadcast(10, "Resuming uploading firmware...");
                    r0 = false;
                }
                try {
                    logi("Uploading firmware...");
                    uploadFirmwareImage(r26.mPacketCharacteristic);
                    logi("Sending Calculate Checksum command (Op Code = 3)");
                    no.nordicsemi.android.dfu.SecureDfuImpl.ObjectChecksum r7 = readChecksum();
                    logi(java.lang.String.format(java.util.Locale.US, "Checksum received (Offset = %d, CRC = %08X)", java.lang.Integer.valueOf(r7.offset), java.lang.Integer.valueOf(r7.CRC32)));
                    boolean r22 = r0;
                    r26.mService.sendLogBroadcast(10, java.lang.String.format(java.util.Locale.US, "Checksum received (Offset = %d, CRC = %08X)", java.lang.Integer.valueOf(r7.offset), java.lang.Integer.valueOf(r7.CRC32)));
                    int r0 = r26.mProgressInfo.getBytesSent() - r7.offset;
                    if (r0 > 0) {
                        logw(r0 + " bytes were lost!");
                        r26.mService.sendLogBroadcast(15, r0 + " bytes were lost");
                        try {
                            r26.mFirmwareStream.reset();
                            r26.mFirmwareStream.read(new byte[r9.maxSize - r0]);
                            r26.mProgressInfo.setBytesSent(r7.offset);
                            int r10 = r26.mPacketsBeforeNotification != 0 ? 1 : 1;
                            r26.mPacketsBeforeNotification = r10;
                            setPacketReceiptNotifications(r10);
                            r26.mService.sendLogBroadcast(10, "Packet Receipt Notif Req (Op Code = 2) sent (Value = 1)");
                        } catch (java.io.IOException r0) {
                            loge("Error while reading firmware stream", r0);
                            r26.mService.terminateConnection(r27, no.nordicsemi.android.dfu.DfuBaseService.ERROR_FILE_IO_EXCEPTION);
                            return;
                        } catch (java.lang.Throwable r0) {
                            loge("Progress lost. Bytes sent: " + r26.mProgressInfo.getBytesSent(), r0);
                            r26.mService.terminateConnection(r27, no.nordicsemi.android.dfu.DfuBaseService.ERROR_PROGRESS_LOST);
                            return;
                        }
                    }
                    int r8 = (int) (((no.nordicsemi.android.dfu.internal.ArchiveInputStream) r26.mFirmwareStream).getCrc32() & 4294967295L);
                    if (r8 != r7.CRC32) {
                        java.lang.String r0 = java.lang.String.format(java.util.Locale.US, "CRC does not match! Expected %08X but found %08X.", java.lang.Integer.valueOf(r8), java.lang.Integer.valueOf(r7.CRC32));
                        if (r5 < 3) {
                            r5++;
                            java.lang.String r0 = r0 + java.lang.String.format(java.util.Locale.US, " Retrying...(%d/%d)", java.lang.Integer.valueOf(r5), 3);
                            logi(r0);
                            r26.mService.sendLogBroadcast(15, r0);
                            try {
                                r26.mFirmwareStream.reset();
                                r26.mProgressInfo.setBytesSent(((no.nordicsemi.android.dfu.internal.ArchiveInputStream) r26.mFirmwareStream).getBytesRead());
                            } catch (java.io.IOException r0) {
                                loge("Error while resetting the firmware stream", r0);
                                r26.mService.terminateConnection(r27, no.nordicsemi.android.dfu.DfuBaseService.ERROR_FILE_IO_EXCEPTION);
                                return;
                            }
                        } else {
                            loge(r0);
                            r26.mService.sendLogBroadcast(20, r0);
                            r26.mService.terminateConnection(r27, no.nordicsemi.android.dfu.DfuBaseService.ERROR_CRC_ERROR);
                            return;
                        }
                    } else if (r0 > 0) {
                        r10 = r23;
                        r0 = true;
                    } else {
                        logi("Executing data object (Op Code = 4)");
                        writeExecute(r26.mProgressInfo.isComplete());
                        r26.mService.sendLogBroadcast(10, "Data object executed");
                        r21++;
                        r26.mFirmwareStream.mark(0);
                        r5 = 1;
                    }
                    r0 = r22;
                    r10 = r23;
                } catch (no.nordicsemi.android.dfu.internal.exception.DeviceDisconnectedException r0) {
                    loge("Disconnected while sending data");
                    throw r0;
                }
            }
        } else {
            logi("Executing data object (Op Code = 4)");
            writeExecute(true);
            r26.mService.sendLogBroadcast(10, "Data object executed");
        }
        long r5 = android.os.SystemClock.elapsedRealtime() - r3;
        logi("Transfer of " + (r26.mProgressInfo.getBytesSent() - r9.offset) + " bytes has taken " + r5 + " ms");
        r26.mService.sendLogBroadcast(10, "Upload completed in " + r5 + " ms");
    }

    private int getStatusCode(byte[] bArr, int i) throws UnknownResponseException {
        if (bArr == null || bArr.length < 3 || bArr[0] != 96 || bArr[1] != i || (bArr[2] != 1 && bArr[2] != 2 && bArr[2] != 3 && bArr[2] != 4 && bArr[2] != 5 && bArr[2] != 7 && bArr[2] != 8 && bArr[2] != 10 && bArr[2] != 11)) {
            throw new UnknownResponseException("Invalid response received", bArr, 96, i);
        }
        return bArr[2];
    }

    private void setNumberOfPackets(byte[] bArr, int i) {
        bArr[1] = (byte) (i & 255);
        bArr[2] = (byte) ((i >> 8) & 255);
    }

    private void setObjectSize(byte[] bArr, int i) {
        bArr[2] = (byte) (i & 255);
        bArr[3] = (byte) ((i >> 8) & 255);
        bArr[4] = (byte) ((i >> 16) & 255);
        bArr[5] = (byte) ((i >> 24) & 255);
    }

    private void setPacketReceiptNotifications(int i) throws DfuException, DeviceDisconnectedException, UploadAbortedException, UnknownResponseException, RemoteDfuException {
        if (!this.mConnected) {
            throw new DeviceDisconnectedException("Unable to read Checksum: device disconnected");
        }
        logi("Sending the number of packets before notifications (Op Code = 2, Value = " + i + ")");
        byte[] bArr = OP_CODE_PACKET_RECEIPT_NOTIF_REQ;
        setNumberOfPackets(bArr, i);
        writeOpCode(this.mControlPointCharacteristic, bArr);
        byte[] readNotificationResponse = readNotificationResponse();
        int statusCode = getStatusCode(readNotificationResponse, 2);
        if (statusCode == 11) {
            throw new RemoteDfuExtendedErrorException("Sending the number of packets failed", readNotificationResponse[3]);
        }
        if (statusCode != 1) {
            throw new RemoteDfuException("Sending the number of packets failed", statusCode);
        }
    }

    private void writeOpCode(BluetoothGattCharacteristic bluetoothGattCharacteristic, byte[] bArr) throws DeviceDisconnectedException, DfuException, UploadAbortedException {
        writeOpCode(bluetoothGattCharacteristic, bArr, false);
    }

    private void writeCreateRequest(int i, int i2) throws DeviceDisconnectedException, DfuException, UploadAbortedException, RemoteDfuException, UnknownResponseException {
        if (!this.mConnected) {
            throw new DeviceDisconnectedException("Unable to create object: device disconnected");
        }
        byte[] bArr = i == 1 ? OP_CODE_CREATE_COMMAND : OP_CODE_CREATE_DATA;
        setObjectSize(bArr, i2);
        writeOpCode(this.mControlPointCharacteristic, bArr);
        byte[] readNotificationResponse = readNotificationResponse();
        int statusCode = getStatusCode(readNotificationResponse, 1);
        if (statusCode == 11) {
            throw new RemoteDfuExtendedErrorException("Creating Command object failed", readNotificationResponse[3]);
        }
        if (statusCode != 1) {
            throw new RemoteDfuException("Creating Command object failed", statusCode);
        }
    }

    private ObjectInfo selectObject(int i) throws DeviceDisconnectedException, DfuException, UploadAbortedException, RemoteDfuException, UnknownResponseException {
        if (!this.mConnected) {
            throw new DeviceDisconnectedException("Unable to read object info: device disconnected");
        }
        byte[] bArr = OP_CODE_SELECT_OBJECT;
        bArr[1] = (byte) i;
        writeOpCode(this.mControlPointCharacteristic, bArr);
        byte[] readNotificationResponse = readNotificationResponse();
        int statusCode = getStatusCode(readNotificationResponse, 6);
        if (statusCode != 11) {
            if (statusCode != 1) {
                throw new RemoteDfuException("Selecting object failed", statusCode);
            }
            ObjectInfo objectInfo = new ObjectInfo();
            objectInfo.maxSize = unsignedBytesToInt(readNotificationResponse, 3);
            objectInfo.offset = unsignedBytesToInt(readNotificationResponse, 7);
            objectInfo.CRC32 = unsignedBytesToInt(readNotificationResponse, 11);
            return objectInfo;
        }
        throw new RemoteDfuExtendedErrorException("Selecting object failed", readNotificationResponse[3]);
    }

    private ObjectChecksum readChecksum() throws DeviceDisconnectedException, DfuException, UploadAbortedException, RemoteDfuException, UnknownResponseException {
        if (!this.mConnected) {
            throw new DeviceDisconnectedException("Unable to read Checksum: device disconnected");
        }
        writeOpCode(this.mControlPointCharacteristic, OP_CODE_CALCULATE_CHECKSUM);
        byte[] readNotificationResponse = readNotificationResponse();
        int statusCode = getStatusCode(readNotificationResponse, 3);
        if (statusCode != 11) {
            if (statusCode != 1) {
                throw new RemoteDfuException("Receiving Checksum failed", statusCode);
            }
            ObjectChecksum objectChecksum = new ObjectChecksum();
            objectChecksum.offset = unsignedBytesToInt(readNotificationResponse, 3);
            objectChecksum.CRC32 = unsignedBytesToInt(readNotificationResponse, 7);
            return objectChecksum;
        }
        throw new RemoteDfuExtendedErrorException("Receiving Checksum failed", readNotificationResponse[3]);
    }

    private int unsignedBytesToInt(byte[] bArr, int i) {
        return (bArr[i] & UByte.MAX_VALUE) + ((bArr[i + 1] & UByte.MAX_VALUE) << 8) + ((bArr[i + 2] & UByte.MAX_VALUE) << 16) + ((bArr[i + 3] & UByte.MAX_VALUE) << 24);
    }

    private void writeExecute() throws DfuException, DeviceDisconnectedException, UploadAbortedException, UnknownResponseException, RemoteDfuException {
        if (!this.mConnected) {
            throw new DeviceDisconnectedException("Unable to read Checksum: device disconnected");
        }
        writeOpCode(this.mControlPointCharacteristic, OP_CODE_EXECUTE);
        byte[] readNotificationResponse = readNotificationResponse();
        int statusCode = getStatusCode(readNotificationResponse, 4);
        if (statusCode == 11) {
            throw new RemoteDfuExtendedErrorException("Executing object failed", readNotificationResponse[3]);
        }
        if (statusCode != 1) {
            throw new RemoteDfuException("Executing object failed", statusCode);
        }
    }

    private void writeExecute(boolean z) throws DfuException, DeviceDisconnectedException, UploadAbortedException, UnknownResponseException, RemoteDfuException {
        try {
            writeExecute();
        } catch (RemoteDfuException e) {
            if (z && e.getErrorNumber() == 5) {
                logw(e.getMessage() + ": " + SecureDfuError.parse(517));
                if (this.mFileType == 1) {
                    logw("Are you sure your new SoftDevice is API compatible with the updated one? If not, update the bootloader as well");
                }
                this.mService.sendLogBroadcast(15, String.format(Locale.US, "Remote DFU error: %s. SD busy? Retrying...", SecureDfuError.parse(517)));
                logi("SD busy? Retrying...");
                logi("Executing data object (Op Code = 4)");
                writeExecute();
                return;
            }
            throw e;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class ObjectInfo extends ObjectChecksum {
        int maxSize;

        private ObjectInfo() {
            super();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class ObjectChecksum {
        int CRC32;
        int offset;

        private ObjectChecksum() {
        }
    }
}
