package com.google.firebase.auth.internal;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.core.ServerValues;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* loaded from: classes2.dex */
public final class zzbd {
    private static final List zza = new ArrayList(Arrays.asList("firebaseAppName", "firebaseUserUid", "operation", "tenantId", "verifyAssertionRequest", "statusCode", "statusMessage", ServerValues.NAME_OP_TIMESTAMP));
    private static final zzbd zzb = new zzbd();
    private Task zzc;
    private Task zzd;
    private long zze = 0;

    private zzbd() {
    }

    public static zzbd zzc() {
        return zzb;
    }

    private static final void zzf(SharedPreferences sharedPreferences) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        for (String str : zza) {
            edit.remove(str);
        }
        edit.commit();
    }

    public final Task zza() {
        if (DefaultClock.getInstance().currentTimeMillis() - this.zze < 3600000) {
            return this.zzc;
        }
        return null;
    }

    public final Task zzb() {
        if (DefaultClock.getInstance().currentTimeMillis() - this.zze < 3600000) {
            return this.zzd;
        }
        return null;
    }

    public final void zzd(Context context) {
        Preconditions.checkNotNull(context);
        zzf(context.getSharedPreferences("com.google.firebase.auth.internal.ProcessDeathHelper", 0));
        this.zzc = null;
        this.zze = 0L;
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x007f, code lost:
        if (r4.equals("com.google.firebase.auth.internal.NONGMSCORE_SIGN_IN") == false) goto L33;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void zze(com.google.firebase.auth.FirebaseAuth r13) {
        com.google.android.gms.common.internal.Preconditions.checkNotNull(r13);
        boolean r2 = false;
        android.content.SharedPreferences r0 = r13.getApp().getApplicationContext().getSharedPreferences("com.google.firebase.auth.internal.ProcessDeathHelper", 0);
        if (r13.getApp().getName().equals(r0.getString("firebaseAppName", ""))) {
            if (r0.contains("verifyAssertionRequest")) {
                com.google.android.gms.internal.p001firebaseauthapi.zzaay r1 = (com.google.android.gms.internal.p001firebaseauthapi.zzaay) com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer.deserializeFromString(r0.getString("verifyAssertionRequest", ""), com.google.android.gms.internal.p001firebaseauthapi.zzaay.CREATOR);
                java.lang.String r4 = r0.getString("operation", "");
                java.lang.String r6 = r0.getString("tenantId", null);
                java.lang.String r3 = r0.getString("firebaseUserUid", "");
                r12.zze = r0.getLong(com.google.firebase.database.core.ServerValues.NAME_OP_TIMESTAMP, 0L);
                if (r6 != null) {
                    r13.setTenantId(r6);
                    r1.zzf(r6);
                }
                int r6 = r4.hashCode();
                if (r6 == -98509410) {
                    if (r4.equals("com.google.firebase.auth.internal.NONGMSCORE_REAUTHENTICATE")) {
                        r2 = true;
                    }
                    r2 = true;
                } else if (r6 != 175006864) {
                    if (r6 == 1450464913) {
                    }
                    r2 = true;
                } else {
                    if (r4.equals("com.google.firebase.auth.internal.NONGMSCORE_LINK")) {
                        r2 = true;
                    }
                    r2 = true;
                }
                if (!r2) {
                    r12.zzc = r13.signInWithCredential(com.google.firebase.auth.zze.zzb(r1));
                } else if (!r2) {
                    if (!r2) {
                        r12.zzc = null;
                    } else if (r13.getCurrentUser().getUid().equals(r3)) {
                        r12.zzc = r13.zzf(r13.getCurrentUser(), com.google.firebase.auth.zze.zzb(r1));
                    } else {
                        r12.zzc = null;
                    }
                } else if (r13.getCurrentUser().getUid().equals(r3)) {
                    r12.zzc = r13.zzd(r13.getCurrentUser(), com.google.firebase.auth.zze.zzb(r1));
                } else {
                    r12.zzc = null;
                }
                zzf(r0);
            } else if (r0.contains("recaptchaToken")) {
                java.lang.String r13 = r0.getString("recaptchaToken", "");
                java.lang.String r1 = r0.getString("operation", "");
                r12.zze = r0.getLong(com.google.firebase.database.core.ServerValues.NAME_OP_TIMESTAMP, 0L);
                if (r1.hashCode() != -214796028 || !r1.equals("com.google.firebase.auth.internal.ACTION_SHOW_RECAPTCHA")) {
                    r2 = true;
                }
                if (r2) {
                    r12.zzd = null;
                } else {
                    r12.zzd = com.google.android.gms.tasks.Tasks.forResult(r13);
                }
                zzf(r0);
            } else if (r0.contains("statusCode")) {
                com.google.android.gms.common.api.Status r2 = new com.google.android.gms.common.api.Status(r0.getInt("statusCode", 17062), r0.getString("statusMessage", ""));
                r12.zze = r0.getLong(com.google.firebase.database.core.ServerValues.NAME_OP_TIMESTAMP, 0L);
                zzf(r0);
                r12.zzc = com.google.android.gms.tasks.Tasks.forException(com.google.android.gms.internal.p001firebaseauthapi.zzxc.zza(r2));
            }
        }
    }
}
