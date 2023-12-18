package com.google.android.gms.internal.p001firebaseauthapi;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import java.io.CharConversionException;
import java.io.FileNotFoundException;
import java.io.IOException;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzfq  reason: invalid package */
/* loaded from: classes.dex */
public final class zzfq {
    private final SharedPreferences zza;
    private final String zzb = "GenericIdpKeyset";

    public zzfq(Context context, String str, String str2) throws IOException {
        Context applicationContext = context.getApplicationContext();
        if (str2 == null) {
            this.zza = PreferenceManager.getDefaultSharedPreferences(applicationContext);
        } else {
            this.zza = applicationContext.getSharedPreferences(str2, 0);
        }
    }

    private final byte[] zzc() throws IOException {
        try {
            String string = this.zza.getString(this.zzb, null);
            if (string != null) {
                if (string.length() % 2 == 0) {
                    int length = string.length() / 2;
                    byte[] bArr = new byte[length];
                    for (int i = 0; i < length; i++) {
                        int i2 = i + i;
                        int digit = Character.digit(string.charAt(i2), 16);
                        int digit2 = Character.digit(string.charAt(i2 + 1), 16);
                        if (digit != -1 && digit2 != -1) {
                            bArr[i] = (byte) ((digit * 16) + digit2);
                        } else {
                            throw new IllegalArgumentException("input is not hexadecimal");
                        }
                    }
                    return bArr;
                }
                throw new IllegalArgumentException("Expected a string of even length");
            }
            throw new FileNotFoundException(String.format("can't read keyset; the pref value %s does not exist", this.zzb));
        } catch (ClassCastException | IllegalArgumentException unused) {
            throw new CharConversionException(String.format("can't read keyset; the pref value %s is not a valid hex string", this.zzb));
        }
    }

    public final zzmo zza() throws IOException {
        return zzmo.zzc(zzc(), zzacs.zza());
    }

    public final zzof zzb() throws IOException {
        return zzof.zzf(zzc(), zzacs.zza());
    }
}
