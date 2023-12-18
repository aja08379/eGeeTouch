package com.google.android.gms.internal.p001firebaseauthapi;

import com.google.android.gms.internal.p001firebaseauthapi.zzadb;
import com.google.android.gms.internal.p001firebaseauthapi.zzadf;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzadf  reason: invalid package */
/* loaded from: classes.dex */
public abstract class zzadf<MessageType extends zzadf<MessageType, BuilderType>, BuilderType extends zzadb<MessageType, BuilderType>> extends zzabm<MessageType, BuilderType> {
    private static final Map zzb = new ConcurrentHashMap();
    private int zzd = -1;
    protected zzafo zzc = zzafo.zzc();

    /* JADX INFO: Access modifiers changed from: protected */
    public static zzadk zzA(zzadk zzadkVar) {
        int size = zzadkVar.size();
        return zzadkVar.zzd(size == 0 ? 10 : size + size);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object zzC(Method method, Object obj, Object... objArr) {
        try {
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Couldn't use Java reflection to implement protocol message reflection.", e);
        } catch (InvocationTargetException e2) {
            Throwable cause = e2.getCause();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            }
            if (cause instanceof Error) {
                throw ((Error) cause);
            }
            throw new RuntimeException("Unexpected exception thrown by generated accessor method.", cause);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static Object zzD(zzaek zzaekVar, String str, Object[] objArr) {
        return new zzaeu(zzaekVar, str, objArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void zzG(Class cls, zzadf zzadfVar) {
        zzb.put(cls, zzadfVar);
        zzadfVar.zzE();
    }

    private final int zza(zzaew zzaewVar) {
        if (zzaewVar == null) {
            return zzaes.zza().zzb(getClass()).zza(this);
        }
        return zzaewVar.zza(this);
    }

    private static zzadf zzb(zzadf zzadfVar) throws zzadn {
        if (zzadfVar == null || zzadfVar.zzJ()) {
            return zzadfVar;
        }
        zzadn zza = new zzafm(zzadfVar).zza();
        zza.zzh(zzadfVar);
        throw zza;
    }

    private static zzadf zzc(zzadf zzadfVar, byte[] bArr, int i, int i2, zzacs zzacsVar) throws zzadn {
        zzadf zzw = zzadfVar.zzw();
        try {
            zzaew zzb2 = zzaes.zza().zzb(zzw.getClass());
            zzb2.zzi(zzw, bArr, 0, i2, new zzabp(zzacsVar));
            zzb2.zzf(zzw);
            return zzw;
        } catch (zzadn e) {
            e.zzh(zzw);
            throw e;
        } catch (zzafm e2) {
            zzadn zza = e2.zza();
            zza.zzh(zzw);
            throw zza;
        } catch (IOException e3) {
            if (e3.getCause() instanceof zzadn) {
                throw ((zzadn) e3.getCause());
            }
            zzadn zzadnVar = new zzadn(e3);
            zzadnVar.zzh(zzw);
            throw zzadnVar;
        } catch (IndexOutOfBoundsException unused) {
            zzadn zzi = zzadn.zzi();
            zzi.zzh(zzw);
            throw zzi;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzadf zzv(Class cls) {
        Map map = zzb;
        zzadf zzadfVar = (zzadf) map.get(cls);
        if (zzadfVar == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                zzadfVar = (zzadf) map.get(cls);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Class initialization cannot fail.", e);
            }
        }
        if (zzadfVar == null) {
            zzadfVar = (zzadf) ((zzadf) zzafx.zze(cls)).zzj(6, null, null);
            if (zzadfVar == null) {
                throw new IllegalStateException();
            }
            map.put(cls, zzadfVar);
        }
        return zzadfVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static zzadf zzx(zzadf zzadfVar, zzacc zzaccVar, zzacs zzacsVar) throws zzadn {
        zzacg zzh = zzaccVar.zzh();
        zzadf zzw = zzadfVar.zzw();
        try {
            zzaew zzb2 = zzaes.zza().zzb(zzw.getClass());
            zzb2.zzh(zzw, zzach.zzq(zzh), zzacsVar);
            zzb2.zzf(zzw);
            try {
                zzh.zzm(0);
                zzb(zzw);
                return zzw;
            } catch (zzadn e) {
                e.zzh(zzw);
                throw e;
            }
        } catch (zzadn e2) {
            e2.zzh(zzw);
            throw e2;
        } catch (zzafm e3) {
            zzadn zza = e3.zza();
            zza.zzh(zzw);
            throw zza;
        } catch (IOException e4) {
            if (e4.getCause() instanceof zzadn) {
                throw ((zzadn) e4.getCause());
            }
            zzadn zzadnVar = new zzadn(e4);
            zzadnVar.zzh(zzw);
            throw zzadnVar;
        } catch (RuntimeException e5) {
            if (e5.getCause() instanceof zzadn) {
                throw ((zzadn) e5.getCause());
            }
            throw e5;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static zzadf zzy(zzadf zzadfVar, byte[] bArr, zzacs zzacsVar) throws zzadn {
        zzadf zzc = zzc(zzadfVar, bArr, 0, bArr.length, zzacsVar);
        zzb(zzc);
        return zzc;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static zzadk zzz() {
        return zzaet.zze();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            return zzaes.zza().zzb(getClass()).zzj(this, (zzadf) obj);
        }
        return false;
    }

    public final int hashCode() {
        if (zzK()) {
            return zzr();
        }
        int i = this.zza;
        if (i == 0) {
            int zzr = zzr();
            this.zza = zzr;
            return zzr;
        }
        return i;
    }

    public final String toString() {
        return zzaem.zza(this, super.toString());
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaek
    public final /* synthetic */ zzaej zzB() {
        return (zzadb) zzj(5, null, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void zzE() {
        zzaes.zza().zzb(getClass()).zzf(this);
        zzF();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzF() {
        this.zzd &= Integer.MAX_VALUE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzH(int i) {
        this.zzd = (this.zzd & Integer.MIN_VALUE) | Integer.MAX_VALUE;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaek
    public final void zzI(zzacn zzacnVar) throws IOException {
        zzaes.zza().zzb(getClass()).zzn(this, zzaco.zza(zzacnVar));
    }

    public final boolean zzJ() {
        boolean booleanValue = Boolean.TRUE.booleanValue();
        byte byteValue = ((Byte) zzj(1, null, null)).byteValue();
        if (byteValue == 1) {
            return true;
        }
        if (byteValue == 0) {
            return false;
        }
        boolean zzk = zzaes.zza().zzb(getClass()).zzk(this);
        if (booleanValue) {
            zzj(2, true != zzk ? null : this, null);
            return zzk;
        }
        return zzk;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean zzK() {
        return (this.zzd & Integer.MIN_VALUE) != 0;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzael
    public final /* synthetic */ zzaek zzL() {
        return (zzadf) zzj(6, null, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract Object zzj(int i, Object obj, Object obj2);

    final int zzr() {
        return zzaes.zza().zzb(getClass()).zzb(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final zzadb zzt() {
        return (zzadb) zzj(5, null, null);
    }

    public final zzadb zzu() {
        zzadb zzadbVar = (zzadb) zzj(5, null, null);
        zzadbVar.zzh(this);
        return zzadbVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzadf zzw() {
        return (zzadf) zzj(4, null, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzabm
    public final int zzn(zzaew zzaewVar) {
        if (zzK()) {
            int zza = zza(zzaewVar);
            if (zza >= 0) {
                return zza;
            }
            throw new IllegalStateException("serialized size must be non-negative, was " + zza);
        }
        int i = this.zzd & Integer.MAX_VALUE;
        if (i != Integer.MAX_VALUE) {
            return i;
        }
        int zza2 = zza(zzaewVar);
        if (zza2 >= 0) {
            this.zzd = (this.zzd & Integer.MIN_VALUE) | zza2;
            return zza2;
        }
        throw new IllegalStateException("serialized size must be non-negative, was " + zza2);
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzaek
    public final int zzs() {
        int i;
        if (zzK()) {
            i = zza(null);
            if (i < 0) {
                throw new IllegalStateException("serialized size must be non-negative, was " + i);
            }
        } else {
            i = this.zzd & Integer.MAX_VALUE;
            if (i == Integer.MAX_VALUE) {
                i = zza(null);
                if (i >= 0) {
                    this.zzd = (this.zzd & Integer.MIN_VALUE) | i;
                } else {
                    throw new IllegalStateException("serialized size must be non-negative, was " + i);
                }
            }
        }
        return i;
    }
}
