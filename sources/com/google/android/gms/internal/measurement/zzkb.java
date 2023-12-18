package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzkb;
import com.google.android.gms.internal.measurement.zzkf;
import java.io.IOException;
/* compiled from: com.google.android.gms:play-services-measurement-base@@21.2.0 */
/* loaded from: classes.dex */
public class zzkb<MessageType extends zzkf<MessageType, BuilderType>, BuilderType extends zzkb<MessageType, BuilderType>> extends zzin<MessageType, BuilderType> {
    protected zzkf zza;
    private final zzkf zzb;

    /* JADX INFO: Access modifiers changed from: protected */
    public zzkb(MessageType messagetype) {
        this.zzb = messagetype;
        if (!messagetype.zzbO()) {
            this.zza = messagetype.zzbA();
            return;
        }
        throw new IllegalArgumentException("Default instance must be immutable.");
    }

    private static void zza(Object obj, Object obj2) {
        zzlu.zza().zzb(obj.getClass()).zzg(obj, obj2);
    }

    public final zzkb zzaA(zzkf zzkfVar) {
        if (!this.zzb.equals(zzkfVar)) {
            if (!this.zza.zzbO()) {
                zzaH();
            }
            zza(this.zza, zzkfVar);
        }
        return this;
    }

    public final zzkb zzaB(byte[] bArr, int i, int i2, zzjr zzjrVar) throws zzkp {
        if (!this.zza.zzbO()) {
            zzaH();
        }
        try {
            zzlu.zza().zzb(this.zza.getClass()).zzh(this.zza, bArr, 0, i2, new zzir(zzjrVar));
            return this;
        } catch (zzkp e) {
            throw e;
        } catch (IOException e2) {
            throw new RuntimeException("Reading from byte array should not throw IOException.", e2);
        } catch (IndexOutOfBoundsException unused) {
            throw zzkp.zzf();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0036, code lost:
        if (r4 != false) goto L14;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final MessageType zzaC() {
        MessageType r0 = zzaE();
        boolean r1 = java.lang.Boolean.TRUE.booleanValue();
        byte r4 = ((java.lang.Byte) r0.zzl(1, null, null)).byteValue();
        if (r4 != 1) {
            if (r4 != 0) {
                boolean r4 = com.google.android.gms.internal.measurement.zzlu.zza().zzb(r0.getClass()).zzk(r0);
                if (r1) {
                    r0.zzl(2, true != r4 ? null : r0, null);
                }
            }
            throw new com.google.android.gms.internal.measurement.zzmn(r0);
        }
        return r0;
    }

    @Override // com.google.android.gms.internal.measurement.zzll
    /* renamed from: zzaD */
    public MessageType zzaE() {
        if (this.zza.zzbO()) {
            this.zza.zzbJ();
            return (MessageType) this.zza;
        }
        return (MessageType) this.zza;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void zzaG() {
        if (this.zza.zzbO()) {
            return;
        }
        zzaH();
    }

    protected void zzaH() {
        zzkf zzbA = this.zzb.zzbA();
        zza(zzbA, this.zza);
        this.zza = zzbA;
    }

    @Override // com.google.android.gms.internal.measurement.zzin
    public final /* bridge */ /* synthetic */ zzin zzav(byte[] bArr, int i, int i2) throws zzkp {
        zzaB(bArr, 0, i2, zzjr.zza);
        return this;
    }

    @Override // com.google.android.gms.internal.measurement.zzin
    public final /* bridge */ /* synthetic */ zzin zzaw(byte[] bArr, int i, int i2, zzjr zzjrVar) throws zzkp {
        zzaB(bArr, 0, i2, zzjrVar);
        return this;
    }

    @Override // com.google.android.gms.internal.measurement.zzin
    /* renamed from: zzaz */
    public final zzkb zzau() {
        zzkb zzkbVar = (zzkb) this.zzb.zzl(5, null, null);
        zzkbVar.zza = zzaE();
        return zzkbVar;
    }

    @Override // com.google.android.gms.internal.measurement.zzln
    public final /* bridge */ /* synthetic */ zzlm zzbS() {
        throw null;
    }
}
