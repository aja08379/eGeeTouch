package com.google.android.gms.internal.p001firebaseauthapi;

import android.os.Build;
import android.util.Log;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.util.Arrays;
import java.util.Locale;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzfp  reason: invalid package */
/* loaded from: classes.dex */
public final class zzfp implements zzbk {
    private static final String zza = "zzfp";
    private KeyStore zzb;

    public zzfp() throws GeneralSecurityException {
        if (Build.VERSION.SDK_INT >= 23) {
            try {
                KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
                keyStore.load(null);
                this.zzb = keyStore;
                return;
            } catch (IOException | GeneralSecurityException e) {
                throw new IllegalStateException(e);
            }
        }
        throw new IllegalStateException("need Android Keystore on Android M or newer");
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzbk
    public final synchronized zzap zza(String str) throws GeneralSecurityException {
        zzfo zzfoVar;
        zzfoVar = new zzfo(zzqs.zza("android-keystore://", str), this.zzb);
        byte[] zza2 = zzqq.zza(10);
        byte[] bArr = new byte[0];
        if (!Arrays.equals(zza2, zzfoVar.zza(zzfoVar.zzb(zza2, bArr), bArr))) {
            throw new KeyStoreException("cannot use Android Keystore: encryption/decryption of non-empty message and empty aad returns an incorrect result");
        }
        return zzfoVar;
    }

    @Override // com.google.android.gms.internal.p001firebaseauthapi.zzbk
    public final synchronized boolean zzb(String str) {
        return str.toLowerCase(Locale.US).startsWith("android-keystore://");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized boolean zzc(String str) throws GeneralSecurityException {
        String str2;
        try {
        } catch (NullPointerException unused) {
            Log.w(zza, "Keystore is temporarily unavailable, wait 20ms, reinitialize Keystore and try again.");
            try {
                Thread.sleep(20L);
                KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
                this.zzb = keyStore;
                keyStore.load(null);
            } catch (IOException e) {
                throw new GeneralSecurityException(e);
            } catch (InterruptedException unused2) {
            }
            return this.zzb.containsAlias(str2);
        }
        return this.zzb.containsAlias(zzqs.zza("android-keystore://", str));
    }
}
