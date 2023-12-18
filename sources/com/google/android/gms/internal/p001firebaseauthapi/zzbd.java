package com.google.android.gms.internal.p001firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Level;
import java.util.logging.Logger;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzbd  reason: invalid package */
/* loaded from: classes.dex */
final class zzbd {
    private static final Logger zza = Logger.getLogger(zzbd.class.getName());
    private final ConcurrentMap zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbd() {
        this.zzb = new ConcurrentHashMap();
    }

    private final zzax zzg(String str, Class cls) throws GeneralSecurityException {
        zzbc zzh = zzh(str);
        if (cls == null) {
            return zzh.zzb();
        }
        if (zzh.zze().contains(cls)) {
            return zzh.zza(cls);
        }
        String name = cls.getName();
        String valueOf = String.valueOf(zzh.zzc());
        Set<Class> zze = zzh.zze();
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (Class cls2 : zze) {
            if (!z) {
                sb.append(", ");
            }
            sb.append(cls2.getCanonicalName());
            z = false;
        }
        String sb2 = sb.toString();
        throw new GeneralSecurityException("Primitive type " + name + " not supported by key manager of type " + valueOf + ", supported primitives: " + sb2);
    }

    private final synchronized zzbc zzh(String str) throws GeneralSecurityException {
        if (!this.zzb.containsKey(str)) {
            throw new GeneralSecurityException("No key manager found for key type ".concat(String.valueOf(str)));
        }
        return (zzbc) this.zzb.get(str);
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x005d, code lost:
        r6.zzb.putIfAbsent(r0, r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0063, code lost:
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final synchronized void zzi(com.google.android.gms.internal.p001firebaseauthapi.zzbc r7, boolean r8) throws java.security.GeneralSecurityException {
        java.lang.String r0 = r7.zzb().zze();
        com.google.android.gms.internal.p001firebaseauthapi.zzbc r1 = (com.google.android.gms.internal.p001firebaseauthapi.zzbc) r6.zzb.get(r0);
        if (r1 != null && !r1.zzc().equals(r7.zzc())) {
            com.google.android.gms.internal.p001firebaseauthapi.zzbd.zza.logp(java.util.logging.Level.WARNING, "com.google.crypto.tink.KeyManagerRegistry", "registerKeyManagerContainer", "Attempted overwrite of a registered key manager for key type ".concat(r0));
            throw new java.security.GeneralSecurityException(java.lang.String.format("typeUrl (%s) is already registered with %s, cannot be re-registered with %s", r0, r1.zzc().getName(), r7.zzc().getName()));
        }
        r6.zzb.put(r0, r7);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Deprecated
    public final zzax zza(String str) throws GeneralSecurityException {
        return zzg(str, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzax zzb(String str, Class cls) throws GeneralSecurityException {
        return zzg(str, cls);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzax zzc(String str) throws GeneralSecurityException {
        return zzh(str).zzb();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized void zzd(zzgx zzgxVar, zzgc zzgcVar) throws GeneralSecurityException {
        Class zzd;
        int zzf = zzgcVar.zzf();
        if (!zzdv.zza(1)) {
            String valueOf = String.valueOf(zzgxVar.getClass());
            throw new GeneralSecurityException("failed to register key manager " + valueOf + " as it is not FIPS compatible.");
        } else if (zzdv.zza(zzf)) {
            String zzd2 = zzgxVar.zzd();
            String zzd3 = zzgcVar.zzd();
            if (this.zzb.containsKey(zzd2) && ((zzbc) this.zzb.get(zzd2)).zzd() != null && (zzd = ((zzbc) this.zzb.get(zzd2)).zzd()) != null && !zzd.getName().equals(zzgcVar.getClass().getName())) {
                Logger logger = zza;
                Level level = Level.WARNING;
                logger.logp(level, "com.google.crypto.tink.KeyManagerRegistry", "registerAsymmetricKeyManagers", "Attempted overwrite of a registered key manager for key type " + zzd2 + " with inconsistent public key type " + zzd3);
                throw new GeneralSecurityException(String.format("public key manager corresponding to %s is already registered with %s, cannot be re-registered with %s", zzgxVar.getClass().getName(), zzd.getName(), zzgcVar.getClass().getName()));
            }
            zzi(new zzbb(zzgxVar, zzgcVar), true);
            zzi(new zzba(zzgcVar), false);
        } else {
            String valueOf2 = String.valueOf(zzgcVar.getClass());
            throw new GeneralSecurityException("failed to register key manager " + valueOf2 + " as it is not FIPS compatible.");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized void zze(zzgc zzgcVar) throws GeneralSecurityException {
        if (zzdv.zza(zzgcVar.zzf())) {
            zzi(new zzba(zzgcVar), false);
        } else {
            String valueOf = String.valueOf(zzgcVar.getClass());
            throw new GeneralSecurityException("failed to register key manager " + valueOf + " as it is not FIPS compatible.");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean zzf(String str) {
        return this.zzb.containsKey(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbd(zzbd zzbdVar) {
        this.zzb = new ConcurrentHashMap(zzbdVar.zzb);
    }
}
