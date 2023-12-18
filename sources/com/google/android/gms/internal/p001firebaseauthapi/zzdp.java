package com.google.android.gms.internal.p001firebaseauthapi;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import javax.crypto.AEADBadTagException;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzdp  reason: invalid package */
/* loaded from: classes.dex */
abstract class zzdp {
    private final zzdn zza;
    private final zzdn zzb;

    public zzdp(byte[] bArr) throws GeneralSecurityException {
        if (!zzdv.zza(1)) {
            throw new GeneralSecurityException("Can not use ChaCha20Poly1305 in FIPS-mode.");
        }
        this.zza = zza(bArr, 1);
        this.zzb = zza(bArr, 0);
    }

    abstract zzdn zza(byte[] bArr, int i) throws InvalidKeyException;

    public final byte[] zzb(ByteBuffer byteBuffer, byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        if (byteBuffer.remaining() < 16) {
            throw new GeneralSecurityException("ciphertext too short");
        }
        int position = byteBuffer.position();
        byte[] bArr3 = new byte[16];
        byteBuffer.position(byteBuffer.limit() - 16);
        byteBuffer.get(bArr3);
        byteBuffer.position(position);
        byteBuffer.limit(byteBuffer.limit() - 16);
        try {
            byte[] bArr4 = new byte[32];
            this.zzb.zzc(bArr, 0).get(bArr4);
            int remaining = byteBuffer.remaining();
            int i = remaining % 16;
            int i2 = i == 0 ? remaining : (remaining + 16) - i;
            ByteBuffer order = ByteBuffer.allocate(i2 + 16).order(ByteOrder.LITTLE_ENDIAN);
            order.put(bArr2);
            order.position(0);
            order.put(byteBuffer);
            order.position(i2);
            order.putLong(0L);
            order.putLong(remaining);
            if (zzpp.zzb(zzds.zza(bArr4, order.array()), bArr3)) {
                byteBuffer.position(position);
                return this.zza.zzd(bArr, byteBuffer);
            }
            throw new GeneralSecurityException("invalid MAC");
        } catch (GeneralSecurityException e) {
            throw new AEADBadTagException(e.toString());
        }
    }

    public final byte[] zzc(byte[] bArr, byte[] bArr2, byte[] bArr3) throws GeneralSecurityException {
        return zzb(ByteBuffer.wrap(bArr2), bArr, bArr3);
    }
}
