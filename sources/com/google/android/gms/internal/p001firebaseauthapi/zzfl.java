package com.google.android.gms.internal.p001firebaseauthapi;

import android.content.Context;
import android.os.Build;
import android.security.keystore.KeyGenParameterSpec;
import android.util.Log;
import com.google.android.gms.stats.CodePackage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyStoreException;
import java.security.ProviderException;
import javax.crypto.KeyGenerator;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzfl */
/* loaded from: classes.dex */
public final class zzfl {
    private zzbi zze;
    private zzfq zzf = null;
    private zzbj zza = null;
    private String zzb = null;
    private zzap zzc = null;
    private zzbf zzd = null;

    private final zzap zzh() throws GeneralSecurityException {
        String str;
        String str2;
        String str3;
        if (Build.VERSION.SDK_INT >= 23) {
            zzfp zzfpVar = new zzfp();
            boolean zzc = zzfpVar.zzc(this.zzb);
            if (!zzc) {
                try {
                    String str4 = this.zzb;
                    if (new zzfp().zzc(str4)) {
                        throw new IllegalArgumentException(String.format("cannot generate a new key %s because it already exists; please delete it with deleteKey() and try again", str4));
                    }
                    String zza = zzqs.zza("android-keystore://", str4);
                    KeyGenerator keyGenerator = KeyGenerator.getInstance("AES", "AndroidKeyStore");
                    keyGenerator.init(new KeyGenParameterSpec.Builder(zza, 3).setKeySize(256).setBlockModes(CodePackage.GCM).setEncryptionPaddings("NoPadding").build());
                    keyGenerator.generateKey();
                } catch (GeneralSecurityException | ProviderException e) {
                    str2 = zzfn.zzb;
                    Log.w(str2, "cannot use Android Keystore, it'll be disabled", e);
                    return null;
                }
            }
            try {
                return zzfpVar.zza(this.zzb);
            } catch (GeneralSecurityException | ProviderException e2) {
                if (zzc) {
                    throw new KeyStoreException(String.format("the master key %s exists but is unusable", this.zzb), e2);
                }
                str3 = zzfn.zzb;
                Log.w(str3, "cannot use Android Keystore, it'll be disabled", e2);
                return null;
            }
        }
        str = zzfn.zzb;
        Log.w(str, "Android Keystore requires at least Android M");
        return null;
    }

    private final zzbi zzi() throws GeneralSecurityException, IOException {
        String str;
        zzap zzapVar = this.zzc;
        if (zzapVar != null) {
            try {
                return zzbi.zzf(zzbh.zzh(this.zzf, zzapVar));
            } catch (zzadn | GeneralSecurityException e) {
                str = zzfn.zzb;
                Log.w(str, "cannot decrypt keyset: ", e);
            }
        }
        return zzbi.zzf(zzar.zzb(this.zzf));
    }

    @Deprecated
    public final zzfl zzd(zznx zznxVar) {
        String zzf = zznxVar.zzf();
        byte[] zzt = zznxVar.zze().zzt();
        zzoy zzd = zznxVar.zzd();
        int i = zzfn.zza;
        zzoy zzoyVar = zzoy.UNKNOWN_PREFIX;
        int ordinal = zzd.ordinal();
        int i2 = 4;
        if (ordinal == 1) {
            i2 = 1;
        } else if (ordinal == 2) {
            i2 = 2;
        } else if (ordinal == 3) {
            i2 = 3;
        } else if (ordinal != 4) {
            throw new IllegalArgumentException("Unknown output prefix type");
        }
        this.zzd = zzbf.zze(zzf, zzt, i2);
        return this;
    }

    public final zzfl zze(String str) {
        if (str.startsWith("android-keystore://")) {
            this.zzb = str;
            return this;
        }
        throw new IllegalArgumentException("key URI must start with android-keystore://");
    }

    public final zzfl zzf(Context context, String str, String str2) throws IOException {
        if (context == null) {
            throw new IllegalArgumentException("need an Android context");
        }
        this.zzf = new zzfq(context, "GenericIdpKeyset", str2);
        this.zza = new zzfr(context, "GenericIdpKeyset", str2);
        return this;
    }

    public final synchronized zzfn zzg() throws GeneralSecurityException, IOException {
        String str;
        zzbi zze;
        String str2;
        if (this.zzb != null) {
            this.zzc = zzh();
        }
        try {
            zze = zzi();
        } catch (FileNotFoundException e) {
            str = zzfn.zzb;
            if (Log.isLoggable(str, 4)) {
                str2 = zzfn.zzb;
                Log.i(str2, String.format("keyset not found, will generate a new one. %s", e.getMessage()));
            }
            if (this.zzd != null) {
                zze = zzbi.zze();
                zze.zzc(this.zzd);
                zze.zzd(zze.zzb().zzd().zzb(0).zza());
                if (this.zzc != null) {
                    zze.zzb().zzf(this.zza, this.zzc);
                } else {
                    zzar.zza(zze.zzb(), this.zza);
                }
            } else {
                throw new GeneralSecurityException("cannot read or generate keyset");
            }
        }
        this.zze = zze;
        return new zzfn(this, null);
    }
}
