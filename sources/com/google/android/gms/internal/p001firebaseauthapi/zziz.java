package com.google.android.gms.internal.p001firebaseauthapi;

import com.egeetouch.egeetouch_manager.BLEService;
import com.egeetouch.egeetouch_manager.TaskManagement;
import java.util.Arrays;
import kotlin.jvm.internal.ByteCompanionObject;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zziz  reason: invalid package */
/* loaded from: classes.dex */
public final class zziz {
    public static byte[] zza(byte[] bArr) {
        int length = bArr.length;
        if (length >= 16) {
            throw new IllegalArgumentException("x must be smaller than a block.");
        }
        byte[] copyOf = Arrays.copyOf(bArr, 16);
        copyOf[length] = ByteCompanionObject.MIN_VALUE;
        return copyOf;
    }

    public static byte[] zzb(byte[] bArr) {
        if (bArr.length != 16) {
            throw new IllegalArgumentException("value must be a block.");
        }
        byte[] bArr2 = new byte[16];
        for (int i = 0; i < 16; i++) {
            byte b = bArr[i];
            byte b2 = (byte) ((b + b) & BLEService.ReadResponse_clear_passcodeAuditTrail);
            bArr2[i] = b2;
            if (i < 15) {
                bArr2[i] = (byte) (((bArr[i + 1] >> 7) & 1) | b2);
            }
        }
        bArr2[15] = (byte) (((byte) ((bArr[0] >> 7) & TaskManagement.restore_user3)) ^ bArr2[15]);
        return bArr2;
    }
}
