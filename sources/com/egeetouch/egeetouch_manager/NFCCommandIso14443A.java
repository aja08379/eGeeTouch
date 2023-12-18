package com.egeetouch.egeetouch_manager;

import android.nfc.Tag;
import android.nfc.tech.IsoDep;
import com.egeetouch.egeetouch_manager.MainActivity;
import kotlin.jvm.internal.ByteCompanionObject;
/* loaded from: classes.dex */
public class NFCCommandIso14443A {
    public static byte[] bufferData;
    public static int bufferSize;
    public static int chunkSize;
    public static int lastBuffOffsetSent;
    public static int lastChunkIDsent;
    public static int nbChunk;

    /* loaded from: classes.dex */
    public enum NFCCommandStatus {
        CMD_OK,
        CMD_SELECTAPPLIERR,
        CMD_UPDATESIZEINFERR,
        CMD_UPLOADBUFFEREXCEPTIONERR,
        CMD_SENDCHUNCKERR,
        CMD_CLOSEFILEMSGERR,
        CMD_CRCMSGINGERR,
        CMD_LAUNCHACTIONERR,
        CMD_TAGUNREACHABLEERR,
        CMD_STATUSUNKNOWN
    }

    public NFCCommandIso14443A() {
        chunkSize = BLEService.ReadResponse_ShackleBypass;
        lastChunkIDsent = 0;
        lastBuffOffsetSent = 0;
        nbChunk = 0;
        bufferSize = 0;
    }

    public void init(byte[] bArr) {
        bufferData = bArr;
        int length = bArr.length;
        bufferSize = length;
        nbChunk = length / chunkSize;
    }

    public void reset() {
        chunkSize = BLEService.ReadResponse_ShackleBypass;
        lastChunkIDsent = 0;
        lastBuffOffsetSent = 0;
        nbChunk = 0;
        bufferSize = 0;
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x002e, code lost:
        return r2;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static byte[] APDUsendSelectAppli(android.nfc.Tag r7) {
        byte[] r0 = {0, -92, 4, 0, 16, -16, 2, 70, 87, 85, 95, 88, 88, 79, 95, 118, 48, 0, 0, 0, 0};
        byte[] r2 = {1};
        int r4 = 0;
        while (true) {
            if ((r2[0] == 1 || r2[0] == -86) && r4 <= 1) {
                android.nfc.tech.IsoDep r5 = android.nfc.tech.IsoDep.get(r7);
                try {
                    r5.connect();
                    r5.setTimeout(20);
                    return r5.transceive(r0);
                } catch (java.lang.Exception unused) {
                    r4++;
                }
            }
        }
    }

    public NFCCommandStatus APDUsendUpdateBinaryNew(boolean z, Tag tag, long j, MainActivity.StartLoadFromFileTask startLoadFromFileTask) {
        byte b;
        int i;
        byte[] bArr = {0, -92, 4, 0, 16, -16, 2, 70, 87, 85, 95, 88, 88, 79, 95, 118, 48, 0, 0, 0, 0};
        byte[] bArr2 = {1};
        int i2 = bufferSize % chunkSize;
        IsoDep isoDep = IsoDep.get(tag);
        try {
            isoDep.close();
            isoDep.connect();
            isoDep.setTimeout(200);
            if (!isoDep.isConnected()) {
                MainActivity.transmission_error = true;
                return NFCCommandStatus.CMD_TAGUNREACHABLEERR;
            }
            int i3 = 1;
            while (i3 > 0 && i3 < 3) {
                if (isoDep.isConnected() && !startLoadFromFileTask.isCancelled()) {
                    bArr2 = isoDep.transceive(bArr);
                } else {
                    bArr2[0] = 0;
                }
                i3 = (bArr2[0] == -112 && bArr2[1] == 0) ? 0 : i3 + 1;
            }
            if (i3 == 3) {
                MainActivity.transmission_error = true;
                return NFCCommandStatus.CMD_SELECTAPPLIERR;
            }
            byte[] ConvertIntTo2bytesHexaFormat = Helper_NFC.ConvertIntTo2bytesHexaFormat(bufferSize);
            int i4 = 1;
            while (i4 > 0 && i4 < 3) {
                byte[] bArr3 = {-94, 65, ByteCompanionObject.MIN_VALUE, 0, 2, ConvertIntTo2bytesHexaFormat[1], ConvertIntTo2bytesHexaFormat[0]};
                if (isoDep.isConnected() && !startLoadFromFileTask.isCancelled()) {
                    bArr2 = isoDep.transceive(bArr3);
                } else {
                    bArr2[0] = 0;
                }
                i4 = (bArr2[0] == -112 && bArr2[1] == 0) ? 0 : i4 + 1;
            }
            if (i4 == 3) {
                MainActivity.transmission_error = true;
                return NFCCommandStatus.CMD_UPDATESIZEINFERR;
            }
            if (!z) {
                lastChunkIDsent = 0;
            }
            for (int i5 = lastChunkIDsent; i5 <= nbChunk; i5++) {
                int i6 = bufferSize;
                int i7 = chunkSize;
                if (i5 == i6 / i7) {
                    b = (byte) i2;
                    i = i2;
                } else {
                    b = (byte) i7;
                    i = i7;
                }
                byte[] ConvertIntTo2bytesHexaFormat2 = Helper_NFC.ConvertIntTo2bytesHexaFormat(i7 * i5);
                byte[] bArr4 = new byte[i + 5];
                bArr4[0] = 0;
                bArr4[1] = -42;
                bArr4[2] = ConvertIntTo2bytesHexaFormat2[1];
                bArr4[3] = ConvertIntTo2bytesHexaFormat2[0];
                bArr4[4] = b;
                for (int i8 = 0; i8 < i; i8++) {
                    bArr4[i8 + 5] = bufferData[(chunkSize * i5) + i8];
                }
                int i9 = 1;
                while (i9 > 0 && i9 < 3) {
                    if (isoDep.isConnected() && !startLoadFromFileTask.isCancelled()) {
                        bArr2 = isoDep.transceive(bArr4);
                    } else {
                        bArr2[0] = 0;
                    }
                    if (bArr2[0] == -112 && bArr2[1] == 0) {
                        int i10 = i5 + 1;
                        lastChunkIDsent = i10;
                        int i11 = chunkSize;
                        int i12 = i10 * i11;
                        lastBuffOffsetSent = i12;
                        if (i5 == bufferSize / i11) {
                            i11 = i2;
                        }
                        lastBuffOffsetSent = i12 + i11;
                        i9 = 0;
                    } else if (TaskManagement.process_incoming_data(bArr2)) {
                        i9++;
                    }
                }
                if (i9 == 3) {
                    MainActivity.transmission_error = true;
                    return NFCCommandStatus.CMD_SENDCHUNCKERR;
                }
            }
            return NFCCommandStatus.CMD_OK;
        } catch (Exception unused) {
            return NFCCommandStatus.CMD_UPLOADBUFFEREXCEPTIONERR;
        }
    }
}
