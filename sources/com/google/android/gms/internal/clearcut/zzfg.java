package com.google.android.gms.internal.clearcut;

import com.egeetouch.egeetouch_manager.BLEService;
import java.nio.ByteBuffer;
/* loaded from: classes.dex */
abstract class zzfg {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zzc(CharSequence charSequence, ByteBuffer byteBuffer) {
        int length = charSequence.length();
        int position = byteBuffer.position();
        int i = 0;
        while (i < length) {
            try {
                char charAt = charSequence.charAt(i);
                if (charAt >= 128) {
                    break;
                }
                byteBuffer.put(position + i, (byte) charAt);
                i++;
            } catch (IndexOutOfBoundsException unused) {
                throw new ArrayIndexOutOfBoundsException(new StringBuilder(37).append("Failed writing ").append(charSequence.charAt(i)).append(" at index ").append(byteBuffer.position() + Math.max(i, (position - byteBuffer.position()) + 1)).toString());
            }
        }
        if (i == length) {
            byteBuffer.position(position + i);
            return;
        }
        position += i;
        while (i < length) {
            char charAt2 = charSequence.charAt(i);
            if (charAt2 < 128) {
                byteBuffer.put(position, (byte) charAt2);
            } else if (charAt2 < 2048) {
                int i2 = position + 1;
                try {
                    byteBuffer.put(position, (byte) ((charAt2 >>> 6) | 192));
                    byteBuffer.put(i2, (byte) ((charAt2 & '?') | 128));
                    position = i2;
                } catch (IndexOutOfBoundsException unused2) {
                    position = i2;
                    throw new ArrayIndexOutOfBoundsException(new StringBuilder(37).append("Failed writing ").append(charSequence.charAt(i)).append(" at index ").append(byteBuffer.position() + Math.max(i, (position - byteBuffer.position()) + 1)).toString());
                }
            } else if (charAt2 >= 55296 && 57343 >= charAt2) {
                int i3 = i + 1;
                if (i3 != length) {
                    try {
                        char charAt3 = charSequence.charAt(i3);
                        if (Character.isSurrogatePair(charAt2, charAt3)) {
                            int codePoint = Character.toCodePoint(charAt2, charAt3);
                            int i4 = position + 1;
                            try {
                                byteBuffer.put(position, (byte) ((codePoint >>> 18) | BLEService.ReadResponse_ShackleBypass));
                                int i5 = i4 + 1;
                                byteBuffer.put(i4, (byte) (((codePoint >>> 12) & 63) | 128));
                                int i6 = i5 + 1;
                                byteBuffer.put(i5, (byte) (((codePoint >>> 6) & 63) | 128));
                                byteBuffer.put(i6, (byte) ((codePoint & 63) | 128));
                                position = i6;
                                i = i3;
                            } catch (IndexOutOfBoundsException unused3) {
                                position = i4;
                                i = i3;
                                throw new ArrayIndexOutOfBoundsException(new StringBuilder(37).append("Failed writing ").append(charSequence.charAt(i)).append(" at index ").append(byteBuffer.position() + Math.max(i, (position - byteBuffer.position()) + 1)).toString());
                            }
                        } else {
                            i = i3;
                        }
                    } catch (IndexOutOfBoundsException unused4) {
                    }
                }
                throw new zzfi(i, length);
            } else {
                int i7 = position + 1;
                byteBuffer.put(position, (byte) ((charAt2 >>> '\f') | 224));
                position = i7 + 1;
                byteBuffer.put(i7, (byte) (((charAt2 >>> 6) & 63) | 128));
                byteBuffer.put(position, (byte) ((charAt2 & '?') | 128));
            }
            i++;
            position++;
        }
        byteBuffer.position(position);
    }

    abstract int zzb(int i, byte[] bArr, int i2, int i3);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract int zzb(CharSequence charSequence, byte[] bArr, int i, int i2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zzb(CharSequence charSequence, ByteBuffer byteBuffer);

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean zze(byte[] bArr, int i, int i2) {
        return zzb(0, bArr, i, i2) == 0;
    }
}
