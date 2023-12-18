package com.google.android.gms.internal.clearcut;

import com.google.android.gms.internal.clearcut.zzas;
import com.google.android.gms.internal.clearcut.zzat;
import java.io.IOException;
/* loaded from: classes.dex */
public abstract class zzas<MessageType extends zzas<MessageType, BuilderType>, BuilderType extends zzat<MessageType, BuilderType>> implements zzdo {
    private static boolean zzey = false;
    protected int zzex = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public void zzf(int i) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.android.gms.internal.clearcut.zzdo
    public final zzbb zzr() {
        try {
            zzbg zzk = zzbb.zzk(zzas());
            zzb(zzk.zzae());
            return zzk.zzad();
        } catch (IOException e) {
            String name = getClass().getName();
            throw new RuntimeException(new StringBuilder(String.valueOf(name).length() + 62 + "ByteString".length()).append("Serializing ").append(name).append(" to a ").append("ByteString").append(" threw an IOException (should never happen).").toString(), e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int zzs() {
        throw new UnsupportedOperationException();
    }
}
